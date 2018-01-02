// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics.renderer;

import java.io.InvalidObjectException;
import java.awt.Image;
import neat.r;
import neat.system.gb;
import neat.system.lb;
import neat.i;
import neat.system.graphics.q;
import neat.system.f;
import neat.system.ab;
import neat.system.kb;

public class p extends kb implements ab, d, o
{
    public static final neat.kb f;
    public static final neat.kb g;
    public static final neat.kb h;
    private static f i;
    public static final Object j;
    private q k;
    private i l;
    private i m;
    private i n;
    private a o;
    private a p;
    private boolean q;
    private h r;
    private k s;
    private boolean t;
    private int u;
    private int v;
    private transient k w;
    private static /* synthetic */ Class x;
    private static String[] z;
    
    public void a(final h r) {
        this.r = r;
        this.o.a(0, 0, r.f(), r.g());
        this.a();
    }
    
    public void a(final q k) {
        if (this.k != null) {
            this.k.f();
        }
        this.k = k;
    }
    
    public b a() {
        final b a = this.s.a();
        this.m.a(a);
        a.a(this);
        a.a(0, 0, this.d(), this.e());
        a.b(0, 0, this.d(), this.e());
        this.c(a);
        a.a(true);
        return a;
    }
    
    public void b(final b b) {
        if (this.f() == b) {
            throw new RuntimeException(neat.system.graphics.renderer.p.z[0] + neat.system.graphics.renderer.p.z[2]);
        }
        if (!this.m.b(b)) {
            throw new RuntimeException(neat.system.graphics.renderer.p.z[0] + neat.system.graphics.renderer.p.z[1]);
        }
        b.a(false);
        b.c();
        this.m.d(b);
        this.s.a(b);
    }
    
    public m b() {
        final m b = this.s.b();
        this.l.a(b);
        b.a(this);
        this.c(b);
        return b;
    }
    
    public void b(final m m) {
        m.a(false);
        if (m.j() != null) {
            m.i();
        }
        m.t();
        this.l.d(m);
        this.s.a(m);
    }
    
    public g a(final neat.kb kb, final int n, final boolean b) {
        return this.s.a(neat.system.graphics.renderer.e.a((gb)this.b(lb.i), kb, n, b));
    }
    
    public void a(final g g) {
        this.s.a(g);
    }
    
    public g a(final int n, final int n2) {
        return this.s.a(n, n2);
    }
    
    public g a(final int n, final int n2, final int n3) {
        return this.s.a(n, n2, n3);
    }
    
    public g a(final neat.kb kb, final int n) {
        return this.s.b(neat.system.graphics.renderer.e.a((gb)this.b(lb.i), kb, n));
    }
    
    public void b(final g g) {
        this.s.b(g);
    }
    
    public int d() {
        return this.s.f();
    }
    
    public int e() {
        return this.s.g();
    }
    
    public b f() {
        return (b)this.m.a(0);
    }
    
    public void n() {
        this.u = 0;
        this.v = 100000000;
    }
    
    public void a(final boolean b) {
        this.s.a(b);
    }
    
    public boolean o() {
        return this.s.h();
    }
    
    public void a(final b b) {
        if (b.c(1)) {
            this.c(b);
        }
        this.p.c(b.B());
        this.p.a(this.r.h());
    }
    
    public void a(final m m) {
        if (m.d(1)) {
            this.c(m);
        }
        if (!m.k() && m.i != 2) {
            return;
        }
        final a h = neat.system.graphics.renderer.a.h();
        h.b(m.u());
        if (m.x()) {
            this.p.c(m.y());
            h.c(m.y());
        }
        this.p.c(m.u());
        if (!this.p.d(this.r.h())) {
            this.p.a();
            h.f();
            return;
        }
        this.p.a(this.r.h());
        final r f = this.m.f();
        while (f.a()) {
            final b b = (b)f.b();
            if (m.c(b)) {
                b.b(h);
            }
        }
        h.f();
        f.f();
    }
    
    private void c(final m m) {
        if (this.l.i() > 1) {
            int c = this.l.c(m);
            if (c < 0) {
                throw new RuntimeException(neat.system.graphics.renderer.p.z[3]);
            }
            boolean b = false;
            Label_0139: {
                if (c > 0) {
                    m i = (m)this.l.a(c - 1);
                    if (i.g() < m.g()) {
                        while (true) {
                            Label_0124: {
                                break Label_0124;
                                do {
                                    i = (m)this.l.a(c - 1);
                                    if (i.g() >= m.g()) {
                                        this.l.b(c, m);
                                        b = true;
                                        break Label_0139;
                                    }
                                    this.l.b(c, i);
                                } while (--c > 0);
                            }
                            continue;
                        }
                    }
                }
            }
            if (!b && c < this.l.i() - 1) {
                m j = (m)this.l.a(c + 1);
                if (j.g() > m.g()) {
                    while (true) {
                        Label_0246: {
                            break Label_0246;
                            do {
                                j = (m)this.l.a(c + 1);
                                if (j.g() <= m.g()) {
                                    this.l.b(c, m);
                                    return;
                                }
                                this.l.b(c, j);
                            } while (++c < this.l.i() - 1);
                        }
                        continue;
                    }
                }
            }
        }
    }
    
