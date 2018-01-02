// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.c;

import flaxchat.d.b;
import java.awt.event.MouseEvent;
import java.awt.Component;
import flaxchat.i.c;
import flaxchat.n;
import flaxchat.b.h;
import flaxchat.i.g;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class d extends a implements MouseListener, ActionListener
{
    private g m;
    private h n;
    private static String[] z;
    
    public d(final n n) {
        super(n);
        (this.m = new g(n.g(), new c(0, this))).setName(d.z[2]);
        this.n = new h(n, null, null, d.z[4]);
        this.add(this.m, d.z[3]);
        this.m.d().addMouseListener(this);
        ((c)this.m.d()).a(this);
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (flaxchat.a.h.a(mouseEvent)) {
            this.a(this.m.d(), d.z[10], null, null, mouseEvent.getPoint());
            return;
        }
        if (mouseEvent.getClickCount() < 2) {
            return;
        }
        this.a(this.m.d(), d.z[9], null, null);
    }
    
    public void c(final String s) {
        this.a(flaxchat.d.b.b(d.z[0], d.z[1]), s);
    }
    
    public void a(final String s, final String s2) {
        if (s2 == null) {
            return;
        }
        super.k.t().e(this.j());
        this.m.a(String.valueOf(s) + s2);
    }
    
    public void b(final String s) {
        this.a(flaxchat.d.b.b(d.z[6], d.z[7]), "<" + this.a().g() + d.z[8] + s);
    }
    
    public void f() {
        super.k.l().d(null);
    }
    
    public String j() {
        return d.z[2];
    }
    
    public h k() {
        return this.n;
    }
    
    public String b(final String s, final String s2) {
        return d.z[5] + s;
    }
    
    public void i(final String s) {
    }
    
    public f n() {
        return null;
    }
    
    public void m() {
    }
    
    static {
        d.z = new String[] { z(z("\u000e&\u0004,W\n&\u0003\bA\u001b\u0000\b)]\u000e")), z(z("\u007frU")), z(z("/7\u00061G\u000f")), z(z("?&\t1W\u000e")), z(z("\u000f7\u00061G\u000fn\u0013*]\u0010!\u00067")), z(z("(+\u000e6\u0012\n*\u00022\u0012\u0018,\u00026\u0012\u0012,\u0013eA\t3\u0017*@\bc\u0013-[\u000fc\u0000$_\u0019c]e")), z(z("\u000f&\t1\u007f\u000f$$*^\u00131")), z(z("\u007fsT")), z(z("BJ")), z(z("\u000f7\u00061G\u000fn\u0010,\\\u0018,\u0010")), z(z("\u000f7\u00061G\u000fn\u0017*B\t3")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '2';
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
                    c2 = '|';
                    break;
                }
                case 1: {
                    c2 = 'C';
                    break;
                }
                case 2: {
                    c2 = 'g';
                    break;
                }
                case 3: {
                    c2 = 'E';
                    break;
                }
                default: {
                    c2 = '2';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
