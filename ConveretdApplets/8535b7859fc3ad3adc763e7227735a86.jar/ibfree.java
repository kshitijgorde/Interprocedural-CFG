import java.awt.image.ImageObserver;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Window;

// 
// Decompiled by Procyon v0.5.30
// 

public final class ibfree extends Window
{
    private int x0;
    public boolean closed;
    private boolean x1;
    private int x2;
    private int x3;
    private Rectangle[] x4;
    private int x5;
    private int x6;
    public boolean hasMouse;
    private boolean x7;
    private Image x8;
    private Image x9;
    private boolean x10;
    private int x11;
    private int[] x12;
    private int x13;
    public boolean onView;
    private ipfree x14;
    private Rectangle x15;
    private Dimension x16;
    private int x17;
    private int x18;
    private int x19;
    private int x20;
    private int x21;
    private int x22;
    private int x23;
    
    public void close() {
        this.hasMouse = false;
        this.onView = false;
        if (this.closed) {
            return;
        }
        this.closed = true;
        this.resize(0, 0);
        if (this.x20 == 0) {
            this.x14.repaint();
        }
    }
    
    public void destroy() {
        if (this.x8 != null) {
            this.x8.flush();
        }
        if (this.x9 != null) {
            this.x9.flush();
        }
        this.x8 = null;
        this.x9 = null;
        this.x4 = null;
        this.x12 = null;
    }
    
    private void x0(final Graphics graphics) {
        Color black = Color.black;
        if (this.x14.colour[0].equals(Color.black)) {
            black = this.x14.colour[3];
        }
        final int n = 11;
        if (!this.x10) {
            this.x14.drawBox(graphics, this.x21 - 17, 4, n, n, n, true);
            graphics.setColor(black);
            graphics.drawLine(this.x21 - 13, 8, this.x21 - 10, 11);
            graphics.drawLine(this.x21 - 10, 8, this.x21 - 13, 11);
            this.x14.drawBox(graphics, this.x21 - 29, 4, n, n, n, true);
            graphics.setColor(black);
            int n2 = 0;
            do {
                graphics.drawLine(this.x21 - 22 - n2, 12 - n2, this.x21 - 22 - n2, 7 + n2);
            } while (++n2 < 3);
            return;
        }
        this.x14.drawBox(graphics, 0, 0, 20, this.x6, this.x0, false);
        this.x14.drawBox(graphics, 5, 5, n, n, n, true);
        graphics.setColor(black);
        int n3 = 0;
        do {
            graphics.drawLine(9 + n3, 13 - n3, 9 + n3, 8 + n3);
        } while (++n3 < 3);
    }
    
    public int getFocus() {
        if (this.x5 < 0 || this.x5 >= this.x17) {
            return -1;
        }
        return this.x12[this.x5];
    }
    
