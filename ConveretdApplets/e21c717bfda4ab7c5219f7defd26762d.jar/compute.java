// 
// Decompiled by Procyon v0.5.30
// 

class compute
{
    final double K = 0.017453292519943295;
    final char deg = '°';
    
    public String HMS(double x) {
        String str = "";
        x /= 15.0;
        if (x < 0.0) {
            x = Math.abs(x);
        }
        final int HOUR = (int)x;
        final int MIN = (int)(this.frac(x) * 60.0);
        final double SEC = 60.0 * (this.frac(x) * 60.0 - MIN);
        if (HOUR < 10) {
            str = "0";
        }
        str = String.valueOf(str) + HOUR + ":";
        if (MIN < 10) {
            str = String.valueOf(str) + "0";
        }
        str = String.valueOf(str) + MIN + ":";
        if ((int)Math.round(SEC) < 10) {
            str = String.valueOf(str) + "0";
        }
        return String.valueOf(str) + Math.round(SEC);
    }
    
    public String MS(double x) {
        String str = "";
        String minus = "+";
        if (x < 0.0) {
            x = Math.abs(x);
            minus = "-";
        }
        final int MIN = (int)x;
        final double SEC = this.frac(x) * 60.0 - MIN;
        if (MIN < 10) {
            str = String.valueOf(str) + "0";
        }
        str = String.valueOf(str) + MIN + "m ";
        if ((int)Math.round(SEC) < 10) {
            str = String.valueOf(str) + "0";
        }
        return String.valueOf(minus) + str + Math.round(SEC) + "s";
    }
    
    public String hms(double x) {
        String str = "";
        x /= 15.0;
        if (x < 0.0) {
            x = Math.abs(x);
        }
        final int HOUR = (int)x;
        final int MIN = (int)(this.frac(x) * 60.0);
        final double SEC = 60.0 * (this.frac(x) * 60.0 - MIN);
        if (HOUR < 10) {
            str = "0";
        }
        str = String.valueOf(str) + HOUR + "h ";
        if (MIN < 10) {
            str = String.valueOf(str) + "0";
        }
        str = String.valueOf(str) + MIN + "' ";
        if ((int)Math.round(SEC) < 10) {
            str = String.valueOf(str) + "0";
        }
        return String.valueOf(str) + Math.round(SEC) + "''";
    }
    
    public String DMS(double x) {
        String str = "";
        String minus = "";
        if (x < 0.0) {
            x = Math.abs(x);
            minus = "-";
        }
        final int HOUR = (int)x;
        final int MIN = (int)(this.frac(x) * 60.0);
        final double SEC = 60.0 * (this.frac(x) * 60.0 - MIN);
        if (HOUR < 10) {
            str = "0";
        }
        str = String.valueOf(str) + HOUR + '°' + " ";
        if (MIN < 10) {
            str = String.valueOf(str) + "0";
        }
        str = String.valueOf(str) + MIN + "' ";
        if ((int)Math.round(SEC) < 10) {
            str = String.valueOf(str) + "0";
        }
        return String.valueOf(minus) + str + Math.round(SEC) + "''";
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
    
    double sunDecRA(final double JD, final int what) {
        final double PI2 = 6.283185307179586;
        final double T = (JD - 2451545.0) / 36525.0;
        final double M = 6.283185307179586 * this.frac(0.993133 + 99.997361 * T);
        final double DL = 6893.0 * Math.sin(M) + 72.0 * Math.sin(2.0 * M);
        final double L = 6.283185307179586 * this.frac(0.7859453 + M / 6.283185307179586 + (6191.2 * T + DL) / 1296000.0);
        final double SL = Math.sin(L);
        final double X = Math.cos(L);
        final double eps = 23.43929111111111 - (46.815 * T + 5.9E-4 * T * T - 0.001813 * T * T * T) / 3600.0;
        final double Y = Math.cos(0.017453292519943295 * eps) * SL;
        final double Z = Math.sin(0.017453292519943295 * eps) * SL;
        final double R = Math.sqrt(1.0 - Z * Z);
        if (what == 1) {
            return 57.29577951308232 * Math.atan(Z / R);
        }
        if (what == 2) {
            return 7.639437268410976 * Math.atan(Y / (X + R));
        }
        if (what == 3) {
            return L * 360.0 / 6.283185307179586;
        }
        if (what == 4) {
            return eps;
        }
        return 0.0;
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
    
    double refract(final double h) {
        return 1.02 / Math.tan(0.017453292519943295 * (h + 10.3 / (h + 5.11))) / 60.0;
    }
}