    private void c(final b b) {
        if (this.m.i() > 1) {
            int c = this.m.c(b);
            if (c < 0) {
                throw new RuntimeException(neat.system.graphics.renderer.p.z[4]);
            }
            boolean b2 = false;
            Label_0139: {
                if (c > 0) {
                    b b3 = (b)this.m.a(c - 1);
                    if (b3.e() < b.e()) {
                        while (true) {
                            Label_0124: {
                                break Label_0124;
                                do {
                                    b3 = (b)this.m.a(c - 1);
                                    if (b3.e() >= b.e()) {
                                        this.m.b(c, b);
                                        b2 = true;
                                        break Label_0139;
                                    }
                                    this.m.b(c, b3);
                                } while (--c > 0);
                            }
                            continue;
                        }
                    }
                }
            }
            if (!b2 && c < this.m.i() - 1) {
                b b4 = (b)this.m.a(c + 1);
                if (b4.e() > b.e()) {
                    while (true) {
                        Label_0246: {
                            break Label_0246;
                            do {
                                b4 = (b)this.m.a(c + 1);
                                if (b4.e() <= b.e()) {
                                    this.m.b(c, b);
                                    return;
                                }
                                this.m.b(c, b4);
                            } while (++c < this.m.i() - 1);
                        }
                        continue;
                    }
                }
            }
        }
    }
    
    public void p() {
        synchronized (this) {
            if (this.t) {
                try {
                    this.wait();
                }
                catch (InterruptedException ex) {}
            }
        }
    }
    
    public void q() {
        if (this.f().q() == null) {
            return;
        }
        synchronized (this) {
            this.t = true;
        }
        if (this.s.c()) {
            this.r();
        }
        else {
            this.s();
        }
        this.s.d();
        this.t();
        this.s.e();
        this.u();
        this.q = false;
        synchronized (this) {
            this.t = false;
            this.notify();
        }
    }
    
    public void r() {
        this.q = true;
        final r f = this.l.f();
        while (f.a()) {
            final m m = (m)f.b();
            if (!m.z() && m.k()) {
                m.c(3);
            }
        }
        f.f();
        final r f2 = this.m.f();
        while (f2.a()) {
            final b b = (b)f2.b();
            if (!b.j()) {
                b.y();
                b.b(3);
            }
        }
        f2.f();
    }
    
    private void s() {
        while (true) {
            boolean b = false;
            final r f = this.m.f();
            while (f.a()) {
                final b b2 = (b)f.b();
                if (b2.x()) {
                    final a b3 = b2.B();
                    final a h = neat.system.graphics.renderer.a.h();
                    final r f2 = this.m.f();
                    while (f2.a()) {
                        final b b4 = (b)f2.b();
                        if (b2 != b4 && b2.e() >= b4.e() && b4.a(b3)) {
                            if (b4.x()) {
                                h.b(b4.B());
                                b4.b(b3);
                                if (h.equals(b4.B())) {
                                    continue;
                                }
                                b = true;
                            }
                            else {
                                b4.b(b3);
                                b = true;
                            }
                        }
                    }
                    f2.f();
                    h.f();
                    final r f3 = this.l.f();
                    while (f3.a()) {
                        final m m = (m)f3.b();
                        if (!m.z() && m.c(b2) && m.a(b3)) {
                            m.c(5);
                            b = true;
                        }
                    }
                    f3.f();
                }
            }
            f.f();
            if (!b) {
                break;
            }
        }
    }
    
    private void t() {
        if (!this.p.b()) {
            return;
        }
        final a h = neat.system.graphics.renderer.a.h();
        final r f = this.m.f();
        while (f.a()) {
            final b b = (b)f.b();
            if (!b.d()) {
                continue;
            }
            b.a(this.r, this.p);
            final r f2 = this.l.f();
            while (f2.a()) {
                final m m = (m)f2.b();
                if (!m.z()) {
                    continue;
                }
                final int g = m.g();
                if (g < this.u || g > this.v) {
                    continue;
                }
                if (!m.e(b)) {
                    continue;
                }
                b.a(m);
            }
            f2.f();
            b.b(this.r, this.p);
        }
        f.f();
        h.f();
    }
    
    private void u() {
        final r f = this.m.f();
        while (f.a()) {
            final b b = (b)f.b();
            if (b.j()) {
                b.i();
            }
            if (b.x()) {
                b.z();
            }
        }
        f.f();
        final r f2 = this.l.f();
        while (f2.a()) {
            final m m = (m)f2.b();
            if (m.z()) {
                m.A();
            }
        }
        f2.f();
        this.p.a();
    }
    
