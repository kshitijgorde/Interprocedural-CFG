// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.applet;

import com.cc.gui.G;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import com.cc.gui.F;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;
import com.cc.D.C;
import com.cc.B.A;
import com.cc.gui.H;
import java.awt.Panel;

public class B extends Panel
{
    H B;
    private boolean A;
    
    public B(final A a, final C c, final com.cc.C.A a2, final boolean b) {
        this.A = false;
        this.B = new H(this, a, c, a2, b, false, a.L);
        this.setBackground(a.o);
    }
    
    public Dimension getPreferredSize() {
        return this.B.D();
    }
    
    public int G() {
        return this.B.J();
    }
    
    public int C() {
        return this.B.N();
    }
    
    public void print(final Graphics graphics) {
        this.B.N = true;
        try {
            this.paint(graphics);
        }
        finally {
            this.B.N = false;
        }
    }
    
    public void update(final Graphics graphics) {
        for (int i = 0; i < this.B.F.length; ++i) {
            for (int j = 0; j < this.B.F[i].length; ++j) {
                if (this.B.F[i][j] != null) {
                    this.B.F[i][j].B = (this.B.A.d && !this.A);
                }
            }
        }
        this.A = false;
        final Enumeration<F> elements = this.B.J.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().B = false;
        }
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.B.I) {
            graphics.setColor(this.B.A._);
            graphics.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        this.B.B(graphics);
    }
    
    public void B(final char c) {
        this.B.B(c);
    }
    
    public boolean A(final KeyEvent keyEvent) {
        return this.B.A(keyEvent);
    }
    
    public void A(final G g) {
        this.B.B(g);
    }
    
    public void A() {
        this.A = true;
        this.B.B();
    }
    
    public void B() {
        this.B.P();
    }
    
    public void E() {
        this.A = true;
        this.B.O();
    }
    
    public void D() {
        this.A = true;
        this.B.A();
        this.repaint();
    }
    
    public void F() {
        this.A = true;
        this.B.G();
    }
    
    public void A(final char c) {
        this.B.A(c);
    }
    
    public void A(final boolean b) {
        this.B.A(b);
    }
}
