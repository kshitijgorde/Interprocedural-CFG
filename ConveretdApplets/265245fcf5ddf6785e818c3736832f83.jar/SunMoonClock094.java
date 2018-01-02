import java.awt.Event;
import java.awt.Graphics;
import java.awt.Dialog;
import java.net.URL;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Checkbox;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Button;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SunMoonClock094 extends Applet implements Runnable
{
    String versStr;
    Thread myThread;
    final int links = 590;
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
    public Suncanvas myCan;
    public Seloc loc;
    public Seloc time;
    public compute comp;
    Button button;
    public Choice locChoice;
    public Choice timeChoice;
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
    String str;
    String dayStr;
    String hourStr;
    String dayNameStr;
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
    int dst;
    boolean online;
    boolean usrSummer;
    double UT;
    Checkbox runBox;
    boolean running;
    double JD;
    String localDateTimeStr;
    int oben;
    Choice detailChoice;
    boolean azimuthOK;
    boolean zeroDown;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 56106;
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
        System.out.println("SunMoon Clock  (c) 2007-2010 J. Giesen  -  www.GeoAstro.de");
        this.comp = new compute();
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
        this.dat = new Date();
        this.hours = this.dat.getHours();
        this.minutes = this.dat.getMinutes();
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth();
        this.year = this.dat.getYear();
        this.seconds = this.dat.getSeconds();
        this.offset = Integer.parseInt(this.timezoneStr);
        this.dst = Integer.parseInt(this.dstStr);
        this.locOffset = this.offset;
        this.timeString = this.locOffset + " h";
        if (this.locOffset > 0) {
            this.timeString = "+" + this.timeString;
        }
        this.timeString = "UT  " + this.timeString;
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        this.setFont(new Font("Helvetica", 0, 10));
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(2, 20, 0, 0);
        final Label L11 = new Label("Time Zone");
        gbl.setConstraints(L11, gbc);
        this.add(L11);
        final Label L12 = new Label("Location");
        gbc.insets = new Insets(2, 0, 0, 0);
        gbl.setConstraints(L12, gbc);
        this.add(L12);
        gbc.weightx = 10.0;
        final Label L13 = new Label("Latitude");
        gbl.setConstraints(L13, gbc);
        this.add(L13);
        final Label L14 = new Label("N / S");
        gbl.setConstraints(L14, gbc);
        this.add(L14);
        final Label L15 = new Label("Longitude");
        gbl.setConstraints(L15, gbc);
        this.add(L15);
        final Label L16 = new Label("E / W");
        gbl.setConstraints(L16, gbc);
        this.add(L16);
        Label L17 = new Label("Run");
        gbl.setConstraints(L17, gbc);
        this.add(L17);
        final Label L18 = new Label("Details");
        gbl.setConstraints(L18, gbc);
        this.add(L18);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 20, 0, 0);
        this.time = new Seloc();
        this.timeChoice = new Choice();
        this.time.timeMenu(this.timeChoice);
        gbl.setConstraints(this.timeChoice, gbc);
        this.add(this.timeChoice);
        this.timeChoice.select(this.timeString);
        gbc.insets = new Insets(0, 0, 0, 0);
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
        (this.detailChoice = new Choice()).addItem("Choose:");
        this.detailChoice.addItem("About");
        this.detailChoice.addItem("Lunar Transits");
        this.detailChoice.addItem("Rise/Set Moon");
        this.detailChoice.addItem("Solar Transits");
        this.detailChoice.addItem("Full Moon");
        this.detailChoice.addItem("Azimuth on/off");
        this.detailChoice.addItem("Turn Dial");
        gbl.setConstraints(this.detailChoice, gbc);
        this.add(this.detailChoice);
        gbc.gridy = 2;
        L17 = new Label("Local Time (h:m)");
        gbc.insets = new Insets(10, 30, 0, 0);
        gbl.setConstraints(L17, gbc);
        this.add(L17);
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
        final Label L19 = new Label("  Date");
        gbc.insets = new Insets(10, 0, 0, 0);
        gbl.setConstraints(L19, gbc);
        this.add(L19);
        gbc.insets = new Insets(7, 0, 0, 0);
        this.yearChoice = new Choice();
        for (int m = 0; m < 61; ++m) {
            this.yearStr = String.valueOf(this.year + 1900 - 30 + m);
            this.yearChoice.addItem(this.yearStr);
        }
        this.yearStr = new StringBuffer().append(this.year).append(1900).toString();
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
        this.button = new Button("Now");
        gbc.insets = new Insets(5, 0, 0, 10);
        gbl.setConstraints(this.button, gbc);
        this.add(this.button);
        gbc.gridy = 3;
        gbc.weighty = 200.0;
        gbl.setConstraints(this.myCan = new Suncanvas(this.versStr, this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.azimuthOK, this.zeroDown), gbc);
        this.add(this.myCan);
        boolean ok = false;
        this.demo = true;
        this.UT = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        this.JD = this.comp.Jul_Date(this.date, this.month + 1, this.year + 1900, this.UT);
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
    
    public String dayString(double jd) {
        final String[] dayArray = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
        jd += this.locOffset / 24.0;
        long num = (long)(jd + 0.5);
        num -= num / 7L * 7L;
        final int n = (int)num;
        if (n < 0 || n > 6) {
            return "?";
        }
        return dayArray[(int)num];
    }
    
    public void caldat(final double JD) {
        final double jd = JD + this.locOffset / 24.0;
        final double JD2 = (int)(jd + 0.5);
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
        final double hour = 24.0 * (jd + 0.5 - JD2);
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
        this.monthStr = this.monthArray[Month - 1];
        this.dayNameStr = this.dayString(JD);
        this.year = Year - 1900;
        this.date = day;
        this.month = Month - 1;
        this.hours = Hour;
        this.minutes = min;
        this.seconds = sec;
        this.yearStr = String.valueOf(Year);
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
        this.timeString = str;
        this.timeChoice.select(str);
    }
    
    public void doFullMoon() {
        final String[] dataStr = new String[16];
        final PHASE phs = new PHASE();
        int nData = 0;
        String str = "SunMoon Clock " + this.versStr + "\n" + "© 2007-2010 J. Giesen  - www.GeoAstro.de" + "\n";
        if (this.locOffset >= 0) {
            str = String.valueOf(str) + "UT +" + this.locOffset + " h";
        }
        else {
            str = String.valueOf(str) + "UT " + this.locOffset + " h";
        }
        for (int i = 0; i < 14; ++i) {
            dataStr[nData] = phs.PHASE(this.year + 1900, this.locOffset, this.demo)[i];
            final String mStr = dataStr[nData].substring(11, 14);
            if (i > 0 && mStr.equals(dataStr[nData - 1].substring(11, 14))) {
                final String[] array = dataStr;
                final int n = nData;
                array[n] = String.valueOf(array[n]) + " Blue Moon!";
            }
            final String[] array2 = dataStr;
            final int n2 = nData;
            array2[n2] = String.valueOf(array2[n2]) + "\n";
            ++nData;
        }
        dataStr[0] = String.valueOf(str) + "\n" + "\n" + dataStr[0];
        final scrollFrame SF = new scrollFrame("Full Moon " + (this.year + 1900), nData, dataStr, this.demo);
        SF.resize(300, 280);
        SF.show();
    }
    
    public boolean DemoOnline() {
        return this.demo || this.online;
    }
    
    public void paint(final Graphics g) {
        g.setFont(new Font("Courier", 0, 12));
        this.UT = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
        this.caldat(this.JD - this.locOffset / 24.0);
        g.drawString(this.localDateTimeStr = String.valueOf(this.dayNameStr) + " " + (this.year + 1900) + " " + this.monthStr + " " + this.dayStr + "  " + this.hourStr + " UT", 590, this.oben + 250);
        this.caldat(this.JD);
        g.drawString(this.localDateTimeStr = String.valueOf(this.dayNameStr) + " " + (this.year + 1900) + " " + this.monthStr + " " + this.dayStr + "  " + this.hourStr, 590, this.oben + 265);
        this.minChoice.select(this.minutes);
        this.hoursChoice.select(this.hours % 24);
        this.dateChoice.select(this.date - 1);
        this.monthChoice.select(this.month);
        this.yearChoice.select(String.valueOf(this.year + 1900));
        g.setColor(Color.red);
        g.setFont(new Font("Helvetica", 0, 10));
        g.drawString("Location:  " + this.locString + ",  " + this.timeString, 590, 90);
        this.myCan = new Suncanvas(this.versStr, this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.azimuthOK, this.zeroDown);
        g.setColor(Color.black);
        this.str = String.valueOf(Math.abs(Math.round(1000.0 * this.latitude) / 1000.0));
        if (this.latitude >= 0.0) {
            this.str = String.valueOf(this.str) + "° N";
        }
        else {
            this.str = String.valueOf(this.str) + "° S";
        }
        g.drawString("Latitude", 590, this.oben + 10);
        g.setFont(new Font("Courier", 0, 10));
        g.drawString(this.str, 680, this.oben + 10);
        this.str = String.valueOf(Math.abs(Math.round(1000.0 * this.longitude) / 1000.0));
        if (this.longitude >= 0.0) {
            this.str = String.valueOf(this.str) + "° E";
        }
        else {
            this.str = String.valueOf(this.str) + "° W";
        }
        g.setFont(new Font("Helvetica", 0, 10));
        g.drawString("Longitude ", 590, this.oben + 25);
        g.setFont(new Font("Courier", 0, 10));
        g.drawString(this.str, 680, this.oben + 25);
        g.setColor(Color.black);
        this.myCan.update(g);
        g.setColor(Color.red);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        if (this.demo) {
            g.setFont(new Font("Chicago", 0, 110));
            g.drawString("D", 50, 100);
            g.drawString("E", 220, 200);
            g.drawString("M", 390, 300);
            g.drawString("O", 560, 400);
        }
        g.setColor(Color.black);
        g.setFont(new Font("Helvetica", 0, 9));
        g.drawString("SunMoon Clock" + this.versStr, 590, this.size().height - 30);
        g.drawString("© 2007-2010 J. Giesen  -  www.GeoAstro.de", 590, this.size().height - 15);
    }
    
    public boolean keyDown(final Event event, final int code) {
        if (code != 104 && code != 100 && code != 109 && code != 72 && code != 68 && code != 77 && code != 110 && code != 78) {
            return false;
        }
        if (code == 72 || code == 104) {
            if (code == 72) {
                this.JD -= 0.041666666666666664;
            }
            else {
                this.JD += 0.041666666666666664;
            }
            this.caldat(this.JD);
            this.dat.setHours(this.hours);
            this.dat.setDate(this.date);
            this.dat.setMonth(this.month);
            this.myCan = new Suncanvas(this.versStr, this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.azimuthOK, this.zeroDown);
            this.repaint();
            return true;
        }
        if (code == 110 || code == 78) {
            if (code == 110) {
                this.JD += 6.944444444444445E-4;
            }
            else {
                this.JD -= 6.944444444444445E-4;
            }
            this.caldat(this.JD);
            this.dat.setHours(this.hours);
            this.dat.setDate(this.date);
            this.dat.setMonth(this.month);
            this.dat.setMinutes(this.minutes);
            this.myCan = new Suncanvas(this.versStr, this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.azimuthOK, this.zeroDown);
            this.repaint();
            return true;
        }
        if (code == 100 || code == 68) {
            if (code == 68) {
                --this.JD;
            }
            else {
                ++this.JD;
            }
            this.caldat(this.JD);
            this.dat.setDate(this.date);
            this.dat.setMonth(this.month);
            this.dat.setYear(this.year);
            this.JD = this.comp.Jul_Date(this.date, this.month + 1, this.year + 1900, this.UT);
            this.myCan = new Suncanvas(this.versStr, this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.azimuthOK, this.zeroDown);
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
            }
            if (this.month < 0) {
                this.month = 11;
                --this.year;
            }
            final int n = this.comp.daysInMonth(this.month, this.year);
            if (this.date > n) {
                this.date = 1;
                ++this.month;
            }
            this.caldat(this.JD = this.comp.Jul_Date(this.date, this.month + 1, this.year + 1900, this.UT));
            this.dat.setDate(this.date);
            this.dat.setMonth(this.month);
            this.dat.setYear(this.year);
            this.yearStr = String.valueOf(this.year + 1900);
            this.myCan = new Suncanvas(this.versStr, this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.azimuthOK, this.zeroDown);
            this.repaint();
            return true;
        }
        return false;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        final double oldLat = this.latitude;
        final double oldLong = this.longitude;
        if (event.target instanceof Checkbox && event.target == this.runBox) {
            this.running ^= true;
            if (this.running) {
                this.start();
            }
            else {
                this.stop();
            }
            this.repaint();
            return true;
        }
        if (event.target instanceof Button && event.target == this.button) {
            final Date nowDate = new Date();
            this.hours = nowDate.getHours();
            this.minutes = nowDate.getMinutes();
            this.minChoice.select(this.minutes);
            this.date = nowDate.getDate();
            this.month = nowDate.getMonth();
            this.year = nowDate.getYear();
            this.seconds = nowDate.getSeconds();
            this.UT = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
            this.JD = this.comp.Jul_Date(this.date, this.month + 1, this.year + 1900, this.UT);
            if (this.locString != "User Input") {
                this.offset = (int)this.loc.getLatLong(this.locString, 3);
            }
            if (this.hours >= 24) {
                this.hours -= 24;
                ++this.date;
                final int n = this.comp.daysInMonth(this.month, this.year);
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
                    this.date = this.comp.daysInMonth(this.month, this.year);
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
            this.myCan = new Suncanvas(this.versStr, this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.azimuthOK, this.zeroDown);
            this.repaint();
            return true;
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
                    this.myCan = new Suncanvas(this.versStr, this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.azimuthOK, this.zeroDown);
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
                this.myCan = new Suncanvas(this.versStr, this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.azimuthOK, this.zeroDown);
                this.repaint();
                return true;
            }
        }
        if (event.target instanceof Choice) {
            if (event.target == this.detailChoice) {
                if (this.detailChoice.getSelectedItem().equals("About")) {
                    final Dialog Aboutdialog = new Aboutdialog(this, this.versStr, this.demo);
                    Aboutdialog.resize(300, 230);
                    Aboutdialog.show();
                }
                if (this.detailChoice.getSelectedItem().equals("Lunar Transits")) {
                    final Moon moonTable = new Moon(this.versStr, this.dat, this.latitude, -this.longitude, this.locOffset, this.locString);
                    moonTable.moonTable();
                }
                if (this.detailChoice.getSelectedItem().equals("Rise/Set Moon")) {
                    final Moon moonTable = new Moon(this.versStr, this.dat, this.latitude, -this.longitude, this.locOffset, this.locString);
                    moonTable.rsTable();
                }
                if (this.detailChoice.getSelectedItem().equals("Solar Transits")) {
                    this.myCan.sunTable();
                }
                if (this.detailChoice.getSelectedItem().equals("Azimuth on/off")) {
                    this.azimuthOK ^= true;
                    this.myCan = new Suncanvas(this.versStr, this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.azimuthOK, this.zeroDown);
                    this.repaint();
                }
                if (this.detailChoice.getSelectedItem().equals("Full Moon")) {
                    this.doFullMoon();
                }
                if (this.detailChoice.getSelectedItem().equals("Turn Dial")) {
                    this.zeroDown ^= true;
                    this.myCan = new Suncanvas(this.versStr, this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.azimuthOK, this.zeroDown);
                    this.repaint();
                }
                this.detailChoice.select(0);
                return true;
            }
            if (event.target == this.locChoice) {
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
                    this.locOffset = this.offset;
                    this.TimeZoneMenu(this.offset);
                }
                else {
                    this.latitude = this.loc.getLatLong(this.locString, 1);
                    this.longitude = this.loc.getLatLong(this.locString, 2);
                    this.offset = (int)this.loc.getLatLong(this.locString, 3);
                    this.dst = (int)this.loc.getLatLong(this.locString, 4);
                    this.locOffset = this.offset;
                    this.TimeZoneMenu(this.offset);
                }
                if (this.locChoice.getSelectedItem() != "User Input") {
                    final int gmtHours = this.hours - this.locOffset;
                    this.hours = gmtHours + this.offset;
                    if (this.hours >= 24) {
                        this.hours -= 24;
                        ++this.date;
                        final int n = this.comp.daysInMonth(this.month, this.year);
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
                            this.date = this.comp.daysInMonth(this.month, this.year);
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
                this.myCan = new Suncanvas(this.versStr, this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.azimuthOK, this.zeroDown);
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
                    this.TimeZoneMenu(this.offset);
                    this.locOffset = this.offset;
                    this.myCan = new Suncanvas(this.versStr, this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.azimuthOK, this.zeroDown);
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
                    this.locOffset = this.offset;
                    this.myCan = new Suncanvas(this.versStr, this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.azimuthOK, this.zeroDown);
                    this.repaint();
                    return true;
                }
                if (event.target == this.timeChoice) {
                    this.timeString = this.timeChoice.getSelectedItem();
                    final int gmtHours = this.hours - this.locOffset;
                    this.locOffset = this.loc.getTimeZone(this.timeString);
                    this.hours = gmtHours + this.locOffset;
                    this.usrOffset = this.locOffset;
                    if (this.hours >= 24) {
                        this.hours -= 24;
                        ++this.date;
                        final int n = this.comp.daysInMonth(this.month, this.year);
                        if (this.date > n) {
                            this.date = n;
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
                    this.hoursStr = String.valueOf(this.hours);
                    if (this.hours < 10) {
                        this.hoursStr = "0" + this.hoursStr;
                    }
                    this.hoursChoice.select(this.hours);
                    this.UT = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
                    this.JD = this.comp.Jul_Date(this.date, this.month + 1, this.year + 1900, this.UT);
                    this.myCan = new Suncanvas(this.versStr, this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.azimuthOK, this.zeroDown);
                    this.repaint();
                    return true;
                }
                if (event.target == this.hoursChoice) {
                    this.hoursStr = this.hoursChoice.getSelectedItem();
                    this.hours = Integer.parseInt(this.hoursStr);
                    this.dat.setHours(this.hours);
                    this.dat.setSeconds(0);
                    this.seconds = 0;
                    this.UT = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
                    this.JD = this.comp.Jul_Date(this.date, this.month + 1, this.year + 1900, this.UT);
                    this.myCan = new Suncanvas(this.versStr, this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.azimuthOK, this.zeroDown);
                    this.repaint();
                    return true;
                }
                if (event.target == this.minChoice) {
                    this.minStr = this.minChoice.getSelectedItem();
                    this.minutes = Integer.parseInt(this.minStr);
                    this.minChoice.select(this.minStr);
                    this.dat.setMinutes(this.minutes);
                    this.dat.setSeconds(0);
                    this.seconds = 0;
                    this.UT = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
                    this.JD = this.comp.Jul_Date(this.date, this.month + 1, this.year + 1900, this.UT);
                    this.myCan = new Suncanvas(this.versStr, this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.azimuthOK, this.zeroDown);
                    this.repaint();
                    return true;
                }
                if (event.target == this.dateChoice) {
                    this.dateStr = this.dateChoice.getSelectedItem();
                    this.date = Integer.parseInt(this.dateStr);
                    final int n = this.comp.daysInMonth(this.month, this.year);
                    if (this.date > n) {
                        this.date = n;
                    }
                    this.dat.setDate(this.date);
                    this.UT = this.hours - this.locOffset + this.minutes / 60.0 + this.seconds / 3600.0;
                    this.JD = this.comp.Jul_Date(this.date, this.month + 1, this.year + 1900, this.UT);
                    this.myCan = new Suncanvas(this.versStr, this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.azimuthOK, this.zeroDown);
                    this.repaint();
                    return true;
                }
                if (event.target == this.monthChoice) {
                    this.monthStr = this.monthChoice.getSelectedItem();
                    this.month = this.MonthInteger(this.monthStr);
                    final int n = this.comp.daysInMonth(this.month, this.year);
                    if (this.date > n) {
                        this.date = n;
                    }
                    this.dat.setMonth(this.month);
                    this.JD = this.comp.Jul_Date(this.date, this.month + 1, this.year + 1900, this.UT);
                    this.myCan = new Suncanvas(this.versStr, this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.azimuthOK, this.zeroDown);
                    this.repaint();
                    return true;
                }
                if (event.target == this.yearChoice) {
                    this.yearStr = this.yearChoice.getSelectedItem();
                    this.year = Integer.parseInt(this.yearStr);
                    this.year -= 1900;
                    this.dat.setYear(this.year);
                    final int n = this.comp.daysInMonth(this.month, this.year);
                    if (this.date > n) {
                        this.date = n;
                        this.dat.setDate(this.date);
                        this.dateChoice.select(this.date - 1);
                    }
                    this.JD = this.comp.Jul_Date(this.date, this.month + 1, this.year + 1900, this.UT);
                    this.myCan = new Suncanvas(this.versStr, this.date, this.month, this.year, this.hours, this.minutes, this.seconds, this.latitude, this.longitude, this.locString, this.locOffset, this.azimuthOK, this.zeroDown);
                    this.repaint();
                    return true;
                }
            }
        }
        this.repaint();
        return true;
    }
    
    public SunMoonClock094() {
        this.versStr = "  0.94";
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.usrLat = 0.0;
        this.usrLong = 0.0;
        this.usrOffset = 0;
        this.clickLocOffset = 0;
        this.dst = 1;
        this.running = true;
        this.oben = 100;
        this.azimuthOK = true;
        this.zeroDown = true;
    }
}
