import java.awt.Font;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.awt.Event;
import java.net.URL;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class picframe extends Applet implements Runnable
{
    Thread runner;
    Image Buffer;
    Image picture;
    Graphics gBuffer;
    int pos;
    int wide;
    int high;
    int BELT;
    int CROP;
    int guns;
    int FONTCOLOR;
    int x;
    int z;
    int b;
    int h;
    int p;
    int o;
    int era;
    int erb;
    int erc;
    int len1;
    int len2;
    int len3;
    int td;
    int threed;
    Color bgColor;
    Color tack;
    Color shadow;
    Color cord;
    float colorR;
    float colorG;
    float colorB;
    int Ro;
    int Go;
    int Bo;
    int fontsize;
    String onmouse1;
    String onmouse2;
    String onmouse3;
    String ermsg;
    String s;
    String MESSAGE1;
    String MESSAGE2;
    String MESSAGE3;
    String TARGET;
    URL url;
    
    public boolean mouseMove(final Event event, int era, final int erb) {
        this.era = era;
        this.erb = erb;
        final int n = era;
        era = 1;
        if (n <= this.td + this.BELT) {
            era = 0;
        }
        if (n >= this.wide - this.BELT) {
            era = 0;
        }
        if (erb < this.td + this.BELT) {
            era = 0;
        }
        if (erb > this.high - this.BELT) {
            era = 0;
        }
        if (era == 1) {
            this.onmouse1 = this.MESSAGE1;
            this.onmouse2 = this.MESSAGE2;
            this.onmouse3 = this.MESSAGE3;
            this.showStatus(this.url.toString());
        }
        else {
            this.onmouse1 = "";
            this.onmouse2 = "";
            this.showStatus(this.onmouse3 = "");
        }
        this.repaint(100L);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.onmouse1 = "";
        this.onmouse2 = "";
        this.showStatus(this.onmouse3 = "");
        this.repaint();
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.repaint();
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        this.getAppletContext().showDocument(this.url, this.TARGET);
        return true;
    }
    
    public void init() {
        this.Buffer = this.createImage(this.size().width, this.size().height);
        this.gBuffer = this.Buffer.getGraphics();
        this.wide = this.size().width - 1;
        this.p = this.wide + 1;
        this.high = this.size().height - 1;
        this.h = this.high + 1;
        this.picture = this.getImage(this.getCodeBase(), this.getParameter("IMAGE"));
        if (this.getParameter("MESSAGE1") != null) {
            this.MESSAGE1 = this.getParameter("MESSAGE1");
            this.len1 = this.MESSAGE1.length();
        }
        if (this.getParameter("MESSAGE2") != null) {
            this.MESSAGE2 = this.getParameter("MESSAGE2");
            this.len2 = this.MESSAGE2.length();
        }
        if (this.getParameter("MESSAGE3") != null) {
            this.MESSAGE3 = this.getParameter("MESSAGE3");
            this.len3 = this.MESSAGE3.length();
        }
        this.BELT = 2;
        if (this.getParameter("BELT") != null) {
            this.BELT = Integer.parseInt(this.getParameter("BELT"));
        }
        this.b = this.BELT - 1;
        this.td = 0;
        this.threed = 0;
        if (this.getParameter("3d") != null) {
            this.threed = Integer.parseInt(this.getParameter("3d"));
        }
        if (this.threed == 1) {
            this.td = 20;
            this.s = this.getParameter("bgColor");
            if (this.s != null) {
                this.bgColor = new Color(Integer.parseInt(this.s, 16));
            }
            this.gBuffer.setColor(this.bgColor);
            this.gBuffer.fillRect(0, 0, this.size().width, this.size().height);
        }
        this.FONTCOLOR = 1;
        if (this.getParameter("FONTCOLOR") != null) {
            this.FONTCOLOR = Integer.parseInt(this.getParameter("FONTCOLOR"));
        }
        this.CROP = 1;
        if (this.getParameter("CROP") != null) {
            this.CROP = Integer.parseInt(this.getParameter("CROP"));
        }
        if (this.CROP != 1) {
            this.CROP = 0;
        }
        if (this.CROP == 0) {
            this.pos = this.BELT;
        }
        this.s = this.getParameter("LINK");
        if (this.s != null) {
            try {
                this.url = new URL(this.getDocumentBase(), this.s);
            }
            catch (MalformedURLException ex) {
                this.onmouse1 = "URL error";
            }
            this.TARGET = this.getParameter("TARGET");
        }
        this.guns = 1;
        if (this.getParameter("COLOR") != null) {
            this.guns = Integer.parseInt(this.getParameter("COLOR"));
        }
        if (this.guns == 7) {
            this.Ro = 0;
            this.Go = 0;
        }
        if (this.guns == 6) {
            this.Ro = 0;
            this.Bo = 0;
        }
        if (this.guns == 5) {
            this.Ro = 0;
        }
        if (this.guns == 4) {
            this.Go = 0;
            this.Bo = 0;
        }
        if (this.guns == 3) {
            this.Go = 0;
        }
        if (this.guns == 2) {
            this.Bo = 0;
        }
        this.len1 = (this.wide - this.td - this.BELT * 2) / 2 + (this.BELT + this.td) - this.len1 * 6 / 2;
        if (this.len1 < this.BELT + this.td) {
            this.len1 = this.BELT + this.td;
        }
        this.len2 = (this.wide - this.td - this.BELT * 2) / 2 + (this.BELT + this.td) - this.len2 * 6 / 2;
        if (this.len2 < this.BELT + this.td) {
            this.len2 = this.BELT + this.td;
        }
        this.len3 = (this.wide - this.td - this.BELT * 2) / 2 + (this.BELT + this.td) - this.len3 * 6 / 2;
        if (this.len3 < this.BELT + this.td) {
            this.len3 = this.BELT + this.td;
        }
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(100L);
            }
            catch (Exception ex) {}
            this.gBuffer.drawImage(this.picture, this.pos + this.td, this.pos + this.td, this);
            this.drawmessage();
            this.drawframe();
            if (this.td != 0) {
                this.drawshadow();
                this.drawcord();
            }
            this.repaint();
        }
    }
    
    public void drawshadow() {
        this.gBuffer.setColor(this.shadow);
        this.x = 0;
        while (this.x < 15) {
            this.gBuffer.drawLine(19, this.high, this.x + 5, 40);
            ++this.x;
        }
    }
    
    public void drawcord() {
        this.gBuffer.setColor(this.cord);
        final int n = (this.wide - 20) / 4;
        final int n2 = 20 + n;
        final int n3 = this.wide - n;
        final int n4 = (this.wide - 20) / 2 + 20;
        this.gBuffer.drawLine(n2, 19, n4, 1);
        this.gBuffer.drawLine(n3, 19, n4, 1);
        this.gBuffer.setColor(new Color((int)this.colorR, (int)this.colorG, (int)this.colorB));
        this.gBuffer.drawLine(n4 - 1, 0, n4 + 1, 0);
        this.gBuffer.drawLine(n4 - 2, 1, n4 + 2, 1);
        this.gBuffer.drawLine(n4 - 1, 2, n4 + 1, 2);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.Buffer, 0, 0, this);
    }
    
    public void drawframe() {
        this.x = 0;
        while (this.x < this.BELT) {
            this.colorR = this.x * 255.0f / this.BELT * this.Ro;
            this.colorG = this.x * 255.0f / this.BELT * this.Go;
            this.colorB = this.x * 255.0f / this.BELT * this.Bo;
            this.gBuffer.setColor(new Color((int)this.colorR, (int)this.colorG, (int)this.colorB));
            this.gBuffer.drawLine(this.x + this.td, this.x + this.td, this.x + this.td, this.high - this.x);
            this.gBuffer.drawLine(this.b - this.x + 1 + this.td, this.b - this.x + this.td, this.wide - this.b + this.x + this.td, this.b - this.x + this.td);
            this.gBuffer.drawLine(this.wide - this.b + this.x, this.b - this.x + this.td, this.wide - this.b + this.x, this.high - this.b + this.x);
            this.gBuffer.drawLine(this.x + this.td, this.high - this.x, this.wide - this.x, this.high - this.x);
            ++this.x;
        }
    }
    
    public void drawmessage() {
        this.gBuffer.setFont(new Font("Helvetica", 1, this.fontsize));
        this.gBuffer.setColor(Color.black);
        if (this.FONTCOLOR == 2) {
            this.gBuffer.setColor(Color.white);
        }
        if (this.FONTCOLOR == 3) {
            this.gBuffer.setColor(Color.blue);
        }
        if (this.FONTCOLOR == 4) {
            this.gBuffer.setColor(Color.red);
        }
        if (this.FONTCOLOR == 5) {
            this.gBuffer.setColor(Color.green);
        }
        if (this.FONTCOLOR == 6) {
            this.gBuffer.setColor(Color.yellow);
        }
        if (this.FONTCOLOR == 7) {
            this.gBuffer.setColor(Color.orange);
        }
        if (this.FONTCOLOR == 8) {
            this.gBuffer.setColor(Color.gray);
        }
        this.gBuffer.drawString(this.onmouse1, this.len1, this.BELT + this.fontsize + this.td);
        this.gBuffer.drawString(this.onmouse2, this.len2, this.high / 2 + this.td);
        this.gBuffer.drawString(this.onmouse3, this.len3, this.high - this.BELT - 3);
    }
    
    public picframe() {
        this.shadow = Color.darkGray;
        this.cord = Color.black;
        this.Ro = 1;
        this.Go = 1;
        this.Bo = 1;
        this.fontsize = 12;
        this.onmouse1 = "";
        this.onmouse2 = "";
        this.onmouse3 = "";
        this.ermsg = "Error no: ";
        this.MESSAGE1 = "";
        this.MESSAGE2 = "";
        this.MESSAGE3 = "";
        this.TARGET = "";
    }
}
