// 
// Decompiled by Procyon v0.5.30
// 

package d.a.a.a;

import d.a.b.a.a;

public final class c
{
    private static final byte b = 13;
    private static final int[] case;
    private static final int[] byte;
    private static final int else = -32768;
    private static final int e = 32767;
    private int[] char;
    private int[] long;
    private int[][] c;
    private int g;
    private int try;
    private int[] goto;
    private int a;
    private byte[] k;
    private int[] do;
    private int[] int;
    private int[] l;
    private int[] if;
    private int[] f;
    private int[] i;
    private int[] d;
    private int[] j;
    private int[] void;
    private int[] new;
    private int[] h;
    private int[] for;
    
    public c() {
        this.char = new int[280];
        this.long = new int[8];
        this.c = new int[2][8];
        this.goto = new int[9];
        this.do = new int[8];
        this.int = new int[4];
        this.l = new int[4];
        this.if = new int[4];
        this.f = new int[4];
        this.i = new int[52];
        this.d = new int[40];
        this.j = new int[160];
        this.void = new int[13];
        this.new = new int[2];
        this.h = new int[8];
        this.for = new int[160];
    }
    
    public void a() {
        this.try = 40;
    }
    
    public void a(final byte[] array, final int n, final byte[] array2, final int n2, final boolean b) throws g {
        if (this.k == null) {
            this.k = new byte[33];
        }
        System.arraycopy(array, n, this.k, 0, 33);
        final int[] a = this.a(this.k);
        for (int i = 0; i < 160; ++i) {
            d.a.b.a.a.do(a[i], array2, i * 2 + n2, b);
        }
    }
    
