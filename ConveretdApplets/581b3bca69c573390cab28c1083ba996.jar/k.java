// 
// Decompiled by Procyon v0.5.30
// 

class k extends f
{
    protected f d;
    
    void a(final f d) {
        this.d = d;
    }
    
    void a(final String s, final Object o) {
        try {
            if (s.equals("Viewpoint")) {
                ((g)this.d).a((float[])o);
            }
        }
        catch (NullPointerException ex) {}
        this.d.a(s, o);
    }
    
    Object a(final String s) {
        return this.d.a(s);
    }
    
    synchronized void a(final m m) {
        super.a(m);
        this.d.a(m);
    }
    
    void b(final m m) {
        super.b(m);
        this.d.b(m);
    }
    
    m b() {
        return super.a;
    }
    
    m c() {
        return super.b;
    }
    
    synchronized void a() {
        synchronized (super.a) {
            this.d.a();
        }
    }
    
    float a(final float n) {
        return ((g)this.d).b(n);
    }
    
    float d() {
        return ((g)this.d).a(10000.0f);
    }
    
    float e() {
        return ((g)this.d).a(0.0f);
    }
}
