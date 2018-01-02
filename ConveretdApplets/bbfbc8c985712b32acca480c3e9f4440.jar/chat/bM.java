// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Event;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Image;
import java.awt.Component;
import java.awt.Insets;
import java.util.Hashtable;
import java.util.Vector;
import java.awt.CardLayout;
import java.awt.Panel;

public final class bM extends Panel
{
    private int a;
    private CardLayout a;
    private Vector a;
    private Vector b;
    private Hashtable a;
    private boolean a;
    private int b;
    private Insets a;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private cz a;
    private cs a;
    
    public final void a(final String s, final Component component) {
        this.a(s, component, null, null);
    }
    
    public final void a(final String s, final Component component, final Image image, final Image image2) {
        this.a.addElement(s);
        this.b.addElement(component);
        if (image != null && image2 != null && this.a) {
            this.a.put(s, new Image[] { image, image2 });
        }
        this.add(s, component);
    }
    
    public final Component getComponentAt(final int n, final int n2) {
        return this.b.elementAt(this.b);
    }
    
    public final void a(final int b) {
        final int b2 = this.b;
        final int n = (this.a.size() + this.c - 1) / this.c;
        this.b = b;
        final Graphics graphics;
        if ((graphics = this.getGraphics()) != null) {
            if (this.b / n == b2 / n) {
                if (this.a) {
                    this.a(graphics, b2, this.b);
                }
                else {
                    this.b(graphics, b2, this.b);
                }
            }
            else {
                this.repaint();
            }
            graphics.dispose();
        }
        this.a.show(this, this.a.elementAt(b));
    }
    
    public final Dimension minimumSize() {
        return this.preferredSize();
    }
    
    public final Dimension preferredSize() {
        int n = 0;
        int n2 = 0;
        final int n3 = (this.a.size() + this.c - 1) / this.c;
        final FontMetrics fontMetrics = this.getFontMetrics(bk.b);
        for (int i = 0; i < this.a.size(); ++i) {
            if (i % n3 == 0) {
                if (n > n2) {
                    n2 = n;
                }
                n = 0;
            }
            int n4 = fontMetrics.stringWidth(this.a.elementAt(i));
            if (this.a) {
                final Image[] array;
                n4 = (array = (Image[])this.a.get(this.a.elementAt(i)))[0].getWidth(this);
                if (array[1].getWidth(this) > n4) {
                    n4 = array[1].getWidth(this);
                }
            }
            n += n4 + 3 + (this.a << 1);
        }
        if (n > n2) {
            n2 = n;
        }
        final Dimension preferredSize = super.preferredSize();
        return new Dimension(Math.max(preferredSize.width, n2 + 10 + this.a.c / 2), Math.max(preferredSize.height, 42));
    }
    
    public final boolean mouseDown(final Event event, final int n, final int n2) {
        if (n2 < this.d) {
            int n3 = 3;
            final int n4 = (this.a.size() + this.c - 1) / this.c;
            final int n5 = this.b / n4;
            final FontMetrics fontMetrics = this.getFontMetrics(bk.b);
            int i = 0;
        Label_0283:
            while (true) {
                while (i < this.a.size()) {
                    final int n6 = (this.c - 1 - (n5 + i / n4) % this.c) * 27;
                    if (i % n4 == 0) {
                        n3 = 3;
                    }
                    this.a.elementAt(i);
                    int n7 = fontMetrics.stringWidth(this.a.elementAt(i));
                    if (this.a) {
                        final Image[] array;
                        n7 = (array = (Image[])this.a.get(this.a.elementAt(i)))[0].getWidth(this);
                        if (array[1].getWidth(this) > n7) {
                            n7 = array[1].getWidth(this);
                        }
                        if (new Rectangle(n3, n6, n7, 22).inside(n, n2)) {
                            final int n9;
                            final int n8 = n9 = i;
                            break Label_0283;
                        }
                    }
                    final int n10 = n3 + (this.a << 1) + n7;
                    if (this.a || !a(n3, n6, n10, n6 + 22 - 1).inside(n, n2)) {
                        n3 = n10 + 3;
                        ++i;
                        continue;
                    }
                    int n9;
                    final int n8 = n9 = i;
                    final int n11 = n9;
                    if (n8 != -1 && n11 != this.b) {
                        this.a(n11);
                        return true;
                    }
                    return true;
                }
                int n9;
                final int n8 = n9 = -1;
                continue Label_0283;
            }
        }
        return true;
    }
    
