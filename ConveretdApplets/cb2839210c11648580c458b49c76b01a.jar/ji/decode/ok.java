// 
// Decompiled by Procyon v0.5.30
// 

package ji.decode;

import ji.res.s;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import ji.io.a5;
import ji.io.q;
import ji.v1event.a6;
import ji.image.ds;
import java.awt.Font;
import ji.font.j;
import ji.font.d2;
import ji.util.i;
import java.awt.Dimension;
import ji.util.d;
import ji.v1event.af;
import ji.document.ad;
import ji.image.dx;
import ji.util.e;
import java.awt.Graphics;
import java.awt.Component;
import ji.io.ac;
import java.awt.Color;
import java.util.Hashtable;
import ji.awt.c;
import ji.font.ct;
import ji.font.d1;

public class ok
{
    private boolean a;
    private boolean b;
    private short[] c;
    private int[] d;
    private int[] e;
    private int f;
    private int g;
    private int h;
    private byte[] i;
    private d1 j;
    private String k;
    private String l;
    private boolean m;
    private boolean n;
    private boolean o;
    private boolean p;
    private int q;
    private int r;
    private int s;
    private double t;
    private double u;
    private boolean v;
    private int w;
    private int x;
    private int y;
    private int z;
    private int aa;
    private int ab;
    private ct ac;
    private int ad;
    private String ae;
    private String af;
    private int ag;
    private int ah;
    private int ai;
    private int aj;
    private int ak;
    private int al;
    private int am;
    private int an;
    private double ao;
    private int ap;
    private int aq;
    private long ar;
    private boolean as;
    private boolean at;
    private c au;
    private double av;
    private boolean aw;
    private boolean ax;
    private boolean ay;
    Hashtable az;
    
    public ok(final String k, final double t, final double u, final int ap, final double ao, final int aq, final Color color, final boolean aw, final boolean ax, final boolean ay) {
        this.a = false;
        this.b = false;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = (Color.white.getRGB() & 0xFFFFFF);
        this.g = (Color.black.getRGB() & 0xFFFFFF);
        this.h = 5120;
        this.i = new byte[this.h + 3];
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = false;
        this.n = false;
        this.o = false;
        this.p = false;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.t = 1.0;
        this.u = 1.0;
        this.v = false;
        this.w = 0;
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.aa = 0;
        this.ab = 0;
        this.ac = null;
        this.ad = new Color(0, 0, 0).getRGB();
        this.ae = null;
        this.af = null;
        this.ag = -1;
        this.ah = 0;
        this.ai = 0;
        this.aj = 0;
        this.ak = 1;
        this.al = 0;
        this.am = 0;
        this.an = 0;
        this.ao = 0.0;
        this.ap = 0;
        this.aq = 1;
        this.ar = 0L;
        this.as = false;
        this.at = true;
        this.av = 1.0;
        this.aw = true;
        this.ax = false;
        this.ay = false;
        this.az = new Hashtable();
        this.k = k;
        this.t = t;
        this.u = u;
        this.ap = ap;
        this.ao = ao;
        this.aq = aq;
        this.aw = aw;
        this.ax = ax;
        this.ay = ay;
        if (color != null) {
            this.ad = color.getRGB();
        }
    }
    
    public void a(final boolean at) {
        this.at = at;
    }
    
    public void a(final ct ct) {
        if (ct != null) {
            this.ac = ct.c();
        }
    }
    
    public String a() {
        return this.ae;
    }
    
    public String b() {
        return this.af;
    }
    
    public int c() {
        return this.ag;
    }
    
    public int d() {
        if (this.j != null) {
            return this.j.h();
        }
        return 0;
    }
    
    public void b(final boolean p) {
        this.p = p;
    }
    
    public void a(final int aa, final int ab) {
        this.aa = aa;
        this.ab = ab;
    }
    
    public void a(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.w = Math.max(n, 0);
        this.x = Math.max(n2, 0);
        this.y = Math.max(n3, 0);
        this.z = Math.max(n4, 0);
        this.ah = Math.max(n5, 0);
    }
    
    public void a(final c au, final double av) {
        this.au = au;
        this.av = av;
    }
    
    public final boolean a(final ac ac) {
        this.l = "";
        boolean a2;
        try {
            ac.a(0L);
            final byte[] array = new byte[Math.min(20480, (int)ac.v())];
            final int a = ac.a(array);
            this.l = new String(array);
            a2 = this.a(this.l.toCharArray(), a);
        }
        catch (Exception ex) {
            a2 = false;
        }
        return a2;
    }
    
