import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Date;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class compute extends Canvas
{
    double DEC;
    double RA;
    double EOT;
    int offset;
    String[] str;
    String hoursGMTStr;
    String minutesGMTStr;
    String secondsGMTStr;
    String dateGMTStr;
    double diff;
    
    compute(final Date dat) {
        this.str = new String[10];
        String s = "";
        final int hours = dat.getHours();
        final int minutes = dat.getMinutes();
        final int seconds = dat.getSeconds();
        int date = dat.getDate();
        int month = dat.getMonth();
        ++month;
        int year = dat.getYear();
        year += 1900;
        this.str[1] = dat.toString();
        this.str[2] = String.valueOf(dat.toGMTString().substring(0, dat.toGMTString().length() - 3)) + "UT";
        if (-dat.getTimezoneOffset() >= 0) {
            this.str[3] = "Time Zone        = UT + " + -dat.getTimezoneOffset() + " min";
        }
        else {
            this.str[3] = "Time Zone       = UT - " + dat.getTimezoneOffset() + " min";
        }
        final String datGMTStr = dat.toGMTString();
        final int n = datGMTStr.length();
        this.dateGMTStr = datGMTStr.substring(0, n - 22);
        date = Integer.parseInt(this.dateGMTStr);
        this.hoursGMTStr = datGMTStr.substring(n - 12, n - 10);
        final double UT = Integer.parseInt(this.hoursGMTStr) + minutes / 60.0 + seconds / 3600.0;
        this.EOT = this.eot(date, month, year, UT);
        this.diff = Math.abs(this.EOT) - (int)Math.abs(this.EOT);
        long min = (int)Math.round(this.diff * 60.0);
        if (min == 60L) {
            min = 0L;
            if (this.EOT >= 0.0) {
                ++this.EOT;
            }
            else {
                --this.EOT;
            }
        }
        if (min > 9L) {
            s = ":";
        }
        else {
            s = ":0";
        }
        this.str[4] = "Equation of Time = " + (int)this.EOT + s + min + " min ";
        if (this.EOT < 0.0 && (int)this.EOT == 0) {
            this.str[4] = "-" + (int)this.EOT + s + min + " min";
        }
        this.str[5] = "Declination      = " + Math.round(1000.0 * this.DEC) / 1000.0 + " °";
        final double Min = this.frac(Math.abs(this.DEC)) * 60.0;
        s = String.valueOf((int)Min);
        if (Min < 10.0) {
            s = "0" + s;
        }
        final long secs = Math.round(this.frac(Math.abs(Min)) * 60.0);
        String secStr = "";
        secStr = String.valueOf(secs);
        if (secs < 10L) {
            secStr = "0" + secStr;
        }
        this.str[6] = "Declination      = " + (int)this.DEC + "° " + s + "' " + secStr + "''";
        this.repaint();
    }
    
    double frac(double x) {
        x -= (int)x;
        if (x < 0.0) {
            ++x;
        }
        return x;
    }
    
    double JD(final int date, int month, int year, final double ut) {
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
    
    public double sunL(final double T) {
        double L = 280.46645 + 36000.76983 * T + 3.032E-4 * T * T;
        L %= 360.0;
        if (L < 0.0) {
            L += 360.0;
        }
        return L;
    }
    
    public double deltaPSI(final double T) {
        final double K = 0.017453292519943295;
        final double LS = this.sunL(T);
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
        final double LS = this.sunL(T);
        final double LM = 218.3165 + 481267.8813 * T;
        final double eps0 = 23.43929111111111 - (46.815 * T + 5.9E-4 * T * T - 0.001813 * T * T * T) / 3600.0;
        final double omega = 125.04452 - 1934.136261 * T + 0.0020708 * T * T + T * T * T / 450000.0;
        final double deltaEps = (9.2 * Math.cos(0.017453292519943295 * omega) + 0.57 * Math.cos(0.03490658503988659 * LS) + 0.1 * Math.cos(0.03490658503988659 * LM) - 0.09 * Math.cos(0.03490658503988659 * omega)) / 3600.0;
        return eps0 + deltaEps;
    }
    
    public double eot(final int date, final int month, final int year, final double UT) {
        final double K = 0.017453292519943295;
        final double T = (this.JD(date, month, year, UT) - 2451545.0) / 36525.0;
        final double eps = this.EPS(T);
        final double RA = this.RightAscension(T);
        final double LS = this.sunL(T);
        final double deltaPsi = this.deltaPSI(T);
        double E = LS - 0.0057183 - RA + deltaPsi * Math.cos(0.017453292519943295 * eps);
        E *= 4.0;
        return E;
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
        this.DEC = delta;
        return RA;
    }
    
    public void paint(final Graphics g) {
        for (int i = 1; i < 7; ++i) {
            g.drawString(this.str[i], 30, 30 + i * 18);
        }
        g.setColor(Color.blue);
        g.setFont(new Font("Courier", 0, 10));
        g.drawString("© Juergen Giesen 1999-2007", 50, 155);
        g.drawString("www.GeoAstro.de", 50, 170);
        g.setColor(Color.black);
        g.clipRect(0, 0, this.size().width, this.size().height);
    }
}
