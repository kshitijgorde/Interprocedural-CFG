// 
// Decompiled by Procyon v0.5.30
// 

package com.photochannel.easyUploader;

import z.M;
import z.am;
import java.io.IOException;
import z.au;
import z.Z;
import z.aA;
import java.awt.Image;
import java.awt.Dimension;
import java.util.Hashtable;
import java.util.List;
import java.util.ArrayList;
import z.aT;
import z.C;
import z.ad;
import z.aU;
import java.util.Observable;
import z.ah;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import java.security.AccessControlException;
import java.net.MalformedURLException;
import javax.swing.ImageIcon;
import z.bg;
import java.util.TimerTask;
import java.util.Timer;
import java.awt.Component;
import java.awt.LayoutManager;
import z.ay;
import z.af;
import z.R;
import z.ax;
import z.V;
import z.z;
import z.G;
import java.io.File;
import z.ab;
import z.aO;
import z.aS;
import java.awt.Container;
import java.io.FileFilter;
import z.aj;
import z.w;
import java.util.concurrent.LinkedBlockingQueue;
import java.net.URL;
import java.util.Observer;

public class b implements Runnable, Observer
{
    private c a;
    private URL b;
    private EasyUploader c;
    private AppletParams d;
    private boolean e;
    private int f;
    private int g;
    private long h;
    private long i;
    private g j;
    private boolean k;
    private boolean l;
    private boolean m;
    private Thread n;
    private LinkedBlockingQueue o;
    private f p;
    private w q;
    private aj r;
    private FileFilter s;
    private Container t;
    private aS u;
    private aO v;
    private ab w;
    private int x;
    private boolean y;
    private File z;
    private static /* synthetic */ boolean A;
    
    public b() {
        this.a = com.photochannel.easyUploader.c.a;
        this.e = false;
        this.m = false;
        this.o = new LinkedBlockingQueue();
        this.y = false;
        this.z = null;
    }
    
    private d j() {
        return this.c.a();
    }
    
    private int k() {
        System.out.println("width: " + this.t.getWidth());
        if (this.t.getWidth() < 1 || this.t == null) {
            return 592;
        }
        return this.t.getWidth();
    }
    
    private int l() {
        System.out.println("height: " + this.t.getHeight());
        if (this.t.getHeight() < 1 || this.t == null) {
            return 420;
        }
        return this.t.getHeight();
    }
    
