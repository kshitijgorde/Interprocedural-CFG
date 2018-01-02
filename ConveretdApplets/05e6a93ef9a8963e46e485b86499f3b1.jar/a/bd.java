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

public final class bd extends w
{
    private N q;
    private N w;
    private TextField q;
    private bm q;
    private bq q;
    private Frame w;
    private boolean q;
    public ck q;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == bE.q) {
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
                    final ck q;
                    if ((q = new ck(this.q.getText())).equals(this.q.q)) {
                        this.q = q;
                        this.q = true;
                        this.dispose();
                        this.q.w = false;
                    }
                    else {
                        new p(this.w, al.q("Note"), al.q("You have not entered the correct password for this room.  Please try again."), this.q).setVisible(true);
                    }
                }
                else if (event.target == this.w) {
                    this.q = true;
                    this.dispose();
                    if (((l)this.q).r == -999 || !this.q.r()) {
                        this.q.y();
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
    
    public bd(final Frame w, final bq q, final bm q2) {
        super(w, al.q("Enter Password"), true);
        this.q = new N(80, 20);
        this.w = new N(80, 20);
        this.q = new TextField(15);
        this.q = false;
        this.setBackground(aT.w.i);
        this.setForeground(aT.w.u);
        this.setResizable(false);
        this.q = q;
        this.w = w;
        final m m = new m();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        final q q3 = new q(t.q(al.q("You must enter the correct password to enter %1."), new String[] { q2.o }));
        ((N)(this.q = q2)).q(al.q("OK"));
        this.q.t();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        q3.setFont(be.q);
        layout.setConstraints(q3, gridBagConstraints);
        this.add(q3);
        gridBagConstraints.weightx = 1.0;
        layout.setConstraints(m, gridBagConstraints);
        this.add(m);
        gridBagConstraints.insets = new Insets(6, 6, 6, 6);
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 0;
        this.q.setEchoCharacter('*');
        final bc bc = new bc(this.q);
        layout.setConstraints(bc, gridBagConstraints);
        this.add(bc);
        gridBagConstraints.insets = new Insets(2, 5, 4, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.w.q(al.q("Cancel"));
        this.w.t();
        layout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        final ab ab = new ab(this.q);
        layout.setConstraints(ab, gridBagConstraints);
        this.add(ab);
        this.pack();
        this.q.requestFocus();
    }
}
