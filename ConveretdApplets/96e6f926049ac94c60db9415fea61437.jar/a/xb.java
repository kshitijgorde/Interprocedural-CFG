// 
// Decompiled by Procyon v0.5.30
// 

package a;

import neat.fb;
import neat.system.f;
import neat.bb;

public class xb extends bb
{
    private static f d;
    public fb e;
    public int f;
    public int g;
    public int h;
    public int i;
    public int j;
    public float k;
    public float l;
    public int m;
    public int n;
    public float o;
    public float p;
    public int q;
    public boolean r;
    public int s;
    public int t;
    public int u;
    public boolean v;
    public cc w;
    public cc x;
    public dc y;
    private static /* synthetic */ Class z;
    private static String A;
    
    public static xb a() {
        return (xb)xb.d.a();
    }
    
    public void b() {
        xb.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)xb.d.a();
    }
    
    public void g() {
        super.g();
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 1.0f;
        this.l = 1.0f;
        this.m = 0;
        this.n = -1;
        this.o = 1.0f;
        this.p = 0.0f;
        this.q = -1;
        this.r = false;
        this.s = 0;
        this.t = 0;
        this.u = 0;
        this.v = false;
    }
    
    public void h() {
        if (this.e != null) {
            this.e.f();
            this.e = null;
        }
        if (this.w != null) {
            this.w.f();
            this.w = null;
        }
        if (this.x != null) {
            this.x.f();
            this.x = null;
        }
        if (this.y != null) {
            this.y.f();
            this.y = null;
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
        this.w = null;
        this.x = null;
        this.y = null;
    }
    
    static {
        final char[] charArray = "\u0015:\u001e>".toCharArray();
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
                            c2 = 't';
                            break;
                        }
                        case 1: {
                            c2 = '\u0014';
                            break;
                        }
                        case 2: {
                            c2 = 'f';
                            break;
                        }
                        case 3: {
                            c2 = '\\';
                            break;
                        }
                        default: {
                            c2 = '+';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                xb.A = new String(charArray).intern();
                xb.d = new f((xb.z != null) ? xb.z : (xb.z = a(xb.A)));
                return;
            }
            continue;
        }
    }
}
