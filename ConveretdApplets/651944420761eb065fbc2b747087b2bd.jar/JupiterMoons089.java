import java.util.Map;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.Event;
import java.net.URL;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Label;
import java.awt.Checkbox;
import java.awt.TextField;
import java.util.Date;
import java.awt.Choice;
import java.awt.Button;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JupiterMoons089 extends Applet
{
    final double K = 0.017453292519943295;
    final char deg = '°';
    final double AU = 1.4959787E8;
    final String[] monthArray;
    String versStr;
    Button okButton;
    int x0;
    int y0;
    Choice dateChoice;
    Choice monthChoice;
    Choice yearChoice;
    Choice hoursChoice;
    Choice minChoice;
    Choice viewChoice;
    Choice detailChoice;
    Choice timeChoice;
    int RR;
    double rr;
    int R1;
    int R2;
    int R3;
    int R4;
    int x1;
    int y1;
    int x2;
    int y2;
    int x3;
    int y3;
    int x4;
    int y4;
    double X1;
    double Y1;
    double X2;
    double Y2;
    double X3;
    double Y3;
    double X4;
    double Y4;
    double u1;
    double u2;
    double u3;
    double u4;
    Date dat;
    int date;
    int month;
    int year;
    int hours;
    int minutes;
    int locOffset;
    compute comp;
    boolean online;
    boolean demo;
    double deltaT;
    double UT;
    double latitude;
    double longitude;
    double AZ;
    double PA;
    int[] x_1;
    int[] y_1;
    int[] x_2;
    int[] y_2;
    int[] x_3;
    int[] y_3;
    int[] x_4;
    int[] y_4;
    int n1;
    int n2;
    int n3;
    int n4;
    double p1;
    double p2;
    double p3;
    double p4;
    double a1;
    double a2;
    double a3;
    double a4;
    double r1;
    double r2;
    double r3;
    double r4;
    double hRise;
    double hSet;
    double jupElev;
    double illFrac;
    double psi;
    TextField fieldLatDeg;
    TextField fieldLongDeg;
    String offsetStr;
    String latStr;
    String longStr;
    String timeString;
    String latString;
    String longString;
    double Mag;
    double dEJ;
    double earthDist;
    double jupAngle;
    double angle;
    double X5;
    double d1;
    double d2;
    double d3;
    double d4;
    boolean jup;
    boolean grid;
    boolean orb;
    boolean pa;
    boolean illum;
    double zoom;
    double geoLambda;
    double m1;
    double m2;
    double m3;
    double m4;
    double declin;
    double RA;
    double trans;
    double rise;
    double set;
    boolean RISE;
    boolean SETT;
    boolean ABOVE;
    double UTRISE;
    double UTSET;
    String riseStr;
    String setStr;
    double Y0;
    double yPlus;
    double yMinus;
    double DX;
    double YE;
    double zero1;
    double zero2;
    int NZ;
    boolean shadow;
    double zoomMoons;
    boolean to;
    boolean eu;
    Checkbox dtBox;
    Label dtLabel;
    boolean dt;
    double elong;
    String elongStr;
    double U1;
    double U2;
    double U3;
    double U4;
    double omega1;
    double omega2;
    double posAngle;
    boolean gridR;
    double km;
    boolean KM;
    double jupRiseAz;
    double jupSetAz;
    double sunRise;
    double sunSet;
    
    public void init() {
        this.setBackground(Color.white);
        this.setFont(new Font("Helvetica", 0, 11));
        final URL url = this.getDocumentBase();
        String str = url.toString();
        str = String.valueOf(str) + "1234567890123456789012345";
        final String wwwStr = str.substring(0, 27);
        final Double latDouble = Double.valueOf(this.getParameter("latitude"));
        this.latitude = latDouble;
        final Double longDouble = Double.valueOf(this.getParameter("longitude"));
        this.longitude = longDouble;
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        gbc.insets = new Insets(5, 0, 0, 0);
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        this.x0 = this.size().width / 2;
        this.y0 = this.size().height / 2 + 20;
        this.comp = new compute();
        this.dat = new Date();
        this.minutes = this.dat.getMinutes();
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth();
        this.year = this.dat.getYear();
        this.hours = this.dat.getHours();
        this.locOffset = -this.dat.getTimezoneOffset() / 60;
        this.timeString = String.valueOf(this.locOffset) + " h";
        if (this.locOffset > 0) {
            this.timeString = "+" + this.timeString;
        }
        this.timeString = "UT " + this.timeString;
        this.UT = this.hours - this.locOffset + this.minutes / 60.0;
        gbc.gridy = 0;
        Label L = new Label("View");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("Details");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("Year");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("Month");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("Date");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("Hours");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("Minutes");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("");
        gbl.setConstraints(L, gbc);
        this.add(L);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 0, 0);
        (this.viewChoice = new Choice()).add("Geocentric");
        this.viewChoice.add("Topocentric");
        this.viewChoice.add("Orbits");
        this.viewChoice.add("Zoom in");
        this.viewChoice.add("Zoom out");
        this.viewChoice.add("Diagram Moons");
        this.viewChoice.add("Sky");
        gbl.setConstraints(this.viewChoice, gbc);
        this.add(this.viewChoice);
        gbc.insets = new Insets(0, 0, 0, 0);
        (this.detailChoice = new Choice()).add("Grid Degrees on/off");
        this.detailChoice.add("Grid Radii on/off");
        this.detailChoice.add("Grid km on/off");
        this.detailChoice.addItem("Increase Diameter");
        this.detailChoice.addItem("Decrease Diameter");
        gbl.setConstraints(this.detailChoice, gbc);
        this.add(this.detailChoice);
        (this.yearChoice = new Choice()).addItem("1610");
        final int Y = this.year + 1900 - 110;
        for (int i = 0; i < 211; ++i) {
            this.yearChoice.addItem(String.valueOf(Y + i));
        }
        this.yearChoice.select(String.valueOf(this.year + 1900));
        gbl.setConstraints(this.yearChoice, gbc);
        this.add(this.yearChoice);
        this.monthChoice = new Choice();
        for (int j = 0; j < 12; ++j) {
            this.monthChoice.addItem(this.monthArray[j]);
        }
        this.monthChoice.select(this.month);
        gbl.setConstraints(this.monthChoice, gbc);
        this.add(this.monthChoice);
        this.dateChoice = new Choice();
        for (int k = 1; k < 32; ++k) {
            this.dateChoice.addItem(String.valueOf(k));
        }
        this.dateChoice.select(this.date - 1);
        gbl.setConstraints(this.dateChoice, gbc);
        this.add(this.dateChoice);
        this.hoursChoice = new Choice();
        for (int l = 0; l < 10; ++l) {
            this.hoursChoice.addItem("0" + l);
        }
        for (int m = 10; m < 24; ++m) {
            this.hoursChoice.addItem(String.valueOf(m));
        }
        this.hoursChoice.select(this.hours);
        gbl.setConstraints(this.hoursChoice, gbc);
        this.add(this.hoursChoice);
        this.minChoice = new Choice();
        for (int i2 = 0; i2 < 10; ++i2) {
            this.minChoice.addItem("0" + i2);
        }
        for (int i3 = 10; i3 <= 60; ++i3) {
            this.minChoice.addItem(String.valueOf(i3));
        }
        this.minChoice.select(this.minutes);
        gbl.setConstraints(this.minChoice, gbc);
        this.add(this.minChoice);
        gbc.insets = new Insets(0, 0, 5, 0);
        gbl.setConstraints(this.okButton = new Button("Now"), gbc);
        this.add(this.okButton);
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        L = new Label("Latitude:");
        gbl.setConstraints(L, gbc);
        this.add(L);
        gbl.setConstraints(this.fieldLatDeg = new TextField(String.valueOf(this.latitude), 6), gbc);
        this.add(this.fieldLatDeg);
        L = new Label("Longitude:");
        gbl.setConstraints(L, gbc);
        this.add(L);
        gbl.setConstraints(this.fieldLongDeg = new TextField(String.valueOf(this.longitude), 7), gbc);
        this.add(this.fieldLongDeg);
        L = new Label("Time Zone:");
        gbl.setConstraints(L, gbc);
        this.add(L);
        (this.timeChoice = new Choice()).addItem("UT +0 h");
        this.timeChoice.addItem("UT +1 h");
        this.timeChoice.addItem("UT -1 h");
        this.timeChoice.addItem("UT +2 h");
        this.timeChoice.addItem("UT -2 h");
        this.timeChoice.addItem("UT +3 h");
        this.timeChoice.addItem("UT -3 h");
        this.timeChoice.addItem("UT +4 h");
        this.timeChoice.addItem("UT -4 h");
        this.timeChoice.addItem("UT +5 h");
        this.timeChoice.addItem("UT -5 h");
        this.timeChoice.addItem("UT +6 h");
        this.timeChoice.addItem("UT -6 h");
        this.timeChoice.addItem("UT +7 h");
        this.timeChoice.addItem("UT -7 h");
        this.timeChoice.addItem("UT +8 h");
        this.timeChoice.addItem("UT -8 h");
        this.timeChoice.addItem("UT +9 h");
        this.timeChoice.addItem("UT -9 h");
        this.timeChoice.addItem("UT +10 h");
        this.timeChoice.addItem("UT -10 h");
        this.timeChoice.addItem("UT +11 h");
        this.timeChoice.addItem("UT -11 h");
        this.timeChoice.addItem("UT +12 h");
        this.timeChoice.addItem("UT -12 h");
        this.timeChoice.addItem("UT +13 h");
        gbl.setConstraints(this.timeChoice, gbc);
        this.timeChoice.select(this.timeString);
        this.add(this.timeChoice);
        gbl.setConstraints(this.dtBox = new Checkbox(), gbc);
        this.dtBox.setState(this.dt);
        this.add(this.dtBox);
        this.deltaT = this.comp.getDeltaT(this.date, this.month, this.year);
        this.dtLabel = new Label("\u2206T=ET-UT=" + Math.round(10.0 * this.deltaT) / 10.0 + "s");
        gbc.insets = new Insets(0, 0, 0, 20);
        gbl.setConstraints(this.dtLabel, gbc);
        this.add(this.dtLabel);
        gbc.gridy = 3;
        gbc.weighty = 160.0;
        final Canvas can = new Canvas();
        gbl.setConstraints(can, gbc);
        this.add(can);
        boolean ok = true;
        final String email = this.getParameter("email");
        final String param = this.getParameter("password");
        if (this.formula(wwwStr, 18) == this.formula("http://www.tyge.de", 18) || this.formula(wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(wwwStr, 22) == this.formula("http://www.j-giesenviewItem==40.de", 22) || this.formula(wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(wwwStr, 27) == this.formula("http://www.jgiesen.business", 27)) {
            ok = true;
            this.online = true;
            this.demo = false;
        }
        else {
            ok = false;
        }
        if (!ok) {
            ok = true;
            if (email.length() == 0 || (Integer.parseInt(param) != this.formula(email, email.length()) && Integer.parseInt(param) != this.formula(email, email.length()) - 10)) {
                ok = false;
            }
            else {
                ok = true;
                this.demo = false;
            }
            if (wwwStr.substring(0, 7).equals("http://")) {
                ok = false;
                this.demo = true;
                this.online = true;
            }
        }
    }
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 26018;
    }
    
    public void getOffset() {
        if (this.longitude >= -7.5) {
            this.locOffset = (int)((this.longitude + 7.5) / 15.0);
        }
        else {
            this.locOffset = -(int)(-(this.longitude - 7.5) / 15.0);
        }
        if (this.longitude >= 0.0) {
            this.offsetStr = "UT +" + this.locOffset + " h";
        }
        else {
            this.offsetStr = "UT " + this.locOffset + " h";
        }
        this.timeChoice.select(this.offsetStr);
    }
    
    public int getTimeZone(final String timeString) {
        int timeOffset = 0;
        if (timeString.equals("UT +0 h")) {
            timeOffset = 0;
        }
        else if (timeString.equals("UT -1 h")) {
            timeOffset = -1;
        }
        else if (timeString.equals("UT +1 h")) {
            timeOffset = 1;
        }
        else if (timeString.equals("UT -2 h")) {
            timeOffset = -2;
        }
        else if (timeString.equals("UT +2 h")) {
            timeOffset = 2;
        }
        else if (timeString.equals("UT -3 h")) {
            timeOffset = -3;
        }
        else if (timeString.equals("UT +3 h")) {
            timeOffset = 3;
        }
        else if (timeString.equals("UT -4 h")) {
            timeOffset = -4;
        }
        else if (timeString.equals("UT +4 h")) {
            timeOffset = 4;
        }
        else if (timeString.equals("UT -5 h")) {
            timeOffset = -5;
        }
        else if (timeString.equals("UT +5 h")) {
            timeOffset = 5;
        }
        else if (timeString.equals("UT -6 h")) {
            timeOffset = -6;
        }
        else if (timeString.equals("UT +6 h")) {
            timeOffset = 6;
        }
        else if (timeString.equals("UT -7 h")) {
            timeOffset = -7;
        }
        else if (timeString.equals("UT +7 h")) {
            timeOffset = 7;
        }
        else if (timeString.equals("UT -8 h")) {
            timeOffset = -8;
        }
        else if (timeString.equals("UT +8 h")) {
            timeOffset = 8;
        }
        else if (timeString.equals("UT -9 h")) {
            timeOffset = -9;
        }
        else if (timeString.equals("UT +9 h")) {
            timeOffset = 9;
        }
        else if (timeString.equals("UT -10 h")) {
            timeOffset = -10;
        }
        else if (timeString.equals("UT +10 h")) {
            timeOffset = 10;
        }
        else if (timeString.equals("UT -11 h")) {
            timeOffset = -11;
        }
        else if (timeString.equals("UT +11 h")) {
            timeOffset = 11;
        }
        else if (timeString.equals("UT -12 h")) {
            timeOffset = -12;
        }
        else if (timeString.equals("UT +12 h")) {
            timeOffset = 12;
        }
        else if (timeString.equals("UT +13 h")) {
            timeOffset = 13;
        }
        return timeOffset;
    }
    
    public boolean keyDown(final Event event, final int code) {
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
                if (this.date > this.comp.daysInMonth(this.month, this.year)) {
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
                    this.date = this.comp.daysInMonth(this.month, this.year);
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
                    this.hours = 23;
                    --this.date;
                    if (this.date == 0) {
                        --this.month;
                        if (this.month < 0) {
                            this.month = 11;
                            --this.year;
                        }
                        this.yearChoice.select(String.valueOf(this.year + 1900));
                        this.date = this.comp.daysInMonth(this.month, this.year);
                    }
                    this.dat.setDate(this.date);
                    this.dat.setMonth(this.month);
                    this.dat.setYear(this.year);
                }
            }
            else {
                ++this.hours;
            }
            if (this.hours >= 24) {
                this.hours = 0;
                ++this.date;
                final int n = this.comp.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = 1;
                    ++this.month;
                    if (this.month == 12) {
                        this.month = 0;
                        ++this.year;
                        this.yearChoice.select(String.valueOf(this.year + 1900));
                    }
                    this.dat.setMonth(this.month);
                    this.dat.setYear(this.year);
                    this.monthChoice.select(this.month);
                }
                this.dateChoice.select(this.date - 1);
            }
            this.dat.setHours(this.hours);
            this.dat.setDate(this.date);
            this.hoursChoice.select(this.hours);
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
                    this.date = this.comp.daysInMonth(this.month, this.year);
                }
            }
            else {
                ++this.date;
                this.month = this.dat.getMonth();
                final int n = this.comp.daysInMonth(this.month, this.year);
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
            }
            if (this.month < 0) {
                this.month = 11;
                --this.year;
                this.dat.setYear(this.year);
            }
            final int n = this.comp.daysInMonth(this.month, this.year);
            if (this.date > n) {
                this.date = 1;
                ++this.month;
            }
            this.dat.setMonth(this.month);
            this.monthChoice.select(this.month);
            this.dat.setDate(this.date);
            this.dateChoice.select(this.date - 1);
            this.yearChoice.select(String.valueOf(this.year + 1900));
            this.repaint();
            return true;
        }
        return false;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof Checkbox && event.target == this.dtBox) {
            this.dt ^= true;
            this.calculate(this.UT, true);
            this.repaint();
            return true;
        }
        if (event.target instanceof TextField) {
            final double oldLat = this.latitude;
            double oldLong = this.longitude;
            if (event.target == this.fieldLatDeg) {
                String str = this.fieldLatDeg.getText();
                if (str.length() == 0 || str.charAt(0) == '.' || str.charAt(0) == ',') {
                    this.fieldLatDeg.setText(String.valueOf(Math.abs(Math.round(100.0 * oldLat) / 100.0)));
                    return true;
                }
                for (int i = 0; i < str.length(); ++i) {
                    final char c = str.charAt(i);
                    if (c == ',') {
                        str = String.valueOf(str.substring(0, i)) + '.' + str.substring(i + 1, str.length());
                    }
                }
                for (int j = 0; j < str.length(); ++j) {
                    final char c = str.charAt(j);
                    if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != '.' && c != '-' && c != '+') {
                        this.fieldLatDeg.setText(String.valueOf(Math.abs(oldLat)));
                        return true;
                    }
                }
                final Double latDouble = Double.valueOf(str);
                this.latitude = latDouble;
                this.fieldLatDeg.setText(str);
                this.latStr = str;
                if (this.latitude > 90.0) {
                    this.fieldLatDeg.setText(String.valueOf(oldLat));
                    this.latitude = oldLat;
                    this.latStr = String.valueOf(Math.abs(oldLat));
                    this.fieldLatDeg.setText(this.latStr);
                }
                this.repaint();
                return true;
            }
            else if (event.target == this.fieldLongDeg) {
                oldLong = this.longitude;
                String str = this.fieldLongDeg.getText();
                if (str.length() == 0 || str.charAt(0) == '.' || str.charAt(0) == ',') {
                    this.fieldLongDeg.setText(String.valueOf(Math.abs(Math.round(100.0 * oldLong) / 100.0)));
                    return true;
                }
                for (int i = 0; i < str.length(); ++i) {
                    final char c = str.charAt(i);
                    if (c == ',') {
                        str = String.valueOf(str.substring(0, i)) + '.' + str.substring(i + 1, str.length());
                    }
                }
                for (int j = 0; j < str.length(); ++j) {
                    final char c = str.charAt(j);
                    if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != '.' && c != '-' && c != '+') {
                        this.fieldLongDeg.setText(String.valueOf(Math.abs(oldLong)));
                        return true;
                    }
                }
                this.longStr = str;
                final Double longDouble = Double.valueOf(this.longStr);
                this.longitude = longDouble;
                this.fieldLongDeg.setText(this.longStr);
                if (this.longitude > 180.0) {
                    this.fieldLongDeg.setText(String.valueOf(oldLong));
                    this.longitude = oldLong;
                }
                if (this.longitude == (int)this.longitude) {
                    this.fieldLongDeg.setText(String.valueOf((int)Math.abs(this.longitude)) + ".00");
                }
                this.getOffset();
                this.repaint();
                return true;
            }
        }
        if (event.target instanceof Choice) {
            if (event.target == this.minChoice) {
                final String minStr = this.minChoice.getSelectedItem();
                this.minutes = Integer.parseInt(minStr);
                if (this.minutes == 60) {
                    this.minutes = 0;
                    ++this.hours;
                    if (this.hours == 24) {
                        this.hours = 0;
                        ++this.date;
                        final int n = this.comp.daysInMonth(this.month, this.year);
                        if (this.date > n) {
                            this.date = 1;
                            ++this.month;
                            if (this.month == 12) {
                                this.month = 0;
                                ++this.year;
                                this.dat.setYear(this.year);
                                this.yearChoice.select(String.valueOf(this.year + 1900));
                            }
                            this.dat.setMonth(this.month);
                            this.dat.setDate(1);
                            this.dateChoice.select(this.date - 1);
                            this.monthChoice.select(this.month);
                        }
                        this.dateChoice.select(this.date - 1);
                    }
                    this.hoursChoice.select(this.hours);
                    this.dat.setDate(this.date);
                    this.dat.setHours(this.hours);
                }
                this.minChoice.select(minStr);
                this.dat.setMinutes(this.minutes);
                this.repaint();
                return true;
            }
            if (event.target == this.hoursChoice) {
                final String hoursStr = this.hoursChoice.getSelectedItem();
                this.hours = Integer.parseInt(hoursStr);
                this.hoursChoice.select(hoursStr);
                this.dat.setHours(this.hours);
                this.repaint();
                return true;
            }
            if (event.target == this.dateChoice) {
                final String dateStr = this.dateChoice.getSelectedItem();
                this.date = Integer.parseInt(dateStr);
                this.dateChoice.select(this.date - 1);
                this.dat.setDate(this.date);
                final int n = this.comp.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = n;
                    this.dat.setDate(this.date);
                    this.dateChoice.select(this.date - 1);
                }
                this.repaint();
                return true;
            }
            if (event.target == this.monthChoice) {
                final String monthStr = this.monthChoice.getSelectedItem();
                this.month = this.comp.MonthInteger(monthStr);
                this.dat.setDate(this.date);
                this.dateChoice.select(this.date - 1);
                this.dat.setMonth(this.month);
                this.dat.setDate(this.date);
                final int n = this.comp.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = n;
                    this.dat.setDate(this.date);
                    this.dateChoice.select(this.date - 1);
                }
                this.repaint();
                return true;
            }
            if (event.target == this.yearChoice) {
                this.year = Integer.parseInt(this.yearChoice.getSelectedItem());
                this.year -= 1900;
                this.dat.setYear(this.year);
                final int n = this.comp.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = n;
                    this.dat.setDate(this.date);
                    this.dateChoice.select(this.date - 1);
                }
                this.repaint();
                return true;
            }
            if (event.target == this.viewChoice) {
                if (this.viewChoice.getSelectedItem() == "Sky") {
                    this.sky();
                    return true;
                }
                if (this.viewChoice.getSelectedItem() == "Diagram Moons") {
                    final moonFrame mf = new moonFrame(this.year + 1900, this.month + 1, this.date, this.UT, this.demo);
                    mf.resize(410, 605);
                    mf.show();
                }
                if (this.viewChoice.getSelectedItem() == "Geocentric") {
                    this.pa = false;
                    this.orb = false;
                    this.jup = true;
                    int k;
                    for (int n = k = this.detailChoice.getItemCount() - 1; k >= 0; --k) {
                        this.detailChoice.remove(k);
                    }
                    this.detailChoice.addItem("Grid Degrees on/off");
                    this.detailChoice.addItem("Grid Radii on/off");
                    this.detailChoice.addItem("Grid km on/off");
                    this.detailChoice.addItem("Increase Diameter");
                    this.detailChoice.addItem("Decrease Diameter");
                }
                if (this.viewChoice.getSelectedItem() == "Topocentric") {
                    this.pa = true;
                    this.orb = false;
                    this.jup = true;
                    int k;
                    for (int n = k = this.detailChoice.getItemCount() - 1; k >= 0; --k) {
                        this.detailChoice.remove(k);
                    }
                    this.detailChoice.addItem("Grid Degrees on/off");
                    this.detailChoice.addItem("Grid Radii on/off");
                    this.detailChoice.addItem("Grid km on/off");
                    this.detailChoice.addItem("Increase Diameter");
                    this.detailChoice.addItem("Decrease Diameter");
                }
                if (this.viewChoice.getSelectedItem() == "Orbits") {
                    this.pa = false;
                    this.orb = true;
                    this.jup = false;
                    int k;
                    for (int n = k = this.detailChoice.getItemCount() - 1; k >= 0; --k) {
                        this.detailChoice.remove(k);
                    }
                    this.detailChoice.addItem("Grid Degrees on/off");
                    this.detailChoice.addItem("Grid Radii on/off");
                    this.detailChoice.addItem("Grid km on/off");
                    this.detailChoice.addItem("Illumination on/off");
                    this.detailChoice.addItem("Jup. Shadow on/off");
                    this.detailChoice.addItem("Transit/Occult. on/off");
                    this.detailChoice.addItem("Eclipse/Umbra on/off");
                    this.detailChoice.addItem("Increase Diameter");
                    this.detailChoice.addItem("Decrease Diameter");
                }
                if (this.viewChoice.getSelectedItem() == "Zoom in") {
                    this.zoom *= 1.2;
                }
                if (this.viewChoice.getSelectedItem() == "Zoom out") {
                    this.zoom *= 0.8;
                }
                this.repaint();
                return true;
            }
            else {
                if (event.target == this.detailChoice) {
                    if (this.detailChoice.getSelectedItem() == "Grid km on/off") {
                        this.KM ^= true;
                        if (this.KM) {
                            this.gridR = false;
                            this.grid = false;
                        }
                    }
                    if (this.detailChoice.getSelectedItem() == "Grid Degrees on/off") {
                        this.grid ^= true;
                        if (this.grid) {
                            this.gridR = false;
                            this.KM = false;
                        }
                    }
                    if (this.detailChoice.getSelectedItem() == "Grid Radii on/off") {
                        this.gridR ^= true;
                        if (this.gridR) {
                            this.grid = false;
                            this.KM = false;
                        }
                    }
                    if (this.detailChoice.getSelectedItem() == "Illumination on/off") {
                        this.illum ^= true;
                    }
                    if (this.detailChoice.getSelectedItem() == "Jup. Shadow on/off") {
                        this.shadow ^= true;
                    }
                    if (this.detailChoice.getSelectedItem() == "Transit/Occult. on/off") {
                        this.to ^= true;
                    }
                    if (this.detailChoice.getSelectedItem() == "Eclipse/Umbra on/off") {
                        this.eu ^= true;
                    }
                    if (this.detailChoice.getSelectedItem() == "Increase Diameter") {
                        this.zoomMoons *= 1.2;
                    }
                    if (this.detailChoice.getSelectedItem() == "Decrease Diameter") {
                        this.zoomMoons *= 0.8;
                    }
                    this.repaint();
                    return true;
                }
                if (event.target == this.timeChoice) {
                    final int gmtHours = this.hours - this.locOffset;
                    this.timeString = this.timeChoice.getSelectedItem();
                    this.locOffset = this.getTimeZone(this.timeString);
                    this.hours = gmtHours + this.locOffset;
                    this.timeChoice.select(this.timeString);
                    if (this.hours >= 24) {
                        this.hours -= 24;
                        ++this.date;
                        final int n = this.comp.daysInMonth(this.month, this.year);
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
                                this.dat.setYear(this.year);
                            }
                            this.date = this.comp.daysInMonth(this.month, this.year);
                            this.dat.setMonth(this.month);
                            this.monthChoice.select(this.month);
                        }
                    }
                    this.dat.setDate(this.date);
                    this.dateChoice.select(this.date - 1);
                    this.dat.setHours(this.hours);
                    this.timeChoice.select(this.timeString);
                    this.hoursChoice.select(this.hours);
                    this.repaint();
                    return true;
                }
            }
        }
        if (event.target instanceof Button && event.target == this.okButton) {
            this.dat = new Date();
            this.minutes = this.dat.getMinutes();
            this.hours = this.dat.getHours();
            this.date = this.dat.getDate();
            this.month = this.dat.getMonth();
            this.year = this.dat.getYear();
            this.hours = this.dat.getHours();
            this.locOffset = -this.dat.getTimezoneOffset() / 60;
            this.minChoice.select(this.minutes);
            this.hoursChoice.select(this.hours);
            this.dateChoice.select(this.date - 1);
            this.monthChoice.select(this.monthArray[this.month]);
            this.yearChoice.select(String.valueOf(this.year + 1900));
            this.UT = this.hours - this.locOffset + this.minutes / 60.0;
            this.repaint();
            return true;
        }
        return true;
    }
    
    public double pAngle() {
        final double JD = this.comp.JD(this.date, this.month + 1, this.year + 1900, this.UT);
        final double JDE = JD + this.deltaT / 86400.0;
        final double t = (JDE - 2433282.5) / 36525.0;
        final double a0 = 268.0 + 0.1061 * t;
        final double d0 = 64.5 - 0.0164 * t;
        return Math.atan2(Math.cos(0.017453292519943295 * d0) * Math.sin(0.017453292519943295 * (a0 - this.RA)), Math.sin(0.017453292519943295 * d0) * Math.cos(0.017453292519943295 * this.declin) - Math.cos(0.017453292519943295 * d0) * Math.sin(0.017453292519943295 * this.declin) * Math.cos(0.017453292519943295 * (a0 - this.RA))) / 0.017453292519943295;
    }
    
    public void calculate(final double ut, final boolean jup) {
        if (!this.dt) {
            this.deltaT = 0.0;
            this.dtLabel.setText("\u2206T=ET-UT=0s");
        }
        else {
            this.deltaT = this.comp.getDeltaT(this.date, this.month, this.year);
            this.dtLabel.setText("\u2206T=ET-UT= " + Math.round(10.0 * this.deltaT) / 10.0 + "s");
        }
        this.SunRiseSet();
        if (jup) {
            this.jupiter();
        }
        if (this.latitude >= 0.0) {
            this.latString = new StringBuffer().append(this.latitude).append('°').append(" N").toString();
        }
        else {
            this.latString = new StringBuffer().append(Math.abs(this.latitude)).append('°').append(" S").toString();
        }
        if (this.longitude >= 0.0) {
            this.longString = new StringBuffer().append(this.longitude).append('°').append(" E").toString();
        }
        else {
            this.longString = new StringBuffer().append(Math.abs(this.longitude)).append('°').append(" W").toString();
        }
        final double JD = this.comp.JD(this.date, this.month + 1, this.year + 1900, ut);
        final double JDE = JD + this.deltaT / 86400.0;
        final double d = JDE - 2451545.0;
        double V = 172.74 + 0.00111588 * d;
        V = this.comp.check360(V);
        double M = 357.529 + 0.9856003 * d;
        M = this.comp.check360(M);
        double N = 20.02 + 0.0830853 * d + 0.329 * Math.sin(0.017453292519943295 * V);
        N = this.comp.check360(N);
        double J = 66.115 + 0.9025179 * d - 0.329 * Math.sin(0.017453292519943295 * V);
        J = this.comp.check360(J);
        final double A = 1.915 * Math.sin(0.017453292519943295 * M) + 0.02 * Math.sin(0.03490658503988659 * M);
        final double B = 5.555 * Math.sin(0.017453292519943295 * N) + 0.168 * Math.sin(0.03490658503988659 * N);
        final double k = J + A - B;
        final double R = 1.00014 - 0.01671 * Math.cos(0.017453292519943295 * M) - 1.4E-4 * Math.cos(0.03490658503988659 * M);
        final double r = 5.20872 - 0.25208 * Math.cos(0.017453292519943295 * N) - 0.00611 * Math.cos(0.03490658503988659 * N);
        this.dEJ = Math.sqrt(r * r + R * R - 2.0 * r * R * Math.cos(0.017453292519943295 * k));
        this.psi = Math.asin(R * Math.sin(0.017453292519943295 * k) / this.dEJ) / 0.017453292519943295;
        this.illFrac = 0.5 * (1.0 + Math.cos(0.017453292519943295 * this.psi));
        this.omega1 = 210.98 + 877.8169088 * (d - this.dEJ / 173.0) + this.psi - B;
        this.omega2 = 187.23 + 870.1869088 * (d - this.dEJ / 173.0) + this.psi - B;
        this.omega1 = this.comp.check360(this.omega1);
        this.omega2 = this.comp.check360(this.omega2);
        double lambda = 34.35 + 0.083091 * d + 0.329 * Math.sin(0.017453292519943295 * V) + B;
        lambda = this.comp.check360(lambda);
        double De;
        if (!this.orb) {
            final double Ds = 3.12 * Math.sin(0.017453292519943295 * (lambda + 42.8));
            De = Ds - 2.22 * Math.sin(0.017453292519943295 * this.psi) * Math.cos(0.017453292519943295 * (lambda + 22.0)) - 1.3 * (r - this.dEJ) * Math.sin(0.017453292519943295 * (lambda - 100.5)) / this.dEJ;
        }
        else {
            De = 90.0;
        }
        this.u1 = 163.8067 + 203.4058643 * (d - this.dEJ / 173.0) + this.psi - B;
        this.u2 = 358.4108 + 101.2916334 * (d - this.dEJ / 173.0) + this.psi - B;
        this.u3 = 5.7129 + 50.2345179 * (d - this.dEJ / 173.0) + this.psi - B;
        this.u4 = 224.8151 + 21.4879801 * (d - this.dEJ / 173.0) + this.psi - B;
        final double G = 331.18 + 50.310482 * (d - this.dEJ / 173.0);
        final double H = 87.4 + 21.569231 * (d - this.dEJ / 173.0);
        this.U1 = this.u1 + 0.473 * Math.sin(0.03490658503988659 * (this.u1 - this.u2));
        this.U2 = this.u2 + 1.065 * Math.sin(0.03490658503988659 * (this.u2 - this.u3));
        this.U3 = this.u3 + 0.165 * Math.sin(0.017453292519943295 * G);
        this.U4 = this.u4 + 0.841 * Math.sin(0.017453292519943295 * H);
        this.U1 = this.comp.check360(this.U1);
        this.U2 = this.comp.check360(this.U2);
        this.U3 = this.comp.check360(this.U3);
        this.U4 = this.comp.check360(this.U4);
        this.u1 = this.comp.check360(this.u1);
        this.u2 = this.comp.check360(this.u2);
        this.u3 = this.comp.check360(this.u3);
        this.u4 = this.comp.check360(this.u4);
        this.r1 = 5.9073 - 0.0244 * Math.cos(0.03490658503988659 * (this.u1 - this.u2));
        this.r2 = 9.3991 - 0.0882 * Math.cos(0.03490658503988659 * (this.u2 - this.u3));
        this.r3 = 14.9924 - 0.0216 * Math.cos(0.017453292519943295 * G);
        this.r4 = 26.3699 - 0.1935 * Math.cos(0.017453292519943295 * H);
        this.X1 = this.rr * this.r1 * Math.sin(0.017453292519943295 * this.U1);
        this.Y1 = -this.rr * this.r1 * Math.cos(0.017453292519943295 * this.U1) * Math.sin(0.017453292519943295 * De);
        this.x1 = (int)Math.round(this.X1);
        this.y1 = (int)Math.round(this.Y1);
        this.X2 = this.rr * this.r2 * Math.sin(0.017453292519943295 * this.U2);
        this.Y2 = -this.rr * this.r2 * Math.cos(0.017453292519943295 * this.U2) * Math.sin(0.017453292519943295 * De);
        this.x2 = (int)Math.round(this.X2);
        this.y2 = (int)Math.round(this.Y2);
        this.X3 = this.rr * this.r3 * Math.sin(0.017453292519943295 * this.U3);
        this.Y3 = -this.rr * this.r3 * Math.cos(0.017453292519943295 * this.U3) * Math.sin(0.017453292519943295 * De);
        this.x3 = (int)Math.round(this.X3);
        this.y3 = (int)Math.round(this.Y3);
        this.X4 = this.rr * this.r4 * Math.sin(0.017453292519943295 * this.U4);
        this.Y4 = -this.rr * this.r4 * Math.cos(0.017453292519943295 * this.U4) * Math.sin(0.017453292519943295 * De);
        this.x4 = (int)Math.round(this.X4);
        this.y4 = (int)Math.round(this.Y4);
        this.d1 = this.jupAngle * this.X1 / (60.0 * this.rr);
        this.d2 = this.jupAngle * this.X2 / (60.0 * this.rr);
        this.d3 = this.jupAngle * this.X3 / (60.0 * this.rr);
        this.d4 = this.jupAngle * this.X4 / (60.0 * this.rr);
    }
    
    void computeSun(final int d, final int m, final int year) {
        this.RISE = false;
        this.SETT = false;
        for (int i = -this.locOffset; i < -this.locOffset + 24; ++i) {
            this.riseset(d, m + 1, year + 1900, i);
            if (this.RISE && this.SETT) {
                break;
            }
        }
        if (this.RISE || this.SETT) {
            if (this.RISE) {
                this.UTRISE += this.locOffset;
                this.riseStr = this.comp.makeTimeString(this.comp.check24(this.UTRISE));
                this.sunRise = this.comp.check24(this.UTRISE);
            }
            else if (this.ABOVE) {
                this.riseStr = "Sun visible all day";
                this.setStr = "Sun visible all day";
            }
            else {
                this.riseStr = "Sun invisible all day";
                this.setStr = "Sun invisible all day";
            }
            if (this.SETT) {
                this.UTSET += this.locOffset;
                this.setStr = this.comp.makeTimeString(this.comp.check24(this.UTSET));
                this.sunSet = this.comp.check24(this.UTSET);
            }
            else if (this.ABOVE) {
                this.riseStr = "Sun visible all day";
                this.setStr = "Sun visible all day";
            }
            else {
                this.riseStr = "Sun invisible all day";
                this.setStr = "Sun invisible all day";
            }
        }
        else if (this.ABOVE) {
            this.riseStr = "Sun visible all day";
            this.setStr = "Sun visible all day";
        }
        else {
            this.riseStr = "Sun invisible all day";
            this.setStr = "Sun invisible all day";
        }
    }
    
    public void riseset(final int DATE, final int MONTH, final int YEAR, final double HOUR) {
        final double sh = Math.sin(-0.01454441043328608);
        final double JD = this.comp.JD(DATE, MONTH, YEAR, HOUR);
        double dec = this.comp.DeclinationRightAscension(1, JD);
        double ra = this.comp.DeclinationRightAscension(2, JD);
        this.Y0 = this.comp.sin_elev(JD, this.latitude, -this.longitude, dec, ra) - sh;
        final double jdPlus = this.comp.JD(DATE, MONTH, YEAR, HOUR + 1.0);
        dec = this.comp.DeclinationRightAscension(1, jdPlus);
        ra = this.comp.DeclinationRightAscension(2, jdPlus);
        this.yPlus = this.comp.sin_elev(jdPlus, this.latitude, -this.longitude, dec, ra) - sh;
        final double jdMinus = this.comp.JD(DATE, MONTH, YEAR, HOUR - 1.0);
        dec = this.comp.DeclinationRightAscension(1, jdMinus);
        ra = this.comp.DeclinationRightAscension(2, jdMinus);
        this.yMinus = this.comp.sin_elev(jdMinus, this.latitude, -this.longitude, dec, ra) - sh;
        this.ABOVE = (this.yMinus > 0.0);
        this.QUAD();
        switch (this.NZ) {
            case 1: {
                if (this.yMinus < 0.0) {
                    this.UTRISE = HOUR + this.zero1;
                    this.RISE = true;
                    break;
                }
                this.UTSET = HOUR + this.zero1;
                this.SETT = true;
                break;
            }
            case 2: {
                if (this.YE < 0.0) {
                    this.UTRISE = HOUR + this.zero2;
                    this.UTSET = HOUR + this.zero1;
                }
                else {
                    this.UTRISE = HOUR + this.zero1;
                    this.UTSET = HOUR + this.zero2;
                }
                this.RISE = true;
                this.SETT = true;
                break;
            }
        }
    }
    
    public void QUAD() {
        this.NZ = 0;
        final double A = 0.5 * (this.yMinus + this.yPlus) - this.Y0;
        final double B = 0.5 * (this.yPlus - this.yMinus);
        final double C = this.Y0;
        final double XE = -B / (2.0 * A);
        this.YE = (A * XE + B) * XE + C;
        final double DIS = B * B - 4.0 * A * C;
        if (DIS >= 0.0) {
            this.DX = 0.5 * Math.sqrt(DIS) / Math.abs(A);
            this.zero1 = XE - this.DX;
            this.zero2 = XE + this.DX;
            if (Math.abs(this.zero1) <= 1.0) {
                ++this.NZ;
            }
            if (Math.abs(this.zero2) <= 1.0) {
                ++this.NZ;
            }
            if (this.zero1 < -1.0) {
                this.zero1 = this.zero2;
            }
        }
    }
    
    public void orbit() {
        for (int i = 0; i <= this.n1; ++i) {
            final double ut = this.UT + i;
            this.calculate(ut, false);
            this.x_1[i] = this.x1;
            this.y_1[i] = this.y1;
        }
        for (int j = 0; j <= this.n2; ++j) {
            final double ut = this.UT + j;
            this.calculate(ut, false);
            this.x_2[j] = this.x2;
            this.y_2[j] = this.y2;
        }
        for (int k = 0; k <= this.n3; ++k) {
            final double ut = this.UT + k;
            this.calculate(ut, false);
            this.x_3[k] = this.x3;
            this.y_3[k] = this.y3;
        }
        for (int l = 0; l <= this.n4; ++l) {
            final double ut = this.UT + 2 * l;
            this.calculate(ut, false);
            this.x_4[l] = this.x4;
            this.y_4[l] = this.y4;
        }
    }
    
    public void sky() {
        final double[] rise = new double[13];
        final double[] set = new double[13];
        for (int m = 0; m < 12; ++m) {
            this.computeSun(1, m, this.year);
            rise[m] = this.UTRISE;
            set[m] = this.UTSET;
        }
        this.computeSun(this.date, this.month, this.year);
        final skyFrame zf = new skyFrame("Jupiter " + (this.year + 1900), this.year, this.latitude, this.longitude, this.timeString, rise, set, this.locOffset, this.date, this.month, this.sunRise, this.sunSet);
        System.out.println(String.valueOf(this.comp.makeTimeString(this.sunRise)) + "  " + this.comp.makeTimeString(this.sunSet));
        zf.resize(790, 490);
        zf.show();
    }
    
    public void jupiter() {
        final PlanetRiseSet planetRS = new PlanetRiseSet(this.date, this.month + 1, this.year + 1900, this.UT, this.latitude, this.longitude, 4, this.deltaT);
        this.declin = planetRS.declination();
        this.RA = planetRS.alpha();
        this.Mag = planetRS.mag();
        this.earthDist = planetRS.earthDistance();
        this.geoLambda = planetRS.earthLambda();
        this.angle = 60.0 * Math.atan2(1070400.0, this.earthDist * 1.4959787E8) / 0.017453292519943295;
        this.jupAngle = 3600.0 * Math.atan2(71492.0, this.earthDist * 1.4959787E8) / 0.017453292519943295;
        this.rr = 0.5 * this.jupAngle;
        this.rr *= this.zoom;
        this.RR = (int)Math.round(this.rr);
        this.km = 3600.0 * Math.atan2(100000.0, this.earthDist * 1.4959787E8) / 0.017453292519943295;
        this.km *= this.zoom;
        this.m1 = this.comp.magnitude(1, this.earthDist);
        this.m2 = this.comp.magnitude(2, this.earthDist);
        this.m3 = this.comp.magnitude(3, this.earthDist);
        this.m4 = this.comp.magnitude(4, this.earthDist);
        this.R1 = (int)Math.round(this.zoomMoons * 30.0 / this.m1);
        this.R2 = (int)Math.round(this.zoomMoons * 30.0 / this.m2);
        this.R3 = (int)Math.round(this.zoomMoons * 30.0 / this.m3);
        this.R4 = (int)Math.round(this.zoomMoons * 30.0 / this.m4);
        this.jupElev = planetRS.elev();
        this.AZ = this.comp.check360(planetRS.azim() + 180.0);
        this.hRise = this.comp.check24(planetRS.utRise() + this.locOffset);
        this.hSet = this.comp.check24(planetRS.utSet() + this.locOffset);
        this.trans = 0.5 * (this.hRise + this.hSet);
        final PlanetRiseSet pRS2 = new PlanetRiseSet(this.date, this.month + 1, this.year + 1900, this.trans - this.locOffset, this.latitude, this.longitude, 4, this.deltaT);
        double azim2 = pRS2.azim() + 180.0;
        azim2 = this.comp.check360(azim2);
        if (azim2 > 190.0 || azim2 < 170.0) {
            this.trans -= 12.0;
        }
        final PlanetRiseSet pRise = new PlanetRiseSet(this.date, this.month + 1, this.year + 1900, this.hRise - this.locOffset, this.latitude, this.longitude, 4, this.deltaT);
        this.jupRiseAz = pRise.azim() + 180.0;
        this.jupRiseAz = this.comp.check360(this.jupRiseAz);
        final PlanetRiseSet pSet = new PlanetRiseSet(this.date, this.month + 1, this.year + 1900, this.hSet - this.locOffset, this.latitude, this.longitude, 4, this.deltaT);
        this.jupSetAz = pSet.azim() + 180.0;
        this.jupSetAz = this.comp.check360(this.jupSetAz);
        final double jd = this.comp.JD(this.date, this.month + 1, this.year + 1900, this.UT);
        double LHA = this.comp.THETA0(jd) - planetRS.alpha() + this.longitude;
        LHA = this.comp.check360(LHA);
        this.PA = Math.asin(Math.cos(0.017453292519943295 * this.latitude) * Math.sin(0.017453292519943295 * LHA) / Math.sin(0.017453292519943295 * (90.0 - this.jupElev))) / 0.017453292519943295;
        final double planetBeta = planetRS.geoBeta();
        final double jupGeoLambda = planetRS.lambda();
        final double earthBeta = 0.0;
        final PlanetRiseSet pRS3 = new PlanetRiseSet(this.date, this.month + 1, this.year + 1900, this.UT, this.latitude, this.longitude, 0, this.deltaT);
        final double EarthLambda = pRS3.lambda();
        this.elong = Math.acos(Math.cos(0.017453292519943295 * (180.0 - EarthLambda + jupGeoLambda)) * Math.cos(0.017453292519943295 * planetBeta) * Math.cos(0.017453292519943295 * earthBeta) + Math.sin(0.017453292519943295 * planetBeta) * Math.sin(0.017453292519943295 * earthBeta)) / 0.017453292519943295;
        this.elong = 180.0 - this.elong;
        if (this.comp.check360(EarthLambda + 180.0) > jupGeoLambda) {
            this.elongStr = "  E";
        }
        else {
            this.elongStr = "  W";
        }
    }
    
    public void SunRiseSet() {
        this.computeSun(this.date, this.month, this.year);
    }
    
    public void paint(final Graphics g) {
        final RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        renderHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        final Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHints(renderHints);
        this.calculate(this.UT = this.hours - this.locOffset + this.minutes / 60.0, true);
        g.setColor(Color.white);
        g.fillRect(0, 0, 1000, 1000);
        g.setColor(Color.black);
        g.drawString(String.valueOf(this.versStr) + " (c) 2010 J. Giesen  -  www.GeoAstro.de", 50, this.size().height - 10);
        final int rechts = 635;
        g.setColor(Color.red);
        g.fillOval(rechts - this.R1, 85 - this.R1, 2 * this.R1, 2 * this.R1);
        g.drawString("Io", rechts + 20, 90);
        g.drawString(Math.round(100.0 * this.d1) / 100.0 + "'", rechts + 80, 90);
        g.drawString(Math.round(10.0 * this.m1) / 10.0 + " m", rechts - 45, 90);
        g.drawString(new StringBuffer().append(Math.round(10.0 * this.U1) / 10.0).append('°').toString(), rechts + 120, 90);
        g.setColor(Color.green);
        g.fillOval(rechts - this.R2, 100 - this.R2, 2 * this.R2, 2 * this.R2);
        g.drawString("Europa", rechts + 20, 105);
        g.drawString(Math.round(100.0 * this.d2) / 100.0 + "'", rechts + 80, 105);
        g.drawString(Math.round(10.0 * this.m2) / 10.0 + " m", rechts - 45, 105);
        g.drawString(new StringBuffer().append(Math.round(10.0 * this.U2) / 10.0).append('°').toString(), rechts + 120, 105);
        g.setColor(Color.blue);
        g.fillOval(rechts - this.R3, 115 - this.R3, 2 * this.R3, 2 * this.R3);
        g.drawString("Ganymede", rechts + 20, 120);
        g.drawString(Math.round(100.0 * this.d3) / 100.0 + "'", rechts + 80, 120);
        g.drawString(Math.round(10.0 * this.m3) / 10.0 + " m", rechts - 45, 120);
        g.drawString(new StringBuffer().append(Math.round(10.0 * this.U3) / 10.0).append('°').toString(), rechts + 120, 120);
        g.setColor(Color.magenta);
        g.fillOval(rechts - this.R4, 130 - this.R4, 2 * this.R4, 2 * this.R4);
        g.drawString("Callisto", rechts + 20, 135);
        g.drawString(Math.round(100.0 * this.d4) / 100.0 + "'", rechts + 80, 135);
        g.drawString(Math.round(10.0 * this.m4) / 10.0 + " m", rechts - 45, 135);
        g.drawString(new StringBuffer().append(Math.round(10.0 * this.U4) / 10.0).append('°').toString(), rechts + 120, 135);
        g.setColor(Color.lightGray);
        g.drawLine(0, this.y0, 800, this.y0);
        if (this.orb) {
            if (this.grid) {
                double dz = 2.0;
                if (this.zoom > 1.3) {
                    dz = 1.0;
                }
                for (int i = -10; i <= 10; ++i) {
                    this.X5 = this.earthDist * 1.4959787E8 * Math.tan(0.017453292519943295 * i * dz / 60.0);
                    this.X5 /= 71492.0;
                    final double r5 = this.X5 * this.rr;
                    final int x5min = (int)Math.round(r5);
                    g.setColor(Color.lightGray);
                    if (this.x0 - x5min < rechts - 50) {
                        g.drawLine(this.x0 - x5min, this.y0 - 280, this.x0 - x5min, this.y0 + 290);
                    }
                    else {
                        g.drawLine(this.x0 - x5min, this.y0 - 220, this.x0 - x5min, this.y0 + 290);
                    }
                    if (i == 4) {
                        g.drawLine(20, this.y0 - x5min, this.size().width - 220, this.y0 - x5min);
                    }
                    else {
                        g.drawLine(20, this.y0 - x5min, this.size().width - 20, this.y0 - x5min);
                    }
                    g.setColor(Color.darkGray);
                    g.drawString((int)(i * dz) + "'", this.x0 + x5min - 5, this.y0 + 305);
                }
            }
            if (this.gridR) {
                double dz2 = 2.0;
                if (this.zoom > 1.3) {
                    dz2 = 1.0;
                }
                for (int j = -7; j <= 7; ++j) {
                    final double rR = j * 4 * this.rr;
                    final int xJ = (int)Math.round(rR);
                    g.setColor(Color.lightGray);
                    if (this.x0 - xJ < rechts - 50) {
                        g.drawLine(this.x0 - xJ, this.y0 - 280, this.x0 - xJ, this.y0 + 290);
                    }
                    else {
                        g.drawLine(this.x0 - xJ, this.y0 - 220, this.x0 - xJ, this.y0 + 290);
                    }
                    if (this.y0 - xJ > 150) {
                        g.drawLine(70, this.y0 - xJ, this.size().width - 70, this.y0 - xJ);
                    }
                    else {
                        g.drawLine(70, this.y0 - xJ, this.size().width - 220, this.y0 - xJ);
                    }
                    g.setColor(Color.darkGray);
                    g.drawString((int)(dz2 * j * 2.0) + "r", this.x0 + xJ - 5, this.y0 + 305);
                }
            }
            if (this.KM) {
                for (int k = -10; k <= 10; ++k) {
                    final double rR2 = k * this.km;
                    final int xJ2 = (int)Math.round(rR2);
                    if (k % 5 == 0) {
                        g.setColor(Color.gray);
                    }
                    else {
                        g.setColor(Color.lightGray);
                    }
                    if (this.x0 - xJ2 < rechts - 50) {
                        g.drawLine(this.x0 - xJ2, this.y0 - 280, this.x0 - xJ2, this.y0 + 290);
                    }
                    else {
                        g.drawLine(this.x0 - xJ2, this.y0 - 220, this.x0 - xJ2, this.y0 + 290);
                    }
                    if (this.y0 - xJ2 > 150) {
                        g.drawLine(70, this.y0 - xJ2, this.size().width - 70, this.y0 - xJ2);
                    }
                    else {
                        g.drawLine(70, this.y0 - xJ2, this.size().width - 220, this.y0 - xJ2);
                    }
                    g.setColor(Color.darkGray);
                    if (k % 5 == 0) {
                        g.drawString(k * 100000 + " km", this.x0 + xJ2 - 5, this.y0 + 305);
                    }
                }
            }
            if (this.shadow) {
                final int[] sx = new int[4];
                final int[] sy = new int[4];
                sx[0] = this.x0 - (int)Math.round(280.0 * Math.sin(0.017453292519943295 * this.psi) - this.rr * Math.cos(0.017453292519943295 * this.psi));
                sy[0] = this.y0 - (int)Math.round(280.0 * Math.cos(0.017453292519943295 * this.psi));
                sx[1] = this.x0 + (int)Math.round(this.rr * Math.cos(0.017453292519943295 * this.psi));
                sy[1] = this.y0;
                sx[2] = this.x0 - (int)Math.round(this.rr * Math.cos(0.017453292519943295 * this.psi));
                sy[2] = this.y0;
                sx[3] = this.x0 - (int)Math.round(280.0 * Math.sin(0.017453292519943295 * this.psi) + this.rr * Math.cos(0.017453292519943295 * this.psi));
                sy[3] = this.y0 - (int)Math.round(280.0 * Math.cos(0.017453292519943295 * this.psi));
                g.setColor(Color.lightGray);
                g.fillPolygon(sx, sy, 4);
                g.setColor(Color.gray);
                g.drawLine(sx[0], sy[0], sx[1], sy[1]);
                g.drawLine(sx[2], this.y0, sx[3], sy[3]);
            }
            if (this.illum) {
                int x = this.x0 + (int)Math.round(300.0 * Math.sin(0.017453292519943295 * this.psi));
                int y = this.y0 + (int)Math.round(300.0 * Math.cos(0.017453292519943295 * this.psi));
                g.drawLine(this.x0, this.y0, x, y);
                g.setColor(Color.yellow);
                g.fillArc(this.x0 - this.RR, this.y0 - this.RR, 2 * this.RR, 2 * this.RR, (int)Math.round(this.psi), -180);
                g.setColor(Color.darkGray);
                g.fillArc(this.x0 - this.RR, this.y0 - this.RR, 2 * this.RR, 2 * this.RR, (int)Math.round(this.psi), 180);
                g.setColor(Color.black);
                x = this.x0 + (int)Math.round(this.RR * Math.cos(0.017453292519943295 * this.psi));
                y = this.y0 - (int)Math.round(this.RR * Math.sin(0.017453292519943295 * this.psi));
                g.drawLine(this.x0, this.y0, x, y);
                x = this.x0 - (int)Math.round(this.RR * Math.cos(0.017453292519943295 * this.psi));
                y = this.y0 + (int)Math.round(this.RR * Math.sin(0.017453292519943295 * this.psi));
                g.drawLine(this.x0, this.y0, x, y);
                g.drawString("Phase Angle:  " + Math.round(10.0 * this.psi) / 10.0 + '°' + "   Illum. Fract.: " + Math.round(1000.0 * this.illFrac) / 10.0 + "%", 50, 90);
            }
        }
        if (!this.orb) {
            if (this.grid) {
                double dz = 2.0;
                if (this.zoom > 1.3) {
                    dz = 1.0;
                }
                for (int j = -10; j <= 10; ++j) {
                    this.X5 = this.earthDist * 1.4959787E8 * Math.tan(0.017453292519943295 * j * dz / 60.0);
                    this.X5 /= 71492.0;
                    final double r5 = this.X5 * this.rr;
                    final int x5min = (int)Math.round(r5);
                    if (!this.pa) {
                        g.setColor(Color.lightGray);
                        g.drawLine(this.x0 - x5min, this.y0 - 30, this.x0 - x5min, this.y0 + 30);
                        g.setColor(Color.darkGray);
                        g.drawString((int)(dz * j) + "'", this.x0 + x5min - 5, this.y0 - 35);
                    }
                    else {
                        g.setColor(Color.lightGray);
                        g.drawLine(this.x0 - this.comp.rotateX(r5, -30.0, -this.posAngle), this.y0 - this.comp.rotateY(r5, -30.0, -this.posAngle), this.x0 - this.comp.rotateX(r5, 30.0, -this.posAngle), this.y0 - this.comp.rotateY(r5, 30.0, -this.posAngle));
                        g.setColor(Color.darkGray);
                        g.drawString((int)(dz * j) + "'", this.x0 - this.comp.rotateX(r5, -35.0, -this.posAngle), this.y0 - this.comp.rotateY(r5, -35.0, -this.posAngle));
                    }
                }
            }
            if (this.gridR) {
                double dz2 = 2.0;
                if (this.zoom > 1.3) {
                    dz2 = 1.0;
                }
                for (int j = -7; j <= 7; ++j) {
                    final double rR = j * 4 * this.rr;
                    final int xJ = (int)Math.round(rR);
                    if (!this.pa) {
                        g.setColor(Color.lightGray);
                        g.drawLine(this.x0 - xJ, this.y0 - 30, this.x0 - xJ, this.y0 + 30);
                        g.setColor(Color.darkGray);
                        g.drawString((int)(dz2 * j * 2.0) + "r", this.x0 + xJ - 5, this.y0 - 35);
                    }
                    else {
                        g.setColor(Color.lightGray);
                        g.drawLine(this.x0 - this.comp.rotateX(rR, -30.0, -this.posAngle), this.y0 - this.comp.rotateY(rR, -30.0, -this.posAngle), this.x0 - this.comp.rotateX(rR, 30.0, -this.posAngle), this.y0 - this.comp.rotateY(rR, 30.0, -this.posAngle));
                        g.setColor(Color.darkGray);
                        g.drawString((int)(dz2 * j * 2.0) + "r", this.x0 - this.comp.rotateX(rR, -35.0, -this.posAngle), this.y0 - this.comp.rotateY(rR, -35.0, -this.posAngle));
                    }
                }
            }
            if (this.KM) {
                for (int k = -10; k <= 10; ++k) {
                    final double rR2 = k * this.km;
                    final int xJ2 = (int)Math.round(rR2);
                    if (!this.pa) {
                        if (k % 5 == 0) {
                            g.setColor(Color.gray);
                        }
                        else {
                            g.setColor(Color.lightGray);
                        }
                        g.drawLine(this.x0 - xJ2, this.y0 - 30, this.x0 - xJ2, this.y0 + 30);
                        g.setColor(Color.darkGray);
                        if (k % 5 == 0) {
                            g.drawString(k * 100000 + " km", this.x0 + xJ2 - 5, this.y0 - 35);
                        }
                    }
                    else {
                        if (k % 5 == 0) {
                            g.setColor(Color.gray);
                        }
                        else {
                            g.setColor(Color.lightGray);
                        }
                        g.drawLine(this.x0 - this.comp.rotateX(rR2, -30.0, -this.posAngle), this.y0 - this.comp.rotateY(rR2, -30.0, -this.posAngle), this.x0 - this.comp.rotateX(rR2, 30.0, -this.posAngle), this.y0 - this.comp.rotateY(rR2, 30.0, -this.posAngle));
                        g.setColor(Color.darkGray);
                        if (k % 5 == 0) {
                            g.drawString(k * 100000 + " km", this.x0 - this.comp.rotateX(rR2, -35.0, -this.posAngle), this.y0 - this.comp.rotateY(rR2, -35.0, -this.posAngle));
                        }
                    }
                }
            }
            if (this.pa) {
                g.setColor(Color.lightGray);
                g.drawLine(0, this.y0, 800, this.y0);
                this.posAngle = this.PA - this.pAngle();
                int x = this.x0 + this.comp.rotateX(this.r4 * this.rr, 0.0, this.posAngle);
                int y = this.y0 - this.comp.rotateY(this.r4 * this.rr, 0.0, this.posAngle);
                g.setColor(Color.gray);
                g.drawLine(this.x0, this.y0, x, y);
                x = this.x0 - this.comp.rotateX(this.r4 * this.rr, 0.0, this.posAngle);
                y = this.y0 + this.comp.rotateY(this.r4 * this.rr, 0.0, this.posAngle);
                g.drawLine(this.x0, this.y0, x, y);
                this.x1 = this.comp.rotateX(this.X1, this.Y1, this.posAngle);
                this.y1 = this.comp.rotateY(this.X1, this.Y1, this.posAngle);
                this.x2 = this.comp.rotateX(this.X2, this.Y2, this.posAngle);
                this.y2 = this.comp.rotateY(this.X2, this.Y2, this.posAngle);
                this.x3 = this.comp.rotateX(this.X3, this.Y3, this.posAngle);
                this.y3 = this.comp.rotateY(this.X3, this.Y3, this.posAngle);
                this.x4 = this.comp.rotateX(this.X4, this.Y4, this.posAngle);
                this.y4 = this.comp.rotateY(this.X4, this.Y4, this.posAngle);
            }
            g.setColor(Color.black);
            g.drawString(String.valueOf(this.latString) + "  " + this.longString + ",    Sunrise: " + this.riseStr + "    Sunset: " + this.setStr, 40, 90);
            String s = "Rise: " + this.comp.makeTimeString(this.hRise) + "  Az " + Math.round(10.0 * this.jupRiseAz) / 10.0 + '°' + "   Set: " + this.comp.makeTimeString(this.hSet) + "  Az " + Math.round(10.0 * this.jupSetAz) / 10.0 + '°' + "     Transit: " + this.comp.makeTimeString(this.trans);
            g.drawString(s, 40, 105);
            if (this.jupElev > 0.0 && (this.comp.check24(this.UT + this.locOffset) < this.UTRISE || this.comp.check24(this.UT + this.locOffset) > this.UTSET)) {
                g.setColor(Color.blue);
            }
            else {
                g.setColor(Color.black);
            }
            s = "Altitude: " + Math.round(10.0 * this.jupElev) / 10.0 + '°' + "     Azimuth: " + Math.round(10.0 * this.AZ) / 10.0 + '°' + " (" + this.comp.azDirection(this.AZ) + ")";
            g.drawString(s, 355, 105);
            g.setColor(Color.black);
            g.drawString("Magn.: " + Math.round(10.0 * this.Mag) / 10.0 + "     Illum. Fract.: " + Math.round(1000.0 * this.illFrac) / 10.0 + "%     Earth Dist.: " + Math.round(1000.0 * this.earthDist) / 1000.0 + " AU     Radius: " + Math.round(100.0 * this.jupAngle) / 100.0 + "''     Geoc. Long.: " + Math.round(10.0 * this.geoLambda) / 10.0 + '°' + "  " + this.comp.tierkreisStr(this.geoLambda) + "  " + Math.round(10.0 * (this.geoLambda % 30.0)) / 10.0 + '°', 40, 120);
            s = "Declin.: " + Math.round(100.0 * this.declin) / 100.0 + '°' + "     RA: " + Math.round(100.0 * this.RA) / 100.0 + '°' + " =  " + this.comp.makeTimeString(this.RA / 15.0) + "     Elong. Jup. from Sun: " + Math.round(10.0 * this.elong) / 10.0 + '°' + this.elongStr + "     omega1: " + Math.round(10.0 * this.omega1) / 10.0 + '°';
            g.drawString(s, 40, 135);
            if (this.pa) {
                s = "Parallactic Angle: " + Math.round(10.0 * this.PA) / 10.0 + '°';
                s = String.valueOf(s) + "    Pos. Angle: " + Math.round(10.0 * this.posAngle) / 10.0 + '°';
                g.drawString(s, 40, 150);
            }
        }
        g.setColor(Color.black);
        g.drawOval(this.x0 - this.RR, this.y0 - this.RR, 2 * this.RR, 2 * this.RR);
        g.setColor(Color.red);
        if (!this.pa && !this.orb) {
            if (this.omega1 < 90.0 || this.omega1 > 270.0) {
                g.setColor(Color.red);
            }
            else {
                g.setColor(Color.gray);
            }
            g.drawLine(this.x0 + (int)Math.round(this.rr * Math.sin(0.017453292519943295 * this.omega1)), this.y0 - 3, this.x0 + (int)Math.round(this.rr * Math.sin(0.017453292519943295 * this.omega1)), this.y0 + 3);
        }
        if (this.orb) {
            g.setColor(Color.red);
            g.fillOval(this.x0 + (int)Math.round(this.rr * Math.sin(0.017453292519943295 * this.omega1)) - 2, this.y0 + (int)Math.round(this.rr * Math.cos(0.017453292519943295 * this.omega1)) - 2, 4, 4);
        }
        g.setColor(Color.red);
        double D = Math.sqrt(this.X1 * this.X1 + this.Y1 * this.Y1);
        if (D < this.rr) {
            if (this.u1 < 90.0 || this.u1 > 270.0) {
                g.fillOval(this.x0 + this.x1 - this.R1, this.y0 - this.y1 - this.R1, 2 * this.R1, 2 * this.R1);
            }
            else {
                g.drawOval(this.x0 + this.x1 - this.R1, this.y0 - this.y1 - this.R1, 2 * this.R1, 2 * this.R1);
            }
        }
        else {
            g.fillOval(this.x0 + this.x1 - this.R1, this.y0 - this.y1 - this.R1, 2 * this.R1, 2 * this.R1);
            if (Math.abs(this.X1) < this.rr) {
                g.setColor(Color.black);
                g.drawOval(this.x0 + this.x1 - this.R1, this.y0 - this.y1 - this.R1, 2 * this.R1, 2 * this.R1);
            }
        }
        g.setColor(Color.green);
        D = Math.sqrt(this.X2 * this.X2 + this.Y2 * this.Y2);
        if (D < this.rr) {
            if (this.u2 < 90.0 || this.u2 > 270.0) {
                g.fillOval(this.x0 + this.x2 - this.R2, this.y0 - this.y2 - this.R2, 2 * this.R2, 2 * this.R2);
            }
            else {
                g.drawOval(this.x0 + this.x2 - this.R2, this.y0 - this.y2 - this.R2, 2 * this.R2, 2 * this.R2);
            }
        }
        else {
            g.fillOval(this.x0 + this.x2 - this.R2, this.y0 - this.y2 - this.R2, 2 * this.R2, 2 * this.R2);
            if (Math.abs(this.X2) < this.rr) {
                g.setColor(Color.black);
                g.drawOval(this.x0 + this.x2 - this.R2, this.y0 - this.y2 - this.R2, 2 * this.R2, 2 * this.R2);
            }
        }
        g.setColor(Color.blue);
        D = Math.sqrt(this.X3 * this.X3 + this.Y3 * this.Y3);
        if (D < this.rr) {
            if (this.u3 < 90.0 || this.u3 > 270.0) {
                g.fillOval(this.x0 + this.x3 - this.R3, this.y0 - this.y3 - this.R3, 2 * this.R3, 2 * this.R3);
            }
            else {
                g.drawOval(this.x0 + this.x3 - this.R3, this.y0 - this.y3 - this.R3, 2 * this.R3, 2 * this.R3);
            }
        }
        else {
            g.fillOval(this.x0 + this.x3 - this.R3, this.y0 - this.y3 - this.R3, 2 * this.R3, 2 * this.R3);
            if (Math.abs(this.X3) < this.rr) {
                g.setColor(Color.black);
                g.drawOval(this.x0 + this.x3 - this.R3, this.y0 - this.y3 - this.R3, 2 * this.R3, 2 * this.R3);
            }
        }
        g.setColor(Color.magenta);
        D = Math.sqrt(this.X4 * this.X4 + this.Y4 * this.Y4);
        if (D < this.rr) {
            if (this.u4 < 90.0 || this.u4 > 270.0) {
                g.fillOval(this.x0 + this.x4 - this.R4, this.y0 - this.y4 - this.R4, 2 * this.R4, 2 * this.R4);
            }
            else {
                g.drawOval(this.x0 + this.x4 - this.R4, this.y0 - this.y4 - this.R4, 2 * this.R4, 2 * this.R4);
            }
        }
        else {
            g.fillOval(this.x0 + this.x4 - this.R4, this.y0 - this.y4 - this.R4, 2 * this.R4, 2 * this.R4);
            if (Math.abs(this.X4) < this.rr) {
                g.setColor(Color.black);
                g.drawOval(this.x0 + this.x4 - this.R4, this.y0 - this.y4 - this.R4, 2 * this.R4, 2 * this.R4);
            }
        }
        if (this.orb) {
            g.setColor(new Color(255, 150, 150));
            if (this.to) {
                g.drawLine(this.x0 + this.x1, this.y0 - this.y1, this.x0 + this.x1, this.y0);
            }
            if (this.eu) {
                g.drawLine(this.x0 + this.x1, this.y0 - this.y1, this.x0 + (int)Math.round(this.X1 + this.Y1 * Math.tan(0.017453292519943295 * this.psi)), this.y0);
            }
            g.setColor(new Color(150, 255, 150));
            if (this.to) {
                g.drawLine(this.x0 + this.x2, this.y0 - this.y2, this.x0 + this.x2, this.y0);
            }
            if (this.eu) {
                g.drawLine(this.x0 + this.x2, this.y0 - this.y2, this.x0 + (int)Math.round(this.X2 + this.Y2 * Math.tan(0.017453292519943295 * this.psi)), this.y0);
            }
            g.setColor(new Color(150, 150, 255));
            if (this.to) {
                g.drawLine(this.x0 + this.x3, this.y0 - this.y3, this.x0 + this.x3, this.y0);
            }
            if (this.eu) {
                g.drawLine(this.x0 + this.x3, this.y0 - this.y3, this.x0 + (int)Math.round(this.X3 + this.Y3 * Math.tan(0.017453292519943295 * this.psi)), this.y0);
            }
            g.setColor(new Color(255, 150, 255));
            if (this.to) {
                g.drawLine(this.x0 + this.x4, this.y0 - this.y4, this.x0 + this.x4, this.y0);
            }
            if (this.eu) {
                g.drawLine(this.x0 + this.x4, this.y0 - this.y4, this.x0 + (int)Math.round(this.X4 + this.Y4 * Math.tan(0.017453292519943295 * this.psi)), this.y0);
            }
            this.orbit();
            g.setColor(new Color(255, 150, 150));
            for (int l = 0; l < this.n1; ++l) {
                g.drawLine(this.x0 + this.x_1[l], this.y0 - this.y_1[l], this.x0 + this.x_1[l + 1], this.y0 - this.y_1[l + 1]);
            }
            g.setColor(new Color(150, 255, 150));
            for (int k = 0; k < this.n2; ++k) {
                g.drawLine(this.x0 + this.x_2[k], this.y0 - this.y_2[k], this.x0 + this.x_2[k + 1], this.y0 - this.y_2[k + 1]);
            }
            g.setColor(new Color(150, 150, 255));
            for (int m = 0; m < this.n3; ++m) {
                g.drawLine(this.x0 + this.x_3[m], this.y0 - this.y_3[m], this.x0 + this.x_3[m + 1], this.y0 - this.y_3[m + 1]);
            }
            g.setColor(new Color(255, 150, 255));
            for (int j = 0; j < this.n4; ++j) {
                g.drawLine(this.x0 + this.x_4[j], this.y0 - this.y_4[j], this.x0 + this.x_4[j + 1], this.y0 - this.y_4[j + 1]);
            }
        }
        g.clearRect(0, 0, 1000, 78);
        g.setColor(Color.red);
        g.drawRect(1, 1, this.size().width - 3, this.size().height - 3);
        if (this.demo) {
            g.setFont(new Font("Helvetica", 0, 128));
            g.drawString("D  E  M  O", 100, 280);
        }
    }
    
    public JupiterMoons089() {
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.versStr = "Jovian Moons 0.89  -  ";
        this.RR = 10;
        this.R1 = 4;
        this.R2 = 3;
        this.R3 = 5;
        this.R4 = 5;
        this.online = false;
        this.demo = true;
        this.x_1 = new int[50];
        this.y_1 = new int[50];
        this.x_2 = new int[90];
        this.y_2 = new int[90];
        this.x_3 = new int[180];
        this.y_3 = new int[180];
        this.x_4 = new int[210];
        this.y_4 = new int[210];
        this.n1 = 43;
        this.n2 = 86;
        this.n3 = 172;
        this.n4 = 201;
        this.jup = true;
        this.grid = true;
        this.orb = false;
        this.pa = false;
        this.illum = false;
        this.zoom = 1.0;
        this.shadow = false;
        this.zoomMoons = 1.0;
        this.to = true;
        this.eu = true;
        this.dt = true;
        this.gridR = false;
        this.KM = false;
    }
}
