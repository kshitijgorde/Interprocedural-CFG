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

public abstract class o extends Panel implements aB
{
    private String a;
    private GridBagLayout a;
    private GridBagConstraints a;
    private boolean h;
    protected Frame a;
    public boolean e;
    
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
            label.setFont(bL.a);
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
            component.setFont(bL.e);
            component = new aR(component);
        }
        this.a.gridwidth = 0;
        this.a.setConstraints(component, this.a);
        this.add(component);
    }
    
    public void a(final String s, Component component, Component component2) {
        this.a.anchor = 18;
        this.a.fill = 0;
        if (s != null) {
            final Label label = new Label(s);
            label.setFont(bL.a);
            this.a.gridwidth = 1;
            this.a.setConstraints(label, this.a);
            this.add(label);
        }
        if (component instanceof TextComponent) {
            component.setFont(bL.e);
            component = new aR(component);
        }
        this.a.gridwidth = -1;
        this.a.setConstraints(component, this.a);
        this.add(component);
        if (component2 instanceof TextComponent) {
            component2.setFont(bL.e);
            component2 = new aR(component2);
        }
        this.a.gridwidth = 0;
        this.a.setConstraints(component2, this.a);
        this.add(component2);
    }
    
    public void a(final String s, final Component[] array) {
        this.a.anchor = 18;
        this.a.fill = 0;
        if (s != null) {
            final Label label = new Label(s);
            label.setFont(bL.g);
            this.a.gridwidth = 1;
            this.a.setConstraints(label, this.a);
            this.add(label);
        }
        for (int i = 0; i < array.length; ++i) {
            if (array[i] instanceof TextComponent) {
                array[i].setFont(bL.f);
                array[i] = new aR(array[i]);
            }
            array[i].setSize(array[i].preferredSize());
            if (array[i] instanceof Label) {
                this.a.fill = 2;
            }
            this.a.gridwidth = ((i != array.length - 1) ? 1 : 0);
            this.a.setConstraints(array[i], this.a);
            this.add(array[i]);
        }
    }
    
    public void a(final String s, final String s2) {
        final Label label = new Label(s);
        label.setFont(bL.g);
        this.a.anchor = 18;
        this.a.fill = 0;
        this.a.gridwidth = -1;
        this.a.setConstraints(label, this.a);
        this.add(label);
        final c c = new c(s2);
        c.setFont(bL.f);
        this.a.gridwidth = 0;
        this.a.fill = 2;
        this.a.setConstraints(c, this.a);
        this.add(c);
    }
    
    public void a(final String s, final Font font, final boolean b) {
        final c c = new c(s);
        final cH ch = new cH();
        final Insets insets = this.a.insets;
        this.a.weightx = 1.0;
        this.a.fill = 2;
        this.a.gridwidth = 0;
        this.a.insets = new Insets(0, 2, 0, 2);
        c.setFont(font);
        this.a.setConstraints(c, this.a);
        this.add(c);
        if (b) {
            this.a.setConstraints(ch, this.a);
            this.add(ch);
        }
        this.a.insets = insets;
    }
    
    public Frame b() {
        return this.a;
    }
    
    public String a(final Object o) {
        return null;
    }
    
    public abstract void c();
    
    public abstract void d();
    
    public final String getName() {
        return this.a;
    }
    
    public o(final String a, final u u) {
        this.a = new GridBagLayout();
        this.a = new GridBagConstraints();
        this.h = false;
        this.e = false;
        this.setLayout((LayoutManager)(this.a = a));
        this.a.insets = new Insets(1, 2, 1, 2);
        this.a(a, bL.a, true);
        this.setBackground(u.a.g);
        this.setForeground(u.a.f);
    }
}
