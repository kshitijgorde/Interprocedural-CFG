// 
// Decompiled by Procyon v0.5.30
// 

package ji.render;

import ji.v1event.a6;
import ji.image.cy;
import ji.util.d;
import java.awt.image.ImageConsumer;
import ji.util.e;
import java.awt.image.ColorModel;
import ji.io.ac;
import ji.image.du;
import ji.v1event.af;
import ji.io.q;
import java.awt.Color;
import ji.awt.c;
import java.awt.image.ImageProducer;

public class c1 implements ImageProducer
{
    private c a;
    private int b;
    private String c;
    private int d;
    private int e;
    private int f;
    private int g;
    private Color h;
    private q i;
    private String j;
    private af k;
    private String l;
    private boolean m;
    private boolean n;
    private du o;
    private int p;
    private int q;
    private int r;
    private int s;
    private ac t;
    private int[] u;
    private byte[] v;
    private byte[] w;
    private byte[] x;
    private byte[] y;
    private String z;
    private boolean aa;
    private ColorModel ab;
    
    public String toString() {
        return String.valueOf(String.valueOf(new StringBuffer("jiRenderFile(").append(this.c).append(", ").append(this.d).append("*").append(this.e).append("/").append(this.b).append(")")));
    }
    
    public c1(final String c, final int d, final int e, final int f, final int g, final int b, final Color h, final af k, final String z) {
        this.a = new c("jiRenderFile1");
        this.b = 24;
        this.c = null;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = false;
        this.n = false;
        this.o = null;
        this.p = 0;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.aa = false;
        this.ab = null;
        this.z = z;
        this.c = c;
        this.b = b;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.k = k;
        if (c != null) {
            ji.util.e.b(c);
        }
    }
    
    public final int a() {
        if (this.f > 0) {
            return this.f;
        }
        return this.d;
    }
    
    public final int b() {
        if (this.g > 0) {
            return this.g;
        }
        return this.e;
    }
    
    public final int c() {
        return this.b;
    }
    
    public final String d() {
        return this.c;
    }
    
    public final void a(final boolean n) {
        this.n = n;
    }
    
