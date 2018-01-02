// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.cb;
import neat.system.f;
import neat.bb;

public class ac extends bb
{
    private static f d;
    public int e;
    public int f;
    public int g;
    public neat.cb h;
    private static /* synthetic */ Class i;
    private static String z;
    
    public void b() {
        ac.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)ac.d.a();
    }
    
    public static ac a() {
        return (ac)ac.d.a();
    }
    
    public void g() {
        super.g();
        this.e = 0;
        this.f = 0;
        this.g = 0;
    }
    
    public void h() {
        if (this.h != null) {
            this.h.f();
            this.h = null;
        }
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
    
    public ac() {
        this.h = null;
    }
    
    static {
        final char[] charArray = "$\u0007l#;h\u000fa".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0093: {
                if (n > 1) {
                    break Label_0093;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = 'F';
                            break;
                        }
                        case 1: {
                            c2 = 'n';
                            break;
                        }
                        case 2: {
                            c2 = '\u0002';
                            break;
                        }
                        case 3: {
                            c2 = 'D';
                            break;
                        }
                        default: {
                            c2 = 'T';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                ac.z = new String(charArray).intern();
                ac.d = new f((ac.i != null) ? ac.i : (ac.i = a(ac.z)));
                return;
            }
            continue;
        }
    }
}
