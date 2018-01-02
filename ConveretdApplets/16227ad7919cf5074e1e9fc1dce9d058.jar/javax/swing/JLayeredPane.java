// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import javax.accessibility.AccessibleRole;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Container;
import javax.accessibility.AccessibleContext;
import java.awt.Component;
import java.awt.LayoutManager;
import java.util.Hashtable;
import javax.accessibility.Accessible;

public class JLayeredPane extends JComponent implements Accessible
{
    public static final Integer DEFAULT_LAYER;
    public static final Integer PALETTE_LAYER;
    public static final Integer MODAL_LAYER;
    public static final Integer POPUP_LAYER;
    public static final Integer DRAG_LAYER;
    public static final Integer FRAME_CONTENT_LAYER;
    public static final String LAYER_PROPERTY = "layeredContainerLayer";
    private Hashtable componentToLayer;
    private boolean optimizedDrawingPossible;
    
    static {
        DEFAULT_LAYER = new Integer(0);
        PALETTE_LAYER = new Integer(100);
        MODAL_LAYER = new Integer(200);
        POPUP_LAYER = new Integer(300);
        DRAG_LAYER = new Integer(400);
        FRAME_CONTENT_LAYER = new Integer(-30000);
    }
    
    public JLayeredPane() {
        this.optimizedDrawingPossible = true;
        this.setLayout(null);
    }
    
    protected void addImpl(final Component component, final Object o, final int n) {
        JLayeredPane.DEFAULT_LAYER;
        int n2;
        if (o instanceof Integer) {
            n2 = (int)o;
            this.setLayer(component, n2);
        }
        else {
            n2 = this.getLayer(component);
        }
        super.addImpl(component, o, this.insertIndexForLayer(n2, n));
        component.validate();
        component.repaint();
        this.validateOptimizedDrawing();
    }
    
    public AccessibleContext getAccessibleContext() {
        if (super.accessibleContext == null) {
            super.accessibleContext = new AccessibleJLayeredPane();
        }
        return super.accessibleContext;
    }
    
    public int getComponentCountInLayer(final int n) {
        int n2 = 0;
        for (int componentCount = this.getComponentCount(), i = 0; i < componentCount; ++i) {
            final int layer = this.getLayer(this.getComponent(i));
            if (layer == n) {
                ++n2;
            }
            else {
                if (n2 > 0) {
                    break;
                }
                if (layer < n) {
                    break;
                }
            }
        }
        return n2;
    }
    
    protected Hashtable getComponentToLayer() {
        if (this.componentToLayer == null) {
            this.componentToLayer = new Hashtable(4);
        }
        return this.componentToLayer;
    }
    
    public Component[] getComponentsInLayer(final int n) {
        int n2 = 0;
        final Component[] array = new Component[this.getComponentCountInLayer(n)];
        for (int componentCount = this.getComponentCount(), i = 0; i < componentCount; ++i) {
            final int layer = this.getLayer(this.getComponent(i));
            if (layer == n) {
                array[n2++] = this.getComponent(i);
            }
            else {
                if (n2 > 0) {
                    break;
                }
                if (layer < n) {
                    break;
                }
            }
        }
        return array;
    }
    
    public int getIndexOf(final Component component) {
        for (int componentCount = this.getComponentCount(), i = 0; i < componentCount; ++i) {
            if (component == this.getComponent(i)) {
                return i;
            }
        }
        return -1;
    }
    
    public int getLayer(final Component component) {
        Integer n;
        if (component instanceof JComponent) {
            n = (Integer)((JComponent)component).getClientProperty("layeredContainerLayer");
        }
        else {
            n = this.getComponentToLayer().get(component);
        }
        if (n == null) {
            return JLayeredPane.DEFAULT_LAYER;
        }
        return n;
    }
    
    public static int getLayer(final JComponent component) {
        final Integer n;
        if ((n = (Integer)component.getClientProperty("layeredContainerLayer")) != null) {
            return n;
        }
        return JLayeredPane.DEFAULT_LAYER;
    }
    
    public static JLayeredPane getLayeredPaneAbove(final Component component) {
        if (component == null) {
            return null;
        }
        Container container;
        for (container = component.getParent(); container != null && !(container instanceof JLayeredPane); container = container.getParent()) {}
        return (JLayeredPane)container;
    }
    
    protected Integer getObjectForLayer(final int n) {
        Integer n2 = null;
        switch (n) {
            case 0: {
                n2 = JLayeredPane.DEFAULT_LAYER;
                break;
            }
            case 100: {
                n2 = JLayeredPane.PALETTE_LAYER;
                break;
            }
            case 200: {
                n2 = JLayeredPane.MODAL_LAYER;
                break;
            }
            case 300: {
                n2 = JLayeredPane.POPUP_LAYER;
                break;
            }
            case 400: {
                n2 = JLayeredPane.DRAG_LAYER;
                break;
            }
            default: {
                n2 = new Integer(n);
                break;
            }
        }
        return n2;
    }
    
