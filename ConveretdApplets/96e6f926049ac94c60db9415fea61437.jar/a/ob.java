// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.eb;
import neat.cb;
import neat.system.f;
import neat.bb;

public class ob extends bb
{
    private static f d;
    public int e;
    public long f;
    public neat.cb g;
    public eb h;
    private static /* synthetic */ Class i;
    private static String z;
    
    public void b() {
        ob.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)ob.d.a();
    }
    
    public static ob a() {
        return (ob)ob.d.a();
    }
    
    public void g() {
        super.g();
        this.e = 1;
        this.f = 10L;
    }
    
    public void h() {
        super.h();
        if (this.g != null) {
            this.g.f();
            this.g = null;
        }
        if (this.h != null) {
            this.h.f();
            this.h = null;
        }
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public ob() {
        this.g = null;
        this.h = null;
    }
    
    static {
        final char[] charArray = "0\rp6".toCharArray();
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
                            c2 = 'Q';
                            break;
                        }
                        case 1: {
                            c2 = '#';
                            break;
                        }
                        case 2: {
                            c2 = '\u001f';
                            break;
                        }
                        case 3: {
                            c2 = 'T';
                            break;
                        }
                        default: {
                            c2 = 'g';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                ob.z = new String(charArray).intern();
                ob.d = new f((ob.i != null) ? ob.i : (ob.i = a(ob.z)));
                return;
            }
            continue;
        }
    }
}
