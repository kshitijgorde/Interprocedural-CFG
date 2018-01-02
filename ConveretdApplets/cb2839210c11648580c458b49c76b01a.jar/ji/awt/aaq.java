// 
// Decompiled by Procyon v0.5.30
// 

package ji.awt;

import java.awt.Color;
import java.awt.image.RGBImageFilter;

class aaq extends RGBImageFilter
{
    public int a;
    private final /* synthetic */ Color b;
    
    aaq(final Color b) {
        this.b = b;
        this.a = (this.b.getRGB() | 0xFF000000);
    }
    
    public final int filterRGB(final int n, final int n2, final int n3) {
        if ((n3 | 0xFF000000) == this.a) {
            return 0xFFFFFF & n3;
        }
        return n3;
    }
}
