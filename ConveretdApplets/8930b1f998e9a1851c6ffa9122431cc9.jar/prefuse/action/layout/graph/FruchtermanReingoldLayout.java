// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.layout.graph;

import prefuse.data.tuple.TupleSet;
import prefuse.visual.VisualItem;
import java.util.Random;
import java.util.Iterator;
import java.awt.geom.Rectangle2D;
import prefuse.visual.EdgeItem;
import prefuse.visual.NodeItem;
import prefuse.util.PrefuseLib;
import prefuse.data.Graph;
import prefuse.data.Schema;
import prefuse.action.layout.Layout;

public class FruchtermanReingoldLayout extends Layout
{
    private double forceConstant;
    private double temp;
    private int maxIter;
    protected String m_nodeGroup;
    protected String m_edgeGroup;
    protected int m_fidx;
    private static final double EPSILON = 1.0E-6;
    private static final double ALPHA = 0.1;
    public static final String PARAMS = "_fruchtermanReingoldParams";
    public static final Schema PARAMS_SCHEMA;
    
    public FruchtermanReingoldLayout(final String s) {
        this(s, 700);
    }
    
    public FruchtermanReingoldLayout(final String s, final int maxIter) {
        super(s);
        this.maxIter = 700;
        this.m_nodeGroup = PrefuseLib.getGroupName(s, Graph.NODES);
        this.m_edgeGroup = PrefuseLib.getGroupName(s, Graph.EDGES);
        this.maxIter = maxIter;
    }
    
    public int getMaxIterations() {
        return this.maxIter;
    }
    
    public void setMaxIterations(final int maxIter) {
        this.maxIter = maxIter;
    }
    
    public void run(final double n) {
        final Graph graph = (Graph)this.m_vis.getGroup(this.m_group);
        final Rectangle2D layoutBounds = super.getLayoutBounds();
        this.init(graph, layoutBounds);
        for (int i = 0; i < this.maxIter; ++i) {
            final Iterator nodes = graph.nodes();
            while (nodes.hasNext()) {
                final NodeItem nodeItem = nodes.next();
                if (nodeItem.isFixed()) {
                    continue;
                }
                this.calcRepulsion(graph, nodeItem);
            }
            final Iterator edges = graph.edges();
            while (edges.hasNext()) {
                this.calcAttraction(edges.next());
            }
            final Iterator nodes2 = graph.nodes();
            while (nodes2.hasNext()) {
                final NodeItem nodeItem2 = nodes2.next();
                if (nodeItem2.isFixed()) {
                    continue;
                }
                this.calcPositions(nodeItem2, layoutBounds);
            }
            this.cool(i);
        }
        this.finish(graph);
    }
    
    private void init(final Graph graph, final Rectangle2D rectangle2D) {
        this.initSchema(graph.getNodes());
        this.temp = rectangle2D.getWidth() / 10.0;
        this.forceConstant = 0.75 * Math.sqrt(rectangle2D.getHeight() * rectangle2D.getWidth() / graph.getNodeCount());
        final Iterator nodes = graph.nodes();
        final Random random = new Random(42L);
        final double n = 0.1 * rectangle2D.getWidth() / 2.0;
        final double n2 = 0.1 * rectangle2D.getHeight() / 2.0;
        while (nodes.hasNext()) {
            final Params params = this.getParams(nodes.next());
            params.loc[0] = rectangle2D.getCenterX() + random.nextDouble() * n;
            params.loc[1] = rectangle2D.getCenterY() + random.nextDouble() * n2;
        }
    }
    
    private void finish(final Graph graph) {
        final Iterator nodes = graph.nodes();
        while (nodes.hasNext()) {
            final NodeItem nodeItem = nodes.next();
            final Params params = this.getParams(nodeItem);
            this.setX(nodeItem, null, params.loc[0]);
            this.setY(nodeItem, null, params.loc[1]);
        }
    }
    