    public int getPosition(final Component component) {
        int n = 0;
        this.getComponentCount();
        final int index = this.getIndexOf(component);
        if (index == -1) {
            return -1;
        }
        final int layer = this.getLayer(component);
        for (int i = index - 1; i >= 0; --i) {
            if (this.getLayer(this.getComponent(i)) != layer) {
                return n;
            }
            ++n;
        }
        return n;
    }
    
    public int highestLayer() {
        if (this.getComponentCount() > 0) {
            return this.getLayer(this.getComponent(0));
        }
        return 0;
    }
    
    protected int insertIndexForLayer(final int n, final int n2) {
        int n3 = -1;
        int n4 = -1;
        final int componentCount = this.getComponentCount();
        int i = 0;
        while (i < componentCount) {
            final int layer = this.getLayer(this.getComponent(i));
            if (n3 == -1 && layer == n) {
                n3 = i;
            }
            if (layer < n) {
                if (i == 0) {
                    n3 = 0;
                    n4 = 0;
                    break;
                }
                n4 = i;
                break;
            }
            else {
                ++i;
            }
        }
        if (n3 == -1 && n4 == -1) {
            return componentCount;
        }
        if (n3 != -1 && n4 == -1) {
            n4 = componentCount;
        }
        if (n4 != -1 && n3 == -1) {
            n3 = n4;
        }
        if (n2 == -1) {
            return n4;
        }
        if (n2 > -1 && n3 + n2 <= n4) {
            return n3 + n2;
        }
        return n4;
    }
    
    public boolean isOptimizedDrawingEnabled() {
        return this.optimizedDrawingPossible;
    }
    
    public int lowestLayer() {
        final int componentCount = this.getComponentCount();
        if (componentCount > 0) {
            return this.getLayer(this.getComponent(componentCount - 1));
        }
        return 0;
    }
    
    public void moveToBack(final Component component) {
        this.setPosition(component, this.getComponentCountInLayer(this.getLayer(component)));
    }
    
    public void moveToFront(final Component component) {
        this.setPosition(component, 0);
    }
    
    public void paint(final Graphics graphics) {
        if (this.isOpaque()) {
            final Rectangle clipBounds = graphics.getClipBounds();
            Color color = this.getBackground();
            if (color == null) {
                color = Color.lightGray;
            }
            graphics.setColor(color);
            graphics.fillRect(clipBounds.x, clipBounds.y, clipBounds.width, clipBounds.height);
        }
        super.paint(graphics);
    }
    
    protected String paramString() {
        return String.valueOf(super.paramString()) + ",optimizedDrawingPossible=" + (this.optimizedDrawingPossible ? "true" : "false");
    }
    
    public static void putLayer(final JComponent component, final int n) {
        component.putClientProperty("layeredContainerLayer", new Integer(n));
    }
    
    public void remove(final int n) {
        this.getComponent(n);
        super.remove(n);
        this.validateOptimizedDrawing();
    }
    
    public void setLayer(final Component component, final int n) {
        this.setLayer(component, n, -1);
    }
    
    public void setLayer(final Component component, final int n, final int n2) {
        final Integer objectForLayer = this.getObjectForLayer(n);
        if (n == this.getLayer(component) && n2 == this.getPosition(component)) {
            if (component instanceof JComponent) {
                this.repaint(((JComponent)component)._bounds);
            }
            else {
                this.repaint(component.getBounds());
            }
            return;
        }
        if (component instanceof JComponent) {
            ((JComponent)component).putClientProperty("layeredContainerLayer", objectForLayer);
        }
        else {
            this.getComponentToLayer().put(component, objectForLayer);
        }
        if (component.getParent() == null || component.getParent() != this) {
            if (component instanceof JComponent) {
                this.repaint(((JComponent)component)._bounds);
            }
            else {
                this.repaint(component.getBounds());
            }
            return;
        }
        this.remove(component);
        this.add(component, null, n2);
        if (component instanceof JComponent) {
            this.repaint(((JComponent)component)._bounds);
        }
        else {
            this.repaint(component.getBounds());
        }
    }
    
    public void setPosition(final Component component, final int n) {
        this.setLayer(component, this.getLayer(component), n);
    }
    
    private void validateOptimizedDrawing() {
        boolean b = false;
        synchronized (this.getTreeLock()) {
            for (int i = 0; i < this.getComponentCount(); ++i) {
                Integer n = null;
                if ((this.getComponent(i) instanceof JInternalFrame || (this.getComponent(i) instanceof JComponent && (n = (Integer)((JComponent)this.getComponent(i)).getClientProperty("layeredContainerLayer")) != null)) && (n == null || !n.equals(JLayeredPane.FRAME_CONTENT_LAYER))) {
                    b = true;
                    break;
                }
            }
        }
        // monitorexit(this.getTreeLock())
        if (b) {
            this.optimizedDrawingPossible = false;
        }
        else {
            this.optimizedDrawingPossible = true;
        }
    }
    
    protected class AccessibleJLayeredPane extends AccessibleJComponent
    {
        public AccessibleRole getAccessibleRole() {
            return AccessibleRole.LAYERED_PANE;
        }
    }
}
