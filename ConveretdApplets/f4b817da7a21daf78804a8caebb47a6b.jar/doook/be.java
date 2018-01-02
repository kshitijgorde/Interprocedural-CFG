// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.MenuBar;
import java.awt.Font;
import java.io.IOException;
import java.io.DataOutput;
import java.util.Calendar;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.awt.Color;
import java.net.MalformedURLException;
import java.awt.Button;
import java.awt.Component;
import java.awt.Event;
import java.io.DataInput;
import java.util.Date;
import java.awt.MenuItem;
import java.awt.Menu;
import java.awt.Frame;
import java.applet.AudioClip;
import java.awt.MediaTracker;
import java.net.URL;
import java.util.Vector;
import java.net.Socket;
import java.awt.Image;

public abstract class be extends aI implements Runnable
{
    protected static final String[] e;
    protected static final String[] c;
    public static Image c;
    public static Image j;
    public boolean b;
    public boolean c;
    public boolean d;
    public boolean i;
    public boolean e;
    protected int u;
    protected long f;
    public int k;
    protected long g;
    protected boolean l;
    protected boolean y;
    protected boolean p;
    protected boolean n;
    public aL a;
    public aT a;
    protected String A;
    protected String p;
    protected bd a;
    protected Thread e;
    protected Socket a;
    public v a;
    protected k b;
    public k c;
    public k d;
    public k e;
    public k f;
    public k g;
    public k h;
    public k i;
    public k j;
    public k k;
    public k l;
    public k m;
    public k n;
    public k o;
    public k p;
    public k q;
    public k r;
    public k s;
    public Vector i;
    public Vector j;
    public Vector k;
    public long h;
    public long i;
    public long j;
    public long k;
    protected URL c;
    protected boolean G;
    protected boolean H;
    public MediaTracker b;
    protected AudioClip[] a;
    protected e a;
    protected Frame f;
    protected int D;
    protected int E;
    protected int F;
    protected String B;
    public boolean I;
    public boolean J;
    protected String C;
    public boolean K;
    protected Menu a;
    public static MenuItem a;
    private MenuItem b;
    private MenuItem c;
    public String D;
    public String E;
    public String F;
    public String G;
    public String H;
    public String I;
    public String J;
    public String K;
    public URL d;
    public URL e;
    public URL f;
    public int G;
    public int H;
    public int I;
    public int J;
    public int K;
    public int L;
    public int M;
    public int N;
    public int O;
    public int P;
    public int Q;
    public int R;
    public int S;
    public Date a;
    public int T;
    public int U;
    public int V;
    public int W;
    public int X;
    public int Y;
    public boolean L;
    public boolean M;
    public boolean N;
    public boolean O;
    public boolean P;
    public boolean Q;
    public ap c;
    public boolean R;
    public boolean S;
    public boolean T;
    public static boolean U;
    public static boolean V;
    public static boolean W;
    public static boolean X;
    public static boolean Y;
    public static boolean Z;
    public String[] f;
    public int Z;
    public String L;
    public String M;
    public String N;
    public boolean aa;
    public boolean ab;
    public int aa;
    public String O;
    public String P;
    public int ab;
    public int ac;
    public int ad;
    public int ae;
    public int af;
    
    public boolean a() {
        this.a.setSoTimeout(5000);
        final V v = new V(this.a);
        if (v.a() == 67587) {
            this.g = v.a(0, 0);
            this.a.setSoTimeout(0);
            final V v2 = new V(67587, 1);
            v2.a(0, 0, 288233674753703937L);
            v2.a(0, 0, t.c);
            v2.a(0, 21, this.S);
            this.F(v2);
            this.R = false;
            this.aa = false;
            this.T = 0;
            return true;
        }
        return false;
    }
    
    public String a(String a, final aI ai) {
        if (a != null && this.M) {
            this.i.a(false);
            try {
                synchronized (this.i) {
                    final az az = new az(a, ai.c(61) ? 1 : 0);
                    for (int i = 0; i < this.i.a(); ++i) {
                        a = ((as)this.i.a(i)).a(az);
                        az.a = a;
                    }
                }
            }
            finally {
                this.i.c();
            }
            this.j.a(false);
            try {
                synchronized (this.j) {
                    for (int j = 0; j < this.j.a(); ++j) {
                        a = ((as)this.j.a(j)).c(a);
                    }
                }
            }
            finally {
                this.j.c();
            }
        }
        return a;
    }
    
