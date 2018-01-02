// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Event;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Color;
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
import java.awt.Component;
import java.util.Hashtable;

public abstract class bl extends bh
{
    private aB a;
    private Hashtable a;
    private Hashtable b;
    
    public final void a(final m m) {
        switch (m.a) {
            case 67844: {
                (this = this).d = m.a(0);
                super.v = m.a(0, 0);
                if (super.v < 1) {
                    super.v = 300;
                }
                super.w = m.a(0, 1);
                m.a(0, 2);
                super.x = m.a(0, 3);
                m.a(0, 4);
                super.B = m.a(0, 5);
                super.C = m.a(0, 6);
                super.y = m.a(0, 7);
                if (super.a != null) {
                    super.a.a(m.a(super.d, 5));
                }
                if (super.y < 1) {
                    super.y = 300;
                }
                super.z = m.a(0, 8);
                super.A = m.a(0, 9);
                m.a(0, 10);
            }
            case 67585: {
                this.e(m);
            }
            case 65794: {
                for (int i = 0; i < m.b; ++i) {
                    final au au;
                    if ((au = (au)super.c.b(m.a(i, 0))) != null) {
                        final ai ai;
                        if ((ai = (ai)super.d.b(au.b)) != null) {
                            if ((!au.a(23) || this.a(24)) && (this.a(52) || !au.a(18) || au.g == super.g)) {
                                final ai ai2 = ai;
                                --ai2.a;
                            }
                            if (super.a != null) {
                                super.a.b(ai);
                            }
                            if (au.b == super.b && !ai.a(57) && (!au.a(23) || this.a(24)) && (this.a(52) || !au.a(18) || au.g == super.g)) {
                                final String a;
                                String s;
                                if ((a = m.a(i, 0)) == null) {
                                    s = ak.a(ak.a(53), new String[] { super.a.a });
                                }
                                else {
                                    s = this.a(a) + " " + ak.a(ak.a(53), new String[] { super.a.a });
                                }
                                final ao ao;
                                (ao = new ao(s, au, (b)super.b.b(au.a))).k = au.e;
                                final F f;
                                if (au.e != 0 && (f = (F)super.q.b(au.e)) != null) {
                                    ao.b = f.a;
                                }
                                if (au.b != null && !au.a(0)) {
                                    ao.c = au.b;
                                    ao.e = true;
                                }
                                if (super.a != null) {
                                    super.a.a(ao);
                                }
                            }
                        }
                        if (super.a != null) {
                            super.a.a(au);
                        }
                        final aq aq;
                        if ((aq = (aq)super.a.b(au.g)) != null) {
                            final ao ao2;
                            (ao2 = new ao(ak.a(ak.a(53), new String[] { super.a.a }), au, (b)super.b.b(au.a))).k = au.e;
                            final F f2;
                            if (au.e != 0 && (f2 = (F)super.q.b(au.e)) != null) {
                                ao2.b = f2.a;
                            }
                            if (au.b != null && !au.a(0)) {
                                ao2.c = au.b;
                                ao2.e = true;
                            }
                            aq.a(ao2);
                            aq.dispose();
                        }
                        try {
                            super.c.a((Object)au);
                        }
                        finally {
                            throw loadexception(java.lang.Throwable.class);
                        }
                    }
                }
            }
            case 66049: {
                this.b(m);
            }
            case 66305: {
                this.r(m);
            }
            case 66308:
            case 66310: {
                this.n(m);
            }
            case 66306: {
                this.q(m);
            }
            case 66307: {
                this.g(m);
            }
            case 66561: {
                this.f(m);
            }
            case 66816:
            case 50400771: {
                this.c(m);
            }
            case 66817: {
                this.h(m);
            }
            case 67073: {
                this.s(m);
            }
            case 67074: {
                final aj aj;
                if ((aj = (aj)(this = this).c.b(m.a(0, 0))) != null) {
                    final m j;
                    (j = new m(67073, 1)).e = aj.g;
                    j.a(0, 0, super.g);
                    j.a(0, 1, super.p);
                    if (super.q != -999) {
                        j.a(0, super.q, true);
                    }
                    this.m(j);
                }
            }
            case 67329: {
                this.t(m);
            }
            case 67330: {
                this.j(m);
            }
            case 67331: {
                this.v(m);
            }
            case 67333: {
                this.i(m);
            }
            case 67334: {
                this.x(m);
            }
            case 67584: {
                (this = this).a.a = m.a(0, 1);
                super.g = m.a(0, 2);
                super.g = m.e;
                this.a(m.a(0));
                super.b = m.a(0, 0);
                bh.a[0] = ak.a(42);
                bh.a[1] = ak.a(43);
                bh.a[2] = ak.a(44);
                bh.a[3] = ak.a(45);
                bh.a[4] = ak.a(46);
                bh.a[5] = ak.a(47);
                bh.a[6] = ak.a(48);
                bh.a[7] = ak.a(49);
                bh.a[8] = ak.a(50);
                if (!"Admin".equals(super.b)) {
                    if (super.a.a()) {
                        super.a.c = this.a(super.a.b() + "background.gif", true);
                    }
                    if (super.a.b()) {
                        super.a.d = this.a(super.a.b() + "chatbackground.gif", true);
                    }
                    if (aV.a) {
                        (super.a = new af(this, null)).setVisible(false);
                        aV.a.setVisible(false);
                        aV.a.add(super.a.a());
                        ((af)super.a).validate();
                    }
                    else {
                        super.a = new aJ(this).a;
                    }
                    if (super.a != null) {
                        super.a.a(m.a(super.d, 5));
                    }
                }
            }
            case 67586: {
                (this = this).f = true;
                super.b = -999;
                if (super.m == -999) {
                    this.e(super.k);
                }
                else {
                    this.e(super.m);
                }
                final ai ai3 = (ai)super.d.b(super.l);
                final ai ai4 = (ai)super.d.b(super.j);
                if (ai3 == null || (ai3.a == null && ai4.a != null)) {
                    this.a(ai4);
                }
                else {
                    this.a(ai3);
                }
                if (super.a != null) {
                    super.a.a().a();
                }
            }
            case 67843: {
                final long b = (this = this).b;
                super.f = m.a(0, 0);
                m.a(0, 1);
                super.b = m.a(0);
                m.a(0, 0);
                super.r = m.a(0, 1);
                m.a(0, 2);
                super.s = m.a(0, 3);
                super.t = m.a(0, 4);
                super.u = m.a(0, 5);
                if (super.a != null) {
                    super.a.a(super.f);
                    super.a.a().a = ((super.r <= 0) ? 7 : super.r);
                    if (b != super.b) {
                        super.a.b(m.a(super.b, 49));
                    }
                }
            }
            case 68608: {
                this.d(m);
            }
            case 65812: {
                this.p(m);
            }
            case 67338: {}
            case 67341: {
                this.k(m);
            }
            case 132866: {
                this.y(m);
            }
            case 132867: {
                this.z(m);
            }
            case 2125858: {
                m.a(0, 0);
                final d d;
                if ((d = (d)super.t.b(m.a(0, 0))) != null) {
                    if (!d.isShowing()) {
                        d.setVisible(true);
                    }
                    final au au2;
                    if ((au2 = (au)super.c.b(m.a(0, 1))) != null) {
                        ao ao3;
                        if (m.a(0, 2) == 0) {
                            ao3 = new ao(ak.a(ak.a(677), new String[] { au2.c }), ak.a(678), null, -999);
                        }
                        else {
                            ao3 = new ao(ak.a(ak.a(679), new String[] { au2.c }), ak.a(680), null, -999);
                        }
                        ao3.f = true;
                        ao3.j = 15002318;
                        d.a(ao3);
                    }
                }
            }
            case 2125857: {
                m.a(0, 0);
                final ad ad;
                (ad = new ad(super.a.a(), ak.a(682), new String[] { ak.a(2), ak.a(3) }, new String[] { ak.a(ak.a(681), new String[] { m.a(0, 0) }) }, new h(this), this)).setModal(false);
                ad.setVisible(true);
                this.b.put(ad, m);
            }
            case 33621775: {
                this.u(m);
            }
            case 67343: {
                this.w(m);
            }
            case 67588: {
                if ((this = this).a[0] != m.a(0)[0] || super.a[1] != m.a(0)[1]) {
                    this.a(m.a(0));
                    final af af = (af)super.a;
                    final bl bl = this;
                    final af af2 = af;
                    final ah a2 = af.a;
                    final bl bl2 = bl;
                    final ah ah = a2;
                    a2.removeAll();
                    ah.a(bl2);
                    ah.validate();
                    ah.repaint();
                    if (af2.a != null) {
                        af2.a.a(af2.a);
                    }
                    af2.validate();
                    af2.repaint();
                }
            }
            case 67351: {
                this.o(m);
                break;
            }
        }
    }
    
