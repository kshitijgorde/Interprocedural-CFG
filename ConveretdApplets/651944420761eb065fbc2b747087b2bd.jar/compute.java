// 
// Decompiled by Procyon v0.5.30
// 

class compute
{
    final char deg = '°';
    final double K = 0.017453292519943295;
    
    public double frac(double X) {
        X -= (int)X;
        if (X < 0.0) {
            ++X;
        }
        return X;
    }
    
    public int calDat(final int what, final double jd) {
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
        if (what == 1) {
            return day;
        }
        if (what == 2) {
            return month - 1;
        }
        if (what == 3) {
            return year;
        }
        return 0;
    }
    
    public String makeTimeString(double time) {
        String str = "?";
        time = this.check24(time);
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
    
    public double EPS(final double T) {
        final double LS = this.sunL(T);
        final double LM = 218.3165 + 481267.8813 * T;
        final double eps0 = 23.43929111111111 - (46.815 * T + 5.9E-4 * T * T - 0.001813 * T * T * T) / 3600.0;
        final double omega = 125.04452 - 1934.136261 * T + 0.0020708 * T * T + T * T * T / 450000.0;
        final double deltaEps = (9.2 * Math.cos(0.017453292519943295 * omega) + 0.57 * Math.cos(0.03490658503988659 * LS) + 0.1 * Math.cos(0.03490658503988659 * LM) - 0.09 * Math.cos(0.03490658503988659 * omega)) / 3600.0;
        return eps0 + deltaEps;
    }
    
    double sunL(final double T) {
        final double tau = T / 10.0;
        double L = 280.4664567 + 360007.6982779 * tau + 0.03032028 * tau * tau + tau * tau * tau / 49931.0 - tau * tau * tau * tau / 15299.0 + tau * tau * tau * tau * tau / 1988000.0;
        L %= 360.0;
        if (L < 0.0) {
            L += 360.0;
        }
        return L;
    }
    
    public double DeclinationRightAscension(final int what, final double jd) {
        final double T = (jd - 2451545.0) / 36525.0;
        final double L = this.sunL(T);
        double M = 357.5291 + 35999.0503 * T - 1.559E-4 * T * T - 4.8E-7 * T * T * T;
        M %= 360.0;
        if (M < 0.0) {
            M += 360.0;
        }
        double C = (1.9146 - 0.004817 * T - 1.4E-5 * T * T) * Math.sin(0.017453292519943295 * M);
        C += (0.019993 - 1.01E-4 * T) * Math.sin(0.03490658503988659 * M);
        C += 2.9E-4 * Math.sin(0.05235987755982989 * M);
        final double theta = L + C;
        double eps = this.EPS(T);
        eps += 0.00256 * Math.cos(0.017453292519943295 * (125.04 - 1934.136 * T));
        final double lambda = theta - 0.00569 - 0.00478 * Math.sin(0.017453292519943295 * (125.04 - 1934.136 * T));
        double RA = Math.atan2(Math.cos(0.017453292519943295 * eps) * Math.sin(0.017453292519943295 * lambda), Math.cos(0.017453292519943295 * lambda)) / 0.017453292519943295;
        if (RA < 0.0) {
            RA += 360.0;
        }
        final double delta = Math.asin(Math.sin(0.017453292519943295 * eps) * Math.sin(0.017453292519943295 * lambda)) / 0.017453292519943295;
        if (what == 1) {
            return delta;
        }
        return RA / 15.0;
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
        double tau = 15.0 * (this.LM_Sidereal_Time(JD, LONG) - RA);
        if (tau < 0.0) {
            tau += 360.0;
        }
        return Math.cos(0.017453292519943295 * LAT) * Math.cos(0.017453292519943295 * DEC) * Math.cos(0.017453292519943295 * tau) + Math.sin(0.017453292519943295 * LAT) * Math.sin(0.017453292519943295 * DEC);
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
        double t = 6.697374558 + 1.0027379093 * ut + (8640184.812866 + (0.093104 - 6.2E-6 * t_eph) * t_eph) * t_eph / 3600.0;
        t %= 24.0;
        if (t < 0.0) {
            t += 24.0;
        }
        return t;
    }
    
    public String azDirection(final double az) {
        final String[] dirStrArray = { "N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW" };
        final double d = (az - 11.25) / 22.5;
        final int n = (int)d;
        if (az < 11.25 || az > 348.75) {
            return "N";
        }
        return dirStrArray[n + 1];
    }
    
    double check24(double x) {
        if (x > 24.0) {
            x -= 24.0;
        }
        if (x < 0.0) {
            x += 24.0;
        }
        return x;
    }
    
    double check360(double x) {
        x %= 360.0;
        if (x > 360.0) {
            x -= 360.0;
        }
        if (x < 0.0) {
            x += 360.0;
        }
        return x;
    }
    
    public String tierkreisStr(double longitudo) {
        if (longitudo < 0.0) {
            longitudo += 360.0;
        }
        longitudo %= 360.0;
        final String[] TK = { "Ari", "Tau", "Gem", "Can", "Leo", "Vir", "Lib", "Sco", "Sag", "Cap", "Aqu", "Pis" };
        return TK[(int)(longitudo / 30.0)];
    }
    
    public double getDeltaT(final int date, final int month, final int year) {
        double a = this.getDT(year);
        if (a == 0.0) {
            return a;
        }
        if (a == 107.0) {
            return 107.0;
        }
        final double b = this.getDT(year + 1);
        a += (b - a) * (this.JD(date, month + 1, year + 1900, 0.0) - this.JD(1, 1, year + 1900, 0.0)) / 365.25;
        return a;
    }
    
    public double getDT(final int year) {
        final double[] DT = { 45.5, 46.5, 47.5, 48.5, 49.6, 50.5, 51.4, 52.2, 53.0, 53.8, 54.3, 54.9, 55.3, 55.8, 56.3, 56.9, 57.6, 58.3, 59.1, 60.0, 60.8, 61.6, 62.3, 63.0, 63.5, 63.8, 64.1, 64.3, 64.5, 64.6, 64.7, 65.2, 65.1, 65.5, 65.8 };
        final int y = year + 1900;
        if (y > 2009) {
            return 65.8 + (y - 2009) * 0.5;
        }
        if (y >= 1975 && y <= 2009) {
            return DT[y - 1975];
        }
        if (y >= 1900 && y < 1925) {
            final double u = (y - 1900) / 100.0;
            return -2.6 + 114.1 * u + 327.5 * u * u - 1467.4 * u * u * u;
        }
        if (y >= 1925 && y < 1950) {
            final double u = (y - 1925) / 100.0;
            return 24.2 - 6.3 * u - 8.2 * u * u + 483.4 * u * u * u;
        }
        if (y >= 1950 && y < 1975) {
            final double u = (y - 1950) / 100.0;
            return 29.3 + 32.5 * u - 3.8 * u * u + 550.7 * u * u * u;
        }
        if (y == 1610) {
            return 107.0;
        }
        return 0.0;
    }
    
    public double deltaPsi(final double T) {
        final double K = 0.017453292519943295;
        final double[][] L = { { 0.0, 0.0, 0.0, 0.0, 1.0, -171996.0, -174.2 }, { -2.0, 0.0, 0.0, 2.0, 2.0, -13187.0, -1.6 }, { 0.0, 0.0, 0.0, 2.0, 2.0, -2274.0, -0.2 }, { 0.0, 0.0, 0.0, 0.0, 2.0, 2062.0, 0.2 }, { 0.0, 1.0, 0.0, 0.0, 0.0, 1426.0, -3.4 }, { 0.0, 0.0, 1.0, 0.0, 0.0, 712.0, 0.1 }, { -2.0, 1.0, 0.0, 2.0, 2.0, -517.0, 1.2 }, { 0.0, 0.0, 0.0, 2.0, 1.0, -386.0, -0.4 }, { 0.0, 0.0, 1.0, 2.0, 2.0, -301.0, 0.0 }, { -2.0, -1.0, 0.0, 2.0, 2.0, 217.0, -0.5 }, { -2.0, 0.0, 1.0, 0.0, 0.0, -158.0, 0.0 }, { -2.0, 0.0, 0.0, 2.0, 1.0, 129.0, 0.1 }, { 0.0, 0.0, -1.0, 2.0, 2.0, 123.0, 0.0 }, { 2.0, 0.0, 0.0, 0.0, 0.0, 63.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0, 1.0, 63.0, 0.1 }, { 2.0, 0.0, -1.0, 2.0, 2.0, -59.0, 0.0 }, { 0.0, 0.0, -1.0, 0.0, 1.0, -58.0, -0.1 }, { 0.0, 0.0, 1.0, 2.0, 1.0, -51.0, 0.0 }, { -2.0, 0.0, 2.0, 0.0, 0.0, 48.0, 0.0 }, { 0.0, 0.0, -2.0, 2.0, 1.0, 46.0, 0.0 } };
        final double D = 297.85036 + 445267.11148 * T - 0.0019142 * T * T + T * T * T / 189474.0;
        final double M = 357.52772 + 35999.05034 * T - 1.603E-4 * T * T - T * T * T / 300000.0;
        final double Ms = 134.96298 + 477198.867398 * T + 0.0086972 * T * T + T * T * T / 56250.0;
        final double F = 93.27191 + 483202.017538 * T - 0.0036825 * T * T + T * T * T / 327270.0;
        final double omega = 125.04452 - 1934.136261 * T + 0.0020708 * T * T + T * T * T / 450000.0;
        double sum = 0.0;
        for (int i = 0; i <= 19; ++i) {
            sum += (L[i][5] + L[i][6] * T) * Math.sin(0.017453292519943295 * (L[i][0] * D + L[i][1] * M + L[i][2] * Ms + L[i][3] * F + L[i][4] * omega));
        }
        sum /= 10000.0;
        return sum / 3600.0;
    }
    
    public double deltaEps(final double T) {
        final double K = 0.017453292519943295;
        final double[][] L = { { 0.0, 0.0, 0.0, 0.0, 1.0, 92025.0, 8.9 }, { -2.0, 0.0, 0.0, 2.0, 2.0, 5736.0, -3.1 }, { 0.0, 0.0, 0.0, 2.0, 2.0, 977.0, -0.5 }, { 0.0, 0.0, 0.0, 0.0, 2.0, -895.0, 0.5 }, { 0.0, 1.0, 0.0, 0.0, 0.0, 54.0, -0.1 }, { 0.0, 0.0, 1.0, 0.0, 0.0, -7.0, 0.0 }, { -2.0, 1.0, 0.0, 2.0, 2.0, 224.0, -0.6 }, { 0.0, 0.0, 0.0, 2.0, 1.0, 200.0, 0.0 }, { 0.0, 0.0, 1.0, 2.0, 2.0, 129.0, -0.1 }, { -2.0, -1.0, 0.0, 2.0, 2.0, -95.0, 0.3 }, { -2.0, 0.0, 0.0, 2.0, 1.0, -70.0, 0.0 }, { 0.0, 0.0, -1.0, 2.0, 2.0, -53.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0, 1.0, -33.0, 0.0 }, { 2.0, 0.0, -1.0, 2.0, 2.0, 26.0, 0.0 }, { 0.0, 0.0, -1.0, 0.0, 1.0, 32.0, 0.0 }, { 0.0, 0.0, 1.0, 2.0, 1.0, 27.0, 0.0 }, { 0.0, 0.0, -2.0, 2.0, 1.0, -24.0, 0.0 }, { 2.0, 0.0, 0.0, 2.0, 2.0, 16.0, 0.0 }, { 0.0, 0.0, 2.0, 2.0, 2.0, 13.0, 0.0 }, { -2.0, 0.0, 1.0, 2.0, 2.0, -12.0, 0.0 }, { 0.0, 0.0, -1.0, 2.0, 1.0, -10.0, 0.0 }, { 2.0, 0.0, -1.0, 0.0, 1.0, -8.0, 0.0 }, { -2.0, 2.0, 0.0, 2.0, 2.0, 7.0, 0.0 }, { 0.0, 1.0, 0.0, 0.0, 1.0, 9.0, 0.0 } };
        final double D = 297.85036 + 445267.11148 * T - 0.0019142 * T * T + T * T * T / 189474.0;
        final double M = 357.52772 + 35999.05034 * T - 1.603E-4 * T * T - T * T * T / 300000.0;
        final double Ms = 134.96298 + 477198.867398 * T + 0.0086972 * T * T + T * T * T / 56250.0;
        final double F = 93.27191 + 483202.017538 * T - 0.0036825 * T * T + T * T * T / 327270.0;
        final double omega = 125.04452 - 1934.136261 * T + 0.0020708 * T * T + T * T * T / 450000.0;
        double sum = 0.0;
        for (int i = 0; i <= 23; ++i) {
            sum += (L[i][5] + L[i][6] * T) * Math.cos(0.017453292519943295 * (L[i][0] * D + L[i][1] * M + L[i][2] * Ms + L[i][3] * F + L[i][4] * omega));
        }
        sum /= 10000.0;
        return sum / 3600.0;
    }
    
    public int daysInMonth(final int m, final int y) {
        int n = 31;
        if (m == 0 || m == 2 || m == 4 || m == 6 || m == 7 || m == 9 || m == 11) {
            n = 31;
        }
        else if (m == 3 || m == 5 || m == 8 || m == 10) {
            n = 30;
        }
        else if (m == 1) {
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
    
    public int MonthInteger(final String mStr) {
        int m = 100;
        final String[] monthArray = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        for (int i = 0; i < 12; ++i) {
            if (mStr == monthArray[i]) {
                m = i;
                break;
            }
        }
        return m;
    }
    
    double magnitude(final int p, final double r) {
        double m = 0.0;
        if (p == 1) {
            m = 1.995 + 5.0 * Math.log(r) / 2.3026;
        }
        else if (p == 2) {
            m = 2.142 + 5.0 * Math.log(r) / 2.3026;
        }
        else if (p == 3) {
            m = 1.568 + 5.0 * Math.log(r) / 2.3026;
        }
        else if (p == 4) {
            m = 2.813 + 5.0 * Math.log(r) / 2.3026;
        }
        return m;
    }
    
    double JD(final int date, int month, int year, final double UT) {
        if (month <= 2) {
            month += 12;
            --year;
        }
        final int A = (int)(year / 100.0);
        final int B = 2 - A + (int)(A / 4.0);
        return (int)(365.25 * (year + 4716)) + (int)(30.6001 * (month + 1)) + date + B - 1524.5 + UT / 24.0;
    }
    
    public int rotateX(final double x, final double y, final double alpha) {
        return (int)Math.round(x * Math.cos(0.017453292519943295 * alpha) + y * Math.sin(0.017453292519943295 * alpha));
    }
    
    public int rotateY(final double x, final double y, final double alpha) {
        return (int)Math.round(-x * Math.sin(0.017453292519943295 * alpha) + y * Math.cos(0.017453292519943295 * alpha));
    }
    
    double refract(final double h) {
        return 1.02 / Math.tan(0.017453292519943295 * (h + 10.3 / (h + 5.11)));
    }
}
