import java.util.Hashtable;
import java.awt.image.ColorModel;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageProducer;

// 
// Decompiled by Procyon v0.5.30
// 

public class m implements ImageProducer
{
    private ImageConsumer a;
    private int b;
    private int c;
    private ColorModel d;
    private int[] e;
    private int f;
    private int g;
    
    public m(final int b, final int c, final ColorModel d, final int[] e) {
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = 30;
        this.g = 2;
    }
    
    public void a() {
        if (this.a != null) {
            this.startProduction(this.a);
        }
    }
    
    public final void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
    }
    
    public final boolean isConsumer(final ImageConsumer imageConsumer) {
        return this.a == imageConsumer;
    }
    
    public final void removeConsumer(final ImageConsumer imageConsumer) {
    }
    
    public final void startProduction(final ImageConsumer a) {
        if (this.a != a) {
            (this.a = a).setDimensions(this.b, this.c);
            this.a.setProperties(null);
            this.a.setColorModel(this.d);
            this.a.setHints(this.f);
        }
        this.a.setPixels(0, 0, this.b, this.c, this.d, this.e, 0, this.b);
        this.a.imageComplete(this.g);
    }
    
    public synchronized void addConsumer(final ImageConsumer a) {
        this.a = a;
    }
}
