// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.layout.graph;

import prefuse.data.tuple.TupleSet;
import prefuse.visual.VisualItem;
import java.util.Arrays;
import prefuse.data.Graph;
import prefuse.util.ArrayLib;
import prefuse.visual.NodeItem;
import java.awt.geom.Rectangle2D;
import prefuse.Display;
import java.awt.geom.Point2D;
import prefuse.data.Schema;

public class NodeLinkTreeLayout extends TreeLayout
{
    private int m_orientation;
    private double m_bspace;
    private double m_tspace;
    private double m_dspace;
    private double m_offset;
    private double[] m_depths;
    private int m_maxDepth;
    private double m_ax;
    private double m_ay;
    public static final String PARAMS = "_reingoldTilfordParams";
    public static final Schema PARAMS_SCHEMA;
    
    public NodeLinkTreeLayout(final String s) {
        super(s);
        this.m_bspace = 5.0;
        this.m_tspace = 25.0;
        this.m_dspace = 50.0;
        this.m_offset = 50.0;
        this.m_depths = new double[10];
        this.m_maxDepth = 0;
        this.m_orientation = 0;
    }
    
    public NodeLinkTreeLayout(final String s, final int orientation, final double dspace, final double bspace, final double tspace) {
        super(s);
        this.m_bspace = 5.0;
        this.m_tspace = 25.0;
        this.m_dspace = 50.0;
        this.m_offset = 50.0;
        this.m_depths = new double[10];
        this.m_maxDepth = 0;
        this.m_orientation = orientation;
        this.m_dspace = dspace;
        this.m_bspace = bspace;
        this.m_tspace = tspace;
    }
    
    public void setOrientation(final int orientation) {
        if (orientation < 0 || orientation >= 5 || orientation == 4) {
            throw new IllegalArgumentException("Unsupported orientation value: " + orientation);
        }
        this.m_orientation = orientation;
    }
    
    public int getOrientation() {
        return this.m_orientation;
    }
    
    public void setDepthSpacing(final double dspace) {
        this.m_dspace = dspace;
    }
    
    public double getDepthSpacing() {
        return this.m_dspace;
    }
    
    public void setBreadthSpacing(final double bspace) {
        this.m_bspace = bspace;
    }
    
    public double getBreadthSpacing() {
        return this.m_bspace;
    }
    
    public void setSubtreeSpacing(final double tspace) {
        this.m_tspace = tspace;
    }
    
    public double getSubtreeSpacing() {
        return this.m_tspace;
    }
    
    public void setRootNodeOffset(final double offset) {
        this.m_offset = offset;
    }
    
    public double getRootNodeOffset() {
        return this.m_offset;
    }
    
    public Point2D getLayoutAnchor() {
        if (this.m_anchor != null) {
            return this.m_anchor;
        }
        this.m_tmpa.setLocation(0.0, 0.0);
        if (this.m_vis != null) {
            final Display display = this.m_vis.getDisplay(0);
            final Rectangle2D layoutBounds = this.getLayoutBounds();
            switch (this.m_orientation) {
                case 0: {
                    this.m_tmpa.setLocation(this.m_offset, display.getHeight() / 2.0);
                    break;
                }
                case 1: {
                    this.m_tmpa.setLocation(layoutBounds.getMaxX() - this.m_offset, display.getHeight() / 2.0);
                    break;
                }
                case 2: {
                    this.m_tmpa.setLocation(display.getWidth() / 2.0, this.m_offset);
                    break;
                }
                case 3: {
                    this.m_tmpa.setLocation(display.getWidth() / 2.0, layoutBounds.getMaxY() - this.m_offset);
                    break;
                }
            }
            display.getInverseTransform().transform(this.m_tmpa, this.m_tmpa);
        }
        return this.m_tmpa;
    }
    
    private double spacing(final NodeItem nodeItem, final NodeItem nodeItem2, final boolean b) {
        return (b ? this.m_bspace : this.m_tspace) + 0.5 * ((this.m_orientation == 2 || this.m_orientation == 3) ? (nodeItem.getBounds().getWidth() + nodeItem2.getBounds().getWidth()) : (nodeItem.getBounds().getHeight() + nodeItem2.getBounds().getHeight()));
    }
    
    private void updateDepths(final int n, final NodeItem nodeItem) {
        final double n2 = (this.m_orientation == 2 || this.m_orientation == 3) ? nodeItem.getBounds().getHeight() : nodeItem.getBounds().getWidth();
        if (this.m_depths.length <= n) {
            this.m_depths = ArrayLib.resize(this.m_depths, 3 * n / 2);
        }
        this.m_depths[n] = Math.max(this.m_depths[n], n2);
        this.m_maxDepth = Math.max(this.m_maxDepth, n);
    }
    
    private void determineDepths() {
        for (int i = 1; i < this.m_maxDepth; ++i) {
            final double[] depths = this.m_depths;
            final int n = i;
            depths[n] += this.m_depths[i - 1] + this.m_dspace;
        }
    }
    
