// 
// Decompiled by Procyon v0.5.30
// 

package a.a.c;

import a.b.f.e;
import java.io.IOException;
import a.b.e.d;
import org.a.a.c;
import a.b.i.h;
import a.a.b.a.a;
import java.util.Properties;
import org.a.c.f;
import a.b.e.g;

public class b implements g
{
    private static f a;
    private Properties b;
    private a c;
    private a.b.i.h d;
    private a.b.k.h e;
    private String f;
    private String g;
    private c h;
    private d i;
    private String j;
    private int k;
    private int l;
    private int m;
    private a.b.n.d n;
    private int o;
    private static String[] z;
    
    public b() {
        this.n = a.b.g.b.a().d();
        a.a.c.b.a = this.n.a(this.getClass().getName());
        try {
            this.b = a.a.c.c.a();
        }
        catch (IOException ex) {
            a.a.c.b.a.d(a.a.c.b.z[11], ex);
            this.i = new d(a.a.c.b.z[14], ex);
        }
        this.c = a.a.b.a.a.c();
        int int1 = -1;
        String property = null;
        int int2 = 0;
        if (this.b != null) {
            this.f = this.b.getProperty(a.a.c.b.z[9]);
            this.g = this.b.getProperty(a.a.c.b.z[13]);
            this.j = this.b.getProperty(a.a.c.b.z[8]);
            property = this.b.getProperty(a.a.c.b.z[10]);
            final String property2 = this.b.getProperty(a.a.c.b.z[15]);
            try {
                this.k = Integer.parseInt(property2);
            }
            catch (NumberFormatException ex2) {
                this.k = -1;
            }
            final String property3 = this.b.getProperty(a.a.c.b.z[18]);
            try {
                this.l = Integer.parseInt(property3);
            }
            catch (NumberFormatException ex3) {
                this.l = 6000;
            }
            final String property4 = this.b.getProperty(a.a.c.b.z[17]);
            try {
                this.m = Integer.parseInt(property4);
            }
            catch (NumberFormatException ex4) {
                this.m = 15000;
            }
            final String property5 = this.b.getProperty(a.a.c.b.z[12]);
            try {
                int1 = Integer.parseInt(property5);
            }
            catch (NumberFormatException ex5) {
                int1 = -1;
            }
            final String property6 = this.b.getProperty(a.a.c.b.z[16]);
            try {
                int2 = Integer.parseInt(property6);
            }
            catch (NumberFormatException ex6) {
                int2 = 4000;
            }
            final String property7 = this.b.getProperty(a.a.c.b.z[19]);
            try {
                this.o = Integer.parseInt(property7);
            }
            catch (NumberFormatException ex7) {
                this.o = 99;
            }
        }
        if (this.f != null && this.g != null && property != null && int1 > -1) {
            final long currentTimeMillis = System.currentTimeMillis();
            boolean a = false;
            while (!a && System.currentTimeMillis() - currentTimeMillis < int2) {
                a = this.c.a();
                if (!a) {
                    Thread.currentThread();
                    Thread.yield();
                }
            }
            String s = "";
            try {
                s = new String(org.a.f.a.a.a(this.c.a(int1)));
            }
            catch (IOException ex8) {}
            (this.h = new c()).a(property);
            this.h.d(this.f);
            this.h.c(this.g);
            this.h.b(s);
        }
        this.d = new a.a.e.b(this.c);
        this.e = new a.a.d.b(this.c);
    }
    
    public a.b.i.h a() {
        return this.d;
    }
    
    public a.b.k.h b() {
        return this.e;
    }
    
    public String a() throws d {
        if (this.f != null) {
            return this.f;
        }
        if (this.i != null) {
            throw new d(a.a.c.b.z[5], this.i);
        }
        throw new d(a.a.c.b.z[4]);
    }
    
    public String b() throws d {
        if (this.g != null) {
            return this.g;
        }
        if (this.i != null) {
            throw new d(a.a.c.b.z[0], this.i);
        }
        throw new d(a.a.c.b.z[1]);
    }
    
    public int c() throws d {
        if (this.k != -1) {
            return this.k;
        }
        if (this.i != null) {
            throw new d(a.a.c.b.z[20], this.i);
        }
        throw new d(a.a.c.b.z[21]);
    }
    
