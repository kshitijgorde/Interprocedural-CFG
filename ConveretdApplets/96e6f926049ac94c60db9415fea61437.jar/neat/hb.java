// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.f;
import neat.system.cb;

public class hb implements cb
{
    private static f a;
    private static final kb MEMBER_CLASS_CHILD;
    private gb b;
    kb c;
    kb d;
    p e;
    int f;
    int g;
    int h;
    h i;
    h j;
    int k;
    private static /* synthetic */ Class l;
    private static String[] z;
    
    public void a() {
        this.i.c();
        this.e.d();
        this.f = 0;
        this.g = -1;
        this.h = -1;
    }
    
    public void a(final kb kb, final kb kb2) {
        if (kb == null) {
            throw new RuntimeException(hb.z[0]);
        }
        if (kb2 == null) {
            throw new RuntimeException(hb.z[1]);
        }
        if (this.d != null) {
            if (this.d.equals(kb)) {
                return;
            }
            this.d.f();
        }
        this.d = kb.b();
        if (this.c != null) {
            this.c.f();
        }
        this.c = kb2.b();
        this.a();
        int d = -1;
        int f = -1;
        int i = 0;
        final int d2 = kb2.d();
    Label_0165_Outer:
        while (i < d2) {
            boolean b = false;
            while (true) {
                Label_0162: {
                    break Label_0162;
                    char b2 = '\0';
                    do {
                        if (Character.isWhitespace(b2)) {
                            if (++i < d2) {
                                b2 = kb2.b(i);
                                continue Label_0165_Outer;
                            }
                        }
                        if (b) {
                            while (true) {
                                Label_0211: {
                                    break Label_0211;
                                    char b3;
                                    do {
                                        if (++i >= d2) {
                                            continue Label_0165_Outer;
                                        }
                                        b3 = kb2.b(i);
                                    } while (b3 != '\n' && b3 != '\r');
                                }
                                continue;
                            }
                        }
                        if (i >= d2) {
                            break Label_0165_Outer;
                        }
                        boolean b4 = false;
                        int n = 0;
                        do {
                            final char b5 = kb2.b(i);
                            if (b5 == '\"') {
                                n ^= 0x1;
                            }
                            else if (n == 0) {
                                if (b5 == '[') {
                                    f = this.f;
                                    this.e.f(8);
                                    this.f += 8;
                                    this.e.a(f, i + 1);
                                    this.e.a(f + 5, -1);
                                    this.e.a(f + 6, -1);
                                    int n2;
                                    if (d != -1) {
                                        final int d3 = this.e.d(d + 5);
                                        n2 = this.e.d(d + 6);
                                        if (d3 == -1) {
                                            this.e.a(d + 5, f);
                                        }
                                        this.e.a(d + 6, f);
                                    }
                                    else {
                                        final int g = this.g;
                                        n2 = this.h;
                                        if (g == -1) {
                                            this.g = f;
                                        }
                                        this.h = f;
                                    }
                                    if (n2 != -1) {
                                        this.e.a(n2 + 7, f);
                                    }
                                    this.e.a(f + 7, -1);
                                    this.e.a(f + 4, d);
                                    break;
                                }
                                if (b5 == ']') {
                                    if (d == -1) {
                                        throw this.c(i);
                                    }
                                    this.e.a(d + 3, i - 1);
                                    d = this.e.d(d + 4);
                                    b4 = true;
                                    break;
                                }
                                else if (b5 == '=') {
                                    throw this.c(i);
                                }
                            }
                        } while (++i < d2);
                        if (b4) {
                            if (++i >= d2) {
                                break Label_0165_Outer;
                            }
                            continue Label_0165_Outer;
                        }
                        else {
                            if (i >= d2) {
                                break Label_0165_Outer;
                            }
                            while (true) {
                                while (++i < d2) {
                                    final char b6 = kb2.b(i);
                                    if (b6 == '=') {
                                        this.e.a(f + 1, i - 1);
                                        this.e.a(f + 2, i + 1);
                                        d = f;
                                        if (++i >= d2) {
                                            throw this.c(i - 1);
                                        }
                                        continue Label_0165_Outer;
                                    }
                                    else {
                                        if (b6 == '[' || b6 == ']') {
                                            throw this.c(i);
                                        }
                                        continue Label_0165_Outer;
                                    }
                                }
                                continue;
                            }
                        }
                    } while (b2 != ';');
                }
                b = true;
                continue;
            }
        }
        Label_1267: {
            if (this.f > 0) {
                int n3 = this.g;
            Label_1264:
                while (true) {
                    int j = this.e.d(n3);
                    int d4 = this.e.d(n3 + 1);
                    boolean b7 = false;
                    while (j <= d4) {
                        final char b8 = kb2.b(j);
                        if (!Character.isWhitespace(b8)) {
                            if (b8 == '\"') {
                                if (++j >= d4) {
                                    throw this.c(d4);
                                }
                                b7 = true;
                            }
                            Label_0926: {
                                if (b7) {
                                    while (j <= d4) {
                                        if (kb2.b(d4) == '\"') {
                                            if (j > --d4) {
                                                throw this.c(d4);
                                            }
                                            break Label_0926;
                                        }
                                        else {
                                            --d4;
                                        }
                                    }
                                    throw this.c(d4);
                                }
                                while (j <= d4) {
                                    if (!Character.isWhitespace(kb2.b(d4))) {
                                        break Label_0926;
                                    }
                                    --d4;
                                }
                                throw this.c(d4);
                            }
                            this.e.a(n3, j);
                            this.e.a(n3 + 1, d4 + 1);
                            final int d5 = this.e.d(n3 + 5);
                            if (d5 != -1) {
                                n3 = d5;
                                continue Label_1264;
                            }
                            int k = this.e.d(n3 + 2);
                            int d6 = this.e.d(n3 + 3);
                            boolean b9 = false;
                            while (true) {
                                while (k < d6) {
                                    final char b10 = kb2.b(k);
                                    if (!Character.isWhitespace(b10)) {
                                        if (b10 == '\"') {
                                            if (++k > d6) {
                                                throw this.c(k);
                                            }
                                            b9 = true;
                                        }
                                        Label_1172: {
                                            if (b9) {
                                                while (k <= d6) {
                                                    if (kb2.b(d6) == '\"') {
                                                        if (k >= --d6) {}
                                                        break Label_1172;
                                                    }
                                                    --d6;
                                                }
                                                throw this.c(d6);
                                            }
                                            while (k < d6) {
                                                if (!Character.isWhitespace(kb2.b(d6))) {
                                                    break;
                                                }
                                                --d6;
                                            }
                                        }
                                        this.e.a(n3 + 2, k);
                                        this.e.a(n3 + 3, d6 + 1);
                                        while (true) {
                                            int l = 0;
                                            Label_1252: {
                                                break Label_1252;
                                                do {
                                                    n3 = this.e.d(n3 + 4);
                                                    if (n3 == -1) {
                                                        if (n3 == -1) {
                                                            break Label_1267;
                                                        }
                                                        continue Label_1264;
                                                    }
                                                    else {
                                                        l = this.e.d(n3 + 7);
                                                    }
                                                } while (l == -1);
                                            }
                                            n3 = l;
                                            continue;
                                        }
                                    }
                                    ++k;
                                }
                                continue;
                            }
                        }
                        else {
                            ++j;
                        }
                    }
                    throw this.c(j);
                }
            }
        }
        if (this.f > 0) {
            int n4 = this.g;
            do {
                final kb a = this.a(n4);
                final q q = (q)this.i.g(a);
                if (q == null) {
                    this.i.a(a, neat.q.b(n4));
                }
                else {
                    q.a(n4);
                    a.f();
                }
                n4 = this.e.d(n4 + 7);
            } while (n4 != -1);
        }
        final q q2 = (q)this.i.g(neat.bb.MEMBER_DEFAULT);
        if (q2 == null) {
            this.k = -1;
        }
        else {
            this.k = q2.a();
        }
    }
    
