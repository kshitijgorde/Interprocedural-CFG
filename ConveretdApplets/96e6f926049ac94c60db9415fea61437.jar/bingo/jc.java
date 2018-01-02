// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.cb;
import neat.system.f;
import neat.bb;

public class jc extends bb
{
    private static f d;
    public neat.cb e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    private static /* synthetic */ Class k;
    private static String z;
    
    public void b() {
        jc.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)jc.d.a();
    }
    
    public static jc a() {
        return (jc)jc.d.a();
    }
    
    public void g() {
        super.g();
        this.f = -1;
        this.g = -1;
        this.h = 0;
        this.i = 0;
        this.j = -1;
    }
    
    public void h() {
        if (this.e != null) {
            this.e.f();
            this.e = null;
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
    
    public jc() {
        this.e = null;
    }
    
    static {
        final char[] charArray = "8.\u007f)Kt-r".toCharArray();
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
                            c2 = 'Z';
                            break;
                        }
                        case 1: {
                            c2 = 'G';
                            break;
                        }
                        case 2: {
                            c2 = '\u0011';
                            break;
                        }
                        case 3: {
                            c2 = 'N';
                            break;
                        }
                        default: {
                            c2 = '$';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                jc.z = new String(charArray).intern();
                jc.d = new f((jc.k != null) ? jc.k : (jc.k = a(jc.z)));
                return;
            }
            continue;
        }
    }
}
