// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.core;

class b
{
    g a;
    IndexedFaceSet b;
    float c;
    float d;
    int e;
    int f;
    int g;
    int h;
    int i;
    int j;
    int k;
    int l;
    int m;
    float n;
    float o;
    float p;
    float q;
    float r;
    float s;
    float t;
    float u;
    float v;
    int w;
    int x;
    int y;
    int z;
    int A;
    int B;
    int C;
    int D;
    int E;
    int F;
    int G;
    int H;
    int I;
    int J;
    int K;
    int L;
    int M;
    int N;
    int O;
    int P;
    
    void a(final g a, int n, final int n2, final int[] array, final int[] array2) {
        this.a = a;
        this.m = n + 1;
        this.l = array[this.m] + 15;
        this.z = this.l >> 4;
        this.l = array[n2 + 1] + 15;
        this.f = this.l >> 4;
        this.B = this.f - this.z;
        if (this.B > 0) {
            this.h = array[n2 + 1] - array[this.m];
            this.j = this.h << 4;
            this.i = array[n2] - array[n];
            this.k = this.i << 4;
            this.g = this.k * this.z - this.i * array[this.m] + this.h * array[n] - 1 + this.j;
            this.x = this.g / this.j;
            this.D = this.g % this.j;
            if (this.g < 0) {
                this.D = -this.D;
                if (this.D != 0) {
                    --this.x;
                    this.D = this.j - this.D;
                }
            }
            this.y = this.k / this.j;
            this.E = this.k % this.j;
            if (this.k < 0) {
                this.E = -this.E;
                if (this.E != 0) {
                    --this.y;
                    this.E = this.j - this.E;
                }
            }
            this.F = this.j;
            this.c = ((this.z << 4) - array[this.m]) * 0.0625f;
            this.d = ((this.x << 4) - array[n]) * 0.0625f;
            if (this.b.bm) {
                this.G = (int)(array2[n] + this.c * this.b.eC + this.d * this.b.eB);
                this.H = this.y * this.b.eB + this.b.eC;
                this.I = this.b.eB;
                this.J = (int)(array2[n + 1] + this.c * this.b.eE + this.d * this.b.eD);
                this.K = this.y * this.b.eD + this.b.eE;
                this.L = this.b.eD;
                this.M = (int)(array2[n + 2] + this.c * this.b.eG + this.d * this.b.eF);
                this.N = this.y * this.b.eF + this.b.eG;
                this.O = this.b.eF;
            }
            n /= 3;
            this.w = this.x + (a.bn - 1 - this.z) * a.bm;
            this.n = this.b.es[n] + this.c * this.b.ew + this.d * this.b.ev;
            this.o = this.y * this.b.ev + this.b.ew;
            this.p = this.b.ev;
            if (this.b.r) {
                this.q = this.b.et[n] + this.c * this.b.ey + this.d * this.b.ex;
                this.r = this.y * this.b.ex + this.b.ey;
                this.s = this.b.ex;
                this.t = this.b.eu[n] + this.c * this.b.eA + this.d * this.b.ez;
                this.u = this.y * this.b.ez + this.b.eA;
                this.v = this.b.ez;
            }
        }
    }
    
    b(final IndexedFaceSet b) {
        this.b = b;
    }
    
    void a() {
        this.w += this.y - this.a.bm;
        ++this.z;
        --this.B;
        this.n += this.o;
        if (this.b.r) {
            this.q += this.r;
            this.t += this.u;
        }
        if (this.b.bm) {
            this.G += this.H;
            this.J += this.K;
            this.M += this.N;
        }
        this.D += this.E;
        if (this.D >= this.F) {
            ++this.w;
            this.D -= this.F;
            this.n += this.p;
            if (this.b.bm) {
                this.G += this.I;
                this.J += this.L;
                this.M += this.O;
            }
            if (this.b.r) {
                this.q += this.s;
                this.t += this.v;
            }
        }
    }
}
