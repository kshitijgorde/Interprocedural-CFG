// 
// Decompiled by Procyon v0.5.30
// 

class bb implements h
{
    float a;
    float b;
    float c;
    c d;
    
    public void a(final c d) {
        this.d = d;
    }
    
    public c a() {
        return this.d;
    }
    
    public void a(final t t) {
    }
    
    public bb() {
    }
    
    public bb(final float a, final float b, final float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    public bb(final bb bb) {
        this(bb.a, bb.b, bb.c);
    }
    
    public final void b() {
        final float c = this.c();
        final float n = (c == 0.0f) ? 0.0f : (1.0f / c);
        this.a *= n;
        this.b *= n;
        this.c *= n;
    }
    
    public void a(final bb bb) {
        this.a += bb.a;
        this.b += bb.b;
        this.c += bb.c;
    }
    
    public void b(final float n, final float n2, final float n3) {
        this.a -= n;
        this.b -= n2;
        this.c -= n3;
    }
    
    public void b(final bb bb) {
        this.a -= bb.a;
        this.b -= bb.b;
        this.c -= bb.c;
    }
    
    public float c(final bb bb) {
        if (bb == null) {
            return 0.0f;
        }
        return this.a * bb.a + this.b * bb.b + this.c * bb.c;
    }
    
    public final bb d(final bb bb) {
        if (bb == null) {
            return null;
        }
        return new bb(this.b * bb.c - bb.b * this.c, this.c * bb.a - bb.c * this.a, this.a * bb.b - bb.a * this.b);
    }
    
    public float c() {
        return (float)Math.sqrt(this.a * this.a + this.b * this.b + this.c * this.c);
    }
    
    static final void a(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8, final float n9, final bb bb) {
        final float n10 = n4 - n;
        final float n11 = n5 - n2;
        final float n12 = n6 - n3;
        final float n13 = n7 - n;
        final float n14 = n8 - n2;
        final float n15 = n9 - n3;
        bb.a = n11 * n15 - n14 * n12;
        bb.b = n12 * n13 - n15 * n10;
        bb.c = n10 * n14 - n13 * n11;
        bb.b();
    }
    
    public String toString() {
        return "(" + this.a + ", " + this.b + ", " + this.c + ")";
    }
    
    public void a(final float a) {
        this.a = a;
    }
    
    public void b(final float b) {
        this.b = b;
    }
    
    public void c(final float c) {
        this.c = c;
    }
    
    public float d() {
        return this.a;
    }
    
    public float e() {
        return this.b;
    }
    
    public float f() {
        return this.c;
    }
}
