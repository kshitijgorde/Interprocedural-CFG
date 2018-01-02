// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Image;
import java.net.MalformedURLException;
import java.awt.Event;
import java.awt.MenuShortcut;
import java.awt.MenuBar;
import java.awt.Frame;
import java.awt.Color;
import java.applet.AppletContext;
import java.util.Vector;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.net.Socket;
import java.io.IOException;
import java.net.NoRouteToHostException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.util.Random;
import java.net.InetAddress;
import java.net.URL;
import java.io.File;
import java.awt.CheckboxMenuItem;
import java.awt.MenuItem;
import java.awt.Menu;
import java.util.Hashtable;

public class cI extends u
{
    private static Hashtable h;
    public static z b;
    private Menu a;
    public static MenuItem j;
    public static MenuItem k;
    public static MenuItem l;
    public static MenuItem m;
    public static MenuItem n;
    public static MenuItem c;
    public static MenuItem d;
    public static MenuItem e;
    public static MenuItem o;
    public static MenuItem p;
    public static MenuItem q;
    public static MenuItem r;
    private CheckboxMenuItem a;
    private CheckboxMenuItem b;
    private CheckboxMenuItem c;
    protected File c;
    protected File d;
    private ci a;
    private cg c;
    protected int ae;
    protected int af;
    protected int n;
    protected int aG;
    protected long t;
    protected long u;
    protected String aa;
    protected String am;
    private bU a;
    public bx b;
    protected int aP;
    protected int aQ;
    protected int aR;
    protected boolean ay;
    protected boolean az;
    private ct a;
    public static String an;
    
    public void a(final String s, final String s2, final a a, final String s3, final int n) {
        String host = "";
        if (super.h && ("".equals(host) || host == null)) {
            host = cI.b.getDocumentBase().getHost();
        }
        if (host.length() == 0) {
            host = "localhost";
        }
        System.out.println("ChatMaster.login()");
        this.a(s, s2, a, new URL("http://" + cI.b.getCodeBase().getHost() + "/DoookNet/"), n, super.h ? host : null);
    }
    
