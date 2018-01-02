// 
// Decompiled by Procyon v0.5.30
// 

package ji.secure;

import ji.util.d;
import ji.awt.c;

public class dh
{
    private boolean a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private int n;
    private String o;
    private c p;
    
    public String toString() {
        return String.valueOf(String.valueOf(new StringBuffer("READ=").append(this.a).append(", MODIFY=").append(this.b).append(", MODIFYSECURITY=").append(this.c).append(", EXECUTE=").append(this.d).append(", PRINT=").append(this.e).append(", CREATE=").append(this.f).append(", DELETE=").append(this.g).append(", OWNER=").append(this.o)));
    }
    
    public dh(final boolean a, final boolean b, final boolean c, final boolean d, final boolean e, final boolean f, final boolean g, final String o, final String i, final String j, final String k, final String l, final int n) {
        this.a = false;
        this.b = false;
        this.c = false;
        this.d = false;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = "none";
        this.n = 0;
        this.p = null;
        this.n = n;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.o = o;
        this.i = i;
        this.j = j;
        this.k = k;
        this.l = l;
        this.f = f;
        this.g = g;
    }
    
    public final boolean a(final ei ei) {
        boolean b = false;
        if (this.p == null) {
            this.p = new c("jiItemSecurity1");
        }
        boolean b2 = false;
        for (int i = 0; i < this.p.b(); ++i) {
            final ei ei2 = (ei)this.p.b(i);
            if (ei2.j == ei.j) {
                if (ei2.h != null && ei.h != null) {
                    if (ei2.h.toLowerCase().equals(ei.h.toLowerCase()) && ei2.i == ei.i) {
                        ei2.m = ei.m;
                        ei2.l = ei.l;
                        ei2.k = ei.k;
                        b2 = true;
                        break;
                    }
                }
                else if (ei2.h == null && ei.h == null && ei2.i == ei.i) {
                    b2 = true;
                    break;
                }
            }
        }
        if (!b2) {
            this.p.c(ei);
            b = true;
        }
        return b;
    }
    
    public final boolean b(final ei ei) {
        return this.a(ei, true);
    }
    
    public final boolean c(final ei ei) {
        return this.a(ei, false);
    }
    
    private final boolean a(final ei ei, final boolean b) {
        boolean b2 = false;
        if (this.p == null) {
            this.p = new c("jiItemSecurity1");
        }
        for (int i = 0; i < this.p.b(); ++i) {
            final ei ei2 = (ei)this.p.b(i);
            if (ei2.j == ei.j) {
                if (ei2.h != null && ei.h != null) {
                    if (ei2.h.toLowerCase().equals(ei.h.toLowerCase()) && ei2.i == ei.i) {
                        if (b) {
                            this.p.d(i);
                        }
                        else {
                            ei2.k = 3;
                        }
                        b2 = true;
                        break;
                    }
                }
                else if (ei2.h == null && ei.h == null && ei2.i == ei.i) {
                    if (b) {
                        this.p.d(i);
                    }
                    else {
                        ei2.k = 3;
                    }
                    b2 = true;
                    break;
                }
            }
        }
        return b2;
    }
    
    public void a() {
        if (this.p != null) {
            for (int i = 0; i < this.p.b(); ++i) {
                final ei ei = (ei)this.p.b(i);
            }
        }
    }
    
    public final c a(final int n) {
        final c c = new c("jiItemSecurity2");
        if (this.p != null) {
            for (int i = 0; i < this.p.b(); ++i) {
                final ei ei = (ei)this.p.b(i);
                if (ei.j == n && ei.k != 3) {
                    c.c(ei);
                }
            }
        }
        return c;
    }
    
    public final c b() {
        if (this.p != null) {
            return this.p;
        }
        return new c("jiItemSecurity3");
    }
    
    public final void a(final boolean h) {
        this.h = h;
    }
    
    public static final int b(final int n) {
        switch (n) {
            case 2: {
                return n;
            }
            default: {
                return 1;
            }
        }
    }
    
    public final void c(final int n) {
        if (this.c || this.h) {
            if ((n & 0x1) == 0x1) {
                this.a = true;
            }
            else {
                this.a = false;
            }
            if ((n & 0x2) == 0x2) {
                this.e = true;
            }
            else {
                this.e = false;
            }
        }
    }
    
    public final int c() {
        int n = 0;
        if (this.a) {
            n |= 0x1;
        }
        if (this.e) {
            n |= 0x2;
        }
        return n;
    }
    
    public final void a(final String i) {
        if (this.c || this.h) {
            this.i = i;
        }
    }
    
    public final String d() {
        if (this.i != null) {
            return this.i;
        }
        return "";
    }
    
    public final void b(final String j) {
        if (this.c || this.h) {
            this.j = j;
        }
    }
    
    public final String e() {
        if (this.j != null) {
            return this.j;
        }
        return "";
    }
    
    public final void c(final String k) {
        if (this.c || this.h) {
            this.k = k;
        }
    }
    
    public final String f() {
        if (this.k != null) {
            return this.k;
        }
        return "";
    }
    
