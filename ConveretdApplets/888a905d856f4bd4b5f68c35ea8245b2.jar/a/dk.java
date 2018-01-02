// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Label;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.CardLayout;
import java.awt.TextField;
import java.awt.Event;
import java.awt.Choice;

public final class dk extends bE
{
    private static int q;
    private cT q;
    private dE q;
    private Choice q;
    
    public final void dispose() {
        super.dispose();
        if (--dk.q > 0) {
            this.q.u();
            return;
        }
        this.q.g();
    }
    
    public final void q() {
        this.q.w();
    }
    
    public final void q(cE ce) {
        if (this.q.getSelectedIndex() != this.q.i.q((Object)ce)) {
            this.q.select(this.q.i.q((Object)ce));
        }
        this.q.q();
        switch ((ce = ce).s) {
            case 0: {
                this.q.q.disable();
                this.q.w.disable();
            }
            case 1: {
                this.q.q.disable();
                this.q.w.enable();
            }
            default: {
                this.q.q.enable();
                this.q.w.enable();
            }
        }
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 1001: {
                if (event.target == this.q) {
                    this.q.q((cE)this.q.i.q(this.q.getSelectedIndex()));
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public dk(final cT q) {
        super(B.q(be.w("%1 Login"), new String[] { dN.e }), false);
        new ad(70, 20);
        new TextField(20);
        new TextField(20);
        new TextField(20);
        new TextField(10);
        new CardLayout(0, 0);
        this.q = new Choice();
        this.q = q;
        ++dk.q;
        try {
            for (int i = 0; i < this.q.i.q; ++i) {
                this.q.addItem(((cE)this.q.i.q(i)).a);
            }
        }
        finally {}
        this.setResizable(false);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final dT dt;
        (dt = new dT()).setLayout(layout);
        this.q = new dE(m.q(), q, true, true, true, true);
        final Label label;
        (label = new Label(be.w("Connect as "))).setFont(cb.t);
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(2, 2, 2, 6);
        layout.setConstraints(label, gridBagConstraints);
        dt.add(label);
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.q, gridBagConstraints);
        dt.add(this.q);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridheight = 0;
        layout.setConstraints(this.q, gridBagConstraints);
        dt.add(this.q);
        this.setLayout(new BorderLayout());
        this.add("Center", dt);
        this.pack();
        this.e();
        final Rectangle bounds;
        final Rectangle rectangle = bounds = this.getBounds();
        rectangle.x += 20 * dk.q;
        final Rectangle rectangle2 = bounds;
        rectangle2.y += 20 * dk.q;
        this.setBounds(bounds);
    }
    
    static {
        dk.q = 0;
    }
}
