// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Enumeration;
import java.awt.Font;
import java.util.NoSuchElementException;
import com.spilka.client.muc.AppletAbstract;
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
import java.io.DataOutputStream;
import java.io.DataInputStream;

public abstract class bH extends bp implements bz, cd, Runnable
{
    public boolean t;
    public boolean y;
    public boolean u;
    public boolean i;
    public boolean o;
    private int D;
    private boolean W;
    private boolean E;
    public boolean p;
    private boolean R;
    public boolean a;
    public boolean s;
    public boolean d;
    public boolean f;
    protected DataInputStream q;
    protected DataOutputStream q;
    public static String p;
    protected bx q;
    protected Thread q;
    protected Socket q;
    public ce q;
    protected cq q;
    public cq w;
    public cq e;
    public cq r;
    public cq t;
    public cq y;
    public cq u;
    public cq i;
    public cq o;
    public cq p;
    private cq z;
    public cq a;
    public cq s;
    public static cq d;
    public ck q;
    public cq f;
    public cq g;
    public cq h;
    public cq j;
    public cq k;
    public Hashtable q;
    private cq x;
    public cq l;
    public Vector q;
    public long q;
    private long e;
    protected URL q;
    protected boolean g;
    protected boolean h;
    public MediaTracker q;
    protected AudioClip[] q;
    protected cs q;
    protected cs w;
    public Frame q;
    protected int j;
    protected int k;
    protected int l;
    private String d;
    public boolean j;
    public String a;
    public boolean k;
    public String s;
    public URL w;
    public URL e;
    public URL r;
    public int z;
    public int x;
    public int c;
    public int v;
    public int b;
    public int n;
    public int m;
    public int Q;
    public long w;
    public int W;
    public boolean l;
    public boolean z;
    public boolean x;
    public boolean c;
    public boolean v;
    public int E;
    public boolean b;
    public boolean n;
    public boolean m;
    public boolean Q;
    protected cp q;
    protected cp w;
    private Vector w;
    public int R;
    public int T;
    public int Y;
    public int U;
    public int I;
    public int O;
    public int P;
    public int A;
    public int S;
    
    public final boolean q() {
        return this.g;
    }
    
    public final DataInputStream q() {
        return this.q;
    }
    
    public final cq q() {
        return this.o;
    }
    
    public final boolean w() {
        return this.u;
    }
    
    public final cq r() {
        return this.e;
    }
    
    public final Hashtable q() {
        return this.q;
    }
    
    public final cq t() {
        return this.q;
    }
    
    public final cq y() {
        return this.f;
    }
    
    public final void q(final long n) {
        super.q(n);
    }
    
    public final long w() {
        return this.w;
    }
    
    public final boolean q(final String s, final Object o) {
        return super.q(s, o);
    }
    
    public final cq w() {
        return this.w;
    }
    
    public final cq e() {
        return this.y;
    }
    
    public final int o() {
        return this.x;
    }
    
    public final cq u() {
        return this.i;
    }
    
    public final boolean r() {
        return this.c;
    }
    
    public final boolean t() {
        return this.v;
    }
    
    public final boolean q(final int n, final boolean b) {
        final cJ cj;
        (cj = new cJ(67587, 1)).q(0, 0, n);
        cj.q(0, 1, this.n ? 1 : 0);
        cj.q(0, 0, b);
        this.q(cj);
        return true;
    }
    
    public final String w(String s) {
        if (s == null || !this.l) {
            return s;
        }
        final cq o = this.o;
        try {
            synchronized (this.o) {
                for (int i = 0; i < this.o.q(); ++i) {
                    s = ((bc)this.o.q(i)).q(s);
                }
            }
        }
        finally {
            final cq o2 = this.o;
        }
        final cq z = this.z;
        try {
            synchronized (this.z) {
                for (int j = 0; j < this.z.q(); ++j) {
                    s = ((bc)this.z.q(j)).q(s);
                }
            }
        }
        finally {
            final cq z2 = this.z;
        }
        return;
    }
    
    public final void e(final cJ cj) {
        for (int i = 0; i < cj.w(); ++i) {
            final bp bp;
            if ((bp = (bp)this.e.w(cj.q(i, 0))) != null) {
                if (this.x) {
                    final String q;
                    if ((q = cj.q(i, 2)) != null) {
                        try {
                            this.q(new URL(q), "_blank");
                        }
                        catch (MalformedURLException ex) {}
                    }
                }
                else {
                    new bU(this.q.q(), this, bp, cj, i).setVisible(true);
                }
            }
        }
    }
    
