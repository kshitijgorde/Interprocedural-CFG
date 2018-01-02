// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Event;
import java.awt.image.ImageObserver;
import com.diginet.digichat.common.bp;
import java.awt.Color;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Canvas;

public class cr extends Canvas
{
    private final cq a;
    
    void a(final int n) {
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            this.a(graphics, n);
            graphics.dispose();
        }
    }
    
    Rectangle b(final int n) {
        final Dimension size = this.size();
        final Dimension d = this.a.d();
        final int width = d.width;
        return new Rectangle((size.width + 6 - width * 43) / 2 + n % width * 43, (size.height + 6 - d.height * 41) / 2 + n / width * 41, 37, 35);
    }
    
    void c(final int n) {
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            final Rectangle b = this.b(n);
            int x = b.x;
            int y = b.y;
            int height = b.height;
            int width = b.width;
            graphics.setColor(this.getBackground());
            for (int i = 0; i <= 4; ++i) {
                graphics.drawRect(x, y, width, height);
                ++x;
                ++y;
                height -= 2;
                width -= 2;
            }
            graphics.dispose();
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x30) == 0x0) {
            return true;
        }
        this.repaint();
        return (n & 0x20) == 0x0 && CommonStyle.fAnim;
    }
    
    void d(final int n) {
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            final Rectangle b = this.b(n);
            final int x = b.x;
            final int y = b.y;
            final int n2 = x + b.width;
            final int n3 = y + b.height;
            final Color color = new Color(115, 115, 115);
            graphics.setColor(Color.black);
            graphics.drawLine(x, n3 - 2, x, y + 2);
            graphics.drawLine(x, y + 2, x + 2, y);
            graphics.drawLine(x + 2, y, n2 - 2, y);
            graphics.drawLine(n2 - 2, y, n2, y + 2);
            graphics.drawLine(n2, y + 2, n2, n3 - 2);
            graphics.drawLine(n2, n3 - 2, n2 - 2, n3);
            graphics.drawLine(n2 - 2, n3, x + 2, n3);
            graphics.drawLine(x + 2, n3, x, n3 - 2);
            graphics.drawLine(x + 5, y + 4, n2 - 5, y + 4);
            graphics.drawLine(n2 - 4, y + 5, n2 - 4, n3 - 5);
            graphics.drawLine(n2 - 5, n3 - 4, x + 5, n3 - 4);
            graphics.drawLine(x + 4, n3 - 5, x + 4, y + 5);
            graphics.setColor(Color.white);
            graphics.drawLine(x + 1, n3 - 2, x + 1, y + 2);
            graphics.drawLine(x + 2, y + 1, n2 - 2, y + 1);
            graphics.drawLine(n2 - 2, y + 5, n2 - 2, n3 - 5);
            graphics.drawLine(n2 - 2, n3 - 5, n2 - 4, n3 - 3);
            graphics.drawLine(n2 - 4, n3 - 3, x + 5, n3 - 3);
            graphics.setColor(color);
            graphics.drawLine(n2 - 1, y + 2, n2 - 1, n3 - 2);
            graphics.drawLine(n2 - 2, n3 - 1, x + 2, n3 - 1);
            graphics.drawLine(x + 4, n3 - 4, x + 3, n3 - 5);
            graphics.drawLine(x + 3, n3 - 5, x + 3, y + 5);
            graphics.drawLine(x + 3, y + 5, x + 5, y + 3);
            graphics.drawLine(x + 5, y + 3, n2 - 5, y + 3);
            graphics.drawLine(n2 - 5, y + 3, n2 - 4, y + 4);
            graphics.dispose();
        }
    }
    
    void a(final Graphics graphics, final int n) {
        final Image a = ((bp)cq.a(this.a).d(n)).a;
        final Rectangle b = this.b(n - this.a.c);
        final int n2 = 24;
        final int n3 = 24;
        final int n4 = 1 + b.x + (b.width - n2) / 2;
        final int n5 = 1 + b.y + (b.height - n3) / 2;
        if (a != null) {
            graphics.drawImage(a, n4, n5, this);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.isShowing()) {
            final int b = cq.a(this.a).b();
            int e = this.a.e();
            if (b <= e) {
                this.a.c = 0;
                e = b;
                this.a.e.c();
                this.a.f.c();
            }
            else {
                this.a.e.b();
                this.a.f.b();
            }
            int n = this.a.c + e;
            if (n > b) {
                n = b;
            }
            if (this.a.b >= this.a.c && this.a.b < n) {
                this.d(this.a.b - this.a.c);
            }
            for (int i = this.a.c; i < n; ++i) {
                this.a(i);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public boolean handleEvent(final Event event) {
        switch (event.id) {
            case 501:
            case 506: {
                final Dimension size = this.size();
                final Dimension d = this.a.d();
                final int b = cq.a(this.a).b();
                final int width = d.width;
                final int height = d.height;
                final int n = (size.width + 6 - width * 43) / 2;
                int n2 = (event.y - (size.height + 6 - height * 41) / 2) / 41;
                int n3 = (event.x - n) / 43;
                if (n3 >= width) {
                    n3 = width - 1;
                }
                else if (n3 < 0) {
                    n3 = 0;
                }
                if (n2 >= height) {
                    n2 = height - 1;
                }
                else if (n2 < 0) {
                    n2 = 0;
                }
                final int n4 = n2 * width + n3 + this.a.c;
                if (n4 < b && n4 != this.a.b) {
                    this.a.b(n4);
                }
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public Dimension minimumSize() {
        return this.a(1, 1);
    }
    
    public Dimension a(final int n, final int n2) {
        return new Dimension(37 * n, 35 * n2);
    }
    
    public cr(final cq a) {
        this.a = a;
        this.resize(50, 50);
    }
}
