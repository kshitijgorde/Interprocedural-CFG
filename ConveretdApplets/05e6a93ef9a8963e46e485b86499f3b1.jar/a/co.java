// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Enumeration;
import java.awt.Font;
import java.io.IOException;
import java.io.DataOutput;
import java.util.NoSuchElementException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Button;
import java.awt.Image;
import java.net.MalformedURLException;
import java.awt.Frame;
import java.applet.AudioClip;
import java.awt.MediaTracker;
import java.net.URL;
import java.util.Vector;
import java.util.Hashtable;
import java.net.Socket;

public abstract class co extends l implements bb, bq, Runnable
{
    public boolean t;
    public boolean y;
    public boolean u;
    public boolean i;
    public boolean o;
    private int A;
    private boolean Q;
    private boolean W;
    public boolean p;
    private boolean E;
    public boolean a;
    public boolean s;
    public boolean d;
    public boolean f;
    protected bz eis;
    protected a eos;
    public static String p;
    protected f q;
    protected Thread q;
    protected Socket q;
    public an q;
    protected B q;
    public B w;
    public B e;
    public B r;
    public B t;
    public B y;
    public B u;
    public B i;
    public B o;
    public B p;
    private B l;
    public B a;
    public B s;
    public static B d;
    public O q;
    public B f;
    public B g;
    public B h;
    public B j;
    public B k;
    public Hashtable q;
    private B z;
    public Vector q;
    public long e;
    private long t;
    protected URL q;
    protected boolean g;
    protected boolean h;
    public MediaTracker q;
    protected AudioClip[] q;
    protected bh q;
    protected bh w;
    public Frame q;
    protected int g;
    protected int h;
    protected int j;
    private String d;
    public boolean j;
    public String a;
    public boolean k;
    public String s;
    public URL w;
    public URL e;
    public URL r;
    public int k;
    public int l;
    public int z;
    public int x;
    public int c;
    public int v;
    public int b;
    public int n;
    public long r;
    public int m;
    public boolean l;
    public boolean z;
    public boolean x;
    public boolean c;
    public boolean v;
    public int Q;
    public boolean b;
    public boolean n;
    public boolean m;
    protected aA q;
    protected aA w;
    private Vector w;
    public int W;
    public int E;
    public int R;
    public int T;
    public int Y;
    public int U;
    public int I;
    public int O;
    public int P;
    
    public final boolean a_() {
        return this.g;
    }
    
    public final bz q() {
        return this.eis;
    }
    
    public final B w() {
        return this.o;
    }
    
    public final boolean r() {
        return this.u;
    }
    
    public final B t() {
        return this.e;
    }
    
    public final Hashtable q() {
        return this.q;
    }
    
    public final B y() {
        return this.q;
    }
    
    public final B u() {
        return this.f;
    }
    
    public final void q(final long n) {
        super.q(n);
    }
    
    public final long w() {
        return this.r;
    }
    
    public final boolean q(final String s, final Object o) {
        return super.q(s, o);
    }
    
    public final B e() {
        return this.w;
    }
    
    public final B r() {
        return this.y;
    }
    
    public final int r() {
        return this.l;
    }
    
    public final B i() {
        return this.i;
    }
    
    public final boolean t() {
        return this.c;
    }
    
    public final boolean y() {
        return this.v;
    }
    
    public final boolean q(final int n, final boolean b) {
        final cp cp;
        (cp = new cp(67587, 1)).q(0, 0, n);
        cp.q(0, 1, this.n ? 1 : 0);
        cp.q(0, 0, b);
        this.r(cp);
        return true;
    }
    
    public final void w(final l l) {
        super.q(l.q());
        this.k = l.e;
        this.r = l.r;
        this.i(l.w());
        this.u(l.f);
        this.t = l.t;
        this.o = l.o;
        this.y = l.y;
        this.u = l.u;
        super.p = l.p;
    }
    
    public final String w(String s) {
        if (s == null || !this.l) {
            return s;
        }
        try {
            synchronized (this.o) {
                for (int i = 0; i < this.o.q; ++i) {
                    s = ((aN)this.o.q(i)).q(s);
                }
            }
        }
        finally {}
        try {
            synchronized (this.l) {
                for (int j = 0; j < this.l.q; ++j) {
                    s = ((aN)this.l.q(j)).q(s);
                }
            }
        }
        finally {}
        return;
    }
    
