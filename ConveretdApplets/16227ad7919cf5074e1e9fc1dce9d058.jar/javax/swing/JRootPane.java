// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.accessibility.AccessibleRole;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Rectangle;
import java.io.Serializable;
import java.awt.LayoutManager2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.IllegalComponentStateException;
import javax.accessibility.AccessibleContext;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import javax.accessibility.Accessible;

public class JRootPane extends JComponent implements Accessible
{
    protected JMenuBar menuBar;
    protected Container contentPane;
    protected JLayeredPane layeredPane;
    protected Component glassPane;
    protected JButton defaultButton;
    protected DefaultAction defaultPressAction;
    protected DefaultAction defaultReleaseAction;
    
    public JRootPane() {
        this.setGlassPane(this.createGlassPane());
        this.setLayeredPane(this.createLayeredPane());
        this.setContentPane(this.createContentPane());
        this.setLayout(this.createRootLayout());
        this.setDoubleBuffered(true);
    }
    
    protected void addImpl(final Component component, final Object o, final int n) {
        super.addImpl(component, o, n);
        if (this.glassPane != null && this.glassPane.getParent() == this && this.getComponent(0) != this.glassPane) {
            this.add(this.glassPane, 0);
        }
    }
    
    public void addNotify() {
        SystemEventQueueUtilities.addRunnableCanvas(this);
        super.addNotify();
        this.enableEvents(8L);
    }
    
    protected Container createContentPane() {
        final JPanel panel = new JPanel();
        panel.setName(String.valueOf(this.getName()) + ".contentPane");
        panel.setLayout(new BorderLayout() {
            public void addLayoutComponent(final Component component, Object o) {
                if (o == null) {
                    o = "Center";
                }
                super.addLayoutComponent(component, o);
            }
        });
        return panel;
    }
    
    protected Component createGlassPane() {
        final JPanel panel = new JPanel();
        panel.setName(String.valueOf(this.getName()) + ".glassPane");
        panel.setVisible(false);
        panel.setOpaque(false);
        return panel;
    }
    
    protected JLayeredPane createLayeredPane() {
        final JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setName(String.valueOf(this.getName()) + ".layeredPane");
        return layeredPane;
    }
    
