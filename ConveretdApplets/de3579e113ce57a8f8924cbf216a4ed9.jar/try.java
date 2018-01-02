import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Component;
import java.io.Serializable;
import java.awt.LayoutManager2;

// 
// Decompiled by Procyon v0.5.30
// 

public class try implements LayoutManager2, Serializable, _
{
    protected static final double[][] Ha;
    protected double[] Ia;
    protected double[] Ja;
    protected int[] Ka;
    protected int[] La;
    protected int[] Ma;
    protected int[] Na;
    protected a Oa;
    protected boolean Pa;
    protected int Qa;
    protected int Ra;
    private static String o = "\ubf15\ubf24\ubf37\ubf24\ubf28\ubf20\ubf31\ubf20\ubf37\ubf65\ubf26\ubf2a\ubf28\ubf35\ubf2a\ubf2b\ubf20\ubf2b\ubf31\ubf65\ubf26\ubf24\ubf2b\ubf2b\ubf2a\ubf31\ubf65\ubf27\ubf20\ubf65\ubf2b\ubf30\ubf29\ubf29\ubf6b";
    private static String p = "\ubf15\ubf24\ubf37\ubf24\ubf28\ubf20\ubf31\ubf20\ubf37\ubf65\ubf26\ubf2a\ubf2b\ubf36\ubf31\ubf37\ubf24\ubf2c\ubf2b\ubf31\ubf65\ubf26\ubf24\ubf2b\ubf2b\ubf2a\ubf31\ubf65\ubf27\ubf20\ubf65\ubf2b\ubf30\ubf29\ubf29\ubf6b";
    private static String q = "\ubf15\ubf24\ubf37\ubf24\ubf28\ubf20\ubf31\ubf20\ubf37\ubf65\ubf2c\ubf65\ubf2c\ubf36\ubf65\ubf2c\ubf2b\ubf33\ubf24\ubf29\ubf2c\ubf21\ubf6b\ubf65\ubf65\ubf2c\ubf65\ubf78\ubf65";
    private static String C = "\ubf6b\ubf65\ubf65\ubf13\ubf24\ubf29\ubf2c\ubf21\ubf65\ubf37\ubf24\ubf2b\ubf22\ubf20\ubf65\ubf2c\ubf36\ubf65\ubf1e\ubf75\ubf69\ubf65";
    private static String D = "\ubf18\ubf6b";
    private static String E = "\ubf11\ubf24\ubf27\ubf29\ubf20\ubf09\ubf24\ubf3c\ubf2a\ubf30\ubf31\ubf65\ubf3e\ubf3e";
    private static String F = "\ubf69\ubf65";
    private static String ta = "\ubf38\ubf69\ubf65\ubf3e";
    private static String ua = "\ubf38\ubf38";
    private static String wa = "\ubf0b\ubf2a\ubf65\ubf26\ubf2a\ubf2b\ubf36\ubf31\ubf37\ubf24\ubf2c\ubf2b\ubf31\ubf65\ubf23\ubf2a\ubf37\ubf65\ubf31\ubf2d\ubf20\ubf65\ubf26\ubf2a\ubf28\ubf35\ubf2a\ubf2b\ubf20\ubf2b\ubf31";
    private static String xa = "\ubf06\ubf24\ubf2b\ubf2b\ubf2a\ubf31\ubf65\ubf24\ubf26\ubf26\ubf20\ubf35\ubf31\ubf65\ubf24\ubf65\ubf26\ubf2a\ubf2b\ubf36\ubf31\ubf37\ubf24\ubf2c\ubf2b\ubf31\ubf65\ubf2a\ubf23\ubf65\ubf26\ubf29\ubf24\ubf36\ubf36\ubf65";
    
    public try() {
        this(try.Ha);
    }
    
