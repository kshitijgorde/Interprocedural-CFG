// 
// Decompiled by Procyon v0.5.30
// 

class EarthCompute
{
    final double K = 0.017453292519943295;
    double UT;
    double[][] LArray;
    double R;
    double L;
    double B;
    
    double earthL() {
        return this.L;
    }
    
    double earthB() {
        return this.B;
    }
    
    double earthR() {
        return this.R;
    }
    
    public EarthCompute(final double JD) {
        this.LArray = new double[65][3];
        final double T = (JD - 2451545.0) / 365250.0;
        this.B = (this.B0(T) + this.B1(T) * T) / 1.0E8;
        this.L = (this.L0(T) + this.L1(T) * T + this.L2(T) * T * T + this.L3(T) * T * T * T + this.L4(T) * T * T * T * T + this.L5(T) * T * T * T * T * T) / 1.0E8;
        this.R = (this.R0(T) + this.R1(T) * T + this.R2(T) * T * T + this.R3(T) * T * T * T + this.R4(T) * T * T * T * T) / 1.0E8;
    }
    
    double R() {
        return this.R;
    }
    
    double heliocentricLambda() {
        return this.L / 0.017453292519943295 % 360.0;
    }
    
    double L0(final double t) {
        this.LArray[1][0] = 1.75347046E8;
        this.LArray[1][1] = 0.0;
        this.LArray[1][2] = 0.0;
        this.LArray[2][0] = 3341656.0;
        this.LArray[2][1] = 4.6692568;
        this.LArray[2][2] = 6283.07585;
        this.LArray[3][0] = 34894.0;
        this.LArray[3][1] = 4.6261;
        this.LArray[3][2] = 12566.1517;
        this.LArray[4][0] = 3497.0;
        this.LArray[4][1] = 2.7441;
        this.LArray[4][2] = 5753.3849;
        this.LArray[5][0] = 3418.0;
        this.LArray[5][1] = 2.8289;
        this.LArray[5][2] = 3.5231;
        this.LArray[6][0] = 3136.0;
        this.LArray[6][1] = 3.6277;
        this.LArray[6][2] = 77713.7715;
        this.LArray[7][0] = 2676.0;
        this.LArray[7][1] = 4.4181;
        this.LArray[7][2] = 7860.4194;
        this.LArray[8][0] = 2343.0;
        this.LArray[8][1] = 6.1352;
        this.LArray[8][2] = 3930.2097;
        this.LArray[9][0] = 1324.0;
        this.LArray[9][1] = 0.7425;
        this.LArray[9][2] = 11506.7698;
        this.LArray[10][0] = 1273.0;
        this.LArray[10][1] = 2.0371;
        this.LArray[10][2] = 529.691;
        this.LArray[11][0] = 1199.0;
        this.LArray[11][1] = 1.1096;
        this.LArray[11][2] = 1577.3435;
        this.LArray[12][0] = 990.0;
        this.LArray[12][1] = 5.233;
        this.LArray[12][2] = 5884.927;
        this.LArray[13][0] = 902.0;
        this.LArray[13][1] = 2.045;
        this.LArray[13][2] = 26.298;
        this.LArray[14][0] = 857.0;
        this.LArray[14][1] = 3.508;
        this.LArray[14][2] = 398.149;
        this.LArray[15][0] = 780.0;
        this.LArray[15][1] = 1.179;
        this.LArray[15][2] = 5223.694;
        this.LArray[16][0] = 753.0;
        this.LArray[16][1] = 2.533;
        this.LArray[16][2] = 5507.553;
        this.LArray[17][0] = 505.0;
        this.LArray[17][1] = 4.583;
        this.LArray[17][2] = 18849.228;
        this.LArray[18][0] = 492.0;
        this.LArray[18][1] = 4.205;
        this.LArray[18][2] = 775.523;
        this.LArray[19][0] = 357.0;
        this.LArray[19][1] = 2.92;
        this.LArray[19][2] = 0.067;
        this.LArray[20][0] = 317.0;
        this.LArray[20][1] = 5.849;
        this.LArray[20][2] = 11790.629;
        this.LArray[21][0] = 284.0;
        this.LArray[21][1] = 1.899;
        this.LArray[21][2] = 796.298;
        this.LArray[22][0] = 271.0;
        this.LArray[22][1] = 0.315;
        this.LArray[22][2] = 10977.079;
        this.LArray[23][0] = 243.0;
        this.LArray[23][1] = 0.345;
        this.LArray[23][2] = 5486.778;
        this.LArray[24][0] = 206.0;
        this.LArray[24][1] = 4.806;
        this.LArray[24][2] = 2544.314;
        this.LArray[25][0] = 205.0;
        this.LArray[25][1] = 1.869;
        this.LArray[25][2] = 5573.143;
        this.LArray[26][0] = 202.0;
        this.LArray[26][1] = 2.458;
        this.LArray[26][2] = 6069.777;
        this.LArray[27][0] = 156.0;
        this.LArray[27][1] = 0.833;
        this.LArray[27][2] = 213.299;
        this.LArray[28][0] = 132.0;
        this.LArray[28][1] = 3.411;
        this.LArray[28][2] = 2942.463;
        this.LArray[29][0] = 126.0;
        this.LArray[29][1] = 1.083;
        this.LArray[29][2] = 20.775;
        this.LArray[30][0] = 115.0;
        this.LArray[30][1] = 0.645;
        this.LArray[30][2] = 0.98;
        this.LArray[31][0] = 103.0;
        this.LArray[31][1] = 0.636;
        this.LArray[31][2] = 4694.003;
        this.LArray[32][0] = 102.0;
        this.LArray[32][1] = 0.976;
        this.LArray[32][2] = 15720.839;
        this.LArray[33][0] = 102.0;
        this.LArray[33][1] = 4.267;
        this.LArray[33][2] = 7.114;
        this.LArray[34][0] = 99.0;
        this.LArray[34][1] = 6.21;
        this.LArray[34][2] = 2146.17;
        this.LArray[35][0] = 98.0;
        this.LArray[35][1] = 0.68;
        this.LArray[35][2] = 155.42;
        this.LArray[36][0] = 86.0;
        this.LArray[36][1] = 5.98;
        this.LArray[36][2] = 161000.69;
        this.LArray[37][0] = 85.0;
        this.LArray[37][1] = 1.3;
        this.LArray[37][2] = 6275.96;
        this.LArray[38][0] = 85.0;
        this.LArray[38][1] = 3.67;
        this.LArray[38][2] = 71430.7;
        this.LArray[39][0] = 80.0;
        this.LArray[39][1] = 1.81;
        this.LArray[39][2] = 17260.15;
        this.LArray[40][0] = 79.0;
        this.LArray[40][1] = 3.04;
        this.LArray[40][2] = 12036.46;
        this.LArray[41][0] = 75.0;
        this.LArray[41][1] = 1.76;
        this.LArray[41][2] = 5088.63;
        this.LArray[42][0] = 74.0;
        this.LArray[42][1] = 3.5;
        this.LArray[42][2] = 3154.69;
        this.LArray[43][0] = 74.0;
        this.LArray[43][1] = 4.68;
        this.LArray[43][2] = 801.82;
        this.LArray[44][0] = 70.0;
        this.LArray[44][1] = 0.83;
        this.LArray[44][2] = 9437.76;
        this.LArray[45][0] = 62.0;
        this.LArray[45][1] = 3.98;
        this.LArray[45][2] = 8827.39;
        this.LArray[46][0] = 61.0;
        this.LArray[46][1] = 1.82;
        this.LArray[46][2] = 7084.9;
        this.LArray[47][0] = 57.0;
        this.LArray[47][1] = 2.78;
        this.LArray[47][2] = 6286.6;
        this.LArray[48][0] = 56.0;
        this.LArray[48][1] = 4.39;
        this.LArray[48][2] = 14143.5;
        this.LArray[49][0] = 56.0;
        this.LArray[49][1] = 3.47;
        this.LArray[49][2] = 6279.55;
        this.LArray[50][0] = 52.0;
        this.LArray[50][1] = 0.19;
        this.LArray[50][2] = 12139.55;
        this.LArray[51][0] = 52.0;
        this.LArray[51][1] = 1.33;
        this.LArray[51][2] = 1748.02;
        this.LArray[52][0] = 51.0;
        this.LArray[52][1] = 0.28;
        this.LArray[52][2] = 5856.48;
        this.LArray[53][0] = 49.0;
        this.LArray[53][1] = 0.49;
        this.LArray[53][2] = 1194.45;
        this.LArray[54][0] = 41.0;
        this.LArray[54][1] = 5.37;
        this.LArray[54][2] = 8429.24;
        this.LArray[55][0] = 41.0;
        this.LArray[55][1] = 2.4;
        this.LArray[55][2] = 19651.05;
        this.LArray[56][0] = 39.0;
        this.LArray[56][1] = 6.17;
        this.LArray[56][2] = 10447.39;
        this.LArray[57][0] = 37.0;
        this.LArray[57][1] = 6.04;
        this.LArray[57][2] = 10213.29;
        this.LArray[58][0] = 37.0;
        this.LArray[58][1] = 2.57;
        this.LArray[58][2] = 1059.38;
        this.LArray[59][0] = 36.0;
        this.LArray[59][1] = 1.71;
        this.LArray[59][2] = 2352.87;
        this.LArray[60][0] = 36.0;
        this.LArray[60][1] = 1.78;
        this.LArray[60][2] = 6812.77;
        this.LArray[61][0] = 33.0;
        this.LArray[61][1] = 0.59;
        this.LArray[61][2] = 17789.85;
        this.LArray[62][0] = 30.0;
        this.LArray[62][1] = 0.44;
        this.LArray[62][2] = 83996.85;
        this.LArray[63][0] = 30.0;
        this.LArray[63][1] = 2.74;
        this.LArray[63][2] = 1349.87;
        this.LArray[64][0] = 25.0;
        this.LArray[64][1] = 3.16;
        this.LArray[64][2] = 4690.48;
        double l0 = 0.0;
        for (int i = 1; i < 65; ++i) {
            l0 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
        }
        return l0;
    }
    
