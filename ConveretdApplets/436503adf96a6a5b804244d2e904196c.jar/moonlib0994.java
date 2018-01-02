import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Event;
import java.net.URL;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Choice;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class moonlib0994 extends Applet
{
    String versStr;
    final double K = 0.017453292519943295;
    final char deg = '°';
    Date dat;
    int date;
    int month;
    int year;
    int hours;
    int minutes;
    Choice yearChoice;
    Choice monthChoice;
    Choice dateChoice;
    Choice hoursChoice;
    Choice detailsChoice;
    Button nowButton;
    String yearStr;
    String hoursStr;
    String myStr;
    boolean demo;
    boolean online;
    public String email;
    public String param;
    public String wwwStr;
    public String usrStr;
    public String userString;
    String[] monthArray;
    int timezoneoffset;
    int margin;
    int r;
    double UT;
    double jd;
    double distance;
    double winkel;
    double X;
    double Y;
    double radius;
    String[] Data;
    int nData;
    MoonDistance moonDist;
    PlanetRiseSet planetRS;
    compute comp;
    Moon moon;
    double x1;
    double x2;
    int x;
    int y;
    int X1;
    int X2;
    double phase;
    int oben;
    boolean meanOK;
    boolean MinMaxOK;
    double latitude;
    double longitude;
    double RA;
    double dec;
    double elev;
    double lambda;
    double waxwan;
    double earthGeoLambda;
    TextField fieldLat;
    TextField fieldLong;
    Label L1;
    Label L2;
    String homeLatStr;
    String homeLongStr;
    boolean LatLong;
    int[] XX;
    int[] YY;
    boolean libPathYearOK;
    boolean libPathMonthOK;
    final double inclination = 1.54242;
    double T;
    double omega;
    double W;
    double beta;
    double F;
    double A;
    double B;
    double Ls;
    double pAngleAxis;
    boolean axisOK;
    double[] libL;
    double[] libB;
    int red;
    int green;
    int blue;
    boolean grid;
    boolean paralAngle;
    Choice minChoice;
    final int x0 = 200;
    final int X0 = 595;
    int y0;
    
    public void init() {
        this.comp = new compute();
        this.setFont(new Font("Helvetica", 0, 12));
        this.y0 = this.size().height / 2 + 15;
        this.setBackground(new Color(150, 255, 255));
        this.homeLatStr = this.getParameter("latitude");
        this.homeLongStr = this.getParameter("longitude");
        this.red = Integer.parseInt(this.getParameter("red"));
        this.green = Integer.parseInt(this.getParameter("green"));
        this.blue = Integer.parseInt(this.getParameter("blue"));
        final Double latDouble = Double.valueOf(this.homeLatStr);
        this.latitude = latDouble;
        final Double longDouble = Double.valueOf(this.homeLongStr);
        this.longitude = longDouble;
        final URL url = this.getDocumentBase();
        this.myStr = url.toString();
        this.myStr = String.valueOf(this.myStr) + "1234567890123456789012345";
        this.wwwStr = this.myStr.substring(0, 27);
        this.dat = new Date();
        this.year = this.dat.getYear();
        this.month = this.dat.getMonth();
        this.date = this.dat.getDate();
        this.hours = this.dat.getHours();
        double jd = this.JD(this.date, this.month + 1, this.year + 1900, this.hours);
        this.timezoneoffset = -this.dat.getTimezoneOffset() / 60;
        jd -= this.timezoneoffset / 24.0;
        this.hours -= this.timezoneoffset;
        int dT = 0;
        if (this.hours < 0) {
            this.hours += 24;
            dT = -1;
        }
        if (this.hours > 24) {
            this.hours -= 24;
            dT = 1;
        }
        this.minutes = this.dat.getMinutes();
        final double currentUT = this.hours + this.minutes / 60.0;
        final double currentJD = this.JD(this.date, this.month + 1, this.year + 1900, currentUT) + dT;
        this.month = this.comp.caldat(2, currentJD);
        this.date = this.comp.caldat(1, currentJD);
        this.year = this.comp.caldat(0, currentJD);
        this.date = this.caldat(1, jd);
        this.month = this.caldat(2, jd);
        this.year = this.caldat(4, jd) - 1900;
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(5, 5, 0, 0);
        Label L = new Label("Year");
        gbl.setConstraints(L, gbc);
        this.add(L);
        gbc.insets = new Insets(5, 0, 0, 0);
        L = new Label("Month");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("Date");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("UT h");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("UT min");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label();
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("Lat. xx.xx");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("Long. xx.xx");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label("Details");
        gbl.setConstraints(L, gbc);
        this.add(L);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 0, 0);
        this.yearChoice = new Choice();
        for (int i = 0; i < 100; ++i) {
            this.yearStr = String.valueOf(1900 + this.year - 51 + i);
            this.yearChoice.addItem(this.yearStr);
        }
        this.yearStr = String.valueOf(1900 + this.year);
        this.yearChoice.select(this.yearStr);
        gbl.setConstraints(this.yearChoice, gbc);
        this.add(this.yearChoice);
        gbc.insets = new Insets(0, 0, 0, 0);
        this.monthChoice = new Choice();
        for (int j = 0; j < 12; ++j) {
            this.monthChoice.addItem(this.monthArray[j]);
        }
        this.monthChoice.select(this.month);
        gbl.setConstraints(this.monthChoice, gbc);
        this.add(this.monthChoice);
        this.dateChoice = new Choice();
        for (int k = 1; k < 32; ++k) {
            this.dateChoice.addItem(String.valueOf(k));
        }
        this.dateChoice.select(this.date - 1);
        gbl.setConstraints(this.dateChoice, gbc);
        this.add(this.dateChoice);
        this.hoursChoice = new Choice();
        for (int l = 0; l < 24; ++l) {
            this.hoursStr = String.valueOf(l);
            if (l < 10) {
                this.hoursStr = "0" + this.hoursStr;
            }
            this.hoursChoice.addItem(this.hoursStr);
        }
        this.hoursChoice.select(this.hours);
        gbl.setConstraints(this.hoursChoice, gbc);
        this.add(this.hoursChoice);
        this.minChoice = new Choice();
        for (int m = 0; m < 60; ++m) {
            String minStr = String.valueOf(m);
            if (m < 10) {
                minStr = "0" + minStr;
            }
            this.minChoice.addItem(minStr);
        }
        this.minChoice.select(this.minutes);
        gbl.setConstraints(this.minChoice, gbc);
        this.add(this.minChoice);
        gbl.setConstraints(this.nowButton = new Button("Now"), gbc);
        this.add(this.nowButton);
        gbl.setConstraints(this.fieldLat = new TextField(String.valueOf(this.latitude)), gbc);
        this.add(this.fieldLat);
        gbl.setConstraints(this.fieldLong = new TextField(String.valueOf(this.longitude)), gbc);
        this.add(this.fieldLong);
        (this.detailsChoice = new Choice()).addItem("Grid");
        this.detailsChoice.addItem("Mean");
        this.detailsChoice.addItem("Min/Max");
        this.detailsChoice.addItem("Data Dist.");
        this.detailsChoice.addItem("Data Lib.");
        this.detailsChoice.addItem("Libration Graph");
        this.detailsChoice.addItem("Lib. Path Year");
        this.detailsChoice.addItem("Lib. Path Month");
        this.detailsChoice.addItem("Axis on/off");
        gbc.insets = new Insets(0, 0, 0, 5);
        gbl.setConstraints(this.detailsChoice, gbc);
        this.add(this.detailsChoice);
        gbc.gridy = 2;
        gbc.weighty = 160.0;
        final Canvas myCan = new Canvas();
        gbl.setConstraints(myCan, gbc);
        this.add(myCan);
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        this.usrStr = this.email;
        this.userString = String.valueOf(this.email) + "  " + this.dat.toString();
        if (this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(this.wwwStr, 22) == this.formula("http://www.geoastro.de", 22) || this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 27) == this.formula("http://www.jgiesen.business", 27)) {
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
        }
        if (this.demo) {
            this.versStr = String.valueOf(this.versStr) + " DEMO";
        }
    }
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 5965;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof TextField) {
            if (event.target == this.fieldLat) {
                final String str = this.fieldLat.getText();
                for (int i = 0; i < str.length(); ++i) {
                    final char c = str.charAt(i);
                    if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != '.' && c != '-') {
                        this.fieldLat.setText(String.valueOf(this.latitude));
                        return true;
                    }
                }
                try {
                    final Double latDouble = Double.valueOf(this.fieldLat.getText());
                    this.latitude = latDouble;
                }
                catch (Exception ex) {}
                this.repaint();
                return true;
            }
            if (event.target == this.fieldLong) {
                final String str = this.fieldLong.getText();
                for (int i = 0; i < str.length(); ++i) {
                    final char c = str.charAt(i);
                    if (c != '0' && c != '1' && c != '2' && c != '3' && c != '4' && c != '5' && c != '6' && c != '7' && c != '8' && c != '9' && c != '.' && c != '-') {
                        this.fieldLong.setText(String.valueOf(this.longitude));
                        return true;
                    }
                }
                try {
                    final Double longDouble = Double.valueOf(this.fieldLong.getText());
                    this.longitude = longDouble;
                }
                catch (Exception ex2) {}
                this.repaint();
                return true;
            }
        }
        if (event.target instanceof Button && event.target == this.nowButton) {
            this.dat = new Date();
            this.year = this.dat.getYear();
            this.yearStr = String.valueOf(this.year + 1900);
            this.month = this.dat.getMonth();
            this.date = this.dat.getDate();
            this.hours = this.dat.getHours();
            this.minutes = this.dat.getMinutes();
            double jd = this.JD(this.date, this.month + 1, this.year + 1900, this.hours);
            this.timezoneoffset = -this.dat.getTimezoneOffset() / 60;
            jd -= this.timezoneoffset / 24.0;
            this.hours -= this.timezoneoffset;
            int dT = 0;
            if (this.hours < 0) {
                this.hours += 24;
                dT = -1;
            }
            if (this.hours > 24) {
                this.hours -= 24;
                dT = 1;
            }
            final double currentUT = this.hours + this.minutes / 60.0;
            final double currentJD = this.JD(this.date, this.month + 1, this.year + 1900, currentUT) + dT;
            this.month = this.comp.caldat(2, currentJD);
            this.date = this.comp.caldat(1, currentJD);
            this.year = this.comp.caldat(0, currentJD);
            this.dateChoice.select(this.date - 1);
            this.monthChoice.select(this.month);
            this.hoursChoice.select(this.hours);
            this.minChoice.select(this.minutes);
            this.yearChoice.select(String.valueOf(this.year + 1900));
            this.date = this.caldat(1, jd);
            this.month = this.caldat(2, jd);
            this.year = this.caldat(4, jd) - 1900;
            this.dateChoice.select(this.date - 1);
            this.monthChoice.select(this.month);
            this.hoursChoice.select(this.hours);
            this.yearChoice.select(this.yearStr);
            if (this.libPathMonthOK) {
                this.doLib();
            }
            this.repaint();
            return true;
        }
        if (event.target instanceof Choice) {
            if (event.target == this.detailsChoice) {
                if (this.detailsChoice.getSelectedItem().equals("Grid")) {
                    this.grid ^= true;
                    this.repaint();
                    return true;
                }
                if (this.detailsChoice.getSelectedItem().equals("Mean")) {
                    this.meanOK ^= true;
                    if (this.libPathYearOK) {
                        this.meanOK = false;
                    }
                    this.repaint();
                    return true;
                }
                if (this.detailsChoice.getSelectedItem().equals("Min/Max")) {
                    this.MinMaxOK ^= true;
                    if (this.libPathYearOK) {
                        this.MinMaxOK = false;
                    }
                    this.repaint();
                    return true;
                }
                if (this.detailsChoice.getSelectedItem().equals("Data Dist.")) {
                    final boolean dem = this.online || this.demo;
                    this.doData();
                    final Frame df = new dataFrame("Moon Distance " + (this.year + 1900), this.versStr, this.Data, this.nData, dem);
                    df.resize(400, 400);
                    df.show();
                    return true;
                }
                if (this.detailsChoice.getSelectedItem().equals("Data Lib.")) {
                    final boolean dem = this.online || this.demo;
                    this.libPathYearOK = true;
                    this.libPathMonthOK = false;
                    this.doLib();
                    final Frame df = new dataFrame("Moon Libration " + (this.year + 1900), this.versStr, this.Data, this.nData, dem);
                    df.resize(350, 400);
                    df.show();
                    this.libPathYearOK = false;
                    this.repaint();
                    return true;
                }
                if (this.detailsChoice.getSelectedItem().equals("Libration Graph")) {
                    this.doGraph();
                    final Frame mgf = new GraphFrame(this.versStr, this.year, this.libL, this.libB, this.online);
                    mgf.resize(580, 490);
                    mgf.show();
                }
                if (this.detailsChoice.getSelectedItem().equals("Axis on/off")) {
                    this.axisOK ^= true;
                    this.repaint();
                    return true;
                }
                if (this.detailsChoice.getSelectedItem().equals("Lib. Path Month")) {
                    this.libPathYearOK = false;
                    this.libPathMonthOK ^= true;
                    if (this.libPathMonthOK) {
                        this.MinMaxOK = false;
                        this.meanOK = false;
                    }
                    this.doLib();
                    if (this.libPathMonthOK) {
                        this.grid = false;
                    }
                    else {
                        this.grid = true;
                    }
                    this.repaint();
                    return true;
                }
                if (this.detailsChoice.getSelectedItem().equals("Lib. Path Year")) {
                    this.libPathMonthOK = false;
                    this.libPathYearOK ^= true;
                    this.doLib();
                    if (this.libPathYearOK) {
                        this.MinMaxOK = false;
                        this.meanOK = false;
                    }
                    if (this.libPathYearOK) {
                        this.grid = false;
                    }
                    else {
                        this.grid = true;
                    }
                    this.repaint();
                    return true;
                }
            }
            if (event.target == this.minChoice) {
                this.minutes = Integer.parseInt(this.minChoice.getSelectedItem());
                this.repaint();
                return true;
            }
            if (event.target == this.dateChoice) {
                final String dateStr = this.dateChoice.getSelectedItem();
                this.date = Integer.parseInt(dateStr);
                final int n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = n;
                    this.dateChoice.select(this.date - 1);
                    this.hoursChoice.select(this.hours);
                }
                if (this.libPathMonthOK) {
                    this.doLib();
                }
                this.repaint();
                return true;
            }
            if (event.target == this.monthChoice) {
                final String monthStr = this.monthChoice.getSelectedItem();
                this.month = this.MonthInteger(monthStr);
                final int n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = n;
                    this.dateChoice.select(this.date - 1);
                }
                if (this.libPathMonthOK) {
                    this.doLib();
                }
                this.repaint();
                return true;
            }
            if (event.target == this.yearChoice) {
                final String yearStr = this.yearChoice.getSelectedItem();
                this.year = Integer.parseInt(yearStr) - 1900;
                this.yearChoice.select(yearStr);
                final int n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = n;
                    this.dateChoice.select(this.date - 1);
                }
                if (this.libPathMonthOK || this.libPathYearOK) {
                    this.doLib();
                }
                this.repaint();
                return true;
            }
            if (event.target == this.hoursChoice) {
                this.hoursStr = this.hoursChoice.getSelectedItem();
                this.hours = Integer.parseInt(this.hoursStr);
                this.repaint();
                return true;
            }
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int code) {
        int n = 0;
        if (code != 104 && code != 100 && code != 109 && code != 72 && code != 68 && code != 77 && code != 110 && code != 78 && code != 119 && code != 87) {
            return false;
        }
        if (code == 119 || code == 87) {
            double Jd = this.comp.JD(this.date, this.month + 1, this.year + 1900, this.hours + this.minutes / 60.0);
            if (code == 119) {
                Jd += 7.0;
            }
            else {
                Jd -= 7.0;
            }
            this.date = this.comp.caldat(1, Jd);
            this.month = this.comp.caldat(2, Jd);
            this.year = this.comp.caldat(0, Jd);
            this.dat.setDate(this.date);
            this.dat.setYear(this.year);
            this.dateChoice.select(this.date - 1);
            this.dat.setMonth(this.month);
            this.monthChoice.select(this.month);
            this.dat.setYear(this.year);
            this.yearChoice.select(String.valueOf(this.year + 1900));
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
                this.hoursChoice.select(this.hours);
                if (this.date > this.daysInMonth(this.month, this.year)) {
                    this.date = 1;
                    ++this.month;
                    if (this.month == 12) {
                        this.month = 0;
                        ++this.year;
                        this.yearChoice.select(String.valueOf(this.year + 1900));
                    }
                    this.monthChoice.select(this.month);
                }
                this.dateChoice.select(this.date - 1);
            }
            this.minChoice.select(this.minutes);
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
                this.hoursChoice.select(this.hours);
                if (this.date == 0) {
                    --this.month;
                    if (this.month < 0) {
                        this.month = 11;
                        --this.year;
                        this.yearChoice.select(String.valueOf(this.year + 1900));
                    }
                    this.date = this.daysInMonth(this.month, this.year);
                    this.monthChoice.select(this.month);
                }
                this.dateChoice.select(this.date - 1);
            }
            this.minChoice.select(this.minutes);
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
                        }
                        this.date = this.daysInMonth(this.month, this.year);
                    }
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
                    }
                }
            }
            this.hoursChoice.select(this.hours);
            this.dateChoice.select(this.date - 1);
            this.monthChoice.select(this.month);
            this.yearStr = String.valueOf(this.year + 1900);
            this.yearChoice.select(this.yearStr);
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
                    }
                    this.date = this.daysInMonth(this.month, this.year);
                }
            }
            else {
                ++this.date;
                n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = 1;
                    ++this.month;
                    if (this.month == 12) {
                        this.month = 0;
                        ++this.year;
                    }
                }
            }
            this.dateChoice.select(this.date - 1);
            this.monthChoice.select(this.month);
            this.yearStr = String.valueOf(this.year + 1900);
            this.yearChoice.select(this.yearStr);
            if (this.libPathMonthOK) {
                this.doLib();
            }
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
            n = this.daysInMonth(this.month, this.year);
            if (this.date > n) {
                this.date = 1;
                --this.month;
            }
            this.monthChoice.select(this.month);
            this.dateChoice.select(this.date - 1);
            this.yearStr = String.valueOf(this.year + 1900);
            this.yearChoice.select(this.yearStr);
            if (this.libPathMonthOK) {
                this.doLib();
            }
            this.repaint();
            return true;
        }
        return false;
    }
    
    public double JD(final int date, int month, int year, final double STD) {
        double A = 10000.0 * year + 100.0 * month + date;
        if (month <= 2) {
            month += 12;
            --year;
        }
        final double B = year / 400 - year / 100 + year / 4;
        A = 365.0 * year - 679004.0;
        final double MJD = A + B + (int)(30.6001 * (month + 1)) + date + STD / 24.0;
        return MJD + 2400000.5;
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
        final double hour = 24.0 * (JD + 0.5 - JD2);
        if (what == 1) {
            return day;
        }
        if (what == 2) {
            return month - 1;
        }
        if (what == 3) {
            return (int)Math.round(hour);
        }
        if (what == 4) {
            return year;
        }
        return 0;
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
    
    double frac(double x) {
        x -= (int)x;
        if (x < 0.0) {
            ++x;
        }
        return x;
    }
    
    public void doGraph() {
        final double ut = this.UT;
        this.nData = 0;
        for (int m = 0; m < 12; ++m) {
            for (int n = this.daysInMonth(m, this.year), i = 1; i <= n; ++i) {
                final double jd = this.JD(i, m + 1, this.year + 1900, ut);
                this.T = (jd - 2451545.0) / 36525.0;
                this.omega = 125.044555 - 1934.1361849 * this.T + 0.0020762 * this.T * this.T + this.T * this.T * this.T / 467410.0 - this.T * this.T * this.T * this.T / 6.0616E7;
                this.moon = new Moon(jd);
                this.lambda = this.moon.lambda();
                final double Ls = this.lib(jd, this.lambda, 1);
                final double B = this.lib(jd, this.lambda, 2);
                this.libL[this.nData] = Ls;
                this.libB[this.nData] = B;
                ++this.nData;
            }
        }
    }
    
    public void doLib() {
        this.nData = 0;
        final double[] L = new double[400];
        final double[] b = new double[400];
        final double[] Jd = new double[400];
        final double ut = this.UT;
        this.Data[this.nData] = "Libration and angle of rotation axis at " + (int)ut + " UT" + "\n";
        ++this.nData;
        this.Data[this.nData] = "           Lat/°   Long/°    Axis/°\n";
        ++this.nData;
        if (this.libPathYearOK) {
            for (int m = 0; m < 12; ++m) {
                for (int n = this.daysInMonth(m, this.year), i = 1; i <= n; ++i) {
                    final double jd = this.JD(i, m + 1, this.year + 1900, ut);
                    String str1 = String.valueOf(i);
                    if (i < 10) {
                        str1 = "0" + str1;
                    }
                    final double T = (jd - 2451545.0) / 36525.0;
                    final double omega = 125.044555 - 1934.1361849 * T + 0.0020762 * T * T + T * T * T / 467410.0 - T * T * T * T / 6.0616E7;
                    final Moon moon = new Moon(jd);
                    final double lambda = moon.lambda();
                    final double Ls = this.lib(jd, lambda, 1);
                    final double B = this.lib(jd, lambda, 2);
                    L[this.nData] = Ls;
                    b[this.nData] = B;
                    Jd[this.nData] = jd;
                    this.XX[this.nData] = 200 - (int)Math.round(14.0 * Ls);
                    this.YY[this.nData] = this.y0 + (int)Math.round(14.0 * B);
                    String str2;
                    for (str2 = String.valueOf(Math.round(100.0 * B) / 100.0); str2.length() < 6; str2 = " " + str2) {}
                    String str3;
                    for (str3 = String.valueOf(Math.round(100.0 * Ls) / 100.0); str3.length() < 6; str3 = " " + str3) {}
                    double pAngleAxis = this.pAngle(jd, Ls, 15.0 * this.computeMoon(jd, 2));
                    if (pAngleAxis < 0.0) {
                        pAngleAxis += 360.0;
                    }
                    String str4;
                    for (str4 = String.valueOf(Math.round(100.0 * pAngleAxis) / 100.0); str4.length() < 6; str4 = " " + str4) {}
                    this.Data[this.nData] = String.valueOf(this.monthArray[m]) + " " + str1 + "   " + str2 + "   " + str3 + "    " + str4 + "\n";
                    this.libL[this.nData] = Ls;
                    ++this.nData;
                }
            }
        }
        if (this.libPathMonthOK) {
            for (int j = 0; j < 400; ++j) {
                this.XX[j] = 0;
                this.YY[j] = 0;
            }
            for (int n = this.daysInMonth(this.month, this.year), i = 1; i <= n; ++i) {
                final double jd = this.JD(i, this.month + 1, this.year + 1900, ut);
                String str1 = String.valueOf(i);
                if (i < 10) {
                    str1 = "0" + str1;
                }
                final double T = (jd - 2451545.0) / 36525.0;
                final double omega = 125.044555 - 1934.1361849 * T + 0.0020762 * T * T + T * T * T / 467410.0 - T * T * T * T / 6.0616E7;
                final Moon moon = new Moon(jd);
                final double lambda = moon.lambda();
                final double Ls = this.lib(jd, lambda, 1);
                final double B = this.lib(jd, lambda, 2);
                L[this.nData] = Ls;
                b[this.nData] = B;
                Jd[this.nData] = jd;
                this.XX[this.nData] = 200 - (int)Math.round(14.0 * Ls);
                this.YY[this.nData] = this.y0 + (int)Math.round(14.0 * B);
                String str2;
                for (str2 = String.valueOf(Math.round(100.0 * B) / 100.0); str2.length() < 6; str2 = " " + str2) {}
                String str3;
                for (str3 = String.valueOf(Math.round(100.0 * Ls) / 100.0); str3.length() < 6; str3 = " " + str3) {}
                double pAngleAxis = this.pAngle(jd, Ls, 15.0 * this.computeMoon(jd, 2));
                if (pAngleAxis < 0.0) {
                    pAngleAxis += 360.0;
                }
                String str4;
                for (str4 = String.valueOf(Math.round(100.0 * pAngleAxis) / 100.0); str4.length() < 6; str4 = " " + str4) {}
                this.Data[this.nData] = String.valueOf(this.monthArray[this.month]) + " " + str1 + "   " + str2 + "   " + str3 + "    " + str4 + "\n";
                ++this.nData;
            }
        }
        double max = 0.0;
        double jdMax = 0.0;
        double maxL = 0.0;
        double maxB = 0.0;
        for (int k = 0; k <= this.nData; ++k) {
            final double libLB = Math.sqrt(L[k] * L[k] + b[k] * b[k]);
            if (libLB > max) {
                max = libLB;
                jdMax = Jd[k];
                maxL = L[k];
                maxB = b[k];
            }
        }
        this.Data[this.nData] = "\n" + this.monthArray[this.caldat(2, jdMax)] + " " + this.caldat(1, jdMax) + "   root(L*L+B*B)=" + Math.round(100.0 * max) / 100.0 + '°' + "   B=" + Math.round(100.0 * maxB) / 100.0 + '°' + "   L=" + Math.round(100.0 * maxL) / 100.0 + '°' + "\n";
        max = 0.0;
        for (int l = 0; l <= this.nData; ++l) {
            if (b[l] > max) {
                max = b[l];
                jdMax = Jd[l];
            }
        }
        double min = 100.0;
        double jdMin = 0.0;
        for (int i2 = 0; i2 <= this.nData; ++i2) {
            if (b[i2] < min) {
                min = b[i2];
                jdMin = Jd[i2];
            }
        }
        ++this.nData;
        this.Data[this.nData] = String.valueOf(this.monthArray[this.caldat(2, jdMax)]) + " " + this.caldat(1, jdMax) + "   Bmax=" + Math.round(100.0 * max) / 100.0 + '°' + "\n";
        ++this.nData;
        this.Data[this.nData] = String.valueOf(this.monthArray[this.caldat(2, jdMax)]) + " " + this.caldat(1, jdMin) + "   Bmin=" + Math.round(100.0 * min) / 100.0 + '°' + "\n";
        max = 0.0;
        for (int i3 = 0; i3 <= this.nData; ++i3) {
            if (L[i3] > max) {
                max = L[i3];
                jdMax = Jd[i3];
            }
        }
        min = 100.0;
        jdMin = 0.0;
        for (int i4 = 0; i4 <= this.nData; ++i4) {
            if (L[i4] < min) {
                min = L[i4];
                jdMin = Jd[i4];
            }
        }
        ++this.nData;
        this.Data[this.nData] = String.valueOf(this.monthArray[this.caldat(2, jdMax)]) + " " + this.caldat(1, jdMax) + "   Lmax=" + Math.round(100.0 * max) / 100.0 + '°' + "\n";
        ++this.nData;
        this.Data[this.nData] = String.valueOf(this.monthArray[this.caldat(2, jdMax)]) + " " + this.caldat(1, jdMin) + "   Lmin=" + Math.round(100.0 * min) / 100.0 + '°' + "\n";
        int count = 0;
        final double[] LMinMaxJd = new double[30];
        final double[] LMinMax = new double[30];
        for (int i5 = 3; i5 < this.nData - 4; ++i5) {
            if (L[i5] < L[i5 - 1] && L[i5] < L[i5 + 1]) {
                LMinMaxJd[count] = Jd[i5];
                LMinMax[count] = L[i5];
                ++count;
            }
            if (L[i5] > L[i5 - 1] && L[i5] > L[i5 + 1]) {
                LMinMaxJd[count] = Jd[i5];
                LMinMax[count] = L[i5];
                ++count;
            }
        }
        ++this.nData;
        this.Data[this.nData] = "\n";
        ++this.nData;
        this.Data[this.nData] = "Extreme librations in longitude:\n";
        ++this.nData;
        final double[] LL = new double[30];
        final double[] moonL = new double[30];
        for (int i6 = 0; i6 < count - 1; ++i6) {
            for (int h = 0; h <= 24; ++h) {
                final double jd = LMinMaxJd[i6] - 0.5 + h / 24.0;
                final double T = (jd - 2451545.0) / 36525.0;
                final double omega = 125.044555 - 1934.1361849 * T + 0.0020762 * T * T + T * T * T / 467410.0 - T * T * T * T / 6.0616E7;
                final Moon moon2 = new Moon(jd);
                final double lambda = moon2.lambda();
                LL[h] = this.lib(jd, lambda, 1);
            }
            for (int k2 = 1; k2 < 24; ++k2) {
                if (LL[k2] < LL[k2 - 1] && LL[k2] < LL[k2 + 1]) {
                    moonL[i6] = k2;
                }
                if (LL[k2] > LL[k2 - 1] && LL[k2] > LL[k2 + 1]) {
                    moonL[i6] = k2;
                }
            }
            final double JD = LMinMaxJd[i6] - 0.5 + moonL[i6] / 24.0;
            final double T = (JD - 2451545.0) / 36525.0;
            final double omega = 125.044555 - 1934.1361849 * T + 0.0020762 * T * T + T * T * T / 467410.0 - T * T * T * T / 6.0616E7;
            final Moon moon2 = new Moon(JD);
            final double lambda = moon2.lambda();
            LL[i6] = this.lib(JD, lambda, 1);
            String str1 = String.valueOf(this.caldat(1, JD));
            if (str1.length() < 2) {
                str1 = "0" + str1;
            }
            String str2 = String.valueOf(this.caldat(3, JD));
            if (str2.length() < 2) {
                str2 = "0" + str2;
            }
            String str3 = String.valueOf(Math.round(100.0 * JD) / 100.0);
            if (str3.length() < 10) {
                str3 = String.valueOf(str3) + "0";
            }
            this.Data[this.nData] = String.valueOf(str3) + "  " + this.monthArray[this.caldat(2, JD)] + "  " + str1 + "  at " + str2 + " UT  L=" + Math.round(100.0 * LL[i6]) / 100.0 + '°' + "\n";
            ++this.nData;
        }
        if (count - 2 >= 0) {
            double jd2 = LMinMaxJd[count - 2] - 0.5 + moonL[count - 2] / 24.0;
            double jd3 = LMinMaxJd[0] - 0.5 + moonL[0] / 24.0;
            double deltaJD = jd2 - jd3;
            final double dJD = 2.0 * deltaJD / (count - 2);
            this.Data[this.nData] = "Mean period " + Math.round(100.0 * dJD) / 100.0 + " days" + "\n";
            --this.nData;
            count = 0;
            final double[] BMinMaxJd = new double[30];
            final double[] BMinMax = new double[30];
            for (int i7 = 3; i7 < this.nData - 4; ++i7) {
                if (b[i7] < b[i7 - 1] && b[i7] < b[i7 + 1]) {
                    BMinMaxJd[count] = Jd[i7];
                    BMinMax[count] = b[i7];
                    ++count;
                }
                if (b[i7] > b[i7 - 1] && b[i7] > b[i7 + 1]) {
                    BMinMaxJd[count] = Jd[i7];
                    BMinMax[count] = b[i7];
                    ++count;
                }
            }
            ++this.nData;
            this.Data[this.nData] = "\n";
            ++this.nData;
            this.Data[this.nData] = "Extreme librations in latitude:\n";
            ++this.nData;
            final double[] BB = new double[30];
            final double[] moonB = new double[30];
            for (int i8 = 0; i8 < count - 1; ++i8) {
                for (int h2 = 0; h2 <= 24; ++h2) {
                    final double jd = BMinMaxJd[i8] - 0.5 + h2 / 24.0;
                    final double T = (jd - 2451545.0) / 36525.0;
                    final double omega = 125.044555 - 1934.1361849 * T + 0.0020762 * T * T + T * T * T / 467410.0 - T * T * T * T / 6.0616E7;
                    final Moon moon2 = new Moon(jd);
                    final double lambda = moon2.lambda();
                    BB[h2] = this.lib(jd, lambda, 2);
                }
                for (int k3 = 1; k3 < 24; ++k3) {
                    if (BB[k3] < BB[k3 - 1] && BB[k3] < BB[k3 + 1]) {
                        moonB[i8] = k3;
                    }
                    if (BB[k3] > BB[k3 - 1] && BB[k3] > BB[k3 + 1]) {
                        moonB[i8] = k3;
                    }
                }
                final double JD = BMinMaxJd[i8] - 0.5 + moonB[i8] / 24.0;
                final double T = (JD - 2451545.0) / 36525.0;
                final double omega = 125.044555 - 1934.1361849 * T + 0.0020762 * T * T + T * T * T / 467410.0 - T * T * T * T / 6.0616E7;
                final Moon moon2 = new Moon(JD);
                final double lambda = moon2.lambda();
                BB[i8] = this.lib(JD, lambda, 2);
                String str1 = String.valueOf(this.caldat(1, JD));
                if (str1.length() < 2) {
                    str1 = "0" + str1;
                }
                String str2 = String.valueOf(this.caldat(3, JD));
                if (str2.length() < 2) {
                    str2 = "0" + str2;
                }
                String str3 = String.valueOf(Math.round(100.0 * JD) / 100.0);
                if (str3.length() < 10) {
                    str3 = String.valueOf(str3) + "0";
                }
                this.Data[this.nData] = String.valueOf(str3) + "  " + this.monthArray[this.caldat(2, JD)] + "  " + str1 + "  at " + str2 + " UT  B=" + Math.round(100.0 * BB[i8]) / 100.0 + '°' + "\n";
                ++this.nData;
            }
            jd2 = BMinMaxJd[count - 2] - 0.5 + moonB[count - 2] / 24.0;
            jd3 = BMinMaxJd[0] - 0.5 + moonB[0] / 24.0;
            deltaJD = jd2 - jd3;
            deltaJD = 2.0 * deltaJD / (count - 2);
            this.Data[this.nData] = "Mean period " + Math.round(100.0 * deltaJD) / 100.0 + " days" + "\n";
            ++this.nData;
            this.Data[this.nData] = "\n";
            ++this.nData;
            this.Data[this.nData] = "Mean major axis coincides with mean line of nodes (approximately):\n";
            for (int k4 = 0; k4 < 7; ++k4) {
                final double tjd = 2451798.66 + 1095.175 * k4;
                ++this.nData;
                this.Data[this.nData] = String.valueOf(this.caldat(4, tjd)) + "  " + this.monthArray[this.caldat(2, tjd)] + "  " + this.caldat(1, tjd) + "  at " + this.caldat(3, tjd) + " UT" + "\n";
            }
        }
        this.repaint();
    }
    
    public void doData() {
        this.nData = 0;
        double jdMin = 0.0;
        double jdMax = 0.0;
        double min = 500000.0;
        double max = 0.0;
        this.Data[this.nData] = "at " + (int)this.UT + " UT" + "\n";
        ++this.nData;
        this.Data[this.nData] = "\t  Dist./km   arcmin   Phase\n";
        ++this.nData;
        for (int m = 0; m < 12; ++m) {
            for (int n = this.daysInMonth(m, this.year), i = 1; i <= n; ++i) {
                String str1 = String.valueOf(i);
                if (i < 10) {
                    str1 = "0" + str1;
                }
                final double jd = this.JD(i, m + 1, this.year + 1900, this.UT);
                this.moonDist = new MoonDistance(jd);
                final double dist = this.moonDist.computeR();
                if (dist > max) {
                    max = dist;
                    jdMax = jd;
                }
                if (dist < min) {
                    min = dist;
                    jdMin = jd;
                }
                final double w = 60.0 * Math.atan(3476.6 / dist) / 0.017453292519943295;
                String str2;
                for (str2 = String.valueOf(Math.round(1000.0 * w) / 1000.0); str2.length() < 6; str2 = String.valueOf(str2) + " ") {}
                final Moon moon1 = new Moon(jd);
                final double lambda = moon1.lambda();
                final PlanetRiseSet planetRS = new PlanetRiseSet(jd, 0);
                final double earthGeoLambda = planetRS.lambda();
                double waxwan = lambda - earthGeoLambda;
                if (waxwan < 0.0) {
                    waxwan += 360.0;
                }
                waxwan /= 360.0;
                final String str3 = String.valueOf(Math.round(1000.0 * waxwan) / 1000.0);
                this.Data[this.nData] = String.valueOf(this.monthArray[m]) + " " + str1 + "   " + (int)Math.round(dist) + "   " + str2 + "   " + str3 + "\n";
                ++this.nData;
            }
        }
        min = 500000.0;
        max = 0.0;
        for (int i = -48; i <= 48; ++i) {
            final double jd = jdMin + i * 0.25 / 24.0;
            this.moonDist = new MoonDistance(jd);
            final double dist = this.moonDist.computeR();
            if (dist < min) {
                jdMin = jd;
                min = dist;
            }
        }
        for (int j = -48; j <= 48; ++j) {
            final double jd = jdMax + j * 0.25 / 24.0;
            this.moonDist = new MoonDistance(jd);
            final double dist = this.moonDist.computeR();
            if (dist > max) {
                jdMax = jd;
                max = dist;
            }
        }
        this.moonDist = new MoonDistance(jdMin);
        min = this.moonDist.computeR();
        final Moon moonMin = new Moon(jdMin);
        double lambda = moonMin.lambda();
        final PlanetRiseSet planetRSMin = new PlanetRiseSet(jdMin, 0);
        double earthGeoLambda = planetRSMin.lambda();
        double waxwanMin = lambda - earthGeoLambda;
        if (waxwanMin < 0.0) {
            waxwanMin += 360.0;
        }
        waxwanMin /= 360.0;
        this.moonDist = new MoonDistance(jdMax);
        max = this.moonDist.computeR();
        final Moon moonMax = new Moon(jdMax);
        lambda = moonMax.lambda();
        final PlanetRiseSet planetRSMax = new PlanetRiseSet(jdMax, 0);
        earthGeoLambda = planetRSMax.lambda();
        double waxwanMax = lambda - earthGeoLambda;
        if (waxwanMax < 0.0) {
            waxwanMax += 360.0;
        }
        waxwanMax /= 360.0;
        this.Data[this.nData] = "Min " + (int)Math.round(min) + " km  on " + this.monthArray[this.caldat(2, jdMin)] + " " + this.caldat(1, jdMin) + " at " + this.caldat(3, jdMin) + " UT  Phase: " + Math.round(1000.0 * waxwanMin) / 1000.0 + "\n";
        ++this.nData;
        this.Data[this.nData] = "Max " + (int)Math.round(max) + " km  on " + this.monthArray[this.caldat(2, jdMax)] + " " + this.caldat(1, jdMax) + " at " + this.caldat(3, jdMax) + " UT  Phase: " + Math.round(1000.0 * waxwanMax) / 1000.0 + "\n";
        final String[] data = this.Data;
        final int nData = this.nData;
        data[nData] = String.valueOf(data[nData]) + "\n";
        String str1;
        if (this.latitude >= 0.0) {
            str1 = " N";
        }
        else {
            str1 = " S";
        }
        String str2;
        if (this.longitude >= 0.0) {
            str2 = " E";
        }
        else {
            str2 = " W";
        }
        final String[] data2 = this.Data;
        final int nData2 = this.nData;
        data2[nData2] = String.valueOf(data2[nData2]) + "Lat.= " + this.latitude + '°' + str1 + "  Long.= " + this.longitude + '°' + str2 + "\n";
        final String[] data3 = this.Data;
        final int nData3 = this.nData;
        data3[nData3] = String.valueOf(data3[nData3]) + "\t  \tDist.                        Phase   Alt.     Azim.\n";
        this.moonPeriAp();
    }
    
    public void moonPeriAp() {
        final double[] distance = new double[400];
        final double[] distanceH = new double[30];
        final int[] moonPeriDay = new int[30];
        final int[] moonH = new int[30];
        int count = 0;
        final double jd0 = this.JD(1, 1, this.year + 1900, 0.0);
        for (int i = 0; i <= 365; ++i) {
            final double jd2 = jd0 + i;
            final MoonDistance moonD = new MoonDistance(jd2);
            distance[i] = moonD.computeR();
        }
        for (int j = 1; j <= 364; ++j) {
            if (distance[j] < distance[j - 1] && distance[j] < distance[j + 1]) {
                moonPeriDay[count] = j;
                ++count;
            }
            if (distance[j] > distance[j - 1] && distance[j] > distance[j + 1]) {
                moonPeriDay[count] = j;
                ++count;
            }
        }
        for (int k = 0; k < count; ++k) {
            ++this.nData;
            for (int h = 0; h <= 24; ++h) {
                final double jd2 = jd0 + moonPeriDay[k] - 0.5 + h / 24.0;
                final MoonDistance moonD = new MoonDistance(jd2);
                distanceH[h] = moonD.computeR();
            }
            for (int n = 1; n < 24; ++n) {
                if (distanceH[n] < distanceH[n - 1] && distanceH[n] < distanceH[n + 1]) {
                    moonH[k] = n;
                }
                if (distanceH[n] > distanceH[n - 1] && distanceH[n] > distanceH[n + 1]) {
                    moonH[k] = n;
                }
            }
            final double Jd = jd0 + moonPeriDay[k] - 0.5 + moonH[k] / 24.0;
            String str1 = String.valueOf(this.caldat(1, Jd));
            if (str1.length() < 2) {
                str1 = "0" + str1;
            }
            String str2 = String.valueOf(this.caldat(3, Jd));
            if (str2.length() < 2) {
                str2 = "0" + str2;
            }
            String str3;
            if (distanceH[moonH[k]] < 384000.0) {
                str3 = "Perigee  ";
            }
            else {
                str3 = "Apogee   ";
            }
            final Moon moon = new Moon(Jd);
            final double lambda = moon.lambda();
            final PlanetRiseSet planetRS = new PlanetRiseSet(Jd, 0);
            final double earthGeoLambda = planetRS.lambda();
            double waxwan = lambda - earthGeoLambda;
            if (waxwan < 0.0) {
                waxwan += 360.0;
            }
            waxwan /= 360.0;
            String str4;
            for (str4 = String.valueOf(Math.round(1000.0 * waxwan) / 1000.0); str4.length() < 5; str4 = String.valueOf(str4) + " ") {}
            final double dec = this.computeMoon(Jd, 1);
            final double RA = this.computeMoon(Jd, 2);
            final compute comp = new compute();
            double elev = comp.sun_elev(Jd, this.latitude, -this.longitude, dec, RA);
            final MoonDistance moonDist = new MoonDistance(Jd);
            final double dist = moonDist.computeR();
            elev -= this.moonParal(elev, dist);
            final double az = comp.azimuth(Jd, RA, dec, this.latitude, this.longitude);
            String str5;
            for (str5 = String.valueOf(Math.round(10.0 * elev) / 10.0); str5.length() < 7; str5 = " " + str5) {}
            String str6;
            for (str6 = String.valueOf(Math.round(10.0 * az) / 10.0); str6.length() < 7; str6 = " " + str6) {}
            this.Data[this.nData] = String.valueOf(str3) + Math.round(distanceH[moonH[k]]) + " km  " + this.monthArray[this.caldat(2, Jd)] + " " + str1 + "  at " + str2 + " UT  " + str4 + " " + str5 + '°' + " " + str6 + '°' + "\n";
        }
    }
    
    public double computeMoon(final double jd, final int what) {
        final double T = (jd - 2451545.0) / 36525.0;
        final double P2 = 6.283185307179586;
        final double ARC = 206264.8062;
        final double coseps = 0.917482062;
        final double sineps = 0.397777156;
        final double L0 = this.frac(0.606433 + 1336.855225 * T);
        final double L2 = 6.283185307179586 * this.frac(0.374897 + 1325.55241 * T);
        final double LS = 6.283185307179586 * this.frac(0.993133 + 99.997361 * T);
        final double D = 6.283185307179586 * this.frac(0.827361 + 1236.853086 * T);
        final double F = 6.283185307179586 * this.frac(0.259086 + 1342.227825 * T);
        final double DL = 22640.0 * Math.sin(L2) - 4586.0 * Math.sin(L2 - 2.0 * D) + 2370.0 * Math.sin(2.0 * D) + 769.0 * Math.sin(2.0 * L2) - 668.0 * Math.sin(LS) - 412.0 * Math.sin(2.0 * F) - 212.0 * Math.sin(2.0 * L2 - 2.0 * D) - 206.0 * Math.sin(L2 + LS - 2.0 * D) + 192.0 * Math.sin(L2 + 2.0 * D) - 165.0 * Math.sin(LS - 2.0 * D) - 125.0 * Math.sin(D) - 110.0 * Math.sin(L2 + LS) + 148.0 * Math.sin(L2 - LS) - 55.0 * Math.sin(2.0 * F - 2.0 * D);
        final double S = F + (DL + 412.0 * Math.sin(2.0 * F) + 541.0 * Math.sin(LS)) / 206264.8062;
        final double H = F - 2.0 * D;
        final double N = -526.0 * Math.sin(H) + 44.0 * Math.sin(L2 + H) - 31.0 * Math.sin(-L2 + H) - 23.0 * Math.sin(LS + H) + 11.0 * Math.sin(-LS + H) - 25.0 * Math.sin(-2.0 * L2 + F) + 21.0 * Math.sin(-L2 + F);
        final double L_Moon = 6.283185307179586 * this.frac(L0 + DL / 1296000.0);
        final double B_Moon = (18520.0 * Math.sin(S) + N) / 206264.8062;
        final double CB = Math.cos(B_Moon);
        final double X = CB * Math.cos(L_Moon);
        final double V = CB * Math.sin(L_Moon);
        final double W = Math.sin(B_Moon);
        final double Y = 0.917482062 * V - 0.397777156 * W;
        final double Z = 0.397777156 * V + 0.917482062 * W;
        final double rho = Math.sqrt(1.0 - Z * Z);
        if (what == 1) {
            return 57.29577951308232 * Math.atan2(Z, rho);
        }
        if (what == 2) {
            return 7.639437268410976 * Math.atan2(Y, X + rho);
        }
        if (what == 3) {
            return B_Moon / 0.017453292519943295;
        }
        return 0.0;
    }
    
    public double moonParal(final double elev, final double distance) {
        final double horParal = 8.794 / (distance / 1.4959787E8);
        final double paral = Math.cos(0.017453292519943295 * elev) * Math.sin(0.017453292519943295 * horParal / 3600.0);
        return Math.asin(paral) / 0.017453292519943295;
    }
    
    public double pAngle(final double jd, final double EB, final double RA) {
        final double T = (jd - 2451545.0) / 36525.0;
        final double Obl = (84381.448 - 46.815 * T) / 3600.0;
        final double Om2 = 125.044555 - 1934.1361849 * T + 0.0020762 * T * T + T * T * T / 467410.0 - T * T * T * T / 6.0616E7;
        final double X = Math.sin(0.026920307448610938) * Math.sin(0.017453292519943295 * Om2);
        final double Y = Math.sin(0.026920307448610938) * Math.cos(0.017453292519943295 * Om2) * Math.cos(0.017453292519943295 * Obl) - Math.cos(0.026920307448610938) * Math.sin(0.017453292519943295 * Obl);
        final double W = Math.atan2(X, Y) / 0.017453292519943295;
        final double A = Math.sqrt(X * X + Y * Y) * Math.cos(0.017453292519943295 * (RA - W));
        return Math.asin(A / Math.cos(0.017453292519943295 * EB)) / 0.017453292519943295;
    }
    
    public double lib(final double jd, final double lambda, final int what) {
        final double T = (jd - 2451545.0) / 36525.0;
        final double omega = 125.044555 - 1934.1361849 * T + 0.0020762 * T * T + T * T * T / 467410.0 - T * T * T * T / 6.0616E7;
        final double W = lambda - omega;
        final double beta = this.computeMoon(jd, 3);
        double F = 93.2720993 + 483202.0175273 * T - 0.0034029 * T * T - T * T * T / 3526000.0 + T * T * T * T / 8.6331E8;
        F %= 360.0;
        if (F < 0.0) {
            F += 360.0;
        }
        double A = Math.atan2(Math.sin(0.017453292519943295 * W) * Math.cos(0.017453292519943295 * beta) * Math.cos(0.026920307448610938) - Math.sin(0.017453292519943295 * beta) * Math.sin(0.026920307448610938), Math.cos(0.017453292519943295 * W) * Math.cos(0.017453292519943295 * beta)) / 0.017453292519943295;
        if (A < 0.0) {
            A += 360.0;
        }
        double Ls = A - F;
        if (Ls < -10.0) {
            Ls += 360.0;
        }
        if (Ls > 10.0) {
            Ls -= 360.0;
        }
        final double B = Math.asin(-Math.sin(0.017453292519943295 * W) * Math.cos(0.017453292519943295 * beta) * Math.sin(0.026920307448610938) - Math.sin(0.017453292519943295 * beta) * Math.cos(0.026920307448610938)) / 0.017453292519943295;
        if (what == 1) {
            return Ls;
        }
        return B;
    }
    
    public int[] drehe(final double x, final double y, final double alpha) {
        final int[] xy = { (int)Math.round(x * Math.cos(0.017453292519943295 * alpha) + y * Math.sin(0.017453292519943295 * alpha)), (int)Math.round(-x * Math.sin(0.017453292519943295 * alpha) + y * Math.cos(0.017453292519943295 * alpha)) };
        return xy;
    }
    
    double refract(final double h) {
        return 1.02 / Math.tan(0.017453292519943295 * (h + 10.3 / (h + 5.11)));
    }
    
    double ParallacticAngle() {
        final double jd = this.JD(this.date, this.month + 1, this.year + 1900, this.UT);
        final double RA = this.computeMoon(jd, 2);
        final double LHA = this.moon_LHA(jd, RA, this.longitude);
        final double DEC = this.computeMoon(jd, 1);
        final double PA = Math.asin(Math.cos(0.017453292519943295 * this.latitude) * Math.sin(0.017453292519943295 * LHA) / Math.sin(0.017453292519943295 * (90.0 - this.elev))) / 0.017453292519943295;
        return PA;
    }
    
    double GM_Sidereal_Time(final double JD) {
        final double MJD = JD - 2400000.5;
        final long MJD2 = (long)MJD;
        final double ut = (MJD - MJD2) * 24.0;
        final double t_eph = (MJD2 - 51544.5) / 36525.0;
        return 6.697374558 + 1.0027379093 * ut + (8640184.812866 + (0.093104 - 6.2E-6 * t_eph) * t_eph) * t_eph / 3600.0;
    }
    
    double moon_LHA(final double JD, final double RA, final double longitude) {
        final double GMST = this.GM_Sidereal_Time(JD);
        double tau = 15.0 * (GMST - RA) + longitude;
        tau %= 360.0;
        if (tau < 0.0) {
            tau += 360.0;
        }
        return tau;
    }
    
    public void paint(final Graphics g) {
        g.setFont(new Font("Helvetica", 0, 10));
        g.drawString(String.valueOf(this.versStr) + "  (c) 2006-2010  J. Giesen - www.GeoAstro.de", 240, this.size().height - 5);
        g.setFont(new Font("Courier", 0, 12));
        g.setColor(Color.red);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        g.drawLine(this.margin, this.y0, this.size().width - this.margin, this.y0);
        g.drawLine(200, 90, 200, this.size().height - this.margin - 30);
        g.drawLine(595, 90, 595, this.size().height - this.margin - 30);
        this.UT = this.hours + this.minutes / 60.0;
        this.jd = this.JD(this.date, this.month + 1, this.year + 1900, this.UT);
        this.moonDist = new MoonDistance(this.jd);
        this.distance = this.moonDist.computeR();
        this.winkel = 3476.6 / this.distance;
        this.winkel = 60.0 * Math.atan(this.winkel) / 0.017453292519943295;
        this.radius = this.winkel * 5.0;
        this.moon = new Moon(this.jd);
        this.phase = this.moon.phase();
        final Moon moon1 = new Moon(this.jd + 0.041666666666666664);
        final double phase1 = moon1.phase();
        String pStr;
        if (phase1 > this.phase) {
            pStr = "  (+)";
        }
        else {
            pStr = "  (-)";
        }
        this.r = (int)Math.round(this.radius);
        if (!this.libPathYearOK && !this.libPathMonthOK) {
            g.setColor(new Color(this.red, this.green, this.blue));
            g.fillOval(200 - this.r, this.y0 - this.r, 2 * this.r, 2 * this.r);
            g.fillOval(595 - this.r, this.y0 - this.r, 2 * this.r, 2 * this.r);
            g.setColor(Color.black);
            g.drawOval(200 - this.r, this.y0 - this.r, 2 * this.r, 2 * this.r);
            g.drawOval(595 - this.r, this.y0 - this.r, 2 * this.r, 2 * this.r);
        }
        this.lambda = this.moon.lambda();
        this.planetRS = new PlanetRiseSet(this.jd, 0);
        this.earthGeoLambda = this.planetRS.lambda();
        this.waxwan = this.lambda - this.earthGeoLambda;
        if (this.waxwan < 0.0) {
            this.waxwan += 360.0;
        }
        g.setColor(Color.black);
        g.drawString("Phase         " + Math.round(1000.0 * this.waxwan / 360.0) / 1000.0, 20, this.oben + 26);
        g.drawString("Jul. Date     " + Math.round(100000.0 * this.jd) / 100000.0, 20, this.oben);
        g.drawString("Illum. Fract. " + Math.round(1000.0 * this.phase) / 10.0 + " %" + pStr, 20, this.oben + 13);
        final int rechts = 610;
        g.drawString("Diameter " + Math.round(1000.0 * this.winkel) / 1000.0 + "'", rechts, this.oben + 13);
        g.drawString("Distance " + Math.round(this.distance) + " km", rechts, this.oben);
        this.dec = this.computeMoon(this.jd, 1);
        this.RA = this.computeMoon(this.jd, 2);
        this.Ls = this.lib(this.jd, this.lambda, 1);
        this.B = this.lib(this.jd, this.lambda, 2);
        if (this.latitude < 0.0) {
            this.B = -this.B;
            this.Ls = -this.Ls;
        }
        this.pAngleAxis = this.pAngle(this.jd, this.Ls, 15.0 * this.RA);
        final double PA = this.ParallacticAngle();
        final int unten = this.size().height - 47;
        g.drawString("Lib. Lat.  " + Math.round(100.0 * this.B) / 100.0 + '°', 30, unten);
        g.drawString("Lib. Long. " + Math.round(100.0 * this.Ls) / 100.0 + '°', 30, unten + 13);
        g.drawString("Angle Rot. Axis " + Math.round(10.0 * this.pAngleAxis) / 10.0 + '°', 30, unten + 26);
        g.drawString("Local Horizon:", rechts, unten);
        g.drawString("Parallactic Angle " + Math.round(10.0 * PA) / 10.0 + '°', rechts, unten + 13);
        g.drawString("Zenith Angle " + Math.round(10.0 * (-PA + this.pAngleAxis)) / 10.0 + '°', rechts, unten + 26);
        double P = 0.0;
        g.setColor(Color.black);
        this.elev = this.comp.sun_elev(this.jd, this.latitude, -this.longitude, this.dec, this.RA);
        this.elev -= this.moonParal(this.elev, this.distance);
        this.elev += this.refract(this.elev) / 60.0;
        g.drawString("Declin.     " + this.comp.DM(this.dec), 350, this.oben);
        g.drawString("Right Asc.  " + this.comp.HM(this.RA), 350, this.oben + 13);
        String str;
        if (this.latitude >= 0.0) {
            str = " N";
        }
        else {
            str = " S";
        }
        g.drawString("Latit.  " + Math.abs(this.latitude) + '°' + str, 350, unten);
        if (this.longitude >= 0.0) {
            str = " E";
        }
        else {
            str = " W";
        }
        g.drawString("Longit. " + Math.abs(this.longitude) + '°' + str, 350, unten + 13);
        if (this.elev < 0.0) {
            g.setColor(Color.red);
        }
        g.drawString("Altitude  " + Math.round(10.0 * this.elev) / 10.0 + '°', rechts, this.oben + 26);
        g.setColor(Color.black);
        g.setColor(Color.darkGray);
        int xx = 0;
        if (!this.libPathYearOK && !this.libPathMonthOK) {
            final double w = this.waxwan / 90.0;
            for (int N = 0; N < 2; ++N) {
                if (N == 0) {
                    P = this.pAngleAxis;
                    xx = 200;
                }
                else {
                    P = -PA + this.pAngleAxis;
                    xx = 595;
                }
                final int[] px = new int[400];
                final int[] py = new int[400];
                final int[] pxx = new int[400];
                final int[] pyy = new int[400];
                int np = 0;
                if (this.waxwan <= 90.0) {
                    g.setColor(Color.darkGray);
                    np = 0;
                    for (int i = -this.r; i <= this.r; ++i) {
                        this.x1 = -Math.sqrt(this.r * this.r - i * i);
                        this.x2 = (1.0 - w) * this.radius * Math.sqrt(1.0 - i * i / (this.r * this.r));
                        this.x = this.drehe(this.x1, i, -P)[0];
                        this.y = this.drehe(this.x1, i, -P)[1];
                        this.x1 = this.drehe(this.x2, i, -P)[0];
                        final int y1 = this.drehe(this.x2, i, -P)[1];
                        if (this.latitude >= 0.0) {
                            px[np] = xx + this.x;
                            py[np] = this.y0 - this.y;
                            pxx[np] = xx + (int)Math.round(this.x1);
                            pyy[np] = this.y0 - y1;
                        }
                        else {
                            px[np] = xx - this.x;
                            py[np] = this.y0 + this.y;
                            pxx[np] = xx - (int)Math.round(this.x1);
                            pyy[np] = this.y0 + y1;
                        }
                        ++np;
                    }
                    g.fillPolygon(pxx, pyy, np);
                    g.fillPolygon(px, py, np);
                }
                if (this.waxwan > 90.0 && this.waxwan <= 180.0) {
                    np = 0;
                    for (int i = -this.r; i <= this.r; ++i) {
                        this.x1 = Math.sqrt(this.r * this.r - i * i);
                        this.x2 = (w - 1.0) * this.radius * Math.sqrt(1.0 - i * i / (this.r * this.r));
                        this.x = this.drehe(this.x1, i, P)[0];
                        this.y = this.drehe(this.x1, i, P)[1];
                        this.x1 = this.drehe(this.x2, i, P)[0];
                        final int y1 = this.drehe(this.x2, i, P)[1];
                        if (this.latitude >= 0.0) {
                            pxx[np] = xx - (int)Math.round(this.x1);
                            pyy[np] = this.y0 - y1;
                        }
                        else {
                            pxx[np] = xx + (int)Math.round(this.x1);
                            pyy[np] = this.y0 + y1;
                        }
                        ++np;
                    }
                    if (this.latitude >= 0.0) {
                        g.setColor(Color.darkGray);
                        g.fillArc(xx - this.r, this.y0 - this.r, 2 * this.r, 2 * this.r, 90 + (int)Math.round(P), 180);
                        g.setColor(new Color(this.red, this.green, this.blue));
                        g.fillPolygon(pxx, pyy, np);
                    }
                    else {
                        g.setColor(Color.darkGray);
                        g.fillArc(xx - this.r, this.y0 - this.r, 2 * this.r, 2 * this.r, 90 + (int)Math.round(P), -180);
                        g.setColor(new Color(this.red, this.green, this.blue));
                        g.fillPolygon(pxx, pyy, np);
                    }
                }
                if (this.waxwan > 180.0 && this.waxwan <= 270.0) {
                    np = 0;
                    for (int i = -this.r; i <= this.r; ++i) {
                        this.x1 = Math.sqrt(this.r * this.r - i * i);
                        this.x2 = this.x1 - (w - 2.0) * this.radius * Math.sqrt(1.0 - i * i / (this.r * this.r));
                        this.x = this.drehe(this.x1, i, -P)[0];
                        this.y = this.drehe(this.x1, i, -P)[1];
                        this.x1 = this.drehe(this.x2, i, -P)[0];
                        final int y1 = this.drehe(this.x2, i, -P)[1];
                        if (this.latitude >= 0.0) {
                            pxx[np] = xx + (int)Math.round(this.x1);
                            pyy[np] = this.y0 - y1;
                        }
                        else {
                            pxx[np] = xx - (int)Math.round(this.x1);
                            pyy[np] = this.y0 + y1;
                        }
                        ++np;
                    }
                    if (this.latitude >= 0.0) {
                        g.setColor(Color.darkGray);
                        g.fillOval(xx - this.r, this.y0 - this.r, 2 * this.r, 2 * this.r);
                        g.setColor(new Color(this.red, this.green, this.blue));
                        g.fillPolygon(pxx, pyy, np);
                        g.fillArc(xx - this.r, this.y0 - this.r, 2 * this.r, 2 * this.r, 90 + (int)Math.round(P), 180);
                    }
                    else {
                        g.setColor(Color.darkGray);
                        g.fillOval(xx - this.r, this.y0 - this.r, 2 * this.r, 2 * this.r);
                        g.setColor(new Color(this.red, this.green, this.blue));
                        g.fillPolygon(pxx, pyy, np);
                        g.fillArc(xx - this.r, this.y0 - this.r, 2 * this.r, 2 * this.r, 90 + (int)Math.round(P), -180);
                    }
                }
                if (this.waxwan > 270.0) {
                    np = 0;
                    for (int i = -this.r; i <= this.r; ++i) {
                        this.x1 = Math.sqrt(this.r * this.r - i * i);
                        this.x2 = (w - 3.0) * this.radius * Math.sqrt(1.0 - i * i / (this.r * this.r));
                        this.x = this.drehe(this.x1, i, -P)[0];
                        this.y = this.drehe(this.x1, i, -P)[1];
                        this.x1 = this.drehe(-this.x2, i, -P)[0];
                        final int y1 = this.drehe(-this.x2, i, -P)[1];
                        if (this.latitude >= 0.0) {
                            px[np] = xx + this.x;
                            py[np] = this.y0 - this.y;
                            pxx[np] = xx + (int)Math.round(this.x1);
                            pyy[np] = this.y0 - y1;
                        }
                        else {
                            pxx[np] = xx - (int)Math.round(this.x1);
                            pyy[np] = this.y0 + y1;
                        }
                        ++np;
                    }
                    if (this.latitude >= 0.0) {
                        g.setColor(Color.darkGray);
                        g.fillPolygon(pxx, pyy, np);
                        g.fillPolygon(px, py, np);
                    }
                    else {
                        g.setColor(new Color(this.red, this.green, this.blue));
                        g.fillOval(xx - this.r, this.y0 - this.r, 2 * this.r, 2 * this.r);
                        g.setColor(Color.darkGray);
                        g.fillArc(xx - this.r, this.y0 - this.r, 2 * this.r, 2 * this.r, 90 + (int)Math.round(P), 180);
                        g.fillPolygon(pxx, pyy, np);
                    }
                }
                if (this.grid) {
                    g.setColor(Color.gray);
                    for (int t = 0; t <= 36; ++t) {
                        for (int p = 0; p <= 72; ++p) {
                            double phi = this.Ls + p * 5;
                            phi %= 360.0;
                            if (phi < 0.0) {
                                phi += 360.0;
                            }
                            if (phi < 270.0 && phi > 90.0) {
                                final double theta = -this.B * Math.cos(0.017453292519943295 * phi) + t * 5;
                                this.Y = this.r * Math.sin(0.017453292519943295 * theta) * Math.sin(0.017453292519943295 * phi);
                                final double Z = this.r * Math.cos(0.017453292519943295 * theta);
                                final int Y1 = this.drehe(this.Y, Z, -P)[0];
                                final int z1 = this.drehe(this.Y, Z, -P)[1];
                                int d;
                                if (t % 2 == 0 || p % 2 == 0) {
                                    d = 2;
                                }
                                else {
                                    d = 1;
                                }
                                g.drawOval(xx + Y1 - 1, this.y0 - z1 - 1, d, d);
                            }
                        }
                    }
                }
                g.setColor(Color.red);
                for (int p2 = 0; p2 < 72; ++p2) {
                    double phi = -this.Ls + p2 * 5;
                    phi %= 360.0;
                    if (phi < 0.0) {
                        phi += 360.0;
                    }
                    if (phi < 270.0 && phi > 90.0) {
                        double theta = -this.B * Math.cos(0.017453292519943295 * phi) + 90.0;
                        this.Y = this.r * Math.sin(0.017453292519943295 * theta) * Math.sin(0.017453292519943295 * phi);
                        double Z = this.r * Math.cos(0.017453292519943295 * theta);
                        final int yy1 = this.drehe(this.Y, Z, -P)[0];
                        final int z1 = this.drehe(this.Y, Z, -P)[1];
                        phi = -this.Ls + (p2 + 1) * 5;
                        phi %= 360.0;
                        if (phi < 0.0) {
                            phi += 360.0;
                        }
                        theta = -this.B * Math.cos(0.017453292519943295 * phi) + 90.0;
                        this.Y = (int)Math.round(this.r * Math.sin(0.017453292519943295 * theta) * Math.sin(0.017453292519943295 * phi));
                        Z = (int)Math.round(this.r * Math.cos(0.017453292519943295 * theta));
                        final int yy2 = this.drehe(this.Y, Z, -P)[0];
                        final int z2 = this.drehe(this.Y, Z, -P)[1];
                        g.drawLine(xx + yy1, this.y0 - z1, xx + yy2, this.y0 - z2);
                    }
                }
                double phi = this.Ls;
                for (int t2 = 0; t2 <= 36; ++t2) {
                    double theta = this.B * Math.cos(0.017453292519943295 * phi) + t2 * 5;
                    this.Y = this.r * Math.sin(0.017453292519943295 * theta) * Math.sin(0.017453292519943295 * phi);
                    double Z = this.r * Math.cos(0.017453292519943295 * theta);
                    final int yy1 = this.drehe(this.Y, Z, P)[0];
                    final int z1 = this.drehe(this.Y, Z, P)[1];
                    theta = this.B * Math.cos(0.017453292519943295 * phi) + (t2 - 1) * 5;
                    this.Y = this.r * Math.sin(0.017453292519943295 * theta) * Math.sin(0.017453292519943295 * phi);
                    Z = this.r * Math.cos(0.017453292519943295 * theta);
                    final int yy2 = this.drehe(this.Y, Z, P)[0];
                    final int z2 = this.drehe(this.Y, Z, P)[1];
                    g.drawLine(xx - yy1, this.y0 - z1, xx - yy2, this.y0 - z2);
                }
                g.setColor(Color.red);
                phi = this.Ls;
                double theta = this.B * Math.cos(0.017453292519943295 * phi) + 90.0;
                this.Y = this.r * Math.sin(0.017453292519943295 * theta) * Math.sin(0.017453292519943295 * phi);
                double Z = this.r * Math.cos(0.017453292519943295 * theta);
                final int yy1 = this.drehe(this.Y, Z, P)[0];
                final int z1 = this.drehe(this.Y, Z, P)[1];
                g.fillOval(xx - yy1 - 2, this.y0 - z1 - 2, 4, 4);
                g.drawOval(xx - 2, this.y0 - 2, 4, 4);
            }
        }
        if (this.axisOK && !this.libPathMonthOK && !this.libPathYearOK) {
            P = this.pAngleAxis;
            g.setColor(Color.blue);
            g.drawLine(200, this.y0, 200 + (int)Math.round((this.radius + 20.0) * Math.sin(0.017453292519943295 * P)), this.y0 + (int)Math.round((this.radius + 20.0) * Math.cos(0.017453292519943295 * P)));
            g.drawLine(200, this.y0, 200 - (int)Math.round((this.radius + 20.0) * Math.sin(0.017453292519943295 * P)), this.y0 - (int)Math.round((this.radius + 20.0) * Math.cos(0.017453292519943295 * P)));
            g.drawLine(200, this.y0, 200 + (int)Math.round((this.radius + 20.0) * Math.sin(0.017453292519943295 * (P + 90.0))), this.y0 + (int)Math.round((this.radius + 20.0) * Math.cos(0.017453292519943295 * (P + 90.0))));
            g.drawLine(200, this.y0, 200 - (int)Math.round((this.radius + 20.0) * Math.sin(0.017453292519943295 * (P + 90.0))), this.y0 - (int)Math.round((this.radius + 20.0) * Math.cos(0.017453292519943295 * (P + 90.0))));
            P = -PA + this.pAngleAxis;
            g.drawLine(595, this.y0, 595 + (int)Math.round((this.radius + 20.0) * Math.sin(0.017453292519943295 * P)), this.y0 + (int)Math.round((this.radius + 20.0) * Math.cos(0.017453292519943295 * P)));
            g.drawLine(595, this.y0, 595 - (int)Math.round((this.radius + 20.0) * Math.sin(0.017453292519943295 * P)), this.y0 - (int)Math.round((this.radius + 20.0) * Math.cos(0.017453292519943295 * P)));
            g.drawLine(595, this.y0, 595 + (int)Math.round((this.radius + 20.0) * Math.sin(0.017453292519943295 * (P + 90.0))), this.y0 + (int)Math.round((this.radius + 20.0) * Math.cos(0.017453292519943295 * (P + 90.0))));
            g.drawLine(595, this.y0, 595 - (int)Math.round((this.radius + 20.0) * Math.sin(0.017453292519943295 * (P + 90.0))), this.y0 - (int)Math.round((this.radius + 20.0) * Math.cos(0.017453292519943295 * (P + 90.0))));
        }
        double xL = this.radius * this.Ls / 90.0;
        double yL = this.radius * this.B / 90.0;
        g.setColor(Color.red);
        if (this.libPathYearOK || this.libPathMonthOK) {
            xL = 14.0 * this.Ls;
            yL = 14.0 * this.B;
            g.fillOval(200 - (int)Math.round(xL) - 2, this.y0 + (int)Math.round(yL) - 2, 4, 4);
            if (this.libPathMonthOK) {
                g.setColor(Color.blue);
                g.fillOval(this.XX[2] - 2, this.YY[2] - 2, 4, 4);
                g.drawString("1", this.XX[2] + 4, this.YY[2] + 4);
                g.fillOval(this.XX[16] - 2, this.YY[16] - 2, 4, 4);
                g.drawString("15", this.XX[16] + 4, this.YY[16] + 4);
                g.setColor(Color.red);
                g.fillOval(200 - (int)Math.round(xL) - 2, this.y0 + (int)Math.round(yL) - 2, 4, 4);
            }
        }
        if (this.libPathYearOK || this.libPathMonthOK) {
            final int RR = 140;
            for (int j = 2; j < this.nData - 1; ++j) {
                if (this.XX[j] * this.XX[j + 1] != 0) {
                    g.drawLine(this.XX[j], this.YY[j], this.XX[j + 1], this.YY[j + 1]);
                }
            }
            g.setColor(Color.black);
            g.drawOval(200 - RR, this.y0 - RR, 2 * RR, 2 * RR);
            g.drawString("10°", 345, this.y0 - 5);
            g.drawString("10°", 210, this.y0 - 145);
        }
        if (this.meanOK && !this.libPathYearOK && !this.libPathMonthOK) {
            g.setColor(Color.red);
            final int meanR = (int)Math.round(300.0 * Math.atan(0.009044201237769932) / 0.017453292519943295);
            g.drawOval(200 - meanR, this.y0 - meanR, 2 * meanR, 2 * meanR);
        }
        if (this.MinMaxOK && !this.libPathYearOK && !this.libPathMonthOK) {
            g.setColor(Color.blue);
            final int maxR = (int)Math.round(300.0 * Math.atan(0.008547895357985837) / 0.017453292519943295);
            g.drawOval(200 - maxR, this.y0 - maxR, 2 * maxR, 2 * maxR);
            final int minR = (int)Math.round(300.0 * Math.atan(0.009755454226587162) / 0.017453292519943295);
            g.drawOval(200 - minR, this.y0 - minR, 2 * minR, 2 * minR);
        }
        if ((this.MinMaxOK || this.meanOK) && !this.libPathYearOK) {
            g.setColor(Color.black);
            final double minD = 60.0 * Math.atan(0.008547895357985837) / 0.017453292519943295;
            final double maxD = 60.0 * Math.atan(0.009755454226587162) / 0.017453292519943295;
            final double meanD = 60.0 * Math.atan(0.009044201237769932) / 0.017453292519943295;
            g.drawString(Math.round(1000.0 * minD) / 1000.0 + "' min", 350, this.oben + 30);
            g.drawString(Math.round(1000.0 * meanD) / 1000.0 + "' mean", 350, this.oben + 43);
            g.drawString(Math.round(1000.0 * maxD) / 1000.0 + "' max", 350, this.oben + 56);
        }
        if (this.demo) {
            final Font f = g.getFont();
            g.setFont(new Font("Chicago", 0, 72));
            g.setColor(Color.red);
            g.drawString(" D  E  M  O", 50, 120);
            g.setFont(f);
        }
    }
    
    public moonlib0994() {
        this.versStr = "Moon Libration 0.994";
        this.demo = true;
        this.online = false;
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.margin = 20;
        this.Data = new String[450];
        this.oben = 70;
        this.meanOK = false;
        this.MinMaxOK = false;
        this.LatLong = false;
        this.XX = new int[450];
        this.YY = new int[450];
        this.libPathYearOK = false;
        this.libPathMonthOK = false;
        this.axisOK = false;
        this.libL = new double[370];
        this.libB = new double[370];
        this.grid = true;
        this.paralAngle = true;
    }
}
