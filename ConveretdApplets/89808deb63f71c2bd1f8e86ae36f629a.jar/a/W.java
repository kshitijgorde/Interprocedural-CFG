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
import java.net.NoRouteToHostException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.io.IOException;
import java.net.URL;
import java.awt.Image;
import java.awt.Component;
import java.awt.Frame;
import java.util.Enumeration;
import java.awt.Dialog;
import java.util.Hashtable;

public abstract class W extends cq
{
    private cz q;
    private Hashtable w;
    private Hashtable e;
    private aW q;
    
    public final void q() {
        this.w.q();
        this.u.q();
        this.i.q();
        this.o.q();
        this.p.q();
        W.d.q();
        this.g.q();
        this.h.q();
        this.j.q();
        this.t.q();
        this.q.w();
        this.n = false;
        if (this.q != null) {
            this.q.interrupt();
        }
        this.q = null;
    }
    
    public final void w() {
        this.a = false;
        this.m = false;
        this.d = false;
        this.s = false;
        this.e.q();
        this.y.q();
        this.W = this.x;
        W.p = "";
        if (super.q != null) {
            for (int i = 0; i < super.q.q; ++i) {
                ((aH)super.q.q(i)).dispose();
            }
        }
        if (super.f != null) {
            for (int j = 0; j < super.f.q; ++j) {
                ((aP)super.f.q(j)).dispose();
            }
        }
        final Enumeration<Dialog> elements = this.e.elements();
        while (elements.hasMoreElements()) {
            final Dialog nextElement;
            if ((nextElement = elements.nextElement()) instanceof Dialog) {
                nextElement.dispose();
            }
        }
        final Enumeration<cl> elements2 = this.w.elements();
        while (elements2.hasMoreElements()) {
            final cl nextElement2;
            if ((nextElement2 = elements2.nextElement()) instanceof cl) {
                nextElement2.r();
            }
        }
        this.r();
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
        super.b = false;
        this.q.q();
        final ae q2;
        if (cu.q != null && (q2 = cu.q).q != null) {
            q2.q.stop();
            q2.q = null;
        }
        super.g = true;
        super.q = null;
        super.eos = null;
        super.eis = null;
    }
    
