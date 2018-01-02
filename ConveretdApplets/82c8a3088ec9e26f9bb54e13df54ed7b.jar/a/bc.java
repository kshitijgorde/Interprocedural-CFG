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

public final class bc extends v
{
    private M q;
    private M w;
    private TextField q;
    private bl q;
    private bp q;
    private Frame w;
    private boolean q;
    public ck q;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == bD.q) {
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
                        new bR(this.w, ak.q("Note"), ak.q("You have not entered the correct password for this room.  Please try again."), this.q).setVisible(true);
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
    
    public bc(final Frame w, final bp q, final bl q2) {
        super(w, ak.q("Enter Password"), true);
        this.q = new M(80, 20);
        this.w = new M(80, 20);
        this.q = new TextField(15);
        this.q = false;
        this.setBackground(aS.w.i);
        this.setForeground(aS.w.u);
        this.setResizable(false);
        this.q = q;
        this.w = w;
        final m m = new m();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        final p p3 = new p(s.q(ak.q("You must enter the correct password to enter %1."), new String[] { q2.o }));
        ((M)(this.q = q2)).q(ak.q("OK"));
        this.q.t();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        p3.setFont(bd.q);
        layout.setConstraints(p3, gridBagConstraints);
        this.add(p3);
        gridBagConstraints.weightx = 1.0;
        layout.setConstraints(m, gridBagConstraints);
        this.add(m);
        gridBagConstraints.insets = new Insets(6, 6, 6, 6);
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 0;
        this.q.setEchoCharacter('*');
        final bb bb = new bb(this.q);
        layout.setConstraints(bb, gridBagConstraints);
        this.add(bb);
        gridBagConstraints.insets = new Insets(2, 5, 4, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.w.q(ak.q("Cancel"));
        this.w.t();
        layout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        final Z z = new Z(this.q);
        layout.setConstraints(z, gridBagConstraints);
        this.add(z);
        this.pack();
        this.q.requestFocus();
    }
}
