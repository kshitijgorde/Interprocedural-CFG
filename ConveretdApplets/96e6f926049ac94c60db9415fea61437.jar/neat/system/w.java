// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

import neat.r;
import neat.pb;
import neat.i;
import neat.ab;

public class w implements x, cb
{
    private static f b;
    private ab c;
    private i d;
    private pb e;
    private pb f;
    private Class[] g;
    private static /* synthetic */ Class h;
    private static String z;
    
    public void a(final g g) {
        g.a();
        this.a();
        final r f = this.d.f();
        while (f.a()) {
            final x x = (x)f.b();
            this.c.a(x).b(neat.system.x.a).b(g);
            if (!this.c.f()) {
                x.receiveEvent(g);
            }
            else {
                this.c.j();
            }
        }
        f.f();
        g.b();
    }
    
    private void a() {
        Label_0029: {
            break Label_0029;
            x x = null;
            do {
                this.d.a(x);
                x = (x)this.e.a();
            } while (x != null);
        }
        Label_0062: {
            break Label_0062;
            x x2 = null;
            do {
                this.d.d(x2);
                x2 = (x)this.f.a();
            } while (x2 != null);
        }
    }
    
    public void a(final x x) {
        if (this.f.b(x)) {
            return;
        }
        this.e.a(x);
    }
    
    public void receiveEvent(final g g) {
        this.a(g);
    }
    
    public static w b() {
        return (w)w.b.a();
    }
    
    public void f() {
        w.b.a(this);
    }
    
    public void g() {
        this.c = ab.p();
        this.d = i.k();
        this.e = pb.d();
        this.f = pb.d();
    }
    
    public void h() {
        this.c.f();
        this.c = null;
        this.d.f();
        this.d = null;
        this.e.f();
        this.e = null;
        this.f.f();
        this.f = null;
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public w() {
        this.g = new Class[1];
    }
    
    static {
        final char[] charArray = "p%B!\u001em9P!UsnT".toCharArray();
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
                            c2 = '\u001e';
                            break;
                        }
                        case 1: {
                            c2 = '@';
                            break;
                        }
                        case 2: {
                            c2 = '#';
                            break;
                        }
                        case 3: {
                            c2 = 'U';
                            break;
                        }
                        default: {
                            c2 = '0';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                w.z = new String(charArray).intern();
                w.b = new f((w.h != null) ? w.h : (w.h = a(w.z)));
                return;
            }
            continue;
        }
    }
}
