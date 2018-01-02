// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.system.l;
import neat.system.n;
import neat.cb;
import neat.r;
import neat.system.graphics.renderer.m;
import neat.i;
import neat.system.f;

public class d extends c implements s, q, o, p
{
    private static f k;
    private i l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    public int r;
    public boolean s;
    public int t;
    public boolean u;
    public boolean v;
    private m w;
    private boolean x;
    private boolean y;
    private int z;
    private int A;
    private int B;
    private boolean C;
    private boolean D;
    private float E;
    private float F;
    private static /* synthetic */ Class G;
    private static String[] H;
    
    public void a(final u u) {
        if (this.l.b(u)) {
            throw new RuntimeException(d.H[1] + u);
        }
        this.l.a(u);
    }
    
    public void b(final u u) {
        if (!this.l.d(u)) {
            throw new RuntimeException(d.H[2] + u);
        }
    }
    
    public void a(final boolean m) {
        if (this.m == m) {
            return;
        }
        if (!(this.m = m)) {
            this.n = false;
            if (this.p && this.o) {
                final r f = this.l.f();
                while (f.a()) {
                    ((u)f.b()).b(this);
                }
                f.f();
            }
        }
        else {
            this.p = false;
        }
        this.k();
    }
    
    public void b(final boolean n) {
        if (this.n == n) {
            return;
        }
        this.n = n;
        this.k();
    }
    
    public void c(final boolean o) {
        if (this.o == o) {
            return;
        }
        if (!(this.o = o)) {
            this.p = false;
        }
        this.k();
    }
    
    public void b() {
        if (!this.m || this.p) {
            return;
        }
        this.p = true;
        final ib ib = (ib)this.a();
        this.r = ib.o;
        this.k();
        if (ib.q != null) {
            final b b = this.i.b(ib.q);
            if (b instanceof g) {
                ((g)b).c();
            }
        }
        final r f = this.l.f();
        while (f.a()) {
            ((u)f.b()).a(this);
        }
        f.f();
    }
    
    public void c() {
        if (!this.m || !this.p) {
            return;
        }
        this.p = false;
        this.k();
        final ib ib = (ib)this.a();
        if (ib.r != null) {
            final b b = this.i.b(ib.r);
            if (b instanceof g) {
                ((g)b).c();
            }
        }
        final r f = this.l.f();
        while (f.a()) {
            ((u)f.b()).b(this);
        }
        f.f();
    }
    
    public void d(final boolean s) {
        if (this.s == s) {
            return;
        }
        this.s = s;
        this.u = this.n;
        this.t = 0;
        this.k();
    }
    
    private void k() {
        final ib ib = (ib)this.a();
        final int r = this.r();
        if (this.v) {
            this.e(false);
        }
        else if (r == 4) {
            this.e(true);
            if (this.p) {
                this.c(3);
            }
            else if (this.m && (this.o || this.n || (this.s && this.u))) {
                this.c(2);
            }
            else if (this.m) {
                this.c(1);
            }
            else {
                this.c(0);
            }
        }
        else if (r == 3) {
            if (ib.s == 103) {
                this.e(true);
                if (this.p || (this.m && (this.o || this.n || (this.s && this.u)))) {
                    this.c(2);
                }
                else if (this.m) {
                    this.c(1);
                }
                else {
                    this.c(0);
                }
            }
            else if (this.p) {
                this.e(true);
                this.c(2);
            }
            else if (this.m && (this.o || this.n || (this.s && this.u))) {
                this.e(true);
                this.c(1);
            }
            else if (ib.s == 4) {
                if (this.m ^ ib.t) {
                    this.e(true);
                    this.c(0);
                }
                else {
                    this.e(false);
                }
            }
            else {
                this.e(true);
                this.c(0);
            }
        }
        else if (r == 2) {
            if (ib.s == 2) {
                this.e(true);
                if (this.p || (this.m && (this.o || this.n || (this.s && this.u)))) {
                    this.c(1);
                }
                else {
                    this.c(0);
                }
            }
            else if (ib.s == 103) {
                if (this.p || (this.m && (this.o || this.n || (this.s && this.u)))) {
                    this.c(1);
                    this.e(true);
                }
                else if (this.m ^ ib.t) {
                    this.e(true);
                    this.c(0);
                }
                else {
                    this.e(false);
                }
            }
            else if (this.p) {
                this.e(true);
                this.c(1);
            }
            else if (this.m && (this.o || this.n || (this.s && this.u))) {
                this.e(true);
                this.c(0);
            }
            else {
                this.e(false);
            }
        }
        else if (this.m && (this.p || this.o || this.n || (this.s && this.u))) {
            this.e(true);
        }
        else {
            this.e(false);
        }
    }
    
