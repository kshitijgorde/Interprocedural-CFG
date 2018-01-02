import java.net.MalformedURLException;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ztbanner extends Applet implements Runnable
{
    Thread mythread;
    Image offI;
    Image left;
    Image right;
    Graphics offG;
    int xpos;
    int count;
    Font font;
    String m1;
    String m2;
    String m3;
    String m4;
    String m5;
    String text;
    String u1;
    String u2;
    String u3;
    String u4;
    String u5;
    Color colort;
    StringTokenizer t;
    URL url;
    
    public void init() {
        this.text = this.getParameter("textcolor");
        this.t = new StringTokenizer(this.text, ",");
        this.colort = new Color(Integer.parseInt(this.t.nextToken()), Integer.parseInt(this.t.nextToken()), Integer.parseInt(this.t.nextToken()));
        this.setBackground(Color.white);
        this.offI = this.createImage(this.size().width, this.size().height);
        this.offG = this.offI.getGraphics();
        this.left = this.getImage(this.getCodeBase(), "left.gif");
        this.right = this.getImage(this.getCodeBase(), "right.gif");
        this.font = new Font("Courier", 1, 24);
        this.offG.setFont(this.font);
        this.m1 = this.getParameter("message1");
        this.m2 = this.getParameter("message2");
        this.m3 = this.getParameter("message3");
        this.m4 = this.getParameter("message4");
        this.m5 = this.getParameter("message5");
        this.u1 = this.getParameter("link1");
        this.u2 = this.getParameter("link2");
        this.u3 = this.getParameter("link3");
        this.u4 = this.getParameter("link4");
        this.u5 = this.getParameter("link5");
    }
    
    public void paint(final Graphics graphics) {
        this.offG.setColor(this.colort);
        this.offG.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
        graphics.drawImage(this.offI, 0, 0, this);
    }
    
    public void start() {
        (this.mythread = new Thread(this)).start();
    }
    
    public void run() {
        while (true) {
            if (this.m1.length() != 0) {
                this.count = 1;
                this.mover(this.m1);
                this.movel();
            }
            if (this.m2.length() != 0) {
                this.count = 2;
                this.mover(this.m2);
                this.movel();
            }
            if (this.m3.length() != 0) {
                this.count = 3;
                this.mover(this.m3);
                this.movel();
            }
            if (this.m4.length() != 0) {
                this.count = 4;
                this.mover(this.m4);
                this.movel();
            }
            if (this.m5.length() != 0) {
                this.count = 5;
                this.mover(this.m5);
                this.movel();
            }
        }
    }
    
    public void stop() {
        this.mythread.stop();
    }
    
    public void mover(final String s) {
        this.xpos = -150;
        while (this.xpos < this.size().width + 70) {
            try {
                Thread.sleep(5L);
                this.offG.setColor(this.colort);
                this.offG.clearRect(0, 0, this.size().width, this.size().height);
                this.offG.drawString(s, 20, 33);
                this.offG.drawImage(this.right, this.xpos, 0, this.right.getWidth(this), this.right.getHeight(this), this);
                this.offG.setColor(Color.white);
                this.offG.fill3DRect(this.xpos + this.right.getWidth(this) - 1, 0, this.size().width + 100, this.size().height, true);
                ++this.xpos;
                this.repaint();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void movel() {
        while (this.xpos > -70) {
            try {
                Thread.sleep(5L);
                this.offG.clearRect(this.xpos, 0, this.size().width, this.size().height);
                this.offG.drawImage(this.left, this.xpos, 0, this.left.getWidth(this), this.left.getHeight(this), this);
                --this.xpos;
                this.repaint();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.showStatus("Unregistered version");
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus("Unregistered version");
        this.setCursor(new Cursor(12));
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.count == 1) {
            try {
                this.url = new URL(this.u1);
                this.getAppletContext().showDocument(this.url, "_blank");
            }
            catch (MalformedURLException ex) {}
        }
        if (this.count == 2) {
            try {
                this.url = new URL(this.u2);
                this.getAppletContext().showDocument(this.url, "_blank");
            }
            catch (MalformedURLException ex2) {}
        }
        if (this.count == 3) {
            try {
                this.url = new URL(this.u3);
                this.getAppletContext().showDocument(this.url, "_blank");
            }
            catch (MalformedURLException ex3) {}
        }
        if (this.count == 4) {
            try {
                this.url = new URL(this.u4);
                this.getAppletContext().showDocument(this.url, "_blank");
            }
            catch (MalformedURLException ex4) {}
        }
        if (this.count == 5) {
            try {
                this.url = new URL(this.u5);
                this.getAppletContext().showDocument(this.url, "_blank");
            }
            catch (MalformedURLException ex5) {}
        }
        return true;
    }
}
