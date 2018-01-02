// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.Dimension;
import java.util.Locale;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Cursor;
import javax.accessibility.AccessibleStateSet;
import javax.accessibility.AccessibleRole;
import java.awt.Point;
import java.awt.event.FocusListener;
import javax.accessibility.AccessibleComponent;
import java.io.Serializable;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.LayoutManager;
import javax.accessibility.AccessibleContext;
import javax.accessibility.Accessible;
import java.awt.Container;

public class CellRendererPane extends Container implements Accessible
{
    protected AccessibleContext accessibleContext;
    
    public CellRendererPane() {
        this.accessibleContext = null;
        this.setLayout(null);
        this.setVisible(false);
    }
    
    protected void addImpl(final Component component, final Object o, final int n) {
        if (component.getParent() == this) {
            return;
        }
        super.addImpl(component, o, n);
    }
    
    public AccessibleContext getAccessibleContext() {
        if (this.accessibleContext == null) {
            this.accessibleContext = new AccessibleCellRendererPane();
        }
        return this.accessibleContext;
    }
    
    public void invalidate() {
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public void paintComponent(final Graphics graphics, final Component component, final Container container, final int n, final int n2, final int n3, final int n4) {
        this.paintComponent(graphics, component, container, n, n2, n3, n4, false);
    }
    
    public void paintComponent(final Graphics graphics, final Component component, final Container container, final int n, final int n2, final int n3, final int n4, final boolean b) {
        if (component == null) {
            if (container != null) {
                final Color color = graphics.getColor();
                graphics.setColor(container.getBackground());
                graphics.fillRect(n, n2, n3, n4);
                graphics.setColor(color);
            }
            return;
        }
        if (component.getParent() != this) {
            this.add(component);
        }
        component.setBounds(n, n2, n3, n4);
        if (b) {
            component.validate();
        }
        boolean doubleBuffered = false;
        if (component instanceof JComponent && ((JComponent)component).isDoubleBuffered()) {
            doubleBuffered = true;
            ((JComponent)component).setDoubleBuffered(false);
        }
        final Graphics swingGraphics = SwingGraphics.createSwingGraphics(graphics, n, n2, n3, n4);
        try {
            component.paint(swingGraphics);
        }
        finally {
            swingGraphics.dispose();
        }
        if (component instanceof JComponent && doubleBuffered) {
            ((JComponent)component).setDoubleBuffered(true);
        }
        if (component instanceof JComponent) {
            ((JComponent)component).setDoubleBuffered(doubleBuffered);
        }
        component.setBounds(-n3, -n4, 0, 0);
    }
    
    public void paintComponent(final Graphics graphics, final Component component, final Container container, final Rectangle rectangle) {
        this.paintComponent(graphics, component, container, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    public void update(final Graphics graphics) {
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        this.removeAll();
        objectOutputStream.defaultWriteObject();
    }
    
    protected class AccessibleCellRendererPane extends AccessibleContext implements Serializable, AccessibleComponent
    {
        public void addFocusListener(final FocusListener focusListener) {
            CellRendererPane.this.addFocusListener(focusListener);
        }
        
        public boolean contains(final Point point) {
            return CellRendererPane.this.contains(point);
        }
        
        public Accessible getAccessibleAt(final Point point) {
            return SwingUtilities.getAccessibleAt(CellRendererPane.this, point);
        }
        
        public Accessible getAccessibleChild(final int n) {
            return SwingUtilities.getAccessibleChild(CellRendererPane.this, n);
        }
        
        public int getAccessibleChildrenCount() {
            return SwingUtilities.getAccessibleChildrenCount(CellRendererPane.this);
        }
        
        public AccessibleComponent getAccessibleComponent() {
            return this;
        }
        
        public int getAccessibleIndexInParent() {
            return SwingUtilities.getAccessibleIndexInParent(CellRendererPane.this);
        }
        
        public Accessible getAccessibleParent() {
            if (super.accessibleParent != null) {
                return super.accessibleParent;
            }
            final Container parent = CellRendererPane.this.getParent();
            if (parent instanceof Accessible) {
                return (Accessible)parent;
            }
            return null;
        }
        
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.PANEL;
        }
        
        public AccessibleStateSet getAccessibleStateSet() {
            return SwingUtilities.getAccessibleStateSet(CellRendererPane.this);
        }
        
        public Color getBackground() {
            return CellRendererPane.this.getBackground();
        }
        
        public Rectangle getBounds() {
            return CellRendererPane.this.getBounds();
        }
        
        public Cursor getCursor() {
            return CellRendererPane.this.getCursor();
        }
        
        public Font getFont() {
            return CellRendererPane.this.getFont();
        }
        
        public FontMetrics getFontMetrics(final Font font) {
            return CellRendererPane.this.getFontMetrics(font);
        }
        
        public Color getForeground() {
            return CellRendererPane.this.getForeground();
        }
        
        public Locale getLocale() {
            return CellRendererPane.this.getLocale();
        }
        
        public Point getLocation() {
            return CellRendererPane.this.getLocation();
        }
        
        public Point getLocationOnScreen() {
            return CellRendererPane.this.getLocationOnScreen();
        }
        
        public Dimension getSize() {
            return CellRendererPane.this.getSize();
        }
        
        public boolean isEnabled() {
            return CellRendererPane.this.isEnabled();
        }
        
        public boolean isFocusTraversable() {
            return CellRendererPane.this.isFocusTraversable();
        }
        
        public boolean isShowing() {
            return CellRendererPane.this.isShowing();
        }
        
        public boolean isVisible() {
            return CellRendererPane.this.isVisible();
        }
        
        public void removeFocusListener(final FocusListener focusListener) {
            CellRendererPane.this.removeFocusListener(focusListener);
        }
        
        public void requestFocus() {
            CellRendererPane.this.requestFocus();
        }
        
        public void setBackground(final Color background) {
            CellRendererPane.this.setBackground(background);
        }
        
        public void setBounds(final Rectangle bounds) {
            CellRendererPane.this.setBounds(bounds);
        }
        
        public void setCursor(final Cursor cursor) {
            CellRendererPane.this.setCursor(cursor);
        }
        
        public void setEnabled(final boolean enabled) {
            CellRendererPane.this.setEnabled(enabled);
        }
        
        public void setFont(final Font font) {
            CellRendererPane.this.setFont(font);
        }
        
        public void setForeground(final Color foreground) {
            CellRendererPane.this.setForeground(foreground);
        }
        
        public void setLocation(final Point location) {
            CellRendererPane.this.setLocation(location);
        }
        
        public void setSize(final Dimension size) {
            CellRendererPane.this.setSize(size);
        }
        
        public void setVisible(final boolean visible) {
            CellRendererPane.this.setVisible(visible);
        }
    }
}