    double L1(final double t) {
        this.LArray[1][0] = 6.28331966747E11;
        this.LArray[1][1] = 0.0;
        this.LArray[1][2] = 0.0;
        this.LArray[2][0] = 206059.0;
        this.LArray[2][1] = 2.678235;
        this.LArray[2][2] = 6283.07585;
        this.LArray[3][0] = 4303.0;
        this.LArray[3][1] = 2.6351;
        this.LArray[3][2] = 12566.1517;
        this.LArray[4][0] = 425.0;
        this.LArray[4][1] = 1.59;
        this.LArray[4][2] = 3.523;
        this.LArray[5][0] = 119.0;
        this.LArray[5][1] = 5.796;
        this.LArray[5][2] = 26.298;
        this.LArray[6][0] = 109.0;
        this.LArray[6][1] = 2.966;
        this.LArray[6][2] = 1577.344;
        this.LArray[7][0] = 93.0;
        this.LArray[7][1] = 2.59;
        this.LArray[7][2] = 18849.23;
        this.LArray[8][0] = 72.0;
        this.LArray[8][1] = 1.14;
        this.LArray[8][2] = 529.69;
        this.LArray[9][0] = 68.0;
        this.LArray[9][1] = 1.87;
        this.LArray[9][2] = 398.15;
        this.LArray[10][0] = 67.0;
        this.LArray[10][1] = 4.41;
        this.LArray[10][2] = 5507.55;
        this.LArray[11][0] = 59.0;
        this.LArray[11][1] = 2.89;
        this.LArray[11][2] = 5223.69;
        this.LArray[12][0] = 56.0;
        this.LArray[12][1] = 2.17;
        this.LArray[12][2] = 155.42;
        this.LArray[13][0] = 45.0;
        this.LArray[13][1] = 0.4;
        this.LArray[13][2] = 796.3;
        this.LArray[14][0] = 36.0;
        this.LArray[14][1] = 0.47;
        this.LArray[14][2] = 775.52;
        this.LArray[15][0] = 29.0;
        this.LArray[15][1] = 2.65;
        this.LArray[15][2] = 7.11;
        this.LArray[16][0] = 21.0;
        this.LArray[16][1] = 5.34;
        this.LArray[16][2] = 0.98;
        this.LArray[17][0] = 19.0;
        this.LArray[17][1] = 1.85;
        this.LArray[17][2] = 5486.78;
        this.LArray[18][0] = 19.0;
        this.LArray[18][1] = 4.97;
        this.LArray[18][2] = 213.3;
        this.LArray[19][0] = 17.0;
        this.LArray[19][1] = 2.99;
        this.LArray[19][2] = 6275.96;
        this.LArray[20][0] = 16.0;
        this.LArray[20][1] = 0.03;
        this.LArray[20][2] = 2544.31;
        this.LArray[21][0] = 16.0;
        this.LArray[21][1] = 1.43;
        this.LArray[21][2] = 2146.17;
        this.LArray[22][0] = 15.0;
        this.LArray[22][1] = 1.21;
        this.LArray[22][2] = 10977.08;
        this.LArray[23][0] = 12.0;
        this.LArray[23][1] = 2.83;
        this.LArray[23][2] = 1748.02;
        this.LArray[24][0] = 12.0;
        this.LArray[24][1] = 3.26;
        this.LArray[24][2] = 5088.63;
        this.LArray[25][0] = 12.0;
        this.LArray[25][1] = 5.27;
        this.LArray[25][2] = 1194.45;
        this.LArray[26][0] = 12.0;
        this.LArray[26][1] = 2.08;
        this.LArray[26][2] = 4694.0;
        this.LArray[27][0] = 11.0;
        this.LArray[27][1] = 0.77;
        this.LArray[27][2] = 553.57;
        this.LArray[28][0] = 10.0;
        this.LArray[28][1] = 1.3;
        this.LArray[28][2] = 6286.6;
        this.LArray[29][0] = 10.0;
        this.LArray[29][1] = 4.24;
        this.LArray[29][2] = 1349.87;
        this.LArray[30][0] = 9.0;
        this.LArray[30][1] = 2.7;
        this.LArray[30][2] = 242.73;
        this.LArray[31][0] = 9.0;
        this.LArray[31][1] = 5.64;
        this.LArray[31][2] = 951.72;
        this.LArray[32][0] = 8.0;
        this.LArray[32][1] = 5.3;
        this.LArray[32][2] = 2352.87;
        this.LArray[33][0] = 6.0;
        this.LArray[33][1] = 2.65;
        this.LArray[33][2] = 9437.76;
        this.LArray[34][0] = 6.0;
        this.LArray[34][1] = 4.67;
        this.LArray[34][2] = 4690.48;
        double l1 = 0.0;
        for (int i = 1; i < 35; ++i) {
            l1 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
        }
        return l1;
    }
    
