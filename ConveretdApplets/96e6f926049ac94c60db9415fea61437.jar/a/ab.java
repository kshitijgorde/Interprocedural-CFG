// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.nb;
import neat.lb;
import neat.q;
import neat.r;
import neat.kb;
import neat.i;
import neat.h;
import neat.j;
import neat.system.cb;

public abstract class ab implements cb
{
    private j a;
    private h b;
    private yb c;
    private h d;
    private i e;
    private static String[] z;
    
    public void a(final yb c) {
        this.a();
        this.c = c;
        if (c.commands == null) {
            throw new RuntimeException(ab.z[1] + c);
        }
        for (int a = c.commands.a(), i = 0; i < a; ++i) {
            final kb a2 = c.commands.a(i);
            final Object b = c.commands.b(i);
            if (b instanceof kb) {
                this.a.a(a2.b(), ((kb)b).b());
            }
            else {
                if (!(b instanceof fc)) {
                    throw new RuntimeException(ab.z[0] + b);
                }
                final fc fc = (fc)b;
                final h e = h.e();
                final r b2 = fc.b();
                while (b2.a()) {
                    final kb kb = (kb)b2.b();
                    e.a(kb, fc.d(kb));
                }
                b2.f();
                this.a.a(a2.b(), e);
            }
        }
        if (c.e != null) {
            final r b3 = c.e.b();
            while (b3.a()) {
                final kb kb2 = (kb)b3.b();
                this.b.a(kb2.b(), c.e.d(kb2).b());
            }
            b3.f();
        }
        this.b();
    }
    
    public void a() {
        this.c();
        this.a.d();
        this.b.c();
        if (this.c != null) {
            this.c.f();
            this.c = null;
        }
    }
    
    protected void b() {
        for (int c = this.a.c(), i = 0; i < c; ++i) {
            if (((kb)this.a.a(i)).equals(ec.COMMAND__LABEL)) {
                this.d.a(((kb)this.a.b(i)).b(), q.b(i));
            }
        }
        this.e.a(q.b(0));
    }
    
    protected void c() {
        this.d.c();
        this.e.j();
    }
    
    public boolean a(final kb kb) {
        return this.d.f(kb);
    }
    
    public void b(final kb kb) {
        final q q = (q)this.d.g(kb);
        if (q == null) {
            return;
        }
        this.a(q.a());
    }
    
    public void d() {
        this.a(0);
    }
    
    private void a(final int n) {
        final q q = (q)this.e.d();
        if (q == null) {
            return;
        }
        q.a(n);
        this.j();
    }
    
    public void c(final kb kb) {
        final q q = (q)this.d.g(kb);
        if (q == null) {
            return;
        }
        this.b(q.a());
    }
    
    private void b(final int n) {
        this.e.a(q.b(n));
        this.j();
    }
    
    protected void e() {
        this.e.h();
        this.j();
    }
    
    public void i() {
        this.e.j();
        this.j();
    }
    
    public boolean a(final lb lb) {
        final q q = (q)this.e.d();
        if (q == null) {
            return false;
        }
        final int a = q.a();
        if (a < 0 || a >= this.a.c()) {
            return false;
        }
        q.a(a + 1);
        final kb kb = (kb)this.a.a(a);
        final Object b = this.a.b(a);
        boolean b2 = false;
        if (b instanceof kb) {
            b2 = this.a(kb, (kb)b, lb);
        }
        else if (b instanceof h) {
            b2 = this.a(kb, (h)b, lb);
        }
        if (!b2) {
            q.a(a);
        }
        return true;
    }
    
