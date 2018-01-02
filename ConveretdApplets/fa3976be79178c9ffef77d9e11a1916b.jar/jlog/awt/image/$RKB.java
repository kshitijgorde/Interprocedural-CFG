// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.image;

import java.awt.Image;
import java.awt.image.ImageObserver;

public class $RKB implements ImageObserver
{
    ImageObserver $U0;
    int $SKB;
    public static final int $Z0 = 224;
    public static final int $AAB = 3;
    public static final int $TKB = 56;
    public static final int $UKB = 48;
    
    public $RKB(final ImageObserver $u0, final int $skb) {
        this.$SKB = $skb;
        this.$U0 = $u0;
    }
    
    public int getFilterFlags() {
        return this.$SKB;
    }
    
    public ImageObserver getObserver() {
        return this.$U0;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        final ImageObserver $u0 = this.$U0;
        final int $skb = this.$SKB;
        return $u0 != null && $skb != 0 && ((n & $skb) == 0x0 || $u0.imageUpdate(image, n, n2, n3, n4, n5)) && (n & 0xE0) == 0x0;
    }
    
    public void setFilterFlags(final int $skb) {
        this.$SKB = $skb;
    }
    
    public void setObserver(final ImageObserver $u0) {
        this.$U0 = $u0;
    }
}
