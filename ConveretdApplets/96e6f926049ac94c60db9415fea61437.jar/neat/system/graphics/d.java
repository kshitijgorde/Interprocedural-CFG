// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics;

import neat.nb;
import neat.system.graphics.renderer.q;
import neat.system.gb;
import neat.kb;
import neat.system.f;
import neat.system.graphics.renderer.l;

public class d implements l
{
    private static f a;
    private e b;
    private kb c;
    private boolean d;
    private h e;
    private static /* synthetic */ Class f;
    private static String z;
    
    h a() {
        return this.e;
    }
    
    public void a(final gb gb, final q q, final kb kb) {
        if (this.c != null) {
            this.c.f();
            this.c = null;
        }
        if (this.e != null) {
            this.e.f();
            this.e = null;
        }
        if (q.e != null) {
            kb kb2;
            if (kb != null) {
                kb2 = nb.a(q.e, kb);
            }
            else {
                kb2 = q.e.b();
            }
            (this.e = this.b.c()).a(gb, kb2);
            this.c = kb2.b();
            kb2.f();
        }
        else {
            this.e = null;
        }
        this.d = q.f;
    }
    
    public void a() {
        this.b.a(this);
    }
    
    public void b() {
        this.b.b(this);
    }
    
    public boolean b() {
        return this.d;
    }
    
    public void a(final float n) {
    }
    
    public l c() {
        final d d = d();
        if (this.c != null) {
            d.c = this.c.b();
        }
        if (this.e != null) {
            d.e = this.e.a();
        }
        d.d = this.d;
        return d;
    }
    
    public static d d() {
        e.a();
        return (d)d.a.a();
    }
    
    public void f() {
        neat.system.graphics.d.a.a(this);
    }
    
    public void g() {
        this.b = neat.system.graphics.e.b();
        this.d = false;
    }
    
    public void h() {
        this.b = null;
        if (this.c != null) {
            this.c.f();
            this.c = null;
        }
        if (this.e != null) {
            this.e.f();
            this.e = null;
        }
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public d() {
        this.b = null;
        this.c = null;
        this.e = null;
    }
    
    static {
        final char[] charArray = "w%PkTj9Bk\u001ftnVm\u001bi(X|\t7$".toCharArray();
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
                            c2 = '\u0019';
                            break;
                        }
                        case 1: {
                            c2 = '@';
                            break;
                        }
                        case 2: {
                            c2 = '1';
                            break;
                        }
                        case 3: {
                            c2 = '\u001f';
                            break;
                        }
                        default: {
                            c2 = 'z';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                d.z = new String(charArray).intern();
                d.a = new f((d.f != null) ? d.f : (d.f = a(d.z)));
                return;
            }
            continue;
        }
    }
}
