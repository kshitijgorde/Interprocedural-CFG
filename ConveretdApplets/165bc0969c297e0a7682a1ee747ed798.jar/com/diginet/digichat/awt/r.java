// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import com.diginet.digichat.util.dx;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Image;
import com.diginet.digichat.util.s;
import java.awt.Canvas;

public class r extends Canvas implements s
{
    private String a;
    private String b;
    private String c;
    protected boolean d;
    protected boolean e;
    protected boolean f;
    private boolean fGrad;
    private Image g;
    private int h;
    private int i;
    private int j;
    private int[] iaq;
    
    public void resize(final int i, int j, final boolean b) {
        if (b && j < this.j) {
            j = this.j;
        }
        super.resize(i, j);
        this.h = j;
        this.i = i;
    }
    
    public void resize(final int n, final int n2) {
        this.resize(n, n2, true);
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x30) == 0x0) {
            return true;
        }
        this.repaint();
        return (n & 0x20) == 0x0 && CommonStyle.fAnim;
    }
    
    public void a(final String b, final String c) {
        this.b = b;
        this.c = c;
    }
    
    public String a(final Object o) {
        if (this.f) {
            return this.b;
        }
        return this.c;
    }
    
    public boolean a() {
        return this.f;
    }
    
    public void b() {
        if (!this.f) {
            this.f = true;
            this.repaint();
            final Container parent = this.getParent();
            if (parent instanceof u) {
                ((u)parent).a(true, true);
            }
        }
    }
    
    public void c() {
        if (this.f) {
            this.f = false;
            this.repaint();
            final Container parent = this.getParent();
            if (parent instanceof u) {
                ((u)parent).a(true, false);
            }
        }
    }
    
    public void a(final Image g) {
        this.g = g;
        this.repaint();
    }
    
    public void a(final String a) {
        if (this.a == null || (a != null && !a.equals(this.a))) {
            this.a = a;
            this.repaint();
        }
    }
    
    public String d() {
        return this.a;
    }
    
    public Dimension minimumSize() {
        return new Dimension(this.i, this.h);
    }
    
    public Dimension preferredSize() {
        return this.minimumSize();
    }
    
    public void e() {
        if (this.f) {
            final Graphics graphics = this.getGraphics();
            if (graphics != null) {
                this.d = true;
                this.e = true;
                this.a(graphics);
                this.d = false;
                this.e = false;
                this.repaint(150L);
                graphics.dispose();
            }
            this.postEvent(new Event(this, 1001, this.a));
        }
    }
    
    private void q(final Graphics graphics, int i, final int n, final int n2, final int[] array, final int[] array2, final int n3) {
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
            final Dimension size = this.size();
            final int width = size.width;
            final int height = size.height;
            final int n = width - 1;
            final int n2 = height - 1;
            if (this.f && this.fGrad) {
                if (this.e && this.d) {
                    graphics.setColor(new Color(this.iaq[18]));
                    graphics.fillRect(1, 2, n - 3, n2 - 2);
                    this.q(graphics, 1, 1, n - 3, new int[] { 4, 9, 13 }, this.iaq, 4);
                    this.q(graphics, 1, n2 - 2, n - 4, new int[] { 11, 6 }, this.iaq, 4);
                    graphics.setColor(new Color(this.iaq[10]));
                    graphics.drawLine(0, 2, 0, n2 - 2);
                    graphics.setColor(new Color(this.iaq[15]));
                    graphics.drawLine(n - 2, 3, n - 2, n2 - 2);
                    graphics.setColor(new Color(this.iaq[8]));
                    graphics.drawLine(n - 1, 2, n - 1, n2 - 3);
                }
                else {
                    graphics.setColor(new Color(this.iaq[17]));
                    graphics.fillRect(1, 2, n - 2, n2 - 3);
                    this.q(graphics, 1, 0, n - 3, new int[] { 10, 37, 39, 36, 34, 32, 30, 28, 26, 23, 21, 19, 18 }, this.iaq, 0);
                    graphics.setColor(new Color(this.iaq[10]));
                    graphics.drawLine(0, 1, 0, n2 - 2);
                    graphics.setColor(new Color(this.iaq[32]));
                    graphics.drawLine(1, 1, 1, n2 - 3);
                    graphics.setColor(new Color(this.iaq[15]));
                    graphics.drawLine(1, 1, 1, 1);
                    graphics.setColor(new Color(this.iaq[15]));
                    graphics.drawLine(n - 2, 1, n - 2, 1);
                    graphics.setColor(new Color(this.iaq[32]));
                    graphics.drawLine(n - 2, 2, n - 2, n2 - 3);
                    graphics.setColor(new Color(this.iaq[4]));
                    graphics.drawLine(n - 1, 1, n - 1, n2 - 2);
                    graphics.setColor(new Color(this.iaq[2]));
                    graphics.drawLine(1, n2 - 1, n - 2, n2 - 1);
                }
            }
            else {
                final Color background = this.getBackground();
                final Color brighter = background.brighter();
                final Color darker = background.darker();
                final Color darker2 = darker.darker();
                graphics.setColor(this.f ? Color.black : Color.gray);
                graphics.drawLine(3, 0, n - 2, 0);
                graphics.drawLine(n - 1, 1, n, 2);
                graphics.drawLine(n, 3, n, n2 - 2);
                graphics.drawLine(n - 1, n2 - 1, n - 2, n2);
                graphics.drawLine(n - 3, n2, 2, n2);
                graphics.drawLine(1, n2 - 1, 0, n2 - 2);
                graphics.drawLine(0, n2 - 3, 0, 2);
                graphics.drawLine(1, 1, 2, 0);
                if (this.e && this.d) {
                    graphics.setColor(this.f ? darker2 : background);
                    graphics.drawLine(1, n2 - 2, 1, 2);
                    graphics.drawLine(1, 2, 2, 1);
                    graphics.drawLine(2, 1, n - 2, 1);
                    if (this.f) {
                        graphics.setColor(darker);
                    }
                    graphics.drawLine(2, n2 - 2, 2, 2);
                    graphics.drawLine(2, 2, n - 3, 2);
                    if (this.f) {
                        graphics.setColor(brighter);
                    }
                    graphics.drawLine(n - 1, 2, n - 1, n2 - 2);
                    graphics.drawLine(n - 2, n2 - 1, 2, n2 - 1);
                    if (this.f) {
                        graphics.setColor(background);
                    }
                    graphics.drawLine(n - 2, 3, n - 2, n2 - 2);
                    graphics.drawLine(n - 2, n2 - 2, 2, n2 - 2);
                }
                else {
                    graphics.setColor(background);
                    graphics.drawLine(1, n2 - 2, 1, 2);
                    graphics.drawLine(2, 1, n - 2, 1);
                    if (this.f) {
                        graphics.setColor(brighter);
                    }
                    graphics.drawLine(2, n2 - 3, 2, 2);
                    graphics.drawLine(3, 2, n - 2, 2);
                    if (this.f) {
                        graphics.setColor(darker2);
                    }
                    graphics.drawLine(2, n2 - 1, n - 2, n2 - 1);
                    graphics.drawLine(n - 1, n2 - 2, n - 1, 2);
                    if (this.f) {
                        graphics.setColor(darker);
                    }
                    graphics.drawLine(2, n2 - 2, n - 2, n2 - 2);
                    graphics.drawLine(n - 2, n2 - 3, n - 2, 3);
                }
            }
            if (this.g != null) {
                final int width2 = this.g.getWidth(this);
                final int height2 = this.g.getHeight(this);
                if (width2 > 0 && height2 > 0) {
                    graphics.drawImage(this.g, (n - width2) / 2, (n2 - height2) / 2, this);
                }
                else {
                    graphics.drawImage(this.g, -2, -2, 1, 1, this);
                }
            }
            if (this.a != null) {
                final Color background2 = this.getBackground();
                final Color foreground = this.getForeground();
                final FontMetrics fontMetrics = graphics.getFontMetrics();
                final int stringWidth = fontMetrics.stringWidth(this.a);
                final int height3 = fontMetrics.getHeight();
                final int ascent = fontMetrics.getAscent();
                if (this.f) {
                    graphics.setColor(foreground);
                }
                else {
                    graphics.setColor(new Color((foreground.getRed() + background2.getRed()) / 2, (foreground.getGreen() + background2.getGreen()) / 2, (foreground.getBlue() + background2.getBlue()) / 2));
                }
                graphics.drawString(this.a, (n - stringWidth) / 2 - 1, n2 / 2 + (ascent - height3 / 2));
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void b(final Graphics graphics) {
        final Dimension size = this.size();
        final int n = size.width - 1;
        final int n2 = size.height - 1;
        final Container parent = this.getParent();
        if (!(parent instanceof u)) {
            graphics.setColor(parent.getBackground());
            graphics.drawRect(0, 0, n, n2);
            graphics.drawRect(1, 1, n - 2, n2 - 2);
        }
        graphics.setColor(this.getBackground());
        graphics.fillRect(2, 2, n - 4, n2 - 4);
    }
    
    public void paint(final Graphics graphics) {
        if (this.isShowing()) {
            this.b(graphics);
            this.a(graphics);
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (this.f) {
            final Graphics graphics = this.getGraphics();
            boolean inside = this.inside(event.x, event.y);
            switch (event.id) {
                case 501: {
                    this.e = inside;
                    this.d = true;
                    if (graphics != null) {
                        this.a(graphics);
                    }
                    break;
                }
                case 502: {
                    if (this.d) {
                        this.e = inside;
                        this.d = false;
                        if (graphics != null) {
                            this.a(graphics);
                        }
                        if (this.e) {
                            this.postEvent(new Event(this, event.when, 1001, event.x, event.y, event.key, event.modifiers, this.a));
                        }
                    }
                    break;
                }
                case 505: {
                    inside = false;
                }
                case 504:
                case 506: {
                    if (this.e != inside) {
                        this.e = inside;
                        if (graphics != null) {
                            this.a(graphics);
                        }
                        this.e = inside;
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
        this.resize(this.getFontMetrics(this.getFont()).stringWidth(this.a) + 20, this.h);
    }
    
    public boolean isShowing() {
        return (dx.e && dx.b == 1) || super.isShowing();
    }
    
    public r() {
        this(null, 22, 22);
    }
    
    public r(final int n, final int n2) {
        this(null, n, n2);
    }
    
    private static int qi(final int n) {
        if (n > 255) {
            return 255;
        }
        if (n < 0) {
            return 0;
        }
        return n;
    }
    
    private int[] q(int n, final int n2) {
        final int[] array = new int[(n2 << 1) + 1];
        final int n3 = n >> 16 & 0xFF;
        final int n4 = n >> 8 & 0xFF;
        n &= 0xFF;
        final int n5 = n3 / 3 / n2;
        final int n6 = n4 / 3 / n2;
        final int n7 = n / 3 / n2;
        final int n8 = ((255 - n3) / 3 << 1) / n2;
        final int n9 = ((255 - n4) / 3 << 1) / n2;
        final int n10 = ((255 - n) / 3 << 1) / n2;
        for (int i = 0; i < n2 + 1; ++i) {
            array[n2 - i] = (qi(n3 - n5 * i) << 16) + (qi(n4 - n6 * i) << 8) + qi(n - n7 * i);
        }
        for (int j = 0; j < n2 + 1; ++j) {
            array[n2 + j] = (qi(n3 + n8 * j) << 16) + (qi(n4 + n9 * j) << 8) + qi(n + n10 * j);
        }
        return array;
    }
    
    public r(final String s, final int n, int j) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = false;
        this.e = false;
        this.f = true;
        this.g = null;
        final boolean fGrad = CommonStyle.fGrad;
        this.fGrad = fGrad;
        if (fGrad) {
            this.iaq = this.q(CommonStyle.clrCtrlBack.getRGB(), 20);
        }
        this.j = this.getFontMetrics(dw.b).getHeight() + 7;
        this.a(s);
        this.setFont(dw.b);
        this.setBackground(CommonStyle.clrCtrlBack);
        this.setForeground(CommonStyle.clrCtrlText);
        if (j < this.j) {
            j = this.j;
        }
        this.resize(n, j);
    }
    
    public r(final String s) {
        this(s, 22, 22);
        this.resize(this.getFontMetrics(dw.b).stringWidth(s) + 20, this.j);
    }
}
