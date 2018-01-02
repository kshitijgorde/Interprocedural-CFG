// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Node;
import prefuse.data.Tuple;

class InDegreeFunction extends IntFunction
{
    public InDegreeFunction() {
        super(0);
    }
    
    public String getName() {
        return "INDEGREE";
    }
    
    public int getInt(final Tuple tuple) {
        return (tuple instanceof Node) ? ((Node)tuple).getInDegree() : 0;
    }
}
