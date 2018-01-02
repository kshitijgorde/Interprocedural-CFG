// 
// Decompiled by Procyon v0.5.30
// 

package a.b.o.c;

import a.b.b.e;
import java.awt.Cursor;

public class c extends b implements i
{
    private static final Cursor m;
    private a.b.b.e n;
    private a.b.b.e o;
    private a.b.b.e p;
    
    public c() {
        this(new a.b.o.a.b.c(), new a.b.o.a.b.c(), null, c.m);
    }
    
    public c(final a.b.o.a.b.c c, final String s) {
        this(c, c, s, c.m);
    }
    
    public c(final a.b.o.a.b.c c, final a.b.o.a.b.c c2, final String s) {
        this(c, c2, s, c.m);
    }
    
    public c(final a.b.o.a.b.c c, final a.b.o.a.b.c c2, final String s, final Cursor cursor) {
        super(c, c2, true, false);
        this.a(s);
        this.b(cursor);
    }
    
    public void a(final String s) {
        this.b(this.n);
        this.d(this.o);
        this.f(this.p);
        if (s != null) {
            this.n = new a.b.b.e(this, 357, s);
            this.o = new a.b.b.e(this, 358, s);
            this.p = new a.b.b.e(this, 359, s);
            this.a(this.n);
            this.c(this.o);
            this.e(this.p);
        }
    }
    
    public void b(final Cursor cursor) {
        if (cursor != null) {
            this.a(cursor);
        }
        else {
            this.a(c.m);
        }
    }
    
    static {
        m = new Cursor(12);
    }
}
