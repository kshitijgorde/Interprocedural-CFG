import java.awt.Cursor;
import java.awt.Point;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Date;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Button;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SunMoonHorizon0982 extends Applet
{
    final double K = 0.017453292519943295;
    char deg;
    int year;
    int month;
    int date;
    int hours;
    int minutes;
    public double latitude;
    public double longitude;
    int browserOffset;
    public int locOffset;
    String versStr;
    public sunloc loc;
    public sunloc time;
    Button button;
    public String email;
    public String param;
    public String wwwStr;
    public Choice locChoice;
    public Choice timeChoice;
    public String locString;
    public String timeString;
    TextField fieldLatDeg;
    TextField fieldLongDeg;
    Choice ewChoice;
    Choice nsChoice;
    Choice hoursChoice;
    Choice minChoice;
    Choice yearChoice;
    Choice dateChoice;
    Choice monthChoice;
    String hoursStr;
    String minStr;
    String dateStr;
    String monthStr;
    String ewStr;
    String nsStr;
    String latStr;
    String longStr;
    String[] monthArray;
    double[] sunDeclinArray;
    double[] moonDeclinArray;
    double[] moonRiseAz;
    double[] moonSetAz;
    double[] moonRiseJD;
    double[] moonSetJD;
    double[] sunRiseSetAz;
    double[] sunRiseSetJD;
    public compute comp;
    Checkbox moonBox;
    String str;
    boolean online;
    String homeString;
    String homeLatStr;
    String homeLongStr;
    double homeLat;
    double homeLong;
    boolean demo;
    String userString;
    double usrLat;
    double usrLong;
    Choice sunChoice;
    Choice moonChoice;
    int sunAngle;
    int moonAngle;
    boolean sunRiseOK;
    boolean moonRiseOK;
    boolean drawSunOK;
    boolean drawMoonOK;
    int xMouse;
    int yMouse;
    int xUrsprung;
    int yUrsprung;
    int W;
    int H;
    double faktor;
    boolean drawMoonDeclin;
    boolean drawSunDeclin;
    boolean sunEclipseOK;
    boolean moonEclipseOK;
    int sunChoiceItem;
    int moonChoiceItem;
    String[] Data;
    int nData;
    Choice writeChoice;
    boolean dem;
    double currentMoonAz;
    double currentMoonElev;
    double moonDistance;
    double horParal;
    double paral;
    double UT;
    double moonPhase;
    double moonPhase1;
    String moonPhaseStr;
    double currentSunAz;
    double currentSunElev;
    String h_SunRiseStr;
    String h_SunSetStr;
    String h_MoonRiseStr;
    String h_MoonSetStr;
    Rectangle rect;
    int[] fr;
    int[] frSun;
    double maxRiseAz;
    double minRiseAz;
    double maxSetAz;
    double minSetAz;
    double jdRiseMax;
    double jdRiseMin;
    double jdSetMax;
    double jdSetMin;
    double currentMoonRiseJD;
    double currentMoonSetJD;
    double currentMoonSetAz;
    double currentMoonRiseAz;
    double currentSunRiseJD;
    double currentSunSetJD;
    double currentSunRiseDeclin;
    double currentMoonRiseDeclin;
    double currentSunSetDeclin;
    double currentJD;
    double[] fullMoonJD;
    boolean fullMoon;
    String[] fmStr;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 8117 + 937;
    }
    
    public void init() {
        this.setFont(new Font("Helvetica", 0, 12));
        this.xUrsprung = 30;
        this.yUrsprung = this.size().height - 40;
        this.H = this.size().height - 160;
        this.faktor = this.H / 180.0;
        this.rect = new Rectangle(this.xUrsprung, this.yUrsprung - this.H, this.W, this.H);
        this.setBackground(Color.white);
        System.out.println(this.versStr);
        System.out.println("© 2003-2011 J. Giesen - www.GeoAstro.de");
        this.userString = this.getParameter("email");
        this.homeString = this.getParameter("location");
        this.homeLatStr = this.getParameter("latitude");
        this.homeLongStr = this.getParameter("longitude");
        final URL url = this.getDocumentBase();
        this.str = url.toString();
        this.str = String.valueOf(this.str) + "1234567890123456789012345";
        this.wwwStr = this.str.substring(0, 27);
        final Date dat = new Date();
        this.hours = dat.getHours();
        this.minutes = dat.getMinutes();
        this.date = dat.getDate();
        this.month = dat.getMonth();
        this.year = dat.getYear();
        this.browserOffset = dat.getTimezoneOffset();
        this.browserOffset = -this.browserOffset / 60;
        this.locOffset = this.browserOffset;
        this.timeString = String.valueOf(this.browserOffset) + " h";
        if (this.browserOffset > 0) {
            this.timeString = "+" + this.timeString;
        }
        this.timeString = "UT " + this.timeString;
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(2, 0, 0, 0);
        Label L = new Label("Time Zone");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("Location");
        gbl.setConstraints(L, gbc);
        this.add(L);
        gbc.weightx = 10.0;
        L = new Label("Latitude: xx.xx");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("N / S");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("Longitude: xx.xx");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("E / W");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("Sun");
        L.setForeground(Color.red);
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("Moon");
        L.setForeground(Color.blue);
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("Full Moon");
        L.setForeground(Color.blue);
        gbc.insets = new Insets(2, 0, 0, 0);
        gbl.setConstraints(L, gbc);
        this.add(L);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        this.time = new sunloc();
        this.timeChoice = new Choice();
        this.time.timeMenu(this.timeChoice);
        gbc.insets = new Insets(2, 10, 0, 0);
        gbl.setConstraints(this.timeChoice, gbc);
        this.timeChoice.select(this.timeString);
        this.add(this.timeChoice);
        gbc.insets = new Insets(2, 0, 0, 0);
        this.loc = new sunloc();
        this.locChoice = new Choice();
        this.locString = this.homeString;
        this.locChoice.addItem(this.locString);
        this.locChoice.addItem("_________");
        this.locChoice.select(this.locString);
        this.loc.locMenu(this.locChoice);
        gbl.setConstraints(this.locChoice, gbc);
        this.add(this.locChoice);
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
            this.ewStr = "E";
        }
        else {
            this.ewStr = "W";
        }
        this.latStr = String.valueOf(Math.abs(this.latitude));
        gbl.setConstraints(this.fieldLatDeg = new TextField(this.latStr, 6), gbc);
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
        gbl.setConstraints(this.ewChoice, gbc);
        this.add(this.ewChoice);
        gbl.setConstraints(this.sunChoice = new Choice(), gbc);
        this.sunChoice.addItem("Rise U.L.");
        this.sunChoice.addItem("Rise 1" + this.deg);
        this.sunChoice.addItem("Rise 2" + this.deg);
        this.sunChoice.addItem("Rise 3" + this.deg);
        this.sunChoice.addItem("Rise 4" + this.deg);
        this.sunChoice.addItem("Rise 5" + this.deg);
        this.sunChoice.addItem("Set U.L.");
        this.sunChoice.addItem("Set 1" + this.deg);
        this.sunChoice.addItem("Set 2" + this.deg);
        this.sunChoice.addItem("Set 3" + this.deg);
        this.sunChoice.addItem("Set 4" + this.deg);
        this.sunChoice.addItem("Set 5" + this.deg);
        this.sunChoice.addItem("Declination");
        this.sunChoice.addItem("Don't draw");
        this.sunChoice.addItem("Sun Ecl On");
        this.add(this.sunChoice);
        this.moonChoice = new Choice();
        gbc.insets = new Insets(0, 0, 0, 10);
        gbl.setConstraints(this.moonChoice, gbc);
        this.moonChoice.addItem("Rise U.L.");
        this.moonChoice.addItem("Rise 1" + this.deg);
        this.moonChoice.addItem("Rise 2" + this.deg);
        this.moonChoice.addItem("Rise 3" + this.deg);
        this.moonChoice.addItem("Rise 4" + this.deg);
        this.moonChoice.addItem("Rise 5" + this.deg);
        this.moonChoice.addItem("Set U.L.");
        this.moonChoice.addItem("Set 1" + this.deg);
        this.moonChoice.addItem("Set 2" + this.deg);
        this.moonChoice.addItem("Set 3" + this.deg);
        this.moonChoice.addItem("Set 4" + this.deg);
        this.moonChoice.addItem("Set 5" + this.deg);
        this.moonChoice.addItem("Declination");
        this.moonChoice.addItem("Don't draw");
        this.moonChoice.addItem("Moon Ecl On");
        this.add(this.moonChoice);
        this.moonBox = new Checkbox();
        gbc.insets = new Insets(5, 0, 0, 0);
        gbl.setConstraints(this.moonBox, gbc);
        this.add(this.moonBox);
        gbc.gridy = 2;
        L = new Label("Local Time:");
        gbc.insets = new Insets(5, 10, 0, 0);
        gbl.setConstraints(L, gbc);
        this.add(L);
        gbc.insets = new Insets(5, 0, 0, 0);
        this.hoursChoice = new Choice();
        for (int i = 0; i < 10; ++i) {
            this.hoursStr = "0" + String.valueOf(i);
            this.hoursChoice.addItem(this.hoursStr);
        }
        for (int j = 10; j < 24; ++j) {
            this.hoursStr = String.valueOf(j);
            this.hoursChoice.addItem(this.hoursStr);
        }
        this.hoursChoice.select(this.hours);
        gbl.setConstraints(this.hoursChoice, gbc);
        this.add(this.hoursChoice);
        this.minChoice = new Choice();
        for (int k = 0; k < 10; ++k) {
            this.minStr = "0" + String.valueOf(k);
            this.minChoice.addItem(this.minStr);
        }
        for (int l = 10; l < 60; ++l) {
            this.minStr = String.valueOf(l);
            this.minChoice.addItem(this.minStr);
        }
        this.minChoice.select(this.minutes);
        gbl.setConstraints(this.minChoice, gbc);
        this.add(this.minChoice);
        L = new Label("Date :");
        gbl.setConstraints(L, gbc);
        this.add(L);
        gbc.insets = new Insets(5, 0, 0, 0);
        (this.yearChoice = new Choice()).addItem("-2000");
        this.yearChoice.addItem("-1000");
        this.yearChoice.addItem("0");
        for (int m = 0; m <= 20; ++m) {
            this.yearChoice.addItem(String.valueOf(-10 + m));
        }
        for (int i2 = 0; i2 <= 20; ++i2) {
            this.yearChoice.addItem(String.valueOf(990 + i2));
        }
        for (int i3 = 0; i3 < 111; ++i3) {
            this.yearChoice.addItem(String.valueOf(this.year + 1900 - 70 + i3));
        }
        this.yearChoice.select(String.valueOf(this.year + 1900));
        gbl.setConstraints(this.yearChoice, gbc);
        this.add(this.yearChoice);
        this.monthChoice = new Choice();
        for (int i4 = 0; i4 < 12; ++i4) {
            this.monthChoice.addItem(this.monthArray[i4]);
        }
        this.monthChoice.select(this.month);
        gbl.setConstraints(this.monthChoice, gbc);
        this.add(this.monthChoice);
        this.dateChoice = new Choice();
        for (int i5 = 1; i5 < 32; ++i5) {
            this.dateStr = String.valueOf(i5);
            this.dateChoice.addItem(this.dateStr);
        }
        this.dateChoice.select(this.date - 1);
        gbl.setConstraints(this.dateChoice, gbc);
        this.add(this.dateChoice);
        (this.button = new Button()).setLabel("now");
        gbc.insets = new Insets(5, 0, 0, 0);
        gbl.setConstraints(this.button, gbc);
        this.add(this.button);
        this.writeChoice = new Choice();
        gbc.insets = new Insets(5, 0, 0, 10);
        this.writeChoice.add("Write/Draw...");
        this.writeChoice.add("Table Sun");
        this.writeChoice.add("Table Moon");
        this.writeChoice.add("Sun Histogram");
        this.writeChoice.add("Moon Histogram");
        this.writeChoice.add("Sun Rise");
        this.writeChoice.add("Sun Set");
        this.writeChoice.add("Moon Rise");
        this.writeChoice.add("Moon Set");
        this.writeChoice.add("Full Moon Dates");
        this.writeChoice.add("Full Moon Azimuth");
        gbl.setConstraints(this.writeChoice, gbc);
        this.add(this.writeChoice);
        gbc.gridy = 3;
        gbc.weighty = 160.0;
        final Canvas can = new Canvas();
        gbl.setConstraints(can, gbc);
        this.add(can);
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        if (this.formula(this.wwwStr, 22) == this.formula("http://www.Tyge.de", 18) || this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(this.wwwStr, 22) == this.formula("http://www.geoastro.de", 22) || this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 21) == this.formula("http://www.j-giesen.de", 21)) {
            ok = true;
            this.online = true;
            this.demo = false;
        }
        else {
            ok = false;
        }
        if (!ok) {
            ok = true;
            if (this.email.length() == 0 || (Integer.parseInt(this.param) != this.formula(this.email, this.email.length()) && Integer.parseInt(this.param) != this.formula(this.email, this.email.length()) - 10)) {
                ok = false;
            }
            else {
                ok = true;
                this.demo = false;
            }
            if (this.wwwStr.substring(0, 7).equals("http://")) {
                ok = false;
                this.online = true;
            }
        }
    }
    
    public String makeHMString(final double time) {
        int h = (int)time;
        int m = (int)Math.round(60.0 * (time - h));
        if (m == 60) {
            m = 0;
            ++h;
        }
        String hStr;
        if (h < 10) {
            hStr = "0" + h;
        }
        else {
            hStr = String.valueOf(h);
        }
        String minStr;
        if (m < 10) {
            minStr = ":0" + m;
        }
        else {
            minStr = ":" + m;
        }
        return String.valueOf(hStr) + minStr;
    }
    
    public double JD(final int date, int month, int year, final double UT) {
        if (month <= 2) {
            month += 12;
            --year;
        }
        final int A = (int)(year / 100.0);
        int B = 2 - A + (int)(A / 4.0);
        if (year < 1583) {
            B = 0;
        }
        final int C = (int)(365.25 * (year + 4716)) + (int)(30.6001 * (month + 1)) + date + B;
        return C - 1524.5 + UT / 24.0;
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
    
    public String myGMTString() {
        final double STD = this.hours - this.locOffset + this.minutes / 60.0;
        final double time = STD + this.locOffset;
        final Date datGMT = new Date();
        final int gmtOffset = datGMT.getTimezoneOffset() / 60;
        int hGMT = (int)Math.floor(time) - this.locOffset;
        datGMT.setMonth(this.month);
        datGMT.setYear(this.year);
        int monthGMT = this.month;
        int yearGMT = this.year;
        int dateGMT = this.date;
        datGMT.setDate(dateGMT);
        if (hGMT >= 24) {
            hGMT -= 24;
            ++dateGMT;
            if (dateGMT > this.daysInMonth(monthGMT, yearGMT)) {
                dateGMT = 1;
                ++monthGMT;
            }
            if (monthGMT == 12) {
                monthGMT = 0;
                ++yearGMT;
            }
            datGMT.setMonth(monthGMT);
            datGMT.setYear(yearGMT);
            datGMT.setDate(dateGMT);
        }
        if (hGMT <= 0) {
            hGMT += 24;
            --dateGMT;
            if (dateGMT == 0) {
                --monthGMT;
                dateGMT = this.daysInMonth(monthGMT, yearGMT);
            }
            if (monthGMT == -1) {
                monthGMT = 11;
                --yearGMT;
            }
            datGMT.setMonth(monthGMT);
            datGMT.setYear(yearGMT);
            datGMT.setDate(dateGMT);
        }
        datGMT.setHours(hGMT);
        datGMT.setMinutes(this.minutes);
        datGMT.setSeconds(0);
        String gmtStr = datGMT.toString();
        gmtStr = String.valueOf(gmtStr.substring(0, 20)) + "UT ";
        if (this.year + 1900 >= 0) {
            gmtStr = String.valueOf(gmtStr) + (1900 + datGMT.getYear());
        }
        else {
            gmtStr = String.valueOf(gmtStr) + (Math.abs(this.year) - 1900) + " BC";
        }
        return gmtStr;
    }
    
    public String datum(final int x) {
        int count = 0;
        int D = 0;
        int M = 0;
        final String str = "";
        for (int m = 0; m < 12; ++m) {
            for (int d = 1; d <= this.daysInMonth(m, this.year); ++d) {
                if (count <= x) {
                    count += 2;
                    D = d;
                    M = m + 1;
                }
            }
        }
        return M + "/" + D;
    }
    
    public void tableMoon(final int year) {
        this.startCursor();
        double riseAZ = 0.0;
        double setAZ = 0.0;
        final String[] fullMoon = new String[35];
        int nFull = 0;
        this.nData = 0;
        this.Data[0] = "           Rise   Azim.  Illum. |    Set   Azim. Illum.\n";
        final String[] data = this.Data;
        final int n = 0;
        data[n] = String.valueOf(data[n]) + "                           %    |                  %\n";
        this.nData = 1;
        final double jd1 = this.JD(1, 1, year + 1900, 0.0);
        for (int i = 0; i <= 365 && this.moonRiseJD[i] <= this.JD(1, 1, year + 1900 + 1, 0.0); ++i) {
            final double jd2 = jd1 + i;
            String str1 = String.valueOf(this.monthArray[this.caldat(2, this.moonRiseJD[i])]) + " ";
            final int d = this.caldat(1, this.moonRiseJD[i]);
            if (d < 10) {
                str1 = String.valueOf(str1) + "0";
            }
            str1 = String.valueOf(str1) + "" + d + " ";
            riseAZ = this.moonRiseAz[i];
            setAZ = this.moonSetAz[i];
            final double hRise = this.caldat(3, this.moonRiseJD[i]) + this.caldat(5, this.moonRiseJD[i]) / 60.0 + this.locOffset;
            final double hSet = this.caldat(3, this.moonSetJD[i]) + this.caldat(5, this.moonSetJD[i]) / 60.0 + this.locOffset;
            final double phase1 = 100.0 * this.phase(this.moonRiseJD[i]);
            String str2;
            for (str2 = String.valueOf(Math.round(phase1)); str2.length() < 3; str2 = " " + str2) {}
            final double phase2 = 100.0 * this.phase(this.moonSetJD[i]);
            String str3;
            for (str3 = String.valueOf(Math.round(phase2)); str3.length() < 3; str3 = " " + str3) {}
            String str4;
            for (str4 = String.valueOf(Math.round(10.0 * riseAZ) / 10.0); str4.length() < 5; str4 = " " + str4) {}
            String str5;
            if (hRise != 0.0) {
                str5 = this.makeHMString(hRise);
            }
            else {
                str5 = "--:--";
                str4 = "  -.-";
                str2 = "  -";
            }
            String str6;
            for (str6 = String.valueOf(Math.round(10.0 * setAZ) / 10.0); str6.length() < 5; str6 = " " + str6) {}
            String str7;
            if (hSet != 0.0) {
                str7 = this.makeHMString(hSet);
            }
            else {
                str7 = "--:--";
                str6 = "  -.-";
                str3 = "  -";
            }
            this.Data[this.nData] = String.valueOf(str1) + "   " + str5 + "   " + str4 + "    " + str2 + "  |  " + str7 + "   " + str6 + "    " + str3;
            ++this.nData;
            if (Math.round(phase1) == 100L || Math.round(phase2) == 100L) {
                fullMoon[nFull] = this.Data[this.nData - 1];
                ++nFull;
            }
        }
        this.Data[this.nData] = "\nFull Moon at Rise or Set";
        ++this.nData;
        for (int j = 0; j < nFull; ++j) {
            this.Data[this.nData] = fullMoon[j];
            ++this.nData;
        }
        --this.nData;
        this.stopCursor();
    }
    
    double frac(double x) {
        x -= (int)x;
        if (x < 0.0) {
            ++x;
        }
        return x;
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
    
    public double moonParal(final double jd, final double h) {
        final double T = (jd - 2415020.0) / 36525.0;
        final double K = 0.017453292519943295;
        final double m = 296.104608 + 477000.0 * T + 198.849108 * T + 0.009192 * T * T;
        final double l = 270.434164 + 480960.0 * T + 307.883142 * T - 0.001133 * T * T;
        final double L = 279.696678 + 36000.0 * T + 0.768925 * T + 3.03E-4 * T * T;
        double P = 3423.0 + 187.0 * Math.cos(0.017453292519943295 * m) + 10.0 * Math.cos(0.03490658503988659 * m) + 34.0 * Math.cos(0.017453292519943295 * (2.0 * (l - L) - m)) + 28.0 * Math.cos(0.017453292519943295 * (2.0 * (l - L))) + 3.0 * Math.cos(0.017453292519943295 * (2.0 * (l - L) + m));
        P /= 3600.0;
        final double r = 6378.14 / Math.sin(0.017453292519943295 * P);
        final double horParal = 8.794 / (r / 1.4959787E8);
        final double paral = Math.cos(0.017453292519943295 * h) * Math.sin(0.017453292519943295 * horParal / 3600.0);
        return Math.asin(paral) / 0.017453292519943295;
    }
    
    public void doMoonRise(final int year) {
        for (int i = 0; i <= 180; ++i) {
            this.fr[i] = 0;
        }
        final double jd1 = this.JD(1, 1, year + 1900, 0.0);
        for (int j = 0; j <= 26; ++j) {
            final double jd2 = jd1 + j / 24.0;
            theMoon tm = new theMoon(jd2, this.latitude, this.longitude);
            double h1 = tm.elev();
            h1 -= this.moonParal(jd2, h1);
            h1 += this.refract(h1) / 60.0;
            h1 -= this.moonAngle;
            tm = new theMoon(jd2 + 0.041666666666666664, this.latitude, this.longitude);
            double h2 = tm.elev();
            h2 -= this.moonParal(jd2 + 0.041666666666666664, h2);
            h2 += this.refract(h2) / 60.0;
            h2 -= this.moonAngle;
            if (h1 * h2 < 0.0 && h1 < 0.0) {
                this.moonRiseJD[0] = jd2;
                break;
            }
        }
        for (int n = 0; n <= 180; ++n) {
            final double jd2 = this.moonRiseJD[0] + n / 1440.0;
            theMoon THEMOON = new theMoon(jd2, this.latitude, this.longitude);
            double h1 = THEMOON.elev();
            h1 -= this.moonParal(jd2, h1);
            h1 += this.refract(h1) / 60.0;
            THEMOON = new theMoon(jd2 + 6.944444444444445E-4, this.latitude, this.longitude);
            double h2 = THEMOON.elev();
            h2 -= this.moonParal(jd2 + 6.944444444444445E-4, h2);
            h2 += this.refract(h2) / 60.0;
            if (h1 <= this.moonAngle && h2 > this.moonAngle) {
                this.moonRiseJD[0] = jd2;
                THEMOON = new theMoon(this.moonRiseJD[0], this.latitude, this.longitude);
                this.moonRiseAz[0] = THEMOON.azim();
                for (int a = 0; a <= 179; ++a) {
                    if (this.moonRiseAz[0] > a && this.moonRiseAz[0] <= a + 1) {
                        final int[] fr = this.fr;
                        final int n3 = 0;
                        ++fr[n3];
                    }
                }
                break;
            }
        }
        final String str = "";
        this.maxRiseAz = 0.0;
        this.minRiseAz = 500.0;
        final double JD0 = this.JD(this.date, this.month + 1, year + 1900, this.locOffset);
        for (int k = 1; k <= 365; ++k) {
            final double Jd = this.moonRiseJD[k - 1] + 1.0;
            if (Jd > this.JD(2, 1, year + 1900 + 1, 0.0)) {
                break;
            }
            int n2 = 0;
            while (n2 <= 180) {
                final double jd2 = Jd + n2 / 1440.0;
                final theMoon THEMOON = new theMoon(jd2, this.latitude, this.longitude);
                double h1 = THEMOON.elev();
                h1 -= this.moonParal(jd2, h1);
                h1 += this.refract(h1) / 60.0;
                h1 -= this.moonAngle;
                final theMoon THEMOON2 = new theMoon(jd2 + 6.944444444444445E-4, this.latitude, this.longitude);
                double h2 = THEMOON2.elev();
                h2 -= this.moonParal(jd2 + 6.944444444444445E-4, h2);
                h2 += this.refract(h2) / 60.0;
                h2 -= this.moonAngle;
                if (h1 * h2 < 0.0 && h1 < 0.0 && h1 < 0.0) {
                    this.moonRiseJD[k] = jd2 + 3.4722222222222224E-4;
                    final theMoon THEMOON3 = new theMoon(this.moonRiseJD[k], this.latitude, this.longitude);
                    this.moonRiseAz[k] = THEMOON3.azim();
                    for (int a2 = 0; a2 <= 179; ++a2) {
                        if (this.moonRiseAz[k] > a2 && this.moonRiseAz[k] <= a2 + 1) {
                            final int[] fr2 = this.fr;
                            final int n4 = a2;
                            ++fr2[n4];
                        }
                    }
                    if (this.moonRiseAz[k] > this.maxRiseAz) {
                        this.maxRiseAz = this.moonRiseAz[k];
                        this.jdRiseMax = this.moonRiseJD[k];
                    }
                    if (this.moonRiseAz[k] < this.minRiseAz) {
                        this.minRiseAz = this.moonRiseAz[k];
                        this.jdRiseMin = this.moonRiseJD[k];
                    }
                    if (this.moonRiseJD[k] > JD0 && this.moonRiseJD[k] < JD0 + 1.0) {
                        this.currentMoonRiseJD = this.moonRiseJD[k];
                        this.currentMoonRiseAz = this.moonRiseAz[k];
                        break;
                    }
                    break;
                }
                else {
                    ++n2;
                }
            }
        }
        if (this.currentMoonRiseJD >= JD0 && this.currentMoonRiseJD < JD0 + 1.05) {
            this.h_MoonRiseStr = String.valueOf(this.makeHMString(this.caldat(3, this.currentMoonRiseJD + this.locOffset / 24.0) + this.caldat(5, this.currentMoonRiseJD + this.locOffset / 24.0) / 60.0)) + " (" + Math.round(10.0 * this.currentMoonRiseAz) / 10.0 + this.deg + ")";
        }
        else {
            this.h_MoonRiseStr = "--:--";
        }
    }
    
    public void doMoonSet(final int year) {
        this.currentMoonSetAz = 0.0;
        for (int i = 0; i <= 180; ++i) {
            this.fr[i] = 0;
        }
        final double jd1 = this.JD(1, 1, year + 1900, 0.0);
        for (int j = 0; j <= 24; ++j) {
            final double jd2 = jd1 + j / 24.0;
            theMoon tm = new theMoon(jd2, this.latitude, this.longitude);
            double h1 = tm.elev();
            h1 -= this.moonParal(jd2, h1);
            h1 += this.refract(h1) / 60.0;
            h1 -= this.moonAngle;
            tm = new theMoon(jd2 + 0.041666666666666664, this.latitude, this.longitude);
            double h2 = tm.elev();
            h2 -= this.moonParal(jd2 + 0.041666666666666664, h2);
            h2 += this.refract(h2) / 60.0;
            h2 -= this.moonAngle;
            if (h1 * h2 < 0.0 && h2 < 0.0) {
                this.moonSetJD[0] = jd2;
                break;
            }
        }
        for (int n = 0; n <= 180; ++n) {
            final double jd2 = this.moonSetJD[0] + n / 1440.0;
            theMoon THEMOON = new theMoon(jd2, this.latitude, this.longitude);
            double h1 = THEMOON.elev();
            h1 -= this.moonParal(jd2, h1);
            h1 += this.refract(h1) / 60.0;
            h1 -= this.moonAngle;
            THEMOON = new theMoon(jd2 + 6.944444444444445E-4, this.latitude, this.longitude);
            double h2 = THEMOON.elev();
            h2 -= this.moonParal(jd2 + 6.944444444444445E-4, h2);
            h2 += this.refract(h2) / 60.0;
            h2 -= this.moonAngle;
            if (h1 * h2 < 0.0 && h1 > 0.0 && h2 < 0.0) {
                this.moonSetJD[0] = jd2;
                THEMOON = new theMoon(this.moonSetJD[0], this.latitude, this.longitude);
                this.moonSetAz[0] = THEMOON.azim();
                for (int a = 0; a <= 179; ++a) {
                    if (this.moonSetAz[0] > 180 + a && this.moonSetAz[0] <= 180 + a + 1) {
                        final int[] fr = this.fr;
                        final int n3 = 0;
                        ++fr[n3];
                    }
                }
                break;
            }
        }
        this.maxSetAz = 0.0;
        this.minSetAz = 500.0;
        for (int k = 1; k <= 365; ++k) {
            final double Jd = this.moonSetJD[k - 1] + 1.0;
            if (Jd > this.JD(2, 1, year + 1900 + 1, 0.0)) {
                break;
            }
            int n2 = 0;
            while (n2 <= 180) {
                final double jd2 = Jd + n2 / 1440.0;
                final theMoon THEMOON = new theMoon(jd2, this.latitude, this.longitude);
                double h1 = THEMOON.elev();
                h1 -= this.moonParal(jd2, h1);
                h1 += this.refract(h1) / 60.0;
                final theMoon THEMOON2 = new theMoon(jd2 + 6.944444444444445E-4, this.latitude, this.longitude);
                double h2 = THEMOON2.elev();
                h2 -= this.moonParal(jd2 + 6.944444444444445E-4, h2);
                h2 += this.refract(h2) / 60.0;
                if (h1 >= this.moonAngle && h2 < this.moonAngle) {
                    this.moonSetJD[k] = jd2 + 3.4722222222222224E-4;
                    final theMoon THEMOON3 = new theMoon(this.moonSetJD[k], this.latitude, this.longitude);
                    this.moonSetAz[k] = THEMOON3.azim();
                    for (int a2 = 0; a2 < 180; ++a2) {
                        if (this.moonSetAz[k] > 180 + a2 && this.moonSetAz[k] <= 180 + a2 + 1) {
                            final int[] fr2 = this.fr;
                            final int n4 = a2;
                            ++fr2[n4];
                        }
                    }
                    if (this.moonSetAz[k] > this.maxSetAz) {
                        this.maxSetAz = this.moonSetAz[k];
                        this.jdSetMax = this.moonSetJD[k];
                    }
                    if (this.moonSetAz[k] < this.minSetAz) {
                        this.minSetAz = this.moonSetAz[k];
                        this.jdSetMin = this.moonSetJD[k];
                    }
                    if (this.caldat(1, this.moonSetJD[k] + this.locOffset / 24.0) == this.date && this.caldat(2, this.moonSetJD[k] + this.locOffset / 24.0) == this.month) {
                        this.currentMoonSetJD = this.moonSetJD[k];
                        this.currentMoonSetAz = this.moonSetAz[k];
                        break;
                    }
                    break;
                }
                else {
                    ++n2;
                }
            }
        }
        final double j_d = this.JD(this.date, this.month + 1, year + 1900, 0.0);
        if (this.currentMoonSetJD >= j_d && this.currentMoonSetJD < j_d + 1.0) {
            this.h_MoonSetStr = String.valueOf(this.makeHMString(this.caldat(3, this.currentMoonSetJD + this.locOffset / 24.0) + this.caldat(5, this.currentMoonSetJD + this.locOffset / 24.0) / 60.0)) + " (" + Math.round(10.0 * this.currentMoonSetAz) / 10.0 + this.deg + ")";
        }
        else {
            this.h_MoonSetStr = "--:--";
        }
    }
    
    double phase(final double jd) {
        final double T = (jd - 2451545.0) / 36525.0;
        final double D = 297.8502042 + 445267.1115168 * T - 0.00163 * T * T + T * T * T / 545868.0 - T * T * T * T / 1.13065E8;
        final double M = 357.5291092 + 35999.0502909 * T - 1.536E-4 * T * T + T * T * T / 2.449E7;
        final double Ms = 134.9634114 + 477198.8676313 * T + 0.008997 * T * T + T * T * T / 69699.0 - T * T * T * T / 1.4712E7;
        final double i = 180.0 - D - 6.289 * Math.sin(0.017453292519943295 * Ms) + 2.1 * Math.sin(0.017453292519943295 * M) - 1.274 * Math.sin(0.017453292519943295 * (2.0 * D - Ms)) - 0.658 * Math.sin(0.03490658503988659 * D) - 0.214 * Math.sin(0.03490658503988659 * Ms) - 0.11 * Math.sin(0.017453292519943295 * D);
        return 0.5 * (1.0 + Math.cos(0.017453292519943295 * i));
    }
    
    double refract(final double h) {
        final double K = 0.017453292519943295;
        return 1.02 / Math.tan(K * (h + 10.3 / (h + 5.11)));
    }
    
    public void tableSun(final int year) {
        this.startCursor();
        double riseAZ = 0.0;
        double setAZ = 0.0;
        this.nData = 0;
        this.Data[0] = "          Rise   Azim.  |    Set   Azim.";
        this.nData = 1;
        final double jd1 = this.JD(1, 1, year + 1900, 0.0);
        for (int i = 0; i <= 365; ++i) {
            final double jd2 = jd1 + i;
            if (jd2 > this.JD(31, 12, year + 1900, 0.0)) {
                break;
            }
            String str1 = String.valueOf(this.monthArray[this.caldat(2, jd2)]) + " ";
            final int d = this.caldat(1, jd2);
            if (d < 10) {
                str1 = String.valueOf(str1) + "0";
            }
            str1 = String.valueOf(str1) + "" + d;
            SunRiseSet SR = new SunRiseSet(1, jd1 + i, this.latitude, this.longitude, this.sunAngle);
            double hRise = SR.rise();
            hRise = this.caldat(3, hRise) + this.caldat(5, hRise) / 60.0;
            riseAZ = SR.azRise();
            SR = new SunRiseSet(2, jd1 + i, this.latitude, this.longitude, this.sunAngle);
            double hSet = SR.rise();
            hSet = this.caldat(3, hSet) + this.caldat(5, hSet) / 60.0;
            setAZ = SR.azRise();
            String str2;
            for (str2 = String.valueOf(Math.round(10.0 * riseAZ) / 10.0); str2.length() < 5; str2 = " " + str2) {}
            String str3;
            if (hRise != 0.0) {
                str3 = this.makeHMString(hRise);
            }
            else {
                str3 = "--:--";
                str2 = "  -.-";
            }
            String str4;
            for (str4 = String.valueOf(Math.round(10.0 * setAZ) / 10.0); str4.length() < 5; str4 = " " + str4) {}
            String str5;
            if (hSet != 0.0) {
                str5 = this.makeHMString(hSet);
            }
            else {
                str5 = "--:--";
                str4 = "  -.-";
            }
            if (this.caldat(1, jd2) == 1) {
                this.Data[this.nData] = "----------------------------------------";
                ++this.nData;
            }
            this.Data[this.nData] = String.valueOf(str1) + "   " + str3 + "   " + str2 + "  |  " + str5 + "   " + str4;
            ++this.nData;
        }
        --this.nData;
        this.stopCursor();
    }
    
    public void paint(final Graphics g) {
        this.UT = this.hours - this.locOffset + this.minutes / 60.0;
        this.currentJD = this.JD(this.date, this.month + 1, this.year + 1900, this.UT);
        final double jd1 = this.JD(1, 1, this.year + 1900, 0.0);
        this.doMoonRise(this.year);
        this.doMoonSet(this.year);
        this.sunChoiceItem = this.sunChoice.getSelectedIndex();
        this.moonChoiceItem = this.moonChoice.getSelectedIndex();
        final double F = this.H / 90.0;
        int xM = this.xMouse - this.xUrsprung;
        xM = 2 * (int)Math.round(0.5 * xM);
        this.setBackground(Color.white);
        double maxMoonDeclin = 0.0;
        double minMoonDeclin = 0.0;
        g.setFont(new Font("Courier", 0, 10));
        String angleSunStr = "";
        g.drawString(this.myGMTString(), 20, this.yUrsprung - this.H - 25);
        g.drawString("JD " + Math.round(1000.0 * this.currentJD) / 1000.0, 20, this.yUrsprung - this.H - 10);
        if (this.sunAngle == 0) {
            angleSunStr = "(UL):";
        }
        else if (this.sunAngle == 1) {
            angleSunStr = "(1" + this.deg + "):";
        }
        else if (this.sunAngle == 2) {
            angleSunStr = "(2" + this.deg + "):";
        }
        else if (this.sunAngle == 3) {
            angleSunStr = "(3" + this.deg + "):";
        }
        else if (this.sunAngle == 4) {
            angleSunStr = "(4" + this.deg + "):";
        }
        else if (this.sunAngle == 4) {
            angleSunStr = "(5" + this.deg + "):";
        }
        g.setColor(Color.red);
        g.drawString("SUN  " + angleSunStr, this.xUrsprung + 160, this.yUrsprung - this.H - 25);
        g.setColor(Color.black);
        SunRiseSet SR = new SunRiseSet(1, this.JD(this.date, this.month + 1, this.year + 1900, 0.0), this.latitude, this.longitude, this.sunAngle);
        this.currentSunRiseJD = SR.rise();
        this.h_SunRiseStr = this.makeHMString(this.caldat(3, this.currentSunRiseJD + this.locOffset / 24.0) + this.caldat(5, this.currentSunRiseJD + this.locOffset / 24.0) / 60.0);
        g.drawString("Rise " + this.h_SunRiseStr + " (" + Math.round(100.0 * SR.azRise()) / 100.0 + this.deg + ")", this.xUrsprung + 230, this.yUrsprung - this.H - 25);
        SR = new SunRiseSet(2, this.JD(this.date, this.month + 1, this.year + 1900, 0.0), this.latitude, this.longitude, this.sunAngle);
        this.currentSunSetJD = SR.rise();
        this.h_SunSetStr = this.makeHMString(this.caldat(3, this.currentSunSetJD + this.locOffset / 24.0) + this.caldat(5, this.currentSunSetJD + this.locOffset / 24.0) / 60.0);
        g.drawString("Set " + this.h_SunSetStr + " (" + Math.round(100.0 * SR.azRise()) / 100.0 + this.deg + ")", this.xUrsprung + 350, this.yUrsprung - this.H - 25);
        this.comp = new compute(this.currentJD, this.latitude, this.longitude, this.locOffset);
        this.currentSunAz = this.comp.azimuth();
        g.drawString("Az. " + Math.round(100.0 * this.currentSunAz) / 100.0 + this.deg, this.xUrsprung + 470, this.yUrsprung - this.H - 25);
        this.currentSunElev = this.comp.elev();
        g.drawString("Elev " + Math.round(100.0 * this.currentSunElev) / 100.0 + this.deg, this.xUrsprung + 545, this.yUrsprung - this.H - 25);
        final double currentSunDeclin = this.comp.declin();
        g.drawString("Decl " + Math.round(100.0 * currentSunDeclin) / 100.0 + this.deg, this.xUrsprung + 625, this.yUrsprung - this.H - 25);
        this.comp = new compute(this.currentSunRiseJD, this.latitude, this.longitude, this.locOffset);
        this.currentSunRiseDeclin = this.comp.declin();
        this.comp = new compute(this.currentSunSetJD, this.latitude, this.longitude, this.locOffset);
        this.currentSunSetDeclin = this.comp.declin();
        g.setColor(Color.blue);
        String angleMoonStr = "";
        if (this.moonAngle == 0) {
            angleMoonStr = "(UL):";
        }
        else if (this.moonAngle == 1) {
            angleMoonStr = "(1" + this.deg + "):";
        }
        else if (this.moonAngle == 2) {
            angleMoonStr = "(2" + this.deg + "):";
        }
        else if (this.moonAngle == 3) {
            angleMoonStr = "(3" + this.deg + "):";
        }
        else if (this.moonAngle == 4) {
            angleMoonStr = "(4" + this.deg + "):";
        }
        else if (this.moonAngle == 4) {
            angleMoonStr = "(5" + this.deg + "):";
        }
        g.drawString("MOON " + angleMoonStr, this.xUrsprung + 160, this.yUrsprung - this.H - 7);
        g.setColor(Color.black);
        g.drawString("Rise " + this.h_MoonRiseStr, this.xUrsprung + 230, this.yUrsprung - this.H - 7);
        g.drawString("Set " + this.h_MoonSetStr, this.xUrsprung + 350, this.yUrsprung - this.H - 7);
        theMoon tm = new theMoon(this.currentJD, this.latitude, this.longitude);
        this.currentMoonAz = tm.azim();
        g.drawString("Az. " + Math.round(100.0 * this.currentMoonAz) / 100.0 + this.deg, this.xUrsprung + 470, this.yUrsprung - this.H - 7);
        this.currentMoonElev = tm.elev();
        this.currentMoonElev -= this.moonParal(this.currentJD, this.currentMoonElev);
        this.currentMoonElev += this.refract(this.currentMoonElev) / 60.0;
        g.drawString("Elev " + Math.round(100.0 * this.currentMoonElev) / 100.0 + this.deg, this.xUrsprung + 545, this.yUrsprung - this.H - 7);
        final double currentMoonDeclin = tm.declin();
        g.drawString("Decl " + Math.round(100.0 * currentMoonDeclin) / 100.0 + this.deg, this.xUrsprung + 625, this.yUrsprung - this.H - 7);
        this.moonPhase = this.phase(this.currentJD);
        this.moonPhase1 = this.phase(this.currentJD + 0.08333333333333333);
        if (this.moonPhase1 > this.moonPhase) {
            this.moonPhaseStr = " (+)";
        }
        else {
            this.moonPhaseStr = " (-)";
        }
        g.drawString(String.valueOf(Math.round(100.0 * this.moonPhase)) + " %" + this.moonPhaseStr, this.xUrsprung + 705, this.yUrsprung - this.H - 7);
        tm = new theMoon(this.currentMoonRiseJD, this.latitude, this.longitude);
        this.currentMoonRiseDeclin = tm.declin();
        g.setColor(Color.lightGray);
        for (int i = 1; i < 18; ++i) {
            g.drawLine(this.xUrsprung, this.yUrsprung - (int)Math.round(this.faktor * i * 10.0), this.xUrsprung + this.W, this.yUrsprung - (int)Math.round(this.faktor * i * 10.0));
        }
        g.setColor(Color.black);
        g.drawLine(this.xUrsprung, this.yUrsprung - (int)Math.round(this.faktor * 90.0), this.xUrsprung + this.W, this.yUrsprung - (int)Math.round(this.faktor * 90.0));
        g.drawLine(this.xUrsprung, this.yUrsprung - (int)Math.round(this.faktor * 180.0), this.xUrsprung + this.W, this.yUrsprung - (int)Math.round(this.faktor * 180.0));
        g.setColor(Color.red);
        if (!this.drawSunDeclin) {
            if (this.sunRiseOK) {
                for (int j = 1; j < 18; ++j) {
                    if (j != 9) {
                        g.drawString(String.valueOf(j * 10), 10, this.yUrsprung - (int)Math.round(this.faktor * j * 10.0) + 3);
                    }
                }
                g.setFont(new Font("Chicago", 0, 12));
                g.drawString("N", 15, this.yUrsprung + 5);
                g.drawString("E", 15, this.yUrsprung - (int)Math.round(this.faktor * 9.0 * 10.0) + 5);
                g.drawString("S", 15, this.yUrsprung - (int)Math.round(this.faktor * 18.0 * 10.0) + 5);
                g.setFont(new Font("Courier", 0, 10));
            }
            else {
                for (int j = 1; j < 18; ++j) {
                    if (j != 9) {
                        g.drawString(String.valueOf(180 + j * 10), 10, this.yUrsprung - (int)Math.round(this.faktor * j * 10.0) + 3);
                    }
                }
                g.setFont(new Font("Chicago", 0, 12));
                g.drawString("S", 15, this.yUrsprung + 5);
                g.drawString("W", 15, this.yUrsprung - (int)Math.round(this.faktor * 9.0 * 10.0) + 5);
                g.drawString("N", 15, this.yUrsprung - (int)Math.round(this.faktor * 18.0 * 10.0) + 5);
                g.setFont(new Font("Courier", 0, 10));
            }
        }
        else {
            for (int j = -3; j <= 3; ++j) {
                g.drawString(String.valueOf(j * 10), 10, this.yUrsprung - (int)Math.round(this.faktor * 90.0) - (int)Math.round(F * j * 10.0) + 3);
            }
        }
        g.setColor(Color.blue);
        if (!this.drawMoonDeclin) {
            if (this.moonRiseOK) {
                for (int j = 1; j < 18; ++j) {
                    if (j != 9) {
                        g.drawString(String.valueOf(j * 10), this.xUrsprung + this.W + 5, this.yUrsprung - (int)Math.round(this.faktor * j * 10.0) + 3);
                    }
                }
                g.setFont(new Font("Chicago", 0, 12));
                g.drawString("N", this.xUrsprung + this.W + 10, this.yUrsprung + 3);
                g.drawString("E", this.xUrsprung + this.W + 10, this.yUrsprung - (int)Math.round(this.faktor * 9.0 * 10.0) + 5);
                g.drawString("S", this.xUrsprung + this.W + 10, this.yUrsprung - (int)Math.round(this.faktor * 18.0 * 10.0) + 5);
                g.setFont(new Font("Courier", 0, 10));
            }
            else {
                for (int j = 1; j < 18; ++j) {
                    if (j != 9) {
                        g.drawString(String.valueOf(180 + j * 10), this.xUrsprung + this.W + 5, this.yUrsprung - (int)Math.round(this.faktor * j * 10.0) + 3);
                    }
                }
                g.setFont(new Font("Chicago", 0, 12));
                g.drawString("S", this.xUrsprung + this.W + 10, this.yUrsprung + 5);
                g.drawString("W", this.xUrsprung + this.W + 10, this.yUrsprung - (int)Math.round(this.faktor * 9.0 * 10.0) + 5);
                g.drawString("N", this.xUrsprung + this.W + 10, this.yUrsprung - (int)Math.round(this.faktor * 18.0 * 10.0) + 5);
                g.setFont(new Font("Courier", 0, 10));
            }
        }
        else {
            for (int j = -3; j <= 3; ++j) {
                g.drawString(String.valueOf(j * 10), this.xUrsprung + this.W + 5, this.yUrsprung - (int)Math.round(this.faktor * 90.0) - (int)Math.round(F * j * 10.0) + 3);
            }
        }
        if (this.drawSunOK || this.drawSunDeclin) {
            for (int k = 0; k <= 365; ++k) {
                if (this.sunRiseOK) {
                    SR = new SunRiseSet(1, jd1 + k, this.latitude, this.longitude, this.sunAngle);
                }
                else {
                    SR = new SunRiseSet(2, jd1 + k, this.latitude, this.longitude, this.sunAngle);
                }
                this.sunRiseSetJD[k] = SR.rise();
                double az = SR.azRise();
                if (!this.sunRiseOK) {
                    az -= 180.0;
                }
                this.sunRiseSetAz[k] = az;
                if (this.drawSunDeclin) {
                    this.comp = new compute(jd1 + k, this.latitude, this.longitude, this.locOffset);
                    this.sunDeclinArray[k] = this.comp.declin();
                }
            }
        }
        g.setColor(Color.red);
        if (this.drawSunDeclin) {
            for (int j = 1; j < 365; ++j) {
                final int y = this.yUrsprung - this.H / 2 - (int)Math.round(F * this.sunDeclinArray[j - 1]);
                final int y2 = this.yUrsprung - this.H / 2 - (int)Math.round(F * this.sunDeclinArray[j]);
                g.drawLine(this.xUrsprung + 2 * j, y, this.xUrsprung + 2 * j + 2, y2);
            }
        }
        if (this.drawSunOK) {
            g.setColor(Color.red);
            for (int j = 0; j < 365; ++j) {
                final int y = this.yUrsprung - (int)Math.round(this.faktor * this.sunRiseSetAz[j]);
                final int y2 = this.yUrsprung - (int)Math.round(this.faktor * this.sunRiseSetAz[j + 1]);
                g.drawLine(this.xUrsprung + (int)Math.round(2.0 * (this.sunRiseSetJD[j] - jd1)), y, this.xUrsprung + (int)Math.round(2.0 * (this.sunRiseSetJD[j + 1] - jd1)), y2);
            }
        }
        int x = this.xUrsprung;
        for (int m = 0; m < 12; ++m) {
            for (int d = 1; d <= this.daysInMonth(m, this.year); ++d) {
                if (d == 1) {
                    g.setColor(Color.gray);
                    g.drawLine(x, this.yUrsprung, x, this.yUrsprung - this.H);
                    g.setColor(Color.black);
                    g.drawString(this.monthArray[m], x + 20, this.yUrsprung + 15);
                }
                if (d == 10 || d == 20) {
                    g.setColor(Color.lightGray);
                    g.drawLine(x, this.yUrsprung, x, this.yUrsprung - this.H);
                }
                if (d == this.date && m == this.month) {
                    g.setColor(Color.red);
                    g.drawLine(x, this.yUrsprung, x, this.yUrsprung - this.H);
                }
                x += 2;
            }
        }
        g.setColor(Color.black);
        g.drawLine(x + 2, this.yUrsprung, x + 2, this.yUrsprung - this.H);
        if (this.drawMoonOK && this.moonRiseOK) {
            for (int l = 0; l < 365; ++l) {
                if (this.fullMoon) {
                    for (int n = 0; n <= 14; ++n) {
                        if (Math.round(this.fullMoonJD[n]) == Math.round(this.moonRiseJD[l])) {
                            g.setColor(Color.black);
                            final int xx = this.xUrsprung + (int)Math.round(2.0 * (this.moonRiseJD[l] - jd1));
                            final int y = this.yUrsprung - (int)Math.round(this.faktor * this.moonRiseAz[l]);
                            g.drawOval(xx - 3, y - 3, 6, 6);
                            break;
                        }
                    }
                }
                if (this.moonRiseJD[l] - jd1 <= 365.0) {
                    if (this.caldat(1, this.moonRiseJD[l + 1]) - this.caldat(1, this.moonRiseJD[l]) > 1 || (this.caldat(2, this.moonRiseJD[l + 1]) > this.caldat(2, this.moonRiseJD[l + 1]) && this.caldat(1, this.moonRiseJD[l + 1]) > 1)) {
                        g.setColor(Color.red);
                    }
                    else {
                        g.setColor(Color.blue);
                    }
                    final int xx = this.xUrsprung + (int)Math.round(2.0 * (this.moonRiseJD[l] - jd1));
                    final int xx2 = this.xUrsprung + (int)Math.round(2.0 * (this.moonRiseJD[l + 1] - jd1));
                    final int y = this.yUrsprung - (int)Math.round(this.faktor * this.moonRiseAz[l]);
                    final int y2 = this.yUrsprung - (int)Math.round(this.faktor * this.moonRiseAz[l + 1]);
                    g.drawLine(xx, y, xx2, y2);
                }
            }
            final double da = 0.0;
            if (!this.sunEclipseOK && !this.moonEclipseOK) {
                int xx = this.xUrsprung + (int)Math.round(2.0 * (this.jdRiseMax - jd1));
                int y = this.yUrsprung - (int)Math.round(this.faktor * (this.maxRiseAz - da));
                g.drawOval(xx - 3, y - 3, 6, 6);
                this.str = String.valueOf(this.monthArray[this.caldat(2, this.jdRiseMax)]) + "  " + this.caldat(1, this.jdRiseMax);
                g.drawString("Max " + Math.round(10.0 * this.maxRiseAz) / 10.0 + this.deg, xx + 5, y - 5);
                g.drawString(this.str, xx + 5, y - 20);
                xx = this.xUrsprung + (int)Math.round(2.0 * (this.jdRiseMin - jd1));
                y = this.yUrsprung - (int)Math.round(this.faktor * (this.minRiseAz - da));
                g.drawOval(xx - 3, y - 3, 6, 6);
                this.str = String.valueOf(this.monthArray[this.caldat(2, this.jdRiseMin)]) + "  " + this.caldat(1, this.jdRiseMin);
                g.drawString("Min " + Math.round(10.0 * this.minRiseAz) / 10.0 + this.deg, xx + 5, y + 10);
                g.drawString(this.str, xx + 5, y + 25);
            }
        }
        if (this.drawMoonOK && !this.moonRiseOK) {
            for (int l = 0; l < 365; ++l) {
                if (this.fullMoon) {
                    for (int n = 0; n <= 14; ++n) {
                        if (Math.round(this.fullMoonJD[n]) == Math.round(this.moonSetJD[l])) {
                            g.setColor(Color.black);
                            final int xx = this.xUrsprung + (int)Math.round(2.0 * (this.moonSetJD[l] - jd1));
                            final int y = this.yUrsprung - (int)Math.round(this.faktor * (this.moonSetAz[l] - 180.0));
                            g.drawOval(xx - 3, y - 3, 6, 6);
                            break;
                        }
                    }
                }
                if (this.moonSetJD[l] - jd1 <= 365.0) {
                    if (this.caldat(1, this.moonSetJD[l + 1]) - this.caldat(1, this.moonSetJD[l]) > 1 || (this.caldat(2, this.moonSetJD[l + 1]) > this.caldat(2, this.moonSetJD[l + 1]) && this.caldat(1, this.moonSetJD[l + 1]) > 1)) {
                        g.setColor(Color.red);
                    }
                    else {
                        g.setColor(Color.blue);
                    }
                    final int xx = this.xUrsprung + (int)Math.round(2.0 * (this.moonSetJD[l] - jd1));
                    final int xx2 = this.xUrsprung + (int)Math.round(2.0 * (this.moonSetJD[l + 1] - jd1));
                    final int y = this.yUrsprung - (int)Math.round(this.faktor * (this.moonSetAz[l] - 180.0));
                    final int y2 = this.yUrsprung - (int)Math.round(this.faktor * (this.moonSetAz[l + 1] - 180.0));
                    g.drawLine(xx, y, xx2, y2);
                }
            }
            final double da = 180.0;
            int xx = this.xUrsprung + (int)Math.round(2.0 * (this.jdSetMax - jd1));
            int y = this.yUrsprung - (int)Math.round(this.faktor * (this.maxSetAz - da));
            g.drawOval(xx - 3, y - 3, 6, 6);
            this.str = String.valueOf(this.monthArray[this.caldat(2, this.jdSetMax)]) + "  " + this.caldat(1, this.jdSetMax);
            g.drawString("Max " + Math.round(10.0 * this.maxSetAz) / 10.0 + this.deg, xx + 5, y - 5);
            g.drawString(this.str, xx + 5, y - 20);
            xx = this.xUrsprung + (int)Math.round(2.0 * (this.jdSetMin - jd1));
            y = this.yUrsprung - (int)Math.round(this.faktor * (this.minSetAz - da));
            g.drawOval(xx - 3, y - 3, 6, 6);
            this.str = String.valueOf(this.monthArray[this.caldat(2, this.jdSetMin)]) + "  " + this.caldat(1, this.jdSetMin);
            g.drawString("Min " + Math.round(10.0 * this.minSetAz) / 10.0 + this.deg, xx + 5, y + 10);
            g.drawString(this.str, xx + 5, y + 25);
        }
        if (this.drawMoonDeclin) {
            maxMoonDeclin = -50.0;
            minMoonDeclin = 50.0;
            double jdMax = 0.0;
            double jdMin = 0.0;
            for (int l = 0; l <= 365; ++l) {
                tm = new theMoon(jd1 + l, this.latitude, this.longitude);
                this.moonDeclinArray[l] = tm.declin();
                if (this.moonDeclinArray[l] > maxMoonDeclin) {
                    maxMoonDeclin = this.moonDeclinArray[l];
                    jdMax = jd1 + l;
                }
                if (this.moonDeclinArray[l] < minMoonDeclin) {
                    minMoonDeclin = this.moonDeclinArray[l];
                    jdMin = jd1 + l;
                }
            }
            String str1 = "Max " + Math.round(100.0 * maxMoonDeclin) / 100.0 + this.deg;
            String str2 = "Min " + Math.round(100.0 * minMoonDeclin) / 100.0 + this.deg;
            g.drawString(str1, this.xUrsprung + (int)Math.round(2.0 * (jdMax - jd1)) - 30, this.yUrsprung - (int)Math.round(this.faktor * 90.0) - (int)Math.round(F * maxMoonDeclin) - 30);
            g.drawString(str2, this.xUrsprung + (int)Math.round(2.0 * (jdMin - jd1)) - 30, this.yUrsprung - (int)Math.round(this.faktor * 90.0) - (int)Math.round(F * minMoonDeclin) + 30);
            str1 = String.valueOf(this.monthArray[this.caldat(2, jdMax)]) + "/" + this.caldat(1, jdMax);
            str2 = String.valueOf(this.monthArray[this.caldat(2, jdMin)]) + "/" + this.caldat(1, jdMin);
            g.drawString(str1, this.xUrsprung + (int)Math.round(2.0 * (jdMax - jd1)) - 30, this.yUrsprung - (int)Math.round(this.faktor * 90.0) - (int)Math.round(F * maxMoonDeclin) - 20);
            g.drawString(str2, this.xUrsprung + (int)Math.round(2.0 * (jdMin - jd1)) - 30, this.yUrsprung - (int)Math.round(this.faktor * 90.0) - (int)Math.round(F * minMoonDeclin) + 40);
            g.setColor(Color.red);
            g.drawOval(this.xUrsprung + (int)Math.round(2.0 * (jdMax - jd1)) - 2, this.yUrsprung - (int)Math.round(this.faktor * 90.0) - (int)Math.round(F * maxMoonDeclin) - 2, 4, 4);
            g.drawOval(this.xUrsprung + (int)Math.round(2.0 * (jdMin - jd1)) - 2, this.yUrsprung - (int)Math.round(this.faktor * 90.0) - (int)Math.round(F * minMoonDeclin) - 2, 4, 4);
            g.setColor(Color.blue);
            for (int i2 = 0; i2 < 365; ++i2) {
                final int y = this.yUrsprung - this.H / 2 - (int)Math.round(F * this.moonDeclinArray[i2]);
                final int y2 = this.yUrsprung - this.H / 2 - (int)Math.round(F * this.moonDeclinArray[i2 + 1]);
                g.drawLine(this.xUrsprung + 2 * i2, y, this.xUrsprung + 2 * i2 + 2, y2);
            }
        }
        if (this.sunEclipseOK && !this.moonEclipseOK) {
            g.setColor(Color.magenta);
            for (int i3 = 0; i3 < 365; ++i3) {
                for (int n2 = 0; n2 < 365; ++n2) {
                    if (Math.abs(this.sunRiseSetJD[i3] - this.moonRiseJD[n2]) < 0.015 && Math.abs(this.sunRiseSetAz[i3] - this.moonRiseAz[n2]) < 2.0) {
                        final int y = this.yUrsprung - (int)Math.round(this.faktor * this.sunRiseSetAz[i3]);
                        int dy;
                        if (y < this.yUrsprung - (int)(90.0 * this.faktor)) {
                            dy = -10;
                        }
                        else {
                            dy = 15;
                        }
                        x = this.xUrsprung + (int)Math.round(2.0 * (this.sunRiseSetJD[i3] - this.JD(1, 1, this.year + 1900, 0.0)));
                        g.drawOval(x - 4, y - 4, 8, 8);
                        g.drawString("Sun Ecl ", x + 5, y + dy);
                        g.drawString(String.valueOf(this.monthArray[this.caldat(2, this.sunRiseSetJD[i3])]) + " " + this.caldat(1, this.sunRiseSetJD[i3]), x + 5, y + 2 * dy);
                    }
                }
            }
        }
        if (this.moonEclipseOK && !this.sunEclipseOK) {
            g.setColor(Color.magenta);
            for (int i4 = 0; i4 < 365; ++i4) {
                SR = new SunRiseSet(2, jd1 + i4, this.latitude, this.longitude, this.sunAngle);
                this.sunRiseSetJD[i4] = SR.rise();
                double az = SR.azRise();
                if (!this.sunRiseOK) {
                    az -= 180.0;
                }
                this.sunRiseSetAz[i4] = az;
                for (int n3 = 0; n3 < 365; ++n3) {
                    if (Math.abs(this.sunRiseSetJD[i4] - this.moonRiseJD[n3]) < 0.015 && Math.abs(this.sunRiseSetAz[i4] - this.moonRiseAz[n3]) < 2.0) {
                        final int y = this.yUrsprung - (int)Math.round(this.faktor * this.sunRiseSetAz[i4]);
                        int dy2;
                        if (y < this.yUrsprung) {
                            dy2 = -10;
                        }
                        else {
                            dy2 = 10;
                        }
                        x = this.xUrsprung + (int)Math.round(2.0 * (this.sunRiseSetJD[i4] - this.JD(1, 1, this.year + 1900, 0.0)));
                        g.drawOval(x - 4, y - 4, 8, 8);
                        g.drawString("Moon Ecl " + this.monthArray[this.caldat(2, this.sunRiseSetJD[i4])] + " " + this.caldat(1, this.sunRiseSetJD[i4]), x + 10, y + dy2);
                    }
                }
            }
        }
        g.setColor(Color.blue);
        final String str3 = "";
        final String str4 = "";
        if (this.xMouse != 0) {
            final Font f = g.getFont();
            g.setFont(new Font("Courier", 0, 10));
            g.setColor(Color.red);
            g.drawLine(this.xMouse, this.yUrsprung - 3, this.xMouse, this.yUrsprung + 3);
            g.drawLine(this.xMouse, this.yMouse - 3, this.xMouse, this.yMouse + 3);
            g.drawLine(this.xUrsprung + xM - 3, this.yMouse, this.xUrsprung + xM + 3, this.yMouse);
            g.drawString(this.datum(xM), this.xMouse - 10, this.yUrsprung - 30);
            g.setFont(f);
            g.setColor(Color.black);
        }
        g.setColor(Color.black);
        g.drawLine(this.xUrsprung, this.yUrsprung, this.xUrsprung + this.W, this.yUrsprung);
        g.drawLine(this.xUrsprung, this.yUrsprung, this.xUrsprung, this.yUrsprung - this.H);
        g.drawString(String.valueOf(this.versStr) + "  © 2003-2011 J. Giesen\t-   www.GeoAstro.de", this.xUrsprung, this.size().height - 7);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        if (this.demo) {
            g.setFont(new Font("Chicago", 0, 128));
            g.setColor(Color.red);
            g.drawString("D E M O", 170, 300);
        }
        if (this.online) {
            g.setFont(new Font("Chicago", 0, 36));
            g.setColor(Color.red);
            g.drawString("o n l i n e", 350, 530);
        }
    }
    
    public boolean action(final Event event, final Object eventobject) {
        final double oldLat = this.latitude;
        final double oldLong = this.longitude;
        if (event.target == this.moonBox) {
            this.startCursor();
            this.fullMoon ^= true;
            if (this.fullMoon) {
                this.dataFullMoon(false);
            }
            this.repaint();
            this.stopCursor();
            return true;
        }
        if (event.target == this.writeChoice) {
            boolean DEM = false;
            if (this.demo || this.online) {
                DEM = true;
            }
            final String locStr = String.valueOf(this.locString) + ",  " + this.latStr + this.nsStr + ", " + this.longStr + this.ewStr;
            final String datumStr = this.year + 1900 + " " + this.monthArray[this.month] + " " + this.date;
            if (this.writeChoice.getSelectedItem().equals("Full Moon Dates")) {
                this.dataFullMoon(true);
                return true;
            }
            if (this.writeChoice.getSelectedItem().equals("Full Moon Azimuth")) {
                this.startCursor();
                this.dataFullMoon(false);
                this.fmStr[0] = String.valueOf(this.versStr) + "  www.GeoAstro.de" + "\n" + this.latStr + "  " + this.longStr + "\n" + "Altitude " + this.moonAngle + this.deg + "\n";
                if (this.locOffset >= 0) {
                    this.str = "+ ";
                }
                else {
                    this.str = "- ";
                }
                this.str = String.valueOf(this.str) + Math.abs(this.locOffset);
                if (this.moonRiseOK) {
                    final String[] fmStr = this.fmStr;
                    final int n2 = 0;
                    fmStr[n2] = String.valueOf(fmStr[n2]) + "Moon Rise\nUT " + this.str + " h" + "\n" + "\n";
                    for (int i = 0; i < 365; ++i) {
                        for (int k = 0; k <= 14; ++k) {
                            if (Math.round(this.fullMoonJD[k]) == Math.round(this.moonRiseJD[i])) {
                                this.fmStr[k] = this.year + 1900 + "  " + this.monthArray[this.calDat(2, this.moonRiseJD[i] + this.locOffset / 24.0)] + "  " + this.calDat(1, this.moonRiseJD[i] + this.locOffset / 24.0) + "  " + this.makeTimeString("", this.calDat(4, this.moonRiseJD[i]) + this.calDat(5, this.moonRiseJD[i]) / 60.0 + this.locOffset) + "    " + Math.round(10.0 * this.moonRiseAz[i]) / 10.0 + this.deg + "\n";
                                if (k == 0) {
                                    final String[] fmStr2 = this.fmStr;
                                    final int n3 = k;
                                    fmStr2[n3] = String.valueOf(fmStr2[n3]) + this.fmStr[k];
                                }
                            }
                        }
                    }
                }
                else {
                    final String[] fmStr3 = this.fmStr;
                    final int n4 = 0;
                    fmStr3[n4] = String.valueOf(fmStr3[n4]) + "Moon Rise\nUT " + this.str + " h" + "\n" + "\n";
                    for (int i = 0; i < 365; ++i) {
                        for (int k = 0; k <= 14; ++k) {
                            if (Math.round(this.fullMoonJD[k]) == Math.round(this.moonSetJD[i])) {
                                this.fmStr[k] = this.year + 1900 + "  " + this.monthArray[this.calDat(2, this.moonSetJD[i] + this.locOffset / 24.0)] + "  " + this.calDat(1, this.moonSetJD[i] + this.locOffset / 24.0) + "  " + this.makeTimeString("", this.calDat(4, this.moonSetJD[i]) + this.calDat(5, this.moonSetJD[i]) / 60.0 + this.locOffset) + "    " + Math.round(10.0 * this.moonSetAz[i]) / 10.0 + this.deg + "\n";
                                if (k == 0) {
                                    final String[] fmStr4 = this.fmStr;
                                    final int n5 = k;
                                    fmStr4[n5] = String.valueOf(fmStr4[n5]) + this.fmStr[k];
                                }
                            }
                        }
                    }
                }
                final scrollFrame sf = new scrollFrame("Full Moon Azimuth " + (this.year + 1900), 14, this.fmStr, this.online);
                sf.resize(350, 290);
                sf.show();
                this.stopCursor();
                return true;
            }
            if (this.writeChoice.getSelectedItem().equals("Sun Rise")) {
                final Frame srf = new RiseFrame(this.locOffset, 1, 1, locStr, datumStr, this.currentSunRiseJD, this.latitude, this.longitude, this.currentSunRiseDeclin, this.sunAngle, DEM);
                srf.resize(780, 570);
                srf.show();
                this.writeChoice.select(0);
            }
            if (this.writeChoice.getSelectedItem().equals("Sun Set")) {
                final Frame srf = new RiseFrame(this.locOffset, 2, 1, locStr, datumStr, this.currentSunSetJD, this.latitude, this.longitude, this.currentSunSetDeclin, this.sunAngle, DEM);
                srf.resize(780, 570);
                srf.show();
                this.writeChoice.select(0);
            }
            if (this.writeChoice.getSelectedItem().equals("Moon Rise")) {
                final Frame srf = new RiseFrame(this.locOffset, 1, 2, locStr, datumStr, this.currentMoonRiseJD, this.latitude, this.longitude, this.currentMoonRiseDeclin, this.moonAngle, DEM);
                srf.resize(780, 570);
                srf.show();
                this.writeChoice.select(0);
            }
            if (this.writeChoice.getSelectedItem().equals("Moon Set")) {
                final Frame srf = new RiseFrame(this.locOffset, 2, 2, locStr, datumStr, this.currentMoonSetJD, this.latitude, this.longitude, this.currentMoonRiseDeclin, this.moonAngle, DEM);
                srf.resize(780, 570);
                srf.show();
                this.writeChoice.select(0);
            }
            if (this.writeChoice.getSelectedItem().equals("Table Sun")) {
                Frame df;
                if (this.dem) {
                    this.tableSun(105);
                    df = new dataFrame(String.valueOf(this.locString) + ",  " + this.latStr + this.nsStr + ", " + this.longStr + this.ewStr + ",  " + this.timeString + ", Elevation = " + this.sunAngle + this.deg, "The Sun 2005 DEMO", this.versStr, this.Data, this.nData, DEM);
                }
                else {
                    this.tableSun(this.year);
                    df = new dataFrame(String.valueOf(this.locString) + ",  " + this.latStr + this.nsStr + ", " + this.longStr + this.ewStr + ",  " + this.timeString + ", Elevation = " + this.sunAngle + this.deg, "The Sun " + (this.year + 1900), this.versStr, this.Data, this.nData, DEM);
                }
                df.resize(385, 425);
                df.show();
                this.writeChoice.select(0);
            }
            if (this.writeChoice.getSelectedItem().equals("Table Moon")) {
                this.tableMoon(this.year);
                final Frame df = new dataFrame(String.valueOf(this.locString) + ",  " + this.latStr + this.nsStr + ", " + this.longStr + this.ewStr + ",  " + this.timeString + ", Elevation = " + this.moonAngle + this.deg, "The Moon " + (this.year + 1900), this.versStr, this.Data, this.nData, DEM);
                df.resize(385, 425);
                df.show();
                this.writeChoice.select(0);
            }
            if (this.writeChoice.getSelectedItem().equals("Moon Histogram")) {
                int rs;
                if (this.moonRiseOK) {
                    this.doMoonRise(this.year);
                    rs = 1;
                }
                else {
                    this.doMoonSet(this.year);
                    rs = 2;
                }
                final String text = String.valueOf(this.locString) + ",  " + this.latStr + this.nsStr + ", " + this.longStr + this.ewStr;
                final Frame mf = new histogram(2, "Moon Azimuth " + (this.year + 1900), text, this.fr, this.latitude, this.moonAngle, rs, this.dem);
                mf.resize(790, 460);
                mf.show();
                this.writeChoice.select(0);
            }
            if (this.writeChoice.getSelectedItem().equals("Sun Histogram")) {
                int rs;
                if (this.sunRiseOK) {
                    rs = 1;
                }
                else {
                    rs = 2;
                }
                final String text = String.valueOf(this.locString) + ",  " + this.latStr + this.nsStr + ", " + this.longStr + this.ewStr;
                for (int j = 0; j < 180; ++j) {
                    final int[] frSun = this.frSun;
                    final int n6 = j;
                    frSun[n6] += 0;
                }
                for (int l = 0; l <= 365; ++l) {
                    for (int a = 0; a <= 179; ++a) {
                        if (this.sunRiseSetAz[l] > a && this.sunRiseSetAz[l] <= a + 1) {
                            final int[] frSun2 = this.frSun;
                            final int n7 = a;
                            ++frSun2[n7];
                        }
                    }
                }
                final Frame mf = new histogram(1, "Sun Azimuth " + (this.year + 1900), text, this.frSun, this.latitude, this.moonAngle, rs, this.dem);
                mf.resize(790, 460);
                mf.show();
                this.writeChoice.select(0);
            }
        }
        if (event.target == this.sunChoice) {
            this.drawSunDeclin = false;
            if (this.sunChoice.getSelectedItem().equals("Rise U.L.")) {
                this.sunAngle = 0;
                this.sunRiseOK = true;
                this.drawSunOK = true;
            }
            if (this.sunChoice.getSelectedItem().equals("Rise 1" + this.deg)) {
                this.sunAngle = 1;
                this.sunRiseOK = true;
                this.drawSunOK = true;
            }
            if (this.sunChoice.getSelectedItem().equals("Rise 2" + this.deg)) {
                this.sunAngle = 2;
                this.sunRiseOK = true;
                this.drawSunOK = true;
            }
            if (this.sunChoice.getSelectedItem().equals("Rise 3" + this.deg)) {
                this.sunAngle = 3;
                this.sunRiseOK = true;
                this.drawSunOK = true;
            }
            if (this.sunChoice.getSelectedItem().equals("Rise 4" + this.deg)) {
                this.sunAngle = 4;
                this.sunRiseOK = true;
                this.drawSunOK = true;
            }
            if (this.sunChoice.getSelectedItem().equals("Rise 5" + this.deg)) {
                this.sunAngle = 5;
                this.sunRiseOK = true;
                this.drawSunOK = true;
            }
            if (this.sunChoice.getSelectedItem().equals("Set U.L.")) {
                this.sunAngle = 0;
                this.sunRiseOK = false;
                this.drawSunOK = true;
            }
            if (this.sunChoice.getSelectedItem().equals("Set 1" + this.deg)) {
                this.sunAngle = 1;
                this.sunRiseOK = false;
                this.drawSunOK = true;
            }
            if (this.sunChoice.getSelectedItem().equals("Set 2" + this.deg)) {
                this.sunAngle = 2;
                this.sunRiseOK = false;
                this.drawSunOK = true;
            }
            if (this.sunChoice.getSelectedItem().equals("Set 3" + this.deg)) {
                this.sunAngle = 3;
                this.sunRiseOK = false;
                this.drawSunOK = true;
            }
            if (this.sunChoice.getSelectedItem().equals("Set 4" + this.deg)) {
                this.sunAngle = 4;
                this.sunRiseOK = false;
                this.drawSunOK = true;
            }
            if (this.sunChoice.getSelectedItem().equals("Set 5" + this.deg)) {
                this.sunAngle = 5;
                this.sunRiseOK = false;
                this.drawSunOK = true;
            }
            if (this.sunChoice.getSelectedItem().equals("Declination")) {
                this.drawSunDeclin = true;
                this.drawSunOK = false;
            }
            if (this.sunChoice.getSelectedItem().equals("Don't draw")) {
                this.drawSunOK = false;
            }
            if (this.sunChoice.getSelectedItem().equals("Sun Ecl On")) {
                this.sunEclipseOK = true;
                this.moonEclipseOK = false;
                this.sunChoice.remove(14);
                this.sunChoice.add("Sun Ecl Off");
                this.sunChoice.select(this.sunChoiceItem);
                this.moonChoice.remove(14);
                this.moonChoice.add("Moon Ecl On");
            }
            if (this.sunChoice.getSelectedItem().equals("Sun Ecl Off")) {
                this.sunEclipseOK = false;
                this.sunChoice.remove(14);
                this.sunChoice.add("Sun Ecl On");
                this.sunChoice.select(this.sunChoiceItem);
            }
            this.repaint();
        }
        if (event.target == this.moonChoice) {
            this.drawMoonDeclin = false;
            if (this.moonChoice.getSelectedItem().equals("Rise U.L.")) {
                this.moonAngle = 0;
                this.moonRiseOK = true;
                this.drawMoonOK = true;
            }
            if (this.moonChoice.getSelectedItem().equals("Rise 1" + this.deg)) {
                this.moonAngle = 1;
                this.moonRiseOK = true;
                this.drawMoonOK = true;
            }
            if (this.moonChoice.getSelectedItem().equals("Rise 2" + this.deg)) {
                this.moonAngle = 2;
                this.moonRiseOK = true;
                this.drawMoonOK = true;
            }
            if (this.moonChoice.getSelectedItem().equals("Rise 3" + this.deg)) {
                this.moonAngle = 3;
                this.moonRiseOK = true;
                this.drawMoonOK = true;
            }
            if (this.moonChoice.getSelectedItem().equals("Rise 4" + this.deg)) {
                this.moonAngle = 4;
                this.moonRiseOK = true;
                this.drawMoonOK = true;
            }
            if (this.moonChoice.getSelectedItem().equals("Rise 5" + this.deg)) {
                this.moonAngle = 5;
                this.moonRiseOK = true;
                this.drawMoonOK = true;
            }
            if (this.moonChoice.getSelectedItem().equals("Set U.L.")) {
                this.moonAngle = 0;
                this.moonRiseOK = false;
                this.drawMoonOK = true;
            }
            if (this.moonChoice.getSelectedItem().equals("Set 1" + this.deg)) {
                this.moonAngle = 1;
                this.moonRiseOK = false;
                this.drawMoonOK = true;
            }
            if (this.moonChoice.getSelectedItem().equals("Set 2" + this.deg)) {
                this.moonAngle = 2;
                this.moonRiseOK = false;
                this.drawMoonOK = true;
            }
            if (this.moonChoice.getSelectedItem().equals("Set 3" + this.deg)) {
                this.moonAngle = 3;
                this.moonRiseOK = false;
                this.drawMoonOK = true;
            }
            if (this.moonChoice.getSelectedItem().equals("Set 4" + this.deg)) {
                this.moonAngle = 4;
                this.moonRiseOK = false;
                this.drawMoonOK = true;
            }
            if (this.moonChoice.getSelectedItem().equals("Set 5" + this.deg)) {
                this.moonAngle = 5;
                this.moonRiseOK = false;
                this.drawMoonOK = true;
            }
            if (this.moonChoice.getSelectedItem().equals("Declination")) {
                this.drawMoonDeclin = true;
                this.drawMoonOK = false;
            }
            if (this.moonChoice.getSelectedItem().equals("Don't draw")) {
                this.drawMoonOK = false;
            }
            if (this.moonChoice.getSelectedItem().equals("Moon Ecl On")) {
                this.moonEclipseOK = true;
                this.sunEclipseOK = false;
                this.moonChoice.remove(14);
                this.moonChoice.add("Moon Ecl Off");
                this.moonChoice.select(this.moonChoiceItem);
                this.sunChoice.remove(14);
                this.sunChoice.add("Sun Ecl On");
            }
            if (this.moonChoice.getSelectedItem().equals("Moon Ecl Off")) {
                this.moonEclipseOK = false;
                this.moonChoice.remove(14);
                this.moonChoice.add("Moon Ecl On");
                this.moonChoice.select(this.moonChoiceItem);
            }
            this.repaint();
        }
        if (event.target instanceof Button && event.target == this.button) {
            final Date dat = new Date();
            this.hours = dat.getHours();
            this.hoursChoice.select(this.hours);
            this.minutes = dat.getMinutes();
            this.minChoice.select(this.minutes);
            this.date = dat.getDate();
            this.dateChoice.select(this.date - 1);
            this.month = dat.getMonth();
            this.monthChoice.select(this.month);
            this.year = dat.getYear();
            this.yearChoice.select(String.valueOf(this.year + 1900));
            this.repaint();
        }
        if (event.target == this.fieldLatDeg) {
            this.str = this.fieldLatDeg.getText();
            for (int m = 0; m < this.str.length(); ++m) {
                final char c = this.str.charAt(m);
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
            try {
                final Double latDouble = Double.valueOf(this.latStr);
                this.latitude = latDouble;
                if (this.nsChoice.getSelectedItem().equals("S")) {
                    this.latitude = -Math.abs(this.latitude);
                    this.usrLat = this.latitude;
                }
                if (this.latitude == (int)this.latitude) {
                    this.str = String.valueOf(Math.abs((int)this.latitude)) + ".00";
                }
                this.fieldLatDeg.setText(this.str);
                if (this.latitude > 90.0) {
                    this.fieldLatDeg.setText(String.valueOf(oldLat));
                    this.latitude = oldLat;
                }
                else {
                    if (!this.str.equals(this.latStr) || this.str.length() == 0) {
                        this.latStr = this.str;
                        this.locString = "User Input";
                        this.locChoice.select(this.locString);
                    }
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                    this.repaint();
                }
            }
            catch (NumberFormatException ex) {
                this.latitude = oldLat;
                this.fieldLatDeg.setText(String.valueOf(oldLat));
                System.out.println("NumberFormatException on latitude");
            }
            this.fieldLatDeg.nextFocus();
            this.fieldLongDeg.selectAll();
        }
        if (event.target == this.fieldLongDeg) {
            this.str = this.fieldLongDeg.getText();
            for (int m = 0; m < this.str.length(); ++m) {
                final char c = this.str.charAt(m);
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
            try {
                final Double longDouble = Double.valueOf(this.longStr);
                this.longitude = longDouble;
                if (this.ewChoice.getSelectedItem().equals("E")) {
                    this.longitude = -Math.abs(this.longitude);
                    this.usrLong = this.longitude;
                }
                if (this.longitude == (int)this.longitude) {
                    this.str = String.valueOf(Math.abs((int)this.longitude)) + ".00";
                }
                this.fieldLongDeg.setText(this.str);
                if (this.longitude > 180.0) {
                    this.fieldLongDeg.setText(String.valueOf(oldLong));
                    this.longitude = oldLong;
                }
                else {
                    if (!this.str.equals(this.longStr) || this.str.length() == 0) {
                        this.longStr = this.str;
                        this.locString = "User Input";
                        this.locChoice.select(this.locString);
                    }
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                    this.repaint();
                }
            }
            catch (NumberFormatException ex2) {
                this.longitude = oldLong;
                this.fieldLongDeg.setText(String.valueOf(oldLong));
                System.out.println("NumberFormatException on longitude");
            }
        }
        if (event.target == this.locChoice) {
            if (this.locChoice.getSelectedItem().equals("_________")) {
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
            if (this.latitude < 0.0) {
                this.nsChoice.select("S");
            }
            else {
                this.nsChoice.select("N");
            }
            this.longStr = String.valueOf(Math.abs(this.longitude));
            this.fieldLongDeg.setText(this.longStr);
            if (this.longitude <= 0.0) {
                this.ewChoice.select("E");
            }
            else {
                this.ewChoice.select("W");
            }
            this.repaint();
        }
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
            if (this.ewChoice.getSelectedItem().equals("E")) {
                this.ewStr = "E";
                this.longitude = -this.longitude;
            }
            this.usrLat = this.latitude;
            this.usrLong = this.longitude;
            this.repaint();
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
            this.usrLat = this.latitude;
            this.usrLong = this.longitude;
            this.repaint();
        }
        if (event.target == this.timeChoice) {
            this.timeString = this.timeChoice.getSelectedItem();
            final int gmtHours = this.hours - this.locOffset;
            this.locOffset = this.loc.getTimeZone(this.timeString);
            this.hours = gmtHours + this.locOffset;
            if (this.hours >= 24) {
                this.hours -= 24;
                ++this.date;
                final int n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = n;
                    this.dateChoice.select(this.date - 1);
                }
            }
            if (this.hours < 0) {
                this.hours += 24;
                --this.date;
                if (this.date < 1) {
                    --this.month;
                    if (this.month < 0) {
                        this.month = 11;
                        --this.year;
                    }
                    this.date = this.daysInMonth(this.month, this.year);
                    this.monthChoice.select(this.month);
                }
            }
            this.dateChoice.select(this.date - 1);
            this.timeChoice.select(this.timeString);
            this.hoursStr = String.valueOf(this.hours);
            if (this.hours < 10) {
                this.hoursStr = "0" + this.hoursStr;
            }
            this.hoursChoice.select(this.hours);
            this.repaint();
        }
        if (event.target == this.hoursChoice) {
            this.hoursStr = this.hoursChoice.getSelectedItem();
            this.hours = Integer.parseInt(this.hoursStr);
            this.hoursChoice.select(this.hoursStr);
            this.repaint();
        }
        if (event.target == this.minChoice) {
            this.minStr = this.minChoice.getSelectedItem();
            this.minutes = Integer.parseInt(this.minStr);
            this.minChoice.select(this.minStr);
            this.repaint();
        }
        if (event.target == this.dateChoice) {
            this.dateStr = this.dateChoice.getSelectedItem();
            this.date = Integer.parseInt(this.dateStr);
            final int n = this.daysInMonth(this.month, this.year);
            if (this.date > n) {
                this.date = n;
                this.dateChoice.select(this.date - 1);
            }
            this.repaint();
        }
        if (event.target == this.monthChoice) {
            this.monthStr = this.monthChoice.getSelectedItem();
            this.monthChoice.select(this.monthStr);
            this.month = this.MonthInteger(this.monthStr);
            final int n = this.daysInMonth(this.month, this.year);
            if (this.date > n) {
                this.date = n;
                this.dateChoice.select(this.date - 1);
            }
            this.repaint();
        }
        if (event.target == this.yearChoice) {
            this.year = Integer.parseInt(this.yearChoice.getSelectedItem());
            this.year -= 1900;
            final int n = this.daysInMonth(this.month, this.year);
            if (this.date > n) {
                this.date = n;
                this.dateChoice.select(this.date - 1);
            }
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseDown(final Event event, final int x, final int y) {
        if (this.rect.contains(new Point(x, y))) {
            this.xMouse = x;
            this.yMouse = y;
        }
        else {
            this.xMouse = 0;
        }
        this.repaint();
        return true;
    }
    
    public boolean keyDown(final Event event, final int code) {
        int n = 0;
        if (code != 104 && code != 100 && code != 109 && code != 72 && code != 68 && code != 77 && code != 89 && code != 121 && code != 110 && code != 89 && code != 78) {
            return false;
        }
        if (code == 89 || code == 121) {
            if (code == 121) {
                ++this.year;
            }
            if (code == 89) {
                --this.year;
            }
            this.yearChoice.select(String.valueOf(this.year + 1900));
            this.repaint();
            return true;
        }
        if (code == 100 || code == 68) {
            if (code == 68) {
                --this.date;
                if (this.date == 0) {
                    --this.month;
                    if (this.month == -1) {
                        this.month = 11;
                        --this.year;
                    }
                    this.date = this.daysInMonth(this.month, this.year);
                }
            }
            else {
                ++this.date;
                n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = 1;
                    ++this.month;
                    if (this.month == 12) {
                        this.month = 0;
                        ++this.year;
                    }
                }
            }
            this.dateChoice.select(this.date - 1);
            this.monthChoice.select(this.month);
            this.yearChoice.select(String.valueOf(this.year + 1900));
            this.repaint();
            return true;
        }
        if (code == 109 || code == 77) {
            if (code == 77) {
                --this.month;
            }
            else {
                ++this.month;
            }
            if (this.month > 11) {
                this.month = 0;
                ++this.year;
            }
            if (this.month < 0) {
                this.month = 11;
                --this.year;
            }
            n = this.daysInMonth(this.month, this.year);
            if (this.date > n && this.month < 11) {
                this.date = 1;
                ++this.month;
            }
            this.yearChoice.select(String.valueOf(this.year + 1900));
            this.dateChoice.select(this.date - 1);
            this.monthChoice.select(this.month);
            this.repaint();
            return true;
        }
        if (code == 110) {
            ++this.minutes;
            if (this.minutes == 60) {
                this.minutes = 0;
                ++this.hours;
                if (this.hours == 24) {
                    this.hours = 0;
                    ++this.date;
                }
                this.hoursChoice.select(this.hours);
                if (this.date > this.daysInMonth(this.month, this.year)) {
                    this.date = 1;
                    ++this.month;
                    if (this.month == 12) {
                        this.month = 0;
                        ++this.year;
                        this.yearChoice.select(String.valueOf(this.year + 1900));
                    }
                    this.monthChoice.select(this.month);
                }
                this.dateChoice.select(this.date - 1);
            }
            this.minChoice.select(this.minutes);
            this.repaint();
            return true;
        }
        if (code == 78) {
            --this.minutes;
            if (this.minutes == -1) {
                this.minutes = 59;
                --this.hours;
                if (this.hours == -1) {
                    this.hours = 23;
                    --this.date;
                }
                this.hoursChoice.select(this.hours);
                if (this.date == 0) {
                    --this.month;
                    if (this.month < 0) {
                        this.month = 11;
                        --this.year;
                        this.yearChoice.select(String.valueOf(this.year + 1900));
                    }
                    this.date = this.daysInMonth(this.month, this.year);
                    this.monthChoice.select(this.month);
                }
                this.dateChoice.select(this.date - 1);
            }
            this.repaint();
            return true;
        }
        return true;
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
        double hour = 24.0 * this.frac(DAY);
        int min = (int)Math.round(this.frac(hour) * 60.0);
        if (min == 60) {
            min = 0;
            ++hour;
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
        if (what == 4) {
            return (int)hour;
        }
        if (what == 5) {
            return (int)Math.round(60.0 * this.frac(hour));
        }
        if (what == 6) {
            return (int)Math.round(hour);
        }
        return 0;
    }
    
    public String dayString(final double jd) {
        final String[] dayArray = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
        long num = (long)(jd + 0.5);
        num -= num / 7L * 7L;
        return dayArray[(int)num];
    }
    
    public String HM(double x) {
        String str = "";
        if (x < 0.0) {
            x = Math.abs(x);
        }
        final int HOUR = (int)x;
        final int MIN = (int)Math.round(this.frac(x) * 60.0);
        if (HOUR < 10) {
            str = "0";
        }
        str = String.valueOf(str) + HOUR + " h ";
        if (MIN < 10) {
            str = String.valueOf(str) + "0";
        }
        return String.valueOf(str) + MIN + " m ";
    }
    
    public String trim(final double x, final int t) {
        String str = "";
        switch (t) {
            case 5: {
                str = String.valueOf(str) + Math.round(100000.0 * x) / 100000.0;
                break;
            }
            case 4: {
                str = String.valueOf(str) + Math.round(10000.0 * x) / 10000.0;
                break;
            }
            case 3: {
                str = String.valueOf(str) + Math.round(1000.0 * x) / 1000.0;
                break;
            }
            case 2: {
                str = String.valueOf(str) + Math.round(100.0 * x) / 100.0;
                break;
            }
            case 1: {
                str = String.valueOf(str) + Math.round(10.0 * x) / 10.0;
                break;
            }
        }
        for (int n = str.lastIndexOf("."); n > str.length() - (t + 1); str = String.valueOf(str) + "0") {}
        return str;
    }
    
    public void startCursor() {
        this.showStatus("Calculating...");
        this.setCursor(new Cursor(3));
    }
    
    public void stopCursor() {
        this.showStatus("");
        this.setCursor(new Cursor(0));
    }
    
    public String makeTimeString(final String whatStr, double time) {
        String str = "?";
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
        return String.valueOf(whatStr) + " " + str;
    }
    
    public void dataFullMoon(final boolean showFrame) {
        this.startCursor();
        final String[] dataStr = new String[300];
        final double[] jD = new double[17];
        final double curJD = this.JD(this.date, this.month + 1, this.year + 1900, this.UT);
        int minFM = 0;
        int hourFM = 0;
        final double[] delta = new double[122];
        String strD = "";
        String strM = "";
        double jdStart = this.JD(1, 12, this.year + 1900 - 1, 0.0);
        for (int i = 0; i < 41; ++i) {
            final SunMoon sunmoon = new SunMoon(jdStart + i, 6);
            final double moonL = sunmoon.heliocentricLambda();
            final double sunL = sunmoon.lambda();
            double SunMoonExcess = 360.0 - (sunL - moonL);
            if (SunMoonExcess > 360.0) {
                SunMoonExcess -= 360.0;
            }
            delta[i] = SunMoonExcess;
        }
        int DAY = 0;
        for (int j = 0; j < 40; ++j) {
            if (delta[j] < 180.0 && delta[j + 1] > 180.0) {
                DAY = j;
                break;
            }
        }
        jdStart = jdStart + DAY - 1.0;
        final int nn = 0;
        for (int n = 0; n <= 14; ++n) {
            final double jd = jdStart + 29.53058867 * n;
            for (int k = 0; k <= 72; ++k) {
                final SunMoon sunmoon = new SunMoon(jd + k / 24.0, 6);
                final double moonL = sunmoon.heliocentricLambda();
                final double sunL = sunmoon.lambda();
                double SunMoonExcess = 360.0 - (sunL - moonL);
                if (SunMoonExcess > 360.0) {
                    SunMoonExcess -= 360.0;
                }
                delta[k] = SunMoonExcess;
            }
            hourFM = 0;
            for (int l = 0; l < 72; ++l) {
                if (delta[l] < 180.0 && delta[l + 1] > 180.0) {
                    hourFM = l - 1;
                    break;
                }
            }
            for (int m = 0; m <= 120; ++m) {
                final SunMoon sunmoon = new SunMoon(jd + hourFM / 24.0 + m / 1440.0, 6);
                final double moonL = sunmoon.heliocentricLambda();
                final double sunL = sunmoon.lambda();
                double SunMoonExcess = 360.0 - (sunL - moonL);
                if (SunMoonExcess > 360.0) {
                    SunMoonExcess -= 360.0;
                }
                delta[m] = SunMoonExcess;
            }
            minFM = 0;
            for (int i2 = 0; i2 < 120; ++i2) {
                if (delta[i2] < 180.0 && delta[i2 + 1] > 180.0) {
                    minFM = i2;
                    break;
                }
            }
            if (Math.abs(180.0 - delta[minFM]) > Math.abs(180.0 - delta[minFM + 1])) {
                ++minFM;
            }
            final double jdFM = jd + hourFM / 24.0 + minFM / 1440.0;
            strM = this.monthArray[this.calDat(2, jdFM)];
            final int D = this.calDat(1, jdFM);
            if (D < 10) {
                strD = "0" + D;
            }
            else {
                strD = String.valueOf(D);
            }
            jD[n] = jdFM;
            final String strH = this.makeTimeString("", this.calDat(4, jdFM) + this.calDat(5, jdFM) / 60.0);
            if (showFrame) {
                dataStr[n] = String.valueOf(this.calDat(3, jdFM)) + "  " + this.dayString(jdFM) + "  " + strM + " " + strD + " " + strH + "   JD " + this.trim(jD[n], 4);
            }
            this.fullMoonJD[n] = jD[n];
        }
        if (showFrame) {
            if (this.locOffset >= 0) {
                this.str = "+ ";
            }
            else {
                this.str = "- ";
            }
            dataStr[0] = String.valueOf(this.versStr) + "  www.GeoAstro.de" + "\n" + "\n" + "UT " + this.str + Math.abs(this.locOffset) + " h" + "\n" + "\n" + dataStr[0] + "\n";
            for (int n2 = 1; n2 <= 14; ++n2) {
                final String[] array = dataStr;
                final int n3 = n2;
                array[n3] = String.valueOf(array[n3]) + "\n";
            }
            final scrollFrame sf = new scrollFrame("Full Moon " + (this.year + 1900), 14, dataStr, this.online);
            sf.resize(350, 260);
            sf.show();
        }
        this.stopCursor();
    }
    
    public SunMoonHorizon0982() {
        this.deg = '°';
        this.versStr = "SunMoon RiseSet Azimuth 0.982  -";
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.sunDeclinArray = new double[370];
        this.moonDeclinArray = new double[370];
        this.moonRiseAz = new double[370];
        this.moonSetAz = new double[370];
        this.moonRiseJD = new double[370];
        this.moonSetJD = new double[370];
        this.sunRiseSetAz = new double[370];
        this.sunRiseSetJD = new double[370];
        this.online = false;
        this.demo = true;
        this.usrLat = 0.0;
        this.usrLong = 0.0;
        this.sunAngle = 0;
        this.moonAngle = 0;
        this.sunRiseOK = true;
        this.moonRiseOK = true;
        this.drawSunOK = true;
        this.drawMoonOK = true;
        this.W = 730;
        this.drawMoonDeclin = false;
        this.drawSunDeclin = false;
        this.sunEclipseOK = false;
        this.moonEclipseOK = false;
        this.Data = new String[500];
        this.fr = new int[181];
        this.frSun = new int[181];
        this.maxRiseAz = 0.0;
        this.minRiseAz = 0.0;
        this.maxSetAz = 0.0;
        this.minSetAz = 0.0;
        this.fullMoonJD = new double[17];
        this.fullMoon = false;
        this.fmStr = new String[20];
    }
}
