// 
// Decompiled by Procyon v0.5.30
// 

class TideMath
{
    public double[] dHeights;
    private double METRES_FEET;
    private double dYear;
    private double d1980;
    private double as;
    private double ah;
    private double pg;
    private double nod;
    private double pdash;
    private double uO1;
    private double fO1;
    private double uK1;
    private double fK1;
    private double utau1;
    private double ftau1;
    private double uL2;
    private double fL2;
    private double uM2;
    private double fM2;
    private double uK2;
    private double fK2;
    private double EtwoQ1;
    private double Esigma1;
    private double EQ1;
    private double Erho1;
    private double EO1;
    private double Epi1;
    private double EP1;
    private double EK1;
    private double Ephi1;
    private double Etau1;
    private double EtwoN2;
    private double Emu2;
    private double EN2;
    private double Enu2;
    private double EM2;
    private double Elambda2;
    private double EL2;
    private double ET2;
    private double ES2;
    private double EK2;
    private double angO;
    private double facO;
    private double angK;
    private double facK;
    private double angM;
    private double facM;
    private double angS;
    private double facS;
    private double facM1;
    private double angM1;
    private double facS1;
    private double angS1;
    private double facK1;
    private double angK1;
    private double facO1;
    private double angO1;
    private double facM2;
    private double facS2;
    private double facK2;
    private double facO2;
    private double daM;
    private double daS;
    private double daK;
    private double daO;
    private double rsin_t;
    private double rcos_t;
    private double theta;
    private double r;
    private int nYr;
    private int nMn;
    private int nDy;
    private int nHr;
    private int nMi;
    private int nSe;
    private int nBiggest;
    private int nSmallest;
    private int nDp;
    private static boolean bMetric;
    private double[] Hcon;
    
    public TideMath(final double[] Cons, final boolean Metric, final int Decimals) {
        this.dHeights = new double[146];
        this.METRES_FEET = 3.2808;
        this.nDp = 1;
        System.arraycopy(Cons, 0, this.Hcon = new double[14], 0, 14);
        TideMath.bMetric = Metric;
        this.nDp = Decimals;
    }
    
    private double sind(final double x) {
        return Math.sin(3.141592653589793 * x / 180.0);
    }
    
    private double cosd(final double x) {
        return Math.cos(3.141592653589793 * x / 180.0);
    }
    
    private double tand(final double x) {
        return Math.tan(3.141592653589793 * x / 180.0);
    }
    
    private double dasn(final double x) {
        return 57.29577951308232 * Math.asin(x);
    }
    
    private double dacs(final double x) {
        return 57.29577951308232 * Math.acos(x);
    }
    
    private double datn(final double x) {
        return 57.29577951308232 * Math.atan(x);
    }
    
    private double dunw(final double x) {
        return x - Math.floor(x / 360.0) * 360.0;
    }
    
    public double TideHeight(final int yr, final int mn, final int dy, final int hr, final int mi, final int se) {
        this.nYr = yr;
        this.nMn = mn;
        this.nDy = dy;
        this.nHr = hr;
        this.nMi = mi;
        this.nSe = se;
        this.DayNumbers(this.nYr, this.nMn, this.nDy, 0, 0, 0);
        this.HarmonicSubs();
        return this.HarmonicMethod((this.nHr + this.nMi) / 60.0 + this.nSe / 3600.0);
    }
    
    private void DayNumbers(final int nYr, final int nMn, final int nDy, final int nHr, final int nMi, final int nSe) {
        final double yr = nYr;
        final double mn = nMn;
        final double dy = nDy;
        final double hr = nHr;
        final double min = nMi;
        final double sec = nSe;
        double y = 0.0;
        if (Math.floor(yr / 4.0) == yr / 4.0) {
            y = 1.0;
        }
        double b;
        if (mn > 2.0) {
            b = Math.floor(30.6 * (mn + 1.0)) - 63.0 + y;
        }
        else {
            b = Math.floor((63.0 - y) * (mn - 1.0) / 2.0);
        }
        b = b + dy + (hr + min / 60.0 + sec / 3600.0) / 24.0;
        final double l = Math.floor(365.25 * (yr - 1980.0));
        this.dYear = b;
        this.d1980 = l + 1.0 + b - y;
    }
    
    private void BiggestAndSmallest(final double[] ht, final double[] tm, final int count, final int miss_big, final int miss_sma) {
        double big = -100.0;
        double small = 100.0;
        tm[0] = 0.0;
        for (int a = 1; a <= count; ++a) {
            if (ht[a] > big && a != miss_big && (miss_big != 0 || Math.abs(tm[miss_big] - tm[a]) > 6.0)) {
                big = ht[a];
                this.nBiggest = a;
            }
            if (ht[a] < small && a != miss_sma && (miss_sma != 0 || Math.abs(tm[miss_big] - tm[a]) > 6.0)) {
                small = ht[a];
                this.nSmallest = a;
            }
        }
    }
    
