// 
// Decompiled by Procyon v0.5.30
// 

package chat;

import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Canvas;

public final class cl extends Canvas
{
    private ck a;
    
    final Rectangle a(final int n) {
        final Dimension size = this.size();
        final Dimension a;
        final int width = (a = this.a.a()).width;
        return new Rectangle((size.width + 6 - width * 43) / 2 + n % width * 43, (size.height + 6 - a.height * 41) / 2 + n / width * 41, 37, 35);
    }
    
    public final boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.repaint();
            return false;
        }
        return true;
    }
    
    final void a(int x) {
        final Graphics graphics;
        if ((graphics = this.getGraphics()) != null) {
            final Rectangle a;
            x = (a = this.a(x)).x;
            final int y = a.y;
            final int n = x + a.width;
            final int n2 = y + a.height;
            final Color color = new Color(115, 115, 115);
            graphics.setColor(Color.black);
            graphics.drawLine(x, n2 - 2, x, y + 2);
            graphics.drawLine(x, y + 2, x + 2, y);
            graphics.drawLine(x + 2, y, n - 2, y);
            graphics.drawLine(n - 2, y, n, y + 2);
            graphics.drawLine(n, y + 2, n, n2 - 2);
            graphics.drawLine(n, n2 - 2, n - 2, n2);
            graphics.drawLine(n - 2, n2, x + 2, n2);
            graphics.drawLine(x + 2, n2, x, n2 - 2);
            graphics.drawLine(x + 5, y + 4, n - 5, y + 4);
            graphics.drawLine(n - 4, y + 5, n - 4, n2 - 5);
            graphics.drawLine(n - 5, n2 - 4, x + 5, n2 - 4);
            graphics.drawLine(x + 4, n2 - 5, x + 4, y + 5);
            graphics.setColor(Color.white);
            graphics.drawLine(x + 1, n2 - 2, x + 1, y + 2);
            graphics.drawLine(x + 2, y + 1, n - 2, y + 1);
            graphics.drawLine(n - 2, y + 5, n - 2, n2 - 5);
            graphics.drawLine(n - 2, n2 - 5, n - 4, n2 - 3);
            graphics.drawLine(n - 4, n2 - 3, x + 5, n2 - 3);
            graphics.setColor(color);
            graphics.drawLine(n - 1, y + 2, n - 1, n2 - 2);
            graphics.drawLine(n - 2, n2 - 1, x + 2, n2 - 1);
            graphics.drawLine(x + 4, n2 - 4, x + 3, n2 - 5);
            graphics.drawLine(x + 3, n2 - 5, x + 3, y + 5);
            graphics.drawLine(x + 3, y + 5, x + 5, y + 3);
            graphics.drawLine(x + 5, y + 3, n - 5, y + 3);
            graphics.drawLine(n - 5, y + 3, n - 4, y + 4);
            graphics.dispose();
        }
    }
    
    public final void paint(final Graphics graphics) {
        if (this.isShowing()) {
            final int a = this.a.a.a();
            int b = this.a.b();
            if (a <= b) {
                this.a.b = 0;
                b = a;
                this.a.a.b();
                this.a.b.b();
            }
            else {
                this.a.a.a();
                this.a.b.a();
            }
            int n;
            if ((n = this.a.b + b) > a) {
                n = a;
            }
            if (this.a.a >= this.a.b && this.a.a < n) {
                this.a(this.a.a - this.a.b);
            }
            for (int i = this.a.b; i < n; ++i) {
                final int n2 = i;
                final Graphics graphics2;
                if ((graphics2 = this.getGraphics()) != null) {
                    final Graphics graphics3 = graphics2;
                    final int n3 = n2;
                    final Graphics graphics4 = graphics3;
                    final Image a2 = ((b)this.a.a.a(n3)).a;
                    final Rectangle a3 = this.a(n3 - this.a.b);
                    graphics4.drawImage(a2, 1 + a3.x + (a3.width - 24) / 2, 1 + a3.y + (a3.height - 24) / 2, this);
                    graphics2.dispose();
                }
            }
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 501:
            case 506: {
                final Dimension size = this.size();
                final Dimension a = this.a.a();
                final int a2 = this.a.a.a();
                final int width = a.width;
                final int height = a.height;
                final int n = (size.width + 6 - width * 43) / 2;
                int n2 = (event.y - (size.height + 6 - height * 41) / 2) / 41;
                int n3;
                if ((n3 = (event.x - n) / 43) >= width) {
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
                final int n4;
                if ((n4 = n2 * width + n3 + this.a.b) < a2 && n4 != this.a.a) {
                    this.a.b(n4);
                }
                return true;
            }
            default: {
                return super.handleEvent(event);
            }
        }
    }
    
    public final Dimension minimumSize() {
        return new Dimension(37, 35);
    }
    
    public cl(final ck a) {
        this.a = a;
        this.resize(50, 50);
    }
}
