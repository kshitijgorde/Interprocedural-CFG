// 
// Decompiled by Procyon v0.5.30
// 

package jagoclient.gui;

import java.awt.Container;
import java.awt.Graphics;
import jagoclient.Global;
import java.awt.Label;

public class MyLabel extends Label
{
    public MyLabel(final String s) {
        super(s);
        this.setFont(Global.SansSerif);
    }
    
    public void paint(final Graphics graphics) {
        final Container parent = this.getParent();
        if (parent != null) {
            this.setBackground(parent.getBackground());
        }
        super.paint(graphics);
    }
}
