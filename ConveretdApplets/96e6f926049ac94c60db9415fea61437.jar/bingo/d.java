// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.kb;
import neat.system.f;
import neat.system.cb;

public class d implements cb
{
    private static f a;
    public int b;
    public int c;
    public int d;
    public kb e;
    private static /* synthetic */ Class f;
    private static String z;
    
    public static d a() {
        return (d)d.a.a();
    }
    
    public void f() {
        bingo.d.a.a(this);
    }
    
    public void g() {
        this.b = -1;
        this.c = -1;
        this.d = -1;
    }
    
    public void h() {
        if (this.e != null) {
            this.e.f();
            this.e = null;
        }
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public d() {
        this.e = null;
    }
    
    static {
        final char[] charArray = "H\u0006<1u\u0004\u000b".toCharArray();
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
                            c2 = '*';
                            break;
                        }
                        case 1: {
                            c2 = 'o';
                            break;
                        }
                        case 2: {
                            c2 = 'R';
                            break;
                        }
                        case 3: {
                            c2 = 'V';
                            break;
                        }
                        default: {
                            c2 = '\u001a';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                d.z = new String(charArray).intern();
                d.a = new f((d.f != null) ? d.f : (d.f = a(d.z)));
                return;
            }
            continue;
        }
    }
}
