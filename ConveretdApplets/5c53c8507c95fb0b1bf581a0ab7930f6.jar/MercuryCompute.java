// 
// Decompiled by Procyon v0.5.30
// 

class MercuryCompute
{
    final double K = 0.017453292519943295;
    double L;
    double B;
    double R;
    double x;
    double y;
    double z;
    double lambda;
    double beta;
    double T;
    double eps;
    
    public MercuryCompute(final double JD) {
        this.T = (JD - 2451545.0) / 365250.0;
        this.eps = 0.017453292519943295 * (23.433333333333334 + (21.448 - 46.815 * this.T - 5.9E-4 * this.T * this.T + 0.001813 * this.T * this.T * this.T) / 3600.0);
        this.B = (this.B0(this.T) + this.B1(this.T) * this.T + this.B2(this.T) * this.T * this.T + this.B3(this.T) * this.T * this.T * this.T + this.B4(this.T) * this.T * this.T * this.T * this.T) / 1.0E8;
        this.L = (this.L0(this.T) + this.L1(this.T) * this.T + this.L2(this.T) * this.T * this.T + this.L3(this.T) * this.T * this.T * this.T + this.L4(this.T) * this.T * this.T * this.T * this.T + this.L5(this.T) * this.T * this.T * this.T * this.T * this.T) / 1.0E8;
        this.R = (this.R0(this.T) + this.R1(this.T) * this.T + this.R2(this.T) * this.T * this.T + this.R3(this.T) * this.T * this.T * this.T) / 1.0E8;
        final EarthCompute earthComp = new EarthCompute(JD);
        final double RE = earthComp.earthR();
        final double BE = earthComp.earthB();
        final double LE = earthComp.earthL();
        this.x = this.R * Math.cos(this.B) * Math.cos(this.L) - RE * Math.cos(BE) * Math.cos(LE);
        this.y = this.R * Math.cos(this.B) * Math.sin(this.L) - RE * Math.cos(BE) * Math.sin(LE);
        this.z = this.R * Math.sin(this.B) - RE * Math.sin(BE);
        final double tau = 0.0057755183 * Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        this.B = (this.B0(this.T) + this.B1(this.T) * this.T + this.B2(this.T) * this.T * this.T + this.B3(this.T) * this.T * this.T * this.T + this.B4(this.T) * this.T * this.T * this.T * this.T) / 1.0E8;
        this.L = (this.L0(this.T) + this.L1(this.T) * this.T + this.L2(this.T) * this.T * this.T + this.L3(this.T) * this.T * this.T * this.T + this.L4(this.T) * this.T * this.T * this.T * this.T + this.L5(this.T) * this.T * this.T * this.T * this.T * this.T) / 1.0E8;
        this.R = (this.R0(this.T) + this.R1(this.T) * this.T + this.R2(this.T) * this.T * this.T + this.R3(this.T) * this.T * this.T * this.T) / 1.0E8;
        this.x = this.R * Math.cos(this.B) * Math.cos(this.L) - RE * Math.cos(BE) * Math.cos(LE);
        this.y = this.R * Math.cos(this.B) * Math.sin(this.L) - RE * Math.cos(BE) * Math.sin(LE);
        this.z = this.R * Math.sin(this.B) - RE * Math.sin(BE);
        this.lambda = Math.atan2(this.y, this.x) / 0.017453292519943295;
        if (this.lambda < 0.0) {
            this.lambda += 360.0;
        }
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
        final double[][] L = { { 4.4025071E8, 0.0, 0.0 }, { 4.0989415E7, 1.48302034, 26087.90314157 }, { 5046294.0, 4.4778549, 52175.8062831 }, { 855347.0, 1.165203, 78263.709425 }, { 165590.0, 4.119692, 104351.612566 }, { 34562.0, 0.77931, 130439.51571 }, { 7583.0, 3.7135, 156527.4188 }, { 3560.0, 1.512, 1109.3786 }, { 1803.0, 4.1033, 5661.332 }, { 1726.0, 0.3583, 182615.322 }, { 1590.0, 2.9951, 25028.5212 }, { 1365.0, 4.5992, 27197.2817 }, { 1017.0, 0.8803, 31749.2352 }, { 714.0, 1.541, 24978.525 }, { 644.0, 5.303, 21535.95 }, { 451.0, 6.05, 51116.424 }, { 404.0, 3.282, 208703.225 }, { 352.0, 5.242, 20426.571 }, { 345.0, 2.792, 15874.618 }, { 343.0, 5.765, 955.6 }, { 339.0, 5.863, 25558.212 }, { 325.0, 1.337, 53285.185 }, { 273.0, 2.495, 529.691 }, { 264.0, 3.917, 57837.138 }, { 260.0, 0.987, 4551.953 }, { 239.0, 0.113, 1059.382 }, { 235.0, 0.267, 11322.664 }, { 217.0, 0.66, 13521.751 }, { 209.0, 2.092, 47623.853 }, { 183.0, 2.629, 27043.503 }, { 182.0, 2.434, 25661.305 }, { 176.0, 4.536, 51066.428 }, { 173.0, 2.452, 24498.83 }, { 142.0, 3.36, 37410.567 }, { 138.0, 0.291, 10213.286 }, { 125.0, 3.721, 39609.655 }, { 118.0, 2.781, 77204.327 }, { 106.0, 4.206, 19804.827 } };
        double l0 = 0.0;
        for (int i = 0; i < 38; ++i) {
            l0 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return l0;
    }
    
    double L1(final double t) {
        final double[][] L = { { 2.608814706223E12, 0.0, 0.0 }, { 1126008.0, 6.2170397, 26087.9031416 }, { 303471.0, 3.055655, 52175.806283 }, { 80538.0, 6.10455, 78263.70942 }, { 21245.0, 2.83532, 104351.61257 }, { 5592.0, 5.8268, 130439.5157 }, { 1472.0, 2.5185, 156527.4188 }, { 388.0, 5.48, 182615.322 }, { 352.0, 3.052, 1109.379 }, { 103.0, 2.149, 208703.225 }, { 94.0, 6.12, 27197.28 }, { 91.0, 0.0, 24978.52 }, { 52.0, 5.62, 5661.33 }, { 44.0, 4.57, 25028.52 }, { 28.0, 3.04, 51066.43 }, { 27.0, 5.09, 234791.13 } };
        double l1 = 0.0;
        for (int i = 0; i < 16; ++i) {
            l1 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return l1;
    }
    
    double L2(final double t) {
        final double[][] L = { { 53050.0, 0.0, 0.0 }, { 16904.0, 4.69072, 26087.90314 }, { 7397.0, 1.3474, 52175.8063 }, { 3018.0, 4.4564, 78263.7094 }, { 1107.0, 1.2623, 104351.6126 }, { 378.0, 4.32, 130439.516 }, { 123.0, 1.069, 156527.419 }, { 39.0, 4.08, 182615.32 }, { 15.0, 4.63, 1109.38 }, { 12.0, 0.79, 208703.23 } };
        double l2 = 0.0;
        for (int i = 0; i < 10; ++i) {
            l2 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return l2;
    }
    
    double L3(final double t) {
        final double[][] L = { { 188.0, 0.035, 52175.806 }, { 142.0, 3.125, 26087.903 }, { 97.0, 3.0, 78263.71 }, { 44.0, 6.02, 104351.61 }, { 35.0, 0.0, 0.0 }, { 18.0, 2.78, 130439.52 }, { 7.0, 5.82, 156527.42 }, { 3.0, 2.57, 182615.32 } };
        double l3 = 0.0;
        for (int i = 0; i < 8; ++i) {
            l3 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return l3;
    }
    
    double L4(final double t) {
        final double[][] L = { { 114.0, 3.1416, 0.0 }, { 3.0, 2.03, 26087.9 }, { 2.0, 1.42, 78263.71 }, { 2.0, 4.5, 52175.81 }, { 1.0, 4.5, 104351.61 }, { 1.0, 1.27, 130439.52 } };
        double l4 = 0.0;
        for (int i = 0; i < 6; ++i) {
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
        final double[][] L = { { 1.1737529E7, 1.98357499, 26087.90314157 }, { 2388077.0, 5.0373896, 52175.8062831 }, { 1222840.0, 3.1415927, 0.0 }, { 543252.0, 1.796444, 78263.709425 }, { 129779.0, 4.832325, 104351.612566 }, { 31867.0, 1.58088, 130439.51571 }, { 7963.0, 4.6097, 156527.4188 }, { 2014.0, 1.3532, 182615.322 }, { 514.0, 4.378, 208703.225 }, { 209.0, 2.02, 24978.525 }, { 208.0, 4.918, 27197.282 }, { 132.0, 1.119, 234791.128 }, { 121.0, 1.813, 53285.185 }, { 100.0, 5.657, 20426.517 } };
        double b0 = 0.0;
        for (int i = 0; i < 14; ++i) {
            b0 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return b0;
    }
    
    double B1(final double t) {
        final double[][] L = { { 429151.0, 3.501698, 26087.903142 }, { 146234.0, 3.141593, 0.0 }, { 22675.0, 0.01515, 52175.80628 }, { 10895.0, 0.4854, 78263.70942 }, { 6353.0, 3.4294, 104351.6126 }, { 2496.0, 0.1605, 130439.5157 }, { 860.0, 3.185, 156527.419 }, { 278.0, 6.21, 182615.322 }, { 86.0, 2.95, 208703.23 }, { 28.0, 0.29, 27197.28 }, { 26.0, 5.98, 234791.13 } };
        double b1 = 0.0;
        for (int i = 0; i < 11; ++i) {
            b1 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return b1;
    }
    
    double B2(final double t) {
        final double[][] L = { { 11831.0, 4.79066, 26087.90314 }, { 1914.0, 0.0, 0.0 }, { 1045.0, 1.2122, 52175.8063 }, { 266.0, 4.434, 78263.709 }, { 170.0, 1.623, 104351.613 }, { 96.0, 4.8, 130439.52 }, { 45.0, 1.61, 156527.42 }, { 18.0, 4.67, 182615.32 }, { 7.0, 1.43, 208703.23 } };
        double b2 = 0.0;
        for (int i = 0; i < 9; ++i) {
            b2 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return b2;
    }
    
    double B3(final double t) {
        final double[][] L = { { 235.0, 0.354, 26087.903 }, { 161.0, 0.0, 0.0 }, { 19.0, 4.36, 52175.81 }, { 6.0, 2.51, 78263.71 }, { 5.0, 6.14, 104351.61 }, { 3.0, 3.12, 130439.52 }, { 2.0, 6.27, 156527.42 } };
        double b3 = 0.0;
        for (int i = 0; i < 7; ++i) {
            b3 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return b3;
    }
    
    double B4(final double t) {
        final double[][] L = { { 4.0, 1.75, 26087.9 }, { 1.0, 3.14, 0.0 } };
        double b4 = 0.0;
        for (int i = 0; i < 2; ++i) {
            b4 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return b4;
    }
    
    double R0(final double t) {
        final double[][] L = { { 3.9528272E7, 0.0, 0.0 }, { 7834132.0, 6.1923372, 26087.9031416 }, { 795526.0, 2.959897, 52175.806283 }, { 121282.0, 6.010642, 78263.709425 }, { 21922.0, 2.7782, 104351.61257 }, { 4354.0, 5.8289, 130439.5157 }, { 918.0, 2.597, 156527.419 }, { 290.0, 1.424, 25028.521 }, { 260.0, 3.028, 27197.282 }, { 202.0, 5.647, 182615.322 }, { 201.0, 5.592, 31749.235 }, { 142.0, 6.253, 24978.525 }, { 100.0, 3.734, 21535.95 } };
        double r0 = 0.0;
        for (int i = 0; i < 13; ++i) {
            r0 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return r0;
    }
    
    double R1(final double t) {
        final double[][] L = { { 217348.0, 4.656172, 26087.903142 }, { 44142.0, 1.42386, 52175.80628 }, { 10094.0, 4.47466, 78263.70942 }, { 2433.0, 1.2423, 104351.6126 }, { 1624.0, 0.0, 0.0 }, { 604.0, 4.293, 130439.516 }, { 153.0, 1.061, 156527.419 }, { 39.0, 4.11, 182615.32 } };
        double r1 = 0.0;
        for (int i = 0; i < 8; ++i) {
            r1 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return r1;
    }
    
    double R2(final double t) {
        final double[][] L = { { 3118.0, 3.0823, 26087.9031 }, { 1245.0, 6.1518, 52175.8063 }, { 425.0, 2.926, 78263.709 }, { 136.0, 5.98, 104351.613 }, { 42.0, 2.75, 130439.52 }, { 22.0, 3.14, 0.0 }, { 13.0, 5.8, 156527.42 } };
        double r2 = 0.0;
        for (int i = 0; i < 7; ++i) {
            r2 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return r2;
    }
    
    double R3(final double t) {
        final double[][] L = { { 33.0, 1.68, 26087.9 }, { 24.0, 4.63, 52175.81 }, { 12.0, 1.39, 78263.71 }, { 5.0, 4.44, 104351.61 }, { 2.0, 1.21, 130439.52 } };
        double r3 = 0.0;
        for (int i = 0; i < 5; ++i) {
            r3 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return r3;
    }
}
