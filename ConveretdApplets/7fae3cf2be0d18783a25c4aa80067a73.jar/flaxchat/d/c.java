// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.d;

import flaxchat.a.q;
import java.util.Hashtable;

public class c
{
    private static Hashtable a;
    private static String z;
    
    public static void a() {
        try {
            c.a = b.a(c.z);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static String a(final String s) {
        if (s == null) {
            return null;
        }
        final String s2 = c.a.get(s);
        if (s2 == null) {
            return s;
        }
        return s2;
    }
    
    public static String b(final String s) {
        return a(s, null);
    }
    
    public static String a(final String s, final Object[] array) {
        if (s == null) {
            return null;
        }
        final String s2 = c.a.get(s);
        if (s2 == null) {
            return null;
        }
        if (array == null) {
            return s2;
        }
        String s3 = q.a(s2, array);
        if (s3.startsWith("%")) {
            s3 = String.valueOf('\u0003') + s3.substring(1);
        }
        return s3;
    }
    
    static {
        c.z = z(z("\\j\u000b#\u001fQl\u0000"));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'j';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '0';
                    break;
                }
                case 1: {
                    c2 = '\u000b';
                    break;
                }
                case 2: {
                    c2 = 'e';
                    break;
                }
                case 3: {
                    c2 = 'D';
                    break;
                }
                default: {
                    c2 = 'j';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}