// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.image.ImageConsumer;
import java.awt.image.ColorModel;
import java.awt.image.ImageProducer;

abstract class az implements ImageProducer
{
    private bN a;
    protected int width;
    protected int height;
    static final ColorModel a;
    protected volatile boolean aborted;
    
    protected az() {
        this.width = 10;
        this.height = 100;
        this.aborted = false;
    }
    
    protected az(final int width, final int height) {
        this.width = 10;
        this.height = 100;
        this.aborted = false;
        this.width = width;
        this.height = height;
    }
    
    protected void a(final int n, final int[] array) {
        final int n2 = 255 - 255 * n / (this.height - 1);
        final int n3 = 0xFF000000 | n2 << 16 | n2 << 8 | n2;
        int length = array.length;
        while (--length >= 0) {
            array[length] = n3;
        }
    }
    
    public synchronized void addConsumer(final ImageConsumer imageConsumer) {
        for (bN bn = this.a; bn != null; bn = bn.b) {
            if (bn.a == imageConsumer) {
                return;
            }
        }
        this.a = new bN(imageConsumer, this.a, this);
    }
    
    public synchronized boolean isConsumer(final ImageConsumer imageConsumer) {
        for (bN bn = this.a; bn != null; bn = bn.b) {
            if (bn.a == imageConsumer) {
                return true;
            }
        }
        return false;
    }
    
    public synchronized void removeConsumer(final ImageConsumer imageConsumer) {
        bN bn = null;
        for (bN bn2 = this.a; bn2 != null; bn2 = bn2.b) {
            if (bn2.a == imageConsumer) {
                bn2.ar = false;
                if (bn != null) {
                    bn.b = bn2.b;
                }
                else {
                    this.a = bn2.b;
                }
                return;
            }
            bn = bn2;
        }
    }
    
    public synchronized void startProduction(final ImageConsumer imageConsumer) {
        this.addConsumer(imageConsumer);
        for (bN bn = this.a; bn != null; bn = bn.b) {
            if (bn.ar && !bn.isAlive()) {
                bn.start();
            }
        }
    }
    
    protected boolean i() {
        return true;
    }
    
    public void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
    }
    
    static {
        a = ColorModel.getRGBdefault();
    }
}
