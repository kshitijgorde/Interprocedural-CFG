// 
// Decompiled by Procyon v0.5.30
// 

package ABLjemsty;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.Container;
import ABLwidgets.run_prefix;
import java.awt.Panel;

public class TipTarget extends Panel implements run_prefix
{
    public String a;
    public Container b;
    
    public TipTarget(final String s) {
        this(null, s);
    }
    
    public TipTarget(final Container b, final String a) {
        this.a = "";
        this.b = b;
        this.a = a;
    }
    
    public String a() {
        return this.a;
    }
    
    public Component add(final Component component) {
        if (this.b != null) {
            this.b.add(component);
        }
        return component;
    }
    
    public Component add(final Component component, final int n) {
        if (this.b != null) {
            this.b.add(component, n);
        }
        return component;
    }
    
    public void remove(final Component component) {
        if (this.b != null) {
            this.b.remove(component);
        }
    }
    
    public Dimension getSize() {
        if (this.b == null) {
            return new Dimension(0, 0);
        }
        return this.b.getSize();
    }
    
    public Insets getInsets() {
        if (this.b == null) {
            return new Insets(0, 0, 0, 0);
        }
        return this.b.getInsets();
    }
}
