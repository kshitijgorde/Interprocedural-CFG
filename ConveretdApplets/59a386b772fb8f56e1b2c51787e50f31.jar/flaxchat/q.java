// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat;

import java.util.Enumeration;
import java.util.Date;
import flaxchat.i.b;
import flaxchat.e.d;
import java.awt.Component;
import flaxchat.e.j;
import flaxchat.c.a;
import java.util.StringTokenizer;
import flaxchat.c.c;
import flaxchat.h.g;
import java.util.Hashtable;
import flaxchat.h.e;

public class q extends e
{
    private m z;
    private Hashtable A;
    private Hashtable B;
    private Hashtable C;
    private static String[] D;
    
    public q(final m z, final String s, final String s2, final String s3) {
        this.A = new Hashtable();
        this.B = new Hashtable();
        this.C = new Hashtable();
        this.z = z;
        this.o(s);
        this.m(s2);
        this.a(s3);
    }
    
    public String a(final String s) {
        return this.A.get(s);
    }
    
    public void a(final m z) {
        this.z = z;
    }
    
    protected void a(final String s, final g[] array) {
        final boolean s2 = flaxchat.m.s;
        final c c = (c)this.z.c(s);
        if (c == null) {
            return;
        }
        c.a();
        final g[] q = this.q(c.n());
        int n = 0;
        while (true) {
            Label_0056: {
                if (!s2) {
                    break Label_0056;
                }
                c.a(q[n]);
                ++n;
            }
            if (n >= q.length) {
                return;
            }
            continue;
        }
    }
    
    private boolean a(final String s, final String s2) {
        final boolean s3 = flaxchat.m.s;
        final StringTokenizer stringTokenizer = new StringTokenizer(s2, " ");
        while (true) {
            Label_0034: {
                if (!s3) {
                    break Label_0034;
                }
                if (s.equalsIgnoreCase(stringTokenizer.nextToken())) {
                    return true;
                }
            }
            if (!stringTokenizer.hasMoreTokens()) {
                return false;
            }
            continue;
        }
    }
    
    private String a(final StringTokenizer stringTokenizer) {
        while (true) {
            Label_0024: {
                if (!flaxchat.m.s) {
                    break Label_0024;
                }
                final String nextToken = stringTokenizer.nextToken();
                if (!nextToken.equals(" ")) {
                    return nextToken;
                }
            }
            if (!stringTokenizer.hasMoreTokens()) {
                return null;
            }
            continue;
        }
    }
    
    private String b(final StringTokenizer stringTokenizer) {
        final boolean s = flaxchat.m.s;
        final StringBuffer sb = new StringBuffer();
        while (true) {
            Label_0025: {
                if (!s) {
                    break Label_0025;
                }
                sb.append(stringTokenizer.nextToken());
            }
            if (!stringTokenizer.hasMoreTokens()) {
                return sb.toString();
            }
            continue;
        }
    }
    
