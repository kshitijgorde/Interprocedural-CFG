// 
// Decompiled by Procyon v0.5.30
// 

package a.a.b;

import java.awt.image.ColorModel;
import java.util.Hashtable;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageProducer;

public class a implements ImageProducer
{
    private ImageConsumer do;
    private int int;
    private int if;
    private Object a;
    private Hashtable for;
    
    public a(final int int1, final int if1, final Object a) {
        this.do = null;
        this.int = int1;
        this.if = if1;
        this.a = a;
        this.for = new Hashtable();
    }
    
    public void addConsumer(final ImageConsumer do1) {
        this.do = do1;
        try {
            this.a();
        }
        catch (Exception ex) {
            if (this.do != null) {
                this.do.imageComplete(1);
            }
        }
    }
    
    public boolean isConsumer(final ImageConsumer imageConsumer) {
        return imageConsumer == this.do;
    }
    
    public void a() {
        if (this.do != null) {
            this.do.setDimensions(this.int, this.if);
        }
        if (this.do != null) {
            this.do.setProperties(this.for);
        }
        if (this.do != null) {
            this.do.setColorModel(ColorModel.getRGBdefault());
        }
        if (this.do != null) {
            this.do.setHints(14);
        }
        if (this.do != null) {
            if (this.a instanceof byte[]) {
                this.do.setPixels(0, 0, this.int, this.if, ColorModel.getRGBdefault(), (byte[])this.a, 0, this.int);
            }
            else {
                this.do.setPixels(0, 0, this.int, this.if, ColorModel.getRGBdefault(), (int[])this.a, 0, this.int);
            }
        }
        if (this.do != null) {
            this.do.imageComplete(2);
        }
    }
    
    public void removeConsumer(final ImageConsumer imageConsumer) {
        if (imageConsumer == this.do) {
            this.do = null;
        }
    }
    
    public void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
    }
    
    public void startProduction(final ImageConsumer imageConsumer) {
        this.addConsumer(imageConsumer);
    }
}