    public final void y(final cp cp) {
        for (int i = 0; i < cp.w(); ++i) {
            final l l;
            if ((l = (l)this.e.w(cp.q(i, 0))) != null) {
                if (this.x) {
                    final String q;
                    if ((q = cp.q(i, 2)) != null) {
                        try {
                            this.q(new URL(q), "_blank");
                        }
                        catch (MalformedURLException ex) {}
                    }
                }
                else {
                    new i(this.q.q(), this, l, cp, i).setVisible(true);
                }
            }
        }
    }
    
    public final String q(String s) {
        if (s != null && this.l) {
            try {
                synchronized (this.o) {
                    for (int i = 0; i < this.o.q; ++i) {
                        s = ((aN)this.o.q(i)).q(s);
                    }
                }
            }
            finally {}
            try {
                synchronized (this.l) {
                    for (int j = 0; j < this.l.q; ++j) {
                        s = ((aN)this.l.q(j)).q(s);
                    }
                }
            }
            finally {}
        }
        return s;
    }
    
    public final String e(String q) {
        if (q != null) {
            try {
                synchronized (this.z) {
                    for (int i = 0; i < this.z.q; ++i) {
                        q = ((ag)this.z.q(i)).q(q);
                    }
                }
            }
            finally {}
        }
        return q;
    }
    
    public final void q(final String d, final String s, final String s2, final boolean b) {
        this.d = d;
        final cp cp;
        (cp = new cp(67330, 1)).w = -1;
        cp.q = -1;
        cp.e = super.a;
        cp.q(0, 0, -999);
        cp.q(0, 0, d);
        cp.q(0, 1, s);
        cp.q(0, 0, new ck(s2));
        if (!b) {
            cp.q(0, 58);
        }
        this.r(cp);
    }
    
    public final void t() {
        new aQ(this.q.q(), this).setVisible(true);
    }
    
    public final void q(final String d, final String s, final boolean b) {
        this.d = d;
        final cp cp;
        (cp = new cp(67330, 1)).w = -1;
        cp.q = -1;
        cp.e = super.a;
        cp.q(0, 0, -999);
        cp.q(0, 0, d);
        cp.q(0, 1, s);
        if (!b) {
            cp.q(0, 58);
        }
        this.r(cp);
    }
    
    public final void t(final int n) {
        final bm bm;
        if ((bm = (bm)this.y.w(n)) != null) {
            this.q(bm);
            return;
        }
        System.err.println("no such room! " + n);
    }
    
    public final void q(bm bm) {
        ck ck;
        if (bm.w && bm.q != null && !this.q(40)) {
            final bd bd;
            (bd = new bd(this.q.q(), this, bm)).setVisible(true);
            ck = bd.q;
        }
        else {
            ck = bm.q;
        }
        final bm bm2 = bm;
        final ck ck2 = ck;
        bm = bm2;
        if (!bm.w || bm.q == null || this.q(40)) {
            final cp cp;
            (cp = new cp(66049, 1)).q(0, 0, super.a);
            cp.q(0, 1, bm.a);
            cp.q(0, 0, ck2);
            cp.w = -1;
            cp.q = -1;
            this.r(cp);
        }
    }
    
    public final boolean u() {
        return this.Q;
    }
    
    public final boolean i() {
        return this.W;
    }
    
    public final boolean o() {
        return this.E;
    }
    
    public final int q(final int n) {
        int n2 = 0;
        try {
            synchronized (this.e) {
                for (int i = 0; i < this.e.q; ++i) {
                    final as as;
                    if ((as = (as)this.e.q(i)).r == n && (!as.q(23) || this.q(24)) && (!as.q(25) || this.q(32))) {
                        ++n2;
                    }
                }
            }
        }
        finally {}
        System.out.println("Counted users for room:" + this.y.w(n) + " count=" + n2);
        return n2;
    }
    
    public final Image r(String s2, boolean b) {
        b = (boolean)((this.q != null) ? this.q : new MediaTracker(new Button()));
        Image q;
        if ((q = this.q(s, true)) != null) {
            this.q.q(s, q);
            s2 = (String)(this.A++);
            ((MediaTracker)b).addImage(q, (int)s2);
            try {
                ((MediaTracker)b).waitForID((int)s2);
            }
            catch (InterruptedException ex) {}
            if (((MediaTracker)b).isErrorID((int)s2)) {
                q = null;
            }
        }
        return q;
    }
    
