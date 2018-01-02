// 
// Decompiled by Procyon v0.5.30
// 

package ji.wang;

import ji.io.h;
import ji.util.i;
import ji.io.ac;
import java.awt.Color;
import ji.awt.d5;

class hq
{
    public int a;
    public d5 b;
    public d5 c;
    public Color d;
    public Color e;
    public boolean f;
    public boolean g;
    public int h;
    public boolean i;
    public hr j;
    
    public hq() {
    }
    
    public hq(final ac ac, final int n, final String s) throws Exception {
        this.a = a(ac.h(), ac.h() << 8, ac.h() << 16, ac.h() << 24, n);
        final int n2 = ac.h() + (ac.h() << 8) + (ac.h() << 16) + (ac.h() << 24);
        final int n3 = ac.h() + (ac.h() << 8) + (ac.h() << 16) + (ac.h() << 24);
        final int n4 = ac.h() + (ac.h() << 8) + (ac.h() << 16) + (ac.h() << 24);
        final int n5 = ac.h() + (ac.h() << 8) + (ac.h() << 16) + (ac.h() << 24);
        this.b = new d5(n2, n3);
        this.c = new d5(n4, n5);
        this.d = new Color(ac.h() + (ac.h() << 8) + (ac.h() << 16) + (ac.h() << 24));
        this.e = new Color(ac.h() + (ac.h() << 8) + (ac.h() << 16) + (ac.h() << 24));
        this.f = (ac.h() + (ac.h() << 8) + (ac.h() << 16) + (ac.h() << 24) == 1);
        this.g = (ac.h() + (ac.h() << 8) + (ac.h() << 16) + (ac.h() << 24) == 1);
        this.h = a(ac.h(), ac.h() << 8, ac.h() << 16, ac.h() << 24, n);
        ac.a(ac.r() + 8);
        final long r = ac.r();
        this.j = new hr(ac);
        ac.a(r + 60);
        ac.a(ac.r() + 4);
        ac.a(ac.r() + 4);
        this.i = (ac.h() + (ac.h() << 8) + (ac.h() << 16) + (ac.h() << 24) == 1);
        if (ji.util.i.c(5)) {
            ji.io.h.d(s, "WANG: attribute: topLeftPos = ".concat(String.valueOf(String.valueOf(this.b))));
            ji.io.h.d(s, "WANG: attribute: bottomRightPos = ".concat(String.valueOf(String.valueOf(this.c))));
            ji.io.h.d(s, "WANG: attribute: visible = ".concat(String.valueOf(String.valueOf(this.i))));
            if (this.j != null) {
                ji.io.h.d(s, "WANG: attribute: font = ".concat(String.valueOf(String.valueOf(this.j.n))));
            }
            ji.io.h.d(s, "WANG: attribute: uLineSize = ".concat(String.valueOf(String.valueOf(this.h))));
            ji.io.h.d(s, "WANG: attribute: bTransparent = ".concat(String.valueOf(String.valueOf(this.g))));
            ji.io.h.d(s, "WANG: attribute: bHighlighting = ".concat(String.valueOf(String.valueOf(this.f))));
            ji.io.h.d(s, "WANG: attribute: rgbColor1 = ".concat(String.valueOf(String.valueOf(this.d))));
            ji.io.h.d(s, "WANG: attribute: rgbColor2 = ".concat(String.valueOf(String.valueOf(this.e))));
        }
    }
    
    public static int a(final int n, final int n2, final int n3, final int n4, final int n5) {
        int n6 = n + (n2 << 8);
        if (n5 == 1) {
            n6 = n6 + (n3 << 16) + (n4 << 24);
        }
        return n6;
    }
    
    public static byte[] a(final byte[] array, final int n, final int n2) {
        array[n] = (byte)(n2 & 0xFF);
        array[n + 1] = (byte)((n2 & 0xFF00) >> 8);
        array[n + 2] = (byte)((n2 & 0xFF0000) >> 16);
        array[n + 3] = (byte)((n2 & 0xFF000000) >> 24);
        return array;
    }
    
    public int a() {
        return 164;
    }
    
    public void a(final ac ac) throws Exception {
        final byte[] array = new byte[164];
        int n = 0;
        final byte[] a = a(array, n, this.a);
        n += 4;
        final byte[] a2 = a(a, n, this.b.a().x);
        n += 4;
        final byte[] a3 = a(a2, n, this.b.a().y);
        n += 4;
        final byte[] a4 = a(a3, n, this.c.a().x);
        n += 4;
        final byte[] a5 = a(a4, n, this.c.a().y);
        byte[] array3;
        if (this.d != null) {
            final byte[] array2 = a5;
            n += 4;
            array3 = a(array2, n, this.d.getRGB());
        }
        else {
            final byte[] array4 = a5;
            n += 4;
            array3 = a(array4, n, 0);
        }
        byte[] array6;
        if (this.e != null) {
            final byte[] array5 = array3;
            n += 4;
            array6 = a(array5, n, this.e.getRGB());
        }
        else {
            final byte[] array7 = array3;
            n += 4;
            array6 = a(array7, n, 0);
        }
        final byte[] array8 = array6;
        n += 4;
        final byte[] a6 = a(array8, n, this.f ? 1 : 0);
        n += 4;
        final byte[] a7 = a(a6, n, this.g ? 1 : 0);
        n += 4;
        final byte[] a8 = a(a7, n, this.h);
        n += 4;
        n += 8;
        final byte[] a9 = this.j.a(a8, n);
        n += 60;
        n += 8;
        final byte[] a10 = a(a9, n, this.i ? 1 : 0);
        n += 4;
        a10[n] = 63;
        ++n;
        a10[n] = -8;
        ++n;
        a10[n] = 15;
        ++n;
        a10[n] = 0;
        ac.b(a(a10, n, 0));
    }
}
