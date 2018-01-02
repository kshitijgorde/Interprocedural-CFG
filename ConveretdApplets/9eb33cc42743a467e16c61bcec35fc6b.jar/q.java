import java.util.NoSuchElementException;

// 
// Decompiled by Procyon v0.5.30
// 

public class q
{
    public h d4;
    public static final int[] e2;
    public final int[] e1;
    public static final String[] e0;
    public int e_;
    public int ez;
    public static final String[][] ey;
    public static final String[][] ex;
    public static final String[][] ew;
    public static final String[][] ev;
    public static final String[][][] eu;
    public static final String[] et;
    public char[] es;
    public int er;
    public int eq;
    public int ep;
    public char[] eo;
    public String en;
    public String em;
    public int[] el;
    public boolean ek;
    public boolean ej;
    public int ei;
    public int[] eh;
    public int eg;
    public static final int[] ef;
    public static final int[] ee;
    public static final int[] ed;
    public static final int[] ec;
    public static final int[] eb;
    public static final int[] ea;
    public static final int[] d9;
    public static final int[] d8;
    public static final int[] d7;
    public static final int[] d6;
    public static final int[] d5;
    
    public final void du(final h d4) {
        this.d4 = d4;
    }
    
    public q() {
    }
    
    public q(final int n) {
        this(0, 0);
    }
    
    public q(final int n, final int n2) {
        this();
        this.e1 = new int[] { 38, 40, 39, 37, 33, 34, 35, 36, 155, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 123, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 109, 111 };
        this.es = new char[4];
        this.eo = new char[4];
        this.eh = new int[10];
        try {
            this.ds(n);
        }
        catch (NoSuchElementException ex) {
            try {
                this.ds(0);
            }
            catch (NoSuchElementException ex2) {}
        }
    }
    
    public static String[] dt() {
        int n = 0;
        for (int i = 0; i < q.e0.length; ++i) {
            if (!q.e0[i].equals("")) {
                ++n;
            }
        }
        final String[] array = new String[n];
        int n2 = 0;
        for (int j = 0; j < q.e0.length; ++j) {
            if (!q.e0[j].equals("")) {
                array[n2++] = q.e0[j];
            }
        }
        return array;
    }
    
    public final String b3() {
        return q.e0[this.e_];
    }
    
    private final void ds(final int n) throws NoSuchElementException {
        if (n < q.e0.length && n > -1) {
            this.e_ = n;
            this.ez = n;
            if (this.e_ >= 10) {
                this.ez -= 10;
            }
            this.dd();
            return;
        }
        throw new NoSuchElementException(String.valueOf(n) + " is not a supported terminal-emulation");
    }
    
    public final void dr(final String s) throws NoSuchElementException {
        int n;
        for (n = 0; n < q.e0.length && !q.e0[n].equalsIgnoreCase(s); ++n) {}
        this.ds(n);
    }
    
    public final int dq(char c) {
        if (c >= '_' && c <= '~') {
            c = (char)q.e2[c - '_'];
        }
        else if (c >= '+' && c <= '.') {
            c = (char)q.e2[c - '+' + ' '];
        }
        return c;
    }
    
    public final char dp(char c) {
        switch (c) {
            case ' ': {
                c = ' ';
                break;
            }
            case '\u0004': {
                c = '`';
                break;
            }
            case '±': {
                c = 'a';
                break;
            }
            case '\u00f8': {
                c = 'f';
                break;
            }
            case '\u00f1': {
                c = 'g';
                break;
            }
            case '°': {
                c = 'h';
                break;
            }
            case '\u0089':
            case '\u00d9': {
                c = 'j';
                break;
            }
            case '\u008c':
            case '¿': {
                c = 'k';
                break;
            }
            case '\u0086':
            case '\u00da': {
                c = 'l';
                break;
            }
            case '\u0083':
            case '\u00c0': {
                c = 'm';
                break;
            }
            case '\u00c5': {
                c = 'n';
                break;
            }
            case '\u008a':
            case '\u00c4': {
                c = 'q';
                break;
            }
            case '\u00f2': {
                c = 'r';
                break;
            }
            case '\u0087':
            case '\u00c3': {
                c = 't';
                break;
            }
            case '\u008d':
            case '´': {
                c = 'u';
                break;
            }
            case '\u00c1': {
                c = 'v';
                break;
            }
            case '\u00c2': {
                c = 'w';
                break;
            }
            case '\u0085':
            case '³': {
                c = 'x';
                break;
            }
            case '\u00f3':
            case '\u00f9': {
                c = 'y';
                break;
            }
            case '\u00fa': {
                c = 'z';
                break;
            }
            case '\u00e3':
            case '\u00fb': {
                c = '{';
                break;
            }
            case '\u00d8':
            case '\u00fc': {
                c = '|';
                break;
            }
            case '\u009c':
            case '\u00fd': {
                c = '}';
                break;
            }
            case '\u00fe': {
                c = '~';
                break;
            }
            case '\u0019': {
                c = '.';
                break;
            }
            case '\u00db': {
                c = '+';
                break;
            }
            case '\u0018': {
                c = '-';
                break;
            }
        }
        return c;
    }
    
