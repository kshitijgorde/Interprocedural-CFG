// 
// Decompiled by Procyon v0.5.30
// 

package com.magelang.splitter;

import java.awt.Dimension;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.Point;
import java.awt.Insets;
import java.awt.Component;
import com.magelang.Colors;
import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Color;
import java.awt.Window;
import java.awt.Rectangle;
import java.awt.Cursor;
import java.awt.Panel;

public class SplitterBar extends Panel
{
    static final Cursor VERT_CURSOR;
    static final Cursor HORIZ_CURSOR;
    static final Cursor DEF_CURSOR;
    private int orientation;
    private boolean alreadyDrawn;
    private Rectangle originalBounds;
    private Window wBar;
    private boolean mouseInside;
    
    public int getOrientation() {
        return this.orientation;
    }
    
    void setOrientation(final int orientation) {
        this.orientation = orientation;
    }
    
    public SplitterBar() {
        this.orientation = 0;
        this.alreadyDrawn = false;
        this.originalBounds = null;
        this.mouseInside = false;
        this.setBackground(Color.lightGray);
        this.addMouseMotionListener(new SplitterBarMouseMotionListener(this));
        this.addMouseListener(new SplitterBarMouseListener(this));
    }
    
    public void paint(final Graphics graphics) {
        if (this.mouseInside) {
            graphics.setColor(Color.yellow);
        }
        else {
            graphics.setColor(Colors.lightSkyBlue3);
        }
        final Component[] components = this.getComponents();
        if (components != null && components.length > 0) {
            for (int i = 0; i < components.length; ++i) {
                if (components[i] instanceof SplitterSpace) {
                    final Rectangle bounds = components[i].getBounds();
                    if (this.orientation == 0) {
                        graphics.fill3DRect(bounds.x + 2, bounds.y + bounds.height / 2 - 1, bounds.width - 5, 3, true);
                    }
                    else {
                        graphics.fill3DRect(bounds.x + bounds.width / 2 - 1, bounds.y + 2, 3, bounds.y + bounds.height - 5, true);
                    }
                }
            }
        }
        else {
            final Rectangle bounds2 = this.getBounds();
            if (this.orientation == 0) {
                graphics.fill3DRect(2, bounds2.height / 2 - 1, bounds2.width - 5, 3, true);
            }
            else {
                graphics.fill3DRect(bounds2.width / 2 - 1, 2, 3, bounds2.height - 5, true);
            }
        }
    }
    
    public void swapOrientation() {
        this.setOrientation((this.getOrientation() != 1) ? 1 : 0);
    }
    