    public Image q(final URL url) {
        return null;
    }
    
    public Image q(final String s, final boolean b) {
        return null;
    }
    
    public final void y() {
        for (int q = this.f.q, i = 0; i < q; ++i) {
            ((aP)this.f.q(i)).dispose();
        }
        if (this.eos != null) {
            final cp cp;
            (cp = new cp(65794, 1)).q(0, 0, super.a);
            cp.q(0, 0, this.t);
            cp.w = -1;
            cp.q = -1;
            this.r(cp);
            super.r = -999;
            this.g = -999;
            if (this.q != null) {
                final Frame q2 = this.q.q();
                this.q.q();
                if (q2 != null) {
                    q2.dispose();
                }
            }
        }
        this.g = true;
        this.w();
        this.u = false;
        this.i = false;
        if (this.r != null) {
            this.q(this.r, "_self");
        }
    }
    
    public void q(final int n) {
    }
    
    protected final void u(final cp cp) {
        for (int i = 0; i < cp.w(); ++i) {
            final as as;
            if ((as = (as)this.e.w(cp.q(i, 0))) != null) {
                this.q(as, cp.q(i, 1), cp.q, 0);
            }
        }
    }
    
    protected abstract void q(final as p0, final int p1, final long p2, final int p3);
    
    protected final void i(final cp cp) {
        this.g = true;
        this.w();
        if (this.e != null) {
            this.q(this.e, "_blank");
            return;
        }
        new p(new Frame(), al.q("You have been kicked"), cp.q(0, 0), this).setVisible(true);
    }
    
    protected final void o(final cp cp) {
        final String[] array = new String[cp.w()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = cp.q(i, 0);
        }
        new p(this.q.q(), al.q("Operation not allowed"), array, this).setVisible(true);
    }
    
    public final void p(final cp cp) {
        final int q = cp.q(0, 0);
        Label_0184: {
            final int q2;
            switch (q2 = cp.q(0, 1)) {
                default: {
                    switch (q) {
                        case 0: {
                            a.t.q(al.q("Your nickname could not be changed because the name \"%1\" is already in use by another user, or is reserved."), new String[] { cp.q(0, 0) });
                            break Label_0184;
                        }
                        case 3: {
                            a.t.q(al.q("You may not connect to %1 because the version of the %1 Client (%2) is not compatible with the version of %1 Server (%3)."), new String[] { cs.e, "1", "2" });
                            break Label_0184;
                        }
                        case 9: {
                            a.t.q(al.q("You may not connect to %1 because the Server license has been exceeded."), new String[] { cs.e });
                            break Label_0184;
                        }
                        case 10: {
                            a.t.q(al.q("You may not connect to %1 because the Server license has expired."), new String[] { cs.e });
                            break Label_0184;
                        }
                        default: {
                            cp.q(0, 0);
                            break Label_0184;
                        }
                    }
                    break;
                }
                case 65793: {
                    String s = null;
                    switch (q) {
                        case 0: {
                            s = a.t.q(al.q("You may not connect to %1 because another user is already using the name \"%2.\" Please choose another and login again."), new String[] { cs.e, super.o });
                            break;
                        }
                        case 1:
                        case 7: {
                            s = a.t.q(al.q("You may not connect to %1 because your name or password was not entered correctly.  Please re-enter this information and login again."), new String[] { cs.e });
                            break;
                        }
                        case 2: {
                            s = a.t.q(al.q("You may not connect to %1 because your account has been disabled."), new String[] { cs.e });
                            break;
                        }
                        case 4: {
                            s = a.t.q(al.q("You may not connect to %1 because the maximum number of simultaneous connections for this site has been reached."), new String[] { cs.e });
                            break;
                        }
                        case 5: {
                            s = a.t.q(al.q("You may not connect to %1 because you have been banned from this %1 site."), new String[] { cs.e });
                            break;
                        }
                        case 6: {
                            s = a.t.q(al.q("You may not connect to %1 because no site with the specified ID could be found."), new String[] { cs.e });
                            break;
                        }
                        case 8: {
                            s = a.t.q(al.q("You are attempting to connect to %1 from an invalid host. Please contact the %1 Site administrator."), new String[] { cs.e });
                            break;
                        }
                        case 11: {
                            s = a.t.q(al.q("Your name must %1 characters or less.  Please re-enter this information."), new String[] { cp.q(0, 0) });
                            break;
                        }
                        case 12: {
                            s = a.t.q(al.q("You may not connect to %1 because the maximum number of simultaneous connections from same ip has been reached."), new String[] { cs.e });
                            break;
                        }
                        case 13: {
                            s = a.t.q(al.q("You may not connect to %1 because the maximum number of simultaneous connections for this server has been reached."), new String[] { cs.e });
                            break;
                        }
                        case 14: {
                            s = a.t.q(al.q("Your nickname could not be changed because the name \"%1\" is already in use by another user, or is reserved."), new String[] { cp.q(0, 0) });
                            break;
                        }
                        default: {
                            s = cp.q(0, 0);
                            break;
                        }
                    }
                    if (s == null) {
                        s = al.q("Your request could not be fulfilled.(" + q + ", " + q2 + ")");
                    }
                    if (q2 == 65793) {
                        this.g = true;
                        this.w();
                    }
                    new p(this.q, al.q("Note"), s, this).setVisible(true);
                }
            }
        }
    }
    
