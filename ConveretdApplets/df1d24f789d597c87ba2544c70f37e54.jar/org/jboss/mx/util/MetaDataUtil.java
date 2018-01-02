// 
// Decompiled by Procyon v0.5.30
// 

package org.jboss.mx.util;

import java.util.HashSet;

public final class MetaDataUtil
{
    public static final String BOOLEAN_TYPE_NAME;
    public static final String BYTE_TYPE_NAME;
    public static final String CHAR_TYPE_NAME;
    public static final String DOUBLE_TYPE_NAME;
    public static final String FLOAT_TYPE_NAME;
    public static final String INT_TYPE_NAME;
    public static final String LONG_TYPE_NAME;
    public static final String SHORT_TYPE_NAME;
    public static final String VOID_TYPE_NAME;
    private static final HashSet reserved;
    
    public static final boolean isValidJavaIdentifier(final String string) {
        if (string == null || string.length() == 0) {
            return false;
        }
        final char[] chars = string.toCharArray();
        if (!Character.isJavaIdentifierStart(chars[0])) {
            return false;
        }
        for (int i = 1; i < chars.length; ++i) {
            if (!Character.isJavaIdentifierPart(chars[i])) {
                return false;
            }
        }
        return !MetaDataUtil.reserved.contains(string);
    }
    
    public static final boolean isValidJavaType(String string) {
        if (string == null || string.length() == 0) {
            return false;
        }
        if (string.charAt(0) == '[') {
            final String baseClassName = getBaseClassName(string);
            if (baseClassName == null) {
                return false;
            }
            string = baseClassName;
        }
        if (isPrimitive(string)) {
            return true;
        }
        final char[] chars = string.toCharArray();
        int start = 0;
        for (int i = 0; i < chars.length; ++i) {
            if (chars[i] == '.') {
                if (i == start) {
                    return false;
                }
                if (!isValidJavaIdentifier(string.substring(start, i))) {
                    return false;
                }
                start = i + 1;
            }
        }
        return start >= chars.length || isValidJavaIdentifier(string.substring(start, chars.length));
    }
    
    public static String getBaseClassName(final String className) {
        final int length = className.length();
        final int last = length - 1;
        int i;
        for (i = 0; i < length && className.charAt(i) == '['; ++i) {}
        if (i <= 0) {
            return className;
        }
        final char type = className.charAt(i);
        if (type == 'B' || type == 'C' || type == 'D' || type == 'F' || type == 'I' || type == 'J' || type == 'S' || type == 'Z' || type == 'V') {
            if (i != last) {
                return null;
            }
            return className.substring(last, length);
        }
        else {
            if (className.charAt(i) != 'L' || i >= last - 1 || className.charAt(last) != ';') {
                return null;
            }
            return className.substring(i + 1, last);
        }
    }
    
    public static boolean isPrimitive(final String string) {
        return string.equals(MetaDataUtil.INT_TYPE_NAME) || string.equals(MetaDataUtil.LONG_TYPE_NAME) || string.equals(MetaDataUtil.BOOLEAN_TYPE_NAME) || string.equals(MetaDataUtil.BYTE_TYPE_NAME) || string.equals(MetaDataUtil.CHAR_TYPE_NAME) || string.equals(MetaDataUtil.SHORT_TYPE_NAME) || string.equals(MetaDataUtil.FLOAT_TYPE_NAME) || string.equals(MetaDataUtil.DOUBLE_TYPE_NAME) || string.equals(MetaDataUtil.VOID_TYPE_NAME);
    }
    
    static {
        BOOLEAN_TYPE_NAME = Boolean.TYPE.getName();
        BYTE_TYPE_NAME = Byte.TYPE.getName();
        CHAR_TYPE_NAME = Character.TYPE.getName();
        DOUBLE_TYPE_NAME = Double.TYPE.getName();
        FLOAT_TYPE_NAME = Float.TYPE.getName();
        INT_TYPE_NAME = Integer.TYPE.getName();
        LONG_TYPE_NAME = Long.TYPE.getName();
        SHORT_TYPE_NAME = Short.TYPE.getName();
        VOID_TYPE_NAME = Void.TYPE.getName();
        (reserved = new HashSet()).add("assert");
        MetaDataUtil.reserved.add("abstract");
        MetaDataUtil.reserved.add("boolean");
        MetaDataUtil.reserved.add("break");
        MetaDataUtil.reserved.add("byte");
        MetaDataUtil.reserved.add("case");
        MetaDataUtil.reserved.add("catch");
        MetaDataUtil.reserved.add("char");
        MetaDataUtil.reserved.add("class");
        MetaDataUtil.reserved.add("const");
        MetaDataUtil.reserved.add("continue");
        MetaDataUtil.reserved.add("default");
        MetaDataUtil.reserved.add("do");
        MetaDataUtil.reserved.add("double");
        MetaDataUtil.reserved.add("else");
        MetaDataUtil.reserved.add("extends");
        MetaDataUtil.reserved.add("false");
        MetaDataUtil.reserved.add("final");
        MetaDataUtil.reserved.add("finally");
        MetaDataUtil.reserved.add("float");
        MetaDataUtil.reserved.add("for");
        MetaDataUtil.reserved.add("goto");
        MetaDataUtil.reserved.add("if");
        MetaDataUtil.reserved.add("implements");
        MetaDataUtil.reserved.add("import");
        MetaDataUtil.reserved.add("instanceof");
        MetaDataUtil.reserved.add("int");
        MetaDataUtil.reserved.add("interface");
        MetaDataUtil.reserved.add("long");
        MetaDataUtil.reserved.add("native");
        MetaDataUtil.reserved.add("new");
        MetaDataUtil.reserved.add("null");
        MetaDataUtil.reserved.add("package");
        MetaDataUtil.reserved.add("private");
        MetaDataUtil.reserved.add("protected");
        MetaDataUtil.reserved.add("public");
        MetaDataUtil.reserved.add("return");
        MetaDataUtil.reserved.add("short");
        MetaDataUtil.reserved.add("static");
        MetaDataUtil.reserved.add("strictfp");
        MetaDataUtil.reserved.add("super");
        MetaDataUtil.reserved.add("switch");
        MetaDataUtil.reserved.add("synchronized");
        MetaDataUtil.reserved.add("this");
        MetaDataUtil.reserved.add("throw");
        MetaDataUtil.reserved.add("throws");
        MetaDataUtil.reserved.add("transient");
        MetaDataUtil.reserved.add("true");
        MetaDataUtil.reserved.add("try");
        MetaDataUtil.reserved.add("void");
        MetaDataUtil.reserved.add("volatile");
        MetaDataUtil.reserved.add("while");
    }
}
