import java.awt.Event;
import java.awt.Graphics;
import java.awt.Cursor;
import java.awt.Dialog;
import java.awt.Insets;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Color;
import java.net.URL;
import java.awt.Frame;
import java.awt.Label;
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

public class sunshine334 extends Applet
{
    String versStr;
    boolean UV;
    int xMouse;
    int yMouse;
    boolean clicked;
    final int mapOben = 305;
    final int mapRechts = 490;
    Image map;
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
    double azimut;
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
    Checkbox summerBox;
    TextField fieldLatDeg;
    TextField fieldLongDeg;
    Choice ewChoice;
    Choice nsChoice;
    Choice hoursChoice;
    Choice minChoice;
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
    public String locString;
    public String timeString;
    public String email;
    public String param;
    public String wwwStr;
    String[] dayArray;
    String str;
    final int xL = 30;
    final int xOben = 60;
    public Label L11;
    public Label L12;
    public Label L13;
    public Label L14;
    public Label L15;
    public Label L16;
    public Label L17;
    public Label L18;
    public Label L23;
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
    public Frame parentFrame;
    String linkStr;
    URL linkURL;
    boolean usrSummer;
    Choice writeChoice;
    String[] monthArray;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 12792;
    }
    
    public void getClickOffset() {
        if (this.longitude >= -7.5) {
            this.clickLocOffset = (int)((this.longitude + 7.5) / 15.0);
        }
        else {
            this.clickLocOffset = -(int)(-(this.longitude - 7.5) / 15.0);
        }
    }
    
    public void init() {
        final Color background = new Color(235, 235, 255);
        this.setBackground(background);
        this.homeString = this.getParameter("location");
        this.homeLatStr = this.getParameter("latitude");
        this.homeLongStr = this.getParameter("longitude");
        this.timezoneStr = this.getParameter("timezone");
        this.dstStr = this.getParameter("daylightsaving");
        final URL url = this.getCodeBase();
        this.str = url.toString();
        this.str = String.valueOf(this.str) + "01234567890123456789012345";
        this.wwwStr = this.str.substring(0, 27);
        final String backgroundStr = this.getParameter("background");
        this.map = this.getImage(this.getDocumentBase(), "applet/bigmap.gif");
        this.bild = this.getImage(this.getDocumentBase(), "applet/" + backgroundStr + "/a.gif");
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
        (this.L11 = new Label()).setText("Time Zone");
        gbl.setConstraints(this.L11, gbc);
        this.add(this.L11);
        (this.L17 = new Label()).setText("Daylight S. T.");
        gbc.insets = new Insets(2, 0, 0, 0);
        gbl.setConstraints(this.L17, gbc);
        this.add(this.L17);
        (this.L12 = new Label()).setText("Location");
        gbc.insets = new Insets(2, 0, 0, 0);
        gbl.setConstraints(this.L12, gbc);
        this.add(this.L12);
        gbc.weightx = 10.0;
        (this.L13 = new Label()).setText("Latitude");
        gbl.setConstraints(this.L13, gbc);
        this.add(this.L13);
        (this.L14 = new Label()).setText("N / S");
        gbl.setConstraints(this.L14, gbc);
        this.add(this.L14);
        (this.L15 = new Label()).setText("Longitude");
        gbl.setConstraints(this.L15, gbc);
        this.add(this.L15);
        (this.L16 = new Label()).setText("E / W");
        gbl.setConstraints(this.L16, gbc);
        this.add(this.L16);
        (this.L18 = new Label()).setText("      ");
        gbl.setConstraints(this.L18, gbc);
        this.add(this.L18);
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
        gbl.setConstraints(this.fieldLongDeg = new TextField(this.longStr, 7), gbc);
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
        gbl.setConstraints(this.writeChoice = new Choice(), gbc);
        this.writeChoice.addItem("Write...");
        this.writeChoice.addItem("Month");
        this.writeChoice.addItem("Year");
        this.add(this.writeChoice);
        gbc.gridy = 2;
        (this.L17 = new Label()).setText("Local Time (h:m)");
        gbc.insets = new Insets(0, 15, 0, 0);
        gbl.setConstraints(this.L17, gbc);
        this.add(this.L17);
        gbc.insets = new Insets(7, 0, 0, 0);
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
        (this.L23 = new Label()).setText("Date");
        gbc.insets = new Insets(0, 20, 0, 0);
        gbl.setConstraints(this.L23, gbc);
        this.add(this.L23);
        gbc.insets = new Insets(0, 0, 0, 0);
        this.monthChoice = new Choice();
        for (int m = 0; m < 12; ++m) {
            this.monthChoice.addItem(this.monthArray[m]);
        }
        this.monthChoice.select(this.month);
        gbl.setConstraints(this.monthChoice, gbc);
        this.add(this.monthChoice);
        this.dateChoice = new Choice();
        for (int i2 = 1; i2 < 32; ++i2) {
            this.dateStr = String.valueOf(i2);
            this.dateChoice.addItem(this.dateStr);
        }
        this.dateChoice.select(this.date - 1);
        gbl.setConstraints(this.dateChoice, gbc);
        this.add(this.dateChoice);
        (this.button = new Button()).setLabel("Now");
        gbl.setConstraints(this.button, gbc);
        this.add(this.button);
        gbl.setConstraints(this.aboutButton = new Button(), gbc);
        this.aboutButton.setLabel("About...");
        this.add(this.aboutButton);
        gbc.gridy = 3;
        gbc.weighty = 200.0;
        gbl.setConstraints(this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online), gbc);
        this.add(this.myCan);
        gbc.gridy = 4;
        gbc.weighty = 1.0;
        gbl.setConstraints(this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset), gbc);
        this.add(this.mymapcanvas);
        boolean ok = false;
        this.demo = true;
        this.comp = new Compute();
        final double STD = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        final double dec = this.comp.computeDeclination(this.date, this.month + 1, this.year + 1900, STD);
        final double GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, STD);
        final double hoehe = this.comp.computeHeight(dec, this.latitude, this.longitude, GHA);
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
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
            if (ok && this.formula(this.wwwStr, 6) == this.formula("http://", 6)) {
                ok = false;
                this.demo = true;
                this.online = true;
            }
            if (!ok) {
                this.parentFrame = new Frame("Dialog");
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
    
    public void checkSummer(final Date dat, final int myOffset, final int mydst) {
        if (mydst == 0) {
            this.summerBox.setState(false);
            this.offset = myOffset;
            this.isSummer = false;
        }
        else {
            final int theOffset = -dat.getTimezoneOffset() / 60;
            final Date theDate = new Date();
            theDate.setMonth(6);
            final int theOffset2 = -theDate.getTimezoneOffset() / 60;
            if (theOffset == theOffset2) {
                if (this.latitude >= 0.0) {
                    this.isSummer = true;
                    this.summerBox.setState(true);
                    this.offset = myOffset + 1;
                }
                else {
                    this.isSummer = false;
                    this.summerBox.setState(false);
                }
            }
            else if (this.latitude < 0.0) {
                this.isSummer = true;
                this.summerBox.setState(true);
                this.offset = myOffset + 1;
            }
            else {
                this.isSummer = false;
                this.summerBox.setState(false);
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
    
    public void startCursor() {
        this.showStatus("Calculating...");
        this.setCursor(new Cursor(3));
    }
    
    public void stopCursor() {
        this.showStatus("");
        this.setCursor(new Cursor(0));
    }
    
    public void paint(final Graphics g) {
        g.clearRect(0, 0, this.size().width, this.size().height);
        this.comp = new Compute();
        final double STD = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        final double dec = this.comp.computeDeclination(this.date, this.month + 1, this.year + 1900, STD);
        final double GHA = this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, STD);
        final double hoehe = this.comp.computeHeight(dec, this.latitude, this.longitude, GHA);
        if (hoehe >= -0.83) {
            this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
        }
        if (hoehe < -0.83 && hoehe >= -6.0) {
            this.myCan = new Suncanvas(this.bild1, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
        }
        if (hoehe < -6.0 && hoehe >= -12.0) {
            this.myCan = new Suncanvas(this.bild2, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
        }
        if (hoehe < -12.0 && hoehe >= -18.0) {
            this.myCan = new Suncanvas(this.bild3, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
        }
        if (hoehe < -18.0) {
            this.myCan = new Suncanvas(this.bild4, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
        }
        this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset);
        g.setColor(Color.red);
        if (this.dst == 0) {
            g.drawString("Standard Time", 25, 58);
            g.drawString("only", 25, 71);
        }
        Font f = new Font("Helvetica", 0, 10);
        g.setFont(f);
        g.setColor(Color.red);
        g.drawString("Location:  " + this.locString, 490, 340);
        g.setColor(Color.black);
        this.str = String.valueOf(Math.abs(Math.round(100.0 * this.latitude) / 100.0));
        if (this.latitude >= 0.0) {
            this.str = String.valueOf(this.str) + " ° N";
        }
        else {
            this.str = String.valueOf(this.str) + " ° S";
        }
        g.drawString("Latitude", 490, 355);
        g.drawString(this.str, 560, 355);
        this.str = String.valueOf(Math.abs(Math.round(100.0 * this.longitude) / 100.0));
        if (this.longitude >= 0.0) {
            this.str = String.valueOf(this.str) + " ° E";
        }
        else {
            this.str = String.valueOf(this.str) + " ° W";
        }
        g.drawString("Longitude ", 490, 370);
        g.drawString(this.str, 560, 370);
        g.setColor(Color.black);
        this.mymapcanvas.update(g);
        this.myCan.update(g);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        g.setColor(Color.red);
        g.drawString("Click into the map", 490, 435);
        String usrStr = "";
        if (this.demo) {
            usrStr = "demo running";
            f = g.getFont();
            g.setFont(new Font("Chicago", 0, 110));
            g.setColor(Color.red);
            g.drawString("D E M O", 150, 200);
            g.setFont(new Font("Chicago", 0, 40));
            g.drawString("location menu disabled", 50, 450);
            g.setFont(f);
        }
        g.drawString(usrStr, 490, 507);
    }
    
    public boolean mouseDown(final Event event, final int x, final int y) {
        final int xL = 30;
        final int x2 = 256;
        final int y2 = 417;
        this.xMouse = x;
        this.yMouse = y;
        final String str = "";
        if (this.xMouse >= 30 && this.xMouse <= 482 && this.yMouse >= 305 && this.yMouse <= 529) {
            this.clicked = true;
            this.latitude = -(this.yMouse - 417) * 180.0 / 224.0;
            this.longitude = -180.0 + (this.xMouse - 30) * 360.0 / 452.0;
            this.getClickOffset();
            this.latStr = String.valueOf(Math.abs(Math.round(100.0 * this.latitude) / 100.0));
            this.fieldLatDeg.setText(this.latStr);
            if (this.latitude <= 0.0) {
                this.nsChoice.select("S");
            }
            else {
                this.nsChoice.select("N");
            }
            this.longStr = String.valueOf(Math.abs(Math.round(100.0 * this.longitude) / 100.0));
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
            this.offset = this.clickLocOffset;
            this.checkSummer(this.dat, this.offset, this.dst);
            this.usrSummer = this.summerBox.getState();
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
            this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
            this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset);
            this.repaint();
            return true;
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int code) {
        int n = 0;
        final int cmd = 0;
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
                }
                this.dat.setHours(this.hours);
                this.hoursChoice.select(this.hours);
                if (this.date > this.daysInMonth(this.month, this.year)) {
                    this.date = 1;
                    ++this.month;
                    if (this.month > 11) {
                        this.month = 0;
                        ++this.year;
                    }
                    this.dat.setMonth(this.month);
                    this.dat.setYear(this.year);
                    this.monthChoice.select(this.month);
                }
                this.dat.setDate(this.date);
                this.dateChoice.select(this.date - 1);
            }
            this.dat.setMinutes(this.minutes);
            this.minChoice.select(this.minutes);
            this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
            this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset);
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
                    }
                    this.date = this.daysInMonth(this.month, this.year);
                    this.dat.setMonth(this.month);
                    this.monthChoice.select(this.month);
                }
                this.dat.setDate(this.date);
                this.dateChoice.select(this.date - 1);
            }
            this.dat.setMinutes(this.minutes);
            this.minChoice.select(this.minutes);
            this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
            this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset);
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
            if (this.locString != "User Input") {
                this.offset = (int)this.loc.getLatLong(this.locString, 3);
            }
            else {
                this.offset = this.clickLocOffset;
            }
            this.checkSummer(this.dat, this.offset, this.dst);
            this.locOffset = this.offset;
            this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
            this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset);
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
            this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
            this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset);
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
            this.dat.setSeconds(0);
            this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
            this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset);
            this.repaint();
            return true;
        }
        return false;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        final double oldLat = this.latitude;
        final double oldLong = this.longitude;
        if (event.target instanceof Checkbox && event.target == this.summerBox) {
            if (this.summerBox.getState()) {
                this.isSummer = true;
            }
            else {
                this.isSummer = false;
                this.usrSummer = this.summerBox.getState();
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
            this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
            this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset);
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
                this.minutes = this.dat.getMinutes();
                this.minChoice.select(this.minutes);
                this.date = this.dat.getDate();
                this.month = this.dat.getMonth();
                if (this.locString != "User Input") {
                    this.offset = (int)this.loc.getLatLong(this.locString, 3);
                    this.checkSummer(this.dat, this.offset, this.dst);
                }
                else {
                    this.getClickOffset();
                    this.offset = this.usrOffset;
                }
                this.locOffset = this.offset;
                final int gmtHours = this.hours + this.dat.getTimezoneOffset() / 60;
                this.hours = gmtHours + this.offset;
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
                this.hoursStr = String.valueOf(this.hours);
                if (this.hours < 10) {
                    this.hoursStr = "0" + this.hoursStr;
                }
                this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset);
                this.repaint();
                return true;
            }
        }
        if (event.target instanceof TextField) {
            if (this.demo) {
                return true;
            }
            this.str = "";
            final char c1 = ',';
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
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                    this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                    this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset);
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
                this.longStr = this.str;
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
                    this.longStr = this.str;
                    this.locString = "User Input";
                    this.locChoice.select(this.locString);
                }
                this.fieldLatDeg.setText(this.latStr);
                this.fieldLongDeg.setText(this.longStr);
                this.getClickOffset();
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
                this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset);
                this.repaint();
                return true;
            }
        }
        if (event.target instanceof Choice) {
            if (this.demo) {
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
                    this.checkSummer(this.dat, this.offset, this.dst);
                }
                else {
                    this.latitude = this.loc.getLatLong(this.locString, 1);
                    this.longitude = this.loc.getLatLong(this.locString, 2);
                    this.offset = (int)this.loc.getLatLong(this.locString, 3);
                    this.dst = (int)this.loc.getLatLong(this.locString, 4);
                    this.checkSummer(this.dat, this.offset, this.dst);
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
                this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset);
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
                    if (this.locString != "User Input") {
                        this.offset = (int)this.loc.getLatLong(this.locString, 3);
                    }
                    else {
                        this.getClickOffset();
                        this.offset = this.clickLocOffset;
                        this.usrOffset = this.offset;
                    }
                    this.checkSummer(this.dat, this.offset, this.dst);
                    this.locOffset = this.offset;
                    this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                    this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset);
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
                    if (this.locString != "User Input") {
                        this.offset = (int)this.loc.getLatLong(this.locString, 3);
                    }
                    else {
                        if (this.longitude >= -7.5) {
                            this.clickLocOffset = (int)((this.longitude + 7.5) / 15.0);
                        }
                        else {
                            this.clickLocOffset = -(int)(-(this.longitude - 7.5) / 15.0);
                        }
                        this.offset = this.clickLocOffset;
                        this.usrOffset = this.offset;
                    }
                    this.checkSummer(this.dat, this.offset, this.dst);
                    this.locOffset = this.offset;
                    this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                    this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset);
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
                    this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                    this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset);
                    this.repaint();
                    return true;
                }
                if (event.target == this.hoursChoice) {
                    this.hoursStr = this.hoursChoice.getSelectedItem();
                    this.hours = Integer.parseInt(this.hoursStr);
                    this.hoursChoice.select(this.hoursStr);
                    this.dat.setHours(this.hours);
                    this.dat.setSeconds(0);
                    this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                    this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset);
                    this.repaint();
                    return true;
                }
                if (event.target == this.minChoice) {
                    this.minStr = this.minChoice.getSelectedItem();
                    this.minutes = Integer.parseInt(this.minStr);
                    this.minChoice.select(this.minStr);
                    this.dat.setMinutes(this.minutes);
                    this.dat.setSeconds(0);
                    this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                    this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset);
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
                    this.offset = (int)this.loc.getLatLong(this.locString, 3);
                    this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                    this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset);
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
                    this.myCan = new Suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                    this.mymapcanvas = new Mapcanvas(this.map, this.dat, this.latitude, this.longitude, this.clicked, this.locOffset);
                    this.repaint();
                    return true;
                }
                if (event.target == this.writeChoice) {
                    this.startCursor();
                    if (this.writeChoice.getSelectedItem().equals("Month")) {
                        this.myCan.write(1);
                    }
                    if (this.writeChoice.getSelectedItem().equals("Year")) {
                        this.myCan.write(12);
                    }
                    this.writeChoice.select(0);
                    this.stopCursor();
                }
            }
        }
        this.repaint();
        return true;
    }
    
    public sunshine334() {
        this.versStr = "  3.34US";
        this.UV = true;
        this.xMouse = 0;
        this.yMouse = 0;
        this.clicked = true;
        this.dayArray = new String[7];
        this.demo = true;
        this.usrLat = 0.0;
        this.usrLong = 0.0;
        this.usrOffset = 0;
        this.top = 95;
        this.clickLocOffset = 0;
        this.dst = 1;
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
    }
}