    public boolean handleEvent(final Event event) {
        final int x = event.x;
        final int y = event.y;
        try {
            if (event.id == 502) {
                if (!this.x1) {
                    if (!this.x7) {
                        return true;
                    }
                    this.x7 = false;
                    if (!this.x10 && this.x20 == 0 && y < 16 && x < this.x21 - 17 && x > this.x21 - 30) {
                        this.x10 = true;
                        this.resize(20, this.x6);
                    }
                }
                else {
                    this.move(this.x18 + x - this.x2, this.x19 + y - this.x3);
                    this.x15.move(this.x18 + x - this.x2, this.x19 + y - this.x3);
                    this.x1 = false;
                }
            }
            if (event.id == 501) {
                if (!this.x7) {
                    return true;
                }
                this.x7 = false;
                this.x5 = -1;
                for (int i = 0; i < this.x17; ++i) {
                    if (this.x4[i].inside(this.x21 / 2, y)) {
                        this.x5 = i;
                    }
                }
                if (this.x5 >= 0 && this.x5 < this.x17) {
                    this.x14.click(this.x12[this.x5], true);
                }
                if (this.x20 > 0 || (y < 16 && x > this.x21 - 16)) {
                    this.close();
                }
                this.x14.order();
                if (this.x10) {
                    this.resize(this.x21, this.x6);
                    this.x10 = false;
                }
            }
            if (event.id == 503) {
                this.hasMouse = true;
                this.onView = true;
                this.x5 = -1;
                for (int j = 0; j < this.x17; ++j) {
                    if (this.x4[j].inside(this.x21 / 2, y)) {
                        this.x5 = j;
                    }
                }
                if (this.x5 == this.x11) {
                    return true;
                }
                if (!this.x7) {
                    return true;
                }
                this.x7 = false;
                if (this.x10) {
                    this.resize(this.x21, this.x6);
                    this.x10 = false;
                }
                this.x11 = this.x5;
                if (this.x5 == -1 && this.x20 < this.x13 - 1 && this.x14.win[this.x20 + 1].onView) {
                    this.x14.win[this.x20 + 1].close();
                }
                if (this.x5 < 0 || this.x5 >= this.x17) {
                    return this.x7 = true;
                }
                this.x14.move(this.x20, this.x12[this.x5], this.x4[this.x5], this.x15);
                this.x14.order();
                final Graphics graphics = this.getGraphics();
                if (this.x20 == 0) {
                    graphics.clipRect(0, 20, this.x21, this.x6);
                }
                this.paint(graphics);
            }
            if (event.id == 505) {
                this.hasMouse = false;
                this.x11 = -1;
                this.paint(this.getGraphics());
                this.x14.order();
            }
            if (event.id == 504) {
                this.hasMouse = true;
                this.closed = false;
                if (this.x20 < this.x13 - 1 && this.x14.win[this.x20 + 1].onView) {
                    this.x14.win[this.x20 + 1].x5 = -1;
                }
                this.paint(this.getGraphics());
                this.x14.order();
            }
            if (event.id == 506 && this.x20 == 0 && y < 20 && !this.x1) {
                this.x7 = false;
                this.x1 = true;
                this.x18 = this.x15.x;
                this.x19 = this.x15.y;
                this.x2 = x;
                this.x3 = y;
            }
        }
        catch (Throwable t) {
            System.out.println(t);
        }
        return this.x7 = true;
    }
    
    public ibfree(final Frame frame, final int x20, final ipfree x21, final int x22, final Color background) {
        super(frame);
        this.x5 = -1;
        this.x11 = -1;
        this.closed = true;
        this.x16 = frame.getToolkit().getScreenSize();
        this.x20 = x20;
        this.x14 = x21;
        this.x13 = x22;
        this.setBackground(background);
    }
    
    public void make(final Rectangle rectangle, final Image x8, final Image x9, final Rectangle[] x10, final int x11, final int[] x12, final Rectangle rectangle2) {
        this.x22 = rectangle.x;
        this.x23 = rectangle.y;
        this.x21 = rectangle.width;
        this.x6 = rectangle.height;
        if (this.x22 > this.x16.width) {
            return;
        }
        if (this.x22 < 0) {
            this.x22 = 0;
        }
        int n = this.x23 + this.x6 + 10 - this.x16.height;
        if (rectangle2 != null) {
            n = this.x23 + this.x6 - (rectangle2.y + rectangle2.height);
        }
        if (n > 0) {
            this.x23 -= n;
        }
        if (this.x23 < 0) {
            this.x23 = 0;
        }
        if (this.x8 != null) {
            this.x8.flush();
        }
        if (this.x9 != null) {
            this.x9.flush();
        }
        this.x8 = x8;
        this.x9 = x9;
        this.x17 = x11;
        this.x4 = x10;
        this.x12 = x12;
        this.x15 = new Rectangle(this.x22, this.x23, this.x21, this.x6);
        this.resize(this.x21, this.x6);
        this.move(this.x22, this.x23);
        this.show();
        this.x0 = this.x6 - 18;
        this.x7 = true;
        this.paint(this.getGraphics());
        this.show();
        this.toFront();
        this.onView = true;
        this.closed = false;
    }
    
    public void paint(final Graphics graphics) {
        if (this.x8 != null) {
            graphics.drawImage(this.x8, 0, 0, this);
        }
        if (this.x20 == 0) {
            this.x0(graphics);
        }
        if (this.x5 >= 0 && this.x5 < this.x17 && !this.x10) {
            final Rectangle rectangle = this.x4[this.x5];
            graphics.clipRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            if (this.x9 != null) {
                graphics.drawImage(this.x9, 0, 0, this);
            }
        }
    }
    
    public void update(final Graphics graphics) {
        final Image image = this.createImage(this.size().width, this.size().height);
        final Graphics graphics2 = image.getGraphics();
        this.paint(graphics2);
        graphics.drawImage(image, 0, 0, null);
        image.flush();
        graphics2.dispose();
    }
}
