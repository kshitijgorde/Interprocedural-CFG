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
import java.awt.Color;
import java.util.Calendar;
import java.util.Date;
import java.net.MalformedURLException;
import java.net.URL;
import java.io.File;

public final class cT extends aN
{
    public String p;
    public String s;
    public int j;
    public int k;
    public int l;
    public int z;
    public int x;
    public int c;
    private v q;
    private ek q;
    private File q;
    private File w;
    private dk q;
    private aS q;
    protected int v;
    protected int b;
    protected int n;
    protected int m;
    protected int Q;
    protected long w;
    protected long r;
    protected int W;
    protected long t;
    protected String d;
    private cE q;
    protected int E;
    public int R;
    public dr q;
    private a q;
    public M q;
    public M w;
    public M e;
    public M r;
    public M t;
    public M y;
    public M u;
    public M i;
    public int T;
    public int Y;
    public long y;
    
    public final void q() {
        super.q();
        this.w.q();
        this.e.q();
        this.r.q();
        this.t.q();
        this.y.q();
        this.u.q();
        this.i.q();
    }
    
    public final void q(final String s, final String s2, final dD dd, final URL url, final int n, final String s3) {
        this.v = 0;
        this.b = 0;
        this.m = 0;
        this.Q = 0;
        this.n = 0;
        this.r = 0L;
        super.q(s, s2, dd, url, n, s3);
    }
    
    public final void q(final dI di) {
        switch (di.q()) {
            case 67335:
            case 17237761: {
                System.out.println("get account packet time=" + System.currentTimeMillis());
                try {
                    for (int i = 0; i < di.w(); ++i) {
                        final int q = di.q(i, 0);
                        cu cu = (cu)this.e.w(q);
                        if (di.q(i, 63)) {
                            if (cu != null) {
                                this.e.w(q);
                            }
                        }
                        else {
                            if (cu == null) {
                                cu = new cu(q, di.q(i, 0));
                                this.e.q(cu);
                            }
                            else {
                                cu.a = di.q(i, 0);
                            }
                            cu.q = di.q(i, 0);
                            cu.q(di.q(i));
                            cu.p(di.q(i, 1));
                            cu.o(di.q(i, 2));
                            cu.q = di.q(i, 3);
                            cu.h = di.q(i, 4);
                            cu.q(di.q(i, 5));
                            cu.q = di.q(i, 6);
                            cu.w = di.q(i, 8);
                            cu.w(di.q(i, 13));
                            cu.q = di.q(i, 1);
                            cu.w = di.q(i, 2);
                        }
                    }
                    break;
                }
                finally {}
            }
            case 17237762: {
                this.P(di);
            }
            case 67337:
            case 17238273: {
                this.D(di);
            }
            case 17238274: {
                this.F(di);
            }
            case 68352: {
                this.G(di);
            }
            case 68353: {
                this.w = di.q(0, 0);
                final String q2 = di.q(0, 0);
                this.Q = di.q(0, 2);
                this.W = di.q(0, 3);
                this.t = di.q(0, 4);
                if (q2 != null) {
                    this.d = q2;
                }
                if (this.q != null) {
                    this.q.q();
                }
            }
            case 16844556: {
                this.H(di);
            }
            case 67338: {}
            case 67339:
            case 17238785: {
                this.A(di);
            }
            case 17238786: {
                this.S(di);
            }
            case 67841: {
                this.J(di);
            }
            case 65798: {
                this.E = di.q(0, 0);
                di.q(1, 0);
                this.R = di.q(2, 0);
                di.q(3, 0);
                di.q(4, 0);
            }
            case 66309: {
                if (di.q() != null && di.q().length >= 2) {
                    this.q(di.q());
                }
            }
            case 65800: {
                new dm(di.q(0, 0), Long.parseLong(di.q(2, 0)), Integer.parseInt(di.q(1, 0)));
            }
            case 66306: {
                this.y(di);
            }
            case 4198448: {
                this.q.q(di);
            }
            case 4198544: {
                this.I(di);
            }
            case 4202528: {
                this.O(di);
            }
            case 4202561: {
                this.U(di);
            }
            case 4202592:
            case 1075863553: {
                this.T(di);
            }
            case 1075863554: {
                this.Y(di);
            }
            default: {
                super.q(di);
                break;
            }
        }
    }
    
