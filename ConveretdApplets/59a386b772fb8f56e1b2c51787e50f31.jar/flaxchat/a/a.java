// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.a;

import java.awt.Frame;
import java.awt.Component;
import flaxchat.e.g;
import java.awt.Graphics;
import java.awt.Dialog;

final class a extends Dialog
{
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        g.a(graphics, this);
    }
    
    a(final Frame frame) {
        super(frame);
    }
}
