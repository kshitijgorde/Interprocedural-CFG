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

public class bR extends v
{
    private aI q;
    private boolean q;
    private M q;
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
                if (event.target instanceof M) {
                    final String q = ((M)event.target).q;
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
                if (event.key == 10 || event.key == bD.q) {
                    this.q.r();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public bR(final Frame frame, final String s, final Throwable t, final bp bp) {
        this(frame, ak.q("Error"), new String[] { ak.q("OK") }, new String[] { s, t.toString() }, null, bp, null, null);
    }
    
    public bR(final Frame frame, final String s, final String s2, final bp bp) {
        this(frame, s, new String[] { ak.q("OK") }, new String[] { s2 }, null, bp, null, null);
    }
    
    public bR(final Frame frame, final String s, final String s2, final bp bp, final Color color, final Color color2) {
        this(frame, s, new String[] { ak.q("OK") }, new String[] { s2 }, null, bp, color, color2);
    }
    
    public bR(final Frame frame, final String s, final String[] array, final bp bp) {
        this(frame, s, new String[] { ak.q("OK") }, array, null, bp, null, null);
    }
    
    public bR(final Frame frame, final String s, final String[] array, final String[] array2, final aI ai, final bp bp) {
        this(frame, s, array, array2, ai, bp, null, null);
    }
    
    private bR(final Frame frame, final String s, final String[] array, final String[] array2, final aI q, final bp bp, Color u, Color i) {
        super(frame, s, true);
        this.q = false;
        final aS as = (bp != null) ? aS.w : aS.q;
        if (u == null) {
            u = as.u;
        }
        if (i == null) {
            i = as.i;
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
            final p p8 = new p(array2[j]);
            if (j == 0) {
                p8.setFont(bd.q);
            }
            else {
                p8.setFont(bd.r);
            }
            p8.resize(300, 20);
            gridBagLayout.setConstraints(p8, gridBagConstraints);
            this.add(p8);
        }
        final FontMetrics fontMetrics = this.getFontMetrics(bd.w);
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
            final M q2 = new M(array[k], n, 20);
            if (k == 0) {
                this.q = q2;
                final Z z = new Z(q2);
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(z, gridBagConstraints);
                container.add(z);
            }
            else {
                gridBagLayout.setConstraints(q2, gridBagConstraints);
                container.add(q2);
            }
        }
        gridBagLayout.setConstraints(container, gridBagConstraints);
        this.add(container);
    }
    
    public final void q(final String s) {
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final Component component;
            if ((component = components[i]) instanceof p) {
                this.invalidate();
                ((p)component).q(s);
                this.validate();
                return;
            }
        }
    }
}
