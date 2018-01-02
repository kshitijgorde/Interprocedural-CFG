// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;
import java.awt.Color;
import java.awt.Image;
import java.util.Hashtable;

public class dV
{
    private Hashtable q;
    
    public dV(final String s) {
        this.q = new Hashtable();
    }
    
    public final void q(final String s, final Image image) {
        this.q.put(s, image);
    }
    
    public final Image q(final String s) {
        return this.q.get(s);
    }
    
    public dV() {
    }
    
    public static boolean q(String lowerCase, final String s) {
        if (lowerCase.length() == 0 || s.length() == 0) {
            return true;
        }
        int index = 0;
        final char[] charArray = s.toLowerCase().toCharArray();
        lowerCase = lowerCase.toLowerCase();
        for (int i = 0; i < charArray.length; ++i) {
            if ((index = lowerCase.indexOf(charArray[i], index)) == -1) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean q(final String s, final String s2, final String s3) {
        if (s.length() == 0 || s3 == null || s3.length() == 0) {
            return q(s, s2);
        }
        String substring = s2;
        final int index;
        if ((index = s2.indexOf(".")) >= 0) {
            substring = s2.substring(0, index);
        }
        try {
            final int int1;
            if ((int1 = Integer.parseInt(substring)) < 0 || int1 > 255) {
                throw new Exception();
            }
            return q(s3, s2);
        }
        catch (Exception ex) {
            return q(s, s2);
        }
    }
    
    public static String q(final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            char c;
            if ((c = (char)(s.charAt(i) + '[' - '+')) > 'z') {
                c = (char)(s.charAt(i) - '+');
            }
            sb.append(c);
        }
        return sb.toString();
    }
    
    public static String q(final Color color) {
        return Integer.toString(color.getRGB() & 0xFFFFFF, 16).toUpperCase();
    }
    
    public static Color q(final String s) {
        return new Color(Integer.parseInt(s, 16));
    }
    
    public static String[] q(String substring, final String s) {
        final Vector vector = new Vector<String>();
        int index;
        while ((index = substring.indexOf(s)) >= 0) {
            vector.addElement(substring.substring(0, index));
            substring = substring.substring(index + s.length());
        }
        vector.addElement(substring);
        final String[] array = new String[vector.size()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = vector.elementAt(i);
        }
        return array;
    }
    
    public static String w(final String s) {
        final char[] charArray = s.toCharArray();
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < charArray.length; ++i) {
            switch (charArray[i]) {
                case '\\': {
                    sb.append("\\\\");
                    break;
                }
                case '\'': {
                    sb.append("\\'");
                    break;
                }
                case '\"': {
                    sb.append("\\\"");
                    break;
                }
                default: {
                    sb.append(charArray[i]);
                    break;
                }
            }
        }
        return sb.toString();
    }
    
    public static String q(final String s, final char c, final char c2) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); ++i) {
            char char1;
            if ((char1 = s.charAt(i)) == ' ') {
                char1 = '_';
            }
            sb.append(char1);
        }
        return sb.toString();
    }
    
    public static String q(String s, final String s2, final String s3) {
        int index;
        while ((index = s.indexOf(s2)) >= 0) {
            final String substring = s.substring(0, index);
            s = s.substring(index + s2.length(), s.length());
            s = substring + s3 + s;
        }
        return s;
    }
    
    public static String q(final dW dw, String substring, int i) {
        final String[] q = q(substring, " ");
        int n = 0;
        int n2 = 0;
        for (int j = 0; j < q.length; ++j) {
            if (n == i || (n < i && n2 != 0)) {
                int n3 = 0;
                for (i = 0; i < j - 2; ++i) {
                    n3 += q[i].length();
                }
                return substring.substring(0, n3);
            }
            if (n > i) {
                n -= q(dw, q[j - 1]);
                n2 = 1;
            }
            if (n2 == 0) {
                n += q(dw, q[j]);
            }
        }
        if (substring.length() > i) {
            substring = substring.substring(0, i);
        }
        return substring;
    }
    
    public static int q(final dW dw, final String s) {
        for (int i = 0; i < dw.q(); ++i) {
            final cd cd = (cd)dw.q(i);
            final String string = " " + cd.e().trim();
            if ((" " + s).equalsIgnoreCase(" " + cd.getName())) {
                return string.length() - 1;
            }
        }
        return s.length();
    }
}
