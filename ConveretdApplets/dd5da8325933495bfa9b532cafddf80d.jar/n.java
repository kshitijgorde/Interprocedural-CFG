import java.io.InputStream;
import java.util.zip.ZipInputStream;
import java.net.URL;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public final class n
{
    private static short[] a;
    private static byte[] b;
    private static byte[] c;
    private static short[][][] d;
    private static int[] e;
    private static int[][][] f;
    public static byte[] g;
    public static int h;
    public static int i;
    private static int j;
    private static short[] k;
    private static short[] l;
    private static short[][] m;
    private static short[][] n;
    private static byte[] o;
    private static byte[] p;
    private static byte[] q;
    private static byte[] r;
    
    private static void a(final d d, final String s, final byte[] array, final int n, final boolean b) {
        final boolean dj = p.dJ;
        boolean b2 = false;
        final int length = array.length;
        final int d2 = d.d("logo.jpg");
        final int n2 = (d.bb() - 24) / 2;
        final int n3 = 287;
        d.b("Helvetica", 12, 1, Color.white);
        d.a(n, Color.black);
        d.a(n, d2, 160, 111);
        d.a(n, "@Loading Dictionary", 287);
        try {
            URL url = null;
            Label_0131: {
                if (f.h != null) {
                    url = new URL("file:/E:/Source/Clients/FreshGames/Mojo/Dev/Mojo_Java/" + s);
                    if (!dj) {
                        break Label_0131;
                    }
                }
                url = new URL(d.S(), s);
            }
            final InputStream inputStream = url.openConnection().getInputStream();
            final ZipInputStream zipInputStream;
            (zipInputStream = new ZipInputStream(inputStream)).getNextEntry();
            int n4 = 0;
            int n5 = length;
            int n6 = 0;
            while (true) {
                Label_0307: {
                    Label_0302: {
                        if (!dj) {
                            break Label_0302;
                        }
                        final int read;
                        if ((read = zipInputStream.read(array, n4, (n6 > 4096) ? 4096 : n5)) == -1) {
                            break Label_0307;
                        }
                        n4 += read;
                        n5 -= read;
                        final int n7 = b ? i.a((int)i.a(n4, 0.0f, length - 1, 1.0f, 25.0f), 1, 25) : 0;
                        d.a(n, p.de[0], n2, n3 + 20);
                        d.a(n, p.dh[n7], n2 + 0, n3 + 20 + 4);
                        d.f();
                        d.a(5L);
                    }
                    if (n5 > 0) {
                        continue;
                    }
                }
                zipInputStream.closeEntry();
                zipInputStream.close();
                inputStream.close();
                n6 = n5;
                if (dj) {
                    continue;
                }
                break;
            }
            if (n6 != 0 || n4 != length) {
                throw new Exception("Failed to read all bytes !");
            }
        }
        catch (Exception ex) {
            b2 = true;
        }
        if (b2) {
            d.a(n, Color.black);
            d.a(n, d2, 160, 111);
            d.a(n, "@Error Loading Dictionary !", n3);
            do {
                d.f();
                d.a(1000L);
            } while (!dj);
        }
    }
    
    public static void a(final d d, final int n) {
        final boolean dj = p.dJ;
        a(d, "index.jar", n.b = new byte[5425], n, false);
        int n2 = 5425 - 15;
        int n3 = 3;
        while (true) {
            while (true) {
                Label_0097: {
                    if (!dj) {
                        break Label_0097;
                    }
                    n.a[n3 - 3] = (short)(n.b[n2++] * 128 * 128 + n.b[n2++] * 128 + n.b[n2++]);
                    ++n3;
                }
                if (n3 <= 6) {
                    continue;
                }
                break;
            }
            a(d, "words.jar", n.c = new byte[n.b[n2++] * 128 * 128 + n.b[n2++] * 128 + n.b[n2++]], n, true);
            if (!dj) {
                return;
            }
            continue;
        }
    }
    
    public static void a() {
        final boolean dj = p.dJ;
        int n = 0;
        int n2 = 0;
        int n3 = 3;
        int n4;
        while (true) {
            while (true) {
                Label_0216: {
                    if (!dj) {
                        break Label_0216;
                    }
                    n4 = n3 - 3;
                    n.e[n4] = n2;
                    int n5 = 0;
                    while (true) {
                        Label_0206: {
                            if (!dj) {
                                break Label_0206;
                            }
                            int n6 = 0;
                        Label_0107_Outer:
                            while (true) {
                                Label_0196: {
                                    if (!dj) {
                                        break Label_0196;
                                    }
                                    int n7 = 0;
                                    final int n8 = n.b[n++] * 128 + n.b[n++];
                                    int n9 = 8192;
                                    int n10 = n;
                                    int n13;
                                    int n14;
                                    while (true) {
                                        while (true) {
                                            Label_0110: {
                                                if (!dj) {
                                                    break Label_0110;
                                                }
                                                final int n11 = n.b[n10++] * 128;
                                                final int n12 = n.b[n10++];
                                                n9 = n11 + n12;
                                            }
                                            if (n9 == 8192) {
                                                continue Label_0107_Outer;
                                            }
                                            break;
                                        }
                                        int n11;
                                        n13 = (n11 = n8);
                                        int n12;
                                        n14 = (n12 = 8192);
                                        if (dj) {
                                            continue;
                                        }
                                        break;
                                    }
                                    Label_0178: {
                                        if (n13 != n14) {
                                            n7 = n9 / (n3 - 2);
                                            n.f[n4][n5][n6] = n2;
                                            n2 += n9;
                                            if (!dj) {
                                                break Label_0178;
                                            }
                                        }
                                        n.f[n4][n5][n6] = 8192;
                                    }
                                    n.d[n4][n5][n6] = (short)n7;
                                    ++n6;
                                }
                                if (n6 < 26) {
                                    continue;
                                }
                                break;
                            }
                            ++n5;
                        }
                        if (n5 < 26) {
                            continue;
                        }
                        break;
                    }
                    ++n3;
                }
                if (n3 <= 6) {
                    continue;
                }
                break;
            }
            n4 = 1;
            if (dj) {
                continue;
            }
            break;
        }
        byte b = 0;
        int n19 = 0;
    Label_0363:
        while (true) {
            while (true) {
                if (dj) {
                    break Label_0236;
                }
                Label_0391: {
                    break Label_0391;
                    int n15 = 0;
                    int n16 = 0;
                    while (true) {
                        if (b <= 3) {
                            n15 = n4;
                            n16 = 1;
                            if (!dj) {
                                break;
                            }
                        }
                        int n17 = n15 - n16;
                        while (true) {
                            Label_0354: {
                                if (!dj) {
                                    break Label_0354;
                                }
                                Label_0351: {
                                    if (n4 == 1) {
                                        final int n18 = n17 * i.e(b) / (i.e(b - n17) * i.e(n17));
                                        n.m[n17 - 1][b - 1] = (short)n18;
                                        n.j += n18;
                                        if (!dj) {
                                            break Label_0351;
                                        }
                                    }
                                    n.n[n17 - 1][b - 1] = (short)n.j;
                                    a(b, n17, 1, 1);
                                }
                                --n17;
                            }
                            if (n17 >= 3) {
                                continue;
                            }
                            break;
                        }
                        --b;
                    }
                    if (n15 == n16) {
                        n.k = new short[n.j];
                    }
                    ++n4;
                    break Label_0391;
                    n.j = 0;
                    b = 6;
                    if (dj) {}
                    continue Label_0363;
                }
                if (n4 <= 2) {
                    continue;
                }
                break;
            }
            n19 = 0;
            b = 0;
            if (dj) {
                continue Label_0363;
            }
            break;
        }
        byte b3 = 0;
        while (true) {
            Label_0706: {
                if (!dj) {
                    break Label_0706;
                }
                byte b2 = b3;
                byte b4 = 0;
                while (true) {
                    Label_0687: {
                        if (!dj) {
                            break Label_0687;
                        }
                        if (b4 != b) {
                            byte b5 = 0;
                            while (true) {
                                Label_0673: {
                                    if (!dj) {
                                        break Label_0673;
                                    }
                                    if (b5 != b2 && b5 != b) {
                                        byte b6 = 0;
                                        while (true) {
                                            Label_0659: {
                                                if (!dj) {
                                                    break Label_0659;
                                                }
                                                if (b6 != b5 && b6 != b2 && b6 != b) {
                                                    byte b7 = 0;
                                                    while (true) {
                                                        Label_0645: {
                                                            if (!dj) {
                                                                break Label_0645;
                                                            }
                                                            if (b7 != b6 && b7 != b5 && b7 != b2 && b7 != b) {
                                                                byte b8 = 0;
                                                                while (true) {
                                                                    Label_0631: {
                                                                        if (!dj) {
                                                                            break Label_0631;
                                                                        }
                                                                        if (b8 != b7 && b8 != b6 && b8 != b5 && b8 != b2 && b8 != b) {
                                                                            n.o[n19++] = b;
                                                                            n.o[n19++] = b2;
                                                                            n.o[n19++] = b5;
                                                                            n.o[n19++] = b6;
                                                                            n.o[n19++] = b7;
                                                                            n.o[n19++] = b8;
                                                                        }
                                                                        ++b8;
                                                                    }
                                                                    if (b8 < 6) {
                                                                        continue;
                                                                    }
                                                                    break;
                                                                }
                                                            }
                                                            ++b7;
                                                        }
                                                        if (b7 < 6) {
                                                            continue;
                                                        }
                                                        break;
                                                    }
                                                }
                                                ++b6;
                                            }
                                            if (b6 < 6) {
                                                continue;
                                            }
                                            break;
                                        }
                                    }
                                    ++b5;
                                }
                                if (b5 < 6) {
                                    continue;
                                }
                                break;
                            }
                        }
                        ++b2;
                    }
                    if (b2 < 6) {
                        continue;
                    }
                    b4 = (byte)(b + 1);
                    if (dj) {
                        continue;
                    }
                    break;
                }
                b = b4;
            }
            if (b < 6) {
                continue;
            }
            n19 = 0;
            b3 = 0;
            if (dj) {
                continue;
            }
            break;
        }
        byte b9 = b3;
        byte b11 = 0;
        while (true) {
            Label_0950: {
                if (!dj) {
                    break Label_0950;
                }
                byte b10 = b11;
                byte b12 = 0;
                while (true) {
                    Label_0932: {
                        if (!dj) {
                            break Label_0932;
                        }
                        if (b12 != b9) {
                            byte b13 = 0;
                            while (true) {
                                Label_0919: {
                                    if (!dj) {
                                        break Label_0919;
                                    }
                                    if (b13 != b10 && b13 != b9) {
                                        byte b14 = 0;
                                        while (true) {
                                            Label_0906: {
                                                if (!dj) {
                                                    break Label_0906;
                                                }
                                                if (b14 != b13 && b14 != b10 && b14 != b9) {
                                                    byte b15 = 0;
                                                    while (true) {
                                                        Label_0893: {
                                                            if (!dj) {
                                                                break Label_0893;
                                                            }
                                                            if (b15 != b14 && b15 != b13 && b15 != b10 && b15 != b9) {
                                                                n.p[n19++] = b9;
                                                                n.p[n19++] = b10;
                                                                n.p[n19++] = b13;
                                                                n.p[n19++] = b14;
                                                                n.p[n19++] = b15;
                                                            }
                                                            ++b15;
                                                        }
                                                        if (b15 < 5) {
                                                            continue;
                                                        }
                                                        break;
                                                    }
                                                }
                                                ++b14;
                                            }
                                            if (b14 < 5) {
                                                continue;
                                            }
                                            break;
                                        }
                                    }
                                    ++b13;
                                }
                                if (b13 < 5) {
                                    continue;
                                }
                                break;
                            }
                        }
                        ++b10;
                    }
                    if (b10 < 5) {
                        continue;
                    }
                    b12 = (byte)(b9 + 1);
                    if (dj) {
                        continue;
                    }
                    break;
                }
                b9 = b12;
            }
            if (b9 < 5) {
                continue;
            }
            n19 = 0;
            b11 = 0;
            if (dj) {
                continue;
            }
            break;
        }
        byte b16 = b11;
        byte b18 = 0;
        while (true) {
            Label_1133: {
                if (!dj) {
                    break Label_1133;
                }
                byte b17 = b18;
                byte b19 = 0;
                while (true) {
                    Label_1115: {
                        if (!dj) {
                            break Label_1115;
                        }
                        if (b19 != b16) {
                            byte b20 = 0;
                            while (true) {
                                Label_1102: {
                                    if (!dj) {
                                        break Label_1102;
                                    }
                                    if (b20 != b17 && b20 != b16) {
                                        byte b21 = 0;
                                        while (true) {
                                            Label_1089: {
                                                if (!dj) {
                                                    break Label_1089;
                                                }
                                                if (b21 != b20 && b21 != b17 && b21 != b16) {
                                                    n.q[n19++] = b16;
                                                    n.q[n19++] = b17;
                                                    n.q[n19++] = b20;
                                                    n.q[n19++] = b21;
                                                }
                                                ++b21;
                                            }
                                            if (b21 < 4) {
                                                continue;
                                            }
                                            break;
                                        }
                                    }
                                    ++b20;
                                }
                                if (b20 < 4) {
                                    continue;
                                }
                                break;
                            }
                        }
                        ++b17;
                    }
                    if (b17 < 4) {
                        continue;
                    }
                    b19 = (byte)(b16 + 1);
                    if (dj) {
                        continue;
                    }
                    break;
                }
                b16 = b19;
            }
            if (b16 < 4) {
                continue;
            }
            n19 = 0;
            b18 = 0;
            if (dj) {
                continue;
            }
            break;
        }
        byte b22 = b18;
        while (true) {
            Label_1263: {
                if (!dj) {
                    break Label_1263;
                }
                byte b23 = 0;
                byte b24 = 0;
                while (true) {
                    Label_1245: {
                        if (!dj) {
                            break Label_1245;
                        }
                        if (b24 != b22) {
                            byte b25 = 0;
                            while (true) {
                                Label_1232: {
                                    if (!dj) {
                                        break Label_1232;
                                    }
                                    if (b25 != b23 && b25 != b22) {
                                        n.r[n19++] = b22;
                                        n.r[n19++] = b23;
                                        n.r[n19++] = b25;
                                    }
                                    ++b25;
                                }
                                if (b25 < 3) {
                                    continue;
                                }
                                break;
                            }
                        }
                        ++b23;
                    }
                    if (b23 < 3) {
                        continue;
                    }
                    b24 = (byte)(b22 + 1);
                    if (dj) {
                        continue;
                    }
                    break;
                }
                b22 = b24;
            }
            if (b22 >= 3) {
                return;
            }
            continue;
        }
    }
    
    private static boolean a(final int n, final int n2, final int n3, final int n4) {
        final boolean dj = p.dJ;
        final int n5 = 0;
        int n6 = n3;
        while (true) {
            while (true) {
                Label_0092: {
                    if (!dj) {
                        break Label_0092;
                    }
                    n.l[n4 - 1] = (short)(n6 - 1);
                    Label_0089: {
                        final int n7;
                        if (n7 < n2) {
                            a(n, n2, n6 + 1, n4 + 1);
                            if (!dj) {
                                break Label_0089;
                            }
                        }
                        int n8 = 1;
                        while (true) {
                            Label_0083: {
                                if (!dj) {
                                    break Label_0083;
                                }
                                n.k[n.j++] = n.l[n8 - 1];
                                ++n8;
                            }
                            if (n8 <= n4) {
                                continue;
                            }
                            break;
                        }
                    }
                    ++n6;
                }
                if (n6 <= n - n2 + n4) {
                    continue;
                }
                break;
            }
            final int n7 = n5;
            if (!dj) {
                return n7 != 0;
            }
            continue;
        }
    }
    
    private static boolean a(final byte[] array, final int n) {
        final boolean dj = p.dJ;
        final byte[] array2 = { 0, 0, 0, 0, 0, 0 };
        boolean b = false;
        switch (n) {
            case 6: {
                int n2 = 0;
                while (true) {
                    Label_0225: {
                        if (!dj) {
                            break Label_0225;
                        }
                        if (n.d[3][array[n.o[n2]]][array[n.o[n2 + 1]]] > 0) {
                            array2[0] = array[n.o[n2]];
                            array2[1] = array[n.o[n2 + 1]];
                            array2[2] = array[n.o[n2 + 2]];
                            array2[3] = array[n.o[n2 + 3]];
                            array2[4] = array[n.o[n2 + 4]];
                            array2[5] = array[n.o[n2 + 5]];
                            if (c(array2, 6)) {
                                b = true;
                                final int a;
                                if ((a = u.a(array2, 6)) > n.h) {
                                    n.h = a;
                                    System.arraycopy(array2, 0, n.g, 0, 6);
                                }
                            }
                        }
                        n2 += 6;
                    }
                    if (n2 >= 4320) {
                        break;
                    }
                    continue;
                }
            }
            case 5: {
                int n3 = 0;
                while (true) {
                    Label_0377: {
                        if (!dj) {
                            break Label_0377;
                        }
                        if (n.d[2][array[n.p[n3]]][array[n.p[n3 + 1]]] > 0) {
                            array2[0] = array[n.p[n3]];
                            array2[1] = array[n.p[n3 + 1]];
                            array2[2] = array[n.p[n3 + 2]];
                            array2[3] = array[n.p[n3 + 3]];
                            array2[4] = array[n.p[n3 + 4]];
                            if (c(array2, 5)) {
                                b = true;
                                final int a2;
                                if ((a2 = u.a(array2, 5)) > n.h) {
                                    n.h = a2;
                                    System.arraycopy(array2, 0, n.g, 0, 5);
                                }
                            }
                        }
                        n3 += 5;
                    }
                    if (n3 >= 600) {
                        break;
                    }
                    continue;
                }
            }
            case 4: {
                int n4 = 0;
                while (true) {
                    Label_0516: {
                        if (!dj) {
                            break Label_0516;
                        }
                        if (n.d[1][array[n.q[n4]]][array[n.q[n4 + 1]]] > 0) {
                            array2[0] = array[n.q[n4]];
                            array2[1] = array[n.q[n4 + 1]];
                            array2[2] = array[n.q[n4 + 2]];
                            array2[3] = array[n.q[n4 + 3]];
                            if (c(array2, 4)) {
                                b = true;
                                final int a3;
                                if ((a3 = u.a(array2, 4)) > n.h) {
                                    n.h = a3;
                                    System.arraycopy(array2, 0, n.g, 0, 4);
                                }
                            }
                        }
                        n4 += 4;
                    }
                    if (n4 >= 96) {
                        break;
                    }
                    continue;
                }
            }
            case 3: {
                int n5 = 0;
                while (true) {
                    Label_0641: {
                        if (!dj) {
                            break Label_0641;
                        }
                        if (n.d[0][array[n.r[n5]]][array[n.r[n5 + 1]]] > 0) {
                            array2[0] = array[n.r[n5]];
                            array2[1] = array[n.r[n5 + 1]];
                            array2[2] = array[n.r[n5 + 2]];
                            if (c(array2, 3)) {
                                b = true;
                                final int a4;
                                if ((a4 = u.a(array2, 3)) > n.h) {
                                    n.h = a4;
                                    System.arraycopy(array2, 0, n.g, 0, 3);
                                }
                            }
                        }
                        n5 += 3;
                    }
                    if (n5 >= 18) {
                        break;
                    }
                    continue;
                }
            }
        }
        return b;
    }
    
    public static boolean b(final byte[] array, final int i) {
        final boolean dj = p.dJ;
        final byte[] array2 = { 0, 0, 0, 0, 0, 0 };
        boolean b = false;
        n.h = 0;
        if (!i.b(i, 3, 6)) {
            return false;
        }
        if (a(array, i)) {
            n.i = i;
            return true;
        }
        int n = i;
        boolean b2;
        while (true) {
            int n7 = 0;
            Label_0214: {
                if (!dj) {
                    break Label_0214;
                }
                int j = n - 1;
                while (true) {
                    Label_0195: {
                        if (!dj) {
                            break Label_0195;
                        }
                        final short n2 = (short)(n.m[j - 1][n - 1] / j);
                        int n3 = n.n[j - 1][n - 1];
                        short n4 = 0;
                        while (true) {
                            Label_0185: {
                                if (!dj) {
                                    break Label_0185;
                                }
                                short n5 = 0;
                                byte[] array3 = null;
                                short n6 = 0;
                                while (true) {
                                    Label_0153: {
                                        if (!dj) {
                                            break Label_0153;
                                        }
                                        array3[n6] = array[n.k[n3++]];
                                        ++n5;
                                    }
                                    if (n5 < j) {
                                        continue;
                                    }
                                    array3 = array2;
                                    n6 = (short)j;
                                    if (dj) {
                                        continue;
                                    }
                                    break;
                                }
                                if (a(array3, n6)) {
                                    b = true;
                                    n.i = j;
                                }
                                ++n4;
                            }
                            if (n4 < n2) {
                                continue;
                            }
                            break;
                        }
                        --j;
                    }
                    if (j >= 3) {
                        n7 = (b ? 1 : 0);
                        if (dj) {
                            break Label_0214;
                        }
                        if (n7 == 0) {
                            continue;
                        }
                    }
                    break;
                }
                --n;
            }
            if (n7 <= 3) {
                goto Label_0230;
            }
            b2 = b;
            if (!dj && !b2) {
                continue;
            }
            break;
        }
        return b2;
    }
    
    public static int b() {
        return n.i;
    }
    
    public static byte a(final int n) {
        return n.g[n];
    }
    
    public static boolean c(final byte[] array, final int n) {
        final boolean dj = p.dJ;
        if (!i.b(n, 3, 6)) {
            return false;
        }
        final int n2 = n - 3;
        final byte b = array[0];
        final byte b2 = array[1];
        int n3;
        if ((n3 = n.f[n2][b][b2]) == 8192) {
            return false;
        }
        final short n4 = n.d[n2][b][b2];
        final byte b3 = array[2];
        switch (n) {
            case 3: {
                short n5 = 0;
                while (true) {
                    Label_0127: {
                        if (!dj) {
                            break Label_0127;
                        }
                        if (n.c[n3++] == b3) {
                            return true;
                        }
                        ++n5;
                    }
                    if (n5 >= n4) {
                        break;
                    }
                    continue;
                }
            }
            case 4: {
                final byte b4 = array[3];
                short n6 = 0;
                while (true) {
                    Label_0182: {
                        if (!dj) {
                            break Label_0182;
                        }
                        if (n.c[n3] == b3 && n.c[n3 + 1] == b4) {
                            return true;
                        }
                        n3 += 2;
                        ++n6;
                    }
                    if (n6 >= n4) {
                        break;
                    }
                    continue;
                }
            }
            case 5: {
                final byte b5 = array[3];
                final byte b6 = array[4];
                short n7 = 0;
                while (true) {
                    Label_0255: {
                        if (!dj) {
                            break Label_0255;
                        }
                        if (n.c[n3] == b3 && n.c[n3 + 1] == b5 && n.c[n3 + 2] == b6) {
                            return true;
                        }
                        n3 += 3;
                        ++n7;
                    }
                    if (n7 >= n4) {
                        break;
                    }
                    continue;
                }
            }
            case 6: {
                final byte b7 = array[3];
                final byte b8 = array[4];
                final byte b9 = array[5];
                short n8 = 0;
                while (true) {
                    Label_0346: {
                        if (!dj) {
                            break Label_0346;
                        }
                        if (n.c[n3] == b3 && n.c[n3 + 1] == b7 && n.c[n3 + 2] == b8 && n.c[n3 + 3] == b9) {
                            return true;
                        }
                        n3 += 4;
                        ++n8;
                    }
                    if (n8 >= n4) {
                        break;
                    }
                    continue;
                }
            }
        }
        return false;
    }
    
    static {
        n.a = new short[4];
        n.d = new short[4][26][26];
        n.e = new int[4];
        n.f = new int[4][26][26];
        n.g = new byte[6];
        n.h = 0;
        n.i = 0;
        n.j = 0;
        n.k = null;
        n.l = new short[6];
        n.m = new short[6][6];
        n.n = new short[6][6];
        n.o = new byte[4320];
        n.p = new byte[600];
        n.q = new byte[96];
        n.r = new byte[18];
    }
}
