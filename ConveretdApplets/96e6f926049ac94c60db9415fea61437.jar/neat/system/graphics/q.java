// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics;

import neat.system.f;
import neat.bb;

public abstract class q extends bb
{
    private static f d;
    public int e;
    public int f;
    private static /* synthetic */ Class g;
    private static String z;
    
    public void b() {
        q.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)q.d.a();
    }
    
    public static q a() {
        return (q)q.d.a();
    }
    
    public void g() {
        super.g();
        this.e = -1;
        this.f = -1;
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
        final char[] charArray = "MP/\u0012JPL=\u0012\u0001N\u001b)\u0014\u0005S]'\u0005\u0017\rD".toCharArray();
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
                            c2 = '#';
                            break;
                        }
                        case 1: {
                            c2 = '5';
                            break;
                        }
                        case 2: {
                            c2 = 'N';
                            break;
                        }
                        case 3: {
                            c2 = 'f';
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
                q.z = new String(charArray).intern();
                q.d = new f((q.g != null) ? q.g : (q.g = a(q.z)));
                return;
            }
            continue;
        }
    }
}
