import java.awt.Event;
import java.util.Map;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.Dialog;
import java.net.URL;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Checkbox;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Button;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SunMoonPol34 extends Applet
{
    final double K = 0.017453292519943295;
    final char deg = '°';
    Date dat;
    int year;
    int month;
    int date;
    int hours;
    int minutes;
    int seconds;
    public double latitude;
    public double longitude;
    double GHA;
    double dec;
    double hoehe;
    double azimut;
    int browserOffset;
    public int locOffset;
    String versStr;
    public sunloc loc;
    public sunloc time;
    public Aboutdialog about;
    Button button;
    Button aboutButton;
    public String email;
    public String param;
    public String wwwStr;
    public Choice locChoice;
    public Choice timeChoice;
    public Choice detailsChoice;
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
    String yearStr;
    String ewStr;
    String nsStr;
    String latStr;
    String longStr;
    String[] dayArray;
    String[] monthArray;
    final int xOben = 60;
    public compute comp;
    String str;
    public Label L11;
    public Label L12;
    public Label L13;
    public Label L14;
    public Label L15;
    public Label L16;
    public Label L17;
    public Label L23;
    public Label detailsLabel;
    boolean online;
    String usrStr;
    String homeString;
    String homeLatStr;
    String homeLongStr;
    double homeLat;
    double homeLong;
    String serverStr;
    boolean demo;
    String userString;
    double usrLat;
    double usrLong;
    boolean isZoom;
    boolean isMoon;
    boolean isSun;
    Moon myMoon;
    double moonH;
    double moonAz;
    double moon_Dec;
    String moonriseStr;
    String moonsetStr;
    moonPhase myMoonPhase;
    double minMoonRiseAz;
    double maxMoonRiseAz;
    boolean JanJun;
    boolean analemmaOK;
    int[] analemmaX;
    int[] analemmaY;
    Checkbox zoomBox;
    int[] xArray;
    int[] yArray;
    boolean sector;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 12373;
    }
    
    public void init() {
        this.setBackground(Color.white);
        this.setFont(new Font("Helvetica", 0, 12));
        this.userString = this.getParameter("email");
        this.homeString = this.getParameter("location");
        this.homeLatStr = this.getParameter("latitude");
        this.homeLongStr = this.getParameter("longitude");
        this.serverStr = this.getParameter("server");
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
        this.browserOffset = this.dat.getTimezoneOffset();
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
        (this.L11 = new Label()).setText("Time Zone");
        gbl.setConstraints(this.L11, gbc);
        this.add(this.L11);
        (this.L12 = new Label()).setText("Location");
        gbl.setConstraints(this.L12, gbc);
        this.add(this.L12);
        gbc.weightx = 10.0;
        (this.L13 = new Label()).setText("Latit.: xx.xx");
        gbl.setConstraints(this.L13, gbc);
        this.add(this.L13);
        (this.L14 = new Label()).setText("N/S");
        gbl.setConstraints(this.L14, gbc);
        this.add(this.L14);
        (this.L15 = new Label()).setText("Longit.: xx.xx");
        gbl.setConstraints(this.L15, gbc);
        this.add(this.L15);
        (this.L16 = new Label()).setText("E/W");
        gbl.setConstraints(this.L16, gbc);
        this.add(this.L16);
        gbl.setConstraints(this.detailsLabel = new Label("Details"), gbc);
        this.add(this.detailsLabel);
        final Label L = new Label("Zoom");
        gbl.setConstraints(L, gbc);
        this.add(L);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 0, 0);
        this.time = new sunloc();
        this.timeChoice = new Choice();
        this.time.timeMenu(this.timeChoice);
        gbl.setConstraints(this.timeChoice, gbc);
        this.timeChoice.select(this.timeString);
        this.add(this.timeChoice);
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
        gbl.setConstraints(this.fieldLatDeg = new TextField(this.latStr), gbc);
        this.add(this.fieldLatDeg);
        (this.nsChoice = new Choice()).addItem("N");
        this.nsChoice.addItem("S");
        this.nsChoice.select(this.nsStr);
        gbl.setConstraints(this.nsChoice, gbc);
        this.add(this.nsChoice);
        this.longStr = String.valueOf(Math.abs(this.longitude));
        gbl.setConstraints(this.fieldLongDeg = new TextField(this.longStr), gbc);
        this.add(this.fieldLongDeg);
        (this.ewChoice = new Choice()).addItem("E");
        this.ewChoice.addItem("W");
        this.ewChoice.select(this.ewStr);
        gbl.setConstraints(this.ewChoice, gbc);
        this.add(this.ewChoice);
        (this.detailsChoice = new Choice()).addItem("Sun on/off");
        this.detailsChoice.addItem("Moon on/off");
        this.detailsChoice.addItem("Analemma on/off");
        this.detailsChoice.addItem("Sector on/off");
        this.detailsChoice.addItem("Dec-Jun on/off");
        gbl.setConstraints(this.detailsChoice, gbc);
        this.add(this.detailsChoice);
        gbl.setConstraints(this.zoomBox = new Checkbox(), gbc);
        this.add(this.zoomBox);
        gbc.gridy = 2;
        (this.L17 = new Label()).setText("Local Time");
        gbc.insets = new Insets(5, 20, 0, 0);
        gbl.setConstraints(this.L17, gbc);
        this.add(this.L17);
        gbc.insets = new Insets(5, 0, 0, 0);
        this.hoursChoice = new Choice();
        for (int i = 0; i < 10; ++i) {
            this.hoursStr = "0" + String.valueOf(i);
            this.hoursChoice.addItem(this.hoursStr);
        }
        for (int j = 10; j < 25; ++j) {
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
        (this.L23 = new Label()).setText("Date :");
        gbl.setConstraints(this.L23, gbc);
        this.add(this.L23);
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        gbc.insets = new Insets(5, 0, 0, 10);
        this.yearChoice = new Choice();
        for (int m = 0; m < 41; ++m) {
            this.yearStr = String.valueOf(this.year + 1900 - 20 + m);
            this.yearChoice.addItem(this.yearStr);
        }
        this.yearStr = String.valueOf(this.year + 1900);
        this.yearChoice.select(this.yearStr);
        gbl.setConstraints(this.yearChoice, gbc);
        this.add(this.yearChoice);
        this.monthChoice = new Choice();
        for (int i2 = 0; i2 < 12; ++i2) {
            this.monthChoice.addItem(this.monthArray[i2]);
        }
        this.monthChoice.select(this.month);
        gbl.setConstraints(this.monthChoice, gbc);
        this.add(this.monthChoice);
        this.dateChoice = new Choice();
        for (int i3 = 1; i3 < 32; ++i3) {
            this.dateStr = String.valueOf(i3);
            this.dateChoice.addItem(this.dateStr);
        }
        this.dateChoice.select(this.date - 1);
        gbl.setConstraints(this.dateChoice, gbc);
        this.add(this.dateChoice);
        (this.button = new Button()).setLabel(" now  ");
        gbc.insets = new Insets(5, 0, 0, 10);
        gbl.setConstraints(this.button, gbc);
        this.add(this.button);
        gbc.gridy = 3;
        gbc.weighty = 160.0;
        this.aboutButton = new Button();
        gbc.gridx = 0;
        gbc.insets = new Insets(500, 50, 0, 0);
        gbl.setConstraints(this.aboutButton, gbc);
        this.aboutButton.setLabel("About...");
        this.add(this.aboutButton);
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        final int xy = this.formula(this.wwwStr, 27);
        if (this.formula(this.wwwStr, 18) == this.formula("http://www.tyge.de", 18) || this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(this.wwwStr, 22) == this.formula("http://www.geoastro.de", 22) || this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 21) == this.formula("http://www.j-giesen.de", 21) || this.formula(this.wwwStr, 20) == this.formula("http://www.SciAm.com", 20)) {
            ok = true;
            this.online = true;
            this.demo = false;
        }
        else {
            ok = false;
        }
        if (Integer.parseInt(this.serverStr) != 0) {
            if (this.formula(this.wwwStr, 27) == Integer.parseInt(this.serverStr)) {
                ok = true;
                this.online = true;
                this.demo = false;
            }
            else {
                ok = false;
            }
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
                this.demo = true;
            }
            if (!ok) {
                if (this.demo) {
                    this.versStr = String.valueOf(this.versStr) + " DEMO";
                }
                final Dialog AboutDialog = new Aboutdialog(this, this.versStr, this.demo);
                AboutDialog.resize(350, 230);
                AboutDialog.show();
            }
        }
        this.comp = new compute(this.dat, this.latitude, this.longitude, this.locOffset);
        final double elev = this.comp.elev();
        if (elev < -5.0) {
            this.isZoom = true;
        }
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
    
    public double frac(double X) {
        X -= (int)X;
        if (X < 0.0) {
            ++X;
        }
        return X;
    }
    
    public void paint(final Graphics g) {
        final RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        renderHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        final Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHints(renderHints);
        g.setColor(Color.white);
        g.fillRect(0, 0, this.size().width, this.size().height);
        final int oben = this.size().height - 60;
        final int rechts = this.size().width - 150;
        final double mondAlter = 0.0;
        g.setFont(new Font("Courier", 0, 10));
        g.setColor(Color.blue);
        final double UT = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        if (this.isMoon) {
            this.myMoonPhase = new moonPhase(this.locOffset, this.year + 1900 + this.month * 0.08);
            g.drawString(this.myMoonPhase.newMoonStr(), rechts, oben + 15);
            g.drawString(this.myMoonPhase.firstQuarterStr(), rechts, oben + 27);
            g.drawString(this.myMoonPhase.fullMoonStr(), rechts, oben + 39);
            g.drawString(this.myMoonPhase.lastQuarterStr(), rechts, oben + 51);
        }
        g.setColor(Color.red);
        final int left = 20;
        final int F = 9;
        String str = "";
        final int margin = 30;
        String elevStr = "";
        String azimStr = "";
        final int rSun = 2;
        final int rCurrentSun = 8;
        g.setColor(Color.red);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        int xx = this.size().width;
        int yy = this.size().height;
        if (xx > yy) {
            xx = yy;
        }
        else {
            yy = xx;
        }
        final int xm = xx / 2;
        final int ym = yy / 2 + 30;
        this.comp = new compute(this.dat, this.latitude, this.longitude, this.locOffset);
        final double declin = this.comp.declin();
        int r90;
        int circleNum;
        int R;
        if (!this.isZoom) {
            r90 = xm - 2 * margin;
            circleNum = 9;
            R = r90;
        }
        else {
            r90 = xm / 2 - margin;
            circleNum = 18;
            R = 2 * r90;
        }
        if (this.isZoom) {
            g.setColor(new Color(200, 200, 200));
            g.fillOval(xm - R - 1, ym - R - 1, 2 * R + 2, 2 * R + 2);
            g.setColor(Color.white);
            g.fillOval(xm - r90 - 1, ym - r90 - 1, 2 * r90 + 2, 2 * r90 + 2);
            g.setColor(Color.black);
            g.drawOval(xm - R, ym - R, 2 * R, 2 * R);
            g.drawOval(xm - R - 1, ym - R - 1, 2 * R + 2, 2 * R + 2);
        }
        else {
            g.setColor(Color.black);
            g.drawOval(xm - r90, ym - r90, 2 * r90, 2 * r90);
            g.drawOval(xm - r90 - 1, ym - r90 - 1, 2 * r90 + 2, 2 * r90 + 2);
        }
        g.setFont(new Font("Chicago", 0, 12));
        g.setColor(Color.red);
        if (this.isZoom) {
            g.drawString("N", xm - 3, ym - R - 5);
            g.drawString("S", xm - 3, ym + R - 5);
            g.drawString("E", xm + R - 10, ym);
            g.drawString("W", xm - R + 5, ym);
        }
        else {
            g.drawString("N", xm - 3, ym - r90 - 5);
            g.drawString("S", xm - 3, ym + r90 - 5);
            g.drawString("E", xm + R - 10, ym);
            g.drawString("W", xm - R + 5, ym);
        }
        g.setFont(new Font("Courier", 0, 10));
        for (int i = 1; i < circleNum; ++i) {
            final int r91 = (int)Math.round(i * r90 / 9.0);
            if (i < 9) {
                g.setColor(Color.lightGray);
                g.drawOval(xm - r91, ym - r91, 2 * r91, 2 * r91);
                g.setColor(Color.gray);
                g.drawString(new StringBuffer().append(90 - i * 10).append('°').toString(), xm + 1, ym - r91);
            }
            if (i > 9) {
                g.setColor(Color.gray);
                g.drawOval(xm - r91, ym - r91, 2 * r91, 2 * r91);
                if (i % 3 == 0) {
                    g.drawString(new StringBuffer().append(90 - i * 10).append('°').toString(), xm + 1, ym - r91);
                }
            }
        }
        if (this.isZoom) {
            final int r91 = (int)Math.round(9 * r90 / 9.0);
            g.setColor(Color.black);
            g.drawOval(xm - r91, ym - r91, 2 * r91, 2 * r91);
            g.drawOval(xm - r91 - 1, ym - r91 - 1, 2 * r91 + 2, 2 * r91 + 2);
            g.setColor(Color.gray);
        }
        for (int j = 0; j < 12; ++j) {
            final int x1 = xm + (int)Math.round(R * Math.sin(j * 30 * 0.017453292519943295));
            final int y1 = ym - (int)Math.round(R * Math.cos(j * 30 * 0.017453292519943295));
            g.drawLine(xm, ym, x1, y1);
        }
        for (int k = 1; k < 5; ++k) {
            final int x1 = xm + (int)Math.round(R * Math.sin(k * 30 * 0.017453292519943295));
            final int y1 = ym - (int)Math.round(R * Math.cos(k * 30 * 0.017453292519943295));
            g.drawString(new StringBuffer().append(k * 30).append('°').toString(), x1 + 7, y1 + 3);
        }
        for (int l = 8; l < 12; ++l) {
            final int x1 = xm + (int)Math.round(R * Math.sin(l * 30 * 0.017453292519943295));
            final int y1 = ym - (int)Math.round(R * Math.cos(l * 30 * 0.017453292519943295));
            g.drawString(new StringBuffer().append(l * 30).append('°').toString(), x1 - 25, y1);
        }
        int m = 5;
        int x1 = xm + (int)Math.round(R * Math.sin(m * 30 * 0.017453292519943295));
        int y1 = ym - (int)Math.round(R * Math.cos(m * 30 * 0.017453292519943295));
        g.drawString(new StringBuffer().append(m * 30).append('°').toString(), x1 + 10, y1 + 5);
        m = 6;
        x1 = xm + (int)Math.round(R * Math.sin(m * 30 * 0.017453292519943295));
        y1 = ym - (int)Math.round(R * Math.cos(m * 30 * 0.017453292519943295));
        g.drawString(new StringBuffer().append(m * 30).append('°').toString(), x1 - 10, y1 + 15);
        m = 7;
        x1 = xm + (int)Math.round(R * Math.sin(m * 30 * 0.017453292519943295));
        y1 = ym - (int)Math.round(R * Math.cos(m * 30 * 0.017453292519943295));
        g.drawString(new StringBuffer().append(m * 30).append('°').toString(), x1 - 25, y1 + 5);
        g.setColor(Color.red);
        g.setFont(new Font("Courier", 0, 10));
        g.drawString(this.dat.toString(), left, 90);
        double elev = this.comp.elev();
        double azim = this.comp.azimuth();
        elevStr = new StringBuffer().append(Math.round(10.0 * elev) / 10.0).append('°').toString();
        azimStr = String.valueOf(String.valueOf(Math.round(10.0 * azim) / 10.0)) + '°';
        g.drawString("SUN:", left, 103);
        g.drawString("Elev.= " + elevStr, left, 116);
        g.drawString("Azim.= " + azimStr, left, 129);
        g.drawString("Decl.= " + Math.round(100.0 * declin) / 100.0 + '°', left, 142);
        g.setColor(Color.black);
        if (this.latitude >= 0.0) {
            str = " N";
        }
        else {
            str = " S";
        }
        g.drawString("Lat.  " + Math.abs(this.latitude) + str, left, this.size().height - 100);
        if (this.longitude >= 0.0) {
            str = " W";
        }
        else {
            str = " E";
        }
        g.drawString("Long. " + Math.abs(this.longitude) + str, left, this.size().height - 87);
        this.myMoon = new Moon(this.dat, this.latitude, -this.longitude, this.locOffset);
        this.moonAz = this.myMoon.moon_Az();
        this.moonH = this.myMoon.moon_H();
        final MoonDistance myMoonDistance = new MoonDistance(this.date, this.month + 1, this.year, UT);
        final double moonDistance = myMoonDistance.compute();
        final double horParal = 8.794 / (moonDistance / 1.4959787E8);
        double paral = Math.cos(0.017453292519943295 * this.moonH) * Math.sin(0.017453292519943295 * horParal / 3600.0);
        paral = Math.asin(paral) / 0.017453292519943295;
        this.moonH -= paral;
        final double moon_GSW = this.myMoon.moonGSW();
        this.moon_Dec = this.myMoon.moonDEC();
        this.moonriseStr = this.myMoon.riseString();
        this.moonsetStr = this.myMoon.setString();
        final double hRiseAzMoon;
        double hRiseAz = hRiseAzMoon = this.myMoon.hMoonRise();
        final double hSetAzMoon;
        double hSetAz = hSetAzMoon = this.myMoon.hMoonSet();
        Date dat1 = new Date();
        int hoursRiseAz = (int)hRiseAz;
        dat1.setHours(hoursRiseAz);
        double minutesRiseAz = this.frac(hRiseAz) * 60.0;
        int minRiseAz = (int)minutesRiseAz;
        int secRiseAz = (int)(this.frac(minutesRiseAz) * 60.0);
        dat1.setMinutes(minRiseAz);
        dat1.setDate(this.date);
        dat1.setSeconds(secRiseAz);
        dat1.setYear(this.year);
        dat1.setMonth(this.month);
        final Moon myMoon1 = new Moon(dat1, this.latitude, -this.longitude, this.locOffset);
        final double moonAz1 = myMoon1.moon_Az();
        Date dat2 = new Date();
        int hoursSetAz = (int)hSetAz;
        dat2.setHours(hoursSetAz);
        double minutesSetAz = (hSetAz - hoursSetAz) * 60.0;
        int minSetAz = (int)minutesSetAz;
        int secSetAz = (int)((minutesSetAz - minSetAz) * 60.0);
        dat2.setMinutes(minSetAz);
        dat2.setDate(this.date);
        dat2.setSeconds(secSetAz);
        dat2.setYear(this.year);
        dat2.setMonth(this.month);
        final Moon myMoon2 = new Moon(dat2, this.latitude, -this.longitude, this.locOffset);
        final double moonAz2 = myMoon2.moon_Az();
        g.setColor(Color.blue);
        elevStr = new StringBuffer().append(Math.round(10.0 * this.moonH) / 10.0).append('°').toString();
        azimStr = new StringBuffer().append(Math.round(100.0 * this.moonAz) / 100.0).append('°').toString();
        g.drawString("MOON:", xx - 4 * margin + 25, 90);
        g.drawString("Elev.= " + elevStr, xx - 4 * margin + 25, 103);
        g.drawString("Azim.= " + azimStr, xx - 4 * margin + 25, 116);
        final double xe = Math.cos(0.017453292519943295 * azim);
        final double ye = Math.sin(0.017453292519943295 * azim);
        final double ze = Math.sin(0.017453292519943295 * elev);
        final double XM = Math.cos(0.017453292519943295 * this.moonAz);
        final double YM = Math.sin(0.017453292519943295 * this.moonAz);
        final double ZM = Math.sin(0.017453292519943295 * this.moonH);
        double elongation = xe * XM + ye * YM + ze * ZM;
        elongation /= Math.sqrt(xe * xe + ye * ye + ze * ze) * Math.sqrt(XM * XM + YM * YM + ZM * ZM);
        elongation = Math.acos(elongation) / 0.017453292519943295;
        final String elongationStr = "Elong= " + Math.round(10.0 * elongation) / 10.0 + '°';
        g.drawString(elongationStr, xx - 4 * margin + 25, 129);
        g.drawString("Decl.= " + Math.round(100.0 * this.moon_Dec) / 100.0 + '°', xx - 4 * margin + 25, 142);
        final double currentJD = this.comp.Jul_Date(this.date, this.month + 1, this.year + 1900, UT);
        final moonPhase myPhase = new moonPhase(this.locOffset, this.year + 1900 + this.month * 0.08);
        str = myPhase.newMoonStr();
        double age = currentJD - myPhase.moonAge();
        if (age > 29.53) {
            age -= 29.53;
        }
        g.drawString("Age  = " + Math.round(100.0 * age) / 100.0 + "d", xx - 4 * margin + 25, 155);
        x1 = xm + (int)Math.round((90.0 - elev) * r90 / 90.0 * Math.sin(azim * 0.017453292519943295));
        y1 = ym - (int)Math.round((90.0 - elev) * r90 / 90.0 * Math.cos(azim * 0.017453292519943295));
        final int xMoon = xm + (int)Math.round((90.0 - this.moonH) * r90 / 90.0 * Math.sin(this.moonAz * 0.017453292519943295));
        final int yMoon = ym - (int)Math.round((90.0 - this.moonH) * r90 / 90.0 * Math.cos(this.moonAz * 0.017453292519943295));
        if (!this.isZoom) {
            if ((x1 - xm) * (x1 - xm) + (y1 - ym) * (y1 - ym) < r90 * r90) {
                g.setColor(Color.yellow);
                g.fillOval(x1 - rCurrentSun, y1 - rCurrentSun, 2 * rCurrentSun, 2 * rCurrentSun);
                g.setColor(Color.red);
                g.drawOval(x1 - rCurrentSun, y1 - rCurrentSun, 2 * rCurrentSun, 2 * rCurrentSun);
            }
            if ((xMoon - xm) * (xMoon - xm) + (yMoon - ym) * (yMoon - ym) < r90 * r90) {
                g.setColor(Color.blue);
                g.fillOval(xMoon - rCurrentSun, yMoon - rCurrentSun, 2 * rCurrentSun, 2 * rCurrentSun);
                g.setColor(Color.red);
                g.drawOval(xMoon - rCurrentSun, yMoon - rCurrentSun, 2 * rCurrentSun, 2 * rCurrentSun);
            }
        }
        else {
            g.setColor(Color.yellow);
            g.fillOval(x1 - rCurrentSun, y1 - rCurrentSun, 2 * rCurrentSun, 2 * rCurrentSun);
            g.setColor(Color.red);
            g.drawOval(x1 - rCurrentSun, y1 - rCurrentSun, 2 * rCurrentSun, 2 * rCurrentSun);
            g.setColor(Color.blue);
            g.fillOval(xMoon - rCurrentSun, yMoon - rCurrentSun, 2 * rCurrentSun, 2 * rCurrentSun);
            g.setColor(Color.red);
            g.drawOval(xMoon - rCurrentSun, yMoon - rCurrentSun, 2 * rCurrentSun, 2 * rCurrentSun);
        }
        g.setFont(new Font("Courier", 0, 10));
        final Sun mySun = new Sun(this.dat, this.latitude, -this.longitude, this.locOffset);
        final String str2 = mySun.riseString();
        final String str3 = mySun.setString();
        hRiseAz = mySun.h_Rise();
        hSetAz = mySun.h_Set();
        dat1 = new Date();
        hoursRiseAz = (int)hRiseAz;
        dat1.setHours(hoursRiseAz);
        minutesRiseAz = this.frac(hRiseAz) * 60.0;
        minRiseAz = (int)minutesRiseAz;
        secRiseAz = (int)(this.frac(minutesRiseAz) * 60.0);
        dat1.setMinutes(minRiseAz);
        dat1.setDate(this.date);
        dat1.setSeconds(secRiseAz);
        dat1.setYear(this.year);
        dat1.setMonth(this.month);
        final Sun mySun2 = new Sun(dat1, this.latitude, -this.longitude, this.locOffset);
        final compute comp1 = new compute(dat1, this.latitude, this.longitude, this.locOffset);
        final double sunAz1 = comp1.azimuth();
        dat2 = new Date();
        hoursSetAz = (int)hSetAz;
        dat2.setHours(hoursSetAz);
        minutesSetAz = (hSetAz - hoursSetAz) * 60.0;
        minSetAz = (int)minutesSetAz;
        secSetAz = (int)((minutesSetAz - minSetAz) * 60.0);
        dat2.setMinutes(minSetAz);
        dat2.setDate(this.date);
        dat2.setSeconds(secSetAz);
        dat2.setYear(this.year);
        dat2.setMonth(this.month);
        final Sun mySun3 = new Sun(dat2, this.latitude, -this.longitude, this.locOffset);
        final compute comp2 = new compute(dat2, this.latitude, this.longitude, this.locOffset);
        final double sunAz2 = comp2.azimuth();
        final double azRise = Math.acos(Math.sin(0.017453292519943295 * declin) / Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
        x1 = xm + (int)Math.round(R * Math.sin(azRise * 0.017453292519943295));
        y1 = ym - (int)Math.round(R * Math.cos(azRise * 0.017453292519943295));
        final double currentJD2 = this.comp.Jul_Date(this.date, this.month + 1, this.year + 1900, 12.0);
        g.setColor(Color.red);
        if (this.isSun) {
            if (!this.JanJun && this.sector && hRiseAz != 0.0) {
                g.drawLine(xm, ym, x1, y1);
            }
            g.drawString("Rise " + str2, left, 155);
            if (hRiseAz != 0.0) {
                g.drawString("Az   " + Math.round(sunAz1 * 10.0) / 10.0 + '°', left, 168);
            }
            else {
                g.drawString("Az      -", left, 168);
            }
        }
        x1 = xm + (int)Math.round(R * Math.sin(sunAz2 * 0.017453292519943295));
        y1 = ym - (int)Math.round(R * Math.cos(sunAz2 * 0.017453292519943295));
        if (this.isSun) {
            if (!this.JanJun && this.sector && hSetAz != 0.0) {
                g.drawLine(xm, ym, x1, y1);
            }
            g.drawString("Set  " + str3, left, 181);
            if (hSetAz != 0.0) {
                g.drawString("Az   " + Math.round(sunAz2 * 10.0) / 10.0 + '°', left, 194);
            }
            else {
                g.drawString("Az      -", left, 194);
            }
        }
        double azMoonRise = Math.sin(0.017453292519943295 * this.moon_Dec);
        azMoonRise /= Math.cos(0.017453292519943295 * this.latitude);
        azMoonRise = Math.acos(azMoonRise) / 0.017453292519943295;
        x1 = xm + (int)Math.round(R * Math.sin(moonAz1 * 0.017453292519943295));
        y1 = ym - (int)Math.round(R * Math.cos(moonAz1 * 0.017453292519943295));
        if (this.isMoon && this.sector) {
            g.setColor(Color.blue);
            if (hRiseAzMoon != 0.0) {
                g.drawLine(xm, ym, x1, y1);
            }
            x1 = xm + (int)Math.round(R * Math.sin(moonAz2 * 0.017453292519943295));
            y1 = ym - (int)Math.round(R * Math.cos(moonAz2 * 0.017453292519943295));
            if (hSetAzMoon != 0.0) {
                g.drawLine(xm, ym, x1, y1);
            }
        }
        g.setColor(Color.blue);
        g.drawString(this.moonriseStr, xx - 4 * margin + 25, 168);
        if (hRiseAzMoon != 0.0) {
            g.drawString("Az   " + Math.round(10.0 * moonAz1) / 10.0 + '°', xx - 4 * margin + 25, 181);
        }
        else {
            g.drawString("Az     -", xx - 4 * margin + 25, 181);
        }
        g.drawString(this.moonsetStr, xx - 4 * margin + 25, 194);
        if (hSetAzMoon != 0.0) {
            g.drawString("Az   " + Math.round(10.0 * moonAz2) / 10.0 + '°', xx - 4 * margin + 25, 207);
        }
        else {
            g.drawString("Az     -", xx - 4 * margin + 25, 207);
        }
        g.setColor(Color.red);
        g.setFont(new Font("Courier", 0, 10));
        Date myDat = new Date();
        myDat.setDate(this.date);
        myDat.setMonth(this.month);
        myDat.setYear(this.year);
        if (this.isSun) {
            for (int i2 = 0; i2 < 49; ++i2) {
                myDat.setHours(i2 / 2);
                myDat.setMinutes(i2 * 30 % 60);
                myDat.setSeconds(0);
                this.comp = new compute(myDat, this.latitude, this.longitude, this.locOffset);
                elev = this.comp.elev();
                azim = this.comp.azimuth();
                x1 = xm + (int)Math.round((90.0 - elev) * r90 / 90.0 * Math.sin(azim * 0.017453292519943295));
                y1 = ym - (int)Math.round((90.0 - elev) * r90 / 90.0 * Math.cos(azim * 0.017453292519943295));
                this.xArray[i2] = x1;
                this.yArray[i2] = y1;
                if (!this.isZoom) {
                    if ((x1 - xm) * (x1 - xm) + (y1 - ym) * (y1 - ym) < r90 * r90) {
                        g.setColor(Color.red);
                        if (i2 % 2 == 0) {
                            g.drawOval(x1 - rSun, y1 - rSun, 2 * rSun, 2 * rSun);
                        }
                        else {
                            g.drawOval(x1 - 1, y1 - 1, 2, 2);
                        }
                        if (i2 % 2 == 0) {
                            g.drawString(String.valueOf(i2 / 2), x1, y1 + 10);
                        }
                    }
                }
                else if (i2 % 2 == 0) {
                    g.setColor(Color.red);
                    g.drawOval(x1 - rSun, y1 - rSun, 2 * rSun, 2 * rSun);
                    if (i2 != 48) {
                        g.drawString(String.valueOf(i2 / 2), x1, y1 + 12);
                    }
                    else {
                        g.drawString("24", x1, y1 - 10);
                    }
                }
            }
            g.setColor(Color.red);
            for (int i3 = 0; i3 < 48; ++i3) {
                if (this.isZoom) {
                    g.drawLine(this.xArray[i3], this.yArray[i3], this.xArray[i3 + 1], this.yArray[i3 + 1]);
                }
                else if ((this.xArray[i3] - xm) * (this.xArray[i3] - xm) + (this.yArray[i3] - ym) * (this.yArray[i3] - ym) < r90 * r90) {
                    g.drawLine(this.xArray[i3], this.yArray[i3], this.xArray[i3 + 1], this.yArray[i3 + 1]);
                    if (i3 >= 1) {
                        g.drawLine(this.xArray[i3 - 1], this.yArray[i3 - 1], this.xArray[i3], this.yArray[i3]);
                    }
                }
            }
            if (this.JanJun) {
                final int[][] x_Array = new int[7][49];
                final int[][] y_Array = new int[7][49];
                for (int m2 = 0; m2 <= 6; ++m2) {
                    final Date dat3 = new Date();
                    dat3.setDate(21);
                    dat3.setSeconds(0);
                    dat3.setMonth(11 + m2);
                    for (int i4 = 0; i4 < 49; ++i4) {
                        dat3.setHours(i4 / 2);
                        dat3.setMinutes(i4 * 30 % 60);
                        this.comp = new compute(dat3, this.latitude, this.longitude, this.locOffset);
                        elev = this.comp.elev();
                        azim = this.comp.azimuth();
                        this.xArray[i4] = xm + (int)Math.round((90.0 - elev) * r90 / 90.0 * Math.sin(azim * 0.017453292519943295));
                        this.yArray[i4] = ym - (int)Math.round((90.0 - elev) * r90 / 90.0 * Math.cos(azim * 0.017453292519943295));
                        x_Array[m2][i4] = xm + (int)Math.round((90.0 - elev) * r90 / 90.0 * Math.sin(azim * 0.017453292519943295));
                        y_Array[m2][i4] = ym - (int)Math.round((90.0 - elev) * r90 / 90.0 * Math.cos(azim * 0.017453292519943295));
                    }
                    for (int i5 = 0; i5 < 47; ++i5) {
                        if (!this.isZoom) {
                            x1 = this.xArray[i5];
                            y1 = this.yArray[i5];
                            if ((x1 - xm) * (x1 - xm) + (y1 - ym) * (y1 - ym) < r90 * r90) {
                                g.setColor(Color.red);
                                g.drawLine(this.xArray[i5], this.yArray[i5], this.xArray[i5 + 1], this.yArray[i5 + 1]);
                            }
                        }
                    }
                }
                for (int i6 = 0; i6 < 49; ++i6) {
                    if (i6 % 4 == 0) {
                        for (int m3 = 0; m3 < 6; ++m3) {
                            x1 = x_Array[m3][i6];
                            y1 = y_Array[m3][i6];
                            if ((x1 - xm) * (x1 - xm) + (y1 - ym) * (y1 - ym) < r90 * r90) {
                                g.drawLine(x1, y1, x_Array[m3 + 1][i6], y_Array[m3 + 1][i6]);
                            }
                        }
                    }
                }
            }
        }
        if (this.isMoon) {
            myDat = new Date();
            myDat.setDate(this.date);
            myDat.setMonth(this.month);
            myDat.setYear(this.year);
            for (int i5 = 0; i5 < 49; ++i5) {
                myDat.setHours(i5 / 2);
                myDat.setMinutes(i5 * 30 % 60);
                myDat.setSeconds(0);
                this.myMoon = new Moon(myDat, this.latitude, -this.longitude, this.locOffset);
                this.moonH = this.myMoon.moon_H();
                final MoonDistance myMoonDistance2 = new MoonDistance(this.date, this.month + 1, this.year, 0.5 * i5 - this.locOffset);
                final double moonDistance2 = myMoonDistance2.compute();
                final double horParal2 = 8.794 / (moonDistance2 / 1.4959787E8);
                double paral2 = Math.cos(0.017453292519943295 * this.moonH) * Math.sin(0.017453292519943295 * horParal2 / 3600.0);
                paral2 = Math.asin(paral2) / 0.017453292519943295;
                this.moonH -= paral2;
                this.moonAz = this.myMoon.moon_Az();
                x1 = xm + (int)Math.round((90.0 - this.moonH) * r90 / 90.0 * Math.sin(this.moonAz * 0.017453292519943295));
                y1 = ym - (int)Math.round((90.0 - this.moonH) * r90 / 90.0 * Math.cos(this.moonAz * 0.017453292519943295));
                this.xArray[i5] = x1;
                this.yArray[i5] = y1;
                if (!this.isZoom) {
                    if ((x1 - xm) * (x1 - xm) + (y1 - ym) * (y1 - ym) < r90 * r90) {
                        g.setColor(Color.blue);
                        if (i5 % 2 == 0) {
                            g.drawOval(x1 - rSun, y1 - rSun, 2 * rSun, 2 * rSun);
                        }
                        else {
                            g.drawOval(x1 - 1, y1 - 1, 2, 2);
                        }
                        if (i5 % 2 == 0) {
                            g.drawString(String.valueOf(i5 / 2), x1, y1 + 10);
                        }
                    }
                }
                else if (i5 % 2 == 0) {
                    g.setColor(Color.blue);
                    g.drawOval(x1 - rSun, y1 - rSun, 2 * rSun, 2 * rSun);
                    g.drawString(String.valueOf(i5 / 2), x1, y1 + 12);
                }
            }
            g.setColor(Color.blue);
            for (int i7 = 0; i7 < 48; ++i7) {
                if (this.isZoom) {
                    g.drawLine(this.xArray[i7], this.yArray[i7], this.xArray[i7 + 1], this.yArray[i7 + 1]);
                }
                else if ((this.xArray[i7] - xm) * (this.xArray[i7] - xm) + (this.yArray[i7] - ym) * (this.yArray[i7] - ym) < r90 * r90) {
                    g.drawLine(this.xArray[i7], this.yArray[i7], this.xArray[i7 + 1], this.yArray[i7 + 1]);
                    if (i7 >= 1) {
                        g.drawLine(this.xArray[i7 - 1], this.yArray[i7 - 1], this.xArray[i7], this.yArray[i7]);
                    }
                }
            }
        }
        g.setColor(Color.black);
        if (this.demo) {
            this.userString = "demo running";
        }
        else {
            this.userString = this.email;
        }
        if (this.online) {
            this.userString = "running online";
        }
        g.drawString(this.userString, 30, yy - 30);
        if (this.demo) {
            final Font f = g.getFont();
            g.setFont(new Font("Chicago", 0, 96));
            g.setColor(Color.red);
            g.drawString("D E M O", 170, 250);
            g.setFont(f);
        }
        if (this.analemmaOK) {
            g.setColor(Color.red);
            int k2 = 0;
            for (int m4 = 0; m4 < 12; ++m4) {
                for (int d = 1; d <= this.daysInMonth(m4, this.year); ++d) {
                    final double jd = this.comp.Jul_Date(d, m4 + 1, this.year + 1900, UT);
                    final double decAna = this.comp.sunDecRA(1, jd);
                    final double r_a = this.comp.sunDecRA(2, jd);
                    final double ghaAna = this.comp.sunGHA(d, m4 + 1, this.year + 1900, UT, r_a);
                    final double hAna = this.comp.computeHeight(decAna, this.latitude, -this.longitude, ghaAna);
                    final double azAna = this.comp.computeAzimut(decAna, this.latitude, this.longitude, ghaAna, hAna);
                    this.analemmaX[k2] = xm + (int)Math.round((90.0 - hAna) * r90 / 90.0 * Math.sin(azAna * 0.017453292519943295));
                    this.analemmaY[k2] = ym - (int)Math.round((90.0 - hAna) * r90 / 90.0 * Math.cos(azAna * 0.017453292519943295));
                    ++k2;
                }
            }
            for (int i8 = 0; i8 < k2 - 1; ++i8) {
                if ((this.analemmaX[i8] - xm) * (this.analemmaX[i8] - xm) + (this.analemmaY[i8] - ym) * (this.analemmaY[i8] - ym) < r90 * r90 || this.isZoom) {
                    g.drawLine(this.analemmaX[i8], this.analemmaY[i8], this.analemmaX[i8 + 1], this.analemmaY[i8 + 1]);
                }
            }
        }
        g.setFont(new Font("Courier", 0, 10));
        str = String.valueOf(this.versStr) + " - © 1999-2010 Juergen Giesen - www.GeoAstro.de";
        g.drawString(str, 10, yy - 5);
    }
    
    public boolean action(final Event event, final Object eventobject) {
        final double oldLat = this.latitude;
        final double oldLong = this.longitude;
        if (event.target instanceof Checkbox && event.target == this.zoomBox) {
            this.isZoom ^= true;
            this.repaint();
            return true;
        }
        if (event.target instanceof Button) {
            if (event.target == this.aboutButton) {
                final Dialog AboutDialog = new Aboutdialog(this, this.versStr, this.demo);
                AboutDialog.resize(350, 230);
                AboutDialog.show();
            }
            if (event.target == this.button) {
                this.dat = new Date();
                this.hours = this.dat.getHours();
                this.hoursChoice.select(this.hours);
                this.minutes = this.dat.getMinutes();
                this.minChoice.select(this.minutes);
                this.date = this.dat.getDate();
                this.dateChoice.select(this.date - 1);
                this.month = this.dat.getMonth();
                this.monthChoice.select(this.month);
                this.year = this.dat.getYear();
                this.yearStr = String.valueOf(this.year + 1900);
                this.yearChoice.select(this.yearStr);
                this.repaint();
            }
        }
        if (event.target instanceof TextField) {
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
        }
        if (event.target instanceof Choice) {
            if (event.target == this.detailsChoice) {
                if (this.detailsChoice.getSelectedItem().equals("Sun on/off")) {
                    this.isSun ^= true;
                }
                if (this.detailsChoice.getSelectedItem().equals("Moon on/off")) {
                    this.isMoon ^= true;
                }
                if (this.detailsChoice.getSelectedItem().equals("Dec-Jun on/off")) {
                    this.JanJun ^= true;
                    this.isMoon = false;
                    this.isSun = true;
                }
                if (this.detailsChoice.getSelectedItem().equals("Analemma on/off")) {
                    this.analemmaOK ^= true;
                }
                if (this.detailsChoice.getSelectedItem().equals("Sector on/off")) {
                    this.sector ^= true;
                }
                this.repaint();
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
                this.locOffset = this.loc.getTimeZone(this.timeString);
                this.timeChoice.select(this.timeString);
                this.repaint();
            }
            if (event.target == this.hoursChoice) {
                this.hoursStr = this.hoursChoice.getSelectedItem();
                this.hours = Integer.parseInt(this.hoursStr);
                this.hoursChoice.select(this.hoursStr);
                this.dat.setHours(this.hours);
                this.dat.setSeconds(0);
                this.repaint();
            }
            if (event.target == this.minChoice) {
                this.minStr = this.minChoice.getSelectedItem();
                this.minutes = Integer.parseInt(this.minStr);
                this.minChoice.select(this.minStr);
                this.dat.setMinutes(this.minutes);
                this.dat.setSeconds(0);
                this.repaint();
            }
            if (event.target == this.dateChoice) {
                this.dateStr = this.dateChoice.getSelectedItem();
                this.date = Integer.parseInt(this.dateStr);
                this.dat.setDate(this.date);
                this.dat.setSeconds(0);
                final int n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = n;
                    this.dat.setDate(this.date);
                    this.dateChoice.select(this.date - 1);
                }
                this.repaint();
            }
            if (event.target == this.monthChoice) {
                this.monthStr = this.monthChoice.getSelectedItem();
                this.monthChoice.select(this.monthStr);
                this.month = this.MonthInteger(this.monthStr);
                this.dat.setMonth(this.month);
                this.dat.setSeconds(0);
                final int n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = n;
                    this.dat.setDate(this.date);
                    this.dateChoice.select(this.date - 1);
                }
                this.repaint();
            }
            if (event.target == this.yearChoice) {
                this.yearStr = this.yearChoice.getSelectedItem();
                this.year = Integer.parseInt(this.yearStr);
                this.yearChoice.select(this.yearStr);
                this.year -= 1900;
                this.dat.setYear(this.year);
                final int n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = n;
                    this.dat.setDate(this.date);
                    this.dateChoice.select(this.date - 1);
                }
                this.locOffset = -this.dat.getTimezoneOffset() / 60;
                this.repaint();
                this.minMoonRiseAz = 400.0;
                this.maxMoonRiseAz = 0.0;
            }
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int code) {
        int n = 0;
        final int cmd = 0;
        if (code != 110 && code != 100 && code != 78 && code != 100 && code != 109 && code != 72 && code != 104 && code != 68 && code != 77 && code != 89 && code != 121) {
            return false;
        }
        if (code == 89 || code == 121) {
            if (code == 121) {
                ++this.year;
            }
            if (code == 89) {
                --this.year;
            }
            this.dat.setYear(this.year);
            this.yearStr = String.valueOf(this.year + 1900);
            this.yearChoice.select(this.yearStr);
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
            this.dat.setMinutes(this.minutes);
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
                this.dat.setHours(this.hours);
                this.hoursChoice.select(this.hours);
                if (this.date == 0) {
                    --this.month;
                    this.date = this.daysInMonth(this.month, this.year);
                    this.dat.setDate(this.date);
                    this.dat.setMonth(this.month);
                    this.monthChoice.select(this.month);
                }
                this.dateChoice.select(this.date - 1);
            }
            this.dat.setMinutes(this.minutes);
            this.minChoice.select(this.minutes);
            this.repaint();
            return true;
        }
        if (code == 72 || code == 104) {
            if (code == 72) {
                --this.hours;
                if (this.hours < 0) {
                    this.hours += 24;
                    --this.date;
                }
            }
            else {
                ++this.hours;
            }
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
                        this.dat.setYear(this.year);
                    }
                    this.dat.setMonth(this.month);
                    this.monthChoice.select(this.month);
                }
                this.dateChoice.select(this.date - 1);
            }
            this.dat.setHours(this.hours);
            this.dat.setSeconds(0);
            this.dat.setDate(this.date);
            this.hoursStr = String.valueOf(this.hours);
            if (this.hours < 10) {
                this.hoursStr = "0" + this.hoursStr;
            }
            this.hoursChoice.select(this.hours);
            this.date = this.dat.getDate();
            this.dateChoice.select(this.date - 1);
            this.month = this.dat.getMonth();
            this.monthChoice.select(this.month);
            this.dateChoice.select(this.date - 1);
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
            this.dat.setSeconds(0);
            this.dat.setDate(this.date);
            this.yearStr = String.valueOf(this.year + 1900);
            this.yearChoice.select(this.yearStr);
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
            this.dat.setMonth(this.month);
            this.date = this.dat.getDate();
            n = this.daysInMonth(this.month, this.year);
            if (this.date > n) {
                this.date = 1;
                this.dat.setDate(this.date);
                this.dat.setMonth(this.month - 1);
            }
            this.month = this.dat.getMonth();
            this.monthChoice.select(this.month);
            this.dateChoice.select(this.date - 1);
            this.year = this.dat.getYear();
            this.yearStr = String.valueOf(this.year + 1900);
            this.yearChoice.select(this.yearStr);
            this.dat.setSeconds(0);
            this.repaint();
            return true;
        }
        return false;
    }
    
    public SunMoonPol34() {
        this.versStr = "Sun & Moon Polar 3.4";
        this.dayArray = new String[7];
        this.monthArray = new String[12];
        this.online = false;
        this.demo = true;
        this.usrLat = 0.0;
        this.usrLong = 0.0;
        this.isZoom = false;
        this.isMoon = true;
        this.isSun = true;
        this.moonriseStr = "";
        this.moonsetStr = "";
        this.minMoonRiseAz = 400.0;
        this.maxMoonRiseAz = 0.0;
        this.JanJun = false;
        this.analemmaOK = true;
        this.analemmaX = new int[370];
        this.analemmaY = new int[370];
        this.xArray = new int[51];
        this.yArray = new int[51];
        this.sector = true;
    }
}
