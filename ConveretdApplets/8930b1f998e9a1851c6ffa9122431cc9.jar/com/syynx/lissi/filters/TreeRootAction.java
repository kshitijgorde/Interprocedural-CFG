// 
// Decompiled by Procyon v0.5.30
// 

package com.syynx.lissi.filters;

import java.util.Iterator;
import prefuse.data.tuple.TupleSet;
import prefuse.data.Tuple;
import prefuse.data.Node;
import prefuse.data.Graph;
import prefuse.Visualization;
import prefuse.action.GroupAction;

public class TreeRootAction extends GroupAction
{
    public TreeRootAction(final String graphGroup) {
        super(graphGroup);
    }
    
    public void run(final double frac) {
        final TupleSet focus = this.m_vis.getGroup(Visualization.FOCUS_ITEMS);
        if (focus == null || focus.getTupleCount() == 0) {
            return;
        }
        final Graph g = (Graph)this.m_vis.getGroup(this.m_group);
        Object f = null;
        final Iterator tuples = focus.tuples();
        while (tuples.hasNext()) {
            f = tuples.next();
            if (f instanceof Node) {
                if (g.containsTuple((Tuple)f)) {
                    break;
                }
                continue;
            }
            else {
                f = null;
            }
        }
        if (f == null) {
            return;
        }
        g.getSpanningTree((Node)f);
    }
}
