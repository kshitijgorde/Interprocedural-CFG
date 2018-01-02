// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;
import java.awt.Event;
import java.awt.Component;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Frame;

public final class cz extends w
{
    private W q;
    private N q;
    B q;
    private bk q;
    private aP q;
    
    public cz(final Frame frame, final W q, final aP q2) {
        super(frame, al.q(ce.q("t>F9D5K@1BD939@1>DC")), true);
        this.q = q2;
        (this.q = new B()).q(q);
        this.q = q;
        final cA ca;
        (ca = new cA(this)).setBackground(aT.w.w);
        ca.setLayout(new GridBagLayout());
        this.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints;
        (gridBagConstraints = new GridBagConstraints()).insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        this.q = new bk(q, true, null, this.q, null);
        final cv cv;
        (cv = new cv()).setLayout(new GridBagLayout());
        cv.setBackground(aT.w.i);
        gridBagConstraints.insets = new Insets(3, 2, 3, 2);
        cv.add(this.q, gridBagConstraints);
        gridBagConstraints.insets = new Insets(50, 11, 5, 11);
        ca.add(cv, gridBagConstraints);
        this.q = new N(al.q(ce.q("t>F9D5")));
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.insets = new Insets(5, 50, 10, 50);
        ca.add(this.q, gridBagConstraints);
        this.setBounds(100, 100, 250, 445);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        this.add(ca, gridBagConstraints);
        this.setResizable(false);
    }
    
    public final void setVisible(final boolean visible) {
        this.q.w();
        synchronized (this.q.e) {
            for (int i = 0; i < this.q.e.q; ++i) {
                try {
                    final as as = (as)this.q.e.q(i);
                    if (!this.q.q(as.a) && as.a != this.q.a) {
                        this.q.q(as, true);
                    }
                }
                catch (ClassCastException ex) {}
            }
        }
        super.setVisible(visible);
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.q.w();
                this.setVisible(false);
                return true;
            }
            case 1001: {
                if (event.target == this.q) {
                    final Vector q;
                    if ((q = this.q.q()) != null) {
                        String s = "";
                        int n = 0;
                        for (int i = 0; i < q.size(); ++i) {
                            final as as = q.elementAt(i);
                            this.q.q(as);
                            if (n != 0) {
                                s += String.valueOf(new char[] { ',' });
                            }
                            s = s + as.a + "";
                            n = 1;
                        }
                        this.q.q(s, this.q.q, 3);
                    }
                    this.setVisible(false);
                    this.q.w();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
}
