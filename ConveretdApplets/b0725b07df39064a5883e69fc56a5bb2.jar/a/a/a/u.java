// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a;

final class u implements h
{
    private boolean[] a;
    private static m[] b;
    private int c;
    private int d;
    private int[] e;
    private int[] f;
    
    public final void a(final int n) {
    }
    
    public final String toString() {
        return "SEGA SK-1100 Keyboard Controller Emulation";
    }
    
    public u() {
        this.a = new boolean[1200];
        this.e = new int[8];
        this.f = new int[2];
        this.b();
    }
    
    public final boolean a() {
        this.b();
        return true;
    }
    
    public final boolean b() {
        for (int i = 0; i < 8; ++i) {
            this.e[i] = 65535;
        }
        return true;
    }
    
    public final char a(final char c) {
        char c2 = '\0';
        switch (c & '\u000f') {
            case '\f': {
                this = this;
                int n = 255;
                if (this.c == 7) {
                    if ((this.f[0] & 0x1) != 0x0) {
                        n = 254;
                    }
                    if ((this.f[0] & 0x2) != 0x0) {
                        n &= 0xFFFFFFFD;
                    }
                    if ((this.f[0] & 0x4) != 0x0) {
                        n &= 0xFFFFFFFB;
                    }
                    if ((this.f[0] & 0x8) != 0x0) {
                        n &= 0xFFFFFFF7;
                    }
                    if ((this.f[0] & 0x20) != 0x0) {
                        n &= 0xFFFFFFEF;
                    }
                    if ((this.f[0] & 0x10) != 0x0) {
                        n &= 0xFFFFFFDF;
                    }
                    if ((this.f[1] & 0x1) != 0x0) {
                        n &= 0xFFFFFFBF;
                    }
                    if ((this.f[1] & 0x2) != 0x0) {
                        n &= 0xFFFFFF7F;
                    }
                }
                else {
                    n = (this.e[this.c] & 0xFF);
                }
                c2 = (char)n;
                break;
            }
            case '\r': {
                this = this;
                int n2 = 255;
                int n3;
                if (this.c == 7) {
                    if ((this.f[1] & 0x4) != 0x0) {
                        n2 = 254;
                    }
                    if ((this.f[1] & 0x8) != 0x0) {
                        n2 &= 0xFFFFFFFD;
                    }
                    if ((this.f[1] & 0x20) != 0x0) {
                        n2 &= 0xFFFFFFFB;
                    }
                    if ((this.f[1] & 0x10) != 0x0) {
                        n2 &= 0xFFFFFFF7;
                    }
                    n3 = (n2 & 0x3F);
                }
                else {
                    n3 = this.e[this.c] >> 8;
                }
                c2 = (char)(n3 & 0x7F);
                break;
            }
            case '\u000e': {
                c2 = (char)(this = this).d;
                break;
            }
            default: {
                c2 = '\u00ff';
                break;
            }
        }
        return c2;
    }
    
    public final void a(final char c, final char c2) {
        if ((c & '\u000f') == '\u000e') {
            final u u = this;
            final char c3 = (char)(c2 & '\u00ff');
            this = u;
            u.d = (c3 & '\u00ff');
            this.c = (this.d & 0x7);
        }
    }
    
    public final void b(final int n) {
        this.a[n] = true;
    }
    
    public final void c(final int n) {
        this.a[n] = false;
    }
    
    public final void c() {
        this = this;
        for (int i = 0; i < 8; ++i) {
            this.e[i] = 65535;
        }
        for (int j = 0; j < u.b.length; ++j) {
            final m m = u.b[j];
            if (this.a[m.a]) {
                final int[] e = this.e;
                final int b = m.b;
                e[b] &= ~m.c;
            }
        }
    }
    
    static {
        u.b = new m[] { new m(49, 0, 1, "1"), new m(81, 0, 2, "Q"), new m(65, 0, 4, "A"), new m(90, 0, 8, "Z"), new m(3, 0, 16, "Eng Dier's"), new m(18, 0, 16, "Eng Dier's"), new m(44, 0, 32, ","), new m(75, 0, 64, "K"), new m(73, 0, 128, "I"), new m(56, 0, 256, "8"), new m(50, 1, 1, "2"), new m(87, 1, 2, "W"), new m(83, 1, 4, "S"), new m(88, 1, 8, "X"), new m(32, 1, 16, "Space"), new m(46, 1, 32, "."), new m(76, 1, 64, "L"), new m(79, 1, 128, "O"), new m(57, 1, 256, "9"), new m(51, 2, 1, "3"), new m(69, 2, 2, "E"), new m(68, 2, 4, "D"), new m(67, 2, 8, "C"), new m(618, 2, 16, "Home/Clear"), new m(34, 2, 16, "Home/Clear"), new m(47, 2, 32, "/"), new m(59, 2, 64, ";"), new m(80, 2, 128, "P"), new m(48, 2, 256, "0"), new m(52, 3, 1, "4"), new m(82, 3, 2, "R"), new m(70, 3, 4, "F"), new m(86, 3, 8, "V"), new m(617, 3, 16, "Insert/Delete"), new m(8, 3, 16, "Insert/Delete"), new m(1024, 3, 64, ":"), new m(1042, 3, 128, "@"), new m(45, 3, 256, "-"), new m(53, 4, 1, "5"), new m(84, 4, 2, "T"), new m(71, 4, 4, "G"), new m(66, 4, 8, "B"), new m(40, 4, 32, "Down Arrow"), new m(521, 4, 64, "]"), new m(1032, 4, 128, "["), new m(1036, 4, 256, "^"), new m(54, 5, 1, "6"), new m(89, 5, 2, "Y"), new m(72, 5, 4, "H"), new m(78, 5, 8, "N"), new m(37, 5, 32, "Left Arrow"), new m(10, 5, 64, "Return"), new m(128, 5, 256, "Grave"), new m(9, 5, 2048, "Func"), new m(92, 5, 2048, "Func"), new m(55, 6, 1, "7"), new m(85, 6, 2, "U"), new m(74, 6, 4, "J"), new m(77, 6, 8, "M"), new m(39, 6, 32, "Right Arrow"), new m(38, 6, 64, "Up Arrow"), new m(35, 6, 256, "Break"), new m(17, 6, 512, "Graph"), new m(20, 6, 1024, "Ctrl"), new m(16, 6, 2048, "Shift") };
        final m[] array = { new m(38, 7, 1, "Up Arrow"), new m(40, 7, 2, "Down Arrow"), new m(37, 7, 4, "Left Arrow"), new m(39, 7, 8, "Right Arrow"), new m(65, 7, 16, "Button1"), new m(83, 7, 32, "Button2") };
    }
}
