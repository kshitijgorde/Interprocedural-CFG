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

public abstract class cU extends cz implements cM, dJ, Runnable
{
    public boolean y;
    public boolean u;
    public boolean i;
    public boolean o;
    public boolean p;
    private int F;
    private boolean E;
    private boolean R;
    public boolean a;
    private boolean T;
    public boolean s;
    public boolean d;
    public boolean f;
    public boolean g;
    protected DataInputStream q;
    protected DataOutputStream q;
    public static String a;
    protected cK q;
    protected Thread q;
    protected Socket q;
    public dK q;
    protected dW q;
    public dW w;
    public dW e;
    public dW r;
    public dW t;
    public dW y;
    public dW u;
    public dW i;
    public dW o;
    public dW p;
    public dW a;
    private dW c;
    public dW s;
    public dW d;
    public static dW f;
    public dQ q;
    public dW g;
    public dW h;
    public dW j;
    public dW k;
    public dW l;
    public Hashtable q;
    public dW z;
    public dW x;
    public Vector q;
    public long q;
    private long t;
    public long w;
    public long e;
    protected URL q;
    protected boolean h;
    protected boolean j;
    public MediaTracker q;
    protected AudioClip[] q;
    protected dY q;
    protected dY w;
    public Frame q;
    protected int k;
    protected int l;
    public int z;
    private String f;
    public boolean k;
    public String s;
    public boolean l;
    public String d;
    public URL w;
    public URL e;
    public URL r;
    public int x;
    public int c;
    public int v;
    public int b;
    public int n;
    public int m;
    public int Q;
    public int W;
    public long r;
    public int E;
    public boolean z;
    public boolean x;
    public boolean c;
    public boolean v;
    public boolean b;
    public int R;
    public boolean n;
    public boolean m;
    public boolean Q;
    public boolean W;
    protected dV q;
    protected dV w;
    private Vector w;
    public int T;
    public int Y;
    public int U;
    public int I;
    public int O;
    public int P;
    public int A;
    public int S;
    public int D;
    
    public final boolean q() {
        return this.h;
    }
    
    public final DataInputStream q() {
        return this.q;
    }
    
    public final dW q() {
        return this.o;
    }
    
    public final boolean w() {
        return this.i;
    }
    
    public final dW e() {
        return this.e;
    }
    
    public final Hashtable q() {
        return this.q;
    }
    
    public final dW y() {
        return this.q;
    }
    
