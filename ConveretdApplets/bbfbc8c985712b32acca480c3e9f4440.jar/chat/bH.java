// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.io.IOException;
import java.net.MalformedURLException;
import java.awt.Image;
import java.applet.Applet;
import netscape.javascript.JSObject;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Event;
import java.awt.MenuBar;
import java.awt.Menu;
import java.awt.Frame;
import java.applet.AppletContext;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.awt.CheckboxMenuItem;
import java.awt.MenuItem;

public final class bH extends cx
{
    private bg a;
    private l a;
    private MenuItem a;
    private MenuItem b;
    private MenuItem c;
    private MenuItem d;
    private MenuItem e;
    private MenuItem f;
    private MenuItem g;
    private MenuItem h;
    private MenuItem i;
    private MenuItem j;
    private MenuItem k;
    private MenuItem l;
    private CheckboxMenuItem a;
    private be a;
    protected int g;
    protected int h;
    protected int k;
    protected int l;
    protected int m;
    protected long a;
    protected long c;
    protected String e;
    private String q;
    protected int n;
    protected int o;
    protected boolean e;
    private cc a;
    private aN a;
    private CheckboxMenuItem[] a;
    private CheckboxMenuItem b;
    private CheckboxMenuItem c;
    private CheckboxMenuItem d;
    
    public final void a(final String s, final String s2, final aV av, String s3, final int n) {
        s3 = (String)(super.f ? super.a.getCodeBase() : new URL("http://" + s3 + "/DigiChat/DigiClasses/"));
        if (ce.c && super.f) {
            try {
                final Object invoke = Class.forName("netscape.javascript.JSObject").getMethod("getWindow", Class.forName("java.applet.Applet")).invoke(this, super.a);
                final Class[] array = { Class.forName("java.lang.String") };
                final Object invoke2;
                new URL((String)(invoke2 = invoke.getClass().getMethod("getMember", (Class<?>[])array).invoke(invoke, new String("document"))).getClass().getMethod("getMember", (Class<?>[])array).invoke(invoke2, new String("URL"))).getHost();
            }
            catch (NoClassDefFoundError noClassDefFoundError) {}
            catch (ClassNotFoundException ex) {}
            catch (NoSuchMethodException ex2) {}
            catch (IllegalAccessException ex3) {}
            catch (InvocationTargetException ex4) {}
            catch (Exception ex5) {}
        }
        String string;
        if ((string = this.a(new Object[] { "window.location.href" }).toString()).length() == 0) {
            string = "localhost";
        }
        System.out.println("ChatMaster.login()");
        this.a(s, s2, av, (URL)s3, n, super.f ? string : null);
    }
    
    public final void a(final String s, final String s2, final aV av, final URL url, final int n, final String s3) {
        this.g = 0;
        this.l = 0;
        this.m = 0;
        this.h = 0;
        this.k = 0;
        this.c = 0L;
        super.a(s, s2, av, url, n, s3);
    }
    
