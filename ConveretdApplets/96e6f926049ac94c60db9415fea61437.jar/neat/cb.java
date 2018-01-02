// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.gb;
import neat.system.f;
import neat.system.eb;

public class cb extends bb implements eb, ib
{
    private static f d;
    public kb e;
    public kb f;
    public boolean g;
    private kb h;
    private kb i;
    private i j;
    private kb k;
    private i l;
    private gb m;
    private static /* synthetic */ Class n;
    private static String[] z;
    
    void a(final kb kb, final kb kb2) {
        this.c();
        if (this.e != null) {
            return;
        }
        if (this.h != null) {
            this.h.f();
            this.h = null;
        }
        if (this.i != null) {
            this.i.f();
            this.i = null;
        }
        this.i = kb2.b();
        this.h = kb.b();
    }
    
    void b(final kb kb) {
        if (this.k != null) {
            this.k.f();
        }
        this.k = kb.b();
    }
    
    public bb a() {
        if (!this.l.e()) {
            return (bb)this.l.g();
        }
        if (this.e() == null) {
            return null;
        }
        bb bb;
        if (this.e != null) {
            final neat.gb d = neat.gb.d();
            bb = d.a(this.e(), this.k, this.e, this.f);
            d.f();
        }
        else {
            final neat.gb d2 = neat.gb.d();
            if (this.k != null) {
                d2.a(this.k);
            }
            d2.a(this.e());
            bb = d2.a(this.h, this.i, this.f);
            d2.f();
        }
        if (bb == null) {
            return null;
        }
        this.j.a(bb);
        bb.a(this);
        return bb;
    }
    
    public void c() {
        final r f = this.j.f();
        while (f.a()) {
            ((bb)f.b()).b(this);
        }
        f.f();
        this.j.c();
        final r f2 = this.l.f();
        while (f2.a()) {
            ((bb)f2.b()).f();
        }
        f2.f();
        this.l.c();
    }
    
    public void a(final bb bb) {
        if (this.g) {
            this.l.a(bb);
        }
        else {
            bb.b();
        }
    }
    
    public void b(final bb bb) {
        bb.b(this);
        if (!this.j.d(bb)) {
            throw new RuntimeException(cb.z[2] + bb);
        }
    }
    
    public void b() {
        cb.d.a(this);
    }
    
    public static bb newShadow() {
        return (bb)cb.d.a();
    }
    
    public void a(final gb m) {
        this.d();
        (this.m = m).a(this);
    }
    
    public void d() {
        if (this.m != null) {
            this.m.b(this);
            this.m = null;
        }
    }
    
    public gb e() {
        return this.m;
    }
    
    public void a() {
        this.m = null;
    }
    
    public String toString() {
        return super.toString() + cb.z[0] + this.e + cb.z[1] + this.f + "}";
    }
    
    public static cb f() {
        return (cb)cb.d.a();
    }
    
    public void g() {
        super.g();
        this.g = true;
        this.j = neat.i.k();
        this.l = neat.i.k();
    }
    
    public void h() {
        this.c();
        if (this.e != null) {
            this.e.f();
            this.e = null;
        }
        if (this.f != null) {
            this.f.f();
            this.f = null;
        }
        if (this.h != null) {
            this.h.f();
            this.h = null;
        }
        if (this.i != null) {
            this.i.f();
            this.i = null;
        }
        this.j.f();
        this.j = null;
        this.l.f();
        this.l = null;
        if (this.k != null) {
            this.k.f();
            this.k = null;
        }
        this.d();
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
    
    public cb() {
        this.e = null;
        this.f = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
    }
    
    static {
        final String[] z = new String[4];
        final int n = 0;
        final char[] charArray = "\u000eF)\u0013iF\t\r7aL\r\u0001\u001daN\u00034\u001aeG\\".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '\"';
                            break;
                        }
                        case 1: {
                            c2 = 'f';
                            break;
                        }
                        case 2: {
                            c2 = 'z';
                            break;
                        }
                        case 3: {
                            c2 = '{';
                            break;
                        }
                        default: {
                            c2 = '\b';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "\u000eF\u0014\u001aeG\\".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '\"';
                            break;
                        }
                        case 1: {
                            c4 = 'f';
                            break;
                        }
                        case 2: {
                            c4 = 'z';
                            break;
                        }
                        case 3: {
                            c4 = '{';
                            break;
                        }
                        default: {
                            c4 = '\b';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "a\u0007\u0014\\|\u0002\u0014\u001f\u0017mC\u0015\u001f[|J\u000f\t[{J\u0007\u001e\u0014\u007f\u000eF\u0018\u001ekC\u0013\t\u001e(K\u0012Z\u0012{\u0002\b\u0015\u000f(N\t\u001b\u001fmFF\u001c\tgOF\u0016\u0012fI\\".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '\"';
                            break;
                        }
                        case 1: {
                            c6 = 'f';
                            break;
                        }
                        case 2: {
                            c6 = 'z';
                            break;
                        }
                        case 3: {
                            c6 = '{';
                            break;
                        }
                        default: {
                            c6 = '\b';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        z[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "L\u0003\u001b\u000f&A\u0004".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0446: {
                if (n14 > 1) {
                    break Label_0446;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = '\"';
                            break;
                        }
                        case 1: {
                            c8 = 'f';
                            break;
                        }
                        case 2: {
                            c8 = 'z';
                            break;
                        }
                        case 3: {
                            c8 = '{';
                            break;
                        }
                        default: {
                            c8 = '\b';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 <= n16) {
                z[n13] = new String(charArray4).intern();
                cb.z = z;
                cb.d = new f((cb.n != null) ? cb.n : (cb.n = a(cb.z[3])));
                return;
            }
            continue;
        }
    }
}