    public final void d(final String l) {
        if (this.c || this.h) {
            this.l = l;
        }
    }
    
    public final String g() {
        if (this.l != null) {
            return this.l;
        }
        return "";
    }
    
    public final void b(final boolean a) {
        if (this.c || this.h) {
            this.a = a;
        }
    }
    
    public final boolean h() {
        return this.a;
    }
    
    public final void c(final boolean b) {
        if (this.c || this.h) {
            this.b = b;
        }
    }
    
    public final boolean i() {
        return this.b;
    }
    
    public final void d(final boolean c) {
        if (this.c || this.h) {
            this.c = c;
        }
    }
    
    public final boolean j() {
        return this.c;
    }
    
    public final boolean a(final String s, final int n) {
        if (ji.util.d.bf() && this.n == 0) {
            return false;
        }
        if (this.o != null) {
            return s != null && this.o.toLowerCase().equals(s.toLowerCase());
        }
        return n != 2;
    }
    
    public final void e(final boolean d) {
        if (this.c || this.h) {
            this.d = d;
        }
    }
    
    public final boolean k() {
        return this.d;
    }
    
    public final void f(final boolean e) {
        if (this.c || this.h) {
            this.e = e;
        }
    }
    
    public final boolean l() {
        return this.e;
    }
    
    public final void g(final boolean f) {
        if (this.c || this.h) {
            this.f = f;
        }
    }
    
    public final void h(final boolean g) {
        if (this.c || this.h) {
            this.g = g;
        }
    }
    
    public final boolean m() {
        return this.g;
    }
    
    public final void e(final String o) {
        this.o = o;
    }
    
    public final String n() {
        if (ji.util.d.by(this.o)) {
            return "";
        }
        return this.o;
    }
    
    public final void a(final boolean b, final boolean b2, final boolean b3) {
        final boolean h = this.h;
        try {
            this.b(this.h = true);
            this.f(true);
            this.e(true);
            this.c(b);
            this.d(b2);
            this.h(b3);
            this.a((String)null);
            this.b((String)null);
            this.c((String)null);
            this.d(null);
        }
        finally {
            this.h = h;
        }
    }
    
    public final void f(final String m) {
        this.m = m;
        final boolean h = this.h;
        try {
            this.b(this.h = true);
            this.c(false);
            this.e(true);
            this.f(false);
            this.h(false);
            this.d(false);
            this.a((String)null);
            this.b((String)null);
            this.c((String)null);
            this.d(null);
            if (m != null) {
                if (m.toLowerCase().equals("none".toLowerCase())) {
                    this.c(false);
                    this.f(true);
                    this.h(false);
                    this.d(false);
                }
                else if (m.toLowerCase().equals("change".toLowerCase())) {
                    this.c(true);
                    this.f(true);
                    this.h(true);
                    this.d(true);
                }
                else if (m.toLowerCase().equals("admin".toLowerCase())) {
                    this.c(true);
                    this.f(true);
                    this.h(true);
                    this.d(true);
                }
            }
        }
        finally {
            this.h = h;
        }
    }
    
    public final dh o() {
        final dh dh = new dh(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.o, this.i, this.j, this.k, this.l, this.n);
        if (this.p != null) {
            dh.p = new c("jiItemSecurity4");
            for (int i = 0; i < this.p.b(); ++i) {
                dh.p.c(this.p.b(i));
            }
        }
        return dh;
    }
    
    public String p() {
        final StringBuffer sb = new StringBuffer("");
        sb.append("".concat(String.valueOf(String.valueOf(this.a))));
        sb.append("".concat(String.valueOf(String.valueOf(this.b))));
        sb.append("".concat(String.valueOf(String.valueOf(this.c))));
        sb.append("".concat(String.valueOf(String.valueOf(this.d))));
        sb.append("".concat(String.valueOf(String.valueOf(this.e))));
        sb.append("".concat(String.valueOf(String.valueOf(this.f))));
        sb.append("".concat(String.valueOf(String.valueOf(this.g))));
        sb.append("".concat(String.valueOf(String.valueOf(this.o))));
        sb.append("".concat(String.valueOf(String.valueOf(this.i))));
        sb.append("".concat(String.valueOf(String.valueOf(this.j))));
        sb.append("".concat(String.valueOf(String.valueOf(this.k))));
        sb.append("".concat(String.valueOf(String.valueOf(this.l))));
        sb.append("".concat(String.valueOf(String.valueOf(this.n))));
        if (this.p != null) {
            for (int i = 0; i < this.p.b(); ++i) {
                sb.append("".concat(String.valueOf(String.valueOf(((ei)this.p.b(i)).a()))));
            }
        }
        return sb.toString();
    }
    
    public final dh q() {
        final dh dh = new dh(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.o, this.i, this.j, this.k, this.l, this.n);
        if (this.p != null) {
            dh.p = new c("jiItemSecurity4");
            for (int i = 0; i < this.p.b(); ++i) {
                dh.p.c(((ei)this.p.b(i)).b());
            }
        }
        return dh;
    }
}
