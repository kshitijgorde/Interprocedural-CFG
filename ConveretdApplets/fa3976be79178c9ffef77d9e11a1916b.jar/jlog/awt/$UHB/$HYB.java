// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$UHB;

import java.awt.LayoutManager;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import jlog.awt.$C0.$NAB;

public class $HYB extends $NAB implements $BXB, $TWB, $WWB, Runnable
{
    private $GXB $HXB;
    private boolean $UXB;
    private Thread $JYB;
    public long $IYB;
    
    public void $IXB(final $TWB $twb) {
        this.$HXB.$IXB($twb);
    }
    
    public $GXB $JXB() {
        return this.$HXB;
    }
    
    public boolean $UWB(final $VWB $vwb) {
        if ($vwb.getSource() == this.$HXB) {
            final Rectangle clipRect = $vwb.getClipRect();
            super.repaint(120L, clipRect.x, clipRect.y, clipRect.width, clipRect.height);
            return true;
        }
        return false;
    }
    
    public void $WXB(final $TWB $twb) {
        this.$HXB.$WXB($twb);
    }
    
    public void $XHB(final Graphics graphics) {
        graphics.setColor(this.getForeground());
        graphics.setFont(graphics.getFont());
        super.paint(graphics);
    }
    
    public Dimension $YHB() {
        return this.size();
    }
    
    public $HYB() {
        this(new FlowLayout());
    }
    
    public $HYB(final LayoutManager layout) {
        this.$HXB = null;
        this.$UXB = true;
        this.$JYB = null;
        this.$IYB = 3000L;
        this.setLayout(layout);
        (this.$HXB = new $GXB(this, this)).$IXB(this);
    }
    
    public void layout() {
        this.$UXB = true;
        try {
            this.$HXB.stop();
            super.layout();
        }
        finally {
            this.$UXB = false;
            this.$HXB.start();
        }
    }
    
    public void paint(final Graphics graphics) {
        this.$HXB.$AXB(graphics);
    }
    
    public void removeNotify() {
        super.removeNotify();
        this.$HXB.flush();
    }
    
    public void repaint(final long n, final int n2, final int n3, final int n4, final int n5) {
        final Dimension size = this.size();
        if (n4 >= size.width && n5 >= size.height && this.$IYB > 0L) {
            if (this.$JYB == null) {
                (this.$JYB = new Thread(this)).start();
            }
            super.repaint(n, n2, n3, n4, n5);
        }
        else {
            this.$HXB.$KXB(n, n2, n3, n4, n5);
        }
    }
    
    public void repaintAll() {
        final Dimension size = this.getSize();
        this.$JYB = null;
        this.$HXB.$KXB(120L, 0, 0, size.width, size.height);
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        try {
            long $iyb = this.$IYB;
            while ($iyb > 0L) {
                $iyb -= 250L;
                Thread.sleep(250L);
            }
        }
        catch (InterruptedException ex) {
            this.$JYB = null;
        }
        if (currentThread == this.$JYB) {
            this.repaintAll();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
