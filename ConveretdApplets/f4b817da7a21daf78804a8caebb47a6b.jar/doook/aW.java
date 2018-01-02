// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.MenuItem;
import java.awt.Component;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.IOException;
import java.net.NoRouteToHostException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.Random;
import java.net.InetAddress;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Image;
import java.awt.Frame;

public abstract class aW extends be
{
    private b a;
    
    protected void a() {
        super.M = super.L;
        super.L = "00000000-0000-0000-0000-000000000000";
        be.a = null;
        super.c.b();
        super.d.b();
        super.e.b();
        super.f.b();
        super.g.b();
        super.h.b();
        super.i.b();
        super.k.b();
        super.l.b();
        super.m.b();
        super.r.b();
        super.s.b();
        if (super.b != null) {
            for (int i = 0; i < super.b.a(); ++i) {
                ((bc)super.b.a(i)).dispose();
            }
        }
        this.l();
        if (super.a != null) {
            final Frame a = super.a.a();
            super.a.c();
            super.a = null;
            if (this.a != null) {
                this.a.setVisible(false);
            }
            this.a = null;
            if (a != null) {
                a.dispose();
            }
        }
        try {
            Thread.sleep(500L);
        }
        catch (InterruptedException ex) {}
        if (super.a != null) {
            try {
                super.a.close();
                super.a.close();
                super.a.close();
            }
            catch (Exception ex2) {}
        }
        super.a = null;
        super.a = null;
        super.a = null;
        super.G = true;
    }
    
    public void c(final V v) {
        switch (v.a()) {
            case 67585: {
                this.v(v);
                break;
            }
            case 65794: {
                this.f(v);
                break;
            }
            case 66049: {
                this.s(v);
                break;
            }
            case 66305: {
                this.j(v);
                break;
            }
            case 66308: {
                this.g(v);
                break;
            }
            case 66306: {
                this.i(v);
                break;
            }
            case 66307: {
                this.r(v);
                break;
            }
            case 66561: {
                this.w(v);
                break;
            }
            case 66816:
            case 50400771: {
                this.t(v);
                break;
            }
            case 66817: {
                this.x(v);
                break;
            }
            case 67073: {
                this.k(v);
                break;
            }
            case 67074: {
                this.h(v);
                break;
            }
            case 67329: {
                this.l(v);
                break;
            }
            case 67330: {
                this.A(v);
                break;
            }
            case 67331: {
                this.n(v);
                break;
            }
            case 67333: {
                this.y(v);
                break;
            }
            case 67334: {
                if (v.a(0, 0) == this.b()) {
                    this.a(v.a(0));
                    super.ag = v.a(0, 3);
                    super.ah = v.a(0, 6);
                }
                this.p(v);
                break;
            }
            case 67584: {
                this.e(v);
                break;
            }
            case 67586: {
                this.a(v);
                break;
            }
            case 67843: {
                this.o(v);
                break;
            }
            case 68608: {
                this.u(v);
                break;
            }
            case 67338: {
                this.z(v);
                break;
            }
            case 67341: {
                this.B(v);
                break;
            }
            case 33621775: {
                this.m(v);
                break;
            }
            case 263168: {
                aI.a(new Object[] { (v.a(0, 0) == null) ? "" : v.a(0, 0) });
                break;
            }
            case 67342: {
                this.D(v);
                break;
            }
            case 264192: {
                this.E(v);
                break;
            }
            case 327680: {
                this.q(v);
                break;
            }
            case 327936: {
                this.d(v);
                break;
            }
        }
    }
    
    public boolean i() {
        return super.p;
    }
    
    public Image a(final String s, final boolean b, final int n) {
        return this.a(s, b, n, true);
    }
    
