// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.applet;

import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.Component;
import com.cc.B.A;
import java.awt.Image;
import com.cc.gui.C;
import java.awt.Canvas;

public class D extends Canvas
{
    private C B;
    private Image A;
    
    public int A() {
        return this.getBounds().x + this.getBounds().width / 2;
    }
    
    public D(final A a, final com.cc.applet.C c) {
        this.A = null;
        this.addMouseListener(this.B = new C(a, this, c));
    }
    
    public void paint(final Graphics graphics) {
        if (this.A == null) {
            this.A = this.createImage(this.getBounds().width, this.getBounds().height);
        }
        final Graphics graphics2 = this.A.getGraphics();
        super.paint(graphics2);
        this.B.A(graphics2);
        graphics.drawImage(this.A, 0, 0, this);
        graphics2.dispose();
    }
    
    public Dimension getPreferredSize() {
        return this.B.A();
    }
}
