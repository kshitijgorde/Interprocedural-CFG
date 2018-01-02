// 
// Decompiled by Procyon v0.5.30
// 

class VenusCompute
{
    final double K = 0.017453292519943295;
    double L;
    double B;
    double R;
    double T;
    double lambda;
    double beta;
    double eps;
    double x;
    double y;
    double z;
    
    public VenusCompute(final double JD) {
        this.T = (JD - 2451545.0) / 365250.0;
        this.eps = 0.017453292519943295 * (23.433333333333334 + (21.448 - 46.815 * this.T - 5.9E-4 * this.T * this.T + 0.001813 * this.T * this.T * this.T) / 3600.0);
        this.B = (this.B0(this.T) + this.B1(this.T) * this.T + this.B2(this.T) * this.T * this.T + this.B3(this.T) * this.T * this.T * this.T + this.B4(this.T) * this.T * this.T * this.T * this.T) / 1.0E8;
        this.L = (this.L0(this.T) + this.L1(this.T) * this.T + this.L2(this.T) * this.T * this.T + this.L3(this.T) * this.T * this.T * this.T + this.L4(this.T) * this.T * this.T * this.T * this.T + this.L5(this.T) * this.T * this.T * this.T * this.T * this.T) / 1.0E8;
        this.R = (this.R0(this.T) + this.R1(this.T) * this.T + this.R2(this.T) * this.T * this.T + this.R3(this.T) * this.T * this.T * this.T + this.R4(this.T) * this.T * this.T * this.T * this.T) / 1.0E8;
        final EarthCompute earthComp = new EarthCompute(JD);
        final double RE = earthComp.earthR();
        final double BE = earthComp.earthB();
        final double LE = earthComp.earthL();
        this.x = this.R * Math.cos(this.B) * Math.cos(this.L) - RE * Math.cos(BE) * Math.cos(LE);
        this.y = this.R * Math.cos(this.B) * Math.sin(this.L) - RE * Math.cos(BE) * Math.sin(LE);
        this.z = this.R * Math.sin(this.B) - RE * Math.sin(BE);
        final double tau = 0.0057755183 * Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        this.T = (JD - 2451545.0 - tau) / 365250.0;
        this.B = (this.B0(this.T) + this.B1(this.T) * this.T + this.B2(this.T) * this.T * this.T + this.B3(this.T) * this.T * this.T * this.T + this.B4(this.T) * this.T * this.T * this.T * this.T) / 1.0E8;
        this.L = (this.L0(this.T) + this.L1(this.T) * this.T + this.L2(this.T) * this.T * this.T + this.L3(this.T) * this.T * this.T * this.T + this.L4(this.T) * this.T * this.T * this.T * this.T + this.L5(this.T) * this.T * this.T * this.T * this.T * this.T) / 1.0E8;
        this.R = (this.R0(this.T) + this.R1(this.T) * this.T + this.R2(this.T) * this.T * this.T + this.R3(this.T) * this.T * this.T * this.T + this.R4(this.T) * this.T * this.T * this.T * this.T) / 1.0E8;
        this.x = this.R * Math.cos(this.B) * Math.cos(this.L) - RE * Math.cos(BE) * Math.cos(LE);
        this.y = this.R * Math.cos(this.B) * Math.sin(this.L) - RE * Math.cos(BE) * Math.sin(LE);
        this.z = this.R * Math.sin(this.B) - RE * Math.sin(BE);
        this.lambda = Math.atan2(this.y, this.x) / 0.017453292519943295;
        this.beta = Math.atan2(this.z, Math.sqrt(this.x * this.x + this.y * this.y));
    }
    
    double alpha() {
        double a = Math.atan2(Math.sin(0.017453292519943295 * this.lambda) * Math.cos(this.eps) - Math.tan(this.beta) * Math.sin(this.eps), Math.cos(0.017453292519943295 * this.lambda)) / 0.017453292519943295;
        if (a < 0.0) {
            a += 360.0;
        }
        return a;
    }
    
