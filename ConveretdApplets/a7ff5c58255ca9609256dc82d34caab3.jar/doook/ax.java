// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Image;
import java.awt.Canvas;

public class ax extends Canvas implements aO
{
    private String m;
    private String o;
    private String b;
    protected boolean h;
    protected boolean d;
    protected boolean e;
    private Image f;
    private int p;
    private int q;
    private int C;
    int[] c;
    int ah;
    private boolean W;
    int H;
    private static int ai;
    private static int aj;
    private static boolean X;
    private int ak;
    private int al;
    private boolean Y;
    
    public void resize(final int q, int c) {
        if (c < this.C) {
            c = this.C;
        }
        super.resize(q, c);
        this.p = c;
        this.q = q;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.repaint();
            return false;
        }
        return true;
    }
    
    public void a(final String o, final String b) {
        this.o = o;
        this.b = b;
    }
    
    public String a(final Object o) {
        if (this.e) {
            return this.o;
        }
        return this.b;
    }
    
    public boolean b() {
        return this.e;
    }
    
    public void c() {
        if (!this.e) {
            this.e = true;
            this.repaint();
            final Container parent = this.getParent();
            if (parent instanceof au) {
                ((au)parent).a(true, true);
            }
        }
    }
    
    public void g() {
        if (this.e) {
            this.e = false;
            this.repaint();
            final Container parent = this.getParent();
            if (parent instanceof au) {
                ((au)parent).a(true, false);
            }
        }
    }
    
    public void a(final Image f) {
        this.f = f;
        this.repaint();
    }
    
    public void a(final String m) {
        if (this.m == null || (m != null && !m.equals(this.m))) {
            this.m = m;
            this.repaint();
        }
    }
    
    public String c() {
        return this.m;
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.q, this.p);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public void l() {
        if (this.e) {
            final Graphics graphics = this.getGraphics();
            if (graphics != null) {
                this.h = true;
                this.d = true;
                this.a(graphics);
                this.h = false;
                this.d = false;
                this.repaint(150L);
                graphics.dispose();
            }
            this.postEvent(new Event(this, 1001, this.m));
        }
    }
    
    public void a(final Graphics graphics, final int n, final int n2, final int n3, final int[] array, final int[] array2, final int n4) {
        for (int i = 0; i < array.length; ++i) {
            int n5 = array[i] + n4;
            if (n5 >= array2.length) {
                n5 = array2.length - 1;
            }
            if (n5 > n4 - 1) {
                graphics.setColor(new Color(array2[n5]));
                graphics.drawLine(n, n2 + i, n + n3, n2 + i);
            }
        }
    }
    
    private void b(final Graphics graphics) {
        if (graphics != null && this.isShowing()) {
            final Dimension size = this.size();
            final int n = size.width - 1;
            final int n2 = size.height - 1;
            if (this.e) {
                if (this.d && this.h) {
                    final int n3 = 4;
                    graphics.setColor(new Color(this.c[14 + n3]));
                    graphics.fillRect(1, 2, n - 3, n2 - 2);
                    this.a(graphics, 1, 1, n - 3, new int[] { 4, 9, 13 }, this.c, n3);
                    this.a(graphics, 1, 0 + n2 - 2, n - 4, new int[] { 11, 6 }, this.c, n3);
                    graphics.setColor(new Color(this.c[6 + n3]));
                    graphics.drawLine(0, 2, 0, 0 + n2 - 2);
                    graphics.setColor(new Color(this.c[11 + n3]));
                    graphics.drawLine(0 + n - 2, 3, 0 + n - 2, 0 + n2 - 2);
                    graphics.setColor(new Color(this.c[4 + n3]));
                    graphics.drawLine(0 + n - 1, 2, 0 + n - 1, 0 + n2 - 3);
                }
                else {
                    graphics.setColor(new Color(this.c[17]));
                    graphics.fillRect(1, 2, n - 2, n2 - 3);
                    this.a(graphics, 1, 0, n - 3, new int[] { 10, 37, 39, 36, 34, 32, 30, 28, 26, 23, 21, 19, 18 }, this.c, 0);
                    graphics.setColor(new Color(this.c[10]));
                    graphics.drawLine(0, 1, 0, 0 + n2 - 2);
                    graphics.setColor(new Color(this.c[32]));
                    graphics.drawLine(1, 1, 1, 0 + n2 - 3);
                    graphics.setColor(new Color(this.c[15]));
                    graphics.drawLine(1, 1, 1, 1);
                    graphics.setColor(new Color(this.c[15]));
                    graphics.drawLine(0 + n - 2, 1, 0 + n - 2, 1);
                    graphics.setColor(new Color(this.c[32]));
                    graphics.drawLine(0 + n - 2, 2, 0 + n - 2, 0 + n2 - 3);
                    graphics.setColor(new Color(this.c[4]));
                    graphics.drawLine(0 + n - 1, 1, 0 + n - 1, 0 + n2 - 2);
                    graphics.setColor(new Color(this.c[2]));
                    graphics.drawLine(1, 0 + n2 - 1, 0 + n - 2, 0 + n2 - 1);
                }
            }
            else {
                final Color background = this.getBackground();
                background.brighter();
                background.darker().darker();
                graphics.setColor(this.e ? Color.black : Color.gray);
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
            if (this.f != null) {
                final int width = this.f.getWidth(this);
                final int height = this.f.getHeight(this);
                if (width > 0 && height > 0) {
                    graphics.drawImage(this.f, (n - width) / 2, (n2 - height) / 2, this);
                }
                else {
                    graphics.drawImage(this.f, -2, -2, 1, 1, this);
                }
            }
            if (this.m != null) {
                final FontMetrics fontMetrics = graphics.getFontMetrics();
                final int n4 = (n - fontMetrics.stringWidth(this.m)) / 2 - 1;
                final int n5 = n2 / 2 + (fontMetrics.getAscent() - fontMetrics.getHeight() / 2);
                fontMetrics.getAscent();
                if (this.e) {
                    graphics.setColor(new Color((!this.d || !this.h) ? this.ah : 16777215));
                }
                else {
                    graphics.setColor(new Color((this.getForeground().getRed() + this.getBackground().getRed()) / 2, (this.getForeground().getGreen() + this.getBackground().getGreen()) / 2, (this.getForeground().getBlue() + this.getBackground().getBlue()) / 2));
                }
                graphics.drawString(this.m, n4, n5 + 1);
            }
        }
    }
    
    protected void a(final Graphics graphics) {
        if (graphics != null && this.isShowing()) {
            ax.ai = this.ak;
            ax.aj = this.al;
            ax.X = this.Y;
            if (this.W) {
                this.b(graphics);
                return;
            }
            final Dimension size = this.size();
            final int width = size.width;
            final int height = size.height;
            final int n = width - 1;
            final int n2 = height - 1;
            final Color background = this.getBackground();
            final Color brighter = background.brighter();
            final Color darker = background.darker();
            final Color darker2 = darker.darker();
            graphics.setColor(this.e ? Color.black : Color.gray);
            graphics.drawLine(3, 0, n - 2, 0);
            graphics.drawLine(n - 1, 1, n, 2);
            graphics.drawLine(n, 3, n, n2 - 2);
            graphics.drawLine(n - 1, n2 - 1, n - 2, n2);
            graphics.drawLine(n - 3, n2, 2, n2);
            graphics.drawLine(1, n2 - 1, 0, n2 - 2);
            graphics.drawLine(0, n2 - 3, 0, 2);
            graphics.drawLine(1, 1, 2, 0);
            if (this.d && this.h) {
                graphics.setColor(this.e ? darker2 : background);
                graphics.drawLine(1, n2 - 2, 1, 2);
                graphics.drawLine(1, 2, 2, 1);
                graphics.drawLine(2, 1, n - 2, 1);
                if (this.e) {
                    graphics.setColor(darker);
                }
                graphics.drawLine(2, n2 - 2, 2, 2);
                graphics.drawLine(2, 2, n - 3, 2);
                if (this.e) {
                    graphics.setColor(brighter);
                }
                graphics.drawLine(n - 1, 2, n - 1, n2 - 2);
                graphics.drawLine(n - 2, n2 - 1, 2, n2 - 1);
                if (this.e) {
                    graphics.setColor(background);
                }
                graphics.drawLine(n - 2, 3, n - 2, n2 - 2);
                graphics.drawLine(n - 2, n2 - 2, 2, n2 - 2);
            }
            else {
                graphics.setColor(background);
                graphics.drawLine(1, n2 - 2, 1, 2);
                graphics.drawLine(2, 1, n - 2, 1);
                if (this.e) {
                    graphics.setColor(brighter);
                }
                graphics.drawLine(2, n2 - 3, 2, 2);
                graphics.drawLine(3, 2, n - 2, 2);
                if (this.e) {
                    graphics.setColor(darker2);
                }
                graphics.drawLine(2, n2 - 1, n - 2, n2 - 1);
                graphics.drawLine(n - 1, n2 - 2, n - 1, 2);
                if (this.e) {
                    graphics.setColor(darker);
                }
                graphics.drawLine(2, n2 - 2, n - 2, n2 - 2);
                graphics.drawLine(n - 2, n2 - 3, n - 2, 3);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void c(final Graphics graphics) {
        final Dimension size = this.size();
        final int n = size.width - 1;
        final int n2 = size.height - 1;
        final Container parent = this.getParent();
        if (!(parent instanceof au)) {
            graphics.setColor(parent.getBackground());
            graphics.drawRect(0, 0, n, n2);
            graphics.drawRect(1, 1, n - 2, n2 - 2);
        }
        graphics.setColor(this.getBackground());
        graphics.fillRect(2, 2, n - 4, n2 - 4);
    }
    
    public void paint(final Graphics graphics) {
        if (this.isShowing()) {
            final Dimension size = this.size();
            final int n = size.width - 1;
            final int n2 = size.height - 1;
            final Color background = this.getBackground();
            final Color foreground = this.getForeground();
            this.c(graphics);
            this.a(graphics);
            if (this.f != null && !this.W) {
                final int width = this.f.getWidth(this);
                final int height = this.f.getHeight(this);
                if (width > 0 && height > 0) {
                    graphics.drawImage(this.f, (n - width) / 2, (n2 - height) / 2, this);
                }
                else {
                    graphics.drawImage(this.f, -2, -2, 1, 1, this);
                }
            }
            if (this.m != null && !this.W) {
                final FontMetrics fontMetrics = graphics.getFontMetrics();
                final int stringWidth = fontMetrics.stringWidth(this.m);
                final int height2 = fontMetrics.getHeight();
                final int ascent = fontMetrics.getAscent();
                if (this.e) {
                    graphics.setColor(foreground);
                }
                else {
                    graphics.setColor(new Color((foreground.getRed() + background.getRed()) / 2, (foreground.getGreen() + background.getGreen()) / 2, (foreground.getBlue() + background.getBlue()) / 2));
                }
                graphics.drawString(this.m, (n - stringWidth) / 2 - 1, n2 / 2 + (ascent - height2 / 2));
            }
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (this.e) {
            final Graphics graphics = this.getGraphics();
            boolean inside = this.inside(event.x, event.y);
            switch (event.id) {
                case 501: {
                    this.d = inside;
                    this.h = true;
                    if (graphics != null) {
                        this.a(graphics);
                        break;
                    }
                    break;
                }
                case 502: {
                    if (!this.h) {
                        break;
                    }
                    this.d = inside;
                    this.h = false;
                    if (graphics != null) {
                        this.a(graphics);
                    }
                    if (this.d) {
                        this.postEvent(new Event(this, event.when, 1001, event.x, event.y, event.key, event.modifiers, this.m));
                        break;
                    }
                    break;
                }
                case 505: {
                    inside = false;
                }
                case 504:
                case 506: {
                    if (this.d != inside) {
                        this.d = inside;
                        if (graphics != null) {
                            this.a(graphics);
                        }
                        this.d = inside;
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
    
    public void p() {
        this.resize(this.getFontMetrics(this.getFont()).stringWidth(this.m) + 20, this.p);
    }
    
    public boolean isShowing() {
        return (bs.d && bs.t == 1) || super.isShowing();
    }
    
    public ax() {
        this(null, 22, 22);
    }
    
    public ax(final int n, final int n2) {
        this(null, n, n2);
    }
    
    public ax(final String s, final int n, final int n2) {
        this(s, n, n2, false, null, null);
    }
    
    public ax(final String s, final int n, int c, final boolean b, final String s2, final String s3) {
        this.ah = 16777215;
        this.H = 20;
        this.m = null;
        this.o = null;
        this.b = null;
        this.h = false;
        this.d = false;
        this.e = true;
        this.f = null;
        if (b) {
            if (s2 != null) {
                try {
                    ax.ai = Integer.parseInt(s2, 16);
                    ax.X = true;
                }
                catch (Exception ex) {}
            }
            else {
                ax.ai = Integer.parseInt("DDDDDD", 16);
            }
            if (s3 != null) {
                try {
                    ax.aj = Integer.parseInt(s3, 16);
                    ax.X = true;
                }
                catch (Exception ex2) {}
            }
            else {
                ax.aj = 0;
            }
        }
        this.ak = ax.ai;
        this.al = ax.aj;
        this.Y = ax.X;
        this.W = ax.X;
        if (this.W) {
            this.ah = new Color(ax.aj).getRGB();
            final int rgb = new Color(ax.ai).getRGB();
            this.c = new int[this.H * 2 + 1];
            this.c = this.a(rgb);
        }
        this.C = this.getFontMetrics(ay.d).getHeight() + 7;
        this.a(s);
        this.setFont(ay.a);
        this.setBackground(doook.ah.i);
        this.setForeground(Color.black);
        if (c < this.C) {
            c = this.C;
        }
        this.resize(n, c);
    }
    
    public ax(final String s) {
        this(s, 22, 22);
        this.resize(this.getFontMetrics(ay.d).stringWidth(s) + 20, this.C);
    }
    
    int[] a(final int n) {
        final int[] array = new int[this.H * 2 + 1];
        final int n2 = (n & 0xFF0000) >> 16;
        final int n3 = (n & 0xFF00) >> 8;
        final int n4 = n & 0xFF;
        final int n5 = n2 / 3 / this.H;
        final int n6 = n3 / 3 / this.H;
        final int n7 = n4 / 3 / this.H;
        final int n8 = (255 - n2) / 3 * 2 / this.H;
        final int n9 = (255 - n3) / 3 * 2 / this.H;
        final int n10 = (255 - n4) / 3 * 2 / this.H;
        for (int i = 0; i < this.H + 1; ++i) {
            array[this.H - i] = (this.f(n2 - n5 * i) << 16) + (this.f(n3 - n6 * i) << 8) + this.f(n4 - n7 * i);
        }
        for (int j = 0; j < this.H + 1; ++j) {
            array[this.H + j] = (this.f(n2 + n8 * j) << 16) + (this.f(n3 + n9 * j) << 8) + this.f(n4 + n10 * j);
        }
        return array;
    }
    
    int f(final int n) {
        if (n > 255) {
            return 255;
        }
        if (n < 0) {
            return 0;
        }
        return n;
    }
    
    static {
        ax.ai = Integer.parseInt("DDDDDD", 16);
        ax.aj = 0;
        ax.X = false;
    }
}
