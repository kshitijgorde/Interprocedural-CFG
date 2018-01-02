import java.awt.Graphics;
import java.awt.Event;
import java.net.URL;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Button;
import java.awt.Label;
import java.awt.Canvas;
import java.awt.Panel;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SkyPrecession077 extends Applet
{
    final double K = 0.017453292519943295;
    char deg;
    Data myValue;
    Panel p;
    Panel pSouth;
    Canvas c;
    Compute comp;
    String versStr;
    Label L11;
    Label L12;
    Label dummyL1;
    Label dummyL2;
    Label dummyL5;
    Label L112;
    Label L_2;
    Label L_3;
    Label L_4;
    Button run;
    TextField initialEpoqueField;
    TextField finalEpoqueField;
    Choice magChoice;
    Choice detailChoice;
    double epoqueInitial;
    double epoqueFinal;
    double JDInitial;
    double JDFinal;
    double raInitial;
    double raFinal;
    double decInitial;
    double decFinal;
    boolean demo;
    double epoqueIn;
    double epoqueFin;
    String str1;
    String str2;
    int N;
    double mag;
    boolean nameOK;
    boolean pos2000;
    double L;
    double BB;
    double pmRA1;
    double pmDec1;
    double pmRA;
    double pmDec;
    double ra1;
    double dec1;
    
    public void init() {
        this.setFont(new Font("Helvetica", 0, 12));
        final URL url = this.getDocumentBase();
        String myStr = url.toString();
        myStr = String.valueOf(myStr) + "1234567890123456789012345";
        final String wwwStr = myStr.substring(0, 27);
        final String str2000 = this.getParameter("pos2000");
        if (Integer.parseInt(str2000) == 0) {
            this.pos2000 = false;
        }
        else {
            this.pos2000 = true;
        }
        this.setLayout(new BorderLayout());
        this.setBackground(Color.white);
        (this.p = new Panel()).setLayout(new GridLayout(1, 7));
        this.p.add(this.L11);
        this.initialEpoqueField.setBackground(new Color(220, 255, 220));
        this.p.add(this.initialEpoqueField);
        this.p.add(this.L12);
        this.finalEpoqueField.setBackground(new Color(220, 255, 220));
        this.p.add(this.finalEpoqueField);
        (this.magChoice = new Choice()).addItem("Mag<3");
        this.magChoice.addItem("Mag<2");
        this.magChoice.addItem("Mag<1");
        this.magChoice.select("Mag<3");
        this.p.add(this.magChoice);
        this.detailChoice.addItem("Details");
        this.detailChoice.addItem("J 2000");
        this.detailChoice.addItem("Names");
        this.p.add(this.detailChoice);
        this.p.add(this.run);
        this.initialEpoqueField.selectAll();
        this.add("North", this.p);
        (this.c = new Canvas()).setBackground(new Color(255, 255, 255));
        (this.pSouth = new Panel()).setLayout(new GridLayout(1, 9));
        this.pSouth.add(this.L_2);
        this.pSouth.add(this.L112);
        this.add("South", this.pSouth);
        this.finalEpoqueField.nextFocus();
        this.initialEpoqueField.selectAll();
        boolean ok = true;
        final String email = this.getParameter("email");
        final String param = this.getParameter("password");
        if (this.formula(wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(wwwStr, 22) == this.formula("http://www.geoastro.de", 22) || this.formula(wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(wwwStr, 20) == this.formula("http://www.SciAm.com", 20)) {
            ok = true;
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
            this.L112.setText("    D  E  M  O");
        }
        this.getInput();
    }
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 12445;
    }
    
    public String checkInput(final String str) {
        String s = "";
        for (int i = 0; i < str.length(); ++i) {
            final char c = str.charAt(i);
            if (c == ',') {
                s = String.valueOf(s) + ".";
            }
            else {
                s = String.valueOf(s) + c;
            }
        }
        String s2 = "";
        for (int j = 0; j < s.length(); ++j) {
            final char c = s.charAt(j);
            if (c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9' || c == '.' || c == '-') {
                s2 = String.valueOf(s2) + c;
            }
        }
        return s2;
    }
    
    public void getInput() {
        this.L112.setText("");
        this.str1 = this.initialEpoqueField.getText();
        this.str1 = this.checkInput(this.str1);
        this.initialEpoqueField.setText(this.str1);
        this.str2 = this.finalEpoqueField.getText();
        this.str2 = this.checkInput(this.str2);
        this.finalEpoqueField.setText(this.str2);
        final boolean OK = true;
        System.out.println("Calculate precession");
        try {
            System.out.println("Initial J " + this.str1);
            final Double myDouble = Double.valueOf(this.str1);
            this.epoqueInitial = myDouble;
        }
        catch (NumberFormatException ex) {
            this.L112.setText("Initial Epoque missing");
        }
        try {
            System.out.println("Final   J " + this.str2);
            final Double myDouble = Double.valueOf(this.str2);
            this.epoqueFinal = myDouble;
        }
        catch (NumberFormatException ex2) {
            this.L112.setText("Final Epoque missing");
        }
        this.N = (int)Math.round((this.epoqueFinal - this.epoqueInitial) / 100.0);
        this.N = Math.abs(this.N);
        if (!OK) {
            this.L112.setText("  Check input !");
        }
    }
    
    public void transform(final double JDFinal) {
        final double TT = (JDFinal - 2451545.0) / 36525.0;
        final double eps = 23.43929111111111 - 46.815 * TT / 3600.0 - 5.9E-4 * TT * TT / 3600.0 + 0.001813 * TT * TT * TT / 3600.0;
        final double Z = Math.sin(0.017453292519943295 * this.raFinal * 15.0) * Math.cos(0.017453292519943295 * eps) + Math.tan(0.017453292519943295 * this.decFinal) * Math.sin(0.017453292519943295 * eps);
        final double N = Math.cos(0.017453292519943295 * this.raFinal * 15.0);
        final double tanL = Z / N;
        this.L = Math.atan2(Z, N) / 0.017453292519943295;
        if (this.L < 0.0) {
            this.L += 360.0;
        }
        final double sinB = Math.sin(0.017453292519943295 * this.decFinal) * Math.cos(0.017453292519943295 * eps) - Math.cos(0.017453292519943295 * this.decFinal) * Math.sin(0.017453292519943295 * eps) * Math.sin(0.017453292519943295 * this.raFinal * 15.0);
        this.BB = Math.asin(sinB) / 0.017453292519943295;
    }
    
    public void transform2000(final double ra, final double dec) {
        final double TT = 0.0;
        final double eps = 23.43929111111111 - 46.815 * TT / 3600.0 - 5.9E-4 * TT * TT / 3600.0 + 0.001813 * TT * TT * TT / 3600.0;
        final double Z = Math.sin(0.017453292519943295 * ra * 15.0) * Math.cos(0.017453292519943295 * eps) + Math.tan(0.017453292519943295 * dec) * Math.sin(0.017453292519943295 * eps);
        final double N = Math.cos(0.017453292519943295 * ra * 15.0);
        final double tanL = Z / N;
        this.L = Math.atan2(Z, N) / 0.017453292519943295;
        if (this.L < 0.0) {
            this.L += 360.0;
        }
        final double sinB = Math.sin(0.017453292519943295 * dec) * Math.cos(0.017453292519943295 * eps) - Math.cos(0.017453292519943295 * dec) * Math.sin(0.017453292519943295 * eps) * Math.sin(0.017453292519943295 * ra * 15.0);
        this.BB = Math.asin(sinB) / 0.017453292519943295;
    }
    
    public void calculate() {
        this.comp = new Compute();
        this.JDInitial = this.comp.JD(this.epoqueIn);
        this.JDFinal = this.comp.JD(this.epoqueFin);
        final double T = (this.JDInitial - 2451545.0) / 36525.0;
        final double t = (this.JDFinal - this.JDInitial) / 36525.0;
        double zeta = (2306.2181 + 1.39656 * T - 1.39E-4 * T * T) * t + (0.30188 - 3.44E-4 * T) * t * t + 0.017998 * t * t * t;
        double z = (2306.2181 + 1.39656 * T - 1.39E-4 * T * T) * t + (1.09468 + 6.6E-5 * T) * t * t + 0.018203 * t * t * t;
        double theta = (2004.3109 - 0.8533 * T - 2.17E-4 * T * T) * t - (0.42665 + 2.17E-4 * T) * t * t - 0.041833 * t * t * t;
        zeta /= 3600.0;
        z /= 3600.0;
        theta /= 3600.0;
        final double A = Math.cos(0.017453292519943295 * this.decInitial) * Math.sin(0.017453292519943295 * (15.0 * this.raInitial + zeta));
        final double B = Math.cos(0.017453292519943295 * theta) * Math.cos(0.017453292519943295 * this.decInitial) * Math.cos(0.017453292519943295 * (15.0 * this.raInitial + zeta)) - Math.sin(0.017453292519943295 * theta) * Math.sin(0.017453292519943295 * this.decInitial);
        final double C = Math.sin(0.017453292519943295 * theta) * Math.cos(0.017453292519943295 * this.decInitial) * Math.cos(0.017453292519943295 * (15.0 * this.raInitial + zeta)) + Math.cos(0.017453292519943295 * theta) * Math.sin(0.017453292519943295 * this.decInitial);
        final double tan = A / B;
        double alpha = Math.atan2(A, B) / 0.017453292519943295 + z;
        alpha /= 15.0;
        this.raFinal = alpha;
        if (this.raFinal < 0.0) {
            this.raFinal += 24.0;
        }
        if (this.raFinal > 24.0) {
            this.raFinal -= 24.0;
        }
        if (this.decInitial < 70.0) {
            this.decFinal = Math.asin(C) / 0.017453292519943295;
        }
        else {
            final double cosD = Math.sqrt(A * A + B * B);
            this.decFinal = Math.acos(cosD) / 0.017453292519943295;
        }
    }
    
    public boolean action(final Event evt, final Object eventobject) {
        if (this.demo) {
            return true;
        }
        if (evt.target instanceof Button && evt.target == this.run) {
            this.getInput();
            this.calculate();
            this.repaint();
            return true;
        }
        if (evt.target instanceof Choice) {
            if (evt.target == this.magChoice) {
                if (this.magChoice.getSelectedItem().equals("Mag<1")) {
                    this.mag = 1.0;
                }
                if (this.magChoice.getSelectedItem().equals("Mag<2")) {
                    this.mag = 2.0;
                }
                if (this.magChoice.getSelectedItem().equals("Mag<3")) {
                    this.mag = 3.0;
                }
                this.repaint();
                return true;
            }
            if (evt.target == this.detailChoice) {
                if (this.detailChoice.getSelectedItem().equals("J 2000")) {
                    this.pos2000 ^= true;
                }
                if (this.detailChoice.getSelectedItem().equals("Names")) {
                    this.nameOK ^= true;
                }
                this.detailChoice.select(0);
                this.repaint();
                return true;
            }
        }
        return true;
    }
    
    public double getRA(final int i) {
        double ra = 0.0;
        final String str = this.myValue.value(i)[0];
        try {
            final Double myDouble = Double.valueOf(str);
            ra = myDouble;
        }
        catch (NumberFormatException ex) {
            this.L112.setText("RA error");
        }
        return this.raInitial = ra;
    }
    
    public double getPMRA(final int i) {
        double y = 0.0;
        final String str = this.myValue.value(i)[4];
        try {
            final Double myDouble = Double.valueOf(str);
            y = myDouble;
        }
        catch (NumberFormatException ex) {
            this.L112.setText("PM RA error");
        }
        return y;
    }
    
    public double getPMDec(final int i) {
        double y = 0.0;
        final String str = this.myValue.value(i)[5];
        try {
            final Double myDouble = Double.valueOf(str);
            y = myDouble;
        }
        catch (NumberFormatException ex) {
            this.L112.setText("PM Dec error");
        }
        return y;
    }
    
    public double getDec(final int i) {
        double dec = 0.0;
        final String str = this.myValue.value(i)[1];
        try {
            final Double myDouble = Double.valueOf(str);
            dec = myDouble;
        }
        catch (NumberFormatException ex) {
            this.L112.setText("Dec error");
        }
        return this.decInitial = dec;
    }
    
    public double getMag(final int i) {
        double mag = 0.0;
        final String str = this.myValue.value(i)[2];
        try {
            final Double myDouble = Double.valueOf(str);
            mag = myDouble;
        }
        catch (NumberFormatException ex) {
            this.L112.setText("Mag error");
        }
        return mag;
    }
    
    public void paint(final Graphics g) {
        boolean done = false;
        g.setFont(new Font("Helvetica", 0, 11));
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        final int x0 = this.size().width / 2;
        final int y0 = 30 + (this.size().height - 50) / 2;
        final int Radius = x0 - 20;
        int x2 = 0;
        int y2 = 0;
        int x3 = 0;
        final int y3 = 0;
        g.setColor(Color.gray);
        for (int i = 0; i < 12; ++i) {
            final double sin = Math.sin(0.017453292519943295 * i * 30.0);
            final double cos = Math.cos(0.017453292519943295 * i * 30.0);
            y2 = (int)Math.round(Radius * sin);
            x2 = (int)Math.round(Radius * cos);
            g.drawLine(x0, y0, x0 + x2, y0 + y2);
            if (i == 0) {
                g.drawString(i * 2 + "h", x0 + x2 - 15, y0 + y2);
            }
            else {
                g.drawString(i * 2 + "h", x0 + x2, y0 + y2);
            }
        }
        for (int j = 0; j <= 9; ++j) {
            x3 = Math.round(j * Radius / 9);
            g.drawOval(x0 - x3, y0 - x3, 2 * x3, 2 * x3);
        }
        final double ra = 0.0;
        final double dec = 0.0;
        g.setColor(Color.black);
        for (int k = 0; k <= 66; ++k) {
            this.ra1 = this.getRA(k);
            this.dec1 = this.getDec(k);
            final String name = this.myValue.value(k)[3];
            this.pmRA1 = this.getPMRA(k);
            this.pmDec1 = this.getPMDec(k);
            this.pmRA = this.pmRA1 / 15000.0;
            this.pmDec = this.pmDec1 / 1000.0;
            if (name != "") {
                System.out.println(k + " J2000      RA = " + this.ra1 + " h    Dec = " + this.dec1 + this.deg + "   mag = " + this.getMag(k) + "  " + this.pmRA1 + "  " + this.pmDec1 + "   " + name);
            }
            this.transform2000(this.ra1, this.dec1);
            if (name != "") {
                System.out.println(k + " J2000      L  = " + Math.round(10000.0 * this.L) / 10000.0 + this.deg + "   B   = " + Math.round(10000.0 * this.BB) / 10000.0 + this.deg);
            }
            if (this.epoqueInitial == 2000.0) {
                this.raInitial = this.getRA(k);
                this.decInitial = this.getDec(k);
                g.setColor(Color.red);
                final double sin = Math.sin(0.2617993877991494 * this.ra1);
                final double cos = Math.cos(0.2617993877991494 * this.ra1);
                y2 = (int)Math.round((90.0 - this.dec1) * Radius * sin / 90.0);
                x2 = (int)Math.round((90.0 - this.dec1) * Radius * cos / 90.0);
                if (this.getMag(k) <= this.mag) {
                    g.drawOval(x0 + x2 - 1, y0 + y2 - 1, 2, 2);
                    if (this.nameOK) {
                        g.drawString(name, x0 + x2 + 3, y0 + y2 + 2);
                    }
                }
                this.calculate();
            }
            else {
                this.epoqueIn = 2000.0;
                this.epoqueFin = this.epoqueInitial;
                this.raInitial = this.getRA(k);
                this.decInitial = this.getDec(k);
                this.calculate();
                final double sin = Math.sin(0.2617993877991494 * this.raFinal);
                final double cos = Math.cos(0.2617993877991494 * this.raFinal);
                y2 = (int)Math.round((90.0 - this.decFinal) * Radius * sin / 90.0);
                x2 = (int)Math.round((90.0 - this.decFinal) * Radius * cos / 90.0);
                if (this.getMag(k) <= this.mag) {
                    g.setColor(Color.black);
                    if (this.decFinal >= 0.0) {
                        g.drawOval(x0 + x2 - 1, y0 + y2 - 1, 2, 2);
                        g.setColor(Color.red);
                        if (this.nameOK) {
                            g.drawString(name, x0 + x2 + 3, y0 + y2 + 2);
                        }
                    }
                }
                this.raInitial = this.raFinal;
                this.decInitial = this.decFinal;
            }
            if (this.epoqueInitial == this.epoqueFinal) {
                this.epoqueIn = 2000.0;
                this.epoqueFin = this.epoqueInitial;
                this.raInitial = this.getRA(k);
                this.decInitial = this.getDec(k);
                this.calculate();
                g.setColor(Color.black);
                final double sin = Math.sin(0.2617993877991494 * this.raFinal);
                final double cos = Math.cos(0.2617993877991494 * this.raFinal);
                y2 = (int)Math.round((90.0 - this.decFinal) * Radius * sin / 90.0);
                x2 = (int)Math.round((90.0 - this.decFinal) * Radius * cos / 90.0);
                if (this.getMag(k) <= this.mag && this.decFinal >= 0.0) {
                    g.drawOval(x0 + x2 - 1, y0 + y2 - 1, 2, 2);
                    g.setColor(Color.red);
                    if (this.nameOK) {
                        g.drawString(name, x0 + x2 + 3, y0 + y2 + 2);
                    }
                }
                done = true;
            }
            if (!done) {
                for (int n = 0; n <= this.N - 1; ++n) {
                    this.epoqueIn = this.epoqueInitial + n * (this.epoqueFinal - this.epoqueInitial) / this.N;
                    this.epoqueFin = this.epoqueInitial + (n + 1) * (this.epoqueFinal - this.epoqueInitial) / this.N;
                    this.calculate();
                    g.setColor(Color.black);
                    final double sin = Math.sin(0.2617993877991494 * this.raFinal);
                    final double cos = Math.cos(0.2617993877991494 * this.raFinal);
                    y2 = (int)Math.round((90.0 - this.decFinal) * Radius * sin / 90.0);
                    x2 = (int)Math.round((90.0 - this.decFinal) * Radius * cos / 90.0);
                    if (this.getMag(k) <= this.mag && this.decFinal >= 0.0) {
                        g.drawOval(x0 + x2 - 1, y0 + y2 - 1, 2, 2);
                    }
                    this.raInitial = this.raFinal;
                    this.decInitial = this.decFinal;
                }
            }
            if (name != "") {
                System.out.println(k + " J" + this.epoqueFinal + "    RA = " + Math.round(10000.0 * this.raFinal) / 10000.0 + " h     Dec = " + Math.round(10000.0 * this.decFinal) / 10000.0 + this.deg);
                final double delta = this.epoqueFinal - this.epoqueInitial;
                this.raFinal += this.pmRA * delta / 3600.0;
                this.decFinal += this.pmDec * delta / 3600.0;
                System.out.println(k + " J" + this.epoqueFinal + " PM RA = " + Math.round(10000.0 * this.raFinal) / 10000.0 + " h    Dec = " + Math.round(10000.0 * this.decFinal) / 10000.0 + this.deg);
                this.transform(this.JDFinal);
                System.out.println(k + " J" + this.epoqueFinal + "    L  = " + Math.round(10000.0 * this.L) / 10000.0 + this.deg + "   B  =  " + Math.round(10000.0 * this.BB) / 10000.0 + this.deg);
                System.out.println("");
            }
            if (this.pos2000) {
                g.setColor(Color.red);
                final double sin = Math.sin(0.2617993877991494 * this.ra1);
                final double cos = Math.cos(0.2617993877991494 * this.ra1);
                y2 = (int)Math.round((90.0 - this.dec1) * Radius * sin / 90.0);
                x2 = (int)Math.round((90.0 - this.dec1) * Radius * cos / 90.0);
                g.setColor(Color.red);
                if (this.getMag(k) <= this.mag && this.dec1 >= 0.0) {
                    g.fillOval(x0 + x2 - 2, y0 + y2 - 2, 4, 4);
                }
            }
        }
        g.setFont(new Font("Helvetica", 0, 10));
        g.drawString("SkyPrecession " + this.versStr + " - © 2003-2007 J. Giesen - www.GeoAstro.de", 110, this.size().height - 20);
        if (this.demo) {
            final Font f = g.getFont();
            g.setFont(new Font("Chicago", 0, 96));
            g.setColor(Color.red);
            g.drawString("DEMO", 150, 300);
            g.setFont(f);
        }
    }
    
    public boolean keyDown(final Event event, final int code) {
        final int n = 0;
        final int cmd = 0;
        if (code == 10 || code == 3 || code == 9) {
            if (event.target == this.initialEpoqueField) {
                this.initialEpoqueField.nextFocus();
                this.finalEpoqueField.selectAll();
            }
            if (event.target == this.finalEpoqueField) {
                this.finalEpoqueField.nextFocus();
                this.initialEpoqueField.selectAll();
            }
        }
        return false;
    }
    
    public SkyPrecession077() {
        this.deg = '°';
        this.myValue = new Data();
        this.versStr = "v. 0.77";
        this.L11 = new Label("      Inital J:");
        this.L12 = new Label("      Final J:");
        this.dummyL1 = new Label("");
        this.dummyL2 = new Label("");
        this.dummyL5 = new Label("");
        this.L112 = new Label("");
        this.L_2 = new Label("");
        this.L_3 = new Label("");
        this.L_4 = new Label("");
        this.run = new Button("Draw");
        this.initialEpoqueField = new TextField("2000", 10);
        this.finalEpoqueField = new TextField("2500", 10);
        this.magChoice = new Choice();
        this.detailChoice = new Choice();
        this.demo = true;
        this.str1 = "";
        this.str2 = "";
        this.mag = 3.0;
        this.nameOK = true;
        this.ra1 = 0.0;
        this.dec1 = 0.0;
    }
}
