import java.awt.image.ColorModel;
import java.awt.image.ImageConsumer;
import java.awt.image.MemoryImageSource;

// 
// Decompiled by Procyon v0.5.30
// 

class TF_Cube2 extends MemoryImageSource
{
    private ImageConsumer FinalConsumer;
    
    public TF_Cube2(final int i, final int j, final ColorModel colormodel, final int[] ai, final int k, final int l) {
        super(i, j, colormodel, ai, k, l);
    }
    
    public ImageConsumer getConsumer() {
        return this.FinalConsumer;
    }
    
    public synchronized void addConsumer(final ImageConsumer consumer) {
        super.addConsumer(this.FinalConsumer = consumer);
    }
}
