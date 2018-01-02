// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.image.ImageConsumer;
import java.awt.image.ColorModel;
import java.awt.image.ImageProducer;

abstract class aT implements ImageProducer
{
    private v a;
    protected int a;
    protected int b;
    static final ColorModel a;
    private volatile boolean a;
    
    protected aT() {
        this.a = 10;
        this.b = 100;
        this.a = false;
    }
    
    protected aT(final int a) {
        this.a = 10;
        this.b = 100;
        this.a = false;
        this.a = a;
        this.b = 150;
    }
    
    protected void a(int length, final int[] array) {
        final int n = 255 - length * 255 / (this.b - 1);
        final int n2 = 0xFF000000 | n << 16 | n << 8 | n;
        length = array.length;
        while (--length >= 0) {
            array[length] = n2;
        }
    }
    
    public synchronized void addConsumer(final ImageConsumer imageConsumer) {
        for (v v = this.a; v != null; v = v.a) {
            if (v.a == imageConsumer) {
                return;
            }
        }
        this.a = new v(imageConsumer, this.a, this);
    }
    
    public synchronized boolean isConsumer(final ImageConsumer imageConsumer) {
        for (v v = this.a; v != null; v = v.a) {
            if (v.a == imageConsumer) {
                return true;
            }
        }
        return false;
    }
    
    public synchronized void removeConsumer(final ImageConsumer imageConsumer) {
        v v = null;
        v v2 = this.a;
        while (v2 != null) {
            if (v2.a == imageConsumer) {
                v2.a = false;
                if (v != null) {
                    v.a = v2.a;
                    return;
                }
                this.a = v2.a;
            }
            else {
                v = v2;
                v2 = v2.a;
            }
        }
    }
    
    public synchronized void startProduction(final ImageConsumer imageConsumer) {
        this.addConsumer(imageConsumer);
        for (v v = this.a; v != null; v = v.a) {
            if (v.a && !v.isAlive()) {
                v.start();
            }
        }
    }
    
    protected boolean a() {
        return true;
    }
    
    public void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
    }
    
    static {
        a = ColorModel.getRGBdefault();
    }
}