    double L2(final double t) {
        this.LArray[1][0] = 52919.0;
        this.LArray[1][1] = 0.0;
        this.LArray[1][2] = 0.0;
        this.LArray[2][0] = 8720.0;
        this.LArray[2][1] = 1.0721;
        this.LArray[2][2] = 6283.0758;
        this.LArray[3][0] = 309.0;
        this.LArray[3][1] = 0.867;
        this.LArray[3][2] = 12566.152;
        this.LArray[4][0] = 27.0;
        this.LArray[4][1] = 0.05;
        this.LArray[4][2] = 3.52;
        this.LArray[5][0] = 16.0;
        this.LArray[5][1] = 5.19;
        this.LArray[5][2] = 26.3;
        this.LArray[6][0] = 16.0;
        this.LArray[6][1] = 3.68;
        this.LArray[6][2] = 155.42;
        this.LArray[7][0] = 10.0;
        this.LArray[7][1] = 0.76;
        this.LArray[7][2] = 18849.23;
        this.LArray[8][0] = 9.0;
        this.LArray[8][1] = 2.06;
        this.LArray[8][2] = 77713.77;
        this.LArray[9][0] = 7.0;
        this.LArray[9][1] = 0.83;
        this.LArray[9][2] = 775.52;
        this.LArray[10][0] = 5.0;
        this.LArray[10][1] = 4.66;
        this.LArray[10][2] = 1577.34;
        this.LArray[11][0] = 4.0;
        this.LArray[11][1] = 1.03;
        this.LArray[11][2] = 7.11;
        this.LArray[12][0] = 4.0;
        this.LArray[12][1] = 3.44;
        this.LArray[12][2] = 5573.14;
        this.LArray[13][0] = 3.0;
        this.LArray[13][1] = 5.14;
        this.LArray[13][2] = 796.3;
        this.LArray[14][0] = 3.0;
        this.LArray[14][1] = 6.05;
        this.LArray[14][2] = 5507.55;
        this.LArray[15][0] = 3.0;
        this.LArray[15][1] = 1.19;
        this.LArray[15][2] = 242.73;
        this.LArray[16][0] = 3.0;
        this.LArray[16][1] = 6.12;
        this.LArray[16][2] = 529.69;
        this.LArray[17][0] = 3.0;
        this.LArray[17][1] = 0.31;
        this.LArray[17][2] = 398.15;
        this.LArray[18][0] = 3.0;
        this.LArray[18][1] = 2.28;
        this.LArray[18][2] = 553.57;
        this.LArray[19][0] = 2.0;
        this.LArray[19][1] = 4.38;
        this.LArray[19][2] = 5223.69;
        this.LArray[20][0] = 2.0;
        this.LArray[20][1] = 3.75;
        this.LArray[20][2] = 0.98;
        double l2 = 0.0;
        for (int i = 1; i < 21; ++i) {
            l2 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
        }
        return l2;
    }
    
