// 
// Decompiled by Procyon v0.5.30
// 

package ji.wang;

import ji.io.ac;

class hr
{
    int a;
    int b;
    int c;
    int d;
    int e;
    boolean f;
    boolean g;
    boolean h;
    byte i;
    byte j;
    byte k;
    byte l;
    byte m;
    String n;
    
    public hr() {
        this.n = "Courier";
        this.a = 16;
        this.e = 400;
        this.i = 1;
        this.j = 1;
        this.k = 2;
        this.l = 1;
        this.m = 49;
    }
    
    public hr(final ac ac) throws Exception {
        this.a = ac.h() + (ac.h() << 8) + (ac.h() << 16) + (ac.h() << 24);
        this.b = ac.h() + (ac.h() << 8) + (ac.h() << 16) + (ac.h() << 24);
        this.c = ac.h() + (ac.h() << 8) + (ac.h() << 16) + (ac.h() << 24);
        this.d = ac.h() + (ac.h() << 8) + (ac.h() << 16) + (ac.h() << 24);
        this.e = ac.h() + (ac.h() << 8) + (ac.h() << 16) + (ac.h() << 24);
        this.f = (ac.h() == 1);
        this.g = (ac.h() == 1);
        this.h = (ac.h() == 1);
        this.i = (byte)ac.h();
        this.j = (byte)ac.h();
        this.k = (byte)ac.h();
        this.l = (byte)ac.h();
        this.m = (byte)ac.h();
        final StringBuffer sb = new StringBuffer();
        try {
            char c = (char)ac.h();
            for (int n = 0; n < 32 && c != '\0'; c = (char)ac.h(), ++n) {
                sb.append(c);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.n = sb.toString();
    }
    
    public byte[] a(byte[] a, int n) {
        final byte[] a2;
        a = (a2 = hq.a(a, n, this.a));
        n += 4;
        final byte[] a3;
        a = (a3 = hq.a(a2, n, this.b));
        n += 4;
        final byte[] a4;
        a = (a4 = hq.a(a3, n, this.c));
        n += 4;
        final byte[] a5;
        a = (a5 = hq.a(a4, n, this.d));
        n += 4;
        a = hq.a(a5, n, this.e);
        n += 4;
        a[n++] = (byte)(this.f ? 1 : 0);
        a[n++] = (byte)(this.g ? 1 : 0);
        a[n++] = (byte)(this.h ? 1 : 0);
        a[n++] = this.i;
        a[n++] = this.j;
        a[n++] = this.k;
        a[n++] = this.l;
        a[n++] = this.m;
        final byte[] bytes = this.n.getBytes();
        for (int i = 0; i < bytes.length; ++i) {
            a[n++] = bytes[i];
        }
        for (int j = 0; j < 32 - bytes.length; ++j) {
            a[n++] = 0;
        }
        return a;
    }
}
