// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import COM.NextBus.Predictor2.rmi.VehicleInfo;
import java.text.DecimalFormat;
import java.util.Date;
import java.awt.Point;
import COM.NextBus.DBModel.AdherenceRange;
import COM.NextBus.util.e;
import java.awt.Graphics2D;
import COM.NextBus.Predictor2Comm.Location;
import COM.NextBus.HttpMapClient.ConnectionException;
import COM.NextBus.Predictor2Comm.BusPrediction;
import COM.NextBus.util.c;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.awt.Font;
import java.awt.Color;
import COM.NextBus.Predictor2Comm.BusReport;
import java.util.List;
import java.util.Map;
import java.awt.Image;
import java.text.SimpleDateFormat;

public final class aj implements f
{
    private final String a;
    private final O b;
    private final SimpleDateFormat c;
    private final String d;
    private final String e;
    private final Image f;
    private final Map g;
    private final List h;
    private boolean i;
    private BusReport j;
    private n k;
    private int l;
    private int m;
    private int n;
    private long o;
    private long p;
    private String q;
    private String r;
    private String s;
    private String t;
    private String u;
    private String v;
    private boolean w;
    private boolean x;
    private String y;
    private boolean z;
    private boolean A;
    private int B;
    private boolean C;
    private Color[] D;
    private Color E;
    private boolean F;
    private String G;
    private String H;
    private static final Map I;
    private static final Font J;
    private static final Font K;
    
    public aj(final BusReport busReport, final String a, final O b) {
        this.i = false;
        this.j = null;
        this.k = new n(this);
        this.l = 0;
        this.m = 0;
        this.B = 0;
        this.F = false;
        if (busReport == null) {
            throw new IllegalArgumentException();
        }
        this.a = a;
        this.b = b;
        this.d = busReport.c();
        this.i = busReport.A();
        this.b(busReport);
        this.e = "bus-" + this.a + "-" + this.d;
        this.f = this.b.y().b();
        aj.I.put(this.d, this);
        (this.c = new SimpleDateFormat("h:mm:ssa")).setTimeZone(this.b.q());
        this.g = Collections.synchronizedMap(new HashMap<Object, Object>());
        this.h = Collections.synchronizedList(new ArrayList<Object>());
    }
    
    public final void b() {
        this.b.f.a((f)this);
        this.b.f.a(this);
    }
    
    public final void c() {
        this.b.f.b((f)this);
        this.b.f.b(this);
    }
    
    public final String d() {
        return this.d;
    }
    
    public final String e() {
        return this.s;
    }
    
    public final String k() {
        return this.q;
    }
    
    private boolean y() {
        return this.j != null && this.j.t();
    }
    
    private boolean z() {
        return this.j != null && this.j.u();
    }
    
    private t A() {
        return this.b.c.a(this.a);
    }
    
    public final void a(final String q) {
        this.q = q;
        this.r = null;
        this.x = (this.q != null);
        this.D = this.A().a(this.d, this.x, this.z || this.b.O(), this.j.t(), this.s);
        this.t();
    }
    
    public final void b(final String s) {
        this.s = s;
        if (this.s == null) {
            this.s = "";
        }
    }
    
    public final boolean l() {
        if (this.b.C().d()) {
            return false;
        }
        final Iterator<t> iterator;
        if ((iterator = this.b.c.b().iterator()).hasNext()) {
            final t t = iterator.next();
            final boolean b;
            return b = (!this.x && t.a(this.k.c(), this.k.d()));
        }
        return false;
    }
    
    public final boolean m() {
        return !this.b.C().c() && this.x && !this.w;
    }
    
    public final boolean n() {
        return !this.b.C().b() && !this.x;
    }
    
    public final boolean o() {
        final long c;
        boolean b = (c = this.C()) > 420000L;
        if (U.a(this.b.B())) {
            b = (c > 1200000L);
        }
        return (b && this.b.B().startsWith("sf-muni") && this.d.charAt(0) == '4' && this.d.length() == 3) || (b && !this.b.C().e());
    }
    
    public final boolean p() {
        final boolean b = false;
        boolean b3 = false;
        Label_0078: {
            if (this.A) {
                boolean b2 = false;
                final Iterator<t> iterator = (Iterator<t>)this.b.c.b().iterator();
                while (iterator.hasNext()) {
                    b2 |= iterator.next().d(this.s);
                }
                if (!b2) {
                    b3 = true;
                    break Label_0078;
                }
            }
            b3 = false;
        }
        final boolean b4 = b | b3 | this.m() | this.n();
        boolean b5;
        if (!this.b.o() || !this.z()) {
            b5 = false;
        }
        else {
            final BusPrediction e;
            if ((e = this.E()) != null && e.c() - COM.NextBus.util.c.b() > 1200000L) {
                b5 = true;
            }
            else {
                final Iterator<t> iterator2 = (Iterator<t>)this.b.c.b().iterator();
                while (iterator2.hasNext()) {
                    if (iterator2.next().a(this.k.c(), this.k.d())) {
                        b5 = true;
                        return !(b4 | b5 | this.l() | this.o());
                    }
                }
                b5 = false;
            }
        }
        return !(b4 | b5 | this.l() | this.o());
    }
    
