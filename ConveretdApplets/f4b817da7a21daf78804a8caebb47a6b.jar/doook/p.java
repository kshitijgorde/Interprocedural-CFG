// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Event;
import java.awt.Rectangle;
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

public class p extends Panel
{
    private int e;
    private CardLayout a;
    private Vector a;
    private Vector b;
    private Hashtable a;
    private boolean c;
    private final int g;
    private final int h;
    private int i;
    private Insets b;
    private int j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private ap a;
    private be c;
    
    public void a(final String s, final Component component) {
        this.a(s, component, null, null);
    }
    
    public void a(final String s, final Component component, final Image image, final Image image2) {
        this.a.addElement(s);
        this.b.addElement(component);
        if (image != null && image2 != null && this.c) {
            this.a.put(s, new Image[] { image, image2 });
        }
        this.add(s, component);
    }
    
    public Component getComponentAt(final int n, final int n2) {
        return this.b.elementAt(this.i);
    }
    
    public void b(final int i) {
        final int j = this.i;
        final int n = (this.a.size() + this.j - 1) / this.j;
        this.i = i;
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            if (this.i / n == j / n) {
                if (this.c) {
                    this.a(graphics, j, this.i);
                }
                else {
                    this.b(graphics, j, this.i);
                }
            }
            else {
                this.repaint();
            }
            graphics.dispose();
        }
        this.a.show(this, this.a.elementAt(i));
    }
    
    public Dimension minimumSize() {
        return this.preferredSize();
    }
    
    public Dimension preferredSize() {
        int n = 0;
        int n2 = 0;
        final int n3 = (this.a.size() + this.j - 1) / this.j;
        final FontMetrics fontMetrics = this.getFontMetrics(aK.f);
        for (int i = 0; i < this.a.size(); ++i) {
            if (i % n3 == 0) {
                if (n > n2) {
                    n2 = n;
                }
                n = 0;
            }
            int n4 = fontMetrics.stringWidth(this.a.elementAt(i));
            if (this.c) {
                final Image[] array = this.a.get(this.a.elementAt(i));
                n4 = array[0].getWidth(this);
                if (array[1].getWidth(this) > n4) {
                    n4 = array[1].getWidth(this);
                }
            }
            n += n4 + 3 + this.e * 2;
        }
        if (n > n2) {
            n2 = n;
        }
        final Dimension preferredSize = super.preferredSize();
        return new Dimension(Math.max(preferredSize.width, n2 + 10 + this.a.r / 2), Math.max(preferredSize.height, 42));
    }
    
    public int a(final int n, final int n2) {
        int n3 = 3;
        final int n4 = (this.a.size() + this.j - 1) / this.j;
        final int n5 = this.i / n4;
        final FontMetrics fontMetrics = this.getFontMetrics(aK.f);
        for (int i = 0; i < this.a.size(); ++i) {
            final int n6 = (this.j - 1 - (n5 + i / n4) % this.j) * 27;
            if (i % n4 == 0) {
                n3 = 3;
            }
            final String s = this.a.elementAt(i);
            int n7 = fontMetrics.stringWidth(this.a.elementAt(i));
            if (this.c) {
                final Image[] array = this.a.get(this.a.elementAt(i));
                n7 = array[0].getWidth(this);
                if (array[1].getWidth(this) > n7) {
                    n7 = array[1].getWidth(this);
                }
                if (new Rectangle(n3, n6, n7, 22).inside(n, n2)) {
                    return i;
                }
            }
            final int n8 = n3 + this.e * 2 + n7;
            if (!this.c && this.a(n3, n6, n8, n6 + 22 - 1).inside(n, n2)) {
                return i;
            }
            n3 = n8 + 3;
        }
        return -1;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (n2 < this.k) {
            final int a = this.a(n, n2);
            if (a != -1 && a != this.i) {
                this.b(a);
            }
        }
        return true;
    }
    
    private final Polygon a(final int n, final int n2, final int n3, final int n4) {
        final int[] array = { n - 3 + 1, n, n + 4, n + 5, n + 6, n + 7, n + 8, n + 9, n3 - 9, n3 - 8, n3 - 7, n3 - 6, n3 - 5, n3 - 4, n3, n3 + 1 };
        return new Polygon(array, new int[] { n4, n4 - 1, n2 + 5, n2 + 4, n2 + 2, n2 + 1, n2 + 1, n2, n2, n2 + 1, n2 + 1, n2 + 2, n2 + 4, n2 + 5, n4 - 1, n4 }, array.length);
    }
    
    public void a(final Graphics graphics, final int n, final int n2) {
        if (graphics != null) {
            int n3 = 3;
            final int size = this.a.size();
            final int n4 = (size + this.j - 1) / this.j;
            final int n5 = this.i / n4;
            final int n6 = 0;
            if (this.a.r == 0) {
                graphics.setColor(Color.white);
                graphics.drawLine(1, n6 + 22 + 1, this.size().width - 3, n6 + 22 + 1);
                graphics.setColor(Color.black);
                graphics.drawLine(1, n6 + 22, this.size().width - 2, n6 + 22);
            }
            for (int n7 = 0; (n == -1 || n7 <= n || n7 <= n2) && n7 < size; ++n7) {
                if (n7 % n4 == 0) {
                    n3 = 0;
                }
                final Image image = ((Image[])this.a.get(this.a.elementAt(n7)))[n7 != n2 && (n != -1 || n7 != this.i)];
                final int n8 = (this.j - 1 - (n5 + n7 / n4) % this.j) * 27;
                final int n9 = this.e * 2 + image.getWidth(this);
                if (n == -1 || n7 == n || n7 == n2) {
                    graphics.drawImage(image, n3, n8, null);
                }
                n3 += n9 + 3;
            }
        }
    }
    
    private final void b(final Graphics graphics, final int n, final int n2) {
        if (graphics != null) {
            graphics.setFont(aK.f);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            int n3 = 3;
            final int size = this.a.size();
            final int height = fontMetrics.getHeight();
            final int n4 = (size + this.j - 1) / this.j;
            final int n5 = this.i / n4;
            Color color = this.getBackground();
            if (color == null) {
                color = aH.b;
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
                final int n7 = (this.j - 1 - (n5 + n6 / n4) % this.j) * 27;
                final int n8 = stringWidth + this.e * 2;
                final int n9 = n3 + n8;
                if (n == -1 || n6 == n || n6 == n2) {
                    final Polygon a = this.a(n3, n7, n9, n7 + 22 - 1);
                    graphics.setColor((n6 == this.i) ? color : darker);
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
                    graphics.setColor((n6 == this.i) ? darker : darker2);
                    for (int k = 1; k < array.length - 2; ++k) {
                        graphics.drawLine(n9 - array[k], n7 + array2[k], n9 - array[k + 1], n7 + array2[k + 1]);
                    }
                    if (n6 == this.i) {
                        graphics.setColor(color);
                        graphics.drawLine(n3, n7 + 22, n9, n7 + 22);
                        graphics.drawLine(n3 + 1, n7 + 22 - 1, n9 - 1, n7 + 22 - 1);
                    }
                    else {
                        graphics.setColor((this.a.r != 0) ? darker : Color.white);
                        graphics.drawLine(n3, n7 + 22, n9, n7 + 22);
                        graphics.setColor((this.a.r != 0) ? darker2 : Color.black);
                        graphics.drawLine(n3, n7 + 22 - 1, n9, n7 + 22 - 1);
                    }
                    graphics.setColor(foreground);
                    graphics.drawString(s, n3 + this.e, n7 + (22 + height) / 2 - 4);
                }
                n3 += n8 + 3;
                if ((n == -1 && (n6 + 1) % n4 == 0) || n6 == size - 1) {
                    int n10 = this.size().width - 1;
                    if (this.a.r != 0) {
                        n10 -= this.a.r / 2 - 1;
                    }
                    graphics.setColor((this.a.r != 0) ? darker : Color.white);
                    graphics.drawLine(n10 - 2, n7 + 22, n3 - 3 + 1, n7 + 22);
                    graphics.setColor((this.a.r != 0) ? darker2 : Color.black);
                    graphics.drawLine(n10 - 1, n7 + 22 - 1, n3 - 3 + 1, n7 + 22 - 1);
                }
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Dimension size = this.size();
        final int n = size.height - 1;
        final int n2 = size.width - 1;
        final int n3 = this.j * 27 - 5;
        final Color background = this.getBackground();
        final Color background2 = this.getParent().getBackground();
        if (this.a.r != 0) {
            graphics.setColor(background2);
            graphics.fillRect(0, 0, size.width, size.height);
            final int r = this.a.r;
            final int n4 = 22;
            graphics.setColor(background);
            graphics.fillRoundRect(0, n4, n2, n - n4, r, r);
            graphics.drawArc(n2 - r, n - r - 1, r, r, 0, -90);
            graphics.fillRect(0, n4, r * 2, r * 2);
            graphics.setColor(background.darker());
            if (this.c) {
                graphics.drawLine(0, n4, n2 - r / 2, n4);
            }
            graphics.drawLine(n2, n4 + r / 2 - 2, n2, n - r / 2);
            graphics.drawArc(n2 - r, n4, r, r - 1, 0, 90);
            graphics.drawArc(n2 - r, n - r, r, r, 0, -90);
            graphics.drawLine(r / 2 - 1, n, n2 - r / 2, n);
            graphics.drawLine(0, n4, 0, n - r / 2);
            graphics.drawArc(0, n - r, r, r, 180, 90);
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
        if (this.c) {
            this.a(graphics, -1, -1);
        }
        else {
            this.b(graphics, -1, -1);
        }
    }
    
    public void c(final int j) {
        this.j = j;
        this.k = 22 * j + 5 * (j - 1);
        this.b = new Insets(this.l + this.k, this.m, this.o, this.n);
    }
    
    public Insets insets() {
        return this.b;
    }
    
    public p(final int l, final int m, final int o, final int n, final int j, final be c, final boolean c2) {
        this.e = 12;
        this.a = new CardLayout(0, 0);
        this.a = new Vector();
        this.b = new Vector();
        this.a = new Hashtable();
        this.c = false;
        this.g = 0;
        this.h = 1;
        this.i = 0;
        this.setLayout(this.a);
        this.j = j;
        this.c = c;
        this.c = c2;
        this.l = l;
        this.m = m;
        this.n = n;
        this.o = o;
        this.c(j);
        this.a = ((this.c != null) ? this.c.c : ap.b);
        if (this.c) {
            this.e = 0;
        }
        this.setBackground(this.a.k);
        this.setForeground(this.a.j);
    }
    
    public p(final int n, final int n2, final int n3, final int n4, final int n5, final be be) {
        this(n, n2, n3, n4, n5, be, false);
    }
    
    public p(final be be, final boolean b) {
        this(4, 4, 4, 4, 1, be, b);
    }
}
