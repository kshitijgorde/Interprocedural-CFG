// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.applet.Applet;
import java.awt.Panel;
import java.awt.Window;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import java.util.Locale;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Color;
import javax.accessibility.AccessibleStateSet;
import javax.accessibility.AccessibleRole;
import java.awt.event.FocusListener;
import javax.accessibility.AccessibleComponent;
import java.beans.PropertyChangeEvent;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import javax.accessibility.AccessibleState;
import javax.swing.plaf.ComponentUI;
import java.awt.Dimension;
import java.awt.event.WindowListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Graphics;
import javax.swing.plaf.PopupMenuUI;
import java.util.Vector;
import javax.accessibility.AccessibleContext;
import javax.swing.event.PopupMenuEvent;
import java.beans.PropertyChangeListener;
import java.awt.Container;
import java.awt.Dialog;
import java.util.EventListener;
import javax.swing.event.PopupMenuListener;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.util.Hashtable;
import java.awt.Insets;
import java.awt.Frame;
import java.awt.Component;
import javax.accessibility.Accessible;

public class JPopupMenu extends JComponent implements Accessible, MenuElement
{
    private static final String uiClassID = "PopupMenuUI";
    transient Component invoker;
    transient Popup popup;
    transient Frame frame;
    private String label;
    private boolean paintBorder;
    private Insets margin;
    private int desiredLocationX;
    private int desiredLocationY;
    private int lastPopupType;
    private static final Object heavyPopupCacheKey;
    private static final Object lightPopupCacheKey;
    private static final Object mediumPopupCacheKey;
    private static final Object defaultLWPopupEnabledKey;
    private static final int MAX_CACHE_SIZE = 5;
    private boolean lightWeightPopupEnabled;
    private static final int LIGHT_WEIGHT_POPUP = 0;
    private static final int MEDIUM_WEIGHT_POPUP = 1;
    private static final int HEAVY_WEIGHT_POPUP = 2;
    private SingleSelectionModel selectionModel;
    private static Hashtable listenerRegistry;
    private static final Object classLock;
    static /* synthetic */ Class class$javax$swing$event$PopupMenuListener;
    
    static {
        heavyPopupCacheKey = new StringBuffer("JPopupMenu.heavyPopupCache");
        lightPopupCacheKey = new StringBuffer("JPopupMenu.lightPopupCache");
        mediumPopupCacheKey = new StringBuffer("JPopupMenu.mediumPopupCache");
        defaultLWPopupEnabledKey = new StringBuffer("JPopupMenu.defaultLWPopupEnabledKey");
        JPopupMenu.listenerRegistry = null;
        classLock = new Object();
    }
    
    public JPopupMenu() {
        this(null);
    }
    
    public JPopupMenu(final String label) {
        this.label = null;
        this.paintBorder = true;
        this.margin = null;
        this.lastPopupType = 0;
        this.lightWeightPopupEnabled = true;
        this.label = label;
        this.lightWeightPopupEnabled = getDefaultLightWeightPopupEnabled();
        this.setSelectionModel(new DefaultSingleSelectionModel());
        this.addMouseListener(new MouseAdapter() {});
        this.updateUI();
    }
    
    public JMenuItem add(final String s) {
        return this.add(new JMenuItem(s));
    }
    
    public JMenuItem add(final Action action) {
        final JMenuItem menuItem = new JMenuItem((String)action.getValue("Name"), (Icon)action.getValue("SmallIcon"));
        menuItem.setHorizontalTextPosition(4);
        menuItem.setVerticalTextPosition(0);
        menuItem.setEnabled(action.isEnabled());
        menuItem.addActionListener(action);
        this.add(menuItem);
        this.registerMenuItemForAction(menuItem, action);
        return menuItem;
    }
    
    public JMenuItem add(final JMenuItem menuItem) {
        super.add(menuItem);
        return menuItem;
    }
    
    public void addPopupMenuListener(final PopupMenuListener popupMenuListener) {
        super.listenerList.add((JPopupMenu.class$javax$swing$event$PopupMenuListener != null) ? JPopupMenu.class$javax$swing$event$PopupMenuListener : (JPopupMenu.class$javax$swing$event$PopupMenuListener = class$("javax.swing.event.PopupMenuListener")), popupMenuListener);
    }
    
    public void addSeparator() {
        this.add(new Separator());
    }
    
    boolean alwaysOnTop() {
        return true;
    }
    
