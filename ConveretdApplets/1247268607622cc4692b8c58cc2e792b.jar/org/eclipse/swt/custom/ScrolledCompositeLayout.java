// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

class ScrolledCompositeLayout extends Layout
{
    boolean inLayout;
    static final int DEFAULT_WIDTH = 64;
    static final int DEFAULT_HEIGHT = 64;
    
    ScrolledCompositeLayout() {
        this.inLayout = false;
    }
    
    protected Point computeSize(final Composite composite, final int x, final int y, final boolean b) {
        final ScrolledComposite scrolledComposite = (ScrolledComposite)composite;
        final Point point = new Point(64, 64);
        if (scrolledComposite.content != null) {
            final Point computeSize = scrolledComposite.content.computeSize(x, y, b);
            final Point size = scrolledComposite.content.getSize();
            point.x = (scrolledComposite.getExpandHorizontal() ? computeSize.x : size.x);
            point.y = (scrolledComposite.getExpandVertical() ? computeSize.y : size.y);
        }
        point.x = Math.max(point.x, scrolledComposite.minWidth);
        point.y = Math.max(point.y, scrolledComposite.minHeight);
        if (x != -1) {
            point.x = x;
        }
        if (y != -1) {
            point.y = y;
        }
        return point;
    }
    
    protected boolean flushCache(final Control control) {
        return true;
    }
    
    protected void layout(final Composite composite, final boolean b) {
        if (this.inLayout) {
            return;
        }
        final ScrolledComposite scrolledComposite = (ScrolledComposite)composite;
        if (scrolledComposite.content == null) {
            return;
        }
        final ScrollBar horizontalBar = scrolledComposite.getHorizontalBar();
        final ScrollBar verticalBar = scrolledComposite.getVerticalBar();
        if (horizontalBar != null && horizontalBar.getSize().y >= scrolledComposite.getSize().y) {
            return;
        }
        if (verticalBar != null && verticalBar.getSize().x >= scrolledComposite.getSize().x) {
            return;
        }
        this.inLayout = true;
        final Rectangle bounds = scrolledComposite.content.getBounds();
        if (!scrolledComposite.alwaysShowScroll) {
            boolean visible = scrolledComposite.needHScroll(bounds, false);
            final boolean needVScroll = scrolledComposite.needVScroll(bounds, visible);
            if (!visible && needVScroll) {
                visible = scrolledComposite.needHScroll(bounds, needVScroll);
            }
            if (horizontalBar != null) {
                horizontalBar.setVisible(visible);
            }
            if (verticalBar != null) {
                verticalBar.setVisible(needVScroll);
            }
        }
        final Rectangle clientArea = scrolledComposite.getClientArea();
        if (scrolledComposite.expandHorizontal) {
            bounds.width = Math.max(scrolledComposite.minWidth, clientArea.width);
        }
        if (scrolledComposite.expandVertical) {
            bounds.height = Math.max(scrolledComposite.minHeight, clientArea.height);
        }
        if (horizontalBar != null) {
            horizontalBar.setMaximum(bounds.width);
            horizontalBar.setThumb(Math.min(bounds.width, clientArea.width));
            final int n = bounds.width - clientArea.width;
            int selection = horizontalBar.getSelection();
            if (selection >= n) {
                if (n <= 0) {
                    selection = 0;
                    horizontalBar.setSelection(0);
                }
                bounds.x = -selection;
            }
        }
        if (verticalBar != null) {
            verticalBar.setMaximum(bounds.height);
            verticalBar.setThumb(Math.min(bounds.height, clientArea.height));
            final int n2 = bounds.height - clientArea.height;
            int selection2 = verticalBar.getSelection();
            if (selection2 >= n2) {
                if (n2 <= 0) {
                    selection2 = 0;
                    verticalBar.setSelection(0);
                }
                bounds.y = -selection2;
            }
        }
        scrolledComposite.content.setBounds(bounds);
        this.inLayout = false;
    }
}
