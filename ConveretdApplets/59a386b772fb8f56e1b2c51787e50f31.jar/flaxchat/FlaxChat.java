// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat;

import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Container;
import flaxchat.e.f;
import java.util.StringTokenizer;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import flaxchat.e.r;
import flaxchat.h.d;
import flaxchat.e.c;
import java.awt.Graphics;
import flaxchat.i.b;
import com.ms.security.PolicyEngine;
import com.ms.security.PermissionID;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;
import flaxchat.i.a;
import java.util.Vector;

public class FlaxChat extends j implements Runnable
{
    private String a;
    private String b;
    private q c;
    private m d;
    private l e;
    private s f;
    public Vector g;
    private int h;
    private static String[] z;
    
    public void init() {
        this.a();
        flaxchat.i.a.a(this, this.getCodeBase());
        this.b();
        (this.c = new q(null, this.a(FlaxChat.z[10]), this.a(FlaxChat.z[7]), this.a(FlaxChat.z[8]))).a(this.a(FlaxChat.z[5], false));
        this.c.b(this.a(FlaxChat.z[3], false));
        this.setLayout(new BorderLayout());
        try {
            this.c.p(FlaxChat.z[4]);
        }
        catch (Exception ex) {}
        final String a = this.a(FlaxChat.z[7]);
        this.removeAll();
        if (this.b(a)) {
            this.a(a, this.a(FlaxChat.z[6]), this.a(FlaxChat.z[9]));
            return;
        }
        this.add(new o(this), FlaxChat.z[0]);
        this.invalidate();
        this.validate();
        this.validateTree();
    }
    
    private void a() {
        try {
            if (Class.forName(FlaxChat.z[25]) != null) {
                PolicyEngine.assertPermission(PermissionID.NETIO);
            }
        }
        catch (Throwable t) {}
    }
    
    private void b() {
        flaxchat.i.b.a(this);
    }
    
    public void start() {
    }
    
    public void stop() {
    }
    
    public void destroy() {
        try {
            this.o().b();
            this.s();
            this.o().q();
        }
        catch (RuntimeException ex) {}
    }
    
