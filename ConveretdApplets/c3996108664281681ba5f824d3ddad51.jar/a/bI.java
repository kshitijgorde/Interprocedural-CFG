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
import java.net.NoRouteToHostException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.io.IOException;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Component;
import com.spilka.client.muc.AppletAbstract;
import java.awt.Frame;
import java.util.Enumeration;
import java.awt.Dialog;
import java.util.Hashtable;

public abstract class bI extends bH
{
    private bY q;
    private Hashtable w;
    private Hashtable e;
    private bJ q;
    
    public final void r() {
        this.w.e();
        this.u.e();
        this.i.e();
        this.o.e();
        this.p.e();
        bI.d.e();
        this.g.e();
        this.h.e();
        this.j.e();
        this.t.e();
        this.q.w();
        this.n = false;
        if (this.q != null) {
            this.q.interrupt();
        }
        this.q = null;
    }
    
    public final void e() {
        this.a = false;
        this.Q = false;
        this.d = false;
        this.s = false;
        this.e.e();
        this.y.e();
        this.E = this.c;
        bI.p = "";
        if (super.q != null) {
            for (int i = 0; i < super.q.q(); ++i) {
                ((bM)super.q.q(i)).dispose();
            }
        }
        if (super.f != null) {
            for (int j = 0; j < super.f.q(); ++j) {
                ((J)super.f.q(j)).dispose();
            }
        }
        final Enumeration<Dialog> elements = this.e.elements();
        while (elements.hasMoreElements()) {
            final Dialog nextElement;
            if ((nextElement = elements.nextElement()) instanceof Dialog) {
                nextElement.dispose();
            }
        }
        final Enumeration<cy> elements2 = this.w.elements();
        while (elements2.hasMoreElements()) {
            final cy nextElement2;
            if ((nextElement2 = elements2.nextElement()) instanceof cy) {
                nextElement2.q();
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
                super.q.close();
                super.q.close();
                super.q.close();
            }
            catch (Exception ex2) {}
        }
        super.b = false;
        this.q.q();
        if (a.a.q != null) {
            a.a.q.e();
        }
        super.g = true;
        super.q = null;
        super.q = null;
        super.q = null;
    }
    
