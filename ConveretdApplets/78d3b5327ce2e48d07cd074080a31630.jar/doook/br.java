// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.util.Hashtable;
import java.awt.image.ImageConsumer;

class br extends Thread
{
    ImageConsumer a;
    boolean ak;
    br b;
    c a;
    
    br(final ImageConsumer a, final br b, final c a2) {
        super("Synimg");
        this.a = a;
        this.b = b;
        this.a = a2;
        this.setDaemon(this.ak = true);
    }
    
    public void run() {
        final ImageConsumer a = this.a;
        final int width = this.a.width;
        final int height = this.a.height;
        int hints = 0x8 | 0x4 | 0x2;
        if (this.a.a()) {
            hints |= 0x10;
        }
        a.setHints(hints);
        a.setDimensions(width, height);
        a.setProperties(null);
        final ImageConsumer imageConsumer = a;
        final c a2 = this.a;
        imageConsumer.setColorModel(c.a);
        if (this.ak) {
            final int[] array = new int[width];
            do {
                for (int n = 0; n < height && this.ak; ++n) {
                    this.a.a(n, array);
                    if (this.a.aborted) {
                        a.imageComplete(4);
                        return;
                    }
                    final ImageConsumer imageConsumer2 = a;
                    final int n2 = 0;
                    final int n3 = n;
                    final int n4 = width;
                    final int n5 = 1;
                    final c a3 = this.a;
                    imageConsumer2.setPixels(n2, n3, n4, n5, c.a, array, 0, width);
                }
                a.imageComplete(this.a.a() ? 3 : 2);
            } while (!this.a.a() && this.ak);
        }
    }
}
