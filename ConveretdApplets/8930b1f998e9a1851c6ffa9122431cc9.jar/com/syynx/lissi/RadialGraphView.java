// 
// Decompiled by Procyon v0.5.30
// 

package com.syynx.lissi;

import prefuse.visual.EdgeItem;
import prefuse.util.PrefuseLib;
import prefuse.visual.VisualItem;
import prefuse.action.layout.graph.RadialTreeLayout;
import prefuse.action.animate.PolarLocationAnimator;
import prefuse.action.animate.VisibilityAnimator;
import prefuse.action.animate.QualityControlAnimator;
import prefuse.activity.Pacer;
import prefuse.activity.SlowInSlowOutPacer;
import com.syynx.lissi.filters.TreeRootAction;
import prefuse.action.RepaintAction;
import prefuse.action.ActionList;
import prefuse.action.layout.CollapsedSubtreeLayout;
import prefuse.render.RendererFactory;
import prefuse.visual.expression.InGroupPredicate;
import prefuse.render.Renderer;
import prefuse.render.DefaultRendererFactory;
import prefuse.action.assignment.FontAction;
import prefuse.util.FontLib;
import prefuse.action.Action;
import com.syynx.lissi.filters.TextColorAction;
import com.syynx.lissi.filters.NodeColorAction;
import com.syynx.lissi.filters.EdgeColorAction;
import prefuse.data.expression.Predicate;
import com.syynx.lissi.renderers.WeightedEdgeRenderer;
import prefuse.data.Tuple;
import java.util.Iterator;
import prefuse.visual.NodeItem;
import prefuse.data.search.SearchTupleSet;
import prefuse.data.tuple.TupleSet;
import prefuse.data.search.PrefixSearchTupleSet;
import com.syynx.lissi.controls.EdgeNeighborHighlightControl;
import prefuse.controls.FocusControl;
import com.syynx.lissi.controls.NodeToolTipControl;
import prefuse.controls.Control;
import com.syynx.lissi.controls.EdgeToolTipControl;
import prefuse.Visualization;
import java.awt.Dimension;
import prefuse.data.Graph;
import prefuse.action.ItemAction;
import com.syynx.lissi.filters.VisibilityFilterAction;
import com.syynx.lissi.metapanel.FloatWin;
import prefuse.render.EdgeRenderer;
import prefuse.render.LabelRenderer;
import prefuse.Display;

class RadialGraphView extends Display
{
    private static final long serialVersionUID = 1L;
    private LabelRenderer m_nodeRenderer;
    private EdgeRenderer m_edgeRenderer;
    private String m_label;
    private FloatWin fwin;
    public String person;
    public VisibilityFilterAction visFilter;
    private static ItemAction nodeColor;
    private static ItemAction textColor;
    private static RotatableRadialTreeLayout treeLayout;
    private SetSearchTupleAction ssta;
    
    public RadialGraphView(final Graph g, final String label, final Dimension dim, final FloatWin p, final String person) {
        super(new Visualization());
        this.m_label = "label";
        this.fwin = p;
        this.visFilter = new VisibilityFilterAction("tree");
        this.addControlListener(new EdgeToolTipControl());
        this.addControlListener(new NodeToolTipControl());
        this.addControlListener(new FocusControl(1, "filter"));
        this.addControlListener(new EdgeNeighborHighlightControl("recolor"));
        this.ssta = new SetSearchTupleAction(this.m_vis, 1000);
        final SearchTupleSet search = new PrefixSearchTupleSet();
        this.m_vis.addFocusGroup(Visualization.SEARCH_ITEMS, search);
        this.init(g, label, dim, person);
    }
    
    public void run(final String action) {
        this.m_vis.run(action);
    }
    
    public void setBAngle(final double r) {
        RadialGraphView.treeLayout.setOffsetAngle(r);
        this.m_vis.run("rotate");
    }
    
    public static String countEdges(final NodeItem n) {
        int edge = 0;
        final Iterator iEdge = n.edges();
        while (iEdge.hasNext()) {
            iEdge.next();
            ++edge;
        }
        return Integer.toString(edge);
    }
    
