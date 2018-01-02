// 
// Decompiled by Procyon v0.5.30
// 

public class _zd
{
    public int p;
    public int d;
    public int a;
    public int n;
    public int v;
    public int i;
    public int l;
    public boolean p;
    public boolean d;
    public double p;
    public double d;
    public double a;
    public double n;
    public double v;
    public double i;
    public double l;
    public int[] p;
    public int[] d;
    public int[] a;
    public int[] n;
    public int b;
    
    public final synchronized double p(final int n, final int n2) {
        if (this.i == 0.0 || this.n == 0) {
            return 3.0;
        }
        double n3 = 2.0 * Math.atan2(n / 2.0, n2 / 2.0 / Math.tan(Math.atan(this.v / 2.0 / this.i)));
        final double l = this.l;
        final double a = this.a;
        if (this.p && this.p < n3) {
            final int n4 = n / 2;
            this.l = this.p * 180.0 / 3.14159265359;
            do {
                --this.l;
                this.a = Math.atan2(n2 / 2, n4 / Math.tan(this.l / 2.0 * 3.14159265359 / 180.0)) * 2.0;
            } while (this.p() > this.p / 2.0 * 180.0 / 3.14159265359);
            n3 = this.l * 3.14159265359 / 180.0;
        }
        this.l = l;
        this.a = a;
        return n3 * 180.0 / 3.14159265359;
    }
    
    public final double d() {
        if (this.d == 0 || this.a == 0 || this.a == null || this.n == null) {
            return 3.0;
        }
        return this.p * 180.0 / 3.14159265359;
    }
    
    public _zd() {
        this.p = false;
        this.d = false;
    }
    
    public final int p(final boolean d) {
        this.d = d;
        return 0;
    }
    
