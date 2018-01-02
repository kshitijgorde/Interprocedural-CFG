// 
// Decompiled by Procyon v0.5.30
// 

package neat.system;

import java.util.EventListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.applet.Applet;
import neat.system.graphics.renderer.j;
import java.io.IOException;
import neat.mb;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ByteArrayInputStream;
import java.awt.Component;
import java.awt.Image;
import neat.system.graphics.renderer.p;
import java.net.URL;
import neat.nb;
import neat.r;
import neat.pb;
import neat.i;
import neat.h;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.KeyListener;

public class lb extends kb implements ab, gb, KeyListener, MouseListener, MouseMotionListener
{
    private static neat.kb f;
    private static neat.kb g;
    private static f h;
    public static final Object i;
    private transient ib j;
    private boolean k;
    private boolean l;
    private h m;
    private i n;
    private fb o;
    private neat.kb p;
    private neat.kb q;
    private Object r;
    private pb s;
    private int t;
    private int u;
    private k v;
    private static /* synthetic */ Class w;
    private static String[] z;
    
    public void a(final neat.kb kb, final boolean b) {
        if (this.o != null) {
            if (!this.o.b() && !this.o.d()) {
                throw new RuntimeException(lb.z[7]);
            }
            this.n();
        }
        this.o = fb.j();
        final neat.kb s = this.s();
        this.o.a(kb, b, true, false, s, this.j.c(), this.j.d());
        s.f();
        this.o();
    }
    
    public boolean b() {
        if (this.o == null) {
            return false;
        }
        if (this.o.b()) {
            this.o();
            if (this.o == null) {
                return false;
            }
        }
        return true;
    }
    
    public boolean c() {
        return this.o != null && this.o.a() && this.o.d();
    }
    
    public int d() {
        if (this.o == null) {
            return 100;
        }
        return this.o.c();
    }
    
    private void n() {
        if (this.o != null) {
            this.o.f();
            this.o = null;
        }
    }
    
    private void o() {
        if (this.o == null) {
            return;
        }
        if (!this.o.a()) {
            return;
        }
        if (this.o.b()) {
            final h f = this.o.f();
            final r a = f.a();
            while (a.a()) {
                final neat.kb kb = (neat.kb)a.b();
                this.m.a(kb, f.g(kb));
            }
            a.f();
            f.f();
            this.n();
        }
    }
    
    private void p() {
        this.m.c();
    }
    
    public void q() {
        qb.a();
    }
    
    private void r() {
        final r f = this.n.f();
        while (f.a()) {
            this.b((eb)f.b());
        }
        f.f();
    }
    
    public final void a(final neat.kb kb, final neat.kb kb2) {
        if (this.p != null) {
            this.p.f();
            this.p = null;
        }
        if (kb != null) {
            this.p = kb.b();
        }
        if (this.q != null) {
            this.q.f();
            this.q = null;
        }
        if (kb2 != null) {
            this.q = kb2.b();
        }
    }
    
    private neat.kb s() {
        neat.kb kb = null;
        final URL c = this.j.c();
        if (c != null) {
            final String host = c.getHost();
            if (host != null) {
                final neat.kb a = neat.kb.a(host);
                final neat.kb l = nb.l(a);
                a.f();
                int n = 0;
                final int c2 = l.c(46);
                if (c2 > 0) {
                    boolean b = true;
                    for (int i = l.d() - 1; i > c2; --i) {
                        final char b2 = l.b(i);
                        if ((b2 < '0' || b2 > '9') && b2 != ':') {
                            b = false;
                            break;
                        }
                    }
                    if (!b) {
                        int b3 = l.b(46, c2 - 1);
                        if (b3 < 0) {
                            b3 = 0;
                        }
                        else {
                            ++b3;
                        }
                        n = b3;
                    }
                }
                if (n > 0) {
                    kb = l.d(n);
                }
                else {
                    kb = l.b();
                }
                l.f();
                if (this.p != null && this.q != null) {
                    int n2 = 0;
                    if (kb.d() > 0) {
                        n2 = ((this.p.d(kb) >= 0) ? 1 : 0);
                    }
                    if (n2 != 0) {
                        kb.f();
                        kb = this.q.b();
                    }
                }
            }
        }
        final neat.kb b4 = b(kb);
        if (kb != null) {
            kb.f();
        }
        return b4;
    }
    
    protected static neat.kb b(final neat.kb kb) {
        final neat.lb a = neat.lb.a();
        a.a(lb.f);
        if (kb != null) {
            a.a(kb);
        }
        return a.b();
    }
    
