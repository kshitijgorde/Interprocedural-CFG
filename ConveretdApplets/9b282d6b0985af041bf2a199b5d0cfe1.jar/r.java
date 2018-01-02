// 
// Decompiled by Procyon v0.5.30
// 

class r extends j
{
    protected j d;
    
    void a(final String s, final Object o) {
        try {
            if (s.equals("Viewpoint")) {
                ((k)this.d).a((float[])o);
            }
        }
        catch (NullPointerException ex) {}
        this.d.a(s, o);
    }
    
    Object a(final String s) {
        return this.d.a(s);
    }
    
    void a(final t t) {
        super.a(t);
        this.d.a(t);
    }
    
    t b() {
        return super.b;
    }
    
    float c() {
        return ((k)this.d).b(0.0f);
    }
    
    float a(final float n) {
        return ((k)this.d).a(n);
    }
    
    void a() {
        synchronized (super.a) {
            this.d.a();
        }
        // monitorexit(super.a)
    }
    
    float d() {
        return ((k)this.d).b(10000.0f);
    }
    
    void a(final j d) {
        this.d = d;
    }
    
    void b(final t t) {
        super.b(t);
        this.d.b(t);
    }
    
    t e() {
        return super.a;
    }
}
