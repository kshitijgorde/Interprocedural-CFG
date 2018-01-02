// 
// Decompiled by Procyon v0.5.30
// 

public final class k extends f
{
    public float j;
    public float e;
    public float g;
    public float f;
    public float n;
    public float l;
    public float o;
    public float i;
    public int a;
    
    public final void a() {
        this.a(1.0f);
    }
    
    public final void b() {
        this.a(0.0f);
    }
    
    public final boolean a() {
        final boolean a = super.a();
        this.a = ((int)((this.f + this.i * this.a) * 255.0f) << 24 | (int)((this.j + this.n * this.a) * 255.0f) << 16 | (int)((this.e + this.l * this.a) * 255.0f) << 8 | (int)((this.g + this.o * this.a) * 255.0f));
        return a;
    }
    
    public k(final int n, final int n2) {
        final int n3 = n >> 24 & 0xFF;
        final int n4 = n >> 16 & 0xFF;
        final int n5 = n >> 8 & 0xFF;
        final int n6 = n & 0xFF;
        final int n7 = n2 >> 24 & 0xFF;
        final int n8 = n2 >> 16 & 0xFF;
        final int n9 = n2 >> 8 & 0xFF;
        final int n10 = n2 & 0xFF;
        this.j = n4 / 255.0f;
        this.e = n5 / 255.0f;
        this.g = n6 / 255.0f;
        this.f = n3 / 255.0f;
        this.n = (n8 - n4) / 255.0f;
        this.l = (n9 - n5) / 255.0f;
        this.o = (n10 - n6) / 255.0f;
        this.i = (n7 - n3) / 255.0f;
    }
}
