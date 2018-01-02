// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.io.IOException;
import java.util.Hashtable;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Frame;
import java.applet.AppletContext;
import com.spilka.client.muc.AppletAbstract;
import java.net.MalformedURLException;
import java.awt.Color;
import java.util.Date;
import java.util.Calendar;
import java.net.URL;
import java.io.File;

public final class dz extends cX
{
    public String f;
    public String g;
    public int F;
    public int G;
    public int H;
    public int J;
    public int K;
    public int L;
    public int Z;
    private bP q;
    private dr q;
    private File q;
    private File w;
    private dA q;
    private dn q;
    private int B;
    private int N;
    private int M;
    private int aa;
    private int ab;
    private long y;
    private long u;
    private int ac;
    private long i;
    private String h;
    private dB q;
    private int ad;
    public int X;
    public dG q;
    private ak q;
    public dW c;
    public dW v;
    public dW b;
    public dW n;
    public dW m;
    public dW Q;
    public dW W;
    public dW E;
    public int C;
    public int V;
    public long t;
    
    public final void r() {
        super.r();
        this.v.e();
        this.b.e();
        this.n.e();
        this.m.e();
        this.x.e();
        this.W.e();
        this.E.e();
    }
    
    public final void q(final String s, final String s2, final ep ep, final URL url, final int n, final String s3) {
        this.B = 0;
        this.N = 0;
        this.aa = 0;
        this.ab = 0;
        this.M = 0;
        this.u = 0L;
        super.q(s, s2, ep, url, n, s3);
    }
    
