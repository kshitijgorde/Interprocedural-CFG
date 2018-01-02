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

public abstract class aZ extends Panel implements aj
{
    private String a;
    private GridBagLayout a;
    private GridBagConstraints a;
    private boolean c;
    protected Frame e;
    protected boolean b;
    
    public void layout() {
        if (!this.c) {
            this.a.weighty = 1.0E-5;
            this.a.gridheight = 0;
            final Canvas canvas = new Canvas();
            this.a.setConstraints(canvas, this.a);
            this.add(canvas);
            this.c = true;
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
            label.setFont(aK.f);
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
            component.setFont(aK.e);
            component = new aX(component);
        }
        this.a.gridwidth = 0;
        this.a.setConstraints(component, this.a);
        this.add(component);
    }
    
    public void a(final String s, final String s2) {
        final Label label = new Label(s);
        label.setFont(aK.g);
        this.a.anchor = 18;
        this.a.fill = 0;
        this.a.gridwidth = -1;
        this.a.setConstraints(label, this.a);
        this.add(label);
        final D d = new D(s2);
        d.setFont(aK.d);
        this.a.gridwidth = 0;
        this.a.fill = 2;
        this.a.setConstraints(d, this.a);
        this.add(d);
    }
    
    public void a(final String s, final Font font, final boolean b) {
        final D d = new D(s);
        final ad ad = new ad();
        final Insets insets = this.a.insets;
        this.a.weightx = 1.0;
        this.a.fill = 2;
        this.a.gridwidth = 0;
        this.a.insets = new Insets(0, 2, 0, 2);
        d.setFont(font);
        this.a.setConstraints(d, this.a);
        this.add(d);
        if (b) {
            this.a.setConstraints(ad, this.a);
            this.add(ad);
        }
        this.a.insets = insets;
    }
    
    public String a(final Object o) {
        return null;
    }
    
    public abstract void a();
    
    public abstract void c();
    
    public final String getName() {
        return this.a;
    }
    
    public aZ(final String a, final aW aw) {
        this.a = new GridBagLayout();
        this.a = new GridBagConstraints();
        this.c = false;
        this.b = false;
        this.setLayout((LayoutManager)(this.a = a));
        this.a.insets = new Insets(1, 2, 1, 2);
        this.a(a, aK.f, true);
        this.setBackground(aw.c.k);
        this.setForeground(aw.c.j);
    }
}
