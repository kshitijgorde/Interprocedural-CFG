// 
// Decompiled by Procyon v0.5.30
// 

package com.jinsight.jetchart;

import java.awt.image.ImageProducer;
import java.awt.Image;
import java.io.OutputStream;
import java.util.Enumeration;
import java.io.IOException;

class s extends r
{
    static final int o = -1;
    static final int p = 12;
    static final int q = 5003;
    private boolean r;
    int s;
    int t;
    int[][] u;
    n v;
    int w;
    int x;
    boolean y;
    int z;
    int A;
    int B;
    int C;
    int D;
    int E;
    int F;
    int G;
    int[] H;
    int[] I;
    int J;
    int K;
    boolean L;
    int M;
    int N;
    int O;
    int P;
    int Q;
    int[] R;
    int S;
    byte[] T;
    
    void a(final int s, final int t) throws IOException {
        this.s = s;
        this.t = t;
        this.u = new int[t][s];
    }
    
    void a(final int n, final int n2, final int n3, final int n4, final int[] array, final int n5, final int n6) throws IOException {
        final boolean g = GraphSerie.G;
        int n7 = 0;
        while (true) {
            while (true) {
                Label_0040: {
                    if (!g) {
                        break Label_0040;
                    }
                    System.arraycopy(array, n7 * n6 + n5, this.u[n2 + n7], n, n3);
                    ++n7;
                }
                if (n7 < n4) {
                    continue;
                }
                break;
            }
            if (!g) {
                return;
            }
            continue;
        }
    }
    
    void a() throws IOException {
        final boolean g = GraphSerie.G;
        int n = -1;
        int n2 = -1;
        this.v = new n();
        int n3 = 0;
        int n4 = 0;
        int n7;
        int n8;
        while (true) {
            while (true) {
                Label_0216: {
                    if (!g) {
                        break Label_0216;
                    }
                    final int s = this.s;
                    int n5 = 0;
                    while (true) {
                        Label_0204: {
                            if (!g) {
                                break Label_0204;
                            }
                            int n6 = this.u[n4][n5];
                            final boolean b = n6 >>> 24 < 128;
                            Label_0116: {
                                if (b) {
                                    if (n < 0) {
                                        n = n3;
                                        n2 = n6;
                                        if (!g) {
                                            break Label_0116;
                                        }
                                    }
                                    if (n6 != n2) {
                                        n6 = (this.u[n4][n5] = n2);
                                    }
                                }
                            }
                            c c = (c)this.v.get(n6);
                            Label_0201: {
                                if (c == null) {
                                    if (n3 >= 256) {
                                        throw new IOException(b("51Tlv 0Blx.2T>ha8T>; ~|\u0005]"));
                                    }
                                    c = new c(n6, 1, n3, b);
                                    ++n3;
                                    this.v.put(n6, c);
                                    if (!g) {
                                        break Label_0201;
                                    }
                                }
                                final c c2 = c;
                                ++c2.count;
                            }
                            ++n5;
                        }
                        if (n5 < this.s) {
                            continue;
                        }
                        break;
                    }
                    ++n4;
                }
                if (n4 < this.t) {
                    continue;
                }
                break;
            }
            n7 = n3;
            n8 = 2;
            if (g) {
                continue;
            }
            break;
        }
        int n9 = 0;
        Label_0274: {
            if (n7 <= n8) {
                n9 = 1;
                if (!g) {
                    break Label_0274;
                }
            }
            if (n3 <= 4) {
                n9 = 2;
                if (!g) {
                    break Label_0274;
                }
            }
            if (n3 <= 16) {
                n9 = 4;
                if (!g) {
                    break Label_0274;
                }
            }
            n9 = 8;
        }
        final int n10 = 1 << n9;
        final byte[] array = new byte[n10];
        final byte[] array2 = new byte[n10];
        final byte[] array3 = new byte[n10];
        final Enumeration elements = this.v.elements();
        while (true) {
            while (true) {
                Label_0384: {
                    if (!g) {
                        break Label_0384;
                    }
                    final Object nextElement = elements.nextElement();
                    final c c3 = (c)nextElement;
                    array[c3.index] = (byte)(c3.rgb >> 16 & 0xFF);
                    array2[c3.index] = (byte)(c3.rgb >> 8 & 0xFF);
                    array3[c3.index] = (byte)(c3.rgb & 0xFF);
                }
                if (elements.hasMoreElements()) {
                    continue;
                }
                break;
            }
            final Object nextElement = this;
            if (!g) {
                this.a(super.c, this.s, this.t, this.r, (byte)0, n, n9, array, array2, array3);
                return;
            }
            continue;
        }
    }
    
