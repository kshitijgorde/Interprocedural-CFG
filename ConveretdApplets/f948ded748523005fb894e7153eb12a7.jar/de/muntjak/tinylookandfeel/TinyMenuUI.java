// 
// Decompiled by Procyon v0.5.30
// 

package de.muntjak.tinylookandfeel;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import java.beans.PropertyChangeEvent;
import java.awt.Container;
import java.awt.event.MouseEvent;
import java.awt.Point;
import javax.swing.event.MenuDragMouseEvent;
import javax.swing.event.MenuEvent;
import javax.swing.JMenuItem;
import javax.swing.event.MenuKeyEvent;
import java.awt.Component;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.plaf.basic.ComboPopup;
import java.awt.event.KeyEvent;
import java.awt.Window;
import javax.swing.JRootPane;
import java.awt.KeyEventPostProcessor;
import javax.swing.JPopupMenu;
import javax.swing.MenuSelectionManager;
import javax.swing.MenuElement;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.Dimension;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuDragMouseListener;
import javax.swing.event.MouseInputListener;
import javax.swing.SwingUtilities;
import javax.swing.KeyStroke;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.UIManager;
import javax.swing.JMenu;
import javax.swing.plaf.ComponentUI;
import javax.swing.JComponent;
import javax.swing.event.MenuListener;
import java.beans.PropertyChangeListener;
import javax.swing.event.ChangeListener;

public class TinyMenuUI extends TinyMenuItemUI
{
    private static final boolean TRACE = false;
    private static final boolean VERBOSE = false;
    private static final boolean DEBUG = false;
    static final AltProcessor altProcessor;
    protected ChangeListener changeListener;
    protected PropertyChangeListener propertyChangeListener;
    protected MenuListener menuListener;
    private int lastMnemonic;
    private static boolean crossMenuMnemonic;
    private static MenuListener sharedMenuListener;
    
    public TinyMenuUI() {
        this.lastMnemonic = 0;
    }
    
    public static ComponentUI createUI(final JComponent component) {
        return new TinyMenuUI();
    }
    
    protected void installDefaults() {
        super.installDefaults();
        ((JMenu)this.menuItem).setDelay(200);
        TinyMenuUI.crossMenuMnemonic = UIManager.getBoolean("Menu.crossMenuMnemonic");
    }
    
    ActionMap getActionMap() {
        return this.createActionMap();
    }
    
    ActionMap createActionMap() {
        final ActionMap actionMap = super.createActionMap();
        if (actionMap != null) {
            actionMap.put("selectMenu", new PostAction((JMenu)this.menuItem, true));
        }
        return actionMap;
    }
    
    protected String getPropertyPrefix() {
        return "Menu";
    }
    
    protected void installListeners() {
        super.installListeners();
        if (this.changeListener == null) {
            this.changeListener = this.createChangeListener(this.menuItem);
        }
        if (this.changeListener != null) {
            this.menuItem.addChangeListener(this.changeListener);
        }
        if (this.propertyChangeListener == null) {
            this.propertyChangeListener = this.createPropertyChangeListener(this.menuItem);
        }
        if (this.propertyChangeListener != null) {
            this.menuItem.addPropertyChangeListener(this.propertyChangeListener);
        }
        if (TinyLookAndFeel.is1dot4() && (this.menuKeyListener = this.createMenuKeyListener(this.menuItem)) != null) {
            this.menuItem.addMenuKeyListener(this.menuKeyListener);
        }
    }
    
    protected void installKeyboardActions() {
        super.installKeyboardActions();
        this.updateMnemonicBinding();
    }
    
    void updateMnemonicBinding() {
        final int mnemonic = this.menuItem.getModel().getMnemonic();
        final int[] array = (int[])UIManager.get("Menu.shortcutKeys");
        if (mnemonic == this.lastMnemonic || array == null) {
            return;
        }
        if (this.lastMnemonic != 0 && this.windowInputMap != null) {
            for (int i = 0; i < array.length; ++i) {
                this.windowInputMap.remove(KeyStroke.getKeyStroke(this.lastMnemonic, array[i], false));
            }
        }
        if (mnemonic != 0) {
            if (this.windowInputMap == null) {
                this.windowInputMap = this.createInputMap(2);
                SwingUtilities.replaceUIInputMap(this.menuItem, 2, this.windowInputMap);
            }
            for (int j = 0; j < array.length; ++j) {
                this.windowInputMap.put(KeyStroke.getKeyStroke(mnemonic, array[j], false), "selectMenu");
            }
        }
        this.lastMnemonic = mnemonic;
    }
    
