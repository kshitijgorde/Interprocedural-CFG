// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.LayoutManager;
import java.awt.Insets;
import java.awt.Font;
import java.awt.TextComponent;
import java.awt.Label;
import java.awt.Component;
import java.awt.Canvas;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;

public abstract class G extends Panel implements dT
{
    private String q;
    private GridBagLayout q;
    private GridBagConstraints q;
    private boolean w;
    public Frame q;
    public boolean q;
    
    public void layout() {
        if (!this.w) {
            this.q.weighty = 1.0E-5;
            this.q.gridheight = 0;
            final Canvas canvas = new Canvas();
            this.q.setConstraints(canvas, this.q);
            this.add(canvas);
            this.w = true;
        }
        super.layout();
    }
    
    public final void q(final Component component, final int n) {
        this.q(component, 2, 0.0f, 0.0f);
    }
    
    public final void q(final Component component, final int fill, final float n, final float n2) {
        this.q.weightx = n;
        this.q.weighty = n2;
        this.q.anchor = 10;
        this.q.fill = fill;
        this.q.gridwidth = 0;
        this.q.setConstraints(component, this.q);
        this.add(component);
        this.q.weightx = 0.0;
        this.q.weighty = 0.0;
    }
    
    public final void q(final String s, final Component component) {
        Component component2 = component;
        this.q.anchor = 18;
        this.q.fill = 0;
        if (s != null) {
            final Label label;
            (label = new Label(s)).setFont(m.w);
            this.q.gridwidth = 1;
            this.q.setConstraints(label, this.q);
            this.add(label);
        }
        if (component2 instanceof Label) {
            this.q.fill = 2;
        }
        else if (component2 instanceof TextComponent) {
            component2.setFont(m.q);
            component2 = new t(component2);
        }
        this.q.gridwidth = 0;
        this.q.setConstraints(component2, this.q);
        this.add(component2);
    }
    
    public final void q(final String s, Component component, Component component2) {
        this.q.anchor = 18;
        this.q.fill = 0;
        if (s != null) {
            final Label label;
            (label = new Label(s)).setFont(m.t);
            this.q.gridwidth = 1;
            this.q.setConstraints(label, this.q);
            this.add(label);
        }
        if (component instanceof TextComponent) {
            component.setFont(m.r);
            component = new t(component);
        }
        this.q.gridwidth = -1;
        this.q.setConstraints(component, this.q);
        this.add(component);
        if (component2 instanceof TextComponent) {
            component2.setFont(m.r);
            component2 = new t(component2);
        }
        this.q.gridwidth = 0;
        this.q.setConstraints(component2, this.q);
        this.add(component2);
    }
    
    public final void q(final String s, final Component[] array) {
        this.q.anchor = 18;
        this.q.fill = 0;
        if (s != null) {
            final Label label;
            (label = new Label(s)).setFont(m.t);
            this.q.gridwidth = 1;
            this.q.setConstraints(label, this.q);
            this.add(label);
        }
        for (int i = 0; i < array.length; ++i) {
            if (array[i] instanceof TextComponent) {
                array[i].setFont(m.r);
                array[i] = new t(array[i]);
            }
            array[i].setSize(array[i].preferredSize());
            if (array[i] instanceof Label) {
                this.q.fill = 2;
            }
            this.q.gridwidth = ((i != array.length - 1) ? 1 : 0);
            this.q.setConstraints(array[i], this.q);
            this.add(array[i]);
        }
    }
    
    public final void q(final String s, final String s2) {
        final Label label;
        (label = new Label(s)).setFont(m.t);
        this.q.anchor = 18;
        this.q.fill = 0;
        this.q.gridwidth = -1;
        this.q.setConstraints(label, this.q);
        this.add(label);
        final H h;
        (h = new H(s2)).setFont(m.r);
        this.q.gridwidth = 0;
        this.q.fill = 2;
        this.q.setConstraints(h, this.q);
        this.add(h);
    }
    
    public final void q(final String s, final Font font, final boolean b) {
        final H h = new H(s);
        final q q = new q();
        final Insets insets = this.q.insets;
        this.q.weightx = 1.0;
        this.q.fill = 2;
        this.q.gridwidth = 0;
        this.q.insets = new Insets(0, 2, 0, 2);
        h.setFont(font);
        this.q.setConstraints(h, this.q);
        this.add(h);
        if (b) {
            this.q.setConstraints(q, this.q);
            this.add(q);
        }
        this.q.insets = insets;
    }
    
    public final Frame q() {
        return this.q;
    }
    
    public String q(final Object o) {
        return null;
    }
    
    public abstract void q();
    
    public abstract void w();
    
    public final String getName() {
        return this.q;
    }
    
    public G(final String q) {
        this.q = new GridBagLayout();
        this.q = new GridBagConstraints();
        this.w = false;
        this.q = false;
        this.setLayout((LayoutManager)(this.q = q));
        this.q.insets = new Insets(1, 2, 1, 2);
        this.q(q, m.w, true);
        this.setBackground(cf.w.i);
        this.setForeground(cf.w.u);
    }
}
