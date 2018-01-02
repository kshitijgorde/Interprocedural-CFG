// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.Dimension;
import java.util.Locale;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.Color;
import javax.accessibility.AccessibleState;
import javax.accessibility.AccessibleStateSet;
import javax.accessibility.AccessibleRole;
import java.awt.Point;
import java.awt.event.FocusListener;
import javax.accessibility.AccessibleComponent;
import java.io.Serializable;
import java.awt.LayoutManager;
import java.awt.Container;
import java.awt.Component;
import java.awt.Frame;
import javax.accessibility.AccessibleContext;
import javax.accessibility.Accessible;
import java.awt.Window;

public class JWindow extends Window implements Accessible, RootPaneContainer
{
    protected JRootPane rootPane;
    protected boolean rootPaneCheckingEnabled;
    protected AccessibleContext accessibleContext;
    
    public JWindow() {
        this((Frame)null);
    }
    
    public JWindow(final Frame frame) {
        super((frame == null) ? SwingUtilities.getSharedOwnerFrame() : frame);
        this.rootPaneCheckingEnabled = false;
        this.accessibleContext = null;
        this.windowInit();
    }
    
    protected void addImpl(final Component component, final Object o, final int n) {
        if (this.isRootPaneCheckingEnabled()) {
            throw this.createRootPaneException("add");
        }
        super.addImpl(component, o, n);
    }
    
    protected JRootPane createRootPane() {
        return new JRootPane();
    }
    
    private Error createRootPaneException(final String s) {
        final String name = this.getClass().getName();
        return new Error("Do not use " + name + "." + s + "() use " + name + ".getContentPane()." + s + "() instead");
    }
    
    public AccessibleContext getAccessibleContext() {
        if (this.accessibleContext == null) {
            this.accessibleContext = new AccessibleJWindow();
        }
        return this.accessibleContext;
    }
    
    public Container getContentPane() {
        return this.getRootPane().getContentPane();
    }
    
    public Component getGlassPane() {
        return this.getRootPane().getGlassPane();
    }
    
    public JLayeredPane getLayeredPane() {
        return this.getRootPane().getLayeredPane();
    }
    
    public JRootPane getRootPane() {
        return this.rootPane;
    }
    
    protected boolean isRootPaneCheckingEnabled() {
        return this.rootPaneCheckingEnabled;
    }
    
    protected String paramString() {
        return String.valueOf(super.paramString()) + ",rootPaneCheckingEnabled=" + (this.rootPaneCheckingEnabled ? "true" : "false");
    }
    
    public void remove(final Component component) {
        if (component == this.rootPane) {
            super.remove(component);
        }
        else {
            this.getContentPane().remove(component);
        }
    }
    
    public void setContentPane(final Container contentPane) {
        this.getRootPane().setContentPane(contentPane);
    }
    
    public void setGlassPane(final Component glassPane) {
        this.getRootPane().setGlassPane(glassPane);
    }
    
    public void setLayeredPane(final JLayeredPane layeredPane) {
        this.getRootPane().setLayeredPane(layeredPane);
    }
    
    public void setLayout(final LayoutManager layout) {
        if (this.isRootPaneCheckingEnabled()) {
            throw this.createRootPaneException("setLayout");
        }
        super.setLayout(layout);
    }
    
    protected void setRootPane(final JRootPane rootPane) {
        if (this.rootPane != null) {
            this.remove(this.rootPane);
        }
        this.rootPane = rootPane;
        if (this.rootPane != null) {
            final boolean rootPaneCheckingEnabled = this.isRootPaneCheckingEnabled();
            try {
                this.setRootPaneCheckingEnabled(false);
                this.add(this.rootPane, "Center");
            }
            finally {
                this.setRootPaneCheckingEnabled(rootPaneCheckingEnabled);
            }
        }
    }
    
    protected void setRootPaneCheckingEnabled(final boolean rootPaneCheckingEnabled) {
        this.rootPaneCheckingEnabled = rootPaneCheckingEnabled;
    }
    
