// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Canvas;

class aR extends Canvas
{
    private final aM b;
    private final aM c;
    
    void b(final int n) {
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            this.a(graphics, n);
            graphics.dispose();
        }
    }
    
    Rectangle a(final int n) {
        final Dimension size = this.size();
        final Dimension a = this.b.a();
        final int width = a.width;
        return new Rectangle((size.width + 6 - width * 43) / 2 + n % width * 43, (size.height + 6 - a.height * 41) / 2 + n / width * 41, 37, 35);
    }
    
    void e(final int n) {
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            final Rectangle a = this.a(n);
            int x = a.x;
            int y = a.y;
            int height = a.height;
            int width = a.width;
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
        if ((n & 0x20) != 0x0) {
            this.repaint();
            return false;
        }
        return true;
    }
    
    void f(final int n) {
        final Graphics graphics = this.getGraphics();
        if (graphics != null) {
            final Rectangle a = this.a(n);
            final int x = a.x;
            final int y = a.y;
            final int n2 = x + a.width;
            final int n3 = y + a.height;
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
        final Image b = ((ba)aM.a(this.b).a(n)).b;
        final Rectangle a = this.a(n - this.b.f);
        graphics.drawImage(b, 1 + a.x + (a.width - 24) / 2, 1 + a.y + (a.height - 24) / 2, this);
    }
    
    public void paint(final Graphics graphics) {
        if (this.isShowing()) {
            final int a = aM.a(this.b).a();
            int c = this.b.c();
            if (a <= c) {
                this.b.f = 0;
                c = a;
                this.b.a.g();
                this.b.b.g();
            }
            else {
                this.b.a.b();
                this.b.b.b();
            }
            int n = this.b.f + c;
            if (n > a) {
                n = a;
            }
            if (this.b.e >= this.b.f && this.b.e < n) {
                this.f(this.b.e - this.b.f);
            }
            for (int i = this.b.f; i < n; ++i) {
                this.b(i);
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
                final Dimension a = this.b.a();
                final int a2 = aM.a(this.b).a();
                final int width = a.width;
                final int height = a.height;
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
                final int n4 = n2 * width + n3 + this.b.f;
                if (n4 < a2 && n4 != this.b.e) {
                    this.b.c(n4);
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
    
    public aR(final aM c, final aM b) {
        this.c = c;
        this.b = b;
        this.resize(50, 50);
    }
}