    public String c(String s) {
        if (s != null && this.M) {
            this.i.a(false);
            try {
                synchronized (this.i) {
                    for (int i = 0; i < this.i.a(); ++i) {
                        s = ((as)this.i.a(i)).c(s);
                    }
                }
            }
            finally {
                this.i.c();
            }
            this.j.a(false);
            try {
                synchronized (this.j) {
                    for (int j = 0; j < this.j.a(); ++j) {
                        s = ((as)this.j.a(j)).c(s);
                    }
                }
            }
            finally {
                this.j.c();
            }
        }
        return s;
    }
    
    protected abstract void a();
    
    public void a(final String b, final String s, final String s2, final boolean b2) {
        this.B = b;
        final V v = new V(67330, 1);
        v.j = -1;
        v.u = -1;
        v.k = this.b();
        v.a(0, 0, -999);
        v.a(0, 0, b);
        v.a(0, 1, s);
        v.a(0, 0, new M(s2));
        if (!b2) {
            v.a(0, 58);
        }
        this.F(v);
    }
    
    public void b() {
        new ah(this.a.a(), this).setVisible(true);
    }
    
    public void a(final String b, final String s, final boolean b2) {
        this.B = b;
        final V v = new V(67330, 1);
        v.j = -1;
        v.u = -1;
        v.k = this.b();
        v.a(0, 0, -999);
        v.a(0, 0, b);
        v.a(0, 1, s);
        if (!b2) {
            v.a(0, 58);
        }
        this.F(v);
    }
    
    public abstract void c(final V p0);
    
    public void b(final int n) {
        final ax ax = (ax)this.e.b(n);
        if (ax != null) {
            this.a(ax);
        }
        else {
            System.err.println("no such room! " + n);
        }
    }
    
    public void a(final ax ax) {
        if (ax.f && ax.a != null) {
            new Z(this.a.a(), this, ax).setVisible(true);
        }
        if (!ax.f || ax.a == null) {
            final V v = new V(66049, 1);
            v.a(0, 0, this.b());
            v.a(0, 1, ax.b());
            v.j = -1;
            v.u = -1;
            this.F(v);
        }
    }
    
    public long c() {
        return this.f;
    }
    
    public int e() {
        return this.k;
    }
    
    public boolean k() {
        return this.l;
    }
    
    public boolean l() {
        return this.y;
    }
    
    public boolean m() {
        return this.n;
    }
    
    public int a(final int n) {
        int n2 = 0;
        this.d.a(false);
        try {
            synchronized (this.d) {
                for (int i = 0; i < this.d.a(); ++i) {
                    final aq aq = (aq)this.d.a(i);
                    if (aq.w == n && (!aq.c(23) || this.c(24))) {
                        ++n2;
                    }
                }
            }
        }
        finally {
            this.d.c();
        }
        return n2;
    }
    
    public boolean a(final Event event) {
        if (event.target == this.b) {
            this.i();
        }
        else if (event.target == be.a) {
            a(this);
        }
        else if (this.p.a(event.target)) {
            this.a((URL)this.q.a(this.p.a(event.target)), "_blank");
        }
        else if (event.target == this.c) {
            this.f(0);
        }
        return true;
    }
    
    protected static boolean a(final String s) {
        return aI.x && !s.startsWith("status") && !s.toLowerCase().startsWith("doook") && !s.equalsIgnoreCase("chatLogo.gif");
    }
    
    public Image a(final String s, final boolean b) {
        try {
            MediaTracker b2;
            if (this.b != null) {
                b2 = this.b;
            }
            else {
                final Button button;
                b2 = new MediaTracker(button);
                button = new Button();
            }
            final MediaTracker mediaTracker = b2;
            URL url;
            if (b || a(s)) {
                url = new URL(this.c, "Resources/" + this.A + "/" + s);
            }
            else {
                url = new URL(this.c, "Resources/" + s);
            }
            Image a = this.a(url);
            if (a != null) {
                final int n = this.u++;
                mediaTracker.addImage(a, n);
                try {
                    mediaTracker.waitForID(n);
                }
                catch (InterruptedException ex) {}
                if (mediaTracker.isErrorID(n)) {
                    a = null;
                }
            }
            return a;
        }
        catch (MalformedURLException ex2) {
            return null;
        }
    }
    
