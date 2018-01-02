// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.LayoutManager;
import com.diginet.digichat.client.g;
import com.diginet.digichat.exceptions.c0;
import java.awt.Insets;
import java.awt.Font;
import java.awt.TextComponent;
import java.awt.Label;
import com.diginet.digichat.util.q;
import java.awt.Color;
import java.awt.Component;
import java.awt.Canvas;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import com.diginet.digichat.util.p;
import java.awt.Panel;

public abstract class al extends Panel implements p
{
    private String a;
    private GridBagLayout b;
    private GridBagConstraints c;
    private boolean d;
    protected Frame e;
    protected boolean f;
    
    public final void layout() {
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
    
    public final void a(final Component component, final int n) {
        this.a(component, n, 0.0f, 0.0f);
    }
    
    public final void a(final Component component, final int n, final float n2, final float n3) {
        this.a(component, n, n2, n3, 0);
    }
    
    public final void a(final Component component, final int fill, final float n, final float n2, final int gridwidth) {
        this.c.weightx = n;
        this.c.weighty = n2;
        this.c.anchor = 10;
        this.c.fill = fill;
        this.c.gridwidth = gridwidth;
        this.b.setConstraints(component, this.c);
        this.add(component);
        this.c.weightx = 0.0;
        this.c.weighty = 0.0;
    }
    
    public final void a(final String s, final Component component) {
        this.a(null, s, component);
    }
    
    public final void a(final Color foreground, final String s, Component component) {
        this.c.anchor = 18;
        this.c.fill = 0;
        this.c.gridwidth = 0;
        if (s != null) {
            final Component a = q.a(s);
            a.setFont(m.b);
            if (foreground != null) {
                a.setForeground(foreground);
            }
            this.c.gridwidth = 1;
            this.b.setConstraints(a, this.c);
            this.add(a);
        }
        if (component instanceof Label) {
            this.c.fill = 2;
        }
        else if (component instanceof TextComponent) {
            component.setFont(m.a);
            component = new r(component);
        }
        this.c.gridwidth = 0;
        this.b.setConstraints(component, this.c);
        this.add(component);
    }
    
    public final void a(final String s, final String s2) {
        final Component a = q.a(s);
        a.setFont(m.d);
        this.c.anchor = 18;
        this.c.fill = 0;
        this.c.gridwidth = -1;
        this.b.setConstraints(a, this.c);
        this.add(a);
        final an an = new an(s2);
        an.setFont(m.c);
        this.c.gridwidth = 0;
        this.c.fill = 2;
        this.b.setConstraints(an, this.c);
        this.add(an);
    }
    
    public final void a(final String s, final Font font, final boolean b) {
        final an an = new an(s);
        final ay ay = new ay();
        final Insets insets = this.c.insets;
        this.c.weightx = 1.0;
        this.c.fill = 2;
        this.c.gridwidth = 0;
        this.c.insets = new Insets(0, 2, 0, 2);
        an.setFont(font);
        this.b.setConstraints(an, this.c);
        this.add(an);
        if (b) {
            this.b.setConstraints(ay, this.c);
            this.add(ay);
        }
        this.c.insets = insets;
    }
    
    public String a(final Object o) {
        return null;
    }
    
    public abstract void a() throws c0;
    
    public abstract void b();
    
    public final String getName() {
        return this.a;
    }
    
    public al(final String a, final g g) {
        this.b = new GridBagLayout();
        this.c = new GridBagConstraints();
        this.d = false;
        this.f = false;
        this.a = a;
        this.setLayout(this.b);
        this.c.insets = new Insets(1, 2, 1, 2);
        this.a(a, m.b, true);
        this.setBackground(g.df.tabsBackground);
        this.setForeground(g.df.tabsText);
    }
}
