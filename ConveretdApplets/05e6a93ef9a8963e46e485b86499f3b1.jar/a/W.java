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

public abstract class W extends co
{
    private cw q;
    private Hashtable w;
    private Hashtable e;
    private aV q;
    
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
        this.Q = this.z;
        W.p = "";
        if (super.q != null) {
            for (int i = 0; i < super.q.q; ++i) {
                ((aI)super.q.q(i)).dispose();
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
        final Enumeration<cj> elements2 = this.w.elements();
        while (elements2.hasMoreElements()) {
            final cj nextElement2;
            if ((nextElement2 = elements2.nextElement()) instanceof cj) {
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
        final af q2;
        if (cs.q != null && (q2 = cs.q).q != null) {
            q2.q.stop();
            q2.q = null;
        }
        super.g = true;
        super.q = null;
        super.eos = null;
        super.eis = null;
    }
    
    public final void q(cp cp) {
        switch (cp.q()) {
            case 67585: {
                this.p(cp);
            }
            case 65794: {
                final cp cp2 = cp;
                final W w = this;
                this.u();
                for (int i = 0; i < cp2.w(); ++i) {
                    final as as;
                    if ((as = (as)w.e.w(cp2.q(i, 0))) != null) {
                        final String q = a.t.q(al.q("(This user has left %1)"), new String[] { cs.e });
                        final aw aw = (aw)w.w.w(as.e);
                        final bm bm = (bm)w.y.w(as.r);
                        final cF cf;
                        (cf = new cF(as.a, as.o)).t = false;
                        if (bm != null) {
                            if (as.r == w.r && !bm.q(57) && (!as.q(23) || w.q(24)) && (!as.q(25) || w.q(32))) {
                                final String q2;
                                String string;
                                if ((q2 = cp2.q(i, 0)) != null) {
                                    string = w.q(q2) + " " + q;
                                }
                                else {
                                    string = q;
                                }
                                final s s = new s(string, as, false, false, aw, w);
                                s.q(cp2.q, 0);
                                final int n = w.n;
                                if (w.q != null) {
                                    w.q.q(s);
                                }
                            }
                            if ((!as.q(23) || w.q(24)) && (!as.q(25) || w.q(32))) {
                                as.r = -999;
                                bm.w();
                            }
                            if (w.q != null) {
                                w.q.w(bm);
                            }
                        }
                        if (w.q != null) {
                            w.q.q(as);
                            w.q.q(cf, false);
                        }
                        final bD bd;
                        if ((bd = (bD)w.q.w(as.a)) != null) {
                            if (as.a == w.a) {
                                bd.dispose();
                            }
                            else {
                                final s s2 = new s(q, as, false, false, aw, w);
                                s2.q(cp2.q, 0);
                                bd.q(s2);
                            }
                        }
                        try {
                            w.e.w(as);
                        }
                        finally {}
                    }
                }
            }
            case 66049: {
                this.u(cp);
            }
            case 66305: {
                this.e(cp);
            }
            case 66308: {
                this.Y(cp);
            }
            case 66306: {
                this.U(cp);
            }
            case 66307: {
                this.s(cp);
            }
            case 66561: {
                this.a(cp);
            }
            case 66816:
            case 50400771: {
                this.i(cp);
            }
            case 66817: {
                this.f(cp);
            }
            case 67073: {
                this.y(cp);
            }
            case 67074: {
                final l l;
                if ((l = (l)super.e.w(cp.q(0, 0))) != null) {
                    final cp cp3;
                    (cp3 = new cp(67073, 1)).w = l.a;
                    cp3.q(0, 0, super.a);
                    this.r(cp3);
                }
            }
            case 67329: {
                this.I(cp);
            }
            case 67330:
            case 17236481: {
                this.c(cp);
            }
            case 17236482: {
                this.v(cp);
            }
            case 67331:
            case 17236737: {
                this.A(cp);
            }
            case 17236738: {
                this.S(cp);
            }
            case 67332:
            case 17236993: {
                this.g(cp);
            }
            case 17236994: {
                this.h(cp);
            }
            case 67333:
            case 17237265: {
                this.j(cp);
            }
            case 17237266: {
                this.k(cp);
            }
            case 17237249:
            case 275795985: {
                this.l(cp);
            }
            case 275795986: {
                this.z(cp);
            }
            case 67334: {
                this.L(cp);
            }
            case 17237506: {
                this.q(this.q.q(), this.q.w());
            }
            case 17237505: {
                this.K(cp);
            }
            case 67584: {
                final long currentTimeMillis = System.currentTimeMillis();
                final cp cp4 = cp;
                this.n = true;
                super.a = cp4.w;
                this.q(cp4.q(0));
                W.p = cp4.q(0, 0);
                if (cs.q == null) {
                    cs.q = this.w("icon_display.gif", false);
                }
                if (!"Admin".equals(W.p)) {
                    if (cs.r == null) {
                        cs.r = this.w(aT.w.e() + "background.gif", true);
                    }
                    if (cs.t == null) {
                        cs.t = this.w(aT.w.e() + "chatbackground.gif", true);
                    }
                    if (a.h.q().q) {
                        (super.q = new ba(this, null)).setVisible(false);
                        a.h.q().q.setVisible(false);
                        a.h.q().add(super.q.q());
                        ((ba)super.q).validate();
                    }
                    else if (cs.w()) {
                        final long currentTimeMillis2 = System.currentTimeMillis();
                        Object o;
                        if (!bM.q()) {
                            o = new cd(this);
                        }
                        else {
                            o = new aR(this);
                        }
                        final ar ar = new ar(this, (ah)o);
                        System.out.println("Load window complete in " + (System.currentTimeMillis() - currentTimeMillis2));
                        super.q = ar.q;
                        this.q = this.q.q();
                    }
                }
                System.out.println("ex time=" + (System.currentTimeMillis() - currentTimeMillis));
            }
            case 67586: {
                this.w(cp);
            }
            case 67843: {
                final cp cp5 = cp;
                this.r = cp5.q(0);
                this.m = cp5.q(0, 1);
                cs.q = cp5.q(0, 2);
                if (this.q != null && ((ba)this.q).getParent() != null && ((ar)((ba)this.q).getParent()).q != null) {
                    ((ar)((ba)this.q).getParent()).q.u();
                }
            }
            case 68608: {
                this.o(cp);
            }
            case 67338: {
                this.x(cp);
            }
            case 67341:
            case 17239297: {
                this.b(cp);
            }
            case 17239298: {
                this.n(cp);
            }
            case 67844: {
                final cp cp6 = cp;
                this.E = cp6.q(0, 4);
                this.Y = cp6.q(0, 5);
                this.I = cp6.q(0, 6);
                this.O = cp6.q(0, 7);
                this.P = cp6.q(0, 9);
                this.U = cp6.q(0, 10);
            }
            case 33621775:
            case 537948401: {
                this.O(cp);
            }
            case 537948402: {
                this.P(cp);
            }
            case 50400768: {
                final cp cp7 = cp;
                this.w.put(new Integer(cp7.q(0, 1)), new bW((l)super.e.w(cp7.q(0, 0)), cp7.q(0, 0), cp7.q(0, 1), cp7.q(0, 1)));
            }
            case 50400769: {
                final cp cp8 = cp;
                synchronized (this.w) {
                    final Object remove;
                    if ((remove = this.w.remove(new Integer(cp8.q(0, 1)))) instanceof cj) {
                        ((cj)remove).r();
                    }
                    break;
                }
            }
            case 50400770: {
                final cj cj;
                if ((cj = this.w.get(new Integer(cp.q(0, 1)))) != null) {
                    cj.t();
                }
            }
            case 4198416: {
                final cp cp9 = cp;
                cp9.q(0, 63);
                cp9.q(0, 62);
                cs.w = cp9.q(0, 61);
                cs.q = cp9.q(0, 60);
                cs.y = cp9.q(0, 59);
                cs.t = cp9.q(0, 58);
                cs.e = cp9.q(0, 54);
                cs.r = cp9.q(0, 53);
                cs.r = cp9.q(0, 53);
                cp9.q(0, 57);
                cs.u = cp9.q(0, 56);
                cs.i = cp9.q(0, 52);
                this.q = null;
            }
            case 4198464: {
                final cp cp10 = cp;
                new n(new Frame(), cp10.q(0, 0), cp10.q(0, 1), this).setVisible(true);
            }
            case 4198496: {
                final String q3 = (cp = cp).q(0, 0);
                cp.q(0, 1);
                ch.q(cp.q(0, 2));
                cs.r = cp.q(0, 3);
                ch.w(cp.q(0, 4));
                ch.e(cp.q(0, 5));
                if (!"".equals(q3)) {
                    cs.e = q3;
                }
                cp.q(0, 0);
            }
            case 16974593: {
                co.d(cp);
            }
            case 4198465: {
                this.T(cp);
            }
            case 4198466:
            case 1074807297: {
                E(cp);
            }
            case 1074807298: {
                R(cp);
            }
            case 4198512:
            case 1074819073: {
                this.H(cp);
            }
            case 1074819074: {
                this.J(cp);
            }
            case 33621773:
            case 537948369: {
                this.D(cp);
            }
            case 537948370: {
                this.F(cp);
            }
            case 4198513: {
                this.G(cp);
            }
            case 4198528: {
                this.m(cp);
            }
            case 4198529: {
                this.Q(cp);
            }
            case 4202544: {
                this.W(cp);
                break;
            }
        }
    }
    
    private void m(final cp cp) {
        final af q;
        (q = cs.q).q();
        q.q(0);
        try {
            for (int i = 0; i < cp.w(); ++i) {
                final int q2 = cp.q(i, 0);
                final String q3 = cp.q(i, 0);
                at at = (at)super.j.w(q2);
                if (cp.q(i, 63)) {
                    if (at != null) {
                        super.j.w(q2);
                    }
                }
                else {
                    if (at == null) {
                        at = new at(q2, q3);
                    }
                    else {
                        at.o = q3;
                    }
                    at.q(cp.q(i));
                    super.j.q(at);
                    at.q = cp.q(i, 1);
                    at.i(cp.q(i, 2));
                    at.u(cp.q(i, 3));
                    cp.q(i, 4);
                    at.w = cp.q(i, 5);
                    at.q = cp.q(i, 1);
                    if (at.q == null || at.q.trim().equals("")) {
                        at.i(aT.w.b.getRGB());
                        at.u(aT.w.n.getRGB());
                        at.w = aT.w.r;
                        at.q = aT.w.w;
                    }
                    cs.q.q = at.q(4);
                    final af q4 = cs.q;
                    int q5 = at.q;
                    final af af = q4;
                    if (q5 == 0) {
                        q5 = 20;
                    }
                    af.q = q5;
                    af.w = af.q;
                    final String s;
                    if (((s = q3).length() <= 3 || !s.substring(0, 4).equals("rss:")) && at.q(0)) {
                        final at at2;
                        cs.q.q(at2.o, at2 = at);
                    }
                }
            }
        }
        finally {}
        cs.q.w();
    }
    
    private void Q(final cp cp) {
        if (cp.w() == 0) {
            return;
        }
        for (int i = 0; i < cp.w(); ++i) {
            final String q = cp.q(i, 0);
            final int q2 = cp.q(i, 0);
            final at at;
            if ((at = (at)this.j.w(q2)) != null) {
                cs.q.q(q, at);
            }
            else {
                System.out.println("Can't find sc id = " + q2);
            }
        }
        cs.q.w();
    }
    
    private void W(final cp cp) {
        int n = 1;
        for (int i = 0; i < cp.w(); ++i) {
            this.k.q(new aY(n, cp.q(i, 0)));
            ++n;
        }
    }
    
    private static void E(final cp cp) {
        try {
            for (int i = 0; i < cp.w(); ++i) {
                final int q = cp.q(i, 0);
                ao ao = (ao)W.d.w(q);
                if (cp.q(i, 63)) {
                    if (ao != null) {
                        W.d.w(q);
                    }
                }
                else {
                    if (ao == null) {
                        ao = new ao(q, cp.q(i, 0));
                        W.d.q(ao);
                    }
                    else {
                        ao.o = cp.q(i, 0);
                    }
                    ao.q(cp.q(i));
                    ao.i(cp.q(i, 1));
                }
            }
        }
        finally {}
    }
    
    private static void R(final cp cp) {
        try {
            for (int i = 0; i < cp.w(); ++i) {
                W.d.w(cp.q(i, 0));
            }
        }
        finally {}
    }
    
    public static B q(final int n) {
        final B b = new B();
        for (int i = 0; i < W.d.q; ++i) {
            final ao ao;
            if ((ao = (ao)W.d.q(i)).q(n)) {
                b.q(ao);
            }
        }
        return b;
    }
    
    private void T(final cp cp) {
        for (int i = 0; i < cp.w(); ++i) {
            as as;
            if ((as = (as)this.e.w(cp.q(i, 0))) == null) {
                as = new as(cp.q(i, 0), cp.q(i, 0));
            }
            as.i(cp.q(i, 1));
            this.e.q(as);
            super.q.q(as, false);
        }
    }
    
    public final void q(final String s, final int n, final int n2) {
        final cp cp;
        (cp = new cp(66310, 1)).q(0, 0, n);
        cp.q(0, 1, n2);
        cp.q(0, 2, super.a);
        cp.q(0, 0, s);
        this.r(cp);
    }
    
    public final void q(final String s, final int n, final int n2, final int n3) {
        final cp cp;
        (cp = new cp(66310, 1)).q(0, 0, n);
        cp.q(0, 1, n2);
        cp.q(0, 2, super.a);
        if (n3 != 0) {
            cp.q(0, 3, n3);
        }
        cp.q(0, 0, s);
        this.r(cp);
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
    
    public final void q(final String o, final String s, final ck ck, URL url, final int n, final String s2) {
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
                if (bE.e < 66048) {
                    if (bE.e) {
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
                for (int n2 = 0; url == 0 && n2 < bl.q.size(); ++n2) {
                    final int q2 = bl.q(n2);
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
                        if (n2 == bl.q.size() - 1) {
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
                    (super.q = new f((bq)this)).start();
                }
                if (this.q == null) {
                    (this.q = new aV(this)).start();
                }
                if (this.w == null) {
                    this.w = new aA("global");
                }
                if (this.q == null || this.Q != n) {
                    this.q = new aA("" + n);
                }
                final cp cp;
                (cp = new cp(65793, 1)).q(0, 0, n);
                cp.q(0, 1, super.j);
                cp.q(0, 0, o);
                cp.q(0, 1, "en");
                cp.q(0, 2, s2);
                cp.q(0, 3, s);
                cp.q(0, 4, (String)bn.q(new Object[] { "location.href" }, null));
                cp.q(0, 0, ck);
                if (!this.u() && super.j != -999) {
                    cp.q(0, 2);
                }
                if (this.q(23)) {
                    cp.q(0, 23);
                }
                if (this.q(25)) {
                    cp.q(0, 25);
                }
                if (this.o()) {
                    cp.q(0, 3);
                }
                if (super.o && (!bE.w || bE.w != 1)) {
                    cp.q(0, 21);
                    cp.q(0, 20);
                }
                this.r(cp);
            }
            else {
                this.r();
                new p(super.q, "Error", a.t.q(al.q("No response was received from the server.  The %1 Server may not be running, or may not be a compatible version. Please contact the administrator in charge of the site."), new String[] { cs.e }), this).setVisible(true);
            }
        }
        catch (UnknownHostException ex2) {
            this.r();
            new p(super.q, a.t.q(al.q("%1 could not locate the specified host. Please make sure you are using the correct host name."), new String[] { cs.e }), ex2, this).setVisible(true);
        }
        catch (InterruptedIOException ex3) {
            this.r();
            new p(super.q, a.t.q(al.q("No response was received from the server.  The %1 Server may not be running, or may not be a compatible version. Please contact the administrator in charge of the site."), new String[] { cs.e }), ex3, this).setVisible(true);
        }
        catch (NoRouteToHostException ex4) {
            this.r();
            new p(super.q, a.t.q(al.q("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { cs.e }), ex4, this).setVisible(true);
        }
        catch (SecurityException ex5) {
            this.r();
            new p(super.q, a.t.q(al.q("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { cs.e }), ex5, this).setVisible(true);
        }
        catch (IOException ex6) {
            this.r();
            new p(super.q, a.t.q(al.q("%1 could not connect to the specified host.  Please verify that the %1 Server is running and that you are using the correct host name."), new String[] { cs.e }), ex6, this).setVisible(true);
        }
        catch (Exception ex7) {
            this.r();
            new p(super.q, a.t.q(al.q("An unknown error occurred while connecting to the %1 Server.  Please contact the administrator in charge of the site."), new String[] { cs.e }), ex7, this).setVisible(true);
        }
        super.u = false;
        super.i = false;
    }
    
    public void q(final URL url, final String s) {
    }
    
    public final void q(final int n) {
    }
    
    private void q(final String s, final l l, final int n, final int n2, final boolean b, final long n3, final int n4) {
        this.q(s, l, n, b, false, n3, n4, null);
    }
    
    private void q(final String s, final l l, final int n, final boolean b, final boolean b2, final long n2, final int n3, final byte[] array) {
        if (super.q != null) {
            int a;
            if ((a = l.a) == super.a) {
                a = n;
            }
            final s s2;
            (s2 = new s(this.w(s), l, n != -1 && n != -3 && n != -2 && !b, false, (aw)super.w.w(l.e), this)).t = (n == -3 || n == -2);
            s2.q(n2, n3);
            s2.o = l.d;
            if (b2) {
                s2.y = array[0];
            }
            if (s2.e) {
                final bD bd;
                if ((bd = (bD)super.q.w(a)) != null) {
                    bd.q(s2);
                }
                else {
                    if (!super.k) {
                        super.q.q(s2);
                        return;
                    }
                    this.q(s2, l);
                }
            }
            else {
                s2.q = l.w;
                if (n == -3 || n == -2) {
                    return;
                }
                super.q.q(s2);
            }
        }
    }
    
    protected void w(final cp cp) {
        super.u = true;
        super.i = true;
        super.r = -999;
        if (super.w.w(super.k) == null) {
            this.p(super.h);
        }
        else {
            this.p(super.k);
        }
        final bm bm = (bm)super.y.w(super.j);
        bm bm2 = (bm)super.y.w(super.g);
        if (bm == null || (bm.q == null && bm2.q != null)) {
            if (bm2 == null) {
                bm2 = (bm)super.y.q(0);
            }
            this.q(bm2);
            return;
        }
        this.q(bm);
    }
    
    private void u() {
        if (!super.u && !super.i) {
            if (super.q != null) {
                super.q.q().q();
            }
            int r;
            if (super.y.w(super.j) == null) {
                r = super.g;
            }
            else {
                r = super.j;
            }
            super.i = true;
            final bm bm;
            if ((bm = (bm)super.y.w(r)) != null && bm.w && bm.q != null && !this.q(40)) {
                super.r = -999;
                new bd(super.q.q(), this, bm).setVisible(true);
            }
            super.r = r;
        }
    }
    
    protected final void q(final as as, int i, final long n, final int n2) {
        final int r = as.r;
        final bm bm = (bm)super.y.w(i);
        final bm bm2 = (bm)super.y.w(as.r);
        this.u();
        if (as.a == super.a) {
            if (bm2 != null) {
                bm2.q = false;
            }
            bm.q = true;
            this.r = i;
            if (super.q != null) {
                if (!super.h) {
                    this.q(false, false);
                }
                super.q.q(bm);
                if (!super.q.isVisible()) {
                    super.q.setVisible(true);
                }
                super.q.q().validate();
            }
        }
        if (bm != null) {
            if (this.r == bm.a) {
                bm.q(this.q(bm.a));
            }
            if (bm2 != null && bm != null) {
                if (bm2.a != bm.a && (!as.q(23) || this.q(24)) && (!as.q(25) || this.q(32))) {
                    bm.q();
                }
            }
            else if (bm2 == null && bm != null && (!as.q(23) || this.q(24)) && (!as.q(25) || this.q(32))) {
                bm.q();
            }
            if (super.q != null) {
                super.q.w(bm);
            }
        }
        if (bm2 != null) {
            if (this.r == bm2.a) {
                bm2.q(this.q(bm2.a));
            }
            if (bm2 != null && bm != null && bm2.a != bm.a && (!as.q(23) || this.q(24)) && (!as.q(25) || this.q(32))) {
                bm2.w();
            }
            if (super.q != null) {
                super.q.w(bm2);
            }
        }
        final aw aw = (aw)super.w.w(as.e);
        if (i == this.r && as.r != i) {
            if (bm != null && !bm.q(57) && (!as.q(23) || this.q(24)) && (!as.q(25) || this.q(32))) {
                final s s;
                (s = new s(a.t.q(al.q("(This user has entered %1)"), new String[] { this.q(bm.o) }), as, false, false, aw, this)).q(n, n2);
                final int x = super.x;
                if (super.q != null) {
                    super.q.q(s);
                }
            }
        }
        else if (as.r == this.r && i != this.r && bm2 != null && bm != null && !bm2.q(57) && (!as.q(23) || this.q(24)) && (!as.q(25) || this.q(32))) {
            final s s2;
            (s2 = new s(a.t.q(al.q("(This user has moved to %1)"), new String[] { this.q(bm.o) }), as, false, false, aw, this)).q(n, n2);
            final int n3 = super.n;
            if (super.q != null) {
                super.q.q(s2);
            }
        }
        final String q;
        if (super.a == as.a && bm != null && ((bm2 != null && bm.a != bm2.a) || bm2 == null) && (q = bm.q) != null && !"".equals(q)) {
            final as as2;
            (as2 = new as(-999, bm.o)).i(bm.w());
            as2.y(bm.t());
            final s s3;
            (s3 = new s(q, as2, false, false, aw, this)).q(n, n2);
            s3.i = true;
            s3.o = bm.t();
            if (bm.f == 0) {
                s3.s = bm.w();
            }
            else {
                s3.s = bm.y();
            }
            if (super.q != null) {
                super.q.q(s3);
            }
        }
        as.r = i;
        if (super.q != null) {
            if (super.h || i == this.r) {
                super.q.q(as, true);
            }
            else if (!super.h && r == this.r) {
                super.q.q(as);
            }
        }
        if (!this.m) {
            synchronized (this) {
                this.m = true;
            }
            if (this.q != null) {
                for (i = 0; i < this.u.q; ++i) {
                    super.q.q((u)this.u.q(i));
                }
                super.q.q().q();
            }
        }
    }
    
    private void Y(final cp cp) {
        for (int i = 0; i < cp.w(); ++i) {
            l l;
            if ((l = (l)super.e.w(cp.q(i, 0))) == null && this.o() && cp.q(0, 3)) {
                (l = new l(-999, cp.q(0, 1))).e = cp.q(0, 1);
            }
            if (l == null) {
                (l = new l(-999, "Guest")).e = super.h;
            }
            if (l != null) {
                int n;
                if ((n = l.a) == super.a) {
                    n = cp.w;
                }
                if (!l.e) {
                    switch (cp.w) {
                        case -3:
                        case -2:
                        case -1: {
                            if (l.w) {
                                final int v = super.v;
                                break;
                            }
                            final int c = super.c;
                            break;
                        }
                        default: {
                            if (super.q.q(n)) {
                                final int c2 = super.c;
                                break;
                            }
                            final int b = super.b;
                            break;
                        }
                    }
                    if (cp.q() != null) {
                        this.q(cp.q(i, 0), l, cp.w, true, false, cp.q, 0, cp.q());
                    }
                    else {
                        this.q(cp.q(i, 0), l, cp.w, cp.q, true, cp.q, 0);
                    }
                }
            }
        }
    }
    
    private void U(final cp cp) {
        if (super.q != null) {
            for (int i = 0; i < cp.w(); ++i) {
                final int q = cp.q(i, 0);
                final int q2 = cp.q(i, 1);
                final int q3 = cp.q(i, 2);
                final int q4 = cp.q(i, 3);
                final int q5 = cp.q(i, 4);
                final s s;
                (s = new s(this.q(cp.q(i, 1)), this.q(cp.q(i, 0)), false, false, (aw)super.w.w(q), q2, false, false, -1, q3)).i = true;
                s.i = q3;
                s.o = q4;
                s.s = q5;
                s.q(cp.q, 0);
                super.q.q(s);
                if (cp.w == super.a) {
                    final int b = super.b;
                }
                else {
                    final int c = super.c;
                }
            }
        }
    }
    
    protected final void e(final cp cp) {
        this.u();
        for (int i = 0; i < cp.w(); ++i) {
            final int q = cp.q(i, 0);
            final int q2 = cp.q(i, 1);
            final int q3 = cp.q(i, 2);
            String s = cp.q(i, 0);
            final String q4 = cp.q(i, 1);
            if (s == null) {
                s = "";
            }
            if (q > 0) {
                if (cs.q()) {
                    s = ce.q(this.o, s, this.W);
                }
                else {
                    s = ce.q(this.o, s, this.I);
                }
            }
            final String q5 = a.I.q(s, "\r");
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
                s = string + a.I.q(s.substring(string.length()), "\n");
            }
            l l;
            if ((l = (l)super.e.w(q)) == null && this.o() && cp.q(0, 3)) {
                (l = new l(-999, q4)).e = q2;
            }
            if (l == null && q < -2147483638) {
                (l = new l(q, ChatNames.getSecurityName())).e = q;
            }
            if (l == null) {
                l = (l)this.r.w(q);
            }
            if (l != null) {
                int n2 = l.a;
                l.y(q3);
                if (n2 == super.a) {
                    n2 = cp.w;
                }
                if (cp.w != super.a && cp.q(0, 20)) {
                    return;
                }
                if (!l.e) {
                    switch (cp.w) {
                        case -3:
                        case -2:
                        case -1: {
                            if (l.w) {
                                final int v = super.v;
                                break;
                            }
                            final int c = super.c;
                            break;
                        }
                        default: {
                            if (super.q.q(n2)) {
                                final int c2 = super.c;
                                break;
                            }
                            final int b = super.b;
                            break;
                        }
                    }
                    if (cp.q() != null) {
                        this.q(s, l, cp.w, false, cp.q(0, 20), cp.q, 0, cp.q());
                    }
                    else {
                        this.q(s, l, cp.w, cp.q, false, cp.q, 0);
                    }
                }
            }
        }
    }
    
    private void I(final cp cp) {
        cp.q(-1);
        try {
            for (int i = 0; i < cp.w(); ++i) {
                final int q = cp.q(i, 0);
                u u = (u)super.u.w(q);
                if (cp.q(i, 63)) {
                    if (u != null) {
                        super.u.w(q);
                    }
                }
                else {
                    if (u == null) {
                        u = new u(q, cp.q(i, 0));
                        super.u.q(u);
                    }
                    else {
                        u.o = cp.q(i, 0);
                    }
                    cp.q(i, 1);
                    cp.q(i, 2);
                    u.w = cp.q(i, 1);
                    u.q = cp.q(i, 2);
                    cp.q(i, 3);
                    u.q(cp.q(i));
                    if (u.q == null) {
                        u.q = this.w("banners/" + u.q, true);
                    }
                    if (super.q != null && u.q != null) {
                        super.q.q(u);
                    }
                }
            }
            if (super.q != null) {
                super.q.q().q();
            }
        }
        finally {}
    }
    
    private void O(final cp cp) {
        for (int w = cp.w(), i = 0; i < w; ++i) {
            final R r;
            (r = new R(cp.q(i, 0), cp.q(i, 0))).q(cp.q(i));
            r.q = cp.q(i, 1);
            if (!r.q(63) && this.q.q(r.a) != r) {
                r.q = this.e("emoticons/" + r.q, true);
            }
            this.q.q(r);
        }
    }
    
    private void P(final cp cp) {
        for (int i = 0; i < cp.w(); ++i) {
            this.q.q(cp.q(i, 0));
        }
    }
    
    private void A(final cp cp) {
        if (!cr.q) {
            cr.q(this);
        }
        try {
            for (int i = 0; i < cp.w(); ++i) {
                final int q = cp.q(i, 0);
                aw aw = (aw)super.w.w(q);
                if (cp.q(i, 63)) {
                    if (aw != null) {
                        super.w.w(q);
                    }
                }
                else {
                    if (aw == null) {
                        aw = new aw(q, cp.q(i, 0));
                        super.w.q(aw);
                        aw.q = this.e("userIcons/" + aw.o, true);
                    }
                    else {
                        aw.o = cp.q(i, 0);
                        aw.q = this.e("userIcons/" + aw.o, true);
                    }
                    aw.q(cp.q(i));
                    final aw aw2 = aw;
                    int q2 = cp.q(i, 1);
                    final aw aw3 = aw2;
                    if (q2 == 0) {
                        q2 = -1;
                    }
                    aw3.q = q2;
                    if (cp.q(i, 62)) {
                        super.h = q;
                    }
                }
            }
        }
        finally {}
    }
    
    private void S(final cp cp) {
        try {
            for (int i = 0; i < cp.w(); ++i) {
                this.w.w(cp.q(i, 0));
            }
        }
        finally {}
    }
    
    private void D(final cp cp) {
        try {
            for (int i = 0; i < cp.w(); ++i) {
                final int q = cp.q(i, 0);
                final String q2 = cp.q(i, 1);
                bu bu = (bu)this.h.w(q);
                if (cp.q(i, 63)) {
                    if (bu != null) {
                        this.h.w(q);
                    }
                }
                else {
                    if (bu == null) {
                        bu = new bu(q, cp.q(i, 0));
                        this.h.q(bu);
                        bu.q = this.e("starpic/" + q2, true);
                    }
                    else {
                        bu.o = cp.q(i, 0);
                    }
                    bu.q(cp.q(i));
                }
            }
        }
        finally {}
    }
    
    private void F(final cp cp) {
        try {
            for (int i = 0; i < cp.w(); ++i) {
                this.h.w(cp.q(i, 0));
            }
        }
        finally {}
    }
    
    private void G(final cp cp) {
        for (int i = 0; i < cp.w(); ++i) {
            final int q = cp.q(i, 0);
            final int q2 = cp.q(i, 1);
            final as as;
            if ((as = (as)super.e.w(q)) != null) {
                this.q.q(as, false);
                this.q.put("" + q, "" + q2);
            }
        }
    }
    
    private void H(final cp cp) {
        if (super.q != null) {
            for (int i = 0; i < cp.w(); ++i) {
                final int q = cp.q(i, 0);
                bp bp = (bp)super.t.w(q);
                if (cp.q(i, 63)) {
                    if (bp != null) {
                        super.t.w(q);
                    }
                }
                else {
                    if (bp == null) {
                        bp = new bp(q, cp.q(i, 0));
                        super.t.q(bp);
                    }
                    else {
                        bp.o = cp.q(i, 0);
                    }
                    bp.q(cp.q(i));
                    bp.e = cp.q(i, 1);
                    bp.g = cp.q(i, 2);
                    bp.h = cp.q(i, 3);
                    bp.t = bS.q(null, bp.h);
                    bp.i(cp.q(i, 4));
                    bp.u(cp.q(i, 5));
                    bp.u = cp.q(i, 1);
                    bp.a = cp.q(i, 2);
                    bp.p = ((this.y.w(bp.g) != null) ? ((bx)this.y.w(bp.g)).o : "");
                    bp.e = ((this.w.w(bp.e) != null) ? ((aw)this.w.w(bp.e)).q : null);
                    bp.r = ((this.h.w(bp.t) != null) ? ((bu)this.h.w(bp.t)).q : null);
                }
            }
        }
    }
    
    private void J(final cp cp) {
        try {
            for (int i = 0; i < cp.w(); ++i) {
                this.t.w(cp.q(i, 0));
            }
        }
        finally {}
    }
    
    private void K(final cp cp) {
        for (int i = 0; i < cp.w(); ++i) {
            final l l;
            if ((l = (l)this.e.w(cp.q(i, 0))) != null) {
                l.q = cp.q(i, 1);
                l.w = cp.q(i, 2);
                l.q = cp.q(i, 1);
                l.w = cp.q(i, 2);
                l.e = cp.q(i, 3);
                l.r = cp.q(i, 4);
                l.t = cp.q(i, 5);
                cp.q(i, 6);
                l.q = cp.q(i, 0);
            }
        }
    }
    
    private void L(final cp cp) {
        this.u();
        try {
            for (int i = 0; i < cp.w(); ++i) {
                final int q = cp.q(i, 0);
                as as = (as)super.e.w(q);
                final bm bm = (bm)super.y.w(cp.q(i, 2));
                if (cp.q(i, 63)) {
                    if (as != null) {
                        as.q(cp.q(i));
                        if (bm != null) {
                            if ((!as.q(23) || this.q(24)) && (!as.q(25) || this.q(32))) {
                                bm.w();
                            }
                            if (super.q != null) {
                                super.q.w(bm);
                            }
                        }
                        final cF cf;
                        (cf = new cF(as.a, as.o)).t = false;
                        super.e.w(q);
                        if (super.q != null) {
                            super.q.q(as);
                            super.q.q(cf, false);
                        }
                        final bD bd;
                        if ((bd = (bD)super.q.w(as.a)) != null) {
                            bd.dispose();
                        }
                    }
                }
                else {
                    final String e = this.e(cp.q(i, 0));
                    if (as == null) {
                        (as = new as(q, e)).q(cp.q(i));
                        if ((!as.q(23) || this.q(24)) && (!as.q(25) || this.q(32))) {
                            super.e.q(as);
                        }
                        else {
                            this.r.q(as);
                        }
                    }
                    else {
                        as.q(cp.q(i));
                        if ((as.q(23) && !this.q(24)) || (as.q(25) && !this.q(32))) {
                            final as as2 = as;
                            final bm bm2 = bm;
                            final as as3 = as2;
                            if (as3 != null) {
                                if (bm2 != null) {
                                    bm2.w();
                                    if (super.q != null) {
                                        super.q.w(bm2);
                                    }
                                }
                                final cF cf2;
                                (cf2 = new cF(as3.a, as3.o)).t = false;
                                if (super.q != null) {
                                    super.q.q(as3);
                                    super.q.q(cf2, false);
                                }
                                final bD bd2;
                                if ((bd2 = (bD)super.q.w(as3.a)) != null) {
                                    bd2.dispose();
                                }
                            }
                            this.r.q(as);
                        }
                        as.o = e;
                    }
                    as.e = cp.q(i, 1);
                    as.q = (aw)super.w.w(as.e);
                    as.r = cp.q(i, 2);
                    as.i(cp.q(i, 3));
                    as.u(cp.q(i, 4));
                    as.t = cp.q(i, 5);
                    final as as4 = as;
                    final B h = super.h;
                    final as as5 = as4;
                    if (as4.t == 0 && as5.q(62)) {
                        as5.t = 1000;
                    }
                    as5.q = (bu)h.w(as5.t);
                    as.o = cp.q(i, 6);
                    final int u = as.u;
                    final int q2;
                    final int u2 = (q2 = cp.q(i, 7)) & 0xFF;
                    as.y = (q2 >> 16 & 0xFF);
                    as.u = u2;
                    final as as6 = as;
                    final int q3 = cp.q(i, 8);
                    final as as7 = as6;
                    if (q3 == -16777216) {
                        as7.i = 0;
                    }
                    else {
                        as7.i = q3;
                    }
                    as.p = cp.q(i, 9);
                    final as as8 = as;
                    final int n = i;
                    if (10 >= cp.e() - 1) {
                        throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + 10);
                    }
                    as8.q = (cp.q(n, 10) << 32) + (cp.q(n, 11) & 0xFFFFFFFFL);
                    final String q4 = cp.q(i, 1);
                    final String q5 = cp.q(i, 2);
                    if (q4 != null) {
                        as.y = q4;
                    }
                    if (q5 != null) {
                        as.u = q5;
                    }
                    if (cp.q(i, 3) != null) {
                        as.i = cp.q(i, 3);
                        if (as.w == null && this.q(41) && as.i.length() == 2) {
                            as.w = this.e("flags/" + as.i + ".gif", false);
                        }
                    }
                    if (q == super.a) {
                        super.o = as.o;
                        if (as.e != -999) {
                            super.k = as.e;
                        }
                    }
                    Label_1176: {
                        if (as.q(61)) {
                            final as as9 = as;
                            final String o = as.o;
                            final as as10 = as9;
                            if (o.length() > 4) {
                                if (!o.endsWith(".gif") && !o.endsWith(".jpg")) {
                                    if (!o.endsWith(".bmp")) {
                                        break Label_1176;
                                    }
                                }
                                try {
                                    as10.q = this.q("NickPic/" + o, true);
                                }
                                catch (Exception ex) {}
                            }
                        }
                    }
                    final cF cf3;
                    (cf3 = new cF(as.a, as.o)).t = true;
                    cf3.e = as.e;
                    cf3.q = (aw)super.w.w(as.e);
                    cf3.r = as.r;
                    cf3.q(as.q());
                    if (super.q != null) {
                        super.q.q(as, false);
                        super.q.q(cf3, false);
                    }
                    if (as.u == 7 && (u == 8 || u == 9)) {
                        this.q(as, as.r, 0L, 0);
                    }
                    if (super.a == as.a) {
                        this.w(as);
                    }
                }
            }
        }
        finally {}
    }
    
    private void w(final String s, final int n) {
        (this.q = new Socket(s, n)).setTcpNoDelay(true);
        this.eos = new a(new BufferedOutputStream(this.q.getOutputStream(), 16));
        this.eis = new bz(new BufferedInputStream(this.q.getInputStream(), 256));
        try {
            final a eos = this.eos;
            final byte[] q = G.q;
            final a a = eos;
            eos.q = q;
            a.q = 0;
            final bz eis = this.eis;
            final byte[] q2 = G.q;
            final bz bz = eis;
            eis.q = q2;
            bz.q = 0;
        }
        catch (Exception ex) {
            throw new StreamCorruptedException();
        }
    }
    
    public final void q(final s s, final l l) {
        final int a;
        if ((a = l.a) <= 0) {
            return;
        }
        if (l.r > 0 && ((bx)super.y.w(l.r)).q(61) && l.q(59)) {
            return;
        }
        aI ai;
        if ((ai = (aI)super.q.w(a)) == null) {
            ai = new bD(super.q.q(), this, l);
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
            ai.setBounds(n4 + 2, n3 + 10, n, n2);
            super.q.q(ai, a);
        }
        if (s != null) {
            ai.q(s);
        }
        ai.setVisible(true);
    }
    
    public final void e() {
        try {
            if (this.z > 0) {
                this.w((a.h.q().w != null) ? a.h.q().w : this.s, bl.q(0));
                this.q(this.z, true);
                this.g = false;
                (this.q = new f((bq)this)).start();
            }
        }
        catch (Exception ex) {}
    }
    
    private void p(final int n) {
        final cp cp;
        (cp = new cp(67334, 1)).q(0, 0, super.a);
        cp.q(0, 1, n);
        cp.q(0, 0, super.o);
        cp.w = -1;
        cp.q = -1;
        this.r(cp);
    }
    
    public final void q(final boolean b) {
        super.p = true;
    }
    
    public final void w(final int n) {
        if (super.q.q() != null) {
            super.q.q().setCursor(3);
        }
        if (this.q == null) {
            this.q = new cw(super.q.q(), this);
        }
        final cw q = this.q;
        final int n2 = 0;
        final cw cw = q;
        if (q.q) {
            ((aS)cw.q).q(n2);
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
    
    public boolean q(final cj cj) {
        if (this.e.get(cj.q()) == null) {
            this.e.put(cj.q(), cj);
            return true;
        }
        return false;
    }
    
    public final void q(final l l) {
        this.e.remove(l);
    }
    
    public int q(final cj cj) {
        this.q(cj.q());
        if (super.e.w(cj.q().a) == null) {
            cj.r();
        }
        final byte b2;
        final byte b = ((b2 = (byte)new Random().nextInt()) >= 0) ? b2 : ((byte)(-b2));
        final cp cp;
        (cp = new cp(50400768, 1)).q(0, 0, super.a);
        cp.q(0, 1, b);
        cp.q(0, 0, cj.q());
        cp.q(0, 1, cj.w());
        cp.w = cj.q().a;
        cp.q = -1;
        this.r(cp);
        final cp cp2;
        (cp2 = new cp(66305, 1)).q(0, 0, "[File Transfer Requested: file name " + cj.q() + ", size " + r(cj.w()) + ". Please click this message to accept.]");
        cp2.q(0, 0, super.a);
        cp2.q(0, 20);
        cp2.q(new byte[] { b });
        cp2.w = cj.q().a;
        cp2.q = -1;
        this.r(cp2);
        this.w.put(new Integer(b), cj);
        return b;
    }
    
    public final void e(final int n) {
        synchronized (this.w) {
            final bW value;
            if ((value = this.w.get(new Integer(n))) == null) {
                return;
            }
            if (value instanceof bW) {
                if (super.e.w(value.q.a) == null) {
                    this.w.remove(new Integer(n));
                }
                else {
                    this.w.put(new Integer(n), bU.q(this, value));
                }
            }
        }
    }
    
    public final void q(final int n, final l l) {
        final cp cp;
        (cp = new cp(50400770, 1)).q(0, 0, super.a);
        cp.q(0, 1, n);
        cp.w = l.a;
        cp.q = -1;
        this.r(cp);
    }
    
    public final void r(final int n) {
        final cj cj;
        if ((cj = this.w.remove(new Integer(n))) == null) {
            return;
        }
        final cp cp;
        (cp = new cp(50400769, 1)).q(0, 0, super.a);
        cp.q(0, 1, n);
        cp.w = cj.q().a;
        cp.q = -1;
        this.r(cp);
        final cp cp2;
        (cp2 = new cp(66305, 1)).q(0, 0, "[File Transfer Cancelled: file name " + cj.q() + ", size " + r(cj.w()) + ".]");
        cp2.q(0, 0, super.a);
        cp2.q(0, 20);
        cp2.q(new byte[] { (byte)n });
        cp2.w = cj.q().a;
        cp2.q = -1;
        this.r(cp2);
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
    
    public final B q() {
        return this.h;
    }
    
    public static boolean e() {
        return System.getProperty("java.vendor").startsWith("Sun");
    }
}
