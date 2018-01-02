// 
// Decompiled by Procyon v0.5.30
// 

class Sun
{
    static final double P2 = 6.283185307179586;
    static final double K = 0.017453292519943295;
    double LAT;
    double LONG;
    double T;
    double UT;
    int date;
    int month;
    int year;
    int hours;
    int minutes;
    int seconds;
    double locOffset;
    compute comp;
    boolean ABOVE;
    boolean RISE;
    boolean SETT;
    double h_Rise;
    double h_Set;
    int what;
    double YE;
    double zero1;
    double zero2;
    double UTRISE;
    double UTSET;
    int NZ;
    double Y0;
    double yPlus;
    double yMinus;
    double DX;
    double sh;
    double Jd;
    
    public Sun(final double jd, final double myLat, final double myLong, final double myLocOffset, final int myWhat) {
        this.sh = 0.0;
        this.what = myWhat;
        this.LAT = myLat;
        this.LONG = myLong;
        this.Jd = jd;
        this.comp = new compute(jd, this.LAT, this.LONG, (int)myLocOffset);
        this.locOffset = myLocOffset;
        this.T = (jd - 2451545.0) / 36525.0;
        switch (this.what) {
            case 0: {
                this.sh = Math.sin(-0.014543828656868749);
                break;
            }
            case 1: {
                this.sh = Math.sin(0.017453292519943295);
                break;
            }
            case 2: {
                this.sh = Math.sin(0.03490658503988659);
                break;
            }
            case 3: {
                this.sh = Math.sin(0.05235987755982989);
                break;
            }
            case 4: {
                this.sh = Math.sin(0.06981317007977318);
                break;
            }
            case 5: {
                this.sh = Math.sin(0.08726646259971647);
                break;
            }
        }
    }
    
    public void riseset(final double jd, final int HOUR) {
        this.comp = new compute(jd, this.LAT, this.LONG, (int)this.locOffset);
        double dec = this.comp.sunDecRA(1, jd);
        double ra = this.comp.sunDecRA(2, jd);
        this.Y0 = this.comp.sin_elev(jd, this.LAT, -this.LONG, dec, ra) - this.sh;
        final double jdPlus = jd + 0.041666666666666664;
        dec = this.comp.sunDecRA(1, jdPlus);
        ra = this.comp.sunDecRA(2, jdPlus);
        this.yPlus = this.comp.sin_elev(jdPlus, this.LAT, -this.LONG, dec, ra) - this.sh;
        final double jdMinus = jd - 0.041666666666666664;
        dec = this.comp.sunDecRA(1, jdMinus);
        ra = this.comp.sunDecRA(2, jdMinus);
        this.yMinus = this.comp.sin_elev(jdMinus, this.LAT, -this.LONG, dec, ra) - this.sh;
        this.ABOVE = (this.yMinus > 0.0);
        this.QUAD();
        switch (this.NZ) {
            case 1: {
                if (this.yMinus < 0.0) {
                    this.UTRISE = HOUR + this.zero1;
                    this.RISE = true;
                    break;
                }
                this.UTSET = HOUR + this.zero1;
                this.SETT = true;
                break;
            }
            case 2: {
                if (this.YE < 0.0) {
                    this.UTRISE = HOUR + this.zero2;
                    this.UTSET = HOUR + this.zero1;
                }
                else {
                    this.UTRISE = HOUR + this.zero1;
                    this.UTSET = HOUR + this.zero2;
                }
                this.RISE = true;
                this.SETT = true;
                break;
            }
        }
    }
    
    public void QUAD() {
        this.NZ = 0;
        final double A = 0.5 * (this.yMinus + this.yPlus) - this.Y0;
        final double B = 0.5 * (this.yPlus - this.yMinus);
        final double C = this.Y0;
        final double XE = -B / (2.0 * A);
        this.YE = (A * XE + B) * XE + C;
        final double DIS = B * B - 4.0 * A * C;
        if (DIS >= 0.0) {
            this.DX = 0.5 * Math.sqrt(DIS) / Math.abs(A);
            this.zero1 = XE - this.DX;
            this.zero2 = XE + this.DX;
            if (Math.abs(this.zero1) <= 1.0) {
                ++this.NZ;
            }
            if (Math.abs(this.zero2) <= 1.0) {
                ++this.NZ;
            }
            if (this.zero1 < -1.0) {
                this.zero1 = this.zero2;
            }
        }
    }
    
    public void computeRise(final double jd) {
        double hRise = 0.0;
        this.RISE = false;
        for (int i = -(int)this.locOffset; i < -(int)this.locOffset + 24; ++i) {
            this.riseset(jd, i);
            if (this.RISE) {
                break;
            }
        }
        if (this.RISE) {
            hRise = this.UTRISE + this.locOffset;
            if (hRise > 24.0 || hRise < 0.0) {
                this.RISE = false;
            }
            else {
                this.h_Rise = hRise;
            }
        }
    }
    
    public void computeSet(final double jd) {
        double hSet = 0.0;
        this.SETT = false;
        for (int i = -(int)this.locOffset; i < -(int)this.locOffset + 24; ++i) {
            this.riseset(jd, i);
            if (this.SETT) {
                break;
            }
        }
        if (this.SETT) {
            hSet = this.UTSET + this.locOffset;
            if (hSet > 24.0 || hSet < 0.0) {
                this.SETT = false;
            }
            else {
                this.h_Set = hSet;
            }
        }
    }
    
    public double h_Set() {
        return this.h_Set;
    }
    
    public double h_Rise() {
        return this.h_Rise;
    }
    
    public boolean riseOK() {
        return this.RISE;
    }
    
    public boolean setOK() {
        return this.SETT;
    }
}
