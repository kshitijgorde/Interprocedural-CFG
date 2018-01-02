// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$Z7;

import java.awt.Container;
import java.awt.Image;
import java.awt.Component;
import java.awt.image.ImageObserver;

class $ZCB implements ImageObserver
{
    Component $OU;
    
    $ZCB(final Component $ou) {
        this.$OU = $ou;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image != null && image.getWidth(null) > -1 && image.getHeight(null) > -1) {
            this.$OU.invalidate();
            Container container;
            for (container = this.$OU.getParent(); container.getParent() != null; container = container.getParent()) {}
            if (container != null) {
                container.validate();
            }
            this.$OU.repaint();
            return false;
        }
        return (n & 0xE0) == 0x0;
    }
}
