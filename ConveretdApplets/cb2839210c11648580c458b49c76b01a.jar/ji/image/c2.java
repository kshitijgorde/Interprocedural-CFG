// 
// Decompiled by Procyon v0.5.30
// 

package ji.image;

import java.awt.image.DirectColorModel;
import ji.util.i;
import ji.util.d;
import ji.io.h;
import java.awt.image.IndexColorModel;
import java.util.Hashtable;
import java.awt.Image;
import java.awt.image.ColorModel;
import java.awt.image.ImageProducer;
import ji.v1event.a6;
import ji.v1event.af;
import java.awt.image.ImageConsumer;

public class c2 implements ImageConsumer
{
    String a;
    Object b;
    dx c;
    af d;
    a6 e;
    ImageProducer f;
    int g;
    int h;
    int i;
    int j;
    int k;
    int l;
    int m;
    int n;
    ColorModel o;
    boolean p;
    boolean q;
    byte[] r;
    short[] s;
    int[] t;
    int u;
    int v;
    ds w;
    boolean x;
    boolean y;
    boolean z;
    boolean aa;
    int ab;
    int ac;
    int ad;
    boolean ae;
    int[] af;
    byte[] ag;
    
    public c2(final String s, final Object o, final dx dx, final af af, final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final ds ds) {
        this(s, o, dx, af, image.getSource(), n, n2, n3, n4, n5, ds, 0);
    }
    
    public c2(final String s, final Object o, final dx dx, final af af, final Image image, final int n, final int n2, final int n3, final int n4, final int n5, final ds ds, final int n6) {
        this(s, o, dx, af, image.getSource(), n, n2, n3, n4, n5, ds, n6);
    }
    
