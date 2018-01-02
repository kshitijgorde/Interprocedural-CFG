import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Image;
import java.awt.Frame;

// 
// Decompiled by Procyon v0.5.30
// 

class z6 extends Frame
{
    private Image z0;
    
    public boolean handleEvent(final Event event) {
        if (event.id == 201) {
            this.dispose();
            return true;
        }
        return super.handleEvent(event);
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n >= 32) {
            this.repaint();
            this.resize(this.z0.getWidth(null), this.z0.getHeight(null));
            return false;
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        final int width = this.z0.getWidth(this);
        final int height = this.z0.getHeight(this);
        graphics.drawImage(this.z0, 0, 0, this);
        if (width > 200) {
            this.resize(width, height);
        }
    }
    
    public z6(final String s) {
        this.setTitle("Enlargement");
        this.z0 = Toolkit.getDefaultToolkit().getImage(s);
    }
    
    public z6(final Image z0) {
        this.setTitle("Enlargement");
        this.z0 = z0;
    }
}
