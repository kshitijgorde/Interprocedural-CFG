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
import javax.accessibility.AccessibleState;
import javax.accessibility.AccessibleStateSet;
import javax.accessibility.AccessibleRole;
import java.awt.Point;
import java.awt.event.FocusListener;
import javax.accessibility.AccessibleComponent;
import java.io.Serializable;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.accessibility.AccessibleContext;
import javax.accessibility.Accessible;
import java.applet.Applet;

public class JApplet extends Applet implements Accessible, RootPaneContainer
{
    protected JRootPane rootPane;
    protected boolean rootPaneCheckingEnabled;
    protected AccessibleContext accessibleContext;
    
    public JApplet() {
        this.rootPaneCheckingEnabled = false;
        this.accessibleContext = null;
        final TimerQueue sharedInstance = TimerQueue.sharedInstance();
        if (sharedInstance != null) {
            synchronized (sharedInstance) {
                if (!sharedInstance.running) {
                    sharedInstance.start();
                }
            }
            // monitorexit(sharedInstance)
        }
        this.setForeground(Color.black);
        this.setBackground(Color.white);
        this.setLayout(new BorderLayout());
        this.setRootPane(this.createRootPane());
        this.setRootPaneCheckingEnabled(true);
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
            this.accessibleContext = new AccessibleJApplet();
        }
        return this.accessibleContext;
    }
    
    public Container getContentPane() {
        return this.getRootPane().getContentPane();
    }
    
    public Component getGlassPane() {
        return this.getRootPane().getGlassPane();
    }
    
    public JMenuBar getJMenuBar() {
        return this.getRootPane().getMenuBar();
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
        return String.valueOf(super.paramString()) + ",rootPane=" + ((this.rootPane != null) ? this.rootPane.toString() : "") + ",rootPaneCheckingEnabled=" + (this.rootPaneCheckingEnabled ? "true" : "false");
    }
    
    protected void processKeyEvent(final KeyEvent keyEvent) {
        super.processKeyEvent(keyEvent);
        if (!keyEvent.isConsumed()) {
            JComponent.processKeyBindingsForAllComponents(keyEvent, this, keyEvent.getID() == 401);
        }
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
    
    public void setJMenuBar(final JMenuBar menuBar) {
        this.getRootPane().setMenuBar(menuBar);
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
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    protected class AccessibleJApplet extends AccessibleContext implements Serializable, AccessibleComponent
    {
        public void addFocusListener(final FocusListener focusListener) {
            JApplet.this.addFocusListener(focusListener);
        }
        
        public boolean contains(final Point point) {
            return JApplet.this.contains(point);
        }
        
        public Accessible getAccessibleAt(final Point point) {
            return SwingUtilities.getAccessibleAt(JApplet.this, point);
        }
        
        public Accessible getAccessibleChild(final int n) {
            return SwingUtilities.getAccessibleChild(JApplet.this, n);
        }
        
        public int getAccessibleChildrenCount() {
            return SwingUtilities.getAccessibleChildrenCount(JApplet.this);
        }
        
        public AccessibleComponent getAccessibleComponent() {
            return this;
        }
        
        public int getAccessibleIndexInParent() {
            return SwingUtilities.getAccessibleIndexInParent(JApplet.this);
        }
        
        public Accessible getAccessibleParent() {
            if (super.accessibleParent != null) {
                return super.accessibleParent;
            }
            final Container parent = JApplet.this.getParent();
            if (parent instanceof Accessible) {
                return (Accessible)parent;
            }
            return null;
        }
        
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.FRAME;
        }
        
        public AccessibleStateSet getAccessibleStateSet() {
            final AccessibleStateSet accessibleStateSet = SwingUtilities.getAccessibleStateSet(JApplet.this);
            accessibleStateSet.add(AccessibleState.ACTIVE);
            return accessibleStateSet;
        }
        
        public Color getBackground() {
            return JApplet.this.getBackground();
        }
        
        public Rectangle getBounds() {
            return JApplet.this.getBounds();
        }
        
        public Cursor getCursor() {
            return JApplet.this.getCursor();
        }
        
        public Font getFont() {
            return JApplet.this.getFont();
        }
        
        public FontMetrics getFontMetrics(final Font font) {
            return JApplet.this.getFontMetrics(font);
        }
        
        public Color getForeground() {
            return JApplet.this.getForeground();
        }
        
        public Locale getLocale() {
            return JApplet.this.getLocale();
        }
        
        public Point getLocation() {
            return JApplet.this.getLocation();
        }
        
        public Point getLocationOnScreen() {
            return JApplet.this.getLocationOnScreen();
        }
        
        public Dimension getSize() {
            return JApplet.this.getSize();
        }
        
        public boolean isEnabled() {
            return JApplet.this.isEnabled();
        }
        
        public boolean isFocusTraversable() {
            return JApplet.this.isFocusTraversable();
        }
        
        public boolean isShowing() {
            return JApplet.this.isShowing();
        }
        
        public boolean isVisible() {
            return JApplet.this.isVisible();
        }
        
        public void removeFocusListener(final FocusListener focusListener) {
            JApplet.this.removeFocusListener(focusListener);
        }
        
        public void requestFocus() {
            JApplet.this.requestFocus();
        }
        
        public void setBackground(final Color background) {
            JApplet.this.setBackground(background);
        }
        
        public void setBounds(final Rectangle bounds) {
            JApplet.this.setBounds(bounds);
        }
        
        public void setCursor(final Cursor cursor) {
            JApplet.this.setCursor(cursor);
        }
        
        public void setEnabled(final boolean enabled) {
            JApplet.this.setEnabled(enabled);
        }
        
        public void setFont(final Font font) {
            JApplet.this.setFont(font);
        }
        
        public void setForeground(final Color foreground) {
            JApplet.this.setForeground(foreground);
        }
        
        public void setLocation(final Point location) {
            JApplet.this.setLocation(location);
        }
        
        public void setSize(final Dimension size) {
            JApplet.this.setSize(size);
        }
        
        public void setVisible(final boolean visible) {
            JApplet.this.setVisible(visible);
        }
    }
}