    final void q() {
        this.F = true;
        if (!this.b.O() && this.h.size() < 22) {
            List<Location> a = null;
            try {
                a = (List<Location>)this.b.e.a(this.a, this.d, 22);
            }
            catch (ConnectionException ex) {}
            if (a != null) {
                this.h.clear();
                for (final Location location : a) {
                    this.h.add(0, new Q(this, location.d(), location.c()));
                }
            }
        }
    }
    
    final void r() {
        this.F = false;
    }
    
    private synchronized void b(final BusReport j) {
        this.j = j;
        if (this.l != this.k.c() || this.m != this.k.d()) {
            this.h.add(new Q(this, this.k.d(), this.k.c()));
            this.l = this.k.c();
            this.m = this.k.d();
            if (this.h.size() > 22) {
                this.h.remove(0);
            }
        }
        this.s = j.n();
        if (this.s == null) {
            this.s = "";
        }
        this.t = this.b.a(this.a, this.s);
        this.u = this.b.b(this.a, this.s);
        if (this.t == null || this.t.equals("")) {
            this.v = "?";
        }
        else {
            this.v = this.t;
        }
        this.q = j.m();
        this.r = j.B();
        this.o = j.e();
        this.p = j.f();
        this.B = j.p();
        this.i = j.A();
        this.n = j.j();
        if (this.n < 0) {
            this.n = -1;
        }
        this.C = (j.i() < 1.0);
        if (this.C) {
            this.n = -1;
        }
        this.x = (this.q != null && !this.q.equals("") && !this.q.equals("99") && !this.q.endsWith("-99"));
        this.y = j.o();
        this.z = (this.y != null && this.y.length() > 0);
        this.A = (!this.s.equals("") && !this.s.equals("?"));
        this.w = ((this.z || this.b.O()) && this.A);
        Location b = null;
        final G c;
        if (this.w && this.z && (c = this.A().c(this.s)) != null) {
            final double a;
            if ((a = c.a(this.y, this.B)) != -1.0 && !this.z()) {
                this.n = (int)a;
            }
            b = c.b(this.y, this.B);
        }
        this.k.a(j.d(), b);
        final String v = j.v();
        final aj aj;
        if (this.G != null && v == null && (aj = COM.NextBus.AdminMap.aj.I.get(this.G)) != null) {
            aj.H = null;
        }
        if (v != null) {
            this.G = v;
            this.H = null;
            final aj aj2;
            if ((aj2 = COM.NextBus.AdminMap.aj.I.get(v)) != null) {
                aj2.H = this.d;
                aj2.G = null;
            }
        }
        if (this.G != null) {
            this.a(this.d, v);
        }
        if (this.H != null) {
            this.a(this.H, this.d);
        }
        this.D = this.A().a(this.d, this.x, this.w, this.j.t(), this.s);
        this.t();
    }
    
    private void a(final String s, final String s2) {
        if (s == null || s2 == null) {
            return;
        }
        final aj aj = COM.NextBus.AdminMap.aj.I.get(s);
        final aj aj2 = COM.NextBus.AdminMap.aj.I.get(s2);
        if (aj == null || aj2 == null) {
            return;
        }
        if (aj2.n < 0) {
            return;
        }
        if (aj.H != null && aj2.H != null) {
            return;
        }
        aj.n = aj2.n;
        final Location a2;
        final double a = (a2 = aj2.k.a()).a();
        final double b = a2.b();
        final Location b2;
        final double a3 = (b2 = aj2.k.b()).a();
        final double b3 = b2.b();
        final double d = this.b.f.d(20);
        final double e = this.b.f.e(20);
        final double n = a(a(a(a(a(a(a(a((this = this).n, 0), 45), 90), 135), 180), 225), 270), 315) * 3.141592653589793 / 180.0;
        final double n2 = -d * Math.cos(n);
        final double n3 = -e * Math.sin(n);
        aj.k.a(new Location(a + n2, b + n3), new Location(a3 + n2, b3 + n3));
    }
    
    public static void s() {
        final Iterator<aj> iterator = aj.I.values().iterator();
        while (iterator.hasNext()) {
            final aj aj;
            aj.a((aj = iterator.next()).d, aj.G);
        }
    }
    
