// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.applet;

import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import com.cc.gui.D;
import java.awt.Component;
import com.cc.D.C;
import com.cc.C.A;
import java.awt.Graphics;
import java.awt.Image;
import com.cc.gui.B;
import java.awt.Panel;

public class E extends Panel
{
    public final B C;
    private Image B;
    private Graphics A;
    
    public E(final A a, final com.cc.D.B b, final Grid_int grid_int, final com.cc.B.A a2, final C c) {
        this.B = null;
        this.C = new B(this, grid_int.J, a, b, a2, c);
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        this.C.A(n, n2, n3, n4);
        super.setBounds(n, n2, n3, n4);
    }
    
    public com.cc.D.D[] B() {
        return this.C.B();
    }
    
    public int D() {
        return this.C.A();
    }
    
    public com.cc.D.B C() {
        return this.C.E();
    }
    
    public void update(final Graphics graphics) {
        this.C.D();
        this.A(graphics, true);
    }
    
    public void paint(final Graphics graphics) {
        this.A(graphics, false);
    }
    
    public void A(final Graphics graphics, final boolean b) {
        if (this.B == null) {
            final Rectangle bounds = this.getBounds();
            this.B = this.createImage(bounds.width, bounds.height);
        }
        else if (!b) {
            graphics.drawImage(this.B, 0, 0, this);
            return;
        }
        this.A = this.B.getGraphics();
        this.C.A(this.A);
        graphics.drawImage(this.B, 0, 0, this);
        this.A.dispose();
    }
    
    public boolean A() {
        return this.C.I();
    }
}