    public Image a(final String s, final boolean b, final int n, final boolean b2) {
        try {
            URL url;
            if (b || be.a(s)) {
                url = new URL(super.c, "Resources/" + super.A + "/" + s);
            }
            else {
                url = new URL(super.c, "Resources/" + s);
            }
            final Image a = this.a(url);
            if (a != null) {
                super.b.addImage(a, b2 ? n : super.u);
                super.b.statusID(b2 ? n : super.u, true);
                if (!b2) {
                    ++super.u;
                }
            }
            return a;
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    public abstract Image a(final URL p0);
    
    public void m() {
    }
    
    public AudioClip[] a(final URL url) {
        return null;
    }
    
    public abstract void a(final String p0, final String p1, final M p2, final String p3, final int p4);
    
    public void a(final String s, final String s2, final M m, final URL c, final int n, final String s3) {
        super.aa = 0;
        try {
            this.j();
            super.H = false;
            super.i.b();
            if (t.l != null) {
                super.N = t.l;
            }
            else {
                super.N = c.getHost();
            }
            InetAddress[] allByName = InetAddress.getAllByName(super.N);
            if (allByName == null || allByName.length == 0) {
                allByName = new InetAddress[] { InetAddress.getByName(super.N) };
            }
            super.f = new String[allByName.length];
            for (int i = 0; i < allByName.length; ++i) {
                super.f[i] = allByName[i].getHostAddress();
            }
            if (super.f.length > 1) {
                final Random random = new Random();
                for (int j = 0; j < super.f.length; ++j) {
                    int n2 = random.nextInt() % super.f.length;
                    if (n2 < 0) {
                        n2 *= -1;
                    }
                    final String s4 = super.f[n2];
                    super.f[n2] = super.f[j];
                    super.f[j] = s4;
                }
            }
            super.c = c;
            this.f(s);
            Label_0271: {
                if (super.a == null) {
                    if (doook.F.a < 66048) {
                        if (doook.F.c) {
                            break Label_0271;
                        }
                    }
                    try {
                        super.a = this.a(super.c);
                    }
                    catch (Throwable t) {
                        super.a = null;
                    }
                }
            }
            if (t.g != null) {
                super.f = new String[] { t.g };
            }
            if (t.j != 0) {
                for (int k = 0; k < bu.c.length; ++k) {
                    if (t.j == bu.a(k)) {
                        bu.b(k);
                        break;
                    }
                }
            }
            if (this.j()) {
                if (super.a != null && super.a.isAlive()) {
                    super.a.suspend();
                }
                if (super.e != null && super.e.isAlive()) {
                    super.e.suspend();
                }
                super.a = new bd(this);
                super.e = new Thread(this, "Decoder");
                super.G = false;
                super.e.start();
                int n3 = 0;
                String string;
                try {
                    string = aI.a(new Object[] { "encodeURIComponent(document.referrer)" }).toString();
                }
                catch (Exception ex7) {
                    string = "";
                }
                String string2;
                try {
                    string2 = aI.a(new Object[] { "encodeURIComponent(navigator.userAgent)" }).toString();
                }
                catch (Exception ex8) {
                    string2 = "";
                }
                String string3 = "undefined";
                try {
                    while (n3 < 10 && string3.equalsIgnoreCase("undefined")) {
                        string3 = aI.a(new Object[] { "document.doookID" }).toString();
                        if (string3.equalsIgnoreCase("undefined")) {
                            try {
                                Thread.sleep(100L);
                                ++n3;
                            }
                            catch (InterruptedException ex9) {}
                        }
                    }
                    if (string3.equalsIgnoreCase("undefined")) {
                        string3 = "";
                    }
                }
                catch (Exception ex10) {
                    string3 = "";
                }
                if (string3.equalsIgnoreCase("undefined")) {
                    string3 = "";
                }
                final String string4 = "r=" + string + "&a=" + string2 + "&" + string3;
                final V v = new V(65793, 1);
                v.a(0, 0, n);
                v.a(0, 1, aP.b(s));
                v.a(0, 0, s);
                v.a(0, 2, t.b);
                v.a(0, 1, aI.a(new Object[] { "__Q['f']" }).toString());
                v.a(0, 3, s2);
                v.a(0, 4, string4);
                v.a(0, 0, m);
                if (!this.k() && super.F != -999) {
                    v.a(0, 2);
                }
                if (this.c(23)) {
                    v.a(0, 23);
                }
                if (this.m()) {
                    v.a(0, 3);
                }
                if (super.e && (!doook.F.b || doook.F.f != 1)) {
                    v.a(0, 21);
                    v.a(0, 20);
                }
                this.F(v);
            }
            else {
                System.err.println("acknowledge() failed!!!");
                this.a();
            }
        }
        catch (UnknownHostException ex) {
            this.l();
            new E(super.f, aC.a(aG.a("%1 could not locate the specified host. Please make sure you are using the correct host name."), new String[] { t.a }), ex, this).setVisible(true);
        }
        catch (InterruptedIOException ex2) {
            this.l();
            new E(super.f, aC.a(aG.a("No response was received from the server.  The %1 Server may not be running, or may not be a compatible version. Please contact the administrator in charge of the site."), new String[] { t.a }), ex2, this).setVisible(true);
        }
        catch (NoRouteToHostException ex3) {
            this.l();
            new E(super.f, aC.a(aG.a("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { t.a }), ex3, this).setVisible(true);
        }
        catch (SecurityException ex4) {
            this.l();
            new E(super.f, aC.a(aG.a("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { t.a }), ex4, this).setVisible(true);
        }
        catch (IOException ex5) {
            ex5.printStackTrace();
            this.l();
            new E(super.f, aC.a(aG.a("%1 could not connect to the specified host.  Please verify that the %1 Server is running and that you are using the correct host name."), new String[] { t.a }), ex5, this).setVisible(true);
        }
        catch (Exception ex6) {
            ex6.printStackTrace();
            this.l();
            final StringWriter stringWriter = new StringWriter();
            ex6.printStackTrace(new PrintWriter(stringWriter));
            new E(super.f, aC.a(aG.a("An unknown error occurred while connecting to the %1 Server.  Please contact the administrator in charge of the site."), new String[] { t.a }), stringWriter.toString(), this).setVisible(true);
        }
        super.c = false;
        super.d = false;
        super.i = false;
    }
    
    private void e(final String s) {
        try {
            new Socket(s, 53).close();
        }
        catch (Exception ex) {}
    }
    
    private boolean a(final String s, final int n) {
        int n2;
        if (doook.F.b && doook.F.a < 65792 && doook.F.f == 1) {
            n2 = 2;
        }
        else {
            n2 = 256;
        }
        try {
            super.a = new Socket(s, n);
            super.a = new aT(new BufferedOutputStream(super.a.getOutputStream(), 256));
            super.a = new aL(new BufferedInputStream(super.a.getInputStream(), n2));
            this.n();
            return this.a();
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    private boolean j() {
        boolean b = false;
        t.j = 8396;
        for (int z = 0; !b && z < super.f.length; ++z) {
            super.Z = z;
            try {
                b = this.a(super.f[z], t.j);
            }
            catch (Exception ex2) {
                if (z != super.f.length - 1) {
                    try {
                        Thread.sleep(200L);
                    }
                    catch (InterruptedException ex3) {}
                }
            }
        }
        for (int z2 = 0; !b && z2 < super.f.length; ++z2) {
            super.Z = z2;
            this.e(super.f[z2]);
            for (int n = 0; !b && n < bu.c.length; ++n) {
                final int a = bu.a(n);
                if (a != bu.a) {
                    t.j = a;
                    try {
                        b = this.a(super.f[z2], a);
                    }
                    catch (Exception ex) {
                        if (z2 == super.f.length - 1 && n == bu.c.length - 1) {
                            throw ex;
                        }
                        try {
                            Thread.sleep(200L);
                        }
                        catch (InterruptedException ex4) {}
                    }
                }
            }
        }
        return b;
    }
    
    private void n() {
        try {
            final aY ay = new aY();
            final long a = ay.a(ay.b());
            super.a.writeInt(super.J);
            super.a.writeLong(a);
            super.a.flush();
            final long long1 = super.a.readLong();
            super.a.a(aY.a(a));
            super.a.a(aY.a(long1));
        }
        catch (IOException ex) {}
    }
    
    public void a(final URL url, final String s) {
    }
    
    public void e(final int n) {
        if (super.a != null && n > 0 && n <= super.a.length && super.a[n - 1] != null) {
            try {
                super.a[n - 1].stop();
                super.a[n - 1].play();
            }
            catch (Throwable t) {}
        }
    }
    
    public void a(final String s, final aI ai, final int n, final int n2, final boolean b, final long n3, final int n4, final int n5, final int n6, final boolean b2, final boolean b3, final boolean b4) {
        this.a(s, ai, n, n2, b, false, n3, n4, null, n5, n6, b2, b3, b4);
    }
    
    public void a(final String s, final aI ai, final int n, final int n2, final boolean b, final boolean b2, final long n3, final int n4, final byte[] a, final int b3, final int x, final boolean d, final boolean e, final boolean f) {
        if (super.a != null) {
            int b4 = ai.b();
            if (b4 == this.b()) {
                b4 = n;
            }
            ai.g();
            final aN an = new aN(this.a(s, ai), ai, n != -1 && n != -3 && n != -2 && !b, (bh)super.c.b(ai.v));
            an.a(n == -3 || n == -2);
            an.a(n3, n4);
            if (b2) {
                an.g = a[0];
            }
            else {
                an.a = a;
            }
            an.B = b3;
            an.x = x;
            an.D = d;
            an.E = e;
            an.F = f;
            if (an.p) {
                final aA aa = (aA)super.b.b(b4);
                if (aa != null) {
                    aa.a(an);
                }
                else if (super.K) {
                    this.a(an, ai);
                }
                else {
                    super.a.a(an);
                }
            }
            else {
                an.l = ai.u;
                if (n == -3 || n == -2) {
                    this.a(an);
                }
                else {
                    super.a.a(an);
                }
            }
        }
    }
    
    protected void d(final V v) {
        super.s.a(true);
        try {
            for (int i = 0; i < v.d(); ++i) {
                final int a = v.a(i, 0);
                av av = (av)super.s.b(a);
                if (v.a(i, 63)) {
                    if (av != null) {
                        super.s.b(a);
                    }
                }
                else {
                    if (av == null) {
                        av = new av(a, v.a(i, 0));
                        super.s.a(av, a);
                    }
                    else {
                        av.f(v.a(i, 0));
                    }
                    av.a(v.a(i));
                    av.b = v.a(i, 1);
                    av.Q = v.a(i, 2);
                    av.e = v.a(i, 1);
                    try {
                        if (av.Q != null && av.Q.length() > 0 && !bq.h.containsKey(av.Q)) {
                            final Image a2 = this.a("stars/" + av.Q, true, 15);
                            if (a2 != null) {
                                bq.h.put(av.Q, a2);
                            }
                        }
                    }
                    catch (Exception ex) {
                        System.out.println("error loading star image: " + ex.getMessage());
                    }
                }
            }
        }
        finally {
            super.s.c();
        }
    }
    
    protected void e(final V v) {
        this.d(v.j);
        this.a(v.a(0));
        super.A = v.a(0, 0);
        t.a = v.a(0, 1);
        be.e[0] = aG.a("Bass");
        be.e[1] = aG.a("Bell");
        be.e[2] = aG.a("Castanet");
        be.e[3] = aG.a("Chime");
        be.e[4] = aG.a("Conga");
        be.e[5] = aG.a("Cow Bell");
        be.e[6] = aG.a("Double Bell");
        be.e[7] = aG.a("Drum Roll");
        be.e[8] = aG.a("Harp");
        if (!"Admin".equals(super.A)) {
            if (super.c.e()) {
                super.c.n = this.a(super.c.f() + "background.gif", true);
            }
            if (super.c.f()) {
                super.c.h = this.a(super.c.f() + "chatbackground.gif", true);
            }
            if (t.e) {
                (super.a = new O(this, null)).setVisible(false);
                t.a.setVisible(false);
                t.a.add(super.a.b());
                ((O)super.a).validate();
            }
            else {
                super.a = (v)new c(this).a();
            }
            this.m();
        }
    }
    
    public void o() {
        if (super.a != null) {
            ((aV)super.a).a().b();
        }
    }
    
    protected void a(final V v) {
        super.c = true;
        super.d = true;
        super.w = -999;
        this.m();
        if (super.c.b(super.G) == null) {
            this.a(super.E);
        }
        else {
            this.a(super.G);
        }
        final ax ax = (ax)super.e.b(super.F);
        final ax ax2 = (ax)super.e.b(super.D);
        if (ax == null || (ax.a == null && ax2.a != null)) {
            this.a(ax2);
        }
        else {
            this.a(ax);
        }
        if (super.a != null) {
            ((aV)super.a).a().b();
        }
    }
    
    protected void p() {
        if (!ao.h) {
            return;
        }
        if (!super.c && !super.d) {
            if (super.a != null) {
                ((aV)super.a).a().b();
            }
            int w;
            if (super.e.b(super.F) == null) {
                w = super.D;
            }
            else {
                w = super.F;
            }
            super.d = true;
            final ax ax = (ax)super.e.b(w);
            if (ax != null && ax.f && ax.a != null) {
                super.w = -999;
                new Z(super.a.a(), this, ax).setVisible(true);
            }
            super.w = w;
        }
    }
    
    protected void a(final aq aq, final int n, final long n2, final int n3) {
        final int w = aq.w;
        final ax ax = (ax)super.e.b(n);
        final ax ax2 = (ax)super.e.b(aq.w);
        this.c(26);
        this.p();
        if (aq.b() == this.b()) {
            if (ax2 != null) {
                ax2.e = false;
            }
            ax.e = true;
            super.w = n;
            if (super.a != null) {
                if (!super.H) {
                    this.a(false);
                }
                super.a.b(ax);
                if (!super.a.isVisible()) {
                    super.a.setVisible(true);
                }
                super.a.b().validate();
            }
        }
        if (ax != null) {
            ax.a = this.a(ax.b());
            if (ax2 != null && ax != null) {
                if (ax2.b() != ax.b() && (!aq.c(23) || this.c(24))) {
                    final ax ax3 = ax;
                    ++ax3.a;
                }
            }
            else if (ax2 == null && ax != null && (!aq.c(23) || this.c(24))) {
                final ax ax4 = ax;
                ++ax4.a;
            }
            if (super.a != null) {
                super.a.c(ax);
            }
        }
        if (ax2 != null) {
            ax2.a = this.a(ax2.b());
            if (ax2 != null && ax != null && ax2.b() != ax.b() && (!aq.c(23) || this.c(24))) {
                final ax ax5 = ax2;
                --ax5.a;
            }
            if (super.a != null) {
                super.a.c(ax2);
            }
        }
        if (n == super.w && aq.w != n) {
            if (ax != null && !ax.c(57) && (!aq.c(23) || this.c(24))) {
                final aN an = new aN(aC.a(aG.a("(This user has entered %1)"), new String[] { this.c(ax.g()) }), aq, false, (bh)super.c.b(aq.v));
                an.a(n2, n3);
                this.e(super.N);
                if (super.a != null) {
                    super.a.a(an);
                }
            }
        }
        else if (aq.w == super.w && n != super.w && ax2 != null && ax != null && !ax2.c(57) && (!aq.c(23) || this.c(24))) {
            final aN an2 = new aN(aC.a(aG.a("(This user has moved to %1)"), new String[] { this.c(ax.g()) }), aq, false, (bh)super.c.b(aq.v));
            an2.a(n2, n3);
            this.e(super.R);
            if (super.a != null) {
                super.a.a(an2);
            }
        }
        aq.w = n;
        if (super.a != null) {
            if ((super.H || n == super.w) && this.a(aq.g(), doook.x.a.getText())) {
                super.a.a(aq, true);
            }
            else if (!super.H && w == super.w) {
                super.a.a(aq);
            }
        }
    }
    
    protected void f(final V v) {
        this.c(26);
        this.p();
        for (int i = 0; i < v.d(); ++i) {
            final aq aq = (aq)super.d.b(v.a(i, 0));
            if (aq != null) {
                final ax ax = (ax)super.e.b(aq.w);
                final at at = new at(aq.b(), aq.g());
                at.h = false;
                if (ax != null) {
                    if (!aq.c(23) || this.c(24)) {
                        final ax ax2 = ax;
                        --ax2.a;
                    }
                    if (super.a != null) {
                        super.a.c(ax);
                    }
                    if (aq.w == super.w && !ax.c(57) && (!aq.c(23) || this.c(24))) {
                        final String a = v.a(i, 0);
                        String s;
                        if (a == null) {
                            s = aC.a(aG.a("(This user has left %1)"), new String[] { t.a });
                        }
                        else {
                            s = this.c(a) + " " + aC.a(aG.a("(This user has left %1)"), new String[] { t.a });
                        }
                        final aN an = new aN(s, aq, false, (bh)super.c.b(aq.v));
                        an.a(v.a(), v.h());
                        this.e(super.R);
                        if (super.a != null) {
                            super.a.a(an);
                        }
                    }
                }
                if (super.a != null) {
                    super.a.a(aq);
                    ((aV)super.a).a(at, false, false);
                }
                final aA aa = (aA)super.b.b(aq.b());
                if (aa != null) {
                    aa.dispose();
                }
                super.d.a(true);
                try {
                    super.d.b(aq);
                }
                finally {
                    super.d.c();
                }
            }
        }
    }
    
    protected void g(final V v) {
        for (int i = 0; i < v.d(); ++i) {
            if (v.b() != null) {
                this.a(v.b());
            }
            aI ai = (aI)super.d.b(v.a(i, 0));
            if (ai == null && this.m() && v.a(0, 3)) {
                ai = new aI(-999, v.a(0, 1));
                ai.v = v.a(0, 1);
            }
            if (ai == null) {
                ai = new aI(-999, "Guest");
                ai.v = super.E;
            }
            if (ai != null) {
                int n = ai.b();
                if (n == this.b()) {
                    n = v.j;
                }
                if (!ai.v) {
                    switch (v.j) {
                        case -3:
                        case -2:
                        case -1: {
                            if (ai.u) {
                                this.e(super.P);
                                break;
                            }
                            this.e(super.O);
                            break;
                        }
                        default: {
                            if (super.b.a(n)) {
                                this.e(super.O);
                                break;
                            }
                            this.e(super.Q);
                            break;
                        }
                    }
                    this.a(v.a(i, 0), ai, v.j, v.u, true, v.a(), v.h(), v.a(i, 2), v.a(i, 3), v.a(i, 37), v.a(i, 38), v.a(i, 39));
                }
            }
        }
    }
    
    protected void h(final V v) {
        final aI ai = (aI)super.d.b(v.a(0, 0));
        if (ai != null) {
            final V v2 = new V(67073, 1);
            v2.j = ai.b();
            v2.a(0, 0, this.b());
            if (!super.L || ai.c(33)) {
                v2.a(0, 1, super.K);
                if (super.D != null && super.D.length() > 0) {
                    v2.a(0, 0, super.D);
                }
                if (super.H != null && super.H.length() > 0) {
                    v2.a(0, 1, super.H);
                }
                if (super.E != null && super.E.length() > 0) {
                    v2.a(0, 2, super.E);
                }
                if (super.F != null && super.F.length() > 0) {
                    v2.a(0, 3, super.F);
                }
                if (super.L != -999) {
                    v2.a(0, super.L);
                }
            }
            this.F(v2);
        }
    }
    
    protected void i(final V v) {
        this.c(26);
        if (super.a != null) {
            for (int i = 0; i < v.d(); ++i) {
                final aN an = new aN(this.c(v.a(i, 1)), this.c(v.a(i, 0)), false, (bh)super.c.b(v.a(i, 0)), -999, false, false);
                an.A = v.a(i, 1);
                an.y = v.a(i, 2);
                an.z = v.a(i, 3);
                an.B = v.a(i, 6);
                an.x = v.a(i, 7);
                an.B = v.a(i, 31);
                an.C = v.a(i, 36);
                an.D = v.a(i, 37);
                an.E = v.a(i, 38);
                an.F = v.a(i, 39);
                an.a(v.a(), v.h());
                super.a.a(an);
                if (v.j == this.b()) {
                    this.e(super.Q);
                }
                else {
                    this.e(super.O);
                }
            }
        }
    }
    
    protected void j(final V v) {
        this.p();
        for (int i = 0; i < v.d(); ++i) {
            aI ai = (aI)super.d.b(v.a(i, 0));
            if (ai == null && this.m() && v.a(0, 3)) {
                ai = new aI(-999, v.a(0, 1));
                ai.v = v.a(0, 1);
            }
            if (ai != null) {
                int n = ai.b();
                if (n == this.b()) {
                    n = v.j;
                }
                if (v.j != this.b() && v.a(0, 20)) {
                    return;
                }
                if (!ai.v) {
                    if (!v.k) {
                        switch (v.j) {
                            case -3:
                            case -2:
                            case -1: {
                                if (ai.u) {
                                    this.e(super.P);
                                    break;
                                }
                                this.e(super.O);
                                break;
                            }
                            default: {
                                if (super.b.a(n)) {
                                    this.e(super.O);
                                    break;
                                }
                                this.e(super.Q);
                                break;
                            }
                        }
                    }
                    this.a(v.a(i, 0), ai, v.j, v.u, false, v.a(), v.h(), v.a(i, 2), v.a(i, 3), v.a(i, 37), v.a(i, 38), v.a(i, 39));
                }
            }
        }
    }
    
    protected void k(final V v) {
        for (int i = 0; i < v.d(); ++i) {
            final aI ai = (aI)super.d.b(v.a(i, 0));
            if (ai != null) {
                if (super.O) {
                    final String a = v.a(i, 2);
                    if (a != null) {
                        try {
                            this.a(new URL(a), "_blank");
                        }
                        catch (MalformedURLException ex) {}
                    }
                }
                else {
                    new B(super.a.a(), this, ai, v, i);
                }
            }
        }
    }
    
    protected void l(final V v) {
        super.k = v.a(-1);
        super.f.a(true);
        try {
            for (int i = 0; i < v.d(); ++i) {
                final int a = v.a(i, 0);
                z z = (z)super.f.b(a);
                if (v.a(i, 63)) {
                    if (z != null) {
                        super.f.b(a);
                    }
                }
                else {
                    if (z == null) {
                        z = new z(a, v.a(i, 0));
                        super.f.a(z);
                    }
                    else {
                        z.f(v.a(i, 0));
                    }
                    z.b = v.a(i, 1);
                    z.c = v.a(i, 2);
                    z.b = v.a(i, 1);
                    z.a = v.a(i, 2);
                    z.c = v.a(i, 3);
                    z.a(v.a(i));
                    z.f = this.a("banners/" + z.a, true, 30);
                    if (super.a != null) {
                        ((aV)super.a).a(z);
                    }
                }
            }
        }
        finally {
            super.f.c();
        }
        this.o();
    }
    
    protected void m(final V v) {
        for (int d = v.d(), i = 0; i < d; ++i) {
            final ba ba = new ba(v.a(i, 0), v.a(i, 0));
            ba.a(v.a(i));
            ba.b = v.a(i, 1);
            if (!ba.c(63)) {
                ba.b = this.a("emoticons/" + ba.b, true, 50);
            }
            doook.ba.a(ba);
        }
    }
    
    protected void n(final V v) {
        bq.a[0] = this.a("status/status_1.gif", false, 16);
        bq.a[1] = this.a("status/status_2.gif", false, 16);
        bq.a[2] = this.a("status/status_3.gif", false, 16);
        bq.a[3] = this.a("status/status_4.gif", false, 16);
        super.c.a(true);
        try {
            for (int i = 0; i < v.d(); ++i) {
                final int a = v.a(i, 0);
                bh bh = (bh)super.c.b(a);
                if (v.a(i, 63)) {
                    if (bh != null) {
                        super.c.b(a);
                    }
                }
                else {
                    if (bh == null) {
                        bh = new bh(a, v.a(i, 0));
                        super.c.a(bh);
                    }
                    else {
                        bh.f(v.a(i, 0));
                    }
                    bh.b = this.a("userIcons/" + bh.g(), true, 40);
                    bh.b = v.a(i, 1);
                    bh.a(v.a(i));
                    if (v.a(i, 62)) {
                        super.E = a;
                    }
                }
            }
        }
        finally {
            super.c.c();
        }
    }
    
    protected void o(final V v) {
        super.J = v.a(0, 0);
        super.K = v.a(0, 1);
        super.h = v.a(0);
        if (super.S != v.a(0, 0)) {
            aU.a(this, v.a(0, 0));
        }
        super.S = v.a(0, 0);
        super.U = v.a(0, 1);
        super.V = v.a(0, 2);
        super.W = v.a(0, 3);
        super.X = v.a(0, 4);
        super.Y = v.a(0, 5);
        if (super.a != null) {
            ((aV)super.a).a(super.J);
            ((aV)super.a).a().c((super.U <= 0) ? 7 : super.U);
        }
        aI.d[0] = v.a(0, 6);
        aI.d[1] = v.a(0, 7);
        aI.d[2] = v.a(0, 8);
        aI.d[3] = v.a(0, 9);
        aI.w = v.a(0, 31);
        aI.x = v.a(0, 32);
        be.Y = v.a(0, 33);
        be.Z = v.a(0, 34);
        be.U = v.a(0, 35);
        be.V = v.a(0, 36);
        be.W = v.a(0, 37);
        be.X = v.a(0, 38);
        if (!bq.h.containsKey("../../star_system.gif")) {
            bq.l = super.a("Doooknet.gif", false);
            final String[] array = { "system", "admin", "master" };
            for (int i = 0; i < array.length; ++i) {
                try {
                    final Image a = this.a("star_" + array[i] + ".gif", false);
                    if (a != null) {
                        bq.h.put("../../star_" + array[i] + ".gif", a);
                    }
                }
                catch (Exception ex) {}
            }
        }
        if (super.J != null && super.J.length() > 7 && super.a != null) {
            if (be.a == null) {
                be.a = new MenuItem(aG.a("Add To Favorites"));
                super.a.insert(be.a, 0);
                super.a.insertSeparator(1);
            }
        }
        else if (be.a != null) {
            super.a.remove(1);
            super.a.remove(0);
            be.a = null;
        }
    }
    
    protected void p(final V v) {
        this.p();
        super.d.a(true);
        try {
            for (int i = 0; i < v.d(); ++i) {
                final int a = v.a(i, 0);
                aq aq = (aq)super.d.b(a);
                if (v.a(i, 63)) {
                    if (aq != null) {
                        final ax ax = (ax)super.e.b(v.a(i, 2));
                        if (ax != null) {
                            if (!aq.c(23) || this.c(24)) {
                                final ax ax2 = ax;
                                --ax2.a;
                            }
                            if (super.a != null) {
                                super.a.c(ax);
                            }
                        }
                        final at at = new at(aq.b(), aq.g());
                        at.h = false;
                        super.d.b(a);
                        if (super.a != null) {
                            super.a.a(aq);
                            ((aV)super.a).a(at, false, false);
                        }
                        final aA aa = (aA)super.b.b(aq.b());
                        if (aa != null) {
                            aa.dispose();
                        }
                    }
                }
                else {
                    String s = this.c(v.a(i, 0));
                    if (s.length() > 35 && aI.w && !v.a(i, 40)) {
                        s = s.substring(0, 35);
                    }
                    if (aq == null) {
                        aq = new aq(a, s);
                        if (!aq.c(23) || this.c(24)) {
                            super.d.a(aq);
                        }
                    }
                    else {
                        aq.f(s);
                    }
                    aq.v = v.a(i, 1);
                    aq.a = (bh)super.c.b(aq.v);
                    aq.w = v.a(i, 2);
                    aq.ag = v.a(i, 3);
                    aq.a = v.a(i, 4);
                    aq.b = v.a(i, 5);
                    aq.ah = v.a(i, 6);
                    aq.t = v.a(i, 3);
                    aq.Q = v.a(i, 4);
                    if (aq.t != null && aq.t.length() > 0 && !bq.g.containsKey(aq.t)) {
                        try {
                            bq.g.put(aq.t, super.a("userImages/" + aq.t, true, 17));
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    aq.a(v.a(i));
                    final String a2 = v.a(i, 1);
                    final String a3 = v.a(i, 2);
                    if (a2 != null) {
                        aq.w = a2;
                    }
                    if (a3 != null) {
                        aq.x = a3;
                    }
                    if (a == this.b()) {
                        this.f(aq.g());
                        if (aq.v != -999) {
                            super.G = aq.v;
                        }
                    }
                    final at at2 = new at(aq.b(), aq.g());
                    at2.h = true;
                    at2.v = aq.v;
                    at2.a = (bh)super.c.b(aq.v);
                    at2.w = aq.w;
                    at2.a(aq.d());
                    if (super.a != null) {
                        super.a.a(aq, false);
                        ((aV)super.a).a(at2, false, false);
                    }
                }
            }
        }
        catch (Exception ex2) {
            System.out.println(ex2.getMessage());
        }
        finally {
            super.d.c();
        }
    }
    
    protected void q(final V v) {
        for (int i = 0; i < v.d(); ++i) {
            final String a = v.a(i, 0);
            if (a != null && a.length() > 0 && !bq.g.containsKey(a)) {
                final Image a2 = this.a("userImages/" + a, true, 17);
                if (a2 != null) {
                    bq.g.put(a, a2);
                }
            }
        }
    }
    
    public void a(final aN an, final aI ai) {
        final int b = ai.b();
        bc bc = (bc)super.b.b(b);
        if (bc == null) {
            bc = new aA(super.a.a(), this, b);
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int n = screenSize.width / 2 - 20;
            int n2 = screenSize.height / 2 - 20;
            int n3 = 0;
            int n4 = 0;
            switch (super.b.a() % 4) {
                case 0: {
                    n3 = 0;
                    n4 = 0;
                    break;
                }
                case 1: {
                    n3 = 0;
                    n4 = n + 10;
                    break;
                }
                case 2: {
                    n3 = n2 + 10;
                    n4 = 0;
                    break;
                }
                default: {
                    n3 = n2 + 10;
                    n4 = n + 10;
                    break;
                }
            }
            if (n > 400) {
                n = 400;
            }
            if (n2 > 300) {
                n2 = 300;
            }
            bc.reshape(2 + n4, 10 + n3, n, n2);
            super.b.a(bc, b);
        }
        if (an != null) {
            bc.a(an);
        }
        bc.setVisible(true);
    }
    
    public void a(final int n) {
        final V v = new V(67334, 1);
        v.a(0, 0, this.b());
        v.a(0, 1, n);
        v.a(0, 0, this.g());
        v.j = -1;
        v.u = -1;
        this.F(v);
    }
    
    public void b(final boolean p) {
        super.p = p;
    }
    
    public void f(final int n) {
        if (super.a.a() != null) {
            super.a.a().setCursor(3);
        }
        if (this.a == null) {
            this.a = new b(super.a.a(), this);
        }
        if (n >= 0) {
            this.a.c(n);
        }
        if (this.a.isVisible()) {
            this.a.toFront();
        }
        else {
            this.a.setVisible(true);
        }
        if (super.a.a() != null) {
            super.a.a().setCursor(0);
        }
    }
    
    protected void r(final V v) {
        super.r(v);
        if (v.a(-1, 31)) {
            this.i();
        }
    }
}
