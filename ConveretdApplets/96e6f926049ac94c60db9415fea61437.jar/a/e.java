// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.r;
import neat.bb;
import neat.gb;
import neat.system.lb;
import neat.nb;
import neat.i;
import neat.kb;
import neat.system.f;

public class e extends c implements s
{
    private static f k;
    private rb l;
    private mb m;
    private boolean n;
    private kb o;
    private i p;
    private sb q;
    private static /* synthetic */ Class r;
    private static String[] z;
    
    public rb a() {
        return this.l;
    }
    
    public void b() {
        if (this.l != null) {
            this.l.f();
            this.l = null;
        }
        final kb a = kb.a(e.z[0]);
        final kb a2 = nb.a(a, this.i.m());
        a.f();
        if (a2.d() > 0) {
            ((lb)this.i.a(lb.i)).a(a2, true);
        }
        a2.f();
        final jb jb = (jb)this.a();
        if (jb.i != null) {
            this.l = (rb)jb.i.a();
        }
        if (this.l == null && jb.j != null) {
            this.l = (rb)jb.j.a();
        }
        if (this.l == null) {
            throw new RuntimeException(e.z[1]);
        }
        this.c();
    }
    
    private void c() {
        if (this.l != null) {
            if (this.l.p == null) {
                final kb n = this.i.n();
                if (n != null) {
                    this.l.p = n.b();
                }
            }
            if (this.l.q == null) {
                final kb o = this.i.o();
                if (o != null) {
                    this.l.q = o.b();
                }
            }
            if (x.P) {
                final eb eb = (eb)this.i.a(a.eb.i);
                kb o2 = null;
                if (x.R != null) {
                    o2 = eb.o();
                }
                if (o2 != null) {
                    if (this.l.z != null) {
                        this.l.z.f();
                    }
                    this.l.z = o2.b();
                }
                kb p = null;
                if (x.R != null) {
                    p = eb.p();
                }
                if (p != null) {
                    if (this.l.C != null) {
                        this.l.C.f();
                    }
                    this.l.C = p.b();
                }
            }
        }
    }
    
    boolean a(final zb zb) {
        if (this.m != null) {
            this.m.f();
        }
        final neat.bb a = zb.a();
        if (!(a instanceof mb)) {
            throw new RuntimeException(e.z[3] + zb);
        }
        this.m = (mb)a;
        this.n = zb.p;
        if (zb.q != null || zb.r != null) {
            if (this.p != null) {
                this.p.j();
            }
            else {
                this.p = neat.i.k();
            }
            if (zb.q != null) {
                final lb lb = (lb)this.i.a(neat.system.lb.i);
                final gb d = gb.d();
                final i a2 = d.a(lb, this.i.m(), zb.q);
                d.f();
                final r f = a2.f();
                while (f.a()) {
                    this.p.a(f.b());
                }
                f.f();
            }
            if (zb.r != null) {
                final lb lb2 = (lb)this.i.a(lb.i);
                final gb d2 = gb.d();
                final i a3 = d2.a(lb2, this.i.m(), zb.r);
                d2.f();
                final r f2 = a3.f();
                while (f2.a()) {
                    this.p.a(f2.b());
                }
                f2.f();
            }
        }
        if (zb.s != null) {
            final neat.bb a4 = zb.s.a();
            if (!(a4 instanceof sb)) {
                throw new RuntimeException(e.z[3] + a4);
            }
            this.q = (sb)a4;
        }
        return true;
    }
    
    private void k() {
        if (this.m != null) {
            this.i.a(this);
            if (this.n) {
                this.i.p();
            }
            final b a = this.i.a(this.m);
            this.m = null;
            if (!(a instanceof h)) {
                throw new RuntimeException(e.z[2] + a);
            }
            final h h = (h)a;
            if (this.q != null) {
                h.a(this.q);
                this.q.f();
                this.q = null;
            }
        }
        if (this.p != null) {
            if (this.p.e()) {
                this.p.f();
                this.p = null;
            }
            else {
                this.i.a((neat.bb)this.p.g(), false);
            }
        }
    }
    
