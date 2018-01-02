// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.a;

import com.bullionvault.chart.c.j;
import com.bullionvault.chart.e.d;
import com.bullionvault.chart.c.h;
import com.bullionvault.chart.resources.Resources;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class e implements ActionListener
{
    private final i a;
    
    e(final i a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final i a = this.a;
        final JMenuItem menuItem = (JMenuItem)actionEvent.getSource();
        final i i = a;
        final String name = menuItem.getName();
        final String text = menuItem.getText();
        final String s = name;
        final i j = a;
        boolean b;
        if (s.equals(Resources.b("menu.series"))) {
            b = !j.c.a.equals(text);
        }
        else if (s.equals(Resources.b("menu.currency"))) {
            b = !j.c.b.equals(text);
        }
        else if (s.equals(Resources.b("menu.style"))) {
            b = !j.c.d.equals(text);
        }
        else {
            if (!s.equals(Resources.b("menu.period"))) {
                throw new RuntimeException("Unknown Graph option selected [" + text + "] from menu [" + s + "]");
            }
            b = !j.c.e.equals(text);
        }
        if (b) {
            h.e("Parameters have changed - redrawing");
            final j a2 = i.a(menuItem.getName(), menuItem.getText());
            final boolean b2 = null != i.d.c;
            if (a2.b != i.c.b || a2.a != i.c.a) {
                i.d.e();
            }
            else {
                h.e("ChartAppPanel - action() - no change in currency, so no need to restart Real Time reader.");
            }
            final d d = new d(i.d, i, a2.f);
            try {
                d.a("/Full");
            }
            catch (s s2) {
                throw new RuntimeException(Resources.b("error.unable_to_read_data") + s2.getMessage());
            }
            i.d.a(a2.d);
            i.d.a(d, true);
            i.a(a2);
            if (b2) {
                i.d.b(a2.b);
            }
            i.d.f();
            i.d.repaint();
            i.a.a("period", a2.e);
            i.a.a("currency", a2.b);
            i.a.a("style", a2.d);
            i.a.a("series", a2.a);
            i.a.a("version2", "true");
            return;
        }
        h.e("Parameters have not changed - ignoring");
    }
}
