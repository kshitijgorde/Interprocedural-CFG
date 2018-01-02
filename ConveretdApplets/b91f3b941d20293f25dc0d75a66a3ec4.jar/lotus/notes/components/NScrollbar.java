// 
// Decompiled by Procyon v0.5.30
// 

package lotus.notes.components;

import java.awt.Dimension;
import java.awt.Event;
import lotus.notes.util.Bidi;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Canvas;

public class NScrollbar extends Canvas implements Runnable
{
    final int NOTHING = 0;
    final int SCROLLING_UP = 1;
    final int SCROLLING_DOWN = 2;
    final int TRACKING = 4;
    static final int VERTICAL = 1;
    static final int HORIZONTAL = 0;
    static final int ARROW_UP = 0;
    static final int ARROW_LEFT = 1;
    static final int ARROW_RIGHT = 2;
    static final int ARROW_DOWN = 3;
    static final long DELAY = 80L;
    static final long PAUSE_DELAY = 250L;
    Thread thread;
    int thread_state;
    int orientation;
    int line_increment;
    int maximum;
    int minimum;
    int page_increment;
    int value;
    int visible;
    private int direction;
    static final int arrow_width_height = 13;
    boolean top_up;
    boolean bottom_up;
    boolean bar_up;
    boolean doScroll;
    boolean doWait;
    boolean bDrawCorner;
    boolean trackDrag;
    int bar_xy;
    int bar_height;
    int lastx;
    int lasty;
    int state;
    int track_xy;
    int old_bar_xy;
    Color FCOLOR;
    Color BCOLOR;
    Image scratch;
    Graphics g_scratch;
    static final char[] up;
    static final char[] down;
    String BrowserVendor;
    
    public NScrollbar() {
        this(1, 0, 1, 0, 0);
    }
    
    public NScrollbar(final int n) {
        this(n, 0, 1, 0, 0);
    }
    
    public NScrollbar(final int orientation, final int n, final int n2, final int n3, final int n4) {
        this.thread_state = 0;
        this.orientation = 1;
        this.line_increment = 1;
        this.page_increment = 10;
        this.value = 0;
        this.visible = 1;
        this.direction = 0;
        this.top_up = true;
        this.bottom_up = true;
        this.bar_up = true;
        this.doScroll = true;
        this.doWait = true;
        this.bDrawCorner = false;
        this.trackDrag = true;
        this.bar_xy = 0;
        this.bar_height = 15;
        this.lastx = 0;
        this.lasty = 0;
        this.state = 1;
        this.track_xy = 0;
        this.old_bar_xy = 0;
        this.scratch = null;
        this.g_scratch = null;
        this.BrowserVendor = "";
        this.orientation = orientation;
        this.setValues(n, n2, n3, n4);
        this.setForeground(new Color(192, 192, 192));
        this.setBackground(new Color(224, 224, 224));
        this.FCOLOR = this.getForeground();
        this.BCOLOR = this.getBackground();
        this.BrowserVendor = System.getProperty("browser");
        if (this.BrowserVendor != null) {
            this.BrowserVendor = this.BrowserVendor.toLowerCase();
        }
    }
    
    public void setTrackOnDrag(final boolean trackDrag) {
        this.trackDrag = trackDrag;
    }
    
    public boolean isTrackOnDrag() {
        return this.trackDrag;
    }
    
    public int getLineIncrement() {
        return this.line_increment;
    }
    
    public int getMaximum() {
        return this.maximum;
    }
    
    public int getMinimum() {
        return this.minimum;
    }
    
    public int getOrientation() {
        return this.orientation;
    }
    
