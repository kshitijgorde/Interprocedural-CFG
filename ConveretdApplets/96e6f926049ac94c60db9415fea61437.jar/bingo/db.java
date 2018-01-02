// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.nb;
import a.yb;
import neat.h;
import neat.i;
import neat.kb;
import neat.lb;
import neat.system.f;
import a.ab;

public class db extends ab
{
    private static f f;
    private r g;
    private lb h;
    private kb i;
    private boolean j;
    private boolean k;
    private kb l;
    private int m;
    private boolean n;
    private int o;
    private int p;
    private int q;
    private i r;
    private kb s;
    private kb t;
    private static /* synthetic */ Class u;
    private static String[] z;
    
    private void a(final r g) {
        this.g = g;
    }
    
    void k() {
        this.k = false;
        this.n = false;
        this.g.C();
    }
    
    void a(final boolean j) {
        if (this.j == j) {
            return;
        }
        this.j = j;
        this.g.C();
    }
    
    boolean l() {
        return this.j;
    }
    
    void c(final int n) {
        if (this.n && this.o >= 0) {
            this.o -= n;
            if (this.o <= 0) {
                this.n = false;
                this.k = false;
            }
        }
        while (!this.k) {
            if (!this.a(this.h)) {
                this.g.z();
                break;
            }
        }
    }
    
    boolean m() {
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
        this.g.a(this.g.m(), kb);
    }
    
    kb n() {
        return this.l;
    }
    
    private void d(final int m) {
        this.m = m;
        this.g.C();
    }
    
    int o() {
        return this.m;
    }
    
    private void e(final int o) {
        if (this.k) {
            throw new RuntimeException(db.z[1]);
        }
        this.k = true;
        this.n = true;
        this.o = o;
    }
    
    private void d(final kb kb) {
        if (this.k) {
            throw new RuntimeException(db.z[1]);
        }
        if (this.i != null) {
            this.i.f();
            this.i = null;
        }
        if (kb != null) {
            this.i = kb.b();
        }
        else {
            this.w();
        }
        this.g.C();
    }
    
    kb p() {
        return this.i;
    }
    
    private void a(final int p2, final int q) {
        if (p2 == 0 || q == 0) {
            throw new RuntimeException(db.z[0]);
        }
        this.p = p2;
        this.q = q;
        this.g.C();
    }
    
    int q() {
        return this.p;
    }
    
    int r() {
        return this.q;
    }
    
    private void s() {
        final neat.r f = this.r.f();
        while (f.a()) {
            ((h)f.b()).c();
        }
        f.f();
        this.r.j();
    }
    
    private void a(final h h) {
        this.r.a(h);
    }
    
    neat.r t() {
        return this.r.f();
    }
    
    private void u() {
        if (this.s != null) {
            this.s.f();
            this.s = null;
        }
    }
    
    private void e(final kb kb) {
        this.u();
        if (kb != null) {
            this.s = kb.b();
        }
    }
    
    kb v() {
        return this.s;
    }
    
    private void w() {
        this.s();
        this.u();
        this.m = 1;
        if (this.t != null) {
            this.t.f();
            this.t = null;
        }
        this.g.a(0, 0);
        this.g.C();
    }
    
    private void b(final int n, final int n2) {
        this.g.a(n, n2);
    }
    
    private void f(final kb kb) {
        if (this.t != null) {
            this.t.f();
            this.t = null;
        }
        if (kb != null) {
            this.t = kb.b();
        }
        this.g.C();
    }
    
    kb x() {
        return this.t;
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
        this.m = 1;
        final neat.r f = this.r.f();
        while (f.a()) {
            ((h)f.b()).c();
        }
        f.f();
        this.r.j();
        if (this.t != null) {
            this.t.f();
            this.t = null;
        }
    }
    
    protected boolean a(final kb kb, final kb kb2, final lb lb) {
        if (kb.equals(ld.COMMAND__FONT)) {
            this.a(kb2);
        }
        else if (kb.equals(ld.COMMAND__FONT_INDEX)) {
            this.d(nb.b(kb2, 1));
        }
        else if (kb.equals(ld.COMMAND__SHOW)) {
            if (this.m()) {
                return false;
            }
            if (kb2.equals(ld.COMMAND__SHOW__ON)) {
                final kb l = lb.l();
                lb.c(0);
                this.d(l);
                l.f();
            }
            else {
                this.d(null);
            }
        }
        else if (kb.equals(ld.COMMAND__WAIT)) {
            if (this.m()) {
                return false;
            }
            this.e(nb.c(kb2));
        }
        else if (kb.equals(ld.COMMAND__SPRITE)) {
            if (kb2.d() == 0) {
                this.u();
            }
            else {
                this.e(kb2);
            }
        }
        else if (kb.equals(ld.COMMAND__CLEAR)) {
            this.w();
        }
        else if (kb.equals(ld.COMMAND__GET_SCORE)) {
            lb.d(this.g.a().db());
        }
        else {
            if (!kb.equals(ld.COMMAND__BG)) {
                return super.a(kb, kb2, lb);
            }
            this.f(kb2);
        }
        return true;
    }
    