    private final int[] a(final byte[] array) throws g {
        if (array.length != 33) {
            throw new g();
        }
        int n = 0;
        if ((array[n] >> 4 & 0xF) != 0xD) {
            throw new g();
        }
        this.do[0] = (array[n++] & 0xF) << 2;
        final int[] do1 = this.do;
        final int n2 = 0;
        do1[n2] |= (array[n] >> 6 & 0x3);
        this.do[1] = (array[n++] & 0x3F);
        this.do[2] = (array[n] >> 3 & 0x1F);
        this.do[3] = (array[n++] & 0x7) << 2;
        final int[] do2 = this.do;
        final int n3 = 3;
        do2[n3] |= (array[n] >> 6 & 0x3);
        this.do[4] = (array[n] >> 2 & 0xF);
        this.do[5] = (array[n++] & 0x3) << 2;
        final int[] do3 = this.do;
        final int n4 = 5;
        do3[n4] |= (array[n] >> 6 & 0x3);
        this.do[6] = (array[n] >> 3 & 0x7);
        this.do[7] = (array[n++] & 0x7);
        this.int[0] = (array[n] >> 1 & 0x7F);
        this.if[0] = (array[n++] & 0x1) << 1;
        final int[] if1 = this.if;
        final int n5 = 0;
        if1[n5] |= (array[n] >> 7 & 0x1);
        this.l[0] = (array[n] >> 5 & 0x3);
        this.f[0] = (array[n++] & 0x1F) << 1;
        final int[] f = this.f;
        final int n6 = 0;
        f[n6] |= (array[n] >> 7 & 0x1);
        this.i[0] = (array[n] >> 4 & 0x7);
        this.i[1] = (array[n] >> 1 & 0x7);
        this.i[2] = (array[n++] & 0x1) << 2;
        final int[] i = this.i;
        final int n7 = 2;
        i[n7] |= (array[n] >> 6 & 0x3);
        this.i[3] = (array[n] >> 3 & 0x7);
        this.i[4] = (array[n++] & 0x7);
        this.i[5] = (array[n] >> 5 & 0x7);
        this.i[6] = (array[n] >> 2 & 0x7);
        this.i[7] = (array[n++] & 0x3) << 1;
        final int[] j = this.i;
        final int n8 = 7;
        j[n8] |= (array[n] >> 7 & 0x1);
        this.i[8] = (array[n] >> 4 & 0x7);
        this.i[9] = (array[n] >> 1 & 0x7);
        this.i[10] = (array[n++] & 0x1) << 2;
        final int[] k = this.i;
        final int n9 = 10;
        k[n9] |= (array[n] >> 6 & 0x3);
        this.i[11] = (array[n] >> 3 & 0x7);
        this.i[12] = (array[n++] & 0x7);
        this.int[1] = (array[n] >> 1 & 0x7F);
        this.if[1] = (array[n++] & 0x1) << 1;
        final int[] if2 = this.if;
        final int n10 = 1;
        if2[n10] |= (array[n] >> 7 & 0x1);
        this.l[1] = (array[n] >> 5 & 0x3);
        this.f[1] = (array[n++] & 0x1F) << 1;
        final int[] f2 = this.f;
        final int n11 = 1;
        f2[n11] |= (array[n] >> 7 & 0x1);
        this.i[13] = (array[n] >> 4 & 0x7);
        this.i[14] = (array[n] >> 1 & 0x7);
        this.i[15] = (array[n++] & 0x1) << 2;
        final int[] l = this.i;
        final int n12 = 15;
        l[n12] |= (array[n] >> 6 & 0x3);
        this.i[16] = (array[n] >> 3 & 0x7);
        this.i[17] = (array[n++] & 0x7);
        this.i[18] = (array[n] >> 5 & 0x7);
        this.i[19] = (array[n] >> 2 & 0x7);
        this.i[20] = (array[n++] & 0x3) << 1;
        final int[] m = this.i;
        final int n13 = 20;
        m[n13] |= (array[n] >> 7 & 0x1);
        this.i[21] = (array[n] >> 4 & 0x7);
        this.i[22] = (array[n] >> 1 & 0x7);
        this.i[23] = (array[n++] & 0x1) << 2;
        final int[] i2 = this.i;
        final int n14 = 23;
        i2[n14] |= (array[n] >> 6 & 0x3);
        this.i[24] = (array[n] >> 3 & 0x7);
        this.i[25] = (array[n++] & 0x7);
        this.int[2] = (array[n] >> 1 & 0x7F);
        this.if[2] = (array[n++] & 0x1) << 1;
        final int[] if3 = this.if;
        final int n15 = 2;
        if3[n15] |= (array[n] >> 7 & 0x1);
        this.l[2] = (array[n] >> 5 & 0x3);
        this.f[2] = (array[n++] & 0x1F) << 1;
        final int[] f3 = this.f;
        final int n16 = 2;
        f3[n16] |= (array[n] >> 7 & 0x1);
        this.i[26] = (array[n] >> 4 & 0x7);
        this.i[27] = (array[n] >> 1 & 0x7);
        this.i[28] = (array[n++] & 0x1) << 2;
        final int[] i3 = this.i;
        final int n17 = 28;
        i3[n17] |= (array[n] >> 6 & 0x3);
        this.i[29] = (array[n] >> 3 & 0x7);
        this.i[30] = (array[n++] & 0x7);
        this.i[31] = (array[n] >> 5 & 0x7);
        this.i[32] = (array[n] >> 2 & 0x7);
        this.i[33] = (array[n++] & 0x3) << 1;
        final int[] i4 = this.i;
        final int n18 = 33;
        i4[n18] |= (array[n] >> 7 & 0x1);
        this.i[34] = (array[n] >> 4 & 0x7);
        this.i[35] = (array[n] >> 1 & 0x7);
        this.i[36] = (array[n++] & 0x1) << 2;
        final int[] i5 = this.i;
        final int n19 = 36;
        i5[n19] |= (array[n] >> 6 & 0x3);
        this.i[37] = (array[n] >> 3 & 0x7);
        this.i[38] = (array[n++] & 0x7);
        this.int[3] = (array[n] >> 1 & 0x7F);
        this.if[3] = (array[n++] & 0x1) << 1;
        final int[] if4 = this.if;
        final int n20 = 3;
        if4[n20] |= (array[n] >> 7 & 0x1);
        this.l[3] = (array[n] >> 5 & 0x3);
        this.f[3] = (array[n++] & 0x1F) << 1;
        final int[] f4 = this.f;
        final int n21 = 3;
        f4[n21] |= (array[n] >> 7 & 0x1);
        this.i[39] = (array[n] >> 4 & 0x7);
        this.i[40] = (array[n] >> 1 & 0x7);
        this.i[41] = (array[n++] & 0x1) << 2;
        final int[] i6 = this.i;
        final int n22 = 41;
        i6[n22] |= (array[n] >> 6 & 0x3);
        this.i[42] = (array[n] >> 3 & 0x7);
        this.i[43] = (array[n++] & 0x7);
        this.i[44] = (array[n] >> 5 & 0x7);
        this.i[45] = (array[n] >> 2 & 0x7);
        this.i[46] = (array[n++] & 0x3) << 1;
        final int[] i7 = this.i;
        final int n23 = 46;
        i7[n23] |= (array[n] >> 7 & 0x1);
        this.i[47] = (array[n] >> 4 & 0x7);
        this.i[48] = (array[n] >> 1 & 0x7);
        this.i[49] = (array[n++] & 0x1) << 2;
        final int[] i8 = this.i;
        final int n24 = 49;
        i8[n24] |= (array[n] >> 6 & 0x3);
        this.i[50] = (array[n] >> 3 & 0x7);
        this.i[51] = (array[n] & 0x7);
        return this.a(this.do, this.int, this.if, this.l, this.f, this.i);
    }
    