    public Image a(final String s, final boolean b, final int n) {
        return null;
    }
    
    public Image a(final URL url) {
        return null;
    }
    
    public abstract void a(final String p0, final String p1, final M p2, final String p3, final int p4);
    
    public void i() {
        this.i = true;
        if (this.a != null) {
            this.aa = true;
            final V v = new V(65794, 1);
            v.a(0, 0, this.b());
            v.a(0, 0, this.G);
            v.j = -1;
            v.u = -1;
            this.F(v);
            super.w = -999;
            if (this.a != null) {
                final Frame a = this.a.a();
                this.a.c();
                if (a != null) {
                    a.dispose();
                }
            }
        }
        if (this.aa) {
            this.G = true;
            this.C(new V(66561, 0));
            this.c = false;
            this.d = false;
            if (this.f != null) {
                this.a(this.f, "_self");
            }
        }
        this.a();
    }
    
    public void a(final aN an) {
    }
    
    public void a(final byte[] array) {
    }
    
    public abstract void a(final URL p0, final String p1);
    
    public void e(final int n) {
    }
    
    protected void s(final V v) {
        for (int i = 0; i < v.d(); ++i) {
            final aq aq = (aq)this.d.b(v.a(i, 0));
            if (aq != null) {
                this.a(aq, v.a(i, 1), v.a(), v.h());
            }
        }
    }
    
    protected abstract void a(final aq p0, final int p1, final long p2, final int p3);
    
    protected void t(final V v) {
        if (this.e != null) {
            this.a(this.e, "_blank");
        }
        else {
            new E(new Frame(), aG.a("You have been kicked"), v.a(0, 0), this).setVisible(true);
        }
        this.G = true;
        this.aa = true;
        this.C(new V(66561, 0));
        this.a();
    }
    
    protected void u(final V v) {
        final String[] array = new String[v.d()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = v.a(i, 0);
        }
        new E(this.a.a(), aG.a("Operation not allowed"), array, this).setVisible(true);
    }
    
    protected void v(final V v) {
        String s = null;
        final int a = v.a(0, 1);
        switch (v.a(0, 0)) {
            case 2: {
                s = aC.a(aG.a("You may not connect to %1 because your account has been disabled."), new String[] { t.a });
                break;
            }
            case 4: {
                s = aC.a(aG.a("You may not connect to %1 because the maximum number of simultaneous connections for this site has been reached."), new String[] { t.a });
                break;
            }
            case 3: {
                s = aC.a(aG.a("You may not connect to %1 because the version of the Client is not compatible with the version of Server."), new String[] { t.a, ao.b(), ao.a(this.g) });
                break;
            }
            case 5: {
                s = aC.a(aG.a("You may not connect to %1 because you have been banned from this %1 site."), new String[] { t.a });
                break;
            }
            case 6: {
                s = aC.a(aG.a("You may not connect to %1 because no site with the specified ID could be found."), new String[] { t.a });
                break;
            }
            case 8: {
                s = aC.a(aG.a("You are attempting to connect to %1 from an invalid host. Please contact the %1 Site administrator."), new String[] { t.a });
                break;
            }
            case 10: {
                s = aC.a(aG.a("You may not connect to %1 because the Server license has expired."), new String[] { t.a });
                break;
            }
            case 11: {
                s = aC.a(aG.a("You may not connect to %1 because your nickname is too long.  Please re-enter this information and login again."), new String[] { t.a });
                break;
            }
            case 12: {
                s = aC.a(aG.a("You may not connect to %1 because your nickname contains non allowed words."), new String[] { t.a });
                break;
            }
            case 9: {
                s = aC.a(aG.a("You may not connect to %1 because the Server license has been exceeded."), new String[] { t.a });
                break;
            }
            case 0: {
                if (a == 65793) {
                    s = aC.a(aG.a("You may not connect to %1 because another user is already using the name \"%2.\" Please choose another and login again."), new String[] { t.a, this.g() });
                    break;
                }
                s = aC.a(aG.a("Your nickname could not be changed because the name \"%1\" is already in use by another user, or is reserved."), new String[] { v.a(0, 0) });
                break;
            }
            case 1:
            case 7: {
                if (a == 65793) {
                    s = aC.a(aG.a("You may not connect to %1 because your name or password was not entered correctly.  Please re-enter this information and login again."), new String[] { t.a });
                    break;
                }
                break;
            }
        }
        if (s == null) {
            s = aG.a("Your request could not be fulfilled.");
        }
        if (a == 65793) {
            this.G = true;
            this.aa = true;
            this.C(new V(66561, 0));
            this.a();
        }
        new E(this.f, aG.a("Note"), s, this).setVisible(true);
    }
    