    public final void d(final int n, final int n2) {
        final int n3 = this.n - 1 << 16;
        final int n4 = this.n << 16;
        final int n5 = this.v + 1;
        final int n6 = n2 / this.l;
        final int a = this.a;
        for (int i = 0; i < this.d; ++i) {
            final int n7 = this.n[i * 2];
            final int n8 = this.n[i * 2 + 1];
            final int n9 = this.a[i * 2];
            final int n10 = this.a[i * 2 + 1];
            int n11;
            int n12;
            for (n11 = n + n7, n12 = n + n9; n11 > n4 || n12 > n4; n11 -= n4, n12 -= n4) {}
            final int n13 = (n12 - n11) / a;
            final int n14 = (n10 - n8) / a;
            int j = n8 + (n10 - (n8 + n14 * this.a));
            int k = n11 + (n12 - (n11 + n13 * this.a));
            int n15 = i + (this.a - 1) * this.d;
            if (n12 < 0) {
                while (k >= 0) {
                    final int n16 = k >> 16;
                    final int n17 = j >> 16;
                    final int n18 = k - (n16 << 16) >> 8;
                    final int n19 = j - (n17 << 16) >> 8;
                    final int n20 = (255 - n18) * (255 - n19);
                    final int n21 = (255 - n19) * n18;
                    final int n22 = (255 - n18) * n19;
                    final int n23 = n18 * n19;
                    final int n24 = this.p[n17 * this.i + n16];
                    final int n25 = n24 & 0xFF;
                    final int n26 = (n24 & 0xFF00) >> 8;
                    final int n27 = (n24 & 0xFF0000) >> 16;
                    final int n28 = this.p[n17 * this.i + n16 + 1];
                    final int n29 = n28 & 0xFF;
                    final int n30 = (n28 & 0xFF00) >> 8;
                    final int n31 = (n28 & 0xFF0000) >> 16;
                    final int n32 = this.p[(n17 + 1) * this.i + n16];
                    final int n33 = n32 & 0xFF;
                    final int n34 = (n32 & 0xFF00) >> 8;
                    final int n35 = (n32 & 0xFF0000) >> 16;
                    final int n36 = this.p[(n17 + 1) * this.i + n16 + 1];
                    this.d[n15] = ((n20 * n25 + n21 * n29 + n22 * n33 + n23 * (n36 & 0xFF) & 0xFF0000) >> 16 | (n20 * n26 + n21 * n30 + n22 * n34 + n23 * ((n36 & 0xFF00) >> 8) & 0xFF0000) >> 8 | (n20 * n27 + n21 * n31 + n22 * n35 + n23 * ((n36 & 0xFF0000) >> 16) & 0xFF0000) | 0xFF000000);
                    k += n13;
                    j += n14;
                    n15 -= this.d;
                }
                k += n4;
            }
            if (k < 0) {
                int l;
                for (l = k + n4; l <= n4 - 1; l += n13, j += n14, n15 -= this.d) {
                    final int n37 = l >> 16;
                    final int n38 = j >> 16;
                    final int n39 = l - (n37 << 16) >> 8;
                    final int n40 = j - (n38 << 16) >> 8;
                    final int n41 = (255 - n39) * (255 - n40);
                    final int n42 = (255 - n40) * n39;
                    final int n43 = (255 - n39) * n40;
                    final int n44 = n39 * n40;
                    final int n45 = this.p[n38 * this.i + n37];
                    final int n46 = n45 & 0xFF;
                    final int n47 = (n45 & 0xFF00) >> 8;
                    final int n48 = (n45 & 0xFF0000) >> 16;
                    final int n49 = this.p[n38 * this.i + n37 + 1];
                    final int n50 = n49 & 0xFF;
                    final int n51 = (n49 & 0xFF00) >> 8;
                    final int n52 = (n49 & 0xFF0000) >> 16;
                    final int n53 = this.p[(n38 + 1) * this.i + n37];
                    final int n54 = n53 & 0xFF;
                    final int n55 = (n53 & 0xFF00) >> 8;
                    final int n56 = (n53 & 0xFF0000) >> 16;
                    final int n57 = this.p[(n38 + 1) * this.i + n37 + 1];
                    this.d[n15] = ((n41 * n46 + n42 * n50 + n43 * n54 + n44 * (n57 & 0xFF) & 0xFF0000) >> 16 | (n41 * n47 + n42 * n51 + n43 * n55 + n44 * ((n57 & 0xFF00) >> 8) & 0xFF0000) >> 8 | (n41 * n48 + n42 * n52 + n43 * n56 + n44 * ((n57 & 0xFF0000) >> 16) & 0xFF0000) | 0xFF000000);
                }
                k = l - n4;
            }
            while (j > n10) {
                final int n58 = k >> 16;
                final int n59 = j >> 16;
                final int n60 = k - (n58 << 16) >> 8;
                final int n61 = j - (n59 << 16) >> 8;
                final int n62 = (255 - n60) * (255 - n61);
                final int n63 = (255 - n61) * n60;
                final int n64 = (255 - n60) * n61;
                final int n65 = n60 * n61;
                final int n66 = this.p[n59 * this.i + n58];
                final int n67 = n66 & 0xFF;
                final int n68 = (n66 & 0xFF00) >> 8;
                final int n69 = (n66 & 0xFF0000) >> 16;
                final int n70 = this.p[n59 * this.i + n58 + 1];
                final int n71 = n70 & 0xFF;
                final int n72 = (n70 & 0xFF00) >> 8;
                final int n73 = (n70 & 0xFF0000) >> 16;
                final int n74 = this.p[(n59 + 1) * this.i + n58];
                final int n75 = n74 & 0xFF;
                final int n76 = (n74 & 0xFF00) >> 8;
                final int n77 = (n74 & 0xFF0000) >> 16;
                final int n78 = this.p[(n59 + 1) * this.i + n58 + 1];
                this.d[n15] = ((n62 * n67 + n63 * n71 + n64 * n75 + n65 * (n78 & 0xFF) & 0xFF0000) >> 16 | (n62 * n68 + n63 * n72 + n64 * n76 + n65 * ((n78 & 0xFF00) >> 8) & 0xFF0000) >> 8 | (n62 * n69 + n63 * n73 + n64 * n77 + n65 * ((n78 & 0xFF0000) >> 16) & 0xFF0000) | 0xFF000000);
                k += n13;
                j += n14;
                n15 -= this.d;
            }
        }
    }
    
