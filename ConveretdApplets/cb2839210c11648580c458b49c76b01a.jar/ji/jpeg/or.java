// 
// Decompiled by Procyon v0.5.30
// 

package ji.jpeg;

import java.awt.Component;
import ji.io.h;
import java.util.Hashtable;
import ji.filter.fh;
import ji.util.d;
import ji.v1event.a6;
import java.util.Vector;
import ji.image.dx;
import java.io.EOFException;
import ji.io.ac;
import ji.v1event.af;
import ji.image.ds;

public final class or
{
    static ov a;
    ds b;
    Object c;
    private byte[] d;
    private int e;
    private int f;
    private int g;
    private int[][][] h;
    boolean i;
    boolean j;
    static int[] k;
    af l;
    ac m;
    int[] n;
    int o;
    int p;
    boolean q;
    boolean r;
    int[][][][][] s;
    int[] t;
    int[] u;
    int v;
    int w;
    int x;
    int y;
    int z;
    int aa;
    int ab;
    boolean ac;
    aa3 ad;
    int[][] ae;
    int[] af;
    int[] ag;
    private int[] ah;
    private aaz ai;
    private aa1 aj;
    private boolean ak;
    static final int[] al;
    static final int[] am;
    static final int[] an;
    static final int[] ao;
    static final int[] ap;
    
    public final void a() {
        this.r = true;
    }
    
    public or(final ac m, final boolean ak) throws Exception {
        this.f = 7;
        this.h = new int[][][] { { null, null, null, null }, { null, null, null, null } };
        this.i = false;
        this.p = -1;
        this.q = false;
        this.r = false;
        this.t = new int[64];
        this.u = new int[64];
        this.af = new int[3];
        this.ag = new int[3];
        this.ah = new int[3];
        this.ai = new aaz();
        this.aj = new aa1();
        this.ak = false;
        this.m = m;
        this.ak = ak;
    }
    
    final int a(final int n, final int n2) throws Exception {
        int n3 = 16;
        int n4 = 0;
        int n5 = 0;
        final int[] array = this.h[n][n2];
        while (n3-- != 0) {
            final int n6 = 0x1 & this.aa >>> this.f--;
            if (this.f < 0) {
                this.f = 7;
                if (this.g == this.e) {
                    this.e = this.m.a(this.d);
                    if (this.e < 0) {
                        throw new EOFException("Unable to process image: Unexpected EOF");
                    }
                    this.g = 0;
                }
                this.aa = (this.d[this.g++] & 0xFF);
                if (this.aa == 255 && this.f == 7) {
                    if (this.g == this.e) {
                        this.e = this.m.a(this.d);
                        if (this.e < 0) {
                            throw new EOFException("Unable to process image: Unexpected EOF");
                        }
                        this.g = 0;
                    }
                    final int n7 = this.d[this.g++] & 0xFF;
                    if (n7 != 0) {
                        switch (n7) {
                            case 208:
                            case 209:
                            case 210:
                            case 211:
                            case 212:
                            case 213:
                            case 214:
                            case 215: {
                                this.p = (n7 & 0xF);
                                break;
                            }
                            case 216: {
                                throw new os("SOI Found in Image Data");
                            }
                            case 217: {
                                this.d();
                                break;
                            }
                            default: {
                                throw new os("Illegal Marker found in image data");
                            }
                        }
                    }
                }
            }
            ++n4;
            n5 = (n5 << 1 | n6);
            final int n8 = array[(1 << n4) + n5];
            if (n8 != -1) {
                return n8;
            }
        }
        throw new os("Invalid Huffman Code at offset: ".concat(String.valueOf(String.valueOf(Long.toHexString(this.m.r() - (this.e - this.g)).toUpperCase()))));
    }
    
    final int a(final int n) throws Exception {
        int n2 = 0;
        int n3 = 1 << n - 1;
        for (int i = 0; i < n; ++i) {
            final int n4 = 0x1 & this.aa >>> this.f--;
            if (this.f < 0) {
                this.f = 7;
                if (this.g == this.e) {
                    this.e = this.m.a(this.d);
                    if (this.e < 0) {
                        throw new EOFException("Unable to process image: Unexpected EOF");
                    }
                    this.g = 0;
                }
                this.aa = (this.d[this.g++] & 0xFF);
                if (this.aa == 255 && this.f == 7) {
                    if (this.g == this.e) {
                        this.e = this.m.a(this.d);
                        if (this.e < 0) {
                            throw new EOFException("Unable to process image: Unexpected EOF");
                        }
                        this.g = 0;
                    }
                    final int n5 = this.d[this.g++] & 0xFF;
                    if (n5 != 0) {
                        switch (n5) {
                            case 208:
                            case 209:
                            case 210:
                            case 211:
                            case 212:
                            case 213:
                            case 214:
                            case 215: {
                                this.p = (n5 & 0xF);
                                break;
                            }
                            case 216: {
                                throw new os("SOI Found in Image Data");
                            }
                            case 217: {
                                this.d();
                                break;
                            }
                            default: {
                                throw new os("Illegal Marker found in image data: ".concat(String.valueOf(String.valueOf(Integer.toHexString(n5).toUpperCase()))));
                            }
                        }
                    }
                }
            }
            if (n4 == 1) {
                n2 |= n3;
            }
            n3 >>= 1;
        }
        if (n2 < 1 << n - 1) {
            return n2 + (-1 << n) + 1;
        }
        return n2;
    }
    
    final void a(final int n, final int n2, final int n3) throws Exception {
        int i = 0;
        int n4 = 0;
        final int d = this.aj.b[n].d;
        final int c = this.aj.b[n].c;
        this.u[n4] = this.a(this.a(0, d)) + this.ah[n];
        this.ah[n] = this.u[n4];
        ++n4;
        while (i == 0) {
            final int a = this.a(1, c);
            if (a == 0) {
                for (int j = n4; j < 64; ++j) {
                    this.u[j] = 0;
                }
                i = 1;
            }
            else {
                int n5 = a >> 4;
                final int n6 = a & 0xF;
                if (a == 240) {
                    ++n5;
                }
                for (int k = 0; k < n5; ++k) {
                    this.u[n4++] = 0;
                }
                if (n6 > 0) {
                    this.u[n4++] = this.a(n6);
                }
                if (n4 != 64) {
                    continue;
                }
                i = 1;
            }
        }
        final int[] array = this.ae[this.ai.f[n].e];
        for (int l = 0; l < 64; ++l) {
            this.t[or.al[l]] = this.u[l] * array[l];
        }
        or.a.a(this.t, this.s[n2][n3][n]);
    }
    
