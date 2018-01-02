// 
// Decompiled by Procyon v0.5.30
// 

package com.cc.applet;

import com.cc.gui.G;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.Enumeration;
import com.cc.gui.F;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;
import com.cc.D.C;
import com.cc.B.A;
import java.awt.Image;
import com.cc.gui.H;
import java.awt.Panel;

public class B extends Panel
{
    H C;
    private boolean A;
    private Image B;
    
    public B(final A a, final C c, final com.cc.C.A a2, final boolean b) {
        this.A = false;
        this.B = null;
        this.C = new H(this, a, c, a2, b, false, a.L);
        this.setBackground(a.o);
    }
    
    public Dimension getPreferredSize() {
        return this.C.D();
    }
    
    public int G() {
        return this.C.J();
    }
    
    public int C() {
        return this.C.N();
    }
    
    public void print(final Graphics graphics) {
        this.C.N = true;
        try {
            this.paint(graphics);
        }
        finally {
            this.C.N = false;
        }
    }
    
    public void update(final Graphics graphics) {
        for (int i = 0; i < this.C.F.length; ++i) {
            for (int j = 0; j < this.C.F[i].length; ++j) {
                if (this.C.F[i][j] != null) {
                    this.C.F[i][j].B = (this.C.A.d && !this.A);
                }
            }
        }
        this.A = false;
        final Enumeration<F> elements = this.C.J.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().B = false;
        }
        this.A(graphics, true);
    }
    
    public void paint(final Graphics graphics) {
        this.A(graphics, false);
    }
    
    private void A(final Graphics graphics, final boolean b) {
        if (this.B == null && !this.C.N) {
            this.B = this.createImage(this.getBounds().width, this.getBounds().height);
        }
        else if (this.B != null && !b) {
            graphics.drawImage(this.B, 0, 0, this);
            return;
        }
        Graphics graphics2;
        if (this.C.N) {
            graphics2 = graphics;
        }
        else {
            graphics2 = this.B.getGraphics();
        }
        if (this.C.I) {
            graphics2.setColor(this.C.A._);
            graphics2.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        this.C.B(graphics2);
        if (!this.C.N) {
            graphics.drawImage(this.B, 0, 0, this);
            graphics2.dispose();
        }
    }
    
    public void B(final char c) {
        this.C.B(c);
    }
    
    public boolean A(final KeyEvent keyEvent) {
        return this.C.A(keyEvent);
    }
    
    public void A(final G g) {
        this.C.B(g);
    }
    
    public void A() {
        this.A = true;
        this.C.B();
    }
    
    public void B() {
        this.C.P();
    }
    
    public void E() {
        this.A = true;
        this.C.O();
    }
    
    public void D() {
        this.A = true;
        this.C.A();
        this.repaint();
    }
    
    public void F() {
        this.A = true;
        this.C.G();
    }
    
    public void A(final char c) {
        this.C.A(c);
    }
    
    public void A(final boolean b) {
        this.C.A(b);
    }
}
