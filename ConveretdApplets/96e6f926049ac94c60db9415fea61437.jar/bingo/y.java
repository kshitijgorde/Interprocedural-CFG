// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.system.l;
import neat.system.m;
import neat.system.n;
import a.b;
import a.gb;
import neat.cb;
import neat.bb;
import neat.kb;
import neat.system.f;
import a.o;
import a.r;
import a.s;

public class y extends p implements s, r, o, a.p
{
    private static f l;
    private int m;
    private kb n;
    private kb o;
    private int p;
    private kb q;
    private int r;
    private int s;
    private static /* synthetic */ Class t;
    private static String[] z;
    
    private void a() {
        final yc yc = (yc)this.a();
        if (yc.k != null) {
            final neat.bb a = yc.k.a();
            if (!(a instanceof dc)) {
                throw new RuntimeException(y.z[6] + a);
            }
            this.a().a((dc)a);
        }
    }
    
    private kb c() {
        final yc yc = (yc)this.a();
        int n = 0;
        final neat.r b = yc.i.b();
        while (b.a()) {
            final kb kb = (kb)b.b();
            if (n != 0) {
                b.f();
                return kb;
            }
            if (!kb.equals(this.n)) {
                continue;
            }
            n = 1;
        }
        b.f();
        return null;
    }
    
    private uc a(final kb kb) {
        final yc yc = (yc)this.a();
        if (yc.i == null) {
            return null;
        }
        final neat.cb cb = (neat.cb)yc.i.c(kb);
        if (cb == null) {
            return null;
        }
        final neat.bb a = cb.a();
        if (!(a instanceof uc)) {
            throw new RuntimeException(y.z[1] + cb);
        }
        return (uc)a;
    }
    
    private int b(final kb kb) {
        int n = 0;
        final neat.r b = ((yc)this.a()).i.b();
        while (b.a()) {
            final kb kb2 = (kb)b.b();
            if (kb2.equals(kb)) {
                break;
            }
            final uc a = this.a(kb2);
            if (a == null) {
                continue;
            }
            n += a.i;
            a.f();
        }
        b.f();
        return n;
    }
    
    private kb a(final int n) {
        final neat.r b = ((yc)this.a()).i.b();
        if (!b.a()) {
            return null;
        }
        int n2 = 0;
        while (true) {
            final kb kb = (kb)b.b();
            final uc a = this.a(kb);
            if (a != null) {
                final int n3 = n2 + a.i;
                a.f();
                if (n3 > n) {
                    b.f();
                    return kb;
                }
                n2 = n3;
            }
            if (!b.a()) {
                b.f();
                return null;
            }
        }
    }
    
    public void k() {
        final yc yc = (yc)this.a();
        if (yc.j == null) {
            throw new RuntimeException(y.z[4] + yc);
        }
        this.c(yc.j);
    }
    
    public void c(final kb kb) {
        this.a(kb, 0);
    }
    
    public boolean b(int n) {
        final kb a = this.a(n);
        if (a == null) {
            return false;
        }
        n -= this.b(a);
        this.a(a, n);
        return true;
    }
    
    public void a(final kb kb, final int p2) {
        this.l();
        this.a();
        final uc a = this.a(kb);
        if (a == null) {
            throw new RuntimeException(y.z[0] + kb);
        }
        final u u = (u)this.i.a(a);
        if (u == null) {
            throw new RuntimeException(y.z[0] + a);
        }
        this.n = kb.b();
        this.o = u.b().b();
        this.m = this.b(kb);
        this.m += p2;
        this.p = p2;
        final kb c = this.c();
        if (c != null) {
            u.a(this.a(c), p2);
        }
        u.a();
    }
    
    public void l() {
        final u m = this.m();
        if (m != null) {
            this.i.d(m);
        }
        if (this.n != null) {
            this.n.f();
            this.n = null;
        }
        if (this.o != null) {
            this.o.f();
            this.o = null;
        }
        this.m = 0;
    }
    
