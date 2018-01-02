// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.Point;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.io.Serializable;
import java.awt.LayoutManager;

public class ViewportLayout implements LayoutManager, Serializable
{
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    public void layoutContainer(final Container container) {
        final JViewport viewport = (JViewport)container;
        final Component view = viewport.getView();
        Scrollable scrollable = null;
        if (view == null) {
            return;
        }
        if (view instanceof Scrollable) {
            scrollable = (Scrollable)view;
        }
        viewport.getInsets();
        final Dimension preferredSize = view.getPreferredSize();
        final Dimension size = viewport.getSize();
        final Dimension viewCoordinates = viewport.toViewCoordinates(size);
        final Dimension viewSize = new Dimension(preferredSize);
        if (scrollable != null) {
            if (scrollable.getScrollableTracksViewportWidth()) {
                viewSize.width = size.width;
            }
            if (scrollable.getScrollableTracksViewportHeight()) {
                viewSize.height = size.height;
            }
        }
        final Point viewPosition = viewport.getViewPosition();
        if (viewPosition.x + viewCoordinates.width > viewSize.width) {
            viewPosition.x = Math.max(0, viewSize.width - viewCoordinates.width);
        }
        if (viewPosition.y + viewCoordinates.height > viewSize.height) {
            viewPosition.y = Math.max(0, viewSize.height - viewCoordinates.height);
        }
        if (scrollable == null) {
            if (viewPosition.x == 0 && size.width > preferredSize.width) {
                viewSize.width = size.width;
            }
            if (viewPosition.y == 0 && size.height > preferredSize.height) {
                viewSize.height = size.height;
            }
        }
        viewport.setViewPosition(viewPosition);
        viewport.setViewSize(viewSize);
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        return new Dimension(4, 4);
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        final Component view = ((JViewport)container).getView();
        if (view == null) {
            return new Dimension(0, 0);
        }
        if (view instanceof Scrollable) {
            return ((Scrollable)view).getPreferredScrollableViewportSize();
        }
        return view.getPreferredSize();
    }
    
    public void removeLayoutComponent(final Component component) {
    }
}
