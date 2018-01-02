// 
// Decompiled by Procyon v0.5.30
// 

package ji.image;

import ji.util.i;
import ji.util.e;
import java.awt.Component;
import ji.util.d;
import java.awt.Color;
import ji.awt.c;
import ji.io.ac;
import ji.io.q;
import java.awt.image.ColorModel;

public class dw
{
    private byte[] a;
    private int b;
    private int c;
    private int d;
    private int e;
    private double f;
    private ColorModel g;
    private String h;
    private q i;
    private ac j;
    private boolean k;
    private boolean l;
    private byte[] m;
    private byte[] n;
    private byte[] o;
    private int[] p;
    private int q;
    private boolean r;
    private boolean s;
    private long t;
    private long u;
    private long v;
    private long w;
    private long x;
    private int[] y;
    private boolean z;
    private int aa;
    private int ab;
    private int ac;
    private int ad;
    private int[] ae;
    private int[] af;
    private byte[] ag;
    private int ah;
    private c ai;
    private String aj;
    
    public void a(final Color color) {
        if (color != null) {
            this.aa = color.getRGB();
        }
        else {
            this.aa = 0;
        }
    }
    
    public Color a() {
        int aa = this.aa;
        if (aa == 0 && this.y != null && this.y.length > 254) {
            aa = this.y[255];
        }
        if (aa != 0) {
            return new Color(aa);
        }
        return Color.black;
    }
    
    public void a(final int n) {
        this.ab = Math.max(1, n);
    }
    
    public int b() {
        return this.ab;
    }
    
    public int c() {
        return this.q;
    }
    
    public static long a(final long n) {
        return n;
    }
    
    public long d() {
        return this.x;
    }
    
    public static boolean a(final long n, final int n2, final int n3, final boolean b, final String s) {
        boolean b2 = false;
        if (n2 * n3 > n && d.di() != 1 && !d.a(b, s)) {
            b2 = true;
        }
        return b2;
    }
    