    public final dW u() {
        return this.g;
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
    
    public final dW r() {
        return this.w;
    }
    
    public final dW t() {
        return this.y;
    }
    
    public final int o() {
        return this.c;
    }
    
    public final dW i() {
        return this.i;
    }
    
    public final boolean y() {
        return this.v;
    }
    
    public final boolean u() {
        return this.b;
    }
    
    public final boolean q(final int n, final boolean b) {
        final es es;
        (es = new es(67587, 1)).q(0, 0, n);
        es.q(0, 1, this.m ? 1 : 0);
        es.q(0, 0, b);
        this.q(es);
        return true;
    }
    
    public final String w(String s) {
        if (s == null || !this.z) {
            return s;
        }
        final dW o = this.o;
        try {
            synchronized (this.o) {
                for (int i = 0; i < this.o.q(); ++i) {
                    s = ((cd)this.o.q(i)).q(s);
                }
            }
        }
        finally {
            final dW o2 = this.o;
        }
        final dW c = this.c;
        try {
            synchronized (this.c) {
                for (int j = 0; j < this.c.q(); ++j) {
                    s = ((cd)this.c.q(j)).q(s);
                }
            }
        }
        finally {
            final dW c2 = this.c;
        }
        return;
    }
    
    public void e(final es es) {
        for (int i = 0; i < es.w(); ++i) {
            final cz cz;
            if ((cz = (cz)this.e.w(es.q(i, 0))) != null) {
                if (this.c) {
                    final String q;
                    if ((q = es.q(i, 2)) != null) {
                        try {
                            this.q(new URL(q), "_blank");
                        }
                        catch (MalformedURLException ex) {}
                    }
                }
                else {
                    new dh(this.q.q(), this, cz, es, i).setVisible(true);
                }
            }
        }
    }
    
    public final String q(String s) {
        if (s != null && this.z) {
            final dW o = this.o;
            try {
                synchronized (this.o) {
                    for (int i = 0; i < this.o.q(); ++i) {
                        s = ((cd)this.o.q(i)).q(s);
                    }
                }
            }
            finally {
                final dW o2 = this.o;
            }
            final dW c = this.c;
            try {
                synchronized (this.c) {
                    for (int j = 0; j < this.c.q(); ++j) {
                        s = ((cd)this.c.q(j)).q(s);
                    }
                }
            }
            finally {
                final dW c2 = this.c;
            }
        }
        return s;
    }
    
    public final String e(String q) {
        if (q != null) {
            final dW z = this.z;
            try {
                synchronized (this.z) {
                    for (int i = 0; i < this.z.q(); ++i) {
                        q = ((ct)this.z.q(i)).q(q);
                    }
                }
            }
            finally {
                final dW z2 = this.z;
            }
        }
        return q;
    }
    
    public final void q(final String f, final String s, final String s2, final boolean b) {
        this.f = f;
        final es es;
        (es = new es(67330, 1)).w = -1;
        es.q = -1;
        es.e = super.r;
        es.q(0, 0, -999);
        es.q(0, 0, f);
        es.q(0, 1, s);
        es.q(0, 0, new ep(s2));
        if (!b) {
            es.q(0, 58);
        }
        this.q(es);
    }
    
    public final void q() {
        new an(this.q.q(), this).setVisible(true);
    }
    
    public final void q(final String f, final String s, final boolean b) {
        this.f = f;
        final es es;
        (es = new es(67330, 1)).w = -1;
        es.q = -1;
        es.e = super.r;
        es.q(0, 0, -999);
        es.q(0, 0, f);
        es.q(0, 1, s);
        if (!b) {
            es.q(0, 58);
        }
        this.q(es);
    }
    
    public final void q(final int n) {
        final db db;
        if ((db = (db)this.y.w(n)) != null) {
            this.q(db);
            return;
        }
        System.err.println("no such room! " + n);
    }
    
    public final void q(final db db) {
        ep ep;
        if (db.e && db.q != null && !this.q(40)) {
            final ad ad;
            (ad = new ad(this.q.q(), this, db)).setVisible(true);
            ep = ad.q();
        }
        else {
            ep = db.q;
        }
        final ep ep2 = ep;
        if (!db.e || db.q == null || this.q(40)) {
            final es es;
            (es = new es(66049, 1)).q(0, 0, super.r);
            es.q(0, 1, db.r);
            es.q(0, 0, ep2);
            es.w = -1;
            es.q = -1;
            this.q(es);
        }
    }
    
    public final boolean i() {
        return this.E;
    }
    
    public final boolean o() {
        return this.R;
    }
    
    public final int q(final int n) {
        int n2 = 0;
        final dW e = this.e;
        try {
            synchronized (this.e) {
                for (int i = 0; i < this.e.q(); ++i) {
                    final dj dj;
                    if ((dj = (dj)this.e.q(i)).o == n && (!dj.q(23) || this.q(24)) && (!dj.q(25) || this.q(32))) {
                        ++n2;
                    }
                }
            }
        }
        finally {
            final dW e2 = this.e;
        }
        System.out.println("Counted users for room:" + this.y.w(n) + " count=" + n2);
        return n2;
    }
    
    public final Image q(String s2, boolean b) {
        b = (boolean)((this.q != null) ? this.q : new MediaTracker(new Button()));
        Image w;
        if ((w = this.w(s, true)) != null) {
            this.q.q(s, w);
            s2 = (String)(this.F++);
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
        for (int q = this.g.q(), i = 0; i < q; ++i) {
            ((M)this.g.q(i)).dispose();
        }
        if (this.q != null) {
            final es es;
            (es = new es(65794, 1)).q(0, 0, super.r);
            es.q(0, 0, this.u);
            es.w = -1;
            es.q = -1;
            this.q(es);
            super.o = -999;
            this.k = -999;
            if (this.q != null) {
                final Frame q2 = this.q.q();
                this.q.q();
                if (q2 != null) {
                    q2.dispose();
                }
            }
        }
        this.h = true;
        this.e();
        this.i = false;
        this.o = false;
        if (this.r != null) {
            this.q(this.r, "_self");
        }
    }
    
    public void q(final cS cs, final dj dj) {
    }
    
    public void q(final cS cs) {
    }
    
    public void q(final byte[] array) {
    }
    
    public void p(final int n) {
    }
    
    public void r(final es es) {
        for (int i = 0; i < es.w(); ++i) {
            final dj dj;
            if ((dj = (dj)this.e.w(es.q(i, 0))) != null) {
                this.q(dj, es.q(i, 1), es.q, es.r);
            }
        }
    }
    
    protected abstract void q(final dj p0, final int p1, final long p2, final int p3);
    
    protected final void t(final es es) {
        final String q;
        if ((q = es.q(0, 0)) != null && q.indexOf("HARDBAN") >= 0) {
            final int int1 = Integer.parseInt(q.substring(q.indexOf("HARDBAN") + "HARDBAN".length()));
            ea.q("setLS", new String[] { "bid", "" + int1 }, null);
            ea.q("setCookie", new String[] { "bID=", "" + int1 }, null);
            eb.q("You have been kicked");
            return;
        }
        this.h = true;
        this.e();
        if (this.e != null) {
            this.q(this.e, "_blank");
            return;
        }
        new b(new Frame(), eb.q("You have been kicked"), q, this).setVisible(true);
    }
    
    protected final void y(final es es) {
        final String[] array = new String[es.w()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = es.q(i, 0);
        }
        new b(this.q.q(), eb.q("Operation not allowed"), array, this).setVisible(true);
    }
    
    public void u(final es es) {
        final int q = es.q(0, 0);
        Label_0184: {
            final int q2;
            switch (q2 = es.q(0, 1)) {
                default: {
                    switch (q) {
                        case 0: {
                            ec.q(eb.q("Your nickname could not be changed because the name \"%1\" is already in use by another user, or is reserved."), new String[] { es.q(0, 0) });
                            break Label_0184;
                        }
                        case 3: {
                            ec.q(eb.q("You may not connect to %1 because the version of the %1 Client (%2) is not compatible with the version of %1 Server (%3)."), new String[] { a.a.e, "1", "2" });
                            break Label_0184;
                        }
                        case 9: {
                            ec.q(eb.q("You may not connect to %1 because the Server license has been exceeded."), new String[] { a.a.e });
                            break Label_0184;
                        }
                        case 10: {
                            ec.q(eb.q("You may not connect to %1 because the Server license has expired."), new String[] { a.a.e });
                            break Label_0184;
                        }
                        default: {
                            es.q(0, 0);
                            break Label_0184;
                        }
                    }
                    break;
                }
                case 65793: {
                    String s = null;
                    switch (q) {
                        case 0: {
                            s = ec.q(eb.q("You may not connect to %1 because another user is already using the name \"%2.\" Please choose another and login again."), new String[] { a.a.e, this.getName() });
                            break;
                        }
                        case 1:
                        case 7: {
                            s = ec.q(eb.q("You may not connect to %1 because your name or password was not entered correctly.  Please re-enter this information and login again."), new String[] { a.a.e });
                            break;
                        }
                        case 2: {
                            s = ec.q(eb.q("You may not connect to %1 because your account has been disabled."), new String[] { a.a.e });
                            break;
                        }
                        case 4: {
                            s = ec.q(eb.q("You may not connect to %1 because the maximum number of simultaneous connections for this site has been reached."), new String[] { a.a.e });
                            break;
                        }
                        case 5: {
                            s = ec.q(eb.q("You may not connect to %1 because you have been banned from this %1 site."), new String[] { a.a.e });
                            break;
                        }
                        case 6: {
                            s = ec.q(eb.q("You may not connect to %1 because no site with the specified ID could be found."), new String[] { a.a.e });
                            break;
                        }
                        case 8: {
                            s = ec.q(eb.q("You are attempting to connect to %1 from an invalid host. Please contact the %1 Site administrator."), new String[] { a.a.e });
                            break;
                        }
                        case 11: {
                            s = ec.q(eb.q("Your name must %1 characters or less.  Please re-enter this information."), new String[] { es.q(0, 0) });
                            break;
                        }
                        case 12: {
                            s = ec.q(eb.q("You may not connect to %1 because the maximum number of simultaneous connections from same ip has been reached."), new String[] { a.a.e });
                            break;
                        }
                        case 13: {
                            s = ec.q(eb.q("You may not connect to %1 because the maximum number of simultaneous connections for this server has been reached."), new String[] { a.a.e });
                            break;
                        }
                        case 14: {
                            s = ec.q(eb.q("Your nickname could not be changed because the name \"%1\" is already in use by another user, or is reserved."), new String[] { es.q(0, 0) });
                            break;
                        }
                        default: {
                            s = es.q(0, 0);
                            break;
                        }
                    }
                    if (s == null) {
                        s = eb.q("Your request could not be fulfilled.(" + q + ", " + q2 + ")");
                    }
                    if (q2 == 65793) {
                        this.h = true;
                        this.e();
                    }
                    new b(this.q, eb.q("Note"), s, this).setVisible(true);
                }
            }
        }
    }
    
    protected final void i(final es es) {
        if (!this.h) {
            String q = es.q(0, 0);
            String q2 = es.q(0, 1);
            if (q == null) {
                q = "Exception somewhere";
            }
            if (q2 == null) {
                q2 = "Unrecognized exception";
            }
            new b((this.q == null) ? null : this.q.q(), eb.q("Alert"), new String[] { q, q2 }, this).setVisible(true);
        }
    }
    
    protected final void o(final es es) {
        for (int i = 0; i < es.w(); ++i) {
            new b((this.q == null) ? null : this.q.q(), es.q(i, 0), es.q(i, 1), this, new Color(es.q(i, 2)), new Color(es.q(i, 3))).setVisible(true);
        }
    }
    
    protected final void p(final es es) {
        new b(this.q.q(), eb.q("Inactivity Timeout"), ec.q(eb.q("You have been disconnected because you have been inactive for the past %1 minutes."), new String[] { String.valueOf(es.q(0, 0)) }), this).setVisible(true);
        this.h = true;
        this.e();
    }
    
    public void a(final es es) {
        final dW i = this.i;
        try {
            for (int j = 0; j < es.w(); ++j) {
                final int q = es.q(j, 0);
                cb cb = (cb)this.i.w(q);
                if (es.q(j, 63)) {
                    if (cb != null) {
                        this.i.w(q);
                    }
                }
                else {
                    if (cb == null) {
                        cb = new cb(q, es.q(j, 0));
                        this.i.q(cb);
                    }
                    else {
                        cb.t = es.q(j, 0);
                    }
                    cb.w = es.q(j, 1);
                    cb.q = es.q(j, 2);
                    cb.r = es.q(j, 1);
                    cb.y(es.q(j, 3));
                    cb.r(es.q(j, 4));
                    cb.t(es.q(j, 5));
                    cb.q(es.q(j));
                }
            }
        }
        finally {
            final dW k = this.i;
        }
    }
    
    protected final void s(final es es) {
        final dW i = this.i;
        try {
            for (int j = 0; j < es.w(); ++j) {
                this.i.w(es.q(j, 0));
            }
        }
        finally {
            final dW k = this.i;
        }
    }
    
    protected final void d(final es es) {
        final long q = es.q(-1);
        if (es.q(-1, 60)) {
            this.z = true;
        }
        dW dw;
        if (es.q(-1, 59)) {
            if ("Admin".equals(cU.a)) {
                this.q = q;
                dw = this.o;
            }
            else {
                dw = this.c;
            }
            this.t = q;
        }
        else {
            dw = this.o;
            this.q = q;
        }
        if (es.q(this.t | this.q, 62) || es.q(this.t | this.q, 61)) {
            this.z = true;
        }
        try {
            for (int i = 0; i < es.w(); ++i) {
                final int q2 = es.q(i, 0);
                cd cd = (cd)dw.w(q2);
                if (es.q(i, 63)) {
                    if (cd != null) {
                        dw.w(q2);
                    }
                }
                else {
                    if (cd == null) {
                        cd = new cd(q2, es.q(i, 0));
                        dw.q(cd);
                    }
                    else {
                        cd.t = es.q(i, 0);
                    }
                    cd.w(es.q(i, 1));
                    cd.y(es.q(i, 1));
                    cd.q(es.q(i));
                }
            }
        }
        finally {}
    }
    
    protected final void f(final es es) {
        final dW o = this.o;
        try {
            for (int i = 0; i < es.w(); ++i) {
                this.o.w(es.q(i, 0));
            }
        }
        finally {
            final dW o2 = this.o;
        }
    }
    
    protected final void g(final es es) {
        final dW z = this.z;
        try {
            for (int i = 0; i < es.w(); ++i) {
                final int q = es.q(i, 0);
                ct ct = (ct)this.z.w(q);
                if (es.q(i, 63)) {
                    if (ct != null) {
                        this.z.w(q);
                    }
                }
                else {
                    if (ct == null) {
                        ct = new ct(q, es.q(i, 0));
                        this.z.q(ct);
                    }
                    else {
                        ct.t = es.q(i, 0);
                    }
                    ct.w(es.q(i, 1));
                    ct.y(es.q(i, 1));
                    ct.t(es.q(i, 2));
                    ct.q(es.q(i));
                }
            }
        }
        finally {
            final dW z2 = this.z;
        }
    }
    
    protected final void h(final es es) {
        final dW z = this.z;
        try {
            for (int i = 0; i < es.w(); ++i) {
                this.z.w(es.q(i, 0));
            }
        }
        finally {
            final dW z2 = this.z;
        }
    }
    
    protected void j(final es es) {
        es.q(0, 0);
    }
    
    protected final void k(final es es) {
        db db = null;
        bZ bz = null;
        final dW y = this.y;
        try {
            for (int i = 0; i < es.w(); ++i) {
                final int q = es.q(i, 0);
                db db2 = (db)this.y.w(q);
                if (es.q(i, 63) || es.q(i, 0) == null) {
                    if (db2 != null) {
                        this.y.w(q);
                        if (this.q != null) {
                            this.q.q(db2);
                        }
                    }
                    if (q == super.o) {
                        bz = db2;
                    }
                }
                else {
                    if (db2 == null) {
                        String q2;
                        if ((q2 = es.q(i, 0)) == null) {
                            System.err.println("null room name received.");
                            q2 = new String("no name");
                        }
                        db2 = new db(q, this.q(q2));
                        this.y.q(db2);
                    }
                    else {
                        db2.t = this.q(es.q(i, 0));
                    }
                    db2.q = es.q(i, 1);
                    db2.w = es.q(i, 2);
                    db2.e = es.q(i, 3);
                    db2.q(es.q(i, 4));
                    db2.y(es.q(i, 5));
                    db2.t(es.q(i, 6));
                    db2.w(es.q(i, 7));
                    db2.p(es.q(i, 8));
                    db2.q = this.q(es.q(i, 1));
                    db2.w = this.q(es.q(i, 2));
                    final ep q3 = db2.q;
                    db2.q = es.q(i, 0);
                    if (db2.q != null && !db2.q.equals(q3)) {
                        db2.e = true;
                    }
                    db2.q(es.q(i));
                    if (es.q(i, 62)) {
                        this.k = q;
                    }
                    if (this.q != null) {
                        this.q.w(db2);
                    }
                    if (this.f != null && db2.getName().equals(this.f)) {
                        this.f = null;
                        (db = db2).e = false;
                    }
                }
            }
            if (a.a.q == 1) {
                if (this.k == -999 && es.w() > 0) {
                    this.k = es.q(0, 0);
                }
            }
            else if (this.k == -999) {
                this.k = es.q(0, 0);
            }
        }
        finally {
            final dW y2 = this.y;
        }
        if (db != null) {
            this.q(db);
            this.q.q(true);
            return;
        }
        if (bz != null) {
            final cr cr = (cr)this.y.w(this.k);
            this.q(this.k);
            new b(this.q.q(), a.a.e, ec.q(eb.q("The room you were in, \"%1,\" has closed.  You have been moved to \"%2.\""), new String[] { this.q(bz.getName()), this.q(cr.getName()) }), this).setVisible(true);
        }
    }
    
    protected final void l(final es es) {
        final dW y = this.y;
        try {
            bZ bz = null;
            for (int i = 0; i < es.w(); ++i) {
                final int q = es.q(i, 0);
                final db db;
                if ((db = (db)this.y.w(q)) != null) {
                    this.y.w(q);
                    if (this.q != null) {
                        this.q.q(db);
                    }
                }
                if (q == super.o) {
                    bz = db;
                }
            }
            if (bz != null) {
                final cr cr = (cr)this.y.w(this.k);
                this.q(this.k);
                new b(this.q.q(), a.a.e, ec.q(eb.q("The room you were in, \"%1,\" has closed.  You have been moved to \"%2.\""), new String[] { this.q(bz.getName()), this.q(cr.getName()) }), this).setVisible(true);
            }
        }
        finally {
            final dW y2 = this.y;
        }
    }
    
    protected final void z(final es es) {
        final dW a = this.a;
        try {
            cf cf = null;
            cf cf2 = null;
            for (int i = 0; i < es.w(); ++i) {
                int n = es.q(i, 0);
                cf cf3 = (cf)this.a.w(n);
                if (es.q(i, 63)) {
                    this.a.w(n);
                }
                else {
                    if (cf3 == null) {
                        cf3 = new cf(n, es.q(i, 0));
                        n = this.a.q(cf3, n);
                        es.q(i, 0, n);
                    }
                    else {
                        cf3.t = es.q(i, 0);
                    }
                    cf3.q = new Color(es.q(i, 1));
                    cf3.w = new Color(es.q(i, 2));
                    cf3.e = new Color(es.q(i, 3));
                    cf3.r = new Color(es.q(i, 4));
                    cf3.u = new Color(es.q(i, 5));
                    cf3.i = new Color(es.q(i, 6));
                    cf3.o = new Color(es.q(i, 7));
                    cf3.p = new Color(es.q(i, 8));
                    cf3.a = new Color(es.q(i, 9));
                    cf3.s = new Color(es.q(i, 10));
                    cf3.d = new Color(es.q(i, 11));
                    cf3.q = es.q(i, 1);
                    cf3.w = es.q(i, 12);
                    cf3.q = es.q(i, 13);
                    cf3.q();
                    cf3.q(es.q(i));
                    cf3.w(es.q(i, 2));
                    cf3.e = es.q(i, 14);
                    cf3.t = new Color(es.q(i, 15));
                    cf3.y = new Color(es.q(i, 16));
                    cf3.h = new Color(es.q(i, 17));
                    cf3.j = new Color(es.q(i, 18));
                    cf3.k = new Color(es.q(i, 19));
                    cf3.l = new Color(es.q(i, 20));
                    cf3.z = new Color(es.q(i, 21));
                    cf3.x = new Color(es.q(i, 22));
                    cf3.c = new Color(es.q(i, 23));
                    cf3.v = new Color(es.q(i, 24));
                    cf3.b = new Color(es.q(i, 25));
                    cf3.n = new Color(es.q(i, 26));
                    cf3.w = es.q(i, 3);
                    cf3.o = es.q(i, 27);
                    cf3.p = es.q(i, 28);
                    cf3.m = new Color(es.q(i, 29));
                    cf3.Q = new Color(es.q(i, 30));
                    final int n2;
                    if ((n2 = (es.q(i, 31) & 0xFFFFFF)) != 0) {
                        cf3.f = new Color(n2);
                    }
                    cf3.a = Math.max(es.q(i, 32), 100);
                    final int n3;
                    if ((n3 = (es.q(i, 33) & 0xFFFFFF)) != 0) {
                        cf3.g = new Color(n3);
                    }
                    cf3.q(es.q(i, 34));
                    cf3.w(es.q(i, 35));
                    if (cf3.q(62)) {
                        cf = (cf)cf3.clone();
                    }
                    if (n == AppletAbstract.q().e) {
                        cf2 = (cf)cf3.clone();
                    }
                }
            }
            cf q = a.cf.q;
            if (cf2 != null) {
                q = cf2;
            }
            else if (cf != null) {
                q = cf;
            }
            if (this.q != null) {
                this.q.w();
            }
            if (this.q != null) {
                for (int j = 0; j < this.q.q(); ++j) {
                    ((cZ)this.q.q(j)).e();
                }
            }
            a.cf.w.q(q);
        }
        finally {
            final dW a2 = this.a;
        }
    }
    
    protected final void x(final es es) {
        final dW a = this.a;
        try {
            for (int i = 0; i < es.w(); ++i) {
                this.a.w(es.q(i, 0));
            }
        }
        finally {
            final dW a2 = this.a;
        }
    }
    
    public final synchronized void w(final es es) {
        if (es.q() == 67341) {
            this.q.q(es);
        }
        else if (es.q() == 4198416) {
            this.q.q(es);
        }
        else if (es.q() == 4198496) {
            if (this.q.q() > 1) {
                this.q.q(2, es);
            }
            else {
                this.q.w(es);
            }
        }
        else if (es.q() == 4202544) {
            if (this.q.q() > 2) {
                this.q.q(3, es);
            }
            else {
                this.q.w(es);
            }
        }
        else if (es.q() == 67584) {
            if (this.m) {
                this.q.q(1, es);
            }
            else if (this.q.q() > 3) {
                final es es2 = (es)this.q.q(3).q;
                int n = 3;
                if (es2.q() == 4202544) {
                    n = 4;
                }
                if (this.q.q() > n - 1) {
                    this.q.q(n, es);
                }
            }
            else {
                this.q.w(es);
            }
            this.n = true;
        }
        else if (es.q() == 67585 && es.q(0, 1) == 65793) {
            this.q.q(es);
            this.n = true;
        }
        else {
            this.q.w(es);
        }
        synchronized (this.q) {
            this.q.notifyAll();
        }
    }
    
    public void t() {
        this.w();
    }
    
    public abstract void y();
    
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
                    final es es = (es)this.q.q();
                    for (int i = 0; i < this.w.size(); ++i) {
                        ((dJ)this.w.elementAt(i)).c(es);
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
            if (this.h) {
                this.e();
            }
        }
        System.out.println("MUB stoped:" + this.h);
    }
    
    public final void q(final es es) {
        try {
            if (this.q != null) {
                final byte[] q = ev.q(new er(es).w());
                this.q.writeInt(q.length);
                this.q.write(q);
                this.q.flush();
            }
        }
        catch (Exception ex) {
            if (!this.h) {
                this.e();
            }
        }
    }
    
    private static es q(final int n, final String s, final cU cu, final int w, final int q, final int n2) {
        final es es;
        (es = new es(66305, 1)).q = q;
        es.w = w;
        es.q(0, 0, cu.r);
        if (n2 != 0) {
            es.q(0, 2, n2);
        }
        es.q(0, 0, s);
        cu.q(es);
        return es;
    }
    
    public final void q(final String s, final int n, final int n2, final int n3) {
        final es q = q(66305, s, this, n, n2, n3);
        final cr cr;
        if ((cr = (cr)this.y.w(n2)) == null || ((!cr.q(61) || this.q(60)) && !cr.q(56))) {
            ((cV)this).W(q);
        }
    }
    
    public final void q(final String s, final int n) {
        this.q(s, -1, super.o, n);
    }
    
    public abstract void q(final cS p0, final cz p1);
    
    public final void q(final Font font) {
        cf.w.q = font.getName();
        cf.w.w = font.getStyle();
        cf.w.q = font.getSize();
        cf.w.q();
        if (this.q != null) {
            this.q.w();
        }
        if (this.q != null) {
            for (int i = 0; i < this.q.q(); ++i) {
                ((cZ)this.q.q(i)).e();
            }
        }
    }
    
    public final void q(final boolean j, final boolean b) {
        this.j = j;
        final dW e = this.e;
        try {
            synchronized (this.e) {
                for (int i = 0; i < this.e.q(); ++i) {
                    final dj dj = (dj)this.e.q(i);
                    if (!b && (this.j || dj.o == super.o) && dj.getName() != null && dV.q(dj.getName(), dl.q.getText(), dj.i)) {
                        this.q.q(dj, true);
                    }
                    else {
                        this.q.q(dj);
                    }
                }
            }
            if (b) {
                synchronized (this.q) {
                    final Enumeration<String> keys = this.q.keys();
                    while (keys.hasMoreElements()) {
                        final String s = keys.nextElement();
                        final dj dj2;
                        if ((dj2 = (dj)this.e.w(Integer.parseInt(s))) == null) {
                            this.q.remove(s);
                        }
                        else if (Integer.parseInt(this.q.get(s)) > 0 && (this.j || dj2.o == super.o) && dj2.getName() != null && dV.q(dj2.getName(), dl.q.getText(), dj2.i)) {
                            this.q.q(dj2, true);
                        }
                        else {
                            this.q.q(dj2);
                        }
                    }
                }
            }
        }
        finally {
            final dW e2 = this.e;
        }
    }
    
    public void q(final boolean b) {
    }
    
    public void u() {
    }
    
    public final void w(final boolean b) {
        this.E = false;
    }
    
    public final void e(final boolean b) {
        this.R = false;
    }
    
    public void a(final int n) {
    }
    
    public abstract boolean q(final eg p0);
    
    public abstract void q(final cz p0);
    
    public abstract int q(final eg p0);
    
    public abstract void s(final int p0);
    
    public abstract void d(final int p0);
    
    public abstract void q(final int p0, final cz p1);
    
    public cU() {
        super(-999, null);
        this.g = true;
        this.r = new dW();
        this.t = new dW();
        this.p = new dW();
        this.s = new dW();
        this.d = new dW();
        this.q = new dQ();
        this.k = new dW();
        this.l = new dW();
        this.q = new Hashtable();
        this.z = new dW();
        this.x = new dW();
        this.e = 165L;
        this.q = new dY();
        this.w = new dY();
        this.E = 7;
        this.R = -1;
        this.n = false;
        this.m = false;
        this.Q = false;
        this.W = false;
        this.w = new Vector();
        this.T = 1000;
        this.Y = 3;
        this.U = 3;
        this.I = 3;
        this.O = 35;
        this.P = 3;
        this.A = 1000;
        this.S = 3;
        this.D = 3;
        this.y = false;
        this.F = 1000;
        this.q = null;
        this.w = new dW();
        this.e = new dW();
        this.c = false;
        this.z = false;
        this.h = true;
        this.a = new dW();
        this.j = false;
        this.u = false;
        this.i = false;
        this.o = false;
        this.p = false;
        this.E = true;
        this.R = true;
        this.a = false;
        this.T = false;
        this.q = new dW();
        this.w = new dW();
        this.e = new dW();
        this.t = new dW();
        this.y = new dW();
        this.u = new dW();
        this.i = new dW();
        this.o = new dW();
        this.c = new dW();
        this.h = new dW();
        cU.f = new dW();
        this.g = new dW();
        cU.f = new dW();
        this.j = new dW();
        this.q = new Vector();
        this.q = -999L;
        this.k = -999;
        this.l = -999;
        this.z = -999;
        this.k = false;
        this.l = false;
        this.x = -999;
        this.c = 2;
        this.v = -999;
        this.q = -999;
        this.w = -999;
        this.b = 8;
        this.n = 3;
        this.m = 4;
        this.Q = 9;
        this.W = 1;
        this.w = false;
        this.x = false;
        this.v = false;
        this.b = false;
        this.w.addElement(this);
        this.w.addElement(new dI(this));
        this.w.addElement(new dH(this));
    }
    
    public dW w() {
        return this.j;
    }
    
    public final String a_() {
        return this.d;
    }
    
    public final void q(final String d) {
        this.d = d;
    }
    
    public final int a_() {
        return this.v;
    }
    
    public final void a_(final int v) {
        this.v = v;
    }
    
    public final Frame q() {
        return this.q;
    }
    
    public final boolean p() {
        return !this.h;
    }
    
    static {
        final String[] array = { null, null, null, null, null, null, null, null, null };
        final String[] array2 = { "bass.au", "bell.au", "castanet.au", "chime.au", "conga.au", "cowBell.au", "doubleBell.au", "drumRoll.au", "harp.au" };
        cU.f = new dW();
    }
}
