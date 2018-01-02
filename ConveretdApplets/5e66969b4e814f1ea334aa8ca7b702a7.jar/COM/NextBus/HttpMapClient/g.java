// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.HttpMapClient;

import java.awt.Color;
import java.awt.image.RGBImageFilter;

public final class g extends RGBImageFilter
{
    private int a;
    private int b;
    private int c;
    private int d;
    private int e;
    
    public g(final Color color, final int e, final int n) {
        if (e >= 2) {
            this.canFilterIndexColorModel = true;
            this.a = color.getRed() * (e - 2);
            this.b = color.getGreen() * (e - 2);
            this.c = color.getBlue() * (e - 2);
            this.d = 2;
            this.e = e;
            return;
        }
        throw new Error("Ratio must be greater than 1:1");
    }
    
    public g() {
        this(Color.white, 5, 2);
    }
    
    public final int filterRGB(final int n, final int n2, final int n3) {
        return (n3 & 0xFF000000) | ((this.a + (n3 >> 16 & 0xFF) * this.d) / this.e & 0xFF) << 16 | ((this.b + (n3 >> 8 & 0xFF) * this.d) / this.e & 0xFF) << 8 | ((this.c + (n3 & 0xFF) * this.d) / this.e & 0xFF);
    }
}