    protected boolean a(final kb kb, final kb kb2, final lb lb) {
        if (kb.equals(ec.COMMAND__T)) {
            lb.a(kb2);
        }
        else if (kb.equals(ec.COMMAND__GET)) {
            final kb kb3 = (kb)this.b.g(kb2);
            if (kb3 != null) {
                lb.a(kb3);
            }
        }
        else if (!kb.equals(ec.COMMAND__LABEL)) {
            if (kb.equals(ec.COMMAND__GO)) {
                this.b(kb2);
            }
            else if (kb.equals(ec.COMMAND__CALL)) {
                this.c(kb2);
            }
            else if (kb.equals(ec.COMMAND__CALL_RETURN)) {
                this.e();
            }
            else {
                if (!kb.equals(ec.COMMAND__END)) {
                    throw new RuntimeException(ab.z[3] + kb + ab.z[2] + kb2);
                }
                this.i();
            }
        }
        return true;
    }
    
    protected boolean a(final kb kb, final h h, final lb lb) {
        if (kb.equals(ec.COMMAND__SET)) {
            final kb kb2 = (kb)h.g(ec.COMMAND__SET__VARIABLE);
            if (kb2 == null) {
                return true;
            }
            kb d = (kb)h.g(ec.COMMAND__SET__VALUE);
            if (d == null) {
                final kb kb3 = (kb)h.g(ec.COMMAND__SET__OTHER_VARIABLE);
                if (kb3 == null) {
                    return true;
                }
                d = this.d(kb3);
                if (d == null) {
                    return true;
                }
            }
            this.a(kb2, d);
        }
        else if (kb.equals(ec.COMMAND__ADD)) {
            final kb kb4 = (kb)h.g(ec.COMMAND__ADD__VARIABLE);
            if (kb4 == null) {
                return true;
            }
            kb d2 = (kb)h.g(ec.COMMAND__ADD__VALUE);
            if (d2 == null) {
                final kb kb5 = (kb)h.g(ec.COMMAND__ADD__OTHER_VARIABLE);
                if (kb5 == null) {
                    return true;
                }
                d2 = this.d(kb5);
                if (d2 == null) {
                    return true;
                }
            }
            final lb a = lb.a();
            final kb d3 = this.d(kb4);
            if (d3 != null) {
                a.a(d3);
            }
            a.a(d2);
            final kb b = a.b();
            this.a(kb4, b);
            b.f();
        }
        else if (kb.equals(ec.COMMAND__COUNT)) {
            final kb kb6 = (kb)h.g(ec.COMMAND__COUNT__VARIABLE);
            if (kb6 == null) {
                return true;
            }
            kb d4 = (kb)h.g(ec.COMMAND__COUNT__VALUE);
            if (d4 == null) {
                final kb kb7 = (kb)h.g(ec.COMMAND__COUNT__OTHER_VARIABLE);
                if (kb7 == null) {
                    return true;
                }
                d4 = this.d(kb7);
                if (d4 == null) {
                    return true;
                }
            }
            final kb kb8 = (kb)h.g(ec.COMMAND__COUNT__OPERATION);
            if (kb8 == null) {
                return true;
            }
            if (kb8.d() <= 0) {
                return true;
            }
            final kb d5 = this.d(kb6);
            final int b2 = nb.b(d4, 0);
            final int b3 = nb.b(d5, 0);
            final char b4 = kb8.b(0);
            int n;
            if (b4 == '+') {
                n = b3 + b2;
            }
            else if (b4 == '-') {
                n = b3 - b2;
            }
            else if (b4 == '*') {
                n = b3 * b2;
            }
            else if (b4 == '/' && b2 != 0) {
                n = b3 / b2;
            }
            else if (b4 == '%' && b2 != 0) {
                n = b3 % b2;
            }
            else if (b4 == '|') {
                n = (b3 | b2);
            }
            else if (b4 == '&') {
                n = (b3 & b2);
            }
            else if (b4 == '^') {
                n = (b3 ^ b2);
            }
            else {
                n = b3;
            }
            final kb a2 = nb.a(n);
            this.a(kb6, a2);
            a2.f();
        }
        else {
            if (!kb.equals(ec.COMMAND__IF)) {
                throw new RuntimeException(ab.z[3] + kb + ab.z[4] + h);
            }
            kb kb9 = null;
            final kb kb10 = (kb)h.g(ec.COMMAND__IF__VARIABLE);
            if (kb10 != null) {
                kb9 = (kb)this.b.g(kb10);
            }
            if (kb9 == null) {
                return true;
            }
            final kb kb11 = (kb)h.g(ec.COMMAND__IF__GO);
            if (kb11 == null) {
                return true;
            }
            final kb kb12 = (kb)h.g(ec.COMMAND__IF__EQUALS);
            if (kb12 != null) {
                if (kb12.equals(kb9)) {
                    this.b(kb11);
                }
                return true;
            }
            final kb kb13 = (kb)h.g(ec.COMMAND__IF__FIT);
            if (kb13 != null) {
                if (kb9.d() <= nb.c(kb13)) {
                    this.b(kb11);
                }
                return true;
            }
        }
        return true;
    }
    