    public void a(final String e, final String s, final a a, final URL b, final int n, final String s2) {
        super.N = 0;
        this.ae = 0;
        this.n = 0;
        this.aG = 0;
        this.af = 0;
        this.u = 0L;
        super.E = e;
        try {
            this.d();
            super.y = false;
            super.h.b();
            super.j.b();
            if (doook.z.O != null) {
                super.B = doook.z.O;
            }
            else {
                super.B = b.getHost();
            }
            final InetAddress[] allByName = InetAddress.getAllByName(super.B);
            super.c = new String[allByName.length];
            for (int i = 0; i < allByName.length; ++i) {
                super.c[i] = allByName[i].getHostAddress();
            }
            if (super.c.length > 1) {
                final Random random = new Random();
                for (int j = 0; j < super.c.length; ++j) {
                    int n2 = random.nextInt() % super.c.length;
                    if (n2 < 0) {
                        n2 *= -1;
                    }
                    final String s3 = super.c[n2];
                    super.c[n2] = super.c[j];
                    super.c[j] = s3;
                }
            }
            super.b = b;
            this.d(e);
            Label_0281: {
                if (super.a == null) {
                    if (doook.f.i < 66048) {
                        if (doook.f.e) {
                            break Label_0281;
                        }
                    }
                    try {
                        super.a = this.a(super.b);
                    }
                    catch (Throwable t) {
                        super.a = null;
                    }
                }
            }
            if (doook.z.J != null) {
                for (int k = 0; k < super.c.length; ++k) {
                    if (doook.z.J == super.c[k]) {
                        final String s4 = super.c[0];
                        super.c[0] = super.c[k];
                        super.c[k] = s4;
                        break;
                    }
                }
            }
            if (doook.z.Y != 0) {
                for (int l = 0; l < cB.e.length; ++l) {
                    if (doook.z.Y == cB.a(l)) {
                        cB.m(l);
                        break;
                    }
                }
            }
            if (this.r()) {
                if (super.a != null && super.a.isAlive()) {
                    super.a.suspend();
                }
                if (super.a != null && super.a.isAlive()) {
                    super.a.suspend();
                }
                super.a = new ak(this);
                super.a = new Thread(this, "Decoder");
                super.x = false;
                super.a.start();
                int n3 = 0;
                String string;
                try {
                    string = cG.a(new Object[] { "encodeURIComponent(document.referrer)" }).toString();
                }
                catch (Exception ex7) {
                    string = "";
                }
                String string2;
                try {
                    string2 = cG.a(new Object[] { "encodeURIComponent(navigator.userAgent)" }).toString();
                }
                catch (Exception ex8) {
                    string2 = "";
                }
                String string3 = "undefined";
                try {
                    while (n3 < 10 && string3.equalsIgnoreCase("undefined")) {
                        string3 = cG.a(new Object[] { "document.doookID" }).toString();
                        if (string3.equalsIgnoreCase("undefined")) {
                            try {
                                Thread.sleep(100L);
                                ++n3;
                            }
                            catch (InterruptedException ex9) {}
                        }
                    }
                }
                catch (Exception ex10) {
                    string3 = "";
                }
                if (string3.equalsIgnoreCase("undefined")) {
                    string3 = "";
                }
                final String string4 = "r=" + string + "&a=" + string2 + "&" + string3;
                final cD cd = new cD(65793, 1);
                cd.a(0, 0, n);
                cd.a(0, 1, 0);
                cd.a(0, 0, e);
                cd.a(0, 2, doook.z.H);
                cd.a(0, 1, cG.a(new Object[] { "__Q['f']" }).toString());
                cd.a(0, 3, s);
                cd.a(0, 4, string4);
                cd.a(0, 0, a);
                if (!this.e() && super.r != -999) {
                    cd.b(0, 2);
                }
                cd.a(0, 23, this.d(23));
                cd.a(0, 25, this.d(25));
                if (this.g()) {
                    cd.b(0, 3);
                }
                if (super.k && (!doook.f.d || doook.f.h != 1)) {
                    cd.b(0, 21);
                    cd.b(0, 20);
                }
                this.o(cd);
            }
            else {
                System.err.println("acknowledge() failed!!!");
                this.a();
            }
        }
        catch (UnknownHostException ex) {
            this.f();
            new E(super.d, doook.am.a(ao.e("%1 could not locate the specified host. Please make sure you are using the correct host name."), new String[] { doook.z.G }), ex, this).setVisible(true);
        }
        catch (InterruptedIOException ex2) {
            this.f();
            new E(super.d, doook.am.a(ao.e("No response was received from the server.  The %1 Server may not be running, or may not be a compatible version. Please contact the administrator in charge of the site."), new String[] { doook.z.G }), ex2, this).setVisible(true);
        }
        catch (NoRouteToHostException ex3) {
            this.f();
            new E(super.d, doook.am.a(ao.e("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { doook.z.G }), ex3, this).setVisible(true);
        }
        catch (SecurityException ex4) {
            this.f();
            new E(super.d, doook.am.a(ao.e("%1 could not connect to the server. If you are connecting through a firewall or proxy server, you may not be able to use %1."), new String[] { doook.z.G }), ex4, this).setVisible(true);
        }
        catch (IOException ex5) {
            ex5.printStackTrace();
            this.f();
            new E(super.d, doook.am.a(ao.e("%1 could not connect to the specified host.  Please verify that the %1 Server is running and that you are using the correct host name."), new String[] { doook.z.G }), ex5, this).setVisible(true);
        }
        catch (Exception ex6) {
            ex6.printStackTrace();
            this.f();
            new E(super.d, doook.am.a(ao.e("An unknown error occurred while connecting to the %1 Server.  Please contact the administrator in charge of the site."), new String[] { doook.z.G }), ex6, this).setVisible(true);
        }
        super.e = false;
        super.i = false;
        super.j = false;
    }
    
    private void e(final String s) {
        try {
            new Socket(s, 53).close();
        }
        catch (Exception ex) {}
    }
    
    private boolean b(final String s, final int n) {
        int n2;
        if (doook.f.d && doook.f.i < 65792 && doook.f.h == 1) {
            n2 = 2;
        }
        else {
            n2 = 256;
        }
        try {
            super.a = new Socket(s, n);
            super.a = new cy(new BufferedOutputStream(super.a.getOutputStream(), 256));
            super.a = new cC(new BufferedInputStream(super.a.getInputStream(), n2));
            this.z();
            return this.d();
        }
        catch (Exception ex) {
            throw ex;
        }
    }
    
    private boolean r() {
        boolean b = false;
        doook.z.Y = 8396;
        for (int m = 0; !b && m < super.c.length; ++m) {
            super.M = m;
            try {
                b = this.b(super.c[m], doook.z.Y);
            }
            catch (Exception ex2) {
                if (m != super.c.length - 1) {
                    try {
                        Thread.sleep(200L);
                    }
                    catch (InterruptedException ex3) {}
                }
            }
        }
        for (int i = 0; !b && i < super.c.length; ++i) {
            super.M = i;
            this.e(super.c[i]);
            for (int n = 0; !b && n < cB.e.length; ++n) {
                final int a = cB.a(n);
                if (a != cB.i) {
                    doook.z.Y = a;
                    try {
                        b = this.b(super.c[i], a);
                    }
                    catch (Exception ex) {
                        if (i == super.c.length - 1 && n == cB.e.length - 1) {
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
    
    private void z() {
        try {
            final G g = new G();
            final long a = g.a(g.b());
            super.a.writeInt(super.v);
            super.a.writeLong(a);
            super.a.flush();
            final long long1 = super.a.readLong();
            super.a.b(doook.G.a(a));
            super.a.b(doook.G.a(long1));
        }
        catch (IOException ex) {}
    }
    
    public void a(final cD cd) {
        switch (cd.b()) {
            case 67332: {
                this.J(cd);
                break;
            }
            case 67335: {
                this.K(cd);
                break;
            }
            case 67337: {
                this.M(cd);
                break;
            }
            case 68352: {
                this.N(cd);
                break;
            }
            case 68353: {
                this.O(cd);
                break;
            }
            case 16844556: {
                this.P(cd);
                break;
            }
            case 67342: {
                this.Q(cd);
                break;
            }
            case 67338: {
                this.k(cd);
                break;
            }
            case 67339: {
                this.L(cd);
                break;
            }
            case 67841: {
                this.R(cd);
                break;
            }
            case 65798: {
                this.S(cd);
                break;
            }
            case 66309: {
                this.T(cd);
                break;
            }
            case 65797: {
                this.H(cd);
                break;
            }
            case 65800: {
                this.I(cd);
                break;
            }
            case 66306: {
                this.x(cd);
                break;
            }
            case 67843: {
                this.D(cd);
                break;
            }
            case 66305: {
                this.y(cd);
                break;
            }
            case 67334: {
                if (cd.b(0, 0) == this.h()) {
                    this.a(cd.a(0));
                    super.aN = cd.b(0, 3);
                    super.d = cd.b(0, 6);
                }
                this.G(cd);
                break;
            }
            case 263168: {
                cG.a(new Object[] { (cd.a(0, 0) == null) ? "" : cd.a(0, 0) });
                break;
            }
            default: {
                super.a(cd);
                break;
            }
        }
    }
    
    protected void G(final cD cd) {
        this.m();
        super.c.a(true);
        try {
            for (int i = 0; i < cd.g(); ++i) {
                final int b = cd.b(i, 0);
                ab ab = (ab)super.c.b(b);
                if (cd.b(i, 63)) {
                    if (ab != null) {
                        final T t = (T)super.d.b(cd.b(i, 2));
                        if (t != null) {
                            if (!ab.d(23) || this.d(24)) {
                                final T t2 = t;
                                --t2.i;
                            }
                            if (super.a != null) {
                                super.a.b(t);
                            }
                        }
                        final ac ac = new ac(ab.h(), ab.f());
                        ac.a = false;
                        super.c.c(b);
                        if (super.a != null) {
                            super.a.a(ab);
                            ((au)super.a).a(ac, false, false);
                        }
                        final X x = (X)super.a.b(ab.h());
                        if (x != null) {
                            x.dispose();
                        }
                    }
                }
                else {
                    String s = this.d(cd.a(i, 0));
                    if (s.length() > 35 && cG.a[3] && !cd.b(i, 40)) {
                        s = s.substring(0, 35);
                    }
                    int n = 0;
                    if (ab == null) {
                        ab = new ab(b, s);
                        ab.a = this;
                        if (!ab.d(23) || this.d(24)) {
                            super.c.a(ab);
                        }
                    }
                    else {
                        ab.d(s);
                        n = ((super.av && ab.e != cd.b(i, 7)) ? 1 : 0);
                    }
                    ab.g = cd.b(i, 1);
                    ab.a = (as)super.b.b(ab.g);
                    ab.h = cd.b(i, 2);
                    ab.a(cd.a(i));
                    final String a = cd.a(i, 1);
                    final String a2 = cd.a(i, 2);
                    ab.aN = cd.b(i, 3);
                    ab.an = cd.b(i, 4);
                    ab.c = cd.b(i, 5);
                    ab.d = cd.b(i, 6);
                    ab.e = cd.b(i, 7);
                    ab.f = cd.b(i, 8);
                    ab.c = cd.a(i, 3);
                    ab.b = cd.a(i, 4);
                    if (ab.c != null && ab.c.length() > 0 && !doook.y.a.containsKey(ab.c)) {
                        try {
                            doook.y.a.put(ab.c, super.a("userImages/" + ab.c, true, 17));
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                    if (a != null) {
                        ab.W = a;
                    }
                    if (a2 != null) {
                        ab.f = a2;
                    }
                    if (b == this.h()) {
                        this.d(ab.f());
                        if (ab.g != -999) {
                            super.s = ab.g;
                        }
                    }
                    final ac ac2 = new ac(ab.h(), ab.f());
                    ac2.a = true;
                    ac2.g = ab.g;
                    ac2.a = (as)super.b.b(ab.g);
                    ac2.h = ab.h;
                    ac2.a(ab.d());
                    if (super.a != null) {
                        super.a.a(ab, false);
                        ((au)super.a).a(ac2, false, false);
                        if (n != 0) {
                            super.c(super.y);
                        }
                    }
                }
            }
        }
        finally {
            super.c.a();
        }
    }
    
    protected void x(final cD cd) {
        if (super.a != null) {
            for (int i = 0; i < cd.g(); ++i) {
                final Z z = new Z(this.d(cd.a(i, 1)), this.d(cd.a(i, 0)), false, (as)super.b.b(cd.b(i, 0)), -999, false, false);
                z.ak = cd.b(i, 1);
                z.b = cd.a(i, 2);
                z.c = cd.a(i, 3);
                z.al = cd.b(i, 6);
                z.d = cd.b(i, 7);
                z.g = cd.b(i, 31);
                z.ae = cd.b(i, 36);
                z.af = cd.b(i, 37);
                z.ag = cd.b(i, 38);
                z.ah = cd.b(i, 39);
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
    
    protected void D(final cD cd) {
        super.D(cd);
        cG.h[0] = cd.b(0, 6);
        cG.h[1] = cd.b(0, 7);
        cG.h[2] = cd.b(0, 8);
        cG.h[3] = cd.b(0, 9);
        if (!"Admin".equals(super.n) && this.a != null) {
            if (super.x != null && super.x.length() > 7) {
                if (cI.r == null) {
                    cI.r = new MenuItem(ao.e("Add To Favorites"));
                    this.a.insert(cI.r, 0);
                    this.a.insertSeparator(1);
                }
            }
            else if (cI.r != null) {
                this.a.remove(1);
                this.a.remove(0);
                cI.r = null;
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
                    this.a(cd.a(i, 0), cg, cd.j, cd.o, false, cd.c(), cd.l(), cd.b(i, 2), cd.b(i, 3), cd.b(i, 37), cd.b(i, 38), cd.b(i, 39));
                }
            }
        }
    }
    
    public void a(final String s, final cG cg, final int n, final int n2, final boolean b, final long n3, final int n4, final int n5, final int n6, final boolean b2, final boolean b3, final boolean b4) {
        this.a(s, cg, n, n2, b, false, n3, n4, null, n5, n6, b2, b3, b4);
    }
    
    public void a(final String s, final cG cg, final int n, final int n2, final boolean b, final boolean b2, final long n3, final int n4, final byte[] a, final int al, final int d, final boolean af, final boolean ag, final boolean ah) {
        if (super.a != null) {
            int h = cg.h();
            if (h == this.h()) {
                h = n;
            }
            cg.f();
            final Z z = new Z(this.a(s, cg), cg, n != -1 && n != -3 && n != -2 && !b, (as)super.b.b(cg.g));
            z.a(n == -3 || n == -2);
            z.a(n3, n4);
            if (b2) {
                z.ai = a[0];
            }
            else {
                z.a = a;
            }
            z.al = al;
            z.d = d;
            z.af = af;
            z.ag = ag;
            z.ah = ah;
            if (z.v) {
                final X x = (X)super.a.b(h);
                if (x != null) {
                    x.a(z);
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
    
    protected void H(final cD cd) {
        super.e = new Vector();
        for (int g = cd.g(), i = 0; i < g; ++i) {
            super.e.addElement(new bz(cd.a(i, 0), cd.a(i, 1)));
        }
    }
    
    protected void I(final cD cd) {
        this.b = new bx(cd.a(0, 0), Long.parseLong(cd.a(2, 0)), Integer.parseInt(cd.a(1, 0)));
    }
    
    public void J(final cD cd) {
        super.g = cd.a(-1);
        super.f.a(true);
        try {
            for (int i = 0; i < cd.g(); ++i) {
                final int b = cd.b(i, 0);
                aK ak = (aK)super.f.b(b);
                if (cd.b(i, 63)) {
                    if (ak != null) {
                        super.f.c(b);
                    }
                }
                else {
                    if (ak == null) {
                        ak = new aK(b, cd.a(i, 0));
                        super.f.a(ak);
                    }
                    else {
                        ak.d(cd.a(i, 0));
                    }
                    ak.i = cd.b(i, 1);
                    ak.h = cd.b(i, 2);
                    ak.as = cd.b(i, 3);
                    ak.aN = cd.b(i, 4);
                    ak.d = cd.b(i, 5);
                    ak.a = cd.a(i, 1);
                    ak.a(cd.a(i));
                }
            }
        }
        finally {
            super.f.a();
        }
    }
    
    public void K(final cD cd) {
        super.g.a(true);
        try {
            for (int i = 0; i < cd.g(); ++i) {
                final int b = cd.b(i, 0);
                aJ aj = (aJ)super.g.b(b);
                if (cd.b(i, 63)) {
                    if (aj != null) {
                        super.g.c(b);
                    }
                }
                else {
                    if (aj == null) {
                        aj = new aJ(b, cd.a(i, 0));
                        super.g.a(aj);
                    }
                    else {
                        aj.d(cd.a(i, 0));
                    }
                    aj.b = cd.a(i, 0);
                    aj.aN = cd.b(i, 1);
                    aj.d = cd.b(i, 2);
                    aj.aO = cd.b(i, 3);
                    aj.aj = cd.a(i, 1);
                    aj.ai = cd.a(i, 2);
                    aj.ak = cd.a(i, 3);
                    aj.al = cd.a(i, 4);
                    aj.a(cd.a(i));
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        finally {
            super.g.a();
        }
    }
    
    public void L(final cD cd) {
        super.n.a(true);
        try {
            for (int i = 0; i < cd.g(); ++i) {
                final int b = cd.b(i, 0);
                aC ac = (aC)super.n.b(b);
                if (cd.b(i, 63)) {
                    if (ac != null) {
                        super.n.c(b);
                    }
                }
                else {
                    if (ac == null) {
                        ac = new aC(b, cd.a(i, 0));
                        super.n.a(ac);
                    }
                    else {
                        ac.d(cd.a(i, 0));
                    }
                    ac.aq = cd.b(i, 1);
                    ac.a(cd.a(i));
                }
            }
        }
        finally {
            super.n.a();
        }
    }
    
    public void M(final cD cd) {
        super.m.a(true);
        try {
            for (int i = 0; i < cd.g(); ++i) {
                final int b = cd.b(i, 0);
                bZ bz = (bZ)super.m.b(b);
                if (cd.b(i, 63)) {
                    if (bz != null) {
                        super.m.c(b);
                        if (this.c != null) {
                            this.c.a(bz);
                        }
                    }
                }
                else {
                    if (bz == null) {
                        bz = new bZ(b, cd.a(i, 0));
                        super.m.a(bz);
                    }
                    else {
                        bz.d(cd.a(i, 0));
                    }
                    bz.a(cd.a(i));
                    bz.h = cd.b(i, 1);
                    bz.Z = cd.b(i, 2);
                    bz.U = cd.a(i, 1);
                    bz.S = cd.a(i, 2);
                    bz.T = cd.a(i, 3);
                    bz.V = cd.a(i, 4);
                    bz.ai = cd.b(i, 31);
                    bz.aj = cd.b(i, 32);
                    cI.an = cd.a(0, 3);
                    if (bz.Z == 0) {
                        bz.Z = 1023;
                    }
                    if (this.c != null) {
                        this.c.a(bz);
                    }
                }
            }
        }
        finally {
            super.m.a();
        }
    }
    
    public void a(final URL url, final String s) {
        if (super.h) {
            final AppletContext appletContext = cI.b.getAppletContext();
            appletContext.showDocument(url, s);
            if (doook.f.d && doook.f.h == 1 && doook.f.i < 65792 && url.getProtocol().equalsIgnoreCase("http")) {
                appletContext.showDocument(url, s);
            }
        }
        else {
            try {
                BrowserLauncher.a(url.toString());
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private final void N(final cD cd) {
        super.m.a(true);
        try {
            for (int i = 0; i < cd.g(); ++i) {
                final bZ bz = (bZ)super.m.b(cd.b(i, 0));
                if (bz != null) {
                    final int b = cd.b(i, 1);
                    final int b2 = cd.b(i, 3);
                    bz.ar = cd.b(i, 10);
                    this.ae += b - bz.b;
                    this.n += b2 - bz.a;
                    bz.b = b;
                    bz.i = cd.b(i, 2);
                    bz.a = b2;
                    bz.k = cd.a(i, 4);
                    bz.l = cd.a(i, 6);
                    bz.m = cd.a(i, 8);
                    if (this.c != null) {
                        this.c.a(bz);
                    }
                    if (bz.k > this.u) {
                        this.u = bz.k;
                    }
                }
            }
            if (this.ae > this.af) {
                this.af = this.ae;
            }
            if (this.c != null) {
                this.c.a();
            }
        }
        finally {
            super.m.a();
        }
    }
    
    private final void O(final cD cd) {
        this.t = cd.a(0, 0);
        final String a = cd.a(0, 0);
        this.aG = cd.b(0, 2);
        this.af = cd.b(0, 3);
        if (a != null) {
            this.aa = a;
        }
        if (this.c != null) {
            this.c.a();
        }
    }
    
    protected void P(final cD cd) {
        super.o.a(true);
        try {
            for (int i = 0; i < cd.g(); ++i) {
                final int b = cd.b(i, 0);
                aD ad = (aD)super.o.b(b);
                if (cd.b(i, 63)) {
                    super.o.c(b);
                }
                else {
                    if (ad == null) {
                        ad = new aD(b, cd.a(i, 0));
                        super.o.a(ad, b);
                    }
                    else {
                        ad.d(cd.a(i, 0));
                    }
                    ad.a(cd.a(i));
                }
            }
        }
        finally {
            super.o.a();
        }
    }
    
    protected void Q(final cD cd) {
        super.k.a(true);
        try {
            for (int i = 0; i < cd.g(); ++i) {
                final int b = cd.b(i, 0);
                bs bs = (bs)super.k.b(b);
                if (cd.b(i, 63)) {
                    if (bs != null) {
                        super.k.c(b);
                    }
                }
                else {
                    if (bs == null) {
                        bs = new bs(b, cd.a(i, 1));
                        super.k.a(bs, b);
                    }
                    else {
                        bs.d(cd.a(i, 1));
                    }
                    bs.a(cd.a(i));
                    bs.Y = cd.a(i, 0);
                }
            }
        }
        finally {
            super.k.a();
        }
    }
    
    public void k(final cD cd) {
    }
    
    protected void R(final cD cd) {
        super.p.a(true);
        try {
            for (int i = 0; i < cd.g(); ++i) {
                final int b = cd.b(i, 0);
                bU a = (bU)super.p.b(b);
                if (cd.b(i, 63)) {
                    super.p.c(b);
                }
                else {
                    if (a == null) {
                        a = new bU(b, cd.a(i, 0));
                        cd.a(i, 0, super.p.a(a));
                    }
                    else {
                        a.d(cd.a(i, 0));
                    }
                    a.ae = cd.b(i, 1);
                    a.ac = cd.b(i, 2);
                    a.ad = cd.b(i, 3);
                    a.c = new Color(cd.b(i, 4));
                    a.h = new Color(cd.b(i, 5));
                    a.a = new Color(cd.b(i, 6));
                    a.i = new Color(cd.b(i, 7));
                    a.b = new Color(cd.b(i, 8));
                    a.aG = cd.b(i, 9);
                    a.aH = cd.b(i, 10);
                    a.aE = cd.b(i, 11);
                    a.ao = cd.b(i, 12);
                    a.x = cd.b(i, 13);
                    a.af = cd.b(i, 16);
                    a.f = cd.a(i, 1);
                    a.ac = cd.a(i, 2);
                    a.g = cd.a(i, 3);
                    a.h = cd.a(i, 4);
                    a.l = cd.a(i, 5);
                    a.m = cd.a(i, 6);
                    a.P = cd.a(i, 7);
                    cd.a(i, 8);
                    String a2 = cd.a(i, 9);
                    if (a2 == null) {
                        a2 = "";
                    }
                    a.ad = a2;
                    cd.b(i, 14);
                    cd.b(i, 15);
                    a.a(cd.a(i));
                    if (a.d(62)) {
                        this.a = a;
                    }
                }
            }
        }
        finally {
            super.p.a();
        }
    }
    
    protected void e(final cD cd) {
        final int b = cd.b(0, 1);
        if (cd.b(0, 0) == 1 && b == 68096) {
            new E(super.d, ao.e("Note"), ao.e("Your password could not be changed because your old password was not entered correctly."), this).setVisible(true);
            this.a(this.d(58));
            return;
        }
        super.e(cd);
    }
    
    protected void S(final cD cd) {
        this.aP = cd.b(0, 0);
        this.aQ = cd.b(1, 0);
        this.aR = cd.b(2, 0);
        this.ay = (cd.b(3, 0) == 0);
        this.az = (cd.b(4, 0) == 0);
    }
    
    protected void a() {
        super.A = super.z;
        super.z = "00000000-0000-0000-0000-000000000000";
        super.av = false;
        if (this.a != null) {
            this.a.dispose();
            this.a = null;
        }
        if (this.c != null) {
            this.c.dispose();
            this.c = null;
        }
        cI.j = null;
        cI.k = null;
        cI.l = null;
        cI.m = null;
        cI.n = null;
        cI.c = null;
        cI.d = null;
        cI.e = null;
        cI.o = null;
        cI.p = null;
        cI.q = null;
        cI.r = null;
        super.a();
    }
    
    public cD a() {
        super.p.a(false);
        try {
            final cD cd = new cD(67841, super.p.b());
            for (int i = 0; i < super.p.b(); ++i) {
                final bU bu = (bU)super.p.a(i);
                cd.a(i, 0, bu.h());
                cd.a(i, 1, bu.ae);
                cd.a(i, 2, bu.ac);
                cd.a(i, 3, bu.ad);
                cd.a(i, 4, bu.c.getRGB());
                cd.a(i, 5, bu.h.getRGB());
                cd.a(i, 6, bu.a.getRGB());
                cd.a(i, 7, bu.i.getRGB());
                cd.a(i, 8, bu.b.getRGB());
                cd.a(i, 9, bu.aG);
                cd.a(i, 10, bu.aH);
                cd.a(i, 11, bu.aE);
                cd.a(i, 12, bu.ao);
                cd.a(i, 13, bu.x);
                cd.a(i, 14, bu.y);
                cd.a(i, 15, bu.aI);
                cd.a(i, 16, bu.af);
                cd.a(i, 0, bu.f());
                cd.a(i, 1, bu.f);
                cd.a(i, 2, bu.ac);
                cd.a(i, 3, bu.g);
                cd.a(i, 4, bu.h);
                cd.a(i, 5, bu.l);
                cd.a(i, 6, bu.m);
                cd.a(i, 7, bu.P);
                cd.a(i, 8, bu.ae);
                cd.a(i, 9, bu.ad);
                cd.a(i, bu.d());
            }
            return cd;
        }
        finally {
            super.p.a();
        }
    }
    
    public void d() {
        if (!super.h) {
            if (this.a != null) {
                this.a.ae = super.u;
                this.a.ac = super.s;
                this.a.ad = super.t;
                this.a.c = super.a.l;
                this.a.h = super.a.m;
                this.a.a = super.a.n;
                this.a.i = super.a.o;
                this.a.b = super.a.p;
                this.a.aG = super.B;
                this.a.aH = super.C;
                this.a.aE = super.D;
                this.a.ao = super.E;
                this.a.x = super.F;
                this.a.y = super.a.af;
                this.a.aI = super.a.ae;
                this.a.ae = super.a.i;
                this.a.af = super.w;
                this.a.f = this.f();
                this.a.ac = super.w;
                this.a.g = super.r;
                this.a.h = super.s;
                this.a.l = super.t;
                this.a.m = super.u;
                this.a.P = super.v;
                this.a.ad = a(this);
                if (super.C) {
                    this.a.n(32);
                }
                if (super.D) {
                    this.a.n(60);
                }
                if (super.z != -999) {
                    this.a.n(super.z);
                }
                else {
                    this.a.h(1);
                    this.a.h(0);
                }
                if (super.v) {
                    this.a.n(25);
                }
                else {
                    this.a.h(25);
                }
            }
            if (!super.h && !this.c.exists()) {
                this.c.mkdirs();
            }
            final aN an = new aN(new File(this.c, "settings.digi"), this.d, true);
            try {
                an.a(this.a());
            }
            finally {
                an.c();
            }
        }
    }
    
    public void a(final Frame frame) {
        this.a = new Menu(doook.z.G);
        final MenuBar menuBar = new MenuBar();
        for (int i = 1; i <= super.r.b(); ++i) {
            this.a.add((MenuItem)super.r.b(i));
        }
        if (super.r.b() >= 1) {
            this.a.addSeparator();
        }
        if ((this.d() & 0x3F2C00004000000L) != 0x0L) {
            cI.j = new MenuItem(ao.e("Set Site Options..."));
            this.a.add(cI.j);
            this.a.addSeparator();
        }
        if (!"Admin".equals(super.n) && (this.d(25) || this.d(24))) {
            final Menu menu = new Menu(ao.e("Connection Mode"));
            this.a = new CheckboxMenuItem(ao.e("Ghost"), this.d(22));
            this.b = new CheckboxMenuItem(ao.e("Invisible"), this.d(23) && !this.d(22));
            this.c = new CheckboxMenuItem(ao.e("Not Invisible"), !this.d(23) && !this.d(22));
            if (this.d(25)) {
                menu.add(this.a);
            }
            if (this.d(24)) {
                menu.add(this.b);
                menu.add(this.c);
            }
            this.a.add(menu);
            this.a.addSeparator();
        }
        if (this.d(13)) {
            cI.o = new MenuItem(ao.e("Restart Chat..."));
            this.a.add(cI.o);
        }
        if ("Admin".equals(super.n) && (this.d() & 0xC000000000000L) != 0x0L) {
            cI.k = new MenuItem(ao.e("Set Server Options..."));
            this.a.add(cI.k);
        }
        if (this.d(42)) {
            cI.n = new MenuItem(ao.e("Send Chat Broadcast..."));
            this.a.add(cI.n);
        }
        if (this.d(4)) {
            cI.p = new MenuItem(ao.e("View Server Logs"));
            this.a.add(cI.p);
            cI.q = new MenuItem(ao.e("Change FTP Password"));
            this.a.add(cI.q);
        }
        if (this.d(45)) {
            cI.c = new MenuItem(ao.e("Change Password..."));
            this.a.add(cI.c);
        }
        if (this.d(59) || this.d(60)) {
            (cI.e = new MenuItem((this.d(59) ? ao.e("Guest Speaker Controls") : ao.e("Moderator Controls")) + "...")).enable(false);
            this.a.add(cI.e);
        }
        if (!super.h) {
            if (cI.j != null || cI.k != null || cI.n != null || cI.c != null) {
                this.a.addSeparator();
            }
            cI.d = new MenuItem(ao.e("New Login Window"));
            this.a.add(cI.d);
            this.a.addSeparator();
            cI.l = new MenuItem(ao.e("Disconnect"));
            this.a.add(cI.l);
            cI.m = new MenuItem(ao.e("Quit"), new MenuShortcut(81));
            this.a.add(cI.m);
        }
        menuBar.add(this.a);
        if (frame != null) {
            frame.setMenuBar(menuBar);
        }
        if (!"Admin".equals(super.n)) {
            if (super.x != null && super.x.length() > 7) {
                cI.r = new MenuItem(ao.e("Add To Favorites"));
                this.a.insert(cI.r, 0);
                this.a.insertSeparator(1);
            }
            else if (cI.r != null) {
                this.a.remove(1);
                this.a.remove(0);
                cI.r = null;
            }
        }
    }
    
    public boolean a(final Event event) {
        if (event.target == cI.l) {
            this.i();
        }
        else if (event.target == cI.m) {
            this.j();
        }
        else if (event.target == cI.k) {
            this.b();
        }
        else if (event.target == cI.j) {
            e(this);
        }
        else if (event.target == cI.n) {
            a((u)this);
        }
        else if (event.target == cI.c) {
            this.a(false);
        }
        else if (event.target == cI.d) {
            new cI();
        }
        else if (event.target == cI.r) {
            f(this);
        }
        else if (event.target == cI.o) {
            c(this);
        }
        else if (event.target == cI.p) {
            b(this);
        }
        else if (event.target == cI.q) {
            d(this);
        }
        else if (event.target == cI.e) {
            if (this.a != null) {
                this.a.setVisible(true);
            }
        }
        else if (event.target == this.a) {
            if (this.a.getState()) {
                this.o(1);
            }
            this.a.setState(true);
            this.b.setState(false);
            this.c.setState(false);
        }
        else if (event.target == this.b) {
            if (this.b.getState()) {
                this.o(2);
            }
            this.a.setState(false);
            this.b.setState(true);
            this.c.setState(false);
        }
        else if (event.target == this.c) {
            if (this.c.getState()) {
                this.o(3);
            }
            this.a.setState(false);
            this.b.setState(false);
            this.c.setState(true);
        }
        else if (super.r.a(event.target)) {
            this.a((URL)super.s.a(super.r.a(event.target)), "_blank");
        }
        return true;
    }
    
    private void o(final int n) {
        final cD cd = new cD(66305, 1);
        cd.o = -1;
        cd.j = this.h();
        cd.a(0, 0, -1);
        cd.a(0, 0, "mode=" + String.valueOf(n));
        this.o(cd);
    }
    
    public void s(final cD cd) {
        super.s(cd);
        if (super.n.equals("Admin")) {
            this.c = new cg(this);
        }
    }
    
    public void t(final cD cd) {
        if (this.am != null) {
            super.d.a(false);
            try {
                synchronized (super.d) {
                    for (int i = 0; i < super.d.b(); ++i) {
                        final av av = (av)super.d.a(i);
                        if (av.f().equals(this.am)) {
                            super.r = av.h();
                            break;
                        }
                    }
                }
            }
            finally {
                super.d.a();
            }
        }
        if (this.c != null) {
            this.c.setVisible(true);
        }
        else {
            super.t(cd);
        }
        if (this.d(58)) {
            this.a(true);
        }
        final cD cd2 = new cD(65799, 1);
        cd2.a(0, 0, this.h());
        this.o(cd2);
        final cD cd3 = new cD(65796, 1);
        cd3.a(0, 0, this.h());
        this.o(cd3);
        final cD cd4 = new cD(65798, 1);
        cd4.a(0, 0, this.h());
        this.o(cd4);
    }
    
    public void T(final cD cd) {
        if (cd.b() != null) {
            if (cd.b().length < 2) {
                return;
            }
            this.a(cd.b());
        }
    }
    
    protected void b(final cD cd) {
        super.b(cd);
        if ((this.d(59) || this.d(60)) && (super.h == -999 || !((T)super.d.b(super.h)).a())) {
            cI.e.enable(false);
        }
    }
    
    protected void g(final cD cd) {
        super.g(cd);
        if (cd.b(-1, 31)) {
            this.i();
        }
    }
    
    public static final void a(final u u) {
        boolean b;
        if ("Admin".equals(u.n)) {
            b = false;
        }
        else {
            b = true;
        }
        final bC bc = new bC(u.a.a(), u, 0, b ? 1 : 0);
        bc.d();
        bc.setVisible(true);
    }
    
    public void b() {
        this.c.setCursor(3);
        final bH bh = new bH(this.c, this);
        bh.setVisible(true);
        bh.toFront();
        this.c.setCursor(0);
    }
    
    public void s() {
        boolean b;
        if ("Admin".equals(super.n)) {
            b = false;
        }
        else {
            b = true;
        }
        new bC((this.c != null) ? this.c : super.a.a(), this, 0, b ? 1 : 0).setVisible(true);
    }
    
    public static final void b(final u u) {
        final bw bw = new bw(u.a.a(), u);
        bw.d();
        bw.setVisible(true);
    }
    
    public static final void c(final u u) {
        final bu bu = new bu(u.a.a(), u);
        bu.d();
        bu.setVisible(true);
    }
    
    public static final void d(final u u) {
        final bv bv = new bv(u.a.a(), u);
        bv.d();
        bv.setVisible(true);
    }
    
    public static final void e(final u u) {
        if (u.a.a() != null) {
            u.a.a().setCursor(3);
        }
        final ce ce = new ce(u.a.a(), u);
        ce.setVisible(true);
        ce.toFront();
        if (u.a.a() != null) {
            u.a.a().setCursor(0);
        }
    }
    
    public void a(final boolean b) {
        new cw((this.c == null) ? super.a.a() : this.c, this, b).setVisible(true);
        this.a(58, b);
    }
    
    public void x() {
        final String parameter = cI.b.getParameter("siteID");
        final String parameter2 = cI.b.getParameter("serverID");
        final String parameter3 = cI.b.getParameter("nickname");
        final String t = null;
        final String r = null;
        final Object o = null;
        final String s = null;
        final String u = null;
        final String v = null;
        final String s2 = null;
        final Object o2 = null;
        final Object o3 = null;
        final String s3 = null;
        final String s4 = null;
        final String s5 = null;
        final String parameter4 = cI.b.getParameter("floodControl");
        final String s6 = null;
        final String parameter5 = cI.b.getParameter("autoPopup");
        final String parameter6 = cI.b.getParameter("noLogoutButton");
        final String s7 = null;
        final String s8 = null;
        if (s6 != null) {
            cB.a();
            cB.a(s6);
        }
        super.w = cI.b.getCodeBase().getHost();
        if (r != null) {
            super.r = r;
        }
        if (t != null) {
            super.t = t;
        }
        if (s != null) {
            super.s = s;
        }
        if (u != null) {
            super.u = u;
        }
        if (v != null) {
            super.v = v;
        }
        if ("male".equals(o)) {
            super.z = 1;
        }
        else if ("female".equals(o)) {
            super.z = 0;
        }
        if ("true".equals(o2)) {
            super.C = true;
        }
        if ("true".equals(o3)) {
            super.F = true;
        }
        if ("true".equals(parameter6)) {
            super.d = true;
        }
        if ("true".equals(parameter5)) {
            super.B = true;
        }
        if ("true".equalsIgnoreCase(s7)) {
            super.H = true;
        }
        if ("true".equalsIgnoreCase(s8)) {
            super.k = true;
        }
        if (s2 != null) {
            try {
                super.s = Integer.parseInt(s2);
            }
            catch (NumberFormatException ex) {}
        }
        if (s3 != null) {
            try {
                super.w = Integer.parseInt(s3);
            }
            catch (NumberFormatException ex2) {}
        }
        if (s4 != null) {
            try {
                super.c = new URL(s4);
            }
            catch (MalformedURLException ex3) {}
        }
        if (s5 != null) {
            a(this, s5);
        }
        if (parameter3 != null) {
            super.J = true;
        }
        super.k = 250;
        if (parameter4 != null) {
            try {
                super.k = Integer.parseInt(parameter4);
            }
            catch (NumberFormatException ex4) {}
        }
        if (cI.b.getParameter("MenuItem1") != null) {
            for (int n = 1; cI.b.getParameter("MenuItem" + n) != null; ++n) {
                super.r.a(new MenuItem(cI.b.getParameter("MenuItem" + n)), n);
                try {
                    super.s.a(new URL(cI.b.getParameter("MenuLocation" + n)), n);
                }
                catch (MalformedURLException ex5) {
                    super.r.c(n);
                }
            }
        }
        if (parameter != null) {
            try {
                super.u = Integer.parseInt(parameter);
                super.v = Integer.parseInt(parameter2);
            }
            catch (NumberFormatException ex6) {
                super.u = -999;
            }
        }
        this.d(parameter3);
    }
    
    public void e() {
        super.k = true;
        this.a = null;
        final File file = new File(System.getProperty("user.dir"));
        this.c = new File(file, "Settings" + File.separator + "ChatMaster");
        this.d = new File(file, "Temp");
        final File file2 = new File(this.c, "settings.digi");
        if (file2.exists()) {
            final aN an = new aN(file2, this.d, false);
            for (int a = an.a(), i = 0; i < a; ++i) {
                this.a(an.a(i));
            }
        }
        if (!super.p.b(0)) {
            final bU bu = new bU(0, "Admin");
            bu.ae = 0;
            bu.f = "Admin";
            super.p.a(bu);
        }
        if (!super.p.b(1)) {
            final bU bu2 = new bU(1, "ChatMaster");
            bu2.ae = -999;
            bu2.f = "ChatMaster";
            super.p.a(bu2);
        }
        if (!super.p.b(1000)) {
            final bU bu3 = new bU(1000, "Other");
            bu3.ae = -999;
            super.p.a(bu3);
        }
    }
    
    public void a(final bU a) {
        if (this.a != null) {
            this.a.h(62);
        }
        (this.a = a).n(62);
        super.u = a.ae;
        super.s = a.ac;
        super.t = a.ad;
        super.a.l = a.c;
        super.a.m = a.h;
        super.a.n = a.a;
        super.a.o = a.i;
        super.a.p = a.b;
        super.B = a.aG;
        super.C = a.aH;
        super.D = a.aE;
        super.E = a.ao;
        super.F = a.x;
        super.w = a.af;
        this.d(a.f);
        super.w = a.ac;
        super.r = a.g;
        super.s = a.h;
        super.t = a.l;
        super.u = a.m;
        super.v = a.P;
        super.q = a.ad;
        super.C = a.d(32);
        super.D = a.d(60);
        super.v = a.d(25);
        super.q = a.ad;
        if (a.d(1)) {
            super.z = 1;
        }
        else if (a.d(0)) {
            super.z = 0;
        }
        else {
            super.z = -999;
        }
        super.a.i = new String(a.ae);
        super.a.af = a.y;
        super.a.ae = a.aI;
        if (this.a != null) {
            this.a.a(a);
        }
    }
    
    public Image a(final URL url) {
        if (super.h) {
            return cI.b.getImage(url);
        }
        return Toolkit.getDefaultToolkit().getImage(url);
    }
    
    public void j() {
        super.j();
        System.exit(0);
    }
    
    public static final void f(final u u) {
        cG.a(new Object[] { "top.focus(); setTimeout(\"doookBookmarkSite('" + u.x + "', top.document.title);\", 200);" });
    }
    
    public void f() {
        if (super.h) {
            cI.b.reset();
        }
        else {
            this.a.a();
        }
    }
    
    public void a(final Z z) {
        if (this.a == null) {
            this.a = new ct(super.a.a(), this);
            final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int n = screenSize.width / 2 - 20;
            int n2 = screenSize.height / 2 - 20;
            if (n > 400) {
                n = 400;
            }
            if (n2 > 300) {
                n2 = 300;
            }
            this.a.reshape(2, 10 + n2, n, n2);
            cI.e.enable(true);
        }
        this.a.a(z);
        if (!this.a.isShowing()) {
            this.a.setVisible(true);
        }
    }
    
    public void a(final byte[] array) {
        if (this.a != null) {
            this.a.a(array);
            this.a.a();
        }
    }
    
    public void a(final Z z, final ab ab) {
        final bl bl = new bl(this.a, this, z, ab);
        if (this.a != null) {
            this.a.a();
        }
    }
    
    static String a(final cI ci) {
        return ci.q;
    }
    
    static void a(final cI ci, final String q) {
        ci.q = q;
    }
    
    public cI(final z b) {
        this.u = 0L;
        cI.b = b;
        super.h = true;
        cB.a(cB.W + "," + cB.i);
        try {
            this.x();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        super.z = true;
        super.A = false;
    }
    
    public cI() {
        this.u = 0L;
        final String s = cI.h.get("floodControl");
        super.k = 250;
        super.h = false;
        if (s != null) {
            try {
                super.k = Integer.parseInt(s);
            }
            catch (NumberFormatException ex2) {}
        }
        try {
            this.e();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        this.a = new ci(this);
        if (this.a == null) {
            this.a = (bU)super.p.a(0);
        }
        this.a(this.a);
        super.z = true;
        super.A = false;
        this.a.setVisible(true);
    }
    
    static {
        cI.an = "";
    }
}
