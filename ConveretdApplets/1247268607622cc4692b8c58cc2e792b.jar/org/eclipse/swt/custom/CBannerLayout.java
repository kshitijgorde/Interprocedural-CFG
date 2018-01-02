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

class CBannerLayout extends Layout
{
    protected Point computeSize(final Composite composite, final int n, final int n2, final boolean b) {
        final CBanner cBanner = (CBanner)composite;
        final Control left = cBanner.left;
        final Control right = cBanner.right;
        final Control bottom = cBanner.bottom;
        final boolean b2 = left != null && right != null;
        int n3 = n;
        Point computeChildSize = new Point(0, 0);
        if (bottom != null) {
            final int computeTrim = this.computeTrim(bottom);
            computeChildSize = this.computeChildSize(bottom, (n == -1) ? -1 : Math.max(0, n3 - computeTrim), -1, b);
        }
        Point computeChildSize2 = new Point(0, 0);
        if (right != null) {
            final int computeTrim2 = this.computeTrim(right);
            int max = -1;
            if (cBanner.rightWidth != -1) {
                int min = cBanner.rightWidth - computeTrim2;
                if (left != null) {
                    min = Math.min(min, n3 - cBanner.curve_width + 2 * cBanner.curve_indent - 10 - computeTrim2);
                }
                max = Math.max(0, min);
            }
            computeChildSize2 = this.computeChildSize(right, max, -1, b);
            if (n != -1) {
                n3 -= computeChildSize2.x + cBanner.curve_width - 2 * cBanner.curve_indent;
            }
        }
        Point computeChildSize3 = new Point(0, 0);
        if (left != null) {
            final int computeTrim3 = this.computeTrim(left);
            computeChildSize3 = this.computeChildSize(left, (n == -1) ? -1 : Math.max(0, n3 - computeTrim3), -1, b);
        }
        int n4 = computeChildSize3.x + computeChildSize2.x;
        int y = computeChildSize.y;
        if (bottom != null && (left != null || right != null)) {
            y += 3;
        }
        int n5;
        if (left != null) {
            if (right == null) {
                n5 = y + computeChildSize3.y;
            }
            else {
                n5 = y + Math.max(computeChildSize3.y, (cBanner.rightMinHeight == -1) ? computeChildSize2.y : cBanner.rightMinHeight);
            }
        }
        else {
            n5 = y + computeChildSize2.y;
        }
        if (b2) {
            n4 += cBanner.curve_width - 2 * cBanner.curve_indent;
            n5 += 7;
        }
        if (n != -1) {
            n4 = n;
        }
        if (n2 != -1) {
            n5 = n2;
        }
        return new Point(n4, n5);
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
        final CBanner cBanner = (CBanner)composite;
        final Control left = cBanner.left;
        final Control right = cBanner.right;
        final Control bottom = cBanner.bottom;
        final Point size = cBanner.getSize();
        final boolean b2 = left != null && right != null;
        int n = size.x - 2 * cBanner.getBorderWidth();
        int n2 = size.y - 2 * cBanner.getBorderWidth();
        Point computeChildSize = new Point(0, 0);
        if (bottom != null) {
            computeChildSize = this.computeChildSize(bottom, Math.max(0, n - this.computeTrim(bottom)), -1, b);
            n2 -= computeChildSize.y + 1 + 2;
        }
        if (b2) {
            n2 -= 7;
        }
        Math.max(0, n2);
        Point computeChildSize2 = new Point(0, 0);
        if (right != null) {
            final int computeTrim = this.computeTrim(right);
            int max = -1;
            if (cBanner.rightWidth != -1) {
                int min = cBanner.rightWidth - computeTrim;
                if (left != null) {
                    min = Math.min(min, n - cBanner.curve_width + 2 * cBanner.curve_indent - 10 - computeTrim);
                }
                max = Math.max(0, min);
            }
            computeChildSize2 = this.computeChildSize(right, max, -1, b);
            n -= computeChildSize2.x - cBanner.curve_indent + cBanner.curve_width - cBanner.curve_indent;
        }
        Point computeChildSize3 = new Point(0, 0);
        if (left != null) {
            computeChildSize3 = this.computeChildSize(left, Math.max(0, n - this.computeTrim(left)), -1, b);
        }
        int n3 = 0;
        int n4 = 0;
        final int curveStart = cBanner.curveStart;
        Rectangle bounds = null;
        Rectangle bounds2 = null;
        Rectangle bounds3 = null;
        if (bottom != null) {
            bounds3 = new Rectangle(n3, n4 + size.y - computeChildSize.y, computeChildSize.x, computeChildSize.y);
        }
        if (b2) {
            n4 += 4;
        }
        if (left != null) {
            bounds = new Rectangle(n3, n4, computeChildSize3.x, computeChildSize3.y);
            cBanner.curveStart = n3 + computeChildSize3.x - cBanner.curve_indent;
            n3 += computeChildSize3.x - cBanner.curve_indent + cBanner.curve_width - cBanner.curve_indent;
        }
        if (right != null) {
            if (left != null) {
                computeChildSize2.y = Math.max(computeChildSize3.y, (cBanner.rightMinHeight == -1) ? computeChildSize2.y : cBanner.rightMinHeight);
            }
            bounds2 = new Rectangle(n3, n4, computeChildSize2.x, computeChildSize2.y);
        }
        if (cBanner.curveStart < curveStart) {
            cBanner.redraw(cBanner.curveStart - 200, 0, curveStart + cBanner.curve_width - cBanner.curveStart + 200 + 5, size.y, false);
        }
        if (cBanner.curveStart > curveStart) {
            cBanner.redraw(curveStart - 200, 0, cBanner.curveStart + cBanner.curve_width - curveStart + 200 + 5, size.y, false);
        }
        cBanner.update();
        cBanner.curveRect = new Rectangle(cBanner.curveStart, 0, cBanner.curve_width, size.y);
        if (bounds3 != null) {
            bottom.setBounds(bounds3);
        }
        if (bounds2 != null) {
            right.setBounds(bounds2);
        }
        if (bounds != null) {
            left.setBounds(bounds);
        }
    }
}
