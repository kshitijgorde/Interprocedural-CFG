// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$UHB;

import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class $DXB
{
    Image $LXB;
    $WWB $MXB;
    $BXB $NXB;
    
    void $OXB(final Graphics graphics) {
        final Dimension $yhb = this.$NXB.$YHB();
        if ($yhb == null) {
            return;
        }
        this.$LXB = $FXB.createImage(this.$MXB, this.$LXB, $yhb.width, $yhb.height);
        if (this.$LXB == null) {
            return;
        }
        final Graphics graphics2 = this.$LXB.getGraphics();
        try {
            final Rectangle clipRect = graphics.getClipRect();
            if (clipRect == null) {
                graphics2.clipRect(0, 0, $yhb.width, $yhb.height);
            }
            else {
                graphics2.clipRect(clipRect.x, clipRect.y, clipRect.width, clipRect.height);
            }
            this.$PXB(graphics2);
            this.$NXB.$XHB(graphics2);
        }
        finally {
            graphics2.dispose();
        }
    }
    
    void $PXB(final Graphics graphics) {
        graphics.setColor(this.$NXB.getBackground());
        final Dimension $yhb = this.$NXB.$YHB();
        if ($yhb != null) {
            graphics.fillRect(0, 0, $yhb.width, $yhb.height);
        }
    }
    
    public $DXB(final $WWB $mxb, final $BXB $nxb) {
        this.$LXB = null;
        this.$MXB = null;
        this.$NXB = null;
        this.$MXB = $mxb;
        this.$NXB = $nxb;
    }
    
    void flush() {
        if (this.$LXB != null) {
            this.$LXB.flush();
            this.$LXB = null;
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.$LXB == null) {
            this.$OXB(graphics);
        }
        final Image $lxb = this.$LXB;
        if ($lxb == null) {
            this.$PXB(graphics);
        }
        else {
            graphics.drawImage($lxb, 0, 0, null);
        }
    }
    
    public void update(final Graphics graphics) {
        this.$OXB(graphics);
        this.paint(graphics);
    }
}
