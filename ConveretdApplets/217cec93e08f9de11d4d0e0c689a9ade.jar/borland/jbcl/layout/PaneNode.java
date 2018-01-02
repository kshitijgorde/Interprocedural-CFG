// 
// Decompiled by Procyon v0.5.30
// 

package borland.jbcl.layout;

import java.awt.Container;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Component;

class PaneNode
{
    Component childComponent;
    String name;
    PaneNode childNodeA;
    PaneNode childNodeB;
    Rectangle location;
    float widthDivide;
    float heightDivide;
    int xOffset;
    int yOffset;
    boolean horizontal;
    boolean reverse;
    
    public PaneNode(final String name, final Component childComponent, final String s) {
        this.location = new Rectangle();
        this.reverse = false;
        this.childComponent = childComponent;
        this.name = name;
        this.widthDivide = 1.0f;
        this.heightDivide = 0.5f;
        this.horizontal = (s.equals("Top") || s.equals("Bottom"));
        this.reverse = (s.equals("Top") || s.equals("Left"));
    }
    
    public PaneNode(final PaneNode childNodeA, final PaneNode childNodeB, final String s, final float n) {
        this.location = new Rectangle();
        this.reverse = false;
        this.childNodeA = childNodeA;
        this.childNodeB = childNodeB;
        if (s.equals("Top") || s.equals("Bottom")) {
            this.widthDivide = 1.0f;
            this.heightDivide = n;
            this.horizontal = true;
        }
        else {
            this.widthDivide = n;
            this.heightDivide = 1.0f;
            this.horizontal = false;
        }
        if (s.equals("Top") || s.equals("Left")) {
            this.reverse = true;
        }
        else {
            this.reverse = false;
        }
    }
    
    void dump() {
    }
    
    public void setConstraints(final Component component, final PaneConstraints paneConstraints) {
        if (this.childComponent != null) {
            return;
        }
        if (paneConstraints.position.equals("Top") || paneConstraints.position.equals("Left")) {
            this.reverse = true;
        }
        else {
            this.reverse = false;
        }
        if (paneConstraints.position.equals("Bottom")) {
            this.heightDivide = 1.0f - paneConstraints.proportion;
            this.widthDivide = 1.0f;
            this.horizontal = true;
        }
        else if (paneConstraints.position.equals("Top")) {
            this.heightDivide = 1.0f - paneConstraints.proportion;
            this.widthDivide = 1.0f;
            this.horizontal = true;
        }
        else if (paneConstraints.position.equals("Right")) {
            this.widthDivide = 1.0f - paneConstraints.proportion;
            this.heightDivide = 1.0f;
            this.horizontal = false;
        }
        else if (paneConstraints.position.equals("Left")) {
            this.widthDivide = 1.0f - paneConstraints.proportion;
            this.heightDivide = 1.0f;
            this.horizontal = false;
        }
    }
    
    public void addChild(final String s, final Component component, String s2, float n) {
        if (this.childComponent != null) {
            this.childNodeA = new PaneNode(this.name, this.childComponent, "Root");
            this.childComponent = null;
            this.name = null;
            this.childNodeB = new PaneNode(s, component, "Root");
            n = 1.0f - n;
            if (n > 1.0f || n < 0.0f) {
                n = 0.5f;
            }
            if (s2 == null) {
                if (this.horizontal) {
                    s2 = "Right";
                }
                else {
                    s2 = "Bottom";
                }
            }
            else if (s2.equals("Right") || s2.equals("Bottom")) {
                this.reverse = false;
            }
            else {
                this.reverse = true;
                if (!s2.equals("Left") && !s2.equals("Top") && !s2.equals("Root")) {
                    n = 0.5f;
                }
            }
            if (s2.equals("Left") || s2.equals("Right")) {
                this.widthDivide = n;
                this.heightDivide = 1.0f;
                this.horizontal = false;
            }
            else {
                this.widthDivide = 1.0f;
                this.heightDivide = n;
                this.horizontal = true;
            }
        }
        else {
            if (this.horizontal) {
                s2 = "Right";
            }
            else {
                s2 = "Bottom";
            }
            this.childNodeB.addChild(s, component, s2, n);
        }
    }
    
    public boolean addChildSplit(final String s, final String s2, final String s3, final Component component, final float n) {
        if (this.childComponent == null) {
            return this.childNodeA.addChildSplit(s, s2, s3, component, n) || this.childNodeB.addChildSplit(s, s2, s3, component, n);
        }
        if (this.name == null) {
            return false;
        }
        if (this.name.equals(s2)) {
            this.addChild(s, component, s3, n);
            return true;
        }
        return false;
    }
    
