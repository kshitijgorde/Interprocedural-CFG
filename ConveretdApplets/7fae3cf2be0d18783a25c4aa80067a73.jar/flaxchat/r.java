// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat;

import java.util.Enumeration;
import java.util.Date;
import flaxchat.d.b;
import flaxchat.a.d;
import java.awt.Component;
import flaxchat.a.k;
import flaxchat.c.a;
import flaxchat.a.h;
import java.util.StringTokenizer;
import flaxchat.c.c;
import flaxchat.f.g;
import java.util.Hashtable;
import flaxchat.f.e;

public class r extends e
{
    private n z;
    private Hashtable A;
    private Hashtable B;
    public boolean C;
    private Hashtable D;
    private static String[] E;
    
    public boolean a() {
        return this.C;
    }
    
    public void c(final boolean c) {
        this.C = c;
    }
    
    public r(final n z, final String s, final String s2, final String s3) {
        this.A = new Hashtable();
        this.B = new Hashtable();
        this.C = false;
        this.D = new Hashtable();
        this.z = z;
        this.o(s);
        this.m(s2);
        this.a(s3);
    }
    
    public String a(final String s) {
        return this.A.get(s);
    }
    
    public void a(final n z) {
        this.z = z;
    }
    
    protected void a(final String s, final g[] array) {
        final int w = flaxchat.n.w;
        final c c = (c)this.z.c(s);
        if (c == null) {
            return;
        }
        c.a();
        final g[] q = this.q(c.l());
        int n = 0;
        while (true) {
            Label_0056: {
                if (w == 0) {
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
        final int w = flaxchat.n.w;
        final StringTokenizer stringTokenizer = new StringTokenizer(s2, " ");
        while (true) {
            Label_0034: {
                if (w == 0) {
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
                if (flaxchat.n.w == 0) {
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
        final int w = flaxchat.n.w;
        final StringBuffer sb = new StringBuffer();
        while (true) {
            Label_0025: {
                if (w == 0) {
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
        if (s.equalsIgnoreCase(r.E[19])) {
            this.a(stringTokenizer);
            final String trim = this.a(stringTokenizer).trim();
            if (s2.indexOf(r.E[25]) != -1) {
                this.D.put(trim, trim);
            }
            this.t(r.E[20]);
            this.t(String.valueOf('\u0003') + "4" + r.E[36] + '\u0003' + "1" + trim);
            this.t(String.valueOf('\u0003') + "4" + r.E[5] + '\u0003' + r.E[12] + this.a(stringTokenizer) + "@" + this.a(stringTokenizer) + " " + this.b(stringTokenizer).substring(2));
            return s;
        }
        if (s.equalsIgnoreCase(r.E[1])) {
            this.a(stringTokenizer);
            this.t(String.valueOf('\u0003') + "4" + r.E[38] + this.a(stringTokenizer) + r.E[35] + '\u0003' + "1" + this.b(stringTokenizer).substring(2));
            return s;
        }
        if (s.equalsIgnoreCase(r.E[32])) {
            this.a(stringTokenizer);
            this.t(String.valueOf('\u0003') + "4" + r.E[40] + this.a(stringTokenizer) + r.E[35] + '\u0003' + "1" + this.a(stringTokenizer) + " " + this.b(stringTokenizer).substring(2));
            return s;
        }
        if (s.equalsIgnoreCase(r.E[29])) {
            this.a(stringTokenizer);
            this.a(stringTokenizer);
            final int int1 = Integer.parseInt(this.a(stringTokenizer));
            this.t(String.valueOf('\u0003') + "4" + r.E[11] + ((int1 <= 59) ? (String.valueOf(int1) + r.E[24]) : (String.valueOf(int1 / 60) + r.E[26])) + r.E[39]);
            this.t(String.valueOf('\u0003') + "1" + r.E[2] + flaxchat.a.h.a(String.valueOf(this.a(stringTokenizer)) + r.E[13]));
            this.t(r.E[20]);
            return s;
        }
        if (s.equalsIgnoreCase(r.E[21])) {
            this.a(stringTokenizer);
            this.t(String.valueOf('\u0003') + r.E[18] + this.a(stringTokenizer) + r.E[16] + this.a(stringTokenizer) + "@" + this.a(stringTokenizer) + " " + this.a(stringTokenizer) + " " + this.b(stringTokenizer).substring(2));
            return s;
        }
        if (s.equalsIgnoreCase(r.E[31])) {
            this.a(stringTokenizer);
            this.t(String.valueOf(new StringBuffer("\n").append('\u0003').append(r.E[18]).append(this.a(stringTokenizer)).append(" ").toString()) + this.a(stringTokenizer) + " " + (String.valueOf(this.a(stringTokenizer)) + "@" + this.a(stringTokenizer) + " " + this.a(stringTokenizer) + " ") + this.b(stringTokenizer));
            return s;
        }
        if (this.a(s, r.E[8])) {
            this.a(stringTokenizer);
            this.t(String.valueOf('\u0003') + r.E[18] + this.b(stringTokenizer));
            return s;
        }
        if (s.equalsIgnoreCase(r.E[4])) {
            this.a(stringTokenizer);
            this.t(String.valueOf('\u0003') + "1" + this.a(stringTokenizer) + r.E[9]);
            return s;
        }
        if (this.a(s, r.E[15])) {
            this.a(stringTokenizer);
            this.t(String.valueOf('\u0003') + r.E[18] + this.a(stringTokenizer) + " " + this.b(stringTokenizer).substring(2) + "\n" + '\u0003' + r.E[30]);
            return s;
        }
        if (s.equalsIgnoreCase(r.E[28])) {
            this.a(stringTokenizer);
            this.t(String.valueOf('\u0003') + "4" + this.a(stringTokenizer) + r.E[14] + this.b(stringTokenizer).substring(2));
            return s;
        }
        if (this.a(s, r.E[34])) {
            this.a(stringTokenizer);
            this.t(String.valueOf('\u0003') + "4" + this.b(stringTokenizer).substring(1));
            return s;
        }
        if (this.a(s, r.E[27])) {
            this.a(stringTokenizer);
            this.u(String.valueOf('\u0003') + r.E[33] + this.a(stringTokenizer) + " " + this.b(stringTokenizer).substring(1));
            return s;
        }
        if (this.a(s, r.E[23])) {
            this.a(stringTokenizer);
            this.u(String.valueOf('\u0003') + "1" + this.a(stringTokenizer) + " " + this.b(stringTokenizer).substring(1));
            return s;
        }
        if (this.a(s, r.E[37])) {
            this.a(stringTokenizer);
            this.u(String.valueOf('\u0003') + "1" + this.a(stringTokenizer) + " " + this.b(stringTokenizer).substring(1));
            return s;
        }
        if (this.a(s, r.E[22])) {
            this.a(stringTokenizer);
            this.t(String.valueOf('\u0003') + r.E[18] + this.a(stringTokenizer) + " " + this.b(stringTokenizer).substring(1));
            return s;
        }
        if (this.a(s, r.E[10])) {
            return s;
        }
        if (this.a(s, r.E[7])) {
            this.a(stringTokenizer);
            this.t(String.valueOf(this.a(stringTokenizer)) + " " + this.b(stringTokenizer).substring(1));
            return s;
        }
        if (this.a(s, r.E[6])) {
            this.a(stringTokenizer);
            this.t(String.valueOf('\u0003') + "5" + this.a(stringTokenizer) + " " + this.b(stringTokenizer).substring(2));
            return s;
        }
        if (s.equalsIgnoreCase(r.E[17])) {
            this.a(stringTokenizer);
            final a c = this.z.c(this.a(stringTokenizer));
            if (!(c instanceof c)) {
                return s;
            }
            final c c2 = (c)c;
            if (c2.t != null) {
                c2.t.add(this.a(stringTokenizer));
            }
            return s;
        }
        else {
            if (!s.equalsIgnoreCase(r.E[3])) {
                return null;
            }
            this.a(stringTokenizer);
            final a c3 = this.z.c(this.a(stringTokenizer));
            if (!(c3 instanceof c)) {
                return s;
            }
            final c c4 = (c)c3;
            this.a(c4);
            c4.t.removeAll();
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
        final int w = flaxchat.n.w;
        final d t = c.t;
        if (flaxchat.a.k.a(this.z, t, r.E[55], new String[] { r.E[57], r.E[59] }) == -1) {
            return;
        }
        if (t.getSelectedIndexes() == null) {
            return;
        }
        final String[] selectedItems = t.getSelectedItems();
        if (selectedItems == null) {
            return;
        }
        int n = 0;
        while (true) {
            Label_0142: {
                if (w == 0) {
                    break Label_0142;
                }
                final String s = selectedItems[n];
                this.e(r.E[56] + c.l() + r.E[58] + s);
                t.remove(s);
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
        this.a(s4, r.E[50], new String[] { s, s2, s3, s4, s5 }, false);
    }
    
    protected void c(final String s, final String s2, final String s3, final String s4, final String s5) {
        if (s5.startsWith("\u0001")) {
            this.z.e().c(r.E[42] + s + " " + s5 + "]");
            return;
        }
        this.a("3", new String[] { s, s2, s3, s4, s5 });
    }
    
    protected void c(final String s, final String s2) {
        this.A.put(s, s2);
        final a c = this.z.c(s);
        if (c instanceof c) {
            ((c)c).k();
        }
        this.a(s, "4", new Object[] { s, s2 }, false);
    }
    
    protected void a(final String s, final String s2, final String s3, final long n, final boolean b) {
        this.A.put(s, s2);
        final a c = this.z.c(s);
        if (c instanceof c) {
            ((c)c).k();
        }
        this.a(s, "5", new String[] { s, s2, s3 }, false);
    }
    
    protected void d() {
        this.z.l().u();
    }
    
    protected void e() {
        this.z.l().q();
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
        c.c(flaxchat.d.c.a("6", new String[] { s, s2, s3, s4 }));
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
        this.a(s, r.E[52], new String[] { s, s2, s3, s4, s5 }, false);
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
        if (r.E[68].equals(s4)) {
            final a c = this.z.c(s);
            if (c == null) {
                return;
            }
            c.b(true);
        }
        else if (r.E[72].equals(s4)) {
            final a c2 = this.z.c(s);
            if (c2 == null) {
                return;
            }
            c2.b(false);
        }
        else {
            final a c3 = this.z.c(s);
            if (c3 == null || !this.z.b(c3)) {
                if (this.x(s)) {
                    this.a(s, flaxchat.d.c.a(r.E[70]));
                    return;
                }
                if (this.a()) {
                    this.a(s, flaxchat.d.c.a(r.E[73]));
                    return;
                }
            }
            final a a = this.z.a(0, new g("", s));
            a.b(false);
            if (s4.startsWith(r.E[65])) {
                a.c(r.E[69] + s + r.E[66]);
                this.z.l().play(this.z.l().getCodeBase(), s4.substring(6).trim());
                return;
            }
            a.a(flaxchat.d.b.b(r.E[46], r.E[43]), "<" + s + r.E[47] + s4);
            a.f(r.E[67] + FlaxChat.e(r.E[71]));
        }
    }
    
    private boolean b(final String s) {
        return flaxchat.f.d.c(s).toLowerCase().indexOf(this.g().toLowerCase()) != -1;
    }
    
    protected void a(final String s, final String s2, final String s3, final String s4, String string) {
        if (this.b(string) && !s.equalsIgnoreCase(this.z.f())) {
            this.a(r.E[44], new String[] { s2, s });
        }
        final a c = this.z.c(s);
        Label_0175: {
            if (c instanceof c) {
                final c c2 = (c)c;
                string = "<" + s2 + r.E[47] + string;
                c2.a(flaxchat.d.b.b(r.E[46], r.E[43]), string);
                if (flaxchat.n.w == 0) {
                    break Label_0175;
                }
            }
            this.z.k().d(flaxchat.d.c.a(r.E[49], new String[] { s, s2, s3, s4, string }));
        }
        if (string.equalsIgnoreCase(r.E[48])) {
            this.a(s, String.valueOf(s2) + r.E[45] + new Date().toString());
        }
    }
    
    protected void a(final String s, final boolean b, final int n, final String s2) {
        this.A.put(s, s2);
        flaxchat.c.b m = this.z.m();
        if (m == null || m.h()) {
            m = (flaxchat.c.b)this.z.a(3, r.E[51]);
        }
        if (b) {
            m.g();
            m.d();
        }
        m.a(s, n, s2);
    }
    
    protected void e(final String s, final String s2, final String s3, final String s4) {
        final int w = flaxchat.n.w;
        final Enumeration u = this.z.u();
        final a c = this.z.c(s);
        while (true) {
            Label_0133: {
                if (!(c instanceof flaxchat.c.e)) {
                    break Label_0133;
                }
                ((flaxchat.c.e)c).e(s4);
                if (w == 0) {
                    break Label_0133;
                }
                final a a = u.nextElement();
                if (a instanceof c) {
                    final c c2 = (c)a;
                    if (c2.b(new g("", s)) != null) {
                        this.a(c2.l(), r.E[64], new String[] { s, s2, s3, s4 }, false);
                    }
                }
            }
            if (!u.hasMoreElements()) {
                this.a(s, r.E[64], new String[] { s, s2, s3, s4 }, false);
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
            this.b(r.E[41], new String[] { s, s2, s3, s4 });
            this.z.a(c);
            return;
        }
        c.c(flaxchat.d.c.a(r.E[18], new String[] { s, s2, s3, s4 }));
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
                c.c(flaxchat.d.c.a(r.E[54], new String[] { s, s2, s3, s4, s5, s6 }));
                this.z.a(c);
                if (flaxchat.n.w == 0) {
                    break Label_0151;
                }
            }
            c.c(flaxchat.d.c.a(r.E[53], new String[] { s, s2, s3, s4, s5, s6 }));
        }
        c.b(new g("", s5));
    }
    
    protected void o(final String s, final String s2, final String s3, final String s4, final String s5) {
        if (flaxchat.a.k.a(this.z, flaxchat.d.c.a(r.E[61], new String[] { s, s2, s3, s4, s5 }), flaxchat.d.c.b(r.E[62]), new String[] { flaxchat.d.c.b(r.E[63]), flaxchat.d.c.b(r.E[60]) }, flaxchat.d.c.b(r.E[63])) != 0) {
            return;
        }
        this.z.g(s5);
    }
    
    protected void b(final String s, final String s2) {
        super.b(s, s2);
        this.z.l().d(null);
        this.z.l().a(s2);
    }
    
    protected void d(final String s, final String s2, final String s3, final String s4) {
        final int w = flaxchat.n.w;
        this.z.a(s, s4);
        this.e(s, s4);
        final g g = new g("", s);
        final Enumeration u = this.z.u();
        while (true) {
            Label_0144: {
                if (w == 0) {
                    break Label_0144;
                }
                final a a = u.nextElement();
                if (a instanceof c) {
                    final c c = (c)a;
                    c.b(g);
                    final g d = this.d(c.l(), s4);
                    if (d != null) {
                        c.a(d);
                        this.a(c.l(), r.E[0], new String[] { s, s2, s3, s4 }, false);
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
        this.e(r.E[74] + s);
    }
    
    protected void a(final String s, String a, final Object[] array, final boolean b) {
        final a c = this.z.c(s);
        if (c == null) {
            return;
        }
        a = flaxchat.d.c.a(a, array);
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
        this.t(flaxchat.d.c.a(s, array));
    }
    
    protected void b(final String s, final Object[] array) {
        this.u(flaxchat.d.c.a(s, array));
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
    
    public Enumeration b() {
        return this.B.keys();
    }
    
    public boolean x(final String s) {
        return this.B.get(s) != null;
    }
    
    static {
        r.E = new String[] { z(z(" \u000e")), z(z("\"\u0007\u0013")), z(z("V_X3'1LK75\u007f_\u0010z")), z(z("\"\u0000\u0012")), z(z("\"\u0006\u001d")), z(z("YYY.tPRX?'x\u0016\u0010")), z(z("\"\u0003\u001bzg(\u0007\nic \u0016\u0019be")), z(z("#\u0002\u0019zf \u000f\nha'\u0016\u0018oc1\u0004\u001fbt#\u0004\u0013")), z(z("\"\u0007\u0019zg#\u000e\nic)")), z(z("1]K#=eZCz6xD\n(!|CP>!c")), z(z("%\u0006\u0019")), z(z("BYDz?~X_)9p\u0016\u0010z")), z(z(" \u0016")), z(z("!\u0006\u001a")), z(z("1[O)3dZ\n)1sSZzn1")), z(z("%\u0006\u001c")), z(z("1AK)t")), z(z("\"\u0000\u001d")), z(z(" \u0005")), z(z("\"\u0007\u001b")), z(z("<\u001b\u0007wy<\u001b\u0007wy")), z(z("\"\u0007\u001e")), z(z("%\u000e\u0018z`!\u0007\nnc \u0016\u001emg")), z(z(" \u0016\u0018zg1\u0002\not'")), z(z("1EK4=hS")), z(z("WZK\"\u0017yW^")), z(z("1RK1=zW")), z(z("#\u0003\u001bzf$\u0004\nha\"\u0016\u0018o`1\u0004\u001cot#\u0000\u001czf'\u0001")), z(z("\"\u0006\u001b")), z(z("\"\u0007\u001d")), z(z(" \u0005\u0007")), z(z("\"\u0003\u0018")), z(z("\"\u0007\u0018")), z(z(" 9")), z(z("\"\u0006\u001fzg!\u0000\nnl ")), z(z("8\u0016")), z(z("EWA751\u007fY391\u0016\u0010z")), z(z("\"\u0001\u001fzg&\u0004\nic'")), z(z("ZWD;8}WXzt1\u0016\u0010z|")), z(z("=\u0016E47t\u0018")), z(z("BCD/7d\u0016\nzt1\u0016\u0010z|")), z(z(" \u0004")), z(z("\u0012\u0006\u001ez\u000f")), z(z("\u0012\u0006\u001e")), z(z("%\u0001")), z(z("+\u0016~211BC711_Yz:~A\n")), z(z("cSI31gSN\u0017'vuE6;c")), z(z("/\u0016")), z(z("e_G?")), z(z(" \u0007")), z(z(" \u000f")), z(z("R^K4:tZY")), z(z(" \u0006")), z(z(" \u0003")), z(z(" \u0002")), z(z("SWDz\u0018xE^?'x")), z(z("\\yn\u001ft")), z(z("SWD3tZWF>=c")), z(z("1\u001bHz")), z(z("GWP=1\u00f6")), z(z(" \u0000\u0004h")), z(z(" \u0000")), z(z(" \u0000\u0004j")), z(z(" \u0000\u0004k")), z(z(" \u0001")), z(z("\u0007ff\u001b\r")), z(z("L\u0016Y3.t\u0016Y?'1QE40tDN3z")), z(z("BYDz=}S^3tpZG;tkWG;:x\u0016\u0010z")), z(z("4]H\u007f")), z(z("\u0012\u0006\u001c\u0001")), z(z("Wyx\u0019\u0011N{y\u001d")), z(z("Y~\u001079+EY")), z(z("4XA8q")), z(z("^LO6=|\u0016a;$pZC")), z(z("_wg\u001f\u00071")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'T';
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
                    c2 = '\u0011';
                    break;
                }
                case 1: {
                    c2 = '6';
                    break;
                }
                case 2: {
                    c2 = '*';
                    break;
                }
                case 3: {
                    c2 = 'Z';
                    break;
                }
                default: {
                    c2 = 'T';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
