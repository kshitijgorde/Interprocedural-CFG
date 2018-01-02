// 
// Decompiled by Procyon v0.5.30
// 

public final class h
{
    private int[] b;
    private float[] g;
    private int[] a;
    private int d;
    private int e;
    private int c;
    private int h;
    private int i;
    public boolean e;
    public boolean b;
    public boolean a;
    private boolean d;
    private boolean c;
    private int f;
    public int b;
    private float[] h;
    private float[] e;
    private float[] a;
    private float[] c;
    private float[] f;
    private float[] d;
    private float[] b;
    private int a;
    private int g;
    private float d;
    private float f;
    private float j;
    private float g;
    private float i;
    private float a;
    private float e;
    private float b;
    private float c;
    private float h;
    private j a;
    
    public final void a() {
        this.e = this.a.b;
        this.c = this.a.k;
        this.h = this.e - 1;
        this.i = this.c - 1;
        this.b = this.a.e;
        this.a = this.a.a;
        this.g = this.a.d;
        this.e = false;
        this.b = false;
        this.b = 0;
        this.d = 0;
        this.c = false;
    }
    
    public final void a(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (n3 != n6 || n3 != 0.0f || n6 != 0.0f || this.a) {
            this.a = true;
            this.b |= 0x8;
        }
        else {
            this.a = false;
            this.b &= 0xFFFFFFF7;
        }
        this.a[0] = n3;
        this.a[1] = n6;
        this.h[0] = n;
        this.h[1] = n4;
        this.e[0] = n2;
        this.e[1] = n5;
    }
    
    public final void a(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final float n7, final float n8) {
        this.b[0] = (n4 * 253.0f + 1.0f) * 65536.0f;
        this.b[1] = (n8 * 253.0f + 1.0f) * 65536.0f;
        if (n4 != 1.0f || n8 != 1.0f) {
            this.b = true;
            this.b |= 0x2;
        }
        else {
            this.b = false;
            this.b &= 0xFFFFFFFD;
        }
        this.c[0] = (n * 253.0f + 1.0f) * 65536.0f;
        this.c[1] = (n5 * 253.0f + 1.0f) * 65536.0f;
        this.f[0] = (n2 * 253.0f + 1.0f) * 65536.0f;
        this.f[1] = (n6 * 253.0f + 1.0f) * 65536.0f;
        this.d[0] = (n3 * 253.0f + 1.0f) * 65536.0f;
        this.d[1] = (n7 * 253.0f + 1.0f) * 65536.0f;
        if (n != n5) {
            this.e = true;
            this.b |= 0x1;
            return;
        }
        if (n2 != n6) {
            this.e = true;
            this.b |= 0x1;
            return;
        }
        if (n3 != n7) {
            this.e = true;
            this.b |= 0x1;
            return;
        }
        this.f = (0xFF000000 | (int)(255.0f * n) << 16 | (int)(255.0f * n2) << 8 | (int)(255.0f * n3));
        this.e = false;
        this.b &= 0xFFFFFFFE;
    }
    
    public final void a(final int d) {
        this.d = d;
        this.c = false;
        if (this.d != -1) {
            this.c = true;
            return;
        }
        this.d = 0;
    }
    
