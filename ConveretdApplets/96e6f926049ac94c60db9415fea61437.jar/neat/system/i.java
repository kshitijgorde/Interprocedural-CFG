// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

import java.awt.event.KeyEvent;

public class i extends h
{
    private static f g;
    private static /* synthetic */ Class h;
    private static String z;
    
    public static i a(final KeyEvent keyEvent) {
        final i i = (i)neat.system.i.g.a();
        i.a(keyEvent);
        return i;
    }
    
    public void f() {
        i.g.a(this);
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
        final char[] charArray = "mxc\u0002\rpdq\u0002Fn3k".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0092: {
                if (n > 1) {
                    break Label_0092;
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
                            c2 = '\u001d';
                            break;
                        }
                        case 2: {
                            c2 = '\u0002';
                            break;
                        }
                        case 3: {
                            c2 = 'v';
                            break;
                        }
                        default: {
                            c2 = '#';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                i.z = new String(charArray).intern();
                i.g = new f((i.h != null) ? i.h : (i.h = a(i.z)));
                return;
            }
            continue;
        }
    }
}
