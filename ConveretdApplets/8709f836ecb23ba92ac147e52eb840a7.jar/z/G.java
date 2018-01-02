// 
// Decompiled by Procyon v0.5.30
// 

package z;

import java.io.InputStream;
import java.net.URL;
import nanoxml.XMLElement;

public class G
{
    private static XMLElement a;
    private static A b;
    private static az c;
    private static String d;
    private static URL e;
    private static /* synthetic */ boolean f;
    
    public static void a(final InputStream inputStream, final InputStream inputStream2, final InputStream inputStream3) {
        if (G.a != null) {
            return;
        }
        G.a = aa.a(inputStream);
        G.b = new A(inputStream2);
        G.c = new az(aa.a(ap.a(ap.a(inputStream3))));
        System.out.println(String.format("Built string table: %d entries x %d languages", G.c.b(), G.c.a()));
    }
    
    public static void a(final String d) {
        G.d = d;
        System.out.println("Set language == " + d);
    }
    
    public static String a() {
        return G.d;
    }
    
    public static bg b(final String s) {
        if (!G.f && G.a == null) {
            throw new AssertionError();
        }
        final XMLElement c;
        if ((c = aa.c(G.a, s)) == null) {
            throw new RuntimeException("Configuration is missing item: " + s);
        }
        return new bg(c);
    }
    
    public static String c(final String s) {
        if (!G.f && G.d == null) {
            throw new AssertionError();
        }
        return G.c.a(s, G.d);
    }
    
    public static String a(final String s, final String s2) {
        if (!G.f && G.b == null) {
            throw new AssertionError();
        }
        return G.b.a(s, s2);
    }
    
    public static void a(final URL e) {
        if (!G.f && e == null) {
            throw new AssertionError();
        }
        G.e = e;
    }
    
    public static URL b() {
        return G.e;
    }
    
    public static String c() {
        return G.d.toLowerCase();
    }
    
    static {
        G.f = !G.class.desiredAssertionStatus();
    }
}