    protected void w(final V v) {
        if (!this.G) {
            new E((this.a == null) ? null : this.a.a(), aG.a("Alert"), new String[] { v.a(0, 0), v.a(0, 1) }, this).setVisible(true);
        }
    }
    
    protected void r(final V v) {
        for (int i = 0; i < v.d(); ++i) {
            new E((this.a == null) ? null : this.a.a(), v.a(i, 0), v.a(i, 1), this).setVisible(true);
        }
    }
    
    protected void x(final V v) {
        new E(this.a.a(), aG.a("Inactivity Timeout"), aC.a(aG.a("You have been disconnected because you have been inactive for the past %1 minutes."), new String[] { String.valueOf(v.a(0, 0)) }), this).setVisible(true);
        this.G = true;
        this.aa = true;
        this.C(new V(66561, 0));
        this.a();
    }
    
    protected void y(final V v) {
        final long a = v.a(-1);
        if (this.i == -999L && v.a(-1, 60)) {
            this.M = true;
        }
        k k;
        if (v.a(-1, 59)) {
            if ("Admin".equals(this.A)) {
                this.i = a;
                k = this.i;
            }
            else {
                k = this.j;
            }
            this.j = a;
        }
        else {
            k = this.i;
            this.i = a;
        }
        if (doook.V.a(this.j | this.i, 62) || doook.V.a(this.j | this.i, 61)) {
            this.M = true;
        }
        k.a(true);
        try {
            for (int i = 0; i < v.d(); ++i) {
                final int a2 = v.a(i, 0);
                as as = (as)k.b(a2);
                if (v.a(i, 63)) {
                    if (as != null) {
                        k.b(a2);
                    }
                }
                else {
                    if (as == null) {
                        as = new as(a2, v.a(i, 0));
                        k.a(as);
                    }
                    else {
                        as.f(v.a(i, 0));
                    }
                    as.a = v.a(i, 1);
                    as.ag = v.a(i, 1);
                    as.a(v.a(i));
                }
            }
        }
        finally {
            k.c();
        }
    }
    
    protected void z(final V v) {
        this.p = v.a(0, 0);
    }
    
    protected void A(final V v) {
        ax ax = null;
        bn bn = null;
        this.e.a(true);
        try {
            for (int i = 0; i < v.d(); ++i) {
                final int a = v.a(i, 0);
                ax ax2 = (ax)this.e.b(a);
                if (v.a(i, 63) || v.a(i, 0) == null) {
                    if (ax2 != null) {
                        this.e.b(a);
                        if (this.a != null) {
                            this.a.a(ax2);
                        }
                    }
                    if (a == super.w) {
                        bn = ax2;
                    }
                }
                else {
                    if (ax2 == null) {
                        String a2 = v.a(i, 0);
                        if (a2 == null) {
                            System.err.println("null room name received.");
                            a2 = new String("no name");
                        }
                        ax2 = new ax(a, this.c(a2));
                        this.e.a(ax2);
                    }
                    else {
                        ax2.f(this.c(v.a(i, 0)));
                    }
                    ax2.b = v.a(i, 1);
                    ax2.c = v.a(i, 2);
                    ax2.d = v.a(i, 3);
                    ax2.a = v.a(i, 4);
                    ax2.ag = v.a(i, 5);
                    ax2.ah = v.a(i, 6);
                    ax2.a = this.c(v.a(i, 1));
                    ax2.b = this.c(v.a(i, 2));
                    final M a3 = ax2.a;
                    ax2.a = v.a(i, 0);
                    if (ax2.a != null && !ax2.a.equals(a3)) {
                        ax2.f = true;
                    }
                    ax2.a(v.a(i));
                    if (v.a(i, 62)) {
                        this.D = a;
                    }
                    if (this.a != null) {
                        this.a.c(ax2);
                    }
                    if (this.B != null && ax2.g().equals(this.B)) {
                        this.B = null;
                        ax = ax2;
                        ax.f = false;
                    }
                }
            }
            if (this.D == -999) {
                this.D = v.a(0, 0);
            }
        }
        finally {
            this.e.c();
        }
        if (ax != null) {
            this.a(ax);
            this.a.a(true);
        }
        else if (bn != null) {
            final a a4 = (a)this.e.b(this.D);
            this.b(this.D);
            new E(this.a.a(), t.a, aC.a(aG.a("The room you were in, \"%1,\" has closed.  You have been moved to \"%2.\""), new String[] { this.c(bn.g()), this.c(a4.g()) }), this).setVisible(true);
        }
    }
    
