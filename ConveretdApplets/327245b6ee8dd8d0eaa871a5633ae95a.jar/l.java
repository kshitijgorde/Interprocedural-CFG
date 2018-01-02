import java.util.Enumeration;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

final class l extends m
{
    am a;
    int[] b;
    int c;
    int d;
    int e;
    int[][] f;
    int g;
    boolean h;
    static Vector i;
    int j;
    int k;
    boolean l;
    Vector m;
    
    l(final am a) {
        this.e = 0;
        this.f = new int[10][];
        this.k = -1;
        this.l = false;
        this.m = new Vector(0, 10);
        super.a = 1;
        this.a = a;
    }
    
    l(final int[] b, final int c, final int d) {
        this.e = 0;
        this.f = new int[10][];
        this.k = -1;
        this.l = false;
        this.m = new Vector(0, 10);
        super.a = 1;
        this.b = b;
        this.f[0] = b;
        this.g = 1;
        this.c = c;
        this.d = d;
        this.h = false;
    }
    
    void a() {
        final boolean l = c.l;
        super.a();
        this.b = null;
        int n = 0;
        while (true) {
            while (true) {
                Label_0029: {
                    if (!l) {
                        break Label_0029;
                    }
                    final l i = this;
                    i.f[n] = null;
                    ++n;
                }
                if (n < this.f.length) {
                    continue;
                }
                break;
            }
            this.g = 0;
            this.h = false;
            final l i = this;
            if (l) {
                continue;
            }
            break;
        }
        if (this.a != null) {
            this.a.a();
        }
        this.a = null;
        l.i.removeElement(this);
        while (true) {
            while (true) {
                Label_0096: {
                    if (!l) {
                        break Label_0096;
                    }
                    final Object firstElement = this.m.firstElement();
                    ((b)firstElement).ac();
                }
                if (this.m.size() > 0) {
                    continue;
                }
                break;
            }
            this.m.removeAllElements();
            final Object firstElement = this;
            if (!l) {
                this.m = null;
                return;
            }
            continue;
        }
    }
    
    void b() {
        final boolean l = c.l;
        int n = 0;
        int c;
        int d;
        int[] array;
        int n2;
        int n3;
        int n4;
        int[] array2;
        int n5;
        int[] array3;
        int n6;
        int n7;
        int[] array4;
        int n8;
        int[] array5;
        int n9;
        Label_0077_Outer:Label_0112_Outer:
        while (true) {
            Label_0150: {
                if (!l) {
                    break Label_0150;
                }
                c = this.c(n);
                d = this.d(n);
                array = this.f[n];
                n2 = 0;
                n3 = c - 1;
                n4 = 0;
                while (true) {
                    while (true) {
                        Label_0083: {
                            if (!l) {
                                break Label_0083;
                            }
                            array2 = array;
                            n5 = n2;
                            array2[n5] &= Integer.MAX_VALUE;
                            array3 = array;
                            n6 = n3;
                            array3[n6] &= Integer.MAX_VALUE;
                            ++n4;
                            n2 += c;
                            n3 += c;
                        }
                        if (n4 < d) {
                            continue Label_0077_Outer;
                        }
                        break;
                    }
                    n2 = 0;
                    n3 = (d - 1) * c;
                    n7 = 0;
                    if (l) {
                        continue Label_0112_Outer;
                    }
                    break;
                }
                while (true) {
                    Label_0141: {
                        if (!l) {
                            break Label_0141;
                        }
                        array4 = array;
                        n8 = n2;
                        array4[n8] &= Integer.MAX_VALUE;
                        array5 = array;
                        n9 = n3;
                        array5[n9] &= Integer.MAX_VALUE;
                        ++n7;
                        ++n2;
                        ++n3;
                    }
                    if (n7 < c) {
                        continue;
                    }
                    break;
                }
                ++n;
            }
            if (n >= this.g) {
                return;
            }
            continue;
        }
    }
    
    void c() {
        this.h = false;
    }
    
    void a(int g) {
        if (this.g < g) {
            (this.f = new int[10][])[0] = this.b;
            int n = 1;
            while (true) {
                Label_0075: {
                    if (!c.l) {
                        break Label_0075;
                    }
                    this.f[n] = this.a(this.c(n), this.d(n));
                    if (this.f[n] == null) {
                        g = n;
                    }
                    ++n;
                }
                if (n < g) {
                    continue;
                }
                break;
            }
        }
        this.g = g;
    }
    
