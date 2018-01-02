import com.daysofwonder.req.s;
import com.daysofwonder.req.A;
import com.daysofwonder.req.E;
import com.daysofwonder.req.u;
import com.daysofwonder.req.D;
import com.daysofwonder.applet.az;
import com.daysofwonder.applet.af;
import com.daysofwonder.applet.aF;
import java.awt.Image;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import com.daysofwonder.req.z;
import com.daysofwonder.tt.req.g;
import com.daysofwonder.applet.aC;
import com.daysofwonder.req.h;
import com.daysofwonder.req.j;
import com.daysofwonder.req.y;
import com.daysofwonder.util.t;
import com.daysofwonder.req.k;
import com.daysofwonder.applet.J;
import com.daysofwonder.req.f;
import com.daysofwonder.tt.m;
import com.daysofwonder.tt.o;
import com.daysofwonder.tt.n;
import java.util.Vector;
import com.daysofwonder.util.UIProperties;
import com.daysofwonder.b.b;
import com.daysofwonder.tt.i;
import com.daysofwonder.tt.e;
import com.daysofwonder.tt.d;
import com.daysofwonder.applet.aG;

// 
// Decompiled by Procyon v0.5.30
// 

public class G extends aG
{
    private static final c K;
    private static final int[] L;
    private boolean M;
    private boolean N;
    private String O;
    private d P;
    private d Q;
    private int R;
    private int S;
    private static Integer[] T;
    private e U;
    private i[] V;
    private b[] W;
    private b[] X;
    private UIProperties Y;
    private boolean Z;
    private i aa;
    private com.daysofwonder.util.G ab;
    private S ac;
    private com.daysofwonder.util.G ad;
    private Vector ae;
    private Vector af;
    private Vector ag;
    private boolean ah;
    private boolean ai;
    private boolean aj;
    private boolean ak;
    private boolean al;
    private int am;
    private int an;
    private n ao;
    private ae ap;
    private com.daysofwonder.tt.c aq;
    private i ar;
    private boolean as;
    private Object at;
    private n au;
    private o av;
    private i aw;
    private boolean ax;
    private boolean ay;
    private m az;
    
    public G(final String s, final int n) {
        super(s, n);
        this.M = true;
        this.R = -1;
        this.at = new Object();
        com.daysofwonder.req.f.a(this.getClass(), "com/daysofwonder/tt/ttrequest.properties");
    }
    
    public void a(final J j) {
        super.a(j);
        if (j instanceof S) {
            this.ac = (S)j;
        }
        else {
            this.ac = null;
        }
    }
    
    public void a() {
        this.N = true;
    }
    
