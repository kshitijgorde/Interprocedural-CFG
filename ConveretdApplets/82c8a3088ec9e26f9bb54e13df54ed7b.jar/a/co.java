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

public abstract class co extends l implements ba, bp, Runnable
{
    public boolean t;
    public boolean y;
    public boolean u;
    public boolean i;
    public boolean o;
    private int S;
    private boolean Q;
    private boolean W;
    public boolean p;
    private boolean E;
    public boolean a;
    public boolean s;
    public boolean d;
    public boolean f;
    protected by eis;
    protected a eos;
    public static String p;
    protected f q;
    protected Thread q;
    protected Socket q;
    public am q;
    protected A q;
    public A w;
    public A e;
    public A r;
    public A t;
    public A y;
    public A u;
    public A i;
    public A o;
    public A p;
    private A l;
    public A a;
    public A s;
    public static A d;
    public N q;
    public A f;
    public A g;
    public A h;
    public A j;
    public A k;
    public Hashtable q;
    private A z;
    public Vector q;
    public long e;
    private long t;
    protected URL q;
    protected boolean g;
    protected boolean h;
    public MediaTracker q;
    protected AudioClip[] q;
    protected bg q;
    protected bg w;
    public Frame q;
    protected int h;
    protected int j;
    protected int k;
    private String d;
    public boolean j;
    public String a;
    public boolean k;
    public String s;
    public URL w;
    public URL e;
    public URL r;
    public int l;
    public int z;
    public int x;
    public int c;
    public int v;
    public int b;
    public int n;
    public int m;
    public long r;
    public int Q;
    public boolean l;
    public boolean z;
    public boolean x;
    public boolean c;
    public boolean v;
    public int W;
    public boolean b;
    public boolean n;
    public boolean m;
    protected az q;
    protected az w;
    private Vector w;
    public int E;
    public int R;
    public int T;
    public int Y;
    public int U;
    public int I;
    public int O;
    public int P;
    public int A;
    
    public final boolean a_() {
        return this.g;
    }
    
    public final by q() {
        return this.eis;
    }
    
    public final A w() {
        return this.o;
    }
    
    public final boolean r() {
        return this.u;
    }
    
    public final A t() {
        return this.e;
    }
    
    public final Hashtable q() {
        return this.q;
    }
    
    public final A y() {
        return this.q;
    }
    
