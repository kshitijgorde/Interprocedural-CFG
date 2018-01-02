import java.util.Date;

// 
// Decompiled by Procyon v0.5.30
// 

class compute
{
    final double K = 0.017453292519943295;
    
    public double frac(double X) {
        X -= (int)X;
        if (X < 0.0) {
            ++X;
        }
        return X;
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
        str = (int)time + str + min;
        if (time < 10.0) {
            str = "0" + str;
        }
        return str;
    }
    
    public String makeTimeString1(double h, int m, int s) {
        String str = "";
        if (s == 60) {
            s = 0;
            ++m;
        }
        int min;
        if ((min = m) == 60) {
            min = 0;
            ++h;
        }
        if (h < 0.0) {
            h += 24.0;
        }
        if (h > 24.0) {
            h -= 24.0;
        }
        final int H = (int)h;
        if (h < 10.0) {
            str = "0";
        }
        str = String.valueOf(str) + H + ":";
        if (min < 10) {
            str = String.valueOf(str) + "0";
        }
        str = String.valueOf(str) + min + ":";
        if (s < 10) {
            str = String.valueOf(str) + "0";
        }
        return String.valueOf(str) + s;
    }
    
    double GM_Sidereal_Time(final double JD) {
        final double MJD = JD - 2400000.5;
        final long MJD2 = (long)MJD;
        final double ut = (MJD - MJD2) * 24.0;
        final double t_eph = (MJD2 - 51544.5) / 36525.0;
        double x = 6.697374558 + 1.0027379093 * ut + (8640184.812866 + (0.093104 - 6.2E-6 * t_eph) * t_eph) * t_eph / 3600.0;
        x %= 24.0;
        if (x < 0.0) {
            x += 24.0;
        }
        return x;
    }
    
