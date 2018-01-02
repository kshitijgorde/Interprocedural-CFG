// 
// Decompiled by Procyon v0.5.30
// 

package ji.io;

import ji.util.m;
import java.util.Enumeration;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Vector;
import ji.graphic.b0;
import ji.sec.u;
import ji.sec.au;
import java.text.SimpleDateFormat;
import ji.awt.c;
import ji.awt.gl;
import ji.res.s;
import ji.res.aa;
import java.awt.Component;
import ji.util.e;
import ji.util.i;
import java.net.URL;
import ji.util.d;
import ji.v1event.af;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.util.GregorianCalendar;
import ji.res.ab;
import java.applet.AppletContext;
import java.applet.Applet;

public class f6
{
    private boolean a;
    private boolean b;
    private boolean c;
    private Applet d;
    private AppletContext e;
    private String f;
    private ab g;
    private q h;
    private String[] i;
    private GregorianCalendar j;
    private GregorianCalendar k;
    private String l;
    private boolean m;
    private String n;
    private String o;
    private String p;
    private FileOutputStream q;
    private PrintWriter r;
    private boolean s;
    private boolean t;
    private static Object u;
    
    public f6(final boolean a, final Applet d, final String l) {
        this.a = false;
        this.b = false;
        this.c = false;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = false;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = false;
        this.t = false;
        this.l = l;
        this.a = a;
        this.d = d;
        if (d != null) {
            this.e = d.getAppletContext();
        }
        try {
            if (this.s) {
                this.q = new FileOutputStream("c:\\viewone\\lic.log");
                this.r = new PrintWriter(this.q);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private final boolean a7() {
        return this.o != null;
    }
    
    public final boolean a() {
        return this.d != null && !this.a7();
    }
    
    public final boolean b() {
        return this.t;
    }
    
    public final String a(final Object o, final af af, final boolean b, final String o2, final boolean b2) {
        try {
            try {
                if (this.s) {
                    this.r.println("Loading license1... testName=".concat(String.valueOf(String.valueOf(o2))));
                }
                this.o = o2;
                if (this.o != null && this.d != null) {
                    if (!ji.util.d.bj(this.o)) {
                        this.o = "file:///".concat(String.valueOf(String.valueOf(this.o)));
                    }
                    if (this.o.toLowerCase().startsWith("file")) {
                        try {
                            ji.util.d.a(new URL(this.o), true, this.l);
                        }
                        catch (Exception ex2) {
                            this.o = ji.util.d.b(this.o, "///", "//");
                        }
                    }
                    this.o = ji.util.d.b(this.o, "\\", "/");
                }
                if (this.s) {
                    this.r.println("Loading license2... testName=".concat(String.valueOf(String.valueOf(o2))));
                }
                try {
                    if (b2 && this.g != null) {
                        this.g.f();
                        this.g = null;
                    }
                }
                catch (Exception ex3) {}
                if (this.s) {
                    this.r.println("Loading license3... codes=".concat(String.valueOf(String.valueOf(this.g))));
                }
                if (this.g == null) {
                    this.g = new ab(this.l, "licMessages");
                    this.f = this.a(af);
                }
                else if (!b) {
                    if (this.s) {
                        this.r.println("Loading license4.");
                    }
                    if (this.f != null) {
                        ji.util.d.br(true);
                        this.bb();
                        this.g.d();
                        this.f = this.a(af);
                    }
                }
                if (this.f == null) {
                    this.c = true;
                }
                if (this.s) {
                    this.r.println("Loading license5 loaded=".concat(String.valueOf(String.valueOf(this.c))));
                }
                if (this.c && !b) {
                    if (this.s) {
                        this.r.println("Loading license6");
                    }
                    ji.util.d.aj(this.ao());
                    ji.util.d.ak(this.ap());
                    ji.util.d.al(this.aq());
                    ji.util.d.bn(this.af());
                    ji.util.d.bp(this.ad());
                    ji.util.d.bo(this.ad() || (this.ak() && this.a()));
                    ji.util.d.bm(this.c);
                    ji.util.i.a(3, this.e());
                    ji.util.d.b1(this.f());
                    ji.util.e.k(this.i());
                    ji.util.e.l(this.i());
                    ji.util.e.m(this.ar());
                    ji.util.e.n(this.as());
                    ji.util.e.o(this.at());
                    Component component = null;
                    if (o instanceof Component) {
                        component = (Component)o;
                    }
                    if (!ji.util.d.a(this.l, o, ji.util.e.am(), ji.util.d.lg, ji.util.d.lh, ji.util.d.li, component)) {
                        if (this.s) {
                            this.r.println("Loading license7");
                        }
                        ji.util.d.d = true;
                        final String[] array = { this.a(981), this.a(982) };
                        int max = 0;
                        for (int i = 0; i < array.length; ++i) {
                            max = Math.max(array[i].length(), max);
                        }
                        for (int j = 0; j < array.length; ++j) {
                            for (int n = max - array[j].length() / 2, k = 0; k < n; ++k) {
                                array[j] = String.valueOf(String.valueOf(new StringBuffer(" ").append(array[j]).append(" ")));
                            }
                        }
                        this.f = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(array[0]))).append("\n\n").append(array[1])));
                        if (this.s) {
                            this.r.println("Loading license8 strError=".concat(String.valueOf(String.valueOf(this.f))));
                        }
                        return this.f;
                    }
                    if (this.s) {
                        this.r.println("Loading license9");
                    }
                    final boolean a = aa.a(this.at());
                    if (this.s) {
                        this.r.println("Loading license10 doVersionError=".concat(String.valueOf(String.valueOf(a))));
                    }
                    if (a) {
                        try {
                            final String[] array2 = { this.a(974), this.a(975), this.a(976), this.a(977), this.a(978) };
                            int max2 = 0;
                            for (int l = 0; l < array2.length; ++l) {
                                max2 = Math.max(array2[l].length(), max2);
                            }
                            for (int n2 = 0; n2 < array2.length; ++n2) {
                                for (int n3 = max2 - array2[n2].length() / 2, n4 = 0; n4 < n3; ++n4) {
                                    array2[n2] = String.valueOf(String.valueOf(new StringBuffer(" ").append(array2[n2]).append(" ")));
                                }
                            }
                            this.f = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(array2[0]))).append("\n\n").append(array2[1]).append("\n").append(array2[2]).append("\n\n").append(array2[3]).append("\n\n").append(array2[4])));
                        }
                        catch (Exception ex4) {}
                        ji.util.d.d = true;
                        return this.f;
                    }
                    final String a2 = aa.a("ji.res.jiv2", "ji", null, ji.util.e.u(), false, o, this.l);
                    if (a2 != null) {
                        this.f = a2;
                        return this.f;
                    }
                    if (this.s) {
                        this.r.println("Loading license12 applet=".concat(String.valueOf(String.valueOf(this.d))));
                    }
                    if (this.d != null) {
                        if (this.g("encryptedConfigParameter")) {
                            if (this.d.getParameter("encConfig") == null) {
                                return this.a(1008);
                            }
                        }
                        else {
                            final String parameter = this.d.getParameter("encConfig");
                            if (parameter != null && parameter.length() > 0) {
                                return this.a(1009);
                            }
                        }
                    }
                    if (this.s) {
                        this.r.println("Loading license13");
                    }
                    aa.a(this.l);
                    ji.util.e.p(this.ax());
                    ji.util.e.q(this.au());
                    ji.util.e.t(this.av());
                    ji.util.e.u(this.aw());
                    ji.util.d.bq(this.g());
                    ji.util.d.n(this.m());
                    ji.util.d.b6(this.n());
                    ji.util.d.b5(this.o());
                    ji.util.d.g(this.j());
                    ji.util.d.f(this.p());
                    ji.util.d.e(this.q());
                    final String t = this.t();
                    final String s = this.s();
                    final String v = this.v();
                    final String u = this.u();
                    final String w = this.w();
                    ji.res.s.g(t);
                    ji.res.s.f(s);
                    ji.res.s.h(v);
                    ji.res.s.l(u);
                    ji.util.d.a4(w);
                    ji.util.i.a(144, this.bg());
                    ji.util.e.s(this.k());
                    ji.util.e.r(this.ay());
                    if (!ji.util.d.by(s) || !ji.util.d.by(v) || !ji.util.d.by(u)) {
                        ji.res.s.a(true);
                    }
                    if (!this.a() || this.a7()) {
                        ji.util.d.br(true);
                    }
                    else if (this.ak() || this.ad()) {
                        ji.util.d.br(false);
                    }
                    else {
                        if (this.h == null) {
                            ji.util.d.br(true);
                            this.h = ji.io.q.a(o, this.l);
                        }
                        if (!ji.util.d.dx()) {
                            this.f = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(770)))).append("\n").append(ji.io.q.d())));
                            ji.io.q.e();
                            this.c = false;
                            this.t = true;
                            return this.f;
                        }
                    }
                    if (this.s) {
                        this.r.println("Loading license14 ".concat(String.valueOf(String.valueOf(this.ac()))));
                    }
                    if (this.ac()) {
                        this.f = this.a(39);
                        this.c = false;
                    }
                    else {
                        if (this.s) {
                            this.r.println("Loading license15 ".concat(String.valueOf(String.valueOf(this.a()))));
                        }
                        if (this.a()) {
                            if (!ji.util.d.a(ji.util.e.am()) || this.aj()) {
                                if (!this.af() && !this.ad() && !this.aj()) {
                                    this.f = this.a(50);
                                    this.c = false;
                                }
                                else if (this.aj()) {
                                    if (!this.d(false)) {
                                        if (!this.m) {
                                            this.f = this.a(38);
                                        }
                                        else {
                                            ji.res.s.n = false;
                                            this.f = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(597)))).append("\n \n").append(this.a(598)).append("\n \n")));
                                        }
                                        this.c = false;
                                    }
                                    else if (!this.d(true)) {
                                        this.f = String.valueOf(String.valueOf(new StringBuffer("Please contact support@daeja.com before continuing to use this software.\n \n(").append(this.a0()).append("/").append(ji.util.e.am()).append(")")));
                                        this.c = false;
                                    }
                                }
                            }
                            else if (!this.af() && !this.ae() && !this.ah()) {
                                this.f = this.a(53);
                                this.c = false;
                            }
                        }
                        else {
                            if (this.s) {
                                this.r.println(String.valueOf(String.valueOf(new StringBuffer("Loading license16 ").append(this.af()).append(", ").append(this.ae()).append(", ").append(this.ai()).append(", ").append(this.ah()))));
                            }
                            if (!this.af() && !this.ae() && !this.ai() && !this.ah() && !this.a9()) {
                                this.f = this.a(53);
                                this.c = false;
                            }
                            if (this.ae()) {
                                final boolean b3 = this.i == null;
                                this.bh();
                                ji.util.e.v(this.a6());
                                if (b3) {
                                    this.i = null;
                                }
                            }
                            if (ji.util.d.b() && this.a9()) {
                                if (!this.c(false)) {
                                    this.f = this.a(1274);
                                    if (!ji.util.d.by(this.p)) {
                                        this.f = String.valueOf(String.valueOf(this.f)).concat(String.valueOf(String.valueOf(" - ".concat(String.valueOf(String.valueOf(this.p))))));
                                    }
                                    this.c = false;
                                }
                                else if (!this.c(true)) {
                                    this.f = String.valueOf(String.valueOf(new StringBuffer("Please contact support@daeja.com before continuing to use this software.\n \n(").append(this.a0()).append("/").append(this.n).append(")")));
                                    this.c = false;
                                }
                            }
                        }
                        if (this.s) {
                            this.r.println("Loading license17 ".concat(String.valueOf(String.valueOf(this.c))));
                        }
                        if (this.c && this.af()) {
                            this.a(false, true, this.b(o), o);
                            if ((this.j != null || this.k != null) && !this.a(this.j, this.k)) {
                                if (this.k.get(1) < 1971) {
                                    this.f = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(62)))).append("\n").append(this.a(63)).append("\n").append(this.a(64))));
                                }
                                else if (!this.a(this.j)) {
                                    this.f = String.valueOf(String.valueOf(this.a(67))).concat(String.valueOf(String.valueOf(this.d(this.j))));
                                }
                                else {
                                    this.f = String.valueOf(String.valueOf(this.a(68))).concat(String.valueOf(String.valueOf(this.d(this.k))));
                                }
                                this.c = false;
                            }
                        }
                        if (this.s) {
                            this.r.println("Loading license18 ".concat(String.valueOf(String.valueOf(this.c))));
                        }
                        if (this.c && !this.h("license")) {
                            if (!this.i("license")) {
                                this.f = String.valueOf(String.valueOf(this.a(43))).concat(String.valueOf(String.valueOf(this.n(this.l("license")))));
                            }
                            else {
                                this.f = String.valueOf(String.valueOf(this.a(44))).concat(String.valueOf(String.valueOf(this.n(this.m("license")))));
                            }
                            this.c = false;
                        }
                        if (this.s) {
                            this.r.println("Loading license19 ".concat(String.valueOf(String.valueOf(this.c))));
                        }
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                this.f = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a(18)))).append("\n").append(ex.toString())));
            }
            if (this.f != null) {
                this.f = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a1()))).append(" \n").append(this.f)));
                if (ji.util.d.eg()) {
                    this.f = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.f))).append(" \n").append(ji.util.e.am())));
                }
            }
            if (this.s) {
                this.r.println("Loading license20 ".concat(String.valueOf(String.valueOf(this.f))));
            }
        }
        finally {
            if (this.r != null) {
                try {
                    this.r.close();
                    this.q.close();
                }
                catch (Exception ex5) {}
            }
        }
        return this.f;
    }
    
    private String a(final int n) {
        return ji.util.d.b(n, this.l);
    }
    
    private final String a(final af af) throws Exception {
        synchronized (f6.u) {
            String s = null;
            if (!this.a7()) {
                if (ji.util.d.c4 != null && ji.util.e.u() && !ji.util.d.by(ji.util.d.f8) && ji.util.d.f8.toLowerCase().indexOf("viewone".toLowerCase()) < 0) {
                    ji.util.d.c((byte[])null);
                }
                if (ji.util.d.c4 != null && ji.util.e.u() && !ji.util.d.by(ji.util.d.f9) && ji.util.d.f9.toLowerCase().indexOf("viewone".toLowerCase()) < 0) {
                    ji.util.d.c((byte[])null);
                }
                if (ji.util.d.c4 == null) {
                    s = this.g.a(ji.util.d.cv(this.l), ji.util.d.cw(this.l), ji.util.d.cx(this.l), true, af, null, false, 0);
                }
                else {
                    this.g.a(null, ji.util.d.c4, ji.util.d.bm(this.l));
                }
            }
            else {
                String s2 = ji.util.d.i(this.o, this.l);
                if (s2.endsWith("/") || s2.endsWith("\\")) {
                    s2 = s2.substring(0, s2.length() - 1);
                }
                final String h = ji.util.d.h(this.o, this.l);
                s = this.g.a(h, h, s2, true, af, null, false, 0);
            }
            if (s != null && s.length() > 0 && ji.util.d.et()) {
                s = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(String.valueOf(String.valueOf(s)).concat("\n \n")))).append(this.a(32)).append("\n")))))).append(this.a(33)).append("\n")))))).append(this.a(34)).append("\n")))))).append(this.a(35)).append("\n")))))).append(this.a(36)).append("\n \n")));
            }
            // monitorexit(f6.u)
            return s;
        }
    }
    
    public final boolean c() {
        return !this.c;
    }
    
    public final boolean d() {
        return !this.an() || this.ad() || ji.util.d.dx();
    }
    
    public final boolean e() {
        boolean b = this.g("lzw");
        if (!b) {
            b = this.a8();
        }
        return b;
    }
    
    private final boolean a8() {
        return this.ba().after(new gl(2004, 6, 7).a());
    }
    
    public final boolean f() {
        return this.g("modca");
    }
    
    public final boolean g() {
        return this.g("serverlogging");
    }
    
    public final boolean h() {
        return !this.e("doMatch").equals("false");
    }
    
    public final boolean i() {
        return this.g("annotate");
    }
    
    public final boolean j() {
        return true;
    }
    
    public final boolean k() {
        return this.e("burn").equals("true");
    }
    
    public final boolean l() {
        return this.e("streamer").equals("true");
    }
    
    public final boolean m() {
        return this.e("v3jp2k").equals("true");
    }
    
    public final boolean n() {
        final String e = this.e("v3java");
        return ji.util.d.by(e) || e.equals("true");
    }
    
    public final boolean o() {
        return this.e("v3dotnet").equals("true");
    }
    
    public final boolean p() {
        return this.e("checkCRC").equals("true") || this.e("checkCRC").equals("");
    }
    
    public final boolean q() {
        return this.e("v3index").equals("true");
    }
    
    private String d(final String s) {
        if (ji.util.d.by(s)) {
            return s;
        }
        return ji.util.d.b(s, "filenet", "FileNet");
    }
    
    public final String r() {
        return this.d(this.b("company", true));
    }
    
    public final String s() {
        return this.d(this.b("rebrandproduct", true));
    }
    
    public final String t() {
        return this.d(this.b("rebrandcompany", true));
    }
    
    public final String u() {
        return this.b("rebrandemail", true);
    }
    
    public final String v() {
        return this.b("rebrandweb", true);
    }
    
    public final String w() {
        return this.b("rebrandcert", true);
    }
    
    public final String x() {
        return this.b("contact", true);
    }
    
    public final String y() {
        return this.b("country", true);
    }
    
    private String e(final String s) {
        return this.b(s, false);
    }
    
    private final String a(final boolean b) {
        if (b) {
            return "Yes";
        }
        return "No";
    }
    
    private final String f(final String s) {
        return this.a(s, false);
    }
    
    private final String a(final String s, final boolean b) {
        if (this.e(s).equals("true") || b) {
            final String l = this.l(s);
            final String m = this.m(s);
            boolean b2;
            if (b) {
                b2 = this.h(s);
            }
            else {
                b2 = this.g(s);
            }
            String s2 = this.a(b2);
            if (!ji.util.d.by(l)) {
                s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(", starts ".concat(String.valueOf(String.valueOf(ji.util.d.b(this.k(l), true)))))));
            }
            if (!ji.util.d.by(m)) {
                s2 = String.valueOf(String.valueOf(s2)).concat(String.valueOf(String.valueOf(", expires ".concat(String.valueOf(String.valueOf(ji.util.d.b(this.k(m), true)))))));
            }
            return s2;
        }
        return this.a(false);
    }
    
    public final String z() {
        String s = "<UNABLE TO READ LICENSE FILE>";
        try {
            if (this.g != null) {
                s = String.valueOf(String.valueOf(new StringBuffer("License summary for ").append(this.o).append("\n\n")));
                final c c = new c("jiLicText1");
                c.a("Type", String.valueOf(String.valueOf(this.a4())).concat("\n"));
                if (this.aa() == 0) {
                    c.a("Trial days", String.valueOf(String.valueOf(this.bc())).concat("\n"));
                }
                c.a("Valid", String.valueOf(String.valueOf(this.a("license", true))).concat("\n"));
                c.a("Support/Demo License", String.valueOf(String.valueOf(this.a(this.am()))).concat("\n\n"));
                c.a("Issue Date", String.valueOf(String.valueOf(ji.util.d.b(this.k(this.e("issueDate")), true))).concat("\n"));
                c.a("Serial", String.valueOf(String.valueOf(this.a1())).concat("\n\n"));
                c.a("Module: Print Accelerator", String.valueOf(String.valueOf(this.f("printAccel"))).concat("\n"));
                c.a("Module: Annotations ", String.valueOf(String.valueOf(this.f("annotate"))).concat("\n"));
                c.a("Module: Extended Features (V3)", String.valueOf(String.valueOf(this.f("v3Features"))).concat("\n"));
                c.a("Module: Extended Pro Features (V3)", String.valueOf(String.valueOf(this.f("pro"))).concat("\n"));
                c.a("Module: OutsideIn", String.valueOf(String.valueOf(this.f("stellent"))).concat("\n"));
                c.a("Module: PDF", String.valueOf(String.valueOf(this.f("pdf"))).concat("\n"));
                c.a("Module: JPEG2000", String.valueOf(String.valueOf(this.f("v3jp2k"))).concat("\n"));
                c.a("Module: PNG", String.valueOf(String.valueOf(this.f("v3png"))).concat("\n"));
                c.a("Module: LZW", String.valueOf(String.valueOf(this.f("lzw"))).concat("\n"));
                c.a("Module: COLD", String.valueOf(String.valueOf(this.f("cold"))).concat("\n"));
                c.a("Module: DjVu", String.valueOf(String.valueOf(this.f("djvue"))).concat("\n\n"));
                c.a("System: Java", String.valueOf(String.valueOf(this.f("v3java"))).concat("\n"));
                c.a("System: DotNet", String.valueOf(String.valueOf(this.f("v3dotnet"))).concat("\n\n"));
                c.a("System: Burn", String.valueOf(String.valueOf(this.f("burn"))).concat("\n\n"));
                c.a("System: Streamer", String.valueOf(String.valueOf(this.f("streamer"))).concat("\n\n"));
                c.a("Contact", String.valueOf(String.valueOf(this.x())).concat("\n"));
                c.a("Company", String.valueOf(String.valueOf(this.r())).concat("\n"));
                c.a("Country", String.valueOf(String.valueOf(this.y())).concat("\n"));
                c.a("Reseller", String.valueOf(String.valueOf(this.e("reseller"))).concat("\n\n"));
                int max = 0;
                for (int i = 0; i < c.b(); ++i) {
                    max = Math.max(max, ((String)c.c(i)).length());
                }
                ++max;
                for (int j = 0; j < c.b(); ++j) {
                    s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.a((String)c.c(j), max)))).append(": ").append((String)c.b(j)))))));
                }
                if (this.h()) {
                    this.bh();
                    final String b = this.b(true);
                    if (b != null) {
                        s = String.valueOf(String.valueOf(s)).concat("Domain match:\n\n");
                        s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(b)).concat("\n"))));
                    }
                    else {
                        s = String.valueOf(String.valueOf(s)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(this.a("Domain match", max))).concat(": None defined\n"))));
                    }
                }
                else {
                    c.a("Domain match\n", this.a(false));
                }
            }
        }
        catch (Exception ex) {}
        return String.valueOf(String.valueOf(s)).concat("\n(c) Copyright Daeja Image Systems 1997-2003. All rights reserved\n");
    }
    
    private final String a(final String s, final int n) {
        String concat = s;
        for (int n2 = n - concat.length(), i = 0; i < n2; ++i) {
            concat = String.valueOf(String.valueOf(concat)).concat(" ");
        }
        return concat;
    }
    
    private String b(final String s, final boolean b) {
        if (this.g == null) {
            return "";
        }
        final String a = this.g.a(s);
        if (a == null) {
            return "";
        }
        if (b) {
            return a;
        }
        return a.toLowerCase();
    }
    
    public final int aa() {
        if (this.g != null) {
            return ji.util.d.c(this.e("type"), 5);
        }
        return 5;
    }
    
    public final int ab() {
        if (this.g != null) {
            return ji.util.d.c(this.e("trialType"), 1);
        }
        return 1;
    }
    
    public final boolean ac() {
        return this.aa() == 5;
    }
    
    public final boolean ad() {
        return this.aa() == 2;
    }
    
    public final boolean ae() {
        return this.aa() == 6 || this.ag();
    }
    
    public final boolean af() {
        if (jid.jdlang.toLowerCase().startsWith("lang")) {
            return true;
        }
        boolean b = this.aa() == 0;
        if (ji.util.d.c(this.a0(), 0) == 455657789) {
            b = true;
        }
        return b;
    }
    
    public final boolean ag() {
        return this.aa() == 7;
    }
    
    public final boolean ah() {
        return this.aa() == 3;
    }
    
    private final boolean a9() {
        return this.aa() == 8;
    }
    
    public final boolean ai() {
        return this.aa() == 4;
    }
    
    public final boolean aj() {
        return this.aa() == 1;
    }
    
    public final boolean ak() {
        return !this.an();
    }
    
    public final boolean al() {
        return this.e("resetTrial").equals("true");
    }
    
    public final boolean am() {
        return this.e("support").equals("true");
    }
    
    public final boolean an() {
        return true;
    }
    
    public final boolean ao() {
        return this.e("showCopy").equals("true");
    }
    
    public final boolean ap() {
        return !this.e("showLogo").equals("false");
    }
    
    public final boolean aq() {
        return this.e("showResellersLogo").equals("true");
    }
    
    public final boolean ar() {
        return this.g("printAccel");
    }
    
    public final boolean as() {
        return true;
    }
    
    public final boolean at() {
        return this.g("pro");
    }
    
    public final boolean au() {
        return this.g("pdf");
    }
    
    public final boolean av() {
        return this.g("stellent");
    }
    
    public final boolean aw() {
        return this.at() || this.g("cold");
    }
    
    public final boolean ax() {
        return this.g("djvue");
    }
    
    public final boolean ay() {
        return this.e("office").equals("true");
    }
    
    public final boolean az() {
        return this.e("promptTrialKey").equals("true");
    }
    
    public final String a0() {
        return this.e("serial");
    }
    
    public final String a1() {
        return this.e("license");
    }
    
    public final String a2() {
        String s;
        if (this.af() && this.k != null) {
            s = this.d(this.k);
        }
        else {
            s = this.n(this.m("license"));
        }
        if (s == null) {
            return null;
        }
        if (s.length() > 0) {
            return s;
        }
        return null;
    }
    
    public final boolean a3() {
        return this.c;
    }
    
    private final boolean g(final String s) {
        return this.h(s) && this.e(s).equals("true");
    }
    
    private final boolean h(final String s) {
        return this.i(s) && this.j(s);
    }
    
    private final boolean a(final GregorianCalendar gregorianCalendar, final GregorianCalendar gregorianCalendar2) {
        return this.a(gregorianCalendar) && this.b(gregorianCalendar2);
    }
    
    private final boolean i(final String s) {
        boolean a = true;
        final String l = this.l(s);
        if (l != null) {
            a = this.a(this.k(l));
        }
        return a;
    }
    
    private final boolean a(final GregorianCalendar gregorianCalendar) {
        boolean b = true;
        if (gregorianCalendar != null) {
            b = !gregorianCalendar.after(this.ba());
        }
        return b;
    }
    
    private final boolean j(final String s) {
        boolean b = true;
        final String m = this.m(s);
        if (m != null) {
            b = this.b(this.k(m));
        }
        return b;
    }
    
    private final boolean b(final GregorianCalendar gregorianCalendar) {
        boolean b = true;
        if (gregorianCalendar != null) {
            b = (this.ba().before(gregorianCalendar) || this.ba().equals(gregorianCalendar));
        }
        return b;
    }
    
    private final GregorianCalendar k(final String s) {
        try {
            return new GregorianCalendar(ji.util.d.c(s.substring(0, 4), 1), ji.util.d.c(s.substring(4, 6), 1) - 1, ji.util.d.c(s.substring(6, 8), 1));
        }
        catch (Exception ex) {
            return this.ba();
        }
    }
    
    private final GregorianCalendar ba() {
        final gl gl = new gl();
        return new gl().a();
    }
    
    private final GregorianCalendar b(final int n) {
        final gl gl = new gl();
        gl.a(n);
        return gl.a();
    }
    
    private final String c(final GregorianCalendar gregorianCalendar) {
        if (gregorianCalendar == null) {
            return "00000000";
        }
        return String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(ji.util.d.a(gregorianCalendar.get(1), 4)))).append(ji.util.d.a(gregorianCalendar.get(2) + 1, 2)).append(ji.util.d.a(gregorianCalendar.get(5), 2))));
    }
    
    private final String l(final String s) {
        return this.e(String.valueOf(String.valueOf(s)).concat("StartDate"));
    }
    
    private final String m(final String s) {
        return this.e(String.valueOf(String.valueOf(s)).concat("EndDate"));
    }
    
    private final String n(final String s) {
        String d = "";
        if (s != null && s.length() > 0) {
            d = this.d(this.k(s));
        }
        return d;
    }
    
    private final String d(final GregorianCalendar gregorianCalendar) {
        String value = "";
        if (gregorianCalendar != null) {
            value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(new SimpleDateFormat("MMMMMMMMM").format(gregorianCalendar.getTime())))).append(" ").append(gregorianCalendar.get(5)).append(", ").append(gregorianCalendar.get(1))));
        }
        return value;
    }
    
    public final String a4() {
        switch (this.aa()) {
            case 0: {
                return this.a(12);
            }
            case 1: {
                return this.a(17);
            }
            case 2: {
                return this.a(13);
            }
            case 3: {
                return this.a(14);
            }
            case 4: {
                return this.a(15);
            }
            case 5: {
                return "";
            }
            case 6: {
                return this.a(16);
            }
            case 7: {
                return "CD";
            }
            case 8: {
                return "Servlet";
            }
            default: {
                return "";
            }
        }
    }
    
    public final boolean a(final Object o) {
        if (this.ag()) {
            try {
                final String c = this.c(o);
                return c == null || !ji.util.d.bc(this.a1()).toLowerCase().equals(ji.util.d.bc(c.toLowerCase()));
            }
            catch (Exception ex) {
                ex.printStackTrace();
                return true;
            }
        }
        return this.af() && this.b;
    }
    
    private final void bb() {
        try {
            if (this.h != null) {
                ji.io.q.c();
                this.h = null;
            }
        }
        catch (Exception ex) {}
    }
    
    private final int bc() {
        return ji.util.d.c(this.e("firstTrialDays"), 14);
    }
    
    public final boolean b(final Object o) {
        boolean b = false;
        try {
            if (this.af()) {
                final String d = this.d(o);
                if (d != null) {
                    final String bf = this.bf();
                    if (bf != null && !bf.equals(d)) {
                        b = true;
                    }
                }
                else {
                    b = true;
                }
            }
        }
        catch (Exception ex) {
            return true;
        }
        return b;
    }
    
    private final String c(final Object o) throws Exception {
        try {
            return this.a5();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private final String d(final Object o) throws Exception {
        try {
            final String a5 = this.a5();
            if (a5 == null) {
                return null;
            }
            if (a5.length() > 28) {
                return a5.substring(29, a5.length());
            }
            return null;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final void a(final boolean b, final boolean b2, final boolean b3, final Object o) {
        try {
            if (ji.util.d.dx()) {
                String s = this.c(o);
                if (s != null) {
                    final String a = this.a(1, s);
                    final String e = this.e("issueDate");
                    if (a == null) {
                        s = null;
                    }
                    if (b3) {
                        s = null;
                    }
                    if (s != null) {
                        boolean b4 = true;
                        if (e != null && e.equals(a)) {
                            b4 = false;
                        }
                        if (b4) {
                            if (this.al() || b) {
                                s = null;
                            }
                            else {
                                this.b = false;
                                if (this.ab() == 2) {
                                    s = this.a(0, this.k(e), null, null);
                                }
                                else if (this.ab() == 1) {
                                    s = this.a(0, this.k(e), this.ba(), this.b(this.bc()));
                                }
                                else {
                                    s = this.a(0, this.k(e), this.k(this.l("license")), this.k(this.m("license")));
                                }
                                this.a(s, "9qs8ych", o);
                            }
                        }
                    }
                }
                if (s == null) {
                    if (this.az() && b2) {
                        this.b = true;
                    }
                    else {
                        this.b = false;
                        if (this.ab() == 2) {
                            s = this.a(0, this.k(this.e("issueDate")), null, null);
                        }
                        else if (this.ab() == 1) {
                            s = this.a(0, this.k(this.e("issueDate")), this.ba(), this.b(this.bc()));
                        }
                        else {
                            s = this.a(0, this.k(this.e("issueDate")), this.k(this.l("license")), this.k(this.m("license")));
                        }
                        this.a(s, "9qs8ych", o);
                    }
                }
                if (s != null && this.ab() != 2) {
                    this.j = this.k(this.a(2, s));
                    this.k = this.k(this.a(3, s));
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public final void a(final String s, final String s2, final Object o) {
        if (s2 != null) {
            try {
                if (ji.util.d.dx() && s2.equals("9qs8ych")) {
                    this.a(s);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private final void a(final au au, final String s) {
        try {
            if (s != null) {
                au.writeInt(s.length());
                for (int i = s.length() - 1; i >= 0; --i) {
                    final char char1 = s.charAt(i);
                    au.writeInt((int)(Math.random() * 7.941));
                    au.writeChar((char)('\u00ff' - char1));
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private final String a(final au au) {
        String concat = null;
        try {
            concat = "";
            for (int int1 = au.readInt(), i = 0; i < int1; ++i) {
                au.readInt();
                concat = String.valueOf(String.valueOf((char)('\u00ff' - au.readChar()))).concat(String.valueOf(String.valueOf(concat)));
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return concat;
    }
    
    public final void a(final String s) throws Exception {
        if (ji.util.d.dx()) {
            final au au = new au(this.be(), "rw", this.l, this.bd());
            this.a(au, s);
            au.e();
        }
    }
    
    public final String a5() throws Exception {
        String a = null;
        if (ji.util.d.dn()) {
            try {
                if (new u(this.be(), this.l).c()) {
                    final au au = new au(this.be(), "r", this.l, this.bd());
                    au.a(0L);
                    a = this.a(au);
                    au.e();
                }
            }
            catch (Exception ex) {}
        }
        return a;
    }
    
    private final boolean bd() {
        if (this.h == null) {
            this.h = ji.io.q.a((Object)null, this.l);
        }
        return this.h.s();
    }
    
    private final String be() {
        if (this.h == null) {
            this.h = ji.io.q.a((Object)null, this.l);
        }
        return this.h.k();
    }
    
    private final String a(final int n, final GregorianCalendar gregorianCalendar, final GregorianCalendar gregorianCalendar2, final GregorianCalendar gregorianCalendar3) {
        return String.valueOf(String.valueOf(new StringBuffer("").append(n).append("-").append(this.c(gregorianCalendar)).append("-").append(this.c(gregorianCalendar2)).append("-").append(this.c(gregorianCalendar3)).append("-").append(this.bf())));
    }
    
    private final String a(final int n, final String s) {
        try {
            if (s != null) {
                switch (n) {
                    case 0: {
                        return s.substring(0, 1);
                    }
                    case 1: {
                        return s.substring(2, 10);
                    }
                    case 2: {
                        return s.substring(11, 19);
                    }
                    case 3: {
                        return s.substring(20, 28);
                    }
                    case 4: {
                        return s.substring(29, s.length());
                    }
                }
            }
        }
        catch (Exception ex) {}
        return "";
    }
    
    private final String bf() {
        return "".concat(String.valueOf(String.valueOf(this.g.b())));
    }
    
    public final void b(final String s) {
        if (ji.util.d.eg() && this.aj()) {
            this.bh();
            if (this.i != null) {
                final String a = ji.util.d.a("License", "Please enter your password", "", false, false, s, 48, '*', true, false, null, false, 0);
                if (a != null && a.equals("729401")) {
                    ji.util.d.a(this.b(false), (af)null, s);
                }
            }
            else {
                ji.util.d.a("No web sites found", (af)null, s);
            }
        }
    }
    
    private final String b(final boolean b) {
        Object value = null;
        if ((ji.util.d.eg() && this.aj()) || b) {
            this.bh();
            if (this.i != null) {
                value = "";
                for (int i = 0; i < this.i.length; ++i) {
                    value = String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(value))).append(this.i[i]).append("\n")));
                }
            }
        }
        return (String)value;
    }
    
    private final boolean c(final boolean b) {
        if (!this.a9()) {
            return false;
        }
        final Vector<String> vector = new Vector<String>();
        try {
            final InetAddress localHost = InetAddress.getLocalHost();
            vector.addElement(localHost.getHostName().toLowerCase());
            if (!ji.util.d.a(1, 4, this.l)) {
                vector.addElement(localHost.getHostAddress());
            }
            else {
                this.a(vector);
            }
        }
        catch (IOException ex) {
            ji.util.d.a(ex);
        }
        if (b) {
            final Enumeration<String> elements = vector.elements();
            while (elements.hasMoreElements()) {
                final String n = elements.nextElement();
                this.o(n);
                if (this.m) {
                    this.n = n;
                    return false;
                }
            }
        }
        if (!this.h()) {
            return true;
        }
        final StringBuffer sb = new StringBuffer();
        final Enumeration<String> elements2 = vector.elements();
        this.bh();
        boolean b2 = false;
        while (elements2.hasMoreElements() && !b2) {
            final String s = elements2.nextElement();
            if (this.r(s)) {
                b2 = true;
            }
            if (!s.startsWith("127.") && !s.equalsIgnoreCase("localhost")) {
                if (sb.length() > 0) {
                    sb.append(", ");
                }
                sb.append(s);
            }
        }
        if (!b2) {
            this.p = String.valueOf(String.valueOf(new StringBuffer("[ ").append(sb.toString()).append(" ]")));
        }
        return b2;
    }
    
    private void a(final Vector vector) {
        if (!ji.util.d.a(1, 4, this.l)) {
            return;
        }
        try {
            final m m = new m();
            m.a("java.net.NetworkInterface");
            final Enumeration enumeration = (Enumeration)m.c("getNetworkInterfaces");
            if (enumeration != null) {
                while (enumeration.hasMoreElements()) {
                    final Object nextElement = enumeration.nextElement();
                    if (nextElement != null) {
                        final Enumeration enumeration2 = (Enumeration)new m(nextElement).c("getInetAddresses");
                        if (enumeration2 == null) {
                            continue;
                        }
                        while (enumeration2.hasMoreElements()) {
                            vector.addElement(enumeration2.nextElement().getHostAddress().toLowerCase());
                        }
                    }
                }
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
    }
    
    private final boolean d(final boolean b) {
        boolean r = false;
        this.m = false;
        if (this.a()) {
            final URL am = ji.util.e.am();
            String bc = null;
            if (am != null) {
                bc = ji.util.d.bc(am.toString().toLowerCase());
            }
            if (b && bc != null) {
                this.o(bc);
                if (ji.util.d.c(this.a0(), 0) == 455657789 && this.aa() != 0) {
                    this.m = true;
                }
                if (this.m) {
                    return false;
                }
            }
            if (!this.h()) {
                r = true;
            }
            else {
                this.bh();
                if (this.i != null && bc != null) {
                    try {
                        if (!r) {
                            r = this.r(bc);
                        }
                    }
                    catch (Exception ex) {}
                }
                ji.util.e.v(this.a6());
            }
        }
        return r;
    }
    
    private void o(final String s) {
        if (s.indexOf(ji.util.d.bc("webpro.wcomnet".toLowerCase())) >= 0) {
            this.m = true;
        }
        if (s.indexOf(ji.util.d.bc("docstore".toLowerCase())) >= 0) {
            this.m = true;
        }
        if (s.indexOf(ji.util.d.bc("anc.co.uk".toLowerCase())) >= 0) {
            this.m = true;
        }
        if (s.indexOf(ji.util.d.bc("194.201.50.2".toLowerCase())) >= 0) {
            this.m = true;
        }
        if (s.indexOf(ji.util.d.bc("195.205.250.179".toLowerCase())) >= 0) {
            this.m = true;
        }
        if (s.indexOf(ji.util.d.bc("perfectinfo.".toLowerCase())) >= 0) {
            this.m = true;
        }
        if (s.indexOf(ji.util.d.bc("perfectinformation.".toLowerCase())) >= 0) {
            this.m = true;
        }
        if (s.indexOf(ji.util.d.bc("165.139.160.222".toLowerCase())) >= 0) {
            this.m = true;
        }
        if (s.indexOf(ji.util.d.bc("10.1.1.252".toLowerCase())) >= 0) {
            this.m = true;
        }
        if (s.indexOf(ji.util.d.bc("aquatiff".toLowerCase())) >= 0) {
            this.m = true;
        }
        if (s.indexOf(ji.util.d.bc("aquaforest".toLowerCase())) >= 0) {
            this.m = true;
        }
        if (s.indexOf(ji.util.d.bc("tiff-tools".toLowerCase())) >= 0) {
            this.m = true;
        }
    }
    
    private final boolean bg() {
        final String r = this.r();
        return !ji.util.d.by(r) && r.toLowerCase().indexOf("ge insurance solutions") >= 0;
    }
    
    private final void bh() {
        try {
            if (this.g != null) {
                this.i = this.g.c("match");
            }
        }
        catch (Exception ex) {}
    }
    
    public final boolean a6() {
        return this.r("/fnjavav1files");
    }
    
    public final boolean c(final String s) {
        if (this.ag()) {
            try {
                return ji.util.d.bc(this.a1()).toLowerCase().equals(ji.util.d.bc(s.toLowerCase()));
            }
            catch (Exception ex) {
                return false;
            }
        }
        return this.p(s) || this.q(s);
    }
    
    public final String a(final String s, final Object o) {
        String s2 = null;
        this.a(s, "9qs8ych", o);
        this.a(true, false, false, o);
        if ((this.j != null || this.k != null) && !this.a(this.j, this.k)) {
            if (!this.a(this.j)) {
                s2 = String.valueOf(String.valueOf(this.a(67))).concat(String.valueOf(String.valueOf(this.d(this.j))));
            }
            else {
                s2 = String.valueOf(String.valueOf(this.a(68))).concat(String.valueOf(String.valueOf(this.d(this.k))));
            }
        }
        return s2;
    }
    
    public final String b(final String s, final Object o) {
        final String s2 = null;
        this.a(s, "9qs8ych", o);
        return s2;
    }
    
    private boolean p(final String s) {
        boolean b = false;
        try {
            if (s != null) {
                final char[] charArray = s.toCharArray();
                final char[] array = { charArray[6], charArray[11], charArray[8], charArray[7], charArray[0], charArray[5], charArray[1], charArray[10], charArray[13], charArray[2], charArray[12], charArray[3] };
                String concat = "";
                for (int i = 0; i < 9; ++i) {
                    concat = String.valueOf(String.valueOf(concat)).concat(String.valueOf(String.valueOf(array[i])));
                }
                long n = 0L;
                for (int j = 0; j < 10; ++j) {
                    n += array[j];
                }
                String s2 = "".concat(String.valueOf(String.valueOf(n)));
                if (s2.length() > 2) {
                    s2 = s2.substring(s2.length() - 2, s2.length());
                }
                if (s2.length() < 2) {
                    final String concat2 = "00".concat(String.valueOf(String.valueOf(s2)));
                    s2 = concat2.substring(concat2.length() - 2, concat2.length());
                }
                final char[] charArray2 = s2.toCharArray();
                if (charArray2[0] == array[10] && charArray2[1] == array[11]) {
                    b = true;
                }
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    private final boolean q(final String s) {
        boolean b = false;
        try {
            final String upperCase = s.toUpperCase();
            if (upperCase.length() == 28 && ji.util.d.d(String.valueOf(String.valueOf(upperCase.substring(0, 2))).concat(String.valueOf(String.valueOf(upperCase.substring(5, upperCase.length())))), 2).toLowerCase().equals(upperCase.substring(2, 4).toLowerCase())) {
                b = true;
            }
        }
        catch (Exception ex) {}
        return b;
    }
    
    private boolean r(final String s) {
        try {
            if (this.i != null) {
                for (int i = 0; i <= this.i.length; ++i) {
                    if (this.i[i] != null && this.i[i].length() > 1 && s.indexOf(ji.util.d.bc(this.i[i].toLowerCase())) >= 0) {
                        return true;
                    }
                }
                return false;
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    static {
        f6.u = new Object();
    }
}
