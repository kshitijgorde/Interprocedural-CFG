// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.cb;
import neat.system.f;
import neat.bb;

public class vb extends bb
{
    private static f d;
    public neat.cb e;
    public float f;
    public float g;
    public float h;
    public int i;
    public int j;
    public int k;
    public float l;
    private static /* synthetic */ Class m;
    private static String z;
    
    public void b() {
        vb.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)vb.d.a();
    }
    
    public static vb a() {
        return (vb)vb.d.a();
    }
    
    public void g() {
        super.g();
        this.f = 0.0f;
        this.g = 0.0f;
        this.h = 0.5f;
        this.i = 1000;
        this.j = 1000;
        this.k = 1;
        this.l = 1.0f;
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
    
    public vb() {
        this.e = null;
    }
    
    static {
        final char[] charArray = "Jv ,8\u0006i,".toCharArray();
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
                            c2 = '(';
                            break;
                        }
                        case 1: {
                            c2 = '\u001f';
                            break;
                        }
                        case 2: {
                            c2 = 'N';
                            break;
                        }
                        case 3: {
                            c2 = 'K';
                            break;
                        }
                        default: {
                            c2 = 'W';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                vb.z = new String(charArray).intern();
                vb.d = new f((vb.m != null) ? vb.m : (vb.m = a(vb.z)));
                return;
            }
            continue;
        }
    }
}