    void b() throws Exception {
        if (!this.q) {
            this.ac = true;
            throw new os("Error quantization table found before start of image");
        }
        final int n = (this.m.k() - 2) / 65;
        final byte[] array = new byte[64];
        for (int i = 0; i < n; ++i) {
            final int j = this.m.j();
            this.m.a(array);
            final int[] array2 = this.ae[j];
            for (int k = 0; k < 64; ++k) {
                array2[k] = (array[k] & 0xFF);
            }
        }
    }
    
    void a(final dx dx) throws Exception {
        final aa4 aa4 = new aa4();
        if (!this.q) {
            this.ac = true;
            throw new os("Error huffman table found before start of image");
        }
        int i = this.m.k() - 2;
        final byte[] array = new byte[i];
        this.m.a(array);
        int n = 0;
        while (i > 0) {
            int n2 = 0;
            this.ab = (array[n++] & 0xFF);
            final int n3 = this.ab >> 4;
            final int n4 = this.ab & 0xF;
            int n5 = 0;
            for (int j = 0; j < 16; ++j) {
                aa4.a[j] = (array[n++] & 0xFF);
                n5 += aa4.a[j];
                if (aa4.a[j] != 0) {
                    n2 = j + 1;
                }
            }
            if (n5 > 256) {
                this.ac = true;
                throw new os("Error bad huffman code count");
            }
            for (int k = 0; k < n5; ++k) {
                aa4.b[k] = (array[n++] & 0xFF);
            }
            i -= 17 + n5;
            this.h[n3][n4] = new int[(1 << n2) + 65536];
            final int[] l = or.k;
            for (int n6 = this.h[n3][n4].length / 8192, n7 = 0; n7 < n6; ++n7) {
                System.arraycopy(l, 0, this.h[n3][n4], n7 * 8192, 8192);
            }
            final aa5[] array2 = new aa5[n5 + 1];
            for (int n8 = 0; n8 < array2.length; ++n8) {
                array2[n8] = new aa5(null);
            }
            int n9 = 0;
            int n10 = 1;
            int n11 = 0;
            while (n9 < 16) {
                while (n10 <= aa4.a[n9]) {
                    array2[n11].b = n9 + 1;
                    ++n11;
                    ++n10;
                }
                ++n9;
                n10 = 1;
            }
            array2[n11].b = 0;
            int n12 = 0;
            int a = 0;
            int b = array2[n12].b;
            while (array2[n12].b > 0) {
                while (array2[n12].b == b) {
                    array2[n12].a = a;
                    array2[n12].c = aa4.b[n12];
                    ++a;
                    ++n12;
                }
                if (array2[n12].b > 0) {
                    while (array2[n12].b != b) {
                        a <<= 1;
                        ++b;
                    }
                }
            }
            final int[] array3 = this.h[n3][n4];
            for (int n13 = 0; n13 < array2.length; ++n13) {
                final aa5 aa5 = array2[n13];
                if (aa5.b > 0) {
                    array3[(1 << aa5.b) + aa5.a] = aa5.c;
                }
            }
        }
        this.i = true;
    }
    
    void a(final int n, final dx dx) throws Exception {
        if (!this.q) {
            this.ac = true;
            throw new os("Unknown marker found before start of image");
        }
        Vector<String> vector = dx.bk.get("Unknown Markers");
        if (vector == null) {
            vector = new Vector<String>();
            dx.bk.put("Unknown Markers", vector);
        }
        vector.addElement(Integer.toHexString(n).toUpperCase());
        this.m.a(this.m.k() - 2);
    }
    
    private int f() throws Exception {
        if (this.g == this.e) {
            this.e = this.m.a(this.d);
            if (this.e < 0) {
                throw new EOFException("Unable to process image: Unexpected EOF");
            }
            this.g = 0;
        }
        return this.d[this.g++] & 0xFF;
    }
    
    void b(final dx dx) throws Exception {
        a6 a6 = new a6(this, 4, "");
        if (ji.util.d.du() && dx.ak) {
            a6 = new a6(this, 23, "");
        }
        final boolean b = this.l != null;
        int n = 0;
        if (!this.q) {
            this.ac = true;
            throw new os("Start of image data found before start of image");
        }
        final int n2 = this.v * 8;
        this.n = new int[n2 * this.ai.c];
        this.x = 0;
        final int n3 = this.y * this.z;
        final float n4 = 100.0f / n3;
        float n5 = 0.0f;
        int n6 = 0;
        if (this.j) {
            this.ac = false;
            for (int n7 = 0; n7 < n3 && !this.ac && !this.r; n7 += this.o) {
                try {
                    for (int i = 0; i < this.o; ++i) {
                        this.c();
                        if ((i + n7 + 1) % this.y == 0) {
                            this.b.a(this.n, this.ai.c * n2, null, n6, n6 + n2 - 1, true);
                            n6 += n2;
                        }
                        final int n8 = (int)(n5 += n4) / 10;
                        if (b && n8 > n) {
                            n = n8;
                            a6.a("".concat(String.valueOf(String.valueOf((int)n5))));
                            this.l.a(a6);
                        }
                    }
                    if (this.aa == 255 && this.f == 7) {
                        this.aa = this.f();
                    }
                    else {
                        if (this.f() != 255) {}
                        final int f = this.f();
                        if ((f & 0xF0) == 0xD0) {
                            switch (f) {
                                case 208:
                                case 209:
                                case 210:
                                case 211:
                                case 212:
                                case 213:
                                case 214:
                                case 215: {
                                    this.p = (f & 0xF);
                                    break;
                                }
                                case 216: {
                                    throw new os("SOI Found in Image Data");
                                }
                                case 217: {
                                    this.d();
                                    break;
                                }
                                default: {
                                    throw new os("Illegal Marker found in image data");
                                }
                            }
                        }
                        this.aa = this.f();
                    }
                    this.f = 7;
                    for (int j = 0; j < this.ah.length; ++j) {
                        this.ah[j] = 0;
                    }
                }
                catch (Exception ex) {
                    if (ex instanceof EOFException) {
                        try {
                            this.d();
                            continue;
                        }
                        catch (Exception ex2) {
                            this.ac = true;
                            throw ex;
                        }
                    }
                    ex.printStackTrace();
                    this.ac = true;
                    throw ex;
                }
            }
        }
        else {
            try {
                for (int k = 0; k < n3; ++k) {
                    this.c();
                    if ((k + 1) % this.y == 0) {
                        this.b.a(this.n, this.ai.c * n2, null, n6, n6 + n2 - 1, true);
                        n6 += n2;
                    }
                    final int n9 = (int)(n5 += n4) / 10;
                    if (b && n9 > n) {
                        n = n9;
                        a6.a("".concat(String.valueOf(String.valueOf((int)n5))));
                        this.l.a(a6);
                    }
                }
                this.ac = true;
            }
            catch (Exception ex3) {
                this.ac = true;
            }
        }
    }
    
