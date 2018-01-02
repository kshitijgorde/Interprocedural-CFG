// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$UHB;

import jlog.$OR.$HZB;
import java.util.Enumeration;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Image;

public class $GXB implements Runnable, $XWB
{
    Image $UYB;
    Image $VYB;
    $BXB $NXB;
    $WWB $MXB;
    $KYB $WYB;
    Thread $OR;
    Rectangle $XYB;
    boolean $YYB;
    Vector $QO;
    Object $ZYB;
    public Rectangle $RYB;
    Object $GZB;
    Object $KZB;
    
    public void $AXB(final Graphics graphics) {
        this.$AXB(graphics, 0, 0, -1, -1);
    }
    
    public void $AXB(Graphics create, final int n, final int n2, final int n3, final int n4) {
        create = create.create();
        try {
            this.$BZB(create, n, n2, n3, n4);
        }
        finally {
            create.dispose();
        }
    }
    
    public $KYB $AZB() {
        if (this.$WYB == null) {
            this.$WYB = new $KYB();
        }
        return this.$WYB;
    }
    
    private void $BZB(final Graphics graphics, final int n, final int n2, final int n3, final int n4) {
        final Dimension $yhb = this.$NXB.$YHB();
        if ($yhb == null) {
            throw new $SWB("size=null");
        }
        final Rectangle $czb = this.$CZB(graphics, n, n2, n3, n4, $yhb);
        if ($czb == null) {
            return;
        }
        synchronized (this.$ZYB) {
            Image $vyb = this.$VYB;
            if ($vyb != null && ($vyb.getWidth(null) != $yhb.width || $vyb.getHeight(null) != $yhb.height)) {
                $vyb = null;
                this.$VYB.flush();
                this.$VYB = null;
            }
            Color color = this.$NXB.getBackground();
            if (color == null) {
                color = Color.white;
            }
            graphics.setColor(color);
            if ($vyb == null) {
                graphics.fillRect($czb.x, $czb.y, $czb.width, $czb.height);
                if (!this.$YYB && this.$AZB().isEmpty() && $yhb.width > 0 && $yhb.height > 0) {
                    this.$KXB(0L, 0, 0, $yhb.width, $yhb.height);
                }
            }
            else {
                this.$DZB(graphics, $vyb, n, n2, n3, n4, $yhb);
            }
        }
        // monitorexit(this.$ZYB)
    }
    
    private Rectangle $CZB(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Dimension dimension) {
        if (n3 < 0 && n4 < 0) {
            return $QXB.$RXB(graphics.getClipRect(), dimension);
        }
        return new Rectangle(0, 0, dimension.width, dimension.height);
    }
    
    private void $DZB(final Graphics graphics, final Image image, final int n, final int n2, final int n3, final int n4, final Dimension dimension) {
        final int max = Math.max(image.getWidth(null), 0);
        final int max2 = Math.max(image.getHeight(null), 0);
        if (n3 < 0 && n4 < 0) {
            graphics.drawImage(image, n, n2, graphics.getColor(), null);
            if (max < dimension.width) {
                graphics.fillRect(max, 0, dimension.width - max, dimension.height);
            }
            if (max2 < dimension.height && max != 0) {
                graphics.fillRect(0, max2, max, dimension.height - max2);
            }
        }
        else {
            graphics.drawImage(image, n, n2, n3, n4, graphics.getColor(), null);
        }
    }
    
    public void $IXB(final $TWB $twb) {
        this.$QO.addElement($twb);
    }
    
    public boolean $IZB() {
        return this.$YYB;
    }
    
    public void $KXB(final long n, final int n2, final int n3, final int n4, final int n5) {
        final Rectangle $rxb = $QXB.$RXB(new Rectangle(n2, n3, n4, n5), this.$NXB.$YHB());
        if ($rxb == null) {
            return;
        }
        this.$AZB().$MYB($rxb);
        if (!this.$YYB) {
            this.start();
        }
    }
    
