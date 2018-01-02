import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Panel;
import java.awt.Image;
import java.awt.Choice;
import java.awt.Button;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class GeocentricMotion097 extends Applet implements Runnable
{
    Thread myThread;
    String versStr;
    Date dat;
    Button buttonMinus;
    Button buttonPlus;
    Button buttonFirst;
    Button buttonRunStop;
    Choice planetChoice;
    Choice speedChoice;
    Choice timeChoice;
    String str;
    int left;
    double intervall;
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
    int dimension;
    int xEarth;
    int yEarth;
    int[] XX;
    int[] YY;
    double faktor;
    String planetStr;
    double diameterPlanet;
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
    Image bild1;
    Image bild2;
    Image bild3;
    
    public int formula(final String s, final int n) {
        long n2 = 0L;
        for (int i = 0; i < n; ++i) {
            final char char1 = s.charAt(i);
            final long n3 = i * Character.digit(char1, i) * Character.digit(char1, 36 - i);
            final long n4 = Character.digit(char1, 36 - i);
            n2 += n4 * n4;
        }
        if ((int)n2 < 99) {
            return -2 * this.dat.getSeconds();
        }
        return (int)n2;
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
                ++this.alphaE;
                this.days = (int)Math.round(this.alphaE * 365.25 / 360.0);
            }
            catch (Exception ex) {}
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void init() {
        this.SE = 100;
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
        this.winkel = 0.0;
        this.days = 0;
        this.myStr = this.getDocumentBase().toString();
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
        this.speedChoice.addItem("speed 7");
        this.speedChoice.select("speed 4");
        panel.add(this.speedChoice);
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        this.usrStr = this.email;
        this.userString = this.email;
        boolean b;
        if (this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21) || this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(this.wwwStr, 24) == this.formula("http://www.venus-transit", 24)) {
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
        graphics.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        graphics.setColor(Color.blue);
        graphics.fillOval(this.xEarth - this.earthRadius / 2, this.yEarth - this.earthRadius / 2, this.earthRadius, this.earthRadius);
        graphics.setFont(new Font("Helvetica", 0, 10));
        graphics.drawString("Heliocentric and Geocentric Planet Motion " + this.versStr, 10, this.size().height - 20);
        graphics.drawString("© 2001 Juergen Giesen  --  www.jgiesen.de", 10, this.size().height - 7);
        if (this.planetStr.equals("Mercury")) {
            graphics.drawImage(this.bild1, 300, this.size().height - 75, this);
        }
        if (this.planetStr.equals("Venus")) {
            graphics.drawImage(this.bild2, 300, this.size().height - 75, this);
        }
        if (this.planetStr.equals("Mars")) {
            graphics.drawImage(this.bild3, 300, this.size().height - 75, this);
        }
        graphics.drawString("Days since inf. conj.  = " + this.days, this.left, this.size().height - 70);
        this.str = String.valueOf(Math.round(1000 * this.days / 365.2564) / 1000.0);
        graphics.drawString("Years since inf. conj.= " + this.str, this.left, this.size().height - 55);
        graphics.drawString("Mean Dist. to Sun = " + this.orbitRadius + " AU", this.left, this.size().height - 40);
        final double n = this.xSun + this.SV * Math.sin(this.K * this.alpha);
        final int n2 = (int)Math.round(n);
        final double n3 = this.ySun + this.SV * Math.cos(this.K * this.alpha);
        final int n4 = (int)Math.round(n3);
        graphics.setColor(Color.red);
        graphics.fillOval(n2 - this.planetRadius, n4 - this.planetRadius, 2 * this.planetRadius, 2 * this.planetRadius);
        graphics.drawLine(this.xSun, this.ySun, n2, n4);
        graphics.setColor(Color.blue);
        final double n5 = this.xSun + this.SE * Math.sin(this.K * this.alphaEarth);
        final int n6 = (int)Math.round(n5);
        final double n7 = this.ySun + this.SE * Math.cos(this.K * this.alphaEarth);
        final int n8 = (int)Math.round(n7);
        graphics.fillOval(n6 - this.earthRadius, n8 - this.earthRadius, 2 * this.earthRadius, 2 * this.earthRadius);
        graphics.drawOval(this.xSun - this.SE, this.ySun - this.SE, 2 * this.SE, 2 * this.SE);
        graphics.drawLine(this.xSun, this.ySun, n6, n8);
        final double sqrt = Math.sqrt((n - n5) * (n - n5) + (n3 - n7) * (n3 - n7));
        this.str = String.valueOf(Math.round(1000.0 * sqrt / this.SE) / 1000.0);
        graphics.setColor(Color.black);
        graphics.drawString("Distance to Earth   = " + this.str + " AU", this.left, this.size().height - 25);
        final double n9 = Math.acos((this.SE * this.SE + sqrt * sqrt - this.SV * this.SV) / (2.0 * this.SE * sqrt)) / this.K;
        if (this.planetStr.equals("Venus") || this.planetStr.equals("Mercury")) {
            this.str = String.valueOf(String.valueOf(Math.round(10.0 * n9) / 10.0)) + " deg";
            graphics.drawString("Elong. Angle = " + this.str, this.left, this.size().height - 10);
        }
        graphics.setColor(Color.red);
        graphics.drawOval(this.xSun - this.SV, this.ySun - this.SV, 2 * this.SV, 2 * this.SV);
        if (this.alpha == 0.0) {
            graphics.drawString("Earth", this.xSun + 20, this.ySun + this.SE + 10);
            graphics.setColor(Color.red);
            graphics.drawString(this.planetStr, this.xSun + 20, this.ySun + this.SV + 10);
            graphics.setColor(Color.black);
            graphics.drawString("Sun", this.xSun + 20, this.ySun + 5);
        }
        graphics.setColor(Color.yellow);
        graphics.fillOval(this.xSun - 10, this.ySun - 10, 20, 20);
        graphics.drawLine(this.xSun - 15, this.ySun - 1, this.xSun + 15, this.ySun - 1);
        graphics.drawLine(this.xSun - 15, this.ySun + 1, this.xSun + 15, this.ySun + 1);
        graphics.drawLine(this.xSun - 20, this.ySun, this.xSun + 20, this.ySun);
        graphics.drawLine(this.xSun - 1, this.ySun - 15, this.xSun - 1, this.ySun + 15);
        graphics.drawLine(this.xSun + 1, this.ySun - 15, this.xSun + 1, this.ySun + 15);
        graphics.drawLine(this.xSun, this.ySun - 20, this.xSun, this.ySun + 20);
        graphics.drawLine(this.xSun - 15, this.ySun - 15, this.xSun + 15, this.ySun + 15);
        graphics.drawLine(this.xSun - 15, this.ySun + 15, this.xSun + 15, this.ySun - 15);
        this.winkel = Math.atan2(n7 - n3, n5 - n);
        final int n10 = -(int)Math.round(this.faktor * sqrt * Math.cos(this.winkel));
        final int n11 = -(int)Math.round(this.faktor * sqrt * Math.sin(this.winkel));
        graphics.setColor(Color.red);
        graphics.fillOval(this.xEarth + n10 - 3, this.yEarth + n11 - 3, 6, 6);
        this.XX[(int)this.alphaEarth] = n10;
        this.YY[(int)this.alphaEarth] = n11;
        for (int i = 1; i < this.dimension; ++i) {
            graphics.drawLine(this.xEarth + this.XX[i], this.yEarth + this.YY[i], this.xEarth + this.XX[i - 1], this.yEarth + this.YY[i - 1]);
        }
        if (this.demo) {
            final Font font = graphics.getFont();
            graphics.setColor(Color.red);
            graphics.setFont(new Font("Chicago", 0, 120));
            graphics.drawString("D E M O", 40, 240);
            graphics.setFont(font);
        }
    }
    
    public boolean action(final Event event, final Object o) {
        if (event.target instanceof Button) {
            if (event.target == this.buttonPlus) {
                this.alpha += this.intervall / this.tPlanet;
                this.alpha %= 360.0;
                this.alphaEarth += this.intervall;
                this.alphaE += this.intervall;
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
                this.days = (int)Math.round(this.alphaE * 365.25 / 360.0);
                this.alphaEarth %= 360.0;
                this.repaint();
            }
            if (event.target == this.buttonFirst) {
                this.alpha = 0.0;
                this.alphaEarth = 0.0;
                this.days = 0;
                this.alphaE = 0.0;
                for (int i = 1; i < this.dimension; ++i) {
                    this.XX[i] = 0;
                    this.YY[i] = 0;
                }
                this.repaint();
            }
            if (event.target == this.buttonRunStop) {
                this.running ^= true;
                this.start();
            }
            if (!this.running) {
                this.repaint();
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
                if (this.speedChoice.getSelectedItem().equals("speed 7")) {
                    this.delay = 5;
                }
            }
            if (event.target == this.planetChoice) {
                if (this.planetChoice.getSelectedItem().equals("Mars")) {
                    this.SE = 60;
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
                }
                if (this.planetChoice.getSelectedItem().equals("Mercury")) {
                    this.SE = 75;
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
                }
                if (this.planetChoice.getSelectedItem().equals("Venus")) {
                    this.SE = 75;
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
                }
                this.alpha = 0.0;
                this.repaint();
            }
        }
        return true;
    }
    
    public GeocentricMotion097() {
        this.versStr = "  0.97";
        this.str = "";
        this.left = 405;
        this.year = 365.2564;
        this.K = 0.017453292519943295;
        this.running = false;
        this.delay = 50;
        this.xSun = 160;
        this.ySun = 190;
        this.dimension = 1800;
        this.xEarth = 400;
        this.yEarth = 190;
        this.XX = new int[this.dimension];
        this.YY = new int[this.dimension];
        this.faktor = 0.5;
        this.planetStr = "";
        this.demo = true;
        this.online = false;
    }
}
