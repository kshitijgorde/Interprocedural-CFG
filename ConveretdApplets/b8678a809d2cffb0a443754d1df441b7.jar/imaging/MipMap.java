// 
// Decompiled by Procyon v0.5.30
// 

package imaging;

import imaging.filters.ScaleFilter;
import java.awt.image.BufferedImage;

public class MipMap
{
    private static int minSize;
    
    public static BufferedImage[] getMipMap(final BufferedImage src) {
        MipMap.minSize = Math.min(src.getWidth(), src.getHeight());
        int currentSize = MipMap.minSize;
        int count = 0;
        while ((currentSize >>= 1) > 1) {
            ++count;
        }
        final BufferedImage[] buffer = new BufferedImage[count];
        BufferedImage currentImage = src;
        for (int i = 0; i < count; ++i) {
            buffer[i] = currentImage;
            currentImage = new ScaleFilter().filter(currentImage, 0.5f);
        }
        return buffer;
    }
}
