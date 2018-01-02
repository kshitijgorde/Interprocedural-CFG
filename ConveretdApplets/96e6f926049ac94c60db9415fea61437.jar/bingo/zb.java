// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.cb;
import neat.system.f;
import neat.bb;

public class zb extends bb
{
    private static f d;
    public int e;
    public int f;
    public int g;
    public neat.cb h;
    private static /* synthetic */ Class i;
    private static String z;
    
    public void b() {
        zb.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)zb.d.a();
    }
    
    public static zb a() {
        return (zb)zb.d.a();
    }
    
    public void g() {
        super.g();
        this.e = 0;
        this.f = 0;
        this.g = 0;
    }
    
    public void h() {
        if (this.h != null) {
            this.h.f();
            this.h = null;
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
    
    public zb() {
        this.h = null;
    }
    
    static {
        final char[] charArray = "8\u0002RDEt\u0011^".toCharArray();
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
                            c2 = 'Z';
                            break;
                        }
                        case 1: {
                            c2 = 'k';
                            break;
                        }
                        case 2: {
                            c2 = '<';
                            break;
                        }
                        case 3: {
                            c2 = '#';
                            break;
                        }
                        default: {
                            c2 = '*';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                zb.z = new String(charArray).intern();
                zb.d = new f((zb.i != null) ? zb.i : (zb.i = a(zb.z)));
                return;
            }
            continue;
        }
    }
}
