import java.awt.Event;
import java.awt.Cursor;
import java.awt.Color;
import java.util.Map;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.Dialog;
import java.net.URL;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Font;
import java.awt.TextField;
import java.awt.Choice;
import java.awt.Button;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SolsticeDaylight25 extends Applet
{
    final char deg = '°';
    Date dat;
    int year;
    int month;
    int date;
    int hours;
    int minutes;
    public double latitude;
    public double longitude;
    int browserOffset;
    public int locOffset;
    String versStr;
    public suncanvas myCan;
    public sunloc loc;
    public sunloc time;
    public Aboutdialog about;
    Button button;
    Button aboutButton;
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
    Choice yearChoice;
    Choice dateChoice;
    Choice monthChoice;
    String dateStr;
    String monthStr;
    String yearStr;
    String ewStr;
    String nsStr;
    String latStr;
    String longStr;
    String[] monthArray;
    final int xL = 60;
    final int xOben = 60;
    public compute comp;
    String str;
    boolean online;
    String homeString;
    String homeLatStr;
    String homeLongStr;
    double homeLat;
    double homeLong;
    boolean demo;
    String userString;
    double usrLat;
    double usrLong;
    Choice viewChoice;
    int start;
    double zoom;
    boolean AM;
    String[][] STR;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 16355;
    }
    
    public void init() {
        this.setFont(new Font("Helvetica", 0, 12));
        this.userString = this.getParameter("email");
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
        if (this.month > 10 || this.month < 3) {
            this.start = 10;
        }
        else {
            this.start = 4;
        }
        this.browserOffset = this.dat.getTimezoneOffset();
        this.browserOffset = -this.browserOffset / 60;
        this.locOffset = this.browserOffset;
        this.timeString = "UT " + this.browserOffset + " h";
        if (this.browserOffset > 0) {
            this.timeString = "UT +" + this.browserOffset + " h";
        }
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        this.setLayout(gbl);
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(2, 0, 0, 0);
        Label L = new Label();
        L.setText("Time Zone");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label();
        L.setText("Location");
        gbl.setConstraints(L, gbc);
        this.add(L);
        gbc.weightx = 10.0;
        L = new Label();
        L.setText("Lat.: xx.xx");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label();
        L.setText("N / S");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label();
        L.setText("Long.: xx.xx");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label();
        L.setText("E / W");
        gbl.setConstraints(L, gbc);
        this.add(L);
        L = new Label();
        L.setText("Draw / Write");
        gbl.setConstraints(L, gbc);
        this.add(L);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 0, 0);
        this.time = new sunloc();
        this.timeChoice = new Choice();
        this.time.timeMenu(this.timeChoice);
        gbl.setConstraints(this.timeChoice, gbc);
        this.timeChoice.select(this.timeString);
        this.add(this.timeChoice);
        gbc.insets = new Insets(0, 0, 0, 0);
        this.loc = new sunloc();
        this.locChoice = new Choice();
        this.locString = this.homeString;
        this.locChoice.addItem(this.locString);
        this.locChoice.addItem("_________");
        this.locChoice.select(this.locString);
        this.loc.locMenu(this.locChoice);
        gbl.setConstraints(this.locChoice, gbc);
        this.add(this.locChoice);
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
        gbl.setConstraints(this.fieldLatDeg = new TextField(this.latStr, 4), gbc);
        this.add(this.fieldLatDeg);
        (this.nsChoice = new Choice()).addItem("N");
        this.nsChoice.addItem("S");
        this.nsChoice.select(this.nsStr);
        gbl.setConstraints(this.nsChoice, gbc);
        this.add(this.nsChoice);
        this.longStr = String.valueOf(Math.abs(this.longitude));
        gbl.setConstraints(this.fieldLongDeg = new TextField(this.longStr, 4), gbc);
        this.add(this.fieldLongDeg);
        (this.ewChoice = new Choice()).addItem("E");
        this.ewChoice.addItem("W");
        this.ewChoice.select(this.ewStr);
        gbl.setConstraints(this.ewChoice, gbc);
        this.add(this.ewChoice);
        gbc.insets = new Insets(0, 0, 0, 10);
        gbl.setConstraints(this.viewChoice = new Choice(), gbc);
        this.viewChoice.addItem("Summer Solstice");
        this.viewChoice.addItem("Winter Solstice");
        this.viewChoice.addItem("Feb - Apr");
        this.viewChoice.addItem("Aug - Oct");
        this.viewChoice.addItem("Zoom in");
        this.viewChoice.addItem("Zoom out");
        this.viewChoice.addItem("Seasons");
        this.viewChoice.addItem("Rise Year");
        this.viewChoice.addItem("Set Year");
        if (this.start == 4) {
            this.viewChoice.select(0);
        }
        else {
            this.viewChoice.select(1);
        }
        this.add(this.viewChoice);
        gbc.gridy = 2;
        L = new Label();
        L.setText("Date :");
        gbl.setConstraints(L, gbc);
        this.add(L);
        this.monthArray = new String[] { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
        gbc.insets = new Insets(5, 0, 0, 10);
        this.yearChoice = new Choice();
        for (int i = 0; i < 40; ++i) {
            this.yearStr = String.valueOf(this.year + 1900 - 20 + i);
            this.yearChoice.addItem(this.yearStr);
        }
        this.yearStr = String.valueOf(this.year + 1900);
        this.yearChoice.select(this.yearStr);
        gbl.setConstraints(this.yearChoice, gbc);
        this.add(this.yearChoice);
        this.monthChoice = new Choice();
        for (int j = 0; j < 12; ++j) {
            this.monthChoice.addItem(this.monthArray[j]);
        }
        this.monthChoice.select(this.month);
        gbl.setConstraints(this.monthChoice, gbc);
        this.add(this.monthChoice);
        this.dateChoice = new Choice();
        for (int k = 1; k < 32; ++k) {
            this.dateStr = String.valueOf(k);
            this.dateChoice.addItem(this.dateStr);
        }
        this.dateChoice.select(this.date - 1);
        gbl.setConstraints(this.dateChoice, gbc);
        this.add(this.dateChoice);
        (this.button = new Button()).setLabel("Today");
        gbc.insets = new Insets(5, 0, 0, 10);
        gbl.setConstraints(this.button, gbc);
        this.add(this.button);
        gbl.setConstraints(this.aboutButton = new Button(), gbc);
        this.aboutButton.setLabel("About...");
        this.add(this.aboutButton);
        gbc.gridy = 3;
        gbc.weighty = 160.0;
        gbl.setConstraints(this.myCan = new suncanvas(this.zoom, this.start, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online), gbc);
        this.add(this.myCan);
        this.resize(this.size().width, this.size().height);
        this.repaint();
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        final int xy = this.formula(this.wwwStr, 27);
        if (this.formula(this.wwwStr, 18) == this.formula("http://www.tyge.de", 18) || this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(this.wwwStr, 22) == this.formula("http://www.geoastro.de", 22) || this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 27) == this.formula("http://www.jgiesen.business", 27)) {
            ok = true;
            this.online = true;
            this.demo = false;
        }
        else {
            ok = false;
        }
        if (!ok) {
            ok = true;
            if (this.email.length() == 0 || (Integer.parseInt(this.param) != this.formula(this.email, this.email.length()) && Integer.parseInt(this.param) != this.formula(this.email, this.email.length()) - 10)) {
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
                final Dialog Aboutdialog = new Aboutdialog(this, this.versStr, this.demo);
                Aboutdialog.resize(250, 230);
                Aboutdialog.show();
            }
        }
        System.out.println("Solstice Daylight Applet " + this.versStr + " (c) 2011 J. Giesen - www.GeoAstro.de");
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
    
    public void findElev(final double AZ) {
        final double[][][] az = new double[32][12][125];
        double az2 = 0.0;
        double az3 = 0.0;
        final int[][] T = new int[32][12];
        final double[][] t = new double[32][12];
        final double[][] eot = new double[32][12];
        for (int d = 1; d <= 31; ++d) {
            for (int m = 0; m < 12; ++m) {
                eot[d][m] = this.comp.EOT(this.comp.JD(d, m + 1, this.year + 1900, 12 - this.locOffset));
                for (int i = 0; i < 24; ++i) {
                    final double jd = this.comp.JD(d, m + 1, this.year + 1900, i);
                    final double dec = this.comp.sunDecRA(1, jd);
                    final double ra = this.comp.sunDecRA(2, jd);
                    az[d][m][i] = this.comp.sun_elev(jd, this.latitude, -this.longitude, dec, ra);
                }
            }
        }
        for (int d2 = 1; d2 <= 31; ++d2) {
            for (int j = 0; j < 12; ++j) {
                for (int k = 0; k < 23; ++k) {
                    az2 = az[d2][j][k];
                    az3 = az[d2][j][k + 1];
                    if (this.AM) {
                        if (az2 < AZ && az3 > AZ) {
                            T[d2][j] = k;
                            break;
                        }
                    }
                    else if (az2 > AZ && az3 < AZ) {
                        T[d2][j] = k;
                        break;
                    }
                }
            }
        }
        for (int d3 = 1; d3 <= 31; ++d3) {
            for (int l = 0; l < 12; ++l) {
                for (int i2 = 0; i2 < 122; ++i2) {
                    final double jd = this.comp.JD(d3, l + 1, this.year + 1900, T[d3][l] + i2 / 60.0);
                    final double dec = this.comp.sunDecRA(1, jd);
                    final double ra = this.comp.sunDecRA(2, jd);
                    az[d3][l][i2] = this.comp.sun_elev(jd, this.latitude, -this.longitude, dec, ra);
                }
            }
        }
        for (int d4 = 1; d4 <= 31; ++d4) {
            for (int m2 = 0; m2 < 12; ++m2) {
                for (int i3 = 0; i3 < 60; ++i3) {
                    az2 = az[d4][m2][i3];
                    az3 = az[d4][m2][i3 + 1];
                    final double delta = (AZ - az2) / (az3 - az2);
                    if (this.AM) {
                        if (az2 < AZ && az3 > AZ) {
                            t[d4][m2] = i3 + delta;
                            break;
                        }
                    }
                    else if (az2 > AZ && az3 < AZ) {
                        t[d4][m2] = i3 + delta;
                        break;
                    }
                }
            }
        }
        double UT = 0.0;
        for (int d5 = 1; d5 <= 31; ++d5) {
            for (int m3 = 0; m3 < 12; ++m3) {
                UT = T[d5][m3] + t[d5][m3] / 60.0;
                if (UT > 0.0) {
                    UT += this.locOffset;
                    this.STR[d5][m3] = " " + this.comp.makeTimeString("", UT);
                }
                else {
                    this.STR[d5][m3] = "      ";
                }
                UT = 0.0;
            }
        }
    }
    
    public void doElev(final double AZ) {
        final String[] dataStr = new String[380];
        int n = 1;
        this.findElev(-0.833);
        for (int d = 1; d <= 31; ++d) {
            String str = " " + d;
            if (d < 10) {
                str = " 0" + d;
            }
            dataStr[n] = String.valueOf(str) + " ";
            for (int m = 0; m < 12; ++m) {
                str = this.STR[d][m];
                if (d <= this.comp.daysInMonth(m, this.year)) {
                    final String[] array = dataStr;
                    final int n2 = n;
                    array[n2] = String.valueOf(array[n2]) + " " + str;
                }
                else {
                    final String[] array2 = dataStr;
                    final int n3 = n;
                    array2[n3] = String.valueOf(array2[n3]) + "     -  ";
                }
            }
            final String[] array3 = dataStr;
            final int n4 = n;
            array3[n4] = String.valueOf(array3[n4]) + "\n";
            if (d % 5 == 0 && d < 30) {
                final String[] array4 = dataStr;
                final int n5 = n;
                array4[n5] = String.valueOf(array4[n5]) + "----------------------------------------------------------------------------------------------------\n";
            }
            ++n;
        }
        String latStr = new StringBuffer().append(Math.abs(this.latitude)).append('°').toString();
        if (this.latitude >= 0.0) {
            latStr = String.valueOf(latStr) + " N";
        }
        else {
            latStr = String.valueOf(latStr) + " S";
        }
        String longStr = new StringBuffer().append(Math.abs(this.longitude)).append('°').toString();
        if (this.longitude >= 0.0) {
            longStr = String.valueOf(longStr) + " E";
        }
        else {
            longStr = String.valueOf(longStr) + " W";
        }
        String utStr = ",  UT " + this.locOffset + " h";
        if (this.locOffset >= 0) {
            utStr = ",  UT +" + this.locOffset + " h";
        }
        String str = ",  Standard Time";
        dataStr[0] = "\n  Latitude " + latStr + "  Logitude " + longStr + utStr + ",  Elevation -0.833" + '°' + str + "\n" + "\n";
        final String[] array5 = dataStr;
        final int n6 = 0;
        array5[n6] = String.valueOf(array5[n6]) + "        ";
        for (int i = 0; i < 12; ++i) {
            final String[] array6 = dataStr;
            final int n7 = 0;
            array6[n7] = String.valueOf(array6[n7]) + this.monthArray[i] + "     ";
        }
        final String[] array7 = dataStr;
        final int n8 = 0;
        array7[n8] = String.valueOf(array7[n8]) + "\n";
        String title = "";
        if (this.AM) {
            title = String.valueOf(title) + "Sunrise ";
        }
        else {
            title = String.valueOf(title) + "Sunset ";
        }
        if (this.demo) {
            this.online = true;
        }
        final scrollFrame sf = new scrollFrame(String.valueOf(title) + (this.year + 1900), 31, dataStr, this.online);
        sf.resize(660, 550);
        sf.show();
    }
    
    public void paint(final Graphics g) {
        final RenderingHints renderHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        renderHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        final Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHints(renderHints);
        final int unten = 390;
        g.setColor(Color.white);
        g.fillRect(0, 0, this.size().width, this.size().height);
        g.setColor(Color.black);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        g.setFont(new Font("Helvetica", 0, 10));
        g.setColor(Color.red);
        this.comp = new compute();
        if (this.demo) {
            this.userString = "demo running";
        }
        else {
            this.userString = this.email;
        }
        if (this.online) {
            this.userString = "running online";
        }
        final int left = 50;
        final int yMitte = 370;
        if (this.start == 4 || this.start == 10) {
            final double jd1 = this.comp.JD(9, this.start + 1, this.year + 1900, 12.0);
            for (int i = 1; i <= 83; ++i) {
                final double jd2 = jd1 + i;
                final int x = left + Math.round((i - 1) * 9);
                g.setColor(Color.lightGray);
                if (this.comp.caldat(1, jd2) == 1) {
                    g.setColor(Color.red);
                    g.drawString("1", x - 2, yMitte + 265);
                }
                if (this.comp.caldat(1, jd2) % 5 == 0) {
                    g.setColor(Color.gray);
                    g.drawString(String.valueOf(this.comp.caldat(1, jd2)), x - 5, yMitte + 265);
                }
                if (this.comp.caldat(1, jd2) % 10 == 0) {
                    g.setColor(Color.darkGray);
                    g.drawString(String.valueOf(this.comp.caldat(1, jd2)), x - 5, yMitte + 265);
                }
                g.drawLine(x, yMitte - 250, x, yMitte + 250);
            }
            g.setColor(Color.black);
            g.drawString(this.monthArray[this.start], left + 75, yMitte + 245);
            g.drawString(this.monthArray[this.start + 1], left + 350, yMitte + 245);
            g.drawString(this.monthArray[(this.start + 2) % 12], left + 630, yMitte + 245);
            g.setColor(Color.red);
            g.drawRect(left, yMitte - 250, 738, 500);
        }
        if (this.start == 2) {
            final int N = this.daysInMonth(1, this.year) + this.daysInMonth(2, this.year) + this.daysInMonth(3, this.year);
            final double jd1 = this.comp.JD(1, 2, this.year + 1900, 12.0);
            for (int j = 0; j <= N; ++j) {
                final double jd2 = jd1 + j;
                final int x = left + Math.round(j * 9);
                g.setColor(Color.lightGray);
                if (this.comp.caldat(1, jd2) == 1) {
                    g.setColor(Color.red);
                    g.drawString("1", x - 2, yMitte + 265);
                }
                if (this.comp.caldat(1, jd2) % 5 == 0) {
                    g.setColor(Color.gray);
                    g.drawString(String.valueOf(this.comp.caldat(1, jd2)), x - 5, yMitte + 265);
                }
                if (this.comp.caldat(1, jd2) % 10 == 0) {
                    g.setColor(Color.darkGray);
                    g.drawString(String.valueOf(this.comp.caldat(1, jd2)), x - 5, yMitte + 265);
                }
                g.drawLine(x, yMitte - 250, x, yMitte + 250);
            }
            g.setColor(Color.black);
            g.drawString(this.monthArray[1], left + 120, yMitte + 245);
            g.drawString(this.monthArray[2], left + 375, yMitte + 245);
            g.drawString(this.monthArray[3], left + 650, yMitte + 245);
            g.setColor(Color.red);
            g.drawRect(left, yMitte - 250, N * 9, 500);
        }
        if (this.start == 8) {
            final int N = 1 + this.daysInMonth(8, this.year) + this.daysInMonth(9, this.year) + this.daysInMonth(10, this.year);
            final double jd1 = this.comp.JD(1, 8, this.year + 1900, 12.0);
            for (int j = 0; j <= N; ++j) {
                final double jd2 = jd1 + j;
                final int x = left + Math.round(j * 9);
                g.setColor(Color.lightGray);
                if (this.comp.caldat(1, jd2) == 1) {
                    g.setColor(Color.red);
                    g.drawString("1", x - 2, yMitte + 265);
                }
                if (this.comp.caldat(1, jd2) % 5 == 0) {
                    g.setColor(Color.gray);
                    g.drawString(String.valueOf(this.comp.caldat(1, jd2)), x - 5, yMitte + 265);
                }
                if (this.comp.caldat(1, jd2) % 10 == 0) {
                    g.setColor(Color.darkGray);
                    g.drawString(String.valueOf(this.comp.caldat(1, jd2)), x - 5, yMitte + 265);
                }
                g.drawLine(x, yMitte - 250, x, yMitte + 250);
            }
            g.setColor(Color.black);
            g.drawString(this.monthArray[7], left + 120, yMitte + 245);
            g.drawString(this.monthArray[8], left + 395, yMitte + 245);
            g.drawString(this.monthArray[9], left + 665, yMitte + 245);
            g.setColor(Color.red);
            g.drawRect(left, yMitte - 250, N * 9, 500);
        }
        this.myCan.update(g);
        if (this.demo) {
            g.setColor(Color.red);
            g.setFont(new Font("Chicago", 0, 96));
            g.drawString("D E M O", 150, 280);
        }
    }
    
    public void startCursor() {
        this.showStatus("Calculating...");
        this.setCursor(new Cursor(3));
    }
    
    public void startCursor1() {
        this.showStatus("Calculating for 1 or 2 minutes. Please be patient ...");
        this.setCursor(new Cursor(3));
    }
    
    public void stopCursor() {
        this.showStatus("");
        this.setCursor(new Cursor(0));
    }
    
    public boolean keyDown(final Event event, final int code) {
        if (code != 104 && code != 100 && code != 109 && code != 72 && code != 68 && code != 77 && code != 121 && code != 89) {
            return false;
        }
        if (code == 121) {
            ++this.year;
            this.dat.setYear(this.year);
            this.yearChoice.select(String.valueOf(this.year + 1900));
            final int n = this.daysInMonth(this.month, this.year);
            if (this.date > n) {
                this.date = n;
            }
            this.dat.setDate(this.date);
            this.dateChoice.select(this.date - 1);
            this.myCan = new suncanvas(this.zoom, this.start, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
            this.repaint();
            return true;
        }
        if (code == 89) {
            --this.year;
            this.dat.setYear(this.year);
            this.yearChoice.select(String.valueOf(this.year + 1900));
            final int n = this.daysInMonth(this.month, this.year);
            if (this.date > n) {
                this.date = n;
            }
            this.dat.setDate(this.date);
            this.dateChoice.select(this.date - 1);
            this.myCan = new suncanvas(this.zoom, this.start, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
            this.repaint();
            return true;
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
                final int n = this.daysInMonth(this.month, this.year);
                if (this.date > n) {
                    this.date = 1;
                    ++this.month;
                    this.dat.setMonth(this.month);
                    this.monthChoice.select(this.month);
                }
                this.dateChoice.select(this.date - 1);
            }
            this.dat.setHours(this.hours);
            this.dat.setDate(this.date);
            this.date = this.dat.getDate();
            this.dateChoice.select(this.date - 1);
            this.month = this.dat.getMonth();
            this.monthChoice.select(this.month);
            this.myCan = new suncanvas(this.zoom, this.start, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
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
                final int n = this.daysInMonth(this.month, this.year);
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
            this.dat.setDate(this.date);
            this.yearStr = String.valueOf(this.year + 1900);
            this.yearChoice.select(this.yearStr);
            this.myCan = new suncanvas(this.zoom, this.start, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
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
            final int n = this.daysInMonth(this.month, this.year);
            if (this.date > n) {
                this.date = 1;
                this.dat.setDate(this.date);
                this.dat.setMonth(this.month - 1);
            }
            this.month = this.dat.getMonth();
            this.monthChoice.select(this.month);
            this.dateChoice.select(this.date - 1);
            this.year = this.dat.getYear();
            this.year += 1900;
            this.yearStr = String.valueOf(this.year);
            this.yearChoice.select(this.yearStr);
            this.myCan = new suncanvas(this.zoom, this.start, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
            this.repaint();
            return true;
        }
        return false;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        int n = 0;
        if (event.target instanceof Button) {
            if (event.target == this.aboutButton) {
                final Dialog Aboutdialog = new Aboutdialog(this, this.versStr, this.demo);
                Aboutdialog.resize(250, 230);
                Aboutdialog.show();
                this.repaint();
                return true;
            }
            if (event.target == this.button) {
                this.dat = new Date();
                this.hours = this.dat.getHours();
                this.minutes = this.dat.getMinutes();
                this.date = this.dat.getDate();
                this.dateChoice.select(this.date - 1);
                this.month = this.dat.getMonth();
                this.monthChoice.select(this.month);
                this.year = this.dat.getYear();
                this.yearStr = String.valueOf(this.year + 1900);
                this.yearChoice.select(this.yearStr);
                this.browserOffset = -this.dat.getTimezoneOffset() / 60;
                if (this.month >= 10) {
                    this.start = 10;
                    this.viewChoice.select(1);
                }
                else if (this.month >= 4 && this.month <= 6) {
                    this.start = 4;
                    this.viewChoice.select(0);
                }
                else if (this.month >= 1 && this.month <= 3) {
                    this.start = 2;
                    this.viewChoice.select(2);
                }
                else if (this.month >= 7 && this.month <= 9) {
                    this.start = 8;
                    this.viewChoice.select(3);
                }
                this.myCan = new suncanvas(this.zoom, this.start, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                this.repaint();
                return true;
            }
        }
        if (event.target instanceof Choice && event.target == this.viewChoice) {
            if (this.viewChoice.getSelectedItem().equals("Seasons")) {
                final computeSeasons mySeasons = new computeSeasons(this.year + 1900);
                final String[] dataStr = { "\nSpring:  " + mySeasons.spring() + "\n", "Summer:  " + mySeasons.summer() + "\n", "Autumn:  " + mySeasons.autumn() + "\n", "Winter:  " + mySeasons.winter() + "\n" };
                final seasonsFrame seasonsf = new seasonsFrame("Seasons " + (this.year + 1900), 3, dataStr);
                seasonsf.schreiben(dataStr, 3, this.online);
                seasonsf.resize(220, 130);
                seasonsf.show();
                if (this.start == 4) {
                    this.viewChoice.select(0);
                }
                if (this.start == 10) {
                    this.viewChoice.select(1);
                }
                if (this.start == 2) {
                    this.viewChoice.select(2);
                }
                if (this.start == 8) {
                    this.viewChoice.select(3);
                }
                return true;
            }
            if (this.viewChoice.getSelectedItem().equals("Summer Solstice")) {
                this.start = 4;
                this.myCan = new suncanvas(this.zoom, this.start, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                this.repaint();
                return true;
            }
            if (this.viewChoice.getSelectedItem().equals("Winter Solstice")) {
                this.start = 10;
                this.myCan = new suncanvas(this.zoom, this.start, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                this.repaint();
                return true;
            }
            if (this.viewChoice.getSelectedItem().equals("Feb - Apr")) {
                this.start = 2;
                this.myCan = new suncanvas(this.zoom, this.start, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                this.repaint();
                return true;
            }
            if (this.viewChoice.getSelectedItem().equals("Aug - Oct")) {
                this.start = 8;
                this.myCan = new suncanvas(this.zoom, this.start, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                this.repaint();
                return true;
            }
            if (this.viewChoice.getSelectedItem().equals("Zoom in")) {
                this.zoom *= 1.1;
                this.myCan = new suncanvas(this.zoom, this.start, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                this.repaint();
            }
            if (this.viewChoice.getSelectedItem().equals("Zoom out")) {
                this.zoom /= 1.1;
                this.myCan = new suncanvas(this.zoom, this.start, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                this.repaint();
            }
            if (this.viewChoice.getSelectedItem().equals("Rise Year")) {
                this.AM = true;
                this.doElev(-0.833);
            }
            if (this.viewChoice.getSelectedItem().equals("Set Year")) {
                this.AM = false;
                this.doElev(-0.833);
            }
            if (this.start == 4) {
                this.viewChoice.select(0);
            }
            if (this.start == 10) {
                this.viewChoice.select(1);
            }
            if (this.start == 2) {
                this.viewChoice.select(2);
            }
            if (this.start == 8) {
                this.viewChoice.select(3);
            }
            return true;
        }
        else {
            if (event.target instanceof TextField) {
                final double oldLat = this.latitude;
                double oldLong = this.longitude;
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
                    final Double latDouble = Double.valueOf(this.str);
                    this.latitude = latDouble;
                    if (this.nsChoice.getSelectedItem().equals("S")) {
                        this.latitude = -Math.abs(this.latitude);
                        this.usrLat = this.latitude;
                    }
                    this.fieldLatDeg.setText(this.str);
                    if (this.latitude == (int)this.latitude) {
                        this.fieldLatDeg.setText(String.valueOf((int)Math.abs(this.latitude)) + ".00");
                    }
                    final Double longDouble = Double.valueOf(this.longStr);
                    this.usrLong = longDouble;
                    this.latStr = this.str;
                    if (this.latitude > 90.0) {
                        this.fieldLatDeg.setText(String.valueOf(oldLat));
                        this.latitude = oldLat;
                        this.latStr = String.valueOf(Math.abs(oldLat));
                        this.fieldLatDeg.setText(this.latStr);
                    }
                    this.myCan = new suncanvas(this.zoom, this.start, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                    this.fieldLatDeg.nextFocus();
                    this.fieldLongDeg.selectAll();
                    this.repaint();
                    return true;
                }
                else if (event.target == this.fieldLongDeg) {
                    oldLong = this.longitude;
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
                    final Double longDouble = Double.valueOf(this.longStr);
                    this.longitude = longDouble;
                    if (this.ewChoice.getSelectedItem().equals("W")) {
                        this.longitude = -Math.abs(this.longitude);
                        this.usrLong = this.longitude;
                    }
                    this.fieldLongDeg.setText(this.longStr);
                    if (this.longitude > 180.0) {
                        this.fieldLongDeg.setText(String.valueOf(oldLong));
                        this.longitude = oldLong;
                    }
                    if (this.longitude == (int)this.longitude) {
                        this.fieldLongDeg.setText(String.valueOf((int)Math.abs(this.longitude)) + ".00");
                    }
                    this.myCan = new suncanvas(this.zoom, this.start, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                    this.repaint();
                    return true;
                }
            }
            if (event.target == this.locChoice) {
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
                this.myCan = new suncanvas(this.zoom, this.start, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
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
                    this.usrLat = this.latitude;
                    this.usrLong = this.longitude;
                    this.myCan = new suncanvas(this.zoom, this.start, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
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
                    this.myCan = new suncanvas(this.zoom, this.start, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                    this.repaint();
                    return true;
                }
                if (event.target == this.timeChoice) {
                    this.timeString = this.timeChoice.getSelectedItem();
                    this.locOffset = this.loc.getTimeZone(this.timeString);
                    this.timeChoice.select(this.timeString);
                    this.myCan = new suncanvas(this.zoom, this.start, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                }
                if (event.target == this.dateChoice) {
                    this.dateStr = this.dateChoice.getSelectedItem();
                    this.date = Integer.parseInt(this.dateStr);
                    this.dat.setDate(this.date);
                    n = this.daysInMonth(this.month, this.year);
                    if (this.date > n) {
                        this.date = n;
                        this.dat.setDate(this.date);
                        this.dateChoice.select(this.date - 1);
                    }
                    this.browserOffset = -this.dat.getTimezoneOffset() / 60;
                    this.myCan = new suncanvas(this.zoom, this.start, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                    this.repaint();
                    return true;
                }
                if (event.target == this.monthChoice) {
                    this.monthStr = this.monthChoice.getSelectedItem();
                    this.month = this.MonthInteger(this.monthStr);
                    this.monthChoice.select(this.month);
                    this.dat.setMonth(this.month);
                    n = this.daysInMonth(this.month, this.year);
                    if (this.date > n) {
                        this.date = n;
                        this.dat.setDate(this.date);
                        this.dateChoice.select(this.date - 1);
                    }
                    this.browserOffset = -this.dat.getTimezoneOffset() / 60;
                    this.myCan = new suncanvas(this.zoom, this.start, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                    this.repaint();
                    return true;
                }
                if (event.target == this.yearChoice) {
                    this.yearStr = this.yearChoice.getSelectedItem();
                    this.year = Integer.parseInt(this.yearStr);
                    this.yearChoice.select(this.yearStr);
                    this.year -= 1900;
                    this.dat.setYear(this.year);
                    n = this.daysInMonth(this.month, this.year);
                    if (this.date > n) {
                        this.date = n;
                        this.dat.setDate(this.date);
                        this.dateChoice.select(this.date - 1);
                    }
                    this.browserOffset = -this.dat.getTimezoneOffset() / 60;
                    this.myCan = new suncanvas(this.zoom, this.start, this.dat, this.latitude, this.longitude, this.locString, this.locOffset, this.versStr, this.online);
                    this.repaint();
                    return true;
                }
                this.repaint();
                return true;
            }
        }
    }
    
    public SolsticeDaylight25() {
        this.versStr = " v. 2.5";
        this.monthArray = new String[12];
        this.online = false;
        this.demo = true;
        this.usrLat = 0.0;
        this.usrLong = 0.0;
        this.start = 4;
        this.zoom = 10.0;
        this.STR = new String[32][12];
    }
}