    public try(final double[][] array) {
        if (array != null && array.length == 2) {
            final double[] array2 = array[0];
            final double[] array3 = array[1];
            this.Ia = new double[array2.length];
            this.Ja = new double[array3.length];
            System.arraycopy(array2, 0, this.Ia, 0, this.Ia.length);
            System.arraycopy(array3, 0, this.Ja, 0, this.Ja.length);
            for (int i = 0; i < this.Ia.length; ++i) {
                if (this.Ia[i] < 0.0 && this.Ia[i] != -1.0 && this.Ia[i] != -2.0 && this.Ia[i] != -3.0) {
                    this.Ia[i] = 0.0;
                }
            }
            for (int j = 0; j < this.Ja.length; ++j) {
                if (this.Ja[j] < 0.0 && this.Ja[j] != -1.0 && this.Ja[j] != -2.0 && this.Ja[j] != -3.0) {
                    this.Ja[j] = 0.0;
                }
            }
        }
        else {
            final double[] array4 = { -1.0 };
            final double[] array5 = { -1.0 };
            this.b(array4);
            this._(array5);
        }
        this.Oa = new a(this);
        this.Pa = true;
    }
    
    public b b(final Component component) {
        final c b = this.Oa.b();
        while (b.a()) {
            final d d = (d)b.b();
            if (d.r == component) {
                return new b(d.t, d.u, d.v, d.w, d.x, d.y);
            }
        }
        return null;
    }
    
    public void _(final Component component, final b b) {
        if (component == null) {
            throw new IllegalArgumentException(try.o);
        }
        if (b == null) {
            throw new IllegalArgumentException(try.p);
        }
        final c b2 = this.Oa.b();
        while (b2.a()) {
            if (((d)b2.b()).r == component) {
                b2.a(new d(this, component, b));
            }
        }
    }
    
    public void b(final double[] array) {
        System.arraycopy(array, 0, this.Ia = new double[array.length], 0, this.Ia.length);
        for (int i = 0; i < this.Ia.length; ++i) {
            if (this.Ia[i] < 0.0 && this.Ia[i] != -1.0 && this.Ia[i] != -2.0 && this.Ia[i] != -3.0) {
                this.Ia[i] = 0.0;
            }
        }
        this.Pa = true;
    }
    
    public void _(final double[] array) {
        System.arraycopy(array, 0, this.Ja = new double[array.length], 0, this.Ja.length);
        for (int i = 0; i < this.Ja.length; ++i) {
            if (this.Ja[i] < 0.0 && this.Ja[i] != -1.0 && this.Ja[i] != -2.0 && this.Ja[i] != -3.0) {
                this.Ja[i] = 0.0;
            }
        }
        this.Pa = true;
    }
    
    public void b(final int n, double n2) {
        if (n2 < 0.0 && n2 != -1.0 && n2 != -2.0 && n2 != -3.0) {
            n2 = 0.0;
        }
        this.Ia[n] = n2;
        this.Pa = true;
    }
    
    public void _(final int n, double n2) {
        if (n2 < 0.0 && n2 != -1.0 && n2 != -2.0 && n2 != -3.0) {
            n2 = 0.0;
        }
        this.Ja[n] = n2;
        this.Pa = true;
    }
    
    public double[] b() {
        final double[] array = new double[this.Ia.length];
        System.arraycopy(this.Ia, 0, array, 0, array.length);
        return array;
    }
    
    public double[] _() {
        final double[] array = new double[this.Ja.length];
        System.arraycopy(this.Ja, 0, array, 0, array.length);
        return array;
    }
    
    public double b(final int n) {
        return this.Ia[n];
    }
    
    public double _(final int n) {
        return this.Ja[n];
    }
    
    public int _() {
        return this.Ia.length;
    }
    
    public int l() {
        return this.Ja.length;
    }
    
    public void a(final int n, double n2) {
        if (n < 0 || n > this.Ia.length) {
            throw new IllegalArgumentException(try.q + n + try.C + this.Ia.length + try.D);
        }
        if (n2 < 0.0 && n2 != -1.0 && n2 != -2.0 && n2 != -3.0) {
            n2 = 0.0;
        }
        final double[] ia = new double[this.Ia.length + 1];
        System.arraycopy(this.Ia, 0, ia, 0, n);
        System.arraycopy(this.Ia, n, ia, n + 1, this.Ia.length - n);
        ia[n] = n2;
        this.Ia = ia;
        final c b = this.Oa.b();
        while (b.a()) {
            final d d = (d)b.b();
            if (d.t >= n) {
                final d d2 = d;
                ++d2.t;
            }
            if (d.v >= n) {
                final d d3 = d;
                ++d3.v;
            }
        }
        this.Pa = true;
    }
    
