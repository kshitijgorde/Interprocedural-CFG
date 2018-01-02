// 
// Decompiled by Procyon v0.5.30
// 

package astro;

public class Matrix
{
    public double fA11;
    public double fA12;
    public double fA13;
    public double fA21;
    public double fA22;
    public double fA23;
    public double fA31;
    public double fA32;
    public double fA33;
    static final double fGeneralPrec = 0.013888888888888888;
    static final double fPrecLimit = 30.0;
    
    public Matrix() {
        final double fa11 = 0.0;
        this.fA33 = fa11;
        this.fA32 = fa11;
        this.fA31 = fa11;
        this.fA23 = fa11;
        this.fA22 = fa11;
        this.fA21 = fa11;
        this.fA13 = fa11;
        this.fA12 = fa11;
        this.fA11 = fa11;
    }
    
    public Matrix(final double fa11, final double fa12, final double fa13, final double fa14, final double fa15, final double fa16, final double fa17, final double fa18, final double fa19) {
        this.fA11 = fa11;
        this.fA12 = fa12;
        this.fA13 = fa13;
        this.fA21 = fa14;
        this.fA22 = fa15;
        this.fA23 = fa16;
        this.fA31 = fa17;
        this.fA32 = fa18;
        this.fA33 = fa19;
    }
    
    public Matrix Mul(final Matrix matrix) {
        return new Matrix(this.fA11 * matrix.fA11 + this.fA12 * matrix.fA21 + this.fA13 * matrix.fA31, this.fA11 * matrix.fA12 + this.fA12 * matrix.fA22 + this.fA13 * matrix.fA32, this.fA11 * matrix.fA13 + this.fA12 * matrix.fA23 + this.fA13 * matrix.fA33, this.fA21 * matrix.fA11 + this.fA22 * matrix.fA21 + this.fA23 * matrix.fA31, this.fA21 * matrix.fA12 + this.fA22 * matrix.fA22 + this.fA23 * matrix.fA32, this.fA21 * matrix.fA13 + this.fA22 * matrix.fA23 + this.fA23 * matrix.fA33, this.fA31 * matrix.fA11 + this.fA32 * matrix.fA21 + this.fA33 * matrix.fA31, this.fA31 * matrix.fA12 + this.fA32 * matrix.fA22 + this.fA33 * matrix.fA32, this.fA31 * matrix.fA13 + this.fA32 * matrix.fA23 + this.fA33 * matrix.fA33);
    }
    
    public Matrix Mul(final double n) {
        return new Matrix(this.fA11 * n, this.fA12 * n, this.fA13 * n, this.fA21 * n, this.fA22 * n, this.fA23 * n, this.fA31 * n, this.fA32 * n, this.fA33 * n);
    }
    
    public static Matrix RotateX(final double n) {
        return new Matrix(1.0, 0.0, 0.0, 0.0, Math.cos(n), Math.sin(n), 0.0, -Math.sin(n), Math.cos(n));
    }
    
    public static Matrix RotateY(final double n) {
        return new Matrix(Math.cos(n), 0.0, -Math.sin(n), 0.0, 1.0, 0.0, Math.sin(n), 0.0, Math.cos(n));
    }
    
    public static Matrix RotateZ(final double n) {
        return new Matrix(Math.cos(n), Math.sin(n), 0.0, -Math.sin(n), Math.cos(n), 0.0, 0.0, 0.0, 1.0);
    }
    
    public void Invert() {
        final double n = 1.0 / (this.fA11 * (this.fA22 * this.fA33 - this.fA23 * this.fA32) - this.fA12 * (this.fA21 * this.fA33 - this.fA23 * this.fA31) + this.fA13 * (this.fA21 * this.fA32 - this.fA22 * this.fA31));
        final double fa11 = 1.0 * n * (this.fA22 * this.fA33 - this.fA23 * this.fA32);
        final double fa12 = -1.0 * n * (this.fA12 * this.fA33 - this.fA13 * this.fA32);
        final double fa13 = 1.0 * n * (this.fA12 * this.fA23 - this.fA13 * this.fA22);
        final double fa14 = -1.0 * n * (this.fA21 * this.fA33 - this.fA23 * this.fA31);
        final double fa15 = 1.0 * n * (this.fA11 * this.fA33 - this.fA13 * this.fA31);
        final double fa16 = -1.0 * n * (this.fA11 * this.fA23 - this.fA13 * this.fA21);
        final double fa17 = 1.0 * n * (this.fA21 * this.fA32 - this.fA22 * this.fA31);
        final double fa18 = -1.0 * n * (this.fA11 * this.fA32 - this.fA12 * this.fA31);
        final double fa19 = 1.0 * n * (this.fA11 * this.fA22 - this.fA12 * this.fA21);
        this.fA11 = fa11;
        this.fA12 = fa12;
        this.fA13 = fa13;
        this.fA21 = fa14;
        this.fA22 = fa15;
        this.fA23 = fa16;
        this.fA31 = fa17;
        this.fA32 = fa18;
        this.fA33 = fa19;
    }
    