    public final synchronized int p() {
        if (this.a == 0 || this.d == 0 || this.l <= 0.0) {
            return 1;
        }
        this.a = Math.atan2(this.a / 2, this.p / Math.tan(this.l / 2.0 * 3.14159265359 / 180.0)) * 2.0 * 180.0 / 3.14159265359;
        if (this.a > this.n) {
            this.a = this.n;
        }
        this.v = (this.n - this.a) / 2.0;
        return 0;
    }
    
    public synchronized int p(final int[] d, final int n, double n2, double v, double d2) {
        if (this.d == 0 || this.a == 0 || this.a == null || this.n == null) {
            return 3;
        }
        double n3;
        for (n3 = this.p * 180.0 / 3.14159265359; n2 < 0.0; n2 += n3) {}
        while (n2 > n3) {
            n2 -= n3;
        }
        if (d2 > this.d) {
            d2 = this.d;
        }
        if (d2 < 1.0) {
            d2 = 1.0;
        }
        if (this.l != d2) {
            this.l = d2;
            this.p();
        }
        if (v > this.v) {
            v = this.v;
        }
        else if (v < -this.v) {
            v = -this.v;
        }
        this.d = d;
        this.b = (int)(n2 / (this.p * 180.0 / 3.14159265359) * this.n * 65536.0);
        this.p(v + this.a / 2.0, this.a);
        this.p(v - this.a / 2.0, this.n);
        if (this.d) {
            this.d(this.b, n);
        }
        else {
            this.p(this.b, n);
        }
        return 0;
    }
    
    public final int p(final int d, final int a) {
        if (d == 0 || a == 0 || d > 100000 || a > 100000) {
            return 3;
        }
        this.p = d / 2;
        this.d = d;
        this.a = a;
        this.p();
        if (this.a != null) {
            this.a = null;
        }
        if (this.n != null) {
            this.n = null;
        }
        this.a = new int[this.d * 2];
        if (this.a == null) {
            return 2;
        }
        this.n = new int[this.d * 2];
        if (this.n == null) {
            return 2;
        }
        return 0;
    }
    
    public final void p(final int n, final int n2) {
        final int n3 = this.n - 1 << 16;
        final int n4 = this.n << 16;
        final int n5 = this.v + 1;
        final int n6 = n2 / this.l;
        final int a = this.a;
        for (int i = 0; i < this.d; ++i) {
            final int n7 = this.n[i * 2];
            final int n8 = this.n[i * 2 + 1];
            final int n9 = this.a[i * 2];
            final int n10 = this.a[i * 2 + 1];
            int n11;
            int n12;
            for (n11 = n + n7, n12 = n + n9; n11 > n4 || n12 > n4; n11 -= n4, n12 -= n4) {}
            final int n13 = (n12 - n11) / a;
            final int n14 = (n10 - n8) / a;
            int j = n8 + (n10 - (n8 + n14 * this.a));
            int k = n11 + (n12 - (n11 + n13 * this.a));
            int n15 = i + (this.a - 1) * this.d;
            if (n12 < 0) {
                while (k >= 0) {
                    this.d[n15] = this.p[(j >> 16) * this.i + (k >> 16)];
                    k += n13;
                    j += n14;
                    n15 -= this.d;
                }
                k += n4;
            }
            if (k < 0) {
                int l;
                for (l = k + n4; l < n4 - 1; l += n13, j += n14, n15 -= this.d) {
                    this.d[n15] = this.p[(j >> 16) * this.i + (l >> 16)];
                }
                k = l - n4;
            }
            while (j > n10) {
                this.d[n15] = this.p[(j >> 16) * this.i + (k >> 16)];
                k += n13;
                j += n14;
                n15 -= this.d;
            }
        }
    }
    
