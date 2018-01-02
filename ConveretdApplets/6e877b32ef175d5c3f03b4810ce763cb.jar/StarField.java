import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Dimension;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class StarField extends Applet implements Runnable
{
    Dimension d;
    Font font;
    FontMetrics fm;
    Graphics goff;
    Image ii;
    Thread thethread;
    String s;
    final int numstars = 64;
    int[] starx;
    int[] stary;
    int[] starz;
    final int c = 8;
    
    public StarField() {
        this.font = new Font("Helvetica", 1, 36);
    }
    
    public void DrawStars() {
        this.goff.setColor(Color.white);
        for (int i = 0; i < 64; ++i) {
            final int n = this.starx[i] / this.starz[i] + this.d.width / 2;
            final int n2 = this.stary[i] / this.starz[i] + this.d.height / 2;
            final int[] starz = this.starz;
            final int n3 = i;
            --starz[n3];
            final int n4 = this.starx[i] / this.starz[i] + this.d.width / 2;
            final int n5 = this.stary[i] / this.starz[i] + this.d.height / 2;
            if (n4 < 0 || n4 > this.d.width || n5 < 0 || n5 > this.d.height || this.starz[i] < 2) {
                this.starx[i] = 8 * (int)(Math.random() * this.d.width - this.d.width / 2);
                this.stary[i] = 8 * (int)(Math.random() * this.d.height - this.d.height / 2);
                this.starz[i] = 66;
            }
            else {
                this.goff.setColor(new Color(255 - 3 * this.starz[i], 255 - 3 * this.starz[i], 255 - 3 * this.starz[i]));
                this.goff.drawLine(n, n2, n4, n5);
            }
        }
    }
    
    public String getAppletInfo() {
        return "Starfield - Door Brian Postma";
    }
    
    public void init() {
        this.d = this.size();
        final Graphics graphics = this.getGraphics();
        graphics.setFont(this.font);
        this.fm = graphics.getFontMetrics();
        this.stary = new int[64];
        this.starx = new int[64];
        this.starz = new int[64];
        for (int i = 0; i < 64; ++i) {
            this.starx[i] = 8 * (int)(Math.random() * this.d.width - this.d.width / 2);
            this.stary[i] = 8 * (int)(Math.random() * this.d.height * 2.0 - this.d.height / 2);
            this.starz[i] = i + 2;
        }
        this.s = this.getParameter("Text");
    }
    
    public void paint(final Graphics graphics) {
        if (this.goff == null && this.d.width > 0 && this.d.height > 0) {
            this.ii = this.createImage(this.d.width, this.d.height);
            this.goff = this.ii.getGraphics();
        }
        if (this.goff == null || this.ii == null) {
            return;
        }
        this.goff.setFont(this.font);
        this.goff.setColor(Color.black);
        this.goff.fillRect(0, 0, this.d.width, this.d.height);
        this.DrawStars();
        this.goff.setColor(new Color(128, 192, 255));
        this.goff.drawString(this.s, (this.d.width - this.fm.stringWidth(this.s)) / 2, this.d.height / 2);
        graphics.drawImage(this.ii, 0, 0, this);
    }
    
    public void run() {
        Thread.currentThread().setPriority(10);
        final Graphics graphics = this.getGraphics();
        while (true) {
            final long currentTimeMillis = System.currentTimeMillis();
            try {
                this.paint(graphics);
                Thread.sleep(Math.max(0L, currentTimeMillis + 30L - System.currentTimeMillis()));
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