    double delta() {
        final double sinDelta = Math.sin(this.beta) * Math.cos(this.eps) + Math.cos(this.beta) * Math.sin(this.eps) * Math.sin(0.017453292519943295 * this.lambda);
        return Math.asin(sinDelta) / 0.017453292519943295 % 360.0;
    }
    
    double R() {
        return this.R;
    }
    
    double earthDist() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }
    
    double L0(final double t) {
        final double[][] L = { { 3.17614667E8, 0.0, 0.0 }, { 1353968.0, 5.5931332, 10213.2855462 }, { 89892.0, 5.3065, 20426.57109 }, { 5477.0, 4.4163, 7860.4194 }, { 3456.0, 2.6996, 11790.6291 }, { 2372.0, 2.9938, 3930.2097 }, { 1664.0, 4.2502, 1577.3435 }, { 1438.0, 4.1575, 9683.5946 }, { 1317.0, 5.1867, 26.2983 }, { 1201.0, 6.1536, 30639.8566 }, { 769.0, 0.816, 9437.763 }, { 761.0, 1.95, 529.691 }, { 708.0, 1.065, 775.523 }, { 585.0, 3.998, 191.448 }, { 500.0, 4.123, 15720.839 }, { 429.0, 3.586, 19367.189 }, { 327.0, 5.677, 5507.553 }, { 326.0, 4.591, 10404.734 }, { 232.0, 3.163, 9153.904 }, { 180.0, 4.653, 1109.379 }, { 155.0, 5.57, 19651.048 }, { 128.0, 4.226, 20.775 }, { 128.0, 0.962, 5661.332 }, { 106.0, 1.537, 801.821 } };
        double l0 = 0.0;
        for (int i = 0; i < 24; ++i) {
            l0 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return l0;
    }
    
    double L1(final double t) {
        final double[][] L = { { 1.021352943053E12, 0.0, 0.0 }, { 95708.0, 2.46424, 10213.28555 }, { 14445.0, 0.51625, 20426.57109 }, { 213.0, 1.795, 30639.857 }, { 174.0, 2.655, 26.298 }, { 152.0, 6.106, 1577.344 }, { 82.0, 5.7, 191.45 }, { 70.0, 2.68, 9437.76 }, { 52.0, 3.6, 775.52 }, { 38.0, 1.03, 529.69 }, { 30.0, 1.25, 5507.55 }, { 25.0, 6.11, 10404.73 } };
        double l1 = 0.0;
        for (int i = 0; i < 12; ++i) {
            l1 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return l1;
    }
    
    double L2(final double t) {
        final double[][] L = { { 54127.0, 0.0, 0.0 }, { 3891.0, 0.3451, 10213.2855 }, { 1338.0, 2.0201, 20426.5711 }, { 24.0, 2.05, 26.3 }, { 19.0, 3.54, 30639.86 }, { 10.0, 3.97, 775.52 }, { 7.0, 1.52, 1577.34 }, { 6.0, 1.0, 191.45 } };
        double l2 = 0.0;
        for (int i = 0; i < 8; ++i) {
            l2 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return l2;
    }
    
    double L3(final double t) {
        final double[][] L = { { 136.0, 4.804, 10213.286 }, { 78.0, 3.67, 20426.57 }, { 26.0, 0.0, 0.0 } };
        double l3 = 0.0;
        for (int i = 0; i < 3; ++i) {
            l3 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return l3;
    }
    
    double L4(final double t) {
        final double[][] L = { { 114.0, 3.1416, 0.0 }, { 3.0, 5.21, 20426.57 }, { 2.0, 2.51, 10213.29 } };
        double l4 = 0.0;
        for (int i = 0; i < 3; ++i) {
            l4 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return l4;
    }
    
    double L5(final double t) {
        final double[][] L = { { 1.0, 3.14, 0.0 } };
        double l5 = 0.0;
        for (int i = 0; i < 1; ++i) {
            l5 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return l5;
    }
    
    double B0(final double t) {
        final double[][] L = { { 5923638.0, 0.2670278, 10213.2855462 }, { 40108.0, 1.14737, 20426.57109 }, { 32815.0, 3.14159, 0.0 }, { 1011.0, 1.0895, 30639.8566 }, { 149.0, 6.254, 18073.705 }, { 138.0, 0.86, 1577.344 }, { 130.0, 3.672, 9437.763 }, { 120.0, 3.705, 2352.866 }, { 108.0, 4.539, 22003.915 } };
        double b0 = 0.0;
        for (int i = 0; i < 9; ++i) {
            b0 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return b0;
    }
    
    double B1(final double t) {
        final double[][] L = { { 513348.0, 1.803643, 10213.285546 }, { 4380.0, 3.3862, 20426.5711 }, { 199.0, 0.0, 0.0 }, { 197.0, 2.53, 30639.857 } };
        double b1 = 0.0;
        for (int i = 0; i < 4; ++i) {
            b1 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return b1;
    }
    
    double B2(final double t) {
        final double[][] L = { { 22378.0, 3.38509, 10213.28555 }, { 282.0, 0.0, 0.0 }, { 173.0, 5.256, 20426.571 }, { 27.0, 3.87, 30639.86 } };
        double b2 = 0.0;
        for (int i = 0; i < 4; ++i) {
            b2 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return b2;
    }
    
    double B3(final double t) {
        final double[][] L = { { 647.0, 4.992, 10213.286 }, { 20.0, 3.14, 0.0 }, { 6.0, 0.77, 20426.57 }, { 3.0, 5.44, 30639.86 } };
        double b3 = 0.0;
        for (int i = 0; i < 4; ++i) {
            b3 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return b3;
    }
    
    double B4(final double t) {
        final double[][] L = { { 14.0, 0.32, 10213.29 } };
        double b4 = 0.0;
        for (int i = 0; i < 1; ++i) {
            b4 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return b4;
    }
    
    double R0(final double t) {
        final double[][] L = { { 7.2334821E7, 0.0, 0.0 }, { 489824.0, 4.021518, 10213.285546 }, { 1658.0, 4.9021, 20426.5711 }, { 1632.0, 2.8455, 7860.4194 }, { 1378.0, 1.1285, 11790.6291 }, { 498.0, 2.587, 9683.595 }, { 374.0, 1.423, 3930.21 }, { 264.0, 5.529, 9437.763 }, { 237.0, 2.551, 15720.839 }, { 222.0, 2.013, 19367.189 }, { 126.0, 2.728, 1577.344 }, { 119.0, 3.02, 10404.734 } };
        double r0 = 0.0;
        for (int i = 0; i < 12; ++i) {
            r0 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return r0;
    }
    
    double R1(final double t) {
        final double[][] L = { { 34551.0, 0.89199, 10213.28555 }, { 234.0, 1.772, 20426.571 }, { 234.0, 3.142, 0.0 } };
        double r1 = 0.0;
        for (int i = 0; i < 3; ++i) {
            r1 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return r1;
    }
    
    double R2(final double t) {
        final double[][] L = { { 1407.0, 5.0637, 10213.2855 }, { 16.0, 5.47, 20426.57 }, { 13.0, 0.0, 0.0 } };
        double r2 = 0.0;
        for (int i = 0; i < 3; ++i) {
            r2 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return r2;
    }
    
    double R3(final double t) {
        final double[][] L = { { 50.0, 3.22, 10213.29 } };
        double r3 = 0.0;
        for (int i = 0; i < 1; ++i) {
            r3 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return r3;
    }
    
    double R4(final double t) {
        final double[][] L = { { 1.0, 0.92, 10213.29 } };
        double r4 = 0.0;
        for (int i = 0; i < 1; ++i) {
            r4 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return r4;
    }
}
