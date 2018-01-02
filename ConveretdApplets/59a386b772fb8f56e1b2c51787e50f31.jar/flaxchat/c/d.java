// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.c;

import flaxchat.i.b;
import java.awt.event.MouseEvent;
import java.awt.Component;
import flaxchat.d.c;
import flaxchat.m;
import flaxchat.b.h;
import flaxchat.d.g;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class d extends a implements MouseListener, ActionListener
{
    private g j;
    private h k;
    private static String[] z;
    
    public d(final m m) {
        super(m);
        (this.j = new g(m.g(), new c(0, this))).setName(d.z[6]);
        this.k = new h(m, null, null, d.z[7]);
        this.add(this.j, d.z[5]);
        this.j.d().addMouseListener(this);
        ((c)this.j.d()).a(this);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (flaxchat.e.g.a(mouseEvent)) {
            this.a(this.j.d(), d.z[3], null, null, mouseEvent.getPoint());
            return;
        }
        if (mouseEvent.getClickCount() < 2) {
            return;
        }
        this.a(this.j.d(), d.z[4], null, null);
    }
    
    public void c(final String s) {
        this.a(flaxchat.i.b.b(d.z[9], d.z[8]), s);
    }
    
    public void a(final String s, final String s2) {
        if (s2 == null) {
            return;
        }
        super.h.t().e(this.j());
        this.j.a(String.valueOf(s) + s2);
    }
    
    public void b(final String s) {
        this.a(flaxchat.i.b.b(d.z[2], d.z[1]), "<" + this.a().g() + d.z[0] + s);
    }
    
    public void f() {
        super.h.l().d(null);
    }
    
    public String j() {
        return d.z[6];
    }
    
    public h k() {
        return this.k;
    }
    
    public String b(final String s, final String s2) {
        return d.z[10] + s;
    }
    
    static {
        d.z = new String[] { z(z("-`")), z(z("\u0010Ym")), z(z("`\f0}u`\u000e\u001dfT|\u001b")), z(z("`\u001d?}M`D.fHf\u0019")), z(z("`\u001d?}M`D)`Vw\u0006)")), z(z("P\f0}]a")), z(z("@\u001d?}M`")), z(z("`\u001d?}M`D*fW\u007f\u000b?{")), z(z("\u0010Xl")), z(z("a\f=`]e\f:DKt*1eWa")), z(z("G\u00017z\u0018e\u0000;~\u0018w\u0006;z\u0018}\u0006*)Kf\u0019.fJgI*aQ`I9hUvId)")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '8';
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
                    c2 = '\u0013';
                    break;
                }
                case 1: {
                    c2 = 'i';
                    break;
                }
                case 2: {
                    c2 = '^';
                    break;
                }
                case 3: {
                    c2 = '\t';
                    break;
                }
                default: {
                    c2 = '8';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
