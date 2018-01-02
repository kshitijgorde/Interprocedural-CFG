import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Rectangle;
import java.util.Vector;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.net.URL;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class TalkImageApplet extends Applet implements Runnable
{
    String imageURLString;
    URL imageURL;
    Image image;
    int cycles;
    int cycleMinMillis;
    int cycleMaxMillis;
    int spaceMinMillis;
    int spaceMaxMillis;
    String fontName;
    String runmessage;
    Font defaultFont;
    int defFontSize;
    boolean randomize;
    Color bubbleBackground;
    Color bubbleForeground;
    Vector endpoints;
    Vector centerpoints;
    Vector wordbubbles;
    Vector fontSizes;
    Rectangle imageRect;
    WordBubble currentbubble;
    int currentMessageIndex;
    Thread animator;
    MediaTracker mt1;
    
    protected int randint(final int n, final int n2) {
        if (n == n2) {
            return n;
        }
        return n + (int)Math.floor(Math.random() * (n2 - n));
    }
    
    public void init() {
        this.imageURLString = null;
        this.imageURL = null;
        this.image = null;
        this.cycles = 0;
        this.cycleMinMillis = 1500;
        this.cycleMaxMillis = 5000;
        this.spaceMinMillis = 250;
        this.spaceMaxMillis = 1500;
        this.fontName = "Helvetica";
        this.defFontSize = 10;
        this.defaultFont = null;
        this.randomize = true;
        this.bubbleBackground = Color.white;
        this.bubbleForeground = Color.black;
        this.endpoints = new Vector();
        this.centerpoints = new Vector();
        this.wordbubbles = new Vector();
        this.fontSizes = new Vector();
        this.currentbubble = null;
        this.currentMessageIndex = -1;
        this.animator = null;
        this.imageRect = null;
        this.readParams();
        try {
            this.imageURL = new URL(this.imageURLString);
            this.image = this.getImage(this.imageURL);
            if (this.image == null) {
                System.out.println("\nCouldn't load image!" + this.imageURL);
            }
            (this.mt1 = new MediaTracker(this)).addImage(this.image, 1);
            if (this.mt1.isErrorAny()) {
                System.out.println("Media error occurred 1.");
                this.image = null;
            }
            this.mt1.waitForAll();
            if (this.mt1.isErrorAny()) {
                System.out.println("Media error occurred 2.");
                this.image = null;
            }
        }
        catch (Exception ex) {
            this.mt1 = null;
            System.out.println("Could not do image!");
            this.image = null;
        }
        this.defaultFont = new Font(this.fontName, 0, this.defFontSize);
        System.out.println("Font itself is " + this.defaultFont);
        this.show();
    }
    
    public void start() {
        System.out.println("In start...");
        for (int i = 0; i < this.wordbubbles.size(); ++i) {
            final WordBubble wordBubble = this.wordbubbles.elementAt(i);
            wordBubble.setParent(this);
            wordBubble.setTextFont(this.defaultFont);
            if (this.fontSizes.elementAt(i) != null) {
                final int int1 = Integer.parseInt(this.fontSizes.elementAt(i));
                if (int1 > 2 && int1 < 40) {
                    wordBubble.setTextFont(new Font(this.fontName, 0, int1));
                }
            }
            wordBubble.background = this.bubbleBackground;
            wordBubble.foreground = this.bubbleForeground;
        }
        if (this.image != null) {
            (this.imageRect = new Rectangle(0, 0, this.image.getWidth(this), this.image.getHeight(this))).translate((this.size().width - this.imageRect.width) / 2, this.size().height - this.imageRect.height);
        }
        this.repaint();
        (this.animator = new Thread(this)).start();
        this.showStatus(this.runmessage);
    }
    
    public void stop() {
        if (this.animator != null && this.animator.isAlive()) {
            this.animator.stop();
        }
        this.animator = null;
        this.showStatus("Applet done.");
    }
    
    public String getAppletInfo() {
        return "Talking Image Applet v1.0 (28 Dec 1997), by Neal Ziring";
    }
    
    public void destroy() {
        if (this.animator != null) {
            this.animator.stop();
        }
    }
    
    public void run() {
        try {
            Thread.sleep(this.spaceMaxMillis);
        }
        catch (Exception ex) {}
        final Graphics graphics = this.getGraphics();
        if (this.image != null) {
            do {
                int randint;
                int randint2;
                if (this.randomize) {
                    randint = this.randint(0, this.endpoints.size());
                    do {
                        randint2 = this.randint(0, this.wordbubbles.size());
                    } while (this.currentMessageIndex == randint2);
                }
                else {
                    randint = (this.currentMessageIndex + 1) % this.wordbubbles.size();
                    randint2 = (this.currentMessageIndex + 1) % this.wordbubbles.size();
                }
                Point point = this.endpoints.elementAt(randint % this.endpoints.size());
                if (point == null) {
                    point = this.endpoints.elementAt(0);
                }
                final Point endpoint = new Point(point.x + this.imageRect.x, point.y + this.imageRect.y);
                Point point2 = this.centerpoints.elementAt(randint % this.centerpoints.size());
                if (point2 == null) {
                    point2 = this.centerpoints.elementAt(0);
                }
                final Point centerpoint = new Point(point2.x + this.imageRect.x, point2.y + this.imageRect.y);
                this.currentbubble = (WordBubble)this.wordbubbles.elementAt(randint2);
                this.currentMessageIndex = randint2;
                this.currentbubble.setEndpoint(endpoint);
                this.currentbubble.setCenterpoint(centerpoint);
                this.currentbubble.draw(graphics);
                try {
                    Thread.sleep(this.randint(this.cycleMinMillis, this.cycleMaxMillis));
                }
                catch (Exception ex2) {}
                this.currentbubble.undraw(graphics);
                this.currentbubble = null;
                try {
                    Thread.sleep(this.randint(this.spaceMinMillis, this.spaceMaxMillis));
                }
                catch (Exception ex3) {}
                --this.cycles;
            } while (this.cycles != 0);
        }
        graphics.dispose();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.size().width;
        final int height = this.size().height;
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, width, this.imageRect.y);
        graphics.fillRect(0, 0, this.imageRect.x, height);
        if (this.image != null) {
            graphics.fillRect(0, 0, width, this.imageRect.y);
            graphics.fillRect(0, 0, this.imageRect.x, height);
            graphics.fillRect(this.imageRect.x + this.imageRect.width, this.imageRect.y, width, height);
            graphics.drawImage(this.image, this.imageRect.x, this.imageRect.y, this);
            if (this.currentbubble != null) {
                this.currentbubble.draw(graphics);
            }
        }
        else {
            graphics.fillRect(0, 0, width, height);
        }
    }
    
    public String getparm(final String s, final String s2) {
        final String parameter = this.getParameter(s);
        if (parameter != null) {
            return parameter;
        }
        return s2;
    }
    
    void readParams() {
        this.imageURLString = this.getParameter("imageURL");
        this.cycles = Integer.parseInt(this.getparm("cycles", "0"));
        this.runmessage = this.getparm("runmessage", this.getAppletInfo());
        this.cycleMinMillis = Integer.parseInt(this.getparm("cycleTimeMin", "1500"));
        if (this.cycleMinMillis < 50) {
            this.cycleMinMillis *= 1000;
        }
        this.cycleMaxMillis = Integer.parseInt(this.getparm("cycleTimeMax", "10000"));
        if (this.cycleMaxMillis < 250) {
            this.cycleMaxMillis *= 1000;
        }
        this.spaceMinMillis = Integer.parseInt(this.getparm("spaceTimeMin", "250"));
        if (this.spaceMinMillis < 50) {
            this.spaceMinMillis *= 1000;
        }
        this.spaceMaxMillis = Integer.parseInt(this.getparm("spaceTimeMax", "1500"));
        if (this.spaceMaxMillis < 150) {
            this.spaceMaxMillis *= 1000;
        }
        this.fontName = this.getparm("fontName", "Helvetica");
        this.randomize = Boolean.valueOf(this.getparm("randomize", "true"));
        this.defFontSize = Integer.parseInt(this.getparm("defFontSize", "11"));
        this.bubbleBackground = new Color(Integer.parseInt(this.getparm("wordbackground", "ffffff"), 16));
        this.bubbleForeground = new Color(Integer.parseInt(this.getparm("wordforeground", "0"), 16));
        this.setBackground(new Color(Integer.parseInt(this.getparm("background", "C0C0C0"), 16)));
        boolean b = true;
        int n = 0;
        while (b) {
            b = false;
            final String parameter = this.getParameter("message" + n);
            if (parameter != null) {
                this.wordbubbles.addElement(new WordBubble(parameter, this));
                b = true;
                final String parameter2 = this.getParameter("size" + n);
                if (parameter2 != null) {
                    this.fontSizes.addElement(parameter2);
                }
                else {
                    this.fontSizes.addElement("0");
                }
            }
            final String parameter3 = this.getParameter("endpoint" + n);
            if (parameter3 != null) {
                this.endpoints.addElement(this.pointFromString(parameter3));
                b = true;
            }
            final String parameter4 = this.getParameter("centerpoint" + n);
            if (parameter4 != null) {
                this.centerpoints.addElement(this.pointFromString(parameter4));
                b = true;
            }
            ++n;
        }
    }
    
    Point pointFromString(final String s) {
        int int1 = 0;
        int int2 = 0;
        final int index = s.indexOf(44);
        if (index > 0) {
            int1 = Integer.parseInt(s.substring(0, index).trim());
            int2 = Integer.parseInt(s.substring(index + 1).trim());
        }
        return new Point(int1, int2);
    }
}
