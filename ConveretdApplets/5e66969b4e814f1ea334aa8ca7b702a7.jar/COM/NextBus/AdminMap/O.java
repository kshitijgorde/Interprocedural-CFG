// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.applet.AppletContext;
import java.net.URL;
import java.awt.Container;
import java.util.Date;
import COM.NextBus.Predictor2Comm.TitleInfo;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Label;
import COM.NextBus.Applets.LabeledTextField;
import COM.NextBus.Applets.LabeledChoice;
import java.awt.Choice;
import java.awt.TextField;
import java.awt.Checkbox;
import java.awt.Component;
import java.awt.Color;
import COM.NextBus.AdminMap.Toolbar.AgencyToolbarPanel;
import java.util.Iterator;
import COM.NextBus.util.TimeZoneException;
import COM.NextBus.util.f;
import java.util.Collections;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.Hashtable;
import java.text.SimpleDateFormat;
import java.awt.Font;
import java.util.Map;
import java.io.File;
import java.util.TimeZone;
import java.util.List;
import COM.NextBus.HttpMapClient.a;
import COM.NextBus.Applets.d;

public final class O implements d
{
    private String n;
    private String o;
    boolean a;
    N b;
    af c;
    F d;
    a e;
    MapCanvas f;
    private T p;
    private L q;
    private r r;
    int g;
    public BusSelectorDialog h;
    k i;
    ac j;
    private int s;
    private int t;
    private final List u;
    private final String v;
    private final boolean w;
    private final String x;
    private final boolean y;
    private final int z;
    private final int A;
    private final boolean B;
    private final boolean C;
    private final boolean D;
    private final boolean E;
    private final boolean F;
    private final String G;
    private final String H;
    private final String I;
    private final String J;
    private final String K;
    private final TimeZone L;
    private final File M;
    private final Applet N;
    private final Map O;
    private final Map P;
    static final Font k;
    static final Font l;
    private static Font Q;
    static final Font m;
    private static final SimpleDateFormat R;
    
