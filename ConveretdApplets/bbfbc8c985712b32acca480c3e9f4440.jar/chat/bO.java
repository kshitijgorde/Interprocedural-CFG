// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.image.ImageConsumer;
import java.awt.image.ColorModel;
import java.awt.image.ImageProducer;

abstract class bO implements ImageProducer
{
    private B a;
    protected int a;
    protected int b;
    static final ColorModel a;
    private volatile boolean a;
    
    protected bO() {
        this.a = 10;
        this.b = 100;
        this.a = false;
    }
    
    protected bO(final int a) {
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
        for (B b = this.a; b != null; b = b.a) {
            if (b.a == imageConsumer) {
                return;
            }
        }
        this.a = new B(imageConsumer, this.a, this);
    }
    
    public synchronized boolean isConsumer(final ImageConsumer imageConsumer) {
        for (B b = this.a; b != null; b = b.a) {
            if (b.a == imageConsumer) {
                return true;
            }
        }
        return false;
    }
    
    public synchronized void removeConsumer(final ImageConsumer imageConsumer) {
        B b = null;
        B b2 = this.a;
        while (b2 != null) {
            if (b2.a == imageConsumer) {
                b2.a = false;
                if (b != null) {
                    b.a = b2.a;
                    return;
                }
                this.a = b2.a;
            }
            else {
                b = b2;
                b2 = b2.a;
            }
        }
    }
    
    public synchronized void startProduction(final ImageConsumer imageConsumer) {
        this.addConsumer(imageConsumer);
        for (B b = this.a; b != null; b = b.a) {
            if (b.a && !b.isAlive()) {
                b.start();
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
