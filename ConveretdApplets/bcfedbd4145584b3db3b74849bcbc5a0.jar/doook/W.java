// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.LayoutManager;
import java.awt.Insets;
import java.awt.Font;
import java.awt.TextComponent;
import java.awt.Label;
import java.awt.Color;
import java.awt.Component;
import java.awt.Canvas;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;

public abstract class W extends Panel implements aO
{
    private String m;
    private GridBagLayout a;
    private GridBagConstraints a;
    private boolean h;
    protected Frame b;
    protected boolean e;
    
    public void layout() {
        if (!this.h) {
            this.a.weighty = 1.0E-5;
            this.a.gridheight = 0;
            final Canvas canvas = new Canvas();
            this.a.setConstraints(canvas, this.a);
            this.add(canvas);
            this.h = true;
        }
        super.layout();
    }
    
    public void a(final Component component, final int n) {
        this.a(component, n, 0.0f, 0.0f);
    }
    
    public void a(final Component component, final int fill, final float n, final float n2) {
        this.a.weightx = n;
        this.a.weighty = n2;
        this.a.anchor = 10;
        this.a.fill = fill;
        this.a.gridwidth = 0;
        this.a.setConstraints(component, this.a);
        this.add(component);
        this.a.weightx = 0.0;
        this.a.weighty = 0.0;
    }
    
    public void a(final String s, final Component component) {
        this.a(null, s, component);
    }
    
    public void a(final Color foreground, final String s, Component component) {
        this.a.anchor = 18;
        this.a.fill = 0;
        if (s != null) {
            final Label label = new Label(s);
            label.setFont(ay.a);
            if (foreground != null) {
                label.setForeground(foreground);
            }
            this.a.gridwidth = 1;
            this.a.setConstraints(label, this.a);
            this.add(label);
        }
        if (component instanceof Label) {
            this.a.fill = 2;
        }
        else if (component instanceof TextComponent) {
            component.setFont(ay.d);
            component = new aw(component);
        }
        this.a.gridwidth = 0;
        this.a.setConstraints(component, this.a);
        this.add(component);
    }
    
    public void a(final String s, final String s2) {
        final Label label = new Label(s);
        label.setFont(ay.b);
        this.a.anchor = 18;
        this.a.fill = 0;
        this.a.gridwidth = -1;
        this.a.setConstraints(label, this.a);
        this.add(label);
        final R r = new R(s2);
        r.setFont(ay.e);
        this.a.gridwidth = 0;
        this.a.fill = 2;
        this.a.setConstraints(r, this.a);
        this.add(r);
    }
    
    public void a(final String s, final Font font, final boolean b) {
        final R r = new R(s);
        final K k = new K();
        final Insets insets = this.a.insets;
        this.a.weightx = 1.0;
        this.a.fill = 2;
        this.a.gridwidth = 0;
        this.a.insets = new Insets(0, 2, 0, 2);
        r.setFont(font);
        this.a.setConstraints(r, this.a);
        this.add(r);
        if (b) {
            this.a.setConstraints(k, this.a);
            this.add(k);
        }
        this.a.insets = insets;
    }
    
    public String a(final Object o) {
        return null;
    }
    
    public abstract void f();
    
    public abstract void c();
    
    public final String getName() {
        return this.m;
    }
    
    public W(final String m, final at at) {
        this.a = new GridBagLayout();
        this.a = new GridBagConstraints();
        this.h = false;
        this.e = false;
        this.m = m;
        this.setLayout(this.a);
        this.a.insets = new Insets(1, 2, 1, 2);
        this.a(m, ay.a, true);
        this.setBackground(at.b.g);
        this.setForeground(at.b.f);
    }
}
