// 
// Decompiled by Procyon v0.5.30
// 

package dlt.mandelbrot.a;

import java.awt.Color;

public class b extends g
{
    public Color do(int n) {
        if (n / 254 % 2 == 0) {
            n = n % 254 + 1;
        }
        else {
            n = 256 - (n % 254 + 1);
        }
        return Color.getHSBColor(n / 256.0f, 1.0f, 1.0f);
    }
    
    public int if() {
        return 255;
    }
    
    public Color a(int if1) {
        if1 = this.if(if1);
        return Color.getHSBColor(if1 / 255.0f, 1.0f, 1.0f);
    }
    
    public String toString() {
        return new String("HSB2");
    }
}
