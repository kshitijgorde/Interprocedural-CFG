// 
// Decompiled by Procyon v0.5.30
// 

public class Geoid
{
    static final double FUZZ = 1.0E-15;
    static final double dtr = 0.017453292519943295;
    static final double rtd = 57.29577951308232;
    double a;
    double inv_f;
    double f;
    double esq;
    double e2sq;
    
    Geoid(final double a, final double inv_f) {
        this.a = a;
        this.inv_f = inv_f;
        this.f = 1.0 / this.inv_f;
        this.esq = 2.0 * this.f - this.f * this.f;
        this.e2sq = this.esq / (1.0 - this.esq);
    }
    
    private boolean fuzzyEquals(final double n, final double n2) {
        return Math.abs(n - n2) < 1.0E-15;
    }
    
    private double rho(final double n) {
        return this.a * (1.0 - this.esq) / Math.pow(1.0 - this.esq * Math.sin(n) * Math.sin(n), 1.5);
    }
    
    private double v(final double n) {
        return this.a / Math.sqrt(1.0 - this.esq * Math.sin(n) * Math.sin(n));
    }
    
    private double CunninghamAzimuth(double n, double n2, double n3, double n4) {
        if (this.fuzzyEquals(n3, 90.0)) {
            return 0.0;
        }
        if (this.fuzzyEquals(n, 90.0)) {
            return 180.0;
        }
        if (this.fuzzyEquals(n, 0.0)) {
            return this.RobbinsAzimuth(n, n2, n3, n4);
        }
        n *= 0.017453292519943295;
        n2 *= 0.017453292519943295;
        n3 *= 0.017453292519943295;
        n4 *= 0.017453292519943295;
        final double n5 = n4 - n2;
        if (!this.fuzzyEquals(n5, 3.141592653589793)) {
            if (this.fuzzyEquals(n5, 0.0)) {
                if (n > n3) {
                    return 180.0;
                }
                if (n <= n3) {
                    return 0.0;
                }
            }
            double n6 = Math.atan2(Math.sin(n5), (Math.tan(n3) / ((1.0 + this.e2sq) * Math.tan(n)) + this.esq * this.v(n) * Math.cos(n) / (this.v(n3) * Math.cos(n3)) - Math.cos(n5)) * Math.sin(n)) * 57.29577951308232;
            if (n6 < 0.0) {
                n6 += 360.0;
            }
            return n6;
        }
        if (n > 0.0 && n3 > 0.0) {
            return 0.0;
        }
        if (n < 0.0 && n3 < 0.0) {
            return 180.0;
        }
        if (n3 < 0.0) {
            if (3.141592653589793 - n - n3 > 3.141592653589793) {
                return 180.0;
            }
            return 0.0;
        }
        else {
            if (3.141592653589793 - n3 - n > 3.141592653589793) {
                return 180.0;
            }
            return 0.0;
        }
    }
    
    private double RobbinsAzimuth(double n, double n2, double n3, double n4) {
        if (this.fuzzyEquals(n3, 90.0)) {
            return 0.0;
        }
        if (this.fuzzyEquals(n, 90.0)) {
            return 180.0;
        }
        n *= 0.017453292519943295;
        n2 *= 0.017453292519943295;
        n3 *= 0.017453292519943295;
        n4 *= 0.017453292519943295;
        final double n5 = n4 - n2;
        if (!this.fuzzyEquals(n5, 3.141592653589793)) {
            if (this.fuzzyEquals(n5, 0.0)) {
                if (n > n3) {
                    return 180.0;
                }
                if (n <= n3) {
                    return 0.0;
                }
            }
            double n6 = Math.atan2(Math.sin(n5), Math.cos(n) * ((1.0 - this.esq) * Math.tan(n3) + this.esq * this.v(n) * Math.sin(n) / (this.v(n3) * Math.cos(n3))) - Math.sin(n) * Math.cos(n5)) * 57.29577951308232;
            if (n6 < 0.0) {
                n6 += 360.0;
            }
            return n6;
        }
        if (n > 0.0 && n3 > 0.0) {
            return 0.0;
        }
        if (n < 0.0 && n3 < 0.0) {
            return 180.0;
        }
        if (n3 < 0.0) {
            if (3.141592653589793 - n - n3 > 3.141592653589793) {
                return 180.0;
            }
            return 0.0;
        }
        else {
            if (3.141592653589793 - n3 - n > 3.141592653589793) {
                return 180.0;
            }
            return 0.0;
        }
    }
    
    public AzimuthDistance RudoeInverse(double n, double n2, double n3, double n4) {
        final double n5 = 0.017453292519943295 * this.CunninghamAzimuth(n, n2, n3, n4);
        n *= 0.017453292519943295;
        n2 *= 0.017453292519943295;
        n3 *= 0.017453292519943295;
        n4 *= 0.017453292519943295;
        final double n6 = this.e2sq * (Math.cos(n) * Math.cos(n) * Math.cos(n5) * Math.cos(n5) + Math.sin(n) * Math.sin(n));
        final double n7 = this.v(n) / (1.0 + n6) * Math.sqrt(1.0 + this.e2sq * Math.cos(n) * Math.cos(n) * Math.cos(n5) * Math.cos(n5));
        final double atan2 = Math.atan2(Math.tan(n), Math.cos(n5) * Math.sqrt(1.0 + n6));
        final double n8 = n4 - n2;
        final double atan3 = Math.atan2(this.v(n) * Math.sin(n) + (1.0 + n6) * (this.v(n3) * (1.0 - this.esq) * Math.sin(n3) - this.v(n) * (1.0 - this.esq) * Math.sin(n)), (this.v(n3) * Math.cos(n3) * Math.cos(n8) * Math.cos(n5) - this.v(n3) * Math.cos(n3) * Math.sin(n8) * Math.sin(n) * Math.sin(n5)) * Math.sqrt(1.0 + n6));
        return new AzimuthDistance(n7 * ((1.0 + 0.25 * n6 - 0.046875 * n6 * n6 + 0.01953125 * n6 * n6 * n6) * (atan3 - atan2) + (-0.125 * n6 + 0.03125 * n6 * n6 - 0.0146484375 * n6 * n6 * n6) * (Math.sin(2.0 * atan3) - Math.sin(2.0 * atan2)) + (-0.00390625 * n6 * n6 + 0.0029296875 * n6 * n6 * n6) * (Math.sin(4.0 * atan3) - Math.sin(4.0 * atan2))), 57.29577951308232 * n5);
    }
}
