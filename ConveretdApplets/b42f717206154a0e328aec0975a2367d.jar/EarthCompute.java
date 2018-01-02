import java.util.Date;

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
    Date dat;
    int browserOffset;
    int locOffset;
    String timeString;
    int year;
    int month;
    int date;
    int hours;
    int minutes;
    int seconds;
    
    public double JD(final int date, int month, int year, final double STD) {
        double A = 10000.0 * year + 100.0 * month + date;
        if (month <= 2) {
            month += 12;
            --year;
        }
        double B;
        if (A <= 1.58210041E7) {
            B = -2 + (year + 4716) / 4 - 1179;
        }
        else {
            B = year / 400 - year / 100 + year / 4;
        }
        A = 365.0 * year - 679004.0;
        final double MJD = A + B + (int)(30.6001 * (month + 1)) + date + STD / 24.0;
        return MJD + 2400000.5;
    }
    
    double earthR() {
        return this.R;
    }
    
    public EarthCompute(final int date, final int month, final int year, final double UT) {
        this.LArray = new double[65][3];
        final double JDE = this.JD(date, month, year, UT);
        final double T = (JDE - 2451545.0) / 365250.0;
        this.R = (this.R0(T) + this.R1(T) * T + this.R2(T) * T * T + this.R3(T) * T * T * T + this.R4(T) * T * T * T * T) / 1.0E8;
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