    public final void b() {
        if (this.a.j) {
            this.d = true;
            this.b |= 0x10;
        }
        else {
            this.d = false;
            this.b &= 0xFFFFFFEF;
        }
        if (j.a[6]) {
            final float n = -this.e;
            final float n2 = -this.c;
            final float n3 = this.e * 2;
            final float n4 = this.c * 2;
            if (this.h[1] < n || this.h[1] > n3 || this.h[0] < n || this.h[0] > n3 || this.e[1] < n2 || this.e[1] > n4 || this.e[0] < n2 || this.e[0] > n4) {
                return;
            }
        }
        if (!this.a()) {
            return;
        }
        boolean b = false;
        if (this.h[1] < this.h[0]) {
            final float n5 = this.h[1];
            this.h[1] = this.h[0];
            this.h[0] = n5;
            final float n6 = this.e[1];
            this.e[1] = this.e[0];
            this.e[0] = n6;
            final float n7 = this.c[1];
            this.c[1] = this.c[0];
            this.c[0] = n7;
            final float n8 = this.f[1];
            this.f[1] = this.f[0];
            this.f[0] = n8;
            final float n9 = this.d[1];
            this.d[1] = this.d[0];
            this.d[0] = n9;
            final float n10 = this.a[1];
            this.a[1] = this.a[0];
            this.a[0] = n10;
        }
        int n11 = (int)this.h[1] - (int)this.h[0];
        int n12;
        if (Math.abs(n12 = (int)this.e[1] - (int)this.e[0]) > Math.abs(n11)) {
            final int n13 = n12;
            n12 = n11;
            n11 = n13;
            b = true;
        }
        int n14;
        int n15;
        int n16;
        if (n11 < 0) {
            this.a = 1;
            this.g = 0;
            n14 = (int)this.h[1];
            n15 = (int)this.e[1];
            n16 = -n11;
        }
        else {
            this.a = 0;
            this.g = 1;
            n14 = (int)this.h[0];
            n15 = (int)this.e[0];
            n16 = n11;
        }
        int n17;
        if (n16 == 0) {
            n17 = 0;
        }
        else {
            n17 = (n12 << 16) / n11;
        }
        this.d = this.c[this.a];
        this.f = this.f[this.a];
        this.j = this.d[this.a];
        if (this.e) {
            this.e = (this.c[this.g] - this.c[this.a]) / n16;
            this.b = (this.f[this.g] - this.f[this.a]) / n16;
            this.c = (this.d[this.g] - this.d[this.a]) / n16;
        }
        else {
            this.e = 0.0f;
            this.b = 0.0f;
            this.c = 0.0f;
        }
        this.g = this.b[this.a];
        if (this.b) {
            this.h = (this.b[this.g] - this.b[this.a]) / n16;
        }
        else {
            this.h = 0.0f;
        }
        this.i = this.a[this.a];
        this.i -= 0.001f;
        if (this.a) {
            this.a = (this.a[this.g] - this.a[this.a]) / n16;
        }
        else {
            this.a = 0.0f;
        }
        if (n16 == 0) {
            if (this.b) {
                this.a(n14, n15);
                return;
            }
            this.b(n14, n15);
        }
        else {
            if (this.c && this.d) {
                this.a((int)this.h[0], (int)this.h[0]);
                this.a((int)this.h[1], (int)this.h[1]);
                this.a(this.h[0], this.e[0], this.h[1], this.e[1]);
                return;
            }
            if (this.d) {
                this.h(n14, n15, n17, n16, b);
                return;
            }
            if (this.b == 0) {
                this.e(n14, n15, n17, n16, b);
                return;
            }
            if (this.b == 2) {
                this.g(n14, n15, n17, n16, b);
                return;
            }
            if (this.b == 1) {
                this.b(n14, n15, n17, n16, b);
                return;
            }
            if (this.b == 3) {
                this.d(n14, n15, n17, n16, b);
                return;
            }
            if (this.b == 8) {
                this.f(n14, n15, n17, n16, b);
                return;
            }
            if (this.b == 10) {
                this.i(n14, n15, n17, n16, b);
                return;
            }
            if (this.b == 9) {
                this.c(n14, n15, n17, n16, b);
                return;
            }
            if (this.b == 11) {
                this.a(n14, n15, n17, n16, b);
            }
        }
    }
    