    public final void q(cr cr) {
        switch (cr.q()) {
            case 67585: {
                this.p(cr);
            }
            case 65794: {
                final cr cr2 = cr;
                final W w = this;
                this.u();
                for (int i = 0; i < cr2.w(); ++i) {
                    final ar ar;
                    if ((ar = (ar)w.e.w(cr2.q(i, 0))) != null) {
                        final String q = a.s.q(ak.q("(This user has left %1)"), new String[] { cu.e });
                        final av av = (av)w.w.w(ar.e);
                        final bn bn = (bn)w.y.w(ar.r);
                        final cI ci;
                        (ci = new cI(ar.s, ar.o)).t = false;
                        if (bn != null) {
                            if (ar.r == w.r && !bn.q(57) && (!ar.q(23) || w.q(24)) && (!ar.q(25) || w.q(32))) {
                                final String q2;
                                String string;
                                if ((q2 = cr2.q(i, 0)) != null) {
                                    string = w.q(q2) + " " + q;
                                }
                                else {
                                    string = q;
                                }
                                final r r = new r(string, ar, false, false, av, w);
                                r.q(cr2.q, 0);
                                final int m = w.m;
                                if (w.q != null) {
                                    w.q.q(r);
                                }
                            }
                            if ((!ar.q(23) || w.q(24)) && (!ar.q(25) || w.q(32))) {
                                ar.r = -999;
                                bn.w();
                            }
                            if (w.q != null) {
                                w.q.w(bn);
                            }
                        }
                        if (w.q != null) {
                            w.q.q(ar);
                            w.q.q(ci, false);
                        }
                        final bE be;
                        if ((be = (bE)w.q.w(ar.s)) != null) {
                            if (ar.s == w.s) {
                                be.dispose();
                            }
                            else {
                                final r r2 = new r(q, ar, false, false, av, w);
                                r2.q(cr2.q, 0);
                                be.q(r2);
                            }
                        }
                        try {
                            w.e.w(ar);
                        }
                        finally {}
                    }
                }
            }
            case 66049: {
                this.u(cr);
            }
            case 66305: {
                this.e(cr);
            }
            case 66308: {
                this.Y(cr);
            }
            case 66306: {
                this.U(cr);
            }
            case 66307: {
                this.s(cr);
            }
            case 66561: {
                this.a(cr);
            }
            case 66816:
            case 50400771: {
                this.i(cr);
            }
            case 66817: {
                this.f(cr);
            }
            case 67073: {
                this.y(cr);
            }
            case 67074: {
                final l l;
                if ((l = (l)super.e.w(cr.q(0, 0))) != null) {
                    final cr cr3;
                    (cr3 = new cr(67073, 1)).w = l.s;
                    cr3.q(0, 0, super.s);
                    this.r(cr3);
                }
            }
            case 67329: {
                this.I(cr);
            }
            case 67330:
            case 17236481: {
                this.c(cr);
            }
            case 17236482: {
                this.v(cr);
            }
            case 67331:
            case 17236737: {
                this.A(cr);
            }
            case 17236738: {
                this.S(cr);
            }
            case 67332:
            case 17236993: {
                this.g(cr);
            }
            case 17236994: {
                this.h(cr);
            }
            case 67333:
            case 17237265: {
                this.j(cr);
            }
            case 17237266: {
                this.k(cr);
            }
            case 17237249:
            case 275795985: {
                this.l(cr);
            }
            case 275795986: {
                this.z(cr);
            }
            case 67334: {
                this.L(cr);
            }
            case 17237506: {
                this.q(this.q.q(), this.q.w());
            }
            case 17237505: {
                this.K(cr);
            }
            case 67584: {
                final long currentTimeMillis = System.currentTimeMillis();
                final cr cr4 = cr;
                this.n = true;
                super.s = cr4.w;
                this.q(cr4.q(0));
                W.p = cr4.q(0, 0);
                if (cu.q == null) {
                    cu.q = this.w("icon_display.gif", false);
                }
                if (!"Admin".equals(W.p)) {
                    if (cu.r == null) {
                        cu.r = this.w(aU.w.e() + "background.gif", true);
                    }
                    if (cu.t == null) {
                        cu.t = this.w(aU.w.e() + "chatbackground.gif", true);
                    }
                    if (a.h.q().q) {
                        (super.q = new bb(this, null)).setVisible(false);
                        a.h.q().q.setVisible(false);
                        a.h.q().add(super.q.q());
                        ((bb)super.q).validate();
                    }
                    else if (cu.w()) {
                        final long currentTimeMillis2 = System.currentTimeMillis();
                        Object o;
                        if (!bN.q()) {
                            o = new cf(this);
                        }
                        else {
                            o = new aS(this);
                        }
                        final aq aq = new aq(this, (ag)o);
                        System.out.println("Load window complete in " + (System.currentTimeMillis() - currentTimeMillis2));
                        super.q = aq.q;
                        this.q = this.q.q();
                    }
                }
                System.out.println("ex time=" + (System.currentTimeMillis() - currentTimeMillis));
            }
            case 67586: {
                this.w(cr);
            }
            case 67843: {
                final cr cr5 = cr;
                this.r = cr5.q(0);
                this.Q = cr5.q(0, 1);
                cu.q = cr5.q(0, 2);
                if (this.q != null && ((bb)this.q).getParent() != null && ((aq)((bb)this.q).getParent()).q != null) {
                    ((aq)((bb)this.q).getParent()).q.u();
                }
            }
            case 68608: {
                this.o(cr);
            }
            case 67338: {
                this.x(cr);
            }
            case 67341:
            case 17239297: {
                this.b(cr);
            }
            case 17239298: {
                this.n(cr);
            }
            case 67844: {
                final cr cr6 = cr;
                this.R = cr6.q(0, 4);
                this.U = cr6.q(0, 5);
                this.O = cr6.q(0, 6);
                this.P = cr6.q(0, 7);
                this.A = cr6.q(0, 9);
                this.I = cr6.q(0, 10);
            }
            case 33621775:
            case 537948401: {
                this.O(cr);
            }
            case 537948402: {
                this.P(cr);
            }
            case 50400768: {
                final cr cr7 = cr;
                this.w.put(new Integer(cr7.q(0, 1)), new bY((l)super.e.w(cr7.q(0, 0)), cr7.q(0, 0), cr7.q(0, 1), cr7.q(0, 1)));
            }
            case 50400769: {
                final cr cr8 = cr;
                synchronized (this.w) {
                    final Object remove;
                    if ((remove = this.w.remove(new Integer(cr8.q(0, 1)))) instanceof cl) {
                        ((cl)remove).r();
                    }
                    break;
                }
            }
            case 50400770: {
                final cl cl;
                if ((cl = this.w.get(new Integer(cr.q(0, 1)))) != null) {
                    cl.t();
                }
            }
            case 4198416: {
                final cr cr9 = cr;
                cr9.q(0, 63);
                cr9.q(0, 62);
                cu.w = cr9.q(0, 61);
                cu.q = cr9.q(0, 60);
                cu.y = cr9.q(0, 59);
                cu.t = cr9.q(0, 58);
                cu.e = cr9.q(0, 54);
                cu.r = cr9.q(0, 53);
                cu.r = cr9.q(0, 53);
                cr9.q(0, 57);
                cu.u = cr9.q(0, 56);
                cu.i = cr9.q(0, 52);
                this.q = null;
            }
            case 4198464: {
                final cr cr10 = cr;
                new n(new Frame(), cr10.q(0, 0), cr10.q(0, 1), this).setVisible(true);
            }
            case 4198496: {
                final String q3 = (cr = cr).q(0, 0);
                cr.q(0, 1);
                cj.q(cr.q(0, 2));
                cu.r = cr.q(0, 3);
                cj.w(cr.q(0, 4));
                cj.e(cr.q(0, 5));
                W.p = cr.q(0, 6);
                final cn q4;
                if ((q4 = a.h.q().q).q != null) {
                    q4.q = q4.q.getImage(q4.q.getCodeBase(), "Resources/" + cq.p + "/" + q4.q);
                }
                if (!"".equals(q3)) {
                    cu.e = q3;
                }
                cr.q(0, 0);
            }
            case 16974593: {
                cq.d(cr);
            }
            case 4198465: {
                this.T(cr);
            }
            case 4198466:
            case 1074807297: {
                E(cr);
            }
            case 1074807298: {
                R(cr);
            }
            case 4198512:
            case 1074819073: {
                this.H(cr);
            }
            case 1074819074: {
                this.J(cr);
            }
            case 33621773:
            case 537948369: {
                this.D(cr);
            }
            case 537948370: {
                this.F(cr);
            }
            case 4198513: {
                this.G(cr);
            }
            case 4198528: {
                this.m(cr);
            }
            case 4198529: {
                this.Q(cr);
            }
            case 4202544: {
                this.W(cr);
                break;
            }
        }
    }
    
