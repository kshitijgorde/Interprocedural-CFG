// 
// Decompiled by Procyon v0.5.30
// 

package a;

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
import java.awt.Font;
import java.awt.Panel;

public final class aR extends Panel
{
    private static Font q;
    private int q;
    private CardLayout q;
    private Vector q;
    private Vector w;
    private Hashtable q;
    private boolean q;
    private int w;
    private Insets q;
    private int e;
    private int r;
    private int t;
    private int y;
    private int u;
    private int i;
    private aS q;
    private bp q;
    
    public final void q(final String s, final Component component) {
        this.q(s, component, null, null);
    }
    
    public final void q(final String s, final Component component, final Image image, final Image image2) {
        this.q.addElement(s);
        this.w.addElement(component);
        if (image != null && image2 != null && this.q) {
            this.q.put(s, new Image[] { image, image2 });
        }
        this.add(s, component);
    }
    
    public final Component getComponentAt(final int n, final int n2) {
        return this.w.elementAt(this.w);
    }
    
    public final void q(final int w) {
        final int w2 = this.w;
        final int n = (this.q.size() + this.e - 1) / this.e;
        this.w = w;
        final Graphics graphics;
        if ((graphics = this.getGraphics()) != null) {
            if (this.w / n == w2 / n) {
                if (this.q) {
                    this.q(graphics, w2, this.w);
                }
                else {
                    this.w(graphics, w2, this.w);
                }
            }
            else {
                this.repaint();
            }
            graphics.dispose();
        }
        this.q.show(this, this.q.elementAt(w));
    }
    
    public final Dimension minimumSize() {
        return this.preferredSize();
    }
    
    public final Dimension preferredSize() {
        int n = 0;
        int n2 = 0;
        final int n3 = (this.q.size() + this.e - 1) / this.e;
        final FontMetrics fontMetrics = this.getFontMetrics(aR.q);
        for (int i = 0; i < this.q.size(); ++i) {
            if (i % n3 == 0) {
                if (n > n2) {
                    n2 = n;
                }
                n = 0;
            }
            int n4 = fontMetrics.stringWidth(this.q.elementAt(i));
            if (this.q) {
                final Image[] array;
                n4 = (array = (Image[])this.q.get(this.q.elementAt(i)))[0].getWidth(this);
                if (array[1].getWidth(this) > n4) {
                    n4 = array[1].getWidth(this);
                }
            }
            n += n4 + 3 + (this.q << 1);
        }
        if (n > n2) {
            n2 = n;
        }
        final Dimension preferredSize = super.preferredSize();
        final Dimension dimension = new Dimension(Math.max(preferredSize.width, aS.w.y), preferredSize.height);
        return new Dimension(Math.max(dimension.width, n2 + 10 + this.q.e / 2), Math.max(dimension.height, 42));
    }
    
    public final boolean mouseDown(final Event event, int n, int n2) {
        if (n2 < this.r) {
            final int n3 = n;
            n2 = n2;
            n = n3;
            int n4 = 3;
            final int n5 = (this.q.size() + this.e - 1) / this.e;
            final int n6 = this.w / n5;
            final FontMetrics fontMetrics = this.getFontMetrics(aR.q);
            int i = 0;
        Label_0290:
            while (true) {
                while (i < this.q.size()) {
                    final int n7 = (this.e - 1 - (n6 + i / n5) % this.e) * 27;
                    if (i % n5 == 0) {
                        n4 = 3;
                    }
                    this.q.elementAt(i);
                    int n8 = fontMetrics.stringWidth(this.q.elementAt(i));
                    if (this.q) {
                        final Image[] array;
                        n8 = (array = (Image[])this.q.get(this.q.elementAt(i)))[0].getWidth(this);
                        if (array[1].getWidth(this) > n8) {
                            n8 = array[1].getWidth(this);
                        }
                        if (new Rectangle(n4, n7, n8, 22).inside(n, n2)) {
                            final int n10;
                            final int n9 = n10 = i;
                            break Label_0290;
                        }
                    }
                    final int n11 = n4 + (this.q << 1) + n8;
                    if (this.q || !q(n4, n7, n11, n7 + 22 - 1).inside(n, n2)) {
                        n4 = n11 + 3;
                        ++i;
                        continue;
                    }
                    int n10;
                    final int n9 = n10 = i;
                    final int n12 = n10;
                    if (n9 != -1 && n12 != this.w) {
                        this.q(n12);
                        return true;
                    }
                    return true;
                }
                int n10;
                final int n9 = n10 = -1;
                continue Label_0290;
            }
        }
        return true;
    }
    
