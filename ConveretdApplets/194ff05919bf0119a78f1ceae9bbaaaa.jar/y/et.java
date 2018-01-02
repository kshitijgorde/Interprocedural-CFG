// 
// Decompiled by Procyon v0.5.30
// 

package y;

import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.FontMetrics;
import java.awt.Image;

public final class et extends u
{
    private static int[] a;
    private bl a;
    private Image[] a;
    private boolean a;
    private dp a;
    private FontMetrics a;
    private ak a;
    
    public et(final Image[] a, final boolean a2, final ak a3) {
        this.a = new bl((byte)0);
        this.a = new du();
        this.a = a3;
        this.a = a;
        this.a = a2;
    }
    
    public final void a(final bl bl) {
        this.a.a.removeAllElements();
        for (int i = 0; i < bl.a.size(); ++i) {
            this.a.a(bl.a(i));
        }
        if (this.a != null) {
            this.a.a(this.a);
        }
        this.h();
    }
    
    public final void a(final ei ei) {
        this.b(ei);
        ei.a(u.a);
        final bl[] array = new bl[4];
        for (int i = 0; i < 4; ++i) {
            array[i] = new bl((byte)0);
        }
        for (int j = 0; j < this.a.a.size(); ++j) {
            array[this.a.a(j).b].a(this.a.a(j));
        }
        int n = 0;
        for (int k = 0; k < 4; ++k) {
            if (k % 2 == 0) {
                n = 0;
            }
            final int n2 = this.a ? (k / 2) : k;
            final Image image = this.a[et.a[k]];
            ei.a(image, this.a ? n : 0, n2 * 18 + (18 - image.getHeight(null)) / 2, null);
            if (this.a) {
                n += 16;
            }
            for (int l = 0; l < array[et.a[k]].a.size(); ++l) {
                if (!this.a) {
                    n = l * 12 + 16;
                }
                ei.a(Color.white);
                ei.b(n, n2 * 18, 15, 17);
                ei.a(Color.black);
                ei.c(n, n2 * 18, 15, 17);
                final en a = array[et.a[k]].a(l);
                ei.a((a.b == 2 || a.b == 0) ? Color.black : Color.red);
                ei.a(a.a(this.a).substring(0, 1), n + 2, n2 * 18 + (18 + this.a.getAscent()) / 2 - 1);
                if (this.a) {
                    n += 12;
                }
            }
            if (this.a) {
                n += 4;
            }
        }
    }
    
    public final void b() {
        super.b();
        this.a = this.a(u.a);
    }
    
    public final int a() {
        return 136;
    }
    
    public final int b() {
        return 18 * (this.a ? 2 : 4);
    }
    
    static {
        et.a = new int[] { 2, 3, 1, 0 };
    }
}
