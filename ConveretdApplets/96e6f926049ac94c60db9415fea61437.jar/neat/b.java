// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import java.util.NoSuchElementException;
import neat.system.f;

public class b extends a
{
    private static f a;
    private g b;
    private int c;
    private boolean d;
    private static /* synthetic */ Class e;
    private static String[] z;
    
    protected void a(final neat.f f) {
        this.b = (g)f;
    }
    
    protected neat.f a() {
        return this.b;
    }
    
    protected void b() {
        this.b = null;
    }
    
    protected void a(final int n) {
    }
    
    protected void b(final int n) {
        if (n == this.c) {
            this.d = false;
        }
    }
    
    protected void c() {
        this.c = -1;
        this.d = false;
    }
    
    public boolean a() {
        if (this.b == null) {
            return false;
        }
        Label_0050: {
            break Label_0050;
            int i = 0;
            do {
                if (this.b.a(i) != null) {
                    return true;
                }
                this.c = i;
                i = this.c + 1;
            } while (i < this.b.f());
        }
        return false;
    }
    
    public Object b() {
        if (!this.a()) {
            throw new NoSuchElementException(neat.b.z[0]);
        }
        this.d = true;
        return this.b.a(++this.c);
    }
    
    public boolean c() {
        return this.b != null && this.d;
    }
    
    public int d() {
        if (this.b == null) {
            throw new NullPointerException(neat.b.z[2]);
        }
        if (!this.d) {
            throw new IllegalStateException(neat.b.z[1]);
        }
        return this.c;
    }
    
    public void e() {
        this.b.b(this.c);
    }
    
    public static b a(final g g) {
        final b b = (b)neat.b.a.a();
        b.a((neat.f)g);
        return b;
    }
    
    public void f() {
        neat.b.a.a(this);
    }
    
    public void g() {
        super.g();
    }
    
    public void h() {
        super.h();
        this.b = null;
        this.c = -1;
        this.d = false;
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public b() {
        this.b = null;
        this.c = -1;
        this.d = false;
    }
    
    static {
        final String[] z = new String[4];
        final int n = 0;
        final char[] charArray = "<j\u0007\u0017nHk\u0011Ee\u0007\"\u000f\ny\r\"\r\u0007a\ra\u0016Eb\u0006\"\u000e\fx\u001c\"C".toCharArray();
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
                            c2 = 'h';
                            break;
                        }
                        case 1: {
                            c2 = '\u0002';
                            break;
                        }
                        case 2: {
                            c2 = 'b';
                            break;
                        }
                        case 3: {
                            c2 = 'e';
                            break;
                        }
                        default: {
                            c2 = '\u000b';
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
        final char[] charArray2 = "<j\u0007Ee\rz\u0016Ef\rv\n\noHj\u0003\u0016+\u0006m\u0016Er\rvB\u0007n\rlB\u0006j\u0004n\u0007\u0001'Hm\u0010E\u007f\u0000gB\ni\u0002g\u0001\u0011+\u0001qB\u0004g\u001ag\u0003\u0001rHp\u0007\bd\u001eg\u0006E*".toCharArray();
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
                            c4 = 'h';
                            break;
                        }
                        case 1: {
                            c4 = '\u0002';
                            break;
                        }
                        case 2: {
                            c4 = 'b';
                            break;
                        }
                        case 3: {
                            c4 = 'e';
                            break;
                        }
                        default: {
                            c4 = '\u000b';
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
        final char[] charArray3 = "<j\u0007\u0017nHk\u0011Ee\u0007\"\u000e\fx\u001c\"C".toCharArray();
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
                            c6 = 'h';
                            break;
                        }
                        case 1: {
                            c6 = '\u0002';
                            break;
                        }
                        case 2: {
                            c6 = 'b';
                            break;
                        }
                        case 3: {
                            c6 = 'e';
                            break;
                        }
                        default: {
                            c6 = '\u000b';
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
        final char[] charArray4 = "\u0006g\u0003\u0011%\n".toCharArray();
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
                            c8 = 'h';
                            break;
                        }
                        case 1: {
                            c8 = '\u0002';
                            break;
                        }
                        case 2: {
                            c8 = 'b';
                            break;
                        }
                        case 3: {
                            c8 = 'e';
                            break;
                        }
                        default: {
                            c8 = '\u000b';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 <= n16) {
                z[n13] = new String(charArray4).intern();
                b.z = z;
                b.a = new f((b.e != null) ? b.e : (b.e = a(b.z[3])));
                return;
            }
            continue;
        }
    }
}
