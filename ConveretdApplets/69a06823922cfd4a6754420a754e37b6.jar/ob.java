import java.util.Random;

// 
// Decompiled by Procyon v0.5.30
// 

public class ob
{
    static int[] a;
    static double b;
    static double c;
    static double d;
    static double e;
    static double f;
    static int[] g;
    static int[] h;
    int[] i;
    double j;
    double k;
    double l;
    double m;
    double n;
    int[] o;
    int[] p;
    public static boolean q;
    
    public ob() {
        this.i = new int[] { 0, 0, 0, 0, 1 };
        this.o = new int[5];
        this.p = new int[5];
        while (System.currentTimeMillis() - System.currentTimeMillis() < 10L) {}
        final Random random = new Random();
        final int[] array = new int[4];
        for (int i = 0; i < 4; ++i) {
            array[i] = random.nextInt();
            while (Math.abs(array[i]) > 10000) {
                final int[] array2 = array;
                final int n = i;
                array2[n] /= 10;
            }
        }
        this.a(Math.abs(array[0]), Math.abs(array[1]), Math.abs(array[2]), Math.abs(array[3]));
        b(Math.abs(array[0]), Math.abs(array[1]), Math.abs(array[2]), Math.abs(array[3]));
    }
    
    public ob(final int[] array) {
        final boolean q = ob.q;
        this.i = new int[] { 0, 0, 0, 0, 1 };
        this.o = new int[5];
        this.p = new int[5];
        this.a(array[0], array[1], array[2], array[3]);
        if (q.a != 0) {
            ob.q = !q;
        }
    }
    
    public double a() {
        final boolean q = ob.q;
        final int[] array = { 0, this.o[1] * this.p[4] + this.o[2] * this.p[3] + this.o[3] * this.p[2] + this.o[4] * this.p[1], this.o[2] * this.p[4] + this.o[3] * this.p[3] + this.o[4] * this.p[2], this.o[3] * this.p[4] + this.o[4] * this.p[3], this.o[4] * this.p[4] };
        this.o[4] = array[4] % (int)this.n;
        array[3] += (int)(array[4] / this.n);
        this.o[3] = array[3] % (int)this.n;
        array[2] += (int)(array[3] / this.n);
        this.o[2] = array[2] % (int)this.n;
        this.o[1] = (int)(array[1] + array[2] / this.n) % (int)this.n;
        final double n = this.j * this.o[1] + this.k * this.o[2] + this.l * this.o[3] + this.m * this.o[4];
        if (q) {
            int a = q.a;
            q.a = ++a;
        }
        return n;
    }
    
    public int a(final int n) {
        return (int)(this.a() * n);
    }
    
    public double b() {
        return this.a();
    }
    
    public void a(final int n, final int n2, final int n3, final int n4) {
        this.i[1] = n;
        this.i[2] = n2;
        this.i[3] = n3;
        int i;
        for (i = 2 * n4 + 1; i >= 10000; i -= 500) {}
        this.i[4] = i;
        this.p[1] = 502;
        this.p[2] = 1521;
        this.p[3] = 4071;
        this.p[4] = 2107;
        this.o[1] = 0;
        this.o[2] = 0;
        this.o[3] = 0;
        this.o[4] = 1;
        this.n = Math.pow(2.0, 12.0);
        this.j = Math.pow(2.0, -12.0);
        this.k = this.j * this.j;
        this.l = this.j * this.k;
        this.m = this.j * this.l;
        for (int j = 1; j <= 4; ++j) {
            this.o[j] = this.i[j];
        }
        this.o[4] = 2 * (this.o[4] / 2) + 1;
    }
    
    public static void b(final int n, final int n2, final int n3, final int n4) {
        ob.a[1] = n;
        ob.a[2] = n2;
        ob.a[3] = n3;
        ob.a[4] = n4;
        ob.h[1] = 502;
        ob.h[2] = 1521;
        ob.h[3] = 4071;
        ob.h[4] = 2107;
        ob.g[1] = 0;
        ob.g[2] = 0;
        ob.g[3] = 0;
        ob.g[4] = 1;
        ob.f = Math.pow(2.0, 12.0);
        ob.b = Math.pow(2.0, -12.0);
        ob.c = ob.b * ob.b;
        ob.d = ob.b * ob.c;
        ob.e = ob.b * ob.d;
        for (int i = 1; i <= 4; ++i) {
            ob.g[i] = ob.a[i];
        }
        ob.g[4] = 2 * (ob.g[4] / 2) + 1;
    }
    
    public int[] c() {
        return new int[] { this.i[1], this.i[2], this.i[3], this.i[4] };
    }
    
    static {
        ob.a = new int[] { 0, 0, 0, 0, 1 };
        ob.g = new int[5];
        ob.h = new int[5];
    }
}