    public void init(final Graph g, final String label, final Dimension dim, final String id) {
        this.m_vis.reset();
        String publications = "0";
        String edges = "0";
        this.m_label = label;
        this.m_vis.add("tree", g);
        final Iterator it = this.m_vis.getGroup("tree.nodes").tuples();
        Tuple t = null;
        while (it.hasNext()) {
            t = it.next();
            if (t.getString("id").equals(id)) {
                this.person = t.getString("name");
                publications = t.getString("doccount");
                edges = countEdges((NodeItem)t);
                break;
            }
        }
        if (id != null) {
            this.fwin.displayData(this.person, id, publications, edges);
        }
        this.m_vis.getSourceData("tree.nodes").addColumn("_prefocus", Boolean.TYPE);
        (this.m_nodeRenderer = new LabelRenderer(this.m_label)).setRenderType(2);
        this.m_nodeRenderer.setHorizontalAlignment(2);
        this.m_nodeRenderer.setRoundedCorner(8, 8);
        this.m_edgeRenderer = new WeightedEdgeRenderer();
        this.m_vis.setInteractive("tree.nodes", null, true);
        this.m_vis.setInteractive("tree.edges", null, true);
        final ItemAction edgeColor = new EdgeColorAction("tree.edges");
        RadialGraphView.nodeColor = new NodeColorAction("tree.nodes");
        RadialGraphView.textColor = new TextColorAction("tree.nodes");
        this.m_vis.putAction("textColor", RadialGraphView.textColor);
        final FontAction fonts = new FontAction("tree.nodes", FontLib.getFont("Tahoma", 10.0));
        fonts.add("ingroup('_focus_')", FontLib.getFont("Tahoma", 11.0));
        final DefaultRendererFactory rf = new DefaultRendererFactory(this.m_nodeRenderer);
        rf.add(new InGroupPredicate("tree.edges"), this.m_edgeRenderer);
        this.m_vis.setRendererFactory(rf);
        RadialGraphView.treeLayout = new RotatableRadialTreeLayout("tree");
        this.m_vis.putAction("treeLayout", RadialGraphView.treeLayout);
        final CollapsedSubtreeLayout subLayout = new CollapsedSubtreeLayout("tree");
        this.m_vis.putAction("subLayout", subLayout);
        final ActionList recolor = new ActionList();
        recolor.add(RadialGraphView.textColor);
        recolor.add(RadialGraphView.nodeColor);
        recolor.add(edgeColor);
        recolor.add(new RepaintAction());
        this.m_vis.putAction("recolor", recolor);
        final ActionList visibility = new ActionList();
        visibility.add(this.visFilter);
        visibility.add(RadialGraphView.textColor);
        visibility.add(RadialGraphView.nodeColor);
        visibility.add(edgeColor);
        visibility.add(new RepaintAction());
        this.m_vis.putAction("visibility", visibility);
        final ActionList filter = new ActionList();
        final ActionList rotate = new ActionList();
        rotate.add(RadialGraphView.treeLayout);
        rotate.add(new RepaintAction());
        this.m_vis.putAction("rotate", rotate);
        filter.add(new TreeRootAction("tree"));
        filter.add(fonts);
        filter.add(RadialGraphView.treeLayout);
        filter.add(subLayout);
        filter.add(RadialGraphView.textColor);
        filter.add(RadialGraphView.nodeColor);
        filter.add(edgeColor);
        this.m_vis.putAction("filter", filter);
        final ActionList animate = new ActionList(1250L);
        animate.setPacingFunction(new SlowInSlowOutPacer());
        animate.add(new QualityControlAnimator());
        animate.add(new VisibilityAnimator("tree"));
        animate.add(new FloatWinFlasher(this.fwin));
        animate.add(new PolarLocationAnimator("tree.nodes", "linear"));
        animate.add(new RepaintAction());
        this.m_vis.putAction("animate", animate);
        this.m_vis.alwaysRunAfter("filter", "animate");
        if (t != null) {
            this.m_vis.getFocusGroup(Visualization.FOCUS_ITEMS).addTuple(this.m_vis.getVisualItem("tree.nodes", t));
        }
        this.m_vis.run("treeLayout");
        this.m_vis.run("filter");
        this.m_vis.run("visibility");
    }
    
    public void setHover(final Tuple t) {
        NodeItem n = null;
        final Iterator ts = this.m_vis.getGroup("tree.nodes").tuples();
        while (ts.hasNext()) {
            final Tuple t2 = ts.next();
            if (t2.getString("id").equals(t.getString("ID"))) {
                n = (NodeItem)t2;
                break;
            }
        }
        if (n != null) {
            this.ssta.setSearchTuple(n, true);
        }
    }
    
