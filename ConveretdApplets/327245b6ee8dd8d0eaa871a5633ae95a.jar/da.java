// 
// Decompiled by Procyon v0.5.30
// 

class da
{
    c8 a;
    c8 b;
    int[] c;
    int d;
    boolean e;
    
    da(final c8 a, final c8 b) {
        this.c = new int[2];
        this.e = false;
        this.a = a;
        this.b = b;
        this.d = 0;
    }
    
    void a(final int n) {
        if (this.d < 2) {
            this.c[this.d] = n;
        }
        ++this.d;
    }
    
    void a() {
        this.c = null;
    }
}
