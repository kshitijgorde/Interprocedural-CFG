// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.system.f;
import neat.bb;

public class pc extends bb
{
    private static f d;
    public kd e;
    private static /* synthetic */ Class f;
    private static String z;
    
    public void b() {
        pc.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)pc.d.a();
    }
    
    public static pc a() {
        return (pc)pc.d.a();
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
    
    public pc() {
        this.e = null;
    }
    
    static {
        final char[] charArray = "\u0015\u001bO\u0001@Y\u0002B".toCharArray();
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
                            c2 = 'w';
                            break;
                        }
                        case 1: {
                            c2 = 'r';
                            break;
                        }
                        case 2: {
                            c2 = '!';
                            break;
                        }
                        case 3: {
                            c2 = 'f';
                            break;
                        }
                        default: {
                            c2 = '/';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                pc.z = new String(charArray).intern();
                pc.d = new f((pc.f != null) ? pc.f : (pc.f = a(pc.z)));
                return;
            }
            continue;
        }
    }
}
