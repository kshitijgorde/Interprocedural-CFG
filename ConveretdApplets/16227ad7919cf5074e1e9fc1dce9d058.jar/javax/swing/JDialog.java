// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.util.Locale;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Color;
import javax.accessibility.AccessibleState;
import javax.accessibility.AccessibleStateSet;
import javax.accessibility.AccessibleRole;
import java.awt.event.FocusListener;
import javax.accessibility.AccessibleComponent;
import java.io.Serializable;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Point;
import java.applet.Applet;
import java.awt.Window;
import java.awt.LayoutManager;
import java.awt.event.WindowEvent;
import java.awt.event.KeyEvent;
import java.awt.Container;
import java.awt.Component;
import java.awt.Frame;
import javax.accessibility.AccessibleContext;
import javax.accessibility.Accessible;
import java.awt.Dialog;

public class JDialog extends Dialog implements WindowConstants, Accessible, RootPaneContainer
{
    private int defaultCloseOperation;
    protected JRootPane rootPane;
    protected boolean rootPaneCheckingEnabled;
    protected AccessibleContext accessibleContext;
    
    public JDialog() {
        this(null, false);
    }
    
    public JDialog(final Frame frame) {
        this(frame, false);
    }
    
    public JDialog(final Frame frame, final String s) {
        this(frame, s, false);
    }
    
    public JDialog(final Frame frame, final String s, final boolean b) {
        super((frame == null) ? SwingUtilities.getSharedOwnerFrame() : frame, s, b);
        this.defaultCloseOperation = 1;
        this.rootPaneCheckingEnabled = false;
        this.accessibleContext = null;
        this.dialogInit();
    }
    
