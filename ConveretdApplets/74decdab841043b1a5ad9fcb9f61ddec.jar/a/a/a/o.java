// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import java.io.File;

public final class o
{
    private static String a;
    private static String b;
    
    public static String a() {
        return o.b;
    }
    
    private static String c() {
        if (o.a != null) {
            return o.a;
        }
        String s;
        if (!(s = System.getProperty("java.io.tmpdir")).endsWith(File.separator)) {
            s += File.separator;
        }
        final String string = s + "screencast-o-matic";
        final File file;
        if (!(file = new File(string)).exists()) {
            file.mkdir();
        }
        return o.a = string;
    }
    
    public static void a(String b) {
        o.b = b;
        String s;
        if (r.a()) {
            if (!(s = System.getProperty("java.io.tmpdir")).endsWith(File.separator)) {
                s += File.separator;
            }
        }
        else {
            String s2;
            if (!(s2 = System.getProperty("user.home")).endsWith(File.separator)) {
                s2 += File.separator;
            }
            if (r.b()) {
                s = s2 + "Library" + File.separator;
                final File file;
                if (!(file = new File(s)).exists() && !file.mkdir()) {
                    f.b("Failed to create dir: " + file);
                    return;
                }
            }
            else {
                s = s2 + ".";
            }
        }
        String a = s + b;
        File file2 = new File(a);
        if (r.a() && (!r.a() || !r.d().startsWith("5")) && !file2.exists()) {
            String s3;
            if (!(s3 = System.getProperty("user.home")).endsWith(File.separator)) {
                s3 += File.separator;
            }
            a = s3 + "AppData\\Local" + File.separator + (b.equals("som") ? "Screencast-O-Matic" : b);
            file2 = new File(a);
        }
        if (!file2.exists() && !file2.mkdir()) {
            f.b("Failed to create dir: " + file2);
            return;
        }
        b = (o.a = a);
        f.a(b);
        f.b("Setting tmp dir: " + b);
    }
    
    public static File a(final String s, final String s2) {
        return new File(c() + File.separator + s + s2);
    }
    
    public static String b() {
        return c() + File.separator;
    }
}