    protected final void a(final cp cp) {
        if (!this.g) {
            String q = cp.q(0, 0);
            String q2 = cp.q(0, 1);
            if (q == null) {
                q = "Exception somewhere";
            }
            if (q2 == null) {
                q2 = "Unrecognized exception";
            }
            new p((this.q == null) ? null : this.q.q(), al.q("Alert"), new String[] { q, q2 }, this).setVisible(true);
        }
    }
    
    protected final void s(final cp cp) {
        for (int i = 0; i < cp.w(); ++i) {
            new p((this.q == null) ? null : this.q.q(), cp.q(i, 0), cp.q(i, 1), this, new Color(cp.q(i, 2)), new Color(cp.q(i, 3))).setVisible(true);
        }
    }
    
    protected static void d(final cp cp) {
        try {
            h.q().getAppletContext().showDocument(new URL(cp.q(0, 0)), "_blank");
        }
        catch (MalformedURLException ex) {
            System.out.println("Bad URL:" + cp.q(0, 0));
        }
    }
    
    protected final void f(final cp cp) {
        new p(this.q.q(), al.q("Inactivity Timeout"), a.t.q(al.q("You have been disconnected because you have been inactive for the past %1 minutes."), new String[] { String.valueOf(cp.q(0, 0)) }), this).setVisible(true);
        this.g = true;
        this.w();
    }
    
    public final void g(final cp cp) {
        try {
            for (int i = 0; i < cp.w(); ++i) {
                final int q = cp.q(i, 0);
                bw bw = (bw)this.i.w(q);
                if (cp.q(i, 63)) {
                    if (bw != null) {
                        this.i.w(q);
                    }
                }
                else {
                    if (bw == null) {
                        bw = new bw(q, cp.q(i, 0));
                        this.i.q(bw);
                    }
                    else {
                        bw.o = cp.q(i, 0);
                    }
                    cp.q(i, 1);
                    cp.q(i, 2);
                    cp.q(i, 1);
                    bw.i(cp.q(i, 3));
                    bw.y(cp.q(i, 4));
                    bw.u(cp.q(i, 5));
                    cp.q(i, 21);
                    bw.q(cp.q(i));
                }
            }
        }
        finally {}
    }
    
    protected final void h(final cp cp) {
        try {
            for (int i = 0; i < cp.w(); ++i) {
                this.i.w(cp.q(i, 0));
            }
        }
        finally {}
    }
    
