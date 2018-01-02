// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.f;
import neat.system.cb;

public class k implements cb
{
    private static n a;
    private static f b;
    public static final boolean c;
    public static final boolean d;
    private m e;
    private static /* synthetic */ Class f;
    private static String[] z;
    
    public int a() {
        return this.e.b;
    }
    
    public void a(final byte[] array, final int n, final int n2) {
        k.a.a(this.e, array, n, n2);
    }
    
    public byte[] b() {
        if (k.c) {
            System.err.println(k.z[5] + this.e + k.z[8]);
        }
        final byte[] c = k.a.c(this.e);
        if (k.c) {
            System.err.println(k.z[5] + this.e + k.z[9] + c);
        }
        return c;
    }
    
    public void c() {
        if (k.c) {
            System.err.println(k.z[5] + this.e + k.z[7] + this.e.h);
        }
        k.a.d(this.e);
        if (k.c) {
            System.err.println(k.z[5] + this.e + k.z[6] + this.e.i);
        }
    }
    
    static k b(final byte[] array, final int n, final int n2) {
        if (k.c) {
            System.err.println(k.z[4] + array + k.z[0] + n + k.z[1] + n2);
        }
        final k k = (k)neat.k.b.a();
        if (neat.k.c) {
            System.err.println(neat.k.z[5] + k.e + neat.k.z[2]);
        }
        k.a(array, n, n2);
        if (neat.k.c) {
            System.err.println(neat.k.z[5] + k.e + neat.k.z[3]);
        }
        return k;
    }
    
    public void f() {
        if (k.c) {
            System.err.println(k.z[5] + this.e + k.z[10]);
        }
        k.b.a(this);
    }
    
    public void g() {
        this.e = k.a.a(this);
    }
    
