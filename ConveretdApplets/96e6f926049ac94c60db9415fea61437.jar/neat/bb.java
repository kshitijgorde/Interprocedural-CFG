// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.cb;

public abstract class bb implements cb
{
    public static final kb MEMBER_ENCODED_NAME_PREP;
    public static final kb NEWLINE;
    static final kb MEMBER_DEFAULT;
    static final kb MEMBER_CLASS_CHILD;
    static final kb NEW_SHADOW_METHOD;
    private kb a;
    private ib b;
    public static int c;
    private static String[] z;
    
    public void a(final kb kb) {
        if (this.a != null) {
            this.a.f();
            this.a = null;
        }
        if (kb != null) {
            this.a = kb.b();
        }
    }
    
    public kb a() {
        return this.a;
    }
    
    public void a(final ib b) {
        if (b == null) {
            throw new RuntimeException(bb.z[2]);
        }
        if (this.b != null) {
            throw new RuntimeException(bb.z[4]);
        }
        this.b = b;
    }
    
    public void b(final ib ib) {
        if (ib == null) {
            throw new RuntimeException(bb.z[2]);
        }
        if (this.b != ib) {
            throw new RuntimeException(bb.z[1]);
        }
        this.b = null;
    }
    
    public abstract void b();
    
    public static bb newShadow() {
        throw new RuntimeException(bb.z[0]);
    }
    
    public final void f() {
        if (this.b != null) {
            this.b.a(this);
        }
        else {
            this.b();
        }
    }
    
    public void g() {
    }
    
    public void h() {
        if (this.a != null) {
            this.a.f();
            this.a = null;
        }
        if (this.b != null) {
            this.b.b(this);
            this.b = null;
        }
    }
    
    public String toString() {
        return bb.z[3] + this.a + "}";
    }
    
    public bb() {
        this.a = null;
        this.b = null;
    }
    
    static {
        bb.z = new String[] { z(z("r\tgF\u000fC\u0016Q\u000e\u0000B\u000euNH\u0006\fg\u0012\tI\u0005\"\u000f\u0012\u0006\u000fm\u0012AO\fr\n\u0004K\u0004l\u0012\u0004BA#")), z(z("e\u0000lA\u0015\u0006\u0002n\u0003\u0000TAq\u000e\u0000B\u000euF\u0013C\u0002{\u0005\rC\u0013.F\u0003C\u0002c\u0013\u0012CAk\u0012AO\u0012l\t\u0015\u0006\u0012g\u0012A\u0007")), z(z("u\tc\u0002\u000eQAp\u0003\u0002_\u0002n\u0003\u0013\u0006\u0011c\u0014\u0000K\u0004v\u0003\u0013\u0006\bqF\u000fS\rnF@")), z(z("u\tc\u0002\u000eQ\u001al\u0007\fC[")), z(z("u\tc\u0002\u000eQAp\u0003\u0002_\u0002n\u0003\u0013\u0006\bqF\u0000J\u0013g\u0007\u0005\u0006\u0012g\u0012A\u0007")) };
        MEMBER_ENCODED_NAME_PREP = kb.a(z(z("\u0007\u0012")));
        NEWLINE = kb.a(z(z("+k")));
        MEMBER_DEFAULT = kb.a(z(z("B\u0004d\u0007\u0014J\u0015")));
        MEMBER_CLASS_CHILD = kb.a(z(z("E\rc\u0015\u0012")));
        NEW_SHADOW_METHOD = kb.a(z(z("H\u0004u5\tG\u0005m\u0011")));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        int i;
        do {
            i = charArray.length;
            if (i < 2) {
                continue;
            }
            return charArray;
        } while (i == 0);
        final int n = 0;
        charArray[n] ^= 'a';
        return charArray;
    }
    
    private static String z(final char[] array) {
        int length;
        int n2;
        final int n = n2 = (length = array.length);
        int n3 = 0;
        while (true) {
            Label_0085: {
                if (n > 1) {
                    break Label_0085;
                }
                length = (n2 = n3);
                do {
                    final char c = array[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = '&';
                            break;
                        }
                        case 1: {
                            c2 = 'a';
                            break;
                        }
                        case 2: {
                            c2 = '\u0002';
                            break;
                        }
                        case 3: {
                            c2 = 'f';
                            break;
                        }
                        default: {
                            c2 = 'a';
                            break;
                        }
                    }
                    array[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                return new String(array).intern();
            }
            continue;
        }
    }
}
