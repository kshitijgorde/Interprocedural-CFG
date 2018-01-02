// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.action.layout.graph;

import prefuse.data.tuple.TupleSet;
import prefuse.visual.EdgeItem;
import java.awt.geom.Rectangle2D;
import prefuse.util.force.ForceItem;
import java.util.Iterator;
import java.awt.geom.Point2D;
import prefuse.visual.NodeItem;
import prefuse.util.force.DragForce;
import prefuse.util.force.SpringForce;
import prefuse.util.force.Force;
import prefuse.util.force.NBodyForce;
import prefuse.util.PrefuseLib;
import prefuse.data.Graph;
import prefuse.data.Schema;
import prefuse.visual.VisualItem;
import prefuse.util.force.ForceSimulator;
import prefuse.action.layout.Layout;

public class ForceDirectedLayout extends Layout
{
    private ForceSimulator m_fsim;
    private long m_lasttime;
    private long m_maxstep;
    private boolean m_runonce;
    private int m_iterations;
    private boolean m_enforceBounds;
    protected transient VisualItem referrer;
    protected String m_nodeGroup;
    protected String m_edgeGroup;
    public static final String FORCEITEM = "_forceItem";
    public static final Schema FORCEITEM_SCHEMA;
    
    public ForceDirectedLayout(final String s) {
        this(s, false, false);
    }
    
    public ForceDirectedLayout(final String s, final boolean b) {
        this(s, b, false);
    }
    
    public ForceDirectedLayout(final String s, final boolean enforceBounds, final boolean runonce) {
        super(s);
        this.m_lasttime = -1L;
        this.m_maxstep = 50L;
        this.m_iterations = 100;
        this.m_nodeGroup = PrefuseLib.getGroupName(s, Graph.NODES);
        this.m_edgeGroup = PrefuseLib.getGroupName(s, Graph.EDGES);
        this.m_enforceBounds = enforceBounds;
        this.m_runonce = runonce;
        (this.m_fsim = new ForceSimulator()).addForce(new NBodyForce());
        this.m_fsim.addForce(new SpringForce());
        this.m_fsim.addForce(new DragForce());
    }
    
    public ForceDirectedLayout(final String s, final ForceSimulator forceSimulator, final boolean b) {
        this(s, forceSimulator, b, false);
    }
    
    public ForceDirectedLayout(final String s, final ForceSimulator fsim, final boolean enforceBounds, final boolean runonce) {
        super(s);
        this.m_lasttime = -1L;
        this.m_maxstep = 50L;
        this.m_iterations = 100;
        this.m_nodeGroup = PrefuseLib.getGroupName(s, Graph.NODES);
        this.m_edgeGroup = PrefuseLib.getGroupName(s, Graph.EDGES);
        this.m_enforceBounds = enforceBounds;
        this.m_runonce = runonce;
        this.m_fsim = fsim;
    }
    
    public long getMaxTimeStep() {
        return this.m_maxstep;
    }
    
    public void setMaxTimeStep(final long maxstep) {
        this.m_maxstep = maxstep;
    }
    
    public ForceSimulator getForceSimulator() {
        return this.m_fsim;
    }
    
    public void setForceSimulator(final ForceSimulator fsim) {
        this.m_fsim = fsim;
    }
    
    public int getIterations() {
        return this.m_iterations;
    }
    
    public void setIterations(final int iterations) {
        if (iterations < 1) {
            throw new IllegalArgumentException("Iterations must be a positive number!");
        }
        this.m_iterations = iterations;
    }
    
    public void setDataGroups(final String nodeGroup, final String edgeGroup) {
        this.m_nodeGroup = nodeGroup;
        this.m_edgeGroup = edgeGroup;
    }
    
    public void run(final double n) {
        if (this.m_runonce) {
            final Point2D layoutAnchor = this.getLayoutAnchor();
            final Iterator visibleItems = this.m_vis.visibleItems(this.m_nodeGroup);
            while (visibleItems.hasNext()) {
                final NodeItem nodeItem = visibleItems.next();
                nodeItem.setX(layoutAnchor.getX());
                nodeItem.setY(layoutAnchor.getY());
            }
            this.m_fsim.clear();
            long n2 = 1000L;
            this.initSimulator(this.m_fsim);
            for (int i = 0; i < this.m_iterations; ++i) {
                n2 *= (long)(1.0 - i / this.m_iterations);
                this.m_fsim.runSimulator(n2 + 50L);
            }
            this.updateNodePositions();
        }
        else {
            if (this.m_lasttime == -1L) {
                this.m_lasttime = System.currentTimeMillis() - 20L;
            }
            final long currentTimeMillis = System.currentTimeMillis();
            final long min = Math.min(this.m_maxstep, currentTimeMillis - this.m_lasttime);
            this.m_lasttime = currentTimeMillis;
            this.m_fsim.clear();
            this.initSimulator(this.m_fsim);
            this.m_fsim.runSimulator(min);
            this.updateNodePositions();
        }
        if (n == 1.0) {
            this.reset();
        }
    }
    
