// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Component;
import java.util.Vector;
import java.util.Enumeration;
import java.awt.Dialog;
import java.net.NoRouteToHostException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Image;
import java.awt.Frame;
import java.util.Hashtable;

public abstract class cx extends cs
{
    private bp a;
    private Hashtable a;
    private Hashtable b;
    private static Hashtable c;
    
    public void a(final r r) {
        switch (r.a) {
            case 67585: {
                this.b(r);
            }
            case 65794: {
                for (int i = 0; i < r.b; ++i) {
                    final aZ az;
                    if ((az = (aZ)super.c.b(r.a(i, 0))) != null) {
                        final aP ap;
                        if ((ap = (aP)super.d.b(az.b)) != null) {
                            if ((!az.a(23) || this.a(24)) && (this.a(52) || !az.a(18) || az.i == super.i)) {
                                final aP ap2 = ap;
                                --ap2.a;
                            }
                            if (super.a != null) {
                                super.a.b(ap);
                            }
                            if (az.b == super.b && !ap.a(57) && (!az.a(23) || this.a(24)) && (this.a(52) || !az.a(18) || az.i == super.i)) {
                                final String a;
                                String s;
                                if ((a = r.a(i, 0)) == null) {
                                    s = bm.a(aS.a(53), new String[] { super.a.a });
                                }
                                else {
                                    s = this.a(a) + " " + bm.a(aS.a(53), new String[] { super.a.a });
                                }
                                final aU au;
                                (au = new aU(s, az, (b)super.b.b(az.a))).k = az.e;
                                final Y y;
                                if (az.e != 0 && (y = (Y)super.q.b(az.e)) != null) {
                                    au.b = y.a;
                                }
                                if (az.b != null && !az.a(0)) {
                                    au.c = az.b;
                                    au.f = true;
                                }
                                if (super.a != null) {
                                    super.a.a(au);
                                }
                            }
                        }
                        if (super.a != null) {
                            super.a.a(az);
                        }
                        final aW aw;
                        if ((aw = (aW)super.a.b(az.i)) != null) {
                            final aU au2;
                            (au2 = new aU(bm.a(aS.a(53), new String[] { super.a.a }), az, (b)super.b.b(az.a))).k = az.e;
                            final Y y2;
                            if (az.e != 0 && (y2 = (Y)super.q.b(az.e)) != null) {
                                au2.b = y2.a;
                            }
                            if (az.b != null && !az.a(0)) {
                                au2.c = az.b;
                                au2.f = true;
                            }
                            aw.a(au2);
                            aw.dispose();
                        }
                        try {
                            super.c.b((Object)az);
                        }
                        finally {
                            throw loadexception(java.lang.Throwable.class);
                        }
                    }
                }
            }
            case 66049: {
                this.e(r);
            }
            case 66305: {
                this.u(r);
            }
            case 66308:
            case 66310: {
                this.q(r);
            }
            case 66306: {
                this.t(r);
            }
            case 66307: {
                this.i(r);
            }
            case 66561: {
                this.h(r);
            }
            case 66816:
            case 50400771: {
                this.f(r);
            }
            case 66817: {
                this.j(r);
            }
            case 67073: {
                this.v(r);
            }
            case 67074: {
                final ai ai;
                if ((ai = (ai)(this = this).c.b(r.a(0, 0))) != null) {
                    final r r2;
                    (r2 = new r(67073, 1)).e = ai.i;
                    r2.a(0, 0, super.i);
                    if (!super.q || ai.a(39)) {
                        r2.a(0, 1, super.w);
                        if (super.h != null && super.h.length() > 0) {
                            r2.a(0, 0, super.h);
                        }
                        if (super.l != null && super.l.length() > 0) {
                            r2.a(0, 1, super.l);
                        }
                        if (super.i != null && super.i.length() > 0) {
                            r2.a(0, 2, super.i);
                        }
                        if (super.j != null && super.j.length() > 0) {
                            r2.a(0, 3, super.j);
                        }
                        if (super.x != -999) {
                            r2.a(0, super.x, true);
                        }
                    }
                    this.o(r2);
                }
            }
            case 67329: {
                this.w(r);
            }
            case 67330: {
                this.l(r);
            }
            case 67331: {
                this.y(r);
            }
            case 67333: {
                this.k(r);
            }
            case 67334: {
                this.A(r);
            }
            case 67584: {
                this.c(r);
            }
            case 67586: {
                this.d(r);
            }
            case 67843: {
                final long d = (this = this).d;
                super.n = r.a(0, 0);
                r.a(0, 1);
                super.d = r.a(0);
                super.y = r.a(0, 0);
                super.z = r.a(0, 1);
                super.A = r.a(0, 2);
                super.B = r.a(0, 3);
                super.C = r.a(0, 4);
                super.D = r.a(0, 5);
                if (super.a != null) {
                    super.a.a(super.n);
                    super.a.a().a = ((super.z <= 0) ? 7 : super.z);
                    if (d != super.d) {
                        super.a.b(r.a(super.d, 49));
                    }
                }
            }
            case 68608: {
                this.g(r);
            }
            case 65812: {
                this.s(r);
            }
            case 67338: {}
            case 67341: {
                this.m(r);
            }
            case 132866: {
                this.B(r);
            }
            case 132867: {
                this.C(r);
            }
            case 2125858: {
                r.a(0, 0);
                final e e;
                if ((e = (e)super.t.b(r.a(0, 0))) != null) {
                    if (!e.isShowing()) {
                        e.setVisible(true);
                    }
                    final aZ az2;
                    if ((az2 = (aZ)super.c.b(r.a(0, 1))) != null) {
                        aU au3;
                        if (r.a(0, 2) == 0) {
                            au3 = new aU(bm.a(aS.a(677), new String[] { az2.d }), aS.a(678), null, -999);
                        }
                        else {
                            au3 = new aU(bm.a(aS.a(679), new String[] { az2.d }), aS.a(680), null, -999);
                        }
                        au3.g = true;
                        au3.j = 15002318;
                        e.a(au3);
                    }
                }
            }
            case 2125857: {
                r.a(0, 0);
                final bD bd;
                (bd = new bD(super.a.a(), aS.a(682), new String[] { aS.a(2), aS.a(3) }, new String[] { bm.a(aS.a(681), new String[] { r.a(0, 0) }) }, new n(this), this)).setModal(false);
                bd.setVisible(true);
                this.b.put(bd, r);
            }
            case 33621775: {
                this.x(r);
            }
            case 67343: {
                this.z(r);
            }
            case 67588: {
                if ((this = this).a[0] != r.a(0)[0] || super.a[1] != r.a(0)[1]) {
                    this.a(r.a(0));
                    final aF af = (aF)super.a;
                    final cx cx = this;
                    final aF af2 = af;
                    final aI a2 = af.a;
                    final cx cx2 = cx;
                    final aI ai2 = a2;
                    a2.removeAll();
                    ai2.a(cx2);
                    ai2.validate();
                    ai2.repaint();
                    if (af2.a != null) {
                        af2.a.a(af2.a);
                    }
                    af2.validate();
                    af2.repaint();
                    this.b();
                }
            }
            case 67351: {
                this.r(r);
            }
            case 2125859: {
                this.D(r);
                break;
            }
        }
    }
    
