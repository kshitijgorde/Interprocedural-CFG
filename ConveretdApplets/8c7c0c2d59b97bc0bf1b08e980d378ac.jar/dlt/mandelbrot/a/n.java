// 
// Decompiled by Procyon v0.5.30
// 

package dlt.mandelbrot.a;

import java.awt.Color;

public class n extends g
{
    public int if() {
        return 255;
    }
    
    public Color a(int if1) {
        Color color;
        if (if1 != 0) {
            if1 *= 8;
            if1 = this.if(if1);
            color = new Color(128 + if1 / 2, 0, 0);
        }
        else {
            color = new Color(0, 0, 0);
        }
        return color;
    }
    
    public String toString() {
        return new String("Red");
    }
}