    double L3(final double t) {
        this.LArray[1][0] = 289.0;
        this.LArray[1][1] = 5.844;
        this.LArray[1][2] = 6283.076;
        this.LArray[2][0] = 35.0;
        this.LArray[2][1] = 0.0;
        this.LArray[2][2] = 0.0;
        this.LArray[3][0] = 17.0;
        this.LArray[3][1] = 5.49;
        this.LArray[3][2] = 12566.15;
        this.LArray[4][0] = 3.0;
        this.LArray[4][1] = 5.2;
        this.LArray[4][2] = 155.42;
        this.LArray[5][0] = 1.0;
        this.LArray[5][1] = 4.72;
        this.LArray[5][2] = 3.52;
        this.LArray[6][0] = 1.0;
        this.LArray[6][1] = 5.3;
        this.LArray[6][2] = 18849.23;
        this.LArray[7][0] = 1.0;
        this.LArray[7][1] = 5.97;
        this.LArray[7][2] = 242.73;
        double l3 = 0.0;
        for (int i = 1; i < 8; ++i) {
            l3 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
        }
        return l3;
    }
    
    double L4(final double t) {
        this.LArray[1][0] = 114.0;
        this.LArray[1][1] = 3.142;
        this.LArray[1][2] = 0.0;
        this.LArray[2][0] = 8.0;
        this.LArray[2][1] = 4.13;
        this.LArray[2][2] = 6283.08;
        this.LArray[3][0] = 1.0;
        this.LArray[3][1] = 3.84;
        this.LArray[3][2] = 12566.15;
        double l4 = 0.0;
        for (int i = 1; i < 4; ++i) {
            l4 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
        }
        return l4;
    }
    
