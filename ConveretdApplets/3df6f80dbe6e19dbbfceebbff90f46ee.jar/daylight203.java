import java.awt.Cursor;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Dialog;
import java.net.URL;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Font;
import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Label;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Image;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class daylight203 extends Applet
{
    final char deg = '°';
    Date dat;
    int year;
    int month;
    int date;
    int hours;
    int minutes;
    int seconds;
    Image bild;
    Image bild1;
    Image bild2;
    Image bild3;
    Image bild4;
    public double latitude;
    public double longitude;
    double GHA;
    double dec;
    double hoehe;
    double azimut;
    int browserOffset;
    public int locOffset;
    String versStr;
    public suncanvas myCan;
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
    public String locString;
    public String timeString;
    public Choice writeChoice;
    TextField fieldLatDeg;
    TextField fieldLongDeg;
    Choice ewChoice;
    Choice nsChoice;
    Choice hoursChoice;
    Choice minChoice;
    Choice yearChoice;
    Choice dateChoice;
    Choice monthChoice;
    Checkbox box;
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
    final int xL = 60;
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
    boolean online;
    boolean console;
    boolean sunboxState;
    String homeString;
    String homeLatStr;
    String homeLongStr;
    double homeLat;
    double homeLong;
    String horizonViewStr;
    boolean demo;
    String userString;
    int xMouse;
    int yMouse;
    double usrLat;
    double usrLong;
    boolean write;
    boolean written;
    boolean isYear;
    Image currentImg;
    Image map;
    Choice viewChoice;
    boolean showSun;
    boolean showHours;
    Rectangle rect;
    Point pt;
    boolean summary;
    boolean AM;
    String[][] STR;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 14766;
    }
    
    public void init() {
        final Color background = new Color(235, 235, 255);
        this.setBackground(background);
        this.setFont(new Font("Helvetica", 0, 12));
        this.userString = this.getParameter("email");
        this.homeString = this.getParameter("location");
        this.homeLatStr = this.getParameter("latitude");
        this.homeLongStr = this.getParameter("longitude");
        this.horizonViewStr = this.getParameter("view");
        final URL url = this.getDocumentBase();
        this.str = url.toString();
        this.str = String.valueOf(this.str) + "1234567890123456789012345";
        this.wwwStr = this.str.substring(0, 27);
        if (this.horizonViewStr.equals("0")) {
            this.bild = this.getImage(this.getDocumentBase(), "applet/sunview/a.gif");
            this.bild1 = this.getImage(this.getDocumentBase(), "applet/sunview/b.gif");
            this.bild2 = this.getImage(this.getDocumentBase(), "applet/sunview/c.gif");
            this.bild3 = this.getImage(this.getDocumentBase(), "applet/sunview/d.gif");
            this.bild4 = this.getImage(this.getDocumentBase(), "applet/sunview/e.gif");
        }
        else {
            this.bild = this.getImage(this.getDocumentBase(), "applet/sunview1/a.gif");
            this.bild1 = this.getImage(this.getDocumentBase(), "applet/sunview1/b.gif");
            this.bild2 = this.getImage(this.getDocumentBase(), "applet/sunview1/c.gif");
            this.bild3 = this.getImage(this.getDocumentBase(), "applet/sunview1/d.gif");
            this.bild4 = this.getImage(this.getDocumentBase(), "applet/sunview1/e.gif");
        }
        this.map = this.getImage(this.getDocumentBase(), "applet/bigmap.gif");
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
        this.browserOffset = this.dat.getTimezoneOffset();
        this.browserOffset = -this.browserOffset / 60;
        this.locOffset = this.browserOffset;
        this.timeString = "UT " + this.browserOffset + " h";
        if (this.browserOffset > 0) {
            this.timeString = "UT +" + this.browserOffset + " h";
        }
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
        (this.L13 = new Label()).setText("Lat.: xx.xx");
        gbl.setConstraints(this.L13, gbc);
        this.add(this.L13);
        (this.L14 = new Label()).setText("N / S");
        gbl.setConstraints(this.L14, gbc);
        this.add(this.L14);
        (this.L15 = new Label()).setText("Long.: xx.xx");
        gbl.setConstraints(this.L15, gbc);
        this.add(this.L15);
        (this.L16 = new Label()).setText("E / W");
        gbl.setConstraints(this.L16, gbc);
        this.add(this.L16);
        (this.L17 = new Label()).setText("Twilight");
        gbl.setConstraints(this.L17, gbc);
        this.add(this.L17);
        (this.L17 = new Label()).setText("View");
        gbl.setConstraints(this.L17, gbc);
        this.add(this.L17);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 0, 0);
        this.time = new sunloc();
        this.timeChoice = new Choice();
        this.time.timeMenu(this.timeChoice);
        gbl.setConstraints(this.timeChoice, gbc);
        this.timeChoice.select(this.timeString);
        this.add(this.timeChoice);
        gbc.insets = new Insets(0, 0, 0, 0);
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
            this.ewStr = "W";
        }
        else {
            this.ewStr = "E";
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
        gbl.setConstraints(this.fieldLongDeg = new TextField(this.longStr, 4), gbc);
        this.add(this.fieldLongDeg);
        (this.ewChoice = new Choice()).addItem("E");
        this.ewChoice.addItem("W");
        this.ewChoice.select(this.ewStr);
        gbl.setConstraints(this.ewChoice, gbc);
        this.add(this.ewChoice);
        gbl.setConstraints(this.box = new Checkbox(), gbc);
        this.box.setState(true);
        this.add(this.box);
        gbc.insets = new Insets(0, 0, 0, 10);
        gbl.setConstraints(this.viewChoice = new Choice(), gbc);
        this.viewChoice.addItem("Select...");
        this.viewChoice.addItem("Horizon");
        this.viewChoice.addItem("Map");
        this.viewChoice.addItem("Hours/Month");
        this.viewChoice.addItem("Hours/Day");
        this.viewChoice.addItem("Duration of Sunlight");
        this.viewChoice.addItem("Sun on/off");
        this.viewChoice.addItem("Hours on/off");
        this.add(this.viewChoice);
        gbc.gridy = 2;
        (this.L17 = new Label()).setText("Local Time:");
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
        gbl.setConstraints(this.myCan = new suncanvas(this.map, this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, false, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours), gbc);
        this.add(this.myCan);
        this.resize(this.size().width, this.size().height);
        this.repaint();
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 7;
        gbc2.weightx = 0.0;
        gbc2.gridy = 12;
        gbc2.weighty = 6.0;
        gbc2.insets = new Insets(0, 0, 0, 15);
        gbl.setConstraints(this.aboutButton = new Button(), gbc2);
        this.aboutButton.setLabel("About...");
        this.add(this.aboutButton);
        gbc2 = new GridBagConstraints();
        gbc2.gridx = 7;
        gbc2.weightx = 0.0;
        gbc2.gridy = 6;
        gbc2.weighty = 0.0;
        gbc2.insets = new Insets(0, 0, 0, 25);
        gbl.setConstraints(this.writeChoice = new Choice(), gbc2);
        this.writeChoice.addItem("Write / Draw ...");
        this.writeChoice.addItem("Month");
        this.writeChoice.addItem("Year");
        this.writeChoice.addItem("Rise Year");
        this.writeChoice.addItem("Set Year");
        this.writeChoice.addItem("Summary Year");
        this.writeChoice.addItem("Seasons");
        this.writeChoice.addItem("Duration/Lat.");
        this.writeChoice.addItem("Sunrise/Lat.");
        this.add(this.writeChoice);
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        final int xy = this.formula(this.wwwStr, 27);
        if (this.formula(this.wwwStr, 18) == this.formula("http://www.tyge.de", 18) || this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(this.wwwStr, 22) == this.formula("http://www.geoastro.de", 22) || this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 27) == this.formula("http://www.jgiesen.business", 27)) {
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
            if (!ok) {
                final Dialog Aboutdialog = new Aboutdialog(this, this.versStr, this.demo);
                Aboutdialog.resize(250, 230);
                Aboutdialog.show();
            }
        }
        System.out.println("Daylight Applet " + this.versStr + " (c) 1999-2011 J. Giesen - www.GeoAstro.de");
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
    
    public void findElev(final double AZ) {
        final double[][][] az = new double[32][12][125];
        double az2 = 0.0;
        double az3 = 0.0;
        final int[][] T = new int[32][12];
        final double[][] t = new double[32][12];
        final double[][] eot = new double[32][12];
        for (int d = 1; d <= 31; ++d) {
            for (int m = 0; m < 12; ++m) {
                eot[d][m] = this.comp.EOT(this.comp.JD(d, m + 1, this.year + 1900, 12 - this.locOffset));
                for (int i = 0; i < 24; ++i) {
                    final double jd = this.comp.JD(d, m + 1, this.year + 1900, i);
                    final double dec = this.comp.sunDecRA(1, jd);
                    final double ra = this.comp.sunDecRA(2, jd);
                    az[d][m][i] = this.comp.sun_elev(jd, this.latitude, -this.longitude, dec, ra);
                }
            }
        }
        for (int d2 = 1; d2 <= 31; ++d2) {
            for (int j = 0; j < 12; ++j) {
                for (int k = 0; k < 23; ++k) {
                    az2 = az[d2][j][k];
                    az3 = az[d2][j][k + 1];
                    if (this.AM) {
                        if (az2 < AZ && az3 > AZ) {
                            T[d2][j] = k;
                            break;
                        }
                    }
                    else if (az2 > AZ && az3 < AZ) {
                        T[d2][j] = k;
                        break;
                    }
                }
            }
        }
        for (int d3 = 1; d3 <= 31; ++d3) {
            for (int l = 0; l < 12; ++l) {
                for (int i2 = 0; i2 < 122; ++i2) {
                    final double jd = this.comp.JD(d3, l + 1, this.year + 1900, T[d3][l] + i2 / 60.0);
                    final double dec = this.comp.sunDecRA(1, jd);
                    final double ra = this.comp.sunDecRA(2, jd);
                    az[d3][l][i2] = this.comp.sun_elev(jd, this.latitude, -this.longitude, dec, ra);
                }
            }
        }
        for (int d4 = 1; d4 <= 31; ++d4) {
            for (int m2 = 0; m2 < 12; ++m2) {
                for (int i3 = 0; i3 < 60; ++i3) {
                    az2 = az[d4][m2][i3];
                    az3 = az[d4][m2][i3 + 1];
                    final double delta = (AZ - az2) / (az3 - az2);
                    if (this.AM) {
                        if (az2 < AZ && az3 > AZ) {
                            t[d4][m2] = i3 + delta;
                            break;
                        }
                    }
                    else if (az2 > AZ && az3 < AZ) {
                        t[d4][m2] = i3 + delta;
                        break;
                    }
                }
            }
        }
        double UT = 0.0;
        for (int d5 = 1; d5 <= 31; ++d5) {
            for (int m3 = 0; m3 < 12; ++m3) {
                UT = T[d5][m3] + t[d5][m3] / 60.0;
                if (UT > 0.0) {
                    UT += this.locOffset;
                    this.STR[d5][m3] = " " + this.comp.makeTimeString("", UT);
                }
                else {
                    this.STR[d5][m3] = "      ";
                }
                UT = 0.0;
            }
        }
    }
    
    public void doElev(final double AZ) {
        final String[] dataStr = new String[380];
        int n = 1;
        this.findElev(-0.833);
        for (int d = 1; d <= 31; ++d) {
            String str = " " + d;
            if (d < 10) {
                str = " 0" + d;
            }
            dataStr[n] = String.valueOf(str) + " ";
            for (int m = 0; m < 12; ++m) {
                str = this.STR[d][m];
                if (d <= this.comp.daysInMonth(m, this.year)) {
                    final String[] array = dataStr;
                    final int n2 = n;
                    array[n2] = String.valueOf(array[n2]) + " " + str;
                }
                else {
                    final String[] array2 = dataStr;
                    final int n3 = n;
                    array2[n3] = String.valueOf(array2[n3]) + "     -  ";
                }
            }
            final String[] array3 = dataStr;
            final int n4 = n;
            array3[n4] = String.valueOf(array3[n4]) + "\n";
            if (d % 5 == 0 && d < 30) {
                final String[] array4 = dataStr;
                final int n5 = n;
                array4[n5] = String.valueOf(array4[n5]) + "----------------------------------------------------------------------------------------------------\n";
            }
            ++n;
        }
        String latStr = new StringBuffer().append(Math.abs(this.latitude)).append('°').toString();
        if (this.latitude >= 0.0) {
            latStr = String.valueOf(latStr) + " N";
        }
        else {
            latStr = String.valueOf(latStr) + " S";
        }
        String longStr = new StringBuffer().append(Math.abs(this.longitude)).append('°').toString();
        if (this.longitude >= 0.0) {
            longStr = String.valueOf(longStr) + " E";
        }
        else {
            longStr = String.valueOf(longStr) + " W";
        }
        String utStr = ",  UT " + this.locOffset + " h";
        if (this.locOffset >= 0) {
            utStr = ",  UT +" + this.locOffset + " h";
        }
        String str = ",  Standard Time";
        dataStr[0] = "\n  Latitude " + latStr + "  Logitude " + longStr + utStr + ",  Elevation -0.833" + '°' + str + "\n" + "\n";
        final String[] array5 = dataStr;
        final int n6 = 0;
        array5[n6] = String.valueOf(array5[n6]) + "        ";
        for (int i = 0; i < 12; ++i) {
            final String[] array6 = dataStr;
            final int n7 = 0;
            array6[n7] = String.valueOf(array6[n7]) + this.monthArray[i] + "     ";
        }
        final String[] array7 = dataStr;
        final int n8 = 0;
        array7[n8] = String.valueOf(array7[n8]) + "\n";
        String title = "";
        if (this.AM) {
            title = String.valueOf(title) + "Sunrise ";
        }
        else {
            title = String.valueOf(title) + "Sunset ";
        }
        final scrollFrame sf = new scrollFrame(String.valueOf(title) + (this.year + 1900), 31, dataStr, this.online);
        sf.resize(630, 550);
        sf.show();
    }
    
    public void paint(final Graphics g) {
        final int unten = 390;
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        Font f = new Font("Helvetica", 0, 10);
        g.setFont(f);
        g.setColor(Color.red);
        this.comp = new compute();
        final double STD = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        final double JD = this.comp.JD(this.date, this.month + 1, this.year + 1900, STD);
        final double dec = this.comp.sunDecRA(1, JD);
        final double RA = this.comp.sunDecRA(2, JD);
        final double hoehe = this.comp.sun_elev(JD, this.latitude, -this.longitude, dec, RA);
        if (this.browserOffset > 0) {
            g.drawString("System Clock Time Zone Offset is +" + this.browserOffset + " h", 15, 93);
        }
        else {
            g.drawString("System Clock Time Zone Offset is " + this.browserOffset + " h", 15, 93);
        }
        if (hoehe >= 0.0) {
            this.currentImg = this.bild;
        }
        if (hoehe < 0.0 && hoehe >= -6.0) {
            this.currentImg = this.bild1;
        }
        if (hoehe < -6.0 && hoehe >= -12.0) {
            this.currentImg = this.bild2;
        }
        if (hoehe < -12.0 && hoehe >= -18.0) {
            this.currentImg = this.bild3;
        }
        if (hoehe < -18.0) {
            this.currentImg = this.bild4;
        }
        this.myCan.update(g);
        if (this.demo) {
            this.userString = "demo running";
        }
        else {
            this.userString = this.email;
        }
        if (this.online) {
            this.userString = "running online";
        }
        if (this.demo) {
            f = g.getFont();
            g.setColor(Color.red);
            g.setFont(new Font("Chicago", 0, 96));
            g.drawString("D E M O", 150, 280);
            g.setFont(f);
        }
    }
    
    public boolean mouseDown(final Event event, final int x, final int y) {
        final int xR = 608;
        final int y2 = 116;
        final int y3 = 404;
        this.xMouse = x;
        this.yMouse = y;
        final String str = "";
        final int y4 = 114;
        double hClick = 0.0;
        this.pt = new Point(x, y);
        if (this.rect.contains(this.pt)) {
            hClick = -24.0 * (this.yMouse - 404) / 288.0;
            this.hours = (int)hClick;
            this.minutes = (int)Math.round((hClick - this.hours) * 60.0);
            this.dat.setHours(this.hours);
            this.hoursChoice.select(this.hours);
            this.dat.setMinutes(this.minutes);
            this.minChoice.select(this.minutes);
            this.dat.setSeconds(0);
            final int NumberOfDays = 365;
            final double xDate = NumberOfDays * (this.xMouse - 60) / 548.0;
            int sum = 0;
            int monthClick = 0;
            int dateClick = 1;
            for (int i = 1; i < NumberOfDays + 1; ++i) {
                if (sum < (int)xDate) {
                    sum += this.daysInMonth(i, this.year);
                    monthClick = i;
                }
            }
            this.month = monthClick - 1;
            this.dat.setMonth(this.month);
            this.monthChoice.select(this.month);
            dateClick = this.daysInMonth(monthClick, this.year) - (int)(sum - xDate);
            if (dateClick == 0) {
                dateClick = 1;
            }
            if (dateClick > this.daysInMonth(monthClick, this.year)) {
                dateClick = this.daysInMonth(monthClick, this.year);
            }
            this.date = dateClick;
            this.dat.setDate(this.date);
            this.dateChoice.select(this.date - 1);
            this.comp = new compute();
            final double STD = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
            final double JD = this.comp.JD(this.date, this.month + 1, this.year + 1900, STD);
            final double dec = this.comp.sunDecRA(1, JD);
            final double RA = this.comp.sunDecRA(2, JD);
            this.hoehe = this.comp.sun_elev(JD, this.latitude, -this.longitude, dec, RA);
            if (this.hoehe >= 0.0) {
                this.myCan = new suncanvas(this.map, this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, false, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
            }
            if (this.hoehe < 0.0 && this.hoehe >= -6.0) {
                this.myCan = new suncanvas(this.map, this.bild1, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, false, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
            }
            if (this.hoehe < -6.0 && this.hoehe >= -12.0) {
                this.myCan = new suncanvas(this.map, this.bild2, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, false, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
            }
            if (this.hoehe < -12.0 && this.hoehe >= -18.0) {
                this.myCan = new suncanvas(this.map, this.bild3, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, false, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
            }
            if (this.hoehe < -18.0) {
                this.myCan = new suncanvas(this.map, this.bild4, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, false, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
            }
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseMove(final Event event, final int x, final int y) {
        this.pt = new Point(x, y);
        if (this.rect.contains(this.pt)) {
            this.setCursor(new Cursor(1));
        }
        else {
            this.setCursor(new Cursor(0));
        }
        return true;
    }
    
    public void startCursor() {
        this.showStatus("Calculating...");
        this.setCursor(new Cursor(3));
    }
    
    public void startCursor1() {
        this.showStatus("Calculating for 1 or 2 minutes. Please be patient ...");
        this.setCursor(new Cursor(3));
    }
    
    public void stopCursor() {
        this.showStatus("");
        this.setCursor(new Cursor(0));
    }
    
    public String makeDMSString(final double time) {
        final String str = "?";
        final int d = (int)time;
        final int h = (int)(24.0 * (time - d));
        final int m = (int)Math.round(1440.0 * (time - d) - 60 * h);
        String minStr;
        if (m >= 10) {
            minStr = String.valueOf(m) + " min";
        }
        else {
            minStr = "0" + m + " min";
        }
        return d + " d  " + h + " h  " + minStr;
    }
    
    public boolean keyDown(final Event event, final int code) {
        if (code != 104 && code != 100 && code != 109 && code != 72 && code != 68 && code != 77) {
            return false;
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
                final int n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = 1;
                    ++this.month;
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
            this.myCan = new suncanvas(this.map, this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, false, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
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
                final int n = this.daysInMonth(this.month, this.year);
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
            this.myCan = new suncanvas(this.map, this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, false, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
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
            final int n = this.daysInMonth(this.month, this.year);
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
            this.myCan = new suncanvas(this.map, this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, false, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
            this.repaint();
            return true;
        }
        return false;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        int n = 0;
        if (event.target instanceof Button) {
            if (event.target == this.aboutButton) {
                final Dialog Aboutdialog = new Aboutdialog(this, this.versStr, this.demo);
                Aboutdialog.resize(250, 230);
                Aboutdialog.show();
                this.repaint();
                return true;
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
                this.browserOffset = -this.dat.getTimezoneOffset() / 60;
                this.myCan = new suncanvas(this.map, this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, this.sunboxState, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
            }
        }
        if (event.target instanceof Choice) {
            if (event.target == this.viewChoice) {
                if (this.viewChoice.getSelectedItem().equals("Horizon")) {
                    this.startCursor();
                    this.myCan = new suncanvas(this.map, this.currentImg, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, true, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
                    this.viewChoice.select("Select...");
                    this.stopCursor();
                    return true;
                }
                if (this.viewChoice.getSelectedItem().equals("Map")) {
                    this.startCursor();
                    this.myCan.doMap();
                    this.viewChoice.select("Select...");
                    this.stopCursor();
                    return true;
                }
                if (this.viewChoice.getSelectedItem().equals("Hours/Month")) {
                    this.startCursor();
                    (this.myCan = new suncanvas(this.map, this.currentImg, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, false, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours)).doMonth();
                    this.viewChoice.select("Select...");
                    this.stopCursor();
                    return true;
                }
                if (this.viewChoice.getSelectedItem().equals("Hours/Day")) {
                    this.startCursor();
                    (this.myCan = new suncanvas(this.map, this.currentImg, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, false, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours)).doDay();
                    this.viewChoice.select("Select...");
                    this.stopCursor();
                    return true;
                }
                if (this.viewChoice.getSelectedItem().equals("Sun on/off")) {
                    this.startCursor();
                    this.showSun ^= true;
                    this.myCan = new suncanvas(this.map, this.currentImg, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, false, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
                    this.viewChoice.select("Select...");
                    this.stopCursor();
                    this.repaint();
                    return true;
                }
                if (this.viewChoice.getSelectedItem().equals("Hours on/off")) {
                    this.startCursor();
                    this.showHours ^= true;
                    this.myCan = new suncanvas(this.map, this.currentImg, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, false, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
                    this.viewChoice.select("Select...");
                    this.stopCursor();
                    this.repaint();
                    return true;
                }
                if (this.viewChoice.getSelectedItem().equals("Duration of Sunlight")) {
                    this.startCursor1();
                    (this.myCan = new suncanvas(this.map, this.currentImg, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, false, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours)).doSemiduration();
                    this.viewChoice.select("Select...");
                    this.stopCursor();
                    this.repaint();
                    return true;
                }
            }
            if (event.target == this.writeChoice) {
                if (this.writeChoice.getSelectedItem().equals("Duration/Lat.")) {
                    this.startCursor();
                    final durationFrame durf = new durationFrame(this.dat, this.latitude);
                    durf.resize(350, 130);
                    durf.show();
                    this.stopCursor();
                    this.writeChoice.select("Write...");
                    return true;
                }
                if (this.writeChoice.getSelectedItem().equals("Sunrise/Lat.")) {
                    this.startCursor();
                    final drawSunriseFrame srf = new drawSunriseFrame(this.year + 1900, this.latitude, this.longitude, this.locOffset, this.online);
                    srf.resize(810, 670);
                    srf.show();
                    this.stopCursor();
                    this.writeChoice.select(0);
                    return true;
                }
                if (this.writeChoice.getSelectedItem().equals("Seasons")) {
                    final computeSeasons mySeasons = new computeSeasons(this.year + 1900);
                    final String[] dataStr = { "\nSpring:  " + mySeasons.spring() + "\n", "Summer:  " + mySeasons.summer() + "\n", "Autumn:  " + mySeasons.autumn() + "\n", "Winter:  " + mySeasons.winter() + "\n" };
                    final seasonsFrame seasonsf = new seasonsFrame("Seasons " + (this.year + 1900), 3, dataStr);
                    seasonsf.schreiben(dataStr, 3, this.online);
                    seasonsf.resize(220, 130);
                    seasonsf.show();
                    this.writeChoice.select("Write...");
                    return true;
                }
                this.startCursor();
                if (this.writeChoice.getSelectedItem().equals("Month")) {
                    this.write = true;
                    this.isYear = false;
                }
                if (this.writeChoice.getSelectedItem().equals("Year")) {
                    this.write = true;
                    this.isYear = true;
                }
                if (this.writeChoice.getSelectedItem().equals("Summary Year")) {
                    this.write = true;
                    this.isYear = true;
                    this.summary = true;
                }
                if (this.writeChoice.getSelectedItem().equals("Rise Year")) {
                    this.AM = true;
                    this.doElev(-0.833);
                }
                if (this.writeChoice.getSelectedItem().equals("Set Year")) {
                    this.AM = false;
                    this.doElev(-0.833);
                }
                if (!this.written) {
                    this.written = true;
                }
                if (this.locOffset > 0) {
                    this.str = " + " + this.locOffset;
                }
                else {
                    this.str = " - " + Math.abs(this.locOffset);
                }
                if (this.write) {
                    this.myCan = new suncanvas(this.map, this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, false, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
                }
                this.summary = false;
                this.repaint();
                this.write = false;
                this.writeChoice.select("Write / Draw ...");
                this.stopCursor();
                return true;
            }
        }
        if (event.target instanceof Checkbox && event.target == this.box) {
            if (this.box.getState()) {
                this.console = true;
            }
            else {
                this.console = false;
            }
            this.startCursor();
            this.myCan = new suncanvas(this.map, this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, this.sunboxState, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
            this.repaint();
            this.stopCursor();
            return true;
        }
        if (event.target instanceof TextField) {
            final double oldLat = this.latitude;
            double oldLong = this.longitude;
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
                }
                final Double latDouble = Double.valueOf(this.str);
                this.latitude = latDouble;
                if (this.nsChoice.getSelectedItem().equals("S")) {
                    this.latitude = -Math.abs(this.latitude);
                    this.usrLat = this.latitude;
                }
                this.fieldLatDeg.setText(this.str);
                if (this.latitude == (int)this.latitude) {
                    this.fieldLatDeg.setText(String.valueOf((int)Math.abs(this.latitude)) + ".00");
                }
                final Double longDouble = Double.valueOf(this.longStr);
                this.usrLong = longDouble;
                this.latStr = this.str;
                if (this.latitude > 90.0) {
                    this.fieldLatDeg.setText(String.valueOf(oldLat));
                    this.latitude = oldLat;
                    this.latStr = String.valueOf(Math.abs(oldLat));
                    this.fieldLatDeg.setText(this.latStr);
                }
                this.written = false;
                this.myCan = new suncanvas(this.map, this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, this.sunboxState, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
                this.fieldLatDeg.nextFocus();
                this.fieldLongDeg.selectAll();
                this.repaint();
                return true;
            }
            else if (event.target == this.fieldLongDeg) {
                oldLong = this.longitude;
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
                }
                this.longStr = this.str;
                final Double longDouble = Double.valueOf(this.longStr);
                this.longitude = longDouble;
                if (this.ewChoice.getSelectedItem().equals("W")) {
                    this.longitude = -Math.abs(this.longitude);
                    this.usrLong = this.longitude;
                }
                this.fieldLongDeg.setText(this.longStr);
                if (this.longitude > 180.0) {
                    this.fieldLongDeg.setText(String.valueOf(oldLong));
                    this.longitude = oldLong;
                }
                if (this.longitude == (int)this.longitude) {
                    this.fieldLongDeg.setText(String.valueOf((int)Math.abs(this.longitude)) + ".00");
                }
                this.written = false;
                this.myCan = new suncanvas(this.map, this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, this.sunboxState, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
                this.repaint();
                return true;
            }
        }
        if (event.target == this.locChoice) {
            this.written = false;
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
            this.myCan = new suncanvas(this.map, this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, this.sunboxState, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
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
                this.usrLat = this.latitude;
                this.usrLong = this.longitude;
                this.written = false;
                this.myCan = new suncanvas(this.map, this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, this.sunboxState, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
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
                this.written = false;
                this.myCan = new suncanvas(this.map, this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, this.sunboxState, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
                this.repaint();
                return true;
            }
            if (event.target == this.timeChoice) {
                this.timeString = this.timeChoice.getSelectedItem();
                this.locOffset = this.loc.getTimeZone(this.timeString);
                this.timeChoice.select(this.timeString);
                this.written = false;
                this.myCan = new suncanvas(this.map, this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, this.sunboxState, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
            }
            if (event.target == this.hoursChoice) {
                this.hoursStr = this.hoursChoice.getSelectedItem();
                this.hours = Integer.parseInt(this.hoursStr);
                this.hoursChoice.select(this.hoursStr);
                this.dat.setHours(this.hours);
                this.dat.setSeconds(0);
                this.myCan = new suncanvas(this.map, this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, this.sunboxState, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
                this.repaint();
                return true;
            }
            if (event.target == this.minChoice) {
                this.minStr = this.minChoice.getSelectedItem();
                this.minutes = Integer.parseInt(this.minStr);
                this.minChoice.select(this.minStr);
                this.dat.setMinutes(this.minutes);
                this.dat.setSeconds(0);
                this.myCan = new suncanvas(this.map, this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, this.sunboxState, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
                this.repaint();
                return true;
            }
            if (event.target == this.dateChoice) {
                this.dateStr = this.dateChoice.getSelectedItem();
                this.date = Integer.parseInt(this.dateStr);
                this.dat.setDate(this.date);
                this.dat.setSeconds(0);
                n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = n;
                    this.dat.setDate(this.date);
                    this.dateChoice.select(this.date - 1);
                }
                this.browserOffset = -this.dat.getTimezoneOffset() / 60;
                this.myCan = new suncanvas(this.map, this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, this.sunboxState, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
                this.repaint();
                return true;
            }
            if (event.target == this.monthChoice) {
                this.monthStr = this.monthChoice.getSelectedItem();
                this.month = this.MonthInteger(this.monthStr);
                this.monthChoice.select(this.month);
                this.dat.setMonth(this.month);
                this.dat.setSeconds(0);
                n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = n;
                    this.dat.setDate(this.date);
                    this.dateChoice.select(this.date - 1);
                }
                this.browserOffset = -this.dat.getTimezoneOffset() / 60;
                this.myCan = new suncanvas(this.map, this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, this.sunboxState, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
                this.repaint();
                return true;
            }
            if (event.target == this.yearChoice) {
                this.yearStr = this.yearChoice.getSelectedItem();
                this.year = Integer.parseInt(this.yearStr);
                this.yearChoice.select(this.yearStr);
                this.year -= 1900;
                this.dat.setYear(this.year);
                n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = n;
                    this.dat.setDate(this.date);
                    this.dateChoice.select(this.date - 1);
                }
                this.browserOffset = -this.dat.getTimezoneOffset() / 60;
                this.myCan = new suncanvas(this.map, this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console, this.sunboxState, this.write, this.isYear, this.summary, this.online, this.showSun, this.showHours);
                this.repaint();
                return true;
            }
            this.repaint();
            return true;
        }
    }
    
    public daylight203() {
        this.versStr = " v. 2.03";
        this.monthArray = new String[12];
        this.online = false;
        this.console = true;
        this.sunboxState = false;
        this.demo = true;
        this.usrLat = 0.0;
        this.usrLong = 0.0;
        this.write = false;
        this.written = false;
        this.isYear = false;
        this.showSun = true;
        this.showHours = true;
        this.rect = new Rectangle(60, 116, 548, 288);
        this.summary = false;
        this.AM = true;
        this.STR = new String[32][12];
    }
}
