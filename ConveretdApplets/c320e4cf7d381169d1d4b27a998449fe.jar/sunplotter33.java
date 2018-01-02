import java.awt.Cursor;
import java.awt.Event;
import java.util.Map;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Button;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class sunplotter33 extends Applet
{
    final char deg = '°';
    final double K = 0.017453292519943295;
    Date dat;
    int year;
    int month;
    int date;
    int hours;
    int minutes;
    public double latitude;
    public double longitude;
    double GHA;
    double dec;
    double hoehe;
    double azimut;
    double UT;
    int browserOffset;
    public int locOffset;
    String versStr;
    public sunloc loc;
    public sunloc time;
    Button button;
    public String email;
    public String param;
    public String wwwStr;
    public String locString;
    public String timeString;
    TextField fieldLatDeg;
    TextField fieldLongDeg;
    Choice locChoice;
    Choice timeChoice;
    Choice hoursChoice;
    Choice minChoice;
    Choice dateChoice;
    Choice monthChoice;
    Choice yearChoice;
    Choice ewChoice;
    Choice nsChoice;
    Checkbox box;
    Checkbox textBox;
    String hoursStr;
    String minStr;
    String dateStr;
    String monthStr;
    String yearStr;
    String ewStr;
    String nsStr;
    String latStr;
    String longStr;
    String[] monthArray;
    public compute comp;
    String str;
    boolean online;
    String usrStr;
    String homeString;
    String homeLatStr;
    String homeLongStr;
    double homeLat;
    double homeLong;
    boolean demo;
    String userString;
    double usrLat;
    double usrLong;
    final int radius = 250;
    final int xm = 320;
    final int ym = 460;
    Rise_Set rs;
    EOT eot;
    double equation;
    double longitudeOffset;
    final double EPS = 23.4393;
    double sunAngle;
    Checkbox LHABox;
    Checkbox azBox;
    Font f;
    Font f1;
    Color green;
    double trans;
    double steigTB;
    double rTagbogen;
    double xmT;
    double ymT;
    double STW;
    int rTB;
    int xM;
    int yM;
    int wStart;
    boolean isAz;
    boolean isLHA;
    boolean isText;
    double X1;
    double X2;
    double Y1;
    double Y2;
    double XS;
    double YS;
    double xSUN;
    double ySUN;
    double S;
    double SX;
    double SY;
    int x;
    int y;
    int x1;
    int y1;
    int x2;
    int y2;
    int xLat;
    int yLat;
    Choice detailChoice;
    boolean isAlt;
    boolean isDec;
    boolean isEcl;
    boolean isRise;
    boolean isDegrees;
    boolean isData;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 4171;
    }
    
    public void init() {
        this.userString = this.getParameter("email");
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
        Label L = new Label("Time Zone");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("Location");
        gbl.setConstraints(L, gbc);
        this.add(L);
        gbc.weightx = 10.0;
        L = new Label("Lat.: xx.xx");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("N / S");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("Long.: xx.xx");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("E / W");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("Details");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("LHA");
        gbc.insets = new Insets(2, 0, 0, 0);
        L.setBackground(Color.magenta);
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("Az");
        gbc.insets = new Insets(2, 0, 0, 10);
        L.setBackground(Color.red);
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
        gbl.setConstraints(this.fieldLatDeg = new TextField(this.latStr, 4), gbc);
        this.add(this.fieldLatDeg);
        (this.nsChoice = new Choice()).addItem("N");
        this.nsChoice.addItem("S");
        this.nsChoice.select(this.nsStr);
        gbl.setConstraints(this.nsChoice, gbc);
        this.add(this.nsChoice);
        this.longStr = String.valueOf(Math.abs(this.longitude));
        gbl.setConstraints(this.fieldLongDeg = new TextField(this.longStr, 5), gbc);
        this.add(this.fieldLongDeg);
        (this.ewChoice = new Choice()).addItem("E");
        this.ewChoice.addItem("W");
        this.ewChoice.select(this.ewStr);
        gbl.setConstraints(this.ewChoice, gbc);
        this.add(this.ewChoice);
        (this.detailChoice = new Choice()).addItem("Select:");
        this.detailChoice.addItem("Text off");
        this.detailChoice.addItem("Altitude off");
        this.detailChoice.addItem("Declination off");
        this.detailChoice.addItem("Ecliptic off");
        this.detailChoice.addItem("Degrees off");
        this.detailChoice.addItem("Data off");
        this.detailChoice.addItem("Rise/Set");
        gbl.setConstraints(this.detailChoice, gbc);
        this.add(this.detailChoice);
        this.LHABox = new Checkbox("");
        gbc.insets = new Insets(0, 0, 0, 10);
        gbl.setConstraints(this.LHABox, gbc);
        this.LHABox.setState(this.isLHA);
        this.add(this.LHABox);
        this.azBox = new Checkbox("");
        gbc.insets = new Insets(0, 0, 0, 10);
        gbl.setConstraints(this.azBox, gbc);
        this.azBox.setState(this.isAz);
        this.add(this.azBox);
        gbc.gridy = 2;
        L = new Label("Standard Time:");
        gbc.insets = new Insets(5, 20, 0, 0);
        gbl.setConstraints(L, gbc);
        this.add(L);
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
        L = new Label("Date :");
        gbl.setConstraints(L, gbc);
        this.add(L);
        gbc.insets = new Insets(5, 0, 0, 10);
        this.yearChoice = new Choice();
        for (int m = 0; m < 12; ++m) {
            this.yearStr = String.valueOf(this.year + 1900 - 5 + m);
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
        (this.button = new Button()).setLabel("now");
        gbc.insets = new Insets(5, 0, 0, 10);
        gbl.setConstraints(this.button, gbc);
        this.add(this.button);
        gbc.gridy = 3;
        gbc.weighty = 160.0;
        final Canvas can = new Canvas();
        gbl.setConstraints(can, gbc);
        this.add(can);
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        final int xy = this.formula(this.wwwStr, 27);
        if (this.formula(this.wwwStr, 22) == this.formula("http://www.geoastro.de", 22) || this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 20) == this.formula("http://www.SciAm.com", 20)) {
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
                this.demo = true;
            }
        }
        this.repaint();
    }
    
    public double longOffset() {
        if (this.longitude >= -7.5) {
            final double delta = (this.longitude + 7.5) * 4.0;
        }
        else {
            final double delta = (this.longitude - 7.5) * 4.0;
        }
        return this.locOffset * 60 + this.longitude * 4.0;
    }
    
    public String makeTimeString(double time) {
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
        if (min > 9) {
            str = ":";
        }
        else {
            str = ":0";
        }
        str = String.valueOf(String.valueOf((int)time) + str + min);
        if (time < 10.0) {
            str = "0" + str;
        }
        return str;
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
    
    public double kreisSchnitt(final int what, final double xm, final double ym, final double r, final double m, final double b) {
        final double A = 1.0 + m * m;
        final double B = xm - m * b + m * ym;
        final double C = r * r - xm * xm - b * b + 2.0 * ym * b - ym * ym;
        final double x = B / A + Math.sqrt(C / A + B * B / (A * A));
        if (what == 1) {
            return x;
        }
        return m * x + b;
    }
    
    public String eotString(double x) {
        final double diff = Math.abs(x - (int)x);
        int min = (int)Math.round(diff * 60.0);
        if (min == 60) {
            min = 0;
            if (x >= 0.0) {
                ++x;
            }
            else {
                --x;
            }
        }
        String str;
        if (min > 9) {
            str = ":";
        }
        else {
            str = ":0";
        }
        str = String.valueOf((int)x) + str + min;
        if (x >= 0.0) {
            str = "+" + str;
        }
        if (x < 0.0 && (int)x == 0) {
            str = "-" + str;
        }
        if (Math.abs(x) < 10.0) {
            str = " " + str;
        }
        return str;
    }
    
    public void doRise() {
        final Rise_Set rs = new Rise_Set(this.dat, this.latitude, -this.longitude, this.locOffset, 0.0);
        final double t = rs.h_rise();
        this.hours = (int)t;
        this.minutes = (int)Math.round(60.0 * this.comp.frac(t));
        if (this.minutes == 60) {
            this.minutes = 0;
            ++this.hours;
        }
        this.dat.setHours(this.hours);
        this.dat.setMinutes(this.minutes);
        this.hoursChoice.select(this.hours);
        this.minChoice.select(this.minutes);
    }
    
    public void menu(final int item, final boolean tf) {
        final String[] str = new String[10];
        final int n = this.detailChoice.getItemCount();
        for (int i = 0; i < n; ++i) {
            str[i] = this.detailChoice.getItem(i);
        }
        for (int j = n - 1; j > 0; --j) {
            this.detailChoice.remove(j);
        }
        if (item == 1) {
            if (tf) {
                this.detailChoice.addItem("Text on");
            }
            else {
                this.detailChoice.addItem("Text off");
            }
            this.detailChoice.addItem(str[2]);
            this.detailChoice.addItem(str[3]);
            this.detailChoice.addItem(str[4]);
            this.detailChoice.addItem(str[5]);
            this.detailChoice.addItem(str[6]);
            this.detailChoice.addItem(str[7]);
        }
        else if (item == 2) {
            this.detailChoice.addItem(str[1]);
            if (tf) {
                this.detailChoice.addItem("Altitude on");
            }
            else {
                this.detailChoice.addItem("Altitude off");
            }
            this.detailChoice.addItem(str[3]);
            this.detailChoice.addItem(str[4]);
            this.detailChoice.addItem(str[5]);
            this.detailChoice.addItem(str[6]);
            this.detailChoice.addItem(str[7]);
        }
        else if (item == 3) {
            this.detailChoice.addItem(str[1]);
            this.detailChoice.addItem(str[2]);
            if (tf) {
                this.detailChoice.addItem("Declination on");
            }
            else {
                this.detailChoice.addItem("Declination off");
            }
            this.detailChoice.addItem(str[4]);
            this.detailChoice.addItem(str[5]);
            this.detailChoice.addItem(str[6]);
            this.detailChoice.addItem(str[7]);
        }
        else if (item == 4) {
            this.detailChoice.addItem(str[1]);
            this.detailChoice.addItem(str[2]);
            this.detailChoice.addItem(str[3]);
            if (tf) {
                this.detailChoice.addItem("Ecliptic on");
            }
            else {
                this.detailChoice.addItem("Ecliptic off");
            }
            this.detailChoice.addItem(str[5]);
            this.detailChoice.addItem(str[6]);
            this.detailChoice.addItem(str[7]);
        }
        else if (item == 5) {
            this.detailChoice.addItem(str[1]);
            this.detailChoice.addItem(str[2]);
            this.detailChoice.addItem(str[3]);
            this.detailChoice.addItem(str[4]);
            if (tf) {
                this.detailChoice.addItem("Degrees on");
            }
            else {
                this.detailChoice.addItem("Degrees off");
            }
            this.detailChoice.addItem(str[6]);
            this.detailChoice.addItem(str[7]);
        }
        else if (item == 6) {
            this.detailChoice.addItem(str[1]);
            this.detailChoice.addItem(str[2]);
            this.detailChoice.addItem(str[3]);
            this.detailChoice.addItem(str[4]);
            this.detailChoice.addItem(str[5]);
            if (tf) {
                this.detailChoice.addItem("Data on");
            }
            else {
                this.detailChoice.addItem("Data off");
            }
            this.detailChoice.addItem(str[7]);
        }
    }
    
    public void paint(final Graphics g) {
        final RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        renderHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        final Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHints(renderHints);
        g.setColor(Color.white);
        g.fillRect(0, 0, 800, 800);
        g.setColor(Color.black);
        this.UT = this.hours - this.locOffset + this.minutes / 60.0;
        this.eot = new EOT(this.date, this.month + 1, this.year + 1900, this.UT);
        this.comp = new compute(this.dat, this.latitude, this.longitude, this.locOffset);
        this.equation = this.eot.eotValue();
        double azim = this.comp.azimuth();
        g.setFont(this.f);
        this.longitudeOffset = this.longOffset();
        final double az = 0.0;
        this.trans = 12.0 - this.equation / 60.0 + this.longitudeOffset / 60.0;
        this.sunAngle = (this.hours + this.minutes / 60.0 - this.trans) * 15.0;
        final int left = 610;
        g.setFont(this.f1);
        g.drawString("Equ. of Time " + this.eotString(this.equation) + " m", left, 240);
        this.hoehe = this.comp.elev();
        final double JD = this.comp.Jul_Date(this.date, this.month + 1, this.year + 1900, this.UT);
        this.comp.sun(JD);
        this.GHA = this.comp.sun_GHA(this.date, this.month + 1, this.year + 1900, this.UT);
        azim = this.comp.azimuth();
        this.dec = this.eot.DecRA(1, (JD - 2451545.0) / 36525.0);
        g.setColor(Color.red);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        if (this.isText) {
            g.setFont(this.f1);
            g.drawString("North", 595, 460);
            g.drawString("South", 15, 460);
            g.setFont(this.f);
        }
        g.setColor(new Color(150, 150, 255));
        g.fillOval(60, 200, 520, 520);
        g.setColor(new Color(235, 235, 255));
        g.fillOval(70, 210, 500, 500);
        if (this.latitude >= 0.0) {
            g.setColor(Color.lightGray);
            g.fillArc(70, 210, 500, 500, 180, 180);
            if (this.isText) {
                g.setColor(Color.darkGray);
                g.setFont(this.f1);
                g.drawString("Night", 306, 690);
                g.setFont(this.f);
            }
        }
        else {
            g.setColor(Color.lightGray);
            g.fillArc(70, 210, 500, 500, 180, -180);
            if (this.isText) {
                g.setColor(Color.darkGray);
                g.setFont(this.f1);
                g.drawString("Night", 306, 410);
                g.setFont(this.f);
            }
        }
        g.setColor(Color.darkGray);
        if (this.isText) {
            g.drawLine(320, 210, 320, 710);
        }
        g.setColor(Color.blue);
        g.drawOval(70, 210, 500, 500);
        g.drawOval(60, 200, 520, 520);
        if (this.isDegrees) {
            g.setColor(Color.red);
            for (int k = 0; k <= 18; ++k) {
                final int n = k * 5;
                int LL;
                if (k % 2 == 0) {
                    LL = 15;
                    g.setColor(Color.red);
                }
                else {
                    g.setColor(Color.black);
                    LL = 10;
                }
                this.x1 = 320 + (int)Math.round(250.0 * Math.cos(0.017453292519943295 * n));
                this.y1 = 460 - (int)Math.round(250.0 * Math.sin(0.017453292519943295 * n));
                this.x2 = 320 + (int)Math.round((250 + LL) * Math.cos(0.017453292519943295 * n));
                this.y2 = 460 - (int)Math.round((250 + LL) * Math.sin(0.017453292519943295 * n));
                g.drawLine(this.x1, this.y1, this.x2, this.y2);
                if (k == 6 || k == 12) {
                    g.drawString(new StringBuffer().append(k * 5).append('°').toString(), this.x2, this.y2);
                }
            }
            int n = 0;
            int L1 = 10;
            while (90.0 - this.latitude + n * 5 < 90.0) {
                int LL;
                if (n % 2 == 0) {
                    LL = 19;
                    g.setColor(Color.red);
                }
                else {
                    g.setColor(Color.black);
                    LL = 14;
                }
                this.x1 = 320 - (int)Math.round((250 + L1) * Math.cos(0.017453292519943295 * (90.0 - this.latitude + n * 5)));
                this.y1 = 460 - (int)Math.round((250 + L1) * Math.sin(0.017453292519943295 * (90.0 - this.latitude + n * 5)));
                this.x2 = 320 - (int)Math.round((250 + LL) * Math.cos(0.017453292519943295 * (90.0 - this.latitude + n * 5)));
                this.y2 = 460 - (int)Math.round((250 + LL) * Math.sin(0.017453292519943295 * (90.0 - this.latitude + n * 5)));
                g.drawLine(this.x1, this.y1, this.x2, this.y2);
                ++n;
            }
            for (n = 0; 90.0 - this.latitude + n * 5 > 0.0; --n) {
                int LL;
                if (n % 2 == 0) {
                    LL = 19;
                    g.setColor(Color.red);
                }
                else {
                    g.setColor(Color.black);
                    LL = 14;
                }
                this.x1 = 320 - (int)Math.round((250 + L1) * Math.cos(0.017453292519943295 * (90.0 - this.latitude + n * 5)));
                this.y1 = 460 - (int)Math.round((250 + L1) * Math.sin(0.017453292519943295 * (90.0 - this.latitude + n * 5)));
                this.x2 = 320 - (int)Math.round((250 + LL) * Math.cos(0.017453292519943295 * (90.0 - this.latitude + n * 5)));
                this.y2 = 460 - (int)Math.round((250 + LL) * Math.sin(0.017453292519943295 * (90.0 - this.latitude + n * 5)));
                g.drawLine(this.x1, this.y1, this.x2, this.y2);
                if (n == 0) {
                    g.drawString("0°", this.x2, this.y2);
                }
            }
            n = 0;
            L1 = 0;
            while (n * 5 < 90) {
                int LL;
                if (n % 2 == 0) {
                    LL = -9;
                    g.setColor(Color.red);
                }
                else {
                    g.setColor(Color.black);
                    LL = -4;
                }
                this.x1 = 320 - (int)Math.round((250 + L1) * Math.cos(0.017453292519943295 * n * 5.0));
                this.y1 = 460 - (int)Math.round((250 + L1) * Math.sin(0.017453292519943295 * n * 5.0));
                this.x2 = 320 - (int)Math.round((250 + LL) * Math.cos(0.017453292519943295 * n * 5.0));
                this.y2 = 460 - (int)Math.round((250 + LL) * Math.sin(0.017453292519943295 * n * 5.0));
                g.drawLine(this.x1, this.y1, this.x2, this.y2);
                ++n;
            }
            g.drawString("0°", 75, 455);
            g.drawString("0°", 555, 455);
        }
        g.setColor(Color.black);
        g.drawLine(70, 460, 570, 460);
        if (this.isText) {
            g.setColor(Color.black);
            g.setFont(this.f1);
            g.drawString("Horizon", 95, 455);
        }
        g.setFont(this.f);
        this.xLat = 320 + (int)Math.round(250.0 * Math.cos(0.017453292519943295 * this.latitude));
        this.yLat = 460 - (int)Math.round(250.0 * Math.sin(0.017453292519943295 * this.latitude));
        g.setColor(Color.red);
        g.drawLine(320, 460, this.xLat, this.yLat);
        if (this.isText) {
            g.setFont(this.f1);
            if (this.latitude > 0.0) {
                g.drawString("North Pole", this.xLat + 5, this.yLat - 20);
                this.xLat = 320 - (int)Math.round(250.0 * Math.cos(0.017453292519943295 * this.latitude));
                this.yLat = 460 + (int)Math.round(250.0 * Math.sin(0.017453292519943295 * this.latitude));
                g.drawString("South Pole", this.xLat - 90, this.yLat + 15);
                g.setColor(Color.black);
                g.drawString("Zenith", 300, 190);
                g.drawString("Nadir", 300, 735);
            }
            else {
                g.drawString("South Pole", this.xLat + 20, this.yLat);
                g.drawString("Zenith", 300, 700);
            }
            g.drawArc(170, 310, 300, 300, 0, (int)Math.round(this.latitude));
            g.drawString("Latit.", 320 + (int)Math.round(160.0 * Math.cos(0.008726646259971648 * this.latitude)), 460 - (int)Math.round(160.0 * Math.sin(0.008726646259971648 * this.latitude)));
            g.setFont(this.f);
        }
        this.xLat = 320 - (int)Math.round(250.0 * Math.cos(0.017453292519943295 * this.latitude));
        this.yLat = 460 + (int)Math.round(250.0 * Math.sin(0.017453292519943295 * this.latitude));
        g.drawLine(320, 460, this.xLat, this.yLat);
        this.x1 = 320 + (int)Math.round(250.0 * Math.cos(0.017453292519943295 * (this.latitude - 90.0)));
        this.y1 = 460 - (int)Math.round(250.0 * Math.sin(0.017453292519943295 * (this.latitude - 90.0)));
        this.x2 = 320 - (int)Math.round(250.0 * Math.cos(0.017453292519943295 * (this.latitude - 90.0)));
        this.y2 = 460 + (int)Math.round(250.0 * Math.sin(0.017453292519943295 * (this.latitude - 90.0)));
        g.setColor(Color.blue);
        g.drawLine(this.x1, this.y1, this.x2, this.y2);
        if (this.isText) {
            g.setFont(this.f1);
            g2d.translate(320, 460);
            g2d.rotate(0.017453292519943295 * (90.0 - this.latitude));
            g2d.drawString("Equator  Equinox", 100, -10);
            g2d.rotate(-0.017453292519943295 * (90.0 - this.latitude));
            g2d.translate(-320, -460);
            g.drawString("Equator", this.x2 - 70, this.y2 - 20);
            g.setFont(this.f);
        }
        if (this.isEcl) {
            this.x1 = 320 + (int)Math.round(250.0 * Math.cos(0.017453292519943295 * (this.latitude - 90.0 - 23.4393)));
            this.y1 = 460 - (int)Math.round(250.0 * Math.sin(0.017453292519943295 * (this.latitude - 90.0 - 23.4393)));
            this.x2 = 320 - (int)Math.round(250.0 * Math.cos(0.017453292519943295 * (this.latitude - 90.0 - 23.4393)));
            this.y2 = 460 + (int)Math.round(250.0 * Math.sin(0.017453292519943295 * (this.latitude - 90.0 - 23.4393)));
            g.setColor(this.green);
            g.drawLine(this.x1, this.y1, this.x2, this.y2);
        }
        if (this.isText && this.isEcl) {
            g.setFont(this.f1);
            g.setColor(this.green);
            g.drawString("Ecliptic", this.x1 + 20, this.y1 + 10);
            this.x1 = 320 + (int)Math.round(250.0 * Math.cos(0.017453292519943295 * (this.latitude - 90.0 + 23.4393)));
            this.y1 = 460 - (int)Math.round(250.0 * Math.sin(0.017453292519943295 * (this.latitude - 90.0 + 23.4393)));
            this.x2 = 320 - (int)Math.round(250.0 * Math.cos(0.017453292519943295 * (this.latitude - 90.0 - 23.4393)));
            this.y2 = 460 + (int)Math.round(250.0 * Math.sin(0.017453292519943295 * (this.latitude - 90.0 - 23.4393)));
            g.drawLine(this.x1, this.y1, this.x2, this.y2);
            this.x1 = 320 + (int)Math.round(250.0 * Math.cos(0.017453292519943295 * (this.latitude - 90.0 - 23.4393)));
            this.y1 = 460 - (int)Math.round(250.0 * Math.sin(0.017453292519943295 * (this.latitude - 90.0 - 23.4393)));
            this.x2 = 320 - (int)Math.round(250.0 * Math.cos(0.017453292519943295 * (this.latitude - 90.0 + 23.4393)));
            this.y2 = 460 + (int)Math.round(250.0 * Math.sin(0.017453292519943295 * (this.latitude - 90.0 + 23.4393)));
            g.drawLine(this.x1, this.y1, this.x2, this.y2);
            g2d.translate(320, 460);
            g2d.rotate(0.017453292519943295 * (90.0 - this.latitude));
            g2d.drawString("Summer Solstice", 100, -(int)(250.0 * Math.sin(0.4090929593627069)) - 5);
            g2d.rotate(-0.017453292519943295 * (90.0 - this.latitude));
            g2d.rotate(0.017453292519943295 * (90.0 - this.latitude));
            g2d.drawString("Winter Solstice", 100, (int)(250.0 * Math.sin(0.4090929593627069)) + 15);
            g2d.rotate(-0.017453292519943295 * (90.0 - this.latitude));
            g2d.rotate(0.017453292519943295 * (90.0 - this.latitude + 23.4393));
            g2d.drawString("Ecliptic", -200, -5);
            g2d.rotate(-0.017453292519943295 * (90.0 - this.latitude + 23.4393));
            g2d.translate(-320, -460);
        }
        if (this.latitude == 0.0) {
            this.latitude = 0.001;
        }
        final double equSteig = Math.tan(0.017453292519943295 * (90.0 - this.latitude));
        this.X1 = 320.0 - 250.0 * Math.cos(0.017453292519943295 * (90.0 - this.latitude + this.dec));
        this.Y1 = 460.0 - 250.0 * Math.sin(0.017453292519943295 * (90.0 - this.latitude + this.dec));
        this.XS = this.X1;
        this.YS = this.Y1;
        final double m = equSteig;
        final double b = this.YS - m * this.XS - 460.0;
        final double q = (b * b + 102400.0 - 62500.0) / (1.0 + m * m);
        final double p = (m * b - 320.0) / (1.0 + m * m);
        this.X1 = -p + Math.sqrt(p * p - q);
        this.X2 = -p - Math.sqrt(p * p - q);
        this.x1 = (int)Math.round(this.X1);
        this.x2 = (int)Math.round(this.X2);
        this.Y1 = this.YS - m * (this.XS - this.X1);
        this.Y2 = this.YS - m * (this.XS - this.X2);
        this.y1 = (int)Math.round(this.Y1);
        this.y2 = (int)Math.round(this.Y2);
        double Declin = Math.atan2(460.0 - this.Y2, 320.0 - this.X2) / 0.017453292519943295;
        Declin = Declin - 90.0 + this.latitude;
        g.setColor(Color.blue);
        g.setFont(this.f1);
        g.drawString("Declin " + Math.round(10.0 * Declin) / 10.0 + '°', left, 160);
        if (this.isDec) {
            g.drawArc(195, 335, 250, 250, 180 - (int)Math.round(90.0 - this.latitude), -(int)Math.round(Declin));
            final int yd = 460 - (int)Math.round(115.0 * Math.sin(0.017453292519943295 * (90.0 + this.latitude - 0.5 * Declin)));
            final int xd = 320 + (int)Math.round(115.0 * Math.cos(0.017453292519943295 * (90.0 + this.latitude - 0.5 * Declin)));
            g.drawString("Decl", xd, yd);
        }
        if (this.isText && this.isEcl) {
            g.setColor(this.green);
            g.drawArc(220, 360, 200, 200, 180 - (int)Math.round(90.0 - this.latitude), -(int)Math.round(23.4393));
            final int yd = 460 - (int)Math.round(90.0 * Math.sin(0.017453292519943295 * (90.0 + this.latitude - 11.71965)));
            final int xd = 320 + (int)Math.round(90.0 * Math.cos(0.017453292519943295 * (90.0 + this.latitude - 11.71965)));
            g.drawString("23.4°", xd, yd);
        }
        g.setColor(Color.magenta);
        g.drawLine(this.x1, this.y1, this.x2, this.y2);
        if (this.isLHA) {
            this.rTagbogen = 0.5 * Math.sqrt((this.X1 - this.X2) * (this.X1 - this.X2) + (this.Y1 - this.Y2) * (this.Y1 - this.Y2));
            this.rTB = (int)Math.round(this.rTagbogen);
            this.xmT = 0.5 * (this.X1 + this.X2);
            this.xM = (int)Math.round(this.xmT);
            this.ymT = 0.5 * (this.Y1 + this.Y2);
            this.yM = (int)Math.round(this.ymT);
            this.wStart = -(int)Math.round(90.0 - this.latitude);
            g.drawArc(this.xM - this.rTB, this.yM - this.rTB, 2 * this.rTB, 2 * this.rTB, this.wStart, 180);
            for (int i = 0; i <= 12; ++i) {
                this.x = this.xM + (int)Math.round(this.rTagbogen * Math.cos(0.017453292519943295 * (90.0 + this.latitude - i * 15)));
                this.y = this.yM - (int)Math.round(this.rTagbogen * Math.sin(0.017453292519943295 * (90.0 + this.latitude - i * 15)));
                g.drawOval(this.x - 2, this.y - 2, 4, 4);
                if (this.UT > this.trans - this.locOffset) {
                    g.drawString(String.valueOf(12 + i), this.x, this.y);
                }
                else {
                    g.drawString(String.valueOf(12 - i), this.x, this.y);
                }
            }
            this.steigTB = Math.atan2(this.Y2 - this.Y1, this.X2 - this.X1) / 0.017453292519943295;
            this.steigTB = -1.0 / this.steigTB;
        }
        if (this.isText) {
            g.setFont(this.f1);
            g2d.translate(320, 460);
            g2d.rotate(0.017453292519943295 * (90.0 - this.latitude));
            g2d.drawString("Declin. Circle", -40, -(int)Math.round(250.0 * Math.sin(0.017453292519943295 * this.dec) + 5.0));
            g2d.drawString("Diurnal Arc", -40, -(int)Math.round(250.0 * Math.sin(0.017453292519943295 * this.dec)) + 10);
            g2d.rotate(-0.017453292519943295 * (90.0 - this.latitude));
            g2d.translate(-320, -460);
        }
        g.setFont(this.f);
        final double RADIUS = 0.5 * Math.sqrt((this.X1 - this.X2) * (this.X1 - this.X2) + (this.Y1 - this.Y2) * (this.Y1 - this.Y2));
        if (this.isData) {
            this.rs = new Rise_Set(this.dat, this.latitude, -this.longitude, this.locOffset, -0.8333);
            final int L2 = 30;
            g.setColor(Color.black);
            g.drawString("Stand. Time", L2, 118);
            g.drawString("Stand. Time", L2, 136);
            g.setColor(Color.red);
            g.drawString("Rise:", L2 + 85, 100);
            g.drawString(String.valueOf(this.rs.rise_String()) + " (-0.8°)", L2 + 85, 118);
            g.setColor(Color.blue);
            g.drawString("Set:", L2 + 180, 100);
            g.drawString(String.valueOf(this.rs.set_String()) + " (-0.8°)", L2 + 180, 118);
            g.setColor(Color.black);
            g.drawString("Trans.", L2 + 285, 100);
            g.drawString(this.makeTimeString(12.0 + this.equation / 60.0 - this.longitudeOffset / 60.0), L2 + 285, 118);
            g.drawString("Altit.:", L2 + 360, 100);
            g.drawString(new StringBuffer().append(Math.round(10.0 * this.hoehe) / 10.0).append('°').toString(), L2 + 360, 118);
            g.drawString("Azim:", L2 + 435, 100);
            g.drawString(new StringBuffer().append(Math.round(10.0 * azim) / 10.0).append('°').toString(), L2 + 435, 118);
            g.drawString("Decl:", L2 + 500, 100);
            g.drawString(new StringBuffer().append(Math.round(10.0 * this.dec) / 10.0).append('°').toString(), L2 + 500, 118);
            this.rs = new Rise_Set(this.dat, this.latitude, -this.longitude, this.locOffset, 0.0);
            g.drawString(String.valueOf(this.makeTimeString(this.rs.h_rise())) + " (0°)", L2 + 85, 136);
            g.drawString(String.valueOf(this.makeTimeString(this.rs.h_set())) + " (0°)", L2 + 180, 136);
            g.drawString(this.makeTimeString(12.0), L2 + 285, 136);
        }
        g.setFont(new Font("Courier", 0, 10));
        this.S = RADIUS - RADIUS * Math.cos(0.017453292519943295 * this.sunAngle);
        this.SX = this.S * Math.cos(Math.atan(equSteig));
        this.SY = this.S * Math.sin(Math.atan(equSteig));
        this.xSUN = this.X2 + this.SX;
        this.ySUN = this.Y2 + this.SY;
        this.x = (int)Math.round(this.xSUN);
        this.y = (int)Math.round(this.ySUN);
        if (this.hoehe > 0.0) {
            g.setColor(Color.yellow);
        }
        else {
            g.setColor(Color.pink);
        }
        g.fillOval(this.x - 8, this.y - 8, 16, 16);
        g.setColor(Color.red);
        g.drawOval(this.x - 8, this.y - 8, 16, 16);
        this.steigTB = Math.tan(0.017453292519943295 * (90.0 - this.latitude));
        this.steigTB = -1.0 / this.steigTB;
        final double B = -this.steigTB * this.xSUN + this.ySUN;
        final double xTB = this.kreisSchnitt(1, this.xmT, this.ymT, this.rTagbogen, this.steigTB, B);
        final double yTB = this.kreisSchnitt(2, this.xmT, this.ymT, this.rTagbogen, this.steigTB, B);
        final int tbX = (int)Math.round(xTB);
        final int tbY = (int)Math.round(yTB);
        if (this.isLHA) {
            g.setColor(Color.magenta);
            g.drawLine(this.x, this.y, tbX, tbY);
        }
        final int ys = (int)Math.round(-1.0 / this.steigTB * (xTB - this.xSUN) + this.ySUN);
        final int xtb = (int)Math.round(0.5 * (this.X1 + this.X2));
        final int ytb = (int)Math.round(0.5 * (this.Y1 + this.Y2));
        if (this.isLHA) {
            g.drawLine(tbX, tbY, xtb, ytb);
        }
        this.wStart = -(int)Math.round(90.0 - this.latitude);
        final double stundenwinkel = 180.0 + Math.atan2(yTB - 0.5 * (this.Y1 + this.Y2), xTB - 0.5 * (this.X1 + this.X2)) / 0.017453292519943295;
        this.STW = stundenwinkel - (90.0 - this.latitude);
        final int stw = (int)Math.round(this.STW);
        if (this.isLHA) {
            g.setFont(this.f1);
            if (this.UT > this.trans - this.locOffset) {
                g.drawArc(xtb - 75, ytb - 75, 150, 150, this.wStart + 180, -stw);
                g.drawString("Solar Time    " + this.makeTimeString(12.0 + this.STW / 15.0), left, 200);
                g.drawString("Local Hour Angle " + Math.round(10.0 * this.STW) / 10.0 + '°', left, 180);
                g.drawString("LHA", xtb + (int)Math.round(50.0 * Math.cos(0.017453292519943295 * (this.wStart + 180 - 0.5 * this.STW))), ytb - (int)Math.round(50.0 * Math.sin(0.017453292519943295 * (this.wStart + 180 - 0.5 * this.STW))));
            }
            else {
                g.drawArc(xtb - 75, ytb - 75, 150, 150, this.wStart + 180, 360 - stw);
                g.drawString("Solar Time    " + this.makeTimeString(12.0 - this.STW / 15.0), left, 200);
                g.drawString("Local Hour Angle " + Math.round(10.0 * (360.0 - this.STW)) / 10.0 + '°', left, 180);
                g.drawString("LHA", xtb - (int)Math.round(50.0 * Math.cos(0.017453292519943295 * (this.wStart - 0.5 * this.STW))), ytb - (int)Math.round(50.0 * Math.sin(0.017453292519943295 * (this.wStart - 0.5 * this.STW))));
            }
        }
        g.setFont(this.f1);
        g.setColor(Color.red);
        g.drawString("Standard Time " + this.makeTimeString(this.hours + this.minutes / 60.0), left, 220);
        final double XH = -Math.sqrt(62500.0 - (this.ySUN - 460.0) * (this.ySUN - 460.0)) + 320.0;
        final int xh = (int)Math.round(XH);
        g.drawLine(xh, this.y, 640 - xh, this.y);
        if (this.isText) {
            g.drawString("Altit. Circle", 330, this.y - 5);
        }
        final double alt = Math.atan2(460.0 - (this.Y2 + this.SY), 320.0 - XH) / 0.017453292519943295;
        g.setColor(Color.red);
        g.drawLine(320, 460, xh, this.y);
        if (this.isAlt) {
            g.drawString("Alt.", 320 - (int)Math.round(140.0 * Math.cos(0.008726646259971648 * alt)), 460 - (int)Math.round(140.0 * Math.sin(0.008726646259971648 * alt)));
            g.drawArc(170, 310, 300, 300, 180, -(int)Math.round(alt));
        }
        g.drawString("Altitude " + Math.round(100.0 * alt) / 100.0 + '°', left, 120);
        g.setColor(Color.magenta);
        g.drawLine(320, 460, this.x2, this.y2);
        if (this.isAz) {
            g.setColor(Color.red);
            final double rThales = 320.0 - XH;
            final int rT = (int)Math.round(rThales);
            g.drawArc(xh, this.y - rT, 2 * rT, 2 * rT, 180, -180);
            final int yT = this.y;
            for (int j = 0; j <= 12; ++j) {
                this.x1 = 320 + (int)Math.round(rThales * Math.cos(0.017453292519943295 * j * 15.0));
                this.y1 = yT - (int)Math.round(rThales * Math.sin(0.017453292519943295 * j * 15.0));
                g.drawOval(this.x1 - 2, this.y1 - 2, 4, 4);
                if (this.UT > this.trans - this.locOffset) {
                    g.drawString(new StringBuffer().append(360 - j * 15).append('°').toString(), this.x1, this.y1);
                }
                else {
                    g.drawString(new StringBuffer().append(180 - j * 15).append('°').toString(), this.x1, this.y1);
                }
            }
            final double YT = -Math.sqrt(rThales * rThales - (320.0 - this.xSUN) * (320.0 - this.xSUN)) + this.ySUN;
            final int yt = (int)Math.round(YT);
            g.drawLine(this.x, yt, this.x, this.y);
            g.drawLine(this.x, yt, 320, this.y);
            final double dy = Math.sqrt(rThales * rThales - (320.0 - this.xSUN) * (320.0 - this.xSUN));
            final double dx = 320.0 - this.xSUN;
            final double AZ = Math.atan2(dy, dx) / 0.017453292519943295;
            if (this.hours + this.minutes / 60.0 < this.trans) {
                g.drawString("Azimuth (E of N) " + Math.round(10.0 * (180.0 - AZ)) / 10.0 + '°', left, 140);
                g.drawArc(250, this.y - 70, 140, 140, 0, (int)Math.round(180.0 - AZ));
                g.drawString("Az", 320 + (int)Math.round(50.0 * Math.cos(0.008726646259971648 * (180.0 - AZ))), this.y - (int)Math.round(50.0 * Math.sin(0.008726646259971648 * (180.0 - AZ))));
            }
            else {
                g.drawString("Azimuth (E of N) " + Math.round(10.0 * (AZ + 180.0)) / 10.0 + '°', left, 140);
                g.drawArc(250, this.y - 70, 140, 140, 0, -(int)Math.round(180.0 + AZ));
                g.drawString("Az", 320 - (int)Math.round(50.0 * Math.cos(-0.008726646259971648 * AZ)), this.y + (int)Math.round(50.0 * Math.sin(0.008726646259971648 * AZ)));
            }
        }
        g.setColor(Color.black);
        if (this.isText && !this.isLHA && !this.isAz) {
            g.setFont(new Font("Helvetiva", 0, 14));
            g.drawString("Click inside the right half of the blue ring to set the latitude.", 120, 160);
        }
        if (this.demo) {
            this.f = g.getFont();
            g.setColor(Color.red);
            g.setFont(new Font("Chicago", 0, 96));
            g.drawString("D E M O", 170, 280);
            g.setFont(this.f);
        }
        g.setFont(new Font("Helvetica", 0, 10));
        g.setColor(Color.red);
        g.drawString(this.versStr, left, this.size().height - 70);
        g.drawString("© 2001-2010 Juergen Giesen", left, this.size().height - 55);
        g.drawString("www.GeoAstro.de", left, this.size().height - 40);
    }
    
    public boolean mouseDown(final Event event, final int x, final int y) {
        if ((x - 320) * (x - 320) + (y - 460) * (y - 460) > 62500 && (x - 320) * (x - 320) + (y - 460) * (y - 460) < 67600 && x > 320 && x != 320 && y != 460) {
            final double winkel = Math.atan2(x - 320, -y + 460) / 0.017453292519943295;
            this.latitude = 90.0 - winkel;
            this.latStr = String.valueOf(Math.abs(Math.round(100.0 * this.latitude) / 100.0));
            this.fieldLatDeg.setText(this.latStr);
            if (this.latitude > 0.0) {
                this.nsChoice.select("N");
            }
            else {
                this.nsChoice.select("S");
            }
            this.locChoice.select("User Input");
            this.repaint();
            return true;
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int x, final int y) {
        if ((x - 320) * (x - 320) + (y - 460) * (y - 460) > 62500 && (x - 320) * (x - 320) + (y - 460) * (y - 460) < 67600 && x > 320) {
            this.setCursor(new Cursor(1));
        }
        else {
            this.setCursor(new Cursor(0));
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int code) {
        int n = 0;
        if (code != 104 && code != 100 && code != 109 && code != 72 && code != 68 && code != 77 && code != 110 && code != 78) {
            return false;
        }
        if (code == 110) {
            ++this.minutes;
            if (this.minutes == 60) {
                this.minutes = 0;
                ++this.hours;
                if (this.hours == 24) {
                    this.hours = 0;
                    ++this.date;
                    this.dat.setDate(this.date);
                }
                this.dat.setHours(this.hours);
                this.hoursChoice.select(this.hours);
                if (this.date > this.daysInMonth(this.month, this.year)) {
                    this.date = 1;
                    this.dat.setDate(this.date);
                    ++this.month;
                    if (this.month == 12) {
                        this.month = 0;
                        ++this.year;
                        this.dat.setYear(this.year);
                        this.yearChoice.select(String.valueOf(this.year + 1900));
                    }
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
                    this.dat.setDate(this.date);
                }
                this.dat.setHours(this.hours);
                this.hoursChoice.select(this.hours);
                if (this.date == 0) {
                    --this.month;
                    if (this.month < 0) {
                        this.month = 11;
                        --this.year;
                        this.dat.setYear(this.year);
                        this.yearChoice.select(String.valueOf(this.year + 1900));
                    }
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
                    this.dat.setMonth(this.month);
                    this.monthChoice.select(this.month);
                }
                this.dateChoice.select(this.date - 1);
            }
            this.dat.setHours(this.hours);
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
            if (this.month > 11) {
                this.month = 0;
                ++this.year;
                this.dat.setYear(this.year);
                this.yearChoice.select(String.valueOf(this.year + 1900));
            }
            n = this.daysInMonth(this.month, this.year);
            if (this.date > n) {
                this.date = 1;
                this.dat.setDate(this.date);
                this.dateChoice.select(this.date - 1);
                --this.month;
            }
            this.dat.setMonth(this.month);
            this.monthChoice.select(this.month);
            if (this.isRise) {
                this.doRise();
            }
            this.repaint();
            return true;
        }
        return true;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        final double oldLat = this.latitude;
        final double oldLong = this.longitude;
        if (event.target instanceof Button && event.target == this.button) {
            this.isRise = false;
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
        if (event.target instanceof Checkbox) {
            if (event.target == this.LHABox) {
                if (this.LHABox.getState()) {
                    this.isLHA = true;
                }
                else {
                    this.isLHA = false;
                }
                this.repaint();
                return true;
            }
            if (event.target == this.azBox) {
                if (this.azBox.getState()) {
                    this.isAz = true;
                }
                else {
                    this.isAz = false;
                }
                this.repaint();
                return true;
            }
            return true;
        }
        else {
            if (event.target == this.fieldLatDeg) {
                this.str = this.fieldLatDeg.getText();
                for (int i = 0; i < this.str.length(); ++i) {
                    final char c = this.str.charAt(i);
                    if (c == ',') {
                        this.str = String.valueOf(this.str.substring(0, i)) + '.' + this.str.substring(i + 1, this.str.length());
                    }
                }
                for (int j = 0; j < this.str.length(); ++j) {
                    final char c = this.str.charAt(j);
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
                        this.str = String.valueOf(Math.abs((int)this.latitude)) + ".0";
                    }
                    this.fieldLatDeg.setText(this.str);
                    if (this.latitude > 90.0 || this.latitude == 0.0) {
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
            }
            if (event.target == this.fieldLongDeg) {
                this.str = this.fieldLongDeg.getText();
                for (int i = 0; i < this.str.length(); ++i) {
                    final char c = this.str.charAt(i);
                    if (c == ',') {
                        this.str = String.valueOf(this.str.substring(0, i)) + '.' + this.str.substring(i + 1, this.str.length());
                    }
                }
                for (int j = 0; j < this.str.length(); ++j) {
                    final char c = this.str.charAt(j);
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
                    if (this.longitude == (int)this.longitude) {
                        this.str = String.valueOf(this.str) + ".0";
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
                        if (this.ewChoice.getSelectedItem().equals("E")) {
                            this.longitude = -Math.abs(this.longitude);
                            this.usrLong = this.longitude;
                        }
                        this.repaint();
                    }
                }
                catch (NumberFormatException ex2) {
                    this.longitude = oldLong;
                    this.fieldLongDeg.setText(String.valueOf(oldLong));
                    System.out.println("NumberFormatException on longitude");
                }
            }
            if (event.target == this.detailChoice) {
                if (this.detailChoice.getSelectedItem().equals("Text off")) {
                    this.isText = false;
                    this.menu(1, true);
                }
                else if (this.detailChoice.getSelectedItem().equals("Text on")) {
                    this.isText = true;
                    this.menu(1, false);
                }
                else if (this.detailChoice.getSelectedItem().equals("Altitude off")) {
                    this.isAlt = false;
                    this.menu(2, true);
                }
                else if (this.detailChoice.getSelectedItem().equals("Altitude on")) {
                    this.isAlt = true;
                    this.menu(2, false);
                }
                else if (this.detailChoice.getSelectedItem().equals("Declination off")) {
                    this.isDec = false;
                    this.menu(3, true);
                }
                else if (this.detailChoice.getSelectedItem().equals("Declination on")) {
                    this.isDec = true;
                    this.menu(3, false);
                }
                else if (this.detailChoice.getSelectedItem().equals("Ecliptic off")) {
                    this.isEcl = false;
                    this.menu(4, true);
                }
                else if (this.detailChoice.getSelectedItem().equals("Ecliptic on")) {
                    this.isEcl = true;
                    this.menu(4, false);
                }
                else if (this.detailChoice.getSelectedItem().equals("Degrees off")) {
                    this.isDegrees = false;
                    this.menu(5, true);
                }
                else if (this.detailChoice.getSelectedItem().equals("Degrees on")) {
                    this.isDegrees = true;
                    this.menu(5, false);
                }
                else if (this.detailChoice.getSelectedItem().equals("Data off")) {
                    this.isData = false;
                    this.menu(6, true);
                }
                else if (this.detailChoice.getSelectedItem().equals("Data on")) {
                    this.isData = true;
                    this.menu(6, false);
                }
                else if (this.detailChoice.getSelectedItem().equals("Rise/Set")) {
                    this.isRise = true;
                    final Rise_Set rs = new Rise_Set(this.dat, this.latitude, -this.longitude, this.locOffset, 0.0);
                    final double t = rs.h_rise();
                    this.hours = (int)t;
                    this.minutes = (int)Math.round(60.0 * this.comp.frac(t));
                    if (this.minutes == 60) {
                        this.minutes = 0;
                        ++this.hours;
                    }
                    this.dat.setHours(this.hours);
                    this.dat.setMinutes(this.minutes);
                    this.hoursChoice.select(this.hours);
                    this.minChoice.select(this.minutes);
                }
                this.repaint();
                return true;
            }
            if (event.target == this.locChoice) {
                if (this.locChoice.getSelectedItem().equals("_________")) {
                    this.locChoice.select(this.locString);
                    return true;
                }
                if (this.locChoice.getSelectedItem().equals("Home")) {
                    this.locString = this.homeString;
                    this.locChoice.select(this.locString);
                    this.latitude = this.homeLat;
                    this.longitude = this.homeLong;
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
                if (this.latitude <= 0.0) {
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
                this.repaint();
            }
            if (event.target == this.minChoice) {
                this.minStr = this.minChoice.getSelectedItem();
                this.minutes = Integer.parseInt(this.minStr);
                this.minChoice.select(this.minStr);
                this.dat.setMinutes(this.minutes);
                this.repaint();
            }
            if (event.target == this.dateChoice) {
                this.dateStr = this.dateChoice.getSelectedItem();
                this.date = Integer.parseInt(this.dateStr);
                this.dat.setDate(this.date);
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
                if (this.isRise) {
                    this.doRise();
                }
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
            }
            this.repaint();
            return true;
        }
    }
    
    public sunplotter33() {
        this.versStr = "Spheric Sun Plotter  3.3";
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.str = "";
        this.online = false;
        this.demo = true;
        this.usrLat = 0.0;
        this.usrLong = 0.0;
        this.f = new Font("Helvetiva", 0, 12);
        this.f1 = new Font("Courier", 0, 12);
        this.green = new Color(0, 200, 0);
        this.isAz = false;
        this.isLHA = false;
        this.isText = true;
        this.isAlt = true;
        this.isDec = true;
        this.isEcl = true;
        this.isRise = false;
        this.isDegrees = true;
        this.isData = true;
    }
}