    public void c(final neat.kb kb) {
        this.b(kb, true);
    }
    
    public void b(final neat.kb kb, final boolean b) {
        final db b2 = this.j.b();
        if (b2 != null) {
            try {
                final URL url = new URL(b2.getDocumentBase(), kb.toString());
                neat.kb kb2;
                if (b) {
                    kb2 = neat.kb.a(lb.z[6]);
                }
                else {
                    kb2 = neat.kb.a(lb.z[5]);
                }
                b2.a(url, kb2);
                kb2.f();
                return;
            }
            catch (Throwable t) {}
        }
        ((p)this.b(neat.system.graphics.renderer.p.j)).b(kb);
    }
    
    public Image a(final int n, final int n2) {
        Image image = null;
        int n3 = 0;
        while (true) {
            try {
                final Component a = this.j.a();
                if (a != null) {
                    image = a.createImage(n, n2);
                }
                break;
            }
            catch (Throwable t2) {
                final Throwable t = t2;
                if (++n3 >= 3) {
                    throw new RuntimeException(lb.z[4] + n + lb.z[2] + n2 + lb.z[3] + nb.a(t));
                }
                try {
                    System.gc();
                    Thread.yield();
                    Thread.sleep(500L);
                    System.gc();
                    Thread.yield();
                    Thread.sleep(500L);
                }
                catch (Throwable t3) {}
            }
        }
        return image;
    }
    
    private void t() {
        synchronized (this.r) {
            this.s = pb.d();
        }
        // monitorexit(this.r)
    }
    
    private void u() {
        Label_0019: {
            break Label_0019;
            g v = null;
            do {
                v.f();
                v = this.v();
            } while (v != null);
        }
        synchronized (this.r) {
            this.s.f();
            this.s = null;
        }
        // monitorexit(this.r)
    }
    
    private void b(final g g) {
        synchronized (this.r) {
            if (this.s == null) {
                g.f();
            }
            else {
                this.s.a(g);
            }
        }
        // monitorexit(this.r)
    }
    
    private g v() {
        g g = null;
        synchronized (this.r) {
            if (this.s != null && this.s.c() > 0) {
                g = (g)this.s.a();
            }
        }
        // monitorexit(this.r)
        return g;
    }
    
    private void w() {
        if (this.j == null) {
            return;
        }
        if (this.j.e()) {
            return;
        }
        Label_0066: {
            break Label_0066;
            g v = null;
            do {
                if (v instanceof m || v instanceof l) {
                    this.a((k)v);
                }
                else {
                    this.a(v);
                }
                v = this.v();
            } while (v != null);
        }
    }
    
    private void x() {
        this.t = 0;
        this.u = 0;
    }
    
    private void y() {
        if (this.v != null) {
            this.v.f();
            this.v = null;
        }
    }
    
    public void a(final int u) {
        this.u = u;
    }
    
    private void a(final k k) {
        if (this.v != null) {
            this.v.f();
            this.v = null;
        }
        if (this.t >= this.u) {
            this.a(k);
            this.t = 0;
        }
        else {
            if (k instanceof m) {
                this.v = neat.system.m.a((m)k);
            }
            else if (k instanceof l) {
                this.v = neat.system.l.a((l)k);
            }
            k.f();
        }
    }
    
    private void b(final int n) {
        if (this.t < this.u) {
            this.t += n;
            if (this.t < this.u) {
                return;
            }
            this.t = this.u;
        }
        if (this.v != null) {
            this.a(this.v);
            this.v = null;
        }
    }
    
    protected void j() {
        this.c(neat.system.nb.g);
    }
    
    protected void k() {
        super.k();
        this.w();
    }
    
    public void receiveEvent(final t t) {
        this.o();
        this.b((int)t.f);
    }
    
    public void a(final eb eb) {
        this.n.a(eb);
    }
    
    public void b(final eb eb) {
        if (!this.n.d(eb)) {
            throw new RuntimeException(lb.z[13] + eb);
        }
        eb.a();
    }
    
    public qb e(final neat.kb kb) {
        final Object g = this.m.g(kb);
        if (g == null) {}
        if (!(g instanceof qb)) {
            return null;
        }
        return (qb)g;
    }
    
