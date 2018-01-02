// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.system.f;
import neat.bb;

public class mc extends bb
{
    private static f d;
    public int e;
    private static /* synthetic */ Class f;
    private static String z;
    
    public void b() {
        mc.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)mc.d.a();
    }
    
    public static mc a() {
        return (mc)mc.d.a();
    }
    
    public void g() {
        super.g();
        this.e = 1000;
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
        final char[] charArray = "\u0000p?R\u000bLt2".toCharArray();
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
                            c2 = 'b';
                            break;
                        }
                        case 1: {
                            c2 = '\u0019';
                            break;
                        }
                        case 2: {
                            c2 = 'Q';
                            break;
                        }
                        case 3: {
                            c2 = '5';
                            break;
                        }
                        default: {
                            c2 = 'd';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                mc.z = new String(charArray).intern();
                mc.d = new f((mc.f != null) ? mc.f : (mc.f = a(mc.z)));
                return;
            }
            continue;
        }
    }
}