    protected final void j(final cp cp) {
        final long q = cp.q(-1);
        if (cp.q(-1, 60)) {
            this.l = true;
        }
        B b;
        if (cp.q(-1, 59)) {
            if ("Admin".equals(co.p)) {
                this.e = q;
                b = this.o;
            }
            else {
                b = this.l;
            }
            this.t = q;
        }
        else {
            b = this.o;
            this.e = q;
        }
        if (cp.q(this.t | this.e, 62) || cp.q(this.t | this.e, 61)) {
            this.l = true;
        }
        try {
            for (int i = 0; i < cp.w(); ++i) {
                final int q2 = cp.q(i, 0);
                aN an = (aN)b.w(q2);
                if (cp.q(i, 63)) {
                    if (an != null) {
                        b.w(q2);
                    }
                }
                else {
                    if (an == null) {
                        an = new aN(q2, cp.q(i, 0));
                        b.q(an);
                    }
                    else {
                        an.o = cp.q(i, 0);
                    }
                    an.q(cp.q(i, 1));
                    an.i(cp.q(i, 1));
                    an.q(cp.q(i));
                }
            }
        }
        finally {}
    }
    
    protected final void k(final cp cp) {
        try {
            for (int i = 0; i < cp.w(); ++i) {
                this.o.w(cp.q(i, 0));
            }
        }
        finally {}
    }
    
    protected final void l(final cp cp) {
        try {
            for (int i = 0; i < cp.w(); ++i) {
                final int q = cp.q(i, 0);
                ag ag = (ag)this.z.w(q);
                if (cp.q(i, 63)) {
                    if (ag != null) {
                        this.z.w(q);
                    }
                }
                else {
                    if (ag == null) {
                        ag = new ag(q, cp.q(i, 0));
                        this.z.q(ag);
                    }
                    else {
                        ag.o = cp.q(i, 0);
                    }
                    ag.q(cp.q(i, 1));
                    ag.i(cp.q(i, 1));
                    ag.u(cp.q(i, 2));
                    ag.q(cp.q(i));
                }
            }
        }
        finally {}
    }
    
    protected final void z(final cp cp) {
        try {
            for (int i = 0; i < cp.w(); ++i) {
                this.z.w(cp.q(i, 0));
            }
        }
        finally {}
    }
    
    protected final void x(final cp cp) {
        cp.q(0, 0);
    }
    
    protected final void c(final cp cp) {
        bm bm = null;
        aK ak = null;
        try {
            for (int i = 0; i < cp.w(); ++i) {
                final int q = cp.q(i, 0);
                bm bm2 = (bm)this.y.w(q);
                if (cp.q(i, 63) || cp.q(i, 0) == null) {
                    if (bm2 != null) {
                        this.y.w(q);
                        if (this.q != null) {
                            this.q.q(bm2);
                        }
                    }
                    if (q == super.r) {
                        ak = bm2;
                    }
                }
                else {
                    if (bm2 == null) {
                        String q2;
                        if ((q2 = cp.q(i, 0)) == null) {
                            System.err.println("null room name received.");
                            q2 = new String("no name");
                        }
                        bm2 = new bm(q, this.q(q2));
                        this.y.q(bm2);
                    }
                    else {
                        bm2.o = this.q(cp.q(i, 0));
                    }
                    cp.q(i, 1);
                    cp.q(i, 2);
                    cp.q(i, 3);
                    bm2.q(cp.q(i, 4));
                    bm2.i(cp.q(i, 5));
                    bm2.u(cp.q(i, 6));
                    bm2.w(cp.q(i, 7));
                    bm2.e(cp.q(i, 8));
                    bm2.q = this.q(cp.q(i, 1));
                    this.q(cp.q(i, 2));
                    final ck q3 = bm2.q;
                    bm2.q = cp.q(i, 0);
                    if (bm2.q != null && !bm2.q.equals(q3)) {
                        bm2.w = true;
                    }
                    bm2.q(cp.q(i));
                    if (cp.q(i, 62)) {
                        this.g = q;
                    }
                    if (this.q != null) {
                        this.q.w(bm2);
                    }
                    if (this.d != null && bm2.o.equals(this.d)) {
                        this.d = null;
                        (bm = bm2).w = false;
                    }
                }
            }
            if (cs.q == 1) {
                if (this.g == -999 && cp.w() > 0) {
                    this.g = cp.q(0, 0);
                }
            }
            else if (this.g == -999) {
                this.g = cp.q(0, 0);
            }
        }
        finally {}
        if (bm != null) {
            this.q(bm);
            this.q.q(true);
            return;
        }
        if (ak != null) {
            final bx bx = (bx)this.y.w(this.g);
            this.t(this.g);
            new p(this.q.q(), cs.e, a.t.q(al.q("The room you were in, \"%1,\" has closed.  You have been moved to \"%2.\""), new String[] { this.q(ak.o), this.q(bx.o) }), this).setVisible(true);
        }
    }
    
