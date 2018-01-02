import java.awt.Event;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.net.URL;
import java.awt.Panel;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Font;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DayNight133 extends Applet
{
    String versStr;
    Date dat;
    int year;
    int month;
    int date;
    int day;
    int hours;
    int minutes;
    int seconds;
    Image bild;
    String[] dayArray;
    String[] monthArray;
    Graphics g;
    Button button;
    public DNloc time;
    public Choice timeChoice;
    public String timeString;
    public int locOffset;
    int browserOffset;
    int xMouse;
    int yMouse;
    int xOben;
    int xL;
    double latitude;
    double longitude;
    double dec;
    double GHA;
    double hoehe;
    double K;
    boolean clicked;
    String myStr;
    boolean demo;
    boolean online;
    public String email;
    public String param;
    public String wwwStr;
    public String usrStr;
    public String userString;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        if ((int)num < 99) {
            return -2 * this.dat.getSeconds();
        }
        return (int)num + 1156;
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
    
    public double computeDeclination(final int T, final int M, final int J, final double STD) {
        long N = 365 * J + T + 31 * M - 46;
        if (M < 3) {
            N += (J - 1) / 4;
        }
        else {
            N = N - (int)(0.4 * M + 2.3) + (int)(J / 4.0);
        }
        double X = (N - 693960L) / 1461.0;
        X = (X - (int)X) * 1440.02509 + (int)X * 0.0307572;
        X = X + STD / 24.0 * 0.9856645 + 356.6498973;
        X += 1.91233 * Math.sin(0.9999825 * X * this.K);
        X = (X + Math.sin(1.999965 * X * this.K) / 50.0 + 282.55462) / 360.0;
        X = (X - (int)X) * 360.0;
        final double J2 = (J - 2000) / 100.0;
        final double Ekliptik = 23.43929111 - (46.815 + (5.9E-4 - 0.001813 * J2) * J2) * J2 / 3600.0;
        X = Math.sin(X * this.K) * Math.sin(this.K * Ekliptik);
        return Math.atan(X / Math.sqrt(1.0 - X * X)) / this.K + 7.5E-4;
    }
    
    public double computeGHA(final int T, final int M, final int J, final double STD) {
        long N = 365 * J + T + 31 * M - 46;
        if (M < 3) {
            N += (J - 1) / 4;
        }
        else {
            N = N - (int)(0.4 * M + 2.3) + (int)(J / 4.0);
        }
        final double P = STD / 24.0;
        double X = (P + N - 722449.0) * 0.98564734 + 279.306;
        X *= this.K;
        double XX = -104.55 * Math.sin(X) - 429.266 * Math.cos(X) + 595.63 * Math.sin(2.0 * X) - 2.283 * Math.cos(2.0 * X);
        XX = XX + 4.6 * Math.sin(3.0 * X) + 18.7333 * Math.cos(3.0 * X);
        XX = XX - 13.2 * Math.sin(4.0 * X) - Math.cos(5.0 * X) - Math.sin(5.0 * X) / 3.0 + 0.5 * Math.sin(6.0 * X) + 0.231;
        XX = XX / 240.0 + 360.0 * (P + 0.5);
        if (XX > 360.0) {
            XX -= 360.0;
        }
        return XX;
    }
    
    public double computeHeight() {
        final double sinHeight = Math.sin(this.dec * this.K) * Math.sin(this.latitude * this.K) + Math.cos(this.dec * this.K) * Math.cos(this.K * this.latitude) * Math.cos(this.K * (this.GHA + this.longitude));
        double height = Math.asin(sinHeight);
        height /= this.K;
        return height;
    }
    
    public double computeAzimut() {
        final double cosAz = (Math.sin(this.dec * this.K) - Math.sin(this.latitude * this.K) * Math.sin(this.hoehe * this.K)) / (Math.cos(this.hoehe * this.K) * Math.cos(this.K * this.latitude));
        double Az = 1.5707963267948966 - Math.asin(cosAz);
        Az /= this.K;
        if (Math.sin(this.K * (this.GHA + this.longitude)) < 0.0) {
            Az = Az;
        }
        if (Math.sin(this.K * (this.GHA + this.longitude)) > 0.0) {
            Az = 360.0 - Az;
        }
        return Az;
    }
    
    public void myDayMonth() {
        this.dayArray[0] = "Sunday";
        this.dayArray[1] = "Monday";
        this.dayArray[2] = "Tuesday";
        this.dayArray[3] = "Wednesday";
        this.dayArray[4] = "Thursday";
        this.dayArray[5] = "Friday";
        this.dayArray[6] = "Saturday";
        this.monthArray[0] = "January";
        this.monthArray[1] = "February";
        this.monthArray[2] = "March";
        this.monthArray[3] = "April";
        this.monthArray[4] = "May";
        this.monthArray[5] = "June";
        this.monthArray[6] = "July";
        this.monthArray[7] = "August";
        this.monthArray[8] = "September";
        this.monthArray[9] = "October";
        this.monthArray[10] = "November";
        this.monthArray[11] = "December";
    }
    
    public void init() {
        this.setFont(new Font("Helvetica", 0, 12));
        final URL url = this.getDocumentBase();
        this.myStr = url.toString();
        this.myStr = String.valueOf(this.myStr) + "1234567890123456789012345";
        this.wwwStr = this.myStr.substring(0, 27);
        this.myDayMonth();
        this.bild = this.getImage(this.getDocumentBase(), "applet/map.gif");
        this.dat = new Date();
        final Rectangle r = this.bounds();
        final Image picture = this.createImage(r.width, r.height);
        this.g = picture.getGraphics();
        final MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(this.bild, 0);
        try {
            tracker.waitForID(0);
        }
        catch (Exception ex) {}
        final Panel panel = new Panel();
        this.add("Buttons", panel);
        this.add("Choice", panel);
        this.time = new DNloc();
        this.timeChoice = new Choice();
        this.time.timeMenu(this.timeChoice);
        panel.add(this.timeChoice);
        (this.button = new Button()).setLabel("update time");
        panel.add(this.button);
        this.dat = new Date();
        this.browserOffset = this.dat.getTimezoneOffset();
        this.browserOffset = -this.browserOffset / 60;
        this.locOffset = this.browserOffset;
        this.timeString = String.valueOf(this.browserOffset) + " h";
        if (this.browserOffset > 0) {
            this.timeString = "+" + this.timeString;
        }
        this.timeString = " " + this.timeString;
        this.timeChoice.select(this.timeString);
        this.clicked = false;
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        this.usrStr = this.email;
        this.userString = String.valueOf(this.email) + "  " + this.dat.toString();
        if (this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(this.wwwStr, 22) == this.formula("http://www.geoastro.de", 22) || this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21)) {
            ok = true;
            this.online = true;
            this.demo = false;
        }
        else {
            ok = false;
        }
        if (!ok) {
            ok = true;
            if (this.email.length() == 0 || Integer.parseInt(this.param) != this.formula(this.email, this.email.length())) {
                ok = false;
            }
            else {
                ok = true;
                this.demo = false;
            }
            if (this.wwwStr.substring(0, 7).equals("http://")) {
                ok = false;
                this.demo = true;
            }
        }
        if (this.demo) {
            this.versStr = String.valueOf(this.versStr) + " DEMO";
        }
    }
    
    public int computeLat(final int longitude, final double dec) {
        final double itan = Math.atan(-Math.cos(longitude * this.K) / Math.tan(dec * this.K));
        return (int)Math.round(itan / this.K);
    }
    
    public void paint(final Graphics g) {
        final int x0 = 180;
        final int y0 = 90 + this.xOben;
        final int left = 370 + this.xL;
        final int Radius = 6;
        g.setFont(new Font("Helvetica", 0, 9));
        g.drawString("Day & Night Applet" + this.versStr, left - 30, 250);
        g.drawString("© 1998-2007 Juergen Giesen", left - 30, 265);
        g.drawString("www.GeoAstro.de", left - 30, 280);
        Font f = new Font("Helvetica", 0, 10);
        g.setFont(f);
        g.drawImage(this.bild, this.xL, this.xOben, this);
        g.drawRect(this.xL, this.xOben, 360, 178);
        g.drawString("Time Zone Offset", this.xL, this.xOben - 15);
        this.dat = new Date();
        String str = this.dat.toString();
        g.drawString(str, this.xL, 250);
        this.day = this.dat.getDay();
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth();
        str = String.valueOf(this.monthArray[this.month]) + " " + String.valueOf(this.date) + ", ";
        this.year = this.dat.getYear();
        str = String.valueOf(str) + " 19" + this.year;
        this.hours = this.dat.getHours();
        this.minutes = this.dat.getMinutes();
        this.seconds = this.dat.getSeconds();
        final double STD = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        this.dec = this.computeDeclination(this.date, this.month + 1, this.year + 1900, STD);
        str = String.valueOf(Math.round(100.0 * this.dec) / 100.0);
        String s = str.substring(0, str.indexOf(".") + 2);
        g.setColor(Color.blue);
        g.drawString("Declin.   " + s + " degs", left, 60);
        this.GHA = this.computeGHA(this.date, this.month + 1, this.year + 1900, STD);
        str = String.valueOf(Math.round(10.0 * this.GHA) / 10.0);
        s = str.substring(0, str.indexOf(".") + 2);
        g.drawString("GHA       " + s + " degs", left, 80);
        double equation = this.eot(this.date, this.month + 1, this.year + 1900, STD);
        final double diff = Math.abs(equation) - (int)Math.abs(equation);
        int min = (int)Math.round(diff * 60.0);
        if (min == 60) {
            min = 0;
            if (equation >= 0.0) {
                ++equation;
            }
            else {
                --equation;
            }
        }
        if (min > 9) {
            str = ":";
        }
        else {
            str = ":0";
        }
        String eqtStr = (int)equation + str + min + " min";
        if (equation < 0.0 && (int)equation == 0) {
            eqtStr = "-" + (int)equation + str + min + " min";
        }
        g.drawString("Equation of Time", left, 100);
        g.drawString("                      " + eqtStr, left, 113);
        g.setColor(Color.black);
        if (this.browserOffset > 0) {
            str = "+";
        }
        else {
            str = "";
        }
        g.drawString("Browser's Time Zone Offset is UT " + str + this.browserOffset + " h", this.xL, 280);
        this.hours = this.dat.getHours() - this.locOffset;
        this.timeString = String.valueOf(this.locOffset) + " h";
        if (this.hours < 0) {
            this.hours += 24;
        }
        if (this.hours >= 24) {
            this.hours -= 24;
        }
        if (this.minutes > 9) {
            str = ":";
        }
        else {
            str = ":0";
        }
        str = String.valueOf(this.hours) + str + this.minutes;
        if (this.hours < 10) {
            str = "0" + str;
        }
        if (this.seconds > 9) {
            s = ":";
        }
        else {
            s = ":0";
        }
        str = String.valueOf(str) + s + this.seconds;
        g.setColor(Color.blue);
        g.drawString(String.valueOf(str) + " UT", this.xL, 265);
        g.setColor(Color.red);
        int x2 = x0 - (int)Math.round(this.GHA);
        if (x2 < 0) {
            x2 += 360;
        }
        if (x2 > 360) {
            x2 -= 360;
        }
        int xx = this.xL;
        g.setColor(Color.gray);
        g.drawLine(this.xL, y0, this.xL + 2 * x0 - 2, y0);
        g.drawLine(this.xL + x0, y0 - 90, this.xL + x0, y0 + 90);
        final int yy1 = (int)Math.round(y0 - 23.5);
        final int yy2 = (int)Math.round(y0 + 23.5);
        int y2 = (int)Math.round(y0 - 90 + 23.5);
        int yy3 = (int)Math.round(y0 + 90 - 23.5);
        for (int i = 0; i < 60; ++i) {
            g.drawLine(xx, yy1, xx + 2, yy1);
            g.drawLine(xx, yy2, xx + 2, yy2);
            g.drawLine(xx, y2, xx + 2, y2);
            g.drawLine(xx, yy3, xx + 2, yy3);
            xx += 6;
        }
        y2 = y0 - (int)Math.round(this.dec);
        g.setColor(Color.yellow);
        g.fillOval(this.xL + x2 - Radius, y2 - Radius, 2 * Radius, 2 * Radius);
        g.setColor(Color.red);
        g.drawOval(this.xL + x2 - Radius - 1, y2 - Radius - 1, 2 * Radius + 2, 2 * Radius + 2);
        g.setXORMode(Color.black);
        int F;
        if (this.dec > 0.0) {
            F = 1;
        }
        else {
            F = -1;
        }
        for (int j = -x2; x2 + j < 2 * x0; ++j) {
            yy3 = this.computeLat(j, this.dec);
            g.drawLine(this.xL + x2 + j, y0 - yy3, this.xL + x2 + j, y0 + F * 90);
        }
        g.setPaintMode();
        g.setColor(Color.black);
        this.longitude = this.xMouse - this.xL - 180;
        this.latitude = 90 - this.yMouse + this.xOben;
        str = String.valueOf((int)Math.abs(this.latitude));
        if (this.latitude > 0.0) {
            str = String.valueOf(str) + " N";
        }
        else {
            str = String.valueOf(str) + " S";
        }
        str = String.valueOf(str) + "  " + String.valueOf((int)Math.abs(this.longitude));
        if (this.longitude > 0.0) {
            str = String.valueOf(str) + " E";
        }
        else {
            str = String.valueOf(str) + " W";
        }
        this.hoehe = this.computeHeight();
        g.setColor(Color.red);
        if (this.clicked) {
            g.drawString(str, 260, 250);
            str = "Elev.  = " + String.valueOf((int)Math.round(this.hoehe)) + " deg";
            g.drawString(str, 260, 265);
            g.drawOval(this.xMouse - 3, this.yMouse - 3, 6, 6);
        }
        else {
            g.drawString("Click the map to see", 250, 250);
            g.drawString("the shadow", 250, 265);
            g.drawString("and more !", 250, 280);
        }
        double azimuth = this.computeAzimut();
        if (this.clicked) {
            str = "Azim. = " + String.valueOf((int)Math.round(azimuth)) + " deg";
            g.drawString(str, 260, 280);
        }
        if (this.hoehe > 0.0 && this.clicked) {
            azimuth -= 180.0;
            final double gnomon = 50.0 / Math.tan(this.K * this.hoehe);
            final int yGnomon = (int)Math.round(gnomon * Math.cos(this.K * azimuth));
            final int xGnomon = (int)Math.round(gnomon * Math.sin(this.K * azimuth));
            g.drawLine(this.xMouse, this.yMouse, this.xMouse + xGnomon, this.yMouse - yGnomon);
        }
        g.setColor(Color.red);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        g.setFont(new Font("Helvetica", 0, 9));
        g.setColor(Color.black);
        g.drawString("Map © Apple Computer, Inc.", 145, 233);
        if (this.demo) {
            f = g.getFont();
            g.setColor(Color.red);
            g.setFont(new Font("Chicago", 0, 96));
            g.drawString("D E M O", 30, 180);
            g.setFont(f);
        }
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof Button) {
            this.repaint();
        }
        if (event.target == this.timeChoice) {
            this.timeString = this.timeChoice.getSelectedItem();
            this.locOffset = this.time.getTimeZone(this.timeString);
            this.locOffset = this.locOffset;
            this.timeChoice.select(this.timeString);
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int x, final int y) {
        this.xMouse = x;
        this.yMouse = y;
        if (this.xMouse >= this.xL && this.xMouse <= this.xL + 360 && this.yMouse >= this.xOben && this.yMouse <= this.xOben + 180) {
            this.clicked = true;
            this.repaint();
        }
        else {
            this.clicked = false;
        }
        return true;
    }
    
    public DayNight133() {
        this.versStr = "  - v 1.33e";
        this.dayArray = new String[7];
        this.monthArray = new String[12];
        this.xMouse = 0;
        this.yMouse = 0;
        this.xOben = 45;
        this.xL = 20;
        this.hoehe = 0.0;
        this.K = 0.017453292519943295;
        this.demo = true;
        this.online = false;
    }
}