    void c() throws Exception {
        for (int i = 0; i < this.ai.d; ++i) {
            final int c = this.ai.f[i].c;
            final int d = this.ai.f[i].d;
            for (int j = 0; j < c; ++j) {
                for (int k = 0; k < d; ++k) {
                    this.a(i, k, j);
                }
            }
        }
        switch (this.ai.e) {
            case 2: {
                this.h();
                break;
            }
            case 0: {
                this.b(0);
                this.b(1);
                break;
            }
            case 3: {
                this.c(0, 0);
                this.c(1, 0);
                this.c(0, 1);
                this.c(1, 1);
                break;
            }
            case 1: {
                this.b(0, 0);
                this.b(0, 1);
            }
            case 4: {
                this.g();
                break;
            }
            default: {
                for (int l = 0; l < this.v; ++l) {
                    for (int n = 0; n < this.w; ++n) {
                        this.b(n, l);
                    }
                }
                break;
            }
        }
        ++this.x;
    }
    
    private final void g() {
        final int[][] array = this.s[0][0][0];
        final int c = this.ai.c;
        final int n = this.x % this.y * 8;
        int n2 = 8 - (n + 8 - c);
        if (n2 > 8) {
            n2 = 8;
        }
        int n3 = 8 - (8 - this.ai.b);
        if (n3 > 8) {
            n3 = 8;
        }
        for (int i = 0; i < n2; ++i) {
            final int[] array2 = array[i];
            int n4 = n + i;
            for (final int n5 : array2) {
                this.n[n4] = (0xFF000000 | n5 << 16 | n5 << 8 | n5);
                n4 += c;
            }
        }
    }
    
    private final void b(final int n, final int n2) {
        int n3;
        if (n >= this.ai.f[1].d) {
            n3 = this.ai.f[1].d - 1;
        }
        else {
            n3 = n;
        }
        int n4;
        if (n2 >= this.ai.f[1].c) {
            n4 = this.ai.f[1].c - 1;
        }
        else {
            n4 = n2;
        }
        int n5;
        if (n >= this.ai.f[2].d) {
            n5 = this.ai.f[2].d - 1;
        }
        else {
            n5 = n;
        }
        int n6;
        if (n2 >= this.ai.f[2].c) {
            n6 = this.ai.f[2].c - 1;
        }
        else {
            n6 = n2;
        }
        final int n7 = n2 << 3;
        final int n8 = n << 3;
        final int n9 = this.x % this.y * this.w * 8 + n8;
        final int n10 = n7;
        int n11 = 8 - (n9 + 8 - this.ai.c);
        if (n11 > 8) {
            n11 = 8;
        }
        int n12 = 8 - (n10 + 8 - this.ai.b);
        if (n12 > 8) {
            n12 = 8;
        }
        final int[][] array = this.s[n][n2][0];
        final int[][] array2 = this.s[n3][n4][1];
        final int[][] array3 = this.s[n5][n6][2];
        final int n13 = this.af[1];
        final int n14 = this.ag[1];
        int n15 = n7 * this.ai.c + n9;
        final int n16 = this.ai.c - n11;
        if (this.ak) {
            for (int i = 0; i < n12; ++i) {
                for (int j = 0; j < n11; ++j) {
                    int n17 = array[j][i];
                    int n18 = array2[(j + n8) / n13][(i + n7) / n14];
                    int n19 = array3[(j + n8) / n13][(i + n7) / n14];
                    if (n17 < 0) {
                        n17 = 0;
                    }
                    else if (n17 > 255) {
                        n17 = 255;
                    }
                    if (n18 < 0) {
                        n18 = 0;
                    }
                    else if (n18 > 255) {
                        n18 = 255;
                    }
                    if (n19 < 0) {
                        n19 = 0;
                    }
                    else if (n19 > 255) {
                        n19 = 255;
                    }
                    this.n[n15] = (0xFF000000 | n17 << 16 | n18 << 8 | n19);
                    ++n15;
                }
                n15 += n16;
            }
        }
        else {
            for (int k = 0; k < n12; ++k) {
                for (int l = 0; l < n11; ++l) {
                    final int n20 = array[l][k];
                    final int n21 = array2[(l + n8) / n13][(k + n7) / n14];
                    final int n22 = array3[(l + n8) / n13][(k + n7) / n14];
                    int n23 = n20 + or.am[n22];
                    int n24 = n20 + (or.ao[n22] + or.ap[n21] >> 16);
                    int n25 = n20 + or.an[n21];
                    if (n23 < 0) {
                        n23 = 0;
                    }
                    else if (n23 > 255) {
                        n23 = 255;
                    }
                    if (n24 < 0) {
                        n24 = 0;
                    }
                    else if (n24 > 255) {
                        n24 = 255;
                    }
                    if (n25 < 0) {
                        n25 = 0;
                    }
                    else if (n25 > 255) {
                        n25 = 255;
                    }
                    this.n[n15] = (0xFF000000 | n23 << 16 | n24 << 8 | n25);
                    ++n15;
                }
                n15 += n16;
            }
        }
    }
    
