// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Event;
import java.awt.Image;
import java.awt.Component;
import java.awt.Label;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Panel;
import java.text.SimpleDateFormat;
import java.awt.Frame;
import java.awt.TextField;

public final class ao extends v
{
    private static final String q;
    private static final String w;
    private M q;
    private M w;
    private TextField q;
    private ci q;
    private TextField w;
    private W q;
    
    public ao(final Frame frame, final W q) {
        super(frame, s.q(ak.q(ao.q), ak.q("...")), false);
        new SimpleDateFormat("dd MMM yy HH:mm");
        this.q = q;
        this.q = new M(80, 20);
        this.w = new M(80, 20);
        this.setBackground(aU.w.q);
        final cy cy = new cy();
        final Panel panel = new Panel();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setResizable(true);
        this.setLayout(layout);
        cy.setLayout(layout);
        cy.setBackground(aU.w.i);
        cy.setForeground(aU.w.u);
        panel.setLayout(layout);
        panel.setBackground(aU.w.i);
        panel.setForeground(aU.w.u);
        gridBagConstraints.anchor = 17;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridheight = 1;
        panel.add(new Label(ak.q(ao.w)), gridBagConstraints);
        this.q = new TextField(50);
        gridBagConstraints.gridwidth = 0;
        panel.add(this.q, gridBagConstraints);
        final Label label = new Label(ak.q(cg.q("x5CC175e")));
        gridBagConstraints.gridwidth = 1;
        panel.add(label, gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        panel.add(this.q = new bz(this, this.q), gridBagConstraints);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        panel.add(this.w = new TextField(3), gridBagConstraints);
        gridBagConstraints.gridwidth = 1;
        layout.setConstraints(panel, gridBagConstraints);
        cy.add(panel);
        gridBagConstraints.gridwidth = 0;
        final Image e;
        if ((e = this.q.e("postbox.gif", false)) != null) {
            final aG ag;
            (ag = new aG()).w(e);
            cy.add(ag, gridBagConstraints);
        }
        this.add(cy, gridBagConstraints);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.w.q(ak.q("Close"));
        this.w.t();
        layout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        this.q.q(ak.q("Send"));
        this.q.t();
        final Z z = new Z(this.q);
        layout.setConstraints(z, gridBagConstraints);
        this.add(z);
        this.pack();
        this.q();
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.target == this.w) {
                    return true;
                }
                if (event.target == this.q) {
                    this.setTitle(s.q(ak.q(ao.q), this.q.getText()));
                }
            }
            case 1001: {
                if (event.target == this.q || event.target == this.w) {
                    this.dispose();
                    return true;
                }
                if (event.target != this.q) {
                    break;
                }
                final String text = this.q.getText();
                final String q = this.q.q(false);
                if (text == null || text.trim().length() == 0) {
                    new bT(this.q.q(), ak.q("Error"), ak.q("Master name should be set"), this.q).setVisible(true);
                    this.q.requestFocus();
                    return true;
                }
                if (q == null || q.trim().length() == 0) {
                    new bT(this.q.q(), ak.q("Error"), ak.q("Message should be entered"), this.q).setVisible(true);
                    this.q.q.requestFocus();
                    return true;
                }
                final cr cr;
                (cr = new cr(4202528, 1)).q = -1;
                cr.w = -1;
                cr.q(0, 0, 1);
                cr.q(0, 2, this.q.q.getForeground().getRGB());
                cr.q(0, 0, text);
                cr.q(0, 1, q);
                this.q.r(cr);
                this.dispose();
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    static TextField q(final ao ao) {
        return ao.w;
    }
    
    static {
        q = cg.q("\u001f66<9>5K=5CC175KD?KP\\");
        w = cg.q("%C5BK>1=5e");
    }
}
