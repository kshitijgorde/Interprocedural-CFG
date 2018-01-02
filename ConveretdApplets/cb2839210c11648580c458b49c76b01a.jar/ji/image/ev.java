// 
// Decompiled by Procyon v0.5.30
// 

package ji.image;

public final class ev
{
    public Object a;
    public int b;
    public int c;
    public double d;
    public double e;
    public int f;
    public int g;
    
    public ev() {
        this.a = null;
        this.b = 0;
        this.c = 0;
        this.d = 0.0;
        this.e = 0.0;
        this.f = 0;
        this.g = 0;
    }
    
    public ev a() {
        final ev ev = new ev();
        ev.a(this);
        return ev;
    }
    
    public void a(final ev ev) {
        this.a = ev.a;
        this.b = ev.b;
        this.c = ev.c;
        this.d = ev.d;
        this.e = ev.e;
        this.f = ev.f;
        this.g = ev.g;
    }
    
    public final void b() {
        this.c = 1;
    }
    
    public final boolean c() {
        return this.c == 1;
    }
    
    public final boolean d() {
        return this.c == 2;
    }
}
