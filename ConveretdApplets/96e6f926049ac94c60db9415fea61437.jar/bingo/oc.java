// 
// Decompiled by Procyon v0.5.30
// 

package bingo;

import neat.kb;
import neat.cb;
import neat.system.f;
import neat.bb;

public class oc extends bb
{
    private static f d;
    public boolean e;
    public neat.cb f;
    public int g;
    public int h;
    public neat.cb i;
    public int j;
    public int k;
    public neat.cb l;
    public int m;
    public int n;
    public kb o;
    private static /* synthetic */ Class p;
    private static String z;
    
    public void b() {
        oc.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)oc.d.a();
    }
    
    public static oc a() {
        return (oc)oc.d.a();
    }
    
    public void g() {
        super.g();
        this.e = false;
        this.g = -1;
        this.h = -1;
        this.j = -1;
        this.k = -1;
        this.m = -1;
        this.n = -1;
    }
    
    public void h() {
        if (this.f != null) {
            this.f.f();
            this.f = null;
        }
        if (this.i != null) {
            this.i.f();
            this.i = null;
        }
        if (this.l != null) {
            this.l.f();
            this.l = null;
        }
        if (this.o != null) {
            this.o.f();
            this.o = null;
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
    
    public oc() {
        this.f = null;
        this.i = null;
        this.l = null;
        this.o = null;
    }
    
    static {
        final char[] charArray = "FG_^\u0014\nAR".toCharArray();
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
                            c2 = '$';
                            break;
                        }
                        case 1: {
                            c2 = '.';
                            break;
                        }
                        case 2: {
                            c2 = '1';
                            break;
                        }
                        case 3: {
                            c2 = '9';
                            break;
                        }
                        default: {
                            c2 = '{';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                oc.z = new String(charArray).intern();
                oc.d = new f((oc.p != null) ? oc.p : (oc.p = a(oc.z)));
                return;
            }
            continue;
        }
    }
}