    public void calcPositions(final NodeItem nodeItem, final Rectangle2D rectangle2D) {
        final Params params = this.getParams(nodeItem);
        final double max = Math.max(1.0E-6, Math.sqrt(params.disp[0] * params.disp[0] + params.disp[1] * params.disp[1]));
        final double n = params.disp[0] / max * Math.min(max, this.temp);
        if (Double.isNaN(n)) {
            System.err.println("Mathematical error... (calcPositions:xDisp)");
        }
        final double n2 = params.disp[1] / max * Math.min(max, this.temp);
        final double[] loc = params.loc;
        final int n3 = 0;
        loc[n3] += n;
        final double[] loc2 = params.loc;
        final int n4 = 1;
        loc2[n4] += n2;
        final double n5 = rectangle2D.getWidth() / 50.0;
        double n6 = params.loc[0];
        if (n6 < rectangle2D.getMinX() + n5) {
            n6 = rectangle2D.getMinX() + n5 + Math.random() * n5 * 2.0;
        }
        else if (n6 > rectangle2D.getMaxX() - n5) {
            n6 = rectangle2D.getMaxX() - n5 - Math.random() * n5 * 2.0;
        }
        double n7 = params.loc[1];
        if (n7 < rectangle2D.getMinY() + n5) {
            n7 = rectangle2D.getMinY() + n5 + Math.random() * n5 * 2.0;
        }
        else if (n7 > rectangle2D.getMaxY() - n5) {
            n7 = rectangle2D.getMaxY() - n5 - Math.random() * n5 * 2.0;
        }
        params.loc[0] = n6;
        params.loc[1] = n7;
    }
    
    public void calcAttraction(final EdgeItem edgeItem) {
        final Params params = this.getParams(edgeItem.getSourceItem());
        final Params params2 = this.getParams(edgeItem.getTargetItem());
        final double n = params.loc[0] - params2.loc[0];
        final double n2 = params.loc[1] - params2.loc[1];
        final double max = Math.max(1.0E-6, Math.sqrt(n * n + n2 * n2));
        final double n3 = max * max / this.forceConstant;
        if (Double.isNaN(n3)) {
            System.err.println("Mathematical error...");
        }
        final double n4 = n / max * n3;
        final double n5 = n2 / max * n3;
        final double[] disp = params.disp;
        final int n6 = 0;
        disp[n6] -= n4;
        final double[] disp2 = params.disp;
        final int n7 = 1;
        disp2[n7] -= n5;
        final double[] disp3 = params2.disp;
        final int n8 = 0;
        disp3[n8] += n4;
        final double[] disp4 = params2.disp;
        final int n9 = 1;
        disp4[n9] += n5;
    }
    
    public void calcRepulsion(final Graph graph, final NodeItem nodeItem) {
        final Params params = this.getParams(nodeItem);
        params.disp[0] = 0.0;
        params.disp[1] = 0.0;
        final Iterator nodes = graph.nodes();
        while (nodes.hasNext()) {
            final NodeItem nodeItem2 = nodes.next();
            final Params params2 = this.getParams(nodeItem2);
            if (nodeItem2.isFixed()) {
                continue;
            }
            if (nodeItem == nodeItem2) {
                continue;
            }
            final double n = params.loc[0] - params2.loc[0];
            final double n2 = params.loc[1] - params2.loc[1];
            final double max = Math.max(1.0E-6, Math.sqrt(n * n + n2 * n2));
            final double n3 = this.forceConstant * this.forceConstant / max;
            if (Double.isNaN(n3)) {
                System.err.println("Mathematical error...");
            }
            final double[] disp = params.disp;
            final int n4 = 0;
            disp[n4] += n / max * n3;
            final double[] disp2 = params.disp;
            final int n5 = 1;
            disp2[n5] += n2 / max * n3;
        }
    }
    
    private void cool(final int n) {
        this.temp *= 1.0 - n / this.maxIter;
    }
    
    protected void initSchema(final TupleSet set) {
        try {
            set.addColumns(FruchtermanReingoldLayout.PARAMS_SCHEMA);
        }
        catch (IllegalArgumentException ex) {}
    }
    
    private Params getParams(final VisualItem visualItem) {
        Params params = (Params)visualItem.get("_fruchtermanReingoldParams");
        if (params == null) {
            params = new Params();
            visualItem.set("_fruchtermanReingoldParams", params);
        }
        return params;
    }
    
    static {
        (PARAMS_SCHEMA = new Schema()).addColumn("_fruchtermanReingoldParams", Params.class);
    }
    
    public static class Params implements Cloneable
    {
        double[] loc;
        double[] disp;
        
        public Params() {
            this.loc = new double[2];
            this.disp = new double[2];
        }
    }
}
