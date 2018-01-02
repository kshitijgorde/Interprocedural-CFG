// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Random;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.StreamCorruptedException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;
import com.spilka.client.muc.ChatNames;
import java.awt.Component;
import java.net.NoRouteToHostException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.io.IOException;
import java.net.URL;
import java.awt.Image;
import java.awt.Frame;
import java.util.Enumeration;
import java.awt.Dialog;
import java.util.Hashtable;

public abstract class ap extends dH
{
    private dW q;
    private Hashtable w;
    private Hashtable e;
    private bG q;
    
    public void q() {
        this.p.q();
        this.g.q();
        this.h.q();
        this.j.q();
        this.k.q();
        ap.x.q();
        this.v.q();
        this.b.q();
        this.n.q();
        this.d.q();
        this.q.w();
        this.m = false;
        if (this.q != null) {
            this.q.interrupt();
        }
        this.q = null;
    }
    
    public void w() {
        this.s = false;
        this.W = false;
        this.f = false;
        this.d = false;
        this.a.q();
        this.f.q();
        this.L = this.S;
        ap.f = "";
        if (super.o != null) {
            for (int i = 0; i < super.o.q; ++i) {
                ((bm)super.o.q(i)).dispose();
            }
        }
        if (super.c != null) {
            for (int j = 0; j < super.c.q; ++j) {
                ((bz)super.c.q(j)).dispose();
            }
        }
        final Enumeration<Dialog> elements = this.e.elements();
        while (elements.hasMoreElements()) {
            final Dialog nextElement;
            if ((nextElement = elements.nextElement()) instanceof Dialog) {
                nextElement.dispose();
            }
        }
        final Enumeration<dB> elements2 = this.w.elements();
        while (elements2.hasMoreElements()) {
            final dB nextElement2;
            if ((nextElement2 = elements2.nextElement()) instanceof dB) {
                nextElement2.t();
            }
        }
        this.t();
        if (super.q != null) {
            final Frame q = super.q.q();
            super.q.q();
            super.q = null;
            if (this.q != null) {
                this.q.setVisible(false);
            }
            this.q = null;
            if (q != null) {
                q.dispose();
            }
        }
        this.w.q();
        if (this.q != null) {
            this.q.interrupt();
        }
        this.q = null;
        try {
            Thread.sleep(500L);
        }
        catch (InterruptedException ex) {}
        if (super.q != null) {
            try {
                super.eis.close();
                super.eos.close();
                super.q.close();
            }
            catch (Exception ex2) {}
        }
        super.n = false;
        this.q.q();
        final ax q2;
        if (dN.q != null && (q2 = dN.q).q != null) {
            q2.q.stop();
            q2.q = null;
        }
        super.h = true;
        super.q = null;
        super.eos = null;
        super.eis = null;
    }
    
