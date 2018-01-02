// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import com.diginet.digichat.util.ch;
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
    private Image g;
    private int h;
    private int i;
    private int j;
    
    public void resize(final int i, int j) {
        if (j < this.j) {
            j = this.j;
        }
        super.resize(i, j);
        this.h = j;
        this.i = i;
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.repaint();
            return false;
        }
        return true;
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
    
    protected void a(final Graphics graphics) {
        if (graphics != null && this.isShowing()) {
            final Dimension size = this.size();
            final int width = size.width;
            final int height = size.height;
            final int n = width - 1;
            final int n2 = height - 1;
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
            final Dimension size = this.size();
            final int n = size.width - 1;
            final int n2 = size.height - 1;
            final Color background = this.getBackground();
            final Color foreground = this.getForeground();
            this.b(graphics);
            this.a(graphics);
            if (this.g != null) {
                final int width = this.g.getWidth(this);
                final int height = this.g.getHeight(this);
                if (width > 0 && height > 0) {
                    graphics.drawImage(this.g, (n - width) / 2, (n2 - height) / 2, this);
                }
                else {
                    graphics.drawImage(this.g, -2, -2, 1, 1, this);
                }
            }
            if (this.a != null) {
                final FontMetrics fontMetrics = graphics.getFontMetrics();
                final int stringWidth = fontMetrics.stringWidth(this.a);
                final int height2 = fontMetrics.getHeight();
                final int ascent = fontMetrics.getAscent();
                if (this.f) {
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
        return (ch.e && ch.b == 1) || super.isShowing();
    }
    
    public r() {
        this(null, 22, 22);
    }
    
    public r(final int n, final int n2) {
        this(null, n, n2);
    }
    
    public r(final String s, final int n, int j) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = false;
        this.e = false;
        this.f = true;
        this.g = null;
        this.j = this.getFontMetrics(p.b).getHeight() + 7;
        this.a(s);
        this.setFont(p.b);
        this.setBackground(b6.b);
        this.setForeground(Color.black);
        if (j < this.j) {
            j = this.j;
        }
        this.resize(n, j);
    }
    
    public r(final String s) {
        this(s, 22, 22);
        this.resize(this.getFontMetrics(p.b).stringWidth(s) + 20, this.j);
    }
}
