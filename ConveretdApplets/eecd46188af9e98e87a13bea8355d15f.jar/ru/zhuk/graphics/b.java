// 
// Decompiled by Procyon v0.5.30
// 

package ru.zhuk.graphics;

import java.awt.image.ImageObserver;
import java.awt.Image;
import java.util.Random;
import java.awt.Graphics;

public class b
{
    private Graphics e;
    private int d;
    private Random c;
    private int a;
    private boolean b;
    private Image f;
    
    public b(final Graphics e) {
        this.e = e;
        this.c = new Random();
    }
    
    public Graphics a() {
        return this.e;
    }
    
    public void a(final int d) {
        this.d = d;
    }
    
    public void a(final Image f) {
        this.f = f;
    }
    
    private void a(int n, int n2) {
        if (this.f != null) {
            final int n3 = 4;
            this.e.drawImage(this.f, n - n3, n2 - n3, n + n3, n2 + n3, n - n3, n2 - n3, n + n3, n2 + n3, null);
            return;
        }
        ++this.a;
        if (this.d == 0) {
            this.e.drawLine(n, n2, n, n2);
        }
        else if (this.d > 0 && this.d < 10) {
            final int d = this.d;
            final int n4 = (d << 1) + 1;
            this.e.fillArc(n - d, n2 - d, n4, n4, 0, 360);
        }
        else if (this.d > 10 && this.d < 20) {
            final int n5 = this.d - 10;
            final int n6 = (n5 << 1) + 1;
            this.e.fillRect(n - n5, n2 - n5, n6, n6);
        }
        else if (this.d > 20 && this.d < 30) {
            final int n7 = (1 << this.d - 20) - 1;
            n += (this.c.nextInt() & n7) - (n7 >> 1);
            n2 += (this.c.nextInt() & n7) - (n7 >> 1);
            this.e.drawLine(n, n2, n, n2);
        }
        else if (this.d > 30 && this.d < 40) {
            final int n8 = (1 << this.d - 30) - 1;
            final int n9 = n8 >> 1;
            this.e.drawLine(n, n2, n + ((this.c.nextInt() & n8) - n9), n2 + ((this.c.nextInt() & n8) - n9));
        }
        else if (this.d > 40 && this.d < 50) {
            final int n10 = (1 << this.d - 40) - 1;
            final int n11 = n10 >> 1;
            this.e.drawLine(n + ((this.c.nextInt() & n10) - n11), n2 + ((this.c.nextInt() & n10) - n11), n + ((this.c.nextInt() & n10) - n11), n2 + ((this.c.nextInt() & n10) - n11));
        }
        else if (this.d > 50 && this.d < 60) {
            final int n12 = this.d - 50;
            this.e.drawLine(n - n12, n2 - n12, n + n12, n2 + n12);
            this.e.drawLine(n - n12 + 1, n2 - n12, n + n12, n2 + n12 - 1);
            this.e.drawLine(n - n12, n2 - n12 + 1, n + n12 - 1, n2 + n12);
        }
        else if (this.d > 60 && this.d < 70) {
            final int n13 = this.d - 60;
            this.e.drawLine(n - n13, n2 + n13, n + n13, n2 - n13);
            this.e.drawLine(n - n13, n2 + n13 - 1, n + n13 - 1, n2 - n13);
            this.e.drawLine(n - n13 + 1, n2 + n13, n + n13, n2 - n13 + 1);
        }
        else if (this.d >= 70 && this.d < 80) {
            final int n14 = this.d - 70;
            final int n15 = (n14 << 1) + 1;
            if ((this.a >>> 3 + n14 & 0x1) != 0x0 || this.b) {
                this.e.fillRect(n - n14, n2 - n14, n15, n15);
            }
        }
    }
    
