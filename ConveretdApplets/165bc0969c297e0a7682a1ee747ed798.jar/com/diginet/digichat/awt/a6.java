// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.FontMetrics;
import java.awt.Panel;
import java.awt.Component;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import com.diginet.digichat.common.a2;
import com.esial.util.c;
import com.diginet.digichat.client.i;
import java.awt.Frame;
import com.diginet.digichat.util.dx;
import java.awt.Event;
import com.diginet.digichat.util.ae;

public class a6 extends ag
{
    private ae a;
    private boolean b;
    private r c;
    private String d;
    
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
                if (!this.b) {
                    this.dispose();
                    if (this.a != null) {
                        this.a.a(this, null);
                    }
                }
                return true;
            }
            case 1001: {
                if (event.target instanceof r) {
                    final String d = ((r)event.target).d();
                    this.d = d;
                    if (!this.b) {
                        this.dispose();
                    }
                    if (this.a != null) {
                        this.a.a(this, d);
                    }
                    return true;
                }
                break;
            }
            case 401: {
                if (event.key == 10 || event.key == dx.a) {
                    this.c.e();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public a6(final Frame frame, final String s, final Throwable t, final i i) {
        this(frame, com.esial.util.c.a("Error"), new String[] { com.esial.util.c.a("OK") }, new String[] { s, t.toString() }, null, i);
    }
    
    public a6(final Frame frame, final String s, final String s2, final i i) {
        this(frame, s, new String[] { com.esial.util.c.a("OK") }, new String[] { s2 }, null, i);
    }
    
    public a6(final Frame frame, final String s, final String[] array, final i i) {
        this(frame, s, new String[] { com.esial.util.c.a("OK") }, array, null, i);
    }
    
    public a6(final Frame frame, final String s, final String[] array, final String[] array2, final ae a, final i i) {
        super(frame, s, true);
        this.b = false;
        final a2 a2 = (i != null) ? i.cc : com.diginet.digichat.common.a2.a;
        this.setBackground(a2.j);
        this.setForeground(a2.i);
        this.a = a;
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout gridBagLayout = new GridBagLayout();
        this.setResizable(false);
        this.setLayout(gridBagLayout);
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        gridBagConstraints.anchor = 18;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.weightx = 1.0;
        for (int j = 0; j < array2.length; ++j) {
            final aw aw = new aw(array2[j]);
            if (j == 0) {
                aw.setFont(dw.a);
            }
            else {
                aw.setFont(dw.c);
            }
            aw.resize(300, 20);
            gridBagLayout.setConstraints(aw, gridBagConstraints);
            this.add(aw);
        }
        final FontMetrics fontMetrics = this.getFontMetrics(dw.b);
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridheight = 0;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.weightx = 0.0;
        final Panel panel = new Panel();
        panel.setLayout(gridBagLayout);
        for (int k = array.length - 1; k >= 0; --k) {
            int n = fontMetrics.stringWidth(array[k]) + 20;
            if (n < 60) {
                n = 60;
            }
            final r c = new r(array[k], n, 20);
            if (k == 0) {
                this.c = c;
                final u u = new u(c);
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(u, gridBagConstraints);
                panel.add(u);
            }
            else {
                gridBagLayout.setConstraints(c, gridBagConstraints);
                panel.add(c);
            }
        }
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
    }
}
