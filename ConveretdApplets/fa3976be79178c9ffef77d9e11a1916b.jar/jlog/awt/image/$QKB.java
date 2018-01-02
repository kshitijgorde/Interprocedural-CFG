// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.image;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.ImageObserver;

public class $QKB extends $RKB
{
    public $QKB(final ImageObserver imageObserver) {
        super(imageObserver, 3);
    }
    
    public static Dimension getImageSize(final Image image, final ImageObserver imageObserver) {
        if (image == null) {
            return new Dimension(0, 0);
        }
        final int width = image.getWidth(imageObserver);
        final int height = image.getHeight(imageObserver);
        if (width < 0 || height < 0) {
            return new Dimension(-1, -1);
        }
        return new Dimension(width, height);
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        final ImageObserver $u0 = super.$U0;
        final int $skb = super.$SKB;
        return $u0 != null && $skb != 0 && ((n & $skb) == 0x0 || image.getWidth(null) == -1 || image.getHeight(null) == -1 || $u0.imageUpdate(image, n, n2, n3, n4, n5)) && (n & 0xE0) == 0x0;
    }
}
