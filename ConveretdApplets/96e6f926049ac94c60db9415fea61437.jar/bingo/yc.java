// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.bb;
import a.b;
import neat.cb;
import neat.kb;
import neat.eb;
import neat.system.f;
import a.hb;

public class yc extends hb
{
    private static f h;
    public eb i;
    public kb j;
    public neat.cb k;
    public pc l;
    private static /* synthetic */ Class m;
    private static String z;
    
    public b a() {
        return bingo.y.r();
    }
    
    public void b() {
        yc.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)yc.h.a();
    }
    
    public static yc a() {
        return (yc)yc.h.a();
    }
    
    public void g() {
        super.g();
    }
    
    public void h() {
        super.h();
        if (this.i != null) {
            this.i.f();
            this.i = null;
        }
        if (this.j != null) {
            this.j.f();
            this.j = null;
        }
        if (this.k != null) {
            this.k.f();
            this.k = null;
        }
        if (this.l != null) {
            this.l.f();
            this.l = null;
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
    
    public yc() {
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
    }
    
    static {
        final char[] charArray = "^9T\u001df\u0012)Y".toCharArray();
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
                            c2 = '<';
                            break;
                        }
                        case 1: {
                            c2 = 'P';
                            break;
                        }
                        case 2: {
                            c2 = ':';
                            break;
                        }
                        case 3: {
                            c2 = 'z';
                            break;
                        }
                        default: {
                            c2 = '\t';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                yc.z = new String(charArray).intern();
                yc.h = new f((yc.m != null) ? yc.m : (yc.m = a(yc.z)));
                return;
            }
            continue;
        }
    }
}
