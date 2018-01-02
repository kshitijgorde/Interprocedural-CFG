// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.applet.Applet;
import java.awt.Image;

public class ad extends ac implements Runnable
{
    private Thread byte;
    private Image do;
    private Image new;
    private int a;
    private int case;
    private int if;
    private long for;
    private boolean try;
    private int int;
    
    public ad(final Applet applet, final String s) {
        this.byte = null;
        this.try = true;
        this.for = 100L;
        this.int = 2;
        try {
            this.do = applet.getImage(applet.getCodeBase(), s);
            final MediaTracker mediaTracker = new MediaTracker(this);
            mediaTracker.addImage(this.do, 0);
            if (!mediaTracker.waitForAll(2000L)) {
                System.out.println("MediaTracke timed out (WaitIcon)");
            }
            if (mediaTracker.isErrorAny()) {
                this.do = null;
            }
        }
        catch (Throwable t) {
            this.do = null;
        }
        if (this.do != null) {
            this.a = this.do.getWidth(null);
            this.case = this.do.getHeight(null);
        }
        this.if = 0;
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        this.a(graphics);
    }
    
    private void a(final Graphics graphics) {
        try {
            if (this.do == null || graphics == null) {
                return;
            }
            final Dimension size = this.getSize();
            graphics.setColor(this.getBackground());
            graphics.draw3DRect(this.int, this.int, size.width - (this.int * 2 + 1), size.height - (this.int * 2 + 1), false);
            if (this.byte != null) {
                graphics.clipRect(this.int + 2, this.int + 2, size.width - (this.int * 2 + 4), size.height - (this.int * 2 + 4));
                graphics.drawImage(this.new, this.if - this.a, 0, this);
            }
            else {
                graphics.fillRect(0, 0, size.width - 1, size.height - 1);
                graphics.draw3DRect(this.int, this.int, size.width - (this.int * 2 + 1), size.height - (this.int * 2 + 1), false);
            }
        }
        catch (Throwable t) {}
    }
    
    public long if() {
        return this.for;
    }
    
    public void a(final long for1) {
        this.for = for1;
    }
    
    public synchronized void a() {
        if (this.byte != null) {
            try {
                this.byte.stop();
            }
            catch (Throwable t) {}
            this.byte = null;
            this.new = null;
            this.repaint();
        }
    }
    
    public void run() {
        this.if = 0;
        while (this.byte != null) {
            this.repaint();
            try {
                Thread.sleep(this.for);
            }
            catch (Throwable t) {}
            if (this.if >= this.a - 1) {
                this.if = 0;
            }
            else {
                ++this.if;
            }
        }
    }
    
    public synchronized void do() {
        if (this.do == null) {
            return;
        }
        if (this.byte == null) {
            final int width = this.getSize().width;
            final int height = this.getSize().height;
            final int n = Math.round(width / this.a) + 2;
            final int n2 = n * this.a;
            if (n2 == 0 || height == 0) {
                return;
            }
            this.new = this.createImage(n2, height);
            if (this.new == null) {
                return;
            }
            final Graphics graphics = this.new.getGraphics();
            int n3 = 0;
            for (int i = 0; i < n; ++i) {
                graphics.drawImage(this.do, n3, (height - this.case) / 2, this);
                n3 += this.a;
            }
            (this.byte = new Thread(this)).start();
            graphics.dispose();
        }
    }
}
