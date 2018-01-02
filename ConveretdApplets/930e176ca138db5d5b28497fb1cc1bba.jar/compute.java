import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

class compute
{
    double DEC;
    double RA;
    int offset;
    String str;
    String str0;
    String str1;
    String str2;
    String str3;
    String[] monthArray;
    double rise;
    double set;
    double currentTime;
    int year;
    int month;
    int date;
    int hours;
    int minutes;
    int seconds;
    double UT;
    double T;
    double GHA;
    double JD;
    double latitude;
    double longitude;
    double hoehe;
    
    compute(final Date dat, final double LAT, final double LONG, final int locOffset) {
        (this.monthArray = new String[12])[0] = "Jan";
        this.monthArray[1] = "Feb";
        this.monthArray[2] = "Mar";
        this.monthArray[3] = "Ap";
        this.monthArray[4] = "May";
        this.monthArray[5] = "Jun";
        this.monthArray[6] = "Jul";
        this.monthArray[7] = "Aug";
        this.monthArray[8] = "Sep";
        this.monthArray[9] = "Oct";
        this.monthArray[10] = "Nov";
        this.monthArray[11] = "Dec";
        this.latitude = LAT;
        this.longitude = LONG;
        this.hours = dat.getHours();
        this.minutes = dat.getMinutes();
        this.seconds = dat.getSeconds();
        this.date = dat.getDate();
        this.month = dat.getMonth();
        ++this.month;
        this.year = dat.getYear();
        this.year += 1900;
        this.offset = locOffset;
        this.UT = this.hours - this.offset + this.minutes / 60.0 + this.seconds / 3600.0;
        this.currentTime = this.UT + this.offset;
        this.JD = this.Jul_Date(this.date, this.month, this.year, this.UT);
        this.T = (this.JD - 2451545.0) / 36525.0;
        this.sun(this.JD);
        this.GHA = this.sun_GHA(this.date, this.month, this.year, this.UT);
        this.hoehe = this.sun_elev(this.JD, this.latitude, this.longitude, this.DEC);
        this.str0 = dat.toString().substring(0, 3);
        this.str1 = String.valueOf(this.str0) + ", " + dat.toString().substring(8, 10);
        this.str1 = String.valueOf(this.str1) + "  " + this.monthArray[this.month - 1];
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
    
    double GM_Sidereal_Time(final double JD) {
        final double MJD = JD - 2400000.5;
        final long MJD2 = (long)MJD;
        final double ut = (MJD - MJD2) * 24.0;
        final double t_eph = (MJD2 - 51544.5) / 36525.0;
        return 6.697374558 + 1.0027379093 * ut + (8640184.812866 + (0.093104 - 6.2E-6 * t_eph) * t_eph) * t_eph / 3600.0;
    }
    
    double frac(double x) {
        x -= (int)x;
        if (x < 0.0) {
            ++x;
        }
        return x;
    }
    
    void sun(final double JD) {
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
        this.DEC = 57.29577951308232 * Math.atan(Z / R);
        this.RA = 7.639437268410976 * Math.atan(Y / (X + R));
        if (this.RA < 0.0) {
            this.RA += 24.0;
        }
    }
    
    double LM_Sidereal_Time(final double JD, final double LONG) {
        final double GMST = this.GM_Sidereal_Time(JD);
        return 24.0 * this.frac((GMST - LONG / 15.0) / 24.0);
    }
    
    double sun_GHA(final int date, final int month, final int year, final double ut) {
        final double JD = this.Jul_Date(date, month, year, ut);
        final double GMST = this.GM_Sidereal_Time(JD);
        double tau = 15.0 * (GMST - this.RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        if (tau < 0.0) {
            tau += 360.0;
        }
        return tau;
    }
    
    double sun_elev(final double JD, final double LAT, final double LONG, final double DEC) {
        final double K = 0.017453292519943295;
        double tau = 15.0 * (this.LM_Sidereal_Time(JD, LONG) - this.RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        final double sinH = Math.cos(0.017453292519943295 * LAT) * Math.cos(0.017453292519943295 * DEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * LAT) * Math.sin(0.017453292519943295 * DEC);
        return Math.asin(sinH) / 0.017453292519943295;
    }
    
    public void RiseSet(final int date, final int month, final int year, final double LAT, final double LONG) {
        int iRise = 100;
        int iSet = 100;
        double elev1Rise = 0.0;
        double elevRise = 0.0;
        double elev1Set = 0.0;
        double elevSet = 0.0;
        for (int i = 1; i < 25; ++i) {
            double ut = i - 1;
            double JD = this.Jul_Date(date, month, year, ut);
            this.sun(JD);
            final double elev1 = this.sun_elev(JD, LAT, LONG, this.DEC);
            ut = i;
            JD = this.Jul_Date(date, month, year, ut);
            this.sun(JD);
            final double elev2 = this.sun_elev(JD, LAT, LONG, this.DEC);
            if (elev1 < 0.0 && elev2 > 0.0) {
                iRise = i;
                elev1Rise = elev1;
                elevRise = elev2;
            }
            if (elev1 > 0.0 && elev2 < 0.0) {
                iSet = i;
                elev1Set = elev1;
                elevSet = elev2;
            }
        }
        if (iRise == 100) {
            this.str2 = "no sunrise";
        }
        else {
            final double elev1 = elev1Rise;
            final double elev2 = elevRise;
            final double utRise = iRise - (elev2 + 0.8333) / (elev2 - elev1);
            double riseTime = utRise + this.offset;
            if (riseTime < 0.0) {
                riseTime += 24.0;
            }
            if (riseTime > 24.0) {
                riseTime -= 24.0;
            }
            long min = Math.round(this.frac(riseTime) * 60.0);
            if (min == 60L) {
                min = 0L;
                ++riseTime;
            }
            String s;
            if (min > 9L) {
                s = ":";
            }
            else {
                s = ":0";
            }
            this.str2 = String.valueOf((int)riseTime) + s + min;
            this.rise = riseTime;
        }
        if (iSet == 100) {
            this.str3 = "no sunset";
        }
        else {
            final double elev1 = elev1Set;
            final double elev2 = elevSet;
            final double utSet = iSet + (elev2 + 0.8333) / (elev1 - elev2);
            double setTime = utSet + this.offset;
            if (setTime < 0.0) {
                setTime += 24.0;
            }
            if (setTime > 24.0) {
                setTime -= 24.0;
            }
            long min = Math.round(this.frac(setTime) * 60.0);
            if (min == 60L) {
                min = 0L;
                ++setTime;
            }
            String s;
            if (min > 9L) {
                s = ":";
            }
            else {
                s = ":0";
            }
            this.str3 = String.valueOf((int)setTime) + s + min;
            this.set = setTime;
        }
    }
    
    public double computeHeight(final double dec, final double latitude, final double longitude, final double GHA) {
        final double K = 0.017453292519943295;
        final double lat_K = latitude * 3.141592653589793 / 180.0;
        final double dec_K = dec * 3.141592653589793 / 180.0;
        final double sinHeight = Math.sin(dec_K) * Math.sin(lat_K) + Math.cos(dec_K) * Math.cos(lat_K) * Math.cos(0.017453292519943295 * (GHA + longitude));
        final double height = Math.asin(sinHeight) / 0.017453292519943295;
        return height;
    }
    
    public double computeAzimut(final double dec, final double latitude, final double longitude, final double GHA, final double hoehe) {
        final double K = 0.017453292519943295;
        final double lat_K = latitude * 3.141592653589793 / 180.0;
        final double hoehe_K = hoehe * 3.141592653589793 / 180.0;
        final double nenner = Math.cos(hoehe_K) * Math.cos(lat_K);
        if (Math.abs(nenner) > 1.0E-5) {
            final double cosAz = (Math.sin(dec * 0.017453292519943295) - Math.sin(lat_K) * Math.sin(hoehe_K)) / nenner;
            double Az = 1.5707963267948966 - Math.asin(cosAz);
            Az /= 0.017453292519943295;
            if (Math.sin(0.017453292519943295 * (GHA - longitude)) <= 0.0) {
                Az = Az;
            }
            if (Math.sin(0.017453292519943295 * (GHA - longitude)) > 0.0) {
                Az = 360.0 - Az;
            }
            return Az;
        }
        return 0.0;
    }
    
    public double elev() {
        return this.hoehe = this.sun_elev(this.JD, this.latitude, this.longitude, this.DEC);
    }
    
    public double azimuth() {
        return this.computeAzimut(this.DEC, this.latitude, this.longitude, this.GHA, this.hoehe);
    }
    
    public double rise() {
        return this.rise;
    }
    
    public double set() {
        return this.set;
    }
    
    public String result1() {
        return this.str1;
    }
    
    public String result2() {
        return this.str2;
    }
    
    public String result3() {
        return this.str3;
    }
    
    public double currentTime() {
        return this.currentTime;
    }
    
    public double declin() {
        return this.DEC;
    }
}