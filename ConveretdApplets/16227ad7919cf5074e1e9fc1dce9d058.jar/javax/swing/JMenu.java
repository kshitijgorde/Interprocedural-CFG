// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleSelection;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.event.ChangeEvent;
import java.io.Serializable;
import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.plaf.PopupMenuUI;
import javax.swing.plaf.MenuItemUI;
import javax.accessibility.AccessibleState;
import java.awt.event.KeyEvent;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Point;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;
import java.util.Enumeration;
import java.beans.PropertyChangeListener;
import java.util.Vector;
import java.util.EventListener;
import javax.swing.event.MenuListener;
import java.awt.event.ActionListener;
import javax.accessibility.AccessibleContext;
import java.awt.Component;
import java.util.Hashtable;
import javax.swing.event.MenuEvent;
import javax.swing.event.ChangeListener;
import javax.accessibility.Accessible;

public class JMenu extends JMenuItem implements Accessible, MenuElement
{
    private static final String uiClassID = "MenuUI";
    private JPopupMenu popupMenu;
    private ChangeListener menuChangeListener;
    private MenuEvent menuEvent;
    private static Hashtable listenerRegistry;
    private int delay;
    protected WinListener popupListener;
    static /* synthetic */ Class class$javax$swing$event$MenuListener;
    
    static {
        JMenu.listenerRegistry = null;
    }
    
    public JMenu() {
        this("");
    }
    
    public JMenu(final String s) {
        super(s);
        this.menuChangeListener = null;
        this.menuEvent = null;
    }
    
    public JMenu(final String s, final boolean b) {
        this(s);
    }
    
    public Component add(final Component component) {
        if (component instanceof JComponent) {
            final AccessibleContext accessibleContext = ((JComponent)component).getAccessibleContext();
            if (accessibleContext != null) {
                accessibleContext.setAccessibleParent(this);
            }
        }
        this.ensurePopupMenuCreated();
        this.popupMenu.add(component);
        return component;
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
        menuItem.getAccessibleContext().setAccessibleParent(this);
        this.ensurePopupMenuCreated();
        return this.popupMenu.add(menuItem);
    }
    
    public void addMenuListener(final MenuListener menuListener) {
        super.listenerList.add((JMenu.class$javax$swing$event$MenuListener != null) ? JMenu.class$javax$swing$event$MenuListener : (JMenu.class$javax$swing$event$MenuListener = class$("javax.swing.event.MenuListener")), menuListener);
    }
    
    public void addSeparator() {
        this.ensurePopupMenuCreated();
        this.popupMenu.addSeparator();
    }
    
