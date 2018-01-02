// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Scrollable;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

class ViewFormLayout extends Layout
{
    protected Point computeSize(final Composite composite, final int x, final int y, final boolean b) {
        final ViewForm viewForm = (ViewForm)composite;
        final Control topLeft = viewForm.topLeft;
        final Control topCenter = viewForm.topCenter;
        final Control topRight = viewForm.topRight;
        final Control content = viewForm.content;
        Point computeChildSize = new Point(0, 0);
        if (topLeft != null) {
            computeChildSize = this.computeChildSize(topLeft, -1, -1, b);
        }
        Point computeChildSize2 = new Point(0, 0);
        if (topCenter != null) {
            computeChildSize2 = this.computeChildSize(topCenter, -1, -1, b);
        }
        Point computeChildSize3 = new Point(0, 0);
        if (topRight != null) {
            computeChildSize3 = this.computeChildSize(topRight, -1, -1, b);
        }
        final Point point = new Point(0, 0);
        if (viewForm.separateTopCenter || (x != -1 && computeChildSize.x + computeChildSize2.x + computeChildSize3.x > x)) {
            point.x = computeChildSize.x + computeChildSize3.x;
            if (computeChildSize.x > 0 && computeChildSize3.x > 0) {
                final Point point2 = point;
                point2.x += viewForm.horizontalSpacing;
            }
            point.x = Math.max(computeChildSize2.x, point.x);
            point.y = Math.max(computeChildSize.y, computeChildSize3.y);
            if (topCenter != null) {
                final Point point3 = point;
                point3.y += computeChildSize2.y;
                if (topLeft != null || topRight != null) {
                    final Point point4 = point;
                    point4.y += viewForm.verticalSpacing;
                }
            }
        }
        else {
            point.x = computeChildSize.x + computeChildSize2.x + computeChildSize3.x;
            int n = -1;
            if (computeChildSize.x > 0) {
                ++n;
            }
            if (computeChildSize2.x > 0) {
                ++n;
            }
            if (computeChildSize3.x > 0) {
                ++n;
            }
            if (n > 0) {
                final Point point5 = point;
                point5.x += n * viewForm.horizontalSpacing;
            }
            point.y = Math.max(computeChildSize.y, Math.max(computeChildSize2.y, computeChildSize3.y));
        }
        if (content != null) {
            if (topLeft != null || topRight != null || topCenter != null) {
                final Point point6 = point;
                ++point6.y;
            }
            final Point point7 = new Point(0, 0);
            final Point computeChildSize4 = this.computeChildSize(content, -1, -1, b);
            point.x = Math.max(point.x, computeChildSize4.x);
            final Point point8 = point;
            point8.y += computeChildSize4.y;
            if (point.y > computeChildSize4.y) {
                final Point point9 = point;
                point9.y += viewForm.verticalSpacing;
            }
        }
        final Point point10 = point;
        point10.x += 2 * viewForm.marginWidth;
        final Point point11 = point;
        point11.y += 2 * viewForm.marginHeight;
        if (x != -1) {
            point.x = x;
        }
        if (y != -1) {
            point.y = y;
        }
        return point;
    }
    
    Point computeChildSize(final Control control, final int n, final int n2, final boolean b) {
        Object layoutData = control.getLayoutData();
        if (layoutData == null || !(layoutData instanceof CLayoutData)) {
            layoutData = new CLayoutData();
            control.setLayoutData(layoutData);
        }
        return ((CLayoutData)layoutData).computeSize(control, n, n2, b);
    }
    
    int computeTrim(final Control control) {
        if (control instanceof Scrollable) {
            return ((Scrollable)control).computeTrim(0, 0, 0, 0).width;
        }
        return control.getBorderWidth() * 2;
    }
    
    protected boolean flushCache(final Control control) {
        final Object layoutData = control.getLayoutData();
        if (layoutData != null && layoutData instanceof CLayoutData) {
            ((CLayoutData)layoutData).flushCache();
        }
        return true;
    }
    
