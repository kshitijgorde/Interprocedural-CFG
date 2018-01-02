// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Component;

public final class cp extends Component
{
    private float q;
    private float w;
    private float e;
    private float[] q;
    private int q;
    private int w;
    private int e;
    private int r;
    private int t;
    
    protected cp(final int n, final int r, final int n2, final float n3, final float n4, final float n5) {
        this.r = r;
        this.t = 150;
        this.q = 0.0f;
        this.w = 0.0f;
        this.e = 0.0f;
        this.q = new float[3];
        this.q(n, n3, n4, n5);
    }
    
    public final void q(final int e, float e2, final float n, final float n2) {
        this.e = e;
        this.q = -1;
        this.w = 0;
        e2 = e2;
        this.q = e2;
        e2 = n;
        this.w = e2;
        e2 = n2;
        this.e = e2;
    }
    
    public final void q(final float q) {
        this.q = q;
    }
    
    public final void w(final float w) {
        this.w = w;
    }
    
    public final void e(final float e) {
        this.e = e;
    }
    
    public final float q() {
        return this.q;
    }
    
    public final float w() {
        return this.w;
    }
    
    public final float e() {
        return this.e;
    }
    
    public final void q(final int n, final int n2, final float[] array) {
        switch (this.e) {
            case 0: {
                final float n3 = n / this.r;
                final float n4 = n2 / this.t;
                array[0] = this.q;
                array[1] = this.w - n3;
                array[2] = this.e - n4;
            }
            case 1: {
                final float n5 = n2 / this.t;
                array[0] = n * (1.0f / this.r);
                array[1] = this.w;
                array[2] = 1.0f - n5;
            }
            case 2: {
                final float n6 = n2 / this.t;
                array[0] = n * (1.0f / this.r);
                array[1] = 1.0f - n6;
                array[2] = this.e;
            }
            case 3: {
                array[0] = n2 * (1.0f / this.t);
                array[1] = this.w;
                array[2] = this.e;
            }
            case 4: {
                final float n7 = n2 / this.t;
                array[0] = this.q;
                array[1] = this.w - n7;
                array[2] = this.e;
            }
            case 5: {
                final float n8 = n2 / this.t;
                array[0] = this.q;
                array[1] = this.w;
                array[2] = this.e - n8;
                break;
            }
        }
    }
    
    protected final Image q(final Image image) {
        final Graphics graphics = image.getGraphics();
        for (int i = 0; i < this.t; ++i) {
            for (int j = 0; j < this.r; ++j) {
                final int n = j;
                final int q = i;
                final int n2 = n;
                int n3;
                if (this.e >= 3 && q == this.q) {
                    n3 = this.w;
                }
                else {
                    this.q(n2, q, this.q);
                    this.q = q;
                    this.w = Color.HSBtoRGB(this.q[0], this.q[1], this.q[2]);
                    n3 = this.w;
                }
                graphics.setColor(new Color(n3));
                graphics.drawLine(j, i, j + 1, i);
            }
        }
        return image;
    }
}
