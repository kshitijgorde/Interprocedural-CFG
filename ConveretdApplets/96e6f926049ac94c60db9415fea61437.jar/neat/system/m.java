// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

import java.awt.event.MouseEvent;

public class m extends k
{
    private static f j;
    private static /* synthetic */ Class k;
    private static String z;
    
    public static m a(final MouseEvent mouseEvent) {
        final m m = (m)neat.system.m.j.a();
        m.a(mouseEvent);
        return m;
    }
    
    public static m a(final m m) {
        final m i = (m)m.j.a();
        i.a(m);
        return i;
    }
    
    public void f() {
        m.j.a(this);
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
        final char[] charArray = "dpo/kyl}/ g;c".toCharArray();
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
                            c2 = '\n';
                            break;
                        }
                        case 1: {
                            c2 = '\u0015';
                            break;
                        }
                        case 2: {
                            c2 = '\u000e';
                            break;
                        }
                        case 3: {
                            c2 = '[';
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
                m.z = new String(charArray).intern();
                m.j = new f((m.k != null) ? m.k : (m.k = a(m.z)));
                return;
            }
            continue;
        }
    }
}