    int a(final int n, final kb kb) {
        if (n >= this.f) {
            return -1;
        }
        for (int i = this.e.d(n + 5); i != -1; i = this.e.d(i + 7)) {
            final kb a = this.a(i);
            final boolean equals = a.equals(kb);
            a.f();
            if (equals) {
                return i;
            }
        }
        return -1;
    }
    
    kb a(final int n) {
        if (n < 0 || n >= this.f) {
            return null;
        }
        return this.c.c(this.e.d(n), this.e.d(n + 1));
    }
    
    kb b(final int n) {
        if (n < 0 || n >= this.f) {
            return null;
        }
        if (this.e.d(n + 5) != -1) {
            return null;
        }
        return this.c.c(this.e.d(n + 2), this.e.d(n + 3));
    }
    
    RuntimeException a(final String s, int n) {
        if (this.c == null) {
            return new RuntimeException(hb.z[1]);
        }
        int n2 = 0;
        int b = 0;
        final int d = this.c.d();
        if (n >= d) {
            n = d - 1;
        }
        int n3 = 0;
        while (true) {
            Label_0115: {
                break Label_0115;
                do {
                    ++n2;
                    if (++b >= d) {
                        final lb a = lb.a();
                        a.c(s + hb.z[3] + n2 + hb.z[4]);
                        a.a(this.c.c(n3, b));
                        a.a(neat.bb.NEWLINE);
                        for (int i = n - n3; i > 0; --i) {
                            a.a(' ');
                        }
                        a.a('^');
                        return new RuntimeException(a.b().toString());
                    }
                    n3 = b;
                    b = this.c.b(neat.bb.NEWLINE, b);
                } while (b >= 0 && b < n);
            }
            if (b < 0) {
                b = this.c.d() - 1;
            }
            continue;
        }
    }
    