    private static Polygon q(final int n, final int n2, final int n3, final int n4) {
        final int[] array = { n - 3 + 1, n, n + 4, n + 5, n + 6, n + 7, n + 8, n + 9, n3 - 9, n3 - 8, n3 - 7, n3 - 6, n3 - 5, n3 - 4, n3, n3 + 1 };
        return new Polygon(array, new int[] { n4, n4 - 1, n2 + 5, n2 + 4, n2 + 2, n2 + 1, n2 + 1, n2, n2, n2 + 1, n2 + 1, n2 + 2, n2 + 4, n2 + 5, n4 - 1, n4 }, array.length);
    }
    
    private void q(final Graphics graphics, final int n, final int n2) {
        if (graphics != null) {
            int n3 = 3;
            final int size;
            final int n4 = ((size = this.q.size()) + this.e - 1) / this.e;
            final int n5 = this.w / n4;
            if (this.q.e == 0) {
                graphics.setColor(Color.white);
                graphics.drawLine(1, 23, this.size().width - 3, 23);
                graphics.setColor(Color.black);
                graphics.drawLine(1, 22, this.size().width - 2, 22);
            }
            for (int n6 = 0; (n == -1 || n6 <= n || n6 <= n2) && n6 < size; ++n6) {
                if (n6 % n4 == 0) {
                    n3 = 0;
                }
                final Image image = ((Image[])this.q.get(this.q.elementAt(n6)))[n6 != n2 && (n != -1 || n6 != this.w)];
                final int n7 = (this.e - 1 - (n5 + n6 / n4) % this.e) * 27;
                final int n8 = (this.q << 1) + image.getWidth(this);
                if (n == -1 || n6 == n || n6 == n2) {
                    graphics.drawImage(image, n3, n7, null);
                }
                n3 += n8 + 3;
            }
        }
    }
    
