// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.kb;
import neat.system.cb;

public abstract class b implements cb
{
    protected gb a;
    private kb b;
    private l c;
    public boolean d;
    private boolean e;
    private boolean f;
    public static boolean g;
    private static String[] z;
    
    protected gb a() {
        return this.a;
    }
    
    public kb b() {
        return this.b;
    }
    
    void a(final l c) {
        if (this.c != null) {
            throw new RuntimeException(a.b.z[1] + c);
        }
        this.c = c;
    }
    
    void b(final l l) {
        if (this.c == null) {
            throw new RuntimeException(a.b.z[2]);
        }
        if (this.c != l) {
            throw new RuntimeException(a.b.z[4] + this.c + a.b.z[3] + l);
        }
        this.c = null;
    }
    
    void e() {
        this.d = false;
    }
    
    protected boolean f() {
        return this.d;
    }
    
    void i() {
        this.d = true;
    }
    
    void j() {
        this.e = true;
    }
    
    boolean k() {
        this.e = false;
        if (!this.f) {
            return false;
        }
        this.f = false;
        return true;
    }
    
    protected boolean l() {
        return this.e;
    }
    
    public void m() {
        this.f = true;
    }
    
    protected void a(final gb a) {
        if (this.a != null) {
            throw new RuntimeException(a.b.z[0]);
        }
        this.a = a;
        if (a.d != null) {
            this.b = a.d.b();
        }
        else {
            this.b = a.a().b();
        }
    }
    
    protected void n() {
    }
    
    protected void o() {
    }
    
    public void g() {
        this.e();
        this.f = false;
        this.e = false;
    }
    
    public void h() {
        if (this.a != null) {
            this.a.f();
            this.a = null;
        }
        if (this.b != null) {
            this.b.f();
            this.b = null;
        }
        this.c = null;
    }
    
    public abstract void f();
    
    public b() {
        this.a = null;
        this.b = null;
        this.c = null;
    }
    
    static {
        final String[] z = new String[5];
        final int n = 0;
        final char[] charArray = "GlA`_zpM~_zw\br\u0013aaIw\u00063hGr\u001bv`\b2".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0097: {
                if (n2 > 1) {
                    break Label_0097;
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
                            c2 = '\u0004';
                            break;
                        }
                        case 2: {
                            c2 = '(';
                            break;
                        }
                        case 3: {
                            c2 = '\u0013';
                            break;
                        }
                        default: {
                            c2 = '\u007f';
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
        final char[] charArray2 = "ZpM~_zw\br\u0013aaIw\u00063eLw\u001aw$\\|_|p@v\r3lG\u007f\u001bvv\u0012".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0213: {
                if (n6 > 1) {
                    break Label_0213;
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
                            c4 = '\u0004';
                            break;
                        }
                        case 2: {
                            c4 = '(';
                            break;
                        }
                        case 3: {
                            c4 = '\u0013';
                            break;
                        }
                        default: {
                            c4 = '\u007f';
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
        final char[] charArray3 = "ZpM~_zw\b}\u0010g$Iw\u001bv`\bg\u00103lG\u007f\u001bvv\b2".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0329: {
                if (n10 > 1) {
                    break Label_0329;
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
                            c6 = '\u0004';
                            break;
                        }
                        case 2: {
                            c6 = '(';
                            break;
                        }
                        case 3: {
                            c6 = '\u0013';
                            break;
                        }
                        default: {
                            c6 = '\u007f';
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
        final char[] charArray4 = "?$Fv\b3lG\u007f\u001bvv\u0012".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0445: {
                if (n14 > 1) {
                    break Label_0445;
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
                            c8 = '\u0004';
                            break;
                        }
                        case 2: {
                            c8 = '(';
                            break;
                        }
                        case 3: {
                            c8 = '\u0013';
                            break;
                        }
                        default: {
                            c8 = '\u007f';
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
        final char[] charArray5 = "ZpM~_zw\br\u001bwaL3\u000b|$I}_|p@v\r3lG\u007f\u001bvv\u0012".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0561: {
                if (n18 > 1) {
                    break Label_0561;
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
                            c10 = '\u0004';
                            break;
                        }
                        case 2: {
                            c10 = '(';
                            break;
                        }
                        case 3: {
                            c10 = '\u0013';
                            break;
                        }
                        default: {
                            c10 = '\u007f';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 <= n20) {
                z[n17] = new String(charArray5).intern();
                b.z = z;
                return;
            }
            continue;
        }
    }
}
