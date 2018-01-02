// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.a;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class u implements ActionListener
{
    private final w a;
    
    u(final w a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final w a;
        (a = this.a).setVisible(false);
        a.dispose();
    }
}