    public final void a(final AppletParams d, final EasyUploader c, final URL b, final Container t) {
        if (!b.A && d == null) {
            throw new AssertionError();
        }
        if (!b.A && c == null) {
            throw new AssertionError();
        }
        if (!b.A && b == null) {
            throw new AssertionError();
        }
        if (!b.A && t == null) {
            throw new AssertionError();
        }
        this.d = d;
        this.c = c;
        this.b = b;
        this.t = t;
        final long currentTimeMillis = System.currentTimeMillis();
        System.out.println("PNI Uploader Applet Version: 1.4.8_4");
        System.out.println("OS == " + System.getProperty("os.name"));
        System.out.println("Width = " + this.k());
        System.out.println("Height = " + this.l());
        if (!n()) {
            this.a(c.b);
            return;
        }
        try {
            if (this.d.getDebug()) {
                System.out.println("** DEBUG mode enabled **");
                this.d.a();
                if (this.d.getDebugLogFile() == null) {
                    this.p = new f(null);
                }
                else {
                    String s;
                    if ((s = this.d.getDebugLogFile()).startsWith("c:/")) {
                        s = System.getProperty("user.home") + s.substring(2);
                    }
                    System.out.println("Log transcript to file: " + s);
                    this.p = new f(s);
                }
            }
            else {
                System.out.println("DEBUG mode is OFF");
                this.p = new f("SILENT");
            }
            final String speedTest;
            if ((speedTest = this.d.getSpeedTest()) != null) {
                this.e = true;
                this.f = Integer.parseInt(speedTest);
                System.out.println("SPEED TEST MODE: number of trials = " + this.f);
            }
            System.out.println("PNI Uploader Applet Version: 1.4.8_4");
            System.out.println("OS = " + System.getProperty("os.name"));
            G.a(EasyUploader.class.getResourceAsStream("/res/config.xml"), EasyUploader.class.getResourceAsStream("/res/config.css"), EasyUploader.class.getResourceAsStream("/res/string-table.xml"));
            G.a(this.d.getLanguage());
            G.a(b);
            z.z.a();
            V.a(this.m());
            final String reducePhotosDefault = this.d.getReducePhotosDefault();
            this.l = reducePhotosDefault.equals("disable");
            if (this.l) {
                this.k = false;
            }
            else {
                this.k = Boolean.parseBoolean(reducePhotosDefault);
            }
            final ax ax;
            (ax = new ax(this.d)).addObserver(this);
            (this.q = new w(1.0f * this.d.getImageDownscalingJpegQFactor() / 100.0f, this.d.getImageDownscalingMinDimensionPx1(), this.d.getImageDownscalingMinDimensionPx2(), ax)).addObserver(this);
            this.r = new aj(this.q);
            final bg b2;
            this.s = new R((b2 = G.b("//functional")).c("supportedImageTypes"));
            af.a(G.b("//zoomWindow").e("maxSize"), this.q);
            final int b3 = G.b("//fileDialog").b("thumbnailSize");
            final ImageIcon a = V.a(G.b("//fileDialog").j("unavailableImage"));
            this.m = (b2.a("disableDragAndDrop") && b2.k("disableDragAndDrop"));
            ay.a(this.q, b3, this.s, a);
            V.a(this);
            p();
            this.t.setLayout(null);
            this.u = new aS(this.q, G.b("//ui/frame"), this.k(), this.l());
            this.t.add(this.u);
            this.v = new aO(this, this.q, G.b("//thumbnailView"), this.u.a());
            this.t.add(this.v);
            this.w = new ab(this, this.u.a(), this.r, G.b("//listView"));
            this.t.add(this.w);
            this.u.a(this.w);
            this.t.setComponentZOrder(this.v, 0);
            this.t.setComponentZOrder(this.w, 1);
            this.t.setComponentZOrder(this.u, 2);
            this.u.c();
            final bg b4;
            if ((b4 = G.b("//ui")).a("viewmode") && b4.c("viewmode").equals("list")) {
                new Timer(true).schedule(new k(this), 500L);
            }
            else {
                this.a(1);
            }
            new Timer(true).schedule(new j(this), 1500L);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println("init time = " + (System.currentTimeMillis() - currentTimeMillis) + " ms");
    }
    
    private ClassLoader m() {
        try {
            return new e(new URL[] { new URL(this.b, "Tiff.jar"), new URL(this.b, "batik-awt-util.jar"), new URL(this.b, "batik-codec.jar") });
        }
        catch (MalformedURLException ex2) {
            final MalformedURLException ex = ex2;
            ex2.printStackTrace();
            throw new RuntimeException(ex);
        }
    }
    
    public final void a() {
        this.t.setBounds(0, 0, this.k(), this.l());
        if (this.a == com.photochannel.easyUploader.c.b) {
            return;
        }
        (this.n = new Thread(this)).setName("PNI-page-event-listener");
        this.n.start();
        this.a(com.photochannel.easyUploader.c.c);
    }
    
    public final void b() {
        V.a((ClassLoader)null);
        try {
            Thread.sleep(400L);
        }
        catch (InterruptedException ex) {}
        System.out.println("1 -->" + this.p);
        if (this.p != null) {
            final String a = this.p.a();
            if (this.d.c()) {
                this.q.l(a);
            }
        }
        this.q = null;
        this.r = null;
        this.s = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.z = null;
        System.out.println("Destroying applet: Say g'night, Gracie");
    }
    
    public static void a(final String s) {
        System.out.println(s);
    }
    
    private static boolean n() {
        try {
            new File("/").exists();
            return true;
        }
        catch (AccessControlException ex2) {
            return false;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return true;
        }
    }
    
    private void a(final c a) {
        if (!com.photochannel.easyUploader.b.A && a == this.a) {
            throw new AssertionError();
        }
        System.out.println("chaging state to: " + a);
        if ((this.a = a) == com.photochannel.easyUploader.c.c) {
            if (!this.m) {
                ay.b();
            }
            else {
                ay.a();
            }
        }
        else if (a == com.photochannel.easyUploader.c.d) {
            this.q.f();
        }
        else if (a == com.photochannel.easyUploader.c.e) {
            this.v.a();
            this.v.invalidate();
            this.v.validate();
            this.v.repaint();
            this.w.d();
            this.w.repaint();
            ay.a();
        }
        this.j().a(a.toString());
    }
    
    private void a(final int x) {
        if (!com.photochannel.easyUploader.b.A && x != 1 && x != 2) {
            throw new AssertionError();
        }
        if ((this.x = x) == 1) {
            this.w.setVisible(false);
            this.v.setVisible(true);
        }
        else if (x == 2) {
            this.v.setVisible(false);
            this.w.a();
            this.w.setVisible(true);
        }
        this.u.a(x);
    }
    
    public final void c() {
        this.n = null;
        if (this.q != null) {
            this.q.c();
        }
        this.o.add(new g(this, com.photochannel.easyUploader.a.a));
    }
    
    private static URL o() {
        try {
            return new URL(G.b(), "archive_client_log.ashx");
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public void run() {
        this.t.repaint();
        while (this.n != null) {
            try {
                final g g = this.o.take();
                System.out.println("rec'd message: " + g.a + "\n");
                switch (com.photochannel.easyUploader.i.a[g.a.ordinal()]) {
                    case 5: {}
                    case 6: {
                        if (!com.photochannel.easyUploader.b.A && this.a != com.photochannel.easyUploader.c.c) {
                            throw new AssertionError();
                        }
                        this.q.a();
                        continue;
                    }
                    case 7: {
                        if (!com.photochannel.easyUploader.b.A && this.a != com.photochannel.easyUploader.c.c) {
                            throw new AssertionError();
                        }
                        if (this.e) {
                            this.h = System.currentTimeMillis();
                        }
                        this.q.a((URL)g.b.get("batchInitURL"), (URL)g.b.get("errorLoggingURL"), o(), this.k, (String)g.b.get("appContextID"), (String)g.b.get("userID"), (String)g.b.get("albumID"), (String)g.b.get("albumName"));
                        this.a(com.photochannel.easyUploader.c.e);
                        continue;
                    }
                    case 8: {
                        if (!com.photochannel.easyUploader.b.A && this.a != com.photochannel.easyUploader.c.e) {
                            throw new AssertionError();
                        }
                        this.a(com.photochannel.easyUploader.c.h);
                        this.q.d();
                        continue;
                    }
                    case 9: {
                        if (this.e) {
                            final long n = System.currentTimeMillis() - this.h;
                            this.i += n;
                            System.out.println("Speed trial: Completed trial#" + this.g + ": " + n + " ms");
                            if (++this.g < this.f) {
                                this.a(com.photochannel.easyUploader.c.c);
                                this.o.add(this.j);
                                continue;
                            }
                            System.out.println("Speed trial: total time = " + this.i + " ms");
                            System.out.println("Speed trial: average time = " + this.i / this.f + " ms");
                            continue;
                        }
                        else {
                            if (!com.photochannel.easyUploader.b.A && this.a != com.photochannel.easyUploader.c.e) {
                                throw new AssertionError();
                            }
                            this.a(com.photochannel.easyUploader.c.f);
                            this.j().a((String)g.b.get("batchID"), (String)g.b.get("userID"));
                            continue;
                        }
                        break;
                    }
                    case 10: {
                        if (!com.photochannel.easyUploader.b.A && this.a != com.photochannel.easyUploader.c.e) {
                            throw new AssertionError();
                        }
                        this.a(com.photochannel.easyUploader.c.h);
                        continue;
                    }
                    default: {
                        SwingUtilities.invokeLater(new l(this, g));
                        continue;
                    }
                }
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private static void p() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void b(final String s, final String s2) {
        if (!com.photochannel.easyUploader.b.A && (s == null || s.length() <= 0)) {
            throw new AssertionError();
        }
        if (!com.photochannel.easyUploader.b.A && (s2 == null || s2.length() <= 0)) {
            throw new AssertionError();
        }
        System.out.println("launchErrorDialog: " + s2);
        JOptionPane.showMessageDialog(this.t, s2, s, 0);
    }
    
    public final void d() {
        if (this.a == com.photochannel.easyUploader.c.c) {
            this.o.add(new g(this, com.photochannel.easyUploader.a.b));
        }
    }
    
    public final void e() {
        if (this.a == com.photochannel.easyUploader.c.c) {
            this.o.add(new g(this, com.photochannel.easyUploader.a.h));
        }
    }
    
    public final void f() {
        if (this.a == com.photochannel.easyUploader.c.e) {
            this.o.add(new g(this, com.photochannel.easyUploader.a.f));
        }
    }
    
    public final void g() {
        if (this.a == com.photochannel.easyUploader.c.c || this.a == com.photochannel.easyUploader.c.e) {
            this.o.add(new g(this, com.photochannel.easyUploader.a.d));
        }
    }
    
    public final void h() {
        if (this.a == com.photochannel.easyUploader.c.c || this.a == com.photochannel.easyUploader.c.e) {
            this.o.add(new g(this, com.photochannel.easyUploader.a.c));
        }
    }
    
    public final void a(final String s, final String s2) {
        if (this.a != com.photochannel.easyUploader.c.c) {
            return;
        }
        if (this.q.e() == 0) {
            return;
        }
        final g j;
        (j = new g(this, com.photochannel.easyUploader.a.e)).b.put("appContextID", this.d.getAppContextID());
        j.b.put("userID", this.d.getUserID());
        if (s != null) {
            j.b.put("albumID", s);
        }
        if (s2 != null) {
            j.b.put("albumName", s2);
        }
        try {
            j.b.put("batchInitURL", new URL(this.d.getBatchInitURL()));
            j.b.put("errorLoggingURL", new URL(this.d.getErrorLoggingURL()));
        }
        catch (MalformedURLException ex2) {
            final MalformedURLException ex = ex2;
            ex2.printStackTrace();
            throw new RuntimeException(ex);
        }
        if (this.e) {
            this.j = j;
        }
        this.o.add(j);
    }
    
    public final int i() {
        return this.q.e();
    }
    
    public final BufferedImage a(final int n, final int n2) {
        if (!com.photochannel.easyUploader.b.A && (n <= 0 || n2 <= 0)) {
            throw new AssertionError();
        }
        try {
            if (System.getProperty("os.name").startsWith("Mac")) {
                return new BufferedImage(n, n2, 1);
            }
            return (BufferedImage)this.t.createImage(n, n2);
        }
        catch (OutOfMemoryError outOfMemoryError) {
            throw new ah();
        }
    }
    
    public void update(final Observable observable, final Object o) {
        if (o instanceof aU) {
            if (this.a == com.photochannel.easyUploader.c.e) {
                this.o.add(new g(this, com.photochannel.easyUploader.a.g));
            }
        }
        else if (o instanceof ad) {
            if (this.a == com.photochannel.easyUploader.c.e) {
                final ad ad = (ad)o;
                final g g;
                (g = new g(this, com.photochannel.easyUploader.a.i)).b.put("batchID", ad.a);
                g.b.put("userID", ad.b);
                this.o.add(g);
            }
        }
        else if (o instanceof C && this.a == com.photochannel.easyUploader.c.e) {
            this.o.add(new g(this, com.photochannel.easyUploader.a.j));
        }
    }
    
    static {
        b.A = !b.class.desiredAssertionStatus();
    }
}