    double sin_elev(final double JD, final double LAT, final double LONG, final double DEC, final double RA) {
        double tau = 15.0 * (this.LMST(JD, LONG) - RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        return Math.cos(0.017453292519943295 * LAT) * Math.cos(0.017453292519943295 * DEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * LAT) * Math.sin(0.017453292519943295 * DEC);
    }
    
    double LMST(final double JD, final double LONG) {
        final double GMST = this.GM_Sidereal_Time(JD);
        return 24.0 * this.frac((GMST - LONG / 15.0) / 24.0);
    }
    
    public double Jul_Date(final int date, int month, int year, final double UT) {
        if (month <= 2) {
            month += 12;
            --year;
        }
        final double B = year / 400 - year / 100 + year / 4;
        final double A = 365.0 * year - 679004.0;
        final double MJD = A + B + (int)(30.6001 * (month + 1)) + date + UT / 24.0;
        return MJD + 2400000.5;
    }
    
    public String HMS(double x) {
        String str = "";
        x /= 15.0;
        if (x < 0.0) {
            x = Math.abs(x);
        }
        int HOUR = (int)x;
        int MIN = (int)(this.frac(x) * 60.0);
        double SEC = 60.0 * (this.frac(x) * 60.0 - MIN);
        if ((int)Math.round(SEC) == 60) {
            SEC = 0.0;
            ++MIN;
        }
        if (MIN == 60) {
            MIN = 0;
            ++HOUR;
        }
        if (HOUR >= 24) {
            HOUR -= 24;
        }
        if (HOUR < 10) {
            str = " ";
        }
        str = String.valueOf(str) + HOUR + "h ";
        if (MIN < 10) {
            str = String.valueOf(str) + " ";
        }
        str = String.valueOf(str) + MIN + "m ";
        if ((int)Math.round(SEC) < 10) {
            str = String.valueOf(str) + "0";
        }
        str = String.valueOf(str) + Math.round(SEC) + "s";
        return str;
    }
    
    public String HMS1(final double x) {
        String str = "";
        int HOUR = (int)x;
        int MIN = (int)(this.frac(x) * 60.0);
        double SEC = 60.0 * (this.frac(x) * 60.0 - MIN);
        if ((int)Math.round(SEC) == 60) {
            SEC = 0.0;
            ++MIN;
        }
        if (MIN == 60) {
            MIN = 0;
            ++HOUR;
        }
        if (HOUR > 26) {
            HOUR -= 24;
        }
        if (HOUR < 10) {
            str = " ";
        }
        str = String.valueOf(str) + HOUR + "h ";
        if (MIN < 10) {
            str = String.valueOf(str) + " ";
        }
        str = String.valueOf(str) + MIN + "m ";
        if ((int)Math.round(SEC) < 10) {
            str = String.valueOf(str) + "0";
        }
        str = String.valueOf(str) + Math.round(SEC) + "s";
        return str;
    }
    
    double moon_LHA(final int date, final int month, final int year, final double UT, final int locOffset, final double latitude, final double longitude) {
        final double jd = this.Jul_Date(date, month + 1, year + 1900, UT);
        final Date dat = new Date();
        dat.setYear(year);
        dat.setMonth(month);
        dat.setDate(date);
        dat.setHours((int)UT);
        dat.setMinutes((int)Math.round(60.0 * this.frac(UT)));
        final Moon moon = new Moon("", dat, latitude, -longitude, locOffset, "");
        final double moonRA = moon.alpha();
        double moonLHA = this.THETA0(jd) - longitude - moonRA;
        if (moonLHA < 0.0) {
            moonLHA += 360.0;
        }
        return moonLHA;
    }
    
    double sun_LHA(final int date, final int month, final int year, final double UT, final int locOffset, final double latitude, final double longitude) {
        final double JD = this.Jul_Date(date, month + 1, year + 1900, UT);
        final Date dat = new Date();
        dat.setYear(year);
        dat.setMonth(month);
        dat.setDate(date);
        dat.setHours((int)UT);
        dat.setMinutes((int)Math.round(60.0 * this.frac(UT)));
        final Rise_Set rs = new Rise_Set(dat, latitude, longitude, locOffset, -0.833);
        final double RA = rs.sunDecRA(2, JD);
        final double sunGHA = this.sun_GHA(JD, RA);
        double sunLHA = sunGHA - longitude;
        if (sunLHA < 0.0) {
            sunLHA += 360.0;
        }
        sunLHA %= 360.0;
        return sunLHA;
    }
    
    double sun_GHA(final double JD, final double RA) {
        final double GMST = this.GM_Sidereal_Time(JD);
        double tau = 15.0 * (GMST - RA);
        tau %= 360.0;
        if (tau < 0.0) {
            tau += 360.0;
        }
        return tau;
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
    
    public int daysInMonth(final int m, int y) {
        int n = 31;
        y += 1900;
        if (m == 0 | m == 2 | m == 4 | m == 6 | m == 7 | m == 9 | m == 11) {
            n = 31;
        }
        if (m == 3 | m == 5 | m == 8 | m == 10) {
            n = 30;
        }
        if (m == 1) {
            n = 28;
            if (y % 4 == 0) {
                n = 29;
            }
            if (y % 100 == 0) {
                n = 28;
            }
            if (y % 400 == 0) {
                n = 29;
            }
        }
        return n;
    }
    
    public double caldat(final int what, final double JD) {
        final int Z = (int)(JD + 0.5);
        final double F = this.frac(JD + 0.5);
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
        if (what == 1) {
            return day;
        }
        if (what == 2) {
            return month;
        }
        if (what == 3) {
            return 24.0 * this.frac(DAY);
        }
        return year;
    }
    
    public double moonDecRA(final int what, final double jd) {
        final double P2 = 6.283185307179586;
        final double ARC = 206264.8062;
        final double coseps = 0.917482062;
        final double sineps = 0.397777156;
        final double K = 0.017453292519943295;
        final double T = (jd - 2451545.0) / 36525.0;
        final double L0 = this.frac(0.606433 + 1336.855225 * T);
        final double L2 = P2 * this.frac(0.374897 + 1325.55241 * T);
        final double LS = P2 * this.frac(0.993133 + 99.997361 * T);
        final double D = P2 * this.frac(0.827361 + 1236.853086 * T);
        final double F = P2 * this.frac(0.259086 + 1342.227825 * T);
        final double DL = 22640.0 * Math.sin(L2) - 4586.0 * Math.sin(L2 - 2.0 * D) + 2370.0 * Math.sin(2.0 * D) + 769.0 * Math.sin(2.0 * L2) - 668.0 * Math.sin(LS) - 412.0 * Math.sin(2.0 * F) - 212.0 * Math.sin(2.0 * L2 - 2.0 * D) - 206.0 * Math.sin(L2 + LS - 2.0 * D) + 192.0 * Math.sin(L2 + 2.0 * D) - 165.0 * Math.sin(LS - 2.0 * D) - 125.0 * Math.sin(D) - 110.0 * Math.sin(L2 + LS) + 148.0 * Math.sin(L2 - LS) - 55.0 * Math.sin(2.0 * F - 2.0 * D);
        final double S = F + (DL + 412.0 * Math.sin(2.0 * F) + 541.0 * Math.sin(LS)) / ARC;
        final double H = F - 2.0 * D;
        final double N = -526.0 * Math.sin(H) + 44.0 * Math.sin(L2 + H) - 31.0 * Math.sin(-L2 + H) - 23.0 * Math.sin(LS + H) + 11.0 * Math.sin(-LS + H) - 25.0 * Math.sin(-2.0 * L2 + F) + 21.0 * Math.sin(-L2 + F);
        final double L_Moon = P2 * this.frac(L0 + DL / 1296000.0);
        final double B_Moon = (18520.0 * Math.sin(S) + N) / ARC;
        final double CB = Math.cos(B_Moon);
        final double X = CB * Math.cos(L_Moon);
        final double V = CB * Math.sin(L_Moon);
        final double W = Math.sin(B_Moon);
        final double Y = coseps * V - sineps * W;
        final double Z = sineps * V + coseps * W;
        final double rho = Math.sqrt(1.0 - Z * Z);
        final double DEC = 57.29577951308232 * Math.atan2(Z, rho);
        final double RA = 7.639437268410976 * Math.atan2(Y, X + rho);
        if (what == 1) {
            return DEC;
        }
        return RA;
    }
}