    public final void a(final r r) {
        switch (r.a) {
            case 67332: {
                (this = this).g = r.a(-1);
                try {
                    for (int i = 0; i < r.b; ++i) {
                        final int a = r.a(i, 0);
                        ao ao = (ao)super.f.b(a);
                        if (r.a(i, 63)) {
                            if (ao != null) {
                                super.f.b(a);
                            }
                        }
                        else {
                            if (ao == null) {
                                ao = new ao(a, r.a(i, 0));
                                super.f.a(ao);
                            }
                            else {
                                ao.d = r.a(i, 0);
                            }
                            ao.b = r.a(i, 1);
                            ao.a = r.a(i, 2);
                            ao.c = r.a(i, 3);
                            ao.d = r.a(i, 4);
                            ao.e = r.a(i, 5);
                            ao.a = r.a(i, 1);
                            ao.b = r.a(i);
                        }
                    }
                }
                finally {
                    throw loadexception(java.lang.Throwable.class);
                }
            }
            case 67844: {
                (this = this).h = r.a(0);
                super.E = r.a(0, 0);
                if (super.E < 1) {
                    super.E = 300;
                }
                super.F = r.a(0, 1);
                super.G = r.a(0, 2);
                super.H = r.a(0, 3);
                super.M = r.a(0, 4);
                super.N = r.a(0, 5);
                super.O = r.a(0, 6);
                super.I = r.a(0, 7);
                if (super.a != null) {
                    super.a.a(r.a(super.h, 5));
                }
                if (super.I < 1) {
                    super.I = 300;
                }
                super.J = r.a(0, 8);
                super.K = r.a(0, 9);
                super.L = r.a(0, 10);
            }
            case 67335: {
                try {
                    for (int j = 0; j < r.b; ++j) {
                        final int a2 = r.a(j, 0);
                        Z z = (Z)super.g.b(a2);
                        if (r.a(j)) {
                            if (z != null) {
                                super.g.b(a2);
                            }
                        }
                        else {
                            if (z == null) {
                                z = new Z(a2, r.a(j, 0));
                                super.g.a(z);
                            }
                            else {
                                z.d = r.a(j, 0);
                            }
                            z.a = r.a(j);
                            z.a = r.a(j, 1);
                            z.b = r.a(j, 1);
                            z.a = r.a(j, 2);
                            z.c = r.a(j, 3);
                            z.c = r.a(j, 2);
                            z.d = r.a(j, 3);
                            z.b = r.a(j, 4);
                            z.a(r.a(j));
                        }
                    }
                }
                finally {
                    throw loadexception(java.lang.Throwable.class);
                }
            }
            case 67337: {
                try {
                    for (int k = 0; k < r.b; ++k) {
                        final int a3 = r.a(k, 0);
                        aK ak = (aK)super.i.b(a3);
                        if (r.a(k, 63)) {
                            if (ak != null) {
                                super.i.b(a3);
                                if (this.a != null) {
                                    this.a.a.a.a((a)ak);
                                }
                            }
                        }
                        else {
                            if (ak == null) {
                                ak = new aK(a3, r.a(k, 0));
                                super.i.a(ak);
                            }
                            else {
                                ak.d = r.a(k, 0);
                            }
                            ak.b = r.a(k);
                            ak.a = r.a(k, 1);
                            ak.e = r.a(k, 2);
                            ak.a = r.a(k, 1);
                            ak.b = r.a(k, 2);
                            ak.c = r.a(k, 4);
                            if (ak.e == 0) {
                                ak.e = 1023;
                            }
                            if (this.a != null) {
                                this.a.a(ak);
                            }
                        }
                    }
                }
                finally {
                    throw loadexception(java.lang.Throwable.class);
                }
            }
            case 65801: {
                aN.a = null;
                if (r.a(0, 0) != -1) {
                    try {
                        aN.a = new String[r.b];
                        for (int l = 0; l < r.b; ++l) {
                            aN.a[l] = r.a(l, 0);
                        }
                    }
                    finally {
                        throw loadexception(java.lang.Throwable.class);
                    }
                }
            }
            case 65809: {
                if (r.b > 0 && r.a(0, 0) != -1) {
                    this.p(r);
                }
                this.a.a();
            }
            case 68352: {
                try {
                    for (int n = 0; n < r.b; ++n) {
                        final aK ak2;
                        if ((ak2 = (aK)super.i.b(r.a(n, 0))) != null) {
                            final int a4 = r.a(n, 1);
                            final int a5 = r.a(n, 3);
                            final bH bh = this;
                            bh.g += a4 - ak2.d;
                            final bH bh2 = this;
                            bh2.l += a5 - ak2.c;
                            ak2.d = a4;
                            ak2.b = r.a(n, 2);
                            ak2.c = a5;
                            ak2.a = r.a(n, 4);
                            r.a(n, 6);
                            r.a(n, 8);
                            final bH bh3 = this;
                            final int a6;
                            bh3.h += (a6 = r.a(n, 10)) - ak2.h;
                            ak2.h = a6;
                            if (this.a != null) {
                                this.a.a(ak2);
                            }
                            if (ak2.a > this.c) {
                                this.c = ak2.a;
                            }
                        }
                    }
                    if (this.g > this.k) {
                        this.k = this.g;
                    }
                    if (this.a != null) {
                        this.a.b();
                    }
                }
                finally {
                    throw loadexception(java.lang.Throwable.class);
                }
            }
            case 67360: {
                try {
                    for (int n2 = 0; n2 < r.b; ++n2) {
                        final int a7 = r.a(n2, 0);
                        bv bv = (bv)super.m.b(a7);
                        if (r.a(n2, 63)) {
                            if (bv != null) {
                                super.m.b(a7);
                            }
                        }
                        else {
                            if (bv == null) {
                                bv = new bv(a7, r.a(n2, 0));
                                super.m.a(bv);
                            }
                            else {
                                bv.d = r.a(n2, 0);
                            }
                            bv.b = r.a(n2, 1);
                            bv.a = r.a(n2, 2);
                            bv.c = r.a(n2, 3);
                            bv.a = r.a(n2, 1);
                            bv.b = r.a(n2, 2);
                            bv.b = r.a(n2);
                        }
                    }
                }
                finally {
                    throw loadexception(java.lang.Throwable.class);
                }
            }
            case 68353: {
                (this = this).a = r.a(0, 0);
                final String a8 = r.a(0, 0);
                this.m = r.a(0, 2);
                this.k = r.a(0, 3);
                if (a8 != null) {
                    this.e = a8;
                }
                if (this.a != null) {
                    this.a.b();
                }
            }
            case 16844556: {
                try {
                    for (int n3 = 0; n3 < r.b; ++n3) {
                        final int a9 = r.a(n3, 0);
                        u u = (u)super.k.b(a9);
                        if (r.a(n3, 63)) {
                            super.k.b(a9);
                        }
                        else {
                            if (u == null) {
                                u = new u(a9, r.a(n3, 0));
                                super.k.a(u, a9);
                            }
                            else {
                                u.d = r.a(n3, 0);
                            }
                            u.b = r.a(n3);
                        }
                    }
                }
                finally {
                    throw loadexception(java.lang.Throwable.class);
                }
            }
            case 67338: {}
            case 67339: {
                try {
                    for (int n4 = 0; n4 < r.b; ++n4) {
                        final int a10 = r.a(n4, 0);
                        s s = (s)super.j.b(a10);
                        if (r.a(n4, 63)) {
                            if (s != null) {
                                super.j.b(a10);
                            }
                        }
                        else {
                            if (s == null) {
                                s = new s(a10, r.a(n4, 0));
                                super.j.a(s);
                            }
                            else {
                                s.d = r.a(n4, 0);
                            }
                            s.a = r.a(n4, 1);
                            s.a = r.a(n4, 2);
                            s.a = r.a(n4, 1);
                            s.b = r.a(n4);
                        }
                    }
                }
                finally {
                    throw loadexception(java.lang.Throwable.class);
                }
            }
            case 67841: {}
            case 65798: {
                (this = this).n = r.a(0, 0);
                r.a(1, 0);
                this.o = r.a(2, 0);
                this.e = (r.a(3, 0) == 0);
                r.a(4, 0);
            }
            case 67347: {
                try {
                    for (int n5 = 0; n5 < r.b; ++n5) {
                        final int a11 = r.a(n5, 0);
                        final bH bh4;
                        final bv bv2;
                        if ((bv2 = (bv)bh4.m.b(a11)) != null && bh4.a != null && bh4.a.isVisible() && bh4.a.a() != null) {
                            synchronized (((T)bh4.a.a()).a) {
                                final bv bv3 = (bv)((T)bh4.a.a()).b(a11);
                                final int a12 = ((T)bh4.a.a()).a.a((a)bv3);
                                if (r.a(n5, 1) == 1) {
                                    bv2.b(1);
                                    bv3.b(1);
                                }
                                else {
                                    bv2.c(1);
                                    bv3.c(1);
                                }
                                if (a12 == -1) {
                                    ((T)bh4.a.a()).a.c(bv3);
                                }
                                else {
                                    ((T)bh4.a.a()).a.a(bv3, a12);
                                }
                                ((cj)bh4.a.a()).d(bv3);
                            }
                        }
                    }
                }
                finally {
                    throw loadexception(java.lang.Throwable.class);
                }
            }
            case 66309: {
                if (r.a != null && r.a.length >= 2) {
                    this.a(r.a);
                }
            }
            case 67342: {
                try {
                    for (int n6 = 0; n6 < r.b; ++n6) {
                        final int a13 = r.a(n6, 0);
                        bn bn = (bn)super.p.b(a13);
                        if (r.a(n6, 63)) {
                            if (bn != null) {
                                super.p.b(a13);
                            }
                        }
                        else {
                            if (bn == null) {
                                bn = new bn(a13, r.a(n6, 0));
                                super.p.a(bn);
                            }
                            else {
                                bn.d = r.a(n6, 0);
                            }
                            bn.a = r.a(n6, 1);
                            bn.b = r.a(n6, 2);
                            bn.a = r.a(n6, 1);
                            bn.b = r.a(n6);
                        }
                    }
                }
                finally {
                    throw loadexception(java.lang.Throwable.class);
                }
            }
            case 67348: {
                r.a(0, 0);
                r.a(0, 1);
                r.a(0, 2);
                r.a(0, 3);
                r.a(0, 4);
                r.a(0, 0);
                r.a(0, 1);
                super.o = r.a(0, 2);
                try {
                    if (super.o != null && super.o.length() > 0) {
                        try {
                            final String string = "http://" + super.a.getHost() + "/DigiChat/rss/" + super.o;
                            BufferedReader bufferedReader = null;
                            try {
                                bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new URL(string + "z").openStream())));
                            }
                            catch (Exception ex2) {
                                try {
                                    bufferedReader = new BufferedReader(new InputStreamReader(new URL(string).openStream()));
                                }
                                catch (Exception ex3) {}
                            }
                            if (bufferedReader != null) {
                                bufferedReader.readLine();
                                bufferedReader.close();
                            }
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
                catch (Exception ex4) {}
            }
            case 67350: {
                this.d.setState(r.a(0, 0));
            }
            default: {
                super.a(r);
            }
        }
    }
    
