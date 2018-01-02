import com.daysofwonder.tt.d;
import com.daysofwonder.tt.n;
import com.daysofwonder.tt.c;
import com.daysofwonder.tt.i;
import java.awt.Dimension;
import com.daysofwonder.util.t;
import com.daysofwonder.applet.am;
import com.daysofwonder.util.UIProperties;
import com.daysofwonder.applet.y;
import com.daysofwonder.b.b;
import com.daysofwonder.applet.ap;

// 
// Decompiled by Procyon v0.5.30
// 

public abstract class z extends ap
{
    protected G m;
    protected b[] n;
    protected b[] o;
    protected b[] p;
    protected b[] q;
    protected b[] r;
    protected b[] s;
    protected b[] t;
    protected b[] u;
    protected b v;
    protected b w;
    protected b x;
    protected b y;
    protected static final String[] z;
    protected static final String[] A;
    protected static final String[] B;
    protected static final String[] C;
    
    public z(final G m, final int n, final int n2) {
        super(n, n2);
        this.m = m;
    }
    
    public void b() {
        super.b();
    }
    
    public void k() {
        for (int i = 0; i < this.n.length; ++i) {
            this.n[i] = com.daysofwonder.applet.y.a("img/" + z.z[i + 10]);
            this.p[i] = com.daysofwonder.applet.y.a("img/" + z.z[i]);
            this.o[i] = this.n[i];
        }
    }
    
    public void e() {
        this.n = new b[10];
        this.p = new b[10];
        this.o = new b[10];
        this.k();
        this.q = new b[z.A.length];
        this.r = new b[z.A.length];
        this.s = new b[z.A.length];
        for (int i = 0; i < this.q.length; ++i) {
            this.q[i] = com.daysofwonder.applet.y.a("img/" + z.A[i]);
            this.r[i] = com.daysofwonder.applet.y.a(this.q[i]);
            this.s[i] = com.daysofwonder.applet.y.b(this.q[i]);
        }
        this.t = new b[z.B.length];
        for (int j = 0; j < z.B.length; ++j) {
            if (z.B[j] != null) {
                this.t[j] = com.daysofwonder.applet.y.a("img/small_" + z.B[j]);
            }
        }
        this.u = new b[z.C.length];
        for (int k = 0; k < z.C.length; ++k) {
            if (z.C[k] != null) {
                this.u[k] = com.daysofwonder.applet.y.a("img/med_" + z.C[k]);
            }
        }
        this.v = com.daysofwonder.applet.y.a("img/traincardback.gif");
        this.w = com.daysofwonder.applet.y.a("img/city.gif");
        this.x = com.daysofwonder.applet.y.a("img/smallcity.gif");
    }
    
    public b[] l() {
        return this.n;
    }
    
    public b[] m() {
        return this.o;
    }
    
    public b a(final int n) {
        if (n == -1) {
            return this.v;
        }
        return this.q[n];
    }
    
    public b b(final int n) {
        if (n == -1) {
            return this.v;
        }
        return this.u[n];
    }
    
    public b c(final int n) {
        if (n == -1) {
            return this.v;
        }
        return this.r[n];
    }
    
    public b d(final int n) {
        if (n == -1) {
            return this.v;
        }
        return this.s[n];
    }
    
    public am a(final String s, final String s2, final UIProperties uiProperties, final UIProperties uiProperties2) {
        if (s.equalsIgnoreCase("player")) {
            return new p(this, s2, uiProperties, uiProperties2);
        }
        if (s.equalsIgnoreCase("other")) {
            return new u(this, s2, uiProperties, uiProperties2);
        }
        if (s.equalsIgnoreCase("deck")) {
            return new O(this, s2, uiProperties, uiProperties2);
        }
        if (s.equalsIgnoreCase("board")) {
            return new A(this, s2, uiProperties, uiProperties2);
        }
        if (s.equalsIgnoreCase("tickets")) {
            return new v(this, s2, uiProperties, uiProperties2);
        }
        if (s.equalsIgnoreCase("ticketchooser")) {
            return new f(this, s2, uiProperties, uiProperties2);
        }
        if (s.equalsIgnoreCase("start")) {
            return new as(this, s2, uiProperties, uiProperties2);
        }
        if (s.equalsIgnoreCase("score")) {
            return new N(this, s2, uiProperties, uiProperties2);
        }
        if (s.equalsIgnoreCase("deckicon")) {
            return new o(this, s2, uiProperties, uiProperties2);
        }
        if (s.equalsIgnoreCase("tunnel")) {
            return new h(this, s2, uiProperties, uiProperties2);
        }
        if (s.equalsIgnoreCase("colorcard")) {
            return new E(this, s2, uiProperties, uiProperties2);
        }
        com.daysofwonder.util.t.e("[CRITICAL] unknown control type: " + s + " name: " + s2);
        return null;
    }
    