    private void checkOtherComponents() {
        Rectangle rectangle = this.getBounds();
        final Component[] components = this.getParent().getComponents();
        final Insets insets = this.getParent().getInsets();
        final Rectangle bounds = this.getParent().getBounds();
        int n;
        for (n = 0; n < components.length && components[n] != this; ++n) {}
        final int n2 = n;
        if (this.orientation == 0) {
            if (rectangle.y < this.originalBounds.y) {
                boolean b = false;
                for (int n3 = n - 1; !b && n3 > -1; --n3) {
                    if (components[n3] instanceof SplitterBar) {
                        final Rectangle bounds2 = components[n3].getBounds();
                        if (rectangle.y <= bounds2.y + bounds2.height) {
                            components[n3].setLocation(bounds2.x, rectangle.y - bounds2.height);
                            for (int i = n - 1; i > n3; --i) {
                                components[i].setVisible(false);
                            }
                            n = n3;
                            rectangle = components[n3].getBounds();
                        }
                        else {
                            b = true;
                        }
                    }
                }
                if (rectangle.y <= insets.top) {
                    final int n4 = rectangle.y - insets.top;
                    for (int j = n - 1; j > -1; --j) {
                        components[j].setVisible(false);
                    }
                    boolean visible = false;
                    for (int n5 = n; !visible && n5 <= n2; ++n5) {
                        if (components[n5] instanceof SplitterBar) {
                            final Point location2;
                            final Point location = location2 = components[n5].getLocation();
                            location2.y -= n4;
                            components[n5].setLocation(location);
                        }
                        else {
                            visible = components[n5].isVisible();
                        }
                    }
                }
                for (int n6 = n2 + 1; n6 < components.length && !components[n6].isVisible(); ++n6) {
                    components[n6].setVisible(true);
                }
            }
            else if (rectangle.y > this.originalBounds.y) {
                boolean b2 = false;
                for (int n7 = n + 1; !b2 && n7 < components.length; ++n7) {
                    if (components[n7] instanceof SplitterBar) {
                        final Rectangle bounds3 = components[n7].getBounds();
                        if (rectangle.y + rectangle.height >= bounds3.y) {
                            components[n7].setLocation(bounds3.x, rectangle.y + rectangle.height);
                            for (int k = n + 1; k < n7; ++k) {
                                components[k].setVisible(false);
                            }
                            n = n7;
                            rectangle = components[n7].getBounds();
                        }
                        else {
                            b2 = true;
                        }
                    }
                }
                if (rectangle.y + rectangle.height >= bounds.height - insets.bottom) {
                    final int n8 = rectangle.y + rectangle.height - (bounds.height - insets.bottom);
                    for (int l = n + 1; l < components.length; ++l) {
                        components[l].setVisible(false);
                    }
                    boolean visible2 = false;
                    for (int n9 = n; !visible2 && n9 >= n2; --n9) {
                        if (components[n9] instanceof SplitterBar) {
                            final Point location4;
                            final Point location3 = location4 = components[n9].getLocation();
                            location4.y -= n8;
                            components[n9].setLocation(location3);
                        }
                        else {
                            visible2 = components[n9].isVisible();
                        }
                    }
                }
                for (int n10 = n2 - 1; n10 > -1 && !components[n10].isVisible(); --n10) {
                    components[n10].setVisible(true);
                }
            }
        }
        else if (rectangle.x < this.originalBounds.x) {
            boolean b3 = false;
            for (int n11 = n - 1; !b3 && n11 > -1; --n11) {
                if (components[n11] instanceof SplitterBar) {
                    final Rectangle bounds4 = components[n11].getBounds();
                    if (rectangle.x <= bounds4.x + bounds4.width) {
                        components[n11].setLocation(rectangle.x - bounds4.width, bounds4.y);
                        for (int n12 = n - 1; n12 > n11; --n12) {
                            components[n12].setVisible(false);
                        }
                        n = n11;
                        rectangle = components[n11].getBounds();
                    }
                    else {
                        b3 = true;
                    }
                }
            }
            if (rectangle.x <= insets.left) {
                final int n13 = rectangle.x - insets.left;
                for (int n14 = n - 1; n14 > -1; --n14) {
                    components[n14].setVisible(false);
                }
                boolean visible3 = false;
                for (int n15 = n; !visible3 && n15 <= n2; ++n15) {
                    if (components[n15] instanceof SplitterBar) {
                        final Point location6;
                        final Point location5 = location6 = components[n15].getLocation();
                        location6.x -= n13;
                        components[n15].setLocation(location5);
                    }
                    else {
                        visible3 = components[n15].isVisible();
                    }
                }
            }
            for (int n16 = n2 + 1; n16 < components.length && !components[n16].isVisible(); ++n16) {
                components[n16].setVisible(true);
            }
        }
        else if (rectangle.x > this.originalBounds.x) {
            boolean b4 = false;
            for (int n17 = n + 1; !b4 && n17 < components.length; ++n17) {
                if (components[n17] instanceof SplitterBar) {
                    final Rectangle bounds5 = components[n17].getBounds();
                    if (rectangle.x + rectangle.width >= bounds5.x) {
                        components[n17].setLocation(rectangle.x + rectangle.width, bounds5.y);
                        for (int n18 = n + 1; n18 < n17; ++n18) {
                            components[n18].setVisible(false);
                        }
                        n = n17;
                        rectangle = components[n17].getBounds();
                    }
                    else {
                        b4 = true;
                    }
                }
            }
            if (rectangle.x + rectangle.width >= bounds.width - insets.right) {
                final int n19 = rectangle.x + rectangle.width - (bounds.width - insets.right);
                for (int n20 = n + 1; n20 < components.length; ++n20) {
                    components[n20].setVisible(false);
                }
                boolean visible4 = false;
                for (int n21 = n; !visible4 && n21 >= n2; --n21) {
                    if (components[n21] instanceof SplitterBar) {
                        final Point location8;
                        final Point location7 = location8 = components[n21].getLocation();
                        location8.x -= n19;
                        components[n21].setLocation(location7);
                    }
                    else {
                        visible4 = components[n21].isVisible();
                    }
                }
            }
            for (int n22 = n2 - 1; n22 > -1 && !components[n22].isVisible(); --n22) {
                components[n22].setVisible(true);
            }
        }
    }
    
