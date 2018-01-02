// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.d;

import flaxchat.i.b;
import java.awt.Dimension;
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
        flaxchat.e.g.a(graphics, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(flaxchat.i.b.a(g.z[2], 110), 0);
    }
    
    public void a() {
        this.b.b(this.c.g());
    }
    
    public void b() {
        if (this.b != null) {
            this.b.e();
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
        return this.b.d();
    }
    
    static {
        g.z = new String[] { z(z("n&\u0018@x_")), z(z("h\"\u0005@")), z(z("X0\u0013FQD0\u0002gtW&")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '\u001d';
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
                    c2 = '-';
                    break;
                }
                case 1: {
                    c2 = 'C';
                    break;
                }
                case 2: {
                    c2 = 'v';
                    break;
                }
                case 3: {
                    c2 = '4';
                    break;
                }
                default: {
                    c2 = '\u001d';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