    private void T(final dI di) {
        try {
            for (int i = 0; i < di.w(); ++i) {
                final int q = di.q(i, 0);
                de de = (de)this.y.w(q);
                if (di.q(i, 63)) {
                    if (de != null) {
                        this.y.w(q);
                    }
                }
                else {
                    if (de == null) {
                        de = new de(q, di.q(i, 0));
                        this.y.q(de);
                    }
                    else {
                        de.a = di.q(i, 0);
                    }
                    de.w = di.q(i, 1);
                    de.q = di.q(i, 2);
                    de.w = di.q(i, 1);
                    de.q(di.q(i));
                    final cx cx;
                    if ((cx = (cx)this.b.w(de.q)) != null) {
                        de.q = cx.q;
                    }
                }
            }
        }
        finally {}
    }
    
    private void Y(final dI di) {
        try {
            for (int i = 0; i < di.w(); ++i) {
                this.y.w(di.q(i, 0));
            }
        }
        finally {}
    }
    
    private void U(final dI di) {
        final int[] array = new int[di.w()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = di.q(i, 0);
        }
        final bt bt = new bt(this.q.q(), this, ((p)this.a.w(array[0])).u);
        final int[] array2 = array;
        final bt bt2 = bt;
        bt.q.e();
        synchronized (bt2.q.a) {
            for (int j = 0; j < array2.length; ++j) {
                final aO ao;
                if ((ao = (aO)bt2.q.a.w(array2[j])) != null) {
                    bt2.q.q(ao, true);
                }
            }
        }
        final F f;
        f.setVisible(true);
    }
    
    public final void a(final dI di) {
        super.o = di.q(-1);
        super.a(di);
    }
    
