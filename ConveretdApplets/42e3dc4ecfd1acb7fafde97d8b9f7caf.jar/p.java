import java.util.Iterator;
import java.util.StringTokenizer;
import au.com.rocketdog.project.awc.applet.Main;
import java.awt.MenuItem;
import java.util.Enumeration;
import java.awt.Color;
import java.util.Hashtable;
import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class p extends q
{
    private static Vector a;
    private static Vector b;
    private static Hashtable c;
    private static n d;
    private static Vector e;
    private static Vector f;
    private static boolean g;
    private static boolean h;
    private v i;
    private static boolean j;
    public static String[] k;
    public static String[] l;
    private t m;
    private static p n;
    
    public static void a(final av av, final Color color) {
        p.c.put(av.r(), color);
        final Enumeration<cj> elements = p.a.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().a(av, color);
        }
    }
    
    public static void a(final av av) {
        p.c.remove(av.r());
        final Enumeration<cj> elements = p.a.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().a(av);
        }
    }
    
    public static boolean b(final av av) {
        return p.c.containsKey(av.r());
    }
    
    public static Color a(final String s) {
        return p.c.get(s);
    }
    
    public static void a(final bf bf) {
        if (p.e.indexOf(bf) < 0) {
            p.e.addElement(bf);
        }
    }
    
    public static void a(final MenuItem menuItem) {
        if (p.f.indexOf(menuItem) < 0) {
            p.f.addElement(menuItem);
        }
    }
    
    public static void b(final MenuItem menuItem) {
        if (p.f.indexOf(menuItem) < 0) {
            p.f.addElement(menuItem);
        }
    }
    
    public static boolean b(final String s) {
        return p.b.contains(s);
    }
    
    public static void b(final bf bf) {
        p.e.removeElement(bf);
    }
    
    public static void a(final cj cj) {
        p.a.addElement(cj);
    }
    
    public static void b(final cj cj) {
        p.a.removeElement(cj);
    }
    
    public void a(final String s, final String s2, final String s3) {
        boolean b = false;
        if (!s2.equalsIgnoreCase("ChanServ") && !s2.equalsIgnoreCase("OperServ")) {
            final Enumeration<cj> elements = p.a.elements();
            while (elements.hasMoreElements()) {
                final cj cj = elements.nextElement();
                if (cj.g().equalsIgnoreCase(s)) {
                    b = true;
                    if (this.m.a("chat.showjoins").equalsIgnoreCase("true")) {
                        cj.a(s2, s3, "", false);
                        break;
                    }
                    cj.a(s2, s3, "", true);
                    break;
                }
            }
            if (!b && s2.startsWith(n.b().z())) {
                final bj c = h.c(s);
                if (c != null) {
                    final i b2 = i.b();
                    b2.a(c);
                    if (!b2.isShowing()) {
                        b2.show();
                    }
                    this.c();
                }
            }
        }
    }
    
    public void a(final String s, final String s2, final String s3, final String s4) {
        if (!s2.equalsIgnoreCase("ChanServ") && !s2.equalsIgnoreCase("OperServ")) {
            final Enumeration<cj> elements = p.a.elements();
            while (elements.hasMoreElements()) {
                final cj cj = elements.nextElement();
                if (cj.g().equalsIgnoreCase(s)) {
                    if (this.m.a("chat.showjoins").equalsIgnoreCase("true")) {
                        cj.a(s2, s4, false);
                        break;
                    }
                    cj.a(s2, s4, true);
                    break;
                }
            }
        }
    }
    
    public void b(final String s, final String s2, final String s3) {
        final Enumeration<cj> elements = p.a.elements();
        if (elements.hasMoreElements()) {
            final cj cj = elements.nextElement();
            if (this.m.a("chat.showjoins").equalsIgnoreCase("true")) {
                cj.a(s, "", false);
            }
            else {
                cj.a(s, "", true);
            }
        }
    }
    
    public void a(final String s, final String s2, final String s3, final String s4, final String s5) {
        final Enumeration<cj> elements = p.a.elements();
        while (elements.hasMoreElements()) {
            final cj cj = elements.nextElement();
            if (cj.g().equalsIgnoreCase(s)) {
                cj.a(s2, s4, s5);
                break;
            }
        }
    }
    
    public void b(final String s, final String s2, final String s3, String substring) {
        if (substring.indexOf("This nickname is owned by someone else") >= 0) {
            this.j("PRIVMSG NickServ :IDENTIFY awc");
        }
        else {
            if (s2.equalsIgnoreCase("ChanServ")) {
                String substring2 = "";
                final int index = substring.indexOf("[");
                final int index2 = substring.indexOf("]");
                if (index >= 0 && index2 >= 0) {
                    substring2 = substring.substring(index + 1, index2);
                    substring = substring.substring(index2 + 1, substring.length());
                }
                final Enumeration<cj> elements = (Enumeration<cj>)p.a.elements();
                while (elements.hasMoreElements()) {
                    final cj cj = elements.nextElement();
                    if (cj.g().equalsIgnoreCase(substring2)) {
                        cj.a(Main.p.a("chat.roommessage"), substring);
                        break;
                    }
                }
            }
            if (s3.equalsIgnoreCase("$$*.anywebcam.com")) {
                if (substring.startsWith("close")) {
                    l.b().b(Integer.valueOf(substring.split(":")[1]));
                }
                else if (substring.startsWith("H")) {
                    n.c = Long.parseLong(substring.substring(1, substring.length()));
                }
                else {
                    n.d = Long.parseLong(substring.substring(1, substring.length()));
                }
            }
        }
    }
    
    public void c(final String s, final String s2, final String s3, final String s4) {
        if (!Main.b(s2)) {
            final Enumeration<cj> elements = p.a.elements();
            while (elements.hasMoreElements()) {
                final cj cj = elements.nextElement();
                if (cj.g().equalsIgnoreCase(s)) {
                    cj.a(s2, s4);
                    break;
                }
            }
        }
    }
    
    public void a(final String s, final String s2) {
        final Enumeration<cj> elements = p.a.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().a("AWC OPP : " + s, s2);
        }
    }
    
    public void d(final String s, final String s2, final String s3, final String s4) {
        final Enumeration<cj> elements = p.a.elements();
        while (elements.hasMoreElements()) {
            final cj cj = elements.nextElement();
            if (cj.g().equalsIgnoreCase(s)) {
                cj.b(s2, s4);
                break;
            }
        }
    }
    
    public void a(final int n, String substring) {
        substring = substring.substring(n.b().z().length() + 1, substring.length());
        switch (n) {
            case 352: {
                try {
                    final StringTokenizer stringTokenizer = new StringTokenizer(substring, " ");
                    final String nextToken = stringTokenizer.nextToken();
                    final String nextToken2 = stringTokenizer.nextToken();
                    if (!nextToken2.equalsIgnoreCase("AnyWebCam") && !nextToken2.equalsIgnoreCase("AWC_Triva_")) {
                        stringTokenizer.nextToken();
                        stringTokenizer.nextToken();
                        final String nextToken3 = stringTokenizer.nextToken();
                        final String nextToken4 = stringTokenizer.nextToken();
                        final Enumeration<cj> elements = (Enumeration<cj>)p.a.elements();
                        while (elements.hasMoreElements()) {
                            final cj cj = elements.nextElement();
                            if (cj.g().equalsIgnoreCase(nextToken)) {
                                cj.a(nextToken3, nextToken2, nextToken4, true);
                                break;
                            }
                        }
                    }
                }
                catch (Exception ex) {
                    b.a(ex, 3);
                }
                break;
            }
            case 353: {
                final Enumeration<cj> elements2 = (Enumeration<cj>)p.a.elements();
                while (elements2.hasMoreElements()) {
                    final cj cj2 = elements2.nextElement();
                    if (substring.indexOf(cj2.g()) >= 0) {
                        this.j("who " + cj2.g());
                        break;
                    }
                }
                break;
            }
            case 474: {
                final Enumeration<cj> elements3 = (Enumeration<cj>)p.a.elements();
                while (elements3.hasMoreElements()) {
                    final cj cj3 = elements3.nextElement();
                    if (substring.indexOf(cj3.g()) >= 0) {
                        cj3.f();
                        break;
                    }
                }
                break;
            }
            case 367: {
                final Enumeration<cj> elements4 = (Enumeration<cj>)p.a.elements();
                while (elements4.hasMoreElements()) {
                    final cj cj4 = elements4.nextElement();
                    if (substring.indexOf(cj4.g()) >= 0) {
                        cj4.a(substring.substring(cj4.g().length() + 1, substring.indexOf("!")));
                        break;
                    }
                }
                break;
            }
            case 368: {
                final Enumeration<cj> elements5 = (Enumeration<cj>)p.a.elements();
                while (elements5.hasMoreElements()) {
                    final cj cj5 = elements5.nextElement();
                    if (substring.indexOf(cj5.g()) >= 0) {
                        cj5.h();
                        break;
                    }
                }
                break;
            }
            case 319: {
                (this.i = v.a()).a(n, substring);
                break;
            }
            case 401: {
                (this.i = v.a()).a(n, substring);
                break;
            }
            case 318: {
                (this.i = v.a()).a(n, substring);
                break;
            }
            case 372: {
                final StringTokenizer stringTokenizer2 = new StringTokenizer(substring, " ");
                stringTokenizer2.nextToken();
                final String nextToken5 = stringTokenizer2.nextToken();
                n.c = Long.parseLong(nextToken5.substring(1, nextToken5.length()));
                final String nextToken6 = stringTokenizer2.nextToken();
                n.d = Long.parseLong(nextToken6.substring(1, nextToken6.length()));
                break;
            }
            case 475: {
                final Enumeration<cj> elements6 = (Enumeration<cj>)p.a.elements();
                while (elements6.hasMoreElements()) {
                    final cj cj6 = elements6.nextElement();
                    if (substring.indexOf(cj6.g()) >= 0) {
                        cj6.c();
                        break;
                    }
                }
                break;
            }
            case 640: {
                synchronized (p.b) {
                    final StringTokenizer stringTokenizer3 = new StringTokenizer(substring, " ");
                    while (stringTokenizer3.hasMoreTokens()) {
                        p.b.addElement(stringTokenizer3.nextToken());
                    }
                }
                break;
            }
        }
    }
    
    private void j(final String s, final String s2, final String s3) {
        if (s3.startsWith("?")) {
            p.n.j("PRIVMSG " + s + " :?" + n.b().x() + "" + p.d.h() + "" + n.b().j());
            return;
        }
        for (int i = 0; i < p.e.size(); ++i) {
            if (p.e.elementAt(i) instanceof be && ((be)p.e.elementAt(i)).q() == Integer.parseInt(s2)) {
                ((bf)p.e.elementAt(i)).b(s3);
                return;
            }
        }
        if (s3.startsWith("*")) {
            l.b().b(Integer.parseInt(s2), s.substring(1), false, 0, n.b().s());
        }
    }
    
    public synchronized void c(final String s, final String s2, final String s3) {
        if (s.startsWith("^")) {
            this.j(s, s2, s3);
        }
        else {
            if (b(s)) {
                this.d(s, s2, s3);
                return;
            }
            if (s.equalsIgnoreCase("AnyWebCam")) {
                if (s3.equals("CF1")) {
                    p.d.m();
                }
                else if (s3.equals("CF0")) {
                    p.d.n();
                }
                return;
            }
            if (!Main.b(s)) {
                if (!p.j && !Main.c(s)) {
                    if (!s3.equals("0")) {
                        p.n.j("PRIVMSG " + s + " :" + Main.p.a("chat.noprivate"));
                    }
                }
                else {
                    this.d(s, s2, s3);
                }
            }
        }
    }
    
    public void d(final String s, final String s2, final String s3) {
        if (!this.k(s, s3)) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s2, "^");
            final int int1 = Integer.parseInt(stringTokenizer.nextToken());
            final String nextToken = stringTokenizer.nextToken();
            final int int2 = Integer.parseInt(nextToken.substring(0, 1));
            final int int3 = Integer.parseInt(nextToken.substring(1, 2));
            final i b = i.b();
            if (!b.isShowing()) {
                b.setVisible(true);
            }
            final cy a = b.a(new cz(s, 0, int1, int3, int2, "private"));
            a(a);
            a.b(s3);
        }
    }
    
    private boolean k(final String s, final String s2) {
        for (int i = 0; i < p.e.size(); ++i) {
            if (!(p.e.elementAt(i) instanceof be) && ((cy)p.e.elementAt(i)).f().equalsIgnoreCase(s)) {
                if (s2 != null && s2.length() > 0) {
                    ((bf)p.e.elementAt(i)).b(s2);
                }
                return true;
            }
        }
        return false;
    }
    
    public void a(final cz cz) {
        if (!this.k(cz.r(), null)) {
            final i b = i.b();
            if (!b.isShowing()) {
                b.setVisible(true);
            }
            a(b.a(cz));
            cz.e(true);
        }
    }
    
    private p(final String s, final String s2, final String s3, final int n, final String s4, final String s5) throws Exception {
        this.m = t.a();
        this.l(s);
        this.m(s5);
        this.k(s4);
        this.b(false);
        this.a(s2, n, s3, 512);
        if (this.m.a("chat.allowpms").equalsIgnoreCase("false")) {
            p.j = false;
        }
        else {
            p.j = true;
        }
    }
    
    public static synchronized p a() {
        try {
            if (p.n == null || !p.n.g()) {
                p.d = n.b();
                (p.n = new p(p.d.z(), Main.a, Main.h, Main.r, p.d.r(), p.d.f())).j();
            }
        }
        catch (a5 a5) {
            p.n = null;
            new a8(Main.h(), Main.p.a("chat.message.memberinuse"), Main.p.a("chat.message.ifnottryinfive"), 400, 100).show();
            b.a(a5.getMessage(), 3);
        }
        catch (Exception ex) {
            p.n = null;
            new a8(Main.h(), Main.p.a("chat.message.cannotconnect"), Main.p.a("chat.message.tryinfive"), 400, 100).show();
            b.a("IRC " + ex.getMessage(), 3);
        }
        return p.n;
    }
    
    public static void a(final boolean j) {
        p.j = j;
        final Enumeration<MenuItem> elements = p.f.elements();
        while (elements.hasMoreElements()) {
            final MenuItem menuItem = elements.nextElement();
            if (j) {
                menuItem.setLabel("Disable PM");
                menuItem.setName("disable_pm");
            }
            else {
                menuItem.setLabel("Enable PM");
                menuItem.setName("enable_pm");
            }
        }
    }
    
    public static boolean b() {
        return p.j;
    }
    
    private void j() {
        this.j("PRIVMSG NickServ :REGISTER awc");
        this.j("PRIVMSG NickServ :IDENTIFY awc");
        if (n.b().o()) {
            this.j("OPER " + n.b().z() + " awc");
            this.j("PRIVMSG Operserv :IDENTIFY operawc");
        }
        this.j("GETAWCOPS");
    }
    
    public void c() {
        if (p.a.size() > 0) {
            final StringBuffer sb = new StringBuffer("RLA@");
            final Iterator iterator = p.a.iterator();
            while (iterator.hasNext()) {
                final cj cj = iterator.next();
                if (sb.length() + cj.g().length() > 255) {
                    this.f("#DTU@" + n.b().z(), sb.toString());
                    sb.delete(0, sb.length());
                    sb.append("RLA@");
                }
                else {
                    sb.append(cj.g());
                    if (!iterator.hasNext()) {
                        continue;
                    }
                    sb.append(",");
                }
            }
            if (sb.length() > 3) {}
        }
    }
    
    public static void d() {
        try {
            if (p.n != null) {
                p.n.i();
                p.n.e();
            }
            p.n = null;
        }
        catch (Exception ex) {
            b.a(ex, 3);
        }
    }
    
    static {
        p.a = new Vector();
        p.b = new Vector();
        p.c = new Hashtable();
        p.e = new Vector();
        p.f = new Vector();
        p.g = true;
        p.h = true;
        p.j = false;
        p.k = new String[7];
        (p.l = new String[7])[0] = Main.p.a("chat.kick.beunbefit");
        p.k[0] = Main.p.a("chat.kick.beunbefitroom");
        p.l[1] = Main.p.a("chat.kick.directing");
        p.k[1] = Main.p.a("chat.kick.requestdirect");
        p.l[2] = Main.p.a("chat.kick.disrespect");
        p.k[2] = Main.p.a("chat.kick.disrespectmember");
        p.l[3] = Main.p.a("chat.kick.harassing");
        p.k[3] = Main.p.a("chat.kick.harrasmember");
        p.l[4] = Main.p.a("chat.kick.language");
        p.k[4] = Main.p.a("chat.kick.langunbefitroom");
        p.l[5] = Main.p.a("chat.kick.ruleviolate");
        p.k[5] = Main.p.a("chat.kick.notfollowrules");
        p.l[6] = Main.p.a("chat.kick.vulgar");
        p.k[6] = Main.p.a("chat.kick.vulgarlang");
        p.n = null;
    }
}
