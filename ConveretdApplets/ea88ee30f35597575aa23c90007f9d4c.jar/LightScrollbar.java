import java.awt.Event;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class LightScrollbar extends Canvas implements Runnable
{
    static final int NOTHING = 0;
    static final int SCROLLING_UP = 1;
    static final int SCROLLING_DOWN = 2;
    static final int TRACKING = 4;
    static final int VERTICAL = 1;
    static final int HORIZONTAL = 0;
    static final int ARROW_UP = 0;
    static final int ARROW_LEFT = 1;
    static final int ARROW_RIGHT = 2;
    static final int ARROW_DOWN = 3;
    static final long DELAY = 100L;
    Thread thread;
    int thread_state;
    int orientation;
    int line_increment;
    int maximum;
    int minimum;
    int page_increment;
    public int value;
    int visible;
    static final int arrow_width_height = 11;
    boolean top_up;
    boolean bottom_up;
    boolean bar_up;
    int bar_xy;
    int bar_height;
    int lastx;
    int lasty;
    int state;
    int track_xy;
    int old_bar_xy;
    Image scratch;
    Graphics g_scratch;
    Rectangle topleft_arrow;
    Rectangle botright_arrow;
    Rectangle bar_rect;
    int scroll_xy;
    int scroll_width;
    int shift_left;
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
        this.thread_state = 0;
    }
    
    public final void setValue(int value) {
        if (value < this.minimum) {
            value = this.minimum;
        }
        if (value > this.maximum) {
            value = this.maximum;
        }
        this.value = value;
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        if (this.g_scratch == null) {
            this.scratch = this.createImage(this.size().width, this.size().height);
            this.g_scratch = this.scratch.getGraphics();
        }
        this.paintSingleBuffer(this.g_scratch);
        graphics.drawImage(this.scratch, 0, 0, this);
    }
    
    public final int getVisible() {
        return this.visible;
    }
    
    public final void setVisible(final boolean b) {
        this.show(b);
    }
    
    public final void setValues(final int value, int visible, final int minimum, int maximum) {
        if (visible < 0) {
            visible = 0;
        }
        this.visible = visible;
        if (maximum < minimum) {
            maximum = minimum;
        }
        this.minimum = minimum;
        this.maximum = maximum;
        this.setValue(value);
        this.repaint();
    }
    
    public Dimension minimumSize() {
        if (this.orientation == 0) {
            return new Dimension(50, 5);
        }
        return new Dimension(5, 50);
    }
    
    private final void fill3DEmbossedRect(final Rectangle rectangle, final boolean b, final Graphics graphics, final Color color) {
        if (b) {
            final int n = rectangle.x + rectangle.width - 1;
            final int n2 = rectangle.y + rectangle.height - 1;
            graphics.setColor(color);
            graphics.fillRect(rectangle.x, rectangle.y, rectangle.width - 1, rectangle.height);
            graphics.setColor(Color.black);
            graphics.drawLine(rectangle.x, n2, n, n2);
            graphics.drawLine(rectangle.x, n2, n, n2);
            graphics.drawLine(n, rectangle.y, n, n2);
            graphics.setColor(color.brighter());
            graphics.drawLine(rectangle.x + 1, rectangle.y + 1, rectangle.x + 1, n2 - 2);
            graphics.drawLine(rectangle.x + 1, rectangle.y + 1, n - 2, rectangle.y + 1);
            graphics.setColor(color.darker());
            graphics.drawLine(n - 1, n2 - 1, n - 1, rectangle.y + 1);
            graphics.drawLine(n - 1, n2 - 1, rectangle.x + 1, n2 - 1);
            return;
        }
        graphics.setColor(color.darker());
        graphics.fillRect(rectangle.x, rectangle.y, rectangle.width - 1, rectangle.height);
        graphics.setColor(color);
        graphics.fillRect(rectangle.x + 1, rectangle.y + 1, rectangle.width - 2, rectangle.height - 2);
    }
    
    public final void setLineIncrement(int line_increment) {
        if (line_increment < 0) {
            line_increment = 0;
        }
        this.line_increment = line_increment;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private final void drawArrowHead(final Rectangle rectangle, final Graphics graphics, final Color color, final int n, final boolean b) {
        final int n2 = rectangle.x + rectangle.width;
        final int n3 = rectangle.y + rectangle.height;
        final int x = rectangle.x;
        final int y = rectangle.y;
        this.fill3DEmbossedRect(rectangle, b, graphics, color);
        graphics.setColor(Color.black);
        switch (n) {
            case 3: {
                graphics.drawLine(x + 4, y + 4, x + 6, y + 4);
                graphics.drawLine(x + 5, y + 5, x + 5, y + 5);
            }
            case 0: {
                graphics.drawLine(x + 4, n3 - 5, x + 6, n3 - 5);
                graphics.drawLine(x + 5, n3 - 6, x + 5, n3 - 6);
            }
            case 1: {
                graphics.drawLine(n2 - 5, y + 4, n2 - 5, y + 6);
                graphics.drawLine(n2 - 6, y + 5, n2 - 6, y + 5);
            }
            case 2: {
                graphics.drawLine(x + 4, y + 4, x + 4, y + 6);
                graphics.drawLine(x + 5, y + 5, x + 5, y + 5);
            }
            default: {}
        }
    }
    
    public final void setLeft(final int shift_left) {
        this.shift_left = shift_left;
        this.repaint();
    }
    
    public void start() {
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
    }
    
    private final void paintSingleBuffer(final Graphics graphics) {
        final int width = this.size().width;
        final int height = this.size().height;
        final Color foreground = this.getForeground();
        graphics.setColor(foreground.brighter());
        graphics.fill3DRect(0, 0, width, height, false);
        final int n = (this.orientation == 0) ? width : height;
        final int n2 = this.maximum - this.minimum + this.visible;
        final int n3 = n - 22 - this.shift_left;
        this.scroll_xy = 0;
        this.scroll_width = n3;
        if (n2 == 0) {
            this.bar_xy = 11;
            this.bar_height = n3;
        }
        else {
            this.bar_xy = this.scroll_xy + (this.value - this.minimum) * n3 / n2;
            this.bar_height = this.visible * n3 / n2;
        }
        if (this.bar_height < 4) {
            this.bar_height = 4;
        }
        if (this.bar_xy > n - 11 - 4) {
            this.bar_xy = n - 11 - 4;
            if (this.bar_xy < 11) {
                this.bar_xy = 11;
            }
        }
        switch (this.orientation) {
            case 1: {
                this.topleft_arrow = new Rectangle(0, height - (22 + this.shift_left), width, 11);
                this.botright_arrow = new Rectangle(0, height - (11 + this.shift_left), width, 11);
                this.bar_rect = new Rectangle(0, this.bar_xy, 11, this.bar_height);
                this.drawArrowHead(this.topleft_arrow, graphics, foreground, 0, this.top_up);
                this.drawArrowHead(this.botright_arrow, graphics, foreground, 3, this.bottom_up);
                this.fill3DEmbossedRect(this.bar_rect, this.bar_up, graphics, foreground);
            }
            case 0: {
                this.topleft_arrow = new Rectangle(width - (22 + this.shift_left), 0, 11, height);
                this.botright_arrow = new Rectangle(width - (11 + this.shift_left), 0, 11, height);
                this.bar_rect = new Rectangle(this.bar_xy, 0, this.bar_height, 11);
                this.drawArrowHead(this.topleft_arrow, graphics, foreground, 1, this.top_up);
                this.drawArrowHead(this.botright_arrow, graphics, foreground, 2, this.bottom_up);
                this.fill3DEmbossedRect(this.bar_rect, this.bar_up, graphics, foreground);
            }
            default: {}
        }
    }
    
    public Dimension preferredSize() {
        if (this.orientation == 0) {
            return new Dimension(100, 11);
        }
        return new Dimension(11, 100);
    }
    
    public LightScrollbar(final int orientation) {
        this.orientation = 1;
        this.line_increment = 1;
        this.page_increment = 10;
        this.visible = 1;
        this.top_up = true;
        this.bottom_up = true;
        this.bar_up = true;
        this.state = 1;
        this.topleft_arrow = new Rectangle();
        this.botright_arrow = new Rectangle();
        this.bar_rect = new Rectangle();
        this.orientation = orientation;
        this.setValues(this.value, this.visible, this.minimum, this.maximum);
        this.setForeground(Color.lightGray);
        this.setBackground(Color.white);
    }
    
    public void run() {
        while (this.thread != null) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
            if (this.thread_state == 1) {
                this.value -= this.line_increment;
                if (this.value < this.minimum) {
                    this.value = this.minimum;
                }
                this.top_up = false;
                this.repaint();
                this.postEvent(new Event(this, 601, new Integer(this.value)));
            }
            else {
                if (this.thread_state != 2) {
                    continue;
                }
                this.value += this.line_increment;
                if (this.value > this.maximum) {
                    this.value = this.maximum;
                }
                this.bottom_up = false;
                this.repaint();
                this.postEvent(new Event(this, 602, new Integer(this.value)));
            }
        }
    }
    
    public boolean handleEvent(final Event event) {
        final int track_xy = (this.orientation == 0) ? event.x : event.y;
        this.size();
        this.size();
        switch (event.id) {
            case 501: {
                if (this.topleft_arrow.inside(event.x, event.y)) {
                    this.value -= this.line_increment;
                    if (this.value < this.minimum) {
                        this.value = this.minimum;
                    }
                    this.top_up = false;
                    this.repaint();
                    this.postEvent(new Event(this, 601, new Integer(this.value)));
                    this.thread_state = 1;
                    this.start();
                    break;
                }
                if (this.botright_arrow.inside(event.x, event.y)) {
                    this.value += this.line_increment;
                    if (this.value > this.maximum) {
                        this.value = this.maximum;
                    }
                    this.bottom_up = false;
                    this.repaint();
                    this.postEvent(new Event(this, 602, new Integer(this.value)));
                    this.thread_state = 2;
                    this.start();
                    break;
                }
                if (track_xy < this.bar_xy && track_xy > this.scroll_xy) {
                    this.state = 1;
                    this.value -= this.page_increment;
                    if (this.value < this.minimum) {
                        this.value = this.minimum;
                    }
                    this.repaint();
                    this.postEvent(new Event(this, 603, new Integer(this.value)));
                    this.thread_state = 1;
                    this.start();
                    break;
                }
                if (track_xy > this.bar_xy + this.bar_height && track_xy < this.scroll_width + this.scroll_xy) {
                    this.state = 2;
                    this.value += this.page_increment;
                    if (this.value > this.maximum) {
                        this.value = this.maximum;
                    }
                    this.repaint();
                    this.postEvent(new Event(this, 604, new Integer(this.value)));
                    this.thread_state = 2;
                    this.start();
                    break;
                }
                this.track_xy = track_xy;
                this.old_bar_xy = this.bar_xy;
                this.bar_up = false;
                this.state = 4;
                this.repaint();
                break;
            }
            case 502: {
                this.bottom_up = true;
                this.top_up = true;
                this.bar_up = true;
                this.state = 0;
                this.repaint();
                this.stop();
                break;
            }
            case 505: {
                if (this.topleft_arrow.inside(this.lastx, this.lasty) || this.botright_arrow.inside(this.lastx, this.lasty)) {
                    this.repaint();
                    break;
                }
                break;
            }
            case 504: {
                this.lastx = event.x;
                this.lasty = event.y;
                if (this.topleft_arrow.inside(event.x, event.y) || this.botright_arrow.inside(event.x, event.y)) {
                    this.repaint();
                    break;
                }
                break;
            }
            case 506: {
                if (this.topleft_arrow.inside(event.x, event.y) != this.topleft_arrow.inside(this.lastx, this.lasty) || this.botright_arrow.inside(event.x, event.y) != this.botright_arrow.inside(this.lastx, this.lasty)) {
                    this.repaint();
                }
                if (this.bar_up) {
                    break;
                }
                int scroll_xy = track_xy - this.track_xy + this.old_bar_xy;
                final int n = this.scroll_width - this.bar_height;
                if (n == 11) {
                    return false;
                }
                if (scroll_xy < this.scroll_xy) {
                    scroll_xy = this.scroll_xy;
                }
                if (scroll_xy > n) {
                    scroll_xy = n;
                }
                this.value = (scroll_xy - this.scroll_xy) * (this.maximum - this.minimum) / (n - this.scroll_xy) + this.minimum;
                this.repaint();
                this.postEvent(new Event(this, 605, new Integer(this.value)));
                break;
            }
            default: {
                return super.handleEvent(event);
            }
        }
        this.lastx = event.x;
        this.lasty = event.y;
        return false;
    }
    
    public void reshape(final int n, final int n2, final int n3, final int n4) {
        super.reshape(n, n2, n3, n4);
        if (n3 > 0 && n4 > 0) {
            this.scratch = this.createImage(n3, n4);
            if (this.scratch != null) {
                this.g_scratch = this.scratch.getGraphics();
            }
            this.repaint();
        }
    }
}
