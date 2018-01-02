// 
// Decompiled by Procyon v0.5.30
// 

public class c1 extends am
{
    int a;
    int b;
    int c;
    int d;
    int e;
    char f;
    char g;
    int[] h;
    byte[] i;
    int j;
    boolean k;
    int[] l;
    int m;
    int n;
    long p;
    int q;
    int r;
    byte[] s;
    char[] t;
    int u;
    int v;
    dm[] w;
    char[][] x;
    char[] y;
    int z;
    int[] aa;
    int ab;
    int ac;
    int[] ad;
    int[] ae;
    int af;
    int ag;
    int[] ah;
    static int[] ai;
    static int[] aj;
    static int[] ak;
    static int[] al;
    static boolean am;
    
    public c1() {
        this.h = new int[65];
        this.s = new byte[2048];
        this.t = new char[2048];
        this.y = new char[768];
        this.aa = new int[513];
        this.ad = new int[256];
        this.ae = new int[513];
        this.ah = new int[256];
    }
    
    final int a(final byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        return n;
    }
    
    final char b(final byte b) {
        int n = b;
        if (n < 0) {
            n += 256;
        }
        return (char)n;
    }
    
    int b() throws Exception {
        this.r = 0;
        this.n = this.i.length - this.j;
        if (this.n > 2048) {
            this.n = 2048;
        }
        for (int i = 0; i < this.n; ++i) {
            this.t[i] = this.b(this.i[this.j++]);
        }
        return this.n;
    }
    
    void c() throws Exception {
        this.p = (this.p << 8 & 0xFFFFFFFFL) + ((this.n-- != 0) ? this.t[this.r++] : ((this.b() != 0 && this.n-- != 0) ? this.t[this.r++] : 0L));
    }
    
    int a(final int n, final int n2) throws Exception {
        if (this.q < n) {
            this.c();
            this.q += 8;
        }
        if (this.q < n) {
            this.c();
            this.q += 8;
        }
        return (int)(this.p >> this.q - n & n2);
    }
    
    void a(final int n) {
        this.q -= n;
    }
    
    int a(final char[] array, final char[] array2, int n, final int n2) throws Exception {
        int q = this.q;
        if (q < n) {
            this.c();
            q += 8;
            if (q < n) {
                this.c();
                q += 8;
            }
        }
        n = (int)(this.p >> q - n & n2);
        this.q = q - array[n];
        return array2[n];
    }
    
    int d() throws Exception {
        if (this.q == 0) {
            this.p = ((this.n-- != 0) ? this.t[this.r++] : ((this.b() != 0 && this.n-- != 0) ? this.t[this.r++] : 0L));
            this.q = 8;
        }
        final long p = this.p;
        final int q = this.q - 1;
        this.q = q;
        return (int)(p >> q & 0x1L);
    }
    
    private void g() throws Exception {
        this.a = this.a(this.i[this.j++]);
        if (this.a == 3) {
            this.f = 'Y';
            this.g = 'B';
        }
        else {
            this.f = 'G';
            this.g = '0';
        }
        this.b = this.a(this.i[this.j++]) + (this.a(this.i[this.j++]) << 8);
        this.c = this.a(this.i[this.j++]) + (this.a(this.i[this.j++]) << 8);
        this.e = this.a(this.i[this.j++]);
        for (int i = 1; i <= this.e << 1; ++i) {
            this.h[i] = this.a(this.i[this.j++]);
        }
        this.d = this.a(this.i[this.j++]);
    }
    
