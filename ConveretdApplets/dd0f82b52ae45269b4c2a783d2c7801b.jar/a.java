import javax.swing.JOptionPane;
import java.util.zip.CRC32;

// 
// Decompiled by Procyon v0.5.30
// 

public final class a
{
    private boolean b;
    private boolean c;
    private short[] d;
    private short[][] e;
    private short[][] f;
    private short[] g;
    private ab[][] h;
    private V i;
    private int j;
    private int k;
    private int l;
    boolean a;
    private boolean m;
    private int n;
    private boolean o;
    private boolean p;
    private static String[] q;
    private static boolean[] r;
    private long s;
    
    public a(final V i) {
        this.b = false;
        this.c = true;
        this.o = true;
        this.s = 0L;
        this.i = i;
        this.p = false;
    }
    
    public final void a(final String s) {
        final short[] a;
        if ((a = new k().a(s, this.i.a)) == null || a.length == 0) {
            System.out.println("Unable to load ROM file.");
            this.p = false;
        }
        this.d = new short[16];
        for (int i = 0; i < 16; ++i) {
            this.d[i] = a[i];
        }
        if (!new String(new byte[] { (byte)a[0], (byte)a[1], (byte)a[2], (byte)a[3] }).equals("NES" + new String(new byte[] { 26 }))) {
            this.p = false;
            return;
        }
        this.j = this.d[4];
        this.k = this.d[5] << 1;
        this.l = (((this.d[6] & 0x1) != 0x0) ? 1 : 0);
        this.a = ((this.d[6] & 0x2) != 0x0);
        this.m = ((this.d[6] & 0x8) != 0x0);
        this.n = (this.d[6] >> 4 | (this.d[7] & 0xF0));
        if (this.a) {
            this.i();
        }
        boolean b = false;
        for (int j = 8; j < 16; ++j) {
            if (this.d[j] != 0) {
                b = true;
                break;
            }
        }
        if (b) {
            this.n &= 0xF;
        }
        this.e = new short[this.j][16384];
        this.f = new short[this.k][4096];
        this.h = new ab[this.k][256];
        int n = 16;
        for (int k = 0; k < this.j; ++k) {
            for (int n2 = 0; n2 < 16384 && n + n2 < a.length; ++n2) {
                this.e[k][n2] = a[n + n2];
            }
            n += 16384;
        }
        for (int l = 0; l < this.k; ++l) {
            for (int n3 = 0; n3 < 4096 && n + n3 < a.length; ++n3) {
                this.f[l][n3] = a[n + n3];
            }
            n += 4096;
        }
        for (int n4 = 0; n4 < this.k; ++n4) {
            for (int n5 = 0; n5 < 256; ++n5) {
                this.h[n4][n5] = new ab();
            }
        }
        for (int n6 = 0; n6 < this.k; ++n6) {
            for (int n7 = 0; n7 < 4096; ++n7) {
                final int n8 = n7 >> 4;
                final int n9;
                if ((n9 = n7 % 16) < 8) {
                    this.h[n6][n8].a(n9, this.f[n6][n7], this.f[n6][n7 + 8]);
                }
                else {
                    this.h[n6][n8].a(n9 - 8, this.f[n6][n7 - 8], this.f[n6][n7]);
                }
            }
        }
        final CRC32 crc32 = new CRC32();
        crc32.update(new byte[this.e.length + this.f.length]);
        this.s = crc32.getValue();
        System.out.println("CRC Value: " + this.s + "");
        this.p = true;
    }
    
    public final boolean a() {
        return this.p;
    }
    
    public final int b() {
        return this.j;
    }
    
    public final int c() {
        return this.k;
    }
    
    public final short[] a(final int n) {
        return this.e[n];
    }
    
    public final short[] b(final int n) {
        return this.f[n];
    }
    
    public final ab[] c(final int n) {
        return this.h[n];
    }
    
    public final int d() {
        if (this.m) {
            return 2;
        }
        if (this.l == 0) {
            return 1;
        }
        return 0;
    }
    
