// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.cb;
import neat.system.f;
import neat.bb;

public class kb extends bb
{
    private static f d;
    public neat.cb e;
    public int f;
    public int g;
    public neat.cb h;
    public int i;
    public int j;
    public neat.cb k;
    public int l;
    public int m;
    public neat.cb n;
    public int o;
    public int p;
    private static /* synthetic */ Class q;
    private static String z;
    
    public void b() {
        kb.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)kb.d.a();
    }
    
    public static kb a() {
        return (kb)kb.d.a();
    }
    
    public void g() {
        super.g();
        this.f = 0;
        this.g = 0;
        this.i = 0;
        this.j = 0;
        this.l = 0;
        this.m = 0;
        this.o = 0;
        this.p = 0;
    }
    
    public void h() {
        if (this.e != null) {
            this.e.f();
            this.e = null;
        }
        if (this.h != null) {
            this.h.f();
            this.h = null;
        }
        if (this.k != null) {
            this.k.f();
            this.k = null;
        }
        if (this.n != null) {
            this.n.f();
            this.n = null;
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
    
    public kb() {
        this.e = null;
        this.h = null;
        this.k = null;
        this.n = null;
    }
    
    static {
        final char[] charArray = "\u0005q=4:Is1".toCharArray();
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
                            c2 = 'g';
                            break;
                        }
                        case 1: {
                            c2 = '\u0018';
                            break;
                        }
                        case 2: {
                            c2 = 'S';
                            break;
                        }
                        case 3: {
                            c2 = 'S';
                            break;
                        }
                        default: {
                            c2 = 'U';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                kb.z = new String(charArray).intern();
                kb.d = new f((kb.q != null) ? kb.q : (kb.q = a(kb.z)));
                return;
            }
            continue;
        }
    }
}