    public void q(dI di) {
        switch (di.q()) {
            case 67585: {
                this.f(di);
            }
            case 65794: {
                final dI di2 = di;
                final ap ap = this;
                this.r();
                for (int i = 0; i < di2.w(); ++i) {
                    final aO ao;
                    if ((ao = (aO)ap.a.w(di2.q(i, 0))) != null) {
                        final String q = a.B.q(be.w("(This user has left %1)"), new String[] { dN.e });
                        final aZ az = (aZ)ap.p.w(ao.e);
                        final cj cj = (cj)ap.f.w(ao.r);
                        final eh eh;
                        (eh = new eh(ao.s, ao.a)).y = false;
                        if (cj != null) {
                            if (ao.r == ap.r && !cj.q(57) && (!ao.q(23) || ap.q(24)) && (!ao.q(25) || ap.q(32))) {
                                final String q2;
                                String string;
                                if ((q2 = di2.q(i, 0)) != null) {
                                    string = ap.q(q2) + " " + q;
                                }
                                else {
                                    string = q;
                                }
                                final A a = new A(string, ao, false, false, az, ap);
                                a.q(di2.q, 0);
                                final int j = ap.J;
                                if (ap.q != null) {
                                    ap.q.q(a);
                                }
                            }
                            if ((!ao.q(23) || ap.q(24)) && (!ao.q(25) || ap.q(32))) {
                                ao.r = -999;
                                cj.w();
                            }
                            if (ap.q != null) {
                                ap.q.w(cj);
                            }
                        }
                        if (ap.q != null) {
                            ap.q.q(ao);
                            ap.q.q(eh, false);
                        }
                        final cJ cj2;
                        if ((cj2 = (cJ)ap.o.w(ao.s)) != null) {
                            if (ao.s == ap.s) {
                                cj2.dispose();
                            }
                            else {
                                final A a2 = new A(q, ao, false, false, az, ap);
                                a2.q(di2.q, 0);
                                cj2.q(a2);
                            }
                        }
                        try {
                            ap.a.w(ao);
                        }
                        finally {}
                    }
                }
            }
            case 66049: {
                this.g(di);
            }
            case 66305: {
                this.u(di);
            }
            case 66308: {
                this.A(di);
            }
            case 66306: {
                this.y(di);
            }
            case 66307: {
                this.l(di);
            }
            case 66561: {
                this.k(di);
            }
            case 66816:
            case 50400771: {
                this.h(di);
            }
            case 66817: {
                this.x(di);
            }
            case 67073: {
                this.s(di);
            }
            case 67074: {
                final p p;
                if ((p = (p)super.a.w(di.q(0, 0))) != null) {
                    final dI di3;
                    (di3 = new dI(67073, 1)).w = p.s;
                    di3.q(0, 0, super.s);
                    this.o(di3);
                }
            }
            case 67329: {
                this.S(di);
            }
            case 67330:
            case 17236481: {
                this.Q(di);
            }
            case 17236482: {
                this.W(di);
            }
            case 67331:
            case 17236737: {
                this.i(di);
            }
            case 17236738: {
                this.G(di);
            }
            case 67332:
            case 17236993: {
                this.a(di);
            }
            case 17236994: {
                this.c(di);
            }
            case 67333:
            case 17237265: {
                this.v(di);
            }
            case 17237266: {
                this.b(di);
            }
            case 17237249:
            case 275795985: {
                this.n(di);
            }
            case 275795986: {
                this.m(di);
            }
            case 67334: {
                this.C(di);
            }
            case 17237506: {
                this.q(this.q.q(), this.q.w());
            }
            case 17237505: {
                this.X(di);
            }
            case 67584: {
                final long currentTimeMillis = System.currentTimeMillis();
                this.r(di);
                System.out.println("ex time=" + (System.currentTimeMillis() - currentTimeMillis));
            }
            case 67586: {
                this.t(di);
            }
            case 67843: {
                this.e(di);
            }
            case 68608: {
                this.j(di);
            }
            case 67338: {
                this.d(di);
            }
            case 67341:
            case 17239297: {
                this.E(di);
            }
            case 17239298: {
                this.R(di);
            }
            case 67844: {
                this.w(di);
            }
            case 33621775:
            case 537948401: {
                this.D(di);
            }
            case 537948402: {
                this.F(di);
            }
            case 50400768: {
                final dI di4 = di;
                this.w.put(new Integer(di4.q(0, 1)), new di((p)super.a.w(di4.q(0, 0)), di4.q(0, 0), di4.q(0, 1), di4.q(0, 1)));
            }
            case 50400769: {
                final dI di5 = di;
                synchronized (this.w) {
                    final Object remove;
                    if ((remove = this.w.remove(new Integer(di5.q(0, 1)))) instanceof dB) {
                        ((dB)remove).t();
                    }
                    break;
                }
            }
            case 50400770: {
                final dB db;
                if ((db = this.w.get(new Integer(di.q(0, 1)))) != null) {
                    db.y();
                }
            }
            case 4198416: {
                final dI di6 = di;
                dN.u = di6.q(0, 63);
                di6.q(0, 62);
                dN.w = di6.q(0, 61);
                dN.q = di6.q(0, 60);
                dN.y = di6.q(0, 59);
                dN.t = di6.q(0, 58);
                dN.e = di6.q(0, 54);
                dN.r = di6.q(0, 53);
                dN.r = di6.q(0, 53);
                di6.q(0, 57);
                dN.i = di6.q(0, 52);
                this.q = null;
            }
            case 4198464: {
                final dI di7 = di;
                new s(new Frame(), di7.q(0, 0), di7.q(0, 1), this).setVisible(true);
            }
            case 4198496: {
                final String q3 = (di = di).q(0, 0);
                di.q(0, 1);
                dz.q(di.q(0, 2));
                dN.r = di.q(0, 3);
                dz.w(di.q(0, 4));
                dz.e(di.q(0, 5));
                ap.f = di.q(0, 6);
                final dE q4;
                if ((q4 = a.m.q().q).q != null) {
                    q4.q = q4.q.getImage(q4.q.getCodeBase(), "Resources/" + dH.f + "/" + q4.q);
                }
                if (!"".equals(q3)) {
                    dN.e = q3;
                }
                di.q(0, 0);
            }
            case 16974593: {
                dH.z(di);
            }
            case 4198465: {
                this.P(di);
            }
            case 4198466:
            case 1074807297: {
                I(di);
            }
            case 1074807298: {
                O(di);
            }
            case 4198512:
            case 1074819073: {
                this.L(di);
            }
            case 1074819074: {
                this.Z(di);
            }
            case 33621773:
            case 537948369: {
                this.H(di);
            }
            case 537948370: {
                this.J(di);
            }
            case 4198513: {
                this.K(di);
            }
            case 4198528: {
                this.T(di);
            }
            case 4198529: {
                this.Y(di);
            }
            case 4202544: {
                this.U(di);
                break;
            }
        }
    }
    
    private void T(final dI di) {
        final ax q;
        (q = dN.q).q();
        q.q(0);
        try {
            for (int i = 0; i < di.w(); ++i) {
                final int q2 = di.q(i, 0);
                final String q3 = di.q(i, 0);
                aR ar = (aR)super.n.w(q2);
                if (di.q(i, 63)) {
                    if (ar != null) {
                        super.n.w(q2);
                    }
                }
                else {
                    if (ar == null) {
                        ar = new aR(q2, q3);
                    }
                    else {
                        ar.a = q3;
                    }
                    ar.q(di.q(i));
                    super.n.q(ar);
                    ar.q = di.q(i, 1);
                    ar.p(di.q(i, 2));
                    ar.o(di.q(i, 3));
                    ar.e = di.q(i, 4);
                    ar.w = di.q(i, 5);
                    ar.q = di.q(i, 1);
                    if (ar.q == null || ar.q.trim().equals("")) {
                        ar.p(bC.w.b.getRGB());
                        ar.o(bC.w.n.getRGB());
                        ar.e = bC.w.t;
                        ar.w = bC.w.r;
                        ar.q = bC.w.w;
                    }
                    dN.q.q = ar.q(4);
                    final ax q4 = dN.q;
                    int q5 = ar.q;
                    final ax ax = q4;
                    if (q5 == 0) {
                        q5 = 20;
                    }
                    ax.q = q5;
                    ax.w = ax.q;
                    final String s;
                    if (((s = q3).length() <= 3 || !s.substring(0, 4).equals("rss:")) && ar.q(0)) {
                        final aR ar2;
                        dN.q.q(ar2.a, ar2 = ar);
                    }
                }
            }
        }
        finally {}
        dN.q.w();
    }
    
    private void Y(final dI di) {
        if (di.w() == 0) {
            return;
        }
        for (int i = 0; i < di.w(); ++i) {
            final String q = di.q(i, 0);
            final int q2 = di.q(i, 0);
            final aR ar;
            if ((ar = (aR)this.n.w(q2)) != null) {
                dN.q.q(q, ar);
            }
            else {
                System.out.println("Can't find sc id = " + q2);
            }
        }
        dN.q.w();
    }
    
    private void U(final dI di) {
        int n = 1;
        for (int i = 0; i < di.w(); ++i) {
            this.m.q(new bN(n, di.q(i, 0)));
            ++n;
        }
    }
    
