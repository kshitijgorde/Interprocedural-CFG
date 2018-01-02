// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.awt.Graphics;
import java.awt.Panel;

class eb extends Panel
{
    bb a;
    
    public void a(final bb a) {
        if (a == null) {
            return;
        }
        this.a = a;
        this.setSize(a.getPreferredSize());
    }
    
    public void paint(final Graphics graphics) {
        eb eb = this;
        if (!f.u) {
            if (this.a != null) {
                this.a.paint(graphics);
            }
            eb = this;
        }
        eb.paint(graphics);
    }
}
