// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

import java.util.zip.ZipEntry;
import java.io.FileInputStream;
import java.util.zip.ZipOutputStream;
import java.io.FilenameFilter;
import java.io.OutputStream;
import java.util.logging.Handler;
import java.util.logging.FileHandler;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Formatter;
import java.util.logging.Logger;

public final class f
{
    private static String a;
    private static Logger b;
    private static String c;
    private static Formatter d;
    
    public static void a(String property) {
        if (f.b != null) {
            return;
        }
        try {
            f.c = property;
            if ((f.b = Logger.getLogger("log")).getHandlers().length == 0) {
                f.b.setUseParentHandlers(false);
                property = System.getProperty("som.log.level", "DEBUG");
                Level level = Level.ALL;
                if (property.equalsIgnoreCase("info")) {
                    level = Level.INFO;
                }
                else if (property.equalsIgnoreCase("warning")) {
                    level = Level.WARNING;
                }
                f.b.setLevel(level);
                a();
                f.b.info("---------------------------");
                f.b.info("-----   STARTING UP   -----");
                f.b.info("---------------------------");
                f.b.info("Java version: " + System.getProperty("java.version"));
            }
        }
        catch (IOException ex) {
            a("", ex);
        }
    }
    
    private static void a() {
        if (f.b.getHandlers().length > 0) {
            f.b.removeHandler(f.b.getHandlers()[0]);
        }
        final FileHandler fileHandler;
        (fileHandler = new FileHandler(f.c + File.separator + f.a + "-%g.log", 1048576, 2, true)).setFormatter(f.d);
        f.b.addHandler(fileHandler);
    }
    
    public static void a(final OutputStream outputStream) {
        String[] list;
        String[] array;
        for (int length = (array = (list = new File(f.c).list(new e()))).length, i = 0; i < length; ++i) {
            b("Found log to zip: " + array[i]);
        }
        b("-------------------------------");
        b("-----   CLOSING FOR ZIP   -----");
        b("-------------------------------");
        if (f.b.getHandlers().length > 0) {
            f.b.getHandlers()[0].close();
        }
        try {
            final byte[] array2 = new byte[4096];
            final ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
            try {
                String[] array3;
                for (int length2 = (array3 = list).length, j = 0; j < length2; ++j) {
                    final String s = array3[j];
                    final FileInputStream fileInputStream = new FileInputStream(f.c + File.separator + s);
                    zipOutputStream.putNextEntry(new ZipEntry(s));
                    int read;
                    while ((read = fileInputStream.read(array2)) != -1) {
                        zipOutputStream.write(array2, 0, read);
                    }
                    zipOutputStream.closeEntry();
                }
            }
            finally {
                zipOutputStream.close();
            }
        }
        finally {
            a();
            b("----------------------------------");
            b("-----   STARTING AFTER ZIP   -----");
            b("----------------------------------");
        }
    }
    
    public static void a(final Object o) {
        if (a(o, null)) {
            f.b.finest((o != null) ? o.toString() : "null");
        }
    }
    
    public static void b(final Object o) {
        if (a(o, null)) {
            f.b.info((o != null) ? o.toString() : "null");
        }
    }
    
    public static void c(final Object o) {
        if (a(o, null)) {
            f.b.warning((o != null) ? o.toString() : "null");
        }
    }
    
    public static void a(final Throwable t) {
        a("", t);
    }
    
    public static void a(final String s, final Throwable t) {
        if (a((Object)s, t)) {
            f.b.log(Level.WARNING, s, t);
        }
    }
    
    private static boolean a(final Object o, final Throwable t) {
        if (f.b != null) {
            return true;
        }
        if (o != null && o.toString().length() > 0) {
            System.out.println(o);
        }
        if (t != null) {
            t.printStackTrace();
        }
        return false;
    }
    
    static {
        f.a = "app";
        f.b = null;
        f.c = null;
        f.d = new d();
    }
}
