// 
// Decompiled by Procyon v0.5.30
// 

package ji.font;

import ji.io.ac;
import ji.io.q;
import java.util.Enumeration;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.PixelGrabber;
import ji.image.dw;
import ji.v1event.af;
import java.awt.Toolkit;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.ByteArrayInputStream;
import ji.io.h;
import ji.util.d;
import ji.util.i;
import java.awt.Color;
import java.awt.Component;
import java.util.Hashtable;
import java.awt.Font;

public class d1
{
    boolean a;
    boolean b;
    private static long c;
    public String d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private String l;
    private int m;
    private int n;
    private boolean o;
    private boolean p;
    private boolean q;
    private boolean r;
    private int s;
    private int t;
    private int u;
    private int v;
    private boolean w;
    String x;
    String y;
    Font z;
    ct aa;
    int ab;
    private boolean ac;
    boolean ad;
    String ae;
    boolean af;
    double ag;
    Hashtable ah;
    u1 ai;
    int aj;
    byte[] ak;
    Object al;
    Object am;
    int[] an;
    int ao;
    boolean ap;
    
    public final void a(final boolean ap) {
        this.ap = ap;
    }
    
    public String toString() {
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.d))).append("(").append(this.m).append(", ").append(this.f).append(", ").append(this.x).append(")")));
    }
    
    public d1(final Component component, final String s, final String s2, final byte[] array, final double n, final String s3, final int n2, final String s4, final ct ct) {
        this(component, s, s2, array, ji.font.j.q, n, s3, n2, s4, ct);
    }
    
    public d1(final Component component, final String x, final String y, final byte[] array, final int n, double ag, final String l, final int n2, final String ae, final ct aa) {
        this.a = false;
        this.b = false;
        this.d = null;
        this.e = 0;
        this.f = 0;
        this.g = 84;
        this.h = 0;
        this.i = 0;
        this.j = 0;
        this.k = 84;
        this.l = null;
        this.m = -1;
        this.n = 0;
        this.o = false;
        this.p = false;
        this.q = false;
        this.r = false;
        this.s = 0;
        this.t = -1;
        this.u = -1;
        this.v = 0;
        this.w = false;
        this.x = null;
        this.y = null;
        this.z = null;
        this.aa = null;
        this.ab = Color.white.getRGB();
        this.ad = true;
        this.ae = null;
        this.af = false;
        this.ag = 1.0;
        this.ah = null;
        this.ai = null;
        this.aj = 0;
        this.ak = null;
        this.al = new Object();
        this.am = new Object();
        this.an = null;
        this.ao = 0;
        this.ap = false;
        this.x = x.toLowerCase();
        this.y = y;
        this.g = n;
        this.h = n2;
        this.ag = ag;
        this.ae = ae;
        if (aa != null) {
            this.ac = aa.b();
        }
        else {
            this.ac = ji.util.i.c(247);
        }
        this.a(component, aa);
        this.k = n;
        if (ag != 1.0) {
            this.g = (int)(n * this.ag);
        }
        if (aa != null) {
            this.o = aa.g;
            this.p = aa.h;
            this.q = aa.e;
            this.r = aa.d;
        }
        if (array == null || aa.b()) {
            if (ji.util.d.cy() || this.a) {
                ji.util.d.c(ae, "Create font ".concat(String.valueOf(String.valueOf(x))));
            }
            this.w = true;
            final int n3 = (int)(n / 100.0f * 80.0f);
            if (aa.b()) {
                this.z = aa.i;
            }
            else {
                this.z = this.a(component, n3);
            }
            this.aa = aa;
            this.l = this.z.getFamily();
            this.m = this.z.getSize();
            this.n = this.a(component);
            this.e = this.n;
            if (this.ac) {
                ag = this.z.getSize() / this.e;
                this.g = this.z.getSize();
            }
            this.e = Math.max(this.e, 2);
            this.f = this.b(component);
            this.i = this.e;
            this.j = this.f;
        }
        else {
            this.x = x;
            if (ji.util.d.cy() || this.a) {
                ji.util.d.c(ae, "Load font ".concat(String.valueOf(String.valueOf(x))));
            }
            this.z = this.a(component, n);
            this.a(array);
            this.l = l;
            this.m = n2;
            this.n = this.e;
        }
    }
    
    public final void a(final int s, final int t, final int u) {
        this.s = s;
        this.t = t;
        this.u = u;
    }
    
    public final boolean a() {
        return this.ac;
    }
    
    public final int b() {
        return this.aj;
    }
    
    public final void a(final String l) {
        this.l = l;
    }
    
    public final void a(final int m) {
        this.m = m;
    }
    
    public final String c() {
        return this.x;
    }
    
    public final String d() {
        return this.l;
    }
    
    public final int e() {
        return this.m;
    }
    
    public final Font f() {
        return this.z;
    }
    
    private final void a(final byte[] array) {
        if (this.a() && ji.util.i.c(253)) {
            ji.io.h.d(this.ae, "** Inconsistency. New font system loading old font.");
        }
        if (!this.b) {
            try {
                if (ji.util.d.dv() || ji.util.d.dr()) {
                    ji.util.d.d(this.ae, "Loading font ".concat(String.valueOf(String.valueOf(this.x))));
                }
                System.arraycopy(array, 0, this.ak = new byte[array.length], 0, array.length);
                final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(this.ak);
                final DataInputStream dataInputStream = new DataInputStream(byteArrayInputStream);
                dataInputStream.read(new byte[dataInputStream.readInt()]);
                final byte[] array2 = new byte[dataInputStream.readInt()];
                dataInputStream.read(array2);
                this.d = new String(array2, "UTF8");
                this.f = dataInputStream.readInt();
                this.e = dataInputStream.readInt();
                this.i = this.e;
                this.j = this.f;
                if (this.ag != 1.0) {
                    if (!this.a()) {
                        this.f *= (int)this.ag;
                        this.e *= (int)this.ag;
                    }
                    else {
                        this.f *= (int)this.ag;
                        this.e *= (int)this.ag;
                    }
                }
                final int int1 = dataInputStream.readInt();
                final int[] array3 = new int[int1];
                final int[] array4 = new int[int1];
                final int[] array5 = new int[int1];
                final int[] array6 = new int[int1];
                for (int i = 0; i < int1; ++i) {
                    array3[i] = dataInputStream.readInt();
                }
                for (int j = 0; j < int1; ++j) {
                    array4[j] = dataInputStream.readInt();
                }
                for (int k = 0; k < int1; ++k) {
                    array5[k] = dataInputStream.readInt();
                }
                for (int l = 0; l < int1; ++l) {
                    array6[l] = dataInputStream.readInt();
                }
                for (int n = 0; n < int1; ++n) {
                    final int n2 = array4[n];
                    final d2 d2 = new d2();
                    d2.m = array5[n];
                    d2.n = array6[n];
                    d2.o = array4[n];
                    d2.k = array3[n];
                    if (this.ag != 1.0) {
                        d2.i = (int)(array3[n] * this.ag);
                    }
                    else {
                        d2.i = array3[n];
                    }
                    d2.j = this.e;
                    d2.l = this.i;
                    this.a("".concat(String.valueOf(String.valueOf(array4[n]))), "".concat(String.valueOf(String.valueOf(n2))), d2);
                }
                dataInputStream.close();
                byteArrayInputStream.close();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (ji.util.d.dv() || ji.util.d.dr()) {
            ji.util.d.d(this.ae, "Loaded font ".concat(String.valueOf(String.valueOf(this.x))));
        }
    }
    
    private final Font a(final Component component, final int n) {
        Font font = component.getFont();
        Font font2 = null;
        boolean b = false;
        final String[] fontList = Toolkit.getDefaultToolkit().getFontList();
        if (fontList != null) {
            for (int i = 0; i < fontList.length; ++i) {
                if (fontList[i].toLowerCase().indexOf("mono") >= 0) {
                    if (this.a()) {
                        font = new Font(fontList[i], this.b(), n);
                    }
                    else {
                        font = new Font(fontList[i], 1, n);
                    }
                    b = true;
                    break;
                }
                if (font2 == null && fontList[i].toLowerCase().indexOf("courier") >= 0) {
                    if (this.a()) {
                        font2 = new Font(fontList[i], this.b(), n);
                    }
                    else {
                        font2 = new Font(fontList[i], 1, n);
                    }
                }
            }
        }
        if (!b && font2 != null) {
            return font2;
        }
        return font;
    }
    
    public final int g() {
        return this.f;
    }
    
    public final int h() {
        return this.n;
    }
    
    public final int a(final Component component, final String s, final boolean b, final af af) {
        return this.a(component, s, b, false, af);
    }
    
    public final int a(final Component component, final String s, final boolean b, final boolean b2, final af af) {
        try {
            d2 d2;
            if (!b) {
                d2 = this.a(component, s, this.o, this.p, b2, af);
            }
            else {
                d2 = this.a(s, component, this.o, this.p, b2, af);
            }
            return d2.i;
        }
        catch (Exception ex) {
            return 32;
        }
    }
    
    public final d2 b(final Component component, final String s, final boolean b, final boolean b2, final af af) {
        try {
            d2 d2;
            if (!b) {
                d2 = this.a(component, s, this.o, this.p, b2, af);
            }
            else {
                d2 = this.a(s, component, this.o, this.p, b2, af);
            }
            return d2;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private final String a(final String s, final boolean b, final boolean b2, final boolean b3) {
        if (this.z != null) {
            return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(s))).append("-").append(this.z.getFamily()).append("-").append(this.z.getSize()).append("-").append(this.d(this.z.isBold())).append(this.d(this.z.isItalic())).append(this.d(b)).append(this.d(b2)).append(this.d(b3)).append(this.t).append(this.u)));
        }
        return s;
    }
    
    private final String d(final boolean b) {
        if (b) {
            return "1";
        }
        return "0";
    }
    
    private final d2 a(final String s, final Component component, final boolean b, final boolean b2, final boolean b3, final af af) throws Exception {
        try {
            final String a = this.a(s, b, b2, b3);
            d2 d2 = this.a(a, true);
            if (d2 == null) {
                d2 = this.a(a, s, component, false, b, b2, b3, af);
                if (!d2.p) {
                    this.a(s, a, d2);
                }
            }
            else if (d2.b == null && this.t < 0 && (s.length() != 1 || b3 || this.ap)) {
                this.c(a);
                d2 = this.a(a, s, component, false, b, b2, b3, af);
                if (!d2.p) {
                    this.a(s, a, d2);
                }
            }
            return d2;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public final d2 a(final Component component, final String s, final boolean b, final boolean b2, final boolean b3, final boolean b4, final af af) {
        if (!b) {
            return this.a(component, s, b2, b3, b4, af);
        }
        try {
            return this.a(s, component, b2, b3, b4, af);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private final boolean q() {
        return this.ad;
    }
    
    public final void b(final boolean ad) {
        this.ad = ad;
    }
    
    private d2 a(final String s, final String s2, final Component component, final boolean b, final boolean b2, final boolean b3, final boolean b4, final af af) throws Exception {
        synchronized (this.al) {
            final d2 d2 = new d2();
            int n2;
            int n;
            if (this.a()) {
                d2.i = this.a(component, s2) + 4;
                if (b4) {
                    final d2 d3 = d2;
                    d3.i += this.f;
                }
                d2.j = this.e;
                n = (n2 = this.e);
            }
            else {
                d2.i = this.a(component, s2) + 4;
                d2.j = this.e;
                n = 80 * this.e / 100;
                n2 = this.e;
            }
            final int ao = d2.i * n2;
            boolean b5 = false;
            if (this.an == null) {
                b5 = true;
            }
            else if (this.ao < ao) {
                b5 = true;
            }
            boolean b6 = true;
            if (this.a()) {
                if (s2.length() == 1 && !b4) {
                    if (!this.ap) {
                        b6 = false;
                    }
                }
                else {
                    b6 = !this.a(s, d2);
                    if (!b6) {
                        d2.p = true;
                    }
                }
            }
            if (this.a() && this.q()) {
                d2.c = new dw(n2, d2.i, component, 512000L, this.ae, false, 23);
            }
            if (b6) {
                if (b5) {
                    this.j();
                    if (this.q() && this.a()) {
                        this.ao = d2.i;
                    }
                    else {
                        this.ao = ao;
                    }
                    this.an = new int[this.ao];
                }
                if (!b) {
                    if (this.a()) {
                        ji.font.j.a(this.ae, component, s2, d2.i, n2, n, this.z, b2, b3, this.an, this.s, this.t, this.u, d2.c, this.s, af);
                    }
                    else {
                        Image image = component.createImage(d2.i, n2);
                        if (!ji.util.d.em() && image == null && !component.isDisplayable()) {
                            image = ji.font.j.a(d2.i, n2, 1);
                        }
                        final Graphics graphics = image.getGraphics();
                        graphics.setColor(Color.white);
                        graphics.fillRect(0, 0, d2.i, n2);
                        graphics.setColor(Color.black);
                        graphics.setFont(this.z);
                        graphics.drawString(s2, 0, 80 * this.e / 100 - 2);
                        ji.util.d.a(component, image);
                        new PixelGrabber(image, 0, 0, d2.i, n2, this.an, 0, d2.i).grabPixels();
                        image.flush();
                    }
                }
                else {
                    final int rgb = Color.white.getRGB();
                    for (int i = 0; i < ao; ++i) {
                        this.an[i] = rgb;
                    }
                }
                if (d2.c != null) {
                    d2.d = d2.c.i() * d2.c.k();
                    d2.b = new byte[1];
                }
                else {
                    d2.b = ji.font.j.a(d2.i, n2, this.an, this.s);
                    d2.d = d2.b.length;
                }
            }
            if (d2.b != null) {
                d1.c += d2.b.length;
            }
            // monitorexit(this.al)
            return d2;
        }
    }
    
    private final d2 a(final Component component, final String s, final boolean b, final boolean b2, final boolean b3, final af af) {
        d2 d2 = null;
        try {
            int n = s.toCharArray()[0];
            final String a = this.a(s, b, b2, b3);
            try {
                if (n < 16) {
                    n = 32;
                }
                if (this.a()) {
                    d2 = this.a(a, false);
                }
                else {
                    d2 = this.a("".concat(String.valueOf(String.valueOf(n))), false);
                }
            }
            catch (Exception ex) {}
            if (d2 == null) {
                if (ji.util.d.cy() || this.a) {
                    ji.util.d.c(this.ae, String.valueOf(String.valueOf(new StringBuffer("Create character ").append(s).append("/").append(n))));
                }
                d2 = this.a(a, s, component, n == 32, b, b2, false, af);
                if (!d2.p) {
                    this.a(s, a, d2);
                }
            }
            else if (d2.b == null) {
                if (ji.util.d.cy() || this.a) {
                    ji.util.d.c(this.ae, String.valueOf(String.valueOf(new StringBuffer("Extract data for ").append(s).append("/").append(n).append(" from ").append(this.x))));
                }
                final byte[] array = new byte[d2.n];
                System.arraycopy(this.ak, d2.m, array, 0, d2.n);
                final int k = d2.k;
                final int l = d2.l;
                d2.b = new byte[k * l];
                d2.d = d2.b.length;
                d1.c += d2.d;
                int n2 = 0;
                int n3 = 0;
                int n4 = 0;
                for (int i = 0; i < l; ++i) {
                    final int n5 = i * k;
                    for (int j = 0; j < k; ++j) {
                        final int n6 = array[n3] >>> 7 - n2 & 0x1;
                        ++n4;
                        if (++n2 > 7) {
                            n2 = 0;
                            ++n3;
                        }
                        d2.b[j + n5] = (byte)(n6 & 0xFF);
                    }
                }
                if (this.ag != 1.0) {
                    final int m = d2.i;
                    final byte[] b4 = new byte[m * d2.j];
                    try {
                        final int[] array2 = new int[k];
                        final int n7 = m - 1;
                        for (int n8 = 0; n8 < k; ++n8) {
                            array2[n8] = Math.min((int)(n8 * this.ag), n7);
                        }
                        for (int n9 = 0; n9 < l; ++n9) {
                            final int n10 = (int)(n9 * this.ag) * m;
                            final int n11 = n9 * k;
                            for (int n12 = 0; n12 < k; ++n12) {
                                final byte[] array3 = b4;
                                final int n13 = array2[n12] + n10;
                                array3[n13] |= d2.b[n12 + n11];
                            }
                        }
                    }
                    catch (Exception ex2) {}
                    d1.c -= d2.d;
                    d2.b = b4;
                    d1.c -= d2.d;
                }
            }
        }
        catch (Exception ex3) {}
        return d2;
    }
    
    private final int a(final Component component, final String s) {
        return ji.font.j.a(component, this.z, s);
    }
    
    private final int a(final Component component) {
        try {
            final Graphics graphics = component.getGraphics();
            graphics.setFont(this.z);
            return graphics.getFontMetrics().getHeight();
        }
        catch (Exception ex) {
            return 0;
        }
    }
    
    private final int b(final Component component) {
        return ji.font.j.a(component, this.z, "W");
    }
    
    public final boolean i() {
        return this.af;
    }
    
    final void j() {
        synchronized (this.al) {
            final Object am = this.am;
            // monitorenter(am)
            try {
                try {
                    if (this.ah != null) {
                        final Enumeration<Object> keys = this.ah.keys();
                        while (keys.hasMoreElements()) {
                            final d2 d2 = this.ah.get(keys.nextElement());
                            if (d2.h && d2.b != null && this.b(d2.g)) {
                                d2.b = null;
                                d1.c -= d2.d;
                            }
                        }
                    }
                    if (this.an != null) {
                        this.an = null;
                        this.ao = 0;
                    }
                }
                catch (Exception ex) {
                    ji.util.d.a(ex);
                }
            }
            // monitorexit(am)
            finally {}
        }
        // monitorexit(this.al)
    }
    
    private final void a(final d2 d2) {
        if (d2 != null) {
            if (d2.b != null) {
                d1.c -= d2.b.length;
            }
            d2.a();
        }
    }
    
    public final void k() {
        if (this.w && !this.a() && this.ah != null) {
            this.a(false, true);
        }
    }
    
    private final void a(final Component component, final ct ct) {
        synchronized (this.am) {
            if (this.a() && this.ai == null) {
                this.ai = new u1(component, this.ae, ct);
            }
            if (this.ah == null) {
                this.ah = new Hashtable();
            }
        }
        // monitorexit(this.am)
    }
    
    private final void a(final String f, final String g, final d2 d2) {
        if (this.a() && d2.b != null && this.t < 0) {
            this.ai.a(f, g, d2);
        }
        synchronized (this.am) {
            if (this.ah.get(g) == null) {
                d2.f = f;
                d2.g = g;
                this.ah.put(g, d2);
            }
        }
        // monitorexit(this.am)
    }
    
    private final boolean b(final String s) {
        return this.a() && this.e(s);
    }
    
    private final d2 a(final String s, final boolean b) {
        synchronized (this.am) {
            final d2 d2 = this.ah.get(s);
            if (d2 != null && b && this.t < 0) {
                this.a(s, d2);
            }
            // monitorexit(this.am)
            return d2;
        }
    }
    
    private final void c(final String s) {
        synchronized (this.am) {
            this.ah.remove(s);
            this.d(s);
        }
        // monitorexit(this.am)
    }
    
    private final boolean a(final String s, final d2 d2) {
        return d2 != null && this.a() && this.ai.a(s, d2);
    }
    
    private final void d(final String s) {
        if (s != null && this.a()) {
            this.ai.b(s);
        }
    }
    
    private final boolean e(final String s) {
        return s != null && this.a() && this.ai.a(s);
    }
    
    public final void l() {
        if (this.a()) {
            this.ai.a();
        }
    }
    
    public final void c(final boolean b) {
        this.o();
        this.a(b, false);
    }
    
    private final void a(final boolean b, final boolean b2) {
        this.j();
        if (!b2) {
            if (this.p() > 0) {
                return;
            }
        }
        try {
            if (this.a()) {
                synchronized (this.am) {
                    if (this.ah != null) {
                        final Enumeration<d2> elements = this.ah.elements();
                        while (elements.hasMoreElements()) {
                            final d2 nextElement = elements.nextElement();
                            if (nextElement != null && nextElement instanceof d2) {
                                this.a(nextElement);
                            }
                        }
                        this.ah.clear();
                    }
                }
                // monitorexit(this.am)
            }
        }
        catch (Exception ex) {}
        try {
            if (this.ai != null) {
                if (b) {
                    this.ai.d();
                    this.ai = null;
                }
                else {
                    this.ai.c();
                }
            }
        }
        catch (Exception ex2) {}
    }
    
    public final void m() {
        this.af = true;
        this.z = null;
        this.ak = null;
        this.a(true, true);
    }
    
    public synchronized void n() {
        ++this.v;
    }
    
    public synchronized void o() {
        --this.v;
    }
    
    public synchronized int p() {
        return this.v;
    }
    
    static {
        d1.c = 0L;
    }
    
    class u1
    {
        String a;
        String b;
        String c;
        q d;
        Hashtable e;
        ct f;
        ac g;
        Object h;
        Component i;
        boolean j;
        String k;
        
        public u1(final d1 d1, final Component component, final String a, final ct f) {
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.g = null;
            this.h = new Object();
            this.i = null;
            this.j = false;
            this.k = null;
            try {
                this.a = a;
                this.f = f;
                this.e = new Hashtable(100);
                this.d = ji.io.q.a(component, a);
                this.k = this.d.f();
                this.b = this.d.n();
                this.c = this.d.n();
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        private final boolean a(final boolean b) {
            try {
                if (this.g != null) {
                    return true;
                }
                if (ji.io.ac.d(this.b, this.a) && ji.io.ac.d(this.c, this.a)) {
                    this.g = new ac(this.b, true, false, 0, false, this.i, true, this.a, false, false, true);
                    this.j = true;
                    this.b();
                    return true;
                }
                if (!b) {
                    if (this.c != null) {
                        ji.io.ac.c(this.c, this.a);
                    }
                    ji.io.ac.c(this.b, this.a);
                    if (this.e != null) {
                        this.e.clear();
                    }
                    this.j = true;
                    this.g = new ac(this.b, true, false, 0, false, this.i, this.a);
                    return true;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            return false;
        }
        
        private final void e() {
            try {
                synchronized (this.h) {
                    if (this.g != null) {
                        this.g.a(this.i);
                        this.g = null;
                    }
                }
                // monitorexit(this.h)
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        public final void a(final String s, final String s2, final d2 d2) {
            try {
                synchronized (this.h) {
                    if (this.a(false)) {
                        this.g.x();
                        this.e.put(s2, "".concat(String.valueOf(String.valueOf(this.g.w()))));
                        this.g.b(d2.b.length);
                        this.g.b(d2.b);
                        d2.h = true;
                        d2.d = d2.b.length;
                        this.j = true;
                    }
                }
                // monitorexit(this.h)
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        public final boolean a(final String s) {
            try {
                return this.e.get(s) != null;
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return false;
            }
        }
        
        public final boolean a(final String s, final d2 d2) {
            try {
                synchronized (this.h) {
                    if (this.a(true) && d2.b == null) {
                        final String s2 = this.e.get(s);
                        if (s2 != null) {
                            final long a = ji.util.d.a(s2, 0L);
                            if (this.g.w() > a) {
                                this.g.a(a);
                                d2.d = this.g.p();
                                d2.b = new byte[d2.d];
                                this.g.a(d2.b);
                                d1.c += d2.d;
                                // monitorexit(this.h)
                                return true;
                            }
                        }
                    }
                }
                // monitorexit(this.h)
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            return false;
        }
        
        public final void b(final String s) {
            try {
                if (s != null && this.e.get(s) != null) {
                    this.e.remove(s);
                    this.j = true;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        
        public final void a() {
            if (this.j) {
                try {
                    final ac ac = new ac(this.c, true, false, 0, false, this.i, this.a);
                    ac.b(this.e.size());
                    final Enumeration<String> keys = this.e.keys();
                    while (keys.hasMoreElements()) {
                        final String s = keys.nextElement();
                        final String s2 = this.e.get(s);
                        ac.b(s.length());
                        ac.b(s.getBytes());
                        ac.b(s2.length());
                        ac.b(s2.getBytes());
                    }
                    ac.a(this.i);
                    this.e();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            this.j = false;
        }
        
        public final void b() {
            if (this.j) {
                try {
                    this.e.clear();
                    final ac ac = new ac(this.c, false, false, 0, false, this.i, this.a);
                    for (int p = ac.p(), i = 0; i < p; ++i) {
                        final byte[] array = new byte[ac.p()];
                        ac.a(array);
                        final byte[] array2 = new byte[ac.p()];
                        ac.a(array2);
                        this.e.put(new String(array), new String(array2));
                    }
                    ac.a(this.i);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            this.j = false;
        }
        
        public final void c() {
            try {
                if (this.b != null) {
                    this.e();
                    ji.io.ac.c(this.b, this.a);
                    ji.io.ac.c(this.c, this.a);
                }
            }
            catch (Exception ex) {}
            this.i = null;
        }
        
        public final void d() {
            try {
                if (this.b != null) {
                    this.c();
                    this.b = null;
                    this.d = null;
                    this.e.clear();
                    this.e = null;
                    this.a = null;
                }
            }
            catch (Exception ex) {}
            this.i = null;
        }
    }
}
