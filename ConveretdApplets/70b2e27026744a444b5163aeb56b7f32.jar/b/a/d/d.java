// 
// Decompiled by Procyon v0.5.30
// 

package b.a.d;

import java.io.IOException;
import java.io.BufferedReader;
import java.awt.Window;
import java.awt.Component;
import java.util.ResourceBundle;
import java.lang.reflect.Array;

public class d
{
    public static final String a;
    public static final String b;
    public static final String c;
    public static final String d;
    private static e e;
    private static f f;
    
    public static boolean a(final Object o, final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
    
    public static boolean a(final String s) {
        return s == null || s.length() == 0;
    }
    
    public static int a(final Object o) {
        return a(o, 0, 10);
    }
    
    public static int a(final Object o, final int n) {
        return a(o, n, 10);
    }
    
    public static int a(final Object o, final int n, final int n2) {
        if (o instanceof Number) {
            return ((Number)o).intValue();
        }
        if (o instanceof byte[]) {
            final byte[] array = (byte[])o;
            int n3 = 0;
            for (int i = 0; i < array.length; ++i) {
                n3 = (n3 << 8 | (0xFF & array[i]));
            }
            return n3;
        }
        if (o == null) {
            return n;
        }
        String s = o.toString().trim();
        if (s.length() == 0) {
            return n;
        }
        if (s.charAt(0) == '+') {
            s = s.substring(1);
        }
        try {
            return Integer.parseInt(s, n2);
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public static long a(final Object o, final long n) {
        return a(o, n, 10);
    }
    
    public static long a(final Object o, final long n, final int n2) {
        if (o instanceof Number) {
            return ((Number)o).longValue();
        }
        if (o instanceof byte[]) {
            final byte[] array = (byte[])o;
            long n3 = 0L;
            for (int i = 0; i < array.length; ++i) {
                n3 = (n3 << 8 | (0xFF & array[i]));
            }
            return n3;
        }
        if (o == null) {
            return n;
        }
        String s = o.toString().trim();
        if (s.length() == 0) {
            return n;
        }
        if (s.charAt(0) == '+') {
            s = s.substring(1);
        }
        try {
            return Long.parseLong(s, n2);
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public static boolean b(final Object o) {
        return a(o, false);
    }
    
    public static boolean a(final Object o, final boolean b) {
        if (o instanceof Boolean) {
            return (boolean)o;
        }
        if (o == null) {
            return b;
        }
        final String upperCase = o.toString().trim().toUpperCase();
        return "TRUE".equals(upperCase) || "1".equals(upperCase) || "YES".equals(upperCase) || "Y".equals(upperCase) || "ON".equals(upperCase) || (!"FALSE".equals(upperCase) && !"0".equals(upperCase) && !"NO".equals(upperCase) && !"N".equals(upperCase) && !"OFF".equals(upperCase) && b);
    }
    
    public static int[] a(final int[] array, final int n) {
        return (int[])b((Object)array, n);
    }
    
    public static Object b(final Object o, final int n) {
        if (o == null) {
            return null;
        }
        if (!o.getClass().isArray()) {
            throw new ArrayStoreException();
        }
        final int length = Array.getLength(o);
        if (length == n) {
            return o;
        }
        final Object instance = Array.newInstance(o.getClass().getComponentType(), n);
        System.arraycopy(o, 0, instance, 0, Math.min(length, n));
        return instance;
    }
    
    public static String a(final long n, final int n2) {
        return b.a.d.c.a(b.a.d.c.a(Long.toHexString(n), '0', n2), n2).toUpperCase();
    }
    
    public static boolean a(final char c) {
        return '0' <= c && c <= '9';
    }
    
    public static int a(String s, String s2) {
        if (s == null || s2 == null || s.equals(s2)) {
            return 0;
        }
        s = s.replace('_', '.');
        s = s.replace('-', '.');
        s2 = s2.replace('-', '.');
        s2 = s2.replace('_', '.');
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) != '.' && !a(s.charAt(i))) {
                s = s.substring(0, i);
                break;
            }
        }
        for (int j = 0; j < s2.length(); ++j) {
            if (s2.charAt(j) != '.' && !a(s2.charAt(j))) {
                s2 = s2.substring(0, j);
                break;
            }
        }
        while (s.length() > 0 && s2.length() > 0) {
            final int index = s.indexOf(46);
            int n;
            if (index < 0) {
                n = a((Object)s);
                s = "";
            }
            else {
                n = a((Object)s.substring(0, index));
                s = s.substring(index + 1);
            }
            final int index2 = s2.indexOf(46);
            int n2;
            if (index2 < 0) {
                n2 = a((Object)s2);
                s2 = "";
            }
            else {
                n2 = a((Object)s2.substring(0, index2));
                s2 = s2.substring(index2 + 1);
            }
            if (n < n2) {
                return -1;
            }
            if (n > n2) {
                return 1;
            }
        }
        if (s.length() == 0 && s2.length() == 0) {
            return 0;
        }
        if (s.length() == 0) {
            return -1;
        }
        return 1;
    }
    
    public static int b(final String s) {
        return a(b.a.d.d.c, s);
    }
    
    public static int c(final String s) {
        return a(b.a.d.d.b, s);
    }
    
    public static int b(final String s, final String s2) {
        if (a(s, (Object)s2)) {
            return 0;
        }
        if (s == null) {
            return -1;
        }
        if (s2 == null) {
            return 1;
        }
        final String[] a = b.a.d.c.a(s, '.');
        final String[] a2 = b.a.d.c.a(s, '.');
        final long n = Long.MIN_VALUE;
        for (int i = 0; i < Math.min(a.length, a2.length); ++i) {
            final String s3 = a[i];
            final long a3 = a(s3, n);
            final String s4 = a2[i];
            final long a4 = a(s4, n);
            if (a3 != n && a4 != n) {
                if (a3 < a4) {
                    return -1;
                }
                if (a3 > a4) {
                    return 1;
                }
            }
            else {
                final int compareToIgnoreCase = s3.compareToIgnoreCase(s4);
                if (compareToIgnoreCase != 0) {
                    return compareToIgnoreCase;
                }
            }
        }
        return a.length - a2.length;
    }
    
    public static boolean a() {
        return b.a.d.d.a != null && b.a.d.d.a.indexOf("Mac ") >= 0;
    }
    
    public static boolean b() {
        return b.a.d.d.a != null && b.a.d.d.a.indexOf("Windows ") >= 0;
    }
    
    public static String a(final ResourceBundle resourceBundle) {
        final String name = resourceBundle.getClass().getName();
        int lastIndex = name.lastIndexOf(46);
        if (lastIndex < 0) {
            lastIndex = 0;
        }
        final int index = name.indexOf(95, lastIndex);
        if (index < 0) {
            return "";
        }
        return name.substring(index);
    }
    
    public static boolean a(final long n) {
        try {
            Thread.sleep(n);
        }
        catch (InterruptedException ex) {
            return false;
        }
        return true;
    }
    
    public static boolean a(final Thread thread, final long n) {
        try {
            thread.join(n);
        }
        catch (InterruptedException ex) {
            return false;
        }
        return true;
    }
    
    public static void a(final long n, final Runnable runnable) {
        a(n, "Util.invokeAfterDelay", runnable);
    }
    
    public static void a(final long n, final String s, final Runnable runnable) {
        new h(s, n, runnable).start();
    }
    
    public static Window a(Component parent) {
        while (parent != null) {
            if (parent instanceof Window) {
                return (Window)parent;
            }
            parent = parent.getParent();
        }
        return null;
    }
    
    public static int b(final int[] array, final int n) {
        return a(array, n, 0);
    }
    
    public static int a(final int[] array, final int n, final int n2) {
        if (array == null) {
            return -1;
        }
        int n3 = -1;
        for (int i = n2; i < array.length; ++i) {
            if (array[i] == n) {
                n3 = i;
                break;
            }
        }
        return n3;
    }
    
    public static String a(final BufferedReader bufferedReader) throws IOException {
        String s;
        do {
            s = bufferedReader.readLine();
            if (s != null) {
                final int index = s.indexOf("#");
                if (index >= 0) {
                    s = s.substring(0, index);
                }
                s = b.a.d.c.a(s);
                if (s.length() > 0) {
                    return s;
                }
                continue;
            }
        } while (s != null);
        return null;
    }
    
    static {
        a = System.getProperty("os.name");
        b = System.getProperty("os.version");
        c = System.getProperty("java.version");
        d = System.getProperty("java.vm.version");
        b.a.d.d.e = new e(null);
        b.a.d.d.f = new f(null);
    }
}
