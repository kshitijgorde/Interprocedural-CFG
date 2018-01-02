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

public class azimuth46 extends Applet
{
    String versStr;
    final char deg = '°';
    public String wString;
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
    double GHA;
    double dec;
    double azimut;
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
    String separator;
    int timezoneoffset;
    int[] xArray;
    boolean[] hArray;
    int xMouse;
    int yMouse;
    Choice hoursChoice;
    Choice minChoice;
    Choice yearChoice;
    Choice monthChoice;
    Choice dateChoice;
    Date dat;
    Button aboutButton;
    double[] azData;
    double[] elevData;
    int nData;
    int choiceItem;
    double currentTrans;
    double currentAzim;
    double currentElev;
    double transElev;
    double[][] AzElevData;
    double[][][] aeData;
    int nAzEData;
    double UT;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 25099;
    }
    
    public void init() {
        final Color background = new Color(235, 235, 255);
        this.setBackground(background);
        this.homeString = this.getParameter("location");
        this.homeLatStr = this.getParameter("latitude");
        this.homeLongStr = this.getParameter("longitude");
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
        L13.setText("Latit.");
        gbl.setConstraints(L13, gbc);
        this.add(L13);
        final Label L14 = new Label();
        L14.setText("N / S");
        gbl.setConstraints(L14, gbc);
        this.add(L14);
        final Label L15 = new Label();
        L15.setText("Long.");
        gbl.setConstraints(L15, gbc);
        this.add(L15);
        final Label L16 = new Label();
        L16.setText("E / W");
        gbl.setConstraints(L16, gbc);
        this.add(L16);
        this.sunBox = new Checkbox("sun");
        gbc.insets = new Insets(5, 0, 0, 5);
        gbl.setConstraints(this.sunBox, gbc);
        this.sunBox.setState(true);
        this.add(this.sunBox);
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
        this.locChoice.addItem(this.separator);
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
        this.showChoice.addItem("Azimuths");
        this.showChoice.addItem("Elevations");
        this.showChoice.addItem("Azimuth");
        this.showChoice.addItem("Elevation");
        this.showChoice.addItem("Noon Elev.");
        this.showChoice.addItem("Day");
        this.showChoice.addItem("Table");
        this.showChoice.addItem("Az/Elev");
        this.showChoice.addItem("Az/Elev Jan-Jun");
        this.showChoice.addItem("Az/Elev Jul-Dec");
        this.showChoice.addItem("AZ/ELEV");
        this.showChoice.addItem("AZ/ELEV Chart");
        this.showChoice.addItem("Get Time of Az");
        this.showChoice.addItem("Get Time of Elev");
        this.showChoice.select("Azimuth");
        this.add(this.showChoice);
        gbc.gridy = 3;
        gbc.gridx = 0;
        final Label L17 = new Label();
        L17.setText("Local Time (h:m):");
        gbc.insets = new Insets(5, 20, 0, 0);
        gbl.setConstraints(L17, gbc);
        this.add(L17);
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
        final Label L18 = new Label();
        L18.setText("Date:");
        gbl.setConstraints(L18, gbc);
        this.add(L18);
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
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
        gbc.gridy = 4;
        gbc.weighty = 1.0;
        final Canvas myCan = new Canvas();
        gbl.setConstraints(myCan, gbc);
        this.add(myCan);
        gbc.gridy = 10;
        gbc.weightx = 0.0;
        this.aboutButton = new Button();
        gbc.insets = new Insets(300, 20, 0, 20);
        gbl.setConstraints(this.aboutButton, gbc);
        this.aboutButton.setLabel("About...");
        this.add(this.aboutButton);
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        this.usrStr = this.email;
        if (this.formula(this.wwwStr, 18) == this.formula("http://www.tyge.de", 18) || this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(this.wwwStr, 20) == this.formula("http://www.SciAm.com", 20)) {
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
        E *= 4.0;
        return E;
    }
    
    public void computeAzData() {
        this.nData = 0;
        this.comp = new compute();
        for (int i = -this.timezoneoffset * 4; i < -this.timezoneoffset * 4 + 96; ++i) {
            final double Jd = this.JD(this.date, this.month + 1, this.year + 1900, i * 0.25);
            final double dec = this.comp.sunDecRA(Jd, 1);
            final double ra = this.comp.sunDecRA(Jd, 2);
            final double gha = this.comp.sun_GHA(Jd, ra);
            final double hoehe = this.comp.sun_elev(Jd, this.latitude, -this.longitude, dec, ra);
            final double azim = this.comp.computeAzimut(dec, this.latitude, this.longitude, gha, hoehe);
            this.azData[i + this.timezoneoffset * 4] = azim;
            this.elevData[i + this.timezoneoffset * 4] = hoehe;
            ++this.nData;
        }
    }
    
    public void compute6AzData(final int what) {
        this.nData = 0;
        int start = 0;
        int stop = 5;
        if (what == 2) {
            start = 6;
            stop = 11;
        }
        if (what == 3) {
            start = 0;
            stop = 11;
        }
        this.comp = new compute();
        for (int m = start; m <= stop; ++m) {
            for (int i = -this.timezoneoffset * 4; i < -this.timezoneoffset * 4 + 96; ++i) {
                final double Jd = this.JD(21, m + 1, this.year + 1900, i * 0.25);
                final double dec = this.comp.sunDecRA(Jd, 1);
                final double ra = this.comp.sunDecRA(Jd, 2);
                final double gha = this.comp.sun_GHA(Jd, ra);
                final double hoehe = this.comp.sun_elev(Jd, this.latitude, -this.longitude, dec, ra);
                final double azim = this.comp.computeAzimut(dec, this.latitude, this.longitude, gha, hoehe);
                this.azData[this.nData] = azim;
                this.elevData[this.nData] = hoehe;
                ++this.nData;
            }
        }
    }
    
    public void computeChartData() {
        double dec = 0.0;
        double T = 0.0;
        final double K = 0.017453292519943295;
        this.comp = new compute();
        for (int d = 1; d < 36; ++d) {
            for (int t = 0; t <= 66; ++t) {
                dec = -90 + d * 5;
                T = -165 + t * 5;
                double hoehe = Math.cos(K * this.latitude) * Math.cos(K * dec) * Math.cos(K * T) + Math.sin(K * this.latitude) * Math.sin(K * dec);
                hoehe = Math.asin(hoehe) / K;
                double azim = (Math.sin(dec * K) - Math.sin(K * this.latitude) * Math.sin(K * hoehe)) / (Math.cos(K * hoehe) * Math.cos(K * this.latitude));
                azim = Math.acos(azim) / K;
                this.aeData[d][t][0] = azim;
                this.aeData[d][t][1] = hoehe;
            }
        }
    }
    
    public void computeAzElevData() {
        int n = 0;
        for (int m = 0; m < 12; ++m) {
            for (int d = 1; d < this.daysInMonth(m, this.year) + 1; ++d) {
                final double jd = this.JD(d, m + 1, this.year + 1900, this.UT);
                final double dec = this.comp.sunDecRA(jd, 1);
                final double ra = this.comp.sunDecRA(jd, 2);
                final double gha = this.comp.sun_GHA(jd, ra);
                final double hoehe = this.comp.sun_elev(jd, this.latitude, -this.longitude, dec, ra);
                final double azim = this.comp.computeAzimut(dec, this.latitude, this.longitude, gha, hoehe);
                this.AzElevData[n][0] = m;
                this.AzElevData[n][1] = d;
                this.AzElevData[n][2] = azim;
                this.AzElevData[n][3] = hoehe;
                ++n;
            }
        }
        this.nAzEData = n;
    }
    
    public double transElevation(final double jd) {
        final compute com = new compute();
        final double dec = com.sunDecRA(jd, 1);
        final double ra = com.sunDecRA(jd, 2);
        final double gha = com.sun_GHA(jd, ra);
        return com.sun_elev(jd, this.latitude, -this.longitude, dec, ra);
    }
    
    public void paint(final Graphics g) {
        int n = 0;
        String str = "";
        g.setFont(new Font("Courier", 0, 10));
        int nDays = 0;
        final int links = 120;
        final int y0 = 460;
        int stunde = 0;
        g.setFont(new Font("Helvetica", 0, 11));
        final double time = this.hours + this.minutes / 60.0 + this.seconds / 3600.0;
        this.UT = time - this.timezoneoffset;
        this.comp = new compute();
        double Jd = this.JD(this.date, this.month + 1, this.year + 1900, this.UT);
        final double curDec = this.comp.sunDecRA(Jd, 1);
        final double curRA = this.comp.sunDecRA(Jd, 2);
        final double curGHA = this.comp.sun_GHA(Jd, curRA);
        this.currentElev = this.comp.sun_elev(Jd, this.latitude, -this.longitude, curDec, curRA);
        this.currentAzim = this.comp.computeAzimut(curDec, this.latitude, this.longitude, curGHA, this.currentElev);
        double equation = this.eot(this.date, this.month + 1, this.year + 1900, this.UT);
        this.currentTrans = 12.0 - equation / 60.0 - this.longitude / 15.0;
        this.currentTrans += this.timezoneoffset;
        this.transElev = this.transElevation(this.JD(this.date, this.month + 1, this.year + 1900, this.currentTrans - this.timezoneoffset));
        if (this.choiceItem == 1 || this.choiceItem == 3 || this.choiceItem == 4) {
            for (int i = 1; i < 13; ++i) {
                g.drawString(-90 + i * 15 + "°", links - 35, y0 - i * 30);
            }
        }
        else {
            for (int i = 1; i < 13; ++i) {
                g.drawString(i * 30 + "°", links - 35, y0 - i * 30);
            }
        }
        int azSun = 0;
        if (this.sun) {
            if (this.choiceItem == 0 || this.choiceItem == 2) {
                if (this.xMouse != 0 && this.yMouse != 0) {
                    g.drawLine(links - 5, this.yMouse, links + 365, this.yMouse);
                    final int az = (int)Math.round((y0 - this.yMouse) * (y0 - 100) / 360.0);
                    g.drawString("=" + az + '°', 25, this.yMouse + 5);
                    this.xMouse = 0;
                    this.yMouse = 0;
                }
                azSun = (int)Math.round(this.currentAzim);
                g.drawString(new StringBuffer().append(Math.round(10.0 * this.currentAzim) / 10.0).append('°').toString(), 25, y0 - azSun);
                nDays = 0;
                for (int m = 0; m < this.month; ++m) {
                    nDays += this.daysInMonth(m, this.year);
                }
                final int xSun = nDays + this.date;
                azSun = (int)Math.round(this.currentAzim);
                g.setColor(Color.red);
                g.drawLine(links + xSun, y0, links + xSun, y0 - 360);
                g.drawOval(links + xSun - 6, y0 - azSun - 6, 12, 12);
                g.setColor(Color.yellow);
                g.fillOval(links + xSun - 5, y0 - azSun - 5, 10, 10);
                g.setColor(Color.red);
                if (this.hours >= 24) {
                    this.hours -= 24;
                }
                if (this.hours < 0) {
                    this.hours += 24;
                }
                str = String.valueOf(this.hours);
                if (this.hours < 10) {
                    str = "0" + this.hours;
                }
                else {
                    str = String.valueOf(this.hours);
                }
                if (this.minutes < 10) {
                    str = String.valueOf(str) + ":0" + this.minutes;
                }
                else {
                    str = String.valueOf(str) + ":" + this.minutes;
                }
                g.drawString(str, links + 420, y0 - azSun);
            }
            if (this.choiceItem == 1 || this.choiceItem == 3 || this.choiceItem == 4) {
                azSun = 180 + (int)Math.round(2.0 * this.currentElev);
                g.drawString(new StringBuffer().append(Math.round(10.0 * this.currentElev) / 10.0).append('°').toString(), 25, y0 - azSun);
                nDays = 0;
                for (int m = 0; m < this.month; ++m) {
                    nDays += this.daysInMonth(m, this.year);
                }
                final int xSun = nDays + this.date;
                g.setColor(Color.red);
                g.drawLine(links + xSun, y0, links + xSun, y0 - 360);
                g.drawOval(links + xSun - 6, y0 - azSun - 6, 12, 12);
                g.setColor(Color.yellow);
                g.fillOval(links + xSun - 5, y0 - azSun - 5, 10, 10);
                g.setColor(Color.red);
                if (this.hours >= 24) {
                    this.hours -= 24;
                }
                if (this.hours < 0) {
                    this.hours += 24;
                }
                str = String.valueOf(this.hours);
                if (this.hours < 10) {
                    str = "0" + this.hours;
                }
                else {
                    str = String.valueOf(this.hours);
                }
                if (this.minutes < 10) {
                    str = String.valueOf(str) + ":0" + this.minutes;
                }
                else {
                    str = String.valueOf(str) + ":" + this.minutes;
                }
                g.drawString(str, links + 420, y0 - azSun);
            }
        }
        g.setColor(Color.lightGray);
        for (int a = 0; a < 13; ++a) {
            g.drawLine(links, y0 - a * 30, links + 365, y0 - a * 30);
        }
        nDays = 0;
        for (int j = 0; j < 12; ++j) {
            nDays += this.daysInMonth(j, this.year);
            g.drawLine(links + nDays, y0, links + nDays, y0 - 360);
        }
        int maxGMT = -this.timezoneoffset + 24;
        g.setColor(Color.red);
        double trans = 0.0;
        if (this.choiceItem == 4) {
            n = 0;
            for (int k = 0; k < 12; ++k) {
                for (int d = 1; d < this.daysInMonth(k, this.year) + 1; ++d) {
                    ++n;
                    equation = this.eot(d, k + 1, this.year + 1900, 12 - this.timezoneoffset);
                    trans = 12.0 - equation / 60.0 - this.longitude / 15.0;
                    trans += this.timezoneoffset;
                    this.comp = new compute();
                    Jd = this.JD(d, k + 1, this.year + 1900, trans - this.timezoneoffset);
                    final double dec = this.comp.sunDecRA(Jd, 1);
                    final double ra = this.comp.sunDecRA(Jd, 2);
                    this.xArray[n] = 180 + (int)Math.round(2.0 * this.comp.sun_elev(Jd, this.latitude, -this.longitude, dec, ra));
                }
            }
            for (int l = 1; l < 365; ++l) {
                if (Math.abs(this.xArray[l] - this.xArray[l + 1]) < 200) {
                    g.drawLine(links + l, y0 - this.xArray[l], links + l + 1, y0 - this.xArray[l + 1]);
                }
            }
        }
        if (this.choiceItem == 2 || this.choiceItem == 3) {
            n = 0;
            this.UT = this.hours - this.timezoneoffset + this.minutes / 60.0 + this.seconds / 3600.0;
            for (int k = 0; k < 12; ++k) {
                for (int d = 1; d < this.daysInMonth(k, this.year) + 1; ++d) {
                    ++n;
                    if (this.choiceItem == 3) {
                        Jd = this.JD(d, k + 1, this.year + 1900, this.UT);
                        final double dec = this.comp.sunDecRA(Jd, 1);
                        final double ra = this.comp.sunDecRA(Jd, 2);
                        this.xArray[n] = 180 + (int)Math.round(2.0 * this.comp.sun_elev(Jd, this.latitude, -this.longitude, dec, ra));
                    }
                    else {
                        Jd = this.JD(d, k + 1, this.year + 1900, this.UT);
                        final double dec = this.comp.sunDecRA(Jd, 1);
                        final double ra = this.comp.sunDecRA(Jd, 2);
                        final double gha = this.comp.sun_GHA(Jd, ra);
                        final double hoehe = this.comp.sun_elev(Jd, this.latitude, -this.longitude, dec, ra);
                        final double azim = this.comp.computeAzimut(dec, this.latitude, this.longitude, gha, hoehe);
                        this.xArray[n] = (int)Math.round(azim);
                        if (hoehe >= 0.0) {
                            this.hArray[n] = true;
                        }
                        else {
                            this.hArray[n] = false;
                        }
                    }
                }
            }
            for (int l = 1; l < 365; ++l) {
                if (this.hArray[l]) {
                    g.setColor(Color.red);
                }
                else {
                    g.setColor(Color.blue);
                }
                if (Math.abs(this.xArray[l] - this.xArray[l + 1]) < 200) {
                    g.drawLine(links + l, y0 - this.xArray[l], links + l + 1, y0 - this.xArray[l + 1]);
                }
            }
        }
        if (this.choiceItem == 0 || this.choiceItem == 1) {
            if (this.choiceItem == 1) {
                maxGMT = -this.timezoneoffset + 24 - 11;
            }
            for (int gmt = -this.timezoneoffset; gmt < maxGMT; ++gmt) {
                stunde = gmt + this.timezoneoffset;
                if (stunde >= 24) {
                    stunde -= 24;
                }
                if (stunde < 0) {
                    stunde += 24;
                }
                n = 0;
                for (int m2 = 0; m2 < 12; ++m2) {
                    for (int d2 = 1; d2 < this.daysInMonth(m2, this.year) + 1; ++d2) {
                        ++n;
                        if (this.choiceItem == 1) {
                            Jd = this.JD(d2, m2 + 1, this.year + 1900, gmt);
                            final double dec2 = this.comp.sunDecRA(Jd, 1);
                            final double ra2 = this.comp.sunDecRA(Jd, 2);
                            final double gha2 = this.comp.sun_GHA(Jd, ra2);
                            final double hoehe2 = this.comp.sun_elev(Jd, this.latitude, -this.longitude, dec2, ra2);
                            if (hoehe2 >= 0.0) {
                                this.hArray[n] = true;
                            }
                            else {
                                this.hArray[n] = false;
                            }
                            this.xArray[n] = 180 + (int)Math.round(2.0 * this.comp.sun_elev(Jd, this.latitude, -this.longitude, dec2, ra2));
                        }
                        else {
                            Jd = this.JD(d2, m2 + 1, this.year + 1900, stunde - this.timezoneoffset);
                            final double dec2 = this.comp.sunDecRA(Jd, 1);
                            final double ra2 = this.comp.sunDecRA(Jd, 2);
                            final double gha2 = this.comp.sun_GHA(Jd, ra2);
                            final double hoehe2 = this.comp.sun_elev(Jd, this.latitude, -this.longitude, dec2, ra2);
                            final double azim2 = this.comp.computeAzimut(dec2, this.latitude, this.longitude, gha2, hoehe2);
                            this.xArray[n] = (int)Math.round(azim2);
                            if (hoehe2 >= 0.0) {
                                this.hArray[n] = true;
                            }
                            else {
                                this.hArray[n] = false;
                            }
                        }
                    }
                }
                g.setFont(new Font("Courier", 0, 10));
                for (int i2 = 1; i2 < 365; ++i2) {
                    if (this.hArray[i2]) {
                        g.setColor(Color.red);
                    }
                    else {
                        g.setColor(Color.blue);
                    }
                    if (Math.abs(this.xArray[i2] - this.xArray[i2 + 1]) < 200) {
                        g.drawLine(links + i2, y0 - this.xArray[i2], links + i2 + 1, y0 - this.xArray[i2 + 1]);
                    }
                }
                if (stunde % 2 == 0) {
                    g.setColor(Color.blue);
                }
                else {
                    g.setColor(Color.black);
                }
                if (this.xArray[365] > 0) {
                    g.drawString(stunde + ":00", links + 375, y0 - this.xArray[365] + 3);
                }
            }
        }
        g.setFont(new Font("Courier", 0, 10));
        g.setColor(Color.red);
        if (this.choiceItem == 1 || this.choiceItem == 3) {
            g.drawString("Elevations", 10, y0 - 365);
        }
        if (this.choiceItem == 4) {
            g.drawString("Noon Elevation", 10, y0 - 345);
        }
        if (this.choiceItem == 0 || this.choiceItem == 2) {
            g.drawString("Azimuth", 20, y0 - 365);
        }
        g.setColor(Color.black);
        g.drawRect(links, y0 - 360, n, 360);
        for (int i3 = 0; i3 < 12; ++i3) {
            g.drawString(this.monthArray[i3], links + 5 + i3 * 31, y0 + 12);
        }
        g.setColor(Color.black);
        g.drawString(String.valueOf(this.versStr) + "  © 2000-2011 J.Giesen - www.GeoAstro.de", links - 50, this.size().height - 5);
        g.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
        if (this.demo) {
            g.setFont(new Font("Chicago", 0, 124));
            g.setColor(Color.red);
            g.drawString("D E M O", 50, 300);
        }
    }
    
    public boolean mouseDown(final Event event, final int x, final int y) {
        final int xL = 120;
        final int xR = 466;
        final int y2 = 460;
        final int y3 = 100;
        this.xMouse = 0;
        this.yMouse = 0;
        if (x > 120 && x < 466 && y > 100 && y < 460) {
            this.xMouse = x;
            this.yMouse = y;
        }
        this.repaint();
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
            if (event.target == this.showChoice) {
                if (this.showChoice.getSelectedItem().equals("Azimuths")) {
                    this.choiceItem = 0;
                    this.repaint();
                }
                if (this.showChoice.getSelectedItem().equals("Elevations")) {
                    this.choiceItem = 1;
                    this.repaint();
                }
                if (this.showChoice.getSelectedItem().equals("Azimuth")) {
                    this.choiceItem = 2;
                    this.repaint();
                }
                if (this.showChoice.getSelectedItem().equals("Elevation")) {
                    this.choiceItem = 3;
                    this.repaint();
                }
                if (this.showChoice.getSelectedItem().equals("Noon Elev.")) {
                    this.choiceItem = 4;
                    this.repaint();
                }
                if (this.showChoice.getSelectedItem().equals("Day")) {
                    this.computeAzData();
                    String titleStr = String.valueOf(this.latStr) + '°' + this.nsStr + ", " + this.longStr + '°' + this.ewStr;
                    titleStr = String.valueOf(titleStr) + ", " + (this.year + 1900) + " " + this.monthArray[this.month] + " " + this.date;
                    if (this.sun) {
                        titleStr = String.valueOf(titleStr) + ", " + this.comp.makeTimeString(this.UT + this.timezoneoffset);
                    }
                    final Frame azf = new azFrame(titleStr, this.azData, this.elevData, this.nData, this.year, this.currentTrans, this.transElev, this.sun, this.currentAzim, this.currentElev, this.UT, this.timezoneoffset, this.demo);
                    azf.resize(600, 465);
                    azf.show();
                    this.showChoice.select(this.choiceItem);
                }
                if (this.showChoice.getSelectedItem().equals("Table")) {
                    this.computeAzElevData();
                    String titleStr = String.valueOf(this.latStr) + '°' + this.nsStr + ", " + this.longStr + '°' + this.ewStr;
                    titleStr = String.valueOf(titleStr) + ", " + (this.year + 1900) + ", " + this.comp.makeTimeString(this.UT + this.timezoneoffset);
                    boolean dem = false;
                    if (this.online || this.demo) {
                        dem = true;
                    }
                    final Frame df = new dataFrame(titleStr, this.versStr, this.AzElevData, this.nAzEData, dem);
                    df.resize(300, 435);
                    df.show();
                    this.showChoice.select(this.choiceItem);
                }
                if (this.showChoice.getSelectedItem().equals("Az/Elev")) {
                    this.computeAzData();
                    String titleStr = String.valueOf(this.latStr) + '°' + this.nsStr + ", " + this.longStr + '°' + this.ewStr;
                    titleStr = String.valueOf(titleStr) + ", " + (this.year + 1900) + " " + this.monthArray[this.month] + " " + this.date;
                    if (this.sun) {
                        titleStr = String.valueOf(titleStr) + ", " + this.comp.makeTimeString(this.UT + this.timezoneoffset);
                    }
                    boolean dem = false;
                    if (this.online || this.demo) {
                        dem = true;
                    }
                    final Frame aef = new aeFrame(false, titleStr, this.year + 1900, this.latitude, this.longitude, this.azData, this.elevData, this.nData, this.year, this.currentTrans, this.transElev, this.sun, this.currentAzim, this.currentElev, this.UT, this.timezoneoffset, this.demo);
                    aef.resize(500, 435);
                    aef.show();
                    this.showChoice.select(this.choiceItem);
                }
                if (this.showChoice.getSelectedItem().equals("Az/Elev Jan-Jun")) {
                    this.compute6AzData(1);
                    String titleStr = String.valueOf(this.latStr) + '°' + this.nsStr + ", " + this.longStr + '°' + this.ewStr;
                    titleStr = String.valueOf(titleStr) + ", " + (this.year + 1900) + " Jan 21 - Jun 21";
                    if (this.sun) {
                        titleStr = String.valueOf(titleStr) + ", " + this.comp.makeTimeString(this.UT + this.timezoneoffset);
                    }
                    boolean dem = false;
                    if (this.online || this.demo) {
                        dem = true;
                    }
                    final Frame aef = new aeFrame(false, titleStr, this.year + 1900, this.latitude, this.longitude, this.azData, this.elevData, this.nData, this.year, this.currentTrans, this.transElev, this.sun, this.currentAzim, this.currentElev, this.UT, this.timezoneoffset, this.demo);
                    aef.resize(500, 435);
                    aef.show();
                    this.showChoice.select(this.choiceItem);
                }
                if (this.showChoice.getSelectedItem().equals("Az/Elev Jul-Dec")) {
                    this.compute6AzData(2);
                    String titleStr = String.valueOf(this.latStr) + '°' + this.nsStr + ", " + this.longStr + '°' + this.ewStr;
                    titleStr = String.valueOf(titleStr) + ", " + (this.year + 1900) + " Jul 21 - Dec 21";
                    if (this.sun) {
                        titleStr = String.valueOf(titleStr) + ", " + this.comp.makeTimeString(this.UT + this.timezoneoffset);
                    }
                    boolean dem = false;
                    if (this.online || this.demo) {
                        dem = true;
                    }
                    final Frame aef = new aeFrame(false, titleStr, this.year + 1900, this.latitude, this.longitude, this.azData, this.elevData, this.nData, this.year, this.currentTrans, this.transElev, this.sun, this.currentAzim, this.currentElev, this.UT, this.timezoneoffset, this.demo);
                    aef.resize(500, 435);
                    aef.show();
                    this.showChoice.select(this.choiceItem);
                }
                if (this.showChoice.getSelectedItem().equals("AZ/ELEV")) {
                    String titleStr = String.valueOf(this.latStr) + '°' + this.nsStr + ", " + this.longStr + '°' + this.ewStr;
                    titleStr = String.valueOf(titleStr) + ", " + (this.year + 1900) + " Jan 21 - Dec 21";
                    boolean dem = false;
                    if (this.online || this.demo) {
                        dem = true;
                    }
                    final Frame aef = new aeFrame(true, titleStr, this.year + 1900, this.latitude, this.longitude, null, null, this.nData, this.year, this.currentTrans, this.transElev, this.sun, this.currentAzim, this.currentElev, this.UT, this.timezoneoffset, this.demo);
                    aef.resize(800, 550);
                    aef.show();
                    this.showChoice.select(this.choiceItem);
                }
                if (this.showChoice.getSelectedItem().equals("AZ/ELEV Chart")) {
                    this.computeChartData();
                    final String titleStr = String.valueOf(this.latStr) + '°' + this.nsStr + ", " + this.longStr + '°' + this.ewStr;
                    boolean dem = false;
                    if (this.online || this.demo) {
                        dem = true;
                    }
                    final Frame df = new dFrame(titleStr, this.aeData, this.demo);
                    df.resize(800, 550);
                    df.show();
                    this.showChoice.select(this.choiceItem);
                }
                if (this.showChoice.getSelectedItem().equals("Get Time of Az")) {
                    final String titleStr = String.valueOf(this.latStr) + '°' + this.nsStr + ", " + this.longStr + '°' + this.ewStr + ", " + (this.year + 1900) + " " + this.monthArray[this.month] + " " + this.date;
                    boolean dem = false;
                    if (this.online || this.demo) {
                        dem = true;
                    }
                    final Frame gtFrame = new gtFrame(titleStr, this.JD(this.date, this.month + 1, this.year + 1900, 0.0), this.latitude, this.longitude, this.timezoneoffset, this.demo);
                    gtFrame.resize(440, 120);
                    gtFrame.show();
                    this.showChoice.select(this.choiceItem);
                }
                if (this.showChoice.getSelectedItem().equals("Get Time of Elev")) {
                    final String titleStr = String.valueOf(this.latStr) + '°' + this.nsStr + ", " + this.longStr + '°' + this.ewStr + ", " + (this.year + 1900) + " " + this.monthArray[this.month] + " " + this.date;
                    boolean dem = false;
                    if (this.online || this.demo) {
                        dem = true;
                    }
                    final Frame elevFrame = new elevFrame(titleStr, this.JD(this.date, this.month + 1, this.year + 1900, 0.0), this.latitude, this.longitude, this.timezoneoffset, this.demo);
                    elevFrame.resize(440, 120);
                    elevFrame.show();
                    this.showChoice.select(this.choiceItem);
                }
            }
            if (event.target == this.locChoice) {
                if (this.locChoice.getSelectedItem().equals(this.separator)) {
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
    
    public azimuth46() {
        this.versStr = "Azimuth & Elevation Diagram v. 4.6 ";
        this.wString = "?";
        this.demo = true;
        this.online = false;
        this.sun = true;
        this.usrLat = 0.0;
        this.usrLong = 0.0;
        this.monthArray = new String[12];
        this.usrStr = "demo";
        this.separator = "---------------";
        this.xArray = new int[370];
        this.hArray = new boolean[370];
        this.xMouse = 0;
        this.yMouse = 0;
        this.azData = new double[1200];
        this.elevData = new double[1200];
        this.choiceItem = 0;
        this.AzElevData = new double[370][4];
        this.aeData = new double[40][71][2];
    }
}