    public void clearHover(final Tuple t) {
        NodeItem n = null;
        final Iterator ts = this.m_vis.getGroup("tree.nodes").tuples();
        while (ts.hasNext()) {
            final Tuple t2 = ts.next();
            if (t2.getString("id").equals(t.getString("ID"))) {
                n = (NodeItem)t2;
                break;
            }
        }
        if (n != null) {
            this.ssta.setSearchTuple(n, false);
        }
    }
    
    public void setFocus(final Tuple t) {
        NodeItem n = null;
        final Iterator ts = this.m_vis.getGroup("tree.nodes").tuples();
        while (ts.hasNext()) {
            final Tuple t2 = ts.next();
            if (t2.getString("id").equals(t.getString("ID"))) {
                n = (NodeItem)t2;
                break;
            }
        }
        if (n != null) {
            this.m_vis.getFocusGroup(Visualization.FOCUS_ITEMS).setTuple(n);
            this.fwin.displayData(n.getString("name"), n.getString("id"), n.getString("doccount"), countEdges(n));
            this.m_vis.run("filter");
            this.m_vis.run("visibility");
        }
    }
    
    public static class RotatableRadialTreeLayout extends RadialTreeLayout
    {
        private double off;
        
        public RotatableRadialTreeLayout(final String group) {
            super(group);
            this.off = 0.0;
        }
        
        public void setOffsetAngle(final double r) {
            this.off = r;
        }
        
        protected void setPolarLocation(final NodeItem n, final NodeItem p, final double r, final double t) {
            this.setX(n, p, this.m_origin.getX() + r * Math.cos(t));
            this.setY(n, p, this.m_origin.getY() + r * Math.sin(t));
        }
    }
    
    public static class FloatWinFlasher extends Action
    {
        FloatWin _fwin;
        
        public FloatWinFlasher(final FloatWin fwin) {
            this._fwin = fwin;
        }
        
        public void run(final double frac) {
            if (frac == 1.0) {
                this._fwin.active = false;
                this._fwin.repaint();
            }
        }
    }
    
    public static class SetSearchTupleAction implements Runnable
    {
        Tuple t;
        Visualization m_vis;
        Thread th;
        int msec;
        boolean state;
        NodeItem n;
        int timer;
        
        public SetSearchTupleAction(final Visualization vis, final int delay) {
            this.timer = 0;
            this.m_vis = vis;
            this.msec = delay;
            (this.th = new Thread(this)).start();
        }
        
        public void setSearchTuple(final Tuple t, final boolean state) {
            if (!state) {
                if (t != null) {
                    (this.n = (NodeItem)this.m_vis.getVisualItem("tree.nodes", t)).setHover(false);
                    this.n.set(String.valueOf(PrefuseLib.FIELD_PREFIX) + "prefocus", false);
                    final Iterator iter = this.n.edges();
                    while (iter.hasNext()) {
                        final EdgeItem eitem = iter.next();
                        final NodeItem nitem = eitem.getAdjacentItem(this.n);
                        eitem.setHover(false);
                        nitem.setHover(false);
                    }
                }
                this.t = null;
            }
            else {
                this.t = t;
            }
            this.timer = 0;
        }
        
        public void run() {
            while (true) {
                if (this.timer <= this.msec) {
                    if (this.timer == this.msec) {
                        if (this.t != null) {
                            (this.n = (NodeItem)this.m_vis.getVisualItem("tree.nodes", this.t)).set(String.valueOf(PrefuseLib.FIELD_PREFIX) + "prefocus", !this.n.isHighlighted());
                            final Iterator iter = this.n.edges();
                            while (iter.hasNext()) {
                                final EdgeItem eitem = iter.next();
                                final NodeItem nitem = eitem.getAdjacentItem(this.n);
                                eitem.setHover(!eitem.isHighlighted());
                                nitem.setHover(!nitem.isHighlighted());
                            }
                            this.t = null;
                        }
                        this.m_vis.run("recolor");
                    }
                    ++this.timer;
                }
                try {
                    Thread.sleep(1L);
                }
                catch (Exception e) {
                    System.out.println(e.toString());
                }
            }
        }
    }
}