    public final String q(String s) {
        if (s != null && this.l) {
            final cq o = this.o;
            try {
                synchronized (this.o) {
                    for (int i = 0; i < this.o.q(); ++i) {
                        s = ((bc)this.o.q(i)).q(s);
                    }
                }
            }
            finally {
                final cq o2 = this.o;
            }
            final cq z = this.z;
            try {
                synchronized (this.z) {
                    for (int j = 0; j < this.z.q(); ++j) {
                        s = ((bc)this.z.q(j)).q(s);
                    }
                }
            }
            finally {
                final cq z2 = this.z;
            }
        }
        return s;
    }
    
    public final String e(String q) {
        if (q != null) {
            final cq x = this.x;
            try {
                synchronized (this.x) {
                    for (int i = 0; i < this.x.q(); ++i) {
                        q = ((bm)this.x.q(i)).q(q);
                    }
                }
            }
            finally {
                final cq x2 = this.x;
            }
        }
        return q;
    }
    
    public final void q(final String d, final String s, final String s2, final boolean b) {
        this.d = d;
        final cJ cj;
        (cj = new cJ(67330, 1)).w = -1;
        cj.q = -1;
        cj.e = super.q;
        cj.q(0, 0, -999);
        cj.q(0, 0, d);
        cj.q(0, 1, s);
        cj.q(0, 0, new cH(s2));
        if (!b) {
            cj.q(0, 58);
        }
        this.q(cj);
    }
    
    public final void q() {
        new Y(this.q.q(), this).setVisible(true);
    }
    
    public final void q(final String d, final String s, final boolean b) {
        this.d = d;
        final cJ cj;
        (cj = new cJ(67330, 1)).w = -1;
        cj.q = -1;
        cj.e = super.q;
        cj.q(0, 0, -999);
        cj.q(0, 0, d);
        cj.q(0, 1, s);
        if (!b) {
            cj.q(0, 58);
        }
        this.q(cj);
    }
    
    public final void q(final int n) {
        final bO bo;
        if ((bo = (bO)this.y.w(n)) != null) {
            this.q(bo);
            return;
        }
        System.err.println("no such room! " + n);
    }
    
    public final void q(final bO bo) {
        cH ch;
        if (bo.w && bo.q != null && !this.q(40)) {
            final T t;
            (t = new T(this.q.q(), this, bo)).setVisible(true);
            ch = t.q();
        }
        else {
            ch = bo.q;
        }
        final cH ch2 = ch;
        if (!bo.w || bo.q == null || this.q(40)) {
            final cJ cj;
            (cj = new cJ(66049, 1)).q(0, 0, super.q);
            cj.q(0, 1, bo.q);
            cj.q(0, 0, ch2);
            cj.w = -1;
            cj.q = -1;
            this.q(cj);
        }
    }
    
    public final boolean y() {
        return this.W;
    }
    
    public final boolean u() {
        return this.E;
    }
    
    public final boolean i() {
        return this.R;
    }
    
    public final int q(final int n) {
        int n2 = 0;
        final cq e = this.e;
        try {
            synchronized (this.e) {
                for (int i = 0; i < this.e.q(); ++i) {
                    final bV bv;
                    if ((bv = (bV)this.e.q(i)).i == n && (!bv.q(23) || this.q(24)) && (!bv.q(25) || this.q(32))) {
                        ++n2;
                    }
                }
            }
        }
        finally {
            final cq e2 = this.e;
        }
        System.out.println("Counted users for room:" + this.y.w(n) + " count=" + n2);
        return n2;
    }
    
    public final Image q(String s2, boolean b) {
        b = (boolean)((this.q != null) ? this.q : new MediaTracker(new Button()));
        Image w;
        if ((w = this.w(s, true)) != null) {
            this.q.q(s, w);
            s2 = (String)(this.D++);
            ((MediaTracker)b).addImage(w, (int)s2);
            try {
                ((MediaTracker)b).waitForID((int)s2);
            }
            catch (InterruptedException ex) {}
            if (((MediaTracker)b).isErrorID((int)s2)) {
                w = null;
            }
        }
        return w;
    }
    
