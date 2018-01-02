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

public class FireWorks extends Applet implements Runnable
{
    Dimension d;
    Font font;
    FontMetrics fm;
    Graphics goff;
    Image ii;
    Thread thethread;
    String s;
    final int numrockets = 8;
    final int num = 16;
    int[] xcoord;
    int[] ycoord;
    int[] xspeed;
    int[] yspeed;
    int[] count;
    boolean[] exploding;
    
    public FireWorks() {
        this.font = new Font("Helvetica", 1, 36);
    }
    
    public void FireWorks(final Graphics graphics) {
        for (int i = 0; i < 8; ++i) {
            if (!this.exploding[i] && this.yspeed[i * 16] > 0) {
                this.exploding[i] = true;
                for (int j = 0; j < 16; ++j) {
                    final int n = i * 16 + j;
                    this.yspeed[n] = (int)(Math.random() * 28.0) - 15;
                    this.xspeed[n] = (int)(Math.random() * 31.0) - 16;
                    if (this.xspeed[n] >= 0) {
                        final int[] xspeed = this.xspeed;
                        final int n2 = n;
                        ++xspeed[n2];
                    }
                }
            }
            for (int k = 0; k < 16; ++k) {
                final int n3 = i * 16 + k;
                if (this.exploding[i]) {
                    switch (i & 0x3) {
                        case 0: {
                            graphics.setColor(new Color(192, this.count[i] + 32, this.count[i] + 127));
                            break;
                        }
                        case 1: {
                            graphics.setColor(new Color(this.count[i] + 32, 192, this.count[i] + 127));
                            break;
                        }
                        case 2: {
                            graphics.setColor(new Color(192, 192, this.count[i] + 32));
                            break;
                        }
                        default: {
                            graphics.setColor(new Color(this.count[i] + 32, this.count[i] + 127, 192));
                            break;
                        }
                    }
                }
                else {
                    graphics.setColor(Color.white);
                }
                graphics.fillRect(this.xcoord[n3] >> 3, this.ycoord[n3] >> 3, 2, 2);
                final int[] xcoord = this.xcoord;
                final int n4 = n3;
                xcoord[n4] += this.xspeed[n3];
                final int[] ycoord = this.ycoord;
                final int n5 = n3;
                ycoord[n5] += this.yspeed[n3];
                final int[] yspeed = this.yspeed;
                final int n6 = n3;
                ++yspeed[n6];
            }
            final int[] count = this.count;
            final int n7 = i;
            --count[n7];
            if (this.count[i] <= 0) {
                this.count[i] = 128;
                this.exploding[i] = false;
                final int n8 = (int)(Math.random() * this.d.width) << 3;
                final int n9 = this.d.height << 3;
                final int n10 = (int)(Math.random() * 28.0) - 58;
                int n11 = (int)(Math.random() * 15.0) - 8;
                if (n11 >= 0) {
                    ++n11;
                }
                for (int l = 0; l < 16; ++l) {
                    final int n12 = i * 16 + l;
                    this.xcoord[n12] = n8;
                    this.ycoord[n12] = n9;
                    this.xspeed[n12] = n11;
                    this.yspeed[n12] = n10;
                }
            }
        }
    }
    
    public String getAppletInfo() {
        return "Fireworks - By Brian Postma";
    }
    
    public void init() {
        this.d = this.size();
        final Graphics graphics = this.getGraphics();
        graphics.setFont(this.font);
        this.fm = graphics.getFontMetrics();
        this.xcoord = new int[128];
        this.ycoord = new int[128];
        this.xspeed = new int[128];
        this.yspeed = new int[128];
        this.count = new int[8];
        this.exploding = new boolean[8];
        for (int i = 0; i < 128; ++i) {
            this.xcoord[i] = -20000;
            this.ycoord[i] = -20000;
            this.xspeed[i] = 0;
            this.yspeed[i] = 0;
        }
        for (int j = 0; j < 8; ++j) {
            this.count[j] = 1 + j * 16;
            this.exploding[j] = true;
        }
        this.s = this.getParameter("Text");
    }
    
    public void paint(final Graphics graphics) {
        if (this.goff == null && this.d.width > 0 && this.d.height > 0) {
            this.ii = this.createImage(this.d.width, this.d.height);
            (this.goff = this.ii.getGraphics()).setFont(this.font);
        }
        if (this.goff == null || this.ii == null) {
            return;
        }
        this.goff.setColor(Color.black);
        this.goff.fillRect(0, 0, this.d.width, this.d.height);
        this.FireWorks(this.goff);
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