    protected void a(final a.gb gb) {
        super.a(gb);
        if (!(gb instanceof jb)) {
            throw new RuntimeException(e.z[4] + gb);
        }
        final jb jb = (jb)gb;
    }
    
    protected void n() {
        super.n();
        this.b();
    }
    
    public void d() {
    }
    
    public void a(final int n) {
        this.k();
    }
    
    public static e l() {
        return (e)e.k.a();
    }
    
    public void f() {
        e.k.a(this);
    }
    
    public void g() {
        super.g();
    }
    
    public void h() {
        super.h();
        if (this.l != null) {
            this.l.f();
            this.l = null;
        }
        if (this.o != null) {
            this.o.f();
            this.o = null;
        }
        if (this.p != null) {
            this.p.j();
            this.p.f();
            this.p = null;
        }
        if (this.m != null) {
            this.m.f();
            this.m = null;
        }
        if (this.q != null) {
            this.q.f();
            this.q = null;
        }
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public e() {
        this.l = null;
        this.m = null;
        this.o = null;
        this.p = null;
        this.q = null;
    }
    
    static {
        final String[] z = new String[6];
        final int n = 0;
        final char[] charArray = "\u0011}|*u\u0003`~-~\fgu;|".toCharArray();
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
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = 'J';
                            break;
                        }
                        case 1: {
                            c2 = '.';
                            break;
                        }
                        case 2: {
                            c2 = '9';
                            break;
                        }
                        case 3: {
                            c2 = '~';
                            break;
                        }
                        default: {
                            c2 = '!';
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
        final char[] charArray2 = "\u0019KM\nH$IJ^L?]M^C/\u000e]\u001bG#@\\\u001a\u0001k".toCharArray();
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
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = 'J';
                            break;
                        }
                        case 1: {
                            c4 = '.';
                            break;
                        }
                        case 2: {
                            c4 = '9';
                            break;
                        }
                        case 3: {
                            c4 = '~';
                            break;
                        }
                        default: {
                            c4 = '!';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
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
        final char[] charArray3 = "\u001eFP\r\u0001#]\u0019\u0010N>\u000eX^u+]R7U/C\u0003".toCharArray();
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
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = 'J';
                            break;
                        }
                        case 1: {
                            c6 = '.';
                            break;
                        }
                        case 2: {
                            c6 = '9';
                            break;
                        }
                        case 3: {
                            c6 = '~';
                            break;
                        }
                        default: {
                            c6 = '!';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
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
        final char[] charArray4 = "\u0019FX\u001aN=\u000e_\u0011SjZX\rJjCL\rUjL\\^u+]R7U/Cj\u0016@.AND".toCharArray();
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
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = 'J';
                            break;
                        }
                        case 1: {
                            c8 = '.';
                            break;
                        }
                        case 2: {
                            c8 = '9';
                            break;
                        }
                        case 3: {
                            c8 = '~';
                            break;
                        }
                        default: {
                            c8 = '!';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
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
        final char[] charArray5 = "\u001eFP\r\u00019FX\u001aN=\u000eV\u0018\u0001#Z\\\u0013\u0001'[J\n\u0001(K\u0019\u001f\u0001\tAW\nS%BU\u001bS\u0003Z\\\u0013r\"O]\u0011VjGT\u000eM/C\\\u0010U+ZP\u0011Op".toCharArray();
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
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = 'J';
                            break;
                        }
                        case 1: {
                            c10 = '.';
                            break;
                        }
                        case 2: {
                            c10 = '9';
                            break;
                        }
                        case 3: {
                            c10 = '~';
                            break;
                        }
                        default: {
                            c10 = '!';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
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
        final char[] charArray6 = "+\u0000\\".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0682: {
                if (n22 > 1) {
                    break Label_0682;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = 'J';
                            break;
                        }
                        case 1: {
                            c12 = '.';
                            break;
                        }
                        case 2: {
                            c12 = '9';
                            break;
                        }
                        case 3: {
                            c12 = '~';
                            break;
                        }
                        default: {
                            c12 = '!';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 <= n24) {
                z[n21] = new String(charArray6).intern();
                e.z = z;
                e.k = new f((e.r != null) ? e.r : (e.r = a(e.z[5])));
                return;
            }
            continue;
        }
    }
}
