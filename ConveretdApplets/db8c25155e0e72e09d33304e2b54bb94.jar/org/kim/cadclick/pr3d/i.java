// 
// Decompiled by Procyon v0.5.30
// 

package org.kim.cadclick.pr3d;

import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Image;
import java.awt.image.MemoryImageSource;

class i
{
    private MemoryImageSource if;
    private Prove3d G;
    private boolean aK;
    protected boolean aI;
    private boolean l;
    private float j;
    private boolean e;
    private float g;
    private float d;
    private float c;
    private float b;
    private float void;
    protected int do;
    protected int m;
    protected int aJ;
    private int[] a;
    private int[] long;
    private int[] goto;
    private int[] else;
    private int[] char;
    private boolean case;
    private boolean byte;
    private boolean try;
    private int new;
    private int int;
    private float for;
    private int ao;
    private int am;
    private int ak;
    private int ai;
    private int ag;
    private int ae;
    private int ad;
    private int ac;
    private int[] ab;
    private int aa;
    private int Z;
    c Y;
    c X;
    c W;
    private float[] V;
    private int[] T;
    private float R;
    private float P;
    private float N;
    private float L;
    private float J;
    private float H;
    private float E;
    private float C;
    private long A;
    private long y;
    private long U;
    private float S;
    private float Q;
    private float O;
    private float M;
    private int K;
    private int I;
    private int F;
    private int D;
    private int B;
    private int z;
    private int x;
    private float w;
    private float v;
    private float u;
    private float t;
    private float s;
    private float r;
    private float q;
    private float p;
    private float o;
    private float n;
    private float k;
    private float i;
    private int h;
    private int f;
    private int aH;
    private int aG;
    private int aF;
    private int aE;
    private float aD;
    private float aC;
    private float aB;
    private float aA;
    private float az;
    private float ay;
    private float ax;
    private float aw;
    private float av;
    private float au;
    private float at;
    private float ar;
    private c[] aq;
    private c[] ap;
    private c[] an;
    private c[] al;
    private c[] aj;
    private c[] ah;
    private int af;
    protected Image as;
    
    public final void a(final c c, final c c2, final c c3) {
        int n = 3;
        this.Y.new = c.new;
        this.Y.for = c.for;
        this.Y.if = c.if;
        this.Y.byte = c.byte;
        this.Y.a = c.a;
        this.Y.char = c.char * this.j;
        this.Y.case = c.case * this.j;
        this.Y.try = c.try * this.j * this.d;
        this.X.new = c2.new;
        this.X.for = c2.for;
        this.X.if = c2.if;
        this.X.byte = c2.byte;
        this.X.a = c2.a;
        this.X.char = c2.char * this.j;
        this.X.case = c2.case * this.j;
        this.X.try = c2.try * this.j * this.d;
        this.W.new = c3.new;
        this.W.for = c3.for;
        this.W.if = c3.if;
        this.W.byte = c3.byte;
        this.W.a = c3.a;
        this.W.char = c3.char * this.j;
        this.W.case = c3.case * this.j;
        this.W.try = c3.try * this.j * this.d;
        if (this.case) {
            this.Y.goto = c.goto;
            this.Y.else = c.else;
            this.X.goto = c2.goto;
            this.X.else = c2.else;
            this.W.goto = c3.goto;
            this.W.else = c3.else;
            this.Y.int = this.Y.goto * this.Y.try * this.for;
            this.Y.do = this.Y.else * this.Y.try * this.for;
            this.X.int = this.X.goto * this.X.try * this.for;
            this.X.do = this.X.else * this.X.try * this.for;
            this.W.int = this.W.goto * this.W.try * this.for;
            this.W.do = this.W.else * this.W.try * this.for;
        }
        int n2 = this.Y.a | this.X.a | this.W.a;
        if (n2 != 0) {
            this.ap = this.al;
            this.an = this.aj;
            this.af = 0;
            this.an[0] = this.Y;
            this.an[1] = this.X;
            this.an[2] = this.W;
            if ((n2 & 0x10) != 0x0) {
                final c[] an = this.an;
                this.an = this.ap;
                this.ap = an;
                if ((n = this.do(n)) < 3) {
                    return;
                }
                n2 = 63;
            }
            if ((n2 & 0x1) != 0x0) {
                final c[] an2 = this.an;
                this.an = this.ap;
                this.ap = an2;
                if ((n = this.for(n)) < 3) {
                    return;
                }
            }
            if ((n2 & 0x2) != 0x0) {
                final c[] an3 = this.an;
                this.an = this.ap;
                this.ap = an3;
                if ((n = this.if(n)) < 3) {
                    return;
                }
            }
            if ((n2 & 0x4) != 0x0) {
                final c[] an4 = this.an;
                this.an = this.ap;
                this.ap = an4;
                if ((n = this.int(n)) < 3) {
                    return;
                }
            }
            if ((n2 & 0x8) != 0x0) {
                final c[] an5 = this.an;
                this.an = this.ap;
                this.ap = an5;
                if ((n = this.a(n)) < 3) {
                    return;
                }
            }
        }
        else {
            (this.an = this.al)[0] = this.Y;
            this.an[1] = this.X;
            this.an[2] = this.W;
        }
        this.aq[0] = this.an[0];
        for (int i = 1; i < n - 1; ++i) {
            this.aq[1] = this.an[i];
            this.aq[2] = this.an[i + 1];
            this.if(this.aq[0], this.aq[1], this.aq[2]);
            this.a(this.aq, 3);
        }
    }
    
