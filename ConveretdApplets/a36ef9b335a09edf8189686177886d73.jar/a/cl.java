// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;
import java.awt.Color;
import java.awt.Font;

public class cl
{
    public Font q;
    public Font w;
    public Font e;
    public Font r;
    public Font t;
    
    public cl(final Font q) {
        this.q = q;
        this.w = new Font(this.q.getFamily(), 0, this.q.getSize());
        this.e = new Font(this.q.getFamily(), 2, this.q.getSize());
        this.r = new Font(this.q.getFamily(), 1, this.q.getSize());
        this.t = new Font(this.q.getFamily(), 3, this.q.getSize());
    }
    
    public static String q(final char c) {
        return "[" + c + ']';
    }
    
    private static String w(final char c) {
        return "[/" + c + ']';
    }
    
    public static String q(final String s, final int n) {
        switch (n) {
            case 0: {
                return s;
            }
            case 2: {
                return q('i') + " " + s + " " + w('i');
            }
            case 1: {
                return q('b') + " " + s + " " + w('b');
            }
            case 3: {
                return q('i') + q('b') + " " + s + " " + w('b') + w('i');
            }
            default: {
                return s;
            }
        }
    }
    
    public static String q(String s, final char c) {
        if (s.indexOf(q(c)) >= 0) {
            final int index = s.indexOf(q(c));
            final int index2;
            if ((index2 = (s = s.substring(0, index) + s.substring(index + q(c).length(), s.length())).indexOf(w(c))) >= 0) {
                s = s.substring(0, index2) + s.substring(index2 + w(c).length(), s.length());
            }
        }
        return s.trim();
    }
    
    public cl() {
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
    
    public static String q(final cq cq, String substring, int i) {
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
                n -= q(cq, q[j - 1]);
                n2 = 1;
            }
            if (n2 == 0) {
                n += q(cq, q[j]);
            }
        }
        if (substring.length() > i) {
            substring = substring.substring(0, i);
        }
        return substring;
    }
    
    public static int q(final cq cq, final String s) {
        for (int i = 0; i < cq.q(); ++i) {
            final bc bc = (bc)cq.q(i);
            final String string = " " + bc.e().trim();
            if ((" " + s).equalsIgnoreCase(" " + bc.getName())) {
                return string.length() - 1;
            }
        }
        return s.length();
    }
}
