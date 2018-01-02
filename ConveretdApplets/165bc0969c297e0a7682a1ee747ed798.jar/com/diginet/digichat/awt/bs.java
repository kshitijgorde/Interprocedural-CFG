// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

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
import com.diginet.digichat.client.i;
import com.diginet.digichat.common.a2;
import java.awt.Insets;
import java.util.Hashtable;
import java.util.Vector;
import java.awt.CardLayout;
import java.awt.Panel;

public class bs extends Panel
{
    private int a;
    private CardLayout b;
    private Vector c;
    private Vector d;
    private Hashtable e;
    private boolean f;
    private boolean fCheck;
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
    private a2 q;
    private i r;
    
    public void a(final String s, final Component component) {
        this.a(s, component, null, null);
    }
    
    public void a(final String s, final Component component, final Image image, final Image image2) {
        this.a(s, component, -1, image, image2);
    }
    
    public void a(final String s, final Component component, final int n, final Image image, final Image image2) {
        if (n < 0) {
            this.c.addElement(s);
            this.d.addElement(component);
        }
        else {
            this.c.insertElementAt(s, n);
            this.d.insertElementAt(component, n);
        }
        if (this.f) {
            this.fCheck = true;
            if (image != null && image2 != null) {
                this.e.put(s, new Image[] { image, image2 });
            }
        }
        this.add(s, component);
    }
    
    public void remove(final Component component) {
        final int index;
        if ((index = this.d.indexOf(component)) >= 0) {
            this.e.remove(this.c.elementAt(index));
            this.d.removeElementAt(index);
            this.c.removeElementAt(index);
        }
        super.remove(component);
    }
    
    public Component a(final int n) {
        return this.getComponent(n);
    }
    
    public Component getComponentAt(final int n, final int n2) {
        return this.d.elementAt(this.i);
    }
    
    private void chkImg() {
        if (this.fCheck) {
            if (this.f) {
                for (int i = 0; i < this.c.size(); ++i) {
                    if (this.e.get(this.c.elementAt(i)) == null) {
                        this.f = false;
                        this.a = 12;
                        break;
                    }
                }
            }
            this.fCheck = false;
        }
    }
    
