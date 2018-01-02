// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import a.cc;
import neat.kb;
import neat.cb;
import neat.system.f;
import neat.bb;

public class bc extends bb
{
    private static f d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public neat.cb j;
    public kb k;
    public int l;
    public cc m;
    public cc n;
    public fd o;
    private static /* synthetic */ Class p;
    private static String z;
    
    public void b() {
        bc.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)bc.d.a();
    }
    
    public static bc a() {
        return (bc)bc.d.a();
    }
    
    public void g() {
        super.g();
        this.e = 9;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.l = -1;
    }
    
    public void h() {
        if (this.j != null) {
            this.j.f();
            this.j = null;
        }
        if (this.k != null) {
            this.k.f();
            this.k = null;
        }
        if (this.m != null) {
            this.m.f();
            this.m = null;
        }
        if (this.n != null) {
            this.n.f();
            this.n = null;
        }
        if (this.o != null) {
            this.o.f();
            this.o = null;
        }
        super.h();
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public bc() {
        this.j = null;
        this.k = null;
        this.m = null;
        this.n = null;
        this.o = null;
    }
    
    static {
        final char[] charArray = ">zz{Nrqw".toCharArray();
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
                            c2 = '\\';
                            break;
                        }
                        case 1: {
                            c2 = '\u0013';
                            break;
                        }
                        case 2: {
                            c2 = '\u0014';
                            break;
                        }
                        case 3: {
                            c2 = '\u001c';
                            break;
                        }
                        default: {
                            c2 = '!';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                bc.z = new String(charArray).intern();
                bc.d = new f((bc.p != null) ? bc.p : (bc.p = a(bc.z)));
                return;
            }
            continue;
        }
    }
}
