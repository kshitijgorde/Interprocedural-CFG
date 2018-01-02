// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.LayoutManager;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Color;
import java.awt.Choice;
import java.awt.TextComponent;
import java.awt.Label;
import java.awt.Component;
import java.awt.Canvas;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;

public abstract class D extends Panel implements ab
{
    private String a;
    private GridBagLayout a;
    private GridBagConstraints a;
    private boolean a;
    
    public void layout() {
        if (!this.a) {
            this.a.weighty = 1.0E-5;
            this.a.gridheight = 0;
            final Canvas canvas = new Canvas();
            this.a.setConstraints(canvas, this.a);
            this.add(canvas);
            this.a = true;
        }
        super.layout();
    }
    
    public final void a(final Component component) {
        this.a(component, 2, 0.0f, 0.0f);
    }
    
    public final void a(final Component component, final int fill, final float n, final float n2) {
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
    
    public final void a(final String s, Component component) {
        (this = this).a.anchor = 18;
        this.a.fill = 0;
        if (s != null) {
            final aw aw;
            (aw = new aw(s, (byte)0)).setFont(ay.b);
            this.a.gridwidth = 1;
            this.a.setConstraints(aw, this.a);
            this.add(aw);
        }
        if (component instanceof Label) {
            this.a.fill = 2;
        }
        else if (component instanceof aw) {
            this.a.fill = 2;
        }
        else if (component instanceof TextComponent) {
            component.setFont(ay.a);
            component = new g(component);
        }
        else if (component instanceof Choice) {
            component.setForeground(Color.black);
            component.setBackground(Color.white);
        }
        this.a.gridwidth = 0;
        this.a.setConstraints(component, this.a);
        this.add(component);
    }
    
    public final void a(final String s, final Component[] array) {
        this.a.anchor = 18;
        this.a.fill = 0;
        if (s != null) {
            final aw aw;
            (aw = new aw(s, (byte)0)).setFont(ay.d);
            this.a.gridwidth = 1;
            this.a.setConstraints(aw, this.a);
            this.add(aw);
        }
        for (int i = 0; i < array.length; ++i) {
            if (array[i] instanceof TextComponent) {
                array[i].setFont(ay.c);
                array[i] = new g(array[i]);
            }
            else if (array[i] instanceof Choice) {
                array[i].setForeground(Color.black);
                array[i].setBackground(Color.white);
            }
            array[i].setSize(array[i].preferredSize());
            if (array[i] instanceof Label) {
                this.a.fill = 2;
            }
            if (array[i] instanceof aw) {
                this.a.fill = 2;
            }
            this.a.gridwidth = ((i != array.length - 1) ? 1 : 0);
            this.a.setConstraints(array[i], this.a);
            this.add(array[i]);
        }
    }
    
    public final void a(final String s, final String s2) {
        final aw aw;
        (aw = new aw(s, (byte)0)).setFont(ay.d);
        this.a.anchor = 18;
        this.a.fill = 0;
        this.a.gridwidth = -1;
        this.a.setConstraints(aw, this.a);
        this.add(aw);
        final t t;
        (t = new t(s2)).setFont(ay.c);
        this.a.gridwidth = 0;
        this.a.fill = 2;
        this.a.setConstraints(t, this.a);
        this.add(t);
    }
    
    public final void a(final String s, final Font font, final boolean b) {
        final t t = new t(s);
        final bb bb = new bb();
        final Insets insets = this.a.insets;
        this.a.weightx = 1.0;
        this.a.fill = 2;
        this.a.gridwidth = 0;
        this.a.insets = new Insets(0, 2, 0, 2);
        t.setFont(font);
        this.a.setConstraints(t, this.a);
        this.add(t);
        if (b) {
            this.a.setConstraints(bb, this.a);
            this.add(bb);
        }
        this.a.insets = insets;
    }
    
    public String a(final Object o) {
        return null;
    }
    
    public abstract void a();
    
    public abstract void b();
    
    public final String getName() {
        return this.a;
    }
    
    public D(final String a, final bl bl) {
        this.a = new GridBagLayout();
        this.a = new GridBagConstraints();
        this.a = false;
        this.setLayout((LayoutManager)(this.a = a));
        this.a.insets = new Insets(1, 2, 1, 2);
        this.a(a, ay.b, true);
        this.setBackground(bl.a.h);
        this.setForeground(bl.a.g);
    }
}