    public boolean a(k k) {
        if (k.p()) {
            try {
                k = (k)com.daysofwonder.req.f.a(k);
            }
            catch (Exception ex) {
                com.daysofwonder.util.t.a(ex);
            }
        }
        if (this.Z() != null && k.n() == null) {
            k.a(this.Z());
            k.c();
        }
        if (k instanceof y && !this.n && this.aB()) {
            this.c(k);
        }
        else if (k instanceof j) {
            String s = aG.c(((j)k).d());
            if (s.length() > 15) {
                s = s.substring(0, 15) + "...";
            }
            final com.daysofwonder.a.i i = this.q.get(((j)k).e());
            if (i != null) {
                this.w.a(29, new Object[] { i.z(), s });
                i.a(s);
            }
            this.h(27);
            this.i(27);
            this.w.a();
        }
        else if (k instanceof h) {
            this.al = false;
            final h h = (h)k;
            if (h.d() == this.s.v()) {
                com.daysofwonder.util.t.a("Get a YourTurn for me");
                this.R = this.s.v();
                this.h(1);
                if (this.a != 9) {
                    this.a = 6;
                }
                if (this.aA()) {
                    this.w.a(8, new Object[] { this.s.z() });
                }
                else {
                    this.w.a(1, null);
                }
                if (this.ah) {
                    com.daysofwonder.util.t.a("yourtun: last turn for other player");
                    this.w.a(26, null);
                    if (!this.aA()) {
                        this.w.b(4, null);
                    }
                }
            }
            else {
                final int d = h.d();
                com.daysofwonder.util.t.a("Get a YourTurn for id: " + d);
                final d a = this.a(d);
                if (a != null) {
                    System.out.println(a.z() + " is about to play");
                    this.R = d;
                    this.h(30);
                    this.i(30);
                    this.h(2);
                    this.w.a(2, new Object[] { a.z() });
                    this.i(2);
                }
            }
            if (this.a == 17) {
                this.a = 6;
            }
            this.w.a();
            com.daysofwonder.util.t.a("yourtun: end of yourturn");
        }
        else if (k instanceof com.daysofwonder.tt.req.f) {
            com.daysofwonder.util.t.a("got TTGameStateRequest");
            final com.daysofwonder.tt.req.f f = (com.daysofwonder.tt.req.f)k;
        }
        else if (k instanceof com.daysofwonder.tt.req.b) {
            final com.daysofwonder.tt.req.b b = (com.daysofwonder.tt.req.b)k;
            final i[] d2 = b.d();
            this.aj = b.f();
            final int g = b.g();
            com.daysofwonder.util.t.a("remainingCards: " + g + " fRemainingCards " + this.am + " fDeckExhausted " + this.aj);
            if (g > this.am) {
                this.h(10);
                this.w.a(27, null);
            }
            this.am = g;
            this.an = b.h();
            this.w.a();
            if (b.e()) {
                com.daysofwonder.util.t.a("Got 3 locos");
                this.w.a(13, null);
                this.h(10);
                this.w.a(new ao(this.V, d2), 100);
            }
            else {
                for (int j = 0; j < this.V.length; ++j) {
                    if (this.V[j] == null && d2[j] != null) {
                        this.w.a(16, new Object[] { d2[j] });
                        this.h(11);
                        this.w.a(new ar(this.V, d2[j], j), 100);
                    }
                }
            }
            this.w.a();
        }
        else if (k instanceof com.daysofwonder.tt.req.k) {
            final com.daysofwonder.tt.req.k l = (com.daysofwonder.tt.req.k)k;
            this.aj = l.e();
            final int g2 = l.g();
            com.daysofwonder.util.t.a("remainingCards: " + g2 + " fRemainingCards " + this.am + " fDeckExhausted " + this.aj);
            if (g2 > this.am) {
                this.h(10);
                this.w.a(27, null);
            }
            this.am = g2;
            this.an = l.h();
            if (l.f() == this.s.v()) {
                if (l.j() != null) {
                    this.w.a(14, new Object[] { l.j() });
                    if (this.al && !this.aA()) {
                        this.al = false;
                        if (!l.j().c() && !this.aN()) {
                            this.Z = true;
                            this.a = 10;
                            this.R = this.s.v();
                            this.w.a();
                        }
                    }
                    else {
                        if (this.aA() || this.ai) {
                            if (l.d() != -1) {
                                this.V[l.d()] = null;
                            }
                            this.h(8);
                            this.w.a(new ar(this.V, l.j(), l.d(), -2), this.ai ? 30 : 100);
                            this.h(9);
                            this.ai = false;
                        }
                        this.P.b(l.j());
                    }
                    if (this.Z) {
                        this.w.a(25, null);
                    }
                }
            }
            else {
                final d a2 = this.a(l.f());
                if (l.j() != null) {
                    a2.a(1);
                    if (l.d() >= 0) {
                        this.w.a(15, new Object[] { a2.z(), l.j() });
                        this.V[l.d()] = null;
                        this.w.a();
                        this.h(8);
                        this.w.a(new ar(this.V, l.j(), l.d(), this.b(a2)), 100);
                        this.h(9);
                        this.w.a();
                    }
                    else {
                        this.h(8);
                        this.w.a(new ar(null, com.daysofwonder.tt.i.a, -1, this.b(a2)), 100);
                        this.h(9);
                        this.w.a(22, new Object[] { a2.z() });
                    }
                }
            }
            if (l.d() >= 0) {
                if (l.k() != null) {
                    com.daysofwonder.util.t.a("new card at " + l.d() + " is " + l.k());
                    this.h(11);
                    this.w.a(16, new Object[] { l.k() });
                    this.w.a(new ar(this.V, l.k(), l.d()), 100);
                    this.h(9);
                }
                else {
                    com.daysofwonder.util.t.a("NO NEW CARD");
                }
            }
            this.w.a();
        }
        else if (k instanceof com.daysofwonder.tt.req.e) {
            final com.daysofwonder.tt.req.e e = (com.daysofwonder.tt.req.e)k;
            final d a3 = this.a(e.e());
            final o a4 = e.a(this.U);
            if (a3 != this.s) {
                this.h(9);
                System.out.println(a3.z() + " claimed " + a4);
                this.w.a(17, new Object[] { a3.z(), a4, e.d() });
                a3.b(e.d(), a4);
                this.w.a(new l(e.d(), a4, this.b(a3)), 100);
                a3.c(e.d(), a4);
                this.i(28);
            }
            else if (this.aA()) {
                this.P.a(e.d(), a4, null);
                this.h(9);
                this.w.a(new l(e.d(), a4, -1), 100);
                this.w.a(17, new Object[] { a3.z(), a4, e.d() });
                this.P.g();
                this.i(28);
            }
            else {
                com.daysofwonder.util.t.a("CLAIMED ROUTE TUNNEL_STATE");
                if (this.a == 18) {
                    com.daysofwonder.util.t.a("score increase");
                    this.h(28);
                    if (a4 != null) {
                        com.daysofwonder.util.t.a("route claimed");
                        this.ac.a(a4, this.t);
                        if (this.M) {
                            com.daysofwonder.util.t.a("score animation");
                            this.w.a(new e(e.d(), a4), 100);
                        }
                        com.daysofwonder.util.t.a("fMe.played: " + e.d());
                        this.P.a(e.d(), a4, null);
                        this.P.g();
                    }
                    com.daysofwonder.util.t.a("back to play state");
                    this.a = 6;
                    this.R = -1;
                    this.w.a();
                }
                this.w.a(18, new Object[] { a4, e.d() });
                this.P.g();
                this.i(28);
            }
            if (a3.d() <= 2 && !this.ah) {
                com.daysofwonder.util.t.a("Victory condition - Last turn");
                this.w.a(23, new Object[] { a3.z(), a3.d() });
                this.ah = true;
            }
            this.ac.a();
        }
        else if (k instanceof com.daysofwonder.tt.req.l) {
            final com.daysofwonder.tt.req.l m = (com.daysofwonder.tt.req.l)k;
            final d a5 = this.a(m.e());
            final com.daysofwonder.tt.c a6 = m.a(this.U);
            if (a5 != this.s) {
                System.out.println(a5.z() + " claimed " + a6);
                this.w.a(30, new Object[] { a5.z(), a6, m.d() });
                a5.a(m.d(), a6);
                this.w.a(new n(m.d(), a6, this.b(a5)), 100);
            }
            else if (this.aA()) {
                this.P.a(m.d(), a6, null);
                this.w.a(new n(m.d(), a6, -1), 100);
                this.w.a(30, new Object[] { a5.z(), a6, m.d() });
            }
            else {
                this.w.a(31, new Object[] { a6, m.d() });
            }
            this.ac.a();
        }
        else if (k instanceof com.daysofwonder.tt.req.i) {
            final com.daysofwonder.tt.req.i i2 = (com.daysofwonder.tt.req.i)k;
            final d a7 = this.a(i2.h());
            final o a8 = i2.a(this.U);
            this.ao = null;
            com.daysofwonder.util.t.a("TTClaimedTunnelRequest: " + a8);
            if (!i2.k()) {
                com.daysofwonder.util.t.a("Asking for Tunnel: " + a8);
                if (a7 != this.s || this.aA()) {
                    if (this.aA()) {
                        this.P.b(i2.g());
                    }
                    this.w.a(new l(i2.g(), a8, this.aA() ? -1 : this.b(a7), i2.j()), 100);
                }
                this.a = 19;
                this.ac.a();
                this.w.a(new aq(i2.d(), 1, 3, 0), 100);
                this.ab = i2.d();
                this.ao = i2.e();
                this.a = 19;
                this.ac.a();
                if (i2.j()) {
                    com.daysofwonder.util.t.a("Tunnel success: " + a8);
                    if (this.ao != null && this.ao.d() > 0) {
                        com.daysofwonder.util.t.a("asking confirmation " + a8);
                        this.a = 20;
                        if (a7 == this.P) {
                            this.ac.a(a8, i2.g(), this.ao);
                        }
                        this.w.a();
                        return false;
                    }
                    com.daysofwonder.util.t.a("auto accept " + a8);
                    if (this.P == a7) {
                        com.daysofwonder.util.t.a("play " + i2.g());
                        this.P.a(i2.g(), a8, null);
                        this.ac.a(a8, this.t);
                        this.w.a(36, new Object[] { null, a8, i2.g() });
                        this.ac.a(a8, false);
                        this.h(28);
                        if (this.M) {
                            this.w.a(new e(i2.g(), a8), 100);
                        }
                        this.P.g();
                        this.i(28);
                    }
                    else {
                        com.daysofwonder.util.t.a("other player play " + i2.g());
                        this.h(9);
                        a7.b(i2.g(), a8);
                        this.ac.a(a8, this.b(a7));
                        this.w.a(33, new Object[] { a7.z(), a8, i2.g() });
                        this.h(28);
                        if (this.M) {
                            this.w.a(new e(i2.g(), a8), 100);
                        }
                        a7.c(i2.g(), a8);
                        this.i(28);
                    }
                }
                else {
                    com.daysofwonder.util.t.a("tunnel complete failure " + a8);
                    this.h(5);
                    if (a7 == this.P) {
                        com.daysofwonder.util.t.a("adding card back " + i2.g() + " my hand " + this.P.l());
                        this.w.a(37, new Object[] { null, a8, i2.g() });
                        this.w.a(new l(i2.g(), a8, -1, false, true), 100);
                        this.P.l().a(i2.g());
                    }
                    else {
                        com.daysofwonder.util.t.a("fill card back");
                        this.w.a(34, new Object[] { a7.z(), a8, i2.g() });
                        this.w.a(new l(i2.g(), a8, this.b(a7), false, true), 100);
                        a7.a(i2.g().d());
                    }
                    this.i(5);
                }
            }
            else {
                this.ao = i2.e();
                com.daysofwonder.util.t.a("Got confirmation for Tunnel: " + a8 + "  " + i2.l());
                if (i2.l()) {
                    com.daysofwonder.util.t.a("tunnel accepted: " + a8);
                    if (a7 == this.P) {
                        final n f2 = i2.f();
                        if (f2 != null) {
                            com.daysofwonder.util.t.a("exchanged: " + f2 + "on: " + this.P.l());
                            for (int n = 0; n < f2.d(); n += 2) {
                                com.daysofwonder.util.t.a("NOT removing: " + f2.a(n + 1) + " adding " + f2.a(n));
                            }
                        }
                        if (this.ao != null && this.ao.d() > 0) {
                            com.daysofwonder.util.t.a("removing: " + this.ao);
                            this.P.b(this.ao);
                            this.h(8);
                            this.w.a(new aq(this.ao, 2, 1, 0), 100);
                            this.h(9);
                        }
                        this.h(28);
                        com.daysofwonder.util.t.a("played: " + this.ao);
                        this.P.a(i2.g(), a8, null);
                        this.ac.a(a8, this.t);
                        this.ac.a(a8, false);
                        if (this.M) {
                            this.w.a(new e(i2.g(), a8), 100);
                        }
                        if (this.ao != null && this.ao.d() > 0) {
                            this.w.a(35, new Object[] { null, a8, i2.g(), this.ao });
                        }
                        else {
                            this.w.a(36, new Object[] { null, a8, i2.g() });
                        }
                        this.P.g();
                        this.i(28);
                    }
                    else {
                        if (this.ao != null && this.ao.d() > 0) {
                            this.h(8);
                            a7.a(this.ao);
                            this.w.a(new aq(this.ao, 4, 1, this.b(a7)), 100);
                            this.h(9);
                        }
                        this.h(28);
                        a7.a(i2.g(), a8);
                        if (this.M) {
                            this.w.a(new e(i2.g(), a8), 100);
                        }
                        this.ac.a(a8, this.b(a7));
                        if (this.ao != null && this.ao.d() > 0) {
                            this.w.a(32, new Object[] { a7.z(), a8, i2.g(), this.ao });
                        }
                        else {
                            this.w.a(33, new Object[] { a7.z(), a8, i2.g() });
                        }
                        this.i(28);
                    }
                }
                else {
                    com.daysofwonder.util.t.a("player refused to pay for tunnel: " + a8);
                    this.h(8);
                    if (a7 == this.P) {
                        this.w.a(new l(i2.g(), a8, -1, false, true), 100);
                    }
                    else {
                        this.w.a(new l(i2.g(), a8, this.b(a7), false, true), 100);
                    }
                    this.h(9);
                    this.ac.a(a8);
                    if (a7 == this.P) {
                        com.daysofwonder.util.t.a("adding card back");
                        this.P.l().a(i2.g());
                        this.w.a(39, new Object[] { null, a8, i2.g() });
                    }
                    else {
                        com.daysofwonder.util.t.a("filling card back");
                        a7.a(i2.g().d());
                        this.w.a(38, new Object[] { a7.z(), a8, i2.g() });
                    }
                }
            }
            this.ab = null;
            this.ao = null;
            this.a = 6;
            this.w.a();
            if (a7.d() <= 2 && !this.ah) {
                com.daysofwonder.util.t.a("Victory condition - Last turn");
                this.w.a(23, new Object[] { a7.z(), a7.d() });
                this.ah = true;
            }
        }
        else if (k instanceof com.daysofwonder.tt.req.d) {
            final com.daysofwonder.tt.req.d d3 = (com.daysofwonder.tt.req.d)k;
            final d a9 = this.a(d3.e());
            this.S = d3.h();
            if (d3.f() == -1) {
                if (this.s.v() == d3.e()) {
                    this.ad = this.U.a(d3.d());
                    System.out.println("Tickets received:" + this.ad);
                    this.a = 7;
                    this.w.a();
                }
                else {
                    if (a9 != null) {
                        System.out.println(a9.z() + " got XXX tickets");
                        this.w.a(19, new Object[] { a9.z(), d3.g() });
                        this.h(8);
                        this.w.a(new an(this.b(a9), -d3.g()), 100);
                        this.h(9);
                        this.w.a();
                    }
                    this.ak = true;
                }
            }
            else if (this.s.v() != d3.e()) {
                System.out.println(a9.z() + " kept " + d3.f() + " tickets");
                if (this.aA() && this.a == 11) {
                    a9.b(-a9.f() + d3.f());
                    this.ak = false;
                    this.a = 6;
                }
                else {
                    a9.b(d3.f());
                }
                this.w.a(20, new Object[] { a9.z(), d3.f() });
                if (3 - d3.f() > 0) {
                    this.h(8);
                    this.w.a(new an(this.b(a9), 3 - d3.f()), 100);
                    this.h(9);
                }
                this.w.a();
            }
            else {
                com.daysofwonder.util.t.a("WAITING_1ST_TICKET_STATE: " + this.a);
                if (this.a == 11) {
                    final com.daysofwonder.util.G k2 = this.P.k();
                    final com.daysofwonder.util.G g3 = new com.daysofwonder.util.G();
                    if (this.aA()) {
                        System.out.println("lurking: " + this.P.k());
                        this.ae = d3.j();
                    }
                    int n2 = 0;
                    final com.daysofwonder.util.y c = k2.c();
                    while (c.a()) {
                        final com.daysofwonder.tt.f f3 = (com.daysofwonder.tt.f)c.b();
                        if (!this.ae.contains(n2)) {
                            System.out.println("Removing: " + f3);
                            g3.c(f3);
                            c.c();
                        }
                        ++n2;
                    }
                    if (this.aA()) {
                        this.w.a(24, new Object[] { this.P.z(), g3 });
                        this.h(8);
                        this.w.a(new an(-1, g3), 20);
                        this.h(9);
                        g3.b();
                    }
                    com.daysofwonder.util.t.a("TTGAME: " + this.P.k());
                    this.ac.e();
                    this.ac.a(null, this.P.k());
                    this.a = 17;
                }
                else {
                    if (this.aA()) {
                        System.out.println("lurking: " + this.P.k());
                        this.ae = d3.j();
                    }
                    final com.daysofwonder.util.G g4 = new com.daysofwonder.util.G();
                    if (this.ad == null) {
                        this.ad = this.U.c(d3.k());
                        System.out.println("tickets null: " + this.ad);
                    }
                    for (int n3 = 0; n3 < this.ae.size(); ++n3) {
                        final int intValue = this.ae.elementAt(n3);
                        if (intValue < 0 || intValue >= this.ad.a()) {
                            com.daysofwonder.util.t.a("Tickets kept > Tickets.size: kept: " + this.ae + " tickets " + this.ad);
                            com.daysofwonder.applet.y.a("Tickets kept > Tickets.size: kept: " + this.ae + " tickets " + this.ad, new Exception());
                        }
                        else {
                            this.P.a((com.daysofwonder.tt.f)this.ad.b(intValue));
                            g4.c(this.ad.b(intValue));
                            System.out.println("Keeping: " + this.ad.b(intValue));
                        }
                    }
                    if (this.aA()) {
                        this.w.a(24, new Object[] { this.P.z(), g4 });
                        this.h(8);
                        this.w.a(new an(-1, g4), 20);
                        this.h(9);
                        this.ac.a(null, this.ad);
                    }
                    else {
                        this.ac.a(this.ae, this.ad);
                    }
                    g4.b();
                    this.R = -1;
                    this.a = 6;
                }
                if (this.ae != null) {
                    this.ae.removeAllElements();
                    this.ae = null;
                }
                if (this.ad != null) {
                    this.ad.b();
                    this.ad = null;
                }
                this.P.g();
                this.w.a();
            }
        }
        else if (k instanceof g) {
            boolean b2 = false;
            final g g5 = (g)k;
            final Hashtable f4 = g5.f();
            com.daysofwonder.util.t.a("bonus: " + f4);
            final Vector d4 = g5.d();
            for (int n4 = 0; n4 < d4.size(); ++n4) {
                final com.daysofwonder.tt.req.j j2 = d4.elementAt(n4);
                final d a10 = this.a(j2.c());
                if (a10 != null) {
                    com.daysofwonder.util.t.a("Score: " + a10.z());
                    com.daysofwonder.util.t.a("wonLP: " + j2.e());
                    com.daysofwonder.util.t.a("score: " + j2.d());
                    for (int n5 = 0; n5 < j2.b().length; ++n5) {
                        com.daysofwonder.util.t.a("ticket score: " + j2.b()[n5]);
                    }
                    if (f4.containsKey("longest")) {
                        com.daysofwonder.util.t.a("longest bonus: " + j2.e());
                        a10.d(j2.e());
                        a10.e(j2.i());
                    }
                    else {
                        a10.d(-1);
                    }
                    if (f4.containsKey("tickets")) {
                        com.daysofwonder.util.t.a("tickets bonus: " + j2.f());
                        a10.f(j2.f());
                    }
                    else {
                        a10.f(-1);
                    }
                    a10.k(j2.d());
                    a10.a(j2.b());
                    if (j2.h() != null && j2.h().a() > 0) {
                        a10.b(this.U.b(j2.h()));
                        b2 = true;
                    }
                    if (a10 != this.P) {
                        a10.a(this.U.c(j2.g()));
                    }
                }
            }
            if (b2) {
                this.a = 21;
                this.w.a();
                this.ax();
            }
            if (f4.containsKey("longest")) {
                com.daysofwonder.util.t.a("entering longest path 1: " + f4);
                this.af = g5.e();
                this.ag = f4.get("longest");
                this.a = 13;
                this.w.a();
                this.ax();
            }
            com.daysofwonder.util.t.a("entering score state");
            this.a = 12;
            this.w.a();
            this.ax();
            if (f4.containsKey("longest") && this.af != null) {
                com.daysofwonder.util.t.a("entering longest path 2: " + f4);
                this.a = 16;
                this.w.a();
                this.ax();
                this.af.removeAllElements();
                this.ag.removeAllElements();
                this.af = null;
                this.ag = null;
            }
            else {
                this.a = 23;
                this.w.a();
                this.ax();
            }
        }
        else if (k instanceof z) {
            final z z = (z)k;
            final Vector d5 = z.d();
            final Vector e2 = z.e();
            final Enumeration<Integer> elements = d5.elements();
            final Enumeration<Integer> elements2 = e2.elements();
            while (elements.hasMoreElements() && elements2.hasMoreElements()) {
                this.a((int)elements.nextElement()).a(z.f(), elements2.nextElement());
            }
            this.a = 4;
            this.w.a();
            return this.n = true;
        }
        return false;
    }
    