    public l v() {
        return this.s.i();
    }
    
    public void a(final l l) {
        this.s.a(l);
    }
    
    public j a(final Image image, final int n, final int n2, final int n3) {
        return this.s.a(image, n, n2, n3);
    }
    
    public j a(final byte[] array, final int n, final int n2, final int n3, final int n4) {
        return this.s.a(array, n, n2, n3, n4);
    }
    
    public j a(final neat.kb kb, final byte[] array, final int n, final int n2) {
        return this.s.a(kb, array, n, n2);
    }
    
    public j a(final Object o) {
        return this.s.a(o);
    }
    
    public void a(final neat.kb kb) {
        this.s.a(kb);
    }
    
    public void b(final neat.kb kb) {
        this.s.b(kb);
    }
    
    public boolean w() {
        return this.s.j();
    }
    
    public void x() {
        this.s.k();
    }
    
    public void validateObject() throws InvalidObjectException {
    }
    
    public static p a(final kb kb, final k s) {
        final p p2 = (p)p.i.a();
        p2.s = s;
        p2.a(kb, p.j);
        return p2;
    }
    
    public void f() {
        neat.system.graphics.renderer.p.i.a(this);
    }
    
    public void g() {
        this.r = null;
        this.l = neat.i.k();
        this.m = neat.i.k();
        this.n = neat.i.k();
        this.o = neat.system.graphics.renderer.a.h();
        this.p = neat.system.graphics.renderer.a.h();
        this.n();
        super.g();
    }
    
    public void h() {
        if (this.k != null) {
            this.k.f();
            this.k = null;
        }
        if (this.m != null) {
            this.m.c();
            this.m.f();
            this.m = null;
        }
        if (this.l != null) {
            this.l.c();
            this.l.f();
            this.l = null;
        }
        if (this.r != null) {
            this.r.f();
            this.r = null;
        }
        if (this.o != null) {
            this.o.f();
            this.o = null;
        }
        if (this.p != null) {
            this.p.f();
            this.p = null;
        }
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
    
    public p() {
        this.k = null;
        this.w = null;
    }
    
    static {
        p.z = new String[] { z(z("F\u0016\u00171jf\u0016\u000b\u0007`{\u0007W1jx\u0016\r0Lu\u001d\u000f4|<Z")), z(z("4\u0012\r!jy\u0003\ru{{S\u001d0cq\u0007\u001cun4\u0010\u0018;yu\u0000Y1`q\u0000\u0017r{4\u0011\u001c9`z\u0014Y!`4\u0007\u00110/f\u0016\u00171jf\u0016\u000b")), z(z("4\u0011\u00186ds\u0001\u0016 apS\u001a4ab\u0012\nulu\u001d\u0017:{4\u0011\u001cukq\u001f\u001c!jp")), z(z("a\u0003\u001d4{q\u0017Y&\u007ff\u001a\r0/}\u0000Y:z`S\u00163/x\u001c\u001a4c4\u0000\t'f`\u0016\n")), z(z("a\u0003\u001d4{q\u0017Y6nz\u0005\u0018&/}\u0000Y:z`S\u00163/x\u001c\u001a4c4\u0010\u0018;yu\u0000\u001c&")), z(z("z\u0016\u0018!!g\n\n!jy]\u001e'nd\u001b\u00106|:\u0001\u001c;kq\u0001\u001c'!d")) };
        f = neat.kb.a(z(z(":\u0019\t2")));
        g = neat.kb.a(z(z(":\u0001\u0018\"")));
        h = neat.kb.a(z(z(":\u0001\u0018m")));
        p.i = new f((p.x != null) ? p.x : (p.x = a(p.z[5])));
        j = ((p.x != null) ? p.x : (p.x = a(p.z[5])));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        int i;
        do {
            i = charArray.length;
            if (i < 2) {
                continue;
            }
            return charArray;
        } while (i == 0);
        final int n = 0;
        charArray[n] ^= '\u000f';
        return charArray;
    }
    
    private static String z(final char[] array) {
        int length;
        int n2;
        final int n = n2 = (length = array.length);
        int n3 = 0;
        while (true) {
            Label_0086: {
                if (n > 1) {
                    break Label_0086;
                }
                length = (n2 = n3);
                do {
                    final char c = array[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = '\u0014';
                            break;
                        }
                        case 1: {
                            c2 = 's';
                            break;
                        }
                        case 2: {
                            c2 = 'y';
                            break;
                        }
                        case 3: {
                            c2 = 'U';
                            break;
                        }
                        default: {
                            c2 = '\u000f';
                            break;
                        }
                    }
                    array[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                return new String(array).intern();
            }
            continue;
        }
    }
}
