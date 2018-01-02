// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.kb;
import neat.system.f;
import neat.bb;

public class vb extends bb
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
    public cc s;
    public cc t;
    public boolean u;
    private static /* synthetic */ Class v;
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
        this.h = -1;
        this.j = 0;
        this.k = -1000;
        this.m = -1;
        this.o = -1.0f;
        this.q = -1;
        this.r = false;
        this.u = false;
    }
    
    public void h() {
        super.h();
        if (this.e != null) {
            this.e.f();
            this.e = null;
        }
        if (this.s != null) {
            this.s.f();
            this.s = null;
        }
        if (this.t != null) {
            this.t.f();
            this.t = null;
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
    
    public vb() {
        this.e = null;
        this.s = null;
        this.t = null;
    }
    
    static {
        final char[] charArray = "~\tc\u001e".toCharArray();
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
                            c2 = '\u001f';
                            break;
                        }
                        case 1: {
                            c2 = '\'';
                            break;
                        }
                        case 2: {
                            c2 = '\u0015';
                            break;
                        }
                        case 3: {
                            c2 = '|';
                            break;
                        }
                        default: {
                            c2 = '\\';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                vb.z = new String(charArray).intern();
                vb.d = new f((vb.v != null) ? vb.v : (vb.v = a(vb.z)));
                return;
            }
            continue;
        }
    }
}
