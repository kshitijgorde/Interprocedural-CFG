// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.util.Hashtable;
import java.awt.image.ImageConsumer;

class au extends Thread
{
    ImageConsumer a;
    boolean j;
    au a;
    aD a;
    
    au(final ImageConsumer a, final au a2, final aD a3) {
        super("Synimg");
        this.a = a;
        this.a = a2;
        this.a = a3;
        this.setDaemon(this.j = true);
    }
    
    public void run() {
        final ImageConsumer a = this.a;
        final int i = this.a.i;
        final int u = this.a.u;
        int hints = 0x8 | 0x4 | 0x2;
        if (this.a.b()) {
            hints |= 0x10;
        }
        a.setHints(hints);
        a.setDimensions(i, u);
        a.setProperties(null);
        final ImageConsumer imageConsumer = a;
        final aD a2 = this.a;
        imageConsumer.setColorModel(aD.a);
        if (this.j) {
            final int[] array = new int[i];
            do {
                for (int n = 0; n < u && this.j; ++n) {
                    this.a.a(n, array);
                    if (this.a.q) {
                        a.imageComplete(4);
                        return;
                    }
                    final ImageConsumer imageConsumer2 = a;
                    final int n2 = 0;
                    final int n3 = n;
                    final int n4 = i;
                    final int n5 = 1;
                    final aD a3 = this.a;
                    imageConsumer2.setPixels(n2, n3, n4, n5, aD.a, array, 0, i);
                }
                a.imageComplete(this.a.b() ? 3 : 2);
            } while (!this.a.b() && this.j);
        }
    }
}
