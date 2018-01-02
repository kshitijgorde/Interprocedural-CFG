import java.awt.Cursor;
import java.awt.Point;
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
import java.awt.Rectangle;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Checkbox;
import java.awt.Button;
import java.util.Date;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class sme591 extends Applet
{
    Applet applet;
    String versNum;
    String versStr;
    public String wString;
    public Suncanvas myCan;
    public Mapcanvas mymapcanvas;
    public Seloc loc;
    public Seloc time;
    public Aboutdialog about;
    int xMouse;
    int yMouse;
    int xHorMouse;
    int yHorMouse;
    final int mapOben = 460;
    final int mapRechts = 490;
    final int y90 = 114;
    boolean clicked;
    boolean demo;
    boolean online;
    boolean horClick;
    Image map;
    Image bild;
    Compute comp;
    Date dat;
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
    double usrLat;
    double usrLong;
    int locOffset;
    Button nowButton;
    Button aboutButton;
    Checkbox moonBox;
    TextField fieldLatDeg;
    TextField fieldLongDeg;
    public Choice locChoice;
    public Choice timeChoice;
    Choice ewChoice;
    Choice nsChoice;
    Choice hoursChoice;
    Choice minChoice;
    Choice dateChoice;
    Choice monthChoice;
    Choice yearChoice;
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
    String userString;
    String homeString;
    String homeLatStr;
    String homeLongStr;
    String serverStr;
    final int xL = 30;
    final int xOben = 60;
    final double xFaktor = 1.2555555555555555;
    final double yFaktor = 1.2444444444444445;
    final int y0 = 572;
    Image bild1;
    Image bild2;
    Image bild3;
    Image bild4;
    int clickLocOffset;
    String clickTimeString;
    String latStrNS;
    String longStrEW;
    boolean moon;
    Choice writeChoice;
    boolean nmo;
    boolean sun;
    boolean drawFirst;
    int xLong;
    int yLat;
    int xMap;
    int yMap;
    String xyMapStr;
    double STD;
    double dec;
    double GHA;
    double hoehe;
    Rectangle rect;
    boolean cursorOK;
    boolean analemmaOK;
    boolean altOK;
    boolean dnOK;
    boolean equOK;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 34866 + 836;
    }
    
    public void init() {
        this.comp = new Compute();
        this.setBackground(new Color(235, 235, 255));
        this.rect = new Rectangle(30, 460, 452, 224);
        this.bild = this.getImage(this.getDocumentBase(), "applet/sunview/a.gif");
        this.homeString = this.getParameter("location");
        this.homeLatStr = this.getParameter("latitude");
        this.homeLongStr = this.getParameter("longitude");
        this.serverStr = this.getParameter("server");
        if (this.serverStr.equals("nmo")) {
            this.nmo = true;
            this.versStr = "New Moon Observer - " + this.versNum;
        }
        else {
            this.nmo = false;
            this.versStr = "Sun, Moon & Earth - " + this.versNum;
        }
        final String backgroundStr = this.getParameter("background");
        final URL url = this.getDocumentBase();
        this.str = url.toString();
        this.str = String.valueOf(this.str) + "1234567890123456789012345";
        this.wwwStr = this.str.substring(0, 27);
        this.bild = this.getImage(this.getDocumentBase(), "applet/" + backgroundStr + "/a.gif");
        this.map = this.getImage(this.getDocumentBase(), "applet/bigmap.gif");
        this.bild1 = this.getImage(this.getDocumentBase(), "applet/" + backgroundStr + "/b.gif");
        this.bild2 = this.getImage(this.getDocumentBase(), "applet/" + backgroundStr + "/c.gif");
        this.bild3 = this.getImage(this.getDocumentBase(), "applet/" + backgroundStr + "/d.gif");
        this.bild4 = this.getImage(this.getDocumentBase(), "applet/" + backgroundStr + "/e.gif");
        final MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(this.bild, 0);
        tracker.addImage(this.bild1, 0);
        tracker.addImage(this.bild2, 0);
        tracker.addImage(this.bild3, 0);
        tracker.addImage(this.bild4, 0);
        tracker.addImage(this.map, 0);
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
        this.locOffset = -this.dat.getTimezoneOffset() / 60;
        if (this.locOffset >= 0) {
            this.timeString = "UT +" + this.locOffset + " h";
        }
        else {
            this.timeString = "UT " + this.locOffset + " h";
        }
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        final Font f = new Font("Helvetica", 0, 10);
        this.setFont(f);
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(6, 0, 0, 0);
        final Label L11 = new Label();
        L11.setText("Time Zone");
        gbl.setConstraints(L11, gbc);
        this.add(L11);
        final Label L12 = new Label();
        L12.setText("Location");
        gbl.setConstraints(L12, gbc);
        this.add(L12);
        gbc.weightx = 10.0;
        final Label L13 = new Label();
        L13.setText("Latit.: xx.xx");
        gbl.setConstraints(L13, gbc);
        this.add(L13);
        final Label L14 = new Label();
        L14.setText("N / S");
        gbl.setConstraints(L14, gbc);
        this.add(L14);
        final Label L15 = new Label();
        L15.setText("Longit.: xx.xx");
        gbl.setConstraints(L15, gbc);
        this.add(L15);
        final Label L16 = new Label();
        L16.setText("E / W");
        gbl.setConstraints(L16, gbc);
        this.add(L16);
        final Label L17 = new Label();
        L17.setText("Details");
        gbl.setConstraints(L17, gbc);
        this.add(L17);
        final Label L18 = new Label();
        L18.setText("Moon");
        gbc.insets = new Insets(6, 0, 0, 5);
        gbl.setConstraints(L18, gbc);
        this.add(L18);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 0, 0);
        this.time = new Seloc();
        this.timeChoice = new Choice();
        this.time.timeMenu(this.timeChoice);
        gbl.setConstraints(this.timeChoice, gbc);
        this.timeChoice.select(this.timeString);
        this.add(this.timeChoice);
        gbc.insets = new Insets(0, 0, 0, 0);
        this.locChoice = new Choice();
        this.locString = this.homeString;
        this.locChoice.addItem(this.locString);
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
        gbl.setConstraints(this.fieldLatDeg = new TextField(this.latStr, 6), gbc);
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
        gbl.setConstraints(this.writeChoice = new Choice(), gbc);
        this.writeChoice.addItem("Choose....");
        this.writeChoice.addItem("Day/Night on/off");
        this.writeChoice.addItem("Altitudes on/off");
        this.writeChoice.addItem("Analemma on/off");
        this.writeChoice.addItem("Analemma on/off");
        this.writeChoice.addItem("Equator on/off");
        this.writeChoice.addItem("Write Data");
        this.writeChoice.addItem("New Moon");
        this.writeChoice.addItem("Sunrise");
        this.writeChoice.addItem("Sunset");
        if (this.nmo) {
            this.writeChoice.addItem("daz,dalt,arcl");
        }
        this.add(this.writeChoice);
        gbl.setConstraints(this.moonBox = new Checkbox(), gbc);
        this.add(this.moonBox);
        gbc.gridy = 2;
        final Label L19 = new Label();
        L19.setText("Local Time:");
        gbc.insets = new Insets(5, 20, 0, 0);
        gbl.setConstraints(L19, gbc);
        this.add(L19);
        gbc.insets = new Insets(5, 0, 0, 0);
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
        this.minChoice = new Choice();
        for (int k = 0; k < 10; ++k) {
            this.minStr = "0" + k;
            this.minChoice.addItem(this.minStr);
        }
        for (int l = 10; l <= 60; ++l) {
            this.minStr = String.valueOf(l);
            this.minChoice.addItem(this.minStr);
        }
        this.minChoice.select(this.minutes);
        gbl.setConstraints(this.minChoice, gbc);
        this.add(this.minChoice);
        final Label L20 = new Label();
        L20.setText("Date:");
        gbl.setConstraints(L20, gbc);
        this.add(L20);
        this.yearChoice = new Choice();
        for (int m = 0; m < 80; ++m) {
            this.yearStr = String.valueOf(this.year + 1900 - 35 + m);
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
        (this.nowButton = new Button()).setLabel("now");
        gbc.insets = new Insets(5, 0, 0, 10);
        gbl.setConstraints(this.nowButton, gbc);
        this.add(this.nowButton);
        gbc.gridy = 3;
        gbc.weighty = 160.0;
        gbl.setConstraints(this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.userString, this.xHorMouse, this.yHorMouse, this.horClick, this.moon, this.drawFirst, this.nmo, this.analemmaOK, this.equOK), gbc);
        this.add(this.myCan);
        gbc.gridy = 4;
        gbc.weighty = 1.0;
        gbl.setConstraints(this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset, this.moon, this.sun, this.altOK, this.dnOK), gbc);
        this.add(this.mymapcanvas);
        final GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 7;
        gbc2.weightx = 2.0;
        gbc2.gridy = 5;
        gbc2.weighty = 6.0;
        gbc2.insets = new Insets(0, 0, 10, 15);
        gbl.setConstraints(this.aboutButton = new Button(), gbc2);
        this.aboutButton.setLabel("About...");
        this.add(this.aboutButton);
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        this.usrStr = this.email;
        this.userString = this.email;
        if (this.formula(this.wwwStr, 18) == this.formula("http://www.tyge.de", 18) || this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(this.wwwStr, 22) == this.formula("http://www.geoastro.de", 22) || this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 21) == this.formula("http://www.j-giesen.de", 21) || this.formula(this.wwwStr, 27) == this.formula("http://www.jgiesen.business", 27)) {
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
            if (!ok) {
                this.userString = "Demo running";
                final Dialog Aboutdialog = new Aboutdialog(this, this.versStr, this.demo);
                Aboutdialog.resize(300, 250);
                Aboutdialog.show();
            }
        }
        if (this.demo) {
            this.versStr = String.valueOf(this.versStr) + " DEMO";
        }
        if (this.nmo) {
            this.writeChoice.addItem("Crescent Map");
        }
        System.out.println(String.valueOf(this.versStr) + "  (c) 1998-2011 J. Giesen - www.GeoAstro.de");
    }
    
    public void paint(final Graphics g) {
        this.STD = this.hours - this.locOffset + this.minutes / 60.0;
        this.dec = this.comp.computeDeclination(this.date, this.month + 1, this.year + 1900, this.STD);
        this.GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, this.STD);
        this.hoehe = this.comp.computeHeight(this.dec, this.latitude, this.longitude, this.GHA);
        if (this.hoehe >= 0.0) {
            this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.userString, this.xHorMouse, this.yHorMouse, this.horClick, this.moon, this.drawFirst, this.nmo, this.analemmaOK, this.equOK);
        }
        else if (this.hoehe < 0.0 && this.hoehe >= -6.0) {
            this.myCan = new Suncanvas(this.bild1, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.userString, this.xHorMouse, this.yHorMouse, this.horClick, this.moon, this.drawFirst, this.nmo, this.analemmaOK, this.equOK);
        }
        else if (this.hoehe < -6.0 && this.hoehe >= -12.0) {
            this.myCan = new Suncanvas(this.bild2, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.userString, this.xHorMouse, this.yHorMouse, this.horClick, this.moon, this.drawFirst, this.nmo, this.analemmaOK, this.equOK);
        }
        else if (this.hoehe < -12.0 && this.hoehe >= -18.0) {
            this.myCan = new Suncanvas(this.bild3, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.userString, this.xHorMouse, this.yHorMouse, this.horClick, this.moon, this.drawFirst, this.nmo, this.analemmaOK, this.equOK);
        }
        else if (this.hoehe < -18.0) {
            this.myCan = new Suncanvas(this.bild4, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.userString, this.xHorMouse, this.yHorMouse, this.horClick, this.moon, this.drawFirst, this.nmo, this.analemmaOK, this.equOK);
        }
        g.setFont(new Font("Helvetica", 0, 10));
        g.setColor(Color.red);
        g.drawString(this.clickTimeString, 15, 100);
        this.str = this.latStr;
        if ((int)this.latitude == this.latitude) {
            this.str = Math.abs((int)this.latitude) + ".0";
        }
        if (this.latitude >= 0.0) {
            this.str = String.valueOf(this.str) + " N";
        }
        else {
            this.str = String.valueOf(this.str) + " S";
        }
        g.drawString("Lat.= " + this.str, 155, 420);
        this.str = this.longStr;
        if ((int)this.longitude == this.longitude) {
            this.str = Math.abs((int)this.longitude) + ".0";
        }
        if (this.longitude >= 0.0) {
            this.str = String.valueOf(this.str) + " E";
        }
        else {
            this.str = String.valueOf(this.str) + " W";
        }
        g.drawString("Long.=" + this.str, 240, 420);
        this.mymapcanvas.update(g);
        this.myCan.update(g);
        if (this.xyMapStr != "") {
            g.clearRect(this.xMap + 3, this.yMap - 12, 45, 10);
            g.drawString(this.xyMapStr, this.xMap + 3, this.yMap - 3);
        }
        g.setColor(Color.red);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        if (this.demo) {
            this.usrStr = "demo running";
            g.setFont(new Font("Chicago", 0, 20));
            g.setColor(Color.red);
            g.setFont(new Font("Chicago", 0, 128));
            g.drawString("D  E  M  O", 20, 500);
        }
        g.setColor(Color.black);
        g.setFont(new Font("Helvetica", 0, 10));
        if (this.nmo) {
            g.drawString(String.valueOf(this.versStr) + "  © 2006-2011 J. Giesen", 330, 697);
        }
        else {
            g.drawString(String.valueOf(this.versStr) + "  © 1998-2011 J. Giesen", 330, 697);
        }
        this.showStatus("");
    }
    
    public boolean mouseDown(final Event event, final int x, final int y) {
        this.xMouse = x;
        this.yMouse = y;
        final Rectangle r = new Rectangle(60, 114, 542, 400);
        final Point pt = new Point(x, y);
        if (r.contains(pt)) {
            this.xMouse = x;
            this.yMouse = y;
            this.xHorMouse = x;
            this.yHorMouse = y;
            this.horClick = true;
            this.repaint();
        }
        if (this.rect.contains(pt)) {
            this.clicked = true;
            this.horClick = false;
            this.latitude = -(this.yMouse - 572) * 180.0 / 224.0;
            this.latitude = Math.round(100.0 * this.latitude) / 100.0;
            this.longitude = -180.0 + (this.xMouse - 30) * 90.0 / 113.0;
            this.longitude = Math.round(100.0 * this.longitude) / 100.0;
            if (this.longitude >= -7.5) {
                this.clickLocOffset = (int)((this.longitude + 7.5) / 15.0);
            }
            else {
                this.clickLocOffset = -(int)(-(this.longitude - 7.5) / 15.0);
            }
            if (this.clickLocOffset >= 0) {
                this.clickTimeString = "UT +";
            }
            else {
                this.clickTimeString = "UT ";
            }
            this.clickTimeString = String.valueOf(this.clickTimeString) + this.clickLocOffset + " h";
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
                this.ewChoice.select("W");
            }
            else {
                this.ewChoice.select("E");
            }
            this.locString = "User Input";
            this.locChoice.select(this.locString);
            this.usrLat = this.latitude;
            this.usrLong = this.longitude;
            this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.userString, this.xHorMouse, this.yHorMouse, this.horClick, this.moon, this.drawFirst, this.nmo, this.analemmaOK, this.equOK);
            this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset, this.moon, this.sun, this.altOK, this.dnOK);
            this.repaint();
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int code) {
        int n = 0;
        if (code != 104 && code != 100 && code != 109 && code != 72 && code != 68 && code != 77 && code != 121 && code != 89 && code != 110 && code != 78) {
            return false;
        }
        if (this.nmo && code == 121) {
            this.writeChoice.addItem("Crescent Map");
        }
        else {
            if (code == 89) {
                this.dat.setYear(this.year + 100);
                this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.userString, this.xHorMouse, this.yHorMouse, this.horClick, this.moon, this.drawFirst, this.nmo, this.analemmaOK, this.equOK);
                this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset, this.moon, this.sun, this.altOK, this.dnOK);
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
                    if (this.date > this.comp.daysInMonth(this.month, this.year)) {
                        this.date = 1;
                        ++this.month;
                        if (this.month > 11) {
                            this.month = 0;
                            ++this.year;
                        }
                        this.dat.setMonth(this.month);
                        this.dat.setYear(this.year);
                        this.yearChoice.select(String.valueOf(this.year + 1900));
                        this.monthChoice.select(this.month);
                    }
                    this.dat.setDate(this.date);
                    this.dateChoice.select(this.date - 1);
                }
                this.dat.setMinutes(this.minutes);
                this.minChoice.select(this.minutes);
                this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset, this.moon, this.sun, this.altOK, this.dnOK);
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
                        if (this.month < 0) {
                            this.month = 11;
                            --this.year;
                            this.dat.setYear(this.year);
                            this.yearChoice.select(String.valueOf(this.year + 1900));
                        }
                        this.date = this.comp.daysInMonth(this.month, this.year);
                        this.dat.setMonth(this.month);
                        this.monthChoice.select(this.month);
                    }
                    this.dat.setDate(this.date);
                    this.dateChoice.select(this.date - 1);
                }
                this.dat.setMinutes(this.minutes);
                this.minChoice.select(this.minutes);
                this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset, this.moon, this.sun, this.altOK, this.dnOK);
                this.repaint();
                return true;
            }
            if (code == 72 || code == 104) {
                if (code == 72) {
                    --this.hours;
                    if (this.hours < 0) {
                        this.hours += 24;
                        --this.date;
                        if (this.date == 0) {
                            --this.month;
                            if (this.month < 0) {
                                this.month = 11;
                                --this.year;
                                this.yearStr = String.valueOf(this.year + 1900);
                                this.yearChoice.select(this.yearStr);
                            }
                            this.dat.setMonth(this.month);
                            this.date = this.comp.daysInMonth(this.month, this.year);
                        }
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
                    n = this.comp.daysInMonth(this.month, this.year);
                    if (this.date > n) {
                        this.date = 1;
                        ++this.month;
                        if (this.month == 12) {
                            this.month = 0;
                            ++this.year;
                            this.yearStr = String.valueOf(this.year + 1900);
                            this.yearChoice.select(this.yearStr);
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
                this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset, this.moon, this.sun, this.altOK, this.dnOK);
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
                    n = this.comp.daysInMonth(this.month, this.year);
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
                this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset, this.moon, this.sun, this.altOK, this.dnOK);
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
                n = this.comp.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = 1;
                    this.dat.setDate(this.date);
                    this.dat.setMonth(this.month - 1);
                }
                this.month = this.dat.getMonth();
                this.monthChoice.select(this.month);
                this.dateChoice.select(this.date - 1);
                this.year = this.dat.getYear();
                this.year += 1900;
                this.yearStr = String.valueOf(this.year);
                this.yearChoice.select(this.yearStr);
                this.dat.setSeconds(0);
                this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset, this.moon, this.sun, this.altOK, this.dnOK);
                this.repaint();
                return true;
            }
        }
        return false;
    }
    
    public boolean mouseMove(final Event event, final int x, final int y) {
        if (this.cursorOK) {
            final Point p = new Point(x, y);
            if (this.rect.contains(p)) {
                this.yMap = y;
                this.xMap = x;
                this.xHorMouse = 0;
                this.yHorMouse = 0;
                this.setCursor(new Cursor(1));
                this.xLong = -180 + (int)Math.round((x - 30) / 1.2555555555555555);
                this.yLat = (int)Math.round((572 - y) / 1.2444444444444445);
                this.xyMapStr = String.valueOf(Math.abs(this.yLat));
                if (this.yLat >= 0) {
                    this.xyMapStr = String.valueOf(this.xyMapStr) + "N ";
                }
                else {
                    this.xyMapStr = String.valueOf(this.xyMapStr) + "S ";
                }
                this.xyMapStr = String.valueOf(this.xyMapStr) + Math.abs(this.xLong);
                if (this.xLong > 0) {
                    this.xyMapStr = String.valueOf(this.xyMapStr) + "E";
                }
                else {
                    this.xyMapStr = String.valueOf(this.xyMapStr) + "W";
                }
                this.horClick = false;
            }
            else {
                this.xyMapStr = "";
                this.setCursor(new Cursor(0));
            }
            this.repaint();
        }
        return true;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        this.clickTimeString = "";
        final double oldLat = this.latitude;
        final double oldLong = this.longitude;
        if (event.target instanceof TextField) {
            this.str = "";
            if (event.target == this.fieldLatDeg) {
                this.str = this.fieldLatDeg.getText();
                if (this.str.length() == 0 || this.str.charAt(0) == '.' || this.str.charAt(0) == ',') {
                    this.fieldLatDeg.setText(String.valueOf(Math.abs(Math.round(100.0 * oldLat) / 100.0)));
                    return true;
                }
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
                    this.usrLong = this.longitude;
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
                    this.fieldLatDeg.setText(this.latStr);
                    this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.userString, this.xHorMouse, this.yHorMouse, this.horClick, this.moon, this.drawFirst, this.nmo, this.analemmaOK, this.equOK);
                    this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset, this.moon, this.sun, this.altOK, this.dnOK);
                }
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
                    this.usrLat = this.latitude;
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
                    this.fieldLongDeg.setText(this.longStr);
                    this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.userString, this.xHorMouse, this.yHorMouse, this.horClick, this.moon, this.drawFirst, this.nmo, this.analemmaOK, this.equOK);
                    this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset, this.moon, this.sun, this.altOK, this.dnOK);
                }
                this.repaint();
                return true;
            }
        }
        if (event.target instanceof Checkbox && event.target == this.moonBox) {
            this.moon ^= true;
            this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset, this.moon, this.sun, this.altOK, this.dnOK);
            this.repaint();
            return true;
        }
        if (event.target instanceof Button) {
            if (event.target == this.aboutButton) {
                final Dialog Aboutdialog = new Aboutdialog(this, this.versStr, this.demo);
                Aboutdialog.resize(300, 250);
                Aboutdialog.show();
                return true;
            }
            if (event.target == this.nowButton) {
                (this.dat = new Date()).setSeconds(0);
                this.seconds = 0;
                this.hours = this.dat.getHours();
                this.minutes = this.dat.getMinutes();
                this.minChoice.select(this.minutes);
                this.date = this.dat.getDate();
                this.month = this.dat.getMonth();
                final int gmtHours = this.hours + this.dat.getTimezoneOffset() / 60;
                this.hours = gmtHours + this.locOffset;
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
                this.hoursChoice.select(this.hours);
                this.dat.setHours(this.hours);
                this.hoursStr = String.valueOf(this.hours);
                if (this.hours < 10) {
                    this.hoursStr = "0" + this.hoursStr;
                }
                this.dateChoice.select(this.date - 1);
                this.monthChoice.select(this.month);
                this.year = this.dat.getYear();
                this.yearStr = String.valueOf(this.year + 1900);
                this.yearChoice.select(this.yearStr);
                this.repaint();
                return true;
            }
        }
        if (event.target instanceof Choice) {
            if (event.target == this.writeChoice) {
                if (this.writeChoice.getSelectedItem() == "Sunrise") {
                    this.writeChoice.select(0);
                    (this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.userString, this.xHorMouse, this.yHorMouse, this.horClick, this.moon, this.drawFirst, this.nmo, this.analemmaOK, this.equOK)).sunRiseSet(this.date, this.month, this.year);
                    this.hours = (int)this.myCan.sunRise();
                    this.minutes = (int)Math.round(60.0 * this.comp.frac(this.myCan.sunRise()));
                    this.hoursChoice.select(this.hours);
                    this.minChoice.select(this.minutes);
                    this.dat.setHours(this.hours);
                    this.dat.setMinutes(this.minutes);
                    this.repaint();
                    return true;
                }
                if (this.writeChoice.getSelectedItem() == "Sunset") {
                    this.writeChoice.select(0);
                    (this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.userString, this.xHorMouse, this.yHorMouse, this.horClick, this.moon, this.drawFirst, this.nmo, this.analemmaOK, this.equOK)).sunRiseSet(this.date, this.month, this.year);
                    this.hours = (int)this.myCan.sunSet();
                    this.minutes = (int)Math.round(60.0 * this.comp.frac(this.myCan.sunSet()));
                    this.hoursChoice.select(this.hours);
                    this.minChoice.select(this.minutes);
                    this.dat.setHours(this.hours);
                    this.dat.setMinutes(this.minutes);
                    this.repaint();
                    return true;
                }
                if (this.writeChoice.getSelectedItem() == "Analemma on/off") {
                    this.analemmaOK ^= true;
                    this.writeChoice.select(0);
                    this.repaint();
                    return true;
                }
                if (this.writeChoice.getSelectedItem() == "Equator on/off") {
                    this.equOK ^= true;
                    this.writeChoice.select(0);
                    this.repaint();
                    return true;
                }
                if (this.writeChoice.getSelectedItem() == "Day/Night on/off") {
                    this.dnOK ^= true;
                    this.writeChoice.select(0);
                    this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset, this.moon, this.sun, this.altOK, this.dnOK);
                    this.repaint();
                    return true;
                }
                if (this.writeChoice.getSelectedItem() == "Altitudes on/off") {
                    this.altOK ^= true;
                    this.writeChoice.select(0);
                    this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset, this.moon, this.sun, this.altOK, this.dnOK);
                    this.repaint();
                    return true;
                }
                if (this.writeChoice.getSelectedItem() == "Cursor on/off") {
                    this.cursorOK ^= true;
                    this.writeChoice.select(0);
                    return true;
                }
                if (this.writeChoice.getSelectedItem() == "Write Data") {
                    this.myCan.writeConsole();
                    this.writeChoice.select(0);
                    return true;
                }
                if (this.writeChoice.getSelectedItem() == "New Moon") {
                    this.mymapcanvas.dataNewMoon(true, 1, true);
                    this.writeChoice.select(0);
                    return true;
                }
                if (this.writeChoice.getSelectedItem() == "daz,dalt,arcl") {
                    this.myCan.dataNewMoon(true);
                    this.writeChoice.select(0);
                    this.setCursor(new Cursor(0));
                    return true;
                }
                if (this.writeChoice.getSelectedItem() == "Crescent Map") {
                    if (!this.nmo) {
                        return true;
                    }
                    this.setCursor(new Cursor(3));
                    this.showStatus("Calculating about 2 minutes ...");
                    this.drawFirst = true;
                    this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset, false, false, this.altOK, false);
                    this.analemmaOK = false;
                    this.repaint();
                    this.mymapcanvas.dataNewMoon(false, 2, true);
                    this.mymapcanvas.doLatLong();
                    this.moon = false;
                    this.moonBox.setState(false);
                    this.setCursor(new Cursor(0));
                    this.showStatus("");
                    this.writeChoice.select(0);
                    return true;
                }
            }
            if (event.target == this.locChoice) {
                this.clickTimeString = "";
                if (this.locChoice.getSelectedItem().lastIndexOf("--") != -1) {
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
                this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset, this.moon, this.sun, this.altOK, this.dnOK);
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
                        this.longitude = -Math.abs(this.longitude);
                    }
                    this.usrLat = this.latitude;
                    this.usrLong = this.longitude;
                    this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset, this.moon, this.sun, this.altOK, this.dnOK);
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
                        this.latitude = -Math.abs(this.latitude);
                    }
                    this.usrLat = this.latitude;
                    this.usrLong = this.longitude;
                    this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset, this.moon, this.sun, this.altOK, this.dnOK);
                    this.repaint();
                    return true;
                }
                if (event.target == this.timeChoice) {
                    this.clickTimeString = "";
                    this.timeString = this.timeChoice.getSelectedItem();
                    final int gmtHours = this.hours - this.locOffset;
                    this.locOffset = this.loc.getTimeZone(this.timeString);
                    this.hours = gmtHours + this.locOffset;
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
                    this.hoursStr = String.valueOf(this.hours);
                    if (this.hours < 10) {
                        this.hoursStr = "0" + this.hoursStr;
                    }
                    this.hoursChoice.select(this.hours);
                    this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset, this.moon, this.sun, this.altOK, this.dnOK);
                    this.repaint();
                    return true;
                }
                if (event.target == this.hoursChoice) {
                    this.hoursStr = this.hoursChoice.getSelectedItem();
                    this.hours = Integer.parseInt(this.hoursStr);
                    final int Hours = Integer.parseInt(this.hoursStr);
                    this.hoursChoice.select(this.hoursStr);
                    this.dat.setHours(Hours);
                    this.dat.setSeconds(0);
                    this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset, this.moon, this.sun, this.altOK, this.dnOK);
                    this.repaint();
                    return true;
                }
                if (event.target == this.minChoice) {
                    this.minStr = this.minChoice.getSelectedItem();
                    this.minutes = Integer.parseInt(this.minStr);
                    if (this.minutes == 60) {
                        this.minutes = 0;
                        this.minStr = "00";
                        if (this.hours < 23) {
                            ++this.hours;
                            if (this.hours < 10) {
                                this.hoursChoice.select("0" + this.hours);
                            }
                            else {
                                this.hoursChoice.select(String.valueOf(this.hours));
                            }
                        }
                        else {
                            this.hours = 0;
                            this.dat.setHours(this.hours);
                            this.hoursChoice.select("00");
                            ++this.date;
                            if (this.date > this.comp.daysInMonth(this.month, this.year)) {
                                this.date = 1;
                                ++this.month;
                                this.monthChoice.select(this.month);
                                this.dat.setMonth(this.month);
                            }
                            this.dateStr = String.valueOf(this.date);
                            this.dateChoice.select(this.dateStr);
                            this.dat.setDate(this.date);
                        }
                        this.dat.setHours(this.hours);
                    }
                    this.minChoice.select(this.minStr);
                    this.dat.setMinutes(this.minutes);
                    this.dat.setSeconds(0);
                    this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset, this.moon, this.sun, this.altOK, this.dnOK);
                    this.repaint();
                    return true;
                }
                if (event.target == this.dateChoice) {
                    this.dateStr = this.dateChoice.getSelectedItem();
                    this.date = Integer.parseInt(this.dateStr);
                    this.dat.setDate(this.date);
                    this.dat.setSeconds(0);
                    final int n = this.comp.daysInMonth(this.month, this.year);
                    if (this.date > n) {
                        this.date = n;
                        this.dat.setDate(this.date);
                        this.dateChoice.select(this.date - 1);
                    }
                    this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset, this.moon, this.sun, this.altOK, this.dnOK);
                    this.repaint();
                    return true;
                }
                if (event.target == this.monthChoice) {
                    this.monthStr = this.monthChoice.getSelectedItem();
                    this.month = this.monthChoice.getSelectedIndex();
                    this.dat.setMonth(this.month);
                    this.dat.setSeconds(0);
                    final int n = this.comp.daysInMonth(this.month, this.year);
                    if (this.date > n) {
                        this.date = n;
                        this.dat.setDate(this.date);
                        this.dateChoice.select(this.date - 1);
                    }
                    this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset, this.moon, this.sun, this.altOK, this.dnOK);
                    this.repaint();
                    return true;
                }
                if (event.target == this.yearChoice) {
                    this.yearStr = this.yearChoice.getSelectedItem();
                    this.year = Integer.parseInt(this.yearStr);
                    this.year -= 1900;
                    this.yearChoice.select(this.yearStr);
                    this.dat.setYear(this.year);
                    final int n = this.comp.daysInMonth(this.month, this.year);
                    if (this.date > n) {
                        this.date = n;
                        this.dat.setDate(this.date);
                        this.dateChoice.select(this.date - 1);
                    }
                    this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset, this.moon, this.sun, this.altOK, this.dnOK);
                    this.repaint();
                    return true;
                }
            }
        }
        return true;
    }
    
    public sme591() {
        this.versNum = "5.91";
        this.wString = "?";
        this.clicked = false;
        this.demo = true;
        this.online = false;
        this.horClick = false;
        this.comp = new Compute();
        this.usrLat = 0.0;
        this.usrLong = 0.0;
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.usrStr = "demo";
        this.userString = "?";
        this.clickTimeString = "";
        this.moon = false;
        this.sun = true;
        this.drawFirst = false;
        this.xMap = 0;
        this.yMap = 0;
        this.xyMapStr = "";
        this.cursorOK = false;
        this.analemmaOK = true;
        this.altOK = false;
        this.dnOK = true;
    }
}
