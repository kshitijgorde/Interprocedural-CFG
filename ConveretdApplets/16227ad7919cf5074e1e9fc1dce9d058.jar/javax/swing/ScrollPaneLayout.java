// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.swing.plaf.UIResource;
import javax.swing.border.Border;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.Component;
import java.io.Serializable;
import java.awt.LayoutManager;

public class ScrollPaneLayout implements LayoutManager, ScrollPaneConstants, Serializable
{
    protected JViewport viewport;
    protected JScrollBar vsb;
    protected JScrollBar hsb;
    protected JViewport rowHead;
    protected JViewport colHead;
    protected Component lowerLeft;
    protected Component lowerRight;
    protected Component upperLeft;
    protected Component upperRight;
    protected int vsbPolicy;
    protected int hsbPolicy;
    
    public ScrollPaneLayout() {
        this.vsbPolicy = 20;
        this.hsbPolicy = 30;
    }
    
    public void addLayoutComponent(final String s, final Component component) {
        if (s.equals("VIEWPORT")) {
            this.viewport = (JViewport)this.addSingletonComponent(this.viewport, component);
        }
        else if (s.equals("VERTICAL_SCROLLBAR")) {
            this.vsb = (JScrollBar)this.addSingletonComponent(this.vsb, component);
        }
        else if (s.equals("HORIZONTAL_SCROLLBAR")) {
            this.hsb = (JScrollBar)this.addSingletonComponent(this.hsb, component);
        }
        else if (s.equals("ROW_HEADER")) {
            this.rowHead = (JViewport)this.addSingletonComponent(this.rowHead, component);
        }
        else if (s.equals("COLUMN_HEADER")) {
            this.colHead = (JViewport)this.addSingletonComponent(this.colHead, component);
        }
        else if (s.equals("LOWER_LEFT_CORNER")) {
            this.lowerLeft = this.addSingletonComponent(this.lowerLeft, component);
        }
        else if (s.equals("LOWER_RIGHT_CORNER")) {
            this.lowerRight = this.addSingletonComponent(this.lowerRight, component);
        }
        else if (s.equals("UPPER_LEFT_CORNER")) {
            this.upperLeft = this.addSingletonComponent(this.upperLeft, component);
        }
        else {
            if (!s.equals("UPPER_RIGHT_CORNER")) {
                throw new IllegalArgumentException("invalid layout key " + s);
            }
            this.upperRight = this.addSingletonComponent(this.upperRight, component);
        }
    }
    
    protected Component addSingletonComponent(final Component component, final Component component2) {
        if (component != null && component != component2) {
            component.getParent().remove(component);
        }
        return component2;
    }
    
    public JViewport getColumnHeader() {
        return this.colHead;
    }
    
    public Component getCorner(final String s) {
        if (s.equals("LOWER_LEFT_CORNER")) {
            return this.lowerLeft;
        }
        if (s.equals("LOWER_RIGHT_CORNER")) {
            return this.lowerRight;
        }
        if (s.equals("UPPER_LEFT_CORNER")) {
            return this.upperLeft;
        }
        if (s.equals("UPPER_RIGHT_CORNER")) {
            return this.upperRight;
        }
        return null;
    }
    
    public JScrollBar getHorizontalScrollBar() {
        return this.hsb;
    }
    
    public int getHorizontalScrollBarPolicy() {
        return this.hsbPolicy;
    }
    
    public JViewport getRowHeader() {
        return this.rowHead;
    }
    
    public JScrollBar getVerticalScrollBar() {
        return this.vsb;
    }
    
    public int getVerticalScrollBarPolicy() {
        return this.vsbPolicy;
    }
    
    public JViewport getViewport() {
        return this.viewport;
    }
    
    public Rectangle getViewportBorderBounds(final JScrollPane scrollPane) {
        return scrollPane.getViewportBorderBounds();
    }
    
