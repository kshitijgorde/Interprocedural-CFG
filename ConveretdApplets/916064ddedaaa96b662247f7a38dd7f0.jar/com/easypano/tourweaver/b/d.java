// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import com.easypano.tourweaver.f.h;
import java.awt.Shape;
import java.awt.Rectangle;
import com.easypano.tourweaver.f.e;

public class d extends b implements e
{
    public static final String sb = "0";
    public static final String tb = "1";
    int ub;
    int vb;
    int wb;
    int xb;
    Rectangle yb;
    Rectangle zb;
    Rectangle Ab;
    Shape Bb;
    double Cb;
    double Db;
    double Eb;
    double Fb;
    String Gb;
    int Hb;
    String Ib;
    double Jb;
    int Kb;
    int Lb;
    int Mb;
    int Nb;
    int Ob;
    int Pb;
    int Qb;
    int Rb;
    double Sb;
    double Tb;
    double Ub;
    private static String[] Vb;
    
    public void c(final int ob, final int pb) {
        this.Kb = (int)(ob * this.Sb + 0.5);
        this.Lb = (int)(pb * this.Tb + 0.5);
        this.Ob = ob;
        this.Pb = pb;
    }
    
    public void d(final int qb, final int rb) {
        this.Mb = (int)(qb * this.Sb + 0.5);
        this.Nb = (int)(rb * this.Tb + 0.5);
        this.Qb = qb;
        this.Rb = rb;
    }
    
    public d() {
        this.ub = 0;
        this.vb = 0;
        this.wb = -1;
        this.xb = -1;
        this.yb = new Rectangle();
        this.Ab = new Rectangle();
        this.Cb = 0.0;
        this.Db = 0.0;
        this.Eb = 0.0;
        this.Fb = 0.0;
        this.Hb = 0;
        this.Ib = "";
        this.Jb = 0.0;
        this.Kb = 0;
        this.Lb = 0;
        this.Mb = 0;
        this.Nb = 0;
        this.Ob = 0;
        this.Pb = 0;
        this.Qb = 0;
        this.Rb = 0;
        this.Sb = 1.0;
        this.Tb = 1.0;
        this.Ub = 0.0;
        super.fb = d.Vb[2];
    }
    
    public boolean e() {
        return true;
    }
    
    public double m() {
        return this.Jb;
    }
    
    public String f() {
        return this.Gb;
    }
    
    public void a(final double sb, final double tb) {
        this.Sb = sb;
        this.Tb = tb;
        this.wb = (int)(sb * this.ub + 0.5);
        this.xb = (int)(tb * this.vb + 0.5);
        this.Kb = (int)(this.Ob * sb + 0.5);
        this.Lb = (int)(this.Pb * tb + 0.5);
        this.Mb = (int)(this.Qb * sb + 0.5);
        this.Nb = (int)(this.Rb * tb + 0.5);
        this.zb.setBounds((int)(this.Ab.x * sb + 0.5), (int)(this.Ab.y * tb + 0.5), (int)(this.Ab.width * sb + 0.5), (int)(this.Ab.height * tb + 0.5));
        this.setBounds(this.wb - super.ob, this.xb - super.pb, this.getBounds().width, this.getBounds().height);
        this.Bb = null;
    }
    
    public double n() {
        return this.Fb;
    }
    
    public double o() {
        return this.Cb;
    }
    
    public void a(final double fb) {
        this.Fb = fb;
    }
    
    public int g() {
        return this.Hb;
    }
    
    public Shape h() {
        final boolean u = com.easypano.tourweaver.b.f.u;
        final Shape bb = this.Bb;
        if (!u) {
            d d = null;
            Label_0075: {
                Label_0074: {
                    if (bb == null) {
                        final boolean equals = this.Ib.equals("0");
                        if (!u) {
                            if (equals) {
                                this.Bb = this.zb;
                                if (!u) {
                                    break Label_0074;
                                }
                            }
                            d = this;
                            if (u) {
                                break Label_0075;
                            }
                            this.Ib.equals("1");
                        }
                        if (equals) {
                            this.Bb = new kb(this.zb);
                        }
                    }
                }
                d = this;
            }
            final Shape bb2 = d.Bb;
        }
        return bb;
    }
    
    public void a(final Shape bb) {
        this.Bb = bb;
    }
    
    public void a(final int wb, final int xb) {
        this.wb = wb;
        this.xb = xb;
    }
    
    public int getX() {
        return this.ub;
    }
    
    public int getY() {
        return this.vb;
    }
    
    public int p() {
        return this.wb;
    }
    