    public final void a() {
        if (this.a != null) {
            this.a.b();
        }
    }
    
    public final void a(final URL url, final String s) {
        final AppletContext appletContext;
        (appletContext = super.a.getAppletContext()).showDocument(url, s);
        if (ce.b && ce.b == 1 && ce.c < 65792 && url.getProtocol().equalsIgnoreCase("http")) {
            appletContext.showDocument(url, s);
        }
    }
    
    public final void b() {
        this.a = new bg(super.a.a(), this);
    }
    
    protected final void b(final r r) {
        final int a = r.a(0, 1);
        if (r.a(0, 0) == 1 && a == 68096) {
            new bD(null, aS.a(1), aS.a(188), this).setVisible(true);
            this.a(this.a(58));
            return;
        }
        if (r.a(0, 0) == 1 && a == 67345) {
            new bD(null, aS.a(1), aS.a(188), this).setVisible(true);
            this.m();
            return;
        }
        super.b(r);
    }
    
    public final void c() {
        if (this.a != null) {
            this.a.dispose();
            this.a = null;
        }
        if (this.a != null) {
            this.a.dispose();
            this.a = null;
        }
        if (this.a != null) {
            this.a.dispose();
            this.a = null;
        }
        super.c();
    }
    
    public final void a(final Frame frame) {
        final Menu menu = new Menu(super.a.a);
        final MenuBar menuBar = new MenuBar();
        if (!"Admin".equals(super.f)) {
            menu.add(this.l = new MenuItem(aS.a(559)));
            menu.addSeparator();
        }
        for (int i = 1; i <= super.n.a(); ++i) {
            menu.add((MenuItem)super.n.b(i));
        }
        if (super.n.a() >= 1) {
            menu.addSeparator();
        }
        if ((super.a[0] & 0x3F4C00004080400L) != 0x0L) {
            menu.add(this.a = new MenuItem(aS.a(187)));
            menu.addSeparator();
        }
        if (!"Admin".equals(super.f)) {
            final Menu menu2;
            (menu2 = new Menu(aS.a(28))).add(this.a[0] = new CheckboxMenuItem(aS.a(549), true));
            menu2.add(this.a[1] = new CheckboxMenuItem(aS.a(550)));
            menu2.add(this.a[2] = new CheckboxMenuItem(aS.a(551), false));
            menu2.add(this.a[3] = new CheckboxMenuItem(aS.a(552), false));
            menu2.add(this.a[4] = new CheckboxMenuItem(aS.a(553), false));
            menu.add(menu2);
            menu.addSeparator();
            if (this.a(25) || this.a(24)) {
                final Menu menu3 = new Menu(aS.a(571));
                this.a = new CheckboxMenuItem(aS.a(570), this.a(18));
                if (this.a(25)) {
                    menu3.add(this.a);
                }
                if (this.a(24)) {
                    this.b = new CheckboxMenuItem(aS.a(9), this.a(23) && !this.a(18));
                    this.c = new CheckboxMenuItem(aS.a(569), !this.a(23) && !this.a(18));
                    menu3.add(this.b);
                    menu3.add(this.c);
                }
                menu.add(menu3);
                menu.addSeparator();
            }
        }
        if ("Admin".equals(super.f) && (super.a[0] & 0xC000000000000L) != 0x0L) {
            menu.add(this.b = new MenuItem(aS.a(177)));
        }
        if (this.a(42)) {
            menu.add(this.f = new MenuItem(aS.a(178)));
        }
        if (this.a(70)) {
            menu.add(this.e = new MenuItem(aS.a(655)));
        }
        if (this.a(52)) {
            menu.add(this.h = new MenuItem(aS.a(574)));
        }
        if (this.a(52) || this.a(60)) {
            menu.add(this.d = new CheckboxMenuItem(aS.a(641), false));
        }
        if (this.a(52) || this.a(60)) {
            menu.add(this.k = new MenuItem(aS.a(179)));
        }
        if (this.a(45)) {
            menu.add(this.g = new MenuItem(aS.a(180)));
        }
        if (this.a(13)) {
            menu.add(this.j = new MenuItem(aS.a(181)));
        }
        if (this.a(59) || this.a(60)) {
            (this.i = new MenuItem((this.a(59) ? aS.a(182) : aS.a(183)) + "...")).enable(false);
            menu.add(this.i);
        }
        menu.addSeparator();
        menu.add(this.d = new MenuItem(aS.a(11)));
        menu.add(this.c = new MenuItem(aS.a(12)));
        menuBar.add(menu);
        if (frame != null) {
            frame.setMenuBar(menuBar);
        }
    }
    
