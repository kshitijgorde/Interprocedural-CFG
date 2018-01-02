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
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;

public abstract class S extends Panel implements aB
{
    private String a;
    private GridBagLayout a;
    private GridBagConstraints a;
    private boolean b;
    protected Frame a;
    public boolean a;
    
    public void layout() {
        if (!this.b) {
            this.a.weighty = 1.0E-5;
            this.a.gridheight = 0;
            final Canvas canvas = new Canvas();
            this.a.setConstraints(canvas, this.a);
            this.add(canvas);
            this.b = true;
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
    
    public final void a(final Component component, final Component component2) {
        this.a.weightx = 0.0;
        this.a.weighty = 0.0;
        this.a.anchor = 10;
        this.a.fill = 1;
        this.a.gridwidth = -1;
        this.a.setConstraints(component, this.a);
        this.add(component);
        this.a.gridwidth = 0;
        this.a.setConstraints(component2, this.a);
        this.add(component2);
        this.a.weightx = 0.0;
        this.a.weighty = 0.0;
    }
    
    public final void a(final String s, Component component) {
        (this = this).a.anchor = 18;
        this.a.fill = 0;
        if (s != null) {
            final bi bi;
            (bi = new bi(s, (byte)0)).setFont(bk.b);
            this.a.gridwidth = 1;
            this.a.setConstraints(bi, this.a);
            this.add(bi);
        }
        if (component instanceof Label) {
            this.a.fill = 2;
        }
        else if (component instanceof bi) {
            this.a.fill = 2;
        }
        else if (component instanceof TextComponent) {
            component.setFont(bk.a);
            component = new i(component);
        }
        else if (component instanceof Choice) {
            component.setForeground(Color.black);
            component.setBackground(Color.white);
        }
        this.a.gridwidth = 0;
        this.a.setConstraints(component, this.a);
        this.add(component);
    }
    
    public final void a(final String s, Component component, Component component2) {
        this.a.anchor = 18;
        this.a.fill = 0;
        this.a.weightx = 0.0;
        if (s != null) {
            final bi bi;
            (bi = new bi(s, (byte)0)).setFont(bk.d);
            this.a.gridwidth = 1;
            this.a.setConstraints(bi, this.a);
            this.add(bi);
        }
        if (component instanceof Label) {
            component.setFont(bk.d);
        }
        if (component instanceof bi) {
            component.setFont(bk.d);
        }
        if (component instanceof TextComponent) {
            component.setFont(bk.c);
            component = new i(component);
        }
        else if (component instanceof Choice) {
            component.setForeground(Color.black);
            component.setBackground(Color.white);
        }
        this.a.gridwidth = -1;
        this.a.setConstraints(component, this.a);
        this.add(component);
        if (component2 instanceof TextComponent) {
            component2.setFont(bk.c);
            component2 = new i(component2);
        }
        else if (component2 instanceof Choice) {
            component2.setForeground(Color.black);
            component2.setBackground(Color.white);
        }
        this.a.gridwidth = 0;
        this.a.setConstraints(component2, this.a);
        this.add(component2);
    }
    
    public final void a(final String s, final Component[] array) {
        this.a.anchor = 18;
        this.a.fill = 0;
        if (s != null) {
            final bi bi;
            (bi = new bi(s, (byte)0)).setFont(bk.d);
            this.a.gridwidth = 1;
            this.a.setConstraints(bi, this.a);
            this.add(bi);
        }
        for (int i = 0; i < array.length; ++i) {
            if (array[i] instanceof TextComponent) {
                array[i].setFont(bk.c);
                array[i] = new i(array[i]);
            }
            else if (array[i] instanceof Choice) {
                array[i].setForeground(Color.black);
                array[i].setBackground(Color.white);
            }
            array[i].setSize(array[i].preferredSize());
            if (array[i] instanceof Label) {
                this.a.fill = 2;
            }
            if (array[i] instanceof bi) {
                this.a.fill = 2;
            }
            this.a.gridwidth = ((i != array.length - 1) ? 1 : 0);
            this.a.setConstraints(array[i], this.a);
            this.add(array[i]);
        }
    }
    
    public final void a(final String s, final String s2) {
        final bi bi;
        (bi = new bi(s, (byte)0)).setFont(bk.d);
        this.a.anchor = 18;
        this.a.fill = 0;
        this.a.gridwidth = -1;
        this.a.setConstraints(bi, this.a);
        this.add(bi);
        final A a;
        (a = new A(s2)).setFont(bk.c);
        this.a.gridwidth = 0;
        this.a.fill = 2;
        this.a.setConstraints(a, this.a);
        this.add(a);
    }
    
    public final void a(final String s, final Font font, final boolean b) {
        final A a = new A(s);
        final k k = new k();
        final Insets insets = this.a.insets;
        this.a.weightx = 1.0;
        this.a.fill = 2;
        this.a.gridwidth = 0;
        this.a.insets = new Insets(0, 2, 0, 2);
        a.setFont(font);
        this.a.setConstraints(a, this.a);
        this.add(a);
        if (b) {
            this.a.setConstraints(k, this.a);
            this.add(k);
        }
        this.a.insets = insets;
    }
    
    public final Frame a() {
        return this.a;
    }
    
    public String a(final Object o) {
        return null;
    }
    
    public abstract void b();
    
    public abstract void a();
    
    public final String getName() {
        return this.a;
    }
    
    public S(final String a, final cx cx) {
        this.a = new GridBagLayout();
        this.a = new GridBagConstraints();
        this.b = false;
        this.a = false;
        this.setLayout((LayoutManager)(this.a = a));
        this.a.insets = new Insets(1, 2, 1, 2);
        this.a(a, bk.b, true);
        this.setBackground(cx.a.h);
        this.setForeground(cx.a.g);
    }
}
