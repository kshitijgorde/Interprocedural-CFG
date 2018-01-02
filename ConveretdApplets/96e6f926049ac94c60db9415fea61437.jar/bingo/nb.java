// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.cb;
import neat.system.f;
import neat.bb;

public class nb extends bb
{
    private static f d;
    public neat.cb e;
    public int f;
    public int g;
    public int h;
    public neat.cb i;
    public int j;
    public int k;
    public neat.cb l;
    private static /* synthetic */ Class m;
    private static String z;
    
    public void b() {
        nb.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)nb.d.a();
    }
    
    public static nb a() {
        return (nb)nb.d.a();
    }
    
    public void g() {
        super.g();
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.j = 0;
        this.k = 0;
    }
    
    public void h() {
        if (this.e != null) {
            this.e.f();
            this.e = null;
        }
        if (this.i != null) {
            this.i.f();
            this.i = null;
        }
        if (this.l != null) {
            this.l.f();
            this.l = null;
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
    
    public nb() {
        this.e = null;
        this.i = null;
        this.l = null;
    }
    
    static {
        final char[] charArray = "Q\u0011S@ \u001d\u0016_".toCharArray();
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
                            c2 = '3';
                            break;
                        }
                        case 1: {
                            c2 = 'x';
                            break;
                        }
                        case 2: {
                            c2 = '=';
                            break;
                        }
                        case 3: {
                            c2 = '\'';
                            break;
                        }
                        default: {
                            c2 = 'O';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                nb.z = new String(charArray).intern();
                nb.d = new f((nb.m != null) ? nb.m : (nb.m = a(nb.z)));
                return;
            }
            continue;
        }
    }
}