    public final void c(es es) {
        switch (es.q()) {
            case 67335:
            case 17237761: {
                final es es2 = es;
                es = (es)this;
                final dW b = this.b;
                try {
                    for (int i = 0; i < es2.w(); ++i) {
                        final int q = es2.q(i, 0);
                        bT bt = (bT)((dz)es).b.w(q);
                        if (es2.q(i, 63)) {
                            if (bt != null) {
                                ((dz)es).b.w(q);
                            }
                        }
                        else {
                            if (bt == null) {
                                bt = new bT(q, es2.q(i, 0));
                                ((dz)es).b.q(bt);
                            }
                            else {
                                bt.t = es2.q(i, 0);
                            }
                            bt.q = es2.q(i, 0);
                            bt.q(es2.q(i));
                            bt.y(es2.q(i, 1));
                            bt.t(es2.q(i, 2));
                            bt.q = es2.q(i, 3);
                            bt.i = es2.q(i, 4);
                            bt.q(es2.q(i, 5));
                            bt.q = es2.q(i, 6);
                            bt.w = es2.q(i, 8);
                            bt.w(es2.q(i, 11));
                            bt.q = es2.q(i, 1);
                            bt.w = es2.q(i, 2);
                        }
                    }
                    break;
                }
                finally {
                    final dW b2 = ((dz)es).b;
                }
            }
            case 17237762: {
                final es es3 = es;
                es = (es)this;
                final dW b3 = this.b;
                try {
                    for (int j = 0; j < es3.w(); ++j) {
                        ((dz)es).b.w(es3.q(j, 0));
                    }
                    break;
                }
                finally {
                    final dW b4 = ((dz)es).b;
                }
            }
            case 67337:
            case 17238273: {
                final es es4 = es;
                es = (es)this;
                final dW n = this.n;
                try {
                    for (int k = 0; k < es4.w(); ++k) {
                        final int q2 = es4.q(k, 0);
                        cw cw = (cw)((dz)es).n.w(q2);
                        if (es4.q(k, 63)) {
                            if (cw != null) {
                                ((dz)es).n.w(q2);
                                if (((dz)es).q != null) {
                                    ((dz)es).q.q(cw);
                                    ((dz)es).q.w(cw);
                                }
                            }
                        }
                        else {
                            if (cw == null) {
                                cw = new cw(q2, es4.q(k, 0));
                                ((dz)es).n.q(cw);
                            }
                            else {
                                cw.t = es4.q(k, 0);
                            }
                            cw.q(es4.q(k));
                            cw.q = es4.q(k, 1);
                            cw.a = es4.q(k, 2);
                            cw.r = es4.q(k, 3);
                            cw.s = es4.q(k, 5);
                            cw.d = es4.q(k, 6);
                            cw.r = es4.q(k, 1);
                            cw.w = es4.q(k, 2);
                            cw.q = es4.q(k, 3);
                            cw.y = es4.q(k, 4);
                            cw.u = es4.q(k, 5);
                            cw.i = es4.q(k, 6);
                            cw.e = es4.q(k, 7);
                            cw.o = es4.q(k, 8);
                            cw.w(es4.q(k, 9));
                            cw.a = es4.q(k, 10);
                            cw.p = ((es4.q(k, 0) != null) ? es4.q(k, 0).toString() : "");
                            if (cw.a == 0) {
                                cw.a = 1023;
                            }
                            if (((dz)es).q != null) {
                                final cw cw2;
                                if ((cw2 = cw).r > 0L && cw2.s > 0) {
                                    final Calendar instance;
                                    (instance = Calendar.getInstance()).setTime(new Date(cw2.r));
                                    instance.add(6, cw2.s);
                                    if (System.currentTimeMillis() > instance.getTime().getTime()) {
                                        cw2.t(Color.red.getRGB());
                                    }
                                    else {
                                        instance.add(6, -5);
                                        if (System.currentTimeMillis() > instance.getTime().getTime()) {
                                            cw2.t(Color.orange.getRGB());
                                        }
                                    }
                                }
                                ((dz)es).q.q(cw);
                                ((dz)es).q.w(cw);
                            }
                        }
                    }
                    break;
                }
                finally {
                    final dW n2 = ((dz)es).n;
                }
            }
            case 17238274: {
                this.D(es);
            }
            case 68352: {
                this.F(es);
            }
            case 68353: {
                final es es5 = es;
                this.y = es5.q(0, 0);
                final String q3 = es5.q(0, 0);
                this.ab = es5.q(0, 2);
                this.ac = es5.q(0, 3);
                this.i = es5.q(0, 4);
                if (q3 != null) {
                    this.h = q3;
                }
                if (this.q != null) {
                    this.q.w();
                }
            }
            case 16844556: {
                this.G(es);
            }
            case 67338: {}
            case 67339:
            case 17238785: {
                this.U(es);
            }
            case 17238786: {
                this.I(es);
            }
            case 17239041: {
                this.O(es);
            }
            case 17239042: {
                this.P(es);
            }
            case 1075875841: {
                this.A(es);
            }
            case 1075875842: {
                this.S(es);
            }
            case 67841: {
                this.H(es);
            }
            case 65798: {
                final es es6 = es;
                this.ad = es6.q(0, 0);
                es6.q(1, 0);
                this.X = es6.q(2, 0);
                es6.q(3, 0);
                es6.q(4, 0);
            }
            case 66309: {
                final es es7 = es;
                if (es7.q() != null && es7.q().length >= 2) {
                    this.q(es7.q());
                }
            }
            case 65800: {
                final es es8 = es;
                new cB(es8.q(0, 0), Long.parseLong(es8.q(2, 0)), Integer.parseInt(es8.q(1, 0)));
            }
            case 66306: {
                this.Q(es);
            }
            case 4198448: {
                this.q.q(es);
            }
            case 4198544: {
                this.T(es);
            }
            case 4202528: {
                this.Y(es);
            }
            case 4202561: {
                this.R(es);
            }
            default: {
                super.c(es);
                break;
            }
        }
    }
    
    private void R(final es es) {
        final int[] array = new int[es.w()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = es.q(i, 0);
        }
        new aj(this.q.q(), this, ((cz)this.e.w(array[0])).i).q(array);
    }
    
    public final void a(final es es) {
        super.e = es.q(-1);
        super.a(es);
    }
    
