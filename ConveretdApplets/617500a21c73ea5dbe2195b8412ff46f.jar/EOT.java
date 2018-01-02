// 
// Decompiled by Procyon v0.5.30
// 

class EOT
{
    double E;
    
    public String eot(final int date, final int month, final int year, final double UT) {
        final double K = 0.017453292519943295;
        final double T = (this.JD(date, month, year, UT) - 2451545.0) / 36525.0;
        final double eps = this.EPS(T);
        final double RA = this.RightAscension(T);
        final double LS = this.sunL(T);
        final double deltaPsi = this.deltaPSI(T);
        this.E = LS - 0.0057183 - RA + deltaPsi * Math.cos(0.017453292519943295 * eps);
        if (this.E > 5.0) {
            this.E -= 360.0;
        }
        this.E *= 4.0;
        return this.makeTimeString(this.E);
    }
    
    double value() {
        return this.E;
    }
    
    public double deltaPSI(final double T) {
        final double K = 0.017453292519943295;
        final double LS = this.sunAppL(T);
        double LM = 218.3165 + 481267.8813 * T;
        LM %= 360.0;
        if (LM < 0.0) {
            LM += 360.0;
        }
        final double omega = 125.04452 - 1934.136261 * T + 0.0020708 * T * T + T * T * T / 450000.0;
        double deltaPsi = -17.2 * Math.sin(0.017453292519943295 * omega) - 1.32 * Math.sin(0.03490658503988659 * LS) - 0.23 * Math.sin(0.03490658503988659 * LM) + 0.21 * Math.sin(0.03490658503988659 * omega);
        deltaPsi /= 3600.0;
        return deltaPsi;
    }
    
    public double EPS(final double T) {
        final double K = 0.017453292519943295;
        final double LS = this.sunAppL(T);
        final double LM = 218.3165 + 481267.8813 * T;
        final double eps0 = 23.433333333333334 + (21.448 - 46.815 * T - 5.9E-4 * T * T + 0.001813 * T * T * T) / 3600.0;
        final double omega = 125.04452 - 1934.136261 * T + 0.0020708 * T * T + T * T * T / 450000.0;
        final double deltaEps = (9.2 * Math.cos(0.017453292519943295 * omega) + 0.57 * Math.cos(0.03490658503988659 * LS) + 0.1 * Math.cos(0.03490658503988659 * LM) - 0.09 * Math.cos(0.03490658503988659 * omega)) / 3600.0;
        return eps0 + deltaEps;
    }
    
    public double sunAppL(final double T) {
        final double K = 0.017453292519943295;
        final double M = 357.5291 + 35999.0503 * T - 1.559E-4 * T * T - 4.8E-7 * T * T * T;
        final double C = (1.9146 - 0.004817 * T - 1.4E-5 * T * T) * Math.sin(0.017453292519943295 * M) + (0.019993 - 1.01E-4 * T) * Math.sin(0.03490658503988659 * M) + 2.9E-4 * Math.sin(0.05235987755982989 * M);
        final double L = 280.46645 + 36000.76983 * T + 3.032E-4 * T * T;
        double Theta = L + C;
        final double omega = 125.04 - 1934.136 * T;
        Theta = Theta - 0.00569 - 0.00478 * Math.sin(0.017453292519943295 * omega);
        Theta %= 360.0;
        if (Theta < 0.0) {
            Theta += 360.0;
        }
        return Theta;
    }
    
    public double JD(final int date, int month, int year, final double STD) {
        if (month <= 2) {
            month += 12;
            --year;
        }
        final double B = year / 400 - year / 100 + year / 4;
        final double A = 365.0 * year - 679004.0;
        final double MJD = A + B + (int)(30.6001 * (month + 1)) + date + STD / 24.0;
        return MJD + 2400000.5;
    }
    
    public double RightAscension(final double T) {
        final double K = 0.017453292519943295;
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
        double RA = Math.atan2(Math.cos(0.017453292519943295 * eps) * Math.sin(0.017453292519943295 * lambda), Math.cos(0.017453292519943295 * lambda));
        RA /= 0.017453292519943295;
        if (RA < 0.0) {
            RA += 360.0;
        }
        double delta = Math.asin(Math.sin(0.017453292519943295 * eps) * Math.sin(0.017453292519943295 * lambda));
        delta /= 0.017453292519943295;
        return RA;
    }
    
    public double sunL(final double T) {
        double L = 280.46645 + 36000.76983 * T + 3.032E-4 * T * T;
        final double tau = T / 10.0;
        L = 280.4664567 + 360007.6982779 * tau + 0.03032028 * tau * tau + tau * tau * tau / 49931.0 - tau * tau * tau * tau / 15299.0 + tau * tau * tau * tau * tau / 1988000.0;
        L %= 360.0;
        if (L < 0.0) {
            L += 360.0;
        }
        return L;
    }
    
    public String makeTimeString(double time) {
        final double diff = Math.abs(time) - (int)Math.abs(time);
        int min = (int)Math.round(diff * 60.0);
        if (min == 60) {
            min = 0;
            if (time >= 0.0) {
                ++time;
            }
            else {
                --time;
            }
        }
        String str;
        if (min > 9) {
            str = "m ";
        }
        else {
            str = "m 0";
        }
        String eqtStr = (int)time + str + min + "s";
        if (time > 0.0) {
            eqtStr = "+ " + (int)time + str + min + "s";
        }
        if (time < 0.0) {
            eqtStr = "- " + (int)Math.abs(time) + str + min + "s";
        }
        if (time < 0.0 && (int)time == 0) {
            eqtStr = "- " + (int)time + str + min + "s";
        }
        return eqtStr;
    }
}