    public String f() throws d {
        if (this.j != null) {
            return this.j;
        }
        if (this.i != null) {
            throw new d(a.a.c.b.z[7], this.i);
        }
        throw new d(a.a.c.b.z[6]);
    }
    
    public c[] g() throws d {
        if (this.h != null) {
            return new c[] { this.h };
        }
        if (this.i != null) {
            throw new d(a.a.c.b.z[2], this.i);
        }
        throw new d(a.a.c.b.z[3]);
    }
    
    public int d() {
        return this.l;
    }
    
    public int e() {
        return this.m;
    }
    
    public int f() {
        return this.o;
    }
    
    static {
        final String[] z = new String[22];
        final int n = 0;
        final char[] charArray = "\u0019?Gp88 \u0002R>>5\u0014\u0000,><\u0002\u0000>8p\u0015E+3p\u0015E;\"5\u0014Tj'1\u0013Hd".toCharArray();
        final int i = charArray.length;
        for (int n2 = 0; i > n2; ++n2) {
            final int n3 = n2;
            final char c = charArray[n3];
            char c2 = '\0';
            switch (n2 % 5) {
                case 0: {
                    c2 = 'W';
                    break;
                }
                case 1: {
                    c2 = 'P';
                    break;
                }
                case 2: {
                    c2 = 'g';
                    break;
                }
                case 3: {
                    c2 = ' ';
                    break;
                }
                default: {
                    c2 = 'J';
                    break;
                }
            }
            charArray[n3] = (char)(c ^ c2);
        }
        z[n] = new String(charArray).intern();
        final int n4 = 1;
        final char[] charArray2 = "\u00038\u0002\u0000!2)Gd#$ \u000bA3\u00131\u0013Ad\u0016 \u0017\u0000#$p\tO>w4\u0002F#95\u0003\u0000#9p\u0013H/w \u0015O:2\"\u0013I/$p\u0001I&2~".toCharArray();
        final int j = charArray2.length;
        for (int n5 = 0; j > n5; ++n5) {
            final int n6 = n5;
            final char c3 = charArray2[n6];
            char c4 = '\0';
            switch (n5 % 5) {
                case 0: {
                    c4 = 'W';
                    break;
                }
                case 1: {
                    c4 = 'P';
                    break;
                }
                case 2: {
                    c4 = 'g';
                    break;
                }
                case 3: {
                    c4 = ' ';
                    break;
                }
                default: {
                    c4 = 'J';
                    break;
                }
            }
            charArray2[n6] = (char)(c3 ^ c4);
        }
        z[n4] = new String(charArray2).intern();
        final int n7 = 2;
        final char[] charArray3 = "\u0019?Gp88 \u0002R>>5\u0014\u0000,><\u0002\u0000>8p\u0015E+3p\u0004O%<9\u0002\u0000.6$\u0006\u000e".toCharArray();
        final int k = charArray3.length;
        for (int n8 = 0; k > n8; ++n8) {
            final int n9 = n8;
            final char c5 = charArray3[n9];
            char c6 = '\0';
            switch (n8 % 5) {
                case 0: {
                    c6 = 'W';
                    break;
                }
                case 1: {
                    c6 = 'P';
                    break;
                }
                case 2: {
                    c6 = 'g';
                    break;
                }
                case 3: {
                    c6 = ' ';
                    break;
                }
                default: {
                    c6 = 'J';
                    break;
                }
            }
            charArray3[n9] = (char)(c5 ^ c6);
        }
        z[n7] = new String(charArray3).intern();
        final int n10 = 3;
        final char[] charArray4 = "\u0016p\tE/35\u0003\u0000)8?\fI/w;\u0002Yj\u007f\u0013\bN$23\u0013I%9~$O%<9\u0002\u000e\u00192#\u0014I%9\u001b\u0002Yfw\u0014\u000eS:;1\u001ed+#1Ia.3\"\u0002S9{p\bRj\u00139\u0014P&6)#A>6~&P:~p\u000eSj9?\u0013\u0000.26\u000eN/3p\u000eNj#8\u0002\u0000:%?\u0017E8#9\u0002Sj19\u000bEd".toCharArray();
        final int l = charArray4.length;
        for (int n11 = 0; l > n11; ++n11) {
            final int n12 = n11;
            final char c7 = charArray4[n12];
            char c8 = '\0';
            switch (n11 % 5) {
                case 0: {
                    c8 = 'W';
                    break;
                }
                case 1: {
                    c8 = 'P';
                    break;
                }
                case 2: {
                    c8 = 'g';
                    break;
                }
                case 3: {
                    c8 = ' ';
                    break;
                }
                default: {
                    c8 = 'J';
                    break;
                }
            }
            charArray4[n12] = (char)(c7 ^ c8);
        }
        z[n10] = new String(charArray4).intern();
        final int n13 = 4;
        final char[] charArray5 = "\u00038\u0002\u0000!2)Gd#$ \u000bA3\u00131\u0013Ad\u00164\u0003R/$#GI9w>\bTj35\u0001I$24GI$w$\u000fEj'\"\bP/%$\u000eE9w6\u000eL/y".toCharArray();
        final int length = charArray5.length;
        for (int n14 = 0; length > n14; ++n14) {
            final int n15 = n14;
            final char c9 = charArray5[n15];
            char c10 = '\0';
            switch (n14 % 5) {
                case 0: {
                    c10 = 'W';
                    break;
                }
                case 1: {
                    c10 = 'P';
                    break;
                }
                case 2: {
                    c10 = 'g';
                    break;
                }
                case 3: {
                    c10 = ' ';
                    break;
                }
                default: {
                    c10 = 'J';
                    break;
                }
            }
            charArray5[n15] = (char)(c9 ^ c10);
        }
        z[n13] = new String(charArray5).intern();
        final int n16 = 5;
        final char[] charArray6 = "\u0019?Gp88 \u0002R>>5\u0014\u0000,><\u0002\u0000>8p\u0015E+3p\u0015E;\"5\u0014Tj??\u0014Td".toCharArray();
        final int length2 = charArray6.length;
        for (int n17 = 0; length2 > n17; ++n17) {
            final int n18 = n17;
            final char c11 = charArray6[n18];
            char c12 = '\0';
            switch (n17 % 5) {
                case 0: {
                    c12 = 'W';
                    break;
                }
                case 1: {
                    c12 = 'P';
                    break;
                }
                case 2: {
                    c12 = 'g';
                    break;
                }
                case 3: {
                    c12 = ' ';
                    break;
                }
                default: {
                    c12 = 'J';
                    break;
                }
            }
            charArray6[n18] = (char)(c11 ^ c12);
        }
        z[n16] = new String(charArray6).intern();
        final int n19 = 6;
        final char[] charArray7 = "\u00038\u0002\u0000!2)Gc%9>\u0002C>>?\t\u000e\u00182!\u0012E9#~/E+35\u0015\u0000#$p\tO>w4\u0002F#95\u0003\u0000#9p\u0013H/w \u0015O:2\"\u0013I/$p\u0001I&2~".toCharArray();
        final int length3 = charArray7.length;
        for (int n20 = 0; length3 > n20; ++n20) {
            final int n21 = n20;
            final char c13 = charArray7[n21];
            char c14 = '\0';
            switch (n20 % 5) {
                case 0: {
                    c14 = 'W';
                    break;
                }
                case 1: {
                    c14 = 'P';
                    break;
                }
                case 2: {
                    c14 = 'g';
                    break;
                }
                case 3: {
                    c14 = ' ';
                    break;
                }
                default: {
                    c14 = 'J';
                    break;
                }
            }
            charArray7[n21] = (char)(c13 ^ c14);
        }
        z[n19] = new String(charArray7).intern();
        final int n22 = 7;
        final char[] charArray8 = "\u0019?Gp88 \u0002R>>5\u0014\u0000,><\u0002\u0000>8p\u0015E+3p\u0013H/w\"\u0002Q?2#\u0013\u0000\"21\u0003E8y".toCharArray();
        final int length4 = charArray8.length;
        for (int n23 = 0; length4 > n23; ++n23) {
            final int n24 = n23;
            final char c15 = charArray8[n24];
            char c16 = '\0';
            switch (n23 % 5) {
                case 0: {
                    c16 = 'W';
                    break;
                }
                case 1: {
                    c16 = 'P';
                    break;
                }
                case 2: {
                    c16 = 'g';
                    break;
                }
                case 3: {
                    c16 = ' ';
                    break;
                }
                default: {
                    c16 = 'J';
                    break;
                }
            }
            charArray8[n24] = (char)(c15 ^ c16);
        }
        z[n22] = new String(charArray8).intern();
        final int n25 = 8;
        final char[] charArray9 = "\u0014?\tN/4$\u000eO$y\u0002\u0002Q?2#\u0013\u000e\u000221\u0003E8".toCharArray();
        final int length5 = charArray9.length;
        for (int n26 = 0; length5 > n26; ++n26) {
            final int n27 = n26;
            final char c17 = charArray9[n27];
            char c18 = '\0';
            switch (n26 % 5) {
                case 0: {
                    c18 = 'W';
                    break;
                }
                case 1: {
                    c18 = 'P';
                    break;
                }
                case 2: {
                    c18 = 'g';
                    break;
                }
                case 3: {
                    c18 = ' ';
                    break;
                }
                default: {
                    c18 = 'J';
                    break;
                }
            }
            charArray9[n27] = (char)(c17 ^ c18);
        }
        z[n25] = new String(charArray9).intern();
        final int n28 = 9;
        final char[] charArray10 = "\u00139\u0014P&6)#A>6~&D.%5\u0014S".toCharArray();
        final int length6 = charArray10.length;
        for (int n29 = 0; length6 > n29; ++n29) {
            final int n30 = n29;
            final char c19 = charArray10[n30];
            char c20 = '\0';
            switch (n29 % 5) {
                case 0: {
                    c20 = 'W';
                    break;
                }
                case 1: {
                    c20 = 'P';
                    break;
                }
                case 2: {
                    c20 = 'g';
                    break;
                }
                case 3: {
                    c20 = ' ';
                    break;
                }
                default: {
                    c20 = 'J';
                    break;
                }
            }
            charArray10[n30] = (char)(c19 ^ c20);
        }
        z[n28] = new String(charArray10).intern();
        final int n31 = 10;
        final char[] charArray11 = "\u0014?\tN/4$\u000eO$y\u0013\bO!>5Is/$#\u000eO$\u001c5\u001e".toCharArray();
        final int length7 = charArray11.length;
        for (int n32 = 0; length7 > n32; ++n32) {
            final int n33 = n32;
            final char c21 = charArray11[n33];
            char c22 = '\0';
            switch (n32 % 5) {
                case 0: {
                    c22 = 'W';
                    break;
                }
                case 1: {
                    c22 = 'P';
                    break;
                }
                case 2: {
                    c22 = 'g';
                    break;
                }
                case 3: {
                    c22 = ' ';
                    break;
                }
                default: {
                    c22 = 'J';
                    break;
                }
            }
            charArray11[n33] = (char)(c21 ^ c22);
        }
        z[n31] = new String(charArray11).intern();
        final int n34 = 11;
        final char[] charArray12 = "\u001f9\u0000H&6>\u0003E8\u0014<\u000eE$#\u0013\bO839\tA>8\"7A86=\u0002T/%#]\u0000\u000f%\"\bRj;?\u0006D#97GP88 \u0002R>>5\u0014\u000e".toCharArray();
        final int length8 = charArray12.length;
        for (int n35 = 0; length8 > n35; ++n35) {
            final int n36 = n35;
            final char c23 = charArray12[n36];
            char c24 = '\0';
            switch (n35 % 5) {
                case 0: {
                    c24 = 'W';
                    break;
                }
                case 1: {
                    c24 = 'P';
                    break;
                }
                case 2: {
                    c24 = 'g';
                    break;
                }
                case 3: {
                    c24 = ' ';
                    break;
                }
                default: {
                    c24 = 'J';
                    break;
                }
            }
            charArray12[n36] = (char)(c23 ^ c24);
        }
        z[n34] = new String(charArray12).intern();
        final int n37 = 12;
        final char[] charArray13 = "\u0014?\tN/4$\u000eO$y\u0013\bO!>5Is/$#\u000eO$\u00049\u001dE".toCharArray();
        final int length9 = charArray13.length;
        for (int n38 = 0; length9 > n38; ++n38) {
            final int n39 = n38;
            final char c25 = charArray13[n39];
            char c26 = '\0';
            switch (n38 % 5) {
                case 0: {
                    c26 = 'W';
                    break;
                }
                case 1: {
                    c26 = 'P';
                    break;
                }
                case 2: {
                    c26 = 'g';
                    break;
                }
                case 3: {
                    c26 = ' ';
                    break;
                }
                default: {
                    c26 = 'J';
                    break;
                }
            }
            charArray13[n39] = (char)(c25 ^ c26);
        }
        z[n37] = new String(charArray13).intern();
        final int n40 = 13;
        final char[] charArray14 = "\u00139\u0014P&6)#A>6~&P:".toCharArray();
        final int length10 = charArray14.length;
        for (int n41 = 0; length10 > n41; ++n41) {
            final int n42 = n41;
            final char c27 = charArray14[n42];
            char c28 = '\0';
            switch (n41 % 5) {
                case 0: {
                    c28 = 'W';
                    break;
                }
                case 1: {
                    c28 = 'P';
                    break;
                }
                case 2: {
                    c28 = 'g';
                    break;
                }
                case 3: {
                    c28 = ' ';
                    break;
                }
                default: {
                    c28 = 'J';
                    break;
                }
            }
            charArray14[n42] = (char)(c27 ^ c28);
        }
        z[n40] = new String(charArray14).intern();
        final int n43 = 14;
        final char[] charArray15 = "\u0002>\u0006B&2p\u0013Oj;?\u0006Dj4?\tF#0%\u0015A>>?\tSd".toCharArray();
        final int length11 = charArray15.length;
        for (int n44 = 0; length11 > n44; ++n44) {
            final int n45 = n44;
            final char c29 = charArray15[n45];
            char c30 = '\0';
            switch (n44 % 5) {
                case 0: {
                    c30 = 'W';
                    break;
                }
                case 1: {
                    c30 = 'P';
                    break;
                }
                case 2: {
                    c30 = 'g';
                    break;
                }
                case 3: {
                    c30 = ' ';
                    break;
                }
                default: {
                    c30 = 'J';
                    break;
                }
            }
            charArray15[n45] = (char)(c29 ^ c30);
        }
        z[n43] = new String(charArray15).intern();
        final int n46 = 15;
        final char[] charArray16 = "\u00139\u0014P&6)#A>6~7O8#".toCharArray();
        final int length12 = charArray16.length;
        for (int n47 = 0; length12 > n47; ++n47) {
            final int n48 = n47;
            final char c31 = charArray16[n48];
            char c32 = '\0';
            switch (n47 % 5) {
                case 0: {
                    c32 = 'W';
                    break;
                }
                case 1: {
                    c32 = 'P';
                    break;
                }
                case 2: {
                    c32 = 'g';
                    break;
                }
                case 3: {
                    c32 = ' ';
                    break;
                }
                default: {
                    c32 = 'J';
                    break;
                }
            }
            charArray16[n48] = (char)(c31 ^ c32);
        }
        z[n46] = new String(charArray16).intern();
        final int n49 = 16;
        final char[] charArray17 = "\u0014?\tN/4$\u000eO$y\u0014\u0002L+.~3I'2".toCharArray();
        final int length13 = charArray17.length;
        for (int n50 = 0; length13 > n50; ++n50) {
            final int n51 = n50;
            final char c33 = charArray17[n51];
            char c34 = '\0';
            switch (n50 % 5) {
                case 0: {
                    c34 = 'W';
                    break;
                }
                case 1: {
                    c34 = 'P';
                    break;
                }
                case 2: {
                    c34 = 'g';
                    break;
                }
                case 3: {
                    c34 = ' ';
                    break;
                }
                default: {
                    c34 = 'J';
                    break;
                }
            }
            charArray17[n51] = (char)(c33 ^ c34);
        }
        z[n49] = new String(charArray17).intern();
        final int n52 = 17;
        final char[] charArray18 = "\u00139\u0014P&6)#A>6~4O)<5\u0013o?#".toCharArray();
        final int length14 = charArray18.length;
        for (int n53 = 0; length14 > n53; ++n53) {
            final int n54 = n53;
            final char c35 = charArray18[n54];
            char c36 = '\0';
            switch (n53 % 5) {
                case 0: {
                    c36 = 'W';
                    break;
                }
                case 1: {
                    c36 = 'P';
                    break;
                }
                case 2: {
                    c36 = 'g';
                    break;
                }
                case 3: {
                    c36 = ' ';
                    break;
                }
                default: {
                    c36 = 'J';
                    break;
                }
            }
            charArray18[n54] = (char)(c35 ^ c36);
        }
        z[n52] = new String(charArray18).intern();
        final int n55 = 18;
        final char[] charArray19 = "\u00139\u0014P&6)#A>6~3I'2\u001f\u0012T".toCharArray();
        final int length15 = charArray19.length;
        for (int n56 = 0; length15 > n56; ++n56) {
            final int n57 = n56;
            final char c37 = charArray19[n57];
            char c38 = '\0';
            switch (n56 % 5) {
                case 0: {
                    c38 = 'W';
                    break;
                }
                case 1: {
                    c38 = 'P';
                    break;
                }
                case 2: {
                    c38 = 'g';
                    break;
                }
                case 3: {
                    c38 = ' ';
                    break;
                }
                default: {
                    c38 = 'J';
                    break;
                }
            }
            charArray19[n57] = (char)(c37 ^ c38);
        }
        z[n55] = new String(charArray19).intern();
        final int n58 = 19;
        final char[] charArray20 = "\u0014?\tN/4$\u000eO$y\u0002\u0002Q?2#\u0013\u000e\u00076(%U$3<\u0002s#-5".toCharArray();
        final int length16 = charArray20.length;
        for (int n59 = 0; length16 > n59; ++n59) {
            final int n60 = n59;
            final char c39 = charArray20[n60];
            char c40 = '\0';
            switch (n59 % 5) {
                case 0: {
                    c40 = 'W';
                    break;
                }
                case 1: {
                    c40 = 'P';
                    break;
                }
                case 2: {
                    c40 = 'g';
                    break;
                }
                case 3: {
                    c40 = ' ';
                    break;
                }
                default: {
                    c40 = 'J';
                    break;
                }
            }
            charArray20[n60] = (char)(c39 ^ c40);
        }
        z[n58] = new String(charArray20).intern();
        final int n61 = 20;
        final char[] charArray21 = "\u0019?Gp88 \u0002R>>5\u0014\u0000,><\u0002\u0000>8p\u0015E+3p\u0015E;\"5\u0014Tj'?\u0015Td".toCharArray();
        final int length17 = charArray21.length;
        for (int n62 = 0; length17 > n62; ++n62) {
            final int n63 = n62;
            final char c41 = charArray21[n63];
            char c42 = '\0';
            switch (n62 % 5) {
                case 0: {
                    c42 = 'W';
                    break;
                }
                case 1: {
                    c42 = 'P';
                    break;
                }
                case 2: {
                    c42 = 'g';
                    break;
                }
                case 3: {
                    c42 = ' ';
                    break;
                }
                default: {
                    c42 = 'J';
                    break;
                }
            }
            charArray21[n63] = (char)(c41 ^ c42);
        }
        z[n61] = new String(charArray21).intern();
        final int n64 = 21;
        final char[] charArray22 = "\u00038\u0002\u0000!2)Gd#$ \u000bA3\u00131\u0013Ad\u0007?\u0015Tj>#GN%#p\u0003E,>>\u0002Dj>>GT\"2p\u0017R%'5\u0015T#2#GF#;5I".toCharArray();
        final int length18 = charArray22.length;
        for (int n65 = 0; length18 > n65; ++n65) {
            final int n66 = n65;
            final char c43 = charArray22[n66];
            char c44 = '\0';
            switch (n65 % 5) {
                case 0: {
                    c44 = 'W';
                    break;
                }
                case 1: {
                    c44 = 'P';
                    break;
                }
                case 2: {
                    c44 = 'g';
                    break;
                }
                case 3: {
                    c44 = ' ';
                    break;
                }
                default: {
                    c44 = 'J';
                    break;
                }
            }
            charArray22[n66] = (char)(c43 ^ c44);
        }
        z[n64] = new String(charArray22).intern();
        b.z = z;
    }
}