    public final synchronized void a(BusReport busReport) {
        if (busReport.a()) {
            final int n = busReport.g() * -1;
            final String b = busReport.b();
            final String c = busReport.c();
            final double a = this.j.d().a();
            final double b2 = this.j.d().b();
            final long e = busReport.e();
            final long f = busReport.f();
            final int g = this.j.g();
            final int j = this.j.j();
            boolean t = this.j.t();
            final boolean u = this.j.u();
            final Integer w = this.j.w();
            final boolean a2 = this.j.A();
            final Integer x = this.j.x();
            final Integer y = this.j.y();
            final Integer z = this.j.z();
            final String b3 = this.j.B();
            final String c2 = this.j.C();
            final double[] d = this.j.D();
            String s = null;
            Label_0249: {
                if (n == 1003) {
                    s = null;
                }
                else {
                    if (n != 1001) {
                        if (n == 1002) {
                            s = null;
                            break Label_0249;
                        }
                        if (n == 1004) {
                            s = this.j.m();
                            t = true;
                            break Label_0249;
                        }
                        if (n == 1005) {
                            s = this.j.m();
                            t = false;
                            break Label_0249;
                        }
                    }
                    s = busReport.m();
                }
            }
            busReport = new BusReport(b, c, a, b2, e, g, j, s, null, null, -1, new BusPrediction[0], t, u, null, f, null, w, a2, x, y, z, b3, c2, d);
        }
        this.b(busReport);
        (this = this).b.f.b(this);
        this.b.f.a(this);
    }
    
    public final boolean t() {
        boolean b = false;
        int n;
        if ((n = (int)(this.C() / 60000L)) < 0) {
            n = 0;
        }
        else if (n >= this.D.length) {
            n = this.D.length - 1;
        }
        if (this.E == null || !this.E.equals(this.D[n])) {
            this.E = this.D[n];
            b = true;
        }
        return b;
    }
    
    private void B() {
        new Thread(new v(this.b.f, this.k.c(), this.k.d()), "Bullseye").start();
    }
    
    final void u() {
        this.b.f.d();
        this.b.f.a(this.k.c(), this.k.d(), true, true);
        this.B();
    }
    
    final void v() {
        this.b.f.a(MapCanvas.a - 4);
        this.B();
    }
    
    final void w() {
        this.b.f.a(this.k.c(), this.k.d(), false, true);
        this.B();
    }
    