    public final boolean a(final char[] array, final int n) {
        boolean b = true;
        try {
            int n2 = 0;
            boolean b2 = false;
            boolean b3 = false;
            for (int i = 0; i < n; ++i) {
                final char c = array[i];
                if (c < ' ' && c != '\n' && c != '\r' && c != '\0') {
                    ++n2;
                }
                if (array[i] == '\r') {
                    b2 = true;
                }
                if (array[i] == '\n') {
                    b3 = true;
                }
            }
            if (!b2) {
                this.m = true;
            }
            else if (!b3) {
                this.m = true;
            }
            this.n = true;
            if (n2 > n / 3) {
                b = false;
            }
        }
        catch (Exception ex) {
            b = false;
        }
        return b;
    }
    
    private final Graphics a(final Component component) {
        Graphics graphics = null;
        if (component != null) {
            graphics = component.getGraphics();
        }
        else if (ji.util.e.v != null) {
            graphics = ji.util.e.v.getGraphics();
        }
        if (graphics != null) {
            return graphics.create();
        }
        return graphics;
    }
    
    public final void a(final dx dx, final ac ac, final ad ad, final af af, final boolean b, final int n, final boolean b2) {
        try {
            ac.a(0L);
            if (!this.n) {
                this.a(ac);
            }
            if (this.a(ad) != null || ji.util.d.b()) {
                this.a(ad, af);
                ac.a(0L);
                final int n2 = (int)ac.v();
                int i = 0;
                final int n3 = 0;
                this.r = 0;
                this.s = this.j.h();
                this.q = 0;
                if (this.ah > 0) {
                    this.ai = Math.max(this.ah - this.j.h(), 0);
                }
                else {
                    this.ai = 0;
                }
                if (this.ai > 200) {
                    this.ai /= 600;
                }
                if (this.ai > 100) {
                    this.ai = 0;
                }
                this.al = 0;
                this.am = 0;
                this.an = 0;
                this.aj = 0;
                this.ak = 1;
                while (i < n2) {
                    try {
                        final int a = ac.a(this.i, 0, this.h);
                        if (a < this.i.length) {
                            this.i[a] = 65;
                            this.i[a + 1] = 65;
                            this.i[a + 2] = 65;
                        }
                        final Dimension a2 = this.a(ad, a, this.j.g(), this.j.h(), n, b2);
                        this.r = Math.max(this.r, a2.width);
                        this.s = a2.height;
                        i += a;
                    }
                    catch (Exception ex) {
                        if (!ji.util.d.cy()) {
                            continue;
                        }
                        ex.printStackTrace();
                    }
                }
                if (this.al > 1) {
                    this.s += this.j.h();
                }
                this.r = Math.max(this.r, n3 + 2 * this.j.g());
                this.v = true;
            }
        }
        catch (Exception ex2) {
            if (ji.util.d.cy()) {
                ex2.printStackTrace();
            }
        }
        if (dx != null) {
            if (this.aa != 0) {
                dx.m = this.aa;
                dx.n = this.ab;
            }
            else {
                final int m = this.w + this.x + (int)(this.r * this.t);
                final int n4 = this.y + this.z + (int)(this.s * this.u);
                if (b || dx.m < m) {
                    dx.m = m;
                }
                if (b || dx.n < n4) {
                    dx.n = n4;
                }
            }
        }
    }
    
    public final void a(final dx dx, final ac ac, final Component component, final af af, final int n, final int n2) {
        try {
            if (this.a(component) != null) {
                this.a(component, af);
                this.r = 0;
                this.s = this.j.h();
                if (this.ah > 0) {
                    this.ai = Math.max(this.ah - this.j.h(), 0);
                }
                else {
                    this.ai = 0;
                }
                if (this.ai > 200) {
                    this.ai /= 600;
                }
                if (this.ai > 100) {
                    this.ai = 0;
                }
                final int n3 = n * (this.j.h() + Math.max(1, this.ai));
                this.al = 0;
                this.am = 0;
                this.an = 0;
                this.aj = 0;
                this.ak = 1;
                Dimension dimension;
                if (n2 > 0 && n > 0) {
                    dimension = this.a(component, this.j.g(), this.j.h(), n, n2, 0, 0);
                }
                else {
                    ac.a(0L);
                    final int n4 = (int)ac.v();
                    int i = 0;
                    this.r = 0;
                    this.s = this.j.h();
                    this.q = 0;
                    if (this.ah > 0) {
                        this.ai = Math.max(this.ah - this.j.h(), 0);
                    }
                    else {
                        this.ai = 0;
                    }
                    if (this.ai > 200) {
                        this.ai /= 600;
                    }
                    if (this.ai > 100) {
                        this.ai = 0;
                    }
                    this.al = 0;
                    this.am = 0;
                    this.an = 0;
                    this.aj = 0;
                    this.ak = 1;
                    while (i < n4) {
                        try {
                            final int a = ac.a(this.i, 0, this.h);
                            if (a < this.i.length) {
                                this.i[a] = 65;
                                this.i[a + 1] = 65;
                                this.i[a + 2] = 65;
                            }
                            final Dimension a2 = this.a(component, a, this.j.g(), this.j.h(), 0, false);
                            this.r = Math.max(this.r, a2.width);
                            this.s = a2.height;
                            i += a;
                        }
                        catch (Exception ex) {
                            if (!ji.util.d.cy()) {
                                continue;
                            }
                            ex.printStackTrace();
                        }
                    }
                    dimension = this.a(component, this.j.g(), this.j.h(), n, n2, this.s, this.r);
                }
                this.r = Math.max(this.r, dimension.width);
                this.s = dimension.height;
                if (this.al > 1) {
                    this.s += this.j.h();
                }
                this.v = true;
            }
        }
        catch (Exception ex2) {
            if (ji.util.d.cy()) {
                ex2.printStackTrace();
            }
        }
        if (dx != null) {
            if (this.aa != 0) {
                dx.m = this.aa;
                dx.n = this.ab;
            }
            else {
                final int m = this.w + this.x + (int)(this.r * this.t);
                final int n5 = this.y + this.z + (int)(this.s * this.u);
                dx.m = m;
                dx.n = n5;
            }
        }
    }
    
