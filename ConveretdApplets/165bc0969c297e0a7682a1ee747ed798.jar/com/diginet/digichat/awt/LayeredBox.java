// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.awt;

import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.Event;
import java.awt.event.WindowEvent;
import java.awt.Dimension;
import com.diginet.digichat.util.Informer;
import com.diginet.digichat.util.Finder;
import java.util.Enumeration;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.LayoutManager;
import java.util.Vector;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.ComponentListener;
import java.awt.event.WindowListener;
import java.awt.Frame;

public class LayeredBox extends Frame implements LayeredContainer, WindowListener, ComponentListener
{
    private int nX;
    private int nY;
    private Insets insets;
    private Component cmpMain;
    private Component cmpDrag;
    private MenuBar mnuBar;
    private MenuPopup pupMenu;
    private Vector vecDrag;
    private Vector vecBounds;
    
    public LayeredBox() {
        this.insets = null;
        this.mnuBar = null;
        this.pupMenu = null;
        this.vecDrag = new Vector();
        this.vecBounds = new Vector();
        final Component component = null;
        this.cmpDrag = component;
        this.cmpMain = component;
        super.setLayout(null);
        this.addWindowListener(this);
        this.addComponentListener(this);
    }
    
    private void blocking() {
        if (this.insets != null) {
            int height;
            if (this.mnuBar == null) {
                height = 0;
            }
            else {
                this.mnuBar.setBounds(this.insets.left, this.insets.top, this.size().width - this.insets.left - this.insets.right, height = this.mnuBar.size().height);
            }
            final Component[] components = this.getComponents();
            for (int i = 0; i < components.length; ++i) {
                final Component component;
                if ((component = components[i]) == this.cmpMain) {
                    final Rectangle bounds = this.getBounds();
                    component.setBounds(this.insets.left, this.insets.top + height, bounds.width - this.insets.left - this.insets.right, bounds.height - this.insets.top - this.insets.bottom - height);
                    component.validate();
                }
                else if (component != this.mnuBar) {
                    final Point location = component.getLocation();
                    component.setLocation(location.x + this.insets.left, location.y + this.insets.top + height);
                }
            }
        }
    }
    
    public void setBar(final MenuBar mnuBar) {
        if (this.mnuBar != null) {
            this.remove(this.mnuBar);
        }
        this.add(this.mnuBar = mnuBar);
        this.blocking();
    }
    
    public void removeBar() {
        if (this.mnuBar != null) {
            this.remove(this.mnuBar);
            this.mnuBar = null;
            this.blocking();
        }
    }
    
    public void setMain(final Component cmpMain) {
        if (this.cmpMain != null) {
            this.remove(this.cmpMain);
        }
        this.add(this.cmpMain = cmpMain, this.getComponentCount() - ((this.mnuBar != null) ? 1 : 0));
        this.blocking();
    }
    
    public void setPopup(final MenuPopup pupMenu) {
        if (this.pupMenu != null) {
            this.remove(this.pupMenu);
        }
        this.pupMenu = pupMenu;
    }
    
    public void removePopup() {
        this.pupMenu = null;
    }
    
    public void addLayer(final Component component, final int n, final int n2) {
        this.add(component, 0);
        if (this.insets == null) {
            component.setLocation(n, n2);
        }
        else {
            component.setLocation(n + this.insets.left, n2 + this.insets.top + ((this.mnuBar == null) ? 0 : this.mnuBar.size().height));
        }
    }
    
    public void removeLayer(final Component component) {
        this.remove(component);
    }
    
    public void addDragListener(final DragListener dragListener) {
        if (this.vecDrag.indexOf(dragListener) < 0) {
            this.vecDrag.addElement(dragListener);
        }
    }
    
    public void removeDragListener(final DragListener dragListener) {
        this.vecDrag.removeElement(dragListener);
    }
    