    protected MouseInputListener createMouseInputListener(final JComponent component) {
        return new MouseInputHandler();
    }
    
    protected ChangeListener createChangeListener(final JComponent component) {
        return null;
    }
    
    protected PropertyChangeListener createPropertyChangeListener(final JComponent component) {
        return new PropertyChangeHandler();
    }
    
    protected void uninstallDefaults() {
        this.menuItem.setArmed(false);
        this.menuItem.setSelected(false);
        this.menuItem.resetKeyboardActions();
        super.uninstallDefaults();
    }
    
    protected void uninstallListeners() {
        super.uninstallListeners();
        if (this.changeListener != null) {
            this.menuItem.removeChangeListener(this.changeListener);
            this.changeListener = null;
        }
        if (this.propertyChangeListener != null) {
            this.menuItem.removePropertyChangeListener(this.propertyChangeListener);
            this.propertyChangeListener = null;
        }
        if (this.menuKeyListener != null) {
            this.menuItem.removeMenuKeyListener(this.menuKeyListener);
            this.menuKeyListener = null;
        }
    }
    
    protected MenuDragMouseListener createMenuDragMouseListener(final JComponent component) {
        return new MenuDragMouseHandler();
    }
    
    protected MenuKeyListener createMenuKeyListener(final JComponent component) {
        return new MenuKeyHandler();
    }
    
    public Dimension getMaximumSize(final JComponent component) {
        if (((JMenu)this.menuItem).isTopLevelMenu()) {
            return new Dimension(component.getPreferredSize().width, 32767);
        }
        return null;
    }
    
    protected void setupPostTimer(final JMenu menu) {
        final Timer timer = new Timer(menu.getDelay(), new PostAction(menu, false));
        timer.setRepeats(false);
        timer.start();
    }
    
    private static void appendPath(final MenuElement[] array, final MenuElement menuElement) {
        final MenuElement[] selectedPath = new MenuElement[array.length + 1];
        System.arraycopy(array, 0, selectedPath, 0, array.length);
        selectedPath[array.length] = menuElement;
        MenuSelectionManager.defaultManager().setSelectedPath(selectedPath);
    }
    
    static JPopupMenu getActivePopupMenu() {
        final MenuElement[] selectedPath = MenuSelectionManager.defaultManager().getSelectedPath();
        for (int i = selectedPath.length - 1; i >= 0; --i) {
            final MenuElement menuElement = selectedPath[i];
            if (menuElement instanceof JPopupMenu) {
                return (JPopupMenu)menuElement;
            }
        }
        return null;
    }
    
    static {
        altProcessor = new AltProcessor();
        TinyMenuUI.crossMenuMnemonic = true;
    }
    
    static class AltProcessor implements KeyEventPostProcessor
    {
        static boolean altKeyPressed;
        static boolean menuCanceledOnPress;
        static JRootPane root;
        static Window winAncestor;
        
        void altPressed(final KeyEvent keyEvent) {
            final MenuSelectionManager defaultManager = MenuSelectionManager.defaultManager();
            final MenuElement[] selectedPath = defaultManager.getSelectedPath();
            if (selectedPath.length > 0 && !(selectedPath[0] instanceof ComboPopup)) {
                defaultManager.clearSelectedPath();
                AltProcessor.menuCanceledOnPress = true;
                keyEvent.consume();
            }
            else if (selectedPath.length > 0) {
                AltProcessor.menuCanceledOnPress = false;
                keyEvent.consume();
            }
            else {
                AltProcessor.menuCanceledOnPress = false;
                JMenuBar jMenuBar = (AltProcessor.root != null) ? AltProcessor.root.getJMenuBar() : null;
                if (jMenuBar == null && AltProcessor.winAncestor instanceof JFrame) {
                    jMenuBar = ((JFrame)AltProcessor.winAncestor).getJMenuBar();
                }
                if (((jMenuBar != null) ? jMenuBar.getMenu(0) : null) != null) {
                    keyEvent.consume();
                }
            }
        }
        
        void altReleased(final KeyEvent keyEvent) {
            if (AltProcessor.menuCanceledOnPress) {
                return;
            }
            final MenuSelectionManager defaultManager = MenuSelectionManager.defaultManager();
            if (defaultManager.getSelectedPath().length == 0) {
                JMenuBar jMenuBar = (AltProcessor.root != null) ? AltProcessor.root.getJMenuBar() : null;
                if (jMenuBar == null && AltProcessor.winAncestor instanceof JFrame) {
                    jMenuBar = ((JFrame)AltProcessor.winAncestor).getJMenuBar();
                }
                final JMenu menu = (jMenuBar != null) ? jMenuBar.getMenu(0) : null;
                if (menu != null) {
                    defaultManager.setSelectedPath(new MenuElement[] { jMenuBar, menu });
                }
            }
        }
        
