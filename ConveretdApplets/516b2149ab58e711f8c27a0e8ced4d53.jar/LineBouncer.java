import java.awt.Color;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Dimension;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class LineBouncer extends Applet implements Runnable
{
    Dimension d;
    Thread thethread;
    int[] x1;
    int[] x2;
    int[] y1;
    int[] y2;
    final int numlines = 32;
    int pos;
    int x1pos;
    int y1pos;
    int x2pos;
    int y2pos;
    int dx1;
    int dy1;
    int dx2;
    int dy2;
    Font font;
    FontMetrics fm;
    String s;
    
    public LineBouncer() {
        this.pos = 0;
        this.x1pos = 0;
        this.y1pos = 0;
        this.x2pos = 1;
        this.y2pos = 1;
        this.dx1 = 3;
        this.dy1 = -3;
        this.dx2 = -3;
        this.dy2 = 3;
        this.font = new Font("Helvetica", 1, 36);
    }
    
    private void DrawLines(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawLine(this.x1[this.pos], this.y1[this.pos], this.x2[this.pos], this.y2[this.pos]);
        graphics.drawLine(this.d.width - this.x1[this.pos], this.y1[this.pos], this.d.width - this.x2[this.pos], this.y2[this.pos]);
        int n;
        if (this.pos > 16) {
            n = 3 * (32 - this.pos);
        }
        else {
            n = 3 * this.pos;
        }
        graphics.setColor(new Color(255 - n, 192 - n, 128 + n));
        graphics.drawLine(this.x1pos, this.y1pos, this.x2pos, this.y2pos);
        graphics.drawLine(this.d.width - this.x1pos, this.y1pos, this.d.width - this.x2pos, this.y2pos);
        this.x1[this.pos] = this.x1pos;
        this.x2[this.pos] = this.x2pos;
        this.y1[this.pos] = this.y1pos;
        this.y2[this.pos] = this.y2pos;
        this.pos = (this.pos + 1) % 32;
        this.x1pos += this.dx1;
        if (this.x1pos <= 0 || this.x1pos >= this.d.width) {
            this.dx1 = -this.dx1;
        }
        this.x2pos += this.dx2;
        if (this.x2pos <= 0 || this.x2pos >= this.d.width) {
            this.dx2 = -this.dx2;
        }
        this.y1pos += this.dy1;
        if (this.y1pos <= 0 || this.y1pos >= this.d.height) {
            this.dy1 = -this.dy1;
        }
        this.y2pos += this.dy2;
        if (this.y2pos <= 0 || this.y2pos >= this.d.height) {
            this.dy2 = -this.dy2;
        }
    }
    
    public String getAppletInfo() {
        return "Testje - Door Brian Postma";
    }
    
    public void init() {
        final Graphics graphics = this.getGraphics();
        graphics.setFont(this.font);
        this.d = this.size();
        this.s = this.getParameter("Text");
        this.fm = graphics.getFontMetrics();
        this.x1 = new int[32];
        this.x2 = new int[32];
        this.y1 = new int[32];
        this.y2 = new int[32];
        for (int i = 0; i < 32; ++i) {
            this.x1[i] = 0;
            this.y1[i] = 0;
            this.x2[i] = 0;
            this.y2[i] = 0;
        }
        this.x1pos = 1 + (int)(Math.random() * this.d.width - 2.0);
        this.x2pos = 1 + (int)(Math.random() * this.d.width - 2.0);
        this.y1pos = 1 + (int)(Math.random() * this.d.height - 2.0);
        this.y2pos = 1 + (int)(Math.random() * this.d.height - 2.0);
    }
    
    public void paint(final Graphics graphics) {
        this.DrawLines(graphics);
        graphics.setFont(this.font);
        graphics.setColor(new Color(128, 192, 255));
        graphics.drawString(this.s, (this.d.width - this.fm.stringWidth(this.s)) / 2, this.d.height / 2);
    }
    
    public void run() {
        Thread.currentThread().setPriority(10);
        final Graphics graphics = this.getGraphics();
        this.setBackground(Color.black);
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
