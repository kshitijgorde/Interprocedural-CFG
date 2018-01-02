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
    
    public Rise_Set(final Date dat, final double latitude, final double longitude, final int n, final double elev) {
        this.elev = elev;
        this.dat = dat;
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth();
        this.year = this.dat.getYear();
        this.hours = this.dat.getHours();
        this.minutes = this.dat.getMinutes();
        this.seconds = this.dat.getSeconds();
        this.currentTime = this.hours + this.minutes / 60.0 + this.seconds / 3600.0;
        this.latitude = latitude;
        this.longitude = longitude;
        this.RISE = false;
        this.SETT = false;
        for (int i = -n; i < -n + 24; ++i) {
            this.riseset(this.date, this.month + 1, this.year + 1900, i);
            if (this.RISE && this.SETT) {
                break;
            }
        }
        if (this.RISE || this.SETT) {
            if (this.RISE) {
                this.UTRISE += n;
                if (this.UTRISE > 24.0) {
                    this.UTRISE -= 24.0;
                }
                if (this.UTRISE < 0.0) {
                    this.UTRISE += 24.0;
                }
            }
            if (this.SETT) {
                this.UTSET += n;
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
                this.riseStr = this.makeTimeString("", this.hRise);
            }
            else if (this.ABOVE) {
                this.riseStr = "Sun visible all day";
                this.setStr = "Sun visible all day";
            }
            else {
                this.riseStr = "Sun unvisible all day";
                this.setStr = "Sun unvisible all day";
            }
            if (this.SETT) {
                this.setStr = this.makeTimeString("", this.hSet);
            }
            else if (this.ABOVE) {
                this.riseStr = "Sun visible all day";
                this.setStr = "Sun visible all day";
            }
            else {
                this.riseStr = "Sun unvisible all day";
                this.setStr = "Sun unvisible all day";
            }
        }
        else if (this.ABOVE) {
            this.riseStr = "Sun visible all day";
            this.setStr = "Sun visible all day";
        }
        else {
            this.riseStr = "Sun unvisible all day";
            this.setStr = "Sun unvisible all day";
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
    
    public void riseset(final int n, final int n2, final int n3, final double n4) {
        final double sin = Math.sin(0.017453292519943295 * this.elev);
        final double jul_Date = this.Jul_Date(n, n2, n3, n4);
        this.Y0 = this.sin_elev(jul_Date, this.latitude, -this.longitude, this.sunDecRA(1, jul_Date), this.sunDecRA(2, jul_Date)) - sin;
        final double jul_Date2 = this.Jul_Date(n, n2, n3, n4 + 1.0);
        this.yPlus = this.sin_elev(jul_Date2, this.latitude, -this.longitude, this.sunDecRA(1, jul_Date2), this.sunDecRA(2, jul_Date2)) - sin;
        final double jul_Date3 = this.Jul_Date(n, n2, n3, n4 - 1.0);
        this.yMinus = this.sin_elev(jul_Date3, this.latitude, -this.longitude, this.sunDecRA(1, jul_Date3), this.sunDecRA(2, jul_Date3)) - sin;
        this.ABOVE = (this.yMinus > 0.0);
        this.QUAD();
        switch (this.NZ) {
            case 1: {
                if (this.yMinus < 0.0) {
                    this.UTRISE = n4 + this.zero1;
                    this.RISE = true;
                    break;
                }
                this.UTSET = n4 + this.zero1;
                this.SETT = true;
                break;
            }
            case 2: {
                if (this.YE < 0.0) {
                    this.UTRISE = n4 + this.zero2;
                    this.UTSET = n4 + this.zero1;
                }
                else {
                    this.UTRISE = n4 + this.zero1;
                    this.UTSET = n4 + this.zero2;
                }
                this.RISE = true;
                this.SETT = true;
                break;
            }
        }
    }
    
    public void QUAD() {
        this.NZ = 0;
        final double n = 0.5 * (this.yMinus + this.yPlus) - this.Y0;
        final double n2 = 0.5 * (this.yPlus - this.yMinus);
        final double y0 = this.Y0;
        final double n3 = -n2 / (2.0 * n);
        this.YE = (n * n3 + n2) * n3 + y0;
        final double n4 = n2 * n2 - 4.0 * n * y0;
        if (n4 >= 0.0) {
            this.DX = 0.5 * Math.sqrt(n4) / Math.abs(n);
            this.zero1 = n3 - this.DX;
            this.zero2 = n3 + this.DX;
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
    
    double sunDecRA(final int n, final double n2) {
        final double n3 = (n2 - 2451545.0) / 36525.0;
        final double n4 = 6.283185307179586 * this.frac(0.993133 + 99.997361 * n3);
        final double n5 = 6.283185307179586 * this.frac(0.7859453 + n4 / 6.283185307179586 + (6191.2 * n3 + (6893.0 * Math.sin(n4) + 72.0 * Math.sin(2.0 * n4))) / 1296000.0);
        final double sin = Math.sin(n5);
        final double cos = Math.cos(n5);
        final double n6 = 0.917482 * sin;
        final double n7 = 0.397778 * sin;
        final double sqrt = Math.sqrt(1.0 - n7 * n7);
        final double n8 = 57.29577951308232 * Math.atan(n7 / sqrt);
        double n9 = 7.639437268410976 * Math.atan(n6 / (cos + sqrt));
        if (n9 < 0.0) {
            n9 += 24.0;
        }
        if (n == 1) {
            return n8;
        }
        return n9;
    }
    
    double sin_elev(final double n, final double n2, final double n3, final double n4, final double n5) {
        double n6 = 15.0 * (this.LM_Sidereal_Time(n, n3) - n5);
        if (n6 < 0.0) {
            n6 += 360.0;
        }
        return Math.cos(0.017453292519943295 * n2) * Math.cos(0.017453292519943295 * n4) * Math.cos(0.017453292519943295 * n6) + Math.sin(0.017453292519943295 * n2) * Math.sin(0.017453292519943295 * n4);
    }
    
    public String makeTimeString(final String s, double n) {
        if (n < 0.0) {
            n += 24.0;
        }
        if (n > 24.0) {
            n -= 24.0;
        }
        int n2 = (int)Math.round((n - (int)n) * 60.0);
        if (n2 == 60) {
            n2 = 0;
            ++n;
        }
        String s2;
        if (n2 > 9) {
            s2 = ":";
        }
        else {
            s2 = ":0";
        }
        String s3 = String.valueOf(String.valueOf((int)n) + s2 + n2);
        if (n < 10.0) {
            s3 = "0" + s3;
        }
        return String.valueOf(s) + "  " + s3 + "  Hrs";
    }
    
    double Jul_Date(final int n, int n2, int n3, final double n4) {
        final double n5 = 10000.0 * n3 + 100.0 * n2 + n;
        if (n2 <= 2) {
            n2 += 12;
            --n3;
        }
        double n6;
        if (n5 <= 1.58210041E7) {
            n6 = -2 + (n3 + 4716) / 4 - 1179;
        }
        else {
            n6 = n3 / 400 - n3 / 100 + n3 / 4;
        }
        return 365.0 * n3 - 679004.0 + n6 + (int)(30.6001 * (n2 + 1)) + n + n4 / 24.0 + 2400000.5;
    }
    
    double frac(double n) {
        n -= (int)n;
        if (n < 0.0) {
            ++n;
        }
        return n;
    }
    
    double LM_Sidereal_Time(final double n, final double n2) {
        return 24.0 * this.frac((this.GM_Sidereal_Time(n) - n2 / 15.0) / 24.0);
    }
    
    double GM_Sidereal_Time(final double n) {
        final double n2 = n - 2400000.5;
        final long n3 = (long)n2;
        final double n4 = (n2 - n3) * 24.0;
        final double n5 = (n3 - 51544.5) / 36525.0;
        return 6.697374558 + 1.0027379093 * n4 + (8640184.812866 + (0.093104 - 6.2E-6 * n5) * n5) * n5 / 3600.0;
    }
}