    public int q() {
        return this.xb;
    }
    
    public Rectangle r() {
        return this.Ab;
    }
    
    public Rectangle s() {
        return this.zb;
    }
    
    public String t() {
        return d.Vb[0] + this.Gb + d.Vb[1] + this.Cb + d.Vb[1] + this.Db + d.Vb[1] + this.Eb + ")";
    }
    
    public double u() {
        return this.Ub;
    }
    
    public void addAttributes(final String s, final String ib) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        boolean b13;
        boolean equals;
        boolean b12;
        boolean b11;
        boolean b10;
        boolean b9;
        boolean b8;
        boolean b7;
        boolean b6;
        boolean b5;
        boolean b4;
        boolean b3;
        boolean b2;
        final boolean b = b2 = (b3 = (b4 = (b5 = (b6 = (b7 = (b8 = (b9 = (b10 = (b11 = (b12 = (equals = (b13 = s.equals(d.Vb[9])))))))))))));
        if (!u) {
            if (b) {
                this.setName(ib);
                if (!u) {
                    return;
                }
            }
            final boolean b14;
            b2 = (b14 = (b3 = (b4 = (b5 = (b6 = (b7 = (b8 = (b9 = (b10 = (b11 = (b12 = (equals = (b13 = s.equals(d.Vb[13]))))))))))))));
        }
        if (!u) {
            if (b) {
                super.a = com.easypano.tourweaver.a.e.b(ib);
                if (!u) {
                    return;
                }
            }
            b3 = (b2 = (b4 = (b5 = (b6 = (b7 = (b8 = (b9 = (b10 = (b11 = (b12 = (equals = (b13 = s.equals(d.Vb[14])))))))))))));
        }
        if (!u) {
            if (b2) {
                this.setBounds(this.yb = com.easypano.tourweaver.a.e.c(ib));
                this.b(this.wb - this.yb.x, this.xb - this.yb.y);
                if (!u) {
                    return;
                }
            }
            b4 = (b3 = (b5 = (b6 = (b7 = (b8 = (b9 = (b10 = (b11 = (b12 = (equals = (b13 = s.equals(d.Vb[4]))))))))))));
        }
        if (!u) {
            if (b3) {
                this.Cb = com.easypano.tourweaver.a.e.a(ib, 0.0);
                super.cb = this.t();
                if (!u) {
                    return;
                }
            }
            b5 = (b4 = (b6 = (b7 = (b8 = (b9 = (b10 = (b11 = (b12 = (equals = (b13 = s.equals(d.Vb[15])))))))))));
        }
        if (!u) {
            if (b4) {
                this.Db = com.easypano.tourweaver.a.e.a(ib, 0.0);
                super.cb = this.t();
                if (!u) {
                    return;
                }
            }
            b6 = (b5 = (b7 = (b8 = (b9 = (b10 = (b11 = (b12 = (equals = (b13 = s.equals(d.Vb[7]))))))))));
        }
        if (!u) {
            if (b5) {
                this.Eb = com.easypano.tourweaver.a.e.a(ib, 0.0);
                super.cb = this.t();
                if (!u) {
                    return;
                }
            }
            b7 = (b6 = (b8 = (b9 = (b10 = (b11 = (b12 = (equals = (b13 = s.equals(d.Vb[11])))))))));
        }
        if (!u) {
            if (b6) {
                this.Fb = com.easypano.tourweaver.a.e.a(ib, 0.0);
                this.Fb -= 180.0;
                this.Fb = com.easypano.tourweaver.a.e.a(this.Fb);
                if (!u) {
                    return;
                }
            }
            b8 = (b7 = (b9 = (b10 = (b11 = (b12 = (equals = (b13 = s.equals(d.Vb[5]))))))));
        }
        if (!u) {
            if (b7) {
                this.Ub = com.easypano.tourweaver.a.e.a(ib, 0.0);
                this.Ub = com.easypano.tourweaver.a.e.a(this.Ub);
                if (!u) {
                    return;
                }
            }
            b9 = (b8 = (b10 = (b11 = (b12 = (equals = (b13 = s.equals(d.Vb[10])))))));
        }
        if (!u) {
            if (b8) {
                this.Gb = ib;
                super.cb = this.t();
                if (!u) {
                    return;
                }
            }
            b10 = (b9 = (b11 = (b12 = (equals = (b13 = s.equals(d.Vb[3]))))));
        }
        if (!u) {
            if (b9) {
                this.zb = com.easypano.tourweaver.a.e.c(ib);
                this.Ab.setBounds(this.zb);
                if (!u) {
                    return;
                }
            }
            b11 = (b10 = (b12 = (equals = (b13 = s.equals("x")))));
        }
        if (!u) {
            if (b10) {
                this.wb = com.easypano.tourweaver.a.e.a(ib, 0);
                this.ub = this.wb;
                this.b(this.wb - this.yb.x, this.xb - this.yb.y);
                if (!u) {
                    return;
                }
            }
            b12 = (b11 = (equals = (b13 = s.equals("y"))));
        }
        if (!u) {
            if (b11) {
                this.xb = com.easypano.tourweaver.a.e.a(ib, 0);
                this.b(this.wb - this.yb.x, this.xb - this.yb.y);
                this.vb = this.xb;
                if (!u) {
                    return;
                }
            }
            equals = (b12 = (b13 = s.equals(d.Vb[8])));
        }
        if (!u) {
            if (b12) {
                this.Jb = com.easypano.tourweaver.a.e.a(ib, 0.0);
                this.Jb = com.easypano.tourweaver.a.e.a(this.Jb);
                if (!u) {
                    return;
                }
            }
            b13 = (equals = s.equals(d.Vb[12]));
        }
        if (!u) {
            if (equals) {
                this.Hb = com.easypano.tourweaver.a.e.a(ib, 0);
                this.Hb = (int)(this.Hb / 100.0 * 256.0);
                if (!u) {
                    return;
                }
            }
            b13 = s.equals(d.Vb[6]);
        }
        if (b13) {
            this.Ib = ib;
            if (!u) {
                return;
            }
        }
        super.addAttributes(s, ib);
    }
    
    public void a(final h h) {
    }
    
    public void b(final h h) {
    }
    
    static {
        final String[] vb = new String[16];
        final int n = 0;
        final char[] charArray = "\u001bkf\u0001L\u0000H`&L\rrj]|\u001cnf\u001bHH".toCharArray();
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
                            c2 = 'h';
                            break;
                        }
                        case 1: {
                            c2 = '\u001c';
                            break;
                        }
                        case 2: {
                            c2 = '\u000f';
                            break;
                        }
                        case 3: {
                            c2 = 'u';
                            break;
                        }
                        default: {
                            c2 = '/';
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
        vb[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "D<k\u001aZ\npjU".toCharArray();
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
                            c4 = 'h';
                            break;
                        }
                        case 1: {
                            c4 = '\u001c';
                            break;
                        }
                        case 2: {
                            c4 = '\u000f';
                            break;
                        }
                        case 3: {
                            c4 = 'u';
                            break;
                        }
                        default: {
                            c4 = '/';
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
        vb[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "\u001c}m".toCharArray();
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
                            c6 = 'h';
                            break;
                        }
                        case 1: {
                            c6 = '\u001c';
                            break;
                        }
                        case 2: {
                            c6 = '\u000f';
                            break;
                        }
                        case 3: {
                            c6 = 'u';
                            break;
                        }
                        default: {
                            c6 = '/';
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
        vb[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "\u001b\u007fn\u001bm\u0007ia\u0011\\".toCharArray();
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
                            c8 = 'h';
                            break;
                        }
                        case 1: {
                            c8 = '\u001c';
                            break;
                        }
                        case 2: {
                            c8 = '\u000f';
                            break;
                        }
                        case 3: {
                            c8 = 'u';
                            break;
                        }
                        default: {
                            c8 = '/';
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
        vb[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "\u0018}a".toCharArray();
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
                            c10 = 'h';
                            break;
                        }
                        case 1: {
                            c10 = '\u001c';
                            break;
                        }
                        case 2: {
                            c10 = '\u000f';
                            break;
                        }
                        case 3: {
                            c10 = 'u';
                            break;
                        }
                        default: {
                            c10 = '/';
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
        vb[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "\u000bs}\u0007J\u001bl`\u001bK8}a".toCharArray();
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
                            c12 = 'h';
                            break;
                        }
                        case 1: {
                            c12 = '\u001c';
                            break;
                        }
                        case 2: {
                            c12 = '\u000f';
                            break;
                        }
                        case 3: {
                            c12 = 'u';
                            break;
                        }
                        default: {
                            c12 = '/';
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
        vb[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "\u001btn\u0005J".toCharArray();
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
                            c14 = 'h';
                            break;
                        }
                        case 1: {
                            c14 = '\u001c';
                            break;
                        }
                        case 2: {
                            c14 = '\u000f';
                            break;
                        }
                        case 3: {
                            c14 = 'u';
                            break;
                        }
                        default: {
                            c14 = '/';
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
        vb[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "\u000esy".toCharArray();
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
                            c16 = 'h';
                            break;
                        }
                        case 1: {
                            c16 = '\u001c';
                            break;
                        }
                        case 2: {
                            c16 = '\u000f';
                            break;
                        }
                        case 3: {
                            c16 = 'u';
                            break;
                        }
                        default: {
                            c16 = '/';
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
        vb[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "\u001a}a\u0012n\u0006{c\u0010".toCharArray();
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
                            c18 = 'h';
                            break;
                        }
                        case 1: {
                            c18 = '\u001c';
                            break;
                        }
                        case 2: {
                            c18 = '\u000f';
                            break;
                        }
                        case 3: {
                            c18 = 'u';
                            break;
                        }
                        default: {
                            c18 = '/';
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
        vb[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "\u0006}b\u0010".toCharArray();
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
                            c20 = 'h';
                            break;
                        }
                        case 1: {
                            c20 = '\u001c';
                            break;
                        }
                        case 2: {
                            c20 = '\u000f';
                            break;
                        }
                        case 3: {
                            c20 = 'u';
                            break;
                        }
                        default: {
                            c20 = '/';
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
        vb[n37] = new String(charArray10).intern();
        final int n41 = 10;
        final char[] charArray11 = "\u001b\u007fj\u001bJ".toCharArray();
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
                            c22 = 'h';
                            break;
                        }
                        case 1: {
                            c22 = '\u001c';
                            break;
                        }
                        case 2: {
                            c22 = '\u000f';
                            break;
                        }
                        case 3: {
                            c22 = 'u';
                            break;
                        }
                        default: {
                            c22 = '/';
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
        vb[n41] = new String(charArray11).intern();
        final int n45 = 11;
        final char[] charArray12 = "\u0001rf\u0001n\u0006{c\u0010".toCharArray();
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
                            c24 = 'h';
                            break;
                        }
                        case 1: {
                            c24 = '\u001c';
                            break;
                        }
                        case 2: {
                            c24 = '\u000f';
                            break;
                        }
                        case 3: {
                            c24 = 'u';
                            break;
                        }
                        default: {
                            c24 = '/';
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
        vb[n45] = new String(charArray12).intern();
        final int n49 = 12;
        final char[] charArray13 = "\u001cnn\u001b\\\u0018}}\u0010A\u000by".toCharArray();
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
                            c26 = 'h';
                            break;
                        }
                        case 1: {
                            c26 = '\u001c';
                            break;
                        }
                        case 2: {
                            c26 = '\u000f';
                            break;
                        }
                        case 3: {
                            c26 = 'u';
                            break;
                        }
                        default: {
                            c26 = '/';
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
        vb[n49] = new String(charArray13).intern();
        final int n53 = 13;
        final char[] charArray14 = "\ns}\u0011J\u001a_`\u0019@\u001a".toCharArray();
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
                            c28 = 'h';
                            break;
                        }
                        case 1: {
                            c28 = '\u001c';
                            break;
                        }
                        case 2: {
                            c28 = '\u000f';
                            break;
                        }
                        case 3: {
                            c28 = 'u';
                            break;
                        }
                        default: {
                            c28 = '/';
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
        vb[n53] = new String(charArray14).intern();
        final int n57 = 14;
        final char[] charArray15 = "\u0000s{7@\u001drk\u0006".toCharArray();
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
                            c30 = 'h';
                            break;
                        }
                        case 1: {
                            c30 = '\u001c';
                            break;
                        }
                        case 2: {
                            c30 = '\u000f';
                            break;
                        }
                        case 3: {
                            c30 = 'u';
                            break;
                        }
                        default: {
                            c30 = '/';
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
        vb[n57] = new String(charArray15).intern();
        final int n61 = 15;
        final char[] charArray16 = "\u001cuc\u0001".toCharArray();
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
                            c32 = 'h';
                            break;
                        }
                        case 1: {
                            c32 = '\u001c';
                            break;
                        }
                        case 2: {
                            c32 = '\u000f';
                            break;
                        }
                        case 3: {
                            c32 = 'u';
                            break;
                        }
                        default: {
                            c32 = '/';
                            break;
                        }
                    }
                    charArray16[length16] = (char)(c31 ^ c32);
                    ++n64;
                } while (n62 == 0);
            }
            if (n62 <= n64) {
                vb[n61] = new String(charArray16).intern();
                d.Vb = vb;
                return;
            }
            continue;
        }
    }
}