    public void d(final int n, double n2) {
        if (n < 0 || n > this.Ja.length) {
            throw new IllegalArgumentException(try.q + n + try.C + this.Ja.length + try.D);
        }
        if (n2 < 0.0 && n2 != -1.0 && n2 != -2.0 && n2 != -3.0) {
            n2 = 0.0;
        }
        final double[] ja = new double[this.Ja.length + 1];
        System.arraycopy(this.Ja, 0, ja, 0, n);
        System.arraycopy(this.Ja, n, ja, n + 1, this.Ja.length - n);
        ja[n] = n2;
        this.Ja = ja;
        final c b = this.Oa.b();
        while (b.a()) {
            final d d = (d)b.b();
            if (d.u >= n) {
                final d d2 = d;
                ++d2.u;
            }
            if (d.w >= n) {
                final d d3 = d;
                ++d3.w;
            }
        }
        this.Pa = true;
    }
    
    public void a(final int n) {
        if (n < 0 || n >= this.Ia.length) {
            throw new IllegalArgumentException(try.q + n + try.C + (this.Ia.length - 1) + try.D);
        }
        final double[] ia = new double[this.Ia.length - 1];
        System.arraycopy(this.Ia, 0, ia, 0, n);
        System.arraycopy(this.Ia, n + 1, ia, n, this.Ia.length - n - 1);
        this.Ia = ia;
        final c b = this.Oa.b();
        while (b.a()) {
            final d d = (d)b.b();
            if (d.t >= n) {
                final d d2 = d;
                --d2.t;
            }
            if (d.v >= n) {
                final d d3 = d;
                --d3.v;
            }
        }
        this.Pa = true;
    }
    
    public void b(final int n) {
        if (n < 0 || n >= this.Ja.length) {
            throw new IllegalArgumentException(try.q + n + try.C + (this.Ja.length - 1) + try.D);
        }
        final double[] ja = new double[this.Ja.length - 1];
        System.arraycopy(this.Ja, 0, ja, 0, n);
        System.arraycopy(this.Ja, n + 1, ja, n, this.Ja.length - n - 1);
        this.Ja = ja;
        final c b = this.Oa.b();
        while (b.a()) {
            final d d = (d)b.b();
            if (d.u >= n) {
                final d d2 = d;
                --d2.u;
            }
            if (d.w >= n) {
                final d d3 = d;
                --d3.w;
            }
        }
        this.Pa = true;
    }
    
    public String toString() {
        String s = try.E;
        String s2;
        if (this.Ia.length > 0) {
            for (int i = 0; i < this.Ia.length - 1; ++i) {
                s = String.valueOf(s) + this.Ia[i] + try.F;
            }
            s2 = String.valueOf(s) + this.Ia[this.Ia.length - 1] + try.ta;
        }
        else {
            s2 = String.valueOf(s) + try.ta;
        }
        String s3;
        if (this.Ja.length > 0) {
            for (int j = 0; j < this.Ja.length - 1; ++j) {
                s2 = String.valueOf(s2) + this.Ja[j] + try.F;
            }
            s3 = String.valueOf(s2) + this.Ja[this.Ja.length - 1] + try.ua;
        }
        else {
            s3 = String.valueOf(s2) + try.ua;
        }
        return s3;
    }
    
    public void a(final Container container, final Graphics graphics) {
        final Dimension size = container.getSize();
        if (this.Pa || size.width != this.Qa || size.height != this.Ra) {
            this.b(container);
        }
        int n = 0;
        for (int i = 0; i < this.La.length; ++i) {
            int n2 = 0;
            for (int j = 0; j < this.Ka.length; ++j) {
                graphics.setColor(new Color((int)(Math.random() * 1.6777215E7)));
                graphics.fillRect(n2, n, this.Ka[j], this.La[i]);
                n2 += this.Ka[j];
            }
            n += this.La[i];
        }
    }
    
