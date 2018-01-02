// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.event.KeyEvent;
import java.awt.Dialog;
import java.awt.Window;
import java.awt.Rectangle;
import java.awt.Component;
import java.awt.Container;
import java.util.Stack;

public class DefaultFocusManager extends FocusManager
{
    Stack history;
    
    public DefaultFocusManager() {
        this.history = new Stack();
    }
    
    Component[] childrenTabOrder(final Container container) {
        final Component[] components = container.getComponents();
        for (int i = 0, length = components.length; i < length; ++i) {
            for (int j = i; j < length; ++j) {
                if (i != j && this.compareTabOrder(components[j], components[i])) {
                    final Component component = components[i];
                    components[i] = components[j];
                    components[j] = component;
                }
            }
        }
        return components;
    }
    
    void clearHistory() {
        this.history.removeAllElements();
    }
    
    public boolean compareTabOrder(final Component component, final Component component2) {
        int n;
        int n2;
        if (component instanceof JComponent) {
            n = ((JComponent)component).getY();
            n2 = ((JComponent)component).getX();
        }
        else {
            final Rectangle bounds = component.getBounds();
            n = bounds.y;
            n2 = bounds.x;
        }
        int n3;
        int n4;
        if (component2 instanceof JComponent) {
            n3 = ((JComponent)component2).getY();
            n4 = ((JComponent)component2).getX();
        }
        else {
            final Rectangle bounds2 = component2.getBounds();
            n3 = bounds2.y;
            n4 = bounds2.x;
        }
        if (Math.abs(n - n3) < 10) {
            return n2 < n4;
        }
        return n < n3;
    }
    
    public void focusNextComponent(final Component component) {
        if (component instanceof JComponent) {
            final JComponent component2 = (JComponent)component;
            final Container focusRoot = this.getFocusRoot(component2);
            if (!this.history.empty() && this.history.peek() != component) {
                this.history.removeAllElements();
            }
            if (focusRoot != null) {
                final Component focusableComponentAfter = this.getFocusableComponentAfter(component2, focusRoot, true);
                if (focusableComponentAfter != null) {
                    if (this.history.empty() || this.history.peek() == component) {
                        this.history.push(focusableComponentAfter);
                    }
                    if (focusableComponentAfter instanceof JComponent) {
                        ((JComponent)focusableComponentAfter).grabFocus();
                    }
                    else {
                        focusableComponentAfter.requestFocus();
                    }
                }
            }
        }
    }
    
    public void focusPreviousComponent(final Component component) {
        if (component instanceof JComponent) {
            final JComponent component2 = (JComponent)component;
            final Container focusRoot = this.getFocusRoot(component2);
            if (!this.history.empty() && this.history.peek() == component) {
                this.history.pop();
                if (!this.history.empty()) {
                    final Component component3 = this.history.peek();
                    if (component3 instanceof JComponent) {
                        ((JComponent)component3).grabFocus();
                    }
                    else {
                        component3.requestFocus();
                    }
                    return;
                }
            }
            this.history.removeAllElements();
            if (focusRoot != null) {
                final Component focusableComponentAfter = this.getFocusableComponentAfter(component2, focusRoot, false);
                if (focusableComponentAfter != null) {
                    if (focusableComponentAfter instanceof JComponent) {
                        ((JComponent)focusableComponentAfter).grabFocus();
                    }
                    else {
                        focusableComponentAfter.requestFocus();
                    }
                }
            }
        }
    }
    
    public Component getComponentAfter(final Container container, final Component component) {
        final Component nextFocusableComponent;
        if (component instanceof JComponent && (nextFocusableComponent = ((JComponent)component).getNextFocusableComponent()) != null) {
            return nextFocusableComponent;
        }
        return this.tabOrderNextComponent(container, component);
    }
    
    public Component getComponentBefore(final Container container, final Component component) {
        final Component inverseGetNextFocusable;
        if ((inverseGetNextFocusable = this.inverseGetNextFocusable(container, component)) != null) {
            return inverseGetNextFocusable;
        }
        return this.tabOrderPreviousComponent(container, component);
    }
    
