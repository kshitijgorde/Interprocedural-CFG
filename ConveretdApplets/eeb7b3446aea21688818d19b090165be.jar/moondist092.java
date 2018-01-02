import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Event;
import java.net.URL;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Choice;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class moondist092 extends Applet
{
    String versStr;
    final double K = 0.017453292519943295;
    final char deg = '°';
    Date dat;
    int date;
    int month;
    int year;
    int hours;
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
    int x0;
    int y0;
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
    double az;
    double lambda;
    double waxwan;
    TextField fieldLat;
    TextField fieldLong;
    GridBagLayout gbl;
    GridBagConstraints gbc;
    Label L1;
    Label L2;
    String homeLatStr;
    String homeLongStr;
    boolean LatLong;
    int[] moonDistance;
    String minDistStr;
    String maxDistStr;
    int nDataDiagram;
    int red;
    int green;
    int blue;
    String minStr;
    String maxStr;
    
    public void init() {
        this.setFont(new Font("Helvetica", 0, 12));
        this.x0 = this.size().width / 2 - 35;
        this.y0 = this.size().height / 2 + 20;
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
        this.date = this.caldat(1, jd);
        this.month = this.caldat(2, jd);
        this.year = this.caldat(4, jd) - 1900;
        this.hours = this.caldat(3, jd);
        this.gbl = new GridBagLayout();
        this.gbc = new GridBagConstraints();
        this.setLayout(this.gbl);
        this.gbc.weightx = 1.0;
        this.gbc.weighty = 0.0;
        this.gbc.insets = new Insets(5, 5, 0, 0);
        final Label L1 = new Label("Year");
        this.gbl.setConstraints(L1, this.gbc);
        this.add(L1);
        this.gbc.insets = new Insets(5, 0, 0, 0);
        final Label L2 = new Label("Month");
        this.gbl.setConstraints(L2, this.gbc);
        this.add(L2);
        final Label L3 = new Label("Date");
        this.gbl.setConstraints(L3, this.gbc);
        this.add(L3);
        final Label L4 = new Label("UT");
        this.gbl.setConstraints(L4, this.gbc);
        this.add(L4);
        final Label L5 = new Label();
        this.gbl.setConstraints(L5, this.gbc);
        this.add(L5);
        final Label L6 = new Label("Lat. xx.xx");
        this.gbl.setConstraints(L6, this.gbc);
        this.add(L6);
        final Label L7 = new Label("Long. xx.xx");
        this.gbl.setConstraints(L7, this.gbc);
        this.add(L7);
        final Label L8 = new Label("Details");
        this.gbl.setConstraints(L8, this.gbc);
        this.add(L8);
        this.gbc.gridy = 1;
        this.gbc.insets = new Insets(0, 10, 0, 0);
        this.yearChoice = new Choice();
        for (int i = 0; i < 110; ++i) {
            this.yearStr = String.valueOf(1900 + this.year - 59 + i);
            this.yearChoice.addItem(this.yearStr);
        }
        this.yearStr = String.valueOf(1900 + this.year);
        this.yearChoice.select(this.yearStr);
        this.gbl.setConstraints(this.yearChoice, this.gbc);
        this.add(this.yearChoice);
        this.gbc.insets = new Insets(0, 0, 0, 0);
        this.monthChoice = new Choice();
        for (int j = 0; j < 12; ++j) {
            this.monthChoice.addItem(this.monthArray[j]);
        }
        this.monthChoice.select(this.month);
        this.gbl.setConstraints(this.monthChoice, this.gbc);
        this.add(this.monthChoice);
        this.dateChoice = new Choice();
        for (int k = 1; k < 32; ++k) {
            this.dateChoice.addItem(String.valueOf(k));
        }
        this.dateChoice.select(this.date - 1);
        this.gbl.setConstraints(this.dateChoice, this.gbc);
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
        this.gbl.setConstraints(this.hoursChoice, this.gbc);
        this.add(this.hoursChoice);
        this.nowButton = new Button("Now");
        this.gbl.setConstraints(this.nowButton, this.gbc);
        this.add(this.nowButton);
        this.fieldLat = new TextField(String.valueOf(this.latitude));
        this.gbl.setConstraints(this.fieldLat, this.gbc);
        this.add(this.fieldLat);
        this.fieldLong = new TextField(String.valueOf(this.longitude));
        this.gbl.setConstraints(this.fieldLong, this.gbc);
        this.add(this.fieldLong);
        (this.detailsChoice = new Choice()).addItem("Mean");
        this.detailsChoice.addItem("Min/Max");
        this.detailsChoice.addItem("Data");
        this.detailsChoice.addItem("Diagram");
        this.detailsChoice.addItem("Topocentric");
        this.gbc.insets = new Insets(0, 0, 0, 5);
        this.gbl.setConstraints(this.detailsChoice, this.gbc);
        this.add(this.detailsChoice);
        this.gbc.gridy = 2;
        this.gbc.weighty = 160.0;
        final Canvas myCan = new Canvas();
        this.gbl.setConstraints(myCan, this.gbc);
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
        return (int)num + 7404;
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
            double jd = this.JD(this.date, this.month + 1, this.year + 1900, this.hours);
            this.timezoneoffset = -this.dat.getTimezoneOffset() / 60;
            jd -= this.timezoneoffset / 24.0;
            this.hours -= this.timezoneoffset;
            if (this.hours < 0) {
                this.hours += 24;
            }
            if (this.hours > 24) {
                this.hours -= 24;
            }
            this.date = this.caldat(1, jd);
            this.month = this.caldat(2, jd);
            this.year = this.caldat(4, jd) - 1900;
            this.dateChoice.select(this.date - 1);
            this.monthChoice.select(this.month);
            this.hoursChoice.select(this.hours);
            this.yearChoice.select(this.yearStr);
            this.repaint();
            return true;
        }
        if (event.target instanceof Choice) {
            if (event.target == this.detailsChoice) {
                if (this.detailsChoice.getSelectedItem().equals("Mean")) {
                    this.meanOK ^= true;
                    this.repaint();
                    return true;
                }
                if (this.detailsChoice.getSelectedItem().equals("Min/Max")) {
                    this.MinMaxOK ^= true;
                    this.repaint();
                    return true;
                }
                if (this.detailsChoice.getSelectedItem().equals("Data")) {
                    final boolean dem = this.online || this.demo;
                    this.doData();
                    final Frame df = new dataFrame("Moon Distance " + (this.year + 1900), String.valueOf(this.versStr) + "\n" + "Lat.= " + this.latitude + '°' + "  Long.= " + this.longitude + '°', this.Data, this.nData, dem);
                    df.resize(440, 400);
                    df.show();
                    return true;
                }
                if (this.detailsChoice.getSelectedItem().equals("Diagram")) {
                    final boolean dem = this.online || this.demo;
                    this.doDistanceDiagram();
                    this.doData();
                    final Frame DF = new diagramFrame("Moon Distance " + (this.year + 1900) + " at " + (int)this.UT + " UT", this.year, this.minStr, this.maxStr, this.nDataDiagram, this.moonDistance, dem);
                    DF.resize(710, 380);
                    DF.show();
                    return true;
                }
                if (this.detailsChoice.getSelectedItem().equals("Topocentric")) {
                    if (this.online || this.demo) {
                        final boolean dem = true;
                    }
                    else {
                        final boolean dem = false;
                    }
                    this.doTopo();
                    return true;
                }
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
                this.repaint();
                return true;
            }
            if (event.target == this.hoursChoice) {
                this.hoursStr = this.hoursChoice.getSelectedItem();
                this.hours = Integer.parseInt(this.hoursStr);
                this.UT = this.hours;
                this.repaint();
                return true;
            }
        }
        return true;
    }
    
    public boolean keyDown(final Event event, final int code) {
        int n = 0;
        if (code != 104 && code != 100 && code != 109 && code != 72 && code != 68 && code != 77) {
            return false;
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
        if (what == 5) {
            return (int)Math.round(60.0 * this.frac(hour));
        }
        if (what == 4) {
            return year;
        }
        return 0;
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
    
    public void doDistanceDiagram() {
        this.nDataDiagram = 0;
        for (int m = 0; m < 12; ++m) {
            for (int n = this.daysInMonth(m, this.year), i = 1; i <= n; ++i) {
                final double jd = this.JD(i, m + 1, this.year + 1900, this.UT);
                final MoonDistance moonDist = new MoonDistance(jd);
                this.moonDistance[this.nDataDiagram] = (int)Math.round(moonDist.computeR());
                ++this.nDataDiagram;
            }
        }
    }
    
    public void doData() {
        this.nData = 0;
        double jdMin = 0.0;
        double jdMax = 0.0;
        double jdMinT = 0.0;
        double jdMaxT = 0.0;
        double min = 500000.0;
        double max = 0.0;
        double Min = 500000.0;
        double Max = 0.0;
        this.Data[this.nData] = "at " + (int)this.UT + " UT" + "\n";
        ++this.nData;
        final double A = 6.2498597316024E19;
        double aMin = 2.0;
        double aMax = 0.0;
        double jdMinA = 0.0;
        double jdMaxA = 0.0;
        final double k = 5974556.666666667;
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
                double w = 2.0 * k / dist;
                String str2;
                for (str2 = String.valueOf(Math.round(1000.0 * w) / 1000.0); str2.length() < 6; str2 = String.valueOf(str2) + "0") {}
                final Moon moon1 = new Moon(jd);
                final double lambda = moon1.lambda();
                final PlanetRiseSet planetRS = new PlanetRiseSet(jd, 0);
                final double earthGeoLambda = planetRS.lambda();
                double waxwan = lambda - earthGeoLambda;
                if (waxwan < 0.0) {
                    waxwan += 360.0;
                }
                waxwan /= 360.0;
                String str3;
                for (str3 = String.valueOf(Math.round(1000.0 * waxwan) / 1000.0); str3.length() < 5; str3 = String.valueOf(str3) + "0") {}
                while (str3.length() < 6) {
                    str3 = String.valueOf(str3) + " ";
                }
                this.Data[this.nData] = String.valueOf(this.monthArray[m]) + " " + str1 + "   " + (int)Math.round(dist) + "   " + str2 + "   " + str3;
                final double D = dist * this.topo(jd);
                if (D > Max) {
                    Max = D;
                    jdMaxT = jd;
                }
                if (D < Min) {
                    Min = D;
                    jdMinT = jd;
                }
                str3 = "   " + Math.round(D) + "   ";
                final String[] data = this.Data;
                final int nData = this.nData;
                data[nData] = String.valueOf(data[nData]) + str3;
                w = 2.0 * k / D;
                for (str3 = String.valueOf(Math.round(1000.0 * w) / 1000.0); str3.length() < 6; str3 = String.valueOf(str3) + "0") {}
                final String[] data2 = this.Data;
                final int nData2 = this.nData;
                data2[nData2] = String.valueOf(data2[nData2]) + "   " + str3;
                final double a = 1000000.0 * A / Math.pow(1000.0 * dist, 3.0);
                if (a > aMax) {
                    aMax = a;
                    jdMaxA = jd;
                }
                if (a < aMin) {
                    aMin = a;
                    jdMinA = jd;
                }
                for (str3 = String.valueOf(Math.round(1000.0 * a) / 1000.0); str3.length() < 5; str3 = String.valueOf(str3) + "0") {}
                final String[] data3 = this.Data;
                final int nData3 = this.nData;
                data3[nData3] = String.valueOf(data3[nData3]) + "     " + str3 + "\n";
                ++this.nData;
            }
        }
        double jd2 = jdMin;
        for (int j = -24; j <= 24; ++j) {
            final double jd = jd2 + j * 0.5 / 24.0;
            this.moonDist = new MoonDistance(jd);
            final double dist = this.moonDist.computeR();
            if (dist < min) {
                jdMin = jd;
                min = dist;
            }
        }
        jd2 = jdMax;
        for (int l = -24; l <= 24; ++l) {
            final double jd = jd2 + l * 0.5 / 24.0;
            this.moonDist = new MoonDistance(jd);
            final double dist = this.moonDist.computeR();
            if (dist > max) {
                jdMax = jd;
                max = dist;
            }
        }
        final Moon moonMin = new Moon(jdMin);
        double lambda = moonMin.lambda();
        final PlanetRiseSet planetRSMin = new PlanetRiseSet(jdMin, 0);
        double earthGeoLambda = planetRSMin.lambda();
        double waxwanMin = lambda - earthGeoLambda;
        if (waxwanMin < 0.0) {
            waxwanMin += 360.0;
        }
        waxwanMin /= 360.0;
        final Moon moonMax = new Moon(jdMax);
        lambda = moonMax.lambda();
        final PlanetRiseSet planetRSMax = new PlanetRiseSet(jdMax, 0);
        earthGeoLambda = planetRSMax.lambda();
        double waxwanMax = lambda - earthGeoLambda;
        if (waxwanMax < 0.0) {
            waxwanMax += 360.0;
        }
        waxwanMax /= 360.0;
        jd2 = jdMinT;
        for (int i2 = -24; i2 <= 24; ++i2) {
            final double jd = jd2 + i2 * 0.5 / 24.0;
            this.moonDist = new MoonDistance(jd);
            final double dist = this.moonDist.computeR();
            final double D = dist * this.topo(jd);
            if (D < Min) {
                jdMinT = jd;
                Min = D;
            }
        }
        jd2 = jdMaxT;
        for (int i3 = -24; i3 <= 24; ++i3) {
            final double jd = jd2 + i3 * 0.5 / 24.0;
            this.moonDist = new MoonDistance(jd);
            final double dist = this.moonDist.computeR();
            final double D = dist * this.topo(jd);
            if (D > Max) {
                jdMaxT = jd;
                Max = D;
            }
        }
        aMin = 2.0;
        aMax = 0.0;
        jd2 = jdMinA;
        for (int i4 = -24; i4 <= 24; ++i4) {
            final double jd = jd2 + i4 * 0.5 / 24.0;
            this.moonDist = new MoonDistance(jd);
            final double dist = this.moonDist.computeR();
            final double a = 1000000.0 * A / Math.pow(1000.0 * dist, 3.0);
            if (a < aMin) {
                jdMinA = jd;
                aMin = a;
            }
        }
        jd2 = jdMaxA;
        for (int i5 = -24; i5 <= 24; ++i5) {
            final double jd = jd2 + i5 * 0.5 / 24.0;
            this.moonDist = new MoonDistance(jd);
            final double dist = this.moonDist.computeR();
            final double a = 1000000.0 * A / Math.pow(1000.0 * dist, 3.0);
            if (a > aMax) {
                jdMaxA = jd;
                aMax = a;
            }
        }
        this.Data[this.nData] = "Geocentric:   Min " + (int)Math.round(min) + " km  on " + this.monthArray[this.caldat(2, jdMin)] + " " + this.caldat(1, jdMin) + " at " + this.makeTimeString(this.caldat(3, jdMin) + this.caldat(5, jdMin) / 60.0) + " UT  Phase: " + Math.round(1000.0 * waxwanMin) / 1000.0 + "\n";
        this.minStr = this.Data[this.nData];
        final String[] data4 = this.Data;
        final int nData4 = this.nData;
        data4[nData4] = String.valueOf(data4[nData4]) + "Topocentric:  Min " + (int)Math.round(Min) + " km  on " + this.monthArray[this.caldat(2, jdMinT)] + " " + this.caldat(1, jdMinT) + " at " + this.makeTimeString(this.caldat(3, jdMinT) + this.caldat(5, jdMinT) / 60.0) + " UT" + "\n";
        this.minDistStr = this.Data[this.nData];
        ++this.nData;
        this.Data[this.nData] = "Geocentric:   Max " + (int)Math.round(max) + " km  on " + this.monthArray[this.caldat(2, jdMax)] + " " + this.caldat(1, jdMax) + " at " + this.makeTimeString(this.caldat(3, jdMax) + this.caldat(5, jdMax) / 60.0) + " UT  Phase: " + Math.round(1000.0 * waxwanMax) / 1000.0 + "\n";
        this.maxStr = "  Max " + (int)Math.round(max) + " km  on " + this.monthArray[this.caldat(2, jdMax)] + " " + this.caldat(1, jdMax) + " at " + this.caldat(3, jdMax) + " UT  Phase: " + Math.round(1000.0 * waxwanMax) / 1000.0 + "\n";
        final String[] data5 = this.Data;
        final int nData5 = this.nData;
        data5[nData5] = String.valueOf(data5[nData5]) + "Topocentric:  Max " + (int)Math.round(Max) + " km  on " + this.monthArray[this.caldat(2, jdMaxT)] + " " + this.caldat(1, jdMaxT) + " at " + this.makeTimeString(this.caldat(3, jdMaxT) + this.caldat(5, jdMaxT) / 60.0) + " UT" + "\n";
        final String[] data6 = this.Data;
        final int nData6 = this.nData;
        data6[nData6] = String.valueOf(data6[nData6]) + "Tidal acceleration Max  " + Math.round(1000.0 * aMax) / 1000.0 + "*10e-6 m/(s*s)   on " + this.monthArray[this.caldat(2, jdMaxA)] + " " + this.caldat(1, jdMaxA) + " at " + this.makeTimeString(this.caldat(3, jdMaxA) + this.caldat(5, jdMaxA) / 60.0) + " UT" + "\n";
        final String[] data7 = this.Data;
        final int nData7 = this.nData;
        data7[nData7] = String.valueOf(data7[nData7]) + "Tidal acceleration Min  " + Math.round(1000.0 * aMin) / 1000.0 + "*10e-6 m/(s*s)   on " + this.monthArray[this.caldat(2, jdMinA)] + " " + this.caldat(1, jdMinA) + " at " + this.makeTimeString(this.caldat(3, jdMinA) + this.caldat(5, jdMinA) / 60.0) + " UT" + "\n";
        this.maxDistStr = this.Data[this.nData];
        final String[] data8 = this.Data;
        final int nData8 = this.nData;
        data8[nData8] = String.valueOf(data8[nData8]) + "\n";
        final String[] data9 = this.Data;
        final int nData9 = this.nData;
        data9[nData9] = String.valueOf(data9[nData9]) + "\t  \tDist.                        Phase   Alt.     Azim.\n";
        this.moonPeriAp();
    }
    
    public void doTopo() {
        int n = 0;
        double min = 2.0;
        double max = 1.0;
        double dMin = 0.0;
        double dMax = 0.0;
        for (int N = this.daysInMonth(this.month, this.year), d = 1; d < N; ++d) {
            final double jd0 = this.JD(d, this.month + 1, this.year + 1900, 0.0);
            for (int t = 0; t < 24; ++t) {
                final double jd2 = jd0 + t / 24.0;
                final MoonDistance MD = new MoonDistance(jd2);
                final double dist = MD.computeR();
                this.moonDistance[n] = (int)Math.round(dist * this.topo(jd2));
                final Moon moon = new Moon(jd2);
                final double lambda = moon.lambda();
                final PlanetRiseSet planetRS = new PlanetRiseSet(jd2, 0);
                final double earthGeoLambda = planetRS.lambda();
                double waxwan = lambda - earthGeoLambda;
                if (waxwan < 0.0) {
                    waxwan += 360.0;
                }
                waxwan /= 360.0;
                if (waxwan < min) {
                    min = waxwan;
                    dMin = d + t / 24.0;
                }
                if (Math.abs(waxwan - 0.5) < max) {
                    max = Math.abs(waxwan - 0.5);
                    dMax = d + t / 24.0;
                }
                ++n;
            }
        }
        String LatLongStr = new StringBuffer().append(Math.abs(this.latitude)).append('°').toString();
        if (this.latitude > 0.0) {
            LatLongStr = String.valueOf(LatLongStr) + " N";
        }
        else {
            LatLongStr = String.valueOf(LatLongStr) + " S";
        }
        LatLongStr = String.valueOf(LatLongStr) + ", " + Math.abs(this.longitude) + '°';
        if (this.longitude > 0.0) {
            LatLongStr = String.valueOf(LatLongStr) + " E";
        }
        else {
            LatLongStr = String.valueOf(LatLongStr) + " W";
        }
        final topoFrame tf = new topoFrame("Topocentric Moon Distance  " + this.monthArray[this.month] + " " + (this.year + 1900), LatLongStr, n, this.moonDistance, dMin, dMax, this.demo);
        tf.resize(820, 450);
        tf.show();
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
        double RA = 7.639437268410976 * Math.atan2(Y, X + rho);
        if (RA < 0.0) {
            RA += 24.0;
        }
        if (what == 1) {
            return 57.29577951308232 * Math.atan2(Z, rho);
        }
        if (what == 2) {
            return RA;
        }
        return 0.0;
    }
    
    public double moonParal(final double elev, final double distance) {
        final double horParal = 8.794 / (distance / 1.4959787E8);
        final double paral = Math.cos(0.017453292519943295 * elev) * Math.sin(0.017453292519943295 * horParal / 3600.0);
        return Math.asin(paral) / 0.017453292519943295;
    }
    
    public double topo(final double jd) {
        final double u = Math.atan(0.99664719 * Math.tan(0.017453292519943295 * this.latitude));
        final double r_c = Math.cos(u);
        final double r_s = 0.99664719 * Math.sin(u);
        final double sp = 6378.14 / this.distance;
        final double dec = this.computeMoon(jd, 1);
        final double RA = this.computeMoon(jd, 2);
        double H = this.comp.LM_Sidereal_Time(jd, -this.longitude);
        H = 15.0 * (H - RA);
        final double A = Math.cos(0.017453292519943295 * dec) * Math.sin(0.017453292519943295 * H);
        final double B = Math.cos(0.017453292519943295 * dec) * Math.cos(0.017453292519943295 * H) - r_c * sp;
        final double C = Math.sin(0.017453292519943295 * dec) - r_s * sp;
        return Math.sqrt(A * A + B * B + C * C);
    }
    
    public void paint(final Graphics g) {
        g.setFont(new Font("Helvetica", 0, 10));
        g.drawString(String.valueOf(this.versStr) + " (c) 2006-2011 J. Giesen - www.GeoAstro.de", 160, this.size().height - 5);
        g.setFont(new Font("Courier", 0, 12));
        g.setColor(Color.red);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        g.setColor(Color.darkGray);
        g.drawLine(this.margin, this.y0, this.size().width - this.margin, this.y0);
        g.drawLine(this.x0, 60, this.x0, this.size().height - this.margin);
        this.UT = this.hours;
        this.jd = this.JD(this.date, this.month + 1, this.year + 1900, this.UT);
        final MoonDistance moonDist = new MoonDistance(this.jd);
        this.distance = moonDist.computeR();
        this.winkel = 120.0 * Math.asin(1737.9219653399998 / this.distance) / 0.017453292519943295;
        this.radius = 5.0 * this.winkel;
        this.moon = new Moon(this.jd);
        this.phase = this.moon.phase();
        this.r = (int)Math.round(this.radius);
        g.setColor(new Color(this.red, this.green, this.blue));
        g.fillOval(this.x0 - this.r, this.y0 - this.r, 2 * this.r, 2 * this.r);
        g.setColor(Color.black);
        g.drawOval(this.x0 - this.r, this.y0 - this.r, 2 * this.r, 2 * this.r);
        this.lambda = this.moon.lambda();
        this.planetRS = new PlanetRiseSet(this.jd, 0);
        final double earthGeoLambda = this.planetRS.lambda();
        this.waxwan = this.lambda - earthGeoLambda;
        if (this.waxwan < 0.0) {
            this.waxwan += 360.0;
        }
        final int rechts = 490;
        g.drawString("Phase         " + Math.round(1000.0 * this.waxwan / 360.0) / 1000.0, 20, this.oben + 26);
        g.drawString("Jul. Date     " + Math.round(100000.0 * this.jd) / 100000.0, 20, this.oben);
        g.drawString("Geocentric:", rechts, this.oben);
        g.drawString("Diameter " + Math.round(1000.0 * this.winkel) / 1000.0 + "'", rechts, this.oben + 26);
        g.drawString("Distance " + Math.round(this.distance) + " km", rechts, this.oben + 13);
        g.drawString("Illum. Fract. " + Math.round(1000.0 * this.phase) / 10.0 + " %", 20, this.oben + 13);
        this.dec = this.computeMoon(this.jd, 1);
        this.RA = this.computeMoon(this.jd, 2);
        this.comp = new compute();
        this.elev = this.comp.sun_elev(this.jd, this.latitude, -this.longitude, this.dec, this.RA);
        this.elev -= this.moonParal(this.elev, this.distance);
        g.drawString("Topocentric:", rechts, this.oben + 46);
        final double D = this.topo(this.jd) * this.distance;
        g.drawString("Distance " + Math.round(D) + " km", rechts, this.oben + 59);
        this.winkel = 120.0 * Math.asin(1737.9219653399998 / D) / 0.017453292519943295;
        g.drawString("Diameter " + Math.round(1000.0 * this.winkel) / 1000.0 + "'", rechts, this.oben + 72);
        final double A = 6.2498597316024E19;
        final double a = 1000000.0 * A / Math.pow(1000.0 * this.distance, 3.0);
        g.drawString("Tidal Acceleration:", rechts, this.oben + 92);
        g.drawString(Math.round(1000.0 * a) / 1000.0 + "*10e-6 m/(s*s)", rechts, this.oben + 105);
        this.az = this.comp.azimuth(this.jd, this.RA, this.dec, this.latitude, this.longitude);
        g.drawString("Declin.  " + Math.round(10.0 * this.dec) / 10.0 + '°', rechts, this.size().height - 40 - 13);
        g.drawString("Altitude " + Math.round(10.0 * this.elev) / 10.0 + '°', rechts, this.size().height - 40);
        g.drawString("Azimuth  " + Math.round(10.0 * this.az) / 10.0 + '°', rechts, this.size().height - 40 + 13);
        String str;
        if (this.latitude >= 0.0) {
            str = " N";
        }
        else {
            str = " S";
        }
        g.drawString("Latitude  " + Math.abs(this.latitude) + '°' + str, 20, this.size().height - 40);
        if (this.longitude >= 0.0) {
            str = " E";
        }
        else {
            str = " W";
        }
        g.drawString("Longitude " + Math.abs(this.longitude) + '°' + str, 20, this.size().height - 40 + 13);
        final double w = this.waxwan / 90.0;
        if (this.waxwan <= 90.0) {
            for (int i = -this.r + 1; i < this.r; ++i) {
                this.x1 = Math.sqrt(this.radius * this.radius - i * i);
                this.X1 = this.x0 - (int)Math.round(this.x1);
                this.x2 = (1.0 - w) * this.radius * Math.sqrt(1.0 - i * i / (this.radius * this.radius));
                this.X2 = this.x0 + (int)Math.round(this.x2);
                g.drawLine(this.X1, this.y0 - i, this.X2, this.y0 - i);
            }
        }
        if (this.waxwan > 90.0 && this.waxwan <= 180.0) {
            for (int i = -this.r + 1; i < this.r; ++i) {
                this.x1 = Math.sqrt(this.radius * this.radius - i * i);
                this.X1 = this.x0 - (int)Math.round(this.x1);
                this.x2 = (w - 1.0) * this.radius * Math.sqrt(1.0 - i * i / (this.radius * this.radius));
                this.X2 = this.x0 - (int)Math.round(this.x2);
                g.drawLine(this.X1, this.y0 - i, this.X2, this.y0 - i);
            }
        }
        if (this.waxwan > 180.0 && this.waxwan <= 270.0) {
            for (int i = -this.r + 1; i < this.r; ++i) {
                this.x1 = Math.sqrt(this.radius * this.radius - i * i);
                this.X1 = this.x0 + (int)Math.round(this.x1);
                this.x2 = this.x1 - (w - 2.0) * this.radius * Math.sqrt(1.0 - i * i / (this.radius * this.radius));
                this.X2 = this.x0 + (int)Math.round(this.x2);
                g.drawLine(this.X1, this.y0 - i, this.X2, this.y0 - i);
            }
        }
        if (this.waxwan > 270.0) {
            for (int i = -this.r + 1; i < this.r; ++i) {
                this.x1 = Math.sqrt(this.radius * this.radius - i * i);
                this.X1 = this.x0 + (int)Math.round(this.x1);
                this.x2 = (w - 3.0) * this.radius * Math.sqrt(1.0 - i * i / (this.radius * this.radius));
                this.X2 = this.x0 - (int)Math.round(this.x2);
                g.drawLine(this.X1, this.y0 - i, this.X2, this.y0 - i);
            }
        }
        if (this.meanOK) {
            g.setColor(Color.red);
            final int meanR = (int)Math.round(300.0 * Math.atan(0.009044201237769932) / 0.017453292519943295);
            g.drawOval(this.x0 - meanR, this.y0 - meanR, 2 * meanR, 2 * meanR);
        }
        if (this.MinMaxOK) {
            g.setColor(Color.blue);
            final int maxR = (int)Math.round(300.0 * Math.atan(0.008547895357985837) / 0.017453292519943295);
            g.drawOval(this.x0 - maxR, this.y0 - maxR, 2 * maxR, 2 * maxR);
            final int minR = (int)Math.round(300.0 * Math.atan(0.009755454226587162) / 0.017453292519943295);
            g.drawOval(this.x0 - minR, this.y0 - minR, 2 * minR, 2 * minR);
        }
        if (this.MinMaxOK || this.meanOK) {
            g.setColor(Color.black);
            final double minD = 60.0 * Math.atan(0.008547895357985837) / 0.017453292519943295;
            final double maxD = 60.0 * Math.atan(0.009755454226587162) / 0.017453292519943295;
            final double meanD = 60.0 * Math.atan(0.009044201237769932) / 0.017453292519943295;
            g.drawString("         " + Math.round(1000.0 * minD) / 1000.0 + "' min", 480, this.oben + 26);
            g.drawString("         " + Math.round(1000.0 * meanD) / 1000.0 + "' mean", 480, this.oben + 39);
            g.drawString("         " + Math.round(1000.0 * maxD) / 1000.0 + "' max", 480, this.oben + 52);
        }
        if (this.demo) {
            final Font f = g.getFont();
            g.setFont(new Font("Chicago", 0, 72));
            g.setColor(Color.red);
            g.drawString(" D  E  M  O", 50, 120);
            g.setFont(f);
        }
    }
    
    public moondist092() {
        this.versStr = "Moon Distance 0.92";
        this.demo = true;
        this.online = false;
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        this.margin = 20;
        this.Data = new String[400];
        this.oben = 70;
        this.meanOK = false;
        this.MinMaxOK = false;
        this.LatLong = false;
        this.moonDistance = new int[750];
    }
}
