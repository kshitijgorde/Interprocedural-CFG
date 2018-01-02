// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

import neat.system.graphics.renderer.j;
import neat.system.graphics.renderer.AnimShadow;
import neat.r;
import neat.h;
import neat.kb;

public class hb implements cb
{
    private static f a;
    private kb b;
    private qb c;
    private boolean d;
    private h e;
    private int f;
    private static /* synthetic */ Class g;
    private static String z;
    
    private void a(final kb kb, final qb c) {
        if (this.b != null) {
            this.b.f();
            this.b = null;
        }
        if (kb != null) {
            this.b = kb.b();
        }
        if (this.c != null) {
            this.c.f();
        }
        this.c = c;
    }
    
    public boolean a() {
        ++this.f;
        if (this.d) {
            return true;
        }
        final fb j = fb.j();
        j.a(this.b, this.c, true);
        if (j.d()) {
            j.f();
            this.f = 0;
            return false;
        }
        if (j.b()) {
            this.d = true;
            final h f = j.f();
            final r a = f.a();
            while (a.a()) {
                final kb kb = (kb)a.b();
                this.e.a(kb, f.g(kb));
            }
            a.f();
            f.f();
            j.f();
            return true;
        }
        j.f();
        this.f = 0;
        return false;
    }
    
    public void b() {
        if (this.f > 0) {
            --this.f;
        }
        if (this.f <= 0 && this.d) {
            this.d = false;
            this.e.c();
        }
    }
    
    public j a(final gb gb, final AnimShadow animShadow, final kb kb) {
        final Object g = this.e.g(kb);
        if (g instanceof qb) {
            final j a = gb.a((qb)g, animShadow.width, animShadow.height);
            if (a == null) {
                return null;
            }
            this.e.c(kb);
            this.e.a(kb.b(), a);
            return a;
        }
        else {
            if (g instanceof j) {
                return (j)g;
            }
            return null;
        }
    }
    
    public static hb b(final kb kb, final qb qb) {
        final hb hb = (hb)neat.system.hb.a.a();
        hb.a(kb, qb);
        return hb;
    }
    
    public void f() {
        hb.a.a(this);
    }
    
    public void g() {
        this.d = false;
        this.e = h.e();
        this.f = 0;
    }
    
    public void h() {
        if (this.b != null) {
            this.b.f();
            this.b = null;
        }
        if (this.c != null) {
            this.c.f();
            this.c = null;
        }
        this.e.c();
        this.e.f();
        this.e = null;
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public hb() {
        this.b = null;
        this.c = null;
        this.e = null;
    }
    
    static {
        final char[] charArray = "\\EV<CAYD<\b_\u000e_*".toCharArray();
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
                            c2 = '2';
                            break;
                        }
                        case 1: {
                            c2 = ' ';
                            break;
                        }
                        case 2: {
                            c2 = '7';
                            break;
                        }
                        case 3: {
                            c2 = 'H';
                            break;
                        }
                        default: {
                            c2 = 'm';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                hb.z = new String(charArray).intern();
                hb.a = new f((hb.g != null) ? hb.g : (hb.g = a(hb.z)));
                return;
            }
            continue;
        }
    }
}