    public boolean f() {
        boolean b = false;
        final c b2 = this.Oa.b();
        while (b2.a()) {
            final d d = (d)b2.b();
            if (d.u < 0 || d.t < 0 || d.w > this.Ja.length || d.v > this.Ia.length) {
                b = true;
                break;
            }
        }
        return b;
    }
    
    public boolean g() {
        final int size = this.Oa.size();
        if (size == 0) {
            return false;
        }
        boolean b = false;
        final d[] _ = this.Oa._(new d[size]);
        for (int i = 1; i < size; ++i) {
            for (int j = i - 1; j >= 0; --j) {
                if ((_[j].t >= _[i].t && _[j].t <= _[i].v && _[j].u >= _[i].u && _[j].u <= _[i].w) || (_[j].v >= _[i].t && _[j].v <= _[i].v && _[j].w >= _[i].u && _[j].w <= _[i].w)) {
                    b = true;
                    break;
                }
            }
        }
        return b;
    }
    
    protected void b(final Container container) {
        final int length = this.Ia.length;
        final int length2 = this.Ja.length;
        this.Ka = new int[length];
        this.La = new int[length2];
        final Insets insets = container.getInsets();
        final Dimension size = container.getSize();
        final int qa = size.width - insets.left - insets.right;
        final int ra = size.height - insets.top - insets.bottom;
        int n = qa;
        int n2 = ra;
        for (int i = 0; i < length; ++i) {
            if (this.Ia[i] >= 1.0 || this.Ia[i] == 0.0) {
                this.Ka[i] = (int)(this.Ia[i] + 0.5);
                n -= this.Ka[i];
            }
        }
        for (int j = 0; j < length2; ++j) {
            if (this.Ja[j] >= 1.0 || this.Ja[j] == 0.0) {
                this.La[j] = (int)(this.Ja[j] + 0.5);
                n2 -= this.La[j];
            }
        }
        for (int k = 0; k < length; ++k) {
            if (this.Ia[k] == -2.0 || this.Ia[k] == -3.0) {
                int n3 = 0;
                final c b = this.Oa.b();
                while (b.a()) {
                    final d d = (d)b.b();
                    if (d.t == k && d.v == k) {
                        final Dimension dimension = (this.Ia[k] == -2.0) ? d.r.getPreferredSize() : d.r.getMinimumSize();
                        final int n4 = (dimension == null) ? 0 : dimension.width;
                        if (n3 >= n4) {
                            continue;
                        }
                        n3 = n4;
                    }
                }
                this.Ka[k] = n3;
                n -= n3;
            }
        }
        for (int l = 0; l < length2; ++l) {
            if (this.Ja[l] == -2.0 || this.Ja[l] == -3.0) {
                int n5 = 0;
                final c b2 = this.Oa.b();
                while (b2.a()) {
                    final d d2 = (d)b2.b();
                    if (d2.u == l && d2.w == l) {
                        final Dimension dimension2 = (this.Ja[l] == -2.0) ? d2.r.getPreferredSize() : d2.r.getMinimumSize();
                        final int n6 = (dimension2 == null) ? 0 : dimension2.height;
                        if (n5 >= n6) {
                            continue;
                        }
                        n5 = n6;
                    }
                }
                this.La[l] = n5;
                n2 -= n5;
            }
        }
        int n7 = n;
        int n8 = n2;
        if (n7 < 0) {
            n7 = 0;
        }
        if (n8 < 0) {
            n8 = 0;
        }
        for (int n9 = 0; n9 < length; ++n9) {
            if (this.Ia[n9] > 0.0 && this.Ia[n9] < 1.0) {
                this.Ka[n9] = (int)(this.Ia[n9] * n7 + 0.5);
                n -= this.Ka[n9];
            }
        }
        for (int n10 = 0; n10 < length2; ++n10) {
            if (this.Ja[n10] > 0.0 && this.Ja[n10] < 1.0) {
                this.La[n10] = (int)(this.Ja[n10] * n8 + 0.5);
                n2 -= this.La[n10];
            }
        }
        if (n < 0) {
            n = 0;
        }
        if (n2 < 0) {
            n2 = 0;
        }
        int n11 = 0;
        int n12 = 0;
        for (int n13 = 0; n13 < length; ++n13) {
            if (this.Ia[n13] == -1.0) {
                ++n11;
            }
        }
        for (int n14 = 0; n14 < length2; ++n14) {
            if (this.Ja[n14] == -1.0) {
                ++n12;
            }
        }
        int n15 = n;
        int n16 = n2;
        for (int n17 = 0; n17 < length; ++n17) {
            if (this.Ia[n17] == -1.0) {
                this.Ka[n17] = n / n11;
                n15 -= this.Ka[n17];
            }
        }
        for (int n18 = 0; n18 < length2; ++n18) {
            if (this.Ja[n18] == -1.0) {
                this.La[n18] = n2 / n12;
                n16 -= this.La[n18];
            }
        }
        for (int n19 = length - 1; n19 >= 0; --n19) {
            if (this.Ia[n19] == -1.0) {
                final int[] ka = this.Ka;
                final int n20 = n19;
                ka[n20] += n15;
                break;
            }
        }
        for (int n21 = length2 - 1; n21 >= 0; --n21) {
            if (this.Ja[n21] == -1.0) {
                final int[] la = this.La;
                final int n22 = n21;
                la[n22] += n16;
                break;
            }
        }
        (this.Ma = new int[length + 1])[0] = insets.left;
        for (int n23 = 0; n23 < length; ++n23) {
            this.Ma[n23 + 1] = this.Ma[n23] + this.Ka[n23];
        }
        (this.Na = new int[length2 + 1])[0] = insets.top;
        for (int n24 = 0; n24 < length2; ++n24) {
            this.Na[n24 + 1] = this.Na[n24] + this.La[n24];
        }
        this.Pa = false;
        this.Qa = qa;
        this.Ra = ra;
    }
    
