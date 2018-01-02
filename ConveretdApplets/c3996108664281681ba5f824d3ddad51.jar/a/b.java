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

public class b extends W
{
    private cw q;
    private boolean q;
    private e q;
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
                if (event.target instanceof e) {
                    final String q = ((e)event.target).q();
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
                if (event.key == 10 || event.key == cx.q) {
                    this.q.r();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public b(final Frame frame, final String s, final Throwable t, final bz bz) {
        this(frame, cv.q("Error"), new String[] { cv.q("OK") }, new String[] { s, t.toString() }, null, bz, null, null);
    }
    
    public b(final Frame frame, final String s, final String s2, final bz bz) {
        this(frame, s, new String[] { cv.q("OK") }, new String[] { s2 }, null, bz, null, null);
    }
    
    public b(final Frame frame, final String s, final String s2, final bz bz, final Color color, final Color color2) {
        this(frame, s, new String[] { cv.q("OK") }, new String[] { s2 }, null, bz, color, color2);
    }
    
    public b(final Frame frame, final String s, final String[] array, final bz bz) {
        this(frame, s, new String[] { cv.q("OK") }, array, null, bz, null, null);
    }
    
    public b(final Frame frame, final String s, final String[] array, final String[] array2, final cw cw, final bz bz) {
        this(frame, s, array, array2, cw, bz, null, null);
    }
    
    private b(final Frame frame, final String s, final String[] array, final String[] array2, final cw q, final bz bz, Color u, Color i) {
        super(frame, s, true);
        this.q = false;
        final be be = (bz != null) ? a.be.w : a.be.q;
        if (u == null) {
            u = be.u;
        }
        if (i == null) {
            i = be.i;
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
            final E e = new E(array2[j]);
            if (j == 0) {
                e.setFont(k.q);
            }
            else {
                e.setFont(k.r);
            }
            e.resize(300, 20);
            gridBagLayout.setConstraints(e, gridBagConstraints);
            this.add(e);
        }
        final FontMetrics fontMetrics = this.getFontMetrics(k.w);
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
            final e q2 = new e(array[k], n, 20);
            if (k == 0) {
                this.q = q2;
                final d d = new d(q2);
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(d, gridBagConstraints);
                container.add(d);
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
            if ((component = components[i]) instanceof E) {
                this.invalidate();
                ((E)component).q(s);
                this.validate();
                return;
            }
        }
    }
}
