import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

class Rise_Set
{
    Date dat;
    int date;
    int month;
    int year;
    double latitude;
    double longitude;
    double Y0;
    double yPlus;
    double yMinus;
    double YE;
    double DX;
    int NZ;
    boolean RISE;
    boolean SETT;
    boolean ABOVE;
    double UTRISE;
    double UTSET;
    double hRise;
    double hSet;
    int locOffset;
    String riseStr;
    String setStr;
    double zero1;
    double zero2;
    double currentTime;
    int hours;
    int minutes;
    int seconds;
    double elev;
    compute comp;
    
    public Rise_Set(final Date myDat, final double myLat, final double myLong, final int locOffset, final double myElev) {
        this.elev = myElev;
        this.comp = new compute();
        this.date = myDat.getDate();
        this.month = myDat.getMonth();
        this.year = myDat.getYear();
        this.currentTime = this.hours + this.minutes / 60.0 + this.seconds / 3600.0;
        this.latitude = myLat;
        this.longitude = myLong;
        this.longitude = -myLong;
        this.RISE = false;
        this.SETT = false;
        for (int i = -locOffset; i < -locOffset + 24; ++i) {
            this.riseset(this.date, this.month + 1, this.year + 1900, i);
            if (this.RISE && this.SETT) {
                break;
            }
        }
        if (this.RISE || this.SETT) {
            if (this.RISE) {
                this.UTRISE += locOffset;
                if (this.UTRISE > 24.0) {
                    this.UTRISE -= 24.0;
                }
                if (this.UTRISE < 0.0) {
                    this.UTRISE += 24.0;
                }
            }
            if (this.SETT) {
                this.UTSET += locOffset;
                if (this.UTSET > 24.0) {
                    this.UTSET -= 24.0;
                }
                if (this.UTSET < 0.0) {
                    this.UTSET += 24.0;
                }
            }
            if (this.RISE) {
                this.hRise = this.UTRISE;
            }
            if (this.SETT) {
                this.hSet = this.UTSET;
            }
            if (this.RISE) {
                this.riseStr = this.comp.makeTimeString(this.hRise);
            }
            else if (this.ABOVE) {
                this.riseStr = "Visible all day";
                this.setStr = "Visible all day";
            }
            else {
                this.riseStr = "Invisible all day";
                this.setStr = "Invisible all day";
            }
            if (this.SETT) {
                this.setStr = this.comp.makeTimeString(this.hSet);
            }
            else if (this.ABOVE) {
                this.riseStr = "Visible all day";
                this.setStr = "Visible all day";
            }
            else {
                this.riseStr = "Invisible all day";
                this.setStr = "Invisible all day";
            }
        }
        else if (this.ABOVE) {
            this.riseStr = "Visible all day";
            this.setStr = "Visible all day";
        }
        else {
            this.riseStr = "Invisible all day";
            this.setStr = "Invisible all day";
        }
    }
    
    String rise_String() {
        return this.riseStr;
    }
    
    String set_String() {
        return this.setStr;
    }
    
    double h_rise() {
        return this.hRise;
    }
    
    double h_set() {
        return this.hSet;
    }
    
    public void riseset(final int date, final int month, final int year, final double HOUR) {
        final double K = 0.017453292519943295;
        final double sh = Math.sin(0.017453292519943295 * this.elev);
        final double JD = this.comp.Jul_Date(date, month, year, HOUR);
        double dec = this.sunDecRA(1, JD);
        double ra = this.sunDecRA(2, JD);
        this.Y0 = this.comp.sin_elev(JD, this.latitude, -this.longitude, dec, ra) - sh;
        final double jdPlus = this.comp.Jul_Date(date, month, year, HOUR + 1.0);
        dec = this.sunDecRA(1, jdPlus);
        ra = this.sunDecRA(2, jdPlus);
        this.yPlus = this.comp.sin_elev(jdPlus, this.latitude, -this.longitude, dec, ra) - sh;
        final double jdMinus = this.comp.Jul_Date(date, month, year, HOUR - 1.0);
        dec = this.sunDecRA(1, jdMinus);
        ra = this.sunDecRA(2, jdMinus);
        this.yMinus = this.comp.sin_elev(jdMinus, this.latitude, -this.longitude, dec, ra) - sh;
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
    
    double sunDecRA(final int what, final double JD) {
        final double PI2 = 6.283185307179586;
        final double cos_eps = 0.917482;
        final double sin_eps = 0.397778;
        final double T = (JD - 2451545.0) / 36525.0;
        final double M = 6.283185307179586 * this.comp.frac(0.993133 + 99.997361 * T);
        final double DL = 6893.0 * Math.sin(M) + 72.0 * Math.sin(2.0 * M);
        final double L = 6.283185307179586 * this.comp.frac(0.7859453 + M / 6.283185307179586 + (6191.2 * T + DL) / 1296000.0);
        final double SL = Math.sin(L);
        final double X = Math.cos(L);
        final double Y = 0.917482 * SL;
        final double Z = 0.397778 * SL;
        final double R = Math.sqrt(1.0 - Z * Z);
        final double DEC = 57.29577951308232 * Math.atan(Z / R);
        double RA = 7.639437268410976 * Math.atan(Y / (X + R));
        if (RA < 0.0) {
            RA += 24.0;
        }
        if (what == 1) {
            return DEC;
        }
        return RA;
    }
}
