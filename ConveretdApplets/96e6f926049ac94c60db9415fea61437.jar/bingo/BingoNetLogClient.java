// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.r;
import neat.nb;
import neat.lb;
import neat.q;
import neat.h;
import neat.kb;
import neat.system.f;
import a.fb;

public class BingoNetLogClient extends fb
{
    private static f S;
    private int T;
    private int U;
    private int V;
    private int W;
    private int X;
    private int Y;
    private int Z;
    private kb ab;
    private boolean bb;
    private int cb;
    private int db;
    private int eb;
    private int fb;
    private int gb;
    private h hb;
    private h ib;
    private boolean jb;
    private boolean kb;
    private boolean lb;
    private boolean mb;
    private boolean nb;
    private static /* synthetic */ Class ob;
    private static String[] pb;
    
    public void a(final kb kb, final boolean bb) {
        if (kb == null) {
            return;
        }
        if (this.ab != null) {
            if (this.bb) {
                this.j();
            }
            this.ab.f();
            this.ab = null;
        }
        this.cb = 0;
        this.db = 0;
        this.eb = 0;
        this.fb = 0;
        this.gb = 0;
        ++this.T;
        this.ab = kb.b();
        this.bb = bb;
        this.a(3);
    }
    
    public void i() {
        this.eb = 0;
    }
    
    public void j() {
        ++this.U;
        this.Y += this.eb;
        this.X = this.Y / this.U;
        if (this.ab != null) {
            if (!this.hb.f(this.ab)) {
                this.hb.a(this.ab.b(), neat.q.b(0));
                this.ib.a(this.ab.b(), neat.q.b(0));
            }
            final q q = (q)this.hb.g(this.ab);
            q.a(q.a() + 1);
            final q q2 = (q)this.ib.g(this.ab);
            q2.a(q2.a() + this.eb);
        }
        this.db = 0;
        this.eb = 0;
        ++this.fb;
        this.a(1);
    }
    
    public void k() {
        ++this.W;
        if (this.ab != null) {
            this.ab.f();
            this.ab = null;
        }
        this.cb = 0;
        this.db = 0;
        this.eb = 0;
        this.fb = 0;
        this.gb = 0;
    }
    
    public void a(final dd dd) {
        this.jb = true;
        this.kb = dd.h;
        this.lb = dd.j;
        this.mb = dd.g;
        this.nb = dd.t;
    }
    
    public void b(final int n) {
        super.b(n);
        this.a(0);
        if (this.ab != null) {
            this.eb += n;
            this.cb += n;
        }
    }
    
    public void d() {
        super.d();
    }
    
    protected void a(final lb lb) {
        super.a(lb);
        lb.c(BingoNetLogClient.pb[6]);
        lb.d(this.T);
        lb.c(BingoNetLogClient.pb[10]);
        lb.d(this.U);
        lb.c(BingoNetLogClient.pb[7]);
        final kb a = neat.nb.a(this.X, true);
        lb.a(a);
        a.f();
        lb.c(BingoNetLogClient.pb[14]);
        lb.d(this.V);
        lb.c(BingoNetLogClient.pb[4]);
        lb.d(this.W);
        lb.c(BingoNetLogClient.pb[17]);
        lb.d(this.Z);
        lb.c(BingoNetLogClient.pb[8]);
        if (this.ab != null) {
            lb.c(BingoNetLogClient.pb[1]);
            lb.a(this.ab);
            lb.c(BingoNetLogClient.pb[11]);
            final kb a2 = neat.nb.a(this.cb, true);
            lb.a(a2);
            a2.f();
            lb.c(BingoNetLogClient.pb[5]);
            lb.d(this.fb);
            lb.c(BingoNetLogClient.pb[13]);
            final kb a3 = neat.nb.a(this.eb, true);
            lb.a(a3);
            a3.f();
            lb.c(BingoNetLogClient.pb[0]);
            lb.d(this.gb);
            lb.c(BingoNetLogClient.pb[8]);
        }
        final r a4 = this.hb.a();
        while (a4.a()) {
            final kb kb = (kb)a4.b();
            final q q = (q)this.hb.g(kb);
            if (q != null) {
                lb.c(BingoNetLogClient.pb[16]);
                lb.a(kb);
                lb.c(BingoNetLogClient.pb[15]);
                lb.d(q.a());
                lb.c("]");
            }
            final q q2 = (q)this.ib.g(kb);
            if (q2 != null) {
                lb.c(BingoNetLogClient.pb[16]);
                lb.a(kb);
                lb.c(BingoNetLogClient.pb[2]);
                final kb a5 = neat.nb.a(q2.a(), true);
                lb.a(a5);
                a5.f();
                lb.c("]");
            }
            lb.c("\n");
        }
        a4.f();
        if (this.jb) {
            lb.c(BingoNetLogClient.pb[3]);
            lb.a(this.kb);
            lb.c(BingoNetLogClient.pb[12]);
            lb.a(this.lb);
            lb.c(BingoNetLogClient.pb[9]);
            lb.a(this.mb);
            lb.c(BingoNetLogClient.pb[18]);
            lb.a(this.nb);
            lb.c(BingoNetLogClient.pb[8]);
        }
    }
    
