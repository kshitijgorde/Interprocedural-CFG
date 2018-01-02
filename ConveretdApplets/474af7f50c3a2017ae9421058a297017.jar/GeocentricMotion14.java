import java.awt.Event;
import java.awt.Font;
import java.util.Map;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Panel;
import java.awt.Color;
import java.awt.Choice;
import java.awt.Button;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class GeocentricMotion14 extends Applet implements Runnable
{
    Thread myThread;
    String versStr;
    Button buttonMinus;
    Button buttonPlus;
    Button buttonFirst;
    Button buttonRunStop;
    Choice planetChoice;
    Choice speedChoice;
    Choice timeChoice;
    Choice sizeChoice;
    String str;
    int left;
    double intervall;
    int days;
    double sidYear;
    double tropYear;
    double K;
    boolean running;
    int delay;
    int SE;
    int SV;
    int xSun;
    int ySun;
    int dimension;
    int xEarth;
    int yEarth;
    int[] XX;
    int[] YY;
    double faktor;
    String planetStr;
    double orbitRadius;
    int planetRadius;
    int earthRadius;
    double tPlanet;
    double winkel;
    String myStr;
    boolean demo;
    boolean online;
    public String email;
    public String param;
    public String wwwStr;
    public String usrStr;
    public String userString;
    double alpha;
    double alphaEarth;
    boolean step;
    double tPlanetDays;
    double synodic;
    int xx;
    int yy;
    int xxE;
    int yyE;
    int xZ;
    int yZ;
    double x;
    double y;
    double X;
    double Y;
    double xE;
    double yE;
    double EP;
    double elongAngle;
    int H;
    double rPlanet;
    final int AU = 149597870;
    boolean epi;
    double r;
    int R;
    int dX;
    int dY;
    Choice detailChoice;
    boolean trace;
    double w;
    double w1;
    int W;
    int xEpi;
    int yEpi;
    int R1;
    boolean retro;
    double x1;
    double y1;
    double xE1;
    double yE1;
    int se;
    int sv;
    boolean conj;
    boolean oppos;
    double Phi;
    double Phi1;
    double e;
    int xHA;
    boolean phase;
    double DX;
    double DX1;
    double DY;
    double DY1;
    int mySize;
    
    public int formula(final String s, final int n) {
        long n2 = 0L;
        for (int i = 0; i < n; ++i) {
            final char char1 = s.charAt(i);
            final long n3 = i * Character.digit(char1, i) * Character.digit(char1, 36 - i);
            final long n4 = Character.digit(char1, 36 - i);
            n2 += n4 * n4;
        }
        return (int)n2 + 13572;
    }
    
    public void start() {
        (this.myThread = new Thread(this)).start();
    }
    
    public void stop() {
        this.myThread.stop();
    }
    
    public void run() {
        while (this.running) {
            try {
                this.repaint();
                this.alpha += 1.0 / this.tPlanet;
                this.alpha %= this.dimension;
                ++this.alphaEarth;
                this.alphaEarth %= this.dimension;
                this.days = (int)Math.round(this.alphaEarth * this.tropYear / 360.0);
            }
            catch (Exception ex) {}
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void init() {
        this.H = this.size().height;
        this.setBackground(Color.white);
        this.SE = 120;
        this.SV = (int)Math.round(0.723 * this.SE);
        this.se = (int)Math.round(this.faktor * this.SE);
        this.sv = (int)Math.round(this.faktor * this.SV);
        this.planetStr = "Venus";
        this.orbitRadius = 0.723;
        this.tPlanetDays = 224.70069;
        this.tPlanet = this.tPlanetDays / this.tropYear;
        this.synodic = 1.0 / this.sidYear - 1.0 / this.tPlanetDays;
        this.synodic = -1.0 / this.synodic;
        this.rPlanet = 6051.8;
        this.intervall = 30.0;
        this.alpha = 0.0;
        this.alphaEarth = 0.0;
        this.winkel = 0.0;
        this.days = 0;
        this.myStr = this.getDocumentBase().toString();
        this.myStr = String.valueOf(this.myStr) + "1234567890123456789012345";
        this.wwwStr = this.myStr.substring(0, 27);
        final Panel panel = new Panel();
        this.add("Buttons", panel);
        (this.planetChoice = new Choice()).addItem("Mercury");
        this.planetChoice.addItem("Venus");
        this.planetChoice.addItem("Mars");
        this.planetChoice.addItem("Jupiter");
        this.planetChoice.addItem("Saturn");
        this.planetChoice.select("Venus");
        panel.add(this.planetChoice);
        (this.buttonFirst = new Button()).setLabel("|<");
        panel.add(this.buttonFirst);
        (this.buttonMinus = new Button()).setLabel("<");
        panel.add(this.buttonMinus);
        (this.timeChoice = new Choice()).addItem("day");
        this.timeChoice.addItem("week");
        this.timeChoice.addItem("month");
        this.timeChoice.addItem("year");
        this.timeChoice.addItem("synod");
        this.timeChoice.select("month");
        panel.add(this.timeChoice);
        (this.buttonPlus = new Button()).setLabel(">");
        panel.add(this.buttonPlus);
        (this.buttonRunStop = new Button()).setLabel("Run/Stop");
        panel.add(this.buttonRunStop);
        (this.speedChoice = new Choice()).addItem("speed 1");
        this.speedChoice.addItem("speed 2");
        this.speedChoice.addItem("speed 3");
        this.speedChoice.addItem("speed 4");
        this.speedChoice.addItem("speed 5");
        this.speedChoice.addItem("speed 6");
        this.speedChoice.select("speed 4");
        panel.add(this.speedChoice);
        (this.sizeChoice = new Choice()).addItem("Full Size");
        this.sizeChoice.addItem("0.75 Size");
        this.sizeChoice.addItem("0.50 Size");
        panel.add(this.sizeChoice);
        (this.detailChoice = new Choice()).addItem("Details");
        this.detailChoice.addItem("Epicycle on/off");
        this.detailChoice.addItem("Trace on/off");
        this.detailChoice.addItem("Retro on/off");
        this.detailChoice.addItem("Phase on/off");
        this.detailChoice.addItem("Inf. Conjunction");
        this.detailChoice.addItem("Sup. Conjunction");
        panel.add(this.detailChoice);
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        this.usrStr = this.email;
        this.userString = this.email;
        boolean b;
        if (this.formula(this.wwwStr, 18) == this.formula("http://www.tyge.de", 18) || this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 22) == this.formula("http://www.j-giesen.de", 22) || this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(this.wwwStr, 24) == this.formula("http://www.venus-transit", 24)) {
            b = true;
            this.online = true;
            this.demo = false;
        }
        else {
            b = false;
        }
        if (!b) {
            if (this.email.length() != 0 && Integer.parseInt(this.param) == this.formula(this.email, this.email.length())) {
                this.demo = false;
            }
            if (this.wwwStr.substring(0, 7).equals("http://")) {
                this.demo = true;
            }
        }
        if (this.demo) {
            this.versStr = String.valueOf(this.versStr) + " DEMO";
        }
    }
    
    public void paint(final Graphics graphics) {
        final RenderingHints renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        ((Graphics2D)graphics).setRenderingHints(renderingHints);
        graphics.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        graphics.setFont(new Font("Helvetica", 0, 11));
        graphics.setColor(Color.red);
        graphics.drawString("Radius " + this.orbitRadius + " AU", 115, this.H - 50);
        graphics.setColor(Color.blue);
        graphics.fillOval(this.xEarth - this.earthRadius, this.yEarth - this.earthRadius, 2 * this.earthRadius, 2 * this.earthRadius);
        this.x = this.xSun + this.SV * Math.sin(this.K * this.alpha);
        this.xx = (int)Math.round(this.x);
        this.y = this.ySun + this.SV * Math.cos(this.K * this.alpha);
        this.yy = (int)Math.round(this.y);
        graphics.setColor(Color.red);
        graphics.fillOval(this.xx - this.planetRadius, this.yy - this.planetRadius, 2 * this.planetRadius, 2 * this.planetRadius);
        graphics.drawLine(this.xSun, this.ySun, this.xx, this.yy);
        graphics.setColor(Color.yellow);
        graphics.fillArc(this.xx - this.planetRadius, this.yy - this.planetRadius, 2 * this.planetRadius, 2 * this.planetRadius, (int)Math.round(this.alpha), 180);
        graphics.setColor(Color.red);
        graphics.drawOval(this.xx - this.planetRadius, this.yy - this.planetRadius, 2 * this.planetRadius, 2 * this.planetRadius);
        graphics.setColor(Color.blue);
        this.xE = this.xSun + this.SE * Math.sin(this.K * this.alphaEarth);
        this.xxE = (int)Math.round(this.xE);
        this.yE = this.ySun + this.SE * Math.cos(this.K * this.alphaEarth);
        this.yyE = (int)Math.round(this.yE);
        graphics.fillOval(this.xxE - this.earthRadius, this.yyE - this.earthRadius, 2 * this.earthRadius, 2 * this.earthRadius);
        graphics.drawOval(this.xSun - this.SE, this.ySun - this.SE, 2 * this.SE, 2 * this.SE);
        graphics.drawLine(this.xSun, this.ySun, this.xxE, this.yyE);
        if (this.retro) {
            graphics.setColor(Color.darkGray);
            this.DX = this.SV * Math.sin(this.K * this.alpha) - this.SE * Math.sin(this.K * this.alphaEarth);
            this.DY = this.SV * Math.cos(this.K * this.alpha) - this.SE * Math.cos(this.K * this.alphaEarth);
            final double n = 1.8 * this.faktor * this.SE;
            this.w = Math.atan2(this.DY, this.DX);
            graphics.drawLine(this.xxE, this.yyE, this.xxE + (int)Math.round(1.05 * n * Math.cos(this.w)), this.yyE + (int)Math.round(1.05 * n * Math.sin(this.w)));
            this.DX1 = this.SV * Math.sin(this.K * (this.alpha + 1.0 / this.tPlanet)) - this.SE * Math.sin(this.K * (this.alphaEarth + 1.0));
            this.DY1 = this.SV * Math.cos(this.K * (this.alpha + 1.0 / this.tPlanet)) - this.SE * Math.cos(this.K * (this.alphaEarth + 1.0));
            this.w1 = Math.atan2(this.DY1, this.DX1);
            if (this.w1 > this.w && this.w * this.w1 > 0.0) {
                graphics.setColor(Color.red);
                graphics.drawString("retro", this.xxE + (int)Math.round(1.05 * n * Math.cos(this.w1)), this.yyE + (int)Math.round(1.05 * n * Math.sin(this.w1)));
            }
        }
        graphics.setColor(Color.magenta);
        graphics.drawLine(this.xxE, this.yyE, this.xx, this.yy);
        this.EP = Math.sqrt((this.x - this.xE) * (this.x - this.xE) + (this.y - this.yE) * (this.y - this.yE));
        graphics.setColor(Color.black);
        graphics.drawString("Dist to Earth = " + Math.round(1000.0 * this.EP / this.SE) / 1000.0 + " AU", this.left, this.H - 25);
        graphics.drawString("Days  = " + this.days, this.left, this.size().height - 70);
        graphics.drawString("Years = " + Math.round(1000 * this.days / this.sidYear) / 1000.0, this.left, this.H - 55);
        graphics.drawString("Synodic periods = " + Math.round(1000 * this.days / this.synodic) / 1000.0, this.left, this.H - 40);
        this.elongAngle = Math.acos((this.SE * this.SE + this.EP * this.EP - this.SV * this.SV) / (2.0 * this.SE * this.EP)) / this.K;
        if (this.planetStr.equals("Venus") || this.planetStr.equals("Mercury")) {
            graphics.drawString("Elong. Angle = " + Math.round(10.0 * this.elongAngle) / 10.0 + " deg", this.left, this.H - 10);
        }
        graphics.drawString(this.versStr, 10, this.H - 20);
        graphics.drawString("© 2001-2011 Juergen Giesen  --  www.GeoAstro.de", 10, this.H - 7);
        graphics.setColor(Color.red);
        graphics.drawOval(this.xSun - this.SV, this.ySun - this.SV, 2 * this.SV, 2 * this.SV);
        if (this.alpha == 0.0) {
            graphics.setColor(Color.blue);
            graphics.drawString("Earth", this.xSun + 20, this.ySun + this.SE + 10);
            graphics.setColor(Color.red);
            graphics.drawString(this.planetStr, this.xSun + 20, this.ySun + this.SV + 10);
            graphics.setColor(Color.black);
            graphics.drawString("Sun", this.xSun + 20, this.ySun + 5);
        }
        graphics.setColor(Color.yellow);
        graphics.fillOval(this.xSun - 6, this.ySun - 6, 12, 12);
        this.winkel = Math.atan2(this.yE - this.y, this.xE - this.x);
        this.xZ = -(int)Math.round(this.faktor * this.EP * Math.cos(this.winkel));
        this.yZ = -(int)Math.round(this.faktor * this.EP * Math.sin(this.winkel));
        graphics.setColor(Color.red);
        final int n2 = (int)Math.round(this.faktor * this.planetRadius);
        graphics.fillOval(this.xEarth + this.xZ - n2, this.yEarth + this.yZ - n2, 2 * n2, 2 * n2);
        if (this.trace && !this.step) {
            this.XX[(int)this.alphaEarth] = this.xZ;
            this.YY[(int)this.alphaEarth] = this.yZ;
            for (int n3 = 1; n3 < this.alphaEarth; ++n3) {
                graphics.drawLine(this.xEarth + this.XX[n3], this.yEarth + this.YY[n3], this.xEarth + this.XX[n3 - 1], this.yEarth + this.YY[n3 - 1]);
            }
        }
        graphics.setColor(Color.magenta);
        graphics.drawLine(this.xEarth, this.yEarth, this.xEarth + this.xZ, this.yEarth + this.yZ);
        if (this.epi) {
            graphics.setColor(Color.gray);
            graphics.drawOval(this.xEarth - this.se, this.yEarth - this.se, 2 * this.se, 2 * this.se);
            this.xEpi = (int)Math.round(this.faktor * this.SE * Math.sin(this.K * this.alphaEarth));
            this.yEpi = (int)Math.round(this.faktor * this.SE * Math.cos(this.K * this.alphaEarth));
            graphics.drawOval(this.xEarth - this.xEpi - 2, this.yEarth - this.yEpi - 2, 4, 4);
            graphics.drawOval(this.xEarth - this.xEpi - this.sv, this.yEarth - this.yEpi - this.sv, 2 * this.sv, 2 * this.sv);
            graphics.setColor(Color.red);
            this.dX = (int)Math.round(this.faktor * this.SV * Math.sin(this.K * this.alpha) - this.faktor * this.SE * Math.sin(this.K * this.alphaEarth));
            this.dY = (int)Math.round(this.faktor * this.SV * Math.cos(this.K * this.alpha) - this.faktor * this.SE * Math.cos(this.K * this.alphaEarth));
            graphics.drawLine(this.xEarth - this.xEpi, this.yEarth - this.yEpi, this.xEarth + this.dX, this.yEarth + this.dY);
            graphics.setColor(Color.yellow);
            graphics.fillArc(this.xEarth + this.dX - this.planetRadius, this.yEarth + this.dY - this.planetRadius, 2 * this.planetRadius, 2 * this.planetRadius, (int)Math.round(this.alpha), 180);
            graphics.setColor(Color.red);
            graphics.drawOval(this.xEarth + this.dX - this.planetRadius, this.yEarth + this.dY - this.planetRadius, 2 * this.planetRadius, 2 * this.planetRadius);
            graphics.setColor(Color.blue);
            graphics.drawLine(this.xEarth, this.yEarth, this.xEarth - this.xEpi, this.yEarth - this.yEpi);
            graphics.setColor(Color.yellow);
            graphics.fillOval(this.xEarth - this.xEpi - 6, this.yEarth - this.yEpi - 6, 12, 12);
        }
        this.r = Math.atan(7200.0 * this.rPlanet * this.SE / (1.4959787E8 * this.EP)) / this.K;
        this.R = (int)Math.round(this.r);
        this.R1 = (int)Math.round(0.25 * this.r);
        graphics.setColor(Color.red);
        graphics.drawString("Diam.= " + Math.round(100.0 * this.r) / 100.0 + "''", this.size().width / 2 - 25, this.H - 10);
        if (this.retro) {
            graphics.setColor(Color.darkGray);
            this.DX = this.faktor * this.SV * Math.sin(this.K * this.alpha) - this.faktor * this.SE * Math.sin(this.K * this.alphaEarth);
            this.DY = this.faktor * this.SV * Math.cos(this.K * this.alpha) - this.faktor * this.SE * Math.cos(this.K * this.alphaEarth);
            final double n4 = 1.8 * this.faktor * this.SE;
            this.w = Math.atan2(this.DY, this.DX);
            graphics.drawLine(this.xEarth, this.yEarth, this.xEarth + (int)Math.round(1.05 * n4 * Math.cos(this.w)), this.yEarth + (int)Math.round(1.05 * n4 * Math.sin(this.w)));
            this.DX1 = this.faktor * this.SV * Math.sin(this.K * (this.alpha + 1.0 / this.tPlanet)) - this.faktor * this.SE * Math.sin(this.K * (this.alphaEarth + 1.0));
            this.DY1 = this.faktor * this.SV * Math.cos(this.K * (this.alpha + 1.0 / this.tPlanet)) - this.faktor * this.SE * Math.cos(this.K * (this.alphaEarth + 1.0));
            this.w1 = Math.atan2(this.DY1, this.DX1);
            if (this.w1 > this.w && this.w * this.w1 > 0.0) {
                graphics.setColor(Color.red);
                graphics.drawString("retro", this.xEarth + (int)Math.round(1.05 * n4 * Math.cos(this.w1)), this.yEarth + (int)Math.round(1.05 * n4 * Math.sin(this.w1)));
            }
            graphics.setColor(Color.red);
            this.w = this.winkel / this.K;
            if (this.w <= 0.0) {
                this.w += 360.0;
            }
            this.W = 100 + (int)Math.round(1.5 * this.w) + 120;
            final double n5 = this.alphaEarth - 1.0;
            final double n6 = this.xSun + this.SE * Math.sin(this.K * n5);
            final double n7 = this.ySun + this.SE * Math.cos(this.K * n5);
            final double n8 = this.alpha - 1.0 / this.tPlanet;
            this.w1 = Math.atan2(n7 - (this.ySun + this.SV * Math.cos(this.K * n8)), n6 - (this.xSun + this.SV * Math.sin(this.K * n8))) / this.K;
            if (this.w1 <= 0.0) {
                this.w1 += 360.0;
            }
            if (this.alphaEarth > 0.0 && this.w1 <= this.w) {
                graphics.drawString("Retrograde", this.W - 30, 110);
            }
            this.xx = 350;
            this.yy = this.H - 70;
            final int n9 = this.R / 2;
            this.elongAngle = Math.acos((this.SE * this.SE + this.EP * this.EP - this.SV * this.SV) / (2.0 * this.SE * this.EP)) / this.K;
            this.Phi = (this.alpha - this.alphaEarth) % 360.0;
            if (this.Phi < 0.0) {
                this.Phi += 360.0;
            }
            if (this.Phi > 180.0) {
                this.elongAngle = -this.elongAngle;
            }
            this.Phi += this.elongAngle;
            this.Phi1 = this.Phi % 360.0;
            this.e = n9 * Math.cos(this.K * this.Phi);
            this.xHA = (int)Math.round(this.e);
            this.yy = 70;
            if (this.Phi1 < 90.0 && this.xHA >= 0) {
                graphics.setColor(Color.black);
                graphics.fillOval(this.W - n9, this.yy - n9, 2 * n9, 2 * n9);
                graphics.clearRect(this.W - n9, this.yy - n9, n9, 2 * n9);
                graphics.fillOval(this.W - this.xHA, this.yy - n9, 2 * this.xHA, 2 * n9);
            }
            else if (this.Phi1 >= 90.0 && this.xHA <= 0 && this.Phi1 < 180.0) {
                graphics.setColor(Color.black);
                graphics.fillOval(this.W - n9, this.yy - n9, 2 * n9, 2 * n9);
                graphics.clearRect(this.W - n9, this.yy - n9, n9, 2 * n9);
                graphics.setColor(Color.white);
                graphics.fillOval(this.W + this.xHA, this.yy - n9, Math.abs(2 * this.xHA), 2 * n9);
            }
            else if (this.Phi1 >= 180.0 && this.xHA <= 0) {
                graphics.setColor(Color.black);
                graphics.fillOval(this.W - n9, this.yy - n9, 2 * n9, 2 * n9);
                graphics.clearRect(this.W, this.yy - n9, n9, 2 * n9);
                graphics.setColor(Color.white);
                graphics.fillOval(this.W + this.xHA, this.yy - n9, Math.abs(2 * this.xHA), 2 * n9);
            }
            else if (this.Phi1 >= 270.0) {
                graphics.setColor(Color.black);
                graphics.fillOval(this.W - n9, this.yy - n9, 2 * n9, 2 * n9);
                graphics.clearRect(this.W, this.yy - n9, n9, 2 * n9);
                graphics.fillOval(this.W - this.xHA, this.yy - n9, 2 * this.xHA, 2 * n9);
            }
            graphics.setColor(Color.black);
            graphics.drawOval(this.W - n9, this.yy - n9, 2 * n9, 2 * n9);
        }
        if (this.phase) {
            this.xx = this.size().width / 2;
            this.yy = this.H - 70;
            final int r = this.R;
            this.elongAngle = Math.acos((this.SE * this.SE + this.EP * this.EP - this.SV * this.SV) / (2.0 * this.SE * this.EP)) / this.K;
            this.Phi = (this.alpha - this.alphaEarth) % 360.0;
            if (this.Phi < 0.0) {
                this.Phi += 360.0;
            }
            if (this.Phi > 180.0) {
                this.elongAngle = -this.elongAngle;
            }
            this.Phi += this.elongAngle;
            this.Phi1 = this.Phi % 360.0;
            this.e = r * Math.cos(this.K * this.Phi);
            this.xHA = (int)Math.round(this.e);
            if (this.Phi1 < 90.0 && this.xHA >= 0) {
                graphics.setColor(Color.black);
                graphics.fillOval(this.xx - r, this.yy - r, 2 * r, 2 * r);
                graphics.clearRect(this.xx - r, this.yy - r, r, 2 * r);
                graphics.fillOval(this.xx - this.xHA, this.yy - r, 2 * this.xHA, 2 * r);
            }
            else if (this.Phi1 >= 90.0 && this.xHA <= 0 && this.Phi1 < 180.0) {
                graphics.setColor(Color.black);
                graphics.fillOval(this.xx - r, this.yy - r, 2 * r, 2 * r);
                graphics.clearRect(this.xx - r, this.yy - r, r, 2 * r);
                graphics.setColor(Color.white);
                graphics.fillOval(this.xx + this.xHA, this.yy - r, Math.abs(2 * this.xHA), 2 * r);
            }
            else if (this.Phi1 >= 180.0 && this.xHA <= 0) {
                graphics.setColor(Color.black);
                graphics.fillOval(this.xx - r, this.yy - r, 2 * r, 2 * r);
                graphics.clearRect(this.xx, this.yy - r, r, 2 * r);
                graphics.setColor(Color.white);
                graphics.fillOval(this.xx + this.xHA, this.yy - r, Math.abs(2 * this.xHA), 2 * r);
            }
            else if (this.Phi1 >= 270.0) {
                graphics.setColor(Color.black);
                graphics.fillOval(this.xx - r, this.yy - r, 2 * r, 2 * r);
                graphics.clearRect(this.xx, this.yy - r, r, 2 * r);
                graphics.fillOval(this.xx - this.xHA, this.yy - r, 2 * this.xHA, 2 * r);
            }
            graphics.setColor(Color.black);
            graphics.drawOval(this.xx - r, this.yy - r, 2 * r, 2 * r);
        }
        if (this.online) {
            graphics.setFont(new Font("Helvetica", 0, this.mySize));
            graphics.drawString("ONLINE", 350, 150);
        }
        if (this.demo) {
            final Font font = graphics.getFont();
            graphics.setColor(Color.red);
            graphics.setFont(new Font("Chicago", 0, 120));
            graphics.drawString("D E M O", 150, 240);
            graphics.setFont(font);
        }
    }
    
    public void conjunction() {
        for (int i = 10; i < this.dimension; ++i) {
            this.alpha = i / this.tPlanet;
            this.alpha %= 360.0;
            this.alphaEarth = i;
            this.days = (int)Math.round(this.alphaEarth * this.tropYear / 360.0);
            this.alphaEarth %= 360.0;
            if (Math.abs(this.alpha - this.alphaEarth) < 1.0) {
                this.repaint();
                break;
            }
        }
    }
    
    public void opposition() {
        for (int i = 10; i < this.dimension; ++i) {
            this.alpha = i / this.tPlanet;
            this.alpha %= 360.0;
            this.alphaEarth = i;
            this.days = (int)Math.round(this.alphaEarth * this.tropYear / 360.0);
            this.alphaEarth %= 360.0;
            if (Math.abs(this.alpha - this.alphaEarth - 180.0) < 1.0) {
                this.repaint();
                break;
            }
        }
    }
    
    public void menuInferior() {
        final int n = this.detailChoice.getItemCount() - 1;
        this.detailChoice.remove(n);
        this.detailChoice.remove(n - 1);
        this.detailChoice.addItem("Inf. Conjunction");
        this.detailChoice.addItem("Sup. Conjunction");
    }
    
    public void menuSuperior() {
        final int n = this.detailChoice.getItemCount() - 1;
        this.detailChoice.remove(n);
        this.detailChoice.remove(n - 1);
        this.detailChoice.addItem("Conjunction");
        this.detailChoice.addItem("Opposition");
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            if (event.target == this.buttonPlus) {
                this.step = true;
                this.alpha += this.intervall / this.tPlanet;
                this.alphaEarth += this.intervall;
                this.days = (int)Math.round(this.alphaEarth * this.tropYear / 360.0);
                this.repaint();
            }
            if (event.target == this.buttonMinus) {
                this.alpha -= this.intervall / this.tPlanet;
                this.alphaEarth -= this.intervall;
                if (this.alphaEarth < 0.0) {
                    this.alphaEarth = -this.alphaEarth;
                }
                this.days = (int)Math.round(this.alphaEarth * this.tropYear / 360.0);
                this.step = true;
                this.repaint();
            }
            if (event.target == this.buttonFirst) {
                this.alpha = 0.0;
                this.alphaEarth = 0.0;
                this.days = 0;
                for (int i = 1; i < this.dimension; ++i) {
                    this.XX[i] = 0;
                    this.YY[i] = 0;
                }
                this.mySize = 10;
                this.repaint();
            }
            if (event.target == this.buttonRunStop) {
                this.running ^= true;
                this.step = false;
                this.start();
            }
            if (!this.running) {
                this.repaint();
            }
            ++this.mySize;
            return true;
        }
        if (event.target instanceof Choice) {
            if (event.target == this.detailChoice) {
                if (this.detailChoice.getSelectedItem().equals("Epicycle on/off")) {
                    this.epi ^= true;
                }
                if (this.detailChoice.getSelectedItem().equals("Trace on/off")) {
                    this.trace ^= true;
                }
                if (this.detailChoice.getSelectedItem().equals("Retro on/off")) {
                    this.retro ^= true;
                }
                if (this.detailChoice.getSelectedItem().equals("Phase on/off")) {
                    this.phase ^= true;
                }
                if (this.detailChoice.getSelectedItem().equals("Opposition")) {
                    this.opposition();
                    this.trace = false;
                }
                if (this.detailChoice.getSelectedItem().equals("Conjunction")) {
                    this.conjunction();
                    this.trace = false;
                }
                if (this.detailChoice.getSelectedItem().equals("Inf. Conjunction")) {
                    this.conjunction();
                    this.trace = false;
                }
                if (this.detailChoice.getSelectedItem().equals("Sup. Conjunction")) {
                    this.opposition();
                    this.trace = false;
                }
                this.detailChoice.select(0);
                this.repaint();
                return true;
            }
            if (event.target == this.timeChoice) {
                if (this.timeChoice.getSelectedItem().equals("day")) {
                    this.intervall = this.sidYear / 360.0;
                }
                if (this.timeChoice.getSelectedItem().equals("week")) {
                    this.intervall = 7.0 * this.sidYear / 360.0;
                }
                if (this.timeChoice.getSelectedItem().equals("month")) {
                    this.intervall = this.sidYear / 12.0;
                }
                if (this.timeChoice.getSelectedItem().equals("year")) {
                    this.intervall = 360.0;
                }
                if (this.timeChoice.getSelectedItem().equals("synod")) {
                    if (this.planetChoice.getSelectedItem().equals("Mercury")) {
                        this.intervall = 41715.936 / this.sidYear;
                    }
                    if (this.planetChoice.getSelectedItem().equals("Venus")) {
                        this.intervall = 210211.41600000003 / this.sidYear;
                    }
                    if (this.planetChoice.getSelectedItem().equals("Mars")) {
                        this.intervall = 280786.212 / this.sidYear;
                    }
                    if (this.planetChoice.getSelectedItem().equals("Jupiter")) {
                        this.intervall = 143596.116 / this.sidYear;
                    }
                    if (this.planetChoice.getSelectedItem().equals("Saturn")) {
                        this.intervall = 136114.416 / this.sidYear;
                    }
                }
            }
            if (event.target == this.speedChoice) {
                if (this.speedChoice.getSelectedItem().equals("speed 1")) {
                    this.delay = 500;
                }
                if (this.speedChoice.getSelectedItem().equals("speed 2")) {
                    this.delay = 250;
                }
                if (this.speedChoice.getSelectedItem().equals("speed 3")) {
                    this.delay = 100;
                }
                if (this.speedChoice.getSelectedItem().equals("speed 4")) {
                    this.delay = 50;
                }
                if (this.speedChoice.getSelectedItem().equals("speed 5")) {
                    this.delay = 20;
                }
                if (this.speedChoice.getSelectedItem().equals("speed 6")) {
                    this.delay = 10;
                }
            }
            if (event.target == this.sizeChoice) {
                if (this.sizeChoice.getSelectedItem().equals("Full Size")) {
                    this.faktor = 1.0;
                }
                if (this.sizeChoice.getSelectedItem().equals("0.75 Size")) {
                    this.faktor = 0.75;
                }
                if (this.sizeChoice.getSelectedItem().equals("0.50 Size")) {
                    this.faktor = 0.5;
                }
                this.se = (int)Math.round(this.faktor * this.SE);
                this.sv = (int)Math.round(this.faktor * this.SV);
                this.alpha = 0.0;
                this.alphaEarth = 0.0;
                this.days = 0;
                for (int j = 1; j < this.dimension; ++j) {
                    this.XX[j] = 0;
                    this.YY[j] = 0;
                }
                this.repaint();
            }
            if (event.target == this.planetChoice) {
                if (this.planetChoice.getSelectedItem().equals("Saturn")) {
                    this.SE = 15;
                    this.SV = (int)Math.round(9.5371 * this.SE);
                    this.earthRadius = 5;
                    this.rPlanet = 60268.0;
                    this.planetStr = "Saturn";
                    this.orbitRadius = 9.5371;
                    this.tPlanetDays = 10756.1995;
                    this.tPlanet = this.tPlanetDays / this.tropYear;
                    this.synodic = 1.0 / this.sidYear - 1.0 / this.tPlanetDays;
                    this.synodic = 1.0 / this.synodic;
                    this.planetRadius = Math.round(this.earthRadius * 2);
                    this.alpha = 0.0;
                    this.alphaEarth = 0.0;
                    this.days = 0;
                    if (this.timeChoice.getSelectedItem().equals("synod")) {
                        this.intervall = 136116.0 / this.sidYear;
                    }
                }
                if (this.planetChoice.getSelectedItem().equals("Jupiter")) {
                    this.SE = 25;
                    this.SV = (int)Math.round(5.20336 * this.SE);
                    this.earthRadius = 4;
                    this.rPlanet = 71492.0;
                    this.planetStr = "Jupiter";
                    this.orbitRadius = 5.20336;
                    this.tPlanetDays = 4333.2867;
                    this.tPlanet = this.tPlanetDays / this.tropYear;
                    this.synodic = 1.0 / this.sidYear - 1.0 / this.tPlanetDays;
                    this.synodic = 1.0 / this.synodic;
                    this.planetRadius = Math.round(this.earthRadius * 2);
                    this.alpha = 0.0;
                    this.alphaEarth = 0.0;
                    this.days = 0;
                    if (this.timeChoice.getSelectedItem().equals("synod")) {
                        this.intervall = 143596.8 / this.sidYear;
                    }
                    this.menuSuperior();
                }
                if (this.planetChoice.getSelectedItem().equals("Mars")) {
                    this.SE = 90;
                    this.SV = (int)Math.round(1.524 * this.SE);
                    this.earthRadius = 10;
                    this.rPlanet = 3397.2;
                    this.planetStr = "Mars";
                    this.orbitRadius = 1.524;
                    this.tPlanetDays = 686.96;
                    this.tPlanet = this.tPlanetDays / this.tropYear;
                    this.synodic = 1.0 / this.sidYear - 1.0 / this.tPlanetDays;
                    this.synodic = 1.0 / this.synodic;
                    this.planetRadius = (int)Math.round(2 * this.earthRadius * 0.532);
                    this.alpha = 0.0;
                    this.alphaEarth = 0.0;
                    this.days = 0;
                    if (this.timeChoice.getSelectedItem().equals("synod")) {
                        this.intervall = 280764.0 / this.sidYear;
                    }
                    this.menuSuperior();
                }
                if (this.planetChoice.getSelectedItem().equals("Mercury")) {
                    this.SE = 120;
                    this.SV = (int)Math.round(0.387 * this.SE);
                    this.rPlanet = 2439.7;
                    this.earthRadius = 10;
                    this.planetStr = "Mercury";
                    this.orbitRadius = 0.387;
                    this.tPlanetDays = 87.96934;
                    this.tPlanet = this.tPlanetDays / this.tropYear;
                    this.synodic = 1.0 / this.sidYear - 1.0 / this.tPlanetDays;
                    this.synodic = -1.0 / this.synodic;
                    this.planetRadius = (int)Math.round(2 * this.earthRadius * 0.382);
                    this.alpha = 0.0;
                    this.alphaEarth = 0.0;
                    this.days = 0;
                    if (this.timeChoice.getSelectedItem().equals("synod")) {
                        this.intervall = 41724.0 / this.sidYear;
                    }
                    this.menuInferior();
                }
                if (this.planetChoice.getSelectedItem().equals("Venus")) {
                    this.SE = 120;
                    this.SV = (int)Math.round(0.723 * this.SE);
                    this.earthRadius = 10;
                    this.rPlanet = 6051.8;
                    this.planetStr = "Venus";
                    this.orbitRadius = 0.723;
                    this.tPlanetDays = 224.70069;
                    this.tPlanet = this.tPlanetDays / this.tropYear;
                    this.synodic = 1.0 / this.sidYear - 1.0 / this.tPlanetDays;
                    this.synodic = -1.0 / this.synodic;
                    this.planetRadius = (int)Math.round(this.earthRadius * 0.949);
                    this.alpha = 0.0;
                    this.alphaEarth = 0.0;
                    this.days = 0;
                    if (this.timeChoice.getSelectedItem().equals("synod")) {
                        this.intervall = 210204.0 / this.sidYear;
                    }
                    this.menuInferior();
                }
                this.se = (int)Math.round(this.faktor * this.SE);
                this.sv = (int)Math.round(this.faktor * this.SV);
                this.alpha = 0.0;
                this.repaint();
            }
        }
        ++this.mySize;
        return true;
    }
    
    public GeocentricMotion14() {
        this.versStr = "Heliocentric and Geocentric Planet Motion  1.4";
        this.str = "";
        this.left = 630;
        this.sidYear = 365.25636042;
        this.tropYear = 365.242190419;
        this.K = 0.017453292519943295;
        this.running = false;
        this.delay = 50;
        this.xSun = 160;
        this.ySun = 280;
        this.dimension = 12000;
        this.xEarth = 540;
        this.yEarth = 280;
        this.XX = new int[this.dimension];
        this.YY = new int[this.dimension];
        this.faktor = 1.0;
        this.planetStr = "";
        this.planetRadius = 8;
        this.earthRadius = 4;
        this.demo = true;
        this.online = false;
        this.step = false;
        this.epi = true;
        this.trace = true;
        this.retro = true;
        this.conj = false;
        this.oppos = false;
        this.phase = true;
        this.mySize = 10;
    }
}