    private void updateNodePositions() {
        final Rectangle2D layoutBounds = this.getLayoutBounds();
        double minX = 0.0;
        double maxX = 0.0;
        double minY = 0.0;
        double maxY = 0.0;
        if (layoutBounds != null) {
            minX = layoutBounds.getMinX();
            minY = layoutBounds.getMinY();
            maxX = layoutBounds.getMaxX();
            maxY = layoutBounds.getMaxY();
        }
        final Iterator visibleItems = this.m_vis.visibleItems(this.m_nodeGroup);
        while (visibleItems.hasNext()) {
            final VisualItem visualItem = visibleItems.next();
            final ForceItem forceItem = (ForceItem)visualItem.get("_forceItem");
            if (visualItem.isFixed()) {
                forceItem.force[0] = 0.0f;
                forceItem.force[1] = 0.0f;
                forceItem.velocity[0] = 0.0f;
                forceItem.velocity[1] = 0.0f;
                if (!Double.isNaN(visualItem.getX())) {
                    continue;
                }
                this.setX(visualItem, this.referrer, 0.0);
                this.setY(visualItem, this.referrer, 0.0);
            }
            else {
                double n = forceItem.location[0];
                double n2 = forceItem.location[1];
                if (this.m_enforceBounds && layoutBounds != null) {
                    final Rectangle2D bounds = visualItem.getBounds();
                    final double n3 = bounds.getWidth() / 2.0;
                    final double n4 = bounds.getHeight() / 2.0;
                    if (n + n3 > maxX) {
                        n = maxX - n3;
                    }
                    if (n - n3 < minX) {
                        n = minX + n3;
                    }
                    if (n2 + n4 > maxY) {
                        n2 = maxY - n4;
                    }
                    if (n2 - n4 < minY) {
                        n2 = minY + n4;
                    }
                }
                this.setX(visualItem, this.referrer, n);
                this.setY(visualItem, this.referrer, n2);
            }
        }
    }
    
    public void reset() {
        final Iterator visibleItems = this.m_vis.visibleItems(this.m_nodeGroup);
        while (visibleItems.hasNext()) {
            final VisualItem visualItem = visibleItems.next();
            final ForceItem forceItem = (ForceItem)visualItem.get("_forceItem");
            if (forceItem != null) {
                forceItem.location[0] = (float)visualItem.getEndX();
                forceItem.location[1] = (float)visualItem.getEndY();
                forceItem.force[0] = (forceItem.force[1] = 0.0f);
                forceItem.velocity[0] = (forceItem.velocity[1] = 0.0f);
            }
        }
        this.m_lasttime = -1L;
    }
    
    protected void initSimulator(final ForceSimulator forceSimulator) {
        final TupleSet group = this.m_vis.getGroup(this.m_nodeGroup);
        if (group == null) {
            return;
        }
        try {
            group.addColumns(ForceDirectedLayout.FORCEITEM_SCHEMA);
        }
        catch (IllegalArgumentException ex) {}
        final float n = (this.referrer == null) ? 0.0f : ((float)this.referrer.getX());
        final float n2 = (this.referrer == null) ? 0.0f : ((float)this.referrer.getY());
        final float n3 = Float.isNaN(n) ? 0.0f : n;
        final float n4 = Float.isNaN(n2) ? 0.0f : n2;
        final Iterator visibleItems = this.m_vis.visibleItems(this.m_nodeGroup);
        while (visibleItems.hasNext()) {
            final VisualItem visualItem = visibleItems.next();
            final ForceItem forceItem = (ForceItem)visualItem.get("_forceItem");
            forceItem.mass = this.getMassValue(visualItem);
            final double endX = visualItem.getEndX();
            final double endY = visualItem.getEndY();
            forceItem.location[0] = (Double.isNaN(endX) ? n3 : ((float)endX));
            forceItem.location[1] = (Double.isNaN(endY) ? n4 : ((float)endY));
            forceSimulator.addItem(forceItem);
        }
        if (this.m_edgeGroup != null) {
            final Iterator visibleItems2 = this.m_vis.visibleItems(this.m_edgeGroup);
            while (visibleItems2.hasNext()) {
                final EdgeItem edgeItem = visibleItems2.next();
                final ForceItem forceItem2 = (ForceItem)edgeItem.getSourceItem().get("_forceItem");
                final ForceItem forceItem3 = (ForceItem)edgeItem.getTargetItem().get("_forceItem");
                final float springCoefficient = this.getSpringCoefficient(edgeItem);
                final float springLength = this.getSpringLength(edgeItem);
                forceSimulator.addSpring(forceItem2, forceItem3, (springCoefficient >= 0.0f) ? springCoefficient : -1.0f, (springLength >= 0.0f) ? springLength : -1.0f);
            }
        }
    }
    
    protected float getMassValue(final VisualItem visualItem) {
        return 1.0f;
    }
    
    protected float getSpringLength(final EdgeItem edgeItem) {
        return -1.0f;
    }
    
    protected float getSpringCoefficient(final EdgeItem edgeItem) {
        return -1.0f;
    }
    
    public VisualItem getReferrer() {
        return this.referrer;
    }
    
    public void setReferrer(final VisualItem referrer) {
        this.referrer = referrer;
    }
    
    static {
        (FORCEITEM_SCHEMA = new Schema()).addColumn("_forceItem", ForceItem.class, new ForceItem());
    }
}
