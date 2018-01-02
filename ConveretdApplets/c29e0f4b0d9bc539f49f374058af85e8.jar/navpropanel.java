import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

public final class navpropanel extends Panel
{
    private Image bgimage;
    private boolean tile;
    
    navpropanel() {
        this.bgimage = null;
        this.tile = false;
    }
    
    navpropanel(final Image bgimage, final boolean tile) {
        this.bgimage = null;
        this.tile = false;
        if (bgimage != null) {
            this.prepareImage(this.bgimage = bgimage, this);
        }
        this.tile = tile;
    }
    
    public void paint(final Graphics graphics) {
        if (this.bgimage != null && this.bgimage.getHeight(null) > 0 && this.bgimage.getWidth(null) > 0) {
            if (this.tile) {
                for (int i = 0; i < this.size().height; i += this.bgimage.getHeight(null)) {
                    for (int j = 0; j < this.size().width; j += this.bgimage.getWidth(null)) {
                        graphics.drawImage(this.bgimage, j, i, this);
                    }
                }
            }
            else {
                graphics.drawImage(this.bgimage, 0, 0, this);
            }
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.repaint();
            return false;
        }
        return true;
    }
}