    public c2(final String a, final Object b, final dx c, final af d, final ImageProducer f, final int h, final int i, final int n, final int k, final int l, final ds w, final int ac) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.g = 0;
        this.l = 0;
        this.m = -1;
        this.n = 0;
        this.p = false;
        this.q = true;
        this.r = null;
        this.s = null;
        this.t = null;
        this.w = null;
        this.x = false;
        this.z = false;
        this.aa = false;
        this.ac = 0;
        this.ad = 0;
        this.ae = false;
        this.af = null;
        this.ag = null;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.f = f;
        this.h = h;
        this.i = i;
        this.j = n;
        this.k = k;
        this.u = 0;
        this.v = n;
        this.w = w;
        this.l = l;
        if (w != null) {
            this.n = w.t();
        }
        this.ac = ac;
        this.aa = false;
        this.ad = 0;
    }
    
    public c2(final String a, final Object b, final af d, final ImageProducer f, final int h, final int i, final int n, final int k, final int l, final int[] af, final int n2) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.g = 0;
        this.l = 0;
        this.m = -1;
        this.n = 0;
        this.p = false;
        this.q = true;
        this.r = null;
        this.s = null;
        this.t = null;
        this.w = null;
        this.x = false;
        this.z = false;
        this.aa = false;
        this.ac = 0;
        this.ad = 0;
        this.ae = false;
        this.af = null;
        this.ag = null;
        this.a = a;
        this.b = b;
        this.c = null;
        this.d = d;
        this.f = f;
        this.h = h;
        this.i = i;
        this.j = n;
        this.k = k;
        this.u = 0;
        this.v = n;
        this.w = null;
        this.af = af;
        this.ag = null;
        this.l = l;
        this.n = n2;
        this.ac = 0;
        this.aa = false;
        this.ad = 0;
    }
    
    public c2(final String a, final Object b, final af d, final ImageProducer f, final int h, final int i, final int n, final int k, final int l, final byte[] ag, final int n2, final boolean aa) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.g = 0;
        this.l = 0;
        this.m = -1;
        this.n = 0;
        this.p = false;
        this.q = true;
        this.r = null;
        this.s = null;
        this.t = null;
        this.w = null;
        this.x = false;
        this.z = false;
        this.aa = false;
        this.ac = 0;
        this.ad = 0;
        this.ae = false;
        this.af = null;
        this.ag = null;
        this.a = a;
        this.b = b;
        this.c = null;
        this.d = d;
        this.f = f;
        this.h = h;
        this.i = i;
        this.j = n;
        this.k = k;
        this.u = 0;
        this.v = n;
        this.w = null;
        this.af = null;
        this.ag = ag;
        this.l = l;
        this.n = n2;
        this.ac = 0;
        this.aa = aa;
        this.ad = 0;
    }
    
    public final void a(final int n, final int n2) {
        this.h += n;
        this.i += n2;
    }
    
    public final void a(final boolean ae) {
        this.ae = ae;
    }
    
    public boolean a() throws InterruptedException {
        return this.a(0L);
    }
    
    public boolean a(final int[] af, final boolean b) throws InterruptedException {
        if (af != null) {
            this.af = af;
        }
        return this.a(0L, b);
    }
    
    public boolean a(final byte[] ag, final boolean b) throws InterruptedException {
        if (ag != null) {
            this.ag = ag;
        }
        return this.a(0L, b);
    }
    
    public synchronized boolean a(final long n) throws InterruptedException {
        return this.a(n, false);
    }
    
    public synchronized boolean a(final long n, final boolean b) throws InterruptedException {
        if ((this.ab & 0x70) != 0x0 && !b) {
            return (this.ab & 0x30) != 0x0;
        }
        final long n2 = n + System.currentTimeMillis();
        if (!this.y) {
            this.y = true;
            this.ab &= 0xFFFFFF7F;
            this.f.startProduction(this);
        }
        while (this.y) {
            long n3;
            if (n == 0) {
                n3 = 0L;
            }
            else {
                n3 = n2 - System.currentTimeMillis();
                if (n3 <= 0) {
                    break;
                }
            }
            this.wait(n3);
        }
        return (this.ab & 0x30) != 0x0;
    }
    
    public synchronized int b() {
        return this.ab;
    }
    
    public synchronized ColorModel c() {
        return this.o;
    }
    
    public void setDimensions(final int n, final int n2) {
        if (this.j < 0) {
            this.j = n - this.h;
        }
        if (this.k < 0) {
            this.k = n2 - this.i;
        }
        if (this.j <= 0 || this.k <= 0) {
            this.imageComplete(3);
        }
        else if (this.o == ColorModel.getRGBdefault()) {
            this.v = this.j;
            this.u = 0;
        }
        this.ab |= 0x3;
    }
    
    public void setHints(final int n) {
    }
    
    public void setProperties(final Hashtable hashtable) {
    }
    
    public void setColorModel(final ColorModel colorModel) {
    }
    
    public final int[] d() {
        return this.t;
    }
    
    public void setPixels(int n, int n2, int n3, int n4, final ColorModel o, final byte[] array, int n5, final int n6) {
        try {
            if (this.z) {
                return;
            }
            if (this.ac == 1) {
                n += this.h;
                n2 += this.i;
            }
            if (n2 < this.i) {
                final int n7 = this.i - n2;
                if (n7 >= n4) {
                    return;
                }
                n5 += n6 * n7;
                n2 += n7;
                n4 -= n7;
            }
            if (n2 + n4 > this.i + this.k) {
                n4 = this.i + this.k - n2;
                if (n4 <= 0) {
                    return;
                }
            }
            if (n < this.h) {
                final int n8 = this.h - n;
                if (n8 >= n3) {
                    return;
                }
                n5 += n8;
                n += n8;
                n3 -= n8;
            }
            if (n + n3 > this.h + this.j) {
                n3 = this.h + this.j - n;
                if (n3 <= 0) {
                    return;
                }
            }
            if (this.o == null) {
                if (this.af != null) {
                    this.z = true;
                    return;
                }
                if (o == ColorModel.getRGBdefault()) {
                    this.z = true;
                    return;
                }
                if (!(o instanceof IndexColorModel)) {
                    this.z = true;
                    ji.io.h.d(this.a, "ERROR*** Unable to process mode (model) while in byte mode pixel grabber!");
                    return;
                }
                this.o = o;
                final IndexColorModel indexColorModel = (IndexColorModel)o;
                if (indexColorModel.getMapSize() == 2) {
                    if (this.w != null) {
                        this.w.a(1, false, this.b);
                        if ((indexColorModel.getRed(0) & 0xFF) == 0xFF && (indexColorModel.getGreen(0) & 0xFF) == 0xFF && (indexColorModel.getBlue(0) & 0xFF) == 0xFF && indexColorModel.getRed(1) == 0 && indexColorModel.getGreen(1) == 0 && indexColorModel.getBlue(1) == 0) {
                            this.q = false;
                        }
                    }
                    this.p = true;
                }
                else {
                    this.t = new int[indexColorModel.getMapSize()];
                    for (int i = 0; i < this.t.length; ++i) {
                        this.t[i] = ((indexColorModel.getAlpha(i) & 0xFF) << 24 | (indexColorModel.getRed(i) & 0xFF) << 16 | (indexColorModel.getGreen(i) & 0xFF) << 8 | (indexColorModel.getBlue(i) & 0xFF));
                    }
                    if (this.w != null) {
                        this.w.a(3, false, this.b);
                        this.w.a(this.t, this.c);
                    }
                }
                if (this.d != null) {
                    this.e = new a6(this, 4, "");
                    if (ji.util.d.du() && this.ae) {
                        this.e = new a6(this, 23, "");
                    }
                    this.e.a("0");
                    this.d.a(this.e);
                }
            }
            if (this.p) {
                if (this.r == null) {
                    this.r = new byte[n3];
                    this.s = new short[n3];
                }
                if (this.w != null) {
                    for (int j = 0; j < n4; ++j) {
                        if (n2 + j + this.l > this.m) {
                            System.arraycopy(array, n5, this.r, 0, n3);
                            if (this.q) {
                                for (int k = 0; k < n3; ++k) {
                                    this.r[k] = (byte)(1 - (this.r[k] & 0xFF));
                                }
                            }
                            this.w.a(this.s, this.a(0, n3, this.r, 0, this.s));
                            this.m = n2 + j + this.l;
                        }
                        n5 += n6;
                    }
                }
                else {
                    ji.io.h.d(this.a, "jiPixelGrabber does not yet support grabbing in this mode");
                }
                this.r = null;
                this.s = null;
            }
            else if (this.w != null) {
                if (n3 * n4 > array.length) {
                    this.z = true;
                    if (ji.util.i.c(85)) {
                        ji.io.h.d(this.a, "ERROR*** jiPixelGrabber has not received enough bytes to process image.");
                    }
                    return;
                }
                for (int l = 0; l < n4; ++l) {
                    if (n2 + l + this.l > this.m) {
                        this.w.b(array, n3, this.b, n2 + l + this.l, n2 + l + this.l + 1, true);
                        this.m = n2 + l + this.l;
                    }
                    n5 += n6;
                }
            }
            else if (this.ag != null) {
                if (this.aa) {
                    int n9 = n3 / 8;
                    if (n9 * 8 != n3) {
                        ++n9;
                    }
                    for (int n10 = 0; n10 < n4; ++n10) {
                        int n11 = 0;
                        int n12 = 128;
                        for (int n13 = 0; n13 < n3; ++n13) {
                            final int n14 = this.t[array[n5 + n13] & 0xFF];
                            if (((n14 & 0xFF0000) >> 16) + ((n14 & 0xFF00) >> 8) + (n14 & 0xFF) > 384) {
                                final byte[] ag = this.ag;
                                final int n15 = this.ad + n11;
                                ag[n15] |= (byte)n12;
                            }
                            n12 >>= 1;
                            if (n12 == 0) {
                                n12 = 128;
                                ++n11;
                            }
                        }
                        n5 += n6;
                        this.ad += n9;
                    }
                }
                else {
                    for (int n16 = 0; n16 < n4; ++n16) {
                        if (n2 + n16 + this.l > this.m) {
                            if (n5 + n6 <= this.ag.length) {
                                System.arraycopy(array, n5, this.ag, this.ad, n6);
                            }
                            this.ad += n6;
                        }
                        n5 += n6;
                    }
                }
            }
            if (this.d != null) {
                final int g = 100 * (n2 + n4) / this.k;
                if (g >= 100 || g > this.g + 10) {
                    this.g = g;
                    this.e.a("".concat(String.valueOf(String.valueOf(g))));
                    this.d.a(this.e);
                }
            }
            this.ab |= 0x8;
        }
        catch (Exception ex) {
            this.z = true;
            ji.io.h.d(this.a, "ERROR*** Unable to process pixel data in Pixel Grabber!");
            ex.printStackTrace();
        }
    }
    
    public final boolean e() {
        return this.z;
    }
    
    public final boolean f() {
        return this.x;
    }
    
    public final void g() {
        this.ad = 0;
        this.x = false;
        this.z = false;
    }
    
    public void setPixels(int n, int n2, int n3, int n4, final ColorModel o, final int[] array, int n5, final int n6) {
        try {
            if (this.z) {
                return;
            }
            if (this.ac == 1) {
                n += this.h;
                n2 += this.i;
            }
            if (n2 < this.i) {
                final int n7 = this.i - n2;
                if (n7 >= n4) {
                    return;
                }
                n5 += n6 * n7;
                n2 += n7;
                n4 -= n7;
            }
            if (n2 + n4 > this.i + this.k) {
                n4 = this.i + this.k - n2;
                if (n4 <= 0) {
                    return;
                }
            }
            if (n < this.h) {
                final int n8 = this.h - n;
                if (n8 >= n3) {
                    return;
                }
                n5 += n8;
                n += n8;
                n3 -= n8;
            }
            if (n + n3 > this.h + this.j) {
                n3 = this.h + this.j - n;
                if (n3 <= 0) {
                    return;
                }
            }
            if (ji.util.i.c(87) && this.w != null) {
                this.w.e(true);
            }
            if (this.o == null) {
                if (o != ColorModel.getRGBdefault() && !(o instanceof DirectColorModel)) {
                    this.z = true;
                    ji.io.h.d(this.a, "ERROR*** Unable to process non-RGB int data in pixel grabber!");
                    return;
                }
                this.o = o;
                this.x = true;
                if (this.w != null) {
                    this.w.a(4, false, this.b);
                }
                if (!ji.util.d.b() && this.d != null) {
                    this.e = new a6(this, 4, "");
                    if (ji.util.d.du() && this.ae) {
                        this.e = new a6(this, 23, "");
                    }
                    this.e.a("0");
                    this.d.a(this.e);
                }
            }
            if (this.w == null && this.af == null && this.ag != null) {
                this.z = true;
                return;
            }
            final int n9 = n5;
            int length = n5 + n6 * n4;
            if (length > array.length) {
                length = array.length;
            }
            for (int i = n9; i < length; ++i) {
                if ((array[i] & 0xFF000000) != 0xFF000000) {
                    array[i] |= 0xFF000000;
                }
            }
            final int n10 = n2 + 1;
            if (this.w != null) {
                for (int j = 0; j < n4; ++j) {
                    if (n10 + j + this.l > this.m) {
                        this.w.a(array, n3, this.b, n10 + j + this.l, n10 + j + this.l + 1, true);
                        this.m = n10 + j + this.l;
                    }
                    n5 += n6;
                }
            }
            else if (this.af != null) {
                for (int k = 0; k < n4; ++k) {
                    if (n2 + k + this.l > this.m) {
                        if (this.ad <= this.af.length) {
                            System.arraycopy(array, n5, this.af, this.ad, n6);
                        }
                        this.ad += n6;
                    }
                    n5 += n6;
                }
            }
            if (!ji.util.d.b() && this.d != null) {
                final int g = 100 * (n2 + n4) / this.k;
                if (g >= 100 || g > this.g + 10) {
                    this.g = g;
                    this.e.a("".concat(String.valueOf(String.valueOf(g))));
                    this.d.a(this.e);
                }
            }
            this.ab |= 0x8;
        }
        catch (Exception ex) {
            this.z = true;
            ji.io.h.d(this.a, "ERROR*** Unable to process pixel data in Pixel Grabber!");
            ex.printStackTrace();
        }
    }
    
    public final int a(final int n, final int n2, final byte[] array, final int n3, final short[] array2) {
        short n4 = 0;
        final int length = array.length;
        int n5 = 0;
        int n6 = n3;
        byte b = 0;
        try {
            for (int i = n; i < n2; ++i) {
                final byte b2 = array[n6++];
                if (b != b2) {
                    array2[n5++] = n4;
                    b = b2;
                    n4 = 1;
                }
                else {
                    ++n4;
                }
            }
            if (n4 > 0) {
                array2[n5++] = n4;
            }
        }
        catch (Exception ex) {}
        return n5;
    }
    
    public synchronized void h() {
        this.y = false;
    }
    
    public synchronized void imageComplete(final int n) {
        this.y = false;
        if (this.z) {
            this.ab |= 0xC0;
        }
        else {
            switch (n) {
                default: {
                    if (ji.util.i.c(5)) {
                        ji.io.h.d(this.a, "Image extraction failed (unknown compression/format).");
                    }
                    this.ab |= 0xC0;
                    break;
                }
                case 4: {
                    ji.io.h.d(this.a, "Image extraction was aborted #2.");
                    this.ab |= 0x80;
                    break;
                }
                case 3: {
                    this.ab |= 0x20;
                    break;
                }
                case 2: {
                    this.ab |= 0x10;
                    break;
                }
            }
        }
        this.b = null;
        this.a = null;
        this.d = null;
        this.e = null;
        this.f.removeConsumer(this);
        this.notifyAll();
    }
    
    public final void i() {
        this.b = null;
        this.a = null;
        this.o = null;
        this.d = null;
        this.e = null;
        this.r = null;
        this.s = null;
        this.ag = null;
        this.af = null;
        this.t = null;
    }
}