    public final void x(cJ cj) {
        switch (cj.q()) {
            case 67585: {
                this.u(cj);
            }
            case 65794: {
                final cJ cj2 = cj;
                cj = (cJ)this;
                this.i();
                for (int i = 0; i < cj2.w(); ++i) {
                    final bV bv;
                    if ((bv = (bV)((bH)cj).e.w(cj2.q(i, 0))) != null) {
                        final String q = cv.q(cv.q("(This user has left %1)"), new String[] { a.a.e });
                        final bj bj = (bj)((bH)cj).w.w(bv.u);
                        final bO bo = (bO)((bH)cj).y.w(bv.i);
                        final bZ bz;
                        (bz = new bZ(bv.q, bv.getName())).t = false;
                        if (bo != null) {
                            if (bv.i == ((bI)cj).i && !bo.q(57) && (!bv.q(23) || ((ba)cj).q(24)) && (!bv.q(25) || ((ba)cj).q(32))) {
                                final String q2;
                                String string;
                                if ((q2 = cj2.q(i, 0)) != null) {
                                    string = ((bH)cj).q(q2) + " " + q;
                                }
                                else {
                                    string = q;
                                }
                                final bF bf = new bF(string, bv, false, false, bj, (bz)cj);
                                bf.q(cj2.q, cj2.r);
                                final int q3 = ((bH)cj).Q;
                                if (((bH)cj).q != null) {
                                    ((bH)cj).q.q(bf);
                                }
                            }
                            if ((!bv.q(23) || ((ba)cj).q(24)) && (!bv.q(25) || ((ba)cj).q(32))) {
                                bv.i = -999;
                                bo.w();
                            }
                            if (((bH)cj).q != null) {
                                ((bH)cj).q.w(bo);
                            }
                        }
                        if (((bH)cj).q != null) {
                            ((bH)cj).q.q(bv);
                            ((bH)cj).q.q(bz, false);
                        }
                        final bL bl;
                        if ((bl = (bL)((bH)cj).q.w(bv.q)) != null) {
                            if (bv.q == ((ba)cj).q) {
                                bl.dispose();
                            }
                            else {
                                final bF bf2 = new bF(q, bv, false, false, bj, (bz)cj);
                                bf2.q(cj2.q, cj2.r);
                                bl.q(bf2);
                            }
                        }
                        final cq e = ((bH)cj).e;
                        try {
                            ((bH)cj).e.w(bv);
                        }
                        finally {
                            final cq e2 = ((bH)cj).e;
                        }
                    }
                }
            }
            case 66049: {
                this.r(cj);
            }
            case 66305: {
                this.v(cj);
            }
            case 66308: {
                this.Y(cj);
            }
            case 66306: {
                this.U(cj);
            }
            case 66307: {
                this.o(cj);
            }
            case 66561: {
                this.i(cj);
            }
            case 66816:
            case 50400771: {
                this.t(cj);
            }
            case 66817: {
                this.p(cj);
            }
            case 67073: {
                this.e(cj);
            }
            case 67074: {
                final bp bp;
                if ((bp = (bp)super.e.w(cj.q(0, 0))) != null) {
                    final cJ cj3;
                    (cj3 = new cJ(67073, 1)).w = bp.q;
                    cj3.q(0, 0, super.q);
                    this.q(cj3);
                }
            }
            case 67329: {
                this.I(cj);
            }
            case 67330:
            case 17236481: {
                this.j(cj);
            }
            case 17236482: {
                this.k(cj);
            }
            case 67331:
            case 17236737: {
                this.A(cj);
            }
            case 17236738: {
                this.S(cj);
            }
            case 67332:
            case 17236993: {
                this.a(cj);
            }
            case 17236994: {
                this.s(cj);
            }
            case 67333:
            case 17237265: {
                this.d(cj);
            }
            case 17237266: {
                this.f(cj);
            }
            case 17237249:
            case 275795985: {
                this.g(cj);
            }
            case 275795986: {
                this.h(cj);
            }
            case 67334: {
                this.L(cj);
            }
            case 17237506: {
                this.q(this.q.q(), this.q.w());
            }
            case 17237505: {
                this.K(cj);
            }
            case 67584: {
                final long currentTimeMillis = System.currentTimeMillis();
                final cJ cj4 = cj;
                this.n = true;
                super.q = cj4.w;
                this.q(cj4.q(0));
                bI.p = cj4.q(0, 0);
                if (a.a.q == null) {
                    a.a.q = this.e("icon_display.gif", false);
                }
                if (!"Admin".equals(bI.p)) {
                    if (a.a.r == null) {
                        a.a.r = this.e(be.w.w() + "background.gif", true);
                    }
                    if (a.a.t == null) {
                        a.a.t = this.e(be.w.w() + "chatbackground.gif", true);
                    }
                    if (AppletAbstract.q().q) {
                        (super.q = new bG(this, null)).setVisible(false);
                        AppletAbstract.q().q.setVisible(false);
                        AppletAbstract.q().add(super.q.q());
                        ((bG)super.q).validate();
                    }
                    else if (a.a.w()) {
                        final long currentTimeMillis2 = System.currentTimeMillis();
                        Object o;
                        if (!ar.q()) {
                            o = new aF(this);
                        }
                        else {
                            o = new aS(this);
                        }
                        final bB bb = new bB(this, (at)o);
                        System.out.println("Load window complete in " + (System.currentTimeMillis() - currentTimeMillis2));
                        super.q = (ce)bb.q();
                        this.q = this.q.q();
                    }
                }
                System.out.println("ex time=" + (System.currentTimeMillis() - currentTimeMillis));
            }
            case 67586: {
                this.c(cj);
            }
            case 67843: {
                final cJ cj5 = cj;
                this.w = cj5.q(0);
                this.W = cj5.q(0, 1);
                a.a.q = cj5.q(0, 2);
                if (this.q != null && ((bG)this.q).getParent() != null && ((bB)((bG)this.q).getParent()).q() != null) {
                    ((bB)((bG)this.q).getParent()).q().u();
                }
            }
            case 68608: {
                this.y(cj);
            }
            case 67338: {
                cj.q(0, 0);
            }
            case 67341:
            case 17239297: {
                this.l(cj);
            }
            case 17239298: {
                this.z(cj);
            }
            case 67844: {
                final cJ cj6 = cj;
                this.T = cj6.q(0, 4);
                this.I = cj6.q(0, 5);
                this.P = cj6.q(0, 6);
                this.A = cj6.q(0, 7);
                this.S = cj6.q(0, 9);
                this.O = cj6.q(0, 10);
            }
            case 33621775:
            case 537948401: {
                this.O(cj);
            }
            case 537948402: {
                this.P(cj);
            }
            case 50400768: {
                final cJ cj7 = cj;
                this.w.put(new Integer(cj7.q(0, 1)), new cz((bp)super.e.w(cj7.q(0, 0)), cj7.q(0, 0), cj7.q(0, 1), cj7.q(0, 1)));
            }
            case 50400769: {
                final cJ cj8 = cj;
                synchronized (this.w) {
                    final Object remove;
                    if ((remove = this.w.remove(new Integer(cj8.q(0, 1)))) instanceof cy) {
                        ((cy)remove).q();
                    }
                    break;
                }
            }
            case 50400770: {
                final cy cy;
                if ((cy = this.w.get(new Integer(cj.q(0, 1)))) != null) {
                    cy.w();
                }
            }
            case 4198416: {
                final cJ cj9 = cj;
                a.a.r = cj9.q(0, 63);
                a.a.q = cj9.q(0, 62);
                a.a.w = cj9.q(0, 54);
                a.a.e = cj9.q(0, 53);
                a.a.t = cj9.q(0, 57);
                a.a.y = cj9.q(0, 52);
                this.q = null;
            }
            case 4198464: {
                final cJ cj10 = cj;
                new V(new Frame(), cj10.q(0, 0), cj10.q(0, 1), this).setVisible(true);
            }
            case 4198496: {
                final String q4 = (cj = cj).q(0, 0);
                bw.q(cj.q(0, 1));
                bw.w(cj.q(0, 2));
                a.a.r = cj.q(0, 3);
                bw.e(cj.q(0, 4));
                bw.r(cj.q(0, 5));
                bI.p = cj.q(0, 6);
                AppletAbstract.q().q.q();
                if (!"".equals(q4)) {
                    a.a.e = q4;
                }
                a.a.u = cj.q(0, 0);
            }
            case 16974593: {
                cj = cj;
                try {
                    AppletAbstract.q().getAppletContext().showDocument(new URL(cj.q(0, 0)), "_blank");
                    break;
                }
                catch (MalformedURLException ex) {
                    System.out.println("Bad URL:" + cj.q(0, 0));
                }
            }
            case 4198465: {
                this.T(cj);
            }
            case 4198466:
            case 1074807297: {
                E(cj);
            }
            case 1074807298: {
                R(cj);
            }
            case 4198512:
            case 1074819073: {
                this.H(cj);
            }
            case 1074819074: {
                this.J(cj);
            }
            case 33621773:
            case 537948369: {
                this.D(cj);
            }
            case 537948370: {
                this.F(cj);
            }
            case 4198513: {
                this.G(cj);
            }
            case 4198528: {
                this.m(cj);
            }
            case 4198529: {
                this.Q(cj);
            }
            case 4202544: {
                this.W(cj);
            }
            case 4202592:
            case 1075863553: {
                this.b(cj);
            }
            case 1075863554: {
                this.n(cj);
                break;
            }
        }
    }
    
    private void b(final cJ cj) {
        final cq l = this.l;
        try {
            for (int i = 0; i < cj.w(); ++i) {
                final int q = cj.q(i, 0);
                bi bi = (bi)this.l.w(q);
                if (cj.q(i, 63)) {
                    if (bi != null) {
                        this.l.w(q);
                    }
                }
                else {
                    if (bi == null) {
                        bi = new bi(q, cj.q(i, 0));
                        this.l.q(bi);
                    }
                    else {
                        bi.e = cj.q(i, 0);
                    }
                    bi.y = cj.q(i, 1);
                    bi.t = cj.q(i, 2);
                    bi.q = cj.q(i, 1);
                    bi.q(cj.q(i));
                    final bn bn;
                    if ((bn = (bn)this.h.w(bi.t)) != null) {
                        bi.q = bn.q;
                    }
                }
            }
        }
        finally {
            final cq j = this.l;
        }
    }
    
