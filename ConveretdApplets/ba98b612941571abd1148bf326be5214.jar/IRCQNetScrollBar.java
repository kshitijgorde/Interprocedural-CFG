import java.awt.Dimension;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class IRCQNetScrollBar extends Canvas
{
    int lineIncrement;
    int pageIncrement;
    int maximum;
    int minimum;
    int value;
    int visible;
    int arrowHeight;
    boolean topArrow;
    boolean botArrow;
    int knobY;
    int oldKnobY;
    int knobHeight;
    int dragY;
    int lastX;
    int lastY;
    Image offScreenImage;
    Graphics offScreenGraphics;
    int width;
    int height;
    static final int NONE = 0;
    static final int UP = 1;
    static final int DOWN = 2;
    static final int DRAGGING = 3;
    int mode;
    
    public IRCQNetScrollBar() {
        this(0, 1, 0, 0);
    }
    
    public IRCQNetScrollBar(final int n, final int n2, final int n3, final int n4) {
        this.lineIncrement = 1;
        this.pageIncrement = 10;
        this.visible = 1;
        this.arrowHeight = 16;
        this.topArrow = true;
        this.botArrow = true;
        this.mode = 1;
        this.setValues(n, n2, n3, n4);
        this.setForeground(Color.lightGray);
        this.setBackground(Color.black);
    }
    
    public int getMaximum() {
        return this.maximum;
    }
    
    public int getMinimum() {
        return this.minimum;
    }
    
    public int getValue() {
        return this.value;
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
    
    public void setValues(int value, int n, final int minimum, int maximum) {
        if (maximum < minimum) {
            maximum = minimum;
        }
        this.minimum = minimum;
        this.maximum = maximum;
        if (n < 0) {
            n = 0;
        }
        this.visible = n;
        this.pageIncrement = n;
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
        final int width = this.size().width - 1;
        final int height = this.size().height;
        if (height <= 0 || width <= 0) {
            return;
        }
        if (!this.isVisible()) {
            return;
        }
        if (this.offScreenImage == null || height != this.height) {
            this.offScreenImage = this.createImage(width, height);
            this.offScreenGraphics = this.offScreenImage.getGraphics();
        }
        this.width = width;
        this.height = height;
        final Color background = this.getBackground();
        final Color foreground = this.getForeground();
        if (background.equals(foreground)) {
            background.brighter().brighter();
        }
        this.offScreenGraphics.setColor(new Color(234, 234, 234));
        this.offScreenGraphics.fillRect(0, 0, width, height);
        this.offScreenGraphics.setColor(foreground);
        IRCQNetUtils.fill3DRect(this.offScreenGraphics, 0, 0, width, this.arrowHeight, this.topArrow);
        IRCQNetUtils.fill3DRect(this.offScreenGraphics, 0, height - this.arrowHeight, width, this.arrowHeight, this.botArrow);
        this.drawArrow(this.offScreenGraphics, Color.black, 0, 0, width, this.arrowHeight, 1);
        this.drawArrow(this.offScreenGraphics, Color.black, 0, height - this.arrowHeight, width, this.arrowHeight, 2);
        final int n = this.maximum - this.minimum;
        final int knobHeight = height - 2 * this.arrowHeight;
        this.offScreenGraphics.setColor(foreground);
        if (n > this.visible) {
            this.knobY = this.arrowHeight + (this.value - this.minimum) * knobHeight / n;
            this.knobHeight = this.visible * knobHeight / n;
        }
        else {
            this.knobY = this.arrowHeight;
            this.knobHeight = knobHeight;
        }
        if (this.knobHeight < 10) {
            this.knobHeight = 10;
        }
        if (this.knobY > height - this.arrowHeight - this.knobHeight) {
            this.knobY = height - this.arrowHeight - this.knobHeight;
        }
        if (this.knobY < this.arrowHeight) {
            this.knobY = this.arrowHeight;
        }
        IRCQNetUtils.fill3DRect(this.offScreenGraphics, 0, this.knobY, width, this.knobHeight, true);
        graphics.drawImage(this.offScreenImage, 0, 0, this);
        graphics.setColor(Color.white);
        graphics.drawLine(width, 0, width, height);
    }
    
    void drawArrow(final Graphics graphics, final Color color, final int n, final int n2, final int n3, final int n4, final int n5) {
        final int n6 = n3 / 2;
        final int n7 = n4 / 2;
        final int n8 = n + n6;
        final int n9 = n2 + n7;
        graphics.setColor(color);
        if (n5 == 1) {
            final int[] array = { n8 - 3, n8 + 3, n8 };
            final int[] array2 = { n9 + 1, n9 + 1, n9 - 2 };
            graphics.fillPolygon(array, array2, 3);
            graphics.drawPolygon(array, array2, 3);
            return;
        }
        final int[] array3 = { n8 - 3, n8 + 3, n8 };
        final int[] array4 = { n9 - 2, n9 - 2, n9 + 1 };
        graphics.fillPolygon(array3, array4, 3);
        graphics.drawPolygon(array3, array4, 3);
    }
    
    public boolean mouseDown(final Event event, final int lastX, final int n) {
        if (n < this.arrowHeight) {
            this.value -= this.lineIncrement;
            if (this.value < this.minimum) {
                this.value = this.minimum;
            }
            this.topArrow = false;
            this.repaint();
            this.postEvent(new Event(this, 601, new Integer(this.value)));
        }
        else if (n > this.size().height - this.arrowHeight) {
            this.value += this.lineIncrement;
            if (this.value > this.maximum) {
                this.value = this.maximum;
            }
            this.botArrow = false;
            this.repaint();
            this.postEvent(new Event(this, 602, new Integer(this.value)));
        }
        else if (n < this.knobY) {
            this.mode = 1;
            this.value -= this.pageIncrement;
            if (this.value < this.minimum) {
                this.value = this.minimum;
            }
            this.repaint();
            this.postEvent(new Event(this, 603, new Integer(this.value)));
        }
        else if (n >= this.knobY + this.knobHeight) {
            this.mode = 2;
            this.value += this.pageIncrement;
            if (this.value > this.maximum) {
                this.value = this.maximum;
            }
            this.repaint();
            this.postEvent(new Event(this, 604, new Integer(this.value)));
        }
        else {
            this.dragY = n;
            this.oldKnobY = this.knobY;
            this.mode = 3;
            this.repaint();
        }
        this.lastX = lastX;
        this.lastY = n;
        return true;
    }
    
    public boolean mouseUp(final Event event, final int lastX, final int lastY) {
        this.topArrow = true;
        this.botArrow = true;
        this.mode = 0;
        this.repaint();
        this.lastX = lastX;
        this.lastY = lastY;
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int lastX, final int lastY) {
        if (this.tArrow(lastX, lastY) != this.tArrow(this.lastX, this.lastY) || this.bArrow(lastX, lastY) != this.bArrow(this.lastX, this.lastY)) {
            this.repaint();
        }
        this.lastX = lastX;
        this.lastY = lastY;
        if (this.mode == 3) {
            int arrowHeight = lastY - this.dragY + this.oldKnobY;
            final int n = this.size().height - this.knobHeight - this.arrowHeight;
            if (n == this.arrowHeight) {
                return false;
            }
            if (arrowHeight < this.arrowHeight) {
                arrowHeight = this.arrowHeight;
            }
            else if (arrowHeight > n) {
                arrowHeight = n;
            }
            this.value = (arrowHeight - this.arrowHeight) * (this.maximum - this.minimum) / (n - this.arrowHeight) + this.minimum;
            this.repaint();
            this.postEvent(new Event(this, 605, new Integer(this.value)));
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int lastX, final int lastY) {
        this.lastX = lastX;
        this.lastY = lastY;
        if (this.tArrow(lastX, lastY) || this.bArrow(lastX, lastY)) {
            this.repaint();
        }
        return true;
    }
    
    public boolean mouseExit(final Event event, final int lastX, final int lastY) {
        this.lastX = lastX;
        this.lastY = lastY;
        this.topArrow = true;
        this.botArrow = true;
        if (this.tArrow(lastX, lastY) || this.bArrow(lastX, lastY)) {
            this.repaint();
        }
        return true;
    }
    
    boolean tArrow(final int n, final int n2) {
        return n >= 0 && n2 >= 0 && n < this.size().width && n2 < this.arrowHeight;
    }
    
    boolean bArrow(final int n, final int n2) {
        return n >= 0 && n2 >= this.size().height - this.arrowHeight && n < this.size().width && n2 < this.size().height;
    }
    
    public Dimension preferredSize() {
        return new Dimension(17, 100);
    }
    
    public Dimension minimumSize() {
        return new Dimension(17, 50);
    }
    
    public boolean lineUp() {
        this.value -= this.lineIncrement;
        if (this.value < this.minimum) {
            this.value = this.minimum;
        }
        this.topArrow = false;
        this.repaint();
        this.postEvent(new Event(this, 601, new Integer(this.value)));
        this.topArrow = true;
        this.botArrow = true;
        this.mode = 0;
        this.repaint();
        return true;
    }
    
    public boolean lineDown() {
        this.value += this.lineIncrement;
        if (this.value > this.maximum) {
            this.value = this.maximum;
        }
        this.botArrow = false;
        this.repaint();
        this.postEvent(new Event(this, 602, new Integer(this.value)));
        this.topArrow = true;
        this.botArrow = true;
        this.mode = 0;
        this.repaint();
        return true;
    }
    
    public boolean pageUp() {
        this.mode = 1;
        this.value -= this.pageIncrement;
        if (this.value < this.minimum) {
            this.value = this.minimum;
        }
        this.repaint();
        this.postEvent(new Event(this, 603, new Integer(this.value)));
        this.topArrow = true;
        this.botArrow = true;
        this.mode = 0;
        this.repaint();
        return true;
    }
    
    public boolean pageDown() {
        this.mode = 2;
        this.value += this.pageIncrement;
        if (this.value > this.maximum) {
            this.value = this.maximum;
        }
        this.repaint();
        this.postEvent(new Event(this, 604, new Integer(this.value)));
        this.topArrow = true;
        this.botArrow = true;
        this.mode = 0;
        this.repaint();
        return true;
    }
}
