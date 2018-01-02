import java.util.StringTokenizer;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Component;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.MediaTracker;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Qcam extends Applet implements Runnable
{
    String s;
    Image pc;
    MediaTracker tracker;
    Thread thread;
    Dimension sz;
    Image im;
    Graphics sc;
    Qtil title;
    Color bc;
    Color altColor;
    Dimension dsz;
    Font lfont;
    int fr;
    int ps;
    
    public void init() {
        this.sz = this.getSize();
        this.im = this.createImage(this.sz.width, this.sz.height);
        this.sc = this.im.getGraphics();
        this.s = this.getParameter("image");
        this.pc = this.getImage(this.getDocumentBase(), this.s);
        this.bc = new Color(192, 192, 192);
        this.s = this.getParameter("fadeRate");
        this.fr = Integer.parseInt(this.s);
        this.s = this.getParameter("panSpeed");
        this.ps = Integer.parseInt(this.s);
        this.lfont = this.getFont(this.getParameter("titleFont"), "Times Roman", 3, 48);
        final FontMetrics fontMetrics = this.getFontMetrics(this.lfont);
        this.s = this.getParameter("title");
        if (this.s != null) {
            this.title = new Qtil(this.s, this.sz, new Dimension(fontMetrics.stringWidth(this.s), fontMetrics.getAscent() - fontMetrics.getDescent()), this.bc, this.altColor, this.fr);
        }
        (this.tracker = new MediaTracker(this)).addImage(this.pc, 0);
    }
    
    public void start() {
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread = null;
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.im, 0, 0, null);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void run() {
        while (!this.tracker.checkID(0, true)) {}
        this.dsz = new Dimension(this.pc.getWidth(null), this.pc.getHeight(null));
        final int n = this.dsz.width - this.sz.width;
        final int n2 = this.dsz.height - this.sz.height;
        int n3 = 0;
        final double n4 = Math.random() * 2.0 + 2.0;
        final double n5 = Math.random() * 2.0 + 2.0;
        final double n6 = Math.random() * 4.0 + 4.0;
        final double n7 = Math.random() * 4.0 + 4.0;
        while (true) {
            this.sc.drawImage(this.pc, -(int)(n / 2 + 3 * n / 8 * Math.sin(n4 * n3 / 360.0) + n / 8 * Math.cos(n6 * n3 / 360.0)), -(int)(n2 / 2 + 3 * n2 / 8 * Math.cos(n5 * n3 / 360.0) + n2 / 8 * Math.sin(n7 * n3 / 360.0)), this.dsz.width, this.dsz.height, null);
            if (this.title != null) {
                this.sc.setFont(this.lfont);
                this.title.draw(this.sc);
            }
            this.repaint();
            n3 += this.ps;
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    Font getFont(final String s, String nextToken, int n, int int1) {
        if (s != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ":");
            if (stringTokenizer.hasMoreTokens()) {
                nextToken = stringTokenizer.nextToken();
            }
            if (stringTokenizer.hasMoreTokens()) {
                final String lowerCase = stringTokenizer.nextToken().toLowerCase();
                if (lowerCase.equals("b")) {
                    n = 1;
                }
                else if (lowerCase.equals("i")) {
                    n = 2;
                }
                else {
                    n = 0;
                }
            }
            if (stringTokenizer.hasMoreTokens()) {
                int1 = Integer.parseInt(stringTokenizer.nextToken());
            }
        }
        return new Font(nextToken, n, int1);
    }
}
