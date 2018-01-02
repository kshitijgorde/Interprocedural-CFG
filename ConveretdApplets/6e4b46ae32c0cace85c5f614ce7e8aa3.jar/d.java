import java.awt.image.ColorModel;
import java.awt.image.ImageConsumer;
import java.awt.image.MemoryImageSource;

// 
// Decompiled by Procyon v0.5.30
// 

public class d extends MemoryImageSource
{
    public ImageConsumer a;
    
    public d(final int n, final int n2, final ColorModel colorModel, final int[] array, final int n3, final int n4) {
        super(n, n2, colorModel, array, n3, n4);
    }
    
    public synchronized void addConsumer(final ImageConsumer a) {
        super.addConsumer(this.a = a);
    }
}
