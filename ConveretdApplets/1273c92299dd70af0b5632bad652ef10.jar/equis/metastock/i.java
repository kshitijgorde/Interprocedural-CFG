// 
// Decompiled by Procyon v0.5.30
// 

package equis.metastock;

class i
{
    private int a;
    public int[] b;
    public int[] c;
    public int[] d;
    public double[] e;
    public double[] f;
    
    i() {
        this.b = new int[10];
        this.c = new int[10];
        this.d = new int[10];
        this.e = new double[10];
        this.f = new double[10];
        this.a = -1;
        this.a();
    }
    
    public void a(final int n) {
        this.b[n] = -1;
        this.c[n] = 0;
        this.d[n] = 0;
        this.e[n] = 0.0;
        this.f[n] = 0.0;
    }
    
    public void a() {
        for (int i = 0; i < 10; ++i) {
            this.a(i);
        }
    }
    
    public void a(final int n, final int n2, final double n3, final int n4, final double n5) {
        ++this.a;
        if (this.a == 10) {
            this.a = 0;
        }
        this.b[this.a] = n;
        this.c[this.a] = n2;
        this.d[this.a] = n4;
        this.e[this.a] = n3;
        this.f[this.a] = n5;
    }
    
    public int b() {
        return 10;
    }
}