    public final int p(final double l) {
        this.l = l;
        this.p();
        return 0;
    }
    
    public final void p(final double n, final int[] array) {
        final double tan = Math.tan(n * 3.14159265359 / 180.0);
        final int n2 = this.v << 16;
        final int n3 = n2 / 2;
        final double n4 = this.i / Math.cos(this.a / 2.0 * 3.14159265359 / 180.0) * Math.cos(n * 3.14159265359 / 180.0);
        final double n5 = Math.tan(this.l / 2.0 * 3.14159265359 / 180.0) * this.i * 2.0 / this.d;
        final int n6 = (int)(this.i * 65536.0);
        int n7 = this.p * 2;
        int p2;
        double n8;
        if ((this.d & 0x1) == 0x0) {
            p2 = this.p;
            n8 = n5 / 2.0;
        }
        else {
            p2 = this.p + 1;
            n8 = 0.0;
        }
        for (int i = 0; i < p2; ++i) {
            final double atan2 = Math.atan2(n8, n4);
            n8 += n5;
            array[n7++] = (int)(atan2 * n6);
            array[n7++] = n2 - ((int)(n6 * Math.cos(atan2) * tan) + n3);
        }
        int n9 = 0;
        int n10 = (this.d - 1) * 2;
        for (int j = 0; j < this.p; ++j) {
            array[n9++] = -array[n10];
            array[n9++] = array[n10 + 1];
            n10 -= 2;
        }
    }
    
    public final synchronized int p(final int[] p8, final int n, final int n2, final int i, final int n3, final int n4, final int n5, final double n6) {
        if (p8 == null) {
            return 1;
        }
        if (n > 100000 || n2 > 100000 || i > 100000 || n3 > 100000 || n4 > 100000 || n <= 0 || n2 <= 0 || n3 <= 0 || n4 <= 0 || n6 < 0.0) {
            return 1;
        }
        if (n6 != 360.0) {
            this.p = true;
        }
        this.p = n6 * 3.14159265359 / 180.0;
        this.p = p8;
        this.v = n2 - 1;
        this.n = n - 1;
        this.i = i;
        this.l = n5 / 8;
        this.d = 179.0;
        this.l = this.d;
        this.i = this.n / this.p;
        this.n = 2.0 * Math.atan(this.v / 2.0 / this.i);
        this.n = this.n * 180.0 / 3.14159265359;
        this.p(n3, n4);
        return 0;
    }
    
    public synchronized double p() {
        if (this.v == -1.0) {
            return 3.0;
        }
        if (!this.p) {
            return 0.0;
        }
        return Math.atan2(Math.tan(this.l / 2.0 * 3.14159265359 / 180.0) * this.i, this.i / Math.cos(this.a / 2.0 * 3.14159265359 / 180.0) * Math.cos(this.n / 2.0 * 3.14159265359 / 180.0)) * 180.0 / 3.14159265359;
    }
    
    public final boolean p() {
        return this.p;
    }
    
    public final synchronized double p(final double l) {
        if (this.d == 0 || this.a == 0 || this.a == null || this.n == null || this.i == 0.0 || this.n == 0) {
            return 3.0;
        }
        if (l == 0.0) {
            return 1.0;
        }
        this.n = 2.0 * Math.atan(this.v / 2.0 / this.i);
        this.n = this.n * 180.0 / 3.14159265359;
        this.a = Math.atan2(this.a / 2, this.p / Math.tan(l / 2.0 * 3.14159265359 / 180.0)) * 2.0 * 180.0 / 3.14159265359;
        if (this.a > this.n) {
            this.a = this.n;
        }
        this.v = (this.n - this.a) / 2.0;
        this.l = l;
        return this.v;
    }
}