    public final char do(char c) {
        switch (c) {
            case ' ': {
                c = ' ';
                break;
            }
            case '`': {
                c = '`';
                break;
            }
            case '1': {
                c = 'a';
                break;
            }
            case 'x': {
                c = 'f';
                break;
            }
            case 'q': {
                c = 'g';
                break;
            }
            case '0': {
                c = 'h';
                break;
            }
            case 'Y': {
                c = 'j';
                break;
            }
            case '?': {
                c = 'k';
                break;
            }
            case 'Z': {
                c = 'l';
                break;
            }
            case '@': {
                c = 'm';
                break;
            }
            case 'E': {
                c = 'n';
                break;
            }
            case 'o': {
                c = 'o';
                break;
            }
            case 'p': {
                c = 'p';
                break;
            }
            case 'D': {
                c = 'q';
                break;
            }
            case 'r': {
                c = 'r';
                break;
            }
            case 's': {
                c = 's';
                break;
            }
            case 'C': {
                c = 't';
                break;
            }
            case '4': {
                c = 'u';
                break;
            }
            case 'A': {
                c = 'v';
                break;
            }
            case 'B': {
                c = 'w';
                break;
            }
            case '3': {
                c = 'x';
                break;
            }
            case 'y': {
                c = 'y';
                break;
            }
            case 'z': {
                c = 'z';
                break;
            }
            case '{': {
                c = '{';
                break;
            }
            case '|': {
                c = '|';
                break;
            }
            case '}': {
                c = '}';
                break;
            }
            case '~': {
                c = '~';
                break;
            }
        }
        return c;
    }
    