    private void b(final int n) {
        if (this.p && (this.q || !this.m)) {
            this.r -= n;
            if (this.r <= 0) {
                this.p = false;
                this.k();
                if (this.m) {
                    final r f = this.l.f();
                    while (f.a()) {
                        ((u)f.b()).b(this);
                    }
                    f.f();
                }
            }
        }
        if (this.s) {
            this.t += n;
            if (this.t >= ((ib)this.a()).p) {
                this.t = 0;
                this.u ^= true;
                this.k();
            }
        }
    }
    
    private void l() {
        final ib ib = (ib)this.a();
        if (ib.i != null) {
            this.a(ib.i);
        }
    }
    
    public void a(final neat.cb cb) {
        this.p();
        this.w = this.i.b(cb);
        if (this.w != null && !this.C) {
            this.A = this.w.c();
            this.B = this.w.d();
        }
        this.z = 0;
        this.y = false;
        this.x = true;
    }
    
    private void p() {
        if (this.w != null) {
            this.i.a(this.w);
            this.w = null;
        }
        this.x = false;
        this.y = false;
        this.z = 0;
    }
    
    public void b(final neat.cb cb) {
        this.a(cb);
        this.k();
    }
    
    public void q() {
        this.l();
        this.k();
    }
    
    protected void e(final boolean y) {
        if (this.y == y) {
            return;
        }
        this.y = y;
        this.x = true;
    }
    
    public void a(final int a, final int b) {
        if (this.A == a && this.B == b) {
            return;
        }
        this.A = a;
        this.B = b;
        this.C = true;
        this.x = true;
    }
    
    private void c(final int z) {
        if (this.z == z) {
            return;
        }
        this.z = z;
        this.x = true;
    }
    
    private int r() {
        if (this.w == null) {
            return 0;
        }
        if (this.w.p() <= 1) {
            return 0;
        }
        return this.w.p();
    }
    
    private void s() {
        if (this.w != null && this.x) {
            this.x = false;
            this.w.a(this.A, this.B);
            if (this.w.p() > 1) {
                this.w.b(this.z);
            }
            if (this.D) {
                this.w.a(this.E, this.F);
            }
            else {
                this.w.s();
            }
            this.w.a(this.y);
            this.a(this.w);
        }
    }
    
    public boolean b(final int n, final int n2) {
        this.s();
        if (this.w != null && this.m) {
            final ib ib = (ib)this.a();
            final int n3 = this.w.c() + ib.j;
            final int n4 = this.w.d() + ib.k;
            final int n5 = n3;
            int n6;
            if (ib.l > 0) {
                n6 = n5 + ib.l;
            }
            else {
                n6 = n5 + this.w.e();
            }
            final int n7 = n4;
            int n8;
            if (ib.m > 0) {
                n8 = n7 + ib.m;
            }
            else {
                n8 = n7 + this.w.f();
            }
            return n >= n3 && n < n6 && n2 >= n4 && n2 < n8;
        }
        return false;
    }
    
    protected void a(final m m) {
    }
    
