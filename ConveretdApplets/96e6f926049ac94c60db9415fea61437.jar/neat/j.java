// 
// Decompiled by Procyon v0.5.30
// 

package neat;

public class j extends f
{
    private static neat.system.f b;
    private i c;
    private i d;
    private static /* synthetic */ Class e;
    private static String[] z;
    
    public void a(final Object o, final Object o2) {
        this.c.a(o);
        this.d.a(o2);
    }
    
    public boolean a(final Object o) {
        return this.d.b(o);
    }
    
    public boolean equals(final Object o) {
        if (!(o instanceof j)) {
            return false;
        }
        final j j = (j)o;
        return this.c.equals(j.c) && this.d.equals(j.d);
    }
    
    public Object a(final int n) {
        return this.c.a(n);
    }
    
    public Object b(final int n) {
        return this.d.a(n);
    }
    
    public Object b(final Object o) {
        final int c = this.c.c(o);
        if (c < 0) {
            return null;
        }
        return this.b(c);
    }
    
    public r a() {
        final e a = neat.e.a(this.c);
        this.c.a(a);
        return a;
    }
    
    public r b() {
        final e a = neat.e.a(this.d);
        this.d.a(a);
        return a;
    }
    
    public int c() {
        return this.c.i();
    }
    
    public void d() {
        this.c.j();
        this.d.j();
    }
    
    public static j e() {
        return (j)j.b.a();
    }
    
    public void f() {
        j.b.a(this);
    }
    
    public void g() {
        this.c = i.k();
        this.d = i.k();
    }
    
    public void h() {
        this.c.f();
        this.c = null;
        this.d.f();
        this.d = null;
    }
    
    public String toString() {
        final lb a = lb.a();
        a.c(j.z[2]);
        for (int i = 0; i < this.c(); ++i) {
            a.c(j.z[1]);
            a.d(i);
            a.c(j.z[0]);
            a.a(this.a(i));
            a.c(j.z[3]);
            a.a(this.b(i));
            a.c("\n");
        }
        a.c("]");
        return a.c();
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
        final char[] charArray = ":&".toCharArray();
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
                            c2 = '\u0014';
                            break;
                        }
                        case 1: {
                            c2 = '\u0006';
                            break;
                        }
                        case 2: {
                            c2 = '4';
                            break;
                        }
                        case 3: {
                            c2 = 'e';
                            break;
                        }
                        default: {
                            c2 = '\\';
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
        final char[] charArray2 = "4&".toCharArray();
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
                            c4 = '\u0014';
                            break;
                        }
                        case 1: {
                            c4 = '\u0006';
                            break;
                        }
                        case 2: {
                            c4 = '4';
                            break;
                        }
                        case 3: {
                            c4 = 'e';
                            break;
                        }
                        default: {
                            c4 = '\\';
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
        final char[] charArray3 = "OvU\f.g<>".toCharArray();
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
                            c6 = '\u0014';
                            break;
                        }
                        case 1: {
                            c6 = '\u0006';
                            break;
                        }
                        case 2: {
                            c6 = '4';
                            break;
                        }
                        case 3: {
                            c6 = 'e';
                            break;
                        }
                        default: {
                            c6 = '\\';
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
        final char[] charArray4 = "8&".toCharArray();
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
                            c8 = '\u0014';
                            break;
                        }
                        case 1: {
                            c8 = '\u0006';
                            break;
                        }
                        case 2: {
                            c8 = '4';
                            break;
                        }
                        case 3: {
                            c8 = 'e';
                            break;
                        }
                        default: {
                            c8 = '\\';
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
        final char[] charArray5 = "zcU\u0011r~".toCharArray();
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
                            c10 = '\u0014';
                            break;
                        }
                        case 1: {
                            c10 = '\u0006';
                            break;
                        }
                        case 2: {
                            c10 = '4';
                            break;
                        }
                        case 3: {
                            c10 = 'e';
                            break;
                        }
                        default: {
                            c10 = '\\';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 <= n20) {
                z[n17] = new String(charArray5).intern();
                j.z = z;
                j.b = new neat.system.f((j.e != null) ? j.e : (j.e = a(j.z[4])));
                return;
            }
            continue;
        }
    }
}
