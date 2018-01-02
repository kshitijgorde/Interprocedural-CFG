// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.StreamCorruptedException;
import java.util.Random;

public final class cT extends bK implements bZ
{
    private float[] a;
    private int b;
    private int c;
    private float d;
    private float[] e;
    private int f;
    private float g;
    private Random h;
    private N i;
    private c as;
    private boolean at;
    
    public cT() {
        this.h = new Random();
        this.i = new N();
        this.as = new c(this.i);
        this.at = true;
    }
    
    public final void a(final int n, final int n2, final int n3, final int n4) {
        super.a(n, n2, n3, n4);
        this.G.a();
        this.a = new float[40];
        this.b = 0;
        this.c = 40;
        this.d = 0.0f;
        this.e = new float[3];
        this.f = 0;
        this.g = 0.0f;
    }
    
    public final int a(final cH ch, final float[] array) {
        int n = 0;
        final float[] array2 = new float[3];
        float n2 = 0.0f;
        int c = 40;
        float n3 = 0.0f;
        float n4 = 0.0f;
        if (ch == null && this.ar != 0) {
            this.I = 0;
        }
        else {
            if (ch == null) {
                final float n5 = (float)Math.exp(-0.04 * this.b * this.b);
                float n7;
                final float n6 = (this.e[0] < this.e[1]) ? ((this.e[1] < this.e[2]) ? (n7 = this.e[1]) : ((this.e[0] < this.e[2]) ? (n7 = this.e[2]) : (n7 = this.e[0]))) : ((this.e[2] < this.e[1]) ? (n7 = this.e[1]) : ((this.e[2] < this.e[0]) ? (n7 = this.e[2]) : (n7 = this.e[0])));
                final float d = n7;
                if (n6 < this.d) {
                    this.d = d;
                }
                float d2;
                if ((d2 = this.d) > 0.95f) {
                    d2 = 0.95f;
                }
                final float n8 = d2 * n5;
                System.arraycopy(this.Y, this.K, this.Y, 0, this.P - this.K);
                System.arraycopy(this.aa, this.K, this.aa, 0, this.P - this.K);
                for (int i = 0; i < this.M; ++i) {
                    final int n9 = this.L * i;
                    final int n10 = this.Z + n9;
                    final int n11 = this.ab + n9;
                    if (this.at) {
                        float h;
                        float j;
                        if (this.H[this.I] != null) {
                            h = this.H[this.I].h;
                            j = this.H[this.I].i;
                        }
                        else {
                            j = (h = 0.7f);
                        }
                        final float n12 = (1.0f - (1.0f - 0.9f * h) / (1.0f - 0.9f * j)) / 0.9f;
                        ba.a(h, this.ah, this.ak, this.O);
                        ba.a(j, this.ah, this.al, this.O);
                        ba.a(n12, this.ah, this.am, this.O);
                    }
                    float n13 = 0.0f;
                    for (int k = 0; k < this.K; ++k) {
                        n13 += this.ac[k] * this.ac[k];
                    }
                    final float n14 = (float)Math.sqrt(n13 / this.K);
                    for (int l = 0; l < this.L; ++l) {
                        this.aa[n11 + l] = n8 * this.aa[n11 + l - this.c] + n5 * (float)Math.sqrt(1.0f - n8) * 3.0f * n14 * (this.h.nextFloat() - 0.5f);
                    }
                    for (int n15 = 0; n15 < this.L; ++n15) {
                        this.Y[n10 + n15] = this.aa[n11 + n15];
                    }
                    if (this.at) {
                        ba.a(this.Y, n10, this.al, this.ak, this.L, this.O, this.ai, this.O);
                        ba.a(this.Y, n10, this.am, this.ah, this.L, this.O, this.ai, 0);
                    }
                    else {
                        for (int n16 = 0; n16 < this.O; ++n16) {
                            this.ai[this.O + n16] = 0.0f;
                        }
                        ba.a(this.Y, n10, this.ah, this.Y, n10, this.L, this.O, this.ai);
                    }
                }
                array[0] = this.Y[0] + 0.0f * this.X;
                for (int n17 = 1; n17 < this.K; ++n17) {
                    array[n17] = this.Y[n17] + 0.0f * array[n17 - 1];
                }
                this.X = array[this.K - 1];
                this.J = 0;
                ++this.b;
                this.e[this.f++] = n8;
                if (this.f > 2) {
                    this.f = 0;
                }
                return 0;
            }
            int b;
            do {
                if (ch.b(1) != 0) {
                    final int b2 = ch.b(3);
                    int n18;
                    if ((n18 = ax.a[b2]) < 0) {
                        throw new StreamCorruptedException("Invalid sideband mode encountered (1st sideband): " + b2);
                    }
                    n18 -= 4;
                    ch.a(n18);
                    if (ch.b(1) != 0) {
                        final int b3 = ch.b(3);
                        int n19;
                        if ((n19 = ax.a[b3]) < 0) {
                            throw new StreamCorruptedException("Invalid sideband mode encountered. (2nd sideband): " + b3);
                        }
                        n19 -= 4;
                        ch.a(n19);
                        if (ch.b(1) != 0) {
                            throw new StreamCorruptedException("More than two sideband layers found");
                        }
                    }
                }
                if ((b = ch.b(4)) == 15) {
                    return 1;
                }
                if (b == 14) {
                    this.as.a(ch);
                }
                else if (b == 13) {
                    ch.a(5 + ch.b(4) * 8);
                }
                else {
                    if (b > 8) {
                        throw new StreamCorruptedException("Invalid mode encountered: " + b);
                    }
                    continue;
                }
            } while (b > 8);
            this.I = b;
        }
        System.arraycopy(this.Y, this.K, this.Y, 0, this.P - this.K);
        System.arraycopy(this.aa, this.K, this.aa, 0, this.P - this.K);
        if (this.H[this.I] == null) {
            ba.a(0.93f, this.ah, this.ad, 10);
            float n20 = 0.0f;
            for (int n21 = 0; n21 < this.K; ++n21) {
                n20 += this.ac[n21] * this.ac[n21];
            }
            final float n22 = (float)Math.sqrt(n20 / this.K);
            for (int ab = this.ab; ab < this.ab + this.K; ++ab) {
                this.aa[ab] = 3.0f * n22 * (this.h.nextFloat() - 0.5f);
            }
            this.J = 1;
            ba.a(this.aa, this.ab, this.ad, this.Y, this.Z, this.K, this.O, this.ai);
            array[0] = this.Y[this.Z] + 0.0f * this.X;
            for (int n23 = 1; n23 < this.K; ++n23) {
                array[n23] = this.Y[this.Z + n23] + 0.0f * array[n23 - 1];
            }
            this.X = array[this.K - 1];
            return this.b = 0;
        }
        this.H[this.I].e.a(this.ae, this.O, ch);
        if (this.b != 0) {
            float n24 = 0.0f;
            for (int n25 = 0; n25 < this.O; ++n25) {
                n24 += Math.abs(this.af[n25] - this.ae[n25]);
            }
            final float n26 = (float)(0.6 * Math.exp(-0.2 * n24));
            for (int n27 = 0; n27 < 2 * this.O; ++n27) {
                final float[] ai = this.ai;
                final int n28 = n27;
                ai[n28] *= n26;
            }
        }
        if (this.J != 0 || this.b != 0) {
            for (int n29 = 0; n29 < this.O; ++n29) {
                this.af[n29] = this.ae[n29];
            }
        }
        if (this.H[this.I].a != -1) {
            n = this.Q + ch.b(7);
        }
        if (this.H[this.I].b != 0) {
            n2 = 0.066667f * ch.b(4);
        }
        final float g = (float)Math.exp(ch.b(5) / 3.5);
        if (this.I == 1) {
            if (ch.b(4) == 15) {
                this.ar = 1;
            }
            else {
                this.ar = 0;
            }
        }
        if (this.I > 1) {
            this.ar = 0;
        }
        for (int n30 = 0; n30 < this.M; ++n30) {
            final int n31 = this.L * n30;
            final int n32 = this.Z + n31;
            final int n33 = this.ab + n31;
            final float n34 = (1.0f + n30) / this.M;
            for (int n35 = 0; n35 < this.O; ++n35) {
                this.ag[n35] = (1.0f - n34) * this.af[n35] + n34 * this.ae[n35];
            }
            bX.a(this.ag, this.O, 0.002f);
            for (int n36 = 0; n36 < this.O; ++n36) {
                this.ag[n36] = (float)Math.cos(this.ag[n36]);
            }
            this.F.a(this.ag, this.ah, this.O);
            if (this.at) {
                final float h2 = this.H[this.I].h;
                final float m = this.H[this.I].i;
                final float n37 = (1.0f - (1.0f - 0.9f * h2) / (1.0f - 0.9f * m)) / 0.9f;
                ba.a(h2, this.ah, this.ak, this.O);
                ba.a(m, this.ah, this.al, this.O);
                ba.a(n37, this.ah, this.am, this.O);
            }
            float n38 = 1.0f;
            this.aj[n30] = 0.0f;
            for (int n39 = 0; n39 <= this.O; ++n39) {
                final float[] aj = this.aj;
                final int n40 = n30;
                aj[n40] += n38 * this.ah[n39];
                n38 = -n38;
            }
            for (int n41 = 0; n41 < this.L; ++n41) {
                this.aa[n33 + n41] = 0.0f;
            }
            int n42;
            if (this.H[this.I].a != -1) {
                final int a;
                if ((a = this.H[this.I].a) != 0) {
                    if ((n42 = n - a + 1) < this.Q) {
                        n42 = this.Q;
                    }
                    if (n + a > this.R) {}
                }
                else {
                    n42 = n;
                }
            }
            else {
                n42 = this.Q;
            }
            final int a2 = this.H[this.I].f.a(this.aa, n33, n42, n2, this.L, array2, ch, this.b, n31, this.d);
            if (this.b != 0 && g < this.g) {
                final float n43 = g / (this.g + 1.0f);
                for (int n44 = 0; n44 < this.L; ++n44) {
                    final float[] aa = this.aa;
                    final int n45 = this.ab + n44;
                    aa[n45] *= n43;
                }
            }
            Math.abs(array2[0] + array2[1] + array2[2]);
            final float abs = Math.abs(array2[1]);
            float n46;
            if (array2[0] > 0.0f) {
                n46 = abs + array2[0];
            }
            else {
                n46 = (float)(abs - 0.5 * array2[0]);
            }
            float n47;
            if (array2[2] > 0.0f) {
                n47 = n46 + array2[2];
            }
            else {
                n47 = (float)(n46 - 0.5 * array2[0]);
            }
            n4 += n47;
            if (n47 > n3) {
                c = a2;
                n3 = n47;
            }
            int n49;
            int n48;
            for (n48 = (n49 = n30 * this.L); n49 < n48 + this.L; ++n49) {
                this.ac[n49] = 0.0f;
            }
            float n50;
            if (this.H[this.I].c == 3) {
                n50 = (float)(g * Math.exp(cT.E[ch.b(3)]));
            }
            else if (this.H[this.I].c == 1) {
                n50 = (float)(g * Math.exp(cT.D[ch.b(1)]));
            }
            else {
                n50 = g;
            }
            if (this.H[this.I].g != null) {
                this.H[this.I].g.a(this.ac, n48, this.L, ch);
            }
            for (int n51 = n48; n51 < n48 + this.L; ++n51) {
                final float[] ac = this.ac;
                final int n52 = n51;
                ac[n52] *= n50;
            }
            if (this.I == 1) {
                final float n53 = n2;
                for (int n54 = 0; n54 < this.L; ++n54) {
                    this.aa[n33 + n54] = 0.0f;
                }
                while (this.aq < this.L) {
                    if (this.aq >= 0) {
                        this.aa[n33 + this.aq] = (float)Math.sqrt(1.0f * n);
                    }
                    this.aq += n;
                }
                this.aq -= this.L;
                float n55;
                if ((n55 = 0.5f + 2.0f * (n53 - 0.6f)) < 0.0f) {
                    n55 = 0.0f;
                }
                if (n55 > 1.0f) {
                    n55 = 1.0f;
                }
                for (int n56 = 0; n56 < this.L; ++n56) {
                    final float an = this.aa[n33 + n56];
                    this.aa[n33 + n56] = 0.8f * n55 * this.aa[n33 + n56] * g + 0.6f * n55 * this.an * g + 0.5f * n55 * this.ac[n48 + n56] - 0.5f * n55 * this.ao + (1.0f - n55) * this.ac[n48 + n56];
                    this.an = an;
                    this.ao = this.ac[n48 + n56];
                    this.ap = 0.95f * this.ap + 0.05f * this.aa[n33 + n56];
                    final float[] aa2 = this.aa;
                    final int n57 = n33 + n56;
                    aa2[n57] -= this.ap;
                }
            }
            else {
                for (int n58 = 0; n58 < this.L; ++n58) {
                    final float[] aa3 = this.aa;
                    final int n59 = n33 + n58;
                    aa3[n59] += this.ac[n48 + n58];
                }
            }
            if (this.H[this.I].d != 0) {
                for (int n60 = 0; n60 < this.L; ++n60) {
                    this.a[n60] = 0.0f;
                }
                this.H[this.I].g.a(this.a, 0, this.L, ch);
                for (int n61 = 0; n61 < this.L; ++n61) {
                    final float[] a3 = this.a;
                    final int n62 = n61;
                    a3[n62] *= (float)(n50 * 0.45454545454545453);
                }
                for (int n63 = 0; n63 < this.L; ++n63) {
                    final float[] aa4 = this.aa;
                    final int n64 = n33 + n63;
                    aa4[n64] += this.a[n63];
                }
            }
            for (int n65 = 0; n65 < this.L; ++n65) {
                this.Y[n32 + n65] = this.aa[n33 + n65];
            }
            if (this.at && this.H[this.I].j > 0.0f) {
                this.G.a(this.aa, n33, this.Y, n32, this.L, a2, array2, this.H[this.I].j);
            }
            if (this.at) {
                ba.a(this.Y, n32, this.al, this.ak, this.L, this.O, this.ai, this.O);
                ba.a(this.Y, n32, this.am, this.ah, this.L, this.O, this.ai, 0);
            }
            else {
                for (int n66 = 0; n66 < this.O; ++n66) {
                    this.ai[this.O + n66] = 0.0f;
                }
                ba.a(this.Y, n32, this.ah, this.Y, n32, this.L, this.O, this.ai);
            }
        }
        array[0] = this.Y[this.Z] + 0.0f * this.X;
        for (int n67 = 1; n67 < this.K; ++n67) {
            array[n67] = this.Y[this.Z + n67] + 0.0f * array[n67 - 1];
        }
        this.X = array[this.K - 1];
        for (int n68 = 0; n68 < this.O; ++n68) {
            this.af[n68] = this.ae[n68];
        }
        this.J = 0;
        this.b = 0;
        this.c = c;
        this.d = 0.25f * n4;
        this.e[this.f++] = this.d;
        if (this.f > 2) {
            this.f = 0;
        }
        this.g = g;
        return 0;
    }
    
    public final void a(final float[] array, final int n) {
        this.i.a(array, n);
    }
    
    public final void a(final boolean at) {
        this.at = at;
    }
}
