// 
// Decompiled by Procyon v0.5.30
// 

class Compute
{
    final double K = 0.017453292519943295;
    final char deg = '°';
    
    public double frac(double X) {
        X -= (int)X;
        if (X < 0.0) {
            ++X;
        }
        return X;
    }
    
    public int daysInMonth(final int m, int y) {
        int n = 31;
        y += 1900;
        if (m == 0 || m == 2 || m == 4 || m == 6 || m == 7 || m == 9 || m == 11) {
            n = 31;
        }
        else if (m == 3 || m == 5 || m == 8 || m == 10) {
            n = 30;
        }
        if (m == 1) {
            n = 28;
            if (y % 4 == 0) {
                n = 29;
            }
            else if (y % 100 == 0) {
                n = 28;
            }
            else if (y % 400 == 0) {
                n = 29;
            }
        }
        return n;
    }
    
    public String HM(double x) {
        String str = "";
        if (x < 0.0) {
            x = Math.abs(x);
        }
        final int HOUR = (int)x;
        final int MIN = (int)Math.round(this.frac(x) * 60.0);
        if (HOUR < 10) {
            str = "0";
        }
        str = String.valueOf(str) + HOUR + " h ";
        if (MIN < 10) {
            str = String.valueOf(str) + "0";
        }
        str = String.valueOf(str) + MIN + " m ";
        return str;
    }
    
    public String DM(final double x) {
        String str = "";
        double X;
        if (x < 0.0) {
            X = Math.abs(x);
        }
        else {
            X = x;
        }
        int DEG = (int)X;
        int MIN = (int)Math.round(this.frac(X) * 60.0);
        if (MIN == 60) {
            MIN = 0;
            ++DEG;
        }
        if (DEG < 10) {
            str = " " + str;
        }
        str = String.valueOf(str) + DEG + '°' + " ";
        if (MIN < 10) {
            str = String.valueOf(str) + "0";
        }
        str = String.valueOf(str) + MIN + "'";
        if (x < 0.0) {
            str = String.valueOf(str) + " S";
        }
        else {
            str = String.valueOf(str) + " N";
        }
        return str;
    }
    
    public String makeTimeString(final String whatStr, double time) {
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
        return String.valueOf(whatStr) + str;
    }
    
    public String DaysHoursMinutes(final double days) {
        final int tage = (int)days;
        String str1 = String.valueOf(tage);
        if (tage < 10) {
            str1 = "0" + str1;
        }
        final double stunden = 24.0 * this.frac(days);
        return str1 + " d " + this.HM(stunden);
    }
    
    public double moonParal(final double elev, final double distance) {
        final double horParal = 8.794 / (distance / 1.4959787E8);
        final double paral = Math.cos(0.017453292519943295 * elev) * Math.sin(0.017453292519943295 * horParal / 3600.0);
        return Math.asin(paral) / 0.017453292519943295;
    }
    
    double refract(final double h) {
        return 1.02 / Math.tan(0.017453292519943295 * (h + 10.3 / (h + 5.11)));
    }
    
    double sunDecRA(final int what, final double JD) {
        final double PI2 = 6.283185307179586;
        final double T = (JD - 2451545.0) / 36525.0;
        final double eps = 23.43929111111111 - (46.815 * T + 5.9E-4 * T * T - 0.001813 * T * T * T) / 3600.0;
        final double cos_eps = Math.cos(0.017453292519943295 * eps);
        final double sin_eps = Math.sin(0.017453292519943295 * eps);
        final double M = 6.283185307179586 * this.frac(0.993133 + 99.997361 * T);
        final double DL = 6893.0 * Math.sin(M) + 72.0 * Math.sin(2.0 * M);
        final double L = 6.283185307179586 * this.frac(0.7859453 + M / 6.283185307179586 + (6191.2 * T + DL) / 1296000.0);
        final double SL = Math.sin(L);
        final double X = Math.cos(L);
        final double Y = cos_eps * SL;
        final double Z = sin_eps * SL;
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
    
    double sun_elev(final double JD, final double LAT, final double LONG, final double DEC, final double RA) {
        double tau = 15.0 * (this.LM_Sidereal_Time(JD, LONG) - RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        final double sinH = Math.cos(0.017453292519943295 * LAT) * Math.cos(0.017453292519943295 * DEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * LAT) * Math.sin(0.017453292519943295 * DEC);
        return Math.asin(sinH) / 0.017453292519943295;
    }
    
    double sin_elev(final double JD, final double LAT, final double LONG, final double DEC, final double RA) {
        double tau = 15.0 * (this.LM_Sidereal_Time(JD, LONG) - RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        return Math.cos(0.017453292519943295 * LAT) * Math.cos(0.017453292519943295 * DEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * LAT) * Math.sin(0.017453292519943295 * DEC);
    }
    
    double sun_GHA(final double JD, final double RA) {
        final double GMST = this.GM_Sidereal_Time(JD);
        double tau = 15.0 * (GMST - RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        return tau;
    }
    
    public double computeLat(final double longitude, final double dec) {
        return Math.atan(-Math.cos(longitude * 0.017453292519943295) / Math.tan(dec * 0.017453292519943295)) / 0.017453292519943295;
    }
    
    public double JD(final int date, int month, int year, final double UT) {
        if (month <= 2) {
            month += 12;
            --year;
        }
        final int A = (int)(year / 100.0);
        final int B = 2 - A + (int)(A / 4.0);
        final int C = (int)(365.25 * (year + 4716)) + (int)(30.6001 * (month + 1)) + date + B;
        return C - 1524.5 + UT / 24.0;
    }
    
    public String dayName(final double jd) {
        final String[] dayArray = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
        long num = (long)(jd + 0.5);
        num -= num / 7L * 7L;
        return dayArray[(int)num];
    }
    
    public String dayString(final double jd) {
        final String[] dayArray = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
        long num = (long)(jd + 0.5);
        num -= num / 7L * 7L;
        return dayArray[(int)num];
    }
    
    public double computeGHA(final int T, final int M, final int J, final double STD) {
        final double jd = this.JD(T, M, J, STD);
        return this.sun_GHA(jd, this.sunDecRA(2, jd)) % 360.0;
    }
    
    public double computeDeclination(final int T, final int M, final int J, final double STD) {
        return this.sunDecRA(1, this.JD(T, M, J, STD));
    }
    
    public double computeHeight(final double dec, final double latitude, final double longitude, final double GHA) {
        final double lat_K = latitude * 0.017453292519943295;
        final double dec_K = dec * 0.017453292519943295;
        final double sinHeight = Math.sin(dec_K) * Math.sin(lat_K) + Math.cos(dec_K) * Math.cos(lat_K) * Math.cos(0.017453292519943295 * (GHA + longitude));
        return Math.asin(sinHeight) / 0.017453292519943295;
    }
    
    public double computeAzimut(final double dec, final double latitude, final double longitude, final double GHA, final double hoehe) {
        final double cosAz = (Math.sin(dec * 0.017453292519943295) - Math.sin(0.017453292519943295 * latitude) * Math.sin(0.017453292519943295 * hoehe)) / (Math.cos(0.017453292519943295 * hoehe) * Math.cos(0.017453292519943295 * latitude));
        final double Az = Math.acos(cosAz) / 0.017453292519943295;
        if (Math.sin(0.017453292519943295 * (GHA + longitude)) <= 0.0) {
            return Az;
        }
        return 360.0 - Az;
    }
}
