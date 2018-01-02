// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics.renderer;

import neat.system.f;
import neat.system.cb;
import neat.system.bb;

public class a implements bb, cb
{
    private static f a;
    public int b;
    public int c;
    public int d;
    public int e;
    private static /* synthetic */ Class f;
    private static String[] z;
    
    public void a() {
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
    }
    
    public void a(final a a) {
        this.c();
        final int b = a.b;
        final int c = a.c;
        final int n = b + a.d;
        final int n2 = c + a.e;
        int b2 = this.b;
        int c2 = this.c;
        int n3 = this.b + this.d;
        int n4 = this.c + this.e;
        if (b2 < b) {
            b2 = b;
        }
        if (c2 < c) {
            c2 = c;
        }
        if (n3 > n) {
            n3 = n;
        }
        if (n4 > n2) {
            n4 = n2;
        }
        this.b = b2;
        this.c = c2;
        this.d = n3 - b2;
        this.e = n4 - c2;
        this.c();
    }
    
    public void b(final a a) {
        this.b = a.b;
        this.c = a.c;
        this.d = a.d;
        this.e = a.e;
        this.c();
    }
    
    public void a(final int b, final int c, final int d, final int e) {
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.c();
    }
    
    public void a(final int b, final int c) {
        this.b = b;
        this.c = c;
    }
    
    public void b(final int d, final int e) {
        this.d = d;
        this.e = e;
        this.c();
    }
    
    public boolean b() {
        return this.d > 0 && this.e > 0;
    }
    
    public boolean c(final a a) {
        return this.b(a.b, a.c, a.d, a.e);
    }
    
    public boolean b(final int b, final int c, final int d, final int e) {
        if (d <= 0 || e <= 0) {
            throw new RuntimeException(neat.system.graphics.renderer.a.z[4] + neat.system.graphics.renderer.a.z[1] + b + neat.system.graphics.renderer.a.z[3] + c + neat.system.graphics.renderer.a.z[2] + d + neat.system.graphics.renderer.a.z[5] + e);
        }
        boolean b2 = false;
        if (!this.b()) {
            this.b = b;
            this.c = c;
            this.d = d;
            this.e = e;
            b2 = true;
        }
        else {
            int b3 = this.b;
            int c2 = this.c;
            int n = b3 + this.d;
            int n2 = c2 + this.e;
            final int n3 = b + d;
            final int n4 = c + e;
            if (b < b3) {
                b3 = b;
                b2 = true;
            }
            if (c < c2) {
                c2 = c;
                b2 = true;
            }
            if (n3 > n) {
                n = n3;
                b2 = true;
            }
            if (n4 > n2) {
                n2 = n4;
                b2 = true;
            }
            if (b2) {
                this.b = b3;
                this.c = c2;
                this.d = n - b3;
                this.e = n2 - c2;
            }
        }
        this.c();
        return b2;
    }
    
    void c() {
        if (!this.b()) {
            throw new RuntimeException(neat.system.graphics.renderer.a.z[0]);
        }
    }
    
    public int d() {
        this.c();
        return this.b;
    }
    
    public int e() {
        this.c();
        return this.c;
    }
    
    public int f() {
        this.c();
        return this.d;
    }
    
    public int g() {
        this.c();
        return this.e;
    }
    
    public boolean d(final a a) {
        return this.c(a.b, a.c, a.d, a.e);
    }
    
    private boolean c(final int n, final int n2, final int n3, final int n4) {
        return n3 != 0 && n4 != 0 && this.d != 0 && this.e != 0 && this.d() < n + n3 && this.e() < n2 + n4 && this.d() + this.f() > n && this.e() + this.g() > n2;
    }
    
    public boolean equals(final Object o) {
        if (!(o instanceof a)) {
            return false;
        }
        final a a = (a)o;
        return this.b == a.b && this.c == a.c && this.d == a.d && this.e == a.e;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("(" + this.b);
        sb.append("," + this.c + ")");
        sb.append(neat.system.graphics.renderer.a.z[6] + (this.b + this.d));
        sb.append("," + (this.c + this.e) + ")");
        return sb.toString();
    }
    
    public static a h() {
        return (a)neat.system.graphics.renderer.a.a.a();
    }
    
