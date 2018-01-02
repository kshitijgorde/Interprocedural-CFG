import java.awt.Font;
import java.awt.Graphics;
import java.awt.Event;
import java.net.URL;
import java.awt.Canvas;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Choice;
import java.awt.Button;
import java.awt.Label;
import java.awt.TextField;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class moondata127 extends Applet
{
    String versStr;
    Date dat;
    TextField latField;
    TextField longField;
    TextField dateField;
    TextField monthField;
    TextField yearField;
    TextField hoursField;
    TextField offsetField;
    double latitude;
    double longitude;
    String latStr;
    String longStr;
    Double latDouble;
    Double longDouble;
    Label L1;
    Label L2;
    Label L3;
    Label L4;
    Label latLabel;
    Label longLabel;
    Label offsetLabel;
    Button button;
    boolean demo;
    boolean online;
    public String myStr;
    public String email;
    public String param;
    public String wwwStr;
    char deg;
    char c;
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
    double K;
    String error;
    double locOffset;
    boolean windowOK;
    double currentPhase;
    String delta;
    String riseStr;
    String setStr;
    boolean isGraph;
    Choice windowChoice;
    boolean isPhase;
    boolean isData;
    boolean isRiseSet;
    boolean isMap;
    boolean isVisibility;
    moonFrame mf;
    graphicFrame gf;
    scrollFrame sf;
    visFrame vf;
    mapFrame mapf;
    Image map;
    boolean mapVis;
    Image bild;
    boolean isHorizon;
    horizonFrame hf;
    double currentAzim;
    String offsetStr;
    double hours;
    String hoursStr;
    boolean dem;
    
    public void init() {
        System.out.println(String.valueOf(this.versStr) + "  (c) 2004-2009 J. Giesen  -  www. GeoAstro.de");
        this.map = this.getImage(this.getDocumentBase(), "applet/bigmap.gif");
        if (this.getParameter("horizonimage").equals("0")) {
            this.bild = this.getImage(this.getDocumentBase(), "applet/a0.gif");
        }
        else {
            this.bild = this.getImage(this.getDocumentBase(), "applet/a1.gif");
        }
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        final URL url = this.getDocumentBase();
        this.myStr = url.toString();
        this.myStr = String.valueOf(this.myStr) + "1234567890123456789012345";
        this.wwwStr = this.myStr.substring(0, 27);
        this.dat = new Date();
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth() + 1;
        this.year = this.dat.getYear() + 1900;
        this.UT = this.dat.getHours() + this.dat.getTimezoneOffset() / 60.0 + this.dat.getMinutes() / 60.0 + this.dat.getSeconds() / 3600.0;
        this.locOffset = -this.dat.getTimezoneOffset() / 60;
        this.offsetStr = String.valueOf(this.locOffset);
        this.hours = this.dat.getHours() + this.dat.getMinutes() / 60.0;
        this.hoursStr = String.valueOf(Math.round(10.0 * this.hours) / 10.0);
        final double jd = this.JD(this.date, this.month, this.year, this.UT);
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
        gbc.insets = new Insets(0, 20, 0, 0);
        gbl.setConstraints(this.latLabel = new Label("Lat."), gbc);
        this.add(this.latLabel);
        gbc.insets = new Insets(0, 0, 0, 0);
        gbl.setConstraints(this.longLabel = new Label("Long."), gbc);
        this.add(this.longLabel);
        gbl.setConstraints(this.offsetLabel = new Label("Time Zone"), gbc);
        this.add(this.offsetLabel);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 10, 0);
        gbl.setConstraints(this.latField = new TextField(this.latStr, 6), gbc);
        this.add(this.latField);
        this.longField = new TextField(this.longStr, 6);
        gbc.insets = new Insets(0, 10, 10, 0);
        gbl.setConstraints(this.longField, gbc);
        this.add(this.longField);
        gbl.setConstraints(this.offsetField = new TextField(this.offsetStr, 6), gbc);
        this.add(this.offsetField);
        gbc.gridy = 2;
        gbc.insets = new Insets(0, 20, 0, 0);
        gbl.setConstraints(this.L1 = new Label("Date  "), gbc);
        this.add(this.L1);
        gbc.insets = new Insets(0, 0, 0, 0);
        gbl.setConstraints(this.L2 = new Label("  Month  "), gbc);
        this.add(this.L2);
        gbl.setConstraints(this.L3 = new Label("   Year  "), gbc);
        this.add(this.L3);
        this.L4 = new Label("Hours");
        gbc.insets = new Insets(0, 0, 0, 20);
        gbl.setConstraints(this.L4, gbc);
        this.add(this.L4);
        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.insets = new Insets(0, 0, 10, 0);
        (this.dateField = new TextField("", 2)).setText(String.valueOf(this.date));
        gbl.setConstraints(this.dateField, gbc);
        this.add(this.dateField);
        gbc.gridx = 1;
        (this.monthField = new TextField("", 2)).setText(String.valueOf(this.month));
        gbl.setConstraints(this.monthField, gbc);
        this.add(this.monthField);
        gbc.gridx = 2;
        (this.yearField = new TextField("", 4)).setText(String.valueOf(this.year));
        gbl.setConstraints(this.yearField, gbc);
        this.add(this.yearField);
        gbc.gridx = 3;
        gbc.insets = new Insets(0, 0, 10, 20);
        gbl.setConstraints(this.hoursField = new TextField(this.hoursStr, 5), gbc);
        this.add(this.hoursField);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.insets = new Insets(0, 0, 45, 0);
        gbl.setConstraints(this.button = new Button("apply input"), gbc);
        this.add(this.button);
        gbc.gridx = 2;
        gbc.insets = new Insets(0, 0, 40, 0);
        gbl.setConstraints(this.windowChoice = new Choice(), gbc);
        this.windowChoice.addItem("Window...");
        this.windowChoice.addItem("Data");
        this.windowChoice.addItem("Phase");
        this.windowChoice.addItem("Rise/Set");
        this.windowChoice.addItem("Visibility");
        this.windowChoice.addItem("Map");
        this.windowChoice.addItem("Horizon");
        this.windowChoice.addItem("Table");
        this.windowChoice.addItem("Graph Altitude");
        this.windowChoice.addItem("Full Moon");
        this.windowChoice.addItem("Seasons");
        this.windowChoice.addItem("Declination Month");
        this.windowChoice.addItem("Declination Year");
        this.add(this.windowChoice);
        gbc.gridy = 5;
        final Canvas can = new Canvas();
        gbc.insets = new Insets(0, 0, 75, 0);
        gbl.setConstraints(can, gbc);
        this.add(can);
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        this.demo = true;
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
        if (this.online || this.demo) {
            this.dem = true;
        }
        else {
            this.dem = false;
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
        final double H = 24.0 * (JD + 0.5 - JD2);
        if (what == 1) {
            return day;
        }
        if (what == 2) {
            return month;
        }
        if (what == 3) {
            return (int)H;
        }
        return year;
    }
    
    public double getLatitude(String str) {
        if (!str.equals("-")) {
            for (int i = 0; i < str.length(); ++i) {
                this.c = str.charAt(i);
                if (this.c == ',') {
                    str = String.valueOf(str.substring(0, i)) + '.' + str.substring(i + 1, str.length());
                }
            }
            for (int j = 0; j < str.length(); ++j) {
                this.c = str.charAt(j);
                if (this.c != '0' && this.c != '1' && this.c != '2' && this.c != '3' && this.c != '4' && this.c != '5' && this.c != '6' && this.c != '7' && this.c != '8' && this.c != '9' && this.c != '.' && this.c != '-') {
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
                this.c = str.charAt(i);
                if (this.c == ',') {
                    str = String.valueOf(str.substring(0, i)) + '.' + str.substring(i + 1, str.length());
                }
            }
            for (int j = 0; j < str.length(); ++j) {
                this.c = str.charAt(j);
                if (this.c != '0' && this.c != '1' && this.c != '2' && this.c != '3' && this.c != '4' && this.c != '5' && this.c != '6' && this.c != '7' && this.c != '8' && this.c != '9' && this.c != '.' && this.c != '-') {
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
    
    public String HM(double x) {
        String str = "";
        if (x < 0.0) {
            x = Math.abs(x);
        }
        final int HOUR = (int)x;
        final int MIN = (int)Math.round(this.frac(x) * 60.0);
        if (HOUR < 10) {
            str = "0";
        }
        str = String.valueOf(str) + HOUR + ":";
        if (MIN < 10) {
            str = String.valueOf(str) + "0";
        }
        return String.valueOf(str) + MIN;
    }
    
    public String DM(final double x) {
        String str = "";
        double X;
        if (x < 0.0) {
            X = Math.abs(x);
        }
        else {
            X = x;
        }
        final int DEG = (int)X;
        final int MIN = (int)Math.round(this.frac(X) * 60.0);
        if (DEG < 10) {
            str = " " + str;
        }
        str = String.valueOf(str) + DEG + this.deg + " ";
        if (MIN < 10) {
            str = String.valueOf(str) + "0";
        }
        return String.valueOf(str) + MIN + "'";
    }
    
    public String DMS(final double x) {
        String str = "";
        double X;
        if (x < 0.0) {
            X = Math.abs(x);
        }
        else {
            X = x;
        }
        final int DEG = (int)X;
        final int MIN = (int)(this.frac(X) * 60.0);
        final double SEC = 60.0 * (this.frac(X) * 60.0 - MIN);
        if (x < 0.0) {
            str = "-";
        }
        else {
            str = "+";
        }
        if (DEG < 10) {
            str = " " + str;
        }
        str = String.valueOf(str) + DEG + this.deg + " ";
        if (MIN < 10) {
            str = String.valueOf(str) + "0";
        }
        str = String.valueOf(str) + MIN + "' ";
        if ((int)Math.round(SEC) < 10) {
            str = String.valueOf(str) + "0";
        }
        return String.valueOf(str) + Math.round(SEC) + "''";
    }
    
    public String tierkreisStr(final double longitudo) {
        final String[] tkArray = { "Ari", "Tau", "Gem", "Can", "Leo", "Vir", "Lib", "Sco", "Sag", "Cap", "Aqu", "Pis" };
        return tkArray[(int)(longitudo / 30.0)];
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
    
    public void doFullMoon() {
        final String[] dataStr = new String[250];
        final PHASE phs = new PHASE();
        int nData = 0;
        String str = String.valueOf(this.versStr) + "\n" + "© 2004-2009 J. Giesen  - www.GeoAstro.de" + "\n";
        if (this.locOffset >= 0.0) {
            str = String.valueOf(str) + "UT +" + this.locOffset + " h";
        }
        else {
            str = String.valueOf(str) + "UT " + this.locOffset + " h";
        }
        for (int y = 0; y <= 15; ++y) {
            for (int i = 0; i < 14; ++i) {
                dataStr[nData] = phs.PHASE(this.year + y, (int)this.locOffset, this.dem)[i];
                final String mStr = dataStr[nData].substring(10, 13);
                if (i > 0 && mStr.equals(dataStr[nData - 1].substring(10, 13))) {
                    final String[] array = dataStr;
                    final int n = nData;
                    array[n] = String.valueOf(array[n]) + " Blue Moon!";
                }
                final String[] array2 = dataStr;
                final int n2 = nData;
                array2[n2] = String.valueOf(array2[n2]) + "\n";
                ++nData;
            }
            final String[] array3 = dataStr;
            final int n3 = nData - 1;
            array3[n3] = String.valueOf(array3[n3]) + "\n";
        }
        dataStr[0] = "\n" + str + "\n" + dataStr[0];
        final scrollFrame SF = new scrollFrame("Full Moon " + this.year + " to " + (this.year + 15), nData, dataStr, this.dem);
        SF.resize(300, 260);
        SF.show();
    }
    
    public void doSeasons() {
        final String[] dataStr = new String[5];
        final computeSeasons seasons = new computeSeasons(this.year);
        dataStr[0] = "\n" + seasons.spring() + "\n";
        dataStr[1] = String.valueOf(seasons.summer()) + "\n";
        dataStr[2] = String.valueOf(seasons.autumn()) + "\n";
        dataStr[3] = String.valueOf(seasons.winter()) + "\n";
        final scrollFrame SF = new scrollFrame("Seasons " + this.year, 4, dataStr, this.dem);
        SF.resize(300, 260);
        SF.show();
    }
    
    public void doTable(final boolean graphOK) {
        final double[] alt = new double[9200];
        final String[] dataStr = new String[400];
        final double[] t = new double[9200];
        final double[] T = new double[370];
        final double[][] d = new double[370][3];
        int nDataGraph = 0;
        int nData = 1;
        dataStr[1] = "© 2004-2009 J. Giesen  - www.GeoAstro.de\n";
        final String[] array = dataStr;
        final int n = 1;
        array[n] = String.valueOf(array[n]) + "Lat. " + this.latitude;
        if (this.latitude >= 0.0) {
            final String[] array2 = dataStr;
            final int n2 = 1;
            array2[n2] = String.valueOf(array2[n2]) + " N, ";
        }
        else {
            final String[] array3 = dataStr;
            final int n3 = 1;
            array3[n3] = String.valueOf(array3[n3]) + " S, ";
        }
        final String[] array4 = dataStr;
        final int n4 = 1;
        array4[n4] = String.valueOf(array4[n4]) + "Long. " + this.longitude;
        if (this.longitude >= 0.0) {
            final String[] array5 = dataStr;
            final int n5 = 1;
            array5[n5] = String.valueOf(array5[n5]) + " E";
        }
        else {
            final String[] array6 = dataStr;
            final int n6 = 1;
            array6[n6] = String.valueOf(array6[n6]) + " W";
        }
        if (this.locOffset >= 0.0) {
            final String[] array7 = dataStr;
            final int n7 = 1;
            array7[n7] = String.valueOf(array7[n7]) + ", UT +" + this.locOffset;
        }
        else {
            final String[] array8 = dataStr;
            final int n8 = 1;
            array8[n8] = String.valueOf(array8[n8]) + ", UT " + this.locOffset;
        }
        final String[] array9 = dataStr;
        final int n9 = 1;
        array9[n9] = String.valueOf(array9[n9]) + " h\n\n";
        final String[] array10 = dataStr;
        final int n10 = 1;
        array10[n10] = String.valueOf(array10[n10]) + "         Transit  Alt.   Ill.    Dist.\n\n";
        nData = 2;
        final String[] monthArray = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        final double jd2;
        double jd = jd2 = this.JD(1, 1, this.year, 0.0 - this.locOffset);
        for (int i = 0; i <= 9000; ++i) {
            t[i] = jd2 + i / 24.0;
            final MOON moon = new MOON(t[i], this.latitude, this.longitude);
            alt[i] = moon.altitude();
        }
        int k = 0;
        for (int j = 1; j < 9000; ++j) {
            if (alt[j] > alt[j - 1] && alt[j] > alt[j + 1]) {
                T[k] = t[j];
                ++k;
            }
        }
        int maxM = 0;
        int maxD = 0;
        int minDist = 500000;
        double minDistJD = 0.0;
        double maxElev = 0.0;
        double maxElevJD = 0.0;
        final double fullPhase = 0.0;
        double PH = 0.0;
        int nFull = 0;
        final double[] fullJD = new double[30];
        int maxDist = 0;
        double maxDistJD = 0.0;
        double minElev = 90.0;
        double minElevJD = 0.0;
        final int maxD2 = 0;
        final int maxH1 = 0;
        final double[] JD2 = new double[365];
        int noTransitDay = -100;
        for (int l = 0; l < 365 && T[l] <= this.JD(1, 1, this.year + 1, 0.0 - this.locOffset); ++l) {
            final double jd3 = T[l] - 0.041666666666666664;
            if (jd3 > this.JD(31, 12, this.year, 24.0)) {
                break;
            }
            int maxMin = 0;
            for (int min = 0; min <= 180; ++min) {
                jd = jd3 + min / 1440.0;
                final MOON moon = new MOON(jd, this.latitude, this.longitude);
                alt[min] = moon.altitude();
            }
            double maxAlt = 0.0;
            maxMin = 0;
            for (int min2 = 1; min2 < 180; ++min2) {
                if (alt[min2] > alt[min2 - 1] && alt[min2] > alt[min2 + 1]) {
                    maxAlt = alt[min2];
                    maxMin = min2;
                }
            }
            final double jd4 = T[l] - 0.041666666666666664 + maxMin / 1440.0;
            JD2[l] = jd4;
            d[l][0] = jd3;
            int maxH2 = this.caldat(3, jd4);
            maxD = this.caldat(1, jd4);
            maxM = this.caldat(2, jd4);
            if (maxMin >= 60) {
                maxMin -= 60;
            }
            if (maxMin >= 60) {
                maxMin -= 60;
            }
            maxH2 += (int)this.locOffset;
            if (maxH2 >= 24) {
                maxH2 -= 24;
                ++maxD;
            }
            if (maxH2 < 0) {
                maxH2 += 24;
                --maxD;
            }
            String str1 = String.valueOf(maxD);
            if (maxD < 10) {
                str1 = " " + maxD;
            }
            String str2 = maxH2 + ":";
            if (maxH2 < 10) {
                str2 = "0" + str2;
            }
            if (maxMin < 10) {
                str2 = String.valueOf(str2) + "0" + maxMin;
            }
            else {
                str2 = String.valueOf(str2) + maxMin;
            }
            final MOON moon = new MOON(jd4, this.latitude, this.longitude);
            PH = moon.phase();
            final double ph = Math.round(100.0 * PH) / 100.0;
            final int dist = (int)Math.round(moon.computeR());
            maxAlt -= this.moonParal(maxAlt, dist);
            final String str3 = String.valueOf(Math.round(10.0 * maxAlt) / 10.0);
            if (dist < minDist) {
                minDist = dist;
                minDistJD = jd4;
            }
            if (maxAlt > maxElev) {
                maxElev = maxAlt;
                maxElevJD = jd4;
            }
            if (maxAlt < minElev) {
                minElev = maxAlt;
                minElevJD = jd4;
            }
            if (PH > 0.997) {
                fullJD[nFull] = jd4;
                ++nFull;
            }
            if (dist > maxDist) {
                maxDist = dist;
                maxDistJD = jd4;
            }
            String str4;
            for (str4 = String.valueOf(ph); str4.length() < 4; str4 = String.valueOf(str4) + "0") {}
            final String str5 = String.valueOf(dist);
            dataStr[nData] = String.valueOf(monthArray[maxM - 1]) + "  " + str1 + "   " + str2 + "   " + str3 + "   " + str4 + "   " + str5 + "\n";
            ++nData;
            if (maxM == this.month) {
                d[nDataGraph][0] = jd4;
                d[nDataGraph][1] = maxAlt;
                d[nDataGraph][2] = PH;
                ++nDataGraph;
            }
        }
        noTransitDay = 0;
        for (int m = 0; m < 365; ++m) {
            boolean dayOK = false;
            for (int j2 = 0; j2 < 365; ++j2) {
                final double jdMidNight = (int)(JD2[m] + this.locOffset / 24.0) + 0.5;
                final double jdMidNight2 = jdMidNight + 1.0;
                if (JD2[j2] + this.locOffset / 24.0 > jdMidNight && JD2[j2] + this.locOffset / 24.0 < jdMidNight2) {
                    dayOK = true;
                    break;
                }
            }
            if (!dayOK) {
                maxD = this.caldat(1, JD2[m] + this.locOffset / 24.0 + 1.0);
                maxM = this.caldat(2, JD2[m] + this.locOffset / 24.0 + 1.0);
                if (JD2[m] != 0.0 && JD2[m] + this.locOffset / 24.0 + 1.0 < this.JD(31, 12, this.year, 24.0) && maxM == this.month && maxD != 0) {
                    noTransitDay = maxD;
                }
            }
        }
        if (!graphOK) {
            maxD = this.caldat(1, minDistJD);
            maxM = this.caldat(2, minDistJD);
            MOON moon = new MOON(minDistJD, this.latitude, this.longitude);
            double ph = Math.round(100.0 * moon.phase()) / 100.0;
            dataStr[nData] = "\nMinimum distance " + Math.round(minDist) + " km on " + monthArray[maxM - 1] + " " + maxD + ",  Illum. fraction " + ph + "%";
            ++nData;
            maxD = this.caldat(1, maxDistJD);
            maxM = this.caldat(2, maxDistJD);
            moon = new MOON(maxDistJD, this.latitude, this.longitude);
            ph = Math.round(100.0 * moon.phase()) / 100.0;
            dataStr[nData] = "\nMaximum distance " + Math.round(maxDist) + " km on " + monthArray[maxM - 1] + " " + maxD + ",  Illum. fraction " + ph + "%" + "\n";
            ++nData;
            maxD = this.caldat(1, maxElevJD);
            maxM = this.caldat(2, maxElevJD);
            moon = new MOON(maxElevJD, this.latitude, this.longitude);
            ph = Math.round(100.0 * moon.phase()) / 100.0;
            dataStr[nData] = "\nMaximum transit altitude " + Math.round(10.0 * maxElev) / 10.0 + this.deg + " on " + monthArray[maxM - 1] + " " + maxD + ",  Illum. fraction " + ph + "%" + "\n";
            ++nData;
            maxD = this.caldat(1, minElevJD);
            maxM = this.caldat(2, minElevJD);
            moon = new MOON(minElevJD, this.latitude, this.longitude);
            ph = Math.round(100.0 * moon.phase()) / 100.0;
            dataStr[nData] = "Minumum transit altitude " + Math.round(10.0 * minElev) / 10.0 + this.deg + " on " + monthArray[maxM - 1] + " " + maxD + ",  Illum. fraction " + ph + "%" + "\n" + "\n";
            final String[] array11 = dataStr;
            final int n11 = nData;
            array11[n11] = String.valueOf(array11[n11]) + "Transits near Full Moon\n";
            ++nData;
            for (int i2 = 0; i2 < nFull; ++i2) {
                maxD = this.caldat(1, fullJD[i2] + this.locOffset / 24.0);
                maxM = this.caldat(2, fullJD[i2] + this.locOffset / 24.0);
                moon = new MOON(fullJD[i2], this.latitude, this.longitude);
                final double maxAlt = moon.altitude();
                final int dist = (int)Math.round(moon.computeR());
                String str1 = String.valueOf(maxD);
                if (maxD < 10) {
                    str1 = "0" + str1;
                }
                double tTrans = 24.0 * this.frac(fullJD[i2]) + this.locOffset + 12.0;
                if (tTrans > 24.0) {
                    tTrans -= 24.0;
                }
                final String str2 = String.valueOf(this.HM(tTrans));
                dataStr[nData] = String.valueOf(monthArray[maxM - 1]) + " " + str1 + "  " + str2 + ",  Alt. " + Math.round(10.0 * maxAlt) / 10.0 + this.deg + "  Dist. " + dist + " km" + "\n";
                ++nData;
            }
            final scrollFrame SF = new scrollFrame("Moon Data " + this.year, nData, dataStr, this.dem);
            SF.resize(410, 260);
            SF.show();
        }
        else {
            String str1 = new StringBuffer().append(Math.abs(this.latitude)).append(this.deg).toString();
            if (this.latitude >= 0.0) {
                str1 = String.valueOf(str1) + " N";
            }
            else {
                str1 = String.valueOf(str1) + " S";
            }
            final graphFrame GF = new graphFrame("Moon Transit Altitude, " + monthArray[this.month - 1] + " " + this.year + ", " + str1, this.locOffset, nDataGraph, d, noTransitDay, this.demo);
            GF.resize(515, 330);
            GF.show();
        }
    }
    
    public double moonParal(final double elev, final double distance) {
        final double horParal = 8.794 / (distance / 1.4959787E8);
        final double paral = Math.cos(this.K * elev) * Math.sin(this.K * horParal / 3600.0);
        return Math.asin(paral) / this.K;
    }
    
    public double moonDec(final double T) {
        final double P2 = 6.283185307179586;
        final double ARC = 206264.8062;
        final double coseps = 0.917482062;
        final double sineps = 0.397777156;
        final double K = 0.017453292519943295;
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
        final double DEC = 57.29577951308232 * Math.atan2(Z, rho);
        return DEC;
    }
    
    public void doDeclination() {
        final String[] monthArray = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        final int nData = this.daysInMonth(this.month - 1, this.year - 1900);
        final double[][] d = new double[32][2];
        for (int i = 1; i <= nData; ++i) {
            final double jd = this.JD(i, this.month, this.year, this.hours - this.locOffset);
            final double T = (jd - 2451545.0) / 36525.0;
            final MOON moon = new MOON(jd, this.latitude, this.longitude);
            d[i][0] = this.moonDec(T);
            d[i][1] = moon.phase();
        }
        final declinationFrame DF = new declinationFrame("Moon Declination " + monthArray[this.month - 1] + " " + this.year + " at " + this.hours + " h", nData, d, this.demo);
        DF.resize(500, 305);
        DF.show();
    }
    
    public void doDeclinationYear() {
        final String[] monthArray = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        final double[][] d = new double[368][2];
        final double jd0 = this.JD(1, 1, this.year, this.hours - this.locOffset);
        int nData = 0;
        for (int m = 0; m < 12; ++m) {
            nData += this.daysInMonth(m, this.year - 1900);
        }
        for (int i = 1; i <= nData; ++i) {
            final double jd2 = jd0 + i;
            final double T = (jd2 - 2451545.0) / 36525.0;
            final MOON moon = new MOON(jd2, this.latitude, this.longitude);
            d[i][0] = this.moonDec(T);
            d[i][1] = moon.phase();
        }
        final declinationFrameYear DFYear = new declinationFrameYear("Moon Declination  " + this.year + " at " + this.hours + " h", this.year, nData, d, this.demo);
        DFYear.resize(800, 535);
        DFYear.show();
    }
    
    public void calculate() {
        this.error = "";
        try {
            this.date = Integer.parseInt(this.dateField.getText());
        }
        catch (NumberFormatException ex) {
            this.error = "input error";
        }
        try {
            this.month = Integer.parseInt(this.monthField.getText());
        }
        catch (NumberFormatException ex2) {
            this.error = "input error";
        }
        try {
            this.year = Integer.parseInt(this.yearField.getText());
        }
        catch (NumberFormatException ex3) {
            this.error = "input error";
        }
        try {
            final Double hoursDouble = Double.valueOf(this.hoursField.getText());
            this.hours = hoursDouble;
        }
        catch (NumberFormatException ex4) {
            this.error = "input error";
        }
        try {
            final Double offsetDouble = Double.valueOf(this.offsetField.getText());
            this.locOffset = offsetDouble;
        }
        catch (NumberFormatException ex5) {
            this.error = "input error";
        }
        this.UT = this.hours - this.locOffset;
        this.dat.setDate(this.date);
        this.dat.setMonth(this.month - 1);
        this.dat.setYear(this.year - 1900);
        final int hour = (int)this.hours;
        this.dat.setHours(hour);
        final int minutes = (int)(60.0 * this.frac(this.hours));
        this.dat.setMinutes(minutes);
        this.dat.setSeconds(0);
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
        final String[] dataStr = new String[430];
        dataStr[0] = "© 2004-2009 J. Giesen  - www.GeoAstro.de\n";
        dataStr[1] = "Lat.  " + this.latitude;
        if (this.latitude >= 0.0) {
            final String[] array = dataStr;
            final int n = 1;
            array[n] = String.valueOf(array[n]) + " N, ";
        }
        else {
            final String[] array2 = dataStr;
            final int n2 = 1;
            array2[n2] = String.valueOf(array2[n2]) + " S, ";
        }
        dataStr[2] = "Long. " + this.longitude;
        if (this.longitude >= 0.0) {
            final String[] array3 = dataStr;
            final int n3 = 2;
            array3[n3] = String.valueOf(array3[n3]) + " E";
        }
        else {
            final String[] array4 = dataStr;
            final int n4 = 2;
            array4[n4] = String.valueOf(array4[n4]) + " W";
        }
        if (this.locOffset >= 0.0) {
            final String[] array5 = dataStr;
            final int n5 = 2;
            array5[n5] = String.valueOf(array5[n5]) + ", UT +" + this.locOffset + " h" + "\n";
        }
        else {
            final String[] array6 = dataStr;
            final int n6 = 2;
            array6[n6] = String.valueOf(array6[n6]) + ", UT " + this.locOffset + " h" + "\n";
        }
        dataStr[1] = String.valueOf(dataStr[1]) + dataStr[2];
        dataStr[2] = "Date  " + this.date + "\n";
        dataStr[3] = "Month " + this.month + "\n";
        dataStr[4] = "Year  " + this.year + "\n";
        dataStr[5] = "Hours " + this.hours + "\n";
        final String[] array7 = dataStr;
        final int n7 = 5;
        array7[n7] = String.valueOf(array7[n7]) + this.dat.toString() + "\n";
        this.jd = this.JD(this.date, this.month, this.year, this.UT);
        System.out.println("Jul. Day " + this.jd);
        dataStr[6] = "Jul. Day " + this.jd + "\n";
        this.moon = new Moon(this.dat, this.latitude, this.longitude, this.locOffset);
        this.currentPhase = this.moon.phase();
        System.out.println(this.dat.toString());
        double eclLong = this.moon.moonLong();
        this.riseStr = this.moon.riseString();
        this.setStr = this.moon.setString();
        System.out.println("Rise " + this.moon.riseString());
        System.out.println("Set  " + this.moon.setString());
        this.repaint();
        this.moon = new Moon(this.dat, this.latitude, this.longitude, this.locOffset);
        this.ra = this.moon.alpha();
        this.dec = this.moon.delta();
        this.moon = new Moon(this.dat, this.latitude, this.longitude, this.locOffset);
        final double moonHours = this.moon.moon_hRise() - this.moon.moon_hSet();
        System.out.println("RA (deg) " + this.ra);
        System.out.println("RA (h)   " + this.HMS(this.ra));
        dataStr[7] = "RA       " + Math.round(1000.0 * this.ra) / 1000.0 + '°' + "\n";
        dataStr[8] = "RA       " + this.HMS(this.ra) + "\n";
        System.out.println("Declin. " + this.dec);
        dataStr[9] = "Declin   " + Math.round(1000.0 * this.dec) / 1000.0 + '°' + "\n";
        dataStr[10] = "Declin   " + this.DMS(this.dec) + "\n";
        dataStr[11] = "Ecl. Long.   " + this.DM(eclLong) + "  (" + this.DM(eclLong % 30.0) + " " + this.tierkreisStr(eclLong) + ")" + "\n";
        MoonDistance moonDist = new MoonDistance(this.jd);
        final String[] array8 = dataStr;
        final int n8 = 11;
        array8[n8] = String.valueOf(array8[n8]) + "Distance   " + Math.round(moonDist.computeR()) + " km" + "\n";
        this.h = this.THETA0(this.jd) + this.longitude - this.ra;
        if (this.h < 0.0) {
            this.h += 360.0;
        }
        this.gha = this.THETA0(this.jd) - this.ra;
        if (this.gha < 0.0) {
            this.gha += 360.0;
        }
        System.out.println("Greenwich Sid. Time (deg) " + this.THETA0(this.jd));
        System.out.println("Hour angle " + this.h);
        dataStr[12] = "Grw. Sid. Time   " + this.HMS(this.THETA0(this.jd)) + "\n";
        dataStr[13] = "Loc. Sid. Time   " + this.HMS(15.0 * this.moon.LM_Sidereal_Time(this.jd, -this.longitude)) + "\n";
        dataStr[14] = "Loc. Hour Angle  " + Math.round(100.0 * this.h) / 100.0 + '°' + "\n";
        this.azim = Math.atan2(Math.sin(this.K * this.h), Math.cos(this.K * this.h) * Math.sin(this.K * this.latitude) - Math.tan(this.K * this.dec) * Math.cos(this.K * this.latitude)) / this.K;
        this.azim += 180.0;
        if (this.azim > 360.0) {
            this.azim -= 360.0;
        }
        this.elev = Math.sin(this.K * this.latitude) * Math.sin(this.K * this.dec) + Math.cos(this.K * this.latitude) * Math.cos(this.K * this.dec) * Math.cos(this.K * this.h);
        this.elev = Math.asin(this.elev) / this.K;
        final double horParal = 3288.90917195;
        double paral = Math.cos(this.K * this.elev) * Math.sin(this.K * horParal / 3600.0);
        paral = Math.asin(paral) / this.K;
        this.elev -= paral;
        dataStr[15] = "Elev.          " + Math.round(100.0 * this.elev) / 100.0 + '°' + "\n";
        dataStr[16] = "Azimuth        " + Math.round(100.0 * this.azim) / 100.0 + '°' + "\n";
        this.moon = new Moon(this.dat, this.latitude, this.longitude, this.locOffset);
        final double ph1 = this.moon.phase1();
        double ph2 = this.moon.phase();
        final double ph3 = this.moon.phase2();
        if (ph2 > ph1 && ph2 < ph3) {
            this.delta = "(+)";
        }
        if (ph2 < ph1 && ph2 > ph3) {
            this.delta = "(-)";
        }
        dataStr[17] = "Illum. Fraction " + Math.round(1000.0 * this.moon.phase()) / 10.0 + " % " + this.delta + "\n" + "\n";
        final String[] array9 = dataStr;
        final int n9 = 17;
        array9[n9] = String.valueOf(array9[n9]) + "      ";
        String[] monthArray = new String[12];
        monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        dataStr[18] = "      ";
        final int[][] p = new int[32][13];
        for (int m = 1; m <= 12; ++m) {
            final String[] array10 = dataStr;
            final int n10 = 17;
            array10[n10] = String.valueOf(array10[n10]) + monthArray[m - 1] + "    ";
        }
        final String[] array11 = dataStr;
        final int n11 = 18;
        array11[n11] = String.valueOf(array11[n11]) + "\n";
        int nData = 18;
        if (this.isData || this.isPhase) {
            final Date calcDat = new Date();
            calcDat.setYear(this.year - 1900);
            for (int i = 1; i <= 31; ++i) {
                ++nData;
                String str;
                if (i < 10) {
                    str = " " + i + " ";
                }
                else {
                    str = i + " ";
                }
                dataStr[nData] = str;
                for (int j = 1; j <= 12; ++j) {
                    String str2;
                    if (i <= this.daysInMonth(j - 1, this.year - 1900)) {
                        calcDat.setDate(i);
                        calcDat.setMonth(j - 1);
                        calcDat.setHours((int)this.locOffset);
                        calcDat.setMinutes(0);
                        calcDat.setSeconds(0);
                        this.moon = new Moon(calcDat, this.latitude, this.longitude, this.locOffset);
                        ph2 = Math.round(100.0 * this.moon.phase()) / 100.0;
                        p[i][j] = (int)Math.round(255.0 * this.moon.phase());
                        str2 = String.valueOf(ph2);
                        if (str2.length() < 4) {
                            str2 = String.valueOf(str2) + "0";
                        }
                    }
                    else {
                        str2 = "    ";
                        p[i][j] = 300;
                    }
                    final String[] array12 = dataStr;
                    final int n12 = nData;
                    array12[n12] = String.valueOf(array12[n12]) + "  " + str2 + " ";
                }
                if (nData % 5 == 0 && (this.online || this.demo)) {
                    dataStr[nData] = " D E M O";
                }
                final String[] array13 = dataStr;
                final int n13 = nData;
                array13[n13] = String.valueOf(array13[n13]) + "\n";
            }
            final String[] array14 = dataStr;
            final int n14 = nData;
            array14[n14] = String.valueOf(array14[n14]) + "\n";
            final String[] array15 = dataStr;
            final int n15 = nData;
            array15[n15] = String.valueOf(array15[n15]) + "Rise, Set, Illuminated Fraction at 0 UT, Duration of Visibility, Distance, Ecl. Long.\n";
            final double[][] visHours = new double[13][32];
            for (int k = 1; k <= 12; ++k) {
                calcDat.setMonth(k - 1);
                for (int d = 1; d <= this.daysInMonth(k - 1, this.year - 1900); ++d) {
                    ++nData;
                    calcDat.setDate(d);
                    calcDat.setHours((int)this.locOffset);
                    calcDat.setMinutes(0);
                    this.moon = new Moon(calcDat, this.latitude, this.longitude, this.locOffset);
                    eclLong = this.moon.moonLong();
                    String str = String.valueOf(d);
                    if (d < 10) {
                        str = "0" + d;
                    }
                    String pStr = String.valueOf(Math.round(100.0 * this.moon.phase()) / 100.0);
                    if (pStr.length() < 4) {
                        pStr = String.valueOf(pStr) + "0";
                    }
                    final String str2 = this.moon.riseString();
                    final String str3 = this.moon.setString();
                    double diff = this.moon.moon_hSet() - this.moon.moon_hRise();
                    if (diff < 0.0) {
                        diff += 24.0;
                    }
                    visHours[k][d] = diff;
                    String str4;
                    if (diff < 10.0) {
                        str4 = " ";
                    }
                    else {
                        str4 = "";
                    }
                    for (str4 = String.valueOf(str4) + Math.round(100.0 * diff) / 100.0; str4.length() < 5; str4 = String.valueOf(str4) + "0") {}
                    dataStr[nData] = this.year + " " + monthArray[k - 1] + " " + str + "  " + str2 + "  " + str3 + "  " + pStr + "  " + str4;
                    final double jdMoon = this.JD(d, k, this.year, 0.0);
                    moonDist = new MoonDistance(jdMoon);
                    final String[] array16 = dataStr;
                    final int n16 = nData;
                    array16[n16] = String.valueOf(array16[n16]) + "  " + Math.round(moonDist.computeR()) + " km  " + this.tierkreisStr(eclLong) + " " + this.DM(eclLong % 30.0) + "\n";
                    if (nData % 5 == 0 && (this.online || this.demo)) {
                        dataStr[nData] = " D E M O\n";
                    }
                }
            }
            double min = 100.0;
            double max = 0.0;
            int maxM = 0;
            int maxD = 0;
            int minM = 0;
            int minD = 0;
            for (int l = 1; l <= 12; ++l) {
                for (int d2 = 1; d2 <= this.daysInMonth(l - 1, this.year - 1900); ++d2) {
                    if (visHours[l][d2] > max) {
                        max = visHours[l][d2];
                        maxM = l;
                        maxD = d2;
                    }
                    if (visHours[l][d2] < min) {
                        min = visHours[l][d2];
                        minM = l;
                        minD = d2;
                    }
                }
            }
            ++nData;
            dataStr[nData] = "Min. above horizon " + Math.round(100.0 * min) / 100.0 + " hours, " + monthArray[minM - 1] + " " + minD + ",  " + Math.round(100 * p[minD][minM] / 255.0) / 100.0 + "%" + "\n";
            final String[] array17 = dataStr;
            final int n17 = nData;
            array17[n17] = String.valueOf(array17[n17]) + "Max. above horizon " + Math.round(100.0 * max) / 100.0 + " hours, " + monthArray[maxM - 1] + " " + maxD + ",  " + Math.round(100 * p[maxD][maxM] / 255.0) / 100.0 + "%";
        }
        if (this.isData) {
            (this.sf = new scrollFrame("Moon Data", nData, dataStr, this.dem)).resize(550, 260);
            this.sf.show();
        }
        if (this.isPhase) {
            boolean demoVersion = false;
            if (this.online || this.demo) {
                demoVersion = true;
            }
            (this.gf = new graphicFrame("Moon " + this.year + " at " + (int)Math.round(this.UT) + " UT", p, demoVersion)).resize(820, 380);
            this.gf.show();
        }
        if (this.isRiseSet) {
            (this.mf = new moonFrame(this.dat, this.versStr, this.latitude, this.longitude, this.locOffset, this.demo)).resize(765, 475);
            this.mf.show();
        }
        if (this.isVisibility) {
            (this.vf = new visFrame(this.versStr, this.year, this.latitude, this.longitude, this.locOffset, this.demo)).resize(820, 450);
            this.vf.show();
        }
        if (this.isMap) {
            (this.mapf = new mapFrame("Moon " + this.year + " " + monthArray[this.month - 1] + " " + this.date + " at " + this.HM(this.UT) + " UT", this.map, this.dec, this.gha, this.currentPhase, this.latitude, this.longitude, this.demo)).resize(510, 280);
            this.mapf.show();
        }
        if (this.isHorizon) {
            final int xS = 256;
            final int xLL = 30;
            double xx;
            if (this.latitude >= this.dec) {
                xx = (180.0 - this.currentAzim) * (xS - xLL) / 180.0;
            }
            else if (this.currentAzim < 180.0) {
                xx = this.currentAzim * (xS - xLL) / 180.0;
            }
            else {
                xx = -(360.0 - this.currentAzim) * (xS - xLL) / 180.0;
            }
            int NorthSouth;
            if (this.latitude >= this.dec) {
                xx = xS - xx;
                NorthSouth = 1;
            }
            else {
                xx += xS;
                NorthSouth = 0;
            }
            final int xSun = (int)Math.round(xx);
            (this.hf = new horizonFrame("The Moon, " + this.year + " " + monthArray[this.month - 1] + " " + this.date + " at " + this.HM(this.UT) + " UT", this.bild, this.demo, this.elev, xSun, this.currentPhase, NorthSouth)).resize(510, 260);
            this.hf.show();
        }
    }
    
    public boolean keyDown(final Event event, final int code) {
        int n = 0;
        if (code != 104 && code != 100 && code != 109 && code != 72 && code != 68 && code != 77 && code != 121 && code != 89) {
            return false;
        }
        if (code == 121) {
            ++this.year;
            this.yearField.setText(String.valueOf(this.year));
            this.calculate();
            this.repaint();
            return true;
        }
        if (code == 89) {
            --this.year;
            this.yearField.setText(String.valueOf(this.year));
            this.calculate();
            this.repaint();
            return true;
        }
        if (code == 72 || code == 104) {
            if (code == 72) {
                --this.hours;
                if (this.hours < 0.0) {
                    this.hours += 24.0;
                    --this.date;
                }
            }
            else {
                ++this.hours;
            }
            if (this.date == 0) {
                --this.month;
                if (this.month == 0) {
                    this.month = 12;
                    --this.year;
                }
                this.date = this.daysInMonth(this.month - 1, this.year - 1900);
            }
            if (this.hours >= 24.0) {
                this.hours -= 24.0;
                ++this.date;
                n = this.daysInMonth(this.month - 1, this.year - 1900);
                if (this.date > n) {
                    this.date = 1;
                    ++this.month;
                    if (this.month == 13) {
                        this.month = 1;
                        ++this.year;
                    }
                }
            }
            this.hoursField.setText(String.valueOf(Math.round(100.0 * this.hours) / 100.0));
            this.dateField.setText(String.valueOf(this.date));
            this.monthField.setText(String.valueOf(this.month));
            this.yearField.setText(String.valueOf(this.year));
            this.calculate();
            this.repaint();
            return true;
        }
        if (code == 100 || code == 68) {
            if (code == 68) {
                --this.date;
                if (this.date == 0) {
                    --this.month;
                    if (this.month == 0) {
                        this.month = 12;
                        --this.year;
                    }
                    this.date = this.daysInMonth(this.month - 1, this.year - 1900);
                }
            }
            else {
                ++this.date;
                n = this.daysInMonth(this.month - 1, this.year - 1900);
                if (this.date > n) {
                    this.date = 1;
                    ++this.month;
                    if (this.month == 13) {
                        this.month = 1;
                        ++this.year;
                    }
                }
            }
            this.dateField.setText(String.valueOf(this.date));
            this.monthField.setText(String.valueOf(this.month));
            this.yearField.setText(String.valueOf(this.year));
            this.calculate();
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
            n = this.daysInMonth(this.month - 1, this.year - 1900);
            if (this.date > n) {
                this.date = 1;
                ++this.month;
                if (this.month == 13) {
                    this.month = 1;
                    ++this.year;
                }
            }
            if (this.month == 0) {
                this.month = 12;
                --this.year;
            }
            if (this.month == 13) {
                this.month = 1;
                ++this.year;
            }
            this.dateField.setText(String.valueOf(this.date));
            this.monthField.setText(String.valueOf(this.month));
            this.yearField.setText(String.valueOf(this.year));
            this.calculate();
            this.repaint();
            return true;
        }
        return true;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof Choice) {
            if (event.target == this.windowChoice) {
                if (this.windowChoice.getSelectedItem().equals("Data")) {
                    this.isData ^= true;
                    this.calculate();
                    this.isData ^= true;
                }
                if (this.windowChoice.getSelectedItem().equals("Phase")) {
                    this.isPhase ^= true;
                    this.calculate();
                    this.isPhase ^= true;
                }
                if (this.windowChoice.getSelectedItem().equals("Rise/Set")) {
                    this.isRiseSet ^= true;
                    this.calculate();
                    this.isRiseSet ^= true;
                }
                if (this.windowChoice.getSelectedItem().equals("Visibility")) {
                    this.isVisibility ^= true;
                    this.calculate();
                    this.isVisibility ^= true;
                }
                if (this.windowChoice.getSelectedItem().equals("Map")) {
                    this.isMap ^= true;
                    this.calculate();
                    this.isMap ^= true;
                }
                if (this.windowChoice.getSelectedItem().equals("Horizon")) {
                    this.isHorizon ^= true;
                    this.calculate();
                    this.isHorizon ^= true;
                }
                if (this.windowChoice.getSelectedItem().equals("Table")) {
                    this.doTable(false);
                }
                if (this.windowChoice.getSelectedItem().equals("Full Moon")) {
                    this.doFullMoon();
                }
                if (this.windowChoice.getSelectedItem().equals("Seasons")) {
                    this.doSeasons();
                }
                if (this.windowChoice.getSelectedItem().equals("Declination Month")) {
                    this.doDeclination();
                }
                if (this.windowChoice.getSelectedItem().equals("Graph Altitude")) {
                    this.doTable(true);
                }
                if (this.windowChoice.getSelectedItem().equals("Declination Year")) {
                    this.doDeclinationYear();
                }
                this.windowChoice.select(0);
            }
            return true;
        }
        if (event.target instanceof Button && event.target == this.button) {
            this.calculate();
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
        return (int)num + 5255;
    }
    
    public void paint(final Graphics g) {
        g.setFont(new Font("Courier", 0, 12));
        g.setColor(Color.red);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        g.setColor(Color.black);
        final int oben = 190;
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
        final double dec1 = this.moonDec((this.JD(this.date, this.month, this.year, this.UT) - 2451545.0) / 36525.0);
        final double dec2 = this.moonDec(0.5 + (this.JD(this.date, this.month, this.year, this.UT) - 2451545.0) / 36525.0);
        String str;
        if (dec2 > dec1) {
            str = " (+)";
        }
        else {
            str = " (-)";
        }
        g.drawString("Declination    : " + Math.round(100.0 * dec1) / 100.0 + this.deg + str, 30, oben + 75);
        g.drawString("Right Ascension: " + this.HMS(this.ra), 30, oben + 90);
        g.setColor(Color.red);
        if (this.demo) {
            g.setFont(new Font("Helvetica", 0, 48));
            g.drawString("D E M O", 70, 225);
        }
        g.drawString(this.error, 30, oben + 75);
        g.setColor(Color.black);
        g.setFont(new Font("Courier", 0, 10));
        g.drawString(String.valueOf(this.versStr) + " © 2004-2009 J.Giesen - www.GeoAstro.de", 10, this.size().height - 10);
    }
    
    public moondata127() {
        this.versStr = "MoonData 1.27";
        this.demo = true;
        this.online = false;
        this.deg = '°';
        this.K = 0.017453292519943295;
        this.error = "";
        this.windowOK = false;
        this.delta = "(?)";
        this.isPhase = false;
        this.isData = false;
        this.isRiseSet = false;
        this.isMap = false;
        this.isVisibility = false;
        this.mapVis = false;
        this.isHorizon = false;
    }
}
