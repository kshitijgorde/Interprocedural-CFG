// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt;

import java.awt.AWTError;
import java.awt.Image;
import java.awt.image.ImageObserver;

class $V0 implements ImageObserver
{
    static final int $Z0 = 224;
    static final int $AAB = 35;
    Image image;
    ImageObserver $BAB;
    
    public $V0(final ImageObserver $bab, final Image image) {
        this.$BAB = null;
        if ($bab == null) {
            throw new AWTError("image_observer");
        }
        if (image == null) {
            throw new AWTError("image");
        }
        this.$BAB = $bab;
        this.image = image;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image != this.image) {
            return false;
        }
        if ((n & 0x23) != 0x0 && image.getWidth(null) != -1 && image.getHeight(null) != -1) {
            this.image = null;
            this.$BAB.imageUpdate(image, n, n2, n3, n4, n5);
            return false;
        }
        return (n & 0xE0) == 0x0;
    }
}