    public final void e(final es es) {
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
                    new di(this.q.q(), this, cz, es, i).setVisible(true);
                }
            }
        }
    }
    
    public final void v(final es es) {
        super.v(es);
        this.T = es.q(0, 0);
        this.I = es.q(0, 1);
        this.C = es.q(0, 2);
        this.U = es.q(0, 3);
        this.V = es.q(0, 8);
        this.Z = es.q(0, 11);
        this.t = es.q(0);
    }
    
    public final void b(final es es) {
        super.b(es);
        this.f = es.q(0, 0);
        this.g = es.q(0, 1);
        this.r = es.q(0);
        this.F = es.q(0, 0);
        this.E = es.q(0, 1);
        this.G = es.q(0, 2);
        this.H = es.q(0, 4);
        this.J = es.q(0, 5);
        this.K = es.q(0, 6);
        this.L = es.q(0, 7);
        if (super.q != null) {
            super.q.q(this.f);
            super.q.q().q((this.E <= 0) ? 7 : this.E);
        }
    }
    
    public final void E(final es es) {
        super.E(es);
        for (int i = 0; i < es.w(); ++i) {
            final cm cm = (cm)super.w.w(es.q(i, 0));
            if (!es.q(i, 63)) {
                cm.q = bJ.q(cm.q, this.b);
            }
        }
    }
    
    private void T(final es es) {
        final dW v = this.v;
        final dW dw = (dW)this.v.clone();
        this.v.e();
        try {
            for (int i = 0; i < es.w(); ++i) {
                final int q = es.q(i, 0);
                final String q2 = es.q(i, 0);
                co co = (co)this.v.w(q);
                if (es.q(i, 63)) {
                    if (co != null) {
                        this.v.w(q);
                    }
                }
                else {
                    if (co == null) {
                        co = new co(q, q2);
                        this.v.q(co);
                    }
                    else {
                        co.t = q2;
                    }
                    co.q = es.q(i, 1);
                    co.t(es.q(i, 2));
                    co.r = es.q(i, 1);
                    co.w = es.q(i, 3);
                    co.y = bJ.q(co.w, this.b);
                    co.e = es.q(i, 4);
                    co.q = es.q(i, 5);
                    if (dw.w(co.r) == null) {
                        aP.w = aP.q;
                        if (this.q != null && ((cT)this.q).getParent() != null && ((cO)((cT)this.q).getParent()).q() != null) {
                            ((cO)((cT)super.q).getParent()).q().u();
                        }
                    }
                }
            }
        }
        finally {
            final dW v2 = this.v;
        }
    }
    
    private void Y(final es es) {
        final dW c = this.c;
        final dW dw = (dW)this.c.clone();
        try {
            for (int i = 0; i < es.w(); ++i) {
                final int q = es.q(i, 0);
                final int q2 = es.q(i, 1);
                final String q3 = es.q(i, 0);
                final String q4 = es.q(i, 1);
                final String q5 = es.q(i, 2);
                final boolean q6 = es.q(i, 0);
                if (q == 2) {
                    Date parse = new Date();
                    try {
                        parse = eq.q.parse(q5);
                    }
                    catch (Exception ex) {}
                    cp cp;
                    if ((cp = (cp)this.c.w(q2)) == null) {
                        cp = new cp(q2, q4, q3, parse, q6);
                    }
                    else {
                        cp.t = q4;
                        cp.q = q3;
                        cp.q = parse;
                        cp.w = q6;
                    }
                    cp.y(es.q(i, 2));
                    this.c.q(cp);
                    if (dw.w(cp.r) == null && !cp.w) {
                        aP.r = aP.e;
                        ((cO)((cT)super.q).getParent()).repaint();
                    }
                }
            }
        }
        finally {
            final dW c2 = this.c;
        }
    }
    
    private void U(final es es) {
        final dW m = this.m;
        try {
            for (int i = 0; i < es.w(); ++i) {
                final int q = es.q(i, 0);
                bX bx = (bX)this.m.w(q);
                if (es.q(i, 63)) {
                    if (bx != null) {
                        this.m.w(q);
                    }
                }
                else {
                    if (bx == null) {
                        bx = new bX(q, es.q(i, 0));
                        this.m.q(bx);
                    }
                    else {
                        bx.t = es.q(i, 0);
                    }
                    bx.q(es.q(i));
                    bx.q = es.q(i, 1);
                }
            }
        }
        finally {
            final dW j = this.m;
        }
    }
    
    private void I(final es es) {
        final dW m = this.m;
        try {
            for (int i = 0; i < es.w(); ++i) {
                this.m.w(es.q(i, 0));
            }
        }
        finally {
            final dW j = this.m;
        }
    }
    
    private void O(final es es) {
        final dW q = this.Q;
        try {
            for (int i = 0; i < es.w(); ++i) {
                final int q2 = es.q(i, 0);
                bW bw = (bW)this.Q.w(q2);
                if (es.q(i, 63)) {
                    if (bw != null) {
                        this.Q.w(q2);
                    }
                }
                else {
                    if (bw == null) {
                        bw = new bW(q2, es.q(i, 0));
                        this.Q.q(bw);
                    }
                    else {
                        bw.t = es.q(i, 0);
                    }
                    bw.q(es.q(i));
                    bw.q = es.q(i, 1);
                    bw.q = es.q(i, 3);
                }
            }
        }
        finally {
            final dW q3 = this.Q;
        }
    }
    
    private void P(final es es) {
        final dW q = this.Q;
        try {
            for (int i = 0; i < es.w(); ++i) {
                this.Q.w(es.q(i, 0));
            }
        }
        finally {
            final dW q2 = this.Q;
        }
    }
    
    private void A(final es es) {
        final dW p = this.p;
        try {
            for (int i = 0; i < es.w(); ++i) {
                final int q = es.q(i, 0);
                bV bv = (bV)this.p.w(q);
                if (es.q(i, 63)) {
                    if (bv != null) {
                        this.p.w(q);
                    }
                }
                else {
                    if (bv == null) {
                        bv = new bV(q, es.q(i, 0));
                        this.p.q(bv);
                    }
                    else {
                        bv.t = es.q(i, 0);
                    }
                    bv.q(es.q(i));
                }
            }
        }
        finally {
            final dW p2 = this.p;
        }
    }
    
    private void S(final es es) {
        final dW p = this.p;
        try {
            for (int i = 0; i < es.w(); ++i) {
                this.p.w(es.q(i, 0));
            }
        }
        finally {
            final dW p2 = this.p;
        }
    }
    
    private void D(final es es) {
        final dW n = this.n;
        try {
            for (int i = 0; i < es.w(); ++i) {
                final int q = es.q(i, 0);
                final cw cw = (cw)this.n.w(q);
                this.n.w(q);
                if (this.q != null) {
                    this.q.q(cw);
                    this.q.w(cw);
                }
            }
        }
        finally {
            final dW n2 = this.n;
        }
    }
    
    public final void q(final URL url, final String s) {
        if (super.y) {
            final AppletContext appletContext;
            (appletContext = AppletAbstract.q().getAppletContext()).showDocument(url, s);
            if (ef.w && ef.w == 1 && ef.e < 65792 && url.getProtocol().equalsIgnoreCase("http")) {
                appletContext.showDocument(url, s);
            }
        }
    }
    
    private final void F(final es es) {
        final dW n = this.n;
        try {
            this.B = 0;
            this.N = 0;
            this.aa = 0;
            this.M = 0;
            for (int i = 0; i < es.w(); ++i) {
                final cw cw;
                if ((cw = (cw)this.n.w(es.q(i, 0))) != null) {
                    final int q = es.q(i, 1);
                    final int q2 = es.q(i, 2);
                    final int q3 = es.q(i, 3);
                    final int q4 = es.q(i, 10);
                    this.B += q;
                    this.N += q4;
                    this.aa += q3;
                    cw.o = q + q4;
                    cw.p = q4;
                    cw.w = q2 + q4;
                    this.M += cw.w;
                    cw.e = q3 + q4;
                    cw.q = es.q(i, 4);
                    cw.w = es.q(i, 6);
                    cw.e = es.q(i, 8);
                    if (this.q != null) {
                        this.q.q(cw);
                        this.q.w(cw);
                    }
                    if (cw.q > this.u) {
                        this.u = cw.q;
                    }
                }
            }
            if (this.B + this.N > this.M) {
                this.M = this.B + this.N;
            }
            if (this.q != null) {
                this.q.w();
            }
            this.q.e();
        }
        finally {
            final dW n2 = this.n;
        }
    }
    
    private void G(final es es) {
        final dW w = this.W;
        try {
            for (int i = 0; i < es.w(); ++i) {
                final int q = es.q(i, 0);
                cD cd = (cD)this.W.w(q);
                if (es.q(i, 63)) {
                    this.W.w(q);
                }
                else {
                    if (cd == null) {
                        cd = new cD(q, es.q(i, 0));
                        this.W.q(cd, q);
                    }
                    else {
                        cd.t = es.q(i, 0);
                    }
                    cd.q(es.q(i));
                }
            }
        }
        finally {
            final dW w2 = this.W;
        }
    }
    
    public final void j(final es es) {
    }
    
    private void H(final es es) {
        final dW e = this.E;
        try {
            for (int i = 0; i < es.w(); ++i) {
                final int q = es.q(i, 0);
                dB q2 = (dB)this.E.w(q);
                if (es.q(i, 63)) {
                    this.E.w(q);
                }
                else {
                    if (q2 == null) {
                        q2 = new dB(q, es.q(i, 0));
                        es.q(i, 0, this.E.q(q2));
                    }
                    else {
                        q2.t = es.q(i, 0);
                    }
                    q2.e = es.q(i, 1);
                    q2.q = es.q(i, 2);
                    q2.w = es.q(i, 3);
                    q2.q = new Color(es.q(i, 4));
                    q2.w = new Color(es.q(i, 5));
                    q2.e = new Color(es.q(i, 6));
                    q2.t = new Color(es.q(i, 7));
                    q2.r = new Color(es.q(i, 8));
                    q2.p = es.q(i, 9);
                    q2.a = es.q(i, 10);
                    q2.s = es.q(i, 11);
                    q2.d = es.q(i, 12);
                    q2.f = es.q(i, 13);
                    q2.o = es.q(i, 16);
                    q2.q = es.q(i, 1);
                    q2.i = es.q(i, 2);
                    q2.w = es.q(i, 3);
                    q2.e = es.q(i, 4);
                    q2.r = es.q(i, 5);
                    q2.y = es.q(i, 6);
                    q2.u = es.q(i, 7);
                    es.q(i, 8);
                    String q3;
                    if ((q3 = es.q(i, 9)) == null) {
                        q3 = "";
                    }
                    q2.o = q3;
                    es.q(i, 14);
                    es.q(i, 15);
                    q2.q(es.q(i));
                    if (q2.q(62)) {
                        this.q = q2;
                    }
                }
            }
        }
        finally {
            final dW e2 = this.E;
        }
    }
    
    public final void u(final es es) {
        final int q = es.q(0, 1);
        if (es.q(0, 0) == 1 && q == 68096) {
            new b(super.q, eb.q("Note"), eb.q("Your password could not be changed because your old password was not entered correctly."), this).setVisible(true);
            this.r(this.q(58));
            return;
        }
        super.u(es);
    }
    
    public final void e() {
        this.c.e();
        aP.r = null;
        if (this.q != null) {
            this.q.dispose();
            this.q = null;
        }
        if (this.q != null) {
            this.q.dispose();
            this.q = null;
        }
        super.e();
        if (this.Q) {
            this.r();
        }
    }
    
    private es q() {
        final dW e = this.E;
        try {
            final es es = new es(67841, this.E.q());
            for (int i = 0; i < this.E.q(); ++i) {
                final dB db = (dB)this.E.q(i);
                es.q(i, 0, db.r);
                es.q(i, 1, db.e);
                es.q(i, 2, db.q);
                es.q(i, 3, db.w);
                es.q(i, 4, db.q.getRGB());
                es.q(i, 5, db.w.getRGB());
                es.q(i, 6, db.e.getRGB());
                es.q(i, 7, db.t.getRGB());
                es.q(i, 8, db.r.getRGB());
                es.q(i, 9, db.p);
                es.q(i, 10, db.a);
                es.q(i, 11, db.s);
                es.q(i, 12, db.d);
                es.q(i, 13, db.f);
                es.q(i, 14, db.g);
                es.q(i, 15, db.h);
                es.q(i, 16, db.o);
                es.q(i, 0, db.getName());
                es.q(i, 1, db.q);
                es.q(i, 2, db.i);
                es.q(i, 3, db.w);
                es.q(i, 4, db.e);
                es.q(i, 5, db.r);
                es.q(i, 6, db.y);
                es.q(i, 7, db.u);
                es.q(i, 8, db.p);
                es.q(i, 9, db.o);
                es.q(i, db.q());
            }
            return es;
        }
        finally {
            final dW e2 = this.E;
        }
    }
    
    public final void u() {
        if (!super.y) {
            if (this.q != null) {
                this.q.e = super.v;
                this.q.q = super.x;
                this.q.w = super.c;
                this.q.q = cf.w.o;
                this.q.w = cf.w.p;
                this.q.e = cf.w.a;
                this.q.t = cf.w.s;
                this.q.r = cf.w.d;
                this.q.p = super.b;
                this.q.a = super.n;
                this.q.s = super.m;
                this.q.d = super.Q;
                this.q.f = super.W;
                this.q.g = cf.w.w;
                this.q.h = cf.w.q;
                this.q.p = cf.w.q;
                this.q.o = super.q;
                this.q.q = this.getName();
                this.q.i = super.d;
                this.q.w = super.q;
                this.q.e = super.e;
                this.q.r = super.r;
                this.q.y = super.u;
                this.q.u = super.y;
                this.q.o = super.s;
                if (super.w) {
                    this.q.i(32);
                }
                if (super.z) {
                    this.q.i(60);
                }
                if (super.w > 0) {
                    this.q.i(super.w);
                }
                else {
                    this.q.o(1);
                    this.q.o(0);
                }
                if (super.a) {
                    this.q.i(25);
                }
                else {
                    this.q.o(25);
                }
            }
            if (!super.y && !this.q.exists()) {
                this.q.mkdirs();
            }
            final cC cc = new cC(new File(this.q, "settings.digi"), this.w, true);
            try {
                cc.q(this.q());
            }
            finally {
                cc.q();
            }
        }
    }
    
    public final void n(final es es) {
        super.n(es);
        if (dz.a.equals("Admin")) {
            this.q = new dn(this);
            return;
        }
        this.q = null;
        super.q = (dK)new cO(this, aO.q(this)).q();
        this.q = this.q.q();
    }
    
    public final void m(final es es) {
        if (this.q != null) {
            this.q.setVisible(true);
        }
        else {
            super.m(es);
        }
        if (this.q(58)) {
            this.r(true);
        }
    }
    
    protected final void r(final es es) {
        super.r(es);
        if ((this.q(59) || this.q(60)) && (this.o == -999 || !((db)super.y.w(this.o)).q(61))) {
            aO.q(false);
        }
    }
    
    public final void p() {
        this.q.setCursor(3);
        if (this.q == null) {
            this.q = new dr(this.q, this);
        }
        this.q.setVisible(true);
        this.q.validate();
        this.q.toFront();
        this.q.setCursor(0);
    }
    
    public final void a() {
        boolean b;
        if ("Admin".equals(cX.a)) {
            b = false;
        }
        else {
            b = true;
        }
        new ca((this.q == null) ? super.q.q() : this.q, this, 0, b ? 1 : 0).setVisible(true);
    }
    
    public final void s() {
        boolean b;
        if ("Admin".equals(cX.a)) {
            b = false;
        }
        else {
            b = true;
        }
        (this.q = new ak((this.q != null) ? this.q : super.q.q(), this, b ? 1 : 0)).setVisible(true);
    }
    
    public final void d() {
        this.q("/Restart", 0);
    }
    
    public final void f() {
        if (super.q.q() != null) {
            super.q.q().setCursor(3);
        }
        if (this.q == null) {
            this.q = new bP(super.q.q(), this);
        }
        this.q.setVisible(true);
        this.q.toFront();
        if (super.q.q() != null) {
            super.q.q().setCursor(0);
        }
    }
    
    public final void r(final boolean b) {
        new dx((this.q == null) ? super.q.q() : this.q, "Change Password", this, b).setVisible(true);
        this.q(58, b);
    }
    
    public final void g() {
        new dx((this.q == null) ? super.q.q() : this.q, "Change FTP Password", this, false).setVisible(true);
    }
    
    public final void q(final dB q) {
        if (this.q != null) {
            this.q.o(62);
        }
        (this.q = q).i(62);
        super.v = q.e;
        super.x = q.q;
        super.c = q.w;
        cf.w.o = q.q;
        cf.w.p = q.w;
        cf.w.a = q.e;
        cf.w.s = q.t;
        cf.w.d = q.r;
        cf.w.f = q.r.darker();
        cf.w.g = cf.w.a;
        super.b = q.p;
        super.n = q.a;
        super.m = q.s;
        super.Q = q.d;
        super.W = q.f;
        super.q = q.o;
        super.t = q.q;
        super.d = q.i;
        super.q = q.w;
        super.e = q.e;
        super.r = q.r;
        super.u = q.y;
        super.y = q.u;
        super.s = q.o;
        super.w = q.q(32);
        super.z = q.q(60);
        super.a = q.q(25);
        super.s = q.o;
        if (q.q(1)) {
            super.w = 1;
        }
        else if (q.q(0)) {
            super.w = 2;
        }
        else {
            super.w = -999;
        }
        cf.w.q = new String(q.p);
        cf.w.w = q.g;
        cf.w.q = q.h;
        if (this.q != null) {
            this.q.q(q);
        }
    }
    
    public final void t() {
        super.t();
        System.exit(0);
    }
    
    public final void y() {
        if (super.y) {
            this.q.q();
        }
        else {
            this.q.w();
        }
        this.q = null;
    }
    
    public final void q(final cS cs) {
        if (this.q == null) {
            super.q.q();
            this.q = new dG(this);
            final Dimension screenSize;
            int n = (screenSize = Toolkit.getDefaultToolkit().getScreenSize()).width / 2 - 20;
            int n2 = screenSize.height / 2 - 20;
            if (n > 400) {
                n = 400;
            }
            if (n2 > 300) {
                n2 = 300;
            }
            this.q.reshape(2, n2 + 10, n, n2);
            aO.q(true);
        }
        this.q.q(cs);
        if (!this.q.isShowing()) {
            this.q.setVisible(true);
        }
    }
    
    public final void q(final byte[] array) {
        if (this.q != null) {
            this.q.q(array);
            this.q.w();
        }
    }
    
    public final void q(final cS cs, final dj dj) {
        new dD(this.q, this, cs, dj);
        if (this.q != null) {
            this.q.w();
        }
    }
    
    public dz(final AppletAbstract q) {
        this.F = 0;
        this.G = 2;
        this.Z = 0;
        this.c = new dW();
        this.v = new dW();
        this.b = new dW();
        this.n = new dW();
        this.m = new dW();
        this.Q = new dW();
        this.W = new dW();
        this.E = new dW();
        this.C = 5;
        this.V = 5;
        this.t = 0L;
        this.u = 0L;
        super.y = true;
        cE.q(cE.w);
        this.q = q;
        try {
            this.o();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        super.k = true;
    }
    
    public dz() {
        this.F = 0;
        this.G = 2;
        this.Z = 0;
        this.c = new dW();
        this.v = new dW();
        this.b = new dW();
        this.n = new dW();
        this.m = new dW();
        this.Q = new dW();
        this.W = new dW();
        this.E = new dW();
        this.C = 5;
        this.V = 5;
        this.t = 0L;
        this.u = 0L;
        null.get((Object)"floodControl");
        super.y = false;
        try {
            super.p = true;
            this.q = null;
            final File file = new File(System.getProperty("user.dir"));
            this.q = new File(file, "Settings" + File.separator + "ChatMaster");
            this.w = new File(file, "Temp");
            final File file2;
            if ((file2 = new File(this.q, "settings.digi")).exists()) {
                cC cc;
                for (int q = (cc = new cC(file2, this.w, 0 != 0)).q(), i = 0; i < q; ++i) {
                    this.c(cc.q(i));
                }
            }
            if (!this.E.q(0)) {
                final dB db;
                (db = new dB(0, "Admin")).e = 0;
                db.q = "Admin";
                this.E.q(db);
            }
            if (!this.E.q(1)) {
                final dB db2;
                (db2 = new dB(1, "ChatMaster")).e = -999;
                db2.q = "ChatMaster";
                this.E.q(db2);
            }
            if (!this.E.q(1000)) {
                final dB db3;
                (db3 = new dB(1000, "Other")).e = -999;
                this.E.q(db3);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        this.q = new dA(this);
        if (this.q == null) {
            this.q = (dB)this.E.q(0);
        }
        this.q(this.q);
        super.k = true;
        this.q.setVisible(true);
    }
    
    public final int q(final String s) {
        for (int i = 0; i < this.x.q(); ++i) {
            final cl cl = (cl)this.x.q(i);
            if (s.equals(cl.getName())) {
                return cl.r;
            }
        }
        return 0;
    }
    
    public final String q(final int n) {
        final cl cl;
        if ((cl = (cl)this.x.w(n)) != null) {
            return cl.getName();
        }
        return a.cl.q;
    }
    
    public final dW o() {
        return this.x;
    }
    
    public final int p() {
        return this.M;
    }
    
    public final int a() {
        return this.aa;
    }
    
    public final int s() {
        return this.ac;
    }
    
    public final long e() {
        return this.i;
    }
    
    public final String w() {
        return this.h;
    }
    
    public final int d() {
        return this.ab;
    }
    
    public final int f() {
        return this.B;
    }
    
    public final int g() {
        return this.N;
    }
    
    public final long r() {
        return this.u;
    }
    
    public final long t() {
        return this.y;
    }
    
    public final int h() {
        return this.ad;
    }
}
