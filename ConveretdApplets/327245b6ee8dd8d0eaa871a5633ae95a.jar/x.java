import java.util.zip.Inflater;
import java.io.IOException;
import java.net.URLConnection;
import java.util.zip.InflaterInputStream;
import java.net.URL;
import java.io.InputStream;

// 
// Decompiled by Procyon v0.5.30
// 

final class x extends w
{
    blaze3d a;
    t b;
    private InputStream c;
    int d;
    int e;
    int f;
    int g;
    private int h;
    private int i;
    boolean j;
    
    x(final blaze3d a, final t b) {
        this.a = a;
        this.b = b;
        this.g = 0;
    }
    
    void a() {
        this.a = null;
        this.b = null;
        this.c = null;
        super.a = null;
    }
    
    boolean a(final URL url) {
        final boolean l = c.l;
        try {
            Label_0425: {
                if (super.a != null) {
                    if (this.g >= this.d) {
                        break Label_0425;
                    }
                }
                URLConnection openConnection;
                InputStream inputStream;
                try {
                    openConnection = url.openConnection();
                    inputStream = openConnection.getInputStream();
                }
                catch (Exception ex) {
                    System.out.println("Error opening URL " + url.toString());
                    return false;
                }
                byte[] array;
                if ((array = this.a(inputStream, 3)) == null) {
                    return false;
                }
                final String s = new String(array);
                if (s.equals("FWS") || s.equals("CWS")) {
                    super.f = this.a(inputStream, 1)[0];
                    this.b.h = super.f;
                    this.b.i = (super.f < 7);
                    array = this.a(inputStream, 4);
                    final int g = (array[0] & 0xFF) | (array[1] & 0xFF) << 8 | (array[2] & 0xFF) << 16 | (array[3] & 0xFF) << 24;
                    this.b.g = g;
                    this.b.f = 8;
                    this.d = g - 8;
                    if (super.a == null) {
                        super.a = new byte[this.d];
                    }
                    Label_0291: {
                        if (s.equals("CWS")) {
                            this.c = new InflaterInputStream(inputStream);
                            if (!l) {
                                break Label_0291;
                            }
                        }
                        this.c = inputStream;
                    }
                    this.g = 0;
                    this.b();
                    if (!l) {
                        break Label_0425;
                    }
                }
                if (array[0] == -1 && array[1] == -40) {
                    this.b.h = -1;
                    this.b.g = openConnection.getContentLength();
                    this.b.f = 3;
                    this.d = this.b.g;
                    if (super.a == null) {
                        super.a = new byte[this.d];
                    }
                    super.a[0] = array[0];
                    super.a[1] = array[1];
                    super.a[2] = array[2];
                    this.c = inputStream;
                    this.g = 3;
                    if (!l) {
                        break Label_0425;
                    }
                }
                return false;
            }
            Label_0449: {
                if (this.b.h == -1) {
                    this.p();
                    if (!l) {
                        break Label_0449;
                    }
                }
                this.n();
            }
            if (this.c != null) {
                this.c.close();
                this.c = null;
            }
        }
        catch (Exception ex2) {
            System.out.println("Error opening URL " + url.toString());
        }
        return true;
    }
    
    private byte[] a(final InputStream inputStream, int i) throws Exception {
        final byte[] array = new byte[i];
        int n = 0;
        do {
            final int read = inputStream.read(array, n, i);
            if (read == -1) {
                return null;
            }
            n += read;
            i -= read;
        } while (i > 0);
        return array;
    }
    
    int b() {
        try {
            int n = 512;
            if (this.g + n > this.d) {
                n = this.d - this.g;
            }
            final int read = this.c.read(super.a, this.g, n);
            if (read <= 0) {
                return 0;
            }
            this.g += read;
            final t b = this.b;
            b.f += read;
            return read;
        }
        catch (IOException ex) {
            return 0;
        }
    }
    
    void n() {
        super.b = 0;
        this.b.f = this.k();
        this.b.g = 65536000 / (this.e() << 8);
        this.b.b(this.e());
        this.b.k.b();
        this.a(this.b, new af[65536]);
    }
    
