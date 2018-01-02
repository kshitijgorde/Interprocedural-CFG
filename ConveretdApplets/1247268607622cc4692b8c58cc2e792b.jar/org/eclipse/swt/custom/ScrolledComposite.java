// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.custom;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ScrollBar;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Layout;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Composite;

public class ScrolledComposite extends Composite
{
    Control content;
    Listener contentListener;
    Listener filter;
    int minHeight;
    int minWidth;
    boolean expandHorizontal;
    boolean expandVertical;
    boolean alwaysShowScroll;
    boolean showFocusedControl;
    
    public ScrolledComposite(final Composite composite, final int n) {
        super(composite, checkStyle(n));
        this.minHeight = 0;
        this.minWidth = 0;
        this.expandHorizontal = false;
        this.expandVertical = false;
        this.alwaysShowScroll = false;
        this.showFocusedControl = false;
        super.setLayout(new ScrolledCompositeLayout());
        final ScrollBar horizontalBar = this.getHorizontalBar();
        if (horizontalBar != null) {
            horizontalBar.setVisible(false);
            horizontalBar.addListener(13, new Listener() {
                public void handleEvent(final Event event) {
                    ScrolledComposite.this.hScroll();
                }
            });
        }
        final ScrollBar verticalBar = this.getVerticalBar();
        if (verticalBar != null) {
            verticalBar.setVisible(false);
            verticalBar.addListener(13, new Listener() {
                public void handleEvent(final Event event) {
                    ScrolledComposite.this.vScroll();
                }
            });
        }
        this.contentListener = new Listener() {
            public void handleEvent(final Event event) {
                if (event.type != 11) {
                    return;
                }
                ScrolledComposite.this.layout(false);
            }
        };
        this.filter = new Listener() {
            public void handleEvent(final Event event) {
                if (event.widget instanceof Control) {
                    final Control control = (Control)event.widget;
                    if (ScrolledComposite.this.contains(control)) {
                        ScrolledComposite.this.showControl(control);
                    }
                }
            }
        };
        this.addDisposeListener(new DisposeListener() {
            public void widgetDisposed(final DisposeEvent disposeEvent) {
                ScrolledComposite.this.getDisplay().removeFilter(15, ScrolledComposite.this.filter);
            }
        });
    }
    
    static int checkStyle(final int n) {
        return n & 0x6000B00;
    }
    
    boolean contains(final Control control) {
        if (control == null || control.isDisposed()) {
            return false;
        }
        for (Composite composite = control.getParent(); composite != null && !(composite instanceof Shell); composite = composite.getParent()) {
            if (this == composite) {
                return true;
            }
        }
        return false;
    }
    
    public boolean getAlwaysShowScrollBars() {
        return this.alwaysShowScroll;
    }
    
    public boolean getExpandHorizontal() {
        this.checkWidget();
        return this.expandHorizontal;
    }
    
    public boolean getExpandVertical() {
        this.checkWidget();
        return this.expandVertical;
    }
    
    public int getMinWidth() {
        this.checkWidget();
        return this.minWidth;
    }
    
    public int getMinHeight() {
        this.checkWidget();
        return this.minHeight;
    }
    
    public Control getContent() {
        return this.content;
    }
    
    public boolean getShowFocusedControl() {
        this.checkWidget();
        return this.showFocusedControl;
    }
    
    void hScroll() {
        if (this.content == null) {
            return;
        }
        this.content.setLocation(-this.getHorizontalBar().getSelection(), this.content.getLocation().y);
    }
    
    boolean needHScroll(final Rectangle rectangle, final boolean b) {
        if (this.getHorizontalBar() == null) {
            return false;
        }
        final Rectangle bounds = this.getBounds();
        final int borderWidth = this.getBorderWidth();
        final Rectangle rectangle2 = bounds;
        rectangle2.width -= 2 * borderWidth;
        final ScrollBar verticalBar = this.getVerticalBar();
        if (b && verticalBar != null) {
            final Rectangle rectangle3 = bounds;
            rectangle3.width -= verticalBar.getSize().x;
        }
        return (!this.expandHorizontal && rectangle.width > bounds.width) || (this.expandHorizontal && this.minWidth > bounds.width);
    }
    
    boolean needVScroll(final Rectangle rectangle, final boolean b) {
        if (this.getVerticalBar() == null) {
            return false;
        }
        final Rectangle bounds = this.getBounds();
        final int borderWidth = this.getBorderWidth();
        final Rectangle rectangle2 = bounds;
        rectangle2.height -= 2 * borderWidth;
        final ScrollBar horizontalBar = this.getHorizontalBar();
        if (b && horizontalBar != null) {
            final Rectangle rectangle3 = bounds;
            rectangle3.height -= horizontalBar.getSize().y;
        }
        return (!this.expandVertical && rectangle.height > bounds.height) || (this.expandVertical && this.minHeight > bounds.height);
    }
    
    public Point getOrigin() {
        this.checkWidget();
        if (this.content == null) {
            return new Point(0, 0);
        }
        final Point location = this.content.getLocation();
        return new Point(-location.x, -location.y);
    }
    
    public void setOrigin(final Point point) {
        this.setOrigin(point.x, point.y);
    }
    
