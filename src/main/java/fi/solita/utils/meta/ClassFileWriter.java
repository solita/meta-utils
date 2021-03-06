package fi.solita.utils.meta;

import static fi.solita.utils.functional.Collections.newMutableList;
import static fi.solita.utils.functional.Collections.newMutableMap;
import static fi.solita.utils.functional.Collections.newSet;
import static fi.solita.utils.functional.Functional.mkString;
import static fi.solita.utils.functional.FunctionalC.takeWhile;
import static fi.solita.utils.functional.Predicates.equalTo;
import static fi.solita.utils.functional.Predicates.not;

import java.io.Serializable;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.processing.Filer;
import javax.lang.model.element.TypeElement;
import javax.tools.FileObject;

import fi.solita.utils.functional.Option;

public class ClassFileWriter {
    
    private static final Pattern IMPORTS = Pattern.compile(Pattern.quote("{${") + "(([a-zA-Z0-9_]+\\.)*([a-zA-Z0-9_$]+))" + Pattern.quote("}$}"));
    private static final String SERIALIZABLE = Serializable.class.getSimpleName();
    private static final String LINE_SEP = System.getProperty("line.separator");
    
    public static void writeClassFile(String packageName, String classSimpleName, Option<String> extendedClassName, Iterable<String> contentLines, Class<?> generator, Filer filer, TypeElement originalClass, Option<SuppressWarnings> classSupressWarnings, boolean isDeprecated) {
        Map<String,String> toImport = newMutableMap();
        toImport.put(SERIALIZABLE, "java.io.Serializable");
        
        StringBuffer content = new StringBuffer();
        for (String line: contentLines) {
            Matcher m = IMPORTS.matcher(line);
            while (m.find()) {
                String fullyQualifiedName = m.group(1).replaceAll("[$]", ".");
                String importClauseName = takeWhile(not(equalTo('$')), m.group(1));
                String simpleName = takeWhile(not(equalTo('$')), m.group(3));
                String importName = m.group(3).replaceAll("[$]", ".");
                String alreadyImported = toImport.get(simpleName);
                if (alreadyImported == null || alreadyImported.equals(importClauseName)) {
                    toImport.put(simpleName, importClauseName);
                    m.appendReplacement(content, importName);
                } else {
                    m.appendReplacement(content, fullyQualifiedName);
                }
            }
            m.appendTail(content);
            content.append(LINE_SEP);
        }
        
        List<String> suppress = newMutableList();
        for (SuppressWarnings sw: classSupressWarnings) {
            for (String sws: sw.value()) {
                if (!"unused".equals(sws)) {
                    suppress.add(sws);
                }
            }
        }
        suppress.add("serial");
        
        try {
            String extend = extendedClassName.isDefined() ? " extends " + extendedClassName.get() : "";
            FileObject fo = filer.createSourceFile((packageName.isEmpty() ? "" : packageName + ".") + classSimpleName, originalClass);
            Writer pw = fo.openWriter();
            
            if (!packageName.isEmpty()) {
                pw.append("package ")
                  .append(packageName)
                  .append(';')
                  .append(LINE_SEP);
            }
            for (String qualifiedName: newSet(toImport.values())) {
                pw.append("import ")
                  .append(qualifiedName)
                  .append(";")
                  .append(LINE_SEP);
            }
            pw.append(LINE_SEP)
              .append("@SuppressWarnings({\"" + mkString("\",\"", suppress) + "\"})")
              .append(LINE_SEP);
            if (isDeprecated) {
                pw.append("@Deprecated")
                  .append(LINE_SEP);
            }
            pw.append("public class ")
              .append(classSimpleName)
              .append(extend)
              .append(" implements ")
              .append(SERIALIZABLE)
              .append(" {")
              .append(LINE_SEP)
              .append(LINE_SEP)
              .append(content)
              .append('}');
            
            pw.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