    public Image q(final URL url) {
        return null;
    }
    
    public Image w(final String s, final boolean b) {
        return null;
    }
    
    public final void w() {
        for (int q = this.f.q(), i = 0; i < q; ++i) {
            ((J)this.f.q(i)).dispose();
        }
        if (this.q != null) {
            final cJ cj;
            (cj = new cJ(65794, 1)).q(0, 0, super.q);
            cj.q(0, 0, this.y);
            cj.w = -1;
            cj.q = -1;
            this.q(cj);
            super.i = -999;
            this.j = -999;
            if (this.q != null) {
                final Frame q2 = this.q.q();
                this.q.q();
                if (q2 != null) {
                    q2.dispose();
                }
            }
        }
        this.g = true;
        this.e();
        this.u = false;
        this.i = false;
        if (this.r != null) {
            this.q(this.r, "_self");
        }
    }
    
    public void i(final int n) {
    }
    
    protected final void r(final cJ cj) {
        for (int i = 0; i < cj.w(); ++i) {
            final bV bv;
            if ((bv = (bV)this.e.w(cj.q(i, 0))) != null) {
                this.q(bv, cj.q(i, 1), cj.q, cj.r);
            }
        }
    }
    
    protected abstract void q(final bV p0, final int p1, final long p2, final int p3);
    
    protected final void t(final cJ cj) {
        final String q;
        if ((q = cj.q(0, 0)) != null && q.indexOf("HARDBAN") >= 0) {
            final int int1 = Integer.parseInt(q.substring(q.indexOf("HARDBAN") + "HARDBAN".length()));
            cu.q("setLS", new String[] { "bid", "" + int1 }, null);
            cu.q("setCookie", new String[] { "bID=", "" + int1 }, null);
            cv.q("You have been kicked");
            return;
        }
        this.g = true;
        this.e();
        if (this.e != null) {
            this.q(this.e, "_blank");
            return;
        }
        new b(new Frame(), cv.q("You have been kicked"), q, this).setVisible(true);
    }
    
    protected final void y(final cJ cj) {
        final String[] array = new String[cj.w()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = cj.q(i, 0);
        }
        new b(this.q.q(), cv.q("Operation not allowed"), array, this).setVisible(true);
    }
    
    public final void u(final cJ cj) {
        final int q = cj.q(0, 0);
        Label_0184: {
            final int q2;
            switch (q2 = cj.q(0, 1)) {
                default: {
                    switch (q) {
                        case 0: {
                            cv.q(cv.q("Your nickname could not be changed because the name \"%1\" is already in use by another user, or is reserved."), new String[] { cj.q(0, 0) });
                            break Label_0184;
                        }
                        case 3: {
                            cv.q(cv.q("You may not connect to %1 because the version of the %1 Client (%2) is not compatible with the version of %1 Server (%3)."), new String[] { a.a.e, "1", "2" });
                            break Label_0184;
                        }
                        case 9: {
                            cv.q(cv.q("You may not connect to %1 because the Server license has been exceeded."), new String[] { a.a.e });
                            break Label_0184;
                        }
                        case 10: {
                            cv.q(cv.q("You may not connect to %1 because the Server license has expired."), new String[] { a.a.e });
                            break Label_0184;
                        }
                        default: {
                            cj.q(0, 0);
                            break Label_0184;
                        }
                    }
                    break;
                }
                case 65793: {
                    String s = null;
                    switch (q) {
                        case 0: {
                            s = cv.q(cv.q("You may not connect to %1 because another user is already using the name \"%2.\" Please choose another and login again."), new String[] { a.a.e, this.getName() });
                            break;
                        }
                        case 1:
                        case 7: {
                            s = cv.q(cv.q("You may not connect to %1 because your name or password was not entered correctly.  Please re-enter this information and login again."), new String[] { a.a.e });
                            break;
                        }
                        case 2: {
                            s = cv.q(cv.q("You may not connect to %1 because your account has been disabled."), new String[] { a.a.e });
                            break;
                        }
                        case 4: {
                            s = cv.q(cv.q("You may not connect to %1 because the maximum number of simultaneous connections for this site has been reached."), new String[] { a.a.e });
                            break;
                        }
                        case 5: {
                            s = cv.q(cv.q("You may not connect to %1 because you have been banned from this %1 site."), new String[] { a.a.e });
                            break;
                        }
                        case 6: {
                            s = cv.q(cv.q("You may not connect to %1 because no site with the specified ID could be found."), new String[] { a.a.e });
                            break;
                        }
                        case 8: {
                            s = cv.q(cv.q("You are attempting to connect to %1 from an invalid host. Please contact the %1 Site administrator."), new String[] { a.a.e });
                            break;
                        }
                        case 11: {
                            s = cv.q(cv.q("Your name must %1 characters or less.  Please re-enter this information."), new String[] { cj.q(0, 0) });
                            break;
                        }
                        case 12: {
                            s = cv.q(cv.q("You may not connect to %1 because the maximum number of simultaneous connections from same ip has been reached."), new String[] { a.a.e });
                            break;
                        }
                        case 13: {
                            s = cv.q(cv.q("You may not connect to %1 because the maximum number of simultaneous connections for this server has been reached."), new String[] { a.a.e });
                            break;
                        }
                        case 14: {
                            s = cv.q(cv.q("Your nickname could not be changed because the name \"%1\" is already in use by another user, or is reserved."), new String[] { cj.q(0, 0) });
                            break;
                        }
                        default: {
                            s = cj.q(0, 0);
                            break;
                        }
                    }
                    if (s == null) {
                        s = cv.q("Your request could not be fulfilled.(" + q + ", " + q2 + ")");
                    }
                    if (q2 == 65793) {
                        this.g = true;
                        this.e();
                    }
                    new b(this.q, cv.q("Note"), s, this).setVisible(true);
                }
            }
        }
    }
    
