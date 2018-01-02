// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.data.expression;

import prefuse.data.Edge;
import prefuse.data.Tuple;

class IsEdgeFunction extends BooleanFunction
{
    public IsEdgeFunction() {
        super(0);
    }
    
    public String getName() {
        return "ISEDGE";
    }
    
    public boolean getBoolean(final Tuple tuple) {
        return tuple instanceof Edge;
    }
}