    protected void windowInit() {
        this.setRootPane(this.createRootPane());
        this.setRootPaneCheckingEnabled(true);
    }
    
    protected class AccessibleJWindow extends AccessibleContext implements Serializable, AccessibleComponent
    {
        public void addFocusListener(final FocusListener focusListener) {
            JWindow.this.addFocusListener(focusListener);
        }
        
        public boolean contains(final Point point) {
            return JWindow.this.contains(point);
        }
        
        public Accessible getAccessibleAt(final Point point) {
            return SwingUtilities.getAccessibleAt(JWindow.this, point);
        }
        
        public Accessible getAccessibleChild(final int n) {
            return SwingUtilities.getAccessibleChild(JWindow.this, n);
        }
        
        public int getAccessibleChildrenCount() {
            return SwingUtilities.getAccessibleChildrenCount(JWindow.this);
        }
        
        public AccessibleComponent getAccessibleComponent() {
            return this;
        }
        
        public int getAccessibleIndexInParent() {
            return SwingUtilities.getAccessibleIndexInParent(JWindow.this);
        }
        
        public Accessible getAccessibleParent() {
            if (super.accessibleParent != null) {
                return super.accessibleParent;
            }
            final Container parent = JWindow.this.getParent();
            if (parent instanceof Accessible) {
                return (Accessible)parent;
            }
            return null;
        }
        
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.WINDOW;
        }
        
        public AccessibleStateSet getAccessibleStateSet() {
            final AccessibleStateSet accessibleStateSet = SwingUtilities.getAccessibleStateSet(JWindow.this);
            if (JWindow.this.getFocusOwner() != null) {
                accessibleStateSet.add(AccessibleState.ACTIVE);
            }
            return accessibleStateSet;
        }
        
        public Color getBackground() {
            return JWindow.this.getBackground();
        }
        
        public Rectangle getBounds() {
            return JWindow.this.getBounds();
        }
        
        public Cursor getCursor() {
            return JWindow.this.getCursor();
        }
        
        public Font getFont() {
            return JWindow.this.getFont();
        }
        
        public FontMetrics getFontMetrics(final Font font) {
            return JWindow.this.getFontMetrics(font);
        }
        
        public Color getForeground() {
            return JWindow.this.getForeground();
        }
        
        public Locale getLocale() {
            return JWindow.this.getLocale();
        }
        
        public Point getLocation() {
            return JWindow.this.getLocation();
        }
        
        public Point getLocationOnScreen() {
            return JWindow.this.getLocationOnScreen();
        }
        
        public Dimension getSize() {
            return JWindow.this.getSize();
        }
        
        public boolean isEnabled() {
            return JWindow.this.isEnabled();
        }
        
        public boolean isFocusTraversable() {
            return JWindow.this.isFocusTraversable();
        }
        
        public boolean isShowing() {
            return JWindow.this.isShowing();
        }
        
        public boolean isVisible() {
            return JWindow.this.isVisible();
        }
        
        public void removeFocusListener(final FocusListener focusListener) {
            JWindow.this.removeFocusListener(focusListener);
        }
        
        public void requestFocus() {
            JWindow.this.requestFocus();
        }
        
        public void setBackground(final Color background) {
            JWindow.this.setBackground(background);
        }
        
        public void setBounds(final Rectangle bounds) {
            JWindow.this.setBounds(bounds);
        }
        
        public void setCursor(final Cursor cursor) {
            JWindow.this.setCursor(cursor);
        }
        
        public void setEnabled(final boolean enabled) {
            JWindow.this.setEnabled(enabled);
        }
        
        public void setFont(final Font font) {
            JWindow.this.setFont(font);
        }
        
        public void setForeground(final Color foreground) {
            JWindow.this.setForeground(foreground);
        }
        
        public void setLocation(final Point location) {
            JWindow.this.setLocation(location);
        }
        
        public void setSize(final Dimension size) {
            JWindow.this.setSize(size);
        }
        
        public void setVisible(final boolean visible) {
            JWindow.this.setVisible(visible);
        }
    }
}