    private boolean ancestorIsModalDialog(final Component component) {
        if (component != null) {
            for (Container container = component.getParent(); container != null; container = container.getParent()) {
                if (container instanceof Dialog && ((Dialog)container).isModal()) {
                    return true;
                }
            }
        }
        return false;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    protected PropertyChangeListener createActionChangeListener(final JMenuItem menuItem) {
        return new ActionChangedListener(menuItem);
    }
    
    private Popup createHeavyWeightPopup() {
        Frame frame = getFrame(this.invoker);
        if (frame != null) {
            this.popup = getRecycledHeavyPopup(frame);
        }
        else {
            frame = new Frame();
        }
        if (this.popup == null) {
            this.popup = (Popup)new WindowPopup(frame);
        }
        return this.popup;
    }
    
    private Popup createLightWeightPopup() {
        Object recycledLightPopup = getRecycledLightPopup();
        if (recycledLightPopup == null) {
            recycledLightPopup = new JPanelPopup();
        }
        return (Popup)recycledLightPopup;
    }
    
    private Popup createMediumWeightPopup() {
        Object recycledMediumPopup = getRecycledMediumPopup();
        if (recycledMediumPopup == null) {
            recycledMediumPopup = new PanelPopup();
        }
        return (Popup)recycledMediumPopup;
    }
    
    protected void firePopupMenuCanceled() {
        final Object[] listenerList = super.listenerList.getListenerList();
        PopupMenuEvent popupMenuEvent = null;
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((JPopupMenu.class$javax$swing$event$PopupMenuListener != null) ? JPopupMenu.class$javax$swing$event$PopupMenuListener : (JPopupMenu.class$javax$swing$event$PopupMenuListener = class$("javax.swing.event.PopupMenuListener")))) {
                if (popupMenuEvent == null) {
                    popupMenuEvent = new PopupMenuEvent(this);
                }
                ((PopupMenuListener)listenerList[i + 1]).popupMenuCanceled(popupMenuEvent);
            }
        }
    }
    
    protected void firePopupMenuWillBecomeInvisible() {
        final Object[] listenerList = super.listenerList.getListenerList();
        PopupMenuEvent popupMenuEvent = null;
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((JPopupMenu.class$javax$swing$event$PopupMenuListener != null) ? JPopupMenu.class$javax$swing$event$PopupMenuListener : (JPopupMenu.class$javax$swing$event$PopupMenuListener = class$("javax.swing.event.PopupMenuListener")))) {
                if (popupMenuEvent == null) {
                    popupMenuEvent = new PopupMenuEvent(this);
                }
                ((PopupMenuListener)listenerList[i + 1]).popupMenuWillBecomeInvisible(popupMenuEvent);
            }
        }
    }
    
    protected void firePopupMenuWillBecomeVisible() {
        final Object[] listenerList = super.listenerList.getListenerList();
        PopupMenuEvent popupMenuEvent = null;
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((JPopupMenu.class$javax$swing$event$PopupMenuListener != null) ? JPopupMenu.class$javax$swing$event$PopupMenuListener : (JPopupMenu.class$javax$swing$event$PopupMenuListener = class$("javax.swing.event.PopupMenuListener")))) {
                if (popupMenuEvent == null) {
                    popupMenuEvent = new PopupMenuEvent(this);
                }
                ((PopupMenuListener)listenerList[i + 1]).popupMenuWillBecomeVisible(popupMenuEvent);
            }
        }
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJPopupMenu();
        }
        return super.accessibleContext;
    }
    
    public Component getComponent() {
        return this;
    }
    
    public Component getComponentAtIndex(final int n) {
        return this.getComponent(n);
    }
    
    public int getComponentIndex(final Component component) {
        final int componentCount = this.getComponentCount();
        final Component[] components = this.getComponents();
        for (int i = 0; i < componentCount; ++i) {
            if (components[i] == component) {
                return i;
            }
        }
        return -1;
    }
    
    public static boolean getDefaultLightWeightPopupEnabled() {
        final Boolean b = (Boolean)SwingUtilities.appContextGet(JPopupMenu.defaultLWPopupEnabledKey);
        if (b == null) {
            SwingUtilities.appContextPut(JPopupMenu.defaultLWPopupEnabledKey, Boolean.TRUE);
            return true;
        }
        return b;
    }
    
    private static Frame getFrame(final Component component) {
        Component parent;
        for (parent = component; !(parent instanceof Frame) && parent != null; parent = parent.getParent()) {}
        return (Frame)parent;
    }
    
    private static Hashtable getHeavyPopupCache() {
        Hashtable hashtable = (Hashtable)SwingUtilities.appContextGet(JPopupMenu.heavyPopupCacheKey);
        if (hashtable == null) {
            hashtable = new Hashtable(2);
            SwingUtilities.appContextPut(JPopupMenu.heavyPopupCacheKey, hashtable);
        }
        return hashtable;
    }
    
    public Component getInvoker() {
        return this.invoker;
    }
    
    public String getLabel() {
        return this.label;
    }
    
    private static Vector getLightPopupCache() {
        Vector vector = (Vector)SwingUtilities.appContextGet(JPopupMenu.lightPopupCacheKey);
        if (vector == null) {
            vector = new Vector();
            SwingUtilities.appContextPut(JPopupMenu.lightPopupCacheKey, vector);
        }
        return vector;
    }
    
    public Insets getMargin() {
        if (this.margin == null) {
            return new Insets(0, 0, 0, 0);
        }
        return this.margin;
    }
    
    private static Vector getMediumPopupCache() {
        Vector vector = (Vector)SwingUtilities.appContextGet(JPopupMenu.mediumPopupCacheKey);
        if (vector == null) {
            vector = new Vector();
            SwingUtilities.appContextPut(JPopupMenu.mediumPopupCacheKey, vector);
        }
        return vector;
    }
    
    static Popup getRecycledHeavyPopup(final Frame frame) {
        synchronized (JPopupMenu.classLock) {
            final Hashtable heavyPopupCache = getHeavyPopupCache();
            if (!heavyPopupCache.containsKey(frame)) {
                // monitorexit(JPopupMenu.classLock)
                return null;
            }
            final Vector<Popup> vector = heavyPopupCache.get(frame);
            if (vector.size() > 0) {
                final Popup popup2 = vector.elementAt(0);
                vector.removeElementAt(0);
                // monitorexit(JPopupMenu.classLock)
                return popup2;
            }
            // monitorexit(JPopupMenu.classLock)
            return null;
        }
    }
    
    static Popup getRecycledLightPopup() {
        synchronized (JPopupMenu.classLock) {
            final Vector lightPopupCache = getLightPopupCache();
            if (lightPopupCache.size() > 0) {
                final Popup popup = lightPopupCache.elementAt(0);
                lightPopupCache.removeElementAt(0);
                // monitorexit(JPopupMenu.classLock)
                return popup;
            }
            // monitorexit(JPopupMenu.classLock)
            return null;
        }
    }
    
    static Popup getRecycledMediumPopup() {
        synchronized (JPopupMenu.classLock) {
            final Vector mediumPopupCache = getMediumPopupCache();
            if (mediumPopupCache.size() > 0) {
                final Popup popup = mediumPopupCache.elementAt(0);
                mediumPopupCache.removeElementAt(0);
                // monitorexit(JPopupMenu.classLock)
                return popup;
            }
            // monitorexit(JPopupMenu.classLock)
            return null;
        }
    }
    
    JPopupMenu getRootPopupMenu() {
        JPopupMenu popupMenu;
        for (popupMenu = this; popupMenu != null && !popupMenu.isPopupMenu() && popupMenu.getInvoker() != null && popupMenu.getInvoker().getParent() != null && popupMenu.getInvoker().getParent() instanceof JPopupMenu; popupMenu = (JPopupMenu)popupMenu.getInvoker().getParent()) {}
        return popupMenu;
    }
    
    public SingleSelectionModel getSelectionModel() {
        return this.selectionModel;
    }
    
    public MenuElement[] getSubElements() {
        final Vector vector = new Vector<Component>();
        for (int componentCount = this.getComponentCount(), i = 0; i < componentCount; ++i) {
            final Component component = this.getComponent(i);
            if (component instanceof MenuElement) {
                vector.addElement(component);
            }
        }
        final MenuElement[] array = new MenuElement[vector.size()];
        for (int j = 0; j < vector.size(); ++j) {
            array[j] = (MenuElement)vector.elementAt(j);
        }
        return array;
    }
    
    public PopupMenuUI getUI() {
        return (PopupMenuUI)super.ui;
    }
    
    public String getUIClassID() {
        return "PopupMenuUI";
    }
    
    public void insert(final Component component, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("index less than zero.");
        }
        final int componentCount = this.getComponentCount();
        final Vector vector = new Vector<Component>();
        for (int i = n; i < componentCount; ++i) {
            vector.addElement(this.getComponent(n));
            this.remove(n);
        }
        this.add(component);
        for (int j = 0; j < vector.size(); ++j) {
            this.add(vector.elementAt(j));
        }
    }
    
    public void insert(final Action action, final int n) {
        throw new Error("void insert(Action, int) {} not yet implemented");
    }
    
    private boolean invokerInHeavyWeightPopup(final Component component) {
        if (component != null) {
            for (Container container = component.getParent(); container != null; container = container.getParent()) {
                if (container instanceof WindowPopup) {
                    return true;
                }
                if (container instanceof PanelPopup) {
                    break;
                }
                if (container instanceof JPanelPopup) {
                    break;
                }
            }
        }
        return false;
    }
    
    public boolean isBorderPainted() {
        return this.paintBorder;
    }
    
    public boolean isLightWeightPopupEnabled() {
        return this.lightWeightPopupEnabled;
    }
    
    private boolean isPopupMenu() {
        return this.invoker != null && !(this.invoker instanceof JMenu);
    }
    
    boolean isSubPopupMenu(final JPopupMenu popupMenu) {
        final int componentCount = this.getComponentCount();
        for (final Component component : this.getComponents()) {
            if (component instanceof JMenu) {
                final JPopupMenu popupMenu2 = ((JMenu)component).getPopupMenu();
                if (popupMenu2 == popupMenu) {
                    return true;
                }
                if (popupMenu2.isSubPopupMenu(popupMenu)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean isVisible() {
        return this.popup != null && this.popup.isShowing();
    }
    
    public void menuSelectionChanged(final boolean b) {
        if (this.invoker instanceof JMenu) {
            final JMenu menu = (JMenu)this.invoker;
            if (b) {
                menu.setPopupMenuVisible(true);
            }
            else {
                menu.setPopupMenuVisible(false);
            }
        }
        if (this.isPopupMenu() && !b) {
            this.setVisible(false);
        }
    }
    
    public void pack() {
        if (this.popup != null) {
            this.popup.pack();
        }
    }
    
    protected void paintBorder(final Graphics graphics) {
        if (this.isBorderPainted()) {
            super.paintBorder(graphics);
        }
    }
    
    protected String paramString() {
        final String s = (this.label != null) ? this.label : "";
        final String s2 = this.paintBorder ? "true" : "false";
        final String s3 = (this.margin != null) ? this.margin.toString() : "";
        String s4;
        if (this.lastPopupType == 0) {
            s4 = "LIGHT_WEIGHT_POPUP";
        }
        else if (this.lastPopupType == 1) {
            s4 = "MEDIUM_WEIGHT_POPUP";
        }
        else if (this.lastPopupType == 2) {
            s4 = "HEAVY_WEIGHT_POPUP";
        }
        else {
            s4 = "";
        }
        return String.valueOf(super.paramString()) + ",desiredLocationX=" + this.desiredLocationX + ",desiredLocationY=" + this.desiredLocationY + ",label=" + s + ",lastPopupType=" + s4 + ",lightWeightPopupEnabled=" + (this.lightWeightPopupEnabled ? "true" : "false") + ",margin=" + s3 + ",paintBorder=" + s2;
    }
    
    private boolean popupFit(final Rectangle rectangle) {
        if (this.invoker != null) {
            for (Container container = this.invoker.getParent(); container != null; container = container.getParent()) {
                if (container instanceof JFrame || container instanceof JDialog || container instanceof JWindow) {
                    return SwingUtilities.isRectangleContainingRectangle(container.getBounds(), rectangle);
                }
                if (container instanceof JApplet) {
                    final Rectangle bounds = container.getBounds();
                    final Point locationOnScreen = container.getLocationOnScreen();
                    bounds.x = locationOnScreen.x;
                    bounds.y = locationOnScreen.y;
                    return SwingUtilities.isRectangleContainingRectangle(bounds, rectangle);
                }
                if (container instanceof Frame) {
                    return SwingUtilities.isRectangleContainingRectangle(container.getBounds(), rectangle);
                }
            }
        }
        return false;
    }
    
    public void processKeyEvent(final KeyEvent keyEvent, final MenuElement[] array, final MenuSelectionManager menuSelectionManager) {
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent, final MenuElement[] array, final MenuSelectionManager menuSelectionManager) {
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        final Vector vector = (Vector)objectInputStream.readObject();
        int n = 0;
        final int size = vector.size();
        if (n < size && vector.elementAt(n).equals("invoker")) {
            this.invoker = vector.elementAt(++n);
            ++n;
        }
        if (n < size && vector.elementAt(n).equals("popup")) {
            this.popup = vector.elementAt(++n);
            ++n;
        }
        if (n < size && vector.elementAt(n).equals("frame")) {
            this.frame = vector.elementAt(++n);
            ++n;
        }
    }
    
    static void recycleHeavyPopup(final Popup popup) {
        synchronized (JPopupMenu.classLock) {
            final Frame frame = getFrame((Component)popup);
            final Hashtable heavyPopupCache = getHeavyPopupCache();
            Vector<Popup> vector;
            if (heavyPopupCache.containsKey(frame)) {
                vector = heavyPopupCache.get(frame);
            }
            else {
                vector = new Vector<Popup>();
                heavyPopupCache.put(frame, vector);
                frame.addWindowListener(new WindowAdapter() {
                    private final /* synthetic */ Frame val$f = val$f;
                    
                    public void windowClosed(final WindowEvent windowEvent) {
                        getHeavyPopupCache().remove(this.val$f);
                    }
                });
            }
            if (vector.size() < 5) {
                vector.addElement(popup);
            }
        }
        // monitorexit(JPopupMenu.classLock)
    }
    
    static void recycleLightPopup(final Popup popup) {
        synchronized (JPopupMenu.classLock) {
            final Vector lightPopupCache = getLightPopupCache();
            if (lightPopupCache.size() < 5) {
                lightPopupCache.addElement(popup);
            }
        }
        // monitorexit(JPopupMenu.classLock)
    }
    
    static void recycleMediumPopup(final Popup popup) {
        synchronized (JPopupMenu.classLock) {
            final Vector mediumPopupCache = getMediumPopupCache();
            if (mediumPopupCache.size() < 5) {
                mediumPopupCache.addElement(popup);
            }
        }
        // monitorexit(JPopupMenu.classLock)
    }
    
    static void recyclePopup(final Popup popup) {
        if (popup instanceof JPanelPopup) {
            recycleLightPopup(popup);
        }
        else if (popup instanceof WindowPopup) {
            recycleHeavyPopup(popup);
        }
        else if (popup instanceof PanelPopup) {
            recycleMediumPopup(popup);
        }
    }
    
    private void registerMenuItemForAction(final JMenuItem menuItem, final Action action) {
        final PropertyChangeListener actionChangeListener = this.createActionChangeListener(menuItem);
        if (JPopupMenu.listenerRegistry == null) {
            JPopupMenu.listenerRegistry = new Hashtable();
        }
        JPopupMenu.listenerRegistry.put(menuItem, actionChangeListener);
        JPopupMenu.listenerRegistry.put(actionChangeListener, action);
        action.addPropertyChangeListener(actionChangeListener);
    }
    
    public void remove(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("index less than zero.");
        }
        if (n > this.getComponentCount() - 1) {
            throw new IllegalArgumentException("index greater than the number of items.");
        }
        final Component component = this.getComponent(n);
        if (component instanceof JMenuItem) {
            this.unregisterMenuItemForAction((JMenuItem)component);
        }
        super.remove(n);
    }
    
    public void remove(final Component component) {
        super.remove(component);
        if (component instanceof JMenuItem) {
            this.unregisterMenuItemForAction((JMenuItem)component);
        }
    }
    
    public void removePopupMenuListener(final PopupMenuListener popupMenuListener) {
        super.listenerList.remove((JPopupMenu.class$javax$swing$event$PopupMenuListener != null) ? JPopupMenu.class$javax$swing$event$PopupMenuListener : (JPopupMenu.class$javax$swing$event$PopupMenuListener = class$("javax.swing.event.PopupMenuListener")), popupMenuListener);
    }
    
    private void replacePopup(final int n) {
        this.popup.removeComponent(this);
        recyclePopup(this.popup);
        this.popup = null;
        switch (n) {
            case 0: {
                this.popup = this.createLightWeightPopup();
                break;
            }
            case 1: {
                this.popup = this.createMediumWeightPopup();
                break;
            }
            case 2: {
                this.popup = this.createHeavyWeightPopup();
                break;
            }
        }
        this.popup.setLocationOnScreen(this.desiredLocationX, this.desiredLocationY);
        this.popup.addComponent(this, "Center");
        this.invalidate();
        this.popup.setBackground(this.getBackground());
        this.popup.pack();
    }
    
    public void setBorderPainted(final boolean paintBorder) {
        this.paintBorder = paintBorder;
        this.repaint();
    }
    
    public static void setDefaultLightWeightPopupEnabled(final boolean b) {
        SwingUtilities.appContextPut(JPopupMenu.defaultLWPopupEnabledKey, new Boolean(b));
    }
    
    public void setInvoker(final Component invoker) {
        final Component invoker2 = this.invoker;
        this.invoker = invoker;
        if (invoker2 != this.invoker && super.ui != null) {
            super.ui.uninstallUI(this);
            super.ui.installUI(this);
        }
        this.invalidate();
    }
    
    public void setLabel(final String label) {
        final String label2 = this.label;
        this.firePropertyChange("label", label2, this.label = label);
        if (super.accessibleContext != null) {
            super.accessibleContext.firePropertyChange("AccessibleVisibleData", label2, label);
        }
        this.invalidate();
        this.repaint();
    }
    
    public void setLightWeightPopupEnabled(final boolean lightWeightPopupEnabled) {
        this.lightWeightPopupEnabled = lightWeightPopupEnabled;
    }
    
    public void setLocation(final int desiredLocationX, final int desiredLocationY) {
        if (this.popup != null) {
            this.popup.setLocationOnScreen(desiredLocationX, desiredLocationY);
        }
        else {
            this.desiredLocationX = desiredLocationX;
            this.desiredLocationY = desiredLocationY;
        }
    }
    
    public void setPopupSize(final int n, final int n2) {
        if (this.popup != null) {
            this.popup.setSize(n, n2);
        }
    }
    
    public void setPopupSize(final Dimension dimension) {
        if (this.popup != null) {
            this.popup.setSize(dimension.width, dimension.height);
        }
    }
    
    public void setSelected(final Component component) {
        this.getSelectionModel().setSelectedIndex(this.getComponentIndex(component));
    }
    
    public void setSelectionModel(final SingleSelectionModel selectionModel) {
        this.selectionModel = selectionModel;
    }
    
    public void setUI(final PopupMenuUI ui) {
        super.setUI(ui);
    }
    
    public void setVisible(final boolean b) {
        if (b == this.isVisible()) {
            return;
        }
        if (!b) {
            this.getSelectionModel().clearSelection();
        }
        else if (this.isPopupMenu()) {
            if (this.getSubElements().length > 0) {
                MenuSelectionManager.defaultManager().setSelectedPath(new MenuElement[] { this, this.getSubElements()[0] });
            }
            else {
                MenuSelectionManager.defaultManager().setSelectedPath(new MenuElement[] { this });
            }
        }
        if (b) {
            final boolean ancestorIsModalDialog = this.ancestorIsModalDialog(this.invoker);
            this.firePopupMenuWillBecomeVisible();
            switch (this.lastPopupType) {
                case 0: {
                    this.popup = this.createLightWeightPopup();
                    break;
                }
                case 1: {
                    this.popup = this.createMediumWeightPopup();
                    break;
                }
                case 2: {
                    this.popup = this.createHeavyWeightPopup();
                    break;
                }
            }
            int lastPopupType = this.lastPopupType;
            this.popup.setLocationOnScreen(this.desiredLocationX, this.desiredLocationY);
            this.popup.addComponent(this, "Center");
            this.popup.setBackground(this.getBackground());
            this.popup.pack();
            int n;
            if (this.popupFit(new Rectangle(this.desiredLocationX, this.desiredLocationY, this.popup.getWidth(), this.popup.getHeight()))) {
                if (this.lightWeightPopupEnabled) {
                    n = 0;
                }
                else {
                    n = 1;
                }
            }
            else if (ancestorIsModalDialog) {
                n = 1;
            }
            else {
                n = 2;
            }
            if (this.invokerInHeavyWeightPopup(this.invoker)) {
                n = 2;
            }
            if (this.invoker == null) {
                n = 2;
            }
            if (n != lastPopupType) {
                this.replacePopup(n);
                lastPopupType = n;
            }
            this.lastPopupType = lastPopupType;
            this.popup.show(this.invoker);
        }
        else if (this.popup != null) {
            this.firePopupMenuWillBecomeInvisible();
            this.popup.hide();
            this.popup.removeComponent(this);
            recyclePopup(this.popup);
            this.popup = null;
        }
        if (super.accessibleContext != null) {
            if (b) {
                super.accessibleContext.firePropertyChange("AccessibleState", null, AccessibleState.VISIBLE);
            }
            else {
                super.accessibleContext.firePropertyChange("AccessibleState", AccessibleState.VISIBLE, null);
            }
        }
    }
    
    public void show(final Component invoker, final int n, final int n2) {
        this.setInvoker(invoker);
        final Frame frame = getFrame(invoker);
        if (frame != this.frame && frame != null) {
            this.frame = frame;
            if (this.popup != null) {
                this.setVisible(false);
            }
        }
        if (invoker != null) {
            final Point locationOnScreen = invoker.getLocationOnScreen();
            this.setLocation(locationOnScreen.x + n, locationOnScreen.y + n2);
        }
        else {
            this.setLocation(n, n2);
        }
        this.setVisible(true);
    }
    
    private void unregisterMenuItemForAction(final JMenuItem menuItem) {
        if (JPopupMenu.listenerRegistry != null) {
            final ActionChangedListener actionChangedListener = JPopupMenu.listenerRegistry.remove(menuItem);
            if (actionChangedListener != null) {
                final Action action = JPopupMenu.listenerRegistry.remove(actionChangedListener);
                if (action != null) {
                    menuItem.removeActionListener(action);
                    action.removePropertyChangeListener(actionChangedListener);
                }
                actionChangedListener.setTarget(null);
            }
        }
    }
    
    public void updateUI() {
        this.setUI((PopupMenuUI)UIManager.getUI(this));
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        final Vector<String> vector = new Vector<String>();
        objectOutputStream.defaultWriteObject();
        if (this.invoker != null && this.invoker instanceof Serializable) {
            vector.addElement("invoker");
            vector.addElement((String)this.invoker);
        }
        if (this.popup != null && this.popup instanceof Serializable) {
            vector.addElement("popup");
            vector.addElement((String)this.popup);
        }
        if (this.frame != null && this.frame instanceof Serializable) {
            vector.addElement("frame");
            vector.addElement((String)this.frame);
        }
        objectOutputStream.writeObject(vector);
        if (super.ui != null && this.getUIClassID().equals("PopupMenuUI")) {
            super.ui.installUI(this);
        }
    }
    
    private class ActionChangedListener implements PropertyChangeListener
    {
        JMenuItem menuItem;
        
        ActionChangedListener(final JMenuItem target) {
            this.setTarget(target);
        }
        
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            final String propertyName = propertyChangeEvent.getPropertyName();
            if (propertyChangeEvent.getPropertyName().equals("Name")) {
                this.menuItem.setText((String)propertyChangeEvent.getNewValue());
            }
            else if (propertyName.equals("enabled")) {
                this.menuItem.setEnabled((boolean)propertyChangeEvent.getNewValue());
            }
            else if (propertyChangeEvent.getPropertyName().equals("SmallIcon")) {
                this.menuItem.setIcon((Icon)propertyChangeEvent.getNewValue());
                this.menuItem.invalidate();
                this.menuItem.repaint();
            }
        }
        
        public void setTarget(final JMenuItem menuItem) {
            this.menuItem = menuItem;
        }
    }
    
    class WindowPopup extends JWindow implements Popup, Serializable, Accessible
    {
        int saveX;
        int saveY;
        boolean firstShow;
        protected AccessibleContext accessibleContext;
        
        public WindowPopup(final Frame frame) {
            super(frame);
            this.firstShow = true;
            this.accessibleContext = null;
        }
        
        public void addComponent(final Component component, final Object o) {
            this.getContentPane().add(component, o);
        }
        
        public AccessibleContext getAccessibleContext() {
            if (this.accessibleContext == null) {
                this.accessibleContext = new AccessibleWindowPopup();
            }
            return this.accessibleContext;
        }
        
        public Rectangle getBoundsOnScreen() {
            return this.getBounds();
        }
        
        public Component getComponent() {
            return this;
        }
        
        public int getHeight() {
            return this.getBounds().height;
        }
        
        public int getWidth() {
            return this.getBounds().width;
        }
        
        public void hide() {
            super.hide();
            this.removeNotify();
        }
        
        public void removeComponent(final Component component) {
            this.getContentPane().remove(component);
        }
        
        public void setLocationOnScreen(final int saveX, final int saveY) {
            this.setLocation(saveX, saveY);
            this.saveX = saveX;
            this.saveY = saveY;
        }
        
        public void show(final Component component) {
            this.setLocation(this.saveX, this.saveY);
            this.setVisible(true);
            if (this.firstShow) {
                this.hide();
                this.setVisible(true);
                this.firstShow = false;
            }
        }
        
        public void update(final Graphics graphics) {
            this.paint(graphics);
        }
        
        protected class AccessibleWindowPopup extends AccessibleContext implements Serializable, AccessibleComponent
        {
            public void addFocusListener(final FocusListener focusListener) {
                WindowPopup.this.addFocusListener(focusListener);
            }
            
            public boolean contains(final Point point) {
                return WindowPopup.this.contains(point);
            }
            
            public Accessible getAccessibleAt(final Point point) {
                return SwingUtilities.getAccessibleAt(WindowPopup.this, point);
            }
            
            public Accessible getAccessibleChild(final int n) {
                return SwingUtilities.getAccessibleChild(WindowPopup.this, n);
            }
            
            public int getAccessibleChildrenCount() {
                return SwingUtilities.getAccessibleChildrenCount(WindowPopup.this);
            }
            
            public AccessibleComponent getAccessibleComponent() {
                return this;
            }
            
            public int getAccessibleIndexInParent() {
                return SwingUtilities.getAccessibleIndexInParent(WindowPopup.this);
            }
            
            public Accessible getAccessibleParent() {
                if (super.accessibleParent != null) {
                    return super.accessibleParent;
                }
                final Container parent = WindowPopup.this.getParent();
                if (parent instanceof Accessible) {
                    return (Accessible)parent;
                }
                return null;
            }
            
            public AccessibleRole getAccessibleRole() {
                return AccessibleRole.WINDOW;
            }
            
            public AccessibleStateSet getAccessibleStateSet() {
                final AccessibleStateSet accessibleStateSet = SwingUtilities.getAccessibleStateSet(WindowPopup.this);
                if (WindowPopup.this.getFocusOwner() != null) {
                    accessibleStateSet.add(AccessibleState.ACTIVE);
                }
                return accessibleStateSet;
            }
            
            public Color getBackground() {
                return WindowPopup.this.getBackground();
            }
            
            public Rectangle getBounds() {
                return WindowPopup.this.getBounds();
            }
            
            public Cursor getCursor() {
                return WindowPopup.this.getCursor();
            }
            
            public Font getFont() {
                return WindowPopup.this.getFont();
            }
            
            public FontMetrics getFontMetrics(final Font font) {
                return WindowPopup.this.getFontMetrics(font);
            }
            
            public Color getForeground() {
                return WindowPopup.this.getForeground();
            }
            
            public Locale getLocale() {
                return WindowPopup.this.getLocale();
            }
            
            public Point getLocation() {
                return WindowPopup.this.getLocation();
            }
            
            public Point getLocationOnScreen() {
                return WindowPopup.this.getLocationOnScreen();
            }
            
            public Dimension getSize() {
                return WindowPopup.this.getSize();
            }
            
            public boolean isEnabled() {
                return WindowPopup.this.isEnabled();
            }
            
            public boolean isFocusTraversable() {
                return WindowPopup.this.isFocusTraversable();
            }
            
            public boolean isShowing() {
                return WindowPopup.this.isShowing();
            }
            
            public boolean isVisible() {
                return WindowPopup.this.isVisible();
            }
            
            public void removeFocusListener(final FocusListener focusListener) {
                WindowPopup.this.removeFocusListener(focusListener);
            }
            
            public void requestFocus() {
                WindowPopup.this.requestFocus();
            }
            
            public void setBackground(final Color background) {
                WindowPopup.this.setBackground(background);
            }
            
            public void setBounds(final Rectangle bounds) {
                WindowPopup.this.setBounds(bounds);
            }
            
            public void setCursor(final Cursor cursor) {
                WindowPopup.this.setCursor(cursor);
            }
            
            public void setEnabled(final boolean enabled) {
                WindowPopup.this.setEnabled(enabled);
            }
            
            public void setFont(final Font font) {
                WindowPopup.this.setFont(font);
            }
            
            public void setForeground(final Color foreground) {
                WindowPopup.this.setForeground(foreground);
            }
            
            public void setLocation(final Point location) {
                WindowPopup.this.setLocation(location);
            }
            
            public void setSize(final Dimension size) {
                WindowPopup.this.setSize(size);
            }
            
            public void setVisible(final boolean visible) {
                WindowPopup.this.setVisible(visible);
            }
        }
    }
    
    class JPanelPopup extends JPanel implements Popup, Serializable
    {
        int desiredLocationX;
        int desiredLocationY;
        
        public JPanelPopup() {
            this.setLayout(new BorderLayout());
            this.setDoubleBuffered(true);
            this.setOpaque(true);
        }
        
        public void addComponent(final Component component, final Object o) {
            this.add(component, o);
        }
        
        Point convertParentLocationToScreen(final Container container, final int n, final int n2) {
            Component component = null;
            for (Container parent = this; parent != null; parent = parent.getParent()) {
                if (parent instanceof Window) {
                    component = parent;
                    break;
                }
            }
            if (component != null) {
                final Rectangle bounds = component.getBounds();
                final Point convertPoint;
                final Point point = convertPoint = SwingUtilities.convertPoint(container, new Point(n, n2), null);
                convertPoint.x += bounds.x;
                final Point point2 = point;
                point2.y += bounds.y;
                return point;
            }
            throw new Error("convertParentLocationToScreen: no window ancestor found");
        }
        
        Point convertScreenLocationToParent(final Container container, final int n, final int n2) {
            Window window = null;
            for (Container parent = container; parent != null; parent = parent.getParent()) {
                if (parent instanceof Window) {
                    window = (Window)parent;
                    break;
                }
            }
            if (window != null) {
                final Point point = new Point(n, n2);
                SwingUtilities.convertPointFromScreen(point, container);
                return point;
            }
            throw new Error("convertScreenLocationToParent: no window ancestor found");
        }
        
        public Rectangle getBoundsOnScreen() {
            final Container parent = this.getParent();
            if (parent != null) {
                final Rectangle bounds = this.getBounds();
                final Point convertParentLocationToScreen = this.convertParentLocationToScreen(parent, bounds.x, bounds.y);
                bounds.x = convertParentLocationToScreen.x;
                bounds.y = convertParentLocationToScreen.y;
                return bounds;
            }
            throw new Error("getBoundsOnScreen called on an invisible popup");
        }
        
        public Component getComponent() {
            return this;
        }
        
        public void hide() {
            final Container parent = this.getParent();
            final Rectangle bounds = this.getBounds();
            if (parent != null) {
                parent.remove(this);
            }
            parent.repaint(bounds.x, bounds.y, bounds.width, bounds.height);
        }
        
        public void pack() {
            this.setSize(this.getPreferredSize());
        }
        
        public void removeComponent(final Component component) {
            this.remove(component);
        }
        
        public void setLocationOnScreen(final int desiredLocationX, final int desiredLocationY) {
            final Container parent = this.getParent();
            if (parent != null) {
                final Point convertScreenLocationToParent = this.convertScreenLocationToParent(parent, desiredLocationX, desiredLocationY);
                this.setLocation(convertScreenLocationToParent.x, convertScreenLocationToParent.y);
            }
            else {
                this.desiredLocationX = desiredLocationX;
                this.desiredLocationY = desiredLocationY;
            }
        }
        
        public void show(final Component component) {
            Container container = null;
            if (component != null) {
                container = component.getParent();
            }
            for (Container parent = container; parent != null; parent = parent.getParent()) {
                if (parent instanceof JRootPane) {
                    if (!(parent.getParent() instanceof JInternalFrame)) {
                        container = ((JRootPane)parent).getLayeredPane();
                        Container container2;
                        for (container2 = container.getParent(); container2 != null && !(container2 instanceof Window); container2 = container2.getParent()) {}
                        final Window window = (Window)container2;
                        break;
                    }
                }
                else if (parent instanceof Window) {
                    container = parent;
                    final Window window2 = (Window)parent;
                    break;
                }
            }
            final Point convertScreenLocationToParent = this.convertScreenLocationToParent(container, this.desiredLocationX, this.desiredLocationY);
            this.setLocation(convertScreenLocationToParent.x, convertScreenLocationToParent.y);
            if (container instanceof JLayeredPane) {
                ((JLayeredPane)container).add(this, JLayeredPane.POPUP_LAYER, 0);
            }
            else {
                container.add(this);
            }
        }
        
        public void update(final Graphics graphics) {
            this.paint(graphics);
        }
    }
    
    class PanelPopup extends Panel implements Popup, Serializable
    {
        int desiredLocationX;
        int desiredLocationY;
        JRootPane rootPane;
        
        public PanelPopup() {
            this.setLayout(new BorderLayout());
            this.add(this.rootPane = new JRootPane(), "Center");
        }
        
        public void addComponent(final Component component, final Object o) {
            this.rootPane.getContentPane().add(component, o);
        }
        
        Point convertParentLocationToScreen(final Container container, final int n, final int n2) {
            Component component = null;
            for (Container parent = this; parent != null; parent = parent.getParent()) {
                if (parent instanceof Window) {
                    component = parent;
                    break;
                }
            }
            if (component != null) {
                final Rectangle bounds = component.getBounds();
                final Point convertPoint;
                final Point point = convertPoint = SwingUtilities.convertPoint(container, new Point(n, n2), null);
                convertPoint.x += bounds.x;
                final Point point2 = point;
                point2.y += bounds.y;
                return point;
            }
            throw new Error("convertParentLocationToScreen: no window ancestor found");
        }
        
        Point convertScreenLocationToParent(final Container container, final int n, final int n2) {
            Window window = null;
            for (Container parent = container; parent != null; parent = parent.getParent()) {
                if (parent instanceof Window) {
                    window = (Window)parent;
                    break;
                }
            }
            if (window != null) {
                final Point point = new Point(n, n2);
                SwingUtilities.convertPointFromScreen(point, container);
                return point;
            }
            throw new Error("convertScreenLocationToParent: no window ancestor found");
        }
        
        public Rectangle getBoundsOnScreen() {
            final Container parent = this.getParent();
            if (parent != null) {
                final Rectangle bounds = this.getBounds();
                final Point convertParentLocationToScreen = this.convertParentLocationToScreen(parent, bounds.x, bounds.y);
                bounds.x = convertParentLocationToScreen.x;
                bounds.y = convertParentLocationToScreen.y;
                return bounds;
            }
            throw new Error("getBoundsOnScreen called on an invisible popup");
        }
        
        public Component getComponent() {
            return this;
        }
        
        public int getHeight() {
            return this.getBounds().height;
        }
        
        public int getWidth() {
            return this.getBounds().width;
        }
        
        public void hide() {
            final Container parent = this.getParent();
            final Rectangle bounds = this.getBounds();
            if (parent != null) {
                parent.remove(this);
            }
            parent.repaint(bounds.x, bounds.y, bounds.width, bounds.height);
        }
        
        public void pack() {
            this.setSize(this.getPreferredSize());
        }
        
        public void paint(final Graphics graphics) {
            super.paint(graphics);
        }
        
        public void removeComponent(final Component component) {
            this.rootPane.getContentPane().remove(component);
        }
        
        public void setLocationOnScreen(final int desiredLocationX, final int desiredLocationY) {
            final Container parent = this.getParent();
            if (parent != null) {
                final Point convertScreenLocationToParent = this.convertScreenLocationToParent(parent, desiredLocationX, desiredLocationY);
                this.setLocation(convertScreenLocationToParent.x, convertScreenLocationToParent.y);
            }
            else {
                this.desiredLocationX = desiredLocationX;
                this.desiredLocationY = desiredLocationY;
            }
        }
        
        public void show(final Component component) {
            Container container = null;
            if (component != null) {
                container = component.getParent();
            }
            while (!(container instanceof Window) && !(container instanceof Applet) && container != null) {
                container = container.getParent();
            }
            if (container instanceof RootPaneContainer) {
                final JLayeredPane layeredPane = ((RootPaneContainer)container).getLayeredPane();
                final Point convertScreenLocationToParent = this.convertScreenLocationToParent(layeredPane, this.desiredLocationX, this.desiredLocationY);
                this.setLocation(convertScreenLocationToParent.x, convertScreenLocationToParent.y);
                layeredPane.add(this, JLayeredPane.POPUP_LAYER, 0);
            }
            else {
                final Point convertScreenLocationToParent2 = this.convertScreenLocationToParent(container, this.desiredLocationX, this.desiredLocationY);
                this.setLocation(convertScreenLocationToParent2.x, convertScreenLocationToParent2.y);
                container.add(this);
            }
        }
        
        public void update(final Graphics graphics) {
            this.paint(graphics);
        }
    }
    
    protected class AccessibleJPopupMenu extends AccessibleJComponent
    {
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.POPUP_MENU;
        }
    }
    
    public static class Separator extends JSeparator
    {
        public Separator() {
            super(0);
        }
        
        public String getUIClassID() {
            return "PopupMenuSeparatorUI";
        }
    }
    
    private interface Popup
    {
        void addComponent(final Component p0, final Object p1);
        
        Rectangle getBoundsOnScreen();
        
        Component getComponent();
        
        int getHeight();
        
        int getWidth();
        
        void hide();
        
        boolean isShowing();
        
        void pack();
        
        void removeComponent(final Component p0);
        
        void setBackground(final Color p0);
        
        void setLocationOnScreen(final int p0, final int p1);
        
        void setSize(final int p0, final int p1);
        
        void show(final Component p0);
    }
}
