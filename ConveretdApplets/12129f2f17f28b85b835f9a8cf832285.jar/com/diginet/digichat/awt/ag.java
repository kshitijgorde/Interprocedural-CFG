// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.LayoutManager;
import com.diginet.digichat.client.h;
import com.diginet.digichat.exceptions.cf;
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
import com.diginet.digichat.util.s;
import java.awt.Panel;

public abstract class ag extends Panel implements s
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
    
    public void a(final String s, final Component component) {
        this.a(null, s, component);
    }
    
    public void a(final Color foreground, final String s, Component component) {
        this.c.anchor = 18;
        this.c.fill = 0;
        if (s != null) {
            final Label label = new Label(s);
            label.setFont(p.b);
            if (foreground != null) {
                label.setForeground(foreground);
            }
            this.c.gridwidth = 1;
            this.b.setConstraints(label, this.c);
            this.add(label);
        }
        if (component instanceof Label) {
            this.c.fill = 2;
        }
        else if (component instanceof TextComponent) {
            component.setFont(p.a);
            component = new t(component);
        }
        this.c.gridwidth = 0;
        this.b.setConstraints(component, this.c);
        this.add(component);
    }
    
    public void a(final String s, final String s2) {
        final Label label = new Label(s);
        label.setFont(p.d);
        this.c.anchor = 18;
        this.c.fill = 0;
        this.c.gridwidth = -1;
        this.b.setConstraints(label, this.c);
        this.add(label);
        final ai ai = new ai(s2);
        ai.setFont(p.c);
        this.c.gridwidth = 0;
        this.c.fill = 2;
        this.b.setConstraints(ai, this.c);
        this.add(ai);
    }
    
    public void a(final String s, final Font font, final boolean b) {
        final ai ai = new ai(s);
        final au au = new au();
        final Insets insets = this.c.insets;
        this.c.weightx = 1.0;
        this.c.fill = 2;
        this.c.gridwidth = 0;
        this.c.insets = new Insets(0, 2, 0, 2);
        ai.setFont(font);
        this.b.setConstraints(ai, this.c);
        this.add(ai);
        if (b) {
            this.b.setConstraints(au, this.c);
            this.add(au);
        }
        this.c.insets = insets;
    }
    
    public String a(final Object o) {
        return null;
    }
    
    public abstract void a() throws cf;
    
    public abstract void b();
    
    public final String getName() {
        return this.a;
    }
    
    public ag(final String a, final h h) {
        this.b = new GridBagLayout();
        this.c = new GridBagConstraints();
        this.d = false;
        this.f = false;
        this.a = a;
        this.setLayout(this.b);
        this.c.insets = new Insets(1, 2, 1, 2);
        this.a(a, p.b, true);
        this.setBackground(h.ca.j);
        this.setForeground(h.ca.i);
    }
}
