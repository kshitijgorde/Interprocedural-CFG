// 
// Decompiled by Procyon v0.5.30
// 

package dlt.a.b;

public class a
{
    public static boolean int;
    private static a[] for;
    private static a[] do;
    private static a[] if;
    private double[][] a;
    
    private a() {
        this.a = new double[4][4];
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                this.a[i][j] = 0.0;
            }
        }
        this.a[0][0] = 1.0;
        this.a[1][1] = 1.0;
        this.a[2][2] = 1.0;
        this.a[3][3] = 1.0;
    }
    
    private double[][] if() {
        return this.a;
    }
    
    private void a(final double[][] a) {
        this.a = a;
    }
    
    private static a[] a() {
        final a[] array = new a[360];
        for (int i = 0; i < 360; ++i) {
            final a a = new a();
            final double[][] if1 = a.if();
            if1[1][1] = d.a(i);
            if1[1][2] = d.if(i);
            if1[2][1] = -d.if(i);
            if1[2][2] = d.a(i);
            a.a(if1);
            array[i] = a;
        }
        return array;
    }
    
    private static a[] do() {
        final a[] array = new a[360];
        for (int i = 0; i < 360; ++i) {
            final a a = new a();
            final double[][] if1 = a.if();
            if1[0][0] = d.a(i);
            if1[0][2] = -d.if(i);
            if1[2][0] = d.if(i);
            if1[2][2] = d.a(i);
            a.a(if1);
            array[i] = a;
        }
        return array;
    }
    
    private static a[] for() {
        final a[] array = new a[360];
        for (int i = 0; i < 360; ++i) {
            final a a = new a();
            final double[][] if1 = a.if();
            if1[0][0] = d.a(i);
            if1[0][1] = d.if(i);
            if1[1][0] = -d.if(i);
            if1[1][1] = d.a(i);
            a.a(if1);
            array[i] = a;
        }
        return array;
    }
    
    public void a(final c c) {
        this.a(c, c);
    }
    
    public void a(final c c, final c c2) {
        final double[] array = { c.for(), c.a(), c.int(), 1.0 };
        final double[] array2 = new double[4];
        for (int i = 0; i < 4; ++i) {
            double n = 0.0;
            for (int j = 0; j < 4; ++j) {
                n += array[j] * this.a[j][i];
            }
            array2[i] = n;
        }
        c2.a(array2[0], array2[1], array2[2]);
    }
    
    public static a a(final a a, final a a2) {
        final a a3 = new a();
        final double[][] if1 = a.if();
        final double[][] if2 = a2.if();
        final double[][] if3 = a3.if();
        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                double n = 0.0;
                for (int k = 0; k < 4; ++k) {
                    n += if1[i][k] * if2[k][j];
                }
                if3[i][j] = n;
            }
        }
        a3.a(if3);
        return a3;
    }
    
    public static a a(final b b) {
        final a a = new a();
        final double[][] if1 = a.if();
        if1[3][0] = b.for();
        if1[3][1] = b.a();
        if1[3][2] = b.int();
        a.a(if1);
        return a;
    }
    
    public static a if(final c c) {
        final a a = new a();
        final double[][] if1 = a.if();
        if1[3][0] = c.for();
        if1[3][1] = c.a();
        if1[3][2] = c.int();
        a.a(if1);
        return a;
    }
    
    public static a a(final f f) {
        final int n = (int)f.if();
        final int n2 = (int)f.a();
        final int n3 = (int)f.int();
        a a = new a();
        if (dlt.a.b.a.int) {
            if (n2 != 0) {
                a = a(a, dlt.a.b.a.do[n2]);
            }
            if (n != 0) {
                a = a(a, dlt.a.b.a.for[n]);
            }
        }
        else {
            if (n != 0) {
                a = a(a, dlt.a.b.a.for[n]);
            }
            if (n2 != 0) {
                a = a(a, dlt.a.b.a.do[n2]);
            }
        }
        if (n3 != 0) {
            a = a(a, dlt.a.b.a.if[n3]);
        }
        return a;
    }
    
    public static a a(final e e) {
        final a a = new a();
        final double[][] if1 = a.if();
        if1[0][0] = e.do();
        if1[1][1] = e.a();
        if1[2][2] = e.int();
        a.a(if1);
        return a;
    }
    
    static {
        a.int = false;
        a.for = a();
        a.do = do();
        a.if = for();
    }
}
