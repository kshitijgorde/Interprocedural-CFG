// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.a;

import java.awt.event.WindowListener;
import java.awt.Window;
import java.awt.Frame;
import javax.swing.JButton;
import javax.swing.JDialog;

public final class w extends JDialog
{
    JButton a;
    
    public w(final Frame frame) {
        super(frame, true);
        this.a = null;
        this.addWindowListener(new v(this, false));
    }
}
