// 
// Decompiled by Procyon v0.5.30
// 

package ji.render;

import ji.document.ad;
import java.awt.Graphics;
import java.awt.image.PixelGrabber;
import ji.font.j;
import java.awt.Font;
import java.util.Vector;
import ji.awt.d5;
import ji.awt.d4;
import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.awt.Rectangle;
import ji.util.d;
import ji.io.ac;
import java.awt.Component;
import ji.io.h;
import ji.util.i;
import ji.image.du;
import ji.image.dw;
import ji.io.q;
import ji.v1event.af;
import java.awt.image.ColorModel;
import java.awt.Color;

public class dt
{
    final int a = 1073741823;
    private int[] b;
    private byte[] c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private boolean i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private Color n;
    private ColorModel o;
    private af p;
    private q q;
    private dw r;
    private du s;
    private c1 t;
    private String u;
    private boolean v;
    private boolean w;
    private boolean x;
    private boolean y;
    private int z;
    private int aa;
    private int[] ab;
    private static int ac;
    private int ad;
    private int ae;
    private int af;
    private double ag;
    private String ah;
    private boolean ai;
    private boolean aj;
    
    public dt(final String ah) {
        this.b = null;
        this.c = null;
        this.d = 0;
        this.e = 0;
        this.f = 0;
        this.g = 0;
        this.h = 0;
        this.i = false;
        this.j = false;
        this.k = false;
        this.l = false;
        this.m = false;
        this.n = Color.black;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = false;
        this.w = false;
        this.x = false;
        this.y = false;
        this.z = 16777215;
        this.aa = 24;
        this.ab = null;
        this.ad = 0;
        this.ae = 0;
        this.af = 8;
        this.ag = 45.0;
        this.ah = null;
        this.ai = false;
        this.aj = false;
        this.ae = dt.ac++;
        this.ah = ah;
    }
    
    public final void a(final boolean w) {
        this.w = w;
    }
    
    public final void b(final boolean i) {
        this.i = i;
        if (this.i) {
            this.j = false;
        }
    }
    
