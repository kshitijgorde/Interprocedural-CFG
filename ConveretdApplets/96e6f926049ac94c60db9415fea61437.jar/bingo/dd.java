// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.bb;
import neat.kb;
import neat.system.f;
import a.rb;

public class dd extends rb
{
    private static f I;
    public kb J;
    public int K;
    private static /* synthetic */ Class L;
    private static String M;
    
    public void b() {
        dd.I.a(this);
    }
    
    public static bb newShadow() {
        return (bb)dd.I.a();
    }
    
    public static dd a() {
        return (dd)dd.I.a();
    }
    
    public void g() {
        super.g();
        this.K = -1;
    }
    
    public void h() {
        if (this.J != null) {
            this.J.f();
            this.J = null;
        }
        super.h();
    }
    
    static /* synthetic */ Class b(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public dd() {
        this.J = null;
    }
    
    static {
        final char[] charArray = "\u0019\u000f7R\u0011U\u0002=".toCharArray();
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
                            c2 = '{';
                            break;
                        }
                        case 1: {
                            c2 = 'f';
                            break;
                        }
                        case 2: {
                            c2 = 'Y';
                            break;
                        }
                        case 3: {
                            c2 = '5';
                            break;
                        }
                        default: {
                            c2 = '~';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                dd.M = new String(charArray).intern();
                dd.I = new f((dd.L != null) ? dd.L : (dd.L = b(dd.M)));
                return;
            }
            continue;
        }
    }
}
