// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.system.f;
import neat.bb;

public class wb extends bb
{
    private static f d;
    public int e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    private static /* synthetic */ Class l;
    private static String z;
    
    public void b() {
        wb.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)wb.d.a();
    }
    
    public static wb a() {
        return (wb)wb.d.a();
    }
    
    public void g() {
        super.g();
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 0;
    }
    
    public void h() {
        super.h();
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
        final char[] charArray = "\u0005W_W".toCharArray();
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
                            c2 = 'd';
                            break;
                        }
                        case 1: {
                            c2 = 'y';
                            break;
                        }
                        case 2: {
                            c2 = '(';
                            break;
                        }
                        case 3: {
                            c2 = '5';
                            break;
                        }
                        default: {
                            c2 = '\u000b';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                wb.z = new String(charArray).intern();
                wb.d = new f((wb.l != null) ? wb.l : (wb.l = a(wb.z)));
                return;
            }
            continue;
        }
    }
}
