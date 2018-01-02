// 
// Decompiled by Procyon v0.5.30
// 

package dlt.mandelbrot.a;

import java.awt.Color;

public class i extends g
{
    public int if() {
        return 384;
    }
    
    public Color a(int if1) {
        Color color;
        if (if1 != 0) {
            if1 = this.if(if1);
            if (if1 < 256) {
                color = new Color(255, if1, if1);
            }
            else {
                final int n = 255 - (if1 / 2 - 128) * 2;
                color = new Color(n, n, n);
            }
        }
        else {
            color = new Color(0, 0, 0);
        }
        return color;
    }
    
    public String toString() {
        return new String("Mix 5");
    }
}
