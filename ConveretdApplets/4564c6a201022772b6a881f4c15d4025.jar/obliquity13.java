import java.awt.Font;
import java.awt.Graphics;
import java.awt.Event;
import java.net.URL;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Button;
import java.awt.Label;
import java.awt.Choice;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class obliquity13 extends Applet
{
    String versStr;
    final char deg = '°';
    Date dat;
    int year;
    Choice year1Choice;
    Choice year2Choice;
    Choice deltaChoice;
    String year1Str;
    String year2Str;
    String deltaStr;
    int delta;
    public Label L1;
    public Label L2;
    public Label L3;
    public Label L4;
    public Label L5;
    public Label L6;
    public Label L7;
    public Label L8;
    public Label L9;
    public Label L10;
    public Label L11;
    public Label L12;
    public Label L20;
    public Label L21;
    public Label L22;
    public Button writeButton;
    scrollFrame sf;
    String[] dataStr;
    boolean online;
    int year1;
    int year2;
    int date;
    int month;
    int currentYear;
    String userString;
    boolean demo;
    double deltaEps;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 2444;
    }
    
    public void init() {
        final URL url = this.getDocumentBase();
        String str = url.toString();
        str = String.valueOf(str) + "1234567890123456789012345";
        final String wwwStr = str.substring(0, 27);
        this.setBackground(Color.lightGray);
        final GridLayout gbl = new GridLayout(7, 3, 20, 20);
        this.setLayout(gbl);
        this.dat = new Date();
        this.date = this.dat.getDate();
        this.month = this.dat.getMonth() + 1;
        this.currentYear = this.dat.getYear() + 1900;
        (this.L7 = new Label()).setText("");
        this.add(this.L7);
        (this.L8 = new Label()).setText("");
        this.add(this.L8);
        (this.L9 = new Label()).setText("");
        this.add(this.L9);
        (this.L10 = new Label()).setText("     Current Mean:");
        this.add(this.L10);
        (this.L11 = new Label()).setText("");
        this.add(this.L11);
        (this.L12 = new Label()).setText("");
        this.add(this.L12);
        (this.L20 = new Label()).setText("     Current True:");
        this.add(this.L20);
        (this.L21 = new Label()).setText("");
        this.add(this.L21);
        (this.L22 = new Label()).setText("");
        this.add(this.L22);
        (this.L1 = new Label()).setText("      Start Year:");
        this.add(this.L1);
        (this.L2 = new Label()).setText("      Stop Year:");
        this.add(this.L2);
        (this.L3 = new Label()).setText("      Intervall:");
        this.add(this.L3);
        this.year1Choice = new Choice();
        for (int i = 0; i < 70; ++i) {
            this.year1Str = " " + (-21000 + i * 500);
            this.year1Choice.addItem(this.year1Str);
        }
        this.add(this.year1Choice);
        this.year2Choice = new Choice();
        for (int j = 0; j < 70; ++j) {
            this.year2Str = " " + (-4500 + j * 500);
            this.year2Choice.addItem(this.year2Str);
        }
        this.add(this.year2Choice);
        this.year2Choice.select(" 2000");
        this.deltaChoice = new Choice();
        this.deltaStr = "1";
        this.deltaChoice.addItem(this.deltaStr);
        this.deltaStr = "10";
        this.deltaChoice.addItem(this.deltaStr);
        this.deltaStr = "50";
        this.deltaChoice.addItem(this.deltaStr);
        this.deltaStr = "100";
        this.deltaChoice.addItem(this.deltaStr);
        this.deltaStr = "500";
        this.deltaChoice.addItem(this.deltaStr);
        this.add(this.deltaChoice);
        this.deltaChoice.select("500");
        (this.L4 = new Label()).setText("");
        this.add(this.L4);
        (this.writeButton = new Button()).setLabel("Write Table");
        this.add(this.writeButton);
        (this.L5 = new Label()).setText("");
        this.add(this.L5);
        (this.L6 = new Label()).setText("");
        this.add(this.L6);
        boolean ok = true;
        final String email = this.getParameter("email");
        final String param = this.getParameter("password");
        if (this.formula(wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(wwwStr, 22) == this.formula("http://www.geoastro.de", 22) || this.formula(wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(wwwStr, 27) == this.formula("http://www.jgiesen.business", 27)) {
            ok = true;
            this.online = true;
            this.demo = false;
        }
        else {
            ok = false;
        }
        if (!ok) {
            ok = true;
            if (email.length() == 0 || (Integer.parseInt(param) != this.formula(email, email.length()) && Integer.parseInt(param) != this.formula(email, email.length()) - 10)) {
                ok = false;
            }
            else {
                ok = true;
                this.demo = false;
            }
            if (wwwStr.substring(0, 7).equals("http://")) {
                ok = false;
                this.demo = true;
                this.online = true;
            }
            if (!ok) {
                this.online = true;
            }
        }
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof Choice) {
            if (event.target == this.year1Choice) {
                this.year1Str = this.year1Choice.getSelectedItem();
                this.year1Str = this.year1Str.substring(1, this.year1Str.length());
                this.year1 = Integer.parseInt(this.year1Str);
                this.year1Choice.select(" " + this.year1Str);
                this.repaint();
                return true;
            }
            if (event.target == this.year2Choice) {
                this.year2Str = this.year2Choice.getSelectedItem();
                this.year2Str = this.year2Str.substring(1, this.year2Str.length());
                this.year2 = Integer.parseInt(this.year2Str);
                this.year2Choice.select(this.year2Str);
                this.repaint();
                return true;
            }
            if (event.target == this.deltaChoice) {
                this.deltaStr = this.deltaChoice.getSelectedItem();
                this.delta = Integer.parseInt(this.deltaStr);
                this.deltaChoice.select(this.deltaStr);
                this.repaint();
                return true;
            }
        }
        if (event.target instanceof Button) {
            this.write();
            return true;
        }
        return true;
    }
    
    public void write() {
        for (int i = 0; i < 2000; ++i) {
            this.dataStr[i] = "";
        }
        this.dataStr[0] = " © J. Giesen  -  www.GeoAstro.de   \n\n";
        this.dataStr[1] = " Year     Jul.Day        Mean          Mean              True         True           deltaEps         deltaEps\n";
        int k = 2;
        int year = this.year1;
        double T = 0.0;
        double eps = 0.0;
        final int anzahl = (this.year2 - this.year1) / this.delta;
        if (this.year2 < this.year1 || anzahl > 2000) {
            if (this.year2 < this.year1) {
                this.dataStr[3] = " Stop Year must be grater than start year";
            }
            if (anzahl > 2000) {
                this.dataStr[3] = " Too many values";
            }
        }
        else {
            String jdStr = "";
            year -= this.delta;
            while (year < this.year2) {
                year += this.delta;
                if (year < 0) {
                    T = (this.JD(1, 1, year + 1, 12.0) - 2451545.0) / 36525.0;
                }
                else {
                    T = (this.JD(1, 1, year, 12.0) - 2451545.0) / 36525.0;
                }
                this.deltaEps = this.deltaEps(T) / 3600.0;
                T /= 100.0;
                eps = 23.433333333333334;
                eps += (21.448 - 4680.93 * T - 1.55 * T * T + 1999.25 * T * T * T - 51.38 * T * T * T * T - 249.67 * T * T * T * T * T - 39.05 * T * T * T * T * T * T + 7.12 * T * T * T * T * T * T * T + 27.87 * T * T * T * T * T * T * T * T + 5.79 * T * T * T * T * T * T * T * T * T + 2.45 * T * T * T * T * T * T * T * T * T * T) / 3600.0;
                if (year < 0) {
                    this.dataStr[k] = String.valueOf(year + 1);
                }
                else {
                    this.dataStr[k] = String.valueOf(year);
                }
                while (this.dataStr[k].length() < 8) {
                    final String[] dataStr = this.dataStr;
                    final int n = k;
                    dataStr[n] = String.valueOf(dataStr[n]) + " ";
                }
                if (year < 0) {
                    jdStr = String.valueOf((int)this.JD(1, 1, year + 1, 12.0));
                }
                else {
                    jdStr = String.valueOf((int)this.JD(1, 1, year, 12.0));
                }
                final String[] dataStr2 = this.dataStr;
                final int n2 = k;
                dataStr2[n2] = String.valueOf(dataStr2[n2]) + "  " + jdStr;
                while (this.dataStr[k].length() < 20) {
                    final String[] dataStr3 = this.dataStr;
                    final int n3 = k;
                    dataStr3[n3] = String.valueOf(dataStr3[n3]) + " ";
                }
                final String[] dataStr4 = this.dataStr;
                final int n4 = k;
                dataStr4[n4] = String.valueOf(dataStr4[n4]) + "  " + Math.round(100000.0 * eps) / 100000.0 + '°';
                while (this.dataStr[k].length() < 33) {
                    final String[] dataStr5 = this.dataStr;
                    final int n5 = k;
                    dataStr5[n5] = String.valueOf(dataStr5[n5]) + " ";
                }
                final String[] dataStr6 = this.dataStr;
                final int n6 = k;
                dataStr6[n6] = String.valueOf(dataStr6[n6]) + "  " + this.DMS(eps);
                while (this.dataStr[k].length() < 52) {
                    final String[] dataStr7 = this.dataStr;
                    final int n7 = k;
                    dataStr7[n7] = String.valueOf(dataStr7[n7]) + " ";
                }
                final String[] dataStr8 = this.dataStr;
                final int n8 = k;
                dataStr8[n8] = String.valueOf(dataStr8[n8]) + "  " + Math.round(100000.0 * (eps + this.deltaEps)) / 100000.0 + '°';
                while (this.dataStr[k].length() < 64) {
                    final String[] dataStr9 = this.dataStr;
                    final int n9 = k;
                    dataStr9[n9] = String.valueOf(dataStr9[n9]) + " ";
                }
                final String[] dataStr10 = this.dataStr;
                final int n10 = k;
                dataStr10[n10] = String.valueOf(dataStr10[n10]) + "  " + this.DMS(eps + this.deltaEps);
                while (this.dataStr[k].length() < 83) {
                    final String[] dataStr11 = this.dataStr;
                    final int n11 = k;
                    dataStr11[n11] = String.valueOf(dataStr11[n11]) + " ";
                }
                final String[] dataStr12 = this.dataStr;
                final int n12 = k;
                dataStr12[n12] = String.valueOf(dataStr12[n12]) + "  " + Math.round(100000.0 * this.deltaEps) / 100000.0 + '°';
                while (this.dataStr[k].length() < 95) {
                    final String[] dataStr13 = this.dataStr;
                    final int n13 = k;
                    dataStr13[n13] = String.valueOf(dataStr13[n13]) + " ";
                }
                final String[] dataStr14 = this.dataStr;
                final int n14 = k;
                dataStr14[n14] = String.valueOf(dataStr14[n14]) + "  " + this.DMS(this.deltaEps) + "\n";
                ++k;
            }
        }
        final scrollFrame sf = new scrollFrame("Obliquity of the Ecliptic", k + 1, this.dataStr, this.online);
        sf.resize(720, 480);
        sf.show();
    }
    
    double sunL(final double T) {
        double L = 280.46645 + 36000.76983 * T + 3.032E-4 * T * T;
        final double tau = T / 10.0;
        L = 280.4664567 + 360007.6982779 * tau + 0.03032028 * tau * tau + tau * tau * tau / 49931.0 - tau * tau * tau * tau / 15299.0 + tau * tau * tau * tau * tau / 1988000.0;
        return L;
    }
    
    double JD(final int date, int month, int year, final double UT) {
        if (month <= 2) {
            month += 12;
            --year;
        }
        final int A = (int)(year / 100.0);
        int B = 2 - A + (int)(A / 4.0);
        if (year < 1582) {
            B = 0;
        }
        return (int)(365.25 * (year + 4716)) + (int)(30.6001 * (month + 1)) + date + B - 1524.5 + UT / 24.0;
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
        str = String.valueOf(str) + DEG + '°' + " ";
        if (MIN < 10) {
            str = String.valueOf(str) + " ";
        }
        str = String.valueOf(str) + MIN + "' ";
        if (SEC < 10.0) {
            str = String.valueOf(str) + " ";
        }
        str = String.valueOf(str) + Math.round(100.0 * SEC) / 100.0 + "''";
        return str;
    }
    
    public double frac(double X) {
        X -= (int)X;
        if (X < 0.0) {
            ++X;
        }
        return X;
    }
    
    public double deltaPSI(final double T) {
        final double K = 0.017453292519943295;
        final double[][] L = { { 0.0, 0.0, 0.0, 0.0, 1.0, -171996.0, -174.2 }, { -2.0, 0.0, 0.0, 2.0, 2.0, -13187.0, -1.6 }, { 0.0, 0.0, 0.0, 2.0, 2.0, -2274.0, -0.2 }, { 0.0, 0.0, 0.0, 0.0, 2.0, 2062.0, 0.2 }, { 0.0, 1.0, 0.0, 0.0, 0.0, 1426.0, -3.4 }, { 0.0, 0.0, 1.0, 0.0, 0.0, 712.0, 0.1 }, { -2.0, 1.0, 0.0, 2.0, 2.0, -517.0, 1.2 }, { 0.0, 0.0, 0.0, 2.0, 1.0, -386.0, -0.4 }, { 0.0, 0.0, 1.0, 2.0, 2.0, -301.0, 0.0 }, { -2.0, -1.0, 0.0, 2.0, 2.0, 217.0, -0.5 }, { -2.0, 0.0, 1.0, 0.0, 0.0, -158.0, 0.0 }, { -2.0, 0.0, 0.0, 2.0, 1.0, 129.0, 0.1 }, { 0.0, 0.0, -1.0, 2.0, 2.0, 123.0, 0.0 }, { 2.0, 0.0, 0.0, 0.0, 0.0, 63.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0, 1.0, 63.0, 0.1 }, { 2.0, 0.0, -1.0, 2.0, 2.0, -59.0, 0.0 }, { 0.0, 0.0, -1.0, 0.0, 1.0, -58.0, -0.1 }, { 0.0, 0.0, 1.0, 2.0, 1.0, -51.0, 0.0 }, { -2.0, 0.0, 2.0, 0.0, 0.0, 48.0, 0.0 }, { 0.0, 0.0, -2.0, 2.0, 1.0, 46.0, 0.0 } };
        final double D = 297.85036 + 445267.11148 * T - 0.0019142 * T * T + T * T * T / 189474.0;
        final double M = 357.52772 + 35999.05034 * T - 1.603E-4 * T * T - T * T * T / 300000.0;
        final double Ms = 134.96298 + 477198.867398 * T + 0.0086972 * T * T + T * T * T / 56250.0;
        final double F = 93.27191 + 483202.017538 * T - 0.0036825 * T * T + T * T * T / 327270.0;
        final double omega = 125.04452 - 1934.136261 * T + 0.0020708 * T * T + T * T * T / 450000.0;
        double sum = 0.0;
        for (int i = 0; i <= 19; ++i) {
            sum += (L[i][5] + L[i][6] * T) * Math.sin(0.017453292519943295 * (L[i][0] * D + L[i][1] * M + L[i][2] * Ms + L[i][3] * F + L[i][4] * omega));
        }
        return sum / 10000.0;
    }
    
    public double deltaEps(final double T) {
        final double K = 0.017453292519943295;
        final double[][] L = { { 0.0, 0.0, 0.0, 0.0, 1.0, 92025.0, 8.9 }, { -2.0, 0.0, 0.0, 2.0, 2.0, 5736.0, -3.1 }, { 0.0, 0.0, 0.0, 2.0, 2.0, 977.0, -0.5 }, { 0.0, 0.0, 0.0, 0.0, 2.0, -895.0, 0.5 }, { 0.0, 1.0, 0.0, 0.0, 0.0, 54.0, -0.1 }, { 0.0, 0.0, 1.0, 0.0, 0.0, -7.0, 0.0 }, { -2.0, 1.0, 0.0, 2.0, 2.0, 224.0, -0.6 }, { 0.0, 0.0, 0.0, 2.0, 1.0, 200.0, 0.0 }, { 0.0, 0.0, 1.0, 2.0, 2.0, 129.0, -0.1 }, { -2.0, -1.0, 0.0, 2.0, 2.0, -95.0, 0.3 }, { -2.0, 0.0, 0.0, 2.0, 1.0, -70.0, 0.0 }, { 0.0, 0.0, -1.0, 2.0, 2.0, -53.0, 0.0 }, { 0.0, 0.0, 1.0, 0.0, 1.0, -33.0, 0.0 }, { 2.0, 0.0, -1.0, 2.0, 2.0, 26.0, 0.0 }, { 0.0, 0.0, -1.0, 0.0, 1.0, 32.0, 0.0 }, { 0.0, 0.0, 1.0, 2.0, 1.0, 27.0, 0.0 }, { 0.0, 0.0, -2.0, 2.0, 1.0, -24.0, 0.0 } };
        final double D = 297.85036 + 445267.11148 * T - 0.0019142 * T * T + T * T * T / 189474.0;
        final double M = 357.52772 + 35999.05034 * T - 1.603E-4 * T * T - T * T * T / 300000.0;
        final double Ms = 134.96298 + 477198.867398 * T + 0.0086972 * T * T + T * T * T / 56250.0;
        final double F = 93.27191 + 483202.017538 * T - 0.0036825 * T * T + T * T * T / 327270.0;
        final double omega = 125.04452 - 1934.136261 * T + 0.0020708 * T * T + T * T * T / 450000.0;
        double sum = 0.0;
        for (int i = 0; i <= 16; ++i) {
            sum += (L[i][5] + L[i][6] * T) * Math.cos(0.017453292519943295 * (L[i][0] * D + L[i][1] * M + L[i][2] * Ms + L[i][3] * F + L[i][4] * omega));
        }
        return sum / 10000.0;
    }
    
    public void paint(final Graphics g) {
        double T = (this.JD(this.date, this.month, this.currentYear, 12.0) - 2451545.0) / 36525.0;
        this.deltaEps = this.deltaEps(T) / 3600.0;
        T /= 100.0;
        double currentEps = 23.433333333333334;
        currentEps += (21.448 - T * 4680.93 - 1.55 * T * T + 1999.25 * T * T * T - 51.38 * T * T * T * T - 249.67 * T * T * T * T * T - 39.05 * T * T * T * T * T * T + 7.12 * T * T * T * T * T * T * T + 27.87 * T * T * T * T * T * T * T * T + 5.79 * T * T * T * T * T * T * T * T * T + 2.45 * T * T * T * T * T * T * T * T * T * T) / 3600.0;
        this.L11.setText("      " + Math.round(100000.0 * currentEps) / 100000.0 + '°');
        this.L12.setText(this.DMS(currentEps));
        this.L21.setText("      " + Math.round(100000.0 * (currentEps + this.deltaEps)) / 100000.0 + '°');
        this.L22.setText(this.DMS(currentEps + this.deltaEps));
        g.setFont(new Font("Helvetica", 1, 12));
        g.drawString("       Obliquity of the Ecliptic", 45, 28);
        g.setFont(new Font("Helvetica", 0, 9));
        g.drawString("Obliquity  " + this.versStr + "  -  © J. Giesen " + (this.dat.getYear() + 1900) + "  --  www.GeoAstro.de", 20, this.size().height - 22);
    }
    
    public obliquity13() {
        this.versStr = "1.3 ";
        this.delta = 500;
        this.dataStr = new String[2000];
        this.online = false;
        this.year1 = -4500;
        this.year2 = 2000;
        this.demo = true;
    }
}
