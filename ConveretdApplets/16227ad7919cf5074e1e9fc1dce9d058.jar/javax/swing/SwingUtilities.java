// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.Graphics;
import javax.swing.text.View;
import java.lang.reflect.InvocationTargetException;
import java.util.Vector;
import java.awt.Frame;
import javax.accessibility.AccessibleSelection;
import javax.accessibility.AccessibleState;
import javax.accessibility.AccessibleStateSet;
import javax.accessibility.AccessibleComponent;
import javax.accessibility.AccessibleContext;
import javax.accessibility.Accessible;
import java.awt.Container;
import java.awt.Window;
import java.applet.Applet;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.Component;
import java.awt.FontMetrics;
import java.awt.Rectangle;
import java.util.Hashtable;

public class SwingUtilities implements SwingConstants
{
    private static boolean canAccessEventQueue;
    private static boolean eventQueueTested;
    static boolean is1dot2;
    private static Class eventDispatchThreadClass;
    private static final Object sharedOwnerFrameKey;
    private static final Object dialogsKey;
    static Hashtable appContextTable;
    static /* synthetic */ Class class$java$lang$Class;
    
    static {
        SwingUtilities.canAccessEventQueue = false;
        SwingUtilities.eventQueueTested = false;
        SwingUtilities.is1dot2 = true;
        try {
            SwingUtilities.is1dot2 = (((SwingUtilities.class$java$lang$Class != null) ? SwingUtilities.class$java$lang$Class : (SwingUtilities.class$java$lang$Class = class$("java.lang.Class"))).getMethod("getProtectionDomain", (Class[])null) != null);
        }
        catch (NoSuchMethodException ex) {
            SwingUtilities.is1dot2 = false;
        }
        if (SwingUtilities.is1dot2) {
            System.err.println("warning: running 1.1 version of SwingUtilities");
        }
        SwingUtilities.eventDispatchThreadClass = null;
        sharedOwnerFrameKey = new StringBuffer("SwingUtilities.sharedOwnerFrame");
        dialogsKey = new StringBuffer("SwingUtilities.dialogs");
        SwingUtilities.appContextTable = new Hashtable(2);
    }
    
    private SwingUtilities() {
        throw new Error("SwingUtilities is just a container for static methods");
    }
    
    static Object appContextGet(final Object o) {
        return SwingUtilities.appContextTable.get(o);
    }
    
    static void appContextPut(final Object o, final Object o2) {
        SwingUtilities.appContextTable.put(o, o2);
    }
    
