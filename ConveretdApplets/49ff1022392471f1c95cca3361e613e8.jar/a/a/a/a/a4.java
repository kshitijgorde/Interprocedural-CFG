// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Dialog;

public class a4 extends Dialog
{
    l a;
    
    a4(final Frame frame, final l a) {
        super(frame);
        this.a = a;
    }
    
    public void paint(final Graphics graphics) {
        this.a.paint(graphics);
    }
    
    public void setUndecorated(final boolean undecorated) {
        super.setUndecorated(undecorated);
    }
    
    public void update(final Graphics graphics) {
    }
}
