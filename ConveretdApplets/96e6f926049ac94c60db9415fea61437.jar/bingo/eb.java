// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.h;
import neat.nb;
import a.yb;
import neat.kb;
import neat.lb;
import neat.system.f;
import a.ab;

public class eb extends ab
{
    private static f f;
    private t g;
    private lb h;
    private kb i;
    private boolean j;
    private boolean k;
    private kb l;
    private boolean m;
    private int n;
    private boolean o;
    private boolean p;
    private float q;
    private float r;
    private boolean s;
    private boolean t;
    private int u;
    private int v;
    private boolean w;
    private kb x;
    private kb y;
    private int z;
    private int A;
    private int B;
    private boolean C;
    private boolean D;
    private boolean E;
    private boolean F;
    private static /* synthetic */ Class G;
    private static String[] H;
    
    private void a(final t g) {
        this.g = g;
    }
    
    void k() {
        this.k = false;
        this.m = false;
        this.o = false;
        this.s = false;
        this.r();
        this.g.v();
    }
    
    void a(final boolean j) {
        if (this.j == j) {
            return;
        }
        this.j = j;
        this.g.v();
    }
    
    void c(final int n) {
        if (this.k && this.C && this.E) {
            this.d(this.D);
        }
        if (this.m && this.n >= 0) {
            this.n -= n;
            if (this.n <= 0) {
                this.m = false;
                this.k = false;
            }
        }
        if (this.o) {
            this.q += this.r * n;
            if (this.q >= 1.0f) {
                this.o = false;
                this.k = false;
            }
            this.g.x();
        }
        if (this.s) {
            this.v += n;
            if (this.v >= this.u) {
                this.s = false;
                this.k = false;
            }
            this.g.x();
        }
        while (!this.k) {
            if (!this.a(this.h)) {
                this.g.s();
                break;
            }
        }
    }
    
    boolean l() {
        return this.k;
    }
    
    private void a(final kb kb) {
        if (this.l != null) {
            this.l.f();
            this.l = null;
        }
        if (kb != null) {
            this.l = kb.b();
        }
        this.g.a(kb);
    }
    
    kb m() {
        return this.l;
    }
    
    private void d(final int n) {
        if (this.k) {
            throw new RuntimeException(eb.H[0]);
        }
        this.k = true;
        this.m = true;
        this.n = n;
    }
    
    private void a(final boolean p2, final float n) {
        if (this.k) {
            throw new RuntimeException(eb.H[0]);
        }
        this.k = true;
        this.o = true;
        this.p = p2;
        this.r = 1.0f / n;
        this.q = 0.0f;
        this.g.x();
    }
    
    boolean n() {
        return this.o;
    }
    
    boolean o() {
        return this.p;
    }
    
    float p() {
        return this.q;
    }
    
    private void a(final boolean t, final int u) {
        if (this.k) {
            throw new RuntimeException(eb.H[0]);
        }
        if (!this.g.a().F()) {
            return;
        }
        this.k = true;
        this.s = true;
        this.t = t;
        this.u = u;
        this.v = 0;
        this.g.x();
    }
    
    private void d(final kb kb) {
        if (this.k) {
            throw new RuntimeException(eb.H[0]);
        }
        if (this.i != null) {
            this.i.f();
            this.i = null;
        }
        if (kb != null) {
            this.i = kb.b();
        }
        else {
            this.r();
        }
        this.g.v();
    }
    
    kb q() {
        return this.i;
    }
    
    private void a(final kb kb, final kb kb2, final int z, final int a, final int b) {
        this.w = true;
        if (this.x != null) {
            this.x.f();
            this.x = null;
        }
        if (kb != null) {
            this.x = kb.b();
        }
        if (this.y != null) {
            this.y.f();
            this.y = null;
        }
        if (kb2 != null) {
            this.y = kb2.b();
        }
        this.z = z;
        this.A = a;
        this.B = b;
    }
    
    public void a(final int a, final int b) {
        this.A = a;
        this.B = b;
    }
    
    private void r() {
        this.w = false;
        if (this.x != null) {
            this.x.f();
            this.x = null;
        }
        if (this.y != null) {
            this.y.f();
            this.y = null;
        }
    }
    
    boolean s() {
        return this.w;
    }
    
    kb t() {
        return this.x;
    }
    
    kb u() {
        return this.y;
    }
    
    int v() {
        return this.z;
    }
    
    int w() {
        return this.A;
    }
    
    int x() {
        return this.B;
    }
    
    private void b(final boolean c) {
        this.C = c;
        this.D = false;
    }
    
    private void c(final boolean b) {
        this.C = b;
        this.D = b;
    }
    
    boolean d(final boolean b) {
        if (!this.C) {
            return this.E = false;
        }
        if (this.D && !b) {
            return this.E = false;
        }
        if (this.m) {
            this.m = false;
            this.k = false;
            this.E = false;
        }
        else {
            this.E = true;
        }
        return true;
    }
    
    private void y() {
        this.F = true;
        this.g.v();
    }
    
    boolean z() {
        return this.F;
    }
    
    public void a(final yb yb) {
        super.a(yb);
    }
    
    public void a() {
        super.a();
        this.k();
        this.h.c(0);
        if (this.i != null) {
            this.i.f();
            this.i = null;
        }
        if (this.l != null) {
            this.l.f();
            this.l = null;
        }
    }
    
