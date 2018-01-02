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

public class E extends N
{
    private G a;
    private boolean a;
    private al e;
    private String m;
    
    public void setVisible(final boolean visible) {
        if (visible) {
            this.pack();
            this.c();
        }
        super.setVisible(visible);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                if (!this.a) {
                    this.dispose();
                    if (this.a != null) {
                        this.a.a(this, null);
                    }
                }
                return true;
            }
            case 1001: {
                if (event.target instanceof al) {
                    final String c = ((al)event.target).c();
                    this.m = c;
                    if (!this.a) {
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
                if (event.key == 10 || event.key == F.e) {
                    this.e.e();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public E(final Frame frame, final String s, final Throwable t, final be be) {
        this(frame, aG.a("Error"), new String[] { aG.a("OK") }, new String[] { s, t.toString() }, null, be);
    }
    
    public E(final Frame frame, final String s, final String s2, final be be) {
        this(frame, s, new String[] { aG.a("OK") }, new String[] { s2 }, null, be);
    }
    
    public E(final Frame frame, final String s, final String[] array, final be be) {
        this(frame, s, new String[] { aG.a("OK") }, array, null, be);
    }
    
    public E(final Frame frame, final String s, final String[] array, final String[] array2, final G a, final be be) {
        super(frame, s, true);
        this.a = false;
        final ap ap = (be == null) ? doook.ap.b : be.c;
        this.setBackground(ap.k);
        this.setForeground(ap.j);
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
            final D d = new D(array2[i]);
            if (i == 0) {
                d.setFont(aK.e);
            }
            else {
                d.setFont(aK.d);
            }
            d.resize(300, 20);
            gridBagLayout.setConstraints(d, gridBagConstraints);
            this.add(d);
        }
        final FontMetrics fontMetrics = this.getFontMetrics(aK.f);
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
            final al e = new al(array[j], n, 20);
            if (j == 0) {
                this.e = e;
                final P p6 = new P(e);
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(p6, gridBagConstraints);
                panel.add(p6);
            }
            else {
                gridBagLayout.setConstraints(e, gridBagConstraints);
                panel.add(e);
            }
        }
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
    }
}