    private MenuElement[] buildMenuElementArray(final JMenu menu) {
        final Vector vector = new Vector<JPopupMenu>();
        Component component = menu.getPopupMenu();
        while (true) {
            if (component instanceof JPopupMenu) {
                final JPopupMenu popupMenu = (JPopupMenu)component;
                vector.insertElementAt(popupMenu, 0);
                component = popupMenu.getInvoker();
            }
            else if (component instanceof JMenu) {
                final JMenu menu2 = (JMenu)component;
                vector.insertElementAt((JPopupMenu)menu2, 0);
                component = menu2.getParent();
            }
            else {
                if (component instanceof JMenuBar) {
                    break;
                }
                continue;
            }
        }
        vector.insertElementAt((JPopupMenu)component, 0);
        final MenuElement[] array = new MenuElement[vector.size()];
        vector.copyInto(array);
        return array;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    private void clearListenerRegistry() {
        if (JMenu.listenerRegistry != null) {
            final Enumeration<JMenu> keys = JMenu.listenerRegistry.keys();
            while (keys.hasMoreElements()) {
                final JMenu nextElement = keys.nextElement();
                if (nextElement == this) {
                    final JMenu menu = nextElement;
                    final ActionChangedListener actionChangedListener = JMenu.listenerRegistry.get(menu);
                    if (actionChangedListener == null) {
                        continue;
                    }
                    final Action action = JMenu.listenerRegistry.get(actionChangedListener);
                    if (action != null) {
                        menu.removeActionListener(action);
                        action.removePropertyChangeListener(actionChangedListener);
                    }
                    actionChangedListener.setTarget(null);
                }
            }
            JMenu.listenerRegistry.clear();
        }
    }
    
    protected PropertyChangeListener createActionChangeListener(final JMenuItem menuItem) {
        return new ActionChangedListener(menuItem);
    }
    
    private ChangeListener createMenuChangeListener() {
        return new MenuChangeListener();
    }
    
    protected WinListener createWinListener(final JPopupMenu popupMenu) {
        return new WinListener(popupMenu);
    }
    
    public void doClick(final int n) {
        MenuSelectionManager.defaultManager().setSelectedPath(this.buildMenuElementArray(this));
    }
    
    private void ensurePopupMenuCreated() {
        if (this.popupMenu == null) {
            (this.popupMenu = new JPopupMenu()).setInvoker(this);
            this.popupListener = this.createWinListener(this.popupMenu);
            this.popupMenu.addPopupMenuListener(new PopupMenuListener() {
                public void popupMenuCanceled(final PopupMenuEvent popupMenuEvent) {
                    JMenu.this.fireMenuCanceled();
                }
                
                public void popupMenuWillBecomeInvisible(final PopupMenuEvent popupMenuEvent) {
                }
                
                public void popupMenuWillBecomeVisible(final PopupMenuEvent popupMenuEvent) {
                }
            });
        }
    }
    
    protected void fireMenuCanceled() {
        final Object[] listenerList = super.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((JMenu.class$javax$swing$event$MenuListener != null) ? JMenu.class$javax$swing$event$MenuListener : (JMenu.class$javax$swing$event$MenuListener = class$("javax.swing.event.MenuListener")))) {
                if (listenerList[i + 1] == null) {
                    throw new Error(String.valueOf(this.getText()) + " has a NULL Listener!! " + i);
                }
                if (this.menuEvent == null) {
                    this.menuEvent = new MenuEvent(this);
                }
                ((MenuListener)listenerList[i + 1]).menuCanceled(this.menuEvent);
            }
        }
    }
    
    protected void fireMenuDeselected() {
        final Object[] listenerList = super.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((JMenu.class$javax$swing$event$MenuListener != null) ? JMenu.class$javax$swing$event$MenuListener : (JMenu.class$javax$swing$event$MenuListener = class$("javax.swing.event.MenuListener")))) {
                if (listenerList[i + 1] == null) {
                    throw new Error(String.valueOf(this.getText()) + " has a NULL Listener!! " + i);
                }
                if (this.menuEvent == null) {
                    this.menuEvent = new MenuEvent(this);
                }
                ((MenuListener)listenerList[i + 1]).menuDeselected(this.menuEvent);
            }
        }
    }
    
    protected void fireMenuSelected() {
        final Object[] listenerList = super.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((JMenu.class$javax$swing$event$MenuListener != null) ? JMenu.class$javax$swing$event$MenuListener : (JMenu.class$javax$swing$event$MenuListener = class$("javax.swing.event.MenuListener")))) {
                if (listenerList[i + 1] == null) {
                    throw new Error(String.valueOf(this.getText()) + " has a NULL Listener!! " + i);
                }
                if (this.menuEvent == null) {
                    this.menuEvent = new MenuEvent(this);
                }
                ((MenuListener)listenerList[i + 1]).menuSelected(this.menuEvent);
            }
        }
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJMenu();
        }
        return super.accessibleContext;
    }
    
    public Component getComponent() {
        return this;
    }
    
    public int getDelay() {
        return this.delay;
    }
    
    public JMenuItem getItem(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("index less than zero.");
        }
        final Component menuComponent = this.getMenuComponent(n);
        if (menuComponent instanceof JMenuItem) {
            return (JMenuItem)menuComponent;
        }
        return null;
    }
    
    public int getItemCount() {
        return this.getMenuComponentCount();
    }
    
    public Component getMenuComponent(final int n) {
        if (this.popupMenu != null) {
            return this.popupMenu.getComponent(n);
        }
        return null;
    }
    
    public int getMenuComponentCount() {
        int componentCount = 0;
        if (this.popupMenu != null) {
            componentCount = this.popupMenu.getComponentCount();
        }
        return componentCount;
    }
    
    public Component[] getMenuComponents() {
        if (this.popupMenu != null) {
            return this.popupMenu.getComponents();
        }
        return new Component[0];
    }
    
    public JPopupMenu getPopupMenu() {
        this.ensurePopupMenuCreated();
        return this.popupMenu;
    }
    
    private Point getPopupMenuOrigin() {
        final JPopupMenu popupMenu = this.getPopupMenu();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        final Dimension size = this.getSize();
        Dimension dimension = popupMenu.getSize();
        if (dimension.width == 0) {
            dimension = popupMenu.getPreferredSize();
        }
        final Point locationOnScreen = this.getLocationOnScreen();
        int n;
        int height;
        if (this.getParent() instanceof JPopupMenu) {
            if (SwingUtilities.isLeftToRight(this)) {
                if (locationOnScreen.x + size.width + dimension.width < screenSize.width) {
                    n = size.width;
                }
                else {
                    n = -dimension.width;
                }
            }
            else if (locationOnScreen.x < dimension.width) {
                n = size.width;
            }
            else {
                n = -dimension.width;
            }
            if (locationOnScreen.y + dimension.height < screenSize.height) {
                height = 0;
            }
            else {
                height = size.height - dimension.height;
            }
        }
        else {
            if (SwingUtilities.isLeftToRight(this)) {
                if (locationOnScreen.x + dimension.width < screenSize.width) {
                    n = 0;
                }
                else {
                    n = size.width - dimension.width;
                }
            }
            else if (locationOnScreen.x + size.width < dimension.width) {
                n = 0;
            }
            else {
                n = size.width - dimension.width;
            }
            if (locationOnScreen.y + size.height + dimension.height < screenSize.height) {
                height = size.height;
            }
            else {
                height = -dimension.height;
            }
        }
        return new Point(n, height);
    }
    
    public MenuElement[] getSubElements() {
        if (this.popupMenu == null) {
            return new MenuElement[0];
        }
        return new MenuElement[] { this.popupMenu };
    }
    
    public String getUIClassID() {
        return "MenuUI";
    }
    
    public void insert(final String s, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("index less than zero.");
        }
        this.ensurePopupMenuCreated();
        this.popupMenu.insert(new JMenuItem(s), n);
    }
    
    public JMenuItem insert(final Action action, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("index less than zero.");
        }
        this.ensurePopupMenuCreated();
        final JMenuItem menuItem = new JMenuItem((String)action.getValue("Name"), (Icon)action.getValue("SmallIcon"));
        menuItem.setHorizontalTextPosition(4);
        menuItem.setVerticalTextPosition(0);
        menuItem.setEnabled(action.isEnabled());
        menuItem.addActionListener(action);
        this.popupMenu.insert(menuItem, n);
        this.registerMenuItemForAction(menuItem, action);
        return menuItem;
    }
    
    public JMenuItem insert(final JMenuItem menuItem, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("index less than zero.");
        }
        menuItem.getAccessibleContext().setAccessibleParent(this);
        this.ensurePopupMenuCreated();
        this.popupMenu.insert(menuItem, n);
        return menuItem;
    }
    
    public void insertSeparator(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("index less than zero.");
        }
        this.ensurePopupMenuCreated();
        this.popupMenu.insert(new JPopupMenu.Separator(), n);
    }
    
    public boolean isMenuComponent(final Component component) {
        if (component == this) {
            return true;
        }
        if (component instanceof JPopupMenu && component == this.getPopupMenu()) {
            return true;
        }
        final int menuComponentCount = this.getMenuComponentCount();
        for (final Component component2 : this.getMenuComponents()) {
            if (component2 == component) {
                return true;
            }
            if (component2 instanceof JMenu && ((JMenu)component2).isMenuComponent(component)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean isPopupMenuVisible() {
        this.ensurePopupMenuCreated();
        return this.popupMenu.isVisible();
    }
    
    public boolean isSelected() {
        return this.getModel().isSelected();
    }
    
    public boolean isTearOff() {
        throw new Error("boolean isTearOff() {} not yet implemented");
    }
    
    public boolean isTopLevelMenu() {
        return this.getParent() instanceof JMenuBar;
    }
    
    public void menuSelectionChanged(final boolean selected) {
        this.setSelected(selected);
    }
    
    protected String paramString() {
        return super.paramString();
    }
    
    protected void processKeyEvent(final KeyEvent keyEvent) {
        MenuSelectionManager.defaultManager().processKeyEvent(keyEvent);
        if (keyEvent.isConsumed()) {
            return;
        }
        if (this.isSelected() && (keyEvent.getKeyCode() == 9 || keyEvent.getKeyChar() == '\t')) {
            if (UIManager.get("Menu.consumesTabs") == Boolean.TRUE) {
                keyEvent.consume();
                return;
            }
            MenuSelectionManager.defaultManager().clearSelectedPath();
        }
        super.processKeyEvent(keyEvent);
    }
    
    private void registerMenuItemForAction(final JMenuItem menuItem, final Action action) {
        final PropertyChangeListener actionChangeListener = this.createActionChangeListener(menuItem);
        if (JMenu.listenerRegistry == null) {
            JMenu.listenerRegistry = new Hashtable();
        }
        JMenu.listenerRegistry.put(menuItem, actionChangeListener);
        JMenu.listenerRegistry.put(actionChangeListener, action);
        action.addPropertyChangeListener(actionChangeListener);
    }
    
    public void remove(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("index less than zero.");
        }
        if (n > this.getItemCount()) {
            throw new IllegalArgumentException("index greater than the number of items.");
        }
        final JMenuItem item = this.getItem(n);
        if (item instanceof JMenuItem) {
            this.unregisterMenuItemForAction(item);
        }
        if (this.popupMenu != null) {
            this.popupMenu.remove(n);
        }
    }
    
    public void remove(final Component component) {
        if (this.popupMenu != null) {
            this.popupMenu.remove(component);
        }
    }
    
    public void remove(final JMenuItem menuItem) {
        if (this.popupMenu != null) {
            this.popupMenu.remove(menuItem);
        }
        this.unregisterMenuItemForAction(menuItem);
    }
    
    public void removeAll() {
        if (this.popupMenu != null) {
            this.popupMenu.removeAll();
        }
        this.clearListenerRegistry();
    }
    
    public void removeMenuListener(final MenuListener menuListener) {
        super.listenerList.remove((JMenu.class$javax$swing$event$MenuListener != null) ? JMenu.class$javax$swing$event$MenuListener : (JMenu.class$javax$swing$event$MenuListener = class$("javax.swing.event.MenuListener")), menuListener);
    }
    
    public void setAccelerator(final KeyStroke keyStroke) {
        throw new Error("setAccelerator() is not defined for JMenu.  Use setMnemonic() instead.");
    }
    
    public void setDelay(final int delay) {
        if (delay < 0) {
            throw new IllegalArgumentException("Delay must be a positive integer");
        }
        this.delay = delay;
    }
    
    public void setMenuLocation(final int n, final int n2) {
        if (this.popupMenu != null) {
            this.popupMenu.setLocation(n, n2);
        }
    }
    
    public void setModel(final ButtonModel buttonModel) {
        final ButtonModel model = this.getModel();
        super.setModel(buttonModel);
        if (model != null && this.menuChangeListener != null) {
            model.removeChangeListener(this.menuChangeListener);
            this.menuChangeListener = null;
        }
        if ((super.model = buttonModel) != null) {
            buttonModel.addChangeListener(this.menuChangeListener = this.createMenuChangeListener());
        }
    }
    
    public void setPopupMenuVisible(final boolean b) {
        if (!this.isEnabled()) {
            return;
        }
        if (b != this.isPopupMenuVisible()) {
            this.ensurePopupMenuCreated();
            if (b && this.isShowing()) {
                final Point popupMenuOrigin = this.getPopupMenuOrigin();
                this.getPopupMenu().show(this, popupMenuOrigin.x, popupMenuOrigin.y);
            }
            else {
                this.getPopupMenu().setVisible(false);
            }
        }
    }
    
    public void setSelected(final boolean selected) {
        final ButtonModel model = this.getModel();
        final boolean selected2 = model.isSelected();
        if (super.accessibleContext != null && selected2 != selected) {
            if (selected) {
                super.accessibleContext.firePropertyChange("AccessibleState", null, AccessibleState.SELECTED);
            }
            else {
                super.accessibleContext.firePropertyChange("AccessibleState", AccessibleState.SELECTED, null);
            }
        }
        if (selected != model.isSelected()) {
            this.getModel().setSelected(selected);
        }
    }
    
    private Point translateToPopupMenu(final int n, final int n2) {
        int n3;
        int n4;
        if (this.getParent() instanceof JPopupMenu) {
            n3 = n - this.getSize().width;
            n4 = n2;
        }
        else {
            n3 = n;
            n4 = n2 - this.getSize().height;
        }
        return new Point(n3, n4);
    }
    
    private Point translateToPopupMenu(final Point point) {
        return this.translateToPopupMenu(point.x, point.y);
    }
    
    private void unregisterMenuItemForAction(final JMenuItem menuItem) {
        if (JMenu.listenerRegistry != null) {
            final ActionChangedListener actionChangedListener = JMenu.listenerRegistry.remove(menuItem);
            if (actionChangedListener != null) {
                final Action action = JMenu.listenerRegistry.remove(actionChangedListener);
                if (action != null) {
                    menuItem.removeActionListener(action);
                    action.removePropertyChangeListener(actionChangedListener);
                }
                actionChangedListener.setTarget(null);
            }
        }
    }
    
    public void updateUI() {
        this.setUI((MenuItemUI)UIManager.getUI(this));
        if (this.popupMenu != null) {
            this.popupMenu.setUI((PopupMenuUI)UIManager.getUI(this.popupMenu));
        }
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (super.ui != null && this.getUIClassID().equals("MenuUI")) {
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
    
    class MenuChangeListener implements ChangeListener, Serializable
    {
        boolean isSelected;
        
        MenuChangeListener() {
            this.isSelected = false;
        }
        
        public void stateChanged(final ChangeEvent changeEvent) {
            final boolean selected = ((ButtonModel)changeEvent.getSource()).isSelected();
            if (selected != this.isSelected) {
                if (selected) {
                    JMenu.this.fireMenuSelected();
                }
                else {
                    JMenu.this.fireMenuDeselected();
                }
                this.isSelected = selected;
            }
        }
    }
    
    protected class WinListener extends WindowAdapter implements Serializable
    {
        JPopupMenu popupMenu;
        
        public WinListener(final JPopupMenu popupMenu) {
            this.popupMenu = popupMenu;
        }
        
        public void windowClosing(final WindowEvent windowEvent) {
            JMenu.this.setSelected(false);
        }
    }
    
    protected class AccessibleJMenu extends AccessibleJMenuItem implements AccessibleSelection
    {
        public void addAccessibleSelection(final int n) {
            if (n < 0 || n >= JMenu.this.getItemCount()) {
                return;
            }
            final JMenuItem item = JMenu.this.getItem(n);
            if (item != null) {
                if (item instanceof JMenu) {
                    MenuSelectionManager.defaultManager().setSelectedPath(JMenu.this.buildMenuElementArray((JMenu)item));
                }
                else {
                    item.doClick();
                    MenuSelectionManager.defaultManager().setSelectedPath(null);
                }
            }
        }
        
        public void clearAccessibleSelection() {
            final MenuElement[] selectedPath = MenuSelectionManager.defaultManager().getSelectedPath();
            if (selectedPath != null) {
                for (int i = 0; i < selectedPath.length; ++i) {
                    if (selectedPath[i] == JMenu.this) {
                        final MenuElement[] selectedPath2 = new MenuElement[i + 1];
                        System.arraycopy(selectedPath, 0, selectedPath2, 0, i);
                        selectedPath2[i] = JMenu.this.getPopupMenu();
                        MenuSelectionManager.defaultManager().setSelectedPath(selectedPath2);
                    }
                }
            }
        }
        
        public Accessible getAccessibleChild(final int n) {
            final Component[] menuComponents = JMenu.this.getMenuComponents();
            int n2 = 0;
            for (int i = 0; i < menuComponents.length; ++i) {
                if (menuComponents[i] instanceof Accessible) {
                    if (n2 == n) {
                        if (menuComponents[i] instanceof JComponent) {
                            ((Accessible)menuComponents[i]).getAccessibleContext().setAccessibleParent(JMenu.this);
                        }
                        return (Accessible)menuComponents[i];
                    }
                    ++n2;
                }
            }
            return null;
        }
        
        public int getAccessibleChildrenCount() {
            final Component[] menuComponents = JMenu.this.getMenuComponents();
            int n = 0;
            for (int i = 0; i < menuComponents.length; ++i) {
                if (menuComponents[i] instanceof Accessible) {
                    ++n;
                }
            }
            return n;
        }
        
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.MENU;
        }
        
        public AccessibleSelection getAccessibleSelection() {
            return this;
        }
        
        public Accessible getAccessibleSelection(final int n) {
            if (n < 0 || n >= JMenu.this.getItemCount()) {
                return null;
            }
            final MenuElement[] selectedPath = MenuSelectionManager.defaultManager().getSelectedPath();
            if (selectedPath != null) {
                for (int i = 0; i < selectedPath.length; ++i) {
                    if (selectedPath[i] == JMenu.this) {
                        while (++i < selectedPath.length) {
                            if (selectedPath[i] instanceof JMenuItem) {
                                return (Accessible)selectedPath[i];
                            }
                        }
                    }
                }
            }
            return null;
        }
        
        public int getAccessibleSelectionCount() {
            final MenuElement[] selectedPath = MenuSelectionManager.defaultManager().getSelectedPath();
            if (selectedPath != null) {
                for (int i = 0; i < selectedPath.length; ++i) {
                    if (selectedPath[i] == JMenu.this && i + 1 < selectedPath.length) {
                        return 1;
                    }
                }
            }
            return 0;
        }
        
        public boolean isAccessibleChildSelected(final int n) {
            final MenuElement[] selectedPath = MenuSelectionManager.defaultManager().getSelectedPath();
            if (selectedPath != null) {
                final JMenuItem item = JMenu.this.getItem(n);
                for (int i = 0; i < selectedPath.length; ++i) {
                    if (selectedPath[i] == item) {
                        return true;
                    }
                }
            }
            return false;
        }
        
        public void removeAccessibleSelection(final int n) {
            if (n < 0 || n >= JMenu.this.getItemCount()) {
                return;
            }
            final JMenuItem item = JMenu.this.getItem(n);
            if (item != null && item instanceof JMenu && ((JMenu)item).isSelected()) {
                final MenuElement[] selectedPath = MenuSelectionManager.defaultManager().getSelectedPath();
                final MenuElement[] selectedPath2 = new MenuElement[selectedPath.length - 1];
                for (int i = 0; i < selectedPath.length - 1; ++i) {
                    selectedPath2[i] = selectedPath[i];
                }
                MenuSelectionManager.defaultManager().setSelectedPath(selectedPath2);
            }
        }
        
        public void selectAllAccessibleSelection() {
        }
    }
}
