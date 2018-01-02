// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.g;

import java.awt.Frame;
import java.awt.Component;
import flaxchat.a.h;
import java.awt.Graphics;
import java.awt.Dialog;

final class a extends Dialog
{
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        h.a(graphics, this);
    }
    
    a(final Frame frame) {
        super(frame);
    }
}
