// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.util.Enumeration;
import java.awt.image.ImageObserver;
import java.awt.Toolkit;
import java.awt.Container;
import java.awt.Component;
import java.applet.Applet;
import java.awt.Window;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.awt.Image;
import java.util.Vector;
import java.util.Hashtable;

public class RepaintManager
{
    Hashtable dirtyComponents;
    Hashtable tmpDirtyComponents;
    Vector invalidComponents;
    boolean doubleBufferingEnabled;
    Image doubleBuffer;
    Dimension doubleBufferSize;
    private Dimension doubleBufferMaxSize;
    private static final Object repaintManagerKey;
    Rectangle tmp;
    static /* synthetic */ Class class$javax$swing$RepaintManager;
    
    static {
        repaintManagerKey = ((RepaintManager.class$javax$swing$RepaintManager != null) ? RepaintManager.class$javax$swing$RepaintManager : (RepaintManager.class$javax$swing$RepaintManager = class$("javax.swing.RepaintManager")));
    }
    
    public RepaintManager() {
        this.dirtyComponents = new Hashtable();
        this.tmpDirtyComponents = new Hashtable();
        this.doubleBufferingEnabled = true;
        this.tmp = new Rectangle();
    }
    
    public synchronized void addDirtyRegion(final JComponent component, final int n, final int n2, final int n3, final int n4) {
        if (n3 <= 0 || n4 <= 0 || component == null) {
            return;
        }
        if (component.getWidth() <= 0 || component.getHeight() <= 0) {
            return;
        }
        Component component2 = null;
        for (Container parent = component; parent != null; parent = parent.getParent()) {
            if (!parent.isVisible() || parent.getPeer() == null) {
                return;
            }
            if (parent instanceof Window || parent instanceof Applet) {
                component2 = parent;
                break;
            }
        }
        final Rectangle rectangle = this.dirtyComponents.get(component);
        if (rectangle == null) {
            this.dirtyComponents.put(component, new Rectangle(n, n2, n3, n4));
        }
        else {
            SwingUtilities.computeUnion(n, n2, n3, n4, rectangle);
        }
        SystemEventQueueUtilities.queueComponentWorkRequest(component2);
    }
    
