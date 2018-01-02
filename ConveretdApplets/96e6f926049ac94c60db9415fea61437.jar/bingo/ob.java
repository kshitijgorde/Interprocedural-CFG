// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.kb;
import neat.cb;
import neat.system.f;
import neat.bb;

public class ob extends bb
{
    private static f d;
    public neat.cb e;
    public kb f;
    public int g;
    public int h;
    public int i;
    public neat.cb j;
    public neat.cb k;
    public neat.cb l;
    public neat.cb m;
    public neat.cb n;
    public neat.cb o;
    public neat.cb p;
    public neat.cb q;
    public kb r;
    public int s;
    public neat.cb t;
    public boolean u;
    public neat.cb v;
    public neat.cb w;
    private static /* synthetic */ Class x;
    public static boolean y;
    private static String z;
    
    public void b() {
        ob.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)ob.d.a();
    }
    
    public static ob a() {
        return (ob)ob.d.a();
    }
    
    public void g() {
        super.g();
        this.g = 0;
        this.h = 0;
        this.i = 75;
        this.s = -1;
        this.u = false;
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
        if (this.j != null) {
            this.j.f();
            this.j = null;
        }
        if (this.k != null) {
            this.k.f();
            this.k = null;
        }
        if (this.l != null) {
            this.l.f();
            this.l = null;
        }
        if (this.m != null) {
            this.m.f();
            this.m = null;
        }
        if (this.n != null) {
            this.n.f();
            this.n = null;
        }
        if (this.o != null) {
            this.o.f();
            this.o = null;
        }
        if (this.p != null) {
            this.p.f();
            this.p = null;
        }
        if (this.q != null) {
            this.q.f();
            this.q = null;
        }
        if (this.r != null) {
            this.r.f();
            this.r = null;
        }
        if (this.t != null) {
            this.t.f();
            this.t = null;
        }
        if (this.v != null) {
            this.v.f();
            this.v = null;
        }
        if (this.w != null) {
            this.w.f();
            this.w = null;
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
    
    public ob() {
        this.e = null;
        this.f = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.t = null;
        this.v = null;
        this.w = null;
    }
    
    static {
        final char[] charArray = "w\u0010|kr;\u0016p".toCharArray();
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
                            c2 = '\u0015';
                            break;
                        }
                        case 1: {
                            c2 = 'y';
                            break;
                        }
                        case 2: {
                            c2 = '\u0012';
                            break;
                        }
                        case 3: {
                            c2 = '\f';
                            break;
                        }
                        default: {
                            c2 = '\u001d';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                ob.z = new String(charArray).intern();
                ob.d = new f((ob.x != null) ? ob.x : (ob.x = a(ob.z)));
                return;
            }
            continue;
        }
    }
}
