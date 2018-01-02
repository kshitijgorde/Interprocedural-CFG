// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import neat.system.f;
import neat.system.cb;

public class ab implements cb
{
    private static f a;
    private Object b;
    private Class c;
    private w d;
    private transient h e;
    private transient h f;
    private kb g;
    private neat.y h;
    private Object[][] i;
    private Object[] j;
    private Class[] k;
    private int l;
    private kb m;
    private x n;
    private static /* synthetic */ Class o;
    public static boolean p;
    private static String[] z;
    
    public ab a(final Object o) {
        this.a(o, o.getClass());
        return this;
    }
    
    public ab a(final Class clazz) {
        this.a((Object)null, clazz);
        return this;
    }
    
    public ab a(final kb kb) {
        Class<?> forName = (Class<?>)this.f.g(kb);
        if (forName == null) {
            try {
                forName = Class.forName(kb.toString());
            }
            catch (ClassNotFoundException ex) {
                throw new RuntimeException(ab.z[2] + kb.toString() + ab.z[1]);
            }
            this.f.a(kb.b(), forName);
        }
        return this.a(forName);
    }
    
    private void a(final Object b, final Class c) {
        this.b = b;
        if (this.c != c) {
            this.c = c;
            this.d = (w)this.e.g(c);
            if (this.d == null) {
                this.d = w.b(c);
                this.e.a(c, this.d);
            }
            this.b();
            this.k();
        }
    }
    
    public Class a() {
        return this.c;
    }
    
    public ab b() {
        if (this.g != null) {
            this.g.f();
            this.g = null;
        }
        this.h = null;
        return this;
    }
    
    private void c() {
        this.h = null;
    }
    
    public ab b(final kb kb) {
        if (this.g != kb) {
            this.b();
            this.g = kb.b();
        }
        else {
            this.c();
        }
        return this.d();
    }
    
    public ab d() {
        this.c();
        for (int i = 0; i < this.l; ++i) {
            this.j[i] = null;
            this.k[i] = null;
        }
        this.l = 0;
        return this;
    }
    
    public ab b(final Object o) {
        if (o == null) {
            throw new IllegalArgumentException(ab.z[9]);
        }
        return this.a(o.getClass(), o);
    }
    
    public ab a(final Class clazz, final Object o) {
        this.c();
        final int n = this.l++;
        if (this.l > this.j.length) {
            final int n2 = this.l + 2;
            final Class[] e = ab.a.e(n2);
            System.arraycopy(this.k, 0, e, 0, this.k.length);
            ab.a.a(this.k);
            this.k = e;
            final Object[] c = ab.a.c(n2);
            System.arraycopy(this.j, 0, c, 0, this.j.length);
            ab.a.a(this.j);
            this.j = c;
        }
        this.k[n] = clazz;
        this.j[n] = o;
        return this;
    }
    
    private void e() {
        if (this.h != null) {
            return;
        }
        if (this.b == null && this.c == null) {
            throw new RuntimeException(ab.z[7]);
        }
        if (this.g == null) {
            throw new RuntimeException(ab.z[8]);
        }
        this.h = this.d.a(this.g, this.k, this.l);
    }
    
    public boolean f() {
        this.e();
        return this.h.b();
    }
    
    private void i() {
        if (this.h != null && this.h.b()) {
            return;
        }
        throw new IllegalArgumentException(ab.z[0] + this.g + ")");
    }
    
    public Object j() {
        this.e();
        this.i();
        if (this.l >= this.i.length) {
            final int length = this.i.length;
            final int n = this.l + 2;
            final Object[][] d = ab.a.d(n);
            System.arraycopy(this.i, 0, d, 0, this.i.length);
            ab.a.a(this.i);
            this.i = d;
            for (int i = length; i < n; ++i) {
                this.i[i] = ab.a.c(i);
            }
        }
        final Object[] array = this.i[this.l];
        for (int j = 0; j < this.l; ++j) {
            array[j] = this.j[j];
        }
        final Object a = this.h.a(this.b, array);
        for (int k = 0; k < this.l; ++k) {
            array[k] = null;
        }
        return a;
    }
    
    public ab k() {
        if (this.m != null) {
            this.m.f();
            this.m = null;
        }
        this.n = null;
        return this;
    }
    
    public ab c(final kb kb) {
        this.k();
        this.m = kb.b();
        this.l();
        return this;
    }
    
    private void l() {
        if (this.n != null) {
            return;
        }
        if (this.b == null && this.c == null) {
            throw new RuntimeException(ab.z[4]);
        }
        if (this.m == null) {
            throw new RuntimeException(ab.z[3]);
        }
        this.n = this.d.a(this.m);
    }
    
    public boolean m() {
        this.l();
        return this.n.b();
    }
    
