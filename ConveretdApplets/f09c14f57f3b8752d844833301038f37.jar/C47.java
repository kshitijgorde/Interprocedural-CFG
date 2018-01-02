// 
// Decompiled by Procyon v0.5.30
// 

public abstract class C47 extends C28
{
    int[] d;
    public static String e;
    int[] f;
    static int[] g;
    static int[] h;
    public static boolean i;
    
    public C47(final int n, final int n2, final int n3, final int n4) {
        final int[] array = new int[5];
        final int[] array2 = new int[5];
        array[0] = n;
        array2[0] = n2;
        array[1] = n + n3;
        array2[1] = n2;
        array[2] = n + n3;
        array2[2] = n2 + n4;
        array[3] = n;
        array2[3] = n2 + n4;
        array[4] = n;
        array2[4] = n2;
        this.b(array, array2);
    }
    
    private void b(final int[] d, final int[] f) {
        this.d = d;
        this.f = f;
        int n = Integer.MIN_VALUE;
        int n2 = Integer.MIN_VALUE;
        int p2 = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;
        for (int n3 = 0; n3 < this.d.length && n3 < this.f.length; ++n3) {
            if (this.d[n3] > n) {
                n = this.d[n3];
            }
            if (this.d[n3] < p2) {
                p2 = this.d[n3];
            }
            if (this.f[n3] > n2) {
                n2 = this.f[n3];
            }
            if (this.f[n3] < b) {
                b = this.f[n3];
            }
        }
        super.P = p2;
        super.B = b;
        super.H = n - p2;
        super.J = n2 - b;
    }
    
    public void f(final int n, final int n2) {
        super.f(n, n2);
        for (int n3 = 0; n3 < this.d.length && n3 < this.f.length; ++n3) {
            final int[] d = this.d;
            final int n4 = n3;
            d[n4] += n;
            final int[] f = this.f;
            final int n5 = n3;
            f[n5] += n2;
        }
    }
    
    public boolean c(final int[] array, final int[] array2) {
        if (array.length != array2.length || array.length != this.d.length || array2.length != this.f.length) {
            return false;
        }
        for (int n = 0; n < array.length && n < this.d.length; ++n) {
            if (array[n] != this.d[n] || array2[n] != this.f[n]) {
                return false;
            }
        }
        return true;
    }
    
    static {
        C47.e = "Copyright (c) 2000 - ZoomON Inc.  All Rights Reserved";
        C47.g = new int[100];
        C47.h = new int[100];
        C47.i = false;
    }
    
    public C47(final int[] array, final int[] array2) {
        this.b(array, array2);
    }
}