    private static Polygon a(final int n, final int n2, final int n3, final int n4) {
        final int[] array = { n - 3 + 1, n, n + 4, n + 5, n + 6, n + 7, n + 8, n + 9, n3 - 9, n3 - 8, n3 - 7, n3 - 6, n3 - 5, n3 - 4, n3, n3 + 1 };
        return new Polygon(array, new int[] { n4, n4 - 1, n2 + 5, n2 + 4, n2 + 2, n2 + 1, n2 + 1, n2, n2, n2 + 1, n2 + 1, n2 + 2, n2 + 4, n2 + 5, n4 - 1, n4 }, array.length);
    }
    
    private void a(final Graphics graphics, final int n, final int n2) {
        if (graphics != null) {
            int n3 = 3;
            final int size;
            final int n4 = ((size = this.a.size()) + this.c - 1) / this.c;
            final int n5 = this.b / n4;
            if (this.a.c == 0) {
                graphics.setColor(Color.white);
                graphics.drawLine(1, 23, this.size().width - 3, 23);
                graphics.setColor(Color.black);
                graphics.drawLine(1, 22, this.size().width - 2, 22);
            }
            for (int n6 = 0; (n == -1 || n6 <= n || n6 <= n2) && n6 < size; ++n6) {
                if (n6 % n4 == 0) {
                    n3 = 0;
                }
                final Image image = ((Image[])this.a.get(this.a.elementAt(n6)))[n6 != n2 && (n != -1 || n6 != this.b)];
                final int n7 = (this.c - 1 - (n5 + n6 / n4) % this.c) * 27;
                final int n8 = (this.a << 1) + image.getWidth(this);
                if (n == -1 || n6 == n || n6 == n2) {
                    graphics.drawImage(image, n3, n7, null);
                }
                n3 += n8 + 3;
            }
        }
    }
    
    private final void b(final Graphics graphics, final int n, final int n2) {
        if (graphics != null) {
            graphics.setFont(bk.b);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            int n3 = 3;
            final int size = this.a.size();
            final int height = fontMetrics.getHeight();
            final int n4 = (size + this.c - 1) / this.c;
            final int n5 = this.b / n4;
            Color color;
            if ((color = this.getBackground()) == null) {
                color = o.b;
            }
            final Color darker = color.darker();
            final Color foreground = this.getForeground();
            final Color darker2 = darker.darker();
            for (int n6 = 0; (n == -1 || n6 <= n || n6 <= n2) && n6 < size; ++n6) {
                final String s = this.a.elementAt(n6);
                final int stringWidth = fontMetrics.stringWidth(s);
                if (n6 % n4 == 0) {
                    n3 = 3;
                }
                final int n7 = (this.c - 1 - (n5 + n6 / n4) % this.c) * 27;
                final int n8 = stringWidth + (this.a << 1);
                final int n9 = n3 + n8;
                if (n == -1 || n6 == n || n6 == n2) {
                    final Polygon a = a(n3, n7, n9, n7 + 22 - 1);
                    graphics.setColor((n6 != this.b) ? darker : color);
                    graphics.fillPolygon(a);
                    final int[] array = { -2, 0, 1, 5, 6, 7, 8, 9, n8 - 10 };
                    final int[] array2 = { 20, 20, 18, 5, 4, 2, 2, 1, 1 };
                    graphics.setColor(Color.white);
                    for (int i = 0; i < array.length - 1; ++i) {
                        graphics.drawLine(n3 + array[i], n7 + array2[i], n3 + array[i + 1], n7 + array2[i + 1]);
                    }
                    graphics.setColor(Color.black);
                    for (int j = 0; j < a.xpoints.length - 1; ++j) {
                        graphics.drawLine(a.xpoints[j], a.ypoints[j], a.xpoints[j + 1], a.ypoints[j + 1]);
                    }
                    graphics.setColor((n6 != this.b) ? darker2 : darker);
                    for (int k = 1; k < array.length - 2; ++k) {
                        graphics.drawLine(n9 - array[k], n7 + array2[k], n9 - array[k + 1], n7 + array2[k + 1]);
                    }
                    if (n6 == this.b) {
                        graphics.setColor(color);
                        graphics.drawLine(n3, n7 + 22, n9, n7 + 22);
                        graphics.drawLine(n3 + 1, n7 + 22 - 1, n9 - 1, n7 + 22 - 1);
                    }
                    else {
                        graphics.setColor((this.a.c == 0) ? Color.white : darker);
                        graphics.drawLine(n3, n7 + 22, n9, n7 + 22);
                        graphics.setColor((this.a.c == 0) ? Color.black : darker2);
                        graphics.drawLine(n3, n7 + 22 - 1, n9, n7 + 22 - 1);
                    }
                    graphics.setColor(foreground);
                    graphics.drawString(s, n3 + this.a, n7 + (height + 22) / 2 - 4);
                }
                n3 += n8 + 3;
                if ((n == -1 && (n6 + 1) % n4 == 0) || n6 == size - 1) {
                    int n10 = this.size().width - 1;
                    if (this.a.c != 0) {
                        n10 -= this.a.c / 2 - 1;
                    }
                    graphics.setColor((this.a.c == 0) ? Color.white : darker);
                    graphics.drawLine(n10 - 2, n7 + 22, n3 - 3 + 1, n7 + 22);
                    graphics.setColor((this.a.c == 0) ? Color.black : darker2);
                    graphics.drawLine(n10 - 1, n7 + 22 - 1, n3 - 3 + 1, n7 + 22 - 1);
                }
            }
        }
    }
    