    public abstract void h();
    
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
                url = new URL(super.a, "Resources/" + super.b + "/" + s);
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
    
    public void a(final String s, final String s2, final ap ap, URL url, final int n, final String s3) {
        try {
            super.i = false;
            super.h.a();
            String s4;
            if (aV.c != null) {
                s4 = aV.c;
            }
            else {
                s4 = a.getHost();
            }
            super.a = a;
            super.d = s;
            super.c = s;
            if (aZ.b && aZ.c < 65792 && aZ.b == 1) {
                url = (URL)2;
            }
            else {
                url = (URL)256;
            }
            boolean a2 = false;
            for (int n2 = 0; !a2 && n2 < super.a.a.size(); ++n2) {
                final int a3 = super.a.a(n2);
                try {
                    if (a3 != aV.d) {
                        super.a = new Socket(s4, a3);
                        super.a = new aS(new BufferedOutputStream(super.a.getOutputStream(), 256));
                        if (super.a.a(n2) == super.a.a) {
                            super.a = new aU(super.a.getInputStream());
                        }
                        else {
                            super.a = new aU(new BufferedInputStream(super.a.getInputStream(), (int)url));
                        }
                        try {
                            final n n4;
                            final n n3 = n4 = new n();
                            final long a4 = n3.a(n3.b, n4.a, n4.a);
                            final n n5 = n3;
                            n3.c = n3.a(a4, n5.a, n5.a);
                            final long c = n5.c;
                            super.a.writeLong(c);
                            super.a.writeLong(aK.a());
                            super.a.flush();
                            final long long1 = super.a.readLong();
                            final aU a5 = super.a;
                            final byte[] a6 = n.a(c);
                            final aU au = a5;
                            a5.a = a6;
                            au.a = 0;
                            final aS a7 = super.a;
                            final byte[] a8 = n.a(long1);
                            final aS as = a7;
                            a7.a = a8;
                            as.a = 0;
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
                super.a = new ax(this);
                super.a = new Thread(this, "Decoder");
                super.h = false;
                super.a.start();
                final m m;
                (m = new m(65793, 1)).a(0, 0, n);
                m.a(0, 1, super.l);
                m.a(0, s);
                m.a(1, aV.b.startsWith("ar") ? "ar" : "en");
                m.a(2, s3);
                m.a(3, s2);
                m.a(ap);
                if (this.a(23)) {
                    m.a(0, 23, true);
                }
                if (this.a(18)) {
                    m.a(0, 18, true);
                }
                this.m(m);
            }
            else {
                System.err.println("acknowledge() failed!!!");
            }
        }
        catch (UnknownHostException ex2) {
            this.d();
            new ad(ak.a(ak.a(37), new String[] { super.a.a }), ex2, this).setVisible(true);
        }
        catch (InterruptedIOException ex3) {
            this.d();
            new ad(ak.a(ak.a(38), new String[] { super.a.a }), ex3, this).setVisible(true);
        }
        catch (NoRouteToHostException ex4) {
            this.d();
            new ad(ak.a(ak.a(39), new String[] { super.a.a }), ex4, this).setVisible(true);
        }
        catch (SecurityException ex5) {
            this.d();
            new ad(ak.a(ak.a(39), new String[] { super.a.a }), ex5, this).setVisible(true);
        }
        catch (IOException ex7) {
            final IOException ex6 = ex7;
            ex7.printStackTrace();
            this.d();
            new ad(ak.a(ak.a(40), new String[] { super.a.a }), ex6, this).setVisible(true);
        }
        catch (Exception ex9) {
            final Exception ex8 = ex9;
            ex9.printStackTrace();
            this.d();
            new ad(ak.a(ak.a(41), new String[] { super.a.a }), ex8, this).setVisible(true);
        }
        super.f = false;
    }
    
    public final void a() {
        if (aZ.c <= 66048) {
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
                        final F f;
                        if ((f = (F)super.q.a(j)) != null && f.a != null) {
                            try {
                                f.a.flush();
                                f.a = null;
                            }
                            catch (SecurityException ex2) {}
                        }
                    }
                }
            }
            finally {
                throw loadexception(java.lang.Throwable.class);
            }
            final Enumeration a = K.a();
            while (a.hasMoreElements()) {
                final K k;
                if ((k = a.nextElement()) != null && k.a != null) {
                    try {
                        k.a.flush();
                        k.a = null;
                    }
                    catch (SecurityException ex3) {}
                }
            }
        }
        final bl bl;
        bl.b.a();
        bl.c.a();
        bl.d.a();
        bl.e.a();
        bl.f.a();
        bl.g.a();
        bl.h.a();
        bl.i.a();
        bl.j.a();
        bl.k.a();
        bl.u.a();
        bl.p.a();
        bl.m.a();
        bl.q.a();
        bl.r.a();
        bl.s.a();
        bl.l.a();
        if (bl.a != null) {
            for (int l = 0; l < bl.a.a(); ++l) {
                ((aA)bl.a.a(l)).dispose();
            }
        }
        if (bl.t != null) {
            for (int n = 0; n < bl.t.a(); ++n) {
                ((d)bl.t.a(n)).dispose();
            }
        }
        bl.a.a();
        bl.t.a();
        final Enumeration<Dialog> elements = bl.a.elements();
        while (elements.hasMoreElements()) {
            final Dialog nextElement;
            if ((nextElement = elements.nextElement()) instanceof Dialog) {
                nextElement.dispose();
            }
        }
        if (bl.b != null) {
            bl.b.clear();
        }
        bl.d();
        if (bl.a != null) {
            final Frame a2 = bl.a.a();
            bl.a.a();
            bl.a = null;
            if (bl.a != null) {
                bl.a.setVisible(false);
            }
            bl.a = null;
            if (a2 != null) {
                a2.dispose();
            }
        }
        try {
            Thread.sleep(500L);
        }
        catch (InterruptedException ex4) {}
        if (bl.a != null) {
            try {
                bl.a.close();
                bl.a.close();
                bl.a.close();
            }
            catch (Exception ex5) {}
        }
        bl.a = null;
        bl.a = null;
        bl.a = null;
        bl.h = true;
    }
    