    private final void h() {
        final int[][] array = this.s[0][0][0];
        final int[][] array2 = this.s[0][0][1];
        final int[][] array3 = this.s[0][0][2];
        final int c = this.ai.c;
        final int n = this.x % this.y * 8;
        int n2 = 8 - (n + 8 - c);
        if (n2 > 8) {
            n2 = 8;
        }
        int n3 = 8 - (8 - this.ai.b);
        if (n3 > 8) {
            n3 = 8;
        }
        if (this.ak) {
            for (int i = 0; i < n2; ++i) {
                int n4 = n + i;
                final int[] array4 = array[i];
                final int[] array5 = array2[i];
                final int[] array6 = array3[i];
                for (int j = 0; j < n3; ++j) {
                    this.n[n4] = (0xFF000000 | array4[j] << 16 | array5[j] << 8 | array6[j]);
                    n4 += c;
                }
            }
        }
        else {
            for (int k = 0; k < n2; ++k) {
                int n5 = n + k;
                final int[] array7 = array[k];
                final int[] array8 = array2[k];
                final int[] array9 = array3[k];
                for (int l = 0; l < n3; ++l) {
                    final int n6 = array7[l];
                    final int n7 = array8[l];
                    final int n8 = array9[l];
                    int n9 = n6 + or.am[n8];
                    int n10 = n6 + (or.ao[n8] + or.ap[n7] >> 16);
                    int n11 = n6 + or.an[n7];
                    if (n9 < 0) {
                        n9 = 0;
                    }
                    else if (n9 > 255) {
                        n9 = 255;
                    }
                    if (n10 < 0) {
                        n10 = 0;
                    }
                    else if (n10 > 255) {
                        n10 = 255;
                    }
                    if (n11 < 0) {
                        n11 = 0;
                    }
                    else if (n11 > 255) {
                        n11 = 255;
                    }
                    this.n[n5] = (0xFF000000 | n9 << 16 | n10 << 8 | n11);
                    n5 += c;
                }
            }
        }
    }
    
    private final void c(final int n, final int n2) {
        final int[] am = or.am;
        final int[] ao = or.ao;
        final int[] ap = or.ap;
        final int[] an = or.an;
        final int n3 = n2 << 3;
        final int n4 = n << 3;
        final int c = this.ai.c;
        final int[][] array = this.s[n][n2][0];
        final int[][] array2 = this.s[0][0][1];
        final int[][] array3 = this.s[0][0][2];
        final int n5 = this.x % this.y * 2 * 8 + n4;
        final int n6 = n3;
        int n7 = 8 - (n5 + 8 - c);
        if (n7 > 8) {
            n7 = 8;
        }
        int n8 = 8 - (n6 + 8 - this.ai.b);
        if (n8 > 8) {
            n8 = 8;
        }
        final int n9 = n6 * c + n5;
        if (this.ak) {
            for (int i = 0; i < n7; ++i) {
                int n10 = n9 + i;
                final int[] array4 = array[i];
                final int[] array5 = array2[i + n4 >> 1];
                final int[] array6 = array3[i + n4 >> 1];
                for (int j = 0; j < n8; ++j) {
                    final int n11 = n3 + j >> 1;
                    int n12 = array4[j];
                    int n13 = array5[n11];
                    int n14 = array6[n11];
                    if (n12 < 0) {
                        n12 = 0;
                    }
                    else if (n12 > 255) {
                        n12 = 255;
                    }
                    if (n13 < 0) {
                        n13 = 0;
                    }
                    else if (n13 > 255) {
                        n13 = 255;
                    }
                    if (n14 < 0) {
                        n14 = 0;
                    }
                    else if (n14 > 255) {
                        n14 = 255;
                    }
                    this.n[n10] = (0xFF000000 | n12 << 16 | n13 << 8 | n14);
                    n10 += c;
                }
            }
        }
        else {
            for (int k = 0; k < n7; ++k) {
                int n15 = n9 + k;
                final int[] array7 = array[k];
                final int[] array8 = array2[k + n4 >> 1];
                final int[] array9 = array3[k + n4 >> 1];
                for (int l = 0; l < n8; ++l) {
                    final int n16 = n3 + l >> 1;
                    final int n17 = array7[l];
                    final int n18 = array8[n16];
                    final int n19 = array9[n16];
                    int n20 = n17 + am[n19];
                    int n21 = n17 + (ao[n19] + ap[n18] >> 16);
                    int n22 = n17 + an[n18];
                    if (n20 < 0) {
                        n20 = 0;
                    }
                    else if (n20 > 255) {
                        n20 = 255;
                    }
                    if (n21 < 0) {
                        n21 = 0;
                    }
                    else if (n21 > 255) {
                        n21 = 255;
                    }
                    if (n22 < 0) {
                        n22 = 0;
                    }
                    else if (n22 > 255) {
                        n22 = 255;
                    }
                    this.n[n15] = (0xFF000000 | n20 << 16 | n21 << 8 | n22);
                    n15 += c;
                }
            }
        }
    }
    
