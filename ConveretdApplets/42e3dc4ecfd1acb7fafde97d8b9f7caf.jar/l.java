import java.util.ArrayList;
import java.util.Iterator;
import java.net.URLEncoder;
import java.awt.event.ActionListener;
import java.net.URLConnection;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedInputStream;
import java.net.URL;
import au.com.rocketdog.project.awc.applet.Main;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Vector;
import java.util.Map;
import java.util.List;

// 
// Decompiled by Procyon v0.5.30
// 

public class l
{
    private static List a;
    private Map b;
    public Vector c;
    public boolean d;
    public boolean e;
    public boolean f;
    public boolean g;
    public boolean h;
    private static l i;
    
    private l() {
        this.b = new HashMap();
        this.c = new Vector();
        this.d = true;
        this.e = true;
        this.f = true;
        this.g = true;
        this.h = true;
    }
    
    public int a() {
        return this.b.size();
    }
    
    public static synchronized l b() {
        if (l.i == null) {
            l.i = new l();
        }
        return l.i;
    }
    
    public synchronized void c() {
        final n b = n.b();
        int size = this.b.size();
        if (size > b.a()) {
            final Object[] array = this.b.values().toArray();
            for (int n = 0; n < array.length && size > b.a(); --size, ++n) {
                ((be)array[n]).g();
            }
        }
    }
    
    public synchronized void a(final int n) {
        final Object[] array = this.b.values().toArray();
        for (int i = 0; i < array.length; ++i) {
            final be be = (be)array[i];
            if (be.q() == n) {
                be.g();
                break;
            }
        }
    }
    
    public synchronized void b(final int n) {
        this.a(n);
        final h f = h.f();
        final Enumeration elements = f.ab.elements();
        while (elements.hasMoreElements()) {
            final ba ba = elements.nextElement();
            if (ba.q() == n) {
                this.c.add(ba.s());
                f.b(ba);
                break;
            }
        }
    }
    
    public static void c(final int n) {
        try {
            new BufferedReader(new InputStreamReader(new BufferedInputStream(new URL("http://" + Main.b + "/awc/servlet/dispatch?CMD=cmd.broadcaster.buddies&bid=" + n.b().y() + "&id=" + n + "&add=true").openStream()))).close();
        }
        catch (Exception ex) {
            b.a(ex, 3);
        }
    }
    
