// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.layout;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Component;
import java.awt.LayoutManager2;

public class PaneLayout implements LayoutManager2
{
    PaneNode rootNode;
    PaneNode lastSelected;
    private PaneNode lastDeletion;
    int gap;
    private String lastComponentAdded;
    private int addCount;
    
    public PaneLayout() {
        this.gap = 0;
        this.addCount = 0;
    }
    
    public void setGap(final int gap) {
        this.gap = gap;
    }
    
    public int getGap() {
        return this.gap;
    }
    
    public void setConstraints(final Component component, final PaneConstraints paneConstraints) {
        this.setComponentConstraints(component, paneConstraints);
        this.lastDeletion = null;
    }
    
    private boolean setComponentConstraints(final Component component, final PaneConstraints paneConstraints) {
        if (this.rootNode == null) {
            this.rootNode = new PaneNode(paneConstraints.name, component, "Root");
            return true;
        }
        final PaneNode parentNode = this.rootNode.getParentNode(component, null);
        if (parentNode != null) {
            parentNode.setConstraints(component, paneConstraints);
            return true;
        }
        return false;
    }
    
    public PaneConstraints getConstraints(final Component component) {
        PaneConstraints paneConstraints = null;
        if (this.rootNode != null) {
            final PaneNode parentNode = this.rootNode.getParentNode(component, null);
            if (parentNode != null) {
                if (parentNode.childComponent == component) {
                    paneConstraints = new PaneConstraints(parentNode.name, parentNode.name, "Root", 0.5f);
                }
                else {
                    final float n = parentNode.heightDivide * parentNode.widthDivide;
                    final String nodeAComponent = parentNode.childNodeA.getNodeAComponent();
                    String s = parentNode.childNodeB.getNodeAComponent();
                    if (parentNode.childNodeA.childComponent == component) {
                        s = parentNode.childNodeB.name;
                    }
                    if (parentNode.horizontal) {
                        if (parentNode.reverse) {
                            paneConstraints = new PaneConstraints(s, nodeAComponent, "Top", 1.0f - n);
                        }
                        else {
                            paneConstraints = new PaneConstraints(s, nodeAComponent, "Bottom", 1.0f - n);
                        }
                    }
                    else if (parentNode.reverse) {
                        paneConstraints = new PaneConstraints(s, nodeAComponent, "Left", 1.0f - n);
                    }
                    else {
                        paneConstraints = new PaneConstraints(s, nodeAComponent, "Right", 1.0f - n);
                    }
                    if (s.equals(nodeAComponent)) {
                        paneConstraints.position = "Root";
                    }
                }
            }
        }
        return paneConstraints;
    }
    
    public String toString() {
        return "PaneLayout";
    }
    
    public void removeLayoutComponent(final Component component) {
        if (component == null) {
            this.lastDeletion = null;
            return;
        }
        if (this.rootNode != null) {
            if (this.rootNode.childComponent == component) {
                this.rootNode = null;
            }
            else {
                final PaneNode immediateParent = this.rootNode.getImmediateParent(component);
                if (immediateParent != null) {
                    this.lastDeletion = immediateParent.removeChild(component);
                }
            }
        }
    }
    
    public Dimension preferredLayoutSize(final Container container) {
        final Insets insets = container.getInsets();
        if (this.rootNode != null) {
            final Dimension preferredSize;
            final Dimension dimension = preferredSize = this.rootNode.getPreferredSize(this.gap);
            preferredSize.width += insets.right + insets.left;
            final Dimension dimension2 = dimension;
            dimension2.height += insets.top + insets.bottom;
            return dimension;
        }
        return new Dimension(10, 10);
    }
    
    public Dimension minimumLayoutSize(final Container container) {
        return this.preferredLayoutSize(container);
    }
    
    public void layoutContainer(final Container container) {
        if (this.rootNode != null) {
            final Dimension size = container.getSize();
            final Insets insets = container.getInsets();
            this.rootNode.assertLocation(new Rectangle(insets.left, insets.top, size.width - insets.left - insets.right, size.height - insets.top - insets.bottom), this.gap);
        }
    }
    
    public String[] getAddOrder(final Container container) {
        final String[] array = new String[container.getComponents().length];
        final Point point = new Point(0, 0);
        if (this.rootNode != null) {
            if (this.rootNode.childComponent == null) {
                this.rootNode.getComponents(point, array, true);
            }
            else {
                array[0] = this.rootNode.name;
            }
        }
        return array;
    }
    
    void addChild(final Component component, final float n) {
        ++this.addCount;
        final String concat = String.valueOf("component").concat(String.valueOf(this.addCount));
        if (this.rootNode == null) {
            this.rootNode = new PaneNode(concat, component, "Top");
        }
        else {
            this.rootNode.addChild(concat, component, "Bottom", n);
        }
        this.lastComponentAdded = concat;
    }
    
    public void dragDivider(final int n, final int n2) {
        if (this.lastSelected != null) {
            this.lastSelected.drag(n, n2);
        }
    }
    
    public Rectangle getDividerBounds() {
        if (this.lastSelected != null) {
            return this.lastSelected.location;
        }
        return null;
    }
    
