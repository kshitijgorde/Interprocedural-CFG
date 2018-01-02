// 
// Decompiled by Procyon v0.5.30
// 

package ru.zhuk.graphics.codec;

import ru.zhuk.graphics.d;
import java.io.IOException;
import java.awt.Color;
import java.awt.Image;
import java.io.OutputStream;
import java.awt.Component;
import java.io.FilterOutputStream;

public class a extends FilterOutputStream
{
    private static final int[] a;
    private static final int[] q;
    private static int[][] u;
    private Component o;
    private int n;
    private int l;
    private int s;
    private int p;
    private int i;
    private int e;
    private boolean j;
    private int t;
    private int k;
    private int v;
    private int r;
    private int x;
    private int d;
    private int h;
    private int f;
    private int m;
    private int y;
    private int b;
    private int c;
    private byte[] w;
    private int g;
    
    public static int a(final OutputStream outputStream, final Image image, final int n, final Color color, final Component o) throws IOException {
        final a a = new a(outputStream);
        a.o = o;
        a.a(image, n, color);
        return a.i();
    }
    
    public a(final OutputStream outputStream) {
        super(outputStream);
        this.n = 0;
        this.w = new byte[256];
    }
    
    public int i() {
        return this.n;
    }
    
    public void a(final Image image, final int n, final Color color) throws IOException {
        this.n = 0;
        if (image == null) {
            return;
        }
        final d d = new d(this.o, image, 0, 0, -1, -1, true);
        try {
            d.grabPixels();
        }
        catch (InterruptedException ex) {
            this.n = 1;
            return;
        }
        if ((d.status() & 0x80) != 0x0) {
            this.n = 1;
            return;
        }
        final int[] array = (int[])d.getPixels();
        final int width = d.getWidth();
        final int height = d.getHeight();
        switch (n) {
            case 5: {
                final int n2 = 256;
                final int[] f = this.f();
                final byte[] a = this.a(array, width, false);
                int i = n2 - 1;
                int n3 = 0;
                while (i != 0) {
                    ++n3;
                    i >>= 1;
                }
                this.a(width, height, n3);
                this.a(f, n3);
                if (color != null) {
                    this.a(color, f);
                }
                this.b(width, height);
                this.a(a, n3);
                this.write(0);
                this.write(59);
            }
            default: {
                this.n = 3;
            }
        }
    }
    
    private int[] f() {
        final int[] array = new int[256];
        for (int i = 0; i < 256; ++i) {
            array[i] = ru.zhuk.graphics.codec.a.q[i];
        }
        return array;
    }
    
    private byte[] a(final int[] array, final int n, final boolean b) {
        final byte[] array2 = new byte[array.length];
        int n2 = 0;
        for (int i = 0; i < array.length; ++i) {
            final int n3 = array[i] & 0xFFFFFF;
            int n4 = -1;
            final int n5 = (n3 & 0xFF0000) >> 16;
            final int n6 = (n3 & 0xFF00) >> 8;
            final int n7 = n3 & 0xFF;
            int n8 = n5 / 51;
            int n9 = n6 / 51;
            int n10 = n7 / 51;
            if (b) {
                final int n11 = ru.zhuk.graphics.codec.a.u[i % n % 4][i / n % 4] / 5;
                if (n8 < 5 && n5 % 51 >= n11) {
                    ++n8;
                }
                if (n9 < 5 && n6 % 51 >= n11) {
                    ++n9;
                }
                if (n10 < 5 && n7 % 51 >= n11) {
                    ++n10;
                }
                array2[i] = (byte)(n8 * 36 + n9 * 6 + n10);
            }
            else {
                for (int n12 = n8; n12 <= n8 + 1 && n12 < 6; ++n12) {
                    for (int n13 = n9; n13 <= n9 + 1 && n13 < 6; ++n13) {
                        for (int n14 = n10; n14 <= n10 + 1 && n14 < 6; ++n14) {
                            int n15 = 40 + n12 * 36 + n13 * 6 + n14;
                            if (n15 == 40) {
                                n15 = 0;
                            }
                            final int c = this.c(n3, ru.zhuk.graphics.codec.a.q[n15]);
                            if (c < n2 || n4 < 0) {
                                n2 = c;
                                n4 = n15;
                            }
                        }
                    }
                }
                int n16;
                int n17;
                if (n5 > n6 && n5 > n7) {
                    n16 = 30;
                    n17 = (n5 + 8) / 17;
                }
                else if (n6 > n5 && n6 > n7) {
                    n16 = 20;
                    n17 = (n6 + 8) / 17;
                }
                else {
                    n16 = 10;
                    n17 = (n7 + 8) / 17;
                }
                if (n17 > 0) {
                    final int n18 = n16 + (n17 - n17 / 3);
                    final int c2 = this.c(n3, ru.zhuk.graphics.codec.a.q[n18]);
                    if (c2 < n2 || n4 < 0) {
                        n2 = c2;
                        n4 = n18;
                    }
                }
                final int n19 = (this.d(n3) + 8) / 17;
                if (n19 > 0) {
                    final int n20 = n19 - n19 / 3;
                    final int c3 = this.c(n3, ru.zhuk.graphics.codec.a.q[n20]);
                    if (c3 < n2 || n4 < 0) {
                        n2 = c3;
                        n4 = n20;
                    }
                }
                array2[i] = (byte)n4;
            }
        }
        return array2;
    }
    