    public void layoutContainer(final Container container) {
        final JScrollPane scrollPane = (JScrollPane)container;
        this.vsbPolicy = scrollPane.getVerticalScrollBarPolicy();
        this.hsbPolicy = scrollPane.getHorizontalScrollBarPolicy();
        final Rectangle bounds = new Rectangle(scrollPane.getSize());
        final Insets insets = container.getInsets();
        bounds.x = insets.left;
        bounds.y = insets.top;
        final Rectangle rectangle = bounds;
        rectangle.width -= insets.left + insets.right;
        final Rectangle rectangle2 = bounds;
        rectangle2.height -= insets.top + insets.bottom;
        final Rectangle bounds2 = new Rectangle(0, bounds.y, 0, 0);
        if (this.colHead != null && this.colHead.isVisible()) {
            final int height = this.colHead.getPreferredSize().height;
            bounds2.height = height;
            final Rectangle rectangle3 = bounds;
            rectangle3.y += height;
            final Rectangle rectangle4 = bounds;
            rectangle4.height -= height;
        }
        final Rectangle bounds3 = new Rectangle(bounds.x, 0, 0, 0);
        if (this.rowHead != null && this.rowHead.isVisible()) {
            final int width = this.rowHead.getPreferredSize().width;
            bounds3.width = width;
            final Rectangle rectangle5 = bounds;
            rectangle5.x += width;
            final Rectangle rectangle6 = bounds;
            rectangle6.width -= width;
        }
        final Border viewportBorder = scrollPane.getViewportBorder();
        Insets borderInsets;
        if (viewportBorder != null) {
            borderInsets = viewportBorder.getBorderInsets(container);
            final Rectangle rectangle7 = bounds;
            rectangle7.x += borderInsets.left;
            final Rectangle rectangle8 = bounds;
            rectangle8.y += borderInsets.top;
            final Rectangle rectangle9 = bounds;
            rectangle9.width -= borderInsets.left + borderInsets.right;
            final Rectangle rectangle10 = bounds;
            rectangle10.height -= borderInsets.top + borderInsets.bottom;
        }
        else {
            borderInsets = new Insets(0, 0, 0, 0);
        }
        bounds2.x = bounds.x;
        bounds3.y = bounds.y;
        final Component component = (this.viewport != null) ? this.viewport.getView() : null;
        final Dimension dimension = (component != null) ? component.getPreferredSize() : new Dimension(0, 0);
        final Dimension dimension2 = (this.viewport != null) ? this.viewport.toViewCoordinates(bounds.getSize()) : new Dimension(0, 0);
        boolean scrollableTracksViewportWidth = false;
        boolean scrollableTracksViewportHeight = false;
        if (component instanceof Scrollable) {
            final Scrollable scrollable = (Scrollable)component;
            scrollableTracksViewportWidth = scrollable.getScrollableTracksViewportWidth();
            scrollableTracksViewportHeight = scrollable.getScrollableTracksViewportHeight();
        }
        final Rectangle bounds4 = new Rectangle(0, bounds.y - borderInsets.top, 0, 0);
        boolean b = this.vsbPolicy == 22 || (this.vsbPolicy != 21 && !scrollableTracksViewportHeight && dimension.height > dimension2.height);
        if (this.vsb != null && b) {
            final int width2 = this.vsb.getPreferredSize().width;
            final Rectangle rectangle11 = bounds;
            rectangle11.width -= width2;
            bounds4.x = bounds.x + bounds.width + borderInsets.right;
            bounds4.width = width2;
        }
        final Rectangle bounds5 = new Rectangle(bounds.x - borderInsets.left, 0, 0, 0);
        final boolean b2 = this.hsbPolicy == 32 || (this.hsbPolicy != 31 && !scrollableTracksViewportWidth && dimension.width > dimension2.width);
        if (this.hsb != null && b2) {
            final int height2 = this.hsb.getPreferredSize().height;
            final Rectangle rectangle12 = bounds;
            rectangle12.height -= height2;
            bounds5.y = bounds.y + bounds.height + borderInsets.bottom;
            bounds5.height = height2;
            if (this.vsb != null && !b && this.vsbPolicy != 21) {
                b = (dimension.height > this.viewport.toViewCoordinates(bounds.getSize()).height);
                if (b) {
                    final int width3 = this.vsb.getPreferredSize().width;
                    final Rectangle rectangle13 = bounds;
                    rectangle13.width -= width3;
                    bounds4.x = bounds.x + bounds.width + borderInsets.right;
                    bounds4.width = width3;
                }
            }
        }
        bounds4.height = bounds.height + borderInsets.top + borderInsets.bottom;
        bounds5.width = bounds.width + borderInsets.left + borderInsets.right;
        bounds3.height = bounds.height;
        bounds2.width = bounds.width;
        if (this.viewport != null) {
            this.viewport.setBounds(bounds);
        }
        if (this.rowHead != null) {
            this.rowHead.setBounds(bounds3);
        }
        if (this.colHead != null) {
            this.colHead.setBounds(bounds2);
        }
        if (this.vsb != null) {
            if (b) {
                this.vsb.setVisible(true);
                this.vsb.setBounds(bounds4);
            }
            else {
                this.vsb.setVisible(false);
            }
        }
        if (this.hsb != null) {
            if (b2) {
                this.hsb.setVisible(true);
                this.hsb.setBounds(bounds5);
            }
            else {
                this.hsb.setVisible(false);
            }
        }
        if (this.lowerLeft != null) {
            this.lowerLeft.setBounds(bounds3.x, bounds5.y, bounds3.width, bounds5.height);
        }
        if (this.lowerRight != null) {
            this.lowerRight.setBounds(bounds4.x, bounds5.y, bounds4.width, bounds5.height);
        }
        if (this.upperLeft != null) {
            this.upperLeft.setBounds(bounds3.x, bounds2.y, bounds3.width, bounds2.height);
        }
        if (this.upperRight != null) {
            this.upperRight.setBounds(bounds4.x, bounds2.y, bounds4.width, bounds2.height);
        }
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        final JScrollPane scrollPane = (JScrollPane)container;
        this.vsbPolicy = scrollPane.getVerticalScrollBarPolicy();
        this.hsbPolicy = scrollPane.getHorizontalScrollBarPolicy();
        final Insets insets = container.getInsets();
        int n = insets.left + insets.right;
        int n2 = insets.top + insets.bottom;
        if (this.viewport != null) {
            final Dimension minimumSize = this.viewport.getMinimumSize();
            n += minimumSize.width;
            n2 += minimumSize.height;
        }
        final Border viewportBorder = scrollPane.getViewportBorder();
        if (viewportBorder != null) {
            final Insets borderInsets = viewportBorder.getBorderInsets(container);
            n += borderInsets.left + borderInsets.right;
            n2 += borderInsets.top + borderInsets.bottom;
        }
        if (this.rowHead != null && this.rowHead.isVisible()) {
            final Dimension minimumSize2 = this.rowHead.getMinimumSize();
            n += minimumSize2.width;
            n2 = Math.max(n2, minimumSize2.height);
        }
        if (this.colHead != null && this.colHead.isVisible()) {
            final Dimension minimumSize3 = this.colHead.getMinimumSize();
            n = Math.max(n, minimumSize3.width);
            n2 += minimumSize3.height;
        }
        if (this.vsb != null && this.vsbPolicy != 21) {
            final Dimension minimumSize4 = this.vsb.getMinimumSize();
            n += minimumSize4.width;
            n2 = Math.max(n2, minimumSize4.height);
        }
        if (this.hsb != null && this.hsbPolicy != 21) {
            final Dimension minimumSize5 = this.hsb.getMinimumSize();
            n = Math.max(n, minimumSize5.width);
            n2 += minimumSize5.height;
        }
        return new Dimension(n, n2);
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        final JScrollPane scrollPane = (JScrollPane)container;
        this.vsbPolicy = scrollPane.getVerticalScrollBarPolicy();
        this.hsbPolicy = scrollPane.getHorizontalScrollBarPolicy();
        final Insets insets = container.getInsets();
        int n = insets.left + insets.right;
        int n2 = insets.top + insets.bottom;
        Dimension preferredSize = null;
        Dimension viewSize = null;
        Object view = null;
        if (this.viewport != null) {
            preferredSize = this.viewport.getPreferredSize();
            viewSize = this.viewport.getViewSize();
            view = this.viewport.getView();
        }
        if (preferredSize != null) {
            n += preferredSize.width;
            n2 += preferredSize.height;
        }
        final Border viewportBorder = scrollPane.getViewportBorder();
        if (viewportBorder != null) {
            final Insets borderInsets = viewportBorder.getBorderInsets(container);
            n += borderInsets.left + borderInsets.right;
            n2 += borderInsets.top + borderInsets.bottom;
        }
        if (this.rowHead != null && this.rowHead.isVisible()) {
            n += this.rowHead.getPreferredSize().width;
        }
        if (this.colHead != null && this.colHead.isVisible()) {
            n2 += this.colHead.getPreferredSize().height;
        }
        if (this.vsb != null && this.vsbPolicy != 21) {
            if (this.vsbPolicy == 22) {
                n += this.vsb.getPreferredSize().width;
            }
            else if (viewSize != null && preferredSize != null) {
                boolean b = true;
                if (view instanceof Scrollable) {
                    b = (((Scrollable)view).getScrollableTracksViewportHeight() ^ true);
                }
                if (b && viewSize.height > preferredSize.height) {
                    n += this.vsb.getPreferredSize().width;
                }
            }
        }
        if (this.hsb != null && this.hsbPolicy != 31) {
            if (this.hsbPolicy == 32) {
                n2 += this.hsb.getPreferredSize().height;
            }
            else if (viewSize != null && preferredSize != null) {
                boolean b2 = true;
                if (view instanceof Scrollable) {
                    b2 = (((Scrollable)view).getScrollableTracksViewportWidth() ^ true);
                }
                if (b2 && viewSize.width > preferredSize.width) {
                    n2 += this.hsb.getPreferredSize().height;
                }
            }
        }
        return new Dimension(n, n2);
    }
    
