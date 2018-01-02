// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.a;

import java.awt.event.ActionEvent;
import javax.swing.JCheckBoxMenuItem;
import java.awt.event.ActionListener;

final class l implements ActionListener
{
    private final JCheckBoxMenuItem a;
    private final i b;
    
    l(final i b, final JCheckBoxMenuItem a) {
        this.b = b;
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.b.d.a.a(!this.b.d.a.b());
        this.a.setState(this.b.d.a.b());
        this.b.repaint();
    }
}