    protected void a(final gb gb) {
        super.a(gb);
        if (!(gb instanceof ib)) {
            throw new RuntimeException(d.H[0] + gb);
        }
        this.q = ((ib)gb).n;
    }
    
    public void d() {
        this.l();
        this.k();
    }
    
    public void a(final int n) {
        this.b(n);
    }
    
    public void a() {
        this.s();
    }
    
    public boolean a(final n n) {
        if (n.f && this.b(n.d, n.e)) {
            this.b();
            return true;
        }
        return false;
    }
    
    public boolean a(final neat.system.o o) {
        if (o.f && this.b(o.d, o.e)) {
            this.c();
            return true;
        }
        return false;
    }
    
    public boolean a(final neat.system.m m) {
        this.c(this.b(m.d, m.e));
        return false;
    }
    
    public boolean a(final l l) {
        this.c(this.b(l.d, l.e));
        return false;
    }
    
    public static d t() {
        return (d)d.k.a();
    }
    
    public void f() {
        d.k.a(this);
    }
    
    public void g() {
        super.g();
        this.l = neat.i.k();
        this.m = false;
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = false;
        this.x = false;
        this.y = true;
        this.C = false;
        this.A = -1;
        this.B = -1;
        this.z = 0;
        this.D = false;
        this.s = false;
        this.v = false;
    }
    
    public void h() {
        this.p();
        final r f = this.l.f();
        while (f.a()) {
            ((u)f.b()).c(this);
        }
        f.f();
        this.l.f();
        this.l = null;
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
    
    public d() {
        this.l = null;
        this.w = null;
    }
    
    static {
        final String[] h = new String[4];
        final int n = 0;
        final char[] charArray = "1\u0000I?|\u0016\u0000A(3\u0012HO*|\f\u001cE!|\b\u001dS8|\u0007\r\u0000-|'\u001dT83\u000b;H-8\n\u001f\u0000%1\u0015\u0004E!9\u000b\u001cA85\n\u0006\u001a".toCharArray();
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
                            c2 = 'e';
                            break;
                        }
                        case 1: {
                            c2 = 'h';
                            break;
                        }
                        case 2: {
                            c2 = ' ';
                            break;
                        }
                        case 3: {
                            c2 = 'L';
                            break;
                        }
                        default: {
                            c2 = '\\';
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
        final char[] charArray2 = "1\u0000I?|\t\u0001S89\u000b\rRl5\u0016HA .\u0000\tD5|\u0004\fD)8E\u001cOl>\u0010\u001cT#2_".toCharArray();
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
                            c4 = 'e';
                            break;
                        }
                        case 1: {
                            c4 = 'h';
                            break;
                        }
                        case 2: {
                            c4 = ' ';
                            break;
                        }
                        case 3: {
                            c4 = 'L';
                            break;
                        }
                        default: {
                            c4 = '\\';
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
        h[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "1\u0000I?|\t\u0001S89\u000b\rRl5\u0016HN#(E\tD(9\u0001HT#|\u0007\u001dT83\u000bR".toCharArray();
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
                            c6 = 'e';
                            break;
                        }
                        case 1: {
                            c6 = 'h';
                            break;
                        }
                        case 2: {
                            c6 = ' ';
                            break;
                        }
                        case 3: {
                            c6 = 'L';
                            break;
                        }
                        default: {
                            c6 = '\\';
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
        h[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "\u0004FD".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0450: {
                if (n14 > 1) {
                    break Label_0450;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = 'e';
                            break;
                        }
                        case 1: {
                            c8 = 'h';
                            break;
                        }
                        case 2: {
                            c8 = ' ';
                            break;
                        }
                        case 3: {
                            c8 = 'L';
                            break;
                        }
                        default: {
                            c8 = '\\';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 <= n16) {
                h[n13] = new String(charArray4).intern();
                d.H = h;
                d.k = new f((d.G != null) ? d.G : (d.G = a(d.H[3])));
                return;
            }
            continue;
        }
    }
}
