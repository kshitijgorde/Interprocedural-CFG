// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.util.Hashtable;
import java.awt.image.ImageConsumer;

class bN extends Thread
{
    ImageConsumer a;
    boolean ar;
    bN b;
    az a;
    
    bN(final ImageConsumer a, final bN b, final az a2) {
        super("Synimg");
        this.a = a;
        this.b = b;
        this.a = a2;
        this.setDaemon(this.ar = true);
    }
    
    public void run() {
        final ImageConsumer a = this.a;
        final int width = this.a.width;
        final int height = this.a.height;
        int hints = 0x8 | 0x4 | 0x2;
        if (this.a.i()) {
            hints |= 0x10;
        }
        a.setHints(hints);
        a.setDimensions(width, height);
        a.setProperties(null);
        final ImageConsumer imageConsumer = a;
        final az a2 = this.a;
        imageConsumer.setColorModel(az.a);
        if (this.ar) {
            final int[] array = new int[width];
            do {
                for (int n = 0; n < height && this.ar; ++n) {
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
                    final az a3 = this.a;
                    imageConsumer2.setPixels(n2, n3, n4, n5, az.a, array, 0, width);
                }
                a.imageComplete(this.a.i() ? 3 : 2);
            } while (!this.a.i() && this.ar);
        }
    }
}
