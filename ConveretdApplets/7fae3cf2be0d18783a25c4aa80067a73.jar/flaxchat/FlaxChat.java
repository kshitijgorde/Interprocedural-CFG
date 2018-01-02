// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat;

import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Container;
import flaxchat.a.g;
import java.util.StringTokenizer;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import flaxchat.a.s;
import flaxchat.f.d;
import flaxchat.a.c;
import java.awt.Graphics;
import flaxchat.d.b;
import com.ms.security.PolicyEngine;
import com.ms.security.PermissionID;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.applet.Applet;
import flaxchat.d.a;
import java.util.Vector;

public class FlaxChat extends j implements Runnable
{
    private String a;
    private String b;
    private r c;
    private n d;
    private m e;
    private t f;
    public static String g;
    public Vector h;
    private int i;
    private static String[] z;
    
    public void a(final String g) {
        FlaxChat.g = g;
    }
    
    private boolean a() {
        final int w = n.w;
        if (this.getParameter(FlaxChat.z[24]) != null) {
            return false;
        }
        final String string = this.getDocumentBase().getHost().toString();
        final String[] e = flaxchat.d.a.e();
        if (e == null) {
            return false;
        }
        int n = 0;
        while (true) {
            Label_0064: {
                if (w == 0) {
                    break Label_0064;
                }
                if (string.indexOf(e[n]) != -1) {
                    return false;
                }
                ++n;
            }
            if (n >= e.length) {
                return true;
            }
            continue;
        }
    }
    
    public void init() {
        this.b();
        flaxchat.d.a.a(this, this.getCodeBase());
        this.c();
        (this.c = new r(null, this.a(FlaxChat.z[7]), this.a(FlaxChat.z[6]), this.a(FlaxChat.z[5]))).a(this.a(FlaxChat.z[2], false));
        this.c.b(this.a(FlaxChat.z[0], false));
        this.setLayout(new BorderLayout());
        try {
            this.c.p(FlaxChat.z[8]);
        }
        catch (Exception ex) {}
        final String a = this.a(FlaxChat.z[6]);
        this.removeAll();
        if (this.b(a)) {
            this.a(a, this.a(FlaxChat.z[4]), this.a(FlaxChat.z[1]));
            return;
        }
        this.add(new p(this), FlaxChat.z[3]);
        this.invalidate();
        this.validate();
        this.validateTree();
    }
    
    private void b() {
        try {
            if (Class.forName(FlaxChat.z[15]) != null) {
                PolicyEngine.assertPermission(PermissionID.NETIO);
            }
        }
        catch (Throwable t) {}
    }
    
    private void c() {
        flaxchat.d.b.a(this);
    }
    
    public void start() {
    }
    
    public void stop() {
    }
    
    public void destroy() {
        try {
            this.p().b();
            this.t();
            this.p().q();
        }
        catch (RuntimeException ex) {}
    }
    
    private String b(String s) {
        try {
            if (s == null) {
                return "";
            }
            if (s.toLowerCase().startsWith(FlaxChat.z[21])) {
                s = s.substring(7);
                s = h(s);
                if (n.w == 0) {
                    return s;
                }
            }
            if (s.toLowerCase().startsWith(FlaxChat.z[22])) {
                s = s.substring(7);
                s = g(s);
            }
            return s;
        }
        catch (RuntimeException ex) {
            return "";
        }
    }
    
    public void paint(final Graphics graphics) {
        flaxchat.a.c.a(graphics, String.valueOf(this.e()) + FlaxChat.z[11], 10, 0, this.getSize().height);
    }
    
    public void a(final String s, final String b, final String s2) {
        this.b = b;
        this.a(s, s2);
    }
    
    private void a(final String s, final String a) {
        this.a = a;
        final String b = this.b(a.a(FlaxChat.z[32]));
        this.d();
        this.a(s);
        this.c(s, b);
    }
    