    byte b(final int n, final int n2) throws IOException {
        final c c = (c)this.v.get(this.u[n2][n]);
        if (c == null) {
            throw new IOException(b("\"1W#ia0T8;'1N\"\u007f"));
        }
        return (byte)c.index;
    }
    
    static void a(final OutputStream outputStream, final String s) throws IOException {
        outputStream.write(s.getBytes());
    }
    
    void a(final OutputStream outputStream, final int w, final int x, final boolean y, final byte b, final int n, final int n2, final byte[] array, final byte[] array2, final byte[] array3) throws IOException {
        final boolean g = GraphSerie.G;
        this.w = w;
        this.x = x;
        this.y = y;
        final int n3 = 1 << n2;
        final int n4 = 0;
        this.B = w * x;
        this.setChanged();
        this.notifyObservers(new Integer(this.B));
        this.C = 0;
        int n5 = 0;
        Label_0082: {
            if (n2 <= 1) {
                n5 = 2;
                if (!g) {
                    break Label_0082;
                }
            }
            n5 = n2;
        }
        this.z = 0;
        this.A = 0;
        a(outputStream, b("\u0006\u0017}t\" "));
        this.a(w, outputStream);
        this.a(x, outputStream);
        this.a((byte)((byte)(0xFFFFFF80 | 0x70) | (byte)(n2 - 1)), outputStream);
        this.a(b, outputStream);
        this.a((byte)0, outputStream);
        int n6 = 0;
        while (true) {
            while (true) {
                Label_0197: {
                    if (!g) {
                        break Label_0197;
                    }
                    this.a(array[n6], outputStream);
                    this.a(array2[n6], outputStream);
                    this.a(array3[n6], outputStream);
                    ++n6;
                }
                if (n6 < n3) {
                    continue;
                }
                break;
            }
            if (!g) {
                if (n != -1) {
                    this.a((byte)33, outputStream);
                    this.a((byte)(-7), outputStream);
                    this.a((byte)4, outputStream);
                    this.a((byte)1, outputStream);
                    this.a((byte)0, outputStream);
                    this.a((byte)0, outputStream);
                    this.a((byte)n, outputStream);
                    this.a((byte)0, outputStream);
                }
                this.a((byte)44, outputStream);
                this.a(n4, outputStream);
                this.a(n4, outputStream);
                this.a(w, outputStream);
                this.a(x, outputStream);
                Label_0323: {
                    if (y) {
                        this.a((byte)64, outputStream);
                        if (!g) {
                            break Label_0323;
                        }
                    }
                    this.a((byte)0, outputStream);
                }
                this.a((byte)n5, outputStream);
                this.b(n5 + 1, outputStream);
                this.a((byte)0, outputStream);
                this.a((byte)59, outputStream);
                return;
            }
            continue;
        }
    }
    
    void g() {
        final boolean g = GraphSerie.G;
        ++this.z;
        if (this.z == this.w) {
            this.z = 0;
            if (!this.y) {
                ++this.A;
                if (!g) {
                    return;
                }
            }
            Label_0206: {
                switch (this.C) {
                    case 0: {
                        this.A += 8;
                        if (this.A >= this.x) {
                            ++this.C;
                            this.A = 4;
                        }
                        if (g) {
                            break Label_0206;
                        }
                        break;
                    }
                    case 1: {
                        this.A += 8;
                        if (this.A >= this.x) {
                            ++this.C;
                            this.A = 2;
                        }
                        if (g) {
                            break Label_0206;
                        }
                        break;
                    }
                    case 2: {
                        this.A += 4;
                        if (this.A >= this.x) {
                            ++this.C;
                            this.A = 1;
                        }
                        if (g) {
                            break Label_0206;
                        }
                        break;
                    }
                    case 3: {
                        this.A += 2;
                        if (g) {}
                        break;
                    }
                }
            }
        }
    }
    
