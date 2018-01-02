// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.f;

public class c extends a
{
    private static f a;
    private i b;
    private int c;
    private boolean d;
    private static /* synthetic */ Class e;
    private static String z;
    
    protected void a(final neat.f f) {
        this.b = (i)f;
    }
    
    protected neat.f a() {
        return this.b;
    }
    
    protected void b() {
        this.b = null;
    }
    
    protected void a(final int n) {
        if (n <= this.c) {
            ++this.c;
        }
    }
    
    protected void b(final int n) {
        if (n <= this.c) {
            if (n == this.c) {
                this.d = false;
            }
            --this.c;
        }
    }
    
    protected void c() {
        this.c = -1;
        this.d = false;
    }
    
    public boolean a() {
        return this.c + 1 < this.b.i();
    }
    
    public Object b() {
        this.d = true;
        return this.b.a(++this.c);
    }
    
    public boolean c() {
        return this.d;
    }
    
    public Object a() {
        return this.b.a(this.c);
    }
    
    public int d() {
        return this.c;
    }
    
    public void e() {
        this.b.b(this.c);
    }
    
    public static c a(final i i) {
        final c c = (c)neat.c.a.a();
        c.a((neat.f)i);
        return c;
    }
    
    public void f() {
        neat.c.a.a(this);
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
    
    public c() {
        this.b = null;
        this.c = -1;
        this.d = false;
    }
    
    static {
        final char[] charArray = "?Gs]p2".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0094: {
                if (n > 1) {
                    break Label_0094;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = 'Q';
                            break;
                        }
                        case 1: {
                            c2 = '\"';
                            break;
                        }
                        case 2: {
                            c2 = '\u0012';
                            break;
                        }
                        case 3: {
                            c2 = ')';
                            break;
                        }
                        default: {
                            c2 = '^';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                c.z = new String(charArray).intern();
                c.a = new f((c.e != null) ? c.e : (c.e = a(c.z)));
                return;
            }
            continue;
        }
    }
}
