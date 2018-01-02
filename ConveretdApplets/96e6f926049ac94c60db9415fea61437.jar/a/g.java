// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.r;
import neat.system.graphics.renderer.l;
import neat.system.graphics.renderer.p;
import neat.system.gb;
import neat.i;
import neat.system.f;

public class g extends c implements s
{
    private static f k;
    private boolean l;
    private i m;
    private int n;
    private int o;
    private boolean p;
    private boolean q;
    private float r;
    private float s;
    private boolean t;
    private static /* synthetic */ Class u;
    private static String[] z;
    
    public boolean a(final boolean b) {
        if (this.l) {
            return true;
        }
        final lb lb = (lb)this.a();
        if (lb.i == null) {
            return this.l = true;
        }
        if (!this.d(b)) {
            return false;
        }
        final p b2 = this.i.b();
        final neat.system.lb lb2 = (neat.system.lb)this.i.a(neat.system.lb.i);
        this.r = lb.i.g;
        for (int i = 0; i < lb.j; ++i) {
            final l v = b2.v();
            v.a(lb2, lb.i, this.i.m());
            this.m.a(v);
        }
        this.l = true;
        this.p();
        return true;
    }
    
    public boolean a() {
        return !this.l && this.s();
    }
    
    private void b() {
        this.k();
        final p b = this.i.b();
        final r f = this.m.f();
        while (f.a()) {
            b.a((l)f.b());
        }
        f.f();
        this.m.c();
        this.n = 0;
        this.o = 0;
        this.l = false;
        if (this.n()) {
            this.r();
        }
    }
    
    private void b(final int n) {
        if (this.p != this.i.k()) {
            this.p = this.i.k();
            if (((lb)this.a()).l) {
                final r f = this.m.f();
                while (f.a()) {
                    final l l = (l)f.b();
                    if (this.p) {
                        l.b();
                    }
                    l.b();
                }
                f.f();
            }
        }
        if (this.o > 0) {
            this.o -= n;
            if (this.o < 0) {
                this.o = 0;
            }
        }
    }
    
    public void c() {
        if (!this.q) {
            return;
        }
        if (!this.a(true)) {
            return;
        }
        if (this.o > 0) {
            return;
        }
        if (this.m.e()) {
            return;
        }
        if (++this.n >= this.m.i()) {
            this.n = 0;
        }
        ((l)this.m.a(this.n)).a();
        final lb lb = (lb)this.a();
        if (lb.k > 0) {
            this.o = lb.k;
        }
    }
    
    public void k() {
        final r f = this.m.f();
        while (f.a()) {
            ((l)f.b()).b();
        }
        f.f();
        this.o = 0;
    }
    
    public boolean m() {
        return ((lb)this.a()).m;
    }
    
    public boolean n() {
        return ((lb)this.a()).n;
    }
    
    public void b(final boolean q) {
        if (this.q != q && !(this.q = q)) {
            this.k();
        }
    }
    
    public boolean o() {
        return this.q;
    }
    
    public void a(final float r) {
        if (this.r != r) {
            this.r = r;
            this.p();
        }
    }
    
    public void b(final float s) {
        if (this.s != s) {
            this.s = s;
            this.p();
        }
    }
    
    private void p() {
        if (!this.l) {
            return;
        }
        final float c = this.c(this.r * this.s);
        final r f = this.m.f();
        while (f.a()) {
            ((l)f.b()).a(c);
        }
        f.f();
    }
    
    private float c(final float n) {
        if (n == 0.0f) {
            return 0.0f;
        }
        if (n == 1.0f) {
            return 1.0f;
        }
        final float n2 = 0.6f;
        final float n3 = 0.9f;
        float n4;
        if (n < 0.5f) {
            n4 = n2 + (n3 - n2) * (n / 0.5f);
        }
        else {
            n4 = n3 + (1.0f - n3) * (n - 0.5f) / 0.5f;
        }
        return n4;
    }
    
    public void q() {
        this.b();
    }
    
    public void c(final boolean t) {
        if (this.t != t) {
            this.t = t;
        }
    }
    
    private boolean d(final boolean b) {
        return !this.i.l();
    }
    
    private void c(final int n) {
    }
    
    private void r() {
    }
    
    public boolean s() {
        return false;
    }
    
    protected void a(final a.gb gb) {
        super.a(gb);
        if (!(gb instanceof lb)) {
            throw new RuntimeException(g.z[0] + gb);
        }
        final lb lb = (lb)gb;
    }
    
    public void d() {
    }
    
    public void a(final int n) {
        this.b(n);
        this.c(n);
    }
    
    public static g t() {
        return (g)g.k.a();
    }
    
    public void f() {
        g.k.a(this);
    }
    
    public void g() {
        super.g();
        this.l = false;
        this.m = neat.i.k();
        this.n = 0;
        this.p = false;
        this.q = true;
        this.r = 1.0f;
        this.s = 1.0f;
        this.t = true;
    }
    
    public void h() {
        this.b();
        this.m.f();
        this.m = null;
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
    
    public g() {
        this.m = null;
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "c=\u007fJ\u007fD=w]0@uy_\u007f^!sT\u007fZ eM\u007fU06X\u007fd:cW;~!sT\f_4rV(\u0017<{I3R8sW+V!\u007fV1\r".toCharArray();
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
                            c2 = '7';
                            break;
                        }
                        case 1: {
                            c2 = 'U';
                            break;
                        }
                        case 2: {
                            c2 = '\u0016';
                            break;
                        }
                        case 3: {
                            c2 = '9';
                            break;
                        }
                        default: {
                            c2 = '_';
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
        final char[] charArray2 = "V{q".toCharArray();
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
                            c4 = '7';
                            break;
                        }
                        case 1: {
                            c4 = 'U';
                            break;
                        }
                        case 2: {
                            c4 = '\u0016';
                            break;
                        }
                        case 3: {
                            c4 = '9';
                            break;
                        }
                        default: {
                            c4 = '_';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 <= n8) {
                z[n5] = new String(charArray2).intern();
                g.z = z;
                g.k = new f((g.u != null) ? g.u : (g.u = a(g.z[1])));
                return;
            }
            continue;
        }
    }
}
