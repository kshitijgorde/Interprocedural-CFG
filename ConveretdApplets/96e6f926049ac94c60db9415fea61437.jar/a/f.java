// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.bb;
import neat.r;
import neat.gb;
import neat.nb;
import neat.system.lb;
import neat.kb;
import neat.i;

public class f extends c implements s
{
    private static neat.system.f k;
    private int l;
    private qb m;
    private int n;
    private i o;
    private boolean p;
    private i q;
    private kb r;
    private int s;
    private static /* synthetic */ Class t;
    private static String[] z;
    
    private void b(final int n) {
        if (n == this.n) {
            return;
        }
        this.n = n;
        if (this.n == 0) {
            return;
        }
        final lb lb = (lb)this.i.a(neat.system.lb.i);
        switch (this.n) {
            case 1: {
                final a.kb kb = (a.kb)this.a();
                if (kb.i == null) {
                    break;
                }
                if (this.l >= kb.i.a()) {
                    this.b(40);
                    return;
                }
                this.m = (qb)kb.i.c(this.l);
                ++this.l;
                this.b(10);
            }
            case 10: {
                if (this.m.e == null) {
                    if (this.m.h) {
                        this.b(20);
                    }
                    else {
                        this.b(30);
                    }
                    return;
                }
                final kb a = nb.a(this.m.e, this.i.m());
                lb.a(a, false);
                a.f();
                break;
            }
            case 20: {
                if (this.m.f == null) {
                    if (this.m.h) {
                        this.b(30);
                    }
                    else {
                        this.b(40);
                    }
                    return;
                }
                if (this.o != null) {
                    this.o.j();
                }
                else {
                    this.o = neat.i.k();
                }
                final gb d = gb.d();
                d.a(this.i.m());
                final r c = this.m.f.c();
                while (c.a()) {
                    final i a2 = d.a((neat.system.gb)this.i.a(neat.system.lb.i), this.i.m(), (kb)c.b());
                    if (a2 != null) {
                        final r f = a2.f();
                        while (f.a()) {
                            this.o.a(f.b());
                        }
                        f.f();
                        a2.f();
                    }
                }
                c.f();
                d.f();
                this.s = 0;
                break;
            }
            case 30: {
                if (this.m.h) {
                    this.b(40);
                }
                else {
                    this.b(20);
                }
                break;
            }
            case 40: {
                this.m = null;
                final a.kb kb2 = (a.kb)this.a();
                if (kb2.i == null) {
                    break;
                }
                if (this.l < kb2.i.a()) {
                    this.b(1);
                    return;
                }
                break;
            }
        }
    }
    
    private void c(final int n) {
        if (this.n == 0) {
            return;
        }
        final lb lb = (lb)this.i.a(neat.system.lb.i);
        switch (this.n) {
            case 10: {
                lb.d();
                if (lb.c()) {
                    throw new RuntimeException(f.z[0]);
                }
                if (!lb.b()) {
                    if (this.m.h) {
                        this.b(20);
                    }
                    else {
                        this.b(30);
                    }
                    return;
                }
                break;
            }
            case 20: {
                if (this.o == null) {
                    if (this.m.h) {
                        this.b(30);
                    }
                    else {
                        this.b(40);
                    }
                    return;
                }
                int g;
                if (this.m.g < 0) {
                    if (--this.s > 0) {
                        break;
                    }
                    this.s = -this.m.g;
                    g = 1;
                }
                else {
                    g = this.m.g;
                }
                for (int i = 0; i < g; ++i) {
                    if (this.o.e()) {
                        if (this.m.h) {
                            this.b(30);
                        }
                        else {
                            this.b(40);
                        }
                        return;
                    }
                    this.i.a((neat.bb)this.o.g(), false);
                }
                break;
            }
            case 30: {
                if (this.m.h) {
                    this.b(40);
                }
                else {
                    this.b(20);
                }
            }
        }
    }
    
    public void a() {
        if (this.n == 0) {
            this.b(1);
        }
    }
    
    public boolean b() {
        return this.n != 0 && this.n != 40;
    }
    
    public void c() {
        ((lb)this.i.a(lb.i)).q();
    }
    
    protected void a(final a.gb gb) {
        super.a(gb);
        if (!(gb instanceof a.kb)) {
            throw new RuntimeException(f.z[1] + gb);
        }
        final a.kb kb = (a.kb)gb;
    }
    
    public void d() {
    }
    
    public void a(final int n) {
        this.c(n);
    }
    
    public static f d() {
        return (f)f.k.a();
    }
    
    public void f() {
        f.k.a(this);
    }
    
    public void g() {
        super.g();
        this.l = 0;
        this.n = 0;
        this.p = false;
    }
    
    public void h() {
        this.m = null;
        if (this.o != null) {
            this.o.j();
            this.o.f();
            this.o = null;
        }
        if (this.q != null) {
            this.q.f();
            this.q = null;
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
    
    public f() {
        this.m = null;
        this.o = null;
        this.q = null;
        this.r = null;
    }
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "r\u0005\u001c\u001b_\u0011\b\u001d]O\u0011\u0003\u0013QN\u0011E".toCharArray();
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
                            c2 = '1';
                            break;
                        }
                        case 1: {
                            c2 = 'd';
                            break;
                        }
                        case 2: {
                            c2 = 'r';
                            break;
                        }
                        case 3: {
                            c2 = '<';
                            break;
                        }
                        default: {
                            c2 = '+';
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
        final char[] charArray2 = "e\f\u001bO\u000bB\f\u0013XDFD\u001dZ\u000bX\u0010\u0017Q\u000b\\\u0011\u0001H\u000bS\u0001R]\u000bc\u0001\u0001S^C\u0007\u0017u_T\t!TJU\u000b\u0005\u001cB\\\u0014\u001eYFT\n\u0006]_X\u000b\u001c\u0006".toCharArray();
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
                            c4 = '1';
                            break;
                        }
                        case 1: {
                            c4 = 'd';
                            break;
                        }
                        case 2: {
                            c4 = 'r';
                            break;
                        }
                        case 3: {
                            c4 = '<';
                            break;
                        }
                        default: {
                            c4 = '+';
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
        final char[] charArray3 = "PJ\u0014".toCharArray();
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
                            c6 = '1';
                            break;
                        }
                        case 1: {
                            c6 = 'd';
                            break;
                        }
                        case 2: {
                            c6 = 'r';
                            break;
                        }
                        case 3: {
                            c6 = '<';
                            break;
                        }
                        default: {
                            c6 = '+';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 <= n12) {
                z[n9] = new String(charArray3).intern();
                f.z = z;
                f.k = new neat.system.f((f.t != null) ? f.t : (f.t = a(f.z[2])));
                return;
            }
            continue;
        }
    }
}