    public final boolean a() {
        final int a = this.a(this.h[0], this.e[0]);
        final int a2 = this.a(this.h[1], this.e[1]);
        final int n = a | a2;
        if ((a & a2) != 0x0) {
            return false;
        }
        if (n != 0) {
            float n2 = 0.0f;
            float n3 = 1.0f;
            for (int i = 0; i < 4; ++i) {
                if ((n >> i) % 2 == 1) {
                    final float a3 = this.a(this.h[0], this.e[0], this.h[1], this.e[1], i + 1);
                    if ((a >> i) % 2 == 1) {
                        n2 = ((a3 > n2) ? a3 : n2);
                    }
                    else {
                        n3 = ((a3 < n3) ? a3 : n3);
                    }
                }
            }
            if (n2 > n3) {
                return false;
            }
            final float n4 = this.h[0];
            final float n5 = this.e[0];
            this.h[0] = n4 + n2 * (this.h[1] - n4);
            this.e[0] = n5 + n2 * (this.e[1] - n5);
            this.h[1] = n4 + n3 * (this.h[1] - n4);
            this.e[1] = n5 + n3 * (this.e[1] - n5);
            if (this.e) {
                final float n6 = this.c[0];
                this.c[0] = n6 + n2 * (this.c[1] - n6);
                this.c[1] = n6 + n3 * (this.c[1] - n6);
                final float n7 = this.f[0];
                this.f[0] = n7 + n2 * (this.f[1] - n7);
                this.f[1] = n7 + n3 * (this.f[1] - n7);
                final float n8 = this.d[0];
                this.d[0] = n8 + n2 * (this.d[1] - n8);
                this.d[1] = n8 + n3 * (this.d[1] - n8);
            }
            if (this.b) {
                final float n9 = this.b[0];
                this.b[0] = n9 + n2 * (this.b[1] - n9);
                this.b[1] = n9 + n3 * (this.b[1] - n9);
            }
        }
        return true;
    }
    
    private final int a(final float n, final float n2) {
        final int h = this.h;
        final int n3 = ((n2 < 0.0f) ? 8 : 0) | ((n2 > this.i) ? 4 : 0) | ((n < 0.0f) ? 2 : 0);
        boolean b = false;
        if (n > h) {
            b = true;
        }
        return n3 | (b ? 1 : 0);
    }
    
    private final float a(final float n, final float n2, final float n3, final float n4, final int n5) {
        final int h = this.h;
        final int i = this.i;
        switch (n5) {
            case 4: {
                return (0.0f - n2) / (n4 - n2);
            }
            case 3: {
                return (i - n2) / (n4 - n2);
            }
            case 2: {
                return (0.0f - n) / (n3 - n);
            }
            case 1: {
                return (h - n) / (n3 - n);
            }
            default: {
                return -1.0f;
            }
        }
    }
    
    private final void b(final int n, final int n2) {
        final float i = this.i;
        final int n3 = n2 * this.e + n;
        if (i <= this.g[n3]) {
            this.b[n3] = this.f;
            this.g[n3] = i;
        }
    }
    
    private final void a(final int n, final int n2) {
        final int n3 = (int)this.b[0];
        final int n4 = this.f & 0xFF0000;
        final int n5 = this.f & 0xFF00;
        final int n6 = this.f & 0xFF;
        final float i = this.i;
        final int n7 = n2 * this.e + n;
        if (i <= this.g[n7]) {
            final int n8 = n3 >> 16;
            final int n10;
            final int n9 = (n10 = this.b[n7]) & 0xFF00;
            final int n11 = n10 & 0xFF;
            final int n12 = n10 & 0xFF0000;
            this.b[n7] = (0xFF000000 | (n12 + ((n4 - n12) * n8 >> 8) & 0xFF0000) | (n9 + ((n5 - n9) * n8 >> 8) & 0xFF00) | (n11 + ((n6 - n11) * n8 >> 8) & 0xFF));
        }
    }
    
    private final void e(int i, int j, final int n, int n2, final boolean b) {
        if (b) {
            n2 += j;
            int n3 = 32768 + (i << 16);
            while (j <= n2) {
                final int n4 = j * this.e + (n3 >> 16);
                this.b[n4] = this.f;
                this.g[n4] = this.i;
                n3 += n;
                ++j;
            }
            return;
        }
        n2 += i;
        int n5 = 32768 + (j << 16);
        while (i <= n2) {
            final int n6 = (n5 >> 16) * this.e + i;
            this.b[n6] = this.f;
            this.g[n6] = this.i;
            n5 += n;
            ++i;
        }
    }
    
