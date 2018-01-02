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

public final class T extends W
{
    private e q;
    private e w;
    private TextField q;
    private bO q;
    private bz q;
    private Frame w;
    private boolean q;
    private cH q;
    
    public final cH q() {
        return this.q;
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == cx.q) {
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
                    final cH q;
                    if ((q = new cH(this.q.getText())).equals(this.q.q)) {
                        this.q = q;
                        this.q = true;
                        this.dispose();
                        this.q.w = false;
                    }
                    else {
                        new b(this.w, cv.q("Note"), cv.q("You have not entered the correct password for this room.  Please try again."), this.q).setVisible(true);
                    }
                }
                else if (event.target == this.w) {
                    this.q = true;
                    this.dispose();
                    if (((bp)this.q).i == -999 || !this.q.w()) {
                        this.q.w();
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
    
    public T(final Frame w, final bz q, final bO q2) {
        super(w, cv.q("Enter Password"), true);
        this.q = new e(80, 20);
        this.w = new e(80, 20);
        this.q = new TextField(15);
        this.q = false;
        this.setBackground(be.w.i);
        this.setForeground(be.w.u);
        this.setResizable(false);
        this.q = q;
        this.w = w;
        final o o = new o();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        final E e = new E(cv.q(cv.q("You must enter the correct password to enter %1."), new String[] { q2.getName() }));
        ((e)(this.q = q2)).q(cv.q("OK"));
        this.q.t();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        e.setFont(k.q);
        layout.setConstraints(e, gridBagConstraints);
        this.add(e);
        gridBagConstraints.weightx = 1.0;
        layout.setConstraints(o, gridBagConstraints);
        this.add(o);
        gridBagConstraints.insets = new Insets(6, 6, 6, 6);
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 0;
        this.q.setEchoCharacter('*');
        final r r = new r(this.q);
        layout.setConstraints(r, gridBagConstraints);
        this.add(r);
        gridBagConstraints.insets = new Insets(2, 5, 4, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.w.q(cv.q("Cancel"));
        this.w.t();
        layout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        final d d = new d(this.q);
        layout.setConstraints(d, gridBagConstraints);
        this.add(d);
        this.pack();
        this.q.requestFocus();
    }
}
