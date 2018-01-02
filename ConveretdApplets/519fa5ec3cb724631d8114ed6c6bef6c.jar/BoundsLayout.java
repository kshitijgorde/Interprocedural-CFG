import java.awt.Container;
import java.awt.Component;
import java.util.Hashtable;
import java.awt.Dimension;
import java.awt.LayoutManager2;

// 
// Decompiled by Procyon v0.5.30
// 

public class BoundsLayout implements LayoutManager2
{
    private Dimension minimumSize;
    private Dimension preferredSize;
    private Hashtable constraintsTable;
    private BoundsConstraints defaultConstraints;
    
    public BoundsLayout(final int minimumWidth, final int minimumHeight) {
        this(new Dimension(minimumWidth, minimumHeight));
    }
    
    public BoundsLayout(final int minimumWidth, final int minimumHeight, final int preferredWidth, final int preferredHeight) {
        this(new Dimension(minimumWidth, minimumHeight), new Dimension(preferredWidth, preferredHeight));
    }
    
    public BoundsLayout(final Dimension minimumSize) {
        this(minimumSize, minimumSize);
    }
    
    public BoundsLayout(final Dimension minimumSize, final Dimension preferredSize) {
        this.minimumSize = minimumSize;
        this.preferredSize = preferredSize;
        this.constraintsTable = new Hashtable();
        this.defaultConstraints = new BoundsConstraints();
    }
    
    public void setConstraints(final Component component, final BoundsConstraints constraints) {
        this.constraintsTable.put(component, constraints.clone());
    }
    
    public BoundsConstraints getConstraints(final Component component) {
        return (BoundsConstraints)this.lookUpConstraints(component).clone();
    }
    
    protected BoundsConstraints lookUpConstraints(final Component component) {
        BoundsConstraints constraints = this.constraintsTable.get(component);
        if (constraints == null) {
            this.setConstraints(component, this.defaultConstraints);
            constraints = this.defaultConstraints;
        }
        return constraints;
    }
    
    private void removeConstraints(final Component component) {
        this.constraintsTable.remove(component);
    }
    
    public void addLayoutComponent(final String str, final Component component) {
    }
    
    public void addLayoutComponent(final Component component, final Object constraints) {
        if (constraints instanceof BoundsConstraints) {
            this.setConstraints(component, (BoundsConstraints)constraints);
        }
        else if (constraints != null) {
            throw new IllegalArgumentException("Cannot add to layout: constraints must be a BoundsConstraint");
        }
    }
    
    public void removeLayoutComponent(final Component component) {
        this.removeConstraints(component);
    }
    
    public Dimension minimumLayoutSize(final Container parent) {
        return this.minimumSize;
    }
    
    public Dimension preferredLayoutSize(final Container parent) {
        return this.preferredSize;
    }
    
    public Dimension maximumLayoutSize(final Container parent) {
        return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
    
    public float getLayoutAlignmentX(final Container parent) {
        return 0.5f;
    }
    
    public float getLayoutAlignmentY(final Container parent) {
        return 0.5f;
    }
    
    public void invalidateLayout(final Container parent) {
    }
    
    public void layoutContainer(final Container parent) {
        synchronized (parent.getTreeLock()) {
            final Component[] components = parent.getComponents();
            final Dimension parentSize = parent.getSize();
            int extraWidth = parentSize.width - this.minimumSize.width;
            int extraHeight = parentSize.height - this.minimumSize.height;
            if (extraWidth < 0) {
                extraWidth = 0;
            }
            if (extraHeight < 0) {
                extraHeight = 0;
            }
            for (int i = 0; i < components.length; ++i) {
                final BoundsConstraints constraints = this.lookUpConstraints(components[i]);
                components[i].setBounds(constraints.getBounds(extraWidth, extraHeight));
            }
        }
    }
}
