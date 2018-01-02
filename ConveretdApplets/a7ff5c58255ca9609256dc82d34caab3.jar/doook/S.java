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

public class S extends aA
{
    private P a;
    private boolean c;
    private ax d;
    private String g;
    
    public void setVisible(final boolean visible) {
        if (visible) {
            this.pack();
            this.f();
        }
        super.setVisible(visible);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 201: {
                if (!this.c) {
                    this.dispose();
                    if (this.a != null) {
                        this.a.a(this, null);
                    }
                }
                return true;
            }
            case 1001: {
                if (event.target instanceof ax) {
                    final String c = ((ax)event.target).c();
                    this.g = c;
                    if (!this.c) {
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
                if (event.key == 10 || event.key == bs.c) {
                    this.d.l();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public S(final Frame frame, final String s, final Throwable t, final as as) {
        this(frame, ar.b("Error"), new String[] { ar.b("OK") }, new String[] { s, t.toString() }, null, as);
    }
    
    public S(final Frame frame, final String s, final String s2, final as as) {
        this(frame, s, new String[] { ar.b("OK") }, new String[] { s2 }, null, as);
    }
    
    public S(final Frame frame, final String s, final String[] array, final as as) {
        this(frame, s, new String[] { ar.b("OK") }, array, null, as);
    }
    
    public S(final Frame frame, final String s, final String[] array, final String[] array2, final P a, final as as) {
        super(frame, s, true);
        this.c = false;
        final aZ az = (as == null) ? aZ.c : as.b;
        this.setBackground(az.g);
        this.setForeground(az.f);
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
            final R r = new R(array2[i]);
            if (i == 0) {
                r.setFont(ay.d);
            }
            else {
                r.setFont(ay.e);
            }
            r.resize(300, 20);
            gridBagLayout.setConstraints(r, gridBagConstraints);
            this.add(r);
        }
        final FontMetrics fontMetrics = this.getFontMetrics(ay.a);
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
            final ax d = new ax(array[j], n, 20);
            if (j == 0) {
                this.d = d;
                final au au = new au(d);
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(au, gridBagConstraints);
                panel.add(au);
            }
            else {
                gridBagLayout.setConstraints(d, gridBagConstraints);
                panel.add(d);
            }
        }
        gridBagLayout.setConstraints(panel, gridBagConstraints);
        this.add(panel);
    }
}
