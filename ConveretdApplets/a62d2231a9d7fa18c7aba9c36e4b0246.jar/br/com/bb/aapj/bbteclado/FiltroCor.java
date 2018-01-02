// 
// Decompiled by Procyon v0.5.30
// 

package br.com.bb.aapj.bbteclado;

import java.awt.Color;
import java.awt.image.RGBImageFilter;

public class FiltroCor extends RGBImageFilter
{
    private int a;
    
    public FiltroCor() {
        this.a = 100;
    }
    
    public FiltroCor(final int a) {
        this.a = a;
    }
    
    public int filterRGB(final int n, final int n2, final int n3) {
        final Color color = new Color(n3);
        final int red = color.getRed();
        final int green = color.getGreen();
        final int blue = color.getBlue();
        if ((red == 0 && green == 3 && blue == 148) || (red == 1 && green == 4 && blue == 148) || (red == 0 && green == 0 && blue == 0) || (red == 1 && green == 3 && blue == 148) || (red == 1 && green == 4 && blue == 147) || (red == 2 && green == 5 && blue == 149) || (red == 2 && green == 5 && blue == 148) || (red == 3 && green == 5 && blue == 148) || (red == 4 && green == 6 && blue == 148) || (red == 1 && green == 3 && blue == 147) || (red == 3 && green == 5 && blue == 147) || (red == 3 && green == 6 && blue == 148) || (red == 4 && green == 6 && blue == 147) || (red == 3 && green == 6 && blue == 149)) {
            return -16777216 + (((red + this.a <= 255) ? (red + this.a) : 255) * 256 * 256 + ((green + this.a <= 255) ? (green + this.a) : 255) * 256 + ((blue + this.a <= 255) ? (blue + this.a) : 255));
        }
        return n3;
    }
    
    public void setNivel(final int a) {
        this.a = a;
    }
}
