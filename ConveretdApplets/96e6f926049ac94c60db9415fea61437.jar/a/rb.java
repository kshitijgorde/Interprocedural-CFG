// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.kb;
import neat.system.f;
import neat.bb;

public class rb extends bb
{
    private static f d;
    public kb e;
    public boolean f;
    public boolean g;
    public boolean h;
    public int i;
    public boolean j;
    public int k;
    public kb l;
    public kb m;
    public kb n;
    public kb o;
    public kb p;
    public kb q;
    public boolean r;
    public boolean s;
    public boolean t;
    public boolean u;
    public boolean v;
    public boolean w;
    public int x;
    public kb y;
    public kb z;
    public kb A;
    public kb B;
    public kb C;
    public kb D;
    public kb E;
    public kb F;
    public int G;
    private static /* synthetic */ Class H;
    private static String I;
    
    public void b() {
        rb.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)rb.d.a();
    }
    
    public static rb a() {
        return (rb)rb.d.a();
    }
    
    public void g() {
        super.g();
        this.f = true;
        this.g = false;
        this.h = true;
        this.i = 100;
        this.j = true;
        this.k = 100;
        this.r = false;
        this.s = false;
        this.t = true;
        this.u = true;
        this.v = true;
        this.w = true;
        this.x = -1;
        this.G = -1;
    }
    
    public void h() {
        if (this.l != null) {
            this.l.f();
            this.l = null;
        }
        if (this.m != null) {
            this.m.f();
            this.m = null;
        }
        if (this.n != null) {
            this.n.f();
            this.n = null;
        }
        if (this.o != null) {
            this.o.f();
            this.o = null;
        }
        if (this.p != null) {
            this.p.f();
            this.p = null;
        }
        if (this.q != null) {
            this.q.f();
            this.q = null;
        }
        if (this.y != null) {
            this.y.f();
            this.y = null;
        }
        if (this.z != null) {
            this.z.f();
            this.z = null;
        }
        if (this.A != null) {
            this.A.f();
            this.A = null;
        }
        if (this.B != null) {
            this.B.f();
            this.B = null;
        }
        if (this.C != null) {
            this.C.f();
            this.C = null;
        }
        if (this.D != null) {
            this.D.f();
            this.D = null;
        }
        if (this.E != null) {
            this.E.f();
            this.E = null;
        }
        if (this.F != null) {
            this.F.f();
            this.F = null;
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
    
    public rb() {
        this.e = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = null;
        this.E = null;
        this.F = null;
    }
    
    static {
        final char[] charArray = "\u000f\"#p".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0094: {
                if (n > 1) {
                    break Label_0094;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = 'n';
                            break;
                        }
                        case 1: {
                            c2 = '\f';
                            break;
                        }
                        case 2: {
                            c2 = 'Q';
                            break;
                        }
                        case 3: {
                            c2 = '\u0012';
                            break;
                        }
                        default: {
                            c2 = 'i';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                rb.I = new String(charArray).intern();
                rb.d = new f((rb.H != null) ? rb.H : (rb.H = a(rb.I)));
                return;
            }
            continue;
        }
    }
}
