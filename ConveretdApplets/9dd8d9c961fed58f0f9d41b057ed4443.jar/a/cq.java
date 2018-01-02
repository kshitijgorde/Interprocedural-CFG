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

public abstract class cq extends l implements bc, br, Runnable
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
    protected bA eis;
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
    protected bi q;
    protected bi w;
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
    
    public final bA q() {
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
        final cr cr;
        (cr = new cr(67587, 1)).q(0, 0, n);
        cr.q(0, 1, this.n ? 1 : 0);
        cr.q(0, 0, b);
        this.r(cr);
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
                    s = ((aO)this.o.q(i)).q(s);
                }
            }
        }
        finally {}
        try {
            synchronized (this.l) {
                for (int j = 0; j < this.l.q; ++j) {
                    s = ((aO)this.l.q(j)).q(s);
                }
            }
        }
        finally {}
        return;
    }
    
    public final void y(final cr cr) {
        for (int i = 0; i < cr.w(); ++i) {
            final l l;
            if ((l = (l)this.e.w(cr.q(i, 0))) != null) {
                if (this.x) {
                    final String q;
                    if ((q = cr.q(i, 2)) != null) {
                        try {
                            this.q(new URL(q), "_blank");
                        }
                        catch (MalformedURLException ex) {}
                    }
                }
                else {
                    new i(this.q.q(), this, l, cr, i).setVisible(true);
                }
            }
        }
    }
    
    public final String q(String s) {
        if (s != null && this.l) {
            try {
                synchronized (this.o) {
                    for (int i = 0; i < this.o.q; ++i) {
                        s = ((aO)this.o.q(i)).q(s);
                    }
                }
            }
            finally {}
            try {
                synchronized (this.l) {
                    for (int j = 0; j < this.l.q; ++j) {
                        s = ((aO)this.l.q(j)).q(s);
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
        final cr cr;
        (cr = new cr(67330, 1)).w = -1;
        cr.q = -1;
        cr.e = super.s;
        cr.q(0, 0, -999);
        cr.q(0, 0, d);
        cr.q(0, 1, s);
        cr.q(0, 0, new cm(s2));
        if (!b) {
            cr.q(0, 58);
        }
        this.r(cr);
    }
    
    public final void t() {
        new aR(this.q.q(), this).setVisible(true);
    }
    
    public final void q(final String d, final String s, final boolean b) {
        this.d = d;
        final cr cr;
        (cr = new cr(67330, 1)).w = -1;
        cr.q = -1;
        cr.e = super.s;
        cr.q(0, 0, -999);
        cr.q(0, 0, d);
        cr.q(0, 1, s);
        if (!b) {
            cr.q(0, 58);
        }
        this.r(cr);
    }
    
    public final void q(final int n) {
        final bn bn;
        if ((bn = (bn)this.y.w(n)) != null) {
            this.q(bn);
            return;
        }
        System.err.println("no such room! " + n);
    }
    
    public final void q(bn bn) {
        cm cm;
        if (bn.w && bn.q != null && !this.q(40)) {
            final be be;
            (be = new be(this.q.q(), this, bn)).setVisible(true);
            cm = be.q;
        }
        else {
            cm = bn.q;
        }
        final bn bn2 = bn;
        final cm cm2 = cm;
        bn = bn2;
        if (!bn.w || bn.q == null || this.q(40)) {
            final cr cr;
            (cr = new cr(66049, 1)).q(0, 0, super.s);
            cr.q(0, 1, bn.s);
            cr.q(0, 0, cm2);
            cr.w = -1;
            cr.q = -1;
            this.r(cr);
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
            ((aP)this.f.q(i)).dispose();
        }
        if (this.eos != null) {
            final cr cr;
            (cr = new cr(65794, 1)).q(0, 0, super.s);
            cr.q(0, 0, this.t);
            cr.w = -1;
            cr.q = -1;
            this.r(cr);
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
    
    protected final void u(final cr cr) {
        for (int i = 0; i < cr.w(); ++i) {
            final ar ar;
            if ((ar = (ar)this.e.w(cr.q(i, 0))) != null) {
                this.q(ar, cr.q(i, 1), cr.q, 0);
            }
        }
    }
    
    protected abstract void q(final ar p0, final int p1, final long p2, final int p3);
    
    protected final void i(final cr cr) {
        this.g = true;
        this.w();
        if (this.e != null) {
            this.q(this.e, "_blank");
            return;
        }
        new bT(new Frame(), ak.q("You have been kicked"), cr.q(0, 0), this).setVisible(true);
    }
    
    protected final void o(final cr cr) {
        final String[] array = new String[cr.w()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = cr.q(i, 0);
        }
        new bT(this.q.q(), ak.q("Operation not allowed"), array, this).setVisible(true);
    }
    
    public final void p(final cr cr) {
        final int q = cr.q(0, 0);
        Label_0184: {
            final int q2;
            switch (q2 = cr.q(0, 1)) {
                default: {
                    switch (q) {
                        case 0: {
                            a.s.q(ak.q("Your nickname could not be changed because the name \"%1\" is already in use by another user, or is reserved."), new String[] { cr.q(0, 0) });
                            break Label_0184;
                        }
                        case 3: {
                            a.s.q(ak.q("You may not connect to %1 because the version of the %1 Client (%2) is not compatible with the version of %1 Server (%3)."), new String[] { cu.e, "1", "2" });
                            break Label_0184;
                        }
                        case 9: {
                            a.s.q(ak.q("You may not connect to %1 because the Server license has been exceeded."), new String[] { cu.e });
                            break Label_0184;
                        }
                        case 10: {
                            a.s.q(ak.q("You may not connect to %1 because the Server license has expired."), new String[] { cu.e });
                            break Label_0184;
                        }
                        default: {
                            cr.q(0, 0);
                            break Label_0184;
                        }
                    }
                    break;
                }
                case 65793: {
                    String s = null;
                    switch (q) {
                        case 0: {
                            s = a.s.q(ak.q("You may not connect to %1 because another user is already using the name \"%2.\" Please choose another and login again."), new String[] { cu.e, super.o });
                            break;
                        }
                        case 1:
                        case 7: {
                            s = a.s.q(ak.q("You may not connect to %1 because your name or password was not entered correctly.  Please re-enter this information and login again."), new String[] { cu.e });
                            break;
                        }
                        case 2: {
                            s = a.s.q(ak.q("You may not connect to %1 because your account has been disabled."), new String[] { cu.e });
                            break;
                        }
                        case 4: {
                            s = a.s.q(ak.q("You may not connect to %1 because the maximum number of simultaneous connections for this site has been reached."), new String[] { cu.e });
                            break;
                        }
                        case 5: {
                            s = a.s.q(ak.q("You may not connect to %1 because you have been banned from this %1 site."), new String[] { cu.e });
                            break;
                        }
                        case 6: {
                            s = a.s.q(ak.q("You may not connect to %1 because no site with the specified ID could be found."), new String[] { cu.e });
                            break;
                        }
                        case 8: {
                            s = a.s.q(ak.q("You are attempting to connect to %1 from an invalid host. Please contact the %1 Site administrator."), new String[] { cu.e });
                            break;
                        }
                        case 11: {
                            s = a.s.q(ak.q("Your name must %1 characters or less.  Please re-enter this information."), new String[] { cr.q(0, 0) });
                            break;
                        }
                        case 12: {
                            s = a.s.q(ak.q("You may not connect to %1 because the maximum number of simultaneous connections from same ip has been reached."), new String[] { cu.e });
                            break;
                        }
                        case 13: {
                            s = a.s.q(ak.q("You may not connect to %1 because the maximum number of simultaneous connections for this server has been reached."), new String[] { cu.e });
                            break;
                        }
                        case 14: {
                            s = a.s.q(ak.q("Your nickname could not be changed because the name \"%1\" is already in use by another user, or is reserved."), new String[] { cr.q(0, 0) });
                            break;
                        }
                        default: {
                            s = cr.q(0, 0);
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
                    new bT(this.q, ak.q("Note"), s, this).setVisible(true);
                }
            }
        }
    }
    
    protected final void a(final cr cr) {
        if (!this.g) {
            String q = cr.q(0, 0);
            String q2 = cr.q(0, 1);
            if (q == null) {
                q = "Exception somewhere";
            }
            if (q2 == null) {
                q2 = "Unrecognized exception";
            }
            new bT((this.q == null) ? null : this.q.q(), ak.q("Alert"), new String[] { q, q2 }, this).setVisible(true);
        }
    }
    
    protected final void s(final cr cr) {
        for (int i = 0; i < cr.w(); ++i) {
            new bT((this.q == null) ? null : this.q.q(), cr.q(i, 0), cr.q(i, 1), this, new Color(cr.q(i, 2)), new Color(cr.q(i, 3))).setVisible(true);
        }
    }
    
    protected static void d(final cr cr) {
        try {
            h.q().getAppletContext().showDocument(new URL(cr.q(0, 0)), "_blank");
        }
        catch (MalformedURLException ex) {
            System.out.println("Bad URL:" + cr.q(0, 0));
        }
    }
    
    protected final void f(final cr cr) {
        new bT(this.q.q(), ak.q("Inactivity Timeout"), a.s.q(ak.q("You have been disconnected because you have been inactive for the past %1 minutes."), new String[] { String.valueOf(cr.q(0, 0)) }), this).setVisible(true);
        this.g = true;
        this.w();
    }
    
    public final void g(final cr cr) {
        try {
            for (int i = 0; i < cr.w(); ++i) {
                final int q = cr.q(i, 0);
                bx bx = (bx)this.i.w(q);
                if (cr.q(i, 63)) {
                    if (bx != null) {
                        this.i.w(q);
                    }
                }
                else {
                    if (bx == null) {
                        bx = new bx(q, cr.q(i, 0));
                        this.i.q(bx);
                    }
                    else {
                        bx.o = cr.q(i, 0);
                    }
                    cr.q(i, 1);
                    cr.q(i, 2);
                    cr.q(i, 1);
                    bx.p(cr.q(i, 3));
                    bx.i(cr.q(i, 4));
                    bx.o(cr.q(i, 5));
                    bx.q(cr.q(i));
                }
            }
        }
        finally {}
    }
    
    protected final void h(final cr cr) {
        try {
            for (int i = 0; i < cr.w(); ++i) {
                this.i.w(cr.q(i, 0));
            }
        }
        finally {}
    }
    
    protected final void j(final cr cr) {
        final long q = cr.q(-1);
        if (cr.q(-1, 60)) {
            this.l = true;
        }
        A a;
        if (cr.q(-1, 59)) {
            if ("Admin".equals(cq.p)) {
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
        if (cr.q(this.t | this.e, 62) || cr.q(this.t | this.e, 61)) {
            this.l = true;
        }
        try {
            for (int i = 0; i < cr.w(); ++i) {
                final int q2 = cr.q(i, 0);
                aO ao = (aO)a.w(q2);
                if (cr.q(i, 63)) {
                    if (ao != null) {
                        a.w(q2);
                    }
                }
                else {
                    if (ao == null) {
                        ao = new aO(q2, cr.q(i, 0));
                        a.q(ao);
                    }
                    else {
                        ao.o = cr.q(i, 0);
                    }
                    ao.q(cr.q(i, 1));
                    ao.p(cr.q(i, 1));
                    ao.q(cr.q(i));
                }
            }
        }
        finally {}
    }
    
    protected final void k(final cr cr) {
        try {
            for (int i = 0; i < cr.w(); ++i) {
                this.o.w(cr.q(i, 0));
            }
        }
        finally {}
    }
    
    protected final void l(final cr cr) {
        try {
            for (int i = 0; i < cr.w(); ++i) {
                final int q = cr.q(i, 0);
                af af = (af)this.z.w(q);
                if (cr.q(i, 63)) {
                    if (af != null) {
                        this.z.w(q);
                    }
                }
                else {
                    if (af == null) {
                        af = new af(q, cr.q(i, 0));
                        this.z.q(af);
                    }
                    else {
                        af.o = cr.q(i, 0);
                    }
                    af.q(cr.q(i, 1));
                    af.p(cr.q(i, 1));
                    af.o(cr.q(i, 2));
                    af.q(cr.q(i));
                }
            }
        }
        finally {}
    }
    
    protected final void z(final cr cr) {
        try {
            for (int i = 0; i < cr.w(); ++i) {
                this.z.w(cr.q(i, 0));
            }
        }
        finally {}
    }
    
    protected final void x(final cr cr) {
        cr.q(0, 0);
    }
    
    protected final void c(final cr cr) {
        bn bn = null;
        aJ aj = null;
        try {
            for (int i = 0; i < cr.w(); ++i) {
                final int q = cr.q(i, 0);
                bn bn2 = (bn)this.y.w(q);
                if (cr.q(i, 63) || cr.q(i, 0) == null) {
                    if (bn2 != null) {
                        this.y.w(q);
                        if (this.q != null) {
                            this.q.q(bn2);
                        }
                    }
                    if (q == super.r) {
                        aj = bn2;
                    }
                }
                else {
                    if (bn2 == null) {
                        String q2;
                        if ((q2 = cr.q(i, 0)) == null) {
                            System.err.println("null room name received.");
                            q2 = new String("no name");
                        }
                        bn2 = new bn(q, this.q(q2));
                        this.y.q(bn2);
                    }
                    else {
                        bn2.o = this.q(cr.q(i, 0));
                    }
                    cr.q(i, 1);
                    cr.q(i, 2);
                    cr.q(i, 3);
                    bn2.q(cr.q(i, 4));
                    bn2.p(cr.q(i, 5));
                    bn2.o(cr.q(i, 6));
                    bn2.w(cr.q(i, 7));
                    bn2.e(cr.q(i, 8));
                    bn2.q = this.q(cr.q(i, 1));
                    this.q(cr.q(i, 2));
                    final cm q3 = bn2.q;
                    bn2.q = cr.q(i, 0);
                    if (bn2.q != null && !bn2.q.equals(q3)) {
                        bn2.w = true;
                    }
                    bn2.q(cr.q(i));
                    if (cr.q(i, 62)) {
                        this.h = q;
                    }
                    if (this.q != null) {
                        this.q.w(bn2);
                    }
                    if (this.d != null && bn2.o.equals(this.d)) {
                        this.d = null;
                        (bn = bn2).w = false;
                    }
                }
            }
            if (cu.q == 1) {
                if (this.h == -999 && cr.w() > 0) {
                    this.h = cr.q(0, 0);
                }
            }
            else if (this.h == -999) {
                this.h = cr.q(0, 0);
            }
        }
        finally {}
        if (bn != null) {
            this.q(bn);
            this.q.q(true);
            return;
        }
        if (aj != null) {
            final by by = (by)this.y.w(this.h);
            this.q(this.h);
            new bT(this.q.q(), cu.e, a.s.q(ak.q("The room you were in, \"%1,\" has closed.  You have been moved to \"%2.\""), new String[] { this.q(aj.o), this.q(by.o) }), this).setVisible(true);
        }
    }
    
    protected final void v(final cr cr) {
        try {
            aJ aj = null;
            for (int i = 0; i < cr.w(); ++i) {
                final int q = cr.q(i, 0);
                final bn bn;
                if ((bn = (bn)this.y.w(q)) != null) {
                    this.y.w(q);
                    if (this.q != null) {
                        this.q.q(bn);
                    }
                }
                if (q == super.r) {
                    aj = bn;
                }
            }
            if (aj != null) {
                final by by = (by)this.y.w(this.h);
                this.q(this.h);
                new bT(this.q.q(), cu.e, a.s.q(ak.q("The room you were in, \"%1,\" has closed.  You have been moved to \"%2.\""), new String[] { this.q(aj.o), this.q(by.o) }), this).setVisible(true);
            }
        }
        finally {}
    }
    
    protected final void b(final cr cr) {
        try {
            aU au = null;
            aU au2 = null;
            for (int i = 0; i < cr.w(); ++i) {
                int n = cr.q(i, 0);
                aU au3 = (aU)this.p.w(n);
                if (cr.q(i, 63)) {
                    this.p.w(n);
                }
                else {
                    if (au3 == null) {
                        au3 = new aU(n, cr.q(i, 0));
                        n = this.p.q(au3, n);
                        cr.q(i, 0, n);
                    }
                    else {
                        au3.o = cr.q(i, 0);
                    }
                    au3.q = new Color(cr.q(i, 1));
                    au3.w = new Color(cr.q(i, 2));
                    au3.e = new Color(cr.q(i, 3));
                    au3.r = new Color(cr.q(i, 4));
                    au3.u = new Color(cr.q(i, 5));
                    au3.i = new Color(cr.q(i, 6));
                    au3.o = new Color(cr.q(i, 7));
                    au3.p = new Color(cr.q(i, 8));
                    au3.a = new Color(cr.q(i, 9));
                    au3.s = new Color(cr.q(i, 10));
                    au3.d = new Color(cr.q(i, 11));
                    au3.q = cr.q(i, 1);
                    au3.w = cr.q(i, 12);
                    au3.q = cr.q(i, 13);
                    au3.q();
                    au3.q(cr.q(i));
                    au3.q(cr.q(i, 2));
                    au3.e = cr.q(i, 14);
                    au3.t = new Color(cr.q(i, 15));
                    au3.y = new Color(cr.q(i, 16));
                    au3.h = new Color(cr.q(i, 17));
                    au3.j = new Color(cr.q(i, 18));
                    au3.k = new Color(cr.q(i, 19));
                    au3.l = new Color(cr.q(i, 20));
                    au3.z = new Color(cr.q(i, 21));
                    au3.x = new Color(cr.q(i, 22));
                    au3.c = new Color(cr.q(i, 23));
                    au3.v = new Color(cr.q(i, 24));
                    au3.b = new Color(cr.q(i, 25));
                    au3.n = new Color(cr.q(i, 26));
                    au3.w = cr.q(i, 3);
                    au3.r = cr.q(i, 27);
                    au3.t = cr.q(i, 28);
                    au3.m = new Color(cr.q(i, 29));
                    au3.Q = new Color(cr.q(i, 30));
                    final int n2;
                    if ((n2 = (cr.q(i, 31) & 0xFFFFFF)) != 0) {
                        au3.f = new Color(n2);
                    }
                    au3.y = Math.max(cr.q(i, 32), 100);
                    final int n3;
                    if ((n3 = (cr.q(i, 33) & 0xFFFFFF)) != 0) {
                        au3.g = new Color(n3);
                    }
                    au3.q(cr.q(i, 34));
                    au3.w(cr.q(i, 35));
                    if (au3.q(62)) {
                        au = (aU)au3.clone();
                    }
                    if (n == a.h.q().e) {
                        au2 = (aU)au3.clone();
                    }
                }
            }
            aU q = aU.q;
            if (au2 != null) {
                q = au2;
            }
            else if (au != null) {
                q = au;
            }
            if (this.q != null) {
                this.q.w();
            }
            if (this.q != null) {
                for (int j = 0; j < this.q.q; ++j) {
                    ((aH)this.q.q(j)).q();
                }
            }
            aU.w.q(q);
        }
        finally {}
    }
    
    protected final void n(final cr cr) {
        try {
            for (int i = 0; i < cr.w(); ++i) {
                this.p.w(cr.q(i, 0));
            }
        }
        finally {}
    }
    
    public final synchronized void t(final cr cr) {
        if (cr.q() == 67341) {
            this.q.q(cr);
        }
        else if (cr.q() == 4198416) {
            if (this.q.q() > 0) {
                this.q.q(1, cr);
            }
            else {
                this.q.w(cr);
            }
        }
        else if (cr.q() == 4198496) {
            if (this.q.q() > 1) {
                this.q.q(2, cr);
            }
            else {
                this.q.w(cr);
            }
        }
        else if (cr.q() == 4202544) {
            if (this.q.q() > 2) {
                this.q.q(3, cr);
            }
            else {
                this.q.w(cr);
            }
        }
        else if (cr.q() == 67584) {
            if (this.n) {
                this.q.q(cr);
            }
            else if (this.q.q() > 3) {
                final cr cr2 = (cr)this.q.q(3).q;
                int n = 3;
                if (cr2.q() == 4202544) {
                    n = 4;
                }
                if (this.q.q() > n - 1) {
                    this.q.q(n, cr);
                }
            }
            else {
                this.q.w(cr);
            }
            this.b = true;
        }
        else if (cr.q() == 67585 && cr.q(0, 1) == 65793) {
            this.q.q(cr);
            this.b = true;
        }
        else {
            this.q.w(cr);
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
                    final cr cr = (cr)this.q.q();
                    for (int i = 0; i < this.w.size(); ++i) {
                        ((bc)this.w.elementAt(i)).q(cr);
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
    
    public final void r(final cr cr) {
        try {
            if (this.eos != null) {
                this.eos.q(cr.w().length);
                cr.q(this.eos);
                this.eos.flush();
            }
        }
        catch (IOException ex) {
            if (!this.g) {
                this.w();
            }
        }
    }
    
    private static cr q(final int n, final String s, final cq cq, final int w, final int q, final int n2) {
        final cr cr;
        (cr = new cr(66305, 1)).q = q;
        cr.w = w;
        cr.q(0, 0, cq.s);
        if (n2 != 0) {
            cr.q(0, 2, n2);
        }
        cr.q(0, 0, s);
        cq.r(cr);
        return cr;
    }
    
    public final void w(final String s, final int n, final int n2, final int n3) {
        final cr q = q(66305, s, this, n, n2, n3);
        final by by;
        if ((by = (by)this.y.w(n2)) == null || ((!by.q(61) || this.q(60)) && !by.q(56))) {
            ((W)this).e(q);
        }
    }
    
    public final void q(final String s, final int n) {
        this.w(s, -1, super.r, n);
    }
    
    public abstract void q(final r p0, final l p1);
    
    public final void q(final Font font) {
        aU.w.q = font.getName();
        aU.w.w = font.getStyle();
        aU.w.q = font.getSize();
        aU.w.q();
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
                    if (!b && (this.h || ar.r == super.r) && ar.o != null && cg.q(ar.o, ax.q.getText(), ar.y)) {
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
                        else if (Integer.parseInt(this.q.get(s)) > 0 && (this.h || ar2.r == super.r) && ar2.o != null && cg.q(ar2.o, ax.q.getText(), ar2.y)) {
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
    
    public abstract boolean q(final cl p0);
    
    public abstract void q(final l p0);
    
    public abstract int q(final cl p0);
    
    public abstract void t(final int p0);
    
    public abstract void y(final int p0);
    
    public abstract void q(final int p0, final l p1);
    
    public cq() {
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
        this.q = new bi();
        this.w = new bi();
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
        cq.d = new A();
        this.f = new A();
        cq.d = new A();
        this.h = new A();
        this.q = new Vector();
        this.e = -999L;
        new bZ();
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
        cq.d = new A();
    }
}