    public u m() {
        if (this.o == null) {
            return null;
        }
        return (u)this.i.b(this.o);
    }
    
    public int n() {
        return this.m;
    }
    
    public ob c(int n) {
        final kb a = this.a(n);
        if (a == null) {
            return null;
        }
        n -= this.b(a);
        final uc a2 = this.a(a);
        if (a2 == null) {
            return null;
        }
        if (!(a2 instanceof vc)) {
            throw new RuntimeException(y.z[3] + a);
        }
        ob ob = null;
        final neat.cb a3 = v.a((vc)a2, n);
        if (a3 != null) {
            final neat.bb a4 = a3.a();
            if (!(a4 instanceof ob)) {
                throw new RuntimeException(y.z[2] + a3);
            }
            final bingo.bb a5 = this.a();
            if (a5 != null) {
                a5.d(a3.e);
            }
            ob = (ob)a4;
        }
        a2.f();
        return ob;
    }
    
    public pc o() {
        return ((yc)this.a()).l;
    }
    
    private void p() {
    }
    
    private void a(final int n, final int n2, final boolean b) {
    }
    
    private void a(final int r, final int s) {
        if (r == this.r && s == this.s) {
            return;
        }
        this.r = r;
        this.s = s;
        this.q();
    }
    
    private void q() {
    }
    
    protected void a(final gb gb) {
        super.a(gb);
        if (!(gb instanceof yc)) {
            throw new RuntimeException(y.z[5] + gb);
        }
        final yc yc = (yc)gb;
        this.a();
    }
    
    public void d() {
    }
    
    public void a(final int n) {
        this.q();
    }
    
    public void b() {
    }
    
    public void c() {
        this.q();
    }
    
    public boolean a(final n n) {
        this.a(n.d, n.e);
        if (n.f) {
            this.a(n.d, n.e, false);
        }
        else if (n.h) {
            this.a(n.d, n.e, true);
        }
        return false;
    }
    
    public boolean a(final neat.system.o o) {
        this.a(o.d, o.e);
        return false;
    }
    
    public boolean a(final m m) {
        this.a(m.d, m.e);
        return false;
    }
    
    public boolean a(final l l) {
        this.a(l.d, l.e);
        return false;
    }
    
    public static y r() {
        return (y)y.l.a();
    }
    
    public void f() {
        y.l.a(this);
    }
    
    public void g() {
        super.g();
        this.m = 0;
        this.p = 0;
    }
    
