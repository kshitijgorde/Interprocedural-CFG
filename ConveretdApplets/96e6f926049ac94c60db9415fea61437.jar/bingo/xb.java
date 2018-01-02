// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.cb;
import neat.system.f;
import neat.bb;

public class xb extends bb
{
    private static f d;
    public neat.cb e;
    public neat.cb f;
    public neat.cb g;
    public int h;
    public int i;
    public neat.cb j;
    private static /* synthetic */ Class k;
    private static String z;
    
    public void b() {
        xb.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)xb.d.a();
    }
    
    public static xb a() {
        return (xb)xb.d.a();
    }
    
    public void g() {
        super.g();
        this.h = 100;
        this.i = 100;
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
        if (this.j != null) {
            this.j.f();
            this.j = null;
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
    
    public xb() {
        this.e = null;
        this.f = null;
        this.g = null;
        this.j = null;
    }
    
    static {
        final char[] charArray = "3H8ZQ\u007fY4".toCharArray();
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
                            c2 = 'Q';
                            break;
                        }
                        case 1: {
                            c2 = '!';
                            break;
                        }
                        case 2: {
                            c2 = 'V';
                            break;
                        }
                        case 3: {
                            c2 = '=';
                            break;
                        }
                        default: {
                            c2 = '>';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                xb.z = new String(charArray).intern();
                xb.d = new f((xb.k != null) ? xb.k : (xb.k = a(xb.z)));
                return;
            }
            continue;
        }
    }
}
