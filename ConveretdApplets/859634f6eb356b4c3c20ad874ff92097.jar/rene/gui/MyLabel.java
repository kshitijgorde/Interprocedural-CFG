// 
// Decompiled by Procyon v0.5.30
// 

package rene.gui;

import java.awt.Container;
import java.awt.Graphics;
import java.awt.Label;

public class MyLabel extends Label
{
    public MyLabel(final String s) {
        super(s);
        if (Global.NormalFont != null) {
            this.setFont(Global.NormalFont);
        }
    }
    
    public MyLabel(final String s, final int n) {
        super(s, n);
        if (Global.NormalFont != null) {
            this.setFont(Global.NormalFont);
        }
    }
    
    public void paint(final Graphics graphics) {
        final Container parent = this.getParent();
        if (parent != null) {
            this.setBackground(parent.getBackground());
        }
        super.paint(graphics);
    }
}