    protected final void v(final cp cp) {
        try {
            aK ak = null;
            for (int i = 0; i < cp.w(); ++i) {
                final int q = cp.q(i, 0);
                final bm bm;
                if ((bm = (bm)this.y.w(q)) != null) {
                    this.y.w(q);
                    if (this.q != null) {
                        this.q.q(bm);
                    }
                }
                if (q == super.r) {
                    ak = bm;
                }
            }
            if (ak != null) {
                final bx bx = (bx)this.y.w(this.g);
                this.t(this.g);
                new p(this.q.q(), cs.e, a.t.q(al.q("The room you were in, \"%1,\" has closed.  You have been moved to \"%2.\""), new String[] { this.q(ak.o), this.q(bx.o) }), this).setVisible(true);
            }
        }
        finally {}
    }
    
    protected final void b(final cp cp) {
        try {
            aT at = null;
            aT at2 = null;
            for (int i = 0; i < cp.w(); ++i) {
                int n = cp.q(i, 0);
                aT at3 = (aT)this.p.w(n);
                if (cp.q(i, 63)) {
                    this.p.w(n);
                }
                else {
                    if (at3 == null) {
                        at3 = new aT(n, cp.q(i, 0));
                        n = this.p.q(at3, n);
                        cp.q(i, 0, n);
                    }
                    else {
                        at3.o = cp.q(i, 0);
                    }
                    at3.q = new Color(cp.q(i, 1));
                    at3.w = new Color(cp.q(i, 2));
                    at3.e = new Color(cp.q(i, 3));
                    at3.r = new Color(cp.q(i, 4));
                    at3.u = new Color(cp.q(i, 5));
                    at3.i = new Color(cp.q(i, 6));
                    at3.o = new Color(cp.q(i, 7));
                    at3.p = new Color(cp.q(i, 8));
                    at3.a = new Color(cp.q(i, 9));
                    at3.s = new Color(cp.q(i, 10));
                    at3.d = new Color(cp.q(i, 11));
                    at3.q = cp.q(i, 1);
                    at3.w = cp.q(i, 12);
                    at3.q = cp.q(i, 13);
                    at3.q();
                    at3.q(cp.q(i));
                    at3.q(cp.q(i, 2));
                    at3.e = cp.q(i, 14);
                    at3.t = new Color(cp.q(i, 15));
                    at3.y = new Color(cp.q(i, 16));
                    at3.h = new Color(cp.q(i, 17));
                    at3.j = new Color(cp.q(i, 18));
                    at3.k = new Color(cp.q(i, 19));
                    at3.l = new Color(cp.q(i, 20));
                    at3.z = new Color(cp.q(i, 21));
                    at3.x = new Color(cp.q(i, 22));
                    at3.c = new Color(cp.q(i, 23));
                    at3.v = new Color(cp.q(i, 24));
                    at3.b = new Color(cp.q(i, 25));
                    at3.n = new Color(cp.q(i, 26));
                    at3.w = cp.q(i, 3);
                    at3.r = cp.q(i, 27);
                    at3.t = cp.q(i, 28);
                    at3.m = new Color(cp.q(i, 29));
                    at3.Q = new Color(cp.q(i, 30));
                    final int n2;
                    if ((n2 = (cp.q(i, 31) & 0xFFFFFF)) != 0) {
                        at3.f = new Color(n2);
                    }
                    at3.y = Math.max(cp.q(i, 32), 100);
                    final int n3;
                    if ((n3 = (cp.q(i, 33) & 0xFFFFFF)) != 0) {
                        at3.g = new Color(n3);
                    }
                    at3.q(cp.q(i, 34));
                    at3.w(cp.q(i, 35));
                    if (at3.q(62)) {
                        at = (aT)at3.clone();
                    }
                    if (n == a.h.q().e) {
                        at2 = (aT)at3.clone();
                    }
                }
            }
            aT q = aT.q;
            if (at2 != null) {
                q = at2;
            }
            else if (at != null) {
                q = at;
            }
            if (this.q != null) {
                this.q.w();
            }
            if (this.q != null) {
                for (int j = 0; j < this.q.q; ++j) {
                    ((aI)this.q.q(j)).q();
                }
            }
            aT.w.q(q);
        }
        finally {}
    }
    
