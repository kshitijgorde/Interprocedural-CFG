// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.applet;

import java.awt.Graphics;
import com.cc.gui.D;
import java.awt.Component;
import com.cc.D.C;
import com.cc.C.A;
import com.cc.gui.B;
import java.awt.Panel;

public class E extends Panel
{
    public final B A;
    
    public E(final A a, final com.cc.D.B b, final Grid_int grid_int, final com.cc.B.A a2, final C c) {
        this.A = new B(this, grid_int.I, a, b, a2, c);
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        this.A.A(n, n2, n3, n4);
        super.setBounds(n, n2, n3, n4);
    }
    
    public com.cc.D.D[] B() {
        return this.A.B();
    }
    
    public int D() {
        return this.A.A();
    }
    
    public com.cc.D.B C() {
        return this.A.E();
    }
    
    public void update(final Graphics graphics) {
        this.A.D();
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.A.A(graphics);
    }
    
    public boolean A() {
        return this.A.F();
    }
}
