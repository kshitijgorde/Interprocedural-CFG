// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.b;

import flaxchat.e.p;
import java.awt.Component;
import java.util.StringTokenizer;
import java.util.Hashtable;
import java.awt.Image;
import flaxchat.e.c;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.awt.event.ComponentListener;
import flaxchat.d.j;

public class d extends j implements ComponentListener
{
    private int h;
    private Vector i;
    private b j;
    private h k;
    private static String[] z;
    
    public d() {
        this.addMouseListener(this.j = new b(this));
        this.addMouseMotionListener(this.j);
        this.a(new Dimension(0, 40));
    }
    
    public void a(final h k) {
        this.k = k;
        this.i = k.a;
        this.doLayout();
        this.repaint();
    }
    
    public void doLayout() {
        final int f = flaxchat.b.h.f;
        super.doLayout();
        int h = 4;
        final Rectangle rectangle = new Rectangle();
        if (this.i == null) {
            return;
        }
        int n = 0;
        while (true) {
            Label_0124: {
                if (f == 0) {
                    break Label_0124;
                }
                final Object element = this.i.elementAt(n);
                Label_0121: {
                    if (element instanceof a) {
                        ((a)element).setLocation(h, 2);
                        h += 4;
                        if (f == 0) {
                            break Label_0121;
                        }
                    }
                    final g g = (g)element;
                    final int n2 = g.e.getWidth(this) + 10;
                    g.a(new Rectangle(h, 2, n2, 24));
                    h += n2 + 2;
                }
                ++n;
            }
            if (n >= this.i.size()) {
                this.h = h;
                return;
            }
            continue;
        }
    }
    
    public void a(final Graphics graphics) {
        final int f = flaxchat.b.h.f;
        super.a(graphics);
        final int height = this.getSize().height;
        int n = 4;
        int n2 = 0;
        while (true) {
            Label_0213: {
                if (f == 0) {
                    break Label_0213;
                }
                final Object element = this.i.elementAt(n2);
                Label_0210: {
                    if (element instanceof a) {
                        final Color color = graphics.getColor();
                        graphics.setColor(Color.gray);
                        graphics.drawLine(n, 3, n, height - 4);
                        graphics.setColor(Color.gray.brighter().brighter());
                        graphics.drawLine(n + 1, 4, n + 1, height - 3);
                        n += 4;
                        graphics.setColor(color);
                        if (f == 0) {
                            break Label_0210;
                        }
                    }
                    final g g = (g)element;
                    final Image e = g.e;
                    final int width = g.g.getSize().width;
                    final int width2 = g.e.getWidth(this);
                    final int height2 = g.e.getHeight(this);
                    final int n3 = n + (width - width2) / 2;
                    final int n4 = (height - height2) / 2;
                    this.a(graphics, g);
                    graphics.drawImage(e, n3, n4, this);
                    n += width + 2;
                }
                ++n2;
            }
            if (n2 >= this.i.size()) {
                if (flaxchat.b.b.a(this.j) != null) {
                    graphics.setFont(flaxchat.i.b.d(d.z[6]));
                    flaxchat.e.c.a(graphics, flaxchat.b.b.a(this.j).c, this.h + 4, 0, height);
                }
                return;
            }
            continue;
        }
    }
    
    private void a(final Graphics graphics, final g g) {
        final Rectangle g2 = g.g;
        final boolean b = g.b;
        final boolean a = g.a;
        if (b) {
            this.a(graphics, g2, flaxchat.i.d.e(), a ? Color.gray : this.getBackground());
            return;
        }
        if (a) {
            this.a(graphics, g2, flaxchat.i.d.e(), Color.gray);
            return;
        }
        this.a(graphics, g2, flaxchat.i.d.d(), this.getBackground());
    }
    
    private void a(final Graphics graphics, final Rectangle rectangle, final Image image, final Color color) {
        final Dimension size = rectangle.getSize();
        final int x = rectangle.getLocation().x;
        final int y = rectangle.getLocation().y;
        if (image == null) {
            final Color color2 = graphics.getColor();
            graphics.setColor(color);
            graphics.fillRoundRect(1, 1, size.width, size.height, 10, 10);
            graphics.setColor(color2);
            return;
        }
        final int width = image.getWidth(this);
        final int n = image.getWidth(this) / 3;
        final int height = image.getHeight(this);
        final int width2 = size.width;
        final int height2 = size.height;
        try {
            graphics.drawImage(image, x, y, x + n, y + height2, 0, 0, n, height, this);
            graphics.drawImage(image, x + width2 - n, y, x + width2, y + height2, width - n, 0, width, height, this);
            graphics.drawImage(image, x + n, y, x + width2 - n, y + height2, n, 0, width - n, height, this);
        }
        catch (RuntimeException ex) {
            ex.printStackTrace();
        }
    }
    
    public void a(final String s, final Hashtable hashtable) {
        final int f = flaxchat.b.h.f;
        if (s.equals(d.z[5])) {
            this.k.c.a(null, null, null, d.z[3]);
            return;
        }
        String a = hashtable.get(d.z[4]);
        final String s2 = hashtable.get(d.z[2]);
        if (s2 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s2, ",");
            final int countTokens = stringTokenizer.countTokens();
            final String[] array = new String[countTokens];
            final int[] array2 = new int[countTokens];
            int n = 0;
            while (true) {
                Label_0129: {
                    if (f == 0) {
                        break Label_0129;
                    }
                    array[n] = stringTokenizer.nextToken();
                    array2[n] = n;
                    ++n;
                }
                if (n < array.length) {
                    continue;
                }
                break;
            }
            final String[] a2 = flaxchat.e.j.a(this.k.c, hashtable.get(d.z[0]), d.z[1], array, array2);
            if (a2 == null) {
                return;
            }
            a = p.a(a, a2);
        }
        this.k.c.a(this, this.k.d, this.k.e, a);
    }
    
    static Vector a(final d d) {
        return d.i;
    }
    
    static {
        d.z = new String[] { z(z("np]")), z(z("f]qCrHPd")), z(z("i\u007f@")), z(z("AS\u007fNE")), z(z("c|T")), z(z("hP{PXNUq")), z(z("T^\u007fWSACVT_T")) };
    }
    
    private static char[] z(final String s) {
        final char[] charArray = s.toCharArray();
        if (charArray.length < 2) {
            final int n = 0;
            charArray[n] ^= '1';
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
                    c2 = ' ';
                    break;
                }
                case 1: {
                    c2 = '1';
                    break;
                }
                case 2: {
                    c2 = '\u0010';
                    break;
                }
                case 3: {
                    c2 = ';';
                    break;
                }
                default: {
                    c2 = '1';
                    break;
                }
            }
            array[n2] = (char)(c ^ c2);
        }
        return new String(array).intern();
    }
}
