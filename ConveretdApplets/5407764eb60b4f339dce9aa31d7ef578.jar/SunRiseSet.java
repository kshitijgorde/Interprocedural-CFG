// 
// Decompiled by Procyon v0.5.30
// 

class SunRiseSet
{
    final double K = 0.017453292519943295;
    double LAT;
    double LONG;
    double jdRise;
    double AZ;
    double HH;
    
    public SunRiseSet(final int rs, final double Jd, final double myLat, final double myLong, final int angle) {
        this.LAT = myLat;
        this.LONG = myLong;
        double jd0 = 0.0;
        double h0;
        if (angle == 0) {
            h0 = -0.8333;
        }
        else {
            h0 = angle;
        }
        for (int i = 0; i < 24; ++i) {
            final double jd2 = Jd + i / 24.0;
            double dec = this.sunDecRA(1, jd2);
            double ra = this.sunDecRA(2, jd2);
            final double h2 = this.elev(jd2, this.LAT, this.LONG, dec, ra) - h0;
            final double jd3 = jd2 + 0.041666666666666664;
            dec = this.sunDecRA(1, jd3);
            ra = this.sunDecRA(2, jd3);
            final double h3 = this.elev(jd3, this.LAT, this.LONG, dec, ra) - h0;
            if (rs == 1) {
                if (h2 * h3 < 0.0 && h2 < 0.0 && h3 > 0.0) {
                    jd0 = jd2;
                    break;
                }
            }
            else if (h2 * h3 < 0.0 && h2 > 0.0 && h3 < 0.0) {
                jd0 = jd2;
                break;
            }
        }
        final double dt = 0.5;
        for (int j = 0; j <= 120; ++j) {
            final double jd2 = jd0 + j * dt / 1440.0;
            double dec = this.sunDecRA(1, jd2);
            double ra = this.sunDecRA(2, jd2);
            final double h2 = this.elev(jd2, this.LAT, this.LONG, dec, ra) - h0;
            final double jd3 = jd2 + dt / 1440.0;
            dec = this.sunDecRA(1, jd3);
            ra = this.sunDecRA(2, jd3);
            final double h3 = this.elev(jd3, this.LAT, this.LONG, dec, ra) - h0;
            if (rs == 1) {
                if (h2 * h3 < 0.0 && h2 < 0.0 && h3 > 0.0) {
                    this.jdRise = 0.5 * (jd2 + jd3);
                    break;
                }
            }
            else if (h2 * h3 < 0.0 && h2 > 0.0 && h3 < 0.0) {
                this.jdRise = 0.5 * (jd2 + jd3);
                break;
            }
        }
        double dec = this.sunDecRA(1, this.jdRise);
        double ra = this.sunDecRA(2, this.jdRise);
        final double gha = this.GHA(this.jdRise, ra);
        double h3 = this.elev(this.jdRise, this.LAT, this.LONG, dec, ra);
        h3 += this.refract(h3) / 60.0;
        this.HH = h3;
        this.AZ = this.azim(this.jdRise, this.LAT, this.LONG);
    }
    
    double refract(final double h) {
        final double K = 0.017453292519943295;
        return 1.02 / Math.tan(K * (h + 10.3 / (h + 5.11)));
    }
    
    double rise() {
        return this.jdRise;
    }
    
    double azRise() {
        return this.AZ;
    }
    
    double elevSun() {
        return this.HH;
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
    
    double frac(double x) {
        x -= (int)x;
        if (x < 0.0) {
            ++x;
        }
        return x;
    }
    
    double elev(final double JD, final double LAT, final double LONG, final double DEC, final double RA) {
        final double K = 0.017453292519943295;
        double tau = 15.0 * (this.LMST(JD, LONG) - RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        final double sinH = Math.cos(0.017453292519943295 * LAT) * Math.cos(0.017453292519943295 * DEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * LAT) * Math.sin(0.017453292519943295 * DEC);
        return Math.asin(sinH) / 0.017453292519943295;
    }
    
    double LMST(final double JD, final double LONG) {
        final double GMST = this.GMST(JD);
        return 24.0 * this.frac((GMST - LONG / 15.0) / 24.0);
    }
    
    double GMST(final double JD) {
        final double MJD = JD - 2400000.5;
        final long MJD2 = (long)MJD;
        final double ut = (MJD - MJD2) * 24.0;
        final double t_eph = (MJD2 - 51544.5) / 36525.0;
        return 6.697374558 + 1.0027379093 * ut + (8640184.812866 + (0.093104 - 6.2E-6 * t_eph) * t_eph) * t_eph / 3600.0;
    }
    
    double GHA(final double jd, final double RA) {
        return 15.0 * (this.GMST(jd) - RA);
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
    
    double sun_GHA(final double jd, final double RA) {
        final double GMST = this.GM_Sidereal_Time(jd);
        double tau = 15.0 * (GMST - RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        if (tau < 0.0) {
            tau += 360.0;
        }
        return tau;
    }
    
    double GM_Sidereal_Time(final double JD) {
        final double MJD = JD - 2400000.5;
        final long MJD2 = (long)MJD;
        final double ut = (MJD - MJD2) * 24.0;
        final double t_eph = (MJD2 - 51544.5) / 36525.0;
        return 6.697374558 + 1.0027379093 * ut + (8640184.812866 + (0.093104 - 6.2E-6 * t_eph) * t_eph) * t_eph / 3600.0;
    }
    
    double azim(final double JD, final double LAT, final double LONG) {
        final double RA = this.sunDecRA(2, JD);
        final double DEC = this.sunDecRA(1, JD);
        final double h = this.THETA0(JD) - LONG - 15.0 * RA;
        double az = Math.atan2(Math.sin(0.017453292519943295 * h), Math.cos(0.017453292519943295 * h) * Math.sin(0.017453292519943295 * LAT) - Math.tan(0.017453292519943295 * DEC) * Math.cos(0.017453292519943295 * LAT)) / 0.017453292519943295;
        final double gha = this.sun_GHA(JD, RA);
        if (Math.sin(0.017453292519943295 * (gha - LONG)) <= 0.0) {
            az = az;
        }
        if (Math.sin(0.017453292519943295 * (gha - LONG)) > 0.0) {
            az += 180.0;
        }
        if (az < 0.0) {
            az += 180.0;
        }
        return az;
    }
}
