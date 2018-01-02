// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.Container;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Panel;

public final class bz extends Panel implements aB
{
    private boolean b;
    private boolean c;
    public boolean a;
    private Image a;
    private int b;
    private int c;
    private int d;
    private Rectangle a;
    private Rectangle b;
    private boolean d;
    private boolean e;
    private boolean f;
    public v a;
    private cs a;
    private TextArea a;
    public int a;
    private int[] a;
    private boolean g;
    private int e;
    private int f;
    private boolean h;
    
    public final void resize(final int c, int d) {
        if (d < this.d) {
            d = this.d;
        }
        super.resize(c, d);
        this.b = d;
        this.c = c;
    }
    
    public final boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.repaint();
            return false;
        }
        return true;
    }
    
    public final String a(final Object o) {
        if (this.a) {
            return null;
        }
        return null;
    }
    
    public final void a() {
        this.a.a(2);
        this.a.a = this.a.a(4);
    }
    
    private void d() {
        int a = 1;
        if (this.d) {
            ++a;
        }
        if (this.e) {
            a += 2;
        }
        ((Component)(this.a = a)).setFont(new Font(this.a.a.a, a - 1, this.a.a.a));
    }
    
    public final void b() {
        if (!this.a) {
            this.a = true;
            this.repaint();
        }
    }
    
    public final void c() {
        if (this.a) {
            this.a = false;
            this.repaint();
        }
    }
    
    public final void a(final Image a) {
        this.a = a;
        this.repaint();
    }
    
    public final Dimension minimumSize() {
        return new Dimension(this.c, this.b);
    }
    
    public final Dimension preferredSize() {
        return this.minimumSize();
    }
    
    private static void a(final Graphics graphics, final int n, final int n2, final int n3, final int[] array, final int[] array2, final int n4) {
        for (int i = 0; i < array.length; ++i) {
            int n5;
            if ((n5 = array[i] + n4) >= array2.length) {
                n5 = array2.length - 1;
            }
            if (n5 > n4 - 1) {
                graphics.setColor(new Color(array2[n5]));
                graphics.drawLine(n, n2 + i, n + n3, n2 + i);
            }
        }
    }
    
    public final void a(final int n) {
        this.a = new int[(this.e << 1) + 1];
        this.a = this.a(n);
    }
    
    private void a(final Graphics graphics) {
        final Dimension size;
        final int width = (size = this.size()).width;
        final int height = size.height;
        if (this.a != null) {
            final int width2 = this.a.getWidth(this);
            final int height2 = this.a.getHeight(this);
            if (this.d) {
                graphics.setColor(new Color(12702446));
            }
            else {
                graphics.setColor(new Color(14804712));
            }
            graphics.fillRect(this.a.x, this.a.y, this.a.width, this.a.height);
            graphics.setColor(new Color(3238597));
            graphics.drawRect(this.a.x, this.a.y, this.a.width - 1, this.a.height - 1);
            if (this.e) {
                graphics.setColor(new Color(12702446));
            }
            else {
                graphics.setColor(new Color(14804712));
            }
            graphics.fillRect(this.b.x, this.b.y, this.b.width, this.b.height);
            graphics.setColor(new Color(3238597));
            graphics.drawRect(this.b.x, this.b.y, this.b.width - 1, this.b.height - 1);
            if (width2 > 0 && height2 > 0) {
                graphics.drawImage(this.a, (width - width2) / 2, (height - height2) / 2, this);
                return;
            }
            graphics.drawImage(this.a, -2, -2, 1, 1, this);
        }
    }
    
    private void b(final Graphics graphics) {
        if (graphics != null && this.isShowing()) {
            if (this.g) {
                final int n = (this = this).size().width - 1;
                final int n2 = this.size().height - 1;
                int f = 0;
                int n3 = 0;
                if (this.a && this.f != -1) {
                    f = this.f;
                    n3 = 6;
                }
                if (!this.a) {
                    f = 1;
                    if (this.f != -1) {
                        n3 = 6;
                    }
                }
                if (f == 0) {
                    graphics.setColor(new Color(this.a[17]));
                    graphics.fillRect(2, 3, n - 2, n2 - 3);
                    if (n3 == 0) {
                        a(graphics, 2, 1, n - 3, new int[] { 10, 37, 39, 36, 34, 32, 30, 28, 26, 23, 21, 19, 18 }, this.a, 0);
                    }
                    else {
                        a(graphics, 3, n2 + 1 - 15, n - 5, new int[] { 18, 19, 21, 22, 24, 26, 28, 30, 32, 34, 36, 38, 39, 39, 39 }, this.a, 0);
                        a(graphics, 2, 1, n - 3, new int[] { 10, 37 }, this.a, 0);
                    }
                    graphics.setColor(new Color(this.a[10]));
                    graphics.drawLine(1, 2, 1, n2 + 1 - 2);
                    graphics.setColor(new Color(this.a[32]));
                    graphics.drawLine(2, 2, 2, n2 + 1 - 3);
                    graphics.setColor(new Color(this.a[15]));
                    graphics.drawLine(2, 2, 2, 2);
                    graphics.setColor(new Color(this.a[15]));
                    graphics.drawLine(n + 1 - 2, 2, n + 1 - 2, 2);
                    graphics.setColor(new Color(this.a[32]));
                    graphics.drawLine(n + 1 - 2, 3, n + 1 - 2, n2 + 1 - 3);
                    graphics.setColor(new Color(this.a[4]));
                    graphics.drawLine(n + 1 - 1, 2, n + 1 - 1, n2 + 1 - 2);
                    graphics.setColor(new Color(this.a[2]));
                    graphics.drawLine(2, n2 + 1 - 1, n + 1 - 2, n2 + 1 - 1);
                }
                else {
                    if (n3 == 6) {
                        n3 = 9;
                    }
                    graphics.setColor(new Color(this.a[n3 + 14]));
                    graphics.fillRect(2, 3, n - 3, n2 - 2);
                    a(graphics, 2, 2, n - 3, new int[] { 4, 9, 13 }, this.a, n3);
                    a(graphics, 2, n2 + 1 - 2, n - 4, new int[] { 11, 6 }, this.a, n3);
                    graphics.setColor(new Color(this.a[n3 + 6]));
                    graphics.drawLine(1, 3, 1, n2 + 1 - 2);
                    graphics.setColor(new Color(this.a[n3 + 11]));
                    graphics.drawLine(n + 1 - 2, 4, n + 1 - 2, n2 + 1 - 2);
                    graphics.setColor(new Color(this.a[n3 + 4]));
                    graphics.drawLine(n + 1 - 1, 3, n + 1 - 1, n2 + 1 - 3);
                }
                if (this.a != null) {
                    final Dimension size = this.size();
                    graphics.drawImage(this.a, (size.width - 1 - this.a.getWidth(this)) / 2 + 1, (size.height - 1 - this.a.getHeight(this)) / 2 + 1, this);
                }
                return;
            }
            final Dimension size2;
            int width = (size2 = this.size()).width;
            int height = size2.height;
            --width;
            --height;
            final Color background;
            final Color brighter = (background = this.getBackground()).brighter();
            final Color darker2;
            final Color darker = (darker2 = background.darker()).darker();
            graphics.setColor(this.a ? Color.black : Color.gray);
            graphics.drawLine(3, 0, width - 2, 0);
            graphics.drawLine(width - 1, 1, width, 2);
            graphics.drawLine(width, 3, width, height - 2);
            graphics.drawLine(width - 1, height - 1, width - 2, height);
            graphics.drawLine(width - 3, height, 2, height);
            graphics.drawLine(1, height - 1, 0, height - 2);
            graphics.drawLine(0, height - 3, 0, 2);
            graphics.drawLine(1, 1, 2, 0);
            if (this.c && this.b) {
                graphics.setColor(this.a ? darker : background);
                graphics.drawLine(1, height - 2, 1, 2);
                graphics.drawLine(1, 2, 2, 1);
                graphics.drawLine(2, 1, width - 2, 1);
                if (this.a) {
                    graphics.setColor(darker2);
                }
                graphics.drawLine(2, height - 2, 2, 2);
                graphics.drawLine(2, 2, width - 3, 2);
                if (this.a) {
                    graphics.setColor(brighter);
                }
                graphics.drawLine(width - 1, 2, width - 1, height - 2);
                graphics.drawLine(width - 2, height - 1, 2, height - 1);
                if (this.a) {
                    graphics.setColor(background);
                }
                graphics.drawLine(width - 2, 3, width - 2, height - 2);
                graphics.drawLine(width - 2, height - 2, 2, height - 2);
                return;
            }
            graphics.setColor(background);
            graphics.drawLine(1, height - 2, 1, 2);
            graphics.drawLine(2, 1, width - 2, 1);
            if (this.a) {
                graphics.setColor(brighter);
            }
            graphics.drawLine(2, height - 3, 2, 2);
            graphics.drawLine(3, 2, width - 2, 2);
            if (this.a) {
                graphics.setColor(darker);
            }
            graphics.drawLine(2, height - 1, width - 2, height - 1);
            graphics.drawLine(width - 1, height - 2, width - 1, 2);
            if (this.a) {
                graphics.setColor(darker2);
            }
            graphics.drawLine(2, height - 2, width - 2, height - 2);
            graphics.drawLine(width - 2, height - 3, width - 2, 3);
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final void paint(final Graphics graphics) {
        if (this.isShowing()) {
            final Dimension size;
            final int n = (size = this.size()).width - 1;
            final int n2 = size.height - 1;
            if (this.g) {
                final int x = this.location().x;
                final int y = this.location().y;
                graphics.translate(-x, -y);
                this.getParent().paint(graphics);
                graphics.translate(x, y);
            }
            this.getBackground();
            this.getForeground();
            if (this.g) {
                this.b(graphics);
                return;
            }
            final Dimension size2;
            final int n3 = (size2 = this.size()).width - 1;
            final int n4 = size2.height - 1;
            final Container parent;
            if (!((parent = this.getParent()) instanceof g)) {
                graphics.setColor(parent.getBackground());
                graphics.drawRect(0, 0, n3, n4);
                graphics.drawRect(1, 1, n3 - 2, n4 - 2);
            }
            graphics.setColor(this.getBackground());
            graphics.fillRect(2, 2, n3 - 4, n4 - 4);
            this.b(graphics);
            if (this.a != null) {
                final int width = this.a.getWidth(this);
                final int height = this.a.getHeight(this);
                if (this.f) {
                    this.a = new Rectangle(4, 3, 18, 18);
                    this.b = new Rectangle(this.a.x + this.a.width + 1, this.a.y, 18, 18);
                    this.a(graphics);
                }
                if (width > 0 && height > 0) {
                    graphics.drawImage(this.a, (n - width) / 2, (n2 - height) / 2, this);
                    return;
                }
                graphics.drawImage(this.a, -2, -2, 1, 1, this);
            }
        }
    }
    
    public final boolean handleEvent(final Event event) {
        final Graphics graphics = this.getGraphics();
        boolean inside = this.inside(event.x, event.y);
        switch (event.id) {
            case 501: {
                this.c = inside;
                this.b = true;
                this.f = 1;
                if (this.a != null && this.a.inside(event.x, event.y)) {
                    this.d = !this.d;
                    this.d();
                    if (graphics != null) {
                        this.a(graphics);
                    }
                }
                if (this.a != null && this.b.inside(event.x, event.y)) {
                    this.e = !this.e;
                    this.d();
                    if (graphics != null) {
                        this.a(graphics);
                    }
                }
                if (graphics != null) {
                    this.b(graphics);
                    break;
                }
                break;
            }
            case 502: {
                if (!this.b) {
                    break;
                }
                this.c = inside;
                this.b = false;
                if (graphics != null) {
                    this.b(graphics);
                }
                if (this.c) {
                    this.postEvent(new Event(this, event.when, 1001, event.x, event.y, event.key, event.modifiers, null));
                    break;
                }
                break;
            }
            case 505: {
                inside = false;
                this.setCursor(Cursor.getPredefinedCursor(0));
                this.f = -1;
                if (graphics != null) {
                    this.b(graphics);
                }
            }
            case 503: {
                if (inside && !this.h) {
                    this.h = true;
                    this.setCursor(Cursor.getPredefinedCursor(12));
                    this.f = 0;
                    if (graphics != null) {
                        this.b(graphics);
                        break;
                    }
                    break;
                }
                else {
                    if (!inside) {
                        this.h = false;
                        this.setCursor(Cursor.getPredefinedCursor(0));
                        break;
                    }
                    break;
                }
                break;
            }
            case 504:
            case 506: {
                if (this.c != inside) {
                    this.c = inside;
                    if (graphics != null) {
                        this.b(graphics);
                    }
                    this.c = inside;
                    break;
                }
                break;
            }
        }
        if (graphics != null) {
            graphics.dispose();
        }
        return super.handleEvent(event);
    }
    
    public final boolean isShowing() {
        return (ce.b && ce.b == 1) || super.isShowing();
    }
    
    public bz(int rgb) {
        this(20, 20);
        this.h = false;
        this.g = true;
        o.e.getRGB();
        rgb = o.d.getRGB();
        this.a = new int[(this.e << 1) + 1];
        this.a = this.a(rgb);
    }
    
    public bz(final int n, final int n2, final cs a, final TextArea a2) {
        this(25, 25);
        this.a = a;
        this.a = a2;
        this.f = true;
        (this.a = new v(a, a2)).a(a.a.j);
        this.a.a = this.a.a(4);
        this.a.a(2);
        this.a.b = true;
        final GridBagConstraints gridBagConstraints = new GridBagConstraints();
        final GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        gridBagConstraints.fill = 0;
        gridBagConstraints.anchor = 13;
        gridBagConstraints.gridwidth = 0;
        gridBagConstraints.gridheight = -1;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new Insets(0, 1, 0, 1);
        layout.setConstraints(this.a, gridBagConstraints);
        this.add(this.a);
        this.a.setSize(10, 18);
    }
    
    private bz(final int n, int d) {
        this.e = 20;
        this.f = -1;
        final int[][] array = { { 20, 17, 17, 17, 17, 17, 17 }, { 20, 30, 31, 31, 31, 30, 30 }, { 30, 34, 34, 34, 32, 31, 30 }, { 34, 33, 31, 31, 30, 29, 28 }, { 34, 31, 29, 29, -1, -1, -1 }, { 32, 29, 27, -1, -1, -1, -1 }, { 31, 28, 26, -1, -1, -1, -1 }, { 30, 27, -1, -1, -1, -1, -1 }, { 30, 26, -1, -1, -1, -1, -1 } };
        final int[][] array2 = { { 17, 16, 15, 24, -1 }, { 29, 28, 27, 20, 16 }, { -1, -1, 28, 22, -1 }, { -1, 28, 27, -1, -1 } };
        final int[][] array3 = { { -1, -1, 25, -1, -1 }, { -1, -1, 24, 21, -1 }, { 25, 24, 22, 20, -1 }, { -1, 21, 20, 11, 18 }, { -1, -1, -1, 18, -1 } };
        final int[][] array4 = { { -1, 28, -1, -1 }, { -1, 27, 25, -1 }, { 18, 11, 24, 23 }, { -1, 18, -1, -1 } };
        this.b = false;
        this.c = false;
        this.a = true;
        this.a = null;
        this.f = false;
        this.d = false;
        this.e = false;
        this.a = 0;
        this.a = null;
        this.b = null;
        this.d = 15;
        this.repaint();
        this.setFont(bk.b);
        this.setBackground(o.b);
        this.setForeground(Color.black);
        if (d < this.d) {
            d = this.d;
        }
        this.resize(n, d);
    }
    
    private int[] a(int n) {
        final int[] array = new int[(this.e << 1) + 1];
        final int n2 = n >> 16 & 0xFF;
        final int n3 = n >> 8 & 0xFF;
        n &= 0xFF;
        final int n4 = n2 / 3 / this.e;
        final int n5 = n3 / 3 / this.e;
        final int n6 = n / 3 / this.e;
        final int n7 = ((255 - n2) / 3 << 1) / this.e;
        final int n8 = ((255 - n3) / 3 << 1) / this.e;
        final int n9 = ((255 - n) / 3 << 1) / this.e;
        for (int i = 0; i < this.e + 1; ++i) {
            array[this.e - i] = (a(n2 - n4 * i) << 16) + (a(n3 - n5 * i) << 8) + a(n - n6 * i);
        }
        for (int j = 0; j < this.e + 1; ++j) {
            array[this.e + j] = (a(n2 + n7 * j) << 16) + (a(n3 + n8 * j) << 8) + a(n + n9 * j);
        }
        return array;
    }
    
    private static int a(final int n) {
        if (n > 255) {
            return 255;
        }
        if (n < 0) {
            return 0;
        }
        return n;
    }
}