    public static BingoNetLogClient l() {
        return (BingoNetLogClient)BingoNetLogClient.S.a();
    }
    
    public void f() {
        BingoNetLogClient.S.a(this);
    }
    
    public void g() {
        super.g();
        this.T = 0;
        this.U = 0;
        this.V = 0;
        this.W = 0;
        this.X = 0;
        this.Y = 0;
        this.Z = 0;
        this.cb = 0;
        this.db = 0;
        this.eb = 0;
        this.fb = 0;
        this.gb = 0;
        this.hb = neat.h.e();
        this.ib = neat.h.e();
        this.jb = false;
    }
    
    public void h() {
        this.hb.c();
        this.hb.f();
        this.hb = null;
        this.ib.c();
        this.ib.f();
        this.ib = null;
        if (this.ab != null) {
            this.ab.f();
            this.ab = null;
        }
        super.h();
    }
    
    static /* synthetic */ Class b(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public BingoNetLogClient() {
        this.ab = null;
        this.hb = null;
        this.ib = null;
    }
    
    static {
        final String[] pb = new String[20];
        final int n = 0;
        final char[] charArray = "\u0015O(\u0003\\u".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = 'H';
                            break;
                        }
                        case 1: {
                            c2 = '\u0014';
                            break;
                        }
                        case 2: {
                            c2 = 'I';
                            break;
                        }
                        case 3: {
                            c2 = 'p';
                            break;
                        }
                        default: {
                            c2 = '?';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        pb[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "\u0013u:\u0015Mu".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = 'H';
                            break;
                        }
                        case 1: {
                            c4 = '\u0014';
                            break;
                        }
                        case 2: {
                            c4 = 'I';
                            break;
                        }
                        case 3: {
                            c4 = 'p';
                            break;
                        }
                        default: {
                            c4 = '?';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        pb[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "0g=M".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = 'H';
                            break;
                        }
                        case 1: {
                            c6 = '\u0014';
                            break;
                        }
                        case 2: {
                            c6 = 'I';
                            break;
                        }
                        case 3: {
                            c6 = 'p';
                            break;
                        }
                        default: {
                            c6 = '?';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        pb[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "\u0013g:\u0015\u0002".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0446: {
                if (n14 > 1) {
                    break Label_0446;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = 'H';
                            break;
                        }
                        case 1: {
                            c8 = '\u0014';
                            break;
                        }
                        case 2: {
                            c8 = 'I';
                            break;
                        }
                        case 3: {
                            c8 = 'p';
                            break;
                        }
                        default: {
                            c8 = '?';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        pb[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "\u0015O.\u0011R-%t".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0562: {
                if (n18 > 1) {
                    break Label_0562;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = 'H';
                            break;
                        }
                        case 1: {
                            c10 = '\u0014';
                            break;
                        }
                        case 2: {
                            c10 = 'I';
                            break;
                        }
                        case 3: {
                            c10 = 'p';
                            break;
                        }
                        default: {
                            c10 = '?';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        pb[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "\u0015O(\u001cZ>q%\u0013\u0002".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0678: {
                if (n22 > 1) {
                    break Label_0678;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = 'H';
                            break;
                        }
                        case 1: {
                            c12 = '\u0014';
                            break;
                        }
                        case 2: {
                            c12 = 'I';
                            break;
                        }
                        case 3: {
                            c12 = 'p';
                            break;
                        }
                        default: {
                            c12 = '?';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        pb[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "\u0013s(\u001dZu".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0798: {
                if (n26 > 1) {
                    break Label_0798;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = 'H';
                            break;
                        }
                        case 1: {
                            c14 = '\u0014';
                            break;
                        }
                        case 2: {
                            c14 = 'I';
                            break;
                        }
                        case 3: {
                            c14 = 'p';
                            break;
                        }
                        default: {
                            c14 = '?';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        pb[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "\u0015O%\u0015I-x(\u0004\u0002".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0918: {
                if (n30 > 1) {
                    break Label_0918;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = 'H';
                            break;
                        }
                        case 1: {
                            c16 = '\u0014';
                            break;
                        }
                        case 2: {
                            c16 = 'I';
                            break;
                        }
                        case 3: {
                            c16 = 'p';
                            break;
                        }
                        default: {
                            c16 = '?';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 > n32) {
                continue;
            }
            break;
        }
        pb[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "\u0015\u001e".toCharArray();
        int length9;
        int n35;
        final int n34 = n35 = (length9 = charArray9.length);
        int n36 = 0;
        while (true) {
            Label_1038: {
                if (n34 > 1) {
                    break Label_1038;
                }
                length9 = (n35 = n36);
                do {
                    final char c17 = charArray9[n35];
                    char c18 = '\0';
                    switch (n36 % 5) {
                        case 0: {
                            c18 = 'H';
                            break;
                        }
                        case 1: {
                            c18 = '\u0014';
                            break;
                        }
                        case 2: {
                            c18 = 'I';
                            break;
                        }
                        case 3: {
                            c18 = 'p';
                            break;
                        }
                        default: {
                            c18 = '?';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n36;
                } while (n34 == 0);
            }
            if (n34 > n36) {
                continue;
            }
            break;
        }
        pb[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "\u0015O:\u0016Lu".toCharArray();
        int length10;
        int n39;
        final int n38 = n39 = (length10 = charArray10.length);
        int n40 = 0;
        while (true) {
            Label_1158: {
                if (n38 > 1) {
                    break Label_1158;
                }
                length10 = (n39 = n40);
                do {
                    final char c19 = charArray10[n39];
                    char c20 = '\0';
                    switch (n40 % 5) {
                        case 0: {
                            c20 = 'H';
                            break;
                        }
                        case 1: {
                            c20 = '\u0014';
                            break;
                        }
                        case 2: {
                            c20 = 'I';
                            break;
                        }
                        case 3: {
                            c20 = 'p';
                            break;
                        }
                        default: {
                            c20 = '?';
                            break;
                        }
                    }
                    charArray10[length10] = (char)(c19 ^ c20);
                    ++n40;
                } while (n38 == 0);
            }
            if (n38 > n40) {
                continue;
            }
            break;
        }
        pb[n37] = new String(charArray10).intern();
        final int n41 = 10;
        final char[] charArray11 = "\u0015O%\u0015I-x*M".toCharArray();
        int length11;
        int n43;
        final int n42 = n43 = (length11 = charArray11.length);
        int n44 = 0;
        while (true) {
            Label_1278: {
                if (n42 > 1) {
                    break Label_1278;
                }
                length11 = (n43 = n44);
                do {
                    final char c21 = charArray11[n43];
                    char c22 = '\0';
                    switch (n44 % 5) {
                        case 0: {
                            c22 = 'H';
                            break;
                        }
                        case 1: {
                            c22 = '\u0014';
                            break;
                        }
                        case 2: {
                            c22 = 'I';
                            break;
                        }
                        case 3: {
                            c22 = 'p';
                            break;
                        }
                        default: {
                            c22 = '?';
                            break;
                        }
                    }
                    charArray11[length11] = (char)(c21 ^ c22);
                    ++n44;
                } while (n42 == 0);
            }
            if (n42 > n44) {
                continue;
            }
            break;
        }
        pb[n41] = new String(charArray11).intern();
        final int n45 = 11;
        final char[] charArray12 = "\u0015O(\u0004Ku".toCharArray();
        int length12;
        int n47;
        final int n46 = n47 = (length12 = charArray12.length);
        int n48 = 0;
        while (true) {
            Label_1398: {
                if (n46 > 1) {
                    break Label_1398;
                }
                length12 = (n47 = n48);
                do {
                    final char c23 = charArray12[n47];
                    char c24 = '\0';
                    switch (n48 % 5) {
                        case 0: {
                            c24 = 'H';
                            break;
                        }
                        case 1: {
                            c24 = '\u0014';
                            break;
                        }
                        case 2: {
                            c24 = 'I';
                            break;
                        }
                        case 3: {
                            c24 = 'p';
                            break;
                        }
                        default: {
                            c24 = '?';
                            break;
                        }
                    }
                    charArray12[length12] = (char)(c23 ^ c24);
                    ++n48;
                } while (n46 == 0);
            }
            if (n46 > n48) {
                continue;
            }
            break;
        }
        pb[n45] = new String(charArray12).intern();
        final int n49 = 12;
        final char[] charArray13 = "\u0015O:\u001dJu".toCharArray();
        int length13;
        int n51;
        final int n50 = n51 = (length13 = charArray13.length);
        int n52 = 0;
        while (true) {
            Label_1518: {
                if (n50 > 1) {
                    break Label_1518;
                }
                length13 = (n51 = n52);
                do {
                    final char c25 = charArray13[n51];
                    char c26 = '\0';
                    switch (n52 % 5) {
                        case 0: {
                            c26 = 'H';
                            break;
                        }
                        case 1: {
                            c26 = '\u0014';
                            break;
                        }
                        case 2: {
                            c26 = 'I';
                            break;
                        }
                        case 3: {
                            c26 = 'p';
                            break;
                        }
                        default: {
                            c26 = '?';
                            break;
                        }
                    }
                    charArray13[length13] = (char)(c25 ^ c26);
                    ++n52;
                } while (n50 == 0);
            }
            if (n50 > n52) {
                continue;
            }
            break;
        }
        pb[n49] = new String(charArray13).intern();
        final int n53 = 13;
        final char[] charArray14 = "\u0015O(\u001cZ>q%\u0004Ku".toCharArray();
        int length14;
        int n55;
        final int n54 = n55 = (length14 = charArray14.length);
        int n56 = 0;
        while (true) {
            Label_1638: {
                if (n54 > 1) {
                    break Label_1638;
                }
                length14 = (n55 = n56);
                do {
                    final char c27 = charArray14[n55];
                    char c28 = '\0';
                    switch (n56 % 5) {
                        case 0: {
                            c28 = 'H';
                            break;
                        }
                        case 1: {
                            c28 = '\u0014';
                            break;
                        }
                        case 2: {
                            c28 = 'I';
                            break;
                        }
                        case 3: {
                            c28 = 'p';
                            break;
                        }
                        default: {
                            c28 = '?';
                            break;
                        }
                    }
                    charArray14[length14] = (char)(c27 ^ c28);
                    ++n56;
                } while (n54 == 0);
            }
            if (n54 > n56) {
                continue;
            }
            break;
        }
        pb[n53] = new String(charArray14).intern();
        final int n57 = 14;
        final char[] charArray15 = "\u0015O.\u0011R-$t".toCharArray();
        int length15;
        int n59;
        final int n58 = n59 = (length15 = charArray15.length);
        int n60 = 0;
        while (true) {
            Label_1758: {
                if (n58 > 1) {
                    break Label_1758;
                }
                length15 = (n59 = n60);
                do {
                    final char c29 = charArray15[n59];
                    char c30 = '\0';
                    switch (n60 % 5) {
                        case 0: {
                            c30 = 'H';
                            break;
                        }
                        case 1: {
                            c30 = '\u0014';
                            break;
                        }
                        case 2: {
                            c30 = 'I';
                            break;
                        }
                        case 3: {
                            c30 = 'p';
                            break;
                        }
                        default: {
                            c30 = '?';
                            break;
                        }
                    }
                    charArray15[length15] = (char)(c29 ^ c30);
                    ++n60;
                } while (n58 == 0);
            }
            if (n58 > n60) {
                continue;
            }
            break;
        }
        pb[n57] = new String(charArray15).intern();
        final int n61 = 15;
        final char[] charArray16 = "0x,\u0006Z$wt".toCharArray();
        int length16;
        int n63;
        final int n62 = n63 = (length16 = charArray16.length);
        int n64 = 0;
        while (true) {
            Label_1878: {
                if (n62 > 1) {
                    break Label_1878;
                }
                length16 = (n63 = n64);
                do {
                    final char c31 = charArray16[n63];
                    char c32 = '\0';
                    switch (n64 % 5) {
                        case 0: {
                            c32 = 'H';
                            break;
                        }
                        case 1: {
                            c32 = '\u0014';
                            break;
                        }
                        case 2: {
                            c32 = 'I';
                            break;
                        }
                        case 3: {
                            c32 = 'p';
                            break;
                        }
                        default: {
                            c32 = '?';
                            break;
                        }
                    }
                    charArray16[length16] = (char)(c31 ^ c32);
                    ++n64;
                } while (n62 == 0);
            }
            if (n62 > n64) {
                continue;
            }
            break;
        }
        pb[n61] = new String(charArray16).intern();
        final int n65 = 16;
        final char[] charArray17 = "\u0013g1".toCharArray();
        int length17;
        int n67;
        final int n66 = n67 = (length17 = charArray17.length);
        int n68 = 0;
        while (true) {
            Label_1998: {
                if (n66 > 1) {
                    break Label_1998;
                }
                length17 = (n67 = n68);
                do {
                    final char c33 = charArray17[n67];
                    char c34 = '\0';
                    switch (n68 % 5) {
                        case 0: {
                            c34 = 'H';
                            break;
                        }
                        case 1: {
                            c34 = '\u0014';
                            break;
                        }
                        case 2: {
                            c34 = 'I';
                            break;
                        }
                        case 3: {
                            c34 = 'p';
                            break;
                        }
                        default: {
                            c34 = '?';
                            break;
                        }
                    }
                    charArray17[length17] = (char)(c33 ^ c34);
                    ++n68;
                } while (n66 == 0);
            }
            if (n66 > n68) {
                continue;
            }
            break;
        }
        pb[n65] = new String(charArray17).intern();
        final int n69 = 17;
        final char[] charArray18 = "\u0015O!\u0003\\u".toCharArray();
        int length18;
        int n71;
        final int n70 = n71 = (length18 = charArray18.length);
        int n72 = 0;
        while (true) {
            Label_2118: {
                if (n70 > 1) {
                    break Label_2118;
                }
                length18 = (n71 = n72);
                do {
                    final char c35 = charArray18[n71];
                    char c36 = '\0';
                    switch (n72 % 5) {
                        case 0: {
                            c36 = 'H';
                            break;
                        }
                        case 1: {
                            c36 = '\u0014';
                            break;
                        }
                        case 2: {
                            c36 = 'I';
                            break;
                        }
                        case 3: {
                            c36 = 'p';
                            break;
                        }
                        default: {
                            c36 = '?';
                            break;
                        }
                    }
                    charArray18[length18] = (char)(c35 ^ c36);
                    ++n72;
                } while (n70 == 0);
            }
            if (n70 > n72) {
                continue;
            }
            break;
        }
        pb[n69] = new String(charArray18).intern();
        final int n73 = 18;
        final char[] charArray19 = "\u0015O:\u0018Ou".toCharArray();
        int length19;
        int n75;
        final int n74 = n75 = (length19 = charArray19.length);
        int n76 = 0;
        while (true) {
            Label_2238: {
                if (n74 > 1) {
                    break Label_2238;
                }
                length19 = (n75 = n76);
                do {
                    final char c37 = charArray19[n75];
                    char c38 = '\0';
                    switch (n76 % 5) {
                        case 0: {
                            c38 = 'H';
                            break;
                        }
                        case 1: {
                            c38 = '\u0014';
                            break;
                        }
                        case 2: {
                            c38 = 'I';
                            break;
                        }
                        case 3: {
                            c38 = 'p';
                            break;
                        }
                        default: {
                            c38 = '?';
                            break;
                        }
                    }
                    charArray19[length19] = (char)(c37 ^ c38);
                    ++n76;
                } while (n74 == 0);
            }
            if (n74 > n76) {
                continue;
            }
            break;
        }
        pb[n73] = new String(charArray19).intern();
        final int n77 = 19;
        final char[] charArray20 = "*}'\u0017PfV \u001eX'Z,\u0004s's\n\u001cV-z=".toCharArray();
        int length20;
        int n79;
        final int n78 = n79 = (length20 = charArray20.length);
        int n80 = 0;
        while (true) {
            Label_2358: {
                if (n78 > 1) {
                    break Label_2358;
                }
                length20 = (n79 = n80);
                do {
                    final char c39 = charArray20[n79];
                    char c40 = '\0';
                    switch (n80 % 5) {
                        case 0: {
                            c40 = 'H';
                            break;
                        }
                        case 1: {
                            c40 = '\u0014';
                            break;
                        }
                        case 2: {
                            c40 = 'I';
                            break;
                        }
                        case 3: {
                            c40 = 'p';
                            break;
                        }
                        default: {
                            c40 = '?';
                            break;
                        }
                    }
                    charArray20[length20] = (char)(c39 ^ c40);
                    ++n80;
                } while (n78 == 0);
            }
            if (n78 <= n80) {
                pb[n77] = new String(charArray20).intern();
                BingoNetLogClient.pb = pb;
                BingoNetLogClient.S = new f((BingoNetLogClient.ob != null) ? BingoNetLogClient.ob : (BingoNetLogClient.ob = b(BingoNetLogClient.pb[19])));
                return;
            }
            continue;
        }
    }
}