    public void a(final URL url, final String s) {
    }
    
    private void a(final String s, final aj aj, final int n, final boolean b, final int n2, final int n3) {
        this.a(s, aj, n, b, false, n2, n3, null);
    }
    
    private void a(final String s, final aj aj, final int n, final boolean b, final byte[] array) {
        this.a(s, aj, n, b, false, 0, 0, array);
    }
    
    private void a(final String s, final aj aj, final int n, final boolean b, final boolean b2, final int n2, final int n3, final byte[] array) {
        if (super.a != null) {
            int g;
            if ((g = aj.g) == super.g) {
                g = n;
            }
            final b b3 = (b)super.b.b(aj.a);
            final Object[] a = this.a(s, aj.a(41) ? super.z : super.w);
            final ao ao;
            (ao = new ao((String)a[0], aj, n != -1 && n != -3 && n != -2 && !b, b3, n2, n3)).d = aj.a(41);
            if (ao.d) {
                ao.j = super.C;
            }
            ao.c = (n == -3 || n == -2);
            ao.a = (Vector)a[1];
            ao.a();
            if (aj.g == -999 && aj.a(41) && aj.a(23) && aj.a(24)) {
                if (aj.a(62)) {
                    ao.k = 1;
                }
                else if (aj.a(52)) {
                    ao.k = 2;
                }
                else if (aj.a(5) && aj.a(6)) {
                    ao.k = 6;
                }
                else if (aj.a(24)) {
                    ao.k = 3;
                }
                else if (aj.a(59)) {
                    ao.k = 4;
                }
                else if (aj.a(61)) {
                    ao.k = 5;
                }
                else {
                    ao.k = 0;
                }
            }
            ao.k = aj.e;
            final F f;
            if (aj.e != 0 && (f = (F)super.q.b(aj.e)) != null) {
                ao.b = f.a;
            }
            if (aj instanceof au) {
                if (((au)aj).b != null && !aj.a(0)) {
                    ao.c = ((au)aj).b;
                    ao.e = true;
                }
            }
            else if (aj.a != null && !aj.a(0)) {
                ao.c = aj.a;
                ao.e = true;
            }
            if (ao.b) {
                final aq aq;
                if ((aq = (aq)super.a.b(g)) != null) {
                    aq.a(ao);
                    return;
                }
                if (super.l) {
                    this.a(ao, aj);
                    return;
                }
                ao.d = false;
                super.a.a(ao);
            }
            else {
                ao.a = aj.a;
                if (n == -3 || n == -2) {
                    return;
                }
                super.a.a(ao);
            }
        }
    }
    