    public void layoutContainer(final Container container) {
        final Dimension size = container.getSize();
        if (this.Pa || size.width != this.Qa || size.height != this.Ra) {
            this.b(container);
        }
        final Component[] components = container.getComponents();
        for (int i = 0; i < components.length; ++i) {
            try {
                final c b = this.Oa.b();
                d d = null;
                while (b.a()) {
                    d = (d)b.b();
                    if (d.r == components[i]) {
                        break;
                    }
                    d = null;
                }
                if (d == null) {
                    break;
                }
                int n3;
                int n4 = 0;
                int n5;
                int n6 = 0;
                if (d.s) {
                    int width = 0;
                    int height = 0;
                    if (d.x != 2 || d.y != 2) {
                        final Dimension preferredSize = components[i].getPreferredSize();
                        width = preferredSize.width;
                        height = preferredSize.height;
                    }
                    final int n = this.Ka[d.t];
                    final int n2 = this.La[d.u];
                    if (d.x == 2 || n < width) {
                        n3 = n;
                    }
                    else {
                        n3 = width;
                    }
                    switch (d.x) {
                        case 0: {
                            n4 = this.Ma[d.t];
                            break;
                        }
                        case 3: {
                            n4 = this.Ma[d.t + 1] - n3;
                            break;
                        }
                        case 1: {
                            n4 = this.Ma[d.t] + (n - n3 >> 1);
                            break;
                        }
                        case 2: {
                            n4 = this.Ma[d.t];
                            break;
                        }
                        default: {
                            n4 = 0;
                            break;
                        }
                    }
                    if (d.y == 2 || n2 < height) {
                        n5 = n2;
                    }
                    else {
                        n5 = height;
                    }
                    switch (d.y) {
                        case 0: {
                            n6 = this.Na[d.u];
                            break;
                        }
                        case 3: {
                            n6 = this.Na[d.u + 1] - n5;
                            break;
                        }
                        case 1: {
                            n6 = this.Na[d.u] + (n2 - n5 >> 1);
                            break;
                        }
                        case 2: {
                            n6 = this.Na[d.u];
                            break;
                        }
                        default: {
                            n6 = 0;
                            break;
                        }
                    }
                }
                else {
                    n4 = this.Ma[d.t];
                    n6 = this.Na[d.u];
                    n3 = this.Ma[d.v + 1] - this.Ma[d.t];
                    n5 = this.Na[d.w + 1] - this.Na[d.u];
                }
                components[i].setBounds(n4, n6, n3, n5);
            }
            catch (Exception ex) {}
        }
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        int n = 0;
        int n2 = 0;
        double n3 = 1.0;
        double n4 = 1.0;
        int n5 = 0;
        int n6 = 0;
        for (int i = 0; i < this.Ia.length; ++i) {
            if (this.Ia[i] > 0.0 && this.Ia[i] < 1.0) {
                n3 -= this.Ia[i];
            }
            else if (this.Ia[i] == -1.0) {
                ++n5;
            }
        }
        for (int j = 0; j < this.Ja.length; ++j) {
            if (this.Ja[j] > 0.0 && this.Ja[j] < 1.0) {
                n4 -= this.Ja[j];
            }
            else if (this.Ja[j] == -1.0) {
                ++n6;
            }
        }
        if (n5 > 1) {
            n3 /= n5;
        }
        if (n6 > 1) {
            n4 /= n6;
        }
        if (n3 < 0.0) {
            n3 = 0.0;
        }
        if (n4 < 0.0) {
            n4 = 0.0;
        }
        final int[] array = new int[this.Ia.length];
        for (int k = 0; k < this.Ia.length; ++k) {
            if (this.Ia[k] == -2.0 || this.Ia[k] == -3.0) {
                int n7 = 0;
                final c b = this.Oa.b();
                while (b.a()) {
                    final d d = (d)b.b();
                    if (d.t == k && d.v == k) {
                        final Dimension dimension = (this.Ia[k] == -2.0) ? d.r.getPreferredSize() : d.r.getMinimumSize();
                        final int n8 = (dimension == null) ? 0 : dimension.width;
                        if (n7 >= n8) {
                            continue;
                        }
                        n7 = n8;
                    }
                }
                array[k] = n7;
            }
        }
        final int[] array2 = new int[this.Ja.length];
        for (int l = 0; l < this.Ja.length; ++l) {
            if (this.Ja[l] == -2.0 || this.Ja[l] == -3.0) {
                int n9 = 0;
                final c b2 = this.Oa.b();
                while (b2.a()) {
                    final d d2 = (d)b2.b();
                    if (d2.u == l && d2.u == l) {
                        final Dimension dimension2 = (this.Ja[l] == -2.0) ? d2.r.getPreferredSize() : d2.r.getMinimumSize();
                        final int n10 = (dimension2 == null) ? 0 : dimension2.height;
                        if (n9 >= n10) {
                            continue;
                        }
                        n9 = n10;
                    }
                }
                final int[] array3 = array2;
                final int n11 = l;
                array3[n11] += n9;
            }
        }
        final c b3 = this.Oa.b();
        while (b3.a()) {
            final d d3 = (d)b3.b();
            if (d3.t >= 0 && d3.t < this.Ia.length && d3.v < this.Ia.length && d3.u >= 0 && d3.u < this.Ja.length) {
                if (d3.w >= this.Ja.length) {
                    continue;
                }
                final Dimension preferredSize = d3.r.getPreferredSize();
                int width = preferredSize.width;
                int height = preferredSize.height;
                for (int t = d3.t; t <= d3.v; ++t) {
                    if (this.Ia[t] >= 1.0) {
                        width -= (int)this.Ia[t];
                    }
                    else if (this.Ia[t] == -2.0 || this.Ia[t] == -3.0) {
                        width -= array[t];
                    }
                }
                for (int u = d3.u; u <= d3.w; ++u) {
                    if (this.Ja[u] >= 1.0) {
                        height -= (int)this.Ja[u];
                    }
                    else if (this.Ja[u] == -2.0 || this.Ja[u] == -3.0) {
                        height -= array2[u];
                    }
                }
                double n12 = 0.0;
                for (int t2 = d3.t; t2 <= d3.v; ++t2) {
                    if (this.Ia[t2] > 0.0 && this.Ia[t2] < 1.0) {
                        n12 += this.Ia[t2];
                    }
                    else if (this.Ia[t2] == -1.0 && n3 != 0.0) {
                        n12 += n3;
                    }
                }
                int n13;
                if (n12 == 0.0) {
                    n13 = 0;
                }
                else {
                    n13 = (int)(width / n12 + 0.5);
                }
                if (n < n13) {
                    n = n13;
                }
                double n14 = 0.0;
                for (int u2 = d3.u; u2 <= d3.w; ++u2) {
                    if (this.Ja[u2] > 0.0 && this.Ja[u2] < 1.0) {
                        n14 += this.Ja[u2];
                    }
                    else if (this.Ja[u2] == -1.0 && n4 != 0.0) {
                        n14 += n4;
                    }
                }
                int n15;
                if (n14 == 0.0) {
                    n15 = 0;
                }
                else {
                    n15 = (int)(height / n14 + 0.5);
                }
                if (n2 >= n15) {
                    continue;
                }
                n2 = n15;
            }
        }
        int n16 = n;
        for (int n17 = 0; n17 < this.Ia.length; ++n17) {
            if (this.Ia[n17] >= 1.0) {
                n16 += (int)(this.Ia[n17] + 0.5);
            }
            else if (this.Ia[n17] == -2.0 || this.Ia[n17] == -3.0) {
                n16 += array[n17];
            }
        }
        int n18 = n2;
        for (int n19 = 0; n19 < this.Ja.length; ++n19) {
            if (this.Ja[n19] >= 1.0) {
                n18 += (int)(this.Ja[n19] + 0.5);
            }
            else if (this.Ja[n19] == -2.0 || this.Ja[n19] == -3.0) {
                n18 += array2[n19];
            }
        }
        final Insets insets = container.getInsets();
        return new Dimension(n16 + (insets.left + insets.right), n18 + (insets.top + insets.bottom));
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        int n = 0;
        int n2 = 0;
        double n3 = 1.0;
        double n4 = 1.0;
        int n5 = 0;
        int n6 = 0;
        for (int i = 0; i < this.Ia.length; ++i) {
            if (this.Ia[i] > 0.0 && this.Ia[i] < 1.0) {
                n3 -= this.Ia[i];
            }
            else if (this.Ia[i] == -1.0) {
                ++n5;
            }
        }
        for (int j = 0; j < this.Ja.length; ++j) {
            if (this.Ja[j] > 0.0 && this.Ja[j] < 1.0) {
                n4 -= this.Ja[j];
            }
            else if (this.Ja[j] == -1.0) {
                ++n6;
            }
        }
        if (n5 > 1) {
            n3 /= n5;
        }
        if (n6 > 1) {
            n4 /= n6;
        }
        if (n3 < 0.0) {
            n3 = 0.0;
        }
        if (n4 < 0.0) {
            n4 = 0.0;
        }
        final c b = this.Oa.b();
        while (b.a()) {
            final d d = (d)b.b();
            if (d.t >= 0 && d.t < this.Ia.length && d.v < this.Ia.length && d.u >= 0 && d.u < this.Ja.length) {
                if (d.w >= this.Ja.length) {
                    continue;
                }
                final Dimension minimumSize = d.r.getMinimumSize();
                int width = minimumSize.width;
                int height = minimumSize.height;
                for (int k = d.t; k <= d.v; ++k) {
                    if (this.Ia[k] >= 1.0) {
                        width -= (int)this.Ia[k];
                    }
                }
                for (int l = d.u; l <= d.w; ++l) {
                    if (this.Ja[l] >= 1.0) {
                        height -= (int)this.Ja[l];
                    }
                }
                double n7 = 0.0;
                for (int t = d.t; t <= d.v; ++t) {
                    if (this.Ia[t] > 0.0 && this.Ia[t] < 1.0) {
                        n7 += this.Ia[t];
                    }
                    else if (this.Ia[t] == -1.0 && n3 != 0.0) {
                        n7 += n3;
                    }
                }
                int n8;
                if (n7 == 0.0) {
                    n8 = 0;
                }
                else {
                    n8 = (int)(width / n7 + 0.5);
                }
                if (n < n8) {
                    n = n8;
                }
                double n9 = 0.0;
                for (int u = d.u; u <= d.w; ++u) {
                    if (this.Ja[u] > 0.0 && this.Ja[u] < 1.0) {
                        n9 += this.Ja[u];
                    }
                    else if (this.Ja[u] == -1.0 && n4 != 0.0) {
                        n9 += n4;
                    }
                }
                int n10;
                if (n9 == 0.0) {
                    n10 = 0;
                }
                else {
                    n10 = (int)(height / n9 + 0.5);
                }
                if (n2 >= n10) {
                    continue;
                }
                n2 = n10;
            }
        }
        int n11 = n;
        for (int n12 = 0; n12 < this.Ia.length; ++n12) {
            if (this.Ia[n12] >= 1.0) {
                n11 += (int)(this.Ia[n12] + 0.5);
            }
            else if (this.Ia[n12] == -2.0 || this.Ia[n12] == -3.0) {
                int n13 = 0;
                final c b2 = this.Oa.b();
                while (b2.a()) {
                    final d d2 = (d)b2.b();
                    if (d2.t == n12 && d2.v == n12) {
                        final Dimension dimension = (this.Ia[n12] == -2.0) ? d2.r.getPreferredSize() : d2.r.getMinimumSize();
                        final int n14 = (dimension == null) ? 0 : dimension.width;
                        if (n13 >= n14) {
                            continue;
                        }
                        n13 = n14;
                    }
                }
                n11 += n13;
            }
        }
        int n15 = n2;
        for (int n16 = 0; n16 < this.Ja.length; ++n16) {
            if (this.Ja[n16] >= 1.0) {
                n15 += (int)(this.Ja[n16] + 0.5);
            }
            else if (this.Ja[n16] == -2.0 || this.Ja[n16] == -3.0) {
                int n17 = 0;
                final c b3 = this.Oa.b();
                while (b3.a()) {
                    final d d3 = (d)b3.b();
                    if (d3.u == n16 && d3.u == n16) {
                        final Dimension dimension2 = (this.Ja[n16] == -2.0) ? d3.r.getPreferredSize() : d3.r.getMinimumSize();
                        final int n18 = (dimension2 == null) ? 0 : dimension2.height;
                        if (n17 >= n18) {
                            continue;
                        }
                        n17 = n18;
                    }
                }
                n15 += n17;
            }
        }
        final Insets insets = container.getInsets();
        return new Dimension(n11 + (insets.left + insets.right), n15 + (insets.top + insets.bottom));
    }
    