    public final int dn(char c) {
        if (c > '\u00ff') {
            return c;
        }
        switch (this.el[c]) {
            case 63: {
                int dq = -1;
                switch (this.es[this.eq]) {
                    case 'A': {
                        if (c == '#') {
                            dq = 163;
                            break;
                        }
                        break;
                    }
                    case '1':
                    case 'B': {
                        dq = c;
                        if (dq < 32) {
                            if (dq == 0) {
                                final int ez = this.ez;
                                if (ez == 6 || ez == 7 || ez == 8 || ez == 9) {
                                    return -1;
                                }
                            }
                            this.d4.bx('^');
                            dq += 64;
                            break;
                        }
                        break;
                    }
                    case '0':
                    case '2': {
                        if (!this.d4.aq(10)) {
                            if (this.ez == 1) {
                                c = this.dp(c);
                            }
                            else if (this.ez == 3) {
                                c = this.do(c);
                            }
                            this.d4.bw(c);
                            break;
                        }
                        dq = this.dq(c);
                        break;
                    }
                }
                return dq;
            }
            case 0: {
                this.el = q.ef;
                break;
            }
            case 1: {
                this.el = q.d7;
                break;
            }
            case 2: {
                this.el = q.d8;
                break;
            }
            case 4: {
                this.d4.bs();
                break;
            }
            case 5: {
                this.d4.br();
                break;
            }
            case 6: {
                this.d4.bj();
                this.el = q.ef;
                break;
            }
            case 7: {
                this.el = q.d9;
                break;
            }
            case 8: {
                this.d4.bi();
                this.el = q.ef;
                break;
            }
            case 9: {
                this.d4.bq();
                break;
            }
            case 10: {
                this.eq = 0;
                break;
            }
            case 11: {
                this.eq = 1;
                break;
            }
            case 12: {
                this.el = q.d6;
                break;
            }
            case 13: {
                this.er = 0;
                this.el = q.d5;
                break;
            }
            case 14: {
                this.er = 1;
                this.el = q.d5;
                break;
            }
            case 15: {
                this.er = 2;
                this.el = q.d5;
                break;
            }
            case 16: {
                this.er = 3;
                this.el = q.d5;
                break;
            }
            case 17: {
                this.el = q.ea;
                break;
            }
            case 18: {
                int n = this.eh[this.eg - 1];
                if (n == -1) {
                    n = 0;
                }
                this.eh[this.eg - 1] = 10 * n + (c - '0');
                break;
            }
            case 19: {
                this.eh[this.eg++] = -1;
                break;
            }
            case 72: {
                this.eh[this.eg++] = -1;
                this.en = "";
                this.el = q.eb;
                break;
            }
            case 20: {
                this.el = q.ed;
                break;
            }
            case 21: {
                int n2 = this.eh[0];
                if (n2 < 1) {
                    n2 = 1;
                }
                this.d4.av(n2);
                this.el = q.ef;
                break;
            }
            case 77: {
                this.d4.bj();
            }
            case 22: {
                int n3 = this.eh[0];
                if (n3 < 1) {
                    n3 = 1;
                }
                this.d4.bb(n3);
                this.el = q.ef;
                break;
            }
            case 76: {
                this.d4.bj();
            }
            case 23: {
                int n4 = this.eh[0];
                if (n4 < 1) {
                    n4 = 1;
                }
                this.d4.ba(n4);
                this.el = q.ef;
                break;
            }
            case 24: {
                int n5 = this.eh[0];
                if (n5 < 1) {
                    n5 = 1;
                }
                this.d4.a9(n5);
                this.el = q.ef;
                break;
            }
            case 25: {
                int n6 = this.eh[0];
                if (n6 < 1) {
                    n6 = 1;
                }
                this.d4.a8(n6);
                this.el = q.ef;
                break;
            }
            case 26: {
                int n7 = this.eh[0];
                int n8 = this.eh[1];
                if (n7 < 1) {
                    n7 = 1;
                }
                if (this.eg < 2 || n8 < 1) {
                    n8 = 1;
                }
                this.d4.bc(n7 - 1, n8 - 1, this.ek);
                this.el = q.ef;
                break;
            }
            case 27: {
                switch (this.eh[0]) {
                    case -1:
                    case 0: {
                        this.d4.a1();
                        break;
                    }
                    case 1: {
                        this.d4.a0();
                        break;
                    }
                    case 2: {
                        this.d4.a_();
                        this.d4.bc(0, 0, this.ek);
                        break;
                    }
                }
                this.el = q.ef;
                break;
            }
            case 28: {
                switch (this.eh[0]) {
                    case -1:
                    case 0: {
                        this.d4.az();
                        break;
                    }
                    case 1: {
                        this.d4.ay();
                        break;
                    }
                    case 2: {
                        this.d4.ax();
                        break;
                    }
                }
                this.el = q.ef;
                break;
            }
            case 29: {
                int n9 = this.eh[0];
                if (n9 < 1) {
                    n9 = 1;
                }
                this.d4.at(n9);
                this.el = q.ef;
                break;
            }
            case 30: {
                int n10 = this.eh[0];
                if (n10 < 1) {
                    n10 = 1;
                }
                this.d4.as(n10);
                this.el = q.ef;
                break;
            }
            case 31: {
                int n11 = this.eh[0];
                if (n11 < 1) {
                    n11 = 1;
                }
                this.d4.au(n11);
                this.el = q.ef;
                break;
            }
            case 67: {
                this.eh[0] = -1;
            }
            case 32: {
                this.em = String.valueOf(q.et[4]) + "?1;2c";
                this.d4.bt(this.em.getBytes());
                this.el = q.ef;
                break;
            }
            case 34: {
                if (this.eh[0] <= 0) {
                    this.d4.bm(this.d4.bd());
                }
                else if (this.eh[0] == 3) {
                    this.d4.bk();
                }
                this.el = q.ef;
                break;
            }
            case 35: {
                this.dm(true);
                this.el = q.ef;
                break;
            }
            case 36: {
                this.dm(false);
                this.el = q.ef;
                break;
            }
            case 37: {
                this.dl();
                this.el = q.ef;
                break;
            }
            case 38: {
                this.em = null;
                if (this.eh[0] == 5) {
                    this.em = String.valueOf(q.et[4]) + "0n";
                }
                else if (this.eh[0] == 6) {
                    this.em = String.valueOf(q.et[4]) + (this.d4.be() + 1) + ";" + (this.d4.bd() + 1) + "R";
                }
                if (this.em != null) {
                    this.d4.bt(this.em.getBytes());
                }
                this.el = q.ef;
                break;
            }
            case 39: {
                int n12 = this.eh[0];
                int b2 = this.eh[1];
                if (n12 < 1) {
                    n12 = 1;
                }
                if (this.eg < 2 || b2 == -1 || b2 == 0 || b2 > this.d4.b2()) {
                    b2 = this.d4.b2();
                }
                if (b2 > n12) {
                    this.d4.bf(n12 - 1, b2);
                    this.d4.bc(0, 0, this.ek);
                }
                this.el = q.ef;
                break;
            }
            case 40: {
                if (this.eh[0] == -1 || this.eh[0] == 1 || this.eh[0] == 0) {
                    this.em = String.valueOf(q.et[4]) + String.valueOf(this.d4.be() + 2) + ";1;1;112;112;1;0x";
                }
                if (this.em != null) {
                    this.d4.bt(this.em.getBytes());
                }
                this.el = q.ef;
                break;
            }
            case 41: {
                this.dk(true);
                this.el = q.ef;
                break;
            }
            case 42: {
                this.dk(false);
                this.el = q.ef;
                break;
            }
            case 43: {
                this.el = q.ef;
                break;
            }
            case 44: {
                this.es[this.er] = c;
                this.el = q.ef;
                break;
            }
            case 45: {
                this.d4.a5();
                this.ep = this.eq;
                System.arraycopy(this.es, 0, this.eo, 0, 4);
                this.el = q.ef;
                break;
            }
            case 46: {
                this.d4.a4();
                this.eq = this.ep;
                System.arraycopy(this.eo, 0, this.es, 0, 4);
                this.el = q.ef;
                break;
            }
            case 47: {
                this.el = q.ef;
                break;
            }
            case 48: {
                this.el = q.ef;
                break;
            }
            case 49: {
                this.d4.a7(1);
                this.el = q.ef;
                break;
            }
            case 50: {
                this.d4.a7(1);
                this.d4.bj();
                this.el = q.ef;
                break;
            }
            case 51: {
                this.d4.bn(this.d4.bd());
                this.el = q.ef;
                break;
            }
            case 52: {
                this.d4.a6(1);
                this.el = q.ef;
                break;
            }
            case 53: {
                this.el = q.ef;
                break;
            }
            case 54: {
                this.el = q.ef;
                break;
            }
            case 55: {
                this.eh[0] = -1;
                this.eg = 1;
                this.el = q.ee;
                break;
            }
            case 56: {
                this.eh[0] = -1;
                this.eg = 1;
                this.el = q.ec;
                break;
            }
            case 57: {
                this.dd();
                this.el = q.ef;
                break;
            }
            case 58: {
                this.eq = 2;
                this.el = q.ef;
                break;
            }
            case 59: {
                this.eq = 3;
                this.el = q.ef;
                break;
            }
            case 60: {
                this.el = q.ef;
                break;
            }
            case 61: {
                this.el = q.ef;
                break;
            }
            case 62: {
                this.el = q.ef;
                break;
            }
            case 64: {
                this.dh();
                this.el = q.ef;
                break;
            }
            case 65: {
                this.dg();
                this.el = q.ef;
                break;
            }
            case 68: {
                this.el = q.ef;
                break;
            }
            case 69: {
                this.el = q.ef;
                break;
            }
            case 70: {
                this.el = q.ef;
                break;
            }
            case 73: {
                this.dj();
                this.el = q.ef;
                break;
            }
            case 71: {
                this.en = String.valueOf(this.en) + c;
                break;
            }
            case 74: {
                this.d4.bt(this.b3().getBytes());
                break;
            }
            case 75: {
                this.di();
                this.el = q.ef;
            }
            case 79: {
                int n13 = this.eh[0];
                if (n13 == -1) {
                    n13 = 1;
                }
                this.d4.bp(n13);
                this.el = q.ef;
                break;
            }
            case 80: {
                int n14 = this.eh[0];
                if (n14 == -1) {
                    n14 = 1;
                }
                this.d4.a3(n14);
                this.el = q.ef;
                break;
            }
            case 81: {
                int n15 = this.eh[0];
                if (n15 == -1) {
                    n15 = 1;
                }
                this.d4.a2(n15);
                this.el = q.ef;
                break;
            }
            case 82: {
                int n16 = this.eh[0];
                if (n16 == -1) {
                    n16 = 1;
                }
                this.d4.aw(n16);
                this.el = q.ef;
                break;
            }
            case 83: {
                int n17 = this.eh[0];
                if (n17 == -1) {
                    n17 = 1;
                }
                this.d4.bo(n17);
                this.el = q.ef;
                break;
            }
            case 78:
            case 84: {
                int n18 = this.eh[0];
                if (n18 < 1) {
                    n18 = 1;
                }
                this.d4.bc(this.d4.be(), n18 - 1, false);
                this.el = q.ef;
                break;
            }
            case 85: {
                this.el = q.ef;
                break;
            }
            case 86: {
                int n19 = this.eh[0];
                if (n19 < 1) {
                    n19 = 1;
                }
                this.d4.bc(n19 - 1, this.d4.bd(), false);
                this.el = q.ef;
                break;
            }
        }
        return -1;
    }
    