    private void d() {
        this.d = new n(this);
        (this.e = new m(this)).b(this.e());
        this.c.a(this.d);
        this.removeAll();
        this.add(this.d, FlaxChat.z[3]);
        this.add(this.e, FlaxChat.z[12]);
        this.d.k().d();
        this.n();
        this.d.b();
    }
    
    public String e() {
        return String.valueOf(this.h()) + " " + this.f() + FlaxChat.z[13] + this.p().g();
    }
    
    public String f() {
        return FlaxChat.z[23];
    }
    
    public String g() {
        return String.valueOf(this.f()) + FlaxChat.z[20];
    }
    
    public String h() {
        return FlaxChat.z[9];
    }
    
    public String i() {
        return FlaxChat.z[10];
    }
    
    public String j() {
        return FlaxChat.z[9];
    }
    
    public void c(String c) {
        c = flaxchat.f.d.c(c);
        this.e.b(c);
    }
    
    public void d(final String s) {
        if (s == null) {
            this.c(this.e());
            return;
        }
        this.c(String.valueOf(this.e()) + FlaxChat.z[13] + s);
    }
    
    public void k() {
        this.p().f(this.b);
        this.d(null);
        new s(this).start();
    }
    
    public void run() {
        final int w = n.w;
        final Vector l = this.l();
        final int size = l.size();
        int n = 0;
        while (true) {
            Label_0040: {
                if (w == 0) {
                    break Label_0040;
                }
                this.p().b(l.elementAt(n));
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
        this.a(flaxchat.d.c.a(s, array), b);
    }
    
    public Vector l() {
        final int w = n.w;
        final StringTokenizer stringTokenizer = new StringTokenizer((this.a == null) ? "" : this.a, FlaxChat.z[14]);
        while (true) {
            Label_0049: {
                if (w == 0) {
                    break Label_0049;
                }
                this.h.addElement(stringTokenizer.nextElement());
            }
            if (!stringTokenizer.hasMoreElements()) {
                return this.h;
            }
            continue;
        }
    }
    
    public void m() {
        this.e.b();
        this.add(this.d, FlaxChat.z[3]);
        this.n();
    }
    
    public void n() {
        this.invalidate();
        this.validate();
    }
    
    public n o() {
        return this.d;
    }
    
    public r p() {
        return this.c;
    }
    
    public void f(String g) {
        this.b(FlaxChat.z[16], FlaxChat.z[29] + g);
        g = g(g);
        this.b(FlaxChat.z[16], FlaxChat.z[28] + g);
    }
    
    public static String g(String s) {
        final int w = n.w;
        s = s.trim();
        int n = 0;
        while (true) {
            Label_0026: {
                if (w == 0) {
                    break Label_0026;
                }
                s = flaxchat.a.g.a(s.getBytes());
                ++n;
            }
            if (n >= 5) {
                return s;
            }
            continue;
        }
    }
    
    public static String h(String s) {
        final int w = n.w;
        s = s.trim();
        int n = 0;
        while (true) {
            Label_0023: {
                if (w == 0) {
                    break Label_0023;
                }
                s = flaxchat.a.g.a(s);
                ++n;
            }
            if (n >= 5) {
                return s;
            }
            continue;
        }
    }
    
    private void a(final Component component) {
        final int w = n.w;
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
                if (w == 0) {
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
        this.b(FlaxChat.z[16], FlaxChat.z[17] + s);
        flaxchat.d.a.a(FlaxChat.z[18], FlaxChat.z[19] + s);
        flaxchat.d.b.a();
        flaxchat.d.b.a(this);
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
    
    public void q() {
        this.d.n();
        this.b(FlaxChat.z[31], FlaxChat.z[30]);
        this.d.a(false);
        this.c(null, null);
    }
    
    public synchronized void r() {
        if (this.t()) {
            return;
        }
        (this.f = new t(this, this.c.g(), this.c.n())).start();
    }
    
    public synchronized void c(String g, String n) {
        this.t();
        if (g == null) {
            g = this.c.g();
            n = this.c.n();
        }
        (this.f = new t(this, g, n)).start();
    }
    
    public final boolean s() {
        if (this.getParameter(FlaxChat.z[24]) != null) {
            return false;
        }
        final String host = this.getCodeBase().getHost();
        final String s = FlaxChat.z[26];
        if (host.equals(FlaxChat.z[27])) {
            return false;
        }
        if (host.equals(FlaxChat.z[25] + this.i())) {
            return false;
        }
        if (host.equals(this.i())) {
            return false;
        }
        this.d.e().c(s);
        return true;
    }
    
    public void play(final URL url, final String s) {
        final AudioClip audioClip = this.getAudioClip(url, String.valueOf(flaxchat.d.a.b()) + "/" + s);
        if (audioClip != null) {
            audioClip.play();
        }
    }
    
    private boolean t() {
        if (this.f == null) {
            return false;
        }
        this.f.a();
        this.f = null;
        return true;
    }
    
    public void u() {
        this.k();
        this.d.a(true);
    }
    
    public FlaxChat() {
        this.h = new Vector();
    }
    
    static int a(final FlaxChat flaxChat) {
        return flaxChat.i;
    }
    
    static void a(final FlaxChat flaxChat, final int i) {
        flaxChat.i = i;
    }
    
    static r b(final FlaxChat flaxChat) {
        return flaxChat.c;
    }
    
    static void a(final FlaxChat flaxChat, final String s, final boolean b) {
        flaxChat.a(s, b);
    }
    
    static boolean c(final FlaxChat flaxChat) {
        return flaxChat.a();
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
        FlaxChat.z = new String[] { z(z("hg\u001b\u0013\u0017u}\u00192\u0006")), z(z("g`\u001d/\u001cadM")), z(z("hg\u001b\u0013\u0017wx\u0013/\u0001a")), z(z("Gm\u00125\u0017v")), z(z("ti\u000f2\u0005kz\u0018")), z(z("b}\u0010-\u001cee\u0019")), z(z("ja\u001f*\u001cee\u0019")), z(z("ml\u0019/\u0006")), z(z("GxMsG0")), z(z("Bd\u001d91li\b")), z(z("fi\u00101\u0017pm\u001b(\u001f*k\u0013,")), z(z("$2\\\u0003\u0007$X\u0019/\u0011az\u00198\u001b$c\u001d1\u0013pe\u001d8\u001bja\u0006o")), z(z("Jg\u000e5\u001a")), z(z("$2\\")), z(z("$$")), z(z("gg\u0011o\u001fw&\u000f$\u0011qz\u00155\u000b*X\u0013-\u001bgq9/\u0015mf\u0019")), z(z("\u00078J")), z(z("}m\u0012(Rpm\u0011 H$")), z(z("Wc\u0015/RBg\u0010%\u0017v")), z(z("wc\u0015/\u0001+")), z(z("[9LsC/")), z(z("`m\u001f.\u0016a2")), z(z("af\u001f.\u0016a2")), z(z("5&D")), z(z("@M*")), z(z("s\u007f\u000bo")), z(z("\u00078H\r\u001bwi\u00122Rrm\u0005 Ro}\u000e4\u001eqe\\)\u0013pi\u000f(")), z(z("mz\u001fo\u0010ed\f$\u0006ao\u0015,\\gg\u0011")), z(z("Wa\u001a3\u0017hm\u0012,\u001bw(4 \u001em(\\{R\u00078N")), z(z("Wa\u001a3\u0017hm\u0012$\u001c$E\u00195\u001bj(Faq4:")), z(z("W}\u00124\u0011q(\u001e \u0015hi\u00125\u001bwa\\*\u001dt|\to")), z(z("\u00078H")), z(z("mz\u001f\u0011\u0013w{")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'r';
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
                    c2 = '\b';
                    break;
                }
                case 2: {
                    c2 = '|';
                    break;
                }
                case 3: {
                    c2 = 'A';
                    break;
                }
                default: {
                    c2 = 'r';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
