// 
// Decompiled by Procyon v0.5.30
// 

package ji.util;

import ji.font.j;

public final class i
{
    private static boolean[] a;
    private static boolean[] b;
    private static String[] c;
    private static String[] d;
    private static int[] e;
    private static int[] f;
    private static boolean g;
    
    private static final void b() {
        try {
            i.a[7] = true;
            i.a[0] = true;
            i.a[1] = true;
            i.a[12] = true;
            i.a[15] = true;
            i.a[16] = true;
            i.a[18] = true;
            i.a[29] = true;
            i.a[30] = true;
            i.a[19] = true;
            i.a[20] = true;
            i.a[21] = true;
            i.a[25] = true;
            i.a[26] = true;
            i.a[35] = true;
            i.a[41] = true;
            i.a[42] = true;
            i.a[33] = true;
            i.a[44] = true;
            i.a[48] = true;
            i.a[49] = true;
            i.a[51] = true;
            i.a[53] = true;
            i.a[54] = true;
            i.a[55] = true;
            i.a[63] = true;
            i.a[67] = true;
            i.a[68] = true;
            i.a[69] = true;
            i.a[70] = true;
            i.a[72] = true;
            i.a[73] = true;
            i.a[75] = true;
            i.a[76] = true;
            i.a[87] = true;
            i.a[88] = true;
            i.a[92] = true;
            i.a[93] = true;
            i.a[94] = true;
            i.a[96] = true;
            i.a[97] = true;
            i.a[100] = true;
            i.a[99] = true;
            i.a[101] = true;
            i.a[103] = true;
            i.a[104] = true;
            i.a[106] = true;
            i.a[107] = true;
            i.a[108] = true;
            i.a[111] = true;
            i.a[113] = true;
            i.a[116] = true;
            i.a[117] = true;
            i.a[118] = true;
            i.a[119] = true;
            i.a[120] = true;
            i.a[122] = true;
            i.a[125] = true;
            i.a[129] = true;
            i.a[138] = false;
            i.a[130] = true;
            i.a[139] = true;
            i.a[140] = true;
            i.a[127] = true;
            i.a[143] = true;
            i.a[146] = true;
            i.a[153] = true;
            i.a[161] = false;
            i.a[163] = true;
            i.a[164] = true;
            i.a[166] = true;
            i.a[168] = false;
            i.a[169] = true;
            i.a[170] = true;
            i.a[171] = true;
            i.a[172] = false;
            i.a[173] = true;
            i.a[174] = true;
            i.a[175] = true;
            i.a[176] = true;
            i.a[179] = true;
            i.a[189] = true;
            i.a[186] = true;
            i.a[191] = true;
            i.a[198] = true;
            i.a[203] = true;
            i.a[205] = true;
            i.a[209] = true;
            i.a[208] = true;
            i.a[210] = true;
            i.a[211] = true;
            i.a[213] = true;
            i.a[214] = true;
            i.a[215] = true;
            i.a[216] = true;
            i.a[217] = true;
            i.a[218] = true;
            i.a[219] = true;
            i.a[220] = true;
            i.a[221] = true;
            i.a[224] = true;
            i.a[223] = true;
            i.a[228] = false;
            i.a[229] = true;
            i.a[232] = true;
            i.a[231] = true;
            i.a[238] = true;
            i.a[240] = true;
            i.a[241] = true;
            i.a[242] = true;
            i.a[248] = true;
            i.a[249] = true;
            i.a[256] = false;
            i.a[257] = false;
            i.a[258] = false;
            i.a[259] = true;
            i.a[254] = true;
            i.a[261] = true;
            i.a[263] = true;
            i.a[265] = true;
            i.a[267] = true;
            i.a[268] = true;
            i.a[266] = true;
            i.a[269] = true;
            i.a[271] = true;
            i.a[272] = false;
            i.a[275] = true;
            i.a[276] = true;
            i.a[277] = true;
            i.a[274] = true;
            i.a[273] = true;
            i.a[279] = true;
            i.a[281] = true;
            i.a[282] = true;
            i.a[283] = true;
            i.a[286] = true;
            i.a[142] = true;
            i.a[291] = true;
            i.a[293] = true;
            i.e[0] = 500;
            i.e[1] = j.h(-1);
            i.e[4] = 256;
            i.e[5] = 80;
            i.e[6] = 5;
            i.e[7] = 10;
            i.e[8] = 1048576;
            i.e[9] = 200;
            i.e[10] = 3;
            i.e[11] = 0;
        }
        catch (Exception ex) {}
        System.arraycopy(i.a, 0, i.b, 0, 294);
        System.arraycopy(i.c, 0, i.d, 0, 9);
        System.arraycopy(i.e, 0, i.f, 0, 12);
        i.g = true;
    }
    