    protected final void a(final au au, final int n, final long n2, final int n3) {
        final int b = au.b;
        final ai ai = (ai)super.d.b(n);
        final ai ai2 = (ai)super.d.b(au.b);
        if (au.g == super.g) {
            if (ai2 != null) {
                ai2.a = false;
            }
            ai.a = true;
            super.b = n;
            if (super.a != null) {
                if (!super.i) {
                    this.a(false);
                }
                super.a.a(ai);
                if (!super.a.isVisible()) {
                    super.a.setVisible(true);
                }
                super.a.a().validate();
            }
        }
        if (ai != null) {
            ai.a = this.a(ai.g);
            if (ai2 != null && ai != null) {
                if (ai2.g != ai.g && (!au.a(23) || this.a(24)) && (this.a(52) || !au.a(18) || au.g == super.g)) {
                    final ai ai3 = ai;
                    ++ai3.a;
                }
            }
            else if (ai2 == null && ai != null && (!au.a(23) || this.a(24)) && (this.a(52) || !au.a(18) || au.g == super.g)) {
                final ai ai4 = ai;
                ++ai4.a;
            }
            if (super.a != null) {
                super.a.b(ai);
            }
        }
        if (ai2 != null) {
            ai2.a = this.a(ai2.g);
            if (ai2 != null && ai != null && ai2.g != ai.g && (!au.a(23) || this.a(24)) && (this.a(52) || !au.a(18) || au.g == super.g)) {
                final ai ai5 = ai2;
                --ai5.a;
            }
            if (super.a != null) {
                super.a.b(ai2);
            }
        }
        if (n == super.b && au.b != n) {
            if (ai != null && !ai.a(57) && (!au.a(23) || this.a(24)) && (this.a(52) || !au.a(18) || au.g == super.g)) {
                final ao ao;
                (ao = new ao(ak.a(ak.a(51), new String[] { this.a(ai.c) }), au, (b)super.b.b(au.a))).k = au.e;
                final F f;
                if (au.e != 0 && (f = (F)super.q.b(au.e)) != null) {
                    ao.b = f.a;
                }
                if (au.b != null && !au.a(0)) {
                    ao.c = au.b;
                    ao.e = true;
                }
                if (super.a != null) {
                    super.a.a(ao);
                }
            }
            if (ai.a != null && au.g == super.g) {
                final ao ao2;
                (ao2 = new ao(ai.a, ai.c, null, -999)).h = ai.b;
                ao2.f = true;
                ao2.i = ai.c;
                ao2.j = ai.d;
                if (super.a != null) {
                    super.a.a(ao2);
                }
            }
        }
        else if (au.b == super.b && n != super.b && ai2 != null && ai != null && !ai2.a(57) && (!au.a(23) || this.a(24)) && (this.a(52) || !au.a(18) || au.g == super.g)) {
            final ao ao3;
            (ao3 = new ao(ak.a(ak.a(52), new String[] { this.a(ai.c) }), au, (b)super.b.b(au.a))).k = au.e;
            final F f2;
            if (au.e != 0 && (f2 = (F)super.q.b(au.e)) != null) {
                ao3.b = f2.a;
            }
            if (au.b != null && !au.a(0)) {
                ao3.c = au.b;
                ao3.e = true;
            }
            if (super.a != null) {
                super.a.a(ao3);
            }
        }
        au.b = n;
        if (super.a != null) {
            if ((super.i || n == super.b) && (!super.j || bh.a(au, super.a.a()))) {
                super.a.a(au, true);
                return;
            }
            if (!super.i && b == super.b) {
                super.a.a(au);
            }
        }
    }
    
