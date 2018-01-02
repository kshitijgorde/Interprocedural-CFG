// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.Component;
import java.awt.Panel;

class Viewport extends Panel
{
    Component child;
    int horiz_origin;
    int vert_origin;
    
    Viewport() {
        this.setLayout(null);
    }
    
    public Component add(final Component child) {
        if (this.child != null) {
            this.remove(this.child);
        }
        super.add(this.child = child);
        return child;
    }
    
    public Component add(final String s, final Component child) {
        if (this.child != null) {
            this.remove(this.child);
        }
        super.add(s, this.child = child);
        return child;
    }
    
    public Component add(final Component child, final int n) {
        if (this.child != null) {
            this.remove(this.child);
        }
        super.add(this.child = child, n);
        return child;
    }
    
    public void add(final Component child, final Object o) {
        if (this.child != null) {
            this.remove(this.child);
        }
        super.add(this.child = child, o);
    }
    
    public void add(final Component child, final Object o, final int n) {
        if (this.child != null) {
            this.remove(this.child);
        }
        super.add(this.child = child, o, n);
    }
    
    public Dimension preferredSize() {
        if (this.child != null) {
            return this.child.preferredSize();
        }
        return super.preferredSize();
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        synchronized (this) {
            if (n == this.location().x && n2 == this.location().y && n3 == this.size().width && n4 == this.size().height) {
                // monitorexit(this)
                return;
            }
        }
        super.reshape(n, n2, n3, n4);
        if (this.child != null) {
            this.child.resize(Math.max(n3, this.child.size().width), Math.max(n4, this.child.size().height));
        }
    }
    
    public void addNotify() {
        super.addNotify();
        if (this.child != null) {
            this.child.resize(this.child.preferredSize().width, this.child.preferredSize().height);
        }
    }
    
    public void scrollHorizontal(final int n) {
        if (this.child != null) {
            this.child.move(-n, this.child.location().y);
        }
    }
    
    public void scrollVertical(final int n) {
        if (this.child != null) {
            this.child.move(this.child.location().x, -n);
        }
    }
}
