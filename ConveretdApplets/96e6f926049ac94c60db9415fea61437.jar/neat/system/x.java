// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

import neat.kb;

public interface x
{
    public static final kb a = kb.a(new String(charArray).intern());
    
    void receiveEvent(final g p0);
    
    default static {
        final char[] charArray = "P=n\u0016\u0012T=H\u0005\u001eL,".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0206: {
                if (n > 1) {
                    break Label_0206;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = '\"';
                            break;
                        }
                        case 1: {
                            c2 = 'X';
                            break;
                        }
                        case 2: {
                            c2 = '\r';
                            break;
                        }
                        case 3: {
                            c2 = 's';
                            break;
                        }
                        default: {
                            c2 = '{';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                return;
            }
            continue;
        }
    }
}
