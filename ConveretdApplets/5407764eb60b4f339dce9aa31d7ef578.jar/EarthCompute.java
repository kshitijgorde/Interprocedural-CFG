// 
// Decompiled by Procyon v0.5.30
// 

class EarthCompute
{
    final double K = 0.017453292519943295;
    double R;
    double L;
    double B;
    
    public EarthCompute(final double JD) {
        final double T = (JD - 2451545.0) / 365250.0;
        final double T2 = Math.pow(T, 2.0);
        final double T3 = Math.pow(T, 3.0);
        final double T4 = Math.pow(T, 4.0);
        final double T5 = Math.pow(T, 5.0);
        this.B = (this.B0(T) + this.B1(T) * T) / 1.0E8;
        this.L = (this.L0(T) + this.L1(T) * T + this.L2(T) * T2 + this.L3(T) * T3 + this.L4(T) * T4 + this.L5(T) * T5) / 1.0E8;
        this.R = (this.R0(T) + this.R1(T) * T + this.R2(T) * T2 + this.R3(T) * T3 + this.R4(T) * T4) / 1.0E8;
    }
    
    double R() {
        return this.R;
    }
    
    double heliocentricLambda() {
        return this.L / 0.017453292519943295 % 360.0;
    }
    
    double BETA() {
        return this.B / 0.017453292519943295 % 360.0;
    }
    
    double earthL() {
        return this.L;
    }
    
    double earthB() {
        return this.B;
    }
    
    double earthR() {
        return this.R;
    }
    
