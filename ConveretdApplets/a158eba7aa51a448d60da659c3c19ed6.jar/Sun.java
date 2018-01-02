import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

class Sun
{
    static final double P2 = 6.283185307179586;
    static final double K = 0.017453292519943295;
    double RA;
    double DEC;
    double LAT;
    double LONG;
    double T;
    double declin;
    double GMST;
    double MJD;
    double UT;
    int date;
    int month;
    int year;
    int hours;
    int minutes;
    int seconds;
    double offset;
    double locOffset;
    double utRise;
    double utSet;
    Date dat;
    boolean moonRiseOK;
    boolean moonSetOK;
    compute comp;
    boolean ABOVE;
    boolean RISE;
    boolean SETT;
    double h_Rise;
    double h_Set;
    String riseStr;
    String setStr;
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
    
    public Sun(final Date myDate, final double myLat, final double myLong, final double myLocOffset) {
        this.moonRiseOK = true;
        this.moonSetOK = true;
        this.riseStr = "?";
        this.setStr = "?";
        this.LAT = myLat;
        this.LONG = myLong;
        this.dat = myDate;
        this.offset = this.dat.getTimezoneOffset() / 60.0;
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth() + 1;
        this.year = this.dat.getYear() + 1900;
        this.hours = this.dat.getHours();
        this.minutes = this.dat.getMinutes();
        this.seconds = this.dat.getSeconds();
        this.comp = new compute(this.dat, this.LAT, this.LONG, (int)myLocOffset);
        this.locOffset = myLocOffset;
        this.UT = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        this.T = (this.comp.JD(this.date, this.month, this.year, this.UT) - 2451545.0) / 36525.0;
    }
    
    public void riseset(final int DATE, final int MONTH, final int YEAR, final double HOUR) {
        this.comp = new compute(this.dat, this.LAT, this.LONG, (int)this.locOffset);
        final double K = 0.017453292519943295;
        final double sh = Math.sin(-0.014543828656868749);
        final double JD = this.comp.JD(DATE, MONTH, YEAR, HOUR);
        double dec = this.comp.sunDecRA(1, JD);
        double ra = this.comp.sunDecRA(2, JD);
        this.Y0 = this.comp.sin_elev(JD, this.LAT, -this.LONG, dec, ra) - sh;
        final double jdPlus = this.comp.JD(DATE, MONTH, YEAR, HOUR + 1.0);
        dec = this.comp.sunDecRA(1, jdPlus);
        ra = this.comp.sunDecRA(2, jdPlus);
        this.yPlus = this.comp.sin_elev(jdPlus, this.LAT, -this.LONG, dec, ra) - sh;
        final double jdMinus = this.comp.JD(DATE, MONTH, YEAR, HOUR - 1.0);
        dec = this.comp.sunDecRA(1, jdMinus);
        ra = this.comp.sunDecRA(2, jdMinus);
        this.yMinus = this.comp.sin_elev(jdMinus, this.LAT, -this.LONG, dec, ra) - sh;
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
    
    public String riseString() {
        double hRise = 0.0;
        int hh = 0;
        this.RISE = false;
        for (int i = -(int)this.locOffset; i < -(int)this.locOffset + 24; ++i) {
            this.riseset(this.date, this.month, this.year, i);
            if (this.RISE) {
                break;
            }
        }
        if (this.RISE) {
            hRise = this.UTRISE + this.locOffset;
            if (hRise > 0.0 && hRise < 24.0) {
                hh = (int)hRise;
                final double diff = hRise - hh;
                int min = (int)Math.round(diff * 60.0);
                if (min == 60) {
                    min = 0;
                    ++hh;
                }
                String str;
                if (min > 9) {
                    str = ":";
                }
                else {
                    str = ":0";
                }
                str = hh + str + min;
                if (hh < 10) {
                    str = "0" + str;
                }
                this.riseStr = str;
            }
            else {
                this.riseStr = " --:--";
                this.moonRiseOK = false;
            }
        }
        else {
            this.riseStr = " --:--";
            this.moonRiseOK = false;
        }
        this.h_Rise = hRise;
        return this.riseStr;
    }
    
    public String setString() {
        double hSet = 0.0;
        int hh = 0;
        this.SETT = false;
        for (int i = -(int)this.locOffset; i < -(int)this.locOffset + 24; ++i) {
            this.riseset(this.date, this.month, this.year, i);
            if (this.SETT) {
                break;
            }
        }
        if (this.SETT) {
            hSet = this.UTSET + this.locOffset;
            if (hSet > 0.0 && hSet < 24.0) {
                hh = (int)hSet;
                final double diff = hSet - hh;
                int min = (int)Math.round(diff * 60.0);
                if (min == 60) {
                    min = 0;
                    ++hh;
                }
                String str;
                if (min > 9) {
                    str = ":";
                }
                else {
                    str = ":0";
                }
                str = hh + str + min;
                if (hh < 10) {
                    str = "0" + str;
                }
                this.setStr = str;
            }
            else {
                this.setStr = " --:--";
                this.moonSetOK = false;
            }
        }
        else {
            this.setStr = " --:--";
            this.moonSetOK = false;
        }
        this.h_Set = hSet;
        return this.setStr;
    }
    
    public double h_Set() {
        return this.h_Set;
    }
    
    public double h_Rise() {
        return this.h_Rise;
    }
}