    public final void a(final Graphics2D graphics2D, final MapCanvas mapCanvas) {
        if (!this.p()) {
            return;
        }
        Font font = aj.J;
        String s;
        if (this.b.i()) {
            s = this.s + ":" + this.d;
            final Integer f = this.F();
            if (this.b.j() && f != null) {
                s = s + " " + COM.NextBus.util.e.a(f, true);
                final AdherenceRange g = this.b.e.a(this.a).g();
                if (f > g.a() || f < -g.b()) {
                    font = aj.K;
                }
                if (f > 0) {
                    s += " early";
                }
                else if (f < 0) {
                    s += " late";
                }
            }
        }
        else if (!this.b.o()) {
            String s2;
            if (this.x && this.s != null && this.s.length() > 0) {
                if ((s2 = this.u) == null || s2.length() == 0) {
                    s2 = this.v;
                }
                if (s2.length() > this.s.length() + 13 && (this.s.length() < 5 || s2.length() > 21)) {
                    s2 = this.s;
                }
            }
            else {
                s2 = "?";
            }
            if (this.z() && this.b.C().z()) {
                s2 += " (on break)";
            }
            if (this.i) {
                s2 = "SOS:" + s2;
            }
            s = s2 + " : " + this.d;
            final Integer f2 = this.F();
            if (this.b.C().h() && this.y() && f2 != null) {
                s = s + " " + COM.NextBus.util.e.a(f2, true);
                if (this.b.C().k()) {
                    final AdherenceRange g2 = this.b.e.a(this.a).g();
                    if (f2 > g2.a() || f2 < -g2.b()) {
                        font = aj.K;
                    }
                }
                if (f2 > 0) {
                    s += " early";
                }
                else if (f2 < 0) {
                    s += " late";
                }
            }
            else if (this.b.C().i() && this.E() != null && !this.z()) {
                final Integer x = this.j.x();
                final Integer z = this.j.z();
                if (x != null) {
                    final Integer value = Math.round(x / 1000);
                    s = s + "  Hdwy " + COM.NextBus.util.e.a(x, true);
                    if (z != null) {
                        final int abs;
                        if ((abs = Math.abs(z - value)) * 1000 > 0.25 * z || abs * 1000 < -0.25 * z) {
                            font = aj.K;
                        }
                    }
                    else if (value > this.b.N() * 60 || value < this.b.M() * 60) {
                        font = aj.K;
                    }
                }
            }
        }
        else if (this.w) {
            if (this.b.g()) {
                s = this.s;
            }
            else {
                if ((s = this.u) == null || s.length() == 0) {
                    s = this.v;
                }
                if (s.length() > 20) {
                    s = this.s;
                }
                if (this.d.endsWith("H")) {
                    s += "-HC Accessible";
                }
                if (this.a.indexOf("actransit") >= 0 && (this.d.equals("2151") || this.d.equals("2152") || this.d.equals("2153") || this.d.equals("2154") || this.d.equals("2155") || this.d.equals("2156") || this.d.equals("2157") || this.d.equals("2158") || this.d.equals("2159") || this.d.equals("2160") || this.d.equals("2161") || this.d.equals("2162") || this.d.equals("2101") || this.d.equals("2102") || this.d.equals("2103") || this.d.equals("2104") || this.d.equals("2105") || this.d.equals("2106") || this.d.equals("2107") || this.d.equals("2108") || this.d.equals("2109") || this.d.equals("2110"))) {
                    s += "-Cleaner Fuels Test Vehicle";
                }
            }
            if (this.z()) {
                s += " (on break)";
            }
        }
        else {
            s = "?";
        }
        if (this.F) {
            graphics2D.setColor(Color.black);
            final int size = this.h.size();
            int n = 0;
            for (final Q q : this.h) {
                final Point a = mapCanvas.a(q.b, q.a);
                final int n2 = (++n << 2) / size + 3;
                graphics2D.drawLine(a.x - n2, a.y - n2, a.x + n2, a.y + n2);
                graphics2D.drawLine(a.x - n2, a.y + n2, a.x + n2, a.y - n2);
                graphics2D.drawLine(a.x - n2 + 1, a.y - n2, a.x + n2 + 1, a.y + n2);
                graphics2D.drawLine(a.x - n2 + 1, a.y + n2, a.x + n2 + 1, a.y - n2);
                graphics2D.drawLine(a.x - n2 - 1, a.y - n2, a.x + n2 - 1, a.y + n2);
                graphics2D.drawLine(a.x - n2 - 1, a.y + n2, a.x + n2 - 1, a.y - n2);
            }
        }
        final Point a2 = mapCanvas.a(this.k.c(), this.k.d());
        graphics2D.setFont(font);
        if (this.n >= 0) {
            final double n3;
            final double sin = Math.sin(n3 = a(a(a(a(a(a(a(a(this.n, 0), 45), 90), 135), 180), 225), 270), 315) * 3.141592653589793 * 2.0 / 360.0);
            final double n4 = -Math.cos(n3);
            final double n5 = a2.x;
            final double n6 = n4;
            final double n7 = -sin;
            final double n8 = a2.y;
            final int[] array = { (int)(-sin * 11.0 + n4 * 4.0 + n5), (int)(sin * 5.0 + n4 * 4.0 + n5), (int)(sin * 9.0 + n5), (int)(sin * 5.0 - n4 * 4.0 + n5), (int)(-sin * 11.0 - n4 * 4.0 + n5), (int)(-sin * 7.0 + n5) };
            final int[] array2 = { (int)(-n6 * 11.0 + n7 * 4.0 + n8), (int)(n6 * 5.0 + n7 * 4.0 + n8), (int)(n6 * 9.0 + n8), (int)(n6 * 5.0 - n7 * 4.0 + n8), (int)(-n6 * 11.0 - n7 * 4.0 + n8), (int)(-n6 * 7.0 + n8) };
            final int[] array3 = { (int)(-sin * 13.0 + n4 * 5.0 + n5), (int)(sin * 5.0 + n4 * 5.0 + n5), (int)(sin * 10.0 + n5), (int)(sin * 5.0 - n4 * 5.0 + n5), (int)(-sin * 13.0 - n4 * 5.0 + n5), (int)(-sin * 8.0 + n5) };
            final int[] array4 = { (int)(-n6 * 13.0 + n7 * 5.0 + n8), (int)(n6 * 5.0 + n7 * 5.0 + n8), (int)(n6 * 10.0 + n8), (int)(n6 * 5.0 - n7 * 5.0 + n8), (int)(-n6 * 13.0 - n7 * 5.0 + n8), (int)(-n6 * 8.0 + n8) };
            graphics2D.setColor(this.G());
            graphics2D.fillPolygon(array, array2, array.length);
            graphics2D.setColor(Color.white);
            graphics2D.drawPolygon(array, array2, array.length);
            graphics2D.setColor(Color.black);
            graphics2D.drawPolygon(array3, array4, array3.length);
            graphics2D.setColor(COM.NextBus.AdminMap.G.a(this.G()));
            if (this.C) {
                final int[] array5 = { (int)(sin + n6 * 2.0 + n5), (int)(sin - n6 * 2.0 + n5) };
                final int[] array6 = { (int)(n8 - sin * 2.0 + n6), (int)(n8 + sin * 2.0 + n6) };
                graphics2D.drawLine(array5[0], array6[0], array5[1], array6[1]);
            }
            else {
                graphics2D.drawLine((int)(n5 + n6 * 2.0), (int)(n8 - sin * 2.0), (int)(n5 + sin * 3.0), (int)(n8 + n6 * 3.0));
                graphics2D.drawLine((int)(n5 - n6 * 2.0), (int)(n8 + sin * 2.0), (int)(n5 + sin * 3.0), (int)(n8 + n6 * 3.0));
            }
        }
        else {
            graphics2D.setColor(Color.white);
            graphics2D.fillOval(a2.x - 6, a2.y - 6, 12, 12);
            graphics2D.setColor(this.G());
            graphics2D.fillOval(a2.x - 4, a2.y - 4, 8, 8);
            graphics2D.setColor(Color.black);
            graphics2D.drawOval(a2.x - 4, a2.y - 4, 8, 8);
            graphics2D.setColor(Color.white);
            graphics2D.fillRect(a2.x, a2.y, 1, 1);
        }
        this.b.f.a(s, a2, this.n, this.G(), this.i ? Color.red : COM.NextBus.AdminMap.G.a(this.G()), this, font);
    }
    
