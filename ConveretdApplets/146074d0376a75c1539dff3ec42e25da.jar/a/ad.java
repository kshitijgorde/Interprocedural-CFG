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

public class ad extends ah
{
    protected g q;
    private g w;
    protected TextField q;
    protected db q;
    private cM q;
    private Frame w;
    protected boolean q;
    private ep q;
    
    public final ep q() {
        return this.q;
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 10 || event.key == ef.q) {
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
                    final ep q;
                    if ((q = new ep(this.q.getText())).equals(this.q.q)) {
                        this.q = q;
                        this.q = true;
                        this.dispose();
                        this.q.e = false;
                    }
                    else {
                        new b(this.w, eb.q("Note"), eb.q("You have not entered the correct password for this room.  Please try again."), this.q).setVisible(true);
                    }
                }
                else if (event.target == this.w) {
                    this.q = true;
                    this.dispose();
                    if (((cz)this.q).o == -999 || !this.q.w()) {
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
    
    public ad(final Frame w, final cM q, final db q2) {
        super(w, eb.q("Enter Password"), true);
        this.q = new g(80, 20);
        this.w = new g(80, 20);
        this.q = new TextField(15);
        this.q = false;
        this.setBackground(cf.w.i);
        this.setForeground(cf.w.u);
        this.setResizable(false);
        this.q = q;
        this.w = w;
        final q q3 = new q();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        final H h = new H(ec.q(eb.q("You must enter the correct password to enter %1."), new String[] { q2.getName() }));
        ((g)(this.q = q2)).q(eb.q("OK"));
        this.q.t();
        this.setLayout(layout);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 2;
        h.setFont(m.q);
        layout.setConstraints(h, gridBagConstraints);
        this.add(h);
        gridBagConstraints.weightx = 1.0;
        layout.setConstraints(q3, gridBagConstraints);
        this.add(q3);
        gridBagConstraints.insets = new Insets(6, 6, 6, 6);
        gridBagConstraints.anchor = 10;
        gridBagConstraints.fill = 0;
        this.q.setEchoCharacter('*');
        final t t = new t(this.q);
        layout.setConstraints(t, gridBagConstraints);
        this.add(t);
        gridBagConstraints.insets = new Insets(2, 5, 4, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = -1;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.w.q(eb.q("Cancel"));
        this.w.t();
        layout.setConstraints(this.w, gridBagConstraints);
        this.add(this.w);
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        final f f = new f(this.q);
        layout.setConstraints(f, gridBagConstraints);
        this.add(f);
        this.pack();
        this.q.requestFocus();
    }
}