    protected final void i(final cJ cj) {
        if (!this.g) {
            String q = cj.q(0, 0);
            String q2 = cj.q(0, 1);
            if (q == null) {
                q = "Exception somewhere";
            }
            if (q2 == null) {
                q2 = "Unrecognized exception";
            }
            new b((this.q == null) ? null : this.q.q(), cv.q("Alert"), new String[] { q, q2 }, this).setVisible(true);
        }
    }
    
    protected final void o(final cJ cj) {
        for (int i = 0; i < cj.w(); ++i) {
            new b((this.q == null) ? null : this.q.q(), cj.q(i, 0), cj.q(i, 1), this, new Color(cj.q(i, 2)), new Color(cj.q(i, 3))).setVisible(true);
        }
    }
    
    protected final void p(final cJ cj) {
        new b(this.q.q(), cv.q("Inactivity Timeout"), cv.q(cv.q("You have been disconnected because you have been inactive for the past %1 minutes."), new String[] { String.valueOf(cj.q(0, 0)) }), this).setVisible(true);
        this.g = true;
        this.e();
    }
    
    public final void a(final cJ cj) {
        final cq i = this.i;
        try {
            for (int j = 0; j < cj.w(); ++j) {
                final int q = cj.q(j, 0);
                bb bb = (bb)this.i.w(q);
                if (cj.q(j, 63)) {
                    if (bb != null) {
                        this.i.w(q);
                    }
                }
                else {
                    if (bb == null) {
                        bb = new bb(q, cj.q(j, 0));
                        this.i.q(bb);
                    }
                    else {
                        bb.e = cj.q(j, 0);
                    }
                    bb.y = cj.q(j, 1);
                    bb.t = cj.q(j, 2);
                    bb.q = cj.q(j, 1);
                    bb.r(cj.q(j, 3));
                    bb.b_(cj.q(j, 4));
                    bb.w(cj.q(j, 5));
                    bb.q(cj.q(j));
                }
            }
        }
        finally {
            final cq k = this.i;
        }
    }
    
    protected final void s(final cJ cj) {
        final cq i = this.i;
        try {
            for (int j = 0; j < cj.w(); ++j) {
                this.i.w(cj.q(j, 0));
            }
        }
        finally {
            final cq k = this.i;
        }
    }
    
