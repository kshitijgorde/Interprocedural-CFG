// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.c;

import java.awt.Color;
import java.awt.Graphics;
import com.easypano.tw.g;

public class j extends c
{
    public j(final g g) {
        super(g, false);
    }
    
    public void b(final Graphics graphics) {
        if (super.k != null) {
            graphics.setColor(super.k.getBackground());
            graphics.fillRect(0, 0, super.k.getBounds().width, super.k.getBounds().height);
        }
    }
    
    public void d(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.drawRect(0, 0, super.k.getBounds().width - 1, super.k.getBounds().height - 1);
    }
}