    public final boolean a(final Event event) {
        if (event.target == this.c) {
            this.j();
        }
        else if (event.target == this.e) {
            final e e;
            (e = new e(this, -999)).setTitle(e.getTitle() + ": " + super.d);
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
            super.t.a(e, 0);
            if (!e.isShowing()) {
                e.setVisible(true);
            }
        }
        else if (event.target == this.d) {
            this.f(0);
        }
        else if (event.target == null) {
            this.g();
        }
        else if (event.target == this.b) {
            this.d();
        }
        else if (event.target == this.a) {
            if (super.a.a() != null) {
                super.a.a().setCursor(3);
            }
            (this.a = new bg(super.a.a(), this)).setVisible(true);
            this.a.toFront();
            if (super.a.a() != null) {
                super.a.a().setCursor(0);
            }
        }
        else if (event.target == this.f) {
            this.e();
        }
        else if (event.target == this.g) {
            this.a(false);
        }
        else if (event.target == this.h) {
            this.m();
        }
        else if (event.target == this.j) {
            new aq((this.a != null) ? this.a : super.a.a(), this).setVisible(true);
        }
        else if (event.target == this.k) {
            if (this.a == null || !this.a.isShowing()) {
                this.a = new aN(super.a.a(), this);
            }
            if (!this.a.isShowing()) {
                this.a.setVisible(true);
            }
            else {
                this.a.requestFocus();
            }
        }
        else if (event.target == this.l) {
            this.b(super.n);
        }
        else if (event.target == this.d) {
            final r r;
            (r = new r(67350, 1)).e = super.i;
            this.o(r);
        }
        else if (event.target == this.a) {
            if (this.a.getState()) {
                this.h(1);
            }
            this.a.setState(true);
            this.b.setState(false);
            this.c.setState(false);
        }
        else if (event.target == this.b) {
            if (this.b.getState()) {
                this.h(2);
            }
            this.a.setState(false);
            this.b.setState(true);
            this.c.setState(false);
        }
        else if (event.target == this.c) {
            if (this.c.getState()) {
                this.h(3);
            }
            this.a.setState(false);
            this.b.setState(false);
            this.c.setState(true);
        }
        else if (event.target == this.i) {
            if (this.a != null) {
                this.a.setVisible(true);
            }
        }
        else if (super.n.a(event.target)) {
            this.a((URL)super.o.a(super.n.a(event.target)), "_blank");
        }
        if (event.target instanceof CheckboxMenuItem) {
            for (int i = 0; i < this.a.length; ++i) {
                if (event.target == this.a[i]) {
                    if (!this.a[i].getState()) {
                        this.a[i].setState(true);
                    }
                    else {
                        this.a[i].setState(true);
                        this.g(i);
                    }
                }
                else {
                    this.a[i].setState(false);
                }
            }
        }
        return true;
    }
    