    public O(final Applet n, String s, final Map map, final T p4) {
        this.o = null;
        this.a = false;
        this.g = Integer.MIN_VALUE;
        this.s = Integer.MIN_VALUE;
        this.t = Integer.MIN_VALUE;
        this.O = new Hashtable();
        this.P = new Hashtable();
        this.p = p4;
        String s2;
        if ((s2 = map.get("agency")) == null || s2.length() == 0) {
            s2 = "sf-muni";
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(s2, ":");
        final ArrayList<String> list = new ArrayList<String>();
        while (stringTokenizer.hasMoreElements()) {
            list.add((String)stringTokenizer.nextElement());
        }
        this.u = Collections.unmodifiableList((List<?>)list);
        final String v;
        if ((v = map.get("agencyTitle")) != null) {
            this.v = v;
        }
        else {
            this.v = this.u.get(0);
        }
        this.N = n;
        this.n = s;
        final String s3;
        if ((s3 = map.get("useShortRouteNamesForIcons")) != null && s3.equals("true")) {
            this.C = true;
        }
        else {
            this.C = false;
        }
        this.w = !"false".equals(map.get("shouldShowPredictions"));
        final String s4;
        if ((s4 = map.get("muniCustomMap")) != null && s4.equals("true")) {
            this.E = true;
        }
        else {
            this.E = false;
        }
        final String s5;
        if ((s5 = map.get("muniCustomMapScheduleAdherence")) != null && s5.equals("true")) {
            this.F = true;
        }
        else {
            this.F = false;
        }
        final String s6;
        if ((s6 = map.get("muniCustomMapHideLabels")) != null) {
            s6.equals("true");
        }
        final String s7 = map.get("kioskMode");
        if (this.E || (s7 != null && s7.equals("true"))) {
            this.D = true;
        }
        else {
            this.D = false;
        }
        String s8;
        if ((s8 = map.get("timezone")) == null) {
            s8 = "PST8PDT";
        }
        s = null;
        try {
            s = (String)COM.NextBus.util.f.a(s8);
            if (s == null) {
                s = (String)TimeZone.getDefault();
            }
        }
        catch (TimeZoneException ex) {
            s = (String)TimeZone.getDefault();
        }
        finally {
            TimeZone.getDefault();
        }
        final O o;
        o.L = (TimeZone)s;
        boolean y = false;
        boolean b = false;
        o.K = map.get("includeroutes");
        final String s9;
        if ((s9 = map.get("localcache")) != null) {
            o.M = new File(s9);
        }
        else {
            o.M = null;
        }
        final String g;
        if ((g = map.get("alternativeNameForRoute")) == null) {
            o.G = "Route";
        }
        else {
            o.G = g;
        }
        final String h;
        if ((h = map.get("alternativeNameForJob")) == null) {
            o.H = "Job";
        }
        else {
            o.H = h;
        }
        final String i;
        if ((i = map.get("wordForArriving")) == null) {
            o.I = "Arriving";
        }
        else {
            o.I = i;
        }
        final String j;
        if ((j = map.get("wordForDeparting")) == null) {
            o.J = "Departing";
        }
        else {
            o.J = j;
        }
        if (o.o()) {
            o.z = 0;
            o.A = 30;
            o.x = null;
        }
        else {
            if ((s = map.get("extraload")) != null) {
                o.z = Integer.parseInt(s);
            }
            else {
                o.z = 0;
            }
            if ((s = map.get("interval")) != null) {
                o.A = Integer.parseInt(s);
            }
            else {
                o.A = 10;
            }
            b = ((s = map.get("replay")) != null && s.equals("true"));
            final String s10;
            if ((s10 = map.get("test")) != null) {
                s10.equals("true");
            }
            final String s11;
            if ((s11 = map.get("adherence")) != null) {
                s11.equals("true");
            }
            final String s12 = map.get("headwayMinRange");
            o.s = ((s12 == null) ? 7 : Integer.parseInt(s12));
            final String s13 = map.get("headwayMaxRange");
            o.t = ((s13 == null) ? 11 : Integer.parseInt(s13));
            o.x = map.get("login");
            final String s14;
            if ((s14 = map.get("ssl")) != null && s14.equals("true")) {
                y = true;
            }
            final String o2;
            if ((o2 = map.get("JSESSIONID")) == null) {
                o.o = "null";
            }
            else {
                o.o = o2;
            }
        }
        o.y = y;
        o.B = b;
    }
    
    public final void a() {
        this.q = new L(this);
        this.c = new af(this);
        if (this.q.a.b() || this.q.a.c()) {
            final Iterator<String> iterator = this.u.iterator();
            while (iterator.hasNext()) {
                this.e.a(iterator.next(), "", true);
            }
        }
        this.q.a.a();
        (this = this).q.a.v(true);
        final Iterator<String> iterator2 = this.u.iterator();
        while (iterator2.hasNext()) {
            final String s;
            if ((s = iterator2.next()).indexOf("chapel-hill") != -1 || s.indexOf("simi-valley") != -1) {
                this.q.a.v(false);
            }
        }
    }
    
    public final void b() {
        if (!this.o()) {
            this.r.e();
            ((AgencyToolbarPanel)this.b.f).a(COM.NextBus.AdminMap.r.b);
        }
    }
    
    public final String c() {
        return this.n;
    }
    
    public final boolean d() {
        return this.y;
    }
    
    public final int e() {
        return this.z;
    }
    
    public final int f() {
        return this.A;
    }
    
    public final boolean g() {
        return this.C;
    }
    
    public final boolean h() {
        return this.D;
    }
    
    public final boolean i() {
        return this.E;
    }
    
    public final boolean j() {
        return this.F;
    }
    
    public final String k() {
        return this.G;
    }
    
    public final String l() {
        return this.H;
    }
    
    public final String m() {
        return this.I;
    }
    
    public final String n() {
        return this.J;
    }
    
    final boolean o() {
        final boolean b;
        return b = !this.p.a().contains("AgencyViewState");
    }
    
    public final String p() {
        return this.K;
    }
    
    public final TimeZone q() {
        return this.L;
    }
    
    public final File r() {
        return this.M;
    }
    
    public final F s() {
        return this.d;
    }
    
    public final boolean t() {
        return this.w && !this.B;
    }
    
    public final String u() {
        return this.u.get(0);
    }
    
    public final String v() {
        return this.v;
    }
    
    public final List w() {
        return this.u;
    }
    
    public final Color x() {
        if (this.B) {
            return new Color(COM.NextBus.a.a.c);
        }
        return new Color(COM.NextBus.a.a.d);
    }
    
    final Applet y() {
        return this.N;
    }
    
    public final String z() {
        return this.o;
    }
    
    public final void a(final Component component) {
        component.setFont(COM.NextBus.AdminMap.O.k);
        if (component instanceof Checkbox) {
            component.setBackground(this.x());
            return;
        }
        if (component instanceof TextField) {
            component.setBackground(Color.white);
            return;
        }
        if (component instanceof Choice) {
            component.setBackground(Color.white);
            return;
        }
        if (component instanceof LabeledChoice) {
            component.setBackground(this.x());
            return;
        }
        if (component instanceof LabeledTextField) {
            component.setBackground(this.x());
            return;
        }
        if (component instanceof Label) {
            component.setBackground(this.x());
            return;
        }
        if (!(component instanceof java.awt.List)) {
            component.setBackground(new Color(COM.NextBus.a.a.d));
        }
    }
    
    final String A() {
        if (this.o()) {
            return "NextBus " + COM.NextBus.AdminMap.a.b("Map");
        }
        if (this.B) {
            return "NextBus " + COM.NextBus.AdminMap.a.b("Replay Map");
        }
        return "NextBus " + COM.NextBus.AdminMap.a.b("Agency Map");
    }
    
    final String B() {
        String s = "";
        for (final String s2 : this.u) {
            if (s.length() != 0) {
                s += ':';
            }
            s += s2;
        }
        return s;
    }
    
    public final synchronized void a(final Graphics2D graphics2D, final Dimension dimension) {
        this.i.a(graphics2D, dimension);
        this.j.a(graphics2D, dimension);
    }
    
    public final void a(final String s, final TitleInfo titleInfo) {
        this.O.put(s, titleInfo);
    }
    
    public final void b(final String s, final TitleInfo titleInfo) {
        this.P.put(s, titleInfo);
    }
    
    private TitleInfo c(final String s) {
        return this.O.get(s);
    }
    
    public final String a(final String s, final String s2) {
        final TitleInfo c;
        if ((c = this.c(s)) == null) {
            return s2;
        }
        final String a;
        if ((a = c.a(s2)) == null) {
            return s2;
        }
        return a;
    }
    
    public final String b(final String s, final String s2) {
        TitleInfo c;
        if ((c = this.P.get(s)) == null) {
            c = this.c(s);
        }
        final TitleInfo titleInfo;
        if ((titleInfo = c) == null) {
            return this.a(s, s2);
        }
        final String a;
        if ((a = titleInfo.a(s2)) == null) {
            return this.a(s, s2);
        }
        return a;
    }
    
    public final String c(final String s, final String s2) {
        return this.c(s).b(s2);
    }
    
    public final String d(final String s, final String s2) {
        return this.c(s).c(s2);
    }
    
    public final void a(final String s) {
        if ((this = this).q.a.w()) {
            synchronized (COM.NextBus.AdminMap.O.R) {
                COM.NextBus.AdminMap.O.R.format(new Date());
            }
            final String s2;
            System.out.println(s2 + ": " + s);
        }
    }
    
    public final I C() {
        return this.q.a;
    }
    
    public final void D() {
        final Iterator<String> iterator = this.u.iterator();
        while (iterator.hasNext()) {
            this.e.a(iterator.next(), "", this.C().b() || this.C().c());
        }
        if (this.b.a() != null) {
            this.b.a().a(this.q.a);
        }
        final Iterator<t> iterator2 = this.c.b().iterator();
        while (iterator2.hasNext()) {
            iterator2.next().h();
        }
    }
    
    public final void E() {
        this.f.b(this.q.d().b());
        this.f.b();
    }
    
    public final void a(final boolean b) {
        this.c.a().a(b);
    }
    
    public final boolean F() {
        return this.c.a().a();
    }
    
    public final String G() {
        return this.x;
    }
    
    public final void H() {
        this.b = new N(this.N, this);
        this.N.requestFocus();
        this.i = new k(this);
        this.j = new ac(this);
        this.f = new MapCanvas(this);
        this.b.d();
        this.N.setVisible(true);
        this.c.c();
    }
    
    public final void I() {
        this.n = null;
    }
    
    public final void b(final String s) {
        if (this.N instanceof java.applet.Applet) {
            final AppletContext appletContext = this.N.getAppletContext();
            try {
                appletContext.showDocument(new URL(s), "_blank");
            }
            catch (Throwable t) {}
        }
    }
    
    public final L J() {
        return this.q;
    }
    
    public final synchronized r K() {
        if (this.r == null) {
            (this.r = new r(this)).a();
        }
        return this.r;
    }
    
    public static Font L() {
        return O.Q;
    }
    
    public final int M() {
        return this.s;
    }
    
    public final int N() {
        return this.t;
    }
    
    public final boolean O() {
        return this.B;
    }
    
    public final int P() {
        return 0;
    }
    
    public final synchronized void a(final aj aj) {
        if (this.h == null) {
            this.h = new BusSelectorDialog(this.b.g(), COM.NextBus.AdminMap.a.b("Vehicle Inspector"), this.f.getParent(), this);
        }
        this.h.setVisible(true);
        if (aj != null) {
            this.h.a(aj);
        }
        this.h.b();
    }
    
    public final synchronized BusSelectorDialog Q() {
        return this.h;
    }
    
    static {
        k = new Font("SansSerif", 0, 12);
        l = new Font("SansSerif", 1, 12);
        O.Q = new Font("SansSerif", 1, 16);
        m = new Font("SansSerif", 1, 12);
        R = new SimpleDateFormat("HH:mm:ss.SSS");
    }
}