    public void b(final int n, final int n2, final int n3, final int n4) {
        final int abs = Math.abs(n3 - n);
        final int abs2 = Math.abs(n4 - n2);
        if (abs2 <= abs) {
            int n5 = 2 * abs2 - abs;
            final int n6 = 2 * abs2;
            final int n7 = 2 * (abs2 - abs);
            int i;
            int n8;
            int n9;
            int n10;
            if (n > n3) {
                i = n3;
                n8 = n4;
                n9 = -1;
                n10 = n;
            }
            else {
                i = n;
                n8 = n2;
                n9 = 1;
                n10 = n3;
            }
            this.a(i, n8);
            if ((n4 - n2) * n9 > 0) {
                while (i < n10) {
                    ++i;
                    if (n5 < 0) {
                        n5 += n6;
                    }
                    else {
                        ++n8;
                        n5 += n7;
                    }
                    this.a(i, n8);
                }
            }
            else {
                while (i < n10) {
                    ++i;
                    if (n5 < 0) {
                        n5 += n6;
                    }
                    else {
                        --n8;
                        n5 += n7;
                    }
                    this.a(i, n8);
                }
            }
        }
        else {
            int n11 = 2 * abs - abs2;
            final int n12 = 2 * abs;
            final int n13 = 2 * (abs - abs2);
            int j;
            int n14;
            int n15;
            int n16;
            if (n2 > n4) {
                j = n4;
                n14 = n3;
                n15 = n2;
                n16 = -1;
            }
            else {
                j = n2;
                n14 = n;
                n15 = n4;
                n16 = 1;
            }
            this.a(n14, j);
            if ((n3 - n) * n16 > 0) {
                while (j < n15) {
                    ++j;
                    if (n11 < 0) {
                        n11 += n12;
                    }
                    else {
                        ++n14;
                        n11 += n13;
                    }
                    this.a(n14, j);
                }
            }
            else {
                while (j < n15) {
                    ++j;
                    if (n11 < 0) {
                        n11 += n12;
                    }
                    else {
                        --n14;
                        n11 += n13;
                    }
                    this.a(n14, j);
                }
            }
        }
    }
    
    public void d(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.b(n, n2, n3, n4);
        if (n5 != 0) {
            this.b = true;
            final double n6 = n3 - n;
            final double n7 = n4 - n2;
            final double min = Math.min(8.0, Math.max(Math.abs(n6), Math.abs(n7)) / 2);
            final double n8 = 0.39269908169872414;
            final double n9 = 1.5707963267948966;
            double n10 = (n6 != 0) ? Math.atan(n7 / n6) : ((n7 > 0) ? n9 : (-n9));
            if (n6 < 0) {
                n10 += 3.141592653589793;
            }
            final int n11 = (int)Math.round(-min * Math.cos(n10 - n8));
            final int n12 = (int)Math.round(-min * Math.sin(n10 - n8));
            final int n13 = (int)Math.round(-min * Math.cos(n10 + n8));
            final int n14 = (int)Math.round(-min * Math.sin(n10 + n8));
            if ((n5 & 0x1) != 0x0) {
                this.b(n, n2, n - n11, n2 - n12);
                this.b(n, n2, n - n13, n2 - n14);
            }
            if ((n5 & 0x4) != 0x0) {
                final int n15 = n + (int)n6 / 2;
                final int n16 = n2 + (int)n7 / 2;
                this.b(n15, n16, n15 + n11, n16 + n12);
                this.b(n15, n16, n15 + n13, n16 + n14);
            }
            if ((n5 & 0x2) != 0x0) {
                this.b(n3, n4, n3 + n11, n4 + n12);
                this.b(n3, n4, n3 + n13, n4 + n14);
            }
            this.b = false;
        }
    }
    
    public void c(int n, int n2, int n3, int n4, int min) {
        if (n > n3) {
            final int n5 = n;
            n = n3;
            n3 = n5;
        }
        if (n2 > n4) {
            final int n6 = n2;
            n2 = n4;
            n4 = n6;
        }
        min = Math.min(min, Math.min(n3 - n + 1, n4 - n2 + 1) / 2);
        for (int i = n + min; i < n3 - min; ++i) {
            this.a(i, n2);
            this.a(i + 1, n4);
        }
        for (int j = n2 + min; j < n4 - min; ++j) {
            this.a(n, j + 1);
            this.a(n3, j);
        }
        if (min > 0) {
            this.a(n + min, n2 + min, min, min, false, 1);
            this.a(n3 - min, n2 + min, min, min, false, 2);
            this.a(n3 - min, n4 - min, min, min, false, 4);
            this.a(n + min, n4 - min, min, min, false, 8);
        }
    }
    
