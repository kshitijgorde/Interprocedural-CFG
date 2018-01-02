// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.MenuBar;
import java.awt.Menu;
import java.awt.Font;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.io.DataOutput;
import java.util.Calendar;
import java.io.IOException;
import java.awt.Color;
import java.net.MalformedURLException;
import java.awt.Button;
import java.awt.Component;
import java.awt.Event;
import java.io.DataInput;
import java.util.Date;
import java.awt.MenuItem;
import java.awt.Frame;
import java.applet.AudioClip;
import java.awt.MediaTracker;
import java.net.URL;
import java.util.Vector;
import java.net.Socket;
import java.awt.Image;

public abstract class t extends cG implements Runnable
{
    protected static final String[] b;
    protected static final String[] a;
    public static Image a;
    public static Image b;
    public boolean h;
    public boolean d;
    public boolean e;
    public boolean i;
    public boolean j;
    public boolean k;
    protected int o;
    protected long a;
    public int k;
    protected long b;
    protected boolean t;
    protected boolean u;
    protected boolean v;
    protected boolean w;
    protected cC a;
    protected cy a;
    public String n;
    protected String o;
    protected ak a;
    protected Thread a;
    protected Socket a;
    public i a;
    protected aG a;
    public aG b;
    public aG c;
    public aG d;
    public aG e;
    public aG f;
    public aG g;
    public aG h;
    public aG i;
    public aG j;
    public aG k;
    public aG l;
    public aG m;
    public aG n;
    public aG o;
    public aG p;
    public aG q;
    public aG r;
    public aG s;
    public Vector c;
    public Vector d;
    public Vector e;
    public long c;
    public long d;
    public long e;
    public long f;
    public long g;
    protected URL b;
    protected boolean x;
    protected boolean y;
    public MediaTracker a;
    protected AudioClip[] a;
    protected aE a;
    protected Frame d;
    protected int p;
    protected int q;
    protected int r;
    protected String p;
    public boolean z;
    public boolean A;
    protected String q;
    public boolean B;
    private MenuItem a;
    private MenuItem b;
    public String r;
    public String s;
    public String t;
    public String u;
    public String v;
    public String w;
    public String x;
    public String y;
    public URL c;
    public URL d;
    public URL e;
    public int s;
    public int t;
    public int u;
    public int v;
    public int w;
    public int z;
    public int A;
    public int B;
    public int C;
    public int D;
    public int E;
    public int F;
    public int G;
    public Date a;
    public int c;
    public int H;
    public int I;
    public int J;
    public int K;
    public int L;
    public boolean C;
    public boolean D;
    public boolean E;
    public boolean F;
    public boolean G;
    public boolean H;
    public bA a;
    public boolean I;
    public boolean J;
    public boolean K;
    public boolean L;
    public static boolean M;
    public static boolean N;
    public static boolean O;
    public static boolean P;
    public static boolean Q;
    public static boolean R;
    public String[] c;
    public int M;
    public String z;
    public String A;
    public String B;
    public boolean S;
    public boolean T;
    public int N;
    public String C;
    public String D;
    public int O;
    public int P;
    public int Q;
    public int R;
    public int S;
    
    public boolean d() {
        this.a.setSoTimeout(10000);
        final cD cd = new cD(this.a);
        if (cd.b() == 67587) {
            this.b = cd.a(0, 0);
            this.a.setSoTimeout(0);
            final cD cd2 = new cD(67587, 1);
            cd2.a(0, 0, 288233674753703937L);
            cd2.a(0, 0, doook.z.I);
            cd2.a(0, 21, this.J);
            this.o(cd2);
            this.I = false;
            this.K = false;
            this.L = false;
            this.S = false;
            this.c = 0;
            return true;
        }
        return false;
    }
    
    public String d(String s) {
        if (s == null || !this.D) {
            return s;
        }
        this.h.a(false);
        try {
            synchronized (this.h) {
                for (int i = 0; i < this.h.b(); ++i) {
                    s = ((ax)this.h.a(i)).e(s);
                }
            }
        }
        finally {
            this.h.a();
        }
        this.i.a(false);
        try {
            synchronized (this.i) {
                for (int j = 0; j < this.i.b(); ++j) {
                    s = ((ax)this.i.a(j)).e(s);
                }
            }
        }
        finally {
            this.i.a();
        }
        return s;
    }
    