    protected final void d(final cJ cj) {
        final long q = cj.q(-1);
        if (cj.q(-1, 60)) {
            this.l = true;
        }
        cq cq;
        if (cj.q(-1, 59)) {
            if ("Admin".equals(bH.p)) {
                this.q = q;
                cq = this.o;
            }
            else {
                cq = this.z;
            }
            this.e = q;
        }
        else {
            cq = this.o;
            this.q = q;
        }
        if (cJ.q(this.e | this.q, 62) || cJ.q(this.e | this.q, 61)) {
            this.l = true;
        }
        try {
            for (int i = 0; i < cj.w(); ++i) {
                final int q2 = cj.q(i, 0);
                bc bc = (bc)cq.w(q2);
                if (cj.q(i, 63)) {
                    if (bc != null) {
                        cq.w(q2);
                    }
                }
                else {
                    if (bc == null) {
                        bc = new bc(q2, cj.q(i, 0));
                        cq.q(bc);
                    }
                    else {
                        bc.e = cj.q(i, 0);
                    }
                    bc.q(cj.q(i, 1));
                    bc.r(cj.q(i, 1));
                    bc.q(cj.q(i));
                }
            }
        }
        finally {}
    }
    
    protected final void f(final cJ cj) {
        final cq o = this.o;
        try {
            for (int i = 0; i < cj.w(); ++i) {
                this.o.w(cj.q(i, 0));
            }
        }
        finally {
            final cq o2 = this.o;
        }
    }
    
    protected final void g(final cJ cj) {
        final cq x = this.x;
        try {
            for (int i = 0; i < cj.w(); ++i) {
                final int q = cj.q(i, 0);
                bm bm = (bm)this.x.w(q);
                if (cj.q(i, 63)) {
                    if (bm != null) {
                        this.x.w(q);
                    }
                }
                else {
                    if (bm == null) {
                        bm = new bm(q, cj.q(i, 0));
                        this.x.q(bm);
                    }
                    else {
                        bm.e = cj.q(i, 0);
                    }
                    bm.q(cj.q(i, 1));
                    bm.r(cj.q(i, 1));
                    bm.w(cj.q(i, 2));
                    bm.q(cj.q(i));
                }
            }
        }
        finally {
            final cq x2 = this.x;
        }
    }
    
    protected final void h(final cJ cj) {
        final cq x = this.x;
        try {
            for (int i = 0; i < cj.w(); ++i) {
                this.x.w(cj.q(i, 0));
            }
        }
        finally {
            final cq x2 = this.x;
        }
    }
    
    protected final void j(final cJ cj) {
        bO bo = null;
        ba ba = null;
        final cq y = this.y;
        try {
            for (int i = 0; i < cj.w(); ++i) {
                final int q = cj.q(i, 0);
                bO bo2 = (bO)this.y.w(q);
                if (cj.q(i, 63) || cj.q(i, 0) == null) {
                    if (bo2 != null) {
                        this.y.w(q);
                        if (this.q != null) {
                            this.q.q(bo2);
                        }
                    }
                    if (q == super.i) {
                        ba = bo2;
                    }
                }
                else {
                    if (bo2 == null) {
                        String q2;
                        if ((q2 = cj.q(i, 0)) == null) {
                            System.err.println("null room name received.");
                            q2 = new String("no name");
                        }
                        bo2 = new bO(q, this.q(q2));
                        this.y.q(bo2);
                    }
                    else {
                        bo2.e = this.q(cj.q(i, 0));
                    }
                    cj.q(i, 1);
                    cj.q(i, 2);
                    cj.q(i, 3);
                    bo2.y(cj.q(i, 4));
                    bo2.r(cj.q(i, 5));
                    bo2.w(cj.q(i, 6));
                    bo2.u(cj.q(i, 7));
                    bo2.i(cj.q(i, 8));
                    bo2.q = this.q(cj.q(i, 1));
                    this.q(cj.q(i, 2));
                    final cH q3 = bo2.q;
                    bo2.q = cj.q(i, 0);
                    if (bo2.q != null && !bo2.q.equals(q3)) {
                        bo2.w = true;
                    }
                    bo2.q(cj.q(i));
                    if (cj.q(i, 62)) {
                        this.j = q;
                    }
                    if (this.q != null) {
                        this.q.w(bo2);
                    }
                    if (this.d != null && bo2.getName().equals(this.d)) {
                        this.d = null;
                        (bo = bo2).w = false;
                    }
                }
            }
            if (a.a.q == 1) {
                if (this.j == -999 && cj.w() > 0) {
                    this.j = cj.q(0, 0);
                }
            }
            else if (this.j == -999) {
                this.j = cj.q(0, 0);
            }
        }
        finally {
            final cq y2 = this.y;
        }
        if (bo != null) {
            this.q(bo);
            this.q.q(true);
            return;
        }
        if (ba != null) {
            final bk bk = (bk)this.y.w(this.j);
            this.q(this.j);
            new b(this.q.q(), a.a.e, cv.q(cv.q("The room you were in, \"%1,\" has closed.  You have been moved to \"%2.\""), new String[] { this.q(ba.getName()), this.q(bk.getName()) }), this).setVisible(true);
        }
    }
    
