// 
// Decompiled by Procyon v0.5.30
// 

public class C14
{
    double[][] d;
    public static String e;
    
    public double[] a(final double n, final double n2, final double n3) {
        final double[] array = { n * this.d[0][0] + n2 * this.d[1][0] + n3 * this.d[2][0] + this.d[3][0], n * this.d[0][1] + n2 * this.d[1][1] + n3 * this.d[2][1] + this.d[3][1], n * this.d[0][2] + n2 * this.d[1][2] + n3 * this.d[2][2] + this.d[3][2] };
        final double n4 = n * this.d[0][3] + n2 * this.d[1][3] + n3 * this.d[2][3] + this.d[3][3];
        if (n4 > 0.0) {
            final double[] array2 = array;
            final int n5 = 0;
            array2[n5] /= n4;
            final double[] array3 = array;
            final int n6 = 1;
            array3[n6] /= n4;
            final double[] array4 = array;
            final int n7 = 2;
            array4[n7] /= n4;
        }
        return array;
    }
    
    public C14 b() {
        final double[][] array = new double[4][4];
        final double n = this.d[1][0] * this.d[2][1] - this.d[1][1] * this.d[2][0];
        final double n2 = this.d[1][0] * this.d[2][2] - this.d[1][2] * this.d[2][0];
        final double n3 = this.d[1][0] * this.d[2][3] - this.d[1][3] * this.d[2][0];
        final double n4 = this.d[1][0] * this.d[3][1] - this.d[1][1] * this.d[3][0];
        final double n5 = this.d[1][0] * this.d[3][2] - this.d[1][2] * this.d[3][0];
        final double n6 = this.d[1][0] * this.d[3][3] - this.d[1][3] * this.d[3][0];
        final double n7 = this.d[1][1] * this.d[2][2] - this.d[1][2] * this.d[2][1];
        final double n8 = this.d[1][1] * this.d[2][3] - this.d[1][3] * this.d[2][1];
        final double n9 = this.d[1][1] * this.d[3][2] - this.d[1][2] * this.d[3][1];
        final double n10 = this.d[1][1] * this.d[3][3] - this.d[1][3] * this.d[3][1];
        final double n11 = this.d[1][2] * this.d[2][3] - this.d[1][3] * this.d[2][2];
        final double n12 = this.d[1][2] * this.d[3][3] - this.d[1][3] * this.d[3][2];
        final double n13 = this.d[2][0] * this.d[3][1] - this.d[2][1] * this.d[3][0];
        final double n14 = this.d[2][0] * this.d[3][2] - this.d[2][2] * this.d[3][0];
        final double n15 = this.d[2][0] * this.d[3][3] - this.d[2][3] * this.d[3][0];
        final double n16 = this.d[2][1] * this.d[3][2] - this.d[2][2] * this.d[3][1];
        final double n17 = this.d[2][1] * this.d[3][3] - this.d[2][3] * this.d[3][1];
        final double n18 = this.d[2][2] * this.d[3][3] - this.d[2][3] * this.d[3][2];
        array[0][0] = this.d[1][1] * n18 - this.d[1][2] * n17 + this.d[1][3] * n16;
        array[0][1] = this.d[0][2] * n17 - this.d[0][3] * n16 - this.d[0][1] * n18;
        array[0][2] = this.d[0][1] * n12 - this.d[0][2] * n10 + this.d[0][3] * n9;
        array[0][3] = this.d[0][2] * n8 - this.d[0][3] * n7 - this.d[0][1] * n11;
        array[1][0] = this.d[1][2] * n15 - this.d[1][3] * n14 - this.d[1][0] * n18;
        array[1][1] = this.d[0][0] * n18 - this.d[0][2] * n15 + this.d[0][3] * n14;
        array[1][2] = this.d[0][2] * n6 - this.d[0][3] * n5 - this.d[0][0] * n12;
        array[1][3] = this.d[0][0] * n11 - this.d[0][2] * n3 + this.d[0][3] * n2;
        array[2][0] = this.d[1][0] * n17 - this.d[1][1] * n15 + this.d[1][3] * n13;
        array[2][1] = this.d[0][1] * n15 - this.d[0][3] * n13 - this.d[0][0] * n17;
        array[2][2] = this.d[0][0] * n10 - this.d[0][1] * n6 + this.d[0][3] * n4;
        array[2][3] = this.d[0][1] * n3 - this.d[0][3] * n - this.d[0][0] * n8;
        array[3][0] = this.d[1][1] * n14 - this.d[1][2] * n13 - this.d[1][0] * n16;
        array[3][1] = this.d[0][0] * n16 - this.d[0][1] * n14 + this.d[0][2] * n13;
        array[3][2] = this.d[0][1] * n5 - this.d[0][2] * n4 - this.d[0][0] * n9;
        array[3][3] = this.d[0][0] * n7 - this.d[0][1] * n2 + this.d[0][2] * n;
        return new C14(array);
    }
    
    static {
        C14.e = "Copyright (c) 2000 - ZoomON Inc.  All Rights Reserved";
    }
    
    public double[][] c() {
        return this.d;
    }
    
    public C14(final double[][] d) {
        this.d = d;
    }
}
