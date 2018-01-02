// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.LayoutManager;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Container;
import java.awt.Image;
import java.awt.Component;
import com.diginet.digichat.client.h;
import com.diginet.digichat.common.Theme;
import java.awt.Insets;
import java.util.Hashtable;
import java.util.Vector;
import java.awt.CardLayout;
import java.awt.event.MouseListener;
import java.awt.Panel;

public class bj extends Panel implements MouseListener
{
    private int a;
    private CardLayout b;
    private Vector c;
    private Vector d;
    private Hashtable e;
    private boolean f;
    private final int g;
    private final int h;
    private int i;
    private Insets j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private Theme q;
    private h r;
    
    public final void a(final String s, final Component component) {
        this.a(s, component, null, null);
    }
    
    public final void a(final String s, final Component component, final Image image, final Image image2) {
        this.c.addElement(s);
        this.d.addElement(component);
        if (image != null && image2 != null && this.f) {
            this.e.put(s, new Image[] { image, image2 });
        }
        this.add(s, component);
    }
    
    public final Component getComponentAt(final int n, final int n2) {
        return this.d.elementAt(this.i);
    }
    
    public final void a(final int i) {
        final int j = this.i;
        final int n = (this.c.size() + this.k - 1) / this.k;
        this.i = i;
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            if (this.i / n == j / n) {
                if (this.f) {
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
        this.b.show(this, this.c.elementAt(i));
    }
    
    public final Dimension minimumSize() {
        return this.preferredSize();
    }
    
    public final Dimension preferredSize() {
        int n = 0;
        int n2 = 0;
        final int n3 = (this.c.size() + this.k - 1) / this.k;
        final FontMetrics fontMetrics = this.getFontMetrics(com.diginet.digichat.awt.m.b);
        for (int i = 0; i < this.c.size(); ++i) {
            if (i % n3 == 0) {
                if (n > n2) {
                    n2 = n;
                }
                n = 0;
            }
            int n4 = fontMetrics.stringWidth(this.c.elementAt(i));
            if (this.f) {
                final Image[] array = this.e.get(this.c.elementAt(i));
                n4 = array[0].getWidth(this);
                if (array[1].getWidth(this) > n4) {
                    n4 = array[1].getWidth(this);
                }
            }
            n += n4 + 3 + this.a * 2;
        }
        if (n > n2) {
            n2 = n;
        }
        final Dimension preferredSize = super.preferredSize();
        return new Dimension(Math.max(preferredSize.width, n2 + 10 + this.q.roundedCorners / 2), Math.max(preferredSize.height, 42));
    }
    
    public final int a(final int n, final int n2) {
        int n3 = 3;
        final int n4 = (this.c.size() + this.k - 1) / this.k;
        final int n5 = this.i / n4;
        final FontMetrics fontMetrics = this.getFontMetrics(com.diginet.digichat.awt.m.b);
        for (int i = 0; i < this.c.size(); ++i) {
            final int n6 = (this.k - 1 - (n5 + i / n4) % this.k) * 27;
            if (i % n4 == 0) {
                n3 = 3;
            }
            final String s = this.c.elementAt(i);
            int n7 = fontMetrics.stringWidth(this.c.elementAt(i));
            if (this.f) {
                final Image[] array = this.e.get(this.c.elementAt(i));
                n7 = array[0].getWidth(this);
                if (array[1].getWidth(this) > n7) {
                    n7 = array[1].getWidth(this);
                }
                if (new Rectangle(n3, n6, n7, 22).inside(n, n2)) {
                    return i;
                }
            }
            final int n8 = n3 + this.a * 2 + n7;
            if (!this.f && this.a(n3, n6, n8, n6 + 22 - 1).inside(n, n2)) {
                return i;
            }
            n3 = n8 + 3;
        }
        return -1;
    }
    
    private final Polygon a(final int n, final int n2, final int n3, final int n4) {
        final int[] array = { n - 3 + 1, n, n + 4, n + 5, n + 6, n + 7, n + 8, n + 9, n3 - 9, n3 - 8, n3 - 7, n3 - 6, n3 - 5, n3 - 4, n3, n3 + 1 };
        return new Polygon(array, new int[] { n4, n4 - 1, n2 + 5, n2 + 4, n2 + 2, n2 + 1, n2 + 1, n2, n2, n2 + 1, n2 + 1, n2 + 2, n2 + 4, n2 + 5, n4 - 1, n4 }, array.length);
    }
    
    public final void a(final Graphics graphics, final int n, final int n2) {
        if (graphics != null) {
            int n3 = 3;
            final int size = this.c.size();
            final int n4 = (size + this.k - 1) / this.k;
            final int n5 = this.i / n4;
            final int n6 = 0;
            if (this.q.roundedCorners == 0) {
                graphics.setColor(Color.white);
                graphics.drawLine(1, n6 + 22 + 1, this.size().width - 3, n6 + 22 + 1);
                graphics.setColor(Color.black);
                graphics.drawLine(1, n6 + 22, this.size().width - 2, n6 + 22);
            }
            for (int n7 = 0; (n == -1 || n7 <= n || n7 <= n2) && n7 < size; ++n7) {
                if (n7 % n4 == 0) {
                    n3 = 0;
                }
                final Image image = ((Image[])this.e.get(this.c.elementAt(n7)))[n7 != n2 && (n != -1 || n7 != this.i)];
                final int n8 = (this.k - 1 - (n5 + n7 / n4) % this.k) * 27;
                final int n9 = this.a * 2 + image.getWidth(this);
                if (n == -1 || n7 == n || n7 == n2) {
                    graphics.drawImage(image, n3, n8, null);
                }
                n3 += n9 + 3;
            }
        }
    }
    
    private final void b(final Graphics graphics, final int n, final int n2) {
        if (graphics != null) {
            graphics.setFont(com.diginet.digichat.awt.m.b);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            int n3 = 3;
            final int size = this.c.size();
            final int height = fontMetrics.getHeight();
            final int n4 = (size + this.k - 1) / this.k;
            final int n5 = this.i / n4;
            Color color = this.getBackground();
            if (color == null) {
                color = ColorChoice.lightGray;
            }
            final Color darker = color.darker();
            final Color foreground = this.getForeground();
            final Color darker2 = darker.darker();
            for (int n6 = 0; (n == -1 || n6 <= n || n6 <= n2) && n6 < size; ++n6) {
                final String s = this.c.elementAt(n6);
                final int stringWidth = fontMetrics.stringWidth(s);
                if (n6 % n4 == 0) {
                    n3 = 3;
                }
                final int n7 = (this.k - 1 - (n5 + n6 / n4) % this.k) * 27;
                final int n8 = stringWidth + this.a * 2;
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
                        graphics.setColor((this.q.roundedCorners != 0) ? darker : Color.white);
                        graphics.drawLine(n3, n7 + 22, n9, n7 + 22);
                        graphics.setColor((this.q.roundedCorners != 0) ? darker2 : Color.black);
                        graphics.drawLine(n3, n7 + 22 - 1, n9, n7 + 22 - 1);
                    }
                    graphics.setColor(foreground);
                    graphics.drawString(s, n3 + this.a, n7 + (22 + height) / 2 - 4);
                }
                n3 += n8 + 3;
                if ((n == -1 && (n6 + 1) % n4 == 0) || n6 == size - 1) {
                    int n10 = this.size().width - 1;
                    if (this.q.roundedCorners != 0) {
                        n10 -= this.q.roundedCorners / 2 - 1;
                    }
                    graphics.setColor((this.q.roundedCorners != 0) ? darker : Color.white);
                    graphics.drawLine(n10 - 2, n7 + 22, n3 - 3 + 1, n7 + 22);
                    graphics.setColor((this.q.roundedCorners != 0) ? darker2 : Color.black);
                    graphics.drawLine(n10 - 1, n7 + 22 - 1, n3 - 3 + 1, n7 + 22 - 1);
                }
            }
        }
    }
    
