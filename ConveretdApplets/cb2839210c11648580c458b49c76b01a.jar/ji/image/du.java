// 
// Decompiled by Procyon v0.5.30
// 

package ji.image;

import ji.util.e;
import java.awt.Component;
import ji.util.d;
import ji.awt.c;
import ji.io.ac;
import ji.io.q;
import java.awt.image.ColorModel;

public class du
{
    private boolean a;
    private int[] b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private static int h;
    private double i;
    private double j;
    private double k;
    private ColorModel l;
    private String m;
    private q n;
    private ac o;
    private boolean p;
    private byte[] q;
    private boolean r;
    private int[] s;
    private int[] t;
    private long u;
    private long v;
    private long w;
    private long x;
    private boolean y;
    private int z;
    private int aa;
    private int[] ab;
    private int[] ac;
    private int[] ad;
    private int ae;
    private boolean af;
    private c ag;
    private String ah;
    private int ai;
    private long aj;
    
    public static long a(final long n) {
        return n / 4;
    }
    
    public int a() {
        return this.ai;
    }
    
    public static boolean a(final long n, final int n2, final int n3, final boolean b, final String s) {
        boolean b2 = false;
        if (n2 * n3 > n && d.di() != 1 && !d.a(b, s)) {
            b2 = true;
        }
        return b2;
    }
    
    public du(final int c, final int d, final Component component, final long aj, final String ah, final boolean b, final int ai, final boolean b2) {
        this.a = false;
        this.b = null;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.i = 1.0;
        this.j = 1.0;
        this.k = 1.0;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = false;
        this.q = null;
        this.r = true;
        this.s = null;
        this.t = null;
        this.u = 1048576L;
        this.v = this.u / 2;
        this.w = this.u;
        this.x = 0L;
        this.y = false;
        this.z = 0;
        this.aa = 0;
        this.ab = null;
        this.ac = null;
        this.ad = null;
        this.ae = -1;
        this.af = true;
        this.ag = new c("jiRamLineInts1");
        this.ah = null;
        this.ai = 0;
        try {
            this.g = du.h++;
            this.ah = ah;
            this.c = c;
            this.d = d;
            this.ai = ai;
            this.aj = aj;
            this.u = a(aj);
            this.w = this.u;
            this.v = this.u / 2;
            if (c * d < 2) {
                this.r = false;
            }
            else if (b2 || a(this.w, c, d, b, ah)) {
                this.y = true;
                this.n = ji.io.q.a(component, ah);
                this.m = this.n.o();
                this.ag.c(this.m);
                this.b(component);
                this.q = new byte[d * 4];
                ji.util.e.b(this.m);
            }
            else {
                this.b = new int[c * d];
            }
        }
        catch (Exception ex) {
            if (ji.util.d.cy() || this.a) {
                ex.printStackTrace();
            }
        }
    }
    
    public du(final String s, final int c, final int d, final Component component, final long n, final String ah, final boolean b, final int ai) {
        this.a = false;
        this.b = null;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.i = 1.0;
        this.j = 1.0;
        this.k = 1.0;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = false;
        this.q = null;
        this.r = true;
        this.s = null;
        this.t = null;
        this.u = 1048576L;
        this.v = this.u / 2;
        this.w = this.u;
        this.x = 0L;
        this.y = false;
        this.z = 0;
        this.aa = 0;
        this.ab = null;
        this.ac = null;
        this.ad = null;
        this.ae = -1;
        this.af = true;
        this.ag = new c("jiRamLineInts1");
        this.ah = null;
        this.ai = 0;
        try {
            this.g = du.h++;
            this.ah = ah;
            this.c = c;
            this.d = d;
            this.ai = ai;
            final long u = n / 4;
            this.u = u;
            if (ji.util.d.em()) {
                this.w = this.u * 3;
            }
            this.w = this.u;
            this.v = u / 2;
            this.y = false;
            final int n2 = (int)ji.io.ac.a(s, ah);
            final ac ac = new ac(s, false, false, 0, false, component, ah);
            if (a(this.w, c, d, b, ah)) {
                this.y = true;
            }
            if (this.y) {
                this.q = new byte[d * 4];
                this.n = ji.io.q.a(component, ah);
                this.m = this.n.o();
                this.ag.c(this.m);
                this.b(component);
                ji.util.e.b(this.m);
                int min = Math.min(102400, n2);
                final byte[] array = new byte[min];
                int i = 0;
                while (i < n2) {
                    i += ac.a(array, 0, min);
                    this.o.b(array, 0, min);
                    if (n2 - i < min) {
                        min = n2 - i;
                    }
                }
            }
            else {
                ac.a(this.b = new int[n2 / 4]);
            }
            ac.a(component);
        }
        catch (Exception ex) {
            if (ji.util.d.cy() || this.a) {
                ex.printStackTrace();
            }
        }
    }
    
