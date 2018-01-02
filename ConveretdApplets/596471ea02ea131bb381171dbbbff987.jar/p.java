import java.awt.Color;
import java.io.InputStream;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;

// 
// Decompiled by Procyon v0.5.30
// 

public final class p
{
    private static int[] a;
    private static int[] b;
    private static int[][] c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    
    public p() {
        this.d = -1;
    }
    
    public final boolean a() {
        return this.a("palettes/ntsc.txt");
    }
    
    private boolean a(final String s) {
        try {
            if (s.toLowerCase().endsWith("pal")) {
                final InputStream resourceAsStream = this.getClass().getResourceAsStream(s);
                final byte[] array = new byte[192];
                for (int i = 0; i < 64; i += resourceAsStream.read(array, i, array.length - i)) {}
                final int[] array2 = new int[192];
                for (int j = 0; j < array.length; ++j) {
                    array2[j] = (array[j] & 0xFF);
                }
                for (int k = 0; k < 64; ++k) {
                    p.b[k] = (array2[k * 3] | array2[k * 3 + 1] << 8 | array2[k * 3 + 2] << 16);
                }
            }
            else {
                final BufferedReader bufferedReader;
                String s2 = (bufferedReader = new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(s)))).readLine();
                int n = 0;
                while (s2 != null) {
                    if (s2.startsWith("#")) {
                        p.b[n] = (Integer.decode("0x" + s2.substring(1, 3)) | Integer.decode("0x" + s2.substring(3, 5)) << 8 | Integer.decode("0x" + s2.substring(5, 7)) << 16);
                        ++n;
                    }
                    s2 = bufferedReader.readLine();
                }
            }
            this.a(0);
            this.d();
            this.e();
            return true;
        }
        catch (Exception ex) {
            System.out.println("PaletteTable: Internal Palette Loaded.");
            this.b();
            return false;
        }
    }
    
    private void d() {
        for (int i = 0; i < 8; ++i) {
            float n = 1.0f;
            float n2 = 1.0f;
            float n3 = 1.0f;
            if ((i & 0x1) != 0x0) {
                n = 0.75f;
                n3 = 0.75f;
            }
            if ((i & 0x2) != 0x0) {
                n = 0.75f;
                n2 = 0.75f;
            }
            if ((i & 0x4) != 0x0) {
                n2 = 0.75f;
                n3 = 0.75f;
            }
            for (int j = 0; j < 64; ++j) {
                final int n4;
                p.c[i][j] = a((int)(c(n4 = p.b[j]) * n), (int)(d(n4) * n2), (int)((n4 & 0xFF) * n3));
            }
        }
    }
    
    public final void a(final int d) {
        if (d != this.d) {
            this.d = d;
            for (int i = 0; i < 64; ++i) {
                p.a[i] = p.c[d][i];
            }
            this.e();
        }
    }
    
    public static int b(final int n) {
        return p.a[n];
    }
    
    private static int c(final int n) {
        return n >> 16 & 0xFF;
    }
    
    private static int d(final int n) {
        return n >> 8 & 0xFF;
    }
    
    private static int a(final int n, final int n2, final int n3) {
        return n << 16 | n2 << 8 | n3;
    }
    
    private void e() {
        final int e = this.e;
        final int f = this.f;
        final int g = this.g;
        int h = this.h;
        final int g2 = g;
        final int f2 = f;
        final int e2 = e;
        if (h > 0) {
            h <<= 2;
        }
        for (int i = 0; i < 64; ++i) {
            final int n = p.c[this.d][i];
            final float[] rgBtoHSB;
            final float[] array = rgBtoHSB = Color.RGBtoHSB(n & 0xFF, n >> 8 & 0xFF, n >> 16 & 0xFF, new float[3]);
            final int n2 = 0;
            array[n2] -= (float)Math.floor(rgBtoHSB[0]);
            final int n4;
            int n3 = ((n4 = (0x0 | (int)(rgBtoHSB[0] * 255.0) << 16 | (int)(rgBtoHSB[1] * 255.0) << 8 | (int)(rgBtoHSB[2] * 255.0))) >> 16 & 0xFF) + e2;
            int n5 = (int)((n4 >> 8 & 0xFF) * (1.0 + f2 / 256.0f));
            int n6 = n4 & 0xFF;
            if (n3 < 0) {
                n3 += 255;
            }
            if (n5 < 0) {
                n5 = 0;
            }
            if (n6 < 0) {
                n6 = 0;
            }
            if (n3 > 255) {
                n3 -= 255;
            }
            if (n5 > 255) {
                n5 = 255;
            }
            if (n6 > 255) {
                n6 = 255;
            }
            final int hsBtoRGB;
            final int c = c(hsBtoRGB = Color.HSBtoRGB(n3 / 255.0f, n5 / 255.0f, n6 / 255.0f));
            final int d = d(hsBtoRGB);
            final int n7 = hsBtoRGB & 0xFF;
            int n8 = g2 + 128 + (int)((c - 128) * (1.0 + h / 256.0f));
            int n9 = g2 + 128 + (int)((d - 128) * (1.0 + h / 256.0f));
            int n10 = g2 + 128 + (int)((n7 - 128) * (1.0 + h / 256.0f));
            if (n8 < 0) {
                n8 = 0;
            }
            if (n9 < 0) {
                n9 = 0;
            }
            if (n10 < 0) {
                n10 = 0;
            }
            if (n8 > 255) {
                n8 = 255;
            }
            if (n9 > 255) {
                n9 = 255;
            }
            if (n10 > 255) {
                n10 = 255;
            }
            p.a[i] = a(n8, n9, n10);
        }
        this.e = e2;
        this.f = f2;
        this.g = g2;
        this.h = h;
    }
    
    public final void b() {
        if (p.b == null) {
            p.b = new int[64];
        }
        p.b[0] = a(124, 124, 124);
        p.b[1] = a(0, 0, 252);
        p.b[2] = a(0, 0, 188);
        p.b[3] = a(68, 40, 188);
        p.b[4] = a(148, 0, 132);
        p.b[5] = a(168, 0, 32);
        p.b[6] = a(168, 16, 0);
        p.b[7] = a(136, 20, 0);
        p.b[8] = a(80, 48, 0);
        p.b[9] = a(0, 120, 0);
        p.b[10] = a(0, 104, 0);
        p.b[11] = a(0, 88, 0);
        p.b[12] = a(0, 64, 88);
        p.b[13] = a(0, 0, 0);
        p.b[14] = a(0, 0, 0);
        p.b[15] = a(0, 0, 0);
        p.b[16] = a(188, 188, 188);
        p.b[17] = a(0, 120, 248);
        p.b[18] = a(0, 88, 248);
        p.b[19] = a(104, 68, 252);
        p.b[20] = a(216, 0, 204);
        p.b[21] = a(228, 0, 88);
        p.b[22] = a(248, 56, 0);
        p.b[23] = a(228, 92, 16);
        p.b[24] = a(172, 124, 0);
        p.b[25] = a(0, 184, 0);
        p.b[26] = a(0, 168, 0);
        p.b[27] = a(0, 168, 68);
        p.b[28] = a(0, 136, 136);
        p.b[29] = a(0, 0, 0);
        p.b[30] = a(0, 0, 0);
        p.b[31] = a(0, 0, 0);
        p.b[32] = a(248, 248, 248);
        p.b[33] = a(60, 188, 252);
        p.b[34] = a(104, 136, 252);
        p.b[35] = a(152, 120, 248);
        p.b[36] = a(248, 120, 248);
        p.b[37] = a(248, 88, 152);
        p.b[38] = a(248, 120, 88);
        p.b[39] = a(252, 160, 68);
        p.b[40] = a(248, 184, 0);
        p.b[41] = a(184, 248, 24);
        p.b[42] = a(88, 216, 84);
        p.b[43] = a(88, 248, 152);
        p.b[44] = a(0, 232, 216);
        p.b[45] = a(120, 120, 120);
        p.b[46] = a(0, 0, 0);
        p.b[47] = a(0, 0, 0);
        p.b[48] = a(252, 252, 252);
        p.b[49] = a(164, 228, 252);
        p.b[50] = a(184, 184, 248);
        p.b[51] = a(216, 184, 248);
        p.b[52] = a(248, 184, 248);
        p.b[53] = a(248, 164, 192);
        p.b[54] = a(240, 208, 176);
        p.b[55] = a(252, 224, 168);
        p.b[56] = a(248, 216, 120);
        p.b[57] = a(216, 248, 120);
        p.b[58] = a(184, 248, 184);
        p.b[59] = a(184, 248, 216);
        p.b[60] = a(0, 252, 252);
        p.b[61] = a(216, 216, 16);
        p.b[62] = a(0, 0, 0);
        p.b[63] = a(0, 0, 0);
        this.a(0);
        this.d();
    }
    
    public final void c() {
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.a(this.g = 0);
        this.e();
    }
    
    static {
        p.a = new int[64];
        p.b = new int[64];
        p.c = new int[8][64];
    }
}
