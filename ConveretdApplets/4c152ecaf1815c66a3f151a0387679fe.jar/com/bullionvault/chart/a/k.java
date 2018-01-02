// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.a;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

final class k implements ActionListener
{
    private final String a;
    private final i b;
    
    k(final i b, final String a) {
        this.b = b;
        this.a = a;
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        this.b.a(this.a + this.b.d.e.g + "/Full");
    }
}
