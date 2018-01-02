// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

public class v extends u
{
    private static f i;
    private w j;
    private static /* synthetic */ Class k;
    private static String z;
    
    public void b(final x x) {
        this.j.a(x);
    }
    
    public static v a() {
        return (v)v.i.a();
    }
    
    public static v a(final boolean b) {
        final v a = a();
        a.a(b);
        return a;
    }
    
    public void f() {
        v.i.a(this);
    }
    
    public void g() {
        super.g();
        this.a(this.j = w.b());
    }
    
    public void h() {
        this.j.f();
        this.j = null;
        super.h();
    }
    
    static /* synthetic */ Class b(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        final char[] charArray = "H8Y_jU$K_!KsN".toCharArray();
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
                            c2 = '&';
                            break;
                        }
                        case 1: {
                            c2 = ']';
                            break;
                        }
                        case 2: {
                            c2 = '8';
                            break;
                        }
                        case 3: {
                            c2 = '+';
                            break;
                        }
                        default: {
                            c2 = 'D';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                v.z = new String(charArray).intern();
                v.i = new f((v.k != null) ? v.k : (v.k = b(v.z)));
                return;
            }
            continue;
        }
    }
}
