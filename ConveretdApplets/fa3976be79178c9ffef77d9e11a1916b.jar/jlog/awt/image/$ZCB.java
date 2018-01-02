// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.image;

import java.awt.Image;
import java.awt.image.ImageObserver;

class $ZCB implements ImageObserver
{
    $WKB $ALB;
    
    $ZCB(final $WKB $alb) {
        this.$ALB = $alb;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        this.$ALB.$VLB();
        return false;
    }
}
