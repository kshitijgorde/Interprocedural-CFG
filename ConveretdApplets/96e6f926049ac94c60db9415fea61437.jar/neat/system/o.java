// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

import java.awt.event.MouseEvent;

public class o extends k
{
    private static f j;
    private static /* synthetic */ Class k;
    private static String z;
    
    public static o a(final MouseEvent mouseEvent) {
        final o o = (o)neat.system.o.j.a();
        o.a(mouseEvent);
        return o;
    }
    
    public static o a(final o o) {
        final o o2 = (o)o.j.a();
        o2.a(o);
        return o2;
    }
    
    public void f() {
        o.j.a(this);
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
        final char[] charArray = "u!\\\u0011Kh=N\u0011\u0000vjR".toCharArray();
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
                            c2 = '\u001b';
                            break;
                        }
                        case 1: {
                            c2 = 'D';
                            break;
                        }
                        case 2: {
                            c2 = '=';
                            break;
                        }
                        case 3: {
                            c2 = 'e';
                            break;
                        }
                        default: {
                            c2 = 'e';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                o.z = new String(charArray).intern();
                o.j = new f((o.k != null) ? o.k : (o.k = a(o.z)));
                return;
            }
            continue;
        }
    }
}
