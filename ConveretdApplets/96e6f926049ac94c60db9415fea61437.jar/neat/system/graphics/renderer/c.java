// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics.renderer;

class c implements i
{
    private final b a;
    private static String z;
    
    public void a(final g g) {
        if (b.a(this.a) != g) {
            throw new RuntimeException(c.z);
        }
        this.a.y();
    }
    
    c(final b a) {
        this.a = a;
    }
    
    static {
        final char[] charArray = "4>\u0004m$$*@e= )\u0005,92n\u000ec$a\"\tb;$*".toCharArray();
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
                            c2 = 'A';
                            break;
                        }
                        case 1: {
                            c2 = 'N';
                            break;
                        }
                        case 2: {
                            c2 = '`';
                            break;
                        }
                        case 3: {
                            c2 = '\f';
                            break;
                        }
                        default: {
                            c2 = 'P';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                c.z = new String(charArray).intern();
                return;
            }
            continue;
        }
    }
}
