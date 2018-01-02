// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.bb;
import neat.system.f;

public class mb extends hb
{
    private static f h;
    public bc i;
    private static /* synthetic */ Class j;
    private static String z;
    
    public b a() {
        return a.h.a();
    }
    
    public void b() {
        mb.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)mb.h.a();
    }
    
    public static mb a() {
        return (mb)mb.h.a();
    }
    
    public void g() {
        super.g();
        this.f = 1;
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
    
    public mb() {
        this.i = null;
    }
    
    static {
        final char[] charArray = "9>8\u001d".toCharArray();
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
                            c2 = 'X';
                            break;
                        }
                        case 1: {
                            c2 = '\u0010';
                            break;
                        }
                        case 2: {
                            c2 = 'U';
                            break;
                        }
                        case 3: {
                            c2 = '\u007f';
                            break;
                        }
                        default: {
                            c2 = 'b';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                mb.z = new String(charArray).intern();
                mb.h = new f((mb.j != null) ? mb.j : (mb.j = a(mb.z)));
                return;
            }
            continue;
        }
    }
}