    void a(final l l) {
        try {
            this.k = false;
            this.i = super.a;
            this.j = super.b;
            this.g();
            l.c = this.c;
            l.d = this.b;
            l.g();
            this.a(l.b);
            if (super.d != null) {
                this.k = true;
                this.i = super.d;
                this.j = super.e;
                this.g();
                if (l.c == this.c && l.d == this.b) {
                    this.a(l.b);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private void a(final int[] l) {
        while (true) {
            try {
                if (l == null) {
                    return;
                }
                this.l = l;
                this.m = this.c;
                this.p = 0L;
                this.n = 0;
                this.q = 0;
                this.u = this.b + (1 << this.e) - 1 >> this.e << this.e;
                this.v = this.c + (1 << this.e + 1) - 1 >> this.e + 1 << this.e + 1;
                this.w = new dm[4];
                for (int i = 0; i < 4; ++i) {
                    this.w[i] = new dm(this);
                }
                this.x = new char[this.u][this.v];
                for (int j = 0; j < this.u; ++j) {
                    for (int k = 0; k < this.v; ++k) {
                        this.x[j][k] = '\0';
                    }
                }
                for (int n = 0, n2 = 255, n3 = 0; n3 < this.a; ++n3, n += 8, n2 <<= 8) {
                    final int n4 = (n3 != 0 && this.g != '0') ? 1 : 0;
                    this.a(this.x, this.v, this.u, this.e, n4, this.h, this.w, (n4 != 0) ? 255 : this.d);
                    final int n5 = -1 ^ n2;
                    int n6 = 0;
                    if (this.f == 'Y') {
                        int n7;
                        for (n7 = 0; n7 < this.b; ++n7) {
                            int n8;
                            for (n8 = 0; n8 < this.c; ++n8) {
                                if (n6 < this.l.length) {
                                    this.l[n6] = ((this.l[n6] & n5) | this.x[n7][n8] << n);
                                    ++n6;
                                }
                                n6 += this.m - this.c;
                                this.x[n7][n8] = '\0';
                            }
                            for (int n9 = n8; n9 < this.v; ++n9) {
                                this.x[n7][n9] = '\0';
                            }
                        }
                        for (int n10 = n7; n10 < this.u; ++n10) {
                            for (int n11 = 0; n11 < this.v; ++n11) {
                                this.x[n10][n11] = '\0';
                            }
                        }
                    }
                }
                if (this.f == 'Y') {
                    this.a(this.b, this.c, this.b(this.d), this.g);
                }
                else {
                    this.a(this.x, this.b, this.c);
                }
                this.e();
            }
            catch (Throwable t) {
                if (!l.h()) {
                    return;
                }
                continue;
            }
            break;
        }
    }
    
    void e() {
        this.x = null;
        for (int i = 0; i < 4; ++i) {
            this.w[i].a();
            this.w[i] = null;
        }
        this.w = null;
        this.l = null;
        this.i = null;
    }
    
    private void a(final char[][] array, final int n, final int n2, final int n3, final int n4, final int[] array2, final dm[] array3, final int n5) throws Exception {
        final int n6 = 1 << n3;
        array3[0].b();
        for (int i = 0; i < n2; i += n6) {
            for (int j = 0; j < n; j += n6) {
                int n7 = array3[0].d() + ((j != 0) ? array[i][j - n6] : ((i != 0) ? array[i - n6][j] : '\0'));
                if (n7 < 0) {
                    n7 += 256;
                }
                if (n7 > 255) {
                    n7 -= 256;
                }
                array[i][j] = (char)n7;
            }
        }
        this.b(n5);
        for (int n8 = 1, k = n3; k > n4; --k, n8 += 2) {
            this.a(array, n, n2, k, array2[n8], array2[n8 + 1], array3);
        }
    }
    
    int b(final int n) {
        this.z = 256;
        for (int i = -256; i < 0; ++i) {
            this.y[this.z + i] = '\0';
        }
        int j;
        for (j = 0; j < n; ++j) {
            this.y[this.z + j] = (char)j;
        }
        while (j < 512) {
            this.y[this.z + j] = (char)n;
            ++j;
        }
        return this.z;
    }
    
    int b(final int n, final int n2) {
        this.ab = 256;
        int n3 = n + (n - 1 >> 1);
        if (n3 != this.aa[this.ab + 1]) {
            this.ac = 255 / n + 1;
            this.aa[this.ab + 0] = 0;
            for (int i = 1; i <= this.ac; ++i, n3 += n) {
                this.aa[this.ab + i] = n3;
                this.aa[this.ab - i] = -n3;
            }
            for (int j = 0; j < 256; ++j) {
                this.ad[j] = (255 - j) / n;
            }
        }
        this.af = 256;
        int n4 = n2 + (n2 - 1 >> 1);
        if (n4 == this.ae[this.af + 1]) {
            return 0;
        }
        this.ag = 255 / n2 + 1;
        this.ae[this.af + 0] = 0;
        for (int k = 1; k <= this.ag; ++k, n4 += n2) {
            this.ae[this.af + k] = n4;
            this.ae[this.af - k] = -n4;
        }
        for (int l = 0; l < 256; ++l) {
            this.ah[l] = (255 - l) / n2;
        }
        return 0;
    }
    
    private void a(final char[][] array, final int n, final int n2, final int n3, final int n4, final int n5, final dm[] array2) throws Exception {
        for (int i = 0; i < 4; ++i) {
            array2[i].b();
        }
        char c = '\0';
        char c2 = '\0';
        this.b(n4, n5);
        final int n6 = 2 * n4 + 7;
        final int n7 = 2 * n5 + 7;
        final int n8 = 1 << n3;
        final int n9 = 1 << n3;
        final int n10 = n8 >> 1;
        final int n11 = n9 >> 1;
        final int n12 = n10 >> 1;
        final int n13 = n11 >> 1;
        final int n14 = this.ac - 8;
        final int n15 = this.ag - 8;
        for (int j = n11; j < n2; j += n9) {
            final char[] array3 = array[j];
            final int n16 = j + n11;
            final int n17 = j + n13;
            final int n18 = j - n13;
            final int n19 = j - n11;
            int n20 = j - n9;
            if (n20 < 0) {
                n20 = 0;
            }
            int n21 = n20 - n11;
            if (n21 < 0) {
                n21 = 0;
            }
            char[] array4;
            if (n16 == n2) {
                array4 = array[n19];
            }
            else {
                array4 = array[n16];
            }
            final char[] array5 = array[n17];
            final char[] array6 = array[n18];
            final char[] array7 = array[n19];
            final char[] array8 = array[n20];
            final char[] array9 = array[n21];
            int n22 = -1;
            int n23 = n8;
            int n24 = 0;
            int d = 0;
            int n25;
            char c3 = (char)(n25 = array7[0]);
            char c4 = array4[0];
            for (int k = n10; k < n; k += n8, n23 += n8, n24 += n8) {
                final char c5 = c3;
                final char c6 = c4;
                final int n26 = n25;
                if (n23 != n) {
                    c3 = array7[n23];
                    c4 = array4[n23];
                }
                int n27 = 3;
                final char c7;
                int n28;
                if ((c7 = (char)(c3 - c5)) < '\0') {
                    final char c8;
                    if ((c8 = (char)(c4 - c3)) <= '\0') {
                        final char c9;
                        if ((c9 = (char)(c6 - c4)) > '\0') {
                            final char c10;
                            if ((c10 = (char)(c5 - c6)) > '\0') {
                                n28 = c3 + c6 >> 1;
                            }
                            else if (c8 + c10 == '\0') {
                                if (j != n11 && n23 != n && c5 == array9[n24] && c3 == array9[n23]) {
                                    n28 = array8[k];
                                    n27 = 2;
                                }
                                else if (c5 - c3 > '\u000f' && j != n11 && n23 != n && (array8[k] == c5 || array8[k] == c3)) {
                                    c = c3;
                                    c2 = c5;
                                    n28 = -1;
                                    n27 = 2;
                                }
                                else {
                                    n28 = c5 + c3 >> 1;
                                }
                            }
                            else {
                                n28 = c5 + c3 >> 1;
                            }
                        }
                        else if (c9 == '\0') {
                            n28 = c3 + c6 >> 1;
                        }
                        else {
                            n28 = c3 + c4 >> 1;
                        }
                    }
                    else {
                        final char c11;
                        if ((c11 = (char)(c6 - c4)) > '\0') {
                            if (c5 - c6 <= '\0') {
                                n28 = c5 + c4 >> 1;
                            }
                            else {
                                n28 = c4 + c6 >> 1;
                            }
                        }
                        else if (c5 - c6 <= '\0') {
                            n28 = c5 + c6 >> 1;
                        }
                        else if (c11 != '\0') {
                            if ((c2 = (char)(c5 + c4)) - (c = (char)(c3 + c6)) <= n6) {
                                n28 = c2 + c + '\u0001' >> 2;
                            }
                            else {
                                c >>= 1;
                                c2 >>= 1;
                                n28 = -1;
                            }
                        }
                        else {
                            n28 = c4;
                        }
                    }
                }
                else if (c7 != '\0') {
                    final char c12;
                    if ((c12 = (char)(c4 - c3)) >= '\0') {
                        final char c13;
                        if ((c13 = (char)(c6 - c4)) < '\0') {
                            final char c14;
                            if ((c14 = (char)(c5 - c6)) < '\0') {
                                n28 = c3 + c6 >> 1;
                            }
                            else if (c12 + c14 == '\0') {
                                if (j != n11 && n23 != n && c5 == array9[n24] && c3 == array9[n23]) {
                                    n28 = array8[k];
                                    n27 = 2;
                                }
                                else if (c3 - c5 > '\u000f' && j != n11 && n23 != n && (array8[k] == c5 || array8[k] == c3)) {
                                    c = c5;
                                    c2 = c3;
                                    n28 = -1;
                                    n27 = 2;
                                }
                                else {
                                    n28 = c5 + c3 >> 1;
                                }
                            }
                            else {
                                n28 = c5 + c3 >> 1;
                            }
                        }
                        else if (c13 == '\0') {
                            n28 = c3 + c6 >> 1;
                        }
                        else {
                            n28 = c3 + c4 >> 1;
                        }
                    }
                    else {
                        final char c15;
                        if ((c15 = (char)(c6 - c4)) < '\0') {
                            if (c5 - c6 >= '\0') {
                                n28 = c5 + c4 >> 1;
                            }
                            else {
                                n28 = c4 + c6 >> 1;
                            }
                        }
                        else if (c5 - c6 >= '\0') {
                            n28 = c5 + c6 >> 1;
                        }
                        else if (c15 != '\0') {
                            if ((c2 = (char)(c3 + c6)) - (c = (char)(c5 + c4)) <= n6) {
                                n28 = c2 + c + '\u0001' >> 2;
                            }
                            else {
                                c2 >>= 1;
                                c >>= 1;
                                n28 = -1;
                            }
                        }
                        else {
                            n28 = c4;
                        }
                    }
                }
                else {
                    final char c16;
                    if ((c16 = (char)(c4 - c3)) < '\0') {
                        final char c17;
                        if ((c17 = (char)(c6 - c4)) < '\0') {
                            n28 = c5 + c4 >> 1;
                        }
                        else if (c17 != '\0') {
                            if (c5 - c6 < '\0') {
                                n28 = c5;
                            }
                            else {
                                n28 = c5 + c6 >> 1;
                            }
                        }
                        else if (k != n10 && n16 != n2 && c5 == array7[n24 - n8] && c6 == array4[n24 - n8]) {
                            n28 = array3[k - n8];
                            n27 = 1;
                        }
                        else if (c5 - c4 > '\u000f' && k != n10 && n16 != n2 && (array3[k - n8] == c5 || array3[k - n8] == c4)) {
                            c = c4;
                            c2 = c5;
                            n28 = -1;
                            n27 = 1;
                        }
                        else {
                            n28 = c5 + c4 >> 1;
                        }
                    }
                    else if (c16 != '\0') {
                        final char c18;
                        if ((c18 = (char)(c6 - c4)) > '\0') {
                            n28 = c5 + c4 >> 1;
                        }
                        else if (c18 != '\0') {
                            if (c5 - c6 > '\0') {
                                n28 = c5;
                            }
                            else {
                                n28 = c5 + c6 >> 1;
                            }
                        }
                        else if (k != n10 && n16 != n2 && c5 == array7[n24 - n8] && c6 == array4[n24 - n8]) {
                            n28 = array3[k - n8];
                            n27 = 1;
                        }
                        else if (c4 - c5 > '\u000f' && k != n10 && n16 != n2 && (array3[k - n8] == c5 || array3[k - n8] == c4)) {
                            c = c5;
                            c2 = c4;
                            n28 = -1;
                            n27 = 1;
                        }
                        else {
                            n28 = c5 + c4 >> 1;
                        }
                    }
                    else if (c6 == c4) {
                        if (k != n10 && n16 != n2 && c5 == array7[n24 - n8] && c6 == array4[n24 - n8]) {
                            n28 = array3[k - n8];
                        }
                        else if (j != n11 && n23 != n && c5 == array9[n24] && c3 == array9[n23]) {
                            n28 = array8[k];
                        }
                        else {
                            n28 = c5;
                        }
                    }
                    else {
                        n28 = c5;
                    }
                }
                int n29;
                int d2;
                if (n22 < 0) {
                    n29 = (n22 = array3[k]);
                    if (n29 == 0) {
                        final char c19 = (char)array2[0].d();
                        if ((c19 & '\u0080') != '\0') {
                            n29 = 1;
                        }
                        if ((c19 & '\b') != '\0') {
                            n22 = 1;
                        }
                        final char c20 = (char)(c19 >> 4 & '\u0007');
                        if (c20 == '\u0004') {
                            d2 = array2[1].d();
                        }
                        else if (c20 > '\u0004') {
                            d2 = c20 + n14;
                        }
                        else {
                            d2 = c20;
                        }
                        final char c21 = (char)(c19 & '\u0007');
                        if (c21 == '\u0004') {
                            d = array2[1].d();
                        }
                        else if (c21 > '\u0004') {
                            d = c21 + n14;
                        }
                        else {
                            d = c21;
                        }
                    }
                    else {
                        d2 = 0;
                        d = 0;
                    }
                }
                else {
                    n29 = n22;
                    n22 = -1;
                    d2 = d;
                }
                if (n28 < 0) {
                    n27 = 0;
                    if (array2[0].e() != 0) {
                        n28 = c2;
                    }
                    else {
                        n28 = c;
                    }
                }
                if (d2 == 0) {
                    n25 = n28;
                    if (n29 != 0 && n27 == 3) {
                        array3[k] = (char)n25;
                        array7[k] = (char)(c5 + c3 >> 1);
                        array3[n24] = (char)(c5 + c6 >> 1);
                        if (n3 > 1) {
                            array5[k - n12] = (array6[k - n12] = '\u0001');
                        }
                        continue;
                    }
                }
                else {
                    n27 = 0;
                    n25 = ((d2 > this.ad[n28]) ? this.y[this.z + this.aa[this.ab + d2 - this.ac] + n28] : this.y[this.z + this.aa[this.ab + d2] + n28]);
                }
                array3[k] = (char)n25;
                char c22;
                if (n19 == 0) {
                    c22 = c5;
                }
                else {
                    c22 = array8[k];
                }
                int n30;
                if ((n27 & 0x1) != 0x0) {
                    n30 = c5 + c3 >> 1;
                }
                else {
                    final char c23;
                    if ((c23 = (char)(c22 - c5)) < '\0') {
                        final char c24;
                        if ((c24 = (char)(c3 - c22)) <= '\0') {
                            final char c25;
                            if ((c25 = (char)(n25 - c3)) > '\0') {
                                final char c26;
                                if ((c26 = (char)(c5 - n25)) > '\0') {
                                    n30 = c22 + n25 >> 1;
                                }
                                else if (c24 + c26 == '\0') {
                                    if (n24 != 0 && n19 != 0 && c5 == array8[k - n8] && c3 == array9[n24]) {
                                        n30 = array8[n24];
                                        n27 = 2;
                                    }
                                    else if (c5 - c22 > '\u000f' && n24 != 0 && n19 != 0 && (array8[n24] == c5 || array8[n24] == c22)) {
                                        c = c22;
                                        c2 = c5;
                                        n30 = -1;
                                        n27 = 2;
                                    }
                                    else {
                                        n30 = c5 + c22 >> 1;
                                    }
                                }
                                else {
                                    n30 = c5 + c22 >> 1;
                                }
                            }
                            else if (c25 == '\0') {
                                n30 = c22 + n25 >> 1;
                            }
                            else {
                                n30 = c22 + c3 >> 1;
                            }
                        }
                        else {
                            final char c27;
                            if ((c27 = (char)(n25 - c3)) > '\0') {
                                if (c5 - n25 <= '\0') {
                                    n30 = c5 + c3 >> 1;
                                }
                                else {
                                    n30 = c3 + n25 >> 1;
                                }
                            }
                            else if (c5 - n25 <= '\0') {
                                n30 = c5 + n25 >> 1;
                            }
                            else if (c27 != '\0') {
                                if ((c2 = (char)(c5 + c3)) - (c = (char)(c22 + n25)) <= n7) {
                                    n30 = c2 + c + '\u0001' >> 2;
                                }
                                else {
                                    c >>= 1;
                                    c2 >>= 1;
                                    n30 = -1;
                                }
                            }
                            else {
                                n30 = c3;
                            }
                        }
                    }
                    else if (c23 != '\0') {
                        final char c28;
                        if ((c28 = (char)(c3 - c22)) >= '\0') {
                            final char c29;
                            if ((c29 = (char)(n25 - c3)) < '\0') {
                                final char c30;
                                if ((c30 = (char)(c5 - n25)) < '\0') {
                                    n30 = c22 + n25 >> 1;
                                }
                                else if (c28 + c30 == '\0') {
                                    if (n24 != 0 && n19 != 0 && c5 == array8[k - n8] && c3 == array9[n24]) {
                                        n30 = array8[n24];
                                        n27 = 2;
                                    }
                                    else if (c22 - c5 > '\u000f' && n24 != 0 && n19 != 0 && (array8[n24] == c5 || array8[n24] == c22)) {
                                        c = c5;
                                        c2 = c22;
                                        n30 = -1;
                                        n27 = 2;
                                    }
                                    else {
                                        n30 = c5 + c22 >> 1;
                                    }
                                }
                                else {
                                    n30 = c5 + c22 >> 1;
                                }
                            }
                            else if (c29 == '\0') {
                                n30 = c22 + n25 >> 1;
                            }
                            else {
                                n30 = c22 + c3 >> 1;
                            }
                        }
                        else {
                            final char c31;
                            if ((c31 = (char)(n25 - c3)) < '\0') {
                                if (c5 - n25 >= '\0') {
                                    n30 = c5 + c3 >> 1;
                                }
                                else {
                                    n30 = c3 + n25 >> 1;
                                }
                            }
                            else if (c5 - n25 >= '\0') {
                                n30 = c5 + n25 >> 1;
                            }
                            else if (c31 != '\0') {
                                if ((c2 = (char)(c22 + n25)) - (c = (char)(c5 + c3)) <= n7) {
                                    n30 = c2 + c + '\u0001' >> 2;
                                }
                                else {
                                    c2 >>= 1;
                                    c >>= 1;
                                    n30 = -1;
                                }
                            }
                            else {
                                n30 = c3;
                            }
                        }
                    }
                    else {
                        final char c32;
                        if ((c32 = (char)(c3 - c22)) < '\0') {
                            final char c33;
                            if ((c33 = (char)(n25 - c3)) < '\0') {
                                n30 = c5 + c3 >> 1;
                            }
                            else if (c33 != '\0') {
                                if (c5 - n25 < '\0') {
                                    n30 = c5;
                                }
                                else {
                                    n30 = c5 + n25 >> 1;
                                }
                            }
                            else if (n23 != n && n19 != 0 && c5 == array9[n23] && n25 == array8[k + n8]) {
                                n30 = array8[n23];
                                n27 = 1;
                            }
                            else if (c5 - c3 > '\u000f' && n23 != n && n19 != 0 && (array8[n23] == c5 || array8[n23] == c3)) {
                                c = c3;
                                c2 = c5;
                                n30 = -1;
                                n27 = 1;
                            }
                            else {
                                n30 = c5 + c3 >> 1;
                            }
                        }
                        else if (c32 != '\0') {
                            final char c34;
                            if ((c34 = (char)(n25 - c3)) > '\0') {
                                n30 = c5 + c3 >> 1;
                            }
                            else if (c34 != '\0') {
                                if (c5 - n25 > '\0') {
                                    n30 = c5;
                                }
                                else {
                                    n30 = c5 + n25 >> 1;
                                }
                            }
                            else if (n23 != n && n19 != 0 && c5 == array9[n23] && n25 == array8[k + n8]) {
                                n30 = array8[n23];
                                n27 = 1;
                            }
                            else if (c3 - c5 > '\u000f' && n23 != n && n19 != 0 && (array8[n23] == c5 || array8[n23] == c3)) {
                                c = c5;
                                c2 = c3;
                                n30 = -1;
                                n27 = 1;
                            }
                            else {
                                n30 = c5 + c3 >> 1;
                            }
                        }
                        else if (n25 == c3) {
                            if (n23 != n && n19 != 0 && c5 == array9[n23] && n25 == array8[k + n8]) {
                                n30 = array8[n23];
                            }
                            else if (n24 != 0 && n19 != 0 && c5 == array8[k - n8] && c3 == array9[n24]) {
                                n30 = array8[n24];
                            }
                            else {
                                n30 = c5;
                            }
                        }
                        else {
                            n30 = c5;
                        }
                    }
                }
                int d3;
                int d4;
                if (n29 == 0) {
                    final char c35 = (char)array2[2].d();
                    if ((c35 & '\u0080') != '\0') {
                        array6[k - n12] = '\u0001';
                    }
                    if ((c35 & '\b') != '\0') {
                        array5[k - n12] = '\u0001';
                    }
                    final char c36 = (char)(c35 >> 4 & '\u0007');
                    if (c36 == '\u0004') {
                        d3 = array2[3].d();
                    }
                    else if (c36 > '\u0004') {
                        d3 = c36 + n15;
                    }
                    else {
                        d3 = c36;
                    }
                    final char c37 = (char)(c35 & '\u0007');
                    if (c37 == '\u0004') {
                        d4 = array2[3].d();
                    }
                    else if (c37 > '\u0004') {
                        d4 = c37 + n15;
                    }
                    else {
                        d4 = c37;
                    }
                }
                else {
                    d3 = 0;
                    d4 = 0;
                    if (n3 > 1) {
                        array5[k - n12] = (array6[k - n12] = '\u0001');
                    }
                }
                if (n30 < 0) {
                    n27 = 0;
                    if (array2[0].e() != 0) {
                        n30 = c2;
                    }
                    else {
                        n30 = c;
                    }
                }
                if (d3 == 0) {
                    array7[k] = (char)n30;
                }
                else {
                    array7[k] = ((d3 > this.ah[n30]) ? this.y[this.z + this.ae[this.af + d3 - this.ag] + n30] : this.y[this.z + this.ae[this.af + d3] + n30]);
                }
                int n31;
                if ((n27 & 0x2) != 0x0) {
                    n31 = c5 + c6 >> 1;
                }
                else {
                    final char c38;
                    if ((c38 = (char)(c5 - n26)) < '\0') {
                        final char c39;
                        if ((c39 = (char)(n25 - c5)) <= '\0') {
                            final char c40;
                            if ((c40 = (char)(c6 - n25)) > '\0') {
                                final char c41;
                                if ((c41 = (char)(n26 - c6)) > '\0') {
                                    n31 = c5 + c6 >> 1;
                                }
                                else if (c39 + c41 == '\0') {
                                    if (n24 != 0 && n19 != 0 && n26 == array7[n24 - n8] && c5 == array8[k - n8]) {
                                        n31 = array7[k - n8];
                                    }
                                    else if (n26 - c5 > '\u000f' && n24 != 0 && n19 != 0 && (array7[k - n8] == n26 || array7[k - n8] == c5)) {
                                        c = c5;
                                        c2 = (char)n26;
                                        n31 = -1;
                                    }
                                    else {
                                        n31 = n26 + c5 >> 1;
                                    }
                                }
                                else {
                                    n31 = n26 + c5 >> 1;
                                }
                            }
                            else if (c40 == '\0') {
                                n31 = c5 + c6 >> 1;
                            }
                            else {
                                n31 = c5 + n25 >> 1;
                            }
                        }
                        else {
                            final char c42;
                            if ((c42 = (char)(c6 - n25)) > '\0') {
                                if (n26 - c6 <= '\0') {
                                    n31 = n26 + n25 >> 1;
                                }
                                else {
                                    n31 = n25 + c6 >> 1;
                                }
                            }
                            else if (n26 - c6 <= '\0') {
                                n31 = n26 + c6 >> 1;
                            }
                            else if (c42 != '\0') {
                                if ((c2 = (char)(n26 + n25)) - (c = (char)(c5 + c6)) <= n7) {
                                    n31 = c2 + c + '\u0001' >> 2;
                                }
                                else {
                                    c >>= 1;
                                    c2 >>= 1;
                                    n31 = -1;
                                }
                            }
                            else {
                                n31 = n25;
                            }
                        }
                    }
                    else if (c38 != '\0') {
                        final char c43;
                        if ((c43 = (char)(n25 - c5)) >= '\0') {
                            final char c44;
                            if ((c44 = (char)(c6 - n25)) < '\0') {
                                final char c45;
                                if ((c45 = (char)(n26 - c6)) < '\0') {
                                    n31 = c5 + c6 >> 1;
                                }
                                else if (c43 + c45 == '\0') {
                                    if (n24 != 0 && n19 != 0 && n26 == array7[n24 - n8] && c5 == array8[k - n8]) {
                                        n31 = array7[k - n8];
                                    }
                                    else if (c5 - n26 > '\u000f' && n24 != 0 && n19 != 0 && (array7[k - n8] == n26 || array7[k - n8] == c5)) {
                                        c = (char)n26;
                                        c2 = c5;
                                        n31 = -1;
                                    }
                                    else {
                                        n31 = n26 + c5 >> 1;
                                    }
                                }
                                else {
                                    n31 = n26 + c5 >> 1;
                                }
                            }
                            else if (c44 == '\0') {
                                n31 = c5 + c6 >> 1;
                            }
                            else {
                                n31 = c5 + n25 >> 1;
                            }
                        }
                        else {
                            final char c46;
                            if ((c46 = (char)(c6 - n25)) < '\0') {
                                if (n26 - c6 >= '\0') {
                                    n31 = n26 + n25 >> 1;
                                }
                                else {
                                    n31 = n25 + c6 >> 1;
                                }
                            }
                            else if (n26 - c6 >= '\0') {
                                n31 = n26 + c6 >> 1;
                            }
                            else if (c46 != '\0') {
                                if ((c2 = (char)(c5 + c6)) - (c = (char)(n26 + n25)) <= n7) {
                                    n31 = c2 + c + '\u0001' >> 2;
                                }
                                else {
                                    c2 >>= 1;
                                    c >>= 1;
                                    n31 = -1;
                                }
                            }
                            else {
                                n31 = n25;
                            }
                        }
                    }
                    else {
                        final char c47;
                        if ((c47 = (char)(n25 - c5)) < '\0') {
                            final char c48;
                            if ((c48 = (char)(c6 - n25)) < '\0') {
                                n31 = n26 + n25 >> 1;
                            }
                            else if (c48 != '\0') {
                                if (n26 - c6 < '\0') {
                                    n31 = n26;
                                }
                                else {
                                    n31 = n26 + c6 >> 1;
                                }
                            }
                            else if (n23 != n && n19 != 0 && n26 == c22 && c6 == c3) {
                                n31 = array7[k];
                            }
                            else if (n26 - n25 > '\u000f' && n23 != n && n19 != 0 && (array7[k] == n26 || array7[k] == n25)) {
                                c = (char)n25;
                                c2 = (char)n26;
                                n31 = -1;
                            }
                            else {
                                n31 = n26 + n25 >> 1;
                            }
                        }
                        else if (c47 != '\0') {
                            final char c49;
                            if ((c49 = (char)(c6 - n25)) > '\0') {
                                n31 = n26 + n25 >> 1;
                            }
                            else if (c49 != '\0') {
                                if (n26 - c6 > '\0') {
                                    n31 = n26;
                                }
                                else {
                                    n31 = n26 + c6 >> 1;
                                }
                            }
                            else if (n23 != n && n19 != 0 && n26 == c22 && c6 == c3) {
                                n31 = array7[k];
                            }
                            else if (n25 - n26 > '\u000f' && n23 != n && n19 != 0 && (array7[k] == n26 || array7[k] == n25)) {
                                c = (char)n26;
                                c2 = (char)n25;
                                n31 = -1;
                            }
                            else {
                                n31 = n26 + n25 >> 1;
                            }
                        }
                        else if (c6 == n25) {
                            if (n23 != n && n19 != 0 && n26 == c22 && c6 == c3) {
                                n31 = array7[k];
                            }
                            else if (n24 != 0 && n19 != 0 && n26 == array7[n24 - n8] && c5 == array8[k - n8]) {
                                n31 = array7[k - n8];
                            }
                            else {
                                n31 = n26;
                            }
                        }
                        else {
                            n31 = n26;
                        }
                    }
                }
                final int n32 = d4;
                if (n31 < 0) {
                    if (array2[0].e() != 0) {
                        n31 = c2;
                    }
                    else {
                        n31 = c;
                    }
                }
                if (n32 == 0) {
                    array3[n24] = (char)n31;
                }
                else {
                    array3[n24] = ((n32 > this.ah[n31]) ? this.y[this.z + this.ae[this.af + n32 - this.ag] + n31] : this.y[this.z + this.ae[this.af + n32] + n31]);
                }
            }
        }
    }
    
    static final int c(final int n, final int n2) {
        return n >> n2;
    }
    
    static final int a(final double n) {
        return (int)(n * 65536.0 + 0.5);
    }
    
    static void f() {
        if (c1.am) {
            return;
        }
        for (int i = 0; i <= 255; ++i) {
            final int n = 2 * i - 255;
            c1.ai[i] = c(a(0.701) * n + 32768, 16);
            c1.aj[i] = c(a(0.886) * n + 32768, 16);
            c1.ak[i] = -a(0.35707) * n;
            c1.al[i] = -a(0.17207) * n + 32768;
        }
        c1.am = true;
    }
    
    void a(final int n, final int n2, final int n3, final char c) {
        f();
        if (c != '0') {
            for (int i = 0; i < n; i += 2) {
                int n4 = i * n2;
                for (int j = 0; j < n2; j += 2) {
                    final int n5 = this.l[n4] & 0xFFFF00;
                    this.l[n4 + n2] = ((this.l[n4 + n2] & 0xFF0000FF) | n5);
                    ++n4;
                    this.l[n4] = ((this.l[n4] & 0xFF0000FF) | n5);
                    this.l[n4 + n2] = ((this.l[n4 + n2] & 0xFF0000FF) | n5);
                    ++n4;
                }
            }
        }
        int n6 = 0;
        for (int k = 0; k < n; ++k) {
            for (int l = 0; l < n2; ++l) {
                final int n7 = this.l[n6];
                final int n8 = n7 & 0xFF;
                final int n9 = (n7 & 0xFF00) >> 8;
                final int n10 = (n7 & 0xFF0000) >> 16;
                char c2 = this.y[n3 + n8 + c1.ai[n9]];
                final char c3 = this.y[n3 + n8 + c(c1.al[n10] + c1.ak[n9], 16)];
                char c4 = this.y[n3 + n8 + c1.aj[n10]];
                if (c2 - c3 <= '\u0002' && c2 - c3 >= '\0' && c4 - c3 <= '\u0003' && c4 - c3 >= '\0') {
                    c4 = (c2 = c3);
                }
                if (n6 < this.l.length) {
                    this.l[n6++] = -16777216 + (c2 << 16) + (c3 << 8) + c4;
                }
            }
            n6 += this.m - n2;
        }
    }
    
    private void a(final char[][] array, final int n, final int n2) {
        int n3 = 0;
        if (this.k) {
            for (final char[] array2 : array) {
                for (int j = 0; j < n2; ++j) {
                    final int[] l = this.l;
                    final int n4 = n3;
                    l[n4] &= ((array2[j] & '\u00fe') << 23 | 0xFFFFFF);
                    ++n3;
                }
            }
        }
        else {
            for (final char[] array3 : array) {
                for (int n5 = 0; n5 < n2; ++n5) {
                    this.l[n3] = (array3[n5] & '\u00ff');
                    ++n3;
                }
            }
        }
    }
    
    static {
        c1.ai = new int[256];
        c1.aj = new int[256];
        c1.ak = new int[256];
        c1.al = new int[256];
        c1.am = false;
    }
}
