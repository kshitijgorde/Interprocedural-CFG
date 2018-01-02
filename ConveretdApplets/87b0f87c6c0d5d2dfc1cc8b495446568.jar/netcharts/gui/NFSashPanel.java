// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.gui;

import java.awt.Point;
import java.awt.Event;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.util.Vector;
import java.awt.Component;
import java.awt.Panel;

public class NFSashPanel extends Panel implements NFGuiObserver, NFSashPanelIntf
{
    protected Component last;
    protected int style;
    protected Vector children;
    protected boolean sashOn;
    protected int width;
    protected NFGuiObserver observer;
    
    public NFSashPanel() {
        this.last = null;
        this.style = 2;
        this.sashOn = true;
        this.width = 10;
        this.observer = null;
        this.setLayout(new NFSashLayout(this.style));
        this.children = new Vector(4, 2);
    }
    
    public NFSashPanel(final int style) {
        this.last = null;
        this.style = 2;
        this.sashOn = true;
        this.width = 10;
        this.observer = null;
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
            final NFSash nfSash = new NFSash(this.style, this.last, last, this.width, this.sashOn);
            nfSash.addObserver(this);
            super.add(nfSash);
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
            if (component instanceof NFSash) {
                ((NFSash)component).destroy();
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
                if (component2 instanceof NFSash) {
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
            if (component instanceof NFSash && n == n2++) {
                ((NFSash)component).setColor(color);
            }
        }
    }
    
    public boolean mouseDrag(final Event event, final int n, final int n2) {
        for (int countComponents = this.countComponents(), i = 0; i < countComponents; ++i) {
            final Component component = this.getComponent(i);
            if (component instanceof NFSash) {
                final NFSash nfSash = (NFSash)component;
                final Point location = nfSash.location();
                if (nfSash.mouseDrag(event, n - location.x, n2 - location.y)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        for (int countComponents = this.countComponents(), i = 0; i < countComponents; ++i) {
            final Component component = this.getComponent(i);
            if (component instanceof NFSash) {
                final NFSash nfSash = (NFSash)component;
                final Point location = nfSash.location();
                if (nfSash.mouseUp(event, n - location.x, n2 - location.y)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public void buttonPressed(final Object o, final String s) {
        if (o instanceof NFSash && this.observer != null) {
            this.observer.buttonPressed(this, s);
        }
    }
    
    public void valueChanged(final Object o) {
    }
}
