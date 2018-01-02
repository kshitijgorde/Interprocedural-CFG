// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.a;

import com.bullionvault.chart.c.h;
import java.awt.event.WindowEvent;
import java.awt.Window;
import java.awt.event.WindowAdapter;

public final class v extends WindowAdapter
{
    private Window a;
    private boolean b;
    
    public v(final Window a, final boolean b) {
        this.b = false;
        this.a = a;
        this.b = b;
    }
    
    public final void windowClosing(final WindowEvent windowEvent) {
        h.e("WindowCloser - Window closed.");
        this.a.setVisible(false);
        this.a.dispose();
        if (this.b) {
            System.exit(0);
        }
    }
}
