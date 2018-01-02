// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.f;
import neat.system.cb;

public final class kb implements cb
{
    private static f a;
    private String b;
    private boolean c;
    private Object d;
    private char[] e;
    private int f;
    private kb g;
    private kb h;
    private lb i;
    private char[] j;
    private int k;
    private int l;
    private static /* synthetic */ Class m;
    public static int n;
    private static String[] z;
    
    public static kb a() {
        final kb kb = (kb)neat.kb.a.a();
        kb.c();
        return kb;
    }
    
    public static kb a(final kb kb) {
        final kb kb2 = (kb)kb.a.a();
        kb2.b(kb);
        return kb2;
    }
    
    public static kb a(final kb kb, final int n, final int n2) {
        final kb kb2 = (kb)kb.a.a();
        kb2.b(kb, n, n2);
        return kb2;
    }
    
    public static kb a(final String s) {
        final kb kb = (kb)neat.kb.a.a();
        kb.b(s);
        return kb;
    }
    
    public static kb a(final char c) {
        final kb kb = (kb)neat.kb.a.a();
        kb.b(c);
        return kb;
    }
    
    public static kb a(final lb lb) {
        final kb kb = (kb)neat.kb.a.a();
        kb.b(lb, false);
        return kb;
    }
    
    public static kb a(final lb lb, final boolean b) {
        final kb kb = (kb)neat.kb.a.a();
        kb.b(lb, b);
        return kb;
    }
    
    public static kb a(final char[] array, final int n, final int n2) {
        final kb kb = (kb)neat.kb.a.a();
        kb.b(array, n, n2);
        return kb;
    }
    
    public static kb a(final byte[] array, final int n, final int n2, final int n3) {
        final kb kb = (kb)neat.kb.a.a();
        kb.b(array, n, n2, n3);
        return kb;
    }
    
    public void f() {
        kb.a.a(this);
    }
    
    public void g() {
        this.l = 0;
        this.k = 0;
        this.h = this;
        this.f = -1;
        this.b = null;
    }
    
    public void h() {
        this.b = null;
        if (this.d != null) {
            synchronized (this.d) {
                if (this.i != null) {
                    this.h.i = this.i;
                    this.i = null;
                }
                else if (this.e == null) {
                    this.j = this.h.e;
                    this.h.e = null;
                }
                this.d = null;
                if (this.h == this.g) {
                    this.h.d = null;
                }
                this.h.g = this.g;
                this.g.h = this.h;
                this.h = this;
                this.g = this;
            }
            // monitorexit(this.d)
        }
        if (this.e != null) {
            this.j = this.e;
            this.e = null;
        }
        if (this.i != null) {
            this.i.f();
            this.i = null;
        }
    }
    
    private void b(final kb kb) {
        this.c(kb, kb.k, kb.l);
        this.b = kb.b;
    }
    
    private void b(final kb kb, final int n, final int n2) {
        if (n < 0) {
            throw new StringIndexOutOfBoundsException(n);
        }
        if (n2 < 0) {
            throw new StringIndexOutOfBoundsException(n2);
        }
        if (n + n2 > kb.j.length) {
            throw new StringIndexOutOfBoundsException(n + n2);
        }
        this.c(kb, n, n2);
    }
    
    private void c(final kb g, final int n, final int n2) {
        this.d = g.d;
        if (this.d == null) {
            this.d = g.j;
        }
        synchronized (this.d) {
            g.d = this.d;
            this.c(g.j, n, n2);
            this.h = g.h;
            this.g = g;
            g.h = this;
            this.h.g = this;
        }
        // monitorexit(this.d)
    }
    
    private void b(final String b) {
        this.a(b.length());
        b.getChars(0, this.l, this.j, 0);
        this.b = b;
    }
    
    private void b(final char c) {
        this.a(1);
        this.j[0] = c;
        this.k = 0;
        this.l = 1;
        this.b = null;
    }
    
    private void b(final char[] array, final int n, final int n2) {
        if (n < 0) {
            throw new StringIndexOutOfBoundsException(n);
        }
        if (n2 < 0) {
            throw new StringIndexOutOfBoundsException(n2);
        }
        if (n + n2 > array.length) {
            throw new StringIndexOutOfBoundsException(n + n2);
        }
        this.c(array, n, n2);
    }
    
    private void b(final lb i, final boolean b) {
        if (b) {
            this.c(i.d(), i.e(), i.f());
            if (this.i != null) {
                this.i.f();
            }
            this.i = i;
        }
        else {
            this.a(i.j());
            if (this.l > 0) {
                i.a(0, this.l, this.j, 0);
            }
        }
    }
    
    private void c(final char[] j, final int k, final int l) {
        if (this.e == null) {
            this.e = this.j;
        }
        this.j = j;
        this.k = k;
        this.l = l;
        this.b = null;
    }
    