    private void n(final cJ cj) {
        final cq l = this.l;
        try {
            for (int i = 0; i < cj.w(); ++i) {
                this.l.w(cj.q(i, 0));
            }
        }
        finally {
            final cq j = this.l;
        }
    }
    
    private void m(final cJ cj) {
        final cq j = super.j;
        a.a.q.q();
        try {
            for (int i = 0; i < cj.w(); ++i) {
                final int q = cj.q(i, 0);
                final String q2 = cj.q(i, 0);
                bl bl = (bl)super.j.w(q);
                if (cj.q(i, 63)) {
                    if (bl != null) {
                        super.j.w(q);
                    }
                }
                else {
                    if (bl == null) {
                        bl = new bl(q, q2);
                    }
                    else {
                        bl.e = q2;
                    }
                    bl.q(cj.q(i));
                    super.j.q(bl);
                    bl.t = cj.q(i, 1);
                    bl.r(cj.q(i, 2));
                    bl.w(cj.q(i, 3));
                    cj.q(i, 4);
                    bl.y = cj.q(i, 5);
                    bl.q = cj.q(i, 1);
                    if (bl.q == null || bl.q.trim().equals("")) {
                        bl.r(be.w.b.getRGB());
                        bl.w(be.w.n.getRGB());
                        final int o = be.w.o;
                        bl.y = be.w.i;
                        bl.q = be.w.w;
                    }
                    a.a.q.q(bl.q(4));
                    a.a.q.q(bl.t);
                    final String s;
                    if (((s = q2).length() <= 3 || !s.substring(0, 4).equals("rss:")) && bl.q(0)) {
                        a.a.q.q(bl);
                    }
                }
            }
        }
        finally {
            final cq k = super.j;
        }
        a.a.q.w();
    }
    
    private void Q(final cJ cj) {
        if (cj.w() == 0) {
            return;
        }
        for (int i = 0; i < cj.w(); ++i) {
            final String q = cj.q(i, 0);
            final int q2 = cj.q(i, 0);
            final bl bl;
            if ((bl = (bl)this.j.w(q2)) != null) {
                a.a.q.q(q, bl);
            }
            else {
                System.out.println("Can't find sc id = " + q2);
            }
        }
        a.a.q.w();
    }
    
    private void W(final cJ cj) {
        int n = 1;
        for (int i = 0; i < cj.w(); ++i) {
            this.k.q(new bh(n, cj.q(i, 0)));
            ++n;
        }
    }
    
    private static void E(final cJ cj) {
        try {
            for (int i = 0; i < cj.w(); ++i) {
                final int q = cj.q(i, 0);
                bd bd = (bd)bI.d.w(q);
                if (cj.q(i, 63)) {
                    if (bd != null) {
                        bI.d.w(q);
                    }
                }
                else {
                    if (bd == null) {
                        bd = new bd(q, cj.q(i, 0));
                        bI.d.q(bd);
                    }
                    else {
                        bd.e = cj.q(i, 0);
                    }
                    bd.q(cj.q(i));
                    bd.r(cj.q(i, 1));
                }
            }
        }
        finally {
            final cq d = bI.d;
        }
    }
    
    private static void R(final cJ cj) {
        final cq d = bI.d;
        try {
            for (int i = 0; i < cj.w(); ++i) {
                bI.d.w(cj.q(i, 0));
            }
        }
        finally {
            final cq d2 = bI.d;
        }
    }
    
    public static cq q(final int n) {
        final cq cq = new cq();
        for (int i = 0; i < bI.d.q(); ++i) {
            final bd bd;
            if ((bd = (bd)bI.d.q(i)).q(n)) {
                cq.q(bd);
            }
        }
        return cq;
    }
    
    private void T(final cJ cj) {
        for (int i = 0; i < cj.w(); ++i) {
            bV bv;
            if ((bv = (bV)this.e.w(cj.q(i, 0))) == null) {
                bv = new bV(cj.q(i, 0), cj.q(i, 0));
            }
            bv.r(cj.q(i, 1));
            this.e.q(bv);
            super.q.q(bv, false);
        }
    }
    
    public final void q(final String s, final int n, final int n2) {
        final cJ cj;
        (cj = new cJ(66310, 1)).q(0, 0, n);
        cj.q(0, 1, n2);
        cj.q(0, 2, super.q);
        cj.q(0, 0, s);
        this.q(cj);
    }
    
    public final void w(final String s, final int n, final int n2, final int n3) {
        final cJ cj;
        (cj = new cJ(66310, 1)).q(0, 0, n);
        cj.q(0, 1, n2);
        cj.q(0, 2, super.q);
        if (n3 != 0) {
            cj.q(0, 3, n3);
        }
        cj.q(0, 0, s);
        this.q(cj);
    }
    
    public final boolean p() {
        return super.p;
    }
    
    public final Image w(final String s, final boolean b) {
        return this.r(s, b);
    }
    
