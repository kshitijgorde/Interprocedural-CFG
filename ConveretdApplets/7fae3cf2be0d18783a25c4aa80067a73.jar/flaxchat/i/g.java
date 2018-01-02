// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.i;

import flaxchat.d.b;
import java.awt.Dimension;
import flaxchat.a.h;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Panel;

public class g extends Panel
{
    private int a;
    private a b;
    private d c;
    private static String[] z;
    
    public g(final Component c, final a b) {
        this.setBackground(Color.white);
        this.b = b;
        this.b.c = c;
        this.a = this.getFontMetrics(this.b.getFont()).getHeight();
        (this.c = new d(this.a)).a(c);
        this.setLayout(new BorderLayout());
        this.add(this.b, g.z[0]);
        this.add(this.c, g.z[1]);
        this.b.t = this.c;
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        h.a(graphics, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(flaxchat.d.b.a(g.z[2], 110), 0);
    }
    
    public void a() {
        this.b.b(this.c.g());
    }
    
    public void b() {
        if (this.b != null) {
            this.b.d();
        }
    }
    
    public void a(final String s) {
        this.b.a(s);
    }
    
    public d c() {
        return this.c;
    }
    
    public a d() {
        return this.b;
    }
    
    public int e() {
        return this.b.c();
    }
    
    static {
        g.z = new String[] { z(z("MltHg|")), z(z("KhiH")), z(z("{z\u007fNNgznoktl")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u0002';
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
                    c2 = '\u000e';
                    break;
                }
                case 1: {
                    c2 = '\t';
                    break;
                }
                case 2: {
                    c2 = '\u001a';
                    break;
                }
                case 3: {
                    c2 = '<';
                    break;
                }
                default: {
                    c2 = '\u0002';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