    private final void g(int i, int j, final int n, int n2, final boolean b) {
        final int n3 = this.f & 0xFF0000;
        final int n4 = this.f & 0xFF00;
        final int n5 = this.f & 0xFF;
        int n6 = (int)this.g;
        if (b) {
            n2 += j;
            int n7 = 32768 + (i << 16);
            while (j <= n2) {
                final int n8 = j * this.e + (n7 >> 16);
                final int n9 = n6 >> 16;
                final int n11;
                final int n10 = (n11 = this.b[n8]) & 0xFF00;
                final int n12 = n11 & 0xFF;
                final int n13 = n11 & 0xFF0000;
                this.b[n8] = (0xFF000000 | (n13 + ((n3 - n13) * n9 >> 8) & 0xFF0000) | (n10 + ((n4 - n10) * n9 >> 8) & 0xFF00) | (n12 + ((n5 - n12) * n9 >> 8) & 0xFF));
                this.g[n8] = this.i;
                n6 += (int)this.h;
                n7 += n;
                ++j;
            }
            return;
        }
        n2 += i;
        int n14 = 32768 + (j << 16);
        while (i <= n2) {
            final int n15 = (n14 >> 16) * this.e + i;
            final int n16 = n6 >> 16;
            final int n18;
            final int n17 = (n18 = this.b[n15]) & 0xFF00;
            final int n19 = n18 & 0xFF;
            final int n20 = n18 & 0xFF0000;
            this.b[n15] = (0xFF000000 | (n20 + ((n3 - n20) * n16 >> 8) & 0xFF0000) | (n17 + ((n4 - n17) * n16 >> 8) & 0xFF00) | (n19 + ((n5 - n19) * n16 >> 8) & 0xFF));
            this.g[n15] = this.i;
            n6 += (int)this.h;
            n14 += n;
            ++i;
        }
    }
    
    private final void b(int i, int j, final int n, int n2, final boolean b) {
        int n3 = (int)this.d;
        int n4 = (int)this.f;
        int n5 = (int)this.j;
        if (b) {
            n2 += j;
            int n6 = 32768 + (i << 16);
            while (j <= n2) {
                final int n7 = j * this.e + (n6 >> 16);
                this.b[n7] = (0xFF000000 | ((n3 & 0xFF0000) | (n4 >> 8 & 0xFF00) | n5 >> 16));
                this.g[n7] = this.i;
                n3 += (int)this.e;
                n4 += (int)this.b;
                n5 += (int)this.c;
                n6 += n;
                ++j;
            }
            return;
        }
        n2 += i;
        int n8 = 32768 + (j << 16);
        while (i <= n2) {
            final int n9 = (n8 >> 16) * this.e + i;
            this.b[n9] = (0xFF000000 | ((n3 & 0xFF0000) | (n4 >> 8 & 0xFF00) | n5 >> 16));
            this.g[n9] = this.i;
            n3 += (int)this.e;
            n4 += (int)this.b;
            n5 += (int)this.c;
            n8 += n;
            ++i;
        }
    }
    
    private final void d(int i, int j, final int n, int n2, final boolean b) {
        int n3 = (int)this.d;
        int n4 = (int)this.f;
        int n5 = (int)this.j;
        int n6 = (int)this.g;
        if (b) {
            n2 += j;
            int n7 = 32768 + (i << 16);
            while (j <= n2) {
                final int n8 = j * this.e + (n7 >> 16);
                final int n9 = n3 & 0xFF0000;
                final int n10 = n4 >> 8 & 0xFF00;
                final int n11 = n5 >> 16;
                final int n13;
                final int n12 = (n13 = this.b[n8]) & 0xFF00;
                final int n14 = n13 & 0xFF;
                final int n15 = n13 & 0xFF0000;
                final int n16 = n6 >> 16;
                this.b[n8] = (0xFF000000 | (n15 + ((n9 - n15) * n16 >> 8) & 0xFF0000) | (n12 + ((n10 - n12) * n16 >> 8) & 0xFF00) | (n14 + ((n11 - n14) * n16 >> 8) & 0xFF));
                this.g[n8] = this.i;
                n3 += (int)this.e;
                n4 += (int)this.b;
                n5 += (int)this.c;
                n6 += (int)this.h;
                n7 += n;
                ++j;
            }
            return;
        }
        n2 += i;
        int n17 = 32768 + (j << 16);
        while (i <= n2) {
            final int n18 = (n17 >> 16) * this.e + i;
            final int n19 = n3 & 0xFF0000;
            final int n20 = n4 >> 8 & 0xFF00;
            final int n21 = n5 >> 16;
            final int n23;
            final int n22 = (n23 = this.b[n18]) & 0xFF00;
            final int n24 = n23 & 0xFF;
            final int n25 = n23 & 0xFF0000;
            final int n26 = n6 >> 16;
            this.b[n18] = (0xFF000000 | (n25 + ((n19 - n25) * n26 >> 8) & 0xFF0000) | (n22 + ((n20 - n22) * n26 >> 8) & 0xFF00) | (n24 + ((n21 - n24) * n26 >> 8) & 0xFF));
            this.g[n18] = this.i;
            n3 += (int)this.e;
            n4 += (int)this.b;
            n5 += (int)this.c;
            n6 += (int)this.h;
            n17 += n;
            ++i;
        }
    }
    
