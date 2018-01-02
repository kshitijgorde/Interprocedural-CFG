// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.layout.graph;

import prefuse.data.tuple.TupleSet;
import prefuse.util.ArrayLib;
import java.util.Iterator;
import prefuse.data.Node;
import java.awt.geom.Rectangle2D;
import prefuse.visual.VisualItem;
import prefuse.data.Graph;
import prefuse.data.Schema;
import prefuse.visual.NodeItem;
import java.awt.geom.Point2D;

public class RadialTreeLayout extends TreeLayout
{
    public static final int DEFAULT_RADIUS = 50;
    private static final int MARGIN = 30;
    protected int m_maxDepth;
    protected double m_radiusInc;
    protected double m_theta1;
    protected double m_theta2;
    protected boolean m_setTheta;
    protected boolean m_autoScale;
    protected Point2D m_origin;
    protected NodeItem m_prevRoot;
    public static final String PARAMS = "_radialTreeLayoutParams";
    public static final Schema PARAMS_SCHEMA;
    
    public RadialTreeLayout(final String s) {
        super(s);
        this.m_maxDepth = 0;
        this.m_setTheta = false;
        this.m_autoScale = true;
        this.m_radiusInc = 50.0;
        this.m_prevRoot = null;
        this.m_theta1 = 0.0;
        this.m_theta2 = this.m_theta1 + 6.283185307179586;
    }
    
    public RadialTreeLayout(final String s, final int n) {
        this(s);
        this.m_radiusInc = n;
        this.m_autoScale = false;
    }
    
    public double getRadiusIncrement() {
        return this.m_radiusInc;
    }
    
    public void setRadiusIncrement(final double radiusInc) {
        this.m_radiusInc = radiusInc;
    }
    
    public boolean getAutoScale() {
        return this.m_autoScale;
    }
    
    public void setAutoScale(final boolean autoScale) {
        this.m_autoScale = autoScale;
    }
    
    public void setAngularBounds(final double theta1, final double n) {
        this.m_theta1 = theta1;
        this.m_theta2 = theta1 + n;
        this.m_setTheta = true;
    }
    
    public void run(final double n) {
        this.initSchema(((Graph)this.m_vis.getGroup(this.m_group)).getNodes());
        this.m_origin = this.getLayoutAnchor();
        final NodeItem layoutRoot = this.getLayoutRoot();
        final Params params = (Params)layoutRoot.get("_radialTreeLayoutParams");
        this.calcAngularWidth(layoutRoot, this.m_maxDepth = 0);
        if (this.m_autoScale) {
            this.setScale(this.getLayoutBounds());
        }
        if (!this.m_setTheta) {
            this.calcAngularBounds(layoutRoot);
        }
        if (this.m_maxDepth > 0) {
            this.layout(layoutRoot, this.m_radiusInc, this.m_theta1, this.m_theta2);
        }
        this.setX(layoutRoot, null, this.m_origin.getX());
        this.setY(layoutRoot, null, this.m_origin.getY());
        params.angle = this.m_theta2 - this.m_theta1;
    }
    
    protected void setScale(final Rectangle2D rectangle2D) {
        final double n = Math.min(rectangle2D.getWidth(), rectangle2D.getHeight()) / 2.0;
        if (this.m_maxDepth > 0) {
            this.m_radiusInc = (n - 30.0) / this.m_maxDepth;
        }
    }
    
    private void calcAngularBounds(final NodeItem prevRoot) {
        if (this.m_prevRoot == null || !this.m_prevRoot.isValid() || prevRoot == this.m_prevRoot) {
            this.m_prevRoot = prevRoot;
            return;
        }
        NodeItem prevRoot2 = this.m_prevRoot;
        while (true) {
            final NodeItem nodeItem = (NodeItem)prevRoot2.getParent();
            if (nodeItem == prevRoot) {
                double n = 0.0;
                final Iterator sortedChildren = this.sortedChildren(prevRoot);
                while (sortedChildren.hasNext()) {
                    final Node node = sortedChildren.next();
                    if (node == prevRoot2) {
                        break;
                    }
                    n += ((Params)node.get("_radialTreeLayoutParams")).width;
                }
                this.m_theta1 = -6.283185307179586 * (n + ((Params)prevRoot2.get("_radialTreeLayoutParams")).width / 2.0) / ((Params)prevRoot.get("_radialTreeLayoutParams")).width + Math.atan2(prevRoot2.getY() - prevRoot.getY(), prevRoot2.getX() - prevRoot.getX());
                this.m_theta2 = this.m_theta1 + 6.283185307179586;
                this.m_prevRoot = prevRoot;
                return;
            }
            if (nodeItem == null) {
                this.m_prevRoot = prevRoot;
                return;
            }
            prevRoot2 = nodeItem;
        }
    }
    