    double L5(final double t) {
        this.LArray[1][0] = 1.0;
        this.LArray[1][1] = 3.14;
        this.LArray[1][2] = 0.0;
        double l5 = 0.0;
        for (int i = 1; i < 2; ++i) {
            l5 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
        }
        return l5;
    }
    
    double B0(final double t) {
        this.LArray[1][0] = 280.0;
        this.LArray[1][1] = 3.199;
        this.LArray[1][2] = 84334.662;
        this.LArray[2][0] = 102.0;
        this.LArray[2][1] = 5.422;
        this.LArray[2][2] = 5507.553;
        this.LArray[3][0] = 80.0;
        this.LArray[3][1] = 3.88;
        this.LArray[3][2] = 5223.69;
        this.LArray[4][0] = 44.0;
        this.LArray[4][1] = 3.7;
        this.LArray[4][2] = 2352.87;
        this.LArray[5][0] = 32.0;
        this.LArray[5][1] = 4.0;
        this.LArray[5][2] = 1577.34;
        double b0 = 0.0;
        for (int i = 1; i < 6; ++i) {
            b0 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
        }
        return b0;
    }
    
    double B1(final double t) {
        this.LArray[1][0] = 9.0;
        this.LArray[1][1] = 3.9;
        this.LArray[1][2] = 5507.55;
        this.LArray[2][0] = 6.0;
        this.LArray[2][1] = 1.73;
        this.LArray[2][2] = 5223.69;
        double b1 = 0.0;
        for (int i = 1; i < 3; ++i) {
            b1 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
        }
        return b1;
    }
    
