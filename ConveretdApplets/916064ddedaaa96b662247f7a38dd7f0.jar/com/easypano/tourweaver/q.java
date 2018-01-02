// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver;

import com.easypano.tourweaver.f.k;
import com.easypano.tourweaver.f.s;
import com.easypano.tourweaver.f.d;
import com.easypano.tourweaver.a.c;
import com.easypano.tourweaver.d.e;
import com.easypano.tourweaver.f.m;
import com.easypano.tourweaver.f.x;
import com.easypano.tourweaver.f.h;
import com.easypano.tourweaver.f.ab;
import com.easypano.tourweaver.b.a;
import com.easypano.tourweaver.f.y;
import java.util.Vector;
import com.easypano.tourweaver.f.z;
import com.easypano.tourweaver.f.b;

public class q implements n, i, b
{
    o a;
    z b;
    Vector c;
    j d;
    boolean e;
    String f;
    boolean g;
    double h;
    double i;
    double j;
    double k;
    long l;
    long m;
    private boolean n;
    com.easypano.tourweaver.f.n o;
    boolean p;
    String q;
    p r;
    int s;
    boolean t;
    String u;
    y v;
    private static String[] z;
    
    public q() {
        final boolean v = com.easypano.tourweaver.g.v;
        this.a = null;
        this.c = new Vector();
        this.d = new j(30);
        this.e = true;
        this.f = "";
        this.g = false;
        this.h = 0.0;
        this.i = 0.0;
        this.j = 0.0;
        this.k = 57.29577951308232;
        this.l = -1L;
        this.m = 0L;
        this.n = false;
        this.o = null;
        this.p = true;
        this.q = null;
        this.s = 0;
        this.t = false;
        this.u = "";
        this.v = null;
        this.a = new o();
        this.b = new z();
        this.a.a(this);
        this.b.a(this);
        if (com.easypano.tourweaver.b.a.o != 0) {
            com.easypano.tourweaver.g.v = !v;
        }
    }
    
    public void setDefaultMovie(final String f) {
        this.f = f;
    }
    
    public void movieStoped(final String s) {
        this.fireUpdateMovieStop(s);
    }
    
    public void destroy() {
        this.a.a();
    }
    
    public void setAutoRunMovie(final boolean e) {
        this.e = e;
    }
    
    public void addAnimation(final com.easypano.tourweaver.f.a a, final int n) {
        if (n == 1) {
            this.a.a((com.easypano.tourweaver.f.n)a);
        }
    }
    
    public void setQuality(final int n) {
        this.a.e().a(n);
    }
    
    public synchronized void addPlayerListener(final PlayerListener playerListener) {
        Vector vector2;
        final Vector vector = vector2 = this.c;
        PlayerListener playerListener2 = playerListener;
        if (!com.easypano.tourweaver.g.v) {
            if (vector.contains(playerListener)) {
                return;
            }
            vector2 = this.c;
            playerListener2 = playerListener;
        }
        vector2.addElement(playerListener2);
    }
    
    public void addRenderTarget(final ab ab, final String s) {
        this.a.a(ab, s);
    }
    
    public void addRenderable(final h h, final int n) {
        final boolean v = com.easypano.tourweaver.g.v;
        int n2 = n;
        int n4;
        final int n3 = n4 = 1;
        if (!v) {
            if (n == n3) {
                this.a.a((y)h);
                if (!v) {
                    return;
                }
            }
            n2 = n;
            n4 = 2;
        }
        if (n2 == n4) {
            this.a.a((x)h);
        }
    }
    
    public void stopMovie() {
        final com.easypano.tourweaver.f.n o = this.o;
        if (!com.easypano.tourweaver.g.v) {
            if (o == null) {
                return;
            }
            final com.easypano.tourweaver.f.n o2 = this.o;
        }
        o.e();
    }
    
    public void autoPanScene(final int n, final int n2, final int n3) {
        this.a();
        final com.easypano.tourweaver.f.o e = this.a.e();
        final y y = (y)e.m();
        if (y == null) {
            return;
        }
        final double n4 = n * y.p() / 20.0;
        double n5 = n2 * y.p() / 20.0;
        int j;
        final boolean b = (j = (y.j() ? 1 : 0)) != 0;
        if (!com.easypano.tourweaver.g.v) {
            if (b) {
                n5 = -n5;
            }
            j = n3;
        }
        final double n6 = j * y.p() / 20.0;
        final double h = n4 / 1000.0;
        final double i = n5 / 1000.0;
        final double k = n6 / 1000.0;
        this.h = h;
        this.i = i;
        this.j = k;
        e.b();
        e.b(h * this.m, i * this.m, k * this.m);
    }
    