    private final void b(final int n) {
        final int[][] array = this.s[n][0][0];
        final int[][] array2 = this.s[0][0][1];
        final int[][] array3 = this.s[0][0][2];
        final int c = this.ai.c;
        final int n2 = this.x % this.y * this.w * 8 + n * 8;
        int n3 = 8 - (n2 + 8 - c);
        if (n3 > 8) {
            n3 = 8;
        }
        int n4 = 8 - (8 - this.ai.b);
        if (n4 > 8) {
            n4 = 8;
        }
        if (this.ak) {
            for (int i = 0; i < n3; ++i) {
                int n5 = n2 + i;
                final int[] array4 = array[i];
                final int[] array5 = array2[i];
                final int[] array6 = array3[i];
                for (int j = 0; j < n4; ++j) {
                    int n6 = array4[j];
                    int n7 = array5[j];
                    int n8 = array6[j];
                    if (n6 < 0) {
                        n6 = 0;
                    }
                    else if (n6 > 255) {
                        n6 = 255;
                    }
                    if (n7 < 0) {
                        n7 = 0;
                    }
                    else if (n7 > 255) {
                        n7 = 255;
                    }
                    if (n8 < 0) {
                        n8 = 0;
                    }
                    else if (n8 > 255) {
                        n8 = 255;
                    }
                    this.n[n5] = (0xFF000000 | n6 << 16 | n7 << 8 | n8);
                    n5 += c;
                }
            }
        }
        else {
            for (int k = 0; k < n3; ++k) {
                int n9 = n2 + k;
                final int[] array7 = array[k];
                final int[] array8 = array2[k];
                final int[] array9 = array3[k];
                for (int l = 0; l < n4; ++l) {
                    final int n10 = array7[l];
                    final int n11 = array8[l];
                    final int n12 = array9[l];
                    int n13 = n10 + or.am[n12];
                    int n14 = n10 + (or.ao[n12] + or.ap[n11] >> 16);
                    int n15 = n10 + or.an[n11];
                    if (n13 < 0) {
                        n13 = 0;
                    }
                    else if (n13 > 255) {
                        n13 = 255;
                    }
                    if (n14 < 0) {
                        n14 = 0;
                    }
                    else if (n14 > 255) {
                        n14 = 255;
                    }
                    if (n15 < 0) {
                        n15 = 0;
                    }
                    else if (n15 > 255) {
                        n15 = 255;
                    }
                    this.n[n9] = (0xFF000000 | n13 << 16 | n14 << 8 | n15);
                    n9 += c;
                }
            }
        }
    }
    
    void d() throws Exception {
        this.ac = true;
        if (!this.q) {
            this.m.a((Object)null);
            throw new os("End of image found before start of image");
        }
    }
    
    public dx a(final ac ac, final dx dx, final fh fh) throws Exception {
        int i = 2;
        dx b = null;
        while (i > 0) {
            try {
                b = this.b(ac, dx, fh);
                i = 0;
            }
            catch (Exception ex) {
                if (--i == 0) {
                    throw ex;
                }
                fh.l = i;
            }
        }
        return b;
    }
    
    public dx b(final ac m, final dx dx, final fh fh) throws Exception {
        int i = 0;
        boolean b = true;
        if (fh != null && fh.l > 0) {
            b = false;
        }
        m.a(0L);
        if (dx.bk == null) {
            dx.bk = new Hashtable();
        }
        dx.u = 1;
        dx.v = 1;
        this.ae = new int[4][64];
        this.m = m;
        final int j = this.m.j();
        final int k = this.m.j();
        if (j == 255 && k == 79) {
            return null;
        }
        if (j != 255 && k != 216) {
            return null;
        }
        this.q = true;
        while (i == 0) {
            if (this.m.j() != 255) {
                throw new os("Section Marker (0xFF) Expected");
            }
            final int l = this.m.j();
            switch (l) {
                case 224: {
                    this.d(dx);
                    continue;
                }
                case 196: {
                    this.a(dx);
                    continue;
                }
                case 219: {
                    this.b();
                    continue;
                }
                case 221: {
                    this.e(dx);
                    continue;
                }
                case 192: {
                    dx.bk.put("Frame Type", "1");
                    this.a(dx, fh.u);
                    continue;
                }
                case 193:
                case 194:
                case 195:
                case 197:
                case 198:
                case 199:
                case 201:
                case 202:
                case 203:
                case 205:
                case 206:
                case 207: {
                    dx.bk.put("Frame Type", Integer.toHexString(l & 0xF));
                    throw new ot("Unsupported encoding: ".concat(String.valueOf(String.valueOf(Integer.toHexString(l).toUpperCase()))));
                }
                case 218: {
                    this.f(dx);
                    i = 1;
                    continue;
                }
                case 254: {
                    this.a(dx, b);
                    continue;
                }
                case 217: {
                    throw new os("No Image Data");
                }
                default: {
                    this.a(l, dx);
                    continue;
                }
            }
        }
        if (this.ad == null) {
            dx.l = "Non-JFIF JPEG";
            dx.ac = 100.0;
            dx.ad = 100.0;
            dx.bk.put("Resolution", "(100.0 x 100.0) DPI (Assumed)");
        }
        dx.aa = this.ai.d;
        dx.z = this.ai.a;
        dx.am = 24;
        if (dx.ac != dx.ad) {
            final double max = Math.max(dx.ac, dx.ad);
            dx.ac = max;
            dx.ad = max;
        }
        return dx;
    }
    
    void c(final dx dx) throws Exception {
        final Long n = dx.bk.get("Start of Scan");
        if (n == null) {
            throw new os("NULL File Offset");
        }
        this.d = new byte[409600];
        this.s = new int[4][4][3][8][8];
        this.ah = new int[3];
        this.p = -1;
        this.m.a((long)n);
        this.e = this.m.a(this.d);
        this.g = 0;
        this.f = 7;
        this.aa = (this.d[this.g++] & 0xFF);
        this.q = true;
        this.b(dx);
    }
    
