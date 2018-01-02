// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Canvas;

public final class cq extends Canvas
{
    private final aq q;
    private long q;
    
    final Rectangle q(int n) {
        final Dimension size = this.size();
        final Dimension q;
        final int width = (q = this.q.q()).width;
        final int height = q.height;
        final int n2 = (size.width + 6 - width * 43) / 2;
        final int n3 = (size.height + 6 - height * 41) / 2;
        final int n4 = n2 + n % width * 43;
        n = n3 + n / width * 41;
        return new Rectangle(n4, n, 37, 35);
    }
    
    public final boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        if ((n & 0x20) != 0x0) {
            this.repaint();
            return false;
        }
        return true;
    }
    
    final void q(int n) {
        final Graphics graphics;
        if ((graphics = this.getGraphics()) != null) {
            final Rectangle q;
            final int x = (q = this.q(n)).x;
            final int y = q.y;
            final int n2 = x + q.width;
            n = y + q.height;
            final Color color = new Color(115, 115, 115);
            graphics.setColor(Color.black);
            graphics.drawLine(x, n - 2, x, y + 2);
            graphics.drawLine(x, y + 2, x + 2, y);
            graphics.drawLine(x + 2, y, n2 - 2, y);
            graphics.drawLine(n2 - 2, y, n2, y + 2);
            graphics.drawLine(n2, y + 2, n2, n - 2);
            graphics.drawLine(n2, n - 2, n2 - 2, n);
            graphics.drawLine(n2 - 2, n, x + 2, n);
            graphics.drawLine(x + 2, n, x, n - 2);
            graphics.drawLine(x + 5, y + 4, n2 - 5, y + 4);
            graphics.drawLine(n2 - 4, y + 5, n2 - 4, n - 5);
            graphics.drawLine(n2 - 5, n - 4, x + 5, n - 4);
            graphics.drawLine(x + 4, n - 5, x + 4, y + 5);
            graphics.setColor(Color.white);
            graphics.drawLine(x + 1, n - 2, x + 1, y + 2);
            graphics.drawLine(x + 2, y + 1, n2 - 2, y + 1);
            graphics.drawLine(n2 - 2, y + 5, n2 - 2, n - 5);
            graphics.drawLine(n2 - 2, n - 5, n2 - 4, n - 3);
            graphics.drawLine(n2 - 4, n - 3, x + 5, n - 3);
            graphics.setColor(color);
            graphics.drawLine(n2 - 1, y + 2, n2 - 1, n - 2);
            graphics.drawLine(n2 - 2, n - 1, x + 2, n - 1);
            graphics.drawLine(x + 4, n - 4, x + 3, n - 5);
            graphics.drawLine(x + 3, n - 5, x + 3, y + 5);
            graphics.drawLine(x + 3, y + 5, x + 5, y + 3);
            graphics.drawLine(x + 5, y + 3, n2 - 5, y + 3);
            graphics.drawLine(n2 - 5, y + 3, n2 - 4, y + 4);
            graphics.dispose();
        }
    }
    
    public final void paint(final Graphics graphics) {
        if (this.isShowing()) {
            final int q = this.q.q.q;
            int w = this.q.w();
            if (q <= w) {
                this.q.w = 0;
                w = q;
                this.q.q.e();
                this.q.w.e();
            }
            else {
                this.q.q.q();
                this.q.w.q();
            }
            int n;
            if ((n = this.q.w + w) > q) {
                n = q;
            }
            if (this.q.q >= this.q.w && this.q.q < n) {
                this.q(this.q.q - this.q.w);
            }
            for (int i = this.q.w; i < n; ++i) {
                final int n2 = i;
                final Graphics graphics2;
                if ((graphics2 = this.getGraphics()) != null) {
                    final Graphics graphics3 = graphics2;
                    final int n3 = n2;
                    final Graphics graphics4 = graphics3;
                    final boolean b;
                    if (!(b = (n3 < 0 || n3 >= this.q.q.q))) {
                        Image image = null;
                        if (this.q.q.q(n3) instanceof aZ) {
                            image = ((aZ)this.q.q.q(n3)).q;
                        }
                        if (this.q.q.q(n3) instanceof aj) {
                            image = ((aj)this.q.q.q(n3)).q;
                        }
                        if (this.q.q.q(n3) instanceof cx) {
                            image = ((cx)this.q.q.q(n3)).q;
                        }
                        final Rectangle q2 = this.q(n3 - this.q.w);
                        final int n4 = 1 + q2.x + (q2.width - 24) / 2;
                        final int n5 = 1 + q2.y + (q2.height - 24) / 2;
                        if (image != null) {
                            graphics4.drawImage(image, n4, n5, this);
                        }
                    }
                }
            }
        }
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public final boolean handleEvent(final Event event) {
        switch (event.id) {
            case 501: {
                final long currentTimeMillis = System.currentTimeMillis();
                if (this.q.q != null && currentTimeMillis - this.q < 300L) {
                    this.q.q.postEvent(new Event(this, 1001, event.arg));
                }
                this.q = currentTimeMillis;
            }
            case 506: {
                final Dimension size = this.size();
                final Dimension q = this.q.q();
                final int q2 = this.q.q.q;
                final int width = q.width;
                final int height = q.height;
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
                if ((n4 = n2 * width + n3 + this.q.w) < q2 && n4 != this.q.q) {
                    this.q.w(n4);
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
    
    public cq(final aq aq, final aq q) {
        this.q = 0L;
        this.q = q;
        this.resize(50, 50);
    }
}