    private final void f(int i, int j, final int n, int n2, final boolean b) {
        float k = this.i;
        if (b) {
            n2 += j;
            int n3 = 32768 + (i << 16);
            while (j <= n2) {
                final int n4 = j * this.e + (n3 >> 16);
                if (k <= this.g[n4]) {
                    this.b[n4] = this.f;
                    this.g[n4] = k;
                }
                k += this.a;
                n3 += n;
                ++j;
            }
            return;
        }
        n2 += i;
        int n5 = 32768 + (j << 16);
        while (i <= n2) {
            final int n6 = (n5 >> 16) * this.e + i;
            if (k <= this.g[n6]) {
                this.b[n6] = this.f;
                this.g[n6] = k;
            }
            k += this.a;
            n5 += n;
            ++i;
        }
    }
    
    private final void i(int i, int j, final int n, int n2, final boolean b) {
        float k = this.i;
        final int n3 = this.f & 0xFF0000;
        final int n4 = this.f & 0xFF00;
        final int n5 = this.f & 0xFF;
        int n6 = (int)this.g;
        if (b) {
            n2 += j;
            int n7 = 32768 + (i << 16);
            while (j <= n2) {
                final int n8 = j * this.e + (n7 >> 16);
                if (k <= this.g[n8]) {
                    final int n9 = n6 >> 16;
                    final int n11;
                    final int n10 = (n11 = this.b[n8]) & 0xFF00;
                    final int n12 = n11 & 0xFF;
                    final int n13 = n11 & 0xFF0000;
                    this.b[n8] = (0xFF000000 | (n13 + ((n3 - n13) * n9 >> 8) & 0xFF0000) | (n10 + ((n4 - n10) * n9 >> 8) & 0xFF00) | (n12 + ((n5 - n12) * n9 >> 8) & 0xFF));
                }
                k += this.a;
                n6 += (int)this.h;
                n7 += n;
                ++j;
            }
            return;
        }
        n2 += i;
        int n14 = 32768 + (j << 16);
        while (i <= n2) {
            final int n15 = (n14 >> 16) * this.e + i;
            if (k <= this.g[n15]) {
                final int n16 = n6 >> 16;
                final int n18;
                final int n17 = (n18 = this.b[n15]) & 0xFF00;
                final int n19 = n18 & 0xFF;
                final int n20 = n18 & 0xFF0000;
                this.b[n15] = (0xFF000000 | (n20 + ((n3 - n20) * n16 >> 8) & 0xFF0000) | (n17 + ((n4 - n17) * n16 >> 8) & 0xFF00) | (n19 + ((n5 - n19) * n16 >> 8) & 0xFF));
            }
            k += this.a;
            n6 += (int)this.h;
            n14 += n;
            ++i;
        }
    }
    
    private final void c(int i, int j, final int n, int n2, final boolean b) {
        float k = this.i;
        int n3 = (int)this.d;
        int n4 = (int)this.f;
        int n5 = (int)this.j;
        if (b) {
            n2 += j;
            int n6 = 32768 + (i << 16);
            while (j <= n2) {
                final int n7 = j * this.e + (n6 >> 16);
                if (k <= this.g[n7]) {
                    this.b[n7] = (0xFF000000 | ((n3 & 0xFF0000) | (n4 >> 8 & 0xFF00) | n5 >> 16));
                    this.g[n7] = k;
                }
                k += this.a;
                n3 += (int)this.e;
                n4 += (int)this.b;
                n5 += (int)this.c;
                n6 += n;
                ++j;
            }
            return;
        }
        n2 += i;
        int n8 = 32768 + (j << 16);
        while (i <= n2) {
            final int n9 = (n8 >> 16) * this.e + i;
            if (k <= this.g[n9]) {
                this.b[n9] = (0xFF000000 | ((n3 & 0xFF0000) | (n4 >> 8 & 0xFF00) | n5 >> 16));
                this.g[n9] = k;
            }
            k += this.a;
            n3 += (int)this.e;
            n4 += (int)this.b;
            n5 += (int)this.c;
            n8 += n;
            ++i;
        }
    }
    