    protected final void n(final cp cp) {
        try {
            for (int i = 0; i < cp.w(); ++i) {
                this.p.w(cp.q(i, 0));
            }
        }
        finally {}
    }
    
    public final synchronized void t(final cp cp) {
        if (cp.q() == 67341) {
            this.q.q(cp);
        }
        else if (cp.q() == 4198416) {
            if (this.q.q() > 0) {
                this.q.q(1, cp);
            }
            else {
                this.q.w(cp);
            }
        }
        else if (cp.q() == 4198496) {
            if (this.q.q() > 1) {
                this.q.q(2, cp);
            }
            else {
                this.q.w(cp);
            }
        }
        else if (cp.q() == 4202544) {
            if (this.q.q() > 2) {
                this.q.q(3, cp);
            }
            else {
                this.q.w(cp);
            }
        }
        else if (cp.q() == 67584) {
            if (this.n) {
                this.q.q(cp);
            }
            else if (this.q.q() > 3) {
                final cp cp2 = (cp)this.q.q(3).q;
                int n = 3;
                if (cp2.q() == 4202544) {
                    n = 4;
                }
                if (this.q.q() > n - 1) {
                    this.q.q(n, cp);
                }
            }
            else {
                this.q.w(cp);
            }
            this.b = true;
        }
        else if (cp.q() == 67585 && cp.q(0, 1) == 65793) {
            this.q.q(cp);
            this.b = true;
        }
        else {
            this.q.w(cp);
        }
        synchronized (this.q) {
            this.q.notifyAll();
        }
    }
    
    public abstract void r();
    
    public void run() {
        try {
            while (!this.g) {
                try {
                    if (!this.b) {
                        throw new NoSuchElementException();
                    }
                    while (this.q.q()) {
                        synchronized (this.q) {
                            this.q.wait();
                            continue;
                        }
                        break;
                    }
                    final cp cp = (cp)this.q.q();
                    for (int i = 0; i < this.w.size(); ++i) {
                        ((bb)this.w.elementAt(i)).q(cp);
                    }
                }
                catch (NoSuchElementException ex2) {
                    Thread.sleep(50L);
                }
            }
        }
        catch (InterruptedException ex3) {}
        catch (Exception ex) {
            System.out.println("MUB got exception:" + ex.getMessage());
            if (this.g) {
                this.w();
            }
        }
        System.out.println("MUB stoped:" + this.g);
    }
    
    public final void r(final cp cp) {
        try {
            if (this.eos != null) {
                this.eos.q(cp.w().length);
                cp.q(this.eos);
                this.eos.flush();
            }
        }
        catch (IOException ex) {
            if (!this.g) {
                this.w();
            }
        }
    }
    
    private static cp q(final int n, final String s, final co co, final int w, final int q, final int n2) {
        final cp cp;
        (cp = new cp(66305, 1)).q = q;
        cp.w = w;
        cp.q(0, 0, co.a);
        if (n2 != 0) {
            cp.q(0, 2, n2);
        }
        cp.q(0, 0, s);
        co.r(cp);
        return cp;
    }
    
    public final void w(final String s, final int n, final int n2, final int n3) {
        final cp q = q(66305, s, this, n, n2, n3);
        final bx bx;
        if ((bx = (bx)this.y.w(n2)) == null || ((!bx.q(61) || this.q(60)) && !bx.q(56))) {
            ((W)this).e(q);
        }
    }
    
    public final void q(final String s, final int n) {
        this.w(s, -1, super.r, n);
    }
    
    public abstract void q(final s p0, final l p1);
    
    public final void q(final Font font) {
        aT.w.q = font.getName();
        aT.w.w = font.getStyle();
        aT.w.q = font.getSize();
        aT.w.q();
        if (this.q != null) {
            this.q.w();
        }
        if (this.q != null) {
            for (int i = 0; i < this.q.q; ++i) {
                ((aI)this.q.q(i)).q();
            }
        }
    }
    
