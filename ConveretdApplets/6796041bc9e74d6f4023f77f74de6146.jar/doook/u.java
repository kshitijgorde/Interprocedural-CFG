// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Component;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Image;
import java.awt.Frame;

public abstract class u extends t
{
    private ah a;
    public String E;
    
    protected void a() {
        super.b.b();
        super.c.b();
        super.d.b();
        super.e.b();
        super.f.b();
        super.g.b();
        super.h.b();
        super.j.b();
        super.m.b();
        super.n.b();
        super.o.b();
        super.k.b();
        super.l.b();
        if (super.a != null) {
            for (int i = 0; i < super.a.b(); ++i) {
                ((aj)super.a.a(i)).dispose();
            }
        }
        this.f();
        if (super.a != null) {
            final Frame a = super.a.a();
            super.a.a();
            super.a = null;
            if (this.a != null) {
                this.a.setVisible(false);
            }
            this.a = null;
            if (a != null) {
                a.dispose();
            }
        }
        try {
            Thread.sleep(500L);
        }
        catch (InterruptedException ex) {}
        if (super.a != null) {
            try {
                super.a.close();
                super.a.close();
                super.a.close();
            }
            catch (Exception ex2) {}
        }
        super.a = null;
        super.a = null;
        super.a = null;
        super.x = true;
    }
    
    public void a(final cD cd) {
        switch (cd.b()) {
            case 67585: {
                this.e(cd);
                break;
            }
            case 65794: {
                this.u(cd);
                break;
            }
            case 66049: {
                this.b(cd);
                break;
            }
            case 66305: {
                this.y(cd);
                break;
            }
            case 66308: {
                this.v(cd);
                break;
            }
            case 66306: {
                this.x(cd);
                break;
            }
            case 66307: {
                this.g(cd);
                break;
            }
            case 66561: {
                this.f(cd);
                break;
            }
            case 66816:
            case 50400771: {
                this.c(cd);
                break;
            }
            case 66817: {
                this.h(cd);
                break;
            }
            case 67073: {
                this.z(cd);
                break;
            }
            case 67074: {
                this.w(cd);
                break;
            }
            case 67329: {
                this.A(cd);
                break;
            }
            case 67330: {
                this.l(cd);
                break;
            }
            case 67331: {
                this.C(cd);
                break;
            }
            case 67333: {
                this.i(cd);
                break;
            }
            case 263936: {
                this.j(cd);
            }
            case 67584: {
                this.s(cd);
                break;
            }
            case 67586: {
                this.t(cd);
                break;
            }
            case 67843: {
                this.D(cd);
                break;
            }
            case 68608: {
                this.d(cd);
                break;
            }
            case 67338: {
                this.k(cd);
                break;
            }
            case 67341: {
                this.m(cd);
                break;
            }
            case 33621775: {
                this.B(cd);
                break;
            }
            case 264192: {
                this.p(cd);
                break;
            }
            case 327680: {
                this.q(cd);
                break;
            }
            case 327936: {
                this.r(cd);
                break;
            }
        }
    }
    
    public boolean h() {
        return super.v;
    }
    
    public Image a(final String s, final boolean b, final int n) {
        return this.a(s, b, n, true);
    }
    
