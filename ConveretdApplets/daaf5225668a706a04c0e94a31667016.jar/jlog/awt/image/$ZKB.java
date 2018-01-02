// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.image;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Component;

public class $ZKB extends Component
{
    $WKB $ALB;
    ImageObserver $BLB;
    $MKB $CLB;
    transient boolean $DLB;
    public static final int $LLB = 24;
    int $MLB;
    int $NLB;
    private static final boolean debug = false;
    private transient int $OLB;
    private transient Color[] $YH;
    private transient long $PLB;
    
    public $WKB $GLB() {
        return this.$ALB;
    }
    
    private void $JLB(final Graphics graphics) {
    }
    
    private void $KLB(final Graphics graphics) {
    }
    
    public $ZKB() {
        this(new $WKB());
    }
    
    public $ZKB(final Image image) {
        this(new $WKB(image));
    }
    
    public $ZKB($WKB $alb) {
        this.$BLB = null;
        this.$CLB = null;
        this.$DLB = false;
        this.$MLB = -24;
        this.$NLB = -24;
        this.$OLB = 0;
        this.$YH = new Color[] { Color.red, Color.blue, Color.green, Color.white };
        this.$PLB = 0L;
        if ($alb == null) {
            $alb = new $WKB();
        }
        this.$ALB = $alb;
        this.$CLB = new $ELB(this);
        this.$ALB.$FLB(this.$CLB);
    }
    
    public Image getImage() {
        return this.$ALB.getImage();
    }
    
    public Dimension getImageSize(final $MKB $mkb) {
        return this.$ALB.getSize($mkb);
    }
    
    public Dimension getPreferredSize() {
        if (this.$MLB < 0 || this.$NLB < 0) {
            final Dimension size = this.$ALB.getSize(this.$CLB);
            if (size.width < 0) {
                size.width = Math.abs(this.$MLB);
            }
            if (size.height < 0) {
                size.height = Math.abs(this.$NLB);
            }
            return size;
        }
        return new Dimension(this.$MLB, this.$NLB);
    }
    
    public void paint(final Graphics graphics) {
        final Image image = this.$ALB.getImage();
        final Dimension size = this.getSize();
        final Dimension size2 = this.$ALB.getSize(null);
        if (image == null || size2.width < 1 || size2.height < 1 || size == null || size.width < 1 || size.height < 1) {
            return;
        }
        final boolean b = size2.width == size.width && size2.height == size.height;
        boolean b2;
        while (true) {
            if (b) {
                b2 = this.prepareImage(image, this.$BLB);
            }
            else {
                b2 = this.prepareImage(image, size.width, size.height, this.$BLB);
            }
            if (b2 || this.$BLB != null) {
                break;
            }
            this.$BLB = new $RKB(new $ILB(this), 48);
        }
        if (this.$DLB || b2) {
            if (b) {
                graphics.drawImage(image, 0, 0, null);
            }
            else {
                graphics.drawImage(image, 0, 0, size.width, size.height, null);
            }
        }
    }
    
    public void setImage(final Image image) {
        this.$ALB.setImage(image);
    }
    
    public void setPreferredSize(final int $mlb, final int $nlb) {
        if (this.$MLB != $mlb || this.$NLB != $nlb) {
            this.$MLB = $mlb;
            this.$NLB = $nlb;
            this.invalidate();
            this.$DLB = false;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