    int h() throws IOException {
        if (this.B == 0) {
            return -1;
        }
        --this.B;
        final byte b = this.b(this.z, this.A);
        this.g();
        return b & 0xFF;
    }
    
    void a(final int n, final OutputStream outputStream) throws IOException {
        this.a((byte)(n & 0xFF), outputStream);
        this.a((byte)(n >> 8 & 0xFF), outputStream);
    }
    
    void a(final byte b, final OutputStream outputStream) throws IOException {
        outputStream.write(b);
    }
    
    final int a(final int n) {
        return (1 << n) - 1;
    }
    
    void b(final int m, final OutputStream outputStream) throws IOException {
        final boolean g = GraphSerie.G;
        this.M = m;
        this.L = false;
        this.D = this.M;
        this.F = this.a(this.D);
        this.N = 1 << m - 1;
        this.O = this.N + 1;
        this.K = this.N + 2;
        this.i();
        int h = this.h();
        int n = 0;
        int j = this.J;
        int i;
        while (true) {
            while (true) {
                Label_0094: {
                    if (!g) {
                        break Label_0094;
                    }
                    ++n;
                    j *= 2;
                }
                if (j < 65536) {
                    continue;
                }
                break;
            }
            n = 8 - n;
            i = this.J;
            this.b(i);
            this.c(this.N, outputStream);
            if (g) {
                continue;
            }
            break;
        }
    Label_0378:
        while (true) {
            final int h2 = this.h();
        Label_0386:
            while (true) {
                int k = 0;
                int g2 = 0;
                if (k == g2) {
                    this.c(h, outputStream);
                    this.c(this.O, outputStream);
                    if (!g && !g) {
                        return;
                    }
                }
                else {
                    j = (h2 << this.E) + h;
                }
                int n2 = h2 << n ^ h;
                do {
                    if (this.H[n2] == j) {
                        h = this.I[n2];
                        if (!g) {
                            continue Label_0378;
                        }
                    }
                    if (this.H[n2] >= 0) {
                        int n3 = i - n2;
                        if (n2 == 0) {
                            n3 = 1;
                        }
                        do {
                            if ((n2 -= n3) < 0) {
                                n2 += i;
                            }
                            if (this.H[n2] == j) {
                                h = this.I[n2];
                                if (g) {
                                    continue;
                                }
                                continue Label_0378;
                            }
                        } while (this.H[n2] >= 0);
                    }
                    this.c(h, outputStream);
                    h = h2;
                    k = this.K;
                    g2 = this.G;
                    if (g) {
                        continue Label_0386;
                    }
                    Label_0323: {
                        if (k < g2) {
                            this.I[n2] = this.K++;
                            this.H[n2] = j;
                            if (!g) {
                                break Label_0323;
                            }
                        }
                        this.b(outputStream);
                    }
                    this.setChanged();
                    this.notifyObservers(new Integer(this.s * this.t - this.B));
                    if (super.d) {
                        continue;
                    }
                    continue Label_0378;
                } while (g);
                break;
            }
            throw new IOException(b("\u0003\f~\rP\u001e\rr\u000bU\u0004\u001a"));
        }
    }
    
    void c(final int p2, final OutputStream outputStream) throws IOException {
        final boolean g = GraphSerie.G;
        this.P &= this.R[this.Q];
        Label_0053: {
            if (this.Q > 0) {
                this.P |= p2 << this.Q;
                if (!g) {
                    break Label_0053;
                }
            }
            this.P = p2;
        }
        this.Q += this.D;
    Label_0253_Outer:
        while (true) {
            while (true) {
                Label_0106: {
                    if (!g) {
                        break Label_0106;
                    }
                    this.b((byte)(this.P & 0xFF), outputStream);
                    this.P >>= 8;
                    final s s = this;
                    final s s2 = this;
                    s.Q = s2.Q - 8;
                }
                if (this.Q >= 8) {
                    continue;
                }
                break;
            }
            final s s = this;
            final s s2 = this;
            if (!g) {
                Label_0215: {
                    if (this.K > this.F || this.L) {
                        if (this.L) {
                            final int m = this.M;
                            this.D = m;
                            this.F = this.a(m);
                            this.L = false;
                            if (!g) {
                                break Label_0215;
                            }
                        }
                        ++this.D;
                        if (this.D == this.E) {
                            this.F = this.G;
                            if (!g) {
                                break Label_0215;
                            }
                        }
                        this.F = this.a(this.D);
                    }
                }
                if (p2 == this.O) {
                    while (true) {
                        while (true) {
                            Label_0263: {
                                if (!g) {
                                    break Label_0263;
                                }
                                this.b((byte)(this.P & 0xFF), outputStream);
                                this.P >>= 8;
                                final s s3 = this;
                                final s s4 = this;
                                s3.Q = s4.Q - 8;
                            }
                            if (this.Q > 0) {
                                continue Label_0253_Outer;
                            }
                            break;
                        }
                        final s s3 = this;
                        final s s4 = this;
                        if (g) {
                            continue;
                        }
                        break;
                    }
                    this.c(outputStream);
                }
                return;
            }
            continue;
        }
    }
    