    public final long b() {
        return this.aj;
    }
    
    public final boolean c() {
        return this.y;
    }
    
    private final void b(final Component component) throws Exception {
        if (!this.p || this.af) {
            if (this.o != null) {
                this.o.a((Object)null);
            }
            this.o = new ac(this.m, true, false, 0, false, component, this.ah);
            this.af = false;
            this.p = true;
            this.x = 0L;
        }
    }
    
    private final void c(final Component component) throws Exception {
        if (this.p || this.af) {
            if (this.o != null) {
                this.o.a((Object)null);
            }
            this.o = new ac(this.m, false, true, (int)this.v, false, component, this.ah);
            this.af = false;
            this.p = false;
            this.x = 0L;
        }
    }
    
    public final boolean d() {
        if (this.y) {
            return ji.io.ac.d(this.m, this.ah);
        }
        return this.c * this.d > 0;
    }
    
    public final ColorModel e() {
        return ColorModel.getRGBdefault();
    }
    
    public final int f() {
        return this.d;
    }
    
    public final int g() {
        if (this.i > 0.0) {
            return this.z;
        }
        return this.d;
    }
    
    public final int h() {
        return this.c;
    }
    
    public final int i() {
        if (this.k > 0.0) {
            return this.aa;
        }
        if (this.i > 0.0) {
            return this.aa;
        }
        return this.c;
    }
    
    public final void a(final int n, final int[] array) throws Exception {
        final int n2 = n * this.d;
        if (this.y) {
            if (!this.p) {
                this.b(null);
            }
            if (this.x != n2 * 4) {
                this.o.a((long)(n2 * 4));
                this.x = n2 * 4;
            }
            this.o.b(array);
            this.x += array.length * 4;
        }
        else {
            System.arraycopy(array, 0, this.b, n2, this.d);
        }
    }
    
    public final void a(final int n, final int[] array, final int n2) throws Exception {
        final int n3 = n * this.d;
        if (this.y) {
            if (!this.p) {
                this.b(null);
            }
            if (this.x != n3 * 4) {
                this.o.a((long)(n3 * 4));
                this.x = n3 * 4;
            }
            this.o.b(array, 0, n2);
            this.x += n2 * 4;
        }
        else {
            System.arraycopy(array, 0, this.b, n3, Math.min(array.length, n2));
        }
    }
    
    public final void b(final int n, final int[] array, final int n2) throws Exception {
        final int n3 = n * this.c;
        if (this.y) {
            if (!this.p) {
                this.b(null);
            }
            if (this.x != n3 * 4) {
                this.o.a((long)(n3 * 4));
                this.x = n3 * 4;
            }
            this.o.b(array, 0, n2);
            this.x += n2 * 4;
        }
        else {
            System.arraycopy(array, 0, this.b, n3, Math.min(array.length, n2));
        }
    }
    
    public final void a(final int n, final int[] array, final int n2, final int n3) throws Exception {
        final int n4 = n * this.d;
        int[] array2 = array;
        if (n2 < this.d) {
            final int n5 = this.d * n3;
            array2 = new int[this.d * n3];
            final int min = Math.min(n2 * n3, array2.length);
            for (int i = 0; i < n3; ++i) {
                final int n6 = i * this.d;
                if (n6 < min) {
                    System.arraycopy(array, n6, array2, n6, Math.min(n2, min - n6));
                }
            }
        }
        if (this.y) {
            if (!this.p) {
                this.b(null);
            }
            if (this.x != n4 * 4) {
                this.o.a((long)(n4 * 4));
                this.x = n4 * 4;
            }
            this.o.b(array2, 0, Math.min(array2.length, this.d * n3));
            this.x += this.d * n3 * 4;
        }
        else {
            System.arraycopy(array2, 0, this.b, n4, Math.min(array2.length, n2 * n3));
        }
    }
    
    private final int[] c(final int n, final int n2, final int n3) throws Exception {
        final int min = Math.min(n3 - n2, this.d);
        if (this.s == null) {
            this.s = new int[min];
        }
        else if (this.s.length != min) {
            this.s = new int[min];
        }
        if (n < this.c) {
            if (this.y) {
                final int n4 = n * this.d;
                if (this.p) {
                    this.c(null);
                }
                if (this.x != n4 * 4) {
                    this.o.a((long)(n4 * 4));
                }
                this.o.a(this.q);
                for (int i = 0; i < min; ++i) {
                    final int n5 = (n2 + i) * 4;
                    this.s[i] = ((this.q[n5] & 0xFF) << 24) + ((this.q[n5 + 1] & 0xFF) << 16) + ((this.q[n5 + 2] & 0xFF) << 8) + (this.q[n5 + 3] & 0xFF);
                }
                this.x = this.o.r();
            }
            else {
                System.arraycopy(this.b, n2 + n * this.d, this.s, 0, min);
            }
        }
        return this.s;
    }
    
