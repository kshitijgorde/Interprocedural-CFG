// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.layout.graph;

import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import prefuse.data.Node;
import prefuse.data.util.TreeNodeIterator;
import prefuse.data.Graph;
import prefuse.visual.NodeItem;
import prefuse.visual.VisualItem;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Comparator;
import prefuse.data.Schema;

public class SquarifiedTreeMapLayout extends TreeLayout
{
    public static final String AREA = "_area";
    public static final Schema AREA_SCHEMA;
    private static Comparator s_cmp;
    private ArrayList m_kids;
    private ArrayList m_row;
    private Rectangle2D m_r;
    private double m_frame;
    
    public SquarifiedTreeMapLayout(final String s) {
        this(s, 0.0);
    }
    
    public SquarifiedTreeMapLayout(final String s, final double frameWidth) {
        super(s);
        this.m_kids = new ArrayList();
        this.m_row = new ArrayList();
        this.m_r = new Rectangle2D.Double();
        this.setFrameWidth(frameWidth);
    }
    
    public void setFrameWidth(final double frame) {
        if (frame < 0.0) {
            throw new IllegalArgumentException("Frame value must be greater than or equal to 0.");
        }
        this.m_frame = frame;
    }
    
    public double getFrameWidth() {
        return this.m_frame;
    }
    
    public void run(final double n) {
        final NodeItem layoutRoot = this.getLayoutRoot();
        final Rectangle2D layoutBounds = this.getLayoutBounds();
        this.m_r.setRect(layoutBounds.getX(), layoutBounds.getY(), layoutBounds.getWidth() - 1.0, layoutBounds.getHeight() - 1.0);
        this.computeAreas(layoutRoot);
        this.setX(layoutRoot, null, 0.0);
        this.setY(layoutRoot, null, 0.0);
        layoutRoot.setBounds(0.0, 0.0, this.m_r.getWidth(), this.m_r.getHeight());
        this.updateArea(layoutRoot, this.m_r);
        this.layout(layoutRoot, this.m_r);
    }
    
    private void computeAreas(final NodeItem nodeItem) {
        int n = 0;
        ((Graph)this.m_vis.getGroup(this.m_group)).getNodes().addColumns(SquarifiedTreeMapLayout.AREA_SCHEMA);
        final TreeNodeIterator treeNodeIterator = new TreeNodeIterator(nodeItem);
        while (treeNodeIterator.hasNext()) {
            treeNodeIterator.next().setDouble("_area", 0.0);
        }
        final TreeNodeIterator treeNodeIterator2 = new TreeNodeIterator(nodeItem);
        while (treeNodeIterator2.hasNext()) {
            final NodeItem nodeItem2 = treeNodeIterator2.next();
            if (nodeItem2.getChildCount() == 0) {
                final double size = nodeItem2.getSize();
                nodeItem2.setDouble("_area", size);
                for (NodeItem nodeItem3 = (NodeItem)nodeItem2.getParent(); nodeItem3 != null; nodeItem3 = (NodeItem)nodeItem3.getParent()) {
                    nodeItem3.setDouble("_area", size + nodeItem3.getDouble("_area"));
                }
                ++n;
            }
        }
        final Rectangle2D layoutBounds = this.getLayoutBounds();
        final double n2 = (layoutBounds.getWidth() - 1.0) * (layoutBounds.getHeight() - 1.0) / nodeItem.getDouble("_area");
        final TreeNodeIterator treeNodeIterator3 = new TreeNodeIterator(nodeItem);
        while (treeNodeIterator3.hasNext()) {
            final NodeItem nodeItem4 = treeNodeIterator3.next();
            nodeItem4.setDouble("_area", nodeItem4.getDouble("_area") * n2);
        }
    }
    
    private void layout(final NodeItem nodeItem, final Rectangle2D rectangle2D) {
        final Iterator children = nodeItem.children();
        while (children.hasNext()) {
            this.m_kids.add(children.next());
        }
        Collections.sort((List<Object>)this.m_kids, SquarifiedTreeMapLayout.s_cmp);
        this.squarify(this.m_kids, this.m_row, Math.min(rectangle2D.getWidth(), rectangle2D.getHeight()), rectangle2D);
        this.m_kids.clear();
        final Iterator children2 = nodeItem.children();
        while (children2.hasNext()) {
            final NodeItem nodeItem2 = children2.next();
            if (nodeItem2.getChildCount() > 0) {
                this.updateArea(nodeItem2, rectangle2D);
                this.layout(nodeItem2, rectangle2D);
            }
        }
    }
    
