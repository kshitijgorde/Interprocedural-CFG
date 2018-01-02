// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.c;

import java.awt.Graphics;
import java.awt.Cursor;
import com.easypano.tw.g;

public class h extends c
{
    protected static final int S = 15;
    protected com.easypano.tw.h T;
    
    public h(final com.easypano.tw.h t) {
        super(t, false);
        this.T = null;
        (this.T = t).b(false);
        this.T.setCursor(Cursor.getPredefinedCursor(12));
    }
    
    public void e(final Graphics graphics) {
        h h = this;
        if (!com.easypano.tw.c.a.O) {
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
