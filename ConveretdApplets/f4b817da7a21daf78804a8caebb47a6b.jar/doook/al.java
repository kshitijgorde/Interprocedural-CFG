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

public class al extends Canvas implements aj
{
    private String n;
    private String o;
    private String d;
    protected boolean h;
    protected boolean j;
    protected boolean m;
    private Image k;
    private int j;
    private int k;
    private int l;
    int[] b;
    int c;
    private boolean l;
    int d;
    private static int n;
    private static int o;
    private static boolean n;
    private int q;
    private int r;
    private boolean o;
    
    public void resize(final int k, int l) {
        if (l < this.l) {
            l = this.l;
        }
        super.resize(k, l);
        this.j = l;
        this.k = k;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.repaint();
            return false;
        }
        return true;
    }
    
    public void a(final String o, final String d) {
        this.o = o;
        this.d = d;
    }
    
    public String a(final Object o) {
        if (this.m) {
            return this.o;
        }
        return this.d;
    }
    
    public boolean c() {
        return this.m;
    }
    
    public void b() {
        if (!this.m) {
            this.m = true;
            this.repaint();
            final Container parent = this.getParent();
            if (parent instanceof P) {
                ((P)parent).a(true, true);
            }
        }
    }
    
    public void g() {
        if (this.m) {
            this.m = false;
            this.repaint();
            final Container parent = this.getParent();
            if (parent instanceof P) {
                ((P)parent).a(true, false);
            }
        }
    }
    
    public void a(final Image k) {
        this.k = k;
        this.repaint();
    }
    
    public void a(final String n) {
        if (this.n == null || (n != null && !n.equals(this.n))) {
            this.n = n;
            this.repaint();
        }
    }
    
    public String c() {
        return this.n;
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.k, this.j);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public void e() {
        if (this.m) {
            final Graphics graphics = this.getGraphics();
            if (graphics != null) {
                this.h = true;
                this.j = true;
                this.a(graphics);
                this.h = false;
                this.j = false;
                this.repaint(150L);
                graphics.dispose();
            }
            this.postEvent(new Event(this, 1001, this.n));
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
            if (this.m) {
                if (this.j && this.h) {
                    final int n3 = 4;
                    graphics.setColor(new Color(this.b[14 + n3]));
                    graphics.fillRect(1, 2, n - 3, n2 - 2);
                    this.a(graphics, 1, 1, n - 3, new int[] { 4, 9, 13 }, this.b, n3);
                    this.a(graphics, 1, 0 + n2 - 2, n - 4, new int[] { 11, 6 }, this.b, n3);
                    graphics.setColor(new Color(this.b[6 + n3]));
                    graphics.drawLine(0, 2, 0, 0 + n2 - 2);
                    graphics.setColor(new Color(this.b[11 + n3]));
                    graphics.drawLine(0 + n - 2, 3, 0 + n - 2, 0 + n2 - 2);
                    graphics.setColor(new Color(this.b[4 + n3]));
                    graphics.drawLine(0 + n - 1, 2, 0 + n - 1, 0 + n2 - 3);
                }
                else {
                    graphics.setColor(new Color(this.b[17]));
                    graphics.fillRect(1, 2, n - 2, n2 - 3);
                    this.a(graphics, 1, 0, n - 3, new int[] { 10, 37, 39, 36, 34, 32, 30, 28, 26, 23, 21, 19, 18 }, this.b, 0);
                    graphics.setColor(new Color(this.b[10]));
                    graphics.drawLine(0, 1, 0, 0 + n2 - 2);
                    graphics.setColor(new Color(this.b[32]));
                    graphics.drawLine(1, 1, 1, 0 + n2 - 3);
                    graphics.setColor(new Color(this.b[15]));
                    graphics.drawLine(1, 1, 1, 1);
                    graphics.setColor(new Color(this.b[15]));
                    graphics.drawLine(0 + n - 2, 1, 0 + n - 2, 1);
                    graphics.setColor(new Color(this.b[32]));
                    graphics.drawLine(0 + n - 2, 2, 0 + n - 2, 0 + n2 - 3);
                    graphics.setColor(new Color(this.b[4]));
                    graphics.drawLine(0 + n - 1, 1, 0 + n - 1, 0 + n2 - 2);
                    graphics.setColor(new Color(this.b[2]));
                    graphics.drawLine(1, 0 + n2 - 1, 0 + n - 2, 0 + n2 - 1);
                }
            }
            else {
                final Color background = this.getBackground();
                background.brighter();
                background.darker().darker();
                graphics.setColor(this.m ? Color.black : Color.gray);
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
            if (this.k != null) {
                final int width = this.k.getWidth(this);
                final int height = this.k.getHeight(this);
                if (width > 0 && height > 0) {
                    graphics.drawImage(this.k, (n - width) / 2, (n2 - height) / 2, this);
                }
                else {
                    graphics.drawImage(this.k, -2, -2, 1, 1, this);
                }
            }
            if (this.n != null) {
                final FontMetrics fontMetrics = graphics.getFontMetrics();
                final int n4 = (n - fontMetrics.stringWidth(this.n)) / 2 - 1;
                final int n5 = n2 / 2 + (fontMetrics.getAscent() - fontMetrics.getHeight() / 2);
                fontMetrics.getAscent();
                if (this.m) {
                    graphics.setColor(new Color((!this.j || !this.h) ? this.c : 16777215));
                }
                else {
                    graphics.setColor(new Color((this.getForeground().getRed() + this.getBackground().getRed()) / 2, (this.getForeground().getGreen() + this.getBackground().getGreen()) / 2, (this.getForeground().getBlue() + this.getBackground().getBlue()) / 2));
                }
                graphics.drawString(this.n, n4, n5 + 1);
            }
        }
    }
    
    protected void a(final Graphics graphics) {
        if (graphics != null && this.isShowing()) {
            al.n = this.q;
            al.o = this.r;
            al.n = this.o;
            if (this.l) {
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
            graphics.setColor(this.m ? Color.black : Color.gray);
            graphics.drawLine(3, 0, n - 2, 0);
            graphics.drawLine(n - 1, 1, n, 2);
            graphics.drawLine(n, 3, n, n2 - 2);
            graphics.drawLine(n - 1, n2 - 1, n - 2, n2);
            graphics.drawLine(n - 3, n2, 2, n2);
            graphics.drawLine(1, n2 - 1, 0, n2 - 2);
            graphics.drawLine(0, n2 - 3, 0, 2);
            graphics.drawLine(1, 1, 2, 0);
            if (this.j && this.h) {
                graphics.setColor(this.m ? darker2 : background);
                graphics.drawLine(1, n2 - 2, 1, 2);
                graphics.drawLine(1, 2, 2, 1);
                graphics.drawLine(2, 1, n - 2, 1);
                if (this.m) {
                    graphics.setColor(darker);
                }
                graphics.drawLine(2, n2 - 2, 2, 2);
                graphics.drawLine(2, 2, n - 3, 2);
                if (this.m) {
                    graphics.setColor(brighter);
                }
                graphics.drawLine(n - 1, 2, n - 1, n2 - 2);
                graphics.drawLine(n - 2, n2 - 1, 2, n2 - 1);
                if (this.m) {
                    graphics.setColor(background);
                }
                graphics.drawLine(n - 2, 3, n - 2, n2 - 2);
                graphics.drawLine(n - 2, n2 - 2, 2, n2 - 2);
            }
            else {
                graphics.setColor(background);
                graphics.drawLine(1, n2 - 2, 1, 2);
                graphics.drawLine(2, 1, n - 2, 1);
                if (this.m) {
                    graphics.setColor(brighter);
                }
                graphics.drawLine(2, n2 - 3, 2, 2);
                graphics.drawLine(3, 2, n - 2, 2);
                if (this.m) {
                    graphics.setColor(darker2);
                }
                graphics.drawLine(2, n2 - 1, n - 2, n2 - 1);
                graphics.drawLine(n - 1, n2 - 2, n - 1, 2);
                if (this.m) {
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
        if (!(parent instanceof P)) {
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
            if (this.k != null && !this.l) {
                final int width = this.k.getWidth(this);
                final int height = this.k.getHeight(this);
                if (width > 0 && height > 0) {
                    graphics.drawImage(this.k, (n - width) / 2, (n2 - height) / 2, this);
                }
                else {
                    graphics.drawImage(this.k, -2, -2, 1, 1, this);
                }
            }
            if (this.n != null && !this.l) {
                final FontMetrics fontMetrics = graphics.getFontMetrics();
                final int stringWidth = fontMetrics.stringWidth(this.n);
                final int height2 = fontMetrics.getHeight();
                final int ascent = fontMetrics.getAscent();
                if (this.m) {
                    graphics.setColor(foreground);
                }
                else {
                    graphics.setColor(new Color((foreground.getRed() + background.getRed()) / 2, (foreground.getGreen() + background.getGreen()) / 2, (foreground.getBlue() + background.getBlue()) / 2));
                }
                graphics.drawString(this.n, (n - stringWidth) / 2 - 1, n2 / 2 + (ascent - height2 / 2));
            }
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (this.m) {
            final Graphics graphics = this.getGraphics();
            boolean inside = this.inside(event.x, event.y);
            switch (event.id) {
                case 501: {
                    this.j = inside;
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
                    this.j = inside;
                    this.h = false;
                    if (graphics != null) {
                        this.a(graphics);
                    }
                    if (this.j) {
                        this.postEvent(new Event(this, event.when, 1001, event.x, event.y, event.key, event.modifiers, this.n));
                        break;
                    }
                    break;
                }
                case 505: {
                    inside = false;
                }
                case 504:
                case 506: {
                    if (this.j != inside) {
                        this.j = inside;
                        if (graphics != null) {
                            this.a(graphics);
                        }
                        this.j = inside;
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
    
    public void f() {
        this.resize(this.getFontMetrics(this.getFont()).stringWidth(this.n) + 20, this.j);
    }
    
    public boolean isShowing() {
        return (F.b && F.f == 1) || super.isShowing();
    }
    
    public al() {
        this(null, 22, 22);
    }
    
    public al(final int n, final int n2) {
        this(null, n, n2);
    }
    
    public al(final String s, final int n, final int n2) {
        this(s, n, n2, false, null, null);
    }
    
    public al(final String s, final int n, int l, final boolean b, final String s2, final String s3) {
        this.c = 16777215;
        this.d = 20;
        this.n = null;
        this.o = null;
        this.d = null;
        this.h = false;
        this.j = false;
        this.m = true;
        this.k = null;
        if (b) {
            if (s2 != null) {
                try {
                    al.n = Integer.parseInt(s2, 16);
                    al.n = true;
                }
                catch (Exception ex) {}
            }
            else {
                al.n = Integer.parseInt("DDDDDD", 16);
            }
            if (s3 != null) {
                try {
                    al.o = Integer.parseInt(s3, 16);
                    al.n = true;
                }
                catch (Exception ex2) {}
            }
            else {
                al.o = 0;
            }
        }
        this.q = al.n;
        this.r = al.o;
        this.o = al.n;
        this.l = al.n;
        if (this.l) {
            this.c = new Color(al.o).getRGB();
            final int rgb = new Color(al.n).getRGB();
            this.b = new int[this.d * 2 + 1];
            this.b = this.a(rgb);
        }
        this.l = this.getFontMetrics(aK.e).getHeight() + 7;
        this.a(s);
        this.setFont(aK.f);
        this.setBackground(aH.b);
        this.setForeground(Color.black);
        if (l < this.l) {
            l = this.l;
        }
        this.resize(n, l);
    }
    
    public al(final String s) {
        this(s, 22, 22);
        this.resize(this.getFontMetrics(aK.e).stringWidth(s) + 20, this.l);
    }
    
    int[] a(final int n) {
        final int[] array = new int[this.d * 2 + 1];
        final int n2 = (n & 0xFF0000) >> 16;
        final int n3 = (n & 0xFF00) >> 8;
        final int n4 = n & 0xFF;
        final int n5 = n2 / 3 / this.d;
        final int n6 = n3 / 3 / this.d;
        final int n7 = n4 / 3 / this.d;
        final int n8 = (255 - n2) / 3 * 2 / this.d;
        final int n9 = (255 - n3) / 3 * 2 / this.d;
        final int n10 = (255 - n4) / 3 * 2 / this.d;
        for (int i = 0; i < this.d + 1; ++i) {
            array[this.d - i] = (this.a(n2 - n5 * i) << 16) + (this.a(n3 - n6 * i) << 8) + this.a(n4 - n7 * i);
        }
        for (int j = 0; j < this.d + 1; ++j) {
            array[this.d + j] = (this.a(n2 + n8 * j) << 16) + (this.a(n3 + n9 * j) << 8) + this.a(n4 + n10 * j);
        }
        return array;
    }
    
    int a(final int n) {
        if (n > 255) {
            return 255;
        }
        if (n < 0) {
            return 0;
        }
        return n;
    }
    
    static {
        al.n = Integer.parseInt("DDDDDD", 16);
        al.o = 0;
        al.n = false;
    }
}