    double R0(final double t) {
        this.LArray[1][0] = 1.00013989E8;
        this.LArray[1][1] = 0.0;
        this.LArray[1][2] = 0.0;
        this.LArray[2][0] = 1670700.0;
        this.LArray[2][1] = 3.0984635;
        this.LArray[2][2] = 6283.07585;
        this.LArray[3][0] = 13956.0;
        this.LArray[3][1] = 3.05525;
        this.LArray[3][2] = 12566.1517;
        this.LArray[4][0] = 3084.0;
        this.LArray[4][1] = 5.1985;
        this.LArray[4][2] = 77713.7715;
        this.LArray[5][0] = 1628.0;
        this.LArray[5][1] = 1.1739;
        this.LArray[5][2] = 5753.3849;
        this.LArray[6][0] = 1576.0;
        this.LArray[6][1] = 2.8469;
        this.LArray[6][2] = 7860.4194;
        this.LArray[7][0] = 925.0;
        this.LArray[7][1] = 5.453;
        this.LArray[7][2] = 11506.77;
        this.LArray[8][0] = 542.0;
        this.LArray[8][1] = 4.564;
        this.LArray[8][2] = 3930.21;
        this.LArray[9][0] = 472.0;
        this.LArray[9][1] = 3.661;
        this.LArray[9][2] = 5884.927;
        this.LArray[10][0] = 346.0;
        this.LArray[10][1] = 0.964;
        this.LArray[10][2] = 5507.553;
        this.LArray[11][0] = 329.0;
        this.LArray[11][1] = 5.9;
        this.LArray[11][2] = 5223.694;
        this.LArray[12][0] = 307.0;
        this.LArray[12][1] = 0.299;
        this.LArray[12][2] = 5573.143;
        this.LArray[13][0] = 243.0;
        this.LArray[13][1] = 4.273;
        this.LArray[13][2] = 11790.629;
        this.LArray[14][0] = 212.0;
        this.LArray[14][1] = 5.847;
        this.LArray[14][2] = 1577.344;
        this.LArray[15][0] = 186.0;
        this.LArray[15][1] = 5.022;
        this.LArray[15][2] = 10977.079;
        this.LArray[16][0] = 175.0;
        this.LArray[16][1] = 3.012;
        this.LArray[16][2] = 18849.228;
        this.LArray[17][0] = 110.0;
        this.LArray[17][1] = 5.055;
        this.LArray[17][2] = 5486.778;
        this.LArray[18][0] = 98.0;
        this.LArray[18][1] = 0.89;
        this.LArray[18][2] = 6069.78;
        this.LArray[19][0] = 86.0;
        this.LArray[19][1] = 5.69;
        this.LArray[19][2] = 15720.84;
        this.LArray[20][0] = 86.0;
        this.LArray[20][1] = 1.27;
        this.LArray[20][2] = 161000.69;
        this.LArray[21][0] = 65.0;
        this.LArray[21][1] = 0.27;
        this.LArray[21][2] = 17260.15;
        this.LArray[22][0] = 63.0;
        this.LArray[22][1] = 0.92;
        this.LArray[22][2] = 529.69;
        this.LArray[23][0] = 57.0;
        this.LArray[23][1] = 2.01;
        this.LArray[23][2] = 83996.85;
        this.LArray[24][0] = 56.0;
        this.LArray[24][1] = 5.24;
        this.LArray[24][2] = 71430.7;
        this.LArray[25][0] = 49.0;
        this.LArray[25][1] = 3.25;
        this.LArray[25][2] = 2544.31;
        this.LArray[26][0] = 47.0;
        this.LArray[26][1] = 2.58;
        this.LArray[26][2] = 775.52;
        this.LArray[27][0] = 45.0;
        this.LArray[27][1] = 5.54;
        this.LArray[27][2] = 9437.76;
        this.LArray[28][0] = 43.0;
        this.LArray[28][1] = 6.01;
        this.LArray[28][2] = 6275.96;
        this.LArray[29][0] = 39.0;
        this.LArray[29][1] = 5.36;
        this.LArray[29][2] = 4694.0;
        this.LArray[30][0] = 38.0;
        this.LArray[30][1] = 2.39;
        this.LArray[30][2] = 8827.39;
        this.LArray[31][0] = 37.0;
        this.LArray[31][1] = 0.83;
        this.LArray[31][2] = 19651.05;
        this.LArray[32][0] = 37.0;
        this.LArray[32][1] = 4.9;
        this.LArray[32][2] = 12139.55;
        this.LArray[33][0] = 36.0;
        this.LArray[33][1] = 1.67;
        this.LArray[33][2] = 12036.46;
        this.LArray[34][0] = 35.0;
        this.LArray[34][1] = 1.84;
        this.LArray[34][2] = 2942.46;
        this.LArray[35][0] = 33.0;
        this.LArray[35][1] = 0.24;
        this.LArray[35][2] = 7084.9;
        this.LArray[36][0] = 32.0;
        this.LArray[36][1] = 0.18;
        this.LArray[36][2] = 5088.63;
        this.LArray[37][0] = 32.0;
        this.LArray[37][1] = 1.78;
        this.LArray[37][2] = 398.15;
        this.LArray[38][0] = 28.0;
        this.LArray[38][1] = 1.21;
        this.LArray[38][2] = 6286.6;
        this.LArray[39][0] = 28.0;
        this.LArray[39][1] = 1.9;
        this.LArray[39][2] = 6279.55;
        this.LArray[40][0] = 26.0;
        this.LArray[40][1] = 4.59;
        this.LArray[40][2] = 10447.39;
        double r0 = 0.0;
        for (int i = 1; i < 41; ++i) {
            r0 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
        }
        return r0;
    }
    