    void d(final dx dx) throws Exception {
        final byte[] array = new byte[5];
        final int k = this.m.k();
        dx.bk.remove("xResolution");
        dx.bk.remove("yResolution");
        this.m.a(array);
        if (new String(array).trim().toUpperCase().equals("JFIF")) {
            this.ad = new aa3(null);
            dx.l = "JFIF Compliant JPEG";
            this.ad.a = new String(array).trim();
            this.ad.b = this.m.k();
            this.ad.c = this.m.j();
            this.ad.d = this.m.k();
            this.ad.e = this.m.k();
            this.ad.f = this.m.j();
            this.ad.g = this.m.j();
            final int n = this.ad.f * this.ad.g;
            this.m.a(n);
            this.m.a(k - 16 - n);
            switch (this.ad.c) {
                case 2: {
                    final aa3 ad = this.ad;
                    ad.d *= 2.54;
                    final aa3 ad2 = this.ad;
                    ad2.e *= 2.54;
                }
                case 1: {
                    dx.ac = this.ad.d;
                    dx.ad = this.ad.e;
                    if (dx.ac <= 16.0) {
                        dx.ac = 100.0;
                        dx.bk.put("xResolution Recalculated", "Below Threshold");
                    }
                    if (dx.ad <= 16.0) {
                        dx.ad = 100.0;
                        dx.bk.put("yResolution Recalculated", "Below Threshold");
                    }
                    dx.bk.put("xResolution", String.valueOf(String.valueOf(dx.ac)).concat(" DPI"));
                    dx.bk.put("yResolution", String.valueOf(String.valueOf(dx.ad)).concat(" DPI"));
                    break;
                }
                default: {
                    this.ad.d = 100.0;
                    this.ad.e = 100.0;
                    dx.ac = 100.0;
                    dx.ad = 100.0;
                    dx.bk.put("xResolution", "100.0 DPI (assumed)");
                    dx.bk.put("yResolution", "100.0 DPI (assumed)");
                    break;
                }
            }
            dx.bk.put("JFIF Header", this.ad);
        }
        else {
            dx.l = "Non-JFIF JPEG";
            this.ad.d = 100.0;
            this.ad.e = 100.0;
            dx.ac = 100.0;
            dx.ad = 100.0;
            dx.bk.put("xResolution", "100.0 DPI (assumed)");
            dx.bk.put("yResolution", "100.0 DPI (assumed)");
            this.m.a(k - 2);
        }
    }
    
    void e(final dx dx) throws Exception {
        this.m.l();
        this.o = this.m.k();
        if (this.o > 0) {
            this.j = true;
            dx.bk.put("RI", new Integer(this.o));
        }
        else {
            this.j = false;
        }
    }
    
    void a(final dx dx, final boolean b) throws Exception {
        final short l = this.m.l();
        byte[] array;
        if (b) {
            array = new byte[l];
        }
        else {
            array = new byte[l - 2];
        }
        this.m.a(array);
        dx.bk.put("Comment", new String(array).trim());
    }
    
    void a(final dx dx, final String s) throws Exception {
        final int k = this.m.k();
        this.ai.a = this.m.j();
        if (this.ai.a != 8) {
            this.ac = true;
            throw new os("This decoder can only display 8 bit precision jpg images: ".concat(String.valueOf(String.valueOf(this.ai.a))));
        }
        this.ai.b = this.m.k();
        dx.n = this.ai.b;
        this.ai.c = this.m.k();
        dx.m = this.ai.c;
        this.ai.d = this.m.j();
        if (this.ai.d > this.ai.f.length) {
            ji.io.h.c(s, String.valueOf(String.valueOf(new StringBuffer("The JPEGFast Decoder cannot support JPEGs with ").append(this.ai.d).append(" color components"))));
            throw new os(String.valueOf(String.valueOf(new StringBuffer("This decoder can not display JPEGs with ").append(this.ai.d).append(" color components"))));
        }
        dx.aa = this.ai.d;
        this.v = 0;
        this.w = 0;
        for (int i = 0; i < this.ai.d; ++i) {
            this.ai.f[i].a = this.m.j();
            this.ai.f[i].b = this.m.j();
            this.ai.f[i].c = (this.ai.f[i].b & 0xF);
            this.ai.f[i].d = this.ai.f[i].b >> 4;
            if (this.ai.f[i].c > this.v) {
                this.v = this.ai.f[i].c;
            }
            if (this.ai.f[i].d > this.w) {
                this.w = this.ai.f[i].d;
            }
            this.ai.f[i].e = this.m.j();
        }
        for (int j = 0; j < this.ai.d; ++j) {
            this.af[j] = this.w / this.ai.f[j].d;
            this.ag[j] = this.v / this.ai.f[j].c;
        }
        if (this.ai.d == 1) {
            this.ai.e = 4;
            dx.bk.put("Class", "GREY");
        }
        else {
            final aa0[] f = this.ai.f;
            if (f[0].d == 2) {
                if (f[0].c == 1) {
                    this.ai.e = 0;
                    dx.bk.put("Class", "(2x1):1:1");
                }
                else if (f[0].c == 2) {
                    this.ai.e = 3;
                    dx.bk.put("Class", "(2x2):1:1");
                }
            }
            else if (f[0].d == 1) {
                if (f[0].c == 2) {
                    this.ai.e = 1;
                    dx.bk.put("Class", "(1x2):1:1");
                }
                else if (f[0].c == 1) {
                    this.ai.e = 2;
                    dx.bk.put("Class", "1:1:1");
                }
            }
            else {
                this.ai.e = 5;
                dx.bk.put("Class", "(Undefined");
            }
        }
        this.y = (this.ai.c - 1) / (this.w * 8) + 1;
        this.z = (this.ai.b - 1) / (this.v * 8) + 1;
        dx.bk.put("MCU Width", new Integer(this.y));
        dx.bk.put("MCU Height", new Integer(this.z));
        this.m.a(k - 8 - 3 * this.ai.d);
    }
    
    void f(final dx dx) throws Exception {
        int k = this.m.k();
        this.aj.a = this.m.j();
        if (this.ai.d > 1 && this.aj.a == 1) {
            throw new Exception("Interleaved jpeg not supported by decoder");
        }
        k -= 3;
        final byte[] array = new byte[k];
        int n = 0;
        this.m.a(array);
        for (int i = 0; i < this.aj.a; ++i) {
            this.aj.b[i].a = array[n++];
            this.ab = array[n++];
            this.aj.b[i].b = this.ab;
            this.aj.b[i].c = (this.aj.b[i].b & 0xF);
            this.aj.b[i].d = this.aj.b[i].b >> 4;
            k -= 2;
        }
        dx.bk.put("Start of Scan", new Long(this.m.r()));
    }
    
    public void a(final ac ac, final ds b, final dx dx, final af l, final Component c, final fh fh) throws Exception {
        try {
            this.r = false;
            if (!this.i) {
                this.a(ac, dx, fh);
            }
            this.b = b;
            this.c = c;
            this.l = l;
            this.c(dx);
            this.e();
        }
        catch (Exception ex) {
            this.e();
            throw ex;
        }
        if (this.r) {
            b.a(c);
        }
    }
    
    public void e() {
        for (int i = 0; i < 4; ++i) {
            this.h[0][i] = null;
            this.h[1][i] = null;
        }
        this.d = null;
        this.n = null;
        this.s = null;
        this.ae = null;
        this.i = false;
    }
    
