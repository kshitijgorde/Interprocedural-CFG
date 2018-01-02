// 
// Decompiled by Procyon v0.5.30
// 

class MoonDistance
{
    double jde;
    
    MoonDistance(final double JD) {
        this.jde = JD;
    }
    
    double computeR() {
        final double K = 0.017453292519943295;
        final double T = (this.jde - 2451545.0) / 36525.0;
        final double D = 0.017453292519943295 * (297.8502042 + 445267.1115168 * T - 0.00163 * T * T + T * T * T / 545868.0 - T * T * T * T / 1.13065E8);
        final double M = 0.017453292519943295 * (357.5291092 + 35999.0502909 * T - 1.536E-4 * T * T + T * T * T / 2.449E7);
        final double MS = 0.017453292519943295 * (134.9634114 + 477198.8676313 * T + 0.008997 * T * T + T * T * T / 69699.0 - T * T * T * T / 1.4712E7);
        final double F = 0.017453292519943295 * (93.2720993 + 483202.0175273 * T - 0.0034029 * T * T - T * T * T / 3526000.0 + T * T * T * T / 8.6331E8);
        final double E = 1.0 - 0.002516 * T - 7.4E-6 * T * T;
        double r = -2.0905355E7 * Math.cos(MS) - 3699111.0 * Math.cos(2.0 * D - MS) - 2955968.0 * Math.cos(2.0 * D) - 569925.0 * Math.cos(2.0 * MS) + E * 48888.0 * Math.cos(M) - 3149.0 * Math.cos(2.0 * F);
        r = r + 246158.0 * Math.cos(2.0 * D - 2.0 * MS) - E * 152138.0 * Math.cos(2.0 * D - M - MS) - 170733.0 * Math.cos(2.0 * D + MS) - E * 204586.0 * Math.cos(2.0 * D - M) - E * 129620.0 * Math.cos(M - MS);
        r = r + 108743.0 * Math.cos(D) + E * 104755.0 * Math.cos(M + MS) + 10321.0 * Math.cos(2.0 * D - 2.0 * F) + 79661.0 * Math.cos(MS - 2.0 * F) - 34782.0 * Math.cos(4.0 * D - MS) - 23210.0 * Math.cos(3.0 * MS);
        r = r - 21636.0 * Math.cos(4.0 * D - 2.0 * MS) + E * 24208.0 * Math.cos(2.0 * D + M - MS) + E * 30824.0 * Math.cos(2.0 * D + M) - 8379.0 * Math.cos(D - MS) - E * 16675.0 * Math.cos(D + M);
        r = r - E * 12831.0 * Math.cos(2.0 * D - M + MS) - 10445.0 * Math.cos(2.0 * D + 2.0 * MS) - 11650.0 * Math.cos(4.0 * D) + 14403.0 * Math.cos(2.0 * D - 3.0 * MS) - E * 7003.0 * Math.cos(M - 2.0 * MS);
        r = r + E * 10056.0 * Math.cos(2.0 * D - M - 2.0 * MS) + 6322.0 * Math.cos(D + MS) - E * E * 9884.0 * Math.cos(2.0 * D - 2.0 * M) + E * 5751.0 * Math.cos(M + 2.0 * MS) - E * E * 4950.0 * Math.cos(2.0 * D - 2.0 * M - MS);
        r = r + 4130.0 * Math.cos(2.0 * D + MS - 2.0 * F) - E * 3958.0 * Math.cos(4.0 * D - M - MS) + 3258.0 * Math.cos(3.0 * D - MS) + E * 2616.0 * Math.cos(2.0 * D + M + MS) - E * 1897.0 * Math.cos(4.0 * D - M - 2.0 * MS);
        r = r - E * E * 2117.0 * Math.cos(2.0 * M - MS) + E * E * 2354.0 * Math.cos(2.0 * D + 2.0 * M - MS) - 1423.0 * Math.cos(4.0 * D + MS) - 1117.0 * Math.cos(4.0 * MS) - E * 1571.0 * Math.cos(4.0 * D - M);
        r = r - 1739.0 * Math.cos(D - 2.0 * MS) - 4421.0 * Math.cos(2.0 * MS - 2.0 * F) + E * E * 1165.0 * Math.cos(2.0 * M + MS) + 8752.0 * Math.cos(2.0 * D - MS - 2.0 * F);
        return 385000.56 + r / 1000.0;
    }
}
