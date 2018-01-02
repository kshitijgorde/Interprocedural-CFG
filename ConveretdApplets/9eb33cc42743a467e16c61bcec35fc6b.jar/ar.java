import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public class ar
{
    public static Hashtable hf;
    
    public static ar f1(final String s) {
        final ar ar = ar.hf.get(s);
        return ar.hf.get(s);
    }
    
    public static void f0(final String s, final ar ar) {
        ar.hf.put(s, ar);
    }
    
    public static void f_(final b5 b5) {
        final Enumeration<ar> elements = ar.hf.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().fz(b5);
        }
    }
    
    public void fz(final b5 b5) {
    }
    
    public bm fy(final String s, final int n, final String s2, final int n2, final b7 b7) throws IOException {
        return new bm(s, n, s2, n2, b7);
    }
    
    public final void fx(final int n, final String s, final int n2, final b7 b7) {
    }
    
    static {
        ar.hf = new Hashtable();
        f0("general", new ar());
        try {
            f0("ftp", new bs());
        }
        catch (Throwable t) {
            System.out.println("FTP plugin not found, disabled");
        }
    }
}
