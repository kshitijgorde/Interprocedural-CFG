// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.c;

import java.awt.Color;
import java.awt.Graphics;
import com.easypano.tw.g;

public class b extends a
{
    private int[] P;
    private int[] Q;
    
    public b(final g g) {
        super(g);
        this.P = new int[] { 4, 16, 10 };
        this.Q = new int[] { 7, 7, 13 };
    }
    
    protected void e(final Graphics graphics) {
        final boolean o = com.easypano.tw.c.a.O;
        b b = this;
        Label_0024: {
            if (!o) {
                if (super.H == null) {
                    break Label_0024;
                }
                b = this;
            }
            b.e(graphics);
            if (!o) {
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
