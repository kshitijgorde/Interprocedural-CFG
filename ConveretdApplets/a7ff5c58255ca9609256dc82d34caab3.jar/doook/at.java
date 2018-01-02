// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.MenuItem;
import java.awt.Component;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.IOException;
import java.net.NoRouteToHostException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.Random;
import java.net.InetAddress;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Image;
import java.awt.Frame;

public abstract class at extends as
{
    private i a;
    
    protected void k() {
        super.E = super.D;
        super.D = "00000000-0000-0000-0000-000000000000";
        as.a = null;
        super.d.g();
        super.e.g();
        super.f.g();
        super.g.g();
        super.h.g();
        super.i.g();
        super.j.g();
        super.l.g();
        super.m.g();
        super.n.g();
        super.s.g();
        super.t.g();
        if (super.c != null) {
            for (int i = 0; i < super.c.b(); ++i) {
                ((ai)super.c.a(i)).dispose();
            }
        }
        this.g();
        if (super.a != null) {
            final Frame a = super.a.a();
            super.a.f();
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
        super.A = true;
    }
    
    public void b(final aJ aj) {
        switch (aj.b()) {
            case 67585: {
                this.f(aj);
                break;
            }
            case 65794: {
                this.u(aj);
                break;
            }
            case 66049: {
                this.c(aj);
                break;
            }
            case 66305: {
                this.y(aj);
                break;
            }
            case 66308: {
                this.v(aj);
                break;
            }
            case 66306: {
                this.x(aj);
                break;
            }
            case 66307: {
                this.h(aj);
                break;
            }
            case 66561: {
                this.g(aj);
                break;
            }
            case 66816:
            case 50400771: {
                this.d(aj);
                break;
            }
            case 66817: {
                this.i(aj);
                break;
            }
            case 67073: {
                this.z(aj);
                break;
            }
            case 67074: {
                this.w(aj);
                break;
            }
            case 67329: {
                this.A(aj);
                break;
            }
            case 67330: {
                this.l(aj);
                break;
            }
            case 67331: {
                this.C(aj);
                break;
            }
            case 67333: {
                this.j(aj);
                break;
            }
            case 67334: {
                if (aj.b(0, 0) == this.e()) {
                    this.a(aj.a(0));
                    super.u = aj.b(0, 3);
                    super.d = aj.b(0, 6);
                }
                this.E(aj);
                break;
            }
            case 67584: {
                this.s(aj);
                break;
            }
            case 67586: {
                this.t(aj);
                break;
            }
            case 67843: {
                this.D(aj);
                break;
            }
            case 68608: {
                this.e(aj);
                break;
            }
            case 67338: {
                this.k(aj);
                break;
            }
            case 67341: {
                this.m(aj);
                break;
            }
            case 33621775: {
                this.B(aj);
                break;
            }
            case 263168: {
                doook.F.a(new Object[] { (aj.a(0, 0) == null) ? "" : aj.a(0, 0) });
                break;
            }
            case 67342: {
                this.o(aj);
                break;
            }
            case 264192: {
                this.p(aj);
                break;
            }
            case 327680: {
                this.F(aj);
                break;
            }
            case 327936: {
                this.r(aj);
                break;
            }
        }
    }
    
    public boolean i() {
        return super.x;
    }
    
    public Image a(final String s, final boolean b, final int n) {
        return this.a(s, b, n, true);
    }
    
    public Image a(final String s, final boolean b, final int n, final boolean b2) {
        try {
            URL url;
            if (b || as.a(s)) {
                url = new URL(super.c, "Resources/" + super.r + "/" + s);
            }
            else {
                url = new URL(super.c, "Resources/" + s);
            }
            final Image a = this.a(url);
            if (a != null) {
                super.b.addImage(a, b2 ? n : super.q);
                super.b.statusID(b2 ? n : super.q, true);
                if (!b2) {
                    ++super.q;
                }
            }
            return a;
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    public abstract Image a(final URL p0);
    
    public void p() {
    }
    
    public AudioClip[] a(final URL url) {
        return null;
    }
    
    public abstract void a(final String p0, final String p1, final r p2, final String p3, final int p4);
    
    public void a(final String s, final String s2, final r r, final URL c, final int n, final String s3) {
        super.ab = 0;
        try {
            this.f();
            super.B = false;
            super.j.g();
            if (bi.Y != null) {
                super.F = bi.Y;
            }
            else {
                super.F = c.getHost();
            }
            InetAddress[] allByName = InetAddress.getAllByName(super.F);
            if (allByName == null || allByName.length == 0) {
                allByName = new InetAddress[] { InetAddress.getByName(super.F) };
            }
            super.d = new String[allByName.length];
            for (int i = 0; i < allByName.length; ++i) {
                super.d[i] = allByName[i].getHostAddress();
            }
            if (super.d.length > 1) {
                final Random random = new Random();
                for (int j = 0; j < super.d.length; ++j) {
                    int n2 = random.nextInt() % super.d.length;
                    if (n2 < 0) {
                        n2 *= -1;
                    }
                    final String s4 = super.d[n2];
                    super.d[n2] = super.d[j];
                    super.d[j] = s4;
                }
            }
            super.c = c;
            this.b(s);
            Label_0271: {
                if (super.a == null) {
                    if (bs.g < 66048) {
                        if (bs.e) {
                            break Label_0271;
                        }
                    }
                    try {
                        super.a = this.a(super.c);
                    }
                    catch (Throwable t) {
                        super.a = null;
                    }
                }
            }
            if (bi.T != null) {
                super.d = new String[] { bi.T };
            }
            if (bi.av != 0) {
                for (int k = 0; k < doook.L.a.length; ++k) {
                    if (bi.av == doook.L.a(k)) {
                        doook.L.l(k);
                        break;
                    }
                }
            }
            if (this.j()) {
                if (super.a != null && super.a.isAlive()) {
                    super.a.suspend();
                }
                if (super.c != null && super.c.isAlive()) {
                    super.c.suspend();
                }
                super.a = new f(this);
                super.c = new Thread(this, "Decoder");
                super.A = false;
                super.c.start();
                int n3 = 0;
                String string;
                try {
                    string = doook.F.a(new Object[] { "encodeURIComponent(document.referrer)" }).toString();
                }
                catch (Exception ex7) {
                    string = "";
                }
                String string2;
                try {
                    string2 = doook.F.a(new Object[] { "encodeURIComponent(navigator.userAgent)" }).toString();
                }
                catch (Exception ex8) {
                    string2 = "";
                }
                String string3 = "undefined";
                try {
                    while (n3 < 10 && string3.equalsIgnoreCase("undefined")) {
                        string3 = doook.F.a(new Object[] { "document.doookID" }).toString();
                        if (string3.equalsIgnoreCase("undefined")) {
                            try {
                                Thread.sleep(100L);
                                ++n3;
                            }
                            catch (InterruptedException ex9) {}
                        }
                    }
                    if (string3.equalsIgnoreCase("undefined")) {
                        string3 = "";
                    }
                }
                catch (Exception ex10) {
                    string3 = "";
                }
                if (string3.equalsIgnoreCase("undefined")) {
                    string3 = "";
                }
                final String string4 = "r=" + string + "&a=" + string2 + "&" + string3;
                final aJ aj = new aJ(65793, 1);
                aj.a(0, 0, n);
                aj.a(0, 1, aI.c(s));
                aj.a(0, 0, s);
                aj.a(0, 2, bi.R);
                aj.a(0, 1, doook.F.a(new Object[] { "__Q['f']" }).toString());
                aj.a(0, 3, s2);
                aj.a(0, 4, string4);
                aj.a(0, 0, r);
                if (!this.f() && super.H != -999) {
                    aj.b(0, 2);
                }
                if (this.a(23)) {
                    aj.b(0, 23);
                }
                if (this.h()) {
                    aj.b(0, 3);
                }
                if (super.y && (!bs.d || bs.t != 1)) {
                    aj.b(0, 21);
                    aj.b(0, 20);
                }
                this.q(aj);
            }
            else {
                System.err.println("acknowledge() failed!!!");
                this.k();
            }
        }
        catch (UnknownHostException ex) {
            this.g();
            new S(super.c, doook.H.a(ar.b("%1 could not locate the specified host. Please make sure you are using the correct host name."), new String[] { bi.Q }), ex, this).setVisible(true);
        }
        catch (InterruptedIOException ex2) {
            this.g();
            new S(super.c, doook.H.a(ar.b("No response was received from the server.  The %1 Server may not be running, or may not be a compatible version. Please contact the administrator in charge of the site."), new String[] { bi.Q }), ex2, this).setVisible(true);
        }
        catch (NoRouteToHostException ex3) {
            this.g();
            new S(super.c, doook.H.a(ar.b("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { bi.Q }), ex3, this).setVisible(true);
        }
        catch (SecurityException ex4) {
            this.g();
            new S(super.c, doook.H.a(ar.b("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { bi.Q }), ex4, this).setVisible(true);
        }
        catch (IOException ex5) {
            ex5.printStackTrace();
            this.g();
            new S(super.c, doook.H.a(ar.b("%1 could not connect to the specified host.  Please verify that the %1 Server is running and that you are using the correct host name."), new String[] { bi.Q }), ex5, this).setVisible(true);
        }
        catch (Exception ex6) {
            ex6.printStackTrace();
            this.g();
            final StringWriter stringWriter = new StringWriter();
            ex6.printStackTrace(new PrintWriter(stringWriter));
            new S(super.c, doook.H.a(ar.b("An unknown error occurred while connecting to the %1 Server.  Please contact the administrator in charge of the site."), new String[] { bi.Q }), stringWriter.toString(), this).setVisible(true);
        }
        super.d = false;
        super.e = false;
        super.b = false;
    }
    
    private void c(final String s) {
        try {
            new Socket(s, 53).close();
        }
        catch (Exception ex) {}
    }
    
    private boolean b(final String s, final int n) {
        int n2;
        if (bs.d && bs.g < 65792 && bs.t == 1) {
            n2 = 2;
        }
        else {
            n2 = 256;
        }
        try {
            super.a = new Socket(s, n);
            super.a = new E(new BufferedOutputStream(super.a.getOutputStream(), 256));
            super.a = new J(new BufferedInputStream(super.a.getInputStream(), n2));
            this.q();
            return this.e();
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    private boolean j() {
        boolean b = false;
        bi.av = 8396;
        for (int aa = 0; !b && aa < super.d.length; ++aa) {
            super.aa = aa;
            try {
                b = this.b(super.d[aa], bi.av);
            }
            catch (Exception ex2) {
                if (aa != super.d.length - 1) {
                    try {
                        Thread.sleep(200L);
                    }
                    catch (InterruptedException ex3) {}
                }
            }
        }
        for (int aa2 = 0; !b && aa2 < super.d.length; ++aa2) {
            super.aa = aa2;
            this.c(super.d[aa2]);
            for (int n = 0; !b && n < doook.L.a.length; ++n) {
                final int a = doook.L.a(n);
                if (a != doook.L.g) {
                    bi.av = a;
                    try {
                        b = this.b(super.d[aa2], a);
                    }
                    catch (Exception ex) {
                        if (aa2 == super.d.length - 1 && n == doook.L.a.length - 1) {
                            throw ex;
                        }
                        try {
                            Thread.sleep(200L);
                        }
                        catch (InterruptedException ex4) {}
                    }
                }
            }
        }
        return b;
    }
    
    private void q() {
        try {
            final aL al = new aL();
            final long a = al.a(al.d());
            super.a.writeInt(super.L);
            super.a.writeLong(a);
            super.a.flush();
            final long long1 = super.a.readLong();
            super.a.a(aL.a(a));
            super.a.a(aL.a(long1));
        }
        catch (IOException ex) {}
    }
    
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
    
    public void a(final String s, final F f, final int n, final int n2, final boolean b, final long n3, final int n4, final int n5, final int n6, final boolean b2, final boolean b3, final boolean b4) {
        this.a(s, f, n, n2, b, false, n3, n4, null, n5, n6, b2, b3, b4);
    }
    
    public void a(final String s, final F f, final int n, final int n2, final boolean b, final boolean b2, final long n3, final int n4, final byte[] a, final int ad, final int d, final boolean ap, final boolean aq, final boolean ar) {
        if (super.a != null) {
            int e = f.e();
            if (e == this.e()) {
                e = n;
            }
            f.d();
            final bv bv = new bv(this.a(s, f), f, n != -1 && n != -3 && n != -2 && !b, (af)super.d.b(f.c));
            bv.a(n == -3 || n == -2);
            bv.a(n3, n4);
            if (b2) {
                bv.o = a[0];
            }
            else {
                bv.a = a;
            }
            bv.aD = ad;
            bv.d = d;
            bv.ap = ap;
            bv.aq = aq;
            bv.ar = ar;
            if (bv.z) {
                final X x = (X)super.c.b(e);
                if (x != null) {
                    x.b(bv);
                }
                else if (super.E) {
                    this.a(bv, f);
                }
                else {
                    super.a.b(bv);
                }
            }
            else {
                bv.w = f.j;
                if (n == -3 || n == -2) {
                    this.b(bv);
                }
                else {
                    super.a.b(bv);
                }
            }
        }
    }
    
    protected void r(final aJ aj) {
        super.t.a(true);
        try {
            for (int i = 0; i < aj.g(); ++i) {
                final int b = aj.b(i, 0);
                l l = (l)super.t.b(b);
                if (aj.a(i, 63)) {
                    if (l != null) {
                        super.t.c(b);
                    }
                }
                else {
                    if (l == null) {
                        l = new l(b, aj.a(i, 0));
                        super.t.a(l, b);
                    }
                    else {
                        l.b(aj.a(i, 0));
                    }
                    l.a(aj.a(i));
                    l.description = aj.a(i, 1);
                    l.c = aj.a(i, 2);
                    l.a = aj.b(i, 1);
                    try {
                        if (l.c != null && l.c.length() > 0 && !aX.e.containsKey(l.c)) {
                            final Image a = this.a("stars/" + l.c, true, 15);
                            if (a != null) {
                                aX.e.put(l.c, a);
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
            super.t.f();
        }
    }
    
    protected void s(final aJ aj) {
        this.k(aj.j);
        this.a(aj.a(0));
        super.r = aj.a(0, 0);
        bi.Q = aj.a(0, 1);
        as.c[0] = ar.b("Bass");
        as.c[1] = ar.b("Bell");
        as.c[2] = ar.b("Castanet");
        as.c[3] = ar.b("Chime");
        as.c[4] = ar.b("Conga");
        as.c[5] = ar.b("Cow Bell");
        as.c[6] = ar.b("Double Bell");
        as.c[7] = ar.b("Drum Roll");
        as.c[8] = ar.b("Harp");
        if (!"Admin".equals(super.r)) {
            if (super.b.k()) {
                super.b.q = this.a(super.b.e() + "background.gif", true);
            }
            if (super.b.l()) {
                super.b.r = this.a(super.b.e() + "chatbackground.gif", true);
            }
            if (bi.ae) {
                (super.a = new bj(this, null)).setVisible(false);
                bi.a.setVisible(false);
                bi.b.add(super.a.a());
                ((bj)super.a).validate();
            }
            else {
                super.a = (ak)new ba(this).b();
            }
            this.p();
        }
    }
    
    public void r() {
        if (super.a != null) {
            ((e)super.a).a().c();
        }
    }
    
    protected void t(final aJ aj) {
        super.d = true;
        super.e = true;
        super.t = -999;
        this.p();
        if (super.d.b(super.I) == null) {
            this.b(super.G);
        }
        else {
            this.b(super.I);
        }
        final bn bn = (bn)super.f.b(super.H);
        final bn bn2 = (bn)super.f.b(super.F);
        if (bn == null || (bn.a == null && bn2.a != null)) {
            this.c(bn2);
        }
        else {
            this.c(bn);
        }
        if (super.a != null) {
            ((e)super.a).a().c();
        }
    }
    
    protected void s() {
        if (!doook.I.a) {
            return;
        }
        if (!super.d && !super.e) {
            if (super.a != null) {
                ((e)super.a).a().c();
            }
            int t;
            if (super.f.b(super.H) == null) {
                t = super.F;
            }
            else {
                t = super.H;
            }
            super.e = true;
            final bn bn = (bn)super.f.b(t);
            if (bn != null && bn.c && bn.a != null) {
                super.t = -999;
                new d(super.a.a(), this, bn).setVisible(true);
            }
            super.t = t;
        }
    }
    
    protected void a(final a a, final int n, final long n2, final int n3) {
        final int t = a.t;
        final bn bn = (bn)super.f.b(n);
        final bn bn2 = (bn)super.f.b(a.t);
        this.a(26);
        this.s();
        if (a.e() == this.e()) {
            if (bn2 != null) {
                bn2.a = false;
            }
            bn.a = true;
            super.t = n;
            if (super.a != null) {
                if (!super.B) {
                    this.b(false);
                }
                super.a.a(bn);
                if (!super.a.isVisible()) {
                    super.a.setVisible(true);
                }
                super.a.a().validate();
            }
        }
        if (bn != null) {
            bn.g = this.e(bn.e());
            if (bn2 != null && bn != null) {
                if (bn2.e() != bn.e() && (!a.a(23) || this.a(24))) {
                    final bn bn3 = bn;
                    ++bn3.g;
                }
            }
            else if (bn2 == null && bn != null && (!a.a(23) || this.a(24))) {
                final bn bn4 = bn;
                ++bn4.g;
            }
            if (super.a != null) {
                super.a.b(bn);
            }
        }
        if (bn2 != null) {
            bn2.g = this.e(bn2.e());
            if (bn2 != null && bn != null && bn2.e() != bn.e() && (!a.a(23) || this.a(24))) {
                final bn bn5 = bn2;
                --bn5.g;
            }
            if (super.a != null) {
                super.a.b(bn2);
            }
        }
        if (n == super.t && a.t != n) {
            if (bn != null && !bn.a(57) && (!a.a(23) || this.a(24))) {
                final bv bv = new bv(doook.H.a(ar.b("(This user has entered %1)"), new String[] { this.e(bn.d()) }), a, false, (af)super.d.b(a.c));
                bv.a(n2, n3);
                this.a(super.P);
                if (super.a != null) {
                    super.a.b(bv);
                }
            }
        }
        else if (a.t == super.t && n != super.t && bn2 != null && bn != null && !bn2.a(57) && (!a.a(23) || this.a(24))) {
            final bv bv2 = new bv(doook.H.a(ar.b("(This user has moved to %1)"), new String[] { this.e(bn.d()) }), a, false, (af)super.d.b(a.c));
            bv2.a(n2, n3);
            this.a(super.T);
            if (super.a != null) {
                super.a.b(bv2);
            }
        }
        a.t = n;
        if (super.a != null) {
            if ((super.B || n == super.t) && this.a(a.d(), bh.h.getText())) {
                super.a.a(a, true);
            }
            else if (!super.B && t == super.t) {
                super.a.a(a);
            }
        }
    }
    
    protected void u(final aJ aj) {
        this.a(26);
        this.s();
        for (int i = 0; i < aj.g(); ++i) {
            final a a = (a)super.e.b(aj.b(i, 0));
            if (a != null) {
                final bn bn = (bn)super.f.b(a.t);
                final b b = new b(a.e(), a.d());
                b.a = false;
                if (bn != null) {
                    if (!a.a(23) || this.a(24)) {
                        final bn bn2 = bn;
                        --bn2.g;
                    }
                    if (super.a != null) {
                        super.a.b(bn);
                    }
                    if (a.t == super.t && !bn.a(57) && (!a.a(23) || this.a(24))) {
                        final String a2 = aj.a(i, 0);
                        String s;
                        if (a2 == null) {
                            s = doook.H.a(ar.b("(This user has left %1)"), new String[] { bi.Q });
                        }
                        else {
                            s = this.e(a2) + " " + doook.H.a(ar.b("(This user has left %1)"), new String[] { bi.Q });
                        }
                        final bv bv = new bv(s, a, false, (af)super.d.b(a.c));
                        bv.a(aj.c(), aj.j());
                        this.a(super.T);
                        if (super.a != null) {
                            super.a.b(bv);
                        }
                    }
                }
                if (super.a != null) {
                    super.a.a(a);
                    ((e)super.a).a(b, false, false);
                }
                final X x = (X)super.c.b(a.e());
                if (x != null) {
                    x.dispose();
                }
                super.e.a(true);
                try {
                    super.e.b(a);
                }
                finally {
                    super.e.f();
                }
            }
        }
    }
    
    protected void v(final aJ aj) {
        for (int i = 0; i < aj.g(); ++i) {
            if (aj.b() != null) {
                this.b(aj.b());
            }
            F f = (F)super.e.b(aj.b(i, 0));
            if (f == null && this.h() && aj.a(0, 3)) {
                f = new F(-999, aj.a(0, 1));
                f.c = aj.b(0, 1);
            }
            if (f == null) {
                f = new F(-999, "Guest");
                f.c = super.G;
            }
            if (f != null) {
                int n = f.e();
                if (n == this.e()) {
                    n = aj.j;
                }
                if (!f.h) {
                    switch (aj.j) {
                        case -3:
                        case -2:
                        case -1: {
                            if (f.j) {
                                this.a(super.R);
                                break;
                            }
                            this.a(super.Q);
                            break;
                        }
                        default: {
                            if (super.c.b(n)) {
                                this.a(super.Q);
                                break;
                            }
                            this.a(super.S);
                            break;
                        }
                    }
                    this.a(aj.a(i, 0), f, aj.j, aj.C, true, aj.c(), aj.j(), aj.b(i, 2), aj.b(i, 3), aj.a(i, 37), aj.a(i, 38), aj.a(i, 39));
                }
            }
        }
    }
    
    protected void w(final aJ aj) {
        final F f = (F)super.e.b(aj.b(0, 0));
        if (f != null) {
            final aJ aj2 = new aJ(67073, 1);
            aj2.j = f.e();
            aj2.a(0, 0, this.e());
            if (!super.F || f.a(33)) {
                aj2.a(0, 1, super.M);
                if (super.v != null && super.v.length() > 0) {
                    aj2.a(0, 0, super.v);
                }
                if (super.z != null && super.z.length() > 0) {
                    aj2.a(0, 1, super.z);
                }
                if (super.w != null && super.w.length() > 0) {
                    aj2.a(0, 2, super.w);
                }
                if (super.x != null && super.x.length() > 0) {
                    aj2.a(0, 3, super.x);
                }
                if (super.N != -999) {
                    aj2.b(0, super.N);
                }
            }
            this.q(aj2);
        }
    }
    
    protected void x(final aJ aj) {
        this.a(26);
        if (super.a != null) {
            for (int i = 0; i < aj.g(); ++i) {
                final bv bv = new bv(this.e(aj.a(i, 1)), this.e(aj.a(i, 0)), false, (af)super.d.b(aj.b(i, 0)), -999, false, false);
                bv.aC = aj.b(i, 1);
                bv.c = aj.a(i, 2);
                bv.a = aj.a(i, 3);
                bv.aD = aj.b(i, 6);
                bv.d = aj.b(i, 7);
                bv.g = aj.a(i, 31);
                bv.ao = aj.a(i, 36);
                bv.ap = aj.a(i, 37);
                bv.aq = aj.a(i, 38);
                bv.ar = aj.a(i, 39);
                bv.a(aj.c(), aj.j());
                super.a.b(bv);
                if (aj.j == this.e()) {
                    this.a(super.S);
                }
                else {
                    this.a(super.Q);
                }
            }
        }
    }
    
    protected void y(final aJ aj) {
        this.s();
        for (int i = 0; i < aj.g(); ++i) {
            F f = (F)super.e.b(aj.b(i, 0));
            if (f == null && this.h() && aj.a(0, 3)) {
                f = new F(-999, aj.a(0, 1));
                f.c = aj.b(0, 1);
            }
            if (f != null) {
                int n = f.e();
                if (n == this.e()) {
                    n = aj.j;
                }
                if (aj.j != this.e() && aj.a(0, 20)) {
                    return;
                }
                if (!f.h) {
                    if (!aj.v) {
                        switch (aj.j) {
                            case -3:
                            case -2:
                            case -1: {
                                if (f.j) {
                                    this.a(super.R);
                                    break;
                                }
                                this.a(super.Q);
                                break;
                            }
                            default: {
                                if (super.c.b(n)) {
                                    this.a(super.Q);
                                    break;
                                }
                                this.a(super.S);
                                break;
                            }
                        }
                    }
                    this.a(aj.a(i, 0), f, aj.j, aj.C, false, aj.c(), aj.j(), aj.b(i, 2), aj.b(i, 3), aj.a(i, 37), aj.a(i, 38), aj.a(i, 39));
                }
            }
        }
    }
    
    protected void z(final aJ aj) {
        for (int i = 0; i < aj.g(); ++i) {
            final F f = (F)super.e.b(aj.b(i, 0));
            if (f != null) {
                if (super.I) {
                    final String a = aj.a(i, 2);
                    if (a != null) {
                        try {
                            this.a(new URL(a), "_blank");
                        }
                        catch (MalformedURLException ex) {}
                    }
                }
                else {
                    new Q(super.a.a(), this, f, aj, i);
                }
            }
        }
    }
    
    protected void A(final aJ aj) {
        ((aT)(super.g = aj.a(-1))).a(true);
        try {
            for (int i = 0; i < aj.g(); ++i) {
                final int b = aj.b(i, 0);
                aj aj2 = (aj)super.g.b(b);
                if (aj.a(i, 63)) {
                    if (aj2 != null) {
                        super.g.c(b);
                    }
                }
                else {
                    if (aj2 == null) {
                        aj2 = new aj(b, aj.a(i, 0));
                        super.g.a(aj2);
                    }
                    else {
                        aj2.b(aj.a(i, 0));
                    }
                    aj2.h = aj.b(i, 1);
                    aj2.i = aj.b(i, 2);
                    aj2.o = aj.a(i, 1);
                    aj2.m = aj.a(i, 2);
                    aj2.b = aj.a(i, 3);
                    aj2.a(aj.a(i));
                    aj2.e = this.a("banners/" + aj2.m, true, 30);
                    if (super.a != null) {
                        ((e)super.a).a(aj2);
                    }
                }
            }
        }
        finally {
            super.g.f();
        }
        this.r();
    }
    
    protected void B(final aJ aj) {
        for (int g = aj.g(), i = 0; i < g; ++i) {
            final U u = new U(aj.b(i, 0), aj.a(i, 0));
            u.a(aj.a(i));
            u.b = aj.a(i, 1);
            if (!u.a(63)) {
                u.b = this.a("emoticons/" + u.b, true, 50);
            }
            doook.U.a(u);
        }
    }
    
    protected void C(final aJ aj) {
        aX.a[0] = this.a("status/status_1.gif", false, 16);
        aX.a[1] = this.a("status/status_2.gif", false, 16);
        aX.a[2] = this.a("status/status_3.gif", false, 16);
        aX.a[3] = this.a("status/status_4.gif", false, 16);
        super.d.a(true);
        try {
            for (int i = 0; i < aj.g(); ++i) {
                final int b = aj.b(i, 0);
                af af = (af)super.d.b(b);
                if (aj.a(i, 63)) {
                    if (af != null) {
                        super.d.c(b);
                    }
                }
                else {
                    if (af == null) {
                        af = new af(b, aj.a(i, 0));
                        super.d.a(af);
                    }
                    else {
                        af.b(aj.a(i, 0));
                    }
                    af.a = this.a("userIcons/" + af.d(), true, 40);
                    af.n = aj.a(i, 1);
                    af.a(aj.a(i));
                    if (aj.a(i, 62)) {
                        super.G = b;
                    }
                }
            }
        }
        finally {
            super.d.f();
        }
    }
    
    protected void D(final aJ aj) {
        super.B = aj.a(0, 0);
        super.C = aj.a(0, 1);
        super.d = aj.a(0);
        if (super.U != aj.b(0, 0)) {
            bu.a(this, aj.b(0, 0));
        }
        super.U = aj.b(0, 0);
        super.V = aj.b(0, 1);
        super.W = aj.b(0, 2);
        super.X = aj.b(0, 3);
        super.Y = aj.b(0, 4);
        super.Z = aj.b(0, 5);
        if (super.a != null) {
            ((e)super.a).a(super.B);
            ((e)super.a).a().b((super.V <= 0) ? 7 : super.V);
        }
        doook.F.b[0] = aj.b(0, 6);
        doook.F.b[1] = aj.b(0, 7);
        doook.F.b[2] = aj.b(0, 8);
        doook.F.b[3] = aj.b(0, 9);
        doook.F.o = aj.a(0, 31);
        doook.F.p = aj.a(0, 32);
        as.S = aj.a(0, 33);
        as.T = aj.a(0, 34);
        as.O = aj.a(0, 35);
        as.P = aj.a(0, 36);
        as.Q = aj.a(0, 37);
        as.R = aj.a(0, 38);
        if (!aX.e.containsKey("../../star_system.gif")) {
            aX.m = super.a("Doooknet.gif", false);
            final String[] array = { "system", "admin", "master" };
            for (int i = 0; i < array.length; ++i) {
                try {
                    final Image a = this.a("star_" + array[i] + ".gif", false);
                    if (a != null) {
                        aX.e.put("../../star_" + array[i] + ".gif", a);
                    }
                }
                catch (Exception ex) {}
            }
        }
        if (super.B != null && super.B.length() > 7 && super.a != null) {
            if (as.a == null) {
                as.a = new MenuItem(ar.b("Add To Favorites"));
                super.a.insert(as.a, 0);
                super.a.insertSeparator(1);
            }
        }
        else if (as.a != null) {
            super.a.remove(1);
            super.a.remove(0);
            as.a = null;
        }
    }
    
    protected void E(final aJ aj) {
        this.s();
        super.e.a(true);
        try {
            for (int i = 0; i < aj.g(); ++i) {
                final int b = aj.b(i, 0);
                a a = (a)super.e.b(b);
                if (aj.a(i, 63)) {
                    if (a != null) {
                        final bn bn = (bn)super.f.b(aj.b(i, 2));
                        if (bn != null) {
                            if (!a.a(23) || this.a(24)) {
                                final bn bn2 = bn;
                                --bn2.g;
                            }
                            if (super.a != null) {
                                super.a.b(bn);
                            }
                        }
                        final b b2 = new b(a.e(), a.d());
                        b2.a = false;
                        super.e.c(b);
                        if (super.a != null) {
                            super.a.a(a);
                            ((e)super.a).a(b2, false, false);
                        }
                        final X x = (X)super.c.b(a.e());
                        if (x != null) {
                            x.dispose();
                        }
                    }
                }
                else {
                    String s = this.e(aj.a(i, 0));
                    if (s.length() > 35 && doook.F.o && !aj.a(i, 40)) {
                        s = s.substring(0, 35);
                    }
                    if (a == null) {
                        a = new a(b, s);
                        if (!a.a(23) || this.a(24)) {
                            super.e.a(a);
                        }
                    }
                    else {
                        a.b(s);
                    }
                    a.c = aj.b(i, 1);
                    a.a = (af)super.d.b(a.c);
                    a.t = aj.b(i, 2);
                    a.u = aj.b(i, 3);
                    a.a = aj.b(i, 4);
                    a.b = aj.b(i, 5);
                    a.d = aj.b(i, 6);
                    a.a = aj.a(i, 3);
                    a.c = aj.a(i, 4);
                    if (a.a != null && a.a.length() > 0 && !aX.d.containsKey(a.a)) {
                        try {
                            aX.d.put(a.a, super.a("userImages/" + a.a, true, 17));
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    a.a(aj.a(i));
                    final String a2 = aj.a(i, 1);
                    final String a3 = aj.a(i, 2);
                    if (a2 != null) {
                        a.l = a2;
                    }
                    if (a3 != null) {
                        a.d = a3;
                    }
                    if (b == this.e()) {
                        this.b(a.d());
                        if (a.c != -999) {
                            super.I = a.c;
                        }
                    }
                    final b b3 = new b(a.e(), a.d());
                    b3.a = true;
                    b3.c = a.c;
                    b3.a = (af)super.d.b(a.c);
                    b3.t = a.t;
                    b3.a(a.a());
                    if (super.a != null) {
                        super.a.a(a, false);
                        ((e)super.a).a(b3, false, false);
                    }
                }
            }
        }
        catch (Exception ex2) {
            System.out.println(ex2.getMessage());
        }
        finally {
            super.e.f();
        }
    }
    
    protected void F(final aJ aj) {
        for (int i = 0; i < aj.g(); ++i) {
            final String a = aj.a(i, 0);
            if (a != null && a.length() > 0 && !aX.d.containsKey(a)) {
                final Image a2 = this.a("userImages/" + a, true, 17);
                if (a2 != null) {
                    aX.d.put(a, a2);
                }
            }
        }
    }
    
    public void a(final bv bv, final F f) {
        final int e = f.e();
        ai ai = (ai)super.c.b(e);
        if (ai == null) {
            ai = new X(super.a.a(), this, e);
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int n = screenSize.width / 2 - 20;
            int n2 = screenSize.height / 2 - 20;
            int n3 = 0;
            int n4 = 0;
            switch (super.c.b() % 4) {
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
            ai.reshape(2 + n4, 10 + n3, n, n2);
            super.c.a(ai, e);
        }
        if (bv != null) {
            ai.b(bv);
        }
        ai.setVisible(true);
    }
    
    public void b(final int n) {
        final aJ aj = new aJ(67334, 1);
        aj.a(0, 0, this.e());
        aj.a(0, 1, n);
        aj.a(0, 0, this.d());
        aj.j = -1;
        aj.C = -1;
        this.q(aj);
    }
    
    public void a(final boolean x) {
        super.x = x;
    }
    
    public void c(final int n) {
        if (super.a.a() != null) {
            super.a.a().setCursor(3);
        }
        if (this.a == null) {
            this.a = new i(super.a.a(), this);
        }
        if (n >= 0) {
            this.a.a(n);
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
    
    protected void h(final aJ aj) {
        super.h(aj);
        if (aj.a(-1, 31)) {
            this.n();
        }
    }
}
