// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

public final class IdUtil
{
    public static boolean isConstant(final String id) {
        return Character.isUpperCase(id.charAt(0));
    }
    
    public static boolean isClassVariable(final String id) {
        return id.length() > 1 && id.charAt(0) == '@' && id.charAt(1) == '@';
    }
    
    public static boolean isInstanceVariable(final String id) {
        return id.length() > 0 && id.charAt(0) == '@' && (id.length() < 2 || id.charAt(1) != '@');
    }
    
    public static boolean isGlobal(final String id) {
        return id.length() > 0 && id.charAt(0) == '$';
    }
    
    public static boolean isLocal(final String id) {
        return !isGlobal(id) && !isClassVariable(id) && !isInstanceVariable(id) && !isConstant(id);
    }
    
    public static boolean isAttrSet(final String id) {
        return id.endsWith("=");
    }
    
    public static boolean isValidConstantName(final String id) {
        final int len;
        final char c;
        return (len = id.length()) > 0 && (c = id.charAt(0)) <= 'Z' && c >= 'A' && isNameString(id, 1, len);
    }
    
    public static boolean isValidInstanceVariableName(final String id) {
        final int len;
        return (len = id.length()) > 0 && '@' == id.charAt(0) && (len <= 1 || (isInitialCharacter(id.charAt(1)) && isNameString(id, 2, len)));
    }
    
    public static boolean isValidClassVariableName(final String id) {
        final int len;
        return (len = id.length()) > 1 && '@' == id.charAt(0) && '@' == id.charAt(1) && (len <= 2 || (isInitialCharacter(id.charAt(2)) && isNameString(id, 3, len)));
    }
    
    public static boolean isInitialCharacter(int c) {
        return ((c &= 0xFFFFFFDF) <= 90 && c >= 65) || c == 95;
    }
    
    public static boolean isNameCharacter(final char c) {
        final int letter;
        return ((letter = (c & 0xFFFFFFDF)) <= 90 && letter >= 65) || c == '_' || (c <= '9' && c >= '0');
    }
    
    public static boolean isNameString(final String id, final int start, final int limit) {
        for (int i = start; i < limit; ++i) {
            if (!isNameCharacter(id.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public static final boolean isRubyVariable(final String name) {
        final char c;
        return name.length() > 0 && ((c = name.charAt(0)) == '@' || (c <= 'Z' && c >= 'A'));
    }
}
