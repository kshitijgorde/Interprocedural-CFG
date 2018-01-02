// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$Z7;

import java.awt.image.ImageProducer;
import java.awt.image.FilteredImageSource;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.image.ImageFilter;
import java.awt.Dimension;
import java.awt.Image;

public class $QCB extends $QBB
{
    Image image;
    Image $ZAB;
    Image $ABB;
    Image $BBB;
    boolean $J$;
    boolean $CBB;
    boolean $DBB;
    boolean $EBB;
    Dimension $B_;
    ImageFilter $UCB;
    ImageFilter $WCB;
    ImageObserver $YCB;
    
    ImageFilter $RCB() {
        if (this.$UCB == null) {
            this.$UCB = new $VCB();
        }
        return this.$UCB;
    }
    
    protected Dimension $SBB(final Graphics graphics) {
        final Image image = this.image;
        if (image == null) {
            return new Dimension(0, 0);
        }
        if (this.$B_ != null) {
            return new Dimension(this.$B_);
        }
        return new Dimension(image.getWidth(this.$TCB()), image.getHeight(this.$TCB()));
    }
    
    ImageFilter $SCB() {
        if (this.$WCB == null) {
            this.$WCB = new $XCB(this);
        }
        return this.$WCB;
    }
    
    protected void $TBB(final Graphics graphics, final int n, final int n2) {
        final Dimension $sbb = this.$SBB(graphics);
        if ($sbb.width < 0 || $sbb.height < 0) {
            return;
        }
        if (this.isEnabled()) {
            Image image;
            if (super.$NCB == 2 && this.$ABB != null) {
                image = this.$ABB;
            }
            else if (super.$NCB == 0 && this.image != null) {
                image = this.image;
            }
            else {
                image = this.$ZAB;
            }
            graphics.drawImage(image, n, n2, $sbb.width, $sbb.height, null, this);
        }
        else if (this.$BBB != null) {
            graphics.drawImage(this.$BBB, n, n2, $sbb.width, $sbb.height, null, this);
        }
    }
    
    ImageObserver $TCB() {
        if (this.$YCB == null) {
            this.$YCB = new $ZCB(this);
        }
        return this.$YCB;
    }
    
    public $QCB(final Image image, final String s) {
        this(image, null, s);
    }
    
    public $QCB(final Image image, final String s, final String s2) {
        super(s, s2);
        this.image = null;
        this.$ZAB = null;
        this.$ABB = null;
        this.$BBB = null;
        this.$J$ = false;
        this.$CBB = false;
        this.$DBB = false;
        this.$EBB = false;
        this.$B_ = null;
        this.$UCB = null;
        this.$WCB = null;
        this.$YCB = null;
        this.setImage(null, image, null, null);
    }
    
    public $QCB(final String s, final String s2, final $XAB $xab) {
        super(s, s2, $xab);
        this.image = null;
        this.$ZAB = null;
        this.$ABB = null;
        this.$BBB = null;
        this.$J$ = false;
        this.$CBB = false;
        this.$DBB = false;
        this.$EBB = false;
        this.$B_ = null;
        this.$UCB = null;
        this.$WCB = null;
        this.$YCB = null;
        this.$B_ = $xab.$B_;
        this.$J$ = $xab.$J$;
        this.$CBB = $xab.$CBB;
        this.$EBB = $xab.$EBB;
        this.$DBB = $xab.$DBB;
        this.setImage($xab.image, $xab.$ZAB, $xab.$ABB, $xab.$BBB);
    }
    
    public void dispose() {
        this.setImage(null, null, null, null);
    }
    
    public void setImage(Image image, final Image $zab, Image $abb, Image image2) {
        if (this.$ZAB != $zab) {
            if (this.$ZAB != null && this.$CBB) {
                this.$ZAB.flush();
            }
            this.$ZAB = $zab;
            if (image == null && $zab != null) {
                image = this.createImage(new FilteredImageSource($zab.getSource(), this.$RCB()));
                this.prepareImage(image, null);
                this.$J$ = true;
            }
            if ($abb == null && $zab != null) {
                $abb = $zab;
                this.$DBB = false;
            }
            if (image2 == null && image != null) {
                image2 = this.createImage(new FilteredImageSource(image.getSource(), this.$SCB()));
                this.prepareImage(image2, null);
                this.$EBB = true;
            }
        }
        if (this.image != image) {
            if (this.image != null && this.$J$) {
                this.image.flush();
            }
            this.image = image;
        }
        if (this.$ABB != $abb) {
            if (this.$ABB != null && this.$DBB) {
                this.$ABB.flush();
            }
            this.$ABB = $abb;
        }
        if (this.$BBB != image2) {
            if (this.$BBB != null && this.$EBB) {
                this.$BBB.flush();
            }
            this.$BBB = image2;
        }
    }
    
    public void setImage(final Image image, final boolean b) {
        this.setImage(null, image, image, null);
        this.$CBB = b;
        this.$DBB = b;
    }
}
