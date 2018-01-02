// 
// Decompiled by Procyon v0.5.30
// 

package dlt.mandelbrot.a;

import java.awt.Color;

public class m extends g
{
    public int if() {
        return 255;
    }
    
    public Color a(int if1) {
        Color color;
        if (if1 != 0) {
            if1 = this.if(if1);
            if (if1 < 128) {
                color = new Color(255, if1 * 2, if1 * 2);
            }
            else {
                final int n = 255 - (if1 - 128) * 2;
                color = new Color(n, n, n);
            }
        }
        else {
            color = new Color(0, 0, 0);
        }
        return color;
    }
    
    public String toString() {
        return new String("Mix 3");
    }
}
