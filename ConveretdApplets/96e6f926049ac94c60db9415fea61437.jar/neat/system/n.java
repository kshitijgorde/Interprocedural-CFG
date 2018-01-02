// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

import java.awt.event.MouseEvent;

public class n extends k
{
    private static f j;
    private static /* synthetic */ Class k;
    private static String z;
    
    public static n a(final MouseEvent mouseEvent) {
        final n n = (n)neat.system.n.j.a();
        n.a(mouseEvent);
        return n;
    }
    
    public static n a(final n n) {
        final n n2 = (n)n.j.a();
        n2.a(n);
        return n2;
    }
    
    public void f() {
        n.j.a(this);
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
        final char[] charArray = "\u0018s{Qv\u0005oiQ=\u001b8t".toCharArray();
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
                            c2 = 'v';
                            break;
                        }
                        case 1: {
                            c2 = '\u0016';
                            break;
                        }
                        case 2: {
                            c2 = '\u001a';
                            break;
                        }
                        case 3: {
                            c2 = '%';
                            break;
                        }
                        default: {
                            c2 = 'X';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                neat.system.n.z = new String(charArray).intern();
                neat.system.n.j = new f((neat.system.n.k != null) ? neat.system.n.k : (neat.system.n.k = a(neat.system.n.z)));
                return;
            }
            continue;
        }
    }
}
