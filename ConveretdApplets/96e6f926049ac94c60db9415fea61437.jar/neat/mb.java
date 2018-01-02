// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.f;

public class mb extends lb
{
    private static f e;
    private static /* synthetic */ Class f;
    private static String z;
    
    public static mb a() {
        final mb mb = (mb)neat.mb.e.a();
        mb.i();
        return mb;
    }
    
    public static mb a(final String s) {
        final mb mb = (mb)neat.mb.e.a();
        mb.b(s);
        return mb;
    }
    
    public void f() {
        mb.e.a(this);
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
        final char[] charArray = "mQ\u000fxnnV".toCharArray();
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
                            c2 = '\u0003';
                            break;
                        }
                        case 1: {
                            c2 = '4';
                            break;
                        }
                        case 2: {
                            c2 = 'n';
                            break;
                        }
                        case 3: {
                            c2 = '\f';
                            break;
                        }
                        default: {
                            c2 = '@';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                mb.z = new String(charArray).intern();
                mb.e = new f((mb.f != null) ? mb.f : (mb.f = b(mb.z)));
                return;
            }
            continue;
        }
    }
}
