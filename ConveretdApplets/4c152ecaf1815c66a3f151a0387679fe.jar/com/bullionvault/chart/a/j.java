// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.a;

import com.bullionvault.chart.c.k;
import com.bullionvault.chart.c.h;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class j implements ActionListener
{
    private final i a;
    
    j(final i a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        h.g("Realtime menu item clicked.");
        if (this.a.d.c != null && k.c(this.a.d.c)) {
            this.a.d.e();
            return;
        }
        this.a.d.b(this.a.c.b);
    }
}