    protected void layout(final Composite composite, final boolean b) {
        final ViewForm viewForm = (ViewForm)composite;
        final Control topLeft = viewForm.topLeft;
        final Control topCenter = viewForm.topCenter;
        final Control topRight = viewForm.topRight;
        final Control content = viewForm.content;
        final Rectangle clientArea = composite.getClientArea();
        Point computeChildSize = new Point(0, 0);
        if (topLeft != null && !topLeft.isDisposed()) {
            computeChildSize = this.computeChildSize(topLeft, -1, -1, b);
        }
        Point computeChildSize2 = new Point(0, 0);
        if (topCenter != null && !topCenter.isDisposed()) {
            computeChildSize2 = this.computeChildSize(topCenter, -1, -1, b);
        }
        Point computeChildSize3 = new Point(0, 0);
        if (topRight != null && !topRight.isDisposed()) {
            computeChildSize3 = this.computeChildSize(topRight, -1, -1, b);
        }
        int n = computeChildSize.x + computeChildSize2.x + computeChildSize3.x + 2 * viewForm.marginWidth + 2 * viewForm.highlight;
        int n2 = -1;
        if (computeChildSize.x > 0) {
            ++n2;
        }
        if (computeChildSize2.x > 0) {
            ++n2;
        }
        if (computeChildSize3.x > 0) {
            ++n2;
        }
        if (n2 > 0) {
            n += n2 * viewForm.horizontalSpacing;
        }
        int n3 = clientArea.x + clientArea.width - viewForm.marginWidth - viewForm.highlight;
        int separator = clientArea.y + viewForm.marginHeight + viewForm.highlight;
        int n4 = 0;
        if (viewForm.separateTopCenter || n > clientArea.width) {
            final int max = Math.max(computeChildSize3.y, computeChildSize.y);
            if (topRight != null && !topRight.isDisposed()) {
                n4 = 1;
                final int n5 = n3 - computeChildSize3.x;
                topRight.setBounds(n5, separator, computeChildSize3.x, max);
                n3 = n5 - viewForm.horizontalSpacing;
            }
            if (topLeft != null && !topLeft.isDisposed()) {
                n4 = 1;
                topLeft.setBounds(clientArea.x + viewForm.marginWidth + viewForm.highlight, separator, this.computeChildSize(topLeft, n3 - clientArea.x - viewForm.marginWidth - viewForm.highlight - this.computeTrim(topLeft), -1, false).x, max);
            }
            if (n4 != 0) {
                separator += max + viewForm.verticalSpacing;
            }
            if (topCenter != null && !topCenter.isDisposed()) {
                final Point computeChildSize4 = this.computeChildSize(topCenter, clientArea.width - 2 * viewForm.marginWidth - 2 * viewForm.highlight - this.computeTrim(topCenter), -1, false);
                if (computeChildSize4.x < computeChildSize2.x) {
                    computeChildSize2 = computeChildSize4;
                }
                topCenter.setBounds(clientArea.x + clientArea.width - viewForm.marginWidth - viewForm.highlight - computeChildSize2.x, separator, computeChildSize2.x, computeChildSize2.y);
                separator += computeChildSize2.y + viewForm.verticalSpacing;
            }
        }
        else {
            final int max2 = Math.max(computeChildSize3.y, Math.max(computeChildSize2.y, computeChildSize.y));
            if (topRight != null && !topRight.isDisposed()) {
                n4 = 1;
                final int n6 = n3 - computeChildSize3.x;
                topRight.setBounds(n6, separator, computeChildSize3.x, max2);
                n3 = n6 - viewForm.horizontalSpacing;
            }
            if (topCenter != null && !topCenter.isDisposed()) {
                n4 = 1;
                final int n7 = n3 - computeChildSize2.x;
                topCenter.setBounds(n7, separator, computeChildSize2.x, max2);
                n3 = n7 - viewForm.horizontalSpacing;
            }
            if (topLeft != null && !topLeft.isDisposed()) {
                n4 = 1;
                final Rectangle rectangle = (topLeft instanceof Composite) ? ((Composite)topLeft).computeTrim(0, 0, 0, 0) : new Rectangle(0, 0, 0, 0);
                topLeft.setBounds(clientArea.x + viewForm.marginWidth + viewForm.highlight, separator, this.computeChildSize(topLeft, n3 - clientArea.x - viewForm.marginWidth - viewForm.highlight - rectangle.width, max2 - rectangle.height, false).x, max2);
            }
            if (n4 != 0) {
                separator += max2 + viewForm.verticalSpacing;
            }
        }
        final int separator2 = viewForm.separator;
        viewForm.separator = -1;
        if (content != null && !content.isDisposed()) {
            if (topLeft != null || topRight != null || topCenter != null) {
                viewForm.separator = separator;
                ++separator;
            }
            content.setBounds(clientArea.x + viewForm.marginWidth + viewForm.highlight, separator, clientArea.width - 2 * viewForm.marginWidth - 2 * viewForm.highlight, clientArea.y + clientArea.height - separator - viewForm.marginHeight - viewForm.highlight);
        }
        if (separator2 != viewForm.separator) {
            int n8;
            int max3;
            if (separator2 == -1) {
                n8 = viewForm.separator;
                max3 = viewForm.separator + 1;
            }
            else if (viewForm.separator == -1) {
                n8 = separator2;
                max3 = separator2 + 1;
            }
            else {
                n8 = Math.min(viewForm.separator, separator2);
                max3 = Math.max(viewForm.separator, separator2);
            }
            viewForm.redraw(viewForm.borderLeft, n8, viewForm.getSize().x - viewForm.borderLeft - viewForm.borderRight, max3 - n8, false);
        }
    }
}
