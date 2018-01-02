import java.awt.Font;
import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Dialog;
import java.net.URL;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Choice;
import java.awt.Label;
import java.awt.Scrollbar;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ellipse093 extends Applet
{
    final char deg = '°';
    final double pi = 3.141592653589793;
    Scrollbar scrollE;
    Scrollbar scrollA;
    Scrollbar scrollPhi;
    Scrollbar scrollT;
    Label eLabel;
    Label aLabel;
    Label phiLabel;
    Label tLabel;
    Label detailsLabel;
    Label vLabel;
    Label v1Label;
    Label iterLabel;
    double e;
    int a;
    double max;
    double b;
    int valueA;
    int valueE;
    int valuePhi;
    int valueT;
    double phi;
    double t;
    double v;
    int X;
    int Y;
    int Y1;
    int[] x;
    int[] y;
    String[] data;
    int nData;
    boolean ea;
    boolean pos;
    boolean vel;
    boolean solSys;
    boolean showM;
    boolean showArcs;
    boolean moon;
    boolean radii;
    boolean velCircle;
    int positions;
    String detailStr;
    String aStr;
    double drawFaktor;
    double constant;
    double A;
    double kGauss;
    double AU;
    double aMoon;
    String unitStr;
    Choice detailsChoice;
    double R;
    double r;
    double rBlue;
    String titleStr;
    String versStr;
    String str;
    public String email;
    public String param;
    public String wwwStr;
    boolean online;
    boolean demo;
    String planetStr;
    
    public void init() {
        this.versStr = "Kepler 0.93";
        this.setBackground(new Color(255, 255, 255));
        this.constant = 1.0;
        this.drawFaktor = 100.0;
        this.A = this.a / this.max;
        this.b = this.a * Math.sqrt(1.0 - this.e * this.e);
        final URL url = this.getDocumentBase();
        this.str = url.toString();
        this.str = String.valueOf(this.str) + "1234567890123456789012345";
        this.wwwStr = this.str.substring(0, 27);
        this.setLayout(new BorderLayout());
        final Panel p = new Panel();
        p.setLayout(new GridLayout(0, 2));
        p.add(this.eLabel = new Label(" Eccentricity e = " + this.e));
        (this.scrollE = new Scrollbar(0)).setMaximum(209);
        this.scrollE.setValue(this.valueE);
        p.add(this.scrollE);
        p.add(this.aLabel = new Label(" Semimajor axis a = " + Math.round(100 * this.a / this.max) / 100.0));
        (this.scrollA = new Scrollbar(0)).setValue(this.valueA);
        p.add(this.scrollA);
        this.R = this.a * (1.0 - this.e * this.e) / (1.0 + this.e * Math.cos(3.141592653589793 * this.phi / 180.0));
        this.r = this.R / this.max;
        p.add(this.phiLabel = new Label(" phi=" + Math.round(10.0 * this.phi) / 10.0 + '°' + " r=" + Math.round(100.0 * this.r) / 100.0));
        (this.scrollPhi = new Scrollbar(0)).setBackground(Color.blue);
        this.scrollPhi.setMaximum(370);
        this.scrollPhi.setValue(this.valuePhi);
        p.add(this.scrollPhi);
        p.add(this.tLabel = new Label(" Time t/T=" + Math.round(100.0 * this.t) / 100.0 + " M=" + this.valuePhi + '°'));
        (this.scrollT = new Scrollbar(0)).setMaximum(370);
        this.scrollT.setBackground(Color.red);
        this.scrollT.setForeground(Color.red);
        p.add(this.scrollT);
        p.add(this.detailsLabel = new Label(this.detailStr));
        (this.detailsChoice = new Choice()).addItem("Reset");
        this.detailsChoice.addItem("Show/Hide E, phi, r");
        this.detailsChoice.addItem("Show/Hide Velocity v");
        this.detailsChoice.addItem("Show/Hide Vel. Circle");
        this.detailsChoice.addItem("Show/Hide M");
        this.detailsChoice.addItem("Show/Hide Arcs");
        this.detailsChoice.addItem("Show/Hide 10 Positions");
        this.detailsChoice.addItem("Show/Hide 20 Positions");
        this.detailsChoice.addItem("Show/Hide Radii");
        this.detailsChoice.addItem("Solar System");
        this.detailsChoice.addItem("Earth");
        this.detailsChoice.addItem("Mars");
        this.detailsChoice.addItem("Mercury");
        this.detailsChoice.addItem("Venus");
        this.detailsChoice.addItem("Moon");
        this.detailsChoice.addItem("Show Data");
        this.detailsChoice.addItem("About");
        p.add(this.detailsChoice);
        p.add(this.vLabel = new Label(" Details (v)"));
        p.add(this.v1Label = new Label(" "));
        (this.iterLabel = new Label(" ")).setForeground(Color.red);
        p.add(this.iterLabel);
        this.add("North", p);
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
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
                boolean dem = false;
                if (this.online || this.demo) {
                    dem = true;
                }
                if (dem) {
                    this.versStr = String.valueOf(this.versStr) + " DEMO";
                }
                final Dialog AboutDialog = new Aboutdialog(this, this.versStr, dem);
                AboutDialog.resize(350, 230);
                AboutDialog.show();
            }
        }
        this.repaint();
    }
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 3101;
    }
    
    public double EA(final double t) {
        final double K = 0.017453292519943295;
        double M = t / 360.0;
        double E = 0.0;
        int i = 0;
        final int maxIter = 15;
        final double delta = 1.0E-6;
        M = 6.283185307179586 * (M - Math.floor(M));
        if (M < 0.0) {
            M += 6.283185307179586;
        }
        if (this.e < 0.8) {
            E = M;
        }
        for (double F = E - this.e * Math.sin(M) - M; Math.abs(F) > delta && i < maxIter; F = E - this.e * Math.sin(E) - M, ++i) {
            E -= F / (1.0 - this.e * Math.cos(E));
        }
        if (i >= maxIter) {
            this.iterLabel.setText("Max. Iter. !");
        }
        else {
            this.iterLabel.setText("");
        }
        return E / K;
    }
    
    double XY(final int what) {
        final double K = 0.017453292519943295;
        final double E = this.EA(this.t);
        final double C = Math.cos(K * E);
        final double S = Math.sin(K * E);
        final double fak = Math.sqrt(1.0 - this.e * this.e);
        double result = 0.0;
        final double rho = 1.0 - this.e * C;
        final double F = this.constant / Math.sqrt(this.A);
        switch (what) {
            case 1: {
                result = this.a * (C - this.e);
                break;
            }
            case 2: {
                result = this.a * fak * S;
                break;
            }
            case 3: {
                result = Math.atan2(fak * S, C - this.e) / K;
                if (result < 0.0) {
                    result += 360.0;
                    break;
                }
                break;
            }
            case 4: {
                result = -F * S / rho;
                break;
            }
            case 5: {
                result = F * fak * C / rho;
                break;
            }
        }
        return result;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof Choice && event.target == this.detailsChoice) {
            if (this.detailsChoice.getSelectedItem().equals("Reset")) {
                this.planetStr = "Earth and Moon";
                this.e = 0.5;
                this.a = 130;
                this.constant = 1.0;
                this.drawFaktor = 100.0;
                this.A = this.a / this.max;
                this.b = this.a * Math.sqrt(1.0 - this.e * this.e);
                this.moon = false;
                this.solSys = false;
                this.pos = false;
                this.ea = false;
                this.vel = false;
                this.showM = false;
                this.showArcs = false;
                this.radii = false;
                this.unitStr = "";
                this.scrollE.setValue(100);
                this.valueE = 100;
                this.eLabel.setText(" Eccentricity e = " + Math.round(1000.0 * this.e) / 1000.0);
                this.valueA = 45;
                this.scrollA.setValue(this.valueA);
                this.aLabel.setText(" Semimajor axis a = " + Math.round(100 * this.a / this.max) / 100.0);
                this.phi = 0.0;
                this.valuePhi = 0;
                this.scrollPhi.setValue(0);
                this.b = this.a * Math.sqrt(1.0 - this.e * this.e);
                this.R = this.a * (1.0 - this.e * this.e) / (1.0 + this.e * Math.cos(3.141592653589793 * this.phi / 180.0));
                this.r = this.R / this.max;
                this.phiLabel.setText(" phi=" + Math.round(10.0 * this.phi) / 10.0 + '°' + " r=" + Math.round(1000.0 * this.r) / 1000.0);
                this.t = 0.0;
                this.valueT = 0;
                this.scrollT.setValue(0);
                this.tLabel.setText(" Time t/T=" + Math.round(100.0 * this.t) / 100.0 + " M=" + this.valuePhi + '°');
                this.detailsLabel.setText(this.detailStr);
                this.vLabel.setText(" Details (v)");
                this.v1Label.setText(" ");
                this.repaint();
                return true;
            }
            if (this.detailsChoice.getSelectedItem().equals("Show/Hide E, phi, r")) {
                this.ea ^= true;
                if (this.ea) {
                    this.b = this.a * Math.sqrt(1.0 - this.e * this.e);
                    this.R = this.a * (1.0 - this.e * this.e) / (1.0 + this.e * Math.cos(3.141592653589793 * this.phi / 180.0));
                    this.r = this.R / this.max;
                    this.detailsLabel.setText(" phi=" + Math.round(10.0 * this.phi) / 10.0 + '°' + " E=" + this.valueT + '°' + " r=" + Math.round(1000.0 * this.r) / 1000.0);
                }
                else {
                    this.detailsLabel.setText(this.detailStr);
                }
                this.repaint();
                return true;
            }
            if (this.detailsChoice.getSelectedItem().equals("Show/Hide Velocity v")) {
                this.vel ^= true;
                if (this.vel) {
                    this.v = Math.sqrt(this.XY(4) * this.XY(4) + this.XY(5) * this.XY(5));
                    this.vLabel.setText(" v=" + Math.round(1000.0 * this.v) / 1000.0 + this.unitStr);
                }
                else {
                    this.vLabel.setText(" Details (v)");
                }
                this.repaint();
                return true;
            }
            if (this.detailsChoice.getSelectedItem().equals("Show/Hide 10 Positions") || this.detailsChoice.getSelectedItem().equals("Show/Hide 20 Positions")) {
                this.pos = true;
                if (this.detailsChoice.getSelectedItem().equals("Show/Hide 10 Positions")) {
                    this.positions = 10;
                }
                if (this.detailsChoice.getSelectedItem().equals("Show/Hide 20 Positions")) {
                    this.positions = 20;
                }
                this.repaint();
                return true;
            }
            if (this.detailsChoice.getSelectedItem().equals("Solar System")) {
                this.solSys ^= true;
                this.str = " Semimajor axis a = " + Math.round(100 * this.a / this.max) / 100.0;
                if (this.solSys) {
                    this.planetStr = "Solar System";
                    this.drawFaktor = 3.3;
                    this.constant = this.kGauss * this.AU * Math.sqrt(this.AU) / 86400.0;
                    this.A = this.AU;
                    this.unitStr = " km/s";
                    this.str = String.valueOf(this.str) + this.aStr;
                }
                else {
                    this.constant = 1.0;
                    this.drawFaktor = 100.0;
                    this.A = this.a / this.max;
                    this.planetStr = "";
                }
                this.aLabel.setText(this.str);
                if (this.ea) {
                    this.detailsLabel.setText(" phi=" + Math.round(10.0 * this.XY(3)) / 10.0 + '°' + " E=" + this.valueT + '°' + " r=" + Math.round(1000.0 * this.r) / 1000.0);
                }
                if (this.vel) {
                    this.v = Math.sqrt(this.XY(4) * this.XY(4) + this.XY(5) * this.XY(5));
                    this.vLabel.setText(" v=" + Math.round(1000.0 * this.v) / 1000.0 + this.unitStr);
                }
                this.v1Label.setText(this.planetStr);
                this.repaint();
                return true;
            }
            if (this.detailsChoice.getSelectedItem().equals("Earth")) {
                this.solSys = true;
                this.moon = false;
                this.planetStr = "Sun and Earth";
                this.v1Label.setText(this.planetStr);
                this.e = 0.01671;
                this.valueE = (int)Math.round(200.0 * this.e);
                this.scrollE.setValue(this.valueE);
                this.eLabel.setText(" Eccentricity e = " + Math.round(10000.0 * this.e) / 10000.0);
                this.drawFaktor = 3.3;
                this.constant = this.kGauss * this.AU * Math.sqrt(this.AU) / 86400.0;
                this.A = this.AU;
                this.unitStr = " km/s";
                this.a = 130;
                this.valueA = 45;
                this.scrollA.setValue(this.valueA);
                this.aLabel.setText(" Semimajor axis a = " + Math.round(100 * this.a / this.max) / 100.0 + this.aStr);
                if (this.ea) {
                    this.detailsLabel.setText(" phi=" + Math.round(10.0 * this.XY(3)) / 10.0 + '°' + " E=" + this.valueT + '°' + " r=" + Math.round(1000.0 * this.r) / 1000.0);
                }
                if (this.vel) {
                    this.v = Math.sqrt(this.XY(4) * this.XY(4) + this.XY(5) * this.XY(5));
                    this.vLabel.setText(" v=" + Math.round(1000.0 * this.v) / 1000.0 + this.unitStr);
                }
                else {
                    this.vLabel.setText(" ");
                }
                this.b = this.a * Math.sqrt(1.0 - this.e * this.e);
                this.R = this.a * (1.0 - this.e * this.e) / (1.0 + this.e * Math.cos(3.141592653589793 * this.phi / 180.0));
                this.r = this.R / this.max;
                this.phiLabel.setText(" phi=" + Math.round(10.0 * this.phi) / 10.0 + '°' + " r=" + Math.round(1000.0 * this.r) / 1000.0);
                this.repaint();
                return true;
            }
            if (this.detailsChoice.getSelectedItem().equals("Mars")) {
                this.solSys = true;
                this.moon = false;
                this.planetStr = "Sun and Mars";
                this.v1Label.setText(this.planetStr);
                this.e = 0.0935;
                this.a = (int)Math.round(this.max * 227.92 / 149.6);
                this.valueE = (int)Math.round(200.0 * this.e);
                this.scrollE.setValue(this.valueE);
                this.eLabel.setText(" Eccentricity e = " + Math.round(1000.0 * this.e) / 1000.0);
                this.drawFaktor = 3.3;
                this.constant = this.kGauss * this.AU * Math.sqrt(this.AU) / 86400.0;
                this.A = 2.2792E8;
                this.unitStr = " km/s";
                this.valueA = 45;
                this.scrollA.setValue(this.valueA);
                this.aLabel.setText(" Semimajor axis a = " + Math.round(100 * this.a / this.max) / 100.0 + this.aStr);
                if (this.ea) {
                    this.detailsLabel.setText(" phi=" + Math.round(10.0 * this.XY(3)) / 10.0 + '°' + " E=" + this.valueT + '°' + " r=" + Math.round(1000.0 * this.r) / 1000.0);
                }
                if (this.vel) {
                    this.v = Math.sqrt(this.XY(4) * this.XY(4) + this.XY(5) * this.XY(5));
                    this.vLabel.setText(" v=" + Math.round(1000.0 * this.v) / 1000.0 + this.unitStr);
                }
                else {
                    this.vLabel.setText(" ");
                }
                this.b = this.a * Math.sqrt(1.0 - this.e * this.e);
                this.R = this.a * (1.0 - this.e * this.e) / (1.0 + this.e * Math.cos(3.141592653589793 * this.phi / 180.0));
                this.r = this.R / this.max;
                this.phiLabel.setText(" phi=" + Math.round(10.0 * this.phi) / 10.0 + '°' + " r=" + Math.round(1000.0 * this.r) / 1000.0);
                this.repaint();
                return true;
            }
            if (this.detailsChoice.getSelectedItem().equals("Mercury")) {
                this.solSys = true;
                this.moon = false;
                this.planetStr = "Sun and Mercury";
                this.v1Label.setText(this.planetStr);
                this.e = 0.20563;
                this.a = (int)Math.round(this.max * 0.387099);
                this.valueE = (int)Math.round(200.0 * this.e);
                this.scrollE.setValue(this.valueE);
                this.eLabel.setText(" Eccentricity e = " + Math.round(1000.0 * this.e) / 1000.0);
                this.drawFaktor = 3.3;
                this.constant = this.kGauss * this.AU * Math.sqrt(this.AU) / 86400.0;
                this.A = 5.791E7;
                this.unitStr = " km/s";
                this.valueA = 45;
                this.scrollA.setValue(this.valueA);
                this.aLabel.setText(" Semimajor axis a = " + Math.round(100 * this.a / this.max) / 100.0 + this.aStr);
                if (this.ea) {
                    this.detailsLabel.setText(" phi=" + Math.round(10.0 * this.XY(3)) / 10.0 + '°' + " E=" + this.valueT + '°' + " r=" + Math.round(1000.0 * this.r) / 1000.0);
                }
                if (this.vel) {
                    this.v = Math.sqrt(this.XY(4) * this.XY(4) + this.XY(5) * this.XY(5));
                    this.vLabel.setText(" v=" + Math.round(1000.0 * this.v) / 1000.0 + this.unitStr);
                }
                else {
                    this.vLabel.setText(" ");
                }
                this.b = this.a * Math.sqrt(1.0 - this.e * this.e);
                this.R = this.a * (1.0 - this.e * this.e) / (1.0 + this.e * Math.cos(3.141592653589793 * this.phi / 180.0));
                this.r = this.R / this.max;
                this.phiLabel.setText(" phi=" + Math.round(10.0 * this.phi) / 10.0 + '°' + " r=" + Math.round(1000.0 * this.r) / 1000.0);
                this.repaint();
                return true;
            }
            if (this.detailsChoice.getSelectedItem().equals("Venus")) {
                this.solSys = true;
                this.moon = false;
                this.planetStr = "Sun and Venus";
                this.v1Label.setText(this.planetStr);
                this.e = 0.0067;
                this.a = (int)Math.round(this.max * 0.7233);
                this.valueE = (int)Math.round(200.0 * this.e);
                this.scrollE.setValue(this.valueE);
                this.eLabel.setText(" Eccentricity e = " + Math.round(1000.0 * this.e) / 1000.0);
                this.drawFaktor = 3.3;
                this.constant = this.kGauss * this.AU * Math.sqrt(this.AU) / 86400.0;
                this.A = 1.0821E8;
                this.unitStr = " km/s";
                this.valueA = 45;
                this.scrollA.setValue(this.valueA);
                this.aLabel.setText(" Semimajor axis a = " + Math.round(100 * this.a / this.max) / 100.0 + this.aStr);
                if (this.ea) {
                    this.detailsLabel.setText(" phi=" + Math.round(10.0 * this.XY(3)) / 10.0 + '°' + " E=" + this.valueT + '°' + " r=" + Math.round(1000.0 * this.r) / 1000.0);
                }
                if (this.vel) {
                    this.v = Math.sqrt(this.XY(4) * this.XY(4) + this.XY(5) * this.XY(5));
                    this.vLabel.setText(" v=" + Math.round(1000.0 * this.v) / 1000.0 + this.unitStr);
                }
                else {
                    this.vLabel.setText(" ");
                }
                this.b = this.a * Math.sqrt(1.0 - this.e * this.e);
                this.R = this.a * (1.0 - this.e * this.e) / (1.0 + this.e * Math.cos(3.141592653589793 * this.phi / 180.0));
                this.r = this.R / this.max;
                this.phiLabel.setText(" phi=" + Math.round(10.0 * this.phi) / 10.0 + '°' + " r=" + Math.round(1000.0 * this.r) / 1000.0);
                this.repaint();
                return true;
            }
            if (this.detailsChoice.getSelectedItem().equals("Moon")) {
                this.moon = true;
                this.planetStr = "Earth and Moon";
                this.v1Label.setText(this.planetStr);
                this.e = 0.0554;
                this.a = 130;
                this.valueE = (int)Math.round(200.0 * this.e);
                this.scrollE.setValue(this.valueE);
                this.eLabel.setText(" Eccentricity e = " + Math.round(1000.0 * this.e) / 1000.0);
                this.drawFaktor = 100.0;
                this.constant = this.kGauss * Math.sqrt(3.003167261575587E-6) * this.AU * Math.sqrt(this.AU) / 86400.0;
                this.A = 384440.0;
                this.unitStr = " km/s";
                this.valueA = 45;
                this.scrollA.setValue(this.valueA);
                this.aLabel.setText(" Semimajor axis a = 384440 km");
                if (this.ea) {
                    if (!this.moon) {
                        this.detailsLabel.setText(" phi=" + Math.round(10.0 * this.XY(3)) / 10.0 + '°' + " E=" + this.valueT + '°' + " r=" + Math.round(1000.0 * this.r) / 1000.0);
                    }
                    else {
                        this.r = this.aMoon * (1.0 - this.e * this.e) / (1.0 + this.e * Math.cos(3.141592653589793 * this.phi / 180.0));
                        this.detailsLabel.setText(" phi=" + Math.round(10.0 * this.XY(3)) / 10.0 + '°' + " E=" + this.valueT + '°' + " r=" + Math.round(this.r) + " km");
                    }
                }
                if (this.vel) {
                    this.v = Math.sqrt(this.XY(4) * this.XY(4) + this.XY(5) * this.XY(5));
                    this.vLabel.setText(" v=" + Math.round(1000.0 * this.v) / 1000.0 + this.unitStr);
                }
                else {
                    this.vLabel.setText(" ");
                }
                this.b = this.a * Math.sqrt(1.0 - this.e * this.e);
                this.R = this.a * (1.0 - this.e * this.e) / (1.0 + this.e * Math.cos(3.141592653589793 * this.phi / 180.0));
                this.r = this.R / this.max;
                if (!this.moon) {
                    this.phiLabel.setText(" phi=" + Math.round(10.0 * this.phi) / 10.0 + '°' + " r=" + Math.round(1000.0 * this.r) / 1000.0);
                }
                else {
                    this.r = this.aMoon * (1.0 - this.e * this.e) / (1.0 + this.e * Math.cos(3.141592653589793 * this.phi / 180.0));
                    this.phiLabel.setText(" phi=" + Math.round(10.0 * this.phi) / 10.0 + '°' + " r=" + Math.round(this.r) + " km");
                }
                this.repaint();
                return true;
            }
            if (this.detailsChoice.getSelectedItem().equals("Show/Hide M")) {
                this.showM ^= true;
                this.repaint();
                return true;
            }
            if (this.detailsChoice.getSelectedItem().equals("Show/Hide Arcs")) {
                this.showArcs ^= true;
                this.repaint();
                return true;
            }
            if (this.detailsChoice.getSelectedItem().equals("Show Data")) {
                this.doData();
                boolean dem = false;
                if (this.online || this.demo) {
                    dem = true;
                }
                final Frame df = new dataFrame(this.titleStr, this.versStr, this.str, this.data, this.nData, dem);
                df.resize(400, 400);
                df.show();
            }
            if (this.detailsChoice.getSelectedItem().equals("Show/Hide Radii")) {
                this.radii ^= true;
                this.repaint();
                return true;
            }
            if (this.detailsChoice.getSelectedItem().equals("Show/Hide Vel. Circle")) {
                this.velCircle ^= true;
                this.repaint();
                return true;
            }
            if (this.detailsChoice.getSelectedItem().equals("About")) {
                this.doData();
                boolean dem = false;
                if (this.online || this.demo) {
                    dem = true;
                }
                final Dialog AboutDialog = new Aboutdialog(this, this.versStr, dem);
                AboutDialog.resize(350, 230);
                AboutDialog.show();
            }
        }
        return true;
    }
    
    public boolean handleEvent(final Event evt) {
        if (evt.target instanceof Scrollbar) {
            if (evt.target == this.scrollE) {
                this.valueE = ((Scrollbar)evt.target).getValue();
                this.e = this.valueE / 200.0;
                this.b = this.a * Math.sqrt(1.0 - this.e * this.e);
                this.R = this.a * (1.0 - this.e * this.e) / (1.0 + this.e * Math.cos(3.141592653589793 * this.phi / 180.0));
                this.r = this.R / this.max;
                this.eLabel.setText(" Eccentricity e = " + Math.round(1000.0 * this.e) / 1000.0);
                this.phiLabel.setText(" phi=" + Math.round(10.0 * this.phi) / 10.0 + '°' + " r=" + Math.round(1000.0 * this.r) / 1000.0);
                if (this.vel) {
                    this.v = Math.sqrt(this.XY(4) * this.XY(4) + this.XY(5) * this.XY(5));
                    this.vLabel.setText(" v=" + Math.round(1000.0 * this.v) / 1000.0 + this.unitStr);
                }
                if (this.pos) {
                    for (int i = 0; i <= this.valueT; ++i) {
                        this.x[i] = 0;
                        this.y[i] = 0;
                    }
                }
                if (this.ea) {
                    this.detailsLabel.setText(" phi=" + Math.round(10.0 * this.phi) / 10.0 + '°' + " E=" + this.valueT + '°' + " r=" + Math.round(1000.0 * this.r) / 1000.0);
                }
                this.repaint();
                return true;
            }
            if (evt.target == this.scrollA) {
                this.valueA = ((Scrollbar)evt.target).getValue();
                this.a = (int)this.max + (int)Math.round(this.max * (this.valueA - 45) / 90.0);
                if (this.constant == 1.0) {
                    this.str = " Semimajor axis a = " + Math.round(100 * this.a / this.max) / 100.0;
                }
                else {
                    this.str = " Semimajor axis a = " + Math.round(100 * this.a / this.max) / 100.0 + this.aStr;
                }
                if (this.moon) {
                    this.str = " Semimajor axis a = " + Math.round(this.A * (1.0 + (this.valueA - 45.0) / 90.0)) + " km";
                }
                this.aLabel.setText(this.str);
                this.b = this.a * Math.sqrt(1.0 - this.e * this.e);
                this.R = this.a * (1.0 - this.e * this.e) / (1.0 + this.e * Math.cos(3.141592653589793 * this.phi / 180.0));
                this.r = this.R / this.max;
                this.phiLabel.setText(" phi=" + Math.round(10.0 * this.phi) / 10.0 + '°' + " r=" + Math.round(1000.0 * this.r) / 1000.0);
                if (this.vel) {
                    this.v = Math.sqrt(this.XY(4) * this.XY(4) + this.XY(5) * this.XY(5));
                    this.vLabel.setText(" v=" + Math.round(1000.0 * this.v) / 1000.0 + this.unitStr);
                }
                if (this.pos) {
                    for (int i = 0; i <= this.valueT; ++i) {
                        this.x[i] = 0;
                        this.y[i] = 0;
                    }
                }
                if (this.ea) {
                    this.detailsLabel.setText(" phi=" + Math.round(10.0 * this.phi) / 10.0 + '°' + " E=" + this.valueT + '°' + " r=" + Math.round(1000.0 * this.r) / 1000.0);
                }
                this.repaint();
                return true;
            }
            if (evt.target == this.scrollPhi) {
                this.valuePhi = ((Scrollbar)evt.target).getValue();
                this.phi = this.valuePhi;
                this.b = this.a * Math.sqrt(1.0 - this.e * this.e);
                this.R = this.a * (1.0 - this.e * this.e) / (1.0 + this.e * Math.cos(3.141592653589793 * this.phi / 180.0));
                this.rBlue = this.R;
                this.r = this.R / this.max;
                this.str = " phi=" + Math.round(10.0 * this.phi) / 10.0 + '°' + " r=";
                if (this.moon) {
                    this.r = this.aMoon * (1.0 - this.e * this.e) / (1.0 + this.e * Math.cos(3.141592653589793 * this.phi / 180.0));
                    this.str = String.valueOf(this.str) + Math.round(this.r) + " km";
                }
                else {
                    this.str = String.valueOf(this.str) + Math.round(1000.0 * this.r) / 1000.0;
                }
                this.phiLabel.setText(this.str);
                this.repaint();
                return true;
            }
            if (evt.target == this.scrollT) {
                this.valueT = ((Scrollbar)evt.target).getValue();
                this.t = this.valueT;
                this.b = this.a * Math.sqrt(1.0 - this.e * this.e);
                final double PHI = this.XY(3);
                this.R = this.a * (1.0 - this.e * this.e) / (1.0 + this.e * Math.cos(3.141592653589793 * PHI / 180.0));
                this.r = this.R / this.max;
                if (this.moon) {
                    this.r = this.aMoon * (1.0 - this.e * this.e) / (1.0 + this.e * Math.cos(3.141592653589793 * PHI / 180.0));
                }
                this.tLabel.setText(" Time t/T=" + Math.round(1000.0 * this.t / 360.0) / 1000.0 + " M=" + this.t + '°');
                if (this.pos) {
                    this.x[this.valueT] = (int)Math.round(this.XY(1));
                    this.y[this.valueT] = (int)Math.round(this.XY(2));
                }
                if (this.ea) {
                    final double E = this.EA(this.t);
                    this.str = " phi=" + Math.round(10.0 * PHI) / 10.0 + '°' + " E=" + Math.round(10.0 * E) / 10.0 + '°' + " r=";
                    if (!this.moon) {
                        this.str = String.valueOf(this.str) + Math.round(1000.0 * this.r) / 1000.0;
                    }
                    else {
                        this.str = String.valueOf(this.str) + Math.round(this.r) + " km";
                    }
                    this.detailsLabel.setText(this.str);
                }
                if (this.vel) {
                    this.v = Math.sqrt(this.XY(4) * this.XY(4) + this.XY(5) * this.XY(5));
                    this.vLabel.setText(" v=" + Math.round(1000.0 * this.v) / 1000.0 + this.unitStr);
                }
                this.repaint();
                return true;
            }
        }
        return super.handleEvent(evt);
    }
    
    public void doData() {
        String str7 = "*2pi/T";
        double nenner = 1.0;
        this.str = String.valueOf(this.planetStr) + "\n";
        if (this.moon) {
            this.str = String.valueOf(this.str) + "semimajor axis  a = " + Math.round(this.aMoon) + " km" + "\n";
        }
        else {
            this.str = String.valueOf(this.str) + "semimajor axis  a = " + Math.round(1000 * this.a / this.max) / 1000.0;
        }
        if (this.constant == 1.0) {
            this.str = String.valueOf(this.str) + "\n";
        }
        else {
            this.str = String.valueOf(this.str) + this.aStr + "\n";
        }
        this.str = String.valueOf(this.str) + "eccentricity    e = " + this.e + "\n";
        if (this.moon) {
            this.str = String.valueOf(this.str) + "semiminor axis  b = " + Math.round(this.aMoon * Math.sqrt(1.0 - this.e * this.e)) + " km" + "\n";
        }
        else {
            this.str = String.valueOf(this.str) + "semiminor axis  b = " + Math.round(10000.0 * this.b / this.max) / 10000.0;
        }
        if (this.constant == 1.0) {
            this.str = String.valueOf(this.str) + "\n";
        }
        else {
            this.str = String.valueOf(this.str) + this.aStr + "\n";
        }
        final double k = this.kGauss * Math.pow(this.AU * 1000.0, 1.5) / 86400.0;
        double period = 6.283185307179586 / k * Math.pow(this.A * 1000.0, 1.5) / 86400.0;
        String str8 = " d";
        if (this.constant == 1.0) {
            period = 1.0;
            str8 = "";
            nenner = 6.283185307179586;
        }
        else {
            nenner = period * 86400.0;
            str7 = "";
        }
        if (this.moon) {
            period = 6.283185307179586 / Math.sqrt(3.9869001119999994E14) * Math.pow(this.aMoon * 1000.0, 1.5) / 86400.0;
            str8 = " d";
        }
        this.str = String.valueOf(this.str) + "orbit period    T = " + Math.round(100.0 * period) / 100.0 + str8 + "\n";
        this.str = String.valueOf(this.str) + "perihel. distance = " + Math.round(10000 * this.a * (1.0 - this.e) / this.max) / 10000.0;
        if (this.constant == 1.0) {
            this.str = String.valueOf(this.str) + "\n";
        }
        else {
            this.str = String.valueOf(this.str) + this.aStr + "\n";
        }
        this.str = String.valueOf(this.str) + "aphel. distance   = " + Math.round(10000 * this.a * (1.0 + this.e) / this.max) / 10000.0;
        if (this.constant == 1.0) {
            this.str = String.valueOf(this.str) + "\n";
        }
        else {
            this.str = String.valueOf(this.str) + this.aStr + "\n";
        }
        final double T = this.t;
        this.t = 0.0;
        double v = Math.sqrt(this.XY(4) * this.XY(4) + this.XY(5) * this.XY(5));
        this.str = String.valueOf(this.str) + "perihel. veloc.   = " + Math.round(1000.0 * v) / 1000.0 + this.unitStr + str7 + "\n";
        this.t = 180.0;
        v = Math.sqrt(this.XY(4) * this.XY(4) + this.XY(5) * this.XY(5));
        this.str = String.valueOf(this.str) + "alhel. velocity   = " + Math.round(1000.0 * v) / 1000.0 + this.unitStr + str7 + "\n";
        this.str = String.valueOf(this.str) + "mean velocity     = " + Math.round(1000.0 * this.umfang(this.e) / nenner) / 1000.0 + this.unitStr + str7 + "\n";
        if (this.constant == 1.0 || this.moon) {
            this.str = String.valueOf(this.str) + "orbit length      = " + Math.round(1000.0 * this.umfang(this.e)) / 1000.0 + "\n";
        }
        else {
            this.str = String.valueOf(this.str) + "orbit length      = " + Math.round(1000.0 * this.umfang(this.e) / this.AU) / 1000.0 + "\n";
        }
        this.str = String.valueOf(this.str) + "true anomaly      = phi\n";
        for (int i = 0; i <= 40; ++i) {
            this.t = i * 9;
            str8 = String.valueOf(Math.round(1000.0 * this.t / 360.0) / 1000.0);
            String str9 = String.valueOf(Math.round(100.0 * this.XY(1) / this.max) / 100.0);
            String str10 = String.valueOf(Math.round(100.0 * this.XY(2) / this.max) / 100.0);
            final double vx = this.XY(4);
            final double vy = this.XY(5);
            String str11 = String.valueOf(Math.round(1000.0 * Math.sqrt(vx * vx + vy * vy)) / 1000.0);
            double PHI = this.XY(3);
            String str12 = String.valueOf(Math.round(100.0 * PHI) / 100.0);
            PHI = this.XY(3);
            final double RR = this.a * (1.0 - this.e * this.e) / (1.0 + this.e * Math.cos(3.141592653589793 * PHI / 180.0)) / this.max;
            String str13 = String.valueOf(Math.round(100.0 * RR) / 100.0);
            while (str8.length() < 5) {
                str8 = String.valueOf(str8) + " ";
            }
            while (str9.length() < 6) {
                str9 = String.valueOf(str9) + " ";
            }
            while (str10.length() < 6) {
                str10 = String.valueOf(str10) + " ";
            }
            while (str11.length() < 6) {
                str11 = String.valueOf(str11) + " ";
            }
            while (str12.length() < 6) {
                str12 = String.valueOf(str12) + " ";
            }
            while (str13.length() < 6) {
                str13 = String.valueOf(str13) + " ";
            }
            this.data[i] = str8 + "      " + str12 + "    " + str13 + "    " + str9 + "    " + str10 + "    " + str11 + "\n";
        }
        this.nData = 41;
        this.t = T;
    }
    
    public double umfang(final double x) {
        double sum = 1.0;
        int nIter = 15;
        final double s = 0.0;
        if (x > 0.9) {
            nIter = 30;
        }
        for (int n = 1; n <= nIter; ++n) {
            double h = (1.0 - Math.sqrt(1.0 - this.e * this.e)) / (1.0 + Math.sqrt(1.0 - this.e * this.e));
            h *= h;
            final double C = this.bc(n) * this.bc(n);
            sum += C * Math.pow(h, n);
        }
        return sum * 3.141592653589793 * this.A * (1.0 + Math.sqrt(1.0 - this.e * this.e));
    }
    
    public double fact(final int n) {
        double result = 1.0;
        for (int i = 1; i <= n; ++i) {
            result = (result *= i);
        }
        return result;
    }
    
    public double bc(final int n) {
        double r = 0.5;
        for (int k = 1; k < n; ++k) {
            r *= 0.5 - k;
        }
        return r / this.fact(n);
    }
    
    public void paint(final Graphics g) {
        final int y0 = 390;
        final int x0 = this.size().width / 2;
        final int radius = 4;
        g.setColor(Color.gray);
        g.drawLine(x0 - this.a, y0, x0 + this.a, y0);
        g.drawLine(x0, y0 - (int)Math.round(this.b), x0, y0 + (int)Math.round(this.b));
        g.setColor(Color.black);
        g.setFont(new Font("Helvetica", 0, 10));
        g.drawString(String.valueOf(this.versStr) + " -  (c) 2006 J. Giesen - www.GeoAstro.de", 90, this.size().height - 5);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        final int d = (int)Math.round(this.e * this.a);
        g.setColor(Color.red);
        for (int i = -this.a; i <= this.a; ++i) {
            final int x2 = i;
            final int y2 = (int)Math.round(this.b * Math.sqrt(1.0 - x2 * x2 / (this.a * this.a)));
            final int x3 = i + 1;
            final int y3 = (int)Math.round(this.b * Math.sqrt(1.0 - x3 * x3 / (this.a * this.a)));
            g.drawLine(x0 + x2, y0 + y2, x0 + x3, y0 + y3);
            g.drawLine(x0 + x2, y0 - y2, x0 + x3, y0 - y3);
        }
        this.X = (int)Math.round(this.XY(1));
        this.Y = (int)Math.round(this.XY(2));
        g.setColor(Color.red);
        g.fillOval(x0 - this.X - d - radius, y0 + this.Y - radius, 2 * radius, 2 * radius);
        g.drawLine(x0 - d, y0, x0 - d - this.X, y0 + this.Y);
        if (this.showArcs) {
            g.fillArc(x0 - d - 50, y0 - 50, 100, 100, 180, (int)Math.round(this.XY(3)));
        }
        if (this.vel) {
            this.v = Math.sqrt(this.XY(4) * this.XY(4) + this.XY(5) * this.XY(5));
            final int dx = (int)Math.round(this.drawFaktor * this.XY(4));
            final int dy = (int)Math.round(this.drawFaktor * this.XY(5));
            g.drawLine(x0 - d - this.X, y0 + this.Y, x0 - d - this.X - dx, y0 + this.Y + dy);
        }
        if (this.velCircle) {
            final int dx = (int)Math.round(this.drawFaktor * this.XY(4));
            final int dy = (int)Math.round(this.drawFaktor * this.XY(5));
            final double tv = this.t;
            this.t = 0.0;
            final double vperihel = Math.sqrt(this.XY(4) * this.XY(4) + this.XY(5) * this.XY(5));
            this.t = 180.0;
            final double vaphel = Math.sqrt(this.XY(4) * this.XY(4) + this.XY(5) * this.XY(5));
            this.t = tv;
            final int rv = (int)Math.round(0.5 * this.drawFaktor * (vperihel + vaphel));
            final int yv = (int)Math.round(this.drawFaktor * vperihel - rv);
            g.setColor(Color.gray);
            g.drawOval(x0 - rv, y0 + yv - rv, 2 * rv, 2 * rv);
            g.drawLine(x0, y0 + yv, x0 - dx, y0 + dy);
            g.setColor(Color.red);
            g.drawLine(x0, y0, x0 - dx, y0 + dy);
        }
        if (this.ea) {
            g.setColor(Color.blue);
            g.drawOval(x0 - this.a, y0 - this.a, 2 * this.a, 2 * this.a);
            final double alpha = this.EA(this.t);
            this.X = (int)Math.round(this.a * Math.cos(3.141592653589793 * alpha / 180.0));
            this.Y = (int)Math.round(this.a * Math.sin(3.141592653589793 * alpha / 180.0));
            g.drawLine(x0, y0, x0 - this.X, y0 + this.Y);
            this.Y1 = (int)Math.round(this.XY(2));
            g.drawLine(x0 - this.X, y0 + this.Y, x0 - this.X, y0 + this.Y1);
            g.fillOval(x0 - this.X - radius, y0 + this.Y - radius, 2 * radius, 2 * radius);
            if (this.showArcs) {
                g.fillArc(x0 - 40, y0 - 40, 80, 80, 180, (int)Math.round(alpha));
            }
        }
        if (this.showM) {
            g.setColor(Color.blue);
            g.drawOval(x0 - this.a, y0 - this.a, 2 * this.a, 2 * this.a);
            g.setColor(Color.black);
            this.X = (int)Math.round(this.a * Math.cos(3.141592653589793 * this.t / 180.0));
            this.Y = (int)Math.round(this.a * Math.sin(3.141592653589793 * this.t / 180.0));
            g.drawLine(x0, y0, x0 - this.X, y0 + this.Y);
            g.fillOval(x0 - this.X - radius, y0 + this.Y - radius, 2 * radius, 2 * radius);
            if (this.showArcs) {
                g.fillArc(x0 - 30, y0 - 30, 60, 60, 180, (int)Math.round(this.t));
            }
        }
        if (this.phi != 0.0) {
            g.setColor(Color.red);
            this.b = this.a * Math.sqrt(1.0 - this.e * this.e);
            this.rBlue = this.a * (1.0 - this.e * this.e) / (1.0 + this.e * Math.cos(3.141592653589793 * this.phi / 180.0));
            this.X = (int)Math.round(this.rBlue * Math.cos(3.141592653589793 * this.phi / 180.0));
            this.Y = (int)Math.round(this.rBlue * Math.sin(3.141592653589793 * this.phi / 180.0));
            g.drawLine(x0 - d, y0, x0 - d - this.X, y0 + this.Y);
            g.drawOval(x0 - d - this.X - radius, y0 + this.Y - radius, 2 * radius, 2 * radius);
        }
        if (this.pos) {
            int mod = 36;
            if (this.positions == 20) {
                mod = 18;
            }
            g.setColor(Color.red);
            for (int j = 1; j <= this.valueT; ++j) {
                if (j % mod == 0 && this.x[j] != 0) {
                    g.fillOval(x0 - this.x[j] - d - 2, y0 + this.y[j] - 2, 4, 5);
                    if (this.radii) {
                        g.drawLine(x0 - d, y0, x0 - d - this.x[j], y0 + this.y[j]);
                    }
                }
            }
        }
        g.setColor(Color.black);
        g.drawLine(x0 + d, y0 - 3, x0 + d, y0 + 3);
        g.drawLine(x0 - d, y0 - 3, x0 - d, y0 + 3);
        if (this.demo) {
            g.setFont(new Font("Chicago", 0, 96));
            g.setColor(Color.red);
            g.drawString("D E M O", 30, 300);
        }
    }
    
    public ellipse093() {
        this.e = 0.5;
        this.a = 130;
        this.max = 130.0;
        this.valueA = 45;
        this.valueE = 100;
        this.valuePhi = 0;
        this.valueT = 0;
        this.phi = 0.0;
        this.t = 0.0;
        this.x = new int[370];
        this.y = new int[370];
        this.data = new String[370];
        this.nData = 0;
        this.ea = true;
        this.pos = false;
        this.vel = true;
        this.solSys = false;
        this.showM = true;
        this.showArcs = false;
        this.moon = false;
        this.radii = true;
        this.velCircle = false;
        this.positions = 10;
        this.detailStr = " Details (E, phi, r)";
        this.aStr = " AU";
        this.kGauss = 0.01720209895;
        this.AU = 1.4959787E8;
        this.aMoon = 384400.0;
        this.unitStr = "";
        this.demo = true;
        this.planetStr = "";
    }
}
