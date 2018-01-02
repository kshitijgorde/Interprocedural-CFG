// 
// Decompiled by Procyon v0.5.30
// 

package gui;

import java.awt.Dimension;
import anon.infoservice.JavaVersionDBEntry;
import java.awt.Point;
import javax.swing.JSeparator;
import java.awt.event.MouseListener;
import javax.swing.SwingUtilities;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.util.Random;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.ComponentListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentAdapter;
import java.awt.LayoutManager;
import java.awt.GridBagLayout;
import java.awt.Container;
import javax.swing.JPanel;
import javax.swing.JWindow;
import javax.swing.JPopupMenu;
import java.util.Vector;
import java.awt.Window;
import java.awt.GridBagConstraints;
import java.awt.Component;

public class PopupMenu
{
    private ExitHandler m_exitHandler;
    private Component m_popup;
    private GridBagConstraints m_constraints;
    private Window m_parent;
    private boolean m_bParentOnTop;
    private Vector m_popupListeners;
    private Vector m_registeredComponents;
    private boolean m_bCompatibilityMode;
    
    public PopupMenu() {
        this(new JPopupMenu());
    }
    
    public PopupMenu(final boolean b) {
        this(new JPopupMenu(), b);
    }
    
    public PopupMenu(final JPopupMenu popupMenu) {
        this(popupMenu, false);
    }
    
    private PopupMenu(final JPopupMenu popup, final boolean bCompatibilityMode) {
        this.m_bParentOnTop = false;
        if (popup == null) {
            throw new IllegalArgumentException("Given argument is null!");
        }
        this.m_bCompatibilityMode = bCompatibilityMode;
        if (this.m_bCompatibilityMode) {
            this.m_popup = new JWindow();
            final JPanel contentPane = new JPanel();
            contentPane.setBorder(new JPopupMenu().getBorder());
            ((JWindow)this.m_popup).setContentPane(contentPane);
            ((JWindow)this.m_popup).getContentPane().setLayout(new GridBagLayout());
            this.m_popup.addComponentListener(new ComponentAdapter() {
                public void componentHidden(final ComponentEvent componentEvent) {
                    synchronized (PopupMenu.this.m_popupListeners) {
                        for (int i = 0; i < PopupMenu.this.m_popupListeners.size(); ++i) {
                            ((PopupMenuListener)PopupMenu.this.m_popupListeners.elementAt(i)).popupMenuWillBecomeInvisible(new PopupMenuEvent(componentEvent.getSource()));
                        }
                    }
                }
            });
        }
        else {
            this.m_popup = popup;
            ((JPopupMenu)this.m_popup).addPopupMenuListener(new PopupMenuListener() {
                public void popupMenuWillBecomeVisible(final PopupMenuEvent popupMenuEvent) {
                }
                
                public void popupMenuWillBecomeInvisible(final PopupMenuEvent popupMenuEvent) {
                    PopupMenu.this.resetParentOnTopAttribute();
                }
                
                public void popupMenuCanceled(final PopupMenuEvent popupMenuEvent) {
                }
            });
            ((JPopupMenu)this.m_popup).addMouseMotionListener(new MouseMotionAdapter() {});
        }
        this.m_popupListeners = new Vector();
        this.m_registeredComponents = new Vector();
        this.m_constraints = new GridBagConstraints();
        this.m_constraints.gridx = 0;
        this.m_constraints.gridy = 0;
        this.m_constraints.weighty = 1.0;
        this.m_constraints.fill = 2;
        this.m_constraints.anchor = 17;
        this.m_popup.setName(Double.toString(new Random().nextDouble()));
        this.m_popup.addMouseListener(new MouseAdapter() {
            public void mouseClicked(final MouseEvent mouseEvent) {
                if (SwingUtilities.isRightMouseButton(mouseEvent)) {
                    PopupMenu.this.setVisible(false);
                }
            }
        });
        this.registerExitHandler(null);
    }
    
    protected void removeAll() {
        if (this.m_bCompatibilityMode) {
            ((JWindow)this.m_popup).getContentPane().removeAll();
            this.m_constraints.gridy = 0;
            this.m_registeredComponents.removeAllElements();
        }
        else {
            ((JPopupMenu)this.m_popup).removeAll();
        }
    }
    
    protected void insert(final Component component, final int n) {
        if (this.m_bCompatibilityMode) {
            this.add(component);
        }
        else {
            ((JPopupMenu)this.m_popup).insert(component, n);
        }
    }
    
    protected void addSeparator() {
        this.addSeparator(new JSeparator());
    }
    
    protected void addSeparator(final JSeparator separator) {
        this.add(separator);
        final GridBagConstraints constraints = this.m_constraints;
        ++constraints.gridy;
    }
    
    protected void pack() {
        if (this.m_bCompatibilityMode) {
            ((JWindow)this.m_popup).pack();
        }
        else {
            ((JPopupMenu)this.m_popup).pack();
        }
    }
    
    protected void add(final Component component) {
        if (this.m_bCompatibilityMode) {
            if (component == null) {
                return;
            }
            ((JWindow)this.m_popup).getContentPane().add(component, this.m_constraints);
            final GridBagConstraints constraints = this.m_constraints;
            ++constraints.gridy;
            this.m_registeredComponents.addElement(component);
        }
        else {
            ((JPopupMenu)this.m_popup).add(component);
        }
    }
    
