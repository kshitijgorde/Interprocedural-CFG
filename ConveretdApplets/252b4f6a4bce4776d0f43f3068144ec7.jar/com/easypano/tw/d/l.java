// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.d;

import java.awt.Graphics;
import com.easypano.tw.f;
import java.awt.Color;

public class l extends k
{
    protected Color c;
    
    public l(final com.easypano.tw.k k) {
        super(k);
        this.c = Color.black;
    }
    
    public void d(final Graphics graphics) {
        if (this.c != null) {
            graphics.setColor(this.c);
            graphics.drawRect(0, 0, super.a.getBounds().width - 1, super.a.getBounds().height - 1);
        }
    }
    
    public void a(final Color c) {
        if (c != null) {
            this.c = c;
        }
    }
}
