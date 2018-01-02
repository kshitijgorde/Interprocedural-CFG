// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.applet;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.Component;
import com.cc.B.A;
import com.cc.gui.C;
import java.awt.Canvas;

public class D extends Canvas
{
    private C A;
    
    public int A() {
        return this.getBounds().x + this.getBounds().width / 2;
    }
    
    public D(final A a, final com.cc.applet.C c) {
        this.addMouseListener(this.A = new C(a, this, c));
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        this.A.A(graphics);
    }
    
    public Dimension getPreferredSize() {
        return this.A.A();
    }
}