    public void f() {
        neat.system.graphics.renderer.a.a.a(this);
    }
    
    public void g() {
        this.a();
    }
    
    public void h() {
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        final String[] z = new String[8];
        final int n = 0;
        final char[] charArray = "\u001c\u0004N \u0015\t>N\u0013\u0006\u0011\u001eYe\u0001\u001c\u001eQ \u0003".toCharArray();
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
                            c2 = '}';
                            break;
                        }
                        case 1: {
                            c2 = 'w';
                            break;
                        }
                        case 2: {
                            c2 = '=';
                            break;
                        }
                        case 3: {
                            c2 = 'E';
                            break;
                        }
                        default: {
                            c2 = 'g';
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
        final char[] charArray2 = "]\u000f\u001dxG".toCharArray();
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
                            c4 = '}';
                            break;
                        }
                        case 1: {
                            c4 = 'w';
                            break;
                        }
                        case 2: {
                            c4 = '=';
                            break;
                        }
                        case 3: {
                            c4 = 'E';
                            break;
                        }
                        default: {
                            c4 = 'g';
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
        final char[] charArray3 = "]\u0004T?\u0002%W\u0000e".toCharArray();
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
                            c6 = '}';
                            break;
                        }
                        case 1: {
                            c6 = 'w';
                            break;
                        }
                        case 2: {
                            c6 = '=';
                            break;
                        }
                        case 3: {
                            c6 = 'E';
                            break;
                        }
                        default: {
                            c6 = 'g';
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
        final char[] charArray4 = "]\u000e\u001dxG".toCharArray();
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
                            c8 = '}';
                            break;
                        }
                        case 1: {
                            c8 = 'w';
                            break;
                        }
                        case 2: {
                            c8 = '=';
                            break;
                        }
                        case 3: {
                            c8 = 'E';
                            break;
                        }
                        default: {
                            c8 = 'g';
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
        final char[] charArray5 = "]5R0\t\u0019\u0016O<I\u0018\u000fI \t\u0019_\u0014e\u000e\u0013\u0001\\)\u000e\u0019WM$\u0015\u001c\u001aN".toCharArray();
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
                            c10 = '}';
                            break;
                        }
                        case 1: {
                            c10 = 'w';
                            break;
                        }
                        case 2: {
                            c10 = '=';
                            break;
                        }
                        case 3: {
                            c10 = 'E';
                            break;
                        }
                        default: {
                            c10 = 'g';
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
        final char[] charArray6 = "]\u0004T?\u0002$W\u0000e".toCharArray();
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
                            c12 = '}';
                            break;
                        }
                        case 1: {
                            c12 = 'w';
                            break;
                        }
                        case 2: {
                            c12 = '=';
                            break;
                        }
                        case 3: {
                            c12 = 'E';
                            break;
                        }
                        default: {
                            c12 = 'g';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        z[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "P_".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0798: {
                if (n26 > 1) {
                    break Label_0798;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = '}';
                            break;
                        }
                        case 1: {
                            c14 = 'w';
                            break;
                        }
                        case 2: {
                            c14 = '=';
                            break;
                        }
                        case 3: {
                            c14 = 'E';
                            break;
                        }
                        default: {
                            c14 = 'g';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        z[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "\u0013\u0012\\1I\u000e\u000eN1\u0002\u0010YZ7\u0006\r\u001fT&\u0014S\u0005X+\u0003\u0018\u0005X7I\u001c".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0918: {
                if (n30 > 1) {
                    break Label_0918;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = '}';
                            break;
                        }
                        case 1: {
                            c16 = 'w';
                            break;
                        }
                        case 2: {
                            c16 = '=';
                            break;
                        }
                        case 3: {
                            c16 = 'E';
                            break;
                        }
                        default: {
                            c16 = 'g';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 <= n32) {
                z[n29] = new String(charArray8).intern();
                neat.system.graphics.renderer.a.z = z;
                neat.system.graphics.renderer.a.a = new f((neat.system.graphics.renderer.a.f != null) ? neat.system.graphics.renderer.a.f : (neat.system.graphics.renderer.a.f = a(neat.system.graphics.renderer.a.z[7])));
                return;
            }
            continue;
        }
    }
}
