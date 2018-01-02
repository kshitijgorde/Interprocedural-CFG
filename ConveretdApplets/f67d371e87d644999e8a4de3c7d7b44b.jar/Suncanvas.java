import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Date;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class Suncanvas extends Canvas
{
    String str;
    final double K = 0.017453292519943295;
    final int top = 90;
    final int xL = 55;
    final int xLL = 55;
    final int y90 = 90;
    final int y0 = 252;
    final int xS = 281;
    final int unten = 292;
    final int Radius = 9;
    String s;
    int hInt;
    int azInt;
    int xInt;
    int hBildInt;
    int transInt;
    int min;
    double hBild;
    double xx;
    double STD;
    double equation;
    double trans;
    double diff;
    Image myImage;
    Date dat;
    public Compute comp;
    int browserOffset;
    int locOffset;
    String timeString;
    int year;
    int month;
    int date;
    int hours;
    int minutes;
    int seconds;
    double latitude;
    double longitude;
    double dec;
    double GHA;
    double hoehe;
    double azimut;
    String ghaStr;
    String eqtStr;
    String riseStr;
    String setStr;
    String transStr;
    String locStr;
    String heightStr;
    String azStr;
    String rise45Str;
    String set45Str;
    int[] hArray;
    int[] xArray;
    int xSun;
    int hSun;
    final int mapOben = 320;
    final int mapRechts = 545;
    String versStr;
    String gmtStr;
    String declinStr;
    double currentDec;
    Rise_Set rs;
    double currentElevation;
    double rise45;
    double set45;
    double rise;
    double set;
    double winkel;
    double delta45;
    int xE;
    int yE;
    int xM;
    int yM;
    int x9;
    int r;
    String delta45Str;
    double sum;
    boolean UV;
    double hTrans;
    char deg;
    
    public Suncanvas(final Image bild, final Date myDate, final double myLat, final double myLong, final String myLoc, final int myLocOffset, final String vers) {
        this.s = "";
        this.hArray = new int[25];
        this.xArray = new int[25];
        this.currentElevation = 0.0;
        this.sum = 0.0;
        this.UV = false;
        this.deg = '°';
        this.myImage = bild;
        this.versStr = vers;
        this.latitude = myLat;
        this.longitude = myLong;
        this.locStr = myLoc;
        this.dat = myDate;
        this.hours = this.dat.getHours();
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth();
        this.minutes = this.dat.getMinutes();
        this.seconds = this.dat.getSeconds();
        this.year = this.dat.getYear();
        this.browserOffset = this.dat.getTimezoneOffset();
        this.browserOffset = -this.browserOffset / 60;
        this.locOffset = myLocOffset;
        this.timeString = String.valueOf(this.browserOffset) + " Hrs";
        if (this.browserOffset > 0) {
            this.timeString = "+" + this.timeString;
        }
        this.STD = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        this.comp = new Compute();
        double jd = this.comp.JD(this.date, this.month + 1, this.year + 1900, this.STD);
        this.dec = this.comp.DeclinationRightAscension(1, jd);
        this.currentDec = this.dec;
        this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, this.STD);
        this.str = String.valueOf(Math.round(10.0 * this.GHA) / 10.0);
        this.ghaStr = String.valueOf(this.str) + " ° W";
        this.equation = this.eot(this.date, this.month + 1, this.year + 1900, this.STD);
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
        if (this.equation < 0.0 && (int)this.equation == 0) {
            this.eqtStr = "-" + (int)this.equation + this.str + this.min + " s";
        }
        this.rs = new Rise_Set(this.dat, this.latitude, this.longitude, this.locOffset, -0.833);
        this.riseStr = this.rs.rise_String();
        this.setStr = this.rs.set_String();
        this.rise = this.rs.h_rise();
        this.set = this.rs.h_set();
        if (this.UV) {
            this.rs = new Rise_Set(this.dat, this.latitude, this.longitude, this.locOffset, 45.0);
            this.rise45 = this.rs.h_rise();
            this.set45 = this.rs.h_set();
            this.delta45 = this.set45 - this.rise45;
            if (this.delta45 > 0.0) {
                this.delta45Str = String.valueOf(String.valueOf(Math.round(10.0 * this.delta45) / 10.0)) + " h";
            }
            else {
                this.delta45Str = "";
            }
            if (this.rise45 > 0.0) {
                this.rise45Str = this.makeAMPMString(this.rise45);
            }
            else {
                this.rise45Str = "--:--";
            }
            if (this.set45 > 0.0) {
                this.set45Str = this.makeAMPMString(this.set45);
            }
            else {
                this.set45Str = "--:--";
            }
        }
        this.trans = 12.0 - this.equation / 60.0 - this.longitude / 15.0;
        this.trans += this.locOffset;
        this.transStr = this.rs.makeTimeString("", this.trans);
        jd = this.comp.JD(this.date, this.month + 1, this.year + 1900, this.trans - this.locOffset);
        final double decTrans = this.comp.DeclinationRightAscension(1, jd);
        final double ghaTrans = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, this.trans - this.locOffset);
        this.hTrans = this.comp.computeHeight(decTrans, this.latitude, this.longitude, ghaTrans);
        this.hoehe = this.comp.computeHeight(this.dec, this.latitude, this.longitude, this.GHA);
        this.currentElevation = this.hoehe + this.comp.refract(this.hoehe);
        this.heightStr = Math.round(10.0 * this.currentElevation) / 10.0 + " °";
        this.azimut = this.comp.computeAzimut(this.dec, this.latitude, this.longitude, this.GHA, this.hoehe);
        this.azStr = Math.round(10.0 * this.azimut) / 10.0 + " °";
        this.hBild = this.hoehe * 162.0 / 90.0;
        this.hBildInt = (int)Math.round(this.hBild);
        this.hSun = this.hBildInt;
        if (this.latitude >= 0.0) {
            this.xx = (180.0 - this.azimut) * 226.0 / 180.0;
            this.xx = 281.0 - this.xx;
        }
        else {
            if (this.azimut < 180.0) {
                this.xx = this.azimut * 226.0 / 180.0;
            }
            else {
                this.xx = -(360.0 - this.azimut) * 226.0 / 180.0;
            }
            this.xx += 281.0;
        }
        this.xInt = (int)Math.round(this.xx);
        this.xSun = this.xInt;
        for (int i = -this.locOffset; i < -this.locOffset + 25; ++i) {
            jd = this.comp.JD(this.date, this.month + 1, this.year + 1900, i);
            final double dec1 = this.comp.DeclinationRightAscension(1, jd);
            this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, i);
            this.hoehe = this.comp.computeHeight(dec1, this.latitude, this.longitude, this.GHA);
            this.hBild = this.hoehe * 162.0 / 90.0;
            this.hBildInt = (int)Math.round(this.hBild);
            this.azimut = this.comp.computeAzimut(dec1, this.latitude, this.longitude, this.GHA, this.hoehe);
            if (this.latitude >= this.dec) {
                this.xx = (180.0 - this.azimut) * 226.0 / 180.0;
            }
            else if (this.azimut <= 180.0) {
                this.xx = this.azimut * 226.0 / 180.0;
            }
            else {
                this.xx = -(360.0 - this.azimut) * 226.0 / 180.0;
            }
            if (this.latitude >= this.dec) {
                this.xx = 281.0 - this.xx;
            }
            else {
                this.xx += 281.0;
            }
            this.xInt = (int)Math.round(this.xx);
            this.hArray[i + this.locOffset] = this.hBildInt;
            this.xArray[i + this.locOffset] = this.xInt;
        }
        this.repaint();
    }
    
    public String makeAMPMString(double time) {
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
        String minStr;
        if (min > 9) {
            minStr = ":";
        }
        else {
            minStr = ":0";
        }
        str = String.valueOf(String.valueOf((int)time) + minStr + min);
        if (time < 10.0) {
            str = "0" + str;
        }
        if (time < 12.0) {
            str = String.valueOf(str) + " AM";
        }
        if (time > 12.0) {
            str = String.valueOf(String.valueOf((int)(time - 12.0)) + minStr + min + " PM");
        }
        return str;
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
        final double T = (this.JD(date, month, year, UT) - 2451545.0) / 36525.0;
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
        double delta = Math.asin(Math.sin(0.017453292519943295 * eps) * Math.sin(0.017453292519943295 * lambda));
        delta /= 0.017453292519943295;
        return RA;
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
    
    public double sunTrans() {
        return this.hTrans;
    }
    
    public void paint(final Graphics g) {
        Font f = g.getFont();
        g.setColor(Color.black);
        g.setFont(new Font("Helvetica", 0, 9));
        g.drawString("Sun Shadow" + this.versStr, 545, 530);
        g.drawString("© 2000-2010  J. Giesen", 545, 542);
        g.drawString("www.GeoAstro.de", 545, 554);
        g.setFont(f);
        g.drawImage(this.myImage, 55, 90, this);
        if (this.UV) {
            g.setColor(Color.red);
            g.drawLine(55, 172, 507, 172);
            g.drawLine(55, 170, 507, 170);
            g.drawLine(55, 171, 507, 171);
        }
        g.setFont(new Font("Chicago", 0, 12));
        g.setColor(Color.red);
        if (this.latitude >= this.dec) {
            g.drawString("S", 278, 103);
            g.drawString("E", 165, 103);
            g.drawString("W", 388, 103);
        }
        else {
            g.drawString("N", 278, 103);
            g.drawString("W", 163, 103);
            g.drawString("E", 391, 103);
        }
        g.setFont(new Font("Helvetica", 0, 12));
        g.setColor(Color.red);
        if (this.UV) {
            g.drawString("The most intense sun light and the highest risk for sun damage today is between ", 55, 85);
            g.setFont(new Font("Helvetica", 1, 12));
            g.drawString(String.valueOf(this.rise45Str) + " and " + this.set45Str, 500, 85);
        }
        g.setFont(f);
        this.hours = this.dat.getHours();
        this.minutes = this.dat.getMinutes();
        this.seconds = this.dat.getSeconds();
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth();
        this.year = this.dat.getYear();
        this.hours -= this.locOffset;
        if (this.hours < 0) {
            this.hours += 24;
        }
        if (this.hours >= 24) {
            this.hours -= 24;
        }
        if (this.minutes > 9) {
            this.str = ":";
        }
        else {
            this.str = ":0";
        }
        this.str = String.valueOf(this.hours) + this.str + this.minutes;
        if (this.seconds > 9) {
            this.str = String.valueOf(this.str) + ":";
        }
        else {
            this.str = String.valueOf(this.str) + ":0";
        }
        this.str = String.valueOf(this.str) + this.seconds;
        g.setColor(Color.blue);
        this.gmtStr = this.str;
        String str = this.dat.toString();
        g.drawString("Subsolar Point:", 545, 385);
        g.setColor(Color.black);
        str = String.valueOf(String.valueOf(Math.abs(Math.round(100.0 * this.currentDec) / 100.0))) + '°';
        if (this.currentDec >= 0.0) {
            this.declinStr = String.valueOf(str) + "  N";
        }
        else {
            this.declinStr = String.valueOf(str) + "  S";
        }
        g.drawString("Latitude (Declin.)", 545, 400);
        g.drawString(this.declinStr, 640, 400);
        g.drawString("Longitude (GHA)", 545, 415);
        g.drawString(this.ghaStr, 640, 415);
        g.setColor(Color.red);
        g.drawString("Location:  " + this.locStr, 545, 105);
        g.setColor(Color.black);
        g.drawString("Sunrise", 545, 145);
        g.drawString(this.riseStr, 635, 145);
        g.drawString("Culmination", 545, 160);
        g.drawString(this.transStr, 635, 160);
        g.drawString("Sunset", 545, 175);
        g.drawString(this.setStr, 635, 175);
        g.setColor(Color.black);
        if (this.currentElevation >= 45.0) {
            g.setColor(Color.red);
        }
        g.drawString("Current Elevation", 545, 195);
        g.drawString("   " + this.heightStr, 635, 195);
        g.setColor(Color.black);
        g.drawString("Current Azimuth", 545, 210);
        g.drawString("   " + this.azStr, 635, 210);
        g.setColor(Color.black);
        g.drawString("Equation of Time", 545, 225);
        g.drawString("   " + this.eqtStr, 635, 225);
        g.drawString("Max. Elevation", 545, 240);
        g.drawString("   " + Math.round(10.0 * this.hTrans) / 10.0 + this.deg, 635, 240);
        if (252 - this.hSun < 292) {
            if (this.currentElevation >= -0.83) {
                g.setColor(Color.yellow);
            }
            else {
                g.setColor(Color.red);
            }
            g.fillOval(this.xSun - 9, 252 - this.hSun - 9, 18, 18);
            g.setColor(Color.red);
            g.drawOval(this.xSun - 9, 252 - this.hSun - 9, 18, 18);
        }
        else {
            g.setColor(Color.white);
            g.drawString("Sun", this.xSun - 10, 282);
            g.drawString("V", this.xSun - 4, 292);
        }
        f = g.getFont();
        g.setFont(new Font("Helvetica", 0, 9));
        g.setColor(Color.black);
        for (int i = -this.locOffset; i < -this.locOffset + 24; ++i) {
            if (252 - this.hArray[i + this.locOffset] < 292 && this.xArray[i + this.locOffset] > 55 && this.xArray[i + this.locOffset] < 495) {
                g.setColor(Color.red);
                g.drawOval(this.xArray[i + this.locOffset] - 2, 252 - this.hArray[i + this.locOffset] - 2, 4, 4);
                g.setColor(Color.black);
                if (i % 2 == 0) {
                    g.drawString(String.valueOf(i + this.locOffset), this.xArray[i + this.locOffset] + 7, 252 - this.hArray[i + this.locOffset] + 3);
                }
            }
        }
        if (this.UV) {
            this.xM = 635;
            this.yM = 260;
            this.r = 50;
            final Color background = new Color(235, 235, 255);
            if (this.riseStr.equals("Visible all day")) {
                g.setColor(Color.red);
                g.fillOval(this.xM - this.r, this.yM - this.r, 2 * this.r, 2 * this.r);
                g.setColor(Color.yellow);
                g.fillArc(this.xM - this.r, this.yM - this.r, 2 * this.r, 2 * this.r, 270 - (int)(this.rise45 * 15.0), (int)(360.0 - 15.0 * (this.set45 - this.rise45)));
            }
            else {
                g.setColor(Color.red);
                g.fillOval(this.xM - this.r, this.yM - this.r, 2 * this.r, 2 * this.r);
                g.setColor(Color.yellow);
                g.fillArc(this.xM - this.r, this.yM - this.r, 2 * this.r, 2 * this.r, 270 - (int)(this.rise45 * 15.0), (int)(360.0 - 15.0 * (this.set45 - this.rise45)));
                g.setColor(Color.black);
                g.fillArc(this.xM - this.r, this.yM - this.r, 2 * this.r, 2 * this.r, 270 - (int)(this.rise * 15.0), (int)(360.0 - 15.0 * (this.set - this.rise)));
            }
            g.setFont(new Font("Helvetica", 0, 11));
            g.setColor(Color.red);
            g.drawString("6", this.xM - this.r - 9, this.yM + 5);
            g.drawString("12", this.xM - 5, this.yM - this.r - 3);
            g.drawString("18", this.xM + this.r + 3, this.yM + 5);
            this.x9 = (int)Math.round(this.r * Math.sin(0.7853981633974483));
            g.drawString("9", this.xM - this.x9 - 10, this.yM - this.x9 - 5);
            g.drawString("15", this.xM + this.x9 + 5, this.yM - this.x9 - 5);
            g.drawString("3", this.xM - this.x9 - 10, this.yM + this.x9 + 10);
            g.drawString("21", this.xM + this.x9 + 5, this.yM + this.x9 + 10);
            g.drawString("0", this.xM - 5, this.yM + this.r + 13);
            g.setColor(Color.green);
            this.winkel = 270.0 - 15.0 * (this.STD + this.locOffset);
            this.xE = (int)Math.round(this.r * Math.cos(0.017453292519943295 * this.winkel));
            this.yE = (int)Math.round(this.r * Math.sin(0.017453292519943295 * this.winkel));
            g.drawLine(this.xM, this.yM, this.xM + this.xE, this.yM - this.yE);
            if (Math.abs(Math.cos(0.017453292519943295 * this.winkel)) < 0.71) {
                g.drawLine(this.xM - 2, this.yM, this.xM + this.xE - 2, this.yM - this.yE);
                g.drawLine(this.xM - 1, this.yM, this.xM + this.xE - 1, this.yM - this.yE);
                g.drawLine(this.xM + 1, this.yM, this.xM + this.xE + 1, this.yM - this.yE);
                g.drawLine(this.xM + 2, this.yM, this.xM + this.xE + 2, this.yM - this.yE);
            }
            else {
                g.drawLine(this.xM, this.yM + 2, this.xM + this.xE, this.yM - this.yE + 2);
                g.drawLine(this.xM, this.yM + 1, this.xM + this.xE, this.yM - this.yE + 1);
                g.drawLine(this.xM, this.yM - 1, this.xM + this.xE, this.yM - this.yE - 1);
                g.drawLine(this.xM, this.yM - 2, this.xM + this.xE, this.yM - this.yE - 2);
            }
            g.setColor(Color.black);
            g.drawOval(this.xM - this.r, this.yM - this.r, 2 * this.r, 2 * this.r);
            this.winkel = 270.0 - 15.0 * (this.rise45 + this.set45) / 2.0;
            this.xE = (int)Math.round(this.r * Math.cos(0.017453292519943295 * this.winkel) / 2.0);
            this.yE = (int)Math.round(this.r * Math.sin(0.017453292519943295 * this.winkel) / 2.0);
            g.setFont(new Font("Helvetica", 0, 11));
            g.drawString(this.delta45Str, this.xM + this.xE - 10, this.yM - this.yE - 5);
        }
        g.setColor(Color.red);
        g.setFont(f);
    }
}