    public neat.kb a(final neat.kb kb) {
        final Object g = this.m.g(kb);
        if (g == null) {}
        if (g instanceof neat.kb) {
            return (neat.kb)g;
        }
        if (g instanceof qb) {
            final qb qb = (qb)g;
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(qb.b(), 0, qb.a())));
            final mb a = mb.a();
            try {
                Label_0132: {
                    break Label_0132;
                    int i = 0;
                    do {
                        a.a((char)i);
                        i = bufferedReader.read();
                    } while (i >= 0);
                }
                bufferedReader.close();
            }
            catch (IOException ex) {}
            qb.c();
            final neat.kb b = a.b();
            this.m.c(kb);
            this.m.a(kb.b(), b);
            return b;
        }
        return null;
    }
    
    public j a(final neat.kb kb, final int n, final boolean b) {
        final Object g = this.m.g(kb);
        final neat.kb kb2 = null;
        if (g == null) {}
        if (g instanceof j) {
            if (kb2 != null) {
                kb2.f();
            }
            return (j)g;
        }
        if (g instanceof qb) {
            final qb qb = (qb)g;
            if (kb.c(neat.system.graphics.renderer.p.f) || kb.c(neat.system.graphics.renderer.p.h)) {
                final j a = ((p)this.b(neat.system.graphics.renderer.p.j)).a(kb, qb.b(), qb.a(), n);
                qb.c();
                if (a == null) {
                    throw new RuntimeException(lb.z[1] + kb);
                }
                if (b) {
                    this.m.c(kb);
                    this.m.a(kb.b(), a);
                }
                if (kb2 != null) {
                    kb2.f();
                }
                return a;
            }
        }
        if (kb2 != null) {
            kb2.f();
        }
        return null;
    }
    
    public j a(final int n, final int n2) {
        return ((p)this.b(neat.system.graphics.renderer.p.j)).a(this.a(n, n2), n, n2, -1);
    }
    
    public j a(final int n, final int n2, final int n3) {
        return ((p)this.b(neat.system.graphics.renderer.p.j)).a(this.a(n, n2), n, n2, n3);
    }
    
    public j a(final qb qb, final int n, final int n2) {
        final j a = ((p)this.b(neat.system.graphics.renderer.p.j)).a(qb.b(), qb.a(), n, n2, -1);
        qb.c();
        return a;
    }
    
    public hb b(final neat.kb kb) {
        final neat.lb a = neat.lb.a();
        a.a(kb);
        a.a(fb.c);
        final neat.kb b = a.b();
        final Object g = this.m.g(b);
        if (g instanceof hb) {
            b.f();
            return (hb)g;
        }
        if (g == null) {}
        if (g instanceof qb) {
            final hb b2 = hb.b(b, (qb)g);
            this.m.b(b);
            this.m.a(b.b(), b2);
            b.f();
            return b2;
        }
        b.f();
        return null;
    }
    
    public j c(final neat.kb kb) {
        try {
            final URL c = this.j.c();
            while (true) {
                String s = kb.toString();
                boolean k = this.k;
                if (s.endsWith(lb.z[10])) {
                    if (!this.k) {
                        s = s.substring(0, s.length() - 4) + lb.z[8];
                    }
                    else {
                        if (!this.l) {
                            this.l = true;
                            try {
                                if (System.getProperty(lb.z[12]).startsWith(lb.z[11])) {
                                    this.k = false;
                                }
                                else {
                                    final String property = System.getProperty(lb.z[9]);
                                    final int length = property.length();
                                    final char c2 = (length > 0) ? property.charAt(0) : '\0';
                                    final char c3 = (length > 1) ? property.charAt(1) : '\0';
                                    final char c4 = (length > 2) ? property.charAt(2) : '\0';
                                    if (c2 == '\0' || c2 == '0') {
                                        this.k = false;
                                    }
                                    else if (c2 == '1' && c3 == '.' && (c4 < '2' || c4 > '9')) {
                                        this.k = false;
                                    }
                                }
                            }
                            catch (Throwable t) {
                                this.k = false;
                            }
                        }
                        if (!this.k) {
                            continue;
                        }
                        if (nb.j(kb) == null) {
                            this.k = false;
                            continue;
                        }
                    }
                }
                else {
                    k = false;
                }
                URL url;
                if (c == null) {
                    url = new URL(s);
                }
                else {
                    url = new URL(c, s);
                }
                final Component a = this.j.a();
                if (!(a instanceof Applet)) {
                    return null;
                }
                Object audioClip = null;
                try {
                    audioClip = ((Applet)a).getAudioClip(url);
                }
                catch (Throwable t2) {}
                if (audioClip != null) {
                    return ((p)this.b(neat.system.graphics.renderer.p.j)).a(audioClip);
                }
                if (!k) {
                    return null;
                }
                this.k = false;
            }
        }
        catch (Throwable t3) {
            return null;
        }
    }
    
    public qb d(final neat.kb kb) {
        final Object g = this.m.g(kb);
        if (g == null) {
            final byte[] j = nb.j(kb);
            if (j == null) {
                return null;
            }
            final qb a = qb.a(j, 0, j.length);
            this.m.a(kb.b(), a);
            return a;
        }
        else {
            if (!(g instanceof qb)) {
                return null;
            }
            return (qb)g;
        }
    }
    
    public boolean a() {
        return this.j == null || this.j.e();
    }
    
    public void receiveEvent(final g g) {
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
    }
    
    public void validateObject() throws InvalidObjectException {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.b(neat.system.n.a(mouseEvent));
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.b(neat.system.o.a(mouseEvent));
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        this.b(neat.system.l.a(mouseEvent));
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.b(neat.system.m.a(mouseEvent));
    }
    
    public void keyPressed(final KeyEvent keyEvent) {
        this.b(neat.system.i.a(keyEvent));
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
        this.b(neat.system.j.a(keyEvent));
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public static lb a(final kb kb, final ib j) {
        final lb lb = (lb)neat.system.lb.h.a();
        lb.j = j;
        lb.a(kb, neat.system.lb.i);
        j.a(lb);
        return lb;
    }
    
    public void f() {
        lb.h.a(this);
    }
    
    public void g() {
        super.g();
        this.k = true;
        this.l = false;
        this.m = neat.h.e();
        this.n = neat.i.k();
        this.t();
        this.x();
    }
    
    public void h() {
        this.y();
        this.u();
        if (this.j != null) {
            this.j.b(this);
            this.j = null;
        }
        this.r();
        this.n.f();
        this.n = null;
        this.n();
        this.p();
        this.m.f();
        this.m = null;
        super.h();
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public lb() {
        this.j = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = new Object();
        this.s = null;
        this.v = null;
    }
    
    static {
        lb.z = new String[] { z(z("b\u0014\u000f\u00067\u0001\u0019\u000e@'\u0001\u001c\f@$DO")), z(z("b\u0014\u000f\u00067\u0001\u0011\u0004B,E\u0010AC*U\u0018\u0000Qy")), z(z("\u0001_A")), z(z("\bU@+")), z(z("d\r\u0002D3U\u001c\u000eOcH\u001bAH.@\u0012\u0004\u0001 S\u0010\u0000U*O\u0012A\t")), z(z("~\u0006\u0004M%")), z(z("~\u0001\u000eQ")), z(z("n\u0001\tD1\u0001\u0019\u000e@'H\u001b\u0006\u0001*RU\u000eOcQ\u0007\u000eF1D\u0006\u0012\u0001b")), z(z("\u000f\u0014\u0014")), z(z("K\u0014\u0017@mW\u0010\u0013R*N\u001b")), z(z("\u000f\u0002\u0000W")), z(z("l\u001c\u0002S,R\u001a\u0007U")), z(z("K\u0014\u0017@mW\u0010\u000fE,S")), z(z("u\u001d\bRcS\u0010\u0012N6S\u0016\u0004\u0001 N\u001b\u0012T.D\u0007AO,UU\u0000E'D\u0011AU,\u0001\u0005\u0013N'T\u0016\u0004Sy")), z(z("O\u0010\u0000UmR\f\u0012U&L[\rC")) };
        lb.f = neat.kb.a(z(z("o\u0010\u0000Ucs\u0010\u0012N6S\u0016\u0004\u000f")));
        lb.g = neat.kb.a(z(z("V\u0002\u0016\u000f")));
        lb.h = new f((lb.w != null) ? lb.w : (lb.w = a(lb.z[14])));
        i = ((lb.w != null) ? lb.w : (lb.w = a(lb.z[14])));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        int i;
        do {
            i = charArray.length;
            if (i < 2) {
                continue;
            }
            return charArray;
        } while (i == 0);
        final int n = 0;
        charArray[n] ^= 'C';
        return charArray;
    }
    
    private static String z(final char[] array) {
        int length;
        int n2;
        final int n = n2 = (length = array.length);
        int n3 = 0;
        while (true) {
            Label_0086: {
                if (n > 1) {
                    break Label_0086;
                }
                length = (n2 = n3);
                do {
                    final char c = array[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = '!';
                            break;
                        }
                        case 1: {
                            c2 = 'u';
                            break;
                        }
                        case 2: {
                            c2 = 'a';
                            break;
                        }
                        case 3: {
                            c2 = '!';
                            break;
                        }
                        default: {
                            c2 = 'C';
                            break;
                        }
                    }
                    array[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                return new String(array).intern();
            }
            continue;
        }
    }
}
