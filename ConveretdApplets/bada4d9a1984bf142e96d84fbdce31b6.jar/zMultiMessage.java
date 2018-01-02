import java.applet.AppletContext;
import java.net.MalformedURLException;
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

public class zMultiMessage extends Applet implements Runnable
{
    Thread mythread;
    Image offI;
    Graphics offG;
    int y1;
    int x1;
    int x2;
    int y2;
    int br;
    int p;
    Font font;
    String text1;
    String text2;
    String text3;
    String text4;
    String text5;
    String text6;
    String text7;
    String text8;
    String tc1;
    String tc2;
    String tc3;
    String tc4;
    String bgc;
    String brc;
    Color tcolor1;
    Color tcolor2;
    Color tcolor3;
    Color tcolor4;
    Color bgcolor;
    Color brcolor;
    StringTokenizer st1;
    StringTokenizer st2;
    StringTokenizer st3;
    StringTokenizer st4;
    StringTokenizer stbgc;
    StringTokenizer stbrc;
    URL url;
    
    public void init() {
        this.bgc = this.getParameter("backgroundcolor");
        this.stbgc = new StringTokenizer(this.bgc, ",");
        this.setBackground(this.bgcolor = new Color(Integer.parseInt(this.stbgc.nextToken()), Integer.parseInt(this.stbgc.nextToken()), Integer.parseInt(this.stbgc.nextToken())));
        this.brc = this.getParameter("bordercolor");
        this.stbrc = new StringTokenizer(this.brc, ",");
        this.brcolor = new Color(Integer.parseInt(this.stbrc.nextToken()), Integer.parseInt(this.stbrc.nextToken()), Integer.parseInt(this.stbrc.nextToken()));
        this.tc1 = this.getParameter("textcolor1");
        this.st1 = new StringTokenizer(this.tc1, ",");
        this.tcolor1 = new Color(Integer.parseInt(this.st1.nextToken()), Integer.parseInt(this.st1.nextToken()), Integer.parseInt(this.st1.nextToken()));
        this.tc2 = this.getParameter("textcolor2");
        this.st2 = new StringTokenizer(this.tc2, ",");
        this.tcolor2 = new Color(Integer.parseInt(this.st2.nextToken()), Integer.parseInt(this.st2.nextToken()), Integer.parseInt(this.st2.nextToken()));
        this.tc3 = this.getParameter("textcolor3");
        this.st3 = new StringTokenizer(this.tc3, ",");
        this.tcolor3 = new Color(Integer.parseInt(this.st3.nextToken()), Integer.parseInt(this.st3.nextToken()), Integer.parseInt(this.st3.nextToken()));
        this.tc4 = this.getParameter("textcolor4");
        this.st4 = new StringTokenizer(this.tc4, ",");
        this.tcolor4 = new Color(Integer.parseInt(this.st4.nextToken()), Integer.parseInt(this.st4.nextToken()), Integer.parseInt(this.st4.nextToken()));
        this.offI = this.createImage(this.size().width, this.size().height);
        this.offG = this.offI.getGraphics();
        this.font = new Font("TimesRoman", 0, 18);
        this.offG.setFont(this.font);
        this.text1 = this.getParameter("text1");
        this.text2 = this.getParameter("text2");
        this.text3 = this.getParameter("text3");
        this.text4 = this.getParameter("text4");
        this.text5 = this.getParameter("text5");
        this.text6 = this.getParameter("text6");
        this.text7 = this.getParameter("text7");
        this.text8 = this.getParameter("text8");
        this.y1 = this.size().height + 20;
        this.x1 = -this.size().width;
        this.x2 = this.size().width;
        this.y2 = -10;
    }
    
    public void paint(final Graphics g) {
        this.offG.setColor(this.brcolor);
        this.offG.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
        g.drawImage(this.offI, 0, 0, this);
    }
    
    public void start() {
        (this.mythread = new Thread(this)).start();
    }
    