    public final void paint(final Graphics graphics) {
        if (graphics == null) {
            return;
        }
        final Dimension size = this.size();
        final int n = size.height - 1;
        final int n2 = size.width - 1;
        final int n3 = this.k * 27 - 5;
        final Color background = this.getBackground();
        final Color background2 = this.getParent().getBackground();
        if (this.q.roundedCorners != 0) {
            graphics.setColor(background2);
            graphics.fillRect(0, 0, size.width, size.height);
            final int roundedCorners = this.q.roundedCorners;
            final int n4 = 22;
            graphics.setColor(background);
            graphics.fillRoundRect(0, n4, n2, n - n4, roundedCorners, roundedCorners);
            graphics.drawArc(n2 - roundedCorners, n - roundedCorners - 1, roundedCorners, roundedCorners, 0, -90);
            graphics.fillRect(0, n4, roundedCorners * 2, roundedCorners * 2);
            graphics.setColor(background.darker());
            if (this.f) {
                graphics.drawLine(0, n4, n2 - roundedCorners / 2, n4);
            }
            graphics.drawLine(n2, n4 + roundedCorners / 2 - 2, n2, n - roundedCorners / 2);
            graphics.drawArc(n2 - roundedCorners, n4, roundedCorners, roundedCorners - 1, 0, 90);
            graphics.drawArc(n2 - roundedCorners, n - roundedCorners, roundedCorners, roundedCorners, 0, -90);
            graphics.drawLine(roundedCorners / 2 - 1, n, n2 - roundedCorners / 2, n);
            graphics.drawLine(0, n4, 0, n - roundedCorners / 2);
            graphics.drawArc(0, n - roundedCorners, roundedCorners, roundedCorners, 180, 90);
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
        if (this.f) {
            this.a(graphics, -1, -1);
        }
        else {
            this.b(graphics, -1, -1);
        }
    }
    
    public final void b(final int k) {
        this.k = k;
        this.l = 22 * k + 5 * (k - 1);
        this.j = new Insets(this.m + this.l, this.n, this.p, this.o);
    }
    
    public final Insets insets() {
        return this.j;
    }
    
    public final void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public final void mousePressed(final MouseEvent mouseEvent) {
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        mouseEvent.getSource();
        if (y < this.l) {
            final int a = this.a(x, y);
            if (a != -1 && a != this.i) {
                this.a(a);
            }
        }
    }
    
    public final void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public final void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public final void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public bj(final int m, final int n, final int p7, final int o, final int k, final h r, final boolean f) {
        this.a = 12;
        this.b = new CardLayout(0, 0);
        this.c = new Vector();
        this.d = new Vector();
        this.e = new Hashtable();
        this.f = false;
        this.g = 0;
        this.h = 1;
        this.i = 0;
        this.setLayout(this.b);
        this.k = k;
        this.r = r;
        this.f = f;
        this.m = m;
        this.n = n;
        this.o = o;
        this.p = p7;
        this.b(k);
        this.q = ((this.r != null) ? this.r.df : Theme.defaultTheme);
        if (this.f) {
            this.a = 0;
        }
        this.setBackground(this.q.tabsBackground);
        this.setForeground(this.q.tabsText);
        this.addMouseListener(this);
    }
    
    public bj(final int n, final int n2, final int n3, final int n4, final int n5, final h h) {
        this(n, n2, n3, n4, n5, h, false);
    }
    
    public bj(final h h) {
        this(4, 4, 4, 4, 1, h, false);
    }
    
    public bj(final h h, final boolean b) {
        this(4, 4, 4, 4, 1, h, b);
    }
}