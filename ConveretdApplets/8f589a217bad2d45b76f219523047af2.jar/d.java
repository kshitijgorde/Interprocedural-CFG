import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class d
{
    public l[] p;
    public l[] d;
    public l[] a;
    public l[] n;
    public int p;
    public int d;
    public int a;
    public int n;
    public int v;
    public int i;
    public int l;
    public int b;
    private static p[] p;
    
    public final void d(final double n, final double n2, final double n3) {
        for (int i = 0; i < this.p; ++i) {
            final l l = this.p[i];
            l.p *= n;
            final l j = this.p[i];
            j.d *= n2;
            final l k = this.p[i];
            k.a *= n3;
        }
    }
    
    public final void p(final double n, final double n2, final double n3, final double n4, final int[] array, final int n5, final int n6, final int[] array2, final int n7, final int n8, final int[] array3, final boolean b, final int n9, final boolean b2) {
        final double n10 = 0.20000000298023224;
        final double n11 = 2.5;
        this.a = this.d;
        for (int i = 0; i < this.d; ++i) {
            this.a[i].p(this.d[i]);
            this.a[i].p(-n * 0.017453292519943295);
            this.a[i].d(n2 * 0.017453292519943295);
            this.a[i].p(n10, n11, n10 * Math.tan(n3 * 0.017453292519943295 / 2.0), n10 * Math.tan(n4 * 0.017453292519943295 / 2.0));
        }
        this.n = this.p(this.a, this.n, this.a, 0);
        this.a = this.p(this.n, this.a, this.n, 1);
        this.n = this.p(this.a, this.n, this.a, 3);
        this.a = this.p(this.n, this.a, this.n, 2);
        this.n = this.p(this.a, this.n, this.a, 4);
        for (int j = 0; j < this.n; ++j) {
            this.n[j].p();
        }
        final double n12 = n7;
        final double n13 = n8;
        for (int k = 0; k < this.n; ++k) {
            if (this.n[k].p < -1.0 && Math.abs(this.n[k].p + 1.0) < 1.0E-5) {
                this.n[k].p = -1.0;
            }
            if (this.n[k].p > 1.0 && Math.abs(this.n[k].p - 1.0) < 1.0E-5) {
                this.n[k].p = 1.0;
            }
            if (this.n[k].d < -1.0 && Math.abs(this.n[k].d + 1.0) < 1.0E-5) {
                this.n[k].d = -1.0;
            }
            if (this.n[k].d > 1.0 && Math.abs(this.n[k].d - 1.0) < 1.0E-5) {
                this.n[k].d = 1.0;
            }
            this.n[k].p = (this.n[k].p + 1.0) / 2.0 * (n12 - 0.5);
            this.n[k].d = (this.n[k].d + 1.0) / 2.0 * (n13 - 0.5);
            this.n[k].d = n13 - 0.5 - this.n[k].d;
            this.n[k].p = this.p(this.n[k].p);
            this.n[k].d = this.p(this.n[k].d);
            this.n[k].a = this.p(this.n[k].a);
        }
        if (d.p != null) {
            this.p(this.n, this.n, array, n5, n6, array2, n7, n8, array3, b, n9, b2);
        }
    }
    
    public final void p(final l l, final l i, final l j, final int n) {
        final l k = new l();
        double n2 = 0.0;
        i.p(k, l);
        switch (n) {
            case 0: {
                n2 = -l.a / k.a;
                break;
            }
            case 1: {
                n2 = (-l.p - l.n) / (k.p + k.n);
                break;
            }
            case 2: {
                n2 = (l.n - l.p) / (k.p - k.n);
                break;
            }
            case 3: {
                n2 = (l.n - l.d) / (k.d - k.n);
                break;
            }
            case 4: {
                n2 = (-l.d - l.n) / (k.d + k.n);
                break;
            }
        }
        k.p(j, n2);
        j.d(j, l);
    }
    
    public final void p(final l l, final l i) {
        if (l.d < i.d) {
            d.p[(int)Math.ceil(l.d)].p(l, i);
            return;
        }
        d.p[(int)Math.ceil(i.d)].p(i, l);
    }
    
    public d() {
        this.p = 4;
        this.b = 32;
        this.p = new l[10];
        this.d = new l[10];
        this.a = new l[10];
        this.n = new l[10];
        int n = 0;
        do {
            this.p[n] = new l();
            this.d[n] = new l();
            this.a[n] = new l();
            this.n[n] = new l();
        } while (++n < 10);
    }
    
    public final void p(final double n, final double n2, final double n3) {
        for (int i = 0; i < this.p; ++i) {
            final l l = this.p[i];
            l.p += n;
            final l j = this.p[i];
            j.d += n2;
            final l k = this.p[i];
            k.a += n3;
        }
    }
    
    public final boolean p(final l l, final int n) {
        switch (n) {
            case 0: {
                return l.a >= 0.0;
            }
            case 1: {
                return l.p + l.n >= 0.0;
            }
            case 2: {
                return l.p - l.n <= 0.0;
            }
            case 3: {
                return l.d - l.n <= 0.0;
            }
            case 4: {
                return l.d + l.n >= 0.0;
            }
            default: {
                return false;
            }
        }
    }
    
    public final double p(final double n) {
        if (n != 0.0 && Math.abs(n) < 1.0E-4) {
            return 0.0;
        }
        return n;
    }
    
    private final void p(final int[] array, final int n, final int n2, final int[] array2, final int n3, final int n4, final a a, final a a2, final int n5, final int[] array3, final boolean b, final int n6, final boolean b2) {
        int n7 = 0;
        final int n8 = (int)Math.ceil(a.d);
        final int n9 = (int)Math.ceil(a2.d) - 1 - n8 + 1;
        final double n10 = a2.d - a.d;
        if (n9 <= 0) {
            return;
        }
        int n11;
        int n12;
        if (b) {
            n11 = n3 - 2;
            n12 = n4 - 2;
        }
        else {
            n11 = n3 - 1;
            n12 = n4 - 1;
        }
        double n13 = a.n;
        double v = a.v;
        double i = a.i;
        final double n14 = (a2.n - n13) / n10;
        final double n15 = (a2.v - v) / n10;
        final double n16 = (a2.i - i) / n10;
        final double n17 = n8 - a.d;
        if (n17 > 0.0) {
            n13 += n17 * n14;
            v += n17 * n15;
            i += n17 * n16;
        }
        int n18 = n * n2 + n8;
        final int n19 = n3 * n3 * this.i;
        final double n20 = n14 * n5;
        final double n21 = n15 * n5;
        final double n22 = n16 * n5;
        int n23 = n9 / n5;
        final int n24 = n9 - n23 * n5;
        final double n25 = 1.0 / i;
        double n26 = n13 * n25;
        double n27 = v * n25;
        if (n26 > 1.0) {
            n26 = 1.0;
        }
        if (n27 > 1.0) {
            n27 = 1.0;
        }
        if (n26 < 0.0) {
            n26 = 0.0;
        }
        if (n27 < 0.0) {
            n27 = 0.0;
        }
        double n28 = n26 * n11;
        double n29 = n27 * n12;
        if (n24 > 0) {
            ++n23;
        }
        while (n23-- > 0) {
            int n30;
            if (n24 > 0 && n23 == 0) {
                n30 = n24;
            }
            else {
                n30 = n5;
            }
            n13 += n14 * n30;
            v += n15 * n30;
            i += n16 * n30;
            final double n31 = 1.0 / i;
            double n32 = n13 * n31 * n11;
            double n33 = v * n31 * n12;
            if (n32 > n11) {
                n32 = n11;
            }
            if (n33 > n12) {
                n33 = n12;
            }
            if (n32 < 0.0) {
                n32 = 0.0;
            }
            if (n33 < 0.0) {
                n33 = 0.0;
            }
            int n34 = (int)((n32 - n28) / n30 * 65536.0);
            int n35 = (int)((n33 - n29) / n30 * 65536.0);
            int n36 = (int)(n28 * 65536.0);
            int n37 = (int)(n29 * 65536.0);
            if (n34 < 0) {
                ++n34;
            }
            if (n35 < 0) {
                ++n35;
            }
            final int n38 = n6 << 24;
            if (!b) {
                if (array3 != null) {
                    final int n39 = this.l * 24 * 24;
                    while (n30-- > 0) {
                        final int n40 = (n37 >> 16) * n3 + (n36 >> 16);
                        n7 = array3[n40 + n39];
                        if (n7 != 0) {
                            if (b2) {
                                array[n18] = ((array[n18] & 0xFFFFFF) | n38);
                            }
                            else {
                                int p13 = array2[n40 + n19];
                                if (n7 != 255) {
                                    p13 = this.p(p13, array[n18], n7, n38);
                                }
                                array[n18] = p13;
                            }
                        }
                        ++n18;
                        n36 += n34;
                        n37 += n35;
                    }
                }
                else {
                    while (n30-- > 0) {
                        array[n18++] = array2[(n37 + 32768 >> 16) * n3 + (n36 + 32768 >> 16) + n19];
                        n36 += n34;
                        n37 += n35;
                    }
                }
            }
            else {
                final int n41 = this.l * 24 * 24;
                while (n30-- > 0) {
                    final int n42 = (n37 >> 16) * n3 + (n36 >> 16);
                    if (array3 != null) {
                        n7 = array3[(n37 + 32768 >> 16) * n3 + (n36 + 32768 >> 16) + n41];
                        if (n7 == 0) {
                            ++n18;
                            n36 += n34;
                            n37 += n35;
                            continue;
                        }
                    }
                    if (b2) {
                        array[n18++] = ((array[n18] & 0xFFFFFF) | n38);
                        n36 += n34;
                        n37 += n35;
                    }
                    else {
                        final int n43 = n42 + n19;
                        final int n44 = (n36 & 0xFFFF) >> 8;
                        final int n45 = (n37 & 0xFFFF) >> 8;
                        final int n46 = (255 - n44) * (255 - n45);
                        final int n47 = (255 - n45) * n44;
                        final int n48 = (255 - n44) * n45;
                        final int n49 = n44 * n45;
                        final int n50 = array2[n43];
                        final int n51 = n50 & 0xFF;
                        final int n52 = (n50 & 0xFF00) >> 8;
                        final int n53 = (n50 & 0xFF0000) >> 16;
                        final int n54 = array2[n43 + 1];
                        final int n55 = n54 & 0xFF;
                        final int n56 = (n54 & 0xFF00) >> 8;
                        final int n57 = (n54 & 0xFF0000) >> 16;
                        final int n58 = n43 + n3;
                        final int n59 = array2[n58];
                        final int n60 = n59 & 0xFF;
                        final int n61 = (n59 & 0xFF00) >> 8;
                        final int n62 = (n59 & 0xFF0000) >> 16;
                        final int n63 = array2[n58 + 1];
                        int p14 = (n46 * n51 + n47 * n55 + n48 * n60 + n49 * (n63 & 0xFF) & 0xFF0000) >> 16 | (n46 * n52 + n47 * n56 + n48 * n61 + n49 * ((n63 & 0xFF00) >> 8) & 0xFF0000) >> 8 | (n46 * n53 + n47 * n57 + n48 * n62 + n49 * ((n63 & 0xFF0000) >> 16) & 0xFF0000) | n38;
                        if (array3 != null && n7 != 255) {
                            p14 = this.p(p14, array[n18], n7, n38);
                        }
                        array[n18++] = p14;
                        n36 += n34;
                        n37 += n35;
                    }
                }
            }
            n28 = n32;
            n29 = n33;
        }
    }
    
    public final int p(final l[] array, final l[] array2, final int n, final int n2) {
        if (n <= 0) {
            return 0;
        }
        final l l = new l();
        int n3 = 0;
        l i = array[n - 1];
        for (final l k : array) {
            if (this.p(k, n2)) {
                if (this.p(i, n2)) {
                    n3 = this.p(k, array2, n3);
                }
                else {
                    this.p(i, k, l, n2);
                    n3 = this.p(k, array2, this.p(l, array2, n3));
                }
            }
            else if (this.p(i, n2)) {
                this.p(i, k, l, n2);
                n3 = this.p(l, array2, n3);
            }
            i = k;
        }
        return n3;
    }
    
    public final void p() {
        for (int i = 0; i < this.p; ++i) {
            this.d[i].p(this.p[i]);
        }
        this.d = this.p;
    }
    
    public final void p(final double n, final double n2) {
        for (int i = 0; i < this.p; ++i) {
            this.d[i].p(this.p[i]);
            this.d[i].d(-n2 * 0.017453292519943295);
            this.d[i].p(n * 0.017453292519943295);
        }
        this.d = this.p;
    }
    
    public final int p(final l l, final l[] array, int n) {
        array[n++].p(l);
        return n;
    }
    
    protected final int p(int n, final int n2, int n3, final int n4) {
        final int n5 = (n & 0xFF) * n3;
        final int n6 = ((n & 0xFF00) >> 8) * n3;
        final int n7 = ((n & 0xFF0000) >> 16) * n3;
        n3 = 255 - n3;
        n = (((n2 & 0xFF) * n3 + n5 & 0xFF00) >> 8 | (((n2 & 0xFF00) >> 8) * n3 + n6 & 0xFF00) | (((n2 & 0xFF0000) >> 16) * n3 + n7 & 0xFF00) << 8 | n4);
        return n;
    }
    
    public final void p(final l[] array, final int n, final int[] array2, final int n2, final int n3, final int[] array3, final int n4, final int n5, final int[] array4, final boolean b, final int n6, final boolean b2) {
        if (n <= 0) {
            return;
        }
        this.p(array[n - 1], array[0]);
        for (int i = 0; i < n - 1; ++i) {
            this.p(array[i], array[i + 1]);
        }
        final Vector vector = new Vector<a>();
        int j;
        for (j = 0; j < n5 && d.p[j].p == 0; ++j) {}
        vector.removeAllElements();
        while (j < n5) {
            if (d.p[j].p > 0) {
                int n7 = 0;
                for (int k = 0; k < d.p[j].p.size(); ++k) {
                    final a a = d.p[j].p.elementAt(k);
                    if (n7 < vector.size()) {
                        for (a a2 = vector.elementAt(n7); a2.d < a.d && n7 < vector.size(); a2 = vector.elementAt(n7), ++n7) {}
                    }
                    vector.insertElementAt(a, n7);
                }
                d.p[j].p.removeAllElements();
                d.p[j].p = 0;
            }
            int n8 = 0;
            for (int l = 0; l < vector.size(); ++l) {
                if ((n8 & 0x1) == 0x0) {
                    final a a3 = vector.elementAt(l);
                    if (++l == vector.size()) {
                        break;
                    }
                    this.p(array3, n4, j, array2, n2, n3, a3, vector.elementAt(l), this.b, array4, b, n6, b2);
                }
                ++n8;
            }
            ++j;
            for (int n9 = vector.size() - 1; n9 >= 0; --n9) {
                if (Math.ceil(vector.elementAt(n9).p) == j) {
                    vector.removeElementAt(n9);
                }
                else {
                    final a a5;
                    final a a4 = a5 = vector.elementAt(n9);
                    a5.d += a4.a;
                    final a a6 = a4;
                    a6.n += a4.l;
                    final a a7 = a4;
                    a7.v += a4.b;
                    final a a8 = a4;
                    a8.i += a4.c;
                }
            }
        }
    }
    
    public static final void p(final int n) {
        if (d.p == null) {
            d.p = new p[n + 1];
            for (int i = 0; i < n + 1; ++i) {
                d.p[i] = new p();
            }
        }
    }
}
