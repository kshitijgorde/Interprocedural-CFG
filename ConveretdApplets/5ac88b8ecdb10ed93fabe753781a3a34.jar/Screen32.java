import java.awt.image.PixelGrabber;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class Screen32
{
    public int[] z0;
    public int z1;
    public int z2;
    public int z3;
    public int z4;
    public int[] z5;
    
    public final void z0(final int n) {
        for (int i = 0; i < this.z4 * this.z1; ++i) {
            this.z0[i] = n;
        }
    }
    
    public final void z0(final Screen32 screen32) {
        for (int i = 0; i < this.z1; ++i) {
            for (int j = 0; j < this.z4; ++j) {
                this.z0[j + this.z5[i]] = screen32.z0[j + screen32.z5[i]];
            }
        }
    }
    
    public final int[] z0() {
        return this.z0;
    }
    
    public final int z1() {
        return this.z1;
    }
    
    public final int z2() {
        return this.z4;
    }
    
    public final void z0(final Image image) {
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.z4, this.z1, this.z0, 0, this.z4);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
    }
    
    public Screen32(final int z4, final int z5) {
        this.z4 = z4;
        this.z1 = z5;
        this.z2 = z5 >> 1;
        this.z3 = z4 >> 1;
        this.z0 = new int[this.z4 * this.z1];
        this.z5 = new int[this.z1];
        int n = 0;
        for (int i = 0; i < this.z1; ++i) {
            this.z5[i] = n;
            n += this.z4;
        }
    }
}