    public int getPageIncrement() {
        return this.page_increment;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public int getVisible() {
        return this.visible;
    }
    
    public void setLineIncrement(int line_increment) {
        if (line_increment < 0) {
            line_increment = 0;
        }
        this.line_increment = line_increment;
    }
    
    public void setPageIncrement(int page_increment) {
        if (page_increment < 0) {
            page_increment = 0;
        }
        this.page_increment = page_increment;
    }
    
    public void setValue(int value) {
        if (value < this.minimum) {
            value = this.minimum;
        }
        if (value > this.maximum) {
            value = this.maximum;
        }
        this.value = value;
        this.repaint();
    }
    
    public void setValues(int value, int visible, final int minimum, int maximum) {
        if (visible < 0) {
            visible = 0;
        }
        this.visible = visible;
        if (maximum < minimum) {
            maximum = minimum;
        }
        this.minimum = minimum;
        this.maximum = maximum;
        if (value < minimum) {
            value = minimum;
        }
        if (value > maximum) {
            value = maximum;
        }
        this.value = value;
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.g_scratch == null) {
            this.scratch = this.createImage(this.size().width, this.size().height);
            this.g_scratch = this.scratch.getGraphics();
        }
        this.paintSingleBuffer(this.g_scratch);
        graphics.drawImage(this.scratch, 0, 0, this);
    }
    
    public void paintSingleBuffer(final Graphics graphics) {
        final int width = this.size().width;
        final int height = this.size().height;
        graphics.setColor(this.BCOLOR);
        graphics.fillRect(0, 0, width, height);
        switch (this.orientation) {
            case 1: {
                if (this.bDrawCorner) {
                    this.drawArrowHead(0, 0, graphics, this.FCOLOR, 0, this.top_up);
                    this.drawArrowHead(0, height - 26 - 1, graphics, this.FCOLOR, 3, this.bottom_up);
                    graphics.setColor(this.FCOLOR);
                    graphics.fillRect(0, height - 13 - 1, width, 14);
                    graphics.setColor(this.BCOLOR);
                    break;
                }
                this.drawArrowHead(0, 0, graphics, this.FCOLOR, 0, this.top_up);
                this.drawArrowHead(0, height - 13, graphics, this.FCOLOR, 3, this.bottom_up);
                break;
            }
            case 0: {
                if (this.bDrawCorner) {
                    if (this.direction == 0) {
                        this.drawArrowHead(0, 0, graphics, this.FCOLOR, 1, this.top_up);
                        this.drawArrowHead(width - 26 - 1, 0, graphics, this.FCOLOR, 2, this.bottom_up);
                    }
                    else {
                        this.drawArrowHead(13, 0, graphics, this.FCOLOR, 1, this.bottom_up);
                        this.drawArrowHead(width - 13, 0, graphics, this.FCOLOR, 2, this.top_up);
                    }
                    graphics.setColor(this.FCOLOR);
                    if (this.direction == 0) {
                        graphics.fillRect(width - 13 + 1, 0, 13, height);
                    }
                    else {
                        graphics.fillRect(Bidi.toggleHorzPos(width - 13 + 1, 13, width), 0, 13, height);
                    }
                    graphics.setColor(this.BCOLOR);
                    break;
                }
                if (this.direction == 0) {
                    this.drawArrowHead(0, 0, graphics, this.FCOLOR, 1, this.top_up);
                    this.drawArrowHead(width - 13, 0, graphics, this.FCOLOR, 2, this.bottom_up);
                    break;
                }
                this.drawArrowHead(0, 0, graphics, this.FCOLOR, 1, this.bottom_up);
                this.drawArrowHead(width - 13, 0, graphics, this.FCOLOR, 2, this.top_up);
                break;
            }
        }
        graphics.setColor(this.FCOLOR);
        final int n = this.maximum - this.minimum + this.visible;
        switch (this.orientation) {
            case 1: {
                final int n2 = height - 26;
                if (n == 0) {
                    this.bar_xy = 13;
                }
                else {
                    this.bar_xy = 13 + (this.value - this.minimum) * n2 / Math.max(n, 1);
                    if (!this.bDrawCorner && this.bar_xy + this.bar_height > height - 13) {
                        this.bar_xy = height - 13 - this.bar_height;
                    }
                    else if (this.bDrawCorner && this.bar_xy + this.bar_height > height - 26 - 1) {
                        this.bar_xy = height - 26 - 1 - this.bar_height;
                    }
                }
                this.fill3DEmbossedRect(0, this.bar_xy, 13, this.bar_height, true, graphics, this.FCOLOR);
                break;
            }
            case 0: {
                final int n3 = this.bDrawCorner ? (width - 39) : (width - 26);
                if (n == 0) {
                    this.bar_xy = 13;
                }
                else {
                    this.bar_xy = 13 + (this.value - this.minimum) * n3 / Math.max(this.maximum - this.minimum, 1);
                    if (!this.bDrawCorner && this.bar_xy + this.bar_height > width - 13) {
                        this.bar_xy = width - 13 - this.bar_height;
                    }
                    if (this.bDrawCorner && this.bar_xy + this.bar_height > width - 26 - 1) {
                        this.bar_xy = width - 26 - 1 - this.bar_height;
                    }
                    else if (this.bar_xy < 13) {
                        this.bar_xy = 13;
                    }
                }
                if (this.direction == 0) {
                    this.fill3DEmbossedRect(this.bar_xy, 0, this.bar_height, 13, true, graphics, this.FCOLOR);
                    break;
                }
                this.fill3DEmbossedRect(Bidi.toggleHorzPos(this.bar_xy, this.bar_height, width), 0, this.bar_height, 13, true, graphics, this.FCOLOR);
                break;
            }
        }
    }
    
