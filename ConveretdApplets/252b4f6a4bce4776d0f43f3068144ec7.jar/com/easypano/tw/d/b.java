// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.d;

import java.awt.Color;
import java.awt.Graphics;
import com.easypano.tw.h;

public class b extends a
{
    private int[] P;
    private int[] Q;
    
    public b(final h h) {
        super(h);
        this.P = new int[] { 4, 16, 10 };
        this.Q = new int[] { 7, 7, 13 };
    }
    
    protected void e(final Graphics graphics) {
        final int o = com.easypano.tw.d.a.O;
        b b = this;
        Label_0024: {
            if (o == 0) {
                if (super.H == null) {
                    break Label_0024;
                }
                b = this;
            }
            b.e(graphics);
            if (o == 0) {
                return;
            }
        }
        graphics.setColor(Color.black);
        graphics.fillPolygon(this.P, this.Q, 3);
    }
    
    public void destroyResource() {
        super.destroyResource();
        this.P = null;
        this.Q = null;
    }
}