    public String a(String ah, final cG cg) {
        if (ah == null || !this.D) {
            return ah;
        }
        this.h.a(false);
        try {
            synchronized (this.h) {
                final cE ce = new cE(ah, cg.d(61) ? 1 : 0);
                for (int i = 0; i < this.h.b(); ++i) {
                    ah = ((ax)this.h.a(i)).a(ce);
                    ce.ah = ah;
                }
            }
        }
        finally {
            this.h.a();
        }
        this.i.a(false);
        try {
            synchronized (this.i) {
                for (int j = 0; j < this.i.b(); ++j) {
                    ah = ((ax)this.i.a(j)).e(ah);
                }
            }
        }
        finally {
            this.i.a();
        }
        return ah;
    }
    
    protected abstract void a();
    
    public void a(final String p6, final String s, final String s2, final boolean b, final int n, final int n2) {
        this.p = p6;
        final cD cd = new cD(67330, 1);
        cd.j = -1;
        cd.o = -1;
        cd.k = this.h();
        cd.a(0, 0, -999);
        cd.a(0, 5, n);
        cd.a(0, 6, n2);
        cd.a(0, 0, p6);
        cd.a(0, 1, s);
        cd.a(0, 0, new a(s2));
        if (!b) {
            cd.b(0, 58);
        }
        this.o(cd);
    }
    
    public void h() {
        new ag(this.a.a(), this).setVisible(true);
    }
    
    public void a(final String p5, final String s, final boolean b, final int n, final int n2) {
        this.p = p5;
        final cD cd = new cD(67330, 1);
        cd.j = -1;
        cd.o = -1;
        cd.k = this.h();
        cd.a(0, 0, -999);
        cd.a(0, 5, n);
        cd.a(0, 6, n2);
        cd.a(0, 0, p5);
        cd.a(0, 1, s);
        if (!b) {
            cd.b(0, 58);
        }
        this.o(cd);
    }
    
    public abstract void a(final cD p0);
    
    public void e(final int n) {
        final T t = (T)this.d.b(n);
        if (t != null) {
            this.c(t);
        }
        else {
            System.err.println("no such room! " + n);
        }
    }
    
    public void c(final T t) {
        if (t.m && t.a != null) {
            new ae(this.a.a(), this, t).setVisible(true);
        }
        if (!t.m || t.a == null) {
            final cD cd = new cD(66049, 1);
            cd.a(0, 0, this.h());
            cd.a(0, 1, t.h());
            cd.j = -1;
            cd.o = -1;
            this.o(cd);
        }
    }
    
    public long a() {
        return this.a;
    }
    
    public int f() {
        return this.k;
    }
    
    public boolean e() {
        return this.t;
    }
    
    public boolean f() {
        return this.u;
    }
    
    public boolean g() {
        return this.w;
    }
    
    public int e(final int n) {
        int n2 = 0;
        this.c.a(false);
        try {
            synchronized (this.c) {
                for (int i = 0; i < this.c.b(); ++i) {
                    final ab ab = (ab)this.c.a(i);
                    if (ab.h == n && (!ab.d(23) || this.d(24))) {
                        ++n2;
                    }
                }
            }
        }
        finally {
            this.c.a();
        }
        return n2;
    }
    
    public boolean a(final Event event) {
        if (event.target == this.a) {
            this.i();
        }
        else if (this.r.a(event.target)) {
            this.a((URL)this.s.a(this.r.a(event.target)), "_blank");
        }
        else if (event.target == this.b) {
            this.c(0);
        }
        return true;
    }
    
    protected static boolean b(final String s) {
        return cG.ax && !s.startsWith("status") && !s.toLowerCase().startsWith("doook") && !s.equalsIgnoreCase("chatLogo.gif");
    }
    
