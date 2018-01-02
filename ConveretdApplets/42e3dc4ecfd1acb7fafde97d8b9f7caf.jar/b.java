import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.PrintStream;

// 
// Decompiled by Procyon v0.5.30
// 

public class b
{
    private static boolean a;
    private static int b;
    private static PrintStream c;
    public static String d;
    private static final SimpleDateFormat e;
    
    public static String a(final long n) {
        b.e.applyPattern("dd MMMM yyyy 'at' hh:mm:ss a");
        return b.e.format(new Date(n));
    }
    
    public static void a(final boolean a, final int b, final String s, final String d) {
        b.a = a;
        b.b = b;
        if (s.equalsIgnoreCase("TERMINAL")) {
            b.c = System.out;
        }
        else {
            try {
                b.c = new PrintStream(new FileOutputStream(d, true));
                b.d = d;
            }
            catch (Exception ex) {
                ex.printStackTrace(b.c = System.out);
            }
        }
    }
    
    public static void a(final String s, final int n) {
        if (b.a && b.b <= n) {
            b.c.println(s);
        }
    }
    
    public static void a(final Exception ex, final int n) {
        if (b.a && b.b <= n) {
            b.c.println(a(System.currentTimeMillis()) + " : ");
            ex.printStackTrace(b.c);
        }
    }
    
    static {
        b.a = true;
        b.b = 0;
        b.c = System.out;
        b.d = "";
        e = new SimpleDateFormat();
    }
}
