// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.d;

import java.awt.Graphics;
import java.awt.Cursor;
import com.easypano.tw.i;

public class h extends c
{
    protected static final int S = 15;
    protected i T;
    
    public h(final i t) {
        super(t, false);
        this.T = null;
        (this.T = t).b(false);
        this.T.setCursor(Cursor.getPredefinedCursor(12));
    }
    
    public void e(final Graphics graphics) {
        h h = this;
        if (!com.easypano.tw.d.a.O) {
            if (super.H == null) {
                return;
            }
            h = this;
        }
        h.e(graphics);
    }
    
    public void destroyResource() {
        super.destroyResource();
        this.T = null;
    }
}