    public void h() {
        this.l();
        this.p();
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
    
    public y() {
        this.n = null;
        this.o = null;
        this.q = null;
    }
    
    static {
        final String[] z = new String[8];
        final int n = 0;
        final char[] charArray = "W\u0002\fP\u00104\u000f\r\u0016\u00004\u0017\n\u001e\u00174\u000f\u0007\u0001\u0001xY".toCharArray();
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
                            c2 = '\u0014';
                            break;
                        }
                        case 1: {
                            c2 = 'c';
                            break;
                        }
                        case 2: {
                            c2 = 'b';
                            break;
                        }
                        case 3: {
                            c2 = 'w';
                            break;
                        }
                        default: {
                            c2 = 'd';
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
        final char[] charArray2 = "@\u000b\u000b\u0004Dx\u0006\u0014\u0012\b4\u0010\n\u0016\u0000{\u0014B\u001e\u00174\r\r\u0003DuC.\u0012\u0012q\u000f1\u001f\u0005p\f\u0015W\ry\u0013\u000e\u0012\tq\r\u0016\u0016\u0010}\f\fM".toCharArray();
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
                            c4 = '\u0014';
                            break;
                        }
                        case 1: {
                            c4 = 'c';
                            break;
                        }
                        case 2: {
                            c4 = 'b';
                            break;
                        }
                        case 3: {
                            c4 = 'w';
                            break;
                        }
                        default: {
                            c4 = 'd';
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
        final char[] charArray3 = "C\u0011\r\u0019\u00034\u0010\u0007\u0003Dg\u000b\u0003\u0013\u000bcY".toCharArray();
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
                            c6 = '\u0014';
                            break;
                        }
                        case 1: {
                            c6 = 'c';
                            break;
                        }
                        case 2: {
                            c6 = 'b';
                            break;
                        }
                        case 3: {
                            c6 = 'w';
                            break;
                        }
                        default: {
                            c6 = 'd';
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
        final char[] charArray4 = "C\u0011\r\u0019\u00034\u000f\u0007\u0001\u0001xC\u0011\u001f\u0005p\f\u0015W\u0006mC\f\u0016\tqY".toCharArray();
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
                            c8 = '\u0014';
                            break;
                        }
                        case 1: {
                            c8 = 'c';
                            break;
                        }
                        case 2: {
                            c8 = 'b';
                            break;
                        }
                        case 3: {
                            c8 = 'w';
                            break;
                        }
                        default: {
                            c8 = 'd';
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
        final char[] charArray5 = "@\u000b\u0007\u0005\u00014\n\u0011W\n{C\u0004\u001e\u0016g\u0017B\u001b\u0001b\u0006\u000eW\u0000q\u0005\u000b\u0019\u0001pC\u000b\u0019Dg\u0006\u0010\u001e\u0001gC\u0011\u001f\u0005p\f\u0015M".toCharArray();
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
                            c10 = '\u0014';
                            break;
                        }
                        case 1: {
                            c10 = 'c';
                            break;
                        }
                        case 2: {
                            c10 = 'b';
                            break;
                        }
                        case 3: {
                            c10 = 'w';
                            break;
                        }
                        default: {
                            c10 = 'd';
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
        final char[] charArray6 = "@\u000b\u000b\u0004Dg\u000b\u0003\u0013\u000bcC\r\u0011D}\u0017\u0007\u001aDy\u0016\u0011\u0003Dv\u0006B\u0016DG\u0006\u0010\u001e\u0001g0\n\u0016\u0000{\u0014B\u001e\td\u000f\u0007\u001a\u0001z\u0017\u0003\u0003\r{\rX".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0678: {
                if (n22 > 1) {
                    break Label_0678;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = '\u0014';
                            break;
                        }
                        case 1: {
                            c12 = 'c';
                            break;
                        }
                        case 2: {
                            c12 = 'b';
                            break;
                        }
                        case 3: {
                            c12 = 'w';
                            break;
                        }
                        default: {
                            c12 = 'd';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        z[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "S\u0002\u000f\u0012Dy\f\u0006\u0012Dg\u000b\u0003\u0013\u000bcC\u000f\u0002\u0017`C\u0000\u0012DuC%\u0016\tq.\r\u0013\u0001G\u000b\u0003\u0013\u000bcC\u000b\u001a\u0014x\u0006\u000f\u0012\n`\u0002\u0016\u001e\u000bzY".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0798: {
                if (n26 > 1) {
                    break Label_0798;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = '\u0014';
                            break;
                        }
                        case 1: {
                            c14 = 'c';
                            break;
                        }
                        case 2: {
                            c14 = 'b';
                            break;
                        }
                        case 3: {
                            c14 = 'w';
                            break;
                        }
                        default: {
                            c14 = 'd';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        z[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "v\n\f\u0010\u000b:\u001a".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0918: {
                if (n30 > 1) {
                    break Label_0918;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = '\u0014';
                            break;
                        }
                        case 1: {
                            c16 = 'c';
                            break;
                        }
                        case 2: {
                            c16 = 'b';
                            break;
                        }
                        case 3: {
                            c16 = 'w';
                            break;
                        }
                        default: {
                            c16 = 'd';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 <= n32) {
                z[n29] = new String(charArray8).intern();
                y.z = z;
                y.l = new f((y.t != null) ? y.t : (y.t = a(y.z[7])));
                return;
            }
            continue;
        }
    }
}
