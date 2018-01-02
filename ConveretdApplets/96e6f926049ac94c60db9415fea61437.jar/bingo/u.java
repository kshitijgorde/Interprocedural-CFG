// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import a.gb;
import a.r;
import a.s;

public abstract class u extends p implements s, r
{
    private boolean l;
    protected uc m;
    private int n;
    private static String z;
    
    protected void a() {
        this.k();
        this.l = false;
        int n = ((uc)this.a()).h;
        if (this.m != null) {
            n = this.a(n, this.m.h);
        }
        if (n >= 0) {
            this.a().d(n);
        }
    }
    
    protected void k() {
    }
    
    void a(final uc m, final int n) {
        if (this.m != null) {
            this.m.f();
            this.m = null;
        }
        if (m != null) {
            this.m = m;
        }
        this.n = n;
        this.l = true;
    }
    
    protected int l() {
        return this.n;
    }
    
    protected int a(final int n, final int n2) {
        if (this.m == null) {
            return n;
        }
        return n + (n2 - n) * this.n / ((uc)this.a()).i;
    }
    
    protected float a(final float n, final float n2) {
        if (this.m == null) {
            return n;
        }
        return n + (n2 - n) * this.n / ((uc)this.a()).i;
    }
    
    protected void a(final gb gb) {
        super.a(gb);
        if (!(gb instanceof uc)) {
            throw new RuntimeException(u.z + gb);
        }
        final uc uc = (uc)gb;
        this.l = true;
    }
    
    public void d() {
        if (this.l) {
            this.a();
        }
    }
    
    public void a(final int n) {
        if (this.l) {
            this.a();
        }
    }
    
    public void b() {
    }
    
    public void c() {
    }
    
    public void g() {
        super.g();
        this.l = true;
        this.n = 0;
    }
    
    public void h() {
        this.k();
        if (this.m != null) {
            this.m.f();
            this.m = null;
        }
        super.h();
    }
    
    public abstract void f();
    
    public u() {
        this.m = null;
    }
    
    static {
        final char[] charArray = "$Ythl\u0003Y|\u007f#\u0007\u0011r}l\u0019Exvl\u001dDnol\u0012T=zl<Tk~ #Y|\u007f#\u0007\u0011tv<\u001cTp~\"\u0004Pir#\u001e\u000b".toCharArray();
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
                            c2 = 'p';
                            break;
                        }
                        case 1: {
                            c2 = '1';
                            break;
                        }
                        case 2: {
                            c2 = '\u001d';
                            break;
                        }
                        case 3: {
                            c2 = '\u001b';
                            break;
                        }
                        default: {
                            c2 = 'L';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                u.z = new String(charArray).intern();
                return;
            }
            continue;
        }
    }
}
