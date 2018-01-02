// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.AdminMap;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.TimeZone;
import COM.NextBus.DBModel.AdherenceRange;
import java.util.Date;
import COM.NextBus.Predictor2Comm.BusReport;
import COM.NextBus.Predictor2Comm.RouteInfoBase;
import java.util.Collection;
import java.awt.event.ItemListener;
import java.awt.Font;
import java.awt.Checkbox;
import COM.NextBus.HttpMapClient.ConnectionException;
import java.util.ArrayList;
import COM.NextBus.HttpMapClient.e;
import java.awt.Component;
import java.awt.event.ActionEvent;
import COM.NextBus.Predictor2Comm.StopPrediction;
import java.util.Iterator;
import COM.NextBus.DBModel.YardZone;
import java.net.URL;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.awt.Color;
import java.applet.AudioClip;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionListener;

public final class t implements ActionListener
{
    private final O b;
    private final String c;
    private RouteSelectorDialog d;
    private EventDialog e;
    private final Map f;
    private List g;
    boolean a;
    private final LinkedHashMap h;
    private final List i;
    private final Map j;
    private final Map k;
    private Map l;
    private AudioClip m;
    private static final Color n;
    private static final Color o;
    private static final Color p;
    private static final int q;
    private static final int r;
    private static final int s;
    private int t;
    private int u;
    private int v;
    
    public t(final O b, final String c) {
        this.d = null;
        this.e = null;
        this.f = new ConcurrentHashMap();
        this.a = false;
        this.h = new LinkedHashMap();
        this.i = new Vector();
        this.j = new ConcurrentHashMap();
        this.k = new ConcurrentHashMap();
        this.l = null;
        this.m = null;
        this.t = 1;
        this.u = 0;
        this.v = -1;
        this.b = b;
        this.c = c;
        this.m = this.m();
    }
    
    private AudioClip m() {
        URL url = null;
        try {
            final URL documentBase;
            String string;
            if ((documentBase = this.b.y().getDocumentBase()).getHost() != "") {
                string = "http://" + documentBase.getHost() + "/audio/police.au";
            }
            else {
                string = "http://www.nextbus.com/audio/police.au";
            }
            url = new URL(string);
        }
        catch (Exception ex) {
            System.out.println("Malformed url" + ex);
        }
        return this.b.y().getAudioClip(url);
    }
    