    private void n() {
        if (this.n != null && this.n.b()) {
            return;
        }
        throw new IllegalArgumentException(ab.z[6] + this.c + ab.z[5] + this.m + ")");
    }
    
    public Class o() {
        this.n();
        return this.n.d();
    }
    
    public void c(final Object o) {
        this.n();
        this.n.a(this.b, o);
    }
    
    public void a(final boolean b) {
        this.n();
        this.n.a(this.b, b);
    }
    
    public void a(final byte b) {
        this.n();
        this.n.a(this.b, b);
    }
    
    public void a(final char c) {
        this.n();
        this.n.a(this.b, c);
    }
    
    public void a(final double n) {
        this.n();
        this.n.a(this.b, n);
    }
    
    public void a(final float n) {
        this.n();
        this.n.a(this.b, n);
    }
    
    public void a(final int n) {
        this.n();
        this.n.a(this.b, n);
    }
    
    public void a(final long n) {
        this.n();
        this.n.a(this.b, n);
    }
    
    public void a(final short n) {
        this.n();
        this.n.a(this.b, n);
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.e = neat.h.e();
        this.f = neat.h.e();
    }
    
    public static ab p() {
        return (ab)ab.a.a();
    }
    
    public void f() {
        ab.a.a(this);
    }
    
    public void g() {
        this.e = neat.h.e();
        this.f = neat.h.e();
    }
    
    public void h() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e.c();
        this.e.f();
        this.e = null;
        this.f.c();
        this.f.f();
        this.f = null;
        this.h = null;
        if (this.g != null) {
            this.g.f();
            this.g = null;
        }
        for (int i = 0; i < this.l; ++i) {
            this.j[i] = null;
        }
        this.n = null;
        if (this.m != null) {
            this.m.f();
            this.m = null;
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
    
    public ab() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = ab.a.d(0);
        this.j = ab.a.c(2);
        this.k = ab.a.e(2);
        this.l = 0;
        this.m = null;
        this.n = null;
    }
    
