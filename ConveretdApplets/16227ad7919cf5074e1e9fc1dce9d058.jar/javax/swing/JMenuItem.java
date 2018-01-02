// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.swing.event.ChangeEvent;
import javax.accessibility.AccessibleRole;
import javax.swing.event.ChangeListener;
import java.awt.event.FocusEvent;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.MenuItemUI;
import javax.accessibility.AccessibleState;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.event.FocusListener;
import java.awt.Component;
import javax.accessibility.AccessibleContext;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuDragMouseEvent;
import javax.swing.event.MenuKeyListener;
import java.util.EventListener;
import javax.swing.event.MenuDragMouseListener;
import javax.accessibility.Accessible;

public class JMenuItem extends AbstractButton implements Accessible, MenuElement
{
    private static final String uiClassID = "MenuItemUI";
    private KeyStroke accelerator;
    static /* synthetic */ Class class$javax$swing$event$MenuDragMouseListener;
    static /* synthetic */ Class class$javax$swing$event$MenuKeyListener;
    
    public JMenuItem() {
        this(null, null);
        this.setRequestFocusEnabled(false);
    }
    
    public JMenuItem(final String s) {
        this(s, null);
    }
    
    public JMenuItem(final String s, final int mnemonic) {
        this.setModel(new DefaultButtonModel());
        this.init(s, null);
        this.setMnemonic(mnemonic);
    }
    
    public JMenuItem(final String s, final Icon icon) {
        this.setModel(new DefaultButtonModel());
        this.init(s, icon);
    }
    
    public JMenuItem(final Icon icon) {
        this(null, icon);
        this.setRequestFocusEnabled(false);
    }
    
    public void addMenuDragMouseListener(final MenuDragMouseListener menuDragMouseListener) {
        super.listenerList.add((JMenuItem.class$javax$swing$event$MenuDragMouseListener != null) ? JMenuItem.class$javax$swing$event$MenuDragMouseListener : (JMenuItem.class$javax$swing$event$MenuDragMouseListener = class$("javax.swing.event.MenuDragMouseListener")), menuDragMouseListener);
    }
    
    public void addMenuKeyListener(final MenuKeyListener menuKeyListener) {
        super.listenerList.add((JMenuItem.class$javax$swing$event$MenuKeyListener != null) ? JMenuItem.class$javax$swing$event$MenuKeyListener : (JMenuItem.class$javax$swing$event$MenuKeyListener = class$("javax.swing.event.MenuKeyListener")), menuKeyListener);
    }
    
