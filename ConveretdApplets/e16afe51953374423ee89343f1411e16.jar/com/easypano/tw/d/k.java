// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.d;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import com.easypano.tw.f;

public class k implements p
{
    protected f a;
    protected Insets b;
    
    public k(final f a) {
        this.a = null;
        this.b = new Insets(0, 0, 0, 0);
        this.a = a;
    }
    
    public void a(final Graphics graphics) {
        this.b(graphics);
        this.c(graphics);
        this.d(graphics);
    }
    
    public void b(final Graphics graphics) {
        if (this.a.isOpaque()) {
            graphics.setColor(this.a.getBackground());
            graphics.fillRect(0, 0, this.a.getBounds().width, this.a.getBounds().height);
        }
    }
    
    public void c(final Graphics graphics) {
    }
    
    public void d(final Graphics graphics) {
    }
    
    public Dimension a() {
        return null;
    }
    
    public Insets b() {
        return this.b;
    }
    
    public void a(final Insets b) {
        this.b = b;
    }
    
    public void destroyResource() {
        this.a = null;
    }
}
