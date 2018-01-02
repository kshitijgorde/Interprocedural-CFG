import java.applet.AppletContext;
import java.net.MalformedURLException;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.net.URL;
import java.util.StringTokenizer;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class zMessageGen extends Applet implements Runnable
{
    Graphics offG;
    Image offI;
    Image im1;
    Thread mythread;
    String gen;
    String[] a;
    String b;
    String mess1;
    String mess2;
    String mess3;
    String mess4;
    String mess5;
    String c1;
    String c2;
    String c3;
    String fs;
    String u1;
    String u2;
    String u3;
    String u4;
    String u5;
    int[] sub1;
    int[] sub2;
    int[] xpos1;
    int[] xpos2;
    int[] ypos1;
    int[] ypos2;
    int i;
    int ii;
    int tim;
    int f;
    int red;
    int green;
    int blue;
    int url_count;
    FontMetrics fm;
    Font font;
    Font fontup;
    Color color_random;
    Color color1;
    Color color2;
    Color color3;
    StringTokenizer st;
    URL url;
    MediaTracker mt;
    
    public void init() {
        this.mess1 = this.getParameter("message1");
        this.mess2 = this.getParameter("message2");
        this.mess3 = this.getParameter("message3");
        this.mess4 = this.getParameter("message4");
        this.mess5 = this.getParameter("message5");
        this.c1 = this.getParameter("color1");
        this.c2 = this.getParameter("color2");
        this.c3 = this.getParameter("color3");
        this.fs = this.getParameter("font_size");
        this.u1 = this.getParameter("link1");
        this.u2 = this.getParameter("link2");
        this.u3 = this.getParameter("link3");
        this.u4 = this.getParameter("link4");
        this.u5 = this.getParameter("link5");
        this.st = new StringTokenizer(this.c1, ",");
        this.color1 = new Color(Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()));
        this.st = new StringTokenizer(this.c2, ",");
        this.color2 = new Color(Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()));
        this.st = new StringTokenizer(this.c3, ",");
        this.color3 = new Color(Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()), Integer.parseInt(this.st.nextToken()));
        this.setBackground(Color.white);
        this.f = Integer.parseInt(this.fs);
        this.font = new Font("TimesRoman", 1, this.f);
        this.gen = "abcdfghijklmnoprstuyvuwz";
        this.ii = this.mess1.length();
        this.im1 = this.getImage(this.getCodeBase(), "book.jpg");
        this.offI = this.createImage(this.size().width, this.size().height);
        this.offG = this.offI.getGraphics();
        (this.mt = new MediaTracker(this)).addImage(this.im1, 0);
    }
    
    public void paint(final Graphics g) {
        if (this.mt.checkAll(true)) {
            g.drawImage(this.offI, 0, 0, this);
        }
        else {
            g.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
            g.drawString("Loading...", this.size().width / 2 - 30, this.size().height / 2);
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void start() {
        (this.mythread = new Thread(this)).start();
    }
    
    public void stop() {
        this.mythread.stop();
    }
    
    public void run() {
        try {
            this.mt.waitForAll();
        }
        catch (Exception ex) {}
        while (true) {
            this.tim = 0;
            this.generator();
            this.tim = 0;
            this.generator2(this.mess1, 1);
            this.tim = 0;
            this.message(this.mess1);
            this.tim = 0;
            this.antigenerator(this.mess1);
            this.tim = 0;
            this.generator();
            this.tim = 0;
            this.generator2(this.mess2, 2);
            this.tim = 0;
            this.message(this.mess2);
            this.tim = 0;
            this.antigenerator(this.mess2);
            this.tim = 0;
            this.generator();
            this.tim = 0;
            this.generator2(this.mess3, 3);
            this.tim = 0;
            this.message(this.mess3);
            this.tim = 0;
            this.antigenerator(this.mess3);
            this.tim = 0;
            this.generator();
            this.tim = 0;
            this.generator2(this.mess4, 4);
            this.tim = 0;
            this.message(this.mess4);
            this.tim = 0;
            this.antigenerator(this.mess4);
            this.tim = 0;
            this.generator();
            this.tim = 0;
            this.generator2(this.mess5, 5);
            this.tim = 0;
            this.message(this.mess5);
            this.tim = 0;
            this.antigenerator(this.mess5);
        }
    }
    
    public void generator() {
        while (this.tim < 50) {
            try {
                Thread.sleep(50L);
                this.sub1 = new int[23];
                this.xpos1 = new int[23];
                this.ypos1 = new int[23];
                this.a = new String[23];
                this.offG.clearRect(0, 0, this.size().width, this.size().height);
                this.offG.drawImage(this.im1, 0, 0, this.size().width, this.size().height, this);
                this.repaint();
                for (int i = 0; i < 23; ++i) {
                    this.sub1[i] = (int)(Math.random() * 24.0);
                    this.xpos1[i] = (int)(Math.random() * this.size().width);
                    this.ypos1[i] = (int)(Math.random() * this.size().height);
                    if (this.ypos1[i] < 20) {
                        this.ypos1[i] += 20;
                    }
                    if (this.xpos1[i] > this.size().width - 20) {
                        this.xpos1[i] -= 20;
                    }
                    if (this.xpos1[i] < 20) {
                        this.xpos1[i] += 10;
                    }
                    this.a[i] = this.gen.substring(this.sub1[i], this.sub1[i] + 1);
                }
                for (int i = 0; i < 23; ++i) {
                    this.f = Integer.parseInt(this.fs);
                    this.font = new Font("TimesRoman", 1, this.f);
                    this.offG.setFont(this.font);
                    this.red = (int)(Math.random() * 255.0);
                    this.green = (int)(Math.random() * 255.0);
                    this.blue = (int)(Math.random() * 255.0);
                    this.color_random = new Color(this.red, this.green, this.blue);
                    this.offG.setColor(this.color_random);
                    this.offG.drawString(this.a[i], this.xpos1[i], this.ypos1[i]);
                    this.repaint();
                }
                ++this.tim;
            }
            catch (Exception ex) {}
        }
    }
    
    public void generator2(final String s, final int in) {
        this.url_count = in;
        while (this.tim < s.length()) {
            try {
                Thread.sleep(50L);
                this.sub1 = new int[23];
                this.xpos1 = new int[23];
                this.ypos1 = new int[23];
                this.a = new String[23];
                this.offG.clearRect(0, 0, this.size().width, this.size().height);
                this.offG.drawImage(this.im1, 0, 0, this.size().width, this.size().height, this);
                this.repaint();
                for (int i = 0; i < 8; ++i) {
                    this.sub1[i] = (int)(Math.random() * 24.0);
                    this.xpos1[i] = (int)(Math.random() * this.size().width - 10.0);
                    this.ypos1[i] = (int)(Math.random() * this.size().height);
                    if (this.ypos1[i] < 15) {
                        this.ypos1[i] += 15;
                    }
                    if (this.xpos1[i] > this.size().width - 15) {
                        this.xpos1[i] -= 15;
                    }
                    if (this.xpos1[i] < 20) {
                        this.xpos1[i] += 10;
                    }
                    this.a[i] = this.gen.substring(this.sub1[i], this.sub1[i] + 1);
                }
                for (int i = 0; i < 8; ++i) {
                    this.red = (int)(Math.random() * 255.0);
                    this.green = (int)(Math.random() * 255.0);
                    this.blue = (int)(Math.random() * 255.0);
                    this.color_random = new Color(this.red, this.green, this.blue);
                    this.offG.setColor(this.color_random);
                    this.offG.drawString(this.a[i], this.xpos1[i], this.ypos1[i]);
                }
                this.fm = this.offG.getFontMetrics();
                this.offG.setColor(this.color1);
                this.offG.drawString(s.substring(0, this.tim), 15, 25);
                this.offG.setColor(this.color2);
                this.offG.drawString(s.substring(0, this.tim), (this.size().width - this.fm.stringWidth(s)) / 2, this.size().height / 2 + 5);
                this.offG.setColor(this.color3);
                this.offG.drawString(s.substring(0, this.tim), this.size().width - this.fm.stringWidth(s) - 10, this.size().height - 15);
                this.repaint();
                --this.ii;
                ++this.tim;
            }
            catch (Exception ex) {}
        }
    }
    
    public void message(final String s) {
        while (this.tim < 40) {
            try {
                Thread.sleep(100L);
                this.offG.clearRect(0, 0, this.size().width, this.size().height);
                this.offG.setFont(this.font);
                this.fm = this.offG.getFontMetrics();
                this.offG.drawImage(this.im1, 0, 0, this.size().width, this.size().height, this);
                this.offG.setColor(this.color1);
                this.offG.drawString(s, 15, 25);
                this.offG.setColor(this.color2);
                this.offG.drawString(s, (this.size().width - this.fm.stringWidth(s)) / 2, this.size().height / 2 + 5);
                this.offG.setColor(this.color3);
                this.offG.drawString(s, this.size().width - this.fm.stringWidth(s) - 10, this.size().height - 15);
                this.repaint();
                ++this.tim;
            }
            catch (Exception ex) {}
        }
    }
    
    public void antigenerator(final String s) {
        while (this.tim < 17) {
            try {
                Thread.sleep(10L);
                this.offG.clearRect(0, 0, this.size().width, this.size().height);
                --this.f;
                this.fontup = new Font("TimesRoman", 1, this.f);
                this.offG.setFont(this.fontup);
                this.offG.drawImage(this.im1, 0, 0, this.size().width, this.size().height, this);
                this.offG.setColor(this.color1);
                this.offG.drawString(s, 15, 20);
                this.offG.setColor(this.color2);
                this.offG.drawString(s, (this.size().width - this.fm.stringWidth(s)) / 2, this.size().height / 2 + 5);
                this.offG.setColor(this.color3);
                this.offG.drawString(s, this.size().width - this.fm.stringWidth(s) - 10, this.size().height - 10);
                this.repaint();
                ++this.tim;
            }
            catch (Exception ex) {}
        }
    }
    
    public boolean mouseEnter(final Event evt, final int x, final int y) {
        this.setCursor(new Cursor(12));
        return true;
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        if (this.url_count == 1) {
            try {
                this.url = new URL(this.u1);
                final AppletContext context = this.getAppletContext();
                context.showDocument(this.url, "blank");
            }
            catch (MalformedURLException ex) {}
        }
        if (this.url_count == 2) {
            try {
                this.url = new URL(this.u2);
                final AppletContext context = this.getAppletContext();
                context.showDocument(this.url, "blank");
            }
            catch (MalformedURLException ex2) {}
        }
        if (this.url_count == 3) {
            try {
                this.url = new URL(this.u3);
                final AppletContext context = this.getAppletContext();
                context.showDocument(this.url, "blank");
            }
            catch (MalformedURLException ex3) {}
        }
        if (this.url_count == 4) {
            try {
                this.url = new URL(this.u4);
                final AppletContext context = this.getAppletContext();
                context.showDocument(this.url, "blank");
            }
            catch (MalformedURLException ex4) {}
        }
        if (this.url_count == 5) {
            try {
                this.url = new URL(this.u5);
                final AppletContext context = this.getAppletContext();
                context.showDocument(this.url, "blank");
            }
            catch (MalformedURLException ex5) {}
        }
        return true;
    }
    
    public boolean mouseMove(final Event evt, final int x, final int y) {
        this.showStatus("Zmei Message Generator-Unregistered version....by Plamen Gelev");
        return true;
    }
}