        public boolean postProcessKeyEvent(final KeyEvent keyEvent) {
            if (keyEvent.getKeyCode() == 18) {
                AltProcessor.root = SwingUtilities.getRootPane(keyEvent.getComponent());
                AltProcessor.winAncestor = SwingUtilities.getWindowAncestor(AltProcessor.root);
                if (keyEvent.getID() == 401) {
                    if (!AltProcessor.altKeyPressed) {
                        this.altPressed(keyEvent);
                    }
                    return AltProcessor.altKeyPressed = true;
                }
                if (keyEvent.getID() == 402) {
                    if (AltProcessor.altKeyPressed) {
                        this.altReleased(keyEvent);
                    }
                    AltProcessor.altKeyPressed = false;
                }
            }
            else {
                AltProcessor.altKeyPressed = false;
            }
            return false;
        }
        
        static {
            AltProcessor.altKeyPressed = false;
            AltProcessor.menuCanceledOnPress = false;
            AltProcessor.root = null;
            AltProcessor.winAncestor = null;
        }
    }
    
    private class MenuKeyHandler implements MenuKeyListener
    {
        private int[] indexes;
        private char lastMnemonic;
        private int lastIndex;
        private int matches;
        
        public void menuKeyTyped(final MenuKeyEvent menuKeyEvent) {
            if (TinyMenuUI.this.menuItem == null) {
                return;
            }
            if (!TinyMenuUI.crossMenuMnemonic) {
                final JPopupMenu activePopupMenu = TinyMenuUI.getActivePopupMenu();
                if (activePopupMenu != null && activePopupMenu != TinyMenuUI.this.menuItem.getParent()) {
                    return;
                }
            }
            final int mnemonic = TinyMenuUI.this.menuItem.getMnemonic();
            if (mnemonic == 0) {
                return;
            }
            final MenuElement[] path = menuKeyEvent.getPath();
            if (this.lower((char)mnemonic) == this.lower(menuKeyEvent.getKeyChar())) {
                final JPopupMenu popupMenu = ((JMenu)TinyMenuUI.this.menuItem).getPopupMenu();
                final MenuElement[] subElements = popupMenu.getSubElements();
                if (subElements.length > 0) {
                    final MenuSelectionManager menuSelectionManager = menuKeyEvent.getMenuSelectionManager();
                    final MenuElement[] selectedPath = new MenuElement[path.length + 2];
                    System.arraycopy(path, 0, selectedPath, 0, path.length);
                    selectedPath[path.length] = popupMenu;
                    selectedPath[path.length + 1] = subElements[0];
                    menuSelectionManager.setSelectedPath(selectedPath);
                }
                menuKeyEvent.consume();
            }
        }
        
        public void menuKeyPressed(final MenuKeyEvent menuKeyEvent) {
            if (TinyMenuUI.this.menuItem == null) {
                return;
            }
            final char keyChar = menuKeyEvent.getKeyChar();
            if (!Character.isLetterOrDigit(keyChar)) {
                return;
            }
            final MenuSelectionManager menuSelectionManager = menuKeyEvent.getMenuSelectionManager();
            final MenuElement[] path = menuKeyEvent.getPath();
            final MenuElement[] selectedPath = menuSelectionManager.getSelectedPath();
            for (int i = selectedPath.length - 1; i >= 0; --i) {
                if (selectedPath[i] == TinyMenuUI.this.menuItem) {
                    final JPopupMenu popupMenu = ((JMenu)TinyMenuUI.this.menuItem).getPopupMenu();
                    final MenuElement[] subElements = popupMenu.getSubElements();
                    if (this.indexes == null || this.lastMnemonic != keyChar) {
                        this.matches = 0;
                        this.lastIndex = 0;
                        this.indexes = new int[subElements.length];
                        for (int j = 0; j < subElements.length; ++j) {
                            if (this.lower((char)((JMenuItem)subElements[j]).getMnemonic()) == this.lower(keyChar)) {
                                this.indexes[this.matches++] = j;
                            }
                        }
                        this.lastMnemonic = keyChar;
                    }
                    if (this.matches != 0) {
                        if (this.matches == 1) {
                            final JMenuItem menuItem = (JMenuItem)subElements[this.indexes[0]];
                            if (!(menuItem instanceof JMenu)) {
                                menuSelectionManager.clearSelectedPath();
                                menuItem.doClick();
                            }
                        }
                        else {
                            if (this.lastIndex == this.matches) {
                                this.lastIndex = 0;
                            }
                            final MenuElement menuElement = subElements[this.indexes[this.lastIndex++]];
                            final MenuElement[] selectedPath2 = new MenuElement[path.length + 2];
                            System.arraycopy(path, 0, selectedPath2, 0, path.length);
                            selectedPath2[path.length] = popupMenu;
                            selectedPath2[path.length + 1] = menuElement;
                            menuSelectionManager.setSelectedPath(selectedPath2);
                        }
                    }
                    menuKeyEvent.consume();
                    return;
                }
            }
        }
        