    private int d(final int n) {
        return (((n & 0xFF0000) >> 16) * 30 + ((n & 0xFF00) >> 8) * 59 + (n & 0xFF) * 11) / 100;
    }
    
    private int c(final int n, final int n2) {
        final int n3 = (n & 0xFF0000) >> 16;
        final int n4 = (n & 0xFF00) >> 8;
        final int n5 = n & 0xFF;
        final int n6 = (n2 & 0xFF0000) >> 16;
        final int n7 = (n2 & 0xFF00) >> 8;
        final int n8 = n2 & 0xFF;
        final int n9 = (n6 - n3) * 30;
        final int n10 = (n7 - n4) * 59;
        final int n11 = (n8 - n5) * 11;
        return (n9 * n9 + n10 * n10 + n11 * n11) / 100;
    }
    
    private void a(final int n, final int n2, final int n3) throws IOException {
        this.write(71);
        this.write(73);
        this.write(70);
        this.write(56);
        this.write(57);
        this.write(97);
        this.j(n);
        this.j(n2);
        this.write(0x80 | (n3 - 1 << 4 | n3 - 1));
        this.write(0);
        this.write(0);
    }
    
    private void a(final int[] array, final int n) throws IOException {
        for (int n2 = 1 << n, i = 0; i < n2; ++i) {
            if (i < array.length) {
                this.g(array[i]);
            }
            else {
                this.g(0);
            }
        }
    }
    
