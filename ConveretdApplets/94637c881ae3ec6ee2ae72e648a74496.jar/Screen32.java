import java.awt.image.PixelGrabber;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class Screen32
{
    public int[] data;
    public int[] ytab;
    public int width;
    public int height;
    public int hwidth;
    public int hheight;
    private int widthheight;
    private int i;
    
    public Screen32(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.widthheight = width * height;
        this.hheight = height >> 1;
        this.hwidth = width >> 1;
        this.data = new int[this.widthheight];
        this.ytab = new int[this.height];
        int n = 0;
        this.i = 0;
        while (this.i < this.height) {
            this.ytab[this.i] = n;
            n += this.width;
            ++this.i;
        }
    }
    
    public final void load(final Image image) {
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.width, this.height, this.data, 0, this.width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
    }
    
    public final void copy(final Screen32 screen32) {
        this.i = this.widthheight;
        while (true) {
            final int i = this.i - 1;
            this.i = i;
            if (i < 0) {
                break;
            }
            this.data[this.i] = screen32.data[this.i];
        }
    }
    
    public final void clear(final int n) {
        this.i = this.widthheight;
        while (true) {
            final int i = this.i - 1;
            this.i = i;
            if (i < 0) {
                break;
            }
            this.data[this.i] = n;
        }
    }
    
    public final int getwidth() {
        return this.width;
    }
    
    public final int getheight() {
        return this.height;
    }
    
    public final int getwidthheight() {
        return this.widthheight;
    }
    
    public final int[] getdata() {
        return this.data;
    }
}
