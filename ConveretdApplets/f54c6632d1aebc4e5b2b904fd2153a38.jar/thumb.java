import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Color;
import java.awt.Component;
import java.net.URL;
import java.awt.Font;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class thumb extends Applet implements Runnable
{
    Thread runner;
    Image offimage;
    Graphics off;
    Image knap;
    Image tommel;
    Image tommel2;
    Image knap2;
    String[] text;
    MediaTracker mt;
    int numberofbuttoms;
    int dx;
    int y;
    int x;
    Font font;
    int timer;
    int nedtrykt;
    int aktuelle;
    URL[] links;
    int f1;
    int f2;
    int f3;
    int f4;
    int f5;
    int f6;
    int f7;
    int f8;
    int f9;
    String target;
    boolean knaptrykket;
    boolean f\u00f8rstegang;
    int Width;
    int Height;
    String wait;
    
    public thumb() {
        this.mt = new MediaTracker(this);
        this.numberofbuttoms = 0;
        this.dx = 4;
        this.x = -120;
        this.timer = 0;
        this.nedtrykt = -1;
        this.aktuelle = -1;
        this.target = "_self";
        this.knaptrykket = false;
        this.f\u00f8rstegang = true;
    }
    
    public void init() {
        final String parameter = this.getParameter("text_antialising_color");
        this.f1 = Integer.parseInt(parameter.substring(0, 3));
        this.f2 = Integer.parseInt(parameter.substring(4, 7));
        this.f3 = Integer.parseInt(parameter.substring(8, 11));
        final String parameter2 = this.getParameter("text_color");
        this.f4 = Integer.parseInt(parameter2.substring(0, 3));
        this.f5 = Integer.parseInt(parameter2.substring(4, 7));
        this.f6 = Integer.parseInt(parameter2.substring(8, 11));
        final String parameter3 = this.getParameter("background_color");
        this.f7 = Integer.parseInt(parameter3.substring(0, 3));
        this.f8 = Integer.parseInt(parameter3.substring(4, 7));
        this.f9 = Integer.parseInt(parameter3.substring(8, 11));
        this.font = new Font(this.getParameter("fontface"), 0, Integer.parseInt(this.getParameter("fontsize")));
        if (this.getParameter("target") != null) {
            this.target = this.getParameter("target");
        }
        this.wait = this.getParameter("loading_message");
        this.knap2 = this.getImage(this.getCodeBase(), "knap2.gif");
        this.mt.addImage(this.knap2, 0);
        this.knap = this.getImage(this.getCodeBase(), "knap1.gif");
        this.mt.addImage(this.knap, 1);
        this.tommel = this.getImage(this.getCodeBase(), "tommel.gif");
        this.mt.addImage(this.tommel, 2);
        this.tommel2 = this.getImage(this.getCodeBase(), "tommel2.gif");
        this.mt.addImage(this.tommel2, 3);
        for (int n = 1; this.getParameter("text" + n) != null; ++n) {
            ++this.numberofbuttoms;
        }
        this.text = new String[this.numberofbuttoms];
        this.links = new URL[this.numberofbuttoms];
        for (int i = 0; i < this.numberofbuttoms; ++i) {
            try {
                this.links[i] = new URL(this.getCodeBase(), this.getParameter("link" + (i + 1)));
            }
            catch (Exception ex) {}
            this.text[i] = this.getParameter("text" + (i + 1));
        }
        this.Width = this.size().width;
        this.Height = this.size().height;
        this.offimage = this.createImage(this.size().width, this.size().height);
        (this.off = this.offimage.getGraphics()).setFont(this.font);
        this.off.setColor(new Color(this.f7, this.f8, this.f9));
        this.off.fillRect(0, 0, this.Width, this.Height);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.timer != 0) {
            return true;
        }
        for (int i = 0; i < this.numberofbuttoms; ++i) {
            if (n > 18 && n < 61 && n2 > 18 + i * 50 && n2 < 61 + i * 50) {
                this.y = 20 + i * 50;
                this.aktuelle = i;
            }
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offimage, 0, 0, this);
    }
    
    public void run() {
        while (true) {
            if (this.f\u00f8rstegang && !this.mt.checkAll(true)) {
                this.udskriv(this.wait, 20, 20);
            }
            if (this.f\u00f8rstegang && this.mt.checkAll(true)) {
                this.off.setColor(new Color(this.f7, this.f8, this.f9));
                this.off.fillRect(0, 0, this.Width, this.Height);
                for (int i = 0; i < this.numberofbuttoms; ++i) {
                    this.udskriv(this.text[i], 70, 50 + i * 50);
                    this.off.drawImage(this.knap, 20, 20 + i * 50, this);
                }
                this.f\u00f8rstegang = false;
            }
            if (!this.f\u00f8rstegang && this.mt.checkAll(true)) {
                if (this.aktuelle != -1) {
                    this.off.setColor(new Color(this.f7, this.f8, this.f9));
                    this.off.fillRect(0, 19 + this.aktuelle * 50, this.Width, 80);
                    this.off.drawImage(this.knap, 20, 20 + this.aktuelle * 50, this);
                    if (this.aktuelle + 1 != this.numberofbuttoms) {
                        this.off.drawImage(this.knap, 20, 20 + (this.aktuelle + 1) * 50, this);
                    }
                    if (this.aktuelle != -1) {
                        this.udskriv(this.text[this.aktuelle], 70, 50 + this.aktuelle * 50);
                        if (this.aktuelle + 1 != this.numberofbuttoms) {
                            this.udskriv(this.text[this.aktuelle + 1], 70, 50 + (this.aktuelle + 1) * 50);
                        }
                    }
                }
                if (this.aktuelle != -1) {
                    ++this.timer;
                }
                if (this.nedtrykt != -1 && this.timer > 0) {
                    this.off.drawImage(this.knap2, 20, 20 + this.nedtrykt * 50, this);
                }
                if (this.aktuelle != -1) {
                    if (this.timer < 26 || this.timer > 32) {
                        this.off.drawImage(this.tommel, this.x, this.y, this);
                    }
                    else {
                        this.off.drawImage(this.tommel2, this.x, this.y, this);
                    }
                    this.x += this.dx;
                }
                if (this.timer == 30) {
                    this.getAppletContext().showDocument(this.links[this.aktuelle], this.target);
                }
                if (this.timer == 32) {
                    this.dx = -4;
                }
                if (this.timer == 55) {
                    this.aktuelle = -1;
                    this.dx = 4;
                    this.timer = 0;
                    this.x = -120;
                }
                if (this.timer == 25) {
                    this.dx = 0;
                    if (this.nedtrykt != -1 && this.nedtrykt != this.aktuelle) {
                        this.off.drawImage(this.knap, 20, 20 + this.nedtrykt * 50, this);
                    }
                    this.nedtrykt = this.aktuelle;
                }
            }
            this.repaint();
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException ex) {
                this.showStatus("Error " + ex);
            }
        }
    }
    
    public void start() {
        (this.runner = new Thread(this)).start();
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }
    }
    
    void udskriv(final String s, final int n, final int n2) {
        this.off.setColor(new Color(this.f1, this.f2, this.f3));
        this.off.drawString(s, n + 1, n2);
        this.off.drawString(s, n - 1, n2);
        this.off.drawString(s, n, n2 + 1);
        this.off.drawString(s, n, n2 - 1);
        this.off.setColor(new Color(this.f4, this.f5, this.f6));
        this.off.drawString(s, n, n2);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