    public final void dm(final boolean b) {
        for (int i = 0; i < this.eg; ++i) {
            switch (this.eh[i]) {
                case 4: {
                    this.d4.ar(3, b);
                    break;
                }
                case 20: {
                    this.d4.ar(4, b);
                    break;
                }
            }
        }
    }
    
    public final void dl() {
        for (int i = 0; i < this.eg; ++i) {
            switch (this.eh[i]) {
                case -1:
                case 0: {
                    this.d4.am();
                    break;
                }
                case 1:
                case 5: {
                    this.d4.ap(1, true);
                    break;
                }
                case 4: {
                    this.d4.ap(4, true);
                    break;
                }
                case 7: {
                    this.d4.ap(16, true);
                    break;
                }
                case 22: {
                    this.d4.ap(1, false);
                    break;
                }
                case 24: {
                    this.d4.ap(4, false);
                    break;
                }
                case 25: {
                    this.d4.ap(1, false);
                    break;
                }
                case 27: {
                    this.d4.ap(16, false);
                    break;
                }
                case 10: {
                    this.es[this.er] = 'B';
                    break;
                }
                case 11:
                case 12: {
                    this.es[this.er] = '0';
                    break;
                }
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37: {
                    this.d4.ao(this.eh[i] - 30);
                    break;
                }
                case 90:
                case 91:
                case 92:
                case 93:
                case 94:
                case 95:
                case 96:
                case 97: {
                    this.d4.ao(this.eh[i] - 90);
                    break;
                }
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47: {
                    this.d4.an(this.eh[i] - 40);
                    break;
                }
                case 100:
                case 101:
                case 102:
                case 103:
                case 104:
                case 105:
                case 106:
                case 107: {
                    this.d4.an(this.eh[i] - 100);
                    break;
                }
                case 39: {
                    this.d4.ao(-1);
                    break;
                }
                case 49: {
                    this.d4.an(-1);
                    break;
                }
            }
        }
    }
    
