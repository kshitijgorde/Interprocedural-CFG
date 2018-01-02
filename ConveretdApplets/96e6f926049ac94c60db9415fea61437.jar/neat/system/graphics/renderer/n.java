// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics.renderer;

class n implements i
{
    private final m a;
    private static String z;
    
    public void a(final g g) {
        if (m.a(this.a) != g) {
            throw new RuntimeException(n.z);
        }
        this.a.c(7);
    }
    
    n(final m a) {
        this.a = a;
    }
    
    static {
        final char[] charArray = "x9}KCh-9CZl.|\n^~iwEC-%pD\\h-".toCharArray();
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
                            c2 = '\r';
                            break;
                        }
                        case 1: {
                            c2 = 'I';
                            break;
                        }
                        case 2: {
                            c2 = '\u0019';
                            break;
                        }
                        case 3: {
                            c2 = '*';
                            break;
                        }
                        default: {
                            c2 = '7';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                neat.system.graphics.renderer.n.z = new String(charArray).intern();
                return;
            }
            continue;
        }
    }
}