    private final void a(int i, int j, final int n, int n2, final boolean b) {
        float k = this.i;
        int n3 = (int)this.d;
        int n4 = (int)this.f;
        int n5 = (int)this.j;
        int n6 = (int)this.g;
        if (b) {
            n2 += j;
            int n7 = 32768 + (i << 16);
            while (j <= n2) {
                final int n8 = j * this.e + (n7 >> 16);
                if (k <= this.g[n8]) {
                    final int n9 = n3 & 0xFF0000;
                    final int n10 = n4 >> 8 & 0xFF00;
                    final int n11 = n5 >> 16;
                    final int n13;
                    final int n12 = (n13 = this.b[n8]) & 0xFF00;
                    final int n14 = n13 & 0xFF;
                    final int n15 = n13 & 0xFF0000;
                    final int n16 = n6 >> 16;
                    this.b[n8] = (0xFF000000 | (n15 + ((n9 - n15) * n16 >> 8) & 0xFF0000) | (n12 + ((n10 - n12) * n16 >> 8) & 0xFF00) | (n14 + ((n11 - n14) * n16 >> 8) & 0xFF));
                    this.g[n8] = k;
                }
                k += this.a;
                n3 += (int)this.e;
                n4 += (int)this.b;
                n5 += (int)this.c;
                n6 += (int)this.h;
                n7 += n;
                ++j;
            }
            return;
        }
        n2 += i;
        int n17 = 32768 + (j << 16);
        while (i <= n2) {
            final int n18 = (n17 >> 16) * this.e + i;
            if (k <= this.g[n18]) {
                final int n19 = n3 & 0xFF0000;
                final int n20 = n4 >> 8 & 0xFF00;
                final int n21 = n5 >> 16;
                final int n23;
                final int n22 = (n23 = this.b[n18]) & 0xFF00;
                final int n24 = n23 & 0xFF;
                final int n25 = n23 & 0xFF0000;
                final int n26 = n6 >> 16;
                this.b[n18] = (0xFF000000 | (n25 + ((n19 - n25) * n26 >> 8) & 0xFF0000) | (n22 + ((n20 - n22) * n26 >> 8) & 0xFF00) | (n24 + ((n21 - n24) * n26 >> 8) & 0xFF));
                this.g[n18] = k;
            }
            k += this.a;
            n3 += (int)this.e;
            n4 += (int)this.b;
            n5 += (int)this.c;
            n6 += (int)this.h;
            n17 += n;
            ++i;
        }
    }
    
