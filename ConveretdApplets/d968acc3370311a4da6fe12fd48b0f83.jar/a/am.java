// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Vector;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Container;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Label;

public final class am extends ah
{
    private g q;
    public cI q;
    private dz q;
    private Label q;
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 401: {
                if (event.key == 27) {
                    this.q.r();
                    return true;
                }
                break;
            }
            case 1001: {
                if (event.target == this.q) {
                    this.dispose();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public am(final Frame frame, dz q) {
        super(frame, aJ.m, false);
        this.q = q;
        super.q = new g(80, 20);
        this.q = new g(80, 20);
        (this.q = new cI(q, false)).setFont(cf.w.w());
        final cI q2 = this.q;
        q = this.q;
        bh bh;
        if (aG.q()) {
            bh = new bp(this, q);
        }
        else {
            bh = new be(this, q);
        }
        q2.q(bh);
        this.setBackground(cf.w.q);
        final s s = new s();
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.setResizable(true);
        this.setLayout(gridBagLayout);
        s.setLayout(gridBagLayout);
        s.setBackground(cf.w.i);
        s.setForeground(cf.w.u);
        gridBagConstraints.insets = new Insets(1, 5, 1, 5);
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.anchor = 17;
        gridBagConstraints.fill = 2;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 0.0;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 1.0;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        final t t = new t(this.q);
        gridBagLayout.setConstraints(t, gridBagConstraints);
        s.add(t);
        s.add(this.q = new Label(""), gridBagConstraints);
        gridBagLayout.setConstraints(s, gridBagConstraints);
        this.add(s);
        gridBagConstraints.insets = new Insets(2, 5, 2, 5);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.fill = 0;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.weighty = 0.0;
        this.q.q(eb.q("Close"));
        this.q.t();
        gridBagLayout.setConstraints(this.q, gridBagConstraints);
        this.add(this.q);
        this.q.setSize(431, 265);
        this.pack();
        this.w();
    }
    
    public final void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible) {
            final Vector vector = new Vector<cp>();
            int n = 0;
            int n2 = 0;
            for (int i = 0; i < this.q.c.q(); ++i) {
                final cp cp = (cp)this.q.c.q(i);
                n += cp.getName().length();
                dj dj = null;
                for (int j = 0; j < this.q.e.q(); ++j) {
                    final dj dj2;
                    if ((dj2 = (dj)this.q.e.q(j)).getName().equals(cp.w())) {
                        dj = dj2;
                        break;
                    }
                }
                if (dj == null) {
                    (dj = new dj(-999, cp.w())).e = -1;
                    final bT bt;
                    if ((bt = (bT)this.q.b.w(bJ.q(cp.w(), this.q.b))) != null) {
                        dj.y(bt.y());
                        final cl cl;
                        if ((cl = (cl)this.q.x.w(bt.q)) != null) {
                            dj.p = cl.q;
                        }
                        else {
                            dj.p = -1;
                        }
                    }
                    else {
                        dj.y(2125055);
                        dj.p = -1;
                    }
                }
                else {
                    dj.e(cp.q());
                }
                final StringBuffer sb = new StringBuffer();
                final String format = eq.q.format(cp.q());
                sb.append(cp.getName());
                final cS cs;
                (cs = new cS(sb.toString(), dj, false, false, (cm)this.q.w.w(dj.e), this.q)).p = cp.y();
                cs.q(format);
                if (cp.q()) {
                    cs.d = 14869218;
                }
                else {
                    cs.d = 13684944;
                    ++n2;
                }
                this.q.q(cs);
                if (!cp.q()) {
                    vector.addElement(cp);
                    cp.q(true);
                }
            }
            aP.r = null;
            if (vector.size() > 0) {
                final es es;
                (es = new es(4202528, vector.size())).q = -1;
                es.w = -1;
                for (int k = 0; k < vector.size(); ++k) {
                    final cp cp2 = vector.elementAt(k);
                    es.q(k, 0, 3);
                    es.q(k, 1, cp2.q());
                }
                this.q.q(es);
            }
            this.q.setText("(" + n2 + " " + dV.q(">5G") + ", " + n * 100 / 5120 + dV.q("PEC54KC@135") + ")");
        }
    }
}
