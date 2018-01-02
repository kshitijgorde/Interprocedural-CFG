import java.awt.Image;
import java.awt.image.PixelGrabber;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

// 
// Decompiled by Procyon v0.5.30
// 

class palMoveListener extends MouseMotionAdapter
{
    public static boolean checked;
    int palx;
    int paly;
    int width;
    int[] pixels;
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (palMoveListener.checked) {
            palMoveListener.checked = false;
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
        palMoveListener.checked = true;
    }
}