    protected boolean a(final kb kb, final kb kb2, final lb lb) {
        if (kb.equals(md.COMMAND__FONT)) {
            this.a(kb2);
        }
        else if (kb.equals(md.COMMAND__SHOW)) {
            if (this.l()) {
                return false;
            }
            if (kb2.equals(md.COMMAND__SHOW__ON)) {
                if (lb.j() == 0) {
                    this.d(null);
                }
                else {
                    final kb l = lb.l();
                    lb.c(0);
                    this.d(l);
                    l.f();
                }
                this.b(false);
            }
            else if (kb2.equals(md.COMMAND__SHOW__INTERACT)) {
                this.b(true);
            }
            else if (kb2.equals(md.COMMAND__SHOW__INTERACT_INSIDE)) {
                this.c(true);
            }
            else if (kb2.equals(md.COMMAND__SHOW__OFF)) {
                this.d(null);
            }
        }
        else if (kb.equals(md.COMMAND__WAIT)) {
            if (this.l()) {
                return false;
            }
            this.d(nb.c(kb2));
        }
        else {
            if (!kb.equals(md.COMMAND__FORCE_HELP)) {
                return super.a(kb, kb2, lb);
            }
            this.y();
        }
        return true;
    }
    
    protected boolean a(final kb kb, final h h, final lb lb) {
        if (kb.equals(md.COMMAND__HELP)) {
            final kb kb2 = (kb)h.g(md.COMMAND__HELP__BUBBLE);
            if (kb2 == null) {
                this.r();
            }
            else {
                final kb kb3 = (kb)h.g(md.COMMAND__HELP__BUBBLE_RESERVED);
                int c = 0;
                final kb kb4 = (kb)h.g(md.COMMAND__HELP__BUBBLE_SIZE);
                if (kb4 != null) {
                    c = nb.c(kb4);
                }
                final kb kb5 = (kb)h.g(md.COMMAND__HELP__X);
                int n;
                if (kb5 != null) {
                    n = nb.c(kb5);
                }
                else {
                    n = this.A;
                }
                final kb kb6 = (kb)h.g(md.COMMAND__HELP__Y);
                int n2;
                if (kb6 != null) {
                    n2 = nb.c(kb6);
                }
                else {
                    n2 = this.B;
                }
                this.a(kb2, kb3, c, n, n2);
            }
        }
        else if (kb.equals(md.COMMAND__SCROLL)) {
            if (this.l()) {
                return false;
            }
            final kb kb7 = (kb)h.g(md.COMMAND__SCROLL__TYPE);
            final boolean b = kb7 != null && kb7.equals(md.COMMAND__SCROLL__TYPE__IN);
            int c2 = 1;
            final kb kb8 = (kb)h.g(md.COMMAND__SCROLL__SPEED);
            if (kb8 != null) {
                c2 = nb.c(kb8);
            }
            this.a(b, (float)c2);
        }
        else {
            if (!kb.equals(md.COMMAND__FADE)) {
                return super.a(kb, h, lb);
            }
            if (this.l()) {
                return false;
            }
            final kb kb9 = (kb)h.g(md.COMMAND__FADE__TYPE);
            final boolean b2 = kb9 != null && kb9.equals(md.COMMAND__FADE__TYPE__IN);
            int c3 = 1;
            final kb kb10 = (kb)h.g(md.COMMAND__FADE__TIME);
            if (kb10 != null) {
                c3 = nb.c(kb10);
            }
            this.a(b2, c3);
        }
        return true;
    }
    
    protected void j() {
        this.k();
    }
    
    public static eb b(final t t) {
        final eb eb = (eb)bingo.eb.f.a();
        eb.a(t);
        return eb;
    }
    
    public void f() {
        eb.f.a(this);
    }
    
    public void g() {
        super.g();
        this.h = lb.a();
        this.j = false;
        this.k = false;
        this.m = false;
        this.s = false;
        this.w = false;
        this.A = 0;
        this.B = 0;
        this.C = false;
        this.D = false;
        this.E = false;
        this.F = false;
    }
    
    public void h() {
        super.h();
        this.h.f();
        this.h = null;
        this.g = null;
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
        this.g = null;
        this.h = null;
        this.i = null;
        this.l = null;
        this.x = null;
        this.y = null;
    }
    
    static {
        final String[] h = new String[2];
        final int n = 0;
        final char[] charArray = "\u007f7\u0019Y\u0013\u0010 \u001eQ\fQ-\u0015\u001c\bCc\u0001N\u000eS&\u0002O\b^$Q\u001d".toCharArray();
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
                            c2 = '0';
                            break;
                        }
                        case 1: {
                            c2 = 'C';
                            break;
                        }
                        case 2: {
                            c2 = 'q';
                            break;
                        }
                        case 3: {
                            c2 = '<';
                            break;
                        }
                        default: {
                            c2 = 'a';
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
        h[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "R*\u001f[\u000e\u001e&\u0013".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0218: {
                if (n6 > 1) {
                    break Label_0218;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '0';
                            break;
                        }
                        case 1: {
                            c4 = 'C';
                            break;
                        }
                        case 2: {
                            c4 = 'q';
                            break;
                        }
                        case 3: {
                            c4 = '<';
                            break;
                        }
                        default: {
                            c4 = 'a';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 <= n8) {
                h[n5] = new String(charArray2).intern();
                eb.H = h;
                eb.f = new f((eb.G != null) ? eb.G : (eb.G = a(eb.H[1])));
                return;
            }
            continue;
        }
    }
}
