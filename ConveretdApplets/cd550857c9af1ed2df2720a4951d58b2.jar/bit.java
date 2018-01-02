import java.awt.Color;
import java.awt.image.PixelGrabber;
import java.awt.MediaTracker;
import java.awt.Component;
import java.util.zip.Inflater;
import java.io.BufferedInputStream;
import java.net.URL;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.Socket;
import java.awt.event.KeyEvent;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ColorModel;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.awt.image.DirectColorModel;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class bit extends Applet implements Runnable, MouseListener, MouseMotionListener, KeyListener
{
    int a;
    int b;
    int[] c;
    int[] d;
    int[] e;
    int[] f;
    String[] g;
    a h;
    a i;
    int j;
    int k;
    int l;
    int m;
    int n;
    int o;
    int p;
    int q;
    int r;
    int s;
    float t;
    float u;
    boolean v;
    int w;
    int x;
    int y;
    int z;
    int A;
    int B;
    int C;
    int D;
    String E;
    boolean F;
    String G;
    int H;
    int I;
    a J;
    int K;
    int[] L;
    Thread M;
    DirectColorModel N;
    MemoryImageSource O;
    Image P;
    int[] Q;
    int[] R;
    String S;
    int T;
    int U;
    int V;
    int W;
    int X;
    int Y;
    int Z;
    int aa;
    int ab;
    long ac;
    long ad;
    long ae;
    long af;
    int ag;
    b ah;
    b ai;
    boolean aj;
    boolean ak;
    boolean al;
    boolean am;
    boolean an;
    boolean ao;
    boolean ap;
    boolean aq;
    boolean ar;
    int as;
    int at;
    int au;
    int av;
    int aw;
    int ax;
    int ay;
    boolean[] az;
    boolean[] aA;
    boolean[] aB;
    byte[] aC;
    byte[] aD;
    byte[] aE;
    byte[] aF;
    byte[] aG;
    byte[] aH;
    byte[] aI;
    byte[] aJ;
    byte[] aK;
    byte[] aL;
    String[] aM;
    byte[] aN;
    int aO;
    byte[] aP;
    int aQ;
    
    final void a() {
        this.e();
        this.g();
        this.b(50);
        this.ah.a("font.gif", 8, 12);
        this.ai.a("font_s.gif", 5, 7);
        this.i.a(512, 384);
        String parameter;
        if ((parameter = this.getParameter("a")) == null) {
            parameter = "";
        }
        final String[] array = { "", "", "", "" };
        int n = 0;
        for (int i = 1; i < parameter.length(); ++i) {
            final char char1;
            if ((char1 = parameter.charAt(i)) == ' ') {
                ++n;
            }
            else {
                final String[] array2 = array;
                final int n2 = n;
                array2[n2] = String.valueOf(array2[n2]) + char1;
            }
        }
        this.E = array[0];
        if (this.E.length() == 0) {
            this.E = "";
        }
        String s;
        if ((s = array[1]).length() == 0) {
            s = "";
        }
        this.G = array[2];
        if (this.G.length() == 0) {
            this.G = "";
        }
        if (array[3].equals("3")) {
            this.H = 3;
        }
        if (array[3].equals("1")) {
            this.H = 5;
        }
        bit bit;
        boolean f;
        if (s.equals("0")) {
            bit = this;
            f = true;
        }
        else {
            bit = this;
            f = false;
        }
        bit.F = f;
        for (int j = 0; j < 49152; ++j) {
            this.c[j] = 0;
        }
        for (int k = 0; k < this.f.length; ++k) {
            this.f[k] = 0;
        }
        for (int l = 0; l < this.g.length; ++l) {
            this.g[l] = "";
        }
        this.a(String.valueOf(new String(new byte[] { 104, 116, 116, 112, 58, 47, 47, 100, 97, 110, 45, 98, 97, 108, 108, 46, 106, 112, 47, 115, 99, 111, 114, 101, 47, 98, 105, 116, 46, 112, 104, 112, 63, 97, 61, 48, 38, 98, 61 })) + this.G);
        this.a(this.aN, this.aO);
        if (this.aQ >= 6144) {
            int n3 = 0;
            int n4;
            for (n4 = 0; n4 < 6144; ++n4) {
                this.c[n3++] = (this.aP[n4] >> 7 & 0x1);
                this.c[n3++] = (this.aP[n4] >> 6 & 0x1);
                this.c[n3++] = (this.aP[n4] >> 5 & 0x1);
                this.c[n3++] = (this.aP[n4] >> 4 & 0x1);
                this.c[n3++] = (this.aP[n4] >> 3 & 0x1);
                this.c[n3++] = (this.aP[n4] >> 2 & 0x1);
                this.c[n3++] = (this.aP[n4] >> 1 & 0x1);
                this.c[n3++] = (this.aP[n4] & 0x1);
            }
            final int n5;
            final int[] array3 = new int[n5 = ((this.aP[n4++] << 8 & 0xFF00) | (this.aP[n4++] & 0xFF))];
            for (int n6 = 0; n6 < n5; ++n6) {
                array3[n6] = this.aP[n4++];
            }
            for (int n7 = 0; n7 < n5; ++n7) {
                for (int n8 = 0; n8 < array3[n7]; ++n8) {
                    this.e[this.j++] = ((this.aP[n4] << 8 & 0xFF00) | (this.aP[n4 + 1] & 0xFF));
                    if (this.e[this.j - 1] < 0 || this.e[this.j - 1] >= 49152) {
                        this.e[this.j - 1] = 49151;
                    }
                    n4 += 2;
                }
            }
            for (int n9 = this.j - 1; n9 >= 0; --n9) {
                final int[] c = this.c;
                final int n10 = this.e[n9];
                c[n10] ^= 0x1;
            }
            int n12;
            for (int n11 = 0; n11 < n5 && n4 < this.aQ; ++n11, n4 += n12 + 1) {
                for (n12 = 0; n12 + n4 < this.aQ; ++n12) {
                    if (this.aP[n4 + n12] == 10) {
                        break;
                    }
                }
                try {
                    this.g[n11] = new String(this.aP, n4, n12, "UTF8");
                }
                catch (Exception ex) {
                    this.g[n11] = new String(this.aP, n4, n12);
                }
            }
            int n13 = 0;
            for (int n14 = 0; n14 < n5; ++n14) {
                for (int n15 = 0; n15 < array3[n14]; ++n15) {
                    this.f[n13++] = n14;
                }
            }
        }
        else {
            this.I = 1;
        }
        this.c();
        this.a(-1);
    }
    
    final void b() {
        this.f();
        this.h();
        Label_0068: {
            bit bit;
            boolean v;
            if (this.au >= 384 && this.ar && !this.v) {
                bit = this;
                v = true;
            }
            else {
                if (this.au >= 384 || !this.ar || !this.v) {
                    break Label_0068;
                }
                bit = this;
                v = false;
            }
            bit.v = v;
        }
        int o = this.o;
        if (this.n == 1) {
            o = 0;
        }
        this.m = a(this.m + o, 0, this.j + this.k);
        this.z = this.x;
        this.A = this.y;
        this.x = a(this.at, 0, 511);
        this.y = a(this.au, 0, 383);
        if (this.s > 0) {
            this.x = (int)this.t + (this.at >> this.s);
            this.y = (int)this.u + (this.au >> this.s);
        }
        if (this.x != this.z || this.y != this.A) {
            this.B = this.x / 2;
            this.C = this.y / 2;
        }
        if (!this.v && this.D == 0) {
            if ((this.aj || this.az[32]) && this.m != this.j + this.k) {
                this.m = this.j + this.k;
                this.o = 1;
            }
            else if (this.aj || this.az[32]) {
                if (this.r == 0) {
                    int n;
                    int i;
                    for (n = this.C * 256 + this.B, i = this.j; i < this.j + this.k && this.e[i] != n; ++i) {}
                    if (i == this.j + this.k) {
                        if (this.k < 100) {
                            this.e[this.j + this.k] = n;
                            ++this.k;
                        }
                    }
                    else {
                        --this.k;
                        while (i < this.j + this.k) {
                            this.e[i] = this.e[i + 1];
                            ++i;
                        }
                    }
                    this.m = this.j + this.k;
                    this.l = this.k;
                }
            }
            else if (this.ap && this.s > 0) {
                this.t -= (this.at - this.av) / (1 << this.s);
                this.u -= (this.au - this.aw) / (1 << this.s);
                this.t = a(this.t, 0.0f, 512 - (512 >> this.s));
                this.u = a(this.u, 0.0f, 384 - (384 >> this.s));
            }
        }
        this.w = -1;
        Label_1183: {
            if (this.ar && this.D == 0) {
                if (this.az[49]) {
                    if (this.n == 0) {
                        this.m = 0;
                    }
                    else {
                        this.n = 0;
                    }
                }
                else if (this.az[50]) {
                    this.n = 1 - this.n;
                }
                else if (this.az[51]) {
                    this.m = a(this.m - 1, 0, this.j + this.k);
                }
                else if (this.az[52]) {
                    this.m = a(this.m + 1, 0, this.j + this.k);
                }
                else if (this.az[53]) {
                    if (this.o == -1) {
                        this.o = 1;
                    }
                    else {
                        this.o = -1;
                    }
                }
                else if (this.az[54]) {
                    if (this.o == 2) {
                        this.o = 1;
                    }
                    else {
                        this.o = 2;
                    }
                }
                else if (this.az[37]) {
                    this.B = a(this.B - 1, 0, 255);
                }
                else if (this.az[39]) {
                    this.B = a(this.B + 1, 0, 255);
                }
                else if (this.az[38]) {
                    this.C = a(this.C - 1, 0, 191);
                }
                else if (this.az[40]) {
                    this.C = a(this.C + 1, 0, 191);
                }
                else if (this.az[122]) {
                    this.k = a(this.k - 1, 0, this.l);
                    this.m = this.j + this.k;
                }
                else if (this.az[121]) {
                    this.k = a(this.k + 1, 0, this.l);
                    this.m = this.j + this.k;
                }
                else if (this.az[119]) {
                    this.u = a(this.u - 2.0f, 0.0f, 384 - (384 >> this.s));
                }
                else {
                    bit bit2;
                    float n2;
                    if (this.az[97]) {
                        bit2 = this;
                        n2 = this.t - 2.0f;
                    }
                    else {
                        if (this.az[115]) {
                            this.u = a(this.u + 2.0f, 0.0f, 384 - (384 >> this.s));
                            break Label_1183;
                        }
                        if (!this.az[100]) {
                            break Label_1183;
                        }
                        bit2 = this;
                        n2 = this.t + 2.0f;
                    }
                    bit2.t = a(n2, 0.0f, 512 - (512 >> this.s));
                }
            }
        }
        if (this.j + this.k > 0) {
            if (this.p == 1 && this.al) {
                this.m = (int)((this.j + this.k) * a(this.at - 56, 0, 399) / 399.0f + 0.5f);
            }
            else if (this.p == 1 && this.ak) {
                this.p = 0;
            }
            if (this.b(56, 384, 399, 23) && this.v && this.D == 0) {
                final int a = a(this.at - 56, 0, 399);
                if (this.aj) {
                    this.p = 1;
                    this.m = (int)((this.j + this.k) * a / 399.0f + 0.5f);
                }
                this.w = 100;
            }
        }
        if (this.b(9, 408, 493, 23) && this.v && this.D == 0) {
            this.w = a((this.at - 9) / 38, 0, 12);
            if (this.w == 0) {
                if (this.ak) {
                    if (this.n == 0) {
                        this.m = 0;
                    }
                    else {
                        this.n = 0;
                    }
                }
            }
            else if (this.w == 1) {
                if (this.ak) {
                    this.n = 1 - this.n;
                }
            }
            else if (this.w == 2) {
                if (this.ak) {
                    this.m = a(this.m - 1, 0, this.j + this.k);
                }
            }
            else if (this.w == 3) {
                if (this.ak) {
                    this.m = a(this.m + 1, 0, this.j + this.k);
                }
            }
            else if (this.w == 4) {
                if (this.ak) {
                    if (this.o == -1) {
                        this.o = 1;
                    }
                    else {
                        this.o = -1;
                    }
                }
            }
            else if (this.w == 5) {
                if (this.ak) {
                    if (this.o == 2) {
                        this.o = 1;
                    }
                    else {
                        this.o = 2;
                    }
                }
            }
            else if (this.w == 10) {
                this.q = b(this.q + this.as, 0, 2);
            }
            else if (this.w == 11) {
                Label_1870: {
                    bit bit3;
                    float n3;
                    int n4;
                    if (this.aj) {
                        if (this.s >= 3) {
                            this.s = 0;
                            break Label_1870;
                        }
                        ++this.s;
                        this.t += 512 >> this.s >> 1;
                        bit3 = this;
                        n3 = this.u;
                        n4 = 384 >> this.s >> 1;
                    }
                    else {
                        if (!this.an) {
                            break Label_1870;
                        }
                        if (this.s > 0) {
                            this.t -= 512 >> this.s >> 1;
                            this.u -= 384 >> this.s >> 1;
                            --this.s;
                            break Label_1870;
                        }
                        this.s = 3;
                        this.t += 256 - (512 >> this.s >> 1);
                        bit3 = this;
                        n3 = this.u;
                        n4 = 192 - (384 >> this.s >> 1);
                    }
                    bit3.u = n3 + n4;
                }
                this.t = a(this.t, 0.0f, 512 - (512 >> this.s));
                this.u = a(this.u, 0.0f, 384 - (384 >> this.s));
            }
            else if (this.w == 12 && this.ak) {
                this.D = 1;
                this.s = 0;
            }
            if (this.w == 6 || this.w == 7 || this.w == 8 || this.w == 9) {
                this.w = -1;
            }
        }
        for (int j = 0; j < 49152; ++j) {
            this.d[j] = this.c[j];
        }
        for (int k = 0; k < this.m; ++k) {
            final int[] d = this.d;
            final int n5 = this.e[k];
            d[n5] ^= 0x1;
        }
        final int[] array = { -1, -16777216 };
        int n6 = 0;
        for (int n7 = 0, l = 0; l < 192; ++l, n7 += 512) {
            for (int n8 = 0; n8 < 256; ++n8, ++n6, n7 += 2) {
                this.Q[n7] = array[this.d[n6]];
                this.Q[n7 + 1] = array[this.d[n6]];
                this.Q[n7 + 512] = array[this.d[n6]];
                this.Q[n7 + 513] = array[this.d[n6]];
            }
        }
        if (this.w == 12) {
            for (int m = this.j; m < this.m; ++m) {
                final int n9 = (this.e[m] >> 8) * this.a * 2 + (this.e[m] & 0xFF) * 2;
                this.Q[n9] = -65536;
                this.Q[n9 + 1] = -65536;
                this.Q[n9 + 512] = -65536;
                this.Q[n9 + 513] = -65536;
            }
        }
        if (this.q == 1) {
            for (int n10 = this.m - 1; n10 >= 0 && this.f[n10] == this.f[this.m - 1]; --n10) {
                final int n11 = (this.e[n10] >> 8) * this.a * 2 + (this.e[n10] & 0xFF) * 2;
                this.Q[n11] = -65536;
                this.Q[n11 + 1] = -65536;
                this.Q[n11 + 512] = -65536;
                this.Q[n11 + 513] = -65536;
            }
        }
        else if (this.q == 2) {
            this.V = 1;
            final int b = b(this.j - this.m, 200);
            for (int a2 = a(this.m - b, 1); a2 < this.m && a2 < this.j; ++a2) {
                if (a2 >= this.m - 1 || this.f[a2] != this.f[a2 + 1]) {
                    final int a3 = a((this.e[a2] & 0xFF) * 2, 40, 471);
                    final int n12 = (this.e[a2] >> 8) * 2;
                    final int n13 = n12 + ((n12 < 364) ? 12 : -10);
                    final int b2 = b(4080 * (b - (this.m - a2)) / b, 255);
                    this.h.b(this.g[this.f[a2]]);
                    this.b(a3, n13, this.h.d + 4, 16, b2 << 24 | 0xFFFF);
                    this.b(this.h, a3, n13, this.h.d, 16, 0, 0, this.h.d, 16, b2 << 24 | 0x666666);
                }
            }
            this.V = 0;
        }
        if (!this.v && this.D == 0) {
            this.V = 1;
            this.a(this.B * 2, this.C * 2, 2, 1, -855703552);
            this.a(this.B * 2, this.C * 2, 1, 2, -855703552);
            this.V = 0;
        }
        if (this.s != 0) {
            for (int n14 = 196607; n14 >= 0; --n14) {
                this.i.e[n14] = this.Q[n14];
            }
            this.a(this.i, 0, 0, 512, 384, (int)this.t, (int)this.u, 512 >> this.s, 384 >> this.s, -1);
        }
        this.a(0, 384, this.a, 64, -12566464);
        int n15 = 1;
        int m2;
        int n16 = m2 = this.m;
        Label_2869: {
            break Label_2869;
            do {
                ++n15;
                final int n17;
                n16 = (m2 = n17 / 10);
                n17 = m2;
            } while (n16 >= 10);
        }
        this.ai.a(52 - n15 * 5, 392, String.valueOf(this.m), 255, 255, 255, 255, 0, 0, 0, 255);
        if (this.w == 100) {
            this.a(56, 393, 400, 6, -12578800);
        }
        this.a(56, 395, 400, 2, -16777216);
        if (this.j + this.k > 0) {
            this.a(56 + (int)(399 * this.m / (this.j + this.k) + 0.5f), 394, 1, 4, -65536);
        }
        this.ai.a(460, 392, "BIT " + (100 - this.k), 255, 255, 255, 255, 0, 0, 0, 255);
        for (int n18 = 0; n18 < 13; ++n18) {
            if (n18 != 6 && n18 != 7 && n18 != 8) {
                if (n18 != 9) {
                    this.a(12 + 38 * n18 - 1, 407, 33, 25, (this.w == n18 && this.al) ? -14540254 : -10066330);
                    this.a(12 + 38 * n18, 408, 33, 25, (this.w == n18 && this.al) ? -10066330 : -14540254);
                    this.a(12 + 38 * n18, 408, 32, 24, (this.w == n18 && !this.al) ? -12574688 : -12566464);
                }
            }
        }
        Object object;
        int n19;
        int n20;
        String s;
        int n21;
        int n22;
        int n23;
        if (this.n == 0) {
            object = this.ah;
            n19 = 28;
            n20 = 420;
            s = ">";
            n21 = 242;
            n22 = 64;
            n23 = 64;
        }
        else {
            object = this.ah;
            n19 = 28;
            n20 = 420;
            s = ">";
            n21 = 242;
            n22 = 189;
            n23 = 107;
        }
        object.b(n19, n20, s, n21, n22, n23, 255, 0, 0, 0, 255);
        Object object2;
        int n24;
        int n25;
        String s2;
        int n26;
        int n27;
        int n28;
        if (this.n == 0) {
            object2 = this.ah;
            n24 = 66;
            n25 = 420;
            s2 = "||";
            n26 = 242;
            n27 = 189;
            n28 = 107;
        }
        else {
            object2 = this.ah;
            n24 = 66;
            n25 = 420;
            s2 = "||";
            n26 = 242;
            n27 = 64;
            n28 = 64;
        }
        object2.b(n24, n25, s2, n26, n27, n28, 255, 0, 0, 0, 255);
        this.ah.b(104, 420, "<|", 242, 189, 107, 255, 0, 0, 0, 255);
        this.ah.b(142, 420, "|>", 242, 189, 107, 255, 0, 0, 0, 255);
        Object object3;
        int n29;
        int n30;
        String s3;
        int n31;
        int n32;
        int n33;
        if (this.o == -1) {
            object3 = this.ah;
            n29 = 180;
            n30 = 420;
            s3 = "<<";
            n31 = 242;
            n32 = 64;
            n33 = 64;
        }
        else {
            object3 = this.ah;
            n29 = 180;
            n30 = 420;
            s3 = "<<";
            n31 = 242;
            n32 = 189;
            n33 = 107;
        }
        object3.b(n29, n30, s3, n31, n32, n33, 255, 0, 0, 0, 255);
        Object object4;
        int n34;
        int n35;
        String s4;
        int n36;
        int n37;
        int n38;
        if (this.o == 2) {
            object4 = this.ah;
            n34 = 218;
            n35 = 420;
            s4 = ">>";
            n36 = 242;
            n37 = 64;
            n38 = 64;
        }
        else {
            object4 = this.ah;
            n34 = 218;
            n35 = 420;
            s4 = ">>";
            n36 = 242;
            n37 = 189;
            n38 = 107;
        }
        object4.b(n34, n35, s4, n36, n37, n38, 255, 0, 0, 0, 255);
        this.ai.b(41, 428, "1", 0, 0, 0, 255, 0, 0, 0, 0);
        this.ai.b(79, 428, "2", 0, 0, 0, 255, 0, 0, 0, 0);
        this.ai.b(117, 428, "3", 0, 0, 0, 255, 0, 0, 0, 0);
        this.ai.b(155, 428, "4", 0, 0, 0, 255, 0, 0, 0, 0);
        this.ai.b(193, 428, "5", 0, 0, 0, 255, 0, 0, 0, 0);
        this.ai.b(231, 428, "6", 0, 0, 0, 255, 0, 0, 0, 0);
        if (this.m > 0 && this.m <= this.j) {
            this.ah.b(313, 412, "name", 255, 255, 255, 255, 0, 0, 0, 255);
            this.h.b(this.g[this.f[this.m - 1]]);
            this.b(this.h, 313, 426, this.h.d, 16, 0, 0, this.h.d, 16, -1);
        }
        final String[] array2 = { "non", "dot", "name" };
        this.ah.n = -1;
        this.ah.b(407, 415, "DRAW", 255, 255, 255, 255, 0, 0, 0, 255);
        this.ah.b(407, 426, array2[this.q], 255, 255, 255, 255, 255, 255, 255, 0);
        this.ah.n = 0;
        final String[] array3 = { "x1", "x2", "x4", "x8" };
        this.ah.n = -2;
        this.ah.b(445, 415, "SCALE", 255, 255, 255, 0, 0, 0, 0, 255);
        this.ah.b(445, 415, "SCALE", 255, 255, 255, 255, 0, 0, 0, 0);
        this.ah.b(445, 426, array3[this.s], 255, 255, 255, 255, 255, 255, 255, 0);
        this.ah.n = 0;
        this.ah.b(484, 420, "UP", 255, 160, 160, 255, 0, 0, 0, 255);
        this.a(8, 435, 0, 0, 0, 0, 0, 0, 0, 255);
        this.ah.a(72, 435, "x    y", 0, 0, 0, 0, 0, 0, 0, 255);
        this.ah.a(72, 435, " " + this.B, 0, 0, 0, 0, 0, 0, 0, 255);
        this.ah.a(72, 435, "      " + this.C, 0, 0, 0, 0, 0, 0, 0, 255);
        int n39 = 0;
        for (int n40 = 0; n40 < 49152; ++n40) {
            n39 += this.d[n40];
        }
        this.a(168, 438, 5, 5, -1);
        this.ah.a(168, 435, " " + (49152 - n39), 0, 0, 0, 0, 0, 0, 0, 255);
        this.a(224, 438, 5, 5, -16777216);
        this.ah.a(224, 435, " " + n39, 0, 0, 0, 0, 0, 0, 0, 255);
        final String s5 = new String(new byte[] { 68, 65, 78, 45, 66, 65, 76, 76, 46, 106, 112, 32, 40, 67, 41, 32, 50, 48, 49, 49, 32, 104, 97, 53, 53, 105, 105 });
        this.ah.n = -1;
        this.ah.a(312, 435, s5, 0, 0, 0, 0, 0, 0, 0, 255);
        this.ah.n = 0;
        if (this.I == 1) {
            this.ah.b(256, 192, "Error: download", 0, 0, 0, 0, 0, 0, 0, 255);
        }
        Label_5159: {
            bit bit4;
            int d2;
            if (this.D == 1) {
                if (this.I == 1) {
                    if (this.F) {}
                    this.J.b("Error: download.");
                    bit4 = this;
                    d2 = 4;
                }
                else if (this.G.length() != 0) {
                    Object object5;
                    String s6;
                    if (this.F) {
                        object5 = this.J;
                        s6 = "\u904e\u53bb\u306e\u30c7\u30fc\u30bf\u3067\u3059\u3002";
                    }
                    else {
                        object5 = this.J;
                        s6 = "Past data.";
                    }
                    object5.b(s6);
                    bit4 = this;
                    d2 = 4;
                }
                else if (this.k == 0) {
                    Object object6;
                    String s7;
                    if (this.F) {
                        object6 = this.J;
                        s7 = "\u30af\u30ea\u30c3\u30af\u3057\u3066\u4e0b\u3055\u3044\u3002";
                    }
                    else {
                        object6 = this.J;
                        s7 = "Please click.";
                    }
                    object6.b(s7);
                    bit4 = this;
                    d2 = 4;
                }
                else if (this.E.length() == 0) {
                    Object object7;
                    String s8;
                    if (this.F) {
                        object7 = this.J;
                        s8 = "\u30e6\u30fc\u30b6\u30fc\u767b\u9332\u304c\u5fc5\u8981\u3067\u3059\u3002";
                    }
                    else {
                        object7 = this.J;
                        s8 = "User registration is required.";
                    }
                    object7.b(s8);
                    bit4 = this;
                    d2 = 4;
                }
                else if (this.H == 3) {
                    Object object8;
                    String s9;
                    if (this.F) {
                        object8 = this.J;
                        s9 = "6\u6642\u9593\u306b1\u56de\u306e\u307f\u3067\u3059\u3002";
                    }
                    else {
                        object8 = this.J;
                        s9 = "1 time in 6 hours only.";
                    }
                    object8.b(s9);
                    bit4 = this;
                    d2 = 4;
                }
                else if (this.H == 5) {
                    Object object9;
                    String s10;
                    if (this.F) {
                        object9 = this.J;
                        s10 = "\u30a2\u30c3\u30d7\u30ed\u30fc\u30c9\u51fa\u6765\u307e\u305b\u3093\u3002";
                    }
                    else {
                        object9 = this.J;
                        s10 = "Cannot upload.";
                    }
                    object9.b(s10);
                    bit4 = this;
                    d2 = 4;
                }
                else if (this.H == 0) {
                    Object object10;
                    String s11;
                    if (this.F) {
                        object10 = this.J;
                        s11 = "\u30a2\u30c3\u30d7\u30ed\u30fc\u30c9\u3057\u307e\u3059\u304b\uff1f";
                    }
                    else {
                        object10 = this.J;
                        s11 = "Upload?";
                    }
                    object10.b(s11);
                    bit4 = this;
                    d2 = 2;
                }
                else {
                    bit4 = this;
                    d2 = 4;
                }
            }
            else {
                if (this.D != 2) {
                    break Label_5159;
                }
                this.b(256, 192, 320, 80, -8355712);
                this.ah.b(256, 172, "UPLOAD", 255, 160, 160, 255, 0, 0, 0, 255);
                this.b(this.J, 256, 192, this.J.d, 16, 0, 0, this.J.d, 16, -16777216);
                final boolean c = this.c(206, 212, 60, 20);
                this.ah.b(206, 212, "OK", 255, c ? 0 : 255, c ? 0 : 255, 255, 0, 0, 0, 255);
                if (c && this.ak) {
                    this.D = 3;
                }
                final boolean c2 = this.c(306, 212, 60, 20);
                this.ah.b(306, 212, "CANCEL", 255, c2 ? 0 : 255, c2 ? 0 : 255, 255, 0, 0, 0, 255);
                if (!c2 || !this.ak) {
                    break Label_5159;
                }
                bit4 = this;
                d2 = 0;
            }
            bit4.D = d2;
        }
        if (this.D == 3) {
            final String string = String.valueOf(new String(new byte[] { 104, 116, 116, 112, 58, 47, 47, 100, 97, 110, 45, 98, 97, 108, 108, 46, 106, 112, 47, 115, 99, 111, 114, 101, 47, 98, 105, 116, 46, 112, 104, 112, 63, 97, 61, 49, 38, 98, 61 })) + this.E;
            final byte[] array4 = new byte[1 + this.k + this.k * 2];
            int n41 = 0;
            final byte[] array5 = array4;
            final int n42 = 0;
            ++n41;
            array5[n42] = (byte)this.k;
            int j2 = this.j;
            while (j2 < this.j + this.k) {
                array4[n41] = 0;
                byte[] array6;
                int n44;
                for (int n43 = 7; n43 >= 0 && j2 < this.j + this.k; array6[n44] |= (byte)(this.d[this.e[j2++]] << n43), --n43) {
                    array6 = array4;
                    n44 = n41;
                }
                ++n41;
            }
            for (int j3 = this.j; j3 < this.j + this.k; ++j3) {
                array4[n41++] = (byte)(this.e[j3] >> 8 & 0xFF);
                array4[n41++] = (byte)(this.e[j3] & 0xFF);
            }
            this.aM = this.a(string, "", array4, 1);
            Object object11;
            String s12;
            if (this.aM[0].equals("ok")) {
                this.H = 1;
                if (this.F) {
                    object11 = this.J;
                    s12 = "\u30a2\u30c3\u30d7\u30ed\u30fc\u30c9\u3057\u307e\u3057\u305f\u3002";
                }
                else {
                    object11 = this.J;
                    s12 = "Upload OK.";
                }
            }
            else {
                this.H = 2;
                if (this.F) {
                    if (this.aM[0].equals("err1")) {
                        object11 = this.J;
                        s12 = "\u30e6\u30fc\u30b6\u30fc\u767b\u9332\u304c\u5fc5\u8981\u3067\u3059\u3002";
                    }
                    else if (this.aM[0].equals("err2")) {
                        object11 = this.J;
                        s12 = "6\u6642\u9593\u306b1\u56de\u306e\u307f\u3067\u3059\u3002";
                    }
                    else {
                        object11 = this.J;
                        s12 = "\u30a2\u30c3\u30d7\u30ed\u30fc\u30c9\u51fa\u6765\u307e\u305b\u3093\u3002";
                    }
                }
                else if (this.aM[0].equals("err1")) {
                    object11 = this.J;
                    s12 = "User registration is required.";
                }
                else if (this.aM[0].equals("err2")) {
                    object11 = this.J;
                    s12 = "1 time in 6 hours only.";
                }
                else {
                    object11 = this.J;
                    s12 = "Cannot upload.";
                }
            }
            object11.b(s12);
            this.D = 4;
            return;
        }
        if (this.D == 4) {
            this.b(256, 192, 320, 80, -8355712);
            this.ah.b(256, 172, "UPLOAD", 255, 160, 160, 255, 0, 0, 0, 255);
            this.b(this.J, 256, 192, this.J.d, 16, 0, 0, this.J.d, 16, -16777216);
            final boolean c3 = this.c(256, 212, 60, 20);
            this.ah.b(256, 212, "OK", 255, c3 ? 0 : 255, c3 ? 0 : 255, 255, 0, 0, 0, 255);
            if (c3 && this.ak) {
                this.D = 0;
            }
        }
    }
    
    public void init() {
        this.Q = new int[this.a * this.b];
        this.N = new DirectColorModel(32, 16711680, 65280, 255);
        (this.O = new MemoryImageSource(this.a, this.b, this.Q, 0, this.a)).setFullBufferUpdates(true);
        this.O.setAnimated(true);
        this.P = this.createImage(this.O);
        this.a();
        System.out.println("Copyright(C) 2011 ha55ii, http://dan-ball.jp/");
    }
    
    public void start() {
        if (this.M == null) {
            (this.M = new Thread(this)).start();
        }
    }
    
    public void run() {
        while (this.M != null) {
            this.b();
            this.paint(this.getGraphics());
            this.d();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (graphics == null || this.O == null || this.P == null) {
            System.out.println("null");
            return;
        }
        this.O.newPixels(this.Q, this.N, 0, this.a);
        graphics.drawImage(this.P, 0, 0, null);
    }
    
    public void stop() {
        this.M = null;
    }
    
    final void c() {
        this.U = this.L.length;
        bit bit = this;
        int t = 0;
        Label_0047: {
            break Label_0047;
            do {
                bit = this;
                t = this.T + 1;
                bit.T = t;
            } while (this.T < this.U - 1 && this.S.charAt(this.T) == this.L[this.T]);
        }
        if (this.T == this.U - 1 && this.S.charAt(this.T) == this.L[this.T]) {
            this.R[0] = 1;
            ++this.T;
        }
        if (this.T != this.U) {
            this.Q = null;
        }
    }
    
    final void a(final int n) {
        for (int i = this.a * this.b - 1; i >= 0; --i) {
            this.Q[i] = n;
        }
    }
    
    static int a(final int n, final int n2, final int n3, final int n4) {
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n13 = 0;
        Label_0570: {
            int n11;
            int n12;
            if (n == 1) {
                final int n8 = n2 >> 16 & 0xFF;
                n5 = (((n3 >> 16 & 0xFF) - n8) * n4 >> 8) + n8;
                final int n9 = n2 >> 8 & 0xFF;
                n6 = (((n3 >> 8 & 0xFF) - n9) * n4 >> 8) + n9;
                final int n10 = n2 & 0xFF;
                n11 = ((n3 & 0xFF) - n10) * n4 >> 8;
                n12 = n10;
            }
            else if (n == 2) {
                if ((n5 = ((n3 >> 16 & 0xFF) * n4 >> 8) + (n2 >> 16 & 0xFF)) > 255) {
                    n5 = 255;
                }
                if ((n6 = ((n3 >> 8 & 0xFF) * n4 >> 8) + (n2 >> 8 & 0xFF)) > 255) {
                    n6 = 255;
                }
                if ((n7 = ((n3 & 0xFF) * n4 >> 8) + (n2 & 0xFF)) > 255) {
                    n13 = 255;
                    break Label_0570;
                }
                return 0xFF000000 | n5 << 16 | n6 << 8 | n7;
            }
            else if (n == 3) {
                if ((n5 = (n2 >> 16 & 0xFF) - ((n3 >> 16 & 0xFF) * n4 >> 8)) < 0) {
                    n5 = 0;
                }
                if ((n6 = (n2 >> 8 & 0xFF) - ((n3 >> 8 & 0xFF) * n4 >> 8)) < 0) {
                    n6 = 0;
                }
                if ((n7 = (n2 & 0xFF) - ((n3 & 0xFF) * n4 >> 8)) < 0) {
                    n13 = 0;
                    break Label_0570;
                }
                return 0xFF000000 | n5 << 16 | n6 << 8 | n7;
            }
            else {
                if (n == 4) {
                    n5 = (n3 >> 16 & 0xFF) * (n2 >> 16 & 0xFF) >> 8;
                    n6 = (n3 >> 8 & 0xFF) * (n2 >> 8 & 0xFF) >> 8;
                    n13 = (n3 & 0xFF) * (n2 & 0xFF) >> 8;
                    break Label_0570;
                }
                if (n == 5) {
                    final int n14 = n2 >> 16 & 0xFF;
                    if ((n5 = n14 + ((n3 >> 16 & 0xFF) * n14 * n4 >> 16)) > 255) {
                        n5 = 255;
                    }
                    final int n15 = n2 >> 8 & 0xFF;
                    if ((n6 = n15 + ((n3 >> 8 & 0xFF) * n15 * n4 >> 16)) > 255) {
                        n6 = 255;
                    }
                    final int n16 = n2 & 0xFF;
                    if ((n7 = n16 + ((n3 & 0xFF) * n16 * n4 >> 16)) > 255) {
                        n13 = 255;
                        break Label_0570;
                    }
                    return 0xFF000000 | n5 << 16 | n6 << 8 | n7;
                }
                else {
                    if (n != 6) {
                        return 0xFF000000 | n5 << 16 | n6 << 8 | n7;
                    }
                    final int n17 = n2 >> 16 & 0xFF;
                    n5 = n17 + (n4 - (2 * n17 * n4 >> 8));
                    final int n18 = n2 >> 8 & 0xFF;
                    n6 = n18 + (n4 - (2 * n18 * n4 >> 8));
                    n12 = n4 - (2 * (n11 = (n2 & 0xFF)) * n4 >> 8);
                }
            }
            n13 = n11 + n12;
        }
        n7 = n13;
        return 0xFF000000 | n5 << 16 | n6 << 8 | n7;
    }
    
    final void a(final int n, final int n2, final int n3, final int n4, final int n5) {
        final int n6 = (n < 0) ? 0 : n;
        int i = (n2 < 0) ? 0 : n2;
        final int n7 = (n + n3 > this.a) ? this.a : (n + n3);
        final int n8 = (n2 + n4 > this.b) ? this.b : (n2 + n4);
        int n9 = i * this.a + n6;
        final int n10 = n5 >> 24 & 0xFF;
        final int n11 = this.a - (n7 - n6);
        while (i < n8) {
            for (int j = n6; j < n7; ++j) {
                int[] array;
                int n12;
                int a;
                if (n10 == 255) {
                    array = this.Q;
                    n12 = n9;
                    a = n5;
                }
                else {
                    array = this.Q;
                    n12 = n9;
                    a = a(this.V, this.Q[n9], n5, n10);
                }
                array[n12] = a;
                ++n9;
            }
            n9 += n11;
            ++i;
        }
    }
    
    final void b(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.a(n - n3 / 2, n2 - n4 / 2, n3, n4, n5);
    }
    
    final void a(final a object, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        final int n10 = (n < 0) ? (-n) : 0;
        final int n11 = (n2 < 0) ? (-n2) : 0;
        final int n12 = (n + n3 > this.a) ? (n3 - (n + n3 - this.a)) : n3;
        final int n13 = (n2 + n4 > this.b) ? (n4 - (n2 + n4 - this.b)) : n4;
        final int n14 = n9 >> 24 & 0xFF;
        final int n15 = n9 >> 16 & 0xFF;
        final int n16 = n9 >> 8 & 0xFF;
        final int n17 = n9 & 0xFF;
        if (this.W == 0) {
            for (int i = n11; i < n13; ++i) {
                int n18 = (n2 + i) * this.a + (n + n10);
                final int n19 = (n6 + i * n8 / n4) * object.b + n5;
                for (int j = n10; j < n12; ++j, ++n18) {
                    final int n20 = n19 + j * n7 / n3;
                    if (object.e[n20] != 0) {
                        final int n21 = n15 * (object.e[n20] >> 16 & 0xFF) >> 8;
                        final int n22 = n16 * (object.e[n20] >> 8 & 0xFF) >> 8;
                        final int n23 = n17 * (object.e[n20] & 0xFF) >> 8;
                        int[] array;
                        int n24;
                        int a;
                        if (this.V == 0) {
                            array = this.Q;
                            n24 = n18;
                            a = (0xFF000000 | n21 << 16 | n22 << 8 | n23);
                        }
                        else {
                            array = this.Q;
                            n24 = n18;
                            a = a(this.V, this.Q[n18], n21 << 16 | n22 << 8 | n23, n14);
                        }
                        array[n24] = a;
                    }
                }
            }
            return;
        }
        if (this.W == 1) {
            for (int k = n11; k < n13; ++k) {
                int n25 = (n2 + k) * this.a + (n + n10);
                final int n26 = (n6 + k * n8 / n4) * object.b + n5;
                for (int l = n10; l < n12; ++l, ++n25) {
                    final int n27;
                    if ((n27 = n14 * (object.e[n26 + l * n7 / n3] & 0xFF) >> 8) != 0) {
                        this.Q[n25] = a(this.V, this.Q[n25], n9, n27);
                    }
                }
            }
            return;
        }
        if (this.W == 2) {
            for (int n28 = n11; n28 < n13; ++n28) {
                int n29 = (n2 + n28) * this.a + (n + n10);
                final int n30 = (n6 + n28 * n8 / n4) * object.b + n5;
                for (int n31 = n10; n31 < n12; ++n31, ++n29) {
                    final int n32 = n30 + n31 * n7 / n3;
                    if (object.e[n32] != -16777216) {
                        final int n33 = object.e[n32] >> 16 & 0xFF;
                        final int n34 = object.e[n32] >> 8 & 0xFF;
                        final int n35 = object.e[n32] & 0xFF;
                        int[] array2;
                        int n36;
                        int n37;
                        int n38;
                        if (n33 == n34 && n34 == n35) {
                            array2 = this.Q;
                            n36 = n29;
                            n37 = (0xFF000000 | n15 * n33 >> 8 << 16 | n16 * n34 >> 8 << 8);
                            n38 = n17 * n35 >> 8;
                        }
                        else {
                            array2 = this.Q;
                            n36 = n29;
                            n37 = (0xFF000000 | n33 << 16 | n34 << 8);
                            n38 = n35;
                        }
                        array2[n36] = (n37 | n38);
                    }
                }
            }
        }
    }
    
    final void b(final a object, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        this.a(object, n - n3 / 2, n2 - n4 / 2, n3, n4, n5, n6, n7, n8, n9);
    }
    
    final void b(final int n) {
        this.X = 0;
        this.Y = 0;
        this.ab = 1000 / n;
        this.ac = System.currentTimeMillis();
        this.ad = this.ac;
        this.ae = this.ac + this.ab;
        this.af = this.ac;
        this.Z = 0;
        this.aa = 0;
    }
    
    final void d() {
        ++this.X;
        ++this.ag;
        int a = 0;
        try {
            this.ac = System.currentTimeMillis();
            a = a(a = (int)(this.ae - this.ac), 10, this.ab);
            if (this.R[0] == 0) {
                a += 200;
            }
            Thread.sleep(a);
            this.Z += a;
        }
        catch (InterruptedException ex) {}
        this.ae += this.ab;
        if (this.ac + a >= this.af || this.ac < this.ad) {
            this.aa = this.aa / 2 + this.Z / 2;
            this.Z = 0;
            this.Y = this.X;
            this.X = 0;
            this.ae = this.ac + this.ab;
            this.af = this.ac + 1000L;
        }
        this.ad = this.ac;
    }
    
    final void a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
        this.ah.a(n3, n4, n5, n6);
        this.ah.b(n7, n8, n9, n10);
        this.ah.a(n, n2, String.valueOf(this.Y) + "fps");
        if (this.K == 1) {
            this.ah.a(n + 48, n2, String.valueOf(this.aa) + "sl", 255, 255, 255, 255, 0, 0, 0, 255);
        }
    }
    
    final void e() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }
    
    final void f() {
        this.aj = (!this.al && this.am);
        this.ak = (this.al && !this.am);
        this.an = (!this.ap && this.aq);
        this.ao = (this.ap && !this.aq);
        this.al = this.am;
        this.ap = this.aq;
        this.ar = !(this.ak | this.al | this.ao | this.ap);
        this.as = (this.ak ? 1 : (this.ao ? -1 : 0));
        this.av = this.at;
        this.aw = this.au;
        this.at = this.ax;
        this.au = this.ay;
        if (this.T != this.U) {
            this.Q = null;
        }
    }
    
    final boolean b(final int n, final int n2, final int n3, final int n4) {
        return this.at >= n && n + n3 >= this.at && this.au >= n2 && n2 + n4 >= this.au;
    }
    
    final boolean c(final int n, final int n2, final int n3, final int n4) {
        return this.b(n - n3 / 2, n2 - n4 / 2, n3, n4);
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            this.am = true;
        }
        if ((mouseEvent.getModifiers() & 0x4) != 0x0) {
            this.aq = true;
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if ((mouseEvent.getModifiers() & 0x10) != 0x0) {
            this.am = false;
        }
        if ((mouseEvent.getModifiers() & 0x4) != 0x0) {
            this.aq = false;
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        this.ax = point.x;
        this.ay = point.y;
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        this.ax = point.x;
        this.ay = point.y;
    }
    
    final void g() {
        this.addKeyListener(this);
        for (int i = 0; i < 256; ++i) {
            this.az[i] = false;
            this.aA[i] = false;
            this.aB[i] = false;
        }
        if (this.K == 1) {
            this.requestFocus();
        }
    }
    
    final void h() {
        for (int i = 0; i < 256; ++i) {
            this.az[i] = this.aA[i];
            this.aA[i] = false;
        }
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        final int keyCode;
        if ((keyCode = keyEvent.getKeyCode()) >= 0 && keyCode < 256) {
            this.aB[keyCode] = true;
            if (keyCode >= 37 && keyCode <= 40) {
                this.aA[keyCode] = true;
            }
        }
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        final int keyCode;
        if ((keyCode = keyEvent.getKeyCode()) >= 0 && keyCode < 256) {
            this.aB[keyCode] = false;
        }
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
        final char keyChar;
        if ((keyChar = keyEvent.getKeyChar()) < '\u0100') {
            this.aA[keyChar] = true;
            if (keyChar >= 'A' && keyChar <= 'Z') {
                this.aA[keyChar + ' '] = true;
            }
            if (keyChar >= 'a' && keyChar <= 'z') {
                this.aA[keyChar - ' '] = true;
            }
        }
    }
    
    final String[] a(final String s, final String s2, final byte[] array, final int n) {
        final String[] array2 = new String[100];
        for (int i = 0; i < 100; ++i) {
            array2[i] = "";
        }
        try {
            final String s3 = new String(this.aF);
            StringBuffer sb;
            String s4;
            if (array != null) {
                sb = new StringBuffer(String.valueOf(new String(this.aI))).append(s).append(new String(this.aD)).append(new String(this.aE)).append(s3).append(new String(this.aH)).append(new String(this.aG)).append(new String(this.aH)).append(new String(this.aK)).append(new String(this.aL)).append(array.length).append(new String(this.aH));
                s4 = new String(this.aH);
            }
            else if (s2.length() > 0) {
                sb = new StringBuffer(String.valueOf(new String(this.aI))).append(s).append(new String(this.aD)).append(new String(this.aE)).append(s3).append(new String(this.aH)).append(new String(this.aG)).append(new String(this.aH)).append(new String(this.aJ)).append(new String(this.aL)).append(s2.length()).append(new String(this.aH)).append(new String(this.aH));
                s4 = s2;
            }
            else {
                sb = new StringBuffer(String.valueOf(new String(this.aC))).append(s).append(new String(this.aD)).append(new String(this.aE)).append(s3).append(new String(this.aH));
                s4 = new String(this.aG);
            }
            final String string = sb.append(s4).toString();
            final Socket socket;
            final OutputStream outputStream = (socket = new Socket(s3, 80)).getOutputStream();
            for (int j = 0; j < string.length(); ++j) {
                outputStream.write(string.charAt(j));
            }
            if (array != null) {
                outputStream.write(array);
            }
            outputStream.write(this.aH);
            outputStream.write(this.aH);
            outputStream.flush();
            if (n == 1) {
                final InputStream inputStream = socket.getInputStream();
                int n2 = 0;
                int read;
                while ((read = inputStream.read()) != -1) {
                    if (read == 61) {
                        int read2;
                        while ((read2 = inputStream.read()) != -1 && read2 != 10) {
                            final String[] array3 = array2;
                            final int n3 = n2;
                            array3[n3] = String.valueOf(array3[n3]) + (char)read2;
                        }
                        ++n2;
                    }
                }
                inputStream.close();
            }
            outputStream.close();
            socket.close();
        }
        catch (IOException ex) {
            array2[0] = "error";
            array2[1] = "2";
            ex.printStackTrace();
        }
        return array2;
    }
    
    final void a(final String s) {
        final int n = 210000;
        this.aO = 0;
        this.aN = new byte[n];
        try {
            final BufferedInputStream bufferedInputStream = new BufferedInputStream(new URL(s).openStream());
            int read;
            while ((read = bufferedInputStream.read()) != -1) {
                if (this.aO >= n) {
                    this.aO = 0;
                    break;
                }
                this.aN[this.aO++] = (byte)read;
            }
            bufferedInputStream.close();
        }
        catch (IOException ex) {
            this.aO = 0;
            System.out.println(ex);
        }
    }
    
    final void a(final byte[] array, final int n) {
        this.aQ = 0;
        this.aP = new byte[210000];
        try {
            final Inflater inflater;
            (inflater = new Inflater()).setInput(array, 0, n);
            this.aQ = inflater.inflate(this.aP);
            inflater.end();
        }
        catch (Exception ex) {
            this.aQ = 0;
            System.out.println(ex);
        }
    }
    
    static int a(final int n, final int n2) {
        if (n > n2) {
            return n;
        }
        return n2;
    }
    
    static int b(final int n, final int n2) {
        if (n < n2) {
            return n;
        }
        return n2;
    }
    
    static int a(final int n, final int n2, final int n3) {
        if (n < n2) {
            return n2;
        }
        if (n > n3) {
            return n3;
        }
        return n;
    }
    
    static float a(final float n, final float n2, final float n3) {
        if (n < n2) {
            return n2;
        }
        if (n > n3) {
            return n3;
        }
        return n;
    }
    
    static int b(final int n, final int n2, final int n3) {
        if (n < n2) {
            return n3;
        }
        if (n > n3) {
            return n2;
        }
        return n;
    }
    
    public bit() {
        this.a = 512;
        this.b = 448;
        this.c = new int[49152];
        this.d = new int[49152];
        this.e = new int[100100];
        this.f = new int[100100];
        this.g = new String[1000];
        this.h = new Object() {
            private final bit f;
            Image a;
            int b;
            int c;
            int d;
            int[] e;
            
            final void a(final int b, final int c) {
                this.b = b;
                this.c = c;
                this.e = new int[this.b * this.c];
            }
            
            final void a(final String s) {
                final Image image = this.f.getImage(this.f.getCodeBase(), s);
                final MediaTracker mediaTracker;
                (mediaTracker = new MediaTracker(new Component() {})).addImage(image, 0);
                try {
                    mediaTracker.waitForAll();
                }
                catch (Exception ex) {}
                this.b = image.getWidth(null);
                this.c = image.getHeight(null);
                this.e = new int[this.b * this.c];
                final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.b, this.c, this.e, 0, this.b);
                try {
                    pixelGrabber.grabPixels();
                }
                catch (Exception ex2) {}
                this.f.S = this.f.getDocumentBase().toString();
            }
            
            final void b(final String s) {
                if (this.a == null) {
                    this.a(this.f.a, 16);
                    this.a = this.f.createImage(this.b, this.c);
                }
                final Graphics graphics;
                (graphics = this.a.getGraphics()).setColor(Color.white);
                graphics.fillRect(0, 0, this.b, this.c);
                graphics.setColor(Color.black);
                graphics.drawString(s, 0, 12);
                final PixelGrabber pixelGrabber = new PixelGrabber(this.a, 0, 0, this.b, this.c, this.e, 0, this.b);
                try {
                    pixelGrabber.grabPixels();
                }
                catch (Exception ex) {}
                for (int i = 0; i < this.b * this.c; ++i) {
                    int[] array;
                    int n;
                    int n2;
                    if (this.e[i] == -16777216) {
                        array = this.e;
                        n = i;
                        n2 = -1;
                    }
                    else {
                        array = this.e;
                        n = i;
                        n2 = 0;
                    }
                    array[n] = n2;
                }
                int n3 = 0;
                this.d = 0;
                int b = this.b;
                for (int j = 0; j < this.c; ++j) {
                    for (int k = 0; k < this.b; ++k, ++n3) {
                        if (this.e[n3] == -1 && this.d <= k) {
                            this.d = k + 1;
                        }
                        if (this.e[n3] == -1 && k < b) {
                            b = k;
                        }
                    }
                }
                if (b != this.b) {
                    this.d += b;
                }
            }
            
            {
                this.f = bit;
                this.f = bit;
            }
        };
        this.i = new Object() {
            private final bit f;
            Image a;
            int b;
            int c;
            int d;
            int[] e;
            
            final void a(final int b, final int c) {
                this.b = b;
                this.c = c;
                this.e = new int[this.b * this.c];
            }
            
            final void a(final String s) {
                final Image image = this.f.getImage(this.f.getCodeBase(), s);
                final MediaTracker mediaTracker;
                (mediaTracker = new MediaTracker(new Component() {})).addImage(image, 0);
                try {
                    mediaTracker.waitForAll();
                }
                catch (Exception ex) {}
                this.b = image.getWidth(null);
                this.c = image.getHeight(null);
                this.e = new int[this.b * this.c];
                final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.b, this.c, this.e, 0, this.b);
                try {
                    pixelGrabber.grabPixels();
                }
                catch (Exception ex2) {}
                this.f.S = this.f.getDocumentBase().toString();
            }
            
            final void b(final String s) {
                if (this.a == null) {
                    this.a(this.f.a, 16);
                    this.a = this.f.createImage(this.b, this.c);
                }
                final Graphics graphics;
                (graphics = this.a.getGraphics()).setColor(Color.white);
                graphics.fillRect(0, 0, this.b, this.c);
                graphics.setColor(Color.black);
                graphics.drawString(s, 0, 12);
                final PixelGrabber pixelGrabber = new PixelGrabber(this.a, 0, 0, this.b, this.c, this.e, 0, this.b);
                try {
                    pixelGrabber.grabPixels();
                }
                catch (Exception ex) {}
                for (int i = 0; i < this.b * this.c; ++i) {
                    int[] array;
                    int n;
                    int n2;
                    if (this.e[i] == -16777216) {
                        array = this.e;
                        n = i;
                        n2 = -1;
                    }
                    else {
                        array = this.e;
                        n = i;
                        n2 = 0;
                    }
                    array[n] = n2;
                }
                int n3 = 0;
                this.d = 0;
                int b = this.b;
                for (int j = 0; j < this.c; ++j) {
                    for (int k = 0; k < this.b; ++k, ++n3) {
                        if (this.e[n3] == -1 && this.d <= k) {
                            this.d = k + 1;
                        }
                        if (this.e[n3] == -1 && k < b) {
                            b = k;
                        }
                    }
                }
                if (b != this.b) {
                    this.d += b;
                }
            }
            
            {
                this.f = bit;
                this.f = bit;
            }
        };
        this.o = 1;
        this.v = false;
        this.w = -1;
        this.J = new Object() {
            private final bit f;
            Image a;
            int b;
            int c;
            int d;
            int[] e;
            
            final void a(final int b, final int c) {
                this.b = b;
                this.c = c;
                this.e = new int[this.b * this.c];
            }
            
            final void a(final String s) {
                final Image image = this.f.getImage(this.f.getCodeBase(), s);
                final MediaTracker mediaTracker;
                (mediaTracker = new MediaTracker(new Component() {})).addImage(image, 0);
                try {
                    mediaTracker.waitForAll();
                }
                catch (Exception ex) {}
                this.b = image.getWidth(null);
                this.c = image.getHeight(null);
                this.e = new int[this.b * this.c];
                final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.b, this.c, this.e, 0, this.b);
                try {
                    pixelGrabber.grabPixels();
                }
                catch (Exception ex2) {}
                this.f.S = this.f.getDocumentBase().toString();
            }
            
            final void b(final String s) {
                if (this.a == null) {
                    this.a(this.f.a, 16);
                    this.a = this.f.createImage(this.b, this.c);
                }
                final Graphics graphics;
                (graphics = this.a.getGraphics()).setColor(Color.white);
                graphics.fillRect(0, 0, this.b, this.c);
                graphics.setColor(Color.black);
                graphics.drawString(s, 0, 12);
                final PixelGrabber pixelGrabber = new PixelGrabber(this.a, 0, 0, this.b, this.c, this.e, 0, this.b);
                try {
                    pixelGrabber.grabPixels();
                }
                catch (Exception ex) {}
                for (int i = 0; i < this.b * this.c; ++i) {
                    int[] array;
                    int n;
                    int n2;
                    if (this.e[i] == -16777216) {
                        array = this.e;
                        n = i;
                        n2 = -1;
                    }
                    else {
                        array = this.e;
                        n = i;
                        n2 = 0;
                    }
                    array[n] = n2;
                }
                int n3 = 0;
                this.d = 0;
                int b = this.b;
                for (int j = 0; j < this.c; ++j) {
                    for (int k = 0; k < this.b; ++k, ++n3) {
                        if (this.e[n3] == -1 && this.d <= k) {
                            this.d = k + 1;
                        }
                        if (this.e[n3] == -1 && k < b) {
                            b = k;
                        }
                    }
                }
                if (b != this.b) {
                    this.d += b;
                }
            }
            
            {
                this.f = bit;
                this.f = bit;
            }
        };
        this.L = new int[] { 104, 116, 116, 112, 58, 47, 47, 100, 97, 110, 45, 98, 97, 108, 108, 46, 106, 112, 47 };
        this.R = new int[3];
        this.U = 1;
        this.ah = new Object() {
            private final bit o;
            a a;
            int b;
            int c;
            int d;
            int e;
            int f;
            int g;
            int h;
            float i;
            int j;
            int k;
            int l;
            float m;
            int n;
            
            final void a(final String s, final int n, final int n2) {
                this.a.a(s);
                this.d = n;
                this.b = n;
                this.e = n2;
                this.c = n2;
                final int f = 255;
                this.h = f;
                this.g = f;
                this.f = f;
                this.i = 0.0f;
                final boolean j = false;
                this.l = (j ? 1 : 0);
                this.k = (j ? 1 : 0);
                this.j = (j ? 1 : 0);
                this.m = 0.0f;
                this.n = 0;
            }
            
            final void a(final int n, final int n2, final int n3, final int n4) {
                this.i = n4 / 255.0f;
                this.f = (int)(n * this.i);
                this.g = (int)(n2 * this.i);
                this.h = (int)(n3 * this.i);
                this.i = 1.0f - this.i;
            }
            
            final void b(final int n, final int n2, final int n3, final int n4) {
                this.m = n4 / 255.0f;
                this.j = (int)(n * this.m);
                this.k = (int)(n2 * this.m);
                this.l = (int)(n3 * this.m);
                this.m = 1.0f - this.m;
            }
            
            final void a(int n, final int n2, final String s) {
                for (int length = s.length(), i = 0; i < length; ++i) {
                    final int n3 = (s.charAt(i) - ' ') * this.d;
                    for (int n4 = n2 * this.o.a + n, j = 0; j < this.c; ++j, n4 += this.o.a - this.b) {
                        for (int n5 = 256 * (j * this.e / this.c * this.a.b + n3), n6 = 256 * this.d / this.b, k = 0; k < this.b; ++k, ++n4, n5 += n6) {
                            final int n7;
                            if ((n7 = this.a.e[n5 >> 8]) != 0) {
                                int[] array;
                                int n9;
                                int n10;
                                int n11;
                                float n12;
                                float n13;
                                if (n7 == -1) {
                                    final int n8 = this.o.Q[n4];
                                    array = this.o.Q;
                                    n9 = n4;
                                    n10 = (0xFF000000 | this.f + (int)((n8 >> 16 & 0xFF) * this.i) << 16 | this.g + (int)((n8 >> 8 & 0xFF) * this.i) << 8);
                                    n11 = this.h;
                                    n12 = (n8 & 0xFF);
                                    n13 = this.i;
                                }
                                else {
                                    if (n7 != -16777216) {
                                        continue;
                                    }
                                    final int n14 = this.o.Q[n4];
                                    array = this.o.Q;
                                    n9 = n4;
                                    n10 = (0xFF000000 | this.j + (int)((n14 >> 16 & 0xFF) * this.m) << 16 | this.k + (int)((n14 >> 8 & 0xFF) * this.m) << 8);
                                    n11 = this.l;
                                    n12 = (n14 & 0xFF);
                                    n13 = this.m;
                                }
                                array[n9] = (n10 | n11 + (int)(n12 * n13));
                            }
                        }
                    }
                    n += this.b + this.n;
                }
            }
            
            private void b(int n, int n2, final String s) {
                n -= s.length() * (this.b + this.n) / 2;
                n2 -= this.c / 2;
                this.a(n, n2, s);
            }
            
            final void a(final int n, final int n2, final String s, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
                this.a(n3, n4, n5, n6);
                this.b(n7, n8, n9, n10);
                this.a(n, n2, s);
            }
            
            final void b(final int n, final int n2, final String s, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
                this.a(n3, n4, n5, n6);
                this.b(n7, n8, n9, n10);
                this.b(n, n2, s);
            }
            
            {
                this.o = bit;
                this.o = bit;
                this.a = new Object() {
                    private final bit f;
                    Image a;
                    int b;
                    int c;
                    int d;
                    int[] e;
                    
                    final void a(final int b, final int c) {
                        this.b = b;
                        this.c = c;
                        this.e = new int[this.b * this.c];
                    }
                    
                    final void a(final String s) {
                        final Image image = this.f.getImage(this.f.getCodeBase(), s);
                        final MediaTracker mediaTracker;
                        (mediaTracker = new MediaTracker(new Component() {})).addImage(image, 0);
                        try {
                            mediaTracker.waitForAll();
                        }
                        catch (Exception ex) {}
                        this.b = image.getWidth(null);
                        this.c = image.getHeight(null);
                        this.e = new int[this.b * this.c];
                        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.b, this.c, this.e, 0, this.b);
                        try {
                            pixelGrabber.grabPixels();
                        }
                        catch (Exception ex2) {}
                        this.f.S = this.f.getDocumentBase().toString();
                    }
                    
                    final void b(final String s) {
                        if (this.a == null) {
                            this.a(this.f.a, 16);
                            this.a = this.f.createImage(this.b, this.c);
                        }
                        final Graphics graphics;
                        (graphics = this.a.getGraphics()).setColor(Color.white);
                        graphics.fillRect(0, 0, this.b, this.c);
                        graphics.setColor(Color.black);
                        graphics.drawString(s, 0, 12);
                        final PixelGrabber pixelGrabber = new PixelGrabber(this.a, 0, 0, this.b, this.c, this.e, 0, this.b);
                        try {
                            pixelGrabber.grabPixels();
                        }
                        catch (Exception ex) {}
                        for (int i = 0; i < this.b * this.c; ++i) {
                            int[] array;
                            int n;
                            int n2;
                            if (this.e[i] == -16777216) {
                                array = this.e;
                                n = i;
                                n2 = -1;
                            }
                            else {
                                array = this.e;
                                n = i;
                                n2 = 0;
                            }
                            array[n] = n2;
                        }
                        int n3 = 0;
                        this.d = 0;
                        int b = this.b;
                        for (int j = 0; j < this.c; ++j) {
                            for (int k = 0; k < this.b; ++k, ++n3) {
                                if (this.e[n3] == -1 && this.d <= k) {
                                    this.d = k + 1;
                                }
                                if (this.e[n3] == -1 && k < b) {
                                    b = k;
                                }
                            }
                        }
                        if (b != this.b) {
                            this.d += b;
                        }
                    }
                    
                    {
                        this.f = bit;
                        this.f = bit;
                    }
                };
            }
        };
        this.ai = new Object() {
            private final bit o = bit;
            a a = new Object() {
                private final bit f = bit;
                Image a;
                int b;
                int c;
                int d;
                int[] e;
                
                final void a(final int b, final int c) {
                    this.b = b;
                    this.c = c;
                    this.e = new int[this.b * this.c];
                }
                
                final void a(final String s) {
                    final Image image = this.f.getImage(this.f.getCodeBase(), s);
                    final MediaTracker mediaTracker;
                    (mediaTracker = new MediaTracker(new Component() {})).addImage(image, 0);
                    try {
                        mediaTracker.waitForAll();
                    }
                    catch (Exception ex) {}
                    this.b = image.getWidth(null);
                    this.c = image.getHeight(null);
                    this.e = new int[this.b * this.c];
                    final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.b, this.c, this.e, 0, this.b);
                    try {
                        pixelGrabber.grabPixels();
                    }
                    catch (Exception ex2) {}
                    this.f.S = this.f.getDocumentBase().toString();
                }
                
                final void b(final String s) {
                    if (this.a == null) {
                        this.a(this.f.a, 16);
                        this.a = this.f.createImage(this.b, this.c);
                    }
                    final Graphics graphics;
                    (graphics = this.a.getGraphics()).setColor(Color.white);
                    graphics.fillRect(0, 0, this.b, this.c);
                    graphics.setColor(Color.black);
                    graphics.drawString(s, 0, 12);
                    final PixelGrabber pixelGrabber = new PixelGrabber(this.a, 0, 0, this.b, this.c, this.e, 0, this.b);
                    try {
                        pixelGrabber.grabPixels();
                    }
                    catch (Exception ex) {}
                    for (int i = 0; i < this.b * this.c; ++i) {
                        int[] array;
                        int n;
                        int n2;
                        if (this.e[i] == -16777216) {
                            array = this.e;
                            n = i;
                            n2 = -1;
                        }
                        else {
                            array = this.e;
                            n = i;
                            n2 = 0;
                        }
                        array[n] = n2;
                    }
                    int n3 = 0;
                    this.d = 0;
                    int b = this.b;
                    for (int j = 0; j < this.c; ++j) {
                        for (int k = 0; k < this.b; ++k, ++n3) {
                            if (this.e[n3] == -1 && this.d <= k) {
                                this.d = k + 1;
                            }
                            if (this.e[n3] == -1 && k < b) {
                                b = k;
                            }
                        }
                    }
                    if (b != this.b) {
                        this.d += b;
                    }
                }
                
                {
                    this.f = bit;
                }
            };
            int b;
            int c;
            int d;
            int e;
            int f;
            int g;
            int h;
            float i;
            int j;
            int k;
            int l;
            float m;
            int n;
            
            final void a(final String s, final int n, final int n2) {
                this.a.a(s);
                this.d = n;
                this.b = n;
                this.e = n2;
                this.c = n2;
                final int f = 255;
                this.h = f;
                this.g = f;
                this.f = f;
                this.i = 0.0f;
                final boolean j = false;
                this.l = (j ? 1 : 0);
                this.k = (j ? 1 : 0);
                this.j = (j ? 1 : 0);
                this.m = 0.0f;
                this.n = 0;
            }
            
            final void a(final int n, final int n2, final int n3, final int n4) {
                this.i = n4 / 255.0f;
                this.f = (int)(n * this.i);
                this.g = (int)(n2 * this.i);
                this.h = (int)(n3 * this.i);
                this.i = 1.0f - this.i;
            }
            
            final void b(final int n, final int n2, final int n3, final int n4) {
                this.m = n4 / 255.0f;
                this.j = (int)(n * this.m);
                this.k = (int)(n2 * this.m);
                this.l = (int)(n3 * this.m);
                this.m = 1.0f - this.m;
            }
            
            final void a(int n, final int n2, final String s) {
                for (int length = s.length(), i = 0; i < length; ++i) {
                    final int n3 = (s.charAt(i) - ' ') * this.d;
                    for (int n4 = n2 * this.o.a + n, j = 0; j < this.c; ++j, n4 += this.o.a - this.b) {
                        for (int n5 = 256 * (j * this.e / this.c * this.a.b + n3), n6 = 256 * this.d / this.b, k = 0; k < this.b; ++k, ++n4, n5 += n6) {
                            final int n7;
                            if ((n7 = this.a.e[n5 >> 8]) != 0) {
                                int[] array;
                                int n9;
                                int n10;
                                int n11;
                                float n12;
                                float n13;
                                if (n7 == -1) {
                                    final int n8 = this.o.Q[n4];
                                    array = this.o.Q;
                                    n9 = n4;
                                    n10 = (0xFF000000 | this.f + (int)((n8 >> 16 & 0xFF) * this.i) << 16 | this.g + (int)((n8 >> 8 & 0xFF) * this.i) << 8);
                                    n11 = this.h;
                                    n12 = (n8 & 0xFF);
                                    n13 = this.i;
                                }
                                else {
                                    if (n7 != -16777216) {
                                        continue;
                                    }
                                    final int n14 = this.o.Q[n4];
                                    array = this.o.Q;
                                    n9 = n4;
                                    n10 = (0xFF000000 | this.j + (int)((n14 >> 16 & 0xFF) * this.m) << 16 | this.k + (int)((n14 >> 8 & 0xFF) * this.m) << 8);
                                    n11 = this.l;
                                    n12 = (n14 & 0xFF);
                                    n13 = this.m;
                                }
                                array[n9] = (n10 | n11 + (int)(n12 * n13));
                            }
                        }
                    }
                    n += this.b + this.n;
                }
            }
            
            private void b(int n, int n2, final String s) {
                n -= s.length() * (this.b + this.n) / 2;
                n2 -= this.c / 2;
                this.a(n, n2, s);
            }
            
            final void a(final int n, final int n2, final String s, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
                this.a(n3, n4, n5, n6);
                this.b(n7, n8, n9, n10);
                this.a(n, n2, s);
            }
            
            final void b(final int n, final int n2, final String s, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
                this.a(n3, n4, n5, n6);
                this.b(n7, n8, n9, n10);
                this.b(n, n2, s);
            }
            
            {
                this.o = bit;
            }
        };
        this.az = new boolean[256];
        this.aA = new boolean[256];
        this.aB = new boolean[256];
        this.aC = new byte[] { 71, 69, 84, 32 };
        this.aD = new byte[] { 32, 72, 84, 84, 80, 47, 49, 46, 49, 13, 10 };
        this.aE = new byte[] { 72, 111, 115, 116, 58, 32 };
        this.aF = new byte[] { 100, 97, 110, 45, 98, 97, 108, 108, 46, 106, 112 };
        this.aG = new byte[] { 67, 111, 110, 110, 101, 99, 116, 105, 111, 110, 58, 32, 99, 108, 111, 115, 101 };
        this.aH = new byte[] { 13, 10 };
        this.aI = new byte[] { 80, 79, 83, 84, 32 };
        this.aJ = new byte[] { 67, 111, 110, 116, 101, 110, 116, 45, 116, 121, 112, 101, 58, 32, 97, 112, 112, 108, 105, 99, 97, 116, 105, 111, 110, 47, 120, 45, 119, 119, 119, 45, 102, 111, 114, 109, 45, 117, 114, 108, 101, 110, 99, 111, 100, 101, 100, 13, 10 };
        this.aK = new byte[] { 67, 111, 110, 116, 101, 110, 116, 45, 116, 121, 112, 101, 58, 32, 97, 112, 112, 108, 105, 99, 97, 116, 105, 111, 110, 47, 111, 99, 116, 101, 116, 45, 115, 116, 114, 101, 97, 109, 13, 10 };
        this.aL = new byte[] { 67, 111, 110, 116, 101, 110, 116, 45, 108, 101, 110, 103, 116, 104, 58, 32 };
        this.aM = new String[] { "", "", "", "", "", "", "", "", "", "" };
    }
}