    private void b(final String s) {
        final Object[] array = { "window.focus(); setTimeout(\"window.external.AddFavorite('" + s + "', window.document.title);\", 100);" };
        try {
            JSObject.getWindow((Applet)super.a).call("eval", array);
        }
        catch (Exception ex) {}
    }
    
    public final void c(final r r) {
        super.c(r);
        if (super.f.equals("Admin")) {
            this.a = new be(this);
        }
    }
    
    public final void d(final r r) {
        if (this.q != null) {
            try {
                synchronized (super.d) {
                    for (int i = 0; i < super.d.a(); ++i) {
                        final ak ak;
                        if ((ak = (ak)super.d.a(i)).d.equals(this.q)) {
                            super.s = ak.i;
                            break;
                        }
                    }
                }
            }
            finally {
                throw loadexception(java.lang.Throwable.class);
            }
        }
        final bH bh;
        if (bh.a != null) {
            bh.a.setVisible(true);
        }
        else {
            bh.d(r);
        }
        if (bh.a(58)) {
            bh.a(true);
        }
        final r r2;
        (r2 = new r(65799, 1)).a(0, 0, bh.i);
        bh.o(r2);
        final r r3;
        (r3 = new r(65796, 1)).a(0, 0, bh.i);
        bh.o(r3);
        final r r4;
        (r4 = new r(65798, 1)).a(0, 0, bh.i);
        bh.o(r4);
    }
    
