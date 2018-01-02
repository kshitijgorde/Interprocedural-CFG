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

public abstract class as extends F implements Runnable
{
    protected static final String[] c;
    protected static final String[] b;
    public static Image c;
    public static Image j;
    public boolean h;
    public boolean d;
    public boolean e;
    public boolean b;
    public boolean y;
    protected int q;
    protected long b;
    public int j;
    protected long c;
    protected boolean v;
    protected boolean w;
    protected boolean x;
    protected boolean z;
    public J a;
    public E a;
    protected String r;
    protected String s;
    protected f a;
    protected Thread c;
    protected Socket a;
    public ak a;
    protected aT c;
    public aT d;
    public aT e;
    public aT f;
    public aT g;
    public aT h;
    public aT i;
    public aT j;
    public aT k;
    public aT l;
    public aT m;
    public aT n;
    public aT o;
    public aT p;
    public aT q;
    public aT r;
    public aT s;
    public aT t;
    public Vector e;
    public Vector f;
    public Vector g;
    public long d;
    public long e;
    public long f;
    public long g;
    protected URL c;
    protected boolean A;
    protected boolean B;
    public MediaTracker b;
    protected AudioClip[] a;
    protected aR a;
    protected Frame c;
    protected int F;
    protected int G;
    protected int H;
    protected String t;
    public boolean C;
    public boolean D;
    protected String u;
    public boolean E;
    protected Menu a;
    public static MenuItem a;
    private MenuItem b;
    private MenuItem c;
    public String v;
    public String w;
    public String x;
    public String y;
    public String z;
    public String A;
    public String B;
    public String C;
    public URL d;
    public URL e;
    public URL f;
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
    public int T;
    public Date a;
    public int b;
    public int U;
    public int V;
    public int W;
    public int X;
    public int Y;
    public boolean F;
    public boolean G;
    public boolean H;
    public boolean I;
    public boolean J;
    public boolean K;
    public aZ b;
    public boolean L;
    public boolean M;
    public boolean N;
    public static boolean O;
    public static boolean P;
    public static boolean Q;
    public static boolean R;
    public static boolean S;
    public static boolean T;
    public String[] d;
    public int Z;
    public String D;
    public String E;
    public String F;
    public boolean U;
    public boolean V;
    public int aa;
    public String G;
    public String H;
    public int ab;
    public int ac;
    public int ad;
    public int ae;
    public int af;
    
    public boolean e() {
        this.a.setSoTimeout(5000);
        final aJ aj = new aJ(this.a);
        if (aj.b() == 67587) {
            this.c = aj.a(0, 0);
            this.a.setSoTimeout(0);
            final aJ aj2 = new aJ(67587, 1);
            aj2.a(0, 0, 288233674753703937L);
            aj2.a(0, 0, bi.S);
            aj2.a(0, 21, this.M);
            this.q(aj2);
            this.L = false;
            this.U = false;
            this.b = 0;
            return true;
        }
        return false;
    }
    
    public String a(String k, final F f) {
        if (k != null && this.G) {
            this.j.a(false);
            try {
                synchronized (this.j) {
                    final A a = new A(k, f.a(61) ? 1 : 0);
                    for (int i = 0; i < this.j.b(); ++i) {
                        k = ((aq)this.j.a(i)).a(a);
                        a.k = k;
                    }
                }
            }
            finally {
                this.j.f();
            }
            this.k.a(false);
            try {
                synchronized (this.k) {
                    for (int j = 0; j < this.k.b(); ++j) {
                        k = ((aq)this.k.a(j)).b(k);
                    }
                }
            }
            finally {
                this.k.f();
            }
        }
        return k;
    }
    
