// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.c;

import flaxchat.a.i;
import flaxchat.i.k;
import flaxchat.f.c;
import java.awt.event.MouseEvent;
import java.awt.Component;
import flaxchat.n;
import flaxchat.b.h;
import flaxchat.i.g;
import java.awt.event.MouseListener;

public class b extends a implements MouseListener
{
    private final g m;
    private final String n;
    private flaxchat.c.g o;
    private h p;
    private static String[] z;
    
    public b(final n n, final String n2) {
        super(n);
        this.o = new flaxchat.c.g();
        this.n = n2;
        (this.m = new g(n.g(), new flaxchat.i.b(0))).setName(b.z[1]);
        this.m.d().addMouseListener(this);
        ((flaxchat.i.b)this.m.d()).b(false);
        this.p = new h(n, null, null, b.z[3]);
        this.add(this.m, b.z[2]);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (flaxchat.a.h.b(mouseEvent)) {
            if (mouseEvent.getClickCount() < 2) {
                return;
            }
            final String a = this.a();
            if (a == null) {
                return;
            }
            this.a(this.m, b.z[5], a, null);
        }
        else {
            final String a2 = this.a();
            if (a2 == null) {
                return;
            }
            this.a(this.m, b.z[4], a2, null, mouseEvent.getPoint());
        }
    }
    
    private String a() {
        try {
            return this.m.d().g().a().a();
        }
        catch (RuntimeException ex) {
            return null;
        }
    }
    
    public void c(final String s) {
        super.k.k().c(s);
    }
    
    public void a(final String s, final String s2) {
        super.k.k().a(s, s2);
    }
    
    public void b(final String s) {
        super.k.k().b(s);
    }
    
    public void g() {
        this.m.b();
    }
    
    public void a(final String s, final int n, final String s2) {
        this.a(new c(s, s2, n));
    }
    
    public void a(final c c) {
        ((flaxchat.i.b)this.m.d()).a(new k(c.a(), c), this.o);
        if (this.m.d().g() == null) {
            this.m.d().c(c.a());
            this.m.c().b(0);
        }
    }
    
    public String j() {
        return this.n;
    }
    
    public h k() {
        return this.p;
    }
    
    public String b(final String s, final String s2) {
        return b.z[0] + s;
    }
    
    public void i(final String s) {
    }
    
    public f n() {
        return null;
    }
    
    public void m() {
    }
    
    static {
        b.z = new String[] { z(z("^E\rp3|D\u0001t3nB\u0001p3dB\u0010#`\u007f]\u0014la~\r\u0010kzy\r\u0003b~o\r^#")), z(z("KA\b#PbL\nmvf")), z(z("IH\nwvx")), z(z("yY\u0005wfy\u0000\u0010l|fO\u0005q")), z(z("kA\b.pbL\nmvf\u0000\u0014lc\u007f]")), z(z("kA\b.pbL\nmvf\u0000\u0013j}nB\u0013")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u0013';
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
                    c2 = '\n';
                    break;
                }
                case 1: {
                    c2 = '-';
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
                    c2 = '\u0013';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
