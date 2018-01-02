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

public final class dK extends g
{
    private GridBagConstraints q;
    private GridBagLayout q;
    private ad q;
    private ad w;
    private G q;
    protected bp q;
    private dT q;
    private bB q;
    private TextComponent q;
    
    public final void q(final bp q) {
        ((G)(this.q = q)).q(q);
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
            component = new bZ(component);
        }
        this.q.setConstraints(component, this.q);
        this.q.add(component);
        this.q.weightx = 0.0;
        this.q.weighty = 0.0;
        this.q.fill = 0;
    }
    
    public final void q(final String s, Component component, final int n) {
        final Panel panel = (this.q.q() == null) ? this.q : ((Panel)this.q.q(n));
        final Label label;
        (label = new Label(s)).setFont(cb.t);
        this.q.gridwidth = 1;
        this.q.setConstraints(label, this.q);
        panel.add(label);
        this.q.gridwidth = 0;
        if (component instanceof TextComponent) {
            component.setFont(cb.r);
            if (this.q == null) {
                this.q = (TextComponent)component;
            }
            component = new bZ(component);
        }
        this.q.setConstraints(component, this.q);
        panel.add(component);
    }
    
    public final void q(final String s, Component component, final Component component2, final int n) {
        final Panel panel = (this.q.q() == null) ? this.q : ((Panel)this.q.q(n));
        final Label label;
        (label = new Label(s)).setFont(cb.t);
        this.q.gridwidth = 1;
        this.q.setConstraints(label, this.q);
        panel.add(label);
        this.q.gridwidth = -1;
        if (component instanceof TextComponent) {
            component.setFont(cb.r);
            if (this.q == null) {
                this.q = (TextComponent)component;
            }
            component = new bZ(component);
        }
        this.q.setConstraints(component, this.q);
        panel.add(component);
        this.q.gridwidth = 0;
        panel.add(component2, this.q);
    }
    
    public final void q(final String s, Component component, final Component component2, final Component component3, final int n) {
        final Panel panel = (this.q.q() == null) ? this.q : ((Panel)this.q.q(0));
        final Label label;
        (label = new Label(s)).setFont(cb.t);
        this.q.gridwidth = 1;
        this.q.setConstraints(label, this.q);
        panel.add(label);
        this.q.gridwidth = 1;
        if (component instanceof TextComponent) {
            component.setFont(cb.r);
            if (this.q == null) {
                this.q = (TextComponent)component;
            }
            component = new bZ(component);
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
            (label = new Label(s)).setFont(cb.t);
            this.q.gridwidth = 1;
            this.q.setConstraints(label, this.q);
            panel.add(label);
        }
        for (int i = 0; i < array.length; ++i) {
            if (array[i] instanceof TextComponent) {
                array[i].setFont(cb.r);
                array[i] = new bZ(array[i]);
            }
            array[i].setSize(array[i].minimumSize());
            if (array[i] instanceof Label) {
                array[i].setFont(cb.t);
            }
            this.q.gridwidth = ((i != array.length - 1) ? 1 : 0);
            this.q.setConstraints(array[i], this.q);
            panel.add(array[i]);
        }
    }
    
    public final boolean handleEvent(final Event event) {
        if (this.q.q(event)) {
            return true;
        }
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == cK.q) {
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
                    this.q.r();
                    return true;
                }
                break;
            }
            case 201: {
                this.q.r();
                return true;
            }
        }
        return super.handleEvent(event);
    }
    
    public dK(final String s, final G q, final bp bp, final boolean b, final ap ap) {
        super(q.q, true);
        this.q = new GridBagConstraints();
        this.q = new GridBagLayout();
        this.q = new ad(80, 20);
        this.w = new ad(80, 20);
        this.q = new dT();
        this.q = null;
        this.setBackground(bC.w.q);
        if (bp.s < 0) {
            this.setTitle(B.q(be.w("Add %1"), new String[] { s }));
        }
        else {
            this.setTitle(B.q(be.w("Edit %1"), new String[] { s }));
        }
        this.q = q;
        if (q.q() != null) {
            this.q = new bB(2, 10, 2, 10, 1, ap, false);
            for (int i = 0; i < q.q().length; ++i) {
                final Panel panel;
                (panel = new Panel()).setLayout(this.q);
                this.q.q(q.q()[i], panel);
            }
        }
        this.q.setBackground(bC.w.i);
        this.q.setForeground(bC.w.u);
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
            final Component q2 = this.q(bC.w.e);
            this.q.setConstraints(q2, this.q);
            this.add(q2);
            this.q(bC.w.e());
            q2.setBackground(bC.w.r);
        }
        this.q.gridheight = 0;
        this.q.gridwidth = -1;
        this.q.anchor = 13;
        this.q.fill = 0;
        this.w.q(be.w("Cancel"));
        this.w.t();
        this.q.setConstraints(this.w, this.q);
        this.add(this.w);
        this.q.gridwidth = 0;
        this.q.weightx = 0.0;
        this.q.q(be.w("OK"));
        this.q.t();
        final as as = new as(this.q);
        this.q.setConstraints(as, this.q);
        this.add(as);
        this.q.gridheight = 1;
        this.q.anchor = 18;
        q.q(this);
        this.q(bp);
        this.pack();
    }
}
