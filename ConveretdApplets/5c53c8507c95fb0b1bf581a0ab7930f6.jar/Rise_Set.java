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
    int twilight;
    double currentElev;
    
    public Rise_Set(final Date myDat, final double myLat, final double myLong, final int myLocOffset, final int what) {
        this.dat = myDat;
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth();
        this.year = this.dat.getYear();
        this.hours = this.dat.getHours();
        this.minutes = this.dat.getMinutes();
        this.seconds = this.dat.getSeconds();
        this.currentTime = this.hours + this.minutes / 60.0 + this.seconds / 3600.0;
        this.twilight = what;
        this.latitude = myLat;
        this.longitude = myLong;
        this.locOffset = myLocOffset;
        this.RISE = false;
        this.SETT = false;
        for (int i = -this.locOffset; i < -this.locOffset + 24; ++i) {
            this.riseset(this.date, this.month + 1, this.year + 1900, i);
            if (this.RISE && this.SETT) {
                break;
            }
        }
        if (this.RISE || this.SETT) {
            if (this.RISE) {
                this.UTRISE += this.locOffset;
                if (this.UTRISE > 24.0) {
                    this.UTRISE -= 24.0;
                }
                if (this.UTRISE < 0.0) {
                    this.UTRISE += 24.0;
                }
            }
            if (this.SETT) {
                this.UTSET += this.locOffset;
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
                this.riseStr = this.makeTimeString(this.hRise);
            }
            else if (this.ABOVE) {
                this.riseStr = "  --:--";
                this.setStr = "  --:--";
            }
            else {
                this.riseStr = "  --:--";
                this.setStr = "  --:--";
            }
            if (this.SETT) {
                this.setStr = this.makeTimeString(this.hSet);
            }
            else if (this.ABOVE) {
                this.riseStr = "  --:--";
                this.setStr = "  --:--";
            }
            else {
                this.riseStr = "  --:--";
                this.setStr = "  --:--";
            }
        }
        else if (this.ABOVE) {
            this.riseStr = "  --:--";
            this.setStr = "  --:--";
        }
        else {
            this.riseStr = "  --:--";
            this.setStr = "  --:--";
        }
        this.riseset(this.date, this.month + 1, this.year + 1900, this.currentTime - this.locOffset);
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
        double sh = 0.0;
        switch (this.twilight) {
            case 1: {
                sh = Math.sin(-0.014543828656868749);
                break;
            }
            case 2: {
                sh = Math.sin(-0.10471975511965978);
                break;
            }
            case 3: {
                sh = Math.sin(-0.20943951023931956);
                break;
            }
            case 4: {
                sh = Math.sin(-0.3141592653589793);
                break;
            }
        }
        final double JD = this.Jul_Date(date, month, year, HOUR);
        double dec = this.sunDecRA(1, JD);
        double ra = this.sunDecRA(2, JD);
        this.Y0 = this.sin_elev(JD, this.latitude, -this.longitude, dec, ra) - sh;
        this.currentElev = Math.asin(this.sin_elev(JD, this.latitude, -this.longitude, dec, ra)) / 0.017453292519943295;
        final double jdPlus = this.Jul_Date(date, month, year, HOUR + 1.0);
        dec = this.sunDecRA(1, jdPlus);
        ra = this.sunDecRA(2, jdPlus);
        this.yPlus = this.sin_elev(jdPlus, this.latitude, -this.longitude, dec, ra) - sh;
        final double jdMinus = this.Jul_Date(date, month, year, HOUR - 1.0);
        dec = this.sunDecRA(1, jdMinus);
        ra = this.sunDecRA(2, jdMinus);
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
    
    double sunDecRA(final int what, final double JD) {
        final double PI2 = 6.283185307179586;
        final double cos_eps = 0.917482;
        final double sin_eps = 0.397778;
        final double T = (JD - 2451545.0) / 36525.0;
        final double M = 6.283185307179586 * this.frac(0.993133 + 99.997361 * T);
        final double DL = 6893.0 * Math.sin(M) + 72.0 * Math.sin(2.0 * M);
        final double L = 6.283185307179586 * this.frac(0.7859453 + M / 6.283185307179586 + (6191.2 * T + DL) / 1296000.0);
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
    
    double sin_elev(final double JD, final double LAT, final double LONG, final double DEC, final double RA) {
        final double K = 0.017453292519943295;
        double tau = 15.0 * (this.LM_Sidereal_Time(JD, LONG) - RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
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
        final double diff = time - (int)time;
        int min = (int)Math.round(diff * 60.0);
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
        return " " + str;
    }
    
    double Jul_Date(final int date, int month, int year, final double ut) {
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
        final double MJD = A + B + (int)(30.6001 * (month + 1)) + date + ut / 24.0;
        return MJD + 2400000.5;
    }
    
    double frac(double x) {
        x -= (int)x;
        if (x < 0.0) {
            ++x;
        }
        return x;
    }
    
    double LM_Sidereal_Time(final double JD, final double LONG) {
        final double GMST = this.GM_Sidereal_Time(JD);
        return 24.0 * this.frac((GMST - LONG / 15.0) / 24.0);
    }
    
    double GM_Sidereal_Time(final double JD) {
        final double MJD = JD - 2400000.5;
        final long MJD2 = (long)MJD;
        final double ut = (MJD - MJD2) * 24.0;
        final double t_eph = (MJD2 - 51544.5) / 36525.0;
        return 6.697374558 + 1.0027379093 * ut + (8640184.812866 + (0.093104 - 6.2E-6 * t_eph) * t_eph) * t_eph / 3600.0;
    }
    
    double current_Elev() {
        return this.currentElev;
    }
}
