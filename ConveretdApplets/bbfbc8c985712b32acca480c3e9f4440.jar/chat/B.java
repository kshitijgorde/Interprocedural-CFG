// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.util.Hashtable;
import java.awt.image.ImageConsumer;

final class B extends Thread
{
    ImageConsumer a;
    boolean a;
    B a;
    private bO a;
    
    B(final ImageConsumer a, final B a2, final bO a3) {
        super("Synimg");
        this.a = a;
        this.a = a2;
        this.a = a3;
        this.setDaemon(this.a = true);
    }
    
    public final void run() {
        final ImageConsumer a = this.a;
        final int a2 = this.a.a;
        final int b = this.a.b;
        int hints = 14;
        if (this.a.a()) {
            hints = 30;
        }
        a.setHints(hints);
        a.setDimensions(a2, b);
        a.setProperties(null);
        a.setColorModel(bO.a);
        if (this.a) {
            final int[] array = new int[a2];
            do {
                for (int n = 0; n < b && this.a; ++n) {
                    this.a.a(n, array);
                    a.setPixels(0, n, a2, 1, bO.a, array, 0, a2);
                }
                a.imageComplete(this.a.a() ? 3 : 2);
            } while (!this.a.a() && this.a);
        }
    }
}
