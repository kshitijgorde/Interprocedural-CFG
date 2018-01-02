import java.awt.Frame;
import java.awt.Event;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.util.Date;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Button;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class elevazmoon092 extends Applet
{
    String versStr;
    final char deg = '°';
    public compute comp;
    public Seloc loc;
    public Seloc time;
    Button nowButton;
    boolean demo;
    boolean online;
    double usrLat;
    double usrLong;
    int year;
    int month;
    int date;
    int hours;
    int minutes;
    int seconds;
    public double latitude;
    public double longitude;
    double homeLat;
    double homeLong;
    TextField fieldLatDeg;
    TextField fieldLongDeg;
    public Choice locChoice;
    public Choice timeChoice;
    public Choice ewChoice;
    public Choice nsChoice;
    public Choice showChoice;
    Choice hoursChoice;
    Choice minChoice;
    Choice yearChoice;
    Choice monthChoice;
    Choice dateChoice;
    String[] monthArray;
    public String locString;
    public String timeString;
    String hoursStr;
    String minStr;
    String dateStr;
    String monthStr;
    String yearStr;
    String ewStr;
    String nsStr;
    String latStr;
    String longStr;
    String str;
    public String email;
    public String param;
    public String wwwStr;
    String usrStr;
    String homeString;
    String homeLatStr;
    String homeLongStr;
    String separator;
    int timezoneoffset;
    Date dat;
    String[] Data;
    int nData;
    Choice writeChoice;
    MoonDistance myMoonDistance;
    double moonDistance;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 7001;
    }
    
    public void init() {
        final Color background = new Color(235, 235, 255);
        this.setBackground(background);
        this.homeString = this.getParameter("location");
        this.homeLatStr = this.getParameter("latitude");
        this.homeLongStr = this.getParameter("longitude");
        final URL url = this.getDocumentBase();
        this.str = url.toString();
        this.str = String.valueOf(this.str) + "1234567890123456789012345";
        this.wwwStr = this.str.substring(0, 27);
        this.dat = new Date();
        this.hours = this.dat.getHours();
        this.minutes = this.dat.getMinutes();
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth();
        this.year = this.dat.getYear();
        this.seconds = this.dat.getSeconds();
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        this.timezoneoffset = -this.dat.getTimezoneOffset() / 60;
        this.dat.setYear(91);
        this.dat.setMonth(4);
        this.dat.setDate(19);
        this.dat.setHours(15);
        this.dat.setMinutes(0);
        this.dat.setSeconds(0);
        this.timezoneoffset = 2;
        this.hours = 15;
        this.minutes = 0;
        this.date = 19;
        this.month = 4;
        this.year = 91;
        this.seconds = 0;
        this.timeString = String.valueOf(this.timezoneoffset);
        if (this.timezoneoffset > 0) {
            this.timeString = "+" + this.timeString;
        }
        this.timeString = "UT " + this.timeString + " h";
        final Font f = new Font("Helvetica", 0, 10);
        this.setFont(f);
        gbc.weighty = 0.0;
        gbc.insets = new Insets(5, 20, 0, 0);
        final Label L11 = new Label();
        L11.setText("Time Zone");
        gbl.setConstraints(L11, gbc);
        this.add(L11);
        gbc.insets = new Insets(5, 0, 0, 0);
        final Label L12 = new Label();
        L12.setText("Location");
        gbl.setConstraints(L12, gbc);
        this.add(L12);
        final Label L13 = new Label();
        L13.setText("Latit.");
        gbl.setConstraints(L13, gbc);
        this.add(L13);
        final Label L14 = new Label();
        L14.setText("N / S");
        gbl.setConstraints(L14, gbc);
        this.add(L14);
        final Label L15 = new Label();
        L15.setText("Long.");
        gbl.setConstraints(L15, gbc);
        this.add(L15);
        final Label L16 = new Label();
        L16.setText("E / W");
        gbl.setConstraints(L16, gbc);
        this.add(L16);
        this.writeChoice = new Choice();
        gbc.insets = new Insets(5, 0, 0, 10);
        gbl.setConstraints(this.writeChoice, gbc);
        this.writeChoice.addItem("Write...");
        this.writeChoice.addItem("Day");
        this.add(this.writeChoice);
        gbc.gridy = 1;
        this.time = new Seloc();
        this.timeChoice = new Choice();
        this.time.timeMenu(this.timeChoice);
        gbc.insets = new Insets(0, 20, 0, 0);
        gbl.setConstraints(this.timeChoice, gbc);
        this.timeChoice.select(this.timeString);
        this.add(this.timeChoice);
        gbc.insets = new Insets(0, 10, 0, 0);
        this.locChoice = new Choice();
        this.locString = this.homeString;
        this.locChoice.addItem(this.locString);
        this.locChoice.addItem(this.separator);
        (this.loc = new Seloc()).locMenu(this.locChoice);
        gbl.setConstraints(this.locChoice, gbc);
        this.add(this.locChoice);
        this.locChoice.select(this.locString);
        final Double latDouble = Double.valueOf(this.homeLatStr);
        this.latitude = latDouble;
        this.homeLat = this.latitude;
        if (this.latitude < 0.0) {
            this.nsStr = "S";
        }
        else {
            this.nsStr = "N";
        }
        final Double longDouble = Double.valueOf(this.homeLongStr);
        this.longitude = longDouble;
        this.homeLong = this.longitude;
        if (this.longitude < 0.0) {
            this.ewStr = "W";
        }
        else {
            this.ewStr = "E";
        }
        this.latStr = String.valueOf(Math.abs(this.latitude));
        gbl.setConstraints(this.fieldLatDeg = new TextField(this.latStr, 5), gbc);
        this.add(this.fieldLatDeg);
        (this.nsChoice = new Choice()).addItem("N");
        this.nsChoice.addItem("S");
        this.nsChoice.select(this.nsStr);
        gbl.setConstraints(this.nsChoice, gbc);
        this.add(this.nsChoice);
        this.longStr = String.valueOf(Math.abs(this.longitude));
        gbl.setConstraints(this.fieldLongDeg = new TextField(this.longStr, 6), gbc);
        this.add(this.fieldLongDeg);
        (this.ewChoice = new Choice()).addItem("E");
        this.ewChoice.addItem("W");
        this.ewChoice.select(this.ewStr);
        gbc.insets = new Insets(0, 0, 0, 10);
        gbl.setConstraints(this.ewChoice, gbc);
        this.add(this.ewChoice);
        gbl.setConstraints(this.nowButton = new Button(" Now "), gbc);
        this.add(this.nowButton);
        gbc.gridy = 3;
        gbc.gridx = 0;
        final Label L17 = new Label();
        L17.setText("Local Time (h:m):");
        gbc.insets = new Insets(5, 20, 0, 0);
        gbl.setConstraints(L17, gbc);
        this.add(L17);
        gbc.insets = new Insets(5, 0, 0, 0);
        gbc.gridx = 1;
        this.hoursChoice = new Choice();
        for (int i = 0; i < 10; ++i) {
            this.hoursStr = "0" + i;
            this.hoursChoice.addItem(this.hoursStr);
        }
        for (int j = 10; j < 24; ++j) {
            this.hoursStr = String.valueOf(j);
            this.hoursChoice.addItem(this.hoursStr);
        }
        this.hoursChoice.select(this.hours);
        gbl.setConstraints(this.hoursChoice, gbc);
        this.add(this.hoursChoice);
        gbc.gridx = 2;
        this.minChoice = new Choice();
        for (int k = 0; k < 10; ++k) {
            this.minStr = "0" + k;
            this.minChoice.addItem(this.minStr);
        }
        for (int l = 10; l < 60; ++l) {
            this.minStr = String.valueOf(l);
            this.minChoice.addItem(this.minStr);
        }
        this.minChoice.select(this.minutes);
        gbl.setConstraints(this.minChoice, gbc);
        this.add(this.minChoice);
        gbc.gridx = 3;
        final Label L18 = new Label();
        L18.setText("Date:");
        gbl.setConstraints(L18, gbc);
        this.add(L18);
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        gbc.gridx = 4;
        this.yearChoice = new Choice();
        for (int m = 0; m < 80; ++m) {
            this.yearStr = String.valueOf(this.year + 1900 - 40 + m);
            this.yearChoice.addItem(this.yearStr);
        }
        this.yearStr = String.valueOf(this.year + 1900);
        this.yearChoice.select(this.yearStr);
        gbl.setConstraints(this.yearChoice, gbc);
        this.add(this.yearChoice);
        gbc.gridx = 5;
        this.monthChoice = new Choice();
        for (int i2 = 0; i2 < 12; ++i2) {
            this.monthChoice.addItem(this.monthArray[i2]);
        }
        this.monthChoice.select(this.month);
        gbl.setConstraints(this.monthChoice, gbc);
        this.add(this.monthChoice);
        gbc.gridx = 6;
        this.dateChoice = new Choice();
        for (int i3 = 1; i3 < 32; ++i3) {
            this.dateStr = String.valueOf(i3);
            this.dateChoice.addItem(this.dateStr);
        }
        this.dateChoice.select(this.date - 1);
        gbl.setConstraints(this.dateChoice, gbc);
        this.add(this.dateChoice);
        gbc.gridy = 4;
        gbc.weighty = 1.0;
        gbc.gridy = 10;
        gbc.weightx = 0.0;
        final Canvas myCan = new Canvas();
        gbl.setConstraints(myCan, gbc);
        this.add(myCan);
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        this.usrStr = this.email;
        System.out.println(this.formula(this.email, this.email.length()));
        if (this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(this.wwwStr, 20) == this.formula("http://www.SciAm.com", 20)) {
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
        System.out.println(String.valueOf(this.versStr) + "  (c) 2006 J.Giesen - www.GeoAstro.de");
    }
    
    public int daysInMonth(final int m, int y) {
        int n = 31;
        y += 1900;
        if (m == 0 || m == 2 || m == 4 || m == 6 || m == 7 || m == 9 || m == 11) {
            n = 31;
        }
        if (m == 3 || m == 5 || m == 8 || m == 10) {
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
    
    public int MonthInteger(final String mStr) {
        int m = 100;
        for (int i = 0; i < 12; ++i) {
            if (mStr == this.monthArray[i]) {
                m = i;
            }
        }
        return m;
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
    
    public double JD(final int date, int month, int year, final double UT) {
        if (month <= 2) {
            month += 12;
            --year;
        }
        final int A = (int)(year / 100.0);
        final int B = 2 - A + (int)(A / 4.0);
        final int C = (int)(365.25 * (year + 4716)) + (int)(30.6001 * (month + 1)) + date + B;
        return C - 1524.5 + UT / 24.0;
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
    
    public double sunL(final double T) {
        double L = 280.46645 + 36000.76983 * T + 3.032E-4 * T * T;
        final double tau = T / 10.0;
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
        final double deltaPsi = -17.2 * Math.sin(0.017453292519943295 * omega) - 1.32 * Math.sin(0.03490658503988659 * LS) - 0.23 * Math.sin(0.03490658503988659 * LM) + 0.21 * Math.sin(0.03490658503988659 * omega);
        return deltaPsi / 3600.0;
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
        return E * 4.0;
    }
    
    public String gmtString() {
        final Date gmtDat = new Date();
        gmtDat.setSeconds(this.seconds);
        gmtDat.setMinutes(this.minutes);
        gmtDat.setHours(this.hours - this.timezoneoffset);
        gmtDat.setYear(this.year);
        gmtDat.setMonth(this.month);
        gmtDat.setDate(this.date);
        final int gmtDate = gmtDat.getDate();
        String str;
        if (gmtDate < 10) {
            str = "0" + gmtDate;
        }
        else {
            str = String.valueOf(gmtDate);
        }
        gmtDat.setHours(this.hours - this.timezoneoffset);
        final int gmtHr = gmtDat.getHours();
        String str2;
        if (gmtHr < 10) {
            str2 = "0" + gmtHr;
        }
        else {
            str2 = String.valueOf(gmtHr);
        }
        final int gmtMin = gmtDat.getMinutes();
        String str3;
        if (gmtMin < 10) {
            str3 = ":0" + gmtMin;
        }
        else {
            str3 = ":" + gmtMin;
        }
        final int gmtSec = gmtDat.getSeconds();
        String str4;
        if (gmtSec < 10) {
            str4 = ":0" + gmtSec;
        }
        else {
            str4 = ":" + gmtSec;
        }
        return gmtDat.getYear() + 1900 + " " + this.monthArray[gmtDat.getMonth()] + " " + str + " at " + str2 + str3 + str4;
    }
    
    public void doData() {
        for (int i = 0; i <= 24; ++i) {
            final double Jd = this.JD(this.date, this.month + 1, this.year + 1900, i - this.timezoneoffset);
            String str;
            if (i < 10) {
                str = "0" + i + ":00";
            }
            else {
                str = i + ":00";
            }
            final double dec = this.comp.MoonMeeus(Jd, 1);
            final double RA = this.comp.MoonMeeus(Jd, 2);
            double elev = this.comp.sun_elev(Jd, this.latitude, -this.longitude, dec, RA);
            elev -= this.comp.moonParal(elev, this.moonDistance);
            elev += this.comp.refract(elev) / 60.0;
            final double az = this.comp.azimuth(Jd, RA, dec, this.latitude, this.longitude);
            String str2;
            if (elev > 0.133) {
                str2 = String.valueOf(Math.round(100.0 * elev) / 100.0);
            }
            else {
                str2 = "";
            }
            while (str2.length() < 8) {
                str2 = String.valueOf(str2) + " ";
            }
            final String str3 = String.valueOf(Math.round(100.0 * az) / 100.0);
            this.Data[i] = String.valueOf(str) + "    " + str2 + "  " + str3;
        }
        String str = String.valueOf(this.latStr) + this.nsStr + ",  " + this.longStr + this.ewStr + "  " + this.timeString + "\n" + "\n";
        str = String.valueOf(str) + "Local    App.     Azimuth\n";
        str = String.valueOf(str) + "         Alt.\n";
        this.Data[0] = String.valueOf(str) + this.Data[0];
    }
    
    public void paint(final Graphics g) {
        this.minutes = this.dat.getMinutes();
        this.seconds = this.dat.getSeconds();
        this.hours = this.dat.getHours();
        this.date = this.dat.getDate();
        this.year = this.dat.getYear();
        final double time = this.hours + this.minutes / 60.0 + this.seconds / 3600.0;
        final double UT = time - this.timezoneoffset;
        this.comp = new compute();
        final double Jd = this.JD(this.date, this.month + 1, this.year + 1900, UT);
        final double T = (Jd - 2451545.0) / 36525.0;
        final double dec = this.comp.MoonMeeus(Jd, 1);
        double RA = this.comp.MoonMeeus(Jd, 2);
        final double trueAlt;
        double elev = trueAlt = this.comp.sun_elev(Jd, this.latitude, -this.longitude, dec, RA);
        this.myMoonDistance = new MoonDistance(this.date, this.month + 1, this.year, 12.0);
        this.moonDistance = this.myMoonDistance.compute();
        final double paral = this.comp.moonParal(elev, this.moonDistance);
        elev -= this.comp.moonParal(elev, this.moonDistance);
        elev += this.comp.refract(elev) / 60.0;
        final double az = this.comp.azimuth(Jd, RA, dec, this.latitude, this.longitude);
        final double B = this.comp.MoonMeeus(Jd, 4);
        g.setFont(new Font("Courier", 0, 12));
        final double LST = this.comp.LM_Sidereal_Time(Jd, -this.longitude);
        final double L = this.comp.MoonMeeus(Jd, 3);
        double H = 15.0 * (LST - RA);
        if (H < 0.0) {
            H += 360.0;
        }
        RA = 15.0 * RA % 360.0;
        if (RA < 0.0) {
            RA += 360.0;
        }
        final String gmtStr = this.gmtString();
        g.drawString("Date, UT   " + gmtStr, 50, 115);
        final double F = 1.0E10;
        g.drawString("Jul. Day   " + Math.round(100000.0 * Jd) / 100000.0, 50, 130);
        g.drawString("T2000      " + Math.round(F * T) / F, 50, 145);
        g.drawString("Latitude   " + Math.round(100.0 * B * 180.0 / 3.141592653589793) / 100.0 + '°', 50, 160);
        g.drawString("Longitude  " + Math.round(100.0 * L * 180.0 / 3.141592653589793) / 100.0 + '°', 50, 175);
        g.drawString("Declin.    " + Math.round(100.0 * dec) / 100.0 + '°', 50, 190);
        g.drawString("Right Asc. " + Math.round(100.0 * RA) / 100.0 + '°' + " = " + this.comp.hms(RA), 50, 205);
        g.drawString(this.str = String.valueOf(this.latStr) + '°' + this.nsStr + ",  " + this.longStr + '°' + this.ewStr, 300, 115);
        g.drawString("Local Sid. Time  " + Math.round(15000.0 * LST) / 1000.0 + '°' + " = " + this.comp.hms(15.0 * LST), 300, 130);
        g.drawString("Loc. Hour Angle  " + Math.round(100.0 * H) / 100.0 + '°', 300, 145);
        g.drawString("True Altitude    " + Math.round(10.0 * trueAlt) / 10.0 + '°', 300, 160);
        g.drawString("Parallax Alt.    " + Math.round(100.0 * paral) / 100.0 + '°', 300, 175);
        g.drawString("App. Altitude    " + Math.round(10.0 * elev) / 10.0 + '°', 300, 190);
        g.drawString("Azimuth          " + Math.round(10.0 * az) / 10.0 + '°', 300, 205);
        final String dgr = " deg";
        final String[] text = { "Date, UT   " + gmtStr, "Jul. Day   " + Math.round(100000.0 * Jd) / 100000.0, "T          " + Math.round(F * T) / F, "Long. Sun  " + Math.round(100.0 * L) / 100.0 + dgr, "Declin.    " + Math.round(100.0 * dec) / 100.0 + dgr, "Right Asc. " + Math.round(100.0 * RA) / 100.0 + dgr, "Local Sid. Time  " + Math.round(15000.0 * LST) / 1000.0 + dgr, "Loc. Hour Angle  " + Math.round(100.0 * H) / 100.0 + dgr, "Azimuth          " + Math.round(10.0 * az) / 10.0 + dgr, null };
        final int r = (int)Math.floor(10.0 * Math.random());
        if (this.demo) {
            text[r] = " D E M O";
        }
        g.setFont(new Font("Helvetica", 0, 11));
        g.drawString(String.valueOf(this.versStr) + "  (c) 2006 J.Giesen - www.GeoAstro.de", 130, this.size().height - 5);
        g.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
        if (this.demo) {
            g.setFont(new Font("Chicago", 0, 96));
            g.setColor(Color.red);
            g.drawString("D E M O", 120, 200);
        }
    }
    
    public boolean action(final Event event, final Object eventobject) {
        final double oldLat = this.latitude;
        final double oldLong = this.longitude;
        if (event.target instanceof Button && event.target == this.nowButton) {
            this.dat = new Date();
            this.hours = this.dat.getHours();
            this.hoursChoice.select(this.hours);
            this.minutes = this.dat.getMinutes();
            this.minChoice.select(this.minutes);
            this.seconds = this.dat.getSeconds();
            this.date = this.dat.getDate();
            this.dateChoice.select(this.date - 1);
            this.month = this.dat.getMonth();
            this.monthChoice.select(this.month);
            this.year = this.dat.getYear();
            this.yearStr = String.valueOf(this.year + 1900);
            this.yearChoice.select(this.yearStr);
            this.repaint();
            return true;
        }
        if (event.target instanceof TextField) {
            this.str = "";
            if (event.target == this.fieldLatDeg) {
                this.str = this.fieldLatDeg.getText();
                for (int i = 0; i < this.str.length(); ++i) {
                    final char c = this.str.charAt(i);
                    if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != '.') {
                        this.fieldLatDeg.setText(String.valueOf(Math.abs(oldLat)));
                        return true;
                    }
                }
                if (!this.str.equals(this.latStr) || this.str.length() == 0) {
                    this.locString = "User Input";
                    this.locChoice.select(this.locString);
                    final Double latDouble = Double.valueOf(this.str);
                    this.usrLat = latDouble;
                }
                this.latStr = this.str;
                final Double latDouble = Double.valueOf(this.latStr);
                this.latitude = latDouble;
                if (this.nsChoice.getSelectedItem().equals("S")) {
                    this.latitude = -Math.abs(this.latitude);
                    this.usrLat = this.latitude;
                }
                this.fieldLatDeg.setText(this.str);
                if (Math.abs(this.latitude) >= 90.0) {
                    if (this.latitude == 90.0) {
                        this.latitude = 89.9999;
                    }
                    if (this.latitude == -90.0) {
                        this.latitude = -89.9999;
                    }
                    if (this.latitude > 90.0) {
                        this.latitude = oldLat;
                    }
                    if (this.latitude < -90.0) {
                        this.latitude = oldLat;
                    }
                    this.latStr = String.valueOf(Math.abs(Math.round(1000.0 * this.latitude) / 1000.0));
                    this.fieldLatDeg.setText(this.latStr);
                }
                else {
                    if (!this.str.equals(this.latStr) || this.str.length() == 0) {
                        this.latStr = this.str;
                        this.locString = "User Input";
                        this.locChoice.select(this.locString);
                    }
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                }
                this.repaint();
                return true;
            }
            if (event.target == this.fieldLongDeg) {
                this.str = this.fieldLongDeg.getText();
                for (int i = 0; i < this.str.length(); ++i) {
                    final char c = this.str.charAt(i);
                    if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != '.') {
                        this.fieldLongDeg.setText(String.valueOf(Math.abs(oldLong)));
                        return true;
                    }
                }
                if (!this.str.equals(this.longStr) || this.str.length() == 0) {
                    this.locString = "User Input";
                    this.locChoice.select(this.locString);
                    final Double longDouble = Double.valueOf(this.str);
                    this.usrLong = longDouble;
                }
                this.longStr = this.str;
                final Double longDouble = Double.valueOf(this.str);
                this.longitude = longDouble;
                if (this.ewChoice.getSelectedItem().equals("W")) {
                    this.longitude = -Math.abs(this.longitude);
                    this.usrLong = this.longitude;
                }
                this.fieldLongDeg.setText(this.longStr);
                if (Math.abs(this.longitude) >= 180.0) {
                    this.longitude = oldLong;
                    this.longStr = String.valueOf(Math.abs(Math.round(100.0 * oldLong) / 100.0));
                    this.fieldLongDeg.setText(this.longStr);
                }
                else {
                    if (!this.str.equals(this.longStr) || this.str.length() == 0) {
                        this.longStr = this.str;
                        this.locString = "User Input";
                        this.locChoice.select(this.locString);
                    }
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                }
                this.repaint();
                return true;
            }
        }
        if (event.target instanceof Choice) {
            if (event.target == this.writeChoice && this.writeChoice.getSelectedItem().equals("Day")) {
                this.writeChoice.select(0);
                final boolean dem = this.online || this.demo;
                this.doData();
                final Frame df = new dataFrame(this.year + 1900 + " " + this.monthArray[this.month] + " " + this.date, this.versStr, this.Data, this.nData, dem);
                df.resize(280, 425);
                df.show();
                return true;
            }
            if (event.target == this.locChoice) {
                if (this.locChoice.getSelectedItem().equals(this.separator)) {
                    this.locChoice.select(this.locString);
                    return true;
                }
                this.locString = this.locChoice.getSelectedItem();
                if (this.locChoice.getSelectedItem().equals(this.homeString)) {
                    this.latitude = this.homeLat;
                    this.longitude = this.homeLong;
                }
                else {
                    this.latitude = this.loc.getLatLong(this.locString, 1);
                    this.longitude = this.loc.getLatLong(this.locString, 2);
                }
                if (this.locChoice.getSelectedItem().equals("User Input")) {
                    this.latitude = this.usrLat;
                    this.longitude = this.usrLong;
                }
                this.latStr = String.valueOf(Math.abs(this.latitude));
                this.fieldLatDeg.setText(this.latStr);
                this.longStr = String.valueOf(Math.abs(this.longitude));
                this.fieldLongDeg.setText(this.longStr);
                if (this.longitude <= 0.0) {
                    this.ewChoice.select("W");
                }
                else {
                    this.ewChoice.select("E");
                }
                if (this.latitude <= 0.0) {
                    this.nsChoice.select("S");
                }
                else {
                    this.nsChoice.select("N");
                }
                this.repaint();
                return true;
            }
            else {
                if (event.target == this.ewChoice) {
                    if (!this.ewChoice.getSelectedItem().equals(this.ewStr) || !this.fieldLongDeg.getText().equals(this.longStr)) {
                        this.locChoice.select("User Input");
                        this.locString = "User Input";
                    }
                    this.ewStr = this.ewChoice.getSelectedItem();
                    if (this.longStr.length() > 0) {
                        final Double longDouble = Double.valueOf(this.longStr);
                        this.longitude = longDouble;
                    }
                    if (this.ewChoice.getSelectedItem().equals("W")) {
                        this.ewStr = "W";
                        this.longitude = -this.longitude;
                    }
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                    this.usrLat = this.latitude;
                    this.usrLong = this.longitude;
                    this.repaint();
                    return true;
                }
                if (event.target == this.nsChoice) {
                    if (!this.nsChoice.getSelectedItem().equals(this.nsStr) || !this.fieldLatDeg.getText().equals(this.latStr)) {
                        this.locChoice.select("User Input");
                        this.locString = "User Input";
                    }
                    this.nsStr = this.nsChoice.getSelectedItem();
                    if (this.latStr.length() > 0) {
                        final Double latDouble = Double.valueOf(this.latStr);
                        this.latitude = latDouble;
                    }
                    if (this.nsChoice.getSelectedItem().equals("S")) {
                        this.nsStr = "S";
                        this.latitude = -this.latitude;
                    }
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                    if (this.latitude == 90.0) {
                        this.latitude = 89.999;
                    }
                    if (this.latitude == -90.0) {
                        this.latitude = -89.999;
                    }
                    this.repaint();
                    return true;
                }
                if (event.target == this.timeChoice) {
                    this.timeString = this.timeChoice.getSelectedItem();
                    this.timezoneoffset = this.loc.getTimeZone(this.timeString);
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                    this.repaint();
                    return true;
                }
                if (event.target == this.hoursChoice) {
                    this.hoursStr = this.hoursChoice.getSelectedItem();
                    this.hours = Integer.parseInt(this.hoursStr);
                    this.hoursChoice.select(this.hoursStr);
                    this.dat.setHours(this.hours);
                    this.dat.setSeconds(0);
                    this.repaint();
                    return true;
                }
                if (event.target == this.minChoice) {
                    this.minStr = this.minChoice.getSelectedItem();
                    this.minutes = Integer.parseInt(this.minStr);
                    this.minChoice.select(this.minStr);
                    this.dat.setMinutes(this.minutes);
                    this.dat.setSeconds(0);
                    this.repaint();
                    return true;
                }
                if (event.target == this.dateChoice) {
                    this.dateStr = this.dateChoice.getSelectedItem();
                    this.date = Integer.parseInt(this.dateStr);
                    this.dat.setDate(this.date);
                    this.dat.setSeconds(0);
                    final int n = this.daysInMonth(this.month, this.year);
                    if (this.date > n) {
                        this.date = 1;
                        this.dat.setDate(this.date);
                        this.dateChoice.select(this.date - 1);
                        ++this.month;
                        this.dat.setMonth(this.month);
                        this.monthChoice.select(this.month);
                    }
                    this.repaint();
                    return true;
                }
                if (event.target == this.monthChoice) {
                    this.monthStr = this.monthChoice.getSelectedItem();
                    this.monthChoice.select(this.monthStr);
                    this.month = this.MonthInteger(this.monthStr);
                    this.dat.setSeconds(0);
                    final int n = this.daysInMonth(this.month, this.year);
                    if (this.date > n) {
                        this.date = n;
                        this.dat.setDate(this.date);
                        this.dateChoice.select(this.date - 1);
                    }
                    this.dat.setMonth(this.month);
                    this.repaint();
                    return true;
                }
                if (event.target == this.yearChoice) {
                    this.yearStr = this.yearChoice.getSelectedItem();
                    this.yearChoice.select(this.yearStr);
                    this.year = Integer.parseInt(this.yearStr);
                    this.yearChoice.select(this.yearStr);
                    this.year -= 1900;
                    this.dat.setYear(this.year);
                    this.dat.setSeconds(0);
                    this.repaint();
                    return true;
                }
            }
        }
        return true;
    }
    
    public elevazmoon092() {
        this.versStr = "Elevation & Azimuth Moon  v. 0.92 ";
        this.demo = true;
        this.online = false;
        this.usrLat = 0.0;
        this.usrLong = 0.0;
        this.monthArray = new String[12];
        this.usrStr = "demo";
        this.separator = "---------------";
        this.Data = new String[25];
        this.nData = 25;
    }
}