    public synchronized void addInvalidComponent(final JComponent component) {
        Container container = null;
        for (Container parent = component; parent != null; parent = parent.getParent()) {
            if (parent instanceof CellRendererPane || parent.getPeer() == null) {
                return;
            }
            if (parent instanceof JComponent && ((JComponent)parent).isValidateRoot()) {
                container = parent;
                break;
            }
        }
        if (container == null) {
            return;
        }
        Component component2 = null;
        for (Container parent2 = container; parent2 != null; parent2 = parent2.getParent()) {
            if (!parent2.isVisible() || parent2.getPeer() == null) {
                return;
            }
            if (parent2 instanceof Window || parent2 instanceof Applet) {
                component2 = parent2;
                break;
            }
        }
        if (component2 == null) {
            return;
        }
        if (this.invalidComponents == null) {
            this.invalidComponents = new Vector();
        }
        else {
            for (int size = this.invalidComponents.size(), i = 0; i < size; ++i) {
                if (container == this.invalidComponents.elementAt(i)) {
                    return;
                }
            }
        }
        this.invalidComponents.addElement(container);
        SystemEventQueueUtilities.queueComponentWorkRequest(component2);
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    void collectDirtyComponents(final Hashtable hashtable, final JComponent component, final Vector vector) {
        JComponent component2 = component;
        JComponent component3 = component;
        Rectangle rectangle = component._bounds;
        int n2;
        int n = n2 = 0;
        int n4;
        int n3 = n4 = 0;
        this.tmp = new Rectangle(hashtable.get(component));
        SwingUtilities.computeIntersection(0, 0, rectangle.width, rectangle.height, this.tmp);
        if (this.tmp.isEmpty()) {
            return;
        }
        if (component.isOpaque()) {}
        while (true) {
            final Container parent = component3.getParent();
            if (parent == null || !(parent instanceof JComponent)) {
                if (component != component2) {
                    this.tmp.setLocation(this.tmp.x + n - n2, this.tmp.y + n3 - n4);
                    SwingUtilities.computeUnion(this.tmp.x, this.tmp.y, this.tmp.width, this.tmp.height, hashtable.get(component2));
                }
                if (!vector.contains(component2)) {
                    vector.addElement(component2);
                }
                return;
            }
            component3 = (JComponent)parent;
            if (component3.isOpaque()) {}
            n2 += rectangle.x;
            n4 += rectangle.y;
            this.tmp.setLocation(this.tmp.x + rectangle.x, this.tmp.y + rectangle.y);
            rectangle = component3._bounds;
            this.tmp = SwingUtilities.computeIntersection(0, 0, rectangle.width, rectangle.height, this.tmp);
            if (this.tmp.isEmpty()) {
                return;
            }
            if (hashtable.get(component3) == null) {
                continue;
            }
            component2 = component3;
            n = n2;
            n3 = n4;
        }
    }
    
    public static RepaintManager currentManager(final Component component) {
        RepaintManager repaintManager = (RepaintManager)SwingUtilities.appContextGet(RepaintManager.repaintManagerKey);
        if (repaintManager == null) {
            repaintManager = new RepaintManager();
            SwingUtilities.appContextPut(RepaintManager.repaintManagerKey, repaintManager);
        }
        return repaintManager;
    }
    
    public static RepaintManager currentManager(final JComponent component) {
        return currentManager((Component)component);
    }
    
    public Rectangle getDirtyRegion(final JComponent component) {
        Rectangle rectangle = null;
        synchronized (this) {
            rectangle = this.dirtyComponents.get(component);
        }
        if (rectangle == null) {
            return new Rectangle(0, 0, 0, 0);
        }
        return new Rectangle(rectangle);
    }
    
    public Dimension getDoubleBufferMaximumSize() {
        if (this.doubleBufferMaxSize == null) {
            this.doubleBufferMaxSize = Toolkit.getDefaultToolkit().getScreenSize();
        }
        return this.doubleBufferMaxSize;
    }
    
    public Image getOffscreenBuffer(final Component component, final int n, final int n2) {
        final Dimension doubleBufferMaximumSize = this.getDoubleBufferMaximumSize();
        int width;
        if (n < 1) {
            width = 1;
        }
        else if (n > doubleBufferMaximumSize.width) {
            width = doubleBufferMaximumSize.width;
        }
        else {
            width = n;
        }
        int height;
        if (n2 < 1) {
            height = 1;
        }
        else if (n2 > doubleBufferMaximumSize.height) {
            height = doubleBufferMaximumSize.height;
        }
        else {
            height = n2;
        }
        if (this.doubleBuffer != null && (this.doubleBuffer.getWidth(null) < width || this.doubleBuffer.getHeight(null) < height)) {
            this.doubleBuffer = null;
        }
        int width2 = width;
        int height2 = height;
        if (this.doubleBuffer != null) {
            width2 = this.doubleBufferSize.width;
            height2 = this.doubleBufferSize.height;
            if (this.doubleBufferSize.width < width) {
                width2 = width;
                this.doubleBuffer = null;
            }
            if (this.doubleBufferSize.height < height) {
                height2 = height;
                this.doubleBuffer = null;
            }
        }
        if (this.doubleBuffer == null) {
            this.doubleBuffer = component.createImage(width2, height2);
            this.doubleBufferSize = new Dimension(width2, height2);
        }
        return this.doubleBuffer;
    }
    
    public boolean isCompletelyDirty(final JComponent component) {
        final Rectangle dirtyRegion = this.getDirtyRegion(component);
        return dirtyRegion.width == Integer.MAX_VALUE && dirtyRegion.height == Integer.MAX_VALUE;
    }
    
    public boolean isDoubleBufferingEnabled() {
        return this.doubleBufferingEnabled;
    }
    
    public void markCompletelyClean(final JComponent component) {
        synchronized (this) {
            this.dirtyComponents.remove(component);
        }
    }
    
    public void markCompletelyDirty(final JComponent component) {
        this.addDirtyRegion(component, 0, 0, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    
    public void paintDirtyRegions() {
        synchronized (this) {
            final Hashtable tmpDirtyComponents = this.tmpDirtyComponents;
            this.tmpDirtyComponents = this.dirtyComponents;
            (this.dirtyComponents = tmpDirtyComponents).clear();
        }
        final int size = this.tmpDirtyComponents.size();
        if (size == 0) {
            return;
        }
        final int n = 0;
        final int n2 = 0;
        final Vector vector = new Vector<JComponent>(size);
        final Enumeration<JComponent> keys = (Enumeration<JComponent>)this.tmpDirtyComponents.keys();
        while (keys.hasMoreElements()) {
            this.collectDirtyComponents(this.tmpDirtyComponents, keys.nextElement(), vector);
        }
        for (int size2 = vector.size(), i = 0; i < size2; ++i) {
            final JComponent component = vector.elementAt(i);
            final Rectangle rectangle = this.tmpDirtyComponents.get(component);
            SwingUtilities.computeIntersection(n, n2, component.getWidth(), component.getHeight(), rectangle);
            component.paintImmediately(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        }
        this.tmpDirtyComponents.clear();
    }
    
    public synchronized void removeInvalidComponent(final JComponent component) {
        if (this.invalidComponents != null) {
            final int index = this.invalidComponents.indexOf(component);
            if (index != -1) {
                this.invalidComponents.removeElementAt(index);
            }
        }
    }
    
    public static void setCurrentManager(final RepaintManager repaintManager) {
        if (repaintManager != null) {
            SwingUtilities.appContextPut(RepaintManager.repaintManagerKey, repaintManager);
        }
        else {
            SwingUtilities.appContextRemove(RepaintManager.repaintManagerKey);
        }
    }
    
    public void setDoubleBufferMaximumSize(final Dimension doubleBufferMaxSize) {
        this.doubleBufferMaxSize = doubleBufferMaxSize;
        if (this.doubleBuffer != null && (this.doubleBuffer.getWidth(null) > doubleBufferMaxSize.width || this.doubleBuffer.getHeight(null) > doubleBufferMaxSize.height)) {
            this.doubleBuffer = null;
        }
    }
    
    public void setDoubleBufferingEnabled(final boolean doubleBufferingEnabled) {
        this.doubleBufferingEnabled = doubleBufferingEnabled;
    }
    
    public synchronized String toString() {
        final StringBuffer sb = new StringBuffer();
        if (this.dirtyComponents != null) {
            sb.append(String.valueOf(String.valueOf(this.dirtyComponents)));
        }
        return sb.toString();
    }
    
    public void validateInvalidComponents() {
        final Vector invalidComponents;
        synchronized (this) {
            if (this.invalidComponents == null) {
                // monitorexit(this)
                return;
            }
            invalidComponents = this.invalidComponents;
            this.invalidComponents = null;
        }
        for (int size = invalidComponents.size(), i = 0; i < size; ++i) {
            invalidComponents.elementAt(i).validate();
        }
    }
}