    public final void paint(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Dimension size;
        final int n = (size = this.size()).height - 1;
        final int n2 = size.width - 1;
        final int n3 = this.c * 27 - 5;
        final Color background = this.getBackground();
        final Color background2 = this.getParent().getBackground();
        if (this.a.c != 0) {
            graphics.setColor(background2);
            graphics.fillRect(0, 0, size.width, size.height);
            final int c = this.a.c;
            graphics.setColor(background);
            graphics.fillRoundRect(0, 22, n2, n - 22, c, c);
            graphics.drawArc(n2 - c, n - c - 1, c, c, 0, -90);
            graphics.fillRect(0, 22, c << 1, c << 1);
            graphics.setColor(background.darker());
            if (this.a) {
                graphics.drawLine(0, 22, n2 - c / 2, 22);
            }
            graphics.drawLine(n2, 22 + c / 2 - 2, n2, n - c / 2);
            graphics.drawArc(n2 - c, 22, c, c - 1, 0, 90);
            graphics.drawArc(n2 - c, n - c, c, c, 0, -90);
            graphics.drawLine(c / 2 - 1, n, n2 - c / 2, n);
            graphics.drawLine(0, 22, 0, n - c / 2);
            graphics.drawArc(0, n - c, c, c, 180, 90);
        }
        else {
            graphics.setColor(background2);
            graphics.fillRect(0, 0, size.width + 1, n3);
            graphics.setColor(Color.black);
            graphics.drawLine(0, n3, 0, n - 2);
            graphics.drawLine(0, n - 2, 2, n);
            graphics.drawLine(2, n, n2 - 2, n);
            graphics.drawLine(n2 - 2, n, n2, n - 2);
            graphics.drawLine(n2, n - 2, n2, n3);
            graphics.setColor(background.brighter());
            graphics.drawLine(1, n3, 1, n - 2);
            graphics.setColor(background.darker());
            graphics.drawLine(2, n - 1, n2 - 2, n - 1);
            graphics.drawLine(n2 - 1, n - 2, n2 - 1, n3);
        }
        if (this.a) {
            this.a(graphics, -1, -1);
            return;
        }
        this.b(graphics, -1, -1);
    }
    
    public final void b(final int c) {
        this.c = c;
        this.d = c * 22 + 5 * (c - 1);
        this.a = new Insets(this.e + this.d, this.f, this.h, this.g);
    }
    
    public final Insets insets() {
        return this.a;
    }
    
    public bM(final int e, final int f, final int h, final int g, final cs a, final boolean a2) {
        this.a = 12;
        this.a = new CardLayout(0, 0);
        this.a = new Vector();
        this.b = new Vector();
        this.a = new Hashtable();
        this.a = false;
        this.b = 0;
        this.setLayout(this.a);
        this.c = 1;
        this.a = a;
        this.a = a2;
        this.e = e;
        this.f = f;
        this.g = g;
        this.h = h;
        this.b(1);
        this.a = ((this.a == null) ? cz.a : this.a.a);
        if (this.a) {
            this.a = 0;
        }
        this.setBackground(this.a.h);
        this.setForeground(this.a.g);
    }
    
    public bM(final cs cs) {
        this(2, 10, 2, 10, cs, false);
    }
    
    public bM(final cs cs, final boolean b) {
        this(4, 4, 4, 4, cs, b);
    }
}