    public final A u() {
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
    
    public final A e() {
        return this.w;
    }
    
    public final A r() {
        return this.y;
    }
    
    public final int r() {
        return this.z;
    }
    
    public final A i() {
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
        this.l = l.e;
        this.r = l.r;
        this.p(l.w());
        this.o(l.g);
        this.b_(l.i);
        this.w(l.o);
        this.t = l.t;
        this.p = l.p;
        this.y = l.y;
        this.u = l.u;
        super.a = l.a;
    }
    
    public final String w(String s) {
        if (s == null || !this.l) {
            return s;
        }
        try {
            synchronized (this.o) {
                for (int i = 0; i < this.o.q; ++i) {
                    s = ((aM)this.o.q(i)).q(s);
                }
            }
        }
        finally {}
        try {
            synchronized (this.l) {
                for (int j = 0; j < this.l.q; ++j) {
                    s = ((aM)this.l.q(j)).q(s);
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
                        s = ((aM)this.o.q(i)).q(s);
                    }
                }
            }
            finally {}
            try {
                synchronized (this.l) {
                    for (int j = 0; j < this.l.q; ++j) {
                        s = ((aM)this.l.q(j)).q(s);
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
                        q = ((af)this.z.q(i)).q(q);
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
        cp.e = super.s;
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
        new aP(this.q.q(), this).setVisible(true);
    }
    
    public final void q(final String d, final String s, final boolean b) {
        this.d = d;
        final cp cp;
        (cp = new cp(67330, 1)).w = -1;
        cp.q = -1;
        cp.e = super.s;
        cp.q(0, 0, -999);
        cp.q(0, 0, d);
        cp.q(0, 1, s);
        if (!b) {
            cp.q(0, 58);
        }
        this.r(cp);
    }
    
    public final void q(final int n) {
        final bl bl;
        if ((bl = (bl)this.y.w(n)) != null) {
            this.q(bl);
            return;
        }
        System.err.println("no such room! " + n);
    }
    
    public final void q(bl bl) {
        ck ck;
        if (bl.w && bl.q != null && !this.q(40)) {
            final bc bc;
            (bc = new bc(this.q.q(), this, bl)).setVisible(true);
            ck = bc.q;
        }
        else {
            ck = bl.q;
        }
        final bl bl2 = bl;
        final ck ck2 = ck;
        bl = bl2;
        if (!bl.w || bl.q == null || this.q(40)) {
            final cp cp;
            (cp = new cp(66049, 1)).q(0, 0, super.s);
            cp.q(0, 1, bl.s);
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
                    final ar ar;
                    if ((ar = (ar)this.e.q(i)).r == n && (!ar.q(23) || this.q(24)) && (!ar.q(25) || this.q(32))) {
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
            s2 = (String)(this.S++);
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
            ((aO)this.f.q(i)).dispose();
        }
        if (this.eos != null) {
            final cp cp;
            (cp = new cp(65794, 1)).q(0, 0, super.s);
            cp.q(0, 0, this.t);
            cp.w = -1;
            cp.q = -1;
            this.r(cp);
            super.r = -999;
            this.h = -999;
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
    
    public void e(final int n) {
    }
    
    protected final void u(final cp cp) {
        for (int i = 0; i < cp.w(); ++i) {
            final ar ar;
            if ((ar = (ar)this.e.w(cp.q(i, 0))) != null) {
                this.q(ar, cp.q(i, 1), cp.q, 0);
            }
        }
    }
    
    protected abstract void q(final ar p0, final int p1, final long p2, final int p3);
    
    protected final void i(final cp cp) {
        this.g = true;
        this.w();
        if (this.e != null) {
            this.q(this.e, "_blank");
            return;
        }
        new bR(new Frame(), ak.q("You have been kicked"), cp.q(0, 0), this).setVisible(true);
    }
    
    protected final void o(final cp cp) {
        final String[] array = new String[cp.w()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = cp.q(i, 0);
        }
        new bR(this.q.q(), ak.q("Operation not allowed"), array, this).setVisible(true);
    }
    
    public final void p(final cp cp) {
        final int q = cp.q(0, 0);
        Label_0184: {
            final int q2;
            switch (q2 = cp.q(0, 1)) {
                default: {
                    switch (q) {
                        case 0: {
                            a.s.q(ak.q("Your nickname could not be changed because the name \"%1\" is already in use by another user, or is reserved."), new String[] { cp.q(0, 0) });
                            break Label_0184;
                        }
                        case 3: {
                            a.s.q(ak.q("You may not connect to %1 because the version of the %1 Client (%2) is not compatible with the version of %1 Server (%3)."), new String[] { cs.e, "1", "2" });
                            break Label_0184;
                        }
                        case 9: {
                            a.s.q(ak.q("You may not connect to %1 because the Server license has been exceeded."), new String[] { cs.e });
                            break Label_0184;
                        }
                        case 10: {
                            a.s.q(ak.q("You may not connect to %1 because the Server license has expired."), new String[] { cs.e });
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
                            s = a.s.q(ak.q("You may not connect to %1 because another user is already using the name \"%2.\" Please choose another and login again."), new String[] { cs.e, super.o });
                            break;
                        }
                        case 1:
                        case 7: {
                            s = a.s.q(ak.q("You may not connect to %1 because your name or password was not entered correctly.  Please re-enter this information and login again."), new String[] { cs.e });
                            break;
                        }
                        case 2: {
                            s = a.s.q(ak.q("You may not connect to %1 because your account has been disabled."), new String[] { cs.e });
                            break;
                        }
                        case 4: {
                            s = a.s.q(ak.q("You may not connect to %1 because the maximum number of simultaneous connections for this site has been reached."), new String[] { cs.e });
                            break;
                        }
                        case 5: {
                            s = a.s.q(ak.q("You may not connect to %1 because you have been banned from this %1 site."), new String[] { cs.e });
                            break;
                        }
                        case 6: {
                            s = a.s.q(ak.q("You may not connect to %1 because no site with the specified ID could be found."), new String[] { cs.e });
                            break;
                        }
                        case 8: {
                            s = a.s.q(ak.q("You are attempting to connect to %1 from an invalid host. Please contact the %1 Site administrator."), new String[] { cs.e });
                            break;
                        }
                        case 11: {
                            s = a.s.q(ak.q("Your name must %1 characters or less.  Please re-enter this information."), new String[] { cp.q(0, 0) });
                            break;
                        }
                        case 12: {
                            s = a.s.q(ak.q("You may not connect to %1 because the maximum number of simultaneous connections from same ip has been reached."), new String[] { cs.e });
                            break;
                        }
                        case 13: {
                            s = a.s.q(ak.q("You may not connect to %1 because the maximum number of simultaneous connections for this server has been reached."), new String[] { cs.e });
                            break;
                        }
                        case 14: {
                            s = a.s.q(ak.q("Your nickname could not be changed because the name \"%1\" is already in use by another user, or is reserved."), new String[] { cp.q(0, 0) });
                            break;
                        }
                        default: {
                            s = cp.q(0, 0);
                            break;
                        }
                    }
                    if (s == null) {
                        s = ak.q("Your request could not be fulfilled.(" + q + ", " + q2 + ")");
                    }
                    if (q2 == 65793) {
                        this.g = true;
                        this.w();
                    }
                    new bR(this.q, ak.q("Note"), s, this).setVisible(true);
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
            new bR((this.q == null) ? null : this.q.q(), ak.q("Alert"), new String[] { q, q2 }, this).setVisible(true);
        }
    }
    
    protected final void s(final cp cp) {
        for (int i = 0; i < cp.w(); ++i) {
            new bR((this.q == null) ? null : this.q.q(), cp.q(i, 0), cp.q(i, 1), this, new Color(cp.q(i, 2)), new Color(cp.q(i, 3))).setVisible(true);
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
        new bR(this.q.q(), ak.q("Inactivity Timeout"), a.s.q(ak.q("You have been disconnected because you have been inactive for the past %1 minutes."), new String[] { String.valueOf(cp.q(0, 0)) }), this).setVisible(true);
        this.g = true;
        this.w();
    }
    
    public final void g(final cp cp) {
        try {
            for (int i = 0; i < cp.w(); ++i) {
                final int q = cp.q(i, 0);
                bv bv = (bv)this.i.w(q);
                if (cp.q(i, 63)) {
                    if (bv != null) {
                        this.i.w(q);
                    }
                }
                else {
                    if (bv == null) {
                        bv = new bv(q, cp.q(i, 0));
                        this.i.q(bv);
                    }
                    else {
                        bv.o = cp.q(i, 0);
                    }
                    cp.q(i, 1);
                    cp.q(i, 2);
                    cp.q(i, 1);
                    bv.p(cp.q(i, 3));
                    bv.i(cp.q(i, 4));
                    bv.o(cp.q(i, 5));
                    bv.q(cp.q(i));
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
        A a;
        if (cp.q(-1, 59)) {
            if ("Admin".equals(co.p)) {
                this.e = q;
                a = this.o;
            }
            else {
                a = this.l;
            }
            this.t = q;
        }
        else {
            a = this.o;
            this.e = q;
        }
        if (cp.q(this.t | this.e, 62) || cp.q(this.t | this.e, 61)) {
            this.l = true;
        }
        try {
            for (int i = 0; i < cp.w(); ++i) {
                final int q2 = cp.q(i, 0);
                aM am = (aM)a.w(q2);
                if (cp.q(i, 63)) {
                    if (am != null) {
                        a.w(q2);
                    }
                }
                else {
                    if (am == null) {
                        am = new aM(q2, cp.q(i, 0));
                        a.q(am);
                    }
                    else {
                        am.o = cp.q(i, 0);
                    }
                    am.q(cp.q(i, 1));
                    am.p(cp.q(i, 1));
                    am.q(cp.q(i));
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
                af af = (af)this.z.w(q);
                if (cp.q(i, 63)) {
                    if (af != null) {
                        this.z.w(q);
                    }
                }
                else {
                    if (af == null) {
                        af = new af(q, cp.q(i, 0));
                        this.z.q(af);
                    }
                    else {
                        af.o = cp.q(i, 0);
                    }
                    af.q(cp.q(i, 1));
                    af.p(cp.q(i, 1));
                    af.o(cp.q(i, 2));
                    af.q(cp.q(i));
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
        bl bl = null;
        aJ aj = null;
        try {
            for (int i = 0; i < cp.w(); ++i) {
                final int q = cp.q(i, 0);
                bl bl2 = (bl)this.y.w(q);
                if (cp.q(i, 63) || cp.q(i, 0) == null) {
                    if (bl2 != null) {
                        this.y.w(q);
                        if (this.q != null) {
                            this.q.q(bl2);
                        }
                    }
                    if (q == super.r) {
                        aj = bl2;
                    }
                }
                else {
                    if (bl2 == null) {
                        String q2;
                        if ((q2 = cp.q(i, 0)) == null) {
                            System.err.println("null room name received.");
                            q2 = new String("no name");
                        }
                        bl2 = new bl(q, this.q(q2));
                        this.y.q(bl2);
                    }
                    else {
                        bl2.o = this.q(cp.q(i, 0));
                    }
                    cp.q(i, 1);
                    cp.q(i, 2);
                    cp.q(i, 3);
                    bl2.q(cp.q(i, 4));
                    bl2.p(cp.q(i, 5));
                    bl2.o(cp.q(i, 6));
                    bl2.w(cp.q(i, 7));
                    bl2.e(cp.q(i, 8));
                    bl2.q = this.q(cp.q(i, 1));
                    this.q(cp.q(i, 2));
                    final ck q3 = bl2.q;
                    bl2.q = cp.q(i, 0);
                    if (bl2.q != null && !bl2.q.equals(q3)) {
                        bl2.w = true;
                    }
                    bl2.q(cp.q(i));
                    if (cp.q(i, 62)) {
                        this.h = q;
                    }
                    if (this.q != null) {
                        this.q.w(bl2);
                    }
                    if (this.d != null && bl2.o.equals(this.d)) {
                        this.d = null;
                        (bl = bl2).w = false;
                    }
                }
            }
            if (cs.q == 1) {
                if (this.h == -999 && cp.w() > 0) {
                    this.h = cp.q(0, 0);
                }
            }
            else if (this.h == -999) {
                this.h = cp.q(0, 0);
            }
        }
        finally {}
        if (bl != null) {
            this.q(bl);
            this.q.q(true);
            return;
        }
        if (aj != null) {
            final bw bw = (bw)this.y.w(this.h);
            this.q(this.h);
            new bR(this.q.q(), cs.e, a.s.q(ak.q("The room you were in, \"%1,\" has closed.  You have been moved to \"%2.\""), new String[] { this.q(aj.o), this.q(bw.o) }), this).setVisible(true);
        }
    }
    
    protected final void v(final cp cp) {
        try {
            aJ aj = null;
            for (int i = 0; i < cp.w(); ++i) {
                final int q = cp.q(i, 0);
                final bl bl;
                if ((bl = (bl)this.y.w(q)) != null) {
                    this.y.w(q);
                    if (this.q != null) {
                        this.q.q(bl);
                    }
                }
                if (q == super.r) {
                    aj = bl;
                }
            }
            if (aj != null) {
                final bw bw = (bw)this.y.w(this.h);
                this.q(this.h);
                new bR(this.q.q(), cs.e, a.s.q(ak.q("The room you were in, \"%1,\" has closed.  You have been moved to \"%2.\""), new String[] { this.q(aj.o), this.q(bw.o) }), this).setVisible(true);
            }
        }
        finally {}
    }
    
    protected final void b(final cp cp) {
        try {
            aS as = null;
            aS as2 = null;
            for (int i = 0; i < cp.w(); ++i) {
                int n = cp.q(i, 0);
                aS as3 = (aS)this.p.w(n);
                if (cp.q(i, 63)) {
                    this.p.w(n);
                }
                else {
                    if (as3 == null) {
                        as3 = new aS(n, cp.q(i, 0));
                        n = this.p.q(as3, n);
                        cp.q(i, 0, n);
                    }
                    else {
                        as3.o = cp.q(i, 0);
                    }
                    as3.q = new Color(cp.q(i, 1));
                    as3.w = new Color(cp.q(i, 2));
                    as3.e = new Color(cp.q(i, 3));
                    as3.r = new Color(cp.q(i, 4));
                    as3.u = new Color(cp.q(i, 5));
                    as3.i = new Color(cp.q(i, 6));
                    as3.o = new Color(cp.q(i, 7));
                    as3.p = new Color(cp.q(i, 8));
                    as3.a = new Color(cp.q(i, 9));
                    as3.s = new Color(cp.q(i, 10));
                    as3.d = new Color(cp.q(i, 11));
                    as3.q = cp.q(i, 1);
                    as3.w = cp.q(i, 12);
                    as3.q = cp.q(i, 13);
                    as3.q();
                    as3.q(cp.q(i));
                    as3.q(cp.q(i, 2));
                    as3.e = cp.q(i, 14);
                    as3.t = new Color(cp.q(i, 15));
                    as3.y = new Color(cp.q(i, 16));
                    as3.h = new Color(cp.q(i, 17));
                    as3.j = new Color(cp.q(i, 18));
                    as3.k = new Color(cp.q(i, 19));
                    as3.l = new Color(cp.q(i, 20));
                    as3.z = new Color(cp.q(i, 21));
                    as3.x = new Color(cp.q(i, 22));
                    as3.c = new Color(cp.q(i, 23));
                    as3.v = new Color(cp.q(i, 24));
                    as3.b = new Color(cp.q(i, 25));
                    as3.n = new Color(cp.q(i, 26));
                    as3.w = cp.q(i, 3);
                    as3.r = cp.q(i, 27);
                    as3.t = cp.q(i, 28);
                    as3.m = new Color(cp.q(i, 29));
                    as3.Q = new Color(cp.q(i, 30));
                    final int n2;
                    if ((n2 = (cp.q(i, 31) & 0xFFFFFF)) != 0) {
                        as3.f = new Color(n2);
                    }
                    as3.y = Math.max(cp.q(i, 32), 100);
                    final int n3;
                    if ((n3 = (cp.q(i, 33) & 0xFFFFFF)) != 0) {
                        as3.g = new Color(n3);
                    }
                    as3.q(cp.q(i, 34));
                    as3.w(cp.q(i, 35));
                    if (as3.q(62)) {
                        as = (aS)as3.clone();
                    }
                    if (n == a.h.q().e) {
                        as2 = (aS)as3.clone();
                    }
                }
            }
            aS q = aS.q;
            if (as2 != null) {
                q = as2;
            }
            else if (as != null) {
                q = as;
            }
            if (this.q != null) {
                this.q.w();
            }
            if (this.q != null) {
                for (int j = 0; j < this.q.q; ++j) {
                    ((aH)this.q.q(j)).q();
                }
            }
            aS.w.q(q);
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
                        ((ba)this.w.elementAt(i)).q(cp);
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
        cp.q(0, 0, co.s);
        if (n2 != 0) {
            cp.q(0, 2, n2);
        }
        cp.q(0, 0, s);
        co.r(cp);
        return cp;
    }
    
    public final void w(final String s, final int n, final int n2, final int n3) {
        final cp q = q(66305, s, this, n, n2, n3);
        final bw bw;
        if ((bw = (bw)this.y.w(n2)) == null || ((!bw.q(61) || this.q(60)) && !bw.q(56))) {
            ((W)this).e(q);
        }
    }
    
    public final void q(final String s, final int n) {
        this.w(s, -1, super.r, n);
    }
    
    public abstract void q(final r p0, final l p1);
    
    public final void q(final Font font) {
        aS.w.q = font.getName();
        aS.w.w = font.getStyle();
        aS.w.q = font.getSize();
        aS.w.q();
        if (this.q != null) {
            this.q.w();
        }
        if (this.q != null) {
            for (int i = 0; i < this.q.q; ++i) {
                ((aH)this.q.q(i)).q();
            }
        }
    }
    
    public final void q(final boolean h, final boolean b) {
        this.h = h;
        try {
            synchronized (this.e) {
                for (int i = 0; i < this.e.q; ++i) {
                    final ar ar = (ar)this.e.q(i);
                    if (!b && (this.h || ar.r == super.r) && ar.o != null && ce.q(ar.o, ax.q.getText(), ar.y)) {
                        this.q.q(ar, true);
                    }
                    else {
                        this.q.q(ar);
                    }
                }
            }
            if (b) {
                synchronized (this.q) {
                    final Enumeration<String> keys = this.q.keys();
                    while (keys.hasMoreElements()) {
                        final String s = keys.nextElement();
                        final ar ar2;
                        if ((ar2 = (ar)this.e.w(Integer.parseInt(s))) == null) {
                            this.q.remove(s);
                        }
                        else if (Integer.parseInt(this.q.get(s)) > 0 && (this.h || ar2.r == super.r) && ar2.o != null && ce.q(ar2.o, ax.q.getText(), ar2.y)) {
                            this.q.q(ar2, true);
                        }
                        else {
                            this.q.q(ar2);
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
    
    public void r(final int n) {
    }
    
    public abstract boolean q(final cj p0);
    
    public abstract void q(final l p0);
    
    public abstract int q(final cj p0);
    
    public abstract void t(final int p0);
    
    public abstract void y(final int p0);
    
    public abstract void q(final int p0, final l p1);
    
    public co() {
        super(-999, null);
        this.f = true;
        this.r = new A();
        this.t = new A();
        this.a = new A();
        this.s = new A();
        this.q = new N();
        this.j = new A();
        this.k = new A();
        this.q = new Hashtable();
        this.z = new A();
        this.q = new bg();
        this.w = new bg();
        this.Q = 7;
        this.W = -1;
        this.b = false;
        this.n = false;
        this.m = false;
        this.w = new Vector();
        this.E = 1000;
        this.R = 3;
        this.T = 3;
        this.Y = 3;
        this.U = 35;
        this.I = 3;
        this.O = 1000;
        this.P = 3;
        this.A = 3;
        this.t = false;
        this.S = 1000;
        this.q = null;
        this.w = new A();
        this.e = new A();
        this.x = false;
        this.l = false;
        this.g = true;
        this.p = new A();
        this.h = false;
        this.y = false;
        this.u = false;
        this.i = false;
        this.o = false;
        this.Q = true;
        this.W = true;
        this.p = false;
        this.E = false;
        this.q = new A();
        this.w = new A();
        this.e = new A();
        this.t = new A();
        this.y = new A();
        this.u = new A();
        this.i = new A();
        this.o = new A();
        this.l = new A();
        this.g = new A();
        co.d = new A();
        this.f = new A();
        co.d = new A();
        this.h = new A();
        this.q = new Vector();
        this.e = -999L;
        new bX();
        this.h = -999;
        this.j = -999;
        this.k = -999;
        this.j = false;
        this.k = false;
        this.l = -999;
        this.z = 2;
        this.x = -999;
        this.q = -999;
        this.w = -999;
        this.c = 8;
        this.v = 3;
        this.b = 4;
        this.n = 9;
        this.m = 1;
        this.q = false;
        this.z = false;
        this.c = false;
        this.v = false;
        this.w.addElement(this);
        this.w.addElement(new aD(this));
        this.w.addElement(new s(this));
    }
    
    public A q() {
        return this.h;
    }
    
    public final String a_() {
        return this.s;
    }
    
    public final void q(final String s) {
        this.s = s;
    }
    
    public final int a_() {
        return this.x;
    }
    
    public final void a_(final int x) {
        this.x = x;
    }
    
    public final Frame q() {
        return this.q;
    }
    
    static {
        final String[] array = { null, null, null, null, null, null, null, null, null };
        final String[] array2 = { "bass.au", "bell.au", "castanet.au", "chime.au", "conga.au", "cowBell.au", "doubleBell.au", "drumRoll.au", "harp.au" };
        co.d = new A();
    }
}
