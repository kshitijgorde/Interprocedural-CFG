// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

public abstract class kb implements cb, x
{
    private jb b;
    private Object c;
    private v d;
    private boolean e;
    private static String[] z;
    
    protected void a(final Object c) {
        if (this.b != null) {
            throw new RuntimeException(kb.z[4] + this + kb.z[1] + c + ")");
        }
        this.c = c;
        (this.b = jb.c()).a(this, c);
    }
    
    protected void a(final kb kb, final Object c) {
        if (this.b != null) {
            throw new RuntimeException(kb.z[4] + this + kb.z[1] + c + ")");
        }
        this.c = c;
        (this.b = kb.a()).a(this, c);
    }
    
    private jb a() {
        if (this.b == null) {
            throw new RuntimeException(kb.z[0] + this + kb.z[1] + this.c + ")");
        }
        return this.b;
    }
    
    public kb b(final Object o) {
        if (this.b == null) {
            throw new RuntimeException(kb.z[0] + this + kb.z[1] + this.c + ")");
        }
        return this.b.b(o);
    }
    
    protected void b() {
        this.b.b();
    }
    
    protected int c() {
        return 0;
    }
    
    protected final void d() {
        if (this.b == null) {
            throw new RuntimeException(kb.z[0] + this + kb.z[1] + this.c + ")");
        }
        this.b.a();
    }
    
    final void e() {
        if (this.e) {
            this.j();
            this.e = false;
        }
        this.k();
    }
    
    final void i() {
        if (!this.e) {
            this.l();
            this.e = true;
        }
    }
    
    protected void j() {
    }
    
    protected void k() {
        this.m();
    }
    
    protected void l() {
    }
    
    protected void a(final g g) {
        this.d.a(g);
    }
    
    protected void m() {
        this.d.a();
    }
    
    protected void c(final Object o) {
        this.b(o).d.b(this);
    }
    
    public void receiveEvent(final g g) {
    }
    
    public void f() {
        throw new RuntimeException(kb.z[3] + this + kb.z[2]);
    }
    
    public void g() {
        this.d = v.a();
        this.e = true;
    }
    
    public void h() {
        this.i();
        this.b.a(this.c);
        this.b = null;
        this.c = null;
        this.d.f();
        this.d = null;
    }
    
    public kb() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = true;
    }
    
    static {
        final String[] z = new String[5];
        final int n = 0;
        final char[] charArray = "\u0014xjO\n\u0012\u007flHe\"zf_^`yp\u001cD/d#]^4q`TO$0zY^z".toCharArray();
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
                            c2 = '@';
                            break;
                        }
                        case 1: {
                            c2 = '\u0010';
                            break;
                        }
                        case 2: {
                            c2 = '\u0003';
                            break;
                        }
                        case 3: {
                            c2 = '<';
                            break;
                        }
                        default: {
                            c2 = '*';
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
        final char[] charArray2 = "`1#\u0014C$*".toCharArray();
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
                            c4 = '@';
                            break;
                        }
                        case 1: {
                            c4 = '\u0010';
                            break;
                        }
                        case 2: {
                            c4 = '\u0003';
                            break;
                        }
                        case 3: {
                            c4 = '<';
                            break;
                        }
                        default: {
                            c4 = '*';
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
        final char[] charArray3 = "`1".toCharArray();
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
                            c6 = '@';
                            break;
                        }
                        case 1: {
                            c6 = '\u0010';
                            break;
                        }
                        case 2: {
                            c6 = '\u0003';
                            break;
                        }
                        case 3: {
                            c6 = '<';
                            break;
                        }
                        default: {
                            c6 = '*';
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
        final char[] charArray4 = "\u0014xjO\n)c#]D`qaO^2q`H\n#|bOYl0`]D`~lH\n$uoY^%*".toCharArray();
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
                            c8 = '@';
                            break;
                        }
                        case 1: {
                            c8 = '\u0010';
                            break;
                        }
                        case 2: {
                            c8 = '\u0003';
                            break;
                        }
                        case 3: {
                            c8 = '<';
                            break;
                        }
                        default: {
                            c8 = '*';
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
        final char[] charArray5 = "\u0014xjO\n\u0012\u007flHe\"zf_^`yp\u001cK,bf]N90bH^!skYNz".toCharArray();
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
                            c10 = '@';
                            break;
                        }
                        case 1: {
                            c10 = '\u0010';
                            break;
                        }
                        case 2: {
                            c10 = '\u0003';
                            break;
                        }
                        case 3: {
                            c10 = '<';
                            break;
                        }
                        default: {
                            c10 = '*';
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
                return;
            }
            continue;
        }
    }
}
