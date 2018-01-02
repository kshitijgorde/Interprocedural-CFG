// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.image.ImageConsumer;
import java.awt.image.ColorModel;
import java.awt.image.ImageProducer;

abstract class aD implements ImageProducer
{
    private au b;
    protected int i;
    protected int u;
    static final ColorModel a;
    protected volatile boolean q;
    
    protected aD() {
        this.i = 10;
        this.u = 100;
        this.q = false;
    }
    
    protected aD(final int i, final int u) {
        this.i = 10;
        this.u = 100;
        this.q = false;
        this.i = i;
        this.u = u;
    }
    
    protected void a(final int n, final int[] array) {
        final int n2 = 255 - 255 * n / (this.u - 1);
        final int n3 = 0xFF000000 | n2 << 16 | n2 << 8 | n2;
        int length = array.length;
        while (--length >= 0) {
            array[length] = n3;
        }
    }
    
    public synchronized void addConsumer(final ImageConsumer imageConsumer) {
        for (au au = this.b; au != null; au = au.a) {
            if (au.a == imageConsumer) {
                return;
            }
        }
        this.b = new au(imageConsumer, this.b, this);
    }
    
    public synchronized boolean isConsumer(final ImageConsumer imageConsumer) {
        for (au au = this.b; au != null; au = au.a) {
            if (au.a == imageConsumer) {
                return true;
            }
        }
        return false;
    }
    
    public synchronized void removeConsumer(final ImageConsumer imageConsumer) {
        au au = null;
        for (au au2 = this.b; au2 != null; au2 = au2.a) {
            if (au2.a == imageConsumer) {
                au2.j = false;
                if (au != null) {
                    au.a = au2.a;
                }
                else {
                    this.b = au2.a;
                }
                return;
            }
            au = au2;
        }
    }
    
    public synchronized void startProduction(final ImageConsumer imageConsumer) {
        this.addConsumer(imageConsumer);
        for (au au = this.b; au != null; au = au.a) {
            if (au.j && !au.isAlive()) {
                au.start();
            }
        }
    }
    
    protected boolean b() {
        return true;
    }
    
    public void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
    }
    
    static {
        a = ColorModel.getRGBdefault();
    }
}
