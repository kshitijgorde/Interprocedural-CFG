// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.system.j;
import neat.system.i;
import neat.system.m;
import neat.system.l;
import neat.system.o;
import neat.system.n;
import neat.system.s;
import neat.system.f;
import neat.system.t;
import neat.system.mb;
import neat.bb;
import neat.system.graphics.renderer.p;
import neat.system.lb;
import neat.system.nb;
import neat.system.kb;

public abstract class eb extends kb implements neat.system.eb
{
    public static int f;
    public static int g;
    public static int h;
    public static final Object i;
    private a.y j;
    private pb k;
    private neat.kb l;
    private boolean m;
    private long n;
    private neat.kb o;
    private neat.kb p;
    private neat.kb q;
    private neat.kb r;
    private int s;
    private int t;
    private long u;
    private long v;
    private long w;
    private fb x;
    private static /* synthetic */ Class y;
    private static String[] z;
    
    public void a(final pb k) {
        this.k = k;
    }
    
    private void c() {
        if (this.k != null) {
            this.k.f();
            this.k = null;
        }
    }
    
    public void a(final neat.kb kb) {
        if (this.l != null) {
            this.l.f();
            this.l = null;
        }
        if (kb != null) {
            this.l = kb.b();
        }
    }
    
    public void a(final boolean m, final long n) {
        this.m = m;
        this.n = n;
        this.n();
    }
    
    private void n() {
        final nb nb = (nb)this.b(neat.system.nb.g);
        if (nb != null) {
            if (this.m) {
                nb.b(1L);
            }
            else {
                nb.b(10L);
            }
        }
        final lb lb = (lb)this.b(neat.system.lb.i);
        if (lb != null) {
            if (this.m) {
                lb.a(0);
            }
            else {
                lb.a(100);
            }
        }
        if (this.j != null) {
            this.j.a(this);
            this.j.a(this.m, this.n);
            this.j.b(this);
        }
    }
    
    public final void a(final neat.kb kb, final neat.kb kb2) {
        if (!a.x.P) {
            return;
        }
        if (this.q != null) {
            this.q.f();
            this.q = null;
        }
        if (kb != null) {
            this.q = kb.b();
        }
        if (this.r != null) {
            this.r.f();
            this.r = null;
        }
        if (kb2 != null) {
            this.r = kb2.b();
        }
    }
    
    final neat.kb o() {
        if (!a.x.P) {
            return null;
        }
        return this.q;
    }
    
    final neat.kb p() {
        if (!a.x.P) {
            return null;
        }
        return this.r;
    }
    
    protected void q() {
        this.s = 0;
        this.t = 0;
        this.u = System.currentTimeMillis();
        this.v = -1L;
        this.w = 0L;
        eb.f = 0;
        eb.g = 0;
    }
    
    public void a(final fb x) {
        if (this.x != null) {
            this.x.f();
        }
        this.x = x;
    }
    
    public fb r() {
        return this.x;
    }
    
    private void a(final int n) {
        if (a.y.a && this.x != null) {
            this.x.b(n);
        }
    }
    
    private void s() {
        if (a.y.a && this.x != null) {
            this.x.d();
        }
    }
    
    public void t() {
        if (a.y.a && this.x != null) {
            this.x.e();
        }
    }
    
    private void u() {
        this.c(lb.i);
        this.c(nb.g);
        (this.j = this.x()).c(this.l);
        if (this.o != null || this.p != null) {
            this.j.a(this.o, this.p);
        }
        final p p = (p)this.b(neat.system.graphics.renderer.p.j);
        this.n();
        final neat.bb a = this.k.f.a();
        if (!(a instanceof ob)) {
            throw new RuntimeException(eb.z[1] + this.k);
        }
        this.j.a(this);
        this.j.a((ob)a);
        this.j.b();
        this.j.b(this);
    }
    
    private void v() {
        super.k();
    }
    
    private void w() {
        if (this.j != null) {
            if (!this.j.a()) {
                this.j.a(this);
            }
            this.j.f();
            this.j = null;
            this.c();
        }
    }
    
    protected abstract a.y x();
    
    protected void j() {
        this.u();
    }
    
    protected void k() {
        this.v();
    }
    
    protected void l() {
        try {
            this.w();
        }
        catch (Throwable t) {
            if (a.y.a && this.x != null) {
                this.x.a(eb.z[0], t);
            }
            try {
                ((mb)this.b(mb.g)).s().d();
            }
            catch (Throwable t2) {}
        }
    }
    
    protected int c() {
        return 1;
    }
    
    public void receiveEvent(final t t) {
        this.a((int)t.f);
        neat.system.f.a();
        if (this.j != null) {
            this.j.a(this);
            this.j.a((int)t.f);
            if (this.j == null) {
                return;
            }
            this.j.b(this);
            System.currentTimeMillis();
        }
    }
    
    public void receiveEvent(final s s) {
        this.s();
        if (this.j != null) {
            this.j.a(this);
            this.j.c();
            this.j.b(this);
        }
        ((p)this.b(neat.system.graphics.renderer.p.j)).q();
    }
    
    public void receiveEvent(final n n) {
        if (this.j != null) {
            this.j.a(this);
            this.j.a(n);
            this.j.b(this);
        }
    }
    
    public void receiveEvent(final o o) {
        if (this.j != null) {
            this.j.a(this);
            this.j.a(o);
            this.j.b(this);
        }
    }
    
    public void receiveEvent(final l l) {
        if (this.j != null) {
            this.j.a(this);
            this.j.a(l);
            this.j.b(this);
        }
    }
    
    public void receiveEvent(final m m) {
        if (this.j != null) {
            this.j.a(this);
            this.j.a(m);
            this.j.b(this);
        }
    }
    
    public void receiveEvent(final i i) {
        if (this.j != null) {
            this.j.a(this);
            this.j.a(i);
            this.j.b(this);
        }
    }
    
    public void receiveEvent(final j j) {
        if (this.j != null) {
            this.j.a(this);
            this.j.a(j);
            this.j.b(this);
        }
    }
    
    public void a() {
    }
    
    public void g() {
        super.g();
        this.m = false;
        this.n = 0L;
        this.q();
    }
    
    public void h() {
        super.h();
        this.x = null;
        this.j = null;
        this.c();
        if (this.l != null) {
            this.l.f();
            this.l = null;
        }
        if (this.o != null) {
            this.o.f();
            this.o = null;
        }
        if (this.p != null) {
            this.p.f();
            this.p = null;
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
    
    public eb() {
        this.j = null;
        this.k = null;
        this.l = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.x = null;
    }
    
    static {
        eb.z = new String[] { z(z("D-0hk!6,'u`,6")), z(z("F>/b9r7#cvv\u007f.nwj\u007f/rju\u007f b9`\u007f\u0012rc{3'@xl:\u0011oxe05'pl/.btd16fmh0,=")) };
        eb.h = 0;
        i = ((eb.y != null) ? eb.y : (eb.y = a(z(z("`q'e")))));
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
        charArray[n] ^= '\u0019';
        return charArray;
    }
    
    private static String z(final char[] array) {
        int length;
        int n2;
        final int n = n2 = (length = array.length);
        int n3 = 0;
        while (true) {
            Label_0085: {
                if (n > 1) {
                    break Label_0085;
                }
                length = (n2 = n3);
                do {
                    final char c = array[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = '\u0001';
                            break;
                        }
                        case 1: {
                            c2 = '_';
                            break;
                        }
                        case 2: {
                            c2 = 'B';
                            break;
                        }
                        case 3: {
                            c2 = '\u0007';
                            break;
                        }
                        default: {
                            c2 = '\u0019';
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