    private static int a(int n, final int n2) {
        if (n > 347) {
            n -= 360;
        }
        if (n > n2 - 13 && n < n2 + 13) {
            return n2;
        }
        return n;
    }
    
    private long C() {
        long n;
        if (this.b.O()) {
            n = this.b.d.e() - this.p;
        }
        else {
            n = COM.NextBus.util.c.b() - this.p;
        }
        return n;
    }
    
    private long D() {
        long n;
        if (this.b.O()) {
            n = this.b.d.e() - this.o;
        }
        else {
            n = COM.NextBus.util.c.b() - this.o;
        }
        return n;
    }
    
    private BusPrediction E() {
        if (this.j != null) {
            return this.j.r();
        }
        return null;
    }
    
    private Integer F() {
        final Integer s;
        if ((s = this.j.s()) == null) {
            return null;
        }
        return -s;
    }
    
    public final String[] f() {
        final ArrayList<String> list = new ArrayList<String>();
        final long c = this.C();
        final long d = this.D();
        final t a = this.A();
        if (this.b.C().t()) {
            list.add(COM.NextBus.AdminMap.a.b("Vehicle") + ": " + this.d);
            final String c2;
            if ((c2 = this.j.C()) != null) {
                list.add(COM.NextBus.AdminMap.a.b("Driver ID") + ": " + c2);
            }
        }
        list.add(COM.NextBus.AdminMap.a.b(this.b.k()) + ": " + this.t + (this.b.C().v() ? (" (" + this.s + ")") : ""));
        final BusPrediction[] q;
        if ((q = this.j.q()) != null && q.length > 0) {
            list.add(COM.NextBus.AdminMap.a.b("Direction") + ": " + this.b.d(this.a, q[0].b()));
        }
        if (this.b.C().u()) {
            String s;
            if (this.q == null || this.q.equals("")) {
                s = COM.NextBus.AdminMap.a.b("unknown");
            }
            else {
                s = COM.NextBus.Predictor2.a.a(this.q).a();
            }
            list.add(COM.NextBus.AdminMap.a.b(this.b.l()) + ": " + s + (this.z() ? (" (" + COM.NextBus.AdminMap.a.b("breakpointing") + ")") : ""));
            if (U.b(this.b.B())) {
                String s2;
                if (this.q == null || this.q.equals("")) {
                    s2 = COM.NextBus.AdminMap.a.b("unknown");
                }
                else {
                    try {
                        final String s3 = s;
                        final aa aa;
                        String a2;
                        if ((aa = this.g.get(s3)) != null && new Date().getTime() < aa.b + 300000L) {
                            a2 = aa.a;
                        }
                        else {
                            final String a3 = this.b.e.a(this.b.B(), s3, this.b);
                            final aa aa2;
                            (aa2 = new aa(this)).a = a3;
                            aa2.b = new Date().getTime();
                            this.g.put(s3, aa2);
                            a2 = a3;
                        }
                        s2 = a2;
                    }
                    catch (ConnectionException ex) {
                        s2 = COM.NextBus.AdminMap.a.b("unavailable");
                    }
                }
                list.add(COM.NextBus.AdminMap.a.b("Run") + ": " + s2);
            }
            if (this.r != null && !this.r.equals("")) {
                list.add(COM.NextBus.AdminMap.a.b("Trip") + ": " + this.r);
            }
        }
        final BusPrediction e = this.E();
        if (!this.b.O()) {
            if (e != null) {
                list.add(COM.NextBus.AdminMap.a.b("Next stop") + ": " + this.b.c(this.a, e.a()));
                if (!this.b.o()) {
                    final long c3 = e.c();
                    if (this.b.C().o()) {
                        list.add(COM.NextBus.AdminMap.a.b("Time at stop") + ": " + this.c.format(new Date(c3)));
                    }
                    else {
                        list.add(COM.NextBus.AdminMap.a.b("Time until stop") + ": " + COM.NextBus.util.e.a(c3 - COM.NextBus.util.c.b(), true));
                    }
                }
            }
            else {
                list.add(COM.NextBus.AdminMap.a.b("Next stop") + ": " + COM.NextBus.AdminMap.a.b("Not predicted"));
            }
        }
        if (!this.b.o()) {
            if (this.b.C().r()) {
                final Location d2 = this.j.d();
                final DecimalFormat decimalFormat = new DecimalFormat("0.00000");
                list.add("Lat/Lon of vehicle: " + decimalFormat.format(d2.a()) + ", " + decimalFormat.format(d2.b()));
            }
            final COM.NextBus.HttpMapClient.e c4;
            list.add(COM.NextBus.AdminMap.a.b("Speed") + " (" + (c4 = a.c()).o() + "): " + new DecimalFormat("0.0").format(this.j.h() * c4.p()));
            final VehicleInfo a4 = c4.a(this.d);
            final Integer w;
            if ((w = this.j.w()) != null) {
                String s4 = "Approx # Passengers: " + w;
                if (a4 != null && a4.a() != 0) {
                    s4 = s4 + " (" + w * 100 / a4.a() + "% of seated capacity)";
                }
                list.add(s4);
            }
            final Integer x = this.j.x();
            final Integer y = this.j.y();
            final Integer z = this.j.z();
            String s5 = COM.NextBus.AdminMap.a.b("Headway") + ":";
            if (!this.z()) {
                if (x != null) {
                    s5 = s5 + " " + COM.NextBus.util.e.a(x, true);
                }
                if (y != null && !y.equals(x)) {
                    s5 = s5 + " (" + COM.NextBus.util.e.a(y, true) + " " + COM.NextBus.AdminMap.a.b("Shared") + ")";
                }
                if (z != null) {
                    s5 = s5 + " (" + COM.NextBus.util.e.a(z * 1000, true) + " " + COM.NextBus.AdminMap.a.b("Scheduled") + ")";
                }
            }
            list.add(s5);
            final Integer f = this.F();
            if (this.y() && f != null) {
                String s6 = "";
                if (f > 0) {
                    s6 = "(" + COM.NextBus.AdminMap.a.b("early") + ")";
                }
                else if (f < 0) {
                    s6 = "(" + COM.NextBus.AdminMap.a.b("late") + ")";
                }
                list.add(COM.NextBus.AdminMap.a.b("Adherence") + ": " + COM.NextBus.util.e.a(f, true) + " " + s6);
            }
            if (!this.b.O()) {
                if (this.G != null) {
                    list.add("1st vehicle in two-car train: " + this.G);
                }
                if (this.H != null) {
                    list.add("2nd vehicle in two-car train: " + this.H);
                }
            }
            if (!this.d.startsWith("virtualVehicle")) {
                if (this.p != 0L) {
                    if (this.b.C().o()) {
                        list.add("Last GPS report at: " + this.c.format(new Date(this.p)));
                    }
                    else {
                        list.add(COM.NextBus.AdminMap.a.b("Time since GPS report") + ": " + COM.NextBus.util.e.a(c, true));
                    }
                }
                if (this.o != 0L && this.b.C().s()) {
                    if (this.b.C().o()) {
                        list.add(COM.NextBus.AdminMap.a.b("Last event received") + ": " + this.c.format(new Date(this.o)));
                    }
                    else {
                        list.add(COM.NextBus.AdminMap.a.b("Time since event received") + ": " + COM.NextBus.util.e.a(d, true));
                    }
                }
            }
            else if (this.o != 0L && this.b.C().s()) {
                list.add(COM.NextBus.AdminMap.a.b("Virtual vehicle assigned") + ": " + this.c.format(new Date(this.D())));
            }
            final double[] d3 = this.j.D();
            if (c4.j() && d3 != null && d3.length >= 9 && !a(d3, 3)) {
                list.add(COM.NextBus.AdminMap.a.b("Oil Pressure") + ": " + d3[6] + "psi");
                list.add(COM.NextBus.AdminMap.a.b("Coolant Temperature") + ": " + d3[7] + "F");
                list.add(COM.NextBus.AdminMap.a.b("Battery Voltage") + ": " + d3[8] + "V");
            }
            if (a.c().i()) {
                list.add(COM.NextBus.AdminMap.a.b("Silent Alarm") + ": " + (this.i ? "on" : "off"));
            }
        }
        return list.toArray(new String[list.size()]);
    }
    