    private int for(final int n) {
        c c = this.ap[n - 1];
        float char1 = c.char;
        boolean b = char1 >= 0.0f;
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            final c c2 = this.ap[i];
            final float char2 = c2.char;
            final boolean b2 = char2 >= 0.0f;
            if (b ^ b2) {
                final float n3 = char1 / (char1 - char2);
                final c c3 = this.ah[this.af++];
                c3.char = 0.0f;
                c3.case = c.case + (c2.case - c.case) * n3;
                c3.try = c.try + (c2.try - c.try) * n3;
                if (this.case) {
                    c3.int = c.int + (c2.int - c.int) * n3;
                    c3.do = c.do + (c2.do - c.do) * n3;
                }
                if (this.byte) {
                    c3.byte = c.byte + (c2.byte - c.byte) * n3;
                }
                this.an[n2++] = c3;
            }
            if (b2) {
                this.an[n2++] = c2;
            }
            b = b2;
            c = c2;
            char1 = char2;
        }
        return n2;
    }
    
    private void try() {
        final int n = this.h - this.K;
        if (n <= 0) {
            return;
        }
        int i = this.z + this.K;
        float n2 = this.w + this.S;
        float n3 = this.t + this.Q;
        float n4 = this.q + this.O;
        float n5 = 65536.0f / this.w;
        float n6 = this.t * n5;
        float n7 = this.q * n5;
        int n8 = n >> 4;
        int n9 = n & 0xF;
        final int n10 = this.Z << 16 & 0xFF000000;
        int n11 = 0;
        int n12 = 0;
        if (this.byte) {
            n11 = (int)(this.n * 65536.0f);
            n12 = (int)(this.E * 65536.0f);
        }
        if (n9 == 0) {
            --n8;
            n9 = 16;
        }
        final int new1 = this.new;
        final int int1 = this.int;
        final int n13 = (new1 >> 16) + 1;
        final int n14 = n13 + 1;
        final int n15 = (1 << (16 - int1 << 1)) - 1;
        boolean b = true;
        do {
            int n19;
            int n20;
            int n21;
            int n22;
            int n23;
            int n24;
            int n25;
            if (n8-- > 0) {
                final float n16 = 65536.0f / n2;
                final float n17 = n3 * n16;
                final float n18 = n4 * n16;
                n19 = (int)n5;
                n20 = (int)((long)(n6 + this.A) & 0x1FFFFFFL);
                n21 = (int)((long)(n7 + this.y) & 0x1FFFFFFL);
                n22 = (int)(n16 - n5) >> 4;
                n23 = (int)(n17 - n6) >> 4;
                n24 = (int)(n18 - n7) >> 4;
                n5 = n16;
                n6 = n17;
                n7 = n18;
                n2 += this.S;
                n3 += this.Q;
                n4 += this.O;
                n25 = i + 16;
            }
            else {
                if (n9 == 0) {
                    return;
                }
                n2 = this.aD - this.R;
                final float n26 = 65536.0f / n2;
                final float n27 = n26 * (this.aA - this.N);
                final float n28 = n26 * (this.ax - this.J);
                n19 = (int)n5;
                n20 = (int)((long)(n6 + this.A) & 0x1FFFFFFL);
                n21 = (int)((long)(n7 + this.y) & 0x1FFFFFFL);
                final float n29 = this.V[n9];
                n22 = (int)((n26 - n5) * n29);
                n23 = (int)((n27 - n6) * n29);
                n24 = (int)((n28 - n7) * n29);
                n25 = i + n9;
                b = false;
            }
            if (this.byte) {
                if (this.try) {
                    while (i < n25) {
                        if (n19 < this.goto[i]) {
                            final int n30 = (n21 & new1) >> int1 | (n20 & new1) >> 16;
                            final int n31 = n20 >> 8 & 0xFF;
                            final int n32 = n21 >> 8 & 0xFF;
                            final int n33 = this.ab[n30];
                            final int n34 = n33 & 0xFF00;
                            final int n35 = n33 & 0xFF00FF;
                            final int n36 = this.ab[n30 + n13 & n15];
                            final int n37 = n35 + (n32 * ((n36 & 0xFF00FF) - n35) >>> 8) & 0xFF00FF;
                            final int n38 = n34 + (n32 * ((n36 & 0xFF00) - n34) >>> 8) & 0xFF00;
                            final int n39 = this.ab[n30 + 1 & n15];
                            final int n40 = n39 & 0xFF00;
                            final int n41 = n39 & 0xFF00FF;
                            final int n42 = this.ab[n30 + n14 & n15];
                            final int n43 = (n37 + (n31 * ((n41 + (n32 * ((n42 & 0xFF00FF) - n41) >> 8) & 0xFF00FF) - n37) >> 8) & 0xFF00FF) | (n38 + (n31 * ((n40 + (n32 * ((n42 & 0xFF00) - n40) >> 8) & 0xFF00) - n38) >> 8) & 0xFF00);
                            final int n44 = (n43 >> 16 & 0xFF) * n11 >>> 16;
                            final int n45 = (n43 >> 8 & 0xFF) * n11 >>> 16;
                            final int n46 = (n43 & 0xFF) * n11 >>> 16;
                            int n47 = n44 + this.ae;
                            int n48 = n45 + this.ad;
                            int n49 = n46 + this.ac;
                            if (n47 > 255) {
                                n47 = 255;
                            }
                            if (n48 > 255) {
                                n48 = 255;
                            }
                            if (n49 > 255) {
                                n49 = 255;
                            }
                            this.a[i] = (n10 | n47 << 16 | n48 << 8 | n49);
                            this.goto[i] = n19;
                        }
                        ++i;
                        n19 += n22;
                        n20 += n23;
                        n21 += n24;
                        n11 += n12;
                    }
                }
                else {
                    if (n10 == 0) {
                        continue;
                    }
                    while (i < n25) {
                        if (n19 < this.goto[i]) {
                            final int n50 = this.a[i];
                            final int n51 = n50 >> 16 & 0xFF;
                            final int n52 = n50 >> 8 & 0xFF;
                            final int n53 = n50 & 0xFF;
                            final int n54 = (n21 & new1) >> int1 | (n20 & new1) >> 16;
                            final int n55 = n20 >> 8 & 0xFF;
                            final int n56 = n21 >> 8 & 0xFF;
                            final int n57 = this.ab[n54];
                            final int n58 = n57 & 0xFF00;
                            final int n59 = n57 & 0xFF00FF;
                            final int n60 = this.ab[n54 + n13 & n15];
                            final int n61 = n59 + (n56 * ((n60 & 0xFF00FF) - n59) >>> 8) & 0xFF00FF;
                            final int n62 = n58 + (n56 * ((n60 & 0xFF00) - n58) >>> 8) & 0xFF00;
                            final int n63 = this.ab[n54 + 1 & n15];
                            final int n64 = n63 & 0xFF00;
                            final int n65 = n63 & 0xFF00FF;
                            final int n66 = this.ab[n54 + n14 & n15];
                            final int n67 = (n61 + (n55 * ((n65 + (n56 * ((n66 & 0xFF00FF) - n65) >> 8) & 0xFF00FF) - n61) >> 8) & 0xFF00FF) | (n62 + (n55 * ((n64 + (n56 * ((n66 & 0xFF00) - n64) >> 8) & 0xFF00) - n62) >> 8) & 0xFF00);
                            final int n68 = (n67 >> 16 & 0xFF) * n11 >>> 16;
                            final int n69 = (n67 >> 8 & 0xFF) * n11 >>> 16;
                            final int n70 = (n67 & 0xFF) * n11 >>> 16;
                            int n71 = n68 + this.ae;
                            int n72 = n69 + this.ad;
                            int n73 = n70 + this.ac;
                            if (n71 > 255) {
                                n71 = 255;
                            }
                            if (n72 > 255) {
                                n72 = 255;
                            }
                            if (n73 > 255) {
                                n73 = 255;
                            }
                            int n74 = this.T[n51 + this.aa] + this.T[n71 + this.Z];
                            int n75 = this.T[n52 + this.aa] + this.T[n72 + this.Z];
                            int n76 = this.T[n53 + this.aa] + this.T[n73 + this.Z];
                            if (n74 > 255) {
                                n74 = 255;
                            }
                            if (n75 > 255) {
                                n75 = 255;
                            }
                            if (n76 > 255) {
                                n76 = 255;
                            }
                            this.a[i] = (n10 | n74 << 16 | n75 << 8 | n76);
                        }
                        ++i;
                        n19 += n22;
                        n20 += n23;
                        n21 += n24;
                        n11 += n12;
                    }
                }
            }
            else if (this.try) {
                while (i < n25) {
                    if (n19 < this.goto[i]) {
                        final int n77 = (n21 & new1) >> int1 | (n20 & new1) >> 16;
                        final int n78 = this.ab[n77];
                        if ((n78 & 0xFF000000) != 0x0) {
                            final int n79 = n20 >> 8 & 0xFF;
                            final int n80 = n21 >> 8 & 0xFF;
                            final int n81 = n78 & 0xFF00;
                            final int n82 = n78 & 0xFF00FF;
                            final int n83 = this.ab[n77 + n13 & n15];
                            final int n84 = n82 + (n80 * ((n83 & 0xFF00FF) - n82) >>> 8) & 0xFF00FF;
                            final int n85 = n81 + (n80 * ((n83 & 0xFF00) - n81) >>> 8) & 0xFF00;
                            final int n86 = this.ab[n77 + 1 & n15];
                            final int n87 = n86 & 0xFF00;
                            final int n88 = n86 & 0xFF00FF;
                            final int n89 = this.ab[n77 + n14 & n15];
                            this.a[i] = (n10 | (n84 + (n79 * ((n88 + (n80 * ((n89 & 0xFF00FF) - n88) >> 8) & 0xFF00FF) - n84) >> 8) & 0xFF00FF) | (n85 + (n79 * ((n87 + (n80 * ((n89 & 0xFF00) - n87) >> 8) & 0xFF00) - n85) >> 8) & 0xFF00));
                            this.goto[i] = n19;
                        }
                    }
                    ++i;
                    n19 += n22;
                    n20 += n23;
                    n21 += n24;
                }
            }
            else {
                while (i < n25) {
                    if (n19 < this.goto[i]) {
                        final int n90 = this.a[i];
                        final int n91 = n90 >> 16 & 0xFF;
                        final int n92 = n90 >> 8 & 0xFF;
                        final int n93 = n90 & 0xFF;
                        final int n94 = (n21 & new1) >> int1 | (n20 & new1) >> 16;
                        final int n95 = n20 >> 8 & 0xFF;
                        final int n96 = n21 >> 8 & 0xFF;
                        final int n97 = this.ab[n94];
                        final int n98 = n97 & 0xFF00;
                        final int n99 = n97 & 0xFF00FF;
                        final int n100 = this.ab[n94 + n13 & n15];
                        final int n101 = n99 + (n96 * ((n100 & 0xFF00FF) - n99) >>> 8) & 0xFF00FF;
                        final int n102 = n98 + (n96 * ((n100 & 0xFF00) - n98) >>> 8) & 0xFF00;
                        final int n103 = this.ab[n94 + 1 & n15];
                        final int n104 = n103 & 0xFF00;
                        final int n105 = n103 & 0xFF00FF;
                        final int n106 = this.ab[n94 + n14 & n15];
                        final int n107 = (n101 + (n95 * ((n105 + (n96 * ((n106 & 0xFF00FF) - n105) >> 8) & 0xFF00FF) - n101) >> 8) & 0xFF00FF) | (n102 + (n95 * ((n104 + (n96 * ((n106 & 0xFF00) - n104) >> 8) & 0xFF00) - n102) >> 8) & 0xFF00);
                        final int n108 = n107 >> 16 & 0xFF;
                        final int n109 = n107 >> 8 & 0xFF;
                        final int n110 = n107 & 0xFF;
                        int n111 = this.T[n91 + this.aa] + this.T[n108 + this.Z];
                        int n112 = this.T[n92 + this.aa] + this.T[n109 + this.Z];
                        int n113 = this.T[n93 + this.aa] + this.T[n110 + this.Z];
                        if (n111 > 255) {
                            n111 = 255;
                        }
                        if (n112 > 255) {
                            n112 = 255;
                        }
                        if (n113 > 255) {
                            n113 = 255;
                        }
                        this.a[i] = (n10 | n111 << 16 | n112 << 8 | n113);
                    }
                    ++i;
                    n19 += n22;
                    n20 += n23;
                    n21 += n24;
                }
            }
        } while (b);
    }
    
    public void a(final Prove3d g, final int do1, final int m) {
        this.G = g;
        this.V = new float[17];
        int n = 0;
        do {
            this.V[n] = 1.0f / n;
        } while (++n < 17);
        this.T = new int[65536];
        int n2 = 0;
        do {
            int n3 = 0;
            do {
                this.T[n2 + (n3 << 8)] = n2 * n3 / 255;
            } while (++n3 < 256);
        } while (++n2 < 256);
        this.al = new c[20];
        this.aj = new c[20];
        this.ah = new c[20];
        int n4 = 0;
        do {
            this.ah[n4] = new c();
        } while (++n4 < 20);
        this.do = do1;
        this.m = m;
        this.aJ = this.do * this.m;
        this.a = new int[this.aJ];
        this.long = new int[this.G.appletWidth * this.G.appletHeight];
        this.goto = new int[this.aJ];
        (this.if = new MemoryImageSource(this.do, this.m, new DirectColorModel(24, 16711680, 65280, 255), this.a, 0, this.do)).setAnimated(true);
        this.if.setFullBufferUpdates(true);
        this.as = this.G.getToolkit().createImage(this.if);
        this.if.newPixels();
    }
    
    private int if(final int n) {
        c c = this.ap[n - 1];
        float n2 = this.do - c.char;
        boolean b = n2 >= 0.0f;
        int n3 = 0;
        for (int i = 0; i < n; ++i) {
            final c c2 = this.ap[i];
            final float n4 = this.do - c2.char;
            final boolean b2 = n4 >= 0.0f;
            if (b ^ b2) {
                final float n5 = n2 / (n2 - n4);
                final c c3 = this.ah[this.af++];
                c3.char = this.do;
                c3.case = c.case + (c2.case - c.case) * n5;
                c3.try = c.try + (c2.try - c.try) * n5;
                if (this.case) {
                    c3.int = c.int + (c2.int - c.int) * n5;
                    c3.do = c.do + (c2.do - c.do) * n5;
                }
                if (this.byte) {
                    c3.byte = c.byte + (c2.byte - c.byte) * n5;
                }
                this.an[n3++] = c3;
            }
            if (b2) {
                this.an[n3++] = c2;
            }
            b = b2;
            c = c2;
            n2 = n4;
        }
        return n3;
    }
    
    private void case() {
        if (this.aI && this.l) {
            this.do = this.G.appletWidth << 1;
            this.m = this.G.appletHeight << 1;
            this.aJ = this.do * this.m;
            this.j = 2.0f;
            if (this.else == null) {
                try {
                    this.else = new int[this.aJ];
                    this.char = new int[this.aJ];
                }
                catch (Throwable t) {
                    this.l = false;
                    this.do = this.G.appletWidth;
                    this.m = this.G.appletHeight;
                    this.aJ = this.do * this.m;
                    this.j = 1.0f;
                    this.aI = false;
                }
            }
            if (this.l) {
                final int[] else1 = this.else;
                this.else = this.a;
                this.a = else1;
                final int[] char1 = this.char;
                this.char = this.goto;
                this.goto = char1;
            }
        }
        else {
            this.do = this.G.appletWidth;
            this.m = this.G.appletHeight;
            this.aJ = this.do * this.m;
            this.j = 1.0f;
            this.aI = false;
        }
        this.b = this.do >> 1;
        this.void = this.m >> 1;
        this.g = this.G.avatarHeadRadius;
        this.d = 0.01f;
        this.c = this.G.avatarProjectionDistance * this.j;
    }
    
    public void if(final c c, final c c2) {
        c.try *= this.d;
        c2.try *= this.d;
        final c w = this.W;
        w.try *= this.d;
        if ((c.a | c2.a) != 0x0) {
            final int n = 3;
            this.ap = this.al;
            this.an = this.aj;
            this.af = 0;
            this.ap[0] = c;
            this.ap[1] = c2;
            this.ap[2] = c;
            final int do1;
            if ((do1 = this.do(n)) < 3) {
                return;
            }
            this.ap = this.aj;
            this.an = this.al;
            final int for1;
            if ((for1 = this.for(do1)) < 3) {
                return;
            }
            this.ap = this.al;
            this.an = this.aj;
            final int int1;
            if ((int1 = this.int(for1)) < 3) {
                return;
            }
            this.ap = this.aj;
            this.an = this.al;
            final int if1;
            if ((if1 = this.if(int1)) < 3) {
                return;
            }
            this.ap = this.al;
            this.an = this.aj;
            if (this.a(if1) < 3) {
                return;
            }
        }
        else {
            (this.an = this.al)[0] = c;
            this.an[1] = c2;
            this.an[2] = c;
        }
        this.a(this.an[0], this.an[1]);
    }
    
    public final void a(final int[] array) {
        System.arraycopy(array, 0, this.long, 0, this.long.length);
    }
    
    private void do(final c c, final c c2) {
        final int n = (int)(c.char * 16.0f);
        final int n2 = (int)(c.case * 16.0f);
        final int n3 = (int)(c2.char * 16.0f);
        final int n4 = (int)(c2.case * 16.0f);
        final int n5 = n2 + 15 >> 4;
        this.z = n5 * this.do;
        this.x = (n4 + 15 >> 4) - n5;
        if (this.x != 0) {
            final int n6 = n4 - n2;
            final int d = n6 << 4;
            final int n7 = n3 - n;
            final int n8 = n7 << 4;
            final int n9 = n8 * n5 - n7 * n2 + n6 * n - 1 + d;
            this.K = n9 / d;
            this.B = n9 % d;
            if (n9 < 0) {
                this.B = -this.B;
                if (this.B != 0) {
                    --this.K;
                    this.B = d - this.B;
                }
            }
            this.I = n8 / d;
            this.F = n8 % d;
            if (n8 < 0) {
                this.F = -this.F;
                if (this.F != 0) {
                    --this.I;
                    this.F = d - this.F;
                }
            }
            this.D = d;
            final float n10 = ((n5 << 4) - n2) * 0.0625f;
            final float n11 = ((this.K << 4) - n) * 0.0625f;
            this.w = c.try + n10 * this.P + n11 * this.R;
            this.v = this.I * this.R + this.P;
            this.u = this.R;
            if (this.case) {
                this.t = c.int + n10 * this.L + n11 * this.N;
                this.s = this.I * this.N + this.L;
                this.r = this.N;
                this.q = c.do + n10 * this.H + n11 * this.J;
                this.p = this.I * this.J + this.H;
                this.o = this.J;
            }
            if (this.byte) {
                this.n = c.byte + n10 * this.C + n11 * this.E;
                this.k = this.I * this.E + this.C;
                this.i = this.E;
            }
        }
    }
    
    private void char() {
        final int n = this.h - this.K;
        if (n <= 0) {
            return;
        }
        int i = this.z + this.K;
        float n2 = this.w + this.S;
        float n3 = 1.0f / this.w;
        int n4 = n >> 4;
        int n5 = n & 0xF;
        final int n6 = this.Z << 16 & 0xFF000000;
        int am = 0;
        int ak = 0;
        int ai = 0;
        if (!this.try) {
            am = this.T[this.ae + this.Z];
            ak = this.T[this.ad + this.Z];
            ai = this.T[this.ac + this.Z];
        }
        int n7 = 0;
        int n8 = 0;
        if (this.byte) {
            n7 = (int)(this.n * 65536.0f);
            n8 = (int)(this.E * 65536.0f);
            am = this.am;
            ak = this.ak;
            ai = this.ai;
        }
        if (n5 == 0) {
            --n4;
            n5 = 16;
        }
        boolean b = true;
        do {
            int n10;
            int n11;
            int n12;
            if (n4-- > 0) {
                final float n9 = 1.0f / n2;
                n10 = (int)(n3 * 65536.0f);
                n11 = (int)((n9 - n3) * 4096.0f);
                n3 = n9;
                n2 += this.S;
                n12 = i + 16;
            }
            else {
                if (n5 == 0) {
                    return;
                }
                n2 = this.aD - this.R;
                final float n13 = 1.0f / n2;
                n10 = (int)(n3 * 65536.0f);
                n11 = (int)((n13 - n3) * 65536.0f / n5);
                n12 = i + n5;
                b = false;
            }
            if (this.byte) {
                if (this.try) {
                    while (i < n12) {
                        if (n10 < this.goto[i]) {
                            final int n14 = am * n7 >>> 16;
                            final int n15 = ak * n7 >>> 16;
                            final int n16 = ai * n7 >>> 16;
                            int n17 = n14 + this.ae;
                            int n18 = n15 + this.ad;
                            int n19 = n16 + this.ac;
                            if (n17 > 255) {
                                n17 = 255;
                            }
                            if (n18 > 255) {
                                n18 = 255;
                            }
                            if (n19 > 255) {
                                n19 = 255;
                            }
                            this.a[i] = (n6 | n17 << 16 | n18 << 8 | n19);
                            this.goto[i] = n10;
                        }
                        ++i;
                        n10 += n11;
                        n7 += n8;
                    }
                }
                else {
                    if (n6 == 0) {
                        continue;
                    }
                    while (i < n12) {
                        if (n10 < this.goto[i]) {
                            final int n20 = am * n7 >>> 16;
                            final int n21 = ak * n7 >>> 16;
                            final int n22 = ai * n7 >>> 16;
                            int n23 = n20 + this.ae;
                            int n24 = n21 + this.ad;
                            int n25 = n22 + this.ac;
                            if (n23 > 255) {
                                n23 = 255;
                            }
                            if (n24 > 255) {
                                n24 = 255;
                            }
                            if (n25 > 255) {
                                n25 = 255;
                            }
                            final int n26 = this.a[i];
                            final int n27 = n26 >> 16 & 0xFF;
                            final int n28 = n26 >> 8 & 0xFF;
                            final int n29 = n26 & 0xFF;
                            int n30 = this.T[n27 + this.aa] + this.T[n23 + this.Z];
                            int n31 = this.T[n28 + this.aa] + this.T[n24 + this.Z];
                            int n32 = this.T[n29 + this.aa] + this.T[n25 + this.Z];
                            if (n30 > 255) {
                                n30 = 255;
                            }
                            if (n31 > 255) {
                                n31 = 255;
                            }
                            if (n32 > 255) {
                                n32 = 255;
                            }
                            this.a[i] = (n6 | n30 << 16 | n31 << 8 | n32);
                        }
                        ++i;
                        n10 += n11;
                    }
                }
            }
            else if (this.try) {
                while (i < n12) {
                    if (n10 < this.goto[i]) {
                        this.a[i] = this.ag;
                        this.goto[i] = n10;
                    }
                    ++i;
                    n10 += n11;
                }
            }
            else {
                while (i < n12) {
                    if (n10 < this.goto[i]) {
                        final int n33 = this.a[i];
                        final int n34 = n33 >> 16 & 0xFF;
                        final int n35 = n33 >> 8 & 0xFF;
                        final int n36 = n33 & 0xFF;
                        int n37 = this.T[n34 + this.aa] + am;
                        int n38 = this.T[n35 + this.aa] + ak;
                        int n39 = this.T[n36 + this.aa] + ai;
                        if (n37 > 255) {
                            n37 = 255;
                        }
                        if (n38 > 255) {
                            n38 = 255;
                        }
                        if (n39 > 255) {
                            n39 = 255;
                        }
                        this.a[i] = (n6 | n37 << 16 | n38 << 8 | n39);
                    }
                    ++i;
                    n10 += n11;
                }
            }
        } while (b);
    }
    
    i() {
        this.aI = false;
        this.l = true;
        this.j = 1.0f;
        this.try = true;
        this.Y = new c();
        this.X = new c();
        this.W = new c();
        this.A = 0L;
        this.y = 0L;
        this.U = 0L;
        this.aq = new c[10];
    }
    
    public void byte() {
        this.case = this.G.currShapeHasTexture;
        this.byte = this.G.currShapeIsEmissive;
        this.try = this.G.currShapeIsNotTransparent;
        this.aa = this.G.currShapeTransparency;
        this.Z = this.G.currShapeAlfa;
        this.ao = this.G.currShapeRGBAlfa;
        this.am = this.G.currShapeREDComp;
        this.ak = this.G.currShapeGREENComp;
        this.ai = this.G.currShapeBLUEComp;
        this.ag = this.G.currShapeEmissAlfaRGB;
        this.ae = this.G.currShapeEmissRED;
        this.ad = this.G.currShapeEmissGREEN;
        this.ac = this.G.currShapeEmissBLUE;
        if (!this.case) {
            return;
        }
        this.ab = this.G.currShapeTexture;
        switch (this.ab.length) {
            case 262144: {
                this.for = 511.0f;
                this.new = 33488896;
                this.int = 7;
            }
            case 65536: {
                this.for = 255.0f;
                this.new = 16711680;
                this.int = 8;
            }
            case 16384: {
                this.for = 127.0f;
                this.new = 8323072;
                this.int = 9;
            }
            case 4096: {
                this.for = 63.0f;
                this.new = 4128768;
                this.int = 10;
            }
            case 1024: {
                this.for = 31.0f;
                this.new = 2031616;
                this.int = 11;
            }
            default: {
                this.case = false;
            }
        }
    }
    
    public void new() {
        this.K += this.I;
        this.z += this.do;
        --this.x;
        this.w += this.v;
        this.h += this.f;
        --this.aE;
        this.aD += this.aC;
        if (this.case) {
            this.t += this.s;
            this.q += this.p;
            this.aA += this.az;
            this.ax += this.aw;
        }
        if (this.byte) {
            this.n += this.k;
            this.au += this.at;
        }
        this.B += this.F;
        if (this.B >= this.D) {
            ++this.K;
            this.B -= this.D;
            this.w += this.u;
            if (this.case) {
                this.t += this.r;
                this.q += this.o;
            }
            if (this.byte) {
                this.n += this.i;
            }
        }
        this.aF += this.aH;
        if (this.aF >= this.aG) {
            ++this.h;
            this.aF -= this.aG;
            this.aD += this.aB;
            if (this.case) {
                this.aA += this.ay;
                this.ax += this.av;
            }
            if (this.byte) {
                this.au += this.ar;
            }
        }
    }
    
    public void a(final float n, final float n2, final float n3) {
        final int n4 = (int)(n * this.j);
        final int n5 = (int)(n2 * this.j);
        final int n6 = (int)(1.0f / (n3 * this.j * this.d / 65535.0f));
        final int n7 = n5 * this.do + n4;
        if (n4 > 0 && n4 < this.do && n5 > 0 && n5 < this.m && n6 < this.goto[n7]) {
            this.a[n7] = this.ag;
        }
    }
    
    private final void if() {
        if (this.aI) {
            int n = 0;
            int n2 = 0;
            int n3 = this.m >> 1;
            while (--n3 >= 0) {
                int n4 = this.do >> 1;
                while (--n4 >= 0) {
                    final int[] a = this.a;
                    final int n5 = n;
                    final int[] a2 = this.a;
                    final int n6 = n + 1;
                    final int[] a3 = this.a;
                    final int n7 = n + this.do;
                    final int[] a4 = this.a;
                    final int n8 = n + this.do + 1;
                    final int n9 = this.long[n2++];
                    a3[n7] = (a4[n8] = n9);
                    a[n5] = (a2[n6] = n9);
                    n += 2;
                }
                n += this.do;
            }
        }
        else {
            System.arraycopy(this.long, 0, this.a, 0, this.aJ);
        }
        int aj = this.aJ;
        while (--aj >= 0) {
            this.goto[aj] = Integer.MAX_VALUE;
        }
    }
    
    private void a(final c c, final c c2) {
        int n = (int)(c.char * 256.0f * this.j);
        int n2 = (int)(c.case * 256.0f * this.j);
        final int n3 = (int)(c2.char * 256.0f * this.j);
        final int n4 = (int)(c2.case * 256.0f * this.j);
        float n5 = c.try * this.j / 65536.0f;
        final float n6 = c2.try * this.j / 65536.0f;
        int n7 = n3 - n;
        int n8 = n4 - n2;
        final float n9 = n6 - n5;
        final int n10 = (n7 >= 0) ? n7 : (-n7);
        final int n11 = (n8 >= 0) ? n8 : (-n8);
        if (n10 >> 8 == 0 && n11 >> 8 == 0) {
            return;
        }
        int n12 = 256;
        int n13 = 256;
        int n14;
        if (n10 > n11) {
            if (n7 < 0) {
                n7 = -n7;
                n12 = -256;
            }
            n14 = n7 >> 8;
            n13 = n8 / n14;
        }
        else {
            if (n8 < 0) {
                n8 = -n8;
                n13 = -256;
            }
            n14 = n8 >> 8;
            n12 = n7 / n14;
        }
        final float n15 = n9 / n14;
        final int n16 = this.do << 8;
        final int n17 = this.m << 8;
        for (int i = 0; i < n14; ++i) {
            if (n >= 0 && n < n16 && n2 >= 0 && n2 < n17) {
                final int n18 = this.do * (n2 >> 8) + (n >> 8);
                final int n19 = (int)(1.0f / n5);
                if (n19 <= this.goto[n18]) {
                    this.a[n18] = this.ag;
                    this.goto[n18] = n19;
                }
            }
            n += n12;
            n2 += n13;
            n5 += n15;
        }
    }
    
    private void for() {
        final int n = this.h - this.K;
        if (n <= 0) {
            return;
        }
        int i = this.z + this.K;
        float n2 = this.w + this.S;
        float n3 = this.t + this.Q;
        float n4 = this.q + this.O;
        float n5 = 65536.0f / this.w;
        float n6 = this.t * n5;
        float n7 = this.q * n5;
        int n8 = n >> 4;
        int n9 = n & 0xF;
        final int n10 = this.Z << 16 & 0xFF000000;
        int n11 = 0;
        int n12 = 0;
        if (this.byte) {
            n11 = (int)(this.n * 65536.0f);
            n12 = (int)(this.E * 65536.0f);
        }
        if (n9 == 0) {
            --n8;
            n9 = 16;
        }
        final int new1 = this.new;
        final int int1 = this.int;
        boolean b = true;
        do {
            int n16;
            int n17;
            int n18;
            int n19;
            int n20;
            int n21;
            int n22;
            if (n8-- > 0) {
                final float n13 = 65536.0f / n2;
                final float n14 = n3 * n13;
                final float n15 = n4 * n13;
                n16 = (int)n5;
                n17 = (int)((long)(n6 + this.A) & 0x1FFFFFFL);
                n18 = (int)((long)(n7 + this.y) & 0x1FFFFFFL);
                n19 = (int)(n13 - n5) >> 4;
                n20 = (int)(n14 - n6) >> 4;
                n21 = (int)(n15 - n7) >> 4;
                n5 = n13;
                n6 = n14;
                n7 = n15;
                n2 += this.S;
                n3 += this.Q;
                n4 += this.O;
                n22 = i + 16;
            }
            else {
                if (n9 == 0) {
                    return;
                }
                n2 = this.aD - this.R;
                final float n23 = 65536.0f / n2;
                final float n24 = n23 * (this.aA - this.N);
                final float n25 = n23 * (this.ax - this.J);
                n16 = (int)n5;
                n17 = (int)((long)(n6 + this.A) & 0x1FFFFFFL);
                n18 = (int)((long)(n7 + this.y) & 0x1FFFFFFL);
                final float n26 = this.V[n9];
                n19 = (int)((n23 - n5) * n26);
                n20 = (int)((n24 - n6) * n26);
                n21 = (int)((n25 - n7) * n26);
                n22 = i + n9;
                b = false;
            }
            if (this.byte) {
                if (this.try) {
                    while (i < n22) {
                        if (n16 < this.goto[i]) {
                            final int n27 = this.ab[(n18 & new1) >> int1 | (n17 & new1) >> 16];
                            final int n28 = (n27 >> 16 & 0xFF) * n11 >>> 16;
                            final int n29 = (n27 >> 8 & 0xFF) * n11 >>> 16;
                            final int n30 = (n27 & 0xFF) * n11 >>> 16;
                            int n31 = n28 + this.ae;
                            int n32 = n29 + this.ad;
                            int n33 = n30 + this.ac;
                            if (n31 > 255) {
                                n31 = 255;
                            }
                            if (n32 > 255) {
                                n32 = 255;
                            }
                            if (n33 > 255) {
                                n33 = 255;
                            }
                            this.a[i] = (n10 | n31 << 16 | n32 << 8 | n33);
                            this.goto[i] = n16;
                        }
                        ++i;
                        n16 += n19;
                        n17 += n20;
                        n18 += n21;
                        n11 += n12;
                    }
                }
                else {
                    if (n10 == 0) {
                        continue;
                    }
                    while (i < n22) {
                        if (n16 < this.goto[i]) {
                            final int n34 = this.a[i];
                            final int n35 = n34 >> 16 & 0xFF;
                            final int n36 = n34 >> 8 & 0xFF;
                            final int n37 = n34 & 0xFF;
                            final int n38 = this.ab[(n18 & new1) >> int1 | (n17 & new1) >> 16];
                            final int n39 = (n38 >> 16 & 0xFF) * n11 >>> 16;
                            final int n40 = (n38 >> 8 & 0xFF) * n11 >>> 16;
                            final int n41 = (n38 & 0xFF) * n11 >>> 16;
                            int n42 = n39 + this.ae;
                            int n43 = n40 + this.ad;
                            int n44 = n41 + this.ac;
                            if (n42 > 255) {
                                n42 = 255;
                            }
                            if (n43 > 255) {
                                n43 = 255;
                            }
                            if (n44 > 255) {
                                n44 = 255;
                            }
                            int n45 = this.T[n35 + this.aa] + this.T[n42 + this.Z];
                            int n46 = this.T[n36 + this.aa] + this.T[n43 + this.Z];
                            int n47 = this.T[n37 + this.aa] + this.T[n44 + this.Z];
                            if (n45 > 255) {
                                n45 = 255;
                            }
                            if (n46 > 255) {
                                n46 = 255;
                            }
                            if (n47 > 255) {
                                n47 = 255;
                            }
                            this.a[i] = (n10 | n45 << 16 | n46 << 8 | n47);
                        }
                        ++i;
                        n16 += n19;
                        n17 += n20;
                        n18 += n21;
                        n11 += n12;
                    }
                }
            }
            else if (this.try) {
                while (i < n22) {
                    if (n16 < this.goto[i]) {
                        final int n48 = this.ab[(n18 & new1) >> int1 | (n17 & new1) >> 16];
                        if ((n48 & 0xFF000000) != 0x0) {
                            this.a[i] = (n10 | n48);
                            this.goto[i] = n16;
                        }
                    }
                    ++i;
                    n16 += n19;
                    n17 += n20;
                    n18 += n21;
                }
            }
            else {
                while (i < n22) {
                    if (n16 < this.goto[i]) {
                        final int n49 = this.a[i];
                        final int n50 = n49 >> 16 & 0xFF;
                        final int n51 = n49 >> 8 & 0xFF;
                        final int n52 = n49 & 0xFF;
                        final int n53 = this.ab[(n18 & new1) >> int1 | (n17 & new1) >> 16];
                        final int n54 = n53 >> 16 & 0xFF;
                        final int n55 = n53 >> 8 & 0xFF;
                        final int n56 = n53 & 0xFF;
                        int n57 = this.T[n50 + this.aa] + this.T[n54 + this.Z];
                        int n58 = this.T[n51 + this.aa] + this.T[n55 + this.Z];
                        int n59 = this.T[n52 + this.aa] + this.T[n56 + this.Z];
                        if (n57 > 255) {
                            n57 = 255;
                        }
                        if (n58 > 255) {
                            n58 = 255;
                        }
                        if (n59 > 255) {
                            n59 = 255;
                        }
                        this.a[i] = (n10 | n57 << 16 | n58 << 8 | n59);
                    }
                    ++i;
                    n16 += n19;
                    n17 += n20;
                    n18 += n21;
                }
            }
        } while (b);
    }
    
    private void for(final c c, final c c2) {
        final int n = (int)(c.char * 16.0f);
        final int n2 = (int)(c.case * 16.0f);
        final int n3 = (int)(c2.char * 16.0f);
        final int n4 = (int)(c2.case * 16.0f);
        final int n5 = n2 + 15 >> 4;
        this.aE = (n4 + 15 >> 4) - n5;
        if (this.aE != 0) {
            final int n6 = n4 - n2;
            final int ag = n6 << 4;
            final int n7 = n3 - n;
            final int n8 = n7 << 4;
            final int n9 = n8 * n5 - n7 * n2 + n6 * n - 1 + ag;
            this.h = n9 / ag;
            this.aF = n9 % ag;
            if (n9 < 0) {
                this.aF = -this.aF;
                if (this.aF != 0) {
                    --this.h;
                    this.aF = ag - this.aF;
                }
            }
            this.f = n8 / ag;
            this.aH = n8 % ag;
            if (n8 < 0) {
                this.aH = -this.aH;
                if (this.aH != 0) {
                    --this.f;
                    this.aH = ag - this.aH;
                }
            }
            this.aG = ag;
            final float n10 = ((n5 << 4) - n2) * 0.0625f;
            final float n11 = ((this.h << 4) - n) * 0.0625f;
            this.aD = c.try + n10 * this.P + n11 * this.R;
            this.aC = this.f * this.R + this.P;
            this.aB = this.R;
            if (this.case) {
                this.aA = c.int + n10 * this.L + n11 * this.N;
                this.az = this.f * this.N + this.L;
                this.ay = this.N;
                this.ax = c.do + n10 * this.H + n11 * this.J;
                this.aw = this.f * this.J + this.H;
                this.av = this.J;
            }
            if (this.byte) {
                this.au = c.byte + n10 * this.C + n11 * this.E;
                this.at = this.f * this.E + this.C;
                this.ar = this.E;
            }
        }
    }
    
    public void a(final boolean e) {
        this.e = e;
    }
    
    public boolean do() {
        return this.e;
    }
    
    public final void a(final g g, final boolean b) {
        this.aI = (this.G.appletParam_antialiasing == 1 | (this.G.appletParam_antialiasing == 2 & b));
        this.aK = (this.G.appletParam_bilinear == 1 | (this.G.appletParam_bilinear == 2 & b));
        this.G.allVisiblePointsNum = 0;
        this.G.allVisibleFacesNum = 0;
        this.G.allVisibleNormalsNum = 0;
        this.G.actualLightsNumber = 0;
        this.G.numberOfShapes = 0;
        if (this.G.avatarHeadlight) {
            final float[] actualLightining = this.G.actualLightining;
            actualLightining[1] = (actualLightining[0] = 0.0f);
            actualLightining[2] = -1.0f;
            actualLightining[3] = 1.0f;
            final Prove3d g2 = this.G;
            ++g2.actualLightsNumber;
        }
        final float[] char1 = this.G.char();
        this.case();
        this.if();
        g.a(char1, char1);
        this.G.do(true);
        this.G.do(false);
        this.int();
    }
    
    private int int(final int n) {
        c c = this.ap[n - 1];
        float case1 = c.case;
        boolean b = case1 >= 0.0f;
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            final c c2 = this.ap[i];
            final float case2 = c2.case;
            final boolean b2 = case2 >= 0.0f;
            if (b ^ b2) {
                final float n3 = case1 / (case1 - case2);
                final c c3 = this.ah[this.af++];
                c3.char = c.char + (c2.char - c.char) * n3;
                c3.case = 0.0f;
                c3.try = c.try + (c2.try - c.try) * n3;
                if (this.case) {
                    c3.int = c.int + (c2.int - c.int) * n3;
                    c3.do = c.do + (c2.do - c.do) * n3;
                }
                if (this.byte) {
                    c3.byte = c.byte + (c2.byte - c.byte) * n3;
                }
                this.an[n2++] = c3;
            }
            if (b2) {
                this.an[n2++] = c2;
            }
            b = b2;
            c = c2;
            case1 = case2;
        }
        return n2;
    }
    
    private void a(final c[] array, final int n) {
        System.arraycopy(array, 0, array, n, n);
        int n2 = 0;
        for (int i = 1; i < n; ++i) {
            if (array[i].case < array[n2].case) {
                n2 = i;
            }
        }
        int n3 = n2 + n;
        this.do(array[n2++], array[n2]);
        this.for(array[n3--], array[n3]);
        int n4 = n - 1;
        while (true) {
            if (this.x <= 0 || this.aE <= 0) {
                if (this.x == 0) {
                    if (--n4 == 0) {
                        return;
                    }
                    this.do(array[n2++], array[n2]);
                }
                if (this.aE != 0) {
                    continue;
                }
                if (--n4 == 0) {
                    return;
                }
                this.for(array[n3--], array[n3]);
            }
            else {
                if (this.z >= this.aJ) {
                    return;
                }
                if (this.case) {
                    if (this.aK) {
                        this.try();
                    }
                    else {
                        this.for();
                    }
                }
                else {
                    this.char();
                }
                this.new();
            }
        }
    }
    
    private int do(final int n) {
        c c = this.ap[n - 1];
        float n2 = c.if + this.g;
        boolean b = n2 <= 0.0f;
        int n3 = 0;
        for (int i = 0; i < n; ++i) {
            final c c2 = this.ap[i];
            final float n4 = c2.if + this.g;
            final boolean b2 = n4 <= 0.0f;
            if (b ^ b2) {
                final float n5 = n2 / (n2 - n4);
                final c c3 = this.ah[this.af++];
                c3.if = -this.g;
                final c c4 = c3;
                final float new1 = c.new + (c2.new - c.new) * n5;
                c4.new = new1;
                final float n6 = new1;
                final c c5 = c3;
                final float for1 = c.for + (c2.for - c.for) * n5;
                c5.for = for1;
                final float n7 = for1;
                final float n8 = this.c / -this.g;
                c3.char = n6 * n8 + this.b;
                c3.case = -n7 * n8 + this.void;
                final float try1 = n8 * this.d;
                c3.try = try1;
                if (this.case) {
                    final c c6 = c3;
                    final c c7 = c3;
                    final float goto1 = c.goto + (c2.goto - c.goto) * n5;
                    c7.goto = goto1;
                    c6.int = goto1 * try1 * this.for;
                    final c c8 = c3;
                    final c c9 = c3;
                    final float else1 = c.else + (c2.else - c.else) * n5;
                    c9.else = else1;
                    c8.do = else1 * try1 * this.for;
                }
                if (this.byte) {
                    c3.byte = c.byte + (c2.byte - c.byte) * n5;
                }
                this.an[n3++] = c3;
            }
            if (b2) {
                this.an[n3++] = c2;
            }
            b = b2;
            c = c2;
            n2 = n4;
        }
        return n3;
    }
    
    private int a(final int n) {
        c c = this.ap[n - 1];
        float n2 = this.m - c.case;
        boolean b = n2 >= 0.0f;
        int n3 = 0;
        for (int i = 0; i < n; ++i) {
            final c c2 = this.ap[i];
            final float n4 = this.m - c2.case;
            final boolean b2 = n4 >= 0.0f;
            if (b ^ b2) {
                final float n5 = n2 / (n2 - n4);
                final c c3 = this.ah[this.af++];
                c3.char = c.char + (c2.char - c.char) * n5;
                c3.case = this.m;
                c3.try = c.try + (c2.try - c.try) * n5;
                if (this.case) {
                    c3.int = c.int + (c2.int - c.int) * n5;
                    c3.do = c.do + (c2.do - c.do) * n5;
                }
                if (this.byte) {
                    c3.byte = c.byte + (c2.byte - c.byte) * n5;
                }
                this.an[n3++] = c3;
            }
            if (b2) {
                this.an[n3++] = c2;
            }
            b = b2;
            c = c2;
            n2 = n4;
        }
        return n3;
    }
    
    private void if(final c c, final c c2, final c c3) {
        final int n = (int)(c.char * 16.0f);
        final int n2 = (int)(c.case * 16.0f);
        final int n3 = (int)(c2.char * 16.0f);
        final int n4 = (int)(c2.case * 16.0f);
        final int n5 = (int)(c3.char * 16.0f);
        final int n6 = (int)(c3.case * 16.0f);
        final float n7 = n3 - n5;
        final float n8 = n2 - n6;
        final float n9 = n - n5;
        final float n10 = n4 - n6;
        final float n11 = 16.0f / (n7 * n8 / 16.0f - n9 * n10 / 16.0f);
        final float n12 = -n11;
        final float n13 = n7 * 0.0625f;
        final float n14 = n8 * 0.0625f;
        final float n15 = n9 * 0.0625f;
        final float n16 = n10 * 0.0625f;
        this.R = n11 * ((c2.try - c3.try) * n14 - (c.try - c3.try) * n16);
        this.P = n12 * ((c2.try - c3.try) * n15 - (c.try - c3.try) * n13);
        this.S = this.R * 16.0f;
        if (this.case) {
            this.N = n11 * ((c2.int - c3.int) * n14 - (c.int - c3.int) * n16);
            this.L = n12 * ((c2.int - c3.int) * n15 - (c.int - c3.int) * n13);
            this.J = n11 * ((c2.do - c3.do) * n14 - (c.do - c3.do) * n16);
            this.H = n12 * ((c2.do - c3.do) * n15 - (c.do - c3.do) * n13);
            final float n17 = this.N * c.try - c.int * this.R;
            if (n17 > 0.0f) {
                this.A = 32768L;
            }
            else if (n17 < 0.0f) {
                this.A = 32767L;
            }
            else if (this.L * c.try - c.int * this.P >= 0.0f) {
                this.A = 32768L;
            }
            else {
                this.A = 32767L;
            }
            final float n18 = this.J * c.try - c.do * this.R;
            if (n18 > 0.0f) {
                this.y = 32768L;
            }
            else if (n18 < 0.0f) {
                this.y = 32767L;
            }
            else if (this.H * c.try - c.do * this.P >= 0.0f) {
                this.y = 32768L;
            }
            else {
                this.y = 32767L;
            }
            this.Q = this.N * 16.0f;
            this.O = this.J * 16.0f;
        }
        if (this.byte) {
            this.E = n11 * ((c2.byte - c3.byte) * n14 - (c.byte - c3.byte) * n16);
            this.C = n12 * ((c2.byte - c3.byte) * n15 - (c.byte - c3.byte) * n13);
            final float n19 = this.E * c.try - c.byte * this.R;
            if (n19 > 0.0f) {
                this.U = 32768L;
            }
            else if (n19 < 0.0f) {
                this.U = 32767L;
            }
            else if (this.C * c.try - c.byte * this.P >= 0.0f) {
                this.U = 32768L;
            }
            else {
                this.U = 32767L;
            }
            this.M = this.E * 16.0f;
        }
    }
    
    private void int() {
        if (this.aI) {
            this.do = this.G.appletWidth;
            this.m = this.G.appletHeight;
            final int[] else1 = this.else;
            this.else = this.a;
            this.a = else1;
            final int[] char1 = this.char;
            this.char = this.goto;
            this.goto = char1;
            int n = 0;
            int n2 = 0;
            final int n3 = this.do << 1;
            for (int i = 0; i < this.m; ++i) {
                for (int j = 0; j < this.do; ++j) {
                    final int n4 = this.else[n];
                    final int n5 = this.else[n + 1];
                    final int n6 = this.else[n + n3];
                    final int n7 = this.else[n + n3 + 1];
                    this.a[n2++] = ((n4 & 0xFF0000) + (n5 & 0xFF0000) + (n6 & 0xFF0000) + (n7 & 0xFF0000) >> 2 & 0xFF0000) + ((n4 & 0xFF00) + (n5 & 0xFF00) + (n6 & 0xFF00) + (n7 & 0xFF00) >> 2 & 0xFF00) + ((n4 & 0xFF) + (n5 & 0xFF) + (n6 & 0xFF) + (n7 & 0xFF) >> 2 & 0xFF);
                    n += 2;
                }
                n += n3;
            }
        }
        this.if.newPixels();
        this.G.repaint();
    }
}
