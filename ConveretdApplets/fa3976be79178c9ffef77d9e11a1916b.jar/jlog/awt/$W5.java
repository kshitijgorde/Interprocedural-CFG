// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt;

import java.awt.Component;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import jlog.awt.image.$A6;
import jlog.awt.image.$Q0;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.ImageObserver;
import jlog.$T5.util.$U5;

public class $W5 extends $U5 implements ImageObserver
{
    public static final String $V5 = "PROP_IMAGE";
    public static final String $B5 = "PROP_SIZE";
    private Image image;
    private Dimension $Q1;
    static $Q0 $R0;
    private static boolean ie_bug;
    public boolean $S0;
    
    private void $W0() {
        final Dimension $q1 = this.$Q1;
        Dimension size = null;
        final Image image = this.image;
        if (image != null) {
            size = new Dimension(image.getWidth(null), image.getHeight(null));
            if (size.width < 0 || size.height < 0) {
                this.firePropertyChange("PROP_SIZE", $q1, this.$Q1 = new Dimension(-1, -1));
                size = this.getSize(this);
                if (size == null) {
                    return;
                }
            }
        }
        this.firePropertyChange("PROP_SIZE", $q1, this.$Q1 = size);
    }
    
    public static void $X0(final $Q0 $r0) {
        $W5.$R0 = $r0;
    }
    
    public static $Q0 $Y0() {
        return $W5.$R0;
    }
    
    static {
        $W5.$R0 = null;
        $W5.ie_bug = false;
    }
    
    public $W5() {
        this.image = null;
        this.$Q1 = null;
        this.$S0 = true;
        this.setSource(this);
        $W5.ie_bug = (System.getProperty("java.vendor", "").indexOf("icrosoft") != -1);
    }
    
    public $W5(final Image image) {
        this();
        this.setImage(image);
    }
    
    public $W5(final String image) throws $A6 {
        this();
        this.setImage(image);
    }
    
    public $W5(final URL image) throws $A6 {
        this();
        this.setImage(image);
    }
    
    public void addPropertyChangeListener(final PropertyChangeListener propertyChangeListener, final boolean b) {
        super.addPropertyChangeListener(propertyChangeListener, false);
        if (!b) {
            return;
        }
        propertyChangeListener.propertyChange(new PropertyChangeEvent(this, "PROP_IMAGE", null, this.image));
        propertyChangeListener.propertyChange(new PropertyChangeEvent(this, "PROP_SIZE", null, this.$Q1));
    }
    
    public static Image createImage(final String s) throws $A6 {
        if (s == null) {
            throw new NullPointerException("can not create an Image for filename=null");
        }
        if ($W5.$R0 != null) {
            return $W5.$R0.createImage(s);
        }
        return Toolkit.getDefaultToolkit().getImage(s);
    }
    
    public static Image createImage(final URL url) throws $A6 {
        if (url == null) {
            throw new NullPointerException("can not create an Image for url=null");
        }
        if ($W5.$R0 != null) {
            return $W5.$R0.createImage(url);
        }
        return Toolkit.getDefaultToolkit().getImage(url);
    }
    
    public boolean drawImage(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Color color, final Component component, final long n5) {
        return drawImage(graphics, this.image, n, n2, n3, n4, color, component, n5);
    }
    
    public static boolean drawImage(final Graphics graphics, final Image image, final int n, final int n2, final int n3, final int n4, final Color color, final Component component, final long n5) {
        boolean drawImage = false;
        if (image != null) {
            if (!$W5.ie_bug || Toolkit.getDefaultToolkit().prepareImage(image, n3, n4, null)) {
                drawImage = graphics.drawImage(image, n, n2, n3, n4, color, null);
            }
            if (!drawImage && component != null && n5 > 0L) {
                component.repaint(n5, n, n2, n3, n4);
            }
        }
        return drawImage;
    }
    
    public void finalize() {
        this.setImage((Image)null);
    }
    
    public Image getImage() {
        return this.image;
    }
    
    public Dimension getSize(ImageObserver imageObserver) {
        Dimension dimension = null;
        final Image image = this.image;
        if (image != null) {
            if (imageObserver != null) {
                imageObserver = new $V0(imageObserver, image);
            }
            final int width = image.getWidth(imageObserver);
            final int height = image.getHeight(imageObserver);
            if (width != -1 && height != -1) {
                dimension = new Dimension(width, height);
            }
        }
        return dimension;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == this.image && (n & 0x23) != 0x0 && (n & 0xC0) == 0x0) {
            this.$W0();
        }
        return false;
    }
    
    public void setImage(final Image image) {
        final Image image2 = this.image;
        if (image2 == image) {
            return;
        }
        if (image2 != null) {
            if (this.$S0) {
                image2.flush();
            }
            this.$Q1 = null;
        }
        this.firePropertyChange("PROP_IMAGE", image2, this.image = image);
        this.$W0();
    }
    
    public void setImage(final String s) throws $A6 {
        this.setImage((s == null) ? null : createImage(s));
    }
    
    public void setImage(final URL url) throws $A6 {
        this.setImage((url == null) ? null : createImage(url));
    }
    
    public String toString() {
        return String.valueOf(super.toString()) + "[image=" + this.image + ",dim=" + this.$Q1 + ",owns_image=" + this.$S0 + "]";
    }
}