    public final int[] a(final int n) throws Exception {
        return this.a(n, 0, this.d);
    }
    
    public final int[] a(final int n, final int n2, final int n3) throws Exception {
        return this.b(n, n2, n3);
    }
    
    public final int[] b(final int n, final int n2, final int n3) throws Exception {
        final int min = Math.min(n2, this.z - 1);
        final int min2 = Math.min(n3, this.z - 1);
        int ae = this.ac[Math.min(n, this.aa - 1)];
        int n4 = this.ab[min];
        int n5 = this.ab[min2];
        Math.max(Math.min(n5 - n4 + 1, this.d), 0);
        final int max = Math.max(Math.min(min2 - min + 1, this.z), 0);
        Math.max(max - 1, 0);
        if (this.t == null) {
            this.t = new int[max];
        }
        else if (this.t.length != max) {
            this.t = new int[max];
        }
        try {
            if (this.i != 0.0) {
                if (this.i < 1.0) {
                    this.ae = -1;
                    final int n6 = (int)Math.round(1.0 / (2.0 * this.i));
                    final int n7 = this.d - 1;
                    final int n8 = this.c - 1;
                    final int max2 = Math.max(ae - n6, 0);
                    final int min3 = Math.min(ae + n6, n8);
                    final int n9 = min3 - max2;
                    final int[][] array = new int[n9][0];
                    for (int i = max2; i < min3; ++i) {
                        array[i - max2] = this.c(i, n4, n5 + 1);
                    }
                    final int max3 = Math.max(array[0].length, 0);
                    for (int j = 0; j < n9; ++j) {
                        final int n10 = max2 + j;
                        for (int k = 0; k < max; ++k) {
                            try {
                                final int n11 = this.ab[k + min] - n4;
                                int n12 = 0;
                                final int max4 = Math.max(n11 - n6, 0);
                                final int min4 = Math.min(Math.min(n11 + n6, n7), max3);
                                int n13 = 0;
                                int n14 = 0;
                                int n15 = 0;
                                int n16 = 0;
                                final int max5 = Math.max(n10 - n6, 0);
                                final int min5 = Math.min(n10 + n6, n8);
                                for (int l = max4; l < min4; ++l) {
                                    for (int n17 = max5; n17 < min5; ++n17) {
                                        n13 += (array[j][l] & 0xFF000000) >> 24;
                                        n14 += (array[j][l] & 0xFF0000) >> 16;
                                        n15 += (array[j][l] & 0xFF00) >> 8;
                                        n16 += (array[j][l] & 0xFF);
                                        ++n12;
                                    }
                                }
                                if (n13 != 0) {
                                    n13 = 255;
                                }
                                this.t[k] = (n13 << 24 | n14 / n12 << 16 | n15 / n12 << 8 | n16 / n12);
                            }
                            catch (Exception ex) {
                                ji.util.d.a(ex);
                            }
                        }
                    }
                }
                else {
                    if (this.i == 1.0) {
                        ae = n;
                        n4 = n2;
                        n5 = n3;
                    }
                    try {
                        if (this.ae != ae) {
                            this.ad = this.c(ae, n4, n5 + 1);
                            this.ae = ae;
                        }
                        try {
                            for (int n18 = 0; n18 < max; ++n18) {
                                this.t[n18] = this.ad[this.ab[n18 + min] - n4];
                            }
                        }
                        catch (Exception ex2) {
                            if (this.a) {
                                ex2.printStackTrace();
                                return this.t;
                            }
                            return this.t;
                        }
                    }
                    catch (Exception ex3) {
                        if (this.a) {
                            ex3.printStackTrace();
                            return this.t;
                        }
                        return this.t;
                    }
                }
            }
        }
        catch (Exception ex4) {
            if (ji.util.d.cy() || this.a) {
                ex4.printStackTrace();
            }
        }
        return this.t;
    }
    
    public final String a(final Component component) {
        String o = null;
        try {
            if (this.y) {
                return this.m;
            }
            if (this.n == null) {
                this.n = ji.io.q.a(component, this.ah);
            }
            o = this.n.o();
            this.ag.c(o);
            final ac ac = new ac(o, true, false, 0, false, null, this.ah);
            ac.b(this.b);
            ac.a(component);
        }
        catch (Exception ex) {
            if (ji.util.d.cy() || this.a) {
                ex.printStackTrace();
            }
        }
        return o;
    }
    
