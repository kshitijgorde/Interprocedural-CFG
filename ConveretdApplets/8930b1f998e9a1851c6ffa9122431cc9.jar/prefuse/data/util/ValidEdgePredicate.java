// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.util;

import prefuse.data.Node;
import prefuse.data.Tuple;
import prefuse.data.Graph;
import prefuse.data.expression.AbstractPredicate;

public class ValidEdgePredicate extends AbstractPredicate
{
    private Graph m_g;
    
    public ValidEdgePredicate(final Graph g) {
        this.m_g = g;
    }
    
    public boolean getBoolean(final Tuple tuple) {
        final Node nodeFromKey = this.m_g.getNodeFromKey(tuple.getInt(this.m_g.getEdgeSourceField()));
        final Node nodeFromKey2 = this.m_g.getNodeFromKey(tuple.getInt(this.m_g.getEdgeTargetField()));
        return nodeFromKey != null && nodeFromKey2 != null;
    }
}
