// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.FileWriter;
import java.util.TimeZone;
import java.util.Calendar;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import com.hw.client.util.a;
import java.io.PrintWriter;
import java.io.Writer;

public class aF
{
    private static final String a;
    private static final long b;
    private static int c;
    private static Writer d;
    private static boolean e;
    private static Class f;
    
    public static void a(int n2, final String s, final Throwable t) {
        if ((aF.c & n) == 0x0) {
            return;
        }
        Class a;
        Class f;
        if (aF.f == null) {
            f = (aF.f = (a = a("VT_6_1_0_11.aF")));
        }
        else {
            a = (f = aF.f);
        }
        n2 = (int)f;
        synchronized (a) {
            if (!(aF.d instanceof PrintWriter)) {
                aF.d = new PrintWriter(aF.d);
            }
        }
        try {
            a();
            if (s != null) {
                aF.d.write(s);
            }
            t.printStackTrace((PrintWriter)aF.d);
            aF.d.flush();
        }
        catch (IOException ex) {
            com.hw.client.util.a.d("Failed to write to log: " + ex);
            com.hw.client.util.a.a("Failed log Entry was: " + s, ex);
        }
    }
    
    public static void a(final int n, final String s, final ByteArrayOutputStream byteArrayOutputStream) {
        if ((aF.c & 0x1) == 0x0) {
            return;
        }
        try {
            a();
            if (s != null) {
                aF.d.write(s);
            }
            aF.d.write(aF.a);
            aF.d.write(new String(byteArrayOutputStream.toByteArray(), "ISO_8859-1"));
            aF.d.flush();
        }
        catch (IOException ex) {
            com.hw.client.util.a.d("Failed to write to log: " + ex);
            com.hw.client.util.a.d("Failed log Entry was: " + s);
            com.hw.client.util.a.d(new String(byteArrayOutputStream.toByteArray()));
        }
    }
    
    private static final void a() {
        aF.d.write("{" + Thread.currentThread().getName() + "} ");
        final int n4;
        final int n3;
        final int n2;
        final int n = (n2 = (n3 = (n4 = (int)((System.currentTimeMillis() + aF.b) % 86400000L)) / 1000) / 60) / 60;
        final Writer d = aF.d;
        final StringBuffer append = new StringBuffer().append("[").append(b(n)).append(':').append(b(n2 - n * 60)).append(':').append(b(n3 - n2 * 60)).append('.');
        final int n5 = n4 - n3 * 1000;
        d.write(append.append(((n5 < 10) ? "00" : ((n5 < 100) ? "0" : "")) + n5).append("] ").toString());
    }
    
    private static final String b(final int n) {
        return ((n < 10) ? "0" : "") + n;
    }
    
    public static boolean a(final int n) {
        return (aF.c & n) != 0x0;
    }
    
    private static Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError().initCause(ex);
        }
    }
    
    static {
        a = System.getProperty("line.separator");
        aF.c = 0;
        aF.d = new OutputStreamWriter(System.err);
        aF.e = false;
        final Calendar instance = Calendar.getInstance();
        b = TimeZone.getDefault().getOffset(instance.get(0), instance.get(1), instance.get(2), instance.get(5), instance.get(7), instance.get(14));
        try {
            final String property;
            if ((property = System.getProperty("HTTPClient.log.file")) != null) {
                try {
                    final FileWriter d;
                    if ((d = new FileWriter(property)) != null) {
                        if (aF.e) {
                            try {
                                aF.d.close();
                            }
                            catch (IOException ex) {
                                com.hw.client.util.a.d("Error closing log stream: " + ex);
                            }
                        }
                        aF.d = d;
                        aF.e = true;
                    }
                }
                catch (IOException ex2) {
                    com.hw.client.util.a.d("failed to open file log stream `" + property + "': " + ex2);
                }
            }
        }
        catch (Exception ex3) {}
        try {
            aF.c = Integer.getInteger("HTTPClient.log.mask", 0);
        }
        catch (Exception ex4) {}
    }
}
