// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.f;
import neat.system.cb;

public class l implements cb
{
    private static f a;
    byte[] b;
    private static /* synthetic */ Class c;
    private static String z;
    
    static l a(final int n) {
        final l l = (l)neat.l.a.a();
        l.b = new byte[n];
        return l;
    }
    
    public void f() {
        l.a.a(this);
    }
    
    public void g() {
    }
    
    public void h() {
        this.b = null;
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public l() {
        this.b = null;
    }
    
    static {
        final char[] charArray = "R]P\u001blP".toCharArray();
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
                            c2 = '<';
                            break;
                        }
                        case 1: {
                            c2 = '8';
                            break;
                        }
                        case 2: {
                            c2 = '1';
                            break;
                        }
                        case 3: {
                            c2 = 'o';
                            break;
                        }
                        default: {
                            c2 = 'B';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                l.z = new String(charArray).intern();
                l.a = new f((l.c != null) ? l.c : (l.c = a(l.z)));
                return;
            }
            continue;
        }
    }
}
