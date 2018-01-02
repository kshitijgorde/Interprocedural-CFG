import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class DecentScrollbar extends Canvas implements MouseListener, MouseMotionListener
{
    int value;
    int lo;
    int hi;
    DecentScrollbarListener listener;
    Color gray1;
    Color gray2;
    Color gray3;
    Color gray4;
    static final int tw = 8;
    boolean dragging;
    int thumbpos;
    int dragoffset;
    
    DecentScrollbar(final DecentScrollbarListener listener, final int value, final int lo, final int hi) {
        this.value = value;
        this.lo = lo;
        this.hi = hi;
        this.listener = listener;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.gray1 = new Color(104, 104, 104);
        this.gray2 = new Color(168, 168, 168);
        this.gray3 = new Color(192, 192, 192);
        this.gray4 = new Color(224, 224, 224);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(20, 20);
    }
    
    public void paint(final Graphics graphics) {
        final Dimension size = this.getSize();
        final int width = size.width;
        final int height = size.height;
        final int thumbpos = (this.value - this.lo) * (width - 2 - 8) / (this.hi - this.lo) + 1;
        this.thumbpos = thumbpos;
        final int n = thumbpos;
        graphics.setColor(this.gray2);
        graphics.fillRect(0, 0, width, height);
        graphics.setColor(this.gray3);
        graphics.fillRect(n, 2, 8, height - 4);
        graphics.setColor(this.gray4);
        graphics.drawLine(0, height - 1, width, height - 1);
        graphics.drawLine(width - 1, 1, width - 1, height - 1);
        graphics.drawLine(n, 1, n + 8 - 1, 1);
        graphics.drawLine(n, 1, n, height - 2);
        graphics.setColor(this.gray1);
        graphics.drawLine(0, 0, width - 1, 0);
        graphics.drawLine(0, 0, 0, height - 1);
        graphics.drawLine(n + 8 - 1, 2, n + 8 - 1, height - 2);
        graphics.drawLine(n + 1, height - 2, n + 8 - 1, height - 2);
    }
    
    int getValue() {
        return this.value;
    }
    
    boolean setValue(int value) {
        if (value < this.lo) {
            value = this.lo;
        }
        if (value > this.hi) {
            value = this.hi;
        }
        if (this.value == value) {
            return false;
        }
        this.value = value;
        this.repaint();
        return true;
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.thumbpos <= mouseEvent.getX() && this.thumbpos + 8 >= mouseEvent.getX()) {
            this.dragging = true;
            this.dragoffset = mouseEvent.getX() - this.thumbpos;
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.dragging) {
            this.listener.scrollbarFinished(this);
        }
        this.dragging = false;
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (!this.dragging) {
            return;
        }
        if (this.setValue((mouseEvent.getX() - this.dragoffset - 1) * (this.hi - this.lo) / (this.getSize().width - 2 - 8) + this.lo)) {
            this.listener.scrollbarValueChanged(this);
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
}