    protected final void k(final cJ cj) {
        final cq y = this.y;
        try {
            ba ba = null;
            for (int i = 0; i < cj.w(); ++i) {
                final int q = cj.q(i, 0);
                final bO bo;
                if ((bo = (bO)this.y.w(q)) != null) {
                    this.y.w(q);
                    if (this.q != null) {
                        this.q.q(bo);
                    }
                }
                if (q == super.i) {
                    ba = bo;
                }
            }
            if (ba != null) {
                final bk bk = (bk)this.y.w(this.j);
                this.q(this.j);
                new b(this.q.q(), a.a.e, cv.q(cv.q("The room you were in, \"%1,\" has closed.  You have been moved to \"%2.\""), new String[] { this.q(ba.getName()), this.q(bk.getName()) }), this).setVisible(true);
            }
        }
        finally {
            final cq y2 = this.y;
        }
    }
    
    protected final void l(final cJ cj) {
        final cq p = this.p;
        try {
            be be = null;
            be be2 = null;
            for (int i = 0; i < cj.w(); ++i) {
                int n = cj.q(i, 0);
                be be3 = (be)this.p.w(n);
                if (cj.q(i, 63)) {
                    this.p.w(n);
                }
                else {
                    if (be3 == null) {
                        be3 = new be(n, cj.q(i, 0));
                        n = this.p.q(be3, n);
                        cj.q(i, 0, n);
                    }
                    else {
                        be3.e = cj.q(i, 0);
                    }
                    be3.q = new Color(cj.q(i, 1));
                    be3.w = new Color(cj.q(i, 2));
                    be3.e = new Color(cj.q(i, 3));
                    be3.r = new Color(cj.q(i, 4));
                    be3.u = new Color(cj.q(i, 5));
                    be3.i = new Color(cj.q(i, 6));
                    be3.o = new Color(cj.q(i, 7));
                    be3.p = new Color(cj.q(i, 8));
                    be3.a = new Color(cj.q(i, 9));
                    be3.s = new Color(cj.q(i, 10));
                    be3.d = new Color(cj.q(i, 11));
                    be3.q = cj.q(i, 1);
                    be3.y = cj.q(i, 12);
                    be3.t = cj.q(i, 13);
                    be3.q();
                    be3.q(cj.q(i));
                    be3.q(cj.q(i, 2));
                    be3.u = cj.q(i, 14);
                    be3.t = new Color(cj.q(i, 15));
                    be3.y = new Color(cj.q(i, 16));
                    be3.h = new Color(cj.q(i, 17));
                    be3.j = new Color(cj.q(i, 18));
                    be3.k = new Color(cj.q(i, 19));
                    be3.l = new Color(cj.q(i, 20));
                    be3.z = new Color(cj.q(i, 21));
                    be3.x = new Color(cj.q(i, 22));
                    be3.c = new Color(cj.q(i, 23));
                    be3.v = new Color(cj.q(i, 24));
                    be3.b = new Color(cj.q(i, 25));
                    be3.n = new Color(cj.q(i, 26));
                    be3.w = cj.q(i, 3);
                    be3.i = cj.q(i, 27);
                    be3.o = cj.q(i, 28);
                    be3.m = new Color(cj.q(i, 29));
                    be3.Q = new Color(cj.q(i, 30));
                    final int n2;
                    if ((n2 = (cj.q(i, 31) & 0xFFFFFF)) != 0) {
                        be3.f = new Color(n2);
                    }
                    be3.p = Math.max(cj.q(i, 32), 100);
                    final int n3;
                    if ((n3 = (cj.q(i, 33) & 0xFFFFFF)) != 0) {
                        be3.g = new Color(n3);
                    }
                    be3.y(cj.q(i, 34));
                    be3.u(cj.q(i, 35));
                    if (be3.q(62)) {
                        be = (be)be3.clone();
                    }
                    if (n == AppletAbstract.q().e) {
                        be2 = (be)be3.clone();
                    }
                }
            }
            be q = a.be.q;
            if (be2 != null) {
                q = be2;
            }
            else if (be != null) {
                q = be;
            }
            if (this.q != null) {
                this.q.w();
            }
            if (this.q != null) {
                for (int j = 0; j < this.q.q(); ++j) {
                    ((bM)this.q.q(j)).w();
                }
            }
            a.be.w.q(q);
        }
        finally {
            final cq p2 = this.p;
        }
    }
    