    public synchronized void g() {
        this.E();
        super.g();
    }
    
    public synchronized void a(final Dimension dimension) {
        if (!this.ae) {
            return;
        }
        super.a(dimension);
    }
    
    public G n() {
        return this.m;
    }
    
    public String a(final int n, final Object[] array) {
        return this.a(this.V.b("help." + n), array);
    }
    
    public String a(final String s, final Object[] array) {
        if (s != null) {
            final StringBuffer sb = new StringBuffer();
            for (int i = 0; i < s.length(); ++i) {
                final char char1 = s.charAt(i);
                if (char1 == '%') {
                    if (i < s.length() - 1) {
                        final char char2 = s.charAt(++i);
                        if (Character.isDigit(char2)) {
                            final char c = (char)(char2 - '0');
                            if (array.length > c) {
                                if (array[c] instanceof String) {
                                    sb.append((String)array[c]);
                                }
                                else if (array[c] instanceof i) {
                                    sb.append(this.V.b("card." + ((i)array[c]).b()));
                                }
                                else if (array[c] instanceof com.daysofwonder.tt.o) {
                                    final com.daysofwonder.tt.o o = (com.daysofwonder.tt.o)array[c];
                                    sb.append(o.e().a()).append(" - ").append(o.f().a());
                                }
                                else if (array[c] instanceof c) {
                                    sb.append(((c)array[c]).a());
                                }
                                else if (array[c] instanceof n) {
                                    final n n = (n)array[c];
                                    final int f = n.f();
                                    final int g = n.g();
                                    final i h = n.h();
                                    if (f > 0) {
                                        sb.append(f).append(" ").append(this.V.b("card.9"));
                                    }
                                    else if (g > 0) {
                                        sb.append(g).append(" ").append(this.V.b("card.10"));
                                    }
                                    if ((n.d() > f || n.d() > g) && h != null && !h.c() && !h.d()) {
                                        if (f > 0 || g > 0) {
                                            sb.append(" ").append(this.V.b("and")).append(" ");
                                        }
                                        sb.append(n.d() - f - g).append(" ").append(this.V.b("card." + h.b()));
                                    }
                                }
                                else if (array[c] instanceof com.daysofwonder.util.G) {
                                    final com.daysofwonder.util.G g2 = (com.daysofwonder.util.G)array[c];
                                    for (int j = 0; j < g2.a(); ++j) {
                                        if (g2.b(j) instanceof d) {
                                            if (j > 0) {
                                                sb.append(", ");
                                            }
                                            sb.append(((d)g2.b(j)).z());
                                        }
                                        else if (g2.b(j) instanceof com.daysofwonder.tt.f) {
                                            if (j > 0) {
                                                sb.append(", ");
                                            }
                                            sb.append(((com.daysofwonder.tt.f)g2.b(j)).f().a()).append("-").append(((com.daysofwonder.tt.f)g2.b(j)).g().a());
                                        }
                                    }
                                }
                                else if (array[c] != null) {
                                    sb.append(array[c].toString());
                                }
                            }
                        }
                        else if (char2 == '%') {
                            sb.append(char1);
                        }
                        else {
                            sb.append(char1).append(char2);
                        }
                    }
                    else {
                        sb.append(char1);
                    }
                }
                else {
                    sb.append(char1);
                }
            }
            return sb.toString();
        }
        return "";
    }
    
    public b o() {
        return this.y;
    }
    
    static {
        z = new String[] { "std0.gif", "std1.gif", "std2.gif", "std3.gif", "std4.gif", "std5.gif", "std6.gif", "std7.gif", "std8.gif", "std9.gif", "big0.gif", "big1.gif", "big2.gif", "big3.gif", "big4.gif", "big5.gif", "big6.gif", "big7.gif", "big8.gif", "big9.gif" };
        A = new String[] { "purple.gif", "white.gif", "blue.gif", "yellow.gif", "brown.gif", "black.gif", "red.gif", "green.gif", "loco.gif", "tunnel.gif" };
        B = new String[] { null, "purple.gif", "white.gif", "blue.gif", "yellow.gif", "brown.gif", "black.gif", "red.gif", "green.gif", "loco.gif", "tunnel.gif" };
        C = new String[] { "purple.gif", "white.gif", "blue.gif", "yellow.gif", "brown.gif", "black.gif", "red.gif", "green.gif", "loco.gif", "tunnel.gif" };
    }
}
