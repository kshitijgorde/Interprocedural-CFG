// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.image.ImageConsumer;
import java.awt.image.ColorModel;
import java.awt.image.ImageProducer;

abstract class c implements ImageProducer
{
    private br a;
    protected int width;
    protected int height;
    static final ColorModel a;
    protected volatile boolean aborted;
    
    protected c() {
        this.width = 10;
        this.height = 100;
        this.aborted = false;
    }
    
    protected c(final int width, final int height) {
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
        for (br br = this.a; br != null; br = br.b) {
            if (br.a == imageConsumer) {
                return;
            }
        }
        this.a = new br(imageConsumer, this.a, this);
    }
    
    public synchronized boolean isConsumer(final ImageConsumer imageConsumer) {
        for (br br = this.a; br != null; br = br.b) {
            if (br.a == imageConsumer) {
                return true;
            }
        }
        return false;
    }
    
    public synchronized void removeConsumer(final ImageConsumer imageConsumer) {
        br br = null;
        for (br br2 = this.a; br2 != null; br2 = br2.b) {
            if (br2.a == imageConsumer) {
                br2.ak = false;
                if (br != null) {
                    br.b = br2.b;
                }
                else {
                    this.a = br2.b;
                }
                return;
            }
            br = br2;
        }
    }
    
    public synchronized void startProduction(final ImageConsumer imageConsumer) {
        this.addConsumer(imageConsumer);
        for (br br = this.a; br != null; br = br.b) {
            if (br.ak && !br.isAlive()) {
                br.start();
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
