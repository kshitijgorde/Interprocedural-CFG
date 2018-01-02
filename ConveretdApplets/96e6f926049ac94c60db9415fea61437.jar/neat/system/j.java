// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

import java.awt.event.KeyEvent;

public class j extends h
{
    private static f g;
    private static /* synthetic */ Class h;
    private static String z;
    
    public static j a(final KeyEvent keyEvent) {
        final j j = (j)neat.system.j.g.a();
        j.a(keyEvent);
        return j;
    }
    
    public void f() {
        j.g.a(this);
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
        final char[] charArray = "U\u0013\rR@H\u000f\u001fR\u000bVX\u0006".toCharArray();
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
                            c2 = ';';
                            break;
                        }
                        case 1: {
                            c2 = 'v';
                            break;
                        }
                        case 2: {
                            c2 = 'l';
                            break;
                        }
                        case 3: {
                            c2 = '&';
                            break;
                        }
                        default: {
                            c2 = 'n';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                j.z = new String(charArray).intern();
                j.g = new f((j.h != null) ? j.h : (j.h = a(j.z)));
                return;
            }
            continue;
        }
    }
}
