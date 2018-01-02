// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Color;
import java.awt.Image;
import java.awt.Canvas;

public class e extends Canvas implements cn
{
    private String q;
    private String w;
    private String e;
    protected boolean q;
    protected boolean w;
    private boolean e;
    private Image q;
    private int q;
    private int w;
    private int e;
    private int[] q;
    private Color q;
    private boolean r;
    private int r;
    private Color w;
    
    public void resize(final int w, int e) {
        if (e < this.e) {
            e = this.e;
        }
        super.resize(w, e);
        this.q = e;
        this.w = w;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.repaint();
            return false;
        }
        return true;
    }
    
    public final void q(final String w, final String e) {
        this.w = w;
        this.e = e;
    }
    
    public final String q(final Object o) {
        if (this.e) {
            return this.w;
        }
        return this.e;
    }
    
    public final boolean q() {
        return this.e;
    }
    
    public final void q() {
        if (!this.e) {
            this.w();
        }
    }
    
    public final void w() {
        this.e = true;
        this.repaint();
        final Container parent;
        if ((parent = this.getParent()) instanceof d) {
            ((d)parent).q(true, true);
        }
    }
    
    public final void e() {
        if (this.e) {
            this.e = false;
            this.repaint();
            final Container parent;
            if ((parent = this.getParent()) instanceof d) {
                ((d)parent).q(true, false);
            }
        }
    }
    
    public final void q(final Image q) {
        this.q = q;
        this.repaint();
    }
    
    public final void q(final String q) {
        if (this.q == null || (q != null && !q.equals(this.q))) {
            this.q = q;
            this.repaint();
        }
    }
    
    public final String q() {
        return this.q;
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.w, this.q);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public final void r() {
        if (this.e) {
            final Graphics graphics;
            if ((graphics = this.getGraphics()) != null) {
                this.q = true;
                this.w = true;
                this.q(graphics);
                this.q = false;
                this.w = false;
                this.repaint(150L);
                graphics.dispose();
            }
            this.postEvent(new Event(this, 1001, this.q));
        }
    }
    
    protected void q(final Graphics graphics) {
        if (graphics != null && this.isShowing()) {
            if (this.r) {
                boolean b;
                if (graphics != null && this.isShowing() && this.q != null && this.q.length > 0) {
                    final Dimension size;
                    final int n = (size = this.size()).width - 1;
                    final int n2 = size.height - 1;
                    if (this.e) {
                        if (this.w && this.q) {
                            graphics.setColor(new Color(this.q[18]));
                            graphics.fillRect(1, 2, n - 3, n2 - 2);
                            q(graphics, 1, 1, n - 3, new int[] { 4, 9, 13 }, this.q, 4);
                            q(graphics, 1, n2 + 0 - 2, n - 4, new int[] { 11, 6 }, this.q, 4);
                            graphics.setColor(new Color(this.q[10]));
                            graphics.drawLine(0, 2, 0, n2 + 0 - 2);
                            graphics.setColor(new Color(this.q[15]));
                            graphics.drawLine(n + 0 - 2, 3, n + 0 - 2, n2 + 0 - 2);
                            graphics.setColor(new Color(this.q[8]));
                            graphics.drawLine(n + 0 - 1, 2, n + 0 - 1, n2 + 0 - 3);
                        }
                        else {
                            graphics.setColor(new Color(this.q[17]));
                            graphics.fillRect(1, 2, n - 2, n2 - 3);
                            q(graphics, 1, 0, n - 3, new int[] { 10, 37, 39, 36, 34, 32, 30, 28, 26, 23, 21, 19, 18 }, this.q, 0);
                            graphics.setColor(new Color(this.q[10]));
                            graphics.drawLine(0, 1, 0, n2 + 0 - 2);
                            graphics.setColor(new Color(this.q[32]));
                            graphics.drawLine(1, 1, 1, n2 + 0 - 3);
                            graphics.setColor(new Color(this.q[15]));
                            graphics.drawLine(1, 1, 1, 1);
                            graphics.setColor(new Color(this.q[15]));
                            graphics.drawLine(n + 0 - 2, 1, n + 0 - 2, 1);
                            graphics.setColor(new Color(this.q[32]));
                            graphics.drawLine(n + 0 - 2, 2, n + 0 - 2, n2 + 0 - 3);
                            graphics.setColor(new Color(this.q[4]));
                            graphics.drawLine(n + 0 - 1, 1, n + 0 - 1, n2 + 0 - 2);
                            graphics.setColor(new Color(this.q[2]));
                            graphics.drawLine(1, n2 + 0 - 1, n + 0 - 2, n2 + 0 - 1);
                        }
                    }
                    else {
                        final Color background = this.getBackground();
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
                    if (this.q != null) {
                        final int width = this.q.getWidth(this);
                        final int height = this.q.getHeight(this);
                        if (width > 0 && height > 0) {
                            graphics.drawImage(this.q, (n - width) / 2, (n2 - height) / 2, this);
                        }
                        else {
                            graphics.drawImage(this.q, -2, -2, 1, 1, this);
                        }
                    }
                    final Color q = this.q;
                    if (this.q != null) {
                        final Dimension size2;
                        final int n3 = (size2 = this.size()).width - 1;
                        final int n4 = size2.height - 1;
                        final FontMetrics fontMetrics = graphics.getFontMetrics();
                        final int n5 = (n3 - fontMetrics.stringWidth(this.q)) / 2 - 1;
                        final int n6 = n4 / 2 + (fontMetrics.getAscent() - fontMetrics.getHeight() / 2);
                        if (this.e) {
                            graphics.setColor(new Color((!this.w || !this.q) ? 0 : 0));
                        }
                        else {
                            graphics.setColor(new Color((this.getForeground().getRed() + this.getBackground().getRed()) / 2, (this.getForeground().getGreen() + this.getBackground().getGreen()) / 2, (this.getForeground().getBlue() + this.getBackground().getBlue()) / 2));
                        }
                        if (this.e) {
                            graphics.drawString(this.q, n5 + 1, n6 + 1);
                        }
                        if (this.e) {
                            graphics.setColor((!this.w || !this.q) ? q : Color.blue);
                        }
                        else {
                            graphics.setColor(new Color((this.getForeground().getRed() + this.getBackground().getRed()) / 2, (this.getForeground().getGreen() + this.getBackground().getGreen()) / 2, (this.getForeground().getBlue() + this.getBackground().getBlue()) / 2));
                        }
                        graphics.drawString(this.q, n5, n6 + 1);
                    }
                    b = true;
                }
                else {
                    b = false;
                }
                if (b) {
                    return;
                }
            }
            final Dimension size3;
            final int width2 = (size3 = this.size()).width;
            final int height2 = size3.height;
            final int n7 = width2 - 1;
            final int n8 = height2 - 1;
            final Color background2;
            final Color brighter = (background2 = this.getBackground()).brighter();
            final Color darker2;
            final Color darker = (darker2 = background2.darker()).darker();
            graphics.setColor(this.e ? Color.black : Color.gray);
            graphics.drawLine(3, 0, n7 - 2, 0);
            graphics.drawLine(n7 - 1, 1, n7, 2);
            graphics.drawLine(n7, 3, n7, n8 - 2);
            graphics.drawLine(n7 - 1, n8 - 1, n7 - 2, n8);
            graphics.drawLine(n7 - 3, n8, 2, n8);
            graphics.drawLine(1, n8 - 1, 0, n8 - 2);
            graphics.drawLine(0, n8 - 3, 0, 2);
            graphics.drawLine(1, 1, 2, 0);
            if (this.w && this.q) {
                graphics.setColor(this.e ? darker : background2);
                graphics.drawLine(1, n8 - 2, 1, 2);
                graphics.drawLine(1, 2, 2, 1);
                graphics.drawLine(2, 1, n7 - 2, 1);
                if (this.e) {
                    graphics.setColor(darker2);
                }
                graphics.drawLine(2, n8 - 2, 2, 2);
                graphics.drawLine(2, 2, n7 - 3, 2);
                if (this.e) {
                    graphics.setColor(brighter);
                }
                graphics.drawLine(n7 - 1, 2, n7 - 1, n8 - 2);
                graphics.drawLine(n7 - 2, n8 - 1, 2, n8 - 1);
                if (this.e) {
                    graphics.setColor(background2);
                }
                graphics.drawLine(n7 - 2, 3, n7 - 2, n8 - 2);
                graphics.drawLine(n7 - 2, n8 - 2, 2, n8 - 2);
            }
            else {
                graphics.setColor(background2);
                graphics.drawLine(1, n8 - 2, 1, 2);
                graphics.drawLine(2, 1, n7 - 2, 1);
                if (this.e) {
                    graphics.setColor(brighter);
                }
                graphics.drawLine(2, n8 - 3, 2, 2);
                graphics.drawLine(3, 2, n7 - 2, 2);
                if (this.e) {
                    graphics.setColor(darker);
                }
                graphics.drawLine(2, n8 - 1, n7 - 2, n8 - 1);
                graphics.drawLine(n7 - 1, n8 - 2, n7 - 1, 2);
                if (this.e) {
                    graphics.setColor(darker2);
                }
                graphics.drawLine(2, n8 - 2, n7 - 2, n8 - 2);
                graphics.drawLine(n7 - 2, n8 - 3, n7 - 2, 3);
            }
            this.w(graphics);
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
            this.getBackground();
            this.getForeground();
            final Dimension size2;
            final int n3 = (size2 = this.size()).width - 1;
            final int n4 = size2.height - 1;
            final Container parent;
            if (!((parent = this.getParent()) instanceof d)) {
                graphics.setColor(parent.getBackground());
                graphics.drawRect(0, 0, n3, n4);
                graphics.drawRect(1, 1, n3 - 2, n4 - 2);
            }
            graphics.setColor(this.getBackground());
            graphics.fillRect(2, 2, n3 - 4, n4 - 4);
            this.q(graphics);
            if (this.q != null) {
                final int width = this.q.getWidth(this);
                final int height = this.q.getHeight(this);
                if (width > 0 && height > 0) {
                    graphics.drawImage(this.q, (n - width) / 2, (n2 - height) / 2, this);
                }
                else {
                    graphics.drawImage(this.q, -2, -2, 1, 1, this);
                }
            }
            if (!this.r) {
                this.w(graphics);
            }
        }
    }
    
    private void w(final Graphics graphics) {
        if (this.q != null) {
            final Color background = this.getBackground();
            final Color foreground = this.getForeground();
            final Dimension size;
            final int n = (size = this.size()).width - 1;
            final int n2 = size.height - 1;
            final FontMetrics fontMetrics;
            final int stringWidth = (fontMetrics = graphics.getFontMetrics()).stringWidth(this.q);
            final int height = fontMetrics.getHeight();
            final int ascent = fontMetrics.getAscent();
            if (this.e) {
                graphics.setColor(foreground);
            }
            else {
                graphics.setColor(new Color((foreground.getRed() + background.getRed()) / 2, (foreground.getGreen() + background.getGreen()) / 2, (foreground.getBlue() + background.getBlue()) / 2));
            }
            graphics.drawString(this.q, (n - stringWidth) / 2 - 1, n2 / 2 + (ascent - height / 2));
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (this.e) {
            final Graphics graphics = this.getGraphics();
            boolean inside = this.inside(event.x, event.y);
            switch (event.id) {
                case 501: {
                    this.w = inside;
                    this.q = true;
                    if (graphics != null) {
                        this.q(graphics);
                        break;
                    }
                    break;
                }
                case 502: {
                    if (!this.q) {
                        break;
                    }
                    this.w = inside;
                    this.q = false;
                    if (graphics != null) {
                        this.q(graphics);
                    }
                    if (this.w) {
                        this.postEvent(new Event(this, event.when, 1001, event.x, event.y, event.key, event.modifiers, this.q));
                        break;
                    }
                    break;
                }
                case 505: {
                    inside = false;
                }
                case 504:
                case 506: {
                    if (this.w != inside) {
                        this.w = inside;
                        if (graphics != null) {
                            this.q(graphics);
                        }
                        this.w = inside;
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
    
    public final void t() {
        this.resize(this.getFontMetrics(this.getFont()).stringWidth(this.q) + 20, this.q);
    }
    
    public boolean isShowing() {
        return (cx.w && cx.w == 1) || super.isShowing();
    }
    
    public e() {
        this(null, 22, 22);
    }
    
    public e(final int n, final int n2) {
        this(null, n, n2);
    }
    
    public e(final String s, final int n, int e) {
        this.r = false;
        this.r = be.w.k();
        this.r = 20;
        this.q = null;
        this.w = null;
        this.e = null;
        this.q = false;
        this.w = false;
        this.e = true;
        this.q = null;
        if (this.r) {
            this.q = be.w.j;
            this.w = be.w.h;
            final int rgb = this.w.getRGB();
            this.q = new int[(this.r << 1) + 1];
            this.q = this.q(rgb);
        }
        this.e = this.getFontMetrics(k.w).getHeight() + 7;
        this.q(s);
        this.setFont(k.w);
        this.setBackground(i.w);
        this.setForeground(Color.black);
        if (e < this.e) {
            e = this.e;
        }
        this.resize(n, e);
    }
    
    public e(final String s) {
        this(s, 22, 22);
        this.resize(this.getFontMetrics(k.w).stringWidth(s) + 20, this.e);
    }
    
    private static void q(final Graphics graphics, int i, final int n, final int n2, final int[] array, final int[] array2, final int n3) {
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
    
    private int[] q(int n) {
        final int[] array = new int[(this.r << 1) + 1];
        final int n2 = n >> 16 & 0xFF;
        final int n3 = n >> 8 & 0xFF;
        n &= 0xFF;
        final int n4 = n2 / 3 / this.r;
        final int n5 = n3 / 3 / this.r;
        final int n6 = n / 3 / this.r;
        final int n7 = ((255 - n2) / 3 << 1) / this.r;
        final int n8 = ((255 - n3) / 3 << 1) / this.r;
        final int n9 = ((255 - n) / 3 << 1) / this.r;
        for (int i = 0; i < this.r + 1; ++i) {
            array[this.r - i] = (q(n2 - n4 * i) << 16) + (q(n3 - n5 * i) << 8) + q(n - n6 * i);
        }
        for (int j = 0; j < this.r + 1; ++j) {
            array[this.r + j] = (q(n2 + n7 * j) << 16) + (q(n3 + n8 * j) << 8) + q(n + n9 * j);
        }
        return array;
    }
    
    private static int q(final int n) {
        if (n > 255) {
            return 255;
        }
        if (n < 0) {
            return 0;
        }
        return n;
    }
}
