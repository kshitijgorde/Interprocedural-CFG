// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Node;
import prefuse.data.Tuple;

class OutDegreeFunction extends IntFunction
{
    public OutDegreeFunction() {
        super(0);
    }
    
    public String getName() {
        return "OUTDEGREE";
    }
    
    public int getInt(final Tuple tuple) {
        return (tuple instanceof Node) ? ((Node)tuple).getOutDegree() : 0;
    }
}