    void a(final u u, final af[] array) {
        final boolean l = c.l;
        int n = 0;
        while (true) {
            if (this.j) {
                this.b.f = 0;
                this.b.g = 0;
                if (!l) {
                    return;
                }
            }
            else if (super.b == this.d) {
                return;
            }
            if (this.g - super.b < 2) {
                this.b();
                if (!l) {
                    continue;
                }
            }
            final int b = super.b;
            final int e = this.e();
            this.f = (e & 0x3F);
            if (this.f == 63) {
                if (this.g - super.b < 4) {
                    super.b = b;
                    this.b();
                    if (!l) {
                        continue;
                    }
                }
                this.f = this.g();
            }
            this.e = super.b + this.f;
            super.c = this.e;
            if (this.e > this.g) {
                super.b = b;
                this.b();
                if (!l) {
                    continue;
                }
            }
            final int n2 = e >> 6;
            try {
                Label_1917: {
                    switch (n2) {
                        default: {
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 0: {
                            u.f();
                            return;
                        }
                        case 1: {
                            u.b = n + 1;
                            ++n;
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 2: {
                            this.a(new ad(1));
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 6: {
                            this.e(1);
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 8: {
                            this.i = this.f - 2;
                            this.h = super.b;
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 9: {
                            if (u != this.a.al || this.a.a5 != null) {
                                break;
                            }
                            this.a.an.b(this.c(0));
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 10: {
                            this.d(1);
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 11: {
                            this.a(new ap(1));
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 12: {
                            final v v = new v(super.a, this.b, super.b, this.e - super.b);
                            this.b.r.addElement(v);
                            u.a(n, new ar(v, 0, this.e - super.b, null, 4, 0, null, null, 0));
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 14:
                        case 15: {
                            this.a(u, "EventSound", n2);
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 20: {
                            this.f(1);
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 21: {
                            this.e(2);
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 22: {
                            this.a(new ad(2));
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 26:
                        case 70: {
                            int d = this.d();
                            if (n2 == 70) {
                                d |= this.d() << 8;
                            }
                            final int e2 = this.e();
                            int e3 = -1;
                            ac i = null;
                            ag ag = null;
                            int n3 = -1;
                            String m = null;
                            int e4 = -1;
                            ar[] array2 = null;
                            if ((d & 0x2) != 0x0) {
                                e3 = this.e();
                            }
                            if ((d & 0x4) != 0x0) {
                                i = this.l();
                            }
                            if ((d & 0x8) != 0x0) {
                                ag = new ag();
                                this.a(ag);
                            }
                            if ((d & 0x10) != 0x0) {
                                n3 = this.e();
                            }
                            if ((d & 0x20) != 0x0) {
                                m = this.m();
                            }
                            if ((d & 0x40) != 0x0) {
                                e4 = this.e();
                            }
                            if ((d & 0x80) != 0x0) {
                                array2 = new ar[19];
                                this.e();
                                final int e5 = this.e();
                                if (super.f >= 6) {
                                    final int n4 = e5 | this.e() << 16;
                                }
                                int e6;
                                int n5 = 0;
                                int b2;
                                v v2;
                                ar ar;
                                int n6;
                                Label_1092_Outer:Label_1109_Outer:
                                do {
                                    e6 = this.e();
                                    if (super.f >= 6) {
                                        n5 = (e6 | this.e() << 16);
                                        if (!l) {
                                            e6 = n5;
                                        }
                                    }
                                    if (n5 == 0) {
                                        break;
                                    }
                                    b2 = this.g() + super.b;
                                    v2 = new v(super.a, this.b, super.b, b2 - super.b);
                                    this.b.r.addElement(v2);
                                    ar = new ar(v2, 0, b2 - super.b, null, 4, 0, null, null, 0);
                                    n6 = 0;
                                    while (true) {
                                        while (true) {
                                            Label_1112: {
                                                if (!l) {
                                                    break Label_1112;
                                                }
                                                if ((e6 & 1 << n6) != 0x0) {
                                                    array2[n6] = ar;
                                                }
                                                ++n6;
                                            }
                                            if (n6 < 32) {
                                                continue Label_1109_Outer;
                                            }
                                            break;
                                        }
                                        super.b = b2;
                                        if (!l) {
                                            continue Label_1092_Outer;
                                        }
                                        continue;
                                    }
                                } while (!l);
                            }
                            if ((d & 0x100) != 0x0) {
                                this.e();
                            }
                            if ((d & 0x200) != 0x0) {
                                this.d();
                            }
                            if ((d & 0x400) != 0x0) {
                                this.d();
                            }
                            final int n7 = d & 0x3;
                            if (n7 == 1) {
                                array[e2].a(n, i, ag, n3);
                                if (!l) {
                                    break;
                                }
                            }
                            if (n7 == 2) {
                                final af af = new af(n, u.a - 1, e3, e2, i, ag, n3, m, e4, array2);
                                u.a(af);
                                array[e2] = af;
                                if (!l) {
                                    break;
                                }
                            }
                            if (n7 != 3) {
                                break;
                            }
                            final af af2 = array[e2];
                            af2.b = n - 1;
                            if (i == null) {
                                i = new ac(af2.n[0].c);
                            }
                            if (ag == null && af2.n[1] != null) {
                                ag = new ag(af2.n[1].d);
                            }
                            if (n3 == -1 && af2.n[2] != null) {
                                n3 = af2.n[2].e;
                            }
                            final af af3 = new af(n, u.a - 1, e3, e2, i, ag, n3, m, e4, array2);
                            u.a(af3);
                            array[e2] = af3;
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 28: {
                            array[this.e()].b = n - 1;
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 32: {
                            this.a(new ad(3));
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 33: {
                            this.a(new ap(2));
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 34: {
                            this.a(new ay());
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 35: {
                            this.e(3);
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 36: {
                            this.f(2);
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 37: {
                            this.a(new az());
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 39: {
                            this.a(new u());
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 43: {
                            u.a(n, this.m());
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 46: {
                            this.a(u, "MorphShape", n2);
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 48: {
                            this.d(2);
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 56: {
                            final int e7 = this.e();
                            int n8 = 0;
                            while (true) {
                                Label_1653: {
                                    if (!l) {
                                        break Label_1653;
                                    }
                                    final int e8 = this.e();
                                    final String j = this.m();
                                    final m a = this.b.a(e8);
                                    if (a != null) {
                                        a.e = j;
                                    }
                                    this.b.a(j, a);
                                    ++n8;
                                }
                                if (n8 >= e7) {
                                    break;
                                }
                                continue;
                            }
                        }
                        case 57: {
                            final t a2 = this.a.a(this.m(), false);
                            final int e9 = this.e();
                            int n9 = 0;
                            while (true) {
                                Label_1737: {
                                    if (!l) {
                                        break Label_1737;
                                    }
                                    this.b.a(this.e(), new a1(a2.a(this.m())));
                                    ++n9;
                                }
                                if (n9 < e9) {
                                    continue;
                                }
                                break;
                            }
                            a2.a(this.b);
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 59: {
                            this.e();
                            final v v3 = new v(super.a, this.b, super.b, this.e - super.b);
                            this.b.r.addElement(v3);
                            u.b(n, new ar(v3, 0, this.e - super.b, null, 4, 0, null, null, 0));
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 75: {
                            this.d(3);
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 600: {
                            this.a(new a2());
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 601: {
                            this.a("dc1");
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 602: {
                            this.a("apt");
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 603: {
                            this.a(u, "h3d_SkeletonDef", n2);
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 604: {
                            this.a(u, "h3d_BlendDef", n2);
                            if (l) {
                                break Label_1917;
                            }
                            break;
                        }
                        case 605: {
                            this.e(4);
                            break;
                        }
                    }
                }
            }
            catch (Exception ex) {}
            super.b = this.e;
        }
    }
    
    private void a(final u u, final String s, final int n) {
        try {
            final Class<?> forName = Class.forName(blaze3d.b.gc(s));
            final Class[] array = { this.getClass(), Integer.TYPE, new u().getClass() };
            forName.getMethod(blaze3d.b.gp(forName.getName(), "parseTags", new String[] { array[0].getName(), array[1].getName(), array[2].getName() }), (Class[])array).invoke(null, this, new Integer(n), u);
        }
        catch (Exception ex) {}
    }
    
    void a(final m m) {
        this.b.a(m.a(this), m);
    }
    
    void d(final int n) {
        final ao m = new ao(n);
        this.a(m);
        if (this.b.m == null || m.e > this.b.m.e) {
            this.b.m = m;
        }
    }
    
    private void e(final int n) {
        final boolean l = c.l;
        try {
            final int e = this.e();
            final al al = new al();
            final l i = new l(al);
            Label_0430: {
                if (n == 1) {
                    final int n2 = super.b + 2;
                    final int n3 = this.f - 4;
                    final byte[] array = new byte[this.i + n3];
                    System.arraycopy(super.a, this.h, array, 0, this.i);
                    System.arraycopy(super.a, n2, array, this.i, n3);
                    al.a(array, 0, this.i + n3);
                    if (!l) {
                        break Label_0430;
                    }
                }
                int g = 0;
                if (n > 2) {
                    g = this.g();
                }
                int b = super.b;
                int n4 = 0;
                int n5 = 0;
                int b2 = super.b;
                int n6;
                while (true) {
                Label_0216:
                    while (true) {
                        Label_0207: {
                            if (!l) {
                                break Label_0207;
                            }
                            if ((super.a[b2] & 0xFF) == 0xFF) {
                                if ((super.a[b2 + 1] & 0xFF) == 0xD9) {
                                    n5 = b2 - super.b;
                                    if (!l) {
                                        break Label_0216;
                                    }
                                }
                            }
                            ++b2;
                        }
                        if (b2 < this.e) {
                            continue;
                        }
                        break;
                    }
                    n6 = super.b + n5;
                    if (l) {
                        continue;
                    }
                    break;
                }
                int n8 = 0;
                while (true) {
                Label_0323:
                    while (true) {
                        Label_0314: {
                            if (!l) {
                                break Label_0314;
                            }
                            final int n7 = super.a[n6] & 0xFF;
                            if (n7 == n8) {
                                if ((super.a[n6 + 1] & 0xFF) == 0xD8) {
                                    b = n6 + 2;
                                    n4 = this.e - b;
                                    if (n <= 2) {
                                        break Label_0323;
                                    }
                                    n4 = super.b + g - b;
                                    if (!l) {
                                        break Label_0323;
                                    }
                                }
                            }
                            ++n6;
                        }
                        if (n6 < this.e) {
                            continue;
                        }
                        break;
                    }
                    final byte[] array2 = new byte[n5 + n4];
                    System.arraycopy(super.a, super.b, array2, 0, n5);
                    System.arraycopy(super.a, b, array2, n5, n4);
                    al.a(array2, 0, n5 + n4);
                    final int n7 = n;
                    n8 = 2;
                    if (l) {
                        continue;
                    }
                    break;
                }
                if (n > n8) {
                    al.a(super.a, super.b + g, this.f - 6 - g, n == 3);
                    i.l = true;
                }
            }
            this.b.a(e, i);
        }
        catch (Exception ex) {}
    }
    
    private void f(final int n) {
        final boolean l = c.l;
        final int e = this.e();
        final int d = this.d();
        final int e2 = this.e();
        final int e3 = this.e();
        int n2 = e2;
        int d2 = 0;
        int[] array = null;
        if (d == 3) {
            d2 = this.d();
            array = new int[++d2];
            if ((e2 & 0x3) != 0x0) {
                n2 = e2 - (e2 & 0x3) + 4;
            }
        }
        final int n3 = n2 * e3;
        final int b = super.b;
        final int c = super.c;
        final int n4 = c - b;
        final byte[] input = new byte[c - b];
        final byte[] array2 = new byte[n3 * 4];
        System.arraycopy(super.a, b, input, 0, n4);
        try {
            final Inflater inflater = new Inflater();
            inflater.setInput(input);
            inflater.inflate(array2);
        }
        catch (Exception ex) {
            return;
        }
        final int[] array3 = new int[e2 * e3];
        if (d == 5) {
            int n5 = 0;
            while (true) {
                Label_0426: {
                    if (!l) {
                        break Label_0426;
                    }
                    final int n6 = n5 << 2;
                    int n7 = 0;
                    Label_0225: {
                        if (n == 2) {
                            n7 = array2[n6];
                            if (n7 >= 0) {
                                break Label_0225;
                            }
                            n7 += 256;
                            if (!l) {
                                break Label_0225;
                            }
                        }
                        n7 = 255;
                    }
                    int n8 = array2[n6 + 1];
                    if (n8 < 0) {
                        n8 += 256;
                    }
                    int n9 = array2[n6 + 2];
                    if (n9 < 0) {
                        n9 += 256;
                    }
                    int n10 = array2[n6 + 3];
                    if (n10 < 0) {
                        n10 += 256;
                    }
                    if (n8 > n7) {
                        n8 = n7;
                    }
                    if (n9 > n7) {
                        n9 = n7;
                    }
                    if (n10 > n7) {
                        n10 = n7;
                    }
                    if (n7 > 0) {
                        n8 = (n8 << 8) / n7;
                        n9 = (n9 << 8) / n7;
                        n10 = (n10 << 8) / n7;
                        if (n8 > 255) {
                            n8 = 255;
                        }
                        if (n9 > 255) {
                            n9 = 255;
                        }
                        if (n10 > 255) {
                            n10 = 255;
                        }
                    }
                    array3[n5] = n10 + (n9 << 8) + (n8 << 16) + (n7 << 24);
                    ++n5;
                }
                if (n5 < n3) {
                    continue;
                }
                break;
            }
        }
        if (d == 3) {
            int n11 = 0;
            int n13 = 0;
            boolean b3 = false;
            while (true) {
                while (true) {
                    Label_0585: {
                        if (!l) {
                            break Label_0585;
                        }
                        final boolean b2 = n != 0;
                        final int n12 = n13 * ((b2 == b3) ? 3 : 4);
                        int n14 = array2[n12];
                        if (n14 < 0) {
                            n14 += 256;
                        }
                        int n15 = array2[n12 + 1];
                        if (n15 < 0) {
                            n15 += 256;
                        }
                        int n16 = array2[n12 + 2];
                        if (n16 < 0) {
                            n16 += 256;
                        }
                        int n17 = 255;
                        if (n == 2) {
                            n17 = array2[n12 + 3];
                            if (n17 < 0) {
                                n17 += 256;
                            }
                        }
                        array[n11] = n16 + (n15 << 8) + (n14 << 16) + (n17 << 24);
                        ++n11;
                    }
                    if (n11 < d2) {
                        continue;
                    }
                    break;
                }
                n13 = d2;
                final boolean b2 = n != 0;
                b3 = true;
                if (l) {
                    continue;
                }
                break;
            }
            final int n18 = n13 * ((n == (b3 ? 1 : 0)) ? 3 : 4);
            int n19 = 0;
            while (true) {
                Label_0694: {
                    if (!l) {
                        break Label_0694;
                    }
                    int n20 = 0;
                    while (true) {
                        Label_0684: {
                            if (!l) {
                                break Label_0684;
                            }
                            final int n21 = n2 * n19 + n20 + n18;
                            final int n22 = e2 * n19 + n20;
                            int n23 = array2[n21];
                            if (n23 < 0) {
                                n23 += 256;
                            }
                            array3[n22] = array[n23];
                            ++n20;
                        }
                        if (n20 < e2) {
                            continue;
                        }
                        break;
                    }
                    ++n19;
                }
                if (n19 < e3) {
                    continue;
                }
                break;
            }
        }
        final l i = new l(array3, e2, e3);
        i.l = (n == 2);
        this.b.a(e, i);
        i.a = 1;
    }
    
    void a(final String s) {
        final int e = this.e();
        final String m = this.m();
        final int g = this.g();
        try {
            final am am = (am)Class.forName(blaze3d.b.gc(s)).newInstance();
            am.a(super.a, super.b, g);
            final int n = this.f - 6 - (m.length() + 1);
            if (g != n) {
                am.a(super.a, super.b + g, n - g, false);
            }
            final l l = new l(am);
            l.e = m;
            this.b.a(e, l);
            l.a = 1;
        }
        catch (Exception ex) {}
    }
    
    void p() {
        final boolean l = c.l;
        super.b = 3;
        this.b.b(1);
        this.b.k.b();
        while (true) {
            while (true) {
                Label_0038: {
                    if (!l) {
                        break Label_0038;
                    }
                    final x x = this;
                    x.b();
                }
                if (this.g < this.d) {
                    continue;
                }
                break;
            }
            final al al = new al();
            al.a(super.a, 0, this.d);
            final l i = new l(al);
            this.b.a(1, i);
            i.d();
            final int n = i.c * 20;
            final int n2 = i.d * 20;
            final ad ad = new ad(1);
            ad.b(1);
            ad.c(0, 0);
            ad.e(0, n2);
            ad.e(n, n2);
            ad.e(n, 0);
            ad.e(0, 0);
            this.b.a(2, ad);
            this.b.a(new af(0, 0, 2, 1, new ac(), null, -1, null, -1, null));
            this.b.b = 1;
            final x x = this;
            if (!l) {
                this.b.f();
                return;
            }
            continue;
        }
    }
}