    private void n(final m m) {
        for (int i = 0; i < m.b; ++i) {
            aj aj;
            if ((aj = ((m.a(i, 0) == -1) ? null : ((aj)super.c.b(m.a(i, 0))))) == null && m.a(0) != 0L) {
                (aj = new aj(-999, m.a(0, 1))).c = m.a(0, 2);
                aj.a = m.a(0, 1);
                aj.e = m.a(0, 5);
                if (m.a(0, 3) != null) {
                    aj.a = m.a(0, 3);
                    aj.a = this.b("superImages/" + m.a(0, 3), true, 35);
                }
                aj.a = m.a(0);
            }
            else if (aj == null && m.a(0) == 0L) {
                (aj = new aj(-999, "Guest")).a = super.k;
            }
            if (aj != null) {
                int n;
                if ((n = aj.g) == super.g) {
                    n = m.e;
                }
                if (!aj.b) {
                    switch (m.e) {
                        case -3:
                        case -2:
                        case -1: {
                            if (!aj.a) {
                                break;
                            }
                            break;
                        }
                        default: {
                            super.a.a(n);
                            break;
                        }
                    }
                    if (m.a != null) {
                        this.a(m.a(i, 0), aj, m.e, true, m.a);
                    }
                    else {
                        this.a(m.a(i, 0), aj, m.e, true, m.a(i, 3), m.a(i, 4));
                    }
                }
            }
        }
    }
    
    private void o(final m m) {
        try {
            super.D = (m.a(-1, 4) ? 1 : 0);
            super.E = (int)(m.a(-1) & 0xFL);
            for (int i = 0; i < m.b; ++i) {
                final int a = m.a(i, 0);
                aG ag = (aG)super.s.b(a);
                if (m.a(i, 63)) {
                    if (ag != null) {
                        super.s.b(a);
                    }
                }
                else if (!m.a(i, 8)) {
                    if (ag == null) {
                        super.s.a(ag = new aG(a, m.a(i, 0)));
                    }
                    else {
                        ag.c = m.a(i, 0);
                    }
                    ag.a = m.a(i);
                    ag.a = m.a(i, 1);
                    ag.b = m.a(i, 2);
                    final int a2;
                    ag.a = ((((a2 = m.a(i, 3)) & 0xFFFFFF) == 0x0 && ag.a(4)) ? null : new Color(a2));
                    final int a3;
                    ag.b = ((((a3 = m.a(i, 4)) & 0xFFFFFF) == 0x0 && ag.a(5)) ? null : new Color(a3));
                    ag.a = m.a(i, 1);
                }
            }
        }
        finally {
            if (super.a != null) {
                super.a.b();
            }
        }
    }
    
