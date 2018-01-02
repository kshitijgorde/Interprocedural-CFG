// 
// Decompiled by Procyon v0.5.30
// 

package netcharts.gui;

import java.util.Enumeration;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Container;
import java.util.Vector;
import java.awt.Component;
import java.awt.LayoutManager;

public class NFSashLayout implements LayoutManager
{
    public static final int HORIZONTAL = 2;
    public static final int VERTICAL = 1;
    protected int style;
    protected Component last;
    protected Vector sashs;
    protected Vector children;
    protected int _child_count;
    protected boolean _needs_scaling;
    protected int _min_width;
    protected int _min_height;
    protected int _preferred_width;
    protected int _preferred_height;
    protected int _max_component_width;
    protected int _max_component_height;
    
    public NFSashLayout() {
        this(2);
    }
    
    public NFSashLayout(final int style) {
        this.style = 2;
        this.last = null;
        this._needs_scaling = true;
        this._min_width = 0;
        this._min_height = 0;
        this._preferred_width = 0;
        this._preferred_height = 0;
        this._max_component_width = 0;
        this._max_component_height = 0;
        this.style = style;
        this.sashs = new Vector(4, 2);
    }
    
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    public void removeLayoutComponent(final Component component) {
    }
    
    public void setSizes(final Container container) {
        final int countComponents = container.countComponents();
        if (countComponents == 0 || countComponents == this._child_count) {
            return;
        }
        this._needs_scaling = true;
        this._child_count = 0;
        this.sashs.removeAllElements();
        this._preferred_width = 0;
        this._preferred_height = 0;
        this._min_width = 0;
        this._min_height = 0;
        this._max_component_width = 0;
        this._max_component_height = 0;
        for (int i = 0; i < countComponents; ++i) {
            final Component component = container.getComponent(i);
            if (component.isVisible()) {
                if (component instanceof NFSashIntf) {
                    this.sashs.addElement(component);
                }
                final Dimension preferredSize = component.preferredSize();
                this._max_component_width = Math.max(this._max_component_width, preferredSize.width);
                this._max_component_height = Math.max(this._max_component_height, preferredSize.height);
                if (this.style == 2) {
                    this._preferred_width += preferredSize.width;
                }
                else {
                    this._preferred_height += preferredSize.height;
                }
            }
        }
        if (this.style == 2) {
            this._preferred_height = this._max_component_height;
        }
        else {
            this._preferred_width = this._max_component_width;
        }
        this._min_height = this._preferred_height;
        this._min_width = this._preferred_width;
    }
    
    public Dimension preferredLayoutSize(final Container sizes) {
        this.setSizes(sizes);
        final Insets insets = sizes.insets();
        return new Dimension(this._preferred_width + insets.left + insets.right, this._preferred_height + insets.top + insets.bottom);
    }
    
    public Dimension minimumLayoutSize(final Container sizes) {
        this.setSizes(sizes);
        final Insets insets = sizes.insets();
        return new Dimension(this._min_width + insets.left + insets.right, this._min_height + insets.top + insets.bottom);
    }
    
    public void layoutContainer(final Container sizes) {
        final Insets insets = sizes.insets();
        final int n = sizes.size().width - insets.left - insets.right;
        final int n2 = sizes.size().height - insets.top - insets.bottom;
        this.setSizes(sizes);
        final Dimension size = sizes.size();
        final Dimension allComponentsSize = ((NFSashPanelIntf)sizes).allComponentsSize();
        final double n3 = size.width / allComponentsSize.width;
        final double n4 = size.height / allComponentsSize.height;
        if (this.style == 2) {
            this._max_component_height = size.height;
        }
        else {
            this._max_component_width = size.width;
        }
        final Enumeration elements = this.sashs.elements();
        int n5 = 0;
        int n6 = 0;
        Component component = null;
        final int countManagedComponents = ((NFSashPanelIntf)sizes).countManagedComponents();
        if (countManagedComponents == 0) {
            return;
        }
        for (int i = 0; i < countManagedComponents; ++i) {
            final Component managedComponent = ((NFSashPanelIntf)sizes).getManagedComponent(i);
            if (managedComponent.isVisible()) {
                final Dimension size2 = managedComponent.size();
                final Dimension preferredSize = managedComponent.preferredSize();
                if (this.style == 2) {
                    if (size2.width <= 0 || size2.height <= 0) {
                        size2.width = preferredSize.width;
                    }
                    if (this._needs_scaling) {
                        size2.width *= (int)n3;
                    }
                    if (component != null && elements.hasMoreElements()) {
                        final NFSashIntf nfSashIntf = elements.nextElement();
                        nfSashIntf.left(component);
                        nfSashIntf.right(managedComponent);
                        final Dimension size3 = nfSashIntf.size();
                        nfSashIntf.reshape(insets.left + n5, insets.top, size3.width, this._max_component_height);
                        n5 += size3.width;
                    }
                    if (i == countManagedComponents - 1) {
                        size2.width = size.width - n5;
                    }
                    managedComponent.reshape(insets.left + n5, insets.top, size2.width, this._max_component_height);
                    if (managedComponent instanceof Container) {
                        ((Container)managedComponent).layout();
                    }
                    n5 += size2.width;
                    component = managedComponent;
                }
                else {
                    if (size2.width <= 0 || size2.height <= 0) {
                        size2.height = preferredSize.height;
                    }
                    if (this._needs_scaling) {
                        size2.height *= (int)n4;
                    }
                    if (component != null && elements.hasMoreElements()) {
                        final NFSashIntf nfSashIntf2 = elements.nextElement();
                        nfSashIntf2.left(component);
                        nfSashIntf2.right(managedComponent);
                        final Dimension size4 = nfSashIntf2.size();
                        nfSashIntf2.reshape(insets.left, insets.top + n6, this._max_component_width, size4.height);
                        n6 += size4.height;
                    }
                    if (i == countManagedComponents - 1) {
                        size2.height = size.height - n6;
                    }
                    managedComponent.reshape(insets.left, insets.top + n6, this._max_component_width, size2.height);
                    if (managedComponent instanceof Container) {
                        ((Container)managedComponent).layout();
                    }
                    n6 += size2.height;
                    component = managedComponent;
                }
            }
        }
        while (elements.hasMoreElements()) {
            final Component component2 = (Component)elements.nextElement();
            sizes.remove(component2);
            this.sashs.removeElement(component2);
        }
        this._needs_scaling = false;
    }
}