    static {
        final String[] z = new String[11];
        final int n = 0;
        final char[] charArray = "\u0015s(\u0005\"<62\u00029xp3\u0018#<6}Me6w1\bw".toCharArray();
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
                            c2 = 'X';
                            break;
                        }
                        case 1: {
                            c2 = '\u0016';
                            break;
                        }
                        case 2: {
                            c2 = '\\';
                            break;
                        }
                        case 3: {
                            c2 = 'm';
                            break;
                        }
                        default: {
                            c2 = 'M';
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
        final char[] charArray2 = "x7".toCharArray();
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
                            c4 = 'X';
                            break;
                        }
                        case 1: {
                            c4 = '\u0016';
                            break;
                        }
                        case 2: {
                            c4 = '\\';
                            break;
                        }
                        case 3: {
                            c4 = 'm';
                            break;
                        }
                        default: {
                            c4 = 'M';
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
        final char[] charArray3 = "\f~9M>(s?\u0004+1s8M99d;\b9xu0\f>+62\f =65\u001em6y(M+7c2\tw".toCharArray();
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
                            c6 = 'X';
                            break;
                        }
                        case 1: {
                            c6 = '\u0016';
                            break;
                        }
                        case 2: {
                            c6 = '\\';
                            break;
                        }
                        case 3: {
                            c6 = 'm';
                            break;
                        }
                        default: {
                            c6 = 'M';
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
        final char[] charArray4 = "\u0015c/\u0019m+s(M,xp5\b!<6>\b+7d9M8+s|\u001f(>z9\u000e91y2M+7d|\u000b$=z8\u001emy".toCharArray();
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
                            c8 = 'X';
                            break;
                        }
                        case 1: {
                            c8 = '\u0016';
                            break;
                        }
                        case 2: {
                            c8 = '\\';
                            break;
                        }
                        case 3: {
                            c8 = 'm';
                            break;
                        }
                        default: {
                            c8 = 'M';
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
        final char[] charArray5 = "\u0015c/\u0019m+s(M,xb=\u001f*=b|\u000f(>y.\bm-e9M?=p0\b.,\u007f3\u0003m>y.M+1s0\t>x7".toCharArray();
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
                            c10 = 'X';
                            break;
                        }
                        case 1: {
                            c10 = '\u0016';
                            break;
                        }
                        case 2: {
                            c10 = '\\';
                            break;
                        }
                        case 3: {
                            c10 = 'm';
                            break;
                        }
                        default: {
                            c10 = 'M';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        z[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "t6:\u0004(4rf".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0678: {
                if (n22 > 1) {
                    break Label_0678;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = 'X';
                            break;
                        }
                        case 1: {
                            c12 = '\u0016';
                            break;
                        }
                        case 2: {
                            c12 = '\\';
                            break;
                        }
                        case 3: {
                            c12 = 'm';
                            break;
                        }
                        default: {
                            c12 = 'M';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        z[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "\u001e\u007f9\u0001)xx3\u0019m>y)\u0003)x7|E.4w/\u001ew".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0798: {
                if (n26 > 1) {
                    break Label_0798;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = 'X';
                            break;
                        }
                        case 1: {
                            c14 = '\u0016';
                            break;
                        }
                        case 2: {
                            c14 = '\\';
                            break;
                        }
                        case 3: {
                            c14 = 'm';
                            break;
                        }
                        default: {
                            c14 = 'M';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        z[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "\u0015c/\u0019m+s(M,xb=\u001f*=b|\u000f(>y.\bm-e9M?=p0\b.,\u007f3\u0003m>y.M =b4\u0002)+6}".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0918: {
                if (n30 > 1) {
                    break Label_0918;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = 'X';
                            break;
                        }
                        case 1: {
                            c16 = '\u0016';
                            break;
                        }
                        case 2: {
                            c16 = '\\';
                            break;
                        }
                        case 3: {
                            c16 = 'm';
                            break;
                        }
                        default: {
                            c16 = 'M';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 > n32) {
                continue;
            }
            break;
        }
        z[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "\u0015c/\u0019m+s(M,x{9\u0019%7r|\u000f(>y.\bm-e9M?=p0\b.,\u007f3\u0003m>y.M =b4\u0002)+6}".toCharArray();
        int length9;
        int n35;
        final int n34 = n35 = (length9 = charArray9.length);
        int n36 = 0;
        while (true) {
            Label_1038: {
                if (n34 > 1) {
                    break Label_1038;
                }
                length9 = (n35 = n36);
                do {
                    final char c17 = charArray9[n35];
                    char c18 = '\0';
                    switch (n36 % 5) {
                        case 0: {
                            c18 = 'X';
                            break;
                        }
                        case 1: {
                            c18 = '\u0016';
                            break;
                        }
                        case 2: {
                            c18 = '\\';
                            break;
                        }
                        case 3: {
                            c18 = 'm';
                            break;
                        }
                        default: {
                            c18 = 'M';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n36;
                } while (n34 == 0);
            }
            if (n34 > n36) {
                continue;
            }
            break;
        }
        z[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "\u0015c/\u0019m+s(M9!f9M\">6,\f?9{9\u0019(*6:\u0002?xx)\u0001!xf=\u001f,5s(\b?+6}".toCharArray();
        int length10;
        int n39;
        final int n38 = n39 = (length10 = charArray10.length);
        int n40 = 0;
        while (true) {
            Label_1158: {
                if (n38 > 1) {
                    break Label_1158;
                }
                length10 = (n39 = n40);
                do {
                    final char c19 = charArray10[n39];
                    char c20 = '\0';
                    switch (n40 % 5) {
                        case 0: {
                            c20 = 'X';
                            break;
                        }
                        case 1: {
                            c20 = '\u0016';
                            break;
                        }
                        case 2: {
                            c20 = '\\';
                            break;
                        }
                        case 3: {
                            c20 = 'm';
                            break;
                        }
                        default: {
                            c20 = 'M';
                            break;
                        }
                    }
                    charArray10[length10] = (char)(c19 ^ c20);
                    ++n40;
                } while (n38 == 0);
            }
            if (n38 > n40) {
                continue;
            }
            break;
        }
        z[n37] = new String(charArray10).intern();
        final int n41 = 10;
        final char[] charArray11 = "6s=\u0019c9t".toCharArray();
        int length11;
        int n43;
        final int n42 = n43 = (length11 = charArray11.length);
        int n44 = 0;
        while (true) {
            Label_1278: {
                if (n42 > 1) {
                    break Label_1278;
                }
                length11 = (n43 = n44);
                do {
                    final char c21 = charArray11[n43];
                    char c22 = '\0';
                    switch (n44 % 5) {
                        case 0: {
                            c22 = 'X';
                            break;
                        }
                        case 1: {
                            c22 = '\u0016';
                            break;
                        }
                        case 2: {
                            c22 = '\\';
                            break;
                        }
                        case 3: {
                            c22 = 'm';
                            break;
                        }
                        default: {
                            c22 = 'M';
                            break;
                        }
                    }
                    charArray11[length11] = (char)(c21 ^ c22);
                    ++n44;
                } while (n42 == 0);
            }
            if (n42 <= n44) {
                z[n41] = new String(charArray11).intern();
                ab.z = z;
                ab.a = new f((ab.o != null) ? ab.o : (ab.o = a(ab.z[10])));
                return;
            }
            continue;
        }
    }
}