    public abstract void f();
    
    public final Image a(final String s, final boolean b, final int n) {
        return this.b(s, b, n);
    }
    
    private Image b(final String s, final boolean b, final int n) {
        if (super.a == null) {
            return null;
        }
        try {
            URL url;
            if (b) {
                url = new URL(super.a, "Resources/" + super.f + "/" + s);
            }
            else {
                url = new URL(super.a, "Resources/" + s);
            }
            final Image a;
            if ((a = this.a(url)) != null) {
                super.a.addImage(a, n);
                super.a.statusID(n, true);
            }
            return a;
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    private Image b(final String s) {
        if (super.a == null) {
            return null;
        }
        try {
            final Image a;
            if ((a = this.a(new URL("http://" + super.a.getHost() + "/" + s))) != null) {
                super.a.addImage(a, 45);
                super.a.statusID(45, true);
            }
            return a;
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    public void a(final String s, final String s2, final aV av, URL url, final int n, final String s3) {
        try {
            super.m = false;
            super.h.a();
            String s4;
            if (bU.c != null) {
                s4 = bU.c;
            }
            else {
                s4 = a.getHost();
            }
            super.a = a;
            super.g = s;
            super.d = s;
            if (ce.b && ce.c < 65792 && ce.b == 1) {
                url = (URL)2;
            }
            else {
                url = (URL)256;
            }
            boolean a2 = false;
            for (int n2 = 0; !a2 && n2 < super.a.a.size(); ++n2) {
                final int a3 = super.a.a(n2);
                try {
                    if (a3 != bU.d) {
                        super.a = new Socket(s4, a3);
                        super.a = new bN(new BufferedOutputStream(super.a.getOutputStream(), 256));
                        if (super.a.a(n2) == super.a.a) {
                            super.a = new bR(super.a.getInputStream());
                        }
                        else {
                            super.a = new bR(new BufferedInputStream(super.a.getInputStream(), (int)url));
                        }
                        try {
                            final t t2;
                            final t t = t2 = new t();
                            final long a4 = t.a(t.b, t2.a, t2.a);
                            final t t3 = t;
                            t.c = t.a(a4, t3.a, t3.a);
                            final long c = t3.c;
                            super.a.writeLong(c);
                            super.a.writeLong(bB.a());
                            super.a.flush();
                            final long long1 = super.a.readLong();
                            final bR a5 = super.a;
                            final byte[] a6 = chat.t.a(c);
                            final bR br = a5;
                            a5.a = a6;
                            br.a = 0;
                            final bN a7 = super.a;
                            final byte[] a8 = chat.t.a(long1);
                            final bN bn = a7;
                            a7.a = a8;
                            bn.a = 0;
                        }
                        catch (IOException ex10) {}
                        a2 = this.a();
                    }
                }
                catch (Exception ex) {
                    if (n2 == super.a.a.size() - 1) {
                        throw ex;
                    }
                }
            }
            if (a2) {
                if (super.a != null && super.a.isAlive()) {
                    super.a.interrupt();
                }
                if (super.a != null && super.a.isAlive()) {
                    super.a.interrupt();
                }
                super.a = new bj(this);
                super.a = new Thread(this, "Decoder");
                super.l = false;
                super.a.start();
                final r r;
                (r = new r(65793, 1)).a(0, 0, n);
                r.a(0, 1, super.s);
                r.a(0, 0, s);
                r.a(0, 1, bU.b.startsWith("ar") ? "ar" : "en");
                r.a(0, 2, s3);
                r.a(0, 3, s2);
                r.a(0, 0, av);
                if (this.a(23)) {
                    r.a(0, 23, true);
                }
                if (this.a(18)) {
                    r.a(0, 18, true);
                }
                this.o(r);
            }
            else {
                System.err.println("acknowledge() failed!!!");
            }
        }
        catch (UnknownHostException ex2) {
            this.h();
            new bD(bm.a(aS.a(37), new String[] { super.a.a }), ex2, this).setVisible(true);
        }
        catch (InterruptedIOException ex3) {
            this.h();
            new bD(bm.a(aS.a(38), new String[] { super.a.a }), ex3, this).setVisible(true);
        }
        catch (NoRouteToHostException ex4) {
            this.h();
            new bD(bm.a(aS.a(39), new String[] { super.a.a }), ex4, this).setVisible(true);
        }
        catch (SecurityException ex5) {
            this.h();
            new bD(bm.a(aS.a(39), new String[] { super.a.a }), ex5, this).setVisible(true);
        }
        catch (IOException ex7) {
            final IOException ex6 = ex7;
            ex7.printStackTrace();
            this.h();
            new bD(bm.a(aS.a(40), new String[] { super.a.a }), ex6, this).setVisible(true);
        }
        catch (Exception ex9) {
            final Exception ex8 = ex9;
            ex9.printStackTrace();
            this.h();
            new bD(bm.a(aS.a(41), new String[] { super.a.a }), ex8, this).setVisible(true);
        }
        super.h = false;
    }
    
    public final void p(final r r) {
        super.v.a();
        try {
            for (int i = 0; i < r.b; ++i) {
                final int n = i;
                bw bw = (bw)super.v.b(n);
                if (r.a(i, 63)) {
                    if (bw != null) {
                        super.v.b(n);
                    }
                }
                else {
                    if (bw == null) {
                        bw = new bw(n, r.a(i, 1));
                        super.v.a(bw);
                    }
                    else {
                        bw.d = r.a(i, 1);
                    }
                    bw.a = r.a(i, 0);
                    bw.b = r.a(i, 2);
                    bw.b = r.a(i);
                }
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
        this.a();
    }
    
    public void c() {
        if (ce.c <= 66048) {
            if (cx.c != null) {
                cx.c.clear();
            }
            try {
                synchronized (super.b) {
                    for (int i = 0; i < super.b.a(); ++i) {
                        final b b;
                        if ((b = (b)super.b.a(i)) != null && b.a != null) {
                            try {
                                b.a.flush();
                                b.a = null;
                            }
                            catch (SecurityException ex) {}
                        }
                    }
                }
            }
            finally {
                throw loadexception(java.lang.Throwable.class);
            }
            try {
                synchronized (super.q) {
                    for (int j = 0; j < super.q.a(); ++j) {
                        final Y y;
                        if ((y = (Y)super.q.a(j)) != null && y.a != null) {
                            try {
                                y.a.flush();
                                y.a = null;
                            }
                            catch (SecurityException ex2) {}
                        }
                    }
                }
            }
            finally {
                throw loadexception(java.lang.Throwable.class);
            }
            final Enumeration a = ae.a();
            while (a.hasMoreElements()) {
                final ae ae;
                if ((ae = a.nextElement()) != null && ae.a != null) {
                    try {
                        ae.a.flush();
                        ae.a = null;
                    }
                    catch (SecurityException ex3) {}
                }
            }
        }
        final cx cx;
        cx.b.a();
        cx.c.a();
        cx.d.a();
        cx.e.a();
        cx.f.a();
        cx.g.a();
        cx.h.a();
        cx.i.a();
        cx.j.a();
        cx.k.a();
        cx.v.a();
        cx.p.a();
        cx.m.a();
        cx.q.a();
        cx.r.a();
        cx.s.a();
        cx.u.a();
        cx.l.a();
        if (cx.a != null) {
            for (int k = 0; k < cx.a.a(); ++k) {
                ((bo)cx.a.a(k)).dispose();
            }
        }
        if (cx.t != null) {
            for (int l = 0; l < cx.t.a(); ++l) {
                ((e)cx.t.a(l)).dispose();
            }
        }
        cx.a.a();
        cx.t.a();
        final Enumeration<Dialog> elements = cx.a.elements();
        while (elements.hasMoreElements()) {
            final Dialog nextElement;
            if ((nextElement = elements.nextElement()) instanceof Dialog) {
                nextElement.dispose();
            }
        }
        if (cx.b != null) {
            cx.b.clear();
        }
        cx.h();
        if (cx.a != null) {
            final Frame a2 = cx.a.a();
            cx.a.a();
            cx.a = null;
            if (cx.a != null) {
                cx.a.setVisible(false);
            }
            cx.a = null;
            if (a2 != null) {
                a2.dispose();
            }
        }
        try {
            Thread.sleep(500L);
        }
        catch (InterruptedException ex4) {}
        if (cx.a != null) {
            try {
                cx.a.close();
                cx.a.close();
                cx.a.close();
            }
            catch (Exception ex5) {}
        }
        cx.a = null;
        cx.a = null;
        cx.a = null;
        cx.o = null;
        cx.l = true;
    }
    
    public void a(final URL url, final String s) {
    }
    
    private void a(final String s, final ai ai, final int n, final boolean b, final int n2, final int n3) {
        this.a(s, ai, n, b, false, n2, n3, null);
    }
    
    private void a(final String s, final ai ai, final int n, final boolean b, final byte[] array) {
        this.a(s, ai, n, b, false, 0, 0, array);
    }
    
    private void a(final String s, final ai ai, final int n, final boolean b, final boolean b2, final int n2, final int n3, final byte[] a) {
        if (super.a != null) {
            int i;
            if ((i = ai.i) == super.i) {
                i = n;
            }
            final b b3 = (b)super.b.b(ai.a);
            final Object[] a2 = this.a(s, ai.a(41) ? super.J : super.F);
            final aU au;
            (au = new aU((String)a2[0], ai, n != -1 && n != -3 && n != -2 && !b, b3, n2, n3)).d = ai.a(41);
            if (au.d) {
                au.j = super.O;
            }
            au.c = (n == -3 || n == -2);
            au.a = (Vector)a2[1];
            au.a();
            if (ai.i == -999 && ai.a(41) && ai.a(23) && ai.a(24)) {
                if (ai.a(62)) {
                    au.k = 1;
                }
                else if (ai.a(52)) {
                    au.k = 2;
                }
                else if (ai.a(5) && ai.a(6)) {
                    au.k = 6;
                }
                else if (ai.a(24)) {
                    au.k = 3;
                }
                else if (ai.a(59)) {
                    au.k = 4;
                }
                else if (ai.a(61)) {
                    au.k = 5;
                }
                else {
                    au.k = 0;
                }
            }
            au.k = ai.e;
            final Y y;
            if (ai.e != 0 && (y = (Y)super.q.b(ai.e)) != null) {
                au.b = y.a;
            }
            if (ai instanceof aZ) {
                if (((aZ)ai).b != null && !ai.a(0)) {
                    au.c = ((aZ)ai).b;
                    au.f = true;
                }
            }
            else if (ai.a != null && !ai.a(0)) {
                au.c = ai.a;
                au.f = true;
            }
            au.c = (n == -3 || n == -2);
            au.a = a;
            if (au.b) {
                final aW aw;
                if ((aw = (aW)super.a.b(i)) != null) {
                    aw.a(au);
                    return;
                }
                if (super.p) {
                    this.a(au, ai);
                    return;
                }
                au.d = false;
                super.a.a(au);
            }
            else {
                au.a = ai.c;
                if (n == -3 || n == -2) {
                    this.a(au);
                    return;
                }
                super.a.a(au);
            }
        }
    }
    
    public void c(final r r) {
        super.a.a = r.a(0, 1);
        super.p = r.a(0, 2);
        super.i = r.e;
        this.a(r.a(0));
        super.f = r.a(0, 0);
        cs.a[0] = aS.a(42);
        cs.a[1] = aS.a(43);
        cs.a[2] = aS.a(44);
        cs.a[3] = aS.a(45);
        cs.a[4] = aS.a(46);
        cs.a[5] = aS.a(47);
        cs.a[6] = aS.a(48);
        cs.a[7] = aS.a(49);
        cs.a[8] = aS.a(50);
        if (!"Admin".equals(super.f)) {
            if (super.a.a()) {
                super.a.c = this.a(super.a.c() + "background.gif", true);
            }
            if (super.a.b()) {
                super.a.d = this.a(super.a.c() + "chatbackground.gif", true);
            }
            if (bU.a) {
                (super.a = new aF(this, null)).setVisible(false);
                bU.a.setVisible(false);
                bU.a.add(super.a.a());
                ((aF)super.a).validate();
            }
            else {
                super.a = new bA(this).a;
            }
            if (super.a != null) {
                super.a.a(r.a(super.h, 5));
            }
        }
    }
    
    public void d(final r r) {
        super.h = true;
        super.b = -999;
        if (super.t == -999) {
            this.i(super.r);
        }
        else {
            this.i(super.t);
        }
        final aP ap = (aP)super.d.b(super.s);
        final aP ap2 = (aP)super.d.b(super.q);
        if (ap == null || (ap.a == null && ap2.a != null)) {
            this.a(ap2);
        }
        else {
            this.a(ap);
        }
        if (super.a != null) {
            super.a.a().a();
        }
    }
    
    protected final void a(final aZ az, final int n, final long n2, final int n3) {
        final int b = az.b;
        final aP ap = (aP)super.d.b(n);
        final aP ap2 = (aP)super.d.b(az.b);
        if (az.i == super.i) {
            if (ap2 != null) {
                ap2.c = false;
                ap2.d = true;
            }
            ap.c = true;
            super.b = n;
            if (super.a != null) {
                if (!super.m) {
                    this.b(false);
                }
                super.a.a(ap);
                if (!super.a.isVisible()) {
                    super.a.setVisible(true);
                }
                super.a.a().validate();
            }
        }
        if (ap != null) {
            ap.a = this.a(ap.i);
            if (ap2 != null && ap != null) {
                if (ap2.i != ap.i && (!az.a(23) || this.a(24)) && (this.a(52) || !az.a(18) || az.i == super.i)) {
                    final aP ap3 = ap;
                    ++ap3.a;
                }
            }
            else if (ap2 == null && ap != null && (!az.a(23) || this.a(24)) && (this.a(52) || !az.a(18) || az.i == super.i)) {
                final aP ap4 = ap;
                ++ap4.a;
            }
            if (super.a != null) {
                super.a.b(ap);
            }
        }
        if (ap2 != null) {
            ap2.a = this.a(ap2.i);
            if (ap2 != null && ap != null && ap2.i != ap.i && (!az.a(23) || this.a(24)) && (this.a(52) || !az.a(18) || az.i == super.i)) {
                final aP ap5 = ap2;
                --ap5.a;
            }
            if (super.a != null) {
                super.a.b(ap2);
            }
        }
        if (n == super.b && az.b != n) {
            if (ap != null && !ap.a(57) && (!az.a(23) || this.a(24)) && (this.a(52) || !az.a(18) || az.i == super.i)) {
                final aU au;
                (au = new aU(bm.a(aS.a(51), new String[] { ap.d }), az, (b)super.b.b(az.a))).k = az.e;
                final Y y;
                if (az.e != 0 && (y = (Y)super.q.b(az.e)) != null) {
                    au.b = y.a;
                }
                if (az.b != null && !az.a(0)) {
                    au.c = az.b;
                    au.f = true;
                }
                if (super.a != null) {
                    super.a.a(au);
                }
            }
            if (ap.a != null && az.i == super.i) {
                final aU au2;
                (au2 = new aU(ap.a, ap.d, null, -999)).h = ap.e;
                au2.g = true;
                au2.i = ap.f;
                au2.j = ap.g;
                if (super.a != null) {
                    super.a.a(au2);
                }
            }
        }
        else if (az.b == super.b && n != super.b && ap2 != null && ap != null && !ap2.a(57) && (!az.a(23) || this.a(24)) && (this.a(52) || !az.a(18) || az.i == super.i)) {
            final aU au3;
            (au3 = new aU(bm.a(aS.a(52), new String[] { ap.d }), az, (b)super.b.b(az.a))).k = az.e;
            final Y y2;
            if (az.e != 0 && (y2 = (Y)super.q.b(az.e)) != null) {
                au3.b = y2.a;
            }
            if (az.b != null && !az.a(0)) {
                au3.c = az.b;
                au3.f = true;
            }
            if (super.a != null) {
                super.a.a(au3);
            }
        }
        az.b = n;
        if (super.a != null) {
            if (super.u) {
                if (az.g > 0 && (super.m || n == super.b) && (!super.n || cs.a(az, super.a.a()))) {
                    super.a.a(az, true);
                    return;
                }
                super.a.a(az);
            }
            else {
                if ((super.m || n == super.b) && (!super.n || cs.a(az, super.a.a()))) {
                    super.a.a(az, true);
                    return;
                }
                if (!super.m && b == super.b) {
                    super.a.a(az);
                }
            }
        }
    }
    
    private void q(final r r) {
        for (int i = 0; i < r.b; ++i) {
            if (r.a != null) {
                this.a(r.a);
            }
            ai ai;
            if ((ai = ((r.a(i, 0) == -1) ? null : ((ai)super.c.b(r.a(i, 0))))) == null && r.a(0) != 0L) {
                (ai = new ai(-999, r.a(0, 1))).c = r.a(0, 2);
                ai.a = r.a(0, 1);
                ai.e = r.a(0, 5);
                if (r.a(0, 3) != null) {
                    ai.c = r.a(0, 3);
                    ai.a = this.b("superImages/" + r.a(0, 3), true, 35);
                }
                ai.b = r.a(0);
            }
            else if (ai == null && r.a(0) == 0L) {
                (ai = new ai(-999, "Guest")).a = super.r;
            }
            if (ai != null) {
                int n;
                if ((n = ai.i) == super.i) {
                    n = r.e;
                }
                if (!ai.d) {
                    switch (r.e) {
                        case -3:
                        case -2:
                        case -1: {
                            if (!ai.c) {
                                break;
                            }
                            break;
                        }
                        default: {
                            super.a.a(n);
                            break;
                        }
                    }
                    if (r.a != null) {
                        this.a(r.a(i, 0), ai, r.e, true, r.a);
                    }
                    else {
                        this.a(r.a(i, 0), ai, r.e, true, r.a(i, 3), r.a(i, 4));
                    }
                }
            }
        }
    }
    
    private void r(final r r) {
        try {
            super.P = (r.a(-1, 4) ? 1 : 0);
            super.Q = (int)(r.a(-1) & 0xFL);
            for (int i = 0; i < r.b; ++i) {
                final int a = r.a(i, 0);
                bu bu = (bu)super.s.b(a);
                if (r.a(i, 63)) {
                    if (bu != null) {
                        super.s.b(a);
                    }
                }
                else {
                    if (bu == null) {
                        super.s.a(bu = new bu(a, r.a(i, 0)));
                    }
                    else {
                        bu.d = r.a(i, 0);
                    }
                    bu.b = r.a(i);
                    bu.a = r.a(i, 1);
                    bu.b = r.a(i, 2);
                    final int a2;
                    bu.a = ((((a2 = r.a(i, 3)) & 0xFFFFFF) == 0x0 && bu.a(4)) ? null : new Color(a2));
                    final int a3;
                    bu.b = ((((a3 = r.a(i, 4)) & 0xFFFFFF) == 0x0 && bu.a(5)) ? null : new Color(a3));
                    bu.a = r.a(i, 1);
                }
            }
        }
        finally {
            if (super.a != null) {
                super.a.b();
            }
        }
    }
    
    private void s(final r r) {
        try {
            for (int i = 0; i < r.b; ++i) {
                final int a = r.a(i, 0);
                cE ce = (cE)super.r.b(a);
                if (r.a(i, 63)) {
                    if (ce != null) {
                        super.r.b(a);
                    }
                }
                else {
                    if (ce == null) {
                        ce = new cE(a, r.a(i, 0));
                        super.r.a(ce);
                    }
                    else {
                        ce.d = r.a(i, 0);
                    }
                    ce.a = r.a(i, 1);
                    ce.b = r.a(i);
                }
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
        if (super.a != null) {
            super.a.c();
            for (int j = 0; j < super.a.a(); ++j) {
                final bo bo;
                if ((bo = (bo)super.a.a(j)).a.a(14) && bo.a != null) {
                    bo.a.a(2);
                    bo.a.a = bo.a.a(4);
                    bo.a.b = true;
                }
            }
        }
    }
    
    private void t(final r r) {
        if (super.a != null) {
            for (int i = 0; i < r.b; ++i) {
                final aU au;
                (au = new aU(r.a(0, 1) ? r.a(i, 1) : this.a(r.a(i, 1)), r.a(0, 1) ? r.a(i, 0) : this.a(r.a(i, 0)), (b)super.b.b(r.a(i, 0)), -999)).h = r.a(i, 1);
                au.g = true;
                au.i = r.a(i, 2);
                au.j = r.a(i, 3);
                au.h = true;
                super.a.a(au);
            }
        }
    }
    
    private void u(final r r) {
        for (int i = 0; i < r.b; ++i) {
            final ai ai;
            if ((ai = (ai)super.c.b(r.a(i, 0))) != null) {
                int n;
                if ((n = ai.i) == super.i) {
                    n = r.e;
                }
                if (r.e != super.i && r.a(0, 20)) {
                    return;
                }
                if (!ai.d) {
                    switch (r.e) {
                        case -3:
                        case -2:
                        case -1: {
                            if (!ai.c) {
                                break;
                            }
                            break;
                        }
                        default: {
                            super.a.a(n);
                            break;
                        }
                    }
                    if (r.a != null) {
                        final String a = r.a(i, 0);
                        final ai ai2 = ai;
                        final int e = r.e;
                        final boolean b = false;
                        r.a(0, 20);
                        this.a(a, ai2, e, b, r.a);
                    }
                    else {
                        this.a(r.a(i, 0), ai, r.e, false, r.a(i, 1), r.a(i, 2));
                    }
                }
            }
        }
    }
    
    private void v(final r r) {
        for (int i = 0; i < r.b; ++i) {
            final ai ai;
            if ((ai = (ai)super.c.b(r.a(i, 0))) != null) {
                if (super.s) {
                    final String a;
                    if ((a = r.a(i, 2)) != null) {
                        try {
                            this.a(new URL(a), "_blank");
                        }
                        catch (MalformedURLException ex) {}
                    }
                }
                else {
                    new aT(super.a.a(), this, ai, r, i);
                }
            }
        }
    }
    
    private void w(final r r) {
        r.a(-1);
        try {
            for (int i = 0; i < r.b; ++i) {
                final int a = r.a(i, 0);
                c c = (c)super.e.b(a);
                if (r.a(i, 63)) {
                    if (c != null) {
                        super.e.b(a);
                        if (super.a != null) {
                            super.a.a(a);
                        }
                    }
                }
                else {
                    if (c == null) {
                        c = new c(a, r.a(i, 0));
                        super.e.a(c);
                    }
                    else {
                        c.d = r.a(i, 0);
                    }
                    c.a = r.a(i, 1);
                    c.b = r.a(i, 2);
                    c.b = r.a(i, 1);
                    c.a = r.a(i, 2);
                    c.c = r.a(i, 3);
                    c.b = r.a(i);
                    c.a = this.b("banners/" + c.a, true, 30);
                    if (super.a != null) {
                        super.a.a(c);
                    }
                }
            }
        }
        finally {
            if (super.a != null) {
                super.a.a().a();
            }
        }
    }
    
    private void x(final r r) {
        for (int b = r.b, i = 0; i < b; ++i) {
            final ae ae;
            (ae = new ae(r.a(i, 0), r.a(i, 0))).b = r.a(i);
            ae.a = r.a(i, 1);
            if (!ae.a(63)) {
                ae.a = this.b("emoticons/" + ae.a, true, 50);
            }
            chat.ae.a(ae);
        }
    }
    
    private void y(final r r) {
        try {
            for (int i = 0; i < r.b; ++i) {
                final int a = r.a(i, 0);
                b b = (b)super.b.b(a);
                if (r.a(i, 63)) {
                    if (b != null) {
                        super.b.b(a);
                    }
                }
                else {
                    if (b == null) {
                        b = new b(a, r.a(i, 0));
                        super.b.a(b);
                    }
                    else {
                        b.d = r.a(i, 0);
                    }
                    b.a = this.b("userIcons/" + b.d, true, 40);
                    b.a = r.a(i, 1);
                    b.b = r.a(i);
                    if (r.a(i, 62)) {
                        super.r = a;
                    }
                }
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
    
    private void z(final r r) {
        try {
            for (int i = 0; i < r.b; ++i) {
                final int a = r.a(i, 0);
                Y y = (Y)super.q.b(a);
                if (r.a(i, 63)) {
                    if (y != null) {
                        super.q.b(a);
                    }
                }
                else {
                    if (y == null) {
                        y = new Y(a, r.a(i, 0));
                        super.q.a(y);
                    }
                    else {
                        y.d = r.a(i, 0);
                    }
                    if (a == 1) {
                        y.a = this.b(y.d, false, 13);
                    }
                    else {
                        y.a = this.b("stars/" + y.d, true, 13);
                    }
                    y.b = r.a(i);
                }
            }
            if (!super.q.a(1)) {
                final Y y2 = new Y(1, "star_1.gif");
                super.q.a(y2);
                y2.a = this.b(y2.d, false, 13);
            }
            if (!super.q.a(0)) {
                final Y y3 = new Y(0, "star_0.gif");
                super.q.a(y3);
                y3.a = this.b(y3.d, false, 13);
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
    
    private void A(final r r) {
        try {
            for (int i = 0; i < r.b; ++i) {
                final int a = r.a(i, 0);
                aZ az = (aZ)super.c.b(a);
                if (r.a(i)) {
                    if (az != null) {
                        final aP ap;
                        if ((ap = (aP)super.d.b(r.a(i, 2))) != null) {
                            if ((!az.a(23) || this.a(24)) && (this.a(52) || !az.a(18) || az.i == super.i)) {
                                final aP ap2 = ap;
                                --ap2.a;
                            }
                            if (super.a != null) {
                                super.a.b(ap);
                            }
                        }
                        super.c.b(a);
                        if (super.a != null) {
                            super.a.a(az);
                        }
                        final aW aw;
                        if ((aw = (aW)super.a.b(az.i)) != null) {
                            aw.dispose();
                        }
                    }
                }
                else {
                    int n = 0;
                    final String a2 = this.a(r.a(i, 0));
                    if (az == null) {
                        if ((!(az = new aZ(a, a2)).a(23) || this.a(24)) && (this.a(52) || !az.a(18) || az.i == super.i)) {
                            super.c.a(az);
                        }
                    }
                    else {
                        az.d = a2;
                        n = ((super.u && az.g != r.a(i, 7)) ? 1 : 0);
                    }
                    az.a = r.a(i, 1);
                    az.a = (b)super.b.b(az.a);
                    if (az.b == -999 || r.a(i, 2) != -999) {
                        az.b = r.a(i, 2);
                    }
                    az.c = r.a(i, 3);
                    az.e = r.a(i, 4);
                    az.d = r.a(i, 5);
                    az.f = r.a(i, 6);
                    az.g = r.a(i, 7);
                    az.a = (Y)super.q.b(r.a(i, 4));
                    az.a(r.a(i));
                    az.a();
                    final String a3 = r.a(i, 1);
                    final String a4 = r.a(i, 2);
                    final String a5 = r.a(i, 3);
                    if (a3 != null) {
                        az.a = a3;
                    }
                    if (a4 != null) {
                        az.b = a4;
                    }
                    final Image b;
                    if (a4 != null && cx.c.get(a4) == null && (b = this.b("flags/" + a4 + ".gif")) != null) {
                        cx.c.put(a4, b);
                    }
                    if (a5 != null) {
                        az.c = a5;
                        if (az.b == null) {
                            az.b = this.b("superImages/" + a5, true, 35);
                        }
                    }
                    else if (az.b != null) {
                        az.b.flush();
                        az.b = null;
                    }
                    if (a == super.i) {
                        super.d = az.d;
                        if (az.a != -999) {
                            super.t = az.a;
                            super.d = az.d;
                            super.e = az.e;
                            super.c = az.c;
                            super.f = az.f;
                            super.c = az.c;
                        }
                    }
                    if (super.a != null) {
                        super.a.a(az, false);
                        if (n != 0) {
                            super.b(super.m);
                        }
                    }
                }
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
    
    private void a(final e e) {
        final Dimension screenSize;
        int n = (screenSize = Toolkit.getDefaultToolkit().getScreenSize()).width / 2 - 20;
        int n2 = screenSize.height / 2 - 20;
        int n3 = 0;
        int n4 = 0;
        switch (super.t.a() % 4) {
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
        if (n > 550) {
            n = 550;
        }
        if (n2 > 550) {
            n2 = 550;
        }
        e.reshape(n4 + 2, n3 + 10, n, n2);
    }
    
    private void B(final r r) {
        if (super.t == null) {
            return;
        }
        e e;
        if ((e = (e)super.t.b(r.a(0, 0))) == null && r.f == super.i && (e = (e)super.t.b(0)) != null) {
            super.t.b((Object)e);
            super.t.a(e, r.a(0, 0));
            e.a = r.a(0, 0);
            this.a(null, 0, 0, r.a(0, 0));
        }
        if (e == null) {
            e = new e(this, r.a(0, 0));
            this.a(e);
            super.t.a(e, r.a(0, 0));
            this.a(null, 0, 0, r.a(0, 0));
        }
        e.setVisible(true);
        final boolean b = r.b == 1;
        for (int i = 0; i < r.b; ++i) {
            if (r.a(i, 1)) {
                e.a.a(r.a(i, 1));
            }
            else {
                e.a.a(r.a(i, 1), b);
            }
        }
    }
    
    private void C(final r r) {
        if (super.t == null) {
            return;
        }
        final e e;
        if ((e = (e)super.t.b(r.f)) == null) {
            return;
        }
        for (int i = 0; i < r.b; ++i) {
            if (super.a != null) {
                ai a;
                if ((a = e.a.a(r.a(i, 0))) == null) {
                    (a = new ai(-999, r.a(0, 1))).c = r.a(0, 2);
                    a.a = r.a(0, 1);
                    a.e = r.a(0, 6);
                    if (r.a(0, 3) != null) {
                        a.c = r.a(0, 3);
                        a.a = this.b("superImages/" + r.a(0, 3), true, 35);
                    }
                    a.b = r.a(0);
                }
                final b b = (b)super.b.b(a.a);
                final Object[] a2 = this.a(r.a(i, 0), a.a(41) ? super.J : super.F);
                final aU au;
                (au = new aU((String)a2[0], a, false, b, 0, 0)).d = a.a(41);
                if (au.d) {
                    au.j = super.O;
                }
                au.a = (Vector)a2[1];
                au.a();
                if (a.i == -999 && a.a(41) && a.a(23) && a.a(24)) {
                    if (a.a(62)) {
                        au.k = 1;
                    }
                    else if (a.a(52)) {
                        au.k = 2;
                    }
                    else if (a.a(5) && a.a(6)) {
                        au.k = 6;
                    }
                    else if (a.a(24)) {
                        au.k = 3;
                    }
                    else if (a.a(59)) {
                        au.k = 4;
                    }
                    else if (a.a(61)) {
                        au.k = 5;
                    }
                    else {
                        au.k = 0;
                    }
                }
                au.k = a.e;
                final Y y;
                if (a.e != 0 && (y = (Y)super.q.b(a.e)) != null) {
                    au.b = y.a;
                }
                if (a instanceof aZ) {
                    if (((bx)a).b != null && !a.a(0)) {
                        au.c = ((bx)a).b;
                        au.f = true;
                    }
                }
                else if (a.a != null && !a.a(0)) {
                    au.c = a.a;
                    au.f = true;
                }
                au.a = a.c;
                e.a(au);
            }
        }
    }
    
    public final void a(final aU au, final ai ai) {
        final int i = ai.i;
        if (((ak)super.d.b(ai.b)).a(61) && ai.a(59) && !super.o) {
            return;
        }
        bo bo;
        if ((bo = (bo)super.a.b(i)) == null) {
            bo = new aW(this, i);
            final Dimension screenSize;
            int n = (screenSize = Toolkit.getDefaultToolkit().getScreenSize()).width / 2 - 20;
            int n2 = screenSize.height / 2 - 20;
            int n3 = 0;
            int n4 = 0;
            switch (super.a.a() % 4) {
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
            bo.reshape(n4 + 2, n3 + 10, n, n2);
            super.a.a(bo, i);
        }
        if (au != null) {
            bo.a(au);
        }
        bo.setVisible(true);
    }
    
    public final void a(final bD bd, final String s) {
        int n = 0;
        final r r = this.b.get(bd);
        if (aS.a(3).equals(s)) {
            n = 1;
        }
        else if (aS.a(2).equals(s)) {
            n = 0;
        }
        if (r.a == 2125857) {
            if (n == 0 && super.t.b(r.a(0, 0)) == null) {
                final e e;
                (e = new e(this, r.a(0, 0))).setTitle(e.getTitle() + ": " + r.a(0, 1));
                this.a(e);
                super.t.a(e, r.a(0, 0));
            }
            final r r2;
            (r2 = new r(2125858, 1)).e = r.a(0, 2);
            r2.d = -1;
            r2.a(0, 0, r.a(0, 0));
            r2.a(0, 1, super.i);
            r2.a(0, 2, n);
            this.o(r2);
        }
        this.b.remove(bd);
    }
    
    private void D(final r r) {
        if (r.b == 0) {
            return;
        }
        final cw cw = new cw(this, (ai)super.c.b(r.a(0, 0)), r.b);
        for (int i = 0; i < r.b; ++i) {
            final cs cs;
            final aZ az;
            if ((az = (aZ)cs.c.b(r.a(i, 0))) != null) {
                final cw cw2 = cw;
                final aZ az2 = az;
                final cw cw3 = cw2;
                synchronized (cw2.a) {
                    if ((cw3.a.a(24) || !az2.a(23)) && (cw3.a.a(52) || !az2.a(18) || az2.i == cw3.a.i)) {
                        final int a;
                        if ((a = cw3.a.a((a)az2)) == -1) {
                            cw3.a.c(az2);
                        }
                        else {
                            cw3.a.a(az2, a);
                        }
                        int n;
                        if (az2.a(25) && az2.a(18)) {
                            n = 2;
                        }
                        else if (az2.a(24) && az2.a(23)) {
                            n = 1;
                        }
                        else {
                            n = 0;
                        }
                        if (az2.c) {
                            cw3.a.a(az2, Color.red, Color.pink, new Color(15658734), n);
                        }
                        else if (az2.c != 0) {
                            cw3.a.a(az2, new Color(az2.c), new Color(10079487), new Color((az2.f == 0) ? 15658734 : az2.f), n);
                        }
                        else {
                            cw3.a.a(az2, Color.black, Color.white, new Color((az2.f == 0) ? 15658734 : az2.f), n);
                        }
                        if (az2.d) {
                            cw3.a.b(az2, true);
                        }
                        else {
                            cw3.a.b(az2, false);
                        }
                    }
                }
            }
        }
    }
    
    private void i(final int n) {
        final r r;
        (r = new r(67334, 1)).a(0, 0, super.i);
        r.a(0, 1, n);
        r.a(0, 0, super.d);
        r.e = -1;
        r.d = -1;
        this.o(r);
    }
    
    public final void g(int n) {
        n = (0x1 | n << 4);
        final r r;
        (r = new r(67334, 1)).a(0, 0, super.i);
        r.a(0, 1, super.t);
        r.a(0, 5, n);
        r.a(0, 0, super.d);
        r.e = -1;
        r.d = -1;
        this.o(r);
    }
    
    public final void h(final int n) {
        final r r;
        (r = new r(67334, 1)).a(0, 0, super.i);
        r.a(0, 1, super.t);
        r.a(0, 0, super.d);
        r.a(0, (long)n);
        r.e = -1;
        r.d = -1;
        this.o(r);
    }
    
    public final void f(final int n) {
        if (super.a.a() != null) {
            super.a.a().setCursor(3);
        }
        if (this.a == null) {
            this.a = new bp(super.a.a(), this);
        }
        final bp a;
        if ((a = this.a).a) {
            ((bM)a.a).a(0);
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
    
    public static Image a(final String s) {
        if (s == null) {
            return null;
        }
        final Image value;
        if ((value = cx.c.get(s)) == null) {
            return null;
        }
        return value;
    }
    
    public cx() {
        this.a = new Hashtable();
        this.b = new Hashtable();
    }
    
    static {
        cx.c = new Hashtable();
    }
}
