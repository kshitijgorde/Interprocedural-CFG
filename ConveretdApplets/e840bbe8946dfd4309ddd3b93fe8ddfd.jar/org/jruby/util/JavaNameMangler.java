// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Pattern;

public class JavaNameMangler
{
    public static final Pattern PATH_SPLIT;
    private static final String DANGEROUS_CHARS = "\\/.;:$[]<>";
    private static final String REPLACEMENT_CHARS = "-|,?!%{}^_";
    private static final char ESCAPE_C = '\\';
    private static final char NULL_ESCAPE_C = '=';
    private static final String NULL_ESCAPE = "\\=";
    
    public static String mangledFilenameForStartupClasspath(final String filename) {
        if (filename.equals("-e")) {
            return "ruby/__dash_e__";
        }
        return mangleFilenameForClasspath(filename, null, "", false, false);
    }
    
    public static String mangleFilenameForClasspath(final String filename) {
        return mangleFilenameForClasspath(filename, null, "ruby");
    }
    
    public static String mangleFilenameForClasspath(final String filename, final String parent, final String prefix) {
        return mangleFilenameForClasspath(filename, parent, prefix, true, false);
    }
    
    public static String mangleFilenameForClasspath(final String filename, final String parent, final String prefix, final boolean canonicalize, final boolean preserveIdentifiers) {
        try {
            String classPath = "";
            if (filename.indexOf("!") != -1) {
                final String before = filename.substring(6, filename.indexOf("!"));
                if (canonicalize) {
                    classPath = new JRubyFile(before + filename.substring(filename.indexOf("!") + 1)).getCanonicalPath().toString();
                }
                else {
                    classPath = new JRubyFile(before + filename.substring(filename.indexOf("!") + 1)).toString();
                }
            }
            else {
                try {
                    if (canonicalize) {
                        classPath = new JRubyFile(filename).getCanonicalPath().toString();
                    }
                    else {
                        classPath = new JRubyFile(filename).toString();
                    }
                }
                catch (IOException ioe2) {
                    classPath = filename;
                }
            }
            if (parent != null && parent.length() > 0) {
                String parentPath;
                try {
                    if (canonicalize) {
                        parentPath = new JRubyFile(parent).getCanonicalPath().toString();
                    }
                    else {
                        parentPath = new JRubyFile(parent).toString();
                    }
                }
                catch (IOException ioe3) {
                    parentPath = parent;
                }
                if (!classPath.startsWith(parentPath)) {
                    throw new FileNotFoundException("File path " + classPath + " does not start with parent path " + parentPath);
                }
                final int parentLength = parentPath.length();
                classPath = classPath.substring(parentLength);
            }
            final String[] pathElements = JavaNameMangler.PATH_SPLIT.split(classPath);
            final StringBuilder newPath = new StringBuilder(prefix);
            for (final String element : pathElements) {
                if (element.length() > 0) {
                    if (newPath.length() > 0) {
                        newPath.append("/");
                    }
                    if (!Character.isJavaIdentifierStart(element.charAt(0))) {
                        newPath.append("$");
                    }
                    String pathId = element;
                    if (!preserveIdentifiers) {
                        pathId = mangleStringForCleanJavaIdentifier(element);
                    }
                    newPath.append(pathId);
                }
            }
            final int dotRbIndex = newPath.indexOf("_dot_rb");
            if (dotRbIndex != -1 && dotRbIndex == newPath.length() - 7) {
                newPath.delete(dotRbIndex, dotRbIndex + 7);
            }
            return newPath.toString();
        }
        catch (IOException ioe) {
            ioe.printStackTrace();
            throw new RuntimeException(ioe);
        }
    }
    
    public static String mangleStringForCleanJavaIdentifier(final String name) {
        final char[] characters = name.toCharArray();
        final StringBuilder cleanBuffer = new StringBuilder();
        boolean prevWasReplaced = false;
        for (int i = 0; i < characters.length; ++i) {
            if ((i == 0 && Character.isJavaIdentifierStart(characters[i])) || Character.isJavaIdentifierPart(characters[i])) {
                cleanBuffer.append(characters[i]);
                prevWasReplaced = false;
            }
            else {
                if (!prevWasReplaced) {
                    cleanBuffer.append("_");
                }
                prevWasReplaced = true;
                switch (characters[i]) {
                    case '?': {
                        cleanBuffer.append("p_");
                        break;
                    }
                    case '!': {
                        cleanBuffer.append("b_");
                        break;
                    }
                    case '<': {
                        cleanBuffer.append("lt_");
                        break;
                    }
                    case '>': {
                        cleanBuffer.append("gt_");
                        break;
                    }
                    case '=': {
                        cleanBuffer.append("equal_");
                        break;
                    }
                    case '[': {
                        if (i + 1 < characters.length && characters[i + 1] == ']') {
                            cleanBuffer.append("aref_");
                            ++i;
                            break;
                        }
                        cleanBuffer.append("lbracket_");
                        break;
                    }
                    case ']': {
                        cleanBuffer.append("rbracket_");
                        break;
                    }
                    case '+': {
                        cleanBuffer.append("plus_");
                        break;
                    }
                    case '-': {
                        cleanBuffer.append("minus_");
                        break;
                    }
                    case '*': {
                        cleanBuffer.append("times_");
                        break;
                    }
                    case '/': {
                        cleanBuffer.append("div_");
                        break;
                    }
                    case '&': {
                        cleanBuffer.append("and_");
                        break;
                    }
                    case '.': {
                        cleanBuffer.append("dot_");
                        break;
                    }
                    default: {
                        cleanBuffer.append(Integer.toHexString(characters[i])).append("_");
                        break;
                    }
                }
            }
        }
        return cleanBuffer.toString();
    }
    
    public static String mangleMethodName(final String name) {
        StringBuilder builder = null;
        for (int i = 0; i < name.length(); ++i) {
            final char candidate = name.charAt(i);
            final int escape = escapeChar(candidate);
            if (escape != -1) {
                if (builder == null) {
                    builder = new StringBuilder();
                    builder.append("\\=");
                    builder.append(name.substring(0, i));
                }
                builder.append('\\').append((char)escape);
            }
            else if (builder != null) {
                builder.append(candidate);
            }
        }
        if (builder != null) {
            return builder.toString();
        }
        return name;
    }
    
    public static String demangleMethodName(final String name) {
        if (!name.startsWith("\\=")) {
            return name;
        }
        final StringBuilder builder = new StringBuilder();
        for (int i = 2; i < name.length(); ++i) {
            final char candidate = name.charAt(i);
            if (candidate == '\\') {
                ++i;
                final char escaped = name.charAt(i);
                final char unescape = unescapeChar(escaped);
                builder.append(unescape);
            }
            else {
                builder.append(candidate);
            }
        }
        return builder.toString();
    }
    
    public static String unmangleMethodName(final String name) {
        return name.replaceAll("\\", "/");
    }
    
    private static int escapeChar(final char character) {
        final int index = "\\/.;:$[]<>".indexOf(character);
        if (index == -1) {
            return -1;
        }
        return "-|,?!%{}^_".charAt(index);
    }
    
    private static char unescapeChar(final char character) {
        return "\\/.;:$[]<>".charAt("-|,?!%{}^_".indexOf(character));
    }
    
    static {
        PATH_SPLIT = Pattern.compile("[/\\\\]");
    }
}
