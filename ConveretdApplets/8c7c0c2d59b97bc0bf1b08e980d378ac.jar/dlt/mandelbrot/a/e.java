// 
// Decompiled by Procyon v0.5.30
// 

package dlt.mandelbrot.a;

import java.awt.Color;

public class e extends g
{
    public int if() {
        return 255;
    }
    
    public Color a(int if1) {
        Color color;
        if (if1 != 0) {
            if1 = this.if(if1);
            if (if1 % 2 == 0) {
                color = Color.getHSBColor(if1 / 256.0f, 1.0f, 1.0f);
            }
            else {
                color = Color.getHSBColor(if1 / 256.0f, 0.5f, 1.0f);
            }
        }
        else {
            color = new Color(0, 0, 0);
        }
        return color;
    }
    
    public String toString() {
        return new String("Alternating HSB");
    }
}