    public final void dk(final boolean b) {
        for (int i = 0; i < this.eg; ++i) {
            switch (this.eh[i]) {
                case 1: {
                    this.ej = b;
                    break;
                }
                case 2: {
                    if (b) {
                        this.de();
                        break;
                    }
                    break;
                }
                case 3: {
                    this.d4.ar(15, b);
                    break;
                }
                case 5: {
                    this.d4.ar(0, b);
                    break;
                }
                case 6: {
                    this.ek = b;
                    break;
                }
                case 7: {
                    this.d4.ar(1, b);
                    break;
                }
                case 9: {
                    if (b) {
                        this.ei = 1;
                        break;
                    }
                    this.ei = 0;
                    break;
                }
                case 25: {
                    this.d4.ar(9, b);
                    break;
                }
                case 40: {
                    this.d4.ar(16, b);
                    break;
                }
                case 45: {
                    this.d4.ar(2, b);
                    break;
                }
                case 47: {
                    this.df(b);
                    break;
                }
                case 1000: {
                    if (b) {
                        this.ei = 2;
                        break;
                    }
                    this.ei = 0;
                    break;
                }
                case 1001: {
                    if (b) {
                        this.ei = 3;
                        break;
                    }
                    this.ei = 0;
                    break;
                }
            }
        }
    }
    
    public final void dj() {
        switch (this.eh[0]) {
            case 0:
            case 1:
            case 2: {
                if (this.d4 instanceof h) {
                    this.d4.cc(this.en);
                    break;
                }
                break;
            }
        }
    }
    
    public final void di() {
        int i = 0;
        while (i < this.eg) {
            switch (this.eh[i]) {
                default: {
                    ++i;
                    continue;
                }
            }
        }
    }
    
    public final void dh() {
    }
    
    public final void dg() {
    }
    
    public final void df(final boolean b) {
    }
    
    public final void de() {
        this.es[0] = 'B';
        this.es[1] = '0';
        this.es[2] = 'B';
        this.es[3] = 'B';
        this.eq = 0;
    }
    
    public final void dd() {
        this.de();
        this.el = q.ef;
        this.ek = false;
        this.ej = false;
        this.ei = 0;
        if (this.d4 != null) {
            this.d4.bg();
            this.d4.a_();
            this.d4.bc(0, 0, false);
            this.d4.bl();
        }
    }
    
    public final int dc(final int n) {
        int n2;
        for (n2 = 0; n2 < 35 && this.e1[n2] != n; ++n2) {}
        return n2;
    }
    
    public final int db(final int n) {
        int n2 = 0;
        if ((n & 0x1) != 0x0) {
            n2 = 1;
        }
        if ((n & 0x2) != 0x0) {
            n2 += 2;
        }
        return n2;
    }
    
    public final String da(final int n, final int n2) {
        return q.eu[this.db(n2)][this.dc(n)][this.ez];
    }
    
