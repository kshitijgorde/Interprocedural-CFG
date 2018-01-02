// 
// Decompiled by Procyon v0.5.30
// 

class compute
{
    final double K = 0.017453292519943295;
    final char deg = '°';
    
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
    
    public String DMS(final double x) {
        String str = "";
        final double X = Math.abs(x);
        int DEG = (int)X;
        int MIN = (int)(this.frac(X) * 60.0);
        double SEC = 60.0 * (this.frac(X) * 60.0 - MIN);
        if ((int)Math.round(SEC) == 60) {
            SEC = 0.0;
            ++MIN;
        }
        if (MIN == 60) {
            MIN = 0;
            ++DEG;
        }
        if (x < 0.0) {
            str = "-";
        }
        else {
            str = "+";
        }
        if (DEG < 10) {
            str = " " + str;
        }
        str = String.valueOf(str) + DEG + '°' + " ";
        if (MIN < 10) {
            str = String.valueOf(str) + " ";
        }
        str = String.valueOf(str) + MIN + "' ";
        if ((int)Math.round(SEC) < 10) {
            str = String.valueOf(str) + "0";
        }
        return String.valueOf(str) + Math.round(SEC) + "''";
    }
    
    public String DM(final double x) {
        String str = "";
        final double X = Math.abs(x);
        int DEG = (int)X;
        int MIN = (int)Math.round(this.frac(X) * 60.0);
        if (MIN == 60) {
            MIN = 0;
            ++DEG;
        }
        if (x < 0.0) {
            str = "-";
        }
        else {
            str = "+";
        }
        if (DEG < 10) {
            str = " " + str;
        }
        str = String.valueOf(str) + DEG + '°' + " ";
        if (MIN < 10) {
            str = String.valueOf(str) + " ";
        }
        return String.valueOf(str) + MIN + "' ";
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
        return String.valueOf(str) + MIN + " m ";
    }
    
    public int caldat(final int what, final double JD) {
        final double JD2 = (int)(JD + 0.5);
        final int B = (int)((JD2 - 1867216.25) / 36524.25);
        final double C = JD2 + B - B / 4 + 1525.0;
        final int D = (int)((C - 122.1) / 365.25);
        final double E = 365.0 * D + D / 4;
        final int F = (int)((C - E) / 30.6001);
        final int day = (int)(C - E + 0.5) - (int)(30.6001 * F);
        final int month = F - 1 - 12 * (F / 14);
        final double hour = 24.0 * (JD + 0.5 - JD2);
        final int year = D - 4715 - (7 + month) / 10;
        if (what == 0) {
            return year - 1900;
        }
        if (what == 1) {
            return day;
        }
        if (what == 2) {
            return month - 1;
        }
        if (what == 3) {
            return (int)Math.round(hour);
        }
        if (what == 4) {
            return (int)Math.round(60.0 * this.frac(hour));
        }
        return 0;
    }
    
    double LM_Sidereal_Time(final double JD, final double LONG) {
        final double GMST = this.GM_Sidereal_Time(JD);
        return 24.0 * this.frac((GMST - LONG / 15.0) / 24.0);
    }
    
    double sun_elev(final double JD, final double LAT, final double LONG, final double DEC, final double RA) {
        double tau = 15.0 * (this.LM_Sidereal_Time(JD, LONG) - RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        final double sinH = Math.cos(0.017453292519943295 * LAT) * Math.cos(0.017453292519943295 * DEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * LAT) * Math.sin(0.017453292519943295 * DEC);
        return Math.asin(sinH) / 0.017453292519943295;
    }
    
    double THETA0(final double jd) {
        final double T = (jd - 2451545.0) / 36525.0;
        double x = 280.46061837 + 360.98564736629 * (jd - 2451545.0) + 3.87933E-4 * T * T - T * T * T / 3.871E7;
        x %= 360.0;
        if (x < 0.0) {
            x += 360.0;
        }
        return x;
    }
    
    public double azimuth(final double jd, final double ra, final double dec, final double latitude, final double longitude) {
        final double H = this.THETA0(jd) + longitude - 15.0 * ra;
        return 180.0 + Math.atan2(Math.sin(0.017453292519943295 * H), Math.cos(0.017453292519943295 * H) * Math.sin(0.017453292519943295 * latitude) - Math.tan(0.017453292519943295 * dec) * Math.cos(0.017453292519943295 * latitude)) / 0.017453292519943295;
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
}