    public void b(int n, int n2, int n3, int n4, int min) {
        if (n > n3) {
            final int n5 = n;
            n = n3;
            n3 = n5;
        }
        if (n2 > n4) {
            final int n6 = n2;
            n2 = n4;
            n4 = n6;
        }
        final int n7 = n3 - n + 1;
        final int n8 = n4 - n2 + 1;
        min = Math.min(min, Math.min(n7, n8) / 2);
        final int n9 = min * 2;
        this.e.fillRect(n, n2 + min, n7, n8 - n9);
        this.e.fillRect(n + min, n2, n7 - n9, n8);
        if (min > 0) {
            this.a(n + min, n2 + min, min, min, true, 1);
            this.a(n3 - min, n2 + min, min, min, true, 2);
            this.a(n3 - min, n4 - min, min, min, true, 4);
            this.a(n + min, n4 - min, min, min, true, 8);
        }
    }
    
    private void a(final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n5 & 0x1) != 0x0) {
            this.a(n3 - n, n4 - n2);
        }
        if ((n5 & 0x2) != 0x0) {
            this.a(n3 + n, n4 - n2);
        }
        if ((n5 & 0x4) != 0x0) {
            this.a(n3 + n, n4 + n2);
        }
        if ((n5 & 0x8) != 0x0) {
            this.a(n3 - n, n4 + n2);
        }
    }
    
    private void a(final int n, final int n2, final int n3, final int n4) {
        this.e.drawLine(n3 - n, n4 - n2, n3 + n, n4 - n2);
        this.e.drawLine(n3 - n, n4 + n2, n3 + n, n4 + n2);
    }
    
    public void a(final int n, final int n2, final int n3, final int n4, final boolean b) {
        this.a(n, n2, n3, n4, b, 15);
    }
    
    public void a(final int n, final int n2, final int n3, final int n4, final boolean b, final int n5) {
        int n6 = 0;
        int i = Math.abs(n4);
        final long n7 = Math.abs(n3);
        final long n8 = Math.abs(n4);
        final long n9 = n7 * n7;
        final long n10 = 2 * n9;
        long n11;
        long n12;
        long n13;
        long n14;
        long n15;
        for (n11 = n8 * n8, n12 = 2 * n11, n13 = n11 - n9 * n8 + n9 / 4L, n14 = 0L, n15 = n10 * n8; n14 < n15; n14 += n12, n13 += n11 + n14) {
            if (b) {
                this.a(n6, i, n, n2);
            }
            else {
                this.a(n6, i, n, n2, n5);
            }
            if (n13 > 0L) {
                --i;
                n15 -= n10;
                n13 -= n15;
            }
            ++n6;
        }
        for (long n16 = n13 + (3L * (n9 - n11) / 2L - (n14 + n15)) / 2L; i >= 0; --i, n15 -= n10, n16 += n9 - n15) {
            if (b) {
                this.a(n6, i, n, n2);
            }
            else {
                this.a(n6, i, n, n2, n5);
            }
            if (n16 < 0L) {
                ++n6;
                n14 += n12;
                n16 += n14;
            }
        }
    }
    
    public static void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final int n5) {
        final int[] array = { 0, 2, 4 };
        for (int i = n2 + array[n5 % 3]; i < n2 + n4; i += 6) {
            graphics.drawLine(n, i, n, Math.min(i + 3, n2 + n4));
            graphics.drawLine(n + n3, i, n + n3, Math.min(i + 3, n2 + n4));
        }
        for (int j = n + array[n5 % 3]; j < n + n3; j += 6) {
            graphics.drawLine(j, n2, Math.min(j + 3, n + n3), n2);
            graphics.drawLine(j, n2 + n4, Math.min(j + 3, n + n3), n2 + n4);
        }
    }
}
