// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.bb;
import neat.system.graphics.renderer.q;
import neat.system.f;

public class lb extends hb
{
    private static f h;
    public q i;
    public int j;
    public int k;
    public boolean l;
    public boolean m;
    public boolean n;
    private static /* synthetic */ Class o;
    private static String z;
    
    public b a() {
        return a.g.t();
    }
    
    public void b() {
        lb.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)lb.h.a();
    }
    
    public static lb a() {
        return (lb)lb.h.a();
    }
    
    public void g() {
        super.g();
        this.j = 1;
        this.k = 0;
        this.l = false;
        this.m = false;
        this.n = false;
    }
    
    public void h() {
        super.h();
        if (this.i != null) {
            this.i.f();
            this.i = null;
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
    
    public lb() {
        this.i = null;
    }
    
    static {
        final char[] charArray = "b@~5".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0093: {
                if (n > 1) {
                    break Label_0093;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = '\u0003';
                            break;
                        }
                        case 1: {
                            c2 = 'n';
                            break;
                        }
                        case 2: {
                            c2 = '\u0012';
                            break;
                        }
                        case 3: {
                            c2 = 'W';
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
                lb.z = new String(charArray).intern();
                lb.h = new f((lb.o != null) ? lb.o : (lb.o = a(lb.z)));
                return;
            }
            continue;
        }
    }
}
