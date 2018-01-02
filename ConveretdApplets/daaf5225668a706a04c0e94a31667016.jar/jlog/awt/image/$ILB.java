// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.image;

import java.awt.Image;
import java.awt.image.ImageObserver;

class $ILB implements ImageObserver
{
    $ZKB $QLB;
    
    $ILB(final $ZKB $qlb) {
        this.$QLB = $qlb;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        this.$QLB.$DLB = true;
        this.$QLB.repaint(((n & 0x20) == 0x20) ? 0 : 400);
        return true;
    }
}
