// 
// Decompiled by Procyon v0.5.30
// 

package geracemenu;

import java.awt.Container;
import java.awt.Cursor;
import geracemenu.richtext.ClickableTextActionEvent;
import java.awt.AWTEvent;
import geracemenu.util.EventPoster;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;

public class MenuItemController implements MouseListener, MouseMotionListener
{
    private MenuItem mi;
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (!this.mi.isSelectable() || !this.mi.isClickable()) {
            return;
        }
        MenuItem menuItem;
        for (menuItem = this.mi; menuItem != null && (!(menuItem instanceof Menu) || !(menuItem.getParent() instanceof MenuPanel)); menuItem = menuItem.getParentItem()) {}
        if (menuItem != null) {
            EventPoster.postEvent(new LaunchEvent(menuItem.getParent()));
        }
        EventPoster.postEvent(new ClickableTextActionEvent(this.mi));
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        if (!this.mi.isSelectable()) {
            return;
        }
        this.mi.setCursor(Cursor.getPredefinedCursor(12));
        if (this.mi.getState()) {
            return;
        }
        Container container = this.mi.getTopLevelAncestor();
        if (!(container instanceof MenuItemGroup)) {
            container = this.mi.getTopLevelPanel();
        }
        if (container instanceof MenuItemGroup) {
            ((MenuItemGroup)container).itemSelectedNotify(this.mi);
        }
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        if (!this.mi.isSelectable()) {
            return;
        }
        this.mi.setCursor(Cursor.getPredefinedCursor(0));
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public MenuItemController(final MenuItem mi) {
        (this.mi = mi).addMouseListener(this);
        mi.addMouseMotionListener(this);
    }
}