    protected void j() {
    }
    
    public void a(final kb kb, final kb kb2) {
        this.b.c(kb);
        this.b.a(kb.b(), kb2.b());
    }
    
    public kb d(final kb kb) {
        return (kb)this.b.g(kb);
    }
    
    public void g() {
        this.a = j.e();
        this.b = h.e();
        this.d = h.e();
        this.e = i.k();
    }
    
    public void h() {
        this.a();
        this.a.f();
        this.a = null;
        this.b.f();
        this.b = null;
        this.d.f();
        this.d = null;
        this.e.f();
        this.e = null;
    }
    
    public abstract void f();
    
    public ab() {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
    }
    
    static {
        final String[] z = new String[5];
        final int n = 0;
        final char[] charArray = "J\tu(YhGn'D~\n{2SmGj?Fz\u0014> YmG})[r\u0006p\"\f".toCharArray();
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
                            c2 = '\u001f';
                            break;
                        }
                        case 1: {
                            c2 = 'g';
                            break;
                        }
                        case 2: {
                            c2 = '\u001e';
                            break;
                        }
                        case 3: {
                            c2 = 'F';
                            break;
                        }
                        default: {
                            c2 = '6';
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
        final char[] charArray2 = "K\u000f{4S?\u0006l#\u0016q\b>%Yr\n\u007f(RlGw(\u0016V\tJ#NkGm.W{\bi|".toCharArray();
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
                            c4 = '\u001f';
                            break;
                        }
                        case 1: {
                            c4 = 'g';
                            break;
                        }
                        case 2: {
                            c4 = '\u001e';
                            break;
                        }
                        case 3: {
                            c4 = 'F';
                            break;
                        }
                        default: {
                            c4 = '6';
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
        final char[] charArray3 = "3Gn'D~\n{2Sm]".toCharArray();
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
                            c6 = '\u001f';
                            break;
                        }
                        case 1: {
                            c6 = 'g';
                            break;
                        }
                        case 2: {
                            c6 = '\u001e';
                            break;
                        }
                        case 3: {
                            c6 = 'F';
                            break;
                        }
                        default: {
                            c6 = '6';
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
        z[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "J\tu(YhG})[r\u0006p\"\f".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0446: {
                if (n14 > 1) {
                    break Label_0446;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = '\u001f';
                            break;
                        }
                        case 1: {
                            c8 = 'g';
                            break;
                        }
                        case 2: {
                            c8 = '\u001e';
                            break;
                        }
                        case 3: {
                            c8 = 'F';
                            break;
                        }
                        default: {
                            c8 = '6';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        z[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "3Gn'D~\n{2Sm\u0014$".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0566: {
                if (n18 > 1) {
                    break Label_0566;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = '\u001f';
                            break;
                        }
                        case 1: {
                            c10 = 'g';
                            break;
                        }
                        case 2: {
                            c10 = '\u001e';
                            break;
                        }
                        case 3: {
                            c10 = 'F';
                            break;
                        }
                        default: {
                            c10 = '6';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 <= n20) {
                z[n17] = new String(charArray5).intern();
                ab.z = z;
                return;
            }
            continue;
        }
    }
}