    public static Matrix PrecMatrix(double n, double n2) {
        double n3 = 0.0;
        boolean b = false;
        boolean b2 = false;
        if (n2 == n) {
            return new Matrix(1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0);
        }
        double n4 = (n - 2451545.0) / 36525.0;
        if (n4 < -30.0 || 30.0 < n4) {
            b = true;
            final double n5 = n2;
            n2 = n;
            n = n5;
            n4 = (n - 2451545.0) / 36525.0;
        }
        final double n6 = n4 * n4;
        final double n8;
        double n7 = n8 = (n2 - n) / 36525.0;
        if (n8 < -30.0) {
            b2 = true;
            n7 = -30.0;
            n3 = 1355795.0;
        }
        if (30.0 < n8) {
            b2 = true;
            n7 = 30.0;
            n3 = 3547295.0;
        }
        final double n9 = n7 * n7;
        final double n10 = n9 * n7;
        Matrix matrix = RotateZ((-90.0 - ((2306.2181 + 1.39656 * n4 - 1.39E-4 * n6) * n7 + (1.09468 + 6.6E-5 * n4) * n9 + 0.018203 * n10) / 3600.0) * 3.141592653589793 / 180.0).Mul(RotateX(((2004.3109 - 0.8533 * n4 - 2.17E-4 * n6) * n7 - (0.42665 + 2.17E-4 * n4) * n9 - 0.041833 * n10) / 3600.0 * 3.141592653589793 / 180.0).Mul(RotateZ((90.0 - ((2306.2181 + 1.39656 * n4 - 1.39E-4 * n6) * n7 + (0.30188 - 3.44E-4 * n4) * n9 + 0.017998 * n10) / 3600.0) * 3.141592653589793 / 180.0)));
        if (b2) {
            double n11;
            if (n8 < -30.0) {
                n11 = n2 - n + 1095750.0;
            }
            else {
                n11 = n2 - n - 1095750.0;
            }
            final double n12 = -n11 / 365.24 * 0.013888888888888888 * 3.141592653589793 / 180.0;
            final double ep = ATime.getEp(n3);
            matrix = RotateX(-ep).Mul(RotateZ(n12).Mul(RotateX(ep))).Mul(matrix);
        }
        if (b) {
            matrix.Invert();
        }
        return matrix;
    }
    
    public static Matrix VectorConstant(final double n, final double n2, final double n3, final ATime aTime) {
        final double t = aTime.getT();
        final double t2 = aTime.getT2();
        double n4;
        if (t2 < -40.0) {
            n4 = 0.41595611758032414;
        }
        else if (t2 > 40.0) {
            n4 = 0.40234254941476844;
        }
        else {
            n4 = (23.44253 - 1.3E-4 * t + 0.00256 * Math.cos((249.0 - 19.3 * t) * 3.141592653589793 / 180.0) + 1.5E-4 * Math.cos((198.0 + 720.0 * t) * 3.141592653589793 / 180.0)) * 0.017453292519943295;
        }
        final double sin = Math.sin(n4);
        final double cos = Math.cos(n4);
        final double sin2 = Math.sin(n);
        final double sin3 = Math.sin(n2);
        final double sin4 = Math.sin(n3);
        final double cos2 = Math.cos(n);
        final double cos3 = Math.cos(n2);
        final double cos4 = Math.cos(n3);
        final double n5 = cos2 * sin3 + sin2 * cos4 * cos3;
        final double n6 = -sin2 * sin3 + cos2 * cos4 * cos3;
        return new Matrix(cos2 * cos3 - sin2 * cos4 * sin3, -sin2 * cos3 - cos2 * cos4 * sin3, 0.0, n5 * cos - sin2 * sin4 * sin, n6 * cos - cos2 * sin4 * sin, 0.0, n5 * sin + sin2 * sin4 * cos, n6 * sin + cos2 * sin4 * cos, 0.0);
    }
}