    public Dimension getPreferredSize(final int n) {
        Dimension preferredSize = null;
        try {
            if (this.childComponent != null) {
                preferredSize = this.childComponent.getPreferredSize();
                return preferredSize;
            }
            final Dimension preferredSize2 = this.childNodeA.getPreferredSize(n);
            final Dimension preferredSize3 = this.childNodeB.getPreferredSize(n);
            final float widthDivide = this.widthDivide;
            if (!this.horizontal) {
                if (widthDivide >= 0.999) {
                    preferredSize = preferredSize2;
                }
                if (widthDivide == 0.0) {
                    preferredSize = preferredSize3;
                }
                else {
                    preferredSize = new Dimension((int)Math.max(preferredSize2.width / widthDivide, preferredSize3.width / (1.0 - widthDivide)), Math.max(preferredSize2.height, preferredSize3.height));
                }
                final Dimension dimension = preferredSize;
                dimension.width += n + n + 1;
            }
            else {
                final float heightDivide = this.heightDivide;
                if (heightDivide >= 0.999) {
                    preferredSize = preferredSize2;
                }
                else if (heightDivide == 0.0) {
                    preferredSize = preferredSize3;
                }
                else {
                    preferredSize = new Dimension(Math.max(preferredSize2.width, preferredSize3.width), (int)Math.max(preferredSize2.height / heightDivide, preferredSize3.height / (1.0 - heightDivide)));
                }
                final Dimension dimension2 = preferredSize;
                dimension2.height += n + n + 1;
            }
        }
        catch (Exception ex) {}
        return preferredSize;
    }
    
    public String getNodeAComponent() {
        if (this.childComponent != null) {
            return this.name;
        }
        return this.childNodeA.getNodeAComponent();
    }
    
    public PaneNode getImmediateParent(final Component component) {
        if (this.childComponent != null) {
            if (this.childComponent == component) {
                return this;
            }
            return null;
        }
        else {
            if (this.childNodeA.childComponent == component) {
                return this;
            }
            if (this.childNodeB.childComponent == component) {
                return this;
            }
            final PaneNode immediateParent = this.childNodeA.getImmediateParent(component);
            if (immediateParent != null) {
                return immediateParent;
            }
            return this.childNodeB.getImmediateParent(component);
        }
    }
    
    void getComponents(final Point point, final String[] array, final boolean b) {
        if (this.childComponent == null) {
            if (b) {
                array[point.x] = this.childNodeA.getNodeAComponent();
                ++point.x;
            }
            array[point.x] = this.childNodeB.getNodeAComponent();
            ++point.x;
            this.childNodeA.getComponents(point, array, false);
            this.childNodeB.getComponents(point, array, false);
        }
    }
    
    public PaneNode getParentNode(final Component component, final PaneNode paneNode) {
        if (this.childComponent != null) {
            if (this.childComponent == component) {
                return this;
            }
            return null;
        }
        else if (this.childNodeA.childComponent == component) {
            if (paneNode != null) {
                return paneNode;
            }
            return this.childNodeA;
        }
        else {
            if (this.childNodeB.childComponent == component) {
                return this;
            }
            final PaneNode parentNode = this.childNodeA.getParentNode(component, paneNode);
            if (parentNode != null) {
                return parentNode;
            }
            return this.childNodeB.getParentNode(component, this);
        }
    }
    
    public PaneNode removeChild(final Component component) {
        PaneNode paneNode = null;
        if (this.childNodeA.childComponent == component) {
            paneNode = this.childNodeA;
            paneNode.widthDivide = this.widthDivide;
            paneNode.heightDivide = this.heightDivide;
            paneNode.reverse = this.reverse;
            paneNode.horizontal = this.horizontal;
            this.absorbChildNode(this.childNodeB);
            paneNode.childNodeA = this;
            paneNode.childNodeB = null;
        }
        else if (this.childNodeB.childComponent == component) {
            paneNode = this.childNodeB;
            paneNode.widthDivide = this.widthDivide;
            paneNode.heightDivide = this.heightDivide;
            paneNode.reverse = this.reverse;
            paneNode.horizontal = this.horizontal;
            this.absorbChildNode(this.childNodeA);
            paneNode.childNodeA = null;
            paneNode.childNodeB = this;
        }
        return paneNode;
    }
    
    Rectangle getDividerRect(final int n) {
        if (this.childComponent != null) {
            return new Rectangle(this.location.x - n, this.yOffset + this.location.y - n, this.location.width + n, n);
        }
        if (this.heightDivide == 1.0f) {
            if (this.reverse) {
                return new Rectangle(this.childNodeB.location.x + this.childNodeB.location.width, this.childNodeB.location.y, n, this.childNodeB.location.height);
            }
            return new Rectangle(this.childNodeA.location.x + this.childNodeA.location.width, this.childNodeA.location.y, n, this.childNodeA.location.height);
        }
        else {
            if (this.reverse) {
                return new Rectangle(this.childNodeB.location.x, this.childNodeB.location.y + this.childNodeB.location.height, this.childNodeB.location.width, n);
            }
            return new Rectangle(this.childNodeA.location.x, this.childNodeA.location.y + this.childNodeA.location.height, this.childNodeA.location.width, n);
        }
    }
    