    public final void q(final boolean h, final boolean b) {
        this.h = h;
        try {
            synchronized (this.e) {
                for (int i = 0; i < this.e.q; ++i) {
                    final as as = (as)this.e.q(i);
                    if (!b && (this.h || as.r == super.r) && as.o != null && ce.q(as.o, ay.q.getText(), as.y)) {
                        this.q.q(as, true);
                    }
                    else {
                        this.q.q(as);
                    }
                }
            }
            if (b) {
                synchronized (this.q) {
                    final Enumeration<String> keys = this.q.keys();
                    while (keys.hasMoreElements()) {
                        final String s = keys.nextElement();
                        final as as2;
                        if ((as2 = (as)this.e.w(Integer.parseInt(s))) == null) {
                            this.q.remove(s);
                        }
                        else if (Integer.parseInt(this.q.get(s)) > 0 && (this.h || as2.r == super.r) && as2.o != null && ce.q(as2.o, ay.q.getText(), as2.y)) {
                            this.q.q(as2, true);
                        }
                        else {
                            this.q.q(as2);
                        }
                    }
                }
            }
        }
        finally {}
    }
    
    public void q(final boolean b) {
    }
    
    public final void w(final boolean b) {
        this.Q = false;
    }
    
    public final void e(final boolean b) {
        this.W = false;
    }
    
    public final void r(final boolean b) {
        this.E = true;
    }
    
    public void w(final int n) {
    }
    
    public abstract boolean q(final cj p0);
    
    public abstract void q(final l p0);
    
    public abstract int q(final cj p0);
    
    public abstract void e(final int p0);
    
    public abstract void r(final int p0);
    
    public abstract void q(final int p0, final l p1);
    
    public co() {
        super(-999, null);
        this.f = true;
        this.r = new B();
        this.t = new B();
        this.a = new B();
        this.s = new B();
        this.q = new O();
        this.j = new B();
        this.k = new B();
        this.q = new Hashtable();
        this.z = new B();
        this.q = new bh();
        this.w = new bh();
        this.m = 7;
        this.Q = -1;
        this.b = false;
        this.n = false;
        this.m = false;
        this.w = new Vector();
        this.W = 1000;
        this.E = 3;
        this.R = 3;
        this.T = 3;
        this.Y = 35;
        this.U = 3;
        this.I = 1000;
        this.O = 3;
        this.P = 3;
        this.t = false;
        this.A = 1000;
        this.q = null;
        this.w = new B();
        this.e = new B();
        this.x = false;
        this.l = false;
        this.g = true;
        this.p = new B();
        this.h = false;
        this.y = false;
        this.u = false;
        this.i = false;
        this.o = false;
        this.Q = true;
        this.W = true;
        this.p = false;
        this.E = false;
        this.q = new B();
        this.w = new B();
        this.e = new B();
        this.t = new B();
        this.y = new B();
        this.u = new B();
        this.i = new B();
        this.o = new B();
        this.l = new B();
        this.g = new B();
        co.d = new B();
        this.f = new B();
        co.d = new B();
        this.h = new B();
        this.q = new Vector();
        this.e = -999L;
        new bX();
        this.g = -999;
        this.h = -999;
        this.j = -999;
        this.j = false;
        this.k = false;
        this.k = -999;
        this.l = 2;
        this.z = -999;
        this.q = -999;
        this.w = -999;
        this.x = 8;
        this.c = 3;
        this.v = 4;
        this.b = 9;
        this.n = 1;
        this.q = false;
        this.z = false;
        this.c = false;
        this.v = false;
        this.w.addElement(this);
        this.w.addElement(new aE(this));
        this.w.addElement(new t(this));
    }
    
    public B q() {
        return this.h;
    }
    
    public final String a_() {
        return this.s;
    }
    
    public final void q(final String s) {
        this.s = s;
    }
    
    public final int a_() {
        return this.z;
    }
    
    public final void a_(final int z) {
        this.z = z;
    }
    
    public final Frame q() {
        return this.q;
    }
    
    static {
        final String[] array = { null, null, null, null, null, null, null, null, null };
        final String[] array2 = { "bass.au", "bell.au", "castanet.au", "chime.au", "conga.au", "cowBell.au", "doubleBell.au", "drumRoll.au", "harp.au" };
        co.d = new B();
    }
}