    private void HarmonicSubs() {
        --this.dYear;
        this.Arguments();
        this.NodalCorrections();
        this.PhaseEplusU();
        this.AnglesAndFactors();
        final double ao1 = this.angO;
        final double ak1 = this.angK;
        final double am1 = this.angM;
        final double as1 = this.angS;
        this.facM1 = this.facM;
        this.facS1 = this.facS;
        this.facK1 = this.facK;
        this.facO1 = this.facO;
        ++this.dYear;
        this.Arguments();
        this.NodalCorrections();
        this.PhaseEplusU();
        this.AnglesAndFactors();
        this.facM2 = this.facM;
        this.facS2 = this.facS;
        this.facK2 = this.facK;
        this.facO2 = this.facO;
        double d;
        for (d = am1 - this.angM; d < 600.0; d += 360.0) {}
        this.daM = d;
        for (d = as1 - this.angS; d < 600.0; d += 360.0) {}
        this.daS = d;
        for (d = ak1 - this.angK; d < 300.0; d += 360.0) {}
        this.daK = d;
        for (d = ao1 - this.angO; d < 300.0; d += 360.0) {}
        this.daO = d;
        this.angM1 = am1;
        this.angS1 = as1;
        this.angK1 = ak1;
        this.angO1 = ao1;
    }
    
    private void Arguments() {
        final double Y = this.nYr - 1.0;
        final double M = 10.0;
        final double G = 38.0 - Math.floor(3.0 * Math.floor(49.0 + Y / 100.0) / 4.0);
        final double J = Math.floor(365.25 * (Y + 4712.0)) + Math.floor(30.6 * M + 0.5) + 59.0;
        final double d1;
        final double JulDayN = d1 = 1.0 + G + J - 0.5 - 2415020.0;
        final double d2 = d1 * 1.0E-4;
        this.ah = this.dunw(279.69668 + 0.9856473354 * d1 + 2.267E-5 * d2 * d2);
        this.pdash = this.dunw(281.220833 + 4.70684E-5 * d1 + 3.39E-5 * d2 * d2 + 7.0E-8 * d2 * d2 * d2);
        this.as = this.dunw(270.434164 + 13.1763965268 * d1 - 8.5E-5 * d2 * d2 + 3.9E-8 * d2 * d2 * d2);
        this.pg = this.dunw(334.329556 + 0.1114040803 * d1 - 7.739E-4 * d2 * d2 - 2.6E-7 * d2 * d2 * d2);
        this.nod = 360.0 - this.dunw(-259.183275 + 0.0529539222 * d1 - 1.557E-4 * d2 * d2 - 5.0E-8 * d2 * d2 * d2);
        final double sperday = 13.176396;
        final double hperday = 0.985647;
        final double pgperday = 0.111404;
        final double nodperday = -0.052954;
        final double pdashperday = 4.7E-5;
        this.as = this.dunw(this.as + sperday * this.dYear);
        this.ah = this.dunw(this.ah + hperday * this.dYear);
        this.pg = this.dunw(this.pg + pgperday * this.dYear);
        this.nod = this.dunw(this.nod + nodperday * this.dYear);
        this.pdash = this.dunw(this.pdash + pdashperday * this.dYear);
    }
    
