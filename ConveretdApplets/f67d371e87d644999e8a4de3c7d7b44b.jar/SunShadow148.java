import java.awt.Frame;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Dialog;
import java.net.URL;
import java.awt.Label;
import java.awt.Insets;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.TextField;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Button;
import java.util.Date;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SunShadow148 extends Applet
{
    String versStr;
    boolean UV;
    int xMouse;
    int yMouse;
    final int mapOben = 305;
    final int mapRechts = 545;
    Image bild;
    Image bild1;
    Image bild2;
    Image bild3;
    Image bild4;
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
    double azimuth;
    public int locOffset;
    public int offset;
    int browserOffset;
    public Suncanvas myCan;
    public Compute comp;
    public Seloc loc;
    public Seloc time;
    public Mapcanvas mymapcanvas;
    Button button;
    public Choice locChoice;
    public Choice timeChoice;
    Choice heightChoice;
    Choice angleChoice;
    Checkbox summerBox;
    TextField fieldLatDeg;
    TextField fieldLongDeg;
    Choice ewChoice;
    Choice nsChoice;
    Choice hoursChoice;
    Choice minChoice;
    Choice dateChoice;
    Choice monthChoice;
    Choice yearChoice;
    String hoursStr;
    String minStr;
    String dateStr;
    String monthStr;
    String yearStr;
    TextField fieldHeight;
    String ewStr;
    String nsStr;
    String latStr;
    String longStr;
    public String locString;
    public String timeString;
    public String email;
    public String param;
    public String wwwStr;
    String[] dayArray;
    String[] monthArray;
    String str;
    boolean demo;
    String homeString;
    String homeLatStr;
    String homeLongStr;
    String timezoneStr;
    String dstStr;
    double homeLat;
    double homeLong;
    double usrLat;
    double usrLong;
    int usrOffset;
    int top;
    int clickLocOffset;
    boolean isSummer;
    int dst;
    boolean online;
    Button aboutButton;
    boolean usrSummer;
    boolean angleDemo;
    int[] x1;
    int[] x2;
    int[] x3;
    int[] x4;
    int[] y1;
    int[] y2;
    int[] y3;
    int[] y4;
    int xGnomon;
    int yGnomon;
    final int xL = 30;
    final int xOben = 60;
    final double K = 0.017453292519943295;
    int xKante;
    int yKante;
    int x0;
    int y0;
    int count;
    int angle;
    double[] H;
    double[] A;
    int[] poly1;
    int[] poly2;
    double gnomon;
    int gitter;
    String heightStr;
    Double heightDouble;
    double height;
    String heightInputStr;
    Button timeButton;
    Choice writeChoice;
    Checkbox countBox;
    boolean doCount;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 6345;
    }
    
    public void init() {
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        final Color background = new Color(235, 235, 255);
        this.setBackground(background);
        this.homeString = this.getParameter("location");
        this.homeLatStr = this.getParameter("latitude");
        this.homeLongStr = this.getParameter("longitude");
        this.timezoneStr = this.getParameter("timezone");
        this.dstStr = this.getParameter("daylightsaving");
        this.heightInputStr = this.getParameter("heightinput");
        final URL url = this.getCodeBase();
        this.str = url.toString();
        this.str = String.valueOf(this.str) + "01234567890123456789012345";
        this.wwwStr = this.str.substring(0, 27);
        this.bild = this.getImage(this.getDocumentBase(), "applet/sunview/a.gif");
        this.bild1 = this.getImage(this.getDocumentBase(), "applet/sunview/b.gif");
        this.bild2 = this.getImage(this.getDocumentBase(), "applet/sunview/c.gif");
        this.bild3 = this.getImage(this.getDocumentBase(), "applet/sunview/d.gif");
        this.bild4 = this.getImage(this.getDocumentBase(), "applet/sunview/e.gif");
        final MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(this.bild, 0);
        tracker.addImage(this.bild1, 0);
        tracker.addImage(this.bild2, 0);
        tracker.addImage(this.bild3, 0);
        tracker.addImage(this.bild4, 0);
        try {
            tracker.waitForID(0);
        }
        catch (Exception ex) {}
        this.dat = new Date();
        this.hours = this.dat.getHours();
        this.minutes = this.dat.getMinutes();
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth();
        this.year = this.dat.getYear();
        this.seconds = this.dat.getSeconds();
        final Date theDate = new Date();
        final int theOffset = -theDate.getTimezoneOffset() / 60;
        theDate.setMonth(6);
        final int theOffset2 = -theDate.getTimezoneOffset() / 60;
        if (theOffset == theOffset2) {
            this.isSummer = true;
        }
        else {
            this.isSummer = false;
        }
        this.browserOffset = this.dat.getTimezoneOffset();
        this.browserOffset = -this.browserOffset / 60;
        this.locOffset = this.browserOffset;
        this.timeString = String.valueOf(this.browserOffset) + " h";
        if (this.browserOffset > 0) {
            this.timeString = "+" + this.timeString;
        }
        this.timeString = "UT  " + this.timeString;
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        final Font f = new Font("Helvetica", 0, 10);
        this.setFont(f);
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(2, 20, 0, 0);
        Label L = new Label("Time Zone");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("Dayl. S.T.");
        gbc.insets = new Insets(2, 0, 0, 0);
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("Location");
        gbc.insets = new Insets(2, 0, 0, 0);
        gbl.setConstraints(L, gbc);
        this.add(L);
        gbc.weightx = 10.0;
        L = new Label("Latitude");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("N / S");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("Longitude");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("E / W");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("Obj. Height");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("Angle");
        gbl.setConstraints(L, gbc);
        this.add(L);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 20, 0, 0);
        this.time = new Seloc();
        this.timeChoice = new Choice();
        this.time.timeMenu(this.timeChoice);
        gbl.setConstraints(this.timeChoice, gbc);
        this.timeChoice.select(this.timeString);
        this.add(this.timeChoice);
        gbc.insets = new Insets(0, 0, 0, 0);
        gbl.setConstraints(this.summerBox = new Checkbox(), gbc);
        this.add(this.summerBox);
        if (this.isSummer) {
            this.summerBox.setState(true);
        }
        else {
            this.summerBox.setState(false);
        }
        this.locChoice = new Choice();
        this.locString = this.homeString;
        this.locChoice.addItem(this.locString);
        this.locChoice.addItem("_________");
        this.locChoice.select(this.locString);
        (this.loc = new Seloc()).locMenu(this.locChoice);
        gbc.insets = new Insets(0, 0, 0, 0);
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
            this.ewStr = "W";
        }
        else {
            this.ewStr = "E";
        }
        this.latStr = String.valueOf(Math.abs(this.latitude));
        gbl.setConstraints(this.fieldLatDeg = new TextField(this.latStr, 6), gbc);
        this.add(this.fieldLatDeg);
        (this.nsChoice = new Choice()).addItem("N");
        this.nsChoice.addItem("S");
        if (this.latitude >= 0.0) {
            this.nsChoice.select("N");
        }
        else {
            this.nsChoice.select("S");
        }
        gbl.setConstraints(this.nsChoice, gbc);
        this.add(this.nsChoice);
        this.longStr = String.valueOf(Math.abs(this.longitude));
        gbl.setConstraints(this.fieldLongDeg = new TextField(this.longStr, 6), gbc);
        this.add(this.fieldLongDeg);
        (this.ewChoice = new Choice()).addItem("E");
        this.ewChoice.addItem("W");
        if (this.longitude >= 0.0) {
            this.ewChoice.select("E");
        }
        else {
            this.ewChoice.select("W");
        }
        gbl.setConstraints(this.ewChoice, gbc);
        this.add(this.ewChoice);
        if (this.heightInputStr.equals("menu")) {
            this.heightChoice = new Choice();
            for (int i = 1; i < 31; ++i) {
                this.heightChoice.addItem(String.valueOf(String.valueOf(i)) + " m");
            }
            gbc.insets = new Insets(0, 0, 0, 5);
            gbl.setConstraints(this.heightChoice, gbc);
            this.heightChoice.select(9);
            this.add(this.heightChoice);
        }
        else {
            this.fieldHeight = new TextField("10.0", 7);
            gbc.insets = new Insets(0, 0, 0, 5);
            gbl.setConstraints(this.fieldHeight, gbc);
            this.add(this.fieldHeight);
        }
        this.angleChoice = new Choice();
        for (int i = 0; i < 38; ++i) {
            this.angleChoice.addItem(String.valueOf(String.valueOf(i * 5)) + '°');
        }
        gbc.insets = new Insets(0, 0, 0, 10);
        gbl.setConstraints(this.angleChoice, gbc);
        this.angleChoice.select(0);
        this.add(this.angleChoice);
        gbc.gridy = 2;
        L = new Label("Local Time (h:m)");
        gbc.insets = new Insets(7, 10, 0, 0);
        gbl.setConstraints(L, gbc);
        this.add(L);
        gbc.insets = new Insets(7, 0, 0, 0);
        this.hoursChoice = new Choice();
        for (int j = 0; j < 10; ++j) {
            this.hoursStr = "0" + String.valueOf(j);
            this.hoursChoice.addItem(this.hoursStr);
        }
        for (int k = 10; k < 24; ++k) {
            this.hoursStr = String.valueOf(k);
            this.hoursChoice.addItem(this.hoursStr);
        }
        this.hoursChoice.select(this.hours);
        gbl.setConstraints(this.hoursChoice, gbc);
        this.add(this.hoursChoice);
        this.minChoice = new Choice();
        for (int l = 0; l < 10; ++l) {
            this.minStr = "0" + String.valueOf(l);
            this.minChoice.addItem(this.minStr);
        }
        for (int m = 10; m < 60; ++m) {
            this.minStr = String.valueOf(m);
            this.minChoice.addItem(this.minStr);
        }
        this.minChoice.select(this.minutes);
        gbl.setConstraints(this.minChoice, gbc);
        this.add(this.minChoice);
        L = new Label("Date");
        gbc.insets = new Insets(10, 10, 0, 0);
        gbl.setConstraints(L, gbc);
        this.add(L);
        gbc.insets = new Insets(7, 0, 0, 0);
        this.yearChoice = new Choice();
        for (int i2 = 0; i2 < 20; ++i2) {
            this.yearStr = String.valueOf(this.year + 1900 - 9 + i2);
            this.yearChoice.addItem(this.yearStr);
        }
        this.yearStr = String.valueOf(this.year + 1900);
        this.yearChoice.select(this.yearStr);
        gbc.insets = new Insets(10, 0, 0, 10);
        gbl.setConstraints(this.yearChoice, gbc);
        this.add(this.yearChoice);
        this.monthChoice = new Choice();
        for (int i3 = 0; i3 < 12; ++i3) {
            this.monthChoice.addItem(this.monthArray[i3]);
        }
        this.monthChoice.select(this.month);
        gbl.setConstraints(this.monthChoice, gbc);
        this.add(this.monthChoice);
        this.dateChoice = new Choice();
        for (int i4 = 1; i4 < 32; ++i4) {
            this.dateStr = String.valueOf(i4);
            this.dateChoice.addItem(this.dateStr);
        }
        this.dateChoice.select(this.date - 1);
        gbl.setConstraints(this.dateChoice, gbc);
        this.add(this.dateChoice);
        (this.button = new Button()).setLabel("Now");
        gbc.insets = new Insets(5, 0, 0, 0);
        gbl.setConstraints(this.button, gbc);
        this.add(this.button);
        this.timeButton = new Button("Get Time");
        gbc.insets = new Insets(5, 0, 0, 10);
        gbl.setConstraints(this.timeButton, gbc);
        this.add(this.timeButton);
        gbc.insets = new Insets(5, 0, 0, 0);
        gbc.gridy = 3;
        gbc.weighty = 200.0;
        gbl.setConstraints(this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr), gbc);
        this.add(this.myCan);
        gbc.gridy = 4;
        gbc.weighty = 1.0;
        gbl.setConstraints(this.mymapcanvas = new Mapcanvas(this.dat, this.latitude, this.longitude, this.locOffset), gbc);
        this.add(this.mymapcanvas);
        final GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 8;
        gbc2.weightx = 0.0;
        gbc2.gridy = 12;
        gbc2.weighty = 6.0;
        this.aboutButton = new Button();
        gbc2.insets = new Insets(0, 0, 30, 20);
        gbl.setConstraints(this.aboutButton, gbc2);
        this.aboutButton.setLabel("About...");
        this.add(this.aboutButton);
        gbc2.gridx = 7;
        (this.writeChoice = new Choice()).addItem("Write...");
        this.writeChoice.addItem("Table 60 min");
        this.writeChoice.addItem("Table 15 min");
        this.writeChoice.addItem("Table 5 min");
        this.writeChoice.addItem("Az/Elev/Time");
        gbc2.insets = new Insets(0, 0, 30, 20);
        gbl.setConstraints(this.writeChoice, gbc2);
        this.add(this.writeChoice);
        this.countBox = new Checkbox("Trace");
        gbc2.gridx = 7;
        gbc2.insets = new Insets(0, 0, -30, 20);
        gbl.setConstraints(this.countBox, gbc2);
        this.countBox.setState(this.doCount);
        this.add(this.countBox);
        boolean ok = false;
        this.demo = true;
        this.online = false;
        this.comp = new Compute();
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
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
            if (this.email.length() == 0 | Integer.parseInt(this.param) != this.formula(this.email, this.email.length())) {
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
                final Dialog AboutDialog = new Aboutdialog(this, this.versStr, this.demo);
                AboutDialog.resize(350, 230);
                AboutDialog.show();
            }
        }
        this.repaint();
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
        y = this.dat.getYear() + 1900;
        if (m == 0 | m == 2 | m == 4 | m == 6 | m == 7 | m == 9 | m == 11) {
            n = 31;
        }
        if (m == 3 | m == 5 | m == 8 | m == 10) {
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
    
    public void paint(final Graphics g) {
        g.clearRect(0, 0, this.size().width, this.size().height);
        final double STD = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        final double jd = this.comp.JD(this.date, this.month + 1, this.year + 1900, STD);
        final double dec = this.comp.DeclinationRightAscension(1, jd);
        this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, STD);
        this.hoehe = this.comp.computeHeight(dec, this.latitude, this.longitude, this.GHA);
        this.azimuth = this.comp.computeAzimut(dec, this.latitude, this.longitude, this.GHA, this.hoehe);
        final double azm = this.azimuth - 180.0;
        if (this.hoehe >= -0.83) {
            this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
        }
        if (this.hoehe < -0.83 && this.hoehe >= -6.0) {
            this.myCan = new Suncanvas(this.bild1, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
        }
        if (this.hoehe < -6.0 && this.hoehe >= -12.0) {
            this.myCan = new Suncanvas(this.bild2, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
        }
        if (this.hoehe < -12.0 && this.hoehe >= -18.0) {
            this.myCan = new Suncanvas(this.bild3, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
        }
        if (this.hoehe < -18.0) {
            this.myCan = new Suncanvas(this.bild4, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
        }
        this.mymapcanvas = new Mapcanvas(this.dat, this.latitude, this.longitude, this.locOffset);
        this.hoehe += this.comp.refract(this.hoehe);
        this.H[this.count] = this.hoehe;
        this.A[this.count] = azm;
        final double r = Math.sqrt(this.xKante * this.xKante + this.yKante * this.yKante);
        final double w1 = Math.atan2(this.yKante, this.xKante) / 0.017453292519943295;
        final double w2 = Math.atan2(-this.yKante, this.xKante) / 0.017453292519943295;
        final double w3 = Math.atan2(this.yKante, this.xKante) / 0.017453292519943295;
        final double w4 = Math.atan2(-this.yKante, this.xKante) / 0.017453292519943295;
        final int xM1 = (int)Math.round(30 + this.x0 - r * Math.cos(0.017453292519943295 * (w1 + this.angle)));
        final int yM1 = (int)Math.round(this.y0 - r * Math.sin(0.017453292519943295 * (w1 + this.angle)));
        final int xM2 = (int)Math.round(30 + this.x0 - r * Math.cos(0.017453292519943295 * (w2 + this.angle)));
        final int yM2 = (int)Math.round(this.y0 - r * Math.sin(0.017453292519943295 * (w2 + this.angle)));
        final int xM3 = (int)Math.round(30 + this.x0 + r * Math.cos(0.017453292519943295 * (w3 + this.angle)));
        final int yM3 = (int)Math.round(this.y0 + r * Math.sin(0.017453292519943295 * (w3 + this.angle)));
        final int xM4 = (int)Math.round(30 + this.x0 + r * Math.cos(0.017453292519943295 * (w4 + this.angle)));
        final int yM4 = (int)Math.round(this.y0 + r * Math.sin(0.017453292519943295 * (w4 + this.angle)));
        if (this.doCount) {
            for (int i = 0; i <= this.count - 1; ++i) {
                if (this.H[i] > 0.0) {
                    g.setColor(Color.black);
                    this.gnomon = this.height * 2.5 / Math.tan(0.017453292519943295 * this.H[i]);
                    this.yGnomon = (int)Math.round(this.gnomon * Math.cos(0.017453292519943295 * this.A[i]));
                    this.xGnomon = (int)Math.round(this.gnomon * Math.sin(0.017453292519943295 * this.A[i]));
                    this.x1[i] = xM1 + this.xGnomon;
                    this.y1[i] = yM1 - this.yGnomon;
                    this.x2[i] = xM2 + this.xGnomon;
                    this.y2[i] = yM2 - this.yGnomon;
                    this.x3[i] = xM3 + this.xGnomon;
                    this.y3[i] = yM3 - this.yGnomon;
                    this.x4[i] = xM4 + this.xGnomon;
                    this.y4[i] = yM4 - this.yGnomon;
                    g.setColor(Color.gray);
                    this.poly1 = new int[5];
                    this.poly2 = new int[5];
                    this.poly1[0] = this.x1[i];
                    this.poly1[1] = this.x2[i];
                    this.poly1[2] = this.x3[i];
                    this.poly1[3] = this.x4[i];
                    this.poly2[0] = this.y1[i];
                    this.poly2[1] = this.y2[i];
                    this.poly2[2] = this.y3[i];
                    this.poly2[3] = this.y4[i];
                    g.fillPolygon(this.poly1, this.poly2, 4);
                    g.drawLine(xM1, yM1, this.x1[i], this.y1[i]);
                    g.drawLine(xM2, yM2, this.x2[i], this.y2[i]);
                    g.drawLine(xM3, yM3, this.x3[i], this.y3[i]);
                    g.drawLine(xM4, yM4, this.x4[i], this.y4[i]);
                    if (this.angle == 0) {
                        g.setColor(Color.gray);
                        g.fillRect(this.x1[i], this.y1[i], 2 * this.xKante, 2 * this.yKante);
                    }
                    g.setColor(Color.black);
                    g.drawLine(this.x1[i], this.y1[i], this.x2[i], this.y2[i]);
                    g.drawLine(this.x2[i], this.y2[i], this.x3[i], this.y3[i]);
                    g.drawLine(this.x3[i], this.y3[i], this.x4[i], this.y4[i]);
                    g.drawLine(this.x4[i], this.y4[i], this.x1[i], this.y1[i]);
                }
            }
        }
        g.setColor(Color.gray);
        if (this.hoehe > 0.0) {
            this.gnomon = this.height * 2.5 / Math.tan(0.017453292519943295 * this.hoehe);
            this.yGnomon = (int)Math.round(this.gnomon * Math.cos(0.017453292519943295 * azm));
            this.xGnomon = (int)Math.round(this.gnomon * Math.sin(0.017453292519943295 * azm));
            this.x1[this.count] = xM1 + this.xGnomon;
            this.y1[this.count] = yM1 - this.yGnomon;
            g.drawLine(xM1, yM1, this.x1[this.count], this.y1[this.count]);
            this.x2[this.count] = xM2 + this.xGnomon;
            this.y2[this.count] = yM2 - this.yGnomon;
            g.drawLine(xM2, yM2, this.x2[this.count], this.y2[this.count]);
            this.x3[this.count] = xM3 + this.xGnomon;
            this.y3[this.count] = yM3 - this.yGnomon;
            g.drawLine(xM3, yM3, this.x3[this.count], this.y3[this.count]);
            this.x4[this.count] = xM4 + this.xGnomon;
            this.y4[this.count] = yM4 - this.yGnomon;
            g.drawLine(xM4, yM4, this.x4[this.count], this.y4[this.count]);
            g.setColor(Color.black);
            if (this.angle == 0) {
                g.fillRect(this.x1[this.count], this.y1[this.count], 2 * this.xKante, 2 * this.yKante);
            }
            else {
                g.drawLine(this.x1[this.count], this.y1[this.count], this.x2[this.count], this.y2[this.count]);
                g.drawLine(this.x2[this.count], this.y2[this.count], this.x3[this.count], this.y3[this.count]);
                g.drawLine(this.x3[this.count], this.y3[this.count], this.x4[this.count], this.y4[this.count]);
                g.drawLine(this.x4[this.count], this.y4[this.count], this.x1[this.count], this.y1[this.count]);
                this.poly1 = new int[4];
                this.poly2 = new int[4];
                this.poly1[0] = this.x1[this.count];
                this.poly1[1] = this.x2[this.count];
                this.poly1[2] = this.x3[this.count];
                this.poly1[3] = this.x4[this.count];
                this.poly2[0] = this.y1[this.count];
                this.poly2[1] = this.y2[this.count];
                this.poly2[2] = this.y3[this.count];
                this.poly2[3] = this.y4[this.count];
                g.fillPolygon(this.poly1, this.poly2, 4);
            }
            g.setColor(Color.red);
            this.str = this.hours + ":";
            if (this.minutes < 10) {
                this.str = String.valueOf(this.str) + "0" + this.minutes;
            }
            else {
                this.str = String.valueOf(this.str) + this.minutes;
            }
            g.drawString(this.str, this.x1[this.count], this.y1[this.count]);
        }
        for (int i = -9; i <= 9; ++i) {
            if (Math.abs(i) == 5) {
                g.setColor(Color.black);
            }
            else {
                g.setColor(Color.gray);
            }
            g.drawLine(30 + this.x0 + i * this.gitter, 305, 30 + this.x0 + i * this.gitter, 705);
        }
        for (int j = -7; j <= 7; ++j) {
            if (Math.abs(j) == 5) {
                g.setColor(Color.black);
            }
            else {
                g.setColor(Color.gray);
            }
            g.drawLine(30, this.y0 + j * this.gitter, 30 + 2 * this.x0, this.y0 + j * this.gitter);
        }
        g.setColor(Color.red);
        g.drawRect(30, 305, 2 * this.x0 - 1, 400);
        g.setColor(Color.black);
        g.drawLine(30, this.y0, 530, this.y0);
        g.drawLine(30 + this.x0, 305, 30 + this.x0, 705);
        g.setColor(Color.red);
        if (this.angle == 0) {
            g.fillRect(30 + this.x0 - this.xKante, this.y0 - this.yKante, 2 * this.xKante, 2 * this.yKante);
        }
        g.clearRect(30 + 2 * this.x0, 0, 500, 750);
        g.clearRect(0, 0, 30, 750);
        g.clearRect(0, 706, 780, 750);
        g.clearRect(0, 0, 750, 305);
        g.clearRect(30 + this.x0 - 10, 308, 20, 20);
        g.drawString("N", 30 + this.x0 - 3, 320);
        g.clearRect(35, this.y0 - 5, 20, 20);
        g.drawString("W", 40, this.y0 + 5);
        g.setColor(Color.red);
        this.poly1 = new int[4];
        this.poly2 = new int[4];
        this.poly1[0] = xM1;
        this.poly1[1] = xM2;
        this.poly1[2] = xM3;
        this.poly1[3] = xM4;
        this.poly2[0] = yM1;
        this.poly2[1] = yM2;
        this.poly2[2] = yM3;
        this.poly2[3] = yM4;
        g.fillPolygon(this.poly1, this.poly2, 4);
        if (this.dst == 0) {
            g.drawString("Standard Time only", 25, 85);
        }
        Font f = new Font("Helvetica", 0, 10);
        g.setFont(f);
        g.setColor(Color.red);
        g.drawString("Location:  " + this.locString, 545, 335);
        g.setColor(Color.black);
        this.str = new StringBuffer().append(Math.abs(Math.round(100.0 * this.latitude) / 100.0)).append('°').toString();
        if (this.latitude >= 0.0) {
            this.str = String.valueOf(this.str) + "  N";
        }
        else {
            this.str = String.valueOf(this.str) + "  S";
        }
        g.drawString("Latitude", 545, 355);
        g.drawString(this.str, 640, 355);
        this.str = new StringBuffer().append(Math.abs(Math.round(100.0 * this.longitude) / 100.0)).append('°').toString();
        if (this.longitude >= 0.0) {
            this.str = String.valueOf(this.str) + "  E";
        }
        else {
            this.str = String.valueOf(this.str) + "  W";
        }
        g.drawString("Longitude ", 545, 370);
        g.drawString(this.str, 640, 370);
        this.mymapcanvas.update(g);
        this.myCan.update(g);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        g.setColor(Color.red);
        g.drawString("Click to resize the object", 545, 435);
        g.setColor(Color.black);
        g.drawString("Object: " + Math.round(this.xKante / 1.25) + " m x " + Math.round(this.yKante / 1.25) + " m;  Height: " + this.height + " m", 545, 450);
        g.drawString("Grid size: 10 m", 545, 465);
        final double length = this.height / Math.tan(0.017453292519943295 * this.hoehe);
        if (this.hoehe > 0.0) {
            g.drawString("Shadow  length: " + Math.round(100.0 * length) / 100.0 + " m", 545, 480);
        }
        g.setColor(Color.red);
        String usrStr = "";
        if (this.demo) {
            usrStr = "demo running";
            f = g.getFont();
            g.setFont(new Font("Chicago", 0, 110));
            g.setColor(Color.red);
            g.drawString("D", 100, 150);
            g.drawString("E", 250, 227);
            g.drawString("M", 400, 390);
            g.drawString("O", 550, 510);
            g.setFont(f);
        }
        if (this.demo) {
            g.drawString("DEMO running", 545, 565);
        }
        if (this.online) {
            g.drawString("running online", 545, 565);
        }
        if (!this.demo && !this.online) {
            g.drawString(this.email, 545, 585);
        }
        if (this.angleDemo) {
            g.setFont(new Font("Chicago", 0, 96));
            g.drawString("D E M O", 100, 450);
        }
    }
    
    public boolean mouseDown(final Event event, final int x, final int y) {
        this.xMouse = x;
        this.yMouse = y;
        final String str = "";
        if (this.xMouse >= 30 && this.xMouse <= 482 && this.yMouse >= 305 && this.yMouse <= 629) {
            this.xKante = Math.abs(this.xMouse - this.x0 - 30);
            this.yKante = Math.abs(this.yMouse - this.y0);
            this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
            this.repaint();
            return true;
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int code) {
        int n = 0;
        final int cmd = 0;
        if (code != 110 && code != 78 && code != 104 && code != 100 && code != 109 && code != 72 && code != 68 && code != 77) {
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
            this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
            this.mymapcanvas = new Mapcanvas(this.dat, this.latitude, this.longitude, this.locOffset);
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
            this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
            this.mymapcanvas = new Mapcanvas(this.dat, this.latitude, this.longitude, this.locOffset);
            this.repaint();
            return true;
        }
        if (code == 72 || code == 104) {
            ++this.count;
            this.count %= 24;
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
            this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
            this.mymapcanvas = new Mapcanvas(this.dat, this.latitude, this.longitude, this.locOffset);
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
            this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
            this.mymapcanvas = new Mapcanvas(this.dat, this.latitude, this.longitude, this.locOffset);
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
            this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
            this.mymapcanvas = new Mapcanvas(this.dat, this.latitude, this.longitude, this.locOffset);
            this.repaint();
            return true;
        }
        return false;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        final double oldLat = this.latitude;
        final double oldLong = this.longitude;
        if (event.target instanceof Checkbox) {
            if (event.target == this.summerBox) {
                if (this.summerBox.getState()) {
                    this.isSummer = true;
                }
                else {
                    this.isSummer = false;
                }
                if (this.locChoice.getSelectedItem().equals("User Input")) {
                    this.offset = this.usrOffset;
                    if (!this.isSummer) {
                        this.offset = this.usrOffset - 1;
                    }
                    if (this.isSummer) {
                        this.offset = this.usrOffset + 1;
                    }
                    this.usrSummer = this.summerBox.getState();
                }
                else {
                    this.offset = (int)this.loc.getLatLong(this.locString, 3);
                    if (this.isSummer) {
                        ++this.offset;
                    }
                }
                if (this.latitude == 0.0 && this.longitude == 0.0) {
                    this.offset = 0;
                    if (this.summerBox.getState()) {
                        ++this.offset;
                    }
                }
                if (this.offset > 0) {
                    this.str = "UT  +" + this.offset + " h";
                }
                if (this.offset < 0) {
                    this.str = "UT  " + this.offset + " h";
                }
                if (this.offset == 0) {
                    this.str = "UT  +/- 0 h";
                }
                this.timeChoice.select(this.str);
                this.usrOffset = this.offset;
                final int gmtHours = this.hours - this.locOffset;
                this.hours = gmtHours + this.offset;
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
                            this.dat.setYear(this.year);
                        }
                        this.date = this.daysInMonth(this.month, this.year);
                        this.dat.setMonth(this.month);
                        this.monthChoice.select(this.month);
                    }
                }
                this.dat.setDate(this.date);
                this.hoursChoice.select(this.hours);
                this.dat.setHours(this.hours);
                this.hoursStr = String.valueOf(this.hours);
                if (this.hours < 10) {
                    this.hoursStr = "0" + this.hoursStr;
                }
                this.dateChoice.select(this.date - 1);
                this.monthChoice.select(this.month);
                this.locOffset = this.offset;
                this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
                this.mymapcanvas = new Mapcanvas(this.dat, this.latitude, this.longitude, this.locOffset);
                this.repaint();
                return true;
            }
            if (event.target == this.countBox) {
                this.doCount ^= true;
                this.countBox.setState(this.doCount);
                if (!this.doCount) {
                    this.count = 1;
                }
                this.repaint();
                return true;
            }
        }
        if (event.target instanceof Button) {
            if (event.target == this.timeButton) {
                final double hTrans = this.myCan.sunTrans();
                final Frame hf = new horizonFrame(this.dat, this.latitude, this.longitude, this.locOffset, hTrans, this.height);
                hf.resize(420, 150);
                hf.show();
                return true;
            }
            if (event.target == this.aboutButton) {
                final Dialog AboutDialog = new Aboutdialog(this, this.versStr, this.demo);
                AboutDialog.resize(350, 230);
                AboutDialog.show();
            }
            if (event.target == this.button) {
                this.count = 0;
                this.dat = new Date();
                this.hours = this.dat.getHours();
                this.minutes = this.dat.getMinutes();
                this.minChoice.select(this.minutes);
                this.date = this.dat.getDate();
                this.month = this.dat.getMonth();
                final int gmtHours = this.hours + this.dat.getTimezoneOffset() / 60;
                if (this.hours >= 24) {
                    this.hours -= 24;
                    ++this.date;
                    final int n = this.daysInMonth(this.month, this.year);
                    if (this.date > n) {
                        this.date = 1;
                        ++this.month;
                        if (this.month > 11) {
                            this.month = 0;
                            ++this.year;
                            this.dat.setYear(this.year);
                        }
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
                        this.date = this.daysInMonth(this.month, this.year);
                    }
                }
                this.dat.setHours(this.hours);
                this.dat.setDate(this.date);
                this.dat.setMonth(this.month);
                this.hoursChoice.select(this.hours);
                this.dateChoice.select(this.date - 1);
                this.monthChoice.select(this.month);
                this.year = this.dat.getYear();
                this.yearStr = String.valueOf(this.year + 1900);
                this.yearChoice.select(this.yearStr);
                this.hoursStr = String.valueOf(this.hours);
                if (this.hours < 10) {
                    this.hoursStr = "0" + this.hoursStr;
                }
                this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
                this.mymapcanvas = new Mapcanvas(this.dat, this.latitude, this.longitude, this.locOffset);
                this.repaint();
                return true;
            }
        }
        if (event.target instanceof TextField) {
            this.str = "";
            final char c1 = ',';
            if (event.target == this.fieldHeight) {
                this.str = this.fieldHeight.getText();
                if (this.str.length() == 0 || this.str.charAt(0) == '.' || this.str.charAt(0) == ',') {
                    this.fieldHeight.setText("10.0");
                    this.height = 10.0;
                    this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
                    this.mymapcanvas = new Mapcanvas(this.dat, this.latitude, this.longitude, this.locOffset);
                    this.repaint();
                    return true;
                }
                for (int i = 0; i < this.str.length(); ++i) {
                    final char c2 = this.str.charAt(i);
                    if (c2 == ',') {
                        this.str = String.valueOf(this.str.substring(0, i)) + '.' + this.str.substring(i + 1, this.str.length());
                    }
                }
                for (int j = 0; j < this.str.length(); ++j) {
                    final char c2 = this.str.charAt(j);
                    if (c2 != '0' && c2 != '1' && c2 != '2' && c2 != '3' && c2 != '4' && c2 != '5' && c2 != '6' && c2 != '7' && c2 != '8' && c2 != '9' && c2 != '.') {
                        this.fieldHeight.setText("10.0");
                        this.height = 10.0;
                        this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
                        this.mymapcanvas = new Mapcanvas(this.dat, this.latitude, this.longitude, this.locOffset);
                        this.repaint();
                        return true;
                    }
                }
                this.heightStr = this.str;
                this.heightDouble = Double.valueOf(this.heightStr);
                this.height = this.heightDouble;
            }
            if (event.target == this.fieldLatDeg) {
                this.str = this.fieldLatDeg.getText();
                if (this.str.length() == 0 || this.str.charAt(0) == '.' || this.str.charAt(0) == ',') {
                    this.fieldLatDeg.setText(String.valueOf(Math.abs(Math.round(100.0 * oldLat) / 100.0)));
                    return true;
                }
                for (int i = 0; i < this.str.length(); ++i) {
                    final char c2 = this.str.charAt(i);
                    if (c2 == ',') {
                        this.str = String.valueOf(this.str.substring(0, i)) + '.' + this.str.substring(i + 1, this.str.length());
                    }
                }
                for (int j = 0; j < this.str.length(); ++j) {
                    final char c2 = this.str.charAt(j);
                    if (c2 != '0' && c2 != '1' && c2 != '2' && c2 != '3' && c2 != '4' && c2 != '5' && c2 != '6' && c2 != '7' && c2 != '8' && c2 != '9' && c2 != '.') {
                        this.fieldLatDeg.setText(String.valueOf(Math.abs(Math.round(100.0 * oldLat) / 100.0)));
                        return true;
                    }
                }
                if (!this.str.equals(this.latStr) || this.str.length() == 0) {
                    this.locString = "User Input";
                    this.locChoice.select(this.locString);
                    final Double latDouble = Double.valueOf(this.str);
                    this.usrLat = latDouble;
                    this.usrLong = this.longitude;
                }
                this.latStr = this.str;
                final Double latDouble = Double.valueOf(this.latStr);
                this.latitude = latDouble;
                if (this.nsChoice.getSelectedItem().equals("S")) {
                    this.latitude = -Math.abs(this.latitude);
                    this.usrLat = this.latitude;
                }
                if (Math.abs(this.latitude) > 90.0) {
                    this.latitude = oldLat;
                    this.latStr = String.valueOf(Math.abs(Math.round(100.0 * oldLat) / 100.0));
                    this.fieldLatDeg.setText(this.latStr);
                }
                else {
                    if (!this.str.equals(this.latStr) || this.str.length() == 0) {
                        this.latStr = this.str;
                        this.locString = "User Input";
                        this.locChoice.select(this.locString);
                    }
                    this.latStr = String.valueOf(Math.abs(Math.round(100.0 * this.latitude) / 100.0));
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                    this.count = 0;
                    this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
                    this.mymapcanvas = new Mapcanvas(this.dat, this.latitude, this.longitude, this.locOffset);
                }
                this.fieldLatDeg.nextFocus();
                this.fieldLongDeg.selectAll();
                this.repaint();
                return true;
            }
            else if (event.target == this.fieldLongDeg) {
                this.str = this.fieldLongDeg.getText();
                if (this.str.length() == 0 || this.str.charAt(0) == '.' || this.str.charAt(0) == ',') {
                    this.fieldLongDeg.setText(String.valueOf(Math.abs(Math.round(100.0 * oldLong) / 100.0)));
                    return true;
                }
                for (int i = 0; i < this.str.length(); ++i) {
                    final char c2 = this.str.charAt(i);
                    if (c2 == ',') {
                        this.str = String.valueOf(this.str.substring(0, i)) + '.' + this.str.substring(i + 1, this.str.length());
                    }
                }
                for (int j = 0; j < this.str.length(); ++j) {
                    final char c2 = this.str.charAt(j);
                    if (c2 != '0' && c2 != '1' && c2 != '2' && c2 != '3' && c2 != '4' && c2 != '5' && c2 != '6' && c2 != '7' && c2 != '8' && c2 != '9' && c2 != '.') {
                        this.fieldLongDeg.setText(String.valueOf(Math.abs(Math.round(100.0 * oldLong) / 100.0)));
                        return true;
                    }
                }
                if (!this.str.equals(this.longStr) || this.str.length() == 0) {
                    this.locString = "User Input";
                    this.locChoice.select(this.locString);
                    final Double longDouble = Double.valueOf(this.str);
                    this.usrLong = longDouble;
                    this.usrLat = this.latitude;
                }
                final Double longDouble = Double.valueOf(this.str);
                this.longitude = longDouble;
                if (this.ewChoice.getSelectedItem().equals("W")) {
                    this.longitude = -Math.abs(this.longitude);
                    this.usrLong = this.longitude;
                }
                this.fieldLongDeg.setText(this.longStr);
                if (Math.abs(this.longitude) > 180.0) {
                    this.longitude = oldLong;
                    this.longStr = String.valueOf(Math.abs(Math.round(100.0 * oldLong) / 100.0));
                    this.fieldLongDeg.setText(this.longStr);
                    return true;
                }
                if (!this.str.equals(this.longStr) || this.str.length() == 0) {
                    this.longStr = String.valueOf(Math.abs(Math.round(100.0 * this.longitude) / 100.0));
                    this.locString = "User Input";
                    this.locChoice.select(this.locString);
                }
                this.fieldLatDeg.setText(this.latStr);
                this.fieldLongDeg.setText(this.longStr);
                if (this.longitude >= -7.5) {
                    this.clickLocOffset = (int)((this.longitude + 7.5) / 15.0);
                }
                else {
                    this.clickLocOffset = -(int)(-(this.longitude - 7.5) / 15.0);
                }
                if (this.summerBox.getState()) {
                    ++this.clickLocOffset;
                }
                this.usrOffset = this.clickLocOffset;
                if (this.usrOffset > 0) {
                    this.str = "UT  +" + this.usrOffset + " h";
                }
                if (this.usrOffset < 0) {
                    this.str = "UT  " + this.usrOffset + " h";
                }
                if (this.usrOffset == 0) {
                    this.str = "UT  +/- 0 h";
                }
                this.timeChoice.select(this.str);
                this.count = 0;
                this.locOffset = this.loc.getTimeZone(this.str);
                this.usrOffset = this.locOffset;
                this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
                this.mymapcanvas = new Mapcanvas(this.dat, this.latitude, this.longitude, this.locOffset);
                this.repaint();
                return true;
            }
        }
        if (event.target == this.writeChoice) {
            int dt = 0;
            if (this.writeChoice.getSelectedItem().equals("Table 60 min")) {
                dt = 60;
            }
            if (this.writeChoice.getSelectedItem().equals("Table 15 min")) {
                dt = 15;
            }
            if (this.writeChoice.getSelectedItem().equals("Table 5 min")) {
                dt = 5;
            }
            if (this.writeChoice.getSelectedItem().equals("Az/Elev/Time")) {
                dt = 0;
            }
            final boolean dem = this.online || this.demo;
            final Frame df = new dataFrame(this.versStr, this.date, this.month, this.year, this.locOffset, this.latitude, this.longitude, dt, dem, this.height);
            df.resize(350, 250);
            df.show();
            this.writeChoice.select("Write...");
            return true;
        }
        if (event.target == this.locChoice) {
            this.dst = 1;
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
                this.offset = Integer.parseInt(this.timezoneStr);
                this.dst = Integer.parseInt(this.dstStr);
            }
            else {
                this.latitude = this.loc.getLatLong(this.locString, 1);
                this.longitude = this.loc.getLatLong(this.locString, 2);
                this.offset = (int)this.loc.getLatLong(this.locString, 3);
                this.dst = (int)this.loc.getLatLong(this.locString, 4);
            }
            if (this.locChoice.getSelectedItem() != "User Input") {
                final int gmtHours = this.hours - this.locOffset;
                this.hours = gmtHours + this.offset;
                if (this.hours >= 24) {
                    this.hours -= 24;
                    ++this.date;
                    final int n = this.daysInMonth(this.month, this.year);
                    if (this.date > n) {
                        this.date = 1;
                        ++this.month;
                        if (this.month == 12) {
                            this.month = 0;
                            ++this.year;
                        }
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
                        this.date = this.daysInMonth(this.month, this.year);
                    }
                }
                this.dat.setMonth(this.month);
                this.dat.setDate(this.date);
                this.dat.setHours(this.hours);
                this.monthChoice.select(this.month);
                this.dateChoice.select(this.date - 1);
                this.hoursChoice.select(this.hours);
                this.hoursStr = String.valueOf(this.hours);
                if (this.hours < 10) {
                    this.hoursStr = "0" + this.hoursStr;
                }
                this.locOffset = this.offset;
                if (this.summerBox.getState()) {
                    ++this.locOffset;
                }
            }
            if (this.locChoice.getSelectedItem().equals("User Input")) {
                this.latitude = Math.round(100.0 * this.usrLat) / 100.0;
                this.longitude = Math.round(100.0 * this.usrLong) / 100.0;
                this.locOffset = this.usrOffset;
                if (this.usrOffset > 0) {
                    this.str = "UT  +" + this.usrOffset + " h";
                }
                if (this.usrOffset < 0) {
                    this.str = "UT  " + this.usrOffset + " h";
                }
                if (this.usrOffset == 0) {
                    this.str = "UT  +/- 0 h";
                }
                this.timeChoice.select(this.str);
                this.summerBox.setState(this.usrSummer);
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
            this.ewStr = this.ewChoice.getSelectedItem();
            if (this.latitude <= 0.0) {
                this.nsChoice.select("S");
            }
            else {
                this.nsChoice.select("N");
            }
            this.nsStr = this.nsChoice.getSelectedItem();
            this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
            this.mymapcanvas = new Mapcanvas(this.dat, this.latitude, this.longitude, this.locOffset);
            this.count = 0;
            this.repaint();
            return true;
        }
        else {
            if (event.target == this.ewChoice) {
                if (!this.ewChoice.getSelectedItem().equals(this.ewStr)) {
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
                this.usrLat = this.latitude;
                this.usrLong = this.longitude;
                this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
                this.mymapcanvas = new Mapcanvas(this.dat, this.latitude, this.longitude, this.locOffset);
                this.count = 0;
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
                this.usrLat = this.latitude;
                this.usrLong = this.longitude;
                this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
                this.mymapcanvas = new Mapcanvas(this.dat, this.latitude, this.longitude, this.locOffset);
                this.count = 0;
                this.repaint();
                return true;
            }
            if (event.target == this.timeChoice) {
                this.timeString = this.timeChoice.getSelectedItem();
                final int gmtHours = this.hours - this.locOffset;
                this.locOffset = this.loc.getTimeZone(this.timeString);
                this.usrOffset = this.locOffset;
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
                            this.dat.setYear(this.year);
                        }
                        this.date = this.daysInMonth(this.month, this.year);
                        this.dat.setMonth(this.month);
                        this.monthChoice.select(this.month);
                    }
                }
                this.dat.setDate(this.date);
                this.dateChoice.select(this.date - 1);
                this.dat.setHours(this.hours);
                this.timeChoice.select(this.timeString);
                this.hoursStr = String.valueOf(this.hours);
                if (this.hours < 10) {
                    this.hoursStr = "0" + this.hoursStr;
                }
                this.hoursChoice.select(this.hours);
                this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
                this.mymapcanvas = new Mapcanvas(this.dat, this.latitude, this.longitude, this.locOffset);
                this.repaint();
                return true;
            }
            if (event.target == this.hoursChoice) {
                this.hoursStr = this.hoursChoice.getSelectedItem();
                this.hours = Integer.parseInt(this.hoursStr);
                this.hoursChoice.select(this.hoursStr);
                this.dat.setHours(this.hours);
                this.dat.setSeconds(0);
                this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
                this.mymapcanvas = new Mapcanvas(this.dat, this.latitude, this.longitude, this.locOffset);
                this.count = 0;
                this.repaint();
                return true;
            }
            if (event.target == this.minChoice) {
                this.minStr = this.minChoice.getSelectedItem();
                this.minutes = Integer.parseInt(this.minStr);
                this.minChoice.select(this.minStr);
                this.dat.setMinutes(this.minutes);
                this.dat.setSeconds(0);
                this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
                this.mymapcanvas = new Mapcanvas(this.dat, this.latitude, this.longitude, this.locOffset);
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
                this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
                this.mymapcanvas = new Mapcanvas(this.dat, this.latitude, this.longitude, this.locOffset);
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
                this.usrSummer = this.summerBox.getState();
                this.usrOffset = this.offset;
                this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
                this.mymapcanvas = new Mapcanvas(this.dat, this.latitude, this.longitude, this.locOffset);
                this.count = 0;
                this.repaint();
                return true;
            }
            if (event.target == this.yearChoice) {
                this.yearStr = this.yearChoice.getSelectedItem();
                this.year = Integer.parseInt(this.yearStr);
                this.year -= 1900;
                this.yearChoice.select(this.yearStr);
                this.dat.setYear(this.year);
                final int n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = n;
                    this.dat.setDate(this.date);
                    this.dateChoice.select(this.date - 1);
                }
                this.fieldLatDeg.setText(this.latStr);
                this.fieldLongDeg.setText(this.longStr);
                this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
                this.mymapcanvas = new Mapcanvas(this.dat, this.latitude, this.longitude, this.locOffset);
                this.repaint();
                return true;
            }
            if (event.target == this.heightChoice) {
                final String heightStr = this.heightChoice.getSelectedItem();
                for (int i = 1; i < 31; ++i) {
                    if (heightStr.equals(i + " m")) {
                        this.height = i;
                    }
                }
                this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
                this.mymapcanvas = new Mapcanvas(this.dat, this.latitude, this.longitude, this.locOffset);
                this.repaint();
                return true;
            }
            if (event.target == this.angleChoice) {
                final String heightStr = this.angleChoice.getSelectedItem();
                for (int i = 0; i < 38; ++i) {
                    if (heightStr.equals(new StringBuffer().append(i * 5).append('°').toString())) {
                        this.angle = i * 5;
                    }
                }
                this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr);
                this.mymapcanvas = new Mapcanvas(this.dat, this.latitude, this.longitude, this.locOffset);
                if (this.online && this.angle != 0) {
                    this.angleDemo = true;
                }
                this.repaint();
                return true;
            }
            this.repaint();
            return true;
        }
    }
    
    public SunShadow148() {
        this.versStr = "  1.48";
        this.UV = false;
        this.xMouse = 0;
        this.yMouse = 0;
        this.dayArray = new String[7];
        this.monthArray = new String[12];
        this.demo = true;
        this.usrLat = 0.0;
        this.usrLong = 0.0;
        this.usrOffset = 0;
        this.top = 95;
        this.clickLocOffset = 0;
        this.dst = 1;
        this.angleDemo = false;
        this.x1 = new int[25];
        this.x2 = new int[25];
        this.x3 = new int[25];
        this.x4 = new int[25];
        this.y1 = new int[25];
        this.y2 = new int[25];
        this.y3 = new int[25];
        this.y4 = new int[25];
        this.xKante = 25;
        this.yKante = 25;
        this.x0 = 251;
        this.y0 = 505;
        this.count = 0;
        this.angle = 0;
        this.H = new double[25];
        this.A = new double[25];
        this.gnomon = 0.0;
        this.gitter = 25;
        this.height = 10.0;
        this.doCount = true;
    }
}
