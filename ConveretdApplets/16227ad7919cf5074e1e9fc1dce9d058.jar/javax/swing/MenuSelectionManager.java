// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.event.MouseEvent;
import java.awt.event.KeyEvent;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Component;
import java.util.EventListener;
import javax.swing.event.ChangeListener;
import javax.swing.event.EventListenerList;
import javax.swing.event.ChangeEvent;
import java.util.Vector;

public class MenuSelectionManager
{
    private static final MenuSelectionManager instance;
    Vector selection;
    protected transient ChangeEvent changeEvent;
    protected EventListenerList listenerList;
    static /* synthetic */ Class class$javax$swing$event$ChangeListener;
    
    static {
        instance = new MenuSelectionManager();
    }
    
    public MenuSelectionManager() {
        this.selection = new Vector();
        this.changeEvent = null;
        this.listenerList = new EventListenerList();
    }
    
    public void addChangeListener(final ChangeListener changeListener) {
        this.listenerList.add((MenuSelectionManager.class$javax$swing$event$ChangeListener != null) ? MenuSelectionManager.class$javax$swing$event$ChangeListener : (MenuSelectionManager.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")), changeListener);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public void clearSelectedPath() {
        this.setSelectedPath(null);
    }
    
    public Component componentForPoint(final Component component, final Point point) {
        SwingUtilities.convertPointToScreen(point, component);
        final int x = point.x;
        final int y = point.y;
        final Vector vector = (Vector)this.selection.clone();
        for (int i = vector.size() - 1; i >= 0; --i) {
            final MenuElement[] subElements = vector.elementAt(i).getSubElements();
            for (int j = 0; j < subElements.length; ++j) {
                if (subElements[j] != null) {
                    final Component component2 = subElements[j].getComponent();
                    if (component2.isShowing()) {
                        int n;
                        int n2;
                        if (component2 instanceof JComponent) {
                            n = ((JComponent)component2).getWidth();
                            n2 = ((JComponent)component2).getHeight();
                        }
                        else {
                            final Rectangle bounds = component2.getBounds();
                            n = bounds.width;
                            n2 = bounds.height;
                        }
                        point.x = x;
                        point.y = y;
                        SwingUtilities.convertPointFromScreen(point, component2);
                        if (point.x >= 0 && point.x < n && point.y >= 0 && point.y < n2) {
                            return component2;
                        }
                    }
                }
            }
        }
        return null;
    }
    
    public static MenuSelectionManager defaultManager() {
        return MenuSelectionManager.instance;
    }
    
    protected void fireStateChanged() {
        final Object[] listenerList = this.listenerList.getListenerList();
        for (int i = listenerList.length - 2; i >= 0; i -= 2) {
            if (listenerList[i] == ((MenuSelectionManager.class$javax$swing$event$ChangeListener != null) ? MenuSelectionManager.class$javax$swing$event$ChangeListener : (MenuSelectionManager.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")))) {
                if (this.changeEvent == null) {
                    this.changeEvent = new ChangeEvent(this);
                }
                ((ChangeListener)listenerList[i + 1]).stateChanged(this.changeEvent);
            }
        }
    }
    
    public MenuElement[] getSelectedPath() {
        final MenuElement[] array = new MenuElement[this.selection.size()];
        for (int i = 0; i < this.selection.size(); ++i) {
            array[i] = (MenuElement)this.selection.elementAt(i);
        }
        return array;
    }
    
    public boolean isComponentPartOfCurrentMenu(final Component component) {
        return this.selection.size() > 0 && this.isComponentPartOfCurrentMenu(this.selection.elementAt(0), component);
    }
    
    private boolean isComponentPartOfCurrentMenu(final MenuElement menuElement, final Component component) {
        if (menuElement == null) {
            return false;
        }
        if (menuElement.getComponent() == component) {
            return true;
        }
        final MenuElement[] subElements = menuElement.getSubElements();
        for (int i = 0; i < subElements.length; ++i) {
            if (this.isComponentPartOfCurrentMenu(subElements[i], component)) {
                return true;
            }
        }
        return false;
    }
    
    private void printMenuElementArray(final MenuElement[] array) {
        this.printMenuElementArray(array, false);
    }
    
    private void printMenuElementArray(final MenuElement[] array, final boolean b) {
        System.out.println("Path is(");
        for (int i = 0; i < array.length; ++i) {
            for (int j = 0; j <= i; ++j) {
                System.out.print("  ");
            }
            final MenuElement menuElement = array[i];
            if (menuElement instanceof JMenuItem) {
                System.out.println(String.valueOf(((JMenuItem)menuElement).getText()) + ", ");
            }
            else if (menuElement instanceof JMenuBar) {
                System.out.println("JMenuBar, ");
            }
            else if (menuElement instanceof JPopupMenu) {
                System.out.println("JPopupMenu, ");
            }
            else if (menuElement == null) {
                System.out.println("NULL , ");
            }
            else {
                System.out.println(menuElement + ", ");
            }
        }
        System.out.println(")");
        if (b) {
            Thread.dumpStack();
        }
    }
    
    public void processKeyEvent(final KeyEvent keyEvent) {
        final Vector vector = (Vector)this.selection.clone();
        for (int i = vector.size() - 1; i >= 0; --i) {
            final MenuElement[] subElements = vector.elementAt(i).getSubElements();
            MenuElement[] array = null;
            for (int j = 0; j < subElements.length; ++j) {
                if (subElements[j] != null && subElements[j].getComponent().isShowing()) {
                    if (array == null) {
                        array = new MenuElement[i + 2];
                        for (int k = 0; k <= i; ++k) {
                            array[k] = vector.elementAt(k);
                        }
                    }
                    array[i + 1] = subElements[j];
                    subElements[j].processKeyEvent(keyEvent, array, this);
                    if (keyEvent.isConsumed()) {
                        return;
                    }
                }
            }
        }
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        final Point point = mouseEvent.getPoint();
        final Component component = (Component)mouseEvent.getSource();
        if (!component.isShowing()) {
            return;
        }
        final int id = mouseEvent.getID();
        final int modifiers = mouseEvent.getModifiers();
        if ((id == 504 || id == 505) && (modifiers & 0x1C) != 0x0) {
            return;
        }
        SwingUtilities.convertPointToScreen(point, component);
        final int x = point.x;
        final int y = point.y;
        final Vector vector = (Vector)this.selection.clone();
        final int size = vector.size();
        boolean b = false;
        for (int n = size - 1; n >= 0 && !b; --n) {
            final MenuElement[] subElements = vector.elementAt(n).getSubElements();
            MenuElement[] array = null;
            for (int n2 = 0; n2 < subElements.length && !b; ++n2) {
                if (subElements[n2] != null) {
                    final Component component2 = subElements[n2].getComponent();
                    if (component2.isShowing()) {
                        int n3;
                        int n4;
                        if (component2 instanceof JComponent) {
                            n3 = ((JComponent)component2).getWidth();
                            n4 = ((JComponent)component2).getHeight();
                        }
                        else {
                            final Rectangle bounds = component2.getBounds();
                            n3 = bounds.width;
                            n4 = bounds.height;
                        }
                        point.x = x;
                        point.y = y;
                        SwingUtilities.convertPointFromScreen(point, component2);
                        if (point.x >= 0 && point.x < n3 && point.y >= 0 && point.y < n4) {
                            if (array == null) {
                                array = new MenuElement[n + 2];
                                for (int i = 0; i <= n; ++i) {
                                    array[i] = vector.elementAt(i);
                                }
                            }
                            array[n + 1] = subElements[n2];
                            final MenuElement[] selectedPath = this.getSelectedPath();
                            if (selectedPath[selectedPath.length - 1] != array[n + 1] && selectedPath[selectedPath.length - 2] != array[n + 1]) {
                                selectedPath[selectedPath.length - 1].processMouseEvent(new MouseEvent(selectedPath[selectedPath.length - 1].getComponent(), 505, mouseEvent.getWhen(), mouseEvent.getModifiers(), point.x, point.y, mouseEvent.getClickCount(), mouseEvent.isPopupTrigger()), array, this);
                                subElements[n2].processMouseEvent(new MouseEvent(component2, 504, mouseEvent.getWhen(), mouseEvent.getModifiers(), point.x, point.y, mouseEvent.getClickCount(), mouseEvent.isPopupTrigger()), array, this);
                            }
                            subElements[n2].processMouseEvent(new MouseEvent(component2, mouseEvent.getID(), mouseEvent.getWhen(), mouseEvent.getModifiers(), point.x, point.y, mouseEvent.getClickCount(), mouseEvent.isPopupTrigger()), array, this);
                            b = true;
                            mouseEvent.consume();
                        }
                    }
                }
            }
        }
    }
    
    public void removeChangeListener(final ChangeListener changeListener) {
        this.listenerList.remove((MenuSelectionManager.class$javax$swing$event$ChangeListener != null) ? MenuSelectionManager.class$javax$swing$event$ChangeListener : (MenuSelectionManager.class$javax$swing$event$ChangeListener = class$("javax.swing.event.ChangeListener")), changeListener);
    }
    
    public void setSelectedPath(MenuElement[] array) {
        final int size = this.selection.size();
        int n = 0;
        if (array == null) {
            array = new MenuElement[0];
        }
        for (int n2 = 0; n2 < array.length && n2 < size && this.selection.elementAt(n2) == array[n2]; ++n2) {
            ++n;
        }
        for (int i = size - 1; i >= n; --i) {
            ((MenuElement)this.selection.elementAt(i)).menuSelectionChanged(false);
            this.selection.removeElementAt(i);
        }
        for (int j = n; j < array.length; ++j) {
            if (array[j] != null) {
                array[j].menuSelectionChanged(true);
                this.selection.addElement(array[j]);
            }
        }
        this.fireStateChanged();
    }
}
