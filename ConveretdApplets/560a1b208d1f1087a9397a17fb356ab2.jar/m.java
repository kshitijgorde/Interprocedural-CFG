// 
// Decompiled by Procyon v0.5.30
// 

class m
{
    protected static final float a = 255.0f;
    protected static final float b = 0.63661975f;
    protected static final float c = 1.5707964f;
    protected static final float d = 1.0E-5f;
    float e;
    protected p f;
    protected p g;
    protected p h;
    protected p i;
    protected p j;
    protected p k;
    q l;
    
    float a() {
        return this.g.c;
    }
    
    m() {
        this.e = 255.0f;
        this.f = new p();
        this.g = new p();
        this.h = new p();
        this.i = new p();
        this.j = new p();
        this.k = new p();
        this.l = null;
        this.b(new q());
        this.a(new q());
    }
    
    void a(final q l) {
        this.l = l;
        final float n = (float)Math.cos(l.c);
        final float n2 = (float)Math.sin(l.c);
        final float n3 = (float)Math.cos(l.d);
        final float n4 = (float)Math.sin(l.d);
        this.h.a(n2 * n4, n3, n * n4);
        this.f.a(n2 * n3, -n4, n * n3);
        this.h.a(this.j.a(this.h), this.k.a(this.h), this.i.a(this.h));
        this.f.a(this.j.a(this.f), this.k.a(this.f), this.i.a(this.f));
        this.g = this.h.b(this.f);
    }
    
    float b() {
        return this.h.c;
    }
    
    void b(final q q) {
        final float n = (float)Math.cos(q.c);
        final float n2 = (float)Math.sin(q.c);
        final float n3 = (float)Math.cos(q.d);
        final float n4 = (float)Math.sin(q.d);
        this.k.a(n2 * n4, n3, n * n4);
        this.i.a(n2 * n3, -n4, n * n3);
        this.j = this.k.b(this.i);
    }
    
    float c() {
        return this.f.c;
    }
    
    float a(final j j, final j i) {
        final float n = j.a * this.g.a + j.b * this.h.a + this.l.e * this.e * this.f.a;
        final float n2 = j.a * this.g.b + j.b * this.h.b + this.l.e * this.e * this.f.b;
        final float n3 = j.a * this.g.c + j.b * this.h.c + this.l.e * this.e * this.f.c;
        final float n4 = (float)Math.sqrt(n * n + n2 * n2);
        if (n4 < 1.0E-5f) {
            final float n5 = 0.0f;
            i.b = n5;
            i.a = n5;
            return n3;
        }
        final float n6 = (float)Math.atan2(n4, Math.abs(n3)) * 0.63661975f * this.e / n4;
        i.a = n * n6;
        i.b = n2 * n6;
        return n3;
    }
    
    void a(final float n) {
        this.e = ((n > 0.0f) ? n : 255.0f);
    }
}