    public final int e() {
        return this.aj;
    }
    
    private final Dimension a(final Component component, final int n, final int n2, final int n3, final int n4, final boolean b) {
        int n5 = 0;
        try {
            final int n6 = 5 + n2;
            final int max = Math.max(1, this.ai);
            int n7 = 0;
            if (this.au != null) {
                final ol ol = (ol)this.au.d(String.valueOf(this.aj));
                if (ol != null) {
                    n7 = (int)(ol.a() * this.av);
                    this.al = n7 / n6;
                    this.an = (int)(ol.b() * this.av);
                }
            }
            int n8 = 0;
            int n9 = n;
            if (b) {
                int n10 = 1;
                int n11 = 0;
                for (int i = 0; i < n; ++i) {
                    if (n11 == 0 && n10 == n4 && this.i[i] != 12) {
                        n8 = i;
                        n11 = 1;
                    }
                    if (n10 == n4 && this.i[i] != 12) {
                        n9 = i;
                    }
                    if (this.i[i] == 12) {
                        if (n11 != 0) {
                            break;
                        }
                        ++n10;
                    }
                }
            }
            int n12 = 1;
            for (int j = n8; j < n9; ++j) {
                try {
                    if (b && this.i[j] == 12) {
                        ++this.ak;
                    }
                    if (this.i[j] == 13 || this.i[j] == 10) {
                        if (n12 == 0 || !ji.util.i.c(178)) {
                            this.q = Math.max(this.q, this.al);
                            this.am = Math.max(this.am, n7 + 2 * n6);
                        }
                        this.al = 0;
                        n7 = 0;
                        this.an = this.an + n3 + max;
                        ++n5;
                        ++this.aj;
                        if (this.au != null) {
                            final ol ol2 = (ol)this.au.d(String.valueOf(this.aj));
                            if (ol2 != null) {
                                n7 = (int)(ol2.a() * this.av);
                                this.al = n7 / n6;
                                this.an = (int)(ol2.b() * this.av);
                            }
                        }
                        if (j < n - 1) {
                            while (this.i[j + 1] == 13) {
                                ++j;
                                if (this.m) {
                                    ++this.aj;
                                    this.an = this.an + n3 + max;
                                }
                            }
                            if (!this.m && this.i[j + 1] == 10) {
                                ++j;
                            }
                        }
                        n12 = 1;
                    }
                    else {
                        if (this.i[j] == 9) {
                            n7 += 8 * n6;
                            this.al += 8;
                        }
                        else if ((this.i[j] & 0xFF) >= 32) {
                            if ((this.i[j] & 0xFF) > 32) {
                                n12 = 0;
                            }
                            if (ji.util.i.c(247) && this.at) {
                                d2 b2 = null;
                                if ((this.i[j] & 0xFF) != 0x20) {
                                    final String concat = "".concat(String.valueOf(String.valueOf((char)(this.i[j] & 0xFF))));
                                    b2 = (d2)this.az.get(concat);
                                    if (b2 == null) {
                                        b2 = this.j.b(component, concat, false, false, null);
                                        if (b2 != null) {
                                            this.az.put(concat, b2);
                                        }
                                    }
                                }
                                if (b2 != null) {
                                    n7 += b2.i - 4;
                                }
                                else {
                                    n7 += 28;
                                }
                            }
                            else {
                                n7 += this.j.a(component, "".concat(String.valueOf(String.valueOf((char)(this.i[j] & 0xFF)))), false, null) - 4;
                            }
                            ++this.al;
                        }
                        this.q = Math.max(this.q, this.al);
                    }
                }
                catch (Exception ex) {
                    if (ji.util.d.cy()) {
                        ex.printStackTrace();
                    }
                }
            }
            if (n12 == 0 || !ji.util.i.c(178)) {
                this.am = Math.max(this.am, n7 + 2 * n6);
            }
        }
        catch (Exception ex2) {
            if (ji.util.d.cy()) {
                ex2.printStackTrace();
            }
        }
        ++this.aj;
        return new Dimension(this.am, Math.max(this.an, n3));
    }
    
