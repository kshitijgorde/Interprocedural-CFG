// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.image;

import java.awt.image.ImageProducer;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.Vector;
import java.awt.Image;

public class $WKB extends Image
{
    Image image;
    boolean $RLB;
    Vector $SLB;
    Vector $TLB;
    ImageObserver $ULB;
    Object $WLB;
    
    public void $AMB(final $MKB $mkb) {
        if (this.$SLB == null) {
            return;
        }
        this.$SLB.removeElement($mkb);
    }
    
    public void $FLB(final $MKB $mkb) {
        if (this.$SLB == null) {
            synchronized (this.$WLB) {
                if (this.$SLB == null) {
                    this.$SLB = new Vector();
                }
            }
            // monitorexit(this.$WLB)
        }
        this.$SLB.addElement($mkb);
    }
    
    synchronized void $VLB() {
        final Image image = this.image;
        int width = -1;
        int height = -1;
        if (image != null) {
            this.$XLB();
            width = image.getWidth(this.$ULB);
            height = image.getHeight(this.$ULB);
        }
        final $OKB $okb = new $OKB(this, image, width, height);
        if (this.$SLB != null) {
            final Vector vector = (Vector)this.$SLB.clone();
            for (int i = 0; i < vector.size(); ++i) {
                vector.elementAt(i).$NKB($okb);
            }
        }
        if (this.$TLB != null && $okb.$PKB()) {
            final Vector vector2 = (Vector)this.$TLB.clone();
            for (int j = 0; j < vector2.size(); ++j) {
                vector2.elementAt(j).$NKB($okb);
            }
        }
    }
    
    void $XLB() {
        if (this.$ULB == null && (this.$TLB != null || this.$SLB != null)) {
            this.$ULB = new $QKB(new $ZCB(this));
        }
    }
    
    public void $YLB(final boolean $rlb) {
        this.$RLB = $rlb;
    }
    
    public boolean $ZLB() {
        return this.$RLB;
    }
    
    public $WKB() {
        this.image = null;
        this.$RLB = false;
        this.$SLB = null;
        this.$TLB = null;
        this.$ULB = null;
        this.$WLB = new Object();
    }
    
    public $WKB(final Image image) {
        this.image = null;
        this.$RLB = false;
        this.$SLB = null;
        this.$TLB = null;
        this.$ULB = null;
        this.$WLB = new Object();
        this.image = image;
    }
    
    public void finalize() {
        if (this.$SLB != null) {
            this.$SLB.removeAllElements();
        }
        if (this.$TLB != null) {
            this.$TLB.removeAllElements();
        }
        this.setImage(null);
    }
    
    public void flush() {
        final Image image = this.image;
        if (image != null) {
            image.flush();
        }
    }
    
    public Graphics getGraphics() {
        final Image image = this.image;
        if (image != null) {
            return image.getGraphics();
        }
        return null;
    }
    
    public int getHeight(final ImageObserver imageObserver) {
        final Image image = this.image;
        if (image != null) {
            return image.getHeight(imageObserver);
        }
        return -1;
    }
    
    public Image getImage() {
        return this.image;
    }
    
    public Object getProperty(final String s, final ImageObserver imageObserver) {
        final Image image = this.image;
        if (image != null) {
            return image.getProperty(s, imageObserver);
        }
        return null;
    }
    
    public Dimension getSize(final $MKB $mkb) {
        final Image image = this.image;
        if (image == null) {
            return new Dimension(-1, -1);
        }
        if ($mkb != null) {
            if (this.$TLB == null) {
                synchronized (this.$WLB) {
                    if (this.$TLB == null) {
                        this.$TLB = new Vector();
                    }
                }
                // monitorexit(this.$WLB)
                this.$XLB();
            }
            if (!this.$TLB.contains($mkb)) {
                this.$TLB.addElement($mkb);
            }
        }
        final Dimension dimension = new Dimension(image.getWidth(this.$ULB), image.getHeight(this.$ULB));
        if ($mkb != null && dimension.width > -1 && dimension.height > -1) {
            this.$TLB.removeElement($mkb);
        }
        return dimension;
    }
    
    public ImageProducer getSource() {
        final Image image = this.image;
        if (image != null) {
            return image.getSource();
        }
        return null;
    }
    
    public int getWidth(final ImageObserver imageObserver) {
        final Image image = this.image;
        if (image != null) {
            return image.getWidth(imageObserver);
        }
        return -1;
    }
    
    public void setImage(final Image image) {
        if (image == this.image) {
            return;
        }
        if (this.image != null && this.$RLB) {
            this.image.flush();
        }
        this.image = image;
        if (this.$TLB != null) {
            this.$TLB.removeAllElements();
        }
        this.$VLB();
    }
}