    static {
        or.a = new ov();
        or.k = new int[8192];
        for (int i = 0; i < 8192; ++i) {
            or.k[i] = -1;
        }
        al = new int[] { 0, 1, 8, 16, 9, 2, 3, 10, 17, 24, 32, 25, 18, 11, 4, 5, 12, 19, 26, 33, 40, 48, 41, 34, 27, 20, 13, 6, 7, 14, 21, 28, 35, 42, 49, 56, 57, 50, 43, 36, 29, 22, 15, 23, 30, 37, 44, 51, 58, 59, 52, 45, 38, 31, 39, 46, 53, 60, 61, 54, 47, 55, 62, 63 };
        am = new int[] { -179, -178, -177, -175, -174, -172, -171, -170, -168, -167, -165, -164, -163, -161, -160, -158, -157, -156, -154, -153, -151, -150, -149, -147, -146, -144, -143, -143, -140, -139, -137, -136, -135, -133, -132, -130, -129, -128, -126, -125, -123, -122, -121, -119, -118, -116, -115, -114, -112, -111, -109, -108, -107, -105, -104, -102, -101, -100, -98, -97, -95, -94, -93, -91, -90, -88, -87, -86, -84, -83, -81, -80, -79, -77, -76, -74, -73, -72, -70, -69, -67, -66, -64, -63, -62, -60, -59, -57, -56, -55, -53, -52, -50, -49, -48, -46, -45, -43, -42, -41, -39, -38, -36, -35, -34, -32, -31, -29, -28, -27, -25, -24, -22, -21, -20, -18, -17, -15, -14, -13, -11, -10, -8, -7, -6, -4, -3, -1, 0, 1, 3, 4, 6, 7, 8, 10, 11, 13, 14, 15, 17, 18, 20, 21, 22, 24, 25, 27, 28, 29, 31, 32, 34, 35, 36, 38, 39, 41, 42, 43, 45, 46, 48, 49, 50, 52, 53, 55, 56, 57, 59, 60, 62, 63, 64, 66, 67, 69, 70, 72, 73, 74, 76, 77, 79, 80, 81, 83, 84, 86, 87, 88, 90, 91, 93, 94, 95, 97, 98, 100, 101, 102, 104, 105, 107, 108, 109, 111, 112, 114, 115, 116, 118, 119, 121, 122, 123, 125, 126, 128, 129, 130, 132, 133, 135, 136, 137, 139, 140, 142, 143, 144, 146, 147, 149, 150, 151, 153, 154, 156, 157, 158, 160, 161, 163, 164, 165, 167, 168, 170, 171, 172, 174, 175, 177, 178 };
        an = new int[] { -227, -225, -223, -222, -220, -218, -216, -214, -213, -211, -209, -207, -206, -204, -202, -200, -198, -197, -195, -193, -191, -190, -188, -186, -184, -183, -181, -179, -177, -175, -174, -172, -170, -168, -167, -165, -163, -161, -159, -158, -156, -154, -152, -151, -149, -147, -145, -144, -142, -140, -138, -136, -135, -133, -131, -129, -128, -126, -124, -122, -120, -119, -117, -115, -113, -112, -110, -108, -106, -105, -103, -101, -99, -97, -96, -94, -92, -90, -89, -87, -85, -83, -82, -80, -78, -76, -74, -73, -71, -69, -67, -66, -64, -62, -60, -58, -57, -55, -53, -51, -50, -48, -46, -44, -43, -41, -39, -37, -35, -34, -32, -30, -28, -27, -25, -23, -21, -19, -18, -16, -14, -12, -11, -9, -7, -5, -4, -2, 0, 2, 4, 5, 7, 9, 11, 12, 14, 16, 18, 19, 21, 23, 25, 27, 28, 30, 32, 34, 35, 37, 39, 41, 43, 44, 46, 48, 50, 51, 53, 55, 57, 58, 60, 62, 64, 66, 67, 69, 71, 73, 74, 76, 78, 80, 82, 83, 85, 87, 89, 90, 92, 94, 96, 97, 99, 101, 103, 105, 106, 108, 110, 112, 113, 115, 117, 119, 120, 122, 124, 126, 128, 129, 131, 133, 135, 136, 138, 140, 142, 144, 145, 147, 149, 151, 152, 154, 156, 158, 159, 161, 163, 165, 167, 168, 170, 172, 174, 175, 177, 179, 181, 183, 184, 186, 188, 190, 191, 193, 195, 197, 198, 200, 202, 204, 206, 207, 209, 211, 213, 214, 216, 218, 220, 222, 223, 225 };
        ao = new int[] { 5990656, 5943854, 5897052, 5850250, 5803448, 5756646, 5709844, 5663042, 5616240, 5569438, 5522636, 5475834, 5429032, 5382230, 5335428, 5288626, 5241824, 5195022, 5148220, 5101418, 5054616, 5007814, 4961012, 4914210, 4867408, 4820606, 4773804, 4727002, 4680200, 4633398, 4586596, 4539794, 4492992, 4446190, 4399388, 4352586, 4305784, 4258982, 4212180, 4165378, 4118576, 4071774, 4024972, 3978170, 3931368, 3884566, 3837764, 3790962, 3744160, 3697358, 3650556, 3603754, 3556952, 3510150, 3463348, 3416546, 3369744, 3322942, 3276140, 3229338, 3182536, 3135734, 3088932, 3042130, 2995328, 2948526, 2901724, 2854922, 2808120, 2761318, 2714516, 2667714, 2620912, 2574110, 2527308, 2480506, 2433704, 2386902, 2340100, 2293298, 2246496, 2199694, 2152892, 2106090, 2059288, 2012486, 1965684, 1918882, 1872080, 1825278, 1778476, 1731674, 1684872, 1638070, 1591268, 1544466, 1497664, 1450862, 1404060, 1357258, 1310456, 1263654, 1216852, 1170050, 1123248, 1076446, 1029644, 982842, 936040, 889238, 842436, 795634, 748832, 702030, 655228, 608426, 561624, 514822, 468020, 421218, 374416, 327614, 280812, 234010, 187208, 140406, 93604, 46802, 0, -46802, -93604, -140406, -187208, -234010, -280812, -327614, -374416, -421218, -468020, -514822, -561624, -608426, -655228, -702030, -748832, -795634, -842436, -889238, -936040, -982842, -1029644, -1076446, -1123248, -1170050, -1216852, -1263654, -1310456, -1357258, -1404060, -1450862, -1497664, -1544466, -1591268, -1638070, -1684872, -1731674, -1778476, -1825278, -1872080, -1918882, -1965684, -2012486, -2059288, -2106090, -2152892, -2199694, -2246496, -2293298, -2340100, -2386902, -2433704, -2480506, -2527308, -2574110, -2620912, -2667714, -2714516, -2761318, -2808120, -2854922, -2901724, -2948526, -2995328, -3042130, -3088932, -3135734, -3182536, -3229338, -3276140, -3322942, -3369744, -3416546, -3463348, -3510150, -3556952, -3603754, -3650556, -3697358, -3744160, -3790962, -3837764, -3884566, -3931368, -3978170, -4024972, -4071774, -4118576, -4165378, -4212180, -4258982, -4305784, -4352586, -4399388, -4446190, -4492992, -4539794, -4586596, -4633398, -4680200, -4727002, -4773804, -4820606, -4867408, -4914210, -4961012, -5007814, -5054616, -5101418, -5148220, -5195022, -5241824, -5288626, -5335428, -5382230, -5429032, -5475834, -5522636, -5569438, -5616240, -5663042, -5709844, -5756646, -5803448, -5850250, -5897052, -5943854 };
        ap = new int[] { 2919680, 2897126, 2874572, 2852018, 2829464, 2806910, 2784356, 2761802, 2739248, 2716694, 2694140, 2671586, 2649032, 2626478, 2603924, 2581370, 2558816, 2536262, 2513708, 2491154, 2468600, 2446046, 2423492, 2400938, 2378384, 2355830, 2333276, 2310722, 2288168, 2265614, 2243060, 2220506, 2197952, 2175398, 2152844, 2130290, 2107736, 2085182, 2062628, 2040074, 2017520, 1994966, 1972412, 1949858, 1927304, 1904750, 1882196, 1859642, 1837088, 1814534, 1791980, 1769426, 1746872, 1724318, 1701764, 1679210, 1656656, 1634102, 1611548, 1588994, 1566440, 1543886, 1521332, 1498778, 1476224, 1453670, 1431116, 1408562, 1386008, 1363454, 1340900, 1318346, 1295792, 1273238, 1250684, 1228130, 1205576, 1183022, 1160468, 1137914, 1115360, 1092806, 1070252, 1047698, 1025144, 1002590, 980036, 957482, 934928, 912374, 889820, 867266, 844712, 822158, 799604, 777050, 754496, 731942, 709388, 686834, 664280, 641726, 619172, 596618, 574064, 551510, 528956, 506402, 483848, 461294, 438740, 416186, 393632, 371078, 348524, 325970, 303416, 280862, 258308, 235754, 213200, 190646, 168092, 145538, 122984, 100430, 77876, 55322, 32768, 10214, -12340, -34894, -57448, -80002, -102556, -125110, -147664, -170218, -192772, -215326, -237880, -260434, -282988, -305542, -328096, -350650, -373204, -395758, -418312, -440866, -463420, -485974, -508528, -531082, -553636, -576190, -598744, -621298, -643852, -666406, -688960, -711514, -734068, -756622, -779176, -801730, -824284, -846838, -869392, -891946, -914500, -937054, -959608, -982162, -1004716, -1027270, -1049824, -1072378, -1094932, -1117486, -1140040, -1162594, -1185148, -1207702, -1230256, -1252810, -1275364, -1297918, -1320472, -1343026, -1365580, -1388134, -1410688, -1433242, -1455796, -1478350, -1500904, -1523458, -1546012, -1568566, -1591120, -1613674, -1636228, -1658782, -1681336, -1703890, -1726444, -1748998, -1771552, -1794106, -1816660, -1839214, -1861768, -1884322, -1906876, -1929430, -1951984, -1974538, -1997092, -2019646, -2042200, -2064754, -2087308, -2109862, -2132416, -2154970, -2177524, -2200078, -2222632, -2245186, -2267740, -2290294, -2312848, -2335402, -2357956, -2380510, -2403064, -2425618, -2448172, -2470726, -2493280, -2515834, -2538388, -2560942, -2583496, -2606050, -2628604, -2651158, -2673712, -2696266, -2718820, -2741374, -2763928, -2786482, -2809036, -2831590 };
    }
    
