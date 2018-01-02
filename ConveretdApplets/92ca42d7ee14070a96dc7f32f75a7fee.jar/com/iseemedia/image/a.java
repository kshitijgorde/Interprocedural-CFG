// 
// Decompiled by Procyon v0.5.30
// 

package com.iseemedia.image;

import java.awt.image.ColorModel;
import java.util.Hashtable;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageProducer;

public final class a implements ImageProducer
{
    private ImageConsumer a;
    private int b;
    private int c;
    private Object d;
    private Hashtable e;
    
    public a(final int b, final int c, final Object d) {
        this.a = null;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = new Hashtable();
    }
    
    public final void addConsumer(final ImageConsumer a) {
        this.a = a;
        try {
            this.a();
        }
        catch (Exception ex) {
            if (this.a != null) {
                this.a.imageComplete(1);
            }
        }
    }
    
    public final boolean isConsumer(final ImageConsumer imageConsumer) {
        return imageConsumer == this.a;
    }
    
    public final void removeConsumer(final ImageConsumer imageConsumer) {
        if (imageConsumer == this.a) {
            this.a = null;
        }
    }
    
    public final void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
    }
    
    public final void startProduction(final ImageConsumer imageConsumer) {
        this.addConsumer(imageConsumer);
    }
    
    public final void a() {
        if (this.a != null) {
            this.a.setDimensions(this.b, this.c);
        }
        if (this.a != null) {
            this.a.setProperties(this.e);
        }
        if (this.a != null) {
            this.a.setColorModel(ColorModel.getRGBdefault());
        }
        if (this.a != null) {
            this.a.setHints(14);
        }
        if (this.a != null) {
            if (this.d instanceof byte[]) {
                this.a.setPixels(0, 0, this.b, this.c, ColorModel.getRGBdefault(), (byte[])this.d, 0, this.b);
            }
            else {
                this.a.setPixels(0, 0, this.b, this.c, ColorModel.getRGBdefault(), (int[])this.d, 0, this.b);
            }
        }
        if (this.a != null) {
            this.a.imageComplete(2);
        }
    }
}