    protected boolean a(final kb kb, final h h, final lb lb) {
        if (kb.equals(ld.COMMAND__CLIP)) {
            int c = -1;
            final kb kb2 = (kb)h.g(ld.COMMAND__CLIP__WIDTH);
            if (kb2 != null) {
                c = nb.c(kb2);
            }
            int c2 = -1;
            final kb kb3 = (kb)h.g(ld.COMMAND__CLIP__HEIGHT);
            if (kb3 != null) {
                c2 = nb.c(kb3);
            }
            this.a(c, c2);
        }
        else if (!kb.equals(ld.COMMAND__BUTTON)) {
            if (kb.equals(ld.COMMAND__LINE)) {
                final h e = h.e();
                final neat.r a = h.a();
                while (a.a()) {
                    final kb kb4 = (kb)a.b();
                    final kb kb5 = (kb)h.g(kb4);
                    if (kb5 != null) {
                        e.a(kb4.b(), kb5.b());
                    }
                }
                a.f();
                this.a(e);
            }
            else {
                if (!kb.equals(ld.COMMAND__OFFSET)) {
                    return super.a(kb, h, lb);
                }
                int c3 = 0;
                final kb kb6 = (kb)h.g(ld.COMMAND__OFFSET__X);
                if (kb6 != null) {
                    c3 = nb.c(kb6);
                }
                int c4 = 0;
                final kb kb7 = (kb)h.g(ld.COMMAND__OFFSET__Y);
                if (kb7 != null) {
                    c4 = nb.c(kb7);
                }
                this.b(c3, c4);
            }
        }
        return true;
    }
    
    protected void j() {
        this.k();
    }
    
    public static db b(final r r) {
        final db db = (db)bingo.db.f.a();
        db.a(r);
        return db;
    }
    
    public void f() {
        db.f.a(this);
    }
    
    public void g() {
        super.g();
        this.h = lb.a();
        this.j = false;
        this.k = false;
        this.n = false;
        this.p = -1;
        this.q = -1;
        this.r = neat.i.k();
        this.m = 1;
    }
    
    public void h() {
        this.s();
        super.h();
        this.u();
        this.h.f();
        this.h = null;
        this.r.f();
        this.r = null;
        this.g = null;
        if (this.t != null) {
            this.t.f();
            this.t = null;
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
    
    public db() {
        this.g = null;
        this.h = null;
        this.i = null;
        this.l = null;
        this.r = null;
        this.s = null;
        this.t = null;
    }
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "0\u000f \f\u0011S\u001d+_E\u0010\u0002'[\u0015\u001a\u0000)\u000b\u0004\u0001\u000bn_\nS\u001d'Q\u0000S\u0014+Y\nSO".toCharArray();
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
                            c2 = 's';
                            break;
                        }
                        case 1: {
                            c2 = 'n';
                            break;
                        }
                        case 2: {
                            c2 = 'N';
                            break;
                        }
                        case 3: {
                            c2 = '+';
                            break;
                        }
                        default: {
                            c2 = 'e';
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
        final char[] charArray2 = "<\u001a&N\u0017S\r!F\b\u0012\u0000*\u000b\f\u0000N>Y\n\u0010\u000b=X\f\u001d\tn\n".toCharArray();
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
                            c4 = 's';
                            break;
                        }
                        case 1: {
                            c4 = 'n';
                            break;
                        }
                        case 2: {
                            c4 = 'N';
                            break;
                        }
                        case 3: {
                            c4 = '+';
                            break;
                        }
                        default: {
                            c4 = 'e';
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
        final char[] charArray3 = "\u0011\u0007 L\n]\n,".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0334: {
                if (n10 > 1) {
                    break Label_0334;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = 's';
                            break;
                        }
                        case 1: {
                            c6 = 'n';
                            break;
                        }
                        case 2: {
                            c6 = 'N';
                            break;
                        }
                        case 3: {
                            c6 = '+';
                            break;
                        }
                        default: {
                            c6 = 'e';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 <= n12) {
                z[n9] = new String(charArray3).intern();
                db.z = z;
                db.f = new f((db.u != null) ? db.u : (db.u = a(db.z[2])));
                return;
            }
            continue;
        }
    }
}