    public void b(final int i) {
        if (this.k > 0) {
            final int j = this.i;
            final int n = (this.c.size() + this.k - 1) / this.k;
            this.i = i;
            final Graphics graphics = this.getGraphics();
            if (graphics != null) {
                if (n != 0) {
                    if (this.i / n == j / n) {
                        this.chkImg();
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
                }
                graphics.dispose();
            }
            this.b.show(this, this.c.elementAt(i));
        }
    }
    
    public Dimension minimumSize() {
        return this.preferredSize();
    }
    
    public Dimension preferredSize() {
        int n = 0;
        int n2 = 0;
        if (this.k > 0) {
            final int n3 = (this.c.size() + this.k - 1) / this.k;
            final FontMetrics fontMetrics = this.getFontMetrics(dw.b);
            for (int i = 0; i < this.c.size(); ++i) {
                if (i % n3 == 0) {
                    if (n > n2) {
                        n2 = n;
                    }
                    n = 0;
                }
                int n4 = fontMetrics.stringWidth(this.c.elementAt(i));
                this.chkImg();
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
        }
        final Dimension preferredSize = super.preferredSize();
        return new Dimension(Math.max(preferredSize.width, n2 + 10 + this.q.v / 2), Math.max(preferredSize.height, 42));
    }
    
    private int calcY(final int n, final int n2, final int n3) {
        int n4;
        int n5;
        for (n4 = n2 / n, n5 = 0; (n3 + n5) % this.k != n4; ++n5) {}
        return (this.k - n5 - 1) * 27;
    }
    
    public int a(final int n, final int n2) {
        if (this.k > 0) {
            int n3 = 3;
            final int n4 = (this.c.size() + this.k - 1) / this.k;
            final int n5 = this.i / n4;
            final FontMetrics fontMetrics = this.getFontMetrics(dw.b);
            for (int i = 0; i < this.c.size(); ++i) {
                final int calcY = this.calcY(n4, i, n5);
                if (i % n4 == 0) {
                    n3 = 3;
                }
                final String s = this.c.elementAt(i);
                int n6 = fontMetrics.stringWidth(this.c.elementAt(i));
                this.chkImg();
                if (this.f) {
                    final Image[] array = this.e.get(this.c.elementAt(i));
                    n6 = array[0].getWidth(this);
                    if (array[1].getWidth(this) > n6) {
                        n6 = array[1].getWidth(this);
                    }
                    if (new Rectangle(n3, calcY, n6, 22).inside(n, n2)) {
                        return i;
                    }
                }
                final int n7 = n3 + this.a * 2 + n6;
                if (!this.f && this.a(n3, calcY, n7, calcY + 22 - 1).inside(n, n2)) {
                    return i;
                }
                n3 = n7 + 3;
            }
        }
        return -1;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (n2 < this.l) {
            this.chkImg();
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
            final int n3 = 0;
            if (this.q.v == 0) {
                graphics.setColor(Color.white);
                graphics.drawLine(1, n3 + 22 + 1, this.size().width - 3, n3 + 22 + 1);
                graphics.setColor(Color.black);
                graphics.drawLine(1, n3 + 22, this.size().width - 2, n3 + 22);
            }
            if (this.k > 0) {
                int n4 = 3;
                final int size = this.c.size();
                final int n5 = (size + this.k - 1) / this.k;
                final int n6 = this.i / n5;
                for (int n7 = 0; (n == -1 || n7 <= n || n7 <= n2) && n7 < size; ++n7) {
                    if (n7 % n5 == 0) {
                        n4 = 0;
                    }
                    final Image image = ((Image[])this.e.get(this.c.elementAt(n7)))[n7 != n2 && (n != -1 || n7 != this.i)];
                    final int n8 = (this.k - 1 - (n6 + n7 / n5) % this.k) * 27;
                    final int n9 = this.a * 2 + image.getWidth(this);
                    if (n == -1 || n7 == n || n7 == n2) {
                        graphics.drawImage(image, n4, n8, null);
                    }
                    n4 += n9 + 3;
                }
            }
        }
    }
    
    private final void b(final Graphics graphics, final int n, final int n2) {
        if (graphics != null) {
            graphics.setFont(dw.b);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            int n3 = 3;
            final int size = this.c.size();
            final int height = fontMetrics.getHeight();
            final int n4 = (this.k == 0) ? 1 : ((size + this.k - 1) / this.k);
            final int n5 = this.i / n4;
            Color color = this.getBackground();
            if (color == null) {
                color = cv.b;
            }
            final Color darker = color.darker();
            final Color foreground = this.getForeground();
            final Color darker2 = darker.darker();
            for (int n6 = 0; (n == -1 || n6 <= n || n6 <= n2) && n6 < size; ++n6) {
                int calcY;
                if (this.k > 0) {
                    final String s = this.c.elementAt(n6);
                    final int stringWidth = fontMetrics.stringWidth(s);
                    if (n6 % n4 == 0) {
                        n3 = 3;
                    }
                    calcY = this.calcY(n4, n6, n5);
                    final int n7 = stringWidth + this.a * 2;
                    final int n8 = n3 + n7;
                    if (n == -1 || n6 == n || n6 == n2) {
                        final Polygon a = this.a(n3, calcY, n8, calcY + 22 - 1);
                        graphics.setColor((n6 != this.i) ? darker : color);
                        graphics.fillPolygon(a);
                        final int[] array = { -2, 0, 1, 5, 6, 7, 8, 9, n7 - 10 };
                        final int[] array2 = { 20, 20, 18, 5, 4, 2, 2, 1, 1 };
                        graphics.setColor(Color.white);
                        for (int i = 0; i < array.length - 1; ++i) {
                            graphics.drawLine(n3 + array[i], calcY + array2[i], n3 + array[i + 1], calcY + array2[i + 1]);
                        }
                        graphics.setColor(Color.black);
                        for (int j = 0; j < a.xpoints.length - 1; ++j) {
                            graphics.drawLine(a.xpoints[j], a.ypoints[j], a.xpoints[j + 1], a.ypoints[j + 1]);
                        }
                        graphics.setColor((n6 != this.i) ? darker2 : darker);
                        for (int k = 1; k < array.length - 2; ++k) {
                            graphics.drawLine(n8 - array[k], calcY + array2[k], n8 - array[k + 1], calcY + array2[k + 1]);
                        }
                        if (n6 == this.i) {
                            graphics.setColor(color);
                            graphics.drawLine(n3, calcY + 22, n8, calcY + 22);
                            graphics.drawLine(n3 + 1, calcY + 22 - 1, n8 - 1, calcY + 22 - 1);
                        }
                        else {
                            graphics.setColor((this.q.v == 0) ? Color.white : darker);
                            graphics.drawLine(n3, calcY + 22, n8, calcY + 22);
                            graphics.setColor((this.q.v == 0) ? Color.black : darker2);
                            graphics.drawLine(n3, calcY + 22 - 1, n8, calcY + 22 - 1);
                        }
                        graphics.setColor(foreground);
                        graphics.drawString(s, n3 + this.a, calcY + (22 + height) / 2 - 4);
                    }
                    n3 += n7 + 3;
                }
                else {
                    calcY = -21;
                }
                if ((n == -1 && (n6 + 1) % n4 == 0) || n6 == size - 1) {
                    int n9 = this.size().width - 1;
                    if (this.q.v != 0) {
                        n9 -= this.q.v / 2 - 1;
                    }
                    graphics.setColor((this.q.v == 0) ? Color.white : darker);
                    graphics.drawLine(n9 - 2, calcY + 22, n3 - 3 + 1, calcY + 22);
                    graphics.setColor((this.q.v == 0) ? Color.black : darker2);
                    graphics.drawLine(n9 - 1, calcY + 22 - 1, n3 - 3 + 1, calcY + 22 - 1);
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
        final int n3 = this.k * 27 - 5;
        final Color background = this.getBackground();
        final Color background2 = this.getParent().getBackground();
        this.chkImg();
        if (this.q.v != 0) {
            graphics.setColor(background2);
            graphics.fillRect(0, 0, size.width, size.height);
            final int v = this.q.v;
            final int n4 = 22;
            graphics.setColor(background);
            graphics.fillRoundRect(0, n4, n2, n - n4, v, v);
            graphics.drawArc(n2 - v, n - v - 1, v, v, 0, -90);
            graphics.fillRect(0, n4, v * 2, v * 2);
            graphics.setColor(background.darker());
            if (this.f) {
                graphics.drawLine(0, n4, n2 - v / 2, n4);
            }
            graphics.drawLine(n2, n4 + v / 2 - 2, n2, n - v / 2);
            graphics.drawArc(n2 - v, n4, v, v - 1, 0, 90);
            graphics.drawArc(n2 - v, n - v, v, v, 0, -90);
            graphics.drawLine(v / 2 - 1, n, n2 - v / 2, n);
            graphics.drawLine(0, n4, 0, n - v / 2);
            graphics.drawArc(0, n - v, v, v, 180, 90);
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
    
    public void c(final int k) {
        this.k = k;
        this.l = ((k > 0) ? (22 * k + 5 * (k - 1)) : 0);
        this.j = new Insets(this.m + this.l, this.n, this.p, this.o);
    }
    
    public Insets insets() {
        return this.j;
    }
    
    public bs(final int m, final int n, final int p7, final int o, final int k, final i r, final boolean f) {
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
        this.c(k);
        this.q = ((this.r == null) ? a2.a : this.r.cc);
        if (this.f) {
            this.a = 0;
        }
        this.fCheck = this.f;
        this.setBackground(this.q.j);
        this.setForeground(this.q.i);
    }
    
    public bs(final int n, final int n2, final int n3, final int n4, final int n5, final i i) {
        this(n, n2, n3, n4, n5, i, false);
    }
    
    public bs(final i i, final boolean b) {
        this(4, 4, 4, 4, 1, i, b);
    }
}
