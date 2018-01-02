// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.system.graphics.renderer.a;
import neat.system.graphics.renderer.b;
import neat.system.graphics.renderer.g;
import neat.system.graphics.renderer.m;
import neat.system.f;
import neat.system.cb;

public class bb implements cb
{
    private static f a;
    private m b;
    private int c;
    private int d;
    private static /* synthetic */ Class e;
    private static String[] z;
    
    void a(final x x, final wb wb) {
        this.a(x);
        (this.b = x.b().b()).a(wb.e, wb.f, wb.g - wb.e, wb.h - wb.f);
        this.c = wb.g - wb.e;
        this.d = wb.h - wb.f;
        this.b.a(false);
    }
    
    void a(final x x) {
        if (this.b != null) {
            x.b().b(this.b);
            this.b = null;
        }
    }
    
    void a(final g g) {
        this.b.a(g);
        if (this.c > 0 && this.d > 0) {
            this.b.b(this.c, this.d);
        }
    }
    
    void a(final boolean b) {
        if (this.b != null) {
            this.b.a(b);
        }
    }
    
    void a(final int n) {
        if (this.b != null) {
            this.b.a(n);
        }
    }
    
    void a(final int n, final int n2) {
        if (this.b != null) {
            this.b.a(n, n2);
        }
    }
    
    void b(final int n, final int n2) {
        if (this.b != null) {
            this.b.a(this.b.c() + n, this.b.d() + n2);
        }
    }
    
    void c(final int c, final int d) {
        if (this.b != null) {
            this.c = c;
            this.d = d;
            if (this.b.j() != null) {
                this.b.b(c, d);
            }
        }
    }
    
    void a(final boolean b, final int n, final float n2, final float n3) {
        if (this.b != null) {
            if (b) {
                this.b.a(n, n2, n3);
            }
            else {
                this.b.a(n2, n3);
            }
        }
    }
    
    void a(final b b) {
        if (this.b != null) {
            this.b.a(b);
        }
    }
    
    a a() {
        final a h = neat.system.graphics.renderer.a.h();
        if (this.b != null) {
            h.a(this.b.c(), this.b.d(), this.b.e(), this.b.f());
        }
        return h;
    }
    
    void a(final g g, final int n, final int n2, final int n3, final int n4) {
        if (this.b == null) {
            return;
        }
        final int n5 = 0;
        final int n6 = 0;
        final int n7 = n5 + n;
        final int n8 = n6 + n2;
        int d = this.b.m().d();
        int e = this.b.m().e();
        int n9 = d + this.b.m().f();
        int n10 = e + this.b.m().g();
        int n11 = this.b.c() - n3;
        int n12 = this.b.d() - n4;
        int n13 = n11 + this.b.e();
        int n14 = n12 + this.b.f();
        if (n13 - n11 != n9 - d || n14 - n12 != n10 - e) {
            throw new RuntimeException(bb.z[0]);
        }
        if (n11 >= n7 || n13 <= n5 || n12 >= n8 || n14 <= n6) {
            return;
        }
        if (n11 < n5) {
            d += n5 - n11;
            n11 = n5;
        }
        if (n12 < n6) {
            e += n6 - n12;
            n12 = n6;
        }
        if (n13 > n7) {
            n9 += n7 - n13;
            n13 = n7;
        }
        if (n14 > n8) {
            n10 += n8 - n14;
            n14 = n8;
        }
        if (n11 >= n13 || n12 >= n14 || d >= n9 || e >= n10) {
            return;
        }
        g.a(this.b.j(), n11, n12, n13, n14, d, e, n9, n10);
    }
    
    public static bb b() {
        return (bb)bb.a.a();
    }
    
    public void f() {
        bb.a.a(this);
    }
    
    public void g() {
        this.c = 0;
        this.d = 0;
    }
    
    public void h() {
        this.b = null;
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public bb() {
        this.b = null;
    }
    
    static {
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "84`\u0018<[3b^<\u000f0`\u001f$\u001e!zZ:[\"gK [/aP%[t".toCharArray();
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
                            c2 = '{';
                            break;
                        }
                        case 1: {
                            c2 = 'U';
                            break;
                        }
                        case 2: {
                            c2 = '\u000e';
                            break;
                        }
                        case 3: {
                            c2 = '?';
                            break;
                        }
                        default: {
                            c2 = 'H';
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
        final char[] charArray2 = "\u001a{l]".toCharArray();
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
                            c4 = '{';
                            break;
                        }
                        case 1: {
                            c4 = 'U';
                            break;
                        }
                        case 2: {
                            c4 = '\u000e';
                            break;
                        }
                        case 3: {
                            c4 = '?';
                            break;
                        }
                        default: {
                            c4 = 'H';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 <= n8) {
                z[n5] = new String(charArray2).intern();
                bb.z = z;
                bb.a = new f((bb.e != null) ? bb.e : (bb.e = a(bb.z[1])));
                return;
            }
            continue;
        }
    }
}