    public final int[] j() {
        if (this.y) {
            try {
                if (this.p) {
                    this.c(null);
                }
                final int[] array = new int[this.c * this.d];
                this.x = this.o.a(array) * 4;
                return array;
            }
            catch (Exception ex) {
                if (ji.util.d.cy() || this.a) {
                    ex.printStackTrace();
                }
                return null;
            }
        }
        return this.b;
    }
    
    public final int k() {
        return this.e;
    }
    
    public final int l() {
        return this.f;
    }
    
    public final double m() {
        return this.j;
    }
    
    public final double n() {
        return this.k;
    }
    
    public final void o() {
        this.ae = -1;
    }
    
    public final void a(final int e, final int f, final double n, final double k, final int z, final int aa) {
        this.ae = -1;
        this.e = e;
        this.f = f;
        if (this.i > 0.0) {
            this.i = n;
            this.j = n;
            this.k = k;
            if (this.d > 0 && this.c > 0) {
                try {
                    this.z = Math.max((int)Math.round(this.d * n), 0);
                    if (k > 0.0) {
                        if (e == 90 || e == 270) {
                            this.z = Math.max((int)Math.round(this.d * k), 0);
                            this.aa = Math.max((int)Math.round(this.c * n), 0);
                        }
                        else {
                            this.aa = Math.max((int)Math.round(this.c * k), 0);
                        }
                    }
                    else {
                        this.aa = Math.max((int)Math.round(this.c * n), 0);
                    }
                    if (z > 0) {
                        this.z = z;
                    }
                    if (aa > 0) {
                        this.aa = aa;
                    }
                    this.ab = new int[this.z + 1];
                    this.ac = new int[this.aa + 1];
                    Math.max(this.z - 1, 0);
                    final int max = Math.max(this.c - 1, 0);
                    final int length = this.ab.length;
                    final int length2 = this.ac.length;
                    if (k > 0.0) {
                        if (e == 90 || e == 270) {
                            for (int i = 0; i < length; ++i) {
                                this.ab[i] = Math.min(Math.max((int)Math.round(i / k), 0), this.d);
                            }
                            for (int j = 0; j < length2; ++j) {
                                this.ac[j] = Math.min(Math.max((int)Math.round(j / n), 0), max);
                            }
                        }
                        else {
                            for (int l = 0; l < length; ++l) {
                                this.ab[l] = Math.min(Math.max((int)Math.round(l / n), 0), this.d);
                            }
                            for (int n2 = 0; n2 < length2; ++n2) {
                                this.ac[n2] = Math.min(Math.max((int)Math.round(n2 / k), 0), max);
                            }
                        }
                    }
                    else {
                        for (int n3 = 0; n3 < length; ++n3) {
                            this.ab[n3] = Math.min(Math.max((int)Math.round(n3 / n), 0), this.d);
                        }
                        for (int n4 = 0; n4 < length2; ++n4) {
                            this.ac[n4] = Math.min(Math.max((int)Math.round(n4 / n), 0), max);
                        }
                    }
                }
                catch (Exception ex) {}
            }
        }
    }
    
    public final String toString() {
        String concat = "RAM";
        if (this.y) {
            concat = "FILE/".concat(String.valueOf(String.valueOf(ji.util.d.h(this.m, this.ah))));
        }
        return String.valueOf(String.valueOf(new StringBuffer("jiRamLineInts [").append(this.g).append(", ").append(this.d).append(" * ").append(this.c).append(", rotation=").append(this.e).append(", flip=").append(this.f).append(", xScale=").append(this.j).append(", yScale=").append(this.k).append(", mode=").append(concat).append("]")));
    }
    
    public final void p() {
        try {
            if (this.o != null) {
                this.o.a((Object)null);
            }
        }
        catch (Exception ex) {}
        this.o = null;
        try {
            if (this.m != null && this.n != null) {
                ji.util.e.c(this.m);
                if (ji.util.e.d(this.m) < 1) {
                    this.n.d(this.m);
                }
            }
        }
        catch (Exception ex2) {}
        try {
            if (this.ag != null && this.n != null) {
                for (int i = 1; i != 0; i = 1) {
                    i = 0;
                    for (int j = 0; j < this.ag.b(); ++j) {
                        if (ji.util.e.d((String)this.ag.b(j)) < 1) {
                            this.n.d(this.m);
                            this.ag.d(j);
                            break;
                        }
                    }
                }
            }
        }
        catch (Exception ex3) {}
        this.t = null;
        this.s = null;
        this.q = null;
        this.b = null;
        this.n = null;
        this.m = null;
        this.ad = null;
    }
    
    static {
        du.h = 0;
    }
}