    public Image a(final String s, final boolean b) {
        try {
            MediaTracker a;
            if (this.a == null) {
                final Button button;
                a = new MediaTracker(button);
                button = new Button();
            }
            else {
                a = this.a;
            }
            final MediaTracker mediaTracker = a;
            URL url;
            if (b || b(s)) {
                url = new URL(this.b, "Resources/" + this.n + "/" + s);
            }
            else {
                url = new URL(this.b, "Resources/" + s);
            }
            Image a2 = this.a(url);
            if (a2 != null) {
                final int n = this.o++;
                mediaTracker.addImage(a2, n);
                try {
                    mediaTracker.waitForID(n);
                }
                catch (InterruptedException ex) {}
                if (mediaTracker.isErrorID(n)) {
                    a2 = null;
                }
            }
            return a2;
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
    
    public abstract void a(final String p0, final String p1, final a p2, final String p3, final int p4);
    
    public void i() {
        if (this.a != null) {
            this.S = true;
            final cD cd = new cD(65794, 1);
            cd.a(0, 0, this.h());
            cd.a(0, 0, this.u);
            cd.j = -1;
            cd.o = -1;
            this.o(cd);
            super.h = -999;
            if (this.a != null) {
                final Frame a = this.a.a();
                this.a.a();
                if (a != null) {
                    a.dispose();
                }
            }
        }
        if (this.S) {
            this.j = true;
            this.x = true;
            this.n(new cD(66561, 0));
            this.e = false;
            this.i = false;
            if (this.e != null) {
                this.a(this.e, "_self");
            }
        }
        this.a();
    }
    
    public void a(final Z z, final ab ab) {
        System.out.println("1");
    }
    
    public void a(final Z z) {
    }
    
    public void a(final byte[] array) {
    }
    
    public abstract void a(final URL p0, final String p1);
    
    public void a(final int n) {
    }
    
    protected void b(final cD cd) {
        for (int i = 0; i < cd.g(); ++i) {
            final ab ab = (ab)this.c.b(cd.b(i, 0));
            if (ab != null) {
                this.a(ab, cd.b(i, 1), cd.c(), cd.l());
            }
        }
    }
    
    protected abstract void a(final ab p0, final int p1, final long p2, final int p3);
    
    protected void c(final cD cd) {
        if (this.d != null) {
            this.a(this.d, "_blank");
        }
        else {
            new E(new Frame(), ao.e("You have been kicked"), cd.a(0, 0), this).setVisible(true);
        }
        this.x = true;
        this.S = true;
        this.n(new cD(66561, 0));
        this.a();
    }
    
    protected void d(final cD cd) {
        final String[] array = new String[cd.g()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = cd.a(i, 0);
        }
        new E(this.a.a(), ao.e("Operation not allowed"), array, this).setVisible(true);
    }
    
    protected void e(final cD cd) {
        String s = null;
        final int b = cd.b(0, 1);
        switch (cd.b(0, 0)) {
            case 2: {
                s = am.a(ao.e("You may not connect to %1 because your account has been disabled."), new String[] { doook.z.G });
                break;
            }
            case 4: {
                s = am.a(ao.e("You may not connect to %1 because the maximum number of simultaneous connections for this site has been reached."), new String[] { doook.z.G });
                break;
            }
            case 3: {
                s = am.a(ao.e("You may not connect to %1 because the version of the Client is not compatible with the version of Server."), new String[] { doook.z.G, doook.b.a(), doook.b.a(this.b) });
                break;
            }
            case 5: {
                s = am.a(ao.e("You may not connect to %1 because you have been banned from this %1 site."), new String[] { doook.z.G });
                break;
            }
            case 6: {
                s = am.a(ao.e("You may not connect to %1 because no site with the specified ID could be found."), new String[] { doook.z.G });
                break;
            }
            case 8: {
                s = am.a(ao.e("You are attempting to connect to %1 from an invalid host. Please contact the %1 Site administrator."), new String[] { doook.z.G });
                break;
            }
            case 10: {
                s = am.a(ao.e("You may not connect to %1 because the Server license has expired."), new String[] { doook.z.G });
                break;
            }
            case 11: {
                s = am.a(ao.e("You may not connect to %1 because your nickname is longer than 35 characters.  Please re-enter this information and login again."), new String[] { doook.z.G });
                break;
            }
            case 9: {
                s = am.a(ao.e("You may not connect to %1 because the Server license has been exceeded."), new String[] { doook.z.G });
                break;
            }
            case 0: {
                if (b == 65793) {
                    s = am.a(ao.e("You may not connect to %1 because another user is already using the name \"%2.\" Please choose another and login again."), new String[] { doook.z.G, this.f() });
                    break;
                }
                s = am.a(ao.e("Your nickname could not be changed because the name \"%1\" is already in use by another user, or is reserved."), new String[] { cd.a(0, 0) });
                break;
            }
            case 1:
            case 7: {
                if (b == 65793) {
                    s = am.a(ao.e("You may not connect to %1 because your name or password was not entered correctly.  Please re-enter this information and login again."), new String[] { doook.z.G });
                    break;
                }
                break;
            }
        }
        if (s == null) {
            s = ao.e("Your request could not be fulfilled.");
        }
        if (b == 65793) {
            this.x = true;
            this.n(new cD(66561, 0));
            this.S = true;
            this.a();
        }
        new E(this.d, ao.e("Note"), s, this).setVisible(true);
    }
    
    protected void f(final cD cd) {
        if (!this.x) {
            new E((this.a != null) ? this.a.a() : null, ao.e("Alert"), new String[] { cd.a(0, 0), cd.a(0, 1) }, this).setVisible(true);
        }
    }
    
    protected void g(final cD cd) {
        for (int i = 0; i < cd.g(); ++i) {
            new E((this.a != null) ? this.a.a() : null, cd.a(i, 0), cd.a(i, 1), this).setVisible(true);
        }
    }
    
    protected void h(final cD cd) {
        new E(this.a.a(), ao.e("Inactivity Timeout"), am.a(ao.e("You have been disconnected because you have been inactive for the past %1 minutes."), new String[] { String.valueOf(cd.b(0, 0)) }), this).setVisible(true);
        this.x = true;
        this.S = true;
        this.n(new cD(66561, 0));
        this.a();
    }
    
    protected void i(final cD cd) {
        final long a = cd.a(-1);
        if (this.d == -999L && cd.b(-1, 60)) {
            this.D = true;
        }
        aG ag;
        if (cd.b(-1, 59)) {
            if ("Admin".equals(this.n)) {
                this.d = a;
                ag = this.h;
            }
            else {
                ag = this.i;
            }
            this.e = a;
        }
        else {
            ag = this.h;
            this.d = a;
        }
        if (cD.a(this.e | this.d, 62) || cD.a(this.e | this.d, 61)) {
            this.D = true;
        }
        try {
            ag.a(true);
            for (int i = 0; i < cd.g(); ++i) {
                final int b = cd.b(i, 0);
                ax ax = (ax)ag.b(b);
                if (cd.b(i, 63)) {
                    if (ax != null) {
                        ag.c(b);
                    }
                }
                else {
                    if (ax == null) {
                        ax = new ax(b, cd.a(i, 0));
                        ag.a(ax);
                    }
                    else {
                        ax.d(cd.a(i, 0));
                    }
                    ax.a = cd.a(i, 1);
                    ax.aN = cd.b(i, 1);
                    ax.a(cd.a(i));
                }
            }
        }
        finally {
            ag.a();
        }
    }
    
    protected void j(final cD cd) {
        final aG j = this.j;
        try {
            j.a(true);
            for (int i = 0; i < cd.g(); ++i) {
                final int b = cd.b(i, 0);
                ax ax = (ax)j.b(b);
                if (cd.b(i, 63)) {
                    if (ax != null) {
                        j.c(b);
                    }
                }
                else {
                    if (ax == null) {
                        ax = new ax(b, cd.a(i, 0));
                        j.a(ax);
                    }
                    else {
                        ax.d(cd.a(i, 0));
                    }
                    ax.a = cd.a(i, 1);
                    ax.aN = cd.b(i, 1);
                    ax.d = cd.b(i, 2);
                    ax.a(cd.a(i));
                }
            }
        }
        finally {
            j.a();
        }
    }
    
    protected void k(final cD cd) {
        this.o = cd.a(0, 0);
    }
    
    protected void l(final cD cd) {
        T t = null;
        cF cf = null;
        this.d.a(true);
        try {
            for (int i = 0; i < cd.g(); ++i) {
                final int b = cd.b(i, 0);
                T t2 = (T)this.d.b(b);
                if (cd.b(i, 63) || cd.a(i, 0) == null) {
                    if (t2 != null) {
                        this.d.c(b);
                        if (this.a != null) {
                            this.a.a(t2);
                        }
                    }
                    if (b == super.h) {
                        cf = t2;
                    }
                }
                else {
                    if (t2 == null) {
                        String a = cd.a(i, 0);
                        if (a == null) {
                            System.err.println("null room name received.");
                            a = new String("no name");
                        }
                        t2 = new T(b, this.d(a));
                        this.d.a(t2);
                    }
                    else {
                        t2.d(this.d(cd.a(i, 0)));
                    }
                    t2.a = cd.b(i, 1);
                    t2.b = cd.b(i, 2);
                    t2.Z = cd.b(i, 3);
                    t2.i = cd.b(i, 4);
                    t2.aN = cd.b(i, 5);
                    t2.d = cd.b(i, 6);
                    t2.a = this.d(cd.a(i, 1));
                    t2.R = this.d(cd.a(i, 2));
                    final a a2 = t2.a;
                    t2.a = cd.a(i, 0);
                    if (t2.a != null && !t2.a.equals(a2)) {
                        t2.m = true;
                    }
                    t2.a(cd.a(i));
                    if (cd.b(i, 62)) {
                        this.p = b;
                    }
                    if (this.a != null) {
                        this.a.b(t2);
                    }
                    if (this.p != null && t2.f().equals(this.p)) {
                        this.p = null;
                        t = t2;
                        t.m = false;
                    }
                }
            }
            if (this.p == -999) {
                this.p = cd.b(0, 0);
            }
        }
        finally {
            this.d.a();
        }
        if (t != null) {
            this.c(t);
            this.a.a(true);
        }
        else if (cf != null) {
            final av av = (av)this.d.b(this.p);
            this.e(this.p);
            new E(this.a.a(), doook.z.G, am.a(ao.e("The room you were in, \"%1,\" has closed.  You have been moved to \"%2.\""), new String[] { this.d(cf.f()), this.d(av.f()) }), this).setVisible(true);
        }
    }
    
    protected void m(final cD cd) {
        this.q.a(true);
        try {
            bA a = null;
            bA a2 = null;
            for (int i = 0; i < cd.g(); ++i) {
                int n = cd.b(i, 0);
                bA ba = (bA)this.q.b(n);
                if (cd.b(i, 63)) {
                    this.q.c(n);
                }
                else {
                    if (ba == null) {
                        ba = new bA(n, cd.a(i, 0));
                        n = this.q.a(ba, n);
                        cd.a(i, 0, n);
                    }
                    else {
                        ba.d(cd.a(i, 0));
                    }
                    ba.a = new Color(cd.b(i, 1));
                    ba.b = new Color(cd.b(i, 2));
                    ba.i = new Color(cd.b(i, 3));
                    ba.k = new Color(cd.b(i, 4));
                    ba.f = new Color(cd.b(i, 5));
                    ba.g = new Color(cd.b(i, 6));
                    ba.l = new Color(cd.b(i, 7));
                    ba.m = new Color(cd.b(i, 8));
                    ba.n = new Color(cd.b(i, 9));
                    ba.o = new Color(cd.b(i, 10));
                    ba.p = new Color(cd.b(i, 11));
                    ba.i = cd.a(i, 1);
                    ba.af = cd.b(i, 12);
                    ba.ae = cd.b(i, 13);
                    ba.b();
                    ba.a(cd.a(i));
                    ba.a(cd.a(i, 2));
                    ba.aE = cd.b(i, 14);
                    ba.d = new Color(cd.b(i, 15));
                    ba.e = new Color(cd.b(i, 16));
                    ba.q = new Color(cd.b(i, 17));
                    ba.r = new Color(cd.b(i, 18));
                    if (ba.d(62)) {
                        a = (bA)ba.clone();
                    }
                    if (n == doook.z.X) {
                        a2 = (bA)ba.clone();
                    }
                }
            }
            if (a2 != null) {
                this.a = a2;
            }
            else if (a != null) {
                this.a = a;
            }
            if (this.a != null) {
                this.a.b();
            }
            if (this.a != null) {
                for (int j = 0; j < this.a.b(); ++j) {
                    ((aj)this.a.a(j)).a();
                }
            }
        }
        finally {
            this.q.a();
        }
    }
    
    public synchronized void n(final cD cd) {
        this.a.a(cd);
        this.notify();
        this.notify();
    }
    
    public void j() {
        this.i();
        try {
            this.d();
        }
        catch (IOException ex) {}
    }
    
    public abstract void f();
    
    public void run() {
        this.a.d();
        try {
            this.a.start();
            while (!this.x || !this.a.a()) {
                while (this.a.a() && !this.x) {
                    synchronized (this) {
                        this.wait();
                    }
                }
                this.a((cD)this.a.b());
            }
        }
        catch (InterruptedException ex) {
            if (this.x) {}
            this.x = true;
            this.a();
        }
    }
    
    public void d() {
    }
    
    public void o(final cD cd) {
        if (this.T) {
            System.out.print(ao.e("Reconnecting to the server, please wait ..."));
            return;
        }
        try {
            if (cd.b() == 66049 || (cd.b() == 66305 && !cd.a(0, 0).startsWith("mystatus=")) || cd.b() == 67334 || cd.b() == 67074 || cd.b() == 263680 || cd.b() == 264448) {
                this.a = Calendar.getInstance().getTime();
            }
            cd.a(this.a);
            this.a.flush();
        }
        catch (IOException ex) {
            if (!this.x) {
                this.a();
            }
        }
    }
    
    public void p(final cD a) {
        if (!"Admin".equals(this.n)) {
            try {
                this.C = a.a(0, 0);
                this.D = a.a(0, 1);
                this.O = a.b(0, 0);
                this.P = a.b(0, 1);
                this.Q = a.b(0, 2);
                this.R = a.b(0, 3);
                this.S = a.b(0, 4);
                String line = "";
                if (a.a(0, 2) != null && a.a(0, 2).length() > 0) {
                    try {
                        final String string = "http://" + cI.b.getCodeBase().getHost() + "/DoookNet/rss/" + a.a(0, 2) + ".rss";
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
                    doook.S.a.s = new Color(this.O);
                    doook.S.a.t = new Color(this.P);
                    doook.S.a.af = this.D;
                    doook.S.a.at = (this.R != 1);
                    doook.S.a.aK = 30 - this.Q * 5;
                }
                doook.S.a.ag = line;
                doook.S.a.setVisible(line != "");
                doook.S.a.width = 10;
                ((S)this.a.a()).validate();
                ((S)this.a.a()).repaint();
                doook.S.a.l = this;
                doook.S.a = a;
            }
            catch (Exception ex3) {}
        }
    }
    
    public void a(final String s, final int j, final int o, final int n, final boolean b, final boolean b2) {
        final cD cd = new cD(66305, 1);
        cd.a(0, 0, s);
        cd.a(0, 0, this.h());
        cd.a(0, 2, n);
        cd.a(0, 38, b);
        cd.a(0, 37, b2);
        cd.j = j;
        cd.o = o;
        this.o(cd);
    }
    
    public void a(final String s, final int n, final boolean b, final boolean b2) {
        this.a(s, -1, super.h, n, b, b2);
        this.a = System.currentTimeMillis();
    }
    
    public abstract void a(final Z p0, final cG p1);
    
    public void a(final Font font) {
        this.a.i = font.getName();
        this.a.af = font.getStyle();
        this.a.ae = font.getSize();
        this.a.b();
        if (this.a != null) {
            this.a.b();
        }
        if (this.a != null) {
            for (int i = 0; i < this.a.b(); ++i) {
                ((aj)this.a.a(i)).a();
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
    
    public void c(final boolean y) {
        this.y = y;
        this.c.a(false);
        try {
            synchronized (this.c) {
                for (int i = 0; i < this.c.b(); ++i) {
                    final ab ab = (ab)this.c.a(i);
                    if (super.av) {
                        if (ab.e > 0 && (this.y || ab.h == super.h) && this.a(ab.f(), doook.R.i.getText())) {
                            this.a.a(ab, true);
                        }
                        else {
                            this.a.a(ab);
                        }
                    }
                    else if ((this.y || ab.h == super.h) && this.a(ab.f(), doook.R.i.getText())) {
                        this.a.a(ab, true);
                    }
                    else {
                        this.a.a(ab);
                    }
                }
            }
        }
        finally {
            this.c.a();
        }
    }
    
    public void b(final boolean b) {
    }
    
    public void d(final boolean t) {
        this.t = t;
    }
    
    public void e(final boolean u) {
        this.u = u;
    }
    
    public void a(final Frame frame) {
        final Menu menu = new Menu(doook.z.G);
        final MenuBar menuBar = new MenuBar();
        for (int i = 1; i <= this.r.b(); ++i) {
            menu.add((MenuItem)this.r.b(i));
        }
        if (this.r.b() >= 1) {
            menu.addSeparator();
        }
        if (!doook.z.X) {
            menu.add(this.b = new MenuItem(ao.e("Settings")));
        }
        if (!this.d) {
            menu.add(this.a = new MenuItem(ao.e("Logout")));
        }
        menuBar.add(menu);
        frame.setMenuBar(menuBar);
    }
    
    public void c(final int n) {
    }
    
    public void k() {
        final String a = am.a(ao.e("You have been disconnected from %1 for flooding."), new String[] { doook.z.G });
        final cD cd = new cD(50400771, 1);
        cd.j = this.h();
        cd.a(0, 0, a);
        this.o(cd);
    }
    
    public static boolean a(final int n, final u u) {
        if (n == 4 && cI.r != null) {
            cI.f(u);
            return true;
        }
        if (n == 12 && cI.p != null) {
            cI.b(u);
            return true;
        }
        if (n == 15 && cI.j != null) {
            cI.e(u);
            return true;
        }
        if (n == 19 && cI.o != null) {
            cI.c(u);
            return true;
        }
        if (n == 14 && cI.n != null) {
            cI.a(u);
            return true;
        }
        return false;
    }
    
    public t() {
        super(-999, null);
        this.a = Calendar.getInstance().getTime();
        this.c = 0;
        this.M = 0;
        this.z = "00000000-0000-0000-0000-000000000000";
        this.A = "00000000-0000-0000-0000-000000000000";
        this.B = "";
        this.S = true;
        this.T = false;
        this.N = 0;
        this.A = this.z;
        super.av = false;
        this.z = "00000000-0000-0000-0000-000000000000";
        this.S = true;
        this.d = false;
        this.e = false;
        this.i = false;
        this.j = false;
        this.k = false;
        this.o = 1000;
        this.a = 0L;
        this.k = 250;
        this.t = true;
        this.u = true;
        this.v = false;
        this.w = false;
        this.a = null;
        this.a = new aG();
        this.b = new aG();
        this.c = new aG();
        this.d = new aG();
        this.e = new aG();
        this.f = new aG();
        this.g = new aG();
        this.h = new aG();
        this.j = new aG();
        this.i = new aG();
        this.m = new aG();
        this.n = new aG();
        this.o = new aG();
        this.k = new aG();
        this.l = new aG();
        this.p = new aG();
        this.q = new aG();
        this.r = new aG();
        this.s = new aG();
        this.c = new Vector();
        this.d = new Vector();
        this.e = new Vector();
        this.d = -999L;
        this.x = true;
        this.y = false;
        this.a = new aE();
        this.p = -999;
        this.q = -999;
        this.r = -999;
        this.z = false;
        this.A = true;
        this.B = false;
        this.s = -999;
        this.t = 2;
        this.u = -999;
        this.v = -999;
        this.w = -999;
        this.z = -999;
        this.A = 0;
        this.B = 8;
        this.C = 3;
        this.D = 4;
        this.E = 9;
        this.F = 1;
        this.G = 0;
        this.H = 7;
        this.I = 2;
        this.J = 0;
        this.K = 0;
        this.L = 0;
        this.C = false;
        this.D = false;
        this.E = false;
        this.F = false;
        this.G = false;
        this.H = false;
        this.a = bA.b;
        this.I = false;
        this.K = false;
        this.L = false;
        doook.y.b.clear();
    }
    
    static {
        b = new String[9];
        a = new String[] { "bass.au", "bell.au", "castanet.au", "chime.au", "conga.au", "cowBell.au", "doubleBell.au", "drumRoll.au", "harp.au" };
        t.M = false;
        t.N = false;
        t.O = false;
        t.P = false;
        t.Q = false;
        t.R = false;
    }
}
