import java.io.PrintStream;
import java.lang.reflect.Constructor;

// 
// Decompiled by Procyon v0.5.30
// 

public class v extends w
{
    t a;
    boolean b;
    bn c;
    int d;
    int e;
    as[] f;
    int g;
    int h;
    int i;
    c[] j;
    aq k;
    
    v(final byte[] a, final t a2, final int d, final int e) {
        this.k = new aq();
        super.a = a;
        this.a = a2;
        super.f = a2.h;
        this.b = (super.f < 7);
        this.c = ((super.f < 7) ? d.f : d.e);
        this.d = d;
        this.e = e;
        this.h = 0;
        this.i = 8;
        this.j = new c[this.i];
        this.g = 0;
        this.f = null;
    }
    
    void a() {
        this.a = null;
        final byte[] a = new byte[this.e];
        System.arraycopy(super.a, this.d, a, 0, this.e);
        this.d = 0;
        super.a = a;
    }
    
    void b() {
        if (this.h == this.i) {
            int i = 0;
            Label_0043: {
                if (this.i < 512) {
                    i = this.i << 1;
                    if (!c.l) {
                        break Label_0043;
                    }
                }
                i = this.i + 512;
            }
            final c[] j = new c[i];
            System.arraycopy(this.j, 0, j, 0, this.i);
            this.j = j;
            this.i = i;
        }
    }
    