    public void autoPanScene(final double n, final double n2, final double n3) {
        this.a();
        final com.easypano.tourweaver.f.o e = this.a.e();
        final y y = (y)e.m();
        e.b();
        e.b(n, n2, n3);
    }
    
    public void goToViewer(final double n, final double n2, final double n3) {
        this.goToViewer(n, n2, n3, 3);
    }
    
    public void goToViewer(final double n, final double n2, final double n3, final int n4) {
        final com.easypano.tourweaver.f.o e = this.a.e();
        final com.easypano.tourweaver.f.n n5 = new com.easypano.tourweaver.f.n();
        n5.a(n4);
        final m m = new m();
        m.a(0);
        m.e(com.easypano.tourweaver.q.z[1]);
        m.d(e.m().getName());
        m.a(e.q() * this.k - 180.0, e.r() * this.k, e.s() * this.k);
        final m i = new m();
        i.d(e.m().getName());
        i.a(n, n2, n3);
        i.a(n4);
        i.e(com.easypano.tourweaver.q.z[1]);
        n5.a((e)m);
        n5.a((e)i);
        this.a.b(n5);
        this.stop();
        final ab k = this.a.k(com.easypano.tourweaver.q.z[0]);
        if (!com.easypano.tourweaver.g.v && k == null) {
            return;
        }
        n5.a(k.getCamera());
        this.b.b(n5);
        n5.b();
    }
    
    public void backward() {
        final boolean v = com.easypano.tourweaver.g.v;
        final l d = this.d.d();
        if (!v) {
            if (d == null) {
                this.previousScene();
                if (!v) {
                    return;
                }
            }
            this.g = true;
        }
        this.switchToScene(d.a().getName(), d.b(), d.c(), d.d());
    }
    
    public void forward() {
        final boolean v = com.easypano.tourweaver.g.v;
        final l c = this.d.c();
        if (!v) {
            if (c == null) {
                this.nextScene();
                if (!v) {
                    return;
                }
            }
            this.g = true;
        }
        this.switchToScene(c.a().getName(), c.b(), c.c(), c.d());
    }
    
    public void nextScene() {
        this.switchToScene(this.a.b(this.b.a().getName()));
    }
    
    public void play() {
        final boolean v = com.easypano.tourweaver.g.v;
        this.b.c();
        this.b.d();
        final long currentTimeMillis = System.currentTimeMillis();
        final long l = this.l;
        final long n = -1L;
        long m = 0L;
        Label_0050: {
            if (!v) {
                if (l == n) {
                    m = 100L;
                    break Label_0050;
                }
                final long i = this.l;
            }
            m = l - n;
        }
        this.m = m;
        this.l = currentTimeMillis;
        final boolean n2 = this.n;
        final com.easypano.tourweaver.f.o e;
        Label_0093: {
            if (!v) {
                if (n2) {
                    return;
                }
                e = this.a.e();
                if (v) {
                    break Label_0093;
                }
                e.d();
            }
            if (n2) {
                return;
            }
            this.a.e();
        }
        e.b(this.h * this.m, this.i * this.m, this.j * this.m);
    }
    
    public h getRenderable(final String s, final int n) {
        if (n == 1) {
            return this.a.f(s);
        }
        return null;
    }
    
    public com.easypano.tourweaver.f.n getMovie(final String s) {
        return this.a.c(s);
    }
    
    public void playMovie() {
        q q = this;
        if (!com.easypano.tourweaver.g.v) {
            if (this.o == null) {
                return;
            }
            q = this;
        }
        q.playMovie(this.o.getName());
    }
    
    public void playMovie(final boolean b) {
        q q = this;
        if (!com.easypano.tourweaver.g.v) {
            if (this.o == null) {
                return;
            }
            q = this;
        }
        q.playMovie(this.o.getName(), b);
    }
    
