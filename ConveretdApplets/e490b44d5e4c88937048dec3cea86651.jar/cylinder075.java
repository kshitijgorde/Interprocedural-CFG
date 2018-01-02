import java.awt.Frame;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Dialog;
import java.net.URL;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Button;
import java.util.Date;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Checkbox;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class cylinder075 extends Applet
{
    String versStr;
    final char deg = '°';
    final double K = 0.017453292519943295;
    public compute comp;
    public Seloc loc;
    public Seloc time;
    Checkbox sunBox;
    boolean demo;
    boolean online;
    boolean sun;
    double usrLat;
    double usrLong;
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
    TextField fieldLatDeg;
    TextField fieldLongDeg;
    public Choice locChoice;
    public Choice timeChoice;
    Choice ewChoice;
    Choice nsChoice;
    Choice showChoice;
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
    String homeString;
    String homeLatStr;
    String homeLongStr;
    int timezoneoffset;
    int[] yArray;
    boolean[] hArray;
    Choice hoursChoice;
    Choice minChoice;
    Choice yearChoice;
    Choice monthChoice;
    Choice dateChoice;
    Date dat;
    Button aboutButton;
    String[] dataStr;
    int nData;
    double currentElev;
    double Jd;
    double UT;
    Choice gnomonChoice;
    boolean[] red;
    int[] trans;
    int gnomon;
    double eq;
    double curTrans;
    double dec;
    double ra;
    double gha;
    double hoehe;
    boolean transit;
    int links;
    int y0;
    boolean drawHours;
    boolean half;
    double DX;
    boolean dateLines;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 5528;
    }
    
    public void init() {
        System.out.println(String.valueOf(this.versStr) + "  (c) 2006-2008 J.Giesen - www.GeoAstro.de");
        final Color background = new Color(255, 255, 255);
        this.setBackground(background);
        this.homeString = this.getParameter("location");
        this.homeLatStr = this.getParameter("latitude");
        this.homeLongStr = this.getParameter("longitude");
        final String wString = this.getParameter("w");
        final Double wDouble = Double.valueOf(wString);
        this.DX = wDouble;
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
        this.seconds = this.dat.getSeconds();
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        this.timezoneoffset = -this.dat.getTimezoneOffset() / 60;
        this.timeString = String.valueOf(this.timezoneoffset);
        if (this.timezoneoffset > 0) {
            this.timeString = "+" + this.timeString;
        }
        this.timeString = "UT " + this.timeString + " h";
        final Font f = new Font("Helvetica", 0, 10);
        this.setFont(f);
        gbc.weighty = 0.0;
        gbc.insets = new Insets(5, 20, 0, 0);
        final Label L11 = new Label();
        L11.setText("Time Zone");
        gbl.setConstraints(L11, gbc);
        this.add(L11);
        gbc.insets = new Insets(5, 0, 0, 0);
        final Label L12 = new Label();
        L12.setText("Location");
        gbl.setConstraints(L12, gbc);
        this.add(L12);
        final Label L13 = new Label();
        L13.setText("Latit. xx.xx");
        gbl.setConstraints(L13, gbc);
        this.add(L13);
        final Label L14 = new Label();
        L14.setText("N / S");
        gbl.setConstraints(L14, gbc);
        this.add(L14);
        final Label L15 = new Label();
        L15.setText("Long. xx.xx");
        gbl.setConstraints(L15, gbc);
        this.add(L15);
        final Label L16 = new Label();
        L16.setText("E / W");
        gbl.setConstraints(L16, gbc);
        this.add(L16);
        this.sunBox = new Checkbox("Show Sun");
        gbc.insets = new Insets(5, 0, 0, 5);
        gbl.setConstraints(this.sunBox, gbc);
        this.sunBox.setState(true);
        this.add(this.sunBox);
        final Label L17 = new Label();
        L17.setText("Gnomon");
        gbl.setConstraints(L17, gbc);
        this.add(L17);
        gbc.gridy = 1;
        this.time = new Seloc();
        this.timeChoice = new Choice();
        this.time.timeMenu(this.timeChoice);
        gbc.insets = new Insets(0, 20, 0, 0);
        gbl.setConstraints(this.timeChoice, gbc);
        this.timeChoice.select(this.timeString);
        this.add(this.timeChoice);
        gbc.insets = new Insets(0, 10, 0, 0);
        this.locChoice = new Choice();
        this.locString = this.homeString;
        this.locChoice.addItem(this.locString);
        this.locChoice.addItem("---------------");
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
        gbl.setConstraints(this.fieldLatDeg = new TextField(this.latStr, 5), gbc);
        this.add(this.fieldLatDeg);
        (this.nsChoice = new Choice()).addItem("N");
        this.nsChoice.addItem("S");
        this.nsChoice.select(this.nsStr);
        gbl.setConstraints(this.nsChoice, gbc);
        this.add(this.nsChoice);
        this.longStr = String.valueOf(Math.abs(this.longitude));
        gbl.setConstraints(this.fieldLongDeg = new TextField(this.longStr, 6), gbc);
        this.add(this.fieldLongDeg);
        (this.ewChoice = new Choice()).addItem("E");
        this.ewChoice.addItem("W");
        this.ewChoice.select(this.ewStr);
        gbc.insets = new Insets(0, 0, 0, 10);
        gbl.setConstraints(this.ewChoice, gbc);
        this.add(this.ewChoice);
        this.showChoice = new Choice();
        gbc.insets = new Insets(0, 0, 0, 15);
        gbl.setConstraints(this.showChoice, gbc);
        this.showChoice.addItem("Select...");
        this.showChoice.addItem("Transit on/off");
        this.showChoice.addItem("Hours on/off");
        this.showChoice.addItem("Half Hours on/off");
        this.showChoice.addItem("Date Lines on/off");
        this.showChoice.addItem("Transit Data");
        this.showChoice.addItem("Rise/Set Data");
        this.showChoice.addItem("Now");
        this.add(this.showChoice);
        this.gnomonChoice = new Choice();
        gbc.insets = new Insets(0, 0, 0, 15);
        gbl.setConstraints(this.gnomonChoice, gbc);
        this.gnomonChoice.addItem("300");
        this.gnomonChoice.addItem("275");
        this.gnomonChoice.addItem("250");
        this.gnomonChoice.addItem("200");
        this.gnomonChoice.addItem("150");
        this.gnomonChoice.addItem("100");
        this.gnomonChoice.addItem("50");
        this.gnomonChoice.select("200");
        this.add(this.gnomonChoice);
        gbc.gridy = 3;
        gbc.gridx = 0;
        final Label L18 = new Label();
        L18.setText("Local Time (h:m):");
        gbc.insets = new Insets(5, 20, 0, 0);
        gbl.setConstraints(L18, gbc);
        this.add(L18);
        gbc.insets = new Insets(5, 0, 0, 0);
        gbc.gridx = 1;
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
        gbc.gridx = 2;
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
        gbc.gridx = 3;
        final Label L19 = new Label();
        L19.setText("Date:");
        gbl.setConstraints(L19, gbc);
        this.add(L19);
        gbc.gridx = 4;
        this.yearChoice = new Choice();
        for (int m = 0; m < 20; ++m) {
            this.yearStr = String.valueOf(this.year + 1900 - 9 + m);
            this.yearChoice.addItem(this.yearStr);
        }
        this.yearStr = String.valueOf(this.year + 1900);
        this.yearChoice.select(this.yearStr);
        gbl.setConstraints(this.yearChoice, gbc);
        this.add(this.yearChoice);
        gbc.gridx = 5;
        this.monthChoice = new Choice();
        for (int i2 = 0; i2 < 12; ++i2) {
            this.monthChoice.addItem(this.monthArray[i2]);
        }
        this.monthChoice.select(this.month);
        gbl.setConstraints(this.monthChoice, gbc);
        this.add(this.monthChoice);
        gbc.gridx = 6;
        this.dateChoice = new Choice();
        for (int i3 = 1; i3 < 32; ++i3) {
            this.dateStr = String.valueOf(i3);
            this.dateChoice.addItem(this.dateStr);
        }
        this.dateChoice.select(this.date - 1);
        gbl.setConstraints(this.dateChoice, gbc);
        this.add(this.dateChoice);
        gbc.gridx = 7;
        this.aboutButton = new Button();
        gbc.insets = new Insets(0, 0, 0, 0);
        gbl.setConstraints(this.aboutButton, gbc);
        this.aboutButton.setLabel("About...");
        this.add(this.aboutButton);
        gbc.gridy = 4;
        gbc.weighty = 1.0;
        final Canvas myCan = new Canvas();
        gbl.setConstraints(myCan, gbc);
        this.add(myCan);
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        this.usrStr = this.email;
        if (this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(this.wwwStr, 20) == this.formula("http://www.SciAm.com", 20)) {
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
                final Dialog AboutDialog = new Aboutdialog(this, this.versStr, this.demo);
                AboutDialog.resize(350, 230);
                AboutDialog.show();
            }
        }
        if (this.demo) {
            this.versStr = String.valueOf(this.versStr) + " DEMO";
        }
        this.repaint();
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
    
    public int MonthInteger(final String mStr) {
        int m = 100;
        for (int i = 0; i < 12; ++i) {
            if (mStr == this.monthArray[i]) {
                m = i;
            }
        }
        return m;
    }
    
    public double RightAscension(final double T) {
        final double K = 0.017453292519943295;
        final double L = this.sunL(T);
        double M = 357.5291 + 35999.0503 * T - 1.559E-4 * T * T - 4.8E-7 * T * T * T;
        M %= 360.0;
        if (M < 0.0) {
            M += 360.0;
        }
        double C = (1.9146 - 0.004817 * T - 1.4E-5 * T * T) * Math.sin(0.017453292519943295 * M);
        C += (0.019993 - 1.01E-4 * T) * Math.sin(0.03490658503988659 * M);
        C += 2.9E-4 * Math.sin(0.05235987755982989 * M);
        final double theta = L + C;
        double eps = this.EPS(T);
        eps += 0.00256 * Math.cos(0.017453292519943295 * (125.04 - 1934.136 * T));
        final double lambda = theta - 0.00569 - 0.00478 * Math.sin(0.017453292519943295 * (125.04 - 1934.136 * T));
        double RA = Math.atan2(Math.cos(0.017453292519943295 * eps) * Math.sin(0.017453292519943295 * lambda), Math.cos(0.017453292519943295 * lambda));
        RA /= 0.017453292519943295;
        if (RA < 0.0) {
            RA += 360.0;
        }
        double delta = Math.asin(Math.sin(0.017453292519943295 * eps) * Math.sin(0.017453292519943295 * lambda));
        delta /= 0.017453292519943295;
        return RA;
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
    
    public double EPS(final double T) {
        final double K = 0.017453292519943295;
        final double LS = this.sunL(T);
        final double LM = 218.3165 + 481267.8813 * T;
        final double eps0 = 23.43929111111111 - (46.815 * T + 5.9E-4 * T * T - 0.001813 * T * T * T) / 3600.0;
        final double omega = 125.04452 - 1934.136261 * T + 0.0020708 * T * T + T * T * T / 450000.0;
        final double deltaEps = (9.2 * Math.cos(0.017453292519943295 * omega) + 0.57 * Math.cos(0.03490658503988659 * LS) + 0.1 * Math.cos(0.03490658503988659 * LM) - 0.09 * Math.cos(0.03490658503988659 * omega)) / 3600.0;
        return eps0 + deltaEps;
    }
    
    public double sunL(final double T) {
        double L = 280.46645 + 36000.76983 * T + 3.032E-4 * T * T;
        final double tau = T / 10.0;
        L = 280.4664567 + 360007.6982779 * tau + 0.03032028 * tau * tau + tau * tau * tau / 49931.0 - tau * tau * tau * tau / 15299.0 + tau * tau * tau * tau * tau / 1988000.0;
        L %= 360.0;
        if (L < 0.0) {
            L += 360.0;
        }
        return L;
    }
    
    public double deltaPSI(final double T) {
        final double K = 0.017453292519943295;
        final double LS = this.sunL(T);
        double LM = 218.3165 + 481267.8813 * T;
        LM %= 360.0;
        if (LM < 0.0) {
            LM += 360.0;
        }
        final double omega = 125.04452 - 1934.136261 * T + 0.0020708 * T * T + T * T * T / 450000.0;
        double deltaPsi = -17.2 * Math.sin(0.017453292519943295 * omega) - 1.32 * Math.sin(0.03490658503988659 * LS) - 0.23 * Math.sin(0.03490658503988659 * LM) + 0.21 * Math.sin(0.03490658503988659 * omega);
        deltaPsi /= 3600.0;
        return deltaPsi;
    }
    
    public double eot(final int date, final int month, final int year, final double UT) {
        final double K = 0.017453292519943295;
        final double T = (this.JD(date, month, year, UT) - 2451545.0) / 36525.0;
        final double eps = this.EPS(T);
        final double RA = this.RightAscension(T);
        final double LS = this.sunL(T);
        final double deltaPsi = this.deltaPSI(T);
        double E = LS - 0.0057183 - RA + deltaPsi * Math.cos(0.017453292519943295 * eps);
        if (E > 5.0) {
            E -= 360.0;
        }
        return E * 4.0;
    }
    
    public void doRiseSet() {
        int n = 0;
        String str;
        if (this.timezoneoffset >= 0) {
            str = ", UT +" + this.timezoneoffset;
        }
        else {
            str = ", UT " + this.timezoneoffset;
        }
        this.dataStr[0] = "\n" + this.latStr + this.nsStr + ", " + this.longStr + this.ewStr + str + "h";
        final String[] dataStr = this.dataStr;
        final int n2 = 0;
        dataStr[n2] = String.valueOf(dataStr[n2]) + "\n         Rise    Set   Daylight";
        for (int m = 0; m < 12; ++m) {
            for (int d = 1; d < this.daysInMonth(m, this.year) + 1; ++d) {
                ++n;
                final RiseSet rs = new RiseSet(d, m, this.year, this.timezoneoffset, this.latitude, this.longitude);
                rs.currentRiseSet();
                if (d < 10) {
                    str = "0" + d;
                }
                else {
                    str = String.valueOf(d);
                }
                this.dataStr[n] = String.valueOf(this.monthArray[m]) + "  " + str + "  " + rs.returnStr();
            }
        }
        this.nData = n;
    }
    
    public void paint(final Graphics g) {
        int n = 0;
        int nDays = 0;
        int Y = 0;
        g.setFont(new Font("Helvetica", 0, 11));
        this.UT = this.hours + this.minutes / 60.0 + this.seconds / 3600.0 - this.timezoneoffset;
        this.comp = new compute();
        this.Jd = this.JD(this.date, this.month + 1, this.year + 1900, this.UT);
        this.currentElev = this.comp.sun_elev(this.Jd, this.latitude, -this.longitude, this.comp.sunDecRA(this.Jd, 1), this.comp.sunDecRA(this.Jd, 2));
        switch (this.gnomon) {
            case 300: {
                Y = (int)Math.round(this.gnomon * Math.tan(1.090830782496456));
                break;
            }
            case 250:
            case 275: {
                Y = (int)Math.round(this.gnomon * Math.tan(1.1344640137963142));
                break;
            }
            case 200: {
                Y = (int)Math.round(this.gnomon * Math.tan(1.2217304763960306));
                break;
            }
            case 150: {
                Y = (int)Math.round(this.gnomon * Math.tan(1.3089969389957472));
                break;
            }
            case 100: {
                Y = (int)Math.round(this.gnomon * Math.tan(1.3962634015954636));
                break;
            }
            case 50: {
                Y = (int)Math.round(this.gnomon * Math.tan(1.4835298641951802));
                break;
            }
        }
        nDays = 1;
        g.setColor(Color.darkGray);
        for (int m = 0; m < 12; ++m) {
            nDays += this.daysInMonth(m, this.year);
            g.drawLine(this.links + (int)Math.round(this.DX * nDays), this.y0, this.links + (int)Math.round(this.DX * nDays), this.y0 + Y + 5);
        }
        final int daysInYear = nDays;
        final int X = (int)Math.round(this.DX * daysInYear);
        int N = 1;
        g.setColor(Color.gray);
        for (int i = 0; i < 11; ++i) {
            N += this.daysInMonth(i, this.year);
            g.drawLine(this.links + (int)Math.round(this.DX * N + this.DX * 10.0), this.y0, this.links + (int)Math.round(this.DX * N + this.DX * 10.0), this.y0 + Y);
            g.drawLine(this.links + (int)Math.round(this.DX * N + this.DX * 20.0), this.y0, this.links + (int)Math.round(this.DX * N + this.DX * 20.0), this.y0 + Y);
        }
        g.drawLine(this.links + (int)Math.round(this.DX * 10.0), this.y0, this.links + (int)Math.round(this.DX * 10.0), this.y0 + Y);
        g.drawLine(this.links + (int)Math.round(this.DX * 20.0), this.y0, this.links + (int)Math.round(this.DX * 20.0), this.y0 + Y);
        if (this.dateLines) {
            N = 1;
            g.setColor(Color.lightGray);
            for (int j = 0; j < 11; ++j) {
                N += this.daysInMonth(j, this.year);
                g.drawLine(this.links + (int)Math.round(this.DX * N + this.DX * 5.0), this.y0, this.links + (int)Math.round(this.DX * N + this.DX * 5.0), this.y0 + Y);
                g.drawLine(this.links + (int)Math.round(this.DX * N + this.DX * 15.0), this.y0, this.links + (int)Math.round(this.DX * N + this.DX * 15.0), this.y0 + Y);
                g.drawLine(this.links + (int)Math.round(this.DX * N + this.DX * 25.0), this.y0, this.links + (int)Math.round(this.DX * N + this.DX * 25.0), this.y0 + Y);
            }
            g.drawLine(this.links + (int)Math.round(this.DX * 5.0), this.y0, this.links + (int)Math.round(this.DX * 5.0), this.y0 + Y);
            g.drawLine(this.links + (int)Math.round(this.DX * 15.0), this.y0, this.links + (int)Math.round(this.DX * 15.0), this.y0 + Y);
            g.drawLine(this.links + (int)Math.round(this.DX * 25.0), this.y0, this.links + (int)Math.round(this.DX * 25.0), this.y0 + Y);
        }
        g.setColor(Color.black);
        for (int j = 0; j < 12; ++j) {
            g.drawString(this.monthArray[j], this.links + (int)Math.round(this.DX * j * 30.0 + 13.0 * this.DX + j), this.y0 + Y + 15);
        }
        int dy = 10;
        g.setColor(Color.darkGray);
        for (int k = 0; k <= 4; ++k) {
            final int a = (int)Math.round(this.gnomon * Math.tan(0.017453292519943295 * k * dy));
            g.drawLine(this.links, this.y0 + a, this.links + (int)Math.round(this.DX * daysInYear), this.y0 + a);
            g.drawString(new StringBuffer().append(k * dy).append('°').toString(), this.links - 25, this.y0 + a + 2);
        }
        dy = 5;
        g.setColor(Color.gray);
        for (int l = 0; l <= 9; ++l) {
            final int a = (int)Math.round(this.gnomon * Math.tan(0.017453292519943295 * (40 + l * dy)));
            if (l % 2 == 1) {
                g.setColor(Color.gray);
            }
            else {
                g.setColor(Color.darkGray);
            }
            g.drawLine(this.links, this.y0 + a, this.links + (int)Math.round(this.DX * daysInYear), this.y0 + a);
            g.drawString(new StringBuffer().append(40 + l * dy).append('°').toString(), this.links - 25, this.y0 + a + 2);
        }
        g.setColor(Color.red);
        int a = (int)Math.round(this.gnomon * Math.tan(0.7853981633974483));
        g.drawLine(this.links - 3, this.y0 + a, this.links + (int)Math.round(this.DX * daysInYear) + 3, this.y0 + a);
        g.setFont(new Font("Helvetica", 0, 12));
        for (int gmt = -this.timezoneoffset; gmt < -this.timezoneoffset + 24; ++gmt) {
            n = 0;
            for (int m2 = 0; m2 < 12; ++m2) {
                for (int d = 1; d < this.daysInMonth(m2, this.year) + 1; ++d) {
                    ++n;
                    this.Jd = this.JD(d, m2 + 1, this.year + 1900, gmt);
                    this.dec = this.comp.sunDecRA(this.Jd, 1);
                    this.ra = this.comp.sunDecRA(this.Jd, 2);
                    this.gha = this.comp.sun_GHA(this.Jd, this.ra);
                    this.hoehe = this.comp.sun_elev(this.Jd, this.latitude, -this.longitude, this.dec, this.ra);
                    if (this.hoehe >= 0.0) {
                        this.hArray[n] = true;
                    }
                    else {
                        this.hArray[n] = false;
                    }
                    this.eq = this.eot(d, m2 + 1, this.year + 1900, gmt);
                    this.curTrans = 12.0 - this.eq / 60.0 - this.longitude / 15.0;
                    if (this.curTrans < gmt) {
                        this.red[n] = true;
                    }
                    else {
                        this.red[n] = false;
                    }
                    final double b = this.gnomon * Math.tan(0.017453292519943295 * this.hoehe);
                    this.yArray[n] = (int)Math.round(b);
                }
            }
            for (int i2 = 1; i2 < daysInYear - 1; ++i2) {
                if (this.red[i2]) {
                    g.setColor(Color.red);
                }
                else {
                    g.setColor(Color.blue);
                }
                if (this.hArray[i2]) {
                    if (Math.abs(this.yArray[i2] - this.yArray[i2 + 1]) < 200) {
                        g.drawLine(this.links + (int)Math.round(this.DX * i2), this.y0 + this.yArray[i2], this.links + (int)Math.round(this.DX * (i2 + 1)), this.y0 + this.yArray[i2 + 1]);
                    }
                    if (this.drawHours) {
                        if (this.red[i2] && (i2 == 160 || i2 == 250 || i2 == 70)) {
                            g.drawString(String.valueOf(gmt + this.timezoneoffset), this.links + (int)Math.round(this.DX * i2), this.y0 + this.yArray[i2]);
                        }
                        if (!this.red[i2] && (i2 == 176 || i2 == 267 || i2 == 93)) {
                            g.drawString(String.valueOf(gmt + this.timezoneoffset), this.links + (int)Math.round(this.DX * i2), this.y0 + this.yArray[i2]);
                        }
                    }
                }
            }
        }
        if (this.half) {
            for (int gmt2 = -this.timezoneoffset; gmt2 < -this.timezoneoffset + 24; ++gmt2) {
                n = 0;
                for (int m3 = 0; m3 < 12; ++m3) {
                    for (int d2 = 1; d2 < this.daysInMonth(m3, this.year) + 1; ++d2) {
                        ++n;
                        this.Jd = this.JD(d2, m3 + 1, this.year + 1900, gmt2 + 0.5);
                        this.dec = this.comp.sunDecRA(this.Jd, 1);
                        this.ra = this.comp.sunDecRA(this.Jd, 2);
                        this.gha = this.comp.sun_GHA(this.Jd, this.ra);
                        this.hoehe = this.comp.sun_elev(this.Jd, this.latitude, -this.longitude, this.dec, this.ra);
                        if (this.hoehe >= 0.0) {
                            this.hArray[n] = true;
                        }
                        else {
                            this.hArray[n] = false;
                        }
                        this.eq = this.eot(d2, m3 + 1, this.year + 1900, gmt2 + 0.5);
                        this.curTrans = 12.0 - this.eq / 60.0 - this.longitude / 15.0;
                        if (this.curTrans < gmt2) {
                            this.red[n] = true;
                        }
                        else {
                            this.red[n] = false;
                        }
                        final double b = this.gnomon * Math.tan(0.017453292519943295 * this.hoehe);
                        this.yArray[n] = (int)Math.round(b);
                    }
                }
                for (int i3 = 1; i3 < daysInYear - 1; ++i3) {
                    if (this.red[i3]) {
                        g.setColor(Color.magenta);
                    }
                    else {
                        g.setColor(Color.cyan);
                    }
                    if (this.latitude >= 0.0 && this.hArray[i3] && i3 > 80 && i3 < 280 && Math.abs(this.yArray[i3] - this.yArray[i3 + 1]) < 200) {
                        g.drawLine(this.links + (int)Math.round(this.DX * i3), this.y0 + this.yArray[i3], this.links + (int)Math.round(this.DX * (i3 + 1)), this.y0 + this.yArray[i3 + 1]);
                    }
                    if (this.latitude < 0.0 && this.hArray[i3] && (i3 < 80 || i3 > 280) && Math.abs(this.yArray[i3] - this.yArray[i3 + 1]) < 200) {
                        g.drawLine(this.links + (int)Math.round(this.DX * i3), this.y0 + this.yArray[i3], this.links + (int)Math.round(this.DX * (i3 + 1)), this.y0 + this.yArray[i3 + 1]);
                    }
                }
            }
        }
        if (this.transit) {
            n = 0;
            for (int m2 = 0; m2 < 12; ++m2) {
                for (int d = 1; d < this.daysInMonth(m2, this.year) + 1; ++d) {
                    ++n;
                    this.eq = this.eot(d, m2 + 1, this.year + 1900, 12.0);
                    this.curTrans = 12.0 - this.eq / 60.0 - this.longitude / 15.0;
                    this.Jd = this.JD(d, m2 + 1, this.year + 1900, this.curTrans);
                    this.dec = this.comp.sunDecRA(this.Jd, 1);
                    this.ra = this.comp.sunDecRA(this.Jd, 2);
                    this.gha = this.comp.sun_GHA(this.Jd, this.ra);
                    this.hoehe = this.comp.sun_elev(this.Jd, this.latitude, -this.longitude, this.dec, this.ra);
                    final double b = this.gnomon * Math.tan(0.017453292519943295 * this.hoehe);
                    this.trans[n] = (int)Math.round(b);
                }
            }
            g.setColor(Color.green);
            for (int i2 = 1; i2 < daysInYear - 1; ++i2) {
                g.drawLine(this.links + (int)Math.round(this.DX * i2), this.y0 + this.trans[i2], this.links + (int)Math.round(this.DX * (i2 + 1)), this.y0 + this.trans[i2 + 1]);
            }
        }
        g.clearRect(1, 80, X - 2, 10);
        if (this.sun) {
            nDays = 0;
            for (int m2 = 0; m2 < this.month; ++m2) {
                nDays += this.daysInMonth(m2, this.year);
            }
            nDays += this.date;
            if (this.month == 0) {
                nDays = this.date - 1;
            }
            g.drawLine(this.links + (int)Math.round(this.DX * nDays), this.y0, this.links + (int)Math.round(this.DX * nDays), this.y0 + Y);
            if (this.currentElev > 0.0) {
                a = (int)Math.round(this.gnomon * Math.tan(0.017453292519943295 * this.currentElev));
                g.setColor(Color.yellow);
                g.fillOval(this.links + (int)Math.round(this.DX * nDays) - 6, this.y0 + a - 6, 12, 12);
                g.setColor(Color.black);
                g.drawOval(this.links + (int)Math.round(this.DX * nDays) - 6, this.y0 + a - 6, 12, 12);
                g.drawLine(this.links + (int)Math.round(this.DX * nDays) - 6, this.y0 + a, this.links + (int)Math.round(this.DX * nDays) + 6, this.y0 + a);
                g.drawLine(this.links + (int)Math.round(this.DX * nDays), this.y0 + a - 6, this.links + (int)Math.round(this.DX * nDays), this.y0 + a + 6);
            }
            g.setFont(new Font("Courier", 0, 12));
            g.setColor(Color.red);
            g.drawString("Altitude = " + Math.round(10.0 * this.currentElev) / 10.0 + '°', this.size().width / 2 + 250, this.y0 + Y - 5);
        }
        g.setColor(Color.black);
        g.setFont(new Font("Helvetica", 0, 11));
        g.drawRect(this.links, this.y0, (int)Math.round(this.DX * daysInYear), Y + 20);
        g.drawString("0°", this.links - 25, this.y0 + 2);
        g.drawString(String.valueOf(this.versStr) + "  © 2006-2008 J.Giesen - www.GeoAstro.de", this.links + 15, this.y0 + Y - 5);
        g.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
        if (this.demo) {
            g.setFont(new Font("Chicago", 0, 124));
            g.setColor(Color.red);
            g.drawString("D  E  M  O", 150, 300);
        }
    }
    
    public void computeData() {
        this.dataStr[0] = "\n" + this.latStr + this.nsStr + ", " + this.longStr + this.ewStr;
        final String[] dataStr = this.dataStr;
        final int n2 = 0;
        dataStr[n2] = String.valueOf(dataStr[n2]) + "\n         UT    Alt.    EoT";
        int n = 0;
        for (int m = 0; m < 12; ++m) {
            for (int d = 1; d < this.daysInMonth(m, this.year) + 1; ++d) {
                ++n;
                double eqt = this.eot(d, m + 1, this.year + 1900, 12.0);
                final double trans = 12.0 - eqt / 60.0 - this.longitude / 15.0;
                final double Jd = this.JD(d, m + 1, this.year + 1900, this.curTrans);
                final double dec = this.comp.sunDecRA(Jd, 1);
                final double ra = this.comp.sunDecRA(Jd, 2);
                final double gha = this.comp.sun_GHA(Jd, ra);
                final double hoehe = this.comp.sun_elev(Jd, this.latitude, -this.longitude, dec, ra);
                String str1;
                if (d < 10) {
                    str1 = "0" + d;
                }
                else {
                    str1 = String.valueOf(d);
                }
                final String str2 = this.comp.makeTimeString(trans);
                final String str3 = String.valueOf(Math.round(10.0 * hoehe) / 10.0);
                eqt = this.eot(d, m + 1, this.year + 1900, trans);
                final String str4 = String.valueOf(Math.round(10.0 * eqt) / 10.0);
                this.dataStr[n] = String.valueOf(this.monthArray[m]) + " " + str1 + "  " + str2 + "  " + str3 + "   " + str4;
            }
        }
        this.nData = n;
    }
    
    public int calDat(final int what, final double jd) {
        final double JD0 = (int)(jd + 0.5);
        final int B = (int)((JD0 - 1867216.25) / 36524.25);
        final double C = JD0 + B - B / 4 + 1525.0;
        final int D = (int)((C - 122.1) / 365.25);
        final double E = 365.0 * D + D / 4;
        final int F = (int)((C - E) / 30.6001);
        final int day = (int)(C - E + 0.5) - (int)(30.6001 * F);
        if (what == 1) {
            return day;
        }
        final int month = F - 1 - 12 * (F / 14);
        final int year = D - 4715 - (7 + month) / 10;
        double hour = 24.0 * (jd + 0.5 - JD0);
        final double diff = Math.abs(hour) - (int)Math.abs(hour);
        int min = (int)Math.round(diff * 60.0);
        if (min == 60) {
            min = 0;
            ++hour;
        }
        String str;
        if (min > 9) {
            str = ":";
        }
        else {
            str = ":0";
        }
        String hourStr = String.valueOf((int)hour) + str + min;
        if ((int)hour < 10) {
            hourStr = "0" + hourStr;
        }
        hourStr = String.valueOf(hourStr) + " UT";
        if (what == 2) {
            return month;
        }
        if (what == 3) {
            return year;
        }
        if (what == 4) {
            return (int)hour;
        }
        if (what == 5) {
            return min;
        }
        return 0;
    }
    
    public boolean keyDown(final Event event, final int code) {
        int n = 0;
        if (code != 104 && code != 100 && code != 109 && code != 72 && code != 68 && code != 77 && code != 119 && code != 87 && code != 115 && code != 83) {
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
            this.timezoneoffset = -this.dat.getTimezoneOffset() / 60;
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
            this.timezoneoffset = -this.dat.getTimezoneOffset() / 60;
            this.repaint();
            return true;
        }
        if (code == 119 || code == 87) {
            double Jd = this.JD(this.date, this.month + 1, this.year + 1900, this.hours - this.timezoneoffset + this.minutes / 60.0 + this.seconds / 3600.0);
            if (code == 119) {
                Jd += 7.0;
            }
            else {
                Jd -= 7.0;
            }
            this.date = this.calDat(1, Jd);
            this.month = this.calDat(2, Jd) - 1;
            this.year = this.calDat(3, Jd) - 1900;
            this.dat.setDate(this.date);
            this.dateChoice.select(this.date - 1);
            this.dat.setMonth(this.month);
            this.monthChoice.select(this.month);
            this.dat.setYear(this.year);
            this.yearStr = String.valueOf(this.year + 1900);
            this.yearChoice.select(this.yearStr);
            this.timezoneoffset = -this.dat.getTimezoneOffset() / 60;
            this.repaint();
            return true;
        }
        if (code == 115 || code == 83) {
            double Jd = this.JD(this.date, this.month + 1, this.year + 1900, this.hours + this.minutes / 60.0);
            if (code == 115) {
                Jd += 6.944444444444445E-4;
            }
            else {
                Jd -= 6.944444444444445E-4;
            }
            this.date = this.calDat(1, Jd);
            this.month = this.calDat(2, Jd) - 1;
            this.year = this.calDat(3, Jd) - 1900;
            this.hours = this.calDat(4, Jd);
            if (this.hours < 24) {
                this.hoursChoice.select(this.hours);
            }
            else {
                this.hours = 0;
                this.hoursChoice.select(0);
            }
            this.dat.setHours(this.hours);
            this.minutes = this.calDat(5, Jd);
            this.minChoice.select(this.minutes);
            this.dat.setMinutes(this.minutes);
            this.dat.setSeconds(0);
            this.dat.setDate(this.date);
            this.dateChoice.select(this.date - 1);
            this.dat.setMonth(this.month);
            this.monthChoice.select(this.month);
            this.dat.setYear(this.year);
            this.yearStr = String.valueOf(this.year + 1900);
            this.yearChoice.select(this.yearStr);
            this.repaint();
            return true;
        }
        return true;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        final int code = 0;
        final double oldLat = this.latitude;
        final double oldLong = this.longitude;
        if (event.target == this.aboutButton) {
            final Dialog AboutDialog = new Aboutdialog(this, this.versStr, this.demo);
            AboutDialog.resize(350, 230);
            AboutDialog.show();
        }
        if (event.target instanceof TextField) {
            this.str = "";
            if (event.target == this.fieldLatDeg) {
                this.str = this.fieldLatDeg.getText();
                for (int i = 0; i < this.str.length(); ++i) {
                    final char c = this.str.charAt(i);
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
                final Double latDouble = Double.valueOf(this.latStr);
                this.latitude = latDouble;
                if (this.nsChoice.getSelectedItem().equals("S")) {
                    this.latitude = -Math.abs(this.latitude);
                    this.usrLat = this.latitude;
                }
                this.fieldLatDeg.setText(this.str);
                if (Math.abs(this.latitude) >= 90.0) {
                    if (this.latitude == 90.0) {
                        this.latitude = 89.9999;
                    }
                    if (this.latitude == -90.0) {
                        this.latitude = -89.9999;
                    }
                    if (this.latitude > 90.0) {
                        this.latitude = oldLat;
                    }
                    if (this.latitude < -90.0) {
                        this.latitude = oldLat;
                    }
                    this.latStr = String.valueOf(Math.abs(Math.round(1000.0 * this.latitude) / 1000.0));
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
                }
                this.fieldLatDeg.nextFocus();
                this.fieldLongDeg.selectAll();
                this.repaint();
                return true;
            }
            if (event.target == this.fieldLongDeg) {
                this.str = this.fieldLongDeg.getText();
                for (int i = 0; i < this.str.length(); ++i) {
                    final char c = this.str.charAt(i);
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
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                }
                this.repaint();
                return true;
            }
        }
        if (event.target instanceof Checkbox && event.target == this.sunBox) {
            if (this.sunBox.getState()) {
                this.sun = true;
            }
            else {
                this.sun = false;
            }
            this.repaint();
            return true;
        }
        if (event.target instanceof Choice) {
            if (event.target == this.gnomonChoice) {
                if (this.gnomonChoice.getSelectedItem().equals("300")) {
                    this.gnomon = 300;
                }
                if (this.gnomonChoice.getSelectedItem().equals("275")) {
                    this.gnomon = 275;
                }
                if (this.gnomonChoice.getSelectedItem().equals("250")) {
                    this.gnomon = 250;
                }
                if (this.gnomonChoice.getSelectedItem().equals("200")) {
                    this.gnomon = 200;
                }
                if (this.gnomonChoice.getSelectedItem().equals("150")) {
                    this.gnomon = 150;
                }
                if (this.gnomonChoice.getSelectedItem().equals("100")) {
                    this.gnomon = 100;
                }
                if (this.gnomonChoice.getSelectedItem().equals("50")) {
                    this.gnomon = 50;
                }
                this.repaint();
            }
            if (event.target == this.showChoice) {
                if (this.showChoice.getSelectedItem().equals("Select...")) {
                    this.repaint();
                }
                if (this.showChoice.getSelectedItem().equals("Transit on/off")) {
                    this.transit ^= true;
                    this.showChoice.select(0);
                    this.repaint();
                }
                if (this.showChoice.getSelectedItem().equals("Hours on/off")) {
                    this.drawHours ^= true;
                    this.showChoice.select(0);
                    this.repaint();
                }
                if (this.showChoice.getSelectedItem().equals("Half Hours on/off")) {
                    this.half ^= true;
                    this.showChoice.select(0);
                    this.repaint();
                }
                if (this.showChoice.getSelectedItem().equals("Date Lines on/off")) {
                    this.dateLines ^= true;
                    this.showChoice.select(0);
                    this.repaint();
                }
                if (this.showChoice.getSelectedItem().equals("Transit Data")) {
                    this.computeData();
                    String titleStr = "Transit " + this.latStr + '°' + this.nsStr + ", " + this.longStr + '°' + this.ewStr;
                    titleStr = String.valueOf(titleStr) + ", " + (this.year + 1900);
                    boolean dem = false;
                    if (this.online || this.demo) {
                        dem = true;
                    }
                    final Frame df = new dataFrame(titleStr, this.versStr, this.dataStr, this.nData, dem);
                    df.resize(300, 435);
                    df.show();
                    this.showChoice.select(0);
                }
                if (this.showChoice.getSelectedItem().equals("Rise/Set Data")) {
                    this.doRiseSet();
                    String titleStr = "Rise/Set " + this.latStr + '°' + this.nsStr + ", " + this.longStr + '°' + this.ewStr;
                    titleStr = String.valueOf(titleStr) + ", " + (this.year + 1900);
                    boolean dem = false;
                    if (this.online || this.demo) {
                        dem = true;
                    }
                    final Frame df = new dataFrame(titleStr, this.versStr, this.dataStr, this.nData, dem);
                    df.resize(300, 435);
                    df.show();
                    this.showChoice.select(0);
                }
                if (this.showChoice.getSelectedItem().equals("Now")) {
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
                    this.timezoneoffset = -this.dat.getTimezoneOffset() / 60;
                    this.repaint();
                    this.showChoice.select(0);
                }
            }
            if (event.target == this.locChoice) {
                if (this.locChoice.getSelectedItem().equals("---------------")) {
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
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                    this.usrLat = this.latitude;
                    this.usrLong = this.longitude;
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
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                    if (this.latitude == 90.0) {
                        this.latitude = 89.999;
                    }
                    if (this.latitude == -90.0) {
                        this.latitude = -89.999;
                    }
                    this.repaint();
                    return true;
                }
                if (event.target == this.timeChoice) {
                    this.timeString = this.timeChoice.getSelectedItem();
                    this.timezoneoffset = this.loc.getTimeZone(this.timeString);
                    this.fieldLatDeg.setText(this.latStr);
                    this.fieldLongDeg.setText(this.longStr);
                    this.repaint();
                    return true;
                }
                if (event.target == this.hoursChoice) {
                    this.hoursStr = this.hoursChoice.getSelectedItem();
                    this.hours = Integer.parseInt(this.hoursStr);
                    this.hoursChoice.select(this.hoursStr);
                    this.dat.setHours(this.hours);
                    this.dat.setSeconds(0);
                    this.repaint();
                    return true;
                }
                if (event.target == this.minChoice) {
                    this.minStr = this.minChoice.getSelectedItem();
                    this.minutes = Integer.parseInt(this.minStr);
                    this.minChoice.select(this.minStr);
                    this.dat.setMinutes(this.minutes);
                    this.dat.setSeconds(0);
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
                    this.repaint();
                    return true;
                }
                if (event.target == this.yearChoice) {
                    this.yearStr = this.yearChoice.getSelectedItem();
                    this.yearChoice.select(this.yearStr);
                    this.year = Integer.parseInt(this.yearStr);
                    this.yearChoice.select(this.yearStr);
                    this.year -= 1900;
                    this.dat.setYear(this.year);
                    this.repaint();
                    return true;
                }
            }
        }
        return true;
    }
    
    public cylinder075() {
        this.versStr = "Cylinder Sundial v. 0.75 ";
        this.demo = true;
        this.online = false;
        this.sun = true;
        this.usrLat = 0.0;
        this.usrLong = 0.0;
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.usrStr = "demo";
        this.yArray = new int[370];
        this.hArray = new boolean[370];
        this.dataStr = new String[370];
        this.red = new boolean[370];
        this.trans = new int[370];
        this.gnomon = 200;
        this.transit = true;
        this.links = 40;
        this.y0 = 90;
        this.drawHours = true;
        this.half = false;
        this.dateLines = true;
    }
}
