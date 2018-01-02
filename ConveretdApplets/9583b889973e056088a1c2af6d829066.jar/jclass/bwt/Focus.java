// 
// Decompiled by Procyon v0.5.30
// 

package jclass.bwt;

import java.awt.Frame;
import java.util.Vector;
import java.awt.Container;
import java.awt.Component;

class Focus
{
    private static boolean isTraversable(final Component component) {
        if (component instanceof JCComponent) {
            return ((JCComponent)component).isFocusTraversable();
        }
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                if (isTraversable(components[i])) {
                    return true;
                }
            }
            return false;
        }
        return component.isShowing() && component.isEnabled();
    }
    
    static JCComponent findFocus(final Container container) {
        if (container == null) {
            return null;
        }
        final Component[] components = container.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if (components[i] instanceof Container) {
                return findFocus((Container)components[i]);
            }
            if (components[i] instanceof JCComponent && ((JCComponent)components[i]).hasFocus()) {
                return (JCComponent)components[i];
            }
        }
        return null;
    }
    
    static Component getFirstChild(final Container container) {
        if (!container.isVisible()) {
            return null;
        }
        final Component[] components = container.getComponents();
        for (int i = 0; i < components.length; ++i) {
            if (components[i] instanceof Container) {
                final Component firstChild = getFirstChild((Container)components[i]);
                if (firstChild != null) {
                    return firstChild;
                }
            }
            else if (isTraversable(components[i])) {
                return components[i];
            }
        }
        return null;
    }
    
    private static final int countChildren(final Component component, final Vector vector) {
        if (component == null) {
            return 0;
        }
        if (component instanceof Container) {
            int n = 1;
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                n += countChildren(components[i], vector);
            }
            return n;
        }
        if (isTraversable(component)) {
            if (vector != null) {
                vector.addElement(component);
            }
            return 1;
        }
        return 0;
    }
    
    private static Component getNextChild(final Container container, final Component component) {
        final Vector vector = new Vector<Component>(countChildren(container, null));
        countChildren(container, vector);
        for (int i = vector.indexOf(component) + 1; i < vector.size(); ++i) {
            final Component component2 = vector.elementAt(i);
            if (component2.getParent() != component.getParent()) {
                return component2;
            }
        }
        return null;
    }
    
    private static Component getPreviousChild(final Container container, final Component component) {
        final Vector vector = new Vector<Component>(countChildren(container, null));
        countChildren(container, vector);
        for (int i = vector.indexOf(component) - 1; i >= 0; --i) {
            final Component component2 = vector.elementAt(i);
            if (component2.getParent() != component.getParent()) {
                return component2;
            }
        }
        return null;
    }
    
    static Component getLastChild(final Container container) {
        final Component[] components = container.getComponents();
        int i;
        for (i = components.length - 1; i >= 0; --i) {
            if (components[i] instanceof Container) {
                return getLastChild((Container)components[i]);
            }
            if (isTraversable(components[i])) {
                break;
            }
        }
        if (i >= 0) {
            return components[i];
        }
        return null;
    }
    
    static void nextFocus(Component component) {
        if (component.getParent() == null) {
            return;
        }
        final Component[] components = component.getParent().getComponents();
        if (components.length == 1) {
            return;
        }
        int n;
        for (n = 0; n < components.length && components[n] != component; ++n) {}
        component = null;
        for (int n2 = n + 1; component == null && n2 < components.length; ++n2) {
            if (isTraversable(components[n2])) {
                component = components[n2];
            }
        }
        for (int n3 = 0; component == null && n3 < n; ++n3) {
            if (isTraversable(components[n3])) {
                component = components[n3];
            }
        }
        if (component != null) {
            component.requestFocus();
        }
    }
    
    static void nextFocus(final Container container, final Component component) {
        final Frame frame = BWTUtil.getFrame(container);
        Component component2 = getNextChild(frame, component);
        if (component2 == null) {
            component2 = getFirstChild(frame);
        }
        if (component2 != null) {
            component2.requestFocus();
        }
    }
    
    static void previousFocus(final Container container, final Component component) {
        final Frame frame = BWTUtil.getFrame(container);
        Component component2 = getPreviousChild(frame, component);
        if (component2 == null) {
            component2 = getLastChild(frame);
        }
        if (component2 != null) {
            component2.requestFocus();
        }
    }
    
    static void previousFocus(Component component) {
        if (component.getParent() == null) {
            return;
        }
        final Component[] components = component.getParent().getComponents();
        if (components.length == 1) {
            return;
        }
        int n;
        for (n = 0; n < components.length && components[n] != component; ++n) {}
        component = null;
        for (int n2 = n - 1; component == null && n2 >= 0; --n2) {
            if (isTraversable(components[n2])) {
                component = components[n2];
            }
        }
        for (int n3 = components.length - 1; component == null && n3 != n; --n3) {
            if (isTraversable(components[n3])) {
                component = components[n3];
            }
        }
        if (component != null) {
            component.requestFocus();
        }
    }
}
