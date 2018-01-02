import java.awt.image.PixelGrabber;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class Screen32
{
    public int[] jm21;
    public int[] jm22;
    public int jm24;
    public int jm25;
    public int jm26;
    public int jm27;
    public int jm102;
    
    public Screen32(final int jm24, final int jm25) {
        this.jm24 = jm24;
        this.jm25 = jm25;
        this.jm102 = jm24 * jm25;
        this.jm27 = jm25 >> 1;
        this.jm26 = jm24 >> 1;
        this.jm21 = new int[this.jm102];
        this.jm22 = new int[this.jm25];
        int n = 0;
        for (int i = 0; i < this.jm25; ++i) {
            this.jm22[i] = n;
            n += this.jm24;
        }
    }
    
    public final int jm96() {
        return this.jm25;
    }
    
    public final int[] jm97() {
        return this.jm21;
    }
    
    public final void jm98(final Image image) {
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.jm24, this.jm25, this.jm21, 0, this.jm24);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
    }
    
    public final void jm99(final Screen32 screen32) {
        for (int i = 0; i < this.jm102; ++i) {
            this.jm21[i] = screen32.jm21[i];
        }
    }
    
    public final void jm100(final int n) {
        for (int i = 0; i < this.jm102; ++i) {
            this.jm21[i] = n;
        }
    }
    
    public final int jm101() {
        return this.jm24;
    }
}
