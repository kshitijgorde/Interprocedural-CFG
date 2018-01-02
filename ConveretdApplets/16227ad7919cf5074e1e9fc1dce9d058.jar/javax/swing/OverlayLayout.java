// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.AWTError;
import java.awt.Component;
import java.awt.Container;
import java.io.Serializable;
import java.awt.LayoutManager2;

public class OverlayLayout implements LayoutManager2, Serializable
{
    private Container target;
    private SizeRequirements[] xChildren;
    private SizeRequirements[] yChildren;
    private SizeRequirements xTotal;
    private SizeRequirements yTotal;
    
    public OverlayLayout(final Container target) {
        this.target = target;
    }
    
    public void addLayoutComponent(final Component component, final Object o) {
    }
    
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    void checkContainer(final Container container) {
        if (this.target != container) {
            throw new AWTError("OverlayLayout can't be shared");
        }
    }
    
    void checkRequests() {
        if (this.xChildren == null || this.yChildren == null) {
            final int componentCount = this.target.getComponentCount();
            this.xChildren = new SizeRequirements[componentCount];
            this.yChildren = new SizeRequirements[componentCount];
            for (int i = 0; i < componentCount; ++i) {
                final Component component = this.target.getComponent(i);
                final Dimension minimumSize = component.getMinimumSize();
                final Dimension preferredSize = component.getPreferredSize();
                final Dimension maximumSize = component.getMaximumSize();
                this.xChildren[i] = new SizeRequirements(minimumSize.width, preferredSize.width, maximumSize.width, component.getAlignmentX());
                this.yChildren[i] = new SizeRequirements(minimumSize.height, preferredSize.height, maximumSize.height, component.getAlignmentY());
            }
            this.xTotal = SizeRequirements.getAlignedSizeRequirements(this.xChildren);
            this.yTotal = SizeRequirements.getAlignedSizeRequirements(this.yChildren);
        }
    }
    
    public float getLayoutAlignmentX(final Container container) {
        this.checkContainer(container);
        this.checkRequests();
        return this.xTotal.alignment;
    }
    
    public float getLayoutAlignmentY(final Container container) {
        this.checkContainer(container);
        this.checkRequests();
        return this.yTotal.alignment;
    }
    
    public void invalidateLayout(final Container container) {
        this.checkContainer(container);
        this.xChildren = null;
        this.yChildren = null;
        this.xTotal = null;
        this.yTotal = null;
    }
    
    public void layoutContainer(final Container container) {
        this.checkContainer(container);
        this.checkRequests();
        final int componentCount = container.getComponentCount();
        final int[] array = new int[componentCount];
        final int[] array2 = new int[componentCount];
        final int[] array3 = new int[componentCount];
        final int[] array4 = new int[componentCount];
        final Dimension size = container.getSize();
        final Insets insets = container.getInsets();
        final Dimension dimension = size;
        dimension.width -= insets.left + insets.right;
        final Dimension dimension2 = size;
        dimension2.height -= insets.top + insets.bottom;
        SizeRequirements.calculateAlignedPositions(size.width, this.xTotal, this.xChildren, array, array2);
        SizeRequirements.calculateAlignedPositions(size.height, this.yTotal, this.yChildren, array3, array4);
        for (int i = 0; i < componentCount; ++i) {
            container.getComponent(i).setBounds(insets.left + array[i], insets.top + array3[i], array2[i], array4[i]);
        }
    }
    
    public Dimension maximumLayoutSize(final Container container) {
        this.checkContainer(container);
        this.checkRequests();
        final Dimension dimension = new Dimension(this.xTotal.maximum, this.yTotal.maximum);
        final Insets insets = container.getInsets();
        final Dimension dimension2 = dimension;
        dimension2.width += insets.left + insets.right;
        final Dimension dimension3 = dimension;
        dimension3.height += insets.top + insets.bottom;
        return dimension;
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        this.checkContainer(container);
        this.checkRequests();
        final Dimension dimension = new Dimension(this.xTotal.minimum, this.yTotal.minimum);
        final Insets insets = container.getInsets();
        final Dimension dimension2 = dimension;
        dimension2.width += insets.left + insets.right;
        final Dimension dimension3 = dimension;
        dimension3.height += insets.top + insets.bottom;
        return dimension;
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        this.checkContainer(container);
        this.checkRequests();
        final Dimension dimension = new Dimension(this.xTotal.preferred, this.yTotal.preferred);
        final Insets insets = container.getInsets();
        final Dimension dimension2 = dimension;
        dimension2.width += insets.left + insets.right;
        final Dimension dimension3 = dimension;
        dimension3.height += insets.top + insets.bottom;
        return dimension;
    }
    
    public void removeLayoutComponent(final Component component) {
    }
}
