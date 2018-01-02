// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.awt.Font;
import java.awt.Point;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Graphics2D;

public final class V
{
    private W[] a;
    private int b;
    
    public V() {
        this.a = new W[16];
        this.b = 0;
    }
    
    public final void a(final Graphics2D graphics2D) {
        for (int i = 0; i < this.b; ++i) {
            final W w = this.a[i];
            graphics2D.setFont(w.g);
            final FontMetrics fontMetrics;
            final int l = (fontMetrics = graphics2D.getFontMetrics()).getMaxAscent() + fontMetrics.getMaxDescent();
            if (w.b < 60 || w.b > 300) {
                w.h = 2;
            }
            else if (w.b > 120 && w.b < 240) {
                w.h = 1;
            }
            else if (w.b < 180) {
                w.h = 1;
            }
            else {
                w.h = 2;
            }
            w.k = 4 + fontMetrics.stringWidth(w.a);
            w.l = l;
            switch (w.h) {
                case 1: {
                    w.i = w.c.x - 9 - w.k;
                    w.j = w.c.y - 6 - w.l;
                    w.m = w.k;
                    w.n = w.l / 2;
                    w.o = w.c.x - 1;
                    w.p = w.c.y - 1;
                    break;
                }
                case 2: {
                    w.i = w.c.x + 9;
                    w.j = w.c.y + 6;
                    w.m = 0;
                    w.n = w.l / 2;
                    w.o = w.c.x + 1;
                    w.p = w.c.y + 1;
                    break;
                }
                default: {
                    throw new Error("Unknown type in adjustment!!!");
                }
            }
        }
        this.a(this.a);
        graphics2D.setColor(Color.black);
        for (int j = 0; j < this.b; ++j) {
            final W w2 = this.a[j];
            graphics2D.drawLine(w2.i + w2.m, w2.j + w2.n, w2.o, w2.p);
        }
        for (int k = 0; k < this.b; ++k) {
            final W w3 = this.a[k];
            graphics2D.setColor(Color.black);
            graphics2D.drawRect(w3.i, w3.j, w3.k, w3.l);
            graphics2D.setColor(w3.d);
            graphics2D.fillRect(w3.i + 1, w3.j + 1, w3.k - 1, w3.l - 1);
            graphics2D.setColor(w3.e);
            graphics2D.setFont(w3.g);
            graphics2D.drawString(w3.a, w3.i + 3, w3.j + graphics2D.getFontMetrics().getMaxAscent());
        }
    }
    
    private final void a(final W[] array) {
        for (int i = 0; i < this.b - 1; ++i) {
            for (int j = i + 1; j < this.b; ++j) {
                if (array[i].h == array[j].h) {
                    switch (array[i].h) {
                        case 1: {
                            if (array[i].j < array[j].j || (array[i].j == array[j].j && array[i].a.compareTo(array[j].a) < 0)) {
                                final W w = array[i];
                                array[i] = array[j];
                                array[j] = w;
                                break;
                            }
                            break;
                        }
                        case 2: {
                            if (array[i].j > array[j].j || (array[i].j == array[j].j && array[i].a.compareTo(array[j].a) > 0)) {
                                final W w2 = array[i];
                                array[i] = array[j];
                                array[j] = w2;
                                break;
                            }
                            break;
                        }
                        default: {
                            throw new Error("Unknown type in adjustment!!!");
                        }
                    }
                }
            }
        }
        for (int k = 1; k < this.b; ++k) {
            while (a(array, k)) {}
        }
    }
    
    private static boolean a(final W[] array, final int n) {
        final int i = array[n].i;
        int j = array[n].j;
        final int n2 = i + array[n].k;
        int n3 = j + array[n].l;
        final int h = array[n].h;
        boolean b = false;
        for (int k = 0; k < n; ++k) {
            if (array[k].h == h) {
                final int l = array[k].i;
                final int m = array[k].j;
                final int n4 = l + array[k].k;
                final int n5 = m + array[k].l;
                if (l < n2 && n4 > i && m < n3 && n5 > j) {
                    b = true;
                    switch (h) {
                        case 1: {
                            final int n6 = n3 - m;
                            j -= n6;
                            n3 -= n6;
                            final W w = array[n];
                            w.j -= n6;
                            break;
                        }
                        case 2: {
                            final int n7 = n5 - j;
                            j += n7;
                            n3 += n7;
                            final W w2 = array[n];
                            w2.j += n7;
                            break;
                        }
                        default: {
                            throw new Error("Unknown type in adjustment!!!");
                        }
                    }
                }
            }
        }
        return b;
    }
    
    public final void a() {
        for (int i = 0; i < this.b; ++i) {
            this.a[i] = null;
        }
        this.b = 0;
    }
    
    public final void a(final String s, final Point point, final int n, final Color color, final Color color2, final f f, final Font font) {
        if (this.b >= this.a.length) {
            final W[] a = new W[this.a.length << 1];
            System.arraycopy(this.a, 0, a, 0, this.a.length);
            this.a = a;
        }
        this.a[this.b] = new W(this, s, point, n, color, color2, f, font);
        ++this.b;
    }
    
    final f a(final Point point) {
        f f = null;
        for (int i = 0; i < this.b; ++i) {
            final W w = this.a[i];
            if (point.x >= w.i && point.y >= w.j && point.x < w.i + w.k && point.y < w.j + w.l) {
                f = w.f;
            }
        }
        return f;
    }
}