    public kb b() {
        return a(this);
    }
    
    private void c() {
        this.k = 0;
        this.l = 0;
        this.b = null;
    }
    
    private void b(final byte[] array, int n, final int n2, final int n3) {
        if (n2 < 0) {
            throw new StringIndexOutOfBoundsException(n2);
        }
        if (n3 < 0) {
            throw new StringIndexOutOfBoundsException(n3);
        }
        if (n2 + n3 > array.length) {
            throw new StringIndexOutOfBoundsException(n2 + n3);
        }
        this.a(n3);
        if (n == 0) {
            int n4 = n3;
            while (n4-- > 0) {
                this.j[n4] = (char)(array[n4 + n2] & 0xFF);
            }
        }
        else {
            n <<= 8;
            int n5 = n3;
            while (n5-- > 0) {
                this.j[n5] = (char)(n | (array[n5 + n2] & 0xFF));
            }
        }
    }
    
    private void a(final int l) {
        this.l = l;
        if (this.j.length < this.l) {
            kb.a.a(this.j);
            this.j = kb.a.a(this.l);
        }
        this.b = null;
    }
    
    public int d() {
        return this.l;
    }
    
    public char b(final int n) {
        if (n < 0 || n >= this.l) {
            throw new StringIndexOutOfBoundsException(n);
        }
        return this.j[n + this.k];
    }
    
    public void a(final int n, final int n2, final char[] array, final int n3) {
        System.arraycopy(this.j, this.k + n, array, n3, n2 - n);
    }
    