    c a(final c c, final c[] array, final c c2, c b, int b2, int n, final int n2) {
        final boolean l = c.l;
        final c c3 = b;
        b2 += this.d;
        n += this.d;
        while (true) {
            v v;
            int n3;
            int e;
            c[] j;
            int h;
            c c4;
            c[] i;
            int h2;
            c c5;
            c[] k;
            int h3;
            c[] m;
            int h4;
            c[] j2;
            int h5;
            c[] j3;
            int h6;
            c[] j4;
            int h7;
            c c6;
            c[] j5;
            int h8;
            c[] j6;
            int h9;
            c[] j7;
            int h10;
            c c7;
            c[] j8;
            int h11;
            c[] j9;
            int h12;
            c c8;
            c[] j10;
            int h13;
            c c9;
            String i2;
            c[] j11;
            int h14;
            c c10;
            c[] j12;
            int h15;
            c c11;
            c[] j13;
            int h16;
            c c12;
            String i3;
            c[] j14;
            int h17;
            PrintStream out;
            c[] j15;
            int h18;
            c[] j16;
            int h19;
            c c13;
            c[] j17;
            int h20;
            c[] j18;
            int h21;
            c c14;
            c[] j19;
            int h22;
            int n4;
            int n5;
            c[] j20;
            int h23;
            c c15;
            c[] j21;
            int h24;
            c[] j22;
            int h25;
            c c16;
            c[] j23;
            int h26;
            c[] j24;
            int h27;
            c[] j25;
            int h28;
            c c17;
            c[] j26;
            int h29;
            c[] j27;
            int h30;
            c c18;
            c[] j28;
            int h31;
            c c19;
            au au;
            int n6;
            int n7;
            au au2;
            c[] j29;
            int h32;
            c[] j30;
            int h33;
            c[] j31;
            int h34;
            c c20;
            c[] j32;
            int h35;
            c[] j33;
            int h36;
            c c21;
            c[] j34;
            int h37;
            c c22;
            au au3;
            int n8;
            int n9;
            au au4;
            c[] j35;
            int h38;
            c[] j36;
            int h39;
            c[] j37;
            int h40;
            int n10;
            au au5;
            int n11;
            c[] j38;
            int h41;
            c[] j39 = null;
            int n12;
            c[] j40;
            int h42;
            int n13;
            c c23;
            int n14;
            c[] j41;
            int h43;
            c[] j42 = null;
            c c24;
            c[] j43;
            int h44;
            int n15;
            c[] j44;
            int h45;
            c[] j45;
            int h46;
            c c25;
            c[] j46;
            int h47;
            c[] j47;
            int h48;
            c c26;
            c[] j48;
            int h49;
            c[] j49;
            int h50;
            c c27;
            c[] j50;
            int h51;
            c[] j51;
            int h52;
            c[] j52;
            int h53;
            c c28;
            c[] j53;
            int h54;
            c c29;
            c[] j54;
            int h55;
            c c30;
            c[] j55;
            int h56;
            c c31;
            c[] j56;
            int h57;
            c[] j57;
            int h58;
            c c32;
            c[] j58;
            int h59;
            c c33;
            c[] j59;
            int h60;
            c[] j60;
            int h61;
            c[] j61;
            int h62;
            c[] j62;
            int h63;
            c c34;
            c[] j63;
            int h64;
            c c35;
            c[] j64;
            int h65;
            int n16;
            au au6;
            int n17;
            au au7;
            c[] j65;
            int h66;
            c f;
            Constructor a;
            c[] j66;
            int h67;
            c c36;
            c[] j67;
            int h68;
            c c37;
            c[] j68;
            int h69;
            int n18;
            au au8;
            int n19;
            au au9;
            c[] j69;
            int h70;
            int a2;
            c[] j70;
            int h71;
            c c38;
            c[] j71;
            int h72;
            c[] j72;
            int h73;
            c c39;
            as ae;
            c[] j73;
            int h74;
            c c40;
            c[] j74;
            int h75;
            c c41;
            c[] j75;
            int h76;
            c c42;
            c[] j76;
            int h77;
            c c43;
            c[] j77;
            int h78;
            c c44;
            c[] j78;
            int h79;
            c[] j79;
            int h80;
            c c45;
            c[] j80;
            int h81;
            int n20;
            au au10;
            au au11;
            int n21;
            String m2;
            au au12;
            String m3;
            int e2;
            int d;
            int e3;
            int[] array2;
            String[] array3;
            int n22;
            int n23;
            ar ar;
            int d2;
            int e4;
            int e5;
            c[] j81;
            int h82;
            c c46;
            int e6;
            c c47;
            c[] j82;
            int h83;
            c c48;
            c[] j83;
            int h84;
            c c49;
            int d3;
            au au13;
            String m4;
            int e7;
            int[] array4;
            String[] array5;
            int n24;
            int n25;
            ar ar2;
            c[] j84;
            int h85;
            c c50;
            c[] j85;
            int h86;
            c c51;
            int d4;
            aq aq;
            int e8;
            aq aq2;
            au au14;
            Label_2261_Outer:Label_2488_Outer:Label_2672_Outer:Label_2802_Outer:Label_4126_Outer:Label_5335_Outer:Label_6204_Outer:
            while (true) {
                Label_6527: {
                    if (!l) {
                        break Label_6527;
                    }
                    super.b = b2;
                    v = this;
                    n3 = (v.a[super.b++] & 0xFF);
                    e = 0;
                    if ((n3 & 0x80) != 0x0) {
                        e = this.e();
                    }
                    b2 = super.b + e;
                    Label_6404: {
                        switch (n3) {
                            case 0: {
                                return this.c;
                            }
                            case 4: {
                                b.a("nextFrame", null, false);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 5: {
                                b.a("prevFrame", null, false);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 6: {
                                b.a("play", null, false);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 7: {
                                b.a("stop", null, false);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 9: {
                                b.a("stopAllSounds", null, false);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 11:
                            case 12:
                            case 13:
                            case 16:
                            case 17: {
                                j = this.j;
                                h = this.h - 1;
                                this.h = h;
                                c4 = j[h];
                                i = this.j;
                                h2 = this.h - 1;
                                this.h = h2;
                                c5 = i[h2];
                                if (n3 == 11) {
                                    this.j[this.h++] = d.d(c5, c4);
                                    if (!l) {
                                        break;
                                    }
                                }
                                if (n3 == 12) {
                                    this.j[this.h++] = d.e(c5, c4);
                                    if (!l) {
                                        break;
                                    }
                                }
                                if (n3 == 13) {
                                    this.j[this.h++] = d.f(c5, c4);
                                    if (!l) {
                                        break;
                                    }
                                }
                                if (n3 == 16) {
                                    this.j[this.h++] = d.g(c5, c4);
                                    if (!l) {
                                        break;
                                    }
                                }
                                if (n3 != 17) {
                                    break;
                                }
                                this.j[this.h++] = d.h(c5, c4);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 18: {
                                k = this.j;
                                h3 = this.h - 1;
                                this.h = h3;
                                this.j[this.h++] = d.g(k[h3]);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 20: {
                                m = this.j;
                                h4 = this.h - 1;
                                this.h = h4;
                                this.j[this.h++] = new aq(m[h4].toString().length());
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 23: {
                                if (this.h <= 0) {
                                    break;
                                }
                                --this.h;
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 24: {
                                j2 = this.j;
                                h5 = this.h - 1;
                                this.h = h5;
                                this.j[this.h++] = new aq((int)j2[h5].af());
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 28: {
                                j3 = this.j;
                                h6 = this.h - 1;
                                this.h = h6;
                                this.j[this.h] = b.c(j3[h6], this.b);
                                ++this.h;
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 29: {
                                j4 = this.j;
                                h7 = this.h - 1;
                                this.h = h7;
                                c6 = j4[h7];
                                j5 = this.j;
                                h8 = this.h - 1;
                                this.h = h8;
                                b.a(j5[h8], c6, this.b);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 32: {
                                j6 = this.j;
                                h9 = this.h - 1;
                                this.h = h9;
                                b = j6[h9];
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 33: {
                                j7 = this.j;
                                h10 = this.h - 1;
                                this.h = h10;
                                c7 = j7[h10];
                                j8 = this.j;
                                h11 = this.h - 1;
                                this.h = h11;
                                this.j[this.h++] = d.c(j8[h11], c7);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 34: {
                                j9 = this.j;
                                h12 = this.h - 1;
                                this.h = h12;
                                c8 = j9[h12];
                                j10 = this.j;
                                h13 = this.h - 1;
                                this.h = h13;
                                c9 = j10[h13];
                                i2 = b.i((int)c8.af());
                                Label_1517: {
                                    if (c9.toString().equals("")) {
                                        this.j[this.h] = b.b(i2, this.b);
                                        if (!l) {
                                            break Label_1517;
                                        }
                                    }
                                    this.j[this.h] = b.c(c9, this.b).c(i2, this.b);
                                }
                                ++this.h;
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 35: {
                                j11 = this.j;
                                h14 = this.h - 1;
                                this.h = h14;
                                c10 = j11[h14];
                                j12 = this.j;
                                h15 = this.h - 1;
                                this.h = h15;
                                c11 = j12[h15];
                                j13 = this.j;
                                h16 = this.h - 1;
                                this.h = h16;
                                c12 = j13[h16];
                                i3 = b.i((int)c11.af());
                                if (c12.toString().equals("")) {
                                    b.a(i3, c10, this.b);
                                    if (!l) {
                                        break;
                                    }
                                }
                                b.c(c12, this.b).c(i3, c10);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 37: {
                                j14 = this.j;
                                h17 = this.h - 1;
                                this.h = h17;
                                j14[h17].a("removeMovieClip", null);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 38: {
                                out = System.out;
                                j15 = this.j;
                                h18 = this.h - 1;
                                this.h = h18;
                                out.println(j15[h18].b("toString", new au(), this.b));
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 43: {
                                j16 = this.j;
                                h19 = this.h - 1;
                                this.h = h19;
                                c13 = j16[h19];
                                j17 = this.j;
                                h20 = this.h - 1;
                                this.h = h20;
                                if (c13.d(j17[h20]).ag()) {
                                    this.j[this.h++] = c13;
                                    if (!l) {
                                        break;
                                    }
                                }
                                this.j[this.h++] = d.d;
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 44: {
                                j18 = this.j;
                                h21 = this.h - 1;
                                this.h = h21;
                                c14 = j18[h21];
                                j19 = this.j;
                                h22 = this.h - 1;
                                this.h = h22;
                                n4 = (int)j19[h22].af();
                                n5 = 0;
                                while (true) {
                                    Label_1895: {
                                        if (!l) {
                                            break Label_1895;
                                        }
                                        j20 = this.j;
                                        h23 = this.h - 1;
                                        this.h = h23;
                                        c15 = j20[h23];
                                        ++n5;
                                    }
                                    if (n5 >= n4) {
                                        break;
                                    }
                                    continue;
                                }
                            }
                            case 48: {
                                j21 = this.j;
                                h24 = this.h - 1;
                                this.h = h24;
                                this.j[this.h++] = new aq((int)(Math.random() * j21[h24].af()));
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 52: {
                                this.b();
                                this.j[this.h++] = new aq(System.currentTimeMillis() - this.a.a.a0);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 58: {
                                j22 = this.j;
                                h25 = this.h - 1;
                                this.h = h25;
                                c16 = j22[h25];
                                j23 = this.j;
                                h26 = this.h - 1;
                                this.h = h26;
                                this.j[this.h++] = j23[h26].b(c16, this.b);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 59: {
                                j24 = this.j;
                                h27 = this.h - 1;
                                this.h = h27;
                                this.j[this.h++] = b.a(j24[h27], this.b);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 60: {
                                j25 = this.j;
                                h28 = this.h - 1;
                                this.h = h28;
                                c17 = j25[h28];
                                j26 = this.j;
                                h29 = this.h - 1;
                                this.h = h29;
                                b.a(j26[h29], c17);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 61: {
                                j27 = this.j;
                                h30 = this.h - 1;
                                this.h = h30;
                                c18 = j27[h30];
                                j28 = this.j;
                                h31 = this.h - 1;
                                this.h = h31;
                                c19 = j28[h31];
                                au = new au();
                                n6 = (int)c19.af();
                                n7 = 0;
                                while (true) {
                                    while (true) {
                                        Label_2264: {
                                            if (!l) {
                                                break Label_2264;
                                            }
                                            au2 = au;
                                            j29 = this.j;
                                            h32 = this.h - 1;
                                            this.h = h32;
                                            au2.a(j29[h32]);
                                            ++n7;
                                        }
                                        if (n7 < n6) {
                                            continue Label_2261_Outer;
                                        }
                                        break;
                                    }
                                    this.j[this.h] = b.b(c18, au, this.b);
                                    ++this.h;
                                    if (l) {
                                        continue;
                                    }
                                    break;
                                }
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 62: {
                                j30 = this.j;
                                h33 = this.h - 1;
                                this.h = h33;
                                return j30[h33];
                            }
                            case 63: {
                                j31 = this.j;
                                h34 = this.h - 1;
                                this.h = h34;
                                c20 = j31[h34];
                                j32 = this.j;
                                h35 = this.h - 1;
                                this.h = h35;
                                this.j[this.h++] = new aq(j32[h35].af() % c20.af());
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 64: {
                                j33 = this.j;
                                h36 = this.h - 1;
                                this.h = h36;
                                c21 = j33[h36];
                                j34 = this.j;
                                h37 = this.h - 1;
                                this.h = h37;
                                c22 = j34[h37];
                                au3 = new au();
                                n8 = (int)c22.af();
                                n9 = 0;
                                while (true) {
                                    while (true) {
                                        Label_2491: {
                                            if (!l) {
                                                break Label_2491;
                                            }
                                            au4 = au3;
                                            j35 = this.j;
                                            h38 = this.h - 1;
                                            this.h = h38;
                                            au4.a(j35[h38]);
                                            ++n9;
                                        }
                                        if (n9 < n8) {
                                            continue Label_2488_Outer;
                                        }
                                        break;
                                    }
                                    this.j[this.h] = b.a(c21, au3, this.b);
                                    ++this.h;
                                    if (l) {
                                        continue;
                                    }
                                    break;
                                }
                                if (this.j[this.h - 1].c == null) {
                                    break;
                                }
                                this.j[this.h - 1].c.a(this.a);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 65: {
                                j36 = this.j;
                                h39 = this.h - 1;
                                this.h = h39;
                                b.a(j36[h39], this.c);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 66: {
                                j37 = this.j;
                                h40 = this.h - 1;
                                this.h = h40;
                                n10 = (int)j37[h40].af();
                                au5 = new au();
                                n11 = 0;
                                while (true) {
                                    while (true) {
                                        Label_2709: {
                                            if (!l) {
                                                break Label_2709;
                                            }
                                            j38 = this.j;
                                            h41 = --this.h;
                                            au5.c("" + n11, j39[h41]);
                                            ++n11;
                                        }
                                        if (n11 < n10) {
                                            continue Label_2672_Outer;
                                        }
                                        break;
                                    }
                                    j39 = this.j;
                                    n12 = (h41 = this.h);
                                    this.h = n12 + 1;
                                    if (l) {
                                        continue;
                                    }
                                    break;
                                }
                                j39[n12] = au5;
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 67: {
                                j40 = this.j;
                                h42 = this.h - 1;
                                this.h = h42;
                                n13 = (int)j40[h42].af();
                                c23 = new c();
                                n14 = 0;
                                while (true) {
                                    while (true) {
                                        Label_2835: {
                                            if (!l) {
                                                break Label_2835;
                                            }
                                            j41 = this.j;
                                            h43 = --this.h;
                                            c24 = j42[h43];
                                            j43 = this.j;
                                            h44 = this.h - 1;
                                            this.h = h44;
                                            c23.a(j43[h44], c24);
                                            ++n14;
                                        }
                                        if (n14 < n13) {
                                            continue Label_2802_Outer;
                                        }
                                        break;
                                    }
                                    j42 = this.j;
                                    n15 = (h43 = this.h);
                                    this.h = n15 + 1;
                                    if (l) {
                                        continue;
                                    }
                                    break;
                                }
                                j42[n15] = c23;
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 68: {
                                j44 = this.j;
                                h45 = this.h - 1;
                                this.h = h45;
                                this.j[this.h++] = new as(j44[h45].r());
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 71: {
                                j45 = this.j;
                                h46 = this.h - 1;
                                this.h = h46;
                                c25 = j45[h46];
                                j46 = this.j;
                                h47 = this.h - 1;
                                this.h = h47;
                                this.j[this.h++] = d.c(j46[h47], c25);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 72: {
                                j47 = this.j;
                                h48 = this.h - 1;
                                this.h = h48;
                                c26 = j47[h48];
                                j48 = this.j;
                                h49 = this.h - 1;
                                this.h = h49;
                                this.j[this.h++] = d.i(j48[h49], c26);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 73: {
                                j49 = this.j;
                                h50 = this.h - 1;
                                this.h = h50;
                                c27 = j49[h50];
                                j50 = this.j;
                                h51 = this.h - 1;
                                this.h = h51;
                                this.j[this.h++] = d.k(j50[h51], c27);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 74: {
                                j51 = this.j;
                                h52 = this.h - 1;
                                this.h = h52;
                                this.j[this.h++] = new aq(j51[h52].af());
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 75: {
                                j52 = this.j;
                                h53 = this.h - 1;
                                this.h = h53;
                                c28 = j52[h53];
                                if (c28.a == 4) {
                                    this.j[this.h++] = c28;
                                    if (!l) {
                                        break;
                                    }
                                }
                                this.j[this.h++] = new as(c28.toString());
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 76: {
                                this.b();
                                this.j[this.h++] = this.j[this.h - 1];
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 77: {
                                j53 = this.j;
                                h54 = this.h - 1;
                                this.h = h54;
                                c29 = j53[h54];
                                j54 = this.j;
                                h55 = this.h - 1;
                                this.h = h55;
                                c30 = j54[h55];
                                this.j[this.h++] = c29;
                                this.j[this.h++] = c30;
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 78: {
                                j55 = this.j;
                                h56 = this.h - 1;
                                this.h = h56;
                                c31 = j55[h56];
                                j56 = this.j;
                                h57 = this.h - 1;
                                this.h = h57;
                                this.j[this.h] = j56[h57].d(c31, this.b);
                                ++this.h;
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 79: {
                                j57 = this.j;
                                h58 = this.h - 1;
                                this.h = h58;
                                c32 = j57[h58];
                                j58 = this.j;
                                h59 = this.h - 1;
                                this.h = h59;
                                c33 = j58[h59];
                                j59 = this.j;
                                h60 = this.h - 1;
                                this.h = h60;
                                j59[h60].a(c33, c32);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 80: {
                                j60 = this.j;
                                h61 = this.h - 1;
                                this.h = h61;
                                this.k.c = j60[h61].af();
                                this.j[this.h++] = d.e(this.k);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 81: {
                                j61 = this.j;
                                h62 = this.h - 1;
                                this.h = h62;
                                this.k.c = j61[h62].af();
                                this.j[this.h++] = d.f(this.k);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 82: {
                                j62 = this.j;
                                h63 = this.h - 1;
                                this.h = h63;
                                c34 = j62[h63];
                                j63 = this.j;
                                h64 = this.h - 1;
                                this.h = h64;
                                c35 = j63[h64];
                                j64 = this.j;
                                h65 = this.h - 1;
                                this.h = h65;
                                n16 = (int)j64[h65].af();
                                au6 = null;
                                if (n16 > 0) {
                                    au6 = new au();
                                    n17 = 0;
                                    while (true) {
                                        Label_3731: {
                                            if (!l) {
                                                break Label_3731;
                                            }
                                            au7 = au6;
                                            j65 = this.j;
                                            h66 = this.h - 1;
                                            this.h = h66;
                                            au7.a(j65[h66]);
                                            ++n17;
                                        }
                                        if (n17 < n16) {
                                            continue;
                                        }
                                        break;
                                    }
                                }
                                Label_4011: {
                                    if (c35 == c2) {
                                        if (c34.a == 0 || c34.toString().equals("")) {
                                            f = c35.f("__constructor__");
                                            Label_3872: {
                                                if (c35.c != null && n16 != 0) {
                                                    try {
                                                        a = ar.a(c35.c.getClass().getConstructors(), au6, this.b);
                                                        (c.c = a.newInstance(ar.a(a.getParameterTypes(), au6, this.b))).a(c);
                                                        break Label_3872;
                                                    }
                                                    catch (Exception ex) {
                                                        ex.printStackTrace();
                                                        if (!l) {
                                                            break Label_3872;
                                                        }
                                                    }
                                                }
                                                c.a(au6);
                                            }
                                            this.j[this.h] = c.a(f, au6, c35, this.b);
                                            if (!l) {
                                                break Label_4011;
                                            }
                                        }
                                        this.j[this.h] = c.a(c35.d(c34, this.b), au6, c35, this.b);
                                        if (!l) {
                                            break Label_4011;
                                        }
                                    }
                                    if (c34.a == 0 || c34.toString().equals("")) {
                                        this.j[this.h] = c.a(c35, au6, null, this.b);
                                        if (!l) {
                                            break Label_4011;
                                        }
                                    }
                                    this.j[this.h] = c35.c(c34, au6, this.b);
                                }
                                ++this.h;
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 83: {
                                j66 = this.j;
                                h67 = this.h - 1;
                                this.h = h67;
                                c36 = j66[h67];
                                j67 = this.j;
                                h68 = this.h - 1;
                                this.h = h68;
                                c37 = j67[h68];
                                j68 = this.j;
                                h69 = this.h - 1;
                                this.h = h69;
                                n18 = (int)j68[h69].af();
                                au8 = new au();
                                n19 = 0;
                                while (true) {
                                    while (true) {
                                        Label_4130: {
                                            if (!l) {
                                                break Label_4130;
                                            }
                                            au9 = au8;
                                            j69 = this.j;
                                            h70 = this.h - 1;
                                            this.h = h70;
                                            au9.a(j69[h70]);
                                            ++n19;
                                        }
                                        if (n19 < n18) {
                                            continue Label_4126_Outer;
                                        }
                                        break;
                                    }
                                    a2 = c36.a;
                                    if (l) {
                                        continue;
                                    }
                                    break;
                                }
                                Label_4245: {
                                    if (a2 == 0 || c36.toString().equals("")) {
                                        if (c37.a == 6) {
                                            this.j[this.h] = ((ar)c37).a(au8, this.b);
                                            if (!l) {
                                                break Label_4245;
                                            }
                                        }
                                        this.j[this.h] = this.c;
                                        if (!l) {
                                            break Label_4245;
                                        }
                                    }
                                    this.j[this.h] = c37.a(c36, au8, this.b);
                                }
                                ++this.h;
                                if (this.j[this.h - 1].c == null) {
                                    break;
                                }
                                this.j[this.h - 1].c.a(this.a);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 84: {
                                j70 = this.j;
                                h71 = this.h - 1;
                                this.h = h71;
                                c38 = j70[h71];
                                j71 = this.j;
                                h72 = this.h - 1;
                                this.h = h72;
                                this.j[this.h++] = j71[h72].d(c38);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 85: {
                                j72 = this.j;
                                h73 = this.h - 1;
                                this.h = h73;
                                c39 = j72[h73];
                                this.j[this.h++] = d.d;
                                while ((ae = c39.ae()) != null) {
                                    this.b();
                                    this.j[this.h++] = ae;
                                }
                                break;
                            }
                            case 96:
                            case 97:
                            case 98:
                            case 99:
                            case 100:
                            case 101: {
                                j73 = this.j;
                                h74 = this.h - 1;
                                this.h = h74;
                                c40 = j73[h74];
                                j74 = this.j;
                                h75 = this.h - 1;
                                this.h = h75;
                                c41 = j74[h75];
                                if (n3 == 96) {
                                    this.j[this.h++] = new aq((int)c40.af() & (int)c41.af());
                                    if (!l) {
                                        break;
                                    }
                                }
                                if (n3 == 97) {
                                    this.j[this.h++] = new aq((int)c40.af() | (int)c41.af());
                                    if (!l) {
                                        break;
                                    }
                                }
                                if (n3 == 98) {
                                    this.j[this.h++] = new aq((int)c40.af() ^ (int)c41.af());
                                    if (!l) {
                                        break;
                                    }
                                }
                                if (n3 == 99) {
                                    this.j[this.h++] = new aq((int)c41.af() << ((int)c40.af() & 0x1F));
                                    if (!l) {
                                        break;
                                    }
                                }
                                if (n3 == 100) {
                                    this.j[this.h++] = new aq((int)c41.af() >> ((int)c40.af() & 0x1F));
                                    if (!l) {
                                        break;
                                    }
                                }
                                if (n3 != 101) {
                                    break;
                                }
                                this.j[this.h++] = new aq((int)c41.af() >>> ((int)c40.af() & 0x1F));
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 102: {
                                j75 = this.j;
                                h76 = this.h - 1;
                                this.h = h76;
                                c42 = j75[h76];
                                j76 = this.j;
                                h77 = this.h - 1;
                                this.h = h77;
                                c43 = j76[h77];
                                if (c43.a == c42.a) {
                                    this.j[this.h++] = d.k(c43, c42);
                                    if (!l) {
                                        break;
                                    }
                                }
                                this.j[this.h++] = d.b;
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 103: {
                                j77 = this.j;
                                h78 = this.h - 1;
                                this.h = h78;
                                c44 = j77[h78];
                                j78 = this.j;
                                h79 = this.h - 1;
                                this.h = h79;
                                this.j[this.h++] = d.j(j78[h79], c44);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 105: {
                                j79 = this.j;
                                h80 = this.h - 1;
                                this.h = h80;
                                c45 = j79[h80];
                                j80 = this.j;
                                h81 = this.h - 1;
                                this.h = h81;
                                d.b(j80[h81], c45);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 129: {
                                n20 = this.e() + 1;
                                au10 = new au();
                                au10.a(new aq(n20));
                                b.a("gotoAndStop", au10, false);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 131: {
                                au11 = new au();
                                au11.a(new as(this.m()));
                                au11.a(new as(this.m()));
                                b.a("getURL", au11, false);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 135: {
                                array[this.d()] = this.j[this.h - 1];
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 136: {
                                this.g = this.e();
                                this.f = new as[this.g];
                                n21 = 0;
                                while (true) {
                                    Label_5178: {
                                        if (!l) {
                                            break Label_5178;
                                        }
                                        this.f[n21] = new as(this.m());
                                        ++n21;
                                    }
                                    if (n21 >= this.g) {
                                        break;
                                    }
                                    continue;
                                }
                            }
                            case 139: {
                                m2 = this.m();
                                if (m2.equals("")) {
                                    b = c3;
                                    if (!l) {
                                        break;
                                    }
                                }
                                b = b.b(m2, false);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 140: {
                                au12 = new au();
                                au12.a(new as(this.m()));
                                b.a("gotoAndPlay", au12, false);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 142: {
                                m3 = this.m();
                                e2 = this.e();
                                d = this.d();
                                e3 = this.e();
                                array2 = new int[e2];
                                array3 = new String[e2];
                                n22 = 0;
                                while (true) {
                                    while (true) {
                                        Label_5338: {
                                            if (!l) {
                                                break Label_5338;
                                            }
                                            array2[n22] = this.d();
                                            array3[n22] = this.m();
                                            ++n22;
                                        }
                                        if (n22 < e2) {
                                            continue Label_5335_Outer;
                                        }
                                        break;
                                    }
                                    n23 = b2 + this.e();
                                    ar = new ar(this, b2 - this.d, n23 - this.d, b, d, e2, array2, array3, e3);
                                    if (l) {
                                        continue;
                                    }
                                    break;
                                }
                                Label_5445: {
                                    if (!m3.equals("")) {
                                        b.c(m3, ar);
                                        if (!l) {
                                            break Label_5445;
                                        }
                                    }
                                    this.b();
                                    this.j[this.h++] = ar;
                                }
                                b2 = n23;
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 143: {
                                d2 = this.d();
                                e4 = this.e();
                                e5 = this.e();
                                this.e();
                                if ((d2 & 0x4) == 0x0) {
                                    this.m();
                                }
                                else {
                                    this.d();
                                }
                                this.a(c, array, c2, b, super.b, super.b + e4, 0);
                                b2 += e4 + e5;
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 148: {
                                j81 = this.j;
                                h82 = this.h - 1;
                                this.h = h82;
                                c46 = j81[h82];
                                c46.f = b;
                                e6 = this.e();
                                if (n2 <= 8) {
                                    this.a(c, array, c2, c46, b2 - this.d, b2 - this.d + e6, n2 + 1);
                                }
                                c46.f = null;
                                b2 += e6;
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 150: {
                                while (true) {
                                    Label_6020: {
                                        if (!l) {
                                            break Label_6020;
                                        }
                                        this.b();
                                        Label_5992: {
                                            switch (this.d()) {
                                                case 0: {
                                                    this.j[this.h++] = new as(this.m());
                                                    if (l) {
                                                        break Label_5992;
                                                    }
                                                    break;
                                                }
                                                case 1: {
                                                    this.j[this.h++] = new aq(this.i());
                                                    if (l) {
                                                        break Label_5992;
                                                    }
                                                    break;
                                                }
                                                case 2: {
                                                    this.j[this.h++] = d.d;
                                                    if (l) {
                                                        break Label_5992;
                                                    }
                                                    break;
                                                }
                                                case 3: {
                                                    this.j[this.h++] = this.c;
                                                    if (l) {
                                                        break Label_5992;
                                                    }
                                                    break;
                                                }
                                                case 4: {
                                                    c47 = array[this.d()];
                                                    if (c47 == null) {
                                                        c47 = this.c;
                                                    }
                                                    this.j[this.h++] = c47;
                                                    if (l) {
                                                        break Label_5992;
                                                    }
                                                    break;
                                                }
                                                case 5: {
                                                    this.j[this.h++] = new av(this.d() == 1);
                                                    if (l) {
                                                        break Label_5992;
                                                    }
                                                    break;
                                                }
                                                case 6: {
                                                    this.j[this.h++] = new aq((float)this.j());
                                                    if (l) {
                                                        break Label_5992;
                                                    }
                                                    break;
                                                }
                                                case 7: {
                                                    this.j[this.h++] = new aq(this.g());
                                                    if (l) {
                                                        break Label_5992;
                                                    }
                                                    break;
                                                }
                                                case 8: {
                                                    this.j[this.h++] = this.f[this.d()];
                                                    if (l) {
                                                        break Label_5992;
                                                    }
                                                    break;
                                                }
                                                case 9: {
                                                    this.j[this.h++] = this.f[this.e()];
                                                    break;
                                                }
                                            }
                                        }
                                    }
                                    if (super.b >= b2) {
                                        break;
                                    }
                                    continue;
                                }
                            }
                            case 153: {
                                b2 += this.f();
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 154: {
                                j82 = this.j;
                                h83 = this.h - 1;
                                this.h = h83;
                                c48 = j82[h83];
                                j83 = this.j;
                                h84 = this.h - 1;
                                this.h = h84;
                                c49 = j83[h84];
                                d3 = this.d();
                                au13 = new au();
                                au13.a(c49);
                                if ((d3 & 0x40) == 0x0) {
                                    au13.a(c48);
                                    b.a("getURL", au13, false);
                                    if (!l) {
                                        break;
                                    }
                                }
                                if ((d3 & 0x80) != 0x0) {
                                    break;
                                }
                                c48.b("loadMovie", au13, false);
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 155: {
                                m4 = this.m();
                                e7 = this.e();
                                array4 = new int[e7];
                                array5 = new String[e7];
                                n24 = 0;
                                while (true) {
                                    while (true) {
                                        Label_6207: {
                                            if (!l) {
                                                break Label_6207;
                                            }
                                            array5[n24] = this.m();
                                            ++n24;
                                        }
                                        if (n24 < e7) {
                                            continue Label_6204_Outer;
                                        }
                                        break;
                                    }
                                    n25 = b2 + this.e();
                                    ar2 = new ar(this, b2 - this.d, n25 - this.d, b, 4, e7, array4, array5, 42);
                                    if (l) {
                                        continue;
                                    }
                                    break;
                                }
                                Label_6313: {
                                    if (!m4.equals("")) {
                                        b.c(m4, ar2);
                                        if (!l) {
                                            break Label_6313;
                                        }
                                    }
                                    this.b();
                                    this.j[this.h++] = ar2;
                                }
                                b2 = n25;
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 157: {
                                j84 = this.j;
                                h85 = this.h - 1;
                                this.h = h85;
                                c50 = j84[h85];
                                if (super.f == 5) {
                                    if (!c50.ag()) {
                                        break;
                                    }
                                    b2 += this.f();
                                    if (!l) {
                                        break;
                                    }
                                }
                                if (c50.af() == 0.0f || c50 == this.c) {
                                    break;
                                }
                                b2 += this.f();
                                if (l) {
                                    break Label_6404;
                                }
                                break;
                            }
                            case 159: {
                                j85 = this.j;
                                h86 = this.h - 1;
                                this.h = h86;
                                c51 = j85[h86];
                                d4 = this.d();
                                if ((d4 & 0x2) != 0x0 && c51.a == 3) {
                                    aq = (aq)c51;
                                    e8 = this.e();
                                    aq2 = aq;
                                    aq2.c += e8;
                                }
                                au14 = new au();
                                au14.a(c51);
                                if ((d4 & 0x1) != 0x0) {
                                    b.a("gotoAndPlay", au14, false);
                                    if (!l) {
                                        break;
                                    }
                                }
                                b.a("gotoAndStop", au14, false);
                                break;
                            }
                        }
                    }
                }
                if (b2 < n) {
                    continue;
                }
                break;
            }
            v = this;
            if (!l) {
                return this.c;
            }
            continue;
        }
    }
}