    public final void c9(final int n, final int n2) {
        String s = null;
        String s2 = "";
        switch (n) {
            case 37:
            case 38:
            case 39:
            case 40: {
                if (this.ej) {
                    s2 = q.et[2];
                }
                else {
                    s2 = q.et[4];
                }
                s = this.da(n, n2);
                break;
            }
            case 33:
            case 34:
            case 35:
            case 36: {
                if (!this.d4.aq(7)) {
                    s2 = q.et[4];
                    s = this.da(n, n2);
                    break;
                }
                break;
            }
            case 155: {
                s2 = q.et[4];
                s = this.da(n, n2);
                break;
            }
            case 112:
            case 113:
            case 114:
            case 115:
            case 116:
            case 117:
            case 118:
            case 119:
            case 120:
            case 121:
            case 122:
            case 123: {
                if (this.ez == 7 || this.ez == 8 || this.ez == 3) {
                    s2 = q.et[2];
                }
                else if (this.ez == 6) {
                    if (n > 115) {
                        s2 = q.et[4];
                    }
                    else {
                        s2 = q.et[2];
                    }
                }
                else if (this.ez == 9) {
                    s2 = q.et[0];
                }
                else {
                    s2 = q.et[4];
                }
                s = this.da(n, n2);
                break;
            }
        }
        if (s != null) {
            this.d4.bt((String.valueOf(s2) + s).getBytes());
        }
    }
    
    public final int c8(final int n) {
        int n2 = 0;
        if ((n & 0x10) != 0x0) {
            n2 = 0;
        }
        else if ((n & 0x8) != 0x0) {
            n2 = 1;
        }
        else if ((n & 0x4) != 0x0) {
            n2 = 2;
        }
        return n2;
    }
    
    public final int c7(final int n) {
        int n2 = 0;
        if ((n & 0x1) != 0x0) {
            n2 |= 0x1;
        }
        if ((n & 0x8) != 0x0) {
            n2 |= 0x2;
        }
        if ((n & 0x2) != 0x0) {
            n2 |= 0x4;
        }
        return n2 << 2;
    }
    
    public final void c6(final int n, final int n2, final boolean b, final int n3) {
        switch (this.ei) {
            case 1: {
                if (b) {
                    this.d4.bt(("\u001b[M" + (char)(32 + this.c8(n3)) + (char)(32 + n2 + 1) + (char)(32 + n + 1)).getBytes());
                    break;
                }
                break;
            }
            case 2: {
                this.d4.bt(("\u001b[M" + (char)(32 + (b ? (this.c8(n3) | this.c7(n3)) : 3)) + (char)(32 + n2 + 1) + (char)(32 + n + 1)).getBytes());
                break;
            }
        }
    }
    