    private static class aa5
    {
        int a;
        int b;
        int c;
        
        public String toString() {
            return String.valueOf(String.valueOf(new StringBuffer("Val: ").append(this.c).append("\tLength: ").append(this.b).append("\tCode: ").append(this.c)));
        }
    }
    
    private static class aa3
    {
        String a;
        int b;
        int c;
        double d;
        double e;
        int f;
        int g;
        
        private aa3() {
            this.a = "";
        }
        
        public String toString() {
            return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a))).append(" ").append((this.b & 0xFF00) >> 8).append('.').append(this.b & 0xFF)));
        }
    }
    
    private static class aa0
    {
        int a;
        int b;
        int c;
        int d;
        int e;
    }
    
    private static class aaz
    {
        int a;
        int b;
        int c;
        int d;
        int e;
        aa0[] f;
        
        aaz() {
            this.f = new aa0[3];
            for (int i = 0; i < this.f.length; ++i) {
                this.f[i] = new aa0(null);
            }
        }
    }
    
    private static class aa2
    {
        int a;
        int b;
        int c;
        int d;
    }
    
    private static class aa1
    {
        int a;
        aa2[] b;
        
        aa1() {
            this.b = new aa2[3];
            for (int i = 0; i < this.b.length; ++i) {
                this.b[i] = new aa2(null);
            }
        }
    }
    
    private static class aa4
    {
        int[] a;
        int[] b;
        
        aa4() {
            this.a = new int[16];
            this.b = new int[256];
        }
    }
    
    interface aen
    {
    }
}