    public Rectangle getDividerRect(final int n, final int n2) {
        if (this.rootNode != null) {
            this.lastSelected = this.rootNode.hitTest(n, n2, this.gap * 2);
        }
        if (this.lastSelected != null) {
            return this.lastSelected.getDividerRect(this.gap * 2);
        }
        return null;
    }
    
    void addChild(final String s, final String s2, final String s3, final Component component, final float n) {
        if (this.rootNode == null) {
            this.rootNode = new PaneNode(s, component, "Top");
        }
        else {
            boolean b = true;
            if (s2 == null || s2.length() == 0) {
                if (s3 == null) {
                    this.rootNode.addChild(s, component, null, n);
                }
                else {
                    b = this.rootNode.addChildSplit(s, this.lastComponentAdded, s3, component, n);
                }
            }
            else if (!this.rootNode.addChildSplit(s, s2, s3, component, n)) {
                b = this.rootNode.addChildSplit(s, this.lastComponentAdded, s3, component, n);
            }
            if (!b) {
                this.rootNode.addChild(s, component, s3, n);
            }
        }
    }
    
    public void addLayoutComponent(final String s, final Component component) {
        this.addLayoutComponent(component, new PaneConstraints(s, "", "", 0.5f));
    }
    
    public void addLayoutComponent(final Component component, final Object o) {
        if (o instanceof PaneConstraints) {
            if (!this.justDeleted(component, (PaneConstraints)o)) {
                if (((PaneConstraints)o).name == null) {
                    ++this.addCount;
                    String.valueOf("component").concat(String.valueOf(this.addCount));
                }
                if (this.setComponentConstraints(component, (PaneConstraints)o)) {
                    this.lastDeletion = null;
                    return;
                }
                final PaneConstraints paneConstraints = (PaneConstraints)o;
                this.addChild(paneConstraints.name, paneConstraints.splitComponentName, paneConstraints.position, component, paneConstraints.proportion);
                this.lastComponentAdded = paneConstraints.name;
            }
        }
        else {
            this.addChild(component, 0.5f);
        }
        this.lastDeletion = null;
    }
    
    public Dimension maximumLayoutSize(final Container container) {
        return new Dimension(500, 500);
    }
    
    public float getLayoutAlignmentX(final Container container) {
        return 0.5f;
    }
    
    public float getLayoutAlignmentY(final Container container) {
        return 0.5f;
    }
    
    public void invalidateLayout(final Container container) {
    }
    
    boolean justDeleted(final Component component, final PaneConstraints paneConstraints) {
        if (this.lastDeletion == null) {
            return false;
        }
        if (this.lastDeletion.childComponent == component) {
            if (this.lastDeletion.childNodeA != null) {
                final PaneNode childNodeA = this.lastDeletion.childNodeA;
                final PaneNode childNodeB = new PaneNode(childNodeA.childNodeA, childNodeA.childNodeB, "", 0.5f);
                childNodeB.widthDivide = childNodeA.widthDivide;
                childNodeB.heightDivide = childNodeA.heightDivide;
                childNodeB.name = childNodeA.name;
                this.lastDeletion.name = paneConstraints.name;
                childNodeB.childComponent = childNodeA.childComponent;
                childNodeB.reverse = childNodeA.reverse;
                childNodeB.horizontal = childNodeA.horizontal;
                childNodeA.childNodeA = this.lastDeletion;
                childNodeA.childNodeB = childNodeB;
                childNodeA.name = null;
                childNodeA.childComponent = null;
                childNodeA.widthDivide = this.lastDeletion.widthDivide;
                childNodeA.heightDivide = this.lastDeletion.heightDivide;
                childNodeA.reverse = this.lastDeletion.reverse;
                childNodeA.horizontal = this.lastDeletion.horizontal;
                this.lastDeletion = null;
                this.setConstraints(component, paneConstraints);
                return true;
            }
            final PaneNode childNodeB2 = this.lastDeletion.childNodeB;
            if (childNodeB2 == null) {
                return false;
            }
            if (paneConstraints.splitComponentName.equals(childNodeB2.getNodeAComponent()) || !this.lastDeletion.name.equals(paneConstraints.name)) {
                final PaneNode childNodeA2 = new PaneNode(childNodeB2.childNodeA, childNodeB2.childNodeB, "", 0.5f);
                childNodeA2.widthDivide = childNodeB2.widthDivide;
                childNodeA2.heightDivide = childNodeB2.heightDivide;
                childNodeA2.name = childNodeB2.name;
                this.lastDeletion.name = paneConstraints.name;
                childNodeA2.childComponent = childNodeB2.childComponent;
                childNodeA2.reverse = childNodeB2.reverse;
                childNodeA2.horizontal = childNodeB2.horizontal;
                childNodeB2.childNodeA = childNodeA2;
                childNodeB2.childNodeB = this.lastDeletion;
                childNodeB2.name = null;
                childNodeB2.childComponent = null;
                childNodeB2.widthDivide = this.lastDeletion.widthDivide;
                childNodeB2.heightDivide = this.lastDeletion.heightDivide;
                childNodeB2.reverse = this.lastDeletion.reverse;
                childNodeB2.horizontal = this.lastDeletion.horizontal;
                this.lastDeletion = null;
                this.setConstraints(component, paneConstraints);
                return true;
            }
            this.lastDeletion = null;
        }
        return false;
    }
}
