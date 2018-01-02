// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

public class s extends p
{
    private static f e;
    private static /* synthetic */ Class f;
    private static String z;
    
    public static s a(final long c) {
        final s s = (s)neat.system.s.e.a();
        s.c = c;
        return s;
    }
    
    public void f() {
        s.e.a(this);
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
        final char[] charArray = "{$4mJf8&m\u0001xo&".toCharArray();
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
                            c2 = '\u0015';
                            break;
                        }
                        case 1: {
                            c2 = 'A';
                            break;
                        }
                        case 2: {
                            c2 = 'U';
                            break;
                        }
                        case 3: {
                            c2 = '\u0019';
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
                s.z = new String(charArray).intern();
                s.e = new f((s.f != null) ? s.f : (s.f = a(s.z)));
                return;
            }
            continue;
        }
    }
}
