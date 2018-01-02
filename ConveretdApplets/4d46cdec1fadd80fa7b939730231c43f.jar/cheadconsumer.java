import java.util.Hashtable;
import java.awt.image.ColorModel;
import java.awt.image.ImageConsumer;

// 
// Decompiled by Procyon v0.5.30
// 

public class cheadconsumer implements ImageConsumer
{
    javahead m_pjavahead;
    
    public void setOwner(final javahead pjavahead) {
        this.m_pjavahead = pjavahead;
    }
    
    public void setHints(final int n) {
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final byte[] array, final int n5, final int n6) {
    }
    
    public void setPixels(final int n, final int n2, final int n3, final int n4, final ColorModel colorModel, final int[] array, final int n5, final int n6) {
    }
    
    public void setDimensions(final int n, final int n2) {
    }
    
    public void setProperties(final Hashtable hashtable) {
    }
    
    public void imageComplete(final int n) {
        if (n == 3) {
            this.m_pjavahead.xxx();
        }
    }
    
    public void setColorModel(final ColorModel colorModel) {
    }
}
