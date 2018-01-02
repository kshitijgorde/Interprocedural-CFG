// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Date;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.awt.Event;
import java.text.SimpleDateFormat;

public final class al extends ah
{
    private g q;
    private cI q;
    private dz q;
    private SimpleDateFormat q;
    
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
    
    public al(final Frame frame, final dz q) {
        super(frame, ec.q(eb.q("View %1"), eb.q("Notes")), false);
        this.q = new SimpleDateFormat("dd MMM yy HH:mm");
        this.q = q;
        super.q = new g(80, 20);
        this.q = new g(80, 20);
        (this.q = new cI(q, false)).setFont(cf.w.w());
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
        this.q.setSize(435, 300);
        this.pack();
        this.w();
    }
    
    public final void setVisible(final boolean visible) {
        super.setVisible(visible);
        if (visible) {
            final ch[] q;
            final ch[] array = q = this.q.v.q();
            for (int i = 1; i < q.length; ++i) {
                ch ch;
                int n;
                for (ch = q[i], n = i; n > 0 && q[n - 1].q(ch) >= 0; --n) {
                    q[n] = q[n - 1];
                }
                q[n] = ch;
            }
            for (int j = 0; j < array.length; ++j) {
                final co co = (co)array[j];
                if (this.q.i() == co.w || co.w == -1) {
                    dj dj = null;
                    for (int k = 0; k < this.q.e.q(); ++k) {
                        final dj dj2;
                        if ((dj2 = (dj)this.q.e.q(k)).getName().equals(co.r)) {
                            dj = dj2;
                            break;
                        }
                    }
                    if (dj == null) {
                        (dj = new dj(-999, co.r)).e = -1;
                        final bT bt;
                        if ((bt = (bT)this.q.b.w(bJ.q(co.r, this.q.b))) != null) {
                            if (co.w > 0 && co.w != bt.q()) {
                                continue;
                            }
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
                    final StringBuffer sb = new StringBuffer();
                    final String format = this.q.format(new Date(co.q));
                    sb.append(co.getName());
                    final cS cs;
                    (cs = new cS(sb.toString(), dj, false, false, (cm)this.q.w.w(dj.e), null)).d = co.t();
                    cs.q(format);
                    this.q.q(cs);
                }
            }
            aP.w = null;
        }
    }
}
