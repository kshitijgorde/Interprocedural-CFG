// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.layout.graph;

import prefuse.data.tuple.TupleSet;
import prefuse.visual.VisualItem;
import java.util.Iterator;
import prefuse.visual.NodeItem;
import java.awt.geom.Point2D;
import prefuse.data.Graph;
import prefuse.data.Schema;

public class BalloonTreeLayout extends TreeLayout
{
    private int m_minRadius;
    public static final String PARAMS = "_balloonTreeLayoutParams";
    public static final Schema PARAMS_SCHEMA;
    
    public BalloonTreeLayout(final String s) {
        this(s, 2);
    }
    
    public BalloonTreeLayout(final String s, final int minRadius) {
        super(s);
        this.m_minRadius = 2;
        this.m_minRadius = minRadius;
    }
    
    public int getMinRadius() {
        return this.m_minRadius;
    }
    
    public void setMinRadius(final int minRadius) {
        this.m_minRadius = minRadius;
    }
    
    public void run(final double n) {
        this.initSchema(((Graph)this.m_vis.getGroup(this.m_group)).getNodes());
        final Point2D layoutAnchor = this.getLayoutAnchor();
        this.layout(this.getLayoutRoot(), layoutAnchor.getX(), layoutAnchor.getY());
    }
    
    private void layout(final NodeItem nodeItem, final double n, final double n2) {
        this.firstWalk(nodeItem);
        this.secondWalk(nodeItem, null, n, n2, 1.0, 0.0);
    }
    
    private void firstWalk(final NodeItem nodeItem) {
        final Params params = this.getParams(nodeItem);
        params.d = 0;
        double n = 0.0;
        final Iterator children = nodeItem.children();
        while (children.hasNext()) {
            final NodeItem nodeItem2 = children.next();
            if (!nodeItem2.isVisible()) {
                continue;
            }
            this.firstWalk(nodeItem2);
            final Params params2 = this.getParams(nodeItem2);
            params.d = Math.max(params.d, params2.r);
            params2.a = Math.atan(params2.r / (params.d + params2.r));
            n += params2.a;
        }
        this.adjustChildren(params, n);
        this.setRadius(params);
    }
    
    private void adjustChildren(final Params params, final double n) {
        if (n > 3.141592653589793) {
            params.c = 3.141592653589793 / n;
            params.f = 0.0;
        }
        else {
            params.c = 1.0;
            params.f = 3.141592653589793 - n;
        }
    }
    
    private void setRadius(final Params params) {
        params.r = Math.max(params.d, this.m_minRadius) + 2 * params.d;
    }
    
    private void secondWalk(final NodeItem nodeItem, final NodeItem nodeItem2, final double n, final double n2, final double n3, final double n4) {
        this.setX(nodeItem, nodeItem2, n);
        this.setY(nodeItem, nodeItem2, n2);
        final Params params = this.getParams(nodeItem);
        int n5 = 0;
        final Iterator children = nodeItem.children();
        while (children.hasNext()) {
            if (children.next().isVisible()) {
                ++n5;
            }
        }
        final double n6 = n3 * params.d;
        double n7 = n4 + 3.141592653589793;
        final double n8 = (n5 == 0) ? 0.0 : (params.f / n5);
        double n9 = 0.0;
        final Iterator children2 = nodeItem.children();
        while (children2.hasNext()) {
            final NodeItem nodeItem3 = children2.next();
            if (!nodeItem3.isVisible()) {
                continue;
            }
            final double n10 = params.c * this.getParams(nodeItem3).a;
            final double n11 = params.d * Math.tan(n10) / (1.0 - Math.tan(n10));
            n7 += n9 + n10 + n8;
            final double n12 = (n3 * n11 + n6) * Math.cos(n7);
            final double n13 = (n3 * n11 + n6) * Math.sin(n7);
            n9 = n10;
            this.secondWalk(nodeItem3, nodeItem, n + n12, n2 + n13, n3 * params.c, n7);
        }
    }
    
    private void initSchema(final TupleSet set) {
        try {
            set.addColumns(BalloonTreeLayout.PARAMS_SCHEMA);
        }
        catch (IllegalArgumentException ex) {}
    }
    
    private Params getParams(final NodeItem nodeItem) {
        Params params = (Params)nodeItem.get("_balloonTreeLayoutParams");
        if (params == null) {
            params = new Params();
            nodeItem.set("_balloonTreeLayoutParams", params);
        }
        return params;
    }
    
    static {
        (PARAMS_SCHEMA = new Schema()).addColumn("_balloonTreeLayoutParams", Params.class);
    }
    
    public static class Params
    {
        public int d;
        public int r;
        public double rx;
        public double ry;
        public double a;
        public double c;
        public double f;
    }
}
