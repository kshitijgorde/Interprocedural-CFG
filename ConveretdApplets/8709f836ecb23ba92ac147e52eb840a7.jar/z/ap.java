// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.util.Iterator;
import java.util.Map;
import java.io.InputStream;
import java.util.HashMap;

public class ap
{
    private static final HashMap a;
    private static /* synthetic */ boolean b;
    
    public static String a(final String s) {
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) < '\u0080') {
                if (i > 0) {
                    System.out.println("eatUpperAscii: ate " + i + " chars *burp*");
                }
                return s.substring(i);
            }
        }
        return "";
    }
    
    public static boolean b(final String s) {
        return s != null && s.length() >= 2 && s.charAt(0) == '\"' && s.charAt(s.length() - 1) == '\"';
    }
    
    public static void c(final String s) {
        if (!ap.b && !b(s)) {
            throw new AssertionError();
        }
        System.out.println(s + " -> " + s.substring(1, s.length() - 1));
    }
    
    public static String a(final InputStream inputStream) {
        return a(inputStream, "UTF8");
    }
    
    public static String a(final InputStream inputStream, final String s) {
        final StringBuffer sb = new StringBuffer();
        final byte[] array = new byte[4096];
        int read;
        while ((read = inputStream.read(array)) != -1) {
            sb.append(new String(array, 0, read, s));
        }
        return sb.toString();
    }
    
    public static String d(String replaceAll) {
        for (final Map.Entry<String, V> entry : ap.a.entrySet()) {
            replaceAll = replaceAll.replaceAll(entry.getKey(), (String)entry.getValue());
        }
        return replaceAll;
    }
    
    static {
        ap.b = !ap.class.desiredAssertionStatus();
        final HashMap<String, String> a2;
        (a2 = new HashMap<String, String>()).put("\\xc3\\xa0", String.valueOf('\u00e0'));
        a2.put("\\xc3\\xa1", String.valueOf('\u00e1'));
        a2.put("\\xc3\\xa2", String.valueOf('\u00e2'));
        a2.put("\\xc3\\xa6", String.valueOf('\u00e6'));
        a2.put("\\xc3\\xa7", String.valueOf('\u00e7'));
        a2.put("\\xc3\\xa8", String.valueOf('\u00e8'));
        a2.put("\\xc3\\xa9", String.valueOf('\u00e9'));
        a2.put("\\xc3\\xaa", String.valueOf('\u00ea'));
        a2.put("\\xc3\\xae", String.valueOf('\u00ee'));
        a2.put("\\xc3\\xaf", String.valueOf('\u00ef'));
        a2.put("\\xc3\\xb4", String.valueOf('\u00f4'));
        a2.put("\\xc3\\xb9", String.valueOf('\u00f9'));
        a2.put("\\xc3\\xbb", String.valueOf('\u00fb'));
        a2.put("\\xc3\\xbc", String.valueOf('\u00fc'));
        a = a2;
    }
}
