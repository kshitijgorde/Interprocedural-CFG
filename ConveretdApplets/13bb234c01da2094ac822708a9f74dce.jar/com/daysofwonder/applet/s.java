// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import com.daysofwonder.b.a;
import com.daysofwonder.b.a.b;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Panel;

public class s extends Panel
{
    private ap a;
    private boolean b;
    
    public Dimension preferredSize() {
        return this.a.s();
    }
    
    public Dimension minimumSize() {
        return this.a.s();
    }
    
    public void paint(final Graphics graphics) {
        if (this.a != null && this.b) {
            this.a.b(new b(graphics));
        }
    }
    
    public void update(final Graphics graphics) {
        if (this.a != null && this.b) {
            this.a.c(new b(graphics));
        }
    }
    
    public a a() {
        return new b(this.getGraphics());
    }
}
