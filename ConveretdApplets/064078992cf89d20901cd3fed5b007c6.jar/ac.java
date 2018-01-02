import java.awt.Graphics;
import java.awt.Frame;
import java.awt.Window;

// 
// Decompiled by Procyon v0.5.30
// 

public class ac extends Window
{
    ak a;
    
    public void dispose() {
        super.dispose();
        this.a = null;
    }
    
    ac(final Frame frame, final ak a) {
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