    private static boolean a(final double[] array, int i) {
        if (array == null || array.length <= 3) {
            return true;
        }
        for (i = 3; i < array.length; ++i) {
            if (array[i] != 0.0) {
                return false;
            }
        }
        return true;
    }
    
    private Color G() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     0: aload_0        
        //     1: getfield        COM/NextBus/AdminMap/aj.b:LCOM/NextBus/AdminMap/O;
        //     4: invokevirtual   COM/NextBus/AdminMap/O.C:()LCOM/NextBus/AdminMap/I;
        //     7: invokevirtual   COM/NextBus/AdminMap/I.k:()Z
        //    10: ifeq            142
        //    13: aload_0        
        //    14: dup            
        //    15: astore_0       
        //    16: invokespecial   COM/NextBus/AdminMap/aj.y:()Z
        //    19: ifne            33
        //    22: new             Ljava/awt/Color;
        //    25: dup            
        //    26: getstatic       COM/NextBus/a/a.g:I
        //    29: invokespecial   java/awt/Color.<init>:(I)V
        //    32: areturn        
        //    33: aload_0        
        //    34: getfield        COM/NextBus/AdminMap/aj.b:LCOM/NextBus/AdminMap/O;
        //    37: getfield        COM/NextBus/AdminMap/O.e:LCOM/NextBus/HttpMapClient/a;
        //    40: aload_0        
        //    41: getfield        COM/NextBus/AdminMap/aj.a:Ljava/lang/String;
        //    44: invokevirtual   COM/NextBus/HttpMapClient/a.a:(Ljava/lang/String;)LCOM/NextBus/HttpMapClient/e;
        //    47: dup            
        //    48: astore_1       
        //    49: invokevirtual   COM/NextBus/HttpMapClient/e.g:()LCOM/NextBus/DBModel/AdherenceRange;
        //    52: astore_2       
        //    53: aload_0        
        //    54: invokespecial   COM/NextBus/AdminMap/aj.F:()Ljava/lang/Integer;
        //    57: dup            
        //    58: astore_3       
        //    59: ifnonnull       66
        //    62: iconst_0       
        //    63: goto            74
        //    66: aload_3        
        //    67: invokevirtual   java/lang/Integer.intValue:()I
        //    70: sipush          1000
        //    73: idiv           
        //    74: dup            
        //    75: istore_0       
        //    76: i2d            
        //    77: aload_2        
        //    78: invokevirtual   COM/NextBus/DBModel/AdherenceRange.a:()I
        //    81: sipush          1000
        //    84: idiv           
        //    85: aload_2        
        //    86: invokevirtual   COM/NextBus/DBModel/AdherenceRange.b:()I
        //    89: sipush          1000
        //    92: idiv           
        //    93: ldc2_w          0.5
        //    96: ldc2_w          2.0
        //    99: invokestatic    COM/NextBus/a/a.a:(DIIDD)Ljava/lang/String;
        //   102: dup            
        //   103: astore_1       
        //   104: ifnonnull       117
        //   107: new             Ljava/lang/NullPointerException;
        //   110: dup            
        //   111: ldc             "getAdherenceColor(): colorStr is null"
        //   113: invokespecial   java/lang/NullPointerException.<init>:(Ljava/lang/String;)V
        //   116: athrow         
        //   117: aload_1        
        //   118: iconst_1       
        //   119: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   122: dup            
        //   123: astore          7
        //   125: bipush          16
        //   127: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;I)I
        //   130: istore          8
        //   132: new             Ljava/awt/Color;
        //   135: dup            
        //   136: iload           8
        //   138: invokespecial   java/awt/Color.<init>:(I)V
        //   141: areturn        
        //   142: aload_0        
        //   143: getfield        COM/NextBus/AdminMap/aj.b:LCOM/NextBus/AdminMap/O;
        //   146: invokevirtual   COM/NextBus/AdminMap/O.C:()LCOM/NextBus/AdminMap/I;
        //   149: invokevirtual   COM/NextBus/AdminMap/I.l:()Z
        //   152: ifeq            406
        //   155: aload_0        
        //   156: astore_0       
        //   157: getstatic       COM/NextBus/a/a.h:I
        //   160: istore_1       
        //   161: aload_0        
        //   162: invokespecial   COM/NextBus/AdminMap/aj.E:()LCOM/NextBus/Predictor2Comm/BusPrediction;
        //   165: ifnonnull       179
        //   168: new             Ljava/awt/Color;
        //   171: dup            
        //   172: getstatic       COM/NextBus/a/a.g:I
        //   175: invokespecial   java/awt/Color.<init>:(I)V
        //   178: areturn        
        //   179: aload_0        
        //   180: getfield        COM/NextBus/AdminMap/aj.j:LCOM/NextBus/Predictor2Comm/BusReport;
        //   183: invokevirtual   COM/NextBus/Predictor2Comm/BusReport.x:()Ljava/lang/Integer;
        //   186: astore_2       
        //   187: aload_0        
        //   188: getfield        COM/NextBus/AdminMap/aj.j:LCOM/NextBus/Predictor2Comm/BusReport;
        //   191: invokevirtual   COM/NextBus/Predictor2Comm/BusReport.z:()Ljava/lang/Integer;
        //   194: astore_3       
        //   195: aload_2        
        //   196: ifnonnull       210
        //   199: new             Ljava/awt/Color;
        //   202: dup            
        //   203: getstatic       COM/NextBus/a/a.g:I
        //   206: invokespecial   java/awt/Color.<init>:(I)V
        //   209: areturn        
        //   210: aload_2        
        //   211: ifnull          397
        //   214: aload_0        
        //   215: invokespecial   COM/NextBus/AdminMap/aj.z:()Z
        //   218: ifne            397
        //   221: aload_2        
        //   222: invokevirtual   java/lang/Integer.intValue:()I
        //   225: sipush          1000
        //   228: idiv           
        //   229: i2f            
        //   230: invokestatic    java/lang/Math.round:(F)I
        //   233: istore_1       
        //   234: dconst_0       
        //   235: dstore          7
        //   237: aload_3        
        //   238: ifnull          284
        //   241: aload_3        
        //   242: invokevirtual   java/lang/Integer.intValue:()I
        //   245: iload_1        
        //   246: isub           
        //   247: i2d            
        //   248: dstore          7
        //   250: aload_3        
        //   251: invokevirtual   java/lang/Integer.doubleValue:()D
        //   254: ldc2_w          0.25
        //   257: dmul           
        //   258: invokestatic    java/lang/Math.round:(D)J
        //   261: lstore          9
        //   263: dload           7
        //   265: lload           9
        //   267: l2i            
        //   268: lload           9
        //   270: l2i            
        //   271: ldc2_w          0.3
        //   274: ldc2_w          1.5
        //   277: invokestatic    COM/NextBus/a/a.a:(DIIDD)Ljava/lang/String;
        //   280: astore_0       
        //   281: goto            369
        //   284: aload_0        
        //   285: getfield        COM/NextBus/AdminMap/aj.b:LCOM/NextBus/AdminMap/O;
        //   288: invokevirtual   COM/NextBus/AdminMap/O.N:()I
        //   291: bipush          60
        //   293: imul           
        //   294: istore          9
        //   296: aload_0        
        //   297: getfield        COM/NextBus/AdminMap/aj.b:LCOM/NextBus/AdminMap/O;
        //   300: invokevirtual   COM/NextBus/AdminMap/O.M:()I
        //   303: bipush          60
        //   305: imul           
        //   306: istore          10
        //   308: iload_1        
        //   309: iload           9
        //   311: if_icmple       324
        //   314: iload           9
        //   316: iload_1        
        //   317: isub           
        //   318: i2d            
        //   319: dstore          7
        //   321: goto            337
        //   324: iload_1        
        //   325: iload           10
        //   327: if_icmpge       337
        //   330: iload           10
        //   332: iload_1        
        //   333: isub           
        //   334: i2d            
        //   335: dstore          7
        //   337: dload           7
        //   339: dconst_0       
        //   340: dcmpl          
        //   341: ifne            355
        //   344: new             Ljava/awt/Color;
        //   347: dup            
        //   348: getstatic       COM/NextBus/a/a.f:I
        //   351: invokespecial   java/awt/Color.<init>:(I)V
        //   354: areturn        
        //   355: dload           7
        //   357: iload           10
        //   359: iload           9
        //   361: ldc2_w          0.1
        //   364: dconst_1       
        //   365: invokestatic    COM/NextBus/a/a.a:(DIIDD)Ljava/lang/String;
        //   368: astore_0       
        //   369: aload_0        
        //   370: ifnonnull       383
        //   373: new             Ljava/lang/NullPointerException;
        //   376: dup            
        //   377: ldc             "getHeadwayColor(): colorStr is null"
        //   379: invokespecial   java/lang/NullPointerException.<init>:(Ljava/lang/String;)V
        //   382: athrow         
        //   383: aload_0        
        //   384: iconst_1       
        //   385: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   388: dup            
        //   389: astore          9
        //   391: bipush          16
        //   393: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;I)I
        //   396: istore_1       
        //   397: new             Ljava/awt/Color;
        //   400: dup            
        //   401: iload_1        
        //   402: invokespecial   java/awt/Color.<init>:(I)V
        //   405: areturn        
        //   406: aload_0        
        //   407: getfield        COM/NextBus/AdminMap/aj.E:Ljava/awt/Color;
        //   410: areturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:757)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:655)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:532)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:499)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:141)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:130)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:105)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:317)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:238)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:138)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public final Color g() {
        return COM.NextBus.AdminMap.G.a((this = this).G());
    }
    
    public final Color h() {
        return this.G();
    }
    
    public final Image j() {
        return this.f;
    }
    
    public final String i() {
        return this.s + "-Vehicle";
    }
    
    public final Point a(final MapCanvas mapCanvas) {
        if (this.p()) {
            return mapCanvas.a(this.k.c(), this.k.d());
        }
        return null;
    }
    
    public final String a() {
        return this.e;
    }
    
    public final String x() {
        return this.e;
    }
    
    public final void a(final o o) {
        if (this.p()) {
            final Point point = new Point(this.k.c(), this.k.d());
            o.a = Math.min(o.a, point.x);
            o.c = Math.max(o.c, point.x);
            o.b = Math.min(o.b, point.y);
            o.d = Math.max(o.d, point.y);
        }
    }
    
    public final boolean equals(final Object o) {
        return o != null && o instanceof aj && this.j.equals(((aj)o).j);
    }
    
    public final int hashCode() {
        return this.d.hashCode();
    }
    
    public final String toString() {
        final StringBuffer sb;
        (sb = new StringBuffer()).append("id=").append(this.d);
        sb.append(",busReport=").append(this.j);
        return sb.toString();
    }
    
    static {
        I = Collections.synchronizedMap(new HashMap<Object, Object>());
        J = new Font("SansSerif", 1, 11);
        K = new Font("SansSerif", 1, 11);
    }
}
