// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

public class q extends p
{
    private static f e;
    private static /* synthetic */ Class f;
    private static String z;
    
    public static q a(final long c) {
        final q q = (q)neat.system.q.e.a();
        q.c = c;
        return q;
    }
    
    public void f() {
        q.e.a(this);
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
        final char[] charArray = "9|4s\t$`&sB:7$".toCharArray();
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
                            c2 = 'W';
                            break;
                        }
                        case 1: {
                            c2 = '\u0019';
                            break;
                        }
                        case 2: {
                            c2 = 'U';
                            break;
                        }
                        case 3: {
                            c2 = '\u0007';
                            break;
                        }
                        default: {
                            c2 = '\'';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                q.z = new String(charArray).intern();
                q.e = new f((q.f != null) ? q.f : (q.f = a(q.z)));
                return;
            }
            continue;
        }
    }
}