    public static final void a(final int n) {
        try {
            if (!i.g) {
                b();
            }
            if (n < 294) {
                i.a[n] = true;
            }
        }
        catch (Exception ex) {}
    }
    
    public static final void b(final int n) {
        try {
            if (!i.g) {
                b();
            }
            if (n < 294) {
                i.a[n] = false;
            }
        }
        catch (Exception ex) {}
    }
    
    public static final void a(final int n, final boolean b) {
        if (b) {
            a(n);
        }
        else {
            b(n);
        }
    }
    
    public static final void a(final int n, final String s) {
        b(n, s);
    }
    
    public static final void a(final int n, final int n2) {
        b(n, n2);
    }
    
    public static final boolean c(final int n) {
        if (!i.g) {
            b();
        }
        return i.a[n];
    }
    
    public static final void b(final int n, final int n2) {
        try {
            if (!i.g) {
                b();
            }
            if (n < 12) {
                i.e[n] = n2;
            }
        }
        catch (Exception ex) {}
    }
    
    public static final int d(final int n) {
        try {
            if (!i.g) {
                b();
            }
            if (n < 12) {
                return i.e[n];
            }
        }
        catch (Exception ex) {}
        return 0;
    }
    
    public static final void b(final int n, final String s) {
        try {
            if (!i.g) {
                b();
            }
            if (n < 9) {
                i.c[n] = new String(s);
            }
        }
        catch (Exception ex) {}
    }
    
    public static final String e(final int n) {
        try {
            if (!i.g) {
                b();
            }
            if (n < 9) {
                return i.c[n];
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    public static final int f(final int n) {
        for (int i = (n < 0) ? 0 : (n + 1); i < i.a.length; ++i) {
            if (i.a[i] != i.b[i]) {
                return i;
            }
        }
        return -1;
    }
    
    public static final int g(final int n) {
        for (int i = (n < 0) ? 0 : (n + 1); i < i.c.length; ++i) {
            if (i.d[i] == null) {
                if (i.c[i] != null) {
                    return i;
                }
            }
            else {
                if (i.c[i] == null) {
                    return i;
                }
                if (!i.c[i].equals(i.d[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    public static final int h(final int n) {
        for (int i = (n < 0) ? 0 : (n + 1); i < i.e.length; ++i) {
            if (i.e[i] != i.f[i]) {
                return i;
            }
        }
        return -1;
    }
    
    public static final StringBuffer a() {
        final StringBuffer sb = new StringBuffer();
        int f = -1;
        while ((f = f(f)) != -1) {
            sb.append(String.valueOf(String.valueOf(new StringBuffer("cb: ").append(f).append(" = ").append(c(f)).append("\n"))));
        }
        int g = -1;
        while ((g = g(g)) != -1) {
            sb.append(String.valueOf(String.valueOf(new StringBuffer("cs: ").append(g).append(" = ").append(e(g)).append("\n"))));
        }
        int h = -1;
        while ((h = h(h)) != -1) {
            sb.append(String.valueOf(String.valueOf(new StringBuffer("ci: ").append(h).append(" = ").append(d(h)).append("\n"))));
        }
        return sb;
    }
    
    static {
        i.a = new boolean[294];
        i.b = new boolean[294];
        i.c = new String[9];
        i.d = new String[9];
        i.e = new int[12];
        i.f = new int[12];
        i.g = false;
    }
}
