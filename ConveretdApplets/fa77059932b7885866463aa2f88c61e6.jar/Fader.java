import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Dimension;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Fader extends Applet implements Runnable
{
    Dimension d;
    Image ii;
    Graphics goff;
    Thread thethread;
    Font font;
    FontMetrics fm;
    String s1;
    String s2;
    int color1;
    int color2;
    int dcolor1;
    int dcolor2;
    
    public Fader() {
        this.font = new Font("Helvetica", 1, 36);
    }
    
    public String getAppletInfo() {
        return "Text fader - by Brian Postma";
    }
    
    public void init() {
        this.d = this.size();
        final Graphics graphics = this.getGraphics();
        graphics.setFont(this.font);
        this.fm = graphics.getFontMetrics();
        this.setBackground(Color.black);
        this.s1 = this.getParameter("Text1");
        this.s2 = this.getParameter("Text2");
        this.color1 = 10;
        this.color2 = 245;
        this.dcolor2 = -2;
        this.dcolor1 = 2;
    }
    
    public void paint(final Graphics graphics) {
        if (this.goff == null && this.d.width > 0 && this.d.height > 0) {
            this.ii = this.createImage(this.d.width, this.d.height);
            this.goff = this.ii.getGraphics();
        }
        if (this.goff == null || this.ii == null) {
            return;
        }
        graphics.setFont(this.font);
        this.goff.setFont(this.font);
        if (this.color1 < this.color2) {
            this.goff.setColor(new Color(this.color1 / 4, this.color1 / 2, this.color1));
            this.goff.drawString(this.s1, (this.d.width - this.fm.stringWidth(this.s1)) / 2, this.d.height / 2);
            this.goff.setColor(new Color(this.color2 / 4, this.color2 / 2, this.color2));
            this.goff.drawString(this.s2, (this.d.width - this.fm.stringWidth(this.s2)) / 2, this.d.height / 2);
        }
        else {
            this.goff.setColor(new Color(this.color2 / 4, this.color2 / 2, this.color2));
            this.goff.drawString(this.s2, (this.d.width - this.fm.stringWidth(this.s2)) / 2, this.d.height / 2);
            this.goff.setColor(new Color(this.color1 / 4, this.color1 / 2, this.color1));
            this.goff.drawString(this.s1, (this.d.width - this.fm.stringWidth(this.s1)) / 2, this.d.height / 2);
        }
        graphics.drawImage(this.ii, 0, 0, this);
        this.color1 += this.dcolor1;
        this.color2 += this.dcolor2;
        if (this.color1 <= 3 || this.color1 >= 250) {
            this.dcolor1 = -this.dcolor1;
        }
        if (this.color2 <= 3 || this.color2 >= 250) {
            this.dcolor2 = -this.dcolor2;
        }
    }
    
    public void run() {
        Thread.currentThread().setPriority(10);
        final Graphics graphics = this.getGraphics();
        while (true) {
            final long currentTimeMillis = System.currentTimeMillis();
            try {
                this.paint(graphics);
                Thread.sleep(Math.max(0L, currentTimeMillis + 20L - System.currentTimeMillis()));
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void start() {
        if (this.thethread == null) {
            (this.thethread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.thethread != null) {
            this.thethread.stop();
            this.thethread = null;
        }
    }
}