    public void dragLayer(final Component cmpDrag, final int n, final int n2) {
        this.cmpDrag = cmpDrag;
        final Point location = cmpDrag.getLocation();
        this.nX = location.x + n;
        this.nY = location.y + n2;
        final Enumeration<DragListener> elements = this.vecDrag.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().startDrag(this.cmpDrag, location.x - this.insets.left, location.y - this.insets.top - ((this.mnuBar == null) ? 0 : this.mnuBar.size().height));
        }
    }
    
    public void setInfo(final Component component, final String s) {
        final Informer informer;
        if ((informer = Finder.findInformer(this.cmpMain)) != null) {
            informer.setInfo(component, s);
        }
    }
    
    public void addBoundsListener(final BoundsListener boundsListener) {
        if (this.vecBounds.indexOf(boundsListener) < 0) {
            this.vecBounds.addElement(boundsListener);
        }
    }
    
    public void removeBoundsListener(final BoundsListener boundsListener) {
        this.vecBounds.removeElement(boundsListener);
    }
    
    public void setDirect(final boolean direct) {
        if (this.mnuBar != null) {
            this.mnuBar.setDirect(direct);
        }
    }
    
    public MenuBar getBar() {
        return this.mnuBar;
    }
    
    public Rectangle getArea() {
        if (this.insets == null) {
            return new Rectangle();
        }
        final Dimension size = this.size();
        return new Rectangle(this.insets.left, this.insets.top, size.width - this.insets.left - this.insets.right, size.height - this.insets.top - this.insets.bottom);
    }
    
    public void show() {
        super.show();
        this.insets = super.insets();
        this.blocking();
    }
    
    public void show(final boolean b) {
        if (b) {
            this.show();
        }
        else {
            this.hide();
        }
    }
    
    public void setVisible(final boolean b) {
        this.show(b);
    }
    
    public void setLayout(final LayoutManager layoutManager) {
    }
    
    public void windowOpened(final WindowEvent windowEvent) {
    }
    
    public void windowClosing(final WindowEvent windowEvent) {
        this.postEvent(new Event(windowEvent.getSource(), 201, null));
    }
    
    public void windowClosed(final WindowEvent windowEvent) {
    }
    
    public void windowIconified(final WindowEvent windowEvent) {
        this.postEvent(new Event(windowEvent.getSource(), 203, null));
    }
    
    public void windowDeiconified(final WindowEvent windowEvent) {
        this.postEvent(new Event(windowEvent.getSource(), 204, null));
    }
    
    public void windowActivated(final WindowEvent windowEvent) {
        if (this.mnuBar != null) {
            this.mnuBar.setActive(true);
        }
    }
    
    public void windowDeactivated(final WindowEvent windowEvent) {
        if (this.mnuBar != null) {
            this.mnuBar.setActive(false);
        }
        if (this.pupMenu != null) {
            this.pupMenu.hidePopup(null);
        }
    }
    
    private void execBounds() {
        final Enumeration<BoundsListener> elements = this.vecBounds.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().exetBounds(this);
        }
    }
    
    public void componentResized(final ComponentEvent componentEvent) {
        this.blocking();
        this.execBounds();
    }
    
    public void componentMoved(final ComponentEvent componentEvent) {
        final Point location = this.getLocation();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        if (location.x <= screenSize.width && location.y <= screenSize.height) {
            this.execBounds();
            this.postEvent(new Event(componentEvent.getSource(), System.currentTimeMillis(), 205, location.x, location.y, 0, 0));
        }
    }
    
    public void componentShown(final ComponentEvent componentEvent) {
    }
    
    public void componentHidden(final ComponentEvent componentEvent) {
    }
    
    public boolean handleEvent(final Event event) {
        if (this.cmpMain != null && event.target instanceof MenuItem) {
            return this.cmpMain.handleEvent(event);
        }
        if (this.cmpDrag != null) {
            switch (event.id) {
                case 502:
                case 505: {
                    final Point location = this.cmpDrag.getLocation();
                    final Enumeration<DragListener> elements = this.vecDrag.elements();
                    while (elements.hasMoreElements()) {
                        elements.nextElement().endDrag(this.cmpDrag, location.x - this.insets.left, location.y - this.insets.top - ((this.mnuBar == null) ? 0 : this.mnuBar.size().height));
                    }
                    this.cmpDrag = null;
                    return true;
                }
                case 506: {
                    final Point location2 = this.cmpDrag.getLocation();
                    this.cmpDrag.setLocation(location2.x + event.x - this.nX, location2.y + event.y - this.nY);
                    this.nX = event.x;
                    this.nY = event.y;
                    return true;
                }
            }
        }
        return super.handleEvent(event);
    }
}