    private String b(String s) {
        try {
            if (s == null) {
                return "";
            }
            if (s.toLowerCase().startsWith(FlaxChat.z[15])) {
                s = s.substring(7);
                s = h(s);
                if (!m.s) {
                    return s;
                }
            }
            if (s.toLowerCase().startsWith(FlaxChat.z[16])) {
                s = s.substring(7);
                s = g(s);
            }
            return s;
        }
        catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
            return "";
        }
    }
    
    public void paint(final Graphics graphics) {
        flaxchat.e.c.a(graphics, String.valueOf(this.d()) + FlaxChat.z[2], 10, 0, this.getSize().height);
    }
    
    public void a(final String s, final String b, final String s2) {
        this.b = b;
        this.a(s, s2);
    }
    
    private void a(final String s, final String a) {
        this.a = a;
        final String b = this.b(a.a(FlaxChat.z[32]));
        this.c();
        this.b(FlaxChat.z[33], String.valueOf(this.d.l().h()) + FlaxChat.z[31] + this.e());
        this.c(s, b);
    }
    
    private void c() {
        this.d = new m(this);
        (this.e = new l(this)).b(this.d());
        this.c.a(this.d);
        this.removeAll();
        this.add(this.d, FlaxChat.z[0]);
        this.add(this.e, FlaxChat.z[1]);
        this.d.k().d();
        this.m();
        this.d.b();
    }
    
    public String d() {
        return String.valueOf(this.g()) + " " + this.e() + FlaxChat.z[11] + this.o().g();
    }
    
    public String e() {
        return FlaxChat.z[12];
    }
    
    public String f() {
        return String.valueOf(this.e()) + FlaxChat.z[23];
    }
    
    public String g() {
        return FlaxChat.z[14];
    }
    
    public String h() {
        return FlaxChat.z[24];
    }
    
    public String i() {
        return FlaxChat.z[14];
    }
    
    public void c(String c) {
        c = flaxchat.h.d.c(c);
        this.e.b(c);
    }
    
    public void d(final String s) {
        if (s == null) {
            this.c(this.d());
            return;
        }
        this.c(String.valueOf(this.d()) + FlaxChat.z[11] + s);
    }
    
    public void j() {
        this.o().f(this.b);
        this.d(null);
        new r(this).start();
    }
    
    public void run() {
        final boolean s = m.s;
        final Vector k = this.k();
        final int size = k.size();
        int n = 0;
        while (true) {
            Label_0040: {
                if (!s) {
                    break Label_0040;
                }
                this.o().b(k.elementAt(n));
                ++n;
            }
            if (n >= size) {
                this.d.k().d();
                return;
            }
            continue;
        }
    }
    
    public static String e(final String s) {
        return new SimpleDateFormat(s).format(Calendar.getInstance().getTime());
    }
    
    private void a(final String s, final boolean b) {
        if (b) {
            this.d.k().d(s);
            return;
        }
        this.d.k().c(s);
    }
    
    private void b(final String s, final String s2) {
        this.d.e().a(s, s2);
    }
    
    private void a(final String s, final String[] array, final boolean b) {
        this.a(flaxchat.i.c.a(s, array), b);
    }
    
    public Vector k() {
        final boolean s = m.s;
        final StringTokenizer stringTokenizer = new StringTokenizer((this.a == null) ? "" : this.a, FlaxChat.z[13]);
        while (true) {
            Label_0049: {
                if (!s) {
                    break Label_0049;
                }
                this.g.addElement(stringTokenizer.nextElement());
            }
            if (!stringTokenizer.hasMoreElements()) {
                return this.g;
            }
            continue;
        }
    }
    
    public void l() {
        this.e.b();
        this.add(this.d, FlaxChat.z[0]);
        this.m();
    }
    
    public void m() {
        this.invalidate();
        this.validate();
    }
    
    public m n() {
        return this.d;
    }
    
    public q o() {
        return this.c;
    }
    
    public void f(String g) {
        this.b(FlaxChat.z[27], FlaxChat.z[28] + g);
        g = g(g);
        this.b(FlaxChat.z[27], FlaxChat.z[26] + g);
    }
    
    public static String g(String s) {
        final boolean s2 = m.s;
        s = s.trim();
        int n = 0;
        while (true) {
            Label_0026: {
                if (!s2) {
                    break Label_0026;
                }
                s = f.a(s.getBytes());
                ++n;
            }
            if (n >= 5) {
                return s;
            }
            continue;
        }
    }
    
    public static String h(String s) {
        final boolean s2 = m.s;
        s = s.trim();
        int n = 0;
        while (true) {
            Label_0023: {
                if (!s2) {
                    break Label_0023;
                }
                s = f.a(s);
                ++n;
            }
            if (n >= 5) {
                return s;
            }
            continue;
        }
    }
    
    private void a(final Component component) {
        final boolean s = m.s;
        component.invalidate();
        component.validate();
        component.repaint();
        if (!(component instanceof Container)) {
            return;
        }
        final Container container = (Container)component;
        final int componentCount = container.getComponentCount();
        int n = 0;
        while (true) {
            Label_0056: {
                if (!s) {
                    break Label_0056;
                }
                this.a(container.getComponent(n));
                ++n;
            }
            if (n >= componentCount) {
                return;
            }
            continue;
        }
    }
    
    public void i(final String s) {
        flaxchat.i.a.a(FlaxChat.z[18], FlaxChat.z[17] + s);
        flaxchat.i.b.a();
        flaxchat.i.b.a(this);
        this.a(this.getParent());
        this.a(this.d);
    }
    
    public void j(final String s) {
        try {
            this.d.l().getAppletContext().showDocument(new URL(s));
        }
        catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void p() {
        this.d.n();
        this.b(FlaxChat.z[30], FlaxChat.z[29]);
        this.d.a(false);
        this.c(null, null);
    }
    
    public synchronized void q() {
        if (this.s()) {
            return;
        }
        (this.f = new s(this, this.c.g(), this.c.n())).start();
    }
    
    public synchronized void c(String g, String n) {
        this.s();
        if (g == null) {
            g = this.c.g();
            n = this.c.n();
        }
        (this.f = new s(this, g, n)).start();
    }
    
    public final boolean r() {
        if (this.getParameter(FlaxChat.z[19]) != null) {
            return false;
        }
        final String host = this.getCodeBase().getHost();
        final String s = FlaxChat.z[22];
        if (host.equals(FlaxChat.z[20])) {
            return false;
        }
        if (host.equals(FlaxChat.z[21] + this.h())) {
            return false;
        }
        if (host.equals(this.h())) {
            return false;
        }
        this.d.e().c(s);
        return true;
    }
    
    private boolean s() {
        if (this.f == null) {
            return false;
        }
        this.f.a();
        this.f = null;
        return true;
    }
    
    public void t() {
        this.j();
        this.d.a(true);
    }
    
    public FlaxChat() {
        this.g = new Vector();
    }
    
    static int a(final FlaxChat flaxChat) {
        return flaxChat.h;
    }
    
    static void a(final FlaxChat flaxChat, final int h) {
        flaxChat.h = h;
    }
    
    static q b(final FlaxChat flaxChat) {
        return flaxChat.c;
    }
    
    static void a(final FlaxChat flaxChat, final String s, final boolean b) {
        flaxChat.a(s, b);
    }
    
    static void a(final FlaxChat flaxChat, final String s, final String s2) {
        flaxChat.b(s, s2);
    }
    
    static void a(final FlaxChat flaxChat, final String s, final String[] array, final boolean b) {
        flaxChat.a(s, array, b);
    }
    
    static String a(final FlaxChat flaxChat, final String s) {
        return flaxChat.b(s);
    }
    
    static {
        FlaxChat.z = new String[] { z(z("G\u0006\"Njv")), z(z("J\f>Ng")), z(z("$Ylxz$3)Tla\u0011)Cf$\b-Jnp\u000e-Cfj\n6\u0014")), z(z("h\f+hju\u0016)I{")), z(z("G\u0013}\b:0")), z(z("h\f+hjw\u0013#T|a")), z(z("t\u0002?Ixk\u0011(")), z(z("j\n/Qae\u000e)")), z(z("b\u0016 Vae\u000e)")), z(z("g\u000b-Taa\u000f}")), z(z("m\u0007)T{")), z(z("$Yl")), z(z("5Mt")), z(z("$O")), z(z("B\u000f-BLl\u00028")), z(z("`\u0006/UkaY")), z(z("a\r/UkaY")), z(z("w\b%T|+")), z(z("W\b%T/B\f ^jv")), z(z("@&\u001a")), z(z("m\u0011/\u0014|e\u0001>_{*\r)N")), z(z("s\u0014;\u0014")), z(z("\u0007Sxvfw\u0002\"I/r\u00065[/o\u0016>Ocq\u000elRnp\u0002?S")), z(z("[R|\b?/")), z(z("w\u0002.HjpM\"_{")), z(z("g\f!\u0014bwM?_lq\u0011%Nv*3#Vfg\u001a\tThm\r)")), z(z("W\n*Hjh\u0006\"WfwC\u0004[cmCl\u0000/\u0007S~")), z(z("\u0007Sz")), z(z("W\n*Hjh\u0006\"_a$.)NfjCv\u001a\f4Q")), z(z("W\u0016\"OlqC.[hh\u0002\"Nfw\nlQ`t\u00179\u0014")), z(z("\u0007Sx")), z(z("$\u00015\u001aih\u00024Yge\u0017lL")), z(z("m\u0011/jnw\u0010")), z(z("\u0007S\u007f")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u000f';
        }
        return charArray;
    }
    
    private static String z(final char[] array) {
        final int i = array.length;
        for (int n = 0; i > n; ++n) {
            final int n2 = n;
            final char c = array[n2];
            char c2 = '\0';
            switch (n % 5) {
                case 0: {
                    c2 = '\u0004';
                    break;
                }
                case 1: {
                    c2 = 'c';
                    break;
                }
                case 2: {
                    c2 = 'L';
                    break;
                }
                case 3: {
                    c2 = ':';
                    break;
                }
                default: {
                    c2 = '\u000f';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