    public static final void a(final String s, final int[] array) {
        System.out.print("[" + s + ":");
        for (int i = 0; i < array.length; ++i) {
            System.out.print("" + array[i]);
            if (i < array.length - 1) {
                System.out.print(",");
            }
            else {
                System.out.println("]");
            }
        }
    }
    
    public static final void a(final String s, final int n) {
        System.out.println("[" + s + ":" + n + "]");
    }
    
    private final int[] a(final int[] array, final int[] array2, final int[] array3, final int[] array4, final int[] array5, final int[] array6) {
        for (int i = 0; i < 4; ++i) {
            this.a(array5[i], array4[i], array6, i * 13, this.d);
            this.a(array2[i], array3[i], this.d, this.char);
            for (int j = 0; j < 40; ++j) {
                this.j[i * 40 + j] = this.char[120 + j];
            }
        }
        final int[] a = this.a(array, this.j);
        this.a(a);
        return a;
    }
    
    private final void a(final int n, final int n2, final int[] array, final int n3, final int[] array2) {
        final int[] if1 = this.if(n);
        this.a(array, n3, if1[0], if1[1], this.void);
        a(n2, this.void, array2);
    }
    
    private final int[] if(final int n) {
        int n2 = 0;
        if (n > 15) {
            n2 = (n >> 3) - 1;
        }
        int i = n - (n2 << 3);
        if (i == 0) {
            n2 = -4;
            i = 7;
        }
        else {
            while (i <= 7) {
                i = (i << 1 | 0x1);
                --n2;
            }
            i -= 8;
        }
        this.new[0] = n2;
        this.new[1] = i;
        return this.new;
    }
    
    private final void a(final int[] array, int n, final int n2, final int n3, final int[] array2) {
        final int n4 = d.a.a.a.c.case[n3];
        final int do1 = do(6, n2);
        final int if1 = if(1, do(do1, 1));
        int n5 = 0;
        int n6 = 13;
        while (n6-- > 0) {
            array2[n5++] = int(a(for(n4, (array[n++] << 1) - 7 << 12), if1), do1);
        }
    }
    
    private static final int a(final int n) {
        return (n < -32768) ? -32768 : ((n > 32767) ? 32767 : n);
    }
    
    private static final int do(final int n, final int n2) {
        return a(n - n2);
    }
    
    private static final int a(final int n, final int n2) {
        return a(n + n2);
    }
    
    private static final int if(final int n, final int n2) {
        if (n2 >= 16) {
            return 0;
        }
        if (n2 <= -16) {
            return (n < 0) ? -1 : 0;
        }
        if (n2 < 0) {
            return int(n, -n2);
        }
        return n << n2;
    }
    
    private static final int int(final int n, final int n2) {
        if (n2 >= 16) {
            return (n < 0) ? -1 : 0;
        }
        if (n2 <= -16) {
            return 0;
        }
        if (n2 < 0) {
            return n << -n2;
        }
        return n >> n2;
    }
    
    private static final int for(final int n, final int n2) {
        if (n2 == -32768 && n == -32768) {
            return 32767;
        }
        return a(n * n2 + 16384 >> 15);
    }
    
    private final void a(final int n, final int n2, final int[] array, final int[] array2) {
        final int try1 = (n < 40 || n > 120) ? this.try : n;
        this.try = try1;
        final int n3 = d.a.a.a.c.byte[n2];
        for (int i = 0; i <= 39; ++i) {
            array2[120 + i] = a(array[i], for(n3, array2[120 + (i - try1)]));
        }
        for (int j = 0; j <= 119; ++j) {
            array2[j] = array2[40 + j];
        }
    }
    
    private final int[] a(final int[] array, final int[] array2) {
        final int[] array3 = this.c[this.g];
        final int[][] c = this.c;
        final int g = this.g ^ 0x1;
        this.g = g;
        final int[] array4 = c[g];
        if(array, array3);
        do(array4, array3, this.h);
        if(this.h);
        this.a(this.h, 13, array2, this.for, 0);
        a(array4, array3, this.h);
        if(this.h);
        this.a(this.h, 14, array2, this.for, 13);
        if(array4, array3, this.h);
        if(this.h);
        this.a(this.h, 13, array2, this.for, 27);
        do(array3, this.h);
        if(this.h);
        this.a(this.h, 120, array2, this.for, 40);
        return this.for;
    }
    
