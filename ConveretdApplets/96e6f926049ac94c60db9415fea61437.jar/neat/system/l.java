// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

import java.awt.event.MouseEvent;

public class l extends k
{
    private static f j;
    private static /* synthetic */ Class k;
    private static String z;
    
    public static l a(final MouseEvent mouseEvent) {
        final l l = (l)neat.system.l.j.a();
        l.a(mouseEvent);
        return l;
    }
    
    public static l a(final l l) {
        final l i = (l)l.j.a();
        i.a(l);
        return i;
    }
    
    public void f() {
        l.j.a(this);
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
        final char[] charArray = "QDV4\u0017LXD4\\R\u000f[".toCharArray();
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
                            c2 = '?';
                            break;
                        }
                        case 1: {
                            c2 = '!';
                            break;
                        }
                        case 2: {
                            c2 = '7';
                            break;
                        }
                        case 3: {
                            c2 = '@';
                            break;
                        }
                        default: {
                            c2 = '9';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                l.z = new String(charArray).intern();
                l.j = new f((l.k != null) ? l.k : (l.k = a(l.z)));
                return;
            }
            continue;
        }
    }
}
