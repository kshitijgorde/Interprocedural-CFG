import java.awt.image.ImageObserver;
import java.util.Enumeration;
import java.awt.event.MouseEvent;
import java.awt.Font;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class objectScroller extends Applet implements Runnable, MouseListener, MouseMotionListener
{
    int numberObjects;
    public Thread animationthread;
    Vector t;
    public int y;
    int swich;
    Graphics offscreen;
    public Image image;
    boolean firstTime;
    public Image pic;
    Color AppletBackground;
    String text;
    FontMetrics fm;
    int moveX;
    int moveY;
    int speed;
    int posY;
    int time;
    int pressY;
    int pressX;
    
    public objectScroller() {
        this.animationthread = null;
        this.y = 100;
        this.swich = 0;
        this.firstTime = true;
    }
    
    public String getAppletInfo() {
        return "Object Scroller applet programmed by Poonit Marketkar.........email me queries at poonit@yahoo.com";
    }
    
    public void init() {
        this.image = this.createImage(this.getSize().width, this.getSize().height);
        this.offscreen = this.image.getGraphics();
        this.setBackground(this.AppletBackground = new Color(Integer.parseInt(this.getParameter("backgroundColorR")), Integer.parseInt(this.getParameter("backgroundColorG")), Integer.parseInt(this.getParameter("backgroundColorB"))));
        this.initialiseObject();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    public void initialiseObject() {
        try {
            this.numberObjects = Integer.parseInt(this.getParameter("numberObjects"));
            this.t = new Vector(this.numberObjects);
            this.speed = Integer.parseInt(this.getParameter("sleeping_rate_of_frame"));
            this.time = Integer.parseInt(this.getParameter("wait"));
            for (int i = 0; i < this.numberObjects; ++i) {
                final String s = new String(this.getParameter("object" + (i + 1)));
                if (s.endsWith(".jpg") || s.endsWith(".gif")) {
                    final int int1 = Integer.parseInt(this.getParameter("far" + (i + 1)));
                    this.pic = this.getImage(this.getDocumentBase(), s);
                    try {
                        final URL url = new URL(new String(this.getParameter("url" + (i + 1))));
                        final String s2 = new String(this.getParameter("target" + (i + 1)));
                        if (i == 0) {
                            this.posY = this.getSize().height + int1;
                            this.t.addElement(new imageObject(this, 0, this.posY, this.pic, url, s2));
                        }
                        else {
                            this.posY += int1;
                            this.t.addElement(new imageObject(this, 0, this.posY, this.pic, url, s2));
                        }
                    }
                    catch (NullPointerException ex) {
                        this.showStatus(ex.toString());
                    }
                    catch (MalformedURLException ex2) {
                        this.showStatus(ex2.toString());
                    }
                }
                else {
                    final int int2 = Integer.parseInt(this.getParameter("far" + (i + 1)));
                    final String s3 = new String(this.getParameter("font" + (i + 1)));
                    final String s4 = new String(this.getParameter("style" + (i + 1)));
                    final int int3 = Integer.parseInt(this.getParameter("size" + (i + 1)));
                    if (s4.equals("PLAIN")) {
                        final Font font = new Font(s3, 0, int3);
                    }
                    Font font2;
                    if (s4.equals("ITALIC")) {
                        font2 = new Font(s3, 2, int3);
                    }
                    else {
                        font2 = new Font(s3, 1, int3);
                    }
                    final String s5 = new String(this.getParameter("url" + (i + 1)));
                    try {
                        final URL url2 = new URL(s5);
                        final String s6 = new String(this.getParameter("target" + (i + 1)));
                        final int int4 = Integer.parseInt(this.getParameter("colorStringR" + (i + 1)));
                        final int int5 = Integer.parseInt(this.getParameter("colorStringG" + (i + 1)));
                        final int int6 = Integer.parseInt(this.getParameter("colorStringB" + (i + 1)));
                        if (i == 0) {
                            this.posY = this.getSize().height + int2;
                            this.t.addElement(new textObject(this, 0, this.posY, s, font2, url2, s6, new Color(int4, int5, int6)));
                        }
                        else {
                            this.posY += int2;
                            this.t.addElement(new textObject(this, 0, this.posY, s, font2, url2, s6, new Color(int4, int5, int6)));
                        }
                    }
                    catch (NullPointerException ex3) {
                        this.showStatus(ex3.toString());
                    }
                    catch (MalformedURLException ex4) {
                        this.showStatus(ex4.toString());
                    }
                }
            }
        }
        catch (NullPointerException ex5) {
            this.showStatus("You have goofed up with the format for HTML !!!!!!!!");
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        this.moveY = mouseEvent.getY();
        this.moveX = mouseEvent.getX();
        final Enumeration<scrollingObject> elements = this.t.elements();
        while (elements.hasMoreElements()) {
            final scrollingObject scrollingObject = elements.nextElement();
            if (this.moveY > scrollingObject.locy && this.moveY < scrollingObject.locy + scrollingObject.fmHeight && this.moveX >= scrollingObject.locx && this.moveX <= scrollingObject.locx + scrollingObject.fmWidth) {
                this.showStatus("moveY" + this.moveY + "         d.locy " + scrollingObject.locy);
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.stop();
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.start();
        this.unpauseAll();
        this.showStatus(this.getAppletInfo());
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.moveY = mouseEvent.getY();
        this.moveX = mouseEvent.getX();
        final Enumeration<scrollingObject> elements = this.t.elements();
        while (elements.hasMoreElements()) {
            final scrollingObject scrollingObject = elements.nextElement();
            if (this.moveY > scrollingObject.locy && this.moveY < scrollingObject.locy + scrollingObject.fmHeight && this.moveX >= scrollingObject.locx && this.moveX <= scrollingObject.locx + scrollingObject.fmWidth) {
                this.start();
                scrollingObject.mouseOver = true;
                this.pauseAll();
                this.showStatus("URL:" + scrollingObject.url + "                Target Frame:" + scrollingObject.target);
            }
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.pressY = mouseEvent.getY();
        this.pressX = mouseEvent.getX();
        final Enumeration<scrollingObject> elements = this.t.elements();
        while (elements.hasMoreElements()) {
            final scrollingObject scrollingObject = elements.nextElement();
            if (this.pressY > scrollingObject.locy && this.pressY < scrollingObject.locy + scrollingObject.fmHeight && this.pressX >= scrollingObject.locx && this.pressX <= scrollingObject.locx + scrollingObject.fmWidth) {
                try {
                    this.getAppletContext().showDocument(scrollingObject.url, scrollingObject.target);
                }
                catch (NullPointerException ex) {
                    this.showStatus("URL is invalid !!");
                }
            }
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void paint(final Graphics graphics) {
        if (this.firstTime) {
            graphics.setColor(Color.blue);
            this.fm = graphics.getFontMetrics();
            final String s = "Object Scroller by Poonit Marketkar";
            graphics.drawString(s, this.getSize().width / 2 - this.fm.stringWidth(s) / 2, this.getSize().height / 2 - this.fm.getHeight() + this.fm.getDescent());
            this.firstTime = false;
            for (int i = 0; i < 100000000 * this.time; ++i) {}
        }
        if (this.t.firstElement().locy == 1) {
            for (int j = 0; j < 100000000 * this.time; ++j) {}
        }
        this.offscreen.setColor(this.AppletBackground);
        this.offscreen.fillRect(0, 0, this.getSize().width, this.getSize().height);
        final Enumeration<scrollingObject> elements = (Enumeration<scrollingObject>)this.t.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().paint(this.offscreen);
        }
        graphics.drawImage(this.image, 0, 0, this);
    }
    
    public void pauseAll() {
        final Enumeration<scrollingObject> elements = this.t.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().stopInc();
        }
    }
    
    public void run() {
        while (true) {
            this.repaint();
            try {
                Thread.sleep(this.speed);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void start() {
        if (this.animationthread == null) {
            (this.animationthread = new Thread(this, "animationthread")).start();
        }
    }
    
    public void stop() {
        if (this.animationthread != null && this.animationthread.isAlive()) {
            this.animationthread.stop();
        }
        this.animationthread = null;
    }
    
    public void unpauseAll() {
        final Enumeration<scrollingObject> elements = this.t.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().unstopInc();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
