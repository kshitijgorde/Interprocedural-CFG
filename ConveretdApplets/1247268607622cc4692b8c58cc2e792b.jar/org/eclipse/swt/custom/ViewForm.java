// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;

public class ViewForm extends Composite
{
    public int marginWidth;
    public int marginHeight;
    public int horizontalSpacing;
    public int verticalSpacing;
    public static RGB borderInsideRGB;
    public static RGB borderMiddleRGB;
    public static RGB borderOutsideRGB;
    Control topLeft;
    Control topCenter;
    Control topRight;
    Control content;
    boolean separateTopCenter;
    boolean showBorder;
    int separator;
    int borderTop;
    int borderBottom;
    int borderLeft;
    int borderRight;
    int highlight;
    Point oldSize;
    Color selectionBackground;
    Listener listener;
    static final int OFFSCREEN = -200;
    static final int BORDER1_COLOR = 18;
    static final int SELECTION_BACKGROUND = 25;
    
    static {
        ViewForm.borderInsideRGB = new RGB(132, 130, 132);
        ViewForm.borderMiddleRGB = new RGB(143, 141, 138);
        ViewForm.borderOutsideRGB = new RGB(171, 168, 165);
    }
    
    public ViewForm(final Composite composite, final int n) {
        super(composite, checkStyle(n));
        this.marginWidth = 0;
        this.marginHeight = 0;
        this.horizontalSpacing = 1;
        this.verticalSpacing = 1;
        this.separateTopCenter = false;
        this.showBorder = false;
        this.separator = -1;
        this.borderTop = 0;
        this.borderBottom = 0;
        this.borderLeft = 0;
        this.borderRight = 0;
        this.highlight = 0;
        super.setLayout(new ViewFormLayout());
        this.setBorderVisible((n & 0x800) != 0x0);
        this.listener = new Listener() {
            public void handleEvent(final Event event) {
                switch (event.type) {
                    case 12: {
                        ViewForm.this.onDispose(event);
                        break;
                    }
                    case 9: {
                        ViewForm.this.onPaint(event.gc);
                        break;
                    }
                    case 11: {
                        ViewForm.this.onResize();
                        break;
                    }
                }
            }
        };
        final int[] array = { 12, 9, 11 };
        for (int i = 0; i < array.length; ++i) {
            this.addListener(array[i], this.listener);
        }
    }
    
    static int checkStyle(final int n) {
        return (n & 0x6800000) | 0x100000;
    }
    
    public Rectangle computeTrim(final int n, final int n2, final int n3, final int n4) {
        this.checkWidget();
        return new Rectangle(n - this.borderLeft - this.highlight, n2 - this.borderTop - this.highlight, n3 + this.borderLeft + this.borderRight + 2 * this.highlight, n4 + this.borderTop + this.borderBottom + 2 * this.highlight);
    }
    
    public Rectangle getClientArea() {
        this.checkWidget();
        final Rectangle clientArea;
        final Rectangle rectangle = clientArea = super.getClientArea();
        clientArea.x += this.borderLeft;
        final Rectangle rectangle2 = rectangle;
        rectangle2.y += this.borderTop;
        final Rectangle rectangle3 = rectangle;
        rectangle3.width -= this.borderLeft + this.borderRight;
        final Rectangle rectangle4 = rectangle;
        rectangle4.height -= this.borderTop + this.borderBottom;
        return rectangle;
    }
    
    public Control getContent() {
        return this.content;
    }
    
    public Control getTopCenter() {
        return this.topCenter;
    }
    
    public Control getTopLeft() {
        return this.topLeft;
    }
    
    public Control getTopRight() {
        return this.topRight;
    }
    
    void onDispose(final Event event) {
        this.removeListener(12, this.listener);
        this.notifyListeners(12, event);
        event.type = 0;
        this.topLeft = null;
        this.topCenter = null;
        this.topRight = null;
        this.content = null;
        this.oldSize = null;
        this.selectionBackground = null;
    }
    
    void onPaint(final GC gc) {
        final Color foreground = gc.getForeground();
        final Point size = this.getSize();
        final Color systemColor = this.getDisplay().getSystemColor(18);
        if (this.showBorder) {
            gc.setForeground(systemColor);
            gc.drawRectangle(0, 0, size.x - 1, size.y - 1);
            if (this.highlight > 0) {
                final int n = 1;
                final int n2 = 1;
                final int n3 = size.x - 1;
                final int n4 = size.y - 1;
                final int[] array = { n, n2, n3, n2, n3, n4, n, n4, n, n2 + this.highlight, n + this.highlight, n2 + this.highlight, n + this.highlight, n4 - this.highlight, n3 - this.highlight, n4 - this.highlight, n3 - this.highlight, n2 + this.highlight, n, n2 + this.highlight };
                gc.setBackground(this.getDisplay().getSystemColor(26));
                gc.fillPolygon(array);
            }
        }
        if (this.separator > -1) {
            gc.setForeground(systemColor);
            gc.drawLine(this.borderLeft + this.highlight, this.separator, size.x - this.borderLeft - this.borderRight - this.highlight, this.separator);
        }
        gc.setForeground(foreground);
    }
    
