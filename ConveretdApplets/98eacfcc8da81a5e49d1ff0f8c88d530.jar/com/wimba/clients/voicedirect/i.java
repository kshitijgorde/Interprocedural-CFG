// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.voicedirect;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.ListCellRenderer;
import VT_6_1_0_11.aT;

final class i extends aT implements ListCellRenderer
{
    private ImageIcon a;
    private final k b;
    
    i(final k b) {
        this.b = b;
    }
    
    public final Component getListCellRendererComponent(final JList list, final Object o, final int n, final boolean b, final boolean b2) {
        final m m = (m)o;
        this.setText(m.a());
        this.setToolTipText(m.a());
        if (m.c()) {
            this.a = k.a(this.b);
        }
        else if (m.b()) {
            this.a = k.b(this.b);
        }
        else {
            this.a = k.c(this.b);
        }
        return this;
    }
    
    public final void paint(final Graphics graphics) {
        super.paint(graphics);
        graphics.drawImage(this.a.getImage(), this.getSize().width - this.a.getIconWidth(), 0, this);
    }
}
