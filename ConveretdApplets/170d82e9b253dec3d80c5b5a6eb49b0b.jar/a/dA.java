// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Rectangle;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Label;
import com.spilka.client.muc.AppletAbstract;
import java.awt.LayoutManager;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.CardLayout;
import java.awt.TextField;
import java.awt.Event;
import java.awt.Choice;

public final class dA extends u
{
    private static int q;
    private dz q;
    private cG q;
    private Choice q;
    
    public final void dispose() {
        super.dispose();
        if (--dA.q > 0) {
            this.q.w();
            return;
        }
        this.q.t();
    }
    
    public final void w() {
        this.q.e();
    }
    
    public final void q(final dB db) {
        if (this.q.getSelectedIndex() != this.q.E.q((Object)db)) {
            this.q.select(this.q.E.q((Object)db));
        }
        this.q.w();
        switch (db.q()) {
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
                    this.q.q((dB)this.q.E.q(this.q.getSelectedIndex()));
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public dA(final dz q) {
        super(ec.q(eb.q("%1 Login"), new String[] { a.e }), false);
        new g(70, 20);
        new TextField(20);
        new TextField(20);
        new TextField(20);
        new TextField(10);
        new CardLayout(0, 0);
        this.q = new Choice();
        this.q = q;
        ++dA.q;
        final dW e = this.q.E;
        dW.q();
        try {
            for (int i = 0; i < this.q.E.q(); ++i) {
                this.q.addItem(((dB)this.q.E.q(i)).getName());
            }
        }
        finally {
            final dW e2 = this.q.E;
            dW.w();
        }
        this.setResizable(false);
        final GridBagLayout layout = new GridBagLayout();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final s s;
        (s = new s()).setLayout(layout);
        this.q = new cG(AppletAbstract.q(), q, true, true, true, true);
        final Label label;
        (label = new Label(eb.q("Connect as "))).setFont(m.t);
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.insets = new Insets(2, 2, 2, 6);
        layout.setConstraints(label, gridBagConstraints);
        s.add(label);
        gridBagConstraints.gridwidth = 0;
        layout.setConstraints(this.q, gridBagConstraints);
        s.add(this.q);
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        gridBagConstraints.gridheight = 0;
        layout.setConstraints(this.q, gridBagConstraints);
        s.add(this.q);
        this.setLayout(new BorderLayout());
        this.add("Center", s);
        this.pack();
        this.a_();
        final Rectangle bounds;
        final Rectangle rectangle = bounds = this.getBounds();
        rectangle.x += 20 * dA.q;
        final Rectangle rectangle2 = bounds;
        rectangle2.y += 20 * dA.q;
        this.setBounds(bounds);
    }
    
    static {
        dA.q = 0;
    }
}