    void onResize() {
        final Point size = this.getSize();
        if (this.oldSize == null || this.oldSize.x == 0 || this.oldSize.y == 0) {
            this.redraw();
        }
        else {
            int n = 0;
            if (this.oldSize.x < size.x) {
                n = size.x - this.oldSize.x + this.borderRight + this.highlight;
            }
            else if (this.oldSize.x > size.x) {
                n = this.borderRight + this.highlight;
            }
            this.redraw(size.x - n, 0, n, size.y, false);
            int n2 = 0;
            if (this.oldSize.y < size.y) {
                n2 = size.y - this.oldSize.y + this.borderBottom + this.highlight;
            }
            if (this.oldSize.y > size.y) {
                n2 = this.borderBottom + this.highlight;
            }
            this.redraw(0, size.y - n2, size.x, n2, false);
        }
        this.oldSize = size;
    }
    
    public void setContent(final Control content) {
        this.checkWidget();
        if (content != null && content.getParent() != this) {
            SWT.error(5);
        }
        if (this.content != null && !this.content.isDisposed()) {
            this.content.setBounds(-200, -200, 0, 0);
        }
        this.content = content;
        this.layout(false);
    }
    
    public void setLayout(final Layout layout) {
        this.checkWidget();
    }
    
    void setSelectionBackground(Color systemColor) {
        this.checkWidget();
        if (this.selectionBackground == systemColor) {
            return;
        }
        if (systemColor == null) {
            systemColor = this.getDisplay().getSystemColor(25);
        }
        this.selectionBackground = systemColor;
        this.redraw();
    }
    
    public void setTopCenter(final Control topCenter) {
        this.checkWidget();
        if (topCenter != null && topCenter.getParent() != this) {
            SWT.error(5);
        }
        if (this.topCenter != null && !this.topCenter.isDisposed()) {
            final Point size = this.topCenter.getSize();
            this.topCenter.setLocation(-200 - size.x, -200 - size.y);
        }
        this.topCenter = topCenter;
        this.layout(false);
    }
    
    public void setTopLeft(final Control topLeft) {
        this.checkWidget();
        if (topLeft != null && topLeft.getParent() != this) {
            SWT.error(5);
        }
        if (this.topLeft != null && !this.topLeft.isDisposed()) {
            final Point size = this.topLeft.getSize();
            this.topLeft.setLocation(-200 - size.x, -200 - size.y);
        }
        this.topLeft = topLeft;
        this.layout(false);
    }
    
    public void setTopRight(final Control topRight) {
        this.checkWidget();
        if (topRight != null && topRight.getParent() != this) {
            SWT.error(5);
        }
        if (this.topRight != null && !this.topRight.isDisposed()) {
            final Point size = this.topRight.getSize();
            this.topRight.setLocation(-200 - size.x, -200 - size.y);
        }
        this.topRight = topRight;
        this.layout(false);
    }
    
    public void setBorderVisible(final boolean showBorder) {
        this.checkWidget();
        if (this.showBorder == showBorder) {
            return;
        }
        this.showBorder = showBorder;
        if (this.showBorder) {
            final boolean b = true;
            this.borderBottom = (b ? 1 : 0);
            this.borderRight = (b ? 1 : 0);
            this.borderTop = (b ? 1 : 0);
            this.borderLeft = (b ? 1 : 0);
            if ((this.getStyle() & 0x800000) == 0x0) {
                this.highlight = 2;
            }
        }
        else {
            final boolean b2 = false;
            this.borderRight = (b2 ? 1 : 0);
            this.borderLeft = (b2 ? 1 : 0);
            this.borderTop = (b2 ? 1 : 0);
            this.borderBottom = (b2 ? 1 : 0);
            this.highlight = 0;
        }
        this.layout(false);
        this.redraw();
    }
    
    public void setTopCenterSeparate(final boolean separateTopCenter) {
        this.checkWidget();
        this.separateTopCenter = separateTopCenter;
        this.layout(false);
    }
}
