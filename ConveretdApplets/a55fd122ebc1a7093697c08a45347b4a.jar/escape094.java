import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Event;
import java.net.URL;
import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Checkbox;
import java.awt.Button;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class escape094 extends Applet
{
    Image bild;
    Image leftArrow;
    Image rightArrow;
    int x0;
    int y0;
    int w;
    int h;
    int xL;
    int xR;
    int left;
    int right;
    double K;
    int oben;
    int rechts;
    int xU;
    int yU;
    int yMax;
    int xMax;
    double r;
    double r0;
    double v;
    double v0;
    double energy;
    String s;
    int valRed;
    int valGreen;
    int valBlue;
    Button myButton;
    Checkbox box;
    boolean control;
    boolean zykloid;
    boolean parabel;
    boolean hyperbel;
    double gravitation;
    boolean tangentOK;
    String str;
    public String email;
    public String param;
    public String wwwStr;
    boolean online;
    boolean demo;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        num += 1234L;
        return (int)num;
    }
    
    public void init() {
        final URL url = this.getDocumentBase();
        this.str = url.toString();
        this.str = String.valueOf(this.str) + "1234567890123456789012345";
        this.wwwStr = this.str.substring(0, 27);
        final GridBagLayout gbl = new GridBagLayout();
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.weightx = 2.0;
        gbc.weighty = 0.0;
        gbc.insets = new Insets(480, 0, 0, 0);
        this.setLayout(gbl);
        this.setBackground(Color.white);
        this.bild = this.getImage(this.getDocumentBase(), "applet/schieber.gif");
        this.leftArrow = this.getImage(this.getDocumentBase(), "applet/left.gif");
        this.rightArrow = this.getImage(this.getDocumentBase(), "applet/right.gif");
        final String gravitationStr = this.getParameter("gravitation");
        final Double gravitationDouble = Double.valueOf(gravitationStr);
        this.gravitation = gravitationDouble;
        final String radiusStr = this.getParameter("radius");
        final Double radiusDouble = Double.valueOf(radiusStr);
        this.r0 = radiusDouble;
        this.r0 *= 1000.0;
        gbl.setConstraints(this.myButton = new Button("reset values"), gbc);
        this.add(this.myButton);
        this.box = new Checkbox("Draw tangent");
        gbc.insets = new Insets(480, 0, 0, 0);
        gbl.setConstraints(this.box, gbc);
        this.add(this.box);
        this.valGreen = (int)Math.round(Math.sqrt(this.gravitation * this.r0) / 100.0);
        this.v0 = this.valGreen * 50;
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        if (this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22)) {
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
        }
        this.repaint();
    }
    
    double sinh(final double x) {
        return 0.5 * (Math.exp(x) - Math.exp(-x));
    }
    
    double cosh(final double x) {
        return 0.5 * (Math.exp(x) + Math.exp(-x));
    }
    
    public boolean mouseDown(final Event event, final int x, final int y) {
        this.control = false;
        if (x < this.xL && y > this.y0 && y < this.y0 + this.h) {
            this.valRed = 1;
            this.repaint();
        }
        if (x > this.xR + 5 && x < this.xR + 22 && y > this.y0 && y < this.y0 + this.h) {
            this.valRed = 250;
            this.repaint();
        }
        if (x > this.xL && x < this.xR && y > this.y0 && y < this.y0 + this.h) {
            this.x0 = x - this.w / 2;
            this.valRed = x - this.xL;
            this.control = true;
            this.repaint();
        }
        if (x < this.xL && y > this.y0 + 30 && y < this.y0 + this.h + 30) {
            this.valGreen = 0;
            this.repaint();
        }
        if (x > this.xR + 5 && x < this.xR + 22 && y > this.y0 + 30 && y < this.y0 + this.h + 30) {
            this.valGreen = 250;
            this.repaint();
        }
        if (x > this.xL && x < this.xR && y > this.y0 + 30 && y < this.y0 + this.h + 30) {
            this.x0 = x - this.w / 2;
            this.valGreen = x - this.xL - 1;
            this.control = true;
            this.repaint();
        }
        if (x < this.xL && y > this.y0 + 60 && y < this.y0 + this.h + 60) {
            this.valBlue = 0;
            this.repaint();
        }
        if (x > this.xR + 5 && x < this.xR + 22 && y > this.y0 + 60 && y < this.y0 + this.h + 60) {
            this.valBlue = 250;
            this.repaint();
        }
        if (x > this.xL && x < this.xR && y > this.y0 + 60 && y < this.y0 + this.h + 60) {
            this.x0 = x - this.w / 2;
            this.valBlue = x - this.xL - 1;
            this.control = true;
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int x, final int y) {
        if (x > this.xL && x < this.xR && y > this.y0 && y < this.y0 + this.h && this.control) {
            this.x0 = x - this.w / 2;
            this.valRed = x - this.xL;
            this.repaint();
        }
        if (x > this.xL && x < this.xR && y > this.y0 + 30 && y < this.y0 + this.h + 30 && this.control) {
            this.x0 = x - this.w / 2;
            this.valGreen = x - this.xL - 1;
            this.control = true;
            this.repaint();
        }
        if (x > this.xL && x < this.xR && y > this.y0 + 60 && y < this.y0 + this.h + 60 && this.control) {
            this.x0 = x - this.w / 2;
            this.valBlue = x - this.xL - 1;
            this.repaint();
        }
        return true;
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof Button) {
            this.valRed = 125;
            this.valGreen = 100;
            this.valGreen = (int)Math.round(Math.sqrt(this.gravitation * this.r0) / 100.0);
            this.valBlue = 0;
            this.repaint();
            return true;
        }
        if (event.target instanceof Checkbox && event.target == this.box) {
            if (this.box.getState()) {
                this.tangentOK = true;
            }
            else {
                this.tangentOK = false;
            }
            this.repaint();
            return true;
        }
        return true;
    }
    
    public void paint(final Graphics g) {
        g.setColor(Color.red);
        Font f = new Font("Helvetica", 0, 9);
        g.setColor(Color.black);
        g.setFont(f);
        g.drawString("Cosmic Applet 0.94   © 2000-2007 J. Giesen - www.GeoAstro.de", 140, this.size().height - 5);
        f = new Font("Helvetica", 0, 10);
        g.setFont(f);
        g.setColor(Color.black);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        g.drawString("g = " + this.gravitation + " m/(s*s)", this.rechts + 130, this.oben + 30);
        g.drawString("R = " + this.r0 / 1000.0 + " km", this.rechts + 130, this.oben + 15);
        g.setColor(Color.red);
        g.fillRect(this.xL, 17, 250, 10);
        g.drawImage(this.leftArrow, this.xL - 17, this.y0 + 5, this);
        g.drawImage(this.rightArrow, this.xL + 256, this.y0 + 5, this);
        g.drawImage(this.bild, this.valRed + this.xL - 5, this.y0, this);
        g.drawString("Resize axes", 300, 25);
        g.setColor(Color.blue);
        g.drawString("Drag blue bar  to see current values.", 300, 85);
        g.setColor(Color.green);
        g.fillRect(this.xL, 47, 250, 10);
        g.drawImage(this.leftArrow, this.xL - 17, this.y0 + 35, this);
        g.drawImage(this.rightArrow, this.xL + 256, this.y0 + 35, this);
        g.drawImage(this.bild, this.valGreen + this.xL - 5, this.y0 + 30, this);
        g.setColor(Color.blue);
        g.fillRect(this.xL, 77, 250, 10);
        g.drawImage(this.leftArrow, this.xL - 17, this.y0 + 65, this);
        g.drawImage(this.rightArrow, this.xL + 256, this.y0 + 65, this);
        g.drawImage(this.bild, this.valBlue + this.xL - 5, this.y0 + 60, this);
        if (this.valBlue == 0) {
            g.drawString("Drag blue bar  to see current values.", 40, this.oben);
        }
        else {
            g.drawString("Current values:", 60, this.oben);
        }
        g.setColor(Color.white);
        g.drawLine(this.xL + 62, 15, this.xL + 62, 28);
        g.drawLine(this.xL + 125, 15, this.xL + 125, 28);
        g.drawLine(this.xL + 187, 15, this.xL + 187, 28);
        g.drawLine(this.xL + 50, 45, this.xL + 50, 58);
        g.drawLine(this.xL + 100, 45, this.xL + 100, 58);
        g.drawLine(this.xL + 150, 45, this.xL + 150, 58);
        g.drawLine(this.xL + 200, 45, this.xL + 200, 58);
        g.setColor(Color.black);
        f = new Font("Helvetica", 0, 9);
        g.setFont(f);
        g.drawString("0", this.xL - 2, 70);
        g.drawString("5", this.xL + 98, 70);
        g.drawString("10", this.xL + 195, 70);
        g.drawString("0", this.xL + 124, this.y0 + 30);
        g.drawString("+", this.xL + 240, this.y0 + 30);
        g.drawString("-", this.xL + 10, this.y0 + 30);
        f = new Font("Helvetica", 0, 10);
        g.setFont(f);
        g.setColor(Color.white);
        g.drawLine(this.xL + 50, 75, this.xL + 50, 88);
        g.drawLine(this.xL + 100, 75, this.xL + 100, 88);
        g.drawLine(this.xL + 150, 75, this.xL + 150, 88);
        g.drawLine(this.xL + 200, 75, this.xL + 200, 88);
        g.setColor(Color.black);
        g.drawLine(this.xU, this.yU, this.xU + this.right + 10, this.yU);
        g.drawLine(this.xU, this.yU, this.xU, this.yU - this.yMax);
        g.drawString("Time t", this.xU + this.right - 70, this.yU - 10);
        g.drawString("Distance", this.xU + 10, this.yU - this.yMax + 10);
        g.drawString("r / R", this.xU + 15, this.yU - this.yMax + 25);
        g.setColor(Color.red);
        g.drawString("Velocity", this.xU + this.right + 5, this.yU - this.yMax + 10);
        g.drawString("v", this.xU + this.right + 20, this.yU - this.yMax + 25);
        g.drawLine(this.xU + this.right, this.yU, this.xU + this.right, this.yU - this.yMax);
        g.setColor(Color.black);
        this.v0 = this.valGreen * 50.0;
        double skala = 1.0;
        double F = 1.0;
        this.energy = this.v0 * this.v0 / 2.0 - this.gravitation * this.r0;
        double T = 1.0;
        double a = 1.0;
        if (this.v0 < Math.sqrt(2.0 * this.gravitation * this.r0) + 300.0) {
            this.zykloid = true;
            this.parabel = false;
            this.hyperbel = false;
            skala = 0.0064 * (this.valRed / 10.0) * (this.valRed / 10.0) + 0.001;
            F = Math.sqrt(2.0 * Math.abs(this.energy));
            a = this.gravitation * this.r0 * this.r0 / Math.abs(this.energy);
        }
        if (this.v0 > Math.sqrt(2.0 * this.gravitation * this.r0) + 200.0) {
            this.zykloid = false;
            this.parabel = false;
            this.hyperbel = true;
        }
        if (Math.abs(this.v0 - Math.sqrt(2.0 * this.gravitation * this.r0)) < 200.0) {
            this.zykloid = false;
            this.parabel = true;
            this.hyperbel = false;
        }
        T = 6.283185307179586 * this.gravitation * this.r0 * this.r0 * Math.sqrt(1.0 / Math.abs(8.0 * this.energy * this.energy * this.energy));
        g.setColor(Color.black);
        final double vv = this.v0 / 1000.0;
        this.s = String.valueOf(Math.round(10000.0 * vv) / 10000.0);
        g.drawString("v0 = " + this.s + " km/s", 300, 55);
        this.s = String.valueOf(Math.round(10000.0 * this.energy / 1.0E7) / 1000.0);
        g.drawString("Energy W / m = " + this.s + " MJ / kg", 375, 55);
        int xInt = (int)Math.round(Math.sqrt(2.0 * this.gravitation * this.r0) / 50.0);
        g.drawLine(this.xL + xInt, 45, this.xL + xInt, 58);
        xInt = (int)Math.round(Math.sqrt(this.gravitation * this.r0) / 50.0);
        g.drawLine(this.xL + xInt, 45, this.xL + xInt, 58);
        if (this.zykloid) {
            this.s = String.valueOf(Math.round(1000.0 * a / this.r0) / 1000.0);
            g.drawString("rMax / R = " + this.s, this.rechts, this.oben + 15);
            this.s = String.valueOf(Math.round(1000.0 * (a - this.r0) / 1000.0) / 1000.0);
            g.drawString("hMax = " + this.s + " km", this.rechts, this.oben);
            this.s = String.valueOf(Math.round(10.0 * T) / 10.0);
            g.drawString("T = " + this.s + " s", this.rechts, this.oben + 30);
        }
        double radius = a * 60.0 / this.r0;
        radius *= skala;
        a *= skala;
        final double t = this.right / (6.283185307179586 * radius);
        final double RADIUS = 2.0 * skala * radius / (a / this.r0);
        final int erdRadius = (int)Math.round(RADIUS);
        if (this.zykloid) {
            g.drawString("1", this.xU - 10, this.yU - erdRadius + 5);
            g.setColor(Color.black);
            g.drawLine(this.xU, this.yU - erdRadius, this.xU + this.right, this.yU - erdRadius);
            g.setColor(Color.red);
            g.drawString("v0", this.xU + this.right + 3, this.yU - erdRadius + 5);
            g.setColor(Color.green);
            final double nLines = this.yMax / RADIUS;
            if (nLines > 1.25 && nLines <= 3.0) {
                if (2 * erdRadius <= this.yMax) {
                    g.drawLine(this.xU, this.yU - 2 * erdRadius, this.xU + this.right, this.yU - 2 * erdRadius);
                    g.drawString("2", this.xU - 10, this.yU - 2 * erdRadius + 5);
                }
                if (3 * erdRadius < this.yMax) {
                    g.drawLine(this.xU, this.yU - 3 * erdRadius, this.xU + this.right, this.yU - 3 * erdRadius);
                    g.drawString("3", this.xU - 10, this.yU - 3 * erdRadius + 5);
                }
            }
            if (nLines > 3.0 && nLines <= 4.0) {
                g.drawLine(this.xU, this.yU - 2 * erdRadius, this.xU + this.right, this.yU - 2 * erdRadius);
                g.drawString("2", this.xU - 10, this.yU - 2 * erdRadius + 5);
                if (4 * erdRadius < this.yMax) {
                    g.drawLine(this.xU, this.yU - 4 * erdRadius, this.xU + this.right, this.yU - 4 * erdRadius);
                    g.drawString("4", this.xU - 10, this.yU - 4 * erdRadius + 5);
                }
            }
            if (nLines > 4.0 && nLines <= 6.0) {
                g.drawLine(this.xU, this.yU - 2 * erdRadius, this.xU + this.right, this.yU - 2 * erdRadius);
                g.drawString("2", this.xU - 10, this.yU - 2 * erdRadius + 5);
                g.drawLine(this.xU, this.yU - 3 * erdRadius, this.xU + this.right, this.yU - 3 * erdRadius);
                g.drawString("3", this.xU - 10, this.yU - 3 * erdRadius + 5);
                if (5 * erdRadius < this.yMax) {
                    g.drawLine(this.xU, this.yU - 5 * erdRadius, this.xU + this.right, this.yU - 5 * erdRadius);
                    g.drawString("5", this.xU - 10, this.yU - 5 * erdRadius + 5);
                }
            }
            if (nLines > 6.0 && nLines <= 8.0) {
                g.drawLine(this.xU, this.yU - 5 * erdRadius, this.xU + this.right, this.yU - 5 * erdRadius);
                g.drawString("5", this.xU - 10, this.yU - 5 * erdRadius + 5);
                if (10 * erdRadius < this.yMax) {
                    g.drawLine(this.xU, this.yU - 10 * erdRadius, this.xU + this.right, this.yU - 10 * erdRadius);
                    g.drawString("10", this.xU - 10, this.yU - 10 * erdRadius + 5);
                }
            }
            if (nLines >= 8.0 && nLines < 10.0) {
                g.drawLine(this.xU, this.yU - 5 * erdRadius, this.xU + this.right, this.yU - 5 * erdRadius);
                g.drawString("5", this.xU - 10, this.yU - 5 * erdRadius + 5);
                g.drawLine(this.xU, this.yU - 10 * erdRadius, this.xU + this.right, this.yU - 10 * erdRadius);
                g.drawString("10", this.xU - 15, this.yU - 10 * erdRadius + 5);
            }
            if (nLines >= 10.0 && nLines < 50.0) {
                g.drawLine(this.xU, this.yU - 5 * erdRadius, this.xU + this.right, this.yU - 5 * erdRadius);
                g.drawString("5", this.xU - 10, this.yU - 5 * erdRadius + 5);
                g.drawLine(this.xU, this.yU - 10 * erdRadius, this.xU + this.right, this.yU - 10 * erdRadius);
                g.drawString("10", this.xU - 15, this.yU - 10 * erdRadius + 5);
                if (15 * erdRadius < this.yMax) {
                    g.drawLine(this.xU, this.yU - 15 * erdRadius, this.xU + this.right, this.yU - 15 * erdRadius);
                    g.drawString("15", this.xU - 15, this.yU - 15 * erdRadius + 5);
                }
            }
            if (nLines > 50.0) {
                g.drawLine(this.xU, this.yU - 10 * erdRadius, this.xU + this.right, this.yU - 10 * erdRadius);
                g.drawString("10", this.xU - 15, this.yU - 10 * erdRadius + 5);
                g.drawLine(this.xU, this.yU - 50 * erdRadius, this.xU + this.right, this.yU - 50 * erdRadius);
                g.drawString("50", this.xU - 15, this.yU - 50 * erdRadius + 5);
            }
            double alpha1 = Math.acos(1.0 - RADIUS / radius) / this.K;
            double x = radius * (this.K * alpha1 - Math.sin(this.K * alpha1));
            g.setColor(Color.red);
            g.drawLine(this.xU + (int)Math.round(x), this.yU - 5, this.xU + (int)Math.round(x), this.yU + 2);
            g.drawLine(this.xU + (int)Math.round(6.283185307179586 * radius - x), this.yU - 5, this.xU + (int)Math.round(6.283185307179586 * radius - x), this.yU + 2);
            g.drawString("t0", this.xU + (int)Math.round(x) - 5, this.yU - 10);
            x = 2.0 * radius * 3.141592653589793;
            g.drawLine(this.xU + (int)Math.round(x), this.yU - 5, this.xU + (int)Math.round(x), this.yU + 2);
            g.drawString("T", this.xU + (int)Math.round(x) - 2, this.yU + 13);
            g.setColor(Color.black);
            x = radius * (this.K * alpha1 - Math.sin(this.K * alpha1));
            final double neuesT1 = T * x * t / this.right;
            this.s = String.valueOf(Math.round(10.0 * neuesT1) / 10.0);
            g.drawString("t0 = " + this.s + " s", this.rechts, this.oben + 45);
            final double zeit = T - 2.0 * neuesT1;
            this.s = String.valueOf(Math.round(100.0 * zeit) / 100.0);
            g.drawString("tau = " + this.s + " s", this.rechts, this.oben + 60);
            if (this.valBlue > 0) {
                final int radiusInt = (int)Math.round(radius);
                final double alpha2 = 0.025132741228718346 * this.valBlue;
                double y = radius * (1.0 - Math.cos(alpha2));
                final int yInt = (int)Math.round(y);
                x = radius * (alpha2 - Math.sin(alpha2));
                xInt = (int)Math.round(x);
                g.setColor(Color.blue);
                g.fillOval(this.xU + xInt - 3, this.yU - yInt - 3, 6, 6);
                g.drawLine(this.xU, this.yU - yInt, this.xU + 5, this.yU - yInt);
                g.drawLine(this.xU + xInt, this.yU, this.xU + xInt, this.yU - 5);
                g.setColor(Color.black);
                final double neueZeit = T * x * t / this.right;
                this.s = String.valueOf(Math.round(10.0 * neueZeit) / 10.0);
                g.drawString("t = " + this.s + " s", 60, this.oben + 15);
                this.s = String.valueOf(Math.round(10.0 * (neueZeit - neuesT1)) / 10.0);
                g.drawString("t - t0 = " + this.s + " s", 60, this.oben + 30);
                final int xMitte = (int)Math.round(radius * alpha2);
                g.setColor(Color.blue);
                g.fillOval(this.xU + xMitte - 3, this.yU - radiusInt - 3, 6, 6);
                g.drawOval(this.xU + xMitte - radiusInt, this.yU - 2 * radiusInt, 2 * radiusInt, 2 * radiusInt);
                g.drawLine(this.xU + xMitte, this.yU - radiusInt, this.xU + xInt, this.yU - yInt);
                this.v = F * Math.sin(alpha2) / (1.0 - Math.cos(alpha2));
                if (this.tangentOK) {
                    y = radius * (1.0 - Math.cos(alpha2));
                    alpha1 = 0.025132741228718346 * (this.valBlue + 4);
                    double y2 = radius * (1.0 - Math.cos(alpha1));
                    double x2 = radius * (alpha1 - Math.sin(alpha1));
                    double steig = -100.0 * (y2 - y) / (x - x2);
                    alpha1 = 0.025132741228718346 * (this.valBlue - 4);
                    y2 = radius * (1.0 - Math.cos(alpha1));
                    x2 = radius * (alpha1 - Math.sin(alpha1));
                    steig = 0.5 * (steig - 100.0 * (y2 - y) / (x - x2));
                    final int ySteig = (int)Math.round(steig);
                    if (this.valBlue > 4 && this.valBlue < 246) {
                        g.drawLine(this.xU + xInt, this.yU - yInt, this.xU + xInt + 100, this.yU - yInt - ySteig);
                        g.drawLine(this.xU + xInt, this.yU - yInt, this.xU + xInt - 100, this.yU - yInt + ySteig);
                    }
                }
                final int vInt = (int)Math.round(RADIUS * Math.abs(this.v) / this.v0);
                g.setColor(Color.red);
                g.fillOval(this.xU + xInt - 3, this.yU - vInt - 3, 6, 6);
                g.drawLine(this.xU + this.right, this.yU - vInt, this.xU + this.right - 5, this.yU - vInt);
                g.setColor(Color.black);
                this.s = String.valueOf(Math.round(this.v) / 1000.0);
                g.drawString("v = " + this.s + " km/s", 60, this.oben + 45);
                y = this.r0 * (radius * (1.0 - Math.cos(alpha2)) / RADIUS - 1.0) / 1000.0;
                this.s = String.valueOf(Math.round(1000.0 * y) / 1000.0);
                g.drawString("h = " + this.s + " km", 60, this.oben + 60);
            }
            for (int i = 0; i < this.right; ++i) {
                final double winkel = this.K * i * 360.0 / this.right;
                x = radius * (winkel - Math.sin(winkel));
                xInt = (int)Math.round(x);
                final double y = radius * (1.0 - Math.cos(winkel));
                final int yInt = (int)Math.round(y);
                g.setColor(Color.black);
                if (yInt < this.yMax) {
                    g.drawLine(this.xU + xInt, this.yU - yInt, this.xU + xInt, this.yU - yInt);
                }
                this.v = F * Math.sin(winkel) / (1.0 - Math.cos(winkel));
                final int yvInt = (int)Math.round(RADIUS * Math.abs(this.v) / this.v0);
                g.setColor(Color.red);
                if (yvInt < this.yMax) {
                    g.drawLine(this.xU + xInt, this.yU - yvInt, this.xU + xInt, this.yU - yvInt);
                }
            }
        }
        if (this.parabel) {
            final double konst = this.gravitation * this.r0 * this.r0;
            final double p = Math.exp(Math.log(9.0 * konst / 2.0) / 3.0);
            skala *= 10.0;
            final int PP = 60;
            g.drawLine(this.xU, this.yU - PP, this.xU + this.right, this.yU - PP);
            g.setColor(Color.red);
            g.drawString("v0", this.xU + this.right + 3, this.yU - PP);
            g.setColor(Color.black);
            g.drawString("1", this.xU - 8, this.yU - PP);
            g.setColor(Color.green);
            g.drawLine(this.xU, this.yU - 2 * PP, this.xU + this.right, this.yU - 2 * PP);
            g.drawLine(this.xU, this.yU - 3 * PP, this.xU + this.right, this.yU - 3 * PP);
            g.drawLine(this.xU, this.yU - 4 * PP, this.xU + this.right, this.yU - 4 * PP);
            g.drawLine(this.xU, this.yU - 5 * PP, this.xU + this.right, this.yU - 5 * PP);
            g.drawString("3", this.xU - 8, this.yU - 3 * PP);
            g.drawString("5", this.xU - 8, this.yU - 5 * PP);
            g.setColor(Color.black);
            double x = this.valBlue * this.right / 250.0;
            double y = p * Math.exp(2.0 * Math.log(x * skala) / 3.0);
            xInt = (int)Math.round(x);
            int yInt = (int)Math.round(y * PP / this.r0);
            this.v0 = Math.sqrt(2.0 * this.gravitation * this.r0);
            g.setColor(Color.blue);
            g.fillOval(this.xU + xInt - 3, this.yU - yInt - 3, 6, 6);
            g.drawLine(this.xU, this.yU - yInt, this.xU + 5, this.yU - yInt);
            g.drawLine(this.xU + xInt, this.yU, this.xU + xInt, this.yU - 5);
            this.v = Math.sqrt(2.0 * konst / p) * Math.exp(-Math.log(x * skala) / 3.0);
            int vInt = (int)Math.round(PP * this.v / this.v0);
            g.setColor(Color.red);
            g.fillOval(this.xU + xInt - 3, this.yU - vInt - 3, 6, 6);
            g.drawLine(this.xU + this.right, this.yU - vInt, this.xU + this.right - 5, this.yU - vInt);
            g.setColor(Color.black);
            this.v /= 1000.0;
            this.s = String.valueOf(Math.round(1000.0 * this.v) / 1000.0);
            if (this.valBlue == 0) {
                g.drawString("v = inf", 60, this.oben + 15);
            }
            else {
                g.drawString("v = " + this.s + " km/s", 60, this.oben + 15);
            }
            final double TT = x * skala;
            this.s = String.valueOf(Math.round(10.0 * TT) / 10.0);
            if (this.valBlue > 0) {
                g.drawString("t= " + this.s + " s", 60, this.oben + 30);
            }
            double r = y;
            r /= 1000.0;
            this.s = String.valueOf(Math.round(10.0 * r) / 10.0);
            if (this.valBlue > 0) {
                g.drawString("r = " + this.s + " km", 60, this.oben + 45);
            }
            x = Math.exp(3.0 * Math.log(this.r0 / p) / 2.0) / skala;
            g.setColor(Color.red);
            g.drawLine(this.xU + (int)Math.round(x), this.yU - 5, this.xU + (int)Math.round(x), this.yU + 2);
            g.drawString("t0", this.xU + (int)Math.round(x) - 5, this.yU - 10);
            this.s = String.valueOf(Math.round(10.0 * x * skala) / 10.0);
            if (this.valBlue > 0) {
                g.drawString("t0 = " + this.s + "s", 60, this.oben + 60);
            }
            if (this.tangentOK) {
                x = this.right * this.valBlue / 250.0;
                y = p * Math.exp(2.0 * Math.log(x * skala) / 3.0);
                y = PP * y / this.r0;
                double x2 = this.right * (this.valBlue + 5) / 250.0;
                double y2 = p * Math.exp(2.0 * Math.log(x2 * skala) / 3.0);
                y2 = PP * y2 / this.r0;
                double steig2 = -100.0 * (y2 - y) / (x - x2);
                x2 = this.right * (this.valBlue - 5) / 250.0;
                y2 = p * Math.exp(2.0 * Math.log(x2 * skala) / 3.0);
                y2 = PP * y2 / this.r0;
                steig2 = 0.5 * (steig2 - 100.0 * (y2 - y) / (x - x2));
                final int ySteig2 = (int)Math.round(steig2);
                g.setColor(Color.blue);
                if (this.valBlue > 4 && this.valBlue < 246) {
                    g.drawLine(this.xU + xInt, this.yU - yInt, this.xU + xInt + 100, this.yU - yInt - ySteig2);
                    g.drawLine(this.xU + xInt, this.yU - yInt, this.xU + xInt - 100, this.yU - yInt + ySteig2);
                }
            }
            for (int j = 0; j < this.right; ++j) {
                y = p * Math.exp(2.0 * Math.log(j * skala) / 3.0);
                y = PP * y / this.r0;
                yInt = (int)Math.round(y);
                xInt = Math.round(j);
                if (yInt < this.yMax) {
                    g.drawLine(this.xU + xInt, this.yU - yInt, this.xU + xInt, this.yU - yInt);
                }
                this.v = Math.sqrt(2.0 * konst / p) * Math.exp(-Math.log(j * skala) / 3.0);
                vInt = (int)Math.round(PP * this.v / this.v0);
                g.setColor(Color.red);
                if (vInt < this.yMax) {
                    g.drawLine(this.xU + xInt, this.yU - vInt, this.xU + xInt, this.yU - vInt);
                }
                g.setColor(Color.black);
            }
        }
        if (this.hyperbel) {
            g.drawString("At Work.", 150, 250);
            skala = 0.0064 * (this.valRed / 10.0) * (this.valRed / 10.0) + 0.001;
            skala = 10.0 * Math.exp(skala);
            this.v0 = this.valGreen * 50.0;
            if (this.valBlue > 0) {
                this.v0 = this.valGreen * 50.0;
                final double xx = this.valBlue / 100.0;
                final double x = skala * (this.sinh(xx) - xx);
                xInt = (int)Math.round(x);
                final double y = 0.5 * skala * (this.cosh(xx) - 1.0);
                final int yInt = (int)Math.round(y);
                this.v = 0.5 * (Math.exp(xx) - Math.exp(-xx)) / (0.5 * (Math.exp(xx) + Math.exp(-xx)) - 1.0);
                this.s = String.valueOf(Math.round(this.v * this.v0) / 1000.0);
                g.drawString("v = " + this.s + " km/s", 60, this.oben + 45);
                g.setColor(Color.red);
                final int vInt = (int)Math.round(skala * this.v);
                g.fillOval(this.xU + xInt - 3, this.yU - vInt - 3, 6, 6);
                g.drawLine(this.xU + this.right, this.yU - vInt, this.xU + this.right - 5, this.yU - vInt);
                g.setColor(Color.blue);
                g.fillOval(this.xU + xInt - 3, this.yU - yInt - 3, 6, 6);
                g.drawLine(this.xU, this.yU - yInt, this.xU + 5, this.yU - yInt);
                g.drawLine(this.xU + xInt, this.yU, this.xU + xInt, this.yU - 5);
                if (this.tangentOK) {
                    double x2 = xx + 0.05;
                    double y2 = 0.5 * skala * (this.cosh(x2) - 1.0);
                    x2 = skala * (this.sinh(x2) - x2);
                    double steig3 = -100.0 * (y2 - y) / (x - x2);
                    x2 = xx - 0.05;
                    y2 = 0.5 * skala * (this.cosh(x2) - 1.0);
                    x2 = skala * (this.sinh(x2) - x2);
                    steig3 = 0.5 * (steig3 - 100.0 * (y2 - y) / (x - x2));
                    final int ySteig3 = (int)Math.round(steig3);
                    g.setColor(Color.blue);
                    if (this.valBlue > 4) {
                        g.drawLine(this.xU + xInt, this.yU - yInt, this.xU + xInt + 100, this.yU - yInt - ySteig3);
                        g.drawLine(this.xU + xInt, this.yU - yInt, this.xU + xInt - 100, this.yU - yInt + ySteig3);
                    }
                }
            }
            for (int k = 0; k < this.right; ++k) {
                final double xx = k / 100.0;
                final double y = 0.5 * skala * (this.cosh(xx) - 1.0);
                final double x = skala * (this.sinh(xx) - xx);
                final int yInt = (int)Math.round(y);
                xInt = (int)Math.round(x);
                g.setColor(Color.black);
                if (yInt < this.yMax && xInt < this.right) {
                    g.drawLine(this.xU + xInt, this.yU - yInt, this.xU + xInt, this.yU - yInt);
                }
                g.setColor(Color.red);
                this.v = 0.5 * (Math.exp(xx) - Math.exp(-xx)) / (0.5 * (Math.exp(xx) + Math.exp(-xx)) - 1.0);
                final int vInt = (int)Math.round(skala * this.v);
                if (vInt < this.yMax && xInt < this.right) {
                    g.drawLine(this.xU + xInt, this.yU - vInt, this.xU + xInt, this.yU - vInt);
                }
            }
        }
        if (this.demo) {
            f = g.getFont();
            g.setColor(Color.red);
            g.setFont(new Font("Chicago", 0, 128));
            g.drawString("D E M O", 20, 280);
            g.setFont(f);
        }
    }
    
    public escape094() {
        this.x0 = 10;
        this.y0 = 10;
        this.w = 10;
        this.h = 25;
        this.xL = 20;
        this.xR = 272;
        this.left = 38;
        this.right = 460;
        this.K = 0.017453292519943295;
        this.oben = 430;
        this.rechts = 220;
        this.xU = 20;
        this.yU = 415;
        this.yMax = 300;
        this.xMax = 300;
        this.s = "";
        this.valRed = 125;
        this.valBlue = 0;
        this.control = false;
        this.zykloid = false;
        this.parabel = false;
        this.hyperbel = false;
        this.tangentOK = false;
        this.str = "";
        this.online = false;
        this.demo = true;
    }
}
