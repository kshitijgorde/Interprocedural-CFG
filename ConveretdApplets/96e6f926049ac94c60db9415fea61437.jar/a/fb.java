// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.nb;
import neat.lb;
import neat.kb;
import neat.system.f;
import neat.s;

public class fb extends s
{
    private static int z;
    private static int[] B;
    private static int[] C;
    private static int[] D;
    private static f E;
    protected long F;
    protected int G;
    private long H;
    private int I;
    private int J;
    private int K;
    private int L;
    private int M;
    private int N;
    private int O;
    private int P;
    private kb Q;
    private static /* synthetic */ Class R;
    private static String[] S;
    
    public void b(final kb kb) {
        if (this.Q != null) {
            this.Q.f();
            this.Q = null;
        }
        if (kb != null) {
            this.Q = kb.b();
        }
    }
    
    public void c() {
        final lb a = lb.a();
        try {
            this.a(a);
        }
        catch (Throwable t) {
            a.c(0);
            a.c(fb.S[10]);
            a.c(nb.a(t));
        }
        final kb b = a.b();
        final kb a2 = kb.a(fb.S[11]);
        this.a(a2, b);
        a2.f();
        b.f();
    }
    
    public void b(final int n) {
        if (this.F < 0L) {
            this.F = System.currentTimeMillis();
        }
        this.G += n;
        if (this.G > this.P) {
            if (this.P < 0) {
                this.O = 0;
                this.P = 0;
            }
            else {
                if (++this.O >= fb.D.length) {
                    this.O = fb.D.length - 1;
                }
                this.c();
            }
            final int n2 = fb.D[this.O] * 1000;
            int n3;
            if (n2 >= 0) {
                n3 = n2 * 60;
            }
            else {
                n3 = -n2;
            }
            this.P += n3;
        }
    }
    
    public void d() {
        ++this.I;
        final long currentTimeMillis = System.currentTimeMillis();
        if (this.H < 0L) {
            this.H = currentTimeMillis;
        }
        final int n = (int)(currentTimeMillis - this.H);
        if (n >= fb.z) {
            final int n2 = this.I * 1000 / n;
            if (n2 < this.L || this.L < 0) {
                this.L = n2;
            }
            else if (n2 > this.N || this.N < 0) {
                this.N = n2;
            }
            this.J += this.I;
            this.K += n;
            this.M = this.J * 1000 / this.K;
            this.I = 0;
            this.H = currentTimeMillis;
        }
    }
    
    public void e() {
        this.H = -1L;
        this.I = 0;
        this.J = 0;
        this.K = 0;
        this.L = -1;
        this.N = -1;
        this.M = -1;
    }
    
    public void a(final boolean b) {
        super.a(b);
        if (b) {
            fb.D = fb.B;
        }
        else {
            fb.D = fb.C;
        }
    }
    
    protected void a(final lb lb) {
        super.a(lb);
        final kb a = nb.a((int)(System.currentTimeMillis() - this.F), true);
        lb.c(fb.S[0]);
        lb.a(a);
        a.f();
        final kb a2 = nb.a(this.G, true);
        lb.c(fb.S[7]);
        lb.a(a2);
        a2.f();
        lb.c(fb.S[3]);
        lb.d(this.M);
        lb.c(fb.S[4]);
        lb.d(this.L);
        lb.c(fb.S[8]);
        lb.d(this.N);
        lb.c(fb.S[1]);
        lb.c(fb.S[5]);
        lb.a(Runtime.getRuntime().freeMemory());
        lb.c(fb.S[9]);
        lb.a(Runtime.getRuntime().totalMemory());
        lb.c(fb.S[6]);
        lb.d(neat.system.f.a());
        lb.c("]");
        if (this.Q != null) {
            lb.c(fb.S[2]);
            lb.a(this.Q);
            lb.c("]");
        }
        lb.c("\n");
    }
    
    public static fb f() {
        return (fb)fb.E.a();
    }
    
    public void f() {
        fb.E.a(this);
    }
    
    public void g() {
        super.g();
        this.F = -1L;
        this.G = 0;
        this.e();
        this.O = 0;
        this.P = -1;
    }
    
