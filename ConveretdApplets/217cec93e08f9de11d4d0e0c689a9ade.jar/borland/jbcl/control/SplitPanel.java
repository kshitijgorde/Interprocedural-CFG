// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.control;

import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.LayoutManager;
import java.awt.Color;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.Cursor;
import java.awt.Canvas;
import borland.jbcl.layout.PaneLayout;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import borland.jbcl.view.BeanPanel;

public class SplitPanel extends BeanPanel implements MouseListener, MouseMotionListener
{
    PaneLayout layout;
    Canvas paneLayoutDivider;
    Cursor cursor;
    boolean yChanges;
    int xDelta;
    int yDelta;
    private Rectangle dividerRect;
    private Rectangle dividerBounds;
    boolean isSizing;
    boolean mouseOverPanel;
    Component[] enabledComponents;
    
    public SplitPanel() {
        this.layout = new PaneLayout();
        this.paneLayoutDivider = new Canvas();
        this.isSizing = false;
        this.setFocusAware(this.mouseOverPanel = false);
        this.add(this.paneLayoutDivider);
        this.paneLayoutDivider.setVisible(false);
        this.paneLayoutDivider.setEnabled(false);
        this.setDividerColor(Color.black);
        this.layout.setGap(2);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        super.setLayout(this.layout);
    }
    
    public void setGap(final int gap) {
        this.layout.setGap(gap);
    }
    
    public int getGap() {
        return this.layout.getGap();
    }
    
    public void setDividerColor(final Color background) {
        this.paneLayoutDivider.setBackground(background);
    }
    
    public Color getDividerColor() {
        return this.paneLayoutDivider.getBackground();
    }
    
    public void setLayout(final LayoutManager layout) {
        if (layout instanceof PaneLayout) {
            this.layout = (PaneLayout)layout;
            super.setLayout(layout);
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (mouseEvent.getComponent() != this || !this.mouseOverPanel) {
            return;
        }
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        if (this.mouseOverPanel) {
            this.dividerRect = this.layout.getDividerRect(x, y);
        }
        if (this.dividerRect != null) {
            this.dividerBounds = this.layout.getDividerBounds();
            if (this.dividerRect.width > this.dividerRect.height) {
                this.yChanges = true;
                this.yDelta = y - this.dividerRect.y;
            }
            else {
                this.yChanges = false;
                this.xDelta = x - this.dividerRect.x;
            }
            this.isSizing = true;
            final Component[] components = this.getComponents();
            this.enabledComponents = new Component[components.length];
            for (int i = 0; i < components.length; ++i) {
                if (components[i].isEnabled()) {
                    this.enabledComponents[i] = components[i];
                    components[i].setEnabled(false);
                }
            }
            this.paneLayoutDivider.setBounds(this.dividerRect.x, this.dividerRect.y, this.dividerRect.width, this.dividerRect.height);
            this.paneLayoutDivider.setVisible(true);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        this.paneLayoutDivider.setVisible(false);
        if (this.isSizing) {
            this.isSizing = false;
            for (int i = 0; i < this.enabledComponents.length; ++i) {
                if (this.enabledComponents[i] != null) {
                    this.enabledComponents[i].setEnabled(true);
                }
            }
            this.setCursor(this.cursor);
        }
        this.validate();
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.mouseOverPanel = false;
        if (this.cursor != null && !this.isSizing) {
            this.setCursor(this.cursor);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
        this.mouseOverPanel = true;
        if (!this.isSizing) {
            this.cursor = this.getCursor();
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        this.isSizing = false;
        if (mouseEvent.getComponent() != this) {
            return;
        }
        final Rectangle dividerRect = this.layout.getDividerRect(mouseEvent.getX(), mouseEvent.getY());
        if (dividerRect != null) {
            if (dividerRect.width > dividerRect.height) {
                this.setCursor(Cursor.getPredefinedCursor(8));
            }
            else {
                this.setCursor(Cursor.getPredefinedCursor(11));
            }
        }
        else {
            this.setCursor(Cursor.getPredefinedCursor(0));
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.isSizing && this.mouseOverPanel) {
            int x = this.dividerRect.x;
            int y = this.dividerRect.y;
            if (this.yChanges) {
                y = mouseEvent.getY() - this.yDelta;
                if (y < this.dividerBounds.y) {
                    y = this.dividerBounds.y;
                }
                else if (y > this.dividerBounds.height + this.dividerBounds.y - 1) {
                    y = this.dividerBounds.height + this.dividerBounds.y - 1;
                }
            }
            else {
                x = mouseEvent.getX() - this.xDelta;
                if (x > this.dividerBounds.width + this.dividerBounds.x - 1) {
                    x = this.dividerBounds.width + this.dividerBounds.x - 1;
                }
                else if (x < this.dividerBounds.x) {
                    x = this.dividerBounds.x;
                }
            }
            this.layout.dragDivider(x, y);
            this.dividerRect.x = x;
            this.dividerRect.y = y;
            this.paneLayoutDivider.setLocation(x, y);
        }
    }
    
    public Dimension getPreferredSize() {
        final Dimension preferredSize = super.getPreferredSize();
        if (preferredSize.width == 10) {
            preferredSize.width = 100;
        }
        if (preferredSize.height == 10) {
            preferredSize.height = 100;
        }
        return preferredSize;
    }
}