    RuntimeException b(final String s, final int n) {
        return this.a(s, this.e.d(n));
    }
    
    RuntimeException c(final int n) {
        return this.a(hb.z[2], n);
    }
    
    public static hb a(final gb b) {
        final hb hb = (hb)neat.hb.a.a();
        hb.b = b;
        return hb;
    }
    
    public void f() {
        hb.a.a(this);
    }
    
    public void g() {
        this.b = null;
        this.e = p.h(25);
        this.f = 0;
        this.g = -1;
        this.i = neat.h.e();
        this.j = neat.h.e();
        this.k = -1;
    }
    
    public void h() {
        this.a();
        if (this.c != null) {
            this.c.f();
            this.c = null;
        }
        if (this.d != null) {
            this.d.f();
            this.d = null;
        }
        this.e.f();
        this.e = null;
        this.i.f();
        this.i = null;
        this.j.c();
        this.j.f();
        this.j = null;
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
    
    public hb() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.i = null;
        this.j = null;
    }
    
    static {
        final String[] z = new String[6];
        final int n = 0;
        final char[] charArray = "cQ\u0016\u001a]XL\u0001YK\u0017_\u001aVK\u0017W\u0012WK\u0017P\u0000\u001a@BU\u001f\u001a\u000f".toCharArray();
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
                            c2 = '9';
                            break;
                        }
                        case 2: {
                            c2 = 's';
                            break;
                        }
                        case 3: {
                            c2 = ':';
                            break;
                        }
                        default: {
                            c2 = '.';
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
        final char[] charArray2 = "cQ\u0016\u001a]XL\u0001YK\u0017P\u0000\u001a@BU\u001f\u001a\u000f".toCharArray();
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
                            c4 = '9';
                            break;
                        }
                        case 2: {
                            c4 = 's';
                            break;
                        }
                        case 3: {
                            c4 = ':';
                            break;
                        }
                        default: {
                            c4 = '.';
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
        final char[] charArray3 = "d@\u001dNOO\u0019\u0016H\\XKS\u001b".toCharArray();
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
                            c6 = '7';
                            break;
                        }
                        case 1: {
                            c6 = '9';
                            break;
                        }
                        case 2: {
                            c6 = 's';
                            break;
                        }
                        case 3: {
                            c6 = ':';
                            break;
                        }
                        default: {
                            c6 = '.';
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
        final char[] charArray4 = "\u0017\u0011\u0012N\u000e[P\u001d_\u0014".toCharArray();
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
                            c8 = '7';
                            break;
                        }
                        case 1: {
                            c8 = '9';
                            break;
                        }
                        case 2: {
                            c8 = 's';
                            break;
                        }
                        case 3: {
                            c8 = ':';
                            break;
                        }
                        default: {
                            c8 = '.';
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
        final char[] charArray5 = "\u001e3".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0562: {
                if (n18 > 1) {
                    break Label_0562;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = '7';
                            break;
                        }
                        case 1: {
                            c10 = '9';
                            break;
                        }
                        case 2: {
                            c10 = 's';
                            break;
                        }
                        case 3: {
                            c10 = ':';
                            break;
                        }
                        default: {
                            c10 = '.';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        z[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "Y\\\u0012N\u0000_[".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0678: {
                if (n22 > 1) {
                    break Label_0678;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = '7';
                            break;
                        }
                        case 1: {
                            c12 = '9';
                            break;
                        }
                        case 2: {
                            c12 = 's';
                            break;
                        }
                        case 3: {
                            c12 = ':';
                            break;
                        }
                        default: {
                            c12 = '.';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 <= n24) {
                z[n21] = new String(charArray6).intern();
                hb.z = z;
                hb.a = new f((hb.l != null) ? hb.l : (hb.l = a(hb.z[5])));
                MEMBER_CLASS_CHILD = neat.bb.MEMBER_CLASS_CHILD;
                return;
            }
            continue;
        }
    }
}