    protected LayoutManager createRootLayout() {
        return new RootLayout();
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJRootPane();
        }
        return super.accessibleContext;
    }
    
    public Container getContentPane() {
        return this.contentPane;
    }
    
    public JButton getDefaultButton() {
        return this.defaultButton;
    }
    
    public Component getGlassPane() {
        return this.glassPane;
    }
    
    public JMenuBar getJMenuBar() {
        return this.menuBar;
    }
    
    public JLayeredPane getLayeredPane() {
        return this.layeredPane;
    }
    
    public JMenuBar getMenuBar() {
        return this.menuBar;
    }
    
    public boolean isFocusCycleRoot() {
        return true;
    }
    
    public boolean isValidateRoot() {
        return true;
    }
    
    protected String paramString() {
        return super.paramString();
    }
    
    public void removeNotify() {
        SystemEventQueueUtilities.removeRunnableCanvas(this);
        super.removeNotify();
    }
    
    public void setContentPane(final Container contentPane) {
        if (contentPane == null) {
            throw new IllegalComponentStateException("contentPane cannot be set to null.");
        }
        if (this.contentPane != null && this.contentPane.getParent() == this.layeredPane) {
            this.layeredPane.remove(this.contentPane);
        }
        this.contentPane = contentPane;
        this.layeredPane.add(this.contentPane, JLayeredPane.FRAME_CONTENT_LAYER);
    }
    
    public void setDefaultButton(final JButton owner) {
        final JButton defaultButton = this.defaultButton;
        if (defaultButton != owner) {
            if ((this.defaultButton = owner) != null) {
                if (this.defaultPressAction == null) {
                    this.defaultPressAction = new DefaultAction(this, true);
                    this.defaultReleaseAction = new DefaultAction(this, false);
                    this.registerKeyboardAction(this.defaultPressAction, KeyStroke.getKeyStroke(10, 0, false), 2);
                    this.registerKeyboardAction(this.defaultReleaseAction, KeyStroke.getKeyStroke(10, 0, true), 2);
                    this.registerKeyboardAction(this.defaultPressAction, KeyStroke.getKeyStroke(10, 2, false), 2);
                    this.registerKeyboardAction(this.defaultReleaseAction, KeyStroke.getKeyStroke(10, 2, true), 2);
                }
                this.defaultPressAction.setOwner(owner);
                this.defaultReleaseAction.setOwner(owner);
            }
            else {
                this.unregisterKeyboardAction(KeyStroke.getKeyStroke(10, 0, false));
                this.unregisterKeyboardAction(KeyStroke.getKeyStroke(10, 0, true));
                this.unregisterKeyboardAction(KeyStroke.getKeyStroke(10, 2, false));
                this.unregisterKeyboardAction(KeyStroke.getKeyStroke(10, 2, true));
                this.defaultPressAction = null;
                this.defaultReleaseAction = null;
            }
            if (defaultButton != null) {
                defaultButton.repaint();
            }
            if (owner != null) {
                owner.repaint();
            }
        }
        this.firePropertyChange("defaultButton", defaultButton, owner);
    }
    
    public void setGlassPane(final Component glassPane) {
        if (glassPane == null) {
            throw new NullPointerException("glassPane cannot be set to null.");
        }
        boolean visible = false;
        if (this.glassPane != null && this.glassPane.getParent() == this) {
            this.remove(this.glassPane);
            visible = this.glassPane.isVisible();
        }
        glassPane.setVisible(visible);
        this.add(this.glassPane = glassPane, 0);
        if (visible) {
            this.repaint();
        }
    }
    
    public void setJMenuBar(final JMenuBar menuBar) {
        if (this.menuBar != null && this.menuBar.getParent() == this.layeredPane) {
            this.layeredPane.remove(this.menuBar);
        }
        this.menuBar = menuBar;
        if (this.menuBar != null) {
            this.layeredPane.add(this.menuBar, JLayeredPane.FRAME_CONTENT_LAYER);
        }
    }
    
    public void setLayeredPane(final JLayeredPane layeredPane) {
        if (layeredPane == null) {
            throw new IllegalComponentStateException("layeredPane cannot be set to null.");
        }
        if (this.layeredPane != null && this.layeredPane.getParent() == this) {
            this.remove(this.layeredPane);
        }
        this.add(this.layeredPane = layeredPane, -1);
    }
    
    public void setMenuBar(final JMenuBar menuBar) {
        if (this.menuBar != null && this.menuBar.getParent() == this.layeredPane) {
            this.layeredPane.remove(this.menuBar);
        }
        this.menuBar = menuBar;
        if (this.menuBar != null) {
            this.layeredPane.add(this.menuBar, JLayeredPane.FRAME_CONTENT_LAYER);
        }
    }
    
    static class DefaultAction extends AbstractAction
    {
        JButton owner;
        JRootPane root;
        boolean press;
        
        DefaultAction(final JRootPane root, final boolean press) {
            this.root = root;
            this.press = press;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            if (this.owner != null && SwingUtilities.getRootPane(this.owner) == this.root) {
                final ButtonModel model = this.owner.getModel();
                if (this.press) {
                    model.setArmed(true);
                    model.setPressed(true);
                }
                else {
                    model.setPressed(false);
                }
            }
        }
        
        public boolean isEnabled() {
            return this.owner.getModel().isEnabled();
        }
        
        public void setOwner(final JButton owner) {
            this.owner = owner;
        }
    }
    
    protected class RootLayout implements LayoutManager2, Serializable
    {
        public void addLayoutComponent(final Component component, final Object o) {
        }
        
        public void addLayoutComponent(final String s, final Component component) {
        }
        
        public float getLayoutAlignmentX(final Container container) {
            return 0.0f;
        }
        
        public float getLayoutAlignmentY(final Container container) {
            return 0.0f;
        }
        
        public void invalidateLayout(final Container container) {
        }
        
        public void layoutContainer(final Container container) {
            final Rectangle bounds = container.getBounds();
            final Insets insets = JRootPane.this.getInsets();
            int n = 0;
            final int n2 = bounds.width - insets.right - insets.left;
            final int n3 = bounds.height - insets.top - insets.bottom;
            if (JRootPane.this.layeredPane != null) {
                JRootPane.this.layeredPane.setBounds(insets.left, insets.top, n2, n3);
            }
            if (JRootPane.this.glassPane != null) {
                JRootPane.this.glassPane.setBounds(insets.left, insets.top, n2, n3);
            }
            if (JRootPane.this.menuBar != null) {
                final Dimension preferredSize = JRootPane.this.menuBar.getPreferredSize();
                JRootPane.this.menuBar.setBounds(0, 0, n2, preferredSize.height);
                n += preferredSize.height;
            }
            if (JRootPane.this.contentPane != null) {
                JRootPane.this.contentPane.setBounds(0, n, n2, n3 - n);
            }
        }
        
        public Dimension maximumLayoutSize(final Container container) {
            final Insets insets = JRootPane.this.getInsets();
            Dimension maximumSize;
            if (JRootPane.this.menuBar != null) {
                maximumSize = JRootPane.this.menuBar.getMaximumSize();
            }
            else {
                maximumSize = new Dimension(0, 0);
            }
            Dimension maximumSize2;
            if (JRootPane.this.contentPane != null) {
                maximumSize2 = JRootPane.this.contentPane.getMaximumSize();
            }
            else {
                maximumSize2 = new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE - insets.top - insets.bottom - maximumSize.height - 1);
            }
            return new Dimension(Math.min(maximumSize2.width, maximumSize.width) + insets.left + insets.right, maximumSize2.height + maximumSize.height + insets.top + insets.bottom);
        }
        
        public Dimension minimumLayoutSize(final Container container) {
            final Insets insets = JRootPane.this.getInsets();
            Dimension dimension;
            if (JRootPane.this.contentPane != null) {
                dimension = JRootPane.this.contentPane.getMinimumSize();
            }
            else {
                dimension = container.getSize();
            }
            Dimension minimumSize;
            if (JRootPane.this.menuBar != null) {
                minimumSize = JRootPane.this.menuBar.getMinimumSize();
            }
            else {
                minimumSize = new Dimension(0, 0);
            }
            return new Dimension(Math.max(dimension.width, minimumSize.width) + insets.left + insets.right, dimension.height + minimumSize.height + insets.top + insets.bottom);
        }
        
        public Dimension preferredLayoutSize(final Container container) {
            final Insets insets = JRootPane.this.getInsets();
            Dimension dimension;
            if (JRootPane.this.contentPane != null) {
                dimension = JRootPane.this.contentPane.getPreferredSize();
            }
            else {
                dimension = container.getSize();
            }
            Dimension preferredSize;
            if (JRootPane.this.menuBar != null) {
                preferredSize = JRootPane.this.menuBar.getPreferredSize();
            }
            else {
                preferredSize = new Dimension(0, 0);
            }
            return new Dimension(Math.max(dimension.width, preferredSize.width) + insets.left + insets.right, dimension.height + preferredSize.height + insets.top + insets.bottom);
        }
        
        public void removeLayoutComponent(final Component component) {
        }
    }
    
    protected class AccessibleJRootPane extends AccessibleJComponent
    {
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.ROOT_PANE;
        }
    }
}
