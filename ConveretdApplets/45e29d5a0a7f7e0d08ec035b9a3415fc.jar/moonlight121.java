import java.awt.Font;
import java.awt.Graphics;
import java.awt.Event;
import java.net.URL;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Button;
import java.awt.TextField;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class moonlight121 extends Applet
{
    String versStr;
    char deg;
    final double K = 0.017453292519943295;
    Date dat;
    TextField latField;
    TextField longField;
    double latitude;
    double longitude;
    String latStr;
    String longStr;
    Double latDouble;
    Double longDouble;
    Button button;
    boolean demo;
    boolean online;
    int date;
    int month;
    int year;
    double UT;
    double azim;
    double elev;
    double jd;
    Moon moon;
    double ra;
    double dec;
    double h;
    double gha;
    String error;
    double locOffset;
    double currentPhase;
    double currentAzim;
    String delta;
    String riseStr;
    String setStr;
    graphicFrame gf;
    String yearStr;
    int[][][] p;
    Choice dateChoice;
    Choice monthChoice;
    Choice yearChoice;
    Choice utChoice;
    String[] monthArray;
    boolean weekday;
    Checkbox box;
    boolean vis;
    Checkbox weekdayBox;
    Choice shapeChoice;
    boolean oval;
    
    public void init() {
        System.out.println(String.valueOf(this.versStr) + "  (c) 2006-2008 J. Giesen  -  www. GeoAstro.de");
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        final URL url = this.getDocumentBase();
        String myStr = url.toString();
        myStr = String.valueOf(myStr) + "1234567890123456789012345";
        final String wwwStr = myStr.substring(0, 27);
        this.dat = new Date();
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth() + 1;
        this.year = this.dat.getYear() + 1900;
        this.UT = this.dat.getHours() + this.dat.getTimezoneOffset() / 60.0 + this.dat.getMinutes() / 60.0 + this.dat.getSeconds() / 3600.0;
        this.UT = Math.floor(this.UT);
        this.locOffset = -this.dat.getTimezoneOffset() / 60;
        final double jd = this.JD(this.date, this.month, this.year, this.UT);
        this.date = this.caldat(1, jd);
        this.month = this.caldat(2, jd);
        this.year = this.caldat(3, jd);
        if (this.UT > 24.0) {
            this.UT -= 24.0;
        }
        if (this.UT < 0.0) {
            this.UT += 24.0;
        }
        this.latStr = this.getParameter("latitude");
        this.longStr = this.getParameter("longitude");
        this.latDouble = Double.valueOf(this.latStr);
        this.latitude = this.latDouble;
        this.longDouble = Double.valueOf(this.longStr);
        this.longitude = this.longDouble;
        this.setBackground(Color.white);
        gbc.insets = new Insets(0, 0, 0, 0);
        final Label latLabel = new Label("Lat.");
        gbl.setConstraints(latLabel, gbc);
        this.add(latLabel);
        gbl.setConstraints(this.latField = new TextField(this.latStr, 6), gbc);
        this.add(this.latField);
        final Label longLabel = new Label("Long.");
        gbl.setConstraints(longLabel, gbc);
        this.add(longLabel);
        this.longField = new TextField(this.longStr, 6);
        gbc.insets = new Insets(0, 0, 0, 10);
        gbl.setConstraints(this.longField, gbc);
        gbc.insets = new Insets(0, 0, 0, 0);
        this.add(this.longField);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 0, 0);
        final Label L1 = new Label("Date  ");
        gbl.setConstraints(L1, gbc);
        this.add(L1);
        gbc.insets = new Insets(0, 0, 0, 10);
        final Label L2 = new Label("  Month  ");
        gbl.setConstraints(L2, gbc);
        this.add(L2);
        final Label L3 = new Label("   Year  ");
        gbl.setConstraints(L3, gbc);
        this.add(L3);
        final Label L4 = new Label(" UT");
        gbl.setConstraints(L4, gbc);
        this.add(L4);
        gbc.gridy = 2;
        gbc.gridx = 0;
        this.dateChoice = new Choice();
        for (int i = 1; i < 32; ++i) {
            this.dateChoice.addItem(String.valueOf(i));
        }
        this.dateChoice.select(this.date - 1);
        gbc.insets = new Insets(0, 10, 0, 0);
        gbl.setConstraints(this.dateChoice, gbc);
        this.add(this.dateChoice);
        gbc.gridx = 1;
        this.monthChoice = new Choice();
        for (int j = 0; j < 12; ++j) {
            this.monthChoice.addItem(this.monthArray[j]);
        }
        this.monthChoice.select(this.month - 1);
        gbc.insets = new Insets(0, 0, 0, 0);
        gbl.setConstraints(this.monthChoice, gbc);
        this.add(this.monthChoice);
        gbc.gridx = 2;
        this.yearChoice = new Choice();
        gbc.insets = new Insets(0, 0, 0, 0);
        gbl.setConstraints(this.yearChoice, gbc);
        for (int k = -10; k < 11; ++k) {
            this.yearStr = String.valueOf(this.year + k);
            this.yearChoice.addItem(this.yearStr);
        }
        this.yearStr = String.valueOf(this.year);
        this.yearChoice.select(this.yearStr);
        this.add(this.yearChoice);
        gbc.gridx = 3;
        this.utChoice = new Choice();
        gbc.insets = new Insets(0, 0, 0, 10);
        gbl.setConstraints(this.utChoice, gbc);
        for (int l = 0; l < 24; ++l) {
            this.utChoice.addItem(String.valueOf(l));
        }
        this.utChoice.select((int)this.UT);
        this.add(this.utChoice);
        gbc.gridx = 0;
        gbc.gridy = 3;
        this.button = new Button("Table");
        gbc.insets = new Insets(0, 10, 0, 0);
        gbl.setConstraints(this.button, gbc);
        this.add(this.button);
        gbc.gridx = 1;
        gbl.setConstraints(this.box = new Checkbox("Visibility"), gbc);
        this.box.setState(this.vis);
        this.add(this.box);
        gbc.gridx = 2;
        (this.shapeChoice = new Choice()).add("Circle");
        this.shapeChoice.add("Square");
        gbl.setConstraints(this.shapeChoice, gbc);
        this.add(this.shapeChoice);
        gbc.gridx = 3;
        gbl.setConstraints(this.weekdayBox = new Checkbox("Weekday"), gbc);
        this.weekdayBox.setState(this.weekday);
        this.add(this.weekdayBox);
        gbc.gridy = 5;
        final Canvas can = new Canvas();
        gbc.insets = new Insets(0, 0, 80, 0);
        gbl.setConstraints(can, gbc);
        this.add(can);
        boolean ok = true;
        final String email = this.getParameter("email");
        final String param = this.getParameter("password");
        if (this.formula(wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(wwwStr, 22) == this.formula("http://www.geoastro.de", 22) || this.formula(wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(wwwStr, 21) == this.formula("http://www.j-giesen.de", 21)) {
            ok = true;
            this.online = true;
            this.demo = false;
        }
        else {
            ok = false;
        }
        if (!ok) {
            ok = true;
            if (email.length() == 0 || Integer.parseInt(param) != this.formula(email, email.length())) {
                ok = false;
            }
            else {
                ok = true;
                this.demo = false;
            }
            if (wwwStr.substring(0, 7).equals("http://")) {
                ok = false;
                this.demo = true;
            }
        }
        if (this.demo) {
            this.versStr = String.valueOf(this.versStr) + " DEMO";
        }
        this.calculate();
        this.repaint();
    }
    
    public int caldat(final int what, final double JD) {
        final double JD2 = (int)(JD + 0.5);
        final int B = (int)((JD2 - 1867216.25) / 36524.25);
        final double C = JD2 + B - B / 4 + 1525.0;
        final int D = (int)((C - 122.1) / 365.25);
        final double E = 365.0 * D + D / 4;
        final int F = (int)((C - E) / 30.6001);
        final int day = (int)(C - E + 0.5) - (int)(30.6001 * F);
        final int month = F - 1 - 12 * (F / 14);
        final int year = D - 4715 - (7 + month) / 10;
        if (what == 1) {
            return day;
        }
        if (what == 2) {
            return month;
        }
        return year;
    }
    
    public int weekDay(final double jd) {
        long num = (long)(jd + 0.5);
        num -= num / 7L * 7L;
        return (int)num;
    }
    
    public double getLatitude(String str) {
        if (!str.equals("-")) {
            for (int i = 0; i < str.length(); ++i) {
                final char c = str.charAt(i);
                if (c == ',') {
                    str = String.valueOf(str.substring(0, i)) + '.' + str.substring(i + 1, str.length());
                }
            }
            for (int j = 0; j < str.length(); ++j) {
                final char c = str.charAt(j);
                if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != '.' && c != '-') {
                    this.error = "input error";
                }
                else {
                    try {
                        this.latDouble = Double.valueOf(str);
                        this.latitude = this.latDouble;
                        if (this.latitude > 90.0) {
                            this.latitude = 90.0;
                        }
                        if (this.latitude < -90.0) {
                            this.latitude = -90.0;
                        }
                    }
                    catch (NumberFormatException ex) {
                        this.error = "input error";
                    }
                }
            }
        }
        return this.latitude;
    }
    
    public double getLongitude(String str) {
        if (!str.equals("-")) {
            for (int i = 0; i < str.length(); ++i) {
                final char c = str.charAt(i);
                if (c == ',') {
                    str = String.valueOf(str.substring(0, i)) + '.' + str.substring(i + 1, str.length());
                }
            }
            for (int j = 0; j < str.length(); ++j) {
                final char c = str.charAt(j);
                if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != '.' && c != '-') {
                    this.error = "input error";
                }
                else {
                    try {
                        this.longDouble = Double.valueOf(str);
                        this.longitude = this.longDouble;
                        if (this.longitude > 180.0) {
                            this.longitude = 180.0;
                        }
                        if (this.longitude < -180.0) {
                            this.longitude = -180.0;
                        }
                    }
                    catch (NumberFormatException ex) {
                        this.error = "input error";
                    }
                }
            }
        }
        return this.longitude;
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
    
    double JD(final int date, int month, int year, final double UT) {
        if (month <= 2) {
            month += 12;
            --year;
        }
        final int A = (int)(year / 100.0);
        final int B = 2 - A + (int)(A / 4.0);
        return (int)(365.25 * (year + 4716)) + (int)(30.6001 * (month + 1)) + date + B - 1524.5 + UT / 24.0;
    }
    
    public String HMS(double x) {
        String str = "";
        x /= 15.0;
        if (x < 0.0) {
            x = Math.abs(x);
        }
        final int HOUR = (int)x;
        final int MIN = (int)(this.frac(x) * 60.0);
        final double SEC = 60.0 * (this.frac(x) * 60.0 - MIN);
        if (HOUR < 10) {
            str = " ";
        }
        str = String.valueOf(str) + HOUR + "h ";
        if (MIN < 10) {
            str = String.valueOf(str) + " ";
        }
        str = String.valueOf(str) + MIN + "m ";
        if ((int)Math.round(SEC) < 10) {
            str = String.valueOf(str) + " ";
        }
        return String.valueOf(str) + Math.round(SEC) + "s";
    }
    
    public double frac(double X) {
        X -= (int)X;
        if (X < 0.0) {
            ++X;
        }
        return X;
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
    
    public String tierkreisStr(final double longitudo) {
        final String[] tk = { "Aries", "Taurus", "Gemini", "Cancer", "Leo", "Virgo", "Libra", "Scorpio", "Sagittarius", "Capricornus", "Aquarius", "Pisces" };
        return tk[(int)(longitudo / 30.0)];
    }
    
    public void calculate() {
        this.error = "";
        this.latStr = this.latField.getText();
        this.latitude = this.getLatitude(this.latStr);
        this.longStr = this.longField.getText();
        this.longitude = this.getLongitude(this.longStr);
        System.out.println("Lat. " + this.latitude);
        System.out.println("Long. " + this.longitude);
        System.out.println("Date " + this.date);
        System.out.println("Month " + this.month);
        System.out.println("Year " + this.year);
        System.out.println("UT " + this.UT);
        this.jd = this.JD(this.date, this.month, this.year, this.UT);
        System.out.println("Jul. Day " + this.jd);
        final Date calcDat = new Date();
        calcDat.setDate(this.date);
        calcDat.setMonth(this.month - 1);
        calcDat.setYear(this.year - 1900);
        calcDat.setHours((int)(this.UT + this.locOffset));
        calcDat.setMinutes((int)(60.0 * this.frac(this.UT)));
        calcDat.setSeconds((int)(60.0 * this.frac(60.0 * this.frac(this.UT))));
        this.moon = new Moon(calcDat, this.latitude, this.longitude, this.locOffset);
        this.currentPhase = this.moon.phase();
        System.out.println(calcDat.toString());
        final double eclLong = this.moon.moonLong();
        System.out.println("Ecl. Longit. " + Math.round(10.0 * eclLong) / 10.0);
        System.out.println(String.valueOf(this.tierkreisStr(eclLong)) + " " + Math.round(10.0 * (eclLong % 30.0)) / 10.0);
        this.riseStr = this.moon.riseString();
        this.setStr = this.moon.setString();
        System.out.println("Rise " + this.moon.riseString());
        System.out.println("Set  " + this.moon.setString());
        this.ra = this.moon.alpha();
        this.dec = this.moon.delta();
        final double moonHours = this.moon.moon_hRise() - this.moon.moon_hSet();
        System.out.println("Moonlight Hours " + Math.round(100.0 * moonHours) / 100.0);
        System.out.println("RA (deg) " + Math.round(100.0 * this.ra) / 100.0);
        System.out.println("RA (h)   " + this.HMS(this.ra));
        System.out.println("Declin. " + Math.round(100.0 * this.dec) / 100.0);
        this.h = this.THETA0(this.jd) + this.longitude - this.ra;
        if (this.h < 0.0) {
            this.h += 360.0;
        }
        this.gha = this.THETA0(this.jd) - this.ra;
        if (this.gha < 0.0) {
            this.gha += 360.0;
        }
        System.out.println("Greenwich Sid. Time (deg) " + Math.round(100.0 * this.THETA0(this.jd)) / 100.0);
        System.out.println("Greenwich Sid. Time       " + this.HMS(this.THETA0(this.jd)));
        System.out.println("Hour angle " + Math.round(100.0 * this.h) / 100.0);
        this.azim = Math.atan2(Math.sin(0.017453292519943295 * this.h), Math.cos(0.017453292519943295 * this.h) * Math.sin(0.017453292519943295 * this.latitude) - Math.tan(0.017453292519943295 * this.dec) * Math.cos(0.017453292519943295 * this.latitude)) / 0.017453292519943295;
        this.azim += 180.0;
        if (this.azim > 360.0) {
            this.azim -= 360.0;
        }
        this.elev = Math.sin(0.017453292519943295 * this.latitude) * Math.sin(0.017453292519943295 * this.dec) + Math.cos(0.017453292519943295 * this.latitude) * Math.cos(0.017453292519943295 * this.dec) * Math.cos(0.017453292519943295 * this.h);
        this.elev = Math.asin(this.elev) / 0.017453292519943295;
        double horParal = 3288.90917195;
        double paral = Math.cos(0.017453292519943295 * this.elev) * Math.sin(0.017453292519943295 * horParal / 3600.0);
        paral = Math.asin(paral) / 0.017453292519943295;
        this.elev -= paral;
        this.moon = new Moon(calcDat, this.latitude, this.longitude, this.locOffset);
        final double ph1 = this.moon.phase1();
        final double ph2 = this.moon.phase();
        final double ph3 = this.moon.phase2();
        if (ph2 > ph1 && ph2 < ph3) {
            this.delta = "(+)";
        }
        if (ph2 < ph1 && ph2 > ph3) {
            this.delta = "(-)";
        }
        for (int i = 1; i <= 31; ++i) {
            for (int m = 1; m <= 12; ++m) {
                if (i <= this.daysInMonth(m - 1, this.year - 1900)) {
                    calcDat.setDate(i);
                    calcDat.setMonth(m - 1);
                    calcDat.setHours((int)this.locOffset);
                    calcDat.setMinutes(0);
                    calcDat.setSeconds(0);
                    final double jD = this.JD(i, m, this.year, this.UT);
                    this.moon = new Moon(calcDat, this.latitude, this.longitude, this.locOffset);
                    this.p[i][m][0] = (int)Math.round(255.0 * this.moon.phase());
                    this.p[i][m][1] = this.weekDay(jD);
                    this.ra = this.moon.alpha();
                    this.dec = this.moon.delta();
                    this.h = this.THETA0(jD) + this.longitude - this.ra;
                    double ELEV = Math.sin(0.017453292519943295 * this.latitude) * Math.sin(0.017453292519943295 * this.dec) + Math.cos(0.017453292519943295 * this.latitude) * Math.cos(0.017453292519943295 * this.dec) * Math.cos(0.017453292519943295 * this.h);
                    ELEV = Math.asin(ELEV) / 0.017453292519943295;
                    horParal = 3288.90917195;
                    paral = Math.cos(0.017453292519943295 * ELEV) * Math.sin(0.017453292519943295 * horParal / 3600.0);
                    paral = Math.asin(paral) / 0.017453292519943295;
                    ELEV -= paral;
                    this.p[i][m][2] = (int)Math.round(ELEV);
                }
                else {
                    this.p[i][m][0] = 300;
                }
            }
        }
        this.repaint();
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof Checkbox) {
            if (event.target == this.box) {
                this.vis ^= true;
                this.calculate();
                return true;
            }
            if (event.target == this.weekdayBox) {
                this.weekday ^= true;
                this.calculate();
                return true;
            }
        }
        if (event.target instanceof TextField && (event.target == this.latField || event.target == this.longField)) {
            this.calculate();
            return true;
        }
        if (event.target instanceof Choice) {
            if (event.target == this.shapeChoice) {
                this.oval ^= true;
                this.calculate();
                return true;
            }
            if (event.target == this.yearChoice) {
                this.year = Integer.parseInt(this.yearChoice.getSelectedItem());
                this.calculate();
                return true;
            }
            if (event.target == this.monthChoice) {
                this.month = this.monthChoice.getSelectedIndex() + 1;
                if (this.date > this.daysInMonth(this.month - 1, this.year - 1900)) {
                    this.date = this.daysInMonth(this.month - 1, this.year - 1900);
                    this.dateChoice.select(this.date - 1);
                }
                this.calculate();
                return true;
            }
            if (event.target == this.dateChoice) {
                this.date = Integer.parseInt(this.dateChoice.getSelectedItem());
                if (this.date > this.daysInMonth(this.month - 1, this.year - 1900)) {
                    this.date = this.daysInMonth(this.month - 1, this.year - 1900);
                    this.dateChoice.select(this.date - 1);
                }
                this.calculate();
                return true;
            }
            if (event.target == this.utChoice) {
                this.UT = this.utChoice.getSelectedIndex();
                this.calculate();
                return true;
            }
        }
        if (event.target instanceof Button && event.target == this.button) {
            this.UT = Integer.parseInt(this.utChoice.getSelectedItem());
            this.latStr = this.latField.getText();
            this.latitude = this.getLatitude(this.latStr);
            this.longStr = this.longField.getText();
            this.longitude = this.getLongitude(this.longStr);
            this.calculate();
            boolean demoVersion = false;
            if (this.online || this.demo) {
                demoVersion = true;
            }
            if (this.gf == null) {
                (this.gf = new graphicFrame("Moonlight " + this.year + " at " + (int)Math.round(this.UT) + " UT", this.p, demoVersion, this.weekday, this.vis, this.oval)).resize(990, 600);
                this.gf.show();
            }
            else {
                this.gf.dispose();
                (this.gf = new graphicFrame("Moonlight " + this.year + " at " + (int)Math.round(this.UT) + " UT", this.p, demoVersion, this.weekday, this.vis, this.oval)).resize(990, 600);
                this.gf.show();
            }
            return true;
        }
        return true;
    }
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 8067;
    }
    
    public void paint(final Graphics g) {
        g.setFont(new Font("Courier", 0, 12));
        g.setColor(Color.red);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        g.setColor(Color.black);
        final int oben = 180;
        this.currentAzim = this.azim;
        if (!this.demo) {
            g.drawString("Altitude: " + Math.round(10.0 * this.elev) / 10.0 + this.deg, 30, oben);
        }
        else {
            g.drawString("Altitude: DEMO", 30, oben);
        }
        g.drawString("Azimuth : " + Math.round(10.0 * this.azim) / 10.0 + this.deg, 30, oben + 15);
        g.drawString("Rise:     " + this.riseStr, 30, oben + 30);
        g.drawString("Set:      " + this.setStr, 30, oben + 45);
        g.drawString("Illum. Fraction: " + Math.round(1000.0 * this.currentPhase) / 10.0 + " % " + this.delta, 30, oben + 60);
        g.drawString(this.error, 30, oben + 75);
        g.setFont(new Font("Courier", 0, 10));
        g.drawString(String.valueOf(this.versStr) + " © 2006-2008 J.Giesen - www.GeoAstro.de", 15, this.size().height - 10);
    }
    
    public moonlight121() {
        this.versStr = "MoonLight 1.21";
        this.deg = '°';
        this.demo = true;
        this.online = false;
        this.error = "";
        this.delta = "(?)";
        this.p = new int[32][13][3];
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.weekday = true;
        this.vis = false;
        this.oval = true;
    }
}