    public Image a(final String s, final boolean b, final int n, final boolean b2) {
        try {
            URL url;
            if (b || doook.t.b(s)) {
                url = new URL(super.b, "Resources/" + super.n + "/" + s);
            }
            else {
                url = new URL(super.b, "Resources/" + s);
            }
            final Image a = this.a(url);
            if (a != null) {
                super.a.addImage(a, b2 ? n : super.o);
                super.a.statusID(b2 ? n : super.o, true);
                if (!b2) {
                    ++super.o;
                }
            }
            return a;
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    public abstract Image a(final URL p0);
    
    public void l() {
    }
    
    public AudioClip[] a(final URL url) {
        return null;
    }
    
    public abstract void a(final String p0, final String p1, final a p2, final String p3, final int p4);
    
    public void a(final URL url, final String s) {
    }
    
    public void a(final int n) {
        if (super.a != null && n > 0 && n <= super.a.length && super.a[n - 1] != null) {
            try {
                super.a[n - 1].stop();
                super.a[n - 1].play();
            }
            catch (Throwable t) {}
        }
    }
    
    public void a(final String s, final cG cg, final int n, final int n2, final boolean b, final long n3, final int n4) {
        this.a(s, cg, n, n2, b, false, n3, n4, null);
    }
    
    public void a(final String s, final cG cg, final int n, final int n2, final boolean b, final boolean b2, final long n3, final int n4, final byte[] a) {
        if (super.a != null) {
            int h = cg.h();
            if (h == this.h()) {
                h = n;
            }
            cg.f();
            final Z z = new Z(this.d(s), cg, n != -1 && n != -3 && n != -2 && !b, (as)super.b.b(cg.g));
            z.a(n == -3 || n == -2);
            z.a(n3, n4);
            if (b2) {
                z.ai = a[0];
            }
            else {
                z.a = a;
            }
            if (z.v) {
                final X x = (X)super.a.b(h);
                if (x != null) {
                    x.a((Object)z);
                }
                else if (super.B) {
                    this.a(z, cg);
                }
                else {
                    super.a.a(z);
                }
            }
            else {
                z.t = cg.b;
                if (n == -3 || n == -2) {
                    this.a(z);
                }
                else {
                    super.a.a(z);
                }
            }
        }
    }
    
    protected void q(final cD cd) {
        for (int i = 0; i < cd.g(); ++i) {
            final String a = cd.a(i, 0);
            if (a != null && a.length() > 0 && !doook.y.a.containsKey(a)) {
                final Image a2 = this.a("userImages/" + a, true, 17);
                if (a2 != null) {
                    doook.y.a.put(a, a2);
                }
            }
        }
    }
    
    protected void r(final cD cd) {
        super.l.a(true);
        try {
            for (int i = 0; i < cd.g(); ++i) {
                final int b = cd.b(i, 0);
                cd cd2 = (cd)super.l.b(b);
                if (cd.b(i, 63)) {
                    if (cd2 != null) {
                        super.l.c(b);
                    }
                }
                else {
                    if (cd2 == null) {
                        cd2 = new cd(b, cd.a(i, 0));
                        super.l.a(cd2, b);
                    }
                    else {
                        cd2.d(cd.a(i, 0));
                    }
                    cd2.a(cd.a(i));
                    cd2.description = cd.a(i, 1);
                    cd2.b = cd.a(i, 2);
                    cd2.an = cd.b(i, 1);
                    try {
                        if (cd2.b != null && cd2.b.length() > 0 && !doook.y.b.containsKey(cd2.b)) {
                            final Image a = this.a("stars/" + cd2.b, true, 15);
                            if (a != null) {
                                doook.y.b.put(cd2.b, a);
                            }
                        }
                    }
                    catch (Exception ex) {
                        System.out.println("error loading star image: " + ex.getMessage());
                    }
                }
            }
        }
        finally {
            super.l.a();
        }
    }
    
    protected void s(final cD cd) {
        this.l(cd.j);
        this.a(cd.a(0));
        super.n = cd.a(0, 0);
        doook.z.G = cd.a(0, 1);
        doook.t.b[0] = ao.e("Bass");
        doook.t.b[1] = ao.e("Bell");
        doook.t.b[2] = ao.e("Castanet");
        doook.t.b[3] = ao.e("Chime");
        doook.t.b[4] = ao.e("Conga");
        doook.t.b[5] = ao.e("Cow Bell");
        doook.t.b[6] = ao.e("Double Bell");
        doook.t.b[7] = ao.e("Drum Roll");
        doook.t.b[8] = ao.e("Harp");
        if (!"Admin".equals(super.n)) {
            if (super.a.n()) {
                super.a.v = this.a(super.a.e() + "background.gif", true);
            }
            if (super.a.o()) {
                super.a.w = this.a(super.a.e() + "chatbackground.gif", true);
            }
            if (doook.z.W) {
                (super.a = new S(this, null)).setVisible(false);
                doook.z.a.setVisible(false);
                doook.z.a.add(super.a.a());
                ((S)super.a).validate();
            }
            else {
                super.a = (i)new an(this).b();
            }
            this.l();
        }
    }
    
    protected void t(final cD cd) {
        super.e = true;
        super.i = true;
        super.h = -999;
        this.l();
        if (super.b.b(super.s) == null) {
            this.b(super.q);
        }
        else {
            this.b(super.s);
        }
        final T t = (T)super.d.b(super.r);
        final T t2 = (T)super.d.b(super.p);
        if (t == null || (t.a == null && t2.a != null)) {
            this.c(t2);
        }
        else {
            this.c(t);
        }
        if (super.a != null) {
            ((au)super.a).a().c();
        }
    }
    
    protected void m() {
        if (!doook.b.a) {
            return;
        }
        if (!super.e && !super.i) {
            if (super.a != null) {
                ((au)super.a).a().c();
            }
            int h;
            if (super.d.b(super.r) == null) {
                h = super.p;
            }
            else {
                h = super.r;
            }
            super.i = true;
            final T t = (T)super.d.b(h);
            if (t != null && t.m && t.a != null) {
                super.h = -999;
                new ae(super.a.a(), this, t).setVisible(true);
            }
            super.h = h;
        }
    }
    
    protected void a(final ab ab, final int n, final long n2, final int n3) {
        final int h = ab.h;
        final T t = (T)super.d.b(n);
        final T t2 = (T)super.d.b(ab.h);
        this.d(26);
        this.m();
        if (ab.h() == this.h()) {
            if (t2 != null) {
                t2.a = false;
            }
            t.a = true;
            super.h = n;
            if (super.a != null) {
                if (!super.y) {
                    this.c(false);
                }
                super.a.a(t);
                if (!super.a.isVisible()) {
                    super.a.setVisible(true);
                }
                super.a.a().validate();
            }
        }
        if (t != null) {
            t.i = this.e(t.h());
            if (t2 != null && t != null) {
                if (t2.h() != t.h() && (!ab.d(23) || this.d(24))) {
                    final T t3 = t;
                    ++t3.i;
                }
            }
            else if (t2 == null && t != null && (!ab.d(23) || this.d(24))) {
                final T t4 = t;
                ++t4.i;
            }
            if (super.a != null) {
                super.a.b(t);
            }
        }
        if (t2 != null) {
            t2.i = this.e(t2.h());
            if (t2 != null && t != null && t2.h() != t.h() && (!ab.d(23) || this.d(24))) {
                final T t5 = t2;
                --t5.i;
            }
            if (super.a != null) {
                super.a.b(t2);
            }
        }
        if (n == super.h && ab.h != n) {
            if (t != null && !t.d(57) && (!ab.d(23) || this.d(24))) {
                final Z z = new Z(am.a(ao.e("(This user has entered %1)"), new String[] { this.d(t.f()) }), ab, false, (as)super.b.b(ab.g));
                z.a(n2, n3);
                this.a(super.B);
                if (super.a != null) {
                    super.a.a(z);
                }
            }
        }
        else if (ab.h == super.h && n != super.h && t2 != null && t != null && !t2.d(57) && (!ab.d(23) || this.d(24))) {
            final Z z2 = new Z(am.a(ao.e("(This user has moved to %1)"), new String[] { this.d(t.f()) }), ab, false, (as)super.b.b(ab.g));
            z2.a(n2, n3);
            this.a(super.F);
            if (super.a != null) {
                super.a.a(z2);
            }
        }
        ab.h = n;
        if (super.a != null) {
            if (super.av) {
                if ((ab.f > 0 || ab.e > 0) && (super.y || n == super.h) && this.a(ab.f(), doook.R.i.getText())) {
                    super.a.a(ab, true);
                }
                else {
                    super.a.a(ab);
                }
            }
            else if ((super.y || n == super.h) && this.a(ab.f(), doook.R.i.getText())) {
                super.a.a(ab, true);
            }
            else if (!super.y && h == super.h) {
                super.a.a(ab);
            }
        }
    }
    
    protected void u(final cD cd) {
        this.d(26);
        this.m();
        for (int i = 0; i < cd.g(); ++i) {
            final ab ab = (ab)super.c.b(cd.b(i, 0));
            if (ab != null) {
                final T t = (T)super.d.b(ab.h);
                final ac ac = new ac(ab.h(), ab.f());
                ac.a = false;
                if (t != null) {
                    if (!ab.d(23) || this.d(24)) {
                        final T t2 = t;
                        --t2.i;
                    }
                    if (super.a != null) {
                        super.a.b(t);
                    }
                    if (ab.h == super.h && !t.d(57) && (!ab.d(23) || this.d(24))) {
                        final String a = cd.a(i, 0);
                        String s;
                        if (a == null) {
                            s = am.a(ao.e("(This user has left %1)"), new String[] { doook.z.G });
                        }
                        else {
                            s = this.d(a) + " " + am.a(ao.e("(This user has left %1)"), new String[] { doook.z.G });
                        }
                        final Z z = new Z(s, ab, false, (as)super.b.b(ab.g));
                        z.a(cd.c(), cd.l());
                        this.a(super.F);
                        if (super.a != null) {
                            super.a.a(z);
                        }
                    }
                }
                if (super.a != null) {
                    super.a.a(ab);
                    ((au)super.a).a(ac, false, false);
                }
                final X x = (X)super.a.b(ab.h());
                if (x != null) {
                    x.dispose();
                }
                super.c.a(true);
                try {
                    super.c.b((Object)ab);
                }
                finally {
                    super.c.a();
                }
            }
        }
    }
    
    protected void v(final cD cd) {
        for (int i = 0; i < cd.g(); ++i) {
            if (cd.b() != null) {
                this.a(cd.b());
            }
            cG cg = (cG)super.c.b(cd.b(i, 0));
            if (cg == null && this.g() && cd.b(0, 3)) {
                cg = new cG(-999, cd.a(0, 1));
                cg.g = cd.b(0, 1);
            }
            if (cg == null) {
                cg = new cG(-999, "Guest");
                cg.g = super.q;
            }
            if (cg != null) {
                int n = cg.h();
                if (n == this.h()) {
                    n = cd.j;
                }
                if (!cg.h) {
                    switch (cd.j) {
                        case -3:
                        case -2:
                        case -1: {
                            if (cg.b) {
                                this.a(super.D);
                                break;
                            }
                            this.a(super.C);
                            break;
                        }
                        default: {
                            if (super.a.b(n)) {
                                this.a(super.C);
                                break;
                            }
                            this.a(super.E);
                            break;
                        }
                    }
                    this.a(cd.a(i, 0), cg, cd.j, cd.o, false, cd.c(), cd.l(), cd.b(i, 2), cd.b(i, 3), cd.b(i, 37), cd.b(i, 38), cd.b(i, 39));
                }
            }
        }
    }
    
    public abstract void a(final String p0, final cG p1, final int p2, final int p3, final boolean p4, final long p5, final int p6, final int p7, final int p8, final boolean p9, final boolean p10, final boolean p11);
    
    protected void w(final cD cd) {
        final cG cg = (cG)super.c.b(cd.b(0, 0));
        if (cg != null) {
            final cD cd2 = new cD(67073, 1);
            cd2.j = cg.h();
            cd2.a(0, 0, this.h());
            if (!super.C || cg.d(33)) {
                cd2.a(0, 1, super.w);
                if (super.r != null && super.r.length() > 0) {
                    cd2.a(0, 0, super.r);
                }
                if (super.v != null && super.v.length() > 0) {
                    cd2.a(0, 1, super.v);
                }
                if (super.s != null && super.s.length() > 0) {
                    cd2.a(0, 2, super.s);
                }
                if (super.t != null && super.t.length() > 0) {
                    cd2.a(0, 3, super.t);
                }
                if (super.z != -999) {
                    cd2.b(0, super.z);
                }
            }
            this.o(cd2);
        }
    }
    
    protected void x(final cD cd) {
        this.d(26);
        if (super.a != null) {
            for (int i = 0; i < cd.g(); ++i) {
                final Z z = new Z(this.d(cd.a(i, 1)), this.d(cd.a(i, 0)), false, (as)super.b.b(cd.b(i, 0)), -999, false, false);
                z.a(cd.c(), cd.l());
                super.a.a(z);
                if (cd.j == this.h()) {
                    this.a(super.E);
                }
                else {
                    this.a(super.C);
                }
            }
        }
    }
    
    protected void y(final cD cd) {
        this.m();
        for (int i = 0; i < cd.g(); ++i) {
            cG cg = (cG)super.c.b(cd.b(i, 0));
            if (cg == null && this.g() && cd.b(0, 3)) {
                cg = new cG(-999, cd.a(0, 1));
                cg.g = cd.b(0, 1);
            }
            if (cg != null) {
                int n = cg.h();
                if (n == this.h()) {
                    n = cd.j;
                }
                if (cd.j != this.h() && cd.b(0, 20)) {
                    return;
                }
                if (!cg.h) {
                    if (!cd.s) {
                        switch (cd.j) {
                            case -3:
                            case -2:
                            case -1: {
                                if (cg.b) {
                                    this.a(super.D);
                                    break;
                                }
                                this.a(super.C);
                                break;
                            }
                            default: {
                                if (super.a.b(n)) {
                                    this.a(super.C);
                                    break;
                                }
                                this.a(super.E);
                                break;
                            }
                        }
                    }
                    if (cd.b() != null) {
                        this.a(cd.a(i, 0), cg, cd.j, cd.o, false, cd.b(0, 20), cd.c(), cd.l(), cd.b());
                    }
                    else {
                        this.a(cd.a(i, 0), cg, cd.j, cd.o, false, cd.c(), cd.l());
                    }
                }
            }
        }
    }
    
    protected void z(final cD cd) {
        for (int i = 0; i < cd.g(); ++i) {
            final cG cg = (cG)super.c.b(cd.b(i, 0));
            if (cg != null) {
                if (super.F) {
                    final String a = cd.a(i, 2);
                    if (a != null) {
                        try {
                            this.a(new URL(a), "_blank");
                        }
                        catch (MalformedURLException ex) {}
                    }
                }
                else {
                    new V(super.a.a(), this, cg, cd, i);
                }
            }
        }
    }
    
    protected void A(final cD cd) {
        super.f = cd.a(-1);
        super.e.a(true);
        try {
            for (int i = 0; i < cd.g(); ++i) {
                final int b = cd.b(i, 0);
                at at = (at)super.e.b(b);
                if (cd.b(i, 63)) {
                    if (at != null) {
                        super.e.c(b);
                    }
                }
                else {
                    if (at == null) {
                        at = new at(b, cd.a(i, 0));
                        super.e.a(at);
                    }
                    else {
                        at.d(cd.a(i, 0));
                    }
                    at.a = cd.b(i, 1);
                    at.b = cd.b(i, 2);
                    at.R = cd.a(i, 1);
                    at.a = cd.a(i, 2);
                    at.d = cd.a(i, 3);
                    at.a(cd.a(i));
                    at.g = this.a("banners/" + at.a, true, 30);
                    if (super.a != null) {
                        ((au)super.a).a(at);
                    }
                }
            }
        }
        finally {
            super.e.a();
        }
    }
    
    protected void B(final cD cd) {
        for (int g = cd.g(), i = 0; i < g; ++i) {
            final bh bh = new bh(cd.b(i, 0), cd.a(i, 0));
            bh.a(cd.a(i));
            bh.d = cd.a(i, 1);
            if (!bh.d(63)) {
                bh.e = this.a("emoticons/" + bh.d, true, 50);
            }
            doook.bh.a(bh);
        }
    }
    
    protected void C(final cD cd) {
        doook.y.a[0] = this.a("status/status_1.gif", false, 16);
        doook.y.a[1] = this.a("status/status_2.gif", false, 16);
        doook.y.a[2] = this.a("status/status_3.gif", false, 16);
        doook.y.a[3] = this.a("status/status_4.gif", false, 16);
        super.b.a(true);
        try {
            for (int i = 0; i < cd.g(); ++i) {
                final int b = cd.b(i, 0);
                as as = (as)super.b.b(b);
                if (cd.b(i, 63)) {
                    if (as != null) {
                        super.b.c(b);
                    }
                }
                else {
                    if (as == null) {
                        as = new as(b, cd.a(i, 0));
                        super.b.a(as);
                    }
                    else {
                        as.d(cd.a(i, 0));
                    }
                    as.q = this.a("userIcons/" + as.f(), true, 40);
                    as.Q = cd.a(i, 1);
                    as.a(cd.a(i));
                    if (cd.b(i, 62)) {
                        super.q = b;
                    }
                }
            }
        }
        finally {
            super.b.a();
        }
    }
    
    protected void D(final cD cd) {
        super.x = cd.a(0, 0);
        super.y = cd.a(0, 1);
        super.c = cd.a(0);
        if (super.G != cd.b(0, 0)) {
            bM.a(this, cd.b(0, 0));
        }
        super.G = cd.b(0, 0);
        super.H = cd.b(0, 1);
        super.I = cd.b(0, 2);
        super.J = cd.b(0, 3);
        super.K = cd.b(0, 4);
        super.L = cd.b(0, 5);
        if (super.a != null) {
            ((au)super.a).a(super.x);
            ((au)super.a).a().b((super.H > 0) ? super.H : 7);
        }
        cG.ax = cd.b(0, 32);
        doook.t.Q = cd.b(0, 33);
        doook.t.R = cd.b(0, 34);
        doook.t.M = cd.b(0, 35);
        doook.t.N = cd.b(0, 36);
        doook.t.O = cd.b(0, 37);
        doook.t.P = cd.b(0, 38);
        doook.y.c = super.a("Doooknet.gif", false);
        doook.y.b.clear();
        final String[] array = { "system", "admin", "master" };
        for (int i = 0; i < array.length; ++i) {
            try {
                final Image a = this.a("star_" + array[i] + ".gif", false);
                if (a != null) {
                    doook.y.b.put("../../star_" + array[i] + ".gif", a);
                }
            }
            catch (Exception ex) {
                System.out.println("error loading system star image for: " + array[i]);
                ex.printStackTrace();
            }
        }
    }
    
    public void a(final Z z, final cG cg) {
        final int h = cg.h();
        aj aj = (aj)super.a.b(h);
        if (aj == null) {
            aj = new X(super.a.a(), this, h);
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int n = screenSize.width / 2 - 20;
            int n2 = screenSize.height / 2 - 20;
            int n3 = 0;
            int n4 = 0;
            switch (super.a.b() % 4) {
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
            if (n2 > 300) {
                n2 = 300;
            }
            aj.reshape(2 + n4, 10 + n3, n, n2);
            super.a.a(aj, h);
        }
        if (z != null) {
            aj.a(z);
        }
        aj.setVisible(true);
    }
    
    public void b(final int n) {
        final cD cd = new cD(67334, 1);
        cd.a(0, 0, this.h());
        cd.a(0, 1, n);
        cd.a(0, 0, this.f());
        cd.j = -1;
        cd.o = -1;
        this.o(cd);
    }
    
    public void b(final boolean v) {
        super.v = v;
    }
    
    public void c(final int n) {
        if (super.a.a() != null) {
            super.a.a().setCursor(3);
        }
        if (this.a == null) {
            this.a = new ah(super.a.a(), this);
        }
        if (n >= 0) {
            this.a.b(n);
        }
        if (this.a.isVisible()) {
            this.a.toFront();
        }
        else {
            this.a.setVisible(true);
        }
        if (super.a.a() != null) {
            super.a.a().setCursor(0);
        }
    }
}
