// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import java.awt.LayoutManager;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Point;
import java.awt.Component;
import java.awt.Panel;
import java.awt.Window;

public class PopupMenu extends Window implements MenuItemGroup
{
    private transient Panel itemboard;
    
    public PopupMenu append(final MenuItem menuItem) {
        this.itemboard.add("", menuItem);
        this.pack();
        return this;
    }
    
    public synchronized void setVisible(final boolean visible) {
        super.setVisible(visible);
    }
    
    public void show(final Component component, final int n, final int n2) {
        this.show(component, new Point(n, n2));
    }
    
    public synchronized void show(final Component component, final Point point) {
        if (this.isShowing()) {
            return;
        }
        final Point locationOnScreen = component.getLocationOnScreen();
        final Dimension size = this.getSize();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if (locationOnScreen.x + point.x + size.width > screenSize.width) {
            point.x = -size.width;
        }
        if (locationOnScreen.y + point.y + size.height > screenSize.height) {
            point.y = screenSize.height - locationOnScreen.y - point.y - size.height;
        }
        this.setLocation(locationOnScreen.x + point.x, locationOnScreen.y + point.y);
        this.setVisible(true);
    }
    
    public void itemSelectedNotify(final MenuItem menuItem) {
        final Component[] components = this.itemboard.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if (components[i] instanceof MenuItem && ((MenuItem)components[i]).getState()) {
                ((MenuItem)components[i]).setState(false);
                break;
            }
        }
        menuItem.setState(true);
    }
    
    public void clearItemsSelection() {
        final Component[] components = this.itemboard.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if (components[i] instanceof MenuItem && ((MenuItem)components[i]).getState()) {
                ((MenuItem)components[i]).setState(false);
                if (components[i] instanceof Menu) {
                    ((Menu)components[i]).getPopup().clearItemsSelection();
                }
                break;
            }
        }
        if (this.isShowing()) {
            this.setVisible(false);
        }
    }
    
    protected Window getTopWindow(Component parent) {
        while (parent != null) {
            if (parent instanceof Window) {
                return (Window)parent;
            }
            parent = parent.getParent();
        }
        return null;
    }
    
    private boolean containsMousePoint(final MouseEvent mouseEvent) {
        if (!this.isShowing()) {
            return false;
        }
        final Component component = (Component)mouseEvent.getSource();
        final Point point = mouseEvent.getPoint();
        final Point locationOnScreen = component.getLocationOnScreen();
        final Point locationOnScreen2 = this.getLocationOnScreen();
        return this.contains(locationOnScreen.x + point.x - locationOnScreen2.x, locationOnScreen.y + point.y - locationOnScreen2.y);
    }
    
    public PopupMenu(final Frame frame) {
        this(frame, null, Color.lightGray, true);
    }
    
    public PopupMenu(final Frame frame, final MenuItem[] array, final Color color, final boolean b) {
        super(frame);
        this.add("Center", this.itemboard = new Panel(new MenuLayout(2, new Insets(2, 2, 2, 2))));
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                this.itemboard.add("", array[i]);
            }
            if (b) {
                this.itemboard.add("", new V3DRect(color));
            }
            else {
                this.itemboard.add("", new VRect(color));
            }
        }
        this.pack();
    }
}