    public void addLayoutComponent(final String s, final Component component) {
        this.addLayoutComponent(component, s);
    }
    
    public void addLayoutComponent(final Component component, final Object o) {
        if (o instanceof String) {
            this.Oa.b(new d(this, component, new b((String)o)));
            return;
        }
        if (o instanceof b) {
            this.Oa.b(new d(this, component, (b)o));
            return;
        }
        if (o == null) {
            throw new IllegalArgumentException(try.wa);
        }
        throw new IllegalArgumentException(try.xa + o.getClass());
    }
    
    public void removeLayoutComponent(final Component component) {
        this.Oa.remove(component);
    }
    
    public Dimension maximumLayoutSize(final Container container) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    
    public float getLayoutAlignmentX(final Container container) {
        return 0.5f;
    }
    
    public float getLayoutAlignmentY(final Container container) {
        return 0.5f;
    }
    
    public void invalidateLayout(final Container container) {
        this.Pa = true;
    }
    
    static {
        try.o = _(try.o);
        try.p = _(try.p);
        try.q = _(try.q);
        try.C = _(try.C);
        try.D = _(try.D);
        try.E = _(try.E);
        try.F = _(try.F);
        try.ta = _(try.ta);
        try.ua = _(try.ua);
        try.wa = _(try.wa);
        try.xa = _(try.xa);
        Ha = new double[][] { new double[0], new double[0] };
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFFBF45);
        }
        return new String(array);
    }
}
