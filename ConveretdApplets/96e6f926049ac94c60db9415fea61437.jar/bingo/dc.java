// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.fb;
import neat.eb;
import neat.cb;
import neat.system.f;
import neat.bb;

public class dc extends bb
{
    private static f d;
    public neat.cb e;
    public eb f;
    public fb g;
    private static /* synthetic */ Class h;
    public static int i;
    private static String z;
    
    public void b() {
        dc.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)dc.d.a();
    }
    
    public static dc a() {
        return (dc)dc.d.a();
    }
    
    public void g() {
        super.g();
    }
    
    public void h() {
        if (this.e != null) {
            this.e.f();
            this.e = null;
        }
        if (this.f != null) {
            this.f.f();
            this.f = null;
        }
        if (this.g != null) {
            this.g.f();
            this.g = null;
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
    
    public dc() {
        this.e = null;
        this.f = null;
        this.g = null;
    }
    
    static {
        final char[] charArray = "0\t\u000e\u000f'|\u0004\u0003".toCharArray();
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
                            c2 = 'R';
                            break;
                        }
                        case 1: {
                            c2 = '`';
                            break;
                        }
                        case 2: {
                            c2 = '`';
                            break;
                        }
                        case 3: {
                            c2 = 'h';
                            break;
                        }
                        default: {
                            c2 = 'H';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                dc.z = new String(charArray).intern();
                dc.d = new f((dc.h != null) ? dc.h : (dc.h = a(dc.z)));
                return;
            }
            continue;
        }
    }
}
