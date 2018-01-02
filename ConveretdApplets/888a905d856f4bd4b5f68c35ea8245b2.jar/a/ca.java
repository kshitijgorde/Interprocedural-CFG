// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Event;
import java.awt.Frame;
import java.awt.TextField;

public class ca extends F
{
    protected ad q;
    private ad w;
    protected TextField q;
    protected cj q;
    private cs q;
    private Frame w;
    protected boolean q;
    public dD q;
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == cK.q) {
                    this.q.r();
                    return true;
                }
                if (event.key == 27) {
                    this.w.r();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.q) {
                    final dD q;
                    if ((q = new dD(this.q.getText())).equals(this.q.q)) {
                        this.q = q;
                        this.q = true;
                        this.dispose();
                        this.q.w = false;
                    }
                    else {
                        new dd(this.w, be.w("Note"), be.w("You have not entered the correct password for this room.  Please try again."), this.q).setVisible(true);
                    }
                }
                else if (event.target == this.w) {
                    this.q = true;
                    this.dispose();
                    if (((p)this.q).r == -999 || !this.q.w()) {
                        this.q.u();
                    }
                }
                return true;
            }
            case 201: {
                return this.q && super.handleEvent(event);
            }
        }
        return super.handleEvent(event);
    }
    
    public ca(final Frame w, final cs q, final cj q2) {
        super(w, be.w("Enter Password"), true);
        this.q = new ad(80, 20);
        this.w = new ad(80, 20);
        this.q = new TextField(15);
        this.q = false;
        this.setBackground(bC.w.i);
        this.setForeground(bC.w.u);
        this.setResizable(false);
        this.q = q;
        this.w = w;
        final q q3 = new q();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        final u u = new u(B.q(be.w("You must enter the correct password to enter %1."), new String[] { q2.a }));
        ((ad)(this.q = q2)).q(be.w("OK"));
        this.q.t();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        u.setFont(cb.q);
        layout.setConstraints(u, gridBagConstraints);
        this.add(u);
        gridBagConstraints.weightx = 1.0;
        layout.setConstraints(q3, gridBagConstraints);
        this.add(q3);
        gridBagConstraints.insets = new Insets(6, 6, 6, 6);
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 0;
        this.q.setEchoCharacter('*');
        final bZ bz = new bZ(this.q);
        layout.setConstraints(bz, gridBagConstraints);
        this.add(bz);
        gridBagConstraints.insets = new Insets(2, 5, 4, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.w.q(be.w("Cancel"));
        this.w.t();
        layout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        final as as = new as(this.q);
        layout.setConstraints(as, gridBagConstraints);
        this.add(as);
        this.pack();
        this.q.requestFocus();
    }
}
