// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.bb;
import neat.system.f;

public class fc extends dc
{
    private static f j;
    private static /* synthetic */ Class k;
    private static String z;
    
    public void b() {
        fc.j.a(this);
    }
    
    public static bb newShadow() {
        return (bb)fc.j.a();
    }
    
    public static fc a() {
        return (fc)fc.j.a();
    }
    
    public void g() {
        super.g();
    }
    
    public void h() {
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
    
    static {
        final char[] charArray = "4L;!NxC6".toCharArray();
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
                            c2 = 'V';
                            break;
                        }
                        case 1: {
                            c2 = '%';
                            break;
                        }
                        case 2: {
                            c2 = 'U';
                            break;
                        }
                        case 3: {
                            c2 = 'F';
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
                fc.z = new String(charArray).intern();
                fc.j = new f((fc.k != null) ? fc.k : (fc.k = b(fc.z)));
                return;
            }
            continue;
        }
    }
}
