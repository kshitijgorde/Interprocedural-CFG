// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.bb;
import neat.system.f;

public class ec extends dc
{
    private static f j;
    private static /* synthetic */ Class k;
    private static String z;
    
    public void b() {
        ec.j.a(this);
    }
    
    public static bb newShadow() {
        return (bb)ec.j.a();
    }
    
    public static ec a() {
        return (ec)ec.j.a();
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
        final char[] charArray = "\u000f0rBTC<\u007f".toCharArray();
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
                            c2 = 'm';
                            break;
                        }
                        case 1: {
                            c2 = 'Y';
                            break;
                        }
                        case 2: {
                            c2 = '\u001c';
                            break;
                        }
                        case 3: {
                            c2 = '%';
                            break;
                        }
                        default: {
                            c2 = ';';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                ec.z = new String(charArray).intern();
                ec.j = new f((ec.k != null) ? ec.k : (ec.k = b(ec.z)));
                return;
            }
            continue;
        }
    }
}