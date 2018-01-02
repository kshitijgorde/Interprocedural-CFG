// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.run;

import java.awt.event.WindowListener;
import java.awt.Window;
import com.bullionvault.chart.a.v;
import javax.swing.JFrame;

final class a implements Runnable
{
    private final JFrame a;
    
    a(final JFrame a) {
        this.a = a;
    }
    
    public final void run() {
        this.a.addWindowListener(new v(this.a, true));
        this.a.setVisible(true);
    }
}