    void b(final OutputStream outputStream) throws IOException {
        this.b(this.J);
        this.K = this.N + 2;
        this.L = true;
        this.c(this.N, outputStream);
    }
    
    void b(final int n) {
        final boolean g = GraphSerie.G;
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0020: {
                    if (!g) {
                        break Label_0020;
                    }
                    this.H[n2] = -1;
                    ++n2;
                }
                if (n2 < n) {
                    continue;
                }
                break;
            }
            if (!g) {
                return;
            }
            continue;
        }
    }
    
    void i() {
        this.S = 0;
    }
    
    void b(final byte b, final OutputStream outputStream) throws IOException {
        this.T[this.S++] = b;
        if (this.S >= 254) {
            this.c(outputStream);
        }
    }
    
    void c(final OutputStream outputStream) throws IOException {
        if (this.S > 0) {
            outputStream.write(this.S);
            outputStream.write(this.T, 0, this.S);
            this.S = 0;
        }
    }
    
    s(final Image image, final OutputStream outputStream) throws IOException {
        super(image, outputStream);
        this.r = false;
        this.C = 0;
        this.E = 12;
        this.G = 4096;
        this.H = new int[5003];
        this.I = new int[5003];
        this.J = 5003;
        this.K = 0;
        this.L = false;
        this.P = 0;
        this.Q = 0;
        this.R = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535 };
        this.T = new byte[256];
    }
    
    s(final Image image, final OutputStream outputStream, final boolean r) throws IOException {
        super(image, outputStream);
        this.r = false;
        this.C = 0;
        this.E = 12;
        this.G = 4096;
        this.H = new int[5003];
        this.I = new int[5003];
        this.J = 5003;
        this.K = 0;
        this.L = false;
        this.P = 0;
        this.Q = 0;
        this.R = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535 };
        this.T = new byte[256];
        this.r = r;
    }
    
    s(final ImageProducer imageProducer, final OutputStream outputStream) throws IOException {
        super(imageProducer, outputStream);
        this.r = false;
        this.C = 0;
        this.E = 12;
        this.G = 4096;
        this.H = new int[5003];
        this.I = new int[5003];
        this.J = 5003;
        this.K = 0;
        this.L = false;
        this.P = 0;
        this.Q = 0;
        this.R = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535 };
        this.T = new byte[256];
    }
    
    s(final ImageProducer imageProducer, final OutputStream outputStream, final boolean r) throws IOException {
        super(imageProducer, outputStream);
        this.r = false;
        this.C = 0;
        this.E = 12;
        this.G = 4096;
        this.H = new int[5003];
        this.I = new int[5003];
        this.J = 5003;
        this.K = 0;
        this.L = false;
        this.P = 0;
        this.Q = 0;
        this.R = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535 };
        this.T = new byte[256];
        this.r = r;
    }
    
    s() {
        this.r = false;
        this.C = 0;
        this.E = 12;
        this.G = 4096;
        this.H = new int[5003];
        this.I = new int[5003];
        this.J = 5003;
        this.K = 0;
        this.L = false;
        this.P = 0;
        this.Q = 0;
        this.R = new int[] { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535 };
        this.T = new byte[256];
    }
    
    private static String b(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0089: {
                if (length > 1) {
                    break Label_0089;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = 'A';
                            break;
                        }
                        case 1: {
                            c2 = '^';
                            break;
                        }
                        case 2: {
                            c2 = ';';
                            break;
                        }
                        case 3: {
                            c2 = 'L';
                            break;
                        }
                        default: {
                            c2 = '\u001b';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}