    public boolean mouseDown(final Event event, int toggleHorzPos, final int n) {
        synchronized (this) {
            switch (this.orientation) {
                case 1: {
                    if (n < 13) {
                        this.value -= this.line_increment;
                        if (this.value < this.minimum) {
                            this.value = this.minimum;
                        }
                        this.top_up = false;
                        this.repaint();
                        this.postEvent(new Event(this, 601, null));
                        this.thread_state = 1;
                        this.start();
                        break;
                    }
                    if (this.bDrawCorner && n > this.size().height - 26 - 1 && n < this.size().height - 13) {
                        this.value += this.line_increment;
                        if (this.value > this.maximum) {
                            this.value = this.maximum;
                        }
                        this.bottom_up = false;
                        this.repaint();
                        this.postEvent(new Event(this, 602, null));
                        this.thread_state = 2;
                        this.start();
                        break;
                    }
                    if (!this.bDrawCorner && n > this.size().height - 13) {
                        this.value += this.line_increment;
                        if (this.value > this.maximum) {
                            this.value = this.maximum;
                        }
                        this.bottom_up = false;
                        this.repaint();
                        this.postEvent(new Event(this, 602, null));
                        this.thread_state = 2;
                        this.start();
                        break;
                    }
                    if (n < this.bar_xy) {
                        this.state = 1;
                        this.value -= this.page_increment;
                        if (this.value < this.minimum) {
                            this.value = this.minimum;
                        }
                        this.repaint();
                        this.postEvent(new Event(this, 603, null));
                        break;
                    }
                    if (n >= this.bar_xy + this.bar_height && n < this.size().height - 13) {
                        this.state = 2;
                        this.value += this.page_increment;
                        if (this.value > this.maximum) {
                            this.value = this.maximum;
                        }
                        this.repaint();
                        this.postEvent(new Event(this, 604, null));
                        break;
                    }
                    if (this.bDrawCorner && n > this.size().height - 26 - 1) {
                        this.bar_up = true;
                        break;
                    }
                    this.track_xy = n;
                    this.old_bar_xy = this.bar_xy;
                    this.bar_up = false;
                    this.state = 4;
                    this.repaint();
                    break;
                }
                case 0: {
                    if (this.direction == 1) {
                        toggleHorzPos = Bidi.toggleHorzPos(toggleHorzPos, 0, this.size().width);
                    }
                    if (toggleHorzPos < 13) {
                        this.value -= this.line_increment;
                        if (this.value < this.minimum) {
                            this.value = this.minimum;
                        }
                        this.top_up = false;
                        this.repaint();
                        this.postEvent(new Event(this, 601, null));
                        this.thread_state = 1;
                        this.start();
                        break;
                    }
                    if (toggleHorzPos > this.size().width - 13) {
                        this.value += this.line_increment;
                        if (this.value > this.maximum) {
                            this.value = this.maximum;
                        }
                        this.bottom_up = false;
                        this.repaint();
                        this.postEvent(new Event(this, 602, null));
                        this.thread_state = 2;
                        this.start();
                        break;
                    }
                    if (this.bDrawCorner && toggleHorzPos > this.size().width - 26 - 1 && toggleHorzPos < this.size().width - 13) {
                        this.value += this.line_increment;
                        if (this.value > this.maximum) {
                            this.value = this.maximum;
                        }
                        this.bottom_up = false;
                        this.repaint();
                        this.postEvent(new Event(this, 602, null));
                        this.thread_state = 2;
                        this.start();
                        break;
                    }
                    if (toggleHorzPos < this.bar_xy) {
                        this.state = 1;
                        this.value -= this.page_increment;
                        if (this.value < this.minimum) {
                            this.value = this.minimum;
                        }
                        this.repaint();
                        this.postEvent(new Event(this, 603, null));
                        break;
                    }
                    if (toggleHorzPos >= this.bar_xy + this.bar_height) {
                        this.state = 2;
                        this.value += this.page_increment;
                        if (this.value > this.maximum) {
                            this.value = this.maximum;
                        }
                        this.repaint();
                        this.postEvent(new Event(this, 604, null));
                        break;
                    }
                    if (this.bDrawCorner && toggleHorzPos > this.size().width - 26 - 1 && toggleHorzPos < this.size().width - 13) {
                        this.bar_up = false;
                        break;
                    }
                    this.track_xy = toggleHorzPos;
                    this.old_bar_xy = this.bar_xy;
                    this.bar_up = false;
                    this.state = 4;
                    this.repaint();
                    break;
                }
            }
            this.lastx = toggleHorzPos;
            this.lasty = n;
        }
        return false;
    }
    