    static void appContextRemove(final Object o) {
        SwingUtilities.appContextTable.remove(o);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public static Rectangle[] computeDifference(final Rectangle rectangle, final Rectangle rectangle2) {
        if (rectangle2 == null || !rectangle.intersects(rectangle2) || isRectangleContainingRectangle(rectangle2, rectangle)) {
            return new Rectangle[0];
        }
        final Rectangle rectangle3 = new Rectangle();
        Rectangle rectangle4 = null;
        Rectangle rectangle5 = null;
        Rectangle rectangle6 = null;
        Rectangle rectangle7 = null;
        int n = 0;
        if (isRectangleContainingRectangle(rectangle, rectangle2)) {
            rectangle3.x = rectangle.x;
            rectangle3.y = rectangle.y;
            rectangle3.width = rectangle2.x - rectangle.x;
            rectangle3.height = rectangle.height;
            if (rectangle3.width > 0 && rectangle3.height > 0) {
                rectangle4 = new Rectangle(rectangle3);
                ++n;
            }
            rectangle3.x = rectangle2.x;
            rectangle3.y = rectangle.y;
            rectangle3.width = rectangle2.width;
            rectangle3.height = rectangle2.y - rectangle.y;
            if (rectangle3.width > 0 && rectangle3.height > 0) {
                rectangle5 = new Rectangle(rectangle3);
                ++n;
            }
            rectangle3.x = rectangle2.x;
            rectangle3.y = rectangle2.y + rectangle2.height;
            rectangle3.width = rectangle2.width;
            rectangle3.height = rectangle.y + rectangle.height - (rectangle2.y + rectangle2.height);
            if (rectangle3.width > 0 && rectangle3.height > 0) {
                rectangle6 = new Rectangle(rectangle3);
                ++n;
            }
            rectangle3.x = rectangle2.x + rectangle2.width;
            rectangle3.y = rectangle.y;
            rectangle3.width = rectangle.x + rectangle.width - (rectangle2.x + rectangle2.width);
            rectangle3.height = rectangle.height;
            if (rectangle3.width > 0 && rectangle3.height > 0) {
                rectangle7 = new Rectangle(rectangle3);
                ++n;
            }
        }
        else if (rectangle2.x <= rectangle.x && rectangle2.y <= rectangle.y) {
            if (rectangle2.x + rectangle2.width > rectangle.x + rectangle.width) {
                rectangle3.x = rectangle.x;
                rectangle3.y = rectangle2.y + rectangle2.height;
                rectangle3.width = rectangle.width;
                rectangle3.height = rectangle.y + rectangle.height - (rectangle2.y + rectangle2.height);
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle4 = rectangle3;
                    ++n;
                }
            }
            else if (rectangle2.y + rectangle2.height > rectangle.y + rectangle.height) {
                rectangle3.setBounds(rectangle2.x + rectangle2.width, rectangle.y, rectangle.x + rectangle.width - (rectangle2.x + rectangle2.width), rectangle.height);
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle4 = rectangle3;
                    ++n;
                }
            }
            else {
                rectangle3.setBounds(rectangle2.x + rectangle2.width, rectangle.y, rectangle.x + rectangle.width - (rectangle2.x + rectangle2.width), rectangle2.y + rectangle2.height - rectangle.y);
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle4 = new Rectangle(rectangle3);
                    ++n;
                }
                rectangle3.setBounds(rectangle.x, rectangle2.y + rectangle2.height, rectangle.width, rectangle.y + rectangle.height - (rectangle2.y + rectangle2.height));
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle5 = new Rectangle(rectangle3);
                    ++n;
                }
            }
        }
        else if (rectangle2.x <= rectangle.x && rectangle2.y + rectangle2.height >= rectangle.y + rectangle.height) {
            if (rectangle2.x + rectangle2.width > rectangle.x + rectangle.width) {
                rectangle3.setBounds(rectangle.x, rectangle.y, rectangle.width, rectangle2.y - rectangle.y);
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle4 = rectangle3;
                    ++n;
                }
            }
            else {
                rectangle3.setBounds(rectangle.x, rectangle.y, rectangle.width, rectangle2.y - rectangle.y);
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle4 = new Rectangle(rectangle3);
                    ++n;
                }
                rectangle3.setBounds(rectangle2.x + rectangle2.width, rectangle2.y, rectangle.x + rectangle.width - (rectangle2.x + rectangle2.width), rectangle.y + rectangle.height - rectangle2.y);
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle5 = new Rectangle(rectangle3);
                    ++n;
                }
            }
        }
        else if (rectangle2.x <= rectangle.x) {
            if (rectangle2.x + rectangle2.width >= rectangle.x + rectangle.width) {
                rectangle3.setBounds(rectangle.x, rectangle.y, rectangle.width, rectangle2.y - rectangle.y);
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle4 = new Rectangle(rectangle3);
                    ++n;
                }
                rectangle3.setBounds(rectangle.x, rectangle2.y + rectangle2.height, rectangle.width, rectangle.y + rectangle.height - (rectangle2.y + rectangle2.height));
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle5 = new Rectangle(rectangle3);
                    ++n;
                }
            }
            else {
                rectangle3.setBounds(rectangle.x, rectangle.y, rectangle.width, rectangle2.y - rectangle.y);
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle4 = new Rectangle(rectangle3);
                    ++n;
                }
                rectangle3.setBounds(rectangle2.x + rectangle2.width, rectangle2.y, rectangle.x + rectangle.width - (rectangle2.x + rectangle2.width), rectangle2.height);
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle5 = new Rectangle(rectangle3);
                    ++n;
                }
                rectangle3.setBounds(rectangle.x, rectangle2.y + rectangle2.height, rectangle.width, rectangle.y + rectangle.height - (rectangle2.y + rectangle2.height));
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle6 = new Rectangle(rectangle3);
                    ++n;
                }
            }
        }
        else if (rectangle2.x <= rectangle.x + rectangle.width && rectangle2.x + rectangle2.width > rectangle.x + rectangle.width) {
            if (rectangle2.y <= rectangle.y && rectangle2.y + rectangle2.height > rectangle.y + rectangle.height) {
                rectangle3.setBounds(rectangle.x, rectangle.y, rectangle2.x - rectangle.x, rectangle.height);
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle4 = rectangle3;
                    ++n;
                }
            }
            else if (rectangle2.y <= rectangle.y) {
                rectangle3.setBounds(rectangle.x, rectangle.y, rectangle2.x - rectangle.x, rectangle2.y + rectangle2.height - rectangle.y);
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle4 = new Rectangle(rectangle3);
                    ++n;
                }
                rectangle3.setBounds(rectangle.x, rectangle2.y + rectangle2.height, rectangle.width, rectangle.y + rectangle.height - (rectangle2.y + rectangle2.height));
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle5 = new Rectangle(rectangle3);
                    ++n;
                }
            }
            else if (rectangle2.y + rectangle2.height > rectangle.y + rectangle.height) {
                rectangle3.setBounds(rectangle.x, rectangle.y, rectangle.width, rectangle2.y - rectangle.y);
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle4 = new Rectangle(rectangle3);
                    ++n;
                }
                rectangle3.setBounds(rectangle.x, rectangle2.y, rectangle2.x - rectangle.x, rectangle.y + rectangle.height - rectangle2.y);
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle5 = new Rectangle(rectangle3);
                    ++n;
                }
            }
            else {
                rectangle3.setBounds(rectangle.x, rectangle.y, rectangle.width, rectangle2.y - rectangle.y);
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle4 = new Rectangle(rectangle3);
                    ++n;
                }
                rectangle3.setBounds(rectangle.x, rectangle2.y, rectangle2.x - rectangle.x, rectangle2.height);
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle5 = new Rectangle(rectangle3);
                    ++n;
                }
                rectangle3.setBounds(rectangle.x, rectangle2.y + rectangle2.height, rectangle.width, rectangle.y + rectangle.height - (rectangle2.y + rectangle2.height));
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle6 = new Rectangle(rectangle3);
                    ++n;
                }
            }
        }
        else if (rectangle2.x >= rectangle.x && rectangle2.x + rectangle2.width <= rectangle.x + rectangle.width) {
            if (rectangle2.y <= rectangle.y && rectangle2.y + rectangle2.height > rectangle.y + rectangle.height) {
                rectangle3.setBounds(rectangle.x, rectangle.y, rectangle2.x - rectangle.x, rectangle.height);
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle4 = new Rectangle(rectangle3);
                    ++n;
                }
                rectangle3.setBounds(rectangle2.x + rectangle2.width, rectangle.y, rectangle.x + rectangle.width - (rectangle2.x + rectangle2.width), rectangle.height);
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle5 = new Rectangle(rectangle3);
                    ++n;
                }
            }
            else if (rectangle2.y <= rectangle.y) {
                rectangle3.setBounds(rectangle.x, rectangle.y, rectangle2.x - rectangle.x, rectangle.height);
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle4 = new Rectangle(rectangle3);
                    ++n;
                }
                rectangle3.setBounds(rectangle2.x, rectangle2.y + rectangle2.height, rectangle2.width, rectangle.y + rectangle.height - (rectangle2.y + rectangle2.height));
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle5 = new Rectangle(rectangle3);
                    ++n;
                }
                rectangle3.setBounds(rectangle2.x + rectangle2.width, rectangle.y, rectangle.x + rectangle.width - (rectangle2.x + rectangle2.width), rectangle.height);
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle6 = new Rectangle(rectangle3);
                    ++n;
                }
            }
            else {
                rectangle3.setBounds(rectangle.x, rectangle.y, rectangle2.x - rectangle.x, rectangle.height);
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle4 = new Rectangle(rectangle3);
                    ++n;
                }
                rectangle3.setBounds(rectangle2.x, rectangle.y, rectangle2.width, rectangle2.y - rectangle.y);
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle5 = new Rectangle(rectangle3);
                    ++n;
                }
                rectangle3.setBounds(rectangle2.x + rectangle2.width, rectangle.y, rectangle.x + rectangle.width - (rectangle2.x + rectangle2.width), rectangle.height);
                if (rectangle3.width > 0 && rectangle3.height > 0) {
                    rectangle6 = new Rectangle(rectangle3);
                    ++n;
                }
            }
        }
        final Rectangle[] array = new Rectangle[n];
        int n2 = 0;
        if (rectangle4 != null) {
            array[n2++] = rectangle4;
        }
        if (rectangle5 != null) {
            array[n2++] = rectangle5;
        }
        if (rectangle6 != null) {
            array[n2++] = rectangle6;
        }
        if (rectangle7 != null) {
            array[n2++] = rectangle7;
        }
        return array;
    }
    
    public static Rectangle computeIntersection(final int n, final int n2, final int n3, final int n4, final Rectangle rectangle) {
        final int x = (n > rectangle.x) ? n : rectangle.x;
        final int n5 = (n + n3 < rectangle.x + rectangle.width) ? (n + n3) : (rectangle.x + rectangle.width);
        final int y = (n2 > rectangle.y) ? n2 : rectangle.y;
        final int n6 = (n2 + n4 < rectangle.y + rectangle.height) ? (n2 + n4) : (rectangle.y + rectangle.height);
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = n5 - x;
        rectangle.height = n6 - y;
        if (rectangle.width < 0 || rectangle.height < 0) {
            final boolean b = false;
            rectangle.height = (b ? 1 : 0);
            rectangle.width = (b ? 1 : 0);
            rectangle.y = (b ? 1 : 0);
            rectangle.x = (b ? 1 : 0);
        }
        return rectangle;
    }
    
    public static int computeStringWidth(final FontMetrics fontMetrics, final String s) {
        if (SwingUtilities.is1dot2) {
            return fontMetrics.stringWidth(s);
        }
        final int[] widths = fontMetrics.getWidths();
        int n = 0;
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (char1 > '\u00ff') {
                return fontMetrics.stringWidth(s);
            }
            n += widths[char1];
        }
        return n;
    }
    
    public static Rectangle computeUnion(final int n, final int n2, final int n3, final int n4, final Rectangle rectangle) {
        final int x = (n < rectangle.x) ? n : rectangle.x;
        final int n5 = (n + n3 > rectangle.x + rectangle.width) ? (n + n3) : (rectangle.x + rectangle.width);
        final int y = (n2 < rectangle.y) ? n2 : rectangle.y;
        final int n6 = (n2 + n4 > rectangle.y + rectangle.height) ? (n2 + n4) : (rectangle.y + rectangle.height);
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = n5 - x;
        rectangle.height = n6 - y;
        return rectangle;
    }
    
    public static MouseEvent convertMouseEvent(final Component component, final MouseEvent mouseEvent, final Component component2) {
        final Point convertPoint = convertPoint(component, new Point(mouseEvent.getX(), mouseEvent.getY()), component2);
        Component component3;
        if (component2 != null) {
            component3 = component2;
        }
        else {
            component3 = component;
        }
        return new MouseEvent(component3, mouseEvent.getID(), mouseEvent.getWhen(), mouseEvent.getModifiers(), convertPoint.x, convertPoint.y, mouseEvent.getClickCount(), mouseEvent.isPopupTrigger());
    }
    
    public static Point convertPoint(final Component component, final int n, final int n2, final Component component2) {
        return convertPoint(component, new Point(n, n2), component2);
    }
    
    public static Point convertPoint(Component windowAncestor, final Point point, Component windowAncestor2) {
        if (windowAncestor == null && windowAncestor2 == null) {
            return point;
        }
        if (windowAncestor == null) {
            windowAncestor = getWindowAncestor(windowAncestor2);
            if (windowAncestor == null) {
                throw new Error("Source component not connected to component tree hierarchy");
            }
        }
        final Point point2 = new Point(point);
        convertPointToScreen(point2, windowAncestor);
        if (windowAncestor2 == null) {
            windowAncestor2 = getWindowAncestor(windowAncestor);
            if (windowAncestor2 == null) {
                throw new Error("Destination component not connected to component tree hierarchy");
            }
        }
        convertPointFromScreen(point2, windowAncestor2);
        return point2;
    }
    
    public static void convertPointFromScreen(final Point point, Component parent) {
        do {
            int n;
            int n2;
            if (parent instanceof JComponent) {
                n = ((JComponent)parent).getX();
                n2 = ((JComponent)parent).getY();
            }
            else if (parent instanceof Applet) {
                final Point locationOnScreen = parent.getLocationOnScreen();
                n = locationOnScreen.x;
                n2 = locationOnScreen.y;
            }
            else {
                final Rectangle bounds = parent.getBounds();
                n = bounds.x;
                n2 = bounds.y;
            }
            point.x -= n;
            point.y -= n2;
            if (parent instanceof Window) {
                break;
            }
            if (parent instanceof Applet) {
                break;
            }
            parent = parent.getParent();
        } while (parent != null);
    }
    
    public static void convertPointToScreen(final Point point, Component parent) {
        do {
            int n;
            int n2;
            if (parent instanceof JComponent) {
                n = ((JComponent)parent).getX();
                n2 = ((JComponent)parent).getY();
            }
            else if (parent instanceof Applet) {
                final Point locationOnScreen = parent.getLocationOnScreen();
                n = locationOnScreen.x;
                n2 = locationOnScreen.y;
            }
            else {
                final Rectangle bounds = parent.getBounds();
                n = bounds.x;
                n2 = bounds.y;
            }
            point.x += n;
            point.y += n2;
            if (parent instanceof Window) {
                break;
            }
            if (parent instanceof Applet) {
                break;
            }
            parent = parent.getParent();
        } while (parent != null);
    }
    
    public static Rectangle convertRectangle(final Component component, final Rectangle rectangle, final Component component2) {
        final Point convertPoint = convertPoint(component, new Point(rectangle.x, rectangle.y), component2);
        return new Rectangle(convertPoint.x, convertPoint.y, rectangle.width, rectangle.height);
    }
    
    static final void doPrivileged(final Runnable runnable) {
        runnable.run();
    }
    
    public static Component findFocusOwner(final Component component) {
        if (component instanceof Window) {
            return ((Window)component).getFocusOwner();
        }
        if (component instanceof JComponent && ((JComponent)component).hasFocus()) {
            return component;
        }
        if (component instanceof Container) {
            for (int countComponents = ((Container)component).countComponents(), i = 0; i < countComponents; ++i) {
                final Component focusOwner = findFocusOwner(((Container)component).getComponent(i));
                if (focusOwner != null) {
                    return focusOwner;
                }
            }
            return null;
        }
        return null;
    }
    
    public static Accessible getAccessibleAt(final Component component, final Point point) {
        if (component instanceof Accessible) {
            final Accessible accessible = (Accessible)component;
            if (accessible != null) {
                AccessibleContext accessibleContext = accessible.getAccessibleContext();
                if (accessibleContext != null) {
                    for (int accessibleChildrenCount = accessibleContext.getAccessibleChildrenCount(), i = 0; i < accessibleChildrenCount; ++i) {
                        final Accessible accessibleChild = accessibleContext.getAccessibleChild(i);
                        if (accessibleChild != null) {
                            accessibleContext = accessibleChild.getAccessibleContext();
                            if (accessibleContext != null) {
                                final AccessibleComponent accessibleComponent = accessibleContext.getAccessibleComponent();
                                if (accessibleComponent != null && accessibleComponent.isShowing()) {
                                    final Point location = accessibleComponent.getLocation();
                                    if (accessibleComponent.contains(new Point(point.x - location.x, point.y - location.y))) {
                                        return accessibleChild;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return (Accessible)component;
        }
        Object o = component;
        if (!component.contains(point.x, point.y)) {
            o = null;
        }
        else if (component instanceof Container) {
            final Container container = (Container)component;
            for (int componentCount = container.getComponentCount(), j = 0; j < componentCount; ++j) {
                final Component component2 = container.getComponent(j);
                if (component2 != null && component2.isShowing()) {
                    final Point location2 = component2.getLocation();
                    if (component2.contains(point.x - location2.x, point.y - location2.y)) {
                        o = component2;
                    }
                }
            }
        }
        if (o instanceof Accessible) {
            return (Accessible)o;
        }
        return null;
    }
    
    public static Accessible getAccessibleChild(final Component component, final int n) {
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            int n2 = 0;
            for (int i = 0; i < components.length; ++i) {
                if (components[i] instanceof Accessible) {
                    if (n2 == n) {
                        return (Accessible)components[i];
                    }
                    ++n2;
                }
            }
        }
        return null;
    }
    
    public static int getAccessibleChildrenCount(final Component component) {
        int n = 0;
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                if (components[i] instanceof Accessible) {
                    ++n;
                }
            }
        }
        return n;
    }
    
    public static int getAccessibleIndexInParent(final Component component) {
        int n = -1;
        final Container parent = component.getParent();
        if (parent != null && parent instanceof Accessible) {
            final Component[] components = parent.getComponents();
            for (int i = 0; i < components.length; ++i) {
                if (components[i] instanceof Accessible) {
                    ++n;
                }
                if (component.equals(components[i])) {
                    return n;
                }
            }
        }
        return -1;
    }
    
    public static AccessibleStateSet getAccessibleStateSet(final Component component) {
        final AccessibleStateSet set = new AccessibleStateSet();
        if (component.isEnabled()) {
            set.add(AccessibleState.ENABLED);
        }
        if (component.isFocusTraversable()) {
            set.add(AccessibleState.FOCUSABLE);
        }
        if (component.isVisible()) {
            set.add(AccessibleState.VISIBLE);
        }
        if (component.isShowing()) {
            set.add(AccessibleState.SHOWING);
        }
        for (Container container = component.getParent(); container != null; container = container.getParent()) {
            if (container instanceof Window && ((Window)container).getFocusOwner() == component) {
                set.add(AccessibleState.FOCUSED);
            }
        }
        if (component instanceof Accessible) {
            final AccessibleContext accessibleContext = ((Accessible)component).getAccessibleContext();
            if (accessibleContext != null) {
                final Accessible accessibleParent = accessibleContext.getAccessibleParent();
                if (accessibleParent != null) {
                    final AccessibleContext accessibleContext2 = accessibleParent.getAccessibleContext();
                    if (accessibleContext2 != null) {
                        final AccessibleSelection accessibleSelection = accessibleContext2.getAccessibleSelection();
                        if (accessibleSelection != null) {
                            set.add(AccessibleState.SELECTABLE);
                            final int accessibleIndexInParent = accessibleContext.getAccessibleIndexInParent();
                            if (accessibleIndexInParent >= 0 && accessibleSelection.isAccessibleChildSelected(accessibleIndexInParent)) {
                                set.add(AccessibleState.SELECTED);
                            }
                        }
                    }
                }
            }
        }
        if (component instanceof JComponent && ((JComponent)component).isOpaque()) {
            set.add(AccessibleState.OPAQUE);
        }
        return set;
    }
    
    public static Container getAncestorNamed(final String s, final Component component) {
        if (component == null || s == null) {
            return null;
        }
        Container container;
        for (container = component.getParent(); container != null && !s.equals(container.getName()); container = container.getParent()) {}
        return container;
    }
    
    public static Container getAncestorOfClass(final Class clazz, final Component component) {
        if (component == null || clazz == null) {
            return null;
        }
        Container container;
        for (container = component.getParent(); container != null && !clazz.isInstance(container); container = container.getParent()) {}
        return container;
    }
    
    private static CellRendererPane getCellRendererPane(final Component component, final Container container) {
        Container parent = component.getParent();
        if (parent instanceof CellRendererPane) {
            if (parent.getParent() != container) {
                container.add(parent);
            }
        }
        else {
            parent = new CellRendererPane();
            parent.add(component);
            container.add(parent);
        }
        return (CellRendererPane)parent;
    }
    
    public static Component getDeepestComponentAt(final Component component, final int n, final int n2) {
        if (!component.contains(n, n2)) {
            return null;
        }
        if (component instanceof Container) {
            final Component[] components = ((Container)component).getComponents();
            for (int i = 0; i < components.length; ++i) {
                final Component component2 = components[i];
                if (component2 != null && component2.isVisible()) {
                    final Point location = component2.getLocation();
                    Component component3;
                    if (component2 instanceof Container) {
                        component3 = getDeepestComponentAt(component2, n - location.x, n2 - location.y);
                    }
                    else {
                        component3 = component2.getComponentAt(n - location.x, n2 - location.y);
                    }
                    if (component3 != null && component3.isVisible()) {
                        return component3;
                    }
                }
            }
        }
        return component;
    }
    
    public static Rectangle getLocalBounds(final Component component) {
        final Rectangle rectangle2;
        final Rectangle rectangle = rectangle2 = new Rectangle(component.getBounds());
        final boolean b = false;
        rectangle.y = (b ? 1 : 0);
        rectangle2.x = (b ? 1 : 0);
        return rectangle;
    }
    
    static JDialog getRecycledModalDialog(final Frame frame, final String title) {
        Vector vector = (Vector)appContextGet(SwingUtilities.dialogsKey);
        if (vector == null) {
            vector = new Vector<JDialog>();
            appContextPut(SwingUtilities.dialogsKey, vector);
        }
        JDialog dialog = null;
        synchronized (vector) {
            for (int i = 0; i < vector.size(); ++i) {
                dialog = vector.elementAt(i);
                if (dialog.getParent() == frame) {
                    vector.removeElement(dialog);
                    dialog.setTitle(title);
                    // monitorexit(vector)
                    return dialog;
                }
            }
            dialog = new JDialog(frame, title, true);
        }
        // monitorexit(vector)
        return dialog;
    }
    
    public static Component getRoot(final Component component) {
        Component component2 = null;
        for (Component parent = component; parent != null; parent = parent.getParent()) {
            if (parent instanceof Window) {
                return parent;
            }
            if (parent instanceof Applet) {
                component2 = parent;
            }
        }
        return component2;
    }
    
    public static JRootPane getRootPane(Component parent) {
        if (parent instanceof RootPaneContainer) {
            return ((RootPaneContainer)parent).getRootPane();
        }
        while (parent != null) {
            if (parent instanceof JRootPane) {
                return (JRootPane)parent;
            }
            parent = parent.getParent();
        }
        return null;
    }
    
    static Frame getSharedOwnerFrame() {
        Frame frame = (Frame)appContextGet(SwingUtilities.sharedOwnerFrameKey);
        if (frame == null) {
            frame = new Frame() {
                public synchronized void dispose() {
                    try {
                        this.getToolkit().getSystemEventQueue();
                        super.dispose();
                    }
                    catch (Exception ex) {}
                }
                
                public void show() {
                }
            };
            appContextPut(SwingUtilities.sharedOwnerFrameKey, frame);
        }
        return frame;
    }
    
    private static Window getWindowAncestor(final Component component) {
        for (Container container = component.getParent(); container != null; container = container.getParent()) {
            if (container instanceof Window) {
                return (Window)container;
            }
        }
        return null;
    }
    
    public static void invokeAndWait(final Runnable runnable) throws InterruptedException, InvocationTargetException {
        if (isEventDispatchThread()) {
            throw new Error("Cannot call invokeAndWait from the event dispatcher thread");
        }
        final Object object = new Object() {
            private final /* synthetic */ Runnable val$doRun = val$doRun;
            
            public String toString() {
                return "SwingUtilities.invokeAndWait() lock for " + this.val$doRun;
            }
        };
        Throwable postRunnable = null;
        synchronized (object) {
            postRunnable = SystemEventQueueUtilities.postRunnable(runnable, object);
            object.wait();
        }
        // monitorexit(object)
        if (postRunnable != null) {
            throw new InvocationTargetException(postRunnable);
        }
    }
    
    public static void invokeLater(final Runnable runnable) {
        SystemEventQueueUtilities.postRunnable(runnable, null);
    }
    
    public static boolean isDescendingFrom(final Component component, final Component component2) {
        if (component == component2) {
            return true;
        }
        for (Container container = component.getParent(); container != null; container = container.getParent()) {
            if (container == component2) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean isEventDispatchThread() {
        final Thread currentThread = Thread.currentThread();
        if (SwingUtilities.eventDispatchThreadClass != null) {
            return SwingUtilities.eventDispatchThreadClass.isInstance(currentThread);
        }
        final Class<? extends Thread> class1 = currentThread.getClass();
        if (class1.getName().indexOf("EventDispatchThread") >= 0 || class1.getName().indexOf("JMEventQueue") >= 0) {
            SwingUtilities.eventDispatchThreadClass = class1;
            return true;
        }
        return false;
    }
    
    public static boolean isLeftMouseButton(final MouseEvent mouseEvent) {
        if (SwingUtilities.is1dot2) {
            return (mouseEvent.getModifiers() & 0x10) != 0x0;
        }
        return (mouseEvent.getModifiers() & 0x10) != 0x0 || (mouseEvent.getModifiers() & 0xC) == 0x0;
    }
    
    static boolean isLeftToRight(final Component component) {
        return true;
    }
    
    public static boolean isMiddleMouseButton(final MouseEvent mouseEvent) {
        return (mouseEvent.getModifiers() & 0x8) == 0x8;
    }
    
    public static final boolean isRectangleContainingRectangle(final Rectangle rectangle, final Rectangle rectangle2) {
        return rectangle2.x >= rectangle.x && rectangle2.x + rectangle2.width <= rectangle.x + rectangle.width && rectangle2.y >= rectangle.y && rectangle2.y + rectangle2.height <= rectangle.y + rectangle.height;
    }
    
    public static boolean isRightMouseButton(final MouseEvent mouseEvent) {
        return (mouseEvent.getModifiers() & 0x4) == 0x4;
    }
    
    public static String layoutCompoundLabel(final FontMetrics fontMetrics, final String s, final Icon icon, final int n, final int n2, final int n3, final int n4, final Rectangle rectangle, final Rectangle rectangle2, final Rectangle rectangle3, final int n5) {
        return layoutCompoundLabelImpl(null, fontMetrics, s, icon, n, n2, n3, n4, rectangle, rectangle2, rectangle3, n5);
    }
    
    public static String layoutCompoundLabel(final JComponent component, final FontMetrics fontMetrics, final String s, final Icon icon, final int n, final int n2, final int n3, final int n4, final Rectangle rectangle, final Rectangle rectangle2, final Rectangle rectangle3, final int n5) {
        final boolean b = true;
        int n6 = n2;
        int n7 = n4;
        switch (n2) {
            case 10: {
                n6 = (b ? 2 : 4);
                break;
            }
            case 11: {
                n6 = (b ? 4 : 2);
                break;
            }
        }
        switch (n4) {
            case 10: {
                n7 = (b ? 2 : 4);
                break;
            }
            case 11: {
                n7 = (b ? 4 : 2);
                break;
            }
        }
        return layoutCompoundLabelImpl(component, fontMetrics, s, icon, n, n6, n3, n7, rectangle, rectangle2, rectangle3, n5);
    }
    
    private static String layoutCompoundLabelImpl(final JComponent component, final FontMetrics fontMetrics, String string, final Icon icon, final int n, final int n2, final int n3, final int n4, final Rectangle rectangle, final Rectangle rectangle2, final Rectangle rectangle3, final int n5) {
        if (icon != null) {
            rectangle2.width = icon.getIconWidth();
            rectangle2.height = icon.getIconHeight();
        }
        else {
            final boolean b = false;
            rectangle2.height = (b ? 1 : 0);
            rectangle2.width = (b ? 1 : 0);
        }
        final boolean b2 = string == null || string.equals("");
        View view = null;
        if (b2) {
            final boolean b3 = false;
            rectangle3.height = (b3 ? 1 : 0);
            rectangle3.width = (b3 ? 1 : 0);
            string = "";
        }
        else {
            view = ((component != null) ? ((View)component.getClientProperty("html")) : null);
            if (view != null) {
                rectangle3.width = (int)view.getPreferredSpan(0);
                rectangle3.height = (int)view.getPreferredSpan(1);
            }
            else {
                rectangle3.width = computeStringWidth(fontMetrics, string);
                rectangle3.height = fontMetrics.getHeight();
            }
        }
        final int n6 = (b2 || icon == null) ? 0 : n5;
        if (!b2) {
            int width;
            if (n4 == 0) {
                width = rectangle.width;
            }
            else {
                width = rectangle.width - (rectangle2.width + n6);
            }
            if (rectangle3.width > width) {
                if (view != null) {
                    rectangle3.width = width;
                }
                else {
                    final String s = "...";
                    int computeStringWidth = computeStringWidth(fontMetrics, s);
                    int i;
                    for (i = 0; i < string.length(); ++i) {
                        computeStringWidth += fontMetrics.charWidth(string.charAt(i));
                        if (computeStringWidth > width) {
                            break;
                        }
                    }
                    string = String.valueOf(string.substring(0, i)) + s;
                    rectangle3.width = computeStringWidth(fontMetrics, string);
                }
            }
        }
        if (n3 == 1) {
            if (n4 != 0) {
                rectangle3.y = 0;
            }
            else {
                rectangle3.y = -(rectangle3.height + n6);
            }
        }
        else if (n3 == 0) {
            rectangle3.y = rectangle2.height / 2 - rectangle3.height / 2;
        }
        else if (n4 != 0) {
            rectangle3.y = rectangle2.height - rectangle3.height;
        }
        else {
            rectangle3.y = rectangle2.height + n6;
        }
        if (n4 == 2) {
            rectangle3.x = -(rectangle3.width + n6);
        }
        else if (n4 == 0) {
            rectangle3.x = rectangle2.width / 2 - rectangle3.width / 2;
        }
        else {
            rectangle3.x = rectangle2.width + n6;
        }
        final int min = Math.min(rectangle2.x, rectangle3.x);
        final int n7 = Math.max(rectangle2.x + rectangle2.width, rectangle3.x + rectangle3.width) - min;
        final int min2 = Math.min(rectangle2.y, rectangle3.y);
        final int n8 = Math.max(rectangle2.y + rectangle2.height, rectangle3.y + rectangle3.height) - min2;
        int n9;
        if (n == 1) {
            n9 = rectangle.y - min2;
        }
        else if (n == 0) {
            n9 = rectangle.y + rectangle.height / 2 - (min2 + n8 / 2);
        }
        else {
            n9 = rectangle.y + rectangle.height - (min2 + n8);
        }
        int n10;
        if (n2 == 2) {
            n10 = rectangle.x - min;
        }
        else if (n2 == 4) {
            n10 = rectangle.x + rectangle.width - (min + n7);
        }
        else {
            n10 = rectangle.x + rectangle.width / 2 - (min + n7 / 2);
        }
        rectangle3.x += n10;
        rectangle3.y += n9;
        rectangle2.x += n10;
        rectangle2.y += n9;
        return string;
    }
    
    static Class loadSystemClass(final String s) throws ClassNotFoundException {
        return Class.forName(s);
    }
    
    public static void paintComponent(final Graphics graphics, final Component component, final Container container, final int n, final int n2, final int n3, final int n4) {
        getCellRendererPane(component, container).paintComponent(graphics, component, container, n, n2, n3, n4, false);
    }
    
    public static void paintComponent(final Graphics graphics, final Component component, final Container container, final Rectangle rectangle) {
        paintComponent(graphics, component, container, rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
    
    static void recycleModalDialog(final JDialog dialog) {
        final Vector vector = (Vector)appContextGet(SwingUtilities.dialogsKey);
        synchronized (vector) {
            dialog.getContentPane().removeAll();
            vector.addElement(dialog);
        }
        // monitorexit(vector)
    }
    
    public static void updateComponentTreeUI(final Component component) {
        updateComponentTreeUI0(component);
        component.invalidate();
        component.validate();
        component.repaint();
    }
    
    private static void updateComponentTreeUI0(final Component component) {
        if (component instanceof JComponent) {
            ((JComponent)component).updateUI();
        }
        Component[] array = null;
        if (component instanceof JMenu) {
            array = ((JMenu)component).getMenuComponents();
        }
        else if (component instanceof Container) {
            array = ((Container)component).getComponents();
        }
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                updateComponentTreeUI0(array[i]);
            }
        }
    }
    
    public static Window windowForComponent(final Component component) {
        for (Container container = component.getParent(); container != null; container = container.getParent()) {
            if (container instanceof Window) {
                return (Window)container;
            }
        }
        return null;
    }
}