    public JDialog(final Frame frame, final boolean b) {
        this(frame, null, b);
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
    
    protected void dialogInit() {
        this.enableEvents(72L);
        this.setRootPane(this.createRootPane());
        this.setRootPaneCheckingEnabled(true);
    }
    
    public AccessibleContext getAccessibleContext() {
        if (this.accessibleContext == null) {
            this.accessibleContext = new AccessibleJDialog();
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
    
    public void setLocationRelativeTo(final Component component) {
        Component component2 = null;
        if (component != null) {
            if (component instanceof Window || component instanceof Applet) {
                component2 = component;
            }
            else {
                for (Container container = component.getParent(); container != null; container = container.getParent()) {
                    if (container instanceof Window || container instanceof Applet) {
                        component2 = container;
                        break;
                    }
                }
            }
        }
        if ((component != null && !component.isShowing()) || component2 == null || !component2.isShowing()) {
            final Dimension size = this.getSize();
            final Dimension screenSize = this.getToolkit().getScreenSize();
            this.setLocation((screenSize.width - size.width) / 2, (screenSize.height - size.height) / 2);
        }
        else {
            final Dimension size2 = component.getSize();
            Point locationOnScreen;
            if (component2 instanceof Applet) {
                locationOnScreen = component.getLocationOnScreen();
            }
            else {
                locationOnScreen = new Point(0, 0);
                for (Component parent = component; parent != null; parent = parent.getParent()) {
                    final Point location = parent.getLocation();
                    final Point point = locationOnScreen;
                    point.x += location.x;
                    final Point point2 = locationOnScreen;
                    point2.y += location.y;
                    if (parent == component2) {
                        break;
                    }
                }
            }
            final Rectangle bounds = this.getBounds();
            int n = locationOnScreen.x + (size2.width - bounds.width >> 1);
            int n2 = locationOnScreen.y + (size2.height - bounds.height >> 1);
            final Dimension screenSize2 = this.getToolkit().getScreenSize();
            if (n2 + bounds.height > screenSize2.height) {
                n2 = screenSize2.height - bounds.height;
                n = ((locationOnScreen.x < screenSize2.width >> 1) ? (locationOnScreen.x + size2.width) : (locationOnScreen.x - bounds.width));
            }
            if (n + bounds.width > screenSize2.width) {
                n = screenSize2.width - bounds.width;
            }
            if (n < 0) {
                n = 0;
            }
            if (n2 < 0) {
                n2 = 0;
            }
            this.setLocation(n, n2);
        }
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
    
    protected class AccessibleJDialog extends AccessibleContext implements Serializable, AccessibleComponent
    {
        public void addFocusListener(final FocusListener focusListener) {
            JDialog.this.addFocusListener(focusListener);
        }
        
        public boolean contains(final Point point) {
            return JDialog.this.contains(point);
        }
        
        public Accessible getAccessibleAt(final Point point) {
            return SwingUtilities.getAccessibleAt(JDialog.this, point);
        }
        
        public Accessible getAccessibleChild(final int n) {
            return SwingUtilities.getAccessibleChild(JDialog.this, n);
        }
        
        public int getAccessibleChildrenCount() {
            return SwingUtilities.getAccessibleChildrenCount(JDialog.this);
        }
        
        public AccessibleComponent getAccessibleComponent() {
            return this;
        }
        
        public int getAccessibleIndexInParent() {
            return SwingUtilities.getAccessibleIndexInParent(JDialog.this);
        }
        
        public String getAccessibleName() {
            if (super.accessibleName != null) {
                return super.accessibleName;
            }
            if (JDialog.this.getTitle() == null) {
                return super.getAccessibleName();
            }
            return JDialog.this.getTitle();
        }
        
        public Accessible getAccessibleParent() {
            if (super.accessibleParent != null) {
                return super.accessibleParent;
            }
            final Container parent = JDialog.this.getParent();
            if (parent instanceof Accessible) {
                return (Accessible)parent;
            }
            return null;
        }
        
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.DIALOG;
        }
        
        public AccessibleStateSet getAccessibleStateSet() {
            final AccessibleStateSet accessibleStateSet = SwingUtilities.getAccessibleStateSet(JDialog.this);
            if (JDialog.this.isResizable()) {
                accessibleStateSet.add(AccessibleState.RESIZABLE);
            }
            if (JDialog.this.getFocusOwner() != null) {
                accessibleStateSet.add(AccessibleState.ACTIVE);
            }
            if (JDialog.this.isModal()) {
                accessibleStateSet.add(AccessibleState.MODAL);
            }
            return accessibleStateSet;
        }
        
        public Color getBackground() {
            return JDialog.this.getBackground();
        }
        
        public Rectangle getBounds() {
            return JDialog.this.getBounds();
        }
        
        public Cursor getCursor() {
            return JDialog.this.getCursor();
        }
        
        public Font getFont() {
            return JDialog.this.getFont();
        }
        
        public FontMetrics getFontMetrics(final Font font) {
            return JDialog.this.getFontMetrics(font);
        }
        
        public Color getForeground() {
            return JDialog.this.getForeground();
        }
        
        public Locale getLocale() {
            return JDialog.this.getLocale();
        }
        
        public Point getLocation() {
            return JDialog.this.getLocation();
        }
        
        public Point getLocationOnScreen() {
            return JDialog.this.getLocationOnScreen();
        }
        
        public Dimension getSize() {
            return JDialog.this.getSize();
        }
        
        public boolean isEnabled() {
            return JDialog.this.isEnabled();
        }
        
        public boolean isFocusTraversable() {
            return JDialog.this.isFocusTraversable();
        }
        
        public boolean isShowing() {
            return JDialog.this.isShowing();
        }
        
        public boolean isVisible() {
            return JDialog.this.isVisible();
        }
        
        public void removeFocusListener(final FocusListener focusListener) {
            JDialog.this.removeFocusListener(focusListener);
        }
        
        public void requestFocus() {
            JDialog.this.requestFocus();
        }
        
        public void setBackground(final Color background) {
            JDialog.this.setBackground(background);
        }
        
        public void setBounds(final Rectangle bounds) {
            JDialog.this.setBounds(bounds);
        }
        
        public void setCursor(final Cursor cursor) {
            JDialog.this.setCursor(cursor);
        }
        
        public void setEnabled(final boolean enabled) {
            JDialog.this.setEnabled(enabled);
        }
        
        public void setFont(final Font font) {
            JDialog.this.setFont(font);
        }
        
        public void setForeground(final Color foreground) {
            JDialog.this.setForeground(foreground);
        }
        
        public void setLocation(final Point location) {
            JDialog.this.setLocation(location);
        }
        
        public void setSize(final Dimension size) {
            JDialog.this.setSize(size);
        }
        
        public void setVisible(final boolean visible) {
            JDialog.this.setVisible(visible);
        }
    }
}