    protected final void z(final cJ cj) {
        final cq p = this.p;
        try {
            for (int i = 0; i < cj.w(); ++i) {
                this.p.w(cj.q(i, 0));
            }
        }
        finally {
            final cq p2 = this.p;
        }
    }
    
    public final synchronized void w(final cJ cj) {
        if (cj.q() == 67341) {
            this.q.q(cj);
        }
        else if (cj.q() == 4198416) {
            this.q.q(cj);
        }
        else if (cj.q() == 4198496) {
            if (this.q.q() > 1) {
                this.q.q(2, cj);
            }
            else {
                this.q.w(cj);
            }
        }
        else if (cj.q() == 4202544) {
            if (this.q.q() > 2) {
                this.q.q(3, cj);
            }
            else {
                this.q.w(cj);
            }
        }
        else if (cj.q() == 67584) {
            if (this.n) {
                this.q.q(1, cj);
            }
            else if (this.q.q() > 3) {
                final cJ cj2 = (cJ)this.q.q(3).q;
                int n = 3;
                if (cj2.q() == 4202544) {
                    n = 4;
                }
                if (this.q.q() > n - 1) {
                    this.q.q(n, cj);
                }
            }
            else {
                this.q.w(cj);
            }
            this.b = true;
        }
        else if (cj.q() == 67585 && cj.q(0, 1) == 65793) {
            this.q.q(cj);
            this.b = true;
        }
        else {
            this.q.w(cj);
        }
        synchronized (this.q) {
            this.q.notifyAll();
        }
    }
    