    private double calcAngularWidth(final NodeItem nodeItem, final int maxDepth) {
        if (maxDepth > this.m_maxDepth) {
            this.m_maxDepth = maxDepth;
        }
        double n = 0.0;
        final Rectangle2D bounds = nodeItem.getBounds();
        final double width = bounds.getWidth();
        final double height = bounds.getHeight();
        final double n2 = (maxDepth == 0) ? 0.0 : (Math.sqrt(width * width + height * height) / maxDepth);
        double max;
        if (nodeItem.isExpanded() && nodeItem.getChildCount() > 0) {
            final Iterator children = nodeItem.children();
            while (children.hasNext()) {
                n += this.calcAngularWidth(children.next(), maxDepth + 1);
            }
            max = Math.max(n2, n);
        }
        else {
            max = n2;
        }
        return ((Params)nodeItem.get("_radialTreeLayoutParams")).width = max;
    }
    
    private static final double normalize(double n) {
        while (n > 6.283185307179586) {
            n -= 6.283185307179586;
        }
        while (n < 0.0) {
            n += 6.283185307179586;
        }
        return n;
    }
    
    private Iterator sortedChildren(final NodeItem nodeItem) {
        double normalize = 0.0;
        final NodeItem nodeItem2 = (NodeItem)nodeItem.getParent();
        if (nodeItem2 != null) {
            normalize = normalize(Math.atan2(nodeItem2.getY() - nodeItem.getY(), nodeItem2.getX() - nodeItem.getX()));
        }
        final int childCount = nodeItem.getChildCount();
        if (childCount == 0) {
            return null;
        }
        NodeItem nodeItem3 = (NodeItem)nodeItem.getFirstChild();
        if (!nodeItem3.isStartVisible()) {
            return nodeItem.children();
        }
        final double[] array = new double[childCount];
        final int[] array2 = new int[childCount];
        for (int i = 0; i < childCount; ++i, nodeItem3 = (NodeItem)nodeItem3.getNextSibling()) {
            array[array2[i] = i] = normalize(-normalize + Math.atan2(nodeItem3.getY() - nodeItem.getY(), nodeItem3.getX() - nodeItem.getX()));
        }
        ArrayLib.sort(array, array2);
        return new Iterator() {
            int cur = 0;
            
            public Object next() {
                return nodeItem.getChild(array2[this.cur++]);
            }
            
            public boolean hasNext() {
                return this.cur < array2.length;
            }
            
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
    
    protected void layout(final NodeItem nodeItem, final double n, final double n2, final double n3) {
        final double n4 = n3 - n2;
        final double n5 = n4 / 2.0;
        final double width = ((Params)nodeItem.get("_radialTreeLayoutParams")).width;
        double n6 = 0.0;
        final Iterator sortedChildren = this.sortedChildren(nodeItem);
        while (sortedChildren != null && sortedChildren.hasNext()) {
            final NodeItem nodeItem2 = sortedChildren.next();
            final Params params = (Params)nodeItem2.get("_radialTreeLayoutParams");
            final double n7 = params.width / width;
            if (nodeItem2.isExpanded() && nodeItem2.getChildCount() > 0) {
                this.layout(nodeItem2, n + this.m_radiusInc, n2 + n6 * n4, n2 + (n6 + n7) * n4);
            }
            this.setPolarLocation(nodeItem2, nodeItem, n, n2 + n6 * n4 + n7 * n5);
            params.angle = n7 * n4;
            n6 += n7;
        }
    }
    
    protected void setPolarLocation(final NodeItem nodeItem, final NodeItem nodeItem2, final double n, final double n2) {
        this.setX(nodeItem, nodeItem2, this.m_origin.getX() + n * Math.cos(n2));
        this.setY(nodeItem, nodeItem2, this.m_origin.getY() + n * Math.sin(n2));
    }
    
    protected void initSchema(final TupleSet set) {
        set.addColumns(RadialTreeLayout.PARAMS_SCHEMA);
    }
    
    static {
        (PARAMS_SCHEMA = new Schema()).addColumn("_radialTreeLayoutParams", Params.class, new Params());
    }
    
    public static class Params implements Cloneable
    {
        double width;
        double angle;
        
        public Object clone() {
            final Params params = new Params();
            params.width = this.width;
            params.angle = this.angle;
            return params;
        }
    }
}