    public dw(final int b, final int c, final Component component, final long x, final String aj, final boolean s, final int q) {
        this.a = null;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 1.0;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = true;
        this.l = false;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = 0;
        this.r = true;
        this.s = false;
        this.t = 1048576L;
        this.u = this.t / 2;
        this.v = this.t;
        this.w = 0L;
        this.x = 0L;
        this.y = new int[256];
        this.z = false;
        this.aa = 0;
        this.ab = 1;
        this.ac = 0;
        this.ad = 0;
        this.ae = null;
        this.af = null;
        this.ag = null;
        this.ah = -1;
        this.ai = null;
        this.aj = null;
        try {
            this.aj = aj;
            this.b = b;
            this.c = c;
            this.x = x;
            this.q = q;
            this.s = s;
            this.t = a(x);
            this.v = this.t;
            this.u = x / 2;
            if (b * c < 2) {
                this.r = false;
            }
            else if (a(this.v, b, c, s, aj)) {
                this.z = true;
                this.i = ji.io.q.a(component, aj);
                this.h = this.i.o();
                this.t();
                this.ai.c(this.h);
                this.b(component);
                ji.util.e.b(this.h);
            }
            else {
                this.a = new byte[b * c];
            }
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
    }
    
    public dw(final String s, final int b, final int c, final Component component, final long t, final String aj, final boolean b2, final int q) {
        this.a = null;
        this.b = 0;
        this.c = 0;
        this.d = 0;
        this.e = 0;
        this.f = 1.0;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = true;
        this.l = false;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = 0;
        this.r = true;
        this.s = false;
        this.t = 1048576L;
        this.u = this.t / 2;
        this.v = this.t;
        this.w = 0L;
        this.x = 0L;
        this.y = new int[256];
        this.z = false;
        this.aa = 0;
        this.ab = 1;
        this.ac = 0;
        this.ad = 0;
        this.ae = null;
        this.af = null;
        this.ag = null;
        this.ah = -1;
        this.ai = null;
        this.aj = null;
        try {
            this.aj = aj;
            this.b = b;
            this.c = c;
            this.q = q;
            this.t = t;
            this.v = this.t;
            if (ji.util.d.em()) {
                this.v = this.t * 3;
            }
            this.u = t / 2;
            this.z = false;
            final int n = (int)ji.io.ac.a(s, aj);
            final ac ac = new ac(s, false, false, 0, false, component, aj);
            if (a(this.v, b, c, b2, aj)) {
                this.z = true;
            }
            if (this.z) {
                this.i = ji.io.q.a(component, aj);
                this.h = this.i.o();
                this.t();
                this.ai.c(this.h);
                this.b(component);
                ji.util.e.b(this.h);
                int min = Math.min(102400, n);
                final byte[] array = new byte[min];
                int i = 0;
                while (i < n) {
                    i += ac.a(array, 0, min);
                    this.j.b(array, 0, min);
                    if (n - i < min) {
                        min = n - i;
                    }
                }
            }
            else {
                ac.a(this.a = new byte[n]);
            }
            ac.a(component);
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
    }
    
    public final boolean e() {
        return this.z;
    }
    
    public final boolean f() {
        return this.k;
    }
    
    public final void a(final boolean k) {
        this.k = k;
    }
    
    private final void b(final Component component) throws Exception {
        if (!this.l) {
            if (this.j != null) {
                this.j.a((Object)null);
            }
            this.j = new ac(this.h, true, false, 0, false, component, this.aj);
            this.l = true;
            this.w = 0L;
        }
    }
    
    private final void c(final Component component) throws Exception {
        if (this.l) {
            if (this.j != null) {
                this.j.a((Object)null);
            }
            this.j = new ac(this.h, false, true, (int)this.u, false, component, this.aj);
            this.l = false;
            this.w = 0L;
        }
    }
    
    public final boolean g() {
        if (this.z) {
            return ji.io.ac.d(this.h, this.aj);
        }
        return this.b * this.c > 0;
    }
    
    public final void a(final ColorModel g) {
        this.g = g;
        if (g != null) {
            for (int i = 0; i < 256; ++i) {
                this.y[i] = g.getRGB(i);
            }
        }
    }
    
    public final ColorModel h() {
        return this.g;
    }
    
    public final int i() {
        return this.c;
    }
    
    public final int j() {
        if (this.f > 0.0) {
            return this.ac;
        }
        return this.c;
    }
    
    public final int k() {
        return this.b;
    }
    
    public final int l() {
        if (this.f > 0.0) {
            return this.ad;
        }
        return this.b;
    }
    
    public final byte[] m() {
        return new byte[this.c];
    }
    
    public final void a(final int n, final byte[] array) throws Exception {
        try {
            final int n2 = n * this.c;
            if (this.z) {
                if (!this.l) {
                    this.b(null);
                }
                if (this.w != n2) {
                    this.j.a((long)n2);
                    this.w = n2;
                }
                this.j.b(array);
                this.w += array.length;
            }
            else {
                System.arraycopy(array, 0, this.a, n2, this.c);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final int n, final byte[] array, final int n2, final int n3) throws Exception {
        if (n3 > 0) {
            if (n2 > this.c) {
                final byte[] array2 = new byte[this.c * n3];
                final int length = array.length;
                for (int i = 0; i < n3; ++i) {
                    final int n4 = i * n2;
                    final int n5 = i * this.c;
                    if (n4 + this.c < length) {
                        System.arraycopy(array, n4, array2, n5, this.c);
                    }
                }
                this.b(n, array2, this.c, n3);
            }
            else {
                this.b(n, array, n2, n3);
            }
        }
    }
    
    public final void b(final int n, final byte[] array, final int n2, int min) throws Exception {
        try {
            min = Math.min(min, array.length / n2);
            final int n3 = n * this.c;
            byte[] array2 = array;
            if (n2 < this.c) {
                array2 = new byte[this.c * min];
                for (int i = 0; i < min; ++i) {
                    final int n4 = i * this.c;
                    System.arraycopy(array, n4, array2, n4, n2);
                }
            }
            if (this.z) {
                if (!this.l) {
                    this.b(null);
                }
                if (this.w != n3) {
                    this.j.a((long)n3);
                    this.w = n3;
                }
                this.j.b(array2, 0, this.c * min);
                this.w += this.c * min;
            }
            else {
                System.arraycopy(array2, 0, this.a, n3, this.c * min);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final int n, final byte[] array, final int n2) throws Exception {
        try {
            final int n3 = n * this.c;
            if (this.z) {
                if (!this.l) {
                    this.b(null);
                }
                this.j.b(array, 0, n2);
                this.w += n2;
            }
            else {
                System.arraycopy(array, 0, this.a, n3, n2);
            }
        }
        catch (Exception ex) {}
    }
    
    public final byte[] b(final int n) throws Exception {
        return this.a(n, 0, this.c);
    }
    
    public final byte[] a(final int n, final int n2, final int n3) throws Exception {
        final int min = Math.min(n3 - n2, this.c);
        if (this.m == null) {
            this.m = new byte[min];
        }
        else if (this.m.length != min) {
            this.m = new byte[min];
        }
        try {
            if (n < this.b) {
                if (this.l) {
                    this.c(null);
                }
                final int n4 = n2 + n * this.c;
                if (this.z) {
                    if (this.w != n4) {
                        this.j.a((long)n4);
                        this.w = n4;
                    }
                    this.w += this.j.a(this.m);
                }
                else {
                    System.arraycopy(this.a, n4, this.m, 0, min);
                }
            }
        }
        catch (Exception ex) {}
        return this.m;
    }
    
    public final byte[] a(final int n, final int n2, final int n3, final boolean b) throws Exception {
        try {
            Label_0610: {
                try {
                    if (this.n == null) {
                        this.n = new byte[n3];
                        this.ah = -1;
                    }
                    else if (this.n.length < n3) {
                        this.n = new byte[n3];
                        this.ah = -1;
                    }
                    if (this.f > 1.0) {
                        try {
                            final int ah = (int)(n / this.f);
                            final int n4 = (int)(n2 / this.f);
                            if (this.ah != ah) {
                                this.ag = this.a(ah, n4, this.c + 1);
                                this.ah = ah;
                            }
                            for (int i = 0; i < n3; ++i) {
                                this.n[i] = (byte)(255 * this.ag[(int)((i + n2) / this.f) - n4]);
                            }
                            break Label_0610;
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                            break Label_0610;
                        }
                    }
                    if (this.f == 1.0) {
                        final byte[] a = this.a((int)(n / this.f), n2, this.c + 1);
                        final int n5 = (int)(n2 / this.f);
                        for (int j = 0; j < n3; ++j) {
                            this.n[j] = (byte)(255 * a[this.ae[j + n2] - n5]);
                        }
                    }
                    else {
                        this.ah = -1;
                        final int n6 = (int)(n / this.f);
                        final int n7 = (int)(n2 / this.f);
                        final int n8 = (int)Math.round(1.0 / (2.0 * this.f));
                        final int n9 = this.c - 1;
                        final int n10 = this.b - 1;
                        final int max = Math.max(n6 - n8, 0);
                        final int min = Math.min(n6 + n8, n10);
                        final byte[][] array = new byte[min - max][0];
                        for (int k = max; k < min; ++k) {
                            array[k - max] = this.a(k, n2, this.c + 1);
                        }
                        for (int l = 0; l < n3; ++l) {
                            final int n11 = this.ae[l + n2] - n7;
                            try {
                                final int n12 = n11 - n8;
                                final int n13 = n11 + n8;
                                final int n14 = max - n8;
                                final int n15 = max + n8;
                                final int n16 = (n12 >= 0) ? n12 : 0;
                                final int n17 = (n13 <= n9) ? n13 : n9;
                                final int n18 = (n14 >= 0) ? n14 : 0;
                                final int n19 = (n15 <= n10) ? n15 : n10;
                                int n20 = 0;
                                int n21 = 0;
                                for (int n22 = n16; n22 < n17; ++n22) {
                                    for (int n23 = n18; n23 < n19; ++n23) {
                                        n20 += (array[n23 - n18][n22] & 0xFF);
                                        ++n21;
                                    }
                                }
                                if (n21 > 1) {
                                    this.n[l] = (byte)(255 * n20 / n21);
                                }
                                else if (n21 > 0) {
                                    this.n[l] = (byte)(255 * n20);
                                }
                            }
                            catch (Exception ex3) {}
                        }
                    }
                }
                catch (Exception ex4) {}
                try {
                    if (!b || n < 0) {
                        return this.n;
                    }
                    final int n24 = 254;
                    if (this.ab >= 1) {
                        if (n < this.ab) {
                            for (int n25 = 0; n25 < n3; ++n25) {
                                this.n[n25] = (byte)n24;
                            }
                        }
                        if (n >= this.ad - this.ab) {
                            for (int n26 = 0; n26 < n3; ++n26) {
                                this.n[n26] = (byte)n24;
                            }
                        }
                        for (int n27 = 0; n27 < this.ab; ++n27) {
                            this.n[n27] = (byte)n24;
                        }
                        for (int n28 = n3 - 1; n28 > n3 - 1 - this.ab; --n28) {
                            this.n[n28] = (byte)n24;
                        }
                        return this.n;
                    }
                    return this.n;
                }
                catch (Exception ex2) {
                    if (ji.util.d.cy()) {
                        ex2.printStackTrace();
                        return this.n;
                    }
                    return this.n;
                }
            }
        }
        catch (Exception ex5) {}
        return this.n;
    }
    
    public final int[] b(final int n, final int n2, final int n3, final boolean b) throws Exception {
        if (this.f > 0.0) {
            return this.c(n, n2, n3, b);
        }
        return this.b(n, n2, n3);
    }
    
    public final int[] b(final int n, final int n2, final int n3) throws Exception {
        final byte[] a = this.a(n, n2, n3);
        final int length = a.length;
        final int[] array = new int[length];
        for (int i = 0; i < length; ++i) {
            array[i] = this.y[255 * a[i] & 0xFF];
        }
        return array;
    }
    
    public final int[] c(final int n, final int n2, final int n3, final boolean b) throws Exception {
        final int min = Math.min(n2, this.ac - 1);
        final int min2 = Math.min(n3, this.ac - 1);
        final int min3 = Math.min(n, this.ad - 1);
        final int ah = this.af[min3];
        final int min4 = Math.min(this.ae[min], this.ae[min2]);
        final int max = Math.max(this.ae[min], this.ae[min2]);
        Math.max(Math.min(max - min4, this.c), 0);
        final int max2 = Math.max(Math.min(min2 - min, this.ac), 0);
        Math.max(max2 - 1, 0);
        if (this.o == null) {
            this.o = new byte[max2];
        }
        else if (this.o.length != max2) {
            this.o = new byte[max2];
        }
        if (this.p == null) {
            this.p = new int[max2];
        }
        else if (this.p.length != max2) {
            this.p = new int[max2];
        }
        try {
            final boolean b2 = (this.e & 0x1) > 0;
            if (this.f != 0.0) {
                if (this.f <= 1.0) {
                    this.ah = -1;
                    int n4;
                    if (ji.util.i.c(271)) {
                        n4 = (int)Math.round(1.5 / (2.0 * this.f));
                    }
                    else {
                        n4 = (int)Math.round(1.0 / (2.0 * this.f));
                    }
                    final int max3 = Math.max(ah - n4, 0);
                    final int min5 = Math.min(ah + n4, this.b - 1);
                    final byte[][] array = new byte[min5 - max3][0];
                    for (int i = max3; i < min5; ++i) {
                        array[i - max3] = this.a(i, min4, max + 1);
                    }
                    final int n5 = max3;
                    for (int j = 0; j < max2; ++j) {
                        final int n6 = this.ae[j + min] - min4;
                        try {
                            int n7 = 0;
                            final int n8 = n6 - n4;
                            final int n9 = n6 + n4;
                            final int n10 = n5 - n4;
                            final int n11 = n5 + n4;
                            final int n12 = (n8 >= 0) ? n8 : 0;
                            final int n13 = (n9 < this.c) ? n9 : (this.c - 1);
                            int n14 = 0;
                            final int n15 = (n10 >= 0) ? n10 : 0;
                            final int n16 = (n11 < this.b) ? n11 : (this.b - 1);
                            for (int k = n12; k < n13; ++k) {
                                for (int l = n15; l < n16; ++l) {
                                    n14 += (array[l - n15][k] & 0xFF);
                                    ++n7;
                                }
                            }
                            if (n7 > 1) {
                                this.o[j] = (byte)(255 * n14 / n7);
                            }
                            else if (n7 > 0) {
                                this.o[j] = (byte)(255 * n14);
                            }
                        }
                        catch (Exception ex3) {}
                    }
                }
                else {
                    try {
                        if (this.ah != ah) {
                            this.ag = this.a(ah, min4, max + 1);
                            this.ah = ah;
                        }
                        for (int n17 = 0; n17 < max2; ++n17) {
                            this.o[n17] = (byte)(255 * this.ag[this.ae[n17 + min] - min4]);
                        }
                    }
                    catch (Exception ex4) {}
                }
            }
            if (max2 > 0) {
                for (int n18 = 0; n18 < max2; ++n18) {
                    this.p[n18] = this.y[this.o[n18] & 0xFF];
                }
            }
            try {
                if (!b || this.ac <= 0) {
                    return this.p;
                }
                int aa = this.aa;
                if (aa == 0) {
                    aa = this.y[255];
                }
                if ((min3 < this.ab || min3 >= this.ad - this.ab) && max2 > 1) {
                    for (int n19 = 0; n19 < max2; ++n19) {
                        this.p[n19] = aa;
                    }
                }
                if (min < this.ab) {
                    for (int n20 = 0; n20 < this.ab - min; ++n20) {
                        this.p[n20] = aa;
                    }
                }
                if (min + max2 >= this.ac - this.ab) {
                    for (int n21 = min + max2 - (this.ac - this.ab) + 1, n22 = max2 - 1; n22 >= max2 - n21; --n22) {
                        this.p[n22] = aa;
                    }
                    return this.p;
                }
                return this.p;
            }
            catch (Exception ex) {
                if (ji.util.d.cy()) {
                    ex.printStackTrace();
                    return this.p;
                }
                return this.p;
            }
        }
        catch (Exception ex2) {
            if (ji.util.d.cy()) {
                ex2.printStackTrace();
            }
        }
        return this.p;
    }
    
    public final String a(final Component component) {
        String o = null;
        try {
            if (this.z) {
                return this.h;
            }
            this.t();
            if (this.i == null) {
                this.i = ji.io.q.a(component, this.aj);
            }
            o = this.i.o();
            this.ai.c(o);
            final ac ac = new ac(o, true, false, 0, false, null, this.aj);
            ac.b(this.a);
            ac.a(component);
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
        return o;
    }
    
    public final byte[] n() {
        return this.a;
    }
    
    public final byte[] o() {
        if (this.z) {
            try {
                if (this.l) {
                    this.c(null);
                }
                final byte[] array = new byte[this.b * this.c];
                this.w = this.j.a(array);
                return array;
            }
            catch (Exception ex) {
                if (ji.util.d.cy()) {
                    ex.printStackTrace();
                }
                return null;
            }
        }
        return this.a;
    }
    
    public final int p() {
        return this.d;
    }
    
    public final int q() {
        return this.e;
    }
    
    public final double r() {
        return this.f;
    }
    
    public final void s() {
        this.ah = -1;
    }
    
    public final void a(final int d, final int e, final double f) {
        this.ah = -1;
        this.d = d;
        this.e = e;
        if (f > 0.0) {
            this.f = f;
            if (this.c > 0 && this.b > 0) {
                try {
                    this.ac = Math.max((int)Math.round(this.c * f), 0);
                    this.ad = Math.max((int)Math.round(this.b * f), 0);
                    this.ae = new int[this.ac];
                    this.af = new int[this.ad];
                    Math.max(this.ac - 1, 0);
                    Math.max(this.b - 1, 0);
                    for (int i = 0; i < this.ac; ++i) {
                        this.ae[i] = Math.max((int)Math.round(i / f), 0);
                    }
                    for (int j = 0; j < this.ad; ++j) {
                        this.af[j] = Math.max((int)Math.round(j / f), 0);
                    }
                }
                catch (Exception ex) {
                    if (d.cy()) {
                        ex.printStackTrace();
                    }
                }
            }
        }
    }
    
    public final String toString() {
        String s = "RAM";
        if (this.z) {
            s = "FILE";
        }
        return String.valueOf(String.valueOf(new StringBuffer("jiRamLineBytes [").append(this.c).append(" * ").append(this.b).append(", rotation=").append(this.d).append(", flip=").append(this.e).append(", scale=").append(this.f).append(", mode=").append(s).append("]")));
    }
    
    public final void c(final int n) {
        try {
            if (this.j != null) {
                this.j.a((Object)null);
            }
        }
        catch (Exception ex) {}
        this.j = null;
        try {
            if (this.h != null && this.i != null) {
                ji.util.e.c(this.h);
                if (ji.util.e.d(this.h) < 1) {
                    this.i.d(this.h);
                }
            }
        }
        catch (Exception ex2) {}
        try {
            if (this.ai != null && this.i != null) {
                for (int i = 1; i != 0; i = 1) {
                    i = 0;
                    for (int j = 0; j < this.ai.b(); ++j) {
                        if (ji.util.e.d((String)this.ai.b(j)) < 1) {
                            this.i.d(this.h);
                            this.ai.d(j);
                            break;
                        }
                    }
                }
            }
        }
        catch (Exception ex3) {}
        this.p = null;
        this.o = null;
        this.m = null;
        this.n = null;
        this.a = null;
        this.i = null;
        this.h = null;
        this.ag = null;
        this.ai = null;
    }
    
    private final void t() {
        if (this.ai == null) {
            this.ai = new c("jiRamLineBytes1");
        }
    }
}
