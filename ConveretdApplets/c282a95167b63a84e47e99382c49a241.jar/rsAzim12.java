import java.awt.Font;
import java.awt.Graphics;
import java.awt.Event;
import java.net.URL;
import java.awt.Component;
import java.awt.Label;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Choice;
import java.awt.TextField;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class rsAzim12 extends Applet
{
    char deg;
    Date dat;
    public double latitude;
    public double longitude;
    int year;
    int month;
    int date;
    int hours;
    int minutes;
    int seconds;
    int locOffset;
    Suncanvas myCan;
    public String homeString;
    public String homeLatStr;
    public String homeLongStr;
    public String versStr;
    boolean demo;
    public String str;
    public String email;
    public String param;
    public String wwwStr;
    final double K = 0.017453292519943295;
    double hRise;
    double hSet;
    boolean visible;
    boolean unvisible;
    double jd;
    int theDay;
    int theMonth;
    int theYear;
    double jd0;
    boolean online;
    TextField fieldLatDeg;
    TextField fieldLongDeg;
    String latStr;
    String longStr;
    String timeString;
    Choice timeChoice;
    String timezoneStr;
    int timezoneParam;
    boolean touched;
    String[] dataStr;
    double homeLat;
    double homeLong;
    double[] Set;
    double[] Rise;
    int[] datum;
    int[] monat;
    int[] Year;
    String[] day;
    boolean[] vis;
    boolean[] unvis;
    String ns;
    String ew;
    scrollFrame sf;
    Choice writeChoice;
    Choice monthChoice;
    Choice dateChoice;
    String[] monthArray;
    String dateStr;
    String monthStr;
    Compute comp;
    int offsetJan;
    int offsetJun;
    int offsetMax;
    String yearStr;
    
    public void init() {
        this.setBackground(Color.white);
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        final Panel p = new Panel();
        p.setLayout(new GridLayout(3, 3));
        this.homeString = this.getParameter("location");
        this.homeLongStr = this.getParameter("longitude");
        this.homeLatStr = this.getParameter("latitude");
        this.timezoneStr = this.getParameter("timezone");
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        final Label L1 = new Label(" Latitude");
        p.add(L1);
        final Label L2 = new Label(" Longitude");
        p.add(L2);
        final Label L3 = new Label(" Time Zone");
        p.add(L3);
        final URL url = this.getDocumentBase();
        this.str = url.toString();
        this.str = String.valueOf(this.str) + "1234567890123456789012345";
        this.wwwStr = this.str.substring(0, 27);
        final Double longDouble = Double.valueOf(this.homeLongStr);
        this.longitude = longDouble;
        if (this.longitude > 0.0) {
            this.ew = " E";
        }
        else {
            this.ew = " W";
        }
        this.homeLong = this.longitude;
        final Double latDouble = Double.valueOf(this.homeLatStr);
        this.latitude = latDouble;
        this.homeLat = this.latitude;
        if (this.latitude >= 0.0) {
            this.ns = " N";
        }
        else {
            this.ns = " S";
        }
        this.dat = new Date();
        this.hours = this.dat.getHours();
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth();
        this.minutes = this.dat.getMinutes();
        this.seconds = this.dat.getSeconds();
        this.year = this.dat.getYear();
        this.yearStr = String.valueOf(this.year + 1900);
        this.theMonth = this.month;
        this.jd0 = this.JD(this.date, this.month + 1, this.year + 1900, 0.0);
        this.locOffset = -this.dat.getTimezoneOffset() / 60;
        this.timeString = String.valueOf(Math.abs(this.locOffset)) + " h";
        if (this.locOffset >= 0) {
            this.timeString = "+" + this.timeString;
        }
        this.timeString = "UT " + this.timeString;
        this.latStr = String.valueOf(Math.abs(this.latitude));
        p.add(this.fieldLatDeg = new TextField(this.latStr, 5));
        this.longStr = String.valueOf(Math.abs(this.longitude));
        p.add(this.fieldLongDeg = new TextField(this.longStr, 6));
        (this.timeChoice = new Choice()).addItem("UT +0 h");
        this.timeChoice.addItem("UT -1 h");
        this.timeChoice.addItem("UT +1 h");
        this.timeChoice.addItem("UT -2 h");
        this.timeChoice.addItem("UT +2 h");
        this.timeChoice.addItem("UT -3 h");
        this.timeChoice.addItem("UT +3 h");
        this.timeChoice.addItem("UT -4 h");
        this.timeChoice.addItem("UT +4 h");
        this.timeChoice.addItem("UT -5 h");
        this.timeChoice.addItem("UT +5 h");
        this.timeChoice.addItem("UT -6 h");
        this.timeChoice.addItem("UT +6 h");
        this.timeChoice.addItem("UT -7 h");
        this.timeChoice.addItem("UT +7 h");
        this.timeChoice.addItem("UT -8 h");
        this.timeChoice.addItem("UT +8 h");
        this.timeChoice.addItem("UT -9 h");
        this.timeChoice.addItem("UT +9 h");
        this.timeChoice.addItem("UT -10 h");
        this.timeChoice.addItem("UT +10 h");
        this.timeChoice.addItem("UT -11 h");
        this.timeChoice.addItem("UT +11 h");
        this.timeChoice.addItem("UT -12 h");
        this.timeChoice.addItem("UT +12 h");
        this.timeChoice.addItem("UT +13 h");
        this.timeChoice.select(this.timeString);
        p.add(this.timeChoice);
        this.monthChoice = new Choice();
        for (int i = 0; i < 12; ++i) {
            this.monthChoice.addItem(this.monthArray[i]);
        }
        this.monthChoice.select(this.month);
        p.add(this.monthChoice);
        this.dateChoice = new Choice();
        for (int j = 1; j < 32; ++j) {
            this.dateStr = String.valueOf(j);
            this.dateChoice.addItem(this.dateStr);
        }
        this.dateChoice.select(this.date - 1);
        p.add(this.dateChoice);
        (this.writeChoice = new Choice()).addItem("Write:");
        for (int k = 1; k <= 12; ++k) {
            this.writeChoice.addItem(this.monthString(k));
        }
        this.writeChoice.select(0);
        p.add(this.writeChoice);
        this.add("North", p);
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        this.demo = true;
        this.online = false;
        if (this.formula(this.wwwStr, 22) == this.formula("http://www.geoastro.de", 22) || this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21)) {
            ok = true;
            this.demo = false;
            this.online = true;
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
                this.online = true;
            }
        }
        System.out.println(String.valueOf(this.versStr) + " -  (c) 2005-2010 J. Giesen -- www.GeoAstro.de");
        this.repaint();
        this.calculate(this.dat);
        this.comp = new Compute();
        this.doData();
    }
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 5333;
    }
    
    public void calculate(final Date dat) {
        if (!this.touched) {
            this.locOffset = -dat.getTimezoneOffset() / 60;
            this.timeString = String.valueOf(Math.abs(this.locOffset)) + " h";
            if (this.locOffset >= 0) {
                this.timeString = "+" + this.timeString;
            }
            this.timeString = "UT " + this.timeString;
            this.timeChoice.select(this.timeString);
        }
        this.myCan = new Suncanvas(dat, this.latitude, this.longitude, this.locOffset);
        this.hRise = this.myCan.Rise();
        this.hSet = this.myCan.Set();
        this.visible = this.myCan.isVisible();
        this.unvisible = this.myCan.notVisible();
    }
    
    double THETA0(final double JD) {
        final double T = (JD - 2451545.0) / 36525.0;
        double x = 280.46061837 + 360.98564736629 * (JD - 2451545.0) + 3.87933E-4 * T * T - T * T * T / 3.871E7;
        x %= 360.0;
        if (x < 0.0) {
            x += 360.0;
        }
        return x;
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
    
    public String monthString(final int m) {
        String str = "?";
        final String[] monthArray = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        str = monthArray[m - 1];
        return str;
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
    
    public double JD(final int date, int month, int year, final double STD) {
        if (month <= 2) {
            month += 12;
            --year;
        }
        final double B = year / 400 - year / 100 + year / 4;
        final double A = 365.0 * year - 679004.0;
        final double MJD = A + B + (int)(30.6001 * (month + 1)) + date + STD / 24.0;
        return MJD + 2400000.5;
    }
    
    public void caldat(double JD) {
        JD += this.locOffset / 24.0;
        final double JD2 = (int)(JD + 0.5);
        final int B = (int)((JD2 - 1867216.25) / 36524.25);
        final double C = JD2 + B - B / 4 + 1525.0;
        final int D = (int)((C - 122.1) / 365.25);
        final double E = 365.0 * D + D / 4;
        final int F = (int)((C - E) / 30.6001);
        final int day = (int)(C - E + 0.5) - (int)(30.6001 * F);
        final int month = F - 1 - 12 * (F / 14);
        final int year = D - 4715 - (7 + month) / 10;
        final double hour = 24.0 * (JD + 0.5 - JD2);
        this.theDay = day;
        this.theMonth = month;
        this.theYear = year;
    }
    
    public String dayString(final double jd) {
        final String[] dayArray = { "Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun" };
        long num = (long)(jd + 0.5);
        num -= num / 7L * 7L;
        return dayArray[(int)num];
    }
    
    public void theOffset() {
        final Date dat1 = new Date();
        dat1.setMonth(0);
        dat1.setDate(1);
        this.offsetJan = -dat1.getTimezoneOffset() / 60;
        final Date dat2 = new Date();
        dat2.setMonth(5);
        dat2.setDate(1);
        this.offsetJun = -dat2.getTimezoneOffset() / 60;
        if (this.offsetJun > this.offsetJan) {
            this.offsetMax = this.offsetJun;
        }
        if (this.offsetJan > this.offsetJun) {
            this.offsetMax = this.offsetJan;
        }
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
    
    public boolean action(final Event event, final Object eventobject) {
        final double oldLat = this.latitude;
        final double oldLong = this.longitude;
        if (event.target instanceof Choice) {
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
                return true;
            }
            if (event.target == this.monthChoice) {
                this.monthStr = this.monthChoice.getSelectedItem();
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
                return true;
            }
            if (event.target == this.timeChoice) {
                this.touched = true;
                this.timeString = this.timeChoice.getSelectedItem();
                this.locOffset = this.getTimeZone(this.timeString);
                this.repaint();
                return true;
            }
            if (event.target == this.writeChoice) {
                final String writeStr = this.writeChoice.getSelectedItem();
                if (writeStr.equals("Write:")) {
                    return true;
                }
                if (writeStr.equals("Jan")) {
                    this.month = 0;
                }
                if (writeStr.equals("Feb")) {
                    this.month = 1;
                }
                if (writeStr.equals("Mar")) {
                    this.month = 2;
                }
                if (writeStr.equals("Apr")) {
                    this.month = 3;
                }
                if (writeStr.equals("May")) {
                    this.month = 4;
                }
                if (writeStr.equals("Jun")) {
                    this.month = 5;
                }
                if (writeStr.equals("Jul")) {
                    this.month = 6;
                }
                if (writeStr.equals("Aug")) {
                    this.month = 7;
                }
                if (writeStr.equals("Sep")) {
                    this.month = 8;
                }
                if (writeStr.equals("Oct")) {
                    this.month = 9;
                }
                if (writeStr.equals("Nov")) {
                    this.month = 10;
                }
                if (writeStr.equals("Dec")) {
                    this.month = 11;
                }
                this.writeChoice.select(0);
                this.doData();
                this.repaint();
                return true;
            }
        }
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
                    if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != '.' && c != '+' && c != '-') {
                        this.fieldLatDeg.setText(String.valueOf(Math.abs(oldLat)));
                        return true;
                    }
                }
                if (!this.str.equals(this.latStr) || this.str.length() == 0) {
                    Double.valueOf(this.str);
                }
                this.latStr = this.str;
                final Double latDouble = Double.valueOf(this.latStr);
                this.latitude = latDouble;
                this.fieldLatDeg.setText(this.str);
                if (Math.abs(this.latitude) >= 90.0) {
                    this.latitude = oldLat;
                    this.latStr = String.valueOf(Math.abs(Math.round(100.0 * oldLat) / 100.0));
                    this.fieldLatDeg.setText(this.latStr);
                }
                else {
                    if (!this.str.equals(this.latStr) || this.str.length() == 0) {
                        this.latStr = this.str;
                    }
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                }
                if (this.latitude >= 0.0) {
                    this.ns = " N";
                }
                else {
                    this.ns = " S";
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
                    final char c = this.str.charAt(i);
                    if (c == ',') {
                        this.str = String.valueOf(this.str.substring(0, i)) + '.' + this.str.substring(i + 1, this.str.length());
                    }
                }
                for (int j = 0; j < this.str.length(); ++j) {
                    final char c = this.str.charAt(j);
                    if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != '.' && c != '+' && c != '-') {
                        this.fieldLongDeg.setText(String.valueOf(Math.abs(oldLong)));
                        return true;
                    }
                }
                if (!this.str.equals(this.longStr) || this.str.length() == 0) {
                    Double.valueOf(this.str);
                }
                this.longStr = this.str;
                final Double longDouble = Double.valueOf(this.str);
                this.longitude = longDouble;
                this.fieldLongDeg.setText(this.longStr);
                if (Math.abs(this.longitude) >= 180.0) {
                    this.longitude = oldLong;
                    this.longStr = String.valueOf(Math.abs(Math.round(100.0 * oldLong) / 100.0));
                    this.fieldLongDeg.setText(this.longStr);
                }
                else {
                    if (!this.str.equals(this.longStr) || this.str.length() == 0) {
                        this.longStr = this.str;
                    }
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                }
                if (this.longitude >= 0.0) {
                    this.ew = " E";
                }
                else {
                    this.ew = " W";
                }
                this.repaint();
                return true;
            }
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int code) {
        int n = 0;
        if (code != 100 && code != 109 && code != 68 && code != 77 && code != 119 && code != 87) {
            return false;
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
                        this.yearStr = String.valueOf(this.year + 1900);
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
                        this.yearStr = String.valueOf(this.year + 1900);
                    }
                }
            }
            this.dateChoice.select(this.date - 1);
            this.dat.setMonth(this.month);
            this.monthChoice.select(this.month);
            this.dat.setDate(this.date);
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
            if (this.month == 12) {
                this.month = 0;
                ++this.year;
                this.dat.setYear(this.year);
                this.yearStr = String.valueOf(this.year + 1900);
            }
            if (this.month == -1) {
                this.month = 11;
                --this.year;
                this.dat.setYear(this.year);
                this.yearStr = String.valueOf(this.year + 1900);
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
            this.repaint();
            return true;
        }
        return false;
    }
    
    public int daysInMonth(final int m, int y) {
        int n = 31;
        y += 1900;
        if (m == 0 || m == 2 || m == 4 || m == 6 || m == 7 || m == 9 || m == 11) {
            n = 31;
        }
        else if (m == 3 || m == 5 || m == 8 || m == 10) {
            n = 30;
        }
        else if (m == 1) {
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
    
    public void doData() {
        int kData = 0;
        final Date dat1 = new Date();
        this.dataStr[0] = String.valueOf(this.versStr) + " -  © 2005-2010 J. Giesen" + "\n";
        final String[] dataStr = this.dataStr;
        final int n2 = 0;
        dataStr[n2] = String.valueOf(dataStr[n2]) + "www.GeoAstro.de\n";
        final String[] dataStr2 = this.dataStr;
        final int n3 = 0;
        dataStr2[n3] = String.valueOf(dataStr2[n3]) + "\n";
        if (this.latitude >= 0.0) {
            this.ns = " N";
        }
        else {
            this.ns = " S";
        }
        if (this.longitude >= 0.0) {
            this.ew = " E";
        }
        else {
            this.ew = " W";
        }
        if (this.timezoneStr.equals("auto")) {
            this.dataStr[1] = "Lat. " + Math.abs(this.latitude) + this.deg + this.ns + ",  Long. " + Math.abs(this.longitude) + this.deg + this.ew;
        }
        else {
            this.dataStr[1] = "Lat. " + Math.abs(this.latitude) + this.deg + this.ns + ",  Long. " + Math.abs(this.longitude) + this.deg + this.ew + ",  " + this.timeString;
        }
        final String[] dataStr3 = this.dataStr;
        final int n4 = 1;
        dataStr3[n4] = String.valueOf(dataStr3[n4]) + ",  " + this.yearStr + "\n";
        final String[] dataStr4 = this.dataStr;
        final int n5 = 1;
        dataStr4[n5] = String.valueOf(dataStr4[n5]) + "\n";
        kData = 2;
        final double JD0 = this.JD(1, this.month + 1, this.year + 1900, 0.0);
        final int n = this.daysInMonth(this.month, this.year);
        for (int i = 0; i < n; ++i) {
            final double jd = JD0 + i;
            this.caldat(jd);
            dat1.setDate(this.theDay);
            dat1.setMonth(this.theMonth - 1);
            dat1.setYear(this.theYear - 1900);
            this.datum[i] = this.theDay;
            this.monat[i] = this.theMonth;
            this.Year[i] = this.theYear;
            this.day[i] = this.dayString(jd);
            this.calculate(dat1);
            this.Set[i] = this.hSet;
            this.Rise[i] = this.hRise;
            this.vis[i] = this.visible;
            this.unvis[i] = this.unvisible;
        }
        String str1 = "";
        String str2 = "";
        for (int j = 0; j < n; ++j) {
            this.visible = this.vis[j];
            this.unvisible = this.unvis[j];
            String s = "";
            String s2 = "";
            double jd = this.JD(this.datum[j], this.monat[j], this.year + 1900, this.Rise[j] - this.locOffset);
            double dec = this.comp.sunDecRA(1, jd);
            double ra = this.comp.sunDecRA(2, jd);
            double h = this.THETA0(jd) + this.longitude - 15.0 * ra;
            double elev = Math.sin(0.017453292519943295 * this.latitude) * Math.sin(0.017453292519943295 * dec) + Math.cos(0.017453292519943295 * this.latitude) * Math.cos(0.017453292519943295 * dec) * Math.cos(0.017453292519943295 * h);
            elev = Math.asin(elev) / 0.017453292519943295;
            final double azim1 = 180.0 + Math.atan2(Math.sin(0.017453292519943295 * h), Math.cos(0.017453292519943295 * h) * Math.sin(0.017453292519943295 * this.latitude) - Math.tan(0.017453292519943295 * dec) * Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
            if (azim1 < 100.0) {
                s2 = " ";
            }
            jd = this.JD(this.datum[j], this.monat[j], this.year + 1900, this.Set[j] - this.locOffset);
            dec = this.comp.sunDecRA(1, jd);
            ra = this.comp.sunDecRA(2, jd);
            h = this.THETA0(jd) + this.longitude - 15.0 * ra;
            elev = Math.sin(0.017453292519943295 * this.latitude) * Math.sin(0.017453292519943295 * dec) + Math.cos(0.017453292519943295 * this.latitude) * Math.cos(0.017453292519943295 * dec) * Math.cos(0.017453292519943295 * h);
            elev = Math.asin(elev) / 0.017453292519943295;
            final double azim2 = 180.0 + Math.atan2(Math.sin(0.017453292519943295 * h), Math.cos(0.017453292519943295 * h) * Math.sin(0.017453292519943295 * this.latitude) - Math.tan(0.017453292519943295 * dec) * Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
            if (this.datum[j] < 10) {
                s = " ";
            }
            this.dataStr[kData] = String.valueOf(this.day[j]) + ", " + this.monthString(this.monat[j]) + " " + s + this.datum[j] + "  ";
            ++kData;
            if (this.Rise[j] != 100.0 && this.Set[j] != 100.0) {
                str1 = String.valueOf(this.makeTimeString(this.Rise[j])) + "  " + s2 + Math.round(10.0 * azim1) / 10.0 + this.deg;
                str2 = "             " + this.makeTimeString(this.Set[j]) + "  " + Math.round(10.0 * azim2) / 10.0 + this.deg;
            }
            else if (this.visible) {
                str1 = "Sun visible all day";
            }
            else {
                str1 = "Sun invisible all day";
            }
            this.dataStr[kData] = String.valueOf(str1) + "\n";
            ++kData;
            this.dataStr[kData] = String.valueOf(str2) + "\n";
            ++kData;
        }
        final boolean dem = this.online || this.demo;
        (this.sf = new scrollFrame("Azimuth " + this.monthString(this.theMonth) + " " + this.yearStr, kData, this.dataStr, dem)).resize(300, 600);
        this.sf.show();
    }
    
    public void paint(final Graphics g) {
        g.setFont(new Font("Courier", 0, 10));
        final int links = 20;
        final int oben = 100;
        String usrStr = this.homeString;
        g.setColor(Color.red);
        if (this.homeLat != this.latitude || this.homeLong != this.longitude) {
            usrStr = "User";
        }
        g.drawString(this.str = String.valueOf(usrStr) + ",  Lat. " + Math.abs(this.latitude) + this.deg + this.ns + ",  Long. " + Math.abs(this.longitude) + this.deg + this.ew, links, oben);
        g.setColor(Color.black);
        this.calculate(this.dat);
        this.comp = new Compute();
        double jd = this.JD(this.date, this.month + 1, this.year + 1900, this.hRise - this.locOffset);
        double dec = this.comp.sunDecRA(1, jd);
        double ra = this.comp.sunDecRA(2, jd);
        double h = this.THETA0(jd) + this.longitude - 15.0 * ra;
        double elev = Math.sin(0.017453292519943295 * this.latitude) * Math.sin(0.017453292519943295 * dec) + Math.cos(0.017453292519943295 * this.latitude) * Math.cos(0.017453292519943295 * dec) * Math.cos(0.017453292519943295 * h);
        elev = Math.asin(elev) / 0.017453292519943295;
        double azim = 180.0 + Math.atan2(Math.sin(0.017453292519943295 * h), Math.cos(0.017453292519943295 * h) * Math.sin(0.017453292519943295 * this.latitude) - Math.tan(0.017453292519943295 * dec) * Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
        String s = "";
        if (azim < 100.0) {
            s = " ";
        }
        if (this.hRise != 100.0) {
            g.drawString("Sunrise: " + this.makeTimeString(this.hRise) + "    Azimuth: " + s + Math.round(10.0 * azim) / 10.0 + this.deg, links, oben + 15);
        }
        else {
            if (this.visible) {
                g.drawString("Sun visible all day", links, oben + 15);
            }
            if (this.unvisible) {
                g.drawString("Sun invisible all day", links, oben + 15);
            }
        }
        jd = this.JD(this.date, this.month + 1, this.year + 1900, this.hSet - this.locOffset);
        dec = this.comp.sunDecRA(1, jd);
        ra = this.comp.sunDecRA(2, jd);
        h = this.THETA0(jd) + this.longitude - 15.0 * ra;
        elev = Math.sin(0.017453292519943295 * this.latitude) * Math.sin(0.017453292519943295 * dec) + Math.cos(0.017453292519943295 * this.latitude) * Math.cos(0.017453292519943295 * dec) * Math.cos(0.017453292519943295 * h);
        elev = Math.asin(elev) / 0.017453292519943295;
        azim = 180.0 + Math.atan2(Math.sin(0.017453292519943295 * h), Math.cos(0.017453292519943295 * h) * Math.sin(0.017453292519943295 * this.latitude) - Math.tan(0.017453292519943295 * dec) * Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
        if (this.hSet != 100.0) {
            g.drawString("Sunset:  " + this.makeTimeString(this.hSet) + "    Azimuth: " + Math.round(10.0 * azim) / 10.0 + this.deg, links, oben + 30);
        }
        g.setColor(Color.blue);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        if (this.demo) {
            g.setColor(Color.red);
            g.setFont(new Font("Helvetiva", 1, 24));
            g.drawString("D E M O", 110, this.size().height - 30);
        }
        g.setFont(new Font("Helvetiva", 0, 9));
        g.drawString(String.valueOf(this.versStr) + " -  (c) 2005-2010 J. Giesen -- www.GeoAstro.de", links, this.size().height - 10);
    }
    
    public rsAzim12() {
        this.deg = '°';
        this.versStr = "RS Azimuth 1.2";
        this.demo = true;
        this.touched = false;
        this.dataStr = new String[100];
        this.Set = new double[32];
        this.Rise = new double[32];
        this.datum = new int[32];
        this.monat = new int[32];
        this.Year = new int[32];
        this.day = new String[32];
        this.vis = new boolean[32];
        this.unvis = new boolean[32];
        this.monthArray = new String[12];
    }
}