        public void menuKeyReleased(final MenuKeyEvent menuKeyEvent) {
        }
        
        private char lower(final char c) {
            return Character.toLowerCase(c);
        }
    }
    
    private static class MenuHandler implements MenuListener
    {
        public void menuSelected(final MenuEvent menuEvent) {
        }
        
        public void menuDeselected(final MenuEvent menuEvent) {
        }
        
        public void menuCanceled(final MenuEvent menuEvent) {
            if (MenuSelectionManager.defaultManager().isComponentPartOfCurrentMenu((Component)menuEvent.getSource())) {
                MenuSelectionManager.defaultManager().clearSelectedPath();
            }
        }
    }
    
    private class MenuDragMouseHandler implements MenuDragMouseListener
    {
        public void menuDragMouseEntered(final MenuDragMouseEvent menuDragMouseEvent) {
        }
        
        public void menuDragMouseDragged(final MenuDragMouseEvent menuDragMouseEvent) {
            if (!TinyMenuUI.this.menuItem.isEnabled()) {
                return;
            }
            final MenuSelectionManager menuSelectionManager = menuDragMouseEvent.getMenuSelectionManager();
            final MenuElement[] path = menuDragMouseEvent.getPath();
            final Point point = menuDragMouseEvent.getPoint();
            if (point.x >= 0 && point.x < TinyMenuUI.this.menuItem.getWidth() && point.y >= 0 && point.y < TinyMenuUI.this.menuItem.getHeight()) {
                final JMenu menu = (JMenu)TinyMenuUI.this.menuItem;
                final MenuElement[] selectedPath = menuSelectionManager.getSelectedPath();
                if (selectedPath.length <= 0 || selectedPath[selectedPath.length - 1] != menu.getPopupMenu()) {
                    if (menu.isTopLevelMenu() || menu.getDelay() == 0 || menuDragMouseEvent.getID() == 506) {
                        appendPath(path, menu.getPopupMenu());
                    }
                    else {
                        menuSelectionManager.setSelectedPath(path);
                        TinyMenuUI.this.setupPostTimer(menu);
                    }
                }
            }
            else if (menuDragMouseEvent.getID() == 502 && menuSelectionManager.componentForPoint(menuDragMouseEvent.getComponent(), menuDragMouseEvent.getPoint()) == null) {
                menuSelectionManager.clearSelectedPath();
            }
        }
        
        public void menuDragMouseExited(final MenuDragMouseEvent menuDragMouseEvent) {
        }
        
        public void menuDragMouseReleased(final MenuDragMouseEvent menuDragMouseEvent) {
        }
    }
    
    protected class MouseInputHandler implements MouseInputListener
    {
        public void mouseClicked(final MouseEvent mouseEvent) {
        }
        
        public void mousePressed(final MouseEvent mouseEvent) {
            final JMenu menu = (JMenu)TinyMenuUI.this.menuItem;
            if (!menu.isEnabled()) {
                return;
            }
            final MenuSelectionManager defaultManager = MenuSelectionManager.defaultManager();
            if (menu.isTopLevelMenu()) {
                if (menu.isSelected()) {
                    defaultManager.clearSelectedPath();
                }
                else {
                    final Container parent = menu.getParent();
                    if (parent != null && parent instanceof JMenuBar) {
                        defaultManager.setSelectedPath(new MenuElement[] { (MenuElement)parent, menu });
                    }
                }
            }
            final MenuElement[] selectedPath = defaultManager.getSelectedPath();
            if (selectedPath.length > 0 && selectedPath[selectedPath.length - 1] != menu.getPopupMenu()) {
                if (menu.isTopLevelMenu() || menu.getDelay() == 0) {
                    appendPath(selectedPath, menu.getPopupMenu());
                }
                else {
                    TinyMenuUI.this.setupPostTimer(menu);
                }
            }
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            if (!((JMenu)TinyMenuUI.this.menuItem).isEnabled()) {
                return;
            }
            final MenuSelectionManager defaultManager = MenuSelectionManager.defaultManager();
            defaultManager.processMouseEvent(mouseEvent);
            if (!mouseEvent.isConsumed()) {
                defaultManager.clearSelectedPath();
            }
        }
        
