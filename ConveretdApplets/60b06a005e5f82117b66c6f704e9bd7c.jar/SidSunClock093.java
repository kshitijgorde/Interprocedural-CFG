import java.awt.Event;
import java.awt.Graphics;
import java.awt.Dialog;
import java.net.URL;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Button;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SidSunClock093 extends Applet implements Runnable
{
    String versStr;
    Thread myThread;
    Date dat;
    int year;
    int month;
    int date;
    int hours;
    int minutes;
    int seconds;
    public double latitude;
    public double longitude;
    public int locOffset;
    public int offset;
    int browserOffset;
    public Suncanvas myCan;
    public Seloc loc;
    public Seloc time;
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
    Choice yearChoice;
    String hoursStr;
    String minStr;
    String dateStr;
    String monthStr;
    String yearStr;
    String ewStr;
    String nsStr;
    String latStr;
    String longStr;
    public String locString;
    public String timeString;
    public String email;
    public String param;
    public String wwwStr;
    String[] monthArray;
    String[] dayArray;
    String str;
    String dayStr;
    String hourStr;
    String dayNameStr;
    public Label L11;
    public Label L12;
    public Label L13;
    public Label L14;
    public Label L15;
    public Label L16;
    public Label L17;
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
    int clickLocOffset;
    boolean isSummer;
    int dst;
    boolean online;
    Button aboutButton;
    public Frame parentFrame;
    boolean usrSummer;
    double UT;
    Checkbox runBox;
    boolean running;
    double JD;
    String localDateTimeStr;
    boolean message;
    String sunStr;
    int sun;
    int oben;
    int currentDateTimeDiff;
    boolean currentDateTime;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 9745;
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
        this.sunStr = this.getParameter("sun");
        this.sun = Integer.parseInt(this.sunStr);
        if (this.sun < 2) {
            this.oben = 265;
        }
        else {
            this.oben = 100;
        }
        final URL url = this.getCodeBase();
        this.str = url.toString();
        this.str = String.valueOf(this.str) + "01234567890123456789012345";
        this.wwwStr = this.str.substring(0, 27);
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
        this.timeString = this.locOffset + " h";
        if (this.locOffset > 0) {
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
        (this.L17 = new Label()).setText("Run");
        gbl.setConstraints(this.L17, gbc);
        this.add(this.L17);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 20, 0, 0);
        this.time = new Seloc();
        this.timeChoice = new Choice();
        this.time.timeMenu(this.timeChoice);
        gbl.setConstraints(this.timeChoice, gbc);
        this.add(this.timeChoice);
        this.timeChoice.select(this.timeString);
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
        this.dst = (int)this.loc.getLatLong(this.locString, 4);
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
        gbl.setConstraints(this.runBox = new Checkbox(""), gbc);
        this.runBox.setState(this.running);
        this.add(this.runBox);
        gbc.gridy = 2;
        (this.L17 = new Label()).setText("Local Time (h:m)");
        gbc.insets = new Insets(10, 30, 0, 0);
        gbl.setConstraints(this.L17, gbc);
        this.add(this.L17);
        gbc.insets = new Insets(7, 0, 0, 0);
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
        for (int l = 10; l < 60; ++l) {
            this.minStr = String.valueOf(l);
            this.minChoice.addItem(this.minStr);
        }
        this.minChoice.select(this.minutes);
        this.minStr = this.minChoice.getSelectedItem();
        gbl.setConstraints(this.minChoice, gbc);
        this.add(this.minChoice);
        (this.L23 = new Label()).setText("Date");
        gbc.insets = new Insets(10, 0, 0, 0);
        gbl.setConstraints(this.L23, gbc);
        this.add(this.L23);
        gbc.insets = new Insets(7, 0, 0, 0);
        this.yearChoice = new Choice();
        for (int m = 0; m < 61; ++m) {
            this.yearStr = String.valueOf(this.year + 1900 - 30 + m);
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
        this.dateStr = this.dateChoice.getSelectedItem();
        gbl.setConstraints(this.dateChoice, gbc);
        this.add(this.dateChoice);
        (this.button = new Button()).setLabel("Now");
        gbc.insets = new Insets(5, 0, 0, 10);
        gbl.setConstraints(this.button, gbc);
        this.add(this.button);
        gbc.gridy = 3;
        gbc.weighty = 200.0;
        gbl.setConstraints(this.myCan = new Suncanvas(this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.sun), gbc);
        this.add(this.myCan);
        gbc.gridy = 4;
        gbc.weighty = 1.0;
        final GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.gridx = 7;
        gbc2.weightx = 0.0;
        gbc2.gridy = 12;
        gbc2.weighty = 6.0;
        this.aboutButton = new Button();
        gbc2.insets = new Insets(0, 0, 10, 27);
        gbl.setConstraints(this.aboutButton, gbc2);
        this.aboutButton.setLabel("About");
        this.add(this.aboutButton);
        boolean ok = false;
        this.demo = true;
        final double STD = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        this.UT = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        this.JD = this.Jul_Date(this.date, this.month + 1, this.year + 1900, this.UT);
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        if (this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(this.wwwStr, 22) == this.formula("http://www.geoastro.de", 22) || this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 21) == this.formula("http://www.j-giesen.de", 21)) {
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
                final Dialog Aboutdialog = new Aboutdialog(this, this.versStr, this.online);
                Aboutdialog.resize(300, 230);
                Aboutdialog.show();
            }
        }
        this.repaint();
    }
    
    public void start() {
        (this.myThread = new Thread(this)).start();
    }
    
    public void stop() {
        this.myThread.stop();
    }
    
    public void run() {
        while (this.running) {
            try {
                this.JD += 1.1574074074074073E-5;
                this.repaint();
            }
            catch (NumberFormatException ex) {}
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public double Jul_Date(final int date, int month, int year, final double UT) {
        if (month <= 2) {
            month += 12;
            --year;
        }
        final int A = (int)(year / 100.0);
        final int B = 2 - A + (int)(A / 4.0);
        return (int)(365.25 * (year + 4716)) + (int)(30.6001 * (month + 1)) + date + B - 1524.5 + UT / 24.0;
    }
    
    public String monthString(final int m) {
        if (m > 11 || m < 0) {
            return "?";
        }
        return this.monthArray[m];
    }
    
    public String dayString(final double jd) {
        long num = (long)(jd + 0.5);
        num -= num / 7L * 7L;
        final int n = (int)num;
        if (n < 0 || n > 6) {
            return "?";
        }
        return this.dayArray[(int)num];
    }
    
    public void caldat(double JD) {
        JD += this.locOffset / 24.0;
        final double JD2 = (int)(JD + 0.5);
        final int B = (int)((JD2 - 1867216.25) / 36524.25);
        final double C = JD2 + B - (int)(B / 4.0) + 1525.0;
        final int D = (int)((C - 122.1) / 365.25);
        final double E = 365.0 * D + (int)(D / 4.0);
        final int F = (int)((C - E) / 30.6001);
        final int day = (int)(C - E + 0.5) - (int)(30.6001 * F);
        this.dayStr = String.valueOf(day);
        if (day < 10) {
            this.dayStr = " " + this.dayStr;
        }
        final int Month = F - 1 - 12 * (int)(F / 14.0);
        final int Year = D - 4715 - (int)((7 + Month) / 10.0);
        final double hour = 24.0 * (JD + 0.5 - JD2);
        final double diff = Math.abs(hour) - (int)Math.abs(hour);
        int sec = (int)Math.round((diff * 60.0 - (int)(diff * 60.0)) * 60.0);
        int Hour = (int)hour;
        int min = (int)(diff * 60.0);
        if (sec == 60) {
            sec = 0;
            ++min;
        }
        if (min == 60) {
            min = 0;
            ++Hour;
        }
        String str;
        if (min > 9) {
            str = ":";
        }
        else {
            str = ":0";
        }
        this.hourStr = String.valueOf(Hour) + str + min;
        if (Hour < 10) {
            this.hourStr = "0" + this.hourStr;
        }
        if (sec < 10) {
            this.hourStr = String.valueOf(this.hourStr) + ":0" + sec;
        }
        else {
            this.hourStr = String.valueOf(this.hourStr) + ":" + sec;
        }
        this.monthStr = this.monthString(Month - 1);
        this.dayNameStr = this.dayString(JD);
        if (this.running) {
            this.date = day;
            this.month = Month - 1;
            this.year = Year;
            this.hours = Hour;
            this.hours = Hour + this.locOffset;
            this.minutes = min;
            this.seconds = sec;
            this.yearStr = String.valueOf(Year);
        }
        else {
            this.yearStr = String.valueOf(this.year);
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
    
    public void TimeZoneMenu(final int myOffset) {
        String str = "";
        if (myOffset > 0) {
            str = "UT  +" + myOffset + " h";
        }
        if (myOffset < 0) {
            str = "UT  " + myOffset + " h";
        }
        if (myOffset == 0) {
            str = "UT  +/- 0 h";
        }
        this.timeChoice.select(str);
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
    
    public String isDST(final int d, final int m, final int y, final int mydst) {
        if (mydst == 0) {
            return "W";
        }
        final Date curDate = new Date();
        if (curDate.getMinutes() == this.minutes && curDate.getMonth() == this.month && curDate.getDate() == this.date && curDate.getYear() == this.year - 1900) {
            this.currentDateTime = true;
            this.currentDateTimeDiff = this.seconds - curDate.getSeconds();
        }
        else {
            this.currentDateTime = false;
        }
        curDate.setDate(d);
        curDate.setMonth(m);
        curDate.setYear(y - 1900);
        final int curOffset = -curDate.getTimezoneOffset() / 60;
        final Date theDate6 = new Date();
        theDate6.setMonth(6);
        theDate6.setDate(1);
        theDate6.setYear(y - 1900);
        final int theOffset6 = -theDate6.getTimezoneOffset() / 60;
        final Date theDate7 = new Date();
        theDate7.setMonth(0);
        theDate7.setDate(1);
        theDate7.setYear(y - 1900);
        final int theOffset7 = -theDate7.getTimezoneOffset() / 60;
        if (theOffset7 < theOffset6) {
            if (this.latitude >= 0.0) {
                if (curOffset == theOffset6) {
                    return "S";
                }
                return "W";
            }
            else {
                if (curOffset == theOffset6) {
                    return "W";
                }
                return "S";
            }
        }
        else if (theOffset7 > theOffset6) {
            if (this.latitude >= 0.0) {
                if (curOffset == theOffset7) {
                    return "W";
                }
                return "S";
            }
            else {
                if (curOffset == theOffset7) {
                    return "S";
                }
                return "W";
            }
        }
        else {
            if (theOffset7 != theOffset6) {
                return "?";
            }
            if (this.latitude >= 0.0) {
                if (m < 3 || m > 8) {
                    return "W";
                }
                return "S";
            }
            else {
                if (m < 3 || m > 8) {
                    return "S";
                }
                return "W";
            }
        }
    }
    
    public void paint(final Graphics g) {
        if (this.message) {
            g.setColor(Color.red);
            g.setFont(new Font("Helvetica", 1, 10));
            if (this.sun < 2) {
                g.drawString("Stop to change settings", 400, 200);
            }
            else {
                g.drawString("Stop to change settings", 400, 250);
            }
            g.setColor(Color.black);
        }
        this.UT = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        if (!this.running) {
            this.JD = this.Jul_Date(this.date, this.month + 1, this.year, this.UT);
        }
        this.caldat(this.JD);
        if (this.year < 1000) {
            this.localDateTimeStr = this.year + 1900 + " " + this.monthStr + " " + this.dayStr + " " + this.dayNameStr + " " + this.hourStr;
        }
        else {
            this.localDateTimeStr = this.year + " " + this.monthStr + " " + this.dayStr + " " + this.dayNameStr + " " + this.hourStr;
        }
        g.setFont(new Font("Courier", 0, 12));
        if (this.sun < 2) {
            g.drawString(this.hourStr, 80, 400);
            g.drawString("Zone Time ", 85, 415);
        }
        if (this.sun > 0) {
            g.drawString(this.localDateTimeStr, 400, this.oben + 140 + 10);
        }
        else {
            g.drawString(this.localDateTimeStr, 400, 210);
        }
        g.setFont(new Font("Helvetica", 0, 10));
        this.caldat(this.JD - this.locOffset / 24.0);
        String gmtDateTimeStr;
        if (this.year < 1000) {
            gmtDateTimeStr = this.year + 1900 + "  " + this.dayNameStr + "  " + this.dayStr + "  " + this.monthStr + "  " + this.hourStr;
        }
        else {
            gmtDateTimeStr = this.year + "  " + this.dayNameStr + "  " + this.dayStr + "  " + this.monthStr + "  " + this.hourStr;
        }
        if (this.sun < 2) {
            g.setFont(new Font("Helvetica", 0, 10));
            g.drawString(gmtDateTimeStr, 40, 245);
            g.setFont(new Font("Courier", 0, 12));
            g.drawString("UT " + this.hourStr, 70, 230);
            g.setFont(new Font("Helvetica", 0, 10));
            if (this.running && this.currentDateTime) {
                if (this.currentDateTimeDiff >= 0) {
                    g.drawString("Applet Time = System Clock +" + this.currentDateTimeDiff + " s", 400, 100);
                }
                else {
                    g.drawString("System Clock " + this.currentDateTimeDiff + " s", 400, 100);
                }
            }
            g.setFont(new Font("Helvetica", 0, 12));
            g.drawString("Julian Day", 400, 120);
            g.setFont(new Font("Courier", 0, 12));
            g.drawString(String.valueOf(Math.round(100000.0 * this.JD) / 100000.0), 400, 140);
            final Date systemDate = new Date();
            final int systemOffset = -systemDate.getTimezoneOffset() / 60;
            String systemClock;
            if (systemOffset >= 0) {
                systemClock = "+ " + systemOffset + " h";
            }
            else {
                systemClock = "- " + Math.abs(systemOffset) + " h";
            }
            g.setFont(new Font("Helvetica", 0, 12));
            g.drawString("System Clock: UT " + systemClock, 400, 160);
            final String checkDST = this.isDST(this.date, this.month, this.year, this.dst);
            g.setFont(new Font("Helvetica", 0, 11));
            if (this.dst == 0) {
                g.drawString("No DST", 400, 180);
            }
            else {
                if (checkDST.equals("S")) {
                    g.drawString("DST may be active", 400, 180);
                }
                if (checkDST.equals("W")) {
                    g.drawString("DST not active", 400, 180);
                }
                if (checkDST.equals("?")) {
                    g.drawString("DST ?", 400, 180);
                }
            }
        }
        this.myCan = new Suncanvas(this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.sun);
        g.setColor(Color.red);
        g.setFont(new Font("Helvetica", 0, 10));
        g.drawString("Location:  " + this.locString, 400, this.oben);
        g.setColor(Color.black);
        this.str = String.valueOf(Math.abs(Math.round(100.0 * this.latitude) / 100.0));
        if (this.latitude >= 0.0) {
            this.str = String.valueOf(this.str) + "° N";
        }
        else {
            this.str = String.valueOf(this.str) + "° S";
        }
        g.drawString("Latitude", 400, this.oben + 20);
        g.drawString(this.str, 490, this.oben + 20);
        this.str = String.valueOf(Math.abs(Math.round(100.0 * this.longitude) / 100.0));
        if (this.longitude >= 0.0) {
            this.str = String.valueOf(this.str) + "° E";
        }
        else {
            this.str = String.valueOf(this.str) + "° W";
        }
        g.drawString("Longitude ", 400, this.oben + 35);
        g.drawString(this.str, 490, this.oben + 35);
        g.setColor(Color.black);
        this.myCan.update(g);
        g.setColor(Color.red);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        if (this.demo) {
            g.setFont(new Font("Chicago", 0, 110));
            g.setColor(Color.red);
            g.drawString("D", 50, 100);
            g.drawString("E", 200, 220);
            g.drawString("M", 350, 340);
            g.drawString("O", 500, 460);
        }
        g.setColor(Color.black);
        g.setFont(new Font("Helvetica", 0, 9));
        if (this.sun == 1) {
            g.drawString("Sidereal & Solar Clock" + this.versStr, 400, this.size().height - 30);
        }
        else {
            g.drawString("Sidereal Clock" + this.versStr, 400, this.size().height - 30);
        }
        g.drawString("© 2003-2010 J. Giesen", 400, this.size().height - 18);
    }
    
    public boolean keyDown(final Event event, final int code) {
        int n = 0;
        final int cmd = 0;
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
                    this.dat.setMonth(this.month);
                    this.monthChoice.select(this.month);
                }
            }
            this.dateChoice.select(this.date - 1);
            this.dat.setSeconds(0);
            this.dat.setDate(this.date);
            this.yearStr = String.valueOf(this.year + 1900);
            this.yearChoice.select(this.yearStr);
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
            this.year += 1900;
            this.dat.setSeconds(0);
        }
        this.repaint();
        return true;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        final double oldLat = this.latitude;
        final double oldLong = this.longitude;
        if (this.running && event.target != this.button && event.target != this.aboutButton && event.target != this.button) {
            this.message = true;
        }
        else {
            this.message = false;
        }
        if (event.target instanceof Checkbox) {
            if (event.target == this.runBox) {
                if (this.runBox.getState() && this.running) {
                    this.message = true;
                }
                else {
                    this.message = false;
                }
                this.running ^= true;
                this.runBox.setState(this.running);
                if (this.running) {
                    this.start();
                }
                else {
                    this.stop();
                }
                this.repaint();
                return true;
            }
            if (event.target == this.summerBox) {
                if (this.running) {
                    this.summerBox.setState(this.isSummer);
                    return true;
                }
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
                this.myCan = new Suncanvas(this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.sun);
                this.repaint();
                return true;
            }
        }
        if (event.target instanceof Button) {
            if (event.target == this.aboutButton) {
                final Dialog Aboutdialog = new Aboutdialog(this, this.versStr, this.demo);
                Aboutdialog.resize(300, 230);
                Aboutdialog.show();
            }
            if (event.target == this.button) {
                final Date nowDate = new Date();
                this.hours = nowDate.getHours();
                this.minutes = nowDate.getMinutes();
                this.minChoice.select(this.minutes);
                this.date = nowDate.getDate();
                this.month = nowDate.getMonth();
                this.year = nowDate.getYear();
                this.year += 1900;
                this.seconds = nowDate.getSeconds();
                this.UT = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
                if (this.locString != "User Input") {
                    this.offset = (int)this.loc.getLatLong(this.locString, 3);
                }
                else {
                    this.getClickOffset();
                    this.offset = this.usrOffset;
                }
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
                this.hoursChoice.select(this.hours);
                this.dateChoice.select(this.date - 1);
                this.monthChoice.select(this.month);
                this.yearStr = String.valueOf(this.year + 1900);
                this.yearChoice.select(this.yearStr);
                this.dateStr = this.dateChoice.getSelectedItem();
                this.hoursStr = String.valueOf(this.hours);
                if (this.hours < 10) {
                    this.hoursStr = "0" + this.hoursStr;
                }
                this.myCan = new Suncanvas(this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.sun);
                this.repaint();
                return true;
            }
        }
        if (event.target instanceof TextField) {
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
                    this.latStr = String.valueOf(Math.abs(Math.round(100.0 * this.latitude) / 100.0));
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                    this.myCan = new Suncanvas(this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.sun);
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
                this.getClickOffset();
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
                this.locOffset = this.loc.getTimeZone(this.str);
                this.usrOffset = this.locOffset;
                this.myCan = new Suncanvas(this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.sun);
                this.repaint();
                return true;
            }
        }
        if (event.target instanceof Choice) {
            if (event.target == this.locChoice) {
                if (this.running) {
                    this.locChoice.select(this.locString);
                    return true;
                }
                this.dst = 1;
                if (this.locChoice.getSelectedItem().lastIndexOf("--") != -1) {
                    this.locChoice.select(this.locString);
                    return true;
                }
                this.locString = this.locChoice.getSelectedItem();
                if (this.locChoice.getSelectedItem().equals(this.homeString)) {
                    this.latitude = this.homeLat;
                    this.longitude = this.homeLong;
                    this.offset = Integer.parseInt(this.timezoneStr);
                    this.dst = Integer.parseInt(this.dstStr);
                    if (this.isSummer && this.dst == 1) {
                        ++this.offset;
                    }
                    this.locOffset = this.offset;
                    this.TimeZoneMenu(this.offset);
                }
                else {
                    this.latitude = this.loc.getLatLong(this.locString, 1);
                    this.longitude = this.loc.getLatLong(this.locString, 2);
                    this.offset = (int)this.loc.getLatLong(this.locString, 3);
                    this.dst = (int)this.loc.getLatLong(this.locString, 4);
                    if (this.isSummer && this.dst == 1) {
                        ++this.offset;
                    }
                    this.locOffset = this.offset;
                    this.TimeZoneMenu(this.offset);
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
                this.myCan = new Suncanvas(this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.sun);
                this.repaint();
                return true;
            }
            else if (event.target == this.ewChoice) {
                if (this.running) {
                    this.ewChoice.select(this.ewStr);
                    return true;
                }
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
                this.TimeZoneMenu(this.offset);
                this.locOffset = this.offset;
                this.myCan = new Suncanvas(this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.sun);
                this.repaint();
                return true;
            }
            else if (event.target == this.nsChoice) {
                if (this.running) {
                    this.nsChoice.select(this.nsStr);
                    return true;
                }
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
                this.locOffset = this.offset;
                this.myCan = new Suncanvas(this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.sun);
                this.repaint();
                return true;
            }
            else if (event.target == this.timeChoice) {
                if (this.running) {
                    this.timeChoice.select(this.timeString);
                    return true;
                }
                this.timeString = this.timeChoice.getSelectedItem();
                this.locOffset = this.loc.getTimeZone(this.timeString);
                final int gmtHours = this.hours - this.locOffset;
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
                this.myCan = new Suncanvas(this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.sun);
                this.repaint();
                return true;
            }
            else if (event.target == this.hoursChoice) {
                if (this.running) {
                    this.hoursChoice.select(this.hoursStr);
                    return true;
                }
                this.hoursStr = this.hoursChoice.getSelectedItem();
                this.hours = Integer.parseInt(this.hoursStr);
                this.hoursChoice.select(this.hoursStr);
                this.dat.setHours(this.hours);
                this.dat.setSeconds(0);
                this.seconds = 0;
                this.dateStr = this.dateChoice.getSelectedItem();
                this.date = Integer.parseInt(this.dateStr);
                this.minStr = this.minChoice.getSelectedItem();
                this.minutes = Integer.parseInt(this.minStr);
                this.monthStr = this.monthChoice.getSelectedItem();
                this.month = this.MonthInteger(this.monthStr);
                if (this.running) {
                    this.myCan = new Suncanvas(this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.sun);
                }
                else {
                    this.myCan = new Suncanvas(this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.sun);
                }
                this.repaint();
                return true;
            }
            else if (event.target == this.minChoice) {
                if (this.running) {
                    this.minChoice.select(this.minStr);
                    return true;
                }
                this.minStr = this.minChoice.getSelectedItem();
                this.minutes = Integer.parseInt(this.minStr);
                this.minChoice.select(this.minStr);
                this.dat.setMinutes(this.minutes);
                this.dat.setSeconds(0);
                this.seconds = 0;
                this.hoursStr = this.hoursChoice.getSelectedItem();
                this.hours = Integer.parseInt(this.hoursStr);
                this.dateStr = this.dateChoice.getSelectedItem();
                this.date = Integer.parseInt(this.dateStr);
                this.monthStr = this.monthChoice.getSelectedItem();
                this.month = this.MonthInteger(this.monthStr);
                this.myCan = new Suncanvas(this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.sun);
                this.repaint();
                return true;
            }
            else if (event.target == this.dateChoice) {
                if (this.running) {
                    this.dateChoice.select(this.dateStr);
                    return true;
                }
                this.dateStr = this.dateChoice.getSelectedItem();
                this.date = Integer.parseInt(this.dateStr);
                this.dat.setDate(this.date);
                final int n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = n;
                    this.dat.setDate(this.date);
                    this.dateChoice.select(this.date - 1);
                }
                this.minStr = this.minChoice.getSelectedItem();
                this.minutes = Integer.parseInt(this.minStr);
                this.hoursStr = this.hoursChoice.getSelectedItem();
                this.hours = Integer.parseInt(this.hoursStr);
                this.myCan = new Suncanvas(this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.sun);
                this.repaint();
                return true;
            }
            else if (event.target == this.monthChoice) {
                if (this.running) {
                    this.monthChoice.select(this.monthStr);
                    return true;
                }
                this.monthStr = this.monthChoice.getSelectedItem();
                this.monthChoice.select(this.monthStr);
                this.month = this.MonthInteger(this.monthStr);
                final int n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = n;
                    this.dat.setDate(this.date);
                    this.dateChoice.select(this.date - 1);
                }
                this.dat.setMonth(this.month);
                if (this.locString != "User Input") {
                    this.offset = (int)this.loc.getLatLong(this.locString, 3);
                }
                else {
                    this.offset = this.clickLocOffset;
                }
                this.dateStr = this.dateChoice.getSelectedItem();
                this.date = Integer.parseInt(this.dateStr);
                this.minStr = this.minChoice.getSelectedItem();
                this.minutes = Integer.parseInt(this.minStr);
                this.hoursStr = this.hoursChoice.getSelectedItem();
                this.hours = Integer.parseInt(this.hoursStr);
                this.myCan = new Suncanvas(this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.sun);
                this.repaint();
                return true;
            }
            else if (event.target == this.yearChoice) {
                if (this.running) {
                    this.yearChoice.select(this.yearStr);
                    return true;
                }
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
                this.year += 1900;
                this.dateStr = this.dateChoice.getSelectedItem();
                this.date = Integer.parseInt(this.dateStr);
                this.minStr = this.minChoice.getSelectedItem();
                this.minutes = Integer.parseInt(this.minStr);
                this.hoursStr = this.hoursChoice.getSelectedItem();
                this.hours = Integer.parseInt(this.hoursStr);
                this.monthStr = this.monthChoice.getSelectedItem();
                this.month = this.MonthInteger(this.monthStr);
                this.myCan = new Suncanvas(this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.sun);
                this.repaint();
                return true;
            }
        }
        this.repaint();
        return true;
    }
    
    public SidSunClock093() {
        this.versStr = "  0.93";
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.dayArray = new String[] { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
        this.demo = true;
        this.usrLat = 0.0;
        this.usrLong = 0.0;
        this.usrOffset = 0;
        this.clickLocOffset = 0;
        this.dst = 1;
        this.running = true;
        this.message = false;
    }
}
