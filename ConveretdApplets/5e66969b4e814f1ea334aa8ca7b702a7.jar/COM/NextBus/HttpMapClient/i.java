// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.HttpMapClient;

import java.util.Iterator;
import java.awt.Color;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.List;
import java.awt.Toolkit;
import java.util.Map;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.Image;
import java.awt.image.ImageObserver;

final class i implements ImageObserver
{
    private m a;
    private Image b;
    private int c;
    private long d;
    private /* synthetic */ b e;
    
    i(final b e, final m a) {
        this.e = e;
        this.c = 0;
        this.a = a;
        final byte[] b;
        if ((b = e.b.b(this.a)) != null) {
            this.a(b);
        }
    }
    
    private void a(final byte[] array) {
        this.c = 1;
        if (this.e.a()) {
            this.e.a("Render started for tile:" + this.a);
        }
        this.b = this.e.c.createImage(new FilteredImageSource(this.e.c.createImage(array).getSource(), this.e.d));
        if (this.e.c.prepareImage(this.b, -1, -1, this)) {
            this.c = 2;
            if (this.e.a()) {
                this.e.a("Render completed immediately for tile: " + this.a);
            }
            this.e.c(this.a);
        }
    }
    
    public final boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.c = 2;
            if (this.e.a()) {
                this.e.a("Render completed for tile: " + this.a);
            }
            this.e.c(this.a);
            return false;
        }
        if ((n & 0x40) != 0x0) {
            this.c = 3;
            if (this.e.a()) {
                this.e.a("Render failed for tile: " + this.a);
            }
            this.e.e(this.a);
            return false;
        }
        return true;
    }
}