    public void removeLayoutComponent(final Component component) {
        if (component == this.viewport) {
            this.viewport = null;
        }
        else if (component == this.vsb) {
            this.vsb = null;
        }
        else if (component == this.hsb) {
            this.hsb = null;
        }
        else if (component == this.rowHead) {
            this.rowHead = null;
        }
        else if (component == this.colHead) {
            this.colHead = null;
        }
        else if (component == this.lowerLeft) {
            this.lowerLeft = null;
        }
        else if (component == this.lowerRight) {
            this.lowerRight = null;
        }
        else if (component == this.upperLeft) {
            this.upperLeft = null;
        }
        else if (component == this.upperRight) {
            this.upperRight = null;
        }
    }
    
    public void setHorizontalScrollBarPolicy(final int hsbPolicy) {
        switch (hsbPolicy) {
            case 30:
            case 31:
            case 32: {
                this.hsbPolicy = hsbPolicy;
            }
            default: {
                throw new IllegalArgumentException("invalid horizontalScrollBarPolicy");
            }
        }
    }
    
    public void setVerticalScrollBarPolicy(final int vsbPolicy) {
        switch (vsbPolicy) {
            case 20:
            case 21:
            case 22: {
                this.vsbPolicy = vsbPolicy;
            }
            default: {
                throw new IllegalArgumentException("invalid verticalScrollBarPolicy");
            }
        }
    }
    
    public void syncWithScrollPane(final JScrollPane scrollPane) {
        this.viewport = scrollPane.getViewport();
        this.vsb = scrollPane.getVerticalScrollBar();
        this.hsb = scrollPane.getHorizontalScrollBar();
        this.rowHead = scrollPane.getRowHeader();
        this.colHead = scrollPane.getColumnHeader();
        this.lowerLeft = scrollPane.getCorner("LOWER_LEFT_CORNER");
        this.lowerRight = scrollPane.getCorner("LOWER_RIGHT_CORNER");
        this.upperLeft = scrollPane.getCorner("UPPER_LEFT_CORNER");
        this.upperRight = scrollPane.getCorner("UPPER_RIGHT_CORNER");
        this.vsbPolicy = scrollPane.getVerticalScrollBarPolicy();
        this.hsbPolicy = scrollPane.getHorizontalScrollBarPolicy();
    }
    
    public static class UIResource extends ScrollPaneLayout implements javax.swing.plaf.UIResource
    {
    }
}
