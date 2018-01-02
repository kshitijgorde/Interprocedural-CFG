// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

public class t extends p
{
    private static f e;
    public long f;
    private static /* synthetic */ Class g;
    private static String z;
    
    public static t a(final long c, final long f) {
        final t t = (t)neat.system.t.e.a();
        t.c = c;
        t.f = f;
        return t;
    }
    
    public void f() {
        t.e.a(this);
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
        final char[] charArray = "\u0016yS\u001cj\u000beA\u001c!\u00152F".toCharArray();
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
                            c2 = 'x';
                            break;
                        }
                        case 1: {
                            c2 = '\u001c';
                            break;
                        }
                        case 2: {
                            c2 = '2';
                            break;
                        }
                        case 3: {
                            c2 = 'h';
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
                t.z = new String(charArray).intern();
                t.e = new f((t.g != null) ? t.g : (t.g = a(t.z)));
                return;
            }
            continue;
        }
    }
}