    private final Dimension a(final Component component, final int n, final int n2, final int n3, final int n4, final int an, final int am) {
        try {
            final int n5 = 5 + n;
            final int max = Math.max(1, this.ai);
            if (n3 > 0) {
                this.an = n3 * (n2 + max);
            }
            else {
                this.an = an;
            }
            if (n4 > 0) {
                this.am = n4 * n5 - n5;
            }
            else {
                this.am = am;
            }
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
        return new Dimension(this.am, this.an);
    }
    
    public int f() {
        return this.q;
    }
    
    private final void a(final Component component, final af af) {
        try {
            if (this.j == null) {
                Font a = null;
                if (ji.util.i.c(247) && ji.util.e.av() && this.at && ji.font.j.i(ji.font.j.i)) {
                    a = ji.font.j.a(ji.font.j.a(component, ji.font.j.i, af, this.k), (false | true) ? 1 : 0, ji.font.j.r, component, this.k);
                    if (a != null) {
                        this.ae = a.getName();
                    }
                }
                if (this.ac == null) {
                    if (ji.util.i.c(247) && this.at) {
                        this.ac = ji.font.j.a(a, ji.font.j.a(this.k, ji.font.j.i, component), this.at);
                        this.t *= 0.6;
                    }
                    else {
                        this.ac = ji.font.j.b(a);
                    }
                }
                this.j = ji.font.j.a(component, af, this.k, this.ac, 1.0, this.at);
                this.af = ji.font.j.q();
                if (a == null) {
                    this.ae = ji.font.j.r();
                }
                this.ag = ji.font.j.s();
                if (ji.util.i.c(247) && this.at) {
                    this.j.b(false);
                    this.j.a(true);
                }
                if (this.ap > 0) {
                    this.u = this.ap / this.j.h();
                    this.t *= this.u;
                    if (ji.util.i.c(263) && ji.util.i.c(247) && this.at) {
                        this.t *= 0.945;
                    }
                    if (this.ao > 0.0) {
                        this.u *= this.ao;
                    }
                }
            }
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
    }
    
    public final void a(final ac ac, final ds ds, final dx dx, final af af, final ad ad) throws Exception {
        this.a = false;
        int n = -1;
        int n2 = 5;
        int[] array = null;
        int[] array2 = null;
        int[] array3 = null;
        int[] array4 = null;
        byte[] array5 = null;
        Object o = null;
        try {
            a6 a6 = new a6(this, 4, "");
            if (ji.util.d.du() && dx.ak) {
                a6 = new a6(this, 23, "0");
                n2 = 10;
            }
            final a6 a7 = new a6(this, 1, String.valueOf(String.valueOf(ji.util.d.b(315, this.k))).concat("..."));
            if (this.a(ad) != null || ji.util.d.b()) {
                this.a(ad, af);
                final int h = this.j.h();
                final int n3 = 10;
                final int n4 = dx.n;
                int n5 = 0;
                final int m = dx.m;
                Math.min(h * n3, n4);
                int n6 = this.s / h;
                int i = (int)(n6 * this.u);
                this.b = (n4 > 5000);
                if (this.aq == 1) {
                    ds.a(1, true, this.b, ad);
                    this.b = ds.j();
                }
                else {
                    ds.a(4, this.b = false, this.b, ad);
                }
                final int n7 = (int)(h * this.u);
                ac.a(0L);
                int n8 = h;
                final byte[] array6 = new byte[10 * this.j.g() + 2 * this.r * this.j.h()];
                final byte[] array7 = new byte[array6.length];
                for (int j = 0; j < array7.length; ++j) {
                    array7[j] = 0;
                }
                System.arraycopy(array7, 0, array6, 0, array7.length);
                int n9 = 0;
                final int n10 = 1 + this.r / this.j.g();
                int k = (int)ac.v();
                final q a8 = ji.io.q.a(ad, this.k);
                final String n11 = a8.n();
                final ac ac2 = new ac(n11, true, false, 0, false, ad, this.k);
                int n12 = 2048;
                final byte[] array8 = new byte[n12];
                while (k > 0) {
                    if (k < n12) {
                        n12 = k;
                    }
                    final int a9 = ac.a(array8, 0, n12);
                    if (a9 <= 0) {
                        break;
                    }
                    ac2.b(array8, 0, a9);
                    k -= a9;
                }
                ac2.a(ad);
                final int n13 = (int)Math.min(102400L, ac.a(n11, this.k));
                final ac ac3 = new ac(n11, false, true, n13, false, ad, this.k);
                String s = ji.util.d.bt();
                if (s == null) {
                    s = ji.util.d.bm(this.k);
                }
                final a5 a10 = new a5(ac3, ad);
                final InputStreamReader inputStreamReader = new InputStreamReader(a10, s);
                final BufferedReader bufferedReader = new BufferedReader(inputStreamReader, n13);
                final boolean b = this.au != null;
                try {
                    for (int l = 0; l < this.y; ++l) {
                        n9 += this.a(dx, ds, array7, m, 1, n9, m, ad);
                        ++i;
                    }
                }
                catch (Exception ex4) {}
                ++i;
                this.ar = (int)ac3.w();
                this.as = false;
                if (this.ax) {
                    this.a(bufferedReader, n5);
                    ++n5;
                }
                while (!this.as && this.ar > 0 && !this.i()) {
                    char[] array9 = this.a(bufferedReader, n5);
                    if (this.i()) {
                        return;
                    }
                    if (b) {
                        final ol ol = (ol)this.au.d(String.valueOf(n5));
                        if (ol != null) {
                            for (int n14 = (int)(ol.b() * this.av - n9), n15 = 0; n15 < n14; ++n15) {
                                n9 += this.a(dx, ds, array7, m, 1, n9, m, ad);
                                ++i;
                                ++n6;
                            }
                            final int n16 = (int)(ol.a() * this.av / (this.j.g() * this.t));
                            final char[] array10 = new char[n16 + array9.length];
                            for (int n17 = 0; n17 < n16; ++n17) {
                                array10[n17] = ' ';
                            }
                            System.arraycopy(array9, 0, array10, n16, array9.length);
                            array9 = array10;
                        }
                    }
                    if ((this.p || ji.res.s.j(this.k)) && !b) {
                        final int n18 = 1 + this.q - array9.length;
                        final char[] array11 = new char[n18 + array9.length];
                        for (int n19 = 0; n19 < n18; ++n19) {
                            array11[n19] = ' ';
                        }
                        System.arraycopy(array9, 0, array11, n18, array9.length);
                        array9 = array11;
                    }
                    if (dx.bn) {
                        String s2 = ji.util.d.b(ji.util.d.b(ji.util.d.b(ji.util.d.b(ji.util.d.b(ji.util.d.b(new String(array9), "<user>", dx.bo), "<user>", "-"), "<description>", dx.bp), "<description>", "-"), "<page>", "".concat(String.valueOf(String.valueOf(dx.v)))), "<page>", "-");
                        if (dx.bq != null) {
                            s2 = ji.util.d.b(s2, "<file>", "".concat(String.valueOf(String.valueOf(dx.bq))));
                        }
                        else if (dx.f != null) {
                            s2 = ji.util.d.b(s2, "<file>", "".concat(String.valueOf(String.valueOf(dx.f))));
                        }
                        String s3 = ji.util.e.ae(ji.util.d.b(s2, "<file>", "-"));
                        if (s3.length() > dx.br) {
                            s3 = s3.substring(0, dx.br);
                        }
                        array9 = s3.toCharArray();
                    }
                    n8 += h;
                    ++n5;
                    final int length = array9.length;
                    int n20 = 0;
                    try {
                        try {
                            for (int n21 = 0; n21 < length; ++n21) {
                                char c;
                                if (n21 >= length) {
                                    c = ' ';
                                }
                                else {
                                    c = array9[n21];
                                }
                                if (c >= ' ') {
                                    final int a11 = this.j.a(ad, "".concat(String.valueOf(String.valueOf(c))), false, af);
                                    d2 d2;
                                    if (ji.util.i.c(247) && this.at) {
                                        final String concat = "".concat(String.valueOf(String.valueOf(c)));
                                        d2 = this.az.get(concat);
                                        if (d2 != null && d2.c == null && d2.b == null) {
                                            this.az.remove(concat);
                                            d2 = null;
                                        }
                                        if (d2 == null) {
                                            d2 = this.j.a(ad, concat, ji.util.i.c(247) && this.at, false, false, false, af);
                                            if (d2 != null) {
                                                this.az.put(concat, d2);
                                            }
                                        }
                                    }
                                    else {
                                        d2 = this.j.a(ad, "".concat(String.valueOf(String.valueOf(c))), ji.util.i.c(247) && this.at, false, false, false, af);
                                    }
                                    Label_1713: {
                                        if (d2.c != null) {
                                            try {
                                                for (int n22 = 0; n22 < h; ++n22) {
                                                    final byte[] b2 = d2.c.b(n22);
                                                    final int n23 = n22 * this.r;
                                                    for (int n24 = 0; n24 < a11 - 4; ++n24) {
                                                        if (n24 < b2.length) {
                                                            array6[n20 + n24 + n23] = b2[n24];
                                                        }
                                                    }
                                                }
                                                break Label_1713;
                                            }
                                            catch (Exception ex) {
                                                ex.printStackTrace();
                                                break Label_1713;
                                            }
                                        }
                                        try {
                                            final byte[] b3 = d2.b;
                                            for (int n25 = 0; n25 < h; ++n25) {
                                                final int n26 = n25 * this.r;
                                                final int n27 = n25 * a11;
                                                for (int n28 = 0; n28 < a11 - 4; ++n28) {
                                                    array6[n20 + n28 + n26] = b3[n27 + n28];
                                                }
                                            }
                                        }
                                        catch (Exception ex2) {
                                            ex2.printStackTrace();
                                        }
                                    }
                                    n20 += a11 - 4;
                                }
                            }
                        }
                        catch (Exception ex3) {
                            ji.util.d.a(ex3);
                            if (n5 > 100) {
                                throw ex3;
                            }
                        }
                        Label_2676: {
                            if (this.t != 1.0 || this.u != 1.0) {
                                if (array5 == null) {
                                    array5 = new byte[m * n7];
                                }
                                try {
                                    if (this.u < 1.0) {
                                        if (array3 == null) {
                                            array3 = new int[h];
                                            array4 = new int[h];
                                            for (int n29 = 0; n29 < h; ++n29) {
                                                array3[n29] = (int)(n29 * this.u) * m;
                                                array4[n29] = n29 * this.r;
                                            }
                                        }
                                        if (this.t < 1.0) {
                                            if (array == null) {
                                                array = new int[this.r];
                                                final int n30 = m - 1;
                                                for (int n31 = 0; n31 < this.r; ++n31) {
                                                    array[n31] = Math.min((int)(n31 * this.t), n30);
                                                }
                                            }
                                            for (int n32 = 0; n32 < h; ++n32) {
                                                for (int n33 = 0; n33 < this.r; ++n33) {
                                                    final byte[] array12 = array5;
                                                    final int n34 = array[n33] + array3[n32];
                                                    array12[n34] |= array6[n33 + array4[n32]];
                                                }
                                            }
                                        }
                                        else {
                                            if (array2 == null) {
                                                array2 = new int[m];
                                                for (int n35 = 0; n35 < m; ++n35) {
                                                    array2[n35] = (int)(n35 / this.t);
                                                }
                                            }
                                            for (int n36 = 0; n36 < h; ++n36) {
                                                for (int n37 = 0; n37 < m; ++n37) {
                                                    final byte[] array13 = array5;
                                                    final int n38 = n37 + array3[n36];
                                                    array13[n38] |= array6[array2[n37] + array4[n36]];
                                                }
                                            }
                                        }
                                    }
                                    else {
                                        if (array3 == null) {
                                            array3 = new int[n7];
                                            array4 = new int[n7];
                                            for (int n39 = 0; n39 < n7; ++n39) {
                                                array3[n39] = n39 * m;
                                                array4[n39] = (int)(n39 / this.u) * this.r;
                                            }
                                        }
                                        if (this.t < 1.0) {
                                            if (array == null) {
                                                array = new int[this.r];
                                                final int n40 = m - 1;
                                                for (int n41 = 0; n41 < this.r; ++n41) {
                                                    array[n41] = Math.min((int)(n41 * this.t), n40);
                                                }
                                            }
                                            for (int n42 = 0; n42 < n7; ++n42) {
                                                for (int n43 = 0; n43 < this.r; ++n43) {
                                                    final byte[] array14 = array5;
                                                    final int n44 = array[n43] + array3[n42];
                                                    array14[n44] |= array6[n43 + array4[n42]];
                                                }
                                            }
                                        }
                                        else {
                                            if (array2 == null) {
                                                array2 = new int[m];
                                                for (int n45 = 0; n45 < m; ++n45) {
                                                    array2[n45] = (int)(n45 / this.t);
                                                }
                                            }
                                            for (int n46 = 0; n46 < n7; ++n46) {
                                                for (int n47 = 0; n47 < m; ++n47) {
                                                    final byte[] array15 = array5;
                                                    final int n48 = n47 + array3[n46];
                                                    array15[n48] |= array6[array2[n47] + array4[n46]];
                                                }
                                            }
                                        }
                                    }
                                }
                                catch (Exception ex5) {}
                                Label_2476: {
                                    if (n20 == 0) {
                                        try {
                                            for (int n49 = 0; n49 < n7; ++n49) {
                                                n9 += this.a(dx, ds, array7, m, 1, n9, m, ad);
                                                ++i;
                                            }
                                            break Label_2476;
                                        }
                                        catch (Exception ex6) {
                                            break Label_2476;
                                        }
                                    }
                                    n9 += this.a(dx, ds, array5, (int)(n20 * this.t), n7, n9, m, ad);
                                }
                                if (o == null) {
                                    o = new byte[array5.length];
                                    for (int n50 = 0; n50 < o.length; ++n50) {
                                        o[n50] = false;
                                    }
                                }
                                System.arraycopy(o, 0, array5, 0, o.length);
                                try {
                                    if (this.ai > 0 && !b) {
                                        for (int n51 = 0; n51 < this.ai; ++n51) {
                                            n9 += this.a(dx, ds, array7, m, 1, n9, m, ad);
                                            ++i;
                                        }
                                    }
                                    break Label_2676;
                                }
                                catch (Exception ex7) {
                                    break Label_2676;
                                }
                            }
                            n9 += this.a(dx, ds, array6, n20, h, n9, this.r, ad);
                            System.arraycopy(array7, 0, array6, 0, array7.length);
                            try {
                                if (this.ai > 0 && !b) {
                                    for (int n52 = 0; n52 < this.ai; ++n52) {
                                        n9 += this.a(dx, ds, array7, m, 1, n9, m, ad);
                                        ++i;
                                    }
                                }
                            }
                            catch (Exception ex8) {}
                        }
                        if (af == null) {
                            continue;
                        }
                        final int n53 = 100 * n5 / n6;
                        if (n53 != n && n53 >= n + n2) {
                            af.a(a7);
                            n = n53;
                            a6.a("".concat(String.valueOf(String.valueOf(n53))));
                            af.a(a6);
                            continue;
                        }
                        continue;
                    }
                    catch (Exception ex9) {}
                    break;
                }
                try {
                    if (!b) {
                        for (int n54 = 0; n54 < this.z; ++n54) {
                            n9 += this.a(dx, ds, array7, m, 1, n9, m, ad);
                            ++i;
                        }
                    }
                }
                catch (Exception ex10) {}
                if (this.ab > 0) {
                    while (i < this.ab) {
                        n9 += this.a(dx, ds, array7, m, 1, n9, m, ad);
                        ++i;
                    }
                }
                if (af != null) {
                    a6.a("100");
                    af.a(a6);
                }
                if (this.aq != 1) {
                    ds.e(ad);
                }
                try {
                    if (a10 != null) {
                        a10.close();
                    }
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    if (inputStreamReader != null) {
                        inputStreamReader.close();
                    }
                    if (ac3 != null) {
                        ac3.a(ad);
                    }
                    if (n11 != null) {
                        a8.d(n11);
                        ac.c(n11, this.k);
                    }
                }
                catch (Exception ex11) {}
            }
            if (af != null) {
                a7.a("");
                af.a(a7);
                a6.a("0");
                af.a(a6);
            }
        }
        catch (Exception ex12) {}
    }
    
    private final void h() {
        try {
            if (this.az != null) {
                this.az.clear();
                this.az = null;
            }
        }
        catch (Exception ex) {}
    }
    
    private final char[] a(final BufferedReader bufferedReader, final int n) {
        String s = "";
        try {
            final boolean b = false;
            s = "";
            if (!this.m) {
                while (!b && !this.i()) {
                    final int read = bufferedReader.read();
                    if (read == 10) {
                        --this.ar;
                        if (s.length() == 0 && !this.ay) {
                            continue;
                        }
                        break;
                    }
                    else {
                        if (read < 0) {
                            this.as = true;
                            break;
                        }
                        --this.ar;
                        s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf((char)read)));
                    }
                }
            }
            else {
                s = bufferedReader.readLine();
                if (s != null) {
                    this.ar -= s.length();
                }
            }
            if (s != null) {
                final int length = s.length();
                final char[] charArray = s.toCharArray();
                s = "";
                for (int n2 = 0; n2 < length && !this.i(); ++n2) {
                    if (charArray[n2] == '\t') {
                        s = String.valueOf(String.valueOf(s)).concat("        ");
                    }
                    else {
                        s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(charArray[n2])));
                    }
                }
            }
            else {
                this.as = true;
            }
            if (this.i()) {
                return null;
            }
        }
        catch (Exception ex) {
            if (ji.util.d.cy()) {
                ex.printStackTrace();
            }
        }
        if (this.aw) {
            if (s == null) {
                return " ".toCharArray();
            }
            return " ".concat(String.valueOf(String.valueOf(s))).toCharArray();
        }
        else {
            if (s == null) {
                return new char[0];
            }
            return s.toCharArray();
        }
    }
    
    private final int a(final dx dx, final ds ds, final byte[] array, final int n, final int n2, final int n3, final int n4, final Object o) throws Exception {
        int n5 = n3;
        final int n6 = n * 2;
        if (n6 == 0) {
            return n2;
        }
        if (this.c == null) {
            this.c = new short[n6];
        }
        if (this.b && this.d == null) {
            this.d = new int[n6];
        }
        int i = 0;
        short n7 = 1;
        for (int j = 0; j < n2; ++j) {
            final int n8 = j * n4;
            short n9 = 0;
            int n10 = array[n8];
            int n11 = 0;
            if (this.w != 0) {
                while (i + 2 > this.c.length - 1) {
                    final short[] c = new short[this.c.length + n6];
                    System.arraycopy(this.c, 0, c, 0, this.c.length);
                    this.c = c;
                }
                this.c[i++] = (short)this.w;
                this.c[i++] = 0;
            }
            if (n10 != 0) {
                this.c[i++] = 0;
                n11 = 1;
            }
            for (int k = 1; k < n; ++k) {
                try {
                    final int n12 = n8 + k;
                    if (n12 < array.length) {
                        final int n13 = array[n12] & 0xFF;
                        if (n13 != n10) {
                            n9 += n7;
                            while (i > this.c.length - 1) {
                                final short[] c2 = new short[this.c.length + n6];
                                System.arraycopy(this.c, 0, c2, 0, this.c.length);
                                this.c = c2;
                            }
                            this.c[i++] = n7;
                            n10 = n13;
                            n7 = 1;
                            n11 = 1 - n11;
                        }
                        else {
                            ++n7;
                        }
                    }
                }
                catch (Exception ex) {
                    if (ji.util.d.cy()) {
                        ex.printStackTrace();
                    }
                }
            }
            if (n7 > 0) {
                try {
                    while (i > this.c.length - 1) {
                        final short[] c3 = new short[this.c.length + n6];
                        System.arraycopy(this.c, 0, c3, 0, this.c.length);
                        this.c = c3;
                    }
                    this.c[i++] = n7;
                    n9 += n7;
                    n11 = 1 - n11;
                }
                catch (Exception ex2) {
                    if (ji.util.d.cy()) {
                        ex2.printStackTrace();
                    }
                }
            }
            if (this.x != 0) {
                try {
                    while (i + 4 > this.c.length - 1) {
                        final short[] c4 = new short[this.c.length + n6];
                        System.arraycopy(this.c, 0, c4, 0, this.c.length);
                        this.c = c4;
                    }
                    if (n11 == 0) {
                        this.c[i++] = 0;
                        n11 = 1 - n11;
                    }
                    this.c[i++] = 0;
                    this.c[i++] = (short)this.x;
                }
                catch (Exception ex4) {}
            }
            try {
                if (n9 < n4) {
                    while (i > this.c.length - 1) {
                        final short[] c5 = new short[this.c.length + n6];
                        System.arraycopy(this.c, 0, c5, 0, this.c.length);
                        this.c = c5;
                    }
                    if (n11 == 1) {
                        this.c[i++] = 0;
                        this.c[i++] = (short)(n4 - n9);
                    }
                    else {
                        this.c[i++] = (short)(n4 - n9);
                    }
                }
                if (this.aq == 1) {
                    if (this.b) {
                        if (ds.x()) {
                            final short[] a = ds.a(this.c, i, n5);
                            final int l = a.length;
                            while (l > this.d.length - 1) {
                                this.d = new int[l + n6];
                            }
                            for (int n14 = 0; n14 < l; ++n14) {
                                this.d[n14] = a[n14];
                            }
                            ds.a(this.d, l, n5, false, true);
                        }
                        else {
                            while (i > this.d.length - 1) {
                                this.d = new int[i + n6];
                            }
                            for (int n15 = 0; n15 < i; ++n15) {
                                this.d[n15] = this.c[n15];
                            }
                            ds.a(this.d, i, n5, false, true);
                        }
                    }
                    else {
                        ds.a(this.c, i);
                    }
                }
                else {
                    final byte[] a2 = ji.util.d.a(this.c, dx.m, -1);
                    final int[] array2 = new int[a2.length];
                    for (int min = Math.min(a2.length, array2.length), n16 = 0; n16 < min; ++n16) {
                        if (a2[n16] != 0) {
                            array2[n16] = this.ad;
                        }
                        else {
                            array2[n16] = -1;
                        }
                    }
                    ds.a(array2, 1 * dx.m, o, n5, n5, true);
                }
            }
            catch (Exception ex3) {
                if (ji.util.d.cy()) {
                    ex3.printStackTrace();
                }
            }
            ++n5;
            i = 0;
            n7 = 1;
        }
        return n2;
    }
    
    public final void g() {
        try {
            if (ji.util.e.ai()) {
                this.a = true;
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final dx dx) {
        this.h();
        this.c = null;
        this.d = null;
        this.j = null;
    }
    
    private boolean i() {
        return this.a || ji.util.d.c4();
    }
}
