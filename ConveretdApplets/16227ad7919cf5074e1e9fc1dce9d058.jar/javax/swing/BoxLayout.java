// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing;

import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.AWTError;
import java.io.PrintStream;
import java.awt.Container;
import java.io.Serializable;
import java.awt.LayoutManager2;

public class BoxLayout implements LayoutManager2, Serializable
{
    public static final int X_AXIS = 0;
    public static final int Y_AXIS = 1;
    private int axis;
    private Container target;
    private transient SizeRequirements[] xChildren;
    private transient SizeRequirements[] yChildren;
    private transient SizeRequirements xTotal;
    private transient SizeRequirements yTotal;
    private transient PrintStream dbg;
    
    public BoxLayout(final Container target, final int axis) {
        if (axis != 0 && axis != 1) {
            throw new AWTError("Invalid axis");
        }
        this.axis = axis;
        this.target = target;
    }
    
    BoxLayout(final Container container, final int n, final PrintStream dbg) {
        this(container, n);
        this.dbg = dbg;
    }
    
    public void addLayoutComponent(final Component component, final Object o) {
    }
    
    public void addLayoutComponent(final String s, final Component component) {
    }
    
    void checkContainer(final Container container) {
        if (this.target != container) {
            throw new AWTError("BoxLayout can't be shared");
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
            if (this.axis == 0) {
                this.xTotal = SizeRequirements.getTiledSizeRequirements(this.xChildren);
                this.yTotal = SizeRequirements.getAlignedSizeRequirements(this.yChildren);
            }
            else {
                this.xTotal = SizeRequirements.getAlignedSizeRequirements(this.xChildren);
                this.yTotal = SizeRequirements.getTiledSizeRequirements(this.yChildren);
            }
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
        if (this.axis == 0) {
            SizeRequirements.calculateTiledPositions(size.width, this.xTotal, this.xChildren, array, array2);
            SizeRequirements.calculateAlignedPositions(size.height, this.yTotal, this.yChildren, array3, array4);
        }
        else {
            SizeRequirements.calculateAlignedPositions(size.width, this.xTotal, this.xChildren, array, array2);
            SizeRequirements.calculateTiledPositions(size.height, this.yTotal, this.yChildren, array3, array4);
        }
        for (int i = 0; i < componentCount; ++i) {
            container.getComponent(i).setBounds((int)Math.min(insets.left + array[i], 2147483647L), (int)Math.min(insets.top + array3[i], 2147483647L), array2[i], array4[i]);
        }
        if (this.dbg != null) {
            for (int j = 0; j < componentCount; ++j) {
                this.dbg.println(container.getComponent(j).toString());
                this.dbg.println("X: " + this.xChildren[j]);
                this.dbg.println("Y: " + this.yChildren[j]);
            }
        }
    }
    
    public Dimension maximumLayoutSize(final Container container) {
        this.checkContainer(container);
        this.checkRequests();
        final Dimension dimension = new Dimension(this.xTotal.maximum, this.yTotal.maximum);
        final Insets insets = container.getInsets();
        dimension.width = (int)Math.min(dimension.width + insets.left + insets.right, 2147483647L);
        dimension.height = (int)Math.min(dimension.height + insets.top + insets.bottom, 2147483647L);
        return dimension;
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        this.checkContainer(container);
        this.checkRequests();
        final Dimension dimension = new Dimension(this.xTotal.minimum, this.yTotal.minimum);
        final Insets insets = container.getInsets();
        dimension.width = (int)Math.min(dimension.width + insets.left + insets.right, 2147483647L);
        dimension.height = (int)Math.min(dimension.height + insets.top + insets.bottom, 2147483647L);
        return dimension;
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        this.checkContainer(container);
        this.checkRequests();
        final Dimension dimension = new Dimension(this.xTotal.preferred, this.yTotal.preferred);
        final Insets insets = container.getInsets();
        dimension.width = (int)Math.min(dimension.width + insets.left + insets.right, 2147483647L);
        dimension.height = (int)Math.min(dimension.height + insets.top + insets.bottom, 2147483647L);
        return dimension;
    }
    
    public void removeLayoutComponent(final Component component) {
    }
}
