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
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.Container;
import java.awt.Component;
import javax.accessibility.AccessibleContext;
import javax.accessibility.Accessible;
import java.awt.Frame;

public class JFrame extends Frame implements WindowConstants, Accessible, RootPaneContainer
{
    private int defaultCloseOperation;
    protected JRootPane rootPane;
    protected boolean rootPaneCheckingEnabled;
    protected AccessibleContext accessibleContext;
    
    public JFrame() {
        this.defaultCloseOperation = 1;
        this.rootPaneCheckingEnabled = false;
        this.accessibleContext = null;
        this.frameInit();
    }
    
    public JFrame(final String s) {
        super(s);
        this.defaultCloseOperation = 1;
        this.rootPaneCheckingEnabled = false;
        this.accessibleContext = null;
        this.frameInit();
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
    
    protected void frameInit() {
        this.enableEvents(72L);
        this.setRootPane(this.createRootPane());
        this.setBackground(UIManager.getColor("control"));
        this.setRootPaneCheckingEnabled(true);
    }
    
    public AccessibleContext getAccessibleContext() {
        if (this.accessibleContext == null) {
            this.accessibleContext = new AccessibleJFrame();
        }
        return this.accessibleContext;
    }
    
    public Container getContentPane() {
        return this.getRootPane().getContentPane();
    }
    
    public int getDefaultCloseOperation() {
        return this.defaultCloseOperation;
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
        String s;
        if (this.defaultCloseOperation == 1) {
            s = "HIDE_ON_CLOSE";
        }
        else if (this.defaultCloseOperation == 2) {
            s = "DISPOSE_ON_CLOSE";
        }
        else if (this.defaultCloseOperation == 0) {
            s = "DO_NOTHING_ON_CLOSE";
        }
        else if (this.defaultCloseOperation == 3) {
            s = "EXIT_ON_CLOSE";
        }
        else {
            s = "";
        }
        return String.valueOf(super.paramString()) + ",defaultCloseOperation=" + s + ",rootPane=" + ((this.rootPane != null) ? this.rootPane.toString() : "") + ",rootPaneCheckingEnabled=" + (this.rootPaneCheckingEnabled ? "true" : "false");
    }
    
    protected void processKeyEvent(final KeyEvent keyEvent) {
        super.processKeyEvent(keyEvent);
        if (!keyEvent.isConsumed()) {
            JComponent.processKeyBindingsForAllComponents(keyEvent, this, keyEvent.getID() == 401);
        }
    }
    
    protected void processWindowEvent(final WindowEvent windowEvent) {
        super.processWindowEvent(windowEvent);
        if (windowEvent.getID() == 201) {
            switch (this.defaultCloseOperation) {
                case 1: {
                    this.setVisible(false);
                    break;
                }
                case 2: {
                    this.setVisible(false);
                    this.dispose();
                    break;
                }
                case 3: {
                    System.exit(0);
                    break;
                }
            }
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
    
    public void setDefaultCloseOperation(final int defaultCloseOperation) {
        this.defaultCloseOperation = defaultCloseOperation;
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
    
    protected class AccessibleJFrame extends AccessibleContext implements Serializable, AccessibleComponent
    {
        public void addFocusListener(final FocusListener focusListener) {
            JFrame.this.addFocusListener(focusListener);
        }
        
        public boolean contains(final Point point) {
            return JFrame.this.contains(point);
        }
        
        public Accessible getAccessibleAt(final Point point) {
            for (int accessibleChildrenCount = this.getAccessibleChildrenCount(), i = 0; i < accessibleChildrenCount; ++i) {
                final Accessible accessibleChild = this.getAccessibleChild(i);
                if (accessibleChild != null) {
                    final AccessibleContext accessibleContext = accessibleChild.getAccessibleContext();
                    if (accessibleContext != null) {
                        final AccessibleComponent accessibleComponent = accessibleContext.getAccessibleComponent();
                        if (accessibleComponent != null && accessibleComponent.isShowing()) {
                            final Point location = accessibleComponent.getLocation();
                            if (accessibleComponent.contains(new Point(point.x - location.x, point.y - location.y))) {
                                return accessibleChild;
                            }
                        }
                    }
                }
            }
            return JFrame.this;
        }
        
        public Accessible getAccessibleChild(final int n) {
            return SwingUtilities.getAccessibleChild(JFrame.this, n);
        }
        
        public int getAccessibleChildrenCount() {
            return SwingUtilities.getAccessibleChildrenCount(JFrame.this);
        }
        
        public AccessibleComponent getAccessibleComponent() {
            return this;
        }
        
        public int getAccessibleIndexInParent() {
            return SwingUtilities.getAccessibleIndexInParent(JFrame.this);
        }
        
        public String getAccessibleName() {
            if (super.accessibleName != null) {
                return super.accessibleName;
            }
            if (JFrame.this.getTitle() == null) {
                return super.getAccessibleName();
            }
            return JFrame.this.getTitle();
        }
        
        public Accessible getAccessibleParent() {
            if (super.accessibleParent != null) {
                return super.accessibleParent;
            }
            final Container parent = JFrame.this.getParent();
            if (parent instanceof Accessible) {
                return (Accessible)parent;
            }
            return null;
        }
        
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.FRAME;
        }
        
        public AccessibleStateSet getAccessibleStateSet() {
            final AccessibleStateSet accessibleStateSet = SwingUtilities.getAccessibleStateSet(JFrame.this);
            if (JFrame.this.isResizable()) {
                accessibleStateSet.add(AccessibleState.RESIZABLE);
            }
            if (JFrame.this.getFocusOwner() != null) {
                accessibleStateSet.add(AccessibleState.ACTIVE);
            }
            return accessibleStateSet;
        }
        
        public Color getBackground() {
            return JFrame.this.getBackground();
        }
        
        public Rectangle getBounds() {
            return JFrame.this.getBounds();
        }
        
        public Cursor getCursor() {
            return JFrame.this.getCursor();
        }
        
        public Font getFont() {
            return JFrame.this.getFont();
        }
        
        public FontMetrics getFontMetrics(final Font font) {
            return JFrame.this.getFontMetrics(font);
        }
        
        public Color getForeground() {
            return JFrame.this.getForeground();
        }
        
        public Locale getLocale() {
            return JFrame.this.getLocale();
        }
        
        public Point getLocation() {
            return JFrame.this.getLocation();
        }
        
        public Point getLocationOnScreen() {
            return JFrame.this.getLocationOnScreen();
        }
        
        public Dimension getSize() {
            return JFrame.this.getSize();
        }
        
        public boolean isEnabled() {
            return JFrame.this.isEnabled();
        }
        
        public boolean isFocusTraversable() {
            return JFrame.this.isFocusTraversable();
        }
        
        public boolean isShowing() {
            return JFrame.this.isShowing();
        }
        
        public boolean isVisible() {
            return JFrame.this.isVisible();
        }
        
        public void removeFocusListener(final FocusListener focusListener) {
            JFrame.this.removeFocusListener(focusListener);
        }
        
        public void requestFocus() {
            JFrame.this.requestFocus();
        }
        
        public void setBackground(final Color background) {
            JFrame.this.setBackground(background);
        }
        
        public void setBounds(final Rectangle bounds) {
            JFrame.this.setBounds(bounds);
        }
        
        public void setCursor(final Cursor cursor) {
            JFrame.this.setCursor(cursor);
        }
        
        public void setEnabled(final boolean enabled) {
            JFrame.this.setEnabled(enabled);
        }
        
        public void setFont(final Font font) {
            JFrame.this.setFont(font);
        }
        
        public void setForeground(final Color foreground) {
            JFrame.this.setForeground(foreground);
        }
        
        public void setLocation(final Point location) {
            JFrame.this.setLocation(location);
        }
        
        public void setSize(final Dimension size) {
            JFrame.this.setSize(size);
        }
        
        public void setVisible(final boolean visible) {
            JFrame.this.setVisible(visible);
        }
    }
}