    double R1(final double t) {
        this.LArray[1][0] = 103019.0;
        this.LArray[1][1] = 1.10749;
        this.LArray[1][2] = 6283.07585;
        this.LArray[2][0] = 1721.0;
        this.LArray[2][1] = 1.0644;
        this.LArray[2][2] = 12566.1517;
        this.LArray[3][0] = 702.0;
        this.LArray[3][1] = 3.142;
        this.LArray[3][2] = 0.0;
        this.LArray[4][0] = 32.0;
        this.LArray[4][1] = 1.02;
        this.LArray[4][2] = 18849.23;
        this.LArray[5][0] = 31.0;
        this.LArray[5][1] = 2.84;
        this.LArray[5][2] = 5507.55;
        this.LArray[6][0] = 25.0;
        this.LArray[6][1] = 1.32;
        this.LArray[6][2] = 5223.69;
        this.LArray[7][0] = 18.0;
        this.LArray[7][1] = 1.42;
        this.LArray[7][2] = 1577.34;
        this.LArray[8][0] = 10.0;
        this.LArray[8][1] = 5.91;
        this.LArray[8][2] = 10977.08;
        this.LArray[9][0] = 9.0;
        this.LArray[9][1] = 1.42;
        this.LArray[9][2] = 6275.96;
        this.LArray[10][0] = 9.0;
        this.LArray[10][1] = 0.27;
        this.LArray[10][2] = 5486.78;
        double r1 = 0.0;
        for (int i = 1; i < 11; ++i) {
            r1 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
        }
        return r1;
    }
    
    double R2(final double t) {
        this.LArray[1][0] = 4359.0;
        this.LArray[1][1] = 5.7846;
        this.LArray[1][2] = 6283.0758;
        this.LArray[2][0] = 124.0;
        this.LArray[2][1] = 5.579;
        this.LArray[2][2] = 12566.152;
        this.LArray[3][0] = 12.0;
        this.LArray[3][1] = 3.14;
        this.LArray[3][2] = 0.0;
        this.LArray[4][0] = 9.0;
        this.LArray[4][1] = 3.63;
        this.LArray[4][2] = 77713.77;
        this.LArray[5][0] = 6.0;
        this.LArray[5][1] = 1.87;
        this.LArray[5][2] = 5573.14;
        this.LArray[6][0] = 3.0;
        this.LArray[6][1] = 5.47;
        this.LArray[6][2] = 18849.23;
        double r2 = 0.0;
        for (int i = 1; i < 7; ++i) {
            r2 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
        }
        return r2;
    }
    
    double R3(final double t) {
        this.LArray[1][0] = 145.0;
        this.LArray[1][1] = 4.273;
        this.LArray[1][2] = 6283.076;
        this.LArray[2][0] = 7.0;
        this.LArray[2][1] = 3.92;
        this.LArray[2][2] = 12566.15;
        double r3 = 0.0;
        for (int i = 1; i < 3; ++i) {
            r3 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
        }
        return r3;
    }
    
    double R4(final double t) {
        this.LArray[1][0] = 4.0;
        this.LArray[1][1] = 2.56;
        this.LArray[1][2] = 6283.08;
        double r4 = 0.0;
        for (int i = 1; i < 2; ++i) {
            r4 += this.LArray[i][0] * Math.cos(this.LArray[i][1] + this.LArray[i][2] * t);
        }
        return r4;
    }
}