    private static void I(final dI di) {
        try {
            for (int i = 0; i < di.w(); ++i) {
                final int q = di.q(i, 0);
                aJ aj = (aJ)ap.x.w(q);
                if (di.q(i, 63)) {
                    if (aj != null) {
                        ap.x.w(q);
                    }
                }
                else {
                    if (aj == null) {
                        aj = new aJ(q, di.q(i, 0));
                        ap.x.q(aj);
                    }
                    else {
                        aj.a = di.q(i, 0);
                    }
                    aj.q(di.q(i));
                    aj.p(di.q(i, 1));
                }
            }
        }
        finally {}
    }
    
    private static void O(final dI di) {
        try {
            for (int i = 0; i < di.w(); ++i) {
                ap.x.w(di.q(i, 0));
            }
        }
        finally {}
    }
    
    public static M q(final int n) {
        final M m = new M();
        for (int i = 0; i < ap.x.q; ++i) {
            final aJ aj;
            if ((aj = (aJ)ap.x.q(i)).q(n)) {
                m.q(aj);
            }
        }
        return m;
    }
    
    private void P(final dI di) {
        for (int i = 0; i < di.w(); ++i) {
            aO ao;
            if ((ao = (aO)this.a.w(di.q(i, 0))) == null) {
                ao = new aO(di.q(i, 0), di.q(i, 0));
            }
            ao.p(di.q(i, 1));
            this.a.q(ao);
            super.q.q(ao, false);
        }
    }
    
    public void w(final dI di) {
        this.X = di.q(0, 4);
        this.B = di.q(0, 5);
        this.M = di.q(0, 6);
        this.aa = di.q(0, 7);
        this.ab = di.q(0, 9);
        this.N = di.q(0, 10);
    }
    
    public void e(final dI di) {
        this.p = di.q(0);
        this.K = di.q(0, 1);
        dN.q = di.q(0, 2);
        if (this.q != null && ((bT)this.q).getParent() != null && ((aP)((bT)this.q).getParent()).q != null) {
            ((aP)((bT)this.q).getParent()).q.u();
        }
    }
    
    public final void q(final String s, final int n, final int n2) {
        final dI di;
        (di = new dI(66310, 1)).q(0, 0, n);
        di.q(0, 1, n2);
        di.q(0, 2, super.s);
        di.q(0, 0, s);
        this.o(di);
    }
    
    public final void q(final String s, final int n, final int n2, final int n3) {
        final dI di;
        (di = new dI(66310, 1)).q(0, 0, n);
        di.q(0, 1, n2);
        di.q(0, 2, super.s);
        if (n3 != 0) {
            di.q(0, 3, n3);
        }
        di.q(0, 0, s);
        this.o(di);
    }
    
    public final boolean r() {
        return super.a;
    }
    
    public final Image q(final String s, final boolean b) {
        return this.e(s, b);
    }
    
