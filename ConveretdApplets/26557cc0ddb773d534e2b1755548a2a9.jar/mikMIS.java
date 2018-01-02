import java.awt.image.ColorModel;
import java.awt.image.ImageConsumer;
import java.awt.image.MemoryImageSource;

// 
// Decompiled by Procyon v0.5.30
// 

class mikMIS extends MemoryImageSource
{
    private ImageConsumer lastConsumer;
    
    public mikMIS(final int n, final int n2, final ColorModel colorModel, final byte[] array, final int n3, final int n4) {
        super(n, n2, colorModel, array, n3, n4);
    }
    
    public mikMIS(final int n, final int n2, final ColorModel colorModel, final int[] array, final int n3, final int n4) {
        super(n, n2, colorModel, array, n3, n4);
    }
    
    public mikMIS(final int n, final int n2, final int[] array, final int n3, final int n4) {
        super(n, n2, array, n3, n4);
    }
    
    public synchronized void addConsumer(final ImageConsumer lastConsumer) {
        super.addConsumer(this.lastConsumer = lastConsumer);
    }
    
    public ImageConsumer getConsumer() {
        return this.lastConsumer;
    }
}
