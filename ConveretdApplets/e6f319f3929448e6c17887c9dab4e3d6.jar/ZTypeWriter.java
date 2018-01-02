import java.awt.Cursor;
import java.net.MalformedURLException;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.awt.Color;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ZTypeWriter extends Applet implements Runnable
{
    Image offI;
    Graphics offG;
    Thread mythread;
    String str;
    String m1;
    String m2;
    String m3;
    String m4;
    String m5;
    String m6;
    String m7;
    String m8;
    String m9;
    String m10;
    String back;
    String text;
    String u1;
    String u2;
    String u3;
    String u4;
    String u5;
    String u6;
    String u7;
    String u8;
    String u9;
    String u10;
    int s;
    int count;
    Font font;
    StringTokenizer t1;
    StringTokenizer t2;
    Color colorb;
    Color colort;
    URL url;
    
    public void init() {
        this.back = this.getParameter("backgroundcolor");
        this.text = this.getParameter("textcolor");
        this.t1 = new StringTokenizer(this.back, ",");
        this.setBackground(this.colorb = new Color(Integer.parseInt(this.t1.nextToken()), Integer.parseInt(this.t1.nextToken()), Integer.parseInt(this.t1.nextToken())));
        this.t2 = new StringTokenizer(this.text, ",");
        this.colort = new Color(Integer.parseInt(this.t2.nextToken()), Integer.parseInt(this.t2.nextToken()), Integer.parseInt(this.t2.nextToken()));
        this.offI = this.createImage(this.size().width, this.size().height);
        this.offG = this.offI.getGraphics();
        this.font = new Font("TimesRoman", 1, 16);
        this.m1 = this.getParameter("message1");
        this.m2 = this.getParameter("message2");
        this.m3 = this.getParameter("message3");
        this.m4 = this.getParameter("message4");
        this.m5 = this.getParameter("message5");
        this.m6 = this.getParameter("message6");
        this.m7 = this.getParameter("message7");
        this.m8 = this.getParameter("message8");
        this.m9 = this.getParameter("message9");
        this.m10 = this.getParameter("message10");
        this.u1 = this.getParameter("link1");
        this.u2 = this.getParameter("link2");
        this.u3 = this.getParameter("link3");
        this.u4 = this.getParameter("link4");
        this.u5 = this.getParameter("link5");
        this.u6 = this.getParameter("link6");
        this.u7 = this.getParameter("link7");
        this.u8 = this.getParameter("link8");
        this.u9 = this.getParameter("link9");
        this.u10 = this.getParameter("link10");
    }
    
    public void paint(final Graphics graphics) {
        this.offG.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
        graphics.drawImage(this.offI, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void start() {
        (this.mythread = new Thread(this)).start();
    }
    
    public void run() {
        while (true) {
            if (this.m1.length() != 0) {
                this.count = 1;
                this.type(this.m1);
                this.pause();
                this.back();
            }
            if (this.m2.length() != 0) {
                this.count = 2;
                this.type(this.m2);
                this.pause();
                this.back();
            }
            if (this.m3.length() != 0) {
                this.count = 3;
                this.type(this.m3);
                this.pause();
                this.back();
            }
            if (this.m4.length() != 0) {
                this.count = 4;
                this.type(this.m4);
                this.pause();
                this.back();
            }
            if (this.m5.length() != 0) {
                this.count = 5;
                this.type(this.m5);
                this.pause();
                this.back();
            }
            if (this.m6.length() != 0) {
                this.count = 6;
                this.type(this.m6);
                this.pause();
                this.back();
            }
            if (this.m7.length() != 0) {
                this.count = 7;
                this.type(this.m7);
                this.pause();
                this.back();
            }
            if (this.m8.length() != 0) {
                this.count = 8;
                this.type(this.m8);
                this.pause();
                this.back();
            }
            if (this.m9.length() != 0) {
                this.count = 9;
                this.type(this.m9);
                this.pause();
                this.back();
            }
            if (this.m10.length() != 0) {
                this.count = 10;
                this.type(this.m10);
                this.pause();
                this.back();
            }
        }
    }
    
    public void stop() {
        this.mythread.stop();
    }
    
    public void type(final String s) {
        this.s = 0;
        try {
            while (this.s < s.length() + 1) {
                Thread.sleep(150L);
                this.play(this.getCodeBase(), "type.au");
                this.offG.setColor(this.colort);
                this.offG.setFont(this.font);
                this.offG.drawString(s.substring(0, this.s), 20, 20);
                ++this.s;
                this.repaint();
            }
        }
        catch (InterruptedException ex) {}
    }
    
    public void back() {
        int i = this.size().width;
        while (i > 0) {
            try {
                Thread.sleep(5L);
                this.offG.drawRect(i, 5, 3, this.size().height - 10);
                this.offG.clearRect(i + 2, 5, this.size().width - i, this.size().height - 9);
                this.play(this.getCodeBase(), "type.au");
                --i;
                this.repaint();
            }
            catch (InterruptedException ex) {}
        }
        this.offG.clearRect(0, 0, this.size().width, this.size().height);
        this.repaint();
    }
    
    public void pause() {
        int i = 0;
        while (i < 20) {
            try {
                Thread.sleep(100L);
                ++i;
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.showStatus("Unregistered version");
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
        if (this.count == 6) {
            try {
                this.url = new URL(this.u6);
                this.getAppletContext().showDocument(this.url, "_blank");
            }
            catch (MalformedURLException ex6) {}
        }
        if (this.count == 7) {
            try {
                this.url = new URL(this.u7);
                this.getAppletContext().showDocument(this.url, "_blank");
            }
            catch (MalformedURLException ex7) {}
        }
        if (this.count == 8) {
            try {
                this.url = new URL(this.u8);
                this.getAppletContext().showDocument(this.url, "_blank");
            }
            catch (MalformedURLException ex8) {}
        }
        if (this.count == 9) {
            try {
                this.url = new URL(this.u9);
                this.getAppletContext().showDocument(this.url, "_blank");
            }
            catch (MalformedURLException ex9) {}
        }
        if (this.count == 10) {
            try {
                this.url = new URL(this.u10);
                this.getAppletContext().showDocument(this.url, "_blank");
            }
            catch (MalformedURLException ex10) {}
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus("Unregistered version");
        this.setCursor(new Cursor(12));
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.setCursor(new Cursor(0));
        return true;
    }
}