    protected void B(final V v) {
        this.o.a(true);
        try {
            ap c = null;
            ap c2 = null;
            for (int i = 0; i < v.d(); ++i) {
                int n = v.a(i, 0);
                ap ap = (ap)this.o.b(n);
                if (v.a(i, 63)) {
                    this.o.b(n);
                }
                else {
                    if (ap == null) {
                        ap = new ap(n, v.a(i, 0));
                        n = this.o.a(ap, n);
                        v.a(i, 0, n);
                    }
                    else {
                        ap.f(v.a(i, 0));
                    }
                    ap.c = new Color(v.a(i, 1));
                    ap.d = new Color(v.a(i, 2));
                    ap.e = new Color(v.a(i, 3));
                    ap.g = new Color(v.a(i, 4));
                    ap.j = new Color(v.a(i, 5));
                    ap.k = new Color(v.a(i, 6));
                    ap.l = new Color(v.a(i, 7));
                    ap.m = new Color(v.a(i, 8));
                    ap.n = new Color(v.a(i, 9));
                    ap.o = new Color(v.a(i, 10));
                    ap.f = new Color(v.a(i, 11));
                    ap.k = v.a(i, 1);
                    ap.q = v.a(i, 12);
                    ap.p = v.a(i, 13);
                    ap.g();
                    ap.a(v.a(i));
                    ap.c(v.a(i, 2));
                    ap.r = v.a(i, 14);
                    ap.h = new Color(v.a(i, 15));
                    ap.i = new Color(v.a(i, 16));
                    ap.p = new Color(v.a(i, 17));
                    ap.q = new Color(v.a(i, 18));
                    if (ap.c(62)) {
                        c = (ap)ap.clone();
                    }
                    if (n == t.d) {
                        c2 = (ap)ap.clone();
                    }
                }
            }
            if (c2 != null) {
                this.c = c2;
            }
            else if (c != null) {
                this.c = c;
            }
            if (this.a != null) {
                this.a.d();
            }
            if (this.b != null) {
                for (int j = 0; j < this.b.a(); ++j) {
                    ((bc)this.b.a(j)).a();
                }
            }
        }
        finally {
            this.o.c();
        }
    }
    
    public synchronized void C(final V v) {
        this.a.a(v);
        this.notify();
        this.notify();
    }
    
    protected void D(final V v) {
        this.r.a(true);
        try {
            for (int i = 0; i < v.d(); ++i) {
                final int a = v.a(i, 0);
                q q = (q)this.r.b(a);
                if (v.a(i, 63)) {
                    this.r.b(a);
                }
                else {
                    if (q == null) {
                        q = new q(a, v.a(i, 1));
                        this.r.a(q, a);
                    }
                    else {
                        q.f(v.a(i, 1));
                    }
                    q.a(v.a(i));
                    q.a = v.a(i, 0);
                }
            }
        }
        finally {
            this.r.c();
        }
    }
    
