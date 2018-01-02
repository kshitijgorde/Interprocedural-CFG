// 
// Decompiled by Procyon v0.5.30
// 

class compute
{
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
        return str;
    }
    
    public double JD(final int date, int month, int year, final double STD) {
        double A = 10000.0 * year + 100.0 * month + date;
        if (month <= 2) {
            month += 12;
            --year;
        }
        final double B = year / 400 - year / 100 + year / 4;
        A = 365.0 * year - 679004.0;
        final double MJD = A + B + (int)(30.6001 * (month + 1)) + date + STD / 24.0;
        return MJD + 2400000.5;
    }
    
    double GM_Sidereal_Time(final double JD) {
        final double MJD = JD - 2400000.5;
        final long MJD2 = (long)MJD;
        final double ut = (MJD - MJD2) * 24.0;
        final double t_eph = (MJD2 - 51544.5) / 36525.0;
        return 6.697374558 + 1.0027379093 * ut + (8640184.812866 + (0.093104 - 6.2E-6 * t_eph) * t_eph) * t_eph / 3600.0;
    }
    
    double sin_elev(final double JD, final double LAT, final double LONG, final double DEC, final double RA) {
        final double K = 0.017453292519943295;
        double tau = 15.0 * (this.LM_Sidereal_Time(JD, LONG) - RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        return Math.cos(0.017453292519943295 * LAT) * Math.cos(0.017453292519943295 * DEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * LAT) * Math.sin(0.017453292519943295 * DEC);
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
        final double cos_eps = 0.917482062;
        final double sin_eps = 0.397777156;
        final double T = (JD - 2451545.0) / 36525.0;
        final double M = 6.283185307179586 * this.frac(0.993133 + 99.997361 * T);
        final double DL = 6893.0 * Math.sin(M) + 72.0 * Math.sin(2.0 * M);
        final double L = 6.283185307179586 * this.frac(0.7859453 + M / 6.283185307179586 + (6191.2 * T + DL) / 1296000.0);
        final double SL = Math.sin(L);
        final double X = Math.cos(L);
        final double Y = 0.917482062 * SL;
        final double Z = 0.397777156 * SL;
        final double R = Math.sqrt(1.0 - Z * Z);
        if (what == 1) {
            return 57.29577951308232 * Math.atan(Z / R);
        }
        return 7.639437268410976 * Math.atan(Y / (X + R));
    }
    
    double LM_Sidereal_Time(final double JD, final double LONG) {
        final double GMST = this.GM_Sidereal_Time(JD);
        return 24.0 * this.frac((GMST - LONG / 15.0) / 24.0);
    }
    
    double sun_GHA(final double JD, final double RA) {
        final double GMST = this.GM_Sidereal_Time(JD);
        double tau = 15.0 * (GMST - RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        return tau;
    }
    
    double sun_elev(final double JD, final double LAT, final double LONG, final double DEC, final double RA) {
        final double K = 0.017453292519943295;
        double tau = 15.0 * (this.LM_Sidereal_Time(JD, LONG) - RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        final double sinH = Math.cos(0.017453292519943295 * LAT) * Math.cos(0.017453292519943295 * DEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * LAT) * Math.sin(0.017453292519943295 * DEC);
        return Math.asin(sinH) / 0.017453292519943295;
    }
    
    public double computeAzimut(final double dec, final double latitude, final double longitude, final double GHA, final double hoehe) {
        final double K = 0.017453292519943295;
        final double cosAz = (Math.sin(dec * 0.017453292519943295) - Math.sin(0.017453292519943295 * latitude) * Math.sin(0.017453292519943295 * hoehe)) / (Math.cos(0.017453292519943295 * hoehe) * Math.cos(0.017453292519943295 * latitude));
        double Az = 1.5707963267948966 - Math.asin(cosAz);
        Az /= 0.017453292519943295;
        if (Math.sin(0.017453292519943295 * (GHA + longitude)) > 0.0) {
            Az = 360.0 - Az;
        }
        return Az;
    }
}