    boolean alwaysOnTop() {
        return true;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    protected void fireMenuDragMouseDragged(final MenuDragMouseEvent menuDragMouseEvent) {
        final Object[] listenerList = super.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((JMenuItem.class$javax$swing$event$MenuDragMouseListener != null) ? JMenuItem.class$javax$swing$event$MenuDragMouseListener : (JMenuItem.class$javax$swing$event$MenuDragMouseListener = class$("javax.swing.event.MenuDragMouseListener")))) {
                ((MenuDragMouseListener)listenerList[i + 1]).menuDragMouseDragged(menuDragMouseEvent);
            }
        }
    }
    
    protected void fireMenuDragMouseEntered(final MenuDragMouseEvent menuDragMouseEvent) {
        final Object[] listenerList = super.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((JMenuItem.class$javax$swing$event$MenuDragMouseListener != null) ? JMenuItem.class$javax$swing$event$MenuDragMouseListener : (JMenuItem.class$javax$swing$event$MenuDragMouseListener = class$("javax.swing.event.MenuDragMouseListener")))) {
                ((MenuDragMouseListener)listenerList[i + 1]).menuDragMouseEntered(menuDragMouseEvent);
            }
        }
    }
    
    protected void fireMenuDragMouseExited(final MenuDragMouseEvent menuDragMouseEvent) {
        final Object[] listenerList = super.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((JMenuItem.class$javax$swing$event$MenuDragMouseListener != null) ? JMenuItem.class$javax$swing$event$MenuDragMouseListener : (JMenuItem.class$javax$swing$event$MenuDragMouseListener = class$("javax.swing.event.MenuDragMouseListener")))) {
                ((MenuDragMouseListener)listenerList[i + 1]).menuDragMouseExited(menuDragMouseEvent);
            }
        }
    }
    
    protected void fireMenuDragMouseReleased(final MenuDragMouseEvent menuDragMouseEvent) {
        final Object[] listenerList = super.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((JMenuItem.class$javax$swing$event$MenuDragMouseListener != null) ? JMenuItem.class$javax$swing$event$MenuDragMouseListener : (JMenuItem.class$javax$swing$event$MenuDragMouseListener = class$("javax.swing.event.MenuDragMouseListener")))) {
                ((MenuDragMouseListener)listenerList[i + 1]).menuDragMouseReleased(menuDragMouseEvent);
            }
        }
    }
    
    protected void fireMenuKeyPressed(final MenuKeyEvent menuKeyEvent) {
        final Object[] listenerList = super.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((JMenuItem.class$javax$swing$event$MenuKeyListener != null) ? JMenuItem.class$javax$swing$event$MenuKeyListener : (JMenuItem.class$javax$swing$event$MenuKeyListener = class$("javax.swing.event.MenuKeyListener")))) {
                ((MenuKeyListener)listenerList[i + 1]).menuKeyPressed(menuKeyEvent);
            }
        }
    }
    
    protected void fireMenuKeyReleased(final MenuKeyEvent menuKeyEvent) {
        final Object[] listenerList = super.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((JMenuItem.class$javax$swing$event$MenuKeyListener != null) ? JMenuItem.class$javax$swing$event$MenuKeyListener : (JMenuItem.class$javax$swing$event$MenuKeyListener = class$("javax.swing.event.MenuKeyListener")))) {
                ((MenuKeyListener)listenerList[i + 1]).menuKeyReleased(menuKeyEvent);
            }
        }
    }
    
    protected void fireMenuKeyTyped(final MenuKeyEvent menuKeyEvent) {
        final Object[] listenerList = super.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((JMenuItem.class$javax$swing$event$MenuKeyListener != null) ? JMenuItem.class$javax$swing$event$MenuKeyListener : (JMenuItem.class$javax$swing$event$MenuKeyListener = class$("javax.swing.event.MenuKeyListener")))) {
                ((MenuKeyListener)listenerList[i + 1]).menuKeyTyped(menuKeyEvent);
            }
        }
    }
    
    public KeyStroke getAccelerator() {
        return this.accelerator;
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJMenuItem();
        }
        return super.accessibleContext;
    }
    
    public Component getComponent() {
        return this;
    }
    
    public MenuElement[] getSubElements() {
        return new MenuElement[0];
    }
    
    public String getUIClassID() {
        return "MenuItemUI";
    }
    
    protected void init(final String text, final Icon icon) {
        if (text != null) {
            this.setText(text);
        }
        if (icon != null) {
            this.setIcon(icon);
        }
        this.addFocusListener(new MenuItemFocusListener());
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setHorizontalTextPosition(11);
        this.setHorizontalAlignment(10);
        this.updateUI();
    }
    
    public boolean isArmed() {
        return this.getModel().isArmed();
    }
    
    public void menuSelectionChanged(final boolean armed) {
        this.setArmed(armed);
    }
    
    protected String paramString() {
        return super.paramString();
    }
    
    public void processKeyEvent(final KeyEvent keyEvent, final MenuElement[] array, final MenuSelectionManager menuSelectionManager) {
        this.processMenuKeyEvent(new MenuKeyEvent(keyEvent.getComponent(), keyEvent.getID(), keyEvent.getWhen(), keyEvent.getModifiers(), keyEvent.getKeyCode(), keyEvent.getKeyChar(), array, menuSelectionManager));
    }
    
    public void processMenuDragMouseEvent(final MenuDragMouseEvent menuDragMouseEvent) {
        switch (menuDragMouseEvent.getID()) {
            case 504: {
                this.fireMenuDragMouseEntered(menuDragMouseEvent);
                break;
            }
            case 505: {
                this.fireMenuDragMouseExited(menuDragMouseEvent);
                break;
            }
            case 506: {
                this.fireMenuDragMouseDragged(menuDragMouseEvent);
                break;
            }
            case 502: {
                this.fireMenuDragMouseReleased(menuDragMouseEvent);
                break;
            }
        }
    }
    
    public void processMenuKeyEvent(final MenuKeyEvent menuKeyEvent) {
        switch (menuKeyEvent.getID()) {
            case 401: {
                this.fireMenuKeyPressed(menuKeyEvent);
                break;
            }
            case 402: {
                this.fireMenuKeyReleased(menuKeyEvent);
                break;
            }
            case 400: {
                this.fireMenuKeyTyped(menuKeyEvent);
                break;
            }
        }
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent, final MenuElement[] array, final MenuSelectionManager menuSelectionManager) {
        this.processMenuDragMouseEvent(new MenuDragMouseEvent(mouseEvent.getComponent(), mouseEvent.getID(), mouseEvent.getWhen(), mouseEvent.getModifiers(), mouseEvent.getX(), mouseEvent.getY(), mouseEvent.getClickCount(), mouseEvent.isPopupTrigger(), array, menuSelectionManager));
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        if (this.getUIClassID().equals("MenuItemUI")) {
            this.updateUI();
        }
    }
    
    public void removeMenuDragMouseListener(final MenuDragMouseListener menuDragMouseListener) {
        super.listenerList.remove((JMenuItem.class$javax$swing$event$MenuDragMouseListener != null) ? JMenuItem.class$javax$swing$event$MenuDragMouseListener : (JMenuItem.class$javax$swing$event$MenuDragMouseListener = class$("javax.swing.event.MenuDragMouseListener")), menuDragMouseListener);
    }
    
    public void removeMenuKeyListener(final MenuKeyListener menuKeyListener) {
        super.listenerList.remove((JMenuItem.class$javax$swing$event$MenuKeyListener != null) ? JMenuItem.class$javax$swing$event$MenuKeyListener : (JMenuItem.class$javax$swing$event$MenuKeyListener = class$("javax.swing.event.MenuKeyListener")), menuKeyListener);
    }
    
    public void setAccelerator(final KeyStroke accelerator) {
        final KeyStroke accelerator2 = this.accelerator;
        if (accelerator2 != null) {
            this.unregisterKeyboardAction(accelerator2);
        }
        if (accelerator != null) {
            this.registerKeyboardAction(new ActionListener() {
                public void actionPerformed(final ActionEvent actionEvent) {
                    MenuSelectionManager.defaultManager().clearSelectedPath();
                    JMenuItem.this.doClick();
                }
            }, accelerator, 2);
        }
        this.firePropertyChange("accelerator", accelerator2, this.accelerator = accelerator);
    }
    
    public void setArmed(final boolean armed) {
        final ButtonModel model = this.getModel();
        final boolean armed2 = model.isArmed();
        if (super.accessibleContext != null && armed2 != armed) {
            if (armed) {
                super.accessibleContext.firePropertyChange("AccessibleState", null, AccessibleState.ARMED);
            }
            else {
                super.accessibleContext.firePropertyChange("AccessibleState", AccessibleState.ARMED, null);
            }
        }
        if (model.isArmed() != armed) {
            model.setArmed(armed);
        }
    }
    
    public void setEnabled(final boolean enabled) {
        if (!enabled) {
            this.setArmed(false);
        }
        super.setEnabled(enabled);
    }
    
    public void setUI(final MenuItemUI ui) {
        super.setUI(ui);
    }
    
    public void updateUI() {
        this.setUI((MenuItemUI)UIManager.getUI(this));
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        if (super.ui != null && this.getUIClassID().equals("MenuItemUI")) {
            super.ui.installUI(this);
        }
    }
    
    private static class MenuItemFocusListener implements FocusListener, Serializable
    {
        public void focusGained(final FocusEvent focusEvent) {
        }
        
        public void focusLost(final FocusEvent focusEvent) {
            final JMenuItem menuItem = (JMenuItem)focusEvent.getSource();
            if (menuItem.isFocusPainted()) {
                menuItem.repaint();
            }
        }
    }
    
    protected class AccessibleJMenuItem extends AccessibleAbstractButton implements ChangeListener
    {
        AccessibleJMenuItem() {
            JMenuItem.this.addChangeListener(this);
        }
        
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.MENU_ITEM;
        }
        
        public void stateChanged(final ChangeEvent changeEvent) {
            this.firePropertyChange("AccessibleVisibleData", new Boolean(false), new Boolean(true));
        }
    }
}