    public void E(final V a) {
        try {
            this.O = a.a(0, 0);
            this.P = a.a(0, 1);
            this.ab = a.a(0, 0);
            this.ac = a.a(0, 1);
            this.ad = a.a(0, 2);
            this.ae = a.a(0, 3);
            this.af = a.a(0, 4);
            String line = "";
            if (a.a(0, 2) != null && a.a(0, 2).length() > 0) {
                try {
                    final String string = "http://" + ar.b.getCodeBase().getHost() + "/DoookNet/rss/" + a.a(0, 2) + ".rss";
                    BufferedReader bufferedReader;
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new URL(string + "z").openStream())));
                    }
                    catch (Exception ex2) {
                        bufferedReader = new BufferedReader(new InputStreamReader(new URL(string).openStream()));
                    }
                    line = bufferedReader.readLine();
                    bufferedReader.close();
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
                doook.O.a.d = new Color(this.ab);
                doook.O.a.e = new Color(this.ac);
                doook.O.a.e = this.P;
                doook.O.a.k = (this.ae != 1);
                doook.O.a.k = 30 - this.ad * 5;
            }
            doook.O.a.f = line;
            doook.O.a.setVisible(line != "");
            doook.O.a.d = 10;
            ((O)this.a.b()).validate();
            ((O)this.a.b()).repaint();
            doook.O.a.i = this;
            doook.O.a = a;
        }
        catch (Exception ex3) {}
    }
    
    public abstract void l();
    
    public void run() {
        this.a.b();
        this.a.start();
        try {
            while (!this.G || !this.a.a()) {
                while (this.a.a() && !this.G) {
                    synchronized (this) {
                        this.wait();
                    }
                }
                this.c((V)this.a.a());
            }
        }
        catch (Exception ex) {}
        this.G = true;
        this.a();
    }
    
    public void j() {
    }
    
    public void F(final V v) {
        if (this.ab) {
            System.out.println(aG.a("Reconnecting to the server, please wait ..."));
            return;
        }
        try {
            if (v.a() == 66049 || (v.a() == 66305 && !v.a(0, 0).startsWith("mystatus=")) || v.a() == 67334 || v.a() == 67074 || v.a() == 263680 || v.a() == 264448) {
                this.a = Calendar.getInstance().getTime();
            }
            v.a(this.a);
            this.a.flush();
        }
        catch (IOException ex) {
            if (!this.G) {
                this.a();
            }
        }
    }
    
    public void a(final String s, final int j, final int u, final int n, final boolean b, final boolean b2) {
        final V v = new V(66305, 1);
        v.a(0, 0, s);
        v.a(0, 0, this.b());
        v.a(0, 2, n);
        v.a(0, 38, b);
        v.a(0, 37, b2);
        v.j = j;
        v.u = u;
        this.F(v);
    }
    
    public void a(final String s, final int n, final boolean b, final boolean b2) {
        this.a(s, -1, super.w, n, b, b2);
        this.f = System.currentTimeMillis();
    }
    
    public abstract void a(final aN p0, final aI p1);
    
    public void a(final Font font) {
        this.c.k = font.getName();
        this.c.q = font.getStyle();
        this.c.p = font.getSize();
        this.c.g();
        if (this.a != null) {
            this.a.d();
        }
        if (this.b != null) {
            for (int i = 0; i < this.b.a(); ++i) {
                ((bc)this.b.a(i)).a();
            }
        }
    }
    
    public boolean a(String lowerCase, final String s) {
        if (lowerCase.length() == 0 || s.length() == 0) {
            return true;
        }
        int index = 0;
        final char[] charArray = s.toLowerCase().toCharArray();
        lowerCase = lowerCase.toLowerCase();
        for (int i = 0; i < charArray.length; ++i) {
            if ((index = lowerCase.indexOf(charArray[i], index)) == -1) {
                return false;
            }
        }
        return true;
    }
    
    public void a(final boolean h) {
        this.H = h;
        this.d.a(false);
        try {
            synchronized (this.d) {
                for (int i = 0; i < this.d.a(); ++i) {
                    final aq aq = (aq)this.d.a(i);
                    if ((this.H || aq.w == super.w) && this.a(aq.g(), doook.x.a.getText())) {
                        this.a.a(aq, true);
                    }
                    else {
                        this.a.a(aq);
                    }
                }
            }
        }
        finally {
            this.d.c();
        }
    }
    
    public void b(final boolean b) {
    }
    
    public void c(final boolean l) {
        this.l = l;
    }
    
    public void d(final boolean y) {
        this.y = y;
    }
    
    public void e(final boolean n) {
        this.n = n;
    }
    
    public void a(final Frame frame) {
        this.a = new Menu(t.a);
        final MenuBar menuBar = new MenuBar();
        for (int i = 1; i <= this.p.a(); ++i) {
            this.a.add((MenuItem)this.p.b(i));
        }
        if (this.p.a() >= 1) {
            this.a.addSeparator();
        }
        if (!t.f) {
            this.c = new MenuItem(aG.a("Settings"));
            this.a.add(this.c);
        }
        if (!this.b) {
            this.b = new MenuItem(aG.a("Logout"));
            this.a.add(this.b);
        }
        menuBar.add(this.a);
        frame.setMenuBar(menuBar);
        if (this.J != null && this.J.length() > 7) {
            be.a = new MenuItem(aG.a("Add To Favorites"));
            this.a.insert(be.a, 0);
            this.a.insertSeparator(1);
        }
        else if (be.a != null) {
            this.a.remove(1);
            this.a.remove(0);
            be.a = null;
        }
    }
    
    public static final void a(final be be) {
        aI.a(new Object[] { "top.focus(); setTimeout(\"doookBookmarkSite('" + be.J + "', top.document.title);\", 200);" });
    }
    
    public void f(final int n) {
    }
    
    public void q() {
        final String a = aC.a(aG.a("You have been disconnected from %1 for flooding."), new String[] { t.a });
        final V v = new V(50400771, 1);
        v.j = this.b();
        v.a(0, 0, a);
        this.F(v);
    }
    
    public be() {
        super(-999, null);
        this.a = Calendar.getInstance().getTime();
        this.T = 0;
        this.T = false;
        this.Z = 0;
        this.L = "00000000-0000-0000-0000-000000000000";
        this.M = "00000000-0000-0000-0000-000000000000";
        this.N = "";
        this.aa = true;
        this.ab = false;
        this.aa = 0;
        this.M = this.L;
        this.L = "00000000-0000-0000-0000-000000000000";
        this.aa = true;
        this.b = false;
        this.c = false;
        this.d = false;
        this.i = false;
        this.e = false;
        this.u = 1000;
        this.f = 0L;
        this.k = 250;
        this.l = true;
        this.y = true;
        this.p = false;
        this.n = false;
        this.a = null;
        this.b = new k();
        this.c = new k();
        this.d = new k();
        this.e = new k();
        this.f = new k();
        this.g = new k();
        this.h = new k();
        this.i = new k();
        this.j = new k();
        this.k = new k();
        this.l = new k();
        this.m = new k();
        this.n = new k();
        this.o = new k();
        this.p = new k();
        this.q = new k();
        this.r = new k();
        this.s = new k();
        this.i = new Vector();
        this.j = new Vector();
        this.k = new Vector();
        this.i = -999L;
        this.G = true;
        this.H = false;
        this.a = new e();
        this.D = -999;
        this.E = -999;
        this.F = -999;
        this.I = false;
        this.J = true;
        this.K = false;
        this.G = -999;
        this.H = 2;
        this.I = -999;
        this.J = -999;
        this.K = -999;
        this.L = -999;
        this.M = 0;
        this.N = 8;
        this.O = 3;
        this.P = 4;
        this.Q = 9;
        this.R = 1;
        this.S = 0;
        this.U = 7;
        this.V = 2;
        this.W = 0;
        this.X = 0;
        this.Y = 0;
        this.L = false;
        this.M = false;
        this.N = false;
        this.O = false;
        this.P = false;
        this.Q = false;
        this.c = ap.b;
        this.R = false;
        bq.h.clear();
    }
    
    static {
        e = new String[] { null, null, null, null, null, null, null, null, null };
        c = new String[] { "bass.au", "bell.au", "castanet.au", "chime.au", "conga.au", "cowBell.au", "doubleBell.au", "drumRoll.au", "harp.au" };
        be.U = false;
        be.V = false;
        be.W = false;
        be.X = false;
        be.Y = false;
        be.Z = false;
    }
}
