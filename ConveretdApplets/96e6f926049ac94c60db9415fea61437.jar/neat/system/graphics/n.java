// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics;

import neat.system.f;
import neat.system.graphics.renderer.m;

public class n extends m
{
    private static f v;
    private static /* synthetic */ Class w;
    private static String z;
    
    protected void a() {
    }
    
    protected void b() {
    }
    
    public static m a() {
        return (n)n.v.a();
    }
    
    public void f() {
        n.v.a(this);
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
        final char[] charArray = "\\y\u0017MtAe\u0005M?_2\u0011K;Bt\u001fZ)\u001cr".toCharArray();
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
                            c2 = '2';
                            break;
                        }
                        case 1: {
                            c2 = '\u001c';
                            break;
                        }
                        case 2: {
                            c2 = 'v';
                            break;
                        }
                        case 3: {
                            c2 = '9';
                            break;
                        }
                        default: {
                            c2 = 'Z';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                neat.system.graphics.n.z = new String(charArray).intern();
                neat.system.graphics.n.v = new f((neat.system.graphics.n.w != null) ? neat.system.graphics.n.w : (neat.system.graphics.n.w = a(neat.system.graphics.n.z)));
                return;
            }
            continue;
        }
    }
}