    public abstract void t();
    
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
                    final cJ cj = (cJ)this.q.q();
                    for (int i = 0; i < this.w.size(); ++i) {
                        ((cd)this.w.elementAt(i)).x(cj);
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
            ex.printStackTrace();
            if (this.g) {
                this.e();
            }
        }
        System.out.println("MUB stoped:" + this.g);
    }
    
    public final void q(final cJ cj) {
        try {
            if (this.q != null) {
                final byte[] q = cM.q(new cI(cj).w());
                this.q.writeInt(q.length);
                this.q.write(q);
                this.q.flush();
            }
        }
        catch (Exception ex) {
            if (!this.g) {
                this.e();
            }
        }
    }
    
    private static cJ q(final int n, final String s, final bH bh, final int w, final int q, final int n2) {
        final cJ cj;
        (cj = new cJ(66305, 1)).q = q;
        cj.w = w;
        cj.q(0, 0, bh.q);
        if (n2 != 0) {
            cj.q(0, 2, n2);
        }
        cj.q(0, 0, s);
        bh.q(cj);
        return cj;
    }
    
    public final void q(final String s, final int n, final int n2, final int n3) {
        final cJ q = q(66305, s, this, n, n2, n3);
        final bk bk;
        if ((bk = (bk)this.y.w(n2)) == null || ((!bk.q(61) || this.q(60)) && !bk.q(56))) {
            ((bI)this).v(q);
        }
    }
    
    public final void q(final String s, final int n) {
        this.q(s, -1, super.i, n);
    }
    
    public abstract void q(final bF p0, final bp p1);
    
    public final void q(final Font font) {
        be.w.q = font.getName();
        be.w.y = font.getStyle();
        be.w.t = font.getSize();
        be.w.q();
        if (this.q != null) {
            this.q.w();
        }
        if (this.q != null) {
            for (int i = 0; i < this.q.q(); ++i) {
                ((bM)this.q.q(i)).w();
            }
        }
    }
    
    public final void q(final boolean h, final boolean b) {
        this.h = h;
        final cq e = this.e;
        try {
            synchronized (this.e) {
                for (int i = 0; i < this.e.q(); ++i) {
                    final bV bv = (bV)this.e.q(i);
                    if (!b && (this.h || bv.i == super.i) && bv.getName() != null && cl.q(bv.getName(), bX.q.getText(), bv.u)) {
                        this.q.q(bv, true);
                    }
                    else {
                        this.q.q(bv);
                    }
                }
            }
            if (b) {
                synchronized (this.q) {
                    final Enumeration<String> keys = this.q.keys();
                    while (keys.hasMoreElements()) {
                        final String s = keys.nextElement();
                        final bV bv2;
                        if ((bv2 = (bV)this.e.w(Integer.parseInt(s))) == null) {
                            this.q.remove(s);
                        }
                        else if (Integer.parseInt(this.q.get(s)) > 0 && (this.h || bv2.i == super.i) && bv2.getName() != null && cl.q(bv2.getName(), bX.q.getText(), bv2.u)) {
                            this.q.q(bv2, true);
                        }
                        else {
                            this.q.q(bv2);
                        }
                    }
                }
            }
        }
        finally {
            final cq e2 = this.e;
        }
    }
    
    public void q(final boolean b) {
    }
    
    public static void y() {
    }
    
    public final void w(final boolean b) {
        this.W = false;
    }
    
    public final void e(final boolean b) {
        this.E = false;
    }
    
    public final void r(final boolean b) {
        this.R = true;
    }
    
    public void o(final int n) {
    }
    
    public abstract boolean q(final cy p0);
    
    public abstract void q(final bp p0);
    
    public abstract int q(final cy p0);
    
    public abstract void p(final int p0);
    
    public abstract void a(final int p0);
    
    public abstract void q(final int p0, final bp p1);
    
    public bH() {
        super(-999, null);
        this.f = true;
        this.r = new cq();
        this.t = new cq();
        new cq();
        this.a = new cq();
        this.s = new cq();
        this.q = new ck();
        this.j = new cq();
        this.k = new cq();
        this.q = new Hashtable();
        this.x = new cq();
        this.l = new cq();
        this.q = new cs();
        this.w = new cs();
        this.W = 7;
        this.E = -1;
        this.b = false;
        this.n = false;
        this.Q = false;
        this.w = new Vector();
        this.R = 1000;
        this.T = 3;
        this.Y = 3;
        this.U = 3;
        this.I = 35;
        this.O = 3;
        this.P = 1000;
        this.A = 3;
        this.S = 3;
        this.t = false;
        this.D = 1000;
        this.q = null;
        this.w = new cq();
        this.e = new cq();
        this.x = false;
        this.l = false;
        this.g = true;
        this.p = new cq();
        this.h = false;
        this.y = false;
        this.u = false;
        this.i = false;
        this.o = false;
        this.W = true;
        this.E = true;
        this.p = false;
        this.R = false;
        this.q = new cq();
        this.w = new cq();
        this.e = new cq();
        this.t = new cq();
        this.y = new cq();
        this.u = new cq();
        this.i = new cq();
        this.o = new cq();
        this.z = new cq();
        this.g = new cq();
        bH.d = new cq();
        this.f = new cq();
        bH.d = new cq();
        this.h = new cq();
        this.q = new Vector();
        this.q = -999L;
        this.j = -999;
        this.k = -999;
        this.l = -999;
        this.j = false;
        this.k = false;
        this.z = -999;
        this.x = 2;
        this.c = -999;
        this.t = -999;
        this.y = -999;
        this.v = 8;
        this.b = 3;
        this.n = 4;
        this.m = 9;
        this.Q = 1;
        this.q = false;
        this.z = false;
        this.c = false;
        this.v = false;
        this.w.addElement(this);
        this.w.addElement(new cc(this));
        this.w.addElement(new cb(this));
    }
    
    public cq i() {
        return this.h;
    }
    
    public final String a_() {
        return this.s;
    }
    
    public final void q(final String s) {
        this.s = s;
    }
    
    public final int a_() {
        return this.c;
    }
    
    public final void a_(final int c) {
        this.c = c;
    }
    
    public final Frame q() {
        return this.q;
    }
    
    public final boolean o() {
        return !this.g;
    }
    
    static {
        final String[] array = { null, null, null, null, null, null, null, null, null };
        final String[] array2 = { "bass.au", "bell.au", "castanet.au", "chime.au", "conga.au", "cowBell.au", "doubleBell.au", "drumRoll.au", "harp.au" };
        bH.d = new cq();
    }
}
