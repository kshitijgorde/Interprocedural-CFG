// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.graphics.Cursor;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Composite;

public class CBanner extends Composite
{
    Control left;
    Control right;
    Control bottom;
    boolean simple;
    int[] curve;
    int curveStart;
    Rectangle curveRect;
    int curve_width;
    int curve_indent;
    int rightWidth;
    int rightMinWidth;
    int rightMinHeight;
    Cursor resizeCursor;
    boolean dragging;
    int rightDragDisplacement;
    Listener listener;
    static final int OFFSCREEN = -200;
    static final int BORDER_BOTTOM = 2;
    static final int BORDER_TOP = 3;
    static final int BORDER_STRIPE = 1;
    static final int CURVE_TAIL = 200;
    static final int BEZIER_RIGHT = 30;
    static final int BEZIER_LEFT = 30;
    static final int MIN_LEFT = 10;
    static int BORDER1;
    
    static {
        CBanner.BORDER1 = 20;
    }
    
    public CBanner(final Composite composite, final int n) {
        super(composite, checkStyle(n));
        this.simple = true;
        this.curve = new int[0];
        this.curveStart = 0;
        this.curveRect = new Rectangle(0, 0, 0, 0);
        this.curve_width = 5;
        this.curve_indent = -2;
        this.rightWidth = -1;
        this.rightMinWidth = 0;
        this.rightMinHeight = 0;
        this.dragging = false;
        this.rightDragDisplacement = 0;
        super.setLayout(new CBannerLayout());
        this.resizeCursor = this.getDisplay().getSystemCursor(9);
        this.listener = new Listener() {
            public void handleEvent(final Event event) {
                switch (event.type) {
                    case 12: {
                        CBanner.this.onDispose(event);
                        break;
                    }
                    case 3: {
                        CBanner.this.onMouseDown(event.x, event.y);
                        break;
                    }
                    case 7: {
                        CBanner.this.onMouseExit();
                        break;
                    }
                    case 5: {
                        CBanner.this.onMouseMove(event.x, event.y);
                        break;
                    }
                    case 4: {
                        CBanner.this.onMouseUp();
                        break;
                    }
                    case 9: {
                        CBanner.this.onPaint(event.gc);
                        break;
                    }
                    case 11: {
                        CBanner.this.onResize();
                        break;
                    }
                }
            }
        };
        final int[] array = { 12, 3, 7, 5, 4, 9, 11 };
        for (int i = 0; i < array.length; ++i) {
            this.addListener(array[i], this.listener);
        }
    }
    
    static int[] bezier(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        final double n10 = n;
        final double n11 = 3 * (n3 - n);
        final double n12 = 3 * (n + n5 - 2 * n3);
        final double n13 = n7 - n + 3 * n3 - 3 * n5;
        final double n14 = n2;
        final double n15 = 3 * (n4 - n2);
        final double n16 = 3 * (n2 + n6 - 2 * n4);
        final double n17 = n8 - n2 + 3 * n4 - 3 * n6;
        final int[] array = new int[2 * n9 + 2];
        for (int i = 0; i <= n9; ++i) {
            final double n18 = i / n9;
            array[2 * i] = (int)(n10 + n11 * n18 + n12 * n18 * n18 + n13 * n18 * n18 * n18);
            array[2 * i + 1] = (int)(n14 + n15 * n18 + n16 * n18 * n18 + n17 * n18 * n18 * n18);
        }
        return array;
    }
    
    static int checkStyle(final int n) {
        return 0;
    }
    
    public Control getBottom() {
        this.checkWidget();
        return this.bottom;
    }
    
    public Rectangle getClientArea() {
        return new Rectangle(0, 0, 0, 0);
    }
    
    public Control getLeft() {
        this.checkWidget();
        return this.left;
    }
    
    public Control getRight() {
        this.checkWidget();
        return this.right;
    }
    
    public Point getRightMinimumSize() {
        this.checkWidget();
        return new Point(this.rightMinWidth, this.rightMinHeight);
    }
    
    public int getRightWidth() {
        this.checkWidget();
        if (this.right == null) {
            return 0;
        }
        if (this.rightWidth == -1) {
            return this.right.computeSize(-1, -1, false).x;
        }
        return this.rightWidth;
    }
    
    public boolean getSimple() {
        this.checkWidget();
        return this.simple;
    }
    
    void onDispose(final Event event) {
        this.removeListener(12, this.listener);
        this.notifyListeners(12, event);
        event.type = 0;
        this.resizeCursor = null;
        this.left = null;
        this.right = null;
        this.bottom = null;
    }
    
    void onMouseDown(final int n, final int n2) {
        if (this.curveRect.contains(n, n2)) {
            this.dragging = true;
            this.rightDragDisplacement = this.curveStart - n + this.curve_width - this.curve_indent;
        }
    }
    
    void onMouseExit() {
        if (!this.dragging) {
            this.setCursor(null);
        }
    }
    
    void onMouseMove(final int n, final int n2) {
        if (!this.dragging) {
            if (this.curveRect.contains(n, n2)) {
                this.setCursor(this.resizeCursor);
            }
            else {
                this.setCursor(null);
            }
            return;
        }
        final Point size = this.getSize();
        if (n <= 0 || n >= size.x) {
            return;
        }
        this.rightWidth = Math.max(0, size.x - n - this.rightDragDisplacement);
        if (this.rightMinWidth == -1) {
            this.rightWidth = Math.max(this.right.computeSize(this.rightMinWidth, this.rightMinHeight).x, this.rightWidth);
        }
        else {
            this.rightWidth = Math.max(this.rightMinWidth, this.rightWidth);
        }
        this.layout(false);
    }
    
