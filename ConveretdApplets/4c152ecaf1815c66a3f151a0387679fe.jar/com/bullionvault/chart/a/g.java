// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.a;

import com.bullionvault.chart.c.h;
import javax.swing.JButton;
import com.bullionvault.chart.g.b;
import com.bullionvault.chart.run.ChartApp;
import com.bullionvault.chart.c.e;
import javax.swing.JLabel;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Component;
import javax.swing.SwingUtilities;
import java.awt.Frame;
import com.bullionvault.chart.resources.Resources;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class g implements ActionListener
{
    private final o a;
    
    g(final o a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (this.a.getText().equals(Resources.b("menu.help.about"))) {
            final w w;
            (w = new w((Frame)SwingUtilities.getAncestorOfClass((i.g == null) ? (i.g = i.d("java.awt.Frame")) : i.g, o.a(this.a)))).setTitle("About Galmarley");
            w.getContentPane().setLayout(new GridBagLayout());
            e.a(w.getContentPane(), new JLabel(Resources.b("dialog.about.title")), 0, 0, 1, 1, 0, 10, 0.0, 0.0, 10, 10, 10, 10);
            e.a(w.getContentPane(), new JLabel(Resources.b("dialog.about.version") + ChartApp.a), 0, 1, 1, 1, 0, 10, 0.0, 0.0, 10, 10, 10, 10);
            final JLabel label;
            (label = new JLabel(Resources.b("dialog.about.copyright"), 0)).setVerticalAlignment(0);
            e.a(w.getContentPane(), label, 0, 2, 1, 1, 0, 10, 0.0, 0.0, 10, 10, 10, 10);
            e.a(w.getContentPane(), new JLabel("www.galmarley.com"), 0, 3, 1, 1, 0, 10, 0.0, 0.0, 10, 10, 10, 10);
            final String c;
            if (!(c = b.a.c()).toLowerCase().equals("chart@bullionvault.com")) {
                String s;
                if (c == null || c.equals("")) {
                    s = Resources.b("dialog.about.unregistered");
                }
                else {
                    s = Resources.b("dialog.about.registered_to") + b.a.c();
                }
                e.a(w.getContentPane(), new JLabel(s), 0, 4, 1, 1, 0, 10, 0.0, 0.0, 10, 10, 10, 10);
            }
            (w.a = new JButton(Resources.b("dialog.about.button.ok"))).addActionListener(new u(w));
            e.a(w.getContentPane(), w.a, 0, 5, 1, 1, 0, 10, 0.0, 0.0, 10, 10, 10, 10);
            w.pack();
            w.setVisible(true);
            return;
        }
        if (this.a.getText().equals(Resources.b("menu.help.topics"))) {
            final i a = o.a(this.a);
            h.e("Showing Help Pages...");
            if (!a.b.equals("BullionVault")) {
                a.a(Resources.b("chart_panel.url.galmarley_help"));
                return;
            }
            a.a(Resources.b("chart_panel.url.bullionvault_help"));
        }
        else {
            if (this.a.getText().equals(Resources.b("menu.help.bullionvault"))) {
                o.a(this.a).a(Resources.b("chart_panel.url.bullionvault"));
                return;
            }
            if (this.a.getText().equals(Resources.b("menu.help.galmarley"))) {
                o.a(this.a).a(Resources.b("chart_panel.url.galmarley"));
            }
        }
    }
}