    public final void h(final int n, final int n2, final int n3, final int n4, final boolean b) {
        float i = this.i;
        int n5 = (int)this.d;
        int n6 = (int)this.f;
        int n7 = (int)this.j;
        int n8 = (int)this.g;
        if (b) {
            int n9 = n << 16;
            int n10 = n2 << 16;
            while (n10 >> 16 < n4 + n2) {
                final int n11 = (n10 >> 16) * this.e + (n9 >> 16);
                final int n12 = n5 & 0xFF0000;
                final int n13 = n6 >> 8 & 0xFF00;
                final int n14 = n7 >> 16;
                if (i <= this.g[n11]) {
                    final int n15 = (~n9 >> 8 & 0xFF) * (n8 >> 16) >> 8;
                    final int n17;
                    final int n16 = (n17 = this.b[n11]) & 0xFF00;
                    final int n18 = n17 & 0xFF;
                    final int n19 = n17 & 0xFF0000;
                    this.b[n11] = (0xFF000000 | (n19 + ((n12 - n19) * n15 >> 8) & 0xFF0000) | (n16 + ((n13 - n16) * n15 >> 8) & 0xFF00) | (n18 + ((n14 - n18) * n15 >> 8) & 0xFF));
                    this.g[n11] = i;
                }
                final int n20;
                if ((n20 = (n9 >> 16) + 1) >= this.e) {
                    n9 += n3;
                    n10 += 65536;
                }
                else {
                    final int n21 = (n10 >> 16) * this.e + n20;
                    if (i <= this.g[n21]) {
                        final int n22 = (n9 >> 8 & 0xFF) * (n8 >> 16) >> 8;
                        final int n24;
                        final int n23 = (n24 = this.b[n21]) & 0xFF00;
                        final int n25 = n24 & 0xFF;
                        final int n26 = n24 & 0xFF0000;
                        this.b[n21] = (0xFF000000 | (n26 + ((n12 - n26) * n22 >> 8) & 0xFF0000) | (n23 + ((n13 - n23) * n22 >> 8) & 0xFF00) | (n25 + ((n14 - n25) * n22 >> 8) & 0xFF));
                        this.g[n21] = i;
                    }
                    n9 += n3;
                    n10 += 65536;
                    i += this.a;
                    n5 += (int)this.e;
                    n6 += (int)this.b;
                    n7 += (int)this.c;
                    n8 += (int)this.h;
                }
            }
            return;
        }
        int n27 = n << 16;
        int n28 = n2 << 16;
        while (n27 >> 16 < n4 + n) {
            final int n29 = (n28 >> 16) * this.e + (n27 >> 16);
            final int n30 = n5 & 0xFF0000;
            final int n31 = n6 >> 8 & 0xFF00;
            final int n32 = n7 >> 16;
            if (i <= this.g[n29]) {
                final int n33 = (~n28 >> 8 & 0xFF) * (n8 >> 16) >> 8;
                final int n35;
                final int n34 = (n35 = this.b[n29]) & 0xFF00;
                final int n36 = n35 & 0xFF;
                final int n37 = n35 & 0xFF0000;
                this.b[n29] = (0xFF000000 | (n37 + ((n30 - n37) * n33 >> 8) & 0xFF0000) | (n34 + ((n31 - n34) * n33 >> 8) & 0xFF00) | (n36 + ((n32 - n36) * n33 >> 8) & 0xFF));
                this.g[n29] = i;
            }
            final int n38;
            if ((n38 = (n28 >> 16) + 1) >= this.c) {
                n27 += 65536;
                n28 += n3;
            }
            else {
                final int n39 = n38 * this.e + (n27 >> 16);
                if (i <= this.g[n39]) {
                    final int n40 = (n28 >> 8 & 0xFF) * (n8 >> 16) >> 8;
                    final int n42;
                    final int n41 = (n42 = this.b[n39]) & 0xFF00;
                    final int n43 = n42 & 0xFF;
                    final int n44 = n42 & 0xFF0000;
                    this.b[n39] = (0xFF000000 | (n44 + ((n30 - n44) * n40 >> 8) & 0xFF0000) | (n41 + ((n31 - n41) * n40 >> 8) & 0xFF00) | (n43 + ((n32 - n43) * n40 >> 8) & 0xFF));
                    this.g[n39] = i;
                }
                n27 += 65536;
                n28 += n3;
                i += this.a;
                n5 += (int)this.e;
                n6 += (int)this.b;
                n7 += (int)this.c;
                n8 += (int)this.h;
            }
        }
    }
    
