// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.a;

import java.awt.Component;
import javax.swing.SwingUtilities;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class f implements ActionListener
{
    private final i a;
    
    f(final i a) {
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        final i a;
        if ((a = this.a).e == null) {
            (a.e = new q((Frame)SwingUtilities.getAncestorOfClass((i.g == null) ? (i.g = i.d("java.awt.Frame")) : i.g, a))).a();
        }
        a.e = null;
        a.b();
    }
}
