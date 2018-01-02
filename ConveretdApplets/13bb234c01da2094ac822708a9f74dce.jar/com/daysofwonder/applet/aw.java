// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import com.daysofwonder.b.a;
import com.daysofwonder.b.a.b;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Component;
import com.daysofwonder.util.t;
import javax.swing.JPanel;

public class aw extends JPanel
{
    private ap a;
    private boolean b;
    
    public aw(final ap a) {
        t.a("Panel creation: " + a);
        a.d(true);
        a.a(this);
        this.a = a;
    }
    
    public synchronized void a() {
        this.b = true;
    }
    
    public void b() {
        t.a("Panel release: " + this.a);
        this.a = null;
    }
    
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
    
    public a c() {
        return new b(this.getGraphics());
    }
}
