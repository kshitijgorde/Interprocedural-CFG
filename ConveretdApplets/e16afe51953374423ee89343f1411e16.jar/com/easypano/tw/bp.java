// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Point;
import java.awt.Color;

public class bp extends bo
{
    protected Color d;
    private Point e;
    
    public bp() {
        this.d = new Color(149, 149, 149);
        super.c = this.d;
        this.setBackground(Color.white);
        this.addMouseListener(new z(this));
        this.addMouseMotionListener(new bi(this));
    }
    
    public void a(final Graphics graphics) {
        final Rectangle bounds = this.getBounds();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, bounds.width, bounds.height);
        graphics.setColor(this.d);
        graphics.fillRect(10, 10, bounds.width - 40, 5);
        graphics.drawRect(bounds.width - 22, 5, 16, 16);
        graphics.drawLine(bounds.width - 19, 8, bounds.width - 9, 18);
        graphics.drawLine(bounds.width - 9, 8, bounds.width - 19, 18);
    }
    
    static void a(final bp bp, final Point e) {
        bp.e = e;
    }
    
    static Point a(final bp bp) {
        return bp.e;
    }
}