    public static int a(final int n, final boolean b) {
        try {
            final n b2 = n.b();
            URL url;
            if (b) {
                url = new URL("http://" + Main.b + "/awc/servlet/req?bid=" + n + "&id=" + b2.y() + "&p=true");
            }
            else {
                url = new URL("http://" + Main.b + "/awc/servlet/req?bid=" + n + "&id=" + b2.y());
            }
            final URLConnection openConnection = url.openConnection();
            openConnection.setRequestProperty("User-Agent", "AWCRequester");
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(openConnection.getInputStream())));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equalsIgnoreCase("0")) {
                    bufferedReader.close();
                    return 0;
                }
                if (line.equalsIgnoreCase("1")) {
                    bufferedReader.close();
                    return 1;
                }
                if (line.equalsIgnoreCase("ERROR")) {
                    bufferedReader.close();
                    return 3;
                }
                if (line.equalsIgnoreCase("2")) {
                    bufferedReader.close();
                    return 2;
                }
            }
        }
        catch (Exception ex) {
            b.a(ex, 3);
        }
        return 1;
    }
    
    public synchronized void a(final int n, final String s, final boolean b, final int n2, final int n3) {
        final n b2 = n.b();
        final be be = this.b.get((s + "_" + n3).toLowerCase());
        if (be != null) {
            be.toFront();
        }
        else {
            b.a("name = " + b2.z(), 3);
            b.a("membership = " + b2.h(), 3);
            b.a("sitecode = " + b2.s(), 3);
            b.a("viewing cams = " + this.b.size(), 3);
            b.a("max cams = " + b2.a(), 3);
            if (this.b.size() == b2.a() && b2.h() == 0) {
                final al al = new al(Main.h(), Main.p.a("dialog.goldsilverupgrade"), 400, 100);
                al.a.addActionListener(new am());
                al.setVisible(true);
                return;
            }
            if (this.b.size() == b2.a() && b2.h() < 2) {
                final al al2 = new al(Main.h(), Main.p.a("dialog.goldupgrade"), 400, 100);
                al2.a.addActionListener(new am());
                al2.setVisible(true);
                return;
            }
            if (this.b.size() == b2.a() && b2.h() < 3) {
                new bl(Main.h(), Main.p.a("cams.maxallowed") + " 15").setVisible(true);
                return;
            }
            if (b2.w() || this.b.size() < b2.a()) {
                final be be2 = new be(n, s, b, n2, n3);
                this.b.put(be2.toString(), be2);
                be2.show();
            }
            else {
                final al al3 = new al(Main.h(), Main.p.a("dialog.goldsilverupgrade"), 400, 100);
                al3.a.addActionListener(new am());
                al3.setVisible(true);
            }
        }
    }
    
    public static boolean a(final int n, final String s, final int n2, final int n3) {
        final n b = n.b();
        final StringBuffer sb = new StringBuffer();
        final StringBuffer sb2 = new StringBuffer();
        try {
            sb2.append("http://");
            sb2.append(Main.b);
            sb2.append("/awc/servlet/camop?ban=");
            sb2.append(n3);
            sb2.append("&task=");
            sb2.append(n2);
            sb2.append("&bid=");
            sb2.append(n);
            sb2.append("&id=");
            sb2.append(b.y());
            sb2.append("&session=");
            sb2.append(URLEncoder.encode(n.b().f(), "UTF-8"));
            sb2.append("&mess=");
            sb2.append(URLEncoder.encode(s, "UTF-8"));
            final URLConnection openConnection = new URL(sb2.toString()).openConnection();
            openConnection.setRequestProperty("User-Agent", "AWCRequester");
            final BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new BufferedInputStream(openConnection.getInputStream())));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
                if (line.equalsIgnoreCase("DONE")) {
                    bufferedReader.close();
                    return true;
                }
                if (line.equalsIgnoreCase("ERROR")) {
                    bufferedReader.close();
                    return false;
                }
            }
        }
        catch (Exception ex) {
            b.a(ex, 3);
        }
        return false;
    }
    
    public ba a(final String s) {
        return h.f().ab.get(s);
    }
    
    public synchronized boolean b(final String s) {
        return this.b.containsKey(s);
    }
    
    public static synchronized void d() {
        if (l.i != null) {
            l.i.b.clear();
            l.i.b = null;
            l.i = null;
        }
        l.a.clear();
        m.b();
    }
    
    public void b(final int n, final String s, final boolean b, final int n2, final int n3) {
        this.a(n, s, b, n2, n3);
    }
    
    public synchronized void e() {
        for (int i = 0; i < l.a.size(); ++i) {
            this.a((bb)l.a.get(i));
        }
    }
    
    public void a(final bb bb) {
        switch (bb.w()) {
            case 1: {
                if (!this.d) {
                    bb.b(true);
                    break;
                }
                bb.b(false);
                break;
            }
            case 2: {
                if (!this.f) {
                    bb.b(true);
                    break;
                }
                bb.b(false);
                break;
            }
            case 6: {
                if (!this.g) {
                    bb.b(true);
                    break;
                }
                bb.b(false);
                break;
            }
            case 0: {
                if (!this.h) {
                    bb.b(true);
                    break;
                }
                bb.b(false);
                break;
            }
        }
        bb.p();
    }
    
    public synchronized void c(final String s) {
        this.b.remove(s);
    }
    
    public synchronized void a(final int n, final boolean b, final boolean b2, final int n2) {
        for (final bb bb : l.a) {
            if (bb.q() == n && bb.j() == n2) {
                bb.a(b2);
                bb.d(b);
                bb.c(this.b(bb.s()));
                bb.p();
            }
        }
    }
    
    public synchronized void a(final String s, final int n, final int n2, final boolean b, final int n3, final boolean b2, final boolean b3, final int n4) {
        for (final bb bb : l.a) {
            if (bb.q() == n && bb.j() == n4) {
                bb.a(b2);
                bb.f(n2);
                bb.e(n3);
                bb.d(b);
                bb.c(b3);
                bb.p();
            }
        }
        h.f().a(s + "_" + n4, n2, b, n3);
    }
    
    public synchronized void a(final int n, final int n2, final boolean b) {
        for (final bb bb : l.a) {
            if (bb.q() == n && bb.j() == n2) {
                bb.c(b);
                bb.p();
            }
        }
    }
    
    public static synchronized void b(final bb bb) {
        l.a.add(bb);
    }
    
    public static synchronized void c(final bb bb) {
        l.a.remove(bb);
    }
    
    static {
        l.a = new ArrayList();
    }
}
