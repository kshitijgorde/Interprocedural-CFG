// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Image;
import java.awt.Canvas;

public class i extends Canvas implements ab
{
    String a;
    private String b;
    private String c;
    protected boolean a;
    protected boolean b;
    protected boolean c;
    private Image a;
    private int a;
    private int b;
    private int c;
    private int[] a;
    private int d;
    private boolean d;
    private int e;
    
    public void resize(final int b, int c) {
        if (c < this.c) {
            c = this.c;
        }
        super.resize(b, c);
        this.a = c;
        this.b = b;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.repaint();
            return false;
        }
        return true;
    }
    
    public final void a(final String b, final String c) {
        this.b = b;
        this.c = c;
    }
    
    public final String a(final Object o) {
        if (this.c) {
            return this.b;
        }
        return this.c;
    }
    
    public final boolean a() {
        return this.c;
    }
    
    public final void a() {
        if (!this.c) {
            this.c = true;
            this.repaint();
            final Container parent;
            if ((parent = this.getParent()) instanceof f) {
                ((f)parent).a(true);
            }
        }
    }
    
    public final void b() {
        if (this.c) {
            this.c = false;
            this.repaint();
            final Container parent;
            if ((parent = this.getParent()) instanceof f) {
                ((f)parent).a(false);
            }
        }
    }
    
    public final void a(final Image a) {
        this.a = a;
        this.repaint();
    }
    
    public final void a(final String a) {
        if (this.a == null || (a != null && !a.equals(this.a))) {
            this.a = a;
            this.repaint();
        }
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.b, this.a);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public final void c() {
        if (this.c) {
            final Graphics graphics;
            if ((graphics = this.getGraphics()) != null) {
                this.a = true;
                this.b = true;
                this.a(graphics);
                this.a = false;
                this.b = false;
                this.repaint(150L);
                graphics.dispose();
            }
            this.postEvent(new Event(this, 1001, this.a));
        }
    }
    
    private static void a(final Graphics graphics, int i, final int n, final int n2, final int[] array, final int[] array2, final int n3) {
        int n4;
        for (i = 0; i < array.length; ++i) {
            if ((n4 = array[i] + n3) >= array2.length) {
                n4 = array2.length - 1;
            }
            if (n4 > n3 - 1) {
                graphics.setColor(new Color(array2[n4]));
                graphics.drawLine(1, n + i, n2 + 1, n + i);
            }
        }
    }
    
    protected void a(final Graphics graphics) {
        if (graphics != null && this.isShowing()) {
            if (this.d) {
                if (graphics != null && this.isShowing()) {
                    final Dimension size;
                    final int n = (size = this.size()).width - 1;
                    final int n2 = size.height - 1;
                    if (this.c) {
                        if (this.b && this.a) {
                            graphics.setColor(new Color(this.a[18]));
                            graphics.fillRect(1, 2, n - 3, n2 - 2);
                            a(graphics, 1, 1, n - 3, new int[] { 4, 9, 13 }, this.a, 4);
                            a(graphics, 1, n2 - 2, n - 4, new int[] { 11, 6 }, this.a, 4);
                            graphics.setColor(new Color(this.a[10]));
                            graphics.drawLine(0, 2, 0, n2 - 2);
                            graphics.setColor(new Color(this.a[15]));
                            graphics.drawLine(n - 2, 3, n - 2, n2 - 2);
                            graphics.setColor(new Color(this.a[8]));
                            graphics.drawLine(n - 1, 2, n - 1, n2 - 3);
                        }
                        else {
                            graphics.setColor(new Color(this.a[17]));
                            graphics.fillRect(1, 2, n - 2, n2 - 3);
                            a(graphics, 1, 0, n - 3, new int[] { 10, 37, 39, 36, 34, 32, 30, 28, 26, 23, 21, 19, 18 }, this.a, 0);
                            graphics.setColor(new Color(this.a[10]));
                            graphics.drawLine(0, 1, 0, n2 - 2);
                            graphics.setColor(new Color(this.a[32]));
                            graphics.drawLine(1, 1, 1, n2 - 3);
                            graphics.setColor(new Color(this.a[15]));
                            graphics.drawLine(1, 1, 1, 1);
                            graphics.setColor(new Color(this.a[15]));
                            graphics.drawLine(n - 2, 1, n - 2, 1);
                            graphics.setColor(new Color(this.a[32]));
                            graphics.drawLine(n - 2, 2, n - 2, n2 - 3);
                            graphics.setColor(new Color(this.a[4]));
                            graphics.drawLine(n - 1, 1, n - 1, n2 - 2);
                            graphics.setColor(new Color(this.a[2]));
                            graphics.drawLine(1, n2 - 1, n - 2, n2 - 1);
                        }
                    }
                    else {
                        final Color background;
                        (background = this.getBackground()).brighter();
                        background.darker().darker();
                        graphics.setColor(this.c ? Color.black : Color.gray);
                        graphics.drawLine(3, 0, n - 2, 0);
                        graphics.drawLine(n - 1, 1, n, 2);
                        graphics.drawLine(n, 3, n, n2 - 2);
                        graphics.drawLine(n - 1, n2 - 1, n - 2, n2);
                        graphics.drawLine(n - 3, n2, 2, n2);
                        graphics.drawLine(1, n2 - 1, 0, n2 - 2);
                        graphics.drawLine(0, n2 - 3, 0, 2);
                        graphics.drawLine(1, 1, 2, 0);
                        graphics.setColor(background);
                        graphics.drawLine(1, n2 - 2, 1, 2);
                        graphics.drawLine(2, 1, n - 2, 1);
                        graphics.drawLine(2, n2 - 3, 2, 2);
                        graphics.drawLine(3, 2, n - 2, 2);
                        graphics.drawLine(2, n2 - 1, n - 2, n2 - 1);
                        graphics.drawLine(n - 1, n2 - 2, n - 1, 2);
                        graphics.drawLine(2, n2 - 2, n - 2, n2 - 2);
                        graphics.drawLine(n - 2, n2 - 3, n - 2, 3);
                    }
                    if (this.a != null) {
                        final int width = this.a.getWidth(this);
                        final int height = this.a.getHeight(this);
                        if (width > 0 && height > 0) {
                            graphics.drawImage(this.a, (n - width) / 2, (n2 - height) / 2, this);
                        }
                        else {
                            graphics.drawImage(this.a, -2, -2, 1, 1, this);
                        }
                    }
                    if (this.a != null) {
                        final FontMetrics fontMetrics = graphics.getFontMetrics();
                        final int n3 = (n - fontMetrics.stringWidth(this.a)) / 2 - 1;
                        final int n4 = n2 / 2 + (fontMetrics.getAscent() - fontMetrics.getHeight() / 2);
                        fontMetrics.getAscent();
                        if (this.c) {
                            graphics.setColor(new Color(0));
                        }
                        else {
                            graphics.setColor(new Color((this.getForeground().getRed() + this.getBackground().getRed()) / 2, (this.getForeground().getGreen() + this.getBackground().getGreen()) / 2, (this.getForeground().getBlue() + this.getBackground().getBlue()) / 2));
                        }
                        if (this.c) {
                            graphics.drawString(this.a, n3 + 1, n4 + 1);
                        }
                        if (this.c) {
                            graphics.setColor(new Color((this.b && this.a) ? 65280 : this.d));
                        }
                        else {
                            graphics.setColor(new Color((this.getForeground().getRed() + this.getBackground().getRed()) / 2, (this.getForeground().getGreen() + this.getBackground().getGreen()) / 2, (this.getForeground().getBlue() + this.getBackground().getBlue()) / 2));
                        }
                        graphics.drawString(this.a, n3, n4 + 1);
                    }
                }
                return;
            }
            final Dimension size2;
            int width2 = (size2 = this.size()).width;
            int height2 = size2.height;
            --width2;
            --height2;
            final Color background2;
            final Color brighter = (background2 = this.getBackground()).brighter();
            final Color darker2;
            final Color darker = (darker2 = background2.darker()).darker();
            graphics.setColor(this.c ? Color.black : Color.gray);
            graphics.drawLine(3, 0, width2 - 2, 0);
            graphics.drawLine(width2 - 1, 1, width2, 2);
            graphics.drawLine(width2, 3, width2, height2 - 2);
            graphics.drawLine(width2 - 1, height2 - 1, width2 - 2, height2);
            graphics.drawLine(width2 - 3, height2, 2, height2);
            graphics.drawLine(1, height2 - 1, 0, height2 - 2);
            graphics.drawLine(0, height2 - 3, 0, 2);
            graphics.drawLine(1, 1, 2, 0);
            if (this.b && this.a) {
                graphics.setColor(this.c ? darker : background2);
                graphics.drawLine(1, height2 - 2, 1, 2);
                graphics.drawLine(1, 2, 2, 1);
                graphics.drawLine(2, 1, width2 - 2, 1);
                if (this.c) {
                    graphics.setColor(darker2);
                }
                graphics.drawLine(2, height2 - 2, 2, 2);
                graphics.drawLine(2, 2, width2 - 3, 2);
                if (this.c) {
                    graphics.setColor(brighter);
                }
                graphics.drawLine(width2 - 1, 2, width2 - 1, height2 - 2);
                graphics.drawLine(width2 - 2, height2 - 1, 2, height2 - 1);
                if (this.c) {
                    graphics.setColor(background2);
                }
                graphics.drawLine(width2 - 2, 3, width2 - 2, height2 - 2);
                graphics.drawLine(width2 - 2, height2 - 2, 2, height2 - 2);
                return;
            }
            graphics.setColor(background2);
            graphics.drawLine(1, height2 - 2, 1, 2);
            graphics.drawLine(2, 1, width2 - 2, 1);
            if (this.c) {
                graphics.setColor(brighter);
            }
            graphics.drawLine(2, height2 - 3, 2, 2);
            graphics.drawLine(3, 2, width2 - 2, 2);
            if (this.c) {
                graphics.setColor(darker);
            }
            graphics.drawLine(2, height2 - 1, width2 - 2, height2 - 1);
            graphics.drawLine(width2 - 1, height2 - 2, width2 - 1, 2);
            if (this.c) {
                graphics.setColor(darker2);
            }
            graphics.drawLine(2, height2 - 2, width2 - 2, height2 - 2);
            graphics.drawLine(width2 - 2, height2 - 3, width2 - 2, 3);
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.isShowing()) {
            final Dimension size;
            final int n = (size = this.size()).width - 1;
            final int n2 = size.height - 1;
            final Color background = this.getBackground();
            final Color foreground = this.getForeground();
            final Dimension size2;
            final int n3 = (size2 = this.size()).width - 1;
            final int n4 = size2.height - 1;
            final Container parent;
            if (!((parent = this.getParent()) instanceof f)) {
                graphics.setColor(parent.getBackground());
                graphics.drawRect(0, 0, n3, n4);
                graphics.drawRect(1, 1, n3 - 2, n4 - 2);
            }
            graphics.setColor(this.getBackground());
            graphics.fillRect(2, 2, n3 - 4, n4 - 4);
            this.a(graphics);
            if (this.a != null && !this.d) {
                final int width = this.a.getWidth(this);
                final int height = this.a.getHeight(this);
                if (width > 0 && height > 0) {
                    graphics.drawImage(this.a, (n - width) / 2, (n2 - height) / 2, this);
                }
                else {
                    graphics.drawImage(this.a, -2, -2, 1, 1, this);
                }
            }
            if (this.a != null && !this.d) {
                final FontMetrics fontMetrics;
                final int stringWidth = (fontMetrics = graphics.getFontMetrics()).stringWidth(this.a);
                final int height2 = fontMetrics.getHeight();
                final int ascent = fontMetrics.getAscent();
                if (this.c) {
                    graphics.setColor(foreground);
                }
                else {
                    graphics.setColor(new Color((foreground.getRed() + background.getRed()) / 2, (foreground.getGreen() + background.getGreen()) / 2, (foreground.getBlue() + background.getBlue()) / 2));
                }
                graphics.drawString(this.a, (n - stringWidth) / 2 - 1, n2 / 2 + (ascent - height2 / 2));
            }
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (this.c) {
            final Graphics graphics = this.getGraphics();
            boolean inside = this.inside(event.x, event.y);
            switch (event.id) {
                case 501: {
                    this.b = inside;
                    this.a = true;
                    if (graphics != null) {
                        this.a(graphics);
                        break;
                    }
                    break;
                }
                case 502: {
                    if (!this.a) {
                        break;
                    }
                    this.b = inside;
                    this.a = false;
                    if (graphics != null) {
                        this.a(graphics);
                    }
                    if (this.b) {
                        this.postEvent(new Event(this, event.when, 1001, event.x, event.y, event.key, event.modifiers, this.a));
                        break;
                    }
                    break;
                }
                case 505: {
                    inside = false;
                }
                case 504:
                case 506: {
                    if (this.b != inside) {
                        this.b = inside;
                        if (graphics != null) {
                            this.a(graphics);
                        }
                        this.b = inside;
                        break;
                    }
                    break;
                }
            }
            if (graphics != null) {
                graphics.dispose();
            }
        }
        return super.handleEvent(event);
    }
    
    public final void d() {
        this.resize(this.getFontMetrics(this.getFont()).stringWidth(this.a) + 20, this.a);
    }
    
    public boolean isShowing() {
        return (aZ.b && aZ.b == 1) || super.isShowing();
    }
    
    public i() {
        this(null, 22, 22);
    }
    
    public i(final int n, final int n2) {
        this(null, n, n2);
    }
    
    public i(final String s, final int n, int c) {
        this.d = 16777215;
        this.e = 20;
        this.a = null;
        this.b = null;
        this.c = null;
        this.a = false;
        this.b = false;
        this.c = true;
        this.a = null;
        this.d = j.a;
        if (this.d) {
            this.d = j.e.getRGB();
            final int rgb = j.d.getRGB();
            this.a = new int[(this.e << 1) + 1];
            this.a = this.a(rgb);
        }
        this.c = this.getFontMetrics(ay.b).getHeight() + 7;
        this.a(s);
        this.setFont(ay.b);
        this.setBackground(j.b);
        this.setForeground(Color.black);
        if (c < this.c) {
            c = this.c;
        }
        this.resize(n, c);
    }
    
    public i(final String s) {
        this(s, 22, 22);
        this.resize(this.getFontMetrics(ay.b).stringWidth(s) + 20, this.c);
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
