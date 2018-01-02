// 
// Decompiled by Procyon v0.5.30
// 

class m extends h
{
    protected h d;
    
    void a(final h d) {
        this.d = d;
    }
    
    void a(final String s, final Object o) {
        try {
            if (s.equals("ptrz")) {
                ((i)this.d).a((float[])o);
            }
        }
        catch (NullPointerException ex) {}
        this.d.a(s, o);
    }
    
    Object getProperty(final String s) {
        return this.d.getProperty(s);
    }
    
    synchronized void a(final o o) {
        super.a(o);
        this.d.a(o);
    }
    
    void b(final o o) {
        super.b(o);
        this.d.b(o);
    }
    
    o b() {
        return this.a;
    }
    
    o c() {
        return this.b;
    }
    
    synchronized void a() {
        synchronized (this.a) {
            this.d.a();
        }
    }
    
    float a(final float n) {
        return ((i)this.d).a(n);
    }
    
    float b(final float n) {
        return ((i)this.d).b(n);
    }
    
    float c(final float n) {
        return ((i)this.d).c(n);
    }
    
    float d(final float n) {
        return ((i)this.d).d(n);
    }
    
    float d() {
        final float[] array = { 0.0f, 0.0f, 0.0f, 10000.0f };
        ((i)this.d).a(array);
        return array[3];
    }
    
    float e() {
        final float[] array = { 0.0f, 0.0f, 0.0f, 0.0f };
        ((i)this.d).a(array);
        return array[3];
    }
}