    public final ag e() {
        if (this.n < a.r.length && this.n >= 0 && a.r[this.n]) {
            switch (this.n) {
                case 0: {
                    return new ag();
                }
                case 1: {
                    return new u();
                }
                case 2: {
                    return new t();
                }
                case 3: {
                    return new s();
                }
                case 4: {
                    return new r();
                }
                case 7: {
                    return new v();
                }
                case 9: {
                    return new z();
                }
                case 10: {
                    return new S();
                }
                case 11: {
                    return new T();
                }
                case 15: {
                    return new N();
                }
                case 18: {
                    return new L();
                }
                case 21: {
                    return new ae();
                }
                case 22: {
                    return new af();
                }
                case 23: {
                    return new W();
                }
                case 32: {
                    return new m();
                }
                case 33: {
                    return new l();
                }
                case 34: {
                    return new c();
                }
                case 48: {
                    return new y();
                }
                case 64: {
                    return new ac();
                }
                case 66: {
                    return new aa();
                }
                case 68: {
                    return new Y();
                }
                case 71: {
                    return new e();
                }
                case 72: {
                    return new d();
                }
                case 75: {
                    return new f();
                }
                case 78: {
                    return new j();
                }
                case 79: {
                    return new i();
                }
                case 87: {
                    return new C();
                }
                case 94: {
                    return new Q();
                }
                case 105: {
                    return new U();
                }
                case 140: {
                    return new E();
                }
                case 182: {
                    return new H();
                }
                case 232: {
                    return new D();
                }
            }
        }
        System.out.println("Warning: Mapper not supported yet.");
        return new ag();
    }
    
    public final void a(final boolean b) {
        if (b && !this.a) {
            this.i();
        }
    }
    
    public final short[] f() {
        return this.g;
    }
    
    private void i() {
        if (this.a) {
            try {
                this.g = new short[8192];
                this.c = true;
                final String showInputDialog;
                if ((showInputDialog = JOptionPane.showInputDialog("Returning players insert Save Code here.")) == null) {
                    return;
                }
                final String replaceAll;
                if ((replaceAll = showInputDialog.replaceAll("[^\\p{XDigit}]", "")).length() != this.g.length << 1) {
                    return;
                }
                for (int i = 0; i < this.g.length; ++i) {
                    this.g[i] = Short.parseShort(replaceAll.substring(i << 1, (i << 1) + 2), 16);
                }
                if (this.i.h != null) {
                    this.i.h.c();
                }
            }
            catch (Exception ex) {
                this.b = true;
            }
        }
    }
    
    public final void a(final int n, final short n2) {
        if (!this.b && !this.a && this.o) {
            this.i();
        }
        if (this.a && this.o && !this.b) {
            this.g[n - 24576] = n2;
            this.c = false;
        }
    }
    
