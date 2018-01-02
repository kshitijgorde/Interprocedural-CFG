// 
// Decompiled by Procyon v0.5.30
// 

package jlog.$T5.$D7.$XDC;

import java.awt.Container;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Component;
import jlog.$T5.$D7.ContainerSupport;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Dimension;
import jlog.awt.$W5;
import jlog.$H4;
import java.awt.image.ImageObserver;

public class $YDC extends $SRC implements ImageObserver, $H4
{
    private $W5 $JZ;
    public boolean $ILD;
    private boolean $R2B;
    private boolean $JLD;
    public long refresh;
    private Dimension $KLD;
    
    public static Rectangle $LLD(final Dimension dimension, final Dimension dimension2) {
        final Dimension dimension3 = new Dimension(dimension);
        final float n = dimension2.width / dimension2.height;
        float n2 = dimension.height;
        float n3 = n * n2;
        if (n3 > dimension.width) {
            n2 *= dimension.width / n3;
            n3 = dimension.width;
        }
        dimension3.setSize((int)n3, (int)n2);
        return new Rectangle((dimension.width - dimension3.width) / 2, (dimension.height - dimension3.height) / 2, dimension3.width, dimension3.height);
    }
    
    public $YDC(final $W5 image) {
        super("");
        this.$JZ = new $W5();
        this.$ILD = true;
        this.$R2B = true;
        this.$JLD = true;
        this.refresh = 1000L;
        this.$KLD = null;
        this.setImage(image);
    }
    
    public $W5 getImage() {
        return this.$JZ;
    }
    
    public Dimension getPreferredSize() {
        if (this.$KLD != null) {
            return this.$KLD;
        }
        if (this.$JZ != null) {
            final Dimension size = this.$JZ.getSize(null);
            if (size != null) {
                return size;
            }
        }
        return super.getPreferredSize();
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (this.$ILD) {
            this.repaint();
        }
        return false;
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (this.$JZ != null) {
            final Dimension size = this.$JZ.getSize(this);
            if (size != null) {
                Dimension size2 = this.getSize();
                if (this.$ILD && (size.width > size2.width || size.height > size2.height)) {
                    size2 = new Dimension(size);
                    this.setSize(size2);
                    this.invalidate();
                    final Container $vjd = ContainerSupport.$VJD(this);
                    if ($vjd != null) {
                        if (this.$JLD && $vjd instanceof Frame) {
                            ((Frame)$vjd).pack();
                        }
                        else {
                            $vjd.validate();
                        }
                    }
                }
                Rectangle $lld;
                if (this.$R2B) {
                    $lld = $LLD(size2, size);
                }
                else {
                    $lld = new Rectangle(0, 0, size2.width, size2.height);
                }
                this.$JZ.drawImage(graphics, $lld.x, $lld.y, $lld.width, $lld.height, null, this, this.refresh);
                return;
            }
        }
        super.paint(graphics);
    }
    
    public void setImage(final $W5 $jz) {
        if ($jz != this.$JZ) {
            this.$JZ = $jz;
        }
    }
    
    public void setPreferredSize(final Dimension $kld) {
        this.$KLD = $kld;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
