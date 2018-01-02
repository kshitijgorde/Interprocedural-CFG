import java.io.Writer;
import java.io.OutputStreamWriter;
import java.awt.Frame;
import com.ms.security.PolicyEngine;
import com.ms.security.PermissionID;
import netscape.security.PrivilegeManager;
import java.awt.CheckboxMenuItem;
import java.applet.AppletContext;
import java.io.BufferedWriter;
import java.net.Socket;

// 
// Decompiled by Procyon v0.5.30
// 

class bf
{
    Socket a;
    BufferedWriter b;
    p c;
    bu d;
    br e;
    bq f;
    be g;
    n h;
    j i;
    bk j;
    u k;
    v l;
    bh m;
    AppletContext n;
    bv o;
    bw p;
    
    void a() {
        this.c();
        this.c.dispose();
    }
    
    bf(final p c, final v l, final bh m, final bu d, final String s, final String s2, final irc irc, final String s3, final String s4, final br e, final bq f, final be g, final u k, final n h, final j i, final bk j, final CheckboxMenuItem checkboxMenuItem, final CheckboxMenuItem checkboxMenuItem2, final boolean b, final CheckboxMenuItem checkboxMenuItem3, final AppletContext n) {
        this.c = c;
        this.l = l;
        this.m = m;
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.i = i;
        this.k = k;
        this.j = j;
        this.n = n;
        if (irc.h) {
            try {
                if (System.getProperty(b("\u007f\r0Crc\t(F3g")).startsWith(b("[\t2Q?t\u001c#"))) {
                    PrivilegeManager.enablePrivilege(b("@\u0002/T9g\u001f'N\u001fz\u0002(G?a"));
                }
            }
            catch (Exception ex) {
                System.out.println(b("[\t2Q?t\u001c#\u0002*|\u0003*C(|\u0003(\u0002") + ex.getMessage());
            }
            try {
                if (System.getProperty(b("\u007f\r0Crc\t(F3g")).startsWith(b("X\u0005%P3f\u0003 V"))) {
                    PolicyEngine.assertPermission(PermissionID.SYSTEM);
                }
            }
            catch (Exception ex2) {
                System.out.println(b("X\u0005%P3f\u0003 V|c\u0005)N=a\u0005)L|") + ex2.getMessage());
            }
        }
        try {
            this.a = new Socket(s, Integer.parseInt(s2));
        }
        catch (SecurityException ex3) {
            if (irc.h) {
                final h h2 = new h(new Frame(), bm.dn);
                h2.move(300, 200);
                h2.setSize(6 * bm.dS.length(), 120);
                h2.a(bm.dS);
                p.d = false;
                try {
                    Thread.sleep(3000L);
                    return;
                }
                catch (InterruptedException ex6) {
                    return;
                }
            }
            l.a(bm.bt + b("/L") + ex3, bn.e, false);
            return;
        }
        catch (Exception ex4) {
            l.a(bm.bt + b("/L") + ex4, bn.e, false);
            p.d = false;
            return;
        }
        Label_0484: {
            try {
                if (irc.bm == null) {
                    this.b = new BufferedWriter(new OutputStreamWriter(this.a.getOutputStream()));
                    if (!bm.dX) {
                        break Label_0484;
                    }
                }
                this.b = new BufferedWriter(new OutputStreamWriter(this.a.getOutputStream(), irc.bm));
            }
            catch (Exception ex5) {
                System.out.println(b("Z\u00192R)a?2P9t\u0001fD=|\u0000#Ff5") + ex5);
            }
        }
        p.d = true;
        (this.o = new bv(d, m, this, f, irc, g, e)).start();
        (this.p = new bw(this.a, this, d, this.o, m, this, e, f, g, k, i, j, l, checkboxMenuItem, s3, s4, checkboxMenuItem2, b, checkboxMenuItem3, n)).start();
        this.a(d);
    }
    
    void a(final br e) {
        this.e = e;
    }
    
    br b() {
        return this.e;
    }
    
    void c() {
        if (this.e != null) {
            this.e.b();
        }
        if (this.g != null) {
            this.g.c();
        }
        if (this.k != null) {
            this.k.a();
        }
        if (this.h != null) {
            this.h.dispose();
        }
        if (this.j != null) {
            this.j.c();
        }
        if (this.i != null) {
            this.i.dispose();
        }
        if (this.p != null) {
            this.p.z.dispose();
            this.p.stop();
        }
        if (this.o != null) {
            this.o.stop();
        }
    }
    
    void a(final bu bu) {
        if (irc.l != null) {
            this.a(b("E-\u0015q|") + irc.l + "\n");
        }
        this.a(b("@?\u0003p|") + bu.d() + b("5\rfC|/") + bu.e() + b("\u001f\"\u000fa\u00175") + bu.a() + "\n");
    }
    
    void a(final String s) {
        if (s == null) {
            return;
        }
        try {
            this.b.write(s);
            this.b.flush();
        }
        catch (Exception ex) {
            System.out.println(ex);
            final String string = irc.R + bm.bU;
            this.l.a(string, bn.e, false);
            if (this.g != null) {
                this.g.a(string, false);
            }
            if (this.e != null) {
                this.e.a(string, false);
            }
        }
        if (s.length() > 3 && s.substring(0, 4).equals(b("D9\u000fv"))) {
            p.d = false;
            if (irc.d) {
                this.c();
                return;
            }
            this.a();
        }
    }
    
    private static String b(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = '\u0015';
                    break;
                }
                case 1: {
                    c2 = 'l';
                    break;
                }
                case 2: {
                    c2 = 'F';
                    break;
                }
                case 3: {
                    c2 = '\"';
                    break;
                }
                default: {
                    c2 = '\\';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
