// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.accessibility.AccessibleStateSet;
import javax.accessibility.AccessibleRole;
import javax.accessibility.AccessibleSelection;
import java.io.ObjectOutputStream;
import javax.swing.plaf.ComponentUI;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Graphics;
import javax.swing.plaf.MenuBarUI;
import java.util.Vector;
import javax.accessibility.AccessibleContext;
import java.io.Serializable;
import java.awt.Component;
import java.awt.Insets;
import javax.accessibility.Accessible;

public class JMenuBar extends JComponent implements Accessible, MenuElement
{
    private static final String uiClassID = "MenuBarUI";
    private transient SingleSelectionModel selectionModel;
    private boolean paintBorder;
    private Insets margin;
    
    public JMenuBar() {
        this.paintBorder = true;
        this.margin = null;
        this.setSelectionModel(new DefaultSingleSelectionModel());
        this.updateUI();
    }
    
    public JMenu add(final JMenu menu) {
        super.add(menu);
        return menu;
    }
    
    public void addNotify() {
        super.addNotify();
        KeyboardManager.getCurrentManager().registerMenuBar(this);
    }
    
    KeyboardBinding bindingForKeyStroke(final KeyStroke keyStroke, final int n) {
        Serializable s = super.bindingForKeyStroke(keyStroke, n);
        if (s != null) {
            return (KeyboardBinding)s;
        }
        final Component[] components = this.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if (components[i] instanceof JMenu) {
                s = bindingForKeyStrokeRecursive(components[i], keyStroke, n);
            }
            if (s != null) {
                return (KeyboardBinding)s;
            }
        }
        return null;
    }
    
    static KeyboardBinding bindingForKeyStrokeRecursive(final Component component, final KeyStroke keyStroke, final int n) {
        Serializable s = null;
        if (component == null) {
            return null;
        }
        if (component instanceof JComponent) {
            s = ((JComponent)component).bindingForKeyStroke(keyStroke, n);
            if (s != null) {
                return (KeyboardBinding)s;
            }
        }
        if (component instanceof JMenu) {
            final Component[] menuComponents = ((JMenu)component).getMenuComponents();
            if (menuComponents != null) {
                for (int i = 0; i < menuComponents.length; ++i) {
                    if (menuComponents[i] instanceof JMenuItem) {
                        s = bindingForKeyStrokeRecursive(menuComponents[i], keyStroke, n);
                    }
                    if (s != null) {
                        return (KeyboardBinding)s;
                    }
                }
            }
        }
        return (KeyboardBinding)s;
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJMenuBar();
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
    
    public JMenu getHelpMenu() {
        throw new Error("getHelpMenu() not yet implemented.");
    }
    
    public Insets getMargin() {
        if (this.margin == null) {
            return new Insets(0, 0, 0, 0);
        }
        return this.margin;
    }
    
    public JMenu getMenu(final int n) {
        final Component componentAtIndex = this.getComponentAtIndex(n);
        if (componentAtIndex instanceof JMenu) {
            return (JMenu)componentAtIndex;
        }
        return null;
    }
    
    public int getMenuCount() {
        return this.getComponentCount();
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
    
    public MenuBarUI getUI() {
        return (MenuBarUI)super.ui;
    }
    
    public String getUIClassID() {
        return "MenuBarUI";
    }
    
    public boolean isBorderPainted() {
        return this.paintBorder;
    }
    
    public boolean isManagingFocus() {
        return true;
    }
    
    public boolean isSelected() {
        return this.selectionModel.isSelected();
    }
    
    public void menuSelectionChanged(final boolean b) {
    }
    
    protected void paintBorder(final Graphics graphics) {
        if (this.isBorderPainted()) {
            super.paintBorder(graphics);
        }
    }
    
    protected String paramString() {
        return String.valueOf(super.paramString()) + ",margin=" + ((this.margin != null) ? this.margin.toString() : "") + ",paintBorder=" + (this.paintBorder ? "true" : "false");
    }
    
    public void processKeyEvent(final KeyEvent keyEvent, final MenuElement[] array, final MenuSelectionManager menuSelectionManager) {
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent, final MenuElement[] array, final MenuSelectionManager menuSelectionManager) {
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        final Object[] array = (Object[])objectInputStream.readObject();
        for (int n = 0; n < array.length && array[n] != null; n += 2) {
            if (array[n].equals("selectionModel")) {
                this.selectionModel = (SingleSelectionModel)array[n + 1];
            }
        }
        if (super.ui != null && this.getUIClassID().equals("MenuBarUI")) {
            super.ui.installUI(this);
        }
    }
    
    public void removeNotify() {
        super.removeNotify();
        KeyboardManager.getCurrentManager().unregisterMenuBar(this);
    }
    
    public void setBorderPainted(final boolean paintBorder) {
        final boolean paintBorder2 = this.paintBorder;
        this.firePropertyChange("borderPainted", paintBorder2, this.paintBorder = paintBorder);
        if (paintBorder != paintBorder2) {
            this.revalidate();
            this.repaint();
        }
    }
    
    public void setHelpMenu(final JMenu menu) {
        throw new Error("setHelpMenu() not yet implemented.");
    }
    
    public void setMargin(final Insets margin) {
        final Insets margin2 = this.margin;
        this.firePropertyChange("margin", margin2, this.margin = margin);
        if (margin2 == null || !margin.equals(margin2)) {
            this.revalidate();
            this.repaint();
        }
    }
    
    public void setSelected(final Component component) {
        this.getSelectionModel().setSelectedIndex(this.getComponentIndex(component));
    }
    
    public void setSelectionModel(final SingleSelectionModel selectionModel) {
        this.firePropertyChange("selectionModel", this.selectionModel, this.selectionModel = selectionModel);
    }
    
    public void setUI(final MenuBarUI ui) {
        super.setUI(ui);
    }
    
    public void updateUI() {
        this.setUI((MenuBarUI)UIManager.getUI(this));
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        final Object[] array = new Object[4];
        int n = 0;
        if (this.selectionModel instanceof Serializable) {
            array[n++] = "selectionModel";
            array[n++] = this.selectionModel;
        }
        objectOutputStream.writeObject(array);
    }
    
    protected class AccessibleJMenuBar extends AccessibleJComponent implements AccessibleSelection
    {
        public void addAccessibleSelection(final int selectedIndex) {
            final int selectedIndex2 = JMenuBar.this.getSelectionModel().getSelectedIndex();
            if (selectedIndex == selectedIndex2) {
                return;
            }
            if (selectedIndex2 >= 0 && selectedIndex2 < JMenuBar.this.getMenuCount() && JMenuBar.this.getMenu(selectedIndex2) != null) {
                MenuSelectionManager.defaultManager().setSelectedPath(null);
            }
            JMenuBar.this.getSelectionModel().setSelectedIndex(selectedIndex);
            final JMenu menu = JMenuBar.this.getMenu(selectedIndex);
            if (menu != null) {
                MenuSelectionManager.defaultManager().setSelectedPath(new MenuElement[] { JMenuBar.this, menu, menu.getPopupMenu() });
            }
        }
        
        public void clearAccessibleSelection() {
            final int selectedIndex = JMenuBar.this.getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0 && selectedIndex < JMenuBar.this.getMenuCount() && JMenuBar.this.getMenu(selectedIndex) != null) {
                MenuSelectionManager.defaultManager().setSelectedPath(null);
            }
            JMenuBar.this.getSelectionModel().setSelectedIndex(-1);
        }
        
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.MENU_BAR;
        }
        
        public AccessibleSelection getAccessibleSelection() {
            return this;
        }
        
        public Accessible getAccessibleSelection(final int n) {
            if (JMenuBar.this.isSelected()) {
                if (n != 0) {
                    return null;
                }
                final int selectedIndex = JMenuBar.this.getSelectionModel().getSelectedIndex();
                if (JMenuBar.this.getComponentAtIndex(selectedIndex) instanceof Accessible) {
                    return (Accessible)JMenuBar.this.getComponentAtIndex(selectedIndex);
                }
            }
            return null;
        }
        
        public int getAccessibleSelectionCount() {
            if (JMenuBar.this.isSelected()) {
                return 1;
            }
            return 0;
        }
        
        public AccessibleStateSet getAccessibleStateSet() {
            return super.getAccessibleStateSet();
        }
        
        public boolean isAccessibleChildSelected(final int n) {
            return n == JMenuBar.this.getSelectionModel().getSelectedIndex();
        }
        
        public void removeAccessibleSelection(final int n) {
            if (n >= 0 && n < JMenuBar.this.getMenuCount()) {
                if (JMenuBar.this.getMenu(n) != null) {
                    MenuSelectionManager.defaultManager().setSelectedPath(null);
                }
                JMenuBar.this.getSelectionModel().setSelectedIndex(-1);
            }
        }
        
        public void selectAllAccessibleSelection() {
        }
    }
}
