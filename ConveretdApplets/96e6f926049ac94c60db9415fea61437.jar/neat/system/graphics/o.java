// 
// Decompiled by Procyon v0.5.30
// 

package neat.system.graphics;

import java.awt.Image;
import neat.system.graphics.renderer.l;
import neat.system.graphics.renderer.m;
import neat.system.graphics.renderer.g;
import neat.system.graphics.renderer.e;
import neat.system.graphics.renderer.b;
import java.awt.Graphics;
import neat.system.pb;
import neat.i;
import neat.system.kb;
import neat.system.f;
import neat.system.cb;
import neat.system.graphics.renderer.k;

public class o implements k, cb
{
    private static f a;
    private neat.system.graphics.renderer.f b;
    private kb c;
    private i d;
    private i e;
    private i f;
    private i g;
    private int h;
    private static /* synthetic */ Class i;
    private static String[] z;
    
    public void a(final pb pb) {
        if (this.b != null) {
            this.b.a(false);
            this.b = null;
        }
        (this.b = new j(pb, this.c)).a(true);
    }
    
    public boolean a() {
        return this.b instanceof j;
    }
    
    public void a(final Graphics graphics, final boolean b) {
        if (graphics == null) {
            return;
        }
        if (this.b == null) {
            return;
        }
        if (this.b instanceof j) {
            ((j)this.b).a(graphics, b);
        }
    }
    
    public b a() {
        final b a = this.b.a();
        this.d.a(a);
        return a;
    }
    
    public void a(final b b) {
        if (!this.d.d(b)) {
            throw new RuntimeException(o.z[0] + b);
        }
        this.b.a(b);
    }
    
    public g a(final e e) {
        final g a = this.b.a(e);
        this.e.a(a);
        return a;
    }
    
    public void a(final g g) {
        if (!this.e.d(g)) {
            throw new RuntimeException(o.z[2] + g);
        }
        this.b.a(g);
    }
    
    public m b() {
        final m b = this.b.b();
        this.f.a(b);
        return b;
    }
    
    public void a(final m m) {
        if (!this.f.d(m)) {
            throw new RuntimeException(o.z[1] + m);
        }
        this.b.a(m);
    }
    
    public g a(final int n, final int n2) {
        final g a = this.b.a(n, n2);
        this.g.a(a);
        return a;
    }
    
    public g a(final int n, final int n2, final int n3) {
        final g a = this.b.a(n, n2, n3);
        this.g.a(a);
        return a;
    }
    
    public g b(final e e) {
        final g b = this.b.b(e);
        this.g.a(b);
        return b;
    }
    
    public void b(final g g) {
        if (!this.g.d(g)) {
            throw new RuntimeException(o.z[3] + g);
        }
        this.b.b(g);
    }
    
    public boolean c() {
        return this.b.c();
    }
    
    public void d() {
        this.b.d();
    }
    
    public void e() {
        this.b.e();
    }
    
    public int f() {
        return this.b.f();
    }
    
    public int g() {
        return this.b.g();
    }
    
    public void a(final boolean b) {
        this.b.a(b);
    }
    
    public boolean h() {
        return this.b.h();
    }
    
    public l i() {
        return this.b.i();
    }
    
    public void a(final l l) {
        this.b.a(l);
    }
    
    public neat.system.graphics.renderer.j a(final Image image, final int n, final int n2, final int n3) {
        return this.b.a(image, n, n2, n3);
    }
    
    public neat.system.graphics.renderer.j a(final byte[] array, final int n, final int n2, final int n3, final int n4) {
        return this.b.a(array, n, n2, n3, n4);
    }
    
    public neat.system.graphics.renderer.j a(final neat.kb kb, final byte[] array, final int n, final int n2) {
        return this.b.a(kb, array, n, n2);
    }
    
    public neat.system.graphics.renderer.j a(final Object o) {
        return this.b.a(o);
    }
    
    public void a(final neat.kb kb) {
        this.b.a(kb);
    }
    
    public void b(final neat.kb kb) {
        this.b.b(kb);
    }
    
    public boolean j() {
        return this.b.j();
    }
    
    public void k() {
        this.b.k();
    }
    
    public static o a(final kb c) {
        final o o = (o)neat.system.graphics.o.a.a();
        o.b = null;
        o.c = c;
        return o;
    }
    
    public void f() {
        o.a.a(this);
    }
    
    public void g() {
        this.d = neat.i.k();
        this.e = neat.i.k();
        this.f = neat.i.k();
        this.g = neat.i.k();
        this.h = 0;
    }
    
    public void h() {
        if (this.d != null) {
            this.d.f();
            this.d = null;
        }
        if (this.e != null) {
            this.e.f();
            this.e = null;
        }
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
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        final String[] z = new String[5];
        final int n = 0;
        final char[] charArray = "\nXV[W=QQ^\u0016-\u0010V[W?\\MM\u0016:I\u001fL\u00122UKM\u0013d".toCharArray();
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
                            c2 = '^';
                            break;
                        }
                        case 1: {
                            c2 = '0';
                            break;
                        }
                        case 2: {
                            c2 = '?';
                            break;
                        }
                        case 3: {
                            c2 = '(';
                            break;
                        }
                        default: {
                            c2 = 'w';
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
        final char[] charArray2 = "\nXV[W-@MA\u0003;\u0010V[W?\\MM\u0016:I\u001fL\u00122UKM\u0013d".toCharArray();
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
                            c4 = '^';
                            break;
                        }
                        case 1: {
                            c4 = '0';
                            break;
                        }
                        case 2: {
                            c4 = '?';
                            break;
                        }
                        case 3: {
                            c4 = '(';
                            break;
                        }
                        default: {
                            c4 = 'w';
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
        final char[] charArray3 = "\nXV[W=QQ^\u0016-yRI\u0010;\u0010V[W?\\MM\u0016:I\u001fL\u00122UKM\u0013d".toCharArray();
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
                            c6 = '^';
                            break;
                        }
                        case 1: {
                            c6 = '0';
                            break;
                        }
                        case 2: {
                            c6 = '?';
                            break;
                        }
                        case 3: {
                            c6 = '(';
                            break;
                        }
                        default: {
                            c6 = 'w';
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
        final char[] charArray4 = "\nXV[W-@MA\u0003;yRI\u0010;\u0010V[W?\\MM\u0016:I\u001fL\u00122UKM\u0013d".toCharArray();
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
                            c8 = '^';
                            break;
                        }
                        case 1: {
                            c8 = '0';
                            break;
                        }
                        case 2: {
                            c8 = '?';
                            break;
                        }
                        case 3: {
                            c8 = '(';
                            break;
                        }
                        default: {
                            c8 = 'w';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        z[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "0U^\\Y-IL\\\u00123\u001eXZ\u0016.XVK\u0004p_".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0562: {
                if (n18 > 1) {
                    break Label_0562;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = '^';
                            break;
                        }
                        case 1: {
                            c10 = '0';
                            break;
                        }
                        case 2: {
                            c10 = '?';
                            break;
                        }
                        case 3: {
                            c10 = '(';
                            break;
                        }
                        default: {
                            c10 = 'w';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 <= n20) {
                z[n17] = new String(charArray5).intern();
                o.z = z;
                o.a = new f((o.i != null) ? o.i : (o.i = a(o.z[4])));
                return;
            }
            continue;
        }
    }
}
