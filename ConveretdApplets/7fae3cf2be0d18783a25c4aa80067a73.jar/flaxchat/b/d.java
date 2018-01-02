// 
// Decompiled by Procyon v0.5.30
// 

package flaxchat.b;

import flaxchat.a.q;
import java.awt.Component;
import flaxchat.a.k;
import java.util.StringTokenizer;
import java.awt.Point;
import java.util.Hashtable;
import java.awt.Image;
import flaxchat.a.c;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.util.Vector;
import java.awt.event.ComponentListener;
import flaxchat.i.j;

public class d extends j implements ComponentListener
{
    private int k;
    private Vector l;
    private b m;
    private h n;
    private static String[] z;
    
    public d() {
        this.addMouseListener(this.m = new b(this));
        this.addMouseMotionListener(this.m);
        this.a(new Dimension(0, 30));
    }
    
    public void a(final h n) {
        this.n = n;
        this.l = n.a;
        this.doLayout();
        this.repaint();
    }
    
    public void doLayout() {
        final boolean f = flaxchat.b.h.f;
        super.doLayout();
        int k = 4;
        final Rectangle rectangle = new Rectangle();
        if (this.l == null) {
            return;
        }
        final int height = this.getSize().height;
        final int a = flaxchat.d.b.a(d.z[1], 3);
        int n = 0;
        while (true) {
            Label_0150: {
                if (!f) {
                    break Label_0150;
                }
                final Object element = this.l.elementAt(n);
                Label_0147: {
                    if (element instanceof a) {
                        ((a)element).setLocation(k, 2);
                        k += 4;
                        if (!f) {
                            break Label_0147;
                        }
                    }
                    final g g = (g)element;
                    final int n2 = g.e.getWidth(this) + 10;
                    g.a(new Rectangle(k, a, n2, height - a * 2));
                    k += n2 + 2;
                }
                ++n;
            }
            if (n >= this.l.size()) {
                this.k = k;
                return;
            }
            continue;
        }
    }
    
    public void a(final Graphics graphics) {
        final boolean f = flaxchat.b.h.f;
        super.a(graphics);
        final int height = this.getSize().height;
        int n = 4;
        int n2 = 0;
        while (true) {
            Label_0213: {
                if (!f) {
                    break Label_0213;
                }
                final Object element = this.l.elementAt(n2);
                Label_0210: {
                    if (element instanceof a) {
                        final Color color = graphics.getColor();
                        graphics.setColor(Color.gray);
                        graphics.drawLine(n, 3, n, height - 4);
                        graphics.setColor(Color.gray.brighter().brighter());
                        graphics.drawLine(n + 1, 4, n + 1, height - 3);
                        n += 4;
                        graphics.setColor(color);
                        if (!f) {
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
            if (n2 >= this.l.size()) {
                if (flaxchat.b.b.a(this.m) != null) {
                    graphics.setFont(flaxchat.d.b.d(d.z[0]));
                    flaxchat.a.c.a(graphics, flaxchat.b.b.a(this.m).c, this.k + 4, 0, height);
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
            this.a(graphics, g2, flaxchat.d.d.e(), a ? Color.gray : this.getBackground());
            return;
        }
        if (a) {
            this.a(graphics, g2, flaxchat.d.d.e(), Color.gray);
            return;
        }
        this.a(graphics, g2, flaxchat.d.d.d(), this.getBackground());
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
    
    public void a(final String s, final Hashtable hashtable, final Rectangle rectangle) {
        final boolean f = flaxchat.b.h.f;
        final Hashtable<String, Point> hashtable2 = new Hashtable<String, Point>();
        hashtable2.put("p", rectangle.getLocation());
        if (s.equals(d.z[7])) {
            this.n.c.a(null, null, null, d.z[2], hashtable2);
            return;
        }
        String a = hashtable.get(d.z[6]);
        final String s2 = hashtable.get(d.z[4]);
        if (s2 != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(s2, ",");
            final int countTokens = stringTokenizer.countTokens();
            final String[] array = new String[countTokens];
            final int[] array2 = new int[countTokens];
            int n = 0;
            while (true) {
                Label_0155: {
                    if (!f) {
                        break Label_0155;
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
            final String[] a2 = flaxchat.a.k.a(this.n.c, hashtable.get(d.z[5]), d.z[3], array, array2);
            if (a2 == null) {
                return;
            }
            a = q.a(a, a2);
        }
        this.n.c.a(this, this.n.d, this.n.e, a, hashtable2);
    }
    
    static Vector a(final d d) {
        return d.l;
    }
    
    static {
        d.z = new String[] { z(z("z6s;qo+Z8}z")), z(z("l,h#|`wu9`k-E")), z(z("o;s\"g")), z(z("H5}/Pf8h")), z(z("G\u0017L")), z(z("@\u0018Q")), z(z("M\u0014X")), z(z("F8w<z`=}")) };
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
                    c2 = '\u000e';
                    break;
                }
                case 1: {
                    c2 = 'Y';
                    break;
                }
                case 2: {
                    c2 = '\u001c';
                    break;
                }
                case 3: {
                    c2 = 'W';
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
