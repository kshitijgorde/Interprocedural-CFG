import java.awt.Graphics;
import java.awt.image.ImageFilter;
import java.net.MalformedURLException;
import java.awt.Image;
import java.net.URL;

// 
// Decompiled by Procyon v0.5.30
// 

class HrefButtonArea extends ImageMapArea
{
    URL anchor;
    Image upImage;
    Image downImage;
    boolean pressed;
    int border;
    
    public void handleArg(final String s) {
        try {
            this.anchor = new URL(super.parent.getDocumentBase(), s);
        }
        catch (MalformedURLException ex) {
            this.anchor = null;
        }
        if (this.border * 2 > super.W || this.border * 2 > super.H) {
            this.border = Math.min(super.W, super.H) / 2;
        }
    }
    
    public void makeImages() {
        this.upImage = super.parent.getHighlight(super.X, super.Y, super.W, super.H, new ButtonFilter(false, super.parent.hlpercent, this.border, super.W, super.H));
        this.downImage = super.parent.getHighlight(super.X, super.Y, super.W, super.H, new ButtonFilter(true, super.parent.hlpercent, this.border, super.W, super.H));
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if (image == (this.pressed ? this.downImage : this.upImage)) {
            return super.parent.imageUpdate(image, n, n2 + super.X, n3 + super.Y, n4, n5);
        }
        return image == this.downImage || image == this.upImage;
    }
    
    public void highlight(final Graphics graphics, final boolean b) {
        if (b) {
            this.setHighlight(this.pressed ? this.downImage : this.upImage);
        }
        super.highlight(graphics, b);
        this.showStatus((b && this.anchor != null) ? ("Go To " + this.anchor.toExternalForm()) : null);
    }
    
    public void press() {
        super.parent.repaint();
        this.pressed = true;
    }
    
    public void lift(final int n, final int n2) {
        this.pressed = false;
        super.parent.repaint();
        if (this.inside(n, n2) && this.anchor != null) {
            this.showDocument(this.anchor);
        }
    }
    
    HrefButtonArea() {
        this.pressed = false;
        this.border = 5;
    }
}
