// 
// Decompiled by Procyon v0.5.30
// 

package pa.a.b;

import java.awt.image.ColorModel;
import java.util.Hashtable;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageProducer;

public class a implements ImageProducer
{
    private ImageConsumer int;
    private int a;
    private int for;
    private Object do;
    private Hashtable if;
    
    public a(final int a, final int for1, final Object do1) {
        this.int = null;
        this.a = a;
        this.for = for1;
        this.do = do1;
        this.if = new Hashtable();
    }
    
    public void addConsumer(final ImageConsumer int1) {
        this.int = int1;
        try {
            this.a();
        }
        catch (Exception ex) {
            if (this.int != null) {
                this.int.imageComplete(1);
            }
        }
    }
    
    public boolean isConsumer(final ImageConsumer imageConsumer) {
        return imageConsumer == this.int;
    }
    
    public void a() {
        if (this.int != null) {
            this.int.setDimensions(this.a, this.for);
        }
        if (this.int != null) {
            this.int.setProperties(this.if);
        }
        if (this.int != null) {
            this.int.setColorModel(ColorModel.getRGBdefault());
        }
        if (this.int != null) {
            this.int.setHints(14);
        }
        if (this.int != null) {
            if (this.do instanceof byte[]) {
                this.int.setPixels(0, 0, this.a, this.for, ColorModel.getRGBdefault(), (byte[])this.do, 0, this.a);
            }
            else {
                this.int.setPixels(0, 0, this.a, this.for, ColorModel.getRGBdefault(), (int[])this.do, 0, this.a);
            }
        }
        if (this.int != null) {
            this.int.imageComplete(2);
        }
    }
    
    public void removeConsumer(final ImageConsumer imageConsumer) {
        if (imageConsumer == this.int) {
            this.int = null;
        }
    }
    
    public void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
    }
    
    public void startProduction(final ImageConsumer imageConsumer) {
        this.addConsumer(imageConsumer);
    }
}
