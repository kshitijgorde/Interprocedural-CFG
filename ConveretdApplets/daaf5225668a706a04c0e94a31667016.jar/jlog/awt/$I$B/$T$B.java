// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$I$B;

import java.io.IOException;
import java.util.Enumeration;
import java.awt.Component;
import java.awt.MediaTracker;
import java.util.Date;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.io.InputStream;
import java.awt.Image;
import java.util.Vector;
import java.applet.Applet;

abstract class $T$B extends Applet implements $K$B, Runnable
{
    static final boolean DEBUG = true;
    $J$B $U$B;
    Vector $Z$B;
    $A_B $B_B;
    long $C_B;
    long $D_B;
    Image $UYB;
    Thread $OR;
    Object $I6;
    
    void $E_B() throws Exception {
        final long currentTimeMillis = System.currentTimeMillis();
        if (this.$Z$B != null && (this.$D_B < 1L || currentTimeMillis - this.$C_B < this.$D_B)) {
            return;
        }
        this.$C_B = currentTimeMillis;
        final InputStream resourceAsStream = this.$U$B.getResourceAsStream(this.$U$B.getProperty("BANNER_SCRIPT", "banner.properties"));
        if (resourceAsStream == null) {
            return;
        }
        try {
            this.$X$B(resourceAsStream);
        }
        finally {
            resourceAsStream.close();
        }
    }
    
    void $J_B(int n, final $A_B $b_B) throws InterruptedException {
        this.$B_B = $b_B;
        if ($b_B.image == null) {
            this.repaint();
            Thread.currentThread();
            Thread.sleep(n * 10);
            return;
        }
        while (n > 0 && Thread.currentThread() == this.$OR) {
            this.repaint();
            if ($b_B.image == null) {
                return;
            }
            final int n2 = n;
            Thread.currentThread();
            Thread.sleep(n2 * 10);
            n -= n2;
        }
    }
    
    void $N0(final Graphics graphics) {
        final Dimension $w$B = this.$W$B();
        final $A_B $b_B = this.$B_B;
        if ($b_B == null || $b_B.$G1 == null) {
            graphics.setColor(this.getBackground());
        }
        else {
            graphics.setColor($b_B.$G1);
        }
        graphics.fillRect(0, 0, $w$B.width, $w$B.height);
        if ($b_B == null) {
            return;
        }
        final Image image = $b_B.image;
        if (image == null) {
            return;
        }
        graphics.drawImage(image, ($w$B.width - image.getWidth(this)) / 2, ($w$B.height - image.getHeight(this)) / 2, graphics.getColor(), this);
    }
    
    void $O6() throws Exception {
        while (Thread.currentThread() == this.$OR) {
            this.$E_B();
            if (this.$Z$B == null) {
                break;
            }
            final Enumeration<$A_B> elements = (Enumeration<$A_B>)this.$Z$B.elements();
            while (elements.hasMoreElements()) {
                final $A_B $a_B = elements.nextElement();
                this.$V$B("BANNER_DEFAULT_DURATION", 2000);
                final Date date = new Date();
                if (!$a_B.$G_B.before(date)) {
                    if ($a_B.$G_B.after(date)) {
                        $a_B.flush();
                        this.$Z$B.removeElement($a_B);
                    }
                    else {
                        if (!$a_B.$H_B) {
                            final MediaTracker mediaTracker = new MediaTracker(this);
                            mediaTracker.addImage($a_B.image, 1);
                            mediaTracker.waitForAll();
                            if (mediaTracker.isErrorAny()) {
                                System.err.println("BannerError image:" + $a_B);
                                $a_B.flush();
                                this.$Z$B.removeElement($a_B);
                                continue;
                            }
                            $a_B.$H_B = true;
                        }
                        final int $q6 = $a_B.$Q6;
                        this.setCursor($a_B.$VC != null);
                        this.$J_B($q6, $a_B);
                    }
                }
            }
            if (!this.$Z$B.isEmpty()) {
                continue;
            }
            Thread.currentThread();
            Thread.sleep(30000L);
        }
    }
    
    int $V$B(final String s, final int n) {
        try {
            return Integer.parseInt(this.$U$B.getProperty(s, null));
        }
        catch (Exception ex) {
            return n;
        }
    }
    
    abstract Dimension $W$B();
    
    abstract void $X$B(final InputStream p0) throws IOException;
    
    public $T$B(final $J$B $u$B) {
        this.$Z$B = null;
        this.$B_B = null;
        this.$C_B = 0L;
        this.$D_B = 0L;
        this.$UYB = null;
        this.$OR = null;
        this.$I6 = new Object();
        this.$U$B = $u$B;
        this.$D_B = this.$V$B("BANNER_SCRIPT_REFRESH", 0) * 1000 * 60;
    }
    
    public void destroy() {
        this.stop();
        this.flush();
    }
    
    public void flush() {
        if (this.$Z$B != null) {
            final Enumeration<$A_B> elements = this.$Z$B.elements();
            while (elements.hasMoreElements()) {
                elements.nextElement().flush();
            }
            this.$Z$B.removeAllElements();
        }
        if (this.$UYB != null) {
            this.$UYB.flush();
            this.$UYB = null;
        }
    }
    
    void mousePressed() {
        try {
            final $A_B $b_B = this.$B_B;
            if ($b_B == null || $b_B.$VC == null) {
                return;
            }
            this.$U$B.showDocument($b_B.$VC, this.$U$B.getProperty("BANNER_DEFAULT_TARGET", "_empty"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void paint(final Graphics graphics) {
        final Dimension $w$B = this.$W$B();
        if ($w$B.width < 1 || $w$B.height < 1) {
            return;
        }
        Image $uyb = this.$UYB;
        if ($uyb == null || $uyb.getWidth(null) < $w$B.width || $uyb.getHeight(null) < $w$B.height) {
            if ($uyb != null) {
                $uyb.flush();
            }
            $uyb = this.createImage($w$B.width, $w$B.height);
            this.$UYB = $uyb;
        }
        final Graphics graphics2 = $uyb.getGraphics();
        try {
            this.$N0(graphics2);
        }
        finally {
            graphics2.dispose();
        }
        graphics.drawImage($uyb, 0, 0, this.getBackground(), this);
    }
    
    public void run() {
        try {
            this.$O6();
        }
        catch (Exception ex) {
            System.err.println("BannerComponent.run failed:" + ex.getMessage());
            ex.printStackTrace();
        }
        finally {
            synchronized (this.$I6) {
                if (Thread.currentThread() == this.$OR) {
                    this.$OR = null;
                }
            }
            // monitorexit(this.$I6)
        }
    }
    
    abstract void setCursor(final boolean p0);
    
    public void start() {
        synchronized (this.$I6) {
            if (this.$OR == null || !this.$OR.isAlive()) {
                (this.$OR = new Thread(this)).start();
            }
        }
        // monitorexit(this.$I6)
        this.repaint();
    }
    
    public void stop() {
        synchronized (this.$I6) {
            this.$OR = null;
        }
        // monitorexit(this.$I6)
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
