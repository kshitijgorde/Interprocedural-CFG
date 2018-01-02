import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class palListener extends MouseAdapter
{
    public static boolean checked;
    int palx;
    int paly;
    int width;
    int[] pixels;
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (palListener.checked) {
            palListener.checked = false;
            this.checkPixel();
        }
        this.palx = mouseEvent.getX();
        this.paly = mouseEvent.getY();
        ColoringBook.col = new Color(this.pixels[this.paly * this.width + this.palx]);
        ColoringBook.con.refresh();
    }
    
    public void checkPixel() {
        final Image palPic = ColoringBook.palPic;
        try {
            final PixelGrabber pixelGrabber = new PixelGrabber(palPic, 0, 0, -1, -1, true);
            if (pixelGrabber.grabPixels()) {
                this.width = pixelGrabber.getWidth();
                this.pixels = (int[])pixelGrabber.getPixels();
            }
        }
        catch (InterruptedException ex) {}
    }
    
    static {
        palListener.checked = true;
    }
}