    void b(final int n) {
        final boolean l = c.l;
        this.a(n);
        int n2 = 1;
        while (true) {
            Label_0343: {
                if (!l) {
                    break Label_0343;
                }
                final int c = this.c(n2);
                final int d = this.d(n2);
                final int[] array = this.f[n2];
                final int[] array2 = this.f[n2 - 1];
                final int c2 = this.c(n2 - 1);
                final int d2 = this.d(n2 - 1);
                final int n3 = c;
                final int n4 = d;
                int n5 = 0;
                while (true) {
                    Label_0334: {
                        if (!l) {
                            break Label_0334;
                        }
                        int n6 = n5 * c2 << 1;
                        int n7 = n5 * n3;
                        int n8 = 0;
                        while (true) {
                            Label_0325: {
                                if (!l) {
                                    break Label_0325;
                                }
                                final int n9 = array2[n6];
                                int n12 = 0;
                                int n11 = 0;
                                int n10 = 0;
                                Label_0240: {
                                    if (n8 == c2 >> 1 && n5 == d2 >> 1) {
                                        n10 = (n11 = (n12 = n9));
                                        if (!l) {
                                            break Label_0240;
                                        }
                                    }
                                    if (n8 == c2 >> 1) {
                                        n10 = array2[n6 + c2];
                                        n11 = n9;
                                        n12 = n10;
                                        if (!l) {
                                            break Label_0240;
                                        }
                                    }
                                    if (n5 == d2 >> 1) {
                                        n11 = array2[n6 + 1];
                                        n10 = n9;
                                        n12 = n11;
                                        if (!l) {
                                            break Label_0240;
                                        }
                                    }
                                    n11 = array2[n6 + 1];
                                    n10 = array2[n6 + c2];
                                    n12 = array2[n6 + c2 + 1];
                                }
                                array[n7] = ((n9 >> 2 & 0x3FC03FC0) + (n11 >> 2 & 0x3FC03FC0) + (n10 >> 2 & 0x3FC03FC0) + (n12 >> 2 & 0x3FC03FC0) & 0xFF00FF00) + ((n9 & 0xFF00FF) + (n11 & 0xFF00FF) + (n10 & 0xFF00FF) + (n12 & 0xFF00FF) >> 2 & 0xFF00FF);
                                ++n8;
                                n6 += 2;
                                ++n7;
                            }
                            if (n8 < n3) {
                                continue;
                            }
                            break;
                        }
                        ++n5;
                    }
                    if (n5 < n4) {
                        continue;
                    }
                    break;
                }
                ++n2;
            }
            if (n2 >= this.g) {
                return;
            }
            continue;
        }
    }
    
    int c(final int n) {
        return this.c + (1 << n) - 1 >> n;
    }
    
    int d(final int n) {
        return this.d + (1 << n) - 1 >> n;
    }
    
    final int d() {
        final t b = super.b;
        this.j = blaze3d.c;
        if (this.b == null) {
            this.a.a(this);
            this.k = this.j;
        }
        return this.k;
    }
    
    void e() {
        ++this.e;
    }
    
    void f() {
        final boolean l = c.l;
        --this.e;
        if (this.e == 0 && this.a != null) {
            this.b = null;
            int n = 0;
            while (true) {
                while (true) {
                    Label_0049: {
                        if (!l) {
                            break Label_0049;
                        }
                        final l i = this;
                        i.f[n] = null;
                        ++n;
                    }
                    if (n < this.f.length) {
                        continue;
                    }
                    break;
                }
                this.h = false;
                final l i = this;
                if (l) {
                    continue;
                }
                break;
            }
            this.g = 0;
        }
    }
    
    void g() {
        this.b = this.a(this.c, this.d);
        l.i.addElement(this);
    }
    
    int[] a(final int n, final int n2) {
        try {
            return new int[n * n2];
        }
        catch (OutOfMemoryError outOfMemoryError) {
            if (!h()) {
                return null;
            }
            return new int[n * n2];
        }
    }
    
    static synchronized boolean h() {
        int c = blaze3d.c;
        l l = null;
        final Enumeration<l> elements = (Enumeration<l>)l.i.elements();
        while (elements.hasMoreElements()) {
            final l i = elements.nextElement();
            final int j = i.j;
            int k = 0;
            while (k < c) {
                l = i;
                k = i.j;
                if (!c.l) {
                    c = k;
                    break;
                }
            }
        }
        if (l == null) {
            return false;
        }
        l.a();
        l.i.removeElement(l);
        return true;
    }
    
    static {
        l.i = new Vector(10, 10);
    }
}
