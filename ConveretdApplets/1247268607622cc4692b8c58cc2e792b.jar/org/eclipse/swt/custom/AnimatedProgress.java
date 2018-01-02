// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Canvas;

public class AnimatedProgress extends Canvas
{
    static final int SLEEP = 70;
    static final int DEFAULT_WIDTH = 160;
    static final int DEFAULT_HEIGHT = 18;
    boolean active;
    boolean showStripes;
    int value;
    int orientation;
    boolean showBorder;
    
    public AnimatedProgress(final Composite composite, final int n) {
        super(composite, checkStyle(n));
        this.active = false;
        this.showStripes = false;
        this.orientation = 256;
        this.showBorder = false;
        if ((n & 0x200) != 0x0) {
            this.orientation = 512;
        }
        this.showBorder = ((n & 0x800) != 0x0);
        this.addControlListener(new ControlAdapter() {
            public void controlResized(final ControlEvent controlEvent) {
                AnimatedProgress.this.redraw();
            }
        });
        this.addPaintListener(new PaintListener() {
            public void paintControl(final PaintEvent paintEvent) {
                AnimatedProgress.this.paint(paintEvent);
            }
        });
        this.addDisposeListener(new DisposeListener() {
            public void widgetDisposed(final DisposeEvent disposeEvent) {
                AnimatedProgress.this.stop();
            }
        });
    }
    
    private static int checkStyle(final int n) {
        return n & 0x0;
    }
    
    public synchronized void clear() {
        this.checkWidget();
        if (this.active) {
            this.stop();
        }
        this.showStripes = false;
        this.redraw();
    }
    
    public Point computeSize(final int x, final int y, final boolean b) {
        this.checkWidget();
        Point point;
        if (this.orientation == 256) {
            point = new Point(160, 18);
        }
        else {
            point = new Point(18, 160);
        }
        if (x != -1) {
            point.x = x;
        }
        if (y != -1) {
            point.y = y;
        }
        return point;
    }
    
    private void drawBevelRect(final GC gc, final int n, final int n2, final int n3, final int n4, final Color foreground, final Color foreground2) {
        gc.setForeground(foreground);
        gc.drawLine(n, n2, n + n3 - 1, n2);
        gc.drawLine(n, n2, n, n2 + n4 - 1);
        gc.setForeground(foreground2);
        gc.drawLine(n + n3, n2, n + n3, n2 + n4);
        gc.drawLine(n, n2 + n4, n + n3, n2 + n4);
    }
    
    void paint(final PaintEvent paintEvent) {
        final GC gc = paintEvent.gc;
        final Display display = this.getDisplay();
        final Rectangle clientArea = this.getClientArea();
        gc.fillRectangle(clientArea);
        if (this.showBorder) {
            this.drawBevelRect(gc, clientArea.x, clientArea.y, clientArea.width - 1, clientArea.height - 1, display.getSystemColor(18), display.getSystemColor(20));
        }
        this.paintStripes(gc);
    }
    
    void paintStripes(final GC gc) {
        if (!this.showStripes) {
            return;
        }
        final Rectangle clientArea = this.getClientArea();
        final Rectangle clipping = new Rectangle(clientArea.x + 2, clientArea.y + 2, clientArea.width - 4, clientArea.height - 4);
        gc.setLineWidth(2);
        gc.setClipping(clipping);
        gc.setBackground(this.getDisplay().getSystemColor(26));
        gc.fillRectangle(clipping);
        gc.setForeground(this.getBackground());
        final int n = 12;
        final int n2 = (this.value == 0) ? (n - 2) : (this.value - 2);
        if (this.orientation == 256) {
            final int n3 = clipping.y - 1;
            final int width = clipping.width;
            final int n4 = clipping.height + 2;
            for (int i = 0; i < width; i += n) {
                final int n5 = i + n2;
                gc.drawLine(n5, n3, n5, n4);
            }
        }
        else {
            final int n6 = clipping.x - 1;
            final int n7 = clipping.width + 2;
            for (int height = clipping.height, j = 0; j < height; j += n) {
                final int n8 = j + n2;
                gc.drawLine(n6, n8, n7, n8);
            }
        }
        if (this.active) {
            this.value = (this.value + 2) % n;
        }
    }
    
    public synchronized void start() {
        this.checkWidget();
        if (this.active) {
            return;
        }
        this.active = true;
        this.showStripes = true;
        final Display display = this.getDisplay();
        final Runnable[] array = { null };
        display.timerExec(70, array[0] = new Runnable() {
            public void run() {
                if (!AnimatedProgress.this.active) {
                    return;
                }
                final GC gc = new GC(AnimatedProgress.this);
                AnimatedProgress.this.paintStripes(gc);
                gc.dispose();
                display.timerExec(70, array[0]);
            }
        });
    }
    
    public synchronized void stop() {
        this.active = false;
    }
}