    private void updateArea(final NodeItem nodeItem, final Rectangle2D rectangle2D) {
        final Rectangle2D bounds = nodeItem.getBounds();
        if (this.m_frame == 0.0) {
            rectangle2D.setRect(bounds);
            return;
        }
        final double n = nodeItem.getDouble("_area") - 2.0 * this.m_frame * (bounds.getWidth() + bounds.getHeight() - 2.0 * this.m_frame);
        double n2 = 0.0;
        final Iterator children = nodeItem.children();
        while (children.hasNext()) {
            n2 += children.next().getDouble("_area");
        }
        final double n3 = n / n2;
        final Iterator children2 = nodeItem.children();
        while (children2.hasNext()) {
            final NodeItem nodeItem2 = children2.next();
            nodeItem2.setDouble("_area", nodeItem2.getDouble("_area") * n3);
        }
        rectangle2D.setRect(bounds.getX() + this.m_frame, bounds.getY() + this.m_frame, bounds.getWidth() - 2.0 * this.m_frame, bounds.getHeight() - 2.0 * this.m_frame);
    }
    
    private void squarify(final List list, final List list2, double min, Rectangle2D rectangle2D) {
        double n = Double.MAX_VALUE;
        int size;
        while ((size = list.size()) > 0) {
            final VisualItem visualItem = list.get(size - 1);
            if (visualItem.getDouble("_area") <= 0.0) {
                list.remove(size - 1);
            }
            else {
                list2.add(visualItem);
                final double worst = this.worst(list2, min);
                if (worst <= n) {
                    list.remove(size - 1);
                    n = worst;
                }
                else {
                    list2.remove(list2.size() - 1);
                    rectangle2D = this.layoutRow(list2, min, rectangle2D);
                    min = Math.min(rectangle2D.getWidth(), rectangle2D.getHeight());
                    list2.clear();
                    n = Double.MAX_VALUE;
                }
            }
        }
        if (list2.size() > 0) {
            rectangle2D = this.layoutRow(list2, min, rectangle2D);
            list2.clear();
        }
    }
    
    private double worst(final List list, double n) {
        double max = Double.MIN_VALUE;
        double min = Double.MAX_VALUE;
        double n2 = 0.0;
        final Iterator<VisualItem> iterator = list.iterator();
        while (iterator.hasNext()) {
            final double double1 = iterator.next().getDouble("_area");
            min = Math.min(min, double1);
            max = Math.max(max, double1);
            n2 += double1;
        }
        final double n3 = n2 * n2;
        n *= n;
        return Math.max(n * max / n3, n3 / (n * min));
    }
    
    private Rectangle2D layoutRow(final List list, final double n, final Rectangle2D rectangle2D) {
        double n2 = 0.0;
        final Iterator<NodeItem> iterator = list.iterator();
        while (iterator.hasNext()) {
            n2 += iterator.next().getDouble("_area");
        }
        final double x = rectangle2D.getX();
        final double y = rectangle2D.getY();
        double n3 = 0.0;
        final double n4 = (n == 0.0) ? 0.0 : (n2 / n);
        final boolean b = n == rectangle2D.getWidth();
        for (final NodeItem nodeItem : list) {
            final NodeItem nodeItem2 = (NodeItem)nodeItem.getParent();
            if (b) {
                this.setX(nodeItem, nodeItem2, x + n3);
                this.setY(nodeItem, nodeItem2, y);
            }
            else {
                this.setX(nodeItem, nodeItem2, x);
                this.setY(nodeItem, nodeItem2, y + n3);
            }
            final double n5 = nodeItem.getDouble("_area") / n4;
            if (b) {
                this.setNodeDimensions(nodeItem, n5, n4);
                n3 += n5;
            }
            else {
                this.setNodeDimensions(nodeItem, n4, n5);
                n3 += n5;
            }
        }
        if (b) {
            rectangle2D.setRect(x, y + n4, rectangle2D.getWidth(), rectangle2D.getHeight() - n4);
        }
        else {
            rectangle2D.setRect(x + n4, y, rectangle2D.getWidth() - n4, rectangle2D.getHeight());
        }
        return rectangle2D;
    }
    
    private void setNodeDimensions(final NodeItem nodeItem, final double n, final double n2) {
        nodeItem.setBounds(nodeItem.getX(), nodeItem.getY(), n, n2);
    }
    
    static {
        (AREA_SCHEMA = new Schema()).addColumn("_area", Double.TYPE);
        SquarifiedTreeMapLayout.s_cmp = new Comparator() {
            public int compare(final Object o, final Object o2) {
                final double double1 = ((VisualItem)o).getDouble("_area");
                final double double2 = ((VisualItem)o2).getDouble("_area");
                return (double1 > double2) ? 1 : ((double1 < double2) ? -1 : 0);
            }
        };
    }
}
