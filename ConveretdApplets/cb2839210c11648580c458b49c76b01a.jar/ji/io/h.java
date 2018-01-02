// 
// Decompiled by Procyon v0.5.30
// 

package ji.io;

import ji.util.i;
import ji.util.d;
import java.io.PrintStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import ji.document.ad;
import java.io.PrintWriter;

public class h
{
    private static long a;
    private static long b;
    private static long c;
    private static long d;
    public static boolean e;
    private static PrintWriter f;
    private static boolean g;
    
    public static String a(final int n, final boolean b) {
        String concat = null;
        switch (n) {
            case 0: {
                concat = "LOW";
                break;
            }
            case 1: {
                concat = "MEDIUM";
                break;
            }
            case 2: {
                concat = "HIGH";
                break;
            }
            case 3: {
                concat = "CRITICAL";
                break;
            }
            default: {
                concat = "";
                break;
            }
        }
        if (b && concat.length() > 0) {
            concat = String.valueOf(String.valueOf(concat)).concat(" : ");
        }
        return concat;
    }
    
    public static void a(final ad ad, final String s) {
        try {
            final q a = q.a(ad, s);
            if (a.f() != null) {
                final String value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(a.f()))).append(File.separator).append("console.txt")));
                System.out.println("log: ".concat(String.valueOf(String.valueOf(value))));
                final File file = new File(value);
                try {
                    file.delete();
                }
                catch (Exception ex2) {}
                final PrintStream printStream = new PrintStream(new BufferedOutputStream(new FileOutputStream(file)), true);
                System.setErr(printStream);
                System.setOut(printStream);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static final void a(final String s, final PrintWriter f) {
        if (f != null) {
            h.g = true;
            h.f = f;
        }
        else {
            h.g = false;
            h.f = null;
        }
    }
    
    public static final void a(final String s, final String s2, final int n) {
        final String c = c(s, s2, n);
        if (!h.g) {
            System.out.println(c);
            System.out.flush();
            if (!ji.util.d.ax(s) || !ji.util.d.dp()) {
                ji.util.d.ex();
            }
        }
        else if (h.f != null) {
            h.f.println(c);
            h.f.flush();
        }
    }
    
    public static final void a(final String s, final String s2) {
        if (ji.util.d.cs()) {
            d(s, s2);
        }
    }
    
    public static final void b(final String s, final String s2) {
        if (i.c(6)) {
            d(s, s2);
        }
    }
    
    public static final void c(final String s, final String s2) {
        if (i.c(5)) {
            d(s, s2);
        }
    }
    
    public static final void d(final String s, final String s2) {
        a(s, s2, -1);
    }
    
    public static final void e(final String s, final String s2) {
        a(s, s2, -1);
    }
    
    public static final void a(final String s, final Throwable t) {
        if (!h.g) {
            t.printStackTrace();
        }
        else if (h.f != null) {
            t.printStackTrace(h.f);
        }
    }
    
    public static final void f(final String s, final String s2) {
        if (ji.util.d.cy()) {
            a(s, s2, -1);
        }
    }
    
    public static final void b(final String s, final String s2, final int n) {
        if (ji.util.d.cy()) {
            a(s, s2, n);
        }
    }
    
    public static final void a(final String s) {
        h.c = 0L;
        h.d = 0L;
        h.a = 0L;
        h.b = 0L;
        if (ji.util.d.dr() || ji.util.d.cy()) {
            a(s, "Start of applet session: Log times cleared.", -1);
        }
    }
    
    private static final String c(final String s, final String s2, final int n) {
        if (h.a <= 0) {
            h.a = System.currentTimeMillis();
            h.b = System.currentTimeMillis();
        }
        final String concat = "000000000".concat(String.valueOf(String.valueOf(System.currentTimeMillis() - h.b)));
        final String concat2 = "000000000".concat(String.valueOf(String.valueOf(System.currentTimeMillis() - h.a)));
        h.a = System.currentTimeMillis();
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("> ").append(Thread.currentThread().getName()).append(" ").append(ji.util.d.cd(false)).append(" (").append(concat.substring(concat.length() - 9, concat.length())).append("/").append(concat2.substring(concat2.length() - 9, concat2.length())).append("): ").append(a(n, true)).append(s2)));
    }
    
    static {
        h.a = 0L;
        h.b = 0L;
        h.c = 0L;
        h.d = 0L;
        h.e = false;
        h.f = null;
        h.g = false;
    }
}
