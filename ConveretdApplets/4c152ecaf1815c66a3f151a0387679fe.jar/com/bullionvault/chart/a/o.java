// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.a;

import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.JMenuItem;

final class o extends JMenuItem
{
    private final i a;
    
    o(final i a, final String text) {
        this.a = a;
        this.setText(text);
    }
    
    protected final void init(final String s, final Icon icon) {
        super.init(s, icon);
        this.addActionListener(new g(this));
    }
    
    static i a(final o o) {
        return o.a;
    }
}