        public void mouseEntered(final MouseEvent mouseEvent) {
            final JMenu menu = (JMenu)TinyMenuUI.this.menuItem;
            if (!menu.isEnabled() || menu.getClientProperty("isSystemMenu") == Boolean.TRUE) {
                return;
            }
            menu.putClientProperty("rollover", Boolean.TRUE);
            final MenuSelectionManager defaultManager = MenuSelectionManager.defaultManager();
            final MenuElement[] selectedPath = defaultManager.getSelectedPath();
            if (!menu.isTopLevelMenu()) {
                if (selectedPath.length <= 0 || selectedPath[selectedPath.length - 1] != menu.getPopupMenu()) {
                    if (menu.getDelay() == 0) {
                        appendPath(TinyMenuUI.this.getPath(), menu.getPopupMenu());
                    }
                    else {
                        defaultManager.setSelectedPath(TinyMenuUI.this.getPath());
                        TinyMenuUI.this.setupPostTimer(menu);
                    }
                }
            }
            else if (selectedPath.length > 0 && selectedPath[0] == menu.getParent()) {
                defaultManager.setSelectedPath(new MenuElement[] { (MenuElement)menu.getParent(), menu, menu.getPopupMenu() });
            }
            if (menu.isTopLevelMenu()) {
                menu.repaint();
            }
        }
        
        public void mouseExited(final MouseEvent mouseEvent) {
            final JMenu menu = (JMenu)TinyMenuUI.this.menuItem;
            if (!menu.isEnabled() || menu.getClientProperty("isSystemMenu") == Boolean.TRUE) {
                return;
            }
            menu.putClientProperty("rollover", Boolean.FALSE);
            if (menu.isTopLevelMenu()) {
                menu.repaint();
            }
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            if (!((JMenu)TinyMenuUI.this.menuItem).isEnabled()) {
                return;
            }
            MenuSelectionManager.defaultManager().processMouseEvent(mouseEvent);
        }
        
        public void mouseMoved(final MouseEvent mouseEvent) {
        }
    }
    
    private class PropertyChangeHandler implements PropertyChangeListener
    {
        public void propertyChange(final PropertyChangeEvent propertyChangeEvent) {
            if (propertyChangeEvent.getPropertyName().equals("mnemonic")) {
                TinyMenuUI.this.updateMnemonicBinding();
            }
        }
    }
    
    private static class PostAction extends AbstractAction
    {
        JMenu menu;
        boolean force;
        
        PostAction(final JMenu menu, final boolean force) {
            this.force = false;
            this.menu = menu;
            this.force = force;
        }
        
        public void actionPerformed(final ActionEvent actionEvent) {
            if (!TinyMenuUI.crossMenuMnemonic) {
                final JPopupMenu activePopupMenu = TinyMenuUI.getActivePopupMenu();
                if (activePopupMenu != null && activePopupMenu != this.menu.getParent()) {
                    return;
                }
            }
            final MenuSelectionManager defaultManager = MenuSelectionManager.defaultManager();
            if (this.force) {
                final Container parent = this.menu.getParent();
                if (parent != null && parent instanceof JMenuBar) {
                    final MenuElement[] subElements = this.menu.getPopupMenu().getSubElements();
                    MenuElement[] selectedPath;
                    if (subElements.length > 0) {
                        selectedPath = new MenuElement[] { (MenuElement)parent, this.menu, this.menu.getPopupMenu(), subElements[0] };
                    }
                    else {
                        selectedPath = new MenuElement[] { (MenuElement)parent, this.menu, this.menu.getPopupMenu() };
                    }
                    defaultManager.setSelectedPath(selectedPath);
                }
            }
            else {
                final MenuElement[] selectedPath2 = defaultManager.getSelectedPath();
                if (selectedPath2.length > 0 && selectedPath2[selectedPath2.length - 1] == this.menu) {
                    appendPath(selectedPath2, this.menu.getPopupMenu());
                }
            }
        }
        
        public boolean isEnabled() {
            return this.menu.getModel().isEnabled();
        }
    }
}
