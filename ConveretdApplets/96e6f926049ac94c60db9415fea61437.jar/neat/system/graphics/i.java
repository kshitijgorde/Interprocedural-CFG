// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics;

import neat.system.f;
import neat.system.graphics.renderer.b;

public class i extends b
{
    private static f u;
    private static /* synthetic */ Class v;
    private static String z;
    
    protected void a() {
    }
    
    protected void b() {
    }
    
    public static i a() {
        return (i)i.u.a();
    }
    
    public void f() {
        i.u.a(this);
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
        final char[] charArray = "\u0002\u00181\u000f@\u001f\u0004#\u000f\u000b\u0001S7\t\u000f\u001c\u00159\u0018\u001dB\u0014".toCharArray();
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
                            c2 = 'l';
                            break;
                        }
                        case 1: {
                            c2 = '}';
                            break;
                        }
                        case 2: {
                            c2 = 'P';
                            break;
                        }
                        case 3: {
                            c2 = '{';
                            break;
                        }
                        default: {
                            c2 = 'n';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                i.z = new String(charArray).intern();
                i.u = new f((i.v != null) ? i.v : (i.v = a(i.z)));
                return;
            }
            continue;
        }
    }
}
