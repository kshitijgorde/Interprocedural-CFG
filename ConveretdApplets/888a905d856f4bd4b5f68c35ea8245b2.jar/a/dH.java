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

public abstract class dH extends p implements bU, cs, Runnable
{
    public boolean y;
    public boolean u;
    public boolean i;
    public boolean o;
    public boolean p;
    private int j;
    private boolean E;
    private boolean R;
    public boolean a;
    private boolean T;
    public boolean s;
    public boolean d;
    public boolean f;
    public boolean g;
    protected cF eis;
    protected c eos;
    public static String f;
    protected i q;
    protected Thread q;
    protected Socket q;
    public aH q;
    protected M o;
    public M p;
    public M a;
    public M s;
    public M d;
    public M f;
    public M g;
    public M h;
    public M j;
    public M k;
    private M q;
    public M l;
    public M z;
    public static M x;
    public ae q;
    public M c;
    public M v;
    public M b;
    public M n;
    public M m;
    public Hashtable q;
    public M Q;
    public Vector q;
    public long u;
    private long w;
    public long i;
    public long o;
    protected URL q;
    protected boolean h;
    protected boolean j;
    public MediaTracker q;
    protected AudioClip[] q;
    protected ce q;
    protected ce w;
    public Frame q;
    protected int U;
    protected int I;
    public int O;
    private String p;
    public boolean k;
    public String g;
    public boolean l;
    public String h;
    public URL w;
    public URL e;
    public URL r;
    public int P;
    public int A;
    public int S;
    public int D;
    public int F;
    public int G;
    public int H;
    public int J;
    public long p;
    public int K;
    public boolean z;
    public boolean x;
    public boolean c;
    public boolean v;
    public boolean b;
    public int L;
    public boolean n;
    public boolean m;
    public boolean Q;
    public boolean W;
    protected bi q;
    protected bi w;
    private Vector w;
    public int Z;
    public int X;
    public int C;
    public int V;
    public int B;
    public int N;
    public int M;
    public int aa;
    public int ab;
    
    public final boolean q() {
        return this.h;
    }
    
    public final cF q() {
        return this.eis;
    }
    
    public final M e() {
        return this.j;
    }
    
    public final boolean w() {
        return this.i;
    }
    
    public final M r() {
        return this.a;
    }
    
    public final Hashtable q() {
        return this.q;
    }
    
    public final M u() {
        return this.o;
    }
    
    public final M i() {
        return this.c;
    }
    
    public final void q(final long n) {
        super.q(n);
    }
    
    public final long q() {
        return this.p;
    }
    
    public final boolean q(final String s, final Object o) {
        return super.q(s, o);
    }
    
    public final M t() {
        return this.p;
    }
    
    public final M y() {
        return this.f;
    }
    
    public final int r() {
        return this.A;
    }
    
    public final M o() {
        return this.h;
    }
    
    public final boolean y() {
        return this.v;
    }
    
    public final boolean u() {
        return this.b;
    }
    
    public final boolean q(final int n, final boolean b) {
        final dI di;
        (di = new dI(67587, 1)).q(0, 0, n);
        di.q(0, 1, this.m ? 1 : 0);
        di.q(0, 0, b);
        this.o(di);
        return true;
    }
    
    public final void w(final p p) {
        super.q(p.w());
        this.P = p.e;
        this.r = p.r;
        this.p(p.w());
        this.o(p.g);
        this.b_(p.i);
        this.w(p.o);
        this.t = p.t;
        this.p = p.p;
        this.y = p.y;
        this.u = p.u;
        super.a = p.a;
    }
    
    public final String w(String s) {
        if (s == null || !this.z) {
            return s;
        }
        try {
            synchronized (this.j) {
                for (int i = 0; i < this.j.q; ++i) {
                    s = ((bw)this.j.q(i)).q(s);
                }
            }
        }
        finally {}
        try {
            synchronized (this.q) {
                for (int j = 0; j < this.q.q; ++j) {
                    s = ((bw)this.q.q(j)).q(s);
                }
            }
        }
        finally {}
        return;
    }
    