    public final void addPopupMenuListener(final PopupMenuListener popupMenuListener) {
        synchronized (this.m_popupListeners) {
            if (this.m_bCompatibilityMode) {
                if (popupMenuListener != null && !this.m_popupListeners.contains(popupMenuListener)) {
                    this.m_popupListeners.addElement(popupMenuListener);
                }
            }
            else {
                ((JPopupMenu)this.m_popup).addPopupMenuListener(popupMenuListener);
            }
        }
    }
    
    public final boolean removePopupMenuListener(final PopupMenuListener popupMenuListener) {
        synchronized (this.m_popupListeners) {
            if (this.m_bCompatibilityMode) {
                if (popupMenuListener != null) {
                    return this.m_popupListeners.removeElement(popupMenuListener);
                }
            }
            else {
                ((JPopupMenu)this.m_popup).removePopupMenuListener(popupMenuListener);
            }
        }
        return false;
    }
    
    public JPopupMenu getPopup() {
        return (JPopupMenu)this.m_popup;
    }
    
    public final Point getRelativePosition(final Point point) {
        return GUIUtils.getRelativePosition(point, this.m_popup);
    }
    
    public final Point getMousePosition() {
        return GUIUtils.getMousePosition(this.m_popup);
    }
    
    public final void registerExitHandler(final ExitHandler exitHandler) {
        if (exitHandler != null) {
            this.m_exitHandler = exitHandler;
        }
        else {
            this.m_exitHandler = new ExitHandler() {
                public void exited() {
                }
            };
        }
    }
    
    public final synchronized void show(final Component component, final Point point) {
        this.show(component, null, point);
    }
    
    public final synchronized void show(final Component component, final Window parent, final Point point) {
        final Point calculateLocationOnScreen = this.calculateLocationOnScreen(component, point);
        final Window parentWindow = GUIUtils.getParentWindow(component);
        this.m_popup.setLocation(calculateLocationOnScreen);
        this.pack();
        this.m_parent = null;
        this.m_bParentOnTop = false;
        if (GUIUtils.isAlwaysOnTop(parent)) {
            this.m_bParentOnTop = true;
            this.m_parent = parent;
        }
        else if (GUIUtils.isAlwaysOnTop(parentWindow)) {
            this.m_bParentOnTop = true;
            this.m_parent = parentWindow;
        }
        if (!this.m_bCompatibilityMode && (JavaVersionDBEntry.CURRENT_JAVA_VENDOR.toLowerCase().indexOf("sun") >= 0 || JavaVersionDBEntry.CURRENT_JAVA_VENDOR.toLowerCase().indexOf("apple") >= 0)) {
            ((JPopupMenu)this.m_popup).setInvoker(parentWindow);
        }
        if (this.m_bCompatibilityMode) {
            synchronized (this.m_popupListeners) {
                for (int i = 0; i < this.m_popupListeners.size(); ++i) {
                    ((PopupMenuListener)this.m_popupListeners.elementAt(i)).popupMenuWillBecomeVisible(new PopupMenuEvent(this.m_popup));
                }
            }
        }
        this.setVisible(true);
        if (parentWindow != null && this.m_bParentOnTop) {
            GUIUtils.setAlwaysOnTop(this.m_popup, true);
        }
    }
    
    public void repaint() {
        this.m_popup.repaint();
    }
    
    public final void setLocation(final Point location) {
        this.m_popup.setLocation(location);
    }
    
    public final Point calculateLocationOnScreen(final Component component, final Point point) {
        int x = point.x;
        int y = point.y;
        final GUIUtils.Screen currentScreen = GUIUtils.getCurrentScreen(component);
        final Dimension preferredSize = this.m_popup.getPreferredSize();
        if (x + preferredSize.width > currentScreen.getX() + currentScreen.getWidth()) {
            x = currentScreen.getX() + currentScreen.getWidth() - preferredSize.width;
        }
        if (y + preferredSize.height > currentScreen.getY() + currentScreen.getHeight()) {
            y = currentScreen.getY() + currentScreen.getHeight() - preferredSize.height;
        }
        return new Point(Math.max(x, currentScreen.getX()), Math.max(y, currentScreen.getY()));
    }
    
    public final int getWidth() {
        return this.m_popup.getPreferredSize().width;
    }
    
    public final int getHeight() {
        return this.m_popup.getPreferredSize().height;
    }
    
    public final boolean isVisible() {
        return this.m_popup.isVisible();
    }
    
    public final synchronized void dispose() {
        this.setVisible(false);
        if (this.m_bCompatibilityMode) {
            ((JWindow)this.m_popup).dispose();
        }
    }
    
    private final synchronized void resetParentOnTopAttribute() {
        if (GUIUtils.isAlwaysOnTop(this.m_popup)) {
            GUIUtils.setAlwaysOnTop(this.m_popup, false);
            final Window parent = this.m_parent;
            if (parent != null && this.m_bParentOnTop) {
                GUIUtils.setAlwaysOnTop(parent, false);
                parent.setVisible(true);
                GUIUtils.setAlwaysOnTop(parent, true);
            }
            this.m_parent = null;
            this.m_bParentOnTop = false;
        }
    }
    
    public final synchronized void setVisible(final boolean visible) {
        if (!visible) {
            this.resetParentOnTopAttribute();
        }
        if (visible && this.m_bCompatibilityMode) {
            synchronized (this.m_popupListeners) {
                for (int i = 0; i < this.m_popupListeners.size(); ++i) {
                    ((PopupMenuListener)this.m_popupListeners.elementAt(i)).popupMenuWillBecomeVisible(new PopupMenuEvent(this.m_popup));
                }
            }
        }
        this.m_popup.setVisible(visible);
    }
    
    public interface ExitHandler
    {
        void exited();
    }
}
