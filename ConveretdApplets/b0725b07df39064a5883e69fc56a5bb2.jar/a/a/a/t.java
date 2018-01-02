// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

public class t
{
    private static int c;
    private static int d;
    private static int e;
    private static int f;
    protected f a;
    protected f b;
    private e[] g;
    
    public t() {
        this.g = null;
        this.a = new f(8, t.e);
        this.b = new f(8, t.f);
        this.g = new e[8];
        for (int i = 0; i < 8; ++i) {
            (this.g[i] = new e()).a(null, null);
        }
        (this = this).a.a();
        this.a.a();
    }
    
    public final void a(final int n, final l l, final l i) {
        this.g[n].a(l, i);
    }
    
    public final void h() {
        this.a.a();
    }
    
    public final void i() {
        this.a.a();
    }
    
    public final void a(final byte[] array) {
        if (array == null) {
            return;
        }
        for (int i = 0; i < array.length; ++i) {
            this.a.a((char)i, (char)array[i]);
        }
    }
    
    public final char b(final char c) {
        final char c2 = (char)(c >> 13 & '\u0007');
        final char c3 = (char)(c & '\u1fff');
        final l a;
        if ((a = this.g[c2].a) != null) {
            return a.b(c3);
        }
        return '\0';
    }
    
    public final void b(final char c, final char c2) {
        final char c3 = (char)(c >> 13 & '\u0007');
        final char c4 = (char)(c & '\u1fff');
        final l b;
        if ((b = this.g[c3].b) != null) {
            b.a(c4, c2);
        }
    }
    
    static {
        t.c = 65536;
        t.d = 65536;
        t.e = t.c / 8;
        t.f = t.d / 8;
    }
}