    public final boolean a(final int n, final int n2) {
        final float n3 = n / 6000000.0f;
        final float n4 = n2 / 6000000.0f;
        final List f;
        if ((f = this.b.e.a(this.c).f()) == null) {
            return false;
        }
        final Iterator<YardZone> iterator = f.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().a(n3, n4)) {
                return true;
            }
        }
        return false;
    }
    
    final void a() {
        for (final String s : this.f.keySet()) {
            ((aj)this.f.get(s)).c();
            this.f.remove(s);
        }
        final Iterator iterator2 = this.h.values().iterator();
        while (iterator2.hasNext()) {
            iterator2.next();
        }
        if (this.e != null) {
            this.e.c();
        }
    }
    
    final void a(final Map map) {
        final Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            for (final StopPrediction stopPrediction : (List)map.get(iterator.next())) {
                final G g;
                if ((g = this.h.get(stopPrediction.a())) != null) {
                    g.a(stopPrediction);
                }
            }
        }
    }
    
    public final void actionPerformed(final ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.b.b.c) {
            if (this.d == null) {
                this.d = new RouteSelectorDialog(this.b.b.g(), COM.NextBus.AdminMap.a.b(this.b.k() + "s for") + " " + this.c, this.b.f.getParent(), this.b, this);
            }
            this.d.setVisible(true);
            return;
        }
        if (actionEvent.getSource() == this.b.b.e) {
            this.b.a((aj)null);
            return;
        }
        if (actionEvent.getSource() == this.b.b.d) {
            if (this.e == null) {
                this.e = new SwingEventDialog(this.b.b.g(), "Events for " + this.c, this.b.f.getParent(), this.b, this);
            }
            this.e.setVisible(true);
        }
    }
    
    public final Color[] a(final String s, final boolean b, final boolean b2, final boolean b3, final String s2) {
        if (this.l == null) {
            this.o();
        }
        if (b3) {
            if (!b2) {
                return this.l.get("orangeKey");
            }
            final Color[] array = this.l.get(s2);
            if (this.c.indexOf("seattle") >= 0) {
                final Color[] array2 = { null };
                if (s.equals("1")) {
                    array2[0] = new Color(16711680);
                    return array2;
                }
                if (s.equals("2")) {
                    array2[0] = new Color(16750848);
                    return array2;
                }
                if (s.equals("3")) {
                    array2[0] = new Color(6684876);
                    return array2;
                }
            }
            if (array != null) {
                return array;
            }
        }
        else if (b) {
            return this.l.get("OffJobKey");
        }
        return this.l.get("NoJobKey");
    }
    
    private int n() {
        if (U.a(this.c)) {
            return 8;
        }
        return 2;
    }
    
    private void o() {
        this.l = new ConcurrentHashMap();
        this.b("NoJobKey", COM.NextBus.AdminMap.t.o);
        this.b("OffJobKey", COM.NextBus.AdminMap.t.n);
        this.b("orangeKey", COM.NextBus.AdminMap.t.p);
    }
    
    private void b(final String s, final Color color) {
        final Color[] array = new Color[12 + this.n() + 1];
        for (int i = 0; i < array.length; ++i) {
            array[i] = color;
        }
        this.l.put(s, array);
    }
    
    public final void a(final String s, final Color color) {
        if (this.l == null) {
            this.o();
        }
        final Color[] array = new Color[this.n() + 12 + 1];
        for (int i = 0; i < array.length; ++i) {
            double n = 1.0;
            double n2 = 0.0;
            if (i >= this.n()) {
                n2 = (i - this.n()) / 12.0 * 0.65;
                n = 1.0 - n2;
            }
            array[i] = new Color((int)(color.getRed() * n + COM.NextBus.AdminMap.t.q * n2), (int)(color.getGreen() * n + COM.NextBus.AdminMap.t.r * n2), (int)(color.getBlue() * n + COM.NextBus.AdminMap.t.s * n2));
        }
        this.l.put(s, array);
    }
    
    public final List b() {
        return this.g;
    }
    
    public final void a(final List g) {
        this.g = g;
    }
    
    public final e c() {
        return this.b.e.a(this.c);
    }
    
    public final List a(final String s) {
        try {
            return this.b.e.d(s);
        }
        catch (ConnectionException ex) {
            final ArrayList list;
            (list = new ArrayList()).add("<Not Available>");
            return list;
        }
    }
    
    public final List b(final String s) {
        try {
            return this.b.e.f(this.c, s);
        }
        catch (ConnectionException ex) {
            final ArrayList list;
            (list = new ArrayList()).add("<Not Available");
            return list;
        }
    }
    
    public final G c(final String s) {
        return this.h.get(s);
    }
    
    public final void d() {
        final Iterator<G> iterator = this.h.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().e();
        }
        if (this.d != null) {
            this.d.dispose();
            this.d = null;
        }
        this.h.clear();
        this.i.clear();
    }
    
    public final boolean d(final String s) {
        final Iterator<G> iterator = this.h.values().iterator();
        while (iterator.hasNext()) {
            final G g;
            if ((g = iterator.next()).g().equals(s)) {
                return g.i();
            }
        }
        return false;
    }
    
    public final void e() {
        final Iterator<G> iterator = this.h.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().h();
        }
    }
    
    public final void a(final boolean b, final o o) {
        boolean b2 = false;
        for (final G g : this.h.values()) {
            if (b || g.i()) {
                g.a(o);
                b2 = true;
            }
        }
        final Iterator<G> iterator2;
        if (!b2 && (iterator2 = this.h.values().iterator()).hasNext()) {
            iterator2.next().a(o);
        }
    }
    
    public final Iterator f() {
        return this.i.iterator();
    }
    
    public final Iterator g() {
        return this.h.values().iterator();
    }
    
    public final void h() {
        for (final G g : this.h.values()) {
            g.a(this.b.J().e().a(g.g()));
        }
        for (final Checkbox checkbox : this.i) {
            checkbox.setState(this.b.J().e().a(checkbox.getName()));
        }
    }
    
    public final void i() {
        final ArrayList<String> list = new ArrayList<String>();
        for (final String name : this.g) {
            if (this.h.get(name) == null) {
                final RouteInfoBase b = this.b.e.b(this.c, name);
                final boolean b2;
                if (b2 = (!this.b.i() && this.b.o() && (b.g() || (this.c.indexOf("sf-muni") != -1 && (name.equals("59") || name.equals("60") || name.equals("61")))))) {
                    list.add(name);
                }
                else {
                    final G g;
                    (g = new G(this.b, this.c, b, this)).a();
                    this.h.put(name, g);
                    final Checkbox checkbox = new Checkbox(b.e(), false);
                    this.i.add(checkbox);
                    checkbox.setFont(new Font("SansSerif", 0, (this.g.size() < 105) ? 10 : 9));
                    checkbox.setName(name);
                    checkbox.setLabel(b.e());
                    Color background;
                    Color a = G.a(background = new Color(b.f()));
                    if (System.getProperty("os.name").toLowerCase().startsWith("mac os x")) {
                        background = new Color(200, 200, 200);
                        a = new Color(0, 0, 0);
                    }
                    checkbox.setBackground(background);
                    checkbox.setForeground(a);
                    checkbox.addItemListener(g);
                }
            }
        }
        if (!list.isEmpty()) {
            final ArrayList g2 = new ArrayList(this.g);
            final Iterator<Object> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                g2.remove(iterator2.next());
            }
            this.g = g2;
        }
        this.b.f.b();
    }
    
    public final void e(final String s) {
        for (final String s2 : this.f.keySet()) {
            final aj aj;
            if ((aj = this.f.get(s2)).e().equals(s)) {
                aj.c();
                this.f.remove(s2);
            }
        }
    }
    
    public final void a(final List list, final List list2) {
        final Vector<BusReport> vector = new Vector<BusReport>();
        for (final BusReport busReport2 : list) {
            final BusReport busReport = busReport2;
            final String c;
            if ((c = busReport2.c()) != null) {
                final aj aj;
                if ((aj = this.f.get(c)) == null) {
                    if (!busReport2.a()) {
                        final aj aj2;
                        (aj2 = new aj(busReport2, this.c, this.b)).b();
                        this.f.put(c, aj2);
                    }
                }
                else {
                    aj.a(busReport2);
                }
            }
            if (!this.b.o()) {
                final String c2 = busReport.c();
                final BusReport busReport3;
                BusReport busReport5;
                if ((busReport3 = this.j.get(c2)) == null) {
                    final BusReport busReport4 = busReport;
                    final AdherenceRange g = this.b.e.a(this.c).g();
                    final Integer s = busReport4.s();
                    BusReport a = null;
                    if (s != null && (s > g.b() || s < -1 * g.a())) {
                        a = BusReport.a(busReport4.b(), busReport4.c(), busReport4.e(), busReport4.f(), 1006, this.b.l().toLowerCase() + "=" + busReport4.m() + ",adherence=" + s);
                    }
                    busReport5 = a;
                    this.j.put(c2, busReport);
                }
                else {
                    final BusReport busReport6 = busReport3;
                    final BusReport busReport7 = busReport;
                    final BusReport busReport8 = busReport6;
                    final AdherenceRange g2 = this.b.e.a(this.c).g();
                    final Integer s2 = busReport8.s();
                    final Integer s3 = busReport7.s();
                    int n = 0;
                    int n2 = 0;
                    if (s2 != null) {
                        if (s2 > g2.b()) {
                            n2 = this.t;
                        }
                        else if (s2 < -1 * g2.a()) {
                            n2 = this.v;
                        }
                    }
                    if (s3 != null) {
                        if (s3 > g2.b()) {
                            n = this.t;
                        }
                        else if (s3 < -1 * g2.a()) {
                            n = this.v;
                        }
                    }
                    BusReport a2 = null;
                    if (n2 != n) {
                        a2 = BusReport.a(busReport7.b(), busReport7.c(), busReport7.e(), busReport7.f(), 1006, this.b.l().toLowerCase() + "=" + busReport7.m() + ",adherence=" + s3);
                    }
                    if ((busReport5 = a2) != null) {
                        this.j.put(c2, busReport);
                    }
                }
                if (busReport5 != null) {
                    vector.add(busReport5);
                }
                final BusReport busReport9;
                if ((busReport9 = this.k.get(c2)) == null && busReport.A()) {
                    this.k.put(c2, busReport);
                }
                else {
                    if (busReport9 == null || busReport.A()) {
                        continue;
                    }
                    this.k.remove(c2);
                }
            }
        }
        if (!this.b.o()) {
            final List list3;
            final boolean a3 = (list3 = new ArrayList(this.k.keySet())).size() > 0;
            if (this.c().i()) {
                final BusSelectorDialog q;
                if ((q = this.b.Q()) != null) {
                    BusSelectorDialog.a(list3);
                }
                if (this.a != a3) {
                    this.a = a3;
                    if (q != null) {
                        q.a(this.a);
                        q.pack();
                    }
                    if (this.a) {
                        this.m.play();
                        this.b.b.e.setForeground(Color.red);
                    }
                    else {
                        this.b.b.e.setForeground(Color.black);
                    }
                }
            }
            if (this.e != null) {
                for (final BusReport busReport10 : list2) {
                    vector.add(busReport10);
                    this.b.a("Adding to eventsDialog BusReport vehicle=" + busReport10.c() + ", job=" + busReport10.m() + ", evtime=" + new Date(busReport10.e()).toString() + ", evtype=" + COM.NextBus.DBModel.a.a(-1 * busReport10.g()));
                }
                this.b.a("Adding newLogEvents.size()=" + vector.size() + " events.");
                this.e.f();
            }
        }
    }
    
    public final boolean f(final String s) {
        return this.h(s) != null;
    }
    
    public final void g(final String s) {
        final aj h;
        if ((h = this.h(s)) == null) {
            return;
        }
        h.q();
        this.b.f.b();
    }
    
    public final void j() {
        final Iterator<aj> iterator = this.f.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().r();
        }
        this.b.f.b();
    }
    
    final void k() {
        final Iterator<aj> iterator = this.f.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().t();
        }
    }
    
    public final aj h(final String s) {
        final Iterator<aj> iterator = this.f.values().iterator();
        while (iterator.hasNext()) {
            final aj aj;
            if (s.equalsIgnoreCase((aj = iterator.next()).d())) {
                return aj;
            }
        }
        return null;
    }
    
    public final String i(final String s) {
        if (s == null || s.equals("")) {
            return null;
        }
        final Iterator<aj> iterator = this.f.values().iterator();
        while (iterator.hasNext()) {
            final aj aj;
            final String k;
            if ((k = (aj = iterator.next()).k()) != null && k.endsWith(s)) {
                return aj.d();
            }
        }
        return null;
    }
    
    public final void a(final String s, final String s2) {
        final PropertiesDialog a = this.b.b.a();
        if (s != null && !s.equals("")) {
            this.c(s).a(true);
            final aj h;
            if ((h = this.h(s2)) != null && h.m()) {
                a.b(true);
                this.b.C().b(true);
            }
            if (h != null && h.o()) {
                a.d(true);
                this.b.C().d(true);
            }
            this.b.c.e();
            final Iterator<Checkbox> iterator = this.i.iterator();
            while (iterator.hasNext()) {
                final Checkbox checkbox;
                if ((checkbox = iterator.next()).getName().equals(s)) {
                    checkbox.setState(true);
                }
            }
            return;
        }
        this.b.e.a(this.c, "", true);
        this.b.c.e();
        final aj h2;
        if ((h2 = this.h(s2)) != null) {
            if (h2.n()) {
                a.a(true);
                this.b.C().a(true);
            }
            if (h2.m()) {
                a.b(true);
                this.b.C().b(true);
            }
            if (h2.l()) {
                a.c(true);
                this.b.C().c(true);
            }
            if (h2.o()) {
                a.d(true);
                this.b.C().d(true);
            }
            this.b.b();
        }
    }
    
    public final aj j(String s) {
        aj aj;
        String n = ((aj = this.h(s)) == null) ? null : aj.e();
        if (this.b.O()) {
            if (aj != null) {
                this.a(n, s);
            }
        }
        else {
            if (aj == null || n == null || n.equals("")) {
                BusReport e = null;
                try {
                    e = this.b.e.e(this.c, s);
                }
                catch (ConnectionException ex) {}
                n = e.n();
            }
            if (aj == null || !aj.p()) {
                this.a(n, s);
                aj = this.h(s);
            }
        }
        if (aj == null || !aj.p()) {
            final BusSelectorDialog q = this.b.Q();
            final Color x = this.b.x();
            if (aj == null) {
                s = COM.NextBus.AdminMap.a.b("Vehicle") + " \"" + s + "\" " + COM.NextBus.AdminMap.a.b("not found") + ".";
                s = s + " " + COM.NextBus.AdminMap.a.b("It might be that it hasn't reported in a while.");
                if (this.b.O()) {
                    s += " Also, you must be in play mode to find vehicles.";
                }
            }
            else {
                s = COM.NextBus.AdminMap.a.b("Unknown problem when trying to find vehicle.");
            }
            OKDialog.a(COM.NextBus.AdminMap.a.b("Notice"), s, q, x);
        }
        return aj;
    }
    
    public final void k(final String s) {
        final aj j;
        if ((j = this.j(s)) != null) {
            j.w();
        }
    }
    
    public final void l(final String s) {
        final aj j;
        if ((j = this.j(s)) != null) {
            j.u();
        }
    }
    
    public final void m(final String s) {
        final aj j;
        if ((j = this.j(s)) != null) {
            j.v();
        }
    }
    
    public final void a(final o o) {
        final Iterator<aj> iterator = this.f.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().a(o);
        }
    }
    
    public final String l() {
        return this.c;
    }
    
    public final String a(final long n) {
        return b(n, this.b.q());
    }
    
    private static String b(long n2, final TimeZone timeZone) {
        final DateFormat dateTimeInstance;
        (dateTimeInstance = DateFormat.getDateTimeInstance(2, 2)).setTimeZone(timeZone);
        n = (long)new Date(n2);
        String s;
        try {
            s = dateTimeInstance.format((Date)n);
        }
        catch (Throwable t) {
            s = ((Date)n).toString();
        }
        return s;
    }
    
    public static String a(long n2, final TimeZone timeZone) {
        final SimpleDateFormat simpleDateFormat;
        (simpleDateFormat = new SimpleDateFormat("EEEE, M/dd/yyyy          hh:mm:ss a z")).setTimeZone(timeZone);
        n = (long)new Date(n2);
        String s;
        try {
            s = simpleDateFormat.format((Date)n);
        }
        catch (Throwable t) {
            s = ((Date)n).toString();
        }
        return s;
    }
    
    static {
        n = new Color(COM.NextBus.a.a.h);
        o = new Color(COM.NextBus.a.a.g);
        p = new Color(COM.NextBus.a.a.h);
        q = new Color(COM.NextBus.a.a.i).getRed();
        r = new Color(COM.NextBus.a.a.i).getGreen();
        s = new Color(COM.NextBus.a.a.i).getBlue();
    }
}
