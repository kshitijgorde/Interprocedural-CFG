import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Dialog;

// 
// Decompiled by Procyon v0.5.30
// 

public class ai extends Dialog
{
    ak a;
    
    public void dispose() {
        super.dispose();
        this.a = null;
    }
    
    ai(final Frame frame, final ak a) {
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