    public void run() {
        while (true) {
            this.in(this.text1, this.text2, this.text3, this.text4);
            this.pause();
            this.out(this.text1, this.text2, this.text3, this.text4);
            this.in(this.text5, this.text6, this.text7, this.text8);
            this.pause();
            this.out(this.text5, this.text6, this.text7, this.text8);
        }
    }
    
    public void stop() {
        this.mythread.stop();
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void in(final String str1, final String str2, final String str3, final String str4) {
        while (this.y1 > 30) {
            try {
                Thread.sleep(1L);
                this.offG.clearRect(0, 0, this.size().width, this.size().height);
                this.offG.setColor(this.tcolor1);
                this.offG.drawString(str1, 10, this.y1);
                --this.y1;
                this.repaint();
            }
            catch (Exception ex) {}
        }
        while (this.x1 < 10) {
            try {
                Thread.sleep(1L);
                this.offG.clearRect(0, 0, this.size().width, this.size().height);
                this.offG.setColor(this.tcolor1);
                this.offG.drawString(str1, 10, this.y1);
                this.offG.setColor(this.tcolor2);
                this.offG.drawString(str2, this.x1, 60);
                ++this.x1;
                this.repaint();
            }
            catch (Exception ex2) {}
        }
        while (this.x2 > 10) {
            try {
                Thread.sleep(1L);
                this.offG.clearRect(0, 0, this.size().width, this.size().height);
                this.offG.setColor(this.tcolor1);
                this.offG.drawString(str1, 10, this.y1);
                this.offG.setColor(this.tcolor2);
                this.offG.drawString(str2, this.x1, 60);
                this.offG.setColor(this.tcolor3);
                this.offG.drawString(str3, this.x2, 90);
                --this.x2;
                this.repaint();
            }
            catch (Exception ex3) {}
        }
        while (this.y2 < 120) {
            try {
                Thread.sleep(1L);
                this.offG.clearRect(0, 0, this.size().width, this.size().height);
                this.offG.setColor(this.tcolor1);
                this.offG.drawString(str1, 10, this.y1);
                this.offG.setColor(this.tcolor2);
                this.offG.drawString(str2, this.x1, 60);
                this.offG.setColor(this.tcolor3);
                this.offG.drawString(str3, this.x2, 90);
                this.offG.setColor(this.tcolor4);
                this.offG.drawString(str4, 10, this.y2);
                ++this.y2;
                this.repaint();
            }
            catch (Exception ex4) {}
        }
    }
    
    public void out(final String str1, final String str2, final String str3, final String str4) {
        this.br = 0;
        while (this.br < this.size().width + this.size().height) {
            try {
                Thread.sleep(1L);
                this.offG.clearRect(0, 0, this.size().width, this.size().height);
                this.offG.setColor(this.tcolor1);
                this.offG.drawString(str1, 10, this.y1);
                this.offG.setColor(this.tcolor2);
                this.offG.drawString(str2, this.x1, 60);
                this.offG.setColor(this.tcolor3);
                this.offG.drawString(str3, this.x2, 90);
                this.offG.setColor(this.tcolor4);
                this.offG.drawString(str4, 10, this.y2);
                ++this.y1;
                --this.x1;
                ++this.x2;
                --this.y2;
                ++this.br;
                this.repaint();
            }
            catch (Exception ex) {}
        }
    }
    
    public void pause() {
        this.p = 0;
        while (this.p < 30) {
            try {
                Thread.sleep(100L);
                ++this.p;
            }
            catch (Exception ex) {}
        }
    }
    
    public boolean mouseMove(final Event evt, final int x, final int y) {
        this.showStatus("Applet by Plamen Gelev-Unregistered");
        return true;
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        try {
            this.url = new URL("http://geocities.com/zmei666/applets.htm");
            final AppletContext context = this.getAppletContext();
            context.showDocument(this.url, "_blank");
        }
        catch (MalformedURLException ex) {}
        return true;
    }
}