    void drag(final int n, final int n2) {
        if (this.childComponent == null) {
            if (this.heightDivide == 1.0f) {
                this.widthDivide = (n - this.location.x) / this.location.width;
                if (this.reverse) {
                    this.widthDivide = 1.0f - this.widthDivide;
                }
                if (this.widthDivide < 0.0f) {
                    this.widthDivide = 0.0f;
                }
                else if (this.widthDivide >= 1.0f) {
                    this.widthDivide = 0.999f;
                }
            }
            else {
                if (this.reverse) {
                    this.heightDivide = 1.0f - this.heightDivide;
                }
                this.heightDivide = (n2 - this.location.y) / this.location.height;
                if (this.reverse) {
                    this.heightDivide = 1.0f - this.heightDivide;
                }
                if (this.heightDivide < 0.0f) {
                    this.heightDivide = 0.0f;
                }
                else if (this.heightDivide >= 1.0f) {
                    this.heightDivide = 0.999f;
                }
            }
        }
    }
    
    PaneNode hitTest(final int n, final int n2, final int n3) {
        if (this.location == null || this.childComponent != null) {
            return null;
        }
        int n4;
        if (this.heightDivide == 1.0f) {
            n4 = n - (this.location.x + this.xOffset);
        }
        else {
            n4 = n2 - (this.location.y + this.yOffset);
        }
        if (Math.abs(n4) <= n3) {
            return this;
        }
        if (n4 < 0 ^ this.reverse) {
            return this.childNodeA.hitTest(n, n2, n3);
        }
        return this.childNodeB.hitTest(n, n2, n3);
    }
    
    void assertLocation(final Rectangle rectangle, final int n) {
        this.location.x = rectangle.x;
        this.location.y = rectangle.y;
        this.location.width = rectangle.width;
        this.location.height = rectangle.height;
        if (this.childComponent != null) {
            if (!this.childComponent.getBounds().equals(this.location)) {
                this.childComponent.setBounds(this.location.x, this.location.y, this.location.width, this.location.height);
                if (this.childComponent instanceof Container) {
                    ((Container)this.childComponent).doLayout();
                }
            }
        }
        else {
            final Rectangle rectangle2 = new Rectangle();
            final Rectangle rectangle3 = new Rectangle();
            this.calculateLocations(this.location, rectangle2, rectangle3, n);
            this.childNodeA.assertLocation(rectangle2, n);
            this.childNodeB.assertLocation(rectangle3, n);
        }
    }
    
    void absorbChildNode(final PaneNode paneNode) {
        if (paneNode.childComponent != null) {
            this.childComponent = paneNode.childComponent;
            this.name = paneNode.name;
        }
        else {
            this.childComponent = null;
            this.childNodeA = paneNode.childNodeA;
            this.childNodeB = paneNode.childNodeB;
            this.widthDivide = paneNode.widthDivide;
            this.heightDivide = paneNode.heightDivide;
            this.reverse = paneNode.reverse;
            this.horizontal = paneNode.horizontal;
        }
    }
    
    void calculateLocations(final Rectangle rectangle, final Rectangle rectangle2, final Rectangle rectangle3, int n) {
        n += n;
        if (this.heightDivide == 1.0f) {
            float widthDivide = this.widthDivide;
            if (this.reverse) {
                widthDivide = 1.0f - widthDivide;
            }
            this.xOffset = (int)((rectangle.width - n) * widthDivide);
            rectangle2.x = rectangle.x;
            rectangle2.y = rectangle.y;
            rectangle2.width = this.xOffset;
            rectangle2.height = rectangle.height;
            rectangle3.x = rectangle.x + this.xOffset + n;
            rectangle3.y = rectangle.y;
            rectangle3.width = rectangle.width - this.xOffset - n;
            rectangle3.height = rectangle.height;
        }
        else {
            float heightDivide = this.heightDivide;
            if (this.reverse) {
                heightDivide = 1.0f - heightDivide;
            }
            this.yOffset = (int)((rectangle.height - n) * heightDivide);
            rectangle2.x = rectangle.x;
            rectangle2.y = rectangle.y;
            rectangle2.width = rectangle.width;
            rectangle2.height = this.yOffset;
            rectangle3.x = rectangle.x;
            rectangle3.y = rectangle.y + this.yOffset + n;
            rectangle3.width = rectangle.width;
            rectangle3.height = rectangle.height - this.yOffset - n;
        }
        if (this.reverse) {
            final Rectangle rectangle4 = new Rectangle(rectangle2.x, rectangle2.y, rectangle2.width, rectangle2.height);
            rectangle2.x = rectangle3.x;
            rectangle2.y = rectangle3.y;
            rectangle2.width = rectangle3.width;
            rectangle2.height = rectangle3.height;
            rectangle3.x = rectangle4.x;
            rectangle3.y = rectangle4.y;
            rectangle3.width = rectangle4.width;
            rectangle3.height = rectangle4.height;
        }
    }
}