    public void run(final double n) {
        this.initSchema(((Graph)this.m_vis.getGroup(this.m_group)).getNodes());
        Arrays.fill(this.m_depths, 0.0);
        this.m_maxDepth = 0;
        final Point2D layoutAnchor = this.getLayoutAnchor();
        this.m_ax = layoutAnchor.getX();
        this.m_ay = layoutAnchor.getY();
        final NodeItem layoutRoot = this.getLayoutRoot();
        final Params params = this.getParams(layoutRoot);
        this.firstWalk(layoutRoot, 0, 1);
        this.determineDepths();
        this.secondWalk(layoutRoot, null, -params.prelim, 0);
    }
    
    private void firstWalk(final NodeItem nodeItem, final int number, final int n) {
        final Params params = this.getParams(nodeItem);
        params.number = number;
        this.updateDepths(n, nodeItem);
        final boolean expanded = nodeItem.isExpanded();
        if (nodeItem.getChildCount() == 0 || !expanded) {
            final NodeItem nodeItem2 = (NodeItem)nodeItem.getPreviousSibling();
            if (nodeItem2 == null) {
                params.prelim = 0.0;
            }
            else {
                params.prelim = this.getParams(nodeItem2).prelim + this.spacing(nodeItem2, nodeItem, true);
            }
        }
        else if (expanded) {
            final NodeItem nodeItem3 = (NodeItem)nodeItem.getFirstChild();
            final NodeItem nodeItem4 = (NodeItem)nodeItem.getLastChild();
            NodeItem apportion = nodeItem3;
            NodeItem nodeItem5 = nodeItem3;
            int n2 = 0;
            while (nodeItem5 != null) {
                this.firstWalk(nodeItem5, n2, n + 1);
                apportion = this.apportion(nodeItem5, apportion);
                ++n2;
                nodeItem5 = (NodeItem)nodeItem5.getNextSibling();
            }
            this.executeShifts(nodeItem);
            final double prelim = 0.5 * (this.getParams(nodeItem3).prelim + this.getParams(nodeItem4).prelim);
            final NodeItem nodeItem6 = (NodeItem)nodeItem.getPreviousSibling();
            if (nodeItem6 != null) {
                params.prelim = this.getParams(nodeItem6).prelim + this.spacing(nodeItem6, nodeItem, true);
                params.mod = params.prelim - prelim;
            }
            else {
                params.prelim = prelim;
            }
        }
    }
    
    private NodeItem apportion(final NodeItem ancestor, NodeItem nodeItem) {
        final NodeItem nodeItem2 = (NodeItem)ancestor.getPreviousSibling();
        if (nodeItem2 != null) {
            NodeItem nextRight = ancestor;
            final NodeItem nodeItem3 = nodeItem2;
            NodeItem nextLeft = (NodeItem)ancestor.getParent().getFirstChild();
            double mod = this.getParams(ancestor).mod;
            double mod2 = this.getParams(nextRight).mod;
            double mod3 = this.getParams(nodeItem3).mod;
            double mod4 = this.getParams(nextLeft).mod;
            NodeItem thread;
            NodeItem thread2;
            NodeItem nodeItem4;
            NodeItem nodeItem5;
            for (thread = this.nextRight(nodeItem3), thread2 = this.nextLeft(ancestor); thread != null && thread2 != null; thread = this.nextRight(nodeItem4), thread2 = this.nextLeft(nodeItem5)) {
                nodeItem4 = thread;
                nodeItem5 = thread2;
                nextLeft = this.nextLeft(nextLeft);
                nextRight = this.nextRight(nextRight);
                this.getParams(nextRight).ancestor = ancestor;
                final double n = this.getParams(nodeItem4).prelim + mod3 - (this.getParams(nodeItem5).prelim + mod) + this.spacing(nodeItem4, nodeItem5, false);
                if (n > 0.0) {
                    this.moveSubtree(this.ancestor(nodeItem4, ancestor, nodeItem), ancestor, n);
                    mod += n;
                    mod2 += n;
                }
                mod3 += this.getParams(nodeItem4).mod;
                mod += this.getParams(nodeItem5).mod;
                mod4 += this.getParams(nextLeft).mod;
                mod2 += this.getParams(nextRight).mod;
            }
            if (thread != null && this.nextRight(nextRight) == null) {
                final Params params = this.getParams(nextRight);
                params.thread = thread;
                final Params params2 = params;
                params2.mod += mod3 - mod2;
            }
            if (thread2 != null && this.nextLeft(nextLeft) == null) {
                final Params params3 = this.getParams(nextLeft);
                params3.thread = thread2;
                final Params params4 = params3;
                params4.mod += mod - mod4;
                nodeItem = ancestor;
            }
        }
        return nodeItem;
    }
    
    private NodeItem nextLeft(final NodeItem nodeItem) {
        NodeItem nodeItem2 = null;
        if (nodeItem.isExpanded()) {
            nodeItem2 = (NodeItem)nodeItem.getFirstChild();
        }
        return (nodeItem2 != null) ? nodeItem2 : this.getParams(nodeItem).thread;
    }
    
