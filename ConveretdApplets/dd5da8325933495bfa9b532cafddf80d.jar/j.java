import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.awt.image.DirectColorModel;

// 
// Decompiled by Procyon v0.5.30
// 

public class j
{
    private d a;
    public int b;
    public int c;
    public int[] d;
    private DirectColorModel e;
    private MemoryImageSource f;
    public Image g;
    private boolean h;
    
    public j() {
        this.e = null;
        this.f = null;
        this.g = null;
    }
    
    public j(final d a, final int b, final int c, final boolean h) {
        final boolean dj = p.dJ;
        this.e = null;
        this.f = null;
        this.g = null;
        this.a = a;
        this.b = b;
        this.c = c;
        this.h = h;
        this.d = new int[b * c];
        int n = 0;
        while (true) {
            while (true) {
                Label_0073: {
                    if (!dj) {
                        break Label_0073;
                    }
                    final j j = this;
                    j.d[n] = 0;
                    ++n;
                }
                if (n < b * c) {
                    continue;
                }
                break;
            }
            final j j = this;
            if (!dj) {
                this.e = (h ? new DirectColorModel(32, 16711680, 65280, 255, -16777216) : new DirectColorModel(24, 16711680, 65280, 255));
                (this.f = new MemoryImageSource(b, c, this.e, this.d, 0, b)).setAnimated(true);
                this.f.setFullBufferUpdates(true);
                this.g = a.J.createImage(this.f);
                return;
            }
            continue;
        }
    }
    
    public void a() {
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
    }
    
    public void b() {
        if (this.f != null) {
            this.f.newPixels();
        }
    }
    
    public void a(final int[] array, final float n, final int[] array2, final float n2, final boolean b) {
        final boolean dj = p.dJ;
        final int n3 = this.b * this.c;
        int n4 = 0;
        while (true) {
            Label_0223: {
                if (!dj) {
                    break Label_0223;
                }
                final int n5 = array[n4];
                final int n6 = array2[n4];
                Label_0220: {
                    if (!b || (n5 & 0xFF000000) != 0x0) {
                        this.d[n4] = (((int)(((n5 & 0xFF0000) >> 16) * n) << 16) + ((int)(((n5 & 0xFF00) >> 8) * n) << 8) + (int)((n5 & 0xFF) * n) + ((int)(((n6 & 0xFF0000) >> 16) * n2) << 16) + ((int)(((n6 & 0xFF00) >> 8) * n2) << 8) + (int)((n6 & 0xFF) * n2) | 0xFF000000);
                        if (!dj) {
                            break Label_0220;
                        }
                    }
                    this.d[n4] = (this.h ? 0 : n6);
                }
                ++n4;
            }
            if (n4 >= n3) {
                return;
            }
            continue;
        }
    }
}
