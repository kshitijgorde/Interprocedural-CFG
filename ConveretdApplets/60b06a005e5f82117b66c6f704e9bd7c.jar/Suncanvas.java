import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Date;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class Suncanvas extends Canvas
{
    String str;
    final double K = 0.017453292519943295;
    int min;
    double UT;
    double equation;
    double trans;
    double diff;
    Date dat;
    int locOffset;
    int year;
    int month;
    int date;
    int hours;
    int minutes;
    int seconds;
    double latitude;
    double longitude;
    String eqtStr;
    String riseStr;
    String setStr;
    String transStr;
    Rise_Set rs;
    double rise;
    double set;
    int xE;
    int yE;
    int xM;
    int yM;
    int x9;
    char deg;
    int sun;
    double RA;
    int max;
    int oben;
    int minWOZ;
    int secWOZ;
    int hWOZ;
    double GOZ;
    int minGOZ;
    int hGOZ;
    int secGOZ;
    int xUT;
    int yUT;
    int R;
    int r;
    int rSec;
    int rMin;
    int rH;
    double ST;
    double st;
    int minST;
    int secST;
    int MIN;
    int sec;
    double WOZ;
    
    public Suncanvas(final int _date, final int _month, final int _year, final int _hours, final int _minutes, final int _seconds, final double myLat, final double myLong, final String myLoc, final int myLocOffset, final int mySun) {
        this.deg = '°';
        this.minWOZ = 0;
        this.secWOZ = 0;
        this.hWOZ = 0;
        this.GOZ = 0.0;
        this.minGOZ = 0;
        this.hGOZ = 0;
        this.secGOZ = 0;
        this.xUT = 120;
        this.yUT = 150;
        this.R = 60;
        this.r = 52;
        this.rSec = 50;
        this.rMin = 40;
        this.rH = 30;
        this.ST = 0.0;
        this.MIN = 0;
        this.sec = 0;
        this.WOZ = 0.0;
        this.latitude = myLat;
        this.longitude = -myLong;
        this.locOffset = myLocOffset;
        this.sun = mySun;
        (this.dat = new Date()).setDate(_date);
        this.dat.setMonth(_month);
        this.dat.setYear(_year - 1900);
        this.dat.setHours(_hours);
        this.dat.setMinutes(_minutes);
        this.dat.setSeconds(_seconds);
        this.year = _year;
        this.month = _month;
        this.date = _date;
        this.hours = _hours;
        this.hours = _hours;
        this.minutes = _minutes;
        this.seconds = _seconds;
        if (mySun == 1) {
            this.max = 6;
        }
        else {
            this.max = 4;
        }
        if (mySun == 2) {
            this.oben = 160;
        }
        else {
            this.oben = 320;
        }
        this.UT = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        this.equation = this.eot(this.date, this.month + 1, this.year, this.UT);
        this.diff = Math.abs(this.equation) - (int)Math.abs(this.equation);
        this.min = (int)Math.round(this.diff * 60.0);
        if (this.min == 60) {
            this.min = 0;
            if (this.equation >= 0.0) {
                ++this.equation;
            }
            else {
                --this.equation;
            }
        }
        if (this.min > 9) {
            this.str = " min ";
        }
        else {
            this.str = " min 0";
        }
        this.eqtStr = (int)this.equation + this.str + this.min + " s";
        if (this.equation > 0.0) {
            this.eqtStr = "+ " + (int)this.equation;
        }
        if (this.equation < 0.0) {
            this.eqtStr = "- " + (int)Math.abs(this.equation);
        }
        if (this.equation < 0.0 && (int)this.equation == 0) {
            this.eqtStr = "- " + (int)this.equation;
        }
        this.eqtStr = String.valueOf(this.eqtStr) + this.str + this.min + " s";
        this.rs = new Rise_Set(this.dat, this.latitude, this.longitude, this.locOffset, -0.833);
        this.riseStr = this.rs.rise_String();
        this.setStr = this.rs.set_String();
        this.rise = this.rs.h_rise();
        this.set = this.rs.h_set();
        this.trans = 12.0 - this.equation / 60.0 + this.longitude / 15.0;
        this.trans += this.locOffset;
        this.transStr = this.rs.makeTimeString("", this.trans);
        this.RA = this.rs.sunDecRA(2, this.Jul_Date(this.date, this.month + 1, this.year, this.UT));
    }
    
    public String makeTimeString(double h, int m, int s) {
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
    
    public String makeTimeString1(double h, int m, double s) {
        String str = "";
        if (s == 60.0) {
            s = 0.0;
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
        if (s < 10.0) {
            str = String.valueOf(str) + "0";
        }
        return String.valueOf(str) + Math.round(10.0 * s) / 10.0;
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
        final double T = (this.Jul_Date(date, month, year, UT) - 2451545.0) / 36525.0;
        final double eps = this.EPS(T);
        final double RA = this.RightAscension(T);
        final double LS = this.sunL(T);
        final double deltaPsi = this.deltaPSI(T);
        double E = LS - 0.0057183 - RA + deltaPsi * Math.cos(0.017453292519943295 * eps);
        if (E > 5.0) {
            E -= 360.0;
        }
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
        return RA;
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
    
    double frac(double x) {
        x -= (int)x;
        if (x < 0.0) {
            ++x;
        }
        return x;
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
        double x = 6.697374558 + 1.0027379093 * ut + (8640184.812866 + (0.093104 - 6.2E-6 * t_eph) * t_eph) * t_eph / 3600.0;
        x %= 24.0;
        if (x < 0.0) {
            x += 24.0;
        }
        return x;
    }
    
    public String HMS(double x) {
        String str = "";
        if (x < 0.0) {
            x = Math.abs(x);
        }
        final int HOUR = (int)x;
        final int MIN = (int)(this.frac(x) * 60.0);
        final double SEC = 60.0 * (this.frac(x) * 60.0 - MIN);
        if (HOUR < 10) {
            str = " ";
        }
        str = String.valueOf(str) + HOUR + "h ";
        if (MIN < 10) {
            str = String.valueOf(str) + " ";
        }
        str = String.valueOf(str) + MIN + "m ";
        if (SEC < 10.0) {
            str = String.valueOf(str) + " ";
        }
        return String.valueOf(str) + Math.round(SEC) + "s";
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
        final int DEG = (int)X;
        final int MIN = (int)Math.round(this.frac(X) * 60.0);
        if (x < 0.0) {
            str = "-";
        }
        else {
            str = "+";
        }
        if (DEG < 10) {
            str = " " + str;
        }
        if (DEG < 100) {
            str = " " + str;
        }
        str = String.valueOf(str) + DEG + " min ";
        if (MIN < 10) {
            str = String.valueOf(str) + "0";
        }
        return String.valueOf(str) + MIN + " s";
    }
    
    public void paint(final Graphics g) {
        g.setColor(Color.black);
        g.setFont(new Font("Helvetica", 0, 10));
        if (this.sun < 2) {
            g.drawRect(20, 80, 560, 170);
            g.drawRect(20, 250, 560, 170);
        }
        if (this.sun == 1) {
            g.drawRect(20, 420, 560, 170);
        }
        if (this.sun == 2) {
            g.drawRect(20, 80, 560, 208);
        }
        double SEC = 0.0;
        g.setColor(Color.black);
        g.drawString("Sunrise", 400, this.oben);
        g.drawString(this.riseStr, 490, this.oben);
        g.drawString("Culmination", 400, this.oben + 15);
        g.drawString(this.transStr, 490, this.oben + 15);
        g.drawString("Sunset", 400, this.oben + 30);
        g.drawString(this.setStr, 490, this.oben + 30);
        g.drawString("Equation of Time", 400, this.oben + 45);
        g.drawString(String.valueOf(this.eqtStr), 490, this.oben + 45);
        final String longOffsetStr = String.valueOf(this.DM((-this.longitude - this.locOffset * 15) * 4.0));
        g.drawString("Longitude Offset", 400, this.oben + 60);
        g.drawString(String.valueOf(longOffsetStr), 490, this.oben + 60);
        final double JD = this.Jul_Date(this.date, this.month + 1, this.year, this.UT);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        if (this.sun == 2) {
            final double currentTime = this.hours + this.minutes / 60.0 + this.seconds / 3600.0 - this.locOffset;
            this.WOZ = currentTime - this.longitude / 15.0 + this.equation / 60.0;
            this.WOZ %= 24.0;
            this.hWOZ = (int)this.WOZ;
            this.MIN = (int)(this.frac(this.WOZ) * 60.0);
            this.minWOZ = this.MIN;
            this.sec = (int)Math.round(this.frac(this.WOZ) * 3600.0 - this.MIN * 60);
            this.secWOZ = this.sec;
            final int r = 70;
            final int rh = 50;
            g.setFont(new Font("Courier", 0, 10));
            this.xUT = 110;
            this.yUT = 160;
            g.setColor(Color.yellow);
            g.drawOval(this.xUT - r, this.yUT - r, 2 * r, 2 * r);
            g.setColor(Color.white);
            g.fillOval(this.xUT - r + 1, this.yUT - r + 1, 2 * r - 2, 2 * r - 2);
            g.setColor(Color.black);
            g.drawString("0", this.xUT - 3, this.yUT - r + 14);
            g.drawString("12", this.xUT - 7, this.yUT + r - 5);
            g.drawString("18", this.xUT - r + 6, this.yUT + 3);
            g.drawString("6", this.xUT + r - 12, this.yUT + 3);
            for (int i = 0; i < 12; ++i) {
                final int xH = this.xUT + (int)Math.round((rh + 10) * Math.sin(i * 30 * 0.017453292519943295));
                final int yH = this.yUT + (int)Math.round((rh + 10) * Math.cos(i * 30 * 0.017453292519943295));
                if (i * 30 % 90 != 0) {
                    g.fillOval(xH - 2, yH - 2, 4, 4);
                }
            }
            for (int j = 0; j < 12; ++j) {
                final double winkel = 0.017453292519943295 * (15 + j * 30);
                final int xH = this.xUT + (int)Math.round((rh + 10) * Math.sin(winkel));
                final int yH = this.yUT + (int)Math.round((rh + 10) * Math.cos(winkel));
                g.drawOval(xH - 1, yH - 1, 2, 2);
            }
            double h = this.WOZ * 15.0;
            int xH = (int)Math.round(rh * Math.sin(h * 0.017453292519943295));
            int yH = (int)Math.round(rh * Math.cos(h * 0.017453292519943295));
            g.drawLine(this.xUT, this.yUT, this.xUT + xH, this.yUT - yH);
            g.drawLine(this.xUT + 1, this.yUT, this.xUT + xH, this.yUT - yH);
            g.drawLine(this.xUT + 2, this.yUT, this.xUT + xH, this.yUT - yH);
            g.drawLine(this.xUT - 2, this.yUT, this.xUT + xH, this.yUT - yH);
            g.drawLine(this.xUT - 1, this.yUT, this.xUT + xH, this.yUT - yH);
            g.drawLine(this.xUT, this.yUT + 2, this.xUT + xH, this.yUT - yH);
            g.drawLine(this.xUT, this.yUT + 1, this.xUT + xH, this.yUT - yH);
            g.drawLine(this.xUT, this.yUT - 2, this.xUT + xH, this.yUT - yH);
            g.drawLine(this.xUT, this.yUT - 1, this.xUT + xH, this.yUT - yH);
            g.setColor(Color.yellow);
            g.fillOval(this.xUT - 3, this.yUT - 3, 6, 6);
            g.setColor(Color.black);
            g.drawOval(this.xUT - 3, this.yUT - 3, 6, 6);
            g.setFont(new Font("Helvetica", 0, 10));
            g.drawString("Local Hour Angle", this.xUT - 70, this.yUT + 115);
            g.drawString("Local Hour Angle", 220, this.yUT + 115);
            g.drawString("Local Solar Time", this.xUT - 70, this.yUT + 100);
            g.setFont(new Font("Courier", 0, 12));
            g.drawString(this.makeTimeString(this.hWOZ, this.minWOZ, this.secWOZ), this.xUT + 20, this.yUT + 100);
            this.str = new StringBuffer().append(Math.round(150.0 * (this.WOZ - 12.0)) / 10.0).append(this.deg).toString();
            if (this.WOZ < 12.0) {
                this.str = new StringBuffer().append(360.0 + Math.round(150.0 * (this.WOZ - 12.0)) / 10.0).append(this.deg).toString();
            }
            g.drawString(this.str, this.xUT + 20, this.yUT + 115);
            this.xUT += 190;
            g.setColor(Color.blue);
            g.drawOval(this.xUT - r, this.yUT - r, 2 * r, 2 * r);
            g.setColor(Color.white);
            g.fillOval(this.xUT - r + 1, this.yUT - r + 1, 2 * r - 2, 2 * r - 2);
            g.setColor(Color.black);
            g.setFont(new Font("Courier", 0, 10));
            g.drawString("0", this.xUT - 3, this.yUT - r + 14);
            g.drawString("12", this.xUT - 7, this.yUT + r - 5);
            g.drawString("18", this.xUT - r + 6, this.yUT + 3);
            g.drawString("6", this.xUT + r - 12, this.yUT + 3);
            this.ST = this.LM_Sidereal_Time(JD, this.longitude);
            this.st = this.frac(this.ST);
            this.MIN = (int)(this.st * 60.0);
            this.sec = (int)Math.round(this.st * 3600.0 - this.MIN * 60);
            h = this.ST % 24.0;
            xH = (int)Math.round(rh * Math.sin(h * 15.0 * 0.017453292519943295));
            yH = (int)Math.round(rh * Math.cos(h * 15.0 * 0.017453292519943295));
            g.drawLine(this.xUT, this.yUT, this.xUT + xH, this.yUT - yH);
            g.drawLine(this.xUT + 1, this.yUT, this.xUT + xH, this.yUT - yH);
            g.drawLine(this.xUT + 2, this.yUT, this.xUT + xH, this.yUT - yH);
            g.drawLine(this.xUT - 2, this.yUT, this.xUT + xH, this.yUT - yH);
            g.drawLine(this.xUT - 1, this.yUT, this.xUT + xH, this.yUT - yH);
            g.drawLine(this.xUT, this.yUT + 2, this.xUT + xH, this.yUT - yH);
            g.drawLine(this.xUT, this.yUT + 1, this.xUT + xH, this.yUT - yH);
            g.drawLine(this.xUT, this.yUT - 2, this.xUT + xH, this.yUT - yH);
            g.drawLine(this.xUT, this.yUT - 1, this.xUT + xH, this.yUT - yH);
            g.setColor(Color.blue);
            g.fillOval(this.xUT - 3, this.yUT - 3, 6, 6);
            g.setColor(Color.black);
            g.drawOval(this.xUT - 3, this.yUT - 3, 6, 6);
            g.setFont(new Font("Helvetica", 0, 10));
            g.drawString("Local Sidereal Time", this.xUT - 80, this.yUT + 100);
            g.setFont(new Font("Courier", 0, 12));
            g.drawString(this.makeTimeString(this.ST, this.MIN, this.sec), this.xUT + 20, this.yUT + 100);
            g.drawString(new StringBuffer().append(Math.round(100.0 * this.ST * 15.0) / 100.0).append(this.deg).toString(), this.xUT + 20, this.yUT + 115);
            for (int k = 0; k < 12; ++k) {
                xH = this.xUT + (int)Math.round((rh + 10) * Math.sin(k * 30 * 0.017453292519943295));
                yH = this.yUT + (int)Math.round((rh + 10) * Math.cos(k * 30 * 0.017453292519943295));
                if (k * 30 % 90 != 0) {
                    g.fillOval(xH - 2, yH - 2, 4, 4);
                }
            }
            for (int l = 0; l < 12; ++l) {
                final double winkel = 0.017453292519943295 * (15 + l * 30);
                xH = this.xUT + (int)Math.round((rh + 10) * Math.sin(winkel));
                yH = this.yUT + (int)Math.round((rh + 10) * Math.cos(winkel));
                g.drawOval(xH - 1, yH - 1, 2, 2);
            }
        }
        else {
            for (int m = 0; m < this.max; ++m) {
                g.setFont(new Font("Courier", 0, 12));
                this.xUT = 110 + m % 2 * 180;
                if (m == 2 || m == 3) {
                    this.yUT = 320;
                }
                if (m == 4) {
                    this.yUT = 490;
                }
                g.setColor(Color.blue);
                g.drawOval(this.xUT - this.R, this.yUT - this.R, 2 * this.R, 2 * this.R);
                g.setColor(Color.white);
                g.fillOval(this.xUT - this.R + 1, this.yUT - this.R + 1, 2 * this.R - 2, 2 * this.R - 2);
                g.setColor(Color.black);
                g.drawString("12", this.xUT - 6, this.yUT - this.R + 14);
                g.drawString("6", this.xUT - 4, this.yUT + this.R - 5);
                g.drawString("9", this.xUT - this.R + 8, this.yUT + 3);
                g.drawString("3", this.xUT + this.R - 12, this.yUT + 3);
                switch (m) {
                    case 1: {
                        this.ST = this.GM_Sidereal_Time(JD);
                        this.st = this.frac(this.ST);
                        this.MIN = (int)(this.st * 60.0);
                        this.sec = (int)Math.round(this.st * 3600.0 - this.MIN * 60);
                        SEC = this.st * 3600.0 - this.MIN * 60.0;
                        break;
                    }
                    case 3: {
                        this.ST = this.LM_Sidereal_Time(JD, this.longitude);
                        this.st = this.frac(this.ST);
                        this.MIN = (int)(this.st * 60.0);
                        this.sec = (int)Math.round(this.st * 3600.0 - this.MIN * 60);
                        SEC = this.st * 3600.0 - this.MIN * 60.0;
                        break;
                    }
                    case 0: {
                        this.MIN = this.minutes;
                        this.sec = this.seconds;
                        break;
                    }
                    case 2: {
                        this.MIN = this.minutes;
                        this.sec = this.seconds;
                        break;
                    }
                    case 4: {
                        final double currentTime2 = this.hours + this.minutes / 60.0 + this.seconds / 3600.0 - this.locOffset;
                        this.WOZ = currentTime2 - this.longitude / 15.0 + this.equation / 60.0;
                        this.hWOZ = (int)this.WOZ;
                        this.MIN = (int)(this.frac(this.WOZ) * 60.0);
                        this.minWOZ = this.MIN;
                        this.sec = (int)Math.round(this.frac(this.WOZ) * 3600.0 - this.MIN * 60);
                        this.secWOZ = this.sec;
                        break;
                    }
                    case 5: {
                        final double currentTime2 = this.hours + this.minutes / 60.0 + this.seconds / 3600.0 - this.locOffset;
                        this.GOZ = currentTime2 + this.equation / 60.0;
                        this.hGOZ = (int)this.GOZ;
                        this.MIN = (int)(this.frac(this.GOZ) * 60.0);
                        this.minGOZ = this.MIN;
                        this.sec = (int)Math.round(this.frac(this.GOZ) * 3600.0 - this.MIN * 60);
                        this.secGOZ = this.sec;
                        break;
                    }
                }
                final int xSec = (int)Math.round(this.rSec * Math.sin(this.sec * 6 * 0.017453292519943295));
                final int ySec = (int)Math.round(this.rSec * Math.cos(this.sec * 6 * 0.017453292519943295));
                g.drawLine(this.xUT, this.yUT, this.xUT + xSec, this.yUT - ySec);
                int x = (int)Math.round(this.r * Math.sin(0.5235987755982988));
                int y = (int)Math.round(this.r * Math.cos(0.5235987755982988));
                g.fillOval(this.xUT + x - 2, this.yUT - y - 2, 4, 4);
                x = (int)Math.round(this.r * Math.sin(1.0471975511965976));
                y = (int)Math.round(this.r * Math.cos(1.0471975511965976));
                g.fillOval(this.xUT + x - 2, this.yUT - y - 2, 4, 4);
                x = (int)Math.round(this.r * Math.sin(2.0943951023931953));
                y = (int)Math.round(this.r * Math.cos(2.0943951023931953));
                g.fillOval(this.xUT + x - 2, this.yUT - y - 2, 4, 4);
                x = (int)Math.round(this.r * Math.sin(2.6179938779914944));
                y = (int)Math.round(this.r * Math.cos(2.6179938779914944));
                g.fillOval(this.xUT + x - 2, this.yUT - y - 2, 4, 4);
                x = (int)Math.round(this.r * Math.sin(3.6651914291880923));
                y = (int)Math.round(this.r * Math.cos(3.6651914291880923));
                g.fillOval(this.xUT + x - 2, this.yUT - y - 2, 4, 4);
                x = (int)Math.round(this.r * Math.sin(4.1887902047863905));
                y = (int)Math.round(this.r * Math.cos(4.1887902047863905));
                g.fillOval(this.xUT + x - 2, this.yUT - y - 2, 4, 4);
                x = (int)Math.round(this.r * Math.sin(5.235987755982989));
                y = (int)Math.round(this.r * Math.cos(5.235987755982989));
                g.fillOval(this.xUT + x - 2, this.yUT - y - 2, 4, 4);
                x = (int)Math.round(this.r * Math.sin(5.759586531581287));
                y = (int)Math.round(this.r * Math.cos(5.759586531581287));
                g.fillOval(this.xUT + x - 2, this.yUT - y - 2, 4, 4);
                g.setColor(Color.red);
                final int xMin = (int)Math.round(this.rMin * Math.sin(this.MIN * 6 * 0.017453292519943295));
                final int yMin = (int)Math.round(this.rMin * Math.cos(this.MIN * 6 * 0.017453292519943295));
                g.drawLine(this.xUT, this.yUT, this.xUT + xMin, this.yUT - yMin);
                g.drawLine(this.xUT + 1, this.yUT, this.xUT + xMin, this.yUT - yMin);
                g.drawLine(this.xUT - 1, this.yUT, this.xUT + xMin, this.yUT - yMin);
                g.drawLine(this.xUT, this.yUT + 1, this.xUT + xMin, this.yUT - yMin);
                g.drawLine(this.xUT, this.yUT - 1, this.xUT + xMin, this.yUT - yMin);
                double h2 = this.UT % 12.0;
                switch (m) {
                    case 0: {
                        h2 = this.UT % 12.0;
                        break;
                    }
                    case 2: {
                        h2 = (this.UT + this.locOffset) % 12.0;
                        break;
                    }
                    case 1: {
                        h2 = this.ST % 12.0;
                        break;
                    }
                    case 3: {
                        h2 = this.ST % 12.0;
                        break;
                    }
                    case 4: {
                        h2 = this.WOZ;
                        break;
                    }
                }
                final int xH2 = (int)Math.round(this.rH * Math.sin(h2 * 30.0 * 0.017453292519943295));
                final int yH2 = (int)Math.round(this.rH * Math.cos(h2 * 30.0 * 0.017453292519943295));
                g.drawLine(this.xUT, this.yUT, this.xUT + xH2, this.yUT - yH2);
                g.drawLine(this.xUT + 1, this.yUT, this.xUT + xH2, this.yUT - yH2);
                g.drawLine(this.xUT + 2, this.yUT, this.xUT + xH2, this.yUT - yH2);
                g.drawLine(this.xUT - 2, this.yUT, this.xUT + xH2, this.yUT - yH2);
                g.drawLine(this.xUT - 1, this.yUT, this.xUT + xH2, this.yUT - yH2);
                g.drawLine(this.xUT, this.yUT + 2, this.xUT + xH2, this.yUT - yH2);
                g.drawLine(this.xUT, this.yUT + 1, this.xUT + xH2, this.yUT - yH2);
                g.drawLine(this.xUT, this.yUT - 2, this.xUT + xH2, this.yUT - yH2);
                g.drawLine(this.xUT, this.yUT - 1, this.xUT + xH2, this.yUT - yH2);
                g.setColor(Color.black);
                g.fillOval(this.xUT - 3, this.yUT - 3, 6, 6);
                g.setColor(Color.red);
                switch (m) {
                    case 1: {
                        g.setFont(new Font("Courier", 0, 12));
                        g.drawString("GHA Aries   " + Math.round(1500.0 * this.ST) / 100.0 + this.deg, 400, 230);
                        g.drawString(this.makeTimeString1(this.ST, this.MIN, SEC), this.xUT - 30, this.yUT + this.R + 20);
                        g.setFont(new Font("Helvetiva", 0, 10));
                        g.drawString("Greenwich Sidereal Time", this.xUT - 58, this.yUT + this.R + 35);
                        break;
                    }
                    case 3: {
                        g.setFont(new Font("Courier", 0, 12));
                        g.drawString("LHA Aries    " + Math.round(1500.0 * this.ST) / 100.0 + this.deg, 400, 400);
                        g.drawString(this.makeTimeString1(this.ST, this.MIN, SEC), this.xUT - 30, this.yUT + this.R + 20);
                        g.setFont(new Font("Helvetiva", 0, 10));
                        g.drawString("Local Sidereal Time", this.xUT - 50, this.yUT + this.R + 35);
                        break;
                    }
                    case 4: {
                        g.setColor(Color.black);
                        g.setFont(new Font("Courier", 0, 12));
                        g.drawString(this.makeTimeString(this.hWOZ, this.minWOZ, this.secWOZ), this.xUT - 30, this.yUT + this.R + 20);
                        g.setFont(new Font("Helvetiva", 0, 10));
                        g.drawString("Local Solar Time", this.xUT - 43, this.yUT + this.R + 35);
                        g.drawString("Local Hour Angle", 400, this.yUT - 20);
                        if (this.WOZ < 12.0) {
                            this.str = new StringBuffer().append(360.0 + Math.round(1500.0 * (this.WOZ - 12.0)) / 100.0).append(this.deg).toString();
                        }
                        else {
                            this.str = new StringBuffer().append(Math.round(1500.0 * (this.WOZ - 12.0)) / 100.0).append(this.deg).toString();
                        }
                        g.drawString(this.str, 490, this.yUT - 20);
                        g.drawString("Local Sid. Time", 400, this.yUT - 35);
                        g.drawString(new StringBuffer().append(Math.round(100.0 * this.ST * 15.0) / 100.0).append(this.deg).toString(), 490, this.yUT - 35);
                        break;
                    }
                    case 5: {
                        g.setFont(new Font("Courier", 0, 12));
                        g.drawString(this.makeTimeString(this.hGOZ, this.minGOZ, this.secGOZ), this.xUT - 30, this.yUT + this.R + 20);
                        g.setFont(new Font("Helvetiva", 0, 10));
                        g.drawString("Greenwich Solar Time", this.xUT - 55, this.yUT + this.R + 35);
                        g.setColor(Color.black);
                        g.drawString("Greenwich H. A.", 400, this.yUT - 5);
                        if (this.GOZ < 12.0) {
                            this.str = new StringBuffer().append(360.0 + Math.round(150.0 * (this.GOZ - 12.0)) / 10.0).append(this.deg).toString();
                        }
                        else {
                            this.str = new StringBuffer().append(Math.round(1500.0 * (this.GOZ - 12.0)) / 100.0).append(this.deg).toString();
                        }
                        g.drawString(this.str, 490, this.yUT - 5);
                        g.drawString("Sun R. A.", 400, this.yUT + 10);
                        g.drawString(String.valueOf(this.HMS(this.RA)), 490, this.yUT + 10);
                        g.drawString(new StringBuffer().append(Math.round(100.0 * this.RA * 15.0) / 100.0).append(this.deg).toString(), 490, this.yUT + 25);
                        break;
                    }
                }
            }
        }
    }
}
