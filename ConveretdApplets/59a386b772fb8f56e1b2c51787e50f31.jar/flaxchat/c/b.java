// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.c;

import flaxchat.d.k;
import flaxchat.h.c;
import java.awt.event.MouseEvent;
import java.awt.Component;
import flaxchat.m;
import flaxchat.b.h;
import flaxchat.d.g;
import java.awt.event.MouseListener;

public class b extends a implements MouseListener
{
    private final g j;
    private final String k;
    private flaxchat.c.g l;
    private h m;
    private static String[] z;
    
    public b(final m m, final String k) {
        super(m);
        this.l = new flaxchat.c.g();
        this.k = k;
        (this.j = new g(m.g(), new flaxchat.d.b(0))).setName(b.z[0]);
        this.j.d().addMouseListener(this);
        ((flaxchat.d.b)this.j.d()).b(false);
        this.m = new h(m, null, null, b.z[1]);
        this.add(this.j, b.z[2]);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (flaxchat.e.g.b(mouseEvent)) {
            if (mouseEvent.getClickCount() < 2) {
                return;
            }
            final String a = this.a();
            if (a == null) {
                return;
            }
            this.a(this.j, b.z[5], a, null);
        }
        else {
            final String a2 = this.a();
            if (a2 == null) {
                return;
            }
            this.a(this.j, b.z[4], a2, null, mouseEvent.getPoint());
        }
    }
    
    private String a() {
        try {
            return this.j.d().h().a().a();
        }
        catch (RuntimeException ex) {
            return null;
        }
    }
    
    public void c(final String s) {
        super.h.k().c(s);
    }
    
    public void a(final String s, final String s2) {
        super.h.k().a(s, s2);
    }
    
    public void b(final String s) {
        super.h.k().b(s);
    }
    
    public void g() {
        this.j.b();
    }
    
    public void a(final String s, final int n, final String s2) {
        this.a(new c(s, s2, n));
    }
    
    public void a(final c c) {
        ((flaxchat.d.b)this.j.d()).a(new k(c.a(), c), this.l);
        if (this.j.d().h() == null) {
            this.j.d().c(c.a());
            this.j.c().b(0);
        }
    }
    
    public String j() {
        return this.k;
    }
    
    public h k() {
        return this.m;
    }
    
    public String b(final String s, final String s2) {
        return b.z[3] + s;
    }
    
    static {
        b.z = new String[] { z(z("\nwQ\u0018\u001d#zSV;'")), z(z("8o\\L+86IW1'y\\J")), z(z("\b~SL;9")), z(z("\u001fsTK~=rXO~/tXK~%tI\u0018->kMW,?;IP78;ZY3.;\u0007\u0018")), z(z("*wQ\u0015=#zSV;'6MW.>k")), z(z("*wQ\u0015=#zSV;'6JQ0/tJ")) };
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
                    c2 = 'K';
                    break;
                }
                case 1: {
                    c2 = '\u001b';
                    break;
                }
                case 2: {
                    c2 = '=';
                    break;
                }
                case 3: {
                    c2 = '8';
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
