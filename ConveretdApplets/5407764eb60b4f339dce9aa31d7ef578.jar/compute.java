// 
// Decompiled by Procyon v0.5.30
// 

class compute
{
    double DEC;
    double RA;
    int offset;
    final double K = 0.017453292519943295;
    double UT;
    double T;
    double GHA;
    double JD;
    double latitude;
    double longitude;
    double hoehe;
    
    compute(final double jd, final double LAT, final double LONG, final int locOffset) {
        this.latitude = LAT;
        this.longitude = LONG;
        this.offset = locOffset;
        this.UT = this.caldat(3, jd) + this.caldat(4, jd) / 60.0;
        this.JD = jd;
        this.T = (jd - 2451545.0) / 36525.0;
        this.sun(jd);
        this.GHA = this.sun_GHA(jd);
        this.hoehe = this.sun_elev(jd, this.latitude, this.longitude, this.DEC);
    }
    
    public int caldat(final int what, final double jd) {
        final int Z = (int)(jd + 0.5);
        final double F = this.frac(jd + 0.5);
        int A;
        if (Z < 2299161) {
            A = Z;
        }
        else {
            final int a = (int)((Z - 1867216.25) / 36524.25);
            A = Z + 1 + a - (int)(a / 4.0);
        }
        final int B = A + 1524;
        final int C = (int)((B - 122.1) / 365.25);
        final int D = (int)(365.25 * C);
        final int E = (int)((B - D) / 30.6001);
        final double DAY = B - D - (int)(30.6001 * E) + F;
        final int day = (int)DAY;
        int month;
        if (E < 14) {
            month = E - 1;
        }
        else {
            month = E - 13;
        }
        int year;
        if (month > 2) {
            year = C - 4716;
        }
        else {
            year = C - 4715;
        }
        final double hour = 24.0 * this.frac(DAY);
        if (what == 1) {
            return day;
        }
        if (what == 2) {
            return month - 1;
        }
        if (what == 4) {
            return year;
        }
        if (what == 3) {
            return (int)hour;
        }
        if (what == 5) {
            return (int)Math.round(60.0 * this.frac(hour));
        }
        return 0;
    }
    
    public double JD(final int date, int month, int year, final double STD) {
        if (month <= 2) {
            month += 12;
            --year;
        }
        double B = year / 400 - year / 100 + year / 4;
        if (year < 1583) {
            B = 0.0;
        }
        final double A = 365.0 * year - 679004.0;
        final double MJD = A + B + (int)(30.6001 * (month + 1)) + date + STD / 24.0;
        return MJD + 2400000.5;
    }
    
    double THETA0(final double JD) {
        final double T = (JD - 2451545.0) / 36525.0;
        double x = 280.46061837 + 360.98564736629 * (JD - 2451545.0) + 3.87933E-4 * T * T - T * T * T / 3.871E7;
        x %= 360.0;
        if (x < 0.0) {
            x += 360.0;
        }
        return x;
    }
    
