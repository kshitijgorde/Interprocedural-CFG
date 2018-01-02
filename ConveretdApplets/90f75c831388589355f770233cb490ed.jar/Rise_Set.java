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
    double zero1;
    double zero2;
    compute comp;
    final double K = 0.017453292519943295;
    
    public Rise_Set(final Date myDat, final double myLat, final double myLong) {
        this.comp = new compute();
        this.dat = myDat;
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth();
        this.year = this.dat.getYear();
        this.latitude = myLat;
        this.longitude = myLong;
        this.RISE = false;
        this.SETT = false;
        for (int i = 0; i < 24; ++i) {
            this.riseset(this.date, this.month + 1, this.year + 1900, i);
            if (this.RISE && this.SETT) {
                break;
            }
        }
        if (this.RISE || this.SETT) {
            if (this.RISE) {
                if (this.UTRISE > 24.0) {
                    this.UTRISE -= 24.0;
                }
                if (this.UTRISE < 0.0) {
                    this.UTRISE += 24.0;
                }
            }
            if (this.SETT) {
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
            if (!this.RISE) {
                if (this.ABOVE) {
                    this.hRise = 0.0;
                    this.hSet = 24.0;
                }
                else {
                    this.hRise = 0.0;
                    this.hSet = 0.0;
                }
            }
            if (!this.SETT) {
                if (this.ABOVE) {
                    this.hRise = 0.0;
                    this.hSet = 24.0;
                }
                else {
                    this.hRise = 0.0;
                    this.hSet = 0.0;
                }
            }
        }
        else if (this.ABOVE) {
            this.hRise = 0.0;
            this.hSet = 24.0;
        }
        else {
            this.hRise = 0.0;
            this.hSet = 0.0;
        }
    }
    
    double h_rise() {
        return this.hRise;
    }
    
    double h_set() {
        return this.hSet;
    }
    
    public void riseset(final int date, final int month, final int year, final double HOUR) {
        final double sh = Math.sin(-0.014543828656868749);
        final double JD = this.comp.JD(date, month, year, HOUR);
        double dec = this.comp.sunDecRA(JD, 1);
        double ra = this.comp.sunDecRA(JD, 2);
        this.Y0 = this.sin_elev(JD, this.latitude, -this.longitude, dec, ra) - sh;
        final double jdPlus = this.comp.JD(date, month, year, HOUR + 1.0);
        dec = this.comp.sunDecRA(jdPlus, 1);
        ra = this.comp.sunDecRA(jdPlus, 2);
        this.yPlus = this.sin_elev(jdPlus, this.latitude, -this.longitude, dec, ra) - sh;
        final double jdMinus = this.comp.JD(date, month, year, HOUR - 1.0);
        dec = this.comp.sunDecRA(jdMinus, 1);
        ra = this.comp.sunDecRA(jdMinus, 2);
        this.yMinus = this.sin_elev(jdMinus, this.latitude, -this.longitude, dec, ra) - sh;
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
    
    double sin_elev(final double JD, final double LAT, final double LONG, final double DEC, final double RA) {
        final double tau = 15.0 * (this.comp.LM_Sidereal_Time(JD, LONG) - RA);
        return Math.cos(0.017453292519943295 * LAT) * Math.cos(0.017453292519943295 * DEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * LAT) * Math.sin(0.017453292519943295 * DEC);
    }
    
    public String makeTimeString(double time) {
        String str = "?";
        if (time < 0.0) {
            time += 24.0;
        }
        if (time > 24.0) {
            time -= 24.0;
        }
        int min = (int)Math.round(this.comp.frac(time) * 60.0);
        if (min == 60) {
            min = 0;
            ++time;
        }
        if (min > 9) {
            str = ":";
        }
        else {
            str = ":0";
        }
        str = String.valueOf(String.valueOf((int)time) + str + min);
        if (time < 10.0) {
            str = "0" + str;
        }
        return str;
    }
}
