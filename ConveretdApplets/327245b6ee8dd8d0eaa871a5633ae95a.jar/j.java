import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

class j implements h
{
    static Hashtable a;
    k b;
    b[] c;
    l[] d;
    int[] e;
    n[] f;
    int g;
    int h;
    private boolean i;
    private Vector j;
    private Vector k;
    private Vector l;
    private Vector m;
    int n;
    c p;
    t q;
    
    public void a(final c p) {
        this.p = p;
    }
    
    public c a() {
        return this.p;
    }
    
    public void a(final t q) {
        if (this.q == null) {
            this.q = q;
        }
    }
    
    public j() {
        this.c = new b[4];
        this.d = new l[4];
        this.e = new int[4];
        this.f = new n[4];
        this.h = -1;
        this.i = false;
        this.j = new Vector();
        this.k = new Vector();
        this.l = new Vector();
        this.m = new Vector();
        this.n = 0;
    }
    
    public j(final b b) {
        this.c = new b[4];
        this.d = new l[4];
        this.e = new int[4];
        this.f = new n[4];
        this.h = -1;
        this.i = false;
        this.j = new Vector();
        this.k = new Vector();
        this.l = new Vector();
        this.m = new Vector();
        this.n = 0;
        if (b != null) {
            this.q = b.c;
        }
    }
    
    void b() {
        final boolean l = c.l;
        int n = 0;
        while (true) {
            while (true) {
                Label_0091: {
                    if (!l) {
                        break Label_0091;
                    }
                    final j j = this;
                    if (j.f != null && this.f[n] != null) {
                        this.f[n].a();
                    }
                    if (this.d != null) {
                        this.d[n] = null;
                    }
                    if (this.c[n] != null) {
                        this.c[n].ac();
                        if (this.c[n].l) {
                            this.c[n].a();
                        }
                    }
                    ++n;
                }
                if (n < 4) {
                    continue;
                }
                break;
            }
            this.f = null;
            this.d = null;
            final j j = this;
            if (!l) {
                if (this.b != null) {
                    this.b.d();
                }
                this.b = null;
                this.j.removeAllElements();
                this.k.removeAllElements();
                this.m.removeAllElements();
                this.l.removeAllElements();
                this.p.c = null;
                this.q = null;
                return;
            }
            continue;
        }
    }
    
