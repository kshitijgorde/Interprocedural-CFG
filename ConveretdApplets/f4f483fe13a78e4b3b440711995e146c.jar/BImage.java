// 
// Decompiled by Procyon v0.5.30
// 

public class BImage
{
    int format;
    int[] pixels;
    int width;
    int height;
    int cacheIndex;
    
    public BImage(final int[] pixels, final int width, final int height, final int format) {
        this.pixels = pixels;
        this.width = width;
        this.height = height;
        this.format = format;
        this.cacheIndex = -1;
    }
}
