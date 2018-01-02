// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.Random;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.InputStream;
import java.io.DataInputStream;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;
import com.spilka.client.muc.ChatNames;
import java.awt.Component;
import java.net.NoRouteToHostException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.io.IOException;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import com.spilka.client.muc.AppletAbstract;
import java.awt.Frame;
import java.util.Enumeration;
import java.awt.Dialog;
import java.util.Hashtable;

public abstract class cV extends cU
{
    private dm q;
    private Hashtable w;
    private Hashtable e;
    private cW q;
    
    public void r() {
        this.w.e();
        this.u.e();
        this.i.e();
        this.o.e();
        this.a.e();
        cV.f.e();
        this.h.e();
        this.j.e();
        this.k.e();
        this.t.e();
        this.q.w();
        this.m = false;
        if (this.q != null) {
            this.q.interrupt();
        }
        this.q = null;
    }
    
    public void e() {
        this.s = false;
        this.W = false;
        this.f = false;
        this.d = false;
        this.e.e();
        this.y.e();
        this.R = this.v;
        cV.a = "";
        if (super.q != null) {
            for (int i = 0; i < super.q.q(); ++i) {
                ((cZ)super.q.q(i)).dispose();
            }
        }
        if (super.g != null) {
            for (int j = 0; j < super.g.q(); ++j) {
                ((M)super.g.q(j)).dispose();
            }
        }
        final Enumeration<Dialog> elements = this.e.elements();
        while (elements.hasMoreElements()) {
            final Dialog nextElement;
            if ((nextElement = elements.nextElement()) instanceof Dialog) {
                nextElement.dispose();
            }
        }
        final Enumeration<eg> elements2 = this.w.elements();
        while (elements2.hasMoreElements()) {
            final eg nextElement2;
            if ((nextElement2 = elements2.nextElement()) instanceof eg) {
                nextElement2.q();
            }
        }
        this.y();
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
                super.q.close();
                super.q.close();
                super.q.close();
            }
            catch (Exception ex2) {}
        }
        super.n = false;
        this.q.q();
        if (a.a.q != null) {
            a.a.q.e();
        }
        super.h = true;
        super.q = null;
        super.q = null;
        super.q = null;
    }
    
    public void c(es es) {
        switch (es.q()) {
            case 67585: {
                this.u(es);
            }
            case 65794: {
                final es es2 = es;
                es = (es)this;
                this.o();
                for (int i = 0; i < es2.w(); ++i) {
                    final dj dj;
                    if ((dj = (dj)((cU)es).e.w(es2.q(i, 0))) != null && dj.r != ((bZ)es).r) {
                        final String q = ec.q(eb.q("(This user has left %1)"), new String[] { a.a.e });
                        final cm cm = (cm)((cU)es).w.w(dj.e);
                        final db db = (db)((cU)es).y.w(dj.o);
                        final dt dt;
                        (dt = new dt(dj.r, dj.getName())).y = false;
                        if (db != null) {
                            if (dj.o == ((cV)es).o && !db.q(57) && (!dj.q(23) || ((bZ)es).q(24)) && (!dj.q(25) || ((bZ)es).q(32))) {
                                final String q2;
                                String string;
                                if ((q2 = es2.q(i, 0)) != null) {
                                    string = ((cU)es).q(q2) + " " + q;
                                }
                                else {
                                    string = q;
                                }
                                final cS cs = new cS(string, dj, false, false, cm, (cM)es);
                                cs.q(es2.q, es2.r);
                                final int w = ((cU)es).W;
                                if (((cU)es).q != null) {
                                    ((cU)es).q.q(cs);
                                }
                            }
                            if ((!dj.q(23) || ((bZ)es).q(24)) && (!dj.q(25) || ((bZ)es).q(32))) {
                                dj.o = -999;
                                db.w();
                            }
                            if (((cU)es).q != null) {
                                ((cU)es).q.w(db);
                            }
                        }
                        if (((cU)es).q != null) {
                            ((cU)es).q.q(dj);
                            ((cU)es).q.q(dt, false);
                        }
                        final cY cy;
                        if ((cy = (cY)((cU)es).q.w(dj.r)) != null) {
                            if (dj.r == ((bZ)es).r) {
                                cy.dispose();
                            }
                            else {
                                final cS cs2 = new cS(q, dj, false, false, cm, (cM)es);
                                cs2.q(es2.q, es2.r);
                                cy.q(cs2);
                            }
                        }
                        final dW e = ((cU)es).e;
                        try {
                            ((cU)es).e.w(dj);
                        }
                        finally {
                            final dW e2 = ((cU)es).e;
                        }
                    }
                }
            }
            case 66049: {
                this.r(es);
            }
            case 66305: {
                this.W(es);
            }
            case 66308: {
                this.S(es);
            }
            case 66306: {
                this.Q(es);
            }
            case 66307: {
                this.o(es);
            }
            case 66561: {
                this.i(es);
            }
            case 66816:
            case 50400771: {
                this.t(es);
            }
            case 66817: {
                this.p(es);
            }
            case 67073: {
                this.e(es);
            }
            case 67074: {
                final cz cz;
                if ((cz = (cz)super.e.w(es.q(0, 0))) != null) {
                    final es es3;
                    (es3 = new es(67073, 1)).w = cz.r;
                    es3.q(0, 0, super.r);
                    this.q(es3);
                }
            }
            case 67329: {
                this.D(es);
            }
            case 67330:
            case 17236481: {
                this.k(es);
            }
            case 17236482: {
                this.l(es);
            }
            case 67331:
            case 17236737: {
                this.E(es);
            }
            case 17236738: {
                this.H(es);
            }
            case 67332:
            case 17236993: {
                this.a(es);
            }
            case 17236994: {
                this.s(es);
            }
            case 67333:
            case 17237265: {
                this.d(es);
            }
            case 17237266: {
                this.f(es);
            }
            case 17237249:
            case 275795985: {
                this.g(es);
            }
            case 275795986: {
                this.h(es);
            }
            case 67334: {
                this.V(es);
            }
            case 17237506: {
                this.q(this.q.q(), this.q.w());
            }
            case 17237505: {
                this.C(es);
            }
            case 67584: {
                final long currentTimeMillis = System.currentTimeMillis();
                this.n(es);
                System.out.println("ex time=" + (System.currentTimeMillis() - currentTimeMillis));
            }
            case 67586: {
                this.m(es);
            }
            case 67843: {
                this.b(es);
            }
            case 68608: {
                this.y(es);
            }
            case 67338: {
                this.j(es);
            }
            case 67341:
            case 17239297: {
                this.z(es);
            }
            case 17239298: {
                this.x(es);
            }
            case 67844: {
                this.v(es);
            }
            case 33621775:
            case 537948401: {
                this.F(es);
            }
            case 537948402: {
                this.G(es);
            }
            case 50400768: {
                final es es4 = es;
                this.w.put(new Integer(es4.q(0, 1)), new eh((cz)super.e.w(es4.q(0, 0)), es4.q(0, 0), es4.q(0, 1), es4.q(0, 1)));
            }
            case 50400769: {
                final es es5 = es;
                synchronized (this.w) {
                    final Object remove;
                    if ((remove = this.w.remove(new Integer(es5.q(0, 1)))) instanceof eg) {
                        ((eg)remove).q();
                    }
                    break;
                }
            }
            case 50400770: {
                final eg eg;
                if ((eg = this.w.get(new Integer(es.q(0, 1)))) != null) {
                    eg.w();
                }
            }
            case 4198416: {
                final es es6 = es;
                a.a.r = es6.q(0, 63);
                a.a.q = es6.q(0, 62);
                a.a.w = es6.q(0, 54);
                a.a.e = es6.q(0, 53);
                a.a.t = es6.q(0, 57);
                a.a.y = es6.q(0, 52);
                this.q = null;
            }
            case 4198464: {
                final es es7 = es;
                new ag(new Frame(), es7.q(0, 0), es7.q(0, 1), this).setVisible(true);
            }
            case 4198496: {
                final String q3 = (es = es).q(0, 0);
                cJ.q(es.q(0, 1));
                cJ.w(es.q(0, 2));
                a.a.r = es.q(0, 3);
                cJ.e(es.q(0, 4));
                cJ.r(es.q(0, 5));
                cV.a = es.q(0, 6);
                AppletAbstract.q().q.q();
                if (!"".equals(q3)) {
                    a.a.e = q3;
                }
                a.a.u = es.q(0, 0);
            }
            case 16974593: {
                es = es;
                try {
                    AppletAbstract.q().getAppletContext().showDocument(new URL(es.q(0, 0)), "_blank");
                    break;
                }
                catch (MalformedURLException ex) {
                    System.out.println("Bad URL:" + es.q(0, 0));
                }
            }
            case 4198465: {
                this.A(es);
            }
            case 4198466:
            case 1074807297: {
                O(es);
            }
            case 1074807298: {
                P(es);
            }
            case 4198512:
            case 1074819073: {
                this.Z(es);
            }
            case 1074819074: {
                this.X(es);
            }
            case 33621773:
            case 537948369: {
                this.J(es);
            }
            case 537948370: {
                this.K(es);
            }
            case 4198513: {
                this.L(es);
            }
            case 4198528: {
                this.Y(es);
            }
            case 4198529: {
                this.U(es);
            }
            case 4202544: {
                this.I(es);
            }
            case 4202592:
            case 1075863553: {
                this.R(es);
            }
            case 1075863554: {
                this.T(es);
                break;
            }
        }
    }
    
    private void R(final es es) {
        final dW x = this.x;
        try {
            for (int i = 0; i < es.w(); ++i) {
                final int q = es.q(i, 0);
                cl cl = (cl)this.x.w(q);
                if (es.q(i, 63)) {
                    if (cl != null) {
                        this.x.w(q);
                    }
                }
                else {
                    if (cl == null) {
                        cl = new cl(q, es.q(i, 0));
                        this.x.q(cl);
                    }
                    else {
                        cl.t = es.q(i, 0);
                    }
                    cl.w = es.q(i, 1);
                    cl.q = es.q(i, 2);
                    cl.w = es.q(i, 1);
                    cl.q(es.q(i));
                    final cx cx;
                    if ((cx = (cx)this.j.w(cl.q)) != null) {
                        cl.q = cx.q;
                    }
                }
            }
        }
        finally {
            final dW x2 = this.x;
        }
    }
    
    private void T(final es es) {
        final dW x = this.x;
        try {
            for (int i = 0; i < es.w(); ++i) {
                this.x.w(es.q(i, 0));
            }
        }
        finally {
            final dW x2 = this.x;
        }
    }
    
    private void Y(final es es) {
        final dW k = super.k;
        a.a.q.q();
        try {
            for (int i = 0; i < es.w(); ++i) {
                final int q = es.q(i, 0);
                final String q2 = es.q(i, 0);
                cs cs = (cs)super.k.w(q);
                if (es.q(i, 63)) {
                    if (cs != null) {
                        super.k.w(q);
                    }
                }
                else {
                    if (cs == null) {
                        cs = new cs(q, q2);
                    }
                    else {
                        cs.t = q2;
                    }
                    cs.q(es.q(i));
                    super.k.q(cs);
                    cs.q = es.q(i, 1);
                    cs.y(es.q(i, 2));
                    cs.t(es.q(i, 3));
                    cs.e = es.q(i, 4);
                    cs.w = es.q(i, 5);
                    cs.q = es.q(i, 1);
                    if (cs.q == null || cs.q.trim().equals("")) {
                        cs.y(cf.w.b.getRGB());
                        cs.t(cf.w.n.getRGB());
                        cs.e = cf.w.p;
                        cs.w = cf.w.o;
                        cs.q = cf.w.w;
                    }
                    a.a.q.q(cs.q(4));
                    a.a.q.q(cs.q);
                    final String s;
                    if (((s = q2).length() <= 3 || !s.substring(0, 4).equals("rss:")) && cs.q(0)) {
                        a.a.q.q(cs);
                    }
                }
            }
        }
        finally {
            final dW j = super.k;
        }
        a.a.q.w();
    }
    
    private void U(final es es) {
        if (es.w() == 0) {
            return;
        }
        for (int i = 0; i < es.w(); ++i) {
            final String q = es.q(i, 0);
            final int q2 = es.q(i, 0);
            final cs cs;
            if ((cs = (cs)this.k.w(q2)) != null) {
                a.a.q.q(q, cs);
            }
            else {
                System.out.println("Can't find sc id = " + q2);
            }
        }
        a.a.q.w();
    }
    
    private void I(final es es) {
        int n = 1;
        for (int i = 0; i < es.w(); ++i) {
            this.l.q(new ck(n, es.q(i, 0)));
            ++n;
        }
    }
    
    private static void O(final es es) {
        try {
            for (int i = 0; i < es.w(); ++i) {
                final int q = es.q(i, 0);
                ce ce = (ce)cV.f.w(q);
                if (es.q(i, 63)) {
                    if (ce != null) {
                        cV.f.w(q);
                    }
                }
                else {
                    if (ce == null) {
                        ce = new ce(q, es.q(i, 0));
                        cV.f.q(ce);
                    }
                    else {
                        ce.t = es.q(i, 0);
                    }
                    ce.q(es.q(i));
                    ce.y(es.q(i, 1));
                }
            }
        }
        finally {
            final dW f = cV.f;
        }
    }
    
    private static void P(final es es) {
        final dW f = cV.f;
        try {
            for (int i = 0; i < es.w(); ++i) {
                cV.f.w(es.q(i, 0));
            }
        }
        finally {
            final dW f2 = cV.f;
        }
    }
    
    public static dW q(final int n) {
        final dW dw = new dW();
        for (int i = 0; i < cV.f.q(); ++i) {
            final ce ce;
            if ((ce = (ce)cV.f.q(i)).q(n)) {
                dw.q(ce);
            }
        }
        return dw;
    }
    
    private void A(final es es) {
        for (int i = 0; i < es.w(); ++i) {
            dj dj;
            if ((dj = (dj)this.e.w(es.q(i, 0))) == null) {
                dj = new dj(es.q(i, 0), es.q(i, 0));
            }
            dj.y(es.q(i, 1));
            this.e.q(dj);
            super.q.q(dj, false);
        }
    }
    
    public void v(final es es) {
        this.Y = es.q(0, 4);
        this.O = es.q(0, 5);
        this.A = es.q(0, 6);
        this.S = es.q(0, 7);
        this.D = es.q(0, 9);
        this.P = es.q(0, 10);
    }
    
    public void b(final es es) {
        this.r = es.q(0);
        this.E = es.q(0, 1);
        a.a.q = es.q(0, 2);
        if (this.q != null && ((cT)this.q).getParent() != null && ((cO)((cT)this.q).getParent()).q() != null) {
            ((cO)((cT)this.q).getParent()).q().u();
        }
    }
    
    public final void q(final String s, final int n, final int n2) {
        final es es;
        (es = new es(66310, 1)).q(0, 0, n);
        es.q(0, 1, n2);
        es.q(0, 2, super.r);
        es.q(0, 0, s);
        this.q(es);
    }
    
    public final void w(final String s, final int n, final int n2, final int n3) {
        final es es;
        (es = new es(66310, 1)).q(0, 0, n);
        es.q(0, 1, n2);
        es.q(0, 2, super.r);
        if (n3 != 0) {
            es.q(0, 3, n3);
        }
        es.q(0, 0, s);
        this.q(es);
    }
    
    public final boolean a() {
        return super.a;
    }
    
    public final Image w(final String s, final boolean b) {
        return this.r(s, b);
    }
    
    public final Image e(final String s, final boolean b) {
        try {
            final URL q = super.q;
            if (b) {
                final URL url = new URL(q, "Resources/" + cV.a + "/" + s);
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
            ex.printStackTrace();
            return null;
        }
    }
    
    public final Image r(final String s, final boolean b) {
        final Image e;
        if ((e = this.e(s, b)) != null && this.q != null) {
            if (b) {
                this.q.q(s, e);
            }
            else {
                this.w.q(s, e);
            }
            synchronized (this.w) {
                this.w.w(e);
                this.w.notifyAll();
            }
        }
        return e;
    }
    
    public abstract Image q(final URL p0);
    
    public void q(final String t, final String s, final ep ep, URL url, final int n, final String s2) {
        try {
            super.j = false;
            String s3;
            if (AppletAbstract.q().w != null) {
                s3 = AppletAbstract.q().w;
            }
            else {
                s3 = q.getHost();
            }
            if (s3 == null || s3.trim().length() <= 0) {
                s3 = s2;
            }
            super.q = q;
            super.t = t;
            Label_0098: {
                if (ef.e < 66048) {
                    if (ef.e) {
                        break Label_0098;
                    }
                }
                try {
                    super.q = null;
                }
                catch (Throwable t2) {
                    super.q = null;
                }
            }
            if (ef.w && ef.e < 65792) {
                final int w = ef.w;
            }
            if (!(url = (URL)(this.q != null && this.q != null && this.q != null))) {
                for (int n2 = 0; url == 0 && n2 < cE.q.size(); ++n2) {
                    final int q2 = cE.q(n2);
                    try {
                        if (q2 != AppletAbstract.q().r) {
                            try {
                                this.w(s3, q2);
                                this.q(n, false);
                                url = (URL)1;
                            }
                            catch (IOException ex10) {
                                url = (URL)0;
                            }
                        }
                    }
                    catch (Exception ex) {
                        if (n2 == cE.q.size() - 1) {
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
                    (super.q = new cK((cM)this)).start();
                }
                if (this.q == null) {
                    (this.q = new cW(this)).start();
                }
                if (this.w == null) {
                    this.w = new dV("global");
                }
                if (this.q == null || this.R != n) {
                    this.q = new dV("" + n);
                }
                final es es;
                (es = new es(65793, 1)).q(0, 0, n);
                es.q(0, 1, super.z);
                final es es2 = es;
                final int n3 = 0;
                final int n4 = 2;
                final int q3;
                final int w2;
                if ((q3 = br.q()) == 0) {
                    w2 = br.w();
                }
                es2.q(n3, n4, w2);
                es.q(0, 0, t);
                es.q(0, 1, "en");
                es.q(0, 2, s2);
                es.q(0, 3, s);
                es.q(0, 4, (String)ea.q(new Object[] { "location.href" }, null));
                es.q(0, 0, ep);
                if (!this.i() && super.z != -999) {
                    es.q(0, 2);
                }
                if (this.q(23)) {
                    es.q(0, 23);
                }
                if (this.q(25)) {
                    es.q(0, 25);
                }
                if (super.p && (!ef.w || ef.w != 1)) {
                    es.q(0, 21);
                    es.q(0, 20);
                }
                this.q(es);
            }
            else {
                this.y();
                new b(super.q, "Error", ec.q(eb.q("No response was received from the server.  The %1 Server may not be running, or may not be a compatible version. Please contact the administrator in charge of the site."), new String[] { a.a.e }), this).setVisible(true);
            }
        }
        catch (UnknownHostException ex2) {
            this.y();
            new b(super.q, ec.q(eb.q("%1 could not locate the specified host. Please make sure you are using the correct host name."), new String[] { a.a.e }), ex2, this).setVisible(true);
        }
        catch (InterruptedIOException ex3) {
            this.y();
            new b(super.q, ec.q(eb.q("No response was received from the server.  The %1 Server may not be running, or may not be a compatible version. Please contact the administrator in charge of the site."), new String[] { a.a.e }), ex3, this).setVisible(true);
        }
        catch (NoRouteToHostException ex4) {
            this.y();
            new b(super.q, ec.q(eb.q("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { a.a.e }), ex4, this).setVisible(true);
        }
        catch (SecurityException ex5) {
            this.y();
            new b(super.q, ec.q(eb.q("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { a.a.e }), ex5, this).setVisible(true);
        }
        catch (IOException ex7) {
            final IOException ex6 = ex7;
            ex7.printStackTrace();
            this.y();
            new b(super.q, ec.q(eb.q("%1 could not connect to the specified host.  Please verify that the %1 Server is running and that you are using the correct host name."), new String[] { a.a.e }), ex6, this).setVisible(true);
        }
        catch (Exception ex9) {
            final Exception ex8 = ex9;
            ex9.printStackTrace();
            this.y();
            new b(super.q, ec.q(eb.q("An unknown error occurred while connecting to the %1 Server.  Please contact the administrator in charge of the site."), new String[] { a.a.e }), ex8, this).setVisible(true);
        }
        super.i = false;
        super.o = false;
    }
    
    public void q(final URL url, final String s) {
    }
    
    public final void p(final int n) {
    }
    
    private void q(final String s, final cz cz, final int n, final int n2, final boolean b, final long n3, final int n4) {
        this.q(s, cz, n, b, false, n3, n4, null);
    }
    
    private void q(final String s, final cz cz, final int n, final boolean b, final boolean b2, final long n2, final int n3, final byte[] q) {
        if (super.q != null) {
            int r;
            if ((r = cz.r) == super.r) {
                r = n;
            }
            cz.getName();
            final cS cs;
            (cs = new cS(this.w(s), cz, n != -1 && n != -3 && n != -2 && !b, false, (cm)super.w.w(cz.e), this)).y = (n == -3 || n == -2);
            cs.q(n2, n3);
            cs.p = cz.y;
            if (b2) {
                cs.u = q[0];
            }
            else {
                cs.q = q;
            }
            if (cs.r) {
                final cY cy;
                if ((cy = (cY)super.q.w(r)) != null) {
                    cy.q(cs);
                }
                else {
                    if (!super.l) {
                        super.q.q(cs);
                        return;
                    }
                    this.q(cs, cz);
                }
            }
            else {
                cs.w = cz.e;
                if (n == -3 || n == -2) {
                    this.q(cs);
                    return;
                }
                super.q.q(cs);
            }
        }
    }
    
    public void n(final es es) {
        this.m = true;
        super.r = es.w;
        this.q(es.q(0));
        cV.a = es.q(0, 0);
        if (a.a.q == null) {
            a.a.q = this.e("icon_display.gif", false);
        }
        if (!"Admin".equals(cV.a)) {
            if (a.a.r == null) {
                a.a.r = this.e(cf.w.e() + "background.gif", true);
            }
            if (a.a.t == null) {
                a.a.t = this.e(cf.w.e() + "chatbackground.gif", true);
            }
            if (AppletAbstract.q().q) {
                (super.q = new cT(this, null)).setVisible(false);
                AppletAbstract.q().q.setVisible(false);
                AppletAbstract.q().add(super.q.q());
                ((cT)super.q).validate();
                return;
            }
            if (a.a.w()) {
                final long currentTimeMillis = System.currentTimeMillis();
                final cO co = new cO(this, aO.q(this));
                System.out.println("Load window complete in " + (System.currentTimeMillis() - currentTimeMillis));
                super.q = (dK)co.q();
                this.q = this.q.q();
            }
        }
    }
    
    protected void m(final es es) {
        super.i = true;
        super.o = true;
        super.o = -999;
        if (super.w.w(super.x) == null) {
            this.f(super.l);
        }
        else {
            this.f(super.x);
        }
        final db db = (db)super.y.w(super.z);
        db db2 = (db)super.y.w(super.k);
        if (db == null || (db.q == null && db2.q != null)) {
            if (db2 == null) {
                db2 = (db)super.y.q(0);
            }
            this.q(db2);
            return;
        }
        this.q(db);
    }
    
    private void o() {
        if (!super.i && !super.o) {
            if (super.q != null) {
                super.q.q().w();
            }
            int o;
            if (super.y.w(super.z) == null) {
                o = super.k;
            }
            else {
                o = super.z;
            }
            super.o = true;
            final db db;
            if ((db = (db)super.y.w(o)) != null && db.e && db.q != null && !this.q(40)) {
                super.o = -999;
                new ad(super.q.q(), this, db).setVisible(true);
            }
            super.o = o;
        }
    }
    
    protected final void q(final dj dj, int i, final long n, final int n2) {
        final int o = dj.o;
        final db db = (db)super.y.w(i);
        final db db2 = (db)super.y.w(dj.o);
        if (db == null) {
            return;
        }
        this.o();
        if (dj.r == super.r) {
            if (db2 != null) {
                db2.w = false;
            }
            db.w = true;
            this.o = i;
            if (super.q != null) {
                if (!super.j) {
                    this.q(false, false);
                }
                super.q.q(db);
                if (!super.q.isVisible()) {
                    super.q.setVisible(true);
                }
                super.q.q().validate();
            }
        }
        if (db != null) {
            if (this.o == db.r) {
                db.q(this.q(db.r));
            }
            if (db2 != null && db != null) {
                if (db2.r != db.r && (!dj.q(23) || this.q(24)) && (!dj.q(25) || this.q(32))) {
                    db.q();
                }
            }
            else if (db2 == null && db != null && (!dj.q(23) || this.q(24)) && (!dj.q(25) || this.q(32))) {
                db.q();
            }
            if (super.q != null) {
                super.q.w(db);
            }
        }
        if (db2 != null) {
            if (this.o == db2.r) {
                db2.q(this.q(db2.r));
            }
            if (db2 != null && db != null && db2.r != db.r && (!dj.q(23) || this.q(24)) && (!dj.q(25) || this.q(32))) {
                db2.w();
            }
            if (super.q != null) {
                super.q.w(db2);
            }
        }
        final cm cm = (cm)super.w.w(dj.e);
        if (i == this.o && dj.o != i) {
            if (db != null && !db.q(57) && (!dj.q(23) || this.q(24)) && (!dj.q(25) || this.q(32))) {
                final cS cs;
                (cs = new cS(ec.q(eb.q("(This user has entered %1)"), new String[] { this.q(db.getName()) }), dj, false, false, cm, this)).q(n, n2);
                final int b = super.b;
                if (super.q != null) {
                    super.q.q(cs);
                }
            }
        }
        else if (dj.o == this.o && i != this.o && db2 != null && db != null && !db2.q(57) && (!dj.q(23) || this.q(24)) && (!dj.q(25) || this.q(32))) {
            final cS cs2;
            (cs2 = new cS(ec.q(eb.q("(This user has moved to %1)"), new String[] { this.q(db.getName()) }), dj, false, false, cm, this)).q(n, n2);
            final int w = super.W;
            if (super.q != null) {
                super.q.q(cs2);
            }
        }
        final String q;
        if (super.r == dj.r && db != null && ((db2 != null && db.r != db2.r) || db2 == null) && (q = db.q) != null && !"".equals(q)) {
            final dj dj2;
            (dj2 = new dj(-999, db.getName())).y(db.y());
            dj2.r(db.e());
            final cS cs3;
            (cs3 = new cS(q, dj2, false, false, cm, this)).q(n, n2);
            cs3.p = true;
            cs3.p = db.e();
            if (db.u == 0) {
                cs3.d = db.y();
            }
            else {
                cs3.d = db.i();
            }
            if (super.q != null) {
                super.q.q(cs3);
            }
        }
        dj.o = i;
        if (super.q != null) {
            if (super.j || i == this.o) {
                super.q.q(dj, true);
            }
            else if (!super.j && o == this.o) {
                super.q.q(dj);
            }
        }
        if (!this.W) {
            synchronized (this) {
                this.W = true;
            }
            if (this.q != null) {
                for (i = 0; i < this.u.q(); ++i) {
                    super.q.q((bY)this.u.q(i));
                }
                super.q.q().w();
            }
        }
    }
    
    private void S(final es es) {
        for (int i = 0; i < es.w(); ++i) {
            if (es.q() != null) {
                this.q(es.q());
            }
            cz cz;
            if ((cz = (cz)super.e.w(es.q(i, 0))) == null) {}
            if (cz == null) {
                (cz = new cz(-999, "Guest")).e = super.l;
            }
            if (cz != null) {
                int n;
                if ((n = cz.r) == super.r) {
                    n = es.w;
                }
                if (!cz.r) {
                    switch (es.w) {
                        case -3:
                        case -2:
                        case -1: {
                            if (cz.e) {
                                final int m = super.m;
                                break;
                            }
                            final int n2 = super.n;
                            break;
                        }
                        default: {
                            if (super.q.q(n)) {
                                final int n3 = super.n;
                                break;
                            }
                            final int q = super.Q;
                            break;
                        }
                    }
                    if (es.q() != null) {
                        final String q2 = es.q(i, 0);
                        final cz cz2 = cz;
                        final int w = es.w;
                        final int q3 = es.q;
                        this.q(q2, cz2, w, true, false, es.q, es.r, es.q());
                    }
                    else {
                        this.q(es.q(i, 0), cz, es.w, es.q, true, es.q, es.r);
                    }
                }
            }
        }
    }
    
    protected final void Q(final es es) {
        if (super.q != null) {
            for (int i = 0; i < es.w(); ++i) {
                final int q = es.q(i, 0);
                final int q2 = es.q(i, 1);
                final int q3 = es.q(i, 2);
                final int q4 = es.q(i, 3);
                final int q5 = es.q(i, 4);
                final cS cs;
                (cs = new cS(this.q(es.q(i, 1)), this.q(es.q(i, 0)), false, false, (cm)super.w.w(q), q2, false, false, -1, q3)).p = true;
                cs.o = q3;
                cs.p = q4;
                cs.d = q5;
                cs.q(es.q, es.r);
                super.q.q(cs);
                if (es.w == super.r) {
                    final int q6 = super.Q;
                }
                else {
                    final int n = super.n;
                }
            }
        }
    }
    
    protected final void W(final es es) {
        this.o();
        for (int i = 0; i < es.w(); ++i) {
            final int q = es.q(i, 0);
            es.q(i, 1);
            final int q2 = es.q(i, 2);
            String s = es.q(i, 0);
            es.q(i, 1);
            if (s == null) {
                s = "";
            }
            if (q > 0) {
                if (a.a.q()) {
                    s = dV.q(this.o, s, this.T);
                }
                else {
                    s = dV.q(this.o, s, this.A);
                }
            }
            final String q3 = ex.q(s, "\r");
            if (!"".equals(q3)) {
                s = q3;
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
                s = string + ex.q(s.substring(string.length()), "\n");
            }
            cz cz;
            if ((cz = (cz)super.e.w(q)) == null) {}
            if (cz == null && q < -2147483638) {
                (cz = new cz(q, ChatNames.getSecurityName())).e = q;
            }
            if (cz == null) {
                cz = (cz)this.r.w(q);
            }
            if (cz != null) {
                int n2 = cz.r;
                cz.r(q2);
                if (n2 == super.r) {
                    n2 = es.w;
                }
                if (es.w != super.r && es.q(0, 20)) {
                    return;
                }
                if (!cz.r) {
                    if (!es.q) {
                        switch (es.w) {
                            case -3:
                            case -2:
                            case -1: {
                                if (cz.e) {
                                    final int m = super.m;
                                    break;
                                }
                                final int n3 = super.n;
                                break;
                            }
                            default: {
                                if (super.q.q(n2)) {
                                    final int n4 = super.n;
                                    break;
                                }
                                final int q4 = super.Q;
                                break;
                            }
                        }
                    }
                    if (es.q() != null) {
                        final String s4 = s;
                        final cz cz2 = cz;
                        final int w = es.w;
                        final int q5 = es.q;
                        this.q(s4, cz2, w, false, es.q(0, 20), es.q, es.r, es.q());
                    }
                    else {
                        this.q(s, cz, es.w, es.q, false, es.q, es.r);
                    }
                }
            }
        }
    }
    
    private void D(final es es) {
        super.w = es.q(-1);
        final dW u = super.u;
        try {
            for (int i = 0; i < es.w(); ++i) {
                final int q = es.q(i, 0);
                bY by = (bY)super.u.w(q);
                if (es.q(i, 63)) {
                    if (by != null) {
                        super.u.w(q);
                    }
                }
                else {
                    if (by == null) {
                        by = new bY(q, es.q(i, 0));
                        super.u.q(by);
                    }
                    else {
                        by.t = es.q(i, 0);
                    }
                    by.q = es.q(i, 1);
                    by.w = es.q(i, 2);
                    by.w = es.q(i, 1);
                    by.q = es.q(i, 2);
                    by.e = es.q(i, 3);
                    by.q(es.q(i));
                    if (by.q == null) {
                        by.q = this.e("banners/" + by.q, true);
                    }
                    if (super.q != null && by.q != null) {
                        super.q.q(by);
                    }
                }
            }
            if (super.q != null) {
                super.q.q().w();
            }
        }
        finally {
            final dW u2 = super.u;
        }
    }
    
    private void F(final es es) {
        for (int w = es.w(), i = 0; i < w; ++i) {
            final cj cj;
            (cj = new cj(es.q(i, 0), es.q(i, 0))).q(es.q(i));
            cj.q = es.q(i, 1);
            if (!cj.q(63) && this.q.q(cj.r) != cj) {
                cj.q = this.r("emoticons/" + cj.q, true);
            }
            this.q.q(cj);
        }
    }
    
    private void G(final es es) {
        for (int i = 0; i < es.w(); ++i) {
            this.q.q(es.q(i, 0));
        }
    }
    
    public void E(final es es) {
        if (!dd.q) {
            dd.q(this);
        }
        final dW w = super.w;
        try {
            for (int i = 0; i < es.w(); ++i) {
                final int q = es.q(i, 0);
                cm cm = (cm)super.w.w(q);
                if (es.q(i, 63)) {
                    if (cm != null) {
                        super.w.w(q);
                    }
                }
                else {
                    if (cm == null) {
                        cm = new cm(q, es.q(i, 0));
                        super.w.q(cm);
                        cm.q = this.r("userIcons/" + cm.getName(), true);
                    }
                    else {
                        cm.t = es.q(i, 0);
                        cm.q = this.r("userIcons/" + cm.getName(), true);
                    }
                    cm.q(es.q(i));
                    cm.q(es.q(i, 1));
                    if (es.q(i, 62)) {
                        super.l = q;
                    }
                }
            }
        }
        finally {
            final dW w2 = super.w;
        }
    }
    
    private void H(final es es) {
        final dW w = this.w;
        try {
            for (int i = 0; i < es.w(); ++i) {
                this.w.w(es.q(i, 0));
            }
        }
        finally {
            final dW w2 = this.w;
        }
    }
    
    private void J(final es es) {
        final dW j = this.j;
        try {
            for (int i = 0; i < es.w(); ++i) {
                final int q = es.q(i, 0);
                final String q2 = es.q(i, 1);
                cx cx = (cx)this.j.w(q);
                if (es.q(i, 63)) {
                    if (cx != null) {
                        this.j.w(q);
                    }
                }
                else {
                    if (cx == null) {
                        cx = new cx(q, es.q(i, 0));
                        this.j.q(cx);
                        cx.q = this.r("starpic/" + q2, true);
                    }
                    else {
                        cx.t = es.q(i, 0);
                    }
                    cx.q = q2;
                    cx.q(es.q(i));
                }
            }
        }
        finally {
            final dW k = this.j;
        }
    }
    
    private void K(final es es) {
        final dW j = this.j;
        try {
            for (int i = 0; i < es.w(); ++i) {
                this.j.w(es.q(i, 0));
            }
        }
        finally {
            final dW k = this.j;
        }
    }
    
    private void L(final es es) {
        for (int i = 0; i < es.w(); ++i) {
            final int q = es.q(i, 0);
            final int q2 = es.q(i, 1);
            final dj dj;
            if ((dj = (dj)super.e.w(q)) != null) {
                this.q.q(dj, false);
                this.q.put("" + q, "" + q2);
            }
        }
    }
    
    private void Z(final es es) {
        if (super.q != null) {
            for (int i = 0; i < es.w(); ++i) {
                final int q = es.q(i, 0);
                cA ca = (cA)super.t.w(q);
                if (es.q(i, 63)) {
                    if (ca != null) {
                        super.t.w(q);
                    }
                }
                else {
                    if (ca == null) {
                        ca = new cA(q, es.q(i, 0));
                        super.t.q(ca);
                    }
                    else {
                        ca.t = es.q(i, 0);
                    }
                    ca.q(es.q(i));
                    ca.e = es.q(i, 1);
                    ca.k = es.q(i, 2);
                    ca.l = es.q(i, 3);
                    ca.p = cl.q(this.o(), ca.l);
                    ca.y(es.q(i, 4));
                    ca.t(es.q(i, 5));
                    ca.o = es.q(i, 1);
                    ca.d = es.q(i, 2);
                    ca.s = ((this.y.w(ca.k) != null) ? ((cr)this.y.w(ca.k)).getName() : "");
                    ca.q = ((this.w.w(ca.e) != null) ? ((cm)this.w.w(ca.e)).q : null);
                    ca.w = ((this.j.w(ca.p) != null) ? ((cx)this.j.w(ca.p)).q : null);
                }
            }
        }
    }
    
    private void X(final es es) {
        final dW t = this.t;
        try {
            for (int i = 0; i < es.w(); ++i) {
                this.t.w(es.q(i, 0));
            }
        }
        finally {
            final dW t2 = this.t;
        }
    }
    
    private void C(final es es) {
        for (int i = 0; i < es.w(); ++i) {
            final cz cz;
            if ((cz = (cz)this.e.w(es.q(i, 0))) != null) {
                cz.q = es.q(i, 1);
                cz.w = es.q(i, 2);
                cz.q = es.q(i, 1);
                cz.e = es.q(i, 2);
                cz.r = es.q(i, 3);
                cz.y = es.q(i, 4);
                cz.u = es.q(i, 5);
                cz.w = es.q(i, 6);
                cz.w = es.q(i, 0);
            }
        }
    }
    
    private void V(final es es) {
        this.o();
        final dW e = super.e;
        try {
            for (int i = 0; i < es.w(); ++i) {
                final int q = es.q(i, 0);
                dj dj = (dj)super.e.w(q);
                final db db = (db)super.y.w(es.q(i, 2));
                if (es.q(i, 63)) {
                    if (dj != null) {
                        dj.q(es.q(i));
                        if (db != null) {
                            if ((!dj.q(23) || this.q(24)) && (!dj.q(25) || this.q(32))) {
                                db.w();
                            }
                            if (super.q != null) {
                                super.q.w(db);
                            }
                        }
                        final dt dt;
                        (dt = new dt(dj.r, dj.getName())).y = false;
                        super.e.w(q);
                        if (super.q != null) {
                            super.q.q(dj);
                            super.q.q(dt, false);
                        }
                        final cY cy;
                        if ((cy = (cY)super.q.w(dj.r)) != null) {
                            cy.dispose();
                        }
                    }
                }
                else {
                    final String e2 = this.e(es.q(i, 0));
                    if (dj == null) {
                        (dj = new dj(q, e2)).q(es.q(i));
                        if ((!dj.q(23) || this.q(24)) && (!dj.q(25) || this.q(32))) {
                            super.e.q(dj);
                        }
                        else {
                            this.r.q(dj);
                        }
                    }
                    else {
                        dj.q(es.q(i));
                        if ((dj.q(23) && !this.q(24)) || (dj.q(25) && !this.q(32))) {
                            final dj dj2 = dj;
                            final db db2 = db;
                            final dj dj3 = dj2;
                            if (dj3 != null) {
                                if (db2 != null) {
                                    db2.w();
                                    if (super.q != null) {
                                        super.q.w(db2);
                                    }
                                }
                                final dt dt2;
                                (dt2 = new dt(dj3.r, dj3.getName())).y = false;
                                if (super.q != null) {
                                    super.q.q(dj3);
                                    super.q.q(dt2, false);
                                }
                                final cY cy2;
                                if ((cy2 = (cY)super.q.w(dj3.r)) != null) {
                                    cy2.dispose();
                                }
                            }
                            this.r.q(dj);
                        }
                        dj.t = e2;
                    }
                    dj.e = es.q(i, 1);
                    dj.q = (cm)super.w.w(dj.e);
                    dj.o = es.q(i, 2);
                    dj.y(es.q(i, 3));
                    dj.t(es.q(i, 4));
                    dj.p = es.q(i, 5);
                    final dj dj4 = dj;
                    final dW j = super.j;
                    final dj dj5 = dj4;
                    if (dj4.p == 0 && dj5.q(62)) {
                        dj5.p = 1000;
                    }
                    dj5.q = (cx)j.w(dj5.p);
                    final cl q2;
                    if ((q2 = cl.q(this.x, dj.p)) != null) {
                        dj.h = q2.w;
                    }
                    dj.g = es.q(i, 6);
                    final int s = dj.s;
                    final int q3;
                    final int s2 = (q3 = es.q(i, 7)) & 0xFF;
                    dj.a = (q3 >> 16 & 0xFF);
                    dj.s = s2;
                    dj.b_(es.q(i, 8));
                    dj.j = es.q(i, 9);
                    dj.w(es.q(i, 10));
                    final String q4 = es.q(i, 1);
                    final String q5 = es.q(i, 2);
                    if (q4 != null) {
                        dj.i = q4;
                    }
                    if (q5 != null) {
                        dj.o = q5;
                    }
                    if (es.q(i, 3) != null) {
                        dj.p = es.q(i, 3);
                        if (dj.r == null && this.q(41) && dj.p.length() == 2) {
                            dj.r = this.r("flags/" + dj.p + ".gif", false);
                        }
                    }
                    if (q == super.r) {
                        super.t = dj.getName();
                        if (dj.e != -999) {
                            super.x = dj.e;
                        }
                    }
                    Label_1111: {
                        if (dj.q(61)) {
                            final dj dj6 = dj;
                            final String name = dj.getName();
                            final dj dj7 = dj6;
                            Label_1105: {
                                if (name.length() > 4) {
                                    if (!name.endsWith(".gif") && !name.endsWith(".jpg")) {
                                        if (!name.endsWith(".bmp")) {
                                            break Label_1105;
                                        }
                                    }
                                    try {
                                        dj7.e = this.w("NickPic/" + name, true);
                                    }
                                    catch (Exception ex) {}
                                    break Label_1111;
                                }
                            }
                            dj7.e = null;
                        }
                    }
                    final dt dt3;
                    (dt3 = new dt(dj.r, dj.getName())).y = true;
                    dt3.e = dj.e;
                    dt3.q = (cm)super.w.w(dj.e);
                    dt3.o = dj.o;
                    dt3.q(dj.q());
                    if (super.q != null) {
                        super.q.q(dj, false);
                        super.q.q(dt3, false);
                    }
                    if (dj.s == 7 && (s == 8 || s == 9 || s == 0)) {
                        final int o = dj.o;
                        dj.o = -1;
                        this.q(dj, o, 0L, 0);
                        dj.o = o;
                    }
                    if (super.r == dj.r) {
                        final dj dj8 = dj;
                        this.q(dj8.q());
                        super.x = dj8.e;
                        super.o = dj8.o;
                        this.y(dj8.y());
                        this.t(dj8.u);
                        this.b_(dj8.d);
                        this.w(dj8.f);
                        super.p = dj8.p;
                        super.g = dj8.g;
                        super.a = dj8.a;
                        super.s = dj8.s;
                        super.j = dj8.j;
                    }
                }
            }
        }
        finally {
            final dW e3 = super.e;
        }
    }
    
    private void w(final String s, final int n) {
        (this.q = new Socket(s, n)).setTcpNoDelay(true);
        this.q = new DataOutputStream(new BufferedOutputStream(this.q.getOutputStream(), 16));
        this.q = new DataInputStream(new BufferedInputStream(this.q.getInputStream()));
    }
    
    public final void q(final cS cs, final cz cz) {
        final int r;
        if ((r = cz.r) <= 0) {
            return;
        }
        if (cz.o > 0 && ((cr)super.y.w(cz.o)).q(61) && cz.q(59) && !super.k) {
            return;
        }
        cZ cz2;
        if ((cz2 = (cZ)super.q.w(r)) == null) {
            cz2 = new cY(this, cz);
            final Dimension screenSize;
            int n = (screenSize = Toolkit.getDefaultToolkit().getScreenSize()).width / 2 - 20;
            int n2 = screenSize.height / 2 - 20;
            int n3 = 0;
            int n4 = 0;
            switch (super.q.q() % 4) {
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
            cz2.setBounds(n4 + 2, n3 + 10, n, n2);
            super.q.q(cz2, r);
        }
        if (cs != null) {
            cz2.q(cs);
        }
        cz2.setVisible(true);
    }
    
    public final void i() {
        try {
            if (this.v > 0) {
                this.w((AppletAbstract.q().w != null) ? AppletAbstract.q().w : this.d, cE.q(0));
                this.q(this.v, true);
                this.h = false;
                (this.q = new cK((cM)this)).start();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void f(final int n) {
        final es es;
        (es = new es(67334, 1)).q(0, 0, super.r);
        es.q(0, 1, n);
        es.q(0, 0, this.getName());
        es.w = -1;
        es.q = -1;
        this.q(es);
    }
    
    public final void q(final boolean b) {
        super.a = true;
    }
    
    public final void a(final int n) {
        if (super.q.q() != null) {
            super.q.q().setCursor(3);
        }
        if (this.q == null) {
            this.q = new dm(super.q.q(), this);
        }
        this.q.w(0);
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
    
    public boolean q(final eg eg) {
        if (this.e.get(eg.q()) == null) {
            this.e.put(eg.q(), eg);
            return true;
        }
        return false;
    }
    
    public final void q(final cz cz) {
        this.e.remove(cz);
    }
    
    public int q(final eg eg) {
        this.q(eg.q());
        if (super.e.w(eg.q().r) == null) {
            eg.q();
        }
        final byte b2;
        final byte b = ((b2 = (byte)new Random().nextInt()) >= 0) ? b2 : ((byte)(-b2));
        final es es;
        (es = new es(50400768, 1)).q(0, 0, super.r);
        es.q(0, 1, b);
        es.q(0, 0, eg.q());
        es.q(0, 1, eg.w());
        es.w = eg.q().r;
        es.q = -1;
        this.q(es);
        final es es2;
        (es2 = new es(66305, 1)).q(0, 0, "[File Transfer Requested: file name " + eg.q() + ", size " + r(eg.w()) + ". Please click this message to accept.]");
        es2.q(0, 0, super.r);
        es2.q(0, 20);
        es2.q(new byte[] { b });
        es2.w = eg.q().r;
        es2.q = -1;
        this.q(es2);
        this.w.put(new Integer(b), eg);
        return b;
    }
    
    public final void s(final int n) {
        synchronized (this.w) {
            final eh value;
            if ((value = this.w.get(new Integer(n))) == null) {
                return;
            }
            if (value instanceof eh) {
                if (super.e.w(value.q.r) == null) {
                    this.w.remove(new Integer(n));
                }
                else {
                    this.w.put(new Integer(n), ei.q(this, value));
                }
            }
        }
    }
    
    public final void q(final int n, final cz cz) {
        final es es;
        (es = new es(50400770, 1)).q(0, 0, super.r);
        es.q(0, 1, n);
        es.w = cz.r;
        es.q = -1;
        this.q(es);
    }
    
    public final void d(final int n) {
        final eg eg;
        if ((eg = this.w.remove(new Integer(n))) == null) {
            return;
        }
        final es es;
        (es = new es(50400769, 1)).q(0, 0, super.r);
        es.q(0, 1, n);
        es.w = eg.q().r;
        es.q = -1;
        this.q(es);
        final es es2;
        (es2 = new es(66305, 1)).q(0, 0, "[File Transfer Cancelled: file name " + eg.q() + ", size " + r(eg.w()) + ".]");
        es2.q(0, 0, super.r);
        es2.q(0, 20);
        es2.q(new byte[] { (byte)n });
        es2.w = eg.q().r;
        es2.q = -1;
        this.q(es2);
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
    
    private static void p() {
        final eu eu = new eu();
        final String q = dV.q("VVVVVVVVVV+iVVVVViVVVVVVViVVVVVVVViVVVVVViViVVVggggggX-iVVVVVVVYiVVVYiVVVVVYiVYiVVVVVVVVVYiVVVVgggg+iVgX-iVVVYii+ggViiX-ggVVVYiii+gggViiiX-gggY");
        try {
            eu.q(q.toCharArray());
            eu.q();
        }
        catch (Exception ex) {}
        ew.q = eu.q();
    }
    
    public cV() {
        this.w = new Hashtable();
        this.e = new Hashtable();
        p();
    }
    
    public final dW w() {
        return this.j;
    }
    
    public dW o() {
        return null;
    }
    
    public static boolean s() {
        return System.getProperty("java.vendor").startsWith("Sun");
    }
}