    void onMouseUp() {
        this.dragging = false;
    }
    
    void onPaint(final GC gc) {
        if (this.left == null && this.right == null) {
            return;
        }
        final Point size = this.getSize();
        final Color systemColor = this.getDisplay().getSystemColor(CBanner.BORDER1);
        if (this.bottom != null) {
            final int n = this.bottom.getBounds().y - 1 - 1;
            gc.setForeground(systemColor);
            gc.drawLine(0, n, size.x, n);
        }
        if (this.left == null || this.right == null) {
            return;
        }
        final int[] array = new int[this.curve.length + 6];
        int n2 = 0;
        final int curveStart = this.curveStart;
        array[n2++] = curveStart + 1;
        array[n2++] = size.y - 1;
        for (int i = 0; i < this.curve.length / 2; ++i) {
            array[n2++] = curveStart + this.curve[2 * i];
            array[n2++] = this.curve[2 * i + 1];
        }
        array[n2++] = curveStart + this.curve_width;
        array[n2++] = 0;
        array[n2++] = size.x;
        array[n2++] = 0;
        final Color background = this.getBackground();
        if (this.getDisplay().getDepth() >= 15) {
            final int[] array2 = new int[array.length];
            int n3 = 0;
            for (int j = 0; j < array.length / 2; ++j) {
                array2[n3] = array[n3++] - 1;
                array2[n3] = array[n3++];
            }
            final int[] array3 = new int[array.length];
            int n4 = 0;
            for (int k = 0; k < array.length / 2; ++k) {
                array3[n4] = array[n4++] + 1;
                array3[n4] = array[n4++];
            }
            final RGB rgb = systemColor.getRGB();
            final RGB rgb2 = background.getRGB();
            final Color foreground = new Color(this.getDisplay(), rgb.red + 3 * (rgb2.red - rgb.red) / 4, rgb.green + 3 * (rgb2.green - rgb.green) / 4, rgb.blue + 3 * (rgb2.blue - rgb.blue) / 4);
            gc.setForeground(foreground);
            gc.drawPolyline(array2);
            gc.drawPolyline(array3);
            foreground.dispose();
            final int max = Math.max(0, this.curveStart - 200);
            gc.setForeground(background);
            gc.setBackground(systemColor);
            gc.fillGradientRectangle(max, size.y - 1, this.curveStart - max + 1, 1, false);
        }
        else {
            final int max2 = Math.max(0, this.curveStart - 200);
            gc.setForeground(systemColor);
            gc.drawLine(max2, size.y - 1, this.curveStart + 1, size.y - 1);
        }
        gc.setForeground(systemColor);
        gc.drawPolyline(array);
    }
    
    void onResize() {
        this.updateCurve(this.getSize().y);
    }
    
    public void setBottom(final Control bottom) {
        this.checkWidget();
        if (bottom != null && bottom.getParent() != this) {
            SWT.error(5);
        }
        if (this.bottom != null && !this.bottom.isDisposed()) {
            final Point size = this.bottom.getSize();
            this.bottom.setLocation(-200 - size.x, -200 - size.y);
        }
        this.bottom = bottom;
        this.layout(false);
    }
    
    public void setLayout(final Layout layout) {
        this.checkWidget();
    }
    
    public void setLeft(final Control left) {
        this.checkWidget();
        if (left != null && left.getParent() != this) {
            SWT.error(5);
        }
        if (this.left != null && !this.left.isDisposed()) {
            final Point size = this.left.getSize();
            this.left.setLocation(-200 - size.x, -200 - size.y);
        }
        this.left = left;
        this.layout(false);
    }
    
    public void setRight(final Control right) {
        this.checkWidget();
        if (right != null && right.getParent() != this) {
            SWT.error(5);
        }
        if (this.right != null && !this.right.isDisposed()) {
            final Point size = this.right.getSize();
            this.right.setLocation(-200 - size.x, -200 - size.y);
        }
        this.right = right;
        this.layout(false);
    }
    
    public void setRightMinimumSize(final Point point) {
        this.checkWidget();
        if (point == null || point.x < -1 || point.y < -1) {
            SWT.error(5);
        }
        this.rightMinWidth = point.x;
        this.rightMinHeight = point.y;
        this.layout(false);
    }
    
    public void setRightWidth(final int rightWidth) {
        this.checkWidget();
        if (rightWidth < -1) {
            SWT.error(5);
        }
        this.rightWidth = rightWidth;
        this.layout(false);
    }
    
    public void setSimple(final boolean simple) {
        this.checkWidget();
        if (this.simple != simple) {
            this.simple = simple;
            if (simple) {
                this.curve_width = 5;
                this.curve_indent = -2;
            }
            else {
                this.curve_width = 50;
                this.curve_indent = 5;
            }
            this.updateCurve(this.getSize().y);
            this.layout(false);
            this.redraw();
        }
    }
    
    void updateCurve(final int n) {
        final int n2 = n - 1;
        if (this.simple) {
            this.curve = new int[] { 0, n2, 1, n2, 2, n2 - 1, 3, n2 - 2, 3, 2, 4, 1, 5, 0 };
        }
        else {
            this.curve = bezier(0, n2 + 1, 30, n2 + 1, this.curve_width - 30, 0, this.curve_width, 0, this.curve_width);
        }
    }
}
