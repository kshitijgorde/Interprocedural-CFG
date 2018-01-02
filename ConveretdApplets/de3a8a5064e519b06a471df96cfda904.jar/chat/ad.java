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

public final class ad extends C
{
    private av a;
    private i a;
    
    public final void setVisible(final boolean visible) {
        if (visible) {
            this.pack();
            this.a();
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
                if (event.target instanceof i) {
                    final String a = ((i)event.target).a;
                    this.dispose();
                    if (this.a != null) {
                        this.a.a(this, a);
                    }
                    return true;
                }
                break;
            }
            case 401: {
                if (event.key == 10 || event.key == aZ.a) {
                    this.a.c();
                    return true;
                }
                break;
            }
        }
        return super.handleEvent(event);
    }
    
    public ad(final String s, final Throwable t, final bh bh) {
        this(null, ak.a(4), new String[] { ak.a(2) }, new String[] { s, t.toString() }, null, bh);
    }
    
    public ad(final Frame frame, final String s, final String s2, final bh bh) {
        this(frame, s, new String[] { ak.a(2) }, new String[] { s2 }, null, bh);
    }
    
    public ad(final Frame frame, final String s, final String s2, final bh bh, final Color foreground, final Color background) {
        this(frame, s, new String[] { ak.a(2) }, new String[] { s2 }, null, bh);
        if (background != null) {
            this.setBackground(background);
        }
        if (foreground != null) {
            this.setForeground(foreground);
        }
    }
    
    public ad(final Frame frame, final String s, final String[] array, final bh bh) {
        this(frame, s, new String[] { ak.a(2) }, array, null, bh);
    }
    
    public ad(final Frame frame, final String s, final String[] array, final String[] array2, final av a, final bh bh) {
        super(frame, s, true);
        final bm bm = (bh == null) ? chat.bm.a : bh.a;
        this.setBackground(bm.h);
        this.setForeground(bm.g);
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
            final t t = new t(array2[i]);
            if (i == 0) {
                t.setFont(ay.a);
            }
            else {
                t.setFont(ay.c);
            }
            t.resize(300, 20);
            gridBagLayout.setConstraints(t, gridBagConstraints);
            this.add(t);
        }
        final FontMetrics fontMetrics = this.getFontMetrics(ay.b);
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
            final i a2 = new i(array[j], n, 20);
            if (j == 0) {
                this.a = a2;
                final f f = new f(a2);
                gridBagConstraints.gridwidth = 0;
                gridBagLayout.setConstraints(f, gridBagConstraints);
                container.add(f);
            }
            else {
                gridBagLayout.setConstraints(a2, gridBagConstraints);
                container.add(a2);
            }
        }
        gridBagLayout.setConstraints(container, gridBagConstraints);
        this.add(container);
    }
}
