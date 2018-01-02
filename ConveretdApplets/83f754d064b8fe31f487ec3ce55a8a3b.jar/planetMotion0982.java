import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Component;
import java.awt.Panel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.Button;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class planetMotion0982 extends Applet implements Runnable
{
    Thread myThread;
    String versStr;
    Date dat;
    Button buttonMinus;
    Button buttonPlus;
    Button buttonFirst;
    Button buttonRunStop;
    Checkbox box;
    Choice planetChoice;
    Choice speedChoice;
    Choice timeChoice;
    String str;
    String str1;
    int left;
    double intervall;
    double time;
    int days;
    double year;
    double alphaE;
    double K;
    boolean running;
    int delay;
    int SE;
    int SV;
    int delta;
    int rPlanetMax;
    int xSun;
    int ySun;
    String planetStr;
    double diameterPlanet;
    double orbitRadius;
    int planetRadius;
    int earthRadius;
    double tPlanet;
    double w;
    double w1;
    double M;
    String myStr;
    boolean demo;
    boolean online;
    public String email;
    public String param;
    public String wwwStr;
    double alpha;
    double alphaEarth;
    Image bild1;
    Image bild2;
    Image bild3;
    double synPeriods;
    double daysSynPeriod;
    double x1;
    double y1;
    double xE1;
    double yE1;
    int xx;
    int yy;
    int RV;
    int RE;
    int xxE;
    int yyE;
    double xE;
    double yE;
    double x;
    double y;
    double X;
    double Y;
    int R;
    int R1;
    int R2;
    double A;
    double BB;
    double xS;
    double yS;
    int xxS;
    int yyS;
    double m;
    double m1;
    double D;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        if ((int)num < 99) {
            return -2 * this.dat.getSeconds();
        }
        return (int)num + 14445;
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
                this.alpha %= 360.0;
                ++this.alphaEarth;
                this.alphaEarth %= 360.0;
                ++this.alphaE;
                this.days = (int)Math.round(this.alphaE * 365.25 / 360.0);
                this.synPeriods = this.alphaE / this.daysSynPeriod;
            }
            catch (Exception ex) {}
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void init() {
        this.SE = 150;
        this.SV = (int)Math.round(0.723 * this.SE);
        this.delta = this.SE - this.SV;
        this.rPlanetMax = 20;
        this.planetStr = "Venus";
        this.diameterPlanet = 12103.6;
        this.orbitRadius = 0.723;
        this.tPlanet = 0.61521;
        this.planetRadius = 10;
        this.earthRadius = 10;
        this.intervall = 30.0;
        this.alpha = 0.0;
        this.alphaEarth = 0.0;
        this.alphaE = 0.0;
        this.days = 0;
        this.daysSynPeriod = 583.9;
        this.setBackground(Color.white);
        this.setFont(new Font("Helvetica", 0, 12));
        final URL url = this.getDocumentBase();
        this.myStr = url.toString();
        this.myStr = String.valueOf(this.myStr) + "1234567890123456789012345";
        this.wwwStr = this.myStr.substring(0, 27);
        this.bild1 = this.getImage(this.getDocumentBase(), "applet/EarthMer.gif");
        this.bild2 = this.getImage(this.getDocumentBase(), "applet/EarthVen.gif");
        this.bild3 = this.getImage(this.getDocumentBase(), "applet/EarthMar.gif");
        final Panel panel = new Panel();
        this.add("Buttons", panel);
        (this.planetChoice = new Choice()).addItem("Mercury");
        this.planetChoice.addItem("Venus");
        this.planetChoice.addItem("Mars");
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
        this.timeChoice.addItem("5 synod");
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
        this.speedChoice.select("speed 3");
        panel.add(this.speedChoice);
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        if (this.formula(this.wwwStr, 23) == this.formula("http://www.mercury-tran", 23) || this.formula(this.wwwStr, 23) == this.formula("http://www.merkur-trans", 23) || this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 22) == this.formula("http://www.j-giesen.de", 22) || this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(this.wwwStr, 24) == this.formula("http://www.venus-transit", 24)) {
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
        this.repaint();
    }
    
    public void paint(final Graphics g) {
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        g.setFont(new Font("Helvetica", 0, 10));
        g.drawString("Planet Motion " + this.versStr, 10, this.size().height - 20);
        g.drawString("© 2001-2008 J. Giesen --  www.GeoAstro.de", 10, this.size().height - 7);
        if (this.planetStr.equals("Mercury")) {
            g.drawImage(this.bild1, 450, 100, this);
        }
        else if (this.planetStr.equals("Venus")) {
            g.drawImage(this.bild2, 450, 100, this);
        }
        else if (this.planetStr.equals("Mars")) {
            g.drawImage(this.bild3, 450, 100, this);
        }
        g.drawString("Days since inf. conj.  = " + this.days, this.left, this.size().height - 85);
        this.str = String.valueOf(Math.round(1000 * this.days / 365.2564) / 1000.0);
        g.drawString("Years since inf. conj.= " + this.str, this.left, this.size().height - 70);
        this.str = String.valueOf(Math.round(1000.0 * this.synPeriods) / 1000.0);
        g.drawString("Syn. per. since inf.c.= " + this.str, this.left, this.size().height - 55);
        g.drawString("Mean Dist. to Sun = " + this.orbitRadius + " AU", this.left, this.size().height - 40);
        this.X = this.SV * Math.sin(this.K * this.alpha);
        this.x = this.xSun + this.X;
        this.xx = (int)Math.round(this.x);
        this.RV = this.SV;
        this.Y = this.RV * Math.cos(this.K * this.alpha);
        this.y = this.ySun + this.Y;
        this.yy = (int)Math.round(this.y);
        g.setColor(Color.red);
        g.fillOval(this.xx - this.planetRadius, this.yy - this.planetRadius, 2 * this.planetRadius, 2 * this.planetRadius);
        this.RE = this.SE;
        g.setColor(Color.blue);
        this.X = this.SE * Math.sin(this.K * this.alphaEarth);
        this.xE = this.xSun + this.X;
        this.xxE = (int)Math.round(this.xE);
        this.Y = this.RE * Math.cos(this.K * this.alphaEarth);
        this.yE = this.ySun + this.Y;
        this.yyE = (int)Math.round(this.yE);
        g.fillOval(this.xxE - this.earthRadius, this.yyE - this.earthRadius, 2 * this.earthRadius, 2 * this.earthRadius);
        g.drawOval(this.xSun - this.SE, this.ySun - this.RE, 2 * this.SE, 2 * this.RE);
        if (this.planetStr.equals("Mars")) {
            this.R = (int)Math.round(4.0 * this.SV);
        }
        else {
            this.R = (int)Math.round(1.35 * this.SE);
        }
        this.R1 = this.R - 1;
        this.R2 = this.R + 1;
        g.setColor(Color.darkGray);
        g.drawOval(this.xSun - this.R1, this.ySun - this.R1, 2 * this.R1, 2 * this.R1);
        g.drawOval(this.xSun - this.R2, this.ySun - this.R2, 2 * this.R2, 2 * this.R2);
        g.drawOval(this.xSun - this.R, this.ySun - this.R, 2 * this.R, 2 * this.R);
        g.setColor(Color.black);
        if (this.x == this.xE) {
            this.m = 1.0E100;
        }
        else {
            this.m = (this.y - this.yE) / (this.x - this.xE);
        }
        if (this.x == this.xE) {
            this.M = 1.0E100;
        }
        this.M = this.m;
        this.m1 = this.m * this.m + 1.0;
        this.D = this.yE - this.ySun - this.m * this.xE;
        this.A = (this.xSun - this.m * this.D) / this.m1;
        this.BB = (this.R * this.R - this.xSun * this.xSun - this.D * this.D) / this.m1;
        g.setColor(Color.red);
        if (this.xE >= this.x) {
            this.xS = this.A - Math.sqrt(this.BB + this.A * this.A);
            this.xxS = (int)Math.round(this.xS);
            this.yS = this.yE + this.m * (this.xS - this.xE);
            this.yyS = (int)Math.round(this.yS);
            if (this.x == this.xE) {
                this.xxS = this.xSun;
                this.yyS = this.ySun - this.R;
                if (this.planetStr.equals("Mars")) {
                    this.yyS = this.ySun + this.R;
                }
            }
            g.drawLine(this.xxE, this.yyE, this.xxS, this.yyS);
        }
        else {
            this.xS = this.A + Math.sqrt(this.BB + this.A * this.A);
            this.xxS = (int)Math.round(this.xS);
            this.yS = this.yE + this.m * (this.xS - this.xE);
            this.yyS = (int)Math.round(this.yS);
            g.drawLine(this.xxE, this.yyE, this.xxS, this.yyS);
        }
        this.w = -90.0 + Math.atan2(-(this.y - this.yE), this.x - this.xE) / this.K;
        if (this.w <= 0.0) {
            this.w += 360.0;
        }
        this.x1 = this.xSun + this.SV * Math.sin(this.K * (this.alpha + 1.0 / this.tPlanet));
        this.y1 = this.ySun + this.SV * Math.cos(this.K * (this.alpha + 1.0 / this.tPlanet));
        this.xE1 = this.xSun + this.SE * Math.sin(this.K * (this.alphaEarth + 1.0));
        this.yE1 = this.ySun + this.SE * Math.cos(this.K * (this.alphaEarth + 1.0));
        this.w1 = -90.0 + Math.atan2(-(this.y1 - this.yE1), this.x1 - this.xE1) / this.K;
        if (this.w1 <= 0.0) {
            this.w1 += 360.0;
        }
        if (this.w1 < this.w && Math.abs(this.w - this.w1) < 100.0) {
            g.drawOval(this.xxS - 5, this.yyS - 5, 10, 10);
            g.drawString("RETROGRADE", 30, 75);
        }
        else {
            g.fillOval(this.xxS - 5, this.yyS - 5, 10, 10);
        }
        g.setColor(Color.red);
        final double EP = Math.sqrt((this.x - this.xE) * (this.x - this.xE) + (this.y - this.yE) * (this.y - this.yE));
        this.str = String.valueOf(Math.round(1000.0 * EP / this.SE) / 1000.0);
        g.setColor(Color.black);
        g.drawString("Distance to Earth   = " + this.str + " AU", this.left, this.size().height - 25);
        final double elongAngle = Math.acos((this.SE * this.SE + EP * EP - this.SV * this.SV) / (2.0 * this.SE * EP)) / this.K;
        if (this.planetStr.equals("Venus") || this.planetStr.equals("Mercury")) {
            this.str = String.valueOf(String.valueOf(Math.round(10.0 * elongAngle) / 10.0)) + " deg";
            g.drawString("Elong. Angle = " + this.str, this.left, this.size().height - 10);
        }
        g.setColor(Color.black);
        g.drawLine(this.xSun, this.ySun, this.xx, this.yy);
        g.setColor(Color.red);
        g.drawOval(this.xSun - this.SV, this.ySun - this.RV, 2 * this.SV, 2 * this.RV);
        g.setColor(Color.blue);
        if (this.alpha == 0.0) {
            g.drawString("Earth", this.xSun + 20, this.ySun + this.RE + 10);
            g.setColor(Color.red);
            g.drawString(this.planetStr, this.xSun + 20, this.ySun + this.RV + 10);
            g.setColor(Color.black);
            g.drawString("Sun", this.xSun + 20, this.ySun + 5);
        }
        g.drawLine(this.xSun, this.ySun, this.xxE, this.yyE);
        g.setColor(Color.yellow);
        g.fillOval(this.xSun - 10, this.ySun - 10, 20, 20);
        if (this.demo) {
            final Font f = g.getFont();
            g.setColor(Color.red);
            g.setFont(new Font("Chicago", 0, 120));
            g.drawString("D E M O", 40, 240);
            g.setFont(f);
        }
    }
    
    public boolean action(final Event event, final Object eventobject) {
        final String str = this.timeChoice.getSelectedItem();
        if (event.target instanceof Button) {
            if (event.target == this.buttonPlus) {
                this.alpha += this.intervall / this.tPlanet;
                this.alpha %= 360.0;
                this.alphaEarth += this.intervall;
                this.alphaE += this.intervall;
                this.synPeriods = this.alphaE * 365.25 / 360.0 / this.daysSynPeriod;
                this.days = (int)Math.round(this.alphaE * 365.25 / 360.0);
                this.alphaEarth %= 360.0;
                this.repaint();
                return true;
            }
            if (event.target == this.buttonMinus) {
                this.alpha -= this.intervall / this.tPlanet;
                this.alpha %= 360.0;
                this.alphaEarth -= this.intervall;
                this.alphaE -= this.intervall;
                this.synPeriods = this.alphaE * 365.25 / 360.0 / this.daysSynPeriod;
                this.days = (int)Math.round(this.alphaE * 365.25 / 360.0);
                this.alphaEarth %= 360.0;
                this.repaint();
            }
            if (event.target == this.buttonFirst) {
                this.alpha = 0.0;
                this.alphaEarth = 0.0;
                this.days = 0;
                this.alphaE = 0.0;
                this.synPeriods = 0.0;
                this.repaint();
            }
            if (event.target == this.buttonRunStop) {
                this.running ^= true;
                this.start();
            }
        }
        if (event.target instanceof Choice) {
            if (event.target == this.timeChoice) {
                if (this.timeChoice.getSelectedItem().equals("day")) {
                    this.intervall = this.year / 360.0;
                }
                if (this.timeChoice.getSelectedItem().equals("week")) {
                    this.intervall = 7.0 * this.year / 360.0;
                }
                if (this.timeChoice.getSelectedItem().equals("month")) {
                    this.intervall = this.year / 12.0;
                }
                if (this.timeChoice.getSelectedItem().equals("year")) {
                    this.intervall = 360.0;
                }
                if (this.timeChoice.getSelectedItem().equals("synod")) {
                    if (this.planetChoice.getSelectedItem().equals("Mercury")) {
                        this.intervall = 41724.0 / this.year;
                    }
                    if (this.planetChoice.getSelectedItem().equals("Venus")) {
                        this.intervall = 210204.0 / this.year;
                    }
                    if (this.planetChoice.getSelectedItem().equals("Mars")) {
                        this.intervall = 280764.0 / this.year;
                    }
                }
                if (this.timeChoice.getSelectedItem().equals("5 synod")) {
                    if (this.planetChoice.getSelectedItem().equals("Mercury")) {
                        this.intervall = 208620.0 / this.year;
                    }
                    if (this.planetChoice.getSelectedItem().equals("Venus")) {
                        this.intervall = 1051020.0 / this.year;
                    }
                    if (this.planetChoice.getSelectedItem().equals("Mars")) {
                        this.intervall = 1403820.0 / this.year;
                    }
                }
                if (this.timeChoice.getSelectedItem().equals("41 synod")) {
                    if (this.planetChoice.getSelectedItem().equals("Mercury")) {
                        this.intervall = 1710684.0000000002 / this.year;
                    }
                    if (this.planetChoice.getSelectedItem().equals("Venus")) {
                        this.intervall = 1051020.0 / this.year;
                    }
                    if (this.planetChoice.getSelectedItem().equals("Mars")) {
                        this.intervall = 1403820.0 / this.year;
                    }
                }
                if (this.timeChoice.getSelectedItem().equals("")) {
                    this.timeChoice.select(str);
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
            }
            if (event.target == this.planetChoice) {
                if (this.planetChoice.getSelectedItem().equals("Mars")) {
                    this.SE = 30;
                    this.earthRadius = 7;
                    this.SV = (int)Math.round(1.524 * this.SE);
                    this.delta = this.SE - this.SV;
                    this.rPlanetMax = 10;
                    this.planetStr = "Mars";
                    this.diameterPlanet = 4880.0;
                    this.orbitRadius = 1.524;
                    this.rPlanetMax = (int)Math.round(13.381742738589214);
                    this.tPlanet = 1.881;
                    this.planetRadius = (int)Math.round(this.earthRadius * 0.532);
                    this.alpha = 0.0;
                    this.alphaEarth = 0.0;
                    this.alphaE = 0.0;
                    this.days = 0;
                    if (this.timeChoice.getSelectedItem().equals("synod")) {
                        this.intervall = 280764.0 / this.year;
                    }
                    if (this.timeChoice.getItemCount() == 6) {
                        this.timeChoice.remove(5);
                    }
                    this.daysSynPeriod = 779.9;
                }
                if (this.planetChoice.getSelectedItem().equals("Mercury")) {
                    this.SE = 150;
                    this.SV = (int)Math.round(0.387 * this.SE);
                    this.delta = this.SE - this.SV;
                    this.rPlanetMax = 10;
                    this.planetStr = "Mercury";
                    this.diameterPlanet = 4880.0;
                    this.orbitRadius = 0.387;
                    this.rPlanetMax = (int)Math.round(13.381742738589214);
                    this.tPlanet = 0.24085;
                    this.planetRadius = (int)Math.round(this.earthRadius * 0.382);
                    this.alpha = 0.0;
                    this.alphaEarth = 0.0;
                    this.alphaE = 0.0;
                    this.days = 0;
                    if (this.timeChoice.getSelectedItem().equals("synod")) {
                        this.intervall = 41724.0 / this.year;
                    }
                    if (this.timeChoice.getItemCount() == 6) {
                        this.timeChoice.remove(5);
                    }
                    this.timeChoice.add("41 synod");
                    this.daysSynPeriod = 115.9;
                }
                if (this.planetChoice.getSelectedItem().equals("Venus")) {
                    this.SE = 150;
                    this.SV = (int)Math.round(0.723 * this.SE);
                    this.delta = this.SE - this.SV;
                    this.rPlanetMax = 20;
                    this.planetStr = "Venus";
                    this.diameterPlanet = 12103.6;
                    this.orbitRadius = 0.723;
                    this.rPlanetMax = 20;
                    this.tPlanet = 0.61521;
                    this.planetRadius = (int)Math.round(this.earthRadius * 0.949);
                    this.alpha = 0.0;
                    this.alphaEarth = 0.0;
                    this.alphaE = 0.0;
                    this.days = 0;
                    if (this.timeChoice.getSelectedItem().equals("synod")) {
                        this.intervall = 210204.0 / this.year;
                    }
                    if (this.timeChoice.getSelectedItem().equals("5 synod")) {
                        this.intervall = 1051020.0 / this.year;
                    }
                    if (this.timeChoice.getItemCount() == 6) {
                        this.timeChoice.remove(5);
                    }
                    this.timeChoice.add("5 synod");
                    this.daysSynPeriod = 583.9;
                }
                this.alpha = 0.0;
                this.repaint();
            }
        }
        return true;
    }
    
    public planetMotion0982() {
        this.versStr = "  0.982";
        this.str = "";
        this.str1 = "";
        this.left = 405;
        this.year = 365.2564;
        this.K = 0.017453292519943295;
        this.running = false;
        this.delay = 100;
        this.xSun = 245;
        this.ySun = 255;
        this.planetStr = "";
        this.demo = true;
        this.online = false;
        this.A = 0.0;
        this.BB = 0.0;
        this.xxS = 0;
        this.yyS = 0;
    }
}
