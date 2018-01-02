// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

public class r extends p
{
    private static f e;
    private static /* synthetic */ Class f;
    private static String z;
    
    public static r a(final long c) {
        final r r = (r)neat.system.r.e.a();
        r.c = c;
        return r;
    }
    
    public void f() {
        r.e.a(this);
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
        final char[] charArray = "y4(\u0002kd(:\u0002 z\u007f;".toCharArray();
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
                            c2 = '\u0017';
                            break;
                        }
                        case 1: {
                            c2 = 'Q';
                            break;
                        }
                        case 2: {
                            c2 = 'I';
                            break;
                        }
                        case 3: {
                            c2 = 'v';
                            break;
                        }
                        default: {
                            c2 = 'E';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                r.z = new String(charArray).intern();
                r.e = new f((r.f != null) ? r.f : (r.f = a(r.z)));
                return;
            }
            continue;
        }
    }
}