    private void $LZB(Rectangle $rxb) {
        final Dimension $yhb = this.$NXB.$YHB();
        if ($yhb == null) {
            throw new $SWB("size=null");
        }
        if ($rxb == null) {
            throw new $SWB("clip=null");
        }
        final Rectangle rectangle = new Rectangle(0, 0, $yhb.width, $yhb.height);
        $rxb = $QXB.$RXB($rxb, $yhb);
        if ($rxb == null) {
            return;
        }
        final Image $uyb = this.$UYB;
        this.$UYB = $FXB.createImage(this.$MXB, this.$UYB, $yhb.width, $yhb.height);
        if (this.$UYB == null || this.$UYB.getWidth(null) < 1 || this.$UYB.getHeight(null) < 1) {
            return;
        }
        if ($uyb != this.$UYB || this.$XYB == null) {
            $rxb = rectangle;
            synchronized (this.$ZYB) {
                this.$XYB = null;
            }
            // monitorexit(this.$ZYB)
        }
        final Graphics create = this.$UYB.getGraphics().create();
        try {
            Label_0336: {
                if (this.$XYB != null && this.$VYB != null && this.$VYB.getWidth(null) == $yhb.width && this.$VYB.getHeight(null) == $yhb.height) {
                    final Rectangle $sxb = $QXB.$SXB(this.$XYB, $rxb);
                    if ($sxb == null) {
                        break Label_0336;
                    }
                    final Graphics create2 = create.create();
                    try {
                        create2.clipRect($sxb.x, $sxb.y, $sxb.width, $sxb.height);
                        create2.drawImage(this.$VYB, 0, 0, create2.getColor(), null);
                        break Label_0336;
                    }
                    finally {
                        create2.dispose();
                    }
                }
                $rxb = rectangle;
                this.$AZB().flush();
            }
            synchronized (this.$ZYB) {
                this.$XYB = null;
            }
            // monitorexit(this.$ZYB)
            create.clipRect($rxb.x, $rxb.y, $rxb.width, $rxb.height);
            this.$NXB.$XHB(create);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            create.dispose();
        }
        synchronized (this.$ZYB) {
            this.$XYB = $rxb;
            final Image $vyb = this.$VYB;
            this.$VYB = this.$UYB;
            this.$UYB = $vyb;
        }
        // monitorexit(this.$ZYB)
        if (this.$QO != null && this.$QO.size() > 0) {
            final $VWB $vwb = new $VWB(this, $rxb, this);
            final Enumeration<$TWB> elements = (Enumeration<$TWB>)((Vector)this.$QO.clone()).elements();
            while (elements.hasMoreElements()) {
                final $TWB $twb = elements.nextElement();
                try {
                    $twb.$UWB($vwb);
                }
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
            }
        }
    }
    
    public void $WXB(final $TWB $twb) {
        if (!this.$QO.removeElement($twb)) {
            throw new $SWB("listener was not registered:" + $twb);
        }
    }
    
    public $GXB(final $BXB $nxb, final $WWB $mxb) {
        this.$UYB = null;
        this.$VYB = null;
        this.$WYB = null;
        this.$OR = null;
        this.$XYB = null;
        this.$YYB = false;
        this.$QO = null;
        this.$RYB = null;
        this.$GZB = new Object();
        this.$KZB = new Object();
        if ($nxb == null) {
            throw new $SWB("OffScreenPainter=null");
        }
        this.$NXB = $nxb;
        if ($mxb == null) {
            throw new $SWB("OffScreenImageCreator=null");
        }
        this.$MXB = $mxb;
        this.$ZYB = new Object();
        this.$QO = new Vector();
    }
    
    public void finalize() {
        this.$QO.removeAllElements();
        this.flush();
    }
    
    public Rectangle flush() {
        synchronized (this.$GZB) {
            final boolean $yyb = this.$YYB;
            this.stop();
            this.$YYB = $yyb;
        }
        // monitorexit(this.$GZB)
        final Rectangle flush = this.$AZB().flush();
        synchronized (this.$ZYB) {
            if (this.$UYB != null) {
                this.$UYB.flush();
                this.$UYB = null;
            }
            if (this.$VYB != null) {
                this.$VYB.flush();
                this.$VYB = null;
            }
            this.$XYB = null;
        }
        // monitorexit(this.$ZYB)
        return flush;
    }
    
    public Dimension getScreenSize() {
        return this.$NXB.$YHB();
    }
    
    public void run() {
        final Thread currentThread = Thread.currentThread();
        try {
            while (currentThread == this.$OR) {
                if ($HZB.isInterrupted(currentThread)) {
                    break;
                }
                final Rectangle $pyb = this.$AZB().$PYB(250, this.$RYB);
                synchronized (this.$KZB) {
                    this.$LZB($pyb);
                }
                // monitorexit(this.$KZB)
                Thread.currentThread();
                Thread.yield();
            }
        }
        catch (InterruptedException ex2) {}
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            if (currentThread == this.$OR) {
                this.$OR = null;
            }
            synchronized (currentThread) {
                currentThread.notifyAll();
            }
        }
    }
    
    public void start() {
        synchronized (this.$GZB) {
            this.$YYB = false;
            if (this.$OR == null && !this.$AZB().isEmpty()) {
                (this.$OR = new Thread(this, "OffScreenPainter:" + this.$NXB)).start();
            }
        }
        // monitorexit(this.$GZB)
    }
    
    public void stop() {
        final Thread $or;
        synchronized (this.$GZB) {
            this.$YYB = true;
            $or = this.$OR;
            this.$OR = null;
            if ($or == null) {
                // monitorexit(this.$GZB)
                return;
            }
        }
        // monitorexit(this.$GZB)
        $HZB.interrupt($or);
        synchronized ($or) {
            if ($or.isAlive()) {
                try {
                    $or.wait(5000L);
                    if ($or.isAlive()) {
                        $or.stop();
                    }
                }
                catch (InterruptedException ex) {}
            }
        }
        // monitorexit($or)
    }
}