    public final void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
    }
    
    public final void addConsumer(final ImageConsumer imageConsumer) {
        if (this.a != null) {
            this.a.c(imageConsumer);
        }
    }
    
    public final void removeConsumer(final ImageConsumer imageConsumer) {
        if (this.a != null && this.a.a(imageConsumer)) {
            this.a.b(imageConsumer);
        }
    }
    
    public final boolean isConsumer(final ImageConsumer imageConsumer) {
        return this.a != null && this.a.a(imageConsumer);
    }
    
    public final void startProduction(final ImageConsumer imageConsumer) {
        boolean b = true;
        if (this.d == this.f && this.e == this.g) {
            b = false;
        }
        String s;
        if (this.j == null && b) {
            this.j = this.h();
            s = this.j;
        }
        else {
            s = this.c;
        }
        this.a(imageConsumer, s, this.f, this.g);
    }
    
    private final void j() {
        try {
            if (this.j != null) {
                if (this.i == null) {
                    this.i = ji.io.q.a((Object)null, this.z);
                }
                this.i.d(this.j);
                ac.c(this.j, this.z);
                this.j = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final ColorModel ab) {
        this.ab = ab;
    }
    
    public final ColorModel e() {
        if (this.ab != null) {
            return this.ab;
        }
        return ColorModel.getRGBdefault();
    }
    
    public final int[] a(final int n, final int n2, final int n3, final int n4) {
        int[] array = null;
        if (this.t != null) {
            try {
                final int n5 = n4 * this.f * 4;
                if (this.y == null) {
                    this.y = new byte[n5];
                }
                else if (this.y.length < n5) {
                    this.y = new byte[n5];
                }
                array = new int[n5];
                this.t.a((long)(n2 * this.f * 4));
                this.t.a(this.y, 0, n5);
                for (int i = 0; i < n5; i += 4) {
                    array[i] = this.y[i + 1];
                    array[i + 1] = this.y[i + 2];
                    array[i + 2] = this.y[i + 3];
                    array[i + 3] = this.y[i];
                }
            }
            catch (Exception ex) {}
        }
        return array;
    }
    
    public final void a(final int n, final int n2, final int n3, final int n4, final int[] array) {
        if (this.t != null) {
            try {
                final int n5 = n4 * this.f * 4;
                if (this.y == null) {
                    this.y = new byte[n5];
                }
                else if (this.y.length < n5) {
                    this.y = new byte[n5];
                }
                this.t.a((long)(n2 * this.f * 4));
                this.t.a(this.y, 0, n5);
                for (int i = 0; i < n4; ++i) {
                    final int n6 = i * n3;
                    int n7 = (i * this.f + n) * 4;
                    for (int j = 0; j < n3; ++j) {
                        array[n6 + j] = ((this.y[n7++] & 0xFF) << 24) + ((this.y[n7++] & 0xFF) << 16) + ((this.y[n7++] & 0xFF) << 8) + (this.y[n7++] & 0xFF);
                    }
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public final void a(final int n, final int n2, final int n3, final int n4, final byte[] array) {
        if (this.t != null) {
            try {
                final int n5 = n4 * this.f * 4;
                this.t.a((long)(n2 * this.f * 4));
                this.t.a(array, 0, n5);
            }
            catch (Exception ex) {}
        }
    }
    
    public final void f() {
        try {
            if (this.t != null) {
                this.t.a((Object)null);
                this.t = null;
            }
        }
        catch (Exception ex) {}
    }
    
    public final boolean g() {
        boolean b = false;
        try {
            boolean b2 = true;
            if (this.d == this.f && this.e == this.g) {
                b2 = false;
            }
            String s;
            if (this.j == null && b2) {
                this.j = this.h();
                s = this.j;
            }
            else {
                s = this.c;
            }
            if (s != null && this.t == null) {
                this.t = new ac(s, false, false, 0, false, null, this.z);
                b = true;
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    public final void a(final ImageConsumer imageConsumer, final String s, final int n, final int n2) {
        int n3 = 1;
        ac ac = null;
        boolean b = false;
        try {
            this.aa = true;
            if (this.c != null) {
                ac = new ac(s, false, false, 0, false, null, this.z);
                if (ac.v() > 0) {
                    final ColorModel e = this.e();
                    imageConsumer.setColorModel(e);
                    imageConsumer.setDimensions(n, n2);
                    imageConsumer.setHints(30);
                    int n4 = 100240 / n;
                    if (n4 < 5) {
                        n4 = 5;
                    }
                    int n5;
                    if (this.s > 0 && this.r > 0) {
                        n5 = Math.min(Math.min(n4, n2), this.s);
                    }
                    else {
                        n5 = Math.min(n4, n2);
                    }
                    int n6 = n5 * n;
                    int n7;
                    if (this.b > 8) {
                        n7 = n6 * 4;
                    }
                    else {
                        n7 = n6;
                    }
                    if (this.x == null) {
                        this.x = new byte[n7];
                    }
                    else if (this.x.length != n7) {
                        this.x = new byte[n7];
                    }
                    int i = 0;
                    int n8 = n2;
                    final long currentTimeMillis = System.currentTimeMillis();
                    final boolean b2 = this.k != null;
                    int n9 = n2 / 20;
                    int min = n2;
                    int r = n;
                    if (this.s > 0 && this.r > 0) {
                        min = Math.min(min, this.s);
                        if (this.b > 8) {
                            ac.a((long)(this.q * n * 4));
                        }
                        else {
                            ac.a((long)(this.q * n));
                        }
                        r = this.r;
                    }
                    final int n10 = n5 * r;
                    if (this.b > 8) {
                        if (this.u == null) {
                            this.u = new int[n10];
                        }
                        else if (this.u.length != n10) {
                            this.u = new int[n10];
                        }
                    }
                    else if (this.v == null) {
                        this.v = new byte[n10];
                    }
                    else if (this.v.length != n10) {
                        this.v = new byte[n10];
                    }
                    while (i < min) {
                        if (b2) {
                            final long currentTimeMillis2 = System.currentTimeMillis();
                            --n9;
                            if (currentTimeMillis2 - currentTimeMillis > 250 && n9 <= 0) {
                                this.a(this.k, 100 * i / n2);
                                n9 = n2 / 20;
                                b = true;
                            }
                        }
                        if (this.b > 8) {
                            ac.a(this.x, 0, n6 * 4);
                            for (int j = 0; j < n5; ++j) {
                                final int n11 = j * n * 4;
                                final int n12 = j * r;
                                for (int k = 0; k < r; ++k) {
                                    int n13 = n11 + (k + this.p) * 4;
                                    this.u[n12 + k] = ((this.x[n13++] & 0xFF) << 24 | (this.x[n13++] & 0xFF) << 16 | (this.x[n13++] & 0xFF) << 8 | (this.x[n13] & 0xFF));
                                }
                            }
                            imageConsumer.setPixels(this.p, i + this.q, r, n5, e, this.u, 0, r);
                        }
                        else {
                            ac.a(this.x, 0, n6);
                            for (int l = 0; l < n5; ++l) {
                                final int n14 = l * n;
                                final int n15 = l * r;
                                for (int n16 = 0; n16 < r; ++n16) {
                                    int n17 = n14 + (n16 + this.p);
                                    this.v[n15 + n16] = this.x[n17++];
                                }
                            }
                            imageConsumer.setPixels(this.p, i + this.q, r, n5, e, this.v, 0, r);
                        }
                        i += n5;
                        n8 -= n5;
                        if (n8 < n5) {
                            n5 = n8;
                            n6 = n5 * n;
                        }
                    }
                }
            }
            n3 = 3;
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
        finally {
            if (b) {
                this.a(this.k, 0);
            }
            try {
                if (ac != null) {
                    ac.a((Object)null);
                }
            }
            catch (Exception ex2) {}
            try {
                imageConsumer.imageComplete(n3);
            }
            catch (Exception ex3) {}
            this.aa = false;
        }
    }
    
    public final String h() {
        return this.a(this.c, this.d, this.e, this.f, this.g, this.k);
    }
    
    private final void a(final af af, final int n) {
        try {
            if (af != null) {
                boolean b = true;
                if (af instanceof cy && ((cy)af).ba()) {
                    b = false;
                }
                if (b) {
                    if (this.l == null) {
                        this.l = ji.util.d.b(524, this.z);
                    }
                    af.a(new a6(this, 4, "".concat(String.valueOf(String.valueOf(n)))));
                    if (n > 0) {
                        af.a(new a6(this, 1, this.l));
                    }
                    else {
                        af.a(new a6(this, 1, ""));
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final String a(final String s, final int n, final int n2, final int n3, final int n4, final af af) {
        this.j();
        ac ac = null;
        ac ac2 = null;
        boolean b = false;
        try {
            this.aa = true;
            if (s != null) {
                if (this.i == null) {
                    this.i = ji.io.q.a((Object)null, this.z);
                }
                this.j = this.i.o();
                ac = new ac(s, false, false, 0, false, null, this.z);
                ac2 = new ac(this.j, true, false, 0, false, null, this.z);
                final int n5 = n3 * 4;
                if (this.w == null) {
                    this.w = new byte[n5];
                }
                else if (this.w.length != n5) {
                    this.w = new byte[n5];
                }
                final int[] array = new int[n3];
                final double n6 = n3 / n;
                if (n6 == 1.0) {
                    for (int i = 0; i < n3; ++i) {
                        array[i] = i;
                    }
                }
                else {
                    for (int j = 0; j < n3; ++j) {
                        array[j] = (int)(j / n6);
                    }
                }
                final byte[] array2 = new byte[n * 4];
                int red = 0;
                int green = 0;
                int blue = 0;
                boolean b2 = false;
                if (this.h != null) {
                    red = this.h.getRed();
                    green = this.h.getGreen();
                    blue = this.h.getBlue();
                    b2 = true;
                }
                final long currentTimeMillis = System.currentTimeMillis();
                final boolean b3 = af != null;
                int n7 = n4 / 20;
                if (n6 < 1.0) {
                    if (n4 * n3 > ji.util.d.a7()) {
                        this.m = true;
                    }
                    else {
                        final int n8 = (int)Math.round(1.0 / (2.0 * n6));
                        final int n9 = n - 1;
                        final int n10 = n2 - 1;
                        for (int k = 0; k < n4; ++k) {
                            if (b3) {
                                final long currentTimeMillis2 = System.currentTimeMillis();
                                --n7;
                                if ((currentTimeMillis2 - currentTimeMillis > 100 || this.n) && n7 <= 0) {
                                    this.a(af, 100 * k / n4);
                                    n7 = n4 / 20;
                                    b = true;
                                }
                            }
                            final int n11 = (int)(k / n6);
                            for (int l = 0; l < n3; ++l) {
                                try {
                                    int n12 = 0;
                                    final int max = Math.max(array[l] - n8, 0);
                                    final int min = Math.min(array[l] + n8, n9);
                                    final int max2 = Math.max(n11 - n8, 0);
                                    final int min2 = Math.min(n11 + n8, n10);
                                    int n13 = 0;
                                    int n14 = 0;
                                    int n15 = 0;
                                    int n16 = 0;
                                    for (int n17 = max2; n17 < min2; ++n17) {
                                        ac.a((long)(n * n17 * 4));
                                        ac.a(array2, 0, n * 4);
                                        for (int n18 = max; n18 < min; ++n18) {
                                            final int n19 = n18 * 4;
                                            n13 += (array2[n19] & 0xFF);
                                            n14 += (array2[n19 + 1] & 0xFF);
                                            n15 += (array2[n19 + 2] & 0xFF);
                                            n16 += (array2[n19 + 3] & 0xFF);
                                            ++n12;
                                        }
                                    }
                                    final int n20 = l * 4;
                                    this.w[n20] = (byte)(n13 / n12);
                                    this.w[n20 + 1] = (byte)(n14 / n12);
                                    this.w[n20 + 2] = (byte)(n15 / n12);
                                    this.w[n20 + 3] = (byte)(n16 / n12);
                                }
                                catch (Exception ex) {}
                            }
                            if (b2) {
                                for (int n21 = 0; n21 < n3; ++n21) {
                                    final int n22 = n21 * 4;
                                    if ((this.w[n22 + 1] & 0xFF) == red && (this.w[n22 + 2] & 0xFF) == green && (this.w[n22 + 3] & 0xFF) == blue) {
                                        this.w[n22] = 0;
                                    }
                                }
                            }
                            ac2.b(this.w);
                        }
                    }
                }
                else {
                    final int[] array3 = new int[n4];
                    if (n6 == 1.0) {
                        for (int n23 = 0; n23 < n4; ++n23) {
                            array3[n23] = n * n23;
                        }
                    }
                    else {
                        for (int n24 = 0; n24 < n4; ++n24) {
                            array3[n24] = n * (int)(n24 / n6);
                        }
                    }
                    for (int n25 = 0; n25 < n4; ++n25) {
                        if (b3) {
                            final long currentTimeMillis3 = System.currentTimeMillis();
                            --n7;
                            if ((currentTimeMillis3 - currentTimeMillis > 100 || this.n) && n7 <= 0) {
                                this.a(af, 100 * n25 / n4);
                                n7 = n4 / 20;
                                b = true;
                            }
                        }
                        ac.a((long)(array3[n25] * 4));
                        ac.a(array2, 0, n * 4);
                        for (int n26 = 0; n26 < n3; ++n26) {
                            final int n27 = array[n26] * 4;
                            final int n28 = n26 * 4;
                            this.w[n28] = (byte)(array2[n27] & 0xFF);
                            this.w[n28 + 1] = (byte)(array2[n27 + 1] & 0xFF);
                            this.w[n28 + 2] = (byte)(array2[n27 + 2] & 0xFF);
                            this.w[n28 + 3] = (byte)(array2[n27 + 3] & 0xFF);
                        }
                        if (b2) {
                            for (int n29 = 0; n29 < n3; ++n29) {
                                final int n30 = n29 * 4;
                                if ((this.w[n30 + 1] & 0xFF) == red && (this.w[n30 + 2] & 0xFF) == green && (this.w[n30 + 3] & 0xFF) == blue) {
                                    this.w[n30] = 0;
                                }
                            }
                        }
                        ac2.b(this.w);
                    }
                }
            }
        }
        catch (Exception ex2) {}
        finally {
            if (b) {
                this.a(af, 0);
            }
            try {
                if (ac != null) {
                    ac.a((Object)null);
                }
            }
            catch (Exception ex3) {}
            try {
                if (ac2 != null) {
                    ac2.a((Object)null);
                }
            }
            catch (Exception ex4) {}
            this.aa = false;
        }
        return this.j;
    }
    
    public final void i() {
        this.b(true);
    }
    
    public final void b(final boolean b) {
        this.k = null;
        if (this.a != null) {
            this.a.c();
        }
        this.j();
        try {
            if (this.c != null) {
                ji.util.e.c(this.c);
                if (ji.util.e.d(this.c) < 1) {
                    if (this.i == null) {
                        this.i = ji.io.q.a((Object)null, this.z);
                    }
                    this.i.d(this.c);
                }
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        this.ab = null;
        this.y = null;
        this.w = null;
        this.c = null;
        this.i = null;
        this.u = null;
        this.x = null;
        this.v = null;
    }
}
