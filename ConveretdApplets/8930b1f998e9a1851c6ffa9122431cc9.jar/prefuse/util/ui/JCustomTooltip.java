// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util.ui;

import java.awt.event.MouseEvent;
import java.awt.Window;
import java.awt.event.MouseListener;
import javax.swing.PopupFactory;
import java.awt.event.MouseMotionListener;
import javax.swing.ToolTipManager;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.Popup;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Dimension;
import javax.swing.event.AncestorListener;
import java.awt.Component;
import javax.swing.BorderFactory;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.JToolTip;

public class JCustomTooltip extends JToolTip
{
    private boolean m_persist;
    private Listener m_lstnr;
    
    public JCustomTooltip(final JComponent component, final JComponent component2) {
        this(component, component2, false);
    }
    
    public JCustomTooltip(final JComponent component, final JComponent component2, final boolean persistent) {
        this.m_persist = false;
        this.m_lstnr = null;
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
        this.setComponent(component);
        this.add(component2);
        this.setPersistent(persistent);
    }
    
    public boolean isPersistent() {
        return this.m_persist;
    }
    
    public void setPersistent(final boolean persist) {
        if (persist == this.m_persist) {
            return;
        }
        if (persist) {
            this.addAncestorListener(this.m_lstnr = new Listener());
        }
        else {
            this.removeAncestorListener(this.m_lstnr);
            this.m_lstnr = null;
        }
        this.m_persist = persist;
    }
    
    public void setContent(final JComponent component) {
        this.removeAll();
        this.add(component);
    }
    
    public Dimension getPreferredSize() {
        if (this.getComponentCount() > 0) {
            final Dimension preferredSize = this.getComponent(0).getPreferredSize();
            final Insets insets = this.getInsets();
            return new Dimension(preferredSize.width + insets.left + insets.right, preferredSize.height + insets.top + insets.bottom);
        }
        return super.getPreferredSize();
    }
    
    public void paintComponent(final Graphics graphics) {
        if (this.getComponentCount() > 0) {
            graphics.setColor(this.getBackground());
            graphics.drawRect(0, 0, this.getWidth() - 1, this.getHeight() - 1);
            graphics.setColor(this.getComponent(0).getBackground());
            graphics.fillRect(1, 1, this.getWidth() - 2, this.getHeight() - 2);
        }
    }
    
    private class Listener extends MouseAdapter implements AncestorListener
    {
        private Point point;
        private boolean showing;
        private Popup popup;
        
        private Listener() {
            this.point = new Point();
            this.showing = false;
        }
        
        public void ancestorAdded(final AncestorEvent ancestorEvent) {
            if (this.showing) {
                return;
            }
            final Window windowAncestor = SwingUtilities.getWindowAncestor(JCustomTooltip.this.getParent());
            if (windowAncestor == null || !windowAncestor.isVisible()) {
                return;
            }
            windowAncestor.getLocation(this.point);
            windowAncestor.setVisible(false);
            JCustomTooltip.this.getParent().remove(JCustomTooltip.this);
            final JComponent component = JCustomTooltip.this.getComponent();
            component.setToolTipText(null);
            component.removeMouseMotionListener(ToolTipManager.sharedInstance());
            this.popup = PopupFactory.getSharedInstance().getPopup(component, JCustomTooltip.this, this.point.x, this.point.y);
            final Window windowAncestor2 = SwingUtilities.getWindowAncestor(JCustomTooltip.this);
            windowAncestor2.addMouseListener(this);
            windowAncestor2.setFocusableWindowState(true);
            this.popup.show();
            this.showing = true;
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            if (!this.showing) {
                return;
            }
            final int x = mouseEvent.getX();
            final int y = mouseEvent.getY();
            final Component component = (Component)mouseEvent.getSource();
            if (x < 0 || y < 0 || x > component.getWidth() || y > component.getHeight()) {
                final Window windowAncestor = SwingUtilities.getWindowAncestor(JCustomTooltip.this);
                windowAncestor.removeMouseListener(this);
                windowAncestor.setFocusableWindowState(false);
                this.popup.hide();
                this.popup = null;
                JCustomTooltip.this.getComponent().setToolTipText("?");
                this.showing = false;
            }
        }
        
        public void ancestorMoved(final AncestorEvent ancestorEvent) {
        }
        
        public void ancestorRemoved(final AncestorEvent ancestorEvent) {
        }
    }
}