    double sin_elev(final double JD, final double LAT, final double LONG, final double DEC, final double RA) {
        final double K = 0.017453292519943295;
        double tau = 15.0 * (this.LM_Sidereal_Time(JD, LONG) - RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        return Math.cos(0.017453292519943295 * LAT) * Math.cos(0.017453292519943295 * DEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * LAT) * Math.sin(0.017453292519943295 * DEC);
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
        final double T = (JD - 2451545.0) / 36525.0;
        final double eps = 23.433333333333334 + (21.448 - 46.815 * T - 5.9E-4 * T * T + 0.001813 * T * T * T) / 3600.0;
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
    
    double sun_GHA(final double jd) {
        final double GMST = this.GM_Sidereal_Time(jd);
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
        double sinH = Math.cos(0.017453292519943295 * LAT) * Math.cos(0.017453292519943295 * DEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * LAT) * Math.sin(0.017453292519943295 * DEC);
        sinH = Math.asin(sinH) / 0.017453292519943295;
        final double R = this.refract(sinH) / 60.0;
        return sinH + R;
    }
    
    double refract(final double h) {
        final double K = 0.017453292519943295;
        return 1.02 / Math.tan(K * (h + 10.3 / (h + 5.11)));
    }
    
    public double computeAzimut(final double dec, final double latitude, final double longitude, final double GHA, final double hoehe) {
        final double K = 0.017453292519943295;
        final double lat_K = latitude * 3.141592653589793 / 180.0;
        final double hoehe_K = hoehe * 3.141592653589793 / 180.0;
        final double cosAz = (Math.sin(dec * 0.017453292519943295) - Math.sin(lat_K) * Math.sin(hoehe_K)) / (Math.cos(hoehe_K) * Math.cos(lat_K));
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
    
    double azim(final double JD, final double LAT, final double LONG) {
        final double h = this.THETA0(JD) - LONG - 15.0 * this.RA;
        double az = Math.atan2(Math.sin(0.017453292519943295 * h), Math.cos(0.017453292519943295 * h) * Math.sin(0.017453292519943295 * LAT) - Math.tan(0.017453292519943295 * this.DEC) * Math.cos(0.017453292519943295 * LAT)) / 0.017453292519943295;
        final double gha = this.sun_GHA(JD);
        if (Math.sin(0.017453292519943295 * (gha - LONG)) <= 0.0) {
            az = az;
        }
        if (Math.sin(0.017453292519943295 * (this.GHA - LONG)) > 0.0) {
            az += 180.0;
        }
        if (az < 0.0) {
            az += 180.0;
        }
        return az;
    }
    
    public double elev() {
        return this.hoehe = this.sun_elev(this.JD, this.latitude, this.longitude, this.DEC);
    }
    
    public double azimuth() {
        return this.azim(this.JD, this.latitude, this.longitude);
    }
    
    public double declin() {
        return this.DEC;
    }
    
    double sunDecRA(final int what, final double JD) {
        final double T = (JD - 2451545.0) / 36525.0;
        final double t = T / 10.0;
        final double L = 280.4664567 + 360007.6982779 * t + 0.03032028 * t * t + Math.pow(t, 3.0) / 49931.0 - Math.pow(t, 4.0) / 15299.0 + Math.pow(t, 5.0) / 1988000.0;
        final double M = 357.5291 + 35999.0503 * T - 1.559E-4 * T * T - 4.8E-7 * T * T * T;
        final double C = (1.9146 - 0.004817 * T - 1.4E-5 * T * T) * Math.sin(0.017453292519943295 * M) + (0.019993 - 1.01E-4 * T) * Math.sin(0.03490658503988659 * M) + 2.9E-4 * Math.sin(0.05235987755982989 * M);
        final double theta = L + C;
        final double LM = 218.3165 + 481267.8813 * T;
        final double eps0 = 23.43929111111111 - (46.815 * T + 5.9E-4 * T * T - 0.001813 * T * T * T) / 3600.0;
        final double omega = 125.04452 - 1934.136261 * T + 0.0020708 * T * T + T * T * T / 450000.0;
        final double deltaEps = (9.2 * Math.cos(0.017453292519943295 * omega) + 0.57 * Math.cos(0.03490658503988659 * L) + 0.1 * Math.cos(0.03490658503988659 * LM) - 0.09 * Math.cos(0.03490658503988659 * omega)) / 3600.0;
        double eps2 = eps0 + deltaEps;
        eps2 += 0.00256 * Math.cos(0.017453292519943295 * (125.04 - 1934.136 * T));
        final double lambda = theta - 0.00569 - 0.00478 * Math.sin(0.017453292519943295 * (125.04 - 1934.136 * T));
        double RA = Math.atan2(Math.cos(0.017453292519943295 * eps2) * Math.sin(0.017453292519943295 * lambda), Math.cos(0.017453292519943295 * lambda)) / 0.017453292519943295;
        if (RA < 0.0) {
            RA += 360.0;
        }
        final double delta = Math.asin(Math.sin(0.017453292519943295 * eps2) * Math.sin(0.017453292519943295 * lambda)) / 0.017453292519943295;
        if (what == 1) {
            return delta;
        }
        return RA / 15.0;
    }
}