    static {
        e2 = new int[] { 32, 43, 58, 32, 32, 32, 32, 92, 35, 35, 35, 43, 43, 43, 43, 43, 126, 45, 45, 45, 95, 43, 43, 43, 43, 124, 60, 62, 42, 33, 102, 111, 62, 60, 94, 118 };
        e0 = new String[] { "xterm", "linux", "scoansi", "att6386", "sun", "aixterm", "vt220", "vt100", "ansi", "vt52", "xterm-color", "linux-lat", "", "at386", "", "", "vt320", "vt102" };
        ey = new String[][] { { "A", "A", "A", "A", "A", "A", "A", "A", "A", "A" }, { "B", "B", "B", "B", "B", "B", "B", "B", "B", "B" }, { "C", "C", "C", "C", "C", "C", "C", "C", "C", "C" }, { "D", "D", "D", "D", "D", "D", "D", "D", "D", "D" }, { "5~", "5~", "I", "V", "216z", "150q", "5~", "5~", "5~", "5~" }, { "6~", "6~", "G", "U", "222z", "154q", "6~", "6~", "6~", "6~" }, { "F", "4~", "F", "Y", "220z", "146q", "4~", "4~", "4~", "4~" }, { "H", "1~", "H", "H", "214z", "H", "1~", "1~", "1~", "1~" }, { "2~", "2~", "L", "@", "2~", "139q", "2~", "2~", "L", "L" }, { "11~", "[A", "M", "P", "224z", "001q", "P", "P", "P", "P" }, { "12~", "[B", "N", "Q", "225z", "002q", "Q", "Q", "Q", "Q" }, { "13~", "[C", "O", "R", "226z", "003q", "R", "R", "R", "R" }, { "14~", "[D", "P", "S", "227z", "004q", "S", "S", "S", "S" }, { "15~", "[E", "Q", "T", "228z", "005q", "17~", null, null, null }, { "17~", "17~", "R", "U", "229z", "006q", "18~", null, null, null }, { "18~", "18~", "S", "V", "230z", "007q", "19~", null, null, null }, { "19~", "19~", "T", "W", "231z", "008q", "20~", null, null, null }, { "20~", "20~", "U", "X", "232z", "009q", "21~", null, null, null }, { "21~", "21~", "V", "Y", "233z", "010q", "29~", null, null, null }, { "23~", "23~", null, "Z", "234z", "011q", null, null, null, null }, { "24~", "24~", null, "A", "235z", "012q", null, null, null, null } };
        ex = new String[][] { { "A", "A", "A", "A", "A", "A", "A", "A", "A", "A" }, { "B", "B", "B", "B", "B", "B", "B", "B", "B", "B" }, { "C", "C", "C", "C", "C", "C", "C", "C", "C", "C" }, { "D", "D", "D", "D", "D", "D", "D", "D", "D", "D" }, { "5~", "5~", "I", "V", "216z", "150q", "5~", "5~", "5~", "5~" }, { "6~", "6~", "G", "U", "222z", "154q", "6~", "6~", "6~", "6~" }, { "4~", "4~", "F", "Y", "220z", "146q", "4~", "4~", "4~", "4~" }, { "@", "1~", "H", "H", "214z", "H", "1~", "1~", "1~", "1~" }, { "2~", "2~", "L", "@", "2~", "139q", "2~", "2~", "L", "L" }, { "23~", "23~", "M", "P", "224z", "013q", "P", "P", "P", "P" }, { "24~", "24~", "N", "Q", "225z", "014q", "Q", "Q", "Q", "Q" }, { "25~", "25~", "O", "R", "226z", "015q", "R", "R", "R", "R" }, { "26~", "26~", "P", "S", "227z", "016q", "S", "S", "S", "S" }, { "28~", "28~", "Q", "T", "228z", "017q", "17~", null, null, null }, { "29~", "29~", "R", "U", "229z", "018q", "18~", null, null, null }, { "31~", "31~", "S", "V", "230z", "019q", "19~", null, null, null }, { "32~", "32~", "T", "W", "231z", "020q", "20~", null, null, null }, { "33~", "33~", "U", "X", "232z", "021q", "21~", null, null, null }, { "34~", "34~", "V", "Y", "233z", "022q", "29~", null, null, null }, { "23$", null, null, "Z", "234z", "023q", null, null, null, null }, { "24$", null, null, "A", "235z", "024q", null, null, null, null } };
        ew = new String[][] { { "A", "A", "A", "A", "A", "A", "A", "A", "A", "A" }, { "B", "B", "B", "B", "B", "B", "B", "B", "B", "B" }, { "C", "C", "C", "C", "C", "C", "C", "C", "C", "C" }, { "D", "D", "D", "D", "D", "D", "D", "D", "D", "D" }, { "5~", "5~", "I", "V", "216z", "150q", "5~", "5~", "5~", "5~" }, { "6~", "6~", "G", "U", "222z", "154q", "6~", "6~", "6~", "6~" }, { "4~", "4~", "F", "Y", "220z", "146q", "4~", "4~", "4~", "4~" }, { "@", "1~", "H", "H", "214z", "H", "1~", "1~", "1~", "1~" }, { "2~", "2~", "L", "@", "2~", "139q", "2~", "2~", "L", "L" }, { "11^", null, "M", "P", "224z", "025q", "P", "P", "P", "P" }, { "12^", null, "N", "Q", "225z", "026q", "Q", "Q", "Q", "Q" }, { "13^", null, "O", "R", "226z", "027q", "R", "R", "R", "R" }, { "14^", null, "P", "S", "227z", "028q", "S", "S", "S", "S" }, { "15^", null, "Q", "T", "228z", "029q", "17~", null, null, null }, { "17^", null, "R", "U", "229z", "030q", "18~", null, null, null }, { "18^", null, "S", "V", "230z", "031q", "19~", null, null, null }, { "19^", null, "T", "W", "231z", "032q", "20~", null, null, null }, { "20^", null, "U", "X", "232z", "033q", "21~", null, null, null }, { "21^", null, "V", "Y", "233z", "034q", "29~", null, null, null }, { "23^", null, null, "Z", "234z", "035q", null, null, null, null }, { "24^", null, null, "A", "235z", "036q", null, null, null, null } };
        ev = new String[][] { { "A", "A", "A", "A", "A", "A", "A", "A", "A", "A" }, { "B", "B", "B", "B", "B", "B", "B", "B", "B", "B" }, { "C", "C", "C", "C", "C", "C", "C", "C", "C", "C" }, { "D", "D", "D", "D", "D", "D", "D", "D", "D", "D" }, { "5~", "5~", "I", "V", "216z", "150q", "5~", "5~", "5~", "5~" }, { "6~", "6~", "G", "U", "222z", "154q", "6~", "6~", "6~", "6~" }, { "4~", "4~", "F", "Y", "220z", "146q", "4~", "4~", "4~", "4~" }, { "@", "1~", "H", "H", "214z", "H", "1~", "1~", "1~", "1~" }, { "2~", "2~", "L", "@", "2~", "139q", "2~", "2~", "L", "L" }, { "23^", null, "M", "P", "224z", "001q", "P", "P", "P", "P" }, { "24^", null, "N", "Q", "225z", "002q", "Q", "Q", "Q", "Q" }, { "25^", null, "O", "R", "226z", "003q", "R", "R", "R", "R" }, { "26^", null, "P", "S", "227z", "004q", "S", "S", "S", "S" }, { "28^", null, "Q", "T", "228z", "005q", "17~", null, null, null }, { "29^", null, "R", "U", "229z", "006q", "18~", null, null, null }, { "31^", null, "S", "V", "230z", "007q", "19~", null, null, null }, { "32^", null, "T", "W", "231z", "008q", "20~", null, null, null }, { "33^", null, "U", "X", "232z", "009q", "21~", null, null, null }, { "34^", null, "V", "Y", "233z", "010q", "29~", null, null, null }, { "23@", null, null, "Z", "234z", "011q", null, null, null, null }, { "24@", null, null, "A", "235z", "012q", null, null, null, null } };
        eu = new String[][][] { q.ey, q.ex, q.ew, q.ev };
        et = new String[] { "\u001b", "\u001bN", "\u001bO", "\u001bP", "\u001b[", "\u001b]", "\u001b^", "\u001b_" };
        ef = new int[] { 63, 63, 63, 63, 63, 74, 63, 4, 5, 9, 8, 8, 8, 6, 11, 10, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 7, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 0, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63, 63 };
        ee = new int[] { 3, 3, 3, 3, 3, 3, 3, 4, 5, 9, 8, 8, 8, 6, 11, 10, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 7, 3, 3, 3, 3, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 3, 19, 3, 3, 3, 20, 21, 22, 23, 24, 25, 76, 77, 78, 26, 79, 27, 28, 29, 30, 0, 0, 31, 0, 0, 80, 81, 0, 0, 0, 82, 0, 83, 0, 0, 0, 81, 0, 84, 0, 85, 32, 86, 0, 26, 34, 35, 0, 0, 0, 36, 37, 38, 0, 0, 0, 39, 45, 75, 46, 0, 0, 40, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        ed = new int[] { 3, 3, 3, 3, 3, 3, 3, 4, 5, 9, 8, 8, 8, 6, 11, 10, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 7, 3, 3, 3, 3, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 3, 19, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 41, 0, 0, 0, 42, 0, 0, 0, 0, 0, 65, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        ec = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 5, 9, 8, 8, 8, 6, 11, 10, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 7, 3, 3, 3, 3, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 3, 72, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 41, 0, 0, 0, 42, 0, 0, 0, 0, 0, 65, 64, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        eb = new int[] { 71, 71, 71, 71, 71, 71, 71, 73, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71, 71 };
        ea = new int[] { 3, 3, 3, 3, 3, 3, 3, 4, 5, 9, 8, 8, 8, 6, 11, 10, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 7, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        d9 = new int[] { 3, 3, 3, 3, 3, 3, 3, 4, 5, 9, 8, 8, 8, 6, 11, 10, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 7, 3, 3, 3, 3, 17, 17, 17, 12, 17, 17, 17, 17, 13, 14, 15, 16, 17, 17, 17, 17, 0, 0, 0, 0, 0, 0, 0, 45, 46, 0, 0, 0, 0, 47, 48, 0, 0, 0, 0, 0, 49, 50, 70, 0, 51, 0, 0, 0, 0, 52, 53, 54, 1, 0, 0, 0, 66, 0, 0, 0, 0, 0, 67, 55, 0, 56, 1, 1, 0, 0, 0, 57, 0, 0, 0, 0, 0, 0, 0, 0, 68, 69, 58, 59, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 60, 61, 62, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        d8 = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        d7 = new int[] { 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 3, 0, 2, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        d6 = new int[] { 3, 3, 3, 3, 3, 3, 3, 4, 5, 9, 8, 8, 8, 6, 11, 10, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 7, 3, 3, 3, 3, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 0, 0, 0, 0, 0, 0, 0, 0, 43, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        d5 = new int[] { 3, 3, 3, 3, 3, 3, 3, 4, 5, 9, 8, 8, 8, 6, 11, 10, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 7, 3, 3, 3, 3, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 17, 44, 44, 44, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 44, 44, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    }
}