    public boolean equals(final Object o) {
        if (o != null) {
            if (o instanceof kb) {
                if (o.hashCode() == this.hashCode()) {
                    final kb kb = (kb)o;
                    int l = this.l;
                    if (l == kb.l) {
                        final char[] j = this.j;
                        final char[] i = kb.j;
                        int k = this.k;
                        int m = kb.k;
                        while (l-- != 0) {
                            if (j[k++] != i[m++]) {
                                return false;
                            }
                        }
                        return true;
                    }
                }
            }
            else if (o instanceof String) {
                final String s = (String)o;
                int l2 = this.l;
                if (l2 == s.length()) {
                    final char[] j2 = this.j;
                    int k2 = this.k;
                    int n = 0;
                    while (l2-- != 0) {
                        if (j2[k2++] != s.charAt(n++)) {
                            return false;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean a(final kb kb, final int n) {
        final char[] j = this.j;
        int n2 = this.k + n;
        final int n3 = this.k + this.l;
        final char[] i = kb.j;
        int k = kb.k;
        int l = kb.l;
        if (n < 0 || n2 + l > n3) {
            return false;
        }
        while (--l >= 0) {
            if (j[n2++] != i[k++]) {
                return false;
            }
        }
        return true;
    }
    
    public boolean c(final kb kb) {
        return this.a(kb, this.l - kb.l);
    }
    
    public int hashCode() {
        if (this.f == -1) {
            int f = 0;
            int k = this.k;
            final char[] j = this.j;
            final int l = this.l;
            if (l < 16) {
                for (int i = l; i > 0; --i) {
                    f = f * 37 + j[k++];
                }
            }
            else {
                for (int n = l / 8, n2 = l; n2 > 0; n2 -= n, k += n) {
                    f = f * 39 + j[k];
                }
            }
            if (f == -1) {
                f = -2;
            }
            this.f = f;
        }
        return this.f;
    }
    
    public int a(final int n, final int n2) {
        final int n3 = this.k + this.l;
        final char[] j = this.j;
        for (int i = this.k + n2; i < n3; ++i) {
            if (j[i] == n) {
                return i - this.k;
            }
        }
        return -1;
    }
    
    public int c(final int n) {
        return this.b(n, this.l - 1);
    }
    
    public int b(final int n, final int n2) {
        final int k = this.k;
        final char[] j = this.j;
        for (int i = this.k + ((n2 >= this.l) ? (this.l - 1) : n2); i >= k; --i) {
            if (j[i] == n) {
                return i - this.k;
            }
        }
        return -1;
    }
    
    public int d(final kb kb) {
        return this.b(kb, 0);
    }
    
    public int b(final kb kb, final int n) {
        final char[] j = this.j;
        final char[] i = kb.j;
        final int n2 = this.k + (this.l - kb.l);
        int k = this.k + ((n < 0) ? 0 : n);
    Label_0105:
        while (k <= n2) {
            int l = kb.l;
            int n3 = k;
            int m = kb.k;
            while (l-- != 0) {
                if (j[n3++] != i[m++]) {
                    ++k;
                    continue Label_0105;
                }
            }
            return k - this.k;
        }
        return -1;
    }
    
    public int e(final kb kb) {
        return this.c(kb, this.l);
    }
    
    public int c(final kb kb, int n) {
        if (n < 0) {
            return -1;
        }
        if (n > this.l - kb.l) {
            n = this.l - kb.l;
        }
        if (kb.l == 0) {
            return n;
        }
        final char[] j = this.j;
        final char[] i = kb.j;
        for (int k = this.k + n; k >= this.k; --k) {
            int l = kb.l;
            int n2 = k;
            int m = kb.k;
            while (j[n2++] == i[m++]) {
                if (--l <= 0) {
                    return k - this.k;
                }
            }
        }
        return -1;
    }
    
    public kb d(final int n) {
        return this.c(n, this.d());
    }
    
    public kb c(int n, int n2) {
        if (n > n2) {
            final int n3 = n;
            n = n2;
            n2 = n3;
        }
        if (n < 0) {
            throw new StringIndexOutOfBoundsException(n);
        }
        if (n2 > this.l) {
            throw new StringIndexOutOfBoundsException(n2);
        }
        return a(this, this.k + n, n2 - n);
    }
    
    public kb e() {
        for (int l = this.l, i = 0; i < l; ++i) {
            final char c = this.j[this.k + i];
            final char lowerCase = Character.toLowerCase(c);
            if (c != lowerCase) {
                final kb a = a();
                a.a(l);
                final char[] j = a.j;
                int k;
                for (k = 0; k < i; ++k) {
                    j[k] = this.j[this.k + k];
                }
                j[k++] = lowerCase;
                while (k < l) {
                    j[k] = Character.toLowerCase(this.j[this.k + k]);
                    ++k;
                }
                return a;
            }
        }
        return a(this);
    }
    
    public kb f() {
        int l;
        int n;
        for (l = this.l, n = 0; n < l && this.j[this.k + n] <= ' '; ++n) {}
        while (n < l && this.j[this.k + l - 1] <= ' ') {
            --l;
        }
        return (n > 0 || l < this.l) ? this.c(n, l) : a(this);
    }
    
    public String toString() {
        if (this.b == null) {
            if (this.k + this.l > this.j.length) {
                return kb.z[1] + this.k + kb.z[0] + this.l + kb.z[2] + this.j.length;
            }
            this.b = new String(this.j, this.k, this.l);
        }
        return this.b;
    }
    
    public static kb a(final Object o) {
        return a((o == null) ? kb.z[3] : o.toString());
    }
    
    static /* synthetic */ Class c(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public kb() {
        this.b = null;
        this.c = false;
        this.d = null;
        this.e = null;
        this.g = this;
        this.h = this;
        this.i = null;
        this.j = kb.a.a(0);
    }
    
    static {
        final String[] z = new String[5];
        final int n = 0;
        final char[] charArray = "?\u0000\u001a\u0015^}TC".toCharArray();
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
                            c2 = '\u0013';
                            break;
                        }
                        case 1: {
                            c2 = ' ';
                            break;
                        }
                        case 2: {
                            c2 = 'y';
                            break;
                        }
                        case 3: {
                            c2 = 'z';
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
        final char[] charArray2 = "DR\u0016\u0014L3S\r\bB}GY[\u000b|F\u001f\tNg\u001a".toCharArray();
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
                            c4 = '\u0013';
                            break;
                        }
                        case 1: {
                            c4 = ' ';
                            break;
                        }
                        case 2: {
                            c4 = 'y';
                            break;
                        }
                        case 3: {
                            c4 = 'z';
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
        final char[] charArray3 = "?\u0000\u000f\u001bGfEW\u0016N}G\r\u0012\u0011".toCharArray();
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
                            c6 = '\u0013';
                            break;
                        }
                        case 1: {
                            c6 = ' ';
                            break;
                        }
                        case 2: {
                            c6 = 'y';
                            break;
                        }
                        case 3: {
                            c6 = 'z';
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
            if (n10 > n12) {
                continue;
            }
            break;
        }
        z[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "}U\u0015\u0016".toCharArray();
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
                            c8 = '\u0013';
                            break;
                        }
                        case 1: {
                            c8 = ' ';
                            break;
                        }
                        case 2: {
                            c8 = 'y';
                            break;
                        }
                        case 3: {
                            c8 = 'z';
                            break;
                        }
                        default: {
                            c8 = '+';
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
        final char[] charArray5 = "}E\u0018\u000e\u0005xB".toCharArray();
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
                            c10 = '\u0013';
                            break;
                        }
                        case 1: {
                            c10 = ' ';
                            break;
                        }
                        case 2: {
                            c10 = 'y';
                            break;
                        }
                        case 3: {
                            c10 = 'z';
                            break;
                        }
                        default: {
                            c10 = '+';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 <= n20) {
                z[n17] = new String(charArray5).intern();
                kb.z = z;
                kb.a = new f((kb.m != null) ? kb.m : (kb.m = c(kb.z[4])));
                return;
            }
            continue;
        }
    }
}
