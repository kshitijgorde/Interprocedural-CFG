// 
// Decompiled by Procyon v0.5.30
// 

package chat;

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

public final class bD extends bC
{
    private bc a;
    private cr a;
    public String a;
    
    public final void setVisible(final boolean visible) {
        if (visible) {
            this.pack();
            this.e();
        }
        super.setVisible(visible);
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                this.dispose();
                if (this.a != null) {
                    this.a.a(this, null);
                }
                return true;
            }
            case 1001: {
                if (event.target instanceof cr) {
                    final String a = ((cr)event.target).a;
                    this.a = a;
                    this.dispose();
                    if (this.a != null) {
                        this.a.a(this, a);
                    }
                    return true;
                }
                break;
            }
            case 401: {
                if (event.key == 10 || event.key == ce.a) {
                    this.a.c();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public bD(final String s, final Throwable t, final cs cs) {
        this(null, aS.a(4), new String[] { aS.a(2) }, new String[] { s, t.toString() }, null, cs);
    }
    
    public bD(final Frame frame, final String s, final String s2, final cs cs) {
        this(frame, s, new String[] { aS.a(2) }, new String[] { s2 }, null, cs);
    }
    
    public bD(final Frame frame, final String s, final String s2, final cs cs, final Color foreground, final Color background) {
        this(frame, s, new String[] { aS.a(2) }, new String[] { s2 }, null, cs);
        if (background != null) {
            this.setBackground(background);
        }
        if (foreground != null) {
            this.setForeground(foreground);
        }
    }
    
    public bD(final Frame frame, final String s, final String[] array, final cs cs) {
        this(frame, s, new String[] { aS.a(2) }, array, null, cs);
    }
    
    public bD(final Frame frame, final String s, final String[] array, final String[] array2, final bc a, final cs cs) {
        super(frame, s, true);
        final cz cz = (cs == null) ? chat.cz.a : cs.a;
        this.setBackground(cz.h);
        this.setForeground(cz.g);
        this.a = a;
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = 18;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 1.0;
        for (int i = 0; i < array2.length; ++i) {
            final A a2 = new A(array2[i]);
            if (i == 0) {
                a2.setFont(bk.a);
            }
            else {
                a2.setFont(bk.c);
            }
            a2.resize(300, 20);
            gridBagLayout.setConstraints(a2, gridBagConstraints);
            this.add(a2);
        }
        final FontMetrics fontMetrics = this.getFontMetrics(bk.b);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.0;
        final Container container;
        (container = new Panel()).setLayout(gridBagLayout);
        for (int j = array.length - 1; j >= 0; --j) {
            int n;
            if ((n = fontMetrics.stringWidth(array[j]) + 20) < 60) {
                n = 60;
            }
            final cr a3 = new cr(array[j], n, 20);
            if (j == 0) {
                this.a = a3;
                final g g = new g(a3);
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(g, gridBagConstraints);
                container.add(g);
            }
            else {
                gridBagLayout.setConstraints(a3, gridBagConstraints);
                container.add(a3);
            }
        }
        gridBagLayout.setConstraints(container, gridBagConstraints);
        this.add(container);
    }
}
