// 
// Decompiled by Procyon v0.5.30
// 

final class j
{
    float a;
    float b;
    float c;
    float d;
    float e;
    float f;
    float g;
    float h;
    float i;
    float j;
    float k;
    int l;
    int m;
    int n;
    int o;
    int p;
    int q;
    int r;
    int s;
    int t;
    boolean u;
    int v;
    int w;
    int x;
    float y;
    int z;
    int A;
    int B;
    int C;
    int D;
    int E;
    float F;
    int G;
    int H;
    int I;
    int J;
    float K;
    int L;
    int M;
    
    j() {
        this.L = -2;
        this.a = 0.0f;
        this.b = 0.0f;
        this.c = 0.0f;
        this.u = false;
        this.e = 0.0f;
        this.d = 0.0f;
        this.f = 0.0f;
        this.x = -1;
        this.B = 0;
    }
    
    j(final float a, final float b, final float c, final float e) {
        this.L = -2;
        this.a = a;
        this.b = b;
        this.c = c;
        this.e = e;
        this.d = 0.0f;
        this.f = 0.0f;
        this.u = false;
    }
    
    final void a(final j j) {
        this.a = j.a;
        this.b = j.b;
        this.c = j.c;
        this.e = j.e;
        this.d = j.d;
        this.f = j.f;
    }
    
    final void a() {
        this.a += this.h;
        this.b += this.i;
        this.c += this.j;
    }
    
    final void a(final boolean b) {
        if (this.i > 0.0f) {
            this.i *= 0.9;
            if (this.i < 0.05) {
                this.i = -0.05f;
            }
        }
        else {
            this.i *= 1.2;
            if (this.b <= 1.0f) {
                if (b) {
                    this.i *= (float)(-0.15);
                    return;
                }
                this.b = 0.0f;
                this.i = 0.0f;
            }
        }
    }
}