    private void p(final m m) {
        try {
            for (int i = 0; i < m.b; ++i) {
                final int a = m.a(i, 0);
                br br = (br)super.r.b(a);
                if (m.a(i, 63)) {
                    if (br != null) {
                        super.r.b(a);
                    }
                }
                else {
                    if (br == null) {
                        br = new br(a, m.a(i, 0));
                        super.r.a(br);
                    }
                    else {
                        br.c = m.a(i, 0);
                    }
                    br.a = m.a(i, 1);
                    br.a = m.a(i);
                }
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
        if (super.a != null) {
            super.a.c();
            for (int j = 0; j < super.a.a(); ++j) {
                final aA aa;
                if ((aa = (aA)super.a.a(j)).a.a(14) && aa.a != null) {
                    aa.a.a(1);
                    aa.a.a = aa.a.a(4);
                    aa.a.b = true;
                }
            }
        }
    }
    
    private void q(final m m) {
        if (super.a != null) {
            for (int i = 0; i < m.b; ++i) {
                final ao ao;
                (ao = new ao(m.a(0, 1) ? m.a(i, 1) : this.a(m.a(i, 1)), m.a(0, 1) ? m.a(i, 0) : this.a(m.a(i, 0)), (b)super.b.b(m.a(i, 0)), -999)).h = m.a(i, 1);
                ao.f = true;
                ao.i = m.a(i, 2);
                ao.j = m.a(i, 3);
                ao.g = true;
                super.a.a(ao);
            }
        }
    }
    
    private void r(final m m) {
        for (int i = 0; i < m.b; ++i) {
            final aj aj;
            if ((aj = (aj)super.c.b(m.a(i, 0))) != null) {
                int n;
                if ((n = aj.g) == super.g) {
                    n = m.e;
                }
                if (m.e != super.g && m.a(0, 20)) {
                    return;
                }
                if (!aj.b) {
                    switch (m.e) {
                        case -3:
                        case -2:
                        case -1: {
                            if (!aj.a) {
                                break;
                            }
                            break;
                        }
                        default: {
                            super.a.a(n);
                            break;
                        }
                    }
                    if (m.a != null) {
                        final String a = m.a(i, 0);
                        final aj aj2 = aj;
                        final int e = m.e;
                        final boolean b = false;
                        m.a(0, 20);
                        this.a(a, aj2, e, b, m.a);
                    }
                    else {
                        this.a(m.a(i, 0), aj, m.e, false, m.a(i, 1), m.a(i, 2));
                    }
                }
            }
        }
    }
    
    private void s(final m m) {
        for (int i = 0; i < m.b; ++i) {
            final aj aj;
            if ((aj = (aj)super.c.b(m.a(i, 0))) != null) {
                new am(super.a.a(), this, aj, m, i);
            }
        }
    }
    
    private void t(final m m) {
        m.a(-1);
        try {
            for (int i = 0; i < m.b; ++i) {
                final int a = m.a(i, 0);
                c c = (c)super.e.b(a);
                if (m.a(i, 63)) {
                    if (c != null) {
                        super.e.b(a);
                        if (super.a != null) {
                            super.a.a(a);
                        }
                    }
                }
                else {
                    if (c == null) {
                        c = new c(a, m.a(i, 0));
                        super.e.a(c);
                    }
                    else {
                        c.c = m.a(i, 0);
                    }
                    m.a(i, 1);
                    m.a(i, 2);
                    c.b = m.a(i, 1);
                    c.a = m.a(i, 2);
                    m.a(i, 3);
                    c.a = m.a(i);
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
    
    private void u(final m m) {
        for (int b = m.b, i = 0; i < b; ++i) {
            final K k;
            (k = new K(m.a(i, 0), m.a(i, 0))).a = m.a(i);
            k.a = m.a(i, 1);
            if (!k.a(63)) {
                k.a = this.b("emoticons/" + k.a, true, 50);
            }
            K.a(k);
        }
    }
    
    private void v(final m m) {
        try {
            for (int i = 0; i < m.b; ++i) {
                final int a = m.a(i, 0);
                b b = (b)super.b.b(a);
                if (m.a(i, 63)) {
                    if (b != null) {
                        super.b.b(a);
                    }
                }
                else {
                    if (b == null) {
                        b = new b(a, m.a(i, 0));
                        super.b.a(b);
                    }
                    else {
                        b.c = m.a(i, 0);
                    }
                    b.a = this.b("userIcons/" + b.c, true, 40);
                    b.a = m.a(i, 1);
                    b.a = m.a(i);
                    if (m.a(i, 62)) {
                        super.k = a;
                    }
                }
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
    
    private void w(final m m) {
        try {
            for (int i = 0; i < m.b; ++i) {
                final int a = m.a(i, 0);
                F f = (F)super.q.b(a);
                if (m.a(i, 63)) {
                    if (f != null) {
                        super.q.b(a);
                    }
                }
                else {
                    if (f == null) {
                        f = new F(a, m.a(i, 0));
                        super.q.a(f);
                    }
                    else {
                        f.c = m.a(i, 0);
                    }
                    if (a == 1) {
                        f.a = this.b(f.c, false, 13);
                    }
                    else {
                        f.a = this.b("stars/" + f.c, true, 13);
                    }
                    f.a = m.a(i);
                }
            }
            if (!super.q.a(1)) {
                final F f2 = new F(1, "star_1.gif");
                super.q.a(f2);
                f2.a = this.b(f2.c, false, 13);
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
    
    private void x(final m m) {
        try {
            for (int i = 0; i < m.b; ++i) {
                final int a = m.a(i, 0);
                au au = (au)super.c.b(a);
                final int n = i;
                if (n < -1 || n >= m.b) {
                    throw new ArrayIndexOutOfBoundsException("itemNumber is out-of-bounds: " + n);
                }
                if (((new long[] { m.a[(n << 1) + 2], m.a[(n << 1) + 3] })[0] & 1L << 63) != 0x0L) {
                    if (au != null) {
                        final ai ai;
                        if ((ai = (ai)super.d.b(m.a(i, 2))) != null) {
                            if ((!au.a(23) || this.a(24)) && (this.a(52) || !au.a(18) || au.g == super.g)) {
                                final ai ai2 = ai;
                                --ai2.a;
                            }
                            if (super.a != null) {
                                super.a.b(ai);
                            }
                        }
                        super.c.b(a);
                        if (super.a != null) {
                            super.a.a(au);
                        }
                        final aq aq;
                        if ((aq = (aq)super.a.b(au.g)) != null) {
                            aq.dispose();
                        }
                    }
                }
                else {
                    final String a2 = this.a(m.a(i, 0));
                    if (au == null) {
                        if ((!(au = new au(a, a2)).a(23) || this.a(24)) && (this.a(52) || !au.a(18) || au.g == super.g)) {
                            super.c.a(au);
                        }
                    }
                    else {
                        au.c = a2;
                    }
                    au.a = m.a(i, 1);
                    au.a = (b)super.b.b(au.a);
                    if (au.b == -999 || m.a(i, 2) != -999) {
                        au.b = m.a(i, 2);
                    }
                    au.c = m.a(i, 3);
                    au.e = m.a(i, 4);
                    au.d = m.a(i, 5);
                    au.f = m.a(i, 6);
                    au.i = m.a(i, 7);
                    au.a = (F)super.q.b(m.a(i, 4));
                    au.a(m.a(i));
                    au.a();
                    final String a3;
                    if ((a3 = m.a(i, 3)) != null) {
                        au.a = a3;
                        if (au.b == null) {
                            au.b = this.b("superImages/" + a3, true, 35);
                        }
                    }
                    else if (au.b != null) {
                        au.b.flush();
                        au.b = null;
                    }
                    if (a == super.g) {
                        super.c = au.c;
                        if (au.a != -999) {
                            super.m = au.a;
                            super.d = au.d;
                            super.e = au.e;
                            super.c = au.c;
                            super.f = au.f;
                            super.a = au.a;
                        }
                    }
                    if (super.a != null) {
                        super.a.a(au, false);
                    }
                }
            }
        }
        finally {
            throw loadexception(java.lang.Throwable.class);
        }
    }
    
    private void a(final d d) {
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
        d.reshape(n4 + 2, n3 + 10, n, n2);
    }
    
    private void y(final m m) {
        if (super.t == null) {
            return;
        }
        d d;
        if ((d = (d)super.t.b(m.a(0, 0))) == null && m.f == super.g && (d = (d)super.t.b(0)) != null) {
            super.t.a((Object)d);
            super.t.a(d, m.a(0, 0));
            d.a = m.a(0, 0);
            this.a(null, 0, 0, m.a(0, 0));
        }
        if (d == null) {
            d = new d(this, m.a(0, 0));
            this.a(d);
            super.t.a(d, m.a(0, 0));
            this.a(null, 0, 0, m.a(0, 0));
        }
        d.setVisible(true);
        final boolean b = m.b == 1;
        for (int i = 0; i < m.b; ++i) {
            if (m.a(i, 1)) {
                d.a.a(m.a(i, 1));
            }
            else {
                d.a.a(m.a(i, 1), b);
            }
        }
    }
    
    private void z(final m m) {
        if (super.t == null) {
            return;
        }
        final d d;
        if ((d = (d)super.t.b(m.f)) == null) {
            return;
        }
        for (int i = 0; i < m.b; ++i) {
            if (super.a != null) {
                aj a;
                if ((a = d.a.a(m.a(i, 0))) == null) {
                    (a = new aj(-999, m.a(0, 1))).c = m.a(0, 2);
                    a.a = m.a(0, 1);
                    a.e = m.a(0, 6);
                    if (m.a(0, 3) != null) {
                        a.a = m.a(0, 3);
                        a.a = this.b("superImages/" + m.a(0, 3), true, 35);
                    }
                    a.a = m.a(0);
                }
                final b b = (b)super.b.b(a.a);
                final Object[] a2 = this.a(m.a(i, 0), a.a(41) ? super.z : super.w);
                final ao ao;
                (ao = new ao((String)a2[0], a, false, b, 0, 0)).d = a.a(41);
                if (ao.d) {
                    ao.j = super.C;
                }
                ao.a = (Vector)a2[1];
                ao.a();
                if (a.g == -999 && a.a(41) && a.a(23) && a.a(24)) {
                    if (a.a(62)) {
                        ao.k = 1;
                    }
                    else if (a.a(52)) {
                        ao.k = 2;
                    }
                    else if (a.a(5) && a.a(6)) {
                        ao.k = 6;
                    }
                    else if (a.a(24)) {
                        ao.k = 3;
                    }
                    else if (a.a(59)) {
                        ao.k = 4;
                    }
                    else if (a.a(61)) {
                        ao.k = 5;
                    }
                    else {
                        ao.k = 0;
                    }
                }
                ao.k = a.e;
                final F f;
                if (a.e != 0 && (f = (F)super.q.b(a.e)) != null) {
                    ao.b = f.a;
                }
                if (a instanceof au) {
                    if (((aH)a).b != null && !a.a(0)) {
                        ao.c = ((aH)a).b;
                        ao.e = true;
                    }
                }
                else if (a.a != null && !a.a(0)) {
                    ao.c = a.a;
                    ao.e = true;
                }
                ao.a = a.a;
                d.a(ao);
            }
        }
    }
    
    public final void a(final ad ad, final String s) {
        int n = 0;
        final m m = this.b.get(ad);
        if (ak.a(3).equals(s)) {
            n = 1;
        }
        else if (ak.a(2).equals(s)) {
            n = 0;
        }
        if (m.a == 2125857) {
            if (n == 0 && super.t.b(m.a(0, 0)) == null) {
                final d d;
                (d = new d(this, m.a(0, 0))).setTitle(d.getTitle() + ": " + m.a(0, 1));
                this.a(d);
                super.t.a(d, m.a(0, 0));
            }
            final m i;
            (i = new m(2125858, 1)).e = m.a(0, 2);
            i.d = -1;
            i.a(0, 0, m.a(0, 0));
            i.a(0, 1, super.g);
            i.a(0, 2, n);
            this.m(i);
        }
        this.b.remove(ad);
    }
    
    public final boolean a(final Event event) {
        if (event.target == super.a) {
            final d d;
            (d = new d(this, -999)).setTitle(d.getTitle() + ": " + super.c);
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
            d.reshape(n4 + 2, n3 + 10, n, n2);
            super.t.a(d, 0);
            if (!d.isShowing()) {
                d.setVisible(true);
            }
            return true;
        }
        return super.a(event);
    }
    
    public final void a(final ao ao, final aj aj) {
        final int g = aj.g;
        if (((O)super.d.b(aj.b)).a(61) && aj.a(59) && !super.k) {
            return;
        }
        aA aa;
        if ((aa = (aA)super.a.b(g)) == null) {
            aa = new aq(this, g);
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
            aa.reshape(n4 + 2, n3 + 10, n, n2);
            super.a.a(aa, g);
        }
        if (ao != null) {
            aa.a(ao);
        }
        aa.setVisible(true);
    }
    
    private void e(final int n) {
        final m m;
        (m = new m(67334, 1)).a(0, 0, super.g);
        m.a(0, 1, n);
        m.a(0, super.c);
        m.e = -1;
        m.d = -1;
        this.m(m);
    }
    
    public final void d(final int n) {
        if (super.a.a() != null) {
            super.a.a().setCursor(3);
        }
        if (this.a == null) {
            this.a = new aB(super.a.a(), this);
        }
        final aB a;
        if ((a = this.a).a) {
            ((aR)a.a).a(0);
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
    
    public bl() {
        new Hashtable();
        this.a = new Hashtable();
        this.b = new Hashtable();
    }
    
    static {
        new Hashtable();
    }
}
