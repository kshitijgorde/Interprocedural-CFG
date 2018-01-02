import java.awt.Event;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Label;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.Button;
import java.awt.Image;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class sunview333 extends Applet
{
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
    public Frame parentFrame;
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
    Checkbox box;
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
    final int xL = 30;
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
    boolean licensed;
    boolean console;
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
    
    public int formula(final String s, final int n) {
        long n2 = 0L;
        for (int i = 0; i < n; ++i) {
            final char char1 = s.charAt(i);
            final long n3 = i * Character.digit(char1, i) * Character.digit(char1, 36 - i);
            final long n4 = Character.digit(char1, 36 - i);
            n2 += n4 * n4;
        }
        final long n5 = n2 + 100L;
        if ((int)n5 < 99) {
            return -2 * this.seconds;
        }
        return (int)n5;
    }
    
    public void init() {
        this.setBackground(new Color(235, 235, 255));
        this.userString = this.getParameter("email");
        this.homeString = this.getParameter("location");
        this.homeLatStr = this.getParameter("latitude");
        this.homeLongStr = this.getParameter("longitude");
        this.serverStr = this.getParameter("server");
        this.str = this.getDocumentBase().toString();
        this.str = String.valueOf(this.str) + "1234567890123456789012345";
        this.wwwStr = this.str.substring(0, 27);
        this.bild = this.getImage(this.getDocumentBase(), "applet/sunview/a.gif");
        this.bild1 = this.getImage(this.getDocumentBase(), "applet/sunview/b.gif");
        this.bild2 = this.getImage(this.getDocumentBase(), "applet/sunview/c.gif");
        this.bild3 = this.getImage(this.getDocumentBase(), "applet/sunview/d.gif");
        this.bild4 = this.getImage(this.getDocumentBase(), "applet/sunview/e.gif");
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(this.bild, 0);
        mediaTracker.addImage(this.bild1, 0);
        mediaTracker.addImage(this.bild2, 0);
        mediaTracker.addImage(this.bild3, 0);
        mediaTracker.addImage(this.bild4, 0);
        try {
            mediaTracker.waitForID(0);
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
        this.timeString = String.valueOf(this.browserOffset) + " h";
        if (this.browserOffset > 0) {
            this.timeString = "+" + this.timeString;
        }
        this.timeString = " " + this.timeString;
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        this.setLayout(layout);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(2, 0, 0, 0);
        (this.L11 = new Label()).setText("Time Zone Offset");
        layout.setConstraints(this.L11, gridBagConstraints);
        this.add(this.L11);
        (this.L12 = new Label()).setText("Location");
        layout.setConstraints(this.L12, gridBagConstraints);
        this.add(this.L12);
        gridBagConstraints.weightx = 10.0;
        (this.L13 = new Label()).setText("Latitude: xx.xx");
        layout.setConstraints(this.L13, gridBagConstraints);
        this.add(this.L13);
        (this.L14 = new Label()).setText("N / S");
        layout.setConstraints(this.L14, gridBagConstraints);
        this.add(this.L14);
        (this.L15 = new Label()).setText("Longitude: xx.xx");
        layout.setConstraints(this.L15, gridBagConstraints);
        this.add(this.L15);
        (this.L16 = new Label()).setText("E / W");
        layout.setConstraints(this.L16, gridBagConstraints);
        this.add(this.L16);
        (this.L17 = new Label()).setText("Java Console");
        layout.setConstraints(this.L17, gridBagConstraints);
        this.add(this.L17);
        (this.L17 = new Label()).setText("");
        layout.setConstraints(this.L17, gridBagConstraints);
        this.add(this.L17);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        this.time = new sunloc();
        this.timeChoice = new Choice();
        this.time.timeMenu(this.timeChoice);
        layout.setConstraints(this.timeChoice, gridBagConstraints);
        this.timeChoice.select(this.timeString);
        this.add(this.timeChoice);
        this.loc = new sunloc();
        this.locChoice = new Choice();
        this.locString = this.homeString;
        this.locChoice.addItem(this.locString);
        this.locChoice.addItem("_________");
        this.locChoice.select(this.locString);
        this.loc.locMenu(this.locChoice);
        layout.setConstraints(this.locChoice, gridBagConstraints);
        this.add(this.locChoice);
        this.latitude = Double.valueOf(this.homeLatStr);
        this.homeLat = this.latitude;
        if (this.latitude < 0.0) {
            this.nsStr = "S";
        }
        else {
            this.nsStr = "N";
        }
        this.longitude = Double.valueOf(this.homeLongStr);
        this.homeLong = this.longitude;
        if (this.longitude < 0.0) {
            this.ewStr = "W";
        }
        else {
            this.ewStr = "E";
        }
        this.latStr = String.valueOf(Math.abs(this.latitude));
        layout.setConstraints(this.fieldLatDeg = new TextField(this.latStr, 4), gridBagConstraints);
        this.add(this.fieldLatDeg);
        (this.nsChoice = new Choice()).addItem("N");
        this.nsChoice.addItem("S");
        this.nsChoice.select(this.nsStr);
        layout.setConstraints(this.nsChoice, gridBagConstraints);
        this.add(this.nsChoice);
        this.longStr = String.valueOf(Math.abs(this.longitude));
        layout.setConstraints(this.fieldLongDeg = new TextField(this.longStr, 5), gridBagConstraints);
        this.add(this.fieldLongDeg);
        (this.ewChoice = new Choice()).addItem("E");
        this.ewChoice.addItem("W");
        this.ewChoice.select(this.ewStr);
        layout.setConstraints(this.ewChoice, gridBagConstraints);
        this.add(this.ewChoice);
        layout.setConstraints(this.box = new Checkbox(), gridBagConstraints);
        this.add(this.box);
        gridBagConstraints.gridy = 2;
        (this.L17 = new Label()).setText("Local Time (hh:mm):");
        gridBagConstraints.insets = new Insets(5, 20, 0, 0);
        layout.setConstraints(this.L17, gridBagConstraints);
        this.add(this.L17);
        gridBagConstraints.insets = new Insets(5, 0, 0, 0);
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
        layout.setConstraints(this.hoursChoice, gridBagConstraints);
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
        layout.setConstraints(this.minChoice, gridBagConstraints);
        this.add(this.minChoice);
        (this.L23 = new Label()).setText("Date :");
        layout.setConstraints(this.L23, gridBagConstraints);
        this.add(this.L23);
        this.monthArray[0] = "Jan";
        this.monthArray[1] = "Feb";
        this.monthArray[2] = "Mar";
        this.monthArray[3] = "Apr";
        this.monthArray[4] = "May";
        this.monthArray[5] = "Jun";
        this.monthArray[6] = "Jul";
        this.monthArray[7] = "Aug";
        this.monthArray[8] = "Sep";
        this.monthArray[9] = "Oct";
        this.monthArray[10] = "Nov";
        this.monthArray[11] = "Dec";
        gridBagConstraints.insets = new Insets(5, 0, 0, 10);
        this.yearChoice = new Choice();
        for (int n = 0; n < 12; ++n) {
            this.yearStr = String.valueOf(this.year + 1900 - 5 + n);
            this.yearChoice.addItem(this.yearStr);
        }
        this.yearStr = String.valueOf(this.year + 1900);
        this.yearChoice.select(this.yearStr);
        layout.setConstraints(this.yearChoice, gridBagConstraints);
        this.add(this.yearChoice);
        this.monthChoice = new Choice();
        for (int n2 = 0; n2 < 12; ++n2) {
            this.monthChoice.addItem(this.monthArray[n2]);
        }
        this.monthChoice.select(this.month);
        layout.setConstraints(this.monthChoice, gridBagConstraints);
        this.add(this.monthChoice);
        this.dateChoice = new Choice();
        for (int n3 = 1; n3 < 32; ++n3) {
            this.dateStr = String.valueOf(n3);
            this.dateChoice.addItem(this.dateStr);
        }
        this.dateChoice.select(this.date - 1);
        layout.setConstraints(this.dateChoice, gridBagConstraints);
        this.add(this.dateChoice);
        (this.button = new Button()).setLabel("now");
        gridBagConstraints.insets = new Insets(5, 0, 0, 10);
        layout.setConstraints(this.button, gridBagConstraints);
        this.add(this.button);
        gridBagConstraints.gridy = 3;
        gridBagConstraints.weighty = 160.0;
        layout.setConstraints(this.myCan = new suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, false), gridBagConstraints);
        this.add(this.myCan);
        this.resize(this.size().width, this.size().height);
        this.repaint();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        gridBagConstraints2.gridx = 6;
        gridBagConstraints2.weightx = 0.0;
        gridBagConstraints2.gridy = 12;
        gridBagConstraints2.weighty = 6.0;
        layout.setConstraints(this.aboutButton = new Button(), gridBagConstraints2);
        this.aboutButton.setLabel("About...");
        this.add(this.aboutButton);
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        this.formula(this.wwwStr, 27);
        int n4;
        if (this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 22) == this.formula("http://www.geoastro.de", 22)) {
            n4 = 1;
            this.online = true;
            this.demo = false;
        }
        else {
            n4 = 0;
        }
        if (n4 == 0 && Integer.parseInt(this.serverStr) != 0) {
            if (this.formula(this.wwwStr, 15) == Integer.parseInt(this.serverStr)) {
                n4 = 1;
                this.online = true;
            }
            else {
                n4 = 0;
            }
        }
        if (n4 == 0) {
            boolean b;
            if (this.email.length() == 0 || (Integer.parseInt(this.param) != this.formula(this.email, this.email.length()) && Integer.parseInt(this.param) != this.formula(this.email, this.email.length()) - 10)) {
                b = false;
            }
            else {
                b = true;
                this.licensed = true;
                this.demo = false;
            }
            if (this.wwwStr.substring(0, 7).equals("http://")) {
                b = false;
                this.demo = true;
            }
            if (!b) {
                (this.about = new Aboutdialog(new Frame("Dialog"), this.versStr, 1, this.demo, this.online)).resize(500, 230);
                this.about.show();
            }
        }
        this.comp = new compute();
        final double n5 = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        final double computeHeight = this.comp.computeHeight(this.comp.computeDeclination(this.date, this.month + 1, this.year + 1900, n5), this.latitude, this.longitude, this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, n5));
        if (computeHeight >= 0.0) {
            this.myCan = new suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, true);
        }
        if (computeHeight < 0.0 && computeHeight >= -6.0) {
            this.myCan = new suncanvas(this.bild1, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, true);
        }
        if (computeHeight < -6.0 && computeHeight >= -12.0) {
            this.myCan = new suncanvas(this.bild2, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, true);
        }
        if (computeHeight < -12.0 && computeHeight >= -18.0) {
            this.myCan = new suncanvas(this.bild3, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, true);
        }
        if (computeHeight < -18.0) {
            this.myCan = new suncanvas(this.bild4, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, true);
        }
        this.console = false;
    }
    
    public int MonthInteger(final String s) {
        int n = 100;
        for (int i = 0; i < 12; ++i) {
            if (s == this.monthArray[i]) {
                n = i;
            }
        }
        return n;
    }
    
    public int daysInMonth(final int n, int n2) {
        int n3 = 31;
        n2 += 1900;
        if (n == 0 || n == 2 || n == 4 || n == 6 || n == 7 || n == 9 || n == 11) {
            n3 = 31;
        }
        if (n == 3 || n == 5 || n == 8 || n == 10) {
            n3 = 30;
        }
        if (n == 1) {
            n3 = 28;
            if (n2 % 4 == 0) {
                n3 = 29;
            }
            if (n2 % 100 == 0) {
                n3 = 28;
            }
            if (n2 % 400 == 0) {
                n3 = 29;
            }
        }
        return n3;
    }
    
    public void paint(final Graphics graphics) {
        this.comp = new compute();
        final double n = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        final double computeHeight = this.comp.computeHeight(this.comp.computeDeclination(this.date, this.month + 1, this.year + 1900, n), this.latitude, this.longitude, this.comp.computeGHA(this.date, this.month + 1, this.year + 1900, n));
        if (computeHeight >= 0.0) {
            this.myCan = new suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console);
        }
        if (computeHeight < 0.0 && computeHeight >= -6.0) {
            this.myCan = new suncanvas(this.bild1, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console);
        }
        if (computeHeight < -6.0 && computeHeight >= -12.0) {
            this.myCan = new suncanvas(this.bild2, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console);
        }
        if (computeHeight < -12.0 && computeHeight >= -18.0) {
            this.myCan = new suncanvas(this.bild3, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console);
        }
        if (computeHeight < -18.0) {
            this.myCan = new suncanvas(this.bild4, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.console);
        }
        graphics.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        if (this.online) {
            graphics.drawString("online", 400, 475);
        }
        graphics.setFont(new Font("Helvetica", 0, 10));
        graphics.setColor(Color.red);
        this.myCan.update(graphics);
        if (this.demo) {
            this.userString = "demo running";
        }
        else {
            this.userString = this.email;
        }
        if (this.online) {
            this.userString = "running online";
        }
        graphics.drawString(this.userString, 400, 460);
        if (this.demo) {
            final Font font = graphics.getFont();
            graphics.setFont(new Font("Chicago", 0, 18));
            graphics.setColor(Color.red);
            graphics.drawString("DEMO, other locations disabled.", 140, 150);
            graphics.setFont(font);
        }
    }
    
    public boolean action(final Event event, final Object o) {
        final double latitude = this.latitude;
        final double longitude = this.longitude;
        if (event.target instanceof Button) {
            if (event.target == this.aboutButton) {
                this.parentFrame = new Frame("Dialog");
                final Aboutdialog aboutdialog = new Aboutdialog(this.parentFrame, this.versStr, 0, this.demo, this.online);
                aboutdialog.resize(500, 230);
                aboutdialog.show();
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
                this.myCan = new suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, true);
            }
        }
        if (event.target instanceof Checkbox && event.target == this.box) {
            if (this.box.getState()) {
                this.console = true;
            }
            else {
                this.console = false;
            }
            this.repaint();
            return true;
        }
        if (event.target == this.fieldLatDeg) {
            if (this.demo) {
                return true;
            }
            this.str = this.fieldLatDeg.getText();
            for (int i = 0; i < this.str.length(); ++i) {
                final char char1 = this.str.charAt(i);
                if (char1 != '0' && char1 != '1' && char1 != '2' && char1 != '3' && char1 != '4' && char1 != '5' && char1 != '6' && char1 != '7' && char1 != '8' && char1 != '9' && char1 != '.') {
                    this.fieldLatDeg.setText(String.valueOf(Math.abs(latitude)));
                    return true;
                }
            }
            if (!this.str.equals(this.latStr) || this.str.length() == 0) {
                this.locString = "User Input";
                this.locChoice.select(this.locString);
                this.usrLat = Double.valueOf(this.str);
            }
            this.latStr = this.str;
            try {
                this.latitude = Double.valueOf(this.latStr);
                if (this.nsChoice.getSelectedItem().equals("S")) {
                    this.latitude = -Math.abs(this.latitude);
                    this.usrLat = this.latitude;
                }
                if (this.latitude == (int)this.latitude) {
                    this.str = String.valueOf(Math.abs((int)this.latitude)) + ".00";
                }
                this.fieldLatDeg.setText(this.str);
                if (this.latitude > 90.0) {
                    this.fieldLatDeg.setText(String.valueOf(latitude));
                    this.latitude = latitude;
                }
                else {
                    if (!this.str.equals(this.latStr) || this.str.length() == 0) {
                        this.latStr = this.str;
                        this.locString = "User Input";
                        this.locChoice.select(this.locString);
                    }
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                    this.myCan = new suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, true);
                }
            }
            catch (NumberFormatException ex) {
                this.latitude = latitude;
                this.fieldLatDeg.setText(String.valueOf(latitude));
                System.out.println("NumberFormatException on latitude");
            }
        }
        if (event.target == this.fieldLongDeg) {
            if (this.demo) {
                return true;
            }
            this.str = this.fieldLongDeg.getText();
            for (int j = 0; j < this.str.length(); ++j) {
                final char char2 = this.str.charAt(j);
                if (char2 != '0' && char2 != '1' && char2 != '2' && char2 != '3' && char2 != '4' && char2 != '5' && char2 != '6' && char2 != '7' && char2 != '8' && char2 != '9' && char2 != '.') {
                    this.fieldLongDeg.setText(String.valueOf(Math.abs(longitude)));
                    return true;
                }
            }
            if (!this.str.equals(this.longStr) || this.str.length() == 0) {
                this.locString = "User Input";
                this.locChoice.select(this.locString);
                this.usrLong = Double.valueOf(this.str);
            }
            this.longStr = this.str;
            try {
                this.longitude = Double.valueOf(this.longStr);
                this.fieldLongDeg.setText(this.str);
                if (this.longitude > 180.0) {
                    this.fieldLongDeg.setText(String.valueOf(longitude));
                    this.longitude = longitude;
                }
                else {
                    if (!this.str.equals(this.longStr) || this.str.length() == 0) {
                        this.longStr = this.str;
                        this.locString = "User Input";
                        this.locChoice.select(this.locString);
                    }
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                    this.myCan = new suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, true);
                }
            }
            catch (NumberFormatException ex2) {
                this.longitude = longitude;
                this.fieldLongDeg.setText(String.valueOf(longitude));
                System.out.println("NumberFormatException on longitude");
            }
        }
        if (event.target == this.locChoice) {
            if (this.demo) {
                return true;
            }
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
                this.ewChoice.select("W");
            }
            else {
                this.ewChoice.select("E");
            }
            this.myCan = new suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, true);
        }
        if (event.target == this.ewChoice) {
            if (this.demo) {
                return true;
            }
            if (!this.ewChoice.getSelectedItem().equals(this.ewStr) || !this.fieldLongDeg.getText().equals(this.longStr)) {
                this.locChoice.select("User Input");
                this.locString = "User Input";
            }
            this.ewStr = this.ewChoice.getSelectedItem();
            if (this.longStr.length() > 0) {
                this.longitude = Double.valueOf(this.longStr);
            }
            if (this.ewChoice.getSelectedItem().equals("W")) {
                this.ewStr = "W";
                this.longitude = -this.longitude;
            }
            this.usrLat = this.latitude;
            this.usrLong = this.longitude;
            this.myCan = new suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, true);
        }
        if (event.target == this.nsChoice) {
            if (this.demo) {
                return true;
            }
            if (!this.nsChoice.getSelectedItem().equals(this.nsStr) || !this.fieldLatDeg.getText().equals(this.latStr)) {
                this.locChoice.select("User Input");
                this.locString = "User Input";
            }
            this.nsStr = this.nsChoice.getSelectedItem();
            if (this.latStr.length() > 0) {
                this.latitude = Double.valueOf(this.latStr);
            }
            if (this.nsChoice.getSelectedItem().equals("S")) {
                this.nsStr = "S";
                this.latitude = -this.latitude;
            }
            this.usrLat = this.latitude;
            this.usrLong = this.longitude;
            this.myCan = new suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, true);
        }
        if (event.target == this.timeChoice) {
            this.timeString = this.timeChoice.getSelectedItem();
            this.locOffset = this.loc.getTimeZone(this.timeString);
            this.timeChoice.select(this.timeString);
            this.myCan = new suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, true);
        }
        if (event.target == this.hoursChoice) {
            this.hoursStr = this.hoursChoice.getSelectedItem();
            this.hours = Integer.parseInt(this.hoursStr);
            this.hoursChoice.select(this.hoursStr);
            this.dat.setHours(this.hours);
            this.dat.setSeconds(0);
            this.myCan = new suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, true);
        }
        if (event.target == this.minChoice) {
            this.minStr = this.minChoice.getSelectedItem();
            this.minutes = Integer.parseInt(this.minStr);
            this.minChoice.select(this.minStr);
            this.dat.setMinutes(this.minutes);
            this.dat.setSeconds(0);
            this.myCan = new suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, true);
        }
        if (event.target == this.dateChoice) {
            this.dateStr = this.dateChoice.getSelectedItem();
            this.date = Integer.parseInt(this.dateStr);
            this.dat.setDate(this.date);
            this.dat.setSeconds(0);
            final int daysInMonth = this.daysInMonth(this.month, this.year);
            if (this.date > daysInMonth) {
                this.date = daysInMonth;
                this.dat.setDate(this.date);
                this.dateChoice.select(this.date - 1);
            }
            this.myCan = new suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, true);
        }
        if (event.target == this.monthChoice) {
            this.monthStr = this.monthChoice.getSelectedItem();
            this.monthChoice.select(this.monthStr);
            this.month = this.MonthInteger(this.monthStr);
            this.dat.setMonth(this.month);
            this.dat.setSeconds(0);
            final int daysInMonth2 = this.daysInMonth(this.month, this.year);
            if (this.date > daysInMonth2) {
                this.date = daysInMonth2;
                this.dat.setDate(this.date);
                this.dateChoice.select(this.date - 1);
            }
            this.myCan = new suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, true);
        }
        if (event.target == this.yearChoice) {
            this.yearStr = this.yearChoice.getSelectedItem();
            this.year = Integer.parseInt(this.yearStr);
            this.yearChoice.select(this.yearStr);
            this.year -= 1900;
            this.dat.setYear(this.year);
            final int daysInMonth3 = this.daysInMonth(this.month, this.year);
            if (this.date > daysInMonth3) {
                this.date = daysInMonth3;
                this.dat.setDate(this.date);
                this.dateChoice.select(this.date - 1);
            }
            this.locOffset = -this.dat.getTimezoneOffset() / 60;
            this.myCan = new suncanvas(this.bild, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, true);
        }
        this.repaint();
        return true;
    }
    
    public sunview333() {
        this.versStr = "  v. 3.33";
        this.dayArray = new String[7];
        this.monthArray = new String[12];
        this.online = false;
        this.licensed = false;
        this.console = false;
        this.demo = true;
        this.usrLat = 0.0;
        this.usrLong = 0.0;
    }
}
