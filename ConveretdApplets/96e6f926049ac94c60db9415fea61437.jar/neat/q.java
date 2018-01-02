// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.f;
import neat.system.y;

public class q implements y
{
    private static f a;
    private int b;
    private static /* synthetic */ Class c;
    private static String z;
    
    public void a(final int b) {
        this.b = b;
    }
    
    public int a() {
        return this.b;
    }
    
    public static q b(final int n) {
        final q q = (q)neat.q.a.a();
        q.a(n);
        return q;
    }
    
    public void f() {
        q.a.a(this);
    }
    
    public String toString() {
        return "[" + this.b + "]";
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
        final char[] charArray = "G\u001aG1;X".toCharArray();
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
                            c2 = ')';
                            break;
                        }
                        case 1: {
                            c2 = '\u007f';
                            break;
                        }
                        case 2: {
                            c2 = '&';
                            break;
                        }
                        case 3: {
                            c2 = 'E';
                            break;
                        }
                        default: {
                            c2 = '\u0015';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                q.z = new String(charArray).intern();
                q.a = new f((q.c != null) ? q.c : (q.c = a(q.z)));
                return;
            }
            continue;
        }
    }
}
