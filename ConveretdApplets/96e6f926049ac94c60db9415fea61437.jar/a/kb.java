// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.bb;
import neat.system.f;

public class kb extends hb
{
    private static f h;
    public ac i;
    private static /* synthetic */ Class j;
    private static String z;
    
    public b a() {
        return a.f.d();
    }
    
    public void b() {
        kb.h.a(this);
    }
    
    public static bb newShadow() {
        return (bb)kb.h.a();
    }
    
    public static kb a() {
        return (kb)kb.h.a();
    }
    
    public void g() {
        super.g();
    }
    
    public void h() {
        if (this.i != null) {
            this.i.f();
            this.i = null;
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
    
    public kb() {
        this.i = null;
    }
    
    static {
        final char[] charArray = "\"/\u001e\u0003".toCharArray();
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
                            c2 = 'C';
                            break;
                        }
                        case 1: {
                            c2 = '\u0001';
                            break;
                        }
                        case 2: {
                            c2 = 'u';
                            break;
                        }
                        case 3: {
                            c2 = 'a';
                            break;
                        }
                        default: {
                            c2 = '+';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                kb.z = new String(charArray).intern();
                kb.h = new f((kb.j != null) ? kb.j : (kb.j = a(kb.z)));
                return;
            }
            continue;
        }
    }
}