    private Component getDeepestLastComponent(final Component component) {
        if (component.isVisible() && ((component instanceof JComponent && !((JComponent)component).isManagingFocus()) || !(component instanceof JComponent)) && component instanceof Container && ((Container)component).getComponentCount() > 0) {
            return this.getDeepestLastComponent(this.getLastComponent((Container)component));
        }
        return component;
    }
    
    public Component getFirstComponent(final Container container) {
        final Component[] childrenTabOrder = this.childrenTabOrder(container);
        if (childrenTabOrder.length > 0) {
            return childrenTabOrder[0];
        }
        return null;
    }
    
    Container getFocusRoot(final Component component) {
        for (Container container = component.getParent(); container != null; container = container.getParent()) {
            if ((container instanceof JComponent && ((JComponent)container).isFocusCycleRoot()) || container instanceof Window || container instanceof Dialog) {
                return container;
            }
        }
        return null;
    }
    
    private Component getFocusableComponentAfter(final Component component, final Container container, final boolean b) {
        Component component2 = component;
        do {
            if (b) {
                component2 = this.getNextComponent(component2, container, true);
            }
            else {
                component2 = this.getPreviousComponent(component2, container);
            }
            if (component2 == null) {
                break;
            }
            if (component2 == component) {
                break;
            }
        } while (!component2.isVisible() || !component2.isFocusTraversable() || !component2.isEnabled());
        return component2;
    }
    
    public Component getLastComponent(final Container container) {
        final Component[] childrenTabOrder = this.childrenTabOrder(container);
        if (childrenTabOrder.length > 0) {
            return childrenTabOrder[childrenTabOrder.length - 1];
        }
        return null;
    }
    
    private Component getNextComponent(final Component component, final Container container, final boolean b) {
        if (b && component.isVisible() && (!(component instanceof JComponent) || !((JComponent)component).isManagingFocus()) && component instanceof Container && ((Container)component).getComponentCount() > 0) {
            return this.getFirstComponent((Container)component);
        }
        final Container parent = component.getParent();
        final Component componentAfter = this.getComponentAfter(parent, component);
        if (componentAfter != null) {
            return componentAfter;
        }
        if (parent == container) {
            return container;
        }
        return this.getNextComponent(parent, container, false);
    }
    
    private Component getPreviousComponent(final Component component, final Container container) {
        final Container parent = component.getParent();
        if (component == container) {
            return this.getDeepestLastComponent(container);
        }
        final Component componentBefore = this.getComponentBefore(parent, component);
        if (componentBefore != null) {
            return this.getDeepestLastComponent(componentBefore);
        }
        return parent;
    }
    
    private Component inverseGetNextFocusable(final Container container, final Component component) {
        final Component[] components = container.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if (components[i] instanceof JComponent && ((JComponent)components[i]).getNextFocusableComponent() == component) {
                return components[i];
            }
        }
        return null;
    }
    
    public void processKeyEvent(final Component component, final KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == 9 || keyEvent.getKeyChar() == '\t') {
            if (component instanceof JComponent && ((JComponent)component).isManagingFocus() && ((keyEvent.getModifiers() & 0x2) != 0x2 || keyEvent.getKeyCode() == 73)) {
                return;
            }
            if (keyEvent.getID() != 401) {
                keyEvent.consume();
                return;
            }
            if ((keyEvent.getModifiers() & 0x1) == 0x1) {
                this.focusPreviousComponent(component);
            }
            else {
                this.focusNextComponent(component);
            }
            keyEvent.consume();
        }
    }
    
    private Component tabOrderNextComponent(final Container container, final Component component) {
        final Component[] childrenTabOrder = this.childrenTabOrder(container);
        final int length = childrenTabOrder.length;
        if (length == 1) {
            return null;
        }
        for (int i = 0; i < length - 1; ++i) {
            if (childrenTabOrder[i] == component) {
                return childrenTabOrder[i + 1];
            }
        }
        return null;
    }
    
    private Component tabOrderPreviousComponent(final Container container, final Component component) {
        final Component[] childrenTabOrder = this.childrenTabOrder(container);
        final int length = childrenTabOrder.length;
        if (length == 1) {
            return null;
        }
        for (int i = 1; i < length; ++i) {
            if (childrenTabOrder[i] == component) {
                return childrenTabOrder[i - 1];
            }
        }
        return null;
    }
}