    private void m(final cr cr) {
        final ae q;
        (q = cu.q).q();
        q.q(0);
        try {
            for (int i = 0; i < cr.w(); ++i) {
                final int q2 = cr.q(i, 0);
                final String q3 = cr.q(i, 0);
                as as = (as)super.j.w(q2);
                if (cr.q(i, 63)) {
                    if (as != null) {
                        super.j.w(q2);
                    }
                }
                else {
                    if (as == null) {
                        as = new as(q2, q3);
                    }
                    else {
                        as.o = q3;
                    }
                    as.q(cr.q(i));
                    super.j.q(as);
                    as.q = cr.q(i, 1);
                    as.p(cr.q(i, 2));
                    as.o(cr.q(i, 3));
                    cr.q(i, 4);
                    as.w = cr.q(i, 5);
                    as.q = cr.q(i, 1);
                    if (as.q == null || as.q.trim().equals("")) {
                        as.p(aU.w.b.getRGB());
                        as.o(aU.w.n.getRGB());
                        as.w = aU.w.r;
                        as.q = aU.w.w;
                    }
                    cu.q.q = as.q(4);
                    final ae q4 = cu.q;
                    int q5 = as.q;
                    final ae ae = q4;
                    if (q5 == 0) {
                        q5 = 20;
                    }
                    ae.q = q5;
                    ae.w = ae.q;
                    final String s;
                    if (((s = q3).length() <= 3 || !s.substring(0, 4).equals("rss:")) && as.q(0)) {
                        final as as2;
                        cu.q.q(as2.o, as2 = as);
                    }
                }
            }
        }
        finally {}
        cu.q.w();
    }
    
    private void Q(final cr cr) {
        if (cr.w() == 0) {
            return;
        }
        for (int i = 0; i < cr.w(); ++i) {
            final String q = cr.q(i, 0);
            final int q2 = cr.q(i, 0);
            final as as;
            if ((as = (as)this.j.w(q2)) != null) {
                cu.q.q(q, as);
            }
            else {
                System.out.println("Can't find sc id = " + q2);
            }
        }
        cu.q.w();
    }
    
    private void W(final cr cr) {
        int n = 1;
        for (int i = 0; i < cr.w(); ++i) {
            this.k.q(new aZ(n, cr.q(i, 0)));
            ++n;
        }
    }
    
    private static void E(final cr cr) {
        try {
            for (int i = 0; i < cr.w(); ++i) {
                final int q = cr.q(i, 0);
                an an = (an)W.d.w(q);
                if (cr.q(i, 63)) {
                    if (an != null) {
                        W.d.w(q);
                    }
                }
                else {
                    if (an == null) {
                        an = new an(q, cr.q(i, 0));
                        W.d.q(an);
                    }
                    else {
                        an.o = cr.q(i, 0);
                    }
                    an.q(cr.q(i));
                    an.p(cr.q(i, 1));
                }
            }
        }
        finally {}
    }
    
    private static void R(final cr cr) {
        try {
            for (int i = 0; i < cr.w(); ++i) {
                W.d.w(cr.q(i, 0));
            }
        }
        finally {}
    }
    
    public static A q(final int n) {
        final A a = new A();
        for (int i = 0; i < W.d.q; ++i) {
            final an an;
            if ((an = (an)W.d.q(i)).q(n)) {
                a.q(an);
            }
        }
        return a;
    }
    
    private void T(final cr cr) {
        for (int i = 0; i < cr.w(); ++i) {
            ar ar;
            if ((ar = (ar)this.e.w(cr.q(i, 0))) == null) {
                ar = new ar(cr.q(i, 0), cr.q(i, 0));
            }
            ar.p(cr.q(i, 1));
            this.e.q(ar);
            super.q.q(ar, false);
        }
    }
    
    public final void q(final String s, final int n, final int n2) {
        final cr cr;
        (cr = new cr(66310, 1)).q(0, 0, n);
        cr.q(0, 1, n2);
        cr.q(0, 2, super.s);
        cr.q(0, 0, s);
        this.r(cr);
    }
    
    public final void q(final String s, final int n, final int n2, final int n3) {
        final cr cr;
        (cr = new cr(66310, 1)).q(0, 0, n);
        cr.q(0, 1, n2);
        cr.q(0, 2, super.s);
        if (n3 != 0) {
            cr.q(0, 3, n3);
        }
        cr.q(0, 0, s);
        this.r(cr);
    }
    
    public final boolean w() {
        return super.p;
    }
    
    public final Image q(final String s, final boolean b) {
        return this.e(s, b);
    }
    
