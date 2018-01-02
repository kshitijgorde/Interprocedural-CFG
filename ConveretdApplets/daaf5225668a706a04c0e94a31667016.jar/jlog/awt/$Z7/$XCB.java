// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$Z7;

import java.awt.Color;
import java.awt.Component;
import java.awt.image.RGBImageFilter;

class $XCB extends RGBImageFilter
{
    Component $OU;
    Color $YH;
    int $G1;
    
    public $XCB(final Component $ou) {
        this.$YH = null;
        this.$G1 = 0;
        this.$OU = $ou;
        super.canFilterIndexColorModel = false;
    }
    
    public int filterRGB(final int n, final int n2, final int n3) {
        if (this.$OU.getBackground() != this.$YH) {
            this.$YH = this.$OU.getBackground();
            this.$G1 = (0xFF000000 | this.$YH.getRed() << 16 | this.$YH.getGreen() << 8 | this.$YH.getBlue());
        }
        if ((n + n2 % 2) % 2 != 0) {
            final int n4 = ((n3 >> 16 & 0xFF) + (n3 >> 8 & 0xFF) + (n3 & 0xFF)) / 3;
            return (n3 & 0xFF000000) | n4 << 16 | n4 << 8 | n4;
        }
        return this.$G1;
    }
}
