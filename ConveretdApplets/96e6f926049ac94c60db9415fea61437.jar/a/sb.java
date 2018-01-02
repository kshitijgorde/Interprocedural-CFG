// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.system.f;
import neat.bb;

public class sb extends bb
{
    private static f d;
    private static /* synthetic */ Class e;
    private static String z;
    
    public void b() {
        sb.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)sb.d.a();
    }
    
    public static sb a() {
        return (sb)sb.d.a();
    }
    
    public void g() {
        super.g();
    }
    
    public void h() {
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
    
    static {
        final char[] charArray = "C\u0017IV".toCharArray();
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
                            c2 = '\"';
                            break;
                        }
                        case 1: {
                            c2 = '9';
                            break;
                        }
                        case 2: {
                            c2 = ':';
                            break;
                        }
                        case 3: {
                            c2 = '4';
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
                sb.z = new String(charArray).intern();
                sb.d = new f((sb.e != null) ? sb.e : (sb.e = a(sb.z)));
                return;
            }
            continue;
        }
    }
}