    public String e(String s) {
        if (s != null && this.G) {
            this.j.a(false);
            try {
                synchronized (this.j) {
                    for (int i = 0; i < this.j.b(); ++i) {
                        s = ((aq)this.j.a(i)).b(s);
                    }
                }
            }
            finally {
                this.j.f();
            }
            this.k.a(false);
            try {
                synchronized (this.k) {
                    for (int j = 0; j < this.k.b(); ++j) {
                        s = ((aq)this.k.a(j)).b(s);
                    }
                }
            }
            finally {
                this.k.f();
            }
        }
        return s;
    }
    
    protected abstract void k();
    
    public void a(final String t, final String s, final String s2, final boolean b) {
        this.t = t;
        final aJ aj = new aJ(67330, 1);
        aj.j = -1;
        aj.C = -1;
        aj.k = this.e();
        aj.a(0, 0, -999);
        aj.a(0, 0, t);
        aj.a(0, 1, s);
        aj.a(0, 0, new r(s2));
        if (!b) {
            aj.b(0, 58);
        }
        this.q(aj);
    }
    
    public void i() {
        new aN(this.a.a(), this).setVisible(true);
    }
    
    public void a(final String t, final String s, final boolean b) {
        this.t = t;
        final aJ aj = new aJ(67330, 1);
        aj.j = -1;
        aj.C = -1;
        aj.k = this.e();
        aj.a(0, 0, -999);
        aj.a(0, 0, t);
        aj.a(0, 1, s);
        if (!b) {
            aj.b(0, 58);
        }
        this.q(aj);
    }
    
    public abstract void b(final aJ p0);
    
    public void d(final int n) {
        final bn bn = (bn)this.f.b(n);
        if (bn != null) {
            this.c(bn);
        }
        else {
            System.err.println("no such room! " + n);
        }
    }
    
    public void c(final bn bn) {
        if (bn.c && bn.a != null) {
            new d(this.a.a(), this, bn).setVisible(true);
        }
        if (!bn.c || bn.a == null) {
            final aJ aj = new aJ(66049, 1);
            aj.a(0, 0, this.e());
            aj.a(0, 1, bn.e());
            aj.j = -1;
            aj.C = -1;
            this.q(aj);
        }
    }
    
    public long b() {
        return this.b;
    }
    
    public int h() {
        return this.j;
    }
    
    public boolean f() {
        return this.v;
    }
    
    public boolean g() {
        return this.w;
    }
    
    public boolean h() {
        return this.z;
    }
    
    public int e(final int n) {
        int n2 = 0;
        this.e.a(false);
        try {
            synchronized (this.e) {
                for (int i = 0; i < this.e.b(); ++i) {
                    final a a = (a)this.e.a(i);
                    if (a.t == n && (!a.a(23) || this.a(24))) {
                        ++n2;
                    }
                }
            }
        }
        finally {
            this.e.f();
        }
        return n2;
    }
    
    public boolean a(final Event event) {
        if (event.target == this.b) {
            this.n();
        }
        else if (event.target == as.a) {
            a(this);
        }
        else if (this.q.a(event.target)) {
            this.a((URL)this.r.a(this.q.a(event.target)), "_blank");
        }
        else if (event.target == this.c) {
            this.c(0);
        }
        return true;
    }
    
