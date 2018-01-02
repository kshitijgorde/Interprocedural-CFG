// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.c;

import java.awt.Container;
import flaxchat.h.d;
import java.util.Vector;
import java.util.Enumeration;
import flaxchat.r;
import flaxchat.e.p;
import java.util.StringTokenizer;
import flaxchat.FlaxChat;
import java.awt.event.MouseEvent;
import flaxchat.i.b;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Component;
import flaxchat.d.c;
import flaxchat.m;
import java.awt.Panel;
import flaxchat.d.j;
import java.util.Hashtable;
import flaxchat.b.h;
import flaxchat.d.i;
import flaxchat.d.g;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class e extends a implements MouseListener, ActionListener
{
    private final g j;
    private i k;
    private i l;
    private f m;
    private final flaxchat.h.g n;
    private final h o;
    private final Hashtable p;
    private final j q;
    private Panel r;
    private String s;
    private static String[] z;
    
    public e(final m m, final flaxchat.h.g n) {
        int i = a.i;
        super(m);
        this.p = new Hashtable();
        this.q = new j();
        this.r = new Panel();
        this.n = n;
        this.j = new g(m.g(), new c(0, this));
        this.o = new h(m, n, null, e.z[16]);
        this.j.setName(e.z[15] + n);
        this.q.a(new Dimension(0, 20));
        this.r.setLayout(new BorderLayout(0, 0));
        this.r.add(this.q, e.z[12]);
        this.r.add(this.j, e.z[14]);
        this.add(this.r, e.z[14]);
        this.j.d().addMouseListener(this);
        ((c)this.j.d()).a(this);
        this.q.setVisible(flaxchat.i.b.a(e.z[13], false));
        if (flaxchat.e.e.c) {
            a.i = ++i;
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (flaxchat.e.g.a(mouseEvent)) {
            this.a(this.j.d(), e.z[8], null, this.n, mouseEvent.getPoint());
            return;
        }
        if (mouseEvent.getClickCount() < 2) {
            return;
        }
        this.a(this.j.d(), e.z[7], null, this.n);
    }
    
    public void a() {
        if (this.l != null) {
            this.remove(this.l);
        }
        if (this.k != null) {
            this.remove(this.k);
        }
        this.b();
        super.h.g().requestFocus();
    }
    
    public void g() {
        if (this.k == null) {
            this.k = new flaxchat.b.e(super.h);
        }
        if (this.l != null) {
            this.remove(this.l);
        }
        final Object a = this.a((Object)e.z[28]);
        if (a != null) {
            ((flaxchat.d.f)a).b(false);
        }
        this.add(this.k, e.z[12]);
        this.b();
        super.h.g().requestFocus();
    }
    
    public void h() {
        if (this.l == null) {
            this.l = new flaxchat.b.f(super.h);
        }
        final Object a = this.a((Object)e.z[26]);
        if (a != null) {
            ((flaxchat.d.f)a).b(false);
        }
        if (this.k != null) {
            this.remove(this.k);
        }
        this.add(this.l, e.z[12]);
        this.b();
        super.h.g().requestFocus();
    }
    
    public flaxchat.h.g i() {
        return this.n;
    }
    
    public void c(final String s) {
        this.a(flaxchat.i.b.b(e.z[11], e.z[10]), s);
    }
    
    public void a(final String s, String substring) {
        final int i = a.i;
        final String s2 = substring;
        if (substring == null) {
            return;
        }
        final String[] j = this.i(substring);
        if (this.e(j[1], e.z[32])) {
            substring = j[1].substring(4);
            this.a(substring, j[0].trim().equals(this.i()));
            if (flaxchat.i.b.a(e.z[30], false)) {
                super.h.t().e(this.i().d());
                this.j.a(String.valueOf(s) + s2);
            }
            return;
        }
        if (this.e(j[1], e.z[34])) {
            System.out.println(e.z[37]);
            return;
        }
        final int d = this.d(j[0], j[1]);
        if (d != -1) {
            if (flaxchat.i.b.a(e.z[36], true)) {
                super.h.t().e(this.i().d());
                this.j.a(String.valueOf(s) + substring);
            }
            if (flaxchat.i.b.a(e.z[38], false)) {
                return;
            }
            int n = 0;
            while (true) {
                Label_0269: {
                    if (i == 0) {
                        break Label_0269;
                    }
                    super.h.l().j(flaxchat.i.b.c(e.z[31]));
                    ++n;
                }
                if (n >= d) {
                    return;
                }
                continue;
            }
        }
        else {
            super.h.t().e(this.i().d());
            if (flaxchat.i.b.a(e.z[35], true)) {
                this.j.a("[" + FlaxChat.e(e.z[33]) + e.z[29] + s + substring);
                return;
            }
            this.j.a(String.valueOf(s) + substring);
        }
    }
    
    private void a(final String s, final boolean b) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s);
        String trim = null;
        String nextToken = null;
        String nextToken2 = null;
        String trim2 = null;
        Label_0101: {
            try {
                stringTokenizer.nextToken();
                nextToken = stringTokenizer.nextToken();
                stringTokenizer.nextToken();
                nextToken2 = stringTokenizer.nextToken();
                if (stringTokenizer.nextToken().equals(e.z[22])) {
                    trim2 = flaxchat.e.p.a(stringTokenizer).trim();
                    if (a.i == 0) {
                        break Label_0101;
                    }
                }
                trim = flaxchat.e.p.a(stringTokenizer).trim();
                trim2 = null;
            }
            catch (RuntimeException ex) {
                ex.printStackTrace();
                return;
            }
        }
        if (e.z[21].equals(nextToken)) {
            this.e(nextToken2, trim2, trim, b);
            return;
        }
        if (e.z[23].equals(nextToken)) {
            this.d(nextToken2, trim2, trim, b);
            return;
        }
        if (e.z[20].equals(nextToken)) {
            this.c(nextToken2, trim2, trim, b);
            return;
        }
        if (e.z[24].equals(nextToken)) {
            this.a(nextToken2, trim2, trim, b);
            return;
        }
        if (e.z[25].equals(nextToken)) {
            this.b(nextToken2, trim2, trim, b);
        }
    }
    
    public void e(final String s) {
        final int i = a.i;
        final Enumeration elements = this.p.elements();
        while (true) {
            Label_0040: {
                if (i == 0) {
                    break Label_0040;
                }
                elements.nextElement().a(e.z[19]);
            }
            if (!elements.hasMoreElements()) {
                this.p.clear();
                return;
            }
            continue;
        }
    }
    
    public String b(final String s, final String s2) {
        if (s2.equalsIgnoreCase(this.i())) {
            return this.a(31);
        }
        if (this.p.get(s) == null) {
            return null;
        }
        return this.a(32, this.k(s), s2);
    }
    
    private void a(final String s, final String s2, final String s3, final boolean b) {
        final r r = this.p.get(s);
        if (r == null) {
            this.b(s, this.a(33, this.i()));
            return;
        }
        r.a(s2, s3);
    }
    
    private void b(final String s, final String s2, final String s3, final boolean b) {
        final r r = this.p.get(s);
        if (r == null) {
            this.b(s, this.a(33, this.i()));
            return;
        }
        r.b(s, super.h.l(), this, false);
    }
    
    private void c(final String s, final String s2, final String s3, final boolean b) {
        final r r = this.p.remove(s);
        if (r == null) {
            if (!b) {
                flaxchat.e.j.b(super.h, s2, this.a(34, this.k(s), this.i(), this.m()));
            }
            return;
        }
        try {
            r.a(null);
        }
        catch (RuntimeException ex) {
            ex.printStackTrace();
        }
        if (b) {
            flaxchat.e.j.b(super.h, this.a(35, this.k(s)));
            return;
        }
        flaxchat.e.j.b(super.h, s2, this.a(34, this.k(s), this.i(), this.m()));
    }
    
    private void d(final String s, final String s2, final String s3, final boolean b) {
        if (this.p.get(s) != null) {
            this.b(s, this.a(36));
            return;
        }
        final Class h = this.h(s);
        if (h == null) {
            this.b(s, this.a(37, this.i(), this.k(s)));
            flaxchat.e.j.b(this, this.a(38, this.k(s)));
            return;
        }
        final r a = this.a(h);
        if (a == null) {
            this.b(s, this.a(39, this.i(), this.k(s)));
            flaxchat.e.j.b(this, this.a(40, this.k(s)));
            return;
        }
        if (!a.c().equals(super.h.l().h())) {
            this.b(s, this.a(41, this.i(), this.k(s)));
            flaxchat.e.j.b(this, this.a(42, this.k(s)));
            return;
        }
        try {
            a.a(s, super.h.l(), this, false);
            this.p.put(s, a);
            this.g(s);
            a.b(s, super.h.l(), this, true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.b(s, this.a(43, this.i(), this.k(s)));
            flaxchat.e.j.b(this, this.a(44, this.k(s)));
        }
    }
    
    private void e(final String s, final String s2, final String s3, final boolean b) {
        if (!b && flaxchat.e.j.a(super.h, s2, this.a(45, this.k(s), this.m()), new String[] { e.z[46], e.z[45] }, e.z[46]) != 0) {
            this.b(s, this.a(46, this.i(), this.k(s)));
            return;
        }
        if (this.p.get(s) != null) {
            this.b(s, this.a(36));
            return;
        }
        final Class h = this.h(s);
        if (h == null) {
            this.b(s, this.a(37, this.i(), this.k(s)));
            flaxchat.e.j.b(this, this.a(38, this.k(s)));
            return;
        }
        final r a = this.a(h);
        if (a == null) {
            this.b(s, this.a(39, this.i(), this.k(s)));
            flaxchat.e.j.b(this, this.a(40, this.k(s)));
            return;
        }
        if (!a.c().equals(e.z[47])) {
            this.b(s, this.a(41, this.i(), this.k(s)));
            flaxchat.e.j.b(this, this.a(42, this.k(s)));
            return;
        }
        try {
            a.a(s, super.h.l(), this, true);
            this.p.put(s, a);
            this.c(s, e.z[44]);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.b(s, this.a(43, this.i(), this.k(s)));
            flaxchat.e.j.b(this, this.a(44, this.k(s)));
        }
    }
    
    public void b(final String s, final String s2) {
        this.a().a(this.m(), e.z[1] + s + e.z[0] + s2);
        this.c(s, null, null, true);
    }
    
    public void g(final String s) {
        this.a().a(this.m(), e.z[3] + s + e.z[4]);
    }
    
    private void c(final String s, final String s2) {
        this.a().a(this.m(), e.z[27] + s + e.z[0] + s2);
    }
    
    private Class h(String string) {
        final String lowerCase = string.toLowerCase();
        string = String.valueOf(Character.toUpperCase(string.charAt(0))) + string.substring(1);
        try {
            return Class.forName(e.z[6] + lowerCase + e.z[5] + string);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    private r a(final Class clazz) {
        try {
            return clazz.getConstructor((Class<?>[])null).newInstance((Object[])null);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    private int d(final String s, final String s2) {
        final int i = a.i;
        if (s.trim().equals(this.i().d()) && !flaxchat.i.b.a(e.z[9], true)) {
            return -1;
        }
        final Vector c = flaxchat.i.b.c();
        final int size = c.size();
        int n = 0;
        while (true) {
            Label_0094: {
                if (i == 0) {
                    break Label_0094;
                }
                if (this.e(s2, c.elementAt(n))) {
                    return (int)flaxchat.i.b.b().elementAt(n);
                }
                ++n;
            }
            if (n >= size) {
                return -1;
            }
            continue;
        }
    }
    
    private String[] i(String s) {
        s = flaxchat.h.d.c(s).trim();
        final int index = s.indexOf("<");
        if (index == -1) {
            return new String[] { "", "" };
        }
        final int index2 = s.indexOf(">", index);
        final String substring = s.substring(index + 1, index2);
        s = s.substring(index2 + 1).trim();
        return new String[] { substring, s };
    }
    
    public boolean e(final String s, final String s2) {
        return s.regionMatches(true, 0, s2, 0, s2.length());
    }
    
    public void b(String s) {
        final int i = a.i;
        s = flaxchat.h.h.a(s, this.a().k());
        final StringTokenizer stringTokenizer = new StringTokenizer(s, e.z[42]);
        while (true) {
            Label_0137: {
                if (i == 0) {
                    break Label_0137;
                }
                s = stringTokenizer.nextToken();
                if (this.h(s)) {
                    this.a(e.z[41], e.z[43]);
                    return;
                }
                if (!s.startsWith("\u0001")) {
                    this.a(flaxchat.i.b.b(e.z[40], e.z[10]), "<" + this.i() + e.z[39] + s);
                }
                this.a().a(this.m(), s);
            }
            if (!stringTokenizer.hasMoreElements()) {
                return;
            }
            continue;
        }
    }
    
    public void e() {
        this.j.b();
    }
    
    public void f() {
        super.h.l().d(this.m());
        super.h.g().requestFocus();
    }
    
    public f j() {
        return this.m;
    }
    
    public void k() {
        this.j.b();
        if (this.m != null) {
            this.m.setText("");
        }
    }
    
    public void l() {
        if (this.m == null) {
            this.m = new f(this, this.j);
        }
        this.m.a();
    }
    
    public void j(final String s) {
        this.n.a(s);
    }
    
    public String j() {
        return this.m();
    }
    
    public h k() {
        return this.o;
    }
    
    public String m() {
        return this.n.d();
    }
    
    private String k(final String s) {
        return flaxchat.i.c.a(s);
    }
    
    public void a(final boolean b) {
        final int i = a.i;
        Label_0082: {
            if (b) {
                final Enumeration<r> elements = (Enumeration<r>)this.p.elements();
                while (true) {
                    Label_0034: {
                        if (i == 0) {
                            break Label_0034;
                        }
                        elements.nextElement().b();
                    }
                    if (elements.hasMoreElements()) {
                        continue;
                    }
                    break;
                }
                if (i == 0) {
                    break Label_0082;
                }
            }
            final Enumeration<r> elements2 = (Enumeration<r>)this.p.elements();
            while (true) {
                Label_0073: {
                    if (i == 0) {
                        break Label_0073;
                    }
                    elements2.nextElement().a();
                }
                if (elements2.hasMoreElements()) {
                    continue;
                }
                break;
            }
        }
        super.a(b);
    }
    
    public void c(final boolean b) {
        if (b) {
            this.a().a(this.m(), e.z[18]);
            return;
        }
        this.a().a(this.m(), e.z[17]);
    }
    
    public void b(final boolean b) {
        if (b) {
            this.q.a(String.valueOf(this.m()) + e.z[2]);
            return;
        }
        if (this.s == null) {
            this.q.a("");
            return;
        }
        this.q.a(this.s);
    }
    
    public void f(final String s) {
        this.s = s;
        if (s == null) {
            return;
        }
        this.q.a(s);
    }
    
    public Container l() {
        return this.r;
    }
    
    public flaxchat.f.c g(final String s) {
        final flaxchat.f.c c = new flaxchat.f.c();
        c.c(this.m());
        c.c(this.i());
        return c;
    }
    
    static {
        e.z = new String[] { z(z("+\u0001\u0004\t")), z(z("m@\u0011N~&XI[;a\fDG~")), z(z("+E\u0005L*b\f\u0010H$bU\u0006[")), z(z("m@\u0011N~&XI[;o\fDG~")), z(z("+\u0001\u001a\t\u0017+M\u0004\t,nM\rP")), z(z("%j\u0005H&")), z(z("m@\bQ=cM\u001d\u0007")), z(z("zY\f['&[\u0000G:d[")), z(z("zY\f['&\\\u0006Y+{")), z(z("xI\u0005O\u0010~H\u000eL")), z(z("\b\u001d[")), z(z("yI\n@;}I\rd-lo\u0006E1y")), z(z("XC\u001c]6")), z(z("xD\u0006^\u0013xK+H,")), z(z("HI\u0007];y")), z(z("^_\f[~F_\u000e\t")), z(z("zY\f['&X\u0006F2iM\u001b")), z(z(".B\u0002K{")), z(z(".G\u000b\f")), z(z("^_\f[~zY\u0000];o\f {\u001d+_\f[(n^")), z(z("yI\u0003")), z(z("bB\u001f")), z(z("&A")), z(z("jO\n")), z(z("{@\u0010")), z(z("yI\r")), z(z("xA\u0000E;")), z(z("m@\u0011N~&XIH=h\fDG~")), z(z("hC\u0005F,")), z(z("V\f")), z(z("xD\u0006^\u0019jA\fz*n\\")), z(z("eY\rN;XO\u001b@.\u007fb\bD;")), z(z("m@\u0011N")), z(z("CdSD31_\u001a")), z(z("\nM\n]7dB")), z(z("xD\u0006^\u0013xK;J(ox\u0000D;")), z(z("eY\rN;[^\u0000G*")), z(z("iY\u001bM?")), z(z("i@\u0006J5BB'\\:lI")), z(z("5\f")), z(z("xI\u0007]\u0013xK*F2d^")), z(z("\b\u001c]")), z(z("\u0001!")), z(z("XY\u0007\\=~A\u001cS:j\f\u000b\\~\u007fM\u001bS~`C\u0007\\-jA\bS-bB\u0000Sp")), z(z("jO\nL.\u007fI\r")), z(z("YI\r")), z(z("@M\u000b\\2")), z(z("xM\u000b[;\u007f\u0002\u0007L*")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '^';
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
                    c2 = '\u000b';
                    break;
                }
                case 1: {
                    c2 = ',';
                    break;
                }
                case 2: {
                    c2 = 'i';
                    break;
                }
                case 3: {
                    c2 = ')';
                    break;
                }
                default: {
                    c2 = '^';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
