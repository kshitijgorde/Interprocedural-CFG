// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.kb;
import neat.system.f;
import neat.bb;

public class ub extends bb
{
    private static f d;
    public kb e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public float o;
    public float p;
    public int q;
    public boolean r;
    public boolean s;
    private static /* synthetic */ Class t;
    private static String z;
    
    public void b() {
        ub.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)ub.d.a();
    }
    
    public static ub a() {
        return (ub)ub.d.a();
    }
    
    public void g() {
        super.g();
        this.h = -1;
        this.j = 0;
        this.m = -1;
        this.o = -1.0f;
        this.q = -1;
        this.r = false;
        this.s = false;
    }
    
    public void h() {
        super.h();
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
    
    public ub() {
        this.e = null;
    }
    
    static {
        final char[] charArray = "\u001aY\u001eh".toCharArray();
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
                            c2 = '{';
                            break;
                        }
                        case 1: {
                            c2 = 'w';
                            break;
                        }
                        case 2: {
                            c2 = 'k';
                            break;
                        }
                        case 3: {
                            c2 = '\n';
                            break;
                        }
                        default: {
                            c2 = '\u001b';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                ub.z = new String(charArray).intern();
                ub.d = new f((ub.t != null) ? ub.t : (ub.t = a(ub.z)));
                return;
            }
            continue;
        }
    }
}