    public final Image e(final String s, final boolean b) {
        try {
            final URL q = super.q;
            if (b) {
                final URL url = new URL(q, "Resources/" + bI.p + "/" + s);
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
    
    public final void q(final String e, final String s, final cH ch, URL url, final int n, final String s2) {
        try {
            super.h = false;
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
            super.e = e;
            Label_0098: {
                if (cx.e < 66048) {
                    if (cx.e) {
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
            if (cx.w && cx.e < 65792) {
                final int w = cx.w;
            }
            if (!(url = (URL)(this.q != null && this.q != null && this.q != null))) {
                for (int n2 = 0; url == 0 && n2 < br.q.size(); ++n2) {
                    final int q2 = br.q(n2);
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
                        if (n2 == br.q.size() - 1) {
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
                    (super.q = new bx((bz)this)).start();
                }
                if (this.q == null) {
                    (this.q = new bJ(this)).start();
                }
                if (this.w == null) {
                    this.w = new cp("global");
                }
                if (this.q == null || this.E != n) {
                    this.q = new cp("" + n);
                }
                final cJ cj;
                (cj = new cJ(65793, 1)).q(0, 0, n);
                cj.q(0, 1, super.l);
                final cJ cj2 = cj;
                final int n3 = 0;
                final int n4 = 2;
                final int w2;
                final int e2;
                if ((w2 = a.y.w()) == 0) {
                    e2 = a.y.e();
                }
                cj2.q(n3, n4, e2);
                cj.q(0, 0, e);
                cj.q(0, 1, "en");
                cj.q(0, 2, s2);
                cj.q(0, 3, s);
                cj.q(0, 4, (String)cu.q(new Object[] { "location.href" }, null));
                cj.q(0, 0, ch);
                if (!this.y() && super.l != -999) {
                    cj.q(0, 2);
                }
                if (this.q(23)) {
                    cj.q(0, 23);
                }
                if (this.q(25)) {
                    cj.q(0, 25);
                }
                if (this.i()) {
                    cj.q(0, 3);
                }
                if (super.o && (!cx.w || cx.w != 1)) {
                    cj.q(0, 21);
                    cj.q(0, 20);
                }
                this.q(cj);
            }
            else {
                this.t();
                new b(super.q, "Error", cv.q(cv.q("No response was received from the server.  The %1 Server may not be running, or may not be a compatible version. Please contact the administrator in charge of the site."), new String[] { a.a.e }), this).setVisible(true);
            }
        }
        catch (UnknownHostException ex2) {
            this.t();
            new b(super.q, cv.q(cv.q("%1 could not locate the specified host. Please make sure you are using the correct host name."), new String[] { a.a.e }), ex2, this).setVisible(true);
        }
        catch (InterruptedIOException ex3) {
            this.t();
            new b(super.q, cv.q(cv.q("No response was received from the server.  The %1 Server may not be running, or may not be a compatible version. Please contact the administrator in charge of the site."), new String[] { a.a.e }), ex3, this).setVisible(true);
        }
        catch (NoRouteToHostException ex4) {
            this.t();
            new b(super.q, cv.q(cv.q("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { a.a.e }), ex4, this).setVisible(true);
        }
        catch (SecurityException ex5) {
            this.t();
            new b(super.q, cv.q(cv.q("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { a.a.e }), ex5, this).setVisible(true);
        }
        catch (IOException ex7) {
            final IOException ex6 = ex7;
            ex7.printStackTrace();
            this.t();
            new b(super.q, cv.q(cv.q("%1 could not connect to the specified host.  Please verify that the %1 Server is running and that you are using the correct host name."), new String[] { a.a.e }), ex6, this).setVisible(true);
        }
        catch (Exception ex9) {
            final Exception ex8 = ex9;
            ex9.printStackTrace();
            this.t();
            new b(super.q, cv.q(cv.q("An unknown error occurred while connecting to the %1 Server.  Please contact the administrator in charge of the site."), new String[] { a.a.e }), ex8, this).setVisible(true);
        }
        super.u = false;
        super.i = false;
    }
    
    public void q(final URL url, final String s) {
    }
    
    public final void i(final int n) {
    }
    
    private void q(final String s, final bp bp, final int n, final int n2, final boolean b, final long n3, final int n4) {
        this.q(s, bp, n, b, false, n3, n4, null);
    }
    
    private void q(final String s, final bp bp, final int n, final boolean b, final boolean b2, final long n2, final int n3, final byte[] array) {
        if (super.q != null) {
            int q;
            if ((q = bp.q) == super.q) {
                q = n;
            }
            bp.getName();
            final bF bf;
            (bf = new bF(this.w(s), bp, n != -1 && n != -3 && n != -2 && !b, false, (bj)super.w.w(bp.u), this)).t = (n == -3 || n == -2);
            bf.q(n2, n3);
            bf.o = bp.e;
            if (b2) {
                bf.y = array[0];
            }
            if (bf.e) {
                final bL bl;
                if ((bl = (bL)super.q.w(q)) != null) {
                    bl.q(bf);
                }
                else {
                    if (!super.k) {
                        super.q.q(bf);
                        return;
                    }
                    this.q(bf, bp);
                }
            }
            else {
                bf.q = bp.w;
                if (n == -3 || n == -2) {
                    return;
                }
                super.q.q(bf);
            }
        }
    }
    
    protected void c(final cJ cj) {
        super.u = true;
        super.i = true;
        super.i = -999;
        if (super.w.w(super.z) == null) {
            this.s(super.k);
        }
        else {
            this.s(super.z);
        }
        final bO bo = (bO)super.y.w(super.l);
        bO bo2 = (bO)super.y.w(super.j);
        if (bo == null || (bo.q == null && bo2.q != null)) {
            if (bo2 == null) {
                bo2 = (bO)super.y.q(0);
            }
            this.q(bo2);
            return;
        }
        this.q(bo);
    }
    
    private void i() {
        if (!super.u && !super.i) {
            if (super.q != null) {
                super.q.q().w();
            }
            int i;
            if (super.y.w(super.l) == null) {
                i = super.j;
            }
            else {
                i = super.l;
            }
            super.i = true;
            final bO bo;
            if ((bo = (bO)super.y.w(i)) != null && bo.w && bo.q != null && !this.q(40)) {
                super.i = -999;
                new T(super.q.q(), this, bo).setVisible(true);
            }
            super.i = i;
        }
    }
    
    protected final void q(final bV bv, int i, final long n, final int n2) {
        final int j = bv.i;
        final bO bo = (bO)super.y.w(i);
        final bO bo2 = (bO)super.y.w(bv.i);
        this.i();
        if (bv.q == super.q) {
            if (bo2 != null) {
                bo2.q = false;
            }
            bo.q = true;
            this.i = i;
            if (super.q != null) {
                if (!super.h) {
                    this.q(false, false);
                }
                super.q.q(bo);
                if (!super.q.isVisible()) {
                    super.q.setVisible(true);
                }
                super.q.q().validate();
            }
        }
        if (bo != null) {
            if (this.i == bo.q) {
                bo.y(this.q(bo.q));
            }
            if (bo2 != null && bo != null) {
                if (bo2.q != bo.q && (!bv.q(23) || this.q(24)) && (!bv.q(25) || this.q(32))) {
                    bo.q();
                }
            }
            else if (bo2 == null && bo != null && (!bv.q(23) || this.q(24)) && (!bv.q(25) || this.q(32))) {
                bo.q();
            }
            if (super.q != null) {
                super.q.w(bo);
            }
        }
        if (bo2 != null) {
            if (this.i == bo2.q) {
                bo2.y(this.q(bo2.q));
            }
            if (bo2 != null && bo != null && bo2.q != bo.q && (!bv.q(23) || this.q(24)) && (!bv.q(25) || this.q(32))) {
                bo2.w();
            }
            if (super.q != null) {
                super.q.w(bo2);
            }
        }
        final bj bj = (bj)super.w.w(bv.u);
        if (i == this.i && bv.i != i) {
            if (bo != null && !bo.q(57) && (!bv.q(23) || this.q(24)) && (!bv.q(25) || this.q(32))) {
                final bF bf;
                (bf = new bF(cv.q(cv.q("(This user has entered %1)"), new String[] { this.q(bo.getName()) }), bv, false, false, bj, this)).q(n, n2);
                final int v = super.v;
                if (super.q != null) {
                    super.q.q(bf);
                }
            }
        }
        else if (bv.i == this.i && i != this.i && bo2 != null && bo != null && !bo2.q(57) && (!bv.q(23) || this.q(24)) && (!bv.q(25) || this.q(32))) {
            final bF bf2;
            (bf2 = new bF(cv.q(cv.q("(This user has moved to %1)"), new String[] { this.q(bo.getName()) }), bv, false, false, bj, this)).q(n, n2);
            final int q = super.Q;
            if (super.q != null) {
                super.q.q(bf2);
            }
        }
        final String q2;
        if (super.q == bv.q && bo != null && ((bo2 != null && bo.q != bo2.q) || bo2 == null) && (q2 = bo.q) != null && !"".equals(q2)) {
            final bV bv2;
            (bv2 = new bV(-999, bo.getName())).r(bo.r());
            bv2.b_(bo.u());
            final bF bf3;
            (bf3 = new bF(q2, bv2, false, false, bj, this)).q(n, n2);
            bf3.i = true;
            bf3.o = bo.u();
            if (bo.r == 0) {
                bf3.s = bo.r();
            }
            else {
                bf3.s = bo.i();
            }
            if (super.q != null) {
                super.q.q(bf3);
            }
        }
        bv.i = i;
        if (super.q != null) {
            if (super.h || i == this.i) {
                super.q.q(bv, true);
            }
            else if (!super.h && j == this.i) {
                super.q.q(bv);
            }
        }
        if (!this.Q) {
            synchronized (this) {
                this.Q = true;
            }
            if (this.q != null) {
                for (i = 0; i < this.u.q(); ++i) {
                    super.q.q((aZ)this.u.q(i));
                }
                super.q.q().w();
            }
        }
    }
    
    private void Y(final cJ cj) {
        for (int i = 0; i < cj.w(); ++i) {
            if (cj.q() != null) {
                cj.q();
            }
            bp bp;
            if ((bp = (bp)super.e.w(cj.q(i, 0))) == null && this.i() && cj.q(0, 3)) {
                (bp = new bp(-999, cj.q(0, 1))).u = cj.q(0, 1);
            }
            if (bp == null) {
                (bp = new bp(-999, "Guest")).u = super.k;
            }
            if (bp != null) {
                int n;
                if ((n = bp.q) == super.q) {
                    n = cj.w;
                }
                if (!bp.e) {
                    switch (cj.w) {
                        case -3:
                        case -2:
                        case -1: {
                            if (bp.w) {
                                final int n2 = super.n;
                                break;
                            }
                            final int b = super.b;
                            break;
                        }
                        default: {
                            if (super.q.q(n)) {
                                final int b2 = super.b;
                                break;
                            }
                            final int m = super.m;
                            break;
                        }
                    }
                    if (cj.q() != null) {
                        final String q = cj.q(i, 0);
                        final bp bp2 = bp;
                        final int w = cj.w;
                        final int q2 = cj.q;
                        this.q(q, bp2, w, true, false, cj.q, cj.r, cj.q());
                    }
                    else {
                        this.q(cj.q(i, 0), bp, cj.w, cj.q, true, cj.q, cj.r);
                    }
                }
            }
        }
    }
    
    private void U(final cJ cj) {
        if (super.q != null) {
            for (int i = 0; i < cj.w(); ++i) {
                final int q = cj.q(i, 0);
                final int q2 = cj.q(i, 1);
                final int q3 = cj.q(i, 2);
                final int q4 = cj.q(i, 3);
                final int q5 = cj.q(i, 4);
                final bF bf;
                (bf = new bF(this.q(cj.q(i, 1)), this.q(cj.q(i, 0)), false, false, (bj)super.w.w(q), q2, false, false, -1, q3)).i = true;
                bf.i = q3;
                bf.o = q4;
                bf.s = q5;
                bf.q(cj.q, cj.r);
                super.q.q(bf);
                if (cj.w == super.q) {
                    final int m = super.m;
                }
                else {
                    final int b = super.b;
                }
            }
        }
    }
    
    protected final void v(final cJ cj) {
        this.i();
        for (int i = 0; i < cj.w(); ++i) {
            final int q = cj.q(i, 0);
            final int q2 = cj.q(i, 1);
            final int q3 = cj.q(i, 2);
            String s = cj.q(i, 0);
            final String q4 = cj.q(i, 1);
            if (s == null) {
                s = "";
            }
            if (q > 0) {
                if (a.a.q()) {
                    s = cl.q(this.o, s, this.R);
                }
                else {
                    s = cl.q(this.o, s, this.P);
                }
            }
            final String q5 = cO.q(s, "\r");
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
                s = string + cO.q(s.substring(string.length()), "\n");
            }
            bp bp;
            if ((bp = (bp)super.e.w(q)) == null && this.i() && cj.q(0, 3)) {
                (bp = new bp(-999, q4)).u = q2;
            }
            if (bp == null && q < -2147483638) {
                (bp = new bp(q, ChatNames.getSecurityName())).u = q;
            }
            if (bp == null) {
                bp = (bp)this.r.w(q);
            }
            if (bp != null) {
                int n2 = bp.q;
                bp.b_(q3);
                if (n2 == super.q) {
                    n2 = cj.w;
                }
                if (cj.w != super.q && cj.q(0, 20)) {
                    return;
                }
                if (!bp.e) {
                    if (!cj.q) {
                        switch (cj.w) {
                            case -3:
                            case -2:
                            case -1: {
                                if (bp.w) {
                                    final int n3 = super.n;
                                    break;
                                }
                                final int b = super.b;
                                break;
                            }
                            default: {
                                if (super.q.q(n2)) {
                                    final int b2 = super.b;
                                    break;
                                }
                                final int m = super.m;
                                break;
                            }
                        }
                    }
                    if (cj.q() != null) {
                        final String s4 = s;
                        final bp bp2 = bp;
                        final int w = cj.w;
                        final int q6 = cj.q;
                        this.q(s4, bp2, w, false, cj.q(0, 20), cj.q, cj.r, cj.q());
                    }
                    else {
                        this.q(s, bp, cj.w, cj.q, false, cj.q, cj.r);
                    }
                }
            }
        }
    }
    
    private void I(final cJ cj) {
        cj.q(-1);
        final cq u = super.u;
        try {
            for (int i = 0; i < cj.w(); ++i) {
                final int q = cj.q(i, 0);
                aZ az = (aZ)super.u.w(q);
                if (cj.q(i, 63)) {
                    if (az != null) {
                        super.u.w(q);
                    }
                }
                else {
                    if (az == null) {
                        az = new aZ(q, cj.q(i, 0));
                        super.u.q(az);
                    }
                    else {
                        az.e = cj.q(i, 0);
                    }
                    cj.q(i, 1);
                    cj.q(i, 2);
                    az.w = cj.q(i, 1);
                    az.q = cj.q(i, 2);
                    cj.q(i, 3);
                    az.q(cj.q(i));
                    if (az.q == null) {
                        az.q = this.e("banners/" + az.q, true);
                    }
                    if (super.q != null && az.q != null) {
                        super.q.q(az);
                    }
                }
            }
            if (super.q != null) {
                super.q.q().w();
            }
        }
        finally {
            final cq u2 = super.u;
        }
    }
    
    private void O(final cJ cj) {
        for (int w = cj.w(), i = 0; i < w; ++i) {
            final bg bg;
            (bg = new bg(cj.q(i, 0), cj.q(i, 0))).q(cj.q(i));
            bg.q = cj.q(i, 1);
            if (!bg.q(63) && this.q.q(bg.q) != bg) {
                bg.q = this.r("emoticons/" + bg.q, true);
            }
            this.q.q(bg);
        }
    }
    
    private void P(final cJ cj) {
        for (int i = 0; i < cj.w(); ++i) {
            this.q.q(cj.q(i, 0));
        }
    }
    
    private void A(final cJ cj) {
        if (!bQ.q) {
            bQ.q(this);
        }
        final cq w = super.w;
        try {
            for (int i = 0; i < cj.w(); ++i) {
                final int q = cj.q(i, 0);
                bj bj = (bj)super.w.w(q);
                if (cj.q(i, 63)) {
                    if (bj != null) {
                        super.w.w(q);
                    }
                }
                else {
                    if (bj == null) {
                        bj = new bj(q, cj.q(i, 0));
                        super.w.q(bj);
                        bj.q = this.r("userIcons/" + bj.getName(), true);
                    }
                    else {
                        bj.e = cj.q(i, 0);
                        bj.q = this.r("userIcons/" + bj.getName(), true);
                    }
                    bj.q(cj.q(i));
                    final bj bj2 = bj;
                    int q2 = cj.q(i, 1);
                    final bj bj3 = bj2;
                    if (q2 == 0) {
                        q2 = -1;
                    }
                    bj3.t = q2;
                    if (cj.q(i, 62)) {
                        super.k = q;
                    }
                }
            }
        }
        finally {
            final cq w2 = super.w;
        }
    }
    
    private void S(final cJ cj) {
        final cq w = this.w;
        try {
            for (int i = 0; i < cj.w(); ++i) {
                this.w.w(cj.q(i, 0));
            }
        }
        finally {
            final cq w2 = this.w;
        }
    }
    
    private void D(final cJ cj) {
        final cq h = this.h;
        try {
            for (int i = 0; i < cj.w(); ++i) {
                final int q = cj.q(i, 0);
                final String q2 = cj.q(i, 1);
                bn bn = (bn)this.h.w(q);
                if (cj.q(i, 63)) {
                    if (bn != null) {
                        this.h.w(q);
                    }
                }
                else {
                    if (bn == null) {
                        bn = new bn(q, cj.q(i, 0));
                        this.h.q(bn);
                        bn.q = this.r("starpic/" + q2, true);
                    }
                    else {
                        bn.e = cj.q(i, 0);
                    }
                    bn.q = q2;
                    bn.q(cj.q(i));
                }
            }
        }
        finally {
            final cq h2 = this.h;
        }
    }
    
    private void F(final cJ cj) {
        final cq h = this.h;
        try {
            for (int i = 0; i < cj.w(); ++i) {
                this.h.w(cj.q(i, 0));
            }
        }
        finally {
            final cq h2 = this.h;
        }
    }
    
    private void G(final cJ cj) {
        for (int i = 0; i < cj.w(); ++i) {
            final int q = cj.q(i, 0);
            final int q2 = cj.q(i, 1);
            final bV bv;
            if ((bv = (bV)super.e.w(q)) != null) {
                this.q.q(bv, false);
                this.q.put("" + q, "" + q2);
            }
        }
    }
    
    private void H(final cJ cj) {
        if (super.q != null) {
            for (int i = 0; i < cj.w(); ++i) {
                final int q = cj.q(i, 0);
                bq bq = (bq)super.t.w(q);
                if (cj.q(i, 63)) {
                    if (bq != null) {
                        super.t.w(q);
                    }
                }
                else {
                    if (bq == null) {
                        bq = new bq(q, cj.q(i, 0));
                        super.t.q(bq);
                    }
                    else {
                        bq.e = cj.q(i, 0);
                    }
                    bq.q(cj.q(i));
                    bq.u = cj.q(i, 1);
                    bq.j = cj.q(i, 2);
                    bq.k = cj.q(i, 3);
                    bq.o = bi.q((cq)null, bq.k);
                    bq.r(cj.q(i, 4));
                    bq.w(cj.q(i, 5));
                    bq.i = cj.q(i, 1);
                    bq.a = cj.q(i, 2);
                    bq.p = ((this.y.w(bq.j) != null) ? ((bk)this.y.w(bq.j)).getName() : "");
                    bq.q = ((this.w.w(bq.u) != null) ? ((bj)this.w.w(bq.u)).q : null);
                    bq.w = ((this.h.w(bq.o) != null) ? ((bn)this.h.w(bq.o)).q : null);
                }
            }
        }
    }
    
    private void J(final cJ cj) {
        final cq t = this.t;
        try {
            for (int i = 0; i < cj.w(); ++i) {
                this.t.w(cj.q(i, 0));
            }
        }
        finally {
            final cq t2 = this.t;
        }
    }
    
    private void K(final cJ cj) {
        for (int i = 0; i < cj.w(); ++i) {
            final bp bp;
            if ((bp = (bp)this.e.w(cj.q(i, 0))) != null) {
                bp.t = cj.q(i, 1);
                bp.y = cj.q(i, 2);
                bp.q = cj.q(i, 1);
                bp.w = cj.q(i, 2);
                bp.r = cj.q(i, 3);
                bp.t = cj.q(i, 4);
                bp.y = cj.q(i, 5);
                cj.q(i, 6);
                bp.q = cj.q(i, 0);
            }
        }
    }
    
    private void L(final cJ cj) {
        this.i();
        final cq e = super.e;
        try {
            for (int i = 0; i < cj.w(); ++i) {
                final int q = cj.q(i, 0);
                bV bv = (bV)super.e.w(q);
                final bO bo = (bO)super.y.w(cj.q(i, 2));
                if (cj.q(i, 63)) {
                    if (bv != null) {
                        bv.q(cj.q(i));
                        if (bo != null) {
                            if ((!bv.q(23) || this.q(24)) && (!bv.q(25) || this.q(32))) {
                                bo.w();
                            }
                            if (super.q != null) {
                                super.q.w(bo);
                            }
                        }
                        final bZ bz;
                        (bz = new bZ(bv.q, bv.getName())).t = false;
                        super.e.w(q);
                        if (super.q != null) {
                            super.q.q(bv);
                            super.q.q(bz, false);
                        }
                        final bL bl;
                        if ((bl = (bL)super.q.w(bv.q)) != null) {
                            bl.dispose();
                        }
                    }
                }
                else {
                    final String e2 = this.e(cj.q(i, 0));
                    if (bv == null) {
                        (bv = new bV(q, e2)).q(cj.q(i));
                        if ((!bv.q(23) || this.q(24)) && (!bv.q(25) || this.q(32))) {
                            super.e.q(bv);
                        }
                        else {
                            this.r.q(bv);
                        }
                    }
                    else {
                        bv.q(cj.q(i));
                        if ((bv.q(23) && !this.q(24)) || (bv.q(25) && !this.q(32))) {
                            final bV bv2 = bv;
                            final bO bo2 = bo;
                            final bV bv3 = bv2;
                            if (bv3 != null) {
                                if (bo2 != null) {
                                    bo2.w();
                                    if (super.q != null) {
                                        super.q.w(bo2);
                                    }
                                }
                                final bZ bz2;
                                (bz2 = new bZ(bv3.q, bv3.getName())).t = false;
                                if (super.q != null) {
                                    super.q.q(bv3);
                                    super.q.q(bz2, false);
                                }
                                final bL bl2;
                                if ((bl2 = (bL)super.q.w(bv3.q)) != null) {
                                    bl2.dispose();
                                }
                            }
                            this.r.q(bv);
                        }
                        bv.e = e2;
                    }
                    bv.u = cj.q(i, 1);
                    bv.q = (bj)super.w.w(bv.u);
                    bv.i = cj.q(i, 2);
                    bv.r(cj.q(i, 3));
                    bv.w(cj.q(i, 4));
                    bv.o = cj.q(i, 5);
                    final bV bv4 = bv;
                    final cq h = super.h;
                    final bV bv5 = bv4;
                    if (bv4.o == 0 && bv5.q(62)) {
                        bv5.o = 1000;
                    }
                    bv5.q = (bn)h.w(bv5.o);
                    final bi q2;
                    if ((q2 = bi.q(this.l, bv.o)) != null) {
                        bv.g = q2.y;
                    }
                    bv.f = cj.q(i, 6);
                    final int a = bv.a;
                    final int q3;
                    final int a2 = (q3 = cj.q(i, 7)) & 0xFF;
                    bv.p = (q3 >> 16 & 0xFF);
                    bv.a = a2;
                    bv.y(cj.q(i, 8));
                    bv.h = cj.q(i, 9);
                    bv.u(cj.q(i, 10));
                    final String q4 = cj.q(i, 1);
                    final String q5 = cj.q(i, 2);
                    if (q4 != null) {
                        bv.u = q4;
                    }
                    if (q5 != null) {
                        bv.i = q5;
                    }
                    if (cj.q(i, 3) != null) {
                        bv.o = cj.q(i, 3);
                        if (bv.r == null && this.q(41) && bv.o.length() == 2) {
                            bv.r = this.r("flags/" + bv.o + ".gif", false);
                        }
                    }
                    if (q == super.q) {
                        super.e = bv.getName();
                        if (bv.u != -999) {
                            super.z = bv.u;
                        }
                    }
                    Label_1111: {
                        if (bv.q(61)) {
                            final bV bv6 = bv;
                            final String name = bv.getName();
                            final bV bv7 = bv6;
                            Label_1105: {
                                if (name.length() > 4) {
                                    if (!name.endsWith(".gif") && !name.endsWith(".jpg")) {
                                        if (!name.endsWith(".bmp")) {
                                            break Label_1105;
                                        }
                                    }
                                    try {
                                        bv7.e = this.w("NickPic/" + name, true);
                                    }
                                    catch (Exception ex) {}
                                    break Label_1111;
                                }
                            }
                            bv7.e = null;
                        }
                    }
                    final bZ bz3;
                    (bz3 = new bZ(bv.q, bv.getName())).t = true;
                    bz3.u = bv.u;
                    bz3.q = (bj)super.w.w(bv.u);
                    bz3.i = bv.i;
                    bz3.q(bv.q());
                    if (super.q != null) {
                        super.q.q(bv, false);
                        super.q.q(bz3, false);
                    }
                    if (bv.a == 7 && (a == 8 || a == 9)) {
                        this.q(bv, bv.i, 0L, 0);
                    }
                    if (super.q == bv.q) {
                        final bV bv8 = bv;
                        this.q(bv8.q());
                        super.z = bv8.u;
                        super.i = bv8.i;
                        this.r(bv8.r());
                        this.w(bv8.r);
                        this.y(bv8.s);
                        this.u(bv8.d);
                        super.o = bv8.o;
                        super.f = bv8.f;
                        super.p = bv8.p;
                        super.a = bv8.a;
                        super.h = bv8.h;
                    }
                }
            }
        }
        finally {
            final cq e3 = super.e;
        }
    }
    
    private void w(final String s, final int n) {
        (this.q = new Socket(s, n)).setTcpNoDelay(true);
        this.q = new DataOutputStream(new BufferedOutputStream(this.q.getOutputStream(), 16));
        this.q = new DataInputStream(new BufferedInputStream(this.q.getInputStream()));
    }
    
    public final void q(final bF bf, final bp bp) {
        final int q;
        if ((q = bp.q) <= 0) {
            return;
        }
        if (bp.i > 0 && ((bk)super.y.w(bp.i)).q(61) && bp.q(59)) {
            return;
        }
        bM bm;
        if ((bm = (bM)super.q.w(q)) == null) {
            bm = new bL(this, bp);
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
            bm.setBounds(n4 + 2, n3 + 10, n, n2);
            super.q.q(bm, q);
        }
        if (bf != null) {
            bm.q(bf);
        }
        bm.setVisible(true);
    }
    
    public final void u() {
        try {
            if (this.c > 0) {
                this.w((AppletAbstract.q().w != null) ? AppletAbstract.q().w : this.s, br.q(0));
                this.q(this.c, true);
                this.g = false;
                (this.q = new bx((bz)this)).start();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void s(final int n) {
        final cJ cj;
        (cj = new cJ(67334, 1)).q(0, 0, super.q);
        cj.q(0, 1, n);
        cj.q(0, 0, this.getName());
        cj.w = -1;
        cj.q = -1;
        this.q(cj);
    }
    
    public final void q(final boolean b) {
        super.p = true;
    }
    
    public final void o(final int n) {
        if (super.q.q() != null) {
            super.q.q().setCursor(3);
        }
        if (this.q == null) {
            this.q = new bY(super.q.q(), this);
        }
        this.q.q(0);
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
    
    public boolean q(final cy cy) {
        if (this.e.get(cy.q()) == null) {
            this.e.put(cy.q(), cy);
            return true;
        }
        return false;
    }
    
    public final void q(final bp bp) {
        this.e.remove(bp);
    }
    
    public int q(final cy cy) {
        this.q(cy.q());
        if (super.e.w(cy.q().q) == null) {
            cy.q();
        }
        final byte b2;
        final byte b = ((b2 = (byte)new Random().nextInt()) >= 0) ? b2 : ((byte)(-b2));
        final cJ cj;
        (cj = new cJ(50400768, 1)).q(0, 0, super.q);
        cj.q(0, 1, b);
        cj.q(0, 0, cy.q());
        cj.q(0, 1, cy.w());
        cj.w = cy.q().q;
        cj.q = -1;
        this.q(cj);
        final cJ cj2;
        (cj2 = new cJ(66305, 1)).q(0, 0, "[File Transfer Requested: file name " + cy.q() + ", size " + r(cy.w()) + ". Please click this message to accept.]");
        cj2.q(0, 0, super.q);
        cj2.q(0, 20);
        cj2.q(new byte[] { b });
        cj2.w = cy.q().q;
        cj2.q = -1;
        this.q(cj2);
        this.w.put(new Integer(b), cy);
        return b;
    }
    
    public final void p(final int n) {
        synchronized (this.w) {
            final cz value;
            if ((value = this.w.get(new Integer(n))) == null) {
                return;
            }
            if (value instanceof cz) {
                if (super.e.w(value.q.q) == null) {
                    this.w.remove(new Integer(n));
                }
                else {
                    this.w.put(new Integer(n), cA.q(this, value));
                }
            }
        }
    }
    
    public final void q(final int n, final bp bp) {
        final cJ cj;
        (cj = new cJ(50400770, 1)).q(0, 0, super.q);
        cj.q(0, 1, n);
        cj.w = bp.q;
        cj.q = -1;
        this.q(cj);
    }
    
    public final void a(final int n) {
        final cy cy;
        if ((cy = this.w.remove(new Integer(n))) == null) {
            return;
        }
        final cJ cj;
        (cj = new cJ(50400769, 1)).q(0, 0, super.q);
        cj.q(0, 1, n);
        cj.w = cy.q().q;
        cj.q = -1;
        this.q(cj);
        final cJ cj2;
        (cj2 = new cJ(66305, 1)).q(0, 0, "[File Transfer Cancelled: file name " + cy.q() + ", size " + r(cy.w()) + ".]");
        cj2.q(0, 0, super.q);
        cj2.q(0, 20);
        cj2.q(new byte[] { (byte)n });
        cj2.w = cy.q().q;
        cj2.q = -1;
        this.q(cj2);
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
    
    private static void o() {
        final cL cl = new cL();
        final String q = a.cl.q("VVVVVVVVVV+iVVVVViVVVVVVViVVVVVVVViVVVVVViViVVVggggggX-iVVVVVVVYiVVVYiVVVVVYiVYiVVVVVVVVVYiVVVVgggg+iVgX-iVVVYii+ggViiX-ggVVVYiii+gggViiiX-gggY");
        try {
            cl.q(q.toCharArray());
            cl.q();
        }
        catch (Exception ex) {}
        cN.q = cl.q();
    }
    
    public bI() {
        this.w = new Hashtable();
        this.e = new Hashtable();
        o();
    }
    
    public final cq i() {
        return this.h;
    }
    
    public static boolean a() {
        return System.getProperty("java.vendor").startsWith("Sun");
    }
}
