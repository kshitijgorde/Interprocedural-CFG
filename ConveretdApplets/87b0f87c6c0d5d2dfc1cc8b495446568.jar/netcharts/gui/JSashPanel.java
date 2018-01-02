// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.gui;

import java.awt.Point;
import java.awt.Event;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;
import netcharts.util.NFEventAdapter;
import java.util.Vector;
import java.awt.Component;
import javax.swing.JPanel;

public class JSashPanel extends JPanel implements NFGuiObserver, NFSashPanelIntf
{
    protected Component last;
    protected int style;
    protected Vector children;
    protected boolean sashOn;
    protected int width;
    protected NFGuiObserver observer;
    
    public JSashPanel() {
        this.last = null;
        this.style = 2;
        this.sashOn = true;
        this.width = 10;
        this.observer = null;
        final NFEventAdapter nfEventAdapter = new NFEventAdapter(this);
        this.setLayout(new NFSashLayout(this.style));
        this.children = new Vector(4, 2);
    }
    
    public JSashPanel(final int style) {
        this.last = null;
        this.style = 2;
        this.sashOn = true;
        this.width = 10;
        this.observer = null;
        final NFEventAdapter nfEventAdapter = new NFEventAdapter(this);
        this.style = style;
        this.setLayout(new NFSashLayout(style));
        this.children = new Vector(4, 2);
    }
    
    public void addObserver(final NFGuiObserver observer) {
        this.observer = observer;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    private void a(final Color color) {
        for (int i = 0; i < this.getComponentCount(); ++i) {
            final Component component = this.getComponent(i);
            if (component != null && component instanceof JSash) {
                ((JSash)component).setColor(color);
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        this.a(this.getBackground());
        super.paint(graphics);
    }
    
    public void enable(final boolean sashOn) {
        this.sashOn = sashOn;
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public void addLine(final NFSashLine nfSashLine) {
        super.add(nfSashLine, 0);
    }
    
    public void removeLine(final NFSashLine nfSashLine) {
        super.remove(nfSashLine);
    }
    
    public Component add(final Component last) {
        if (this.last != null) {
            final JSash sash = new JSash(this.style, this.last, last, this.width, this.sashOn);
            sash.addObserver(this);
            super.add(sash);
        }
        this.last = last;
        this.children.addElement(last);
        return super.add(last);
    }
    
    public void remove(final Component component) {
        this.children.removeElement(component);
        super.remove(component);
    }
    
    public void destroy() {
        for (int i = 0; i < this.getComponentCount(); ++i) {
            final Component component = this.getComponent(i);
            if (component instanceof JSash) {
                ((JSash)component).destroy();
            }
        }
        this.last = null;
        this.children.removeAllElements();
        this.observer = null;
        this.removeAll();
    }
    
    public int countManagedComponents() {
        return this.children.size();
    }
    
    public Component getManagedComponent(final int n) {
        return this.children.elementAt(n);
    }
    
    public Vector getManagedComponents() {
        return this.children;
    }
    
    public Dimension allComponentsSize() {
        final int countComponents = this.countComponents();
        int n = 0;
        int n2 = 0;
        int width = 0;
        int height = 0;
        Component component = null;
        for (int i = 0; i < countComponents; ++i) {
            final Component component2 = this.getComponent(i);
            if (!(component2 instanceof NFSashLine)) {
                if (component2 instanceof JSash) {
                    final Dimension preferredSize = component2.preferredSize();
                    width = preferredSize.width;
                    height = preferredSize.height;
                }
                else {
                    Dimension dimension = component2.size();
                    if (dimension.width <= 0 || dimension.height <= 0) {
                        dimension = component2.preferredSize();
                    }
                    n += dimension.width;
                    n2 += dimension.height;
                    if (component != null) {
                        n += width;
                        n2 += height;
                    }
                    component = component2;
                }
            }
        }
        return new Dimension(n, n2);
    }
    
    public void setSashColor(final Color color, final int n) {
        int n2 = 0;
        for (int countComponents = this.countComponents(), i = 0; i < countComponents; ++i) {
            final Component component = this.getComponent(i);
            if (component instanceof JSash && n == n2++) {
                ((JSash)component).setColor(color);
            }
        }
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        for (int countComponents = this.countComponents(), i = 0; i < countComponents; ++i) {
            final Component component = this.getComponent(i);
            if (component instanceof JSash) {
                final JSash sash = (JSash)component;
                final Point location = sash.location();
                if (sash.mouseDrag(event, n - location.x, n2 - location.y)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        for (int countComponents = this.countComponents(), i = 0; i < countComponents; ++i) {
            final Component component = this.getComponent(i);
            if (component instanceof JSash) {
                final JSash sash = (JSash)component;
                final Point location = sash.location();
                if (sash.mouseUp(event, n - location.x, n2 - location.y)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void buttonPressed(final Object o, final String s) {
        if (o instanceof JSash && this.observer != null) {
            this.observer.buttonPressed(this, s);
        }
    }
    
    public void valueChanged(final Object o) {
    }
}
