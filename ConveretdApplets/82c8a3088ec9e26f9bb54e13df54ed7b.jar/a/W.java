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
    private cx q;
    private Hashtable w;
    private Hashtable e;
    private aU q;
    
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
                ((aO)super.f.q(j)).dispose();
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
        final ae q2;
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
                    final ar ar;
                    if ((ar = (ar)w.e.w(cp2.q(i, 0))) != null) {
                        final String q = a.s.q(ak.q("(This user has left %1)"), new String[] { cs.e });
                        final av av = (av)w.w.w(ar.e);
                        final bl bl = (bl)w.y.w(ar.r);
                        final cG cg;
                        (cg = new cG(ar.s, ar.o)).t = false;
                        if (bl != null) {
                            if (ar.r == w.r && !bl.q(57) && (!ar.q(23) || w.q(24)) && (!ar.q(25) || w.q(32))) {
                                final String q2;
                                String string;
                                if ((q2 = cp2.q(i, 0)) != null) {
                                    string = w.q(q2) + " " + q;
                                }
                                else {
                                    string = q;
                                }
                                final r r = new r(string, ar, false, false, av, w);
                                r.q(cp2.q, 0);
                                final int m = w.m;
                                if (w.q != null) {
                                    w.q.q(r);
                                }
                            }
                            if ((!ar.q(23) || w.q(24)) && (!ar.q(25) || w.q(32))) {
                                ar.r = -999;
                                bl.w();
                            }
                            if (w.q != null) {
                                w.q.w(bl);
                            }
                        }
                        if (w.q != null) {
                            w.q.q(ar);
                            w.q.q(cg, false);
                        }
                        final bC bc;
                        if ((bc = (bC)w.q.w(ar.s)) != null) {
                            if (ar.s == w.s) {
                                bc.dispose();
                            }
                            else {
                                final r r2 = new r(q, ar, false, false, av, w);
                                r2.q(cp2.q, 0);
                                bc.q(r2);
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
                    (cp3 = new cp(67073, 1)).w = l.s;
                    cp3.q(0, 0, super.s);
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
                super.s = cp4.w;
                this.q(cp4.q(0));
                W.p = cp4.q(0, 0);
                if (cs.q == null) {
                    cs.q = this.w("icon_display.gif", false);
                }
                if (!"Admin".equals(W.p)) {
                    if (cs.r == null) {
                        cs.r = this.w(aS.w.e() + "background.gif", true);
                    }
                    if (cs.t == null) {
                        cs.t = this.w(aS.w.e() + "chatbackground.gif", true);
                    }
                    if (a.h.q().q) {
                        (super.q = new aZ(this, null)).setVisible(false);
                        a.h.q().q.setVisible(false);
                        a.h.q().add(super.q.q());
                        ((aZ)super.q).validate();
                    }
                    else if (cs.w()) {
                        final long currentTimeMillis2 = System.currentTimeMillis();
                        Object o;
                        if (!bL.q()) {
                            o = new cd(this);
                        }
                        else {
                            o = new aQ(this);
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
                this.w(cp);
            }
            case 67843: {
                final cp cp5 = cp;
                this.r = cp5.q(0);
                this.Q = cp5.q(0, 1);
                cs.q = cp5.q(0, 2);
                if (this.q != null && ((aZ)this.q).getParent() != null && ((aq)((aZ)this.q).getParent()).q != null) {
                    ((aq)((aZ)this.q).getParent()).q.u();
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
                this.R = cp6.q(0, 4);
                this.U = cp6.q(0, 5);
                this.O = cp6.q(0, 6);
                this.P = cp6.q(0, 7);
                this.A = cp6.q(0, 9);
                this.I = cp6.q(0, 10);
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
                W.p = cp.q(0, 6);
                final cl q4;
                if ((q4 = a.h.q().q).q != null) {
                    q4.q = q4.q.getImage(q4.q.getCodeBase(), "Resources/" + co.p + "/" + q4.q);
                }
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
        final ae q;
        (q = cs.q).q();
        q.q(0);
        try {
            for (int i = 0; i < cp.w(); ++i) {
                final int q2 = cp.q(i, 0);
                final String q3 = cp.q(i, 0);
                as as = (as)super.j.w(q2);
                if (cp.q(i, 63)) {
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
                    as.q(cp.q(i));
                    super.j.q(as);
                    as.q = cp.q(i, 1);
                    as.p(cp.q(i, 2));
                    as.o(cp.q(i, 3));
                    cp.q(i, 4);
                    as.w = cp.q(i, 5);
                    as.q = cp.q(i, 1);
                    if (as.q == null || as.q.trim().equals("")) {
                        as.p(aS.w.b.getRGB());
                        as.o(aS.w.n.getRGB());
                        as.w = aS.w.r;
                        as.q = aS.w.w;
                    }
                    cs.q.q = as.q(4);
                    final ae q4 = cs.q;
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
                        cs.q.q(as2.o, as2 = as);
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
            final as as;
            if ((as = (as)this.j.w(q2)) != null) {
                cs.q.q(q, as);
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
            this.k.q(new aX(n, cp.q(i, 0)));
            ++n;
        }
    }
    
    private static void E(final cp cp) {
        try {
            for (int i = 0; i < cp.w(); ++i) {
                final int q = cp.q(i, 0);
                an an = (an)W.d.w(q);
                if (cp.q(i, 63)) {
                    if (an != null) {
                        W.d.w(q);
                    }
                }
                else {
                    if (an == null) {
                        an = new an(q, cp.q(i, 0));
                        W.d.q(an);
                    }
                    else {
                        an.o = cp.q(i, 0);
                    }
                    an.q(cp.q(i));
                    an.p(cp.q(i, 1));
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
    
    private void T(final cp cp) {
        for (int i = 0; i < cp.w(); ++i) {
            ar ar;
            if ((ar = (ar)this.e.w(cp.q(i, 0))) == null) {
                ar = new ar(cp.q(i, 0), cp.q(i, 0));
            }
            ar.p(cp.q(i, 1));
            this.e.q(ar);
            super.q.q(ar, false);
        }
    }
    
    public final void q(final String s, final int n, final int n2) {
        final cp cp;
        (cp = new cp(66310, 1)).q(0, 0, n);
        cp.q(0, 1, n2);
        cp.q(0, 2, super.s);
        cp.q(0, 0, s);
        this.r(cp);
    }
    
    public final void q(final String s, final int n, final int n2, final int n3) {
        final cp cp;
        (cp = new cp(66310, 1)).q(0, 0, n);
        cp.q(0, 1, n2);
        cp.q(0, 2, super.s);
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
                if (bD.e < 66048) {
                    if (bD.e) {
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
                for (int n2 = 0; url == 0 && n2 < bk.q.size(); ++n2) {
                    final int q2 = bk.q(n2);
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
                        if (n2 == bk.q.size() - 1) {
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
                    (super.q = new f((bp)this)).start();
                }
                if (this.q == null) {
                    (this.q = new aU(this)).start();
                }
                if (this.w == null) {
                    this.w = new az("global");
                }
                if (this.q == null || this.W != n) {
                    this.q = new az("" + n);
                }
                final cp cp;
                (cp = new cp(65793, 1)).q(0, 0, n);
                cp.q(0, 1, super.k);
                cp.q(0, 0, o);
                cp.q(0, 1, "en");
                cp.q(0, 2, s2);
                cp.q(0, 3, s);
                cp.q(0, 4, (String)bm.q(new Object[] { "location.href" }, null));
                cp.q(0, 0, ck);
                if (!this.u() && super.k != -999) {
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
                if (super.o && (!bD.w || bD.w != 1)) {
                    cp.q(0, 21);
                    cp.q(0, 20);
                }
                this.r(cp);
            }
            else {
                this.r();
                new bR(super.q, "Error", s.q(ak.q("No response was received from the server.  The %1 Server may not be running, or may not be a compatible version. Please contact the administrator in charge of the site."), new String[] { cs.e }), this).setVisible(true);
            }
        }
        catch (UnknownHostException ex2) {
            this.r();
            new bR(super.q, s.q(ak.q("%1 could not locate the specified host. Please make sure you are using the correct host name."), new String[] { cs.e }), ex2, this).setVisible(true);
        }
        catch (InterruptedIOException ex3) {
            this.r();
            new bR(super.q, s.q(ak.q("No response was received from the server.  The %1 Server may not be running, or may not be a compatible version. Please contact the administrator in charge of the site."), new String[] { cs.e }), ex3, this).setVisible(true);
        }
        catch (NoRouteToHostException ex4) {
            this.r();
            new bR(super.q, s.q(ak.q("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { cs.e }), ex4, this).setVisible(true);
        }
        catch (SecurityException ex5) {
            this.r();
            new bR(super.q, s.q(ak.q("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { cs.e }), ex5, this).setVisible(true);
        }
        catch (IOException ex6) {
            this.r();
            new bR(super.q, s.q(ak.q("%1 could not connect to the specified host.  Please verify that the %1 Server is running and that you are using the correct host name."), new String[] { cs.e }), ex6, this).setVisible(true);
        }
        catch (Exception ex7) {
            this.r();
            new bR(super.q, s.q(ak.q("An unknown error occurred while connecting to the %1 Server.  Please contact the administrator in charge of the site."), new String[] { cs.e }), ex7, this).setVisible(true);
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
                final bC bc;
                if ((bc = (bC)super.q.w(s2)) != null) {
                    bc.q(r);
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
    
    protected void w(final cp cp) {
        super.u = true;
        super.i = true;
        super.r = -999;
        if (super.w.w(super.l) == null) {
            this.s(super.j);
        }
        else {
            this.s(super.l);
        }
        final bl bl = (bl)super.y.w(super.k);
        bl bl2 = (bl)super.y.w(super.h);
        if (bl == null || (bl.q == null && bl2.q != null)) {
            if (bl2 == null) {
                bl2 = (bl)super.y.q(0);
            }
            this.q(bl2);
            return;
        }
        this.q(bl);
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
            final bl bl;
            if ((bl = (bl)super.y.w(r)) != null && bl.w && bl.q != null && !this.q(40)) {
                super.r = -999;
                new bc(super.q.q(), this, bl).setVisible(true);
            }
            super.r = r;
        }
    }
    
    protected final void q(final ar ar, int i, final long n, final int n2) {
        final int r = ar.r;
        final bl bl = (bl)super.y.w(i);
        final bl bl2 = (bl)super.y.w(ar.r);
        this.u();
        if (ar.s == super.s) {
            if (bl2 != null) {
                bl2.q = false;
            }
            bl.q = true;
            this.r = i;
            if (super.q != null) {
                if (!super.h) {
                    this.q(false, false);
                }
                super.q.q(bl);
                if (!super.q.isVisible()) {
                    super.q.setVisible(true);
                }
                super.q.q().validate();
            }
        }
        if (bl != null) {
            if (this.r == bl.s) {
                bl.q(this.q(bl.s));
            }
            if (bl2 != null && bl != null) {
                if (bl2.s != bl.s && (!ar.q(23) || this.q(24)) && (!ar.q(25) || this.q(32))) {
                    bl.q();
                }
            }
            else if (bl2 == null && bl != null && (!ar.q(23) || this.q(24)) && (!ar.q(25) || this.q(32))) {
                bl.q();
            }
            if (super.q != null) {
                super.q.w(bl);
            }
        }
        if (bl2 != null) {
            if (this.r == bl2.s) {
                bl2.q(this.q(bl2.s));
            }
            if (bl2 != null && bl != null && bl2.s != bl.s && (!ar.q(23) || this.q(24)) && (!ar.q(25) || this.q(32))) {
                bl2.w();
            }
            if (super.q != null) {
                super.q.w(bl2);
            }
        }
        final av av = (av)super.w.w(ar.e);
        if (i == this.r && ar.r != i) {
            if (bl != null && !bl.q(57) && (!ar.q(23) || this.q(24)) && (!ar.q(25) || this.q(32))) {
                final r r2;
                (r2 = new r(a.s.q(ak.q("(This user has entered %1)"), new String[] { this.q(bl.o) }), ar, false, false, av, this)).q(n, n2);
                final int c = super.c;
                if (super.q != null) {
                    super.q.q(r2);
                }
            }
        }
        else if (ar.r == this.r && i != this.r && bl2 != null && bl != null && !bl2.q(57) && (!ar.q(23) || this.q(24)) && (!ar.q(25) || this.q(32))) {
            final r r3;
            (r3 = new r(a.s.q(ak.q("(This user has moved to %1)"), new String[] { this.q(bl.o) }), ar, false, false, av, this)).q(n, n2);
            final int m = super.m;
            if (super.q != null) {
                super.q.q(r3);
            }
        }
        final String q;
        if (super.s == ar.s && bl != null && ((bl2 != null && bl.s != bl2.s) || bl2 == null) && (q = bl.q) != null && !"".equals(q)) {
            final ar ar2;
            (ar2 = new ar(-999, bl.o)).p(bl.w());
            ar2.i(bl.t());
            final r r4;
            (r4 = new r(q, ar2, false, false, av, this)).q(n, n2);
            r4.i = true;
            r4.o = bl.t();
            if (bl.g == 0) {
                r4.s = bl.w();
            }
            else {
                r4.s = bl.y();
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
    
    private void Y(final cp cp) {
        for (int i = 0; i < cp.w(); ++i) {
            l l;
            if ((l = (l)super.e.w(cp.q(i, 0))) == null && this.o() && cp.q(0, 3)) {
                (l = new l(-999, cp.q(0, 1))).e = cp.q(0, 1);
            }
            if (l == null) {
                (l = new l(-999, "Guest")).e = super.j;
            }
            if (l != null) {
                int n;
                if ((n = l.s) == super.s) {
                    n = cp.w;
                }
                if (!l.e) {
                    switch (cp.w) {
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
                final r r;
                (r = new r(this.q(cp.q(i, 1)), this.q(cp.q(i, 0)), false, false, (av)super.w.w(q), q2, false, false, -1, q3)).i = true;
                r.i = q3;
                r.o = q4;
                r.s = q5;
                r.q(cp.q, 0);
                super.q.q(r);
                if (cp.w == super.s) {
                    final int n = super.n;
                }
                else {
                    final int v = super.v;
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
                    s = ce.q(this.o, s, this.E);
                }
                else {
                    s = ce.q(this.o, s, this.O);
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
                int n2 = l.s;
                l.i(q3);
                if (n2 == super.s) {
                    n2 = cp.w;
                }
                if (cp.w != super.s && cp.q(0, 20)) {
                    return;
                }
                if (!l.e) {
                    switch (cp.w) {
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
                t t = (t)super.u.w(q);
                if (cp.q(i, 63)) {
                    if (t != null) {
                        super.u.w(q);
                    }
                }
                else {
                    if (t == null) {
                        t = new t(q, cp.q(i, 0));
                        super.u.q(t);
                    }
                    else {
                        t.o = cp.q(i, 0);
                    }
                    cp.q(i, 1);
                    cp.q(i, 2);
                    t.w = cp.q(i, 1);
                    t.q = cp.q(i, 2);
                    cp.q(i, 3);
                    t.q(cp.q(i));
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
    
    private void O(final cp cp) {
        for (int w = cp.w(), i = 0; i < w; ++i) {
            final Q q;
            (q = new Q(cp.q(i, 0), cp.q(i, 0))).q(cp.q(i));
            q.q = cp.q(i, 1);
            if (!q.q(63) && this.q.q(q.s) != q) {
                q.q = this.e("emoticons/" + q.q, true);
            }
            this.q.q(q);
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
                av av = (av)super.w.w(q);
                if (cp.q(i, 63)) {
                    if (av != null) {
                        super.w.w(q);
                    }
                }
                else {
                    if (av == null) {
                        av = new av(q, cp.q(i, 0));
                        super.w.q(av);
                        av.q = this.e("userIcons/" + av.o, true);
                    }
                    else {
                        av.o = cp.q(i, 0);
                        av.q = this.e("userIcons/" + av.o, true);
                    }
                    av.q(cp.q(i));
                    final av av2 = av;
                    int q2 = cp.q(i, 1);
                    final av av3 = av2;
                    if (q2 == 0) {
                        q2 = -1;
                    }
                    av3.q = q2;
                    if (cp.q(i, 62)) {
                        super.j = q;
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
                bt bt = (bt)this.h.w(q);
                if (cp.q(i, 63)) {
                    if (bt != null) {
                        this.h.w(q);
                    }
                }
                else {
                    if (bt == null) {
                        bt = new bt(q, cp.q(i, 0));
                        this.h.q(bt);
                        bt.q = this.e("starpic/" + q2, true);
                    }
                    else {
                        bt.o = cp.q(i, 0);
                    }
                    bt.q(cp.q(i));
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
            final ar ar;
            if ((ar = (ar)super.e.w(q)) != null) {
                this.q.q(ar, false);
                this.q.put("" + q, "" + q2);
            }
        }
    }
    
    private void H(final cp cp) {
        if (super.q != null) {
            for (int i = 0; i < cp.w(); ++i) {
                final int q = cp.q(i, 0);
                bo bo = (bo)super.t.w(q);
                if (cp.q(i, 63)) {
                    if (bo != null) {
                        super.t.w(q);
                    }
                }
                else {
                    if (bo == null) {
                        bo = new bo(q, cp.q(i, 0));
                        super.t.q(bo);
                    }
                    else {
                        bo.o = cp.q(i, 0);
                    }
                    bo.q(cp.q(i));
                    bo.e = cp.q(i, 1);
                    bo.h = cp.q(i, 2);
                    bo.j = cp.q(i, 3);
                    bo.t = bS.q(null, bo.j);
                    bo.p(cp.q(i, 4));
                    bo.o(cp.q(i, 5));
                    bo.u = cp.q(i, 1);
                    bo.a = cp.q(i, 2);
                    bo.p = ((this.y.w(bo.h) != null) ? ((bw)this.y.w(bo.h)).o : "");
                    bo.e = ((this.w.w(bo.e) != null) ? ((av)this.w.w(bo.e)).q : null);
                    bo.r = ((this.h.w(bo.t) != null) ? ((bt)this.h.w(bo.t)).q : null);
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
                ar ar = (ar)super.e.w(q);
                final bl bl = (bl)super.y.w(cp.q(i, 2));
                if (cp.q(i, 63)) {
                    if (ar != null) {
                        ar.q(cp.q(i));
                        if (bl != null) {
                            if ((!ar.q(23) || this.q(24)) && (!ar.q(25) || this.q(32))) {
                                bl.w();
                            }
                            if (super.q != null) {
                                super.q.w(bl);
                            }
                        }
                        final cG cg;
                        (cg = new cG(ar.s, ar.o)).t = false;
                        super.e.w(q);
                        if (super.q != null) {
                            super.q.q(ar);
                            super.q.q(cg, false);
                        }
                        final bC bc;
                        if ((bc = (bC)super.q.w(ar.s)) != null) {
                            bc.dispose();
                        }
                    }
                }
                else {
                    final String e = this.e(cp.q(i, 0));
                    if (ar == null) {
                        (ar = new ar(q, e)).q(cp.q(i));
                        if ((!ar.q(23) || this.q(24)) && (!ar.q(25) || this.q(32))) {
                            super.e.q(ar);
                        }
                        else {
                            this.r.q(ar);
                        }
                    }
                    else {
                        ar.q(cp.q(i));
                        if ((ar.q(23) && !this.q(24)) || (ar.q(25) && !this.q(32))) {
                            final ar ar2 = ar;
                            final bl bl2 = bl;
                            final ar ar3 = ar2;
                            if (ar3 != null) {
                                if (bl2 != null) {
                                    bl2.w();
                                    if (super.q != null) {
                                        super.q.w(bl2);
                                    }
                                }
                                final cG cg2;
                                (cg2 = new cG(ar3.s, ar3.o)).t = false;
                                if (super.q != null) {
                                    super.q.q(ar3);
                                    super.q.q(cg2, false);
                                }
                                final bC bc2;
                                if ((bc2 = (bC)super.q.w(ar3.s)) != null) {
                                    bc2.dispose();
                                }
                            }
                            this.r.q(ar);
                        }
                        ar.o = e;
                    }
                    ar.e = cp.q(i, 1);
                    ar.q = (av)super.w.w(ar.e);
                    ar.r = cp.q(i, 2);
                    ar.p(cp.q(i, 3));
                    ar.o(cp.q(i, 4));
                    ar.t = cp.q(i, 5);
                    final ar ar4 = ar;
                    final A h = super.h;
                    final ar ar5 = ar4;
                    if (ar4.t == 0 && ar5.q(62)) {
                        ar5.t = 1000;
                    }
                    ar5.q = (bt)h.w(ar5.t);
                    ar.p = cp.q(i, 6);
                    final int u = ar.u;
                    final int q2;
                    final int u2 = (q2 = cp.q(i, 7)) & 0xFF;
                    ar.y = (q2 >> 16 & 0xFF);
                    ar.u = u2;
                    ar.b_(cp.q(i, 8));
                    ar.a = cp.q(i, 9);
                    final ar ar6 = ar;
                    final int n = i;
                    if (10 >= cp.e() - 1) {
                        throw new ArrayIndexOutOfBoundsException("index is out-of-bounds: " + 10);
                    }
                    ar6.q = (cp.q(n, 10) << 32) + (cp.q(n, 11) & 0xFFFFFFFFL);
                    ar.w(cp.q(i, 12));
                    final String q3 = cp.q(i, 1);
                    final String q4 = cp.q(i, 2);
                    if (q3 != null) {
                        ar.y = q3;
                    }
                    if (q4 != null) {
                        ar.u = q4;
                    }
                    if (cp.q(i, 3) != null) {
                        ar.i = cp.q(i, 3);
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
                    final cG cg3;
                    (cg3 = new cG(ar.s, ar.o)).t = true;
                    cg3.e = ar.e;
                    cg3.q = (av)super.w.w(ar.e);
                    cg3.r = ar.r;
                    cg3.q(ar.q());
                    if (super.q != null) {
                        super.q.q(ar, false);
                        super.q.q(cg3, false);
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
        this.eis = new by(new BufferedInputStream(this.q.getInputStream(), 256));
        try {
            final a eos = this.eos;
            final byte[] q = F.q;
            final a a = eos;
            eos.q = q;
            a.q = 0;
            final by eis = this.eis;
            final byte[] q2 = F.q;
            final by by = eis;
            eis.q = q2;
            by.q = 0;
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
        if (l.r > 0 && ((bw)super.y.w(l.r)).q(61) && l.q(59)) {
            return;
        }
        aH ah;
        if ((ah = (aH)super.q.w(s)) == null) {
            ah = new bC(super.q.q(), this, l);
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
                this.w((a.h.q().w != null) ? a.h.q().w : this.s, bk.q(0));
                this.q(this.x, true);
                this.g = false;
                (this.q = new f((bp)this)).start();
            }
        }
        catch (Exception ex) {}
    }
    
    private void s(final int n) {
        final cp cp;
        (cp = new cp(67334, 1)).q(0, 0, super.s);
        cp.q(0, 1, n);
        cp.q(0, 0, super.o);
        cp.w = -1;
        cp.q = -1;
        this.r(cp);
    }
    
    public final void q(final boolean b) {
        super.p = true;
    }
    
    public final void r(final int n) {
        if (super.q.q() != null) {
            super.q.q().setCursor(3);
        }
        if (this.q == null) {
            this.q = new cx(super.q.q(), this);
        }
        final cx q = this.q;
        final int n2 = 0;
        final cx cx = q;
        if (q.q) {
            ((aR)cx.q).q(n2);
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
        if (super.e.w(cj.q().s) == null) {
            cj.r();
        }
        final byte b2;
        final byte b = ((b2 = (byte)new Random().nextInt()) >= 0) ? b2 : ((byte)(-b2));
        final cp cp;
        (cp = new cp(50400768, 1)).q(0, 0, super.s);
        cp.q(0, 1, b);
        cp.q(0, 0, cj.q());
        cp.q(0, 1, cj.w());
        cp.w = cj.q().s;
        cp.q = -1;
        this.r(cp);
        final cp cp2;
        (cp2 = new cp(66305, 1)).q(0, 0, "[File Transfer Requested: file name " + cj.q() + ", size " + r(cj.w()) + ". Please click this message to accept.]");
        cp2.q(0, 0, super.s);
        cp2.q(0, 20);
        cp2.q(new byte[] { b });
        cp2.w = cj.q().s;
        cp2.q = -1;
        this.r(cp2);
        this.w.put(new Integer(b), cj);
        return b;
    }
    
    public final void t(final int n) {
        synchronized (this.w) {
            final bW value;
            if ((value = this.w.get(new Integer(n))) == null) {
                return;
            }
            if (value instanceof bW) {
                if (super.e.w(value.q.s) == null) {
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
        (cp = new cp(50400770, 1)).q(0, 0, super.s);
        cp.q(0, 1, n);
        cp.w = l.s;
        cp.q = -1;
        this.r(cp);
    }
    
    public final void y(final int n) {
        final cj cj;
        if ((cj = this.w.remove(new Integer(n))) == null) {
            return;
        }
        final cp cp;
        (cp = new cp(50400769, 1)).q(0, 0, super.s);
        cp.q(0, 1, n);
        cp.w = cj.q().s;
        cp.q = -1;
        this.r(cp);
        final cp cp2;
        (cp2 = new cp(66305, 1)).q(0, 0, "[File Transfer Cancelled: file name " + cj.q() + ", size " + r(cj.w()) + ".]");
        cp2.q(0, 0, super.s);
        cp2.q(0, 20);
        cp2.q(new byte[] { (byte)n });
        cp2.w = cj.q().s;
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
    
    public final A q() {
        return this.h;
    }
    
    public static boolean e() {
        return System.getProperty("java.vendor").startsWith("Sun");
    }
}
