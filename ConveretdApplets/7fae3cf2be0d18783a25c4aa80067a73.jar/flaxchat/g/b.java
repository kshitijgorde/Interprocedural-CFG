// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.g;

import java.awt.Dimension;
import java.awt.Color;
import flaxchat.a.h;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Panel;

final class b extends Panel
{
    private final d a;
    private static String z;
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        super.paint(graphics);
        graphics.drawImage(this.a(), 60, 0, this.getSize().width, this.getSize().height, this);
    }
    
    private Image a() {
        final Image image = this.createImage(200, 40);
        final Graphics graphics = image.getGraphics();
        graphics.setFont(new Font(b.z, 1, 18));
        final String h = this.a.a.l().h();
        final int n = h.length() / 2;
        final String substring = h.substring(0, n);
        final String substring2 = h.substring(n);
        final int a = flaxchat.a.h.a(substring, graphics);
        final int a2 = flaxchat.a.h.a(h, graphics);
        final int n2 = 0;
        final int n3 = 30;
        graphics.setColor(Color.red);
        graphics.drawString(substring, n2, n3);
        graphics.setColor(Color.white);
        graphics.drawString(substring2, n2 + a, n3);
        final Font font = new Font(b.z, 2, 10);
        graphics.setColor(Color.white);
        graphics.setFont(font);
        graphics.drawString(this.a.a.l().g(), n2 + a2, n3 + 8);
        graphics.dispose();
        return image;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(0, 40);
    }
    
    b(final d a) {
        this.a = a;
    }
    
    static {
        b.z = z(z("vJuh:U"));
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= 'U';
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
                    c2 = '2';
                    break;
                }
                case 1: {
                    c2 = '#';
                    break;
                }
                case 2: {
                    c2 = '\u0014';
                    break;
                }
                case 3: {
                    c2 = '\u0004';
                    break;
                }
                default: {
                    c2 = 'U';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
