// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.cb;
import neat.system.f;
import neat.bb;

public class yb extends bb
{
    private static f d;
    public neat.cb e;
    public neat.cb f;
    private static /* synthetic */ Class g;
    private static String z;
    
    public void b() {
        yb.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)yb.d.a();
    }
    
    public static yb a() {
        return (yb)yb.d.a();
    }
    
    public void g() {
        super.g();
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
    
    public yb() {
        this.e = null;
        this.f = null;
    }
    
    static {
        final char[] charArray = "c1mLK/!a".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0092: {
                if (n > 1) {
                    break Label_0092;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = '\u0001';
                            break;
                        }
                        case 1: {
                            c2 = 'X';
                            break;
                        }
                        case 2: {
                            c2 = '\u0003';
                            break;
                        }
                        case 3: {
                            c2 = '+';
                            break;
                        }
                        default: {
                            c2 = '$';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                yb.z = new String(charArray).intern();
                yb.d = new f((yb.g != null) ? yb.g : (yb.g = a(yb.z)));
                return;
            }
            continue;
        }
    }
}
