import java.io.OutputStream;
import java.io.IOException;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ag
{
    private aE a;
    private au a;
    public boolean a;
    private int[] e;
    public int[] a;
    private int[] f;
    public int a;
    public int b;
    public int c;
    public int d;
    private int j;
    public int e;
    public int f;
    public int g;
    private int k;
    private int l;
    private boolean f;
    public int[] b;
    public int h;
    public int[] c;
    private boolean g;
    private int[] g;
    public boolean b;
    public boolean c;
    public boolean d;
    private int m;
    public int[] d;
    public int i;
    public boolean e;
    
    public ag(final au a, final aE a2) {
        this.a = false;
        this.e = new int[272];
        this.a = new int[32];
        this.f = new int[280];
        this.a = 0;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.j = 4096;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.k = 0;
        this.l = 0;
        this.f = false;
        this.b = new int[256];
        this.h = 0;
        this.c = new int[16384];
        this.g = false;
        this.g = new int[12];
        this.b = false;
        this.c = false;
        this.d = false;
        this.m = 1;
        this.i = 0;
        this.e = false;
        this.a = a2;
        this.a = a;
    }
    
    private final void e() {
        int n = this.e & 0x1F;
        final int n2 = (this.e & 0x3E0) >> 5;
        int n3 = 8192 + (this.e & 0xFFF);
        int n4 = 8192 + (this.e & 0xC00) + 960 + ((n2 & 0xFFFC) << 1) + (n >> 2);
        int n5;
        if ((n2 & 0x2) == 0x0) {
            if ((n & 0x2) == 0x0) {
                n5 = (this.b(n4) & 0x3) << 2;
            }
            else {
                n5 = (this.b(n4) & 0xC);
            }
        }
        else if ((n & 0x2) == 0x0) {
            n5 = (this.b(n4) & 0x30) >> 2;
        }
        else {
            n5 = (this.b(n4) & 0xC0) >> 4;
        }
        int n6 = -this.g;
        int n7 = -this.g;
        for (int i = 0; i < 33; ++i) {
            final int a;
            if ((a = this.a.a.a(1, n3 & 0x3FF)) != 0) {
                n5 = (a & 0xC);
            }
            final int n8 = this.d + (this.b(n3) << 4) + ((this.e & 0x7000) >> 12);
            final int b = this.b(n8);
            final int b2 = this.b(n8 + 8);
            if (this.e) {
                this.a.a.a(n8);
            }
            for (int j = 128; j > 0; j >>= 1) {
                int n9 = n5;
                if ((b & j) != 0x0) {
                    n9 |= 0x1;
                }
                if ((b2 & j) != 0x0) {
                    n9 |= 0x2;
                }
                if ((n9 & 0x3) != 0x0) {
                    if (n7 > 0) {
                        this.f[n7] = 1;
                    }
                    if (n6 > 0) {
                        this.e[n6] = n9;
                    }
                }
                else {
                    if (n7 > 0) {
                        this.f[n7] = 0;
                    }
                    if (n6 > 0) {
                        this.e[n6] = 0;
                    }
                }
                ++n7;
                ++n6;
            }
            ++n;
            ++n3;
            if ((n & 0x1) == 0x0) {
                if ((n & 0x3) == 0x0) {
                    if ((n & 0x1F) == 0x0) {
                        n3 ^= 0x400;
                        n4 ^= 0x400;
                        n3 -= 32;
                        n4 -= 8;
                        n -= 32;
                    }
                    ++n4;
                }
                if ((n2 & 0x2) == 0x0) {
                    if ((n & 0x2) == 0x0) {
                        n5 = (this.b(n4) & 0x3) << 2;
                    }
                    else {
                        n5 = (this.b(n4) & 0xC);
                    }
                }
                else if ((n & 0x2) == 0x0) {
                    n5 = (this.b(n4) & 0x30) >> 2;
                }
                else {
                    n5 = (this.b(n4) & 0xC0) >> 4;
                }
            }
        }
        if ((this.b & 0x2) == 0x0) {
            for (int k = 0; k < 8; ++k) {
                this.e[k] = 64;
                this.e[255 - k] = 64;
            }
        }
    }
    
    private final void d(final int n) {
        int n2 = 0;
        final int n3 = ((this.a & 0x20) != 0x0) ? 16 : 8;
        this.c &= 0xDF;
        this.a.a.a(0, 0);
        for (int i = 0; i < 64; ++i) {
            final int n4 = this.b[i * 4] + 1;
            final int n5;
            if ((n5 = n - n4) >= 0) {
                if (n5 < n3) {
                    if (++n2 >= 8) {
                        this.c |= 0x20;
                    }
                    final int n6 = this.b[i * 4 + 1];
                    final int n7 = this.b[i * 4 + 2];
                    final int n8 = this.b[i * 4 + 3];
                    final boolean b = (n7 & 0x80) != 0x0;
                    final boolean b2 = (n7 & 0x40) != 0x0;
                    final boolean b3 = (n7 & 0x20) != 0x0;
                    int n9 = 0;
                    if ((n7 & 0x2) != 0x0) {
                        n9 = 8;
                    }
                    if ((n7 & 0x1) != 0x0) {
                        n9 |= 0x4;
                    }
                    int n10 = 0;
                    int n11 = 8;
                    if (n8 + 7 > 255) {
                        n11 = 8 - (n8 + 7 - 255);
                    }
                    int n12 = n - n4;
                    int n13 = n8 + 0;
                    int n14 = n8 + 0;
                    int n15 = 1;
                    if (b2) {
                        n15 = -1;
                        n10 = 7;
                        n11 = 7 - n11;
                    }
                    if (b) {
                        n12 = n3 - 1 - n12;
                    }
                    if (this.e) {
                        this.a.a.a(n6 << 4);
                    }
                    for (int j = n10; j != n11; j += n15) {
                        if ((this.f[n14] & 0x2) == 0x0) {
                            int n17;
                            int n18;
                            if (n3 == 16) {
                                int n16 = n6 << 4;
                                if ((n6 & 0x1) != 0x0) {
                                    n16 += 4096;
                                    if (n12 < 8) {
                                        n16 -= 16;
                                    }
                                }
                                else if (n12 >= 8) {
                                    n16 += 16;
                                }
                                n17 = n16 + (n12 & 0x7);
                                n18 = 128 >> (j & 0x7);
                            }
                            else {
                                n17 = (n6 << 4) + (n12 & 0x7) + this.j;
                                n18 = 128 >> (j & 0x7);
                            }
                            n9 &= 0xC;
                            if ((this.b(n17) & n18) != 0x0) {
                                n9 |= 0x1;
                            }
                            n17 += 8;
                            if ((this.b(n17) & n18) != 0x0) {
                                n9 |= 0x2;
                            }
                            if ((n9 & 0x3) != 0x0) {
                                if (i == 0 && this.f[n14] == 1) {
                                    this.c |= 0x40;
                                }
                                if ((this.b & 0x4) != 0x0 || (n13 >= 8 && n13 < 248)) {
                                    if (b3) {
                                        final int[] f = this.f;
                                        final int n19 = n14;
                                        f[n19] |= 0x2;
                                        if ((this.f[n14] & 0x1) == 0x0) {
                                            this.e[n13] = 16 + n9;
                                        }
                                    }
                                    else {
                                        this.e[n13] = 16 + n9;
                                        final int[] f2 = this.f;
                                        final int n20 = n14;
                                        f2[n20] |= 0x2;
                                    }
                                }
                            }
                        }
                        ++n13;
                        ++n14;
                    }
                }
            }
        }
    }
    
    public final boolean a() {
        return (this.a & 0x80) != 0x0;
    }
    
    public final void a() {
        for (int i = 0; i < this.e.length; ++i) {
            this.e[i] = 64;
        }
        boolean b;
        if ((this.b & 0x18) != 0x0) {
            this.g();
            b = this.b();
            this.f();
        }
        else {
            b = this.b();
        }
        ++this.i;
        try {
            if (!b) {
                for (int j = 0; j < this.e.length; ++j) {
                    if (this.e[j] != 64) {
                        this.e[j] = (this.a[this.e[j]] & 0x3F);
                    }
                }
                this.a.a.a(this.e);
            }
        }
        catch (Exception ex) {
            this.a.a("Graphics Engine not initialised and PPU write was attempted");
        }
    }
    
    public final void b() {
        this.i = 0;
        if ((this.b & 0x18) != 0x0) {
            this.e = this.f;
        }
    }
    
    private final void f() {
        if ((this.e & 0x7000) != 0x7000) {
            this.e += 4096;
            return;
        }
        this.e &= 0x8FFF;
        if ((this.e & 0x3E0) == 0x3A0) {
            this.e ^= 0x800;
            this.e &= 0xFC1F;
            return;
        }
        if ((this.e & 0x3E0) == 0x3E0) {
            this.e &= 0xFC1F;
            return;
        }
        this.e += 32;
    }
    
    private final void g() {
        this.e &= 0xFBE0;
        this.e |= (this.f & 0x41F);
    }
    
    public final int a(final int n) {
        switch (n & 0x7) {
            case 2: {
                this.c();
                final int c = this.c;
                this.c &= 0x7F;
                return (c & 0xE0) | (this.l & 0x1F);
            }
            case 4: {
                ++this.h;
                this.h &= 0xFF;
                return this.b[this.h];
            }
            case 7: {
                this.l = this.k;
                this.k = this.a();
                return this.l;
            }
            default: {
                return this.l & 0xFF;
            }
        }
    }
    
    private final boolean b() {
        this.f = this.a.a.a(this.i);
        for (int i = 0; i < this.f.length; ++i) {
            this.f[i] = 0;
        }
        if ((this.b & 0x8) != 0x0) {
            this.e();
        }
        if ((this.b & 0x10) != 0x0) {
            this.d(this.i);
        }
        return this.f;
    }
    
    public final void a(final InputStream inputStream) throws IOException {
        for (int i = 0; i < this.a.length; ++i) {
            this.a[i] = (inputStream.read() & 0xFF);
        }
        this.a = aK.c(inputStream);
        this.b = aK.c(inputStream);
        this.c = aK.c(inputStream);
        this.d = aK.a(inputStream);
        this.j = aK.a(inputStream);
        this.e = aK.b(inputStream);
        this.f = aK.b(inputStream);
        this.g = aK.c(inputStream);
        this.k = aK.c(inputStream);
        this.l = aK.c(inputStream);
        for (int j = 0; j < this.g.length; ++j) {
            this.g[j] = aK.b(inputStream);
        }
        for (int k = 0; k < this.c.length; ++k) {
            this.c[k] = aK.c(inputStream);
        }
        this.g = (aK.c(inputStream) == 255);
        this.b = (aK.c(inputStream) == 255);
        this.c = (aK.c(inputStream) == 255);
        this.d = (aK.c(inputStream) == 255);
        this.m = aK.c(inputStream);
        for (int l = 0; l < this.b.length; ++l) {
            this.b[l] = aK.c(inputStream);
        }
        this.h = aK.c(inputStream);
    }
    
    public final void a(final OutputStream outputStream) throws IOException {
        outputStream.write("PPU".getBytes());
        aK.a(outputStream, this.a.length + 3 + 15 + this.g.length * 4 + this.c.length + 5 + this.b.length + 1);
        for (int i = 0; i < this.a.length; ++i) {
            aK.c(outputStream, this.a[i]);
        }
        aK.c(outputStream, this.a);
        aK.c(outputStream, this.b);
        aK.c(outputStream, this.c);
        aK.b(outputStream, this.d);
        aK.b(outputStream, this.j);
        aK.a(outputStream, this.e);
        aK.a(outputStream, this.f);
        aK.c(outputStream, this.g);
        aK.c(outputStream, this.k);
        aK.c(outputStream, this.l);
        for (int j = 0; j < this.g.length; ++j) {
            aK.a(outputStream, this.g[j]);
        }
        for (int k = 0; k < this.c.length; ++k) {
            aK.c(outputStream, this.c[k]);
        }
        outputStream.write(this.g ? 255 : 0);
        outputStream.write(this.b ? 255 : 0);
        outputStream.write(this.c ? 255 : 0);
        outputStream.write(this.d ? 255 : 0);
        aK.c(outputStream, this.m);
        for (int l = 0; l < this.b.length; ++l) {
            aK.c(outputStream, this.b[l]);
        }
        aK.c(outputStream, this.h);
    }
    
    public final void a(int n, final int h) {
        this.l = (h & 0xFF);
        switch ((n = (n & 0x7) + 8192) & 0x7) {
            case 0: {
                this.a = h;
                this.d = (((h & 0x10) != 0x0) ? 4096 : 0);
                this.j = (((h & 0x8) != 0x0) ? 4096 : 0);
                this.m = (((h & 0x4) != 0x0) ? 32 : 1);
                this.f &= 0xF3FF;
                this.f |= (h & 0x3) << 10;
            }
            case 1: {
                if ((this.b & 0xE0) != (h & 0xE0)) {
                    this.a.a.a(this.a.a.a, this.a.a.b, this.a.a.d, h);
                }
                this.b = h;
            }
            case 2: {}
            case 3: {
                this.h = h;
            }
            case 4: {
                this.b[this.h] = (h & 0xFF);
                ++this.h;
                this.h &= 0xFF;
            }
            case 5: {
                this.a(h);
            }
            case 6: {
                this.b(h);
            }
            case 7: {
                this.c(h);
            }
            default: {}
        }
    }
    
    public final void a(final int[] d, final boolean d2, final boolean b) {
        this.a = 0;
        this.b = 0;
        this.c = 0;
        for (int i = 0; i < this.c.length; ++i) {
            this.c[i] = 0;
        }
        this.g[0] = 0;
        this.g[1] = 1024;
        this.g[2] = 2048;
        this.g[3] = 3072;
        this.g[4] = 4096;
        this.g[5] = 5120;
        this.g[6] = 6144;
        this.g[7] = 7168;
        this.g[8] = 8192;
        this.g[9] = 8192;
        this.g[10] = 8192;
        this.g[11] = 8192;
        this.b = b;
        this.d = d2;
        this.c = !d2;
        this.h();
        this.d = d;
        this.h = 0;
        for (int j = 0; j < this.b.length; ++j) {
            this.b[j] = 0;
        }
        this.d = 0;
        this.j = 0;
        this.i = 0;
        this.c();
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.k = 0;
        this.l = 0;
        this.m = 1;
        this.a.a.a(this.a.a.a, this.a.a.b, this.a.a.d, 0);
        this.a = (d.length != 0);
    }
    
    public final int a() {
        final int e = this.e;
        this.e += this.m;
        return this.b(e & 0x3FFF);
    }
    
    public final int b(int n) {
        if (n < 8192) {
            return this.c(n);
        }
        if (n >= 12288) {
            if (n >= 16128) {
                return this.a[n & 0x1F];
            }
            n &= 0xEFFF;
        }
        return this.d(n);
    }
    
    private final int c(int n) {
        n &= 0x1FFF;
        if (this.d.length != 0) {
            return this.d[this.g[n >> 10] + (n & 0x3FF)];
        }
        return this.c[this.g[n >> 10] + (n & 0x3FF)];
    }
    
    public final void c() {
        this.g = false;
    }
    
    public final void a(final int n) {
        this.g = !this.g;
        if (this.g) {
            this.f &= 0xFFE0;
            this.f |= (n & 0xF8) >> 3;
            this.g = (n & 0x7);
            return;
        }
        this.f &= 0xFC1F;
        this.f |= (n & 0xF8) << 2;
        this.f &= 0x8FFF;
        this.f |= (n & 0x7) << 12;
    }
    
    public final void b(final int n) {
        this.g = !this.g;
        if (this.g) {
            this.f &= 0xFF;
            this.f |= (n & 0x3F) << 8;
            return;
        }
        this.f &= 0xFF00;
        this.f |= n;
        this.e = this.f;
    }
    
    private final void h() {
        if (this.b) {
            this.a(0, 1, 2, 3);
            return;
        }
        if (this.c) {
            this.a(0, 0, 1, 1);
            return;
        }
        if (this.d) {
            this.a(0, 1, 0, 1);
            return;
        }
        this.a(0, 1, 2, 3);
    }
    
    public final void a(int n, int n2, int n3, int n4) {
        n &= 0x3;
        n2 &= 0x3;
        n3 &= 0x3;
        n4 &= 0x3;
        this.g[8] = 8192 + (n << 10);
        this.g[9] = 8192 + (n2 << 10);
        this.g[10] = 8192 + (n3 << 10);
        this.g[11] = 8192 + (n4 << 10);
    }
    
    public final void c(final int n) {
        final int e = this.e;
        this.e += this.m;
        int n2;
        if ((n2 = (e & 0x3FFF)) >= 12288) {
            if (n2 >= 16128) {
                if ((n2 & 0xF) == 0x0) {
                    this.a[0] = (n & 0x3F);
                    this.a[16] = (n & 0x3F);
                    return;
                }
                this.a[n2 & 0x1F] = (n & 0x3F);
                return;
            }
            else {
                n2 &= 0xEFFF;
            }
        }
        if (!this.a || n2 >= 8192) {
            this.c[this.g[n2 >> 10] + (n2 & 0x3FF)] = n;
        }
    }
    
    private final int d(final int n) {
        return this.c[this.g[n >> 10] + (n & 0x3FF)];
    }
    
    public final void b(final int n, int n2) {
        if (n >= this.d.length >> 10) {
            return;
        }
        if (n2 >= this.d.length) {
            n2 %= this.d.length;
        }
        this.g[n] = n2;
    }
    
    public final void c(final int n, final int n2) {
        if (n < 8) {
            this.g[n] = 0 + ((n2 & 0xF) << 10);
            return;
        }
        if (n < 12) {
            this.g[n] = 8192 + ((n2 & 0x3) << 10);
        }
    }
    
    public final void d() {
        final aI a = this.a.a;
        for (int i = 0; i < 64; ++i) {
            if (this.b[i * 4] + 1 >= 240) {
                a.a[i] = false;
            }
            else {
                a.a[i] = true;
                final int n = this.b[i * 4 + 1];
                final int n2 = this.b[i * 4 + 2];
                final boolean b = (n2 & 0x80) != 0x0;
                final boolean b2 = (n2 & 0x40) != 0x0;
                int n3 = 16;
                if ((n2 & 0x2) != 0x0) {
                    n3 = 24;
                }
                if ((n2 & 0x1) != 0x0) {
                    n3 |= 0x4;
                }
                final int[] array = { this.a.a.a[this.a[n3 + 0]], this.a.a.a[this.a[n3 + 1]], this.a.a.a[this.a[n3 + 2]], this.a.a.a[this.a[n3 + 3]] };
                for (int j = 0; j < 8; ++j) {
                    int n4 = j;
                    int n5 = 0;
                    int n6 = 8;
                    int n7 = 1;
                    if (b2) {
                        n7 = -1;
                        n5 = 7;
                        n6 = -1;
                    }
                    if (b) {
                        n4 = 7 - n4;
                    }
                    for (int k = n5; k != n6; k += n7) {
                        int n8 = 0;
                        int n9 = (n << 4) + (n4 & 0x7) + this.j;
                        final int n10 = 128 >> (k & 0x7);
                        if ((this.b(n9) & n10) != 0x0) {
                            n8 = 1;
                        }
                        n9 += 8;
                        if ((this.b(n9) & n10) != 0x0) {
                            n8 |= 0x2;
                        }
                        a.a[i][n4 * 8 + k] = array[n8];
                    }
                }
            }
        }
    }
}
