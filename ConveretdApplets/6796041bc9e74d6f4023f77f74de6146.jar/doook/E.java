// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.FontMetrics;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Frame;
import java.awt.Event;

public class E extends p
{
    private ad a;
    private boolean m;
    private aS c;
    private String e;
    
    public void setVisible(final boolean visible) {
        if (visible) {
            this.pack();
            this.d();
        }
        super.setVisible(visible);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                if (!this.m) {
                    this.dispose();
                    if (this.a != null) {
                        this.a.a(this, null);
                    }
                }
                return true;
            }
            case 1001: {
                if (event.target instanceof aS) {
                    final String c = ((aS)event.target).c();
                    this.e = c;
                    if (!this.m) {
                        this.dispose();
                    }
                    if (this.a != null) {
                        this.a.a(this, c);
                    }
                    return true;
                }
                break;
            }
            case 401: {
                if (event.key == 10 || event.key == f.g) {
                    this.c.s();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public E(final Frame frame, final String s, final Throwable t, final t t2) {
        this(frame, ao.e("Error"), new String[] { ao.e("OK") }, new String[] { s, t.toString() }, null, t2);
    }
    
    public E(final Frame frame, final String s, final String s2, final t t) {
        this(frame, s, new String[] { ao.e("OK") }, new String[] { s2 }, null, t);
    }
    
    public E(final Frame frame, final String s, final String[] array, final t t) {
        this(frame, s, new String[] { ao.e("OK") }, array, null, t);
    }
    
    public E(final Frame frame, final String s, final String[] array, final String[] array2, final ad a, final t t) {
        super(frame, s, true);
        this.m = false;
        final bA ba = (t == null) ? bA.b : t.a;
        this.setBackground(ba.g);
        this.setForeground(ba.f);
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
            final c c = new c(array2[i]);
            if (i == 0) {
                c.setFont(bL.e);
            }
            else {
                c.setFont(bL.f);
            }
            c.resize(300, 20);
            gridBagLayout.setConstraints(c, gridBagConstraints);
            this.add(c);
        }
        final FontMetrics fontMetrics = this.getFontMetrics(bL.a);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.0;
        final Panel panel = new Panel();
        panel.setLayout(gridBagLayout);
        for (int j = array.length - 1; j >= 0; --j) {
            int n = fontMetrics.stringWidth(array[j]) + 20;
            if (n < 60) {
                n = 60;
            }
            final aS c2 = new aS(array[j], n, 20);
            if (j == 0) {
                this.c = c2;
                final aQ aq = new aQ(c2);
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(aq, gridBagConstraints);
                panel.add(aq);
            }
            else {
                gridBagLayout.setConstraints(c2, gridBagConstraints);
                panel.add(c2);
            }
        }
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
    }
}
