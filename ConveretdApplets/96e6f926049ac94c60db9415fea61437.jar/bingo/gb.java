// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.cb;
import neat.system.f;
import neat.bb;

public class gb extends bb
{
    private static f d;
    public neat.cb e;
    private static /* synthetic */ Class f;
    private static String z;
    
    public void b() {
        gb.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)gb.d.a();
    }
    
    public static gb a() {
        return (gb)gb.d.a();
    }
    
    public void g() {
        super.g();
    }
    
    public void h() {
        if (this.e != null) {
            this.e.f();
            this.e = null;
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
    
    public gb() {
        this.e = null;
    }
    
    static {
        final char[] charArray = "N +9g\u0002.'".toCharArray();
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
                            c2 = ',';
                            break;
                        }
                        case 1: {
                            c2 = 'I';
                            break;
                        }
                        case 2: {
                            c2 = 'E';
                            break;
                        }
                        case 3: {
                            c2 = '^';
                            break;
                        }
                        default: {
                            c2 = '\b';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                gb.z = new String(charArray).intern();
                gb.d = new f((gb.f != null) ? gb.f : (gb.f = a(gb.z)));
                return;
            }
            continue;
        }
    }
}