    private void a() {
        final com.easypano.tourweaver.f.n o = this.o;
        if (!com.easypano.tourweaver.g.v) {
            if (o == null) {
                return;
            }
            final com.easypano.tourweaver.f.n o2 = this.o;
        }
        o.c();
    }
    
    public void playMovie(final String s, final boolean b) {
        final boolean v = com.easypano.tourweaver.g.v;
        this.n = true;
        final ab k = this.a.k(com.easypano.tourweaver.q.z[0]);
        final com.easypano.tourweaver.f.n c = this.a.c(s);
        if (!v) {
            if (c != null && k != null) {
                com.easypano.tourweaver.f.n n2;
                final com.easypano.tourweaver.f.n n = n2 = c;
                Label_0090: {
                    if (!v) {
                        if (n != this.o) {
                            break Label_0090;
                        }
                        final com.easypano.tourweaver.f.n n3;
                        n2 = (n3 = c);
                    }
                    if (!v) {
                        if (n.t()) {
                            break Label_0090;
                        }
                        n2 = c;
                    }
                    n2.c();
                    return;
                }
                (this.o = c).a(k.getCamera());
                this.o.a(b);
                this.b.b(this.o);
                this.fireUpdateMovie(this.o.getName());
                c.b();
                return;
            }
            com.easypano.tourweaver.a.c.c(this, com.easypano.tourweaver.q.z[2]);
        }
    }
    
    public void playMovie(final String s) {
        this.playMovie(s, this.a.c(s).q());
    }
    
    public void previousScene() {
        this.switchToScene(this.a.a(this.b.a().getName()));
    }
    
    public void removePlayerListener(final PlayerListener playerListener) {
    }
    
    public void reset() {
        final d camera = this.a.k(com.easypano.tourweaver.q.z[0]).getCamera();
        final h m = camera.m();
        h h;
        final y y = (y)(h = m);
        if (!com.easypano.tourweaver.g.v) {
            if (!(y instanceof y)) {
                return;
            }
            h = m;
        }
        final y y2 = (y)h;
        camera.a(y2.v(), y2.w(), y2.x());
        this.stop();
    }
    
    public void stop() {
        this.b.b();
    }
    
    public void switchToMap(String name, final String s) {
        final boolean v = com.easypano.tourweaver.g.v;
        x x = this.a.d(name);
        final x x3;
        final x x2 = x3 = x;
        if (!v && x2 == null) {
            x = this.a.e(s);
            goto Label_0032;
        }
        final x d;
        if (!v) {
            Label_0075: {
                if (x2 == null) {
                    q q = this;
                    if (!v) {
                        if (!this.p) {
                            break Label_0075;
                        }
                        q = this;
                    }
                    x = (d = q.a.d());
                    if (!v) {
                        if (d != null) {
                            name = x.getName();
                        }
                    }
                }
            }
        }
        if (d != null) {
            this.switchToMap(name, s, x.a(), x.b());
        }
    }
    