    protected final void e(final r r) {
        super.e(r);
        if ((this.a(59) || this.a(60)) && (super.b == -999 || !((aP)super.d.b(super.b)).a(61))) {
            this.i.enable(false);
        }
    }
    
    public final void d() {
        this.a.setCursor(3);
        if (this.a == null) {
            this.a = new l(this.a, this);
        }
        this.a.setVisible(true);
        this.a.toFront();
        this.a.setCursor(0);
    }
    
    public final void e() {
        boolean b;
        if ("Admin".equals(super.f)) {
            b = false;
        }
        else {
            b = true;
        }
        new O((this.a == null) ? super.a.a() : this.a, this, 0, b ? 1 : 0).setVisible(true);
    }
    
    public final void a(final boolean b) {
        new af((this.a == null) ? super.a.a() : this.a, this, b).setVisible(true);
        this.b(58, b);
    }
    
    private void m() {
        new bS((this.a == null) ? super.a.a() : this.a, this).setVisible(true);
    }
    
    public final Image a(final URL url) {
        if (super.f && !ce.d) {
            return super.a.getImage(url);
        }
        return Toolkit.getDefaultToolkit().getImage(url);
    }
    
    public final void f() {
        if (super.n != null && super.n.length() > 0) {
            this.b(super.n);
        }
    }
    
