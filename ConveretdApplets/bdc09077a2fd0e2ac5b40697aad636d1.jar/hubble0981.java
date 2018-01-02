import java.awt.Event;
import java.awt.Graphics;
import java.net.URL;
import java.awt.Component;
import java.awt.Panel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Choice;
import java.awt.Checkbox;
import java.awt.Button;
import java.util.Date;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class hubble0981 extends Applet
{
    String versStr;
    Date dat;
    Button buttonLeft;
    Button buttonRight;
    Button buttonUp;
    Button buttonDown;
    Button buttonStartStop;
    Checkbox box;
    Checkbox lineBox;
    Choice numberStarsChoice;
    Choice factorChoice;
    Choice radiusChoice;
    String myStr;
    boolean demo;
    boolean online;
    public String email;
    public String param;
    public String wwwStr;
    public String usrStr;
    public String userString;
    int[] xArray;
    int[] yArray;
    int[] x95Array;
    int[] y95Array;
    double xRand;
    double yRand;
    int xRandInt;
    int yRandInt;
    int myWidth;
    int myHeight;
    int xM95;
    int yM95;
    int xM;
    int yM;
    double factor;
    int oben;
    boolean black;
    boolean line;
    int number;
    int radius;
    
    public int formula(final String str, final int len) {
        long num = 0L;
        for (int i = 0; i < len; ++i) {
            final char c = str.charAt(i);
            long n = i * Character.digit(c, i) * Character.digit(c, 36 - i);
            n = Character.digit(c, 36 - i);
            num += n * n;
        }
        return (int)num + 12456;
    }
    
    public void init() {
        this.setBackground(Color.white);
        this.setFont(new Font("Helvetica", 0, 12));
        this.myWidth = this.size().width;
        this.myHeight = this.size().height - this.oben;
        this.xM = this.size().width / 2;
        this.yM = this.myHeight / 2;
        this.doRandom();
        final URL url = this.getDocumentBase();
        this.myStr = url.toString();
        this.myStr = String.valueOf(this.myStr) + "1234567890123456789012345";
        this.wwwStr = this.myStr.substring(0, 27);
        final Panel panel = new Panel();
        this.add("Buttons", panel);
        (this.factorChoice = new Choice()).addItem("1.00");
        this.factorChoice.addItem("1.02");
        this.factorChoice.addItem("1.04");
        this.factorChoice.addItem("1.06");
        this.factorChoice.addItem("1.08");
        this.factorChoice.addItem("1.10");
        this.factorChoice.addItem("1.12");
        this.factorChoice.addItem("1.14");
        this.factorChoice.addItem("1.16");
        this.factorChoice.addItem("1.18");
        this.factorChoice.addItem("1.20");
        this.factorChoice.select("1.10");
        panel.add(this.factorChoice);
        (this.numberStarsChoice = new Choice()).addItem("250 stars");
        this.numberStarsChoice.addItem("500 stars");
        this.numberStarsChoice.addItem("1000 stars");
        this.numberStarsChoice.addItem("1500 stars");
        this.numberStarsChoice.addItem("2000 stars");
        this.numberStarsChoice.addItem("2500 stars");
        this.numberStarsChoice.addItem("3000 stars");
        this.numberStarsChoice.select("1500 stars");
        panel.add(this.numberStarsChoice);
        (this.buttonLeft = new Button()).setLabel("< left");
        panel.add(this.buttonLeft);
        (this.buttonRight = new Button()).setLabel("right >");
        panel.add(this.buttonRight);
        (this.buttonUp = new Button()).setLabel("up");
        panel.add(this.buttonUp);
        (this.buttonDown = new Button()).setLabel("down");
        panel.add(this.buttonDown);
        (this.buttonStartStop = new Button()).setLabel("new/reset");
        panel.add(this.buttonStartStop);
        (this.radiusChoice = new Choice()).addItem("radius 1");
        this.radiusChoice.addItem("radius 2");
        this.radiusChoice.addItem("radius 3");
        panel.add(this.radiusChoice);
        this.radiusChoice.select("radius 2");
        panel.add(this.box = new Checkbox());
        this.box.setState(true);
        panel.add(this.lineBox = new Checkbox());
        this.lineBox.setState(true);
        boolean ok = true;
        this.email = this.getParameter("email");
        this.param = this.getParameter("password");
        this.usrStr = this.email;
        this.userString = this.email;
        if (this.formula(this.wwwStr, 22) == this.formula("http://www.GeoAstro.de", 22) || this.formula(this.wwwStr, 24) == this.formula("http://www.venus-transit", 24) || this.formula(this.wwwStr, 21) == this.formula("http://www.jgiesen.de", 21)) {
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
    }
    
    public void paint(final Graphics g) {
        g.setColor(Color.red);
        g.drawRect(2, this.oben, this.size().width - 4, this.size().height - this.oben - 2);
        g.setColor(Color.black);
        g.drawLine(this.xM - 10, this.oben + this.yM, this.xM + 10, this.oben + this.yM);
        g.drawLine(this.xM - 10, this.oben + this.yM - 1, this.xM + 10, this.oben + this.yM - 1);
        g.drawLine(this.xM - 10, this.oben + this.yM + 1, this.xM + 10, this.oben + this.yM + 1);
        g.drawLine(this.xM, this.oben + this.yM - 10, this.xM, this.oben + this.yM + 10);
        g.drawLine(this.xM - 1, this.oben + this.yM - 10, this.xM - 1, this.oben + this.yM + 10);
        g.drawLine(this.xM + 1, this.oben + this.yM - 10, this.xM + 1, this.oben + this.yM + 10);
        for (int i = 1; i < this.number; ++i) {
            g.fillOval(this.xArray[i] - this.radius, this.oben + this.yArray[i] - this.radius, 2 * this.radius, 2 * this.radius);
        }
        g.setColor(Color.red);
        g.drawLine(this.xM95 - 10, this.oben + this.yM95, this.xM95 + 10, this.oben + this.yM95);
        g.drawLine(this.xM95 - 10, this.oben + this.yM95 - 1, this.xM95 + 10, this.oben + this.yM95 - 1);
        g.drawLine(this.xM95 - 10, this.oben + this.yM95 + 1, this.xM95 + 10, this.oben + this.yM95 + 1);
        g.drawLine(this.xM95, this.oben + this.yM95 - 10, this.xM95, this.oben + this.yM95 + 10);
        g.drawLine(this.xM95 - 1, this.oben + this.yM95 - 10, this.xM95 - 1, this.oben + this.yM95 + 10);
        g.drawLine(this.xM95 + 1, this.oben + this.yM95 - 10, this.xM95 + 1, this.oben + this.yM95 + 10);
        if (!this.black) {
            g.setColor(Color.red);
        }
        else {
            g.setColor(Color.black);
        }
        for (int j = 1; j < this.number; ++j) {
            g.fillOval(this.x95Array[j] - this.radius, this.oben + this.y95Array[j] - this.radius, 2 * this.radius, 2 * this.radius);
        }
        if (this.line) {
            for (int k = 1; k < this.number; ++k) {
                g.drawLine(this.x95Array[k], this.oben + this.y95Array[k], this.xArray[k], this.oben + this.yArray[k]);
            }
        }
        g.clearRect(2, 2, this.size().width - 2, this.oben - 2);
        g.setColor(Color.black);
        g.drawRect(1, 1, this.size().width - 2, this.size().height - 2);
        g.drawString("red", this.size().width - 75, 55);
        g.drawString("line", this.size().width - 50, 55);
        g.drawString("Expansion", 40, 55);
        g.setColor(Color.blue);
        g.setFont(new Font("Helvetica", 0, 10));
        g.drawString(this.versStr, this.xM - 150, 54);
        if (this.demo) {
            final Font f = g.getFont();
            g.setColor(Color.red);
            g.setFont(new Font("Chicago", 0, 120));
            g.drawString("D", 70, 230);
            g.drawString("E", 170, 310);
            g.drawString("M", 270, 390);
            g.drawString("O", 450, 470);
            g.setFont(f);
        }
    }
    
    void doLeft() {
        for (int i = 1; i < this.number; ++i) {
            --this.x95Array[i];
        }
        --this.xM95;
    }
    
    void doRight() {
        for (int i = 1; i < this.number; ++i) {
            ++this.x95Array[i];
        }
        ++this.xM95;
    }
    
    void doUp() {
        for (int i = 1; i < this.number; ++i) {
            --this.y95Array[i];
        }
        --this.yM95;
    }
    
    void doDown() {
        for (int i = 1; i < this.number; ++i) {
            ++this.y95Array[i];
        }
        ++this.yM95;
    }
    
    void doRandom() {
        final double b = 0.5 * this.myWidth * (this.factor - 1.0);
        final double c = 0.5 * this.myHeight * (this.factor - 1.0);
        this.xM95 = (int)Math.round(this.xM * this.factor - b);
        this.yM95 = (int)Math.round(this.yM * this.factor - c);
        for (int i = 1; i < this.number; ++i) {
            this.xRand = this.myWidth * Math.random();
            this.xRandInt = (int)Math.round(this.xRand);
            this.xArray[i] = this.xRandInt;
            this.yRand = (this.size().height - this.oben) * Math.random();
            this.yRandInt = (int)Math.round(this.yRand);
            this.yArray[i] = this.yRandInt;
        }
        final double a = 0.5 * this.myWidth * (1.0 - this.factor);
        final double aa = 0.5 * this.myHeight * (1.0 - this.factor);
        for (int j = 1; j < this.number; ++j) {
            this.xRandInt = (int)Math.round(a + this.factor * this.xArray[j]);
            this.x95Array[j] = this.xRandInt;
            this.yRandInt = (int)Math.round(aa + this.factor * this.yArray[j]);
            this.y95Array[j] = this.yRandInt;
        }
    }
    
    public boolean action(final Event event, final Object eventobject) {
        if (event.target instanceof Button) {
            if (event.target == this.buttonLeft) {
                this.doLeft();
            }
            if (event.target == this.buttonRight) {
                this.doRight();
            }
            if (event.target == this.buttonUp) {
                this.doUp();
            }
            if (event.target == this.buttonDown) {
                this.doDown();
            }
            if (event.target == this.buttonStartStop) {
                this.doRandom();
            }
            this.repaint();
        }
        if (event.target instanceof Choice) {
            if (event.target == this.factorChoice) {
                if (this.factorChoice.getSelectedItem().equals("1.00")) {
                    this.factor = 1.0;
                }
                if (this.factorChoice.getSelectedItem().equals("1.02")) {
                    this.factor = 1.02;
                }
                if (this.factorChoice.getSelectedItem().equals("1.04")) {
                    this.factor = 1.04;
                }
                if (this.factorChoice.getSelectedItem().equals("1.06")) {
                    this.factor = 1.06;
                }
                if (this.factorChoice.getSelectedItem().equals("1.08")) {
                    this.factor = 1.08;
                }
                if (this.factorChoice.getSelectedItem().equals("1.10")) {
                    this.factor = 1.1;
                }
                if (this.factorChoice.getSelectedItem().equals("1.12")) {
                    this.factor = 1.12;
                }
                if (this.factorChoice.getSelectedItem().equals("1,14")) {
                    this.factor = 1.14;
                }
                if (this.factorChoice.getSelectedItem().equals("1,16")) {
                    this.factor = 1.16;
                }
                if (this.factorChoice.getSelectedItem().equals("1.18")) {
                    this.factor = 1.18;
                }
                if (this.factorChoice.getSelectedItem().equals("1.20")) {
                    this.factor = 1.2;
                }
                this.doRandom();
                this.repaint();
            }
            if (event.target == this.radiusChoice) {
                if (this.radiusChoice.getSelectedItem().equals("radius 1")) {
                    this.radius = 1;
                }
                if (this.radiusChoice.getSelectedItem().equals("radius 2")) {
                    this.radius = 2;
                }
                if (this.radiusChoice.getSelectedItem().equals("radius 3")) {
                    this.radius = 3;
                }
                this.repaint();
            }
            if (event.target == this.numberStarsChoice) {
                if (this.numberStarsChoice.getSelectedItem().equals("250 stars")) {
                    this.number = 250;
                }
                if (this.numberStarsChoice.getSelectedItem().equals("500 stars")) {
                    this.number = 500;
                }
                if (this.numberStarsChoice.getSelectedItem().equals("1000 stars")) {
                    this.number = 1000;
                }
                if (this.numberStarsChoice.getSelectedItem().equals("1500 stars")) {
                    this.number = 1500;
                }
                if (this.numberStarsChoice.getSelectedItem().equals("2000 stars")) {
                    this.number = 2000;
                }
                if (this.numberStarsChoice.getSelectedItem().equals("2500 stars")) {
                    this.number = 2500;
                }
                if (this.numberStarsChoice.getSelectedItem().equals("3000 stars")) {
                    this.number = 3000;
                }
                this.doRandom();
                this.repaint();
            }
        }
        if (event.target instanceof Checkbox) {
            if (event.target == this.box) {
                if (!(this.black ^= true)) {
                    this.box.setState(true);
                }
                this.repaint();
            }
            if (event.target == this.lineBox) {
                this.line ^= true;
                if (this.line) {
                    this.lineBox.setState(true);
                }
                this.repaint();
            }
        }
        return true;
    }
    
    public hubble0981() {
        this.versStr = "  Hubble Applet 0.981  -  © 2001-2008 J.Giesen - www.GeoAstro.de";
        this.demo = true;
        this.online = false;
        this.xArray = new int[3001];
        this.yArray = new int[3001];
        this.x95Array = new int[3001];
        this.y95Array = new int[3001];
        this.factor = 1.1;
        this.oben = 60;
        this.black = false;
        this.line = true;
        this.number = 1500;
        this.radius = 2;
    }
}