    public final Image w(final String s, final boolean b) {
        try {
            final URL q = super.q;
            if (b) {
                final URL url = new URL(q, "Resources/" + ap.f + "/" + s);
            }
            else {
                final URL url = new URL(q, "Resources/" + s);
            }
            Image image;
            if (b) {
                image = this.q.q(s);
            }
            else {
                image = this.w.q(s);
            }
            URL url;
            return (image != null) ? image : this.q(url);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public final Image e(final String s, final boolean b) {
        final Image w;
        if ((w = this.w(s, b)) != null && this.q != null) {
            if (b) {
                this.q.q(s, w);
            }
            else {
                this.w.q(s, w);
            }
            synchronized (this.w) {
                this.w.w(w);
                this.w.notifyAll();
            }
        }
        return w;
    }
    
    public abstract Image q(final URL p0);
    
    public void q(final String a, final String s, final dD dd, URL url, final int n, final String s2) {
        try {
            super.j = false;
            String s3;
            if (a.m.q().w != null) {
                s3 = a.m.q().w;
            }
            else {
                s3 = q.getHost();
            }
            if (s3 == null || s3.trim().length() <= 0) {
                s3 = s2;
            }
            super.q = q;
            super.a = a;
            Label_0098: {
                if (cK.e < 66048) {
                    if (cK.e) {
                        break Label_0098;
                    }
                }
                try {
                    super.q = null;
                }
                catch (Throwable t) {
                    super.q = null;
                }
            }
            if (!(url = (URL)(this.q != null && this.eis != null && this.eos != null))) {
                for (int n2 = 0; url == 0 && n2 < cl.q.size(); ++n2) {
                    final int q2 = cl.q(n2);
                    try {
                        if (q2 != a.m.q().r) {
                            try {
                                this.w(s3, q2);
                                this.q(n, false);
                                url = (URL)1;
                            }
                            catch (IOException ex8) {
                                url = (URL)0;
                            }
                        }
                    }
                    catch (Exception ex) {
                        if (n2 == cl.q.size() - 1) {
                            throw ex;
                        }
                    }
                }
            }
            if (url != 0) {
                this.q.setSoTimeout(0);
                if (super.q != null && super.q.isAlive()) {
                    super.q.interrupt();
                    super.q = null;
                }
                if (this.q.q() || !this.m) {
                    this.q.q();
                }
                super.q = new Thread(this, "Decoder");
                super.h = false;
                super.q.start();
                if (super.q == null || this.m || this.q.q) {
                    (super.q = new i((cs)this)).start();
                }
                if (this.q == null) {
                    (this.q = new bG(this)).start();
                }
                if (this.w == null) {
                    this.w = new bi("global");
                }
                if (this.q == null || this.L != n) {
                    this.q = new bi("" + n);
                }
                final dI di;
                (di = new dI(65793, 1)).q(0, 0, n);
                di.q(0, 1, super.O);
                di.q(0, 0, a);
                di.q(0, 1, "en");
                di.q(0, 2, s2);
                di.q(0, 3, s);
                di.q(0, 4, (String)cm.q(new Object[] { "location.href" }, null));
                di.q(0, 0, dd);
                if (!this.i() && super.O != -999) {
                    di.q(0, 2);
                }
                if (this.q(23)) {
                    di.q(0, 23);
                }
                if (this.q(25)) {
                    di.q(0, 25);
                }
                if (this.p()) {
                    di.q(0, 3);
                }
                if (super.p && (!cK.w || cK.w != 1)) {
                    di.q(0, 21);
                    di.q(0, 20);
                }
                this.o(di);
            }
            else {
                this.t();
                new dd(super.q, "Error", a.B.q(be.w("No response was received from the server.  The %1 Server may not be running, or may not be a compatible version. Please contact the administrator in charge of the site."), new String[] { dN.e }), this).setVisible(true);
            }
        }
        catch (UnknownHostException ex2) {
            this.t();
            new dd(super.q, a.B.q(be.w("%1 could not locate the specified host. Please make sure you are using the correct host name."), new String[] { dN.e }), ex2, this).setVisible(true);
        }
        catch (InterruptedIOException ex3) {
            this.t();
            new dd(super.q, a.B.q(be.w("No response was received from the server.  The %1 Server may not be running, or may not be a compatible version. Please contact the administrator in charge of the site."), new String[] { dN.e }), ex3, this).setVisible(true);
        }
        catch (NoRouteToHostException ex4) {
            this.t();
            new dd(super.q, a.B.q(be.w("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { dN.e }), ex4, this).setVisible(true);
        }
        catch (SecurityException ex5) {
            this.t();
            new dd(super.q, a.B.q(be.w("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { dN.e }), ex5, this).setVisible(true);
        }
        catch (IOException ex6) {
            this.t();
            new dd(super.q, a.B.q(be.w("%1 could not connect to the specified host.  Please verify that the %1 Server is running and that you are using the correct host name."), new String[] { dN.e }), ex6, this).setVisible(true);
        }
        catch (Exception ex7) {
            this.t();
            new dd(super.q, a.B.q(be.w("An unknown error occurred while connecting to the %1 Server.  Please contact the administrator in charge of the site."), new String[] { dN.e }), ex7, this).setVisible(true);
        }
        super.i = false;
        super.o = false;
    }
    
    public void q(final URL url, final String s) {
    }
    
    public final void e(final int n) {
    }
    
    private void q(final String s, final p p7, final int n, final int n2, final boolean b, final long n3, final int n4) {
        this.q(s, p7, n, b, false, n3, n4, null);
    }
    
    private void q(final String s, final p p8, final int n, final boolean b, final boolean b2, final long n2, final int n3, final byte[] q) {
        if (super.q != null) {
            int s2;
            if ((s2 = p8.s) == super.s) {
                s2 = n;
            }
            final A a;
            (a = new A(this.w(s), p8, n != -1 && n != -3 && n != -2 && !b, false, (aZ)super.p.w(p8.e), this)).y = (n == -3 || n == -2);
            a.q(n2, n3);
            a.p = p8.f;
            if (b2) {
                a.u = q[0];
            }
            else {
                a.q = q;
            }
            if (a.r) {
                final cJ cj;
                if ((cj = (cJ)super.o.w(s2)) != null) {
                    cj.q(a);
                }
                else {
                    if (!super.l) {
                        super.q.q(a);
                        return;
                    }
                    this.q(a, p8);
                }
            }
            else {
                a.w = p8.w;
                if (n == -3 || n == -2) {
                    this.q(a);
                    return;
                }
                super.q.q(a);
            }
        }
    }
    
    public void r(final dI di) {
        this.m = true;
        super.s = di.w;
        this.q(di.q(0));
        ap.f = di.q(0, 0);
        if (dN.q == null) {
            dN.q = this.w("icon_display.gif", false);
        }
        if (!"Admin".equals(ap.f)) {
            if (dN.r == null) {
                dN.r = this.w(bC.w.r() + "background.gif", true);
            }
            if (dN.t == null) {
                dN.t = this.w(bC.w.r() + "chatbackground.gif", true);
            }
            if (a.m.q().q) {
                (super.q = new bT(this, null)).setVisible(false);
                a.m.q().q.setVisible(false);
                a.m.q().add(super.q.q());
                ((bT)super.q).validate();
                return;
            }
            if (dN.w()) {
                final long currentTimeMillis = System.currentTimeMillis();
                final aP ap = new aP(this, bj.q(this));
                System.out.println("Load window complete in " + (System.currentTimeMillis() - currentTimeMillis));
                super.q = ap.q;
                this.q = this.q.q();
            }
        }
    }
    
    protected void t(final dI di) {
        super.i = true;
        super.o = true;
        super.r = -999;
        if (super.p.w(super.P) == null) {
            this.f(super.I);
        }
        else {
            this.f(super.P);
        }
        final cj cj = (cj)super.f.w(super.O);
        cj cj2 = (cj)super.f.w(super.U);
        if (cj == null || (cj.q == null && cj2.q != null)) {
            if (cj2 == null) {
                cj2 = (cj)super.f.q(0);
            }
            this.q(cj2);
            return;
        }
        this.q(cj);
    }
    
    private void r() {
        if (!super.i && !super.o) {
            if (super.q != null) {
                super.q.q().q();
            }
            int r;
            if (super.f.w(super.O) == null) {
                r = super.U;
            }
            else {
                r = super.O;
            }
            super.o = true;
            final cj cj;
            if ((cj = (cj)super.f.w(r)) != null && cj.w && cj.q != null && !this.q(40)) {
                super.r = -999;
                new ca(super.q.q(), this, cj).setVisible(true);
            }
            super.r = r;
        }
    }
    
    protected final void q(final aO ao, int i, final long n, final int n2) {
        final int r = ao.r;
        final cj cj = (cj)super.f.w(i);
        final cj cj2 = (cj)super.f.w(ao.r);
        this.r();
        if (ao.s == super.s) {
            if (cj2 != null) {
                cj2.q = false;
            }
            cj.q = true;
            this.r = i;
            if (super.q != null) {
                if (!super.j) {
                    this.q(false, false);
                }
                super.q.q(cj);
                if (!super.q.isVisible()) {
                    super.q.setVisible(true);
                }
                super.q.q().validate();
            }
        }
        if (cj != null) {
            if (this.r == cj.s) {
                cj.q(this.q(cj.s));
            }
            if (cj2 != null && cj != null) {
                if (cj2.s != cj.s && (!ao.q(23) || this.q(24)) && (!ao.q(25) || this.q(32))) {
                    cj.q();
                }
            }
            else if (cj2 == null && cj != null && (!ao.q(23) || this.q(24)) && (!ao.q(25) || this.q(32))) {
                cj.q();
            }
            if (super.q != null) {
                super.q.w(cj);
            }
        }
        if (cj2 != null) {
            if (this.r == cj2.s) {
                cj2.q(this.q(cj2.s));
            }
            if (cj2 != null && cj != null && cj2.s != cj.s && (!ao.q(23) || this.q(24)) && (!ao.q(25) || this.q(32))) {
                cj2.w();
            }
            if (super.q != null) {
                super.q.w(cj2);
            }
        }
        final aZ az = (aZ)super.p.w(ao.e);
        if (i == this.r && ao.r != i) {
            if (cj != null && !cj.q(57) && (!ao.q(23) || this.q(24)) && (!ao.q(25) || this.q(32))) {
                final A a;
                (a = new A(a.B.q(be.w("(This user has entered %1)"), new String[] { this.q(cj.a) }), ao, false, false, az, this)).q(n, n2);
                final int d = super.D;
                if (super.q != null) {
                    super.q.q(a);
                }
            }
        }
        else if (ao.r == this.r && i != this.r && cj2 != null && cj != null && !cj2.q(57) && (!ao.q(23) || this.q(24)) && (!ao.q(25) || this.q(32))) {
            final A a2;
            (a2 = new A(a.B.q(be.w("(This user has moved to %1)"), new String[] { this.q(cj.a) }), ao, false, false, az, this)).q(n, n2);
            final int j = super.J;
            if (super.q != null) {
                super.q.q(a2);
            }
        }
        final String q;
        if (super.s == ao.s && cj != null && ((cj2 != null && cj.s != cj2.s) || cj2 == null) && (q = cj.q) != null && !"".equals(q)) {
            final aO ao2;
            (ao2 = new aO(-999, cj.a)).p(cj.w());
            ao2.i(cj.t());
            final A a3;
            (a3 = new A(q, ao2, false, false, az, this)).q(n, n2);
            a3.p = true;
            a3.p = cj.t();
            if (cj.g == 0) {
                a3.d = cj.w();
            }
            else {
                a3.d = cj.y();
            }
            if (super.q != null) {
                super.q.q(a3);
            }
        }
        ao.r = i;
        if (super.q != null) {
            if (super.j || i == this.r) {
                super.q.q(ao, true);
            }
            else if (!super.j && r == this.r) {
                super.q.q(ao);
            }
        }
        if (!this.W) {
            synchronized (this) {
                this.W = true;
            }
            if (this.q != null) {
                for (i = 0; i < this.g.q; ++i) {
                    super.q.q((C)this.g.q(i));
                }
                super.q.q().q();
            }
        }
    }
    
    private void A(final dI di) {
        for (int i = 0; i < di.w(); ++i) {
            if (di.q() != null) {
                this.q(di.q());
            }
            p p;
            if ((p = (p)super.a.w(di.q(i, 0))) == null && this.p() && di.q(0, 3)) {
                (p = new p(-999, di.q(0, 1))).e = di.q(0, 1);
            }
            if (p == null) {
                (p = new p(-999, "Guest")).e = super.I;
            }
            if (p != null) {
                int n;
                if ((n = p.s) == super.s) {
                    n = di.w;
                }
                if (!p.e) {
                    switch (di.w) {
                        case -3:
                        case -2:
                        case -1: {
                            if (p.w) {
                                final int g = super.G;
                                break;
                            }
                            final int f = super.F;
                            break;
                        }
                        default: {
                            if (super.o.q(n)) {
                                final int f2 = super.F;
                                break;
                            }
                            final int h = super.H;
                            break;
                        }
                    }
                    if (di.q() != null) {
                        this.q(di.q(i, 0), p, di.w, true, false, di.q, 0, di.q());
                    }
                    else {
                        this.q(di.q(i, 0), p, di.w, di.q, true, di.q, 0);
                    }
                }
            }
        }
    }
    
    protected final void y(final dI di) {
        if (super.q != null) {
            for (int i = 0; i < di.w(); ++i) {
                final int q = di.q(i, 0);
                final int q2 = di.q(i, 1);
                final int q3 = di.q(i, 2);
                final int q4 = di.q(i, 3);
                final int q5 = di.q(i, 4);
                final A a;
                (a = new A(this.q(di.q(i, 1)), this.q(di.q(i, 0)), false, false, (aZ)super.p.w(q), q2, false, false, -1, q3)).p = true;
                a.o = q3;
                a.p = q4;
                a.d = q5;
                a.q(di.q, 0);
                super.q.q(a);
                if (di.w == super.s) {
                    final int h = super.H;
                }
                else {
                    final int f = super.F;
                }
            }
        }
    }
    
    protected final void u(final dI di) {
        this.r();
        for (int i = 0; i < di.w(); ++i) {
            final int q = di.q(i, 0);
            final int q2 = di.q(i, 1);
            final int q3 = di.q(i, 2);
            String s = di.q(i, 0);
            final String q4 = di.q(i, 1);
            if (s == null) {
                s = "";
            }
            if (q > 0) {
                if (dN.q()) {
                    s = ds.q(this.j, s, this.Z);
                }
                else {
                    s = ds.q(this.j, s, this.M);
                }
            }
            final String q5 = a.V.q(s, "\r");
            if (!"".equals(q5)) {
                s = q5;
            }
            final String s2 = s;
            final String s3 = "\n";
            String substring = s2;
            int j = s2.indexOf(s3);
            int n = 0;
            final StringBuffer sb = new StringBuffer();
            while (j >= 0) {
                ++n;
                sb.append(substring.substring(0, j + 1));
                substring = substring.substring(j + 1);
                if (n > 4) {
                    break;
                }
                j = substring.indexOf(s3);
            }
            final String string = sb.toString();
            if (!"".equals(string)) {
                s = string + a.V.q(s.substring(string.length()), "\n");
            }
            p p;
            if ((p = (p)super.a.w(q)) == null && this.p() && di.q(0, 3)) {
                (p = new p(-999, q4)).e = q2;
            }
            if (p == null && q < -2147483638) {
                (p = new p(q, ChatNames.getSecurityName())).e = q;
            }
            if (p == null) {
                p = (p)this.s.w(q);
            }
            if (p != null) {
                int n2 = p.s;
                p.i(q3);
                if (n2 == super.s) {
                    n2 = di.w;
                }
                if (di.w != super.s && di.q(0, 20)) {
                    return;
                }
                if (!p.e) {
                    switch (di.w) {
                        case -3:
                        case -2:
                        case -1: {
                            if (p.w) {
                                final int g = super.G;
                                break;
                            }
                            final int f = super.F;
                            break;
                        }
                        default: {
                            if (super.o.q(n2)) {
                                final int f2 = super.F;
                                break;
                            }
                            final int h = super.H;
                            break;
                        }
                    }
                    if (di.q() != null) {
                        this.q(s, p, di.w, false, di.q(0, 20), di.q, 0, di.q());
                    }
                    else {
                        this.q(s, p, di.w, di.q, false, di.q, 0);
                    }
                }
            }
        }
    }
    
    private void S(final dI di) {
        super.i = di.q(-1);
        try {
            for (int i = 0; i < di.w(); ++i) {
                final int q = di.q(i, 0);
                C c = (C)super.g.w(q);
                if (di.q(i, 63)) {
                    if (c != null) {
                        super.g.w(q);
                    }
                }
                else {
                    if (c == null) {
                        c = new C(q, di.q(i, 0));
                        super.g.q(c);
                    }
                    else {
                        c.a = di.q(i, 0);
                    }
                    c.q = di.q(i, 1);
                    c.w = di.q(i, 2);
                    c.w = di.q(i, 1);
                    c.q = di.q(i, 2);
                    c.e = di.q(i, 3);
                    c.q(di.q(i));
                    if (c.q == null) {
                        c.q = this.w("banners/" + c.q, true);
                    }
                    if (super.q != null && c.q != null) {
                        super.q.q(c);
                    }
                }
            }
            if (super.q != null) {
                super.q.q().q();
            }
        }
        finally {}
    }
    
    private void D(final dI di) {
        for (int w = di.w(), i = 0; i < w; ++i) {
            final aj aj;
            (aj = new aj(di.q(i, 0), di.q(i, 0))).q(di.q(i));
            aj.q = di.q(i, 1);
            if (!aj.q(63) && this.q.q(aj.s) != aj) {
                aj.q = this.e("emoticons/" + aj.q, true);
            }
            this.q.q(aj);
        }
    }
    
    private void F(final dI di) {
        for (int i = 0; i < di.w(); ++i) {
            this.q.q(di.q(i, 0));
        }
    }
    
    public void i(final dI di) {
        if (!dM.q) {
            dM.q(this);
        }
        try {
            for (int i = 0; i < di.w(); ++i) {
                final int q = di.q(i, 0);
                aZ az = (aZ)super.p.w(q);
                if (di.q(i, 63)) {
                    if (az != null) {
                        super.p.w(q);
                    }
                }
                else {
                    if (az == null) {
                        az = new aZ(q, di.q(i, 0));
                        super.p.q(az);
                        az.q = this.e("userIcons/" + az.a, true);
                    }
                    else {
                        az.a = di.q(i, 0);
                        az.q = this.e("userIcons/" + az.a, true);
                    }
                    az.q(di.q(i));
                    az.q(di.q(i, 1));
                    if (di.q(i, 62)) {
                        super.I = q;
                    }
                }
            }
        }
        finally {}
    }
    
    private void G(final dI di) {
        try {
            for (int i = 0; i < di.w(); ++i) {
                this.p.w(di.q(i, 0));
            }
        }
        finally {}
    }
    
    private void H(final dI di) {
        try {
            for (int i = 0; i < di.w(); ++i) {
                final int q = di.q(i, 0);
                final String q2 = di.q(i, 1);
                cx cx = (cx)this.b.w(q);
                if (di.q(i, 63)) {
                    if (cx != null) {
                        this.b.w(q);
                    }
                }
                else {
                    if (cx == null) {
                        cx = new cx(q, di.q(i, 0));
                        this.b.q(cx);
                        cx.q = this.e("starpic/" + q2, true);
                    }
                    else {
                        cx.a = di.q(i, 0);
                    }
                    cx.q = q2;
                    cx.q(di.q(i));
                }
            }
        }
        finally {}
    }
    
    private void J(final dI di) {
        try {
            for (int i = 0; i < di.w(); ++i) {
                this.b.w(di.q(i, 0));
            }
        }
        finally {}
    }
    
    private void K(final dI di) {
        for (int i = 0; i < di.w(); ++i) {
            final int q = di.q(i, 0);
            final int q2 = di.q(i, 1);
            final aO ao;
            if ((ao = (aO)super.a.w(q)) != null) {
                this.q.q(ao, false);
                this.q.put("" + q, "" + q2);
            }
        }
    }
    
    private void L(final dI di) {
        if (super.o != null) {
            for (int i = 0; i < di.w(); ++i) {
                final int q = di.q(i, 0);
                cr cr = (cr)super.d.w(q);
                if (di.q(i, 63)) {
                    if (cr != null) {
                        super.d.w(q);
                    }
                }
                else {
                    if (cr == null) {
                        cr = new cr(q, di.q(i, 0));
                        super.d.q(cr);
                    }
                    else {
                        cr.a = di.q(i, 0);
                    }
                    cr.q(di.q(i));
                    cr.e = di.q(i, 1);
                    cr.j = di.q(i, 2);
                    cr.k = di.q(i, 3);
                    cr.t = de.q(this.w(), cr.k);
                    cr.p(di.q(i, 4));
                    cr.o(di.q(i, 5));
                    cr.i = di.q(i, 1);
                    cr.d = di.q(i, 2);
                    cr.s = ((this.f.w(cr.j) != null) ? ((cB)this.f.w(cr.j)).a : "");
                    cr.e = ((this.p.w(cr.e) != null) ? ((aZ)this.p.w(cr.e)).q : null);
                    cr.r = ((this.b.w(cr.t) != null) ? ((cx)this.b.w(cr.t)).q : null);
                }
            }
        }
    }
    
    private void Z(final dI di) {
        try {
            for (int i = 0; i < di.w(); ++i) {
                this.d.w(di.q(i, 0));
            }
        }
        finally {}
    }
    
    private void X(final dI di) {
        for (int i = 0; i < di.w(); ++i) {
            final p p;
            if ((p = (p)this.a.w(di.q(i, 0))) != null) {
                p.q = di.q(i, 1);
                p.w = di.q(i, 2);
                p.q = di.q(i, 1);
                p.e = di.q(i, 2);
                p.r = di.q(i, 3);
                p.t = di.q(i, 4);
                p.y = di.q(i, 5);
                p.w = di.q(i, 6);
                p.q = di.q(i, 0);
            }
        }
    }
    
    private void C(final dI di) {
        this.r();
        try {
            for (int i = 0; i < di.w(); ++i) {
                final int q = di.q(i, 0);
                aO ao = (aO)super.a.w(q);
                final cj cj = (cj)super.f.w(di.q(i, 2));
                if (di.q(i, 63)) {
                    if (ao != null) {
                        ao.q(di.q(i));
                        if (cj != null) {
                            if ((!ao.q(23) || this.q(24)) && (!ao.q(25) || this.q(32))) {
                                cj.w();
                            }
                            if (super.q != null) {
                                super.q.w(cj);
                            }
                        }
                        final eh eh;
                        (eh = new eh(ao.s, ao.a)).y = false;
                        super.a.w(q);
                        if (super.q != null) {
                            super.q.q(ao);
                            super.q.q(eh, false);
                        }
                        final cJ cj2;
                        if ((cj2 = (cJ)super.o.w(ao.s)) != null) {
                            cj2.dispose();
                        }
                    }
                }
                else {
                    final String e = this.e(di.q(i, 0));
                    if (ao == null) {
                        (ao = new aO(q, e)).q(di.q(i));
                        if ((!ao.q(23) || this.q(24)) && (!ao.q(25) || this.q(32))) {
                            super.a.q(ao);
                        }
                        else {
                            this.s.q(ao);
                        }
                    }
                    else {
                        ao.q(di.q(i));
                        if ((ao.q(23) && !this.q(24)) || (ao.q(25) && !this.q(32))) {
                            final aO ao2 = ao;
                            final cj cj3 = cj;
                            final aO ao3 = ao2;
                            if (ao3 != null) {
                                if (cj3 != null) {
                                    cj3.w();
                                    if (super.q != null) {
                                        super.q.w(cj3);
                                    }
                                }
                                final eh eh2;
                                (eh2 = new eh(ao3.s, ao3.a)).y = false;
                                if (super.q != null) {
                                    super.q.q(ao3);
                                    super.q.q(eh2, false);
                                }
                                final cJ cj4;
                                if ((cj4 = (cJ)super.o.w(ao3.s)) != null) {
                                    cj4.dispose();
                                }
                            }
                            this.s.q(ao);
                        }
                        ao.a = e;
                    }
                    ao.e = di.q(i, 1);
                    ao.q = (aZ)super.p.w(ao.e);
                    ao.r = di.q(i, 2);
                    ao.p(di.q(i, 3));
                    ao.o(di.q(i, 4));
                    ao.t = di.q(i, 5);
                    final aO ao4 = ao;
                    final M b = super.b;
                    final aO ao5 = ao4;
                    if (ao4.t == 0 && ao5.q(62)) {
                        ao5.t = 1000;
                    }
                    ao5.q = (cx)b.w(ao5.t);
                    ao.p = di.q(i, 6);
                    final int u = ao.u;
                    final int q2;
                    final int u2 = (q2 = di.q(i, 7)) & 0xFF;
                    ao.y = (q2 >> 16 & 0xFF);
                    ao.u = u2;
                    ao.b_(di.q(i, 8));
                    ao.a = di.q(i, 9);
                    ao.q = di.q(i, 10);
                    ao.w(di.q(i, 12));
                    final String q3 = di.q(i, 1);
                    final String q4 = di.q(i, 2);
                    if (q3 != null) {
                        ao.u = q3;
                    }
                    if (q4 != null) {
                        ao.i = q4;
                    }
                    if (di.q(i, 3) != null) {
                        ao.o = di.q(i, 3);
                        if (ao.w == null && this.q(41) && ao.o.length() == 2) {
                            ao.w = this.e("flags/" + ao.o + ".gif", false);
                        }
                    }
                    if (q == super.s) {
                        super.a = ao.a;
                        if (ao.e != -999) {
                            super.P = ao.e;
                        }
                    }
                    Label_1093: {
                        if (ao.q(61)) {
                            final aO ao6 = ao;
                            final String a = ao.a;
                            final aO ao7 = ao6;
                            if (a.length() > 4) {
                                if (!a.endsWith(".gif") && !a.endsWith(".jpg")) {
                                    if (!a.endsWith(".bmp")) {
                                        break Label_1093;
                                    }
                                }
                                try {
                                    ao7.q = this.q("NickPic/" + a, true);
                                }
                                catch (Exception ex) {}
                            }
                        }
                    }
                    final eh eh3;
                    (eh3 = new eh(ao.s, ao.a)).y = true;
                    eh3.e = ao.e;
                    eh3.q = (aZ)super.p.w(ao.e);
                    eh3.r = ao.r;
                    eh3.q(ao.w());
                    if (super.q != null) {
                        super.q.q(ao, false);
                        super.q.q(eh3, false);
                    }
                    if (ao.u == 7 && (u == 8 || u == 9)) {
                        this.q(ao, ao.r, 0L, 0);
                    }
                    if (super.s == ao.s) {
                        this.w(ao);
                    }
                }
            }
        }
        finally {}
    }
    
    private void w(final String s, final int n) {
        (this.q = new Socket(s, n)).setTcpNoDelay(true);
        this.eos = new c(new BufferedOutputStream(this.q.getOutputStream(), 16));
        this.eis = new cF(new BufferedInputStream(this.q.getInputStream(), 256));
        try {
            final c eos = this.eos;
            final byte[] q = a.T.q;
            final c c = eos;
            eos.q = q;
            c.q = 0;
            final cF eis = this.eis;
            final byte[] q2 = a.T.q;
            final cF cf = eis;
            eis.q = q2;
            cf.q = 0;
        }
        catch (Exception ex) {
            throw new StreamCorruptedException();
        }
    }
    
    public final void q(final A a, final p p2) {
        final int s;
        if ((s = p2.s) <= 0) {
            return;
        }
        if (p2.r > 0 && ((cB)super.f.w(p2.r)).q(61) && p2.q(59) && !super.k) {
            return;
        }
        bm bm;
        if ((bm = (bm)super.o.w(s)) == null) {
            bm = new cJ(this, p2);
            final Dimension screenSize;
            int n = (screenSize = Toolkit.getDefaultToolkit().getScreenSize()).width / 2 - 20;
            int n2 = screenSize.height / 2 - 20;
            int n3 = 0;
            int n4 = 0;
            switch (super.o.q % 4) {
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
            if (n2 > 375) {
                n2 = 375;
            }
            bm.setBounds(n4 + 2, n3 + 10, n, n2);
            super.o.q(bm, s);
        }
        if (a != null) {
            bm.q(a);
        }
        bm.setVisible(true);
    }
    
    public final void e() {
        try {
            if (this.S > 0) {
                this.w((a.m.q().w != null) ? a.m.q().w : this.h, cl.q(0));
                this.q(this.S, true);
                this.h = false;
                (this.q = new i((cs)this)).start();
            }
        }
        catch (Exception ex) {}
    }
    
    private void f(final int n) {
        final dI di;
        (di = new dI(67334, 1)).q(0, 0, super.s);
        di.q(0, 1, n);
        di.q(0, 0, super.a);
        di.w = -1;
        di.q = -1;
        this.o(di);
    }
    
    public final void q(final boolean b) {
        super.a = true;
    }
    
    public final void r(final int n) {
        if (super.q.q() != null) {
            super.q.q().setCursor(3);
        }
        if (this.q == null) {
            this.q = new dW(super.q.q(), this);
        }
        final dW q = this.q;
        final int n2 = 0;
        final dW dw = q;
        if (q.q) {
            ((bB)dw.q).q(n2);
        }
        if (this.q.isVisible()) {
            this.q.toFront();
        }
        else {
            this.q.setVisible(true);
        }
        if (super.q.q() != null) {
            super.q.q().setCursor(0);
        }
    }
    
    public boolean q(final dB db) {
        if (this.e.get(db.q()) == null) {
            this.e.put(db.q(), db);
            return true;
        }
        return false;
    }
    
    public final void q(final p p) {
        this.e.remove(p);
    }
    
    public int q(final dB db) {
        this.q(db.q());
        if (super.a.w(db.q().s) == null) {
            db.t();
        }
        final byte b2;
        final byte b = ((b2 = (byte)new Random().nextInt()) >= 0) ? b2 : ((byte)(-b2));
        final dI di;
        (di = new dI(50400768, 1)).q(0, 0, super.s);
        di.q(0, 1, b);
        di.q(0, 0, db.q());
        di.q(0, 1, db.w());
        di.w = db.q().s;
        di.q = -1;
        this.o(di);
        final dI di2;
        (di2 = new dI(66305, 1)).q(0, 0, "[File Transfer Requested: file name " + db.q() + ", size " + r(db.w()) + ". Please click this message to accept.]");
        di2.q(0, 0, super.s);
        di2.q(0, 20);
        di2.q(new byte[] { b });
        di2.w = db.q().s;
        di2.q = -1;
        this.o(di2);
        this.w.put(new Integer(b), db);
        return b;
    }
    
    public final void t(final int n) {
        synchronized (this.w) {
            final di value;
            if ((value = this.w.get(new Integer(n))) == null) {
                return;
            }
            if (value instanceof di) {
                if (super.a.w(value.q.s) == null) {
                    this.w.remove(new Integer(n));
                }
                else {
                    this.w.put(new Integer(n), dg.q(this, value));
                }
            }
        }
    }
    
    public final void q(final int n, final p p2) {
        final dI di;
        (di = new dI(50400770, 1)).q(0, 0, super.s);
        di.q(0, 1, n);
        di.w = p2.s;
        di.q = -1;
        this.o(di);
    }
    
    public final void y(final int n) {
        final dB db;
        if ((db = this.w.remove(new Integer(n))) == null) {
            return;
        }
        final dI di;
        (di = new dI(50400769, 1)).q(0, 0, super.s);
        di.q(0, 1, n);
        di.w = db.q().s;
        di.q = -1;
        this.o(di);
        final dI di2;
        (di2 = new dI(66305, 1)).q(0, 0, "[File Transfer Cancelled: file name " + db.q() + ", size " + r(db.w()) + ".]");
        di2.q(0, 0, super.s);
        di2.q(0, 20);
        di2.q(new byte[] { (byte)n });
        di2.w = db.q().s;
        di2.q = -1;
        this.o(di2);
    }
    
    private static String r(final String s) {
        if (s.length() < 4) {
            return s + " bytes";
        }
        try {
            double n = Long.parseLong(s) / 1024L;
            boolean b = false;
            if (n > 1024.0) {
                n /= 1024.0;
                b = true;
            }
            String s2;
            final int index = (s2 = Math.floor(n * 1000.0 + 5.0) / 1000.0 + "").indexOf(46);
            final int n2 = s2.length() - 1;
            if (index < 0) {
                s2 += ".00";
            }
            else if (index == 0) {
                s2 = "0" + s2;
            }
            else if (index == n2) {
                s2 += "00";
            }
            else if (index == n2 - 1) {
                s2 += "0";
            }
            else if (index + 2 < n2) {
                s2 = s2.substring(0, index + 3);
            }
            return s2 + (b ? " MB" : " KB");
        }
        catch (NumberFormatException ex) {
            return s + " bytes";
        }
    }
    
    public ap() {
        this.w = new Hashtable();
        this.e = new Hashtable();
    }
    
    public final M q() {
        return this.b;
    }
    
    public M w() {
        return null;
    }
    
    public static boolean t() {
        return System.getProperty("java.vendor").startsWith("Sun");
    }
}
