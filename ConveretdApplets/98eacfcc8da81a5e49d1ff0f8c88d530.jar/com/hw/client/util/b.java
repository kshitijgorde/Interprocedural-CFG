// 
// Decompiled by Procyon v0.5.30
// 

package com.hw.client.util;

import java.awt.Component;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import VT_6_1_0_11.bj;

final class b implements Runnable
{
    private int a;
    private final AbstractApplet b;
    
    b(final AbstractApplet b, final int a) {
        this.b = b;
        this.a = a;
    }
    
    public final void run() {
        if (this.a == 1) {
            final AbstractApplet b = this.b;
            final String lookAndFeel = "javax.swing.plaf.metal.MetalLookAndFeel";
            if (!bj.b()) {
                try {
                    UIManager.setLookAndFeel(lookAndFeel);
                    SwingUtilities.updateComponentTreeUI(b);
                }
                catch (Exception ex) {
                    System.out.println("Failed loading L&F: " + lookAndFeel);
                    System.out.println(ex);
                }
            }
            this.b.b();
            return;
        }
        if (this.a != 2 && this.a != 3) {
            if (this.a == 4) {
                this.b.c();
                try {
                    if (this.b.getComponentCount() != 0) {
                        this.b.removeAll();
                    }
                    return;
                }
                catch (Exception ex2) {
                    return;
                }
            }
            com.hw.client.util.a.b("unhandled mode, mode => " + this.a);
        }
    }
}
