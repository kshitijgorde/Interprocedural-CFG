// 
// Decompiled by Procyon v0.5.30
// 

package a.b.g;

import org.a.c.f;
import a.b.n.c;
import org.a.c.g;
import a.b.e.m;
import a.b.e.n;
import a.b.o.a.a.d;

public class b
{
    private static b a;
    private static d b;
    private static a.b.n.d c;
    private static n d;
    private static String z;
    
    public static b a() {
        if (a.b.g.b.a == null) {
            a(new b());
        }
        return a.b.g.b.a;
    }
    
    public static void a(final b a) {
        a.b.g.b.a = a;
    }
    
    public void a(final d b) {
        b.b = b;
    }
    
    public void a(final a.b.n.d c) {
        a.b.g.b.c = c;
    }
    
    public void a(final n d) {
        a.b.g.b.d = d;
    }
    
    public d b() throws a {
        if (a.b.g.b.b == null) {
            throw new a(a.b.g.b.z);
        }
        return a.b.g.b.b;
    }
    
    public n c() {
        if (a.b.g.b.d == null) {
            return new m(null);
        }
        return a.b.g.b.d;
    }
    
    public a.b.n.d d() {
        if (a.b.g.b.c == null) {
            return new c(new g());
        }
        return a.b.g.b.c;
    }
    
    static {
        final char[] charArray = "@U%W\u0016qL5\u0012\u0017`X$W\u0002u^4\u0018\u0016m\u001d(\u0016\u00174S/\u0003DvX%\u0019DpX&\u001e\nqYn".toCharArray();
        final int i = charArray.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = charArray[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '\u0014';
                    break;
                }
                case 1: {
                    c2 = '=';
                    break;
                }
                case 2: {
                    c2 = '@';
                    break;
                }
                case 3: {
                    c2 = 'w';
                    break;
                }
                default: {
                    c2 = 'd';
                    break;
                }
            }
            charArray[n2] = (char)(c ^ c2);
        }
        a.b.g.b.z = new String(charArray).intern();
    }
}
