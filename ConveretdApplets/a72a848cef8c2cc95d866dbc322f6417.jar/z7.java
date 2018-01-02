import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class z7 extends Panel
{
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (n >= 32) {
            this.repaint();
            return false;
        }
        return true;
    }
    
    public void paint(final Graphics graphics) {
        if (z1.z58 != null) {
            graphics.drawImage(z1.z58, 0, 0, this);
        }
    }
    
    public z7() {
        this.setBackground(Color.white);
        this.setForeground(Color.black);
    }
}