    private final void w(final Graphics graphics, final int n, final int n2) {
        if (graphics != null) {
            graphics.setFont(aR.q);
            final FontMetrics fontMetrics = graphics.getFontMetrics();
            int n3 = 3;
            final int size = this.q.size();
            final int height = fontMetrics.getHeight();
            final int n4 = (size + this.e - 1) / this.e;
            final int n5 = this.w / n4;
            Color color;
            if ((color = this.getBackground()) == null) {
                color = ah.w;
            }
            final Color darker = color.darker();
            final Color foreground = this.getForeground();
            final Color darker2 = darker.darker();
            for (int n6 = 0; (n == -1 || n6 <= n || n6 <= n2) && n6 < size; ++n6) {
                final String s = this.q.elementAt(n6);
                final int stringWidth = fontMetrics.stringWidth(s);
                if (n6 % n4 == 0) {
                    n3 = 3;
                }
                final int n7 = (this.e - 1 - (n5 + n6 / n4) % this.e) * 27;
                final int n8 = stringWidth + (this.q << 1);
                final int n9 = n3 + n8;
                if (n == -1 || n6 == n || n6 == n2) {
                    final Polygon q = q(n3, n7, n9, n7 + 22 - 1);
                    graphics.setColor((n6 != this.w) ? darker : color);
                    graphics.fillPolygon(q);
                    final int[] array = { -2, 0, 1, 5, 6, 7, 8, 9, n8 - 10 };
                    final int[] array2 = { 20, 20, 18, 5, 4, 2, 2, 1, 1 };
                    graphics.setColor(Color.white);
                    for (int i = 0; i < array.length - 1; ++i) {
                        graphics.drawLine(n3 + array[i], n7 + array2[i], n3 + array[i + 1], n7 + array2[i + 1]);
                    }
                    graphics.setColor(Color.black);
                    for (int j = 0; j < q.xpoints.length - 1; ++j) {
                        graphics.drawLine(q.xpoints[j], q.ypoints[j], q.xpoints[j + 1], q.ypoints[j + 1]);
                    }
                    graphics.setColor((n6 != this.w) ? darker2 : darker);
                    for (int k = 1; k < array.length - 2; ++k) {
                        graphics.drawLine(n9 - array[k], n7 + array2[k], n9 - array[k + 1], n7 + array2[k + 1]);
                    }
                    if (n6 == this.w) {
                        graphics.setColor(color);
                        graphics.drawLine(n3, n7 + 22, n9, n7 + 22);
                        graphics.drawLine(n3 + 1, n7 + 22 - 1, n9 - 1, n7 + 22 - 1);
                    }
                    else {
                        graphics.setColor((this.q.e == 0) ? Color.white : darker);
                        graphics.drawLine(n3, n7 + 22, n9, n7 + 22);
                        graphics.setColor((this.q.e == 0) ? Color.black : darker2);
                        graphics.drawLine(n3, n7 + 22 - 1, n9, n7 + 22 - 1);
                    }
                    graphics.setColor(foreground);
                    graphics.drawString(s, n3 + this.q, n7 + (height + 22) / 2 - 4);
                }
                n3 += n8 + 3;
                if ((n == -1 && (n6 + 1) % n4 == 0) || n6 == size - 1) {
                    int n10 = this.size().width - 1;
                    if (this.q.e != 0) {
                        n10 -= this.q.e / 2 - 1;
                    }
                    graphics.setColor((this.q.e == 0) ? Color.white : darker);
                    graphics.drawLine(n10 - 2, n7 + 22, n3 - 3 + 1, n7 + 22);
                    graphics.setColor((this.q.e == 0) ? Color.black : darker2);
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
        final int n3 = this.e * 27 - 5;
        final Color background = this.getBackground();
        final Color background2 = this.getParent().getBackground();
        if (this.q.e != 0) {
            graphics.setColor(background2);
            graphics.fillRect(0, 0, size.width, size.height);
            final int e = this.q.e;
            graphics.setColor(background);
            graphics.fillRoundRect(0, 22, n2, n - 22, e, e);
            graphics.drawArc(n2 - e, n - e - 1, e, e, 0, -90);
            graphics.fillRect(0, 22, e << 1, e << 1);
            graphics.setColor(background.darker());
            if (this.q) {
                graphics.drawLine(0, 22, n2 - e / 2, 22);
            }
            graphics.drawLine(n2, 22 + e / 2 - 2, n2, n - e / 2);
            graphics.drawArc(n2 - e, 22, e, e - 1, 0, 90);
            graphics.drawArc(n2 - e, n - e, e, e, 0, -90);
            graphics.drawLine(e / 2 - 1, n, n2 - e / 2, n);
            graphics.drawLine(0, 22, 0, n - e / 2);
            graphics.drawArc(0, n - e, e, e, 180, 90);
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
        if (this.q) {
            this.q(graphics, -1, -1);
            return;
        }
        this.w(graphics, -1, -1);
    }
    
    public final Insets insets() {
        return this.q;
    }
    
    private aR(final int t, int n, final int i, final int u, final int e, final bp q, final boolean q2) {
        this.q = 12;
        this.q = new CardLayout(0, 0);
        this.q = new Vector();
        this.w = new Vector();
        this.q = new Hashtable();
        this.q = false;
        this.w = 0;
        this.setLayout(this.q);
        this.e = e;
        this.q = q;
        this.q = q2;
        this.t = t;
        this.y = n;
        this.u = u;
        this.i = i;
        n = e;
        this.e = n;
        this.r = n * 22 + 5 * (n - 1);
        this.q = new Insets(this.t + this.r, this.y, this.i, this.u);
        this.q = ((this.q == null) ? aS.q : aS.w);
        if (this.q) {
            this.q = 0;
        }
        this.setBackground(this.q.i);
        this.setForeground(this.q.u);
    }
    
    public aR(final int n, final int n2, final int n3, final int n4, final int n5, final bp bp) {
        this(2, 10, 2, 10, n5, bp, false);
    }
    
    public aR(final bp bp, final boolean b) {
        this(4, 4, 4, 4, 1, bp, b);
    }
    
    static {
        aR.q = bd.e;
    }
}
