// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

import neat.r;
import neat.h;

class jb implements cb
{
    private static f a;
    private h b;
    private static /* synthetic */ Class c;
    private static String[] z;
    
    void a(final kb kb, final Object o) {
        if (this.b.e(o)) {
            throw new IllegalArgumentException(jb.z[0] + kb + jb.z[2] + o + jb.z[1]);
        }
        if (this.b.f(o)) {
            throw new IllegalArgumentException(jb.z[3] + o + jb.z[1]);
        }
        this.b.a(o, kb);
    }
    
    void a(final Object o) {
        if (this.b.a(o) == null) {
            throw new IllegalArgumentException(jb.z[4] + o + jb.z[1]);
        }
        if (this.b.k()) {
            this.f();
        }
    }
    
    kb b(final Object o) {
        final kb kb = (kb)this.b.g(o);
        if (kb == null) {
            throw new IllegalArgumentException(jb.z[4] + o + jb.z[1]);
        }
        return kb;
    }
    
    void a() {
        final r a = this.b.a();
        while (a.a()) {
            this.b(a.b()).e();
        }
        a.f();
    }
    
    void b() {
        while (true) {
            int c = 0;
            final r a = this.b.a();
            while (a.a()) {
                final kb b = this.b(a.b());
                if (b.c() > c) {
                    c = b.c();
                }
            }
            a.f();
            final r a2 = this.b.a();
            while (a2.a()) {
                final kb b2 = this.b(a2.b());
                if (b2.c() >= c || c == 0) {
                    b2.f();
                    if (!a2.c()) {
                        continue;
                    }
                    a2.e();
                }
            }
            a2.f();
            if (c == 0 || this.b.j() == 0) {
                break;
            }
        }
        this.b.c();
    }
    
    public static jb c() {
        return (jb)jb.a.a();
    }
    
    public void f() {
        jb.a.a(this);
    }
    
    public void g() {
        this.b = h.e();
    }
    
    public void h() {
        this.b();
        this.b.m();
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    jb() {
        this.b = null;
    }
    
    static {
        final String[] z = new String[6];
        final int n = 0;
        final char[] charArray = "h\u0001\u0001K\u0006S\u000b\u0002]EHI\u0001K\u0006]\u0005\u001a]GX\u0010HYBX\f\f\u0018RSI\u001cPC\u001c;\u0007WR\u0006I".toCharArray();
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
                            c2 = '<';
                            break;
                        }
                        case 1: {
                            c2 = 'i';
                            break;
                        }
                        case 2: {
                            c2 = 'h';
                            break;
                        }
                        case 3: {
                            c2 = '8';
                            break;
                        }
                        default: {
                            c2 = '&';
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
        final char[] charArray2 = "\u001cH".toCharArray();
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
                            c4 = '<';
                            break;
                        }
                        case 1: {
                            c4 = 'i';
                            break;
                        }
                        case 2: {
                            c4 = 'h';
                            break;
                        }
                        case 3: {
                            c4 = '8';
                            break;
                        }
                        default: {
                            c4 = '&';
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
        final char[] charArray3 = "\u0010I\u0001\\\u001c".toCharArray();
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
                            c6 = '<';
                            break;
                        }
                        case 1: {
                            c6 = 'i';
                            break;
                        }
                        case 2: {
                            c6 = 'h';
                            break;
                        }
                        case 3: {
                            c6 = '8';
                            break;
                        }
                        default: {
                            c6 = '&';
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
        final char[] charArray4 = "h\u0001\u0001K\u0006u-HQU\u001c\b\u0004JC]\r\u0011\u0018SO\f\f\u0018@S\u001bHWRT\f\u001a\u0018I^\u0003\r[R\u001c\u0000\u0006\u0018RT\fHjIS\u001dR\u0018".toCharArray();
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
                            c8 = '<';
                            break;
                        }
                        case 1: {
                            c8 = 'i';
                            break;
                        }
                        case 2: {
                            c8 = 'h';
                            break;
                        }
                        case 3: {
                            c8 = '8';
                            break;
                        }
                        default: {
                            c8 = '&';
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
        final char[] charArray5 = "h\u0001\u0001K\u0006u-HQU\u001c\u0007\u0007L\u0006N\f\u000e]TN\f\f\u0018RSI\u0007ZLY\n\u001c\u0018ORI\u001cPC\u001c;\u0007WR\u0006I".toCharArray();
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
                            c10 = '<';
                            break;
                        }
                        case 1: {
                            c10 = 'i';
                            break;
                        }
                        case 2: {
                            c10 = 'h';
                            break;
                        }
                        case 3: {
                            c10 = '8';
                            break;
                        }
                        default: {
                            c10 = '&';
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
        final char[] charArray6 = "R\f\tL\bO\u0010\u001bLCQG\u0002Z".toCharArray();
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
                            c12 = '<';
                            break;
                        }
                        case 1: {
                            c12 = 'i';
                            break;
                        }
                        case 2: {
                            c12 = 'h';
                            break;
                        }
                        case 3: {
                            c12 = '8';
                            break;
                        }
                        default: {
                            c12 = '&';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 <= n24) {
                z[n21] = new String(charArray6).intern();
                jb.z = z;
                jb.a = new f((jb.c != null) ? jb.c : (jb.c = a(jb.z[5])));
                return;
            }
            continue;
        }
    }
}
