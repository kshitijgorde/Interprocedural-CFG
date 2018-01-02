// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

import neat.k;

public class qb extends k
{
    private static f g;
    private static /* synthetic */ Class h;
    private static String z;
    
    public static void a() {
    }
    
    public static qb a(final byte[] array, final int n, final int n2) {
        final qb qb = (qb)neat.system.qb.g.a();
        qb.a(array, n, n2);
        return qb;
    }
    
    public void f() {
        qb.g.a(this);
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
        final char[] charArray = "$\u0016'u69\n5u}']7c".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0093: {
                if (n > 1) {
                    break Label_0093;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = 'J';
                            break;
                        }
                        case 1: {
                            c2 = 's';
                            break;
                        }
                        case 2: {
                            c2 = 'F';
                            break;
                        }
                        case 3: {
                            c2 = '\u0001';
                            break;
                        }
                        default: {
                            c2 = '\u0018';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                qb.z = new String(charArray).intern();
                qb.g = new f((qb.h != null) ? qb.h : (qb.h = b(qb.z)));
                return;
            }
            continue;
        }
    }
}
