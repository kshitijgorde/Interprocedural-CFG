// 
// Decompiled by Procyon v0.5.30
// 

package ji.filter;

import ji.v1event.af;
import ji.annotate.df;
import ji.document.ad;
import ji.util.i;
import ji.util.d;
import ji.io.ac;
import ji.image.dx;
import ji.v1event.a6;

public class ey extends cj
{
    boolean a;
    protected a6 b;
    
    public ey() {
        this.a = false;
        this.b = null;
    }
    
    public final dx loadImageHeaderInternal(final fh fh) throws Exception {
        final dx dx = new dx();
        dx.l = "STUB";
        dx.u = 1000;
        dx.i = 1000L;
        dx.m = 1400;
        dx.n = 1800;
        dx.am = 24;
        dx.an = 0;
        dx.ar = false;
        dx.at = 0;
        return dx;
    }
    
    public final int[] getPalette(final ac ac, final dx dx, final String s) throws Exception {
        int[] array = null;
        if (dx.am == 4) {
            array = new int[16];
            for (int i = 0; i < array.length; ++i) {
                array[i] = -9408400;
            }
        }
        else if (dx.am == 8) {
            array = new int[256];
            for (int j = 0; j < array.length; ++j) {
                array[j] = -9408400;
            }
        }
        return array;
    }
    
    public final void fillDibInternal(final fh fh) throws Exception {
        try {
            super.c = true;
            this.a = false;
            final dx d = fh.d;
            if (fh.g != null) {
                if (this.b == null) {
                    this.b = new a6(this, 4, "");
                }
                if (d.am >= 24 && ji.util.d.du() && d.ak) {
                    this.b = new a6(this, 23, "");
                }
            }
            int n = 0;
            final int max = Math.max(d.n / 10, 1);
            switch (fh.d.am) {
                case 1: {
                    fh.c.b(1, true, fh.o);
                    final byte[] array = new byte[d.m * d.n];
                    final byte[] array2 = new byte[d.m];
                    try {
                        final int n2 = d.n;
                        boolean b = false;
                        int n3 = 10;
                        for (int i = 0; i < array.length; ++i) {
                            if (b) {
                                array[i] = 1;
                            }
                            else {
                                array[i] = 0;
                            }
                            if (--n3 <= 0) {
                                b = !b;
                                n3 = n3;
                            }
                        }
                        for (int j = 0; j < d.n; ++j) {
                            System.arraycopy(array, j * d.m, array2, 0, d.m);
                            final short[] d2 = ji.util.d.d(array2);
                            if (fh.c.j()) {
                                final int[] array3 = new int[d2.length];
                                for (int k = 0; k < d2.length; ++k) {
                                    array3[k] = d2[k];
                                }
                                fh.c.a(array3, array3.length, j + 1, false, true);
                            }
                            else {
                                fh.c.a(d2, d2.length);
                            }
                            if (--n <= 0 && fh.g != null) {
                                this.b.a("".concat(String.valueOf(String.valueOf(100 * j / n2))));
                                fh.g.a(this.b);
                                n = max;
                            }
                        }
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    if (fh.g != null) {
                        this.b.a("100");
                        fh.g.a(this.b);
                        break;
                    }
                    break;
                }
                case 4: {
                    fh.c.b(2, true, fh.o);
                    final byte[] array4 = new byte[d.m * d.n];
                    boolean b2 = false;
                    int n4 = 10;
                    for (int l = 0; l < array4.length; ++l) {
                        if (b2) {
                            array4[l] = 16;
                        }
                        else {
                            array4[l] = 0;
                        }
                        if (--n4 <= 0) {
                            b2 = !b2;
                            n4 = n4;
                        }
                    }
                    final byte[] ag = fh.c.ag();
                    try {
                        int n5 = d.m / 2;
                        final int n6 = d.m % 2;
                        if (n6 > 0) {
                            n5 += 2 - n6;
                        }
                        int n7 = n5;
                        final int n8 = n7 % 4;
                        if (n8 > 0) {
                            n7 += 4 - n8;
                        }
                        for (int n9 = 0; n9 < d.n && !this.a; ++n9) {
                            System.arraycopy(array4, n9 * n7, ag, (d.n - n9 - 1) * n5, n5);
                        }
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                    if (fh.g != null) {
                        this.b.a("100");
                        fh.g.a(this.b);
                        break;
                    }
                    break;
                }
                case 8: {
                    fh.c.b(3, true, fh.o);
                    final byte[] array5 = new byte[d.m * d.n];
                    boolean b3 = false;
                    int n10 = 10;
                    for (int n11 = 0; n11 < array5.length; ++n11) {
                        if (b3) {
                            array5[n11] = -1;
                        }
                        else {
                            array5[n11] = 0;
                        }
                        if (--n10 <= 0) {
                            b3 = !b3;
                            n10 = n10;
                        }
                    }
                    final byte[] ag2 = fh.c.ag();
                    try {
                        int m = d.m;
                        final int n12 = m % 4;
                        if (n12 > 0) {
                            m += 4 - n12;
                        }
                        final int m2 = d.m;
                        for (int n13 = 0; n13 < d.n && !this.a; ++n13) {
                            System.arraycopy(array5, n13 * m, ag2, (d.n - n13 - 1) * m2, m2);
                        }
                    }
                    catch (Exception ex4) {}
                    if (fh.g != null) {
                        this.b.a("100");
                        fh.g.a(this.b);
                        break;
                    }
                    break;
                }
                case 24: {
                    fh.c.b(4, false, fh.o);
                    if (i.c(87)) {
                        fh.c.e(true);
                    }
                    int n14 = d.m * 3;
                    final int n15 = n14 % 4;
                    if (n15 > 0) {
                        n14 += 4 - n15;
                    }
                    final int[] array6 = new int[d.m];
                    int min = Math.min(Math.max(d.n / 15, 16), d.n);
                    try {
                        final int m3 = d.m;
                        for (int n16 = d.n, n17 = 0; n17 < n16 && !this.a; ++n17) {
                            for (int n18 = 0; n18 < m3; ++n18) {
                                array6[n18] = (16777215 * n17 * n18 / (n16 * m3) | 0xFF000000);
                            }
                            fh.c.a(array6, d.m, fh.o, n17 + 1, n17 + 1, true);
                            if (--min <= 0 && fh.g != null) {
                                this.b.a("".concat(String.valueOf(String.valueOf(100 * n17 / n16))));
                                fh.g.a(this.b);
                                min = min;
                            }
                        }
                        if (fh.g != null) {
                            this.b.a("100");
                            fh.g.a(this.b);
                        }
                    }
                    catch (Exception ex3) {
                        if (ji.util.d.cy()) {
                            ex3.printStackTrace();
                        }
                    }
                    finally {
                        if (array6 != null) {
                            fh.c.e(fh.o);
                        }
                    }
                    break;
                }
            }
        }
        finally {
            super.c = false;
        }
    }
    
    public final String getFilterName() {
        return "jiFilterStub";
    }
    
    public void close(final dx dx, final ad ad) {
    }
    
    public boolean isAborted(final dx dx, final String s) {
        return this.a;
    }
    
    public void abort(final dx dx) {
        this.a = true;
    }
    
    public void clearAbort(final dx dx, final String s) {
        this.a = false;
    }
    
    public boolean d() {
        return super.c;
    }
    
    public boolean e() {
        return false;
    }
    
    public df a(final ac ac, final df df, final dx dx, final af af, final ad ad, final String s, final int n, final boolean b, final int n2) throws Exception {
        return df;
    }
    
    public boolean f() {
        return false;
    }
}