    public void a(final j j) {
        String s = j.d();
        if (this.as) {
            s = this.ac.b(s, j.g());
        }
        System.out.println("\t" + s + " is joining ");
        final d d = (d)this.a(j, s);
        this.w.a(3, new Object[] { d.z() });
        if (this.an()) {
            com.daysofwonder.util.t.a("Resume: name " + s);
            com.daysofwonder.util.t.a("Resume: id " + j.e());
            com.daysofwonder.util.t.a("Resume: score " + j.f());
        }
        this.r.addElement(d);
        this.q.put(d.v(), d);
        this.h(27);
        this.w.a();
        this.i(27);
    }
    
    protected Thread b() {
        final H h = new H(this);
        h.start();
        return h;
    }
    
    public void c() {
        final long currentTimeMillis = System.currentTimeMillis();
        Hashtable<K, b> a = null;
        final aF f = this.ac.f();
        int n = 0;
        final e u = new e();
        int size;
        try {
            com.daysofwonder.util.t.a("before load: " + Runtime.getRuntime().totalMemory() + " " + Runtime.getRuntime().freeMemory());
            a = (Hashtable<K, b>)com.daysofwonder.applet.y.a(null, this.O + com.daysofwonder.applet.y.e() + "-" + com.daysofwonder.applet.y.d() + ".gif", f);
            size = a.size();
            f.b(n, size);
            com.daysofwonder.util.t.a(this.O + "before load Map...:" + a);
            u.a(new ByteArrayInputStream((byte[])(Object)a.get(this.O + ".map")));
            this.U = u;
            com.daysofwonder.util.t.a("after load Map...");
            this.a = 15;
            this.w.a();
            f.b(n++, size);
            this.Y = (UIProperties)a.get("names-" + com.daysofwonder.applet.y.d() + ".properties");
            if (this.Y == null) {
                this.Y = (UIProperties)a.get("names-en.properties");
            }
            f.b(n++, size);
            this.U.a(this.Y);
            com.daysofwonder.util.t.a("after load mem: " + Runtime.getRuntime().totalMemory() + " " + Runtime.getRuntime().freeMemory());
            final com.daysofwonder.tt.f[] a2 = this.U.a();
            this.W = new b[a2.length];
            for (int i = 0; i < a2.length; ++i) {
                if (a2[i].c()) {
                    com.daysofwonder.util.t.a("MultiDest: " + a2[i].h() + " - " + a2[i].i());
                    this.W[i] = a.get(a2[i].h() + "-" + a2[i].i() + "-" + com.daysofwonder.applet.y.d() + ".gif");
                    if (this.W[i] == null) {
                        this.W[i] = a.get(a2[i].h() + "-" + a2[i].i() + ".gif");
                    }
                }
                else {
                    this.W[i] = a.get(a2[i].h() + "-" + a2[i].i() + "-" + com.daysofwonder.applet.y.d() + ".gif");
                    if (this.W[i] == null) {
                        this.W[i] = a.get(a2[i].i() + "-" + a2[i].h() + "-" + com.daysofwonder.applet.y.d() + ".gif");
                    }
                    if (this.W[i] == null) {
                        this.W[i] = a.get(a2[i].h() + "-" + a2[i].i() + ".gif");
                    }
                    if (this.W[i] == null) {
                        this.W[i] = a.get(a2[i].i() + "-" + a2[i].h() + ".gif");
                    }
                }
                if (this.W[i] == null) {
                    com.daysofwonder.util.t.a("[BUG]: fatal, no tickets image for: " + a2[i].h() + "/" + a2[i].i());
                }
                f.b(n++, size);
            }
            this.X = new b[this.U.h()];
            for (int j = 0; j < this.U.h(); ++j) {
                this.X[j] = a.get("city-" + j + ".png");
                if (this.X[j] != null) {
                    f.b(n++, size);
                }
            }
        }
        catch (Exception ex) {
            com.daysofwonder.util.t.a(ex);
            com.daysofwonder.applet.y.a("Map loading exception, map: " + a, ex);
            this.a = 503;
            return;
        }
        com.daysofwonder.util.t.a("Loading " + this.O + " maps took: " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
        com.daysofwonder.util.t.a("before install board mem: " + Runtime.getRuntime().totalMemory() + " " + Runtime.getRuntime().freeMemory());
        final int a3 = this.ac.a(this.U, true, true, a, f, n, size);
        com.daysofwonder.util.t.a("before install hit mem: " + Runtime.getRuntime().totalMemory() + " " + Runtime.getRuntime().freeMemory());
        final int a4 = this.ac.a(this.U, true, false, (byte[])(Object)a.remove("hit.gif"), f, a3, size);
        com.daysofwonder.util.t.a("before install small mem: " + Runtime.getRuntime().totalMemory() + " " + Runtime.getRuntime().freeMemory());
        if (a.get("smallboard.jpg") != null) {
            this.ac.a(this.U, false, false, (Image)a.remove("smallhit.gif"), f, this.ac.a(this.U, false, true, (Image)a.remove("smallboard.jpg"), f, a4, size), size);
        }
        else {
            this.ac.a(this.U, false, false, (Image)a.remove("medhit.gif"), f, this.ac.a(this.U, false, true, (Image)a.remove("medboard.jpg"), f, a4, size), size);
        }
        a.clear();
        System.gc();
        com.daysofwonder.util.t.a("after load mem: " + Runtime.getRuntime().totalMemory() + " " + Runtime.getRuntime().freeMemory());
        this.a = 5;
        this.w.a();
    }
    
    protected void a(final Thread thread) {
        try {
            com.daysofwonder.util.t.a("joining " + thread);
            thread.join();
            com.daysofwonder.util.t.a("end joining " + thread);
        }
        catch (InterruptedException ex) {
            com.daysofwonder.util.t.a(ex);
        }
        if (this.a == 503) {
            this.w.b(50, null);
            throw new RuntimeException("Game Aborted - Map Loading Error");
        }
    }
    
    public void d() {
        boolean b = false;
        this.F = true;
        this.P = (d)this.s;
        this.a = 14;
        this.w.a();
        final Thread b2 = this.b();
        if (this.an() || !this.aA()) {}
        if (!this.aA() && !this.aF()) {
            this.w.a(11, new Object[] { this.O });
        }
        else if (this.aA() && !this.aF()) {
            this.w.a(12, new Object[] { this.O, this.P.z() });
        }
        else if (this.aF()) {
            this.w.a(21, new Object[] { this.O });
        }
        if (!this.aA() && this.aF()) {
            this.w.a();
        }
        try {
            k at;
            while (true) {
                com.daysofwonder.util.t.a("WaitFor...");
                at = this.at();
                com.daysofwonder.util.t.a("WaitFor...found: " + at);
                if (at == null) {
                    if (this.aI()) {
                        throw new af("ping timeout");
                    }
                    b = true;
                    break;
                }
                else if (at instanceof j) {
                    this.a((j)at);
                }
                else if (at instanceof y) {
                    this.c(at);
                }
                else if (at instanceof com.daysofwonder.tt.req.f) {
                    com.daysofwonder.util.t.a("TTGameStateRequest...");
                    this.w.a();
                    this.a(b2);
                    this.w.a();
                    if (this.Z() != null && at.p()) {
                        at.a(this.Z());
                        at.c();
                        com.daysofwonder.util.t.a("deserialize...");
                        break;
                    }
                    break;
                }
                else {
                    com.daysofwonder.util.t.a("unknown req: " + at);
                }
            }
            this.w.a();
            this.a(b2);
            this.w.a();
            if (!b) {
                this.i = true;
                com.daysofwonder.applet.y.a();
                final com.daysofwonder.tt.req.f f = (com.daysofwonder.tt.req.f)at;
                this.P.a();
                if (f.m() != null) {
                    com.daysofwonder.util.t.a("reordering...");
                    final int[] m = f.m();
                    final Vector<d> r = new Vector<d>(this.r.size());
                    for (int i = 0; i < m.length; ++i) {
                        final d a = this.a(m[i]);
                        com.daysofwonder.util.t.a("Adding: " + i + " id: " + m[i] + " name: " + a.z());
                        if (a == this.s) {
                            this.t = i;
                        }
                        r.addElement(a);
                    }
                    this.r = r;
                    this.w.b();
                    this.w.a();
                }
                this.V = f.q();
                this.R = f.r();
                this.S = f.d();
                this.am = f.e();
                this.an = f.f();
                this.aj = f.g();
                final boolean h = f.h();
                for (int j = 0; j < this.r.size(); ++j) {
                    final d d = this.r.elementAt(j);
                    com.daysofwonder.util.t.a("setting: " + d.z());
                    d.a(this.U, f, (this.g && !h) || this.aA());
                    this.ac.a(d, j);
                    this.ac.b(d, j);
                }
                com.daysofwonder.util.t.a("Tickets: " + this.P.k());
                if (this.g || this.aA() || h) {
                    this.ac.a(null, this.P.k());
                }
                if (this.g) {
                    this.al = true;
                }
                if (this.r.size() <= this.U.d()) {
                    this.w.a(28, null);
                }
                this.a = (h ? ((this.aA() || this.P.h()) ? 11 : 9) : 6);
                this.w.a();
                this.ap();
                this.w.a();
                k at2;
                do {
                    at2 = this.at();
                } while (!Thread.currentThread().isInterrupted() && at2 != null && !this.a(at2));
                if (at2 == null && this.aI()) {
                    throw new af("ping timeout", true);
                }
                if (at2 != null && this.aA() && this.n) {
                    this.y = false;
                    this.e();
                    return;
                }
            }
        }
        catch (az az) {
            com.daysofwonder.util.t.a("AbortUserAction...");
            this.m.b();
        }
        boolean b3 = false;
        if (!this.aA() && this.f && this.i) {
            b3 = true;
        }
        this.F = false;
        if (!this.N) {
            this.e();
        }
        this.c(b3);
    }
    
    public synchronized void e() {
        super.e();
        this.P = null;
        this.Q = null;
        this.R = -1;
        this.U = null;
        this.V = null;
        this.Y = null;
        this.Z = false;
        this.aa = null;
        this.ad = null;
        this.ae = null;
        this.S = 0;
        if (this.W != null) {
            for (int i = 0; i < this.W.length; ++i) {
                if (this.W[i] != null) {
                    this.W[i].c();
                }
            }
        }
        if (this.X != null) {
            for (int j = 0; j < this.X.length; ++j) {
                if (this.X[j] != null) {
                    this.X[j].c();
                }
            }
        }
        this.X = null;
        this.W = null;
        this.ah = false;
        this.ai = false;
        this.aj = false;
        this.af = null;
        this.ag = null;
        this.am = 0;
        this.an = 0;
        this.F = false;
        this.ak = false;
        this.al = false;
        this.aq = null;
        this.ar = null;
        this.ao = null;
        this.as = false;
        this.au = null;
        this.av = null;
        this.aw = null;
        this.ay = false;
        this.az = null;
    }
    
    public void a(final com.daysofwonder.req.t t) {
        final int f = t.f();
        if (t.d() != null) {
            final Enumeration<D> elements = (Enumeration<D>)t.d().elements();
            while (elements.hasMoreElements()) {
                final d s = (d)this.a(elements.nextElement());
                if (f == s.v()) {
                    this.t = this.r.size();
                    this.s = s;
                    this.P = (d)this.s;
                }
                this.r.addElement(s);
                this.q.put(s.v(), s);
            }
        }
        t.a("Resume: ME");
        this.O = ((m)t.g()).a();
        t.a("Resume: meindex " + this.t);
        t.a("Resume: id " + this.s.v());
        t.a("Resume: score " + this.s.A());
        this.g = true;
    }
    
    public boolean f() {
        this.l.a(new u());
        E e;
        try {
            do {
                e = (E)this.a(E.class);
                if (e == null) {
                    break;
                }
                com.daysofwonder.util.t.a("req open: " + e.g());
            } while (e.g());
        }
        catch (ClassCastException ex) {
            com.daysofwonder.util.t.a(ex);
            return false;
        }
        if (e == null) {
            com.daysofwonder.util.t.a("ObserveFirstTable: req == null");
            return false;
        }
        this.P = (d)this.s;
        if (e instanceof E) {
            final Vector d = e.d();
            final int a = this.a(d, new I(this));
            if (a != -1) {
                try {
                    return this.b(Integer.valueOf(a));
                }
                catch (ClassCastException ex2) {}
            }
            final int a2 = this.a(d, new J(this));
            if (a2 != -1) {
                try {
                    return this.b(Integer.valueOf(a2));
                }
                catch (ClassCastException ex3) {}
            }
            final int a3 = this.a(d, new K(this));
            if (a3 != -1) {
                try {
                    return this.b(Integer.valueOf(a3));
                }
                catch (ClassCastException ex4) {}
            }
        }
        return false;
    }
    
    private int a(final Vector vector, final d d) {
        final Enumeration<com.daysofwonder.req.b> elements = vector.elements();
        while (elements.hasMoreElements()) {
            final com.daysofwonder.req.b b = elements.nextElement();
            final m m = (m)b.q;
            for (int i = 0; i < b.e.length; ++i) {
                if (b.e[i] == this.g().w()) {}
            }
            if (m.d()) {
                continue;
            }
            if (m.b() != 0) {
                continue;
            }
            if (d.a(b, m)) {
                return b.a;
            }
        }
        return -1;
    }
    
    private boolean a(final com.daysofwonder.req.b b) {
        return "us".equals(((m)b.q).a()) && b.d.length > 2 && b.j < 30;
    }
    
    public void b(final com.daysofwonder.req.t t) {
        this.az = (m)t.g();
        this.O = ((m)t.g()).a();
    }
    
    public void a(final A a) {
        if (a.d() != null) {
            System.out.println("Observing returned infos on players");
            final Enumeration<String> elements = (Enumeration<String>)a.d().elements();
            final Enumeration<Integer> elements2 = (Enumeration<Integer>)a.e().elements();
            final Enumeration<Integer> elements3 = (Enumeration<Integer>)a.f().elements();
            final Enumeration elements4 = a.g().elements();
            while (elements.hasMoreElements() && elements2.hasMoreElements() && elements3.hasMoreElements() && elements4.hasMoreElements()) {
                String string = elements.nextElement();
                if (string.length() > 15) {
                    string = string.substring(0, 15) + "...";
                }
                final int intValue = elements2.nextElement();
                final int intValue2 = elements3.nextElement();
                final d s = new d(string);
                s.h(intValue);
                s.k(intValue2);
                System.out.println("Player: " + string + "(" + intValue + ") score: " + intValue2);
                if (intValue == a.j()) {
                    System.out.println("we are: " + a.j());
                    this.t = this.r.size();
                    this.P = s;
                    this.s = s;
                }
                this.r.addElement(s);
                this.q.put(intValue, s);
            }
            this.R = a.k();
            System.out.println("current player id: " + this.R);
            this.x = true;
            this.w.a();
            final m m = (m)a.l();
            this.ax = m.d();
            this.O = m.a();
            com.daysofwonder.util.t.a("map: \"" + this.O + "\" nocards: " + this.ax);
        }
    }
    
    public com.daysofwonder.a.i a(final j j, final String s) {
        final d d = new d(aG.c(s));
        d.h(j.e());
        d.k(j.f());
        d.l(j.g());
        d.i(j.h());
        d.j(j.j());
        d.a(j.k());
        if (j.l()) {
            d.F();
        }
        d.b(j.m());
        return d;
    }
    
    public com.daysofwonder.a.i a(final D d) {
        String s = d.b();
        if (s.length() > 15) {
            s = s.substring(0, 15) + "...";
        }
        final d d2 = new d(aG.c(s));
        d2.h(d.c());
        d2.n(d.j());
        d2.k(d.h());
        d2.i(d.d());
        d2.j(d.f());
        d2.a(d.e());
        d2.b(d.g());
        if (d.i()) {
            d2.F();
        }
        d2.a(d.k());
        d2.a(d.l());
        d2.m(d.m());
        d2.a(d.n());
        d2.c(d.o());
        return d2;
    }
    
    public d a(final int n) {
        if (n == -1) {
            return null;
        }
        if (n >= 0 && n < G.T.length) {
            return this.q.get(G.T[n]);
        }
        return this.q.get(n);
    }
    
    public d g() {
        return this.P;
    }
    
    public boolean a(final d d) {
        return d.v() == this.R;
    }
    
    public d h() {
        return this.a(this.R);
    }
    
    public boolean i() {
        return this.P != null && this.P.v() == this.R;
    }
    
    public boolean j() {
        return this.a == 5;
    }
    
    public boolean k() {
        return this.a == 17;
    }
    
    public boolean l() {
        return this.a == 18;
    }
    
    public boolean m() {
        return this.a == 19;
    }
    
    public boolean n() {
        return this.a == 20;
    }
    
    public boolean o() {
        return this.a == 6;
    }
    
    public boolean p() {
        return this.a == 10;
    }
    
    public boolean q() {
        return this.a == 7 || this.a == 9;
    }
    
    public boolean r() {
        return this.a == 9;
    }
    
    public boolean s() {
        return this.a == 12;
    }
    
    public boolean t() {
        return this.a == 23;
    }
    
    public boolean u() {
        return this.a == 13 || this.a == 16;
    }
    
    public boolean v() {
        return this.a == 21;
    }
    
    public boolean w() {
        return this.a == 16;
    }
    
    public boolean x() {
        return this.a == 14;
    }
    
    public boolean y() {
        return this.a == 15;
    }
    
    public boolean z() {
        return this.a == 22;
    }
    
    public boolean A() {
        return this.a == 4;
    }
    
    public int B() {
        return this.a;
    }
    
    public d b(final int n) {
        if (n < 0 || n >= this.r.size()) {
            return null;
        }
        return this.r.elementAt(n);
    }
    
    public int b(final d d) {
        if (d == null) {
            return -1;
        }
        return this.r.indexOf(d);
    }
    
    public int C() {
        return this.t;
    }
    
    public i[] D() {
        return this.V;
    }
    
    public void a(final i i) {
        for (int j = 0; j < this.V.length; ++j) {
            if (this.V[j] == i) {
                this.V[j] = null;
                return;
            }
        }
    }
    
    public void b(final i i) {
        this.V[i.e()] = i;
    }
    
    public void a(final d d, final i i) {
        this.a(d, i, false);
    }
    
    public synchronized void a(final d d, final i aa, final boolean ai) {
        this.ai = ai;
        if (aa != null && this.i()) {
            if (aa.c() || this.Z || this.aN()) {
                this.R = -1;
                this.a = 6;
                this.Z = false;
            }
            else {
                this.a = 10;
                this.Z = true;
            }
            this.aa = aa;
            this.l.a(new com.daysofwonder.tt.req.h(this.s, aa.e()));
            aa.b(-1);
        }
    }
    
    private boolean aN() {
        com.daysofwonder.util.t.a("onlyOneCard: " + this.am);
        if (this.am == 0) {
            int n = 0;
            for (int i = 0; i < this.V.length; ++i) {
                final i j = this.V[i];
                com.daysofwonder.util.t.a("onlyOneCard: c=" + j + " i " + i);
                if (j != null && !j.c()) {
                    ++n;
                }
            }
            com.daysofwonder.util.t.a("onlyOneCard: nb=" + n);
            return n == 0;
        }
        return false;
    }
    
    public synchronized boolean E() {
        return this.Z;
    }
    
    public e F() {
        return this.U;
    }
    
    public boolean a(final com.daysofwonder.tt.c c) {
        if (this.P.e() > 0) {
            for (int i = 0; i < this.r.size(); ++i) {
                if (((d)this.r.elementAt(i)).a(c)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public boolean a(final o o) {
        if (this.P.d() >= o.d()) {
            for (int i = 0; i < this.r.size(); ++i) {
                final d d = this.r.elementAt(i);
                if (d.a(o, this.r.size())) {
                    return false;
                }
                if (d == this.P && o.b() && d.a(o.a(), this.r.size())) {
                    com.daysofwonder.util.t.a("player: me has already claimed the parallel route");
                    return false;
                }
            }
            return true;
        }
        com.daysofwonder.util.t.a("canClaim: not enough trains");
        return false;
    }
    
    public void a(final Integer n) {
        final n n2 = new n();
        final n n3 = new n();
        final n n4 = new n();
        com.daysofwonder.util.t.a("ClaimTunnel: " + this.aw);
        final i i = (i)this.au.a(n);
        com.daysofwonder.util.t.a("ClaimTunnel: " + i);
        if (this.P.a(this.av, this.aw.b(), i.b(), 0, n3, n4, n2) == 2 && n2.d() == this.av.d()) {
            this.h(6);
            this.a(6, 120, 0.0f, true);
            this.ac.a(this.av, true);
            this.P.b(n2);
            this.l.a(new com.daysofwonder.tt.req.h(this.P, n2, this.av));
            this.aw = null;
            this.av = null;
            this.au = null;
            com.daysofwonder.util.t.a("ENTERING TUNNEL STATE");
            this.a = 18;
            this.w.a();
            return;
        }
        throw new IllegalStateException("ClaimTunnel problem: h:" + n2 + " tunnel:" + this.av + " tc: " + this.au);
    }
    
    public void a(final o av, final i aw) {
        final n au = new n();
        final int a = this.P.a(av, aw, au);
        com.daysofwonder.util.t.a("BuildHand: " + au + " res: " + a);
        if (a == 3) {
            com.daysofwonder.util.t.a("ENTERING ASKING_TUNNEL_COLOR_STATE");
            this.P.b(aw);
            this.au = au;
            this.av = av;
            this.aw = aw;
            this.a = 22;
            this.w.a();
        }
        else if (a == 2 && au.d() == av.d()) {
            if (!av.j()) {
                this.h(6);
                this.a(6, 120, 0.0f, true);
                this.h(28);
                this.ac.a(av, true);
                if (this.M) {
                    try {
                        this.a(new e(au, av));
                    }
                    catch (InterruptedException ex) {
                        com.daysofwonder.util.t.a("Score Animation Interrupted");
                    }
                }
                this.P.a(au, av, null);
            }
            else {
                this.h(6);
                this.a(6, 120, 0.0f, true);
                this.ac.a(av, true);
                this.P.b(au);
            }
            this.l.a(new com.daysofwonder.tt.req.h(this.P, au, av));
            if (!av.j()) {
                this.R = -1;
                this.a = 6;
                this.w.a();
            }
            else {
                com.daysofwonder.util.t.a("ENTERING TUNNEL STATE");
                this.a = 18;
            }
        }
    }
    
    public void a(final com.daysofwonder.tt.c c, final i i) {
        final n a = this.P.a(c, i);
        if (a != null && a.d() == 4 - this.P.e()) {
            this.P.a(a, c, null);
            final com.daysofwonder.tt.req.h h = new com.daysofwonder.tt.req.h(this.P, a, c);
            this.a = 6;
            this.l.a(h);
            this.R = -1;
            this.w.a();
        }
    }
    
    public o c(final int n) {
        if (this.U == null) {
            return null;
        }
        return this.U.b(n);
    }
    
    public b d(final int n) {
        return this.W[n];
    }
    
    public void G() {
        this.l.a(new com.daysofwonder.tt.req.h(this.s));
        this.a = 7;
        this.w.a();
    }
    
    public void H() {
        this.l.a(new s());
    }
    
    public com.daysofwonder.util.G I() {
        return (this.a == 9) ? this.P.k() : this.ad;
    }
    
    public int J() {
        return Math.min((this.a == 9) ? this.U.f() : this.U.e(), this.I().a());
    }
    
    public void a(final Vector vector) {
        this.ae = (Vector)vector.clone();
        com.daysofwonder.util.t.a("fKept: " + this.ae);
        if (this.a != 9) {
            this.a = 8;
        }
        else {
            this.a = 11;
        }
        if (this.a == 8 || (this.a == 11 && !this.ay)) {
            this.ay = true;
            this.l.a(new com.daysofwonder.tt.req.d(this.ae, 0));
        }
        com.daysofwonder.util.t.a("Ticket State: " + this.a);
        this.w.a();
    }
    
    public n K() {
        return this.ao;
    }
    
    public int L() {
        return this.S;
    }
    
    public int M() {
        return this.am;
    }
    
    public int N() {
        return this.an;
    }
    
    public com.daysofwonder.util.G O() {
        final com.daysofwonder.util.G g = new com.daysofwonder.util.G();
        int a = 0;
        for (int i = 0; i < this.r.size(); ++i) {
            final d d = this.r.elementAt(i);
            if (d.A() >= a) {
                if (d.A() > a) {
                    g.b();
                }
                g.c(d);
                a = d.A();
            }
        }
        return g;
    }
    
    public boolean P() {
        for (int i = 0; i < this.r.size(); ++i) {
            final d d = this.r.elementAt(i);
            if (d != this.s && d.A() > this.s.A()) {
                return false;
            }
        }
        return true;
    }
    
    public boolean Q() {
        return this.aj;
    }
    
    public Vector R() {
        return this.af;
    }
    
    public Vector S() {
        return this.ag;
    }
    
    public int a(final String s, final String s2, final boolean b, final boolean b2, final int n, final int n2, final com.daysofwonder.a.n n3, final String o) {
        this.O = o;
        this.az = (m)n3;
        return super.a(s, s2, b, b2, n, n2, n3, this.O);
    }
    
    public boolean T() {
        return this.U != null && this.U.c();
    }
    
    public void b(final com.daysofwonder.tt.c aq, final i ar) {
        this.aq = aq;
        this.ar = ar;
    }
    
    public com.daysofwonder.tt.c U() {
        return this.aq;
    }
    
    public i V() {
        return this.ar;
    }
    
    public boolean W() {
        return this.U != null && this.a != 15 && this.a != 14;
    }
    
    public com.daysofwonder.util.G X() {
        return this.ab;
    }
    
    public void a(final com.daysofwonder.util.G ab) {
        this.ab = ab;
    }
    
    public void e(final int n) {
        this.l.a(new com.daysofwonder.tt.req.i(n));
        this.a = 20;
        this.w.a();
    }
    
    public int f(final int n) {
        int n2;
        int t;
        if (n == -1) {
            n2 = this.s.C();
            t = this.t;
        }
        else {
            n2 = this.b(n).C();
            t = n;
        }
        if (n2 == -1) {
            return t;
        }
        return n2;
    }
    
    public int g(final int n) {
        final d a = this.a(n);
        return (a.C() == -1) ? this.r.indexOf(a) : a.C();
    }
    
    public void a(final ae ap) {
        this.ap = ap;
    }
    
    public void h(final int n) {
        com.daysofwonder.util.t.a("Sound " + n + " to be played at " + System.currentTimeMillis());
        if (this.ap != null) {
            this.ap.a(n);
        }
    }
    
    public void i(final int n) {
        if (this.ap != null) {
            this.ap.b(n);
        }
    }
    
    public void a(final int n, final int n2, final float n3, final boolean b) {
        com.daysofwonder.util.t.a("Sound " + n + " start fade " + System.currentTimeMillis());
        if (this.ap != null) {
            this.ap.a(n, n2, n3, b);
        }
    }
    
    public int j(final int n) {
        return G.L[n];
    }
    
    public boolean Y() {
        return this.U == null || this.U.b();
    }
    
    public com.daysofwonder.a.j Z() {
        return this.U;
    }
    
    public void a(final aC ac) {
        this.ac.a(ac, 100, false, false);
    }
    
    public String aa() {
        return this.O;
    }
    
    public void a(final boolean m) {
        this.M = m;
    }
    
    public Object ab() {
        return this.at;
    }
    
    public n ac() {
        return this.au;
    }
    
    public boolean ad() {
        return (this.h && !this.ax) || !this.h;
    }
    
    public static c ae() {
        return G.K;
    }
    
    public m af() {
        return this.az;
    }
    
    public boolean a(final String s) {
        return this.s.G().a(s);
    }
    
    static {
        K = new c();
        L = new int[] { 0, 1, 2, 4, 7, 10, 15, 17, 21 };
        G.T = new Integer[10];
        for (int i = 0; i < 10; ++i) {
            G.T[i] = i;
        }
    }
}
