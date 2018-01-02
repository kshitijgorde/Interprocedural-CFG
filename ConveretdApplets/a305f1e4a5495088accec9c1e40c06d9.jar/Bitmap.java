import java.awt.image.MemoryImageSource;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

class Bitmap
{
    int[] data;
    int width;
    int height;
    PixelMuncher muncher;
    
    public Bitmap(final Image image, final int width, final int height) {
        this.width = width;
        this.height = height;
        this.data = new int[width * height];
        this.clear();
        this.muncher = new PixelMuncher(image, width, height, this.data);
    }
    
    public Bitmap(final int width, final int height) {
        this.width = width;
        this.height = height;
        this.data = new int[width * height];
        this.clear();
    }
    
    void clear() {
        for (int i = 0; i < this.pixels(); ++i) {
            this.data[i] = -16777216;
        }
    }
    
    public int get(final int n) {
        return (this.data[n] & 0xFF) >> Wiper.wipeBits;
    }
    
    public int get(final int n, final int n2) {
        return this.get(n2 * this.width + n);
    }
    
    public void paste(final Bitmap bitmap, final int n, final int n2) {
        System.arraycopy(bitmap.data, n, this.data, n, n2);
    }
    
    public int pixels() {
        return this.width * this.height;
    }
    
    public int ready() {
        return this.muncher.ready();
    }
    
    public MemoryImageSource prepare() {
        return new MemoryImageSource(this.width, this.height, this.data, 0, this.width);
    }
}