    void mouseDrag(final MouseEvent mouseEvent) {
        if (SplitterLayout.dragee == null) {
            SplitterLayout.dragee = this;
        }
        else if (SplitterLayout.dragee != this) {
            return;
        }
        Container container = this.getParent();
        final Point locationOnScreen = container.getLocationOnScreen();
        while (container.getParent() != null) {
            container = container.getParent();
        }
        if (!this.alreadyDrawn) {
            this.originalBounds = this.getBounds();
            (this.wBar = new Window((Frame)container)).setBackground(this.getBackground().darker());
        }
        final Container parent = this.getParent();
        final Dimension size = parent.getSize();
        final Point locationOnScreen2 = this.getLocationOnScreen();
        final Insets insets = parent.getInsets();
        if (this.orientation == 0) {
            final Dimension dimension = size;
            dimension.width -= insets.right + insets.left;
        }
        else {
            final Dimension dimension2 = size;
            dimension2.height -= insets.top + insets.bottom;
        }
        final Rectangle bounds = this.getBounds();
        int n = locationOnScreen2.x + ((this.orientation == 1) ? mouseEvent.getX() : 0);
        int n2 = locationOnScreen2.y + ((this.orientation == 0) ? mouseEvent.getY() : 0);
        if (n < locationOnScreen.x + insets.left) {
            n = locationOnScreen.x + insets.left;
        }
        else if (this.orientation == 1 && n > locationOnScreen.x + size.width - bounds.width) {
            n = locationOnScreen.x + size.width - bounds.width;
        }
        if (n2 < locationOnScreen.y + insets.top) {
            n2 = locationOnScreen.y + insets.top;
        }
        else if (this.orientation == 0 && n2 > locationOnScreen.y + size.height - bounds.height) {
            n2 = locationOnScreen.y + size.height - bounds.height;
        }
        this.wBar.setBounds(n, n2, (this.orientation == 1) ? bounds.width : size.width, (this.orientation == 0) ? bounds.height : size.height);
        if (!this.alreadyDrawn) {
            this.wBar.setVisible(true);
            this.alreadyDrawn = true;
        }
    }
    
    void mouseEnter(final MouseEvent mouseEvent) {
        if (SplitterLayout.dragee != null) {
            return;
        }
        this.setCursor((this.orientation == 0) ? SplitterBar.VERT_CURSOR : SplitterBar.HORIZ_CURSOR);
        this.mouseInside = true;
        this.invalidate();
        this.validate();
        this.repaint();
    }
    
    static {
        VERT_CURSOR = new Cursor(8);
        HORIZ_CURSOR = new Cursor(11);
        DEF_CURSOR = new Cursor(0);
    }
    
    void mouseExit(final MouseEvent mouseEvent) {
        if (SplitterLayout.dragee != null) {
            return;
        }
        this.setCursor(SplitterBar.DEF_CURSOR);
        this.mouseInside = false;
        this.invalidate();
        this.validate();
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    void mouseRelease(final MouseEvent mouseEvent) {
        if (this.alreadyDrawn) {
            if (SplitterLayout.dragee != this) {
                return;
            }
            SplitterLayout.dragee = null;
            this.wBar.setVisible(false);
            this.wBar.dispose();
            this.wBar = null;
            this.alreadyDrawn = false;
            final Rectangle bounds;
            final Rectangle rectangle = bounds = this.getBounds();
            bounds.x += ((this.orientation == 1) ? mouseEvent.getX() : 0);
            final Rectangle rectangle2 = rectangle;
            rectangle2.y += ((this.orientation == 0) ? mouseEvent.getY() : 0);
            this.setLocation(rectangle.x, rectangle.y);
            this.setCursor(SplitterBar.DEF_CURSOR);
            this.checkOtherComponents();
            this.mouseInside = false;
            this.invalidate();
            this.getParent().validate();
            SplitterLayout.dragee = null;
        }
    }
}