    public boolean mouseUp(final Event event, final int lastx, final int lasty) {
        synchronized (this) {
            if (!this.trackDrag && this.state == 4) {
                this.postEvent(new Event(this, 605, null));
            }
            else {
                this.stop();
            }
            this.bottom_up = true;
            this.top_up = true;
            this.bar_up = true;
            this.state = 0;
            this.repaint();
            this.lastx = lastx;
            this.lasty = lasty;
        }
        return false;
    }
    
    public boolean mouseEnter(final Event event, final int lastx, final int lasty) {
        this.lastx = lastx;
        this.lasty = lasty;
        if (this.insideTop(lastx, lasty) || this.insideBottom(lastx, lasty)) {
            this.repaint();
        }
        return false;
    }
    
    public boolean mouseExit(final Event event, final int lastx, final int lasty) {
        if (this.insideTop(this.lastx, this.lasty) || this.insideBottom(this.lastx, this.lasty)) {
            this.repaint();
        }
        this.lastx = lastx;
        this.lasty = lasty;
        return false;
    }
    
    public boolean mouseDrag(final Event event, int toggleHorzPos, final int lasty) {
        if ((!this.insideTop(toggleHorzPos, lasty) && !this.top_up) || (!this.insideBottom(toggleHorzPos, lasty) && !this.bottom_up)) {
            this.stopthread();
            return false;
        }
        if (this.insideTop(toggleHorzPos, lasty) || this.insideBottom(toggleHorzPos, lasty)) {
            this.start();
        }
        if (this.insideTop(toggleHorzPos, lasty) != this.insideTop(this.lastx, this.lasty) || this.insideBottom(toggleHorzPos, lasty) != this.insideBottom(this.lastx, this.lasty)) {
            this.repaint();
        }
        switch (this.orientation) {
            case 1: {
                if (this.bar_up && this.bDrawCorner && lasty > this.size().height - 26 - 1) {
                    break;
                }
                if (this.bar_up) {
                    break;
                }
                int n = lasty - this.track_xy + this.old_bar_xy;
                final int n2 = this.bDrawCorner ? (this.size().height - this.bar_height - 26 - 1) : (this.size().height - this.bar_height - 13);
                if (n2 == 13) {
                    return false;
                }
                if (n < 13) {
                    n = 13;
                }
                else if (n > n2) {
                    n = n2;
                }
                this.value = (n - 13) * (this.maximum - this.minimum) / (n2 - 13) + this.minimum;
                this.repaint();
                if (this.trackDrag) {
                    this.postEvent(new Event(this, 605, null));
                }
                break;
            }
            case 0: {
                if (this.direction == 1) {
                    toggleHorzPos = Bidi.toggleHorzPos(toggleHorzPos, 0, this.size().width);
                }
                if (this.bar_up && this.bDrawCorner && toggleHorzPos > this.size().width - 26 - 1) {
                    break;
                }
                if (this.bar_up) {
                    break;
                }
                int n3 = toggleHorzPos - this.track_xy + this.old_bar_xy;
                final int n4 = this.size().width - 26 - 1;
                if (n4 == 13) {
                    return false;
                }
                if (n3 <= 13) {
                    n3 = 13;
                }
                else if (n3 > n4 || n3 > this.maximum) {
                    n3 = n4;
                }
                this.value = (n3 - 13) * (this.maximum - this.minimum) / (n4 - 13) + this.minimum;
                this.repaint();
                if (this.trackDrag) {
                    this.postEvent(new Event(this, 605, null));
                    break;
                }
                break;
            }
        }
        this.lastx = toggleHorzPos;
        this.lasty = lasty;
        return false;
    }
    
