// 
// Decompiled by Procyon v0.5.30
// 

package dlt.mandelbrot.a;

import java.awt.Color;

public class f extends g
{
    public int if() {
        return 255;
    }
    
    public Color a(int if1) {
        Color hsbColor;
        if (if1 != 0) {
            if1 = this.if(if1 * 8);
            final float[] array = new float[3];
            Color.RGBtoHSB(0, 0, 128 + if1 / 2, array);
            hsbColor = Color.getHSBColor(array[0], array[1], 128 + if1 / 2);
        }
        else {
            hsbColor = new Color(0, 0, 0);
        }
        return hsbColor;
    }
    
    public String toString() {
        return new String("Green 2");
    }
}