    private Object a(final Object[] array) {
        try {
            return JSObject.getWindow((Applet)super.a).call("eval", array);
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    public final void g() {
        super.g();
        System.exit(0);
    }
    
    public final void h() {
        if (super.f) {
            super.a.a();
        }
        this.a = null;
    }
    
    public final void a(final aU au) {
        if (this.a == null) {
            this.a = new cc(this);
            final Dimension screenSize;
            int n = (screenSize = Toolkit.getDefaultToolkit().getScreenSize()).width / 2 - 20;
            int n2 = screenSize.height / 2 - 20;
            if (n > 400) {
                n = 400;
            }
            if (n2 > 300) {
                n2 = 300;
            }
            this.a.reshape(2, n2 + 10, n, n2);
            this.i.enable(true);
        }
        this.a.a.a(au);
        if (!this.a.isShowing()) {
            this.a.setVisible(true);
        }
    }
    
    public final void a(final byte[] array) {
        if (this.a != null) {
            Label_0148: {
                final cc a2;
                final boolean a;
                if (!(a = (a2 = this.a).a.a(59)) || array[3] != 0) {
                    if (!a) {
                        if (array[3] == 1) {
                            break Label_0148;
                        }
                    }
                    try {
                        for (int i = a2.a.c - 1; i >= 0; --i) {
                            if (a2.a.a(i).a[0] == array[0] && a2.a.a(i).a[1] == array[1]) {
                                if (array[2] == 2) {
                                    a2.a.a(i);
                                }
                                else {
                                    a2.a.a(i).e = (array[2] == 1);
                                }
                            }
                        }
                    }
                    catch (Exception ex) {}
                }
            }
            this.a.a.c();
        }
    }
    
    public final void a(final aU au, final aZ az) {
        new cd(this.a, this, au, az);
        if (this.a != null) {
            this.a.a.c();
        }
    }
    
    public bH(bU a) {
        this.a = new CheckboxMenuItem[5];
        this.c = 0L;
        super.a = a;
        super.f = true;
        try {
            a = (bU)this;
            String s = super.a.getParameter("siteID");
            final String parameter = ((cs)a).a.getParameter("nickname");
            final String parameter2 = ((cs)a).a.getParameter("email");
            final String parameter3 = ((cs)a).a.getParameter("realname");
            final String parameter4 = ((cs)a).a.getParameter("gender");
            final String parameter5 = ((cs)a).a.getParameter("url");
            final String parameter6 = ((cs)a).a.getParameter("exitmessage");
            final String parameter7 = ((cs)a).a.getParameter("comments");
            String s2 = ((cs)a).a.getParameter("iconID");
            final String parameter8 = ((cs)a).a.getParameter("roomID");
            final String parameter9 = ((cs)a).a.getParameter("blockProfile");
            final String parameter10 = ((cs)a).a.getParameter("openProfileURL");
            final String parameter11 = ((cs)a).a.getParameter("age");
            final String parameter12 = ((cs)a).a.getParameter("helpURL");
            ((cs)a).a.getParameter("buddyList");
            final String parameter13 = ((cs)a).a.getParameter("floodControl");
            final String parameter14 = ((cs)a).a.getParameter("ports");
            final String parameter15 = ((cs)a).a.getParameter("autoPopup");
            final String parameter16 = ((cs)a).a.getParameter("noLogoutButton");
            final String parameter17 = ((cs)a).a.getParameter("flaggedBackground");
            ((cs)a).a.getParameter("signed");
            if (parameter14 != null) {
                ((cs)a).a.a.removeAllElements();
                ((cs)a).a.a(parameter14);
            }
            else {
                ((cs)a).a.a.removeAllElements();
                ((cs)a).a.a(((cs)a).a.a);
            }
            if (((cs)a).a.getParameter("HttpServlet") != null) {
                ((cs)a).a.getParameter("HttpServlet");
            }
            if (s == null) {
                s = ((cs)a).a.getParameter("port");
            }
            if (s2 == null) {
                s2 = ((cs)a).a.getParameter("icon");
            }
            if (parameter8 == null) {
                ((bH)a).q = ((cs)a).a.getParameter("room");
            }
            else {
                try {
                    ((cs)a).s = Integer.parseInt(parameter8);
                }
                catch (NumberFormatException ex2) {}
            }
            ((cs)a).m = ((cs)a).a.getCodeBase().getHost();
            if (parameter3 != null) {
                ((cs)a).h = parameter3;
            }
            if (parameter2 != null) {
                ((cs)a).j = parameter2;
            }
            if (parameter5 != null) {
                ((cs)a).i = parameter5;
            }
            if (parameter6 != null) {
                ((cs)a).k = parameter6;
            }
            if (parameter7 != null) {
                ((cs)a).l = parameter7;
            }
            if ("male".equals(parameter4)) {
                ((cs)a).x = 1;
            }
            else if ("female".equals(parameter4)) {
                ((cs)a).x = 0;
            }
            if ("true".equals(parameter9)) {
                ((cs)a).q = true;
            }
            if ("true".equals(parameter10)) {
                ((cs)a).s = true;
            }
            if ("true".equals(parameter16)) {
                ((cs)a).g = true;
            }
            if ("true".equals(parameter15)) {
                ((cs)a).p = true;
            }
            if ("true".equalsIgnoreCase(parameter17)) {
                ((cs)a).t = true;
            }
            if (s2 != null) {
                try {
                    ((cs)a).t = Integer.parseInt(s2);
                }
                catch (NumberFormatException ex3) {}
            }
            if (parameter11 != null) {
                try {
                    ((cs)a).w = Integer.parseInt(parameter11);
                }
                catch (NumberFormatException ex4) {}
            }
            if (parameter12 != null) {
                try {
                    ((cs)a).b = new URL(parameter12);
                }
                catch (MalformedURLException ex5) {}
            }
            ((cs)a).p = 250;
            if (parameter13 != null) {
                try {
                    ((cs)a).p = Integer.parseInt(parameter13);
                }
                catch (NumberFormatException ex6) {}
            }
            if (((cs)a).a.getParameter("MenuItem1") != null) {
                for (int n = 1; ((cs)a).a.getParameter("MenuItem" + n) != null; ++n) {
                    ((cs)a).n.a(new MenuItem(((cs)a).a.getParameter("MenuItem" + n)), n);
                    try {
                        ((cs)a).o.a(new URL(((cs)a).a.getParameter("MenuLocation" + n)), n);
                    }
                    catch (MalformedURLException ex7) {
                        ((cs)a).n.b(n);
                    }
                }
            }
            if (s != null) {
                try {
                    ((cs)a).v = Integer.parseInt(s);
                }
                catch (NumberFormatException ex8) {
                    ((cs)a).v = -999;
                }
            }
            ((U)a).d = parameter;
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        super.o = true;
    }
}