    public final void g() {
        if (this.a && !this.c) {
            try {
                final StringBuilder sb = new StringBuilder((this.g.length << 1) + this.g.length / 38);
                for (int i = 0; i < this.g.length; ++i) {
                    final String format = String.format("%02x", this.g[i] & 0xFF);
                    if (i % 38 == 0 && i != 0) {
                        sb.append(" ");
                    }
                    sb.append(format);
                }
                JOptionPane.showInputDialog("Save Code for Resuming Game.", sb.toString());
                this.c = true;
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public final void h() {
        this.g();
        this.i = null;
    }
    
    static {
        a.q = new String[255];
        a.r = new boolean[255];
        for (int i = 0; i < 255; ++i) {
            a.q[i] = "Unknown Mapper";
        }
        a.q[0] = "NROM";
        a.q[1] = "Nintendo MMC1";
        a.q[2] = "UxROM";
        a.q[3] = "CNROM";
        a.q[4] = "Nintendo MMC3";
        a.q[5] = "Nintendo MMC5";
        a.q[6] = "FFE F4xxx";
        a.q[7] = "AxROM";
        a.q[8] = "FFE F3xxx";
        a.q[9] = "Nintendo MMC2";
        a.q[10] = "Nintendo MMC4";
        a.q[11] = "Color Dreams";
        a.q[12] = "FFE F6xxx";
        a.q[13] = "CPROM";
        a.q[15] = "iNES Mapper #015";
        a.q[16] = "Bandai";
        a.q[17] = "FFE F8xxx";
        a.q[18] = "Jaleco SS8806";
        a.q[19] = "Namcot 106";
        a.q[20] = "(Hardware) Famicom Disk System";
        a.q[21] = "Konami VRC4a, VRC4c";
        a.q[22] = "Konami VRC2a";
        a.q[23] = "Konami VRC2b, VRC4e, VRC4f";
        a.q[24] = "Konami VRC6a";
        a.q[25] = "Konami VRC4b, VRC4d";
        a.q[26] = "Konami VRC6b";
        a.q[32] = "Irem G-101";
        a.q[33] = "Taito TC0190, TC0350";
        a.q[34] = "BxROM, NINA-001";
        a.q[41] = "Caltron 6-in-1";
        a.q[46] = "Rumblestation 15-in-1";
        a.q[47] = "Nintendo MMC3 Multicart (Super Spike V'Ball + Nintendo World Cup)";
        a.q[48] = "iNES Mapper #048";
        a.q[64] = "Tengen RAMBO-1";
        a.q[65] = "Irem H-3001";
        a.q[66] = "GxROM";
        a.q[67] = "Sunsoft 3";
        a.q[68] = "Sunsoft 4";
        a.q[69] = "Sunsoft FME-7";
        a.q[70] = "iNES Mapper #070";
        a.q[71] = "Camerica";
        a.q[72] = "iNES Mapper #072";
        a.q[73] = "Konami VRC3";
        a.q[75] = "Konami VRC1";
        a.q[76] = "iNES Mapper #076 (Digital Devil Monogatari - Megami Tensei)";
        a.q[77] = "iNES Mapper #077 (Napoleon Senki)";
        a.q[78] = "Irem 74HC161/32";
        a.q[79] = "American Game Cartridges";
        a.q[80] = "iNES Mapper #080";
        a.q[82] = "iNES Mapper #082";
        a.q[85] = "Konami VRC7a, VRC7b";
        a.q[86] = "iNES Mapper #086 (Moero!! Pro Yakyuu)";
        a.q[87] = "iNES Mapper #087";
        a.q[88] = "iNES Mapper #088";
        a.q[89] = "iNES Mapper #087 (Mito Koumon)";
        a.q[92] = "iNES Mapper #092";
        a.q[93] = "iNES Mapper #093 (Fantasy Zone)";
        a.q[94] = "iNES Mapper #094 (Senjou no Ookami)";
        a.q[95] = "iNES Mapper #095 (Dragon Buster) [MMC3 Derived]";
        a.q[96] = "(Hardware) Oeka Kids Tablet";
        a.q[97] = "iNES Mapper #097 (Kaiketsu Yanchamaru)";
        a.q[105] = "NES-EVENT [MMC1 Derived]";
        a.q[113] = "iNES Mapper #113";
        a.q[115] = "iNES Mapper #115 (Yuu Yuu Hakusho Final) [MMC3 Derived]";
        a.q[118] = "iNES Mapper #118 [MMC3 Derived]";
        a.q[119] = "TQROM";
        a.q[140] = "iNES Mapper #140 (Bio Senshi Dan)";
        a.q[152] = "iNES Mapper #152";
        a.q[154] = "iNES Mapper #152 (Devil Man)";
        a.q[159] = "Bandai (Alternate of #016)";
        a.q[180] = "(Hardware) Crazy Climber Controller";
        a.q[182] = "iNES Mapper #182";
        a.q[184] = "iNES Mapper #184";
        a.q[185] = "iNES Mapper #185";
        a.q[207] = "iNES Mapper #185 (Fudou Myouou Den)";
        a.q[228] = "Active Enterprises";
        a.q[232] = "Camerica (Quattro series)";
        a.r[0] = true;
        a.r[1] = true;
        a.r[2] = true;
        a.r[3] = true;
        a.r[4] = true;
        a.r[7] = true;
        a.r[9] = true;
        a.r[10] = true;
        a.r[11] = true;
        a.r[15] = true;
        a.r[18] = true;
        a.r[21] = true;
        a.r[22] = true;
        a.r[23] = true;
        a.r[32] = true;
        a.r[33] = true;
        a.r[34] = true;
        a.r[48] = true;
        a.r[64] = true;
        a.r[66] = true;
        a.r[68] = true;
        a.r[71] = true;
        a.r[72] = true;
        a.r[75] = true;
        a.r[78] = true;
        a.r[79] = true;
        a.r[87] = true;
        a.r[94] = true;
        a.r[105] = true;
        a.r[140] = true;
        a.r[182] = true;
        a.r[232] = true;
    }
}