    public final Image w(final String s, final boolean b) {
        try {
            final URL q = super.q;
            if (b) {
                final URL url = new URL(q, "Resources/" + W.p + "/" + s);
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
    
    public final void q(final String o, final String s, final cm cm, URL url, final int n, final String s2) {
        try {
            super.h = false;
            String s3;
            if (a.h.q().w != null) {
                s3 = a.h.q().w;
            }
            else {
                s3 = q.getHost();
            }
            if (s3 == null || s3.trim().length() <= 0) {
                s3 = s2;
            }
            super.q = q;
            super.o = o;
            Label_0098: {
                if (bF.e < 66048) {
                    if (bF.e) {
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
                for (int n2 = 0; url == 0 && n2 < bm.q.size(); ++n2) {
                    final int q2 = bm.q(n2);
                    try {
                        if (q2 != a.h.q().r) {
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
                        if (n2 == bm.q.size() - 1) {
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
                if (this.q.q() || !this.n) {
                    this.q.q();
                }
                super.q = new Thread(this, "Decoder");
                super.g = false;
                super.q.start();
                if (super.q == null || this.n || this.q.q) {
                    (super.q = new f((br)this)).start();
                }
                if (this.q == null) {
                    (this.q = new aW(this)).start();
                }
                if (this.w == null) {
                    this.w = new az("global");
                }
                if (this.q == null || this.W != n) {
                    this.q = new az("" + n);
                }
                final cr cr;
                (cr = new cr(65793, 1)).q(0, 0, n);
                cr.q(0, 1, super.k);
                cr.q(0, 0, o);
                cr.q(0, 1, "en");
                cr.q(0, 2, s2);
                cr.q(0, 3, s);
                cr.q(0, 4, (String)bo.q(new Object[] { "location.href" }, null));
                cr.q(0, 0, cm);
                if (!this.u() && super.k != -999) {
                    cr.q(0, 2);
                }
                if (this.q(23)) {
                    cr.q(0, 23);
                }
                if (this.q(25)) {
                    cr.q(0, 25);
                }
                if (this.o()) {
                    cr.q(0, 3);
                }
                if (super.o && (!bF.w || bF.w != 1)) {
                    cr.q(0, 21);
                    cr.q(0, 20);
                }
                this.r(cr);
            }
            else {
                this.r();
                new bT(super.q, "Error", s.q(ak.q("No response was received from the server.  The %1 Server may not be running, or may not be a compatible version. Please contact the administrator in charge of the site."), new String[] { cu.e }), this).setVisible(true);
            }
        }
        catch (UnknownHostException ex2) {
            this.r();
            new bT(super.q, s.q(ak.q("%1 could not locate the specified host. Please make sure you are using the correct host name."), new String[] { cu.e }), ex2, this).setVisible(true);
        }
        catch (InterruptedIOException ex3) {
            this.r();
            new bT(super.q, s.q(ak.q("No response was received from the server.  The %1 Server may not be running, or may not be a compatible version. Please contact the administrator in charge of the site."), new String[] { cu.e }), ex3, this).setVisible(true);
        }
        catch (NoRouteToHostException ex4) {
            this.r();
            new bT(super.q, s.q(ak.q("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { cu.e }), ex4, this).setVisible(true);
        }
        catch (SecurityException ex5) {
            this.r();
            new bT(super.q, s.q(ak.q("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { cu.e }), ex5, this).setVisible(true);
        }
        catch (IOException ex6) {
            this.r();
            new bT(super.q, s.q(ak.q("%1 could not connect to the specified host.  Please verify that the %1 Server is running and that you are using the correct host name."), new String[] { cu.e }), ex6, this).setVisible(true);
        }
        catch (Exception ex7) {
            this.r();
            new bT(super.q, s.q(ak.q("An unknown error occurred while connecting to the %1 Server.  Please contact the administrator in charge of the site."), new String[] { cu.e }), ex7, this).setVisible(true);
        }
        super.u = false;
        super.i = false;
    }
    
    public void q(final URL url, final String s) {
    }
    
    public final void e(final int n) {
    }
    
    private void q(final String s, final l l, final int n, final int n2, final boolean b, final long n3, final int n4) {
        this.q(s, l, n, b, false, n3, n4, null);
    }
    
    private void q(final String s, final l l, final int n, final boolean b, final boolean b2, final long n2, final int n3, final byte[] array) {
        if (super.q != null) {
            int s2;
            if ((s2 = l.s) == super.s) {
                s2 = n;
            }
            final r r;
            (r = new r(this.w(s), l, n != -1 && n != -3 && n != -2 && !b, false, (av)super.w.w(l.e), this)).t = (n == -3 || n == -2);
            r.q(n2, n3);
            r.o = l.f;
            if (b2) {
                r.y = array[0];
            }
            if (r.e) {
                final bE be;
                if ((be = (bE)super.q.w(s2)) != null) {
                    be.q(r);
                }
                else {
                    if (!super.k) {
                        super.q.q(r);
                        return;
                    }
                    this.q(r, l);
                }
            }
            else {
                r.q = l.w;
                if (n == -3 || n == -2) {
                    return;
                }
                super.q.q(r);
            }
        }
    }
    
    protected void w(final cr cr) {
        super.u = true;
        super.i = true;
        super.r = -999;
        if (super.w.w(super.l) == null) {
            this.s(super.j);
        }
        else {
            this.s(super.l);
        }
        final bn bn = (bn)super.y.w(super.k);
        bn bn2 = (bn)super.y.w(super.h);
        if (bn == null || (bn.q == null && bn2.q != null)) {
            if (bn2 == null) {
                bn2 = (bn)super.y.q(0);
            }
            this.q(bn2);
            return;
        }
        this.q(bn);
    }
    
    private void u() {
        if (!super.u && !super.i) {
            if (super.q != null) {
                super.q.q().q();
            }
            int r;
            if (super.y.w(super.k) == null) {
                r = super.h;
            }
            else {
                r = super.k;
            }
            super.i = true;
            final bn bn;
            if ((bn = (bn)super.y.w(r)) != null && bn.w && bn.q != null && !this.q(40)) {
                super.r = -999;
                new be(super.q.q(), this, bn).setVisible(true);
            }
            super.r = r;
        }
    }
    
    protected final void q(final ar ar, int i, final long n, final int n2) {
        final int r = ar.r;
        final bn bn = (bn)super.y.w(i);
        final bn bn2 = (bn)super.y.w(ar.r);
        this.u();
        if (ar.s == super.s) {
            if (bn2 != null) {
                bn2.q = false;
            }
            bn.q = true;
            this.r = i;
            if (super.q != null) {
                if (!super.h) {
                    this.q(false, false);
                }
                super.q.q(bn);
                if (!super.q.isVisible()) {
                    super.q.setVisible(true);
                }
                super.q.q().validate();
            }
        }
        if (bn != null) {
            if (this.r == bn.s) {
                bn.q(this.q(bn.s));
            }
            if (bn2 != null && bn != null) {
                if (bn2.s != bn.s && (!ar.q(23) || this.q(24)) && (!ar.q(25) || this.q(32))) {
                    bn.q();
                }
            }
            else if (bn2 == null && bn != null && (!ar.q(23) || this.q(24)) && (!ar.q(25) || this.q(32))) {
                bn.q();
            }
            if (super.q != null) {
                super.q.w(bn);
            }
        }
        if (bn2 != null) {
            if (this.r == bn2.s) {
                bn2.q(this.q(bn2.s));
            }
            if (bn2 != null && bn != null && bn2.s != bn.s && (!ar.q(23) || this.q(24)) && (!ar.q(25) || this.q(32))) {
                bn2.w();
            }
            if (super.q != null) {
                super.q.w(bn2);
            }
        }
        final av av = (av)super.w.w(ar.e);
        if (i == this.r && ar.r != i) {
            if (bn != null && !bn.q(57) && (!ar.q(23) || this.q(24)) && (!ar.q(25) || this.q(32))) {
                final r r2;
                (r2 = new r(a.s.q(ak.q("(This user has entered %1)"), new String[] { this.q(bn.o) }), ar, false, false, av, this)).q(n, n2);
                final int c = super.c;
                if (super.q != null) {
                    super.q.q(r2);
                }
            }
        }
        else if (ar.r == this.r && i != this.r && bn2 != null && bn != null && !bn2.q(57) && (!ar.q(23) || this.q(24)) && (!ar.q(25) || this.q(32))) {
            final r r3;
            (r3 = new r(a.s.q(ak.q("(This user has moved to %1)"), new String[] { this.q(bn.o) }), ar, false, false, av, this)).q(n, n2);
            final int m = super.m;
            if (super.q != null) {
                super.q.q(r3);
            }
        }
        final String q;
        if (super.s == ar.s && bn != null && ((bn2 != null && bn.s != bn2.s) || bn2 == null) && (q = bn.q) != null && !"".equals(q)) {
            final ar ar2;
            (ar2 = new ar(-999, bn.o)).p(bn.w());
            ar2.i(bn.t());
            final r r4;
            (r4 = new r(q, ar2, false, false, av, this)).q(n, n2);
            r4.i = true;
            r4.o = bn.t();
            if (bn.g == 0) {
                r4.s = bn.w();
            }
            else {
                r4.s = bn.y();
            }
            if (super.q != null) {
                super.q.q(r4);
            }
        }
        ar.r = i;
        if (super.q != null) {
            if (super.h || i == this.r) {
                super.q.q(ar, true);
            }
            else if (!super.h && r == this.r) {
                super.q.q(ar);
            }
        }
        if (!this.m) {
            synchronized (this) {
                this.m = true;
            }
            if (this.q != null) {
                for (i = 0; i < this.u.q; ++i) {
                    super.q.q((t)this.u.q(i));
                }
                super.q.q().q();
            }
        }
    }
    
    private void Y(final cr cr) {
        for (int i = 0; i < cr.w(); ++i) {
            l l;
            if ((l = (l)super.e.w(cr.q(i, 0))) == null && this.o() && cr.q(0, 3)) {
                (l = new l(-999, cr.q(0, 1))).e = cr.q(0, 1);
            }
            if (l == null) {
                (l = new l(-999, "Guest")).e = super.j;
            }
            if (l != null) {
                int n;
                if ((n = l.s) == super.s) {
                    n = cr.w;
                }
                if (!l.e) {
                    switch (cr.w) {
                        case -3:
                        case -2:
                        case -1: {
                            if (l.w) {
                                final int b = super.b;
                                break;
                            }
                            final int v = super.v;
                            break;
                        }
                        default: {
                            if (super.q.q(n)) {
                                final int v2 = super.v;
                                break;
                            }
                            final int n2 = super.n;
                            break;
                        }
                    }
                    if (cr.q() != null) {
                        this.q(cr.q(i, 0), l, cr.w, true, false, cr.q, 0, cr.q());
                    }
                    else {
                        this.q(cr.q(i, 0), l, cr.w, cr.q, true, cr.q, 0);
                    }
                }
            }
        }
    }
    
    private void U(final cr cr) {
        if (super.q != null) {
            for (int i = 0; i < cr.w(); ++i) {
                final int q = cr.q(i, 0);
                final int q2 = cr.q(i, 1);
                final int q3 = cr.q(i, 2);
                final int q4 = cr.q(i, 3);
                final int q5 = cr.q(i, 4);
                final r r;
                (r = new r(this.q(cr.q(i, 1)), this.q(cr.q(i, 0)), false, false, (av)super.w.w(q), q2, false, false, -1, q3)).i = true;
                r.i = q3;
                r.o = q4;
                r.s = q5;
                r.q(cr.q, 0);
                super.q.q(r);
                if (cr.w == super.s) {
                    final int n = super.n;
                }
                else {
                    final int v = super.v;
                }
            }
        }
    }
    
    protected final void e(final cr cr) {
        this.u();
        for (int i = 0; i < cr.w(); ++i) {
            final int q = cr.q(i, 0);
            final int q2 = cr.q(i, 1);
            final int q3 = cr.q(i, 2);
            String s = cr.q(i, 0);
            final String q4 = cr.q(i, 1);
            if (s == null) {
                s = "";
            }
            if (q > 0) {
                if (cu.q()) {
                    s = cg.q(this.o, s, this.E);
                }
                else {
                    s = cg.q(this.o, s, this.O);
                }
            }
            final String q5 = H.q(s, "\r");
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
                s = string + H.q(s.substring(string.length()), "\n");
            }
            l l;
            if ((l = (l)super.e.w(q)) == null && this.o() && cr.q(0, 3)) {
                (l = new l(-999, q4)).e = q2;
            }
            if (l == null && q < -2147483638) {
                (l = new l(q, ChatNames.getSecurityName())).e = q;
            }
            if (l == null) {
                l = (l)this.r.w(q);
            }
            if (l != null) {
                int n2 = l.s;
                l.i(q3);
                if (n2 == super.s) {
                    n2 = cr.w;
                }
                if (cr.w != super.s && cr.q(0, 20)) {
                    return;
                }
                if (!l.e) {
                    switch (cr.w) {
                        case -3:
                        case -2:
                        case -1: {
                            if (l.w) {
                                final int b = super.b;
                                break;
                            }
                            final int v = super.v;
                            break;
                        }
                        default: {
                            if (super.q.q(n2)) {
                                final int v2 = super.v;
                                break;
                            }
                            final int n3 = super.n;
                            break;
                        }
                    }
                    if (cr.q() != null) {
                        this.q(s, l, cr.w, false, cr.q(0, 20), cr.q, 0, cr.q());
                    }
                    else {
                        this.q(s, l, cr.w, cr.q, false, cr.q, 0);
                    }
                }
            }
        }
    }
    
    private void I(final cr cr) {
        cr.q(-1);
        try {
            for (int i = 0; i < cr.w(); ++i) {
                final int q = cr.q(i, 0);
                t t = (t)super.u.w(q);
                if (cr.q(i, 63)) {
                    if (t != null) {
                        super.u.w(q);
                    }
                }
                else {
                    if (t == null) {
                        t = new t(q, cr.q(i, 0));
                        super.u.q(t);
                    }
                    else {
                        t.o = cr.q(i, 0);
                    }
                    cr.q(i, 1);
                    cr.q(i, 2);
                    t.w = cr.q(i, 1);
                    t.q = cr.q(i, 2);
                    cr.q(i, 3);
                    t.q(cr.q(i));
                    if (t.q == null) {
                        t.q = this.w("banners/" + t.q, true);
                    }
                    if (super.q != null && t.q != null) {
                        super.q.q(t);
                    }
                }
            }
            if (super.q != null) {
                super.q.q().q();
            }
        }
        finally {}
    }
    
    private void O(final cr cr) {
        for (int w = cr.w(), i = 0; i < w; ++i) {
            final Q q;
            (q = new Q(cr.q(i, 0), cr.q(i, 0))).q(cr.q(i));
            q.q = cr.q(i, 1);
            if (!q.q(63) && this.q.q(q.s) != q) {
                q.q = this.e("emoticons/" + q.q, true);
            }
            this.q.q(q);
        }
    }
    
    private void P(final cr cr) {
        for (int i = 0; i < cr.w(); ++i) {
            this.q.q(cr.q(i, 0));
        }
    }
    
    private void A(final cr cr) {
        if (!ct.q) {
            ct.q(this);
        }
        try {
            for (int i = 0; i < cr.w(); ++i) {
                final int q = cr.q(i, 0);
                av av = (av)super.w.w(q);
                if (cr.q(i, 63)) {
                    if (av != null) {
                        super.w.w(q);
                    }
                }
                else {
                    if (av == null) {
                        av = new av(q, cr.q(i, 0));
                        super.w.q(av);
                        av.q = this.e("userIcons/" + av.o, true);
                    }
                    else {
                        av.o = cr.q(i, 0);
                        av.q = this.e("userIcons/" + av.o, true);
                    }
                    av.q(cr.q(i));
                    final av av2 = av;
                    int q2 = cr.q(i, 1);
                    final av av3 = av2;
                    if (q2 == 0) {
                        q2 = -1;
                    }
                    av3.q = q2;
                    if (cr.q(i, 62)) {
                        super.j = q;
                    }
                }
            }
        }
        finally {}
    }
    
    private void S(final cr cr) {
        try {
            for (int i = 0; i < cr.w(); ++i) {
                this.w.w(cr.q(i, 0));
            }
        }
        finally {}
    }
    
    private void D(final cr cr) {
        try {
            for (int i = 0; i < cr.w(); ++i) {
                final int q = cr.q(i, 0);
                final String q2 = cr.q(i, 1);
                bv bv = (bv)this.h.w(q);
                if (cr.q(i, 63)) {
                    if (bv != null) {
                        this.h.w(q);
                    }
                }
                else {
                    if (bv == null) {
                        bv = new bv(q, cr.q(i, 0));
                        this.h.q(bv);
                        bv.q = this.e("starpic/" + q2, true);
                    }
                    else {
                        bv.o = cr.q(i, 0);
                    }
                    bv.q(cr.q(i));
                }
            }
        }
        finally {}
    }
    
    private void F(final cr cr) {
        try {
            for (int i = 0; i < cr.w(); ++i) {
                this.h.w(cr.q(i, 0));
            }
        }
        finally {}
    }
    
    private void G(final cr cr) {
        for (int i = 0; i < cr.w(); ++i) {
            final int q = cr.q(i, 0);
            final int q2 = cr.q(i, 1);
            final ar ar;
            if ((ar = (ar)super.e.w(q)) != null) {
                this.q.q(ar, false);
                this.q.put("" + q, "" + q2);
            }
        }
    }
    
    private void H(final cr cr) {
        if (super.q != null) {
            for (int i = 0; i < cr.w(); ++i) {
                final int q = cr.q(i, 0);
                bq bq = (bq)super.t.w(q);
                if (cr.q(i, 63)) {
                    if (bq != null) {
                        super.t.w(q);
                    }
                }
                else {
                    if (bq == null) {
                        bq = new bq(q, cr.q(i, 0));
                        super.t.q(bq);
                    }
                    else {
                        bq.o = cr.q(i, 0);
                    }
                    bq.q(cr.q(i));
                    bq.e = cr.q(i, 1);
                    bq.h = cr.q(i, 2);
                    bq.j = cr.q(i, 3);
                    bq.t = bU.q(null, bq.j);
                    bq.p(cr.q(i, 4));
                    bq.o(cr.q(i, 5));
                    bq.u = cr.q(i, 1);
                    bq.a = cr.q(i, 2);
                    bq.p = ((this.y.w(bq.h) != null) ? ((by)this.y.w(bq.h)).o : "");
                    bq.e = ((this.w.w(bq.e) != null) ? ((av)this.w.w(bq.e)).q : null);
                    bq.r = ((this.h.w(bq.t) != null) ? ((bv)this.h.w(bq.t)).q : null);
                }
            }
        }
    }
    
    private void J(final cr cr) {
        try {
            for (int i = 0; i < cr.w(); ++i) {
                this.t.w(cr.q(i, 0));
            }
        }
        finally {}
    }
    
    private void K(final cr cr) {
        for (int i = 0; i < cr.w(); ++i) {
            final l l;
            if ((l = (l)this.e.w(cr.q(i, 0))) != null) {
                l.q = cr.q(i, 1);
                l.w = cr.q(i, 2);
                l.q = cr.q(i, 1);
                l.w = cr.q(i, 2);
                l.e = cr.q(i, 3);
                l.r = cr.q(i, 4);
                l.t = cr.q(i, 5);
                cr.q(i, 6);
                l.q = cr.q(i, 0);
            }
        }
    }
    
    private void L(final cr cr) {
        this.u();
        try {
            for (int i = 0; i < cr.w(); ++i) {
                final int q = cr.q(i, 0);
                ar ar = (ar)super.e.w(q);
                final bn bn = (bn)super.y.w(cr.q(i, 2));
                if (cr.q(i, 63)) {
                    if (ar != null) {
                        ar.q(cr.q(i));
                        if (bn != null) {
                            if ((!ar.q(23) || this.q(24)) && (!ar.q(25) || this.q(32))) {
                                bn.w();
                            }
                            if (super.q != null) {
                                super.q.w(bn);
                            }
                        }
                        final cI ci;
                        (ci = new cI(ar.s, ar.o)).t = false;
                        super.e.w(q);
                        if (super.q != null) {
                            super.q.q(ar);
                            super.q.q(ci, false);
                        }
                        final bE be;
                        if ((be = (bE)super.q.w(ar.s)) != null) {
                            be.dispose();
                        }
                    }
                }
                else {
                    final String e = this.e(cr.q(i, 0));
                    if (ar == null) {
                        (ar = new ar(q, e)).q(cr.q(i));
                        if ((!ar.q(23) || this.q(24)) && (!ar.q(25) || this.q(32))) {
                            super.e.q(ar);
                        }
                        else {
                            this.r.q(ar);
                        }
                    }
                    else {
                        ar.q(cr.q(i));
                        if ((ar.q(23) && !this.q(24)) || (ar.q(25) && !this.q(32))) {
                            final ar ar2 = ar;
                            final bn bn2 = bn;
                            final ar ar3 = ar2;
                            if (ar3 != null) {
                                if (bn2 != null) {
                                    bn2.w();
                                    if (super.q != null) {
                                        super.q.w(bn2);
                                    }
                                }
                                final cI ci2;
                                (ci2 = new cI(ar3.s, ar3.o)).t = false;
                                if (super.q != null) {
                                    super.q.q(ar3);
                                    super.q.q(ci2, false);
                                }
                                final bE be2;
                                if ((be2 = (bE)super.q.w(ar3.s)) != null) {
                                    be2.dispose();
                                }
                            }
                            this.r.q(ar);
                        }
                        ar.o = e;
                    }
                    ar.e = cr.q(i, 1);
                    ar.q = (av)super.w.w(ar.e);
                    ar.r = cr.q(i, 2);
                    ar.p(cr.q(i, 3));
                    ar.o(cr.q(i, 4));
                    ar.t = cr.q(i, 5);
                    final ar ar4 = ar;
                    final A h = super.h;
                    final ar ar5 = ar4;
                    if (ar4.t == 0 && ar5.q(62)) {
                        ar5.t = 1000;
                    }
                    ar5.q = (bv)h.w(ar5.t);
                    ar.p = cr.q(i, 6);
                    final int u = ar.u;
                    final int q2;
                    final int u2 = (q2 = cr.q(i, 7)) & 0xFF;
                    ar.y = (q2 >> 16 & 0xFF);
                    ar.u = u2;
                    ar.b_(cr.q(i, 8));
                    ar.a = cr.q(i, 9);
                    final ar ar6 = ar;
                    final int n = i;
                    if (10 >= cr.e() - 1) {
                        throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + 10);
                    }
                    ar6.q = (cr.q(n, 10) << 32) + (cr.q(n, 11) & 0xFFFFFFFFL);
                    ar.w(cr.q(i, 12));
                    final String q3 = cr.q(i, 1);
                    final String q4 = cr.q(i, 2);
                    if (q3 != null) {
                        ar.y = q3;
                    }
                    if (q4 != null) {
                        ar.u = q4;
                    }
                    if (cr.q(i, 3) != null) {
                        ar.i = cr.q(i, 3);
                        if (ar.w == null && this.q(41) && ar.i.length() == 2) {
                            ar.w = this.e("flags/" + ar.i + ".gif", false);
                        }
                    }
                    if (q == super.s) {
                        super.o = ar.o;
                        if (ar.e != -999) {
                            super.l = ar.e;
                        }
                    }
                    Label_1164: {
                        if (ar.q(61)) {
                            final ar ar7 = ar;
                            final String o = ar.o;
                            final ar ar8 = ar7;
                            if (o.length() > 4) {
                                if (!o.endsWith(".gif") && !o.endsWith(".jpg")) {
                                    if (!o.endsWith(".bmp")) {
                                        break Label_1164;
                                    }
                                }
                                try {
                                    ar8.q = this.q("NickPic/" + o, true);
                                }
                                catch (Exception ex) {}
                            }
                        }
                    }
                    final cI ci3;
                    (ci3 = new cI(ar.s, ar.o)).t = true;
                    ci3.e = ar.e;
                    ci3.q = (av)super.w.w(ar.e);
                    ci3.r = ar.r;
                    ci3.q(ar.q());
                    if (super.q != null) {
                        super.q.q(ar, false);
                        super.q.q(ci3, false);
                    }
                    if (ar.u == 7 && (u == 8 || u == 9)) {
                        this.q(ar, ar.r, 0L, 0);
                    }
                    if (super.s == ar.s) {
                        this.w(ar);
                    }
                }
            }
        }
        finally {}
    }
    
    private void w(final String s, final int n) {
        (this.q = new Socket(s, n)).setTcpNoDelay(true);
        this.eos = new a(new BufferedOutputStream(this.q.getOutputStream(), 16));
        this.eis = new bA(new BufferedInputStream(this.q.getInputStream(), 256));
        try {
            final a eos = this.eos;
            final byte[] q = F.q;
            final a a = eos;
            eos.q = q;
            a.q = 0;
            final bA eis = this.eis;
            final byte[] q2 = F.q;
            final bA ba = eis;
            eis.q = q2;
            ba.q = 0;
        }
        catch (Exception ex) {
            throw new StreamCorruptedException();
        }
    }
    
    public final void q(final r r, final l l) {
        final int s;
        if ((s = l.s) <= 0) {
            return;
        }
        if (l.r > 0 && ((by)super.y.w(l.r)).q(61) && l.q(59)) {
            return;
        }
        aH ah;
        if ((ah = (aH)super.q.w(s)) == null) {
            ah = new bE(this, l);
            final Dimension screenSize;
            int n = (screenSize = Toolkit.getDefaultToolkit().getScreenSize()).width / 2 - 20;
            int n2 = screenSize.height / 2 - 20;
            int n3 = 0;
            int n4 = 0;
            switch (super.q.q % 4) {
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
            ah.setBounds(n4 + 2, n3 + 10, n, n2);
            super.q.q(ah, s);
        }
        if (r != null) {
            ah.q(r);
        }
        ah.setVisible(true);
    }
    
    public final void e() {
        try {
            if (this.x > 0) {
                this.w((a.h.q().w != null) ? a.h.q().w : this.s, bm.q(0));
                this.q(this.x, true);
                this.g = false;
                (this.q = new f((br)this)).start();
            }
        }
        catch (Exception ex) {}
    }
    
    private void s(final int n) {
        final cr cr;
        (cr = new cr(67334, 1)).q(0, 0, super.s);
        cr.q(0, 1, n);
        cr.q(0, 0, super.o);
        cr.w = -1;
        cr.q = -1;
        this.r(cr);
    }
    
    public final void q(final boolean b) {
        super.p = true;
    }
    
    public final void r(final int n) {
        if (super.q.q() != null) {
            super.q.q().setCursor(3);
        }
        if (this.q == null) {
            this.q = new cz(super.q.q(), this);
        }
        final cz q = this.q;
        final int n2 = 0;
        final cz cz = q;
        if (q.q) {
            ((aT)cz.q).q(n2);
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
    
    public boolean q(final cl cl) {
        if (this.e.get(cl.q()) == null) {
            this.e.put(cl.q(), cl);
            return true;
        }
        return false;
    }
    
    public final void q(final l l) {
        this.e.remove(l);
    }
    
    public int q(final cl cl) {
        this.q(cl.q());
        if (super.e.w(cl.q().s) == null) {
            cl.r();
        }
        final byte b2;
        final byte b = ((b2 = (byte)new Random().nextInt()) >= 0) ? b2 : ((byte)(-b2));
        final cr cr;
        (cr = new cr(50400768, 1)).q(0, 0, super.s);
        cr.q(0, 1, b);
        cr.q(0, 0, cl.q());
        cr.q(0, 1, cl.w());
        cr.w = cl.q().s;
        cr.q = -1;
        this.r(cr);
        final cr cr2;
        (cr2 = new cr(66305, 1)).q(0, 0, "[File Transfer Requested: file name " + cl.q() + ", size " + r(cl.w()) + ". Please click this message to accept.]");
        cr2.q(0, 0, super.s);
        cr2.q(0, 20);
        cr2.q(new byte[] { b });
        cr2.w = cl.q().s;
        cr2.q = -1;
        this.r(cr2);
        this.w.put(new Integer(b), cl);
        return b;
    }
    
    public final void t(final int n) {
        synchronized (this.w) {
            final bY value;
            if ((value = this.w.get(new Integer(n))) == null) {
                return;
            }
            if (value instanceof bY) {
                if (super.e.w(value.q.s) == null) {
                    this.w.remove(new Integer(n));
                }
                else {
                    this.w.put(new Integer(n), bW.q(this, value));
                }
            }
        }
    }
    
    public final void q(final int n, final l l) {
        final cr cr;
        (cr = new cr(50400770, 1)).q(0, 0, super.s);
        cr.q(0, 1, n);
        cr.w = l.s;
        cr.q = -1;
        this.r(cr);
    }
    
    public final void y(final int n) {
        final cl cl;
        if ((cl = this.w.remove(new Integer(n))) == null) {
            return;
        }
        final cr cr;
        (cr = new cr(50400769, 1)).q(0, 0, super.s);
        cr.q(0, 1, n);
        cr.w = cl.q().s;
        cr.q = -1;
        this.r(cr);
        final cr cr2;
        (cr2 = new cr(66305, 1)).q(0, 0, "[File Transfer Cancelled: file name " + cl.q() + ", size " + r(cl.w()) + ".]");
        cr2.q(0, 0, super.s);
        cr2.q(0, 20);
        cr2.q(new byte[] { (byte)n });
        cr2.w = cl.q().s;
        cr2.q = -1;
        this.r(cr2);
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
    
    public W() {
        this.w = new Hashtable();
        this.e = new Hashtable();
    }
    
    public final A q() {
        return this.h;
    }
    
    public static boolean e() {
        return System.getProperty("java.vendor").startsWith("Sun");
    }
}
