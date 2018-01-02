import java.awt.Cursor;
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
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Date;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Button;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class pursuit087 extends Applet
{
    String versStr;
    final char deg = '°';
    final double K = 0.017453292519943295;
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
    int timezoneoffset;
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
    Choice hoursChoice;
    Choice minChoice;
    Choice yearChoice;
    Choice monthChoice;
    Choice dateChoice;
    Choice writeChoice;
    String[] monthArray;
    String str;
    String locString;
    String ewStr;
    String nsStr;
    String latStr;
    String longStr;
    String timeString;
    public String email;
    public String param;
    public String wwwStr;
    String usrStr;
    String homeString;
    String homeLatStr;
    String homeLongStr;
    String separator;
    Date dat;
    double Jd;
    double UT;
    double az;
    double elev;
    double dec;
    double RA;
    double X;
    double Y;
    double hRise;
    double hSet;
    int[] xi;
    int[] yi;
    boolean[] vis;
    double[][] data;
    int step;
    int dT;
    double path;
    double v;
    int x0;
    int y0;
    int margin;
    int oben;
    int unten;
    int rechts;
    Rectangle rect;
    Point pt;
    double daylight;
    int text;
    double xMouse;
    double yMouse;
    int xM;
    int yM;
    boolean directionOK;
    int[] xii;
    int[] yii;
    int N;
    boolean positionOK;
    boolean cursorOK;
    String str1;
    String str2;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 7344;
    }
    
    public void init() {
        final Color background = new Color(235, 235, 255);
        this.setBackground(background);
        this.x0 = this.size().width / 2;
        this.y0 = this.size().height / 2 - 35;
        this.X = this.x0;
        this.Y = this.y0;
        this.unten = this.size().height - 20;
        this.rechts = this.size().width - this.margin;
        this.rect = new Rectangle(this.margin, this.oben, this.size().width - 2 * this.margin, this.size().height - this.oben - 20);
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
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        this.timezoneoffset = -this.dat.getTimezoneOffset() / 60;
        this.timeString = String.valueOf(this.timezoneoffset);
        if (this.timezoneoffset > 0) {
            this.timeString = "+" + this.timeString;
        }
        this.timeString = "UT " + this.timeString + " h";
        final Font f = new Font("Helvetica", 0, 11);
        this.setFont(f);
        gbc.weighty = 0.0;
        gbc.insets = new Insets(0, 20, 0, 0);
        final Label L11 = new Label();
        L11.setText("Time Zone");
        gbl.setConstraints(L11, gbc);
        this.add(L11);
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
        gbc.insets = new Insets(5, 0, 0, 20);
        gbl.setConstraints(this.writeChoice, gbc);
        this.writeChoice.addItem("Select:");
        this.writeChoice.addItem("v=3 km/h");
        this.writeChoice.addItem("v=4 km/h");
        this.writeChoice.addItem("v=5 km/h");
        this.writeChoice.addItem("v=6 km/h");
        this.writeChoice.addItem("v=8 km/h");
        this.writeChoice.addItem("dT=1 min");
        this.writeChoice.addItem("dT=5 min");
        this.writeChoice.addItem("dT=10 min");
        this.writeChoice.addItem("dT=20 min");
        this.writeChoice.addItem("dT=30 min");
        this.writeChoice.addItem("dT=60 min");
        this.writeChoice.addItem("Direction on/off");
        this.writeChoice.addItem("Positions on/off");
        this.writeChoice.addItem("Cursor on/off");
        this.writeChoice.addItem("Data");
        this.add(this.writeChoice);
        gbc.gridy = 1;
        this.time = new Seloc();
        this.timeChoice = new Choice();
        this.time.timeMenu(this.timeChoice);
        gbc.insets = new Insets(0, 20, 0, 0);
        gbl.setConstraints(this.timeChoice, gbc);
        this.timeChoice.select(this.timeString);
        this.add(this.timeChoice);
        gbc.insets = new Insets(0, 0, 0, 0);
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
        gbl.setConstraints(this.nowButton = new Button("New Path"), gbc);
        this.add(this.nowButton);
        gbc.gridy = 3;
        gbc.gridx = 0;
        final Label L17 = new Label();
        L17.setText("Local Time (h:m)");
        gbc.insets = new Insets(5, 20, 0, 0);
        gbl.setConstraints(L17, gbc);
        this.add(L17);
        gbc.insets = new Insets(5, 0, 0, 0);
        gbc.gridx = 1;
        this.hoursChoice = new Choice();
        for (int i = 0; i < 10; ++i) {
            this.hoursChoice.addItem("0" + i);
        }
        for (int j = 10; j < 24; ++j) {
            this.hoursChoice.addItem(String.valueOf(j));
        }
        this.hoursChoice.select(this.hours);
        gbl.setConstraints(this.hoursChoice, gbc);
        this.add(this.hoursChoice);
        gbc.gridx = 2;
        this.minChoice = new Choice();
        for (int k = 0; k < 10; ++k) {
            this.minChoice.addItem("0" + k);
        }
        for (int l = 10; l < 60; ++l) {
            this.minChoice.addItem(String.valueOf(l));
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
        for (int m = 0; m < 110; ++m) {
            this.yearChoice.addItem(String.valueOf(this.year + 1900 - 56 + m));
        }
        this.yearChoice.select(String.valueOf(this.year + 1900));
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
            this.dateChoice.addItem(String.valueOf(i3));
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
        if (this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22)) {
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
        System.out.println(String.valueOf(this.versStr) + "  (c) 2006-2011 J.Giesen - www.GeoAstro.de");
        this.comp = new compute();
        final Rise_Set rs = new Rise_Set(this.dat, this.latitude, this.longitude);
        this.hRise = rs.h_rise() + this.timezoneoffset;
        this.hSet = rs.h_set() + this.timezoneoffset;
        this.hours = (int)this.hRise;
        this.dat.setHours(this.hours);
        this.hoursChoice.select(this.hours);
        this.minutes = (int)Math.round(60.0 * this.comp.frac(this.hRise));
        this.dat.setMinutes(this.minutes);
        this.minChoice.select(this.minutes);
        this.dateChoice.select(this.date - 1);
        this.monthChoice.select(this.month);
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
                break;
            }
        }
        return m;
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
    
    public void paint(final Graphics g) {
        g.setColor(Color.white);
        g.fillRect(this.margin, this.oben, this.size().width - 2 * this.margin, this.size().height - this.oben - 20);
        g.setFont(new Font("Courier", 0, 12));
        g.setColor(Color.black);
        g.drawString("E", this.size().width - 20, this.y0 + 5);
        g.drawString("W", 10, this.y0 + 5);
        g.setFont(new Font("Courier", 0, 11));
        if (this.latitude < 0.0) {
            this.y0 = this.size().height / 2 + 90;
        }
        else {
            this.y0 = this.size().height / 2 - 35;
        }
        int y = 125;
        int n = 0;
        while (y <= 600) {
            y = 125 + n * 25;
            if (n % 2 == 0) {
                g.setColor(Color.darkGray);
            }
            else {
                g.setColor(Color.gray);
            }
            g.drawLine(this.margin, y, this.rechts, y);
            ++n;
        }
        for (int i = -11; i <= 11; ++i) {
            if (i % 2 == 0) {
                g.setColor(Color.darkGray);
            }
            else {
                g.setColor(Color.gray);
            }
            if (i != 0) {
                g.drawLine(this.x0 - i * 25, this.oben, this.x0 - i * 25, this.unten);
            }
        }
        g.setColor(Color.black);
        if (this.cursorOK && this.rect.contains(new Point(this.xM, this.yM))) {
            if (this.xM < this.size().width - 3 * this.margin) {
                g.drawString(this.xMouse + " " + this.yMouse, this.xM + 5, this.yM - 5);
            }
            else {
                g.drawString(this.xMouse + " " + this.yMouse, this.xM - 50, this.yM - 5);
            }
        }
        g.clearRect(this.margin, this.oben, 125, 25);
        g.drawString(" " + this.latStr + '°' + this.nsStr + " " + this.longStr + '°' + this.ewStr, this.margin, this.oben + 15);
        g.drawLine(this.margin, this.y0, this.size().width - this.margin, this.y0);
        g.drawLine(this.x0, this.oben + 25, this.x0, this.size().height - 20);
        g.drawRect(this.margin, this.oben, this.size().width - 2 * this.margin, this.size().height - this.oben - 20);
        this.minutes = this.dat.getMinutes();
        this.hours = this.dat.getHours();
        this.date = this.dat.getDate();
        this.year = this.dat.getYear();
        this.month = this.dat.getMonth();
        this.year = this.dat.getYear();
        final Rise_Set rs = new Rise_Set(this.dat, this.latitude, this.longitude);
        this.hRise = rs.h_rise() + this.timezoneoffset;
        this.hSet = rs.h_set() + this.timezoneoffset;
        this.daylight = this.hSet - this.hRise;
        if (this.daylight < 24.0 && this.daylight > 0.0) {
            this.str1 = "Rise " + rs.makeTimeString(this.hRise);
            this.str2 = "Set  " + rs.makeTimeString(this.hSet);
        }
        if (this.daylight == 24.0) {
            this.str1 = "Rise Vis.";
            this.str2 = "Set  all day";
        }
        if (this.daylight == 0.0) {
            this.str1 = "Rise Invis.";
            this.str2 = "Set  all day";
        }
        g.drawString(this.str1, this.margin, this.text);
        g.drawString(this.str2, this.margin, this.text + 12);
        final double time = this.hours + this.minutes / 60.0;
        this.UT = time - this.timezoneoffset;
        this.Jd = this.comp.JD(this.date, this.month + 1, this.year + 1900, this.UT);
        this.dec = this.comp.sunDecRA(this.Jd, 1);
        this.RA = this.comp.sunDecRA(this.Jd, 2);
        this.elev = this.comp.sun_elev(this.Jd, this.latitude, -this.longitude, this.dec, this.RA);
        this.az = this.comp.azimuth(this.Jd, this.RA, this.dec, this.latitude, this.longitude);
        g.drawString("Declin.  " + Math.round(100.0 * this.dec) / 100.0 + '°', 140, this.text);
        this.daylight = this.hSet - this.hRise;
        g.drawString("Daylight " + this.comp.HM(this.daylight), 140, this.text + 12);
        if (this.elev > -0.83) {
            g.setColor(Color.red);
        }
        else {
            g.setColor(Color.blue);
        }
        g.drawString("Altitude " + Math.round(100.0 * this.elev) / 100.0 + '°', 280, this.text);
        g.setColor(Color.black);
        g.drawString("Azimuth  " + Math.round(100.0 * this.az) / 100.0 + '°', 280, this.text + 12);
        g.drawString("Time " + this.comp.HM(this.step * this.dT / 60.0) + "  dT=" + this.dT + " min", 410, this.text);
        this.path = this.v * (this.step * this.dT / 60.0) / 5.0;
        if (this.step == 0) {
            this.path = 0.0;
        }
        this.str = "Path " + Math.round(100.0 * this.path) / 100.0;
        if (this.str.indexOf(".") - this.str.length() == -2) {
            this.str = String.valueOf(this.str) + "0";
        }
        g.drawString(String.valueOf(this.str) + " km", 410, this.text + 12);
        g.drawString("v=" + this.v / 5.0 + " km/h", 513, this.text + 12);
        this.xi[this.step] = (int)Math.round(this.X);
        this.data[this.step][1] = (int)Math.round(20.0 * (this.X - this.x0)) / 100.0;
        this.data[this.step][2] = -(int)Math.round(20.0 * (this.Y - this.y0)) / 100.0;
        this.data[this.step][0] = this.Jd;
        this.data[this.step][3] = this.az;
        this.data[this.step][4] = this.elev;
        this.yi[this.step] = (int)Math.round(this.Y);
        if (this.elev > -0.83) {
            this.vis[this.step] = true;
        }
        else {
            this.vis[this.step] = false;
        }
        g.drawOval(this.xi[this.step] - 1, this.yi[this.step] - 1, 2, 2);
        for (int j = 0; j <= this.N; ++j) {
            if (this.rect.contains(new Point(this.xii[j], this.yii[j]))) {
                g.drawOval(this.xii[j] - 2, this.yii[j] - 2, 4, 4);
            }
        }
        this.pt = new Point(this.xi[this.step], this.yi[this.step]);
        for (int k = 0; k < this.step; ++k) {
            if (this.rect.contains(new Point(this.xi[k], this.yi[k]))) {
                if (this.vis[k]) {
                    g.setColor(Color.red);
                }
                else {
                    g.setColor(Color.blue);
                }
                g.drawLine(this.xi[k], this.yi[k], this.xi[k + 1], this.yi[k + 1]);
                if (this.dT == 60) {
                    g.drawOval(this.xi[k] - 1, this.yi[k] - 1, 2, 2);
                }
            }
        }
        if (this.directionOK && this.rect.contains(this.pt)) {
            if (this.elev > -0.83) {
                g.setColor(Color.magenta);
            }
            else {
                g.setColor(Color.cyan);
            }
            g.drawLine(this.xi[this.step], this.yi[this.step], this.xi[this.step] + (int)Math.round(1.5 * this.v * Math.sin(0.017453292519943295 * this.az)), this.yi[this.step] - (int)Math.round(1.5 * this.v * Math.cos(0.017453292519943295 * this.az)));
        }
        g.setColor(Color.black);
        g.setFont(new Font("Helvetica", 0, 11));
        g.drawString(String.valueOf(this.versStr) + "  (c) 2006-2011 J.Giesen - www.GeoAstro.de", 135, this.size().height - 5);
        g.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
        g.setFont(new Font("Courier", 0, 10));
        for (int l = 0; l < 6; ++l) {
            g.drawString(String.valueOf(l * 10), this.x0 + l * 50 - 5, this.size().height - 25);
        }
        g.drawString("km", this.size().width - 45, this.size().height - 25);
        g.setFont(new Font("Courier", 0, 12));
        g.drawString("North", this.x0 - 15, this.oben + 15);
        if (this.demo) {
            g.setFont(new Font("Chicago", 0, 96));
            g.setColor(Color.red);
            g.drawString("D E M O", 120, 400);
        }
    }
    
    public boolean keyDown(final Event event, final int code) {
        int n = 0;
        if (code != 104 && code != 100 && code != 109 && code != 68 && code != 121) {
            return false;
        }
        if (code == 109) {
            this.minutes += this.dT;
            if (this.minutes >= 60) {
                this.minutes %= 60;
                ++this.hours;
                if (this.hours == 24) {
                    this.hours = 0;
                    ++this.date;
                }
                this.dat.setHours(this.hours);
                this.hoursChoice.select(this.hours);
                if (this.date > this.daysInMonth(this.month, this.year)) {
                    this.date = 1;
                    this.dat.setDate(this.date);
                    ++this.month;
                    this.dat.setMonth(this.month);
                    this.monthChoice.select(this.month);
                }
                this.dateChoice.select(this.date - 1);
            }
            this.dat.setDate(this.date);
            this.dat.setMinutes(this.minutes);
            this.minChoice.select(this.minutes);
            this.X += this.v * Math.sin(0.017453292519943295 * this.az) * this.dT / 60.0;
            this.Y -= this.v * Math.cos(0.017453292519943295 * this.az) * this.dT / 60.0;
            ++this.step;
            this.step %= 1440;
            if (this.positionOK && this.minutes == (int)Math.round(60.0 * this.comp.frac(this.hRise))) {
                this.xii[this.N] = (int)Math.round(this.X);
                this.yii[this.N] = (int)Math.round(this.Y);
                ++this.N;
            }
            this.repaint();
            return true;
        }
        if (code == 104) {
            this.dT = 60;
            ++this.hours;
            if (this.hours >= 24) {
                this.hours -= 24;
                ++this.date;
                this.month = this.dat.getMonth();
                this.year = this.dat.getYear();
                n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = 1;
                    ++this.month;
                    if (this.month == 12) {
                        this.month = 0;
                        ++this.year;
                        this.yearChoice.select(String.valueOf(this.year + 1900));
                        this.dat.setYear(this.year);
                    }
                    this.dat.setMonth(this.month);
                    this.monthChoice.select(this.month);
                }
                this.dateChoice.select(this.date - 1);
            }
            this.dat.setHours(this.hours);
            this.dat.setDate(this.date);
            this.hoursChoice.select(this.hours);
            this.date = this.dat.getDate();
            this.dateChoice.select(this.date - 1);
            this.month = this.dat.getMonth();
            this.monthChoice.select(this.month);
            this.X += this.v * Math.sin(0.017453292519943295 * this.az);
            this.Y -= this.v * Math.cos(0.017453292519943295 * this.az);
            ++this.step;
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
                        this.dat.setYear(this.year);
                    }
                    this.date = this.daysInMonth(this.month, this.year);
                }
            }
            else {
                ++this.date;
                this.month = this.dat.getMonth();
                n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = 1;
                    ++this.month;
                    if (this.month == 12) {
                        this.month = 0;
                        ++this.year;
                        this.dat.setYear(this.year);
                    }
                }
            }
            this.dateChoice.select(this.date - 1);
            this.dat.setMonth(this.month);
            this.monthChoice.select(this.month);
            this.dat.setDate(this.date);
            this.yearChoice.select(String.valueOf(this.year + 1900));
            this.repaint();
            return true;
        }
        return false;
    }
    
    public void neu() {
        for (int i = 0; i < 100; ++i) {
            this.xii[i] = 0;
            this.yii[i] = 0;
        }
        this.N = 0;
        this.X = this.x0;
        if (this.latitude < 0.0) {
            this.y0 = this.size().height / 2 + 90;
        }
        else {
            this.y0 = this.size().height / 2 - 35;
        }
        this.Y = this.y0;
        this.step = 0;
        this.path = 0.0;
        (this.dat = new Date()).setMonth(this.month);
        this.dat.setDate(this.date);
        final Rise_Set rs = new Rise_Set(this.dat, this.latitude, this.longitude);
        this.hRise = rs.h_rise() + this.timezoneoffset;
        this.hSet = rs.h_set() + this.timezoneoffset;
        if (this.hRise == this.timezoneoffset) {
            this.hours = 0;
        }
        else {
            this.hours = (int)this.hRise;
        }
        this.dat.setHours(this.hours);
        this.hoursChoice.select(this.hours);
        this.minutes = (int)Math.round(60.0 * this.comp.frac(this.hRise));
        this.dat.setMinutes(this.minutes);
        this.minChoice.select(this.minutes);
        this.dateChoice.select(this.date - 1);
        this.monthChoice.select(this.month);
    }
    
    public boolean mouseMove(final Event event, final int x, final int y) {
        if (!this.cursorOK) {
            return true;
        }
        final Point p = new Point(x, y);
        this.xM = x;
        this.yM = y;
        if (this.rect.contains(p)) {
            this.setCursor(new Cursor(1));
            this.xMouse = (x - this.x0) / 5.0;
            this.yMouse = -(y - 1 - this.y0) / 5.0;
        }
        else {
            this.setCursor(new Cursor(0));
        }
        this.repaint();
        return true;
    }
    
    public void doData() {
        final scrollFrame sf = new scrollFrame(String.valueOf(this.latStr) + this.nsStr + ", " + this.longStr + this.ewStr + ", " + this.monthArray[this.month] + " " + this.date, this.step, this.data, this.demo);
        sf.resize(320, 480);
        sf.show();
    }
    
    public boolean action(final Event event, final Object eventobject) {
        final double oldLat = this.latitude;
        final double oldLong = this.longitude;
        if (event.target instanceof Button && event.target == this.nowButton) {
            this.neu();
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
                if (this.str.length() == 0) {
                    this.fieldLatDeg.setText(this.latStr);
                    this.repaint();
                    return true;
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
                this.neu();
                this.repaint();
                return true;
            }
            else if (event.target == this.fieldLongDeg) {
                this.str = this.fieldLongDeg.getText();
                for (int i = 0; i < this.str.length(); ++i) {
                    final char c = this.str.charAt(i);
                    if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != '.') {
                        this.fieldLongDeg.setText(String.valueOf(Math.abs(oldLong)));
                        return true;
                    }
                }
                if (this.str.length() == 0) {
                    this.fieldLongDeg.setText(this.longStr);
                    this.repaint();
                    return true;
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
                this.neu();
                this.repaint();
                return true;
            }
        }
        if (event.target instanceof Choice) {
            if (event.target == this.writeChoice) {
                if (this.writeChoice.getSelectedItem().equals("v=3 km/h")) {
                    this.v = 15.0;
                }
                if (this.writeChoice.getSelectedItem().equals("v=4 km/h")) {
                    this.v = 20.0;
                }
                if (this.writeChoice.getSelectedItem().equals("v=5 km/h")) {
                    this.v = 25.0;
                }
                if (this.writeChoice.getSelectedItem().equals("v=6 km/h")) {
                    this.v = 30.0;
                }
                if (this.writeChoice.getSelectedItem().equals("v=8 km/h")) {
                    this.v = 40.0;
                }
                if (this.writeChoice.getSelectedItem().equals("dT=1 min")) {
                    this.dT = 1;
                }
                if (this.writeChoice.getSelectedItem().equals("dT=5 min")) {
                    this.dT = 5;
                }
                if (this.writeChoice.getSelectedItem().equals("dT=10 min")) {
                    this.dT = 10;
                }
                if (this.writeChoice.getSelectedItem().equals("dT=20 min")) {
                    this.dT = 20;
                }
                if (this.writeChoice.getSelectedItem().equals("dT=30 min")) {
                    this.dT = 30;
                }
                if (this.writeChoice.getSelectedItem().equals("dT=60 min")) {
                    this.dT = 60;
                }
                if (this.writeChoice.getSelectedItem().equals("Direction on/off")) {
                    this.directionOK ^= true;
                }
                if (this.writeChoice.getSelectedItem().equals("Positions on/off")) {
                    this.positionOK ^= true;
                }
                if (this.writeChoice.getSelectedItem().equals("Cursor on/off")) {
                    this.cursorOK ^= true;
                }
                if (this.writeChoice.getSelectedItem().equals("Data")) {
                    this.doData();
                }
                this.writeChoice.select(0);
                this.repaint();
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
                this.neu();
                this.repaint();
                return true;
            }
            else {
                if (event.target == this.ewChoice) {
                    if (!this.ewChoice.getSelectedItem().equals(this.ewStr) || !this.fieldLongDeg.getText().equals(this.longStr)) {
                        this.locString = "User Input";
                        this.locChoice.select(this.locString);
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
                    this.usrLat = this.latitude;
                    this.usrLong = this.longitude;
                    this.neu();
                    this.repaint();
                    return true;
                }
                if (event.target == this.nsChoice) {
                    if (!this.nsChoice.getSelectedItem().equals(this.nsStr) || !this.fieldLatDeg.getText().equals(this.latStr)) {
                        this.locString = "User Input";
                        this.locChoice.select(this.locString);
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
                    this.neu();
                    this.repaint();
                    return true;
                }
                if (event.target == this.timeChoice) {
                    this.timeString = this.timeChoice.getSelectedItem();
                    this.timezoneoffset = this.loc.getTimeZone(this.timeString);
                    System.out.println("timezoneoffset " + this.timezoneoffset);
                    this.neu();
                    this.repaint();
                    return true;
                }
                if (event.target == this.hoursChoice) {
                    this.hours = Integer.parseInt(this.hoursChoice.getSelectedItem());
                    this.dat.setHours(this.hours);
                    this.repaint();
                    return true;
                }
                if (event.target == this.minChoice) {
                    this.minutes = Integer.parseInt(this.minChoice.getSelectedItem());
                    this.dat.setMinutes(this.minutes);
                    this.repaint();
                    return true;
                }
                if (event.target == this.dateChoice) {
                    this.date = Integer.parseInt(this.dateChoice.getSelectedItem());
                    this.dat.setDate(this.date);
                    final int n = this.daysInMonth(this.month, this.year);
                    if (this.date > n) {
                        this.date = 1;
                        this.dat.setDate(this.date);
                        this.dateChoice.select(this.date - 1);
                        ++this.month;
                        this.dat.setMonth(this.month);
                        this.monthChoice.select(this.month);
                    }
                    this.neu();
                    this.repaint();
                    return true;
                }
                if (event.target == this.monthChoice) {
                    this.month = this.MonthInteger(this.monthChoice.getSelectedItem());
                    final int n = this.daysInMonth(this.month, this.year);
                    if (this.date > n) {
                        this.date = n;
                        this.dat.setDate(this.date);
                        this.dateChoice.select(this.date - 1);
                    }
                    this.dat.setMonth(this.month);
                    this.neu();
                    this.repaint();
                    return true;
                }
                if (event.target == this.yearChoice) {
                    this.year = Integer.parseInt(this.yearChoice.getSelectedItem());
                    this.year -= 1900;
                    this.dat.setYear(this.year);
                    this.repaint();
                    return true;
                }
            }
        }
        return true;
    }
    
    public pursuit087() {
        this.versStr = "Pursuing the Sun  v. 0.87";
        this.demo = true;
        this.online = false;
        this.usrLat = 0.0;
        this.usrLong = 0.0;
        this.monthArray = new String[12];
        this.usrStr = "demo";
        this.separator = "---------------";
        this.xi = new int[1450];
        this.yi = new int[1450];
        this.vis = new boolean[1450];
        this.data = new double[1450][5];
        this.step = 0;
        this.dT = 10;
        this.path = 0.0;
        this.v = 25.0;
        this.margin = 30;
        this.oben = 125;
        this.text = 105;
        this.directionOK = true;
        this.xii = new int[100];
        this.yii = new int[100];
        this.N = 0;
        this.positionOK = false;
        this.cursorOK = false;
    }
}
