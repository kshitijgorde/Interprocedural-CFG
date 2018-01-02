// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.kb;
import neat.system.f;
import neat.bb;

public class tb extends bb
{
    private static f d;
    public kb e;
    public int f;
    private static /* synthetic */ Class g;
    private static String z;
    
    public void b() {
        tb.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)tb.d.a();
    }
    
    public static tb a() {
        return (tb)tb.d.a();
    }
    
    public void g() {
        super.g();
        this.f = 0;
    }
    
    public void h() {
        super.h();
        if (this.e != null) {
            this.e.f();
            this.e = null;
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
    
    public tb() {
        this.e = null;
    }
    
    static {
        final char[] charArray = "\u001f\n\u0012r".toCharArray();
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
                            c2 = '~';
                            break;
                        }
                        case 1: {
                            c2 = '$';
                            break;
                        }
                        case 2: {
                            c2 = 'f';
                            break;
                        }
                        case 3: {
                            c2 = '\u0010';
                            break;
                        }
                        default: {
                            c2 = '_';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                tb.z = new String(charArray).intern();
                tb.d = new f((tb.g != null) ? tb.g : (tb.g = a(tb.z)));
                return;
            }
            continue;
        }
    }
}
