// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.cb;

public abstract class f implements cb
{
    private i a;
    private static String[] z;
    
    protected void a(final a a) {
        if (this.a == null) {
            this.a = i.k();
        }
        this.a.a((Object)a);
    }
    
    protected void a(final int n) {
        if (this.a == null) {
            return;
        }
        final r f = this.a.f();
        while (f.a()) {
            ((a)f.b()).a(n);
        }
        f.f();
    }
    
    protected void b(final int n) {
        if (this.a == null) {
            return;
        }
        final r f = this.a.f();
        while (f.a()) {
            ((a)f.b()).b(n);
        }
        f.f();
    }
    
    protected void a() {
        if (this.a == null) {
            return;
        }
        final r f = this.a.f();
        while (f.a()) {
            ((a)f.b()).c();
        }
        f.f();
    }
    
    void b(final a a) {
        if (!this.a.d(a)) {
            throw new IllegalArgumentException(f.z[2] + a + f.z[0] + this + f.z[1]);
        }
        if (this.a.i() == 0) {
            this.a.f();
            this.a = null;
        }
    }
    
    private void b() {
        if (this.a == null) {
            return;
        }
        final r f = this.a.f();
        while (f.a()) {
            ((a)f.b()).b();
        }
        f.f();
        this.a.f();
        this.a = null;
    }
    
    public void g() {
    }
    
    public void h() {
        this.b();
    }
    
    public abstract void f();
    
    public f() {
        this.a = null;
    }
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "^&\u0006\u0000j\u0006<".toCharArray();
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
                            c2 = 'r';
                            break;
                        }
                        case 1: {
                            c2 = '\u0006';
                            break;
                        }
                        case 2: {
                            c2 = 'j';
                            break;
                        }
                        case 3: {
                            c2 = 'i';
                            break;
                        }
                        default: {
                            c2 = '\u0019';
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
        final char[] charArray2 = "R'".toCharArray();
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
                            c4 = 'r';
                            break;
                        }
                        case 1: {
                            c4 = '\u0006';
                            break;
                        }
                        case 2: {
                            c4 = 'j';
                            break;
                        }
                        case 3: {
                            c4 = 'i';
                            break;
                        }
                        default: {
                            c4 = '\u0019';
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
        final char[] charArray3 = "&n\u0003\u001a9\u001br\u000f\u001bx\u0006i\u0018Ip\u0001&\u0004\u0006mRi\u0004Im\u001ao\u0019Iu\u001bu\u001eE9\u001br\u000f\u001bx\u0006i\u0018S".toCharArray();
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
                            c6 = 'r';
                            break;
                        }
                        case 1: {
                            c6 = '\u0006';
                            break;
                        }
                        case 2: {
                            c6 = 'j';
                            break;
                        }
                        case 3: {
                            c6 = 'i';
                            break;
                        }
                        default: {
                            c6 = '\u0019';
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
                return;
            }
            continue;
        }
    }
}
