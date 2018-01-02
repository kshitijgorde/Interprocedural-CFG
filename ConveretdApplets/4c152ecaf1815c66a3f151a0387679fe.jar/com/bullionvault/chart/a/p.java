// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.a;

import java.awt.Event;
import com.bullionvault.chart.resources.Resources;
import java.awt.Component;
import java.awt.Container;
import com.bullionvault.chart.c.e;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.event.WindowListener;
import java.awt.Window;
import java.awt.Frame;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JDialog;

public final class p extends JDialog
{
    private JButton a;
    private String b;
    private String c;
    
    public p(final JFrame frame, final String b, final String c) {
        super(frame, true);
        this.a = null;
        this.b = "Message Dialog";
        this.c = "";
        this.b = b;
        this.c = c;
        this.addWindowListener(new v(this, false));
        (this = this).removeAll();
        this.setTitle(this.b);
        this.setLayout(new GridBagLayout());
        final JLabel label;
        (label = new JLabel(this.c, 0)).setHorizontalAlignment(0);
        e.a(this, label, 0, 2, 1, 1, 0, 10, 0.0, 0.0, 10, 10, 10, 10);
        e.a(this, this.a = new JButton(Resources.b("dialog.message.button.ok")), 0, 5, 1, 1, 0, 10, 0.0, 0.0, 10, 10, 10, 10);
        this.pack();
    }
    
    public final boolean action(final Event event, final Object o) {
        if (event.target instanceof JButton) {
            if (event.target != this.a) {
                throw new RuntimeException("Unknown Button pressed " + event.target);
            }
            (this = this).setVisible(false);
            this.dispose();
        }
        return true;
    }
}
