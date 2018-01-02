// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.crimson.util;

public class XmlNames
{
    public static final String SPEC_XML_URI = "http://www.w3.org/XML/1998/namespace";
    public static final String SPEC_XMLNS_URI = "http://www.w3.org/2000/xmlns/";
    
    public static boolean isName(final String value) {
        if (value == null || "".equals(value)) {
            return false;
        }
        final char c = value.charAt(0);
        if (!XmlChars.isLetter(c) && c != '_' && c != ':') {
            return false;
        }
        for (int i = 1; i < value.length(); ++i) {
            if (!XmlChars.isNameChar(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isUnqualifiedName(final String value) {
        if (value == null || value.length() == 0) {
            return false;
        }
        final char c = value.charAt(0);
        if (!XmlChars.isLetter(c) && c != '_') {
            return false;
        }
        for (int i = 1; i < value.length(); ++i) {
            if (!XmlChars.isNCNameChar(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isQualifiedName(final String value) {
        if (value == null) {
            return false;
        }
        final int first = value.indexOf(58);
        if (first <= 0) {
            return isUnqualifiedName(value);
        }
        final int last = value.lastIndexOf(58);
        return last == first && isUnqualifiedName(value.substring(0, first)) && isUnqualifiedName(value.substring(first + 1));
    }
    
    public static boolean isNmtoken(final String token) {
        for (int length = token.length(), i = 0; i < length; ++i) {
            if (!XmlChars.isNameChar(token.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isNCNmtoken(final String token) {
        return isNmtoken(token) && token.indexOf(58) < 0;
    }
    
    public static String getPrefix(final String qualifiedName) {
        final int index = qualifiedName.indexOf(58);
        return (index <= 0) ? null : qualifiedName.substring(0, index);
    }
    
    public static String getLocalPart(final String qualifiedName) {
        final int index = qualifiedName.indexOf(58);
        if (index < 0) {
            return qualifiedName;
        }
        if (index == qualifiedName.length() - 1) {
            return null;
        }
        return qualifiedName.substring(index + 1);
    }
}
