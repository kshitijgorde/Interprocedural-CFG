// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Window;

public class q extends Window
{
    l a;
    
    q(final Frame frame, final l a) {
        super(frame);
        this.a = a;
    }
    
    public void paint(final Graphics graphics) {
        this.a.paint(graphics);
    }
    
    public void repaint() {
    }
    
    public void update(final Graphics graphics) {
    }
}