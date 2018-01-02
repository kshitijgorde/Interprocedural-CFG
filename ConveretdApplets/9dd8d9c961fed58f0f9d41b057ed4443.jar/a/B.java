// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Panel;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.Label;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.awt.Point;
import java.awt.Color;
import java.awt.Event;
import java.awt.TextArea;
import java.awt.Checkbox;

public final class B extends v
{
    private M q;
    private br q;
    private static Checkbox q;
    private TextArea q;
    private bg q;
    private au q;
    private ai q;
    public static boolean q;
    private String q;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 27) {
                    this.q.w();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.q) {
                    this.q.w();
                    return true;
                }
                if (event.target == this.q) {
                    cu.q(" " + ((aO)this.q.q()).o, this.q);
                    if (B.q.getState()) {
                        this.q.w();
                    }
                }
                return true;
            }
            case 201: {
                this.q.w();
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public final synchronized void hide() {
        B.q = false;
        super.hide();
    }
    
    private void w() {
        this.q.r();
        try {
            for (int i = 0; i < this.q.w().q; ++i) {
                final aO ao;
                if ((ao = (aO)this.q.w().q(i)).q(36)) {
                    final K k = new K(ao);
                    this.q.q((aY)k);
                    this.q.q(k, new Color(k.w()), Color.pink);
                    if (ao.e().length() > this.q.length()) {
                        this.q = ao.e();
                    }
                }
            }
        }
        finally {}
    }
    
    public B(final ai q, final Point location) {
        super(new Frame(), ak.q("Select a shortcut"), false);
        this.q = "";
        this.q = new M(80, 20);
        this.q = q;
        this.q = q.q();
        this.setLocation(location);
        this.setBackground(aU.w.q);
        final cy cy = new cy();
        final m m = new m();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        final Label label = new Label(ak.q("Select shortcut"));
        this.q = q.q();
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        cy.setLayout(gridBagLayout);
        cy.setBackground(aU.w.i);
        cy.setForeground(aU.w.u);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        label.setFont(bf.q);
        gridBagLayout.setConstraints(label, gridBagConstraints);
        cy.add(label);
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagLayout.setConstraints(m, gridBagConstraints);
        cy.add(m);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        (this.q = new au(ak.q("Shortcut"), "replace")).q(false);
        (this.q = new bg()).w(this.q);
        final Panel panel = new Panel();
        final GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        panel.setLayout(layout);
        this.w();
        gridBagConstraints2.insets = new Insets(4, 4, 4, 4);
        gridBagConstraints2.gridwidth = -1;
        gridBagConstraints2.gridheight = 0;
        gridBagConstraints2.weighty = 1.0;
        gridBagConstraints2.weightx = 1.0;
        gridBagConstraints2.fill = 1;
        this.q.getFontMetrics(this.q.getFont());
        this.q.setSize(250, 241);
        final bd bd = new bd(this.q);
        layout.setConstraints(bd, gridBagConstraints2);
        panel.add(bd);
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridwidth = 0;
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        cy.add(panel);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagLayout.setConstraints(cy, gridBagConstraints);
        this.add(cy);
        if (B.q == null) {
            (B.q = new Checkbox(ak.q("Close after selection"))).setForeground(aU.w.u);
            B.q.setState(true);
        }
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.gridwidth = -2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        gridBagLayout.setConstraints(B.q, gridBagConstraints);
        this.add(B.q);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 0;
        this.q.q(ak.q("Close"));
        this.q.t();
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        this.pack();
        B.q = true;
    }
    
    static {
        B.q = false;
    }
}
