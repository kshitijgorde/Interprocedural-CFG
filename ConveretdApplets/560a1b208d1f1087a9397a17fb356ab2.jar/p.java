// 
// Decompiled by Procyon v0.5.30
// 

class p
{
    float a;
    float b;
    float c;
    
    float a(final p p) {
        return this.a * p.a + this.b * p.b + this.c * p.c;
    }
    
    void a(final float a, final float b, final float c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    
    p() {
    }
    
    p(final float n, final float n2, final float n3) {
        this.a(n, n2, n3);
    }
    
    p b(final p p) {
        return new p(this.b * p.c - this.c * p.b, this.c * p.a - this.a * p.c, this.a * p.b - this.b * p.a);
    }
}
