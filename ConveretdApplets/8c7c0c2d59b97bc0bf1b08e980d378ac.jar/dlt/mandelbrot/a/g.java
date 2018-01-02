// 
// Decompiled by Procyon v0.5.30
// 

package dlt.mandelbrot.a;

import java.awt.Color;

public abstract class g implements j
{
    public Color[] a() {
        final Color[] array = new Color[this.if() + 1];
        for (int i = 0; i <= this.if(); ++i) {
            array[i] = this.a(i);
        }
        return array;
    }
    
    public int if(int n) {
        if (n == 0) {
            return 0;
        }
        final int n2 = n / (this.if() - 1);
        n = n % (this.if() - 1) + 1;
        if (n2 % 2 == 1) {
            n = this.if() + 1 - n;
        }
        return n;
    }
}