    private String b(final String s, final String s2) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s2, " ", true);
        if (s.equalsIgnoreCase(q.D[57])) {
            this.a(stringTokenizer);
            final String trim = this.a(stringTokenizer).trim();
            if (s2.indexOf(q.D[65]) != -1) {
                this.C.put(trim, trim);
            }
            this.t(q.D[43]);
            this.t(String.valueOf('\u0003') + "4" + q.D[72] + '\u0003' + "1" + trim);
            this.t(String.valueOf('\u0003') + "4" + q.D[51] + '\u0003' + q.D[44] + this.a(stringTokenizer) + "@" + this.a(stringTokenizer) + " " + this.b(stringTokenizer).substring(2));
            return s;
        }
        if (s.equalsIgnoreCase(q.D[67])) {
            this.a(stringTokenizer);
            this.t(String.valueOf('\u0003') + "4" + q.D[69] + this.a(stringTokenizer) + q.D[42] + '\u0003' + "1" + this.b(stringTokenizer).substring(2));
            return s;
        }
        if (s.equalsIgnoreCase(q.D[52])) {
            this.a(stringTokenizer);
            this.t(String.valueOf('\u0003') + "4" + q.D[46] + this.a(stringTokenizer) + q.D[42] + '\u0003' + "1" + this.a(stringTokenizer) + " " + this.b(stringTokenizer).substring(2));
            return s;
        }
        if (s.equalsIgnoreCase(q.D[66])) {
            this.a(stringTokenizer);
            this.a(stringTokenizer);
            final int int1 = Integer.parseInt(this.a(stringTokenizer));
            this.t(String.valueOf('\u0003') + "4" + q.D[53] + ((int1 <= 59) ? (String.valueOf(int1) + q.D[38]) : (String.valueOf(int1 / 60) + q.D[75])) + q.D[59]);
            this.t(String.valueOf('\u0003') + "1" + q.D[61] + flaxchat.e.g.a(String.valueOf(this.a(stringTokenizer)) + q.D[39]));
            this.t(q.D[43]);
            return s;
        }
        if (s.equalsIgnoreCase(q.D[48])) {
            this.a(stringTokenizer);
            this.t(String.valueOf('\u0003') + q.D[35] + this.a(stringTokenizer) + q.D[62] + this.a(stringTokenizer) + "@" + this.a(stringTokenizer) + " " + this.a(stringTokenizer) + " " + this.b(stringTokenizer).substring(2));
            return s;
        }
        if (s.equalsIgnoreCase(q.D[73])) {
            this.a(stringTokenizer);
            this.t(String.valueOf(new StringBuffer("\n").append('\u0003').append(q.D[35]).append(this.a(stringTokenizer)).append(" ").toString()) + this.a(stringTokenizer) + " " + (String.valueOf(this.a(stringTokenizer)) + "@" + this.a(stringTokenizer) + " " + this.a(stringTokenizer) + " ") + this.b(stringTokenizer));
            return s;
        }
        if (s.equalsIgnoreCase(q.D[64])) {
            this.a(stringTokenizer);
            this.t(String.valueOf('\u0003') + q.D[35] + this.b(stringTokenizer));
            return s;
        }
        if (s.equalsIgnoreCase(q.D[58])) {
            this.a(stringTokenizer);
            this.t(String.valueOf('\u0003') + "1" + this.a(stringTokenizer) + q.D[56]);
            return s;
        }
        if (this.a(s, q.D[63])) {
            this.a(stringTokenizer);
            this.t(String.valueOf('\u0003') + q.D[35] + this.a(stringTokenizer) + " " + this.b(stringTokenizer).substring(2) + "\n" + '\u0003' + q.D[60]);
            return s;
        }
        if (s.equalsIgnoreCase(q.D[45])) {
            this.a(stringTokenizer);
            this.t(String.valueOf('\u0003') + "4" + this.a(stringTokenizer) + q.D[41] + this.b(stringTokenizer).substring(2));
            return s;
        }
        if (this.a(s, q.D[68])) {
            this.a(stringTokenizer);
            this.t(String.valueOf('\u0003') + q.D[35] + this.b(stringTokenizer).substring(2));
            return s;
        }
        if (this.a(s, q.D[47])) {
            this.a(stringTokenizer);
            this.u(String.valueOf('\u0003') + q.D[50] + this.a(stringTokenizer) + " " + this.b(stringTokenizer).substring(1));
            return s;
        }
        if (this.a(s, q.D[71])) {
            this.a(stringTokenizer);
            this.u(String.valueOf('\u0003') + "1" + this.a(stringTokenizer) + " " + this.b(stringTokenizer).substring(1));
            return s;
        }
        if (this.a(s, q.D[55])) {
            this.a(stringTokenizer);
            this.u(String.valueOf('\u0003') + "1" + this.a(stringTokenizer) + " " + this.b(stringTokenizer).substring(1));
            return s;
        }
        if (this.a(s, q.D[54])) {
            this.a(stringTokenizer);
            this.t(String.valueOf('\u0003') + q.D[35] + this.a(stringTokenizer) + " " + this.b(stringTokenizer).substring(2));
            return s;
        }
        if (this.a(s, q.D[40])) {
            return s;
        }
        if (this.a(s, q.D[49])) {
            this.a(stringTokenizer);
            this.t(String.valueOf('\u0003') + "5" + this.a(stringTokenizer) + " " + this.b(stringTokenizer).substring(2));
            return s;
        }
        if (s.equalsIgnoreCase(q.D[70])) {
            this.a(stringTokenizer);
            final a c = this.z.c(this.a(stringTokenizer));
            if (!(c instanceof c)) {
                return s;
            }
            final c c2 = (c)c;
            if (c2.q != null) {
                c2.q.add(this.a(stringTokenizer));
            }
            return s;
        }
        else {
            if (!s.equalsIgnoreCase(q.D[74])) {
                return null;
            }
            this.a(stringTokenizer);
            final a c3 = this.z.c(this.a(stringTokenizer));
            if (!(c3 instanceof c)) {
                return s;
            }
            final c c4 = (c)c3;
            this.a(c4);
            c4.q.removeAll();
            return s;
        }
    }
    
    protected void b(final int n, final String s) {
        if (this.b(String.valueOf(n), s) != null) {
            return;
        }
        if (n >= 401) {
            if (n == 466) {
                this.b("1", (Object[])null);
                return;
            }
            this.a(s, true);
        }
    }
    
    private void a(final c c) {
        final boolean s = flaxchat.m.s;
        final d q = c.q;
        if (flaxchat.e.j.a(this.z, q, flaxchat.q.D[25], new String[] { flaxchat.q.D[23], flaxchat.q.D[24] }) == -1) {
            return;
        }
        if (q.getSelectedIndexes() == null) {
            return;
        }
        final String[] selectedItems = q.getSelectedItems();
        if (selectedItems == null) {
            return;
        }
        int n = 0;
        while (true) {
            Label_0142: {
                if (!s) {
                    break Label_0142;
                }
                final String s2 = selectedItems[n];
                this.e(flaxchat.q.D[26] + c.n() + flaxchat.q.D[22] + s2);
                q.remove(s2);
                ++n;
            }
            if (n >= selectedItems.length) {
                return;
            }
            continue;
        }
    }
    
    protected void l(final String s) {
        this.b("2", new String[] { s });
    }
    
    protected void b(final String s, final String s2, final String s3, final String s4, final String s5) {
        this.a(s4, q.D[36], new String[] { s, s2, s3, s4, s5 }, false);
    }
    
    protected void c(final String s, final String s2, final String s3, final String s4, final String s5) {
        if (s.equals(q.D[9]) && (s5.startsWith(q.D[7]) || s5.startsWith(q.D[11]) || s5.startsWith(q.D[8]))) {
            final String a = flaxchat.e.j.a(this.z, q.D[10]);
            if (a == null) {
                return;
            }
            this.z.h().e(q.D[6] + a);
        }
        if (s5.startsWith("\u0001")) {
            this.z.e().c(q.D[5] + s + " " + s5 + "]");
            return;
        }
        this.a("3", new String[] { s, s2, s3, s4, s5 });
    }
    
    protected void c(final String s, final String s2) {
        this.A.put(s, s2);
        final a c = this.z.c(s);
        if (c instanceof c) {
            ((c)c).m();
        }
        this.a(s, "4", new Object[] { s, s2 }, false);
    }
    
    protected void a(final String s, final String s2, final String s3, final long n, final boolean b) {
        this.A.put(s, s2);
        final a c = this.z.c(s);
        if (c instanceof c) {
            ((c)c).m();
        }
        this.a(s, "5", new String[] { s, s2, s3 }, false);
    }
    
    protected void d() {
        this.z.l().t();
    }
    
    protected void e() {
        this.z.l().p();
    }
    
    protected void b(final String s, final String s2, final String s3, final String s4) {
        if (s2.equals(this.g())) {
            this.z.a(s, true);
            this.q(s);
            try {
                Thread.sleep(100L);
                return;
            }
            catch (InterruptedException ex) {
                return;
            }
        }
        final c c = (c)this.z.c(s);
        if (c == null) {
            return;
        }
        c.a(this.d(s, s2));
        c.c(flaxchat.i.c.a("6", new String[] { s, s2, s3, s4 }));
    }
    
    protected void h(final String s, final String s2, final String s3, final String s4, final String s5) {
        this.a(s, "7", new String[] { s, s2, s3, s4, s5 }, false);
        this.d(s, s5);
    }
    
    protected void j(final String s, final String s2, final String s3, final String s4, final String s5) {
        this.a(s, "8", new String[] { s, s2, s3, s4, s5 }, false);
        this.d(s, s5);
    }
    
    protected void g(final String s, final String s2, final String s3, final String s4, final String s5) {
        this.a(s, "9", new String[] { s, s2, s3, s4, s5 }, false);
        this.d(s, s5);
    }
    
    protected void i(final String s, final String s2, final String s3, final String s4, final String s5) {
        this.a(s, q.D[0], new String[] { s, s2, s3, s4, s5 }, false);
        this.d(s, s5);
    }
    
    private void d(final String s, final String s2) {
        final c c = (c)this.z.c(s);
        if (c == null) {
            return;
        }
        c.e(s2);
    }
    
    protected void a(final String s, final String s2, final String s3, final String s4) {
        super.a(s, s2, s3, s4);
        if (q.D[31].equals(s4)) {
            final a c = this.z.c(s);
            if (c == null) {
                return;
            }
            c.b(true);
        }
        else if (q.D[29].equals(s4)) {
            final a c2 = this.z.c(s);
            if (c2 == null) {
                return;
            }
            c2.b(false);
        }
        else {
            final a c3 = this.z.c(s);
            if ((c3 == null || !this.z.b(c3)) && this.x(s)) {
                this.a(s, flaxchat.i.c.a(q.D[28]));
                return;
            }
            final a a = this.z.a(0, new g("", s));
            a.b(false);
            a.a(flaxchat.i.b.b(q.D[17], q.D[15]), "<" + s + q.D[18] + s4);
            a.f(q.D[32] + FlaxChat.e(q.D[30]));
        }
    }
    
    private boolean b(final String s) {
        return flaxchat.h.d.c(s).toLowerCase().indexOf(this.g().toLowerCase()) != -1;
    }
    
    protected void a(final String s, final String s2, final String s3, final String s4, String string) {
        if (this.b(string) && !s.equalsIgnoreCase(this.z.f())) {
            this.a(q.D[16], new String[] { s2, s });
        }
        final a c = this.z.c(s);
        Label_0175: {
            if (c instanceof c) {
                final c c2 = (c)c;
                string = "<" + s2 + q.D[18] + string;
                c2.a(flaxchat.i.b.b(q.D[17], q.D[15]), string);
                if (!flaxchat.m.s) {
                    break Label_0175;
                }
            }
            this.z.k().d(flaxchat.i.c.a(q.D[12], new String[] { s, s2, s3, s4, string }));
        }
        if (string.equalsIgnoreCase(q.D[13])) {
            this.a(s, String.valueOf(s2) + q.D[14] + new Date().toString());
        }
    }
    
    protected void a(final String s, final boolean b, final int n, final String s2) {
        this.A.put(s, s2);
        flaxchat.c.b m = this.z.m();
        if (m == null || m.h()) {
            m = (flaxchat.c.b)this.z.a(3, q.D[19]);
        }
        if (b) {
            m.g();
            m.d();
        }
        m.a(s, n, s2);
    }
    
    protected void e(final String s, final String s2, final String s3, final String s4) {
        final boolean s5 = flaxchat.m.s;
        final Enumeration u = this.z.u();
        final a c = this.z.c(s);
        while (true) {
            Label_0133: {
                if (!(c instanceof flaxchat.c.e)) {
                    break Label_0133;
                }
                ((flaxchat.c.e)c).e(s4);
                if (!s5) {
                    break Label_0133;
                }
                final a a = u.nextElement();
                if (a instanceof c) {
                    final c c2 = (c)a;
                    if (c2.b(new g("", s)) != null) {
                        this.a(c2.n(), q.D[33], new String[] { s, s2, s3, s4 }, false);
                    }
                }
            }
            if (!u.hasMoreElements()) {
                this.a(s, q.D[33], new String[] { s, s2, s3, s4 }, false);
                return;
            }
            continue;
        }
    }
    
    protected void c(final String s, final String s2, final String s3, final String s4) {
        final c c = (c)this.z.c(s);
        if (c == null) {
            return;
        }
        if (s2.equals(this.g())) {
            this.b(q.D[34], new String[] { s, s2, s3, s4 });
            this.z.a(c);
            return;
        }
        c.c(flaxchat.i.c.a(q.D[35], new String[] { s, s2, s3, s4 }));
        c.b(new g("", s2));
    }
    
    protected void a(final String s, final String s2, final String s3, final String s4, final String s5, final String s6) {
        super.a(s, s2, s3, s4, s5, s6);
        final c c = (c)this.z.c(s);
        if (c == null) {
            return;
        }
        Label_0151: {
            if (s5.equals(this.g())) {
                c.c(flaxchat.i.c.a(q.D[20], new String[] { s, s2, s3, s4, s5, s6 }));
                this.z.a(c);
                if (!flaxchat.m.s) {
                    break Label_0151;
                }
            }
            c.c(flaxchat.i.c.a(q.D[21], new String[] { s, s2, s3, s4, s5, s6 }));
        }
        c.b(new g("", s5));
    }
    
    protected void o(final String s, final String s2, final String s3, final String s4, final String s5) {
        if (flaxchat.e.j.a(this.z, flaxchat.i.c.a(q.D[3], new String[] { s, s2, s3, s4, s5 }), flaxchat.i.c.b(q.D[1]), new String[] { flaxchat.i.c.b(q.D[4]), flaxchat.i.c.b(q.D[2]) }, flaxchat.i.c.b(q.D[4])) != 0) {
            return;
        }
        this.z.g(s5);
    }
    
    protected void b(final String s, final String s2) {
        super.b(s, s2);
        this.z.l().d(null);
    }
    
    protected void d(final String s, final String s2, final String s3, final String s4) {
        final boolean s5 = flaxchat.m.s;
        this.z.a(s, s4);
        this.e(s, s4);
        final g g = new g("", s);
        final Enumeration u = this.z.u();
        while (true) {
            Label_0145: {
                if (!s5) {
                    break Label_0145;
                }
                final a a = u.nextElement();
                if (a instanceof c) {
                    final c c = (c)a;
                    c.b(g);
                    final g d = this.d(c.n(), s4);
                    if (d != null) {
                        c.a(d);
                        this.a(c.n(), q.D[27], new String[] { s, s2, s3, s4 }, false);
                    }
                }
            }
            if (!u.hasMoreElements()) {
                return;
            }
            continue;
        }
    }
    
    public void q(final String s) {
        this.e(q.D[37] + s);
    }
    
    protected void a(final String s, String a, final Object[] array, final boolean b) {
        final a c = this.z.c(s);
        if (c == null) {
            return;
        }
        a = flaxchat.i.c.a(a, array);
        if (b) {
            c.d(a);
            return;
        }
        c.c(a);
    }
    
    private void t(final String s) {
        this.b(s, false);
    }
    
    private void a(final String s, final Object[] array) {
        this.t(flaxchat.i.c.a(s, array));
    }
    
    protected void b(final String s, final Object[] array) {
        this.u(flaxchat.i.c.a(s, array));
    }
    
    protected void b(final String s, final boolean b) {
        if (b) {
            this.z.e().d(s);
            return;
        }
        this.z.e().c(s);
    }
    
    private void u(final String s) {
        this.a(s, false);
    }
    
    protected void a(final String s, final boolean b) {
        if (b) {
            this.z.k().d(s);
            return;
        }
        this.z.k().c(s);
    }
    
    public void v(final String s) {
        this.B.remove(s);
    }
    
    public void w(final String s) {
        this.B.put(s, s);
    }
    
    public void e(final String s, final String s2) {
        if (this.B.remove(s) == null) {
            return;
        }
        this.B.put(s2, s2);
    }
    
    public Enumeration a() {
        return this.B.keys();
    }
    
    public boolean x(final String s) {
        return this.B.get(s) != null;
    }
    
    static {
        q.D = new String[] { z(z("3I")), z(z("3O[P")), z(z("3O[R")), z(z("3O")), z(z("3O[Q")), z(z("\u0001IA@)")), z(z("R+<6?Q>U.;A2&% TY<$7L-<&+\"")), z(z("[\u0016\u0000@\u001cm\u000eU\b\u0013t\u001c")), z(z("@\fU\u000e\u001ba\u0012U\u000b\u0013{")), z(z("L\u0010\u0016\u000b!g\u000b\u0003")), z(z("L\u0010\u0016\u000bRQ\u0010\u0013\u0012\u0017l\u0010\u000f")), z(z("@\fU\u000b\u0013{\u0010\u0001\f\u001b")), z(z("3H")), z(z("v\u0010\u0018\u0005")), z(z("8Y!\b\u0017\"\r\u001c\r\u0017\"\u0010\u0006@\u001cm\u000eU")), z(z("\u0001IA")), z(z("6N")), z(z("p\u001c\u0016\t\u0017t\u001c\u0011-\u0001e:\u001a\f\u001dp")), z(z("<Y")), z(z("A\u0011\u0014\u000e\u001cg\u0015\u0006")), z(z("3M")), z(z("3L")), z(z("\"T\u0017@")), z(z("@\u0018\u001b\tRI\u0018\u0019\u0004\u001bp")), z(z("T\u0018\u000f\u0007\u0017\u00e5")), z(z("@\u0018\u001b@>k\n\u0001\u0005\u0001k")), z(z("O61%R")), z(z("3A")), z(z("D6'#7]4&'")), z(z("'\u0017\u001e\u0002W")), z(z("J1O\r\u001f8\n\u0006")), z(z("'\u0012\u0017E")), z(z("Q\u0016\u001b@\u001bn\u001c\u0001\tRc\u0015\u0018\u0001Rx\u0018\u0018\u0001\u001ckYO@")), z(z("3N")), z(z("3K")), z(z("3J")), z(z("3@")), z(z("L88%!\"")), z(z("\"\n\u0014\u000e\u001b{\u001c")), z(z("2IE")), z(z("6IF")), z(z("\"\u0014\u0010\u0013\u0015w\u0015U\u0013\u0017`\u001c\u0005@H\"")), z(z("+Y")), z(z("/TXM_/TXM_")), z(z("3Y")), z(z("1ID")), z(z("Q\f\u001b\u0015\u0011wYU@R\"YO@Z")), z(z("0LD@@7KURG1YGUF\"KCUR0OC@@4N")), z(z("1HA")), z(z("1LD@A;HUSE3YFXC")), z(z("3v")), z(z("J\u0016\u0006\u0014RC\u001d\u0007\u0005\u0001kYO")), z(z("1HG")), z(z("Q\u0016\u001b@\u0019m\u0017\u0000\u0013\u001fcYO@")), z(z("6AG@F2HUTE3YAWA")), z(z("1N@@A5KUSE4")), z(z("\"\u0012\u0014\u0019\u001bv\u0015\u001c@\u0010k\u000bU\u0012\u0007o\f\u000f\u0004\u0007p")), z(z("1HD")), z(z("1IB")), z(z(".Y\u001a\u000e\u0011gW")), z(z("3JX")), z(z("E\u0010\u0007\t\u0001\"\u0003\u0014\r\u0013l\u0010O@")), z(z("\"\u000e\u0014\u0013R")), z(z("6IC")), z(z("1HF")), z(z("D\u0015\u0014\u00181j\u0018\u0001")), z(z("1HB")), z(z("1HL")), z(z("1I@@A2OUTJ3")), z(z("I\u0018\u001b\u0001\u001en\u0018\u0007@R\"YO@Z")), z(z("1OB")), z(z("3YG@A\"MUUR4")), z(z("V\u0018\u001e\r\u0013\"0\u0006\t\u001f\"YO@")), z(z("1LG")), z(z("1OM")), z(z("\"\u001d\u0014\u000b\u001bi\u0018")) };
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
                    c2 = '\u0002';
                    break;
                }
                case 1: {
                    c2 = 'y';
                    break;
                }
                case 2: {
                    c2 = 'u';
                    break;
                }
                case 3: {
                    c2 = '`';
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