    double L0(final double t) {
        final double[][] L = { { 1.75347046E8, 0.0, 0.0 }, { 3341656.0, 4.6692568, 6283.07585 }, { 34894.0, 4.6261, 12566.1517 }, { 3497.0, 2.7441, 5753.3849 }, { 3418.0, 2.8289, 3.5231 }, { 3136.0, 3.6277, 77713.7715 }, { 2676.0, 4.4181, 7860.4194 }, { 2343.0, 6.1352, 3930.2097 }, { 1324.0, 0.7425, 11506.7698 }, { 1273.0, 2.0371, 529.691 }, { 1199.0, 1.1096, 1577.3435 }, { 990.0, 5.233, 5884.927 }, { 902.0, 2.045, 26.298 }, { 857.0, 3.508, 398.149 }, { 780.0, 1.179, 5223.694 }, { 753.0, 2.533, 5507.553 }, { 505.0, 4.583, 18849.228 }, { 492.0, 4.205, 775.523 }, { 357.0, 2.92, 0.067 }, { 317.0, 5.849, 11790.629 }, { 284.0, 1.899, 796.298 }, { 271.0, 0.315, 10977.079 }, { 243.0, 0.345, 5486.778 }, { 206.0, 4.806, 2544.314 }, { 205.0, 1.869, 5573.143 }, { 202.0, 2.458, 6069.777 }, { 156.0, 0.833, 213.299 }, { 132.0, 3.411, 2942.463 }, { 126.0, 1.083, 20.775 }, { 115.0, 0.645, 0.98 }, { 103.0, 0.636, 4694.003 }, { 102.0, 0.976, 15720.839 }, { 102.0, 4.267, 7.114 }, { 99.0, 6.21, 2146.17 }, { 98.0, 0.68, 155.42 }, { 86.0, 5.98, 161000.69 }, { 85.0, 1.3, 6275.96 }, { 85.0, 3.67, 71430.7 }, { 80.0, 1.81, 17260.15 }, { 79.0, 3.04, 12036.46 }, { 75.0, 1.76, 5088.63 }, { 74.0, 3.5, 3154.69 }, { 74.0, 4.68, 801.82 }, { 70.0, 0.83, 9437.76 }, { 62.0, 3.98, 8827.39 }, { 61.0, 1.82, 7084.9 }, { 57.0, 2.78, 6286.6 }, { 56.0, 4.39, 14143.5 }, { 56.0, 3.47, 6279.55 }, { 52.0, 0.19, 12139.55 }, { 52.0, 1.33, 1748.02 }, { 51.0, 0.28, 5856.48 }, { 49.0, 0.49, 1194.45 }, { 41.0, 5.37, 8429.24 }, { 41.0, 2.4, 19651.05 }, { 39.0, 6.17, 10447.39 }, { 37.0, 6.04, 10213.29 }, { 37.0, 2.57, 1059.38 }, { 36.0, 1.71, 2352.87 }, { 36.0, 1.78, 6812.77 }, { 33.0, 0.59, 17789.85 }, { 30.0, 0.44, 83996.85 }, { 30.0, 2.74, 1349.87 }, { 25.0, 3.16, 4690.48 } };
        double l0 = 0.0;
        for (int i = 0; i < 64; ++i) {
            l0 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return l0;
    }
    
    double L1(final double t) {
        final double[][] L = { { 6.28331966747E11, 0.0, 0.0 }, { 206059.0, 2.678235, 6283.07585 }, { 4303.0, 2.6351, 12566.1517 }, { 425.0, 1.59, 3.523 }, { 119.0, 5.796, 26.298 }, { 109.0, 2.966, 1577.344 }, { 93.0, 2.59, 18849.23 }, { 72.0, 1.14, 529.69 }, { 68.0, 1.87, 398.15 }, { 67.0, 4.41, 5507.55 }, { 59.0, 2.89, 5223.69 }, { 56.0, 2.17, 155.42 }, { 45.0, 0.4, 796.3 }, { 36.0, 0.47, 775.52 }, { 29.0, 2.65, 7.11 }, { 21.0, 5.34, 0.98 }, { 19.0, 1.85, 5486.78 }, { 19.0, 4.97, 213.3 }, { 17.0, 2.99, 6275.96 }, { 16.0, 0.03, 2544.31 }, { 16.0, 1.43, 2146.17 }, { 15.0, 1.21, 10977.08 }, { 12.0, 2.83, 1748.02 }, { 12.0, 3.26, 5088.63 }, { 12.0, 5.27, 1194.45 }, { 12.0, 2.08, 4694.0 }, { 11.0, 0.77, 553.57 }, { 10.0, 1.3, 6286.6 }, { 10.0, 4.24, 1349.87 }, { 9.0, 2.7, 242.73 }, { 9.0, 5.64, 951.72 }, { 8.0, 5.3, 2352.87 }, { 6.0, 2.65, 9437.76 }, { 6.0, 4.67, 4690.48 } };
        double l1 = 0.0;
        for (int i = 0; i < 34; ++i) {
            l1 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return l1;
    }
    
    double L2(final double t) {
        final double[][] L = { { 52919.0, 0.0, 0.0 }, { 8720.0, 1.0721, 6283.0758 }, { 309.0, 0.867, 12566.152 }, { 27.0, 0.05, 3.52 }, { 16.0, 5.19, 26.3 }, { 16.0, 3.68, 155.42 }, { 10.0, 0.76, 18849.23 }, { 9.0, 2.06, 77713.77 }, { 7.0, 0.83, 775.52 }, { 5.0, 4.66, 1577.34 }, { 4.0, 1.03, 7.11 }, { 4.0, 3.44, 5573.14 }, { 3.0, 5.14, 796.3 }, { 3.0, 6.05, 5507.55 }, { 3.0, 1.19, 242.73 }, { 3.0, 6.12, 529.69 }, { 3.0, 0.31, 398.15 }, { 3.0, 2.28, 553.57 }, { 2.0, 4.38, 5223.69 }, { 2.0, 3.75, 0.98 } };
        double l2 = 0.0;
        for (int i = 0; i < 20; ++i) {
            l2 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return l2;
    }
    
    double L3(final double t) {
        final double[][] L = { { 289.0, 5.844, 6283.076 }, { 35.0, 0.0, 0.0 }, { 17.0, 5.49, 12566.15 }, { 3.0, 5.2, 155.42 }, { 1.0, 4.72, 3.52 }, { 1.0, 5.3, 18849.23 }, { 1.0, 5.97, 242.73 } };
        double l3 = 0.0;
        for (int i = 0; i < 7; ++i) {
            l3 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return l3;
    }
    
    double L4(final double t) {
        final double[][] L = { { 114.0, 3.142, 0.0 }, { 8.0, 4.13, 6283.08 }, { 1.0, 3.84, 12566.15 } };
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
        final double[][] L = { { 280.0, 3.199, 84334.662 }, { 102.0, 5.422, 5507.553 }, { 80.0, 3.88, 5223.69 }, { 44.0, 3.7, 2352.87 }, { 32.0, 4.0, 1577.34 } };
        double b0 = 0.0;
        for (int i = 0; i < 5; ++i) {
            b0 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return b0;
    }
    
    double B1(final double t) {
        final double[][] L = { { 9.0, 3.9, 5507.55 }, { 6.0, 1.73, 5223.69 } };
        double b1 = 0.0;
        for (int i = 0; i < 2; ++i) {
            b1 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return b1;
    }
    
    double R0(final double t) {
        final double[][] L = { { 1.00013989E8, 0.0, 0.0 }, { 1670700.0, 3.0984635, 6283.07585 }, { 13956.0, 3.05525, 12566.1517 }, { 3084.0, 5.1985, 77713.7715 }, { 1628.0, 1.1739, 5753.3849 }, { 1576.0, 2.8469, 7860.4194 }, { 925.0, 5.453, 11506.77 }, { 542.0, 4.564, 3930.21 }, { 472.0, 3.661, 5884.927 }, { 346.0, 0.964, 5507.553 }, { 329.0, 5.9, 5223.694 }, { 307.0, 0.299, 5573.143 }, { 243.0, 4.273, 11790.629 }, { 212.0, 5.847, 1577.344 }, { 186.0, 5.022, 10977.079 }, { 175.0, 3.012, 18849.228 }, { 110.0, 5.055, 5486.778 }, { 98.0, 0.89, 6069.78 }, { 86.0, 5.69, 15720.84 }, { 86.0, 1.27, 161000.69 }, { 65.0, 0.27, 17260.15 }, { 63.0, 0.92, 529.69 }, { 57.0, 2.01, 83996.85 }, { 56.0, 5.24, 71430.7 }, { 49.0, 3.25, 2544.31 }, { 47.0, 2.58, 775.52 }, { 45.0, 5.54, 9437.76 }, { 43.0, 6.01, 6275.96 }, { 39.0, 5.36, 4694.0 }, { 38.0, 2.39, 8827.39 }, { 37.0, 0.83, 19651.05 }, { 37.0, 4.9, 12139.55 }, { 36.0, 1.67, 12036.46 }, { 35.0, 1.84, 2942.46 }, { 33.0, 0.24, 7084.9 }, { 32.0, 0.18, 5088.63 }, { 32.0, 1.78, 398.15 }, { 28.0, 1.21, 6286.6 }, { 28.0, 1.9, 6279.55 }, { 26.0, 4.59, 10447.39 } };
        double r0 = 0.0;
        for (int i = 0; i < 40; ++i) {
            r0 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return r0;
    }
    
    double R1(final double t) {
        final double[][] L = { { 103019.0, 1.10749, 6283.07585 }, { 1721.0, 1.0644, 12566.1517 }, { 702.0, 3.142, 0.0 }, { 32.0, 1.02, 18849.23 }, { 31.0, 2.84, 5507.55 }, { 25.0, 1.32, 5223.69 }, { 18.0, 1.42, 1577.34 }, { 10.0, 5.91, 10977.08 }, { 9.0, 1.42, 6275.96 }, { 9.0, 0.27, 5486.78 } };
        double r1 = 0.0;
        for (int i = 0; i < 10; ++i) {
            r1 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return r1;
    }
    
    double R2(final double t) {
        final double[][] L = { { 4359.0, 5.7846, 6283.0758 }, { 124.0, 5.579, 12566.152 }, { 12.0, 3.14, 0.0 }, { 9.0, 3.63, 77713.77 }, { 6.0, 1.87, 5573.14 }, { 3.0, 5.47, 18849.23 } };
        double r2 = 0.0;
        for (int i = 0; i < 6; ++i) {
            r2 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return r2;
    }
    
    double R3(final double t) {
        final double[][] L = { { 145.0, 4.273, 6283.076 }, { 7.0, 3.92, 12566.15 } };
        double r3 = 0.0;
        for (int i = 0; i < 2; ++i) {
            r3 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return r3;
    }
    
    double R4(final double t) {
        final double[][] L = { { 4.0, 2.56, 6283.08 } };
        double r4 = 0.0;
        for (int i = 0; i < 1; ++i) {
            r4 += L[i][0] * Math.cos(L[i][1] + L[i][2] * t);
        }
        return r4;
    }
}
