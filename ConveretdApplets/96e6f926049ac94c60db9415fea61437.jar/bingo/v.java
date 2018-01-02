// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.bb;
import neat.cb;
import neat.kb;
import neat.system.f;

public class v extends u
{
    private static f o;
    private static /* synthetic */ Class p;
    private static String[] z;
    
    public int a(final kb kb) {
        final vc vc = (vc)this.a();
        int n = vc.m;
        boolean b = false;
        if (vc.n != null) {
            final mc mc = (mc)vc.n.c(kb);
            if (mc != null) {
                n = n * mc.e / 1000;
                b = true;
            }
        }
        if (!b) {
            n = 0;
        }
        if (this.m instanceof vc) {
            int m = ((vc)this.m).m;
            boolean b2 = false;
            if (((vc)this.m).n != null) {
                final mc mc2 = (mc)((vc)this.m).n.c(kb);
                if (mc2 != null) {
                    m = m * mc2.e / 1000;
                    b2 = true;
                }
            }
            if (!b2 && !b) {
                n = 0;
            }
            else {
                n = this.a(n, m);
            }
        }
        return n;
    }
    
    static final neat.cb a(final vc vc, final int n) {
        if (vc.k != null) {
            return vc.k;
        }
        if (vc.l == null) {
            return null;
        }
        if (n < 0 || vc.l.a() <= 0) {
            return null;
        }
        return (neat.cb)vc.l.c(n % vc.l.a());
    }
    
    public ob a() {
        final neat.cb a = a((vc)this.a(), this.l());
        if (a == null) {
            return null;
        }
        final neat.bb a2 = a.a();
        if (!(a2 instanceof ob)) {
            throw new RuntimeException(v.z[0] + a);
        }
        final bingo.bb a3 = this.a();
        if (a3 != null) {
            a3.d(a.e);
        }
        return (ob)a2;
    }
    
    public int b() {
        final vc vc = (vc)this.a();
        int n = 0;
        if (vc.o != null) {
            n = vc.o.e;
            if (this.m instanceof vc && ((vc)this.m).o != null) {
                n = this.a(n, ((vc)this.m).o.e);
            }
        }
        return n;
    }
    
    public int c() {
        final vc vc = (vc)this.a();
        int f = 0;
        if (vc.o != null) {
            f = vc.o.f;
        }
        return f;
    }
    
    public int d() {
        final vc vc = (vc)this.a();
        int n = 0;
        if (vc.o != null) {
            n = vc.o.g;
            if (this.m instanceof vc && ((vc)this.m).o != null) {
                n = this.a(n, ((vc)this.m).o.g);
            }
        }
        return n;
    }
    
    public int e() {
        final vc vc = (vc)this.a();
        int n = 0;
        if (vc.o != null) {
            n = vc.o.h;
            if (this.m instanceof vc && ((vc)this.m).o != null) {
                n = this.a(n, ((vc)this.m).o.h);
            }
        }
        return n;
    }
    
    public float f() {
        final vc vc = (vc)this.a();
        float n = -1.0f;
        if (vc.o != null) {
            n = vc.o.j;
            if (n >= 0.0f && this.m instanceof vc && ((vc)this.m).o != null && ((vc)this.m).o.j >= 0.0f) {
                n = this.a(n, ((vc)this.m).o.j);
            }
        }
        return n;
    }
    
    public float g() {
        final vc vc = (vc)this.a();
        float n = -1.0f;
        if (vc.o != null) {
            n = vc.o.k;
            if (n >= 0.0f && this.m instanceof vc && ((vc)this.m).o != null && ((vc)this.m).o.k >= 0.0f) {
                n = this.a(n, ((vc)this.m).o.k);
            }
        }
        return n;
    }
    
    public float h() {
        final vc vc = (vc)this.a();
        float n = -1.0f;
        if (vc.o != null) {
            n = vc.o.l;
            if (n >= 0.0f && this.m instanceof vc && ((vc)this.m).o != null && ((vc)this.m).o.l >= 0.0f) {
                n = this.a(n, ((vc)this.m).o.l);
            }
        }
        return n;
    }
    
    public float i() {
        final vc vc = (vc)this.a();
        float n = -1.0f;
        if (vc.o != null) {
            n = vc.o.m;
            if (n >= 0.0f && this.m instanceof vc && ((vc)this.m).o != null && ((vc)this.m).o.m >= 0.0f) {
                n = this.a(n, ((vc)this.m).o.m);
            }
        }
        return n;
    }
    
    public float j() {
        final vc vc = (vc)this.a();
        float n = -1.0f;
        if (vc.o != null) {
            n = vc.o.n;
            if (n >= 0.0f && this.m instanceof vc && ((vc)this.m).o != null && ((vc)this.m).o.n >= 0.0f) {
                n = this.a(n, ((vc)this.m).o.n);
            }
        }
        return n;
    }
    
    public float k() {
        final vc vc = (vc)this.a();
        float n = -1.0f;
        if (vc.o != null) {
            n = vc.o.o;
            if (n >= 0.0f && this.m instanceof vc && ((vc)this.m).o != null && ((vc)this.m).o.o >= 0.0f) {
                n = this.a(n, ((vc)this.m).o.o);
            }
        }
        return n;
    }
    
    public kb l() {
        final vc vc = (vc)this.a();
        if (vc.o == null) {
            return null;
        }
        if (vc.o.i == null) {
            return null;
        }
        return vc.o.i.b();
    }
    
    public int m() {
        final vc vc = (vc)this.a();
        if (vc.p == null) {
            return -1;
        }
        return vc.p.e;
    }
    
    public int n() {
        final vc vc = (vc)this.a();
        if (vc.p == null) {
            return -1;
        }
        return vc.p.f;
    }
    
    public kb o() {
        final vc vc = (vc)this.a();
        if (vc.p == null) {
            return null;
        }
        if (vc.p.h == null) {
            return null;
        }
        return vc.p.h.b();
    }
    
    public kb p() {
        final vc vc = (vc)this.a();
        if (vc.p == null) {
            return null;
        }
        if (vc.p.g == null) {
            return null;
        }
        return vc.p.g.b();
    }
    
    protected void a() {
        super.a();
    }
    
    protected void k() {
        super.k();
    }
    
    public static v q() {
        return (v)v.o.a();
    }
    
    public void f() {
        v.o.a(this);
    }
    
    public void g() {
        super.g();
    }
    
    public void h() {
        super.h();
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "\bfT5e\u007fg^/\",|Z?m(.".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0097: {
                if (n2 > 1) {
                    break Label_0097;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '_';
                            break;
                        }
                        case 1: {
                            c2 = '\u0014';
                            break;
                        }
                        case 2: {
                            c2 = ';';
                            break;
                        }
                        case 3: {
                            c2 = '[';
                            break;
                        }
                        default: {
                            c2 = '\u0002';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
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
        final char[] charArray2 = "=}U<mqb".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0213: {
                if (n6 > 1) {
                    break Label_0213;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '_';
                            break;
                        }
                        case 1: {
                            c4 = '\u0014';
                            break;
                        }
                        case 2: {
                            c4 = ';';
                            break;
                        }
                        case 3: {
                            c4 = '[';
                            break;
                        }
                        default: {
                            c4 = '\u0002';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 <= n8) {
                z[n5] = new String(charArray2).intern();
                v.z = z;
                v.o = new f((v.p != null) ? v.p : (v.p = a(v.z[1])));
                return;
            }
            continue;
        }
    }
}
