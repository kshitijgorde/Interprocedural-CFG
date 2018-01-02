// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.awt.Graphics;

public class j extends i
{
    public j() {
        super.I.setSize(2, 2);
    }
    
    public void c(final Graphics graphics) {
        graphics.setColor(i.D);
        graphics.drawLine(0, 0, super.I.width, 0);
        graphics.setColor(i.E);
        graphics.drawLine(0, 1, super.I.width, 1);
    }
}
