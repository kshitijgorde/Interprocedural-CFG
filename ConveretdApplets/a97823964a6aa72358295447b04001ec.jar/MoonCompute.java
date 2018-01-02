// 
// Decompiled by Procyon v0.5.30
// 

class MoonCompute
{
    final double K = 0.017453292519943295;
    double RA;
    double DEC;
    double B;
    double L;
    
    public MoonCompute(final double JD) {
        final double T = (JD - 2451545.0) / 36525.0;
        final double eps = this.EPS(T);
        final double coseps = Math.cos(0.017453292519943295 * eps);
        final double sineps = Math.sin(0.017453292519943295 * eps);
        final double Ls = 0.017453292519943295 * (218.3164591 + 481267.88134236 * T - 0.0013268 * T * T + T * T * T / 538841.0 - T * T * T * T / 6.5194E7);
        final double D = 0.017453292519943295 * (297.8502042 + 445267.1115168 * T - 0.00163 * T * T + T * T * T / 545868.0 - T * T * T * T / 1.13065E8);
        final double M = 0.017453292519943295 * (357.5291092 + 35999.0502909 * T - 1.536E-4 * T * T + T * T * T / 2.449E7);
        final double Ms = 0.017453292519943295 * (134.9634114 + 477198.8676313 * T + 0.008997 * T * T + T * T * T / 69699.0 - T * T * T * T / 1.4712E7);
        final double F = 0.017453292519943295 * (93.2720993 + 483202.0175273 * T - 0.0034029 * T * T - T * T * T / 3526000.0 + T * T * T * T / 8.6331E8);
        final double E = 1.0 - 0.002516 * T - 7.4E-6 * T * T;
        final double A1 = 0.017453292519943295 * (119.75 + 131.849 * T);
        final double A2 = 0.017453292519943295 * (53.09 + 479264.29 * T);
        final double A3 = 0.017453292519943295 * (313.45 + 481266.484 * T);
        this.L = this.L0(D, M, Ms, F, E) + 3958.0 * Math.sin(A1) + 1962.0 * Math.sin(Ls - F) + 318.0 * Math.sin(A2);
        this.L *= 1.0E-6;
        this.L %= 360.0;
        double LS = Ls / 0.017453292519943295;
        LS %= 360.0;
        if (LS < 0.0) {
            LS += 360.0;
        }
        this.L += LS;
        if (this.L < 0.0) {
            this.L += 360.0;
        }
    }
    
    public double EPS(final double T) {
        final double eps0 = 23.43929111111111 - (46.815 * T + 5.9E-4 * T * T - 0.001813 * T * T * T) / 3600.0;
        return eps0;
    }
    
    double L0(final double D, final double M, final double Ms, final double F, final double E) {
        final double[][] L = { { 6288774.0, 0.0, 0.0, 1.0, 0.0 }, { 1274027.0, 2.0, 0.0, -1.0, 0.0 }, { 658314.0, 2.0, 0.0, 0.0, 0.0 }, { 213618.0, 0.0, 0.0, 2.0, 0.0 }, { -185116.0, 0.0, 1.0, 0.0, 0.0 }, { -114332.0, 0.0, 0.0, 0.0, 2.0 }, { 58793.0, 2.0, 0.0, -2.0, 0.0 }, { 57066.0, 2.0, -1.0, -1.0, 0.0 }, { 53322.0, 2.0, 0.0, 1.0, 0.0 }, { 45758.0, 2.0, -1.0, 0.0, 0.0 }, { -40923.0, 0.0, 1.0, -1.0, 0.0 }, { -34720.0, 1.0, 0.0, 0.0, 0.0 }, { -30383.0, 0.0, 1.0, 1.0, 0.0 }, { 15327.0, 2.0, 0.0, 0.0, -2.0 }, { -12528.0, 0.0, 0.0, 1.0, 2.0 }, { 10980.0, 0.0, 0.0, 1.0, -2.0 }, { 10675.0, 4.0, 0.0, -1.0, 0.0 }, { 10034.0, 0.0, 0.0, 3.0, 0.0 }, { 8548.0, 4.0, 0.0, -2.0, 0.0 }, { -7888.0, 2.0, 1.0, -1.0, 0.0 }, { -6766.0, 2.0, 1.0, 0.0, 0.0 }, { -5163.0, 1.0, 0.0, -1.0, 0.0 }, { 4987.0, 1.0, 1.0, 0.0, 0.0 }, { 4036.0, 2.0, -1.0, 1.0, 0.0 }, { 3994.0, 2.0, 0.0, 2.0, 0.0 }, { 3861.0, 4.0, 0.0, 0.0, 0.0 }, { 3665.0, 2.0, 0.0, -3.0, 0.0 }, { -2689.0, 0.0, 1.0, -2.0, 0.0 }, { -2602.0, 2.0, 0.0, -1.0, 2.0 }, { 2390.0, 2.0, -1.0, -2.0, 0.0 }, { -2348.0, 1.0, 0.0, 1.0, 0.0 }, { 2236.0, 2.0, -2.0, 0.0, 0.0 }, { -2120.0, 0.0, 1.0, 2.0, 0.0 }, { -2069.0, 0.0, 2.0, 0.0, 0.0 }, { 2048.0, 2.0, -2.0, -1.0, 0.0 }, { -1773.0, 2.0, 0.0, 1.0, -2.0 }, { -1595.0, 2.0, 0.0, 0.0, 2.0 }, { 1215.0, 4.0, -1.0, -1.0, 0.0 }, { -1110.0, 0.0, 0.0, 2.0, 2.0 }, { -892.0, 3.0, 0.0, -1.0, 0.0 }, { -810.0, 2.0, 1.0, 1.0, 0.0 }, { 759.0, 4.0, -1.0, -2.0, 0.0 }, { -713.0, 0.0, 2.0, -1.0, 0.0 }, { -700.0, 2.0, 2.0, -1.0, 0.0 }, { 691.0, 2.0, 1.0, -2.0, 0.0 }, { 596.0, 2.0, -1.0, 0.0, -2.0 }, { 549.0, 4.0, 0.0, 1.0, 0.0 }, { 537.0, 0.0, 0.0, 4.0, 0.0 }, { 520.0, 4.0, -1.0, 0.0, 0.0 }, { -487.0, 1.0, 0.0, -2.0, 0.0 }, { -399.0, 2.0, 1.0, 0.0, -2.0 }, { -381.0, 0.0, 0.0, 2.0, -2.0 }, { 351.0, 1.0, 1.0, 1.0, 0.0 }, { -340.0, 3.0, 0.0, -2.0, 0.0 }, { 330.0, 4.0, 0.0, -3.0, 0.0 }, { 327.0, 2.0, -1.0, 2.0, 0.0 }, { -323.0, 0.0, 2.0, 1.0, 0.0 }, { 299.0, 1.0, 1.0, -1.0, 0.0 }, { 294.0, 2.0, 0.0, 3.0, 0.0 } };
        double l = 0.0;
        for (int i = 0; i < 59; ++i) {
            double term = L[i][0] * Math.sin(L[i][1] * D + L[i][2] * M + L[i][3] * Ms + L[i][4] * F);
            if (Math.abs(L[i][2]) == 1.0) {
                term *= E;
            }
            if (Math.abs(L[i][2]) == 2.0) {
                term *= E * E;
            }
            l += term;
        }
        return l;
    }
    
    double lambda() {
        return this.L;
    }
}