    public void setOrigin(int selection, int selection2) {
        this.checkWidget();
        if (this.content == null) {
            return;
        }
        final ScrollBar horizontalBar = this.getHorizontalBar();
        if (horizontalBar != null) {
            horizontalBar.setSelection(selection);
            selection = -horizontalBar.getSelection();
        }
        else {
            selection = 0;
        }
        final ScrollBar verticalBar = this.getVerticalBar();
        if (verticalBar != null) {
            verticalBar.setSelection(selection2);
            selection2 = -verticalBar.getSelection();
        }
        else {
            selection2 = 0;
        }
        this.content.setLocation(selection, selection2);
    }
    
    public void setAlwaysShowScrollBars(final boolean alwaysShowScroll) {
        this.checkWidget();
        if (alwaysShowScroll == this.alwaysShowScroll) {
            return;
        }
        this.alwaysShowScroll = alwaysShowScroll;
        final ScrollBar horizontalBar = this.getHorizontalBar();
        if (horizontalBar != null && this.alwaysShowScroll) {
            horizontalBar.setVisible(true);
        }
        final ScrollBar verticalBar = this.getVerticalBar();
        if (verticalBar != null && this.alwaysShowScroll) {
            verticalBar.setVisible(true);
        }
        this.layout(false);
    }
    
    public void setContent(final Control content) {
        this.checkWidget();
        if (this.content != null && !this.content.isDisposed()) {
            this.content.removeListener(11, this.contentListener);
            this.content.setBounds(new Rectangle(-200, -200, 0, 0));
        }
        this.content = content;
        final ScrollBar verticalBar = this.getVerticalBar();
        final ScrollBar horizontalBar = this.getHorizontalBar();
        if (this.content != null) {
            if (verticalBar != null) {
                verticalBar.setMaximum(0);
                verticalBar.setThumb(0);
                verticalBar.setSelection(0);
            }
            if (horizontalBar != null) {
                horizontalBar.setMaximum(0);
                horizontalBar.setThumb(0);
                horizontalBar.setSelection(0);
            }
            content.setLocation(0, 0);
            this.layout(false);
            this.content.addListener(11, this.contentListener);
        }
        else {
            if (horizontalBar != null) {
                horizontalBar.setVisible(this.alwaysShowScroll);
            }
            if (verticalBar != null) {
                verticalBar.setVisible(this.alwaysShowScroll);
            }
        }
    }
    
    public void setExpandHorizontal(final boolean expandHorizontal) {
        this.checkWidget();
        if (expandHorizontal == this.expandHorizontal) {
            return;
        }
        this.expandHorizontal = expandHorizontal;
        this.layout(false);
    }
    
    public void setExpandVertical(final boolean expandVertical) {
        this.checkWidget();
        if (expandVertical == this.expandVertical) {
            return;
        }
        this.expandVertical = expandVertical;
        this.layout(false);
    }
    
    public void setLayout(final Layout layout) {
        this.checkWidget();
    }
    
    public void setMinHeight(final int n) {
        this.setMinSize(this.minWidth, n);
    }
    
    public void setMinSize(final Point point) {
        if (point == null) {
            this.setMinSize(0, 0);
        }
        else {
            this.setMinSize(point.x, point.y);
        }
    }
    
    public void setMinSize(final int n, final int n2) {
        this.checkWidget();
        if (n == this.minWidth && n2 == this.minHeight) {
            return;
        }
        this.minWidth = Math.max(0, n);
        this.minHeight = Math.max(0, n2);
        this.layout(false);
    }
    
    public void setMinWidth(final int n) {
        this.setMinSize(n, this.minHeight);
    }
    
    public void setShowFocusedControl(final boolean showFocusedControl) {
        this.checkWidget();
        if (this.showFocusedControl == showFocusedControl) {
            return;
        }
        final Display display = this.getDisplay();
        display.removeFilter(15, this.filter);
        if (!(this.showFocusedControl = showFocusedControl)) {
            return;
        }
        display.addFilter(15, this.filter);
        final Control focusControl = display.getFocusControl();
        if (this.contains(focusControl)) {
            this.showControl(focusControl);
        }
    }
    
    public void showControl(final Control control) {
        this.checkWidget();
        if (control == null) {
            SWT.error(4);
        }
        if (control.isDisposed()) {
            SWT.error(5);
        }
        if (!this.contains(control)) {
            SWT.error(5);
        }
        final Rectangle map = this.getDisplay().map(control.getParent(), this, control.getBounds());
        final Rectangle clientArea = this.getClientArea();
        final Point origin = this.getOrigin();
        if (map.x < 0) {
            origin.x = Math.max(0, origin.x + map.x);
        }
        else if (clientArea.width < map.x + map.width) {
            origin.x = Math.max(0, origin.x + map.x + Math.min(map.width, clientArea.width) - clientArea.width);
        }
        if (map.y < 0) {
            origin.y = Math.max(0, origin.y + map.y);
        }
        else if (clientArea.height < map.y + map.height) {
            origin.y = Math.max(0, origin.y + map.y + Math.min(map.height, clientArea.height) - clientArea.height);
        }
        this.setOrigin(origin);
    }
    
    void vScroll() {
        if (this.content == null) {
            return;
        }
        this.content.setLocation(this.content.getLocation().x, -this.getVerticalBar().getSelection());
    }
}