    protected static boolean a(final String s) {
        return F.p && !s.startsWith("status") && !s.toLowerCase().startsWith("doook") && !s.equalsIgnoreCase("chatLogo.gif");
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
                url = new URL(this.c, "Resources/" + this.r + "/" + s);
            }
            else {
                url = new URL(this.c, "Resources/" + s);
            }
            Image a = this.a(url);
            if (a != null) {
                final int n = this.q++;
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
    
    public abstract void a(final String p0, final String p1, final r p2, final String p3, final int p4);
    
    public void n() {
        this.b = true;
        if (this.a != null) {
            this.U = true;
            final aJ aj = new aJ(65794, 1);
            aj.a(0, 0, this.e());
            aj.a(0, 0, this.y);
            aj.j = -1;
            aj.C = -1;
            this.q(aj);
            super.t = -999;
            if (this.a != null) {
                final Frame a = this.a.a();
                this.a.f();
                if (a != null) {
                    a.dispose();
                }
            }
        }
        if (this.U) {
            this.A = true;
            this.n(new aJ(66561, 0));
            this.d = false;
            this.e = false;
            if (this.f != null) {
                this.a(this.f, "_self");
            }
        }
        this.k();
    }
    
    public void b(final bv bv) {
    }
    
    public void b(final byte[] array) {
    }
    
    public abstract void a(final URL p0, final String p1);
    
    public void a(final int n) {
    }
    
    protected void c(final aJ aj) {
        for (int i = 0; i < aj.g(); ++i) {
            final a a = (a)this.e.b(aj.b(i, 0));
            if (a != null) {
                this.a(a, aj.b(i, 1), aj.c(), aj.j());
            }
        }
    }
    
    protected abstract void a(final a p0, final int p1, final long p2, final int p3);
    
    protected void d(final aJ aj) {
        if (this.e != null) {
            this.a(this.e, "_blank");
        }
        else {
            new S(new Frame(), ar.b("You have been kicked"), aj.a(0, 0), this).setVisible(true);
        }
        this.A = true;
        this.U = true;
        this.n(new aJ(66561, 0));
        this.k();
    }
    
    protected void e(final aJ aj) {
        final String[] array = new String[aj.g()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = aj.a(i, 0);
        }
        new S(this.a.a(), ar.b("Operation not allowed"), array, this).setVisible(true);
    }
    
    protected void f(final aJ aj) {
        String s = null;
        final int b = aj.b(0, 1);
        switch (aj.b(0, 0)) {
            case 2: {
                s = doook.H.a(ar.b("You may not connect to %1 because your account has been disabled."), new String[] { bi.Q });
                break;
            }
            case 4: {
                s = doook.H.a(ar.b("You may not connect to %1 because the maximum number of simultaneous connections for this site has been reached."), new String[] { bi.Q });
                break;
            }
            case 3: {
                s = doook.H.a(ar.b("You may not connect to %1 because the version of the Client is not compatible with the version of Server."), new String[] { bi.Q, doook.I.a(), doook.I.a(this.c) });
                break;
            }
            case 5: {
                s = doook.H.a(ar.b("You may not connect to %1 because you have been banned from this %1 site."), new String[] { bi.Q });
                break;
            }
            case 6: {
                s = doook.H.a(ar.b("You may not connect to %1 because no site with the specified ID could be found."), new String[] { bi.Q });
                break;
            }
            case 8: {
                s = doook.H.a(ar.b("You are attempting to connect to %1 from an invalid host. Please contact the %1 Site administrator."), new String[] { bi.Q });
                break;
            }
            case 10: {
                s = doook.H.a(ar.b("You may not connect to %1 because the Server license has expired."), new String[] { bi.Q });
                break;
            }
            case 11: {
                s = doook.H.a(ar.b("You may not connect to %1 because your nickname is too long.  Please re-enter this information and login again."), new String[] { bi.Q });
                break;
            }
            case 12: {
                s = doook.H.a(ar.b("You may not connect to %1 because your nickname contains non allowed words."), new String[] { bi.Q });
                break;
            }
            case 9: {
                s = doook.H.a(ar.b("You may not connect to %1 because the Server license has been exceeded."), new String[] { bi.Q });
                break;
            }
            case 0: {
                if (b == 65793) {
                    s = doook.H.a(ar.b("You may not connect to %1 because another user is already using the name \"%2.\" Please choose another and login again."), new String[] { bi.Q, this.d() });
                    break;
                }
                s = doook.H.a(ar.b("Your nickname could not be changed because the name \"%1\" is already in use by another user, or is reserved."), new String[] { aj.a(0, 0) });
                break;
            }
            case 1:
            case 7: {
                if (b == 65793) {
                    s = doook.H.a(ar.b("You may not connect to %1 because your name or password was not entered correctly.  Please re-enter this information and login again."), new String[] { bi.Q });
                    break;
                }
                break;
            }
        }
        if (s == null) {
            s = ar.b("Your request could not be fulfilled.");
        }
        if (b == 65793) {
            this.A = true;
            this.U = true;
            this.n(new aJ(66561, 0));
            this.k();
        }
        new S(this.c, ar.b("Note"), s, this).setVisible(true);
    }
    
    protected void g(final aJ aj) {
        if (!this.A) {
            new S((this.a == null) ? null : this.a.a(), ar.b("Alert"), new String[] { aj.a(0, 0), aj.a(0, 1) }, this).setVisible(true);
        }
    }
    
    protected void h(final aJ aj) {
        for (int i = 0; i < aj.g(); ++i) {
            new S((this.a == null) ? null : this.a.a(), aj.a(i, 0), aj.a(i, 1), this).setVisible(true);
        }
    }
    
    protected void i(final aJ aj) {
        new S(this.a.a(), ar.b("Inactivity Timeout"), doook.H.a(ar.b("You have been disconnected because you have been inactive for the past %1 minutes."), new String[] { String.valueOf(aj.b(0, 0)) }), this).setVisible(true);
        this.A = true;
        this.U = true;
        this.n(new aJ(66561, 0));
        this.k();
    }
    
    protected void j(final aJ aj) {
        final long a = aj.a(-1);
        if (this.e == -999L && aj.a(-1, 60)) {
            this.G = true;
        }
        aT at;
        if (aj.a(-1, 59)) {
            if ("Admin".equals(this.r)) {
                this.e = a;
                at = this.j;
            }
            else {
                at = this.k;
            }
            this.f = a;
        }
        else {
            at = this.j;
            this.e = a;
        }
        if (aJ.a(this.f | this.e, 62) || aJ.a(this.f | this.e, 61)) {
            this.G = true;
        }
        at.a(true);
        try {
            for (int i = 0; i < aj.g(); ++i) {
                final int b = aj.b(i, 0);
                aq aq = (aq)at.b(b);
                if (aj.a(i, 63)) {
                    if (aq != null) {
                        at.c(b);
                    }
                }
                else {
                    if (aq == null) {
                        aq = new aq(b, aj.a(i, 0));
                        at.a(aq);
                    }
                    else {
                        aq.b(aj.a(i, 0));
                    }
                    aq.m = aj.a(i, 1);
                    aq.u = aj.b(i, 1);
                    aq.a(aj.a(i));
                }
            }
        }
        finally {
            at.f();
        }
    }
    
    protected void k(final aJ aj) {
        this.s = aj.a(0, 0);
    }
    
    protected void l(final aJ aj) {
        bn bn = null;
        D d = null;
        this.f.a(true);
        try {
            for (int i = 0; i < aj.g(); ++i) {
                final int b = aj.b(i, 0);
                bn bn2 = (bn)this.f.b(b);
                if (aj.a(i, 63) || aj.a(i, 0) == null) {
                    if (bn2 != null) {
                        this.f.c(b);
                        if (this.a != null) {
                            this.a.a(bn2);
                        }
                    }
                    if (b == super.t) {
                        d = bn2;
                    }
                }
                else {
                    if (bn2 == null) {
                        String a = aj.a(i, 0);
                        if (a == null) {
                            System.err.println("null room name received.");
                            a = new String("no name");
                        }
                        bn2 = new bn(b, this.e(a));
                        this.f.a(bn2);
                    }
                    else {
                        bn2.b(this.e(aj.a(i, 0)));
                    }
                    bn2.h = aj.b(i, 1);
                    bn2.i = aj.b(i, 2);
                    bn2.B = aj.b(i, 3);
                    bn2.g = aj.b(i, 4);
                    bn2.u = aj.b(i, 5);
                    bn2.d = aj.b(i, 6);
                    bn2.m = this.e(aj.a(i, 1));
                    bn2.o = this.e(aj.a(i, 2));
                    final r a2 = bn2.a;
                    bn2.a = aj.a(i, 0);
                    if (bn2.a != null && !bn2.a.equals(a2)) {
                        bn2.c = true;
                    }
                    bn2.a(aj.a(i));
                    if (aj.a(i, 62)) {
                        this.F = b;
                    }
                    if (this.a != null) {
                        this.a.b(bn2);
                    }
                    if (this.t != null && bn2.d().equals(this.t)) {
                        this.t = null;
                        bn = bn2;
                        bn.c = false;
                    }
                }
            }
            if (this.F == -999) {
                this.F = aj.b(0, 0);
            }
        }
        finally {
            this.f.f();
        }
        if (bn != null) {
            this.c(bn);
            this.a.a(true);
        }
        else if (d != null) {
            final aW aw = (aW)this.f.b(this.F);
            this.d(this.F);
            new S(this.a.a(), bi.Q, doook.H.a(ar.b("The room you were in, \"%1,\" has closed.  You have been moved to \"%2.\""), new String[] { this.e(d.d()), this.e(aw.d()) }), this).setVisible(true);
        }
    }
    
    protected void m(final aJ aj) {
        this.p.a(true);
        try {
            aZ b = null;
            aZ b2 = null;
            for (int i = 0; i < aj.g(); ++i) {
                int n = aj.b(i, 0);
                aZ az = (aZ)this.p.b(n);
                if (aj.a(i, 63)) {
                    this.p.c(n);
                }
                else {
                    if (az == null) {
                        az = new aZ(n, aj.a(i, 0));
                        n = this.p.a(az, n);
                        aj.a(i, 0, n);
                    }
                    else {
                        az.b(aj.a(i, 0));
                    }
                    az.a = new Color(aj.b(i, 1));
                    az.b = new Color(aj.b(i, 2));
                    az.l = new Color(aj.b(i, 3));
                    az.h = new Color(aj.b(i, 4));
                    az.f = new Color(aj.b(i, 5));
                    az.g = new Color(aj.b(i, 6));
                    az.m = new Color(aj.b(i, 7));
                    az.n = new Color(aj.b(i, 8));
                    az.o = new Color(aj.b(i, 9));
                    az.p = new Color(aj.b(i, 10));
                    az.q = new Color(aj.b(i, 11));
                    az.h = aj.a(i, 1);
                    az.ao = aj.b(i, 12);
                    az.an = aj.b(i, 13);
                    az.k();
                    az.a(aj.a(i));
                    az.a(aj.a(i, 2));
                    az.ap = aj.b(i, 14);
                    az.d = new Color(aj.b(i, 15));
                    az.e = new Color(aj.b(i, 16));
                    az.r = new Color(aj.b(i, 17));
                    az.s = new Color(aj.b(i, 18));
                    if (az.a(62)) {
                        b = (aZ)az.clone();
                    }
                    if (n == bi.at) {
                        b2 = (aZ)az.clone();
                    }
                }
            }
            if (b2 != null) {
                this.b = b2;
            }
            else if (b != null) {
                this.b = b;
            }
            if (this.a != null) {
                this.a.k();
            }
            if (this.c != null) {
                for (int j = 0; j < this.c.b(); ++j) {
                    ((ai)this.c.a(j)).f();
                }
            }
        }
        finally {
            this.p.f();
        }
    }
    
    public synchronized void n(final aJ aj) {
        this.a.a(aj);
        this.notify();
        this.notify();
    }
    
    protected void o(final aJ aj) {
        this.s.a(true);
        try {
            for (int i = 0; i < aj.g(); ++i) {
                final int b = aj.b(i, 0);
                aS as = (aS)this.s.b(b);
                if (aj.a(i, 63)) {
                    this.s.c(b);
                }
                else {
                    if (as == null) {
                        as = new aS(b, aj.a(i, 1));
                        this.s.a(as, b);
                    }
                    else {
                        as.b(aj.a(i, 1));
                    }
                    as.a(aj.a(i));
                    as.O = aj.a(i, 0);
                }
            }
        }
        finally {
            this.s.f();
        }
    }
    
    public void p(final aJ b) {
        try {
            this.G = b.a(0, 0);
            this.H = b.a(0, 1);
            this.ab = b.b(0, 0);
            this.ac = b.b(0, 1);
            this.ad = b.b(0, 2);
            this.ae = b.b(0, 3);
            this.af = b.b(0, 4);
            String line = "";
            if (b.a(0, 2) != null && b.a(0, 2).length() > 0) {
                try {
                    final String string = "http://" + av.a.getCodeBase().getHost() + "/DoookNet/rss/" + b.a(0, 2) + ".rss";
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
                bj.a.j = new Color(this.ab);
                bj.a.k = new Color(this.ac);
                bj.a.J = this.H;
                bj.a.ab = (this.ae != 1);
                bj.a.am = 30 - this.ad * 5;
            }
            bj.a.K = line;
            bj.a.setVisible(line != "");
            bj.a.width = 10;
            ((bj)this.a.a()).validate();
            ((bj)this.a.a()).repaint();
            bj.a.g = this;
            bj.b = b;
        }
        catch (Exception ex3) {}
    }
    
    public abstract void g();
    
    public void run() {
        this.a.g();
        this.a.start();
        try {
            while (!this.A || !this.a.b()) {
                while (this.a.b() && !this.A) {
                    synchronized (this) {
                        this.wait();
                    }
                }
                this.b((aJ)this.a.a());
            }
        }
        catch (Exception ex) {}
        this.A = true;
        this.k();
    }
    
    public void f() {
    }
    
    public void q(final aJ aj) {
        if (this.V) {
            System.out.println(ar.b("Reconnecting to the server, please wait ..."));
            return;
        }
        try {
            if (aj.b() == 66049 || (aj.b() == 66305 && !aj.a(0, 0).startsWith("mystatus=")) || aj.b() == 67334 || aj.b() == 67074 || aj.b() == 263680 || aj.b() == 264448) {
                this.a = Calendar.getInstance().getTime();
            }
            aj.a(this.a);
            this.a.flush();
        }
        catch (IOException ex) {
            if (!this.A) {
                this.k();
            }
        }
    }
    
    public void a(final String s, final int j, final int c, final int n, final boolean b, final boolean b2) {
        final aJ aj = new aJ(66305, 1);
        aj.a(0, 0, s);
        aj.a(0, 0, this.e());
        aj.a(0, 2, n);
        aj.a(0, 38, b);
        aj.a(0, 37, b2);
        aj.j = j;
        aj.C = c;
        this.q(aj);
    }
    
    public void a(final String s, final int n, final boolean b, final boolean b2) {
        this.a(s, -1, super.t, n, b, b2);
        this.b = System.currentTimeMillis();
    }
    
    public abstract void a(final bv p0, final F p1);
    
    public void a(final Font font) {
        this.b.h = font.getName();
        this.b.ao = font.getStyle();
        this.b.an = font.getSize();
        this.b.k();
        if (this.a != null) {
            this.a.k();
        }
        if (this.c != null) {
            for (int i = 0; i < this.c.b(); ++i) {
                ((ai)this.c.a(i)).f();
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
    
    public void b(final boolean b) {
        this.B = b;
        this.e.a(false);
        try {
            synchronized (this.e) {
                for (int i = 0; i < this.e.b(); ++i) {
                    final a a = (a)this.e.a(i);
                    if ((this.B || a.t == super.t) && this.a(a.d(), bh.h.getText())) {
                        this.a.a(a, true);
                    }
                    else {
                        this.a.a(a);
                    }
                }
            }
        }
        finally {
            this.e.f();
        }
    }
    
    public void a(final boolean b) {
    }
    
    public void c(final boolean v) {
        this.v = v;
    }
    
    public void d(final boolean w) {
        this.w = w;
    }
    
    public void e(final boolean z) {
        this.z = z;
    }
    
    public void a(final Frame frame) {
        this.a = new Menu(bi.Q);
        final MenuBar menuBar = new MenuBar();
        for (int i = 1; i <= this.q.b(); ++i) {
            this.a.add((MenuItem)this.q.b(i));
        }
        if (this.q.b() >= 1) {
            this.a.addSeparator();
        }
        if (!bi.af) {
            this.c = new MenuItem(ar.b("Settings"));
            this.a.add(this.c);
        }
        if (!this.h) {
            this.b = new MenuItem(ar.b("Logout"));
            this.a.add(this.b);
        }
        menuBar.add(this.a);
        frame.setMenuBar(menuBar);
        if (this.B != null && this.B.length() > 7) {
            as.a = new MenuItem(ar.b("Add To Favorites"));
            this.a.insert(as.a, 0);
            this.a.insertSeparator(1);
        }
        else if (as.a != null) {
            this.a.remove(1);
            this.a.remove(0);
            as.a = null;
        }
    }
    
    public static final void a(final as as) {
        F.a(new Object[] { "top.focus(); setTimeout(\"doookBookmarkSite('" + as.B + "', top.document.title);\", 200);" });
    }
    
    public void c(final int n) {
    }
    
    public void o() {
        final String a = doook.H.a(ar.b("You have been disconnected from %1 for flooding."), new String[] { bi.Q });
        final aJ aj = new aJ(50400771, 1);
        aj.j = this.e();
        aj.a(0, 0, a);
        this.q(aj);
    }
    
    public as() {
        super(-999, null);
        this.a = Calendar.getInstance().getTime();
        this.b = 0;
        this.N = false;
        this.Z = 0;
        this.D = "00000000-0000-0000-0000-000000000000";
        this.E = "00000000-0000-0000-0000-000000000000";
        this.F = "";
        this.U = true;
        this.V = false;
        this.aa = 0;
        this.E = this.D;
        this.D = "00000000-0000-0000-0000-000000000000";
        this.U = true;
        this.h = false;
        this.d = false;
        this.e = false;
        this.b = false;
        this.y = false;
        this.q = 1000;
        this.b = 0L;
        this.j = 250;
        this.v = true;
        this.w = true;
        this.x = false;
        this.z = false;
        this.a = null;
        this.c = new aT();
        this.d = new aT();
        this.e = new aT();
        this.f = new aT();
        this.g = new aT();
        this.h = new aT();
        this.i = new aT();
        this.j = new aT();
        this.k = new aT();
        this.l = new aT();
        this.m = new aT();
        this.n = new aT();
        this.o = new aT();
        this.p = new aT();
        this.q = new aT();
        this.r = new aT();
        this.s = new aT();
        this.t = new aT();
        this.e = new Vector();
        this.f = new Vector();
        this.g = new Vector();
        this.e = -999L;
        this.A = true;
        this.B = false;
        this.a = new aR();
        this.F = -999;
        this.G = -999;
        this.H = -999;
        this.C = false;
        this.D = true;
        this.E = false;
        this.I = -999;
        this.J = 2;
        this.K = -999;
        this.L = -999;
        this.M = -999;
        this.N = 0;
        this.O = 8;
        this.P = 3;
        this.Q = 4;
        this.R = 9;
        this.S = 1;
        this.T = 0;
        this.U = 7;
        this.V = 2;
        this.W = 0;
        this.X = 0;
        this.Y = 0;
        this.F = false;
        this.G = false;
        this.H = false;
        this.I = false;
        this.J = false;
        this.K = false;
        this.b = aZ.c;
        this.L = false;
    }
    
    static {
        c = new String[] { null, null, null, null, null, null, null, null, null };
        b = new String[] { "bass.au", "bell.au", "castanet.au", "chime.au", "conga.au", "cowBell.au", "doubleBell.au", "drumRoll.au", "harp.au" };
        as.O = false;
        as.P = false;
        as.Q = false;
        as.R = false;
        as.S = false;
        as.T = false;
    }
}