    private void NodalCorrections() {
        this.uO1 = 10.8 * this.sind(this.nod) - 1.34 * this.sind(2.0 * this.nod) + 0.19 * this.sind(3.0 * this.nod);
        this.fO1 = 1.0089 + 0.1871 * this.cosd(this.nod) - 0.0147 * this.cosd(2.0 * this.nod) + 0.0014 * this.cosd(3.0 * this.nod);
        this.uK1 = -8.86 * this.sind(this.nod) + 0.68 * this.sind(2.0 * this.nod) - 0.07 * this.sind(3.0 * this.nod);
        this.utau1 = -12.94 * this.sind(this.nod) + 1.34 * this.sind(2.0 * this.nod) - 0.19 * this.sind(3.0 * this.nod);
        this.fK1 = 1.006 + 0.115 * this.cosd(this.nod) - 0.0088 * this.cosd(2.0 * this.nod) + 6.0E-4 * this.cosd(3.0 * this.nod);
        this.ftau1 = 1.0129 + 0.1676 * this.cosd(this.nod) - 0.017 * this.cosd(2.0 * this.nod) + 0.0016 * this.cosd(3.0 * this.nod);
        final double rsint = -0.2505 * this.sind(2.0 * this.pg) - 0.1102 * this.sind(2.0 * this.pg - this.nod) - 0.0156 * this.sind(2.0 * this.pg - 2.0 * this.nod) - 0.037 * this.sind(this.nod);
        final double rcost = 1.0 - 0.2505 * this.cosd(2.0 * this.pg) - 0.1102 * this.cosd(2.0 * this.pg - this.nod) - 0.0156 * this.cosd(2.0 * this.pg - 2.0 * this.nod) - 0.037 * this.cosd(this.nod);
        this.RecToPol(rsint, rcost);
        final double a = this.theta;
        final double b = this.r;
        this.uL2 = a;
        this.fL2 = b;
        this.uM2 = -2.14 * this.sind(this.nod);
        this.fM2 = 1.004 - 0.0373 * this.cosd(this.nod) + 2.0E-4 * this.cosd(2.0 * this.nod);
        this.uK2 = -17.74 * this.sind(this.nod) + 0.68 * this.sind(2.0 * this.nod) - 0.04 * this.sind(3.0 * this.nod);
        this.fK2 = 1.0246 + 0.2863 * this.cosd(this.nod) + 0.0083 * this.cosd(2.0 * this.nod) - 0.0015 * this.cosd(3.0 * this.nod);
    }
    
    private void PhaseEplusU() {
        this.EtwoQ1 = this.ah - 4.0 * this.as + 2.0 * this.pg - 90.0 + this.uO1;
        this.Esigma1 = 3.0 * this.ah - 4.0 * this.as - 90.0 + this.uO1;
        this.EQ1 = this.ah - 3.0 * this.as + this.pg - 90.0 + this.uO1;
        this.Erho1 = 3.0 * this.ah - 3.0 * this.as - this.pg - 90.0 + this.uO1;
        this.EO1 = this.ah - 2.0 * this.as - 90.0 + this.uO1;
        this.Epi1 = -2.0 * this.ah + this.pdash - 90.0;
        this.EP1 = -this.ah - 90.0;
        this.EK1 = this.ah + 90.0 + this.uK1;
        this.Ephi1 = 3.0 * this.ah + 90.0;
        this.Etau1 = this.ah + this.as - this.pg + 90.0 + this.utau1;
        this.EtwoN2 = 2.0 * this.ah - 4.0 * this.as + 2.0 * this.pg + this.uM2;
        this.Emu2 = 4.0 * this.ah - 4.0 * this.as + this.uM2;
        this.EN2 = 2.0 * this.ah - 3.0 * this.as + this.pg + this.uM2;
        this.Enu2 = 4.0 * this.ah - 3.0 * this.as - this.pg + this.uM2;
        this.EM2 = 2.0 * this.ah - 2.0 * this.as + this.uM2;
        this.Elambda2 = -this.as + this.pg + 180.0 + this.uM2;
        this.EL2 = 2.0 * this.ah - this.as - this.pg + 180.0 + this.uL2;
        this.ET2 = -this.ah + this.pdash;
        this.ES2 = 0.0;
        this.EK2 = 2.0 * this.ah + this.uK2;
    }
    
