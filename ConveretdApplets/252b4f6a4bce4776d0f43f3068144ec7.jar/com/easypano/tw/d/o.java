// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.d;

import java.awt.Graphics;
import com.easypano.tw.f;
import java.awt.Color;
import com.easypano.tw.p;

public class o extends k
{
    com.easypano.tw.p c;
    private Color d;
    
    public o(final com.easypano.tw.p c) {
        super(c);
        this.c = null;
        this.d = Color.black;
        this.c = c;
    }
    
    public void d(final Graphics graphics) {
        if (this.d != null) {
            graphics.setColor(this.d);
            graphics.drawRect(0, 0, this.c.getBounds().width - 1, this.c.getBounds().height - 1);
        }
    }
    
    public void a(final Color d) {
        this.d = d;
    }
    
    public void destroyResource() {
        super.destroyResource();
        this.c = null;
    }
}
