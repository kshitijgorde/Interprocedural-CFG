// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.c;

import java.awt.Container;
import flaxchat.f.d;
import java.util.Vector;
import flaxchat.a.k;
import java.util.Enumeration;
import flaxchat.s;
import flaxchat.a.q;
import java.util.StringTokenizer;
import flaxchat.FlaxChat;
import java.awt.event.MouseEvent;
import flaxchat.d.b;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Component;
import flaxchat.i.c;
import flaxchat.n;
import java.awt.Panel;
import flaxchat.i.j;
import java.util.Hashtable;
import flaxchat.b.h;
import flaxchat.i.i;
import flaxchat.i.g;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class e extends a implements MouseListener, ActionListener
{
    private final g m;
    private i n;
    private i o;
    private f p;
    private final flaxchat.f.g q;
    private final h r;
    private final Hashtable s;
    private final j t;
    private Panel u;
    public i v;
    private String w;
    private static String[] z;
    
    public e(final n n, final flaxchat.f.g q) {
        final int l = a.l;
        super(n);
        this.s = new Hashtable();
        this.t = new j();
        this.u = new Panel();
        this.v = new i();
        this.q = q;
        this.m = new g(n.g(), new c(0, this));
        this.r = new h(n, q, null, e.z[9]);
        this.m.setName(e.z[12] + q);
        this.t.a(new Dimension(0, 20));
        this.v.setLayout(new BorderLayout());
        this.v.setVisible(false);
        this.u.setLayout(new BorderLayout(0, 0));
        this.u.add(this.t, e.z[7]);
        this.u.add(this.m, e.z[10]);
        this.u.add(this.v, e.z[8]);
        this.add(this.u, e.z[10]);
        this.m.d().addMouseListener(this);
        ((c)this.m.d()).a(this);
        this.t.setVisible(flaxchat.d.b.a(e.z[11], false));
        if (l != 0) {
            int c = flaxchat.a.e.c;
            flaxchat.a.e.c = ++c;
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (flaxchat.a.h.a(mouseEvent)) {
            this.a(this.m.d(), e.z[5], null, this.q, mouseEvent.getPoint());
            return;
        }
        if (mouseEvent.getClickCount() < 2) {
            return;
        }
        this.a(this.m.d(), e.z[4], null, this.q);
    }
    
    public void m() {
        if (this.o != null) {
            this.remove(this.o);
        }
        if (this.n != null) {
            this.remove(this.n);
        }
        this.b();
        super.k.g().requestFocus();
    }
    
    public void a() {
        if (this.n == null) {
            this.n = new flaxchat.b.e(super.k);
        }
        if (this.o != null) {
            this.remove(this.o);
        }
        final Object a = this.a((Object)e.z[35]);
        if (a != null) {
            ((flaxchat.i.f)a).a(false);
        }
        this.add(this.n, e.z[7]);
        this.b();
        super.k.g().requestFocus();
    }
    
    public void g() {
        if (this.o == null) {
            this.o = new flaxchat.b.f(super.k);
        }
        final Object a = this.a((Object)e.z[6]);
        if (a != null) {
            ((flaxchat.i.f)a).a(false);
        }
        if (this.n != null) {
            this.remove(this.n);
        }
        this.add(this.o, e.z[7]);
        this.b();
        super.k.g().requestFocus();
    }
    
    public flaxchat.f.g h() {
        return this.q;
    }
    
    public void c(final String s) {
        this.a(flaxchat.d.b.b(e.z[3], e.z[2]), s);
    }
    
    public void a(final String s, String substring) {
        final int l = a.l;
        final String s2 = substring;
        if (substring == null) {
            return;
        }
        final String[] i = this.i(substring);
        if (this.e(i[1], e.z[40])) {
            substring = i[1].substring(4);
            this.a(substring, i[0].trim().equals(this.i()));
            if (flaxchat.d.b.a(e.z[43], false)) {
                super.k.t().e(this.h().g());
                this.m.a(String.valueOf(s) + s2);
            }
            return;
        }
        if (this.e(i[1], e.z[39])) {
            return;
        }
        final int d = this.d(i[0], i[1]);
        if (d != -1) {
            if (flaxchat.d.b.a(e.z[38], true)) {
                super.k.t().e(this.h().g());
                this.m.a(String.valueOf(s) + substring);
            }
            if (flaxchat.d.b.a(e.z[44], false)) {
                return;
            }
            int n = 0;
            while (true) {
                Label_0257: {
                    if (l == 0) {
                        break Label_0257;
                    }
                    super.k.l().j(flaxchat.d.b.c(e.z[36]));
                    ++n;
                }
                if (n >= d) {
                    return;
                }
                continue;
            }
        }
        else {
            super.k.t().e(this.h().g());
            if (flaxchat.d.b.a(e.z[37], true)) {
                this.m.a("[" + FlaxChat.e(e.z[41]) + e.z[42] + s + substring);
                return;
            }
            this.m.a(String.valueOf(s) + substring);
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
                if (stringTokenizer.nextToken().equals(e.z[26])) {
                    trim2 = flaxchat.a.q.a(stringTokenizer).trim();
                    if (a.l == 0) {
                        break Label_0101;
                    }
                }
                trim = flaxchat.a.q.a(stringTokenizer).trim();
                trim2 = null;
            }
            catch (RuntimeException ex) {
                ex.printStackTrace();
                return;
            }
        }
        if (e.z[23].equals(nextToken)) {
            this.e(nextToken2, trim2, trim, b);
            return;
        }
        if (e.z[24].equals(nextToken)) {
            this.d(nextToken2, trim2, trim, b);
            return;
        }
        if (e.z[22].equals(nextToken)) {
            this.c(nextToken2, trim2, trim, b);
            return;
        }
        if (e.z[21].equals(nextToken)) {
            this.a(nextToken2, trim2, trim, b);
            return;
        }
        if (e.z[25].equals(nextToken)) {
            this.b(nextToken2, trim2, trim, b);
        }
    }
    
    public void e(final String s) {
        final int l = a.l;
        final Enumeration elements = this.s.elements();
        while (true) {
            Label_0040: {
                if (l == 0) {
                    break Label_0040;
                }
                elements.nextElement().a(e.z[16]);
            }
            if (!elements.hasMoreElements()) {
                this.s.clear();
                return;
            }
            continue;
        }
    }
    
    public String b(final String s, final String s2) {
        if (s2.equalsIgnoreCase(this.i())) {
            return this.a(31);
        }
        if (this.s.get(s) == null) {
            return null;
        }
        return this.a(32, this.k(s), s2);
    }
    
    private void a(final String s, final String s2, final String s3, final boolean b) {
        final s s4 = this.s.get(s);
        if (s4 == null) {
            this.b(s, this.a(33, this.i()));
            return;
        }
        s4.a(s2, s3);
    }
    
    private void b(final String s, final String s2, final String s3, final boolean b) {
        final s s4 = this.s.get(s);
        if (s4 == null) {
            this.b(s, this.a(33, this.i()));
            return;
        }
        s4.b(s, super.k.l(), this, false);
    }
    
    private void c(final String s, final String s2, final String s3, final boolean b) {
        final s s4 = this.s.remove(s);
        if (s4 == null) {
            if (!b) {
                flaxchat.a.k.b(super.k, s2, this.a(34, this.k(s), this.i(), this.k()));
            }
            return;
        }
        try {
            s4.a(null);
        }
        catch (RuntimeException ex) {
            ex.printStackTrace();
        }
        if (b) {
            flaxchat.a.k.b(super.k, this.a(35, this.k(s)));
            return;
        }
        flaxchat.a.k.b(super.k, s2, this.a(34, this.k(s), this.i(), this.k()));
    }
    
    private void d(final String s, final String s2, final String s3, final boolean b) {
        if (this.s.get(s) != null) {
            this.b(s, this.a(36));
            return;
        }
        final Class h = this.h(s);
        if (h == null) {
            this.b(s, this.a(37, this.i(), this.k(s)));
            flaxchat.a.k.b(this, this.a(38, this.k(s)));
            return;
        }
        final s a = this.a(h);
        if (a == null) {
            this.b(s, this.a(39, this.i(), this.k(s)));
            flaxchat.a.k.b(this, this.a(40, this.k(s)));
            return;
        }
        if (!a.c().equals(super.k.l().i())) {
            this.b(s, this.a(41, this.i(), this.k(s)));
            flaxchat.a.k.b(this, this.a(42, this.k(s)));
            return;
        }
        try {
            a.a(s, super.k.l(), this, false);
            this.s.put(s, a);
            this.g(s);
            a.b(s, super.k.l(), this, true);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.b(s, this.a(43, this.i(), this.k(s)));
            flaxchat.a.k.b(this, this.a(44, this.k(s)));
        }
    }
    
    private void e(final String s, final String s2, final String s3, final boolean b) {
        if (!b && flaxchat.a.k.a(super.k, s2, this.a(45, this.k(s), this.k()), new String[] { e.z[18], e.z[20] }, e.z[18]) != 0) {
            this.b(s, this.a(46, this.i(), this.k(s)));
            return;
        }
        if (this.s.get(s) != null) {
            this.b(s, this.a(36));
            return;
        }
        final Class h = this.h(s);
        if (h == null) {
            this.b(s, this.a(37, this.i(), this.k(s)));
            flaxchat.a.k.b(this, this.a(38, this.k(s)));
            return;
        }
        final s a = this.a(h);
        if (a == null) {
            this.b(s, this.a(39, this.i(), this.k(s)));
            flaxchat.a.k.b(this, this.a(40, this.k(s)));
            return;
        }
        if (!a.c().equals(e.z[17])) {
            this.b(s, this.a(41, this.i(), this.k(s)));
            flaxchat.a.k.b(this, this.a(42, this.k(s)));
            return;
        }
        try {
            a.a(s, super.k.l(), this, true);
            this.s.put(s, a);
            this.c(s, e.z[19]);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            this.b(s, this.a(43, this.i(), this.k(s)));
            flaxchat.a.k.b(this, this.a(44, this.k(s)));
        }
    }
    
    public void b(final String s, final String s2) {
        this.a().a(this.k(), e.z[1] + s + e.z[0] + s2);
        this.c(s, null, null, true);
    }
    
    public void g(final String s) {
        this.a().a(this.k(), e.z[34] + s + e.z[33]);
    }
    
    private void c(final String s, final String s2) {
        this.a().a(this.k(), e.z[47] + s + e.z[0] + s2);
    }
    
    private Class h(String s) {
        final String lowerCase = s.toLowerCase();
        s = String.valueOf(Character.toUpperCase(s.charAt(0))) + s.substring(1);
        s = e.z[14] + lowerCase + e.z[13] + s;
        Class<?> forName = null;
        try {
            forName = Class.forName(s);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return forName;
    }
    
    private s a(final Class clazz) {
        try {
            return clazz.getConstructor((Class<?>[])null).newInstance((Object[])null);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    private int d(final String s, final String s2) {
        final int l = a.l;
        if (s.trim().equals(this.h().g()) && !flaxchat.d.b.a(e.z[27], true)) {
            return -1;
        }
        final Vector c = flaxchat.d.b.c();
        final int size = c.size();
        int n = 0;
        while (true) {
            Label_0094: {
                if (l == 0) {
                    break Label_0094;
                }
                if (this.e(s2, c.elementAt(n))) {
                    return (int)flaxchat.d.b.b().elementAt(n);
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
        s = flaxchat.f.d.c(s).trim();
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
        final int l = a.l;
        s = flaxchat.f.h.a(s, this.a().k());
        final StringTokenizer stringTokenizer = new StringTokenizer(s, e.z[32]);
        while (true) {
            Label_0136: {
                if (l == 0) {
                    break Label_0136;
                }
                s = stringTokenizer.nextToken();
                if (this.h(s)) {
                    this.a(e.z[29], e.z[28]);
                    return;
                }
                if (!s.startsWith("\u0001")) {
                    this.a(flaxchat.d.b.b(e.z[30], e.z[2]), "<" + this.i() + e.z[31] + s);
                }
                this.a().a(this.k(), s);
            }
            if (!stringTokenizer.hasMoreElements()) {
                return;
            }
            continue;
        }
    }
    
    public void e() {
        this.m.b();
    }
    
    public void f() {
        super.k.l().d(this.k());
        super.k.g().requestFocus();
    }
    
    public f n() {
        return this.p;
    }
    
    public void i() {
        this.m.b();
        if (this.p != null) {
            this.p.setText("");
        }
    }
    
    public void i(final String s) {
        if (this.p == null) {
            this.p = new f(this, this.m);
        }
        this.p.a(s);
    }
    
    public void j(final String s) {
        this.q.a(s);
    }
    
    public String j() {
        return this.k();
    }
    
    public h k() {
        return this.r;
    }
    
    public String k() {
        return this.q.g();
    }
    
    private String k(final String s) {
        return flaxchat.d.c.a(s);
    }
    
    public void a(final boolean b) {
        final int l = a.l;
        Label_0082: {
            if (b) {
                final Enumeration<s> elements = (Enumeration<s>)this.s.elements();
                while (true) {
                    Label_0034: {
                        if (l == 0) {
                            break Label_0034;
                        }
                        elements.nextElement().b();
                    }
                    if (elements.hasMoreElements()) {
                        continue;
                    }
                    break;
                }
                if (l == 0) {
                    break Label_0082;
                }
            }
            final Enumeration<s> elements2 = (Enumeration<s>)this.s.elements();
            while (true) {
                Label_0073: {
                    if (l == 0) {
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
            this.a().a(this.k(), e.z[46]);
            return;
        }
        this.a().a(this.k(), e.z[45]);
    }
    
    public void b(final boolean b) {
        if (b) {
            this.t.a(String.valueOf(this.k()) + e.z[15]);
            return;
        }
        if (this.w == null) {
            this.t.a("");
            return;
        }
        this.t.a(this.w);
    }
    
    public void f(final String w) {
        this.w = w;
        if (w == null) {
            return;
        }
        this.t.a(w);
    }
    
    public Container l() {
        return this.u;
    }
    
    public flaxchat.h.c g(final String s) {
        final flaxchat.h.c c = new flaxchat.h.c();
        c.c(this.k());
        c.c(this.i());
        return c;
    }
    
    static {
        e.z = new String[] { z(z("g\u0007\t#")), z(z("!F\u001cdkj^Dq.-\nImk")), z(z("D\u001bV")), z(z("5O\u0007j.1O\u0000N8 i\u000bo$5")), z(z("6_\u0001q2j]\rm/(]")), z(z("6_\u0001q2jZ\u000bs>7")), z(z("4G\ro.")), z(z("\u0014E\u0011w#")), z(z("\u0002K\u0017w")), z(z("6_\u0001q2j^\u000bl'%K\u0016")), z(z("\u0004O\nw.5")), z(z("4B\u000bt\u00064M&b9")), z(z("\u0012Y\u0001qk\nY\u0003#")), z(z("il\bb3")), z(z("!F\u0005{(/K\u0010-")), z(z("gC\bf?.\n\u001db1.S\u000bq")), z(z("\u0012Y\u0001qk6_\rw.#\n-Q\bgY\u0001q=\"X")), z(z("%K\bs.3O\u0003j&iI\u000bn")), z(z("\fK\u0006v'")), z(z("&I\u0007f;3O\u0000")), z(z("\u0015O\u0000")), z(z("7F\u001d")), z(z("5O\u000e")), z(z(".D\u0012")), z(z("&I\u0007")), z(z("5O\u0000")), z(z("jG")), z(z("4O\be\u00052N\u0003f")), z(z("\u0014_\nv(2G\u0011y/&\n\u0006vk3K\u0016yk,E\nv8&G\u0005y8.D\rye")), z(z("D\u001aP")), z(z("4O\nw\u00064M'l'(X")), z(z("y\n")), z(z("M'")), z(z("g\u0007\u0017#\u0002gK\t#9\"K\u0000z")), z(z("!F\u001cdkj^Dq.#\nImk")), z(z("$E\bl9")), z(z(")_\u0000d.\u0014I\u0016j;3d\u0005n.")), z(z("4B\u000bt\u00064M6`=#~\rn.")), z(z(")_\u0000d.\u0017X\rm?")), z(z("FK\u0007w\"(D")), z(z("!F\u001cd")), z(z("\u000fb^n&}Y\u0017")), z(z("\u001a\n")), z(z("4B\u000bt\f&G\u0001P?\"Z")), z(z("%F\u000b` \u000eD*v/ O")), z(z("bD\u000fan")), z(z("bA\u0006&")), z(z("!F\u001cdkj^Db($\nImk")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'K';
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
                    c2 = 'G';
                    break;
                }
                case 1: {
                    c2 = '*';
                    break;
                }
                case 2: {
                    c2 = 'd';
                    break;
                }
                case 3: {
                    c2 = '\u0003';
                    break;
                }
                default: {
                    c2 = 'K';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
