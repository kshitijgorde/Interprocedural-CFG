// 
// Decompiled by Procyon v0.5.30
// 

package a.b.m;

import java.awt.Point;
import a.b.o.c.h;

class c
{
    private h a;
    private a.b.a.a.c b;
    private int c;
    private int d;
    private long e;
    private long f;
    private boolean g;
    
    public c(final a.b.a.a.c b, final h a, final long f) {
        this.b = b;
        this.a = a;
        if (f <= 0L && this.b != null) {
            this.f = this.b.a();
        }
        else {
            this.f = f;
        }
        this.e = -1L;
        this.g = true;
    }
    
    public h a() {
        return this.a;
    }
    
    public void a(final long e) {
        this.e = e;
        this.g = false;
    }
    
    public void b(final long n) {
        if (this.e > 0L && !this.g) {
            this.g = (n - this.e >= this.f);
        }
    }
    
    public boolean b() {
        return this.g;
    }
    
    public void a(final Point point) {
        this.c = point.x;
        this.d = point.y;
        if (this.a != null) {
            this.a.setLocation(this.c, this.d);
        }
    }
}