    public final boolean a() {
        return this.j;
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(new StringBuffer("jiImage (").append(this.ae).append(" - ").append(this.f).append(", ").append(this.g).append(", ").append(this.d).append(", ").append(this.e).append(", test=").append(this.i).append(") - ").append(super.toString())));
    }
    
    public final void a(final ColorModel o, final boolean m, final dw dw, final int f, final int g, final int d, final int e, final af p14, final int h, final boolean x, final boolean y, final boolean ai, final int aa, final int ad) {
        this.m();
        this.h = h;
        this.b = null;
        this.c = null;
        this.m = m;
        this.x = x;
        this.y = y;
        this.ai = ai;
        this.aa = aa;
        this.ad = ad;
        if (ad <= 1) {
            this.aj = false;
        }
        this.c = dw.o();
        if (ji.util.i.c(85)) {
            h.d(this.ah, "setParameters1, setting bytes");
        }
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.o = o;
        this.p = p14;
        this.ab = null;
    }
    
    public final void a(final ColorModel o, final boolean m, final du s, final int f, final int g, final int d, final int e, final af p14, final int h, final boolean x, final boolean y, final boolean ai, final int aa, final int ad) {
        this.m();
        this.h = h;
        this.c = null;
        this.b = null;
        this.m = m;
        this.x = x;
        this.y = y;
        this.ai = ai;
        this.aa = aa;
        this.ad = ad;
        if (ad <= 1) {
            this.aj = false;
        }
        if (s.c()) {
            if (ji.util.i.c(85)) {
                h.d(this.ah, "setParameters2, isUsingFile=true, setting intLines");
            }
            this.s = s;
        }
        else {
            if (ji.util.i.c(85)) {
                h.d(this.ah, "setParameters2, isUsingFile=false, setting ints");
            }
            this.b = s.j();
        }
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.o = o;
        this.p = p14;
        this.ab = null;
    }
    
    public final void a(final ColorModel o, final boolean m, final int[] b, final int f, final int g, final int d, final int e, final af p12, final int h, final boolean ai, final int aa, final int ad) {
        this.m();
        this.h = h;
        this.c = null;
        this.b = b;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.o = o;
        this.p = p12;
        this.m = m;
        this.ai = ai;
        this.aa = aa;
        this.ad = ad;
        if (ji.util.i.c(85)) {
            h.d(this.ah, "setParameters3, setting ints");
        }
        if (ad <= 1) {
            this.aj = false;
        }
        this.ab = null;
    }
    
    public final void a(final ColorModel o, final boolean m, final byte[] c, final int f, final int g, final int d, final int e, final af p12, final int h, final boolean ai, final int aa, final int ad) {
        this.m();
        this.h = h;
        this.b = null;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.o = o;
        this.p = p12;
        this.m = m;
        this.ai = ai;
        this.aa = aa;
        this.ad = ad;
        if (ji.util.i.c(85)) {
            h.d(this.ah, "setParameters4, setting bytes");
        }
        if (ad <= 1) {
            this.aj = false;
        }
        this.ab = null;
    }
    
    public int b() {
        if (this.s()) {
            return this.aa;
        }
        return 24;
    }
    
    public final int c() {
        return this.af;
    }
    
    public final String a(final Component component) {
        String s = null;
        this.af = this.ad;
        try {
            if (this.b != null) {
                this.b(component);
                s = this.q.b("ri");
                final ac ac = new ac(s, true, false, 0, false, component, this.ah);
                ac.b(this.b);
                ac.a(component);
                this.af = 24;
            }
            else if (this.s != null) {
                if (this.s.c()) {
                    s = this.s.a(component);
                }
                else {
                    this.b(component);
                    s = this.q.b("ril");
                    final ac ac2 = new ac(s, true, false, 0, false, component, this.ah);
                    ac2.b(this.s.j());
                    ac2.a(component);
                }
                this.af = 24;
            }
            else {
                this.b(component);
                s = this.q.b("rb");
                final ac ac3 = new ac(s, true, false, 0, false, component, this.ah);
                ac3.b(this.c);
                ac3.a(component);
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        return s;
    }
    
    public final du d() {
        return this.s;
    }
    
    public final byte[] e() {
        return this.c;
    }
    
    public final int[] f() {
        return this.b;
    }
    
    public final int g() {
        return this.d;
    }
    
    public final int h() {
        return this.e;
    }
    
    public final int i() {
        return this.f;
    }
    
    public final int j() {
        return this.g;
    }
    
    public Rectangle k() {
        return new Rectangle(this.f, this.g, this.d, this.e);
    }
    
    public final void l() {
        this.q = null;
        this.m();
    }
    
    private final void v() {
        try {
            if (this.t != null) {
                this.t.i();
                this.t = null;
            }
            this.u = null;
        }
        catch (Exception ex) {}
    }
    
    public final void m() {
        this.v();
        this.b = null;
        this.c = null;
        this.o = null;
        this.r = null;
        this.ab = null;
        if (this.v && this.s != null) {
            this.s.p();
        }
        this.s = null;
    }
    
    public final void c(final boolean b) {
        if (this.b == null) {
            try {
                if (this.s != null) {
                    if (ji.util.i.c(85)) {
                        ji.io.h.d(this.ah, "ConvertTo24bitA");
                    }
                    if (this.w) {
                        if (this.b == null) {
                            this.b = this.s.j();
                        }
                        else if (this.b.length < this.d * this.e) {
                            this.b = this.s.j();
                        }
                        final int length = this.b.length;
                        final int[] array = new int[this.d];
                        for (int i = 0; i < this.d; ++i) {
                            array[i] = this.z;
                        }
                        for (int j = 0; j < this.e; ++j) {
                            try {
                                System.arraycopy(array, 0, this.b, j * this.d, this.d);
                            }
                            catch (Exception ex2) {
                                if (this.b == null) {
                                    break;
                                }
                            }
                        }
                    }
                    else {
                        this.b = this.s.j();
                    }
                }
                else {
                    if (ji.util.i.c(85)) {
                        ji.io.h.d(this.ah, "ConvertTo24bitB");
                    }
                    if (this.ab == null) {
                        this.ab = new int[256];
                        for (int k = 0; k < 256; ++k) {
                            this.ab[k] = this.o.getRGB(k);
                        }
                    }
                    if (!b && (this.d * this.e <= ji.util.d.b(this.h, this.x, this.y, this.ah) / 4 || ji.util.d.di() == 1 || ji.util.d.a(this.x, this.ah))) {
                        final int n = this.d * this.e;
                        if (this.b == null) {
                            this.b = new int[n];
                        }
                        else if (this.b.length < n) {
                            this.b = new int[n];
                        }
                        if (this.w) {
                            final int[] array2 = new int[this.d];
                            for (int l = 0; l < this.d; ++l) {
                                array2[l] = this.z;
                            }
                            for (int n2 = 0; n2 < this.e; ++n2) {
                                try {
                                    System.arraycopy(array2, 0, this.b, n2 * this.d, this.d);
                                }
                                catch (Exception ex3) {
                                    if (this.b == null) {
                                        break;
                                    }
                                }
                            }
                        }
                        else if (this.c != null) {
                            for (int n3 = 0; n3 < n; ++n3) {
                                try {
                                    this.b[n3] = (0xFF000000 | this.ab[this.c[n3] & 0xFF]);
                                }
                                catch (Exception ex4) {
                                    if (this.b == null) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    else {
                        this.s = new du(this.e, this.d, null, ji.util.d.a(ji.util.d.cd(), this.x, this.y, this.ah), this.ah, this.x, this.ad, true);
                        this.v = true;
                        final int[] array3 = new int[this.d];
                        if (this.w) {
                            for (int n4 = 0; n4 < this.d; ++n4) {
                                array3[n4] = this.z;
                            }
                        }
                        for (int n5 = 0; n5 < this.e; ++n5) {
                            final int n6 = n5 * this.d;
                            if (!this.w) {
                                for (int n7 = 0; n7 < this.d; ++n7) {
                                    try {
                                        array3[n7] = (0xFF000000 | this.ab[this.c[n6 + n7] & 0xFF]);
                                    }
                                    catch (Exception ex5) {
                                        if (this.c == null) {
                                            break;
                                        }
                                    }
                                }
                            }
                            this.s.a(n5, array3);
                        }
                    }
                }
                this.c = null;
                this.o = ColorModel.getRGBdefault();
            }
            catch (Exception ex) {
                if (ji.util.d.cy()) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public final int n() {
        if (this.b != null) {
            return 24;
        }
        if (this.c != null) {
            return 8;
        }
        if (this.s != null) {
            return 24;
        }
        return 24;
    }
    
    public final Image d(final boolean b) {
        ImageProducer imageProducer = null;
        if (ji.util.i.c(85)) {
            ji.io.h.d(this.ah, "createImage 0");
            ji.io.h.d(this.ah, String.valueOf(String.valueOf(new StringBuffer("createImage w*h=").append(this.d * this.e).append(" trigger=").append(ji.util.d.b(this.h, this.x, this.y, this.ah) / 4).append(" oper mode=").append(ji.util.d.di()).append(" printing=").append(this.x).append(" ramr=").append(ji.util.d.a(this.x, this.ah)))));
            try {
                if (this.b != null) {
                    ji.io.h.d(this.ah, String.valueOf(String.valueOf(new StringBuffer("createImage ints[").append(this.b.length).append("]"))));
                }
                if (this.c != null) {
                    ji.io.h.d(this.ah, String.valueOf(String.valueOf(new StringBuffer("createImage bytes[").append(this.c.length).append("]"))));
                }
                if (this.s != null) {
                    ji.io.h.d(this.ah, "createImage intLines filename=".concat(String.valueOf(String.valueOf(this.s.a(null)))));
                }
            }
            catch (Exception ex2) {}
        }
        try {
            if (this.b != null) {
                if (ji.util.i.c(85)) {
                    ji.io.h.d(this.ah, "createImage A");
                }
                imageProducer = new MemoryImageSource(this.d, Math.min(this.e, this.b.length / this.d), this.o, this.b, 0, this.d);
            }
            else if (this.c != null) {
                if (ji.util.i.c(85)) {
                    ji.io.h.d(this.ah, "createImage B");
                }
                imageProducer = new MemoryImageSource(this.d, Math.min(this.e, this.c.length / this.d), this.o, this.c, 0, this.d);
            }
            else if (this.s != null) {
                if (b || (this.d * this.e >= ji.util.d.b(this.h, this.x, this.y, this.ah) / 4 && ji.util.d.di() != 1 && !ji.util.d.a(this.x, this.ah))) {
                    if (ji.util.i.c(85)) {
                        ji.io.h.d(this.ah, "createImage D");
                    }
                    this.u = this.s.a(null);
                    this.t = new c1(this.u, this.d, this.e, this.d, this.e, 24, null, this.p, this.ah);
                    return Toolkit.getDefaultToolkit().createImage(this.t);
                }
                if (ji.util.i.c(85)) {
                    ji.io.h.d(this.ah, "createImage C");
                }
                imageProducer = new MemoryImageSource(this.d, this.e, this.s.e(), this.s.j(), 0, this.d);
            }
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
        if (imageProducer != null) {
            return Toolkit.getDefaultToolkit().createImage(imageProducer);
        }
        return null;
    }
    
    public final Image a(final int n, final int n2, int min, int min2) {
        if (ji.util.i.c(85)) {
            ji.io.h.d(this.ah, "createImage(xywh) 0");
        }
        ImageProducer imageProducer = null;
        try {
            min = Math.min(min, this.d - n - 1);
            min2 = Math.min(min2, this.e - n2 - 1);
            if (this.b != null) {
                if (ji.util.i.c(85)) {
                    ji.io.h.d(this.ah, "createImage(xywh) A");
                }
                imageProducer = new MemoryImageSource(min, min2, this.o, this.b, n2 * this.d + n, this.d);
            }
            else if (this.c != null) {
                if (ji.util.i.c(85)) {
                    ji.io.h.d(this.ah, "createImage(xywh) B");
                }
                imageProducer = new MemoryImageSource(min, min2, this.o, this.c, n2 * this.d + n, this.d);
            }
            else if (this.s != null) {
                if (ji.util.i.c(85)) {
                    ji.io.h.d(this.ah, "createImage(xywh) C");
                }
                if (this.s.c()) {
                    if (ji.util.i.c(85)) {
                        ji.io.h.d(this.ah, "createImage(xywh) D");
                    }
                    this.s.a(0, 0, 1.0, 1.0, 0, 0);
                    if (min * min2 >= ji.util.d.b(this.h, this.x, this.y, this.ah) / 4 && ji.util.d.di() != 1 && !ji.util.d.a(this.x, this.ah)) {
                        if (ji.util.i.c(85)) {
                            ji.io.h.d(this.ah, "createImage(xywh) F");
                        }
                        this.b(null);
                        final String o = this.q.o();
                        final ac ac = new ac(o, true, false, 0, false, null, this.ah);
                        try {
                            final int n3 = n + min - 1;
                            for (int i = 0; i < min2; ++i) {
                                ac.b(this.s.a(i + n2, n, n3));
                            }
                        }
                        finally {
                            ac.a((Object)null);
                        }
                        this.t = new c1(o, min, min2, min, min2, 24, null, this.p, this.ah);
                        return Toolkit.getDefaultToolkit().createImage(this.t);
                    }
                    if (ji.util.i.c(85)) {
                        ji.io.h.d(this.ah, "createImage(xywh) E");
                    }
                    final int[] array = new int[min * min2];
                    final int n4 = n + min - 1;
                    for (int j = n2; j < n2 + min2; ++j) {
                        final int[] a = this.s.a(j, n, n4);
                        System.arraycopy(a, 0, array, (j - n2) * min, a.length);
                    }
                    imageProducer = new MemoryImageSource(min, min2, this.s.e(), array, 0, min);
                }
                else {
                    if (ji.util.i.c(85)) {
                        ji.io.h.d(this.ah, "createImage(xywh) G");
                    }
                    imageProducer = new MemoryImageSource(min, min2, this.o, this.s.j(), n2 * this.d + n, this.d);
                }
            }
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
        if (imageProducer != null) {
            return Toolkit.getDefaultToolkit().createImage(imageProducer);
        }
        return null;
    }
    
    public final void a(final Color n) {
        this.n = n;
    }
    
    public final Color o() {
        if (this.s()) {
            return Color.black;
        }
        return this.n;
    }
    
    public final void e(final boolean l) {
        this.l = l;
    }
    
    public final boolean p() {
        return this.l;
    }
    
    public final boolean a(final int n, final int n2, final int n3, final int n4, final Component component, final boolean b) {
        boolean a = false;
        if (this.x()) {
            a = this.a(n, n2, n3, n4, this.d, this.e, false, false, this.o(), b);
        }
        return a;
    }
    
    private final boolean a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final boolean b2, final Color color, final boolean b3) {
        boolean b4 = false;
        try {
            final int n7 = 0xFF000000 | color.getRGB();
            final int max = Math.max(Math.abs(n3 - n), Math.abs(n4 - n2));
            if (max > 1073741823) {
                return b4;
            }
            if (max > 0) {
                if (this.i) {
                    this.j = true;
                    return b4;
                }
                if (this.b == null) {
                    this.c(b3);
                }
            }
            if (this.b != null) {
                b4 = true;
                final int length = this.b.length;
                if (max > 0) {
                    if (n4 == n2 && n3 != n && !b2 && !b) {
                        final int min = Math.min(n, n3);
                        final int max2 = Math.max(n, n3);
                        if (max2 >= 0 && n2 >= 0 && n2 < n6) {
                            final int max3 = Math.max(min, 0);
                            final int n8 = Math.min(max2, n5 - 1) - max3 + 1;
                            if (n8 > 0) {
                                final int[] a = ji.util.d.a(n7, n8, this.w());
                                try {
                                    System.arraycopy(a, 0, this.b, n2 * n5 + max3, n8);
                                }
                                catch (Exception ex2) {}
                            }
                        }
                    }
                    else {
                        final double n9 = (n3 - n) / max;
                        final double n10 = (n4 - n2) / max;
                        double n11 = n;
                        double n12 = n2;
                        if (b2) {
                            int n13 = -(int)n11 - 1;
                            int n14 = -(int)n12 - 1;
                            for (int i = 0; i < max; ++i) {
                                final int n15 = (int)n11;
                                final int n16 = (int)n12;
                                if (n15 >= 0 && n16 >= 0 && (n15 != n13 || n16 != n14)) {
                                    final int n17 = n16 * n5 + n15;
                                    if (n17 < length && n17 > -1 && n16 < n6 && n15 < n5) {
                                        final int[] b5 = this.b;
                                        final int n18 = n17;
                                        b5[n18] &= n7;
                                    }
                                }
                                n13 = n15;
                                n14 = n16;
                                n11 += n9;
                                n12 += n10;
                            }
                        }
                        else if (b) {
                            final int red = color.getRed();
                            final int green = color.getGreen();
                            color.getBlue();
                            int n19 = -(int)n11 - 1;
                            int n20 = -(int)n12 - 1;
                            for (int j = 0; j < max; ++j) {
                                final int n21 = (int)n11;
                                final int n22 = (int)n12;
                                if (n21 >= 0 && n22 >= 0 && (n21 != n19 || n22 != n20)) {
                                    final int n23 = n22 * n5 + n21;
                                    if (n23 < length && n23 > -1 && n22 < n6 && n21 < n5) {
                                        final int n24 = this.b[n23];
                                        this.b[n23] = (0xFF000000 | (((n24 & 0xFF0000) >> 16) + red) / 2 << 16 | (((n24 & 0xFF00) >> 8) + green) / 2 << 8 | ((n24 & 0xFF) + green) / 2);
                                    }
                                }
                                n19 = n21;
                                n20 = n22;
                                n11 += n9;
                                n12 += n10;
                            }
                        }
                        else {
                            int n25 = -(int)n11 - 1;
                            int n26 = -(int)n12 - 1;
                            for (int k = 0; k < max; ++k) {
                                final int n27 = (int)n11;
                                final int n28 = (int)n12;
                                if (n27 >= 0 && n28 >= 0 && (n27 != n25 || n28 != n26)) {
                                    final int n29 = n28 * n5 + n27;
                                    if (n29 < length && n29 > -1 && n28 < n6 && n27 < n5) {
                                        this.b[n29] = n7;
                                    }
                                }
                                n25 = n27;
                                n26 = n28;
                                n11 += n9;
                                n12 += n10;
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return b4;
    }
    
    public final boolean b(final int n, final int n2, final int n3, final int n4, final Component component, final boolean b) {
        boolean b2 = false;
        if (this.x()) {
            if (this.a(n, n2, n + n3, n2, n3, n4, false, this.l, this.o(), b)) {
                b2 = true;
            }
            if (this.a(n + n3, n2, n + n3, n2 + n4, n3, n4, false, this.l, this.o(), b)) {
                b2 = true;
            }
            if (this.a(n + n3, n2 + n4, n, n2 + n4, n3, n4, false, this.l, this.o(), b)) {
                b2 = true;
            }
            if (this.a(n, n2 + n4, n, n2, n3, n4, false, this.l, this.o(), b)) {
                b2 = true;
            }
        }
        return b2;
    }
    
    public final boolean c(final int n, final int n2, final int n3, final int n4, final Component component, final boolean b) throws Exception {
        boolean a = false;
        if (this.x()) {
            a = this.a(n, n2, n3, n4, component, -1, this.o(), this.l, b);
        }
        return a;
    }
    
    private final boolean a(final int n, final int n2, final int n3, final int n4, final Component component, final int n5, Color black, final boolean b, final boolean b2) throws Exception {
        boolean b3 = false;
        if (this.x()) {
            try {
                final int n6 = n + n3;
                final int n7 = n2 + n4;
                final int min = Math.min(Math.max(n2, 0), this.e);
                final int min2 = Math.min(Math.max(n7, 0), this.e);
                final int min3 = Math.min(Math.max(n, 0), this.d);
                final int min4 = Math.min(Math.max(n6, 0), this.d);
                int w = 1;
                int w2 = 1;
                if (this.s() && b) {
                    w = this.w();
                    w2 = this.w();
                    black = Color.black;
                }
                final int n8 = 0xFF000000 | black.getRGB();
                if (min < min2 && min3 < min4) {
                    if (this.i) {
                        this.j = true;
                        return b3;
                    }
                    if (this.b == null) {
                        this.c(b2);
                    }
                    b3 = true;
                    if (n5 > 0) {
                        final int n9 = n7 - n5;
                        final int n10 = n2 + n5;
                        final int n11 = n6 - n5;
                        final int n12 = n + n5;
                        final int length = this.b.length;
                        if (b) {
                            for (int i = n2; i < n10; i += w2) {
                                final int n13 = i * this.d;
                                for (int j = min3; j < min4; j += w) {
                                    final int n14 = n13 + j;
                                    if (n14 > -1 && n14 < length) {
                                        final int[] b4 = this.b;
                                        final int n15 = n14;
                                        b4[n15] &= n8;
                                    }
                                }
                            }
                            for (int k = n9; k < min2; k += w2) {
                                final int n16 = k * this.d;
                                for (int l = min3; l < min4; l += w) {
                                    final int n17 = n16 + l;
                                    if (n17 > -1 && n17 < length) {
                                        final int[] b5 = this.b;
                                        final int n18 = n17;
                                        b5[n18] &= n8;
                                    }
                                }
                            }
                            for (int n19 = min; n19 < min2; n19 += w2) {
                                final int n20 = n19 * this.d;
                                for (int n21 = min3; n21 < n12; n21 += w) {
                                    final int n22 = n20 + n21;
                                    if (n22 > -1 && n22 < length) {
                                        final int[] b6 = this.b;
                                        final int n23 = n22;
                                        b6[n23] &= n8;
                                    }
                                }
                                for (int n24 = n11; n24 < min4; n24 += w) {
                                    final int n25 = n20 + n24;
                                    if (n25 > -1 && n25 < length) {
                                        final int[] b7 = this.b;
                                        final int n26 = n25;
                                        b7[n26] &= n8;
                                    }
                                }
                            }
                        }
                        else {
                            for (int n27 = min; n27 < n10; n27 += w2) {
                                final int n28 = n27 * this.d;
                                for (int n29 = min3; n29 < min4; n29 += w) {
                                    final int n30 = n28 + n29;
                                    if (n30 > -1 && n30 < length) {
                                        this.b[n30] = n8;
                                    }
                                }
                            }
                            for (int n31 = n9; n31 < min2; n31 += w2) {
                                final int n32 = n31 * this.d;
                                for (int n33 = min3; n33 < min4; n33 += w) {
                                    final int n34 = n32 + n33;
                                    if (n34 > -1 && n34 < length) {
                                        this.b[n34] = n8;
                                    }
                                }
                            }
                            for (int n35 = min; n35 < min2; n35 += w2) {
                                final int n36 = n35 * this.d;
                                for (int n37 = min3; n37 < n12; n37 += w) {
                                    final int n38 = n36 + n37;
                                    if (n38 > -1 && n38 < length) {
                                        this.b[n38] = n8;
                                    }
                                }
                                for (int n39 = n11; n39 < min4; n39 += w) {
                                    final int n40 = n36 + n39;
                                    if (n40 > -1 && n40 < length) {
                                        this.b[n40] = n8;
                                    }
                                }
                            }
                        }
                    }
                    else if (b) {
                        for (int n41 = min; n41 < min2; n41 += w2) {
                            final int n42 = n41 * this.d;
                            for (int n43 = min3; n43 < min4; n43 += w) {
                                final int[] b8 = this.b;
                                final int n44 = n42 + n43;
                                b8[n44] &= n8;
                            }
                        }
                    }
                    else {
                        final int min5 = Math.min(min3, min4);
                        final int n45 = Math.max(min3, min4) - min5;
                        final int[] a = ji.util.d.a(n8, n45, this.w());
                        for (int n46 = min; n46 < min2; n46 += w2) {
                            System.arraycopy(a, 0, this.b, n46 * this.d + min5, n45);
                        }
                    }
                }
            }
            catch (Exception ex) {
                ji.util.d.a(ex);
            }
        }
        return b3;
    }
    
    public final int[] q() {
        if (this.b != null) {
            return this.b;
        }
        if (this.s != null) {
            return this.s.j();
        }
        return null;
    }
    
    public final byte[] r() {
        if (this.c != null) {
            return this.c;
        }
        if (this.r != null) {
            return this.r.o();
        }
        return null;
    }
    
    private final boolean a(final d4 d4, final boolean b, final int n, final int n2, final boolean b2) {
        boolean b3 = false;
        d5 d5 = null;
        for (int i = 0; i < d4.b(); ++i) {
            final d5 a = d4.a(i);
            if (d5 != null) {
                b3 = this.a((int)d5.a, (int)d5.b, (int)a.a, (int)a.b, n, n2, false, this.l, this.o(), b2);
            }
            d5 = a;
        }
        if (b) {
            final d5 a2 = d4.a(0);
            if (d5 != null) {
                b3 = this.a((int)d5.a, (int)d5.b, (int)a2.a, (int)a2.b, n, n2, false, this.l, this.o(), b2);
            }
        }
        return b3;
    }
    
    public final boolean a(final d4 d4, final Component component, final boolean b) {
        return this.a(d4, true, component, b);
    }
    
    public final boolean a(final d4 d4, final boolean b, final Component component, final boolean b2) {
        boolean a = false;
        if (this.x()) {
            a = this.a(d4, b, this.d, this.e, b2);
        }
        return a;
    }
    
    private final boolean c(final d4 d4, final Component component, final boolean b) {
        final int b2 = d4.b();
        final int[] c = d4.c();
        final int[] d5 = d4.d();
        try {
            if (b2 == 4) {
                int min = c[0];
                int min2 = d5[0];
                int max = c[0];
                int max2 = d5[0];
                for (int i = 0; i < b2; ++i) {
                    min = Math.min(min, c[i]);
                    min2 = Math.min(min2, d5[i]);
                    max = Math.max(max, c[i]);
                    max2 = Math.max(max2, d5[i]);
                }
                final d4 a = d4.a();
                int n = 0;
                for (int n2 = 0; n2 < b2 && n == 0; ++n2) {
                    final d5 a2 = a.a(n2);
                    if (a2.a == min && a2.b == min2) {
                        n = 1;
                    }
                }
                if (n != 0) {
                    int n3 = 0;
                    for (int n4 = 0; n4 < b2 && n3 == 0; ++n4) {
                        final d5 a3 = a.a(n4);
                        if (a3.a == max && a3.b == min2) {
                            n3 = 1;
                        }
                    }
                    if (n3 != 0) {
                        int n5 = 0;
                        for (int n6 = 0; n6 < b2 && n5 == 0; ++n6) {
                            final d5 a4 = a.a(n6);
                            if (a4.a == max && a4.b == max2) {
                                n5 = 1;
                            }
                        }
                        if (n5 != 0) {
                            int n7 = 0;
                            for (int n8 = 0; n8 < b2 && n7 == 0; ++n8) {
                                final d5 a5 = a.a(n8);
                                if (a5.a == min && a5.b == max2) {
                                    n7 = 1;
                                }
                            }
                            if (n7 != 0) {
                                this.c(min, min2, max - min, max2 - min2, component, b);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public final boolean b(final d4 d4, final Component component, final boolean b) {
        boolean a = false;
        if (this.x()) {
            try {
                if (!this.c(d4, component, b)) {
                    a = this.a(d4, d4.getBounds(), component, b);
                }
            }
            catch (Exception ex) {}
        }
        return a;
    }
    
    public final boolean s() {
        return this.ai;
    }
    
    public final void f(final boolean ai) {
        this.ai = ai;
    }
    
    private final int w() {
        if (this.s() && this.l) {
            return 2;
        }
        return 1;
    }
    
    private final boolean a(final d4 d4, final Rectangle rectangle, final Component component, final boolean b) throws Exception {
        boolean b2 = false;
        final int b3 = d4.b();
        if (b3 > 1 && b3 < 10000) {
            final int g = this.g();
            final int h = this.h();
            final int max = Math.max(rectangle.y, 0);
            final int min = Math.min(h, rectangle.height + 1);
            final int y = rectangle.y;
            final int n = 0;
            final Vector[] array = new Vector[min];
            d5 a = d4.a(0);
            for (int i = 1; i <= b3; ++i) {
                d5 d5;
                if (i < b3) {
                    d5 = d4.a(i);
                }
                else {
                    d5 = d4.a(0);
                }
                final int n2 = (int)Math.min(d5.b, a.b);
                final int n3 = (int)Math.max(d5.b, a.b);
                final double n4 = (d5.a - a.a) / (d5.b - a.b);
                final double n5 = a.b;
                if (n3 - n2 < 100000) {
                    for (int j = n2; j < n3; ++j) {
                        final int n6 = j - max;
                        final int max2 = Math.max((int)(a.a + Math.round(j - n5) * n4), 0);
                        if (n6 >= 0 && n6 < min) {
                            if (array[n6] == null) {
                                array[n6] = new Vector(d4.b() * 4);
                            }
                            final int size = array[n6].size();
                            if (size < 1) {
                                array[n6].addElement(new Integer(max2));
                            }
                            else {
                                boolean b4 = false;
                                for (int k = 0; k < size; ++k) {
                                    if ((int)array[n6].elementAt(k) >= max2) {
                                        array[n6].insertElementAt(new Integer(max2), Math.max(k, 0));
                                        b4 = true;
                                        break;
                                    }
                                }
                                if (!b4) {
                                    array[n6].addElement(new Integer(max2));
                                }
                            }
                        }
                    }
                }
                a = d5;
            }
            int w = 1;
            int w2 = 1;
            if (this.s() && this.l) {
                w2 = this.w();
                w = this.w();
                this.n = Color.black;
            }
            final int n7 = 0xFF000000 | this.o().getRGB();
            if (this.i) {
                final int n8 = this.g() * this.h();
                for (int n9 = 0; n9 < min && !this.j; ++n9) {
                    if (array[n9] != null) {
                        final int size2 = array[n9].size();
                        final int n10 = (n9 + max + n) * g;
                        for (int l = 0; l < size2; l += 2) {
                            final int min2 = Math.min(array[n9].elementAt(l), g - 1);
                            if (l + 1 < size2) {
                                final int min3 = Math.min(array[n9].elementAt(l + 1), g - 1);
                                if (min2 != min3 && min3 > 0) {
                                    for (int n11 = min2; n11 <= min3; ++n11) {
                                        final int n12 = n10 + n11;
                                        if (n12 < n8 && n12 >= 0) {
                                            this.j = true;
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                return b2;
            }
            if (this.b == null) {
                this.c(b);
            }
            final int length = this.b.length;
            b2 = true;
            if (this.l) {
                for (int n13 = 0; n13 < min; n13 += w) {
                    if (array[n13] != null) {
                        final int size3 = array[n13].size();
                        final int n14 = (n13 + max + n) * g;
                        for (int n15 = 0; n15 < size3; n15 += 2) {
                            final int min4 = Math.min(array[n13].elementAt(n15), g - 1);
                            if (n15 + 1 < size3) {
                                final int min5 = Math.min(array[n13].elementAt(n15 + 1), g - 1);
                                if (min4 != min5 && min5 > 0 && this.b != null) {
                                    for (int n16 = min4; n16 <= min5; n16 += w2) {
                                        final int n17 = n14 + n16;
                                        if (n17 < length && n17 >= 0) {
                                            final int[] b5 = this.b;
                                            final int n18 = n17;
                                            b5[n18] &= n7;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            else {
                if (this.b == null) {
                    final int[] array2 = new int[this.d];
                    for (int n19 = 0; n19 < this.d; ++n19) {
                        array2[n19] = n7;
                    }
                }
                final int n20;
                final int[] a2 = ji.util.d.a(n7, n20 = rectangle.width + 1, this.w());
                final int n21 = g - 1;
                for (int n22 = 0; n22 < min; n22 += w) {
                    if (array[n22] != null) {
                        final int size4 = array[n22].size();
                        final int n23 = (n22 + max + n) * g;
                        for (int n24 = 0; n24 < size4; n24 += 2) {
                            final int min6 = Math.min(array[n22].elementAt(n24), n21);
                            if (n24 + 1 < size4) {
                                final int min7 = Math.min(array[n22].elementAt(n24 + 1), n21);
                                if (min6 != min7 && min7 > 0) {
                                    final int n25 = n23 + min6;
                                    if (n25 < length && n25 >= 0) {
                                        final int min8 = Math.min(min7 - min6 + 1, n20);
                                        try {
                                            System.arraycopy(a2, 0, this.b, n25, min8);
                                        }
                                        catch (Exception ex) {}
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return b2;
    }
    
    public final boolean a(final int[] array, final int[] array2, final int n, final Component component, final boolean b) {
        boolean a = false;
        if (this.x()) {
            a = this.a(new d4(array, array2, n), false, this.d, this.e, b);
        }
        return a;
    }
    
    private final void b(final Component component) {
        if (this.q == null) {
            this.q = ji.io.q.a(component, this.ah);
        }
    }
    
    public final ColorModel t() {
        return this.o;
    }
    
    private final void a(final int[] array, final int n, final int n2, final int n3, final int n4, final Color color, final Component component) {
        final int min = Math.min(n2 + n4, this.e);
        final int min2 = Math.min(n + n3, this.d);
        final int max = Math.max(Math.min(n3, n4) / 100, 5);
        final int[][] array2 = new int[max + 1][max + 1];
        try {
            for (int i = 0; i < max; ++i) {
                for (int j = 0; j < max; ++j) {
                    array2[i][j] = -1;
                }
            }
            int n5 = 0;
            for (int k = 0; k < max; ++k) {
                array2[k][n5] = -9408400;
                array2[k][max - n5] = -9408400;
                ++n5;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        int n6 = 0;
        for (int l = n2; l < min; ++l) {
            if (l >= 0) {
                final int n7 = (l - n2) * n3;
                final int n8 = l * this.d;
                if (n7 >= 0) {
                    int n9 = 0;
                    for (int n10 = n; n10 < min2; ++n10) {
                        if (n10 >= 0) {
                            final int n11 = array2[n6][n9];
                            if (n11 != 0 && (n11 & 0xFF000000) != 0x0) {
                                this.b[n8 + n10] = n11;
                            }
                        }
                        if (++n9 >= max) {
                            n9 = 0;
                        }
                    }
                }
            }
            if (++n6 >= max) {
                n6 = 0;
            }
        }
    }
    
    private final boolean a(final double n, final int[] array, final dw dw, final int n2, final int n3, final int n4, final int n5, final Color color, final Component component, final boolean b, final boolean b2, final boolean b3) {
        boolean b4 = false;
        boolean b5 = false;
        try {
            int p12 = 0;
            int j = 0;
            int l = 0;
            boolean b6 = false;
            if (dw != null) {
                dw.s();
                b6 = false;
                p12 = dw.p();
                j = dw.j();
                l = dw.l();
            }
            final int min = Math.min(n3 + n5, this.e);
            final int min2 = Math.min(n2 + n4, this.d);
            if (p12 == 90 || p12 == 270) {
                final int n6 = j;
                j = l;
                l = n6;
            }
            boolean b7 = false;
            if (dw == null) {
                b7 = true;
            }
            else if (!dw.g()) {
                b7 = true;
            }
            if (this.i) {
                this.j = true;
                return b4;
            }
            if (this.b == null) {
                this.c(b3);
            }
            if (b7) {
                b4 = true;
                this.a(array, n2, n3, n4, n5, color, component);
            }
            else {
                b4 = true;
                switch (p12) {
                    case 0: {
                        final int max = Math.max(0, -n2);
                        final int min3 = Math.min(max + Math.min(this.g(), j + n2), dw.j());
                        for (int min4 = Math.min(n3 + dw.l(), min), i = n3; i < min4; ++i) {
                            if (i > -1) {
                                final int n7 = i - n3;
                                final int n8 = i * this.d;
                                if (n7 > -1) {
                                    final int[] b8 = dw.b(n7, max, min3, b6);
                                    if (b) {
                                        for (int k = n2; k < min2; ++k) {
                                            if (k > -1) {
                                                final int n9 = k - n2 - max;
                                                if (n9 > -1 && n9 < b8.length) {
                                                    final int n10 = b8[n9];
                                                    if (n10 != 0 && (n10 & 0xFF000000) != 0x0) {
                                                        if ((n10 & 0xFF000000) >> 24 == 127) {
                                                            this.b[n8 + k] &= (n10 | 0xFF000000);
                                                        }
                                                        else {
                                                            this.b[n8 + k] = (n10 | 0xFF000000);
                                                        }
                                                        b5 = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    else if (b2) {
                                        for (int n11 = n2; n11 < min2; ++n11) {
                                            if (n11 > -1) {
                                                final int n12 = n11 - n2 - max;
                                                if (n12 > -1 && n12 < b8.length) {
                                                    final int n13 = b8[n12];
                                                    if (n13 != 0 && (n13 & 0xFF000000) != 0x0) {
                                                        final int n14 = n8 + n11;
                                                        if (this.b[n14] == -1) {
                                                            this.b[n8 + n11] = (n13 | 0xFF000000);
                                                        }
                                                        else {
                                                            final int[] b9 = this.b;
                                                            final int n15 = n14;
                                                            b9[n15] &= (n13 | 0xFF000000);
                                                        }
                                                        b5 = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    else {
                                        for (int n16 = n2; n16 < min2; ++n16) {
                                            if (n16 > -1) {
                                                final int n17 = n16 - n2 - max;
                                                if (n17 > -1 && n17 < b8.length) {
                                                    final int n18 = b8[n17];
                                                    if (n18 != 0 && (n18 & 0xFF000000) != 0x0) {
                                                        this.b[n8 + n16] = (n18 | 0xFF000000);
                                                        b5 = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case 180: {
                        final int min5 = Math.min(Math.max(0, j + n2), dw.j());
                        final int max2 = Math.max(0, j + n2 - this.g());
                        for (int n19 = n3; n19 < min; ++n19) {
                            if (n19 > -1) {
                                final int n20 = l - (n19 - n3) - 1;
                                final int n21 = n19 * this.d;
                                if (n20 > -1) {
                                    final int[] b10 = dw.b(n20, max2, min5, b6);
                                    if (b) {
                                        for (int n22 = n2; n22 < min2; ++n22) {
                                            if (n22 > -1) {
                                                final int n23 = j - (n22 - n2) - 1 - max2;
                                                if (n23 > -1 && n23 < b10.length) {
                                                    final int n24 = b10[n23];
                                                    if (n24 != 0 && (n24 & 0xFF000000) != 0x0) {
                                                        if ((n24 & 0xFF000000) >> 24 == 127) {
                                                            this.b[n21 + n22] &= (n24 | 0xFF000000);
                                                        }
                                                        else {
                                                            this.b[n21 + n22] = (n24 | 0xFF000000);
                                                        }
                                                        b5 = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    else if (b2) {
                                        for (int n25 = n2; n25 < min2; ++n25) {
                                            if (n25 > -1) {
                                                final int n26 = j - (n25 - n2) - 1 - max2;
                                                if (n26 > -1 && n26 < b10.length) {
                                                    final int n27 = b10[n26];
                                                    if (n27 != 0 && (n27 & 0xFF000000) != 0x0) {
                                                        final int n28 = n21 + n25;
                                                        if (this.b[n28] == -1) {
                                                            this.b[n21 + n25] = (n27 | 0xFF000000);
                                                        }
                                                        else {
                                                            final int[] b11 = this.b;
                                                            final int n29 = n28;
                                                            b11[n29] &= (n27 | 0xFF000000);
                                                        }
                                                        b5 = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    else {
                                        for (int n30 = n2; n30 < min2; ++n30) {
                                            if (n30 > -1) {
                                                final int n31 = j - (n30 - n2) - 1 - max2;
                                                if (n31 > -1 && n31 < b10.length) {
                                                    final int n32 = b10[n31];
                                                    if (n32 != 0 && (n32 & 0xFF000000) != 0x0) {
                                                        this.b[n21 + n30] = (n32 | 0xFF000000);
                                                        b5 = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case 90: {
                        final int n33 = n4 - (min2 - n2);
                        final int max3 = Math.max(0, -n3);
                        final int min6 = Math.min(max3 + Math.min(this.h(), l + n3), dw.j());
                        final int n34 = dw.l() - 1;
                        for (int n35 = n2; n35 < min2; ++n35) {
                            if (n35 > -1) {
                                final int n36 = min2 - n35 + n33 - 1;
                                if (n36 <= n34) {
                                    if (n36 > -1) {
                                        final int[] b12 = dw.b(n36, max3, min6, b6);
                                        if (b) {
                                            for (int n37 = n3; n37 < min; ++n37) {
                                                final int n38 = n37 * this.d;
                                                final int n39 = n37 - n3 - max3;
                                                if (n39 > -1 && n38 + n35 > -1 && n39 < b12.length) {
                                                    final int n40 = b12[n39];
                                                    if (n40 != 0 && (n40 & 0xFF000000) != 0x0) {
                                                        if ((n40 & 0xFF000000) >> 24 == 127) {
                                                            this.b[n38 + n35] &= (n40 | 0xFF000000);
                                                        }
                                                        else {
                                                            this.b[n38 + n35] = (n40 | 0xFF000000);
                                                        }
                                                        b5 = true;
                                                    }
                                                }
                                            }
                                        }
                                        else if (b2) {
                                            for (int n41 = n3; n41 < min; ++n41) {
                                                final int n42 = n41 * this.d;
                                                final int n43 = n41 - n3 - max3;
                                                if (n43 > -1 && n42 + n35 > -1 && n43 < b12.length) {
                                                    final int n44 = b12[n43];
                                                    if (n44 != 0 && (n44 & 0xFF000000) != 0x0) {
                                                        final int n45 = n42 + n35;
                                                        if (this.b[n45] == -1) {
                                                            this.b[n42 + n35] = (n44 | 0xFF000000);
                                                        }
                                                        else {
                                                            final int[] b13 = this.b;
                                                            final int n46 = n45;
                                                            b13[n46] &= (n44 | 0xFF000000);
                                                        }
                                                        b5 = true;
                                                    }
                                                }
                                            }
                                        }
                                        else {
                                            for (int n47 = n3; n47 < min; ++n47) {
                                                final int n48 = n47 * this.d;
                                                final int n49 = n47 - n3 - max3;
                                                if (n49 > -1 && n48 + n35 > -1 && n49 < b12.length) {
                                                    final int n50 = b12[n49];
                                                    if (n50 != 0 && (n50 & 0xFF000000) != 0x0) {
                                                        this.b[n48 + n35] = (n50 | 0xFF000000);
                                                        b5 = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case 270: {
                        final int n51 = n4 - (min2 - n2);
                        final int min7 = Math.min(Math.min(l, l + n3), dw.j());
                        final int n52 = min7 - Math.min(this.h(), min7);
                        for (int n53 = n2; n53 < min2; ++n53) {
                            if (n53 > -1) {
                                final int n54 = j - (min2 - n53 + n51) - 1;
                                if (n54 > -1) {
                                    final int[] b14 = dw.b(n54, n52, Math.min(l, min7), b6);
                                    if (b) {
                                        for (int n55 = n3; n55 < min; ++n55) {
                                            final int n56 = n55 * this.d;
                                            final int n57 = l - (n55 - n3) - 1 - n52;
                                            if (n57 > -1 && n56 + n53 > -1 && n57 < b14.length) {
                                                final int n58 = b14[n57];
                                                if (n58 != 0 && (n58 & 0xFF000000) != 0x0) {
                                                    if ((n58 & 0xFF000000) >> 24 == 127) {
                                                        this.b[n56 + n53] &= (n58 | 0xFF000000);
                                                    }
                                                    else {
                                                        this.b[n56 + n53] = (n58 | 0xFF000000);
                                                    }
                                                    b5 = true;
                                                }
                                            }
                                        }
                                    }
                                    else if (b2) {
                                        for (int n59 = n3; n59 < min; ++n59) {
                                            final int n60 = n59 * this.d;
                                            final int n61 = l - (n59 - n3) - 1 - n52;
                                            if (n61 > -1 && n60 + n53 > -1 && n61 < b14.length) {
                                                final int n62 = b14[n61];
                                                if (n62 != 0 && (n62 & 0xFF000000) != 0x0) {
                                                    final int n63 = n60 + n53;
                                                    if (this.b[n63] == -1) {
                                                        this.b[n60 + n53] = (n62 | 0xFF000000);
                                                    }
                                                    else {
                                                        final int[] b15 = this.b;
                                                        final int n64 = n63;
                                                        b15[n64] &= (n62 | 0xFF000000);
                                                    }
                                                    b5 = true;
                                                }
                                            }
                                        }
                                    }
                                    else {
                                        for (int n65 = n3; n65 < min; ++n65) {
                                            final int n66 = n65 * this.d;
                                            final int n67 = l - (n65 - n3) - 1 - n52;
                                            if (n67 > -1 && n66 + n53 > -1 && n67 < b14.length) {
                                                final int n68 = b14[n67];
                                                if (n68 != 0 && (n68 & 0xFF000000) != 0x0) {
                                                    this.b[n66 + n53] = (n68 | 0xFF000000);
                                                    b5 = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
                if (dw != null && dw.f()) {
                    this.a(n2, n3, n4, n5, component, dw.b(), dw.a(), false, b3);
                }
            }
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
        if (b5 && dw.c() > 1) {
            this.aj = true;
        }
        return b4;
    }
    
    private final boolean a(final int[] array, final du du, final int n, final int n2, final int n3, final int n4, final Color color, final Component component, final boolean b, final boolean b2, final boolean b3) {
        boolean b4 = false;
        boolean b5 = false;
        try {
            final boolean b6 = false;
            int g = 0;
            int i = 0;
            if (du != null) {
                du.o();
                g = du.g();
                i = du.i();
            }
            final int min = Math.min(n2 + n4, this.e);
            int min2 = Math.min(n + n3, this.d);
            if ((b6 ? 1 : 0) == 90 || (b6 ? 1 : 0) == 270) {
                final int n5 = g;
                g = i;
                i = n5;
            }
            boolean b7 = false;
            if (du == null) {
                b7 = true;
            }
            else if (!du.d()) {
                b7 = true;
            }
            if (this.i) {
                this.j = true;
                return b4;
            }
            if (this.b == null) {
                this.c(b3);
            }
            if (b7) {
                b4 = true;
                this.a(array, n, n2, n3, n4, color, component);
            }
            else {
                b4 = true;
                switch (b6) {
                    case 180: {
                        final int max = Math.max(0, g + n);
                        final int max2 = Math.max(0, g + n - this.g());
                        for (int j = n2; j < min; ++j) {
                            if (j > -1) {
                                final int n6 = i - (j - n2) - 1;
                                final int n7 = j * this.d;
                                if (n6 > -1) {
                                    final int[] a = du.a(n6, max2, max);
                                    if (b) {
                                        for (int k = n; k <= min2; ++k) {
                                            if (k > -1) {
                                                final int n8 = g - (k - n) - 1 - max2;
                                                if (n8 > -1 && n8 < a.length) {
                                                    final int n9 = a[n8];
                                                    if (n9 != 0 && (n9 & 0xFF000000) != 0x0) {
                                                        this.b[n7 + k] &= n9;
                                                        b5 = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    else if (b2) {
                                        for (int l = n; l <= min2; ++l) {
                                            if (l > -1) {
                                                final int n10 = g - (l - n) - 1 - max2;
                                                if (n10 > -1 && n10 < a.length) {
                                                    final int n11 = a[n10];
                                                    if (n11 != 0 && (n11 & 0xFF000000) != 0x0 && n11 != 0 && (n11 & 0xFF000000) != 0x0) {
                                                        final int n12 = n7 + l;
                                                        if (this.b[n12] == -1) {
                                                            this.b[n7 + l] = n11;
                                                        }
                                                        else {
                                                            final int[] b8 = this.b;
                                                            final int n13 = n12;
                                                            b8[n13] &= n11;
                                                        }
                                                        b5 = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    else {
                                        for (int n14 = n; n14 <= min2; ++n14) {
                                            if (n14 > -1) {
                                                final int n15 = g - (n14 - n) - 1 - max2;
                                                if (n15 > -1 && n15 < a.length) {
                                                    final int n16 = a[n15];
                                                    if (n16 != 0 && (n16 & 0xFF000000) != 0x0) {
                                                        this.b[n7 + n14] = n16;
                                                        b5 = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case 90: {
                        final int n17 = n3 - (min2 - n);
                        ++min2;
                        final int max3 = Math.max(0, -n2);
                        final int n18 = max3 + Math.min(this.h(), i + n2);
                        for (int n19 = n; n19 < min2; ++n19) {
                            if (n19 > -1) {
                                final int n20 = min2 - n19 + n17 - 1;
                                if (n20 > -1) {
                                    final int[] a2 = du.a(n20, max3, n18);
                                    if (b) {
                                        for (int n21 = n2; n21 <= min; ++n21) {
                                            final int n22 = n21 * this.d;
                                            final int n23 = n21 - n2 - max3;
                                            if (n23 > -1 && n22 + n19 > -1 && n23 < a2.length) {
                                                final int n24 = a2[n23];
                                                if (n24 != 0 && (n24 & 0xFF000000) != 0x0) {
                                                    this.b[n22 + n19] &= n24;
                                                    b5 = true;
                                                }
                                            }
                                        }
                                    }
                                    else if (b2) {
                                        for (int n25 = n2; n25 <= min; ++n25) {
                                            final int n26 = n25 * this.d;
                                            final int n27 = n25 - n2 - max3;
                                            if (n27 > -1 && n26 + n19 > -1 && n27 < a2.length) {
                                                final int n28 = a2[n27];
                                                if (n28 != 0 && (n28 & 0xFF000000) != 0x0) {
                                                    final int n29 = n26 + n19;
                                                    if (this.b[n29] == -1) {
                                                        this.b[n26 + n19] = n28;
                                                    }
                                                    else {
                                                        final int[] b9 = this.b;
                                                        final int n30 = n29;
                                                        b9[n30] &= n28;
                                                    }
                                                    b5 = true;
                                                }
                                            }
                                        }
                                    }
                                    else {
                                        for (int n31 = n2; n31 <= min; ++n31) {
                                            final int n32 = n31 * this.d;
                                            final int n33 = n31 - n2 - max3;
                                            if (n33 > -1 && n32 + n19 > -1 && n33 < a2.length) {
                                                final int n34 = a2[n33];
                                                if (n34 != 0 && (n34 & 0xFF000000) != 0x0) {
                                                    this.b[n32 + n19] = n34;
                                                    b5 = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                    case 270: {
                        final int n35 = n3 - (min2 - n);
                        final int min3 = Math.min(i, i + n2);
                        final int n36 = min3 - Math.min(this.h(), min3);
                        for (int n37 = n; n37 <= min2; ++n37) {
                            if (n37 > -1) {
                                final int n38 = g - (min2 - n37 + n35) - 1;
                                if (n38 > -1) {
                                    final int[] a3 = du.a(n38, n36, Math.min(i, min3));
                                    if (b) {
                                        for (int n39 = n2; n39 < min; ++n39) {
                                            final int n40 = n39 * this.d;
                                            final int n41 = i - (n39 - n2) - 1 - n36;
                                            if (n41 > -1 && n40 + n37 > -1 && n41 < a3.length) {
                                                final int n42 = a3[n41];
                                                if (n42 != 0 && (n42 & 0xFF000000) != 0x0) {
                                                    this.b[n40 + n37] &= n42;
                                                    b5 = true;
                                                }
                                            }
                                        }
                                    }
                                    else if (b2) {
                                        for (int n43 = n2; n43 < min; ++n43) {
                                            final int n44 = n43 * this.d;
                                            final int n45 = i - (n43 - n2) - 1 - n36;
                                            if (n45 > -1 && n44 + n37 > -1 && n45 < a3.length) {
                                                final int n46 = a3[n45];
                                                if (n46 != 0 && (n46 & 0xFF000000) != 0x0 && n46 != 0 && (n46 & 0xFF000000) != 0x0) {
                                                    final int n47 = n44 + n37;
                                                    if (this.b[n47] == -1) {
                                                        this.b[n44 + n37] = n46;
                                                    }
                                                    else {
                                                        final int[] b10 = this.b;
                                                        final int n48 = n47;
                                                        b10[n48] &= n46;
                                                    }
                                                    b5 = true;
                                                }
                                            }
                                        }
                                    }
                                    else {
                                        for (int n49 = n2; n49 < min; ++n49) {
                                            final int n50 = n49 * this.d;
                                            final int n51 = i - (n49 - n2) - 1 - n36;
                                            if (n51 > -1 && n50 + n37 > -1 && n51 < a3.length) {
                                                final int n52 = a3[n51];
                                                if (n52 != 0 && (n52 & 0xFF000000) != 0x0) {
                                                    this.b[n50 + n37] = n52;
                                                    b5 = true;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                    default: {
                        final int max4 = Math.max(0, -n);
                        final int n53 = max4 + Math.min(this.g(), g + n);
                        for (int n54 = n2; n54 < min; ++n54) {
                            if (n54 > -1) {
                                final int n55 = n54 - n2;
                                final int n56 = n54 * this.d;
                                if (n55 > -1) {
                                    final int[] a4 = du.a(n55, max4, n53);
                                    if (b) {
                                        for (int n57 = n; n57 < min2; ++n57) {
                                            if (n57 > -1) {
                                                final int n58 = n57 - n - max4;
                                                if (n58 > -1 && n58 < a4.length) {
                                                    final int n59 = a4[n58];
                                                    if (n59 != 0 && (n59 & 0xFF000000) != 0x0) {
                                                        this.b[n56 + n57] &= n59;
                                                        b5 = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    else if (b2) {
                                        for (int n60 = n; n60 < min2; ++n60) {
                                            if (n60 > -1) {
                                                final int n61 = n60 - n - max4;
                                                if (n61 > -1 && n61 < a4.length) {
                                                    final int n62 = a4[n61];
                                                    if (n62 != 0 && (n62 & 0xFF000000) != 0x0) {
                                                        final int n63 = n56 + n60;
                                                        if (this.b[n63] == -1) {
                                                            this.b[n56 + n60] = n62;
                                                        }
                                                        else {
                                                            final int[] b11 = this.b;
                                                            final int n64 = n63;
                                                            b11[n64] &= n62;
                                                        }
                                                        b5 = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    else {
                                        for (int n65 = n; n65 < min2; ++n65) {
                                            if (n65 > -1) {
                                                final int n66 = n65 - n - max4;
                                                if (n66 > -1 && n66 < a4.length) {
                                                    final int n67 = a4[n66];
                                                    if (n67 != 0 && (n67 & 0xFF000000) != 0x0) {
                                                        this.b[n56 + n65] = n67;
                                                        b5 = true;
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    }
                }
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        if (b5 && du.a() > 1) {
            this.aj = true;
        }
        return b4;
    }
    
    public final boolean u() {
        return this.aj;
    }
    
    public final boolean a(final dw dw, final int n, final int n2, final int n3, final int n4, final Color color, final Component component, final boolean b, final boolean b2, final boolean b3) {
        boolean a = false;
        if (this.x()) {
            a = this.a(this.ag, this.b, dw, n, n2, n3, n4, color, component, b, b2, b3);
        }
        return a;
    }
    
    public final boolean a(final du du, final int n, final int n2, final int n3, final int n4, final Color color, final Component component, final boolean b, final boolean b2, final boolean b3) {
        boolean a = false;
        if (this.x()) {
            a = this.a(this.b, du, n, n2, n3, n4, color, component, b, b2, b3);
        }
        return a;
    }
    
    public final boolean a(final String s, final int n, final int n2, final Color color, final Font font, final Component component, final boolean b, final int n3, final boolean b2) {
        boolean a = false;
        if (component != null) {
            try {
                final Graphics graphics = component.getGraphics();
                if (graphics != null) {
                    final Graphics create = graphics.create();
                    create.setFont(font);
                    final int min = Math.min(create.getFontMetrics().stringWidth(s), n3);
                    final int height = create.getFontMetrics().getHeight();
                    if (min > 0 && min > 0) {
                        Image image = component.createImage(min, height);
                        if (!ji.util.d.em() && image == null && !component.isDisplayable()) {
                            image = ji.font.j.a(min, height, 1);
                        }
                        final Graphics graphics2 = image.getGraphics();
                        graphics2.setFont(font);
                        graphics2.setColor(Color.white);
                        graphics2.fillRect(0, 0, min, height);
                        graphics2.setColor(color);
                        graphics2.drawString(s, 0, 80 * height / 100);
                        final int[] array = new int[min * height];
                        new PixelGrabber(image, 0, 0, min, height, array, 0, min).grabPixels();
                        final du du = new du(height, min, component, ji.util.d.b(ji.util.d.cd(), b, this.y, this.ah), this.ah, false, 24, false);
                        du.a(0, 0, 1.0, 1.0, 0, 0);
                        du.a(0, array, min, height);
                        a = this.a(du, n, n2, min, height, Color.white, component, true, false, b2);
                        du.p();
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return a;
    }
    
    private boolean x() {
        final Object w = ji.util.d.w(this.ah);
        if (w instanceof ad) {
            return !((ad)w).co();
        }
        return !ji.util.d.fp && !ji.util.d.c5() && !ji.util.d.i6 && !ji.util.d.c4();
    }
    
    static {
        dt.ac = 0;
    }
}
