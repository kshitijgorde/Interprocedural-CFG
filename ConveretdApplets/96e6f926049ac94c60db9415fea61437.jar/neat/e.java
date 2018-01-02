// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.f;

public class e extends c
{
    private static f f;
    private static /* synthetic */ Class g;
    private static String[] z;
    
    public void e() {
        throw new RuntimeException(e.z[0]);
    }
    
    public static e a(final i i) {
        final e e = (e)neat.e.f.a();
        e.a((neat.f)i);
        return e;
    }
    
    public void f() {
        e.f.a(this);
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
        final String[] z = new String[2];
        final int n = 0;
        final char[] charArray = "z~gLPCszW\u001fJ6gLP@yz\u001f\u0019CfbZ\u001dKxzZ\u0014\u000epaMPGbkM\u0011Zy|LPAx.o\u0011GdBV\u0003Z6/".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '.';
                            break;
                        }
                        case 1: {
                            c2 = '\u0016';
                            break;
                        }
                        case 2: {
                            c2 = '\u000e';
                            break;
                        }
                        case 3: {
                            c2 = '?';
                            break;
                        }
                        default: {
                            c2 = 'p';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "@soK^K".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '.';
                            break;
                        }
                        case 1: {
                            c4 = '\u0016';
                            break;
                        }
                        case 2: {
                            c4 = '\u000e';
                            break;
                        }
                        case 3: {
                            c4 = '?';
                            break;
                        }
                        default: {
                            c4 = 'p';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 <= n8) {
                z[n5] = new String(charArray2).intern();
                e.z = z;
                e.f = new f((e.g != null) ? e.g : (e.g = b(e.z[1])));
                return;
            }
            continue;
        }
    }
}