    final boolean insideTop(final int n, final int n2) {
        switch (this.orientation) {
            case 1: {
                return n > -1 && n2 > -1 && n < this.size().width && n2 < 13;
            }
            case 0: {
                return n > -1 && n2 > -1 && n2 < this.size().height && n < 13;
            }
            default: {
                return false;
            }
        }
    }
    
    final boolean insideBottom(final int n, final int n2) {
        final int n3 = this.bDrawCorner ? 13 : 0;
        final int n4 = 13 + n3;
        switch (this.orientation) {
            case 1: {
                return n > -1 && n2 > this.size().height - n4 && n < this.size().width && n2 < this.size().height - n3;
            }
            case 0: {
                return n2 > -1 && n > this.size().width - n4 && n2 < this.size().height && n < this.size().width - n3;
            }
            default: {
                return false;
            }
        }
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
    
    public void drawCorner(final boolean bDrawCorner) {
        this.bDrawCorner = bDrawCorner;
    }
    
    public Dimension preferredSize() {
        switch (this.orientation) {
            case 1: {
                return new Dimension(14, 100);
            }
            default: {
                return new Dimension(100, 14);
            }
        }
    }
    
    public Dimension minimumSize() {
        switch (this.orientation) {
            case 1: {
                return new Dimension(5, 50);
            }
            default: {
                return new Dimension(50, 5);
            }
        }
    }
    
    private void fill3DEmbossedRect(final int n, final int n2, final int n3, final int n4, final boolean b, final Graphics graphics, final Color color) {
        int n5 = 0;
        int n6 = 0;
        switch (this.orientation) {
            case 1: {
                n5 = n + n3;
                n6 = n2 + n4 - 1;
                break;
            }
            case 0: {
                n5 = n + n3 - 1;
                n6 = n2 + n4;
                break;
            }
        }
        if (b) {
            graphics.setColor(color);
            graphics.fillRect(n, n2, n3 - 1, n4);
            graphics.setColor(Color.black);
            graphics.drawLine(n, n6, n5, n6);
            graphics.drawLine(n5, n2, n5, n6);
            graphics.setColor(color.brighter());
            graphics.drawLine(n + 1, n2 + 1, n + 1, n6 - 2);
            graphics.drawLine(n + 1, n2 + 1, n5 - 2, n2 + 1);
            graphics.setColor(color.darker());
            graphics.drawLine(n5 - 1, n6 - 1, n5 - 1, n2 + 1);
            graphics.drawLine(n5 - 1, n6 - 1, n + 1, n6 - 1);
        }
        else {
            graphics.setColor(color.darker());
            graphics.fillRect(n, n2, n3 + 1, n4 + 1);
            graphics.setColor(color);
            graphics.fillRect(n + 1, n2 + 1, n3 - 2, n4 - 2);
        }
    }
    
    private void drawArrowHead(final int n, final int n2, final Graphics graphics, final Color color, final int n3, final boolean b) {
        final int n4 = n + 13;
        final int n5 = n2 + 13;
        this.fill3DEmbossedRect(n, n2, 13, 13, b, graphics, color);
        graphics.setColor(Color.black);
        switch (n3) {
            case 3: {
                graphics.drawLine(n + 4, n2 + 5, n + 8, n2 + 5);
                graphics.drawLine(n + 5, n2 + 6, n + 7, n2 + 6);
                graphics.drawLine(n + 6, n2 + 7, n + 6, n2 + 7);
                break;
            }
            case 0: {
                graphics.drawLine(n + 4, n5 - 5, n + 8, n5 - 5);
                graphics.drawLine(n + 5, n5 - 6, n + 7, n5 - 6);
                graphics.drawLine(n + 6, n5 - 7, n + 6, n5 - 7);
                break;
            }
            case 1: {
                graphics.drawLine(n4 - 5, n2 + 4, n4 - 5, n2 + 8);
                graphics.drawLine(n4 - 6, n2 + 5, n4 - 6, n2 + 7);
                graphics.drawLine(n4 - 7, n2 + 6, n4 - 7, n2 + 6);
                break;
            }
            case 2: {
                graphics.drawLine(n + 5, n2 + 4, n + 5, n2 + 8);
                graphics.drawLine(n + 6, n2 + 5, n + 6, n2 + 7);
                graphics.drawLine(n + 7, n2 + 6, n + 7, n2 + 6);
                break;
            }
        }
    }
    
    public void run() {
        if (this.doWait) {
            try {
                final Thread thread = this.thread;
                Thread.sleep(250L);
            }
            catch (InterruptedException ex) {
                return;
            }
        }
        this.doWait = false;
        while (this.doScroll) {
            try {
                final Thread thread2 = this.thread;
                Thread.sleep(80L);
            }
            catch (InterruptedException ex2) {}
            if (this.thread_state == 1) {
                synchronized (this) {
                    this.value -= this.line_increment;
                    if (this.value < this.minimum) {
                        this.value = this.minimum;
                    }
                    this.top_up = false;
                    this.repaint();
                    this.postEvent(new Event(this, 601, null));
                }
            }
            else {
                if (this.thread_state != 2) {
                    continue;
                }
                synchronized (this) {
                    this.value += this.line_increment;
                    if (this.value > this.maximum) {
                        this.value = this.maximum;
                    }
                    this.bottom_up = false;
                    this.repaint();
                    this.postEvent(new Event(this, 602, null));
                }
            }
        }
    }
    
    public void start() {
        this.doScroll = true;
        this.doWait = true;
        if (this.thread != null && this.thread.isAlive()) {
            try {
                this.thread.resume();
            }
            catch (Exception ex) {}
        }
        else {
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void startthread() {
    }
    
    public void stop() {
        this.thread_state = 0;
        this.stopthread();
    }
    
    private void stopthread() {
        this.doScroll = false;
        if (this.doWait && this.thread != null) {
            if (this.BrowserVendor.indexOf("netscape") != -1) {
                try {
                    this.thread.interrupt();
                }
                catch (Exception ex) {}
            }
            else {
                try {
                    this.thread.suspend();
                }
                catch (Exception ex2) {}
            }
        }
        this.doWait = false;
    }
    
    public void setDirection(final int direction) {
        this.direction = direction;
    }
    
    static {
        up = new char[] { '^' };
        down = new char[] { 'v' };
    }
}