    private void AnglesAndFactors() {
        for (int a = 1; a <= 5; ++a) {
            if (a == 1) {
                this.PolToRec(this.fO1 * 0.02534, this.EtwoQ1);
                double y = this.rsin_t;
                double x = this.rcos_t;
                this.PolToRec(this.fO1 * 0.03056, this.Esigma1);
                x += this.rcos_t;
                y += this.rsin_t;
                this.PolToRec(this.fO1 * 0.1946, this.EQ1);
                x += this.rcos_t;
                y += this.rsin_t;
                this.PolToRec(this.fO1 * 0.03637, this.Erho1);
                x += this.rcos_t;
                y += this.rsin_t;
                this.PolToRec(this.fO1, this.EO1);
                this.RecToPol(this.rsin_t + y, this.rcos_t + x);
                final double z = this.r;
                this.facO = z;
                this.angO = this.dunw(360.0 - this.theta);
            }
            if (a == 2) {
                double z = 0.01939;
                this.PolToRec(z, this.Epi1);
                double y = this.rsin_t;
                double x = this.rcos_t;
                z = 0.33093;
                this.PolToRec(z, this.EP1);
                x += this.rcos_t;
                y += this.rsin_t;
                this.PolToRec(this.fK1, this.EK1);
                x += this.rcos_t;
                y += this.rsin_t;
                z = 0.01424;
                this.PolToRec(z, this.Ephi1);
                x += this.rcos_t;
                y += this.rsin_t;
                this.PolToRec(this.ftau1 * 0.05591, this.Etau1);
                this.RecToPol(this.rsin_t + y, this.rcos_t + x);
                z = this.r;
                this.facK = z;
                this.angK = this.dunw(360.0 - this.theta);
            }
            if (a == 3) {
                this.PolToRec(this.fM2 * 0.02534, this.EtwoN2 + 30.0);
                double y = this.rsin_t;
                double x = this.rcos_t;
                this.PolToRec(this.fM2 * 0.03057, this.Emu2 + 30.0);
                x += this.rcos_t;
                y += this.rsin_t;
                this.PolToRec(this.fM2 * 0.19146, this.EN2 + 15.0);
                x += this.rcos_t;
                y += this.rsin_t;
                this.PolToRec(this.fM2 * 0.03636, this.Enu2 + 15.0);
                x += this.rcos_t;
                y += this.rsin_t;
                this.PolToRec(this.fM2, this.EM2);
                x += this.rcos_t;
                y += this.rsin_t;
                this.PolToRec(this.fM2 * 0.00738, this.Elambda2 - 15.0);
                x += this.rcos_t;
                y += this.rsin_t;
                this.PolToRec(this.fL2 * 0.02827, this.EL2 - 15.0);
                this.RecToPol(this.rsin_t + y, this.rcos_t + x);
                final double z = this.r;
                this.facM = z;
                this.angM = this.dunw(360.0 - this.theta);
            }
            if (a == 4) {
                this.PolToRec(0.05861, this.ET2);
                double y = this.rsin_t;
                double x = this.rcos_t;
                double z = 1.0;
                this.PolToRec(z, this.ES2);
                x += this.rcos_t;
                y += this.rsin_t;
                this.PolToRec(this.fK2 * 0.27215, this.EK2);
                this.RecToPol(this.rsin_t + y, this.rcos_t + x);
                z = this.r;
                this.facS = z;
                this.angS = this.dunw(360.0 - this.theta);
            }
        }
    }
    
    private double HarmonicMethod(final double thour) {
        final double tday = thour / 24.0;
        final double MFt = this.facM1 + (this.facM2 - this.facM1) * tday;
        final double SFt = this.facS1 + (this.facS2 - this.facS1) * tday;
        final double KFt = this.facK1 + (this.facK2 - this.facK1) * tday;
        final double OFt = this.facO1 + (this.facO2 - this.facO1) * tday;
        final double Kcoscom = this.Hcon[6] * KFt * this.cosd(this.angK1 + this.Hcon[5] - this.daK * tday);
        final double Ocoscom = this.Hcon[8] * OFt * this.cosd(this.angO1 + this.Hcon[7] - this.daO * tday);
        this.rcos_t = this.Hcon[2] * MFt * this.cosd(this.angM1 + this.Hcon[1] - this.daM * tday) + this.Hcon[4] * SFt * this.cosd(this.angS1 + this.Hcon[3] - this.daS * tday);
        this.RecToPol(this.rsin_t = this.Hcon[2] * MFt * this.sind(this.angM1 + this.Hcon[1] - this.daM * tday) + this.Hcon[4] * SFt * this.sind(this.angS1 + this.Hcon[3] - this.daS * tday), this.rcos_t);
        final double D4com = this.r * this.r * this.Hcon[10] * this.cosd(2.0 * this.theta + this.Hcon[9]);
        final double D6com = this.r * this.r * this.r * this.Hcon[12] * this.cosd(3.0 * this.theta + this.Hcon[11]);
        if (!TideMath.bMetric) {
            return (Kcoscom + Ocoscom + this.rcos_t + D4com + D6com + this.Hcon[0] + this.Hcon[13]) * this.METRES_FEET;
        }
        return Kcoscom + Ocoscom + this.rcos_t + D4com + D6com + this.Hcon[0] + this.Hcon[13];
    }
    
    private void PolToRec(final double r_, final double theta_) {
        this.rsin_t = r_ * this.sind(theta_);
        this.rcos_t = r_ * this.cosd(theta_);
    }
    
    private void RecToPol(final double Rsin_t, double Rcos_t) {
        if (Rcos_t == 0.0) {
            Rcos_t = 1.0E-7;
        }
        double t = this.datn(Rsin_t / Rcos_t);
        if (Rcos_t < 0.0) {
            t += 180.0;
        }
        this.theta = t;
        this.r = Rsin_t / this.sind(t);
    }
    
    static {
        TideMath.bMetric = true;
    }
}
