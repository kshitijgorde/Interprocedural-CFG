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

public class aS extends Canvas implements aB
{
    private String a;
    private String R;
    private String d;
    protected boolean h;
    protected boolean d;
    protected boolean e;
    private Image h;
    private int ap;
    private int au;
    private int o;
    int[] d;
    int av;
    private boolean ak;
    int p;
    private static int aw;
    private static int ax;
    private static boolean al;
    private int ay;
    private int az;
    private boolean am;
    
    public void resize(final int au, int o) {
        if (o < this.o) {
            o = this.o;
        }
        super.resize(au, o);
        this.ap = o;
        this.au = au;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.repaint();
            return false;
        }
        return true;
    }
    
    public void a(final String r, final String d) {
        this.R = r;
        this.d = d;
    }
    
    public String a(final Object o) {
        if (this.e) {
            return this.R;
        }
        return this.d;
    }
    
    public boolean a() {
        return this.e;
    }
    
    public void c() {
        if (!this.e) {
            this.e = true;
            this.repaint();
            final Container parent = this.getParent();
            if (parent instanceof aQ) {
                ((aQ)parent).a(true, true);
            }
        }
    }
    
    public void d() {
        if (this.e) {
            this.e = false;
            this.repaint();
            final Container parent = this.getParent();
            if (parent instanceof aQ) {
                ((aQ)parent).a(true, false);
            }
        }
    }
    
    public void a(final Image h) {
        this.h = h;
        this.repaint();
    }
    
    public void a(final String a) {
        if (this.a == null || (a != null && !a.equals(this.a))) {
            this.a = a;
            this.repaint();
        }
    }
    
    public String c() {
        return this.a;
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.au, this.ap);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public void s() {
        if (this.e) {
            final Graphics graphics = this.getGraphics();
            if (graphics != null) {
                this.h = true;
                this.d = true;
                this.b(graphics);
                this.h = false;
                this.d = false;
                this.repaint(150L);
                graphics.dispose();
            }
            this.postEvent(new Event(this, 1001, this.a));
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
    
    private void a(final Graphics graphics) {
        if (graphics != null && this.isShowing()) {
            final Dimension size = this.size();
            final int n = size.width - 1;
            final int n2 = size.height - 1;
            if (this.e) {
                if (this.d && this.h) {
                    final int n3 = 4;
                    graphics.setColor(new Color(this.d[14 + n3]));
                    graphics.fillRect(1, 2, n - 3, n2 - 2);
                    this.a(graphics, 1, 1, n - 3, new int[] { 4, 9, 13 }, this.d, n3);
                    this.a(graphics, 1, 0 + n2 - 2, n - 4, new int[] { 11, 6 }, this.d, n3);
                    graphics.setColor(new Color(this.d[6 + n3]));
                    graphics.drawLine(0, 2, 0, 0 + n2 - 2);
                    graphics.setColor(new Color(this.d[11 + n3]));
                    graphics.drawLine(0 + n - 2, 3, 0 + n - 2, 0 + n2 - 2);
                    graphics.setColor(new Color(this.d[4 + n3]));
                    graphics.drawLine(0 + n - 1, 2, 0 + n - 1, 0 + n2 - 3);
                }
                else {
                    graphics.setColor(new Color(this.d[17]));
                    graphics.fillRect(1, 2, n - 2, n2 - 3);
                    this.a(graphics, 1, 0, n - 3, new int[] { 10, 37, 39, 36, 34, 32, 30, 28, 26, 23, 21, 19, 18 }, this.d, 0);
                    graphics.setColor(new Color(this.d[10]));
                    graphics.drawLine(0, 1, 0, 0 + n2 - 2);
                    graphics.setColor(new Color(this.d[32]));
                    graphics.drawLine(1, 1, 1, 0 + n2 - 3);
                    graphics.setColor(new Color(this.d[15]));
                    graphics.drawLine(1, 1, 1, 1);
                    graphics.setColor(new Color(this.d[15]));
                    graphics.drawLine(0 + n - 2, 1, 0 + n - 2, 1);
                    graphics.setColor(new Color(this.d[32]));
                    graphics.drawLine(0 + n - 2, 2, 0 + n - 2, 0 + n2 - 3);
                    graphics.setColor(new Color(this.d[4]));
                    graphics.drawLine(0 + n - 1, 1, 0 + n - 1, 0 + n2 - 2);
                    graphics.setColor(new Color(this.d[2]));
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
            if (this.h != null) {
                final int width = this.h.getWidth(this);
                final int height = this.h.getHeight(this);
                if (width > 0 && height > 0) {
                    graphics.drawImage(this.h, (n - width) / 2, (n2 - height) / 2, this);
                }
                else {
                    graphics.drawImage(this.h, -2, -2, 1, 1, this);
                }
            }
            if (this.a != null) {
                final FontMetrics fontMetrics = graphics.getFontMetrics();
                final int n4 = (n - fontMetrics.stringWidth(this.a)) / 2 - 1;
                final int n5 = n2 / 2 + (fontMetrics.getAscent() - fontMetrics.getHeight() / 2);
                fontMetrics.getAscent();
                if (this.e) {
                    graphics.setColor(new Color((!this.d || !this.h) ? this.av : 16777215));
                }
                else {
                    graphics.setColor(new Color((this.getForeground().getRed() + this.getBackground().getRed()) / 2, (this.getForeground().getGreen() + this.getBackground().getGreen()) / 2, (this.getForeground().getBlue() + this.getBackground().getBlue()) / 2));
                }
                graphics.drawString(this.a, n4, n5 + 1);
            }
        }
    }
    
    protected void b(final Graphics graphics) {
        if (graphics != null && this.isShowing()) {
            aS.aw = this.ay;
            aS.ax = this.az;
            aS.al = this.am;
            if (this.ak) {
                this.a(graphics);
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
        if (!(parent instanceof aQ)) {
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
            this.b(graphics);
            if (this.h != null && !this.ak) {
                final int width = this.h.getWidth(this);
                final int height = this.h.getHeight(this);
                if (width > 0 && height > 0) {
                    graphics.drawImage(this.h, (n - width) / 2, (n2 - height) / 2, this);
                }
                else {
                    graphics.drawImage(this.h, -2, -2, 1, 1, this);
                }
            }
            if (this.a != null && !this.ak) {
                final FontMetrics fontMetrics = graphics.getFontMetrics();
                final int stringWidth = fontMetrics.stringWidth(this.a);
                final int height2 = fontMetrics.getHeight();
                final int ascent = fontMetrics.getAscent();
                if (this.e) {
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
        if (this.e) {
            final Graphics graphics = this.getGraphics();
            boolean inside = this.inside(event.x, event.y);
            switch (event.id) {
                case 501: {
                    this.d = inside;
                    this.h = true;
                    if (graphics != null) {
                        this.b(graphics);
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
                        this.b(graphics);
                    }
                    if (this.d) {
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
                    if (this.d != inside) {
                        this.d = inside;
                        if (graphics != null) {
                            this.b(graphics);
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
    
    public void t() {
        this.resize(this.getFontMetrics(this.getFont()).stringWidth(this.a) + 20, this.ap);
    }
    
    public boolean isShowing() {
        return (f.d && f.h == 1) || super.isShowing();
    }
    
    public aS() {
        this(null, 22, 22);
    }
    
    public aS(final int n, final int n2) {
        this(null, n, n2);
    }
    
    public aS(final String s, final int n, final int n2) {
        this(s, n, n2, false, null, null);
    }
    
    public aS(final String s, final int n, int o, final boolean b, final String s2, final String s3) {
        this.av = 16777215;
        this.p = 20;
        this.a = null;
        this.R = null;
        this.d = null;
        this.h = false;
        this.d = false;
        this.e = true;
        this.h = null;
        if (b) {
            if (s2 != null) {
                try {
                    aS.aw = Integer.parseInt(s2, 16);
                    aS.al = true;
                }
                catch (Exception ex) {}
            }
            else {
                aS.aw = Integer.parseInt("DDDDDD", 16);
            }
            if (s3 != null) {
                try {
                    aS.ax = Integer.parseInt(s3, 16);
                    aS.al = true;
                }
                catch (Exception ex2) {}
            }
            else {
                aS.ax = 0;
            }
        }
        this.ay = aS.aw;
        this.az = aS.ax;
        this.am = aS.al;
        this.ak = aS.al;
        if (this.ak) {
            this.av = new Color(aS.ax).getRGB();
            final int rgb = new Color(aS.aw).getRGB();
            this.d = new int[this.p * 2 + 1];
            this.d = this.a(rgb);
        }
        this.o = this.getFontMetrics(bL.e).getHeight() + 7;
        this.a(s);
        this.setFont(bL.a);
        this.setBackground(bR.h);
        this.setForeground(Color.black);
        if (o < this.o) {
            o = this.o;
        }
        this.resize(n, o);
    }
    
    public aS(final String s) {
        this(s, 22, 22);
        this.resize(this.getFontMetrics(bL.e).stringWidth(s) + 20, this.o);
    }
    
    int[] a(final int n) {
        final int[] array = new int[this.p * 2 + 1];
        final int n2 = (n & 0xFF0000) >> 16;
        final int n3 = (n & 0xFF00) >> 8;
        final int n4 = n & 0xFF;
        final int n5 = n2 / 3 / this.p;
        final int n6 = n3 / 3 / this.p;
        final int n7 = n4 / 3 / this.p;
        final int n8 = (255 - n2) / 3 * 2 / this.p;
        final int n9 = (255 - n3) / 3 * 2 / this.p;
        final int n10 = (255 - n4) / 3 * 2 / this.p;
        for (int i = 0; i < this.p + 1; ++i) {
            array[this.p - i] = (this.g(n2 - n5 * i) << 16) + (this.g(n3 - n6 * i) << 8) + this.g(n4 - n7 * i);
        }
        for (int j = 0; j < this.p + 1; ++j) {
            array[this.p + j] = (this.g(n2 + n8 * j) << 16) + (this.g(n3 + n9 * j) << 8) + this.g(n4 + n10 * j);
        }
        return array;
    }
    
    int g(final int n) {
        if (n > 255) {
            return 255;
        }
        if (n < 0) {
            return 0;
        }
        return n;
    }
    
    static {
        aS.aw = Integer.parseInt("DDDDDD", 16);
        aS.ax = 0;
        aS.al = false;
    }
}
