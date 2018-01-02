// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.nb;
import a.y;
import neat.kb;
import neat.system.graphics.renderer.m;
import neat.system.f;
import neat.system.cb;

public class o implements cb
{
    private static f a;
    public m b;
    public boolean c;
    public boolean d;
    public kb e;
    public int f;
    public kb g;
    public int h;
    public int i;
    public int j;
    public int k;
    public boolean l;
    private static /* synthetic */ Class m;
    private static String z;
    
    public void a(final a.y y, int n) {
        if (this.b == null) {
            return;
        }
        if (this.e == null) {
            return;
        }
        if (this.e.d() == 0) {
            return;
        }
        final int p2 = this.b.p();
        if (p2 <= 0) {
            return;
        }
        int q = this.b.q();
        while (true) {
            if (this.f >= this.e.d()) {
                if (this.k > 0) {
                    --this.k;
                }
                if (this.g != null && this.k <= 0) {
                    this.e.f();
                    this.e = this.g;
                    this.g = null;
                    this.f = this.h;
                    this.j = 0;
                    this.i = 0;
                    continue;
                }
                this.f = 0;
            }
            if (this.f == 0) {
                this.l = true;
            }
            if (this.e.b(this.f) == '!') {
                if (++this.f >= this.e.d()) {
                    continue;
                }
                final char b = this.e.b(this.f++);
                if (b == 'r' || b == 'l') {
                    if (this.f >= this.e.d()) {
                        continue;
                    }
                    final int a = this.e.a(91, this.f);
                    if (a < 0) {
                        continue;
                    }
                    final int a2 = this.e.a(93, a);
                    if (a2 < 0) {
                        continue;
                    }
                    final int a3 = this.e.a(44, a2);
                    final kb c = this.e.c(this.f, a);
                    final int b2 = nb.b(c, -1);
                    c.f();
                    if (a3 < 0) {
                        this.f = 0;
                    }
                    else {
                        this.f = a3 + 1;
                    }
                    if (b2 < 0) {
                        continue;
                    }
                    if (b == 'r') {
                        if (y.d(b2) != 0) {
                            continue;
                        }
                    }
                    else if (b == 'l') {
                        this.k = b2;
                    }
                    if (this.g != null) {
                        continue;
                    }
                    this.g = this.e;
                    this.h = this.f;
                    this.e = this.g.c(a + 1, a2);
                    this.f = 0;
                    this.j = 0;
                    this.i = 0;
                    this.l = true;
                }
                else {
                    if (b != 'v') {
                        continue;
                    }
                    final int a4 = this.e.a(44, this.f);
                    if (a4 < 0) {
                        this.f = 0;
                    }
                    else {
                        this.f = a4 + 1;
                    }
                    this.l ^= true;
                }
            }
            else {
                int a5 = this.e.a(58, this.f);
                if (a5 < 0 || a5 >= this.e.d() - 1) {
                    continue;
                }
                final kb c2 = this.e.c(this.f, a5);
                int b3 = nb.b(c2, -1);
                c2.f();
                boolean b4 = false;
                if (b3 < 0) {
                    b4 = true;
                    b3 = -b3;
                }
                ++a5;
                int n2 = this.e.a(44, a5);
                if (n2 < 0) {
                    n2 = this.e.d();
                }
                final kb c3 = this.e.c(a5, n2);
                final int b5 = nb.b(c3, -1000);
                c3.f();
                if (b5 == -1000) {
                    return;
                }
                if (b3 == 0) {
                    q = 0;
                    this.j = 0;
                    this.i = -y.d(b5);
                }
                else if (b5 == 0) {
                    if (b4) {
                        q -= b3;
                    }
                    else {
                        q += b3;
                    }
                    this.j += b3;
                }
                else if (b5 > 0 && n > 0) {
                    this.i += n;
                    n = 0;
                    if (this.i >= b5) {
                        final int n3 = this.i / b5;
                        this.i %= b5;
                        if (b4) {
                            q -= n3;
                        }
                        else {
                            q += n3;
                        }
                        this.j += n3;
                    }
                }
                if (this.j < b3) {
                    int n4 = q % p2;
                    if (n4 < 0) {
                        n4 += p2 - 1;
                    }
                    this.b.b(n4);
                    this.a();
                    return;
                }
                this.j -= b3;
                this.f = n2 + 1;
            }
        }
    }
    
    public void a() {
        if (this.b != null) {
            this.b.a((this.d || this.c) && this.l);
        }
    }
    
    public static o b() {
        return (o)o.a.a();
    }
    
    public void f() {
        o.a.a(this);
    }
    
    public void g() {
        this.d = false;
        this.c = false;
        this.f = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
        this.l = true;
    }
    
    public void h() {
        this.b = null;
        if (this.e != null) {
            this.e.f();
            this.e = null;
        }
        if (this.g != null) {
            this.g.f();
            this.g = null;
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
    
    public o() {
        this.b = null;
        this.e = null;
        this.g = null;
    }
    
    static {
        final char[] charArray = "^\u0006\u0018[p\u0012\u0000".toCharArray();
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
                            c2 = '<';
                            break;
                        }
                        case 1: {
                            c2 = 'o';
                            break;
                        }
                        case 2: {
                            c2 = 'v';
                            break;
                        }
                        case 3: {
                            c2 = '<';
                            break;
                        }
                        default: {
                            c2 = '\u001f';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                o.z = new String(charArray).intern();
                o.a = new f((o.m != null) ? o.m : (o.m = a(o.z)));
                return;
            }
            continue;
        }
    }
}