    private void a(final Color color, final int[] array) throws IOException {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == (color.getRGB() & 0xFFFFFF)) {
                this.write(33);
                this.write(249);
                this.write(4);
                this.write(1);
                this.write(0);
                this.write(0);
                this.write(i);
                this.write(0);
            }
        }
    }
    
    private void b(final int n, final int n2) throws IOException {
        this.write(44);
        this.j(0);
        this.j(0);
        this.j(n);
        this.j(n2);
        this.write(0);
    }
    
    private void j(final int n) throws IOException {
        this.write(n & 0xFF);
        this.write((n & 0xFF00) >> 8);
    }
    
    private void g(final int n) throws IOException {
        this.write((n & 0xFF0000) >> 16);
        this.write((n & 0xFF00) >> 8);
        this.write(n & 0xFF);
    }
    
    private void a(final byte[] array, final int n) throws IOException {
        int n2 = n;
        if (n2 < 2) {
            n2 = 2;
        }
        this.write(n2);
        this.b = 0;
        this.c = 0;
        this.g = 0;
        this.m = 1 << n2;
        this.y = this.m + 1;
        this.s = this.y + 1;
        this.x = (1 << n2) - 1;
        this.h = ((n2 <= 2) ? 9 : (this.x - 1));
        this.k = n2 + 1;
        this.f = 4096 - ((1 << this.k - 1) + 3);
        this.g();
        this.b(this.m);
        this.p = 0;
        for (int i = 0; i < array.length; ++i) {
            int l = array[i];
            if (l < 0) {
                l += 256;
            }
            if (this.p > 0 && l != this.l) {
                this.e();
            }
            if (this.l == l) {
                ++this.p;
            }
            else {
                this.l = l;
                this.p = 1;
            }
        }
        if (this.p > 0) {
            this.e();
        }
        this.b(this.y);
        this.c();
    }
    
    private void b() throws IOException {
        this.write(this.g);
        this.write(this.w, 0, this.g);
        this.g = 0;
    }
    
    private void h(final int n) throws IOException {
        this.w[this.g++] = (byte)n;
        if (this.g >= 255) {
            this.b();
        }
    }
    
    private void a() throws IOException {
        if (this.g > 0) {
            this.b();
        }
    }
    
    private void b(final int n) throws IOException {
        this.b |= n << this.c;
        this.c += this.t;
        while (this.c >= 8) {
            this.h(this.b & 0xFF);
            this.b >>= 8;
            this.c -= 8;
        }
    }
    
    private void c() throws IOException {
        if (this.c > 0) {
            this.h(this.b);
        }
        this.a();
    }
    
    private void g() throws IOException {
        this.t = this.k;
        this.r = this.x;
        this.d = this.h;
        this.v = 0;
        this.e = 0;
        this.j = true;
    }
    
    private void a(final int n) throws IOException {
        this.j = false;
        this.b(n);
        ++this.v;
        if (this.v >= this.r) {
            ++this.t;
            this.r += 1 << this.t - 1;
        }
        if (this.v >= this.d) {
            this.b(this.m);
            this.g();
        }
    }
    
    private int e(final int n) {
        if (n < 2) {
            return n;
        }
        int i;
        int n2;
        for (i = n, n2 = 1; i != 0; i >>= 2, n2 <<= 1) {}
        while (true) {
            final int n3 = (n / n2 + n2) / 2;
            if (n3 == n2 || n3 == n2 + 1) {
                break;
            }
            n2 = n3;
        }
        return n2;
    }
    
    private int a(int i, final int n) {
        int n2 = 0;
        for (int n3 = n * (n + 1) / 2; i >= n3; i -= n3) {
            n2 += n;
        }
        if (i > 0) {
            int e;
            for (e = this.e(i); e * (e + 1) >= 2 * i; --e) {}
            while (e * (e + 1) < 2 * i) {
                ++e;
            }
            n2 += e;
        }
        return n2;
    }
    
    private void h() {
        this.d = this.f;
    }
    
    private void d() throws IOException {
        this.d = this.h;
        if (this.v >= this.d) {
            this.b(this.m);
            this.g();
        }
    }
    
    private void i(int i) throws IOException {
        this.h();
        this.i = this.l;
        int e = 1;
        while (i > 0) {
            if (e == 1) {
                this.e = 1;
                this.a(this.l);
                --i;
            }
            else if (i >= e) {
                this.e = e;
                this.a(this.s + e - 2);
                i -= e;
            }
            else if (i == 1) {
                ++this.e;
                this.a(this.l);
                i = 0;
            }
            else {
                ++this.e;
                this.a(this.s + i - 2);
                i = 0;
            }
            if (this.v == 0) {
                e = 1;
            }
            else {
                ++e;
            }
        }
        this.d();
    }
    
    private void c(int i) throws IOException {
        if (1 + this.a(i, this.f) < i) {
            this.b(this.m);
            this.g();
            this.i(i);
        }
        else {
            while (i > 0) {
                this.a(this.l);
                --i;
            }
        }
    }
    
    private void f(final int n) throws IOException {
        int i = n / this.e;
        int n2 = n % this.e;
        int n3 = (n2 != 0) ? 1 : 0;
        if (this.v + i + n3 > this.f) {
            i = this.f - this.v;
            n2 = n - i * this.e;
            n3 = 1 + this.a(n2, this.f);
        }
        if (1 + this.a(n, this.f) < i + n3) {
            this.b(this.m);
            this.g();
            this.i(n);
            return;
        }
        this.h();
        while (i > 0) {
            this.a(this.s + this.e - 2);
            --i;
        }
        if (n2 != 0) {
            if (this.j) {
                this.i(n2);
            }
            else if (n2 == 1) {
                this.a(this.l);
            }
            else {
                this.a(this.s + n2 - 2);
            }
        }
        this.d();
    }
    
    private void e() throws IOException {
        if (this.p == 1) {
            this.a(this.l);
            this.p = 0;
            return;
        }
        if (this.j) {
            this.i(this.p);
        }
        else if (this.e < 2 || this.i != this.l) {
            this.c(this.p);
        }
        else {
            this.f(this.p);
        }
        this.p = 0;
    }
    
    static {
        a = new int[] { 0, 16711680, 65280, 255, 65535, 16711935, 16776960, 8388608, 32768, 128, 32896, 8388736, 8421376, 8421504, 12632256, 16777215 };
        q = new int[256];
        ru.zhuk.graphics.codec.a.u = new int[][] { { 8, 184, 248, 216 }, { 120, 56, 152, 88 }, { 40, 232, 24, 200 }, { 168, 104, 136, 72 } };
        ru.zhuk.graphics.codec.a.q[0] = 0;
        int n = 40;
        for (int i = 0; i < 6; ++i) {
            for (int j = 0; j < 6; ++j) {
                for (int k = 0; k < 6; ++k) {
                    ru.zhuk.graphics.codec.a.q[n++] = (3342336 * i | 13056 * j | 51 * k);
                }
            }
        }
        int n2 = 1;
        for (int l = 0; l < 10; ++l) {
            ru.zhuk.graphics.codec.a.q[l + 1] = 1118481 * n2;
            ru.zhuk.graphics.codec.a.q[l + 11] = 17 * n2;
            ru.zhuk.graphics.codec.a.q[l + 21] = 4352 * n2;
            ru.zhuk.graphics.codec.a.q[l + 31] = 1114112 * n2;
            if (++n2 % 3 == 0) {
                ++n2;
            }
        }
    }
}
