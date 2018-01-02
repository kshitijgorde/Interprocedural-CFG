// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.kb;
import neat.cb;
import neat.system.f;
import neat.bb;

public class ib extends bb
{
    private static f d;
    public neat.cb e;
    public neat.cb f;
    public neat.cb g;
    public neat.cb h;
    public neat.cb i;
    public int j;
    public neat.cb k;
    public kb l;
    private static /* synthetic */ Class m;
    private static String z;
    
    public void b() {
        ib.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)ib.d.a();
    }
    
    public static ib a() {
        return (ib)ib.d.a();
    }
    
    public void g() {
        super.g();
        this.j = 0;
    }
    
    public void h() {
        if (this.e != null) {
            this.e.f();
            this.e = null;
        }
        if (this.f != null) {
            this.f.f();
            this.f = null;
        }
        if (this.g != null) {
            this.g.f();
            this.g = null;
        }
        if (this.h != null) {
            this.h.f();
            this.h = null;
        }
        if (this.i != null) {
            this.i.f();
            this.i = null;
        }
        if (this.k != null) {
            this.k.f();
            this.k = null;
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
    
    public ib() {
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.k = null;
        this.l = null;
    }
    
    static {
        final char[] charArray = "\u0005$^|\u000fI$R".toCharArray();
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
                            c2 = 'M';
                            break;
                        }
                        case 2: {
                            c2 = '0';
                            break;
                        }
                        case 3: {
                            c2 = '\u001b';
                            break;
                        }
                        default: {
                            c2 = '`';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                ib.z = new String(charArray).intern();
                ib.d = new f((ib.m != null) ? ib.m : (ib.m = a(ib.z)));
                return;
            }
            continue;
        }
    }
}
