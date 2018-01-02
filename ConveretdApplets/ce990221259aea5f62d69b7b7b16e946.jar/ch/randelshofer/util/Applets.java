// 
// Decompiled by Procyon v0.5.30
// 

package ch.randelshofer.util;

import java.util.Hashtable;
import java.util.StringTokenizer;
import java.applet.Applet;

public class Applets
{
    public static String getParameter(final Applet applet, final String s, final String s2) {
        final String parameter = applet.getParameter(s);
        return (parameter != null) ? parameter : s2;
    }
    
    public static boolean getParameter(final Applet applet, final String s, final boolean b) {
        final String parameter = applet.getParameter(s);
        return (parameter != null) ? parameter.equals("true") : b;
    }
    
    public static String[] getParameters(final Applet applet, final String s, final String[] array) {
        final String parameter = applet.getParameter(s);
        if (parameter != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ", ");
            final String[] array2 = new String[stringTokenizer.countTokens()];
            for (int i = 0; i < array2.length; ++i) {
                array2[i] = stringTokenizer.nextToken();
            }
            return array2;
        }
        return array;
    }
    
    public static int getParameter(final Applet applet, final String s, final int n) {
        final String parameter = applet.getParameter(s);
        if (parameter != null) {
            try {
                return decode(parameter);
            }
            catch (NumberFormatException ex) {}
        }
        return n;
    }
    
    public static int[] getParameters(final Applet applet, final String s, final int[] array) {
        final String parameter = applet.getParameter(s);
        if (parameter != null) {
            try {
                final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ", ");
                final int[] array2 = new int[stringTokenizer.countTokens()];
                for (int i = 0; i < array2.length; ++i) {
                    array2[i] = decode(stringTokenizer.nextToken());
                }
                return array2;
            }
            catch (NumberFormatException ex) {}
        }
        return array;
    }
    
    public static double getParameter(final Applet applet, final String s, final double n) {
        final String parameter = applet.getParameter(s);
        if (parameter != null) {
            try {
                return Double.valueOf(parameter);
            }
            catch (NumberFormatException ex) {}
        }
        return n;
    }
    
    public static Hashtable getIndexedKeyValueParameters(final Applet applet, final String s, final Hashtable hashtable) {
        final String parameter = applet.getParameter(s);
        if (parameter != null) {
            final Hashtable hashtable2 = new Hashtable<String, String>();
            final StringTokenizer stringTokenizer = new StringTokenizer(parameter, ", ");
            for (int countTokens = stringTokenizer.countTokens(), i = 0; i < countTokens; ++i) {
                final String nextToken = stringTokenizer.nextToken();
                final int index = nextToken.indexOf(61);
                String substring;
                String substring2;
                if (index < 1) {
                    substring = null;
                    substring2 = nextToken;
                }
                else {
                    substring = nextToken.substring(0, index);
                    substring2 = nextToken.substring(index + 1);
                }
                final String string = Integer.toString(i);
                if (substring != null) {
                    hashtable2.put(substring, substring2);
                }
                if (!hashtable2.contains(string)) {
                    hashtable2.put(string, substring2);
                }
            }
            return hashtable2;
        }
        return hashtable;
    }
    
    public static Integer decode(final String s) throws NumberFormatException {
        int n = 10;
        int n2 = 0;
        boolean b = false;
        if (s.startsWith("-")) {
            b = true;
            ++n2;
        }
        if (s.startsWith("0x", n2) || s.startsWith("0X", n2)) {
            n2 += 2;
            n = 16;
        }
        else if (s.startsWith("#", n2)) {
            ++n2;
            n = 16;
        }
        else if (s.startsWith("0", n2) && s.length() > 1 + n2) {
            ++n2;
            n = 8;
        }
        if (s.startsWith("-", n2)) {
            throw new NumberFormatException("Negative sign in wrong position");
        }
        Integer value2;
        try {
            final Integer value = Integer.valueOf(s.substring(n2), n);
            value2 = (b ? new Integer(-value) : value);
        }
        catch (NumberFormatException ex) {
            String substring;
            if (b) {
                final StringBuffer sb;
                substring = new String(sb.append("-").append(s.substring(n2)).toString());
                sb = new StringBuffer();
            }
            else {
                substring = s.substring(n2);
            }
            value2 = Integer.valueOf(substring, n);
        }
        return value2;
    }
}