    public void s(final dI di) {
        for (int i = 0; i < di.w(); ++i) {
            final p p;
            if ((p = (p)this.a.w(di.q(i, 0))) != null) {
                if (this.c) {
                    final String q;
                    if ((q = di.q(i, 2)) != null) {
                        try {
                            this.q(new URL(q), "_blank");
                        }
                        catch (MalformedURLException ex) {}
                    }
                }
                else {
                    new j(this.q.q(), this, p, di, i).setVisible(true);
                }
            }
        }
    }
    
    public final String q(String s) {
        if (s != null && this.z) {
            try {
                synchronized (this.j) {
                    for (int i = 0; i < this.j.q; ++i) {
                        s = ((bw)this.j.q(i)).q(s);
                    }
                }
            }
            finally {}
            try {
                synchronized (this.q) {
                    for (int j = 0; j < this.q.q; ++j) {
                        s = ((bw)this.q.q(j)).q(s);
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
                synchronized (this.Q) {
                    for (int i = 0; i < this.Q.q; ++i) {
                        q = ((ay)this.Q.q(i)).q(q);
                    }
                }
            }
            finally {}
        }
        return q;
    }
    
    public final void q(final String p4, final String s, final String s2, final boolean b) {
        this.p = p4;
        final dI di;
        (di = new dI(67330, 1)).w = -1;
        di.q = -1;
        di.e = super.s;
        di.q(0, 0, -999);
        di.q(0, 0, p4);
        di.q(0, 1, s);
        di.q(0, 0, new dD(s2));
        if (!b) {
            di.q(0, 58);
        }
        this.o(di);
    }
    
    public final void y() {
        new bA(this.q.q(), this).setVisible(true);
    }
    
    public final void q(final String p3, final String s, final boolean b) {
        this.p = p3;
        final dI di;
        (di = new dI(67330, 1)).w = -1;
        di.q = -1;
        di.e = super.s;
        di.q(0, 0, -999);
        di.q(0, 0, p3);
        di.q(0, 1, s);
        if (!b) {
            di.q(0, 58);
        }
        this.o(di);
    }
    
    public final void q(final int n) {
        final cj cj;
        if ((cj = (cj)this.f.w(n)) != null) {
            this.q(cj);
            return;
        }
        System.err.println("no such room! " + n);
    }
    
    public final void q(cj cj) {
        dD dd;
        if (cj.w && cj.q != null && !this.q(40)) {
            final ca ca;
            (ca = new ca(this.q.q(), this, cj)).setVisible(true);
            dd = ca.q;
        }
        else {
            dd = cj.q;
        }
        final cj cj2 = cj;
        final dD dd2 = dd;
        cj = cj2;
        if (!cj.w || cj.q == null || this.q(40)) {
            final dI di;
            (di = new dI(66049, 1)).q(0, 0, super.s);
            di.q(0, 1, cj.s);
            di.q(0, 0, dd2);
            di.w = -1;
            di.q = -1;
            this.o(di);
        }
    }
    
    public final boolean i() {
        return this.E;
    }
    
    public final boolean o() {
        return this.R;
    }
    
    public final boolean p() {
        return false;
    }
    
    public final int q(final int n) {
        int n2 = 0;
        try {
            synchronized (this.a) {
                for (int i = 0; i < this.a.q; ++i) {
                    final aO ao;
                    if ((ao = (aO)this.a.q(i)).r == n && (!ao.q(23) || this.q(24)) && (!ao.q(25) || this.q(32))) {
                        ++n2;
                    }
                }
            }
        }
        finally {}
        System.out.println("Counted users for room:" + this.f.w(n) + " count=" + n2);
        return n2;
    }
    
    public final Image r(String s2, boolean b) {
        b = (boolean)((this.q != null) ? this.q : new MediaTracker(new Button()));
        Image q;
        if ((q = this.q(s, true)) != null) {
            this.q.q(s, q);
            s2 = (String)(this.j++);
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
    
    public final void u() {
        for (int q = this.c.q, i = 0; i < q; ++i) {
            ((bz)this.c.q(i)).dispose();
        }
        if (this.eos != null) {
            final dI di;
            (di = new dI(65794, 1)).q(0, 0, super.s);
            di.q(0, 0, this.y);
            di.w = -1;
            di.q = -1;
            this.o(di);
            super.r = -999;
            this.U = -999;
            if (this.q != null) {
                final Frame q2 = this.q.q();
                this.q.q();
                if (q2 != null) {
                    q2.dispose();
                }
            }
        }
        this.h = true;
        this.w();
        this.i = false;
        this.o = false;
        if (this.r != null) {
            this.q(this.r, "_self");
        }
    }
    
    public void q(final A a, final aO ao) {
    }
    
    public void q(final A a) {
    }
    
    public void q(final byte[] array) {
    }
    
    public void e(final int n) {
    }
    
    public void g(final dI di) {
        for (int i = 0; i < di.w(); ++i) {
            final aO ao;
            if ((ao = (aO)this.a.w(di.q(i, 0))) != null) {
                this.q(ao, di.q(i, 1), di.q, 0);
            }
        }
    }
    
    protected abstract void q(final aO p0, final int p1, final long p2, final int p3);
    
    protected final void h(final dI di) {
        this.h = true;
        this.w();
        if (this.e != null) {
            this.q(this.e, "_blank");
            return;
        }
        new dd(new Frame(), be.w("You have been kicked"), di.q(0, 0), this).setVisible(true);
    }
    
    protected final void j(final dI di) {
        final String[] array = new String[di.w()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = di.q(i, 0);
        }
        new dd(this.q.q(), be.w("Operation not allowed"), array, this).setVisible(true);
    }
    
    public void f(final dI di) {
        final int q = di.q(0, 0);
        Label_0184: {
            final int q2;
            switch (q2 = di.q(0, 1)) {
                default: {
                    switch (q) {
                        case 0: {
                            a.B.q(be.w("Your nickname could not be changed because the name \"%1\" is already in use by another user, or is reserved."), new String[] { di.q(0, 0) });
                            break Label_0184;
                        }
                        case 3: {
                            a.B.q(be.w("You may not connect to %1 because the version of the %1 Client (%2) is not compatible with the version of %1 Server (%3)."), new String[] { dN.e, "1", "2" });
                            break Label_0184;
                        }
                        case 9: {
                            a.B.q(be.w("You may not connect to %1 because the Server license has been exceeded."), new String[] { dN.e });
                            break Label_0184;
                        }
                        case 10: {
                            a.B.q(be.w("You may not connect to %1 because the Server license has expired."), new String[] { dN.e });
                            break Label_0184;
                        }
                        default: {
                            di.q(0, 0);
                            break Label_0184;
                        }
                    }
                    break;
                }
                case 65793: {
                    String s = null;
                    switch (q) {
                        case 0: {
                            s = a.B.q(be.w("You may not connect to %1 because another user is already using the name \"%2.\" Please choose another and login again."), new String[] { dN.e, super.a });
                            break;
                        }
                        case 1:
                        case 7: {
                            s = a.B.q(be.w("You may not connect to %1 because your name or password was not entered correctly.  Please re-enter this information and login again."), new String[] { dN.e });
                            break;
                        }
                        case 2: {
                            s = a.B.q(be.w("You may not connect to %1 because your account has been disabled."), new String[] { dN.e });
                            break;
                        }
                        case 4: {
                            s = a.B.q(be.w("You may not connect to %1 because the maximum number of simultaneous connections for this site has been reached."), new String[] { dN.e });
                            break;
                        }
                        case 5: {
                            s = a.B.q(be.w("You may not connect to %1 because you have been banned from this %1 site."), new String[] { dN.e });
                            break;
                        }
                        case 6: {
                            s = a.B.q(be.w("You may not connect to %1 because no site with the specified ID could be found."), new String[] { dN.e });
                            break;
                        }
                        case 8: {
                            s = a.B.q(be.w("You are attempting to connect to %1 from an invalid host. Please contact the %1 Site administrator."), new String[] { dN.e });
                            break;
                        }
                        case 11: {
                            s = a.B.q(be.w("Your name must %1 characters or less.  Please re-enter this information."), new String[] { di.q(0, 0) });
                            break;
                        }
                        case 12: {
                            s = a.B.q(be.w("You may not connect to %1 because the maximum number of simultaneous connections from same ip has been reached."), new String[] { dN.e });
                            break;
                        }
                        case 13: {
                            s = a.B.q(be.w("You may not connect to %1 because the maximum number of simultaneous connections for this server has been reached."), new String[] { dN.e });
                            break;
                        }
                        case 14: {
                            s = a.B.q(be.w("Your nickname could not be changed because the name \"%1\" is already in use by another user, or is reserved."), new String[] { di.q(0, 0) });
                            break;
                        }
                        default: {
                            s = di.q(0, 0);
                            break;
                        }
                    }
                    if (s == null) {
                        s = be.w("Your request could not be fulfilled.(" + q + ", " + q2 + ")");
                    }
                    if (q2 == 65793) {
                        this.h = true;
                        this.w();
                    }
                    new dd(this.q, be.w("Note"), s, this).setVisible(true);
                }
            }
        }
    }
    
    protected final void k(final dI di) {
        if (!this.h) {
            String q = di.q(0, 0);
            String q2 = di.q(0, 1);
            if (q == null) {
                q = "Exception somewhere";
            }
            if (q2 == null) {
                q2 = "Unrecognized exception";
            }
            new dd((this.q == null) ? null : this.q.q(), be.w("Alert"), new String[] { q, q2 }, this).setVisible(true);
        }
    }
    
    protected final void l(final dI di) {
        for (int i = 0; i < di.w(); ++i) {
            new dd((this.q == null) ? null : this.q.q(), di.q(i, 0), di.q(i, 1), this, new Color(di.q(i, 2)), new Color(di.q(i, 3))).setVisible(true);
        }
    }
    
    protected static void z(final dI di) {
        try {
            m.q().getAppletContext().showDocument(new URL(di.q(0, 0)), "_blank");
        }
        catch (MalformedURLException ex) {
            System.out.println("Bad URL:" + di.q(0, 0));
        }
    }
    
    protected final void x(final dI di) {
        new dd(this.q.q(), be.w("Inactivity Timeout"), a.B.q(be.w("You have been disconnected because you have been inactive for the past %1 minutes."), new String[] { String.valueOf(di.q(0, 0)) }), this).setVisible(true);
        this.h = true;
        this.w();
    }
    
    public void a(final dI di) {
        try {
            for (int i = 0; i < di.w(); ++i) {
                final int q = di.q(i, 0);
                cz cz = (cz)this.h.w(q);
                if (di.q(i, 63)) {
                    if (cz != null) {
                        this.h.w(q);
                    }
                }
                else {
                    if (cz == null) {
                        cz = new cz(q, di.q(i, 0));
                        this.h.q(cz);
                    }
                    else {
                        cz.a = di.q(i, 0);
                    }
                    cz.w = di.q(i, 1);
                    cz.q = di.q(i, 2);
                    cz.r = di.q(i, 1);
                    cz.p(di.q(i, 3));
                    cz.i(di.q(i, 4));
                    cz.o(di.q(i, 5));
                    cz.q(di.q(i));
                }
            }
        }
        finally {}
    }
    
    protected final void c(final dI di) {
        try {
            for (int i = 0; i < di.w(); ++i) {
                this.h.w(di.q(i, 0));
            }
        }
        finally {}
    }
    
    protected final void v(final dI di) {
        final long q = di.q(-1);
        if (di.q(-1, 60)) {
            this.z = true;
        }
        M m;
        if (di.q(-1, 59)) {
            if ("Admin".equals(dH.f)) {
                this.u = q;
                m = this.j;
            }
            else {
                m = this.q;
            }
            this.w = q;
        }
        else {
            m = this.j;
            this.u = q;
        }
        if (dI.q(this.w | this.u, 62) || dI.q(this.w | this.u, 61)) {
            this.z = true;
        }
        try {
            for (int i = 0; i < di.w(); ++i) {
                final int q2 = di.q(i, 0);
                bw bw = (bw)m.w(q2);
                if (di.q(i, 63)) {
                    if (bw != null) {
                        m.w(q2);
                    }
                }
                else {
                    if (bw == null) {
                        bw = new bw(q2, di.q(i, 0));
                        m.q(bw);
                    }
                    else {
                        bw.a = di.q(i, 0);
                    }
                    bw.q(di.q(i, 1));
                    bw.p(di.q(i, 1));
                    bw.q(di.q(i));
                }
            }
        }
        finally {}
    }
    
    protected final void b(final dI di) {
        try {
            for (int i = 0; i < di.w(); ++i) {
                this.j.w(di.q(i, 0));
            }
        }
        finally {}
    }
    
    protected final void n(final dI di) {
        try {
            for (int i = 0; i < di.w(); ++i) {
                final int q = di.q(i, 0);
                ay ay = (ay)this.Q.w(q);
                if (di.q(i, 63)) {
                    if (ay != null) {
                        this.Q.w(q);
                    }
                }
                else {
                    if (ay == null) {
                        ay = new ay(q, di.q(i, 0));
                        this.Q.q(ay);
                    }
                    else {
                        ay.a = di.q(i, 0);
                    }
                    ay.q(di.q(i, 1));
                    ay.p(di.q(i, 1));
                    ay.o(di.q(i, 2));
                    ay.q(di.q(i));
                }
            }
        }
        finally {}
    }
    
    protected final void m(final dI di) {
        try {
            for (int i = 0; i < di.w(); ++i) {
                this.Q.w(di.q(i, 0));
            }
        }
        finally {}
    }
    
    protected void d(final dI di) {
        di.q(0, 0);
    }
    
    protected final void Q(final dI di) {
        cj cj = null;
        bp bp = null;
        try {
            for (int i = 0; i < di.w(); ++i) {
                final int q = di.q(i, 0);
                cj cj2 = (cj)this.f.w(q);
                if (di.q(i, 63) || di.q(i, 0) == null) {
                    if (cj2 != null) {
                        this.f.w(q);
                        if (this.q != null) {
                            this.q.q(cj2);
                        }
                    }
                    if (q == super.r) {
                        bp = cj2;
                    }
                }
                else {
                    if (cj2 == null) {
                        String q2;
                        if ((q2 = di.q(i, 0)) == null) {
                            System.err.println("null room name received.");
                            q2 = new String("no name");
                        }
                        cj2 = new cj(q, this.q(q2));
                        this.f.q(cj2);
                    }
                    else {
                        cj2.a = this.q(di.q(i, 0));
                    }
                    cj2.q = di.q(i, 1);
                    cj2.w = di.q(i, 2);
                    cj2.e = di.q(i, 3);
                    cj2.q(di.q(i, 4));
                    cj2.p(di.q(i, 5));
                    cj2.o(di.q(i, 6));
                    cj2.w(di.q(i, 7));
                    cj2.e(di.q(i, 8));
                    cj2.q = this.q(di.q(i, 1));
                    cj2.w = this.q(di.q(i, 2));
                    final dD q3 = cj2.q;
                    cj2.q = di.q(i, 0);
                    if (cj2.q != null && !cj2.q.equals(q3)) {
                        cj2.w = true;
                    }
                    cj2.q(di.q(i));
                    if (di.q(i, 62)) {
                        this.U = q;
                    }
                    if (this.q != null) {
                        this.q.w(cj2);
                    }
                    if (this.p != null && cj2.a.equals(this.p)) {
                        this.p = null;
                        (cj = cj2).w = false;
                    }
                }
            }
            if (dN.q == 1) {
                if (this.U == -999 && di.w() > 0) {
                    this.U = di.q(0, 0);
                }
            }
            else if (this.U == -999) {
                this.U = di.q(0, 0);
            }
        }
        finally {}
        if (cj != null) {
            this.q(cj);
            this.q.q(true);
            return;
        }
        if (bp != null) {
            final cB cb = (cB)this.f.w(this.U);
            this.q(this.U);
            new dd(this.q.q(), dN.e, a.B.q(be.w("The room you were in, \"%1,\" has closed.  You have been moved to \"%2.\""), new String[] { this.q(bp.a), this.q(cb.a) }), this).setVisible(true);
        }
    }
    
    protected final void W(final dI di) {
        try {
            bp bp = null;
            for (int i = 0; i < di.w(); ++i) {
                final int q = di.q(i, 0);
                final cj cj;
                if ((cj = (cj)this.f.w(q)) != null) {
                    this.f.w(q);
                    if (this.q != null) {
                        this.q.q(cj);
                    }
                }
                if (q == super.r) {
                    bp = cj;
                }
            }
            if (bp != null) {
                final cB cb = (cB)this.f.w(this.U);
                this.q(this.U);
                new dd(this.q.q(), dN.e, a.B.q(be.w("The room you were in, \"%1,\" has closed.  You have been moved to \"%2.\""), new String[] { this.q(bp.a), this.q(cb.a) }), this).setVisible(true);
            }
        }
        finally {}
    }
    
    protected final void E(final dI di) {
        try {
            bC bc = null;
            bC bc2 = null;
            for (int i = 0; i < di.w(); ++i) {
                int n = di.q(i, 0);
                bC bc3 = (bC)this.k.w(n);
                if (di.q(i, 63)) {
                    this.k.w(n);
                }
                else {
                    if (bc3 == null) {
                        bc3 = new bC(n, di.q(i, 0));
                        n = this.k.q(bc3, n);
                        di.q(i, 0, n);
                    }
                    else {
                        bc3.a = di.q(i, 0);
                    }
                    bc3.q = new Color(di.q(i, 1));
                    bc3.w = new Color(di.q(i, 2));
                    bc3.e = new Color(di.q(i, 3));
                    bc3.r = new Color(di.q(i, 4));
                    bc3.u = new Color(di.q(i, 5));
                    bc3.i = new Color(di.q(i, 6));
                    bc3.o = new Color(di.q(i, 7));
                    bc3.p = new Color(di.q(i, 8));
                    bc3.a = new Color(di.q(i, 9));
                    bc3.s = new Color(di.q(i, 10));
                    bc3.d = new Color(di.q(i, 11));
                    bc3.q = di.q(i, 1);
                    bc3.w = di.q(i, 12);
                    bc3.q = di.q(i, 13);
                    bc3.q();
                    bc3.q(di.q(i));
                    bc3.q(di.q(i, 2));
                    bc3.e = di.q(i, 14);
                    bc3.t = new Color(di.q(i, 15));
                    bc3.y = new Color(di.q(i, 16));
                    bc3.h = new Color(di.q(i, 17));
                    bc3.j = new Color(di.q(i, 18));
                    bc3.k = new Color(di.q(i, 19));
                    bc3.l = new Color(di.q(i, 20));
                    bc3.z = new Color(di.q(i, 21));
                    bc3.x = new Color(di.q(i, 22));
                    bc3.c = new Color(di.q(i, 23));
                    bc3.v = new Color(di.q(i, 24));
                    bc3.b = new Color(di.q(i, 25));
                    bc3.n = new Color(di.q(i, 26));
                    bc3.w = di.q(i, 3);
                    bc3.r = di.q(i, 27);
                    bc3.t = di.q(i, 28);
                    bc3.m = new Color(di.q(i, 29));
                    bc3.Q = new Color(di.q(i, 30));
                    final int n2;
                    if ((n2 = (di.q(i, 31) & 0xFFFFFF)) != 0) {
                        bc3.f = new Color(n2);
                    }
                    bc3.y = Math.max(di.q(i, 32), 100);
                    final int n3;
                    if ((n3 = (di.q(i, 33) & 0xFFFFFF)) != 0) {
                        bc3.g = new Color(n3);
                    }
                    bc3.q(di.q(i, 34));
                    bc3.w(di.q(i, 35));
                    if (bc3.q(62)) {
                        bc = (bC)bc3.clone();
                    }
                    if (n == a.m.q().e) {
                        bc2 = (bC)bc3.clone();
                    }
                }
            }
            bC q = bC.q;
            if (bc2 != null) {
                q = bc2;
            }
            else if (bc != null) {
                q = bc;
            }
            if (this.q != null) {
                this.q.w();
            }
            if (this.o != null) {
                for (int j = 0; j < this.o.q; ++j) {
                    ((bm)this.o.q(j)).q();
                }
            }
            bC.w.q(q);
        }
        finally {}
    }
    
    protected final void R(final dI di) {
        try {
            for (int i = 0; i < di.w(); ++i) {
                this.k.w(di.q(i, 0));
            }
        }
        finally {}
    }
    
    public final synchronized void p(final dI di) {
        if (di.q() == 67341) {
            this.q.q(di);
        }
        else if (di.q() == 4198416) {
            if (this.q.q() > 0) {
                this.q.q(1, di);
            }
            else {
                this.q.w(di);
            }
        }
        else if (di.q() == 4198496) {
            if (this.q.q() > 1) {
                this.q.q(2, di);
            }
            else {
                this.q.w(di);
            }
        }
        else if (di.q() == 4202544) {
            if (this.q.q() > 2) {
                this.q.q(3, di);
            }
            else {
                this.q.w(di);
            }
        }
        else if (di.q() == 67584) {
            if (this.m) {
                this.q.q(di);
            }
            else if (this.q.q() > 3) {
                final dI di2 = (dI)this.q.q(3).q;
                int n = 3;
                if (di2.q() == 4202544) {
                    n = 4;
                }
                if (this.q.q() > n - 1) {
                    this.q.q(n, di);
                }
            }
            else {
                this.q.w(di);
            }
            this.n = true;
        }
        else if (di.q() == 67585 && di.q(0, 1) == 65793) {
            this.q.q(di);
            this.n = true;
        }
        else {
            this.q.w(di);
        }
        synchronized (this.q) {
            this.q.notifyAll();
        }
    }
    
    public void g() {
        this.u();
    }
    
    public abstract void t();
    
    public void run() {
        try {
            while (!this.h) {
                try {
                    if (!this.n) {
                        throw new NoSuchElementException();
                    }
                    while (this.q.q()) {
                        synchronized (this.q) {
                            this.q.wait();
                            continue;
                        }
                        break;
                    }
                    final dI di = (dI)this.q.q();
                    for (int i = 0; i < this.w.size(); ++i) {
                        ((bU)this.w.elementAt(i)).q(di);
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
            if (this.h) {
                this.w();
            }
        }
        System.out.println("MUB stoped:" + this.h);
    }
    
    public final void o(final dI di) {
        try {
            if (this.eos != null) {
                this.eos.q(di.w().length);
                di.q(this.eos);
                this.eos.flush();
            }
        }
        catch (IOException ex) {
            if (!this.h) {
                this.w();
            }
        }
    }
    
    private static dI q(final int n, final String s, final dH dh, final int w, final int q, final int n2) {
        final dI di;
        (di = new dI(66305, 1)).q = q;
        di.w = w;
        di.q(0, 0, dh.s);
        if (n2 != 0) {
            di.q(0, 2, n2);
        }
        di.q(0, 0, s);
        dh.o(di);
        return di;
    }
    
    public final void w(final String s, final int n, final int n2, final int n3) {
        final dI q = q(66305, s, this, n, n2, n3);
        final cB cb;
        if ((cb = (cB)this.f.w(n2)) == null || ((!cb.q(61) || this.q(60)) && !cb.q(56))) {
            ((ap)this).u(q);
        }
    }
    
    public final void q(final String s, final int n) {
        this.w(s, -1, super.r, n);
    }
    
    public abstract void q(final A p0, final p p1);
    
    public final void q(final Font font) {
        bC.w.q = font.getName();
        bC.w.w = font.getStyle();
        bC.w.q = font.getSize();
        bC.w.q();
        if (this.q != null) {
            this.q.w();
        }
        if (this.o != null) {
            for (int i = 0; i < this.o.q; ++i) {
                ((bm)this.o.q(i)).q();
            }
        }
    }
    
    public final void q(final boolean j, final boolean b) {
        this.j = j;
        try {
            synchronized (this.a) {
                for (int i = 0; i < this.a.q; ++i) {
                    final aO ao = (aO)this.a.q(i);
                    if (!b && (this.j || ao.r == super.r) && ao.a != null && ds.q(ao.a, bb.q.getText(), ao.u)) {
                        this.q.q(ao, true);
                    }
                    else {
                        this.q.q(ao);
                    }
                }
            }
            if (b) {
                synchronized (this.q) {
                    final Enumeration<String> keys = this.q.keys();
                    while (keys.hasMoreElements()) {
                        final String s = keys.nextElement();
                        final aO ao2;
                        if ((ao2 = (aO)this.a.w(Integer.parseInt(s))) == null) {
                            this.q.remove(s);
                        }
                        else if (Integer.parseInt(this.q.get(s)) > 0 && (this.j || ao2.r == super.r) && ao2.a != null && ds.q(ao2.a, bb.q.getText(), ao2.u)) {
                            this.q.q(ao2, true);
                        }
                        else {
                            this.q.q(ao2);
                        }
                    }
                }
            }
        }
        finally {}
    }
    
    public void q(final boolean b) {
    }
    
    public void i() {
    }
    
    public final void e(final boolean b) {
        this.E = false;
    }
    
    public final void r(final boolean b) {
        this.R = false;
    }
    
    public void r(final int n) {
    }
    
    public abstract boolean q(final dB p0);
    
    public abstract void q(final p p0);
    
    public abstract int q(final dB p0);
    
    public abstract void t(final int p0);
    
    public abstract void y(final int p0);
    
    public abstract void q(final int p0, final p p1);
    
    public dH() {
        super(-999, null);
        this.g = true;
        this.s = new M();
        this.d = new M();
        this.l = new M();
        this.z = new M();
        this.q = new ae();
        this.n = new M();
        this.m = new M();
        this.q = new Hashtable();
        this.Q = new M();
        this.o = 165L;
        this.q = new ce();
        this.w = new ce();
        this.K = 7;
        this.L = -1;
        this.n = false;
        this.m = false;
        this.Q = false;
        this.W = false;
        this.w = new Vector();
        this.Z = 1000;
        this.X = 3;
        this.C = 3;
        this.V = 3;
        this.B = 35;
        this.N = 3;
        this.M = 1000;
        this.aa = 3;
        this.ab = 3;
        this.y = false;
        this.j = 1000;
        this.q = null;
        this.p = new M();
        this.a = new M();
        this.c = false;
        this.z = false;
        this.h = true;
        this.k = new M();
        this.j = false;
        this.u = false;
        this.i = false;
        this.o = false;
        this.p = false;
        this.E = true;
        this.R = true;
        this.a = false;
        this.T = false;
        this.o = new M();
        this.p = new M();
        this.a = new M();
        this.d = new M();
        this.f = new M();
        this.g = new M();
        this.h = new M();
        this.j = new M();
        this.q = new M();
        this.v = new M();
        dH.x = new M();
        this.c = new M();
        dH.x = new M();
        this.b = new M();
        this.q = new Vector();
        this.u = -999L;
        new dj();
        this.U = -999;
        this.I = -999;
        this.O = -999;
        this.k = false;
        this.l = false;
        this.P = -999;
        this.A = 2;
        this.S = -999;
        this.q = -999;
        this.w = -999;
        this.D = 8;
        this.F = 3;
        this.G = 4;
        this.H = 9;
        this.J = 1;
        this.q = false;
        this.x = false;
        this.v = false;
        this.b = false;
        this.w.addElement(this);
        this.w.addElement(new bq(this));
        this.w.addElement(new B(this));
    }
    
    public M q() {
        return this.b;
    }
    
    public final String a_() {
        return this.h;
    }
    
    public final void q(final String h) {
        this.h = h;
    }
    
    public final int a_() {
        return this.S;
    }
    
    public final void a_(final int s) {
        this.S = s;
    }
    
    public final Frame q() {
        return this.q;
    }
    
    static {
        final String[] array = { null, null, null, null, null, null, null, null, null };
        final String[] array2 = { "bass.au", "bell.au", "castanet.au", "chime.au", "conga.au", "cowBell.au", "doubleBell.au", "drumRoll.au", "harp.au" };
        dH.x = new M();
    }
}
