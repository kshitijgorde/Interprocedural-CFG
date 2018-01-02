// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.LayoutManager;
import com.diginet.digichat.client.h;
import com.diginet.digichat.exceptions.dm;
import java.awt.Insets;
import java.awt.Font;
import java.awt.TextComponent;
import java.awt.Color;
import java.awt.Label;
import java.awt.Component;
import java.awt.Canvas;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import com.diginet.digichat.util.s;
import java.awt.Panel;

public abstract class ah extends Panel implements s
{
    private String a;
    private GridBagLayout b;
    private GridBagConstraints c;
    private boolean d;
    protected Frame e;
    protected boolean f;
    
    public void layout() {
        if (!this.d) {
            this.c.weighty = 1.0E-5;
            this.c.gridheight = 0;
            final Canvas canvas = new Canvas();
            this.b.setConstraints(canvas, this.c);
            this.add(canvas);
            this.d = true;
        }
        super.layout();
    }
    
    public void a(final Component component, final int n) {
        this.a(component, n, 0.0f, 0.0f);
    }
    
    public void a(final Component component, final int fill, final float n, final float n2) {
        this.c.weightx = n;
        this.c.weighty = n2;
        this.c.anchor = 10;
        this.c.fill = fill;
        this.c.gridwidth = 0;
        this.b.setConstraints(component, this.c);
        this.add(component);
        this.c.weightx = 0.0;
        this.c.weighty = 0.0;
    }
    
    public Label a(final String s, final Component component) {
        return this.a(null, s, component);
    }
    
    public Label a(final Color foreground, final String s, Component component) {
        this.c.anchor = 18;
        this.c.fill = 0;
        Component component2 = null;
        if (s != null) {
            component2 = new Label(s);
            component2.setFont(dw.b);
            if (foreground != null) {
                component2.setForeground(foreground);
            }
            this.c.gridwidth = 1;
            this.b.setConstraints(component2, this.c);
            this.add(component2);
        }
        if (component instanceof Label) {
            this.c.fill = 2;
        }
        else if (component instanceof TextComponent) {
            component.setFont(dw.a);
            component = new t(component);
        }
        this.c.gridwidth = 0;
        this.b.setConstraints(component, this.c);
        this.add(component);
        return (Label)component2;
    }
    
    public Label a(final String s, Component component, Component component2) {
        this.c.anchor = 18;
        this.c.fill = 0;
        Component component3 = null;
        if (s != null) {
            component3 = new Label(s);
            component3.setFont(dw.d);
            this.c.gridwidth = 1;
            this.b.setConstraints(component3, this.c);
            this.add(component3);
        }
        if (component instanceof TextComponent) {
            component.setFont(dw.c);
            component = new t(component);
        }
        this.c.gridwidth = -1;
        this.b.setConstraints(component, this.c);
        this.add(component);
        if (component2 instanceof TextComponent) {
            component2.setFont(dw.c);
            component2 = new t(component2);
        }
        this.c.gridwidth = 0;
        this.b.setConstraints(component2, this.c);
        this.add(component2);
        return (Label)component3;
    }
    
    public void a(final String s, final Component[] array) {
        this.c.anchor = 18;
        this.c.fill = 0;
        if (s != null) {
            final Label label = new Label(s);
            label.setFont(dw.d);
            this.c.gridwidth = 1;
            this.b.setConstraints(label, this.c);
            this.add(label);
        }
        for (int i = 0; i < array.length; ++i) {
            if (array[i] instanceof TextComponent) {
                array[i].setFont(dw.c);
                array[i] = new t(array[i]);
            }
            array[i].setSize(array[i].preferredSize());
            if (array[i] instanceof Label) {
                this.c.fill = 2;
            }
            this.c.gridwidth = ((i != array.length - 1) ? 1 : 0);
            this.b.setConstraints(array[i], this.c);
            this.add(array[i]);
        }
    }
    
    public void a(final String s, final String s2) {
        final Label label = new Label(s);
        label.setFont(dw.d);
        this.c.anchor = 18;
        this.c.fill = 0;
        this.c.gridwidth = -1;
        this.b.setConstraints(label, this.c);
        this.add(label);
        final aw aw = new aw(s2);
        aw.setFont(dw.c);
        this.c.gridwidth = 0;
        this.c.fill = 2;
        this.b.setConstraints(aw, this.c);
        this.add(aw);
    }
    
    public void a(final String s, final Font font, final boolean b) {
        final aw aw = new aw(s);
        final bb bb = new bb();
        final Insets insets = this.c.insets;
        this.c.weightx = 1.0;
        this.c.fill = 2;
        this.c.gridwidth = 0;
        this.c.insets = new Insets(0, 2, 0, 2);
        aw.setFont(font);
        this.b.setConstraints(aw, this.c);
        this.add(aw);
        if (b) {
            this.b.setConstraints(bb, this.c);
            this.add(bb);
        }
        this.c.insets = insets;
    }
    
    public Frame a() {
        return this.e;
    }
    
    public String a(final Object o) {
        return null;
    }
    
    public abstract void b() throws dm;
    
    public abstract void c();
    
    public final String getName() {
        return this.a;
    }
    
    public ah(final String a, final h h) {
        this.b = new GridBagLayout();
        this.c = new GridBagConstraints();
        this.d = false;
        this.f = false;
        this.a = a;
        this.setLayout(this.b);
        this.c.insets = new Insets(1, 2, 1, 2);
        this.a(a, dw.b, true);
        this.setBackground(h.cc.j);
        this.setForeground(h.cc.i);
    }
}
