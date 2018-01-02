// 
// Decompiled by Procyon v0.5.30
// 

package jlog.awt.$B8;

import java.util.Enumeration;
import java.awt.Dimension;
import java.awt.Container;
import java.util.Vector;
import java.awt.Component;
import java.awt.LayoutManager;

public class $PIB implements LayoutManager
{
    Component $QIB;
    Vector $RIB;
    int $SIB;
    int $TIB;
    
    public void $UIB(final Container container) {
        final Component[] components = container.getComponents();
        if (components.length == 0 || this.$TIB == 0) {
            return;
        }
        final Vector<Vector<Component>> $rib = new Vector<Vector<Component>>();
        Vector<Component> vector = null;
        int max = 0;
        int n = 0;
        for (int i = 0; i < components.length; ++i) {
            final Component component = components[i];
            final Dimension preferredSize = component.getPreferredSize();
            max = Math.max(max, preferredSize.height);
            if (vector != null && n + preferredSize.width > this.$TIB) {
                vector = null;
            }
            if (vector == null) {
                vector = new Vector<Component>();
                $rib.addElement(vector);
                n = 0;
            }
            vector.addElement(component);
            n += preferredSize.width;
        }
        this.$XIB($rib);
        this.$SIB = max;
        this.$RIB = $rib;
    }
    
    boolean $XIB(final Vector vector) {
        final Component $qib = this.$QIB;
        if ($qib == null) {
            return false;
        }
        for (int i = 0; i < vector.size() - 1; ++i) {
            final Vector vector2 = vector.elementAt(i);
            if (vector2.contains($qib)) {
                vector.removeElementAt(i);
                vector.addElement(vector2);
                return true;
            }
        }
        return false;
    }
    
    public void $YIB(final Component $qib, final Container container) {
        if ($qib == this.$QIB) {
            return;
        }
        this.$QIB = $qib;
        this.$UIB(container);
        container.layout();
    }
    
    public $PIB() {
        this.$QIB = null;
        this.$RIB = null;
        this.$SIB = 0;
        this.$TIB = 0;
    }
    
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    public void layoutContainer(final Container container) {
        final Component[] components = container.getComponents();
        if (components == null || components.length == 0) {
            return;
        }
        this.$TIB = container.getSize().width;
        this.$UIB(container);
        for (int n = 0; this.$RIB != null && n < this.$RIB.size(); ++n) {
            final Vector<Component> vector = this.$RIB.elementAt(n);
            int n2 = 0;
            final Enumeration<Component> elements = vector.elements();
            while (elements.hasMoreElements()) {
                n2 += elements.nextElement().getPreferredSize().width;
            }
            int n3 = 0;
            for (int i = 0; i < vector.size(); ++i) {
                final Component component = vector.elementAt(i);
                final Dimension preferredSize = component.getPreferredSize();
                if (this.$RIB.size() != 1 && this.$TIB > n2 && n != 0) {
                    if (i < vector.size() - 1) {
                        final Dimension dimension = preferredSize;
                        dimension.width += preferredSize.width / n2 * (this.$TIB - n2);
                    }
                    else {
                        preferredSize.width = this.$TIB - n3;
                    }
                }
                component.setBounds(n3, n * this.$SIB, preferredSize.width, this.$SIB);
                n3 += preferredSize.width;
            }
        }
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        return this.preferredLayoutSize(container);
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        Dimension size = null;
        if (container.getParent() != null) {
            size = container.getParent().getSize();
        }
        if (size == null || size.width < 1) {
            this.$TIB = 140;
        }
        else {
            this.$TIB = size.width;
        }
        this.$UIB(container);
        if (this.$RIB == null || this.$RIB.size() == 0) {
            return new Dimension(0, 0);
        }
        return new Dimension(this.$TIB, this.$SIB * this.$RIB.size());
    }
    
    public void removeLayoutComponent(final Component component) {
    }
}