    public static final void if(final int[] array, final int[] array2) {
        final int for1 = for(13107, a(array[0], -32) << 10);
        array2[0] = a(for1, for1);
        final int for2 = for(13107, a(array[1], -32) << 10);
        array2[1] = a(for2, for2);
        final int for3 = for(13107, do(a(array[2], -16) << 10, 4096));
        array2[2] = a(for3, for3);
        final int for4 = for(13107, do(a(array[3], -16) << 10, -5120));
        array2[3] = a(for4, for4);
        final int for5 = for(19223, do(a(array[4], -8) << 10, 188));
        array2[4] = a(for5, for5);
        final int for6 = for(17476, do(a(array[5], -8) << 10, -3584));
        array2[5] = a(for6, for6);
        final int for7 = for(31454, do(a(array[6], -4) << 10, -682));
        array2[6] = a(for7, for7);
        final int for8 = for(29708, do(a(array[7], -4) << 10, -2288));
        array2[7] = a(for8, for8);
    }
    
    private static final void do(final int[] array, final int[] array2, final int[] array3) {
        for (int i = 0; i < 8; ++i) {
            array3[i] = a(array[i] >> 2, array2[i] >> 2);
            array3[i] = a(array3[i], array[i] >> 1);
        }
    }
    
    private static final void a(final int[] array, final int[] array2, final int[] array3) {
        for (int i = 0; i < 8; ++i) {
            array3[i] = a(array[i] >> 1, array2[i] >> 1);
        }
    }
    
    private static final void if(final int[] array, final int[] array2, final int[] array3) {
        for (int i = 0; i < 8; ++i) {
            array3[i] = a(array[i] >> 2, array2[i] >> 2);
            array3[i] = a(array3[i], array2[i] >> 1);
        }
    }
    
    private static final void do(final int[] array, final int[] array2) {
        for (int i = 0; i < 8; ++i) {
            array2[i] = array[i];
        }
    }
    
    private static final void if(final int[] array) {
        for (int i = 0; i < 8; ++i) {
            if (array[i] < 0) {
                final int n = (array[i] == -32768) ? 32767 : (-array[i]);
                array[i] = -((n < 11059) ? (n << 1) : ((n < 20070) ? (n + 11059) : a(n >> 2, 26112)));
            }
            else {
                final int n2 = array[i];
                array[i] = ((n2 < 11059) ? (n2 << 1) : ((n2 < 20070) ? (n2 + 11059) : a(n2 >> 2, 26112)));
            }
        }
    }
    
    private final void a(final int[] array, int n, final int[] array2, final int[] array3, final int n2) {
        int n3 = n2;
        int n4 = n2;
        while (n-- > 0) {
            int do1 = array2[n3++];
            int n5 = 8;
            while (n5-- > 0) {
                final int n6 = array[n5];
                final int n7 = this.goto[n5];
                do1 = do(do1, (n6 == -32768 && n7 == -32768) ? 32767 : a(n6 * n7 + 16384 >> 15));
                this.goto[n5 + 1] = a(this.goto[n5], (n6 == -32768 && do1 == -32768) ? 32767 : a(n6 * do1 + 16384 >> 15));
            }
            array3[n4++] = (this.goto[0] = do1);
        }
    }
    
    private final void a(final int[] array) {
        int n = 0;
        int n2 = 160;
        while (n2-- > 0) {
            this.a = a(array[n], for(this.a, 28180));
            array[n] = a(a(this.a, this.a) & 0xFFFFFFF8);
            ++n;
        }
    }
    
    private static final void a(int n, final int[] array, final int[] array2) {
        int n2 = 13;
        int n3 = 0;
        int n4 = 0;
        switch (n) {
            case 3: {
                array2[n3++] = 0;
            }
            case 2: {
                array2[n3++] = 0;
            }
            case 1: {
                array2[n3++] = 0;
            }
            case 0: {
                array2[n3++] = array[n4++];
                --n2;
                break;
            }
        }
        do {
            array2[n3++] = 0;
            array2[n3++] = 0;
            array2[n3++] = array[n4++];
        } while (--n2 > 0);
        while (++n < 4) {
            array2[n3++] = 0;
        }
    }
    
    static {
        case = new int[] { 18431, 20479, 22527, 24575, 26623, 28671, 30719, 32767 };
        byte = new int[] { 3277, 11469, 21299, 32767 };
    }
}
