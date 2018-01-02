import java.util.Enumeration;
import java.util.Locale;
import java.util.Vector;
import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ax extends az implements ay, e
{
    public String a;
    public long b;
    public long c;
    public long d;
    public long e;
    public String f;
    public String g;
    public int h;
    public int i;
    public ci j;
    public int k;
    public int l;
    public int m;
    public String n;
    public static long o;
    public static long p;
    public static int q;
    public static long r;
    public Hashtable s;
    public Hashtable t;
    public Hashtable u;
    public Hashtable v;
    private Vector w;
    public static Hashtable x;
    public static Hashtable y;
    public a3 z;
    private ce aa;
    public Vector ab;
    public int ac;
    public static int ad;
    public boolean ae;
    public cn af;
    public cg ag;
    private StringBuffer ah;
    private int ai;
    private int aj;
    public Vector ak;
    private int al;
    private Hashtable am;
    private boolean an;
    
    public static final ax a(final as as) throws aw {
        try {
            if (ay.a.j()) {
                ay.a.h("PushSession.getPushInstance " + ay.a.a(as));
            }
            return new ax(as, null, null);
        }
        catch (Exception ex) {
            throw az.b(ex, "Trouble with PushSession.getPushInstance");
        }
    }
    
    public ax(final as as, final a5 a5, final b b) throws aw {
        super(as, a5, b);
        this.a = null;
        this.b = -1L;
        this.c = 0L;
        this.f = "";
        this.g = null;
        this.h = 0;
        this.i = 0;
        this.l = 0;
        this.m = 0;
        this.n = null;
        this.u = new Hashtable();
        this.v = new Hashtable();
        this.w = null;
        this.ab = new Vector();
        this.ac = 0;
        this.ae = false;
        this.ag = null;
        this.ah = new StringBuffer();
        this.ai = 10;
        this.aj = 0;
        this.ak = new Vector();
        this.am = new Hashtable();
        this.an = false;
        if (as.c("USE_HEADER_BUFFER") != null) {
            aj.a = as.c("USE_HEADER_BUFFER");
        }
        this.a();
    }
    
    public void a(final a2 a2) throws aw {
        super.a(a2);
    }
    
    public void a() throws aw {
        if (ay.a.l()) {
            ay.a.j(this.as() + " PushSession.init");
        }
        this.s = new Hashtable();
        this.t = new Hashtable();
        this.h();
        this.h = this.aj().d("PUSH_CASCADE");
        this.i = this.aj().d("PUSH_CLIENT_STATUS_TIMEOUT");
        this.i *= this.aj().d("PUSH_CLIENT_STATUS_FACTOR");
        this.al = this.aj().d("MAX_SLEEP_TIME");
        if (this.al < 2000) {
            this.al = 2000;
        }
        this.a(1, "initializing session, try to connect...");
        this.f();
        ax.p = System.currentTimeMillis();
        if (this.d() != 2) {
            if (ay.a.g()) {
                ay.a.d(this.as() + " couldn't establish connection");
            }
            this.a(false);
            if (this.ak.size() > 0) {
                synchronized (this.ak) {
                    throw (aw)this.ak.elementAt(this.ak.size() - 1);
                }
            }
            throw new aw("Couldn't establish PUSH- or HTTPConnection: " + this.c().toString());
        }
        final Boolean c;
        final Boolean c2;
        if (((c = super.a.c("SUBSCRIBE_SESSION_STATUS")) != null && c) || ((c2 = super.a.c("OBSERVE_SESSION_STATUS")) != null && c2)) {
            this.a(new c1(super.a.g("URI_SESSION_STATUS"), new c0()));
            this.ax();
        }
        Integer d = super.a.d("PUSH_UNSUBSCRIBE_DELAY");
        if (d == null) {
            if (ay.a.g()) {
                ay.a.d(this.as() + " did not find valid PUSH_UNSUBSCRIBE_DELAY, using 0");
            }
            d = new Integer(0);
        }
        this.aj = d;
        if (this.aj <= -1) {
            if (ay.a.i()) {
                ay.a.g(this.as() + " no unsubscriptions performed by this session");
            }
            this.ai = 11;
        }
        else if (this.aj == 0) {
            if (ay.a.i()) {
                ay.a.g(this.as() + " immediate unsubscriptions performed by this session");
            }
            this.ai = 10;
        }
        else {
            this.ai = 12;
            final long n = System.currentTimeMillis() + this.aj;
            if (ay.a.i()) {
                ay.a.g(this.as() + " concurrent unsubscriptions performed by this session - adding job to start in " + this.aj + " ms");
            }
            this.at().a(this, System.currentTimeMillis() + this.aj);
        }
    }
    
    public final void a(final c1 c1) {
        this.a(new c1[] { c1 });
    }
    
    public final void a(final c1[] array) {
        this.an = true;
        final Vector vector = new Vector<cw>();
        synchronized (this) {
            if (array == null) {
                return;
            }
            for (int i = 0; i < array.length; ++i) {
                final cw c = this.c(array[i].a());
                if (c != null) {
                    if (ay.a.k()) {
                        ay.a.i(this.as() + " found key " + c.ab() + " in PushObjectCache with id=" + c.ac());
                    }
                    if (ay.a.i()) {
                        ay.a.g(this.as() + " adding subscription " + array[i].toString() + " to obj " + c);
                    }
                    if (c.a(array[i])) {
                        final ci j = this.j;
                        ++j.t;
                    }
                }
                else {
                    final cw cw = new cw(this, array[i].a(), 2, null, false);
                    this.t.put(cw.ab(), cw);
                    if (cw.a(array[i])) {
                        final ci k = this.j;
                        ++k.t;
                    }
                    final String m = this.m();
                    cw.j(m);
                    this.s.put(m, cw);
                    final a5 h = cw.h();
                    if (this.am.size() == 0 || this.a(h) >= 0) {
                        h.e(this.f + this.aj().g("URI_PREFIX_SUBSCRIBE"));
                        h.a("PUSH_CLIENT_ID", this.a);
                        h.a("PUSH_RID", cj.b());
                        h.a("PUSH_OBJECT_ID", m);
                        final String a = this.aj().a("PUSH_CLIENT_NAME");
                        if (a != null) {
                            h.a("PUSH_CLIENT_NAME", a);
                        }
                        if (ay.a.i()) {
                            ay.a.g(this.as() + " constructed new subscription request " + h + " for subs " + array[i]);
                        }
                        if (ay.a.k()) {
                            ay.a.i(this.as() + " put " + cw.ab() + " into PushObjectCache with id=" + cw.ac());
                        }
                        vector.addElement(cw);
                    }
                    else {
                        if (ay.a.j()) {
                            ay.a.h("initializing object with subscription-limit-error");
                        }
                        cw.a((am)null, this.c(h));
                    }
                }
                final ci l = this.j;
                ++l.r;
            }
        }
        if (this.d() == 2 && vector.size() > 0) {
            if (ay.a.i()) {
                ay.a.g(this.as() + " requesting " + vector.size() + " MDGObject(s)");
            }
            new StringBuffer().append(super.a.a("POST_URI")).append(super.a.a("AUTH_ID_NAME")).append("=").append(super.a.a("AUTH_ID_VALUE")).toString();
            new cz(this, vector, this.ao(), this.aj().a("URI_PREFIX"), this.aj().d("REQUEST_TIMEOUT"), this.ag());
            final ci j2 = this.j;
            ++j2.b;
        }
    }
    
    public final void b(final c1 c1) {
        this.b(new c1[] { c1 });
    }
    
    public final void b(final c1[] array) {
        final StringBuffer sb = new StringBuffer();
        synchronized (this) {
            for (int i = 0; i < array.length; ++i) {
                final cw c = this.c(array[i].a());
                if (c != null) {
                    if (ay.a.i()) {
                        ay.a.g(this.as() + " removing subscription " + array.toString() + " from obj " + c.toString());
                    }
                    if (c.b(array[i])) {
                        final ci j = this.j;
                        --j.t;
                    }
                    if (c.ad().size() == 0) {
                        if (ay.a.k()) {
                            ay.a.i(this.as() + " removed last subscription from obj " + c + "; removing obj from cache");
                        }
                        this.s.remove(c.ac());
                        this.t.remove(c.ab());
                        final int b = this.b(c.h());
                        if (b != Integer.MAX_VALUE && ay.a.j()) {
                            ay.a.h("decreased counter of subscription-limit to " + b + " for " + array[i].a());
                        }
                        if (i > 0) {
                            sb.append("," + c.ac());
                        }
                        else {
                            sb.append(c.ac());
                        }
                    }
                    final ci k = this.j;
                    ++k.s;
                }
                else if (ay.a.i()) {
                    ay.a.g(this.as() + " couldn't find object to unsubscribe subscription " + array[i]);
                }
            }
        }
        if (sb.length() > 0 && this.d() == 2) {
            this.a(sb.toString());
        }
        else if (sb.length() > 0 && ay.a.i()) {
            ay.a.g(this.as() + " couldn't unsubscribe id(s) " + sb.toString() + " because of status " + this.c().b());
        }
    }
    
    public final void b() {
        this.a(true);
    }
    
    public final void a(final boolean b) {
        if (this.d() != 7) {
            if (this.s != null) {
                ax.q = this.s.size();
            }
            ax.r = this.i().r;
            this.a(7, "shutting down...");
            if (this.ae) {
                --ax.ad;
            }
            if (this.af != null) {
                this.af.a();
                this.af = null;
            }
            if (b) {
                this.k();
            }
            else if (this.a != null) {
                ax.x.put(this.a, this.ag);
            }
            super.b();
        }
    }
    
    public final synchronized ce c() {
        return this.ab.elementAt(this.ab.size() - 1);
    }
    
    public final synchronized int d() {
        return this.aa.a();
    }
    
    public final Vector e() {
        final Vector<Object> vector = new Vector<Object>();
        int i = 0;
        while (i < this.ab.size()) {
            vector.addElement(this.ab.elementAt(i++));
        }
        return vector;
    }
    
    public final synchronized void a(final c c) {
        if (!this.u.containsKey(c)) {
            this.u.put(c, c);
        }
    }
    
    public final synchronized void b(final c c) {
        this.u.remove(c);
    }
    
    public void f() {
        if (ay.a.i() && !this.g()) {
            ay.a.g(this.as() + " maximum number of persistent sessions reached (" + ax.ad + "); switching to poll session");
        }
        if ((this.ac == 1 || this.ac == 2) && this.g()) {
            ++this.m;
            new cf(this.ao(), this, this.aj().d("PUSH_ESTABLISH_TIMEOUT"));
            if (this.d() == 6) {
                return;
            }
        }
        if ((this.ac == 1 || this.ac == 3) && this.d() != 2) {
            this.a(this.aa.a(), "try to establish poll-connection");
            ++this.m;
            new cl(this.ao(), this, this.aj().d("PUSH_ESTABLISH_TIMEOUT"));
        }
    }
    
    public boolean g() {
        final int intValue = this.aj().d("MAX_PUSH_SESSIONS");
        return intValue == -1 || intValue > ax.ad;
    }
    
    public void h() {
        final String g = this.aj().g("PUSH_MODE");
        if (g.equalsIgnoreCase("auto")) {
            this.ac = 1;
        }
        else if (g.equalsIgnoreCase("push")) {
            this.ac = 2;
        }
        else if (g.equalsIgnoreCase("poll")) {
            this.ac = 3;
        }
        else if (g.equalsIgnoreCase("cached")) {
            if (ax.y.containsKey(this.ao().toString())) {
                this.ac = 3;
                if (ay.a.h()) {
                    ay.a.f(this.as() + " found entry in mode-cache; setting push-mode to 'poll'");
                }
            }
            else {
                this.ac = 1;
            }
        }
    }
    
    public a1 i() {
        return this.j();
    }
    
    public ci j() {
        if (this.j == null) {
            this.j = new ci();
        }
        return this.j;
    }
    
    public final void a(final String s) {
        if (this.ai == 10) {
            if (ay.a.k()) {
                ay.a.i(this.as() + " immediately unsubscribing " + s);
            }
            synchronized (this.ah) {
                this.ah.append(s);
            }
            this.produce();
        }
        else {
            if (this.ai == 11) {
                if (ay.a.j()) {
                    ay.a.h(this.as() + " got unhandled unsubscribe ids " + s);
                }
                return;
            }
            if (ay.a.j()) {
                ay.a.h(this.as() + " preparing " + s + " for unsubscription");
            }
            if (s == null || s.length() == 0) {
                return;
            }
            synchronized (this.ah) {
                if (this.ah.length() > 0 && !s.startsWith(",") && this.ah.charAt(this.ah.length() - 1) != ',') {
                    this.ah.append(",");
                }
                this.ah.append(s);
            }
        }
    }
    
    public final void produce() {
        String s = null;
        synchronized (this.ah) {
            if (this.ah.length() == 0) {
                if (ay.a.k()) {
                    ay.a.i(this.as() + " no IDs to unsubscribe");
                }
                if (this.ai == 12) {
                    this.at().a(this, System.currentTimeMillis() + this.aj);
                }
                return;
            }
            s = this.ah.toString();
            this.ah = new StringBuffer();
        }
        if (ay.a.i()) {
            ay.a.g(this.as() + " try to unsubscribe id(s) " + s);
        }
        final long longValue = this.aj().d("REQUEST_TIMEOUT");
        if (s.length() < this.aj().d("MAX_GET_REQUEST_LENGTH")) {
            new c9(this, this.ao(), longValue, this.b(this.aj().g("URI_UNSUBSCRIBE"), s));
        }
        else {
            if (ay.a.i()) {
                ay.a.g(this.as() + " try to fragment ids; ID-string is too long");
            }
            final int intValue = this.aj().d("MAX_GET_REQUEST_LENGTH");
            while (s.length() > intValue) {
                final int index = s.indexOf(",", intValue);
                if (index < 0) {
                    break;
                }
                final String substring = s.substring(0, index);
                s = s.substring(index + 1);
                new c9(this, this.ao(), longValue, this.b(this.aj().g("URI_UNSUBSCRIBE"), substring));
            }
            if (s.length() > 0) {
                new c9(this, this.ao(), longValue, this.b(this.aj().g("URI_UNSUBSCRIBE"), s));
            }
        }
        if (this.ai == 12) {
            this.at().a(this, System.currentTimeMillis() + this.aj);
        }
    }
    
    public final void k() {
        this.a((String)null, (String)null);
    }
    
    public final void a(final String s, final String s2) {
        new co(this, this.ao(), this.aj().d("REQUEST_TIMEOUT"), this.a(this.aj().g("URI_BYE"), null, s, s2));
    }
    
    public String l() {
        final String string = this.d(this.aj().g("URI_BYE")).toString();
        final String b = super.a.b("PROTOCOL_LIST", 0);
        final String b2 = super.a.b("CHOST_LIST", 0);
        final int intValue = super.a.a("CPORT_LIST", 0);
        String s;
        if (b.indexOf("https") > -1) {
            s = "https";
        }
        else {
            s = "http";
        }
        return s.toLowerCase(Locale.ENGLISH) + "://" + b2 + ":" + intValue + string;
    }
    
    public void a(final Exception ex, final String s) {
        this.a(ex, s, this.c(), null);
    }
    
    public void a(final Exception ex, final String s, final ce ce) {
        this.a(ex, s, ce, null);
    }
    
    public void a(final Exception ex, final String s, final ce ce, final cg cg) {
        if (ay.a.h()) {
            ay.a.d(this.as() + " comErrorOccured: " + s, ex);
        }
        if (ce.a() == 8) {
            if (ay.a.g()) {
                ay.a.d(this.as() + " closing because of STATUS_SERVER_CLOSE");
            }
            this.a(false);
            return;
        }
        if (ce.a() != 2) {
            if (ce.a() != 1 && ce.a() != 4) {
                this.a(5, ex, s, ce);
            }
            return;
        }
        if (this.a(5, ex, s, ce) == null) {
            return;
        }
        if (this.ae) {
            --ax.ad;
        }
        if (cg != null && this.ac == 1) {
            final int a = this.a(cg);
            if (!cg.e() || a == 2) {
                if (ay.a.h()) {
                    ay.a.f(this.as() + " changing push_mode from 'auto' to 'poll'");
                }
                this.ac = 3;
                if (this.aj().g("PUSH_MODE").equalsIgnoreCase("cached")) {
                    ax.y.put(this.ao().toString(), "true");
                    if (ay.a.h()) {
                        ay.a.f(this.as() + " storing push_mode 'poll' in mode-cache");
                    }
                }
            }
            else if (cg.e() && this.ac == 1 && a == 1) {
                if (ay.a.h()) {
                    ay.a.f(this.as() + " changing push_mode from 'auto' to 'push'");
                }
                this.ac = 2;
            }
        }
        final int intValue = this.aj().d("SESSION_FLIP_LIMIT");
        if (intValue != 0 && this.m > intValue && !this.aj().h("PROTOCOL_LIST")[0].equalsIgnoreCase("https")) {
            this.ac = ((this.m % 2 == 0) ? 2 : 3);
        }
        if (ex instanceof ct) {
            this.n = Integer.toString(((ct)ex).c());
        }
        else if (ex instanceof cu && ((cu)ex).c() == 1) {
            this.n = "TIMEOUT";
        }
        else {
            this.n = "UNKNOWN";
        }
        if (this.af != null) {
            this.af.a();
        }
        int n = 0;
        long longValue = this.aj().d("PUSH_SLEEP_TIME");
        if (longValue < 1000L) {
            longValue = 1000L;
        }
        while (true) {
            ++n;
            long n2;
            if (this.e != 0L) {
                n2 = this.e;
            }
            else {
                n2 = this.aj().d("PUSH_SLEEP_TIME");
                if (n2 < 1000L) {
                    n2 = 1000L;
                }
            }
            final long n3 = (long)(Math.random() * n2);
            final ce a2 = this.a(3, "try to sleep for " + (longValue + n3) + "ms");
            try {
                if (ay.a.h()) {
                    ay.a.f(this.as() + " sleeping for " + (longValue + n3) + "ms ...");
                }
                cv.a(longValue + n3);
            }
            catch (Exception ex3) {}
            if (this.a(4, null, "reconnection... try number " + n, a2) == null) {
                return;
            }
            if (ay.a.h()) {
                ay.a.f(this.as() + " try to reconnect...");
            }
            this.f();
            if (this.d() == 2) {
                if (ay.a.h()) {
                    ay.a.f(this.as() + " reconnected! try to resubscribe...");
                }
                final Vector vector = new Vector<cw>();
                synchronized (this) {
                    final Enumeration<Object> keys = this.s.keys();
                    while (keys.hasMoreElements()) {
                        final cw cw = this.s.get(keys.nextElement());
                        final a5 h = cw.h();
                        h.e(this.f + this.aj().g("URI_PREFIX_SUBSCRIBE"));
                        h.a("PUSH_CLIENT_ID", this.a);
                        h.a(super.a.a("AUTH_ID_NAME"), super.a.a("AUTH_ID_VALUE"));
                        vector.addElement(cw);
                    }
                }
                if (this.d() == 2) {
                    final int size = vector.size();
                    if (size > 0) {
                        final Integer d = super.a.d("RESUBSCRIBE_MAX_CHUNK_SIZE");
                        final Integer d2 = super.a.d("RESUBSCRIBE_SLEEP");
                        long longValue2 = 100L;
                        if (d2 != null) {
                            longValue2 = d2;
                        }
                        if (d != null && size > d) {
                            if (ay.a.i()) {
                                ay.a.g("splitting resubscriptions into chunks of " + d + " elements");
                            }
                            int i = 0;
                            final int intValue2 = d;
                            int n4 = 0;
                            while (i < size) {
                                final Vector<cw> vector2 = new Vector<cw>();
                                for (int n5 = 0; n5 < intValue2 && i < size; ++n5, ++i) {
                                    vector2.addElement(vector.elementAt(i));
                                }
                                if (ay.a.k()) {
                                    ay.a.i("starting resubscribe chunk " + n4 + ":" + vector2);
                                }
                                this.aq().a(new cy(this, this, vector2));
                                ++n4;
                                try {
                                    Thread.sleep(longValue2);
                                }
                                catch (InterruptedException ex2) {
                                    if (!ay.a.g()) {
                                        continue;
                                    }
                                    ay.a.b("could not apply the sleep time of " + longValue2 + " between resubscribe chunks", ex2);
                                }
                            }
                        }
                        else {
                            new cz(this, vector, this.ao(), this.aj().a("URI_PREFIX"), this.aj().d("REQUEST_TIMEOUT"), this.ag());
                        }
                    }
                    else if (ay.a.g()) {
                        ay.a.d("no subscriptions available for re-subscribe");
                    }
                    return;
                }
            }
            longValue = longValue * 3L / 2L;
            if (longValue <= this.al) {
                continue;
            }
            longValue = this.al;
        }
    }
    
    public final void a(final cw cw) {
        if (ay.a.i()) {
            ay.a.g(this.as() + " resubscribe object " + cw + " with " + cw.ad().size() + " subscriptions");
        }
        this.c(cw);
        this.b(cw);
    }
    
    public final void b(final cw cw) {
        final Vector ad = cw.ad();
        if (ad != null) {
            final c1[] array = new c1[ad.size()];
            ad.copyInto(array);
            this.a(array);
        }
    }
    
    public final synchronized void c(final cw cw) {
        this.a(cw, true);
    }
    
    public final synchronized void a(final cw cw, final boolean b) {
        this.s.remove(cw.ac());
        this.t.remove(cw.ab());
        if (this.d() == 2 && b) {
            this.a(cw.ac());
        }
    }
    
    public cw b(final String s) {
        return this.s.get(s);
    }
    
    public cw c(final String s) {
        return this.t.get(cw.a(s, this));
    }
    
    public String m() {
        return ck.a(this.k++);
    }
    
    public int a(final cg cg) {
        if (cg instanceof cf) {
            return 1;
        }
        if (cg instanceof cl) {
            return 2;
        }
        return 0;
    }
    
    public synchronized ce a(final a3 z, final cg ag) {
        if (this.d() == 2 || this.d() == 7) {
            return null;
        }
        if (z.f()) {
            ++this.l;
            this.z = z;
            String s = "unknown";
            final int a = this.a(ag);
            if (a == 1) {
                s = "push";
                ++ax.ad;
                this.ae = true;
            }
            else if (a == 2) {
                s = "poll";
                this.ae = false;
            }
            this.a(this.ae ? 11 : 12, "push-session established (" + s + ")");
            if (this.g == null) {
                this.g = this.z.e("PUSH_CLIENT_ID");
            }
            this.a = this.z.e("PUSH_CLIENT_ID");
            this.b = this.z.d("PUSH_SERVER_DELAY");
            this.d = this.z.d("PUSH_IDLE_TIMEOUT");
            this.f = this.z.e("PUSH_PREFIX");
            this.e = this.z.d("PUSH_RECONNECT_DELAY");
            if (this.z.b("PUSH_CASCADE") >= 0) {
                this.h = this.z.d("PUSH_CASCADE");
            }
            int i = 0;
            if (this.z.b("PUSH_CLIENT_STATUS_TIMEOUT") >= 0) {
                final Integer n = (Integer)this.z.g("PUSH_CLIENT_STATUS_TIMEOUT");
                if (n != null) {
                    this.i = n;
                    final int intValue = this.aj().d("PUSH_CLIENT_STATUS_FACTOR");
                    if (intValue <= 0) {
                        this.i = 0;
                    }
                    else {
                        i = this.i;
                        this.i /= intValue;
                        this.i -= this.aj().d("PUSH_ESTABLISH_TIMEOUT");
                        this.i = Math.max(this.i, this.aj().d("PUSH_ESTABLISH_TIMEOUT"));
                    }
                }
            }
            if (this.f == null) {
                this.f = "";
            }
            if (this.w != null) {
                synchronized (ax.x) {
                    for (int j = 0; j < this.w.size(); ++j) {
                        final cg cg = ax.x.get(this.w.elementAt(j));
                        if (cg != null && cg instanceof cf) {
                            ((cf)cg).a();
                        }
                        ax.x.remove(this.w.elementAt(j));
                        if (ay.a.k()) {
                            ay.a.i(this.as() + " removed old pushClientIds " + this.w);
                        }
                    }
                    this.w = null;
                }
            }
            if (ay.a.i()) {
                ay.a.g(this.as() + " received session object (in PushSession.setSessionObject) with following " + "properties: pushClientId=" + this.a + ";pushServerDelay=" + this.b + ";pushIdleTimeout=" + this.d + ";pushURIPrefix=" + this.f + ";pushReconnectDelay=" + this.e + ";pushCascade=" + this.h + ";pushClientStatusTimeout=" + this.i + "(serverTO=" + i + ")");
            }
            this.ag = ag;
            if (this.af != null) {
                if (ay.a.i()) {
                    ay.a.g(this.as() + " killing client-keep-alive scheduler " + this.af.hashCode());
                }
                this.af.a();
                this.af = null;
            }
            if (ay.a.i()) {
                ay.a.g(this.as() + " starting client-keep-alive scheduler");
            }
            this.af = new cn(this);
            return this.c();
        }
        this.ak.addElement(new aw("skye/session MDG error", null, 10));
        if (this.z.r() > 0) {
            this.a(6, this.z.t());
        }
        else {
            this.a(5, this.z.u(), this.z.t());
        }
        return null;
    }
    
    public final String n() {
        return this.a;
    }
    
    public final String o() {
        return this.g;
    }
    
    public final String p() {
        return (this.f != null) ? this.f : "";
    }
    
    public final String q() {
        return this.n;
    }
    
    public final int r() {
        return this.l;
    }
    
    public final int s() {
        return this.m;
    }
    
    public final int t() {
        return this.h;
    }
    
    public final int u() {
        return this.i;
    }
    
    public final int v() {
        return ax.ad;
    }
    
    public final long w() {
        return ax.p;
    }
    
    public final long x() {
        return ax.o;
    }
    
    public final int y() {
        return ax.q;
    }
    
    public final long z() {
        return ax.r;
    }
    
    public final String aa() {
        return this.aj().a("SESSION_INFO_MSG");
    }
    
    public final long ab() {
        if (this.b < 0L) {
            return this.aj().d("PUSH_SERVER_DELAY");
        }
        return this.b;
    }
    
    public long ac() {
        return ((this.z != null) ? this.d : 5000L) + this.b + this.aj().d("PUSH_ESTABLISH_TIMEOUT");
    }
    
    public long ad() {
        return this.c;
    }
    
    public String ae() {
        if (this.ag == null) {
            return "unkown";
        }
        if (this.ag instanceof cf) {
            return "p-push";
        }
        if (this.ag instanceof cl) {
            return "u-push";
        }
        return "unknown";
    }
    
    public cj d(final String s) {
        return this.a(s, null, null, null);
    }
    
    public cj b(final String s, final String s2) {
        return this.a(s, s2, null, null);
    }
    
    public cj a(final String s, final String s2, final String s3, final String s4) {
        return this.a(s, s2, s3, s4, true);
    }
    
    public cj a(final String s, final String s2, final String s3, final String s4, final boolean b) {
        final String s5 = b ? this.aj().a("URI_PREFIX") : "";
        String s6 = (s4 != null) ? s4 : this.f;
        if (s6 == null) {
            s6 = "";
        }
        cj cj;
        if (s5 != null) {
            cj = new cj(s5 + s6 + s);
        }
        else {
            cj = new cj(s6 + s);
        }
        if (s3 != null) {
            cj.a("PUSH_CLIENT_ID", s3);
        }
        else {
            cj.a("PUSH_CLIENT_ID", this.a);
        }
        if (s2 != null) {
            cj.a("PUSH_OBJECT_ID", s2);
        }
        final String a = this.aj().a("AUTH_ID_NAME");
        final String a2 = this.aj().a("AUTH_ID_VALUE");
        if (a != null && a2 != null) {
            cj.a(a, a2);
        }
        else if (ay.a.g()) {
            ay.a.d(this.as() + " either AUTH_ID_NAME or AUTH_ID_VALUE is null");
        }
        final String a3 = this.aj().a("PUSH_CLIENT_NAME");
        if (a3 != null && a3.length() > 0) {
            cj.a("PUSH_CLIENT_NAME", a3);
        }
        this.b(cj);
        if (ay.a.k()) {
            ay.a.i("transformed " + s + " to " + cj.toString());
        }
        return cj;
    }
    
    public cj af() {
        return this.a(this.aj().g("URI_UPDATE"), null, null, null, false);
    }
    
    public void a(final Vector w) {
        this.w = w;
    }
    
    public final ce a(final int n, final String s) {
        return this.a(n, null, s);
    }
    
    public final synchronized ce a(final int n, final Exception ex, final String s, final ce ce) {
        if (this.c() != ce) {
            return null;
        }
        return this.a(n, ex, s);
    }
    
    public final synchronized ce a(final int n, final Exception ex, final String s) {
        this.aa = new ce(n, ex, s);
        this.ab.addElement(this.aa);
        if (ay.a.h()) {
            if (this.ab.size() > 1) {
                ay.a.d(this.as() + " state change from " + this.ab.elementAt(this.ab.size() - 2) + " to " + this.aa, ex);
            }
            else {
                ay.a.d(this.as() + " set state to " + this.aa, ex);
            }
        }
        while (this.ab.size() > 20) {
            this.ab.removeElementAt(0);
        }
        synchronized (this.u) {
            final Enumeration<c> keys = ((Hashtable)this.u.clone()).keys();
            while (keys.hasMoreElements()) {
                try {
                    keys.nextElement().callback(this, this.aa);
                }
                catch (Exception ex2) {
                    if (!ay.a.g()) {
                        continue;
                    }
                    ay.a.b(this.as() + " caught exception during invocation of StateCallback", ex2);
                }
            }
        }
        return this.aa;
    }
    
    public String ag() {
        return this.p() + super.a.a("URI_POST_SUBSCRIBE") + "&PUSH_CLIENT_ID" + "=" + this.a + "&PUSH_RID" + "=" + cj.b() + "&" + super.a.a("AUTH_ID_NAME") + "=" + super.a.a("AUTH_ID_VALUE");
    }
    
    private void a(final c7 object, final int n) {
        synchronized (this.v) {
            if (!this.v.isEmpty()) {
                final Enumeration<c8> keys = ((Hashtable)this.v.clone()).keys();
                while (keys.hasMoreElements()) {
                    try {
                        keys.nextElement().a(this, object, n);
                    }
                    catch (Exception ex) {
                        if (!ay.a.g()) {
                            continue;
                        }
                        ay.a.b(this.as() + " caught exception during invocation of LimitCheckCallback", ex);
                    }
                }
            }
        }
    }
    
    private int a(final a5 a5) {
        if (ay.a.j()) {
            ay.a.h("checking limit for request " + a5);
        }
        if (!this.am.containsKey(a5.c())) {
            if (ay.a.l()) {
                ay.a.j("couldn't find object-path " + a5.c() + " in limitation-hash");
            }
            return Integer.MAX_VALUE;
        }
        final Vector<c7> vector = this.am.get(a5.c());
        if (vector == null) {
            return Integer.MAX_VALUE;
        }
        int i = 0;
        while (i < vector.size()) {
            final Object object = vector.elementAt(i);
            if (a5.a(object.a(), false)) {
                if (ay.a.l()) {
                    ay.a.j("checking limits: " + a5 + " includes " + object.a());
                }
                if (object.c() < object.b()) {
                    object.d();
                    this.a(object, 0);
                    return object.b() - object.c();
                }
                this.a(object, 1);
                return -1;
            }
            else {
                if (ay.a.l()) {
                    ay.a.j("checking limits: " + a5 + " is not including " + object);
                }
                ++i;
            }
        }
        return Integer.MAX_VALUE;
    }
    
    private int b(final a5 a5) {
        if (!this.am.containsKey(a5.c())) {
            return Integer.MAX_VALUE;
        }
        final Vector<c7> vector = this.am.get(a5.c());
        if (vector == null) {
            return Integer.MAX_VALUE;
        }
        int i = 0;
        while (i < vector.size()) {
            final Object object = vector.elementAt(i);
            if (a5.a(object.a(), false)) {
                if (object.c() > 0) {
                    object.e();
                    this.a(object, 2);
                    return object.b() - object.c();
                }
                if (ay.a.g()) {
                    ay.a.d("reached limit -1 for request " + a5);
                }
                this.a(object, 3);
                return object.b();
            }
            else {
                ++i;
            }
        }
        return Integer.MAX_VALUE;
    }
    
    private int c(final a5 a5) {
        if (!this.am.containsKey(a5.c())) {
            return Integer.MAX_VALUE;
        }
        final Vector<c7> vector = this.am.get(a5.c());
        if (vector == null) {
            return Integer.MAX_VALUE;
        }
        for (int i = 0; i < vector.size(); ++i) {
            final Object object = vector.elementAt(i);
            if (a5.a(object.a(), false)) {
                return object.b();
            }
        }
        return Integer.MAX_VALUE;
    }
    
    private void ax() {
        if (this.am.size() == 0) {
            this.an = false;
        }
        else if (ay.a.g()) {
            ay.a.d("reset of hasSubscription-flag failed");
        }
    }
    
    static {
        ax.o = System.currentTimeMillis();
        ax.p = 0L;
        ax.q = 0;
        ax.r = 0L;
        ax.x = new Hashtable();
        ax.y = new Hashtable();
        ax.ad = 0;
    }
}
