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

public class dd extends F
{
    private bn q;
    private boolean q;
    private ad q;
    public String q;
    
    public void setVisible(final boolean visible) {
        if (visible) {
            this.pack();
            this.w();
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
                if (event.target instanceof ad) {
                    final String q = ((ad)event.target).q;
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
                if (event.key == 10 || event.key == cK.q) {
                    this.q.r();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public dd(final Frame frame, final String s, final Throwable t, final cs cs) {
        this(frame, be.w("Error"), new String[] { be.w("OK") }, new String[] { s, t.toString() }, null, cs, null, null);
    }
    
    public dd(final Frame frame, final String s, final String s2, final cs cs) {
        this(frame, s, new String[] { be.w("OK") }, new String[] { s2 }, null, cs, null, null);
    }
    
    public dd(final Frame frame, final String s, final String s2, final cs cs, final Color color, final Color color2) {
        this(frame, s, new String[] { be.w("OK") }, new String[] { s2 }, null, cs, color, color2);
    }
    
    public dd(final Frame frame, final String s, final String[] array, final cs cs) {
        this(frame, s, new String[] { be.w("OK") }, array, null, cs, null, null);
    }
    
    public dd(final Frame frame, final String s, final String[] array, final String[] array2, final bn bn, final cs cs) {
        this(frame, s, array, array2, bn, cs, null, null);
    }
    
    private dd(final Frame frame, final String s, final String[] array, final String[] array2, final bn q, final cs cs, Color u, Color i) {
        super(frame, s, true);
        this.q = false;
        final bC bc = (cs != null) ? bC.w : bC.q;
        if (u == null) {
            u = bc.u;
        }
        if (i == null) {
            i = bc.i;
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
            final u u2 = new u(array2[j]);
            if (j == 0) {
                u2.setFont(cb.q);
            }
            else {
                u2.setFont(cb.r);
            }
            u2.resize(300, 20);
            gridBagLayout.setConstraints(u2, gridBagConstraints);
            this.add(u2);
        }
        final FontMetrics fontMetrics = this.getFontMetrics(cb.w);
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
            final ad q2 = new ad(array[k], n, 20);
            if (k == 0) {
                this.q = q2;
                final as as = new as(q2);
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(as, gridBagConstraints);
                container.add(as);
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
            if ((component = components[i]) instanceof u) {
                this.invalidate();
                ((u)component).q(s);
                this.validate();
                return;
            }
        }
    }
}
