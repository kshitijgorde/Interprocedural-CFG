// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.bb;
import neat.cb;
import neat.r;
import neat.system.j;
import neat.system.i;
import neat.system.l;
import neat.system.o;
import neat.system.n;
import neat.system.nb;
import neat.v;
import neat.system.f;

public class y extends x implements m, k
{
    private static f hb;
    private ob ib;
    private e jb;
    private h kb;
    private z lb;
    private neat.h mb;
    private v nb;
    private static /* synthetic */ Class ob;
    private static String[] pb;
    
    public void a(final ob ib) {
        this.a();
        this.ib = ib;
        this.c().a(ib.e);
        ((nb)this.a(neat.system.nb.g)).a(ib.f);
        this.a(ib.g);
    }
    
    public void a() {
        this.d();
        this.l();
        if (this.ib != null) {
            this.ib.f();
            this.ib = null;
        }
    }
    
    public void b() {
        this.d();
        if (this.ib.h != null) {
            this.a(this.ib.h);
        }
    }
    
    public void a(final int n) {
        this.c().b(n);
    }
    
    public void c() {
        this.c().i();
    }
    
    public void a(final n n) {
        this.c().a(n);
    }
    
    public void a(final o o) {
        this.c().a(o);
    }
    
    public void a(final neat.system.m m) {
        this.c().a(m);
    }
    
    public void a(final l l) {
        this.c().a(l);
    }
    
    public void a(final i i) {
        this.c().a(i);
    }
    
    public void a(final j j) {
        this.c().a(j);
    }
    
    public void b(final int n) {
        final r b = this.c().b();
        while (b.a()) {
            final b b2 = (b)b.b();
            if (b2 instanceof c && ((c)b2).a() >= n) {
                this.d(b2);
            }
        }
        b.f();
    }
    
    public void c(final int n) {
        this.b(n + 1);
    }
    
    public void a(final c c) {
        this.c(c.a());
    }
    
    public e d() {
        return this.jb;
    }
    
    public h e() {
        return this.kb;
    }
    
    private void k() {
        if (this.kb != null) {
            this.d(this.kb);
            this.kb = null;
        }
        this.jb = null;
    }
    
    public void a(final b b) {
        if (b instanceof e) {
            this.jb = (e)b;
        }
        else if (b instanceof h) {
            this.kb = (h)b;
        }
    }
    
    public void b(final b b) {
        if (b == this.jb) {
            this.jb = null;
        }
        else if (b == this.kb) {
            this.kb = null;
        }
    }
    
    private void a(final neat.cb cb) {
        this.l();
        this.lb = this.b(cb);
    }
    
    private void l() {
        if (this.lb != null) {
            this.a(this.lb);
            this.lb = null;
        }
    }
    
    public z b(final neat.cb cb) {
        if (cb == null) {
            return null;
        }
        final neat.bb a = cb.a();
        if (!(a instanceof xb)) {
            throw new RuntimeException(y.pb[0] + cb);
        }
        final xb xb = (xb)a;
        final z l = a.z.l();
        l.a(this, xb);
        return l;
    }
    
    public void a(final z z) {
        if (z != null) {
            z.a(this);
            z.f();
        }
    }
    
    void a(final t t) {
        t.c();
        final neat.i i = (neat.i)this.mb.g(t.a());
        if (i == null) {
            throw new RuntimeException(y.pb[1]);
        }
        if (!i.d(t)) {
            throw new RuntimeException(y.pb[1]);
        }
    }
    
    public void b(final t t) {
        t.f();
    }
    
    public void a(final w w) {
        final neat.i i = (neat.i)this.mb.g(w);
        if (i == null) {
            return;
        }
        final r f = i.f();
        while (f.a()) {
            this.b((t)f.b());
        }
        f.f();
        this.mb.a(w);
        i.f();
    }
    
    public void m() {
        final r a = this.mb.a();
        while (a.a()) {
            this.a((w)a.b());
        }
        a.f();
    }
    
    public float n() {
        return this.nb.c();
    }
    
    public int d(final int n) {
        return this.nb.a(n);
    }
    
    public void c(final b b) {
        if (b instanceof c) {
            ((c)b).a(this);
        }
    }
    
    public static y o() {
        return (y)y.hb.a();
    }
    
    public void f() {
        y.hb.a(this);
    }
    
    public void g() {
        this.nb = neat.v.e();
        super.g();
        this.c().a(this);
        this.mb = neat.h.e();
    }
    
    public void h() {
        this.k();
        this.m();
        this.a();
        this.c().b(this);
        super.h();
        this.mb.f();
        this.mb = null;
        this.nb.f();
        this.nb = null;
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
        this.ib = null;
        this.jb = null;
        this.kb = null;
        this.lb = null;
        this.mb = null;
        this.nb = null;
    }
    
    static {
        final String[] pb = new String[3];
        final int n = 0;
        final char[] charArray = "rl|-/V$q .J${&2\u0001`x/!Thii&Njii-Twii,Hjvi4N$|i\u0006Nji\u001a(@`r>z".toCharArray();
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
                            c2 = '!';
                            break;
                        }
                        case 1: {
                            c2 = '\u0004';
                            break;
                        }
                        case 2: {
                            c2 = '\u001d';
                            break;
                        }
                        case 3: {
                            c2 = 'I';
                            break;
                        }
                        default: {
                            c2 = '@';
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
        pb[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "ult:`Db{,#U$t:`Okii!E`x-`Uk=,&Ga~=`Lev,2\u0001%".toCharArray();
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
                            c4 = '!';
                            break;
                        }
                        case 1: {
                            c4 = '\u0004';
                            break;
                        }
                        case 2: {
                            c4 = '\u001d';
                            break;
                        }
                        case 3: {
                            c4 = 'I';
                            break;
                        }
                        default: {
                            c4 = '@';
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
        pb[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "@*d".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0329: {
                if (n10 > 1) {
                    break Label_0329;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '!';
                            break;
                        }
                        case 1: {
                            c6 = '\u0004';
                            break;
                        }
                        case 2: {
                            c6 = '\u001d';
                            break;
                        }
                        case 3: {
                            c6 = 'I';
                            break;
                        }
                        default: {
                            c6 = '@';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 <= n12) {
                pb[n9] = new String(charArray3).intern();
                y.pb = pb;
                y.hb = new f((y.ob != null) ? y.ob : (y.ob = a(y.pb[2])));
                return;
            }
            continue;
        }
    }
}
