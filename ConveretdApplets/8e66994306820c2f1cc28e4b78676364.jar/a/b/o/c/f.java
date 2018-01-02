// 
// Decompiled by Procyon v0.5.30
// 

package a.b.o.c;

import a.b.b.c;
import java.awt.Cursor;
import java.awt.Color;

public class f extends e implements i
{
    private static final Color o;
    private static final Cursor p;
    private a.b.b.e q;
    private a.b.b.e r;
    private a.b.b.e s;
    
    public f() {
        this(new a.b.o.a.b.e(), new a.b.o.a.b.e(), null, f.p);
    }
    
    public f(final a.b.o.a.b.e e, final String s) {
        this(e, new a.b.o.a.b.e(e.a(), e.b(), f.o), s, f.p);
    }
    
    public f(final a.b.o.a.b.e e, final a.b.o.a.b.e e2, final String s) {
        this(e, e2, s, f.p);
    }
    
    public f(final a.b.o.a.b.e e, final a.b.o.a.b.e e2, final String s, final Cursor cursor) {
        super(e, e2, true, false);
        this.a(s);
        this.b(cursor);
    }
    
    public void a(final String s) {
        if (this.q != null) {
            this.b(this.q);
        }
        if (this.r != null) {
            this.d(this.r);
        }
        if (this.s != null) {
            this.f(this.s);
        }
        if (s != null) {
            this.q = new a.b.b.e(this, 357, s);
            this.r = new a.b.b.e(this, 358, s);
            this.s = new a.b.b.e(this, 359, s);
            this.a(this.q);
            this.c(this.r);
            this.e(this.s);
        }
    }
    
    public void b(final Cursor cursor) {
        if (cursor != null) {
            this.a(cursor);
        }
        else {
            this.a(f.p);
        }
    }
    
    public boolean equals(final Object o) {
        if (o == null) {
            return false;
        }
        try {
            return super.equals(o);
        }
        catch (ClassCastException ex) {
            return false;
        }
    }
    
    static {
        o = new Color(220, 10, 20);
        p = new Cursor(12);
    }
}