    public void h() {
        k.a.a(this.e);
        this.e = null;
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public k() {
        this.e = null;
    }
    
    static {
        final String[] z = new String[12];
        final int n = 0;
        final char[] charArray = "\u0000\u0013p\u001eL_VkB".toCharArray();
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
                    final char c2 = charArray[n3];
                    char c3 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c3 = ',';
                            break;
                        }
                        case 1: {
                            c3 = '3';
                            break;
                        }
                        case 2: {
                            c3 = '\u001f';
                            break;
                        }
                        case 3: {
                            c3 = 'x';
                            break;
                        }
                        default: {
                            c3 = '*';
                            break;
                        }
                    }
                    charArray[length] = (char)(c2 ^ c3);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "\u0000\u0013l\u0011PI\t".toCharArray();
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
                    final char c4 = charArray2[n7];
                    char c5 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c5 = ',';
                            break;
                        }
                        case 1: {
                            c5 = '3';
                            break;
                        }
                        case 2: {
                            c5 = '\u001f';
                            break;
                        }
                        case 3: {
                            c5 = 'x';
                            break;
                        }
                        default: {
                            c5 = '*';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c4 ^ c5);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "\u0005\t?\u0016O[qf\fOnFy\u001eO^\u001b6T\nBVh7HFV|\f\n^V~\u001cS\u0000\u0013~\u0014FCP~\fCBT".toCharArray();
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
                    final char c6 = charArray3[n11];
                    char c7 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c7 = ',';
                            break;
                        }
                        case 1: {
                            c7 = '3';
                            break;
                        }
                        case 2: {
                            c7 = '\u001f';
                            break;
                        }
                        case 3: {
                            c7 = 'x';
                            break;
                        }
                        default: {
                            c7 = '*';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c6 ^ c7);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        z[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "\u0005\t?\u0016O[qf\fOnFy\u001eO^\u001b6T\n^V~\u001cS".toCharArray();
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
                    final char c8 = charArray4[n15];
                    char c9 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c9 = ',';
                            break;
                        }
                        case 1: {
                            c9 = '3';
                            break;
                        }
                        case 2: {
                            c9 = '\u001f';
                            break;
                        }
                        case 3: {
                            c9 = 'x';
                            break;
                        }
                        default: {
                            c9 = '*';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c8 ^ c9);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        z[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "nfY>o~\t?\u0016O[qf\fOnFy\u001eO^\u001b6T\n_G~\n^\u0016".toCharArray();
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
                    final char c10 = charArray5[n19];
                    char c11 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c11 = ',';
                            break;
                        }
                        case 1: {
                            c11 = '3';
                            break;
                        }
                        case 2: {
                            c11 = '\u001f';
                            break;
                        }
                        case 3: {
                            c11 = 'x';
                            break;
                        }
                        default: {
                            c11 = '*';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c10 ^ c11);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        z[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "nfY>o~\u00137".toCharArray();
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
                    final char c12 = charArray6[n23];
                    char c13 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c13 = ',';
                            break;
                        }
                        case 1: {
                            c13 = '3';
                            break;
                        }
                        case 2: {
                            c13 = '\u001f';
                            break;
                        }
                        case 3: {
                            c13 = 'x';
                            break;
                        }
                        default: {
                            c13 = '*';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c12 ^ c13);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        z[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "\u0005\t?\u0014EOX]\rLJVmP\u0003\u0000\u0013j\u0016FCPt\u001dN\u0000\u0013s\u0017IGV{XICFq\fO^\t".toCharArray();
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
                    final char c14 = charArray7[n27];
                    char c15 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c15 = ',';
                            break;
                        }
                        case 1: {
                            c15 = '3';
                            break;
                        }
                        case 2: {
                            c15 = '\u001f';
                            break;
                        }
                        case 3: {
                            c15 = 'x';
                            break;
                        }
                        default: {
                            c15 = '*';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c14 ^ c15);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        z[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "\u0005\t?\rD@\\|\u0013hYUy\u001dX\u0004\u001a3XYXRm\f\u0010".toCharArray();
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
                    final char c16 = charArray8[n31];
                    char c17 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c17 = ',';
                            break;
                        }
                        case 1: {
                            c17 = '3';
                            break;
                        }
                        case 2: {
                            c17 = '\u001f';
                            break;
                        }
                        case 3: {
                            c17 = 'x';
                            break;
                        }
                        default: {
                            c17 = '*';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c16 ^ c17);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 > n32) {
                continue;
            }
            break;
        }
        z[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "\u0005\t?\u0014EOX]\rLJVmP\u0003\u0000\u0013l\fK^G".toCharArray();
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
                    final char c18 = charArray9[n35];
                    char c19 = '\0';
                    switch (n36 % 5) {
                        case 0: {
                            c19 = ',';
                            break;
                        }
                        case 1: {
                            c19 = '3';
                            break;
                        }
                        case 2: {
                            c19 = '\u001f';
                            break;
                        }
                        case 3: {
                            c19 = 'x';
                            break;
                        }
                        default: {
                            c19 = '*';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c18 ^ c19);
                    ++n36;
                } while (n34 == 0);
            }
            if (n34 > n36) {
                continue;
            }
            break;
        }
        z[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "\u0005\t?\u0014EOX]\rLJVmP\u0003\u0000\u0013s\u0017IGV{B".toCharArray();
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
                    final char c20 = charArray10[n39];
                    char c21 = '\0';
                    switch (n40 % 5) {
                        case 0: {
                            c21 = ',';
                            break;
                        }
                        case 1: {
                            c21 = '3';
                            break;
                        }
                        case 2: {
                            c21 = '\u001f';
                            break;
                        }
                        case 3: {
                            c21 = 'x';
                            break;
                        }
                        default: {
                            c21 = '*';
                            break;
                        }
                    }
                    charArray10[length10] = (char)(c20 ^ c21);
                    ++n40;
                } while (n38 == 0);
            }
            if (n38 > n40) {
                continue;
            }
            break;
        }
        z[n37] = new String(charArray10).intern();
        final int n41 = 10;
        final char[] charArray11 = "\u0005\t?\u001cO@Vk\u001d\u0002\u0005".toCharArray();
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
                    final char c22 = charArray11[n43];
                    char c23 = '\0';
                    switch (n44 % 5) {
                        case 0: {
                            c23 = ',';
                            break;
                        }
                        case 1: {
                            c23 = '3';
                            break;
                        }
                        case 2: {
                            c23 = '\u001f';
                            break;
                        }
                        case 3: {
                            c23 = 'x';
                            break;
                        }
                        default: {
                            c23 = '*';
                            break;
                        }
                    }
                    charArray11[length11] = (char)(c22 ^ c23);
                    ++n44;
                } while (n42 == 0);
            }
            if (n42 > n44) {
                continue;
            }
            break;
        }
        z[n41] = new String(charArray11).intern();
        final int n45 = 11;
        final char[] charArray12 = "BV~\f\u0004G".toCharArray();
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
                    final char c24 = charArray12[n47];
                    char c25 = '\0';
                    switch (n48 % 5) {
                        case 0: {
                            c25 = ',';
                            break;
                        }
                        case 1: {
                            c25 = '3';
                            break;
                        }
                        case 2: {
                            c25 = '\u001f';
                            break;
                        }
                        case 3: {
                            c25 = 'x';
                            break;
                        }
                        default: {
                            c25 = '*';
                            break;
                        }
                    }
                    charArray12[length12] = (char)(c24 ^ c25);
                    ++n48;
                } while (n46 == 0);
            }
            if (n46 <= n48) {
                z[n45] = new String(charArray12).intern();
                k.z = z;
                k.a = new n(20000);
                k.b = new f((k.f != null) ? k.f : (k.f = a(k.z[11])));
                c = false;
                d = (true & k.c);
                return;
            }
            continue;
        }
    }
}
