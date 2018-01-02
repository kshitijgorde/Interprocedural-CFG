// 
// Decompiled by Procyon v0.5.30
// 

package com.pchat.sc;

import java.util.Properties;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class StringUtil
{
    public static String boolText(final boolean b) {
        if (b) {
            return "true";
        }
        return "false";
    }
    
    public static boolean getBool(final String s) {
        return isTrue(s);
    }
    
    public static boolean getBool(final String s, final boolean b) {
        if (s == null) {
            return b;
        }
        return isTrue(s) || (!isFalse(s) && b);
    }
    
    public static boolean isTrue(String trim) {
        if (trim == null) {
            return false;
        }
        trim = trim.trim();
        return trim.equalsIgnoreCase("true") || trim.equalsIgnoreCase("yes") || trim.equalsIgnoreCase("enabled") || trim.equalsIgnoreCase("on");
    }
    
    public static boolean isFalse(String trim) {
        if (trim == null) {
            return false;
        }
        trim = trim.trim();
        return trim.equalsIgnoreCase("false") || trim.equalsIgnoreCase("off") || trim.equalsIgnoreCase("disabled") || trim.equalsIgnoreCase("no");
    }
    
    public static String[] splitString(final String s, final String s2, final boolean b) {
        if (s == null) {
            return null;
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s, s2);
        final int countTokens = stringTokenizer.countTokens();
        final String[] array = new String[countTokens];
        for (int i = 0; i < countTokens; ++i) {
            String s3 = stringTokenizer.nextToken();
            if (b) {
                s3 = s3.trim();
            }
            array[i] = s3;
        }
        return array;
    }
    
    public static String catString(final String[] array, final String s) {
        if (array == null) {
            return null;
        }
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            if (i > 0) {
                sb.append(s);
            }
            sb.append(array[i]);
        }
        return sb.toString();
    }
    
    public static String appendList(final String s, final String s2, final String s3) {
        if (isEmpty(s)) {
            return s3;
        }
        return s + s2 + s3;
    }
    
    public static boolean isInList(final String s, final String s2, final String s3, final boolean b) {
        if (isEmpty(s) || isEmpty(s3)) {
            return false;
        }
        final String[] splitString = splitString(s, s2, b);
        for (int i = 0; i < splitString.length; ++i) {
            if (s3.equals(splitString[i])) {
                return true;
            }
        }
        return false;
    }
    
    public static String removeFromList(final String s, final String s2, final String s3, final boolean b, final boolean b2) {
        if (isEmpty(s) || isEmpty(s3)) {
            return s;
        }
        int n = 1;
        final String lowerCase = s3.toLowerCase();
        final String[] splitString = splitString(s, s2, b);
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < splitString.length; ++i) {
            boolean b3 = true;
            if (b2) {
                if (s3.equals(splitString[i])) {
                    b3 = false;
                }
            }
            else if (lowerCase.equals(splitString[i].toLowerCase())) {
                b3 = false;
            }
            if (b3) {
                if (n == 0) {
                    sb.append(s2);
                }
                sb.append(splitString[i]);
                n = 0;
            }
        }
        return sb.toString();
    }
    
    public static boolean isEmpty(final String s) {
        return s == null || s.length() == 0;
    }
    
    public static boolean isTrimmedEmpty(String trim) {
        if (trim == null) {
            return true;
        }
        trim = trim.trim();
        return trim.length() == 0;
    }
    
    public static String showByte(final byte[] array) {
        final StringBuffer sb = new StringBuffer();
        sb.append("length=");
        sb.append(array.length);
        sb.append(" ");
        sb.append("bytes= ");
        for (int i = 0; i < array.length; ++i) {
            sb.append(Byte.toString(array[i]));
            sb.append(" ");
        }
        return sb.toString();
    }
    
    public static String showByte(final String s) {
        return showByte(s.getBytes());
    }
    
    public static String showByte(final StringBuffer sb) {
        return showByte(sb.toString());
    }
    
    public static String genAttributes(final Hashtable hashtable) {
        if (hashtable == null) {
            return null;
        }
        if (hashtable.size() == 0) {
            return "";
        }
        final StringBuffer sb = new StringBuffer();
        final Enumeration keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            final String s = keys.nextElement();
            final String s2 = hashtable.get(s);
            sb.append(s);
            sb.append(":");
            sb.append(s2);
            sb.append("\n");
        }
        return sb.toString();
    }
    
    public static Properties decAttributes(final String s) {
        if (s == null) {
            return null;
        }
        final Properties properties = new Properties();
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "\n");
        while (stringTokenizer.hasMoreTokens()) {
            addProp(properties, stringTokenizer.nextToken());
        }
        return properties;
    }
    
    private static void addProp(final Properties properties, final String s) {
        final int index = s.indexOf(":");
        if (index < 0) {
            ((Hashtable<String, String>)properties).put(s, "");
            return;
        }
        if (index == 0) {
            return;
        }
        final String substring = s.substring(0, index);
        String substring2;
        if (index + 1 == s.length()) {
            substring2 = "";
        }
        else {
            substring2 = s.substring(index + 1);
        }
        ((Hashtable<String, String>)properties).put(substring, substring2);
    }
}
