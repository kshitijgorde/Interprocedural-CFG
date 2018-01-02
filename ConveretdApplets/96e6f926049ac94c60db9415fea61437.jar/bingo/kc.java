// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.kb;
import neat.system.f;
import neat.bb;

public class kc extends bb
{
    private static f d;
    public int e;
    public int f;
    public int g;
    public int h;
    public kb i;
    public float j;
    public float k;
    public float l;
    public float m;
    public float n;
    public float o;
    private static /* synthetic */ Class p;
    private static String z;
    
    public void b() {
        kc.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)kc.d.a();
    }
    
    public static kc a() {
        return (kc)kc.d.a();
    }
    
    public void g() {
        super.g();
        this.e = 1000;
        this.f = 0;
        this.g = 3000;
        this.h = 0;
        this.j = -1.0f;
        this.k = -1.0f;
        this.l = -1.0f;
        this.m = -1.0f;
        this.n = -1.0f;
        this.o = -1.0f;
    }
    
    public void h() {
        if (this.i != null) {
            this.i.f();
            this.i = null;
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
    
    public kc() {
        this.i = null;
    }
    
    static {
        final char[] charArray = " \u0002+[el\u0000&".toCharArray();
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
                            c2 = 'B';
                            break;
                        }
                        case 1: {
                            c2 = 'k';
                            break;
                        }
                        case 2: {
                            c2 = 'E';
                            break;
                        }
                        case 3: {
                            c2 = '<';
                            break;
                        }
                        default: {
                            c2 = '\n';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                kc.z = new String(charArray).intern();
                kc.d = new f((kc.p != null) ? kc.p : (kc.p = a(kc.z)));
                return;
            }
            continue;
        }
    }
}
