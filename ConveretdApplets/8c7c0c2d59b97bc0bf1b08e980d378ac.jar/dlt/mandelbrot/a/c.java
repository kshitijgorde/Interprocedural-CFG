// 
// Decompiled by Procyon v0.5.30
// 

package dlt.mandelbrot.a;

import java.awt.Color;

public class c extends g
{
    public Color for(int n) {
        Color hsbColor;
        if (n != 0) {
            if (n / 254 % 2 == 0) {
                n = n % 254 + 1;
            }
            else {
                n = 256 - (n % 254 + 1);
            }
            hsbColor = Color.getHSBColor(n / 256.0f, 1.0f, 1.0f);
        }
        else {
            hsbColor = new Color(0, 0, 0);
        }
        return hsbColor;
    }
    
    public int if() {
        return 255;
    }
    
    public Color a(int if1) {
        Color hsbColor;
        if (if1 != 0) {
            if1 = this.if(if1);
            hsbColor = Color.getHSBColor(if1 / 255.0f, 1.0f, 1.0f);
        }
        else {
            hsbColor = new Color(0, 0, 0);
        }
        return hsbColor;
    }
    
    public String toString() {
        return new String("HSB");
    }
}
