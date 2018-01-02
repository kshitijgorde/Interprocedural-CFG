import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ScrollWindow extends Applet implements Runnable
{
    Image winScratch;
    Graphics gScratch;
    Thread runner;
    String[] lines;
    int nlines;
    int mlines;
    int topline;
    int fmh;
    Color outerborder;
    Color middleborder;
    Color innerborder;
    Color textcolor;
    Color textbackground;
    boolean isRunning;
    int delay;
    
    public ScrollWindow() {
        this.lines = new String[1000];
        this.outerborder = new Color(13421772);
        this.middleborder = new Color(0);
        this.innerborder = new Color(13421772);
        this.textcolor = new Color(65280);
        this.textbackground = new Color(0);
        this.isRunning = false;
        this.delay = 2;
    }
    
    public void init() {
        final Font f = new Font("Helvetica", 0, 12);
        final FontMetrics fm = this.getFontMetrics(f);
        this.fmh = fm.getHeight();
        this.mlines = (this.getSize().height - 26) / this.fmh;
        this.winScratch = this.createImage(this.getSize().width, this.getSize().height);
        this.gScratch = this.winScratch.getGraphics();
        this.setBackground(Color.white);
        String s = this.getParameter("delay");
        if (s != null) {
            this.delay = Integer.parseInt(s);
        }
        s = this.getParameter("outerborder");
        if (s != null) {
            this.outerborder = new Color(Integer.parseInt(s, 16));
        }
        s = this.getParameter("middleborder");
        if (s != null) {
            this.middleborder = new Color(Integer.parseInt(s, 16));
        }
        s = this.getParameter("innerborder");
        if (s != null) {
            this.innerborder = new Color(Integer.parseInt(s, 16));
        }
        s = this.getParameter("textcolor");
        if (s != null) {
            this.textcolor = new Color(Integer.parseInt(s, 16));
        }
        s = this.getParameter("textbackground");
        if (s != null) {
            this.textbackground = new Color(Integer.parseInt(s, 16));
        }
        int i;
        for (i = 0; i < 1000; ++i) {
            this.lines[i] = this.getParameter(String.valueOf(new StringBuffer("line").append(i + 1)));
            if (this.lines[i] == null) {
                break;
            }
        }
        this.nlines = i;
        this.topline = 0;
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.runner = null;
    }
    
    public void run() {
        final Thread thisThread = Thread.currentThread();
        while (this.runner == thisThread) {
            this.repaint();
            if (this.isRunning && this.nlines > this.mlines) {
                ++this.topline;
                if (this.topline == this.nlines) {
                    this.topline = 0;
                }
            }
            this.isRunning = true;
            try {
                Thread.sleep(this.delay * 1000);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void paint(final Graphics g) {
        final String[] dlines = new String[this.mlines];
        final int w = this.getSize().width;
        final int h = this.getSize().height;
        final Font f = new Font("Helvetica", 0, 12);
        int cnt = 0;
        for (int i = 0; i < this.mlines; ++i) {
            if (this.topline + i < this.nlines) {
                dlines[i] = this.lines[this.topline + i];
            }
            else if (this.nlines > this.mlines) {
                dlines[i] = this.lines[cnt];
                ++cnt;
            }
            else {
                dlines[i] = "";
            }
        }
        this.gScratch.setColor(this.outerborder);
        this.gScratch.drawRect(0, 0, w - 1, h - 1);
        this.gScratch.drawRect(1, 1, w - 3, h - 3);
        this.gScratch.setColor(this.middleborder);
        this.gScratch.drawRect(2, 2, w - 5, h - 5);
        this.gScratch.drawRect(3, 3, w - 7, h - 7);
        this.gScratch.setColor(this.innerborder);
        this.gScratch.drawRect(4, 4, w - 9, h - 9);
        this.gScratch.drawRect(5, 5, w - 11, h - 11);
        this.gScratch.setColor(this.textbackground);
        this.gScratch.fillRect(6, 6, w - 12, h - 12);
        this.gScratch.setFont(f);
        this.gScratch.setColor(this.textcolor);
        for (int i = 0; i < this.mlines; ++i) {
            this.gScratch.drawString(dlines[i], 8, this.fmh * (i + 1) + 8);
        }
        g.drawImage(this.winScratch, 0, 0, this);
    }
    
    public final void update(final Graphics g) {
        this.paint(g);
    }
}