    final void a(final i i, final int n) {
        final boolean l = c.l;
        final a9 a9 = i.m[n];
        final String string = i.c + a9.a;
        if (this.j.indexOf(string) != -1) {
            return;
        }
        if (i.p[n] == null) {
            i.p[n] = new float[4][];
        }
        final float[][] array = i.p[n];
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0148: {
                    if (!l) {
                        break Label_0148;
                    }
                    if (this.f[n2] != null) {
                        this.a(a9, array, n2);
                        this.f[n2].c = this.j.size() + 1;
                    }
                    if (this.d[n2] != null) {
                        this.d[n2].e();
                    }
                    ++n2;
                }
                if (n2 < 4) {
                    continue;
                }
                break;
            }
            this.i = true;
            this.j.addElement(string);
            this.k.addElement(a9);
            this.l.addElement(array);
            this.m.addElement(i);
            if (!l) {
                return;
            }
            continue;
        }
    }
    
    final void a(final i i, final String s) {
        final int index;
        if ((index = this.j.indexOf(i.c + s)) == -1) {
            return;
        }
        this.j.removeElementAt(index);
        this.k.removeElementAt(index);
        this.l.removeElementAt(index);
        this.m.removeElementAt(index);
        if (this.j.isEmpty()) {
            this.i = false;
            if (this.b != null) {
                this.b.c();
            }
            int n = 0;
            while (true) {
                Label_0143: {
                    if (!c.l) {
                        break Label_0143;
                    }
                    if (this.d[n] != null) {
                        this.d[n].f();
                        this.d[n] = null;
                    }
                    ++n;
                }
                if (n < 4) {
                    continue;
                }
                break;
            }
        }
    }
    
    void c() {
        blaze3d.a();
        this.h = blaze3d.c;
    }
    
    int d() {
        final boolean l = c.l;
        int n = 0;
        while (true) {
            while (true) {
                Label_0125: {
                    if (!l) {
                        break Label_0125;
                    }
                    Label_0122: {
                        if (this.c[n] == null) {
                            break Label_0122;
                        }
                        final int h;
                        int n2 = h;
                        if (this.c[n].i == null) {
                            n2 = 1;
                        }
                        if (this.c[n].c == null) {
                            this.c[n].c = this.q;
                        }
                        final int ab = this.c[n].ab();
                        if (ab > this.h) {
                            this.h = ab;
                        }
                        this.d[n] = this.c[n].i;
                        if (n2 != 0 && this.d[n] != null) {
                            this.d[n].e();
                        }
                    }
                    ++n;
                }
                if (n < 4) {
                    continue;
                }
                break;
            }
            final int h = this.h;
            if (!l) {
                return h;
            }
            continue;
        }
    }
    
    boolean a(final g g, final a9 a9, final float[][] array) {
        final boolean b = true;
        int n = 0;
        while (true) {
            Label_0087: {
                if (!c.l) {
                    break Label_0087;
                }
                if (this.d[n] != null) {
                    final n n2 = this.f[n];
                    if (n2 != null && (!n2.b || n2.c != 0)) {
                        n2.a(g, a9, array[n], this.d[n]);
                        final n n3 = n2;
                        --n3.c;
                    }
                }
                ++n;
            }
            if (n >= 4) {
                return b;
            }
            continue;
        }
    }
    
    private final void b(final int n) {
        int n2 = 0;
        while (true) {
            Label_0044: {
                if (!c.l) {
                    break Label_0044;
                }
                this.a((a9)this.k.elementAt(n2), (float[][])this.l.elementAt(n2), n);
                ++n2;
            }
            if (n2 >= this.j.size()) {
                return;
            }
            continue;
        }
    }
    
    private final void a(final a9 a9, final float[][] array, final int n) {
        int n2 = 0;
        Label_0036: {
            if (this.f[n].b) {
                n2 = a9.b * 3 * 2;
                if (!c.l) {
                    break Label_0036;
                }
            }
            n2 = a9.g * 2;
        }
        if (array[n] == null || array[n].length < n2) {
            array[n] = null;
            array[n] = new float[n2];
        }
    }
    
    public boolean a(final String s, final int n, final String s2, final b b) {
        return this.a(s, n, s2, 0.0f, null, b, this.q);
    }
    
    public boolean a(final String s, final int n, final String s2, final String s3) {
        return this.a(s, n, s2, 0.0f, s3, null, this.q);
    }
    
    public boolean a(final String s, final int n, final String s2, final String s3, final b b) {
        return this.a(s, n, s2, 0.0f, s3, null, b.c);
    }
    
    public boolean a(final String s, final int n, final String s2, final float n2) {
        return this.a(s, n, s2, n2, null, null, this.q);
    }
    
    public boolean b(final String s, final int n, final String s2, final b b) {
        return this.a(s, n, s2, 0.0f, null, b, this.q);
    }
    
    public boolean b(final String s, final int n, final String s2, final String s3) {
        return this.a(s, n, s2, 0.0f, s3, null, this.q);
    }
    
    public boolean b(final String s, final int n, final String s2, final String s3, final b b) {
        return this.a(s, n, s2, 0.0f, s3, null, b.c);
    }
    
    public boolean b(final String s, final int n, final String s2, final float n2) {
        return this.a(s, n, s2, n2, null, null, this.q);
    }
    
    boolean a(final String s, final int n, final String s2, final float n2, final String s3, final Object o, final t c) {
        final boolean l = c.l;
        Label_0930: {
            if (s.equals("colour") && n < 4) {
                if (s2.equals("a")) {
                    int n3 = (int)n2 >> 1;
                    Label_0059: {
                        if (n3 > 127) {
                            n3 = 127;
                            if (!l) {
                                break Label_0059;
                            }
                        }
                        if (n3 < 0) {
                            n3 = 0;
                        }
                    }
                    this.e[n] = ((this.e[n] & 0xFFFFFF) | n3 << 24);
                    if (!l) {
                        break Label_0930;
                    }
                }
                if (s2.equals("r")) {
                    int n4 = (int)n2;
                    Label_0125: {
                        if (n4 > 255) {
                            n4 = 255;
                            if (!l) {
                                break Label_0125;
                            }
                        }
                        if (n4 < 0) {
                            n4 = 0;
                        }
                    }
                    this.e[n] = ((this.e[n] & 0xFF00FFFF) | n4 << 16);
                    if (!l) {
                        break Label_0930;
                    }
                }
                if (s2.equals("g")) {
                    int n5 = (int)n2;
                    Label_0191: {
                        if (n5 > 255) {
                            n5 = 255;
                            if (!l) {
                                break Label_0191;
                            }
                        }
                        if (n5 < 0) {
                            n5 = 0;
                        }
                    }
                    this.e[n] = ((this.e[n] & 0xFFFF00FF) | n5 << 8);
                    if (!l) {
                        break Label_0930;
                    }
                }
                if (s2.equals("b")) {
                    int n6 = (int)n2;
                    Label_0257: {
                        if (n6 > 255) {
                            n6 = 255;
                            if (!l) {
                                break Label_0257;
                            }
                        }
                        if (n6 < 0) {
                            n6 = 0;
                        }
                    }
                    this.e[n] = ((this.e[n] & 0xFFFFFF00) | n6);
                    if (!l) {
                        break Label_0930;
                    }
                }
                return false;
            }
            Label_0572: {
                if (s.equals("texture") && n < 4) {
                    if (s2.equals("name")) {
                        Label_0542: {
                            if (o != null) {
                                this.c[n] = (b)o;
                                if (this.c[n].v && this.c[n].u() == this.c[n].t()) {
                                    this.c[n].p.c(0);
                                    this.c[n].a(0);
                                }
                                final f f = this.c[n].b.f();
                                int n7 = (f.c - f.a) / 20;
                                if (n7 < 1) {
                                    n7 = 1;
                                }
                                int n8 = (f.d - f.b) / 20;
                                if (n8 < 1) {
                                    n8 = 1;
                                }
                                this.c[n].i = new l(new int[n7 * n8], n7, n8);
                                this.c[n].f = f;
                                if (!l) {
                                    break Label_0542;
                                }
                            }
                            if (s3 != null) {
                                this.c[n] = new b();
                                this.c[n].l = true;
                                this.c[n].m = s3;
                                this.c[n].c = c;
                                if (l) {
                                    return false;
                                }
                            }
                        }
                        if (this.f[n] == null) {
                            break Label_0930;
                        }
                        this.f[n].c = this.j.size();
                        if (l) {
                            break Label_0572;
                        }
                        break Label_0930;
                    }
                    return false;
                }
            }
            if (s.equals("uvg") && n < 4) {
                Label_0791: {
                    if (s2.equals("type")) {
                        try {
                            if (this.f[n] != null) {
                                this.f[n].a();
                            }
                            (this.f[n] = (n)Class.forName(blaze3d.b.gc(s3)).newInstance()).a(this);
                            if (this.i && ((this.f[n].b && (this.g & 1 << n) == 0x0) || (!this.f[n].b && (this.g & 1 << n) != 0x0))) {
                                this.b(n);
                            }
                            Label_0751: {
                                if (this.f[n].b) {
                                    this.g |= 1 << n;
                                    if (!l) {
                                        break Label_0751;
                                    }
                                }
                                this.g &= ~(1 << n);
                            }
                            break Label_0791;
                        }
                        catch (Exception ex) {
                            if (!l) {
                                break Label_0791;
                            }
                        }
                    }
                    if (this.f[n] == null || !this.f[n].a(s2, n2, s3, o)) {
                        return false;
                    }
                }
                this.f[n].c = this.j.size();
                if (!l) {
                    break Label_0930;
                }
            }
            if (s.equals("shd")) {
                if (s3 != null) {}
                if (s2.equals("type")) {
                    try {
                        if (this.b != null) {
                            final boolean d = this.b.d;
                            this.b.d();
                        }
                        this.b = (k)Class.forName(blaze3d.b.gc(s3)).newInstance();
                        this.b.a = s3;
                        this.b.a(this);
                        break Label_0930;
                    }
                    catch (Exception ex2) {
                        if (!l) {
                            break Label_0930;
                        }
                    }
                }
                return this.b.a(s2, n2, s3, o);
            }
            return false;
        }
        this.c();
        return true;
    }
    
    void f() {
        ++this.n;
    }
    
    void g() {
        --this.n;
        if (this.n == 0) {
            this.b();
        }
    }
    
    static {
        j.a = null;
    }
}
