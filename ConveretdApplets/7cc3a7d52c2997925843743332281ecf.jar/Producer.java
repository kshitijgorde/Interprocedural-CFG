import java.awt.image.ColorModel;
import java.awt.image.ImageConsumer;
import java.awt.image.MemoryImageSource;

// 
// Decompiled by Procyon v0.5.30
// 

class Producer extends MemoryImageSource
{
    private ImageConsumer b;
    public static boolean a;
    
    public ImageConsumer a() {
        return this.b;
    }
    
    public Producer(final int n, final int n2, final int[] array, final int n3, final int n4) {
        super(n, n2, array, n3, n4);
    }
    
    public Producer(final int n, final int n2, final ColorModel colorModel, final int[] array, final int n3, final int n4) {
        super(n, n2, colorModel, array, n3, n4);
    }
    
    public Producer(final int n, final int n2, final ColorModel colorModel, final byte[] array, final int n3, final int n4) {
        int a = snowFX.a;
        super(n, n2, colorModel, array, n3, n4);
        if (Producer.a) {
            snowFX.a = ++a;
        }
    }
    
    public synchronized void addConsumer(final ImageConsumer b) {
        super.addConsumer(this.b = b);
    }
}
