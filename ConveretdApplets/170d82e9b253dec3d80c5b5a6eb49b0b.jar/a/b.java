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

public class b extends ah
{
    private ed q;
    private boolean q;
    private g q;
    private String q;
    
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
                if (event.target instanceof g) {
                    final String q = ((g)event.target).q();
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
                if (event.key == 10 || event.key == ef.q) {
                    this.q.r();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public b(final Frame frame, final String s, final Throwable t, final cM cm) {
        this(frame, eb.q("Error"), new String[] { eb.q("OK") }, new String[] { s, t.toString() }, null, cm, null, null);
    }
    
    public b(final Frame frame, final String s, final String s2, final cM cm) {
        this(frame, s, new String[] { eb.q("OK") }, new String[] { s2 }, null, cm, null, null);
    }
    
    public b(final Frame frame, final String s, final String s2, final cM cm, final Color color, final Color color2) {
        this(frame, s, new String[] { eb.q("OK") }, new String[] { s2 }, null, cm, color, color2);
    }
    
    public b(final Frame frame, final String s, final String[] array, final cM cm) {
        this(frame, s, new String[] { eb.q("OK") }, array, null, cm, null, null);
    }
    
    public b(final Frame frame, final String s, final String[] array, final String[] array2, final ed ed, final cM cm) {
        this(frame, s, array, array2, ed, cm, null, null);
    }
    
    private b(final Frame frame, final String s, final String[] array, final String[] array2, final ed q, final cM cm, Color u, Color i) {
        super(frame, s, true);
        this.q = false;
        final cf cf = (cm != null) ? a.cf.w : a.cf.q;
        if (u == null) {
            u = cf.u;
        }
        if (i == null) {
            i = cf.i;
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
            final H h = new H(array2[j]);
            if (j == 0) {
                h.setFont(m.q);
            }
            else {
                h.setFont(m.r);
            }
            h.resize(300, 20);
            gridBagLayout.setConstraints(h, gridBagConstraints);
            this.add(h);
        }
        final FontMetrics fontMetrics = this.getFontMetrics(m.w);
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
            final g q2 = new g(array[k], n, 20);
            if (k == 0) {
                this.q = q2;
                final f f = new f(q2);
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(f, gridBagConstraints);
                container.add(f);
            }
            else {
                gridBagLayout.setConstraints(q2, gridBagConstraints);
                container.add(q2);
            }
        }
        gridBagLayout.setConstraints(container, gridBagConstraints);
        this.add(container);
    }
    
    public final String q() {
        return this.q;
    }
    
    public final void q(final String s) {
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            final Component component;
            if ((component = components[i]) instanceof H) {
                this.invalidate();
                ((H)component).q(s);
                this.validate();
                return;
            }
        }
    }
}
