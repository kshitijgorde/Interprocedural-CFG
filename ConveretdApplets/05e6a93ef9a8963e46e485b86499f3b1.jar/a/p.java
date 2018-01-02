// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Container;
import java.awt.FontMetrics;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Event;

public class p extends w
{
    private aJ q;
    private boolean q;
    private N q;
    public String q;
    
    public void setVisible(final boolean visible) {
        if (visible) {
            this.pack();
            this.q();
        }
        super.setVisible(visible);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.dispose();
                if (this.q != null) {
                    this.q.q(null);
                }
                return true;
            }
            case 1001: {
                if (event.target instanceof N) {
                    final String q = ((N)event.target).q;
                    this.q = q;
                    this.dispose();
                    if (this.q != null) {
                        this.q.q(q);
                    }
                    return true;
                }
                break;
            }
            case 401: {
                if (event.key == 10 || event.key == bE.q) {
                    this.q.r();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public p(final Frame frame, final String s, final Throwable t, final bq bq) {
        this(frame, al.q("Error"), new String[] { al.q("OK") }, new String[] { s, t.toString() }, null, bq, null, null);
    }
    
    public p(final Frame frame, final String s, final String s2, final bq bq) {
        this(frame, s, new String[] { al.q("OK") }, new String[] { s2 }, null, bq, null, null);
    }
    
    public p(final Frame frame, final String s, final String s2, final bq bq, final Color color, final Color color2) {
        this(frame, s, new String[] { al.q("OK") }, new String[] { s2 }, null, bq, color, color2);
    }
    
    public p(final Frame frame, final String s, final String[] array, final bq bq) {
        this(frame, s, new String[] { al.q("OK") }, array, null, bq, null, null);
    }
    
    public p(final Frame frame, final String s, final String[] array, final String[] array2, final aJ aj, final bq bq) {
        this(frame, s, array, array2, aj, bq, null, null);
    }
    
    private p(final Frame frame, final String s, final String[] array, final String[] array2, final aJ q, final bq bq, Color u, Color i) {
        super(frame, s, true);
        this.q = false;
        final aT at = (bq != null) ? aT.w : aT.q;
        if (u == null) {
            u = at.u;
        }
        if (i == null) {
            i = at.i;
        }
        this.setBackground(i);
        this.setForeground(u);
        this.q = q;
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = 18;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 1.0;
        for (int j = 0; j < array2.length; ++j) {
            final q q2 = new q(array2[j]);
            if (j == 0) {
                q2.setFont(be.q);
            }
            else {
                q2.setFont(be.r);
            }
            q2.resize(300, 20);
            gridBagLayout.setConstraints(q2, gridBagConstraints);
            this.add(q2);
        }
        final FontMetrics fontMetrics = this.getFontMetrics(be.w);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.0;
        final Container container;
        (container = new Panel()).setLayout(gridBagLayout);
        for (int k = array.length - 1; k >= 0; --k) {
            int n;
            if ((n = fontMetrics.stringWidth(array[k]) + 20) < 60) {
                n = 60;
            }
            final N q3 = new N(array[k], n, 20);
            if (k == 0) {
                this.q = q3;
                final ab ab = new ab(q3);
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(ab, gridBagConstraints);
                container.add(ab);
            }
            else {
                gridBagLayout.setConstraints(q3, gridBagConstraints);
                container.add(q3);
            }
        }
        gridBagLayout.setConstraints(container, gridBagConstraints);
        this.add(container);
    }
    
    public final void q(final String s) {
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final Component component;
            if ((component = components[i]) instanceof q) {
                this.invalidate();
                ((q)component).q(s);
                this.validate();
                return;
            }
        }
    }
}
