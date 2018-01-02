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

public final class N extends ah
{
    private cV q;
    private g q;
    private dW q;
    private P q;
    private M q;
    
    public N(final Frame frame, final cV q, final M q2) {
        super(frame, eb.q(dV.q("t>F9D5K@1BD939@1>DC")), true);
        this.q = q2;
        (this.q = new dW()).q(q);
        this.q = q;
        final O o;
        (o = new O(this)).setBackground(cf.w.w);
        o.setLayout(new GridBagLayout());
        this.setLayout(new GridBagLayout());
        final GridBagConstraints gridBagConstraints;
        (gridBagConstraints = new GridBagConstraints()).insets = new Insets(10, 10, 10, 10);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        this.q = new P(q, true, null, this.q, null);
        final s s;
        (s = new s()).setLayout(new GridBagLayout());
        s.setBackground(cf.w.i);
        gridBagConstraints.insets = new Insets(3, 2, 3, 2);
        s.add(this.q, gridBagConstraints);
        gridBagConstraints.insets = new Insets(50, 11, 5, 11);
        o.add(s, gridBagConstraints);
        this.q = new g(eb.q(dV.q("t>F9D5")));
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weighty = 0.0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.insets = new Insets(5, 50, 10, 50);
        o.add(this.q, gridBagConstraints);
        this.setBounds(100, 100, 250, 445);
        gridBagConstraints.fill = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(0, 0, 0, 0);
        this.add(o, gridBagConstraints);
        this.setResizable(false);
    }
    
    public final void setVisible(final boolean visible) {
        this.q.w();
        synchronized (this.q.e) {
            for (int i = 0; i < this.q.e.q(); ++i) {
                try {
                    final dj dj = (dj)this.q.e.q(i);
                    if (!this.q.q(dj.q()) && dj.q() != this.q.q()) {
                        this.q.q(dj, true);
                    }
                }
                catch (ClassCastException ex) {
                    ex.printStackTrace();
                }
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
                            final dj dj = q.elementAt(i);
                            this.q.q(dj);
                            if (n != 0) {
                                s += String.valueOf(new char[] { ',' });
                            }
                            s = s + dj.q() + "";
                            n = 1;
                        }
                        final cV q2 = this.q;
                        final String s2 = s;
                        final int q3 = this.q.q();
                        final M q4 = this.q;
                        q2.q(s2, q3, 3);
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
    
    public final void q(final int n) {
        this.q.w(n);
    }
}