    private final void a(double n, double n2, double n3, double n4) {
        double n5 = n3 - n;
        double n6 = n4 - n2;
        final double n7 = (n5 >= 0.0) ? n5 : (-n5);
        final double n8 = (n6 >= 0.0) ? n6 : (-n6);
        if (n7 < 1.0E-4 && n8 < 1.0E-4) {
            return;
        }
        if (n7 > n8) {
            if (n > n3) {
                final double n9 = n;
                n = n3;
                n3 = n9;
                final double n10 = n2;
                n2 = n4;
                n4 = n10;
                n5 = n3 - n;
                n6 = n4 - n2;
            }
            final double n11 = n6 / n5;
            int n12;
            if ((n12 = (int)(n + 0.5)) < 0) {
                n12 = 0;
            }
            int e;
            if ((e = (int)(n3 + 0.5)) > this.e) {
                e = this.e;
            }
            double n13 = n2 + (n12 + 0.5f - n) * n11;
            for (int i = n12; i < e; ++i, n13 += n11) {
                final int n14;
                if ((n14 = (int)(n13 - 0.5)) >= 0 && n14 < this.i) {
                    final int n16;
                    final int n15 = (n16 = n14 * this.e + i) + this.e;
                    int n17;
                    if (this.a[n16] == this.d) {
                        n17 = this.b[n16];
                    }
                    else {
                        if (this.a[n15] != this.d) {
                            continue;
                        }
                        n17 = this.b[n15];
                    }
                    final int n18 = (int)((n13 - 0.5) * 256.0) & 0xFF;
                    final int n19 = 255 - n18;
                    final int n20 = n17 & 0xFF0000;
                    final int n21 = n17 & 0xFF00;
                    final int n22 = n17 & 0xFF;
                    final int n24;
                    final int n23 = (n24 = this.b[n16]) & 0xFF00;
                    final int n25 = n24 & 0xFF;
                    final int n26 = n24 & 0xFF0000;
                    this.b[n16] = (0xFF000000 | (n26 + ((n20 - n26) * n19 >> 8) & 0xFF0000) | (n23 + ((n21 - n23) * n19 >> 8) & 0xFF00) | (n25 + ((n22 - n25) * n19 >> 8) & 0xFF));
                    final int n28;
                    final int n27 = (n28 = this.b[n15]) & 0xFF00;
                    final int n29 = n28 & 0xFF;
                    final int n30 = n28 & 0xFF0000;
                    this.b[n15] = (0xFF000000 | (n30 + ((n20 - n30) * n18 >> 8) & 0xFF0000) | (n27 + ((n21 - n27) * n18 >> 8) & 0xFF00) | (n29 + ((n22 - n29) * n18 >> 8) & 0xFF));
                }
            }
            return;
        }
        if (n2 > n4) {
            final double n31 = n;
            n = n3;
            n3 = n31;
            final double n32 = n2;
            n2 = n4;
            n4 = n32;
            n5 = n3 - n;
            n6 = n4 - n2;
        }
        final double n33 = n5 / n6;
        int n34;
        if ((n34 = (int)(n2 + 0.5)) < 0) {
            n34 = 0;
        }
        int c;
        if ((c = (int)(n4 + 0.5)) > this.c) {
            c = this.c;
        }
        double n35 = n + (n34 + 0.5f - n2) * n33;
        final int n36 = n34 * this.e;
        for (int n37 = c * this.e, j = n36; j < n37; j += this.e, n35 += n33) {
            final int n38;
            if ((n38 = (int)(n35 - 0.5)) >= 0 && n38 < this.h) {
                final int n40;
                final int n39 = (n40 = j + n38) + 1;
                int n41;
                if (this.a[n40] == this.d) {
                    n41 = this.b[n40];
                }
                else {
                    if (this.a[n39] != this.d) {
                        continue;
                    }
                    n41 = this.b[n39];
                }
                final int n42 = n41 & 0xFF0000;
                final int n43 = n41 & 0xFF00;
                final int n44 = n41 & 0xFF;
                final int n45 = 255 - ((int)((n35 - 0.5) * 256.0) & 0xFF);
                final int n47;
                final int n46 = (n47 = this.b[n40]) & 0xFF00;
                final int n48 = n47 & 0xFF;
                final int n49 = n47 & 0xFF0000;
                this.b[n40] = (0xFF000000 | (n49 + ((n42 - n49) * n45 >> 8) & 0xFF0000) | (n46 + ((n43 - n46) * n45 >> 8) & 0xFF00) | (n48 + ((n44 - n48) * n45 >> 8) & 0xFF));
            }
        }
    }
    
    public h(final j a) {
        this.a = false;
        this.h = new float[2];
        this.e = new float[2];
        this.a = new float[2];
        this.c = new float[2];
        this.f = new float[2];
        this.d = new float[2];
        this.b = new float[2];
        this.a = a;
    }
}