    public final void s(final dI di) {
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
                    new N(this.q.q(), this, p, di, i).setVisible(true);
                }
            }
        }
    }
    
    public final void w(final dI di) {
        super.w(di);
        this.Z = di.q(0, 0);
        this.V = di.q(0, 1);
        this.T = di.q(0, 2);
        this.C = di.q(0, 3);
        this.Y = di.q(0, 8);
        this.y = di.q(0);
    }
    
    public final void e(final dI di) {
        super.e(di);
        this.p = di.q(0, 0);
        this.s = di.q(0, 1);
        this.p = di.q(0);
        this.j = di.q(0, 0);
        this.K = di.q(0, 1);
        this.k = di.q(0, 2);
        this.l = di.q(0, 4);
        this.z = di.q(0, 5);
        this.x = di.q(0, 6);
        this.c = di.q(0, 7);
        if (super.q != null) {
            super.q.q(this.p);
            super.q.q().q = ((this.K <= 0) ? 7 : this.K);
        }
    }
    
    public final void i(final dI di) {
        super.i(di);
        for (int i = 0; i < di.w(); ++i) {
            final aZ az = (aZ)super.p.w(di.q(i, 0));
            if (!di.q(i, 63)) {
                az.q = aY.q(az.q, this.e);
            }
        }
    }
    
    private void I(final dI di) {
        final M m = (M)this.w.clone();
        try {
            for (int i = 0; i < di.w(); ++i) {
                final int q = di.q(i, 0);
                final String q2 = di.q(i, 0);
                D d = (D)this.w.w(q);
                if (di.q(i, 63)) {
                    if (d != null) {
                        this.w.w(q);
                    }
                }
                else {
                    if (d == null) {
                        d = new D(q, q2);
                        this.w.q(d);
                    }
                    else {
                        d.a = q2;
                    }
                    d.q = di.q(i, 1);
                    d.o(di.q(i, 2));
                    d.r = di.q(i, 1);
                    d.w = di.q(i, 3);
                    d.t = aY.q(d.w, this.e);
                    d.e = di.q(i, 4);
                    d.q = di.q(i, 5);
                    if (m.w(d.s) == null) {
                        cW.w = cW.q;
                        if (this.q != null && ((bT)this.q).getParent() != null && ((aP)((bT)this.q).getParent()).q != null) {
                            ((aP)((bT)super.q).getParent()).q.u();
                        }
                    }
                }
            }
        }
        finally {}
    }
    
    private void O(final dI di) {
        final M m = (M)this.q.clone();
        try {
            for (int i = 0; i < di.w(); ++i) {
                final int q = di.q(i, 0);
                final int q2 = di.q(i, 1);
                final String q3 = di.q(i, 0);
                final String q4 = di.q(i, 1);
                final String q5 = di.q(i, 2);
                final boolean q6 = di.q(i, 0);
                if (q == 2) {
                    Date parse = new Date();
                    try {
                        parse = a.d.q.parse(q5);
                    }
                    catch (Exception ex) {}
                    dV dv;
                    if ((dv = (dV)this.q.w(q2)) == null) {
                        dv = new dV(q2, q4, q3, parse, q6);
                    }
                    else {
                        dv.a = q4;
                        dv.q = q3;
                        dv.q = parse;
                        dv.q = q6;
                    }
                    dv.p(di.q(i, 2));
                    this.q.q(dv);
                    if (m.w(dv.s) == null && !dv.q) {
                        cW.r = cW.e;
                        ((aP)((bT)super.q).getParent()).repaint();
                    }
                }
            }
        }
        finally {}
    }
    
    private void P(final dI di) {
        try {
            for (int i = 0; i < di.w(); ++i) {
                this.e.w(di.q(i, 0));
            }
        }
        finally {}
    }
    
    private void A(final dI di) {
        try {
            for (int i = 0; i < di.w(); ++i) {
                final int q = di.q(i, 0);
                bW bw = (bW)this.t.w(q);
                if (di.q(i, 63)) {
                    if (bw != null) {
                        this.t.w(q);
                    }
                }
                else {
                    if (bw == null) {
                        bw = new bW(q, di.q(i, 0));
                        this.t.q(bw);
                    }
                    else {
                        bw.a = di.q(i, 0);
                    }
                    bw.q(di.q(i));
                    bw.q = di.q(i, 1);
                }
            }
        }
        finally {}
    }
    
    private void S(final dI di) {
        try {
            for (int i = 0; i < di.w(); ++i) {
                this.t.w(di.q(i, 0));
            }
        }
        finally {}
    }
    
    private void D(final dI di) {
        try {
            for (int i = 0; i < di.w(); ++i) {
                final int q = di.q(i, 0);
                aK ak = (aK)this.r.w(q);
                if (di.q(i, 63)) {
                    if (ak != null) {
                        this.r.w(q);
                        if (this.q != null) {
                            this.q.q(ak);
                            this.q.w(ak);
                        }
                    }
                }
                else {
                    if (ak == null) {
                        ak = new aK(q, di.q(i, 0));
                        this.r.q(ak);
                    }
                    else {
                        ak.a = di.q(i, 0);
                    }
                    ak.q(di.q(i));
                    ak.q = di.q(i, 1);
                    ak.t = di.q(i, 2);
                    ak.w = di.q(i, 3);
                    ak.y = di.q(i, 5);
                    ak.u = di.q(i, 6);
                    ak.r = di.q(i, 1);
                    ak.w = di.q(i, 2);
                    ak.q = di.q(i, 3);
                    ak.t = di.q(i, 4);
                    ak.y = di.q(i, 5);
                    ak.u = di.q(i, 6);
                    ak.e = di.q(i, 7);
                    ak.i = di.q(i, 8);
                    ak.q(di.q(i, 9));
                    ak.p = di.q(i, 10);
                    ak.o = ((di.q(i, 0) != null) ? di.q(i, 0).toString() : "");
                    if (ak.t == 0) {
                        ak.t = 1023;
                    }
                    if (this.q != null) {
                        final aK ak2;
                        if ((ak2 = ak).w > 0L && ak2.y > 0) {
                            final Calendar instance;
                            (instance = Calendar.getInstance()).setTime(new Date(ak2.w));
                            instance.add(6, ak2.y);
                            if (System.currentTimeMillis() > instance.getTime().getTime()) {
                                ak2.o(Color.red.getRGB());
                            }
                            else {
                                instance.add(6, -5);
                                if (System.currentTimeMillis() > instance.getTime().getTime()) {
                                    ak2.o(Color.orange.getRGB());
                                }
                            }
                        }
                        this.q.q(ak);
                        this.q.w(ak);
                    }
                }
            }
        }
        finally {}
    }
    
    private void F(final dI di) {
        try {
            for (int i = 0; i < di.w(); ++i) {
                final int q = di.q(i, 0);
                final aK ak = (aK)this.r.w(q);
                this.r.w(q);
                if (this.q != null) {
                    this.q.q(ak);
                    this.q.w(ak);
                }
            }
        }
        finally {}
    }
    
    public final void q(final URL url, final String s) {
        if (super.y) {
            final AppletContext appletContext;
            (appletContext = a.m.q().getAppletContext()).showDocument(url, s);
            if (cK.w && cK.w == 1 && cK.e < 65792 && url.getProtocol().equalsIgnoreCase("http")) {
                appletContext.showDocument(url, s);
            }
        }
    }
    
    private final void G(final dI di) {
        try {
            this.v = 0;
            this.b = 0;
            this.m = 0;
            this.n = 0;
            for (int i = 0; i < di.w(); ++i) {
                final aK ak;
                if ((ak = (aK)this.r.w(di.q(i, 0))) != null) {
                    final int q = di.q(i, 1);
                    final int q2 = di.q(i, 2);
                    final int q3 = di.q(i, 3);
                    final int q4 = di.q(i, 10);
                    this.v += q;
                    this.b += q4;
                    this.m += q3;
                    ak.r = q + q4;
                    ak.w = q2 + q4;
                    this.n += ak.w;
                    ak.e = q3 + q4;
                    ak.q = di.q(i, 4);
                    di.q(i, 6);
                    di.q(i, 8);
                    if (this.q != null) {
                        this.q.q(ak);
                        this.q.w(ak);
                    }
                    if (ak.q > this.r) {
                        this.r = ak.q;
                    }
                }
            }
            if (this.v + this.b > this.n) {
                this.n = this.v + this.b;
            }
            if (this.q != null) {
                this.q.q();
            }
            final aS q5;
            if (!(q5 = this.q).q) {
                String s = "";
                if (q5.q.size() > 0) {
                    s = s + "You Have " + q5.q.size() + " Sites Already Expired\n";
                }
                if (q5.w.size() > 0) {
                    s = s + "You Have " + q5.w.size() + " Sites About To Expire\n";
                }
                if (s.length() > 0) {
                    new dd(new Frame(), be.w("Note"), s + "\nExpired Sites Will not Work Unless You Set New End Date", q5.q).setVisible(true);
                    q5.q = true;
                }
            }
        }
        finally {}
    }
    
    private void H(final dI di) {
        try {
            for (int i = 0; i < di.w(); ++i) {
                final int q = di.q(i, 0);
                en en = (en)this.u.w(q);
                if (di.q(i, 63)) {
                    this.u.w(q);
                }
                else {
                    if (en == null) {
                        en = new en(q, di.q(i, 0));
                        this.u.q(en, q);
                    }
                    else {
                        en.a = di.q(i, 0);
                    }
                    en.q(di.q(i));
                }
            }
        }
        finally {}
    }
    
    public final void d(final dI di) {
    }
    
    private void J(final dI di) {
        try {
            for (int i = 0; i < di.w(); ++i) {
                final int q = di.q(i, 0);
                cE q2 = (cE)this.i.w(q);
                if (di.q(i, 63)) {
                    this.i.w(q);
                }
                else {
                    if (q2 == null) {
                        q2 = new cE(q, di.q(i, 0));
                        di.q(i, 0, this.i.q(q2));
                    }
                    else {
                        q2.a = di.q(i, 0);
                    }
                    q2.e = di.q(i, 1);
                    q2.q = di.q(i, 2);
                    q2.w = di.q(i, 3);
                    q2.q = new Color(di.q(i, 4));
                    q2.w = new Color(di.q(i, 5));
                    q2.e = new Color(di.q(i, 6));
                    q2.t = new Color(di.q(i, 7));
                    q2.r = new Color(di.q(i, 8));
                    q2.t = di.q(i, 9);
                    q2.y = di.q(i, 10);
                    q2.u = di.q(i, 11);
                    q2.i = di.q(i, 12);
                    q2.o = di.q(i, 13);
                    q2.r = di.q(i, 16);
                    q2.q = di.q(i, 1);
                    q2.u = di.q(i, 2);
                    q2.w = di.q(i, 3);
                    q2.e = di.q(i, 4);
                    q2.r = di.q(i, 5);
                    q2.t = di.q(i, 6);
                    q2.y = di.q(i, 7);
                    di.q(i, 8);
                    String q3;
                    if ((q3 = di.q(i, 9)) == null) {
                        q3 = "";
                    }
                    q2.i = q3;
                    di.q(i, 14);
                    di.q(i, 15);
                    q2.q(di.q(i));
                    if (q2.q(62)) {
                        this.q = q2;
                    }
                }
            }
        }
        finally {}
    }
    
    public final void f(final dI di) {
        final int q = di.q(0, 1);
        if (di.q(0, 0) == 1 && q == 68096) {
            new dd(super.q, be.w("Note"), be.w("Your password could not be changed because your old password was not entered correctly."), this).setVisible(true);
            this.w(this.q(58));
            return;
        }
        super.f(di);
    }
    
    public final void w() {
        this.q.q();
        cW.r = null;
        if (this.q != null) {
            this.q.dispose();
            this.q = null;
        }
        if (this.q != null) {
            this.q.dispose();
            this.q = null;
        }
        super.w();
        if (this.Q) {
            this.q();
        }
    }
    
    private dI q() {
        try {
            final dI di = new dI(67841, this.i.q);
            for (int i = 0; i < this.i.q; ++i) {
                final cE ce = (cE)this.i.q(i);
                di.q(i, 0, ce.s);
                di.q(i, 1, ce.e);
                di.q(i, 2, ce.q);
                di.q(i, 3, ce.w);
                di.q(i, 4, ce.q.getRGB());
                di.q(i, 5, ce.w.getRGB());
                di.q(i, 6, ce.e.getRGB());
                di.q(i, 7, ce.t.getRGB());
                di.q(i, 8, ce.r.getRGB());
                di.q(i, 9, ce.t);
                di.q(i, 10, ce.y);
                di.q(i, 11, ce.u);
                di.q(i, 12, ce.i);
                di.q(i, 13, ce.o);
                di.q(i, 14, ce.p);
                di.q(i, 15, ce.a);
                di.q(i, 16, ce.r);
                di.q(i, 0, ce.a);
                di.q(i, 1, ce.q);
                di.q(i, 2, ce.u);
                di.q(i, 3, ce.w);
                di.q(i, 4, ce.e);
                di.q(i, 5, ce.r);
                di.q(i, 6, ce.t);
                di.q(i, 7, ce.y);
                di.q(i, 8, ce.o);
                di.q(i, 9, ce.i);
                di.q(i, ce.w());
            }
            return di;
        }
        finally {}
    }
    
    public final void i() {
        if (!super.y) {
            if (this.q != null) {
                this.q.e = super.S;
                this.q.q = super.P;
                this.q.w = super.A;
                this.q.q = bC.w.o;
                this.q.w = bC.w.p;
                this.q.e = bC.w.a;
                this.q.t = bC.w.s;
                this.q.r = bC.w.d;
                this.q.t = super.D;
                this.q.y = super.F;
                this.q.u = super.G;
                this.q.i = super.H;
                this.q.o = super.J;
                this.q.p = bC.w.w;
                this.q.a = bC.w.q;
                this.q.o = bC.w.q;
                this.q.r = super.q;
                this.q.q = super.a;
                this.q.u = super.h;
                this.q.w = super.q;
                this.q.e = super.e;
                this.q.r = super.r;
                this.q.t = super.y;
                this.q.y = super.t;
                this.q.i = super.g;
                if (super.q) {
                    this.q.s(32);
                }
                if (super.z) {
                    this.q.s(60);
                }
                if (super.w > 0) {
                    this.q.s(super.w);
                }
                else {
                    this.q.d(1);
                    this.q.d(0);
                }
                if (super.a) {
                    this.q.s(25);
                }
                else {
                    this.q.d(25);
                }
            }
            if (!super.y && !this.q.exists()) {
                this.q.mkdirs();
            }
            final bH bh = new bH(new File(this.q, "settings.digi"), this.w, true);
            try {
                bh.q(this.q());
            }
            finally {
                bh.q();
            }
        }
    }
    
    public final void r(final dI di) {
        super.r(di);
        if (cT.f.equals("Admin")) {
            this.q = new aS(this);
            return;
        }
        this.q = null;
        super.q = new aP(this, bj.q(this)).q;
        this.q = this.q.q();
    }
    
    public final void t(final dI di) {
        if (this.q != null) {
            this.q.setVisible(true);
        }
        else {
            super.t(di);
        }
        if (this.q(58)) {
            this.w(true);
        }
    }
    
    protected final void g(final dI di) {
        super.g(di);
        if ((this.q(59) || this.q(60)) && (this.r == -999 || !((cj)super.f.w(this.r)).q(61))) {
            bj.q(false);
        }
    }
    
    public final void o() {
        this.q.setCursor(3);
        if (this.q == null) {
            this.q = new ek(this.q, this);
        }
        this.q.setVisible(true);
        this.q.setSize(500, 500);
        this.q.validate();
        this.q.toFront();
        this.q.setCursor(0);
    }
    
    public final void p() {
        boolean b;
        if ("Admin".equals(aN.f)) {
            b = false;
        }
        else {
            b = true;
        }
        new R((this.q == null) ? super.q.q() : this.q, this, 0, b ? 1 : 0).setVisible(true);
    }
    
    public final void a() {
        boolean b;
        if ("Admin".equals(aN.f)) {
            b = false;
        }
        else {
            b = true;
        }
        (this.q = new a((this.q != null) ? this.q : super.q.q(), this, b ? 1 : 0)).setVisible(true);
    }
    
    public final void s() {
        this.q("/Restart", 0);
    }
    
    public final void d() {
        if (super.q.q() != null) {
            super.q.q().setCursor(3);
        }
        if (this.q == null) {
            this.q = new v(super.q.q(), this);
        }
        this.q.setVisible(true);
        this.q.toFront();
        if (super.q.q() != null) {
            super.q.q().setCursor(0);
        }
    }
    
    public final void w(final boolean b) {
        new bO((this.q == null) ? super.q.q() : this.q, "Change Password", this, b).setVisible(true);
        this.q(58, b);
    }
    
    public final void f() {
        new bO((this.q == null) ? super.q.q() : this.q, "Change FTP Password", this, false).setVisible(true);
    }
    
    public final void q(final cE q) {
        if (this.q != null) {
            this.q.d(62);
        }
        (this.q = q).s(62);
        super.S = q.e;
        super.P = q.q;
        super.A = q.w;
        bC.w.o = q.q;
        bC.w.p = q.w;
        bC.w.a = q.e;
        bC.w.s = q.t;
        bC.w.d = q.r;
        bC.w.f = q.r.darker();
        bC.w.g = bC.w.a;
        super.D = q.t;
        super.F = q.y;
        super.G = q.u;
        super.H = q.i;
        super.J = q.o;
        super.q = q.r;
        super.a = q.q;
        super.h = q.u;
        super.q = q.w;
        super.e = q.e;
        super.r = q.r;
        super.y = q.t;
        super.t = q.y;
        super.g = q.i;
        super.q = q.q(32);
        super.z = q.q(60);
        super.a = q.q(25);
        super.g = q.i;
        if (q.q(1)) {
            super.w = 1;
        }
        else if (q.q(0)) {
            super.w = 2;
        }
        else {
            super.w = -999;
        }
        bC.w.q = new String(q.o);
        bC.w.w = q.p;
        bC.w.q = q.a;
        if (this.q != null) {
            this.q.q(q);
        }
    }
    
    public final void g() {
        super.g();
        System.exit(0);
    }
    
    public final void t() {
        if (super.y) {
            this.q.q();
        }
        else {
            this.q.q();
        }
        this.q = null;
    }
    
    public final void q(final A a) {
        if (this.q == null) {
            this.q = new dr(this);
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
            bj.q(true);
        }
        this.q.q(a);
        if (!this.q.isShowing()) {
            this.q.setVisible(true);
        }
    }
    
    public final void q(final byte[] array) {
        if (this.q != null) {
            this.q.q(array);
            this.q.q();
        }
    }
    
    public final void q(final A a, final aO ao) {
        new bL(this.q, this, a, ao);
        if (this.q != null) {
            this.q.q();
        }
    }
    
    public cT(final m q) {
        this.j = 0;
        this.k = 2;
        this.q = new M();
        this.w = new M();
        this.e = new M();
        this.r = new M();
        this.t = new M();
        this.y = new M();
        this.u = new M();
        this.i = new M();
        this.T = 5;
        this.Y = 5;
        this.y = 0L;
        this.r = 0L;
        super.y = true;
        cl.q(cl.q);
        this.q = q;
        try {
            this.r();
        }
        catch (Exception ex) {}
        super.k = true;
    }
    
    public cT() {
        this.j = 0;
        this.k = 2;
        this.q = new M();
        this.w = new M();
        this.e = new M();
        this.r = new M();
        this.t = new M();
        this.y = new M();
        this.u = new M();
        this.i = new M();
        this.T = 5;
        this.Y = 5;
        this.y = 0L;
        this.r = 0L;
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
                bH bh;
                for (int q = (bh = new bH(file2, this.w, 0 != 0)).q(), i = 0; i < q; ++i) {
                    this.q(bh.q(i));
                }
            }
            if (!this.i.q(0)) {
                final cE ce;
                (ce = new cE(0, "Admin")).e = 0;
                ce.q = "Admin";
                this.i.q(ce);
            }
            if (!this.i.q(1)) {
                final cE ce2;
                (ce2 = new cE(1, "ChatMaster")).e = -999;
                ce2.q = "ChatMaster";
                this.i.q(ce2);
            }
            if (!this.i.q(1000)) {
                final cE ce3;
                (ce3 = new cE(1000, "Other")).e = -999;
                this.i.q(ce3);
            }
        }
        catch (IOException ex) {}
        this.q = new dk(this);
        if (this.q == null) {
            this.q = (cE)this.i.q(0);
        }
        this.q(this.q);
        super.k = true;
        this.q.setVisible(true);
    }
    
    public final int q(final String s) {
        for (int i = 0; i < this.y.q; ++i) {
            final de de = (de)this.y.q(i);
            if (s.equals(de.a)) {
                return de.s;
            }
        }
        return 0;
    }
    
    public final String q(final int n) {
        final de de;
        if ((de = (de)this.y.w(n)) != null) {
            return de.a;
        }
        return a.de.q;
    }
    
    public final M w() {
        return this.y;
    }
}
