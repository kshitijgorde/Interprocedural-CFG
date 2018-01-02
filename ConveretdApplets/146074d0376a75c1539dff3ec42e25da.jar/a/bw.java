// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Event;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Component;
import java.awt.TextComponent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

public final class bw extends F
{
    private GridBagConstraints q;
    private GridBagLayout q;
    private g q;
    private g w;
    private bs q;
    protected bZ q;
    private s q;
    private B q;
    private TextComponent q;
    
    public final void q(final boolean b) {
        this.setVisible(true);
    }
    
    public final void q(final bZ q) {
        ((bs)(this.q = q)).q(q);
        if (this.q != null) {
            this.q.requestFocus();
        }
    }
    
    public final void q(Component component, final int fill, final float n, final float n2) {
        this.q.weightx = n;
        this.q.weighty = n2;
        this.q.fill = fill;
        this.q.gridwidth = 0;
        if (component instanceof TextComponent) {
            if (this.q == null) {
                this.q = (TextComponent)component;
            }
            component = new t(component);
        }
        this.q.setConstraints(component, this.q);
        this.q.add(component);
        this.q.weightx = 0.0;
        this.q.weighty = 0.0;
        this.q.fill = 0;
    }
    
    public final void q(final String s, final Component component) {
        this.q(s, component, 0);
    }
    
    public final void q(final String s, Component component, final int n) {
        final Panel panel = (this.q.q() == null) ? this.q : ((Panel)this.q.q(n));
        final Label label;
        (label = new Label(s)).setFont(m.t);
        this.q.gridwidth = 1;
        this.q.setConstraints(label, this.q);
        panel.add(label);
        this.q.gridwidth = 0;
        if (component instanceof TextComponent) {
            component.setFont(m.r);
            if (this.q == null) {
                this.q = (TextComponent)component;
            }
            component = new t(component);
        }
        this.q.setConstraints(component, this.q);
        panel.add(component);
    }
    
    public final void q(final String s, Component component, final Component component2, final int n) {
        final Panel panel = (this.q.q() == null) ? this.q : ((Panel)this.q.q(n));
        final Label label;
        (label = new Label(s)).setFont(m.t);
        this.q.gridwidth = 1;
        this.q.setConstraints(label, this.q);
        panel.add(label);
        this.q.gridwidth = -1;
        if (component instanceof TextComponent) {
            component.setFont(m.r);
            if (this.q == null) {
                this.q = (TextComponent)component;
            }
            component = new t(component);
        }
        this.q.setConstraints(component, this.q);
        panel.add(component);
        this.q.gridwidth = 0;
        panel.add(component2, this.q);
    }
    
    public final void q(final String s, Component component, final Component component2, final Component component3, final int n) {
        final Panel panel = (this.q.q() == null) ? this.q : ((Panel)this.q.q(0));
        final Label label;
        (label = new Label(s)).setFont(m.t);
        this.q.gridwidth = 1;
        this.q.setConstraints(label, this.q);
        panel.add(label);
        this.q.gridwidth = 1;
        if (component instanceof TextComponent) {
            component.setFont(m.r);
            if (this.q == null) {
                this.q = (TextComponent)component;
            }
            component = new t(component);
        }
        this.q.setConstraints(component, this.q);
        panel.add(component);
        this.q.gridwidth = 1;
        this.q.setConstraints(label, this.q);
        panel.add(component2);
        this.q.gridwidth = 0;
        panel.add(component3, this.q);
    }
    
    public final void q(final String s, final Component[] array) {
        this.q.anchor = 18;
        this.q.fill = 0;
        final Panel panel = (this.q.q() == null) ? this.q : ((Panel)this.q.q(0));
        if (s != null) {
            final Label label;
            (label = new Label(s)).setFont(m.t);
            this.q.gridwidth = 1;
            this.q.setConstraints(label, this.q);
            panel.add(label);
        }
        for (int i = 0; i < array.length; ++i) {
            if (array[i] instanceof TextComponent) {
                array[i].setFont(m.r);
                array[i] = new t(array[i]);
            }
            array[i].setSize(array[i].minimumSize());
            if (array[i] instanceof Label) {
                array[i].setFont(m.t);
            }
            this.q.gridwidth = ((i != array.length - 1) ? 1 : 0);
            this.q.setConstraints(array[i], this.q);
            panel.add(array[i]);
        }
    }
    
    public final void q(final String s, String s2) {
        final Label label;
        (label = new Label(s2)).setFont(m.r);
        final Label label2 = label;
        s2 = s;
        this.q(s2, label2, 0);
    }
    
    public final boolean handleEvent(final Event event) {
        if (this.q.q(event)) {
            return true;
        }
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == ef.q) {
                    this.q.r();
                    return true;
                }
                if (event.key == 27 || (event.key == 46 && (event.modifiers & 0x4) != 0x0)) {
                    this.w.r();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.q) {
                    if (this.q.q(this.q)) {
                        this.q.e(this.q);
                    }
                    return true;
                }
                if (event.target == this.w) {
                    final bs q = this.q;
                    final bZ q2 = this.q;
                    q.r();
                    return true;
                }
                break;
            }
            case 201: {
                final bs q3 = this.q;
                final bZ q4 = this.q;
                q3.r();
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public bw(final String s, final bs q, final bZ bz, final boolean b, final cV cv) {
        super(q.q(), true);
        this.q = new GridBagConstraints();
        this.q = new GridBagLayout();
        this.q = new g(80, 20);
        this.w = new g(80, 20);
        this.q = new s();
        this.q = null;
        this.setBackground(cf.w.q);
        if (bz.q() < 0) {
            this.setTitle(ec.q(eb.q("Add %1"), new String[] { s }));
        }
        else {
            this.setTitle(ec.q(eb.q("Edit %1"), new String[] { s }));
        }
        this.q = q;
        if (q.q() != null) {
            this.q = new B(2, 10, 2, 10, 1, cv, false);
            for (int i = 0; i < q.q().length; ++i) {
                final Panel panel;
                (panel = new Panel()).setLayout(this.q);
                this.q.q(q.q()[i], panel);
            }
        }
        this.q.setBackground(cf.w.i);
        this.q.setForeground(cf.w.u);
        this.q.setLayout(this.q);
        this.setLayout(this.q);
        this.setResizable(false);
        this.q.insets = new Insets(4, 4, 4, 4);
        this.q.gridwidth = 0;
        this.q.fill = 1;
        this.q.setConstraints((q.q() == null) ? this.q : this.q, this.q);
        this.add((q.q() == null) ? this.q : this.q);
        this.q.gridheight = -1;
        this.q.weightx = 1.0;
        if (b) {
            final Component q2 = this.q(cf.w.e);
            this.q.setConstraints(q2, this.q);
            this.add(q2);
            this.q(cf.w.e());
            q2.setBackground(cf.w.r);
        }
        this.q.gridheight = 0;
        this.q.gridwidth = -1;
        this.q.anchor = 13;
        this.q.fill = 0;
        this.w.q(eb.q("Cancel"));
        this.w.t();
        this.q.setConstraints(this.w, this.q);
        this.add(this.w);
        this.q.gridwidth = 0;
        this.q.weightx = 0.0;
        this.q.q(eb.q("OK"));
        this.q.t();
        final f f = new f(this.q);
        this.q.setConstraints(f, this.q);
        this.add(f);
        this.q.gridheight = 1;
        this.q.anchor = 18;
        q.q(this);
        this.q(bz);
        this.pack();
    }
}