    private NodeItem nextRight(final NodeItem nodeItem) {
        NodeItem nodeItem2 = null;
        if (nodeItem.isExpanded()) {
            nodeItem2 = (NodeItem)nodeItem.getLastChild();
        }
        return (nodeItem2 != null) ? nodeItem2 : this.getParams(nodeItem).thread;
    }
    
    private void moveSubtree(final NodeItem nodeItem, final NodeItem nodeItem2, final double n) {
        final Params params = this.getParams(nodeItem);
        final Params params2 = this.getParams(nodeItem2);
        final double n2 = params2.number - params.number;
        final Params params3 = params2;
        params3.change -= n / n2;
        final Params params4 = params2;
        params4.shift += n;
        final Params params5 = params;
        params5.change += n / n2;
        final Params params6 = params2;
        params6.prelim += n;
        final Params params7 = params2;
        params7.mod += n;
    }
    
    private void executeShifts(final NodeItem nodeItem) {
        double n = 0.0;
        double n2 = 0.0;
        for (NodeItem nodeItem2 = (NodeItem)nodeItem.getLastChild(); nodeItem2 != null; nodeItem2 = (NodeItem)nodeItem2.getPreviousSibling()) {
            final Params params2;
            final Params params = params2 = this.getParams(nodeItem2);
            params2.prelim += n;
            final Params params3 = params;
            params3.mod += n;
            n2 += params.change;
            n += params.shift + n2;
        }
    }
    
    private NodeItem ancestor(final NodeItem nodeItem, final NodeItem nodeItem2, final NodeItem nodeItem3) {
        final NodeItem nodeItem4 = (NodeItem)nodeItem2.getParent();
        final Params params = this.getParams(nodeItem);
        if (params.ancestor.getParent() == nodeItem4) {
            return params.ancestor;
        }
        return nodeItem3;
    }
    
    private void secondWalk(final NodeItem nodeItem, final NodeItem nodeItem2, final double n, int n2) {
        final Params params = this.getParams(nodeItem);
        this.setBreadth(nodeItem, nodeItem2, params.prelim + n);
        this.setDepth(nodeItem, nodeItem2, this.m_depths[n2]);
        if (nodeItem.isExpanded()) {
            ++n2;
            for (NodeItem nodeItem3 = (NodeItem)nodeItem.getFirstChild(); nodeItem3 != null; nodeItem3 = (NodeItem)nodeItem3.getNextSibling()) {
                this.secondWalk(nodeItem3, nodeItem, n + params.mod, n2);
            }
        }
        params.clear();
    }
    
    private void setBreadth(final NodeItem nodeItem, final NodeItem nodeItem2, final double n) {
        switch (this.m_orientation) {
            case 0:
            case 1: {
                this.setY(nodeItem, nodeItem2, this.m_ay + n);
                break;
            }
            case 2:
            case 3: {
                this.setX(nodeItem, nodeItem2, this.m_ax + n);
                break;
            }
            default: {
                throw new IllegalStateException();
            }
        }
    }
    
    private void setDepth(final NodeItem nodeItem, final NodeItem nodeItem2, final double n) {
        switch (this.m_orientation) {
            case 0: {
                this.setX(nodeItem, nodeItem2, this.m_ax + n);
                break;
            }
            case 1: {
                this.setX(nodeItem, nodeItem2, this.m_ax - n);
                break;
            }
            case 2: {
                this.setY(nodeItem, nodeItem2, this.m_ay + n);
                break;
            }
            case 3: {
                this.setY(nodeItem, nodeItem2, this.m_ay - n);
                break;
            }
            default: {
                throw new IllegalStateException();
            }
        }
    }
    
    protected void initSchema(final TupleSet set) {
        set.addColumns(NodeLinkTreeLayout.PARAMS_SCHEMA);
    }
    
    private Params getParams(final NodeItem nodeItem) {
        Params params = (Params)nodeItem.get("_reingoldTilfordParams");
        if (params == null) {
            params = new Params();
            nodeItem.set("_reingoldTilfordParams", params);
        }
        if (params.number == -2) {
            params.init(nodeItem);
        }
        return params;
    }
    
    static {
        (PARAMS_SCHEMA = new Schema()).addColumn("_reingoldTilfordParams", Params.class);
    }
    
    public static class Params implements Cloneable
    {
        double prelim;
        double mod;
        double shift;
        double change;
        int number;
        NodeItem ancestor;
        NodeItem thread;
        
        public Params() {
            this.number = -2;
            this.ancestor = null;
            this.thread = null;
        }
        
        public void init(final NodeItem ancestor) {
            this.ancestor = ancestor;
            this.number = -1;
        }
        
        public void clear() {
            this.number = -2;
            final double n = 0.0;
            this.change = n;
            this.shift = n;
            this.mod = n;
            this.prelim = n;
            final NodeItem nodeItem = null;
            this.thread = nodeItem;
            this.ancestor = nodeItem;
        }
    }
}
