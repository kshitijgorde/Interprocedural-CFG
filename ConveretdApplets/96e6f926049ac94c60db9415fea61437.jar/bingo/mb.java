// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.system.f;
import neat.bb;

public class mb extends bb
{
    private static f d;
    public int e;
    public float f;
    public float g;
    public float h;
    public float i;
    public float j;
    public float k;
    public int l;
    public int m;
    private static /* synthetic */ Class n;
    private static String z;
    
    public void b() {
        mb.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)mb.d.a();
    }
    
    public static mb a() {
        return (mb)mb.d.a();
    }
    
    public void g() {
        super.g();
        this.e = 1000;
        this.f = 1.0f;
        this.g = 1.0f;
        this.h = 0.0f;
        this.i = 0.0f;
        this.j = 0.0f;
        this.k = 1.0f;
        this.l = 0;
        this.m = 0;
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
        final char[] charArray = "\\3.\r~\u00107\"".toCharArray();
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
                            c2 = '>';
                            break;
                        }
                        case 1: {
                            c2 = 'Z';
                            break;
                        }
                        case 2: {
                            c2 = '@';
                            break;
                        }
                        case 3: {
                            c2 = 'j';
                            break;
                        }
                        default: {
                            c2 = '\u0011';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                mb.z = new String(charArray).intern();
                mb.d = new f((mb.n != null) ? mb.n : (mb.n = a(mb.z)));
                return;
            }
            continue;
        }
    }
}