    public void switchToMap(final String s, final String s2, final String s3, final long n) {
        final boolean v = com.easypano.tourweaver.g.v;
        x x = this.a.d(s);
        final y f = this.a.f(s2);
        final ab k = this.a.k(com.easypano.tourweaver.q.z[3]);
        final ab i = this.a.k(com.easypano.tourweaver.q.z[0]);
        final x x2 = x;
        if (!v && x2 == null) {
            x = this.a.e(s2);
            goto Label_0073;
        }
        if (x2 != null) {
            final ab ab = k;
            if (v || ab != null) {
                final h m = ab.getCamera().m();
                if (m instanceof s) {}
                final d camera = i.getCamera();
                final d camera2 = k.getCamera();
                camera.a(camera2);
                camera.c();
                x.b(s2);
                com.easypano.tourweaver.b.d d = null;
                final com.easypano.tourweaver.f.e j = x.j();
                Label_0171: {
                    if (!v) {
                        if (j == null) {
                            break Label_0171;
                        }
                        x.j();
                    }
                    d = (com.easypano.tourweaver.b.d)j;
                }
                h h = null;
                Label_0228: {
                    if (d != null) {
                        final y y = (y)(h = f);
                        if (v) {
                            break Label_0228;
                        }
                        if (y != null) {
                            camera2.b(d.n());
                            camera2.a(f.v());
                            camera2.d(d.n());
                            camera2.c(f.x());
                        }
                    }
                    h = m;
                }
                final k a = com.easypano.tourweaver.o.a(h, x, s3, n, this, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
                Label_0315: {
                    if (!v) {
                        if (a != null) {
                            a.a(camera2);
                            a.a(x);
                            this.b.b(a);
                            a.b();
                            if (!v) {
                                break Label_0315;
                            }
                        }
                        x.a(camera2);
                        this.b.b(x);
                    }
                    this.updateRenderable(null, x, 2);
                }
                this.p = false;
            }
        }
    }
    
    public void switchToScene(final String s) {
        final boolean v = com.easypano.tourweaver.g.v;
        final y f;
        final y y = f = this.a.f(s);
        if (!v && f == null) {
            return;
        }
        double v2 = f.v();
        double w = y.w();
        double x = y.x();
        if (!v) {
            if (!y.j()) {
                final double n = 57.29577951308232;
                v2 = v2 * n - 180.0;
                w *= n;
                x *= n;
            }
            this.switchToScene(s, v2, w, x, y.a(), y.b(), 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, null, null, null);
        }
    }
    
    public void switchToScene(final String s, final double n, final double n2, final double n3) {
        final y f = this.a.f(s);
        this.switchToScene(s, n, n2, n3, f.a(), f.b(), 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, null, null, null);
    }
    
    public void switchToScene(final String s, double a, double a2, double a3, final String s2, final long n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final String s3, final String s4, final String s5) {
        final boolean v = com.easypano.tourweaver.g.v;
        this.n = false;
        this.a();
        final y f = this.a.f(s);
        Label_0062: {
            if (!v) {
                if (f.j()) {
                    break Label_0062;
                }
                a += 180.0;
                a = com.easypano.tourweaver.a.e.a(a);
                a2 = com.easypano.tourweaver.a.e.a(a2);
            }
            a3 = com.easypano.tourweaver.a.e.a(a3);
        }
        final ab k = this.a.k(com.easypano.tourweaver.q.z[0]);
        if (f != null) {
            final ab ab = k;
            if (!v) {
                if (ab == null) {
                    return;
                }
                this.stop();
            }
            final d camera = ab.getCamera();
            final com.easypano.tourweaver.f.o e = this.a.e();
            y y = null;
            final h m = e.m();
            Label_0140: {
                if (!v) {
                    if (m == null) {
                        break Label_0140;
                    }
                    e.m();
                }
                y = (y)m;
            }
            final y y2 = y;
            final y y3 = f;
            if (!v) {
                if (y2 == y3) {
                    this.autoPanScene((int)f.o(), 0, 0);
                    e.b();
                    return;
                }
                e.b(f);
                this.autoPanScene((int)f.o(), 0, 0);
            }
            final k a4 = com.easypano.tourweaver.o.a(y2, y3, s2, n, this, n2, n3, n4, n5, n6, n7, a, a2, a3);
            if (!v) {
                if (a4 != null) {
                    a4.a(e);
                    a4.a(camera);
                    this.b.b(a4);
                    if (!v) {
                        return;
                    }
                }
                camera.a(a, a2, a3);
                e.a(camera);
            }
            this.b.b(e);
        }
    }
    
    public synchronized void fireUpdateScene(final String s) {
        final boolean v = com.easypano.tourweaver.g.v;
        int i = 0;
        while (i < this.c.size()) {
            ((PlayerListener)this.c.elementAt(i)).sceneSwitching(s);
            ++i;
            if (v) {
                break;
            }
        }
    }
    
    public void setResManager(final p r) {
        this.r = r;
    }
    
    public synchronized void fireUpdateSceneEnd(final String s) {
        final boolean v = com.easypano.tourweaver.g.v;
        int i = 0;
        while (i < this.c.size()) {
            final PlayerListener playerListener = this.c.elementAt(i);
            if (!v) {
                if (!(playerListener instanceof CommunicationAction)) {
                    playerListener.sceneSwitched(s);
                }
                ++i;
            }
            if (v) {
                break;
            }
        }
    }
    
    public synchronized void fireUpdateMovie(final String s) {
        final boolean v = com.easypano.tourweaver.g.v;
        int i = 0;
        while (i < this.c.size()) {
            ((PlayerListener)this.c.elementAt(i)).movieSwitching(s);
            ++i;
            if (v) {
                break;
            }
        }
    }
    
    public synchronized void fireUpdateMovieStop(final String s) {
        final boolean v = com.easypano.tourweaver.g.v;
        int i = 0;
        while (i < this.c.size()) {
            ((PlayerListener)this.c.elementAt(i)).movieStoped(s);
            ++i;
            if (v) {
                break;
            }
        }
    }
    
    public synchronized void fireUpdateMoviePause(final String s) {
        final boolean v = com.easypano.tourweaver.g.v;
        int i = 0;
        while (i < this.c.size()) {
            ((PlayerListener)this.c.elementAt(i)).moviePaused(s);
            ++i;
            if (v) {
                break;
            }
        }
    }
    
    public synchronized void fireUpdateMap(final String s) {
        final boolean v = com.easypano.tourweaver.g.v;
        int i = 0;
        while (i < this.c.size()) {
            ((PlayerListener)this.c.elementAt(i)).mapSwitching(s);
            ++i;
            if (v) {
                break;
            }
        }
    }
    
    public synchronized void fireUpdateMapEnd(final String s) {
        final boolean v = com.easypano.tourweaver.g.v;
        int i = 0;
        while (i < this.c.size()) {
            final PlayerListener playerListener = this.c.elementAt(i);
            if (!v) {
                if (!(playerListener instanceof CommunicationAction)) {
                    playerListener.mapSwitched(s);
                }
                ++i;
            }
            if (v) {
                break;
            }
        }
    }
    
    public void updateScene(final int n, final int n2, final int n3, final int n4, final String s) {
        this.a.a(n, n2, n3, n4, s);
    }
    
    public void updateScene(final byte[] array, final int n, final int n2, final int n3, final int n4, final String s) {
        this.a.a(array, n, n2, n3, n4, s);
    }
    
    public void updateScene(final byte[] array, final String s, final int n, final int n2) {
        final boolean v = com.easypano.tourweaver.g.v;
        this.a.a(array, s, n, n2);
        final y h = this.a.h(s);
        if (h == null) {
            return;
        }
        q q = this;
        if (!v) {
            if (this.s != 0) {
                return;
            }
            ++this.s;
            q = this;
        }
        final com.easypano.tourweaver.f.n c = q.a.c(this.f);
        Label_0119: {
            if (!v) {
                if (c == null) {
                    break Label_0119;
                }
                this.playMovie(c.getName());
            }
            if (!v) {
                if (!this.e) {
                    c.c();
                    if (!v) {
                        return;
                    }
                }
                c.b();
            }
            if (!v) {
                return;
            }
        }
        this.switchToScene(h.getName());
    }
    
    public void updateScene(final int n, final int n2, final int n3, final String s) {
        this.a.a(n, n2, n3, s);
    }
    
    public void updateConfig(final com.easypano.tourweaver.d.b b) {
        b.a(this);
    }
    
    public void updateObject(final Object o, final String s) {
        Object o2 = o;
        if (!com.easypano.tourweaver.g.v) {
            if (!(o instanceof y)) {
                return;
            }
            o2 = o;
        }
        final y y = (y)o2;
        this.addRenderable(y, 1);
        this.switchToScene(y.getName());
    }
    
    public void updateRenderable(final com.easypano.tourweaver.f.a a, final h h, final int n) {
        final boolean v = com.easypano.tourweaver.g.v;
        Label_0330: {
            switch (n) {
                case 1: {
                    final y y = (y)h;
                    final boolean e = y.E();
                    Label_0190: {
                        q q2 = null;
                        Label_0186: {
                            final boolean equals;
                            Label_0138: {
                                Label_0124: {
                                    q q = null;
                                    Label_0111: {
                                        if (!v) {
                                            if (!e) {
                                                this.r.a(com.easypano.tourweaver.a.e.a(com.easypano.tourweaver.a.e.h, y.u()).toString());
                                            }
                                            q = this;
                                            if (v) {
                                                break Label_0111;
                                            }
                                            final boolean t = this.t;
                                        }
                                        if (e) {
                                            equals = this.u.equals(y.getName());
                                            if (v) {
                                                break Label_0138;
                                            }
                                            if (equals) {
                                                break Label_0124;
                                            }
                                        }
                                        q = this;
                                    }
                                    q.switchToMap(y.t(), y.getName());
                                }
                                this.t = false;
                                q2 = this;
                                if (v) {
                                    break Label_0186;
                                }
                                final boolean g = this.g;
                            }
                            if (!equals) {
                                final com.easypano.tourweaver.f.o e2 = this.a.e();
                                this.d.a(new l(h, e2.q(), e2.r(), e2.s()));
                                if (!v) {
                                    break Label_0190;
                                }
                            }
                            q2 = this;
                        }
                        q2.g = false;
                    }
                    this.fireUpdateSceneEnd(h.getName());
                    if (v) {
                        break Label_0330;
                    }
                    break;
                }
                case 3: {
                    final s s = (s)h;
                    if (v) {
                        return;
                    }
                    if (!(s.k() instanceof y)) {
                        break;
                    }
                    this.t = true;
                    final y y2 = (y)s.k();
                    this.u = y2.getName();
                    if (!v) {
                        if (!y2.E()) {
                            this.r.a(com.easypano.tourweaver.a.e.a(com.easypano.tourweaver.a.e.h, y2.u()).toString());
                        }
                        this.fireUpdateScene(h.getName());
                        this.switchToMap(y2.t(), y2.getName());
                        this.fireUpdateMap(h.getName());
                    }
                    if (v) {
                        break Label_0330;
                    }
                    break;
                }
                case 2: {
                    this.fireUpdateMapEnd(h.getName());
                    break;
                }
            }
        }
        this.b.b(h);
    }
    
    public void updateAnimation(final com.easypano.tourweaver.f.a a, final com.easypano.tourweaver.f.a a2, final int n) {
        this.b.b(a2);
    }
    
    public void updateProgress(final com.easypano.tourweaver.f.a a, final double n) {
    }
    
    public void pause(final String s) {
        this.fireUpdateMoviePause(s);
    }
    
    public int getRenderNum(final int n) {
        final boolean v = com.easypano.tourweaver.g.v;
        int n2 = n;
        int n4;
        final int n3 = n4 = 1;
        if (!v) {
            if (n == n3) {
                return this.a.b();
            }
            final int n5 = n;
            n2 = n;
            if (v) {
                return n5;
            }
            n4 = 2;
        }
        if (n2 == n4) {
            return this.a.c();
        }
        return 0;
    }
    
    public com.easypano.tourweaver.f.n getCurMovie() {
        return this.o;
    }
    
    static {
        final String[] z = new String[4];
        final int n = 0;
        final char[] charArray = "\u0011bX\u001e\u000b\u0014hX\u0007\u000b0".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0097: {
                if (n2 > 1) {
                    break Label_0097;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = 'B';
                            break;
                        }
                        case 1: {
                            c2 = '\u0001';
                            break;
                        }
                        case 2: {
                            c2 = '=';
                            break;
                        }
                        case 3: {
                            c2 = 'p';
                            break;
                        }
                        default: {
                            c2 = 'n';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "\u0003tI\u001f".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0213: {
                if (n6 > 1) {
                    break Label_0213;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = 'B';
                            break;
                        }
                        case 1: {
                            c4 = '\u0001';
                            break;
                        }
                        case 2: {
                            c4 = '=';
                            break;
                        }
                        case 3: {
                            c4 = 'p';
                            break;
                        }
                        default: {
                            c4 = 'n';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "jlR\u0006\u0007'!T\u0003N,tQ\u001cN-s\u001d\u0004\u000f0fX\u0004N+r\u001d\u001e\u001b.m\u0013Y".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0329: {
                if (n10 > 1) {
                    break Label_0329;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = 'B';
                            break;
                        }
                        case 1: {
                            c6 = '\u0001';
                            break;
                        }
                        case 2: {
                            c6 = '=';
                            break;
                        }
                        case 3: {
                            c6 = 'p';
                            break;
                        }
                        default: {
                            c6 = 'n';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        z[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "\u000f`M&\u0007'vX\u0002".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0445: {
                if (n14 > 1) {
                    break Label_0445;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = 'B';
                            break;
                        }
                        case 1: {
                            c8 = '\u0001';
                            break;
                        }
                        case 2: {
                            c8 = '=';
                            break;
                        }
                        case 3: {
                            c8 = 'p';
                            break;
                        }
                        default: {
                            c8 = 'n';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 <= n16) {
                z[n13] = new String(charArray4).intern();
                q.z = z;
                return;
            }
            continue;
        }
    }
}