    public void h() {
        if (this.Q != null) {
            this.Q.f();
            this.Q = null;
        }
        super.h();
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public fb() {
        this.Q = null;
    }
    
    static {
        final String[] s = new String[13];
        final int n = 0;
        final char[] charArray = "jK\u0010AEEE\u001cI\u001d".toCharArray();
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
                            c2 = '1';
                            break;
                        }
                        case 1: {
                            c2 = ',';
                            break;
                        }
                        case 2: {
                            c2 = 'q';
                            break;
                        }
                        case 3: {
                            c2 = ',';
                            break;
                        }
                        default: {
                            c2 = ' ';
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
        s[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "l&".toCharArray();
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
                            c4 = '1';
                            break;
                        }
                        case 1: {
                            c4 = ',';
                            break;
                        }
                        case 2: {
                            c4 = 'q';
                            break;
                        }
                        case 3: {
                            c4 = ',';
                            break;
                        }
                        default: {
                            c4 = ' ';
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
        s[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "jZ\u0014^\u001d".toCharArray();
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
                            c6 = '1';
                            break;
                        }
                        case 1: {
                            c6 = ',';
                            break;
                        }
                        case 2: {
                            c6 = 'q';
                            break;
                        }
                        case 3: {
                            c6 = ',';
                            break;
                        }
                        default: {
                            c6 = ' ';
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
        s[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "lw\u0017\\S\f".toCharArray();
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
                            c8 = '1';
                            break;
                        }
                        case 1: {
                            c8 = ',';
                            break;
                        }
                        case 2: {
                            c8 = 'q';
                            break;
                        }
                        case 3: {
                            c8 = ',';
                            break;
                        }
                        default: {
                            c8 = ' ';
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
        s[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "lw\u0017\\S\\E\u001f\u0011".toCharArray();
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
                            c10 = '1';
                            break;
                        }
                        case 1: {
                            c10 = ',';
                            break;
                        }
                        case 2: {
                            c10 = 'q';
                            break;
                        }
                        case 3: {
                            c10 = ',';
                            break;
                        }
                        default: {
                            c10 = ' ';
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
        s[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "jJ\u001cIM\f".toCharArray();
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
                            c12 = '1';
                            break;
                        }
                        case 1: {
                            c12 = ',';
                            break;
                        }
                        case 2: {
                            c12 = 'q';
                            break;
                        }
                        case 3: {
                            c12 = ',';
                            break;
                        }
                        default: {
                            c12 = ' ';
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
        s[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "lw\u0003C\u001d".toCharArray();
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
                            c14 = '1';
                            break;
                        }
                        case 1: {
                            c14 = ',';
                            break;
                        }
                        case 2: {
                            c14 = 'q';
                            break;
                        }
                        case 3: {
                            c14 = ',';
                            break;
                        }
                        default: {
                            c14 = ' ';
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
        s[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "lw\u0016MMTX\u0005\u0011".toCharArray();
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
                            c16 = '1';
                            break;
                        }
                        case 1: {
                            c16 = ',';
                            break;
                        }
                        case 2: {
                            c16 = 'q';
                            break;
                        }
                        case 3: {
                            c16 = ',';
                            break;
                        }
                        default: {
                            c16 = ' ';
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
        s[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "lw\u0017\\S\\M\t\u0011".toCharArray();
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
                            c18 = '1';
                            break;
                        }
                        case 1: {
                            c18 = ',';
                            break;
                        }
                        case 2: {
                            c18 = 'q';
                            break;
                        }
                        case 3: {
                            c18 = ',';
                            break;
                        }
                        default: {
                            c18 = ' ';
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
        s[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "lw\u0005AE\\\u0011".toCharArray();
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
                            c20 = '1';
                            break;
                        }
                        case 1: {
                            c20 = ',';
                            break;
                        }
                        case 2: {
                            c20 = 'q';
                            break;
                        }
                        case 3: {
                            c20 = ',';
                            break;
                        }
                        default: {
                            c20 = ' ';
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
        s[n37] = new String(charArray10).intern();
        final int n41 = 10;
        final char[] charArray11 = "jI\t\u0011i_\f\u001dCG\u000b".toCharArray();
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
                            c22 = '1';
                            break;
                        }
                        case 1: {
                            c22 = ',';
                            break;
                        }
                        case 2: {
                            c22 = 'q';
                            break;
                        }
                        case 3: {
                            c22 = ',';
                            break;
                        }
                        default: {
                            c22 = ' ';
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
        s[n41] = new String(charArray11).intern();
        final int n45 = 11;
        final char[] charArray12 = "vM\u001cI\u0000BX\u0010XEB".toCharArray();
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
                            c24 = '1';
                            break;
                        }
                        case 1: {
                            c24 = ',';
                            break;
                        }
                        case 2: {
                            c24 = 'q';
                            break;
                        }
                        case 3: {
                            c24 = ',';
                            break;
                        }
                        default: {
                            c24 = ' ';
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
        s[n45] = new String(charArray12).intern();
        final int n49 = 12;
        final char[] charArray13 = "P\u0002\u0017N".toCharArray();
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
                            c26 = '1';
                            break;
                        }
                        case 1: {
                            c26 = ',';
                            break;
                        }
                        case 2: {
                            c26 = 'q';
                            break;
                        }
                        case 3: {
                            c26 = ',';
                            break;
                        }
                        default: {
                            c26 = ' ';
                            break;
                        }
                    }
                    charArray13[length13] = (char)(c25 ^ c26);
                    ++n52;
                } while (n50 == 0);
            }
            if (n50 <= n52) {
                s[n49] = new String(charArray13).intern();
                fb.S = s;
                fb.z = 2000;
                fb.B = new int[] { -20, 1, 2, 2, 5, 5, 10 };
                fb.C = new int[] { 2, 10, 30 };
                fb.D = fb.C;
                fb.E = new f((fb.R != null) ? fb.R : (fb.R = a(fb.S[12])));
                return;
            }
            continue;
        }
    }